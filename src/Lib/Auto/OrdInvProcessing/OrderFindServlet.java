package Lib.Auto.OrdInvProcessing;
import Lib.Auto.OrdInvProcessing.orderbean;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.sql.SQLException;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



import Common.DBConnection;
import Common.Security;


public class OrderFindServlet extends HttpServlet {
String flag,f1,f2,f3;
String protocol;
String nm;
String SQLStr="";
ArrayList ser=new ArrayList();
orderbean ob=new orderbean();

java.sql.Connection con=null;
java.sql.Statement st=null;
java.sql.ResultSet rs=null;


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
		con=DBConnection.getInstance();
        PrintWriter out = response.getWriter();
	    
		
		st=con.createStatement();
		nm=request.getParameter("name");
		String value=request.getParameter("sflag");

		   
	 if(value.equals("Dept"))
		    {
		 orderbean serchReport=null;
		 ArrayList searchRepList=new ArrayList();
		 String SQLStr ="select dept_code,dept_name,short_desc from Department_mas where 2>1 and dept_name ilike '" + nm + "%'   order by dept_code";
		 rs = st.executeQuery(SQLStr);
		 while(rs.next()){
			 serchReport.setIdcode(Integer.parseInt(rs.getString("dept_Code")));
			 serchReport.setIdname(rs.getString("dept_Name"));
			 serchReport.setShort_desc(rs.getString("Short_Desc"));
			 searchRepList.add(serchReport);
			    }
		 request.setAttribute("searchObjectOrder",searchRepList);
		 getServletConfig().getServletContext().getRequestDispatcher("/OrdInvProcessing/search.jsp?SearchFlag=dept&name="+nm+"").forward(request, response); 

	 }

 
     
			 } catch (Exception e) {

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


	}



