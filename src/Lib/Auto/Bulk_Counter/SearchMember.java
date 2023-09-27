package Lib.Auto.Bulk_Counter;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Common.Security;
import Common.businessutil.BusinessServiceFactory;
import Common.businessutil.calaloging.CalalogingService;
import Lib.Auto.Member.*;


public class SearchMember extends HttpServlet implements Serializable {
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
String flag,f1,f2,f3;
String protocol="";
String nm;
String SQLString;
String indexPage = null;
ArrayList ser=new ArrayList ();

	
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
			
			response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            CalalogingService ss = BusinessServiceFactory.INSTANCE.getCalalogingService();

            nm=request.getParameter("name");
		    String value=request.getParameter("sflag");
		    
            
		    if(value.equals("Member"))
		    {
		    	List MemberArrylist = new ArrayList();
		    	MemberBean ob=new MemberBean();
		    	ob.setName(nm);
		    	MemberArrylist=ss.getMemberMasSearch(ob);
				request.setAttribute("serarch", MemberArrylist);
			     indexPage = "/Bulk_Counter/search.jsp?check=ok&flag="+value+"&nam="+nm+"";
				dispatch(request, response, indexPage);		    
		    }
	  
	
			 } catch (Exception sss) {
					throw new ServletException(sss);
				}
	}
		
	

	/** 
	 * Instance variable for SQL statement property
	 */
	public void dispatch(HttpServletRequest request,
			HttpServletResponse response, String indexPage)
			throws ServletException, IOException {
		RequestDispatcher dispatch = request.getRequestDispatcher(indexPage);
		dispatch.forward(request, response);
	}

	
}
