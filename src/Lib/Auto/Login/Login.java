package Lib.Auto.Login;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;
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
import Lib.Auto.Author.AuthorBean;
import Lib.Auto.Fine.Fine;
import Lib.Auto.Fine.Finebean;


import Common.businessutil.BusinessServiceFactory;
import Common.businessutil.admin.AdminService;

public class Login extends HttpServlet implements Serializable {
	 private static Logger log4jLogger = Logger.getLogger(Login.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String flag;
	String name="",code="",protocol="",Log_id="",Log_pass="";
	int Course_Mas_code,Coursecode;
	int updateFlag;
	LoginBean ob=new LoginBean();
	
	   String indexPage = null;
	 

	   
	public void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException {

		performTask(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException {

		performTask(request, response);

	}
	public void performTask(
		HttpServletRequest request,
		HttpServletResponse response)throws ServletException {

		
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
			flag = request.getParameter("flag");
			String nm=Security.getParam(request,"name");
			//out.print(session);
		
			if(flag.equals("new")){
				indexPage = "/Login/index.jsp";			
			dispatch(request, response, indexPage);
			}
			
		if(flag.equals("search")){
			
			log4jLogger.info("start===========search====="+request.getParameter("code"));
			ob=new LoginBean();
			ob=ss.getLoginSearch(request.getParameter("code"));
			if(ob!=null){
				request.setAttribute("bean", ob);
				
				indexPage = "/Login/index.jsp?check=searchlogin";	
			}else{
				indexPage = "/Login/index.jsp?check=FailLogin";
			}
			dispatch(request, response, indexPage);
			
			
		}
		if(flag.equals("update")){
			
			log4jLogger.info("start===========search====="+request.getParameter("code"));
			ob=new LoginBean();
			ob.setCode(request.getParameter("code"));
			ob.setName(request.getParameter("name"));
			ob.setPass(request.getParameter("pass"));
			ob.setRights(request.getParameter("rights"));
			ob.setFlag(request.getParameter("flag1"));
			
			int count=ss.getLoginUpdate(ob);
			indexPage = "/Login/index.jsp?check=UpdateLogin";
			dispatch(request, response, indexPage);
		}
		
		
		if(flag.equals("delete")){
			log4jLogger.info("start===========delete====="+request.getParameter("code"));
			ob=new LoginBean();
			ob=ss.getLoginSearch(request.getParameter("code"));
			if(ob!=null){
				request.setAttribute("bean", ob);
				int count=ss.getLoginDelete(request.getParameter("code"));
				indexPage = "/Login/index.jsp?check=DeleteLogin";	
			}else{
				indexPage = "/Login/index.jsp?check=FailLogin";
			}
			
			
			dispatch(request, response, indexPage);
		}
		
		if(flag.equals("save")){
			log4jLogger.info("start===========save====="+request.getParameter("code"));
			ob=new LoginBean();
			ob.setCode(request.getParameter("code"));
			ob.setName(request.getParameter("name"));
			ob.setPass(request.getParameter("pass"));
			ob.setRights(request.getParameter("rights"));
			ob.setFlag(request.getParameter("flag1"));
			
			ob=ss.getLoginSearch(request.getParameter("code"));
			
		
			if(ob!=null){
				ob.setCode(request.getParameter("code"));
				ob.setName(request.getParameter("name"));
				ob.setPass(request.getParameter("pass"));
				ob.setRights(request.getParameter("rights"));
				ob.setFlag(request.getParameter("flag1"));
				
				request.setAttribute("bean", ob);
			
				indexPage = "/Login/index.jsp?check=UpdateCheck";	
			}else{

				ob=new LoginBean();
				ob.setCode(request.getParameter("code"));
				ob.setName(request.getParameter("name"));
				ob.setPass(request.getParameter("pass"));
				ob.setRights(request.getParameter("rights"));
				ob.setFlag(request.getParameter("flag1"));
				
				int count=ss.getLoginSave(ob);
				indexPage = "/Login/index.jsp?check=SaveLogin";
			}
			dispatch(request, response, indexPage);
		}
		
		if (flag.equals("Login")) {
			ob=new LoginBean();
				
	if(request.getParameter("name")!=null){
		
		ob=ss.getLoginSearchName(request.getParameter("name"));
		request.setAttribute("bean", ob);
        indexPage = "/Login/search.jsp?check=ok&nam="+nm+"";
    	dispatch(request, response, indexPage);
	}
	

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

