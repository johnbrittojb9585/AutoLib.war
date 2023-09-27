package Lib.Auto.PubSup;
import Common.Security;
import Lib.Auto.PubSup.PubSupBean;
//import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
//import java.sql.DatabaseMetaData;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.UnavailableException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.DriverManager;
import java.util.*;



import Common.DBConnection;

public class SearchPubSup extends HttpServlet implements Serializable {
/**
	 * 
	 */
private static final long serialVersionUID = 1L;
String flag,t1,t2,t3;
String nm;
String SQLString,protocol;
ArrayList ser=new ArrayList ();
PubSupBean ob=new PubSupBean();

java.sql.Connection con=null;
java.sql.Statement st=null;
java.sql.ResultSet rs=null;
String nextPage = null;
ResourceBundle rb =null;

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException {

		performTask(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException {

		performTask(request, response);

	}
	
	public void performTask(
		HttpServletRequest request,
		HttpServletResponse response)throws ServletException {

		
		try {
			HttpSession session = request.getSession(true);
			String res = Security.checkSecurity(1, session, response, request);		
			if(res.equalsIgnoreCase("Failure"))
			{
				response.sendRedirect("/AutoLib/Tree/sessiontimeout.jsp");  
				return;
			}
			
			
			con=DBConnection.getInstance();
            PrintWriter out = response.getWriter();
	        			
		    nm=request.getParameter("name");
		    String type=request.getParameter("sflag");
		    out.print("Type="+type);
		    
		    if(type.equals("Sup"))
		    {
		    	
		    	if(request.getParameter("name").equals(""))
		    	{
		    		SQLString ="select sp_code,sp_name,short_desc from sup_pub_mas where 2>1 and sp_code between 1 and 100 and sp_type='SUPPLIER' order by sp_name";
		    	}
		    	else
		    	{
		    		SQLString ="select sp_code,sp_name,short_desc from sup_pub_mas where 2>1 and sp_name like '" +Security.getParam(request,"name") + "%'  and sp_type='SUPPLIER' order by sp_name";
		    	}

			
  st=con.createStatement();
  rs = st.executeQuery(SQLString);
    while(rs.next()){

	   		ser.add(rs.getString("Sp_Code"));
			ser.add(rs.getString("Sp_Name"));
			ser.add(rs.getString("Short_Desc"));

		    }
		ob.setAl(ser);
	 getServletConfig().getServletContext().getRequestDispatcher("/PubSup/search.jsp?check=ok&flag="+type+"&nam="+nm+"").forward(request, response);
	
	 }
	if(type.equals("Pub"))
	 {
		
		if(request.getParameter("name").equals(""))
    	{
			SQLString ="select sp_code,sp_name,short_desc from sup_pub_mas where 2>1 and sp_code between 1 and 100 and sp_type='PUBLISHER' order by sp_name";
    	}
    	else
    	{
    		SQLString ="select sp_code,sp_name,short_desc from sup_pub_mas where 2>1 and sp_name like '" + Security.getParam(request,"name") + "%'  and sp_type='PUBLISHER' order by sp_name";
    	}
	 
  st=con.createStatement();
  rs = st.executeQuery(SQLString);
    while(rs.next()){

    	ser.add(rs.getString("Sp_Code"));
		ser.add(rs.getString("Sp_Name"));
		ser.add(rs.getString("Short_Desc"));
		    }
		ob.setAl(ser);
	getServletConfig().getServletContext().getRequestDispatcher("/PubSup/search.jsp?check=ok&flag="+type+"&nam="+nm+"").forward(request, response);
		
	 }
				 
			 } catch (Exception sss) {throw new ServletException(sss);}
				
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
	
	public void dispatch(
			HttpServletRequest request,
			HttpServletResponse response,
			String nextPage)
			throws ServletException, IOException {
			RequestDispatcher dispatch = request.getRequestDispatcher(nextPage);
			dispatch.forward(request, response);
		}
}
