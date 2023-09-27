package Lib.Auto.Suggestion;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

import org.apache.log4j.Logger;

import Common.Security;
import Common.businessutil.BusinessServiceFactory;
import Common.businessutil.calaloging.CalalogingService;
import Common.businessutil.mail.MailService;
import Lib.Auto.Author.AuthorBean;
import Lib.Auto.Book.bookbean;
import Lib.Auto.Department.DepartmentBean;
import Lib.Auto.EBook.EBookBean;
import Lib.Auto.Suggestion.SuggestionBean;





public class SuggestionServlet extends HttpServlet implements Serializable {
	 private static Logger log4jLogger = Logger.getLogger(SuggestionServlet.class);

	private static final long serialVersionUID = -8906987252709033668L;

	String protocol = "", flag = "";
	
	String sugName = "", SameCode = "";
	String sql="";
	String nam="";
    String filename="";
	int  mailcount=0;
	String indexPage = null;
		
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException {
			
		performTask(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException {

		performTask(request, response);
	}

	public void performTask(HttpServletRequest request,HttpServletResponse response) throws ServletException {

		try {			
			HttpSession session = request.getSession(true);
			String res = Security.checkSecurity(1, session, response, request);		
			if(res.equalsIgnoreCase("Failure"))
			{
				response.sendRedirect("/AutoLib/Tree/sessiontimeout.jsp");  
				return;
			}  
			
			PrintWriter out = response.getWriter();
			CalalogingService ss = BusinessServiceFactory.INSTANCE.getCalalogingService();
			MailService ss2 = BusinessServiceFactory.INSTANCE.getMailService();
									
			SuggestionBean ob = null;
			flag = request.getParameter("flag");
			String dtt = "1800-01-01";
			if (flag.equals("new")) {
				log4jLogger.info("start===========new=====");
				ob = new SuggestionBean();
				ob=ss.getSuggestionMax();
								
				request.setAttribute("beanobject", ob);			
				indexPage = "/Suggestion/index.jsp?check=newSuggestion";
				dispatch(request, response, indexPage);
			
			}
			
			if (flag.equals("search")) {
				log4jLogger.info("start===========search====="+flag);
				ob = new SuggestionBean();
				
				ob=ss.getSuggestionSearch(Integer.parseInt(request.getParameter("sugNo")));
				
				if(ob!=null){
					request.setAttribute("beanobject", ob);
					
					indexPage = "/Suggestion/index.jsp?check=searchSuggestion";	
				}else{
					indexPage = "/Suggestion/index.jsp?check=FailSuggestion";
				}
				dispatch(request, response, indexPage);

			}
			
			if (flag.equals("delete")) {		
				log4jLogger.info("start===========delete=====");
				ob = new SuggestionBean();				
				ob=ss.getSuggestionSearch(Integer.parseInt(request.getParameter("sugNo")));				
				if(ob!=null){				
					request.setAttribute("beanobject", ob);
					indexPage = "/Suggestion/index.jsp?check=deleteCheck";
				}else{
					request.setAttribute("beanobject", ob);
					indexPage = "/Suggestion/index.jsp?check=RecordNot";	
				}
				dispatch(request, response, indexPage);
			}
		
				if(flag.equals("Confirmdelete")){
					log4jLogger.info("Confirmdelete===========flag====="+flag);
								
						int count=ss.getSuggestionDelete(Integer.parseInt(request.getParameter("sugNo")));

						indexPage = "/Suggestion/index.jsp?check=DeleteSuggestion";
						dispatch(request, response, indexPage);
				   }

			
			if (flag.equals("update")) {
				log4jLogger.info("start===========update=====");
				ob = new SuggestionBean();
				
				ob.setMemberCode(request.getParameter("memberCode"));
				ob.setDoc(request.getParameter("doc"));
		        ob.setRcDate(Security.TextDate_Update(request.getParameter("rcDate")));
		        ob.setSugName(request.getParameter("sugName"));
		        ob.setSugNo(Integer.parseInt(request.getParameter("sugNo")));
		        ob.setRemarks(request.getParameter("remarks"));
		        ob.setActionTaken("");
				
				int count=ss.getSuggestionUpdate(ob);
				request.setAttribute("BeanObject", ob);				
				indexPage = "/Suggestion/index.jsp?check=UpdateSuggestion";				
				dispatch(request, response, indexPage);
			}	

				
				if (flag.equals("save"))
				{
					 		ob = new SuggestionBean();
						    SuggestionBean eb = null;
					 		
						    ob.setMemberCode(request.getParameter("memberCode"));
					        ob.setDoc(request.getParameter("doc"));
					        ob.setRcDate(Security.TextDate_Insert(request.getParameter("rcDate")));
					      //ob.setMemberCode(String.valueOf(session.getAttribute("UserID")));
					        ob.setSugName(request.getParameter("sugName"));
					        ob.setSugNo(Integer.parseInt(request.getParameter("sugNo")));
					        ob.setRemarks(request.getParameter("remarks"));
					        ob.setActionTaken("");				        
					        ob.setActionTakenDate(Security.TextDate_Insert(dtt));	
					        ob.setStatus("Pending");
					        eb=ss.getSuggestionSearch(Integer.parseInt(request.getParameter("sugNo")));
					        
					        if (eb != null) {
								
								 ob = new SuggestionBean();
								 
								 ob.setDoc(request.getParameter("doc"));
							     ob.setRcDate(Security.TextDate_Insert(request.getParameter("rcDate")));
							     //ob.setMemberCode(String.valueOf(session.getAttribute("UserID")));
							     ob.setMemberCode(request.getParameter("memberCode"));
							     ob.setSugName(request.getParameter("sugName"));
							     ob.setSugNo(Integer.parseInt(request.getParameter("sugNo")));
							     ob.setRemarks(request.getParameter("remarks"));
							   
							     request.setAttribute("beanobject", ob);
								 indexPage = "/Suggestion/index.jsp?check=UpdateCheck";
								 dispatch(request, response, indexPage);
					        }
					        else {
					        	ob.setDoc(request.getParameter("doc"));
							     ob.setRcDate(Security.TextDate_Insert(request.getParameter("rcDate")));
							     ob.setMemberCode(request.getParameter("memberCode"));
							   //ob.setMemberCode(String.valueOf(session.getAttribute("UserID")));
							     ob.setSugName(request.getParameter("sugName"));
							     ob.setSugNo(Integer.parseInt(request.getParameter("sugNo")));
							     ob.setRemarks(request.getParameter("remarks"));
							   			        										
							     int count=ss.getSuggestionSave(ob);
							   
							     mailcount=ss2.getSugReqMail(ob);	
							     
							     //String[] toaddress={"ramsathyan@yahoo.com","ram.nathan@ymail.com"};
							     
							     
							     //int a=ss1.getSendEmail(toaddress,request.getParameter("doc"),request.getParameter("sugName"));		
							     
							     indexPage = "/Suggestion/index.jsp?check=SaveSuggestion";	
							     dispatch(request, response, indexPage);
					}		
				
				}
		}
	catch (Exception sss) {
		throw new ServletException(sss);
		//sss.printStackTrace();
	} finally {
					
	}

	}
				
	public void dispatch(HttpServletRequest request,HttpServletResponse response, String indexPage)
			throws ServletException, IOException {
		RequestDispatcher dispatch = request.getRequestDispatcher(indexPage);
		dispatch.forward(request, response);
	}
	
}
