package Lib.Auto.WeekEndHoliday;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
//import java.sql.DatabaseMetaData;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.UnavailableException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import Common.Security;
import Common.businessutil.BusinessServiceFactory;
import Common.businessutil.admin.AdminService;


public class WeekEndHoliday extends HttpServlet implements Serializable {
	 private static Logger log4jLogger = Logger.getLogger(WeekEndHoliday.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String flag,Day_ID,Remarks;
	int flag1,flag2;
	String indexPage = null;
	
	
	

	public void doGet(HttpServletRequest request, HttpServletResponse response) {

		performTask(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) {

		performTask(request, response);

	}
	public void performTask(
		HttpServletRequest request,
		HttpServletResponse response) {

		
						try {
							
							HttpSession session = request.getSession(true);
							String res = Security.checkSecurity(1, session, response, request);		
							if(res.equalsIgnoreCase("Failure"))
							{
								response.sendRedirect("/AutoLib/Tree/sessiontimeout.jsp");  
								return;
							}
							
							response.setContentType("text/html");
				            PrintWriter out = response.getWriter();
				            AdminService ss = BusinessServiceFactory.INSTANCE.getAdminService();
							ArrayList ser=new ArrayList();
							weekendholidaybean ob=null;
							
							flag=request.getParameter("flag");				


						if(flag.equals("save"))
						{		
							log4jLogger.info("start===========WeakEndHoliDaySave====");					
							flag1=Integer.parseInt(request.getParameter("flag1"));
							flag2=Integer.parseInt(request.getParameter("flag2"));
													
								int count=ss.getWeakEndHolidaySave(flag1,flag2);
								
								if(count>0){
									indexPage = "/WeekEndHoliday/index.jsp?check=SaveWeakEnd";
								}else{
									indexPage = "/WeekEndHoliday/index.jsp?check=NotSaveWeakEnd";
								}
								
								dispatch(request, response, indexPage);
						}
						
						if(flag.equals("search"))
						{
							log4jLogger.info("start===========WeakEndHoliDay Retrival====");
							ob=new weekendholidaybean();
							int count = ss.getWeakEndHolidaySearch();
							
							if(count>0){
							//request.setAttribute("serarch", WeekEndArrylist);					
							indexPage = "/WeekEndHoliday/index.jsp?check=RetriveWeakEnd";					
							}else{
								indexPage = "/WeekEndHoliday/index.jsp?check=NotSaveWeakEnd";
							}
							dispatch(request, response, indexPage);						
						}



			 } catch (Exception e) {
					
					}
		    catch (Throwable theException) {
			
		   }
		    finally{
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

	/** 
	 * Instance variable for SQL statement property
	 */
	
	
}
