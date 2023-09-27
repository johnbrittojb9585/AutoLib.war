package Lib.Auto.Book;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
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

public class BookSearchInsert extends HttpServlet {
	String protocol="",nm="",SQLString="",f1="",f2="",f3="";
	java.sql.Connection con = null;
	java.sql.PreparedStatement Prest = null;

	java.sql.ResultSet rs = null;
	ResourceBundle rb = ResourceBundle.getBundle("LocalStrings");
	
	public static final String SELECT_TITLE="select * from book_mas where title=?";
	public static final String SELECT_AUTHOR="select * from author_mas where author_name=?";
	public static final String SELECT_PUB="select * from sup_pub_mas where SP_name=? and sp_type='PUBLISHER'";
	public static final String SELECT_SUB="select * from subject_mas where sub_name=?";
	public static final String SELECT_BRANCH="select * from branch_mas where branch_name?";
	public static final String SELECT_DEPT="select * from department_mas where Dept_name=?";
	public static final String SELECT_BUD="select * from budget_mas where bud_head=?";
	public static final String SELECT_GROUP="select * from group_mas where Group_name=?";
	public static final String SELECT_KEY="select * from keywords_mas where keyword_name=?";
	public static final String SELECT_SUP="select * from sup_pub_mas where SP_name=? and sp_type='SUPPLIER'";
	
	


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
			   // String kar=request.getParameter("iflag");
			    
			    if(request.getParameter("value").equals("Sub")){
			    	
			    	
			    }
			   
	   
			}  catch (Exception e) {

		}
	catch (Throwable theException) {

	}
	finally
	{
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
