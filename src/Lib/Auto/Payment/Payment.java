package Lib.Auto.Payment;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import Common.Security;
import Common.businessutil.BusinessServiceFactory;
import Common.businessutil.calaloging.CalalogingService;
import Common.businessutil.circulation.CirculationService;

import com.google.gson.Gson;

@WebServlet("/Payment/PaymentServlet")

public class Payment extends HttpServlet implements Serializable {
	 private static Logger log4jLogger = Logger.getLogger(Payment.class);

	 private static final long serialVersionUID = 1L;
	 
	 String indexPage=null,flag;
		String term="";
 
	 
		public void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException {

			performTask(request, response);

		}

		public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException {

			performTask(request, response);

		}
	 
		public void performTask(
				HttpServletRequest request,
				HttpServletResponse response)throws ServletException {

			try {
				HttpSession session = request.getSession(true);
				PrintWriter out=response.getWriter();
				String res = Security.checkSecurity(1, session, response, request);		
				if(res.equalsIgnoreCase("Failure"))
				{
					response.sendRedirect("/AutoLib/Tree/sessiontimeout.jsp");  
					return;
				}
				
				
				CirculationService ss = BusinessServiceFactory.INSTANCE.getCirculationService();
				CalalogingService ss1 = BusinessServiceFactory.INSTANCE.getCalalogingService();
				
				response.setContentType("application/json");
				
				 try{
						String term = request.getParameter("user_no");
						//System.out.println("Data from ajax call " + term);
								
					    //log4jLogger.info("::::::::::$$$$$$$$$$$$$$$$$$$::::::::::::::"+request.getParameter("flag"));
							
							
						//ArrayList<String> list = ss.getFrameWork(term);
					    ArrayList<PaymentBean> list = ss1.getUserNoSearch(term);
					    for(PaymentBean user: list){
							//System.out.println(user.getMcode());
						} 

						String searchList = new Gson().toJson(list);
									
						response.getWriter().write(searchList);  
									
						//System.out.println("Json Value@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+searchList);
						
				}catch(Exception e){
					e.printStackTrace();
				}
				
				
				PaymentBean paybean=new PaymentBean();	
				
				
				
				flag=request.getParameter("flag");
				
								
				if(flag.equals("user")){
					log4jLogger.info("start=========== Retrive user in Payment Master =====");
					paybean=new PaymentBean();	
					
					paybean=ss.getPaymentMember(request.getParameter("user_no").toString().trim());
					
					String ab=paybean.getMcode();
					request.setAttribute("bean",paybean);
					
					if(ab!="NIL")
					{						
					indexPage = "/Payment/index.jsp?check=userdetails&message=TRANSDETAILS&info=PAIDDETAILS";
					}
					else
					{						
						indexPage = "/Payment/index.jsp?check=FailMember";
					}
					
					dispatch(request, response, indexPage);
				}
				
				if(flag.equals("clear")) {
					log4jLogger.info("clear===========flag====="+flag);
					indexPage="/Payment/index.jsp";
					dispatch(request, response, indexPage);
				}
				
				if(flag.equals("new")){
					log4jLogger.info("start=========== Retrive Bill NO in Payment Master =====");
					int bno=0;
					bno=ss.getPaymentBill_no();
					
					
					if(bno>0)
					{
						
					indexPage = "/Payment/index.jsp?check=newbillno";
					}
					else
					{
						
						indexPage = "/Payment/index.jsp?check=newbean";
					}
					dispatch(request, response, indexPage);
				}
				
				if(flag.equals("save")){
					log4jLogger.info("start=========== Save to Payment Master =====");
					int bno=0,pb=0;
				    paybean=new PaymentBean();
				    
				    paybean.setBill_No(Integer.parseInt(request.getParameter("bill_no")));
					paybean.setMcode(request.getParameter("user_no"));
				    paybean.setPdate(Security.getdate(request.getParameter("pdate")));
				    paybean.setCurrent_Amt(Double.parseDouble(request.getParameter("current_amt")));
				    paybean.setDept(String.valueOf(session.getAttribute("UserID")));
				    paybean.setPaymentmode(request.getParameter("Type"));
				
					bno=ss.getAddPayment(paybean);
					
					
					if(bno>0)
					{
						
						paybean=ss.getPaymentMember(request.getParameter("user_no").toString().trim());			
						request.setAttribute("bean",paybean);
						
						
					indexPage = "/Payment/index.jsp?check=userdetails&details=SavePayment&message=TRANSDETAILS&info=PAIDDETAILS";
					}
					else
					{
						
						indexPage = "/Payment/index.jsp?check=newbean";
					}
					dispatch(request, response, indexPage);
				}
				
				
				
				
				 } catch (Exception sss) {
						throw new ServletException(sss);
						
					} finally {
						try{
							
						}catch(Exception e){
						e.printStackTrace();
						}
					}
			
		}


public void dispatch(HttpServletRequest request,
		HttpServletResponse response, String indexPage)
		throws ServletException, IOException {
	RequestDispatcher dispatch = request.getRequestDispatcher(indexPage);
	dispatch.forward(request, response);
}
}