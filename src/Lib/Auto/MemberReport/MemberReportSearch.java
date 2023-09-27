package Lib.Auto.MemberReport;

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

import Common.Security;
import Common.businessutil.BusinessServiceFactory;
import Common.businessutil.reports.ReportService;

public class MemberReportSearch extends HttpServlet {
	String protocol="",nm="",SQLString="",f1="",f2="",f3="",indexPage="";
	java.sql.Connection con = null;
	java.sql.PreparedStatement Prest = null;
	java.sql.Statement st=null;
	java.sql.ResultSet rs = null;
	ResourceBundle rb = ResourceBundle.getBundle("LocalStrings");
	MemberReportBean ob=null;
	
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
	            PrintWriter out = response.getWriter();
	           // con=DBConnection.getInstance();
	            nm=request.getParameter("name");
			    out.print("Name"+nm);
			    String value=request.getParameter("sflag");
			    out.print(value);
			    ReportService ss = BusinessServiceFactory.INSTANCE.getReportService();
			    
			    if(value.equals("name"))
			    {
			    	List MemberArrylist = new ArrayList();
			    	ob=new MemberReportBean();
			    	ob.setMname(nm);
			    	MemberArrylist=ss.getMemberSearch(ob);
					request.setAttribute("search", MemberArrylist);
				     indexPage = "/MemberReport/search_nmvc.jsp?check=ok&flag="+value+"&nam="+nm+"";
					dispatch(request, response, indexPage);		    
			    }
			    
			    if(value.equals("dept"))
			    {
			    	List MemberArrylist = new ArrayList();
			    	ob=new MemberReportBean();
			    	ob.setMname(nm);
			    	MemberArrylist=ss.getDeptSearch(ob);
					request.setAttribute("search", MemberArrylist);
				     indexPage = "/MemberReport/search_nmvc.jsp?check=ok&flag="+value+"&nam="+nm+"";
					dispatch(request, response, indexPage);		    
			    }
			    
			    if(value.equals("group"))
			    {
			    	List MemberArrylist = new ArrayList();
			    	ob=new MemberReportBean();
			    	ob.setMname(nm);
			    	MemberArrylist=ss.getGroupSearch(ob);
					request.setAttribute("search", MemberArrylist);
				     indexPage = "/MemberReport/search_nmvc.jsp?check=ok&flag="+value+"&nam="+nm+"";
					dispatch(request, response, indexPage);		    
			    }
			    
			    if(value.equals("course"))
			    {
			    	List MemberArrylist = new ArrayList();
			    	ob=new MemberReportBean();
			    	ob.setMname(nm);
			    	MemberArrylist=ss.getCourseSearch(ob);
					request.setAttribute("search", MemberArrylist);
				     indexPage = "/MemberReport/search_nmvc.jsp?check=ok&flag="+value+"&nam="+nm+"";
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
