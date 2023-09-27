package Lib.Auto.JNL_Supplier_Invoice;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
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
import Common.businessutil.calaloging.CalalogingService;
import Common.businessutil.serialcontrol.SerialcontrolService;
import Lib.Auto.JNL_Invoice.JnlInvoiceBean;
import Login.Login;

public class SupInvJNLProcessing extends HttpServlet implements Serializable {
	private static final long serialVersionUID = 1L;

	private static Logger log4jLogger = Logger.getLogger(SupInvJNLProcessing.class);

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
			
			
			SerialcontrolService ss = BusinessServiceFactory.INSTANCE.getSerialcontrolService();
			CalalogingService ss1 = BusinessServiceFactory.INSTANCE.getCalalogingService();
			
			flag = request.getParameter("flag");				
			
			if(flag.equals("selectV1"))
			{				
				log4jLogger.info("Inside======flag====="+request.getParameter("flag1"));
				
				List OrdJNLArrylist = new ArrayList();
				
				String commaSeparated=request.getParameter("flag1");
				
				//String [] items = commaSeparated.split(",");
				
				JnlSupInvBean ob=new JnlSupInvBean();
				ob.setQuoteNo(request.getParameter("invno"));
				ob.setQuotedate(request.getParameter("invdate"));
				ob.setAdd1(commaSeparated);		
				
				OrdJNLArrylist = ss.getSupInvOrdJNLCheckList(ob);
				
				List AuthorArrylist = new ArrayList();
				AuthorArrylist=ss1.getCurrencyLoad();
				request.setAttribute("currency", AuthorArrylist);				
				
				session.setAttribute("JNLSearch",OrdJNLArrylist);
				session.setAttribute("JNLSearchSize",OrdJNLArrylist.size());
				
				indexPage ="/JNL_Supplier_Invoice/index.jsp";
				dispatch(request, response, indexPage);
				
				
			}
			
			
			if(flag.equals("Sup"))
			{
				
				log4jLogger.info("===Invoice OrderNo and Supplier Choose==="+flag);
				List InvArrylist = new ArrayList();
				
				JnlSupInvBean ob=new JnlSupInvBean();
				ob.setAdd1(request.getParameter("name"));
				ob.setOrdNo(request.getParameter("ordno"));
				ob.setAdd2("");
				InvArrylist=ss.getInvSearchOrdNoSup(ob);				
																						
				request.setAttribute("search", InvArrylist);
				indexPage ="/JNL_Supplier_Invoice/search_nmvc.jsp?check=ok&flag="+flag+"";
				dispatch(request, response, indexPage);
			}			
			
			if(flag.equals("SupInvoice"))
			{
				
				log4jLogger.info("===Invoice No and Supplier Search==="+flag);
				List InvArrylist = new ArrayList();
				
				JnlSupInvBean ob=new JnlSupInvBean();
				ob.setAdd1(request.getParameter("name"));
				ob.setOrdNo(request.getParameter("ordno"));
				ob.setAdd2("SupInvoice");
				InvArrylist=ss.getInvSearchOrdNoSup(ob);				
																						
				request.setAttribute("search", InvArrylist);
				indexPage ="/JNL_Supplier_Invoice/search_nmvc.jsp?check=ok&flag="+flag+"";
				dispatch(request, response, indexPage);
			}
			
			if (flag.equals("new")) {
				List AuthorArrylist = new ArrayList();
				AuthorArrylist=ss1.getCurrencyLoad();
				request.setAttribute("currency", AuthorArrylist);				
				
				indexPage ="/JNL_Supplier_Invoice/index.jsp";
				dispatch(request, response, indexPage);
				
			}
			
		
			if (flag.equals("Save")) {
				log4jLogger.info("Save===========flag=====" + flag);
								
				JnlSupInvBean beanobject=new JnlSupInvBean();
				
				String order_no="",order_date="",quote_no,quote_date="",remarks="",add1="",add2="";
				int sup_code;
				 
				
				 order_no=request.getParameter("ordno");
				// order_date=Security.TextDate_member(request.getParameter("orddate"));
				 quote_no=request.getParameter("invno");
				 quote_date=Security.TextDate_member(request.getParameter("invdate"));
				 remarks=request.getParameter("remarks");
				 add1=request.getParameter("add1");
				 add2=request.getParameter("add2");
				 
				 //sup_code=Integer.parseInt(request.getParameter("sup_code"));
				 
				 
				 //beanobject.setOrdNo(order_no);
				 //ob.setOrdate(order_date);
				 
				 beanobject.setQuoteNo(quote_no);
				 beanobject.setQuotedate(quote_date);
				 beanobject.setRemarks(remarks);
				 beanobject.setAdd1(add1);
				 beanobject.setAdd2(add2);
				 	
				 
				 String[] a=null,b=null,c=null,d=null,e=null,f=null,g=null,h=null,j=null,ordno=null,orddt=null,supcode=null,sname=null,jnlname=null;
					
					a=request.getParameterValues("jnlno[]");
					b=request.getParameterValues("frequency[]");		
					c=request.getParameterValues("noofissue[]");
					d=request.getParameterValues("volno[]");
					e=request.getParameterValues("bcost[]");
					f=request.getParameterValues("currency[]");
					g=request.getParameterValues("bprice[]");
					h=request.getParameterValues("discount[]");
					j=request.getParameterValues("acceptPrice[]");
					
					ordno=request.getParameterValues("ordno[]");					
					orddt=request.getParameterValues("orddate[]");
					supcode=request.getParameterValues("supcode[]");
					sname=request.getParameterValues("sname[]");
					jnlname=request.getParameterValues("jnlname[]");
								
				 log4jLogger.info("////////////////////////////////////////////////////////");
				
				
				 List OrderSave=null;				 
				 OrderSave=ss.getSupInvOrdJNLMasSave(beanobject);						 
				 
				String commaSeparated=request.getParameter("flag1");					
				String [] items = commaSeparated.split(",");
					
				log4jLogger.info("Values of======flag====="+items.length);				
				
				 
				 if(OrderSave.size()==1){										 
										
					int len=a.length;
					
					List<Object> saveDetail = new ArrayList<Object>();
					 
					for (int j1=1;j1<items.length;j1++)  {
					
					 for (int i=0; i<len; i++) {

						if(a[i].equals(items[j1])) {
//						 if(Arrays.asList(items).contains(a[i])) {
							
							log4jLogger.info("Orinl:"+a[i]+" And "+items[j1]);
						
						// ob.setJNL_Accno(a[i]);
				
						 JnlSupInvDetailsBean detailsbean=new JnlSupInvDetailsBean();
						 
						 detailsbean.setJnlCode(Integer.parseInt(a[i]));
						 detailsbean.setJournal(jnlname[i]);
						 detailsbean.setFrequency(b[i]);
						 detailsbean.setSubsfrmdate(request.getParameter("subsfrom"+a[i]+""));
						 detailsbean.setSubstodate(request.getParameter("substo"+a[i]+""));
						 detailsbean.setNoofissue(Integer.parseInt(c[i]));
						 detailsbean.setVolumeNo(d[i]);
						 detailsbean.setBcost(Double.parseDouble(e[i]));
						 detailsbean.setBcurrency(f[i]);
						 
						 
						 detailsbean.setBprice(Double.parseDouble(g[i]));
						 detailsbean.setDiscount(Double.parseDouble(h[i]));
						 detailsbean.setNetamount(Double.parseDouble(j[i]));
						 
						 detailsbean.setOrdNo(ordno[i]);
						 //detailsbean.setOrdDate(Security.getdate(orddt[i]));
						 detailsbean.setQuoteNo(quote_no);
						 detailsbean.setQuoteDate(Security.getdate(quote_date));
						 detailsbean.setSupCode(Integer.parseInt(supcode[i]));		
						 detailsbean.setSupplier(sname[i]);
						 detailsbean.setRemarks(remarks);
						 detailsbean.setAdd1(add1);
						 detailsbean.setAdd2(add2);	 
						 
						 saveDetail.add(detailsbean);				 
						 
						
					 }else
					 {
						 log4jLogger.info("----False value is-------"+a[i]);
					 }
					}
					}
					List AuthorArrylist = new ArrayList();
					AuthorArrylist=ss1.getCurrencyLoad();
					request.setAttribute("currency", AuthorArrylist);
					
					session.setAttribute("JNLSearch",saveDetail);
					session.setAttribute("JNLSearchSize",saveDetail.size());
					
					 log4jLogger.info("----Update Check For Order-------"+OrderSave.size());
					 indexPage ="/JNL_Supplier_Invoice/index.jsp?check=UpdateCheck";
					 
				 }else {
				
					 int len=a.length;
						
						List<Object> saveDetail = new ArrayList<Object>();
						 
						for (int j1=1;j1<items.length;j1++)  {
						
						 for (int i=0; i<len; i++) {

							if(a[i].equals(items[j1])) {
//							 if(Arrays.asList(items).contains(a[i])) {
								
								log4jLogger.info("Orinl:"+a[i]+" And "+items[j1]);
			
							 JnlSupInvDetailsBean detailsbean=new JnlSupInvDetailsBean();
							 
							 detailsbean.setJnlCode(Integer.parseInt(a[i]));
							 detailsbean.setJournal(jnlname[i]);
							 detailsbean.setFrequency(b[i]);
							 detailsbean.setSubsfrmdate(Security.TextDate_member(request.getParameter("subsfrom"+a[i]+"")));
							 detailsbean.setSubstodate(Security.TextDate_member(request.getParameter("substo"+a[i]+"")));
							 detailsbean.setNoofissue(Integer.parseInt(c[i]));
							 detailsbean.setVolumeNo(d[i]);
							 detailsbean.setBcost(Double.parseDouble(e[i]));
							 detailsbean.setBcurrency(f[i]);							 
							 
							 detailsbean.setBprice(Double.parseDouble(g[i]));
							 detailsbean.setDiscount(Double.parseDouble(h[i]));
							 detailsbean.setNetamount(Double.parseDouble(j[i]));
							 
							 detailsbean.setOrdNo(ordno[i]);
							 detailsbean.setQuoteNo(quote_no);
							 detailsbean.setQuoteDate(quote_date);
							 detailsbean.setSupCode(Integer.parseInt(supcode[i]));		
							 detailsbean.setSupplier(sname[i]);
							 detailsbean.setRemarks(remarks);
							 detailsbean.setAdd1(add1);
							 detailsbean.setAdd2(add2);	 
							 
							 saveDetail.add(detailsbean);				 
							 
							
						 }else
						 {
							 log4jLogger.info("----False value is-------"+a[i]);
						 }
					}
					}
				 OrderSave=null;									 				 
				 OrderSave=ss.getSupInvOrdJNLDetailsSave(saveDetail);
				 
				 ss.getSupInvOrdJNLUpdate(commaSeparated,"");
	
				indexPage ="/JNL_Supplier_Invoice/index.jsp?check=SaveSuccess";
			   }				 
				
				dispatch(request, response, indexPage);
			}
			
			
			
			if (flag.equals("search")) {
				log4jLogger.info("Search===========flag=====" + flag);
								
								
				String inv_no="";				
				inv_no=request.getParameter("invno");			 
								 
				List OrderView=null;
				OrderView=ss.getSupInvOrdJNLFullview(inv_no);
				 
				if(OrderView.size()>0)  {
				  session.setAttribute("JNLSearch",OrderView);
				  session.setAttribute("JNLSearchSize",OrderView.size());
				
				List AuthorArrylist = new ArrayList();
				AuthorArrylist=ss1.getCurrencyLoad();
				request.setAttribute("currency", AuthorArrylist);
				indexPage ="/JNL_Supplier_Invoice/index.jsp";				
				} else {
					indexPage ="/JNL_Supplier_Invoice/index.jsp?check=DeleteFail";							
				}
				
				dispatch(request, response, indexPage);
				
			}
			
			
			if (flag.equals("delete")) {
				log4jLogger.info("Delete===========flag=====" + flag);
				String inv_no="";				
				inv_no=request.getParameter("invno");			 
								 
				List OrderView=null;
				OrderView=ss.getSupInvOrdJNLFullview(inv_no);
				 
				
				if(OrderView.size()>0)
				{		
					
					String commaSeparated=request.getParameter("flag1");
					
					ss.getSupInvOrdJNLUpdate(commaSeparated,"PROCESS");
					ss.getSupInvOrdJNLDelete(inv_no);
					
					indexPage ="/JNL_Supplier_Invoice/index.jsp?check=DeletedSuccess";
					
				}else{
					
					indexPage ="/JNL_Supplier_Invoice/index.jsp?check=DeleteFail";
				}
				
				
				dispatch(request, response, indexPage);
				
			}
			
			
			
			
			if (flag.equals("update")) {
				log4jLogger.info("Update===========flag=====" + flag);
								
                JnlSupInvBean beanobject=new JnlSupInvBean();
				
				String order_no="",order_date="",inv_no,inv_date="",remarks="",add1="",add2="";
				int sup_code;
				 
				
				 order_no=request.getParameter("ordno");
				// order_date=Security.TextDate_member(request.getParameter("orddate"));
				 inv_no=request.getParameter("invno");
				 inv_date=Security.TextDate_member(request.getParameter("invdate"));
				 remarks=request.getParameter("remarks");
				 add1=request.getParameter("add1");
				 add2=request.getParameter("add2");
				 
				 //sup_code=Integer.parseInt(request.getParameter("sup_code"));
				 
				 
				 //beanobject.setOrdNo(order_no);
				 //ob.setOrdate(order_date);
				 
				 beanobject.setQuoteNo(inv_no);
				 beanobject.setQuotedate(inv_date);
				 beanobject.setRemarks(remarks);
				 beanobject.setAdd1(add1);
				 beanobject.setAdd2(add2);
				 	
				 
				 String[] a=null,b=null,c=null,d=null,e=null,f=null,g=null,h=null,j=null,ordno=null,orddt=null,supcode=null,sname=null,jnlname=null;
					
					a=request.getParameterValues("jnlno[]");
					b=request.getParameterValues("frequency[]");		
					c=request.getParameterValues("noofissue[]");
					d=request.getParameterValues("volno[]");
					e=request.getParameterValues("bcost[]");
					f=request.getParameterValues("currency[]");
					g=request.getParameterValues("bprice[]");
					h=request.getParameterValues("discount[]");
					j=request.getParameterValues("acceptPrice[]");
					
					ordno=request.getParameterValues("ordno[]");					
					orddt=request.getParameterValues("orddate[]");
					supcode=request.getParameterValues("supcode[]");
					sname=request.getParameterValues("sname[]");
					jnlname=request.getParameterValues("jnlname[]");
								
				 log4jLogger.info("////////////////////////////////////////////////////////");
				
				 String commaSeparated=request.getParameter("flag1");					
				 String [] items = commaSeparated.split(",");
				
				 ss.getSupInvOrdJNLUpdate(inv_no,"SAVEUPDATE");
				 ss.getSupInvOrdJNLDelete(inv_no);
				 
				 List OrderSave=null;				 
				 OrderSave=ss.getSupInvOrdJNLMasSave(beanobject);					 
				
				 int len=a.length;
				
				 List<Object> saveDetail = new ArrayList<Object>();
				 
				 for (int j1=1;j1<items.length;j1++)  {
				
				 for (int i=0; i<len; i++) {

					if(a[i].equals(items[j1])) {
//					 if(Arrays.asList(items).contains(a[i])) {
						
					log4jLogger.info("Orinl:"+a[i]+" And "+items[j1]);
	
					 JnlSupInvDetailsBean detailsbean=new JnlSupInvDetailsBean();
					 
					 detailsbean.setJnlCode(Integer.parseInt(a[i]));
					 detailsbean.setJournal(jnlname[i]);
					 detailsbean.setFrequency(b[i]);
					 detailsbean.setSubsfrmdate(Security.TextDate_member(request.getParameter("subsfrom"+a[i]+"")));
					 detailsbean.setSubstodate(Security.TextDate_member(request.getParameter("substo"+a[i]+"")));
					 detailsbean.setNoofissue(Integer.parseInt(c[i]));
					 detailsbean.setVolumeNo(d[i]);
					 detailsbean.setBcost(Double.parseDouble(e[i]));
					 detailsbean.setBcurrency(f[i]);							 
					 
					 detailsbean.setBprice(Double.parseDouble(g[i]));
					 detailsbean.setDiscount(Double.parseDouble(h[i]));
					 detailsbean.setNetamount(Double.parseDouble(j[i]));
					 
					 detailsbean.setOrdNo(ordno[i]);
					 detailsbean.setQuoteNo(inv_no);
					 detailsbean.setQuoteDate(inv_date);
					 detailsbean.setSupCode(Integer.parseInt(supcode[i]));		
					 detailsbean.setSupplier(sname[i]);
					 detailsbean.setRemarks(remarks);
					 detailsbean.setAdd1(add1);
					 detailsbean.setAdd2(add2);	 
					 
					 saveDetail.add(detailsbean);				 
					 
					
				 }else
				 {
					 log4jLogger.info("----False value is-------"+a[i]);
				 }
			}
			}
		        
				OrderSave=null;									 				 
		        OrderSave=ss.getSupInvOrdJNLDetailsSave(saveDetail);
		 
		        ss.getSupInvOrdJNLUpdate(commaSeparated,"");
	
				indexPage ="/JNL_Supplier_Invoice/index.jsp?check=UpdateSuccess";
			 	
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
