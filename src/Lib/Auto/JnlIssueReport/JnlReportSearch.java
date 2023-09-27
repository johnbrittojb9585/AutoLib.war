package Lib.Auto.JnlIssueReport;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.UnavailableException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Common.DataQuery;
import Common.Security;
import Common.DBConnection;
import Common.businessutil.BusinessServiceFactory;
import Common.businessutil.reports.ReportService;
import Lib.Auto.JournalList.JournalListBean;
import Lib.Auto.MemberReport.MemberReportBean;


public class JnlReportSearch extends HttpServlet {
	String protocol="",nm="",SQLString="",f1="",f2="",f3="",indexPage="";
	java.sql.Connection con = null;
	java.sql.PreparedStatement Prest = null;
	java.sql.Statement st=null;
	java.sql.ResultSet rs = null;
	ResourceBundle rb = ResourceBundle.getBundle("LocalStrings");
	
	JnlIssueReportBean ob=null;
	JournalListBean ob1 = null;
	

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
	            con=DBConnection.getInstance();
	            nm=request.getParameter("name");
			    out.print("Name"+nm);
			    String value=request.getParameter("sflag");
			    out.print(value);
			    ReportService ss = BusinessServiceFactory.INSTANCE.getReportService();
			    if(value.equals("jnlfind"))
			    {
			    	List MemberArrylist = new ArrayList();
			    	ob=new JnlIssueReportBean();
			    	ob.setJname(nm);
			    	MemberArrylist=ss.getJnlNameSearch(ob);
					request.setAttribute("search", MemberArrylist);
				     indexPage = "/JnlIssueReport/search_nmvc.jsp?check=ok&flag="+value+"&nam="+nm+"";
					dispatch(request, response, indexPage);		    
			    }
			    if(value.equals("deptfind"))
			    {
			    	List MemberArrylist = new ArrayList();
			    	ob1=new JournalListBean();
			    	ob1.setDname(nm);
			    	MemberArrylist=ss.getDeptJnlSearch(ob1);
					request.setAttribute("search", MemberArrylist);
				     indexPage = "/JnlIssueReport/search_nmvc.jsp?check=ok&flag="+value+"&nam="+nm+"";
					dispatch(request, response, indexPage);		    
			    }
			    
			    if(value.equals("pubfind"))
			    {
			    	List MemberArrylist = new ArrayList();
			    	ob1=new JournalListBean();
			    	ob1.setDname(nm);
			    	MemberArrylist=ss.getPubJnlSearch(ob1);
					request.setAttribute("search", MemberArrylist);
				     indexPage = "/JnlIssueReport/search_nmvc.jsp?check=ok&flag="+value+"&nam="+nm+"";
					dispatch(request, response, indexPage);		    
			    }
			
	   
			}  catch (Exception e) {

		}
	catch (Throwable theException) {

	}
	finally{
		try{
			if(rs!=null){
				rs.close();
				rs=null;
			}
			if(Prest!=null){
				Prest.close();
				Prest=null;
			}

		}catch(Exception e){
		e.printStackTrace();
		}
	}

		

		}
		
		public void dispatch(
				HttpServletRequest request,
				HttpServletResponse response,
				String indexPage)
				throws ServletException, IOException {
			   // response.sendRedirect(indexPage);
				RequestDispatcher dispatch = request.getRequestDispatcher(indexPage);
				dispatch.forward(request, response);
			}

}
