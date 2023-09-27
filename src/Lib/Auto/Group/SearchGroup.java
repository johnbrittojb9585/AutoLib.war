package Lib.Auto.Group;
import Lib.Auto.Group.GroupBean;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
//import java.sql.DatabaseMetaData;
import java.sql.DriverManager;

import javax.servlet.ServletException;
import javax.servlet.UnavailableException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;


import Common.DBConnection;
import Common.Security;

public class SearchGroup extends HttpServlet implements Serializable {
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
String flag,t1,t2,t3;
String nm;
String SQLString;
String protocol="";
ArrayList ser=new ArrayList ();
GroupBean ob=new GroupBean();
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
	
	public synchronized void performTask(
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
			con=DBConnection.getInstance();
            
		    nm=code.getParam( request, "name");
		    String fl=request.getParameter("sflag");

		    if(fl.equals("Gen"))
		    {

			SQLString ="select group_code,group_name,remarks from group_mas where 2>1 and  group_name like '" + nm + "%' and status='V1' order by group_code";
  st=con.createStatement();
  rs = st.executeQuery(SQLString);
    while(rs.next()){

	   String f1=rs.getString("group_Code");
           String f2=rs.getString("group_Name");
           String f3=rs.getString("remarks");

			ser.add(f1);
			ser.add(f2);
			ser.add(f3);
		    }
		ob.setAl(ser);
	 getServletConfig().getServletContext().getRequestDispatcher("/Group/search.jsp?check=ok&flag="+fl+"").forward(request, response);
	 }
	 if(fl.equals("Spe"))
	 {
	 SQLString ="select group_code,group_name,remarks from group_mas where 2>1 and group_name like '" + nm + "%' and status='V2' order by group_code";
  st=con.createStatement();
  rs = st.executeQuery(SQLString);
    while(rs.next()){

	   String f1=rs.getString("group_Code");
           String f2=rs.getString("group_Name");
           String f3=rs.getString("remarks");

			ser.add(f1);
			ser.add(f2);
			ser.add(f3);
		    }
		ob.setAl(ser);
	 getServletConfig().getServletContext().getRequestDispatcher("/Group/search.jsp?check=ok&flag="+fl+"").forward(request, response);
	 }
	 if(fl.equals("Gs"))
	 {
	 SQLString ="select group_code,group_name,remarks from group_mas where 2>1 and group_name like '" + nm + "%' and status='V3' order by group_code";
  st=con.createStatement();
  rs = st.executeQuery(SQLString);
    while(rs.next()){

	   String f1=rs.getString("group_Code");
           String f2=rs.getString("group_Name");
           String f3=rs.getString("remarks");

			ser.add(f1);
			ser.add(f2);
			ser.add(f3);
		    }
		ob.setAl(ser);
	 getServletConfig().getServletContext().getRequestDispatcher("/Group/search.jsp?check=ok&flag="+fl+"").forward(request, response);
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
