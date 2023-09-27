package Lib.Auto.Stock;

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

public class StockSearch extends HttpServlet {
	String protocol="",nm="",SQLString="",f1="",f2="",f3="";
	java.sql.Connection con = null;
	java.sql.PreparedStatement Prest = null;
	java.sql.Statement st=null;
	java.sql.ResultSet rs = null;
	ResourceBundle rb = ResourceBundle.getBundle("LocalStrings");
	
	

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
			    
			   if(value.equals("Find_Access"))
			    {
                 ArrayList list=null;
                 StockSearchBean ob=null;
                 out.print("i am");
	  SQLString ="select * from book_mas where 2>1 and title like ('" + nm + "%')";
	  Prest=con.prepareStatement(SQLString);
	  rs = Prest.executeQuery();
	  list=new ArrayList();
	    while(rs.next()){
               ob=new StockSearchBean();
		       ob.setAccess_no(rs.getString("access_no"));
	           ob.setTitle(rs.getString("title"));
	           ob.setAuthor(rs.getString("author_name"));
               list.add(ob);
			    }
	    request.setAttribute("search", list);
		 getServletConfig().getServletContext().getRequestDispatcher("/Stock/search_nmvc.jsp?check=ok&flag="+value+"").forward(request, response);
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
