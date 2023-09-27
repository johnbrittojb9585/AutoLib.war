package Lib.Auto.Journal_Artical;
import Lib.Auto.Journal.journalbean;
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


import Common.DBConnection;
import Common.Security;

public class SearchJournalArt extends HttpServlet implements Serializable {
   /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
String flag,f1,f2,f3;
String protocol="";
String nm;
String SQLString;
ArrayList ser=new ArrayList ();
journalArtbean ob=new journalArtbean();  
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
		    if(value.equals("Nam"))
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
	 getServletConfig().getServletContext().getRequestDispatcher("/Journal_Artical/search.jsp?check=ok&flag="+value+"&nam="+nm+"").forward(request, response);
	 }
		    
		    if(value.equals("Atl"))
		    {

			SQLString ="select atl_no,atl_title,atl_author from journal_articles where 2>1 and atl_title like '" + nm + "%'  order by atl_no";
  st=con.createStatement();
  rs = st.executeQuery(SQLString);
    while(rs.next()){

	   f1=rs.getString("atl_no");
           f2=rs.getString("atl_title");
           f3=rs.getString("atl_author");


			ser.add(f1);
			ser.add(f2);
			ser.add(f3);
		    }
		ob.setAl(ser);
	 getServletConfig().getServletContext().getRequestDispatcher("/Journal_Artical/search.jsp?check=ok&flag="+value+"&nam="+nm+"").forward(request, response);
	 }
		    
		    
	
	  if(value.equals("Sub"))
		    {

			SQLString ="select sub_code,sub_name,short_desc from subject_mas where 2>1 and sub_name like '" + nm + "%'  order by sub_code";
  st=con.createStatement();
  rs = st.executeQuery(SQLString);
    while(rs.next()){

	   f1=rs.getString("sub_Code");
           f2=rs.getString("sub_Name");
           f3=rs.getString("short_desc");

			ser.add(f2);
			ser.add(f1);
			ser.add(f3);
		    }
		ob.setAl(ser);
	 getServletConfig().getServletContext().getRequestDispatcher("/Journal_Artical/search.jsp?check=ok&flag="+value+"&nam="+nm+"").forward(request, response);
	 }
	
				 
			 } catch (Exception e) {
					handleError(request, response, e);
					}
		    catch (Throwable theException) {
			handleError(request, response, theException);
		   }
		    finally
		    {
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
			//** could not format Throwable as String. return simple toString()
			return aThrowable.toString();
		}
	}

	/** 
	 * Instance variable for SQL statement property
	 */
	

	
}

