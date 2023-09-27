package Lib.Auto.Fine;
import Common.Security;
import Lib.Auto.Fine.Finebean;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
//import java.sql.DatabaseMetaData;

import javax.servlet.ServletException;
import javax.servlet.UnavailableException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
//54import javax.servlet.http.HttpSession;

import java.sql.DriverManager;
import java.util.*;


import Common.DBConnection;


public class SearchFine extends HttpServlet implements Serializable {
/**
	 * 
	 */
private static final long serialVersionUID = 1L;
String flag,t1,t2,t3;
String nm;
String SQLString;
ArrayList ser=new ArrayList ();
Finebean ob=new Finebean();
singlecodecheck code=new singlecodecheck();
java.sql.Connection con=null;
java.sql.Statement st=null;
java.sql.ResultSet rs=null;
ResourceBundle rb =null;



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
            con=DBConnection.getInstance();
			String type=request.getParameter("sflag");
		    out.print("Type="+type);
	       
			
		    nm=code.getParam( request, "name");
		    if(type.equals("Fine"))
		    {
			SQLString ="select * from fine_mas where 2>1 and Fine_id like '" + nm + "%' order by Fine_id";
  st=con.createStatement();
  rs = st.executeQuery(SQLString);
    while(rs.next()){
             
		   String f1=rs.getString("fine_id");
           String f2=rs.getString("group_code");
           String f3=rs.getString("fine_amount");
		    ser.add(f1);
			ser.add(f2);
			ser.add(f3);
		    }
		ob.setAl(ser);
	 getServletConfig().getServletContext().getRequestDispatcher("/Fine/search.jsp?check=ok&flag="+type+"&nam="+nm+"").forward(request, response);
		    }
		    if(type.equals("Group"))
			 {
			 SQLString ="select group_code,group_name,remarks from group_mas where 2>1 and Group_name like '" + nm + "%' order by group_name";
		  st=con.createStatement();
		  rs = st.executeQuery(SQLString);
		    while(rs.next()){

		    	
				
				String f1=rs.getString("group_code");
				String f2=rs.getString("group_name");
				String f3=rs.getString("remarks");

					ser.add(f2);
					ser.add(f1);
					ser.add(f3);
				    }
				ob.setAl(ser);
			getServletConfig().getServletContext().getRequestDispatcher("/Fine/search.jsp?check=ok&flag="+type+"&nam="+nm+"").forward(request, response);
				
			 }
			 } catch (Exception e) {
					handleError(request, response, e);
					}
		    catch (Throwable theException) {
			handleError(request, response, theException);
		   }
		    finally{
		    	try{
					if(rs!=null){
						rs.close();
						rs=null;
					}
					if(st!=null){
						st.close();
						st=null;
					}

				}catch(Exception e){
				e.printStackTrace();
				}
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
			
			return aThrowable.toString();
		}
	}

	/** 
	 * Instance variable for SQL statement property
	 */
	

	
}
