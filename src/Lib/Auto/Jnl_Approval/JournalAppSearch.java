package Lib.Auto.Jnl_Approval;
import Common.Security;
import Lib.Auto.Jnl_Approval.Jnlapprovebean;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;


import javax.servlet.ServletException;
import javax.servlet.UnavailableException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.DriverManager;
import java.util.*;

public class JournalAppSearch extends HttpServlet implements Serializable {
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
String flag,f1,f2,f3;
String protocol="";
String nm;
String SQLString;
ArrayList ser=new ArrayList ();
Jnlapprovebean ob=new Jnlapprovebean();  
java.sql.Connection con=null;
java.sql.Statement st=null;
java.sql.ResultSet rs=null;
ResourceBundle rb =null;

public void init() throws ServletException
{
String driver=getServletContext().getInitParameter("driver");
protocol=getServletContext().getInitParameter("protocol");


	if(driver==null || protocol==null){
		throw new UnavailableException("Not Found");
	}


	try {
		rb = ResourceBundle.getBundle("LocalStrings");
	} catch (Exception e) {throw new ServletException(e);}

	try {
		
		Class.forName(driver);
		con =
			DriverManager.getConnection(
				protocol,
				rb.getString("Username"),
				rb.getString("Password"));
			} catch (Exception e) {
		throw new ServletException(e);
			
	}

}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) {

		performTask(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) {

		performTask(request, response);

	}
	
	public void performTask(
		HttpServletRequest request,
		HttpServletResponse response) {

		
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
	        
			//con=(java.sql.Connection)session.getAttribute("con");
		    nm=request.getParameter("name");
		    out.print("Name"+nm);
		    String value=request.getParameter("sflag");
		   if(value!=null)
		    {
		    	
		    }
		    else
		    {
		    	out.print("saravanan"+value);
		    }
		    if(value.equals("Journal"))
		    {

			SQLString ="select Jnl_Code,Jnl_Name,Jnl_Type from journal_mas where 2>1 and jnl_name like '" + nm + "%'  order by jnl_code";
  st=con.createStatement();
  rs = st.executeQuery(SQLString);
    while(rs.next()){

	   f1=rs.getString("Jnl_Code");
           f2=rs.getString("Jnl_Name");
           f3=rs.getString("Jnl_Type");


			ser.add(f1);
			ser.add(f2);
			ser.add(f3);
		    }
		ob.setAl(ser);
		request.setAttribute("bean",ob);
	 getServletConfig().getServletContext().getRequestDispatcher("/Journal_Approval/search.jsp?check=ok&flag="+value+"").forward(request, response);
	 }
		    if(value.equals("Dept"))
		    {

			SQLString ="select dept_Code,dept_Name,short_desc from department_mas where 2>1 and dept_name like '" + nm + "%'  order by dept_code";
  st=con.createStatement();
  rs = st.executeQuery(SQLString);
    while(rs.next()){

	   f1=rs.getString("Dept_Code");
           f2=rs.getString("dept_Name");
           f3=rs.getString("short_desc");


			ser.add(f1);
			ser.add(f2);
			ser.add(f3);
		    }
		ob.setAl(ser);
		request.setAttribute("bean",ob);
	 getServletConfig().getServletContext().getRequestDispatcher("/Journal_Approval/search.jsp?check=ok&flag="+value+"").forward(request, response);
	 }
	  				 
			 } catch (Exception e) {
					handleError(request, response, e);
					}
		    catch (Throwable theException) {
			handleError(request, response, theException);
		   }
	}
	
	public void handleError(
		HttpServletRequest request,
		HttpServletResponse response,
		Throwable e) {
		try {
			String message = e.getLocalizedMessage();
			String stackTrace = printStackToString(e);
			response.sendError(
				HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
				message + "<BR><BR><PRE>\n" + stackTrace + "</PRE>");
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	public static synchronized String printStackToString(Throwable aThrowable) {
		try {
			ByteArrayOutputStream stream = new ByteArrayOutputStream();
			PrintWriter aPrintWriter = new PrintWriter(stream, true);
			aThrowable.printStackTrace(aPrintWriter);
			aPrintWriter.flush();
			aPrintWriter.close();
			stream.flush();
			stream.close();
			return stream.toString();
		} catch (Throwable ex) {
			//** could not format Throwable as String. return simple toString()
			return aThrowable.toString();
		}
	}

	/** 
	 * Instance variable for SQL statement property
	 */
	

	
}


