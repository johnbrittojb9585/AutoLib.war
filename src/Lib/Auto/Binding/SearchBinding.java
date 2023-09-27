package Lib.Auto.Binding;
import Common.Security;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
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


import Common.DBConnection;

public class SearchBinding extends HttpServlet implements Serializable {
/**
	 * 
	 * 
	 * 
	 */
private static final long serialVersionUID = 1L;
String flag,t1,t2,t3;
String nm;
String SQLString,protocol;
ArrayList ser=new ArrayList ();

BindingBean ob=new BindingBean();

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
			
			
            PrintWriter out = response.getWriter();
            con=DBConnection.getInstance();
	        			
		    nm=request.getParameter("name");
		    String type=request.getParameter("sflag");
		    out.print("Type="+type);
		    

		    if(type.equals("Bind"))
		    {
		    	
		    	if(request.getParameter("name").equals(""))
		    	{
		    		SQLString ="select binder_code,binder_name,short_desc from binder_mas where 2>1 and binder_code between 1 and 100  order by binder_name";
		    	}
		    	else
		    	{
		    		SQLString ="select binder_code,binder_name,short_desc from binder_mas where 2>1 and binder_name like '" +Security.getParam(request,"name") + "%'  order by binder_name";
		    	}

			
  st=con.createStatement();
  rs = st.executeQuery(SQLString);
    while(rs.next()){

	   		ser.add(rs.getString("binder_Code"));
			ser.add(rs.getString("binder_Name"));
			ser.add(rs.getString("Short_Desc"));
			
		    }
		ob.setAl(ser);
		
	 getServletConfig().getServletContext().getRequestDispatcher("/Binding/search.jsp?check=ok&flag="+type+"&nam="+nm+"").forward(request, response);
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

