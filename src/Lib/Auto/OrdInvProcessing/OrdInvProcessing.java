package Lib.Auto.OrdInvProcessing;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.UnavailableException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.apache.log4j.Logger;

import Common.businessutil.BusinessServiceFactory;
import Common.businessutil.acquisition.AcquisitionService;

import Common.DataQuery;
import Common.Security;
import Common.Security_Counter;
import Lib.Auto.Book.Book;





public class OrdInvProcessing extends HttpServlet {
	private static Logger log4jLogger = Logger.getLogger(OrdInvProcessing.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	String flag="",protocol="";
	String indexPage = null;
	orderbean orderObject=new orderbean();
	ResourceBundle rb = ResourceBundle.getBundle("LocalStrings");
		
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)  throws ServletException {

		performTask(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException{

		performTask(request, response);

	}
	public void performTask(
		HttpServletRequest request,
		HttpServletResponse response)  throws ServletException {


		try {
								HttpSession session = request.getSession(true);
								String res = Security.checkSecurity(1, session, response, request);		
								if(res.equalsIgnoreCase("Failure"))
								{
									response.sendRedirect("/AutoLib/Tree/sessiontimeout.jsp");  
									return;
								}
								
								
								response.setContentType("text/html");
								AcquisitionService ss = BusinessServiceFactory.INSTANCE.getAcquisitionService();
					            PrintWriter out = response.getWriter();
					            String nm=request.getParameter("name");
								flag=request.getParameter("flag");
								
								orderbean ob=null;
		
								if((flag.equals("Slno"))||(flag.equals("Dept"))||(flag.equals("Sup"))||(flag.equals("Bud"))||(flag.equals("Inv"))||(flag.equals("Dept_Report"))||(flag.equals("Sup_Report"))||(flag.equals("Budget_Report")) )
								{
									
									log4jLogger.info("OrdInvSearch===========flag====="+flag);
									List OrdInvArrylist = new ArrayList();
									ArrayList list=null;
									
									 ob=new orderbean();
									ob.setShort_desc(request.getParameter("name"));
									ob.setIadd1(request.getParameter("flag"));
									
									OrdInvArrylist=ss.getOrdInvSearchName(ob);
									
																											
									request.setAttribute("search", OrdInvArrylist);
									indexPage ="/OrdInvProcessing/search_nmvc.jsp?check=ok&flag="+flag+"";
									dispatch(request, response, indexPage);
								}
								
								if(flag.equals("new"))
								{
									log4jLogger.info("New===========flag====="+flag);
									int code=ss.getOrdInvMax();
									int maxcode=code;
									orderObject.setImax(maxcode);
								
								request.setAttribute("viewObject",orderObject);
								indexPage="/OrdInvProcessing/index.jsp?check=newInvoice";
								 dispatch(request, response, indexPage);
							  
								}
								
								
								if(flag.equals("search")){
									
									log4jLogger.info("search===========flag====="+flag);
									ob=new orderbean();
									ob=ss.getOrdInvSearch(request.getParameter("slno"));
									
									if(ob.getImax()>0){
										request.setAttribute("viewObject",ob);
										indexPage="/OrdInvProcessing/index.jsp?check=SuccessInvoice";
										
									}else{
									
										indexPage="/OrdInvProcessing/index.jsp?check=FailureInvoice";
									}
									dispatch(request, response, indexPage);
								}
				
	
								if(flag.equals("save")){
									
									log4jLogger.info("save===========flag====="+flag);
									
									ob=new orderbean();
									ob=ss.getOrdInvSearch(request.getParameter("slno"));
									
									if(ob.getImax()>0){
										
										orderObject=new orderbean();
										orderObject.setImax(Integer.parseInt(request.getParameter("slno")));
										orderObject.setIord(request.getParameter("ordno"));
										orderObject.setIordate(request.getParameter("orddate"));
										orderObject.setIscode(Integer.parseInt(request.getParameter("scode")));
										orderObject.setIbcode(Integer.parseInt(request.getParameter("bcode")));
										orderObject.setIinvno(request.getParameter("invno"));
										orderObject.setIinvdate(request.getParameter("invdate"));
										orderObject.setIamount(Double.parseDouble(request.getParameter("invamount")));
										orderObject.setIdcode(Integer.parseInt(request.getParameter("dcode")));
										orderObject.setIyear(request.getParameter("year"));
										orderObject.setIdtype(request.getParameter("docname"));
										orderObject.setIcrdeb(request.getParameter("crdbflag"));
										orderObject.setIpaid(request.getParameter("paid"));
										orderObject.setIpaydate(request.getParameter("paiddate"));
										orderObject.setImode(request.getParameter("mode"));
										orderObject.setIpaydet(request.getParameter("paydetails"));
										orderObject.setIadd1(request.getParameter("add1"));
										orderObject.setIadd2(request.getParameter("add2"));
										orderObject.setIsname(request.getParameter("sname"));
										orderObject.setIdname(request.getParameter("dname"));
										orderObject.setIbhead(request.getParameter("bname"));
										request.setAttribute("viewObject",orderObject);
										
									
										indexPage="/OrdInvProcessing/index.jsp?check=ConfirmUpdate";
										
									}else{
										orderObject=new orderbean();
										
										orderObject.setImax(Integer.parseInt(request.getParameter("slno")));
										orderObject.setIord(request.getParameter("ordno"));
										orderObject.setIordate(request.getParameter("orddate"));
										orderObject.setIscode(Integer.parseInt(request.getParameter("scode")));
										orderObject.setIbcode(Integer.parseInt(request.getParameter("bcode")));
										orderObject.setIinvno(request.getParameter("invno"));
										orderObject.setIinvdate(request.getParameter("invdate"));
										orderObject.setIamount(Double.parseDouble(request.getParameter("invamount")));
										orderObject.setIdcode(Integer.parseInt(request.getParameter("dcode")));
										orderObject.setIyear(request.getParameter("year"));
										orderObject.setIdtype(request.getParameter("docname"));
										orderObject.setIcrdeb(request.getParameter("crdbflag"));
										orderObject.setIpaid(request.getParameter("paid"));
										orderObject.setIpaydate(request.getParameter("paiddate"));
										orderObject.setImode(request.getParameter("mode"));
										orderObject.setIpaydet(request.getParameter("paydetails"));
										orderObject.setIadd1(request.getParameter("add1"));
										orderObject.setIadd2(request.getParameter("add2"));
										orderObject.setIsname(request.getParameter("sname"));
										orderObject.setIdname(request.getParameter("dname"));
										orderObject.setIbhead(request.getParameter("bname"));
								
										int count=ss.getOrdInvSave(orderObject);
										
										indexPage="/OrdInvProcessing/index.jsp?check=SaveInvoice";
									}
									dispatch(request, response, indexPage);
									
								}
	
								if(flag.equals("update")){
									log4jLogger.info("update===========flag====="+flag);
									orderObject=new orderbean();
									
									orderObject.setImax(Integer.parseInt(request.getParameter("slno")));
									orderObject.setIord(request.getParameter("ordno"));
									orderObject.setIordate(request.getParameter("orddate"));
									orderObject.setIscode(Integer.parseInt(request.getParameter("scode")));
									orderObject.setIbcode(Integer.parseInt(request.getParameter("bcode")));
									orderObject.setIinvno(request.getParameter("invno"));
									orderObject.setIinvdate(request.getParameter("invdate"));
									orderObject.setIamount(Double.parseDouble(request.getParameter("invamount")));
									orderObject.setIdcode(Integer.parseInt(request.getParameter("dcode")));
									orderObject.setIyear(request.getParameter("year"));
									orderObject.setIdtype(request.getParameter("docname"));
									orderObject.setIcrdeb(request.getParameter("crdbflag"));
									orderObject.setIpaid(request.getParameter("paid"));
									orderObject.setIpaydate(request.getParameter("paiddate"));
									orderObject.setImode(request.getParameter("mode"));
									orderObject.setIpaydet(request.getParameter("paydetails"));
									orderObject.setIadd1(request.getParameter("add1"));
									orderObject.setIadd2(request.getParameter("add2"));
									orderObject.setIsname(request.getParameter("sname"));
									orderObject.setIdname(request.getParameter("dname"));
									orderObject.setIbhead(request.getParameter("bname"));
									
									int count=ss.getOrdInvUpdate(orderObject);
									indexPage="/OrdInvProcessing/index.jsp?check=UpdateInvoice";
									dispatch(request, response, indexPage);
								}
	  
								if(flag.equals("delete")){
									log4jLogger.info("delete===========flag====="+flag);
									
									ob=new orderbean();
									ob=ss.getOrdInvSearch(request.getParameter("slno"));
									
									if(ob.getImax()>0){
										
										int count=ss.getOrdInvDelete(request.getParameter("slno"));
										
										indexPage="/OrdInvProcessing/index.jsp?check=DeleteInvoice";
										
									}else{
																				
										indexPage="/OrdInvProcessing/index.jsp?check=FailureInvoice";
									}
									dispatch(request, response, indexPage);
								}
			
			 
	}  catch (Exception e) {

	}
catch (Throwable theException) {

}
finally
{
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
