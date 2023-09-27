package Lib.Auto.Indent_Payment;

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
import Common.businessutil.acquisition.AcquisitionService;
import Common.businessutil.calaloging.CalalogingService;
import Common.businessutil.serialcontrol.SerialcontrolService;
import Lib.Auto.Author.AuthorBean;
import Lib.Auto.JNL_Order.JnlorderBean;
import Lib.Auto.JNL_Order.JnlorderDetailsBean;
import Login.Login;

public class IndentPaymentProcessing extends HttpServlet implements Serializable {
	private static final long serialVersionUID = 1L;

	private static Logger log4jLogger = Logger.getLogger(IndentPaymentProcessing.class);

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
						
			IndentPaymentBean ob=new IndentPaymentBean();			
			
			flag = request.getParameter("flag");			
			
			
			if (flag.equals("new")) {
				log4jLogger.info("start===========new=====");
				ob = new IndentPaymentBean();
				ob=ss.getPaymentMax();				
					
				session.setAttribute("IndentPayment", ob);
				indexPage = "/Indent_Payment/index.jsp";
				dispatch(request, response, indexPage);
			}
			
			
			if(flag.equals("INVOICE"))
			{
				
				log4jLogger.info("==========Indent Invoice No Search========"+flag);
				List PmtArrylist = new ArrayList();
				
				ob=new IndentPaymentBean();
				ob.setInvNo(request.getParameter("name"));
				ob.setAdd2("");
				
				PmtArrylist=ss.getPaymentIndentSearch(ob);				
																						
				request.setAttribute("search", PmtArrylist);
				indexPage ="/Indent_Payment/search_nmvc.jsp?check=ok&flag="+flag+"";
				dispatch(request, response, indexPage);
			}
			
			
			if(flag.equals("PaymentNo"))
			{
				
				log4jLogger.info("==========Payment Number Search========"+flag);
				List PmtArrylist = new ArrayList();
				
				ob=new IndentPaymentBean();
				ob.setInvNo(request.getParameter("name"));
				ob.setAdd2("PaymentNo");
				
				PmtArrylist=ss.getPaymentIndentSearch(ob);				
																						
				request.setAttribute("search", PmtArrylist);
				indexPage ="/Indent_Payment/search_nmvc.jsp?check=ok&flag="+flag+"";
				dispatch(request, response, indexPage);
			}
			
			if(flag.equals("selectV"))
			{
			log4jLogger.info("Inside======flag====="+request.getParameter("flag1"));
						
			
			List OrdJNLArrylist = new ArrayList();
			
						
			String pmt_date="",transdetail="",chequeno="",chequedate="",remarks="",add1="",add2="";
			int pmt_no;
			double totamt=0;
			
			pmt_no=Integer.parseInt(request.getParameter("payrno"));
			pmt_date=request.getParameter("paysdate");
    		transdetail=request.getParameter("transdetail");
			chequeno=request.getParameter("chequeno");
			chequedate=request.getParameter("chequedate");
			totamt=Double.parseDouble(request.getParameter("totamount"));
			remarks=request.getParameter("remarks");
			add1=request.getParameter("add1");
			add2=request.getParameter("add2");			
			 
			 ob=new IndentPaymentBean();
			 ob.setAdd1(request.getParameter("flag1"));
				 
			 ob.setPaymentno(pmt_no);
			 ob.setPaymentsenddate(pmt_date);
			 ob.setTransdetails(transdetail);
			 ob.setCheckno(chequeno);
			 ob.setChequedate(chequedate);
			 ob.setNetamount(totamt);
			 ob.setRemarks(remarks);

			 			
			OrdJNLArrylist=ss.getPaymentIndentInvoice(ob);
			
			
			session.setAttribute("IndentPayment",OrdJNLArrylist);
			session.setAttribute("IndentPaymentSize",OrdJNLArrylist.size());
		
			indexPage ="/Indent_Payment/index.jsp";
			dispatch(request, response, indexPage);
			
			
			}
					
			

			if (flag.equals("Save")) {
				log4jLogger.info("Save===========flag=====" + flag);
								
				ob=new IndentPaymentBean();
				
				String pmt_date="",transdetail="",chequeno="",chequedate="",inv_ref_no="",remarks="",add1="",add2="";
				int pmt_no;
				double totamt=0;
				
				pmt_no=Integer.parseInt(request.getParameter("payrno"));
				pmt_date=Security.TextDate_member(request.getParameter("paysdate"));
				inv_ref_no=request.getParameter("flag1");				
				transdetail=request.getParameter("transdetail");
				chequeno=request.getParameter("chequeno");
				chequedate=Security.TextDate_member(request.getParameter("chequedate"));
				totamt=Double.parseDouble(request.getParameter("totamount"));
				remarks=request.getParameter("remarks");
				add1=request.getParameter("add1");
				add2=request.getParameter("add2");			
				
				 
				 ob.setPaymentno(pmt_no);
				 ob.setPaymentsenddate(pmt_date);
				 ob.setInvNo(inv_ref_no);
				 ob.setTransdetails(transdetail);
				 ob.setCheckno(chequeno);
				 ob.setChequedate(chequedate);
				 ob.setNetamount(totamt);
				 ob.setRemarks(remarks);
				 ob.setAdd1(add1);
				 ob.setAdd2(add2);
				 				 

				 String[] ainvno=null,invamt=null;
					
				 ainvno=request.getParameterValues("sinvno[]");				
				 invamt=request.getParameterValues("samount[]");
				 
				 
				 List OrderSave=null;	
				 OrderSave=ss.getPaymentIndentMasSave(ob);
				 
			if(OrderSave.size()==1){
				
				int len=ainvno.length;
				
				List<Object> saveDetail = new ArrayList<Object>();
								 
				 for (int i=0; i<len; i++) {

					 IndentPaymentBean  pmtDetails = new IndentPaymentBean();
						
						pmtDetails.setPaymentno(pmt_no);
						pmtDetails.setPaymentsenddate(Security.getdate(pmt_date));
						pmtDetails.setCheckno(chequeno);
						pmtDetails.setChequedate(Security.getdate(chequedate));
						pmtDetails.setNetamount(totamt);						
						pmtDetails.setTransdetails(transdetail);			
						
						pmtDetails.setRemarks(remarks);
						pmtDetails.setAdd1(add1);
						pmtDetails.setAdd2(add2);	
						pmtDetails.setInvNo(ainvno[i]);
						pmtDetails.setInvoiceamount(Double.parseDouble(invamt[i]));
						
					    saveDetail.add(pmtDetails);				 
					 
					}
				
				session.setAttribute("IndentPayment",saveDetail);
				session.setAttribute("IndentPaymentSize",saveDetail.size());
				
				log4jLogger.info("----Update Check For Payment For Journal-------"+OrderSave.size());
				indexPage ="/Indent_Payment/index.jsp?check=UpdateCheck";
				
			}else if(OrderSave.size()==2)  {
				
				log4jLogger.info("----Already Paid for this Invoice-------"+OrderSave.size());
				indexPage ="/Indent_Payment/index.jsp?check=PaidCheck";
				
			}else {			
				 
				    int len=ainvno.length;
					
					List<Object> saveDetail = new ArrayList<Object>();					
					
					 for (int i=0; i<len; i++) {				 
				
						 IndentPaymentBean Paymentbean=new IndentPaymentBean();
						 
						 Paymentbean.setInvNo(ainvno[i]);
						 Paymentbean.setInvoiceamount(Double.parseDouble(invamt[i]));
						 Paymentbean.setPaymentno(pmt_no);
						 saveDetail.add(Paymentbean);
					 }					 
					 
				 ss.getPaymentIndentDetailsUpdate(saveDetail);	 
				 indexPage ="/Indent_Payment/index.jsp?check=SaveSuccess";	
				     
			}				
			  dispatch(request, response, indexPage);
			}
			
			
			
			if (flag.equals("search")) {
				log4jLogger.info("Search===========flag=====" + flag);								
				ob=new IndentPaymentBean();
				
				String payment_no="";				
				payment_no=request.getParameter("payrno");			 
				 
				List OrderView=null;
				OrderView=ss.getPaymentIndentDetailsSearch(payment_no);
				 
				if(OrderView.size()>0)  {
				  session.setAttribute("IndentPayment",OrderView);
				  session.setAttribute("IndentPaymentSize",OrderView.size());				
				
				indexPage ="/Indent_Payment/index.jsp";				
				} else {
					indexPage ="/Indent_Payment/index.jsp?check=DeleteFail";							
				}				
				dispatch(request, response, indexPage);				
			}
			
			
			if (flag.equals("delete")) {
				log4jLogger.info("Delete===========RKflag=====" + flag);
				
				String payment_no="";				
				payment_no=request.getParameter("payrno");				 
								 
				List OrderView=null;
				OrderView=ss.getPaymentIndentDetailsSearch(payment_no);				 
				
				if(OrderView.size()>0)
				{							
					ss.getPaymentIndentDelete(payment_no);					
					indexPage ="/Indent_Payment/index.jsp?check=DeletedSuccess";
					
				}else{					
					indexPage ="/Indent_Payment/index.jsp?check=DeleteFail";
				}				
				
				dispatch(request, response, indexPage);
				
			}
			
			
			if (flag.equals("update")) {
				log4jLogger.info("Update===========flag=====" + flag);
								
                ob=new IndentPaymentBean();
				
				String pmt_date="",transdetail="",chequeno="",chequedate="",inv_ref_no="",remarks="",add1="",add2="";
				int pmt_no;
				double totamt=0;
				
				pmt_no=Integer.parseInt(request.getParameter("payrno"));
				pmt_date=Security.TextDate_member(request.getParameter("paysdate"));
				inv_ref_no=request.getParameter("flag1");				
				transdetail=request.getParameter("transdetail");
				chequeno=request.getParameter("chequeno");
				chequedate=Security.TextDate_member(request.getParameter("chequedate"));
				totamt=Double.parseDouble(request.getParameter("totamount"));
				remarks=request.getParameter("remarks");
				add1=request.getParameter("add1");
				add2=request.getParameter("add2");			
				
				 
				 ob.setPaymentno(pmt_no);
				 ob.setPaymentsenddate(pmt_date);
				 ob.setInvNo(inv_ref_no);
				 ob.setTransdetails(transdetail);
				 ob.setCheckno(chequeno);
				 ob.setChequedate(chequedate);
				 ob.setNetamount(totamt);
				 ob.setRemarks(remarks);
				 ob.setAdd1(add1);
				 ob.setAdd2(add2);
				 				 

				 String[] ainvno=null,invamt=null;
					
				 ainvno=request.getParameterValues("sinvno[]");				
				 invamt=request.getParameterValues("samount[]");
				 
				 String payment_no="";
				 payment_no=pmt_no+"";				 
				 
				 ss.getPaymentIndentDelete(payment_no);  //  Delete Payment Master
				 
				 List OrderSave=null;	
				 OrderSave=ss.getPaymentIndentMasSave(ob);

				 int len=ainvno.length;
					
					List<Object> saveDetail = new ArrayList<Object>();					
					
					 for (int i=0; i<len; i++) {				 
				
						 IndentPaymentBean Paymentbean=new IndentPaymentBean();
						 
						 Paymentbean.setInvNo(ainvno[i]);
						 Paymentbean.setInvoiceamount(Double.parseDouble(invamt[i]));
						 Paymentbean.setPaymentno(pmt_no);
						 saveDetail.add(Paymentbean);
					 }					 
					 
				 ss.getPaymentIndentDetailsUpdate(saveDetail);	 
			 
				 
				indexPage ="/Indent_Payment/index.jsp?check=UpdateSuccess";
			 	
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
