
package Lib.Auto.Email_Reminder;

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

import Common.Security;
import Common.businessutil.BusinessServiceFactory;
import Common.businessutil.calaloging.CalalogingService;
import Common.businessutil.mail.MailService;
import Common.businessutil.sms.SmsService;
import Lib.Auto.Course.CourseBean;
import Lib.Auto.Department.DepartmentBean;
import Lib.Auto.Designation.DesignationBean;
import Lib.Auto.Group.GroupBean;

import javax.servlet.annotation.WebServlet;

import com.google.gson.Gson;

@WebServlet("/Email_Reminder/MailReminder")


public class Reminder extends HttpServlet implements Serializable {
	 private static Logger log4jLogger = Logger.getLogger(Reminder.class);

	private static final long serialVersionUID = 1L;

		String indexPage = null,flag=null;
		int mailcount=0;
		List SearchArrylist;
	
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
						
			MailService ss = BusinessServiceFactory.INSTANCE.getMailService();
			SmsService sms=BusinessServiceFactory.INSTANCE.getSmsService();
			CalalogingService ss1 = BusinessServiceFactory.INSTANCE.getCalalogingService();
			
			 response.setContentType("application/json");
			
			 
			 try{
					String term = request.getParameter("Dname");
					
					System.out.println("========dept_name autocomplete========="+term);
					
					if(!term.equalsIgnoreCase(null) && !term.equalsIgnoreCase(""))
		             {
					//System.out.println("Data from ajax call " + term);
								    
					   ArrayList<DepartmentBean> list = ss1.getMemberAutoDeptSearch(term);
				       for(DepartmentBean user: list){
				    	     
						//System.out.println(user.getName());
					}       

					String searchList = new Gson().toJson(list);
								
					response.getWriter().write(searchList);  
								
					//System.out.println("Json Value@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+searchList);
		             }	
			}catch(Exception e){
				//e.printStackTrace();
			}    		 
			
				try{
					String term = request.getParameter("Gname");
					
					if(!term.equalsIgnoreCase(null) && !term.equalsIgnoreCase(""))
		             {
					//System.out.println("Data from ajax call " + term);
								    
						ArrayList<GroupBean> list = ss1.getMemberAutoGroupSearch(term);
				       for(GroupBean user: list){
						//System.out.println(user.getName());
					}       

					String searchList = new Gson().toJson(list);
								
					response.getWriter().write(searchList);  
								
					//System.out.println("Json Value@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+searchList);
		             }	
			}catch(Exception e){
				//e.printStackTrace();
			}  
				
				
				try{
					String term = request.getParameter("Cname");
					System.out.println("==========coursename==="+term);
					if(!term.equalsIgnoreCase(null) && !term.equalsIgnoreCase(""))
		             {
					//System.out.println("Data from ajax call " + term);
								    
						ArrayList<CourseBean> list = ss1.getMemberAutoCourseSearch(term);
				       for(CourseBean user: list){
						//System.out.println(user.getName());
					}       

					String searchList = new Gson().toJson(list);
								
					response.getWriter().write(searchList);  
								
					//System.out.println("Json Value@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+searchList);
		             }	
			}catch(Exception e){
				//e.printStackTrace();
			}   
				try{
					String term = request.getParameter("Desig");
					
					if(!term.equalsIgnoreCase(null) && !term.equalsIgnoreCase(""))
		             {
					//System.out.println("Data from ajax call " + term);
								    
						ArrayList<DesignationBean> list = ss1.getMemberAutoDesigSearch(term);
				       for(DesignationBean user: list){
						//System.out.println(user.getName());
					}       

					String searchList = new Gson().toJson(list);
								
					response.getWriter().write(searchList);  
								
					//System.out.println("Json Value@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+searchList);
		             }	
			}catch(Exception e){
				//e.printStackTrace();
			}
			
			
			flag = request.getParameter("flag");
			String filterQuery = "";
				
			if (flag.equals("DueReminderList")) {
				log4jLogger.info("start===========DueReminderList=====");
			System.out.println("===dept group course====="+request.getParameter("Dname")+request.getParameter("Gname")+request.getParameter("Cname"));
				if (!request.getParameter("Dname").equalsIgnoreCase("ALL") && !request.getParameter("Dname").isEmpty())
					
					filterQuery = filterQuery+" and dept_name='"+request.getParameter("Dname")+"'";
				
				if (!request.getParameter("Desig").equalsIgnoreCase("ALL") && !request.getParameter("Desig").isEmpty())
					
					filterQuery = filterQuery+" and desig_name='"+request.getParameter("Desig")+"'";
				
				if (!request.getParameter("Gname").equalsIgnoreCase("ALL") && !request.getParameter("Gname").isEmpty())
					
					filterQuery = filterQuery+" and group_name='"+request.getParameter("Gname")+"'";
				if (!request.getParameter("Cname").equalsIgnoreCase("ALL") && !request.getParameter("Cname").isEmpty())
					
					filterQuery = filterQuery+" and course_name='"+request.getParameter("Cname")+"'";
				
				if (!request.getParameter("year").equalsIgnoreCase("ALL") && !request.getParameter("year").isEmpty())
					
					filterQuery = filterQuery+" and cyear='"+request.getParameter("year")+"'";
				System.out.println("=======filterquery========="+filterQuery);		
				
				SearchArrylist=ss.getDueReminderList(filterQuery);			
				
				session.setAttribute("DueReminderList", SearchArrylist);			
				indexPage = "/Email_Reminder/DueReminder.jsp";
				dispatch(request, response, indexPage);				

			}
		
			
			if (flag.equals("DueReminderMail")) {
				log4jLogger.info("start===========DueReminderMail=====");
				
				String code=request.getParameter("flag1");
				
				mailcount=ss.getDueReminderMail(code);			
				System.out.println(mailcount+"mailcount get successfully");
				if(mailcount>0)	{		
				    indexPage = "/Email_Reminder/index.jsp?check=SaveSuccess&mailCount="+mailcount;
				}else {
					indexPage = "/Email_Reminder/index.jsp?check=SaveFail";
				}
				dispatch(request, response, indexPage);	
				
			}
			
			if (flag.equals("DueReminderSMS")) {
				log4jLogger.info("start===========DueReminderMail=====");
				
				String code=request.getParameter("flag1");
				
				mailcount=sms.getDueReminderSMS(code);			
			
				if(mailcount>0)	{		
				    indexPage = "/Email_Reminder/index.jsp?check=SaveSuccessSMS&mailCount="+mailcount;
				}else {
					indexPage = "/Email_Reminder/index.jsp?check=SaveFailSMS";
				}
				dispatch(request, response, indexPage);
				
			}		
			
		
		}
		catch (Exception sss) {
			throw new ServletException(sss);
		} 
		
	}

	/** 
	 * Instance variable for SQL statement property
	 */

	/****************************************************************
	 *prepare the sql statement for execution
	 **/
	public void dispatch(HttpServletRequest request,
			HttpServletResponse response, String indexPage)
			throws ServletException, IOException {
		RequestDispatcher dispatch = request.getRequestDispatcher(indexPage);
		dispatch.forward(request, response);
	}

	
}
