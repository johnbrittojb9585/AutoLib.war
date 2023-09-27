package Lib.Auto.Indent_Order;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
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
import Login.Login;

public class IndentOrderProcessing extends HttpServlet implements Serializable {
	private static final long serialVersionUID = 1L;

	private static Logger log4jLogger = Logger.getLogger(IndentOrderProcessing.class);

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
			
			IndentOrderDetailsBean ob=new IndentOrderDetailsBean();			
			flag = request.getParameter("flag");	
			

			if((flag.equals("Sup"))||(flag.equals("IndentNo")) ||(flag.equals("OrderNo")) )
			{				
				log4jLogger.info("IndentOrder Search===========flag====="+flag);
				List OrdJNLArrylist = new ArrayList();
				ArrayList list=null;
				
				ob=new IndentOrderDetailsBean();
				ob.setTitle(request.getParameter("name"));
				ob.setAdd1(request.getParameter("flag"));
				
				OrdJNLArrylist=ss.getOrdIndentSearchName(ob);				
																						
				request.setAttribute("search", OrdJNLArrylist);
				indexPage ="/Indent_Order/search_nmvc.jsp?check=ok&flag="+flag+"";
				dispatch(request, response, indexPage);
			}			
			
						
			if(flag.equals("selectV"))
			{
			log4jLogger.info("Inside======selected Indent====="+request.getParameter("flag1"));
			List OrdJNLArrylist = new ArrayList();
			
			String order_no="",order_date="",quote_no,quote_date="",sup_name="";
			int sup_code;
			
			 order_no=request.getParameter("ordno");
			 order_date=request.getParameter("orddate");
			 quote_no=request.getParameter("quoteno");
			 quote_date=request.getParameter("quotedate");
			 
			 ob=new IndentOrderDetailsBean();
			 ob.setIndentno(request.getParameter("flag1"));
			 
			 if(request.getParameter("sup_code")!="" && request.getParameter("sup_code")!=null)  {
				 
				 sup_code=Integer.parseInt(request.getParameter("sup_code"));
				 ob.setSupcode(sup_code);				 
				 sup_name=request.getParameter("sname");
				 ob.setRemarks(sup_name);
				 log4jLogger.info("Supplier Code:"+request.getParameter("sup_code")+" And Name is:"+sup_name);
			 }
			 
			 ob.setOrdno(order_no);
			 ob.setOrddate(order_date);
			 ob.setQuoteno(quote_no);
			 ob.setQuotedate(quote_date);
			 			
			OrdJNLArrylist=ss.getOrdSelectedIndent(ob);
			
			session.setAttribute("IndentOrd",OrdJNLArrylist);
			session.setAttribute("IndentOrdSize",OrdJNLArrylist.size());
			
			indexPage ="/Indent_Order/index.jsp";
			dispatch(request, response, indexPage);
			
			}
			
			
			if (flag.equals("new")) {
				List AuthorArrylist = new ArrayList();
				AuthorArrylist=ss1.getCurrencyLoad();
				request.setAttribute("currency", AuthorArrylist);				
				
				indexPage ="/Indent_Order/index.jsp";
				dispatch(request, response, indexPage);
				
			}			
	

			if (flag.equals("Save")) {
				log4jLogger.info("Save===========flag=====" + flag);								
				ob=new IndentOrderDetailsBean();
				
				String order_no="",order_date="",quote_no,quote_date="",remarks="",add1="",add2="";
				int sup_code;
				 
				
				 order_no=request.getParameter("ordno");
				 order_date=Security.TextDate_member(request.getParameter("orddate"));
				 quote_no=request.getParameter("quoteno");
				 quote_date=Security.TextDate_member(request.getParameter("quotedate"));
				 remarks=request.getParameter("remarks");
				 add1=request.getParameter("add1");
				 add2=request.getParameter("add2");
				 
				 sup_code=Integer.parseInt(request.getParameter("sup_code"));				 
				 
				 ob.setOrdno(order_no);
				 ob.setOrddate(order_date);
				 ob.setQuoteno(quote_no);
				 ob.setQuotedate(quote_date);
				 ob.setSupcode(sup_code);
				 ob.setRemarks(remarks);
				 ob.setAdd1(add1);
				 ob.setAdd2(add2);
				 	
				 
				 String[] titleno=null,title=null,author=null,copies=null,indentno=null;
					
				 titleno = request.getParameterValues("titleno[]");
				 title = request.getParameterValues("title[]");		
				 author = request.getParameterValues("author[]");				 
				 copies = request.getParameterValues("copies[]");
				 indentno = request.getParameterValues("indentno[]");					
				 
				 log4jLogger.info("OrderNo=====: " + ob.getOrdno());
				 log4jLogger.info("OrderDate===: " + ob.getOrddate());
				 log4jLogger.info("QuoteNo=====: " + ob.getQuoteno());
				 log4jLogger.info("QuoteDate===: " + ob.getQuotedate());
				 log4jLogger.info("Supplier====: " + ob.getSupcode());
				 log4jLogger.info("Remarks=====: " + ob.getRemarks());
				 log4jLogger.info("add1   =====: " + ob.getAdd1());
				 log4jLogger.info("add2   =====: " + ob.getAdd2());
				
				
				 //List OrderSave=null;
				 
				 //OrderSave=ss.getOrdIndentMasSave(OrderSave,ob);		 
				 
				 
				 boolean b=ss.OrdIndentMasCheck(ob);
				 log4jLogger.info("////////////////////////////////////////////////////////"+b);
				 log4jLogger.info("----------------------+++++++++++++++++++++++-------"+!b);
				 
				 if(b==false){
										 
					//List OrderView=null;
					//OrderView=ss.getOrdJNLFullview(order_no);
					
					int len=titleno.length;
					
					List<Object> saveDetail = new ArrayList<Object>();
					//OrderSave=null;
					
					 for (int i=0; i<len; i++) {
				
						 IndentOrderDetailsBean detailsbean=new IndentOrderDetailsBean();
						 
						 detailsbean.setTitleno(Integer.parseInt(titleno[i]));
						 detailsbean.setTitle(title[i]);
						 detailsbean.setAuthor(author[i]);
						 detailsbean.setApprvoedcopy(Integer.parseInt(copies[i]));
						 detailsbean.setIndentno(indentno[i]);
						 						 
						 detailsbean.setOrdno(order_no);
						 detailsbean.setOrddate(Security.getdate(order_date));
						 detailsbean.setQuoteno(quote_no);
						 detailsbean.setQuotedate(Security.getdate(quote_date));
						 detailsbean.setSupcode(sup_code);		
						 detailsbean.setSupplier(request.getParameter("sname"));
						 detailsbean.setRemarks(remarks);
						 detailsbean.setAdd1(add1);
						 detailsbean.setAdd2(add2);	 
						 
						 saveDetail.add(detailsbean);				 
						 
						}
					
					//List AuthorArrylist = new ArrayList();
					//AuthorArrylist=ss1.getCurrencyLoad();
					//request.setAttribute("currency", AuthorArrylist);
					
					session.setAttribute("IndentOrd",saveDetail);
					session.setAttribute("IndentOrdSize",saveDetail.size());
					
					 log4jLogger.info("----Update Check For Order-------"+b);
					 indexPage ="/Indent_Order/index.jsp?check=UpdateCheck";
					 
				 }else {
				
				int len=titleno.length;
				
				List<Object> saveDetail = new ArrayList<Object>();
				//OrderSave=null;
				 
				 for (int i=0; i<len; i++) {
					 log4jLogger.info("*********************************");
					 
					 IndentOrderDetailsBean detailsbean=new IndentOrderDetailsBean();
					 
					 detailsbean.setTitleno(Integer.parseInt(titleno[i]));
					 detailsbean.setTitle(title[i]);
					 detailsbean.setAuthor(author[i]);
					 detailsbean.setApprvoedcopy(Integer.parseInt(copies[i]));
					 detailsbean.setIndentno(indentno[i]);
					 						 
					 detailsbean.setOrdno(order_no);
					 detailsbean.setOrddate(order_date);
					 detailsbean.setQuoteno(quote_no);
					 detailsbean.setQuotedate(quote_date);
					 detailsbean.setSupcode(sup_code);		
					 detailsbean.setSupplier(request.getParameter("sname"));
					 detailsbean.setRemarks(remarks);
					 detailsbean.setAdd1(add1);
					 detailsbean.setAdd2(add2);	 
					 					 				 
					 saveDetail.add(detailsbean);				 
					 
					}								 

				List OrderSave = ss.getOrdIndentMasSave(saveDetail,ob);
				
				indexPage ="/Indent_Order/index.jsp?check=SaveSuccess";
			   }				 
				
				dispatch(request, response, indexPage);
			}			
			
			
			if (flag.equals("search")) {
				log4jLogger.info("Search===========flag=====" + flag);
								
				ob=new IndentOrderDetailsBean();
				
				String order_no="";				
				order_no=request.getParameter("ordno");			 
				ob.setOrddate(order_no);
				 
				List OrderView=null;
				OrderView=ss.getOrdIndentFullView(order_no);
				 
				if(OrderView.size()>0)  {
					
				    session.setAttribute("IndentOrd",OrderView);
				    session.setAttribute("IndentOrdSize",OrderView.size());
				
				    indexPage ="/Indent_Order/index.jsp";				
				} else {
					
					indexPage ="/Indent_Order/index.jsp?check=DeleteFail";							
				}
				
				dispatch(request, response, indexPage);				
			}
			
			
			if (flag.equals("delete")) {
				log4jLogger.info("Delete===========flag=====" + flag);
				
				String order_no="";
				order_no=request.getParameter("ordno");		
				
				List OrderView=null;
				OrderView=ss.getOrdIndentFullView(order_no);
								
				
				if(OrderView.size()>0)
				{						
					/**List CheckView=null;
					//CheckView = ss.getSupInvCheck(order_no);
					String checkinv="";
					if(CheckView.size()>0) {
						 checkinv=(String) CheckView.get(0);
					}
					
					if(checkinv.equalsIgnoreCase(order_no)) {
						indexPage ="/Indent_Order/index.jsp?check=RefferedOther";
					}else {*/
						
					  Iterator it=OrderView.iterator();	
					  String titleno=null;
					  
					  while(it.hasNext())  {
						  
						  IndentOrderDetailsBean view = (IndentOrderDetailsBean) it.next();						  
						  titleno = titleno +","+ view.getTitleno(); 						  
					  }
					  log4jLogger.info("+++++++++++++++++++" + titleno);
						
					  ss.getIndentOrderDelete(order_no,titleno);					
					  indexPage ="/Indent_Order/index.jsp?check=DeletedSuccess";
				//	}
					
				}else{
					
					indexPage ="/Indent_Order/index.jsp?check=DeleteFail";
				}		
				
				dispatch(request, response, indexPage);				
			}
			
			
			if (flag.equals("update")) {
				log4jLogger.info("Update===========flag=====" + flag);
								
                 ob=new IndentOrderDetailsBean();				
				 String order_no="",order_date="",quote_no,quote_date="",remarks="",add1="",add2="";
				 int sup_code;				 
				
				 order_no=request.getParameter("ordno");
				 order_date=Security.TextDate_member(request.getParameter("orddate"));
				 quote_no=request.getParameter("quoteno");
				 quote_date=Security.TextDate_member(request.getParameter("quotedate"));
				 remarks=request.getParameter("remarks");
				 add1=request.getParameter("add1");
				 add2=request.getParameter("add2");
				 
				 sup_code=Integer.parseInt(request.getParameter("sup_code"));				 
				 
				 ob.setOrdno(order_no);
				 ob.setOrddate(order_date);
				 ob.setQuoteno(quote_no);
				 ob.setQuotedate(quote_date);
				 ob.setSupcode(sup_code);
				 ob.setRemarks(remarks);
				 ob.setAdd1(add1);
				 ob.setAdd2(add2);				
				 
				/** List CheckView=null;
				 //CheckView = ss.getSupInvCheck(order_no);
				 String checkinv="";
				 if(CheckView.size()>0) {
					 checkinv=(String) CheckView.get(0);
				 }
				 
					
				 if(checkinv.equalsIgnoreCase(order_no)) {
						indexPage ="/JNL_Order/index.jsp?check=RefferedOther";
				 }else {*/
				 
				 List OrderView=null;
				 OrderView=ss.getOrdIndentFullView(order_no);									
					
				 if(OrderView.size()>0)
				 {		
				    Iterator it=OrderView.iterator();	
					String titleno=null;
						  
					while(it.hasNext())  {
							  
					 IndentOrderDetailsBean view = (IndentOrderDetailsBean) it.next();						  
					 titleno = titleno +","+ view.getTitleno(); 						  
					}
					log4jLogger.info("+++++++++++++++++++" + titleno);					
				    ss.getIndentOrderDelete(order_no,titleno);   // To Delete Record
				 }
				 			 
				 String[] titleno=null,title=null,author=null,copies=null,indentno=null;
					
				 titleno = request.getParameterValues("titleno[]");
				 title = request.getParameterValues("title[]");		
				 author = request.getParameterValues("author[]");				 
				 copies = request.getParameterValues("copies[]");
				 indentno = request.getParameterValues("indentno[]");				 
								
				 int len=titleno.length;
				
				 List<Object> saveDetail = new ArrayList<Object>();
				 				 
				 for (int i=0; i<len; i++) {
					 
					 IndentOrderDetailsBean detailsbean=new IndentOrderDetailsBean();
					 
					 detailsbean.setTitleno(Integer.parseInt(titleno[i]));
					 detailsbean.setTitle(title[i]);
					 detailsbean.setAuthor(author[i]);
					 detailsbean.setApprvoedcopy(Integer.parseInt(copies[i]));
					 detailsbean.setIndentno(indentno[i]);
					 						 
					 detailsbean.setOrdno(order_no);
					 detailsbean.setOrddate(order_date);
					 detailsbean.setQuoteno(quote_no);
					 detailsbean.setQuotedate(quote_date);
					 detailsbean.setSupcode(sup_code);		
					 detailsbean.setSupplier(request.getParameter("sname"));
					 detailsbean.setRemarks(remarks);
					 detailsbean.setAdd1(add1);
					 detailsbean.setAdd2(add2);	 
					 					 				 
					 saveDetail.add(detailsbean);
					 
					}			 
				 				 
				  List OrderSave = ss.getOrdIndentMasSave(saveDetail,ob);	
				  indexPage ="/Indent_Order/index.jsp?check=UpdateSuccess";
				//}
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
