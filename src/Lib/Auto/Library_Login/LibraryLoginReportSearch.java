package Lib.Auto.Library_Login;

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

import Common.Security;
import Common.businessutil.BusinessServiceFactory;
import Common.businessutil.calaloging.CalalogingService;
import Lib.Auto.Member.MemberBean;

public class LibraryLoginReportSearch extends HttpServlet implements Serializable {
/**
	 * 
	 */
private static final long serialVersionUID = 1L;

String nm;
String SQLString;
String indexPage = null;
ArrayList ser=new ArrayList ();
MemberBean ob=new MemberBean();  

	
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
            CalalogingService ss = BusinessServiceFactory.INSTANCE.getCalalogingService();
            
		    nm=request.getParameter("name");
		    String value=request.getParameter("sflag");		    

			if(value.equals("Department")){
				  List DeptArrylist = new ArrayList();
			    	ob=new MemberBean();
			    	ob.setName(nm);
			    	DeptArrylist=ss.getDeptMasSearch(ob);
					request.setAttribute("serarch", DeptArrylist);
				     indexPage = "/Library_Login/search.jsp?check=ok&flag="+value+"&nam="+nm+"";
					dispatch(request, response, indexPage);	
			}		
			
			if(value.equals("Group")){
				  List GroupArrylist = new ArrayList();
			    	ob=new MemberBean();
			    	ob.setName(nm);
			    	GroupArrylist=ss.getGroupSearch(ob);
					request.setAttribute("serarch", GroupArrylist);
					indexPage = "/Library_Login/search.jsp?check=ok&flag="+value+"&nam="+nm+"";
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
