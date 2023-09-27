package Lib.Auto.Indent_Invoice;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import Common.LibraryConstants;
import Common.Security;
import Common.businessutil.BusinessServiceFactory;
import Common.businessutil.acquisition.AcquisitionService;
import Common.businessutil.calaloging.CalalogingService;
import Common.businessutil.serialcontrol.SerialcontrolService;
import Lib.Auto.Indent_Order.IndentOrderDetailsBean;
import Lib.Auto.JNL_Invoice.JnlInvoiceBean;
import Login.Login;

public class IndentInvProcessing extends HttpServlet implements Serializable {
	private static final long serialVersionUID = 1L;

	private static Logger log4jLogger = Logger.getLogger(IndentInvProcessing.class);

	String indexPage = null;

	String flag = null;


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException {

		performTask(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException {

		performTask(request, response);

	}
	
	
	
	public void performTask(HttpServletRequest request,
			HttpServletResponse response) throws ServletException {
		try {
			HttpSession session = request.getSession(true);
			String res = Security.checkSecurity(1, session, response, request);		
			if(res.equalsIgnoreCase("Failure"))
			{
				response.sendRedirect("/AutoLib/Tree/sessiontimeout.jsp");  
				return;
			}
			
			
			AcquisitionService ss = BusinessServiceFactory.INSTANCE.getAcquisitionService();
			CalalogingService ss1 = BusinessServiceFactory.INSTANCE.getCalalogingService();
			
			flag = request.getParameter("flag");				
									
			if(flag.equals("Sup"))
			{
				
				log4jLogger.info("===OrderNo and Supplier Choose==="+flag);
				List InvArrylist = new ArrayList();
				
				IndentInvBean ob=new IndentInvBean();
				ob.setAdd1(request.getParameter("name"));
				ob.setOrdNo(request.getParameter("ordno"));
				ob.setAdd2("");
				InvArrylist=ss.getInvSearchOrdNo(ob);				
																						
				request.setAttribute("search", InvArrylist);
				indexPage ="/Indent_Invoice/search_nmvc.jsp?check=ok&flag="+flag+"";
				dispatch(request, response, indexPage);
			}			
			
			if(flag.equals("selectV1"))
			{				
				log4jLogger.info("Inside======SELECTED ORDER====="+request.getParameter("flag1"));
				
				List OrdJNLArrylist = new ArrayList();
				
				String commaSeparated=request.getParameter("flag1");
				
				//String [] items = commaSeparated.split(",");
				
				IndentInvBean ob=new IndentInvBean();
				ob.setInvoiceNo(request.getParameter("invno"));
				ob.setInvoicedate(request.getParameter("invdate"));
				ob.setAdd1(commaSeparated);		
				
				OrdJNLArrylist = ss.getInvOrdCheckList(ob);
				
				List AuthorArrylist = new ArrayList();
				AuthorArrylist=ss1.getCurrencyLoad();
				request.setAttribute("currency", AuthorArrylist);				
				
				session.setAttribute("JNLSearch",OrdJNLArrylist);
				session.setAttribute("JNLSearchSize",OrdJNLArrylist.size());
				
				indexPage ="/Indent_Invoice/index.jsp";
				dispatch(request, response, indexPage);				
			}
			
			if(flag.equals("InvoiceNo"))
			{
				
				log4jLogger.info("===Invoice No and Supplier Search==="+flag);
				List InvArrylist = new ArrayList();
				
				IndentInvBean ob=new IndentInvBean();
				ob.setAdd1(request.getParameter("name"));
				ob.setOrdNo(request.getParameter("ordno"));
				ob.setAdd2("InvoiceNo");
				InvArrylist=ss.getInvSearchOrdNo(ob);				
																						
				request.setAttribute("search", InvArrylist);
				indexPage ="/Indent_Invoice/search_nmvc.jsp?check=ok&flag="+flag+"";
				dispatch(request, response, indexPage);
			}
			
			if (flag.equals("new")) {
				List AuthorArrylist = new ArrayList();
				AuthorArrylist=ss1.getCurrencyLoad();
				request.setAttribute("currency", AuthorArrylist);				
				
				indexPage ="/Indent_Invoice/index.jsp";
				dispatch(request, response, indexPage);
				
			}
			
		
			if (flag.equals("Save")) {
				log4jLogger.info("Save===========flag=====" + flag);
								
				IndentInvBean beanobject=new IndentInvBean();
				
				 String order_no="",order_date="",inv_no,inv_date="",remarks="",add1="",add2="";
				 int sup_code;				 
				
				 order_no = request.getParameter("ordno");
				 inv_no = request.getParameter("invno");
				 inv_date = Security.TextDate_member(request.getParameter("invdate"));
				 remarks = request.getParameter("remarks");
				 add1 = request.getParameter("add1");
				 add2 = request.getParameter("add2");
				 
				 beanobject.setInvoiceNo(inv_no);
				 beanobject.setInvoicedate(inv_date);
				 beanobject.setRemarks(remarks);
				 beanobject.setAdd1(add1);
				 beanobject.setAdd2(add2);				 	
				 
				 String[] ordno=null,titleno=null,title=null,copies=null,supcode=null,sname=null,bcost=null,bcurrency=null,bprice=null,bdiscount=null,netprice=null,indtno=null;							
					
					ordno=request.getParameterValues("ordno[]");	
					supcode=request.getParameterValues("supcode[]");
					sname=request.getParameterValues("sname[]");
					copies=request.getParameterValues("copies[]");
					
					titleno=request.getParameterValues("titleno[]");
					title=request.getParameterValues("title[]");
					indtno=request.getParameterValues("indentno[]");
					
					bcost = request.getParameterValues("bcost[]");
					bcurrency = request.getParameterValues("currency[]");
					bprice = request.getParameterValues("bprice[]");
					bdiscount = request.getParameterValues("discount[]");
					netprice = request.getParameterValues("acceptPrice[]");				
								
				//log4jLogger.info("////////////////////////////////////////////////////////");
				
				boolean b=ss.IndentInvoiceCheck(beanobject); 
				 
				String commaSeparated=request.getParameter("flag1");					
				String [] items = commaSeparated.split(",");
				 
				 if(b==false){										 
										
					int len=titleno.length;					
					List<Object> saveDetail = new ArrayList<Object>();
					 
					for (int j1=1;j1<items.length;j1++)  {					
					 for (int i=0; i<len; i++) {

						if(titleno[i].equals(items[j1])) {
						 IndentInvDetailsBean detailsbean=new IndentInvDetailsBean();
					 
						 detailsbean.setTitleno(Integer.parseInt(titleno[i]));
						 detailsbean.setTitle(title[i]);
						 detailsbean.setSupcode(Integer.parseInt(supcode[i]));
						 detailsbean.setSupplier(sname[i]);
						 detailsbean.setCopies(Integer.parseInt(copies[i]));						 
						 detailsbean.setOrdno(ordno[i]);
						 
						 detailsbean.setBcost(Double.parseDouble(bcost[i]));
						 detailsbean.setBcurrency(bcurrency[i]);						 
						 detailsbean.setBprice(Double.parseDouble(bprice[i]));
						 detailsbean.setDiscount(Double.parseDouble(bdiscount[i]));
						 detailsbean.setAmount(Double.parseDouble(netprice[i]));
						 
						 detailsbean.setIndentno(indtno[i]);
						 detailsbean.setInvoiceno(inv_no);
						 detailsbean.setInvoicedate(Security.getdate(inv_date));
						 detailsbean.setRemarks(remarks);
						 detailsbean.setAdd1(add1);
						 detailsbean.setAdd2(add2);	 
						 
						 saveDetail.add(detailsbean);							 
						
					 }else	 {
						 //log4jLogger.info("----False value is-------"+titleno[i]);
					 }
					}
					}
					
					List AuthorArrylist = new ArrayList();
					AuthorArrylist=ss1.getCurrencyLoad();
					request.setAttribute("currency", AuthorArrylist);
					
					session.setAttribute("JNLSearch",saveDetail);
					session.setAttribute("JNLSearchSize",saveDetail.size());
					
					 log4jLogger.info("----Update Check For Order-------");
					 indexPage ="/Indent_Invoice/index.jsp?check=UpdateCheck";
					 
				 }else {
				
						int len=titleno.length;						
						List<Object> saveDetail = new ArrayList<Object>();
						 
						for (int j1=1;j1<items.length;j1++)  {						
						 for (int i=0; i<len; i++) {

							if(titleno[i].equals(items[j1])) {
							 IndentInvDetailsBean detailsbean=new IndentInvDetailsBean();
						 
							 detailsbean.setTitleno(Integer.parseInt(titleno[i]));
							 detailsbean.setTitle(title[i]);
							 detailsbean.setSupcode(Integer.parseInt(supcode[i]));
							 detailsbean.setSupplier(sname[i]);
							 detailsbean.setCopies(Integer.parseInt(copies[i]));						 
							 detailsbean.setOrdno(ordno[i]);
							 
							 detailsbean.setBcost(Double.parseDouble(bcost[i]));
							 detailsbean.setBcurrency(bcurrency[i]);						 
							 detailsbean.setBprice(Double.parseDouble(bprice[i]));
							 detailsbean.setDiscount(Double.parseDouble(bdiscount[i]));
							 detailsbean.setAmount(Double.parseDouble(netprice[i]));
							 
							 detailsbean.setIndentno(indtno[i]);
							 detailsbean.setInvoiceno(inv_no);
							 detailsbean.setInvoicedate(inv_date);
							 detailsbean.setRemarks(remarks);
							 detailsbean.setAdd1(add1);
							 detailsbean.setAdd2(add2);	 
							 
							 saveDetail.add(detailsbean);															 
							
						 }else	 {
							 //log4jLogger.info("----False value is-------"+titleno[i]);
						 }
					}
					}						
			 				 
				 List OrderSave = ss.getIndentInvoiceSave(saveDetail,beanobject);
				 
				 //ss.getSupInvOrdJNLUpdate(commaSeparated,"");
	
				indexPage ="/Indent_Invoice/index.jsp?check=SaveSuccess";
			   }				 
				
				dispatch(request, response, indexPage);
			}
			
			
			
			if (flag.equals("search")) {
				log4jLogger.info("Search===========flag=====" + flag);
								
								
				String inv_no="";				
				inv_no=request.getParameter("invno");			 
								 
				List OrderView=null;
				OrderView=ss.getIndentInvoiceFullView(inv_no);
				 
				if(OrderView.size()>0)  {
					
				  session.setAttribute("JNLSearch",OrderView);
				  session.setAttribute("JNLSearchSize",OrderView.size());
				
				List AuthorArrylist = new ArrayList();
				AuthorArrylist=ss1.getCurrencyLoad();
				request.setAttribute("currency", AuthorArrylist);
				
				indexPage ="/Indent_Invoice/index.jsp";		
				
				} else {
					indexPage ="/Indent_Invoice/index.jsp?check=DeleteFail";							
				}
				
				dispatch(request, response, indexPage);
				
			}
			
			
			if (flag.equals("delete")) {
				log4jLogger.info("Delete===========flag=====" + flag);
				String inv_no="";				
				inv_no=request.getParameter("invno");			 
								 
				List OrderView=null;
				OrderView=ss.getIndentInvoiceFullView(inv_no);	 
				
				if(OrderView.size()>0)
				{						
					
					String commaSeparated=request.getParameter("flag1");					
					//ss.getSupInvOrdJNLUpdate(commaSeparated,"PROCESS");
					
					Iterator it=OrderView.iterator();	
					String titleno=null;
					  
					  while(it.hasNext())  {
						  
						  IndentInvDetailsBean view = (IndentInvDetailsBean) it.next();						  
						  titleno = titleno +","+ view.getTitleno(); 						  
					  }
	
					ss.getIndentInvoiceDelete(inv_no,titleno);				  
					indexPage ="/Indent_Invoice/index.jsp?check=DeletedSuccess";
					
				}else{					
					indexPage ="/Indent_Invoice/index.jsp?check=DeleteFail";
				}
				
				
				dispatch(request, response, indexPage);
				
			}
			
			
			
			
			if (flag.equals("update")) {
				log4jLogger.info("Update===========flag=====" + flag);
								
				IndentInvBean beanobject=new IndentInvBean();
				
				 String order_no="",order_date="",inv_no,inv_date="",remarks="",add1="",add2="";
				 int sup_code;				 
				
				 order_no = request.getParameter("ordno");
				 inv_no = request.getParameter("invno");
				 inv_date = Security.TextDate_member(request.getParameter("invdate"));
				 remarks = request.getParameter("remarks");
				 add1 = request.getParameter("add1");
				 add2 = request.getParameter("add2");
				 
				 beanobject.setInvoiceNo(inv_no);
				 beanobject.setInvoicedate(inv_date);
				 beanobject.setRemarks(remarks);
				 beanobject.setAdd1(add1);
				 beanobject.setAdd2(add2);				 	
				 
				 String[] ordno=null,titleno=null,title=null,copies=null,supcode=null,sname=null,bcost=null,bcurrency=null,bprice=null,bdiscount=null,netprice=null,indtno=null;							
					
					ordno=request.getParameterValues("ordno[]");	
					supcode=request.getParameterValues("supcode[]");
					sname=request.getParameterValues("sname[]");
					copies=request.getParameterValues("copies[]");
					
					titleno=request.getParameterValues("titleno[]");
					title=request.getParameterValues("title[]");
					indtno=request.getParameterValues("indentno[]");
					
					bcost = request.getParameterValues("bcost[]");
					bcurrency = request.getParameterValues("currency[]");
					bprice = request.getParameterValues("bprice[]");
					bdiscount = request.getParameterValues("discount[]");
					netprice = request.getParameterValues("acceptPrice[]");				
								
				log4jLogger.info("////////////////////////////////////////////////////////");				
				 
				String commaSeparated=request.getParameter("flag1");					
				String [] items = commaSeparated.split(",");
				 
				
				List OrderView=null;
				OrderView=ss.getIndentInvoiceFullView(inv_no);	 
				
				if(OrderView.size()>0)
				{						
					
					Iterator it=OrderView.iterator();	
					String title_no=null;
					  
					  while(it.hasNext())  {						  
						  IndentInvDetailsBean view = (IndentInvDetailsBean) it.next();						  
						  title_no = title_no +","+ view.getTitleno(); 						  
					  }	
				    ss.getIndentInvoiceDelete(inv_no,title_no);		 // To Delete the Record 
				}
				
				
			    int len=titleno.length;					
				List<Object> saveDetail = new ArrayList<Object>();
					 
					for (int j1=1;j1<items.length;j1++)  {					
					 for (int i=0; i<len; i++) {

						if(titleno[i].equals(items[j1])) {
						 IndentInvDetailsBean detailsbean=new IndentInvDetailsBean();
					 
						 detailsbean.setTitleno(Integer.parseInt(titleno[i]));
						 detailsbean.setTitle(title[i]);
						 detailsbean.setSupcode(Integer.parseInt(supcode[i]));
						 detailsbean.setSupplier(sname[i]);
						 detailsbean.setCopies(Integer.parseInt(copies[i]));						 
						 detailsbean.setOrdno(ordno[i]);
						 
						 detailsbean.setBcost(Double.parseDouble(bcost[i]));
						 detailsbean.setBcurrency(bcurrency[i]);						 
						 detailsbean.setBprice(Double.parseDouble(bprice[i]));
						 detailsbean.setDiscount(Double.parseDouble(bdiscount[i]));
						 detailsbean.setAmount(Double.parseDouble(netprice[i]));
						 
						 detailsbean.setIndentno(indtno[i]);
						 detailsbean.setInvoiceno(inv_no);
						 detailsbean.setInvoicedate(inv_date);
						 detailsbean.setRemarks(remarks);
						 detailsbean.setAdd1(add1);
						 detailsbean.setAdd2(add2);	 
						 
						 saveDetail.add(detailsbean);							 
						
					 }else	 {
						 log4jLogger.info("----False value is-------"+titleno[i]);
					 }
					}
					}
				List OrderSave = ss.getIndentInvoiceSave(saveDetail,beanobject);	
				indexPage ="/Indent_Invoice/index.jsp?check=UpdateSuccess";
			 	
				dispatch(request, response, indexPage);
			}
			
			
			
			
		}catch(Exception e) {
			e.printStackTrace();
			
		}
		
		
		
	}

	/***************************************************************************
	 * prepare the sql statement for execution
	 */
	public void dispatch(HttpServletRequest request,
			HttpServletResponse response, String indexPage)
			throws ServletException, IOException {
		RequestDispatcher dispatch = request.getRequestDispatcher(indexPage);
		dispatch.forward(request, response);
	}

}
