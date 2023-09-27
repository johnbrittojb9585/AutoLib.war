package Lib.Auto.NoDues;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Common.DBConnection;
import Common.Security;
import Common.businessutil.BusinessServiceFactory;
import Common.businessutil.calaloging.CalalogingService;
import Lib.Auto.Member.MemberBean;

public class NoDuesSearch extends HttpServlet {
	
	String protocol="",nm="",SQLString="",f1="",f2="",f3="";
	
	String indexPage = null;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)  throws ServletException {

		performTask(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException{

		performTask(request, response);

	}
	public void performTask(
			HttpServletRequest request,
			HttpServletResponse response)  throws ServletException {
		   CalalogingService ss = BusinessServiceFactory.INSTANCE.getCalalogingService();

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
	           
	            nm=request.getParameter("name");
			    out.print("Name"+nm);
			    String value=request.getParameter("sflag");
			    out.print(value);
			    
			    MemberBean ob=new MemberBean();
			    ob=new MemberBean();
			    
			    if(value.equals("member"))
			    {
			    	List list = new ArrayList();
			    	ob=new MemberBean();
			    	ob.setName(nm);
			    	list=ss.getMemberMasSearch(ob);
			    	request.setAttribute("search", list);
				     indexPage = "/NoDues/search_nmvc.jsp?check=ok&flag="+value+"";
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
