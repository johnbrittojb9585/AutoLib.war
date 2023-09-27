package Lib.Auto.JNL_Invoice;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
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
import Lib.Auto.Author.AuthorBean;
import Login.Login;

public class InvoiceJNLProcessing extends HttpServlet implements Serializable {
	private static final long serialVersionUID = 1L;

	private static Logger log4jLogger = Logger.getLogger(InvoiceJNLProcessing.class);

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
						
			JnlInvoiceBean ob=new JnlInvoiceBean();			
			
			flag = request.getParameter("flag");			
			
			
			if (flag.equals("new")) {
				log4jLogger.info("start===========new=====");
				ob = new JnlInvoiceBean();
				ob=ss.getInvoiceMax();
				
				request.setAttribute("beanobject", ob);	
				session.setAttribute("JNLInvoice",ob);
				indexPage = "/JNL_Invoice/index.jsp";
				dispatch(request, response, indexPage);			

			}
			
			if(flag.equals("Invoice"))
			{
				
				log4jLogger.info("===Invoice No and Supplier Choose==="+flag);
				List InvArrylist = new ArrayList();
				
				ob=new JnlInvoiceBean();
				ob.setSupplier(request.getParameter("name"));
				ob.setInvoiceno(request.getParameter("invno"));
				ob.setAdd2("");
				InvArrylist=ss.getSupInvNoSearch(ob);				
																						
				request.setAttribute("search", InvArrylist);
				indexPage ="/JNL_Invoice/search_nmvc.jsp?check=ok&flag="+flag+"";
				dispatch(request, response, indexPage);
			}
			
			
			if(flag.equals("SInvoiceNo"))
			{
				
				log4jLogger.info("===Summary Invoice No and Supplier Search==="+flag);
				List InvArrylist = new ArrayList();
				
				ob=new JnlInvoiceBean();
				ob.setSupplier(request.getParameter("name"));
				ob.setInvoiceno(request.getParameter("invno"));
				ob.setAdd2("SInvoiceNo");
				
				InvArrylist=ss.getSupInvNoSearch(ob);				
																						
				request.setAttribute("search", InvArrylist);
				indexPage ="/JNL_Invoice/search_nmvc.jsp?check=ok&flag="+flag+"";
				dispatch(request, response, indexPage);
			}
			
			
			
			/*if(flag.equals("LoadSupInv"))
			{
				
				log4jLogger.info("===Inside LoadSupInv==="+flag);
				List InvArrylist = new ArrayList();
				
				ob=new JnlInvoiceBean();
				ob.setSupplier(request.getParameter("sname"));
				ob.setSupCode(Integer.parseInt(request.getParameter("sup_code")));
				ob.setInvoiceno(request.getParameter("invno"));
				
				InvArrylist=ss.getSupInvDetailsLoad(ob);				
																						
				request.setAttribute("search", InvArrylist);
				indexPage ="/JNL_Invoice/search_nmvc.jsp?check=ok&flag="+flag+"";
				dispatch(request, response, indexPage);
			}*/
			
			

			if (flag.equals("Save")) {
				log4jLogger.info("Save===========flag=====" + flag);
								
				ob=new JnlInvoiceBean();
				
				String order_no="",InvRec_date="",payment_no="",payment_flag="",invoice_no="",invoice_date="",remarks="",add1="",add2="";
				int sup_code,inv_ref_no;
				double totamt=0;				
				
				 inv_ref_no=Integer.parseInt(request.getParameter("invrno"));
				 InvRec_date=Security.TextDate_member(request.getParameter("invrdate"));
				 //order_no=request.getParameter("ordno");
				 sup_code=Integer.parseInt(request.getParameter("sup_code"));
				 payment_flag=request.getParameter("pmtflag");
				 payment_no=request.getParameter("pmtno");
				 invoice_no=request.getParameter("invno");
				 invoice_date=Security.TextDate_member(request.getParameter("invdate"));
				 totamt=Double.parseDouble(request.getParameter("invamount"));
				 remarks=request.getParameter("remarks");
				 add1=request.getParameter("add1");
				 add2=request.getParameter("add2");				 
				 
				 ob.setSno(inv_ref_no);
				 ob.setReceiveddate(InvRec_date);
				 //ob.setOrdNo(order_no);
				 ob.setSupCode(sup_code);
				 ob.setSupplier(request.getParameter("sname"));
				 ob.setPaymentflag(payment_flag);
				 ob.setPaymentno(payment_no);				 
				 ob.setInvoiceno(invoice_no);
				 ob.setInvoicedate(invoice_date);
				 ob.setNetamount(totamt);
				 ob.setRemarks(remarks);
				 ob.setAdd1(add1);
				 ob.setAdd2(add2);
				 
				 
				
				 List OrderSave=null;				 
				 OrderSave=ss.getInvJNLEntrySave(ob);	
	
				 if(OrderSave.size()==1){
					 
					 //List saveDetail=null;				 
    				 //saveDetail=ss.getInvJNLSearch(invoice_no);    
    				    				 
    				 
    					List<Object> finalResults = null;
    					if (OrderSave != null)
    					{			
    						finalResults = new ArrayList<Object>();
    						for (int i = 0; i < OrderSave.size(); i++)
    						{				
    							//Object[] obj = (Object[]) list.get(i);
    							JnlInvoiceBean invDetails = new JnlInvoiceBean();    							
    							
    							invDetails.setSno(ob.getSno());
    							invDetails.setReceiveddate(Security.getdate(ob.getReceiveddate()));
    							//invDetails.setOrdNo(ob.getOrdNo());
    							invDetails.setSupCode(ob.getSupCode());
    							invDetails.setSupplier(ob.getSupplier());
    							invDetails.setInvoiceno(ob.getInvoiceno());
    							invDetails.setInvoicedate(Security.getdate(ob.getInvoicedate()));
    							invDetails.setNetamount(ob.getNetamount());	
    							invDetails.setPaymentflag(ob.getPaymentflag());
    							invDetails.setPaymentno(ob.getPaymentno());
    							invDetails.setRemarks(ob.getRemarks());
    							invDetails.setAdd1(ob.getAdd1());
    							invDetails.setAdd2(ob.getAdd2());				
    							
    							finalResults.add(invDetails);				
    						}
    				 
    					}
    				 
					 
					 session.setAttribute("JNLInvoice",finalResults);
					 session.setAttribute("JNLInvoiceSize",finalResults.size());
						
						 log4jLogger.info("----Update Check For Order-------"+OrderSave.size());
						 indexPage ="/JNL_Invoice/index.jsp?check=UpdateCheck";
				 }else {
					 
					 indexPage ="/JNL_Invoice/index.jsp?check=SaveSuccess";
				 }		 
				
				dispatch(request, response, indexPage);
			}
			
			
			
			if (flag.equals("search")) {
				log4jLogger.info("Search===========flag=====" + flag);								
				ob=new JnlInvoiceBean();
				
				String invoice_no="";				
				invoice_no=request.getParameter("invno");			 
				 
				List OrderView=null;
				OrderView=ss.getInvJNLSearch(invoice_no);
				 
				if(OrderView.size()>0)  {
				  session.setAttribute("JNLInvoice",OrderView);
				  session.setAttribute("JNLInvoiceSize",OrderView.size());				
				
				indexPage ="/JNL_Invoice/index.jsp";				
				} else {
					indexPage ="/JNL_Invoice/index.jsp?check=DeleteFail";							
				}				
				dispatch(request, response, indexPage);				
			}
			
			
			if (flag.equals("delete")) {
				log4jLogger.info("Delete===========flag=====" + flag);
				
				String invoice_no="";							
				invoice_no=request.getParameter("invno");			 
								 
				List OrderView=null;
				OrderView=ss.getInvJNLSearch(invoice_no);				 
				
				if(OrderView.size()>0)
				{							
					ss.getInvJNLDelete(invoice_no);					
					indexPage ="/JNL_Invoice/index.jsp?check=DeletedSuccess";
					
				}else{					
					indexPage ="/JNL_Invoice/index.jsp?check=DeleteFail";
				}				
				
				dispatch(request, response, indexPage);
				
			}
			
			
			if (flag.equals("update")) {
				log4jLogger.info("Update===========flag=====" + flag);
								
                ob=new JnlInvoiceBean();
				
				String order_no="",InvRec_date="",payment_no="",payment_flag="",invoice_no="",invoice_date="",remarks="",add1="",add2="";
				int sup_code,inv_ref_no;
				double totamt=0;				
				
				 inv_ref_no=Integer.parseInt(request.getParameter("invrno"));
				 InvRec_date=Security.TextDate_member(request.getParameter("invrdate"));
				 //order_no=request.getParameter("ordno");
				 sup_code=Integer.parseInt(request.getParameter("sup_code"));
				 payment_flag=request.getParameter("pmtflag");
				 payment_no=request.getParameter("pmtno");
				 invoice_no=request.getParameter("invno");
				 invoice_date=Security.TextDate_member(request.getParameter("invdate"));
				 totamt=Double.parseDouble(request.getParameter("invamount"));
				 remarks=request.getParameter("remarks");
				 add1=request.getParameter("add1");
				 add2=request.getParameter("add2");				 
				 
				 ob.setSno(inv_ref_no);
				 ob.setReceiveddate(InvRec_date);
				 //ob.setOrdNo(order_no);
				 ob.setSupCode(sup_code);
				 ob.setPaymentflag(payment_flag);
				 ob.setPaymentno(payment_no);				 
				 ob.setInvoiceno(invoice_no);
				 ob.setInvoicedate(invoice_date);
				 ob.setNetamount(totamt);
				 ob.setRemarks(remarks);
				 ob.setAdd1(add1);
				 ob.setAdd2(add2);
				 
				 ss.getInvJNLDelete(invoice_no);
				
				 List OrderSave=null;				 
				 OrderSave=ss.getInvJNLEntrySave(ob);		
				 	
				indexPage ="/JNL_Invoice/index.jsp?check=UpdateSuccess";
			 	
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
