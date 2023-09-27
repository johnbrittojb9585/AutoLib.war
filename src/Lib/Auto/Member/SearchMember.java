package Lib.Auto.Member;
import java.io.ByteArrayOutputStream;
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

//import Common.DBConnection;

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
MemberBean ob=new MemberBean();  
MemberBean newbean=new MemberBean(); 

	
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
//            con=DBConnection.getInstance();
	        
			//con=(java.sql.Connection)session.getAttribute("con");
            
            
		    nm=request.getParameter("name");
		    String value=request.getParameter("sflag");
		    
            
		    if(value.equals("Member"))
		    {
		    	List MemberArrylist = new ArrayList();
		    	ob=new MemberBean();
		    	ob.setName(nm);
		    	MemberArrylist=ss.getMemberMasSearch(ob);
				request.setAttribute("serarch", MemberArrylist);
			     indexPage = "/Member/search.jsp?check=ok&flag="+value+"&nam="+nm+"";
				dispatch(request, response, indexPage);		    
		    }
	  if(value.equals("Department"))
		    {		    
		  List DeptArrylist = new ArrayList();	
	    	ob.setName(nm);
	    	DeptArrylist=ss.getDeptMasSearch(ob);	 	
	    	 request.setAttribute("serarch", DeptArrylist);
		     indexPage = "/Member/search.jsp?check=ok&flag="+value+"&nam="+nm+"";
			dispatch(request, response, indexPage);		   
		 }
	  
	  
	  if(value.equals("Desig"))
	  {
		 
		  List DesigArrylist = new ArrayList();
	    	ob=new MemberBean();
	    	ob.setName(nm);
	    	DesigArrylist=ss.getDesignationSearch(ob);
			request.setAttribute("serarch", DesigArrylist);
		     indexPage = "/Member/search.jsp?check=ok&flag="+value+"&nam="+nm+"";
			dispatch(request, response, indexPage);		
	  }
	
	  if(value.equals("Group")){
		  List GroupArrylist = new ArrayList();
	    	ob=new MemberBean();
	    	ob.setName(nm);
	    	GroupArrylist=ss.getGroupSearch(ob);
			request.setAttribute("serarch", GroupArrylist);
		     indexPage = "/Member/search.jsp?check=ok&flag="+value+"&nam="+nm+"";
			dispatch(request, response, indexPage);	
	  }
	  
	  if(value.equals("Course"))
	  {
		  List CourseArrylist = new ArrayList();
	    	ob=new MemberBean();
	    	ob.setName(nm);
	    	CourseArrylist=ss.getCourseSearch(ob);
			request.setAttribute("serarch", CourseArrylist);
		     indexPage = "/Member/search.jsp?check=ok&flag="+value+"&nam="+nm+"";
			dispatch(request, response, indexPage);	
	  }
	  
	  
	  if(value.equals("City"))
	  {
		  List CityArrylist = new ArrayList();
	    	ob=new MemberBean();
	    	ob.setName(nm);
	    	CityArrylist=ss.getCitySearch(ob);
			request.setAttribute("serarch", CityArrylist);
		     indexPage = "/Member/search.jsp?check=ok&flag="+value+"&nam="+nm+"";
			dispatch(request, response, indexPage);	
	  }
	  
	  if(value.equals("Branch")){
	    		    	          
           List BranchArrylist = new ArrayList();
           ob=new MemberBean();
	       ob.setName(nm);
	       BranchArrylist=ss.getBookSearchBranch(nm);
           request.setAttribute("serarch", BranchArrylist);
           indexPage = "/Member/search.jsp?check=ok&flag="+value+"&nam="+nm+"";
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
