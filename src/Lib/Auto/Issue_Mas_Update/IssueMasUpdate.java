package Lib.Auto.Issue_Mas_Update;
import Lib.Auto.Fine.Fine;
import Lib.Auto.Fine.Finebean;
import Lib.Auto.Holiday.Holidaybean;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
//import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

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


public class IssueMasUpdate extends HttpServlet implements Serializable {
	 private static Logger log4jLogger = Logger.getLogger(IssueMasUpdate.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	IssueMasUpdatebean ob=new IssueMasUpdatebean();
	String indexPage = null;
	String flag="",frmdt="",todt="",updt="",strsql="",sqlquery="";
	
	
	

	public void doGet(HttpServletRequest request, HttpServletResponse response) {

		performTask(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) {

		performTask(request, response);

	}
	public void performTask(
		HttpServletRequest request,
		HttpServletResponse response)
	{
	
	   try 
	   {
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
				flag=request.getParameter("flag");
				
				

				if(flag.equalsIgnoreCase("update"))
				{
					
					System.out.println("::::INSIDE UPDATE:::::"+flag);
					frmdt=Security.TextDate_Insert(request.getParameter("fromdt"));
					todt=Security.TextDate_Insert(request.getParameter("todt"));
					updt=Security.TextDate_Insert(request.getParameter("updt"));
					
					ob=new IssueMasUpdatebean();
					ob.setFromdt(frmdt);
					ob.setTodt(todt);
					ob.setUpdt(updt);
					
					System.out.println(ob);
					
					int updateCount =ss.getDueDateCount(ob);
					System.out.println("Update Count:::"+updateCount);
					
//					int Count =ss.getDueDateUpdate(ob);
					request.setAttribute("bean", ob);
					request.setAttribute("fromdt", frmdt);
					System.out.println(request.getAttribute("bean"));
					if(updateCount>0)
					{
						request.setAttribute("updateCount", updateCount);
						indexPage ="/Issue_Mas_Update/index.jsp?check=updateCheck";
					}
					else
					{
						indexPage ="/Issue_Mas_Update/index.jsp?check=NoData";
					}
					dispatch(request, response, indexPage);
				}
				
				
				
				

				if(flag.equalsIgnoreCase("updateConfirm"))
				{
					
					System.out.println("::::INSIDE UPDATE:::::"+flag);
					frmdt=Security.TextDate_Insert(request.getParameter("fromdt"));
					todt=Security.TextDate_Insert(request.getParameter("todt"));
					updt=Security.TextDate_Insert(request.getParameter("updt"));
					
					ob=new IssueMasUpdatebean();
					ob.setFromdt(frmdt);
					ob.setTodt(todt);
					ob.setUpdt(updt);
					
					System.out.println(ob);
					
					int Count =ss.getDueDateUpdate(ob);
					request.setAttribute("bean", ob);
					System.out.println(request.getAttribute("bean"));
					if(Count>0)
					{
						request.setAttribute("updatedCount", Count);
						indexPage ="/Issue_Mas_Update/index.jsp?check=updated";
					}
					else
					{
						indexPage ="/Issue_Mas_Update/index.jsp?check=NoData";
					}
					dispatch(request, response, indexPage);
				}
				
				

					
			 } 
	       catch (Exception e) 
			 {
				e.printStackTrace();	
			 }
		    catch (Throwable theException)
            {
			
		    }
		    finally{
		    	try{
					
				}
		    	catch(Exception e)
				{
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
