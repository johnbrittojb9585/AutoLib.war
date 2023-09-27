package Lib.Auto.Course;
import java.io.IOException;
import java.io.Serializable;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.UnavailableException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Common.Security;


import Common.DBConnection;

public class SearchCourse extends HttpServlet implements Serializable {
String flag,protocol="";
String nm;

String SQLString;
ArrayList ser=new ArrayList ();
CourseBean ob=new CourseBean();
java.sql.Connection con=null;
ResourceBundle rb =null;
java.sql.Statement st=null;
java.sql.ResultSet rs=null;
String nextPage = null;


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
			nm=Security.getParam(request,"name");
			st = con.createStatement();
			 
  			rs = st.executeQuery("select * from course_mas where 2>1 and Course_name like '" +Security.getParam(request,"name") + "%' order by Course_code");
   		    while(rs.next()){
             
	   		ser.add(rs.getString("Course_Code"));
			ser.add(rs.getString("Course_Name"));
			ser.add(rs.getString("Short_Desc"));
		    }
		ob.setAl(ser);
		nextPage="/Course/search.jsp?check=ok&nam="+nm+"";
			   dispatch(request, response, nextPage);
				 
			 }  catch (Exception sss) {throw new ServletException(sss);}
					
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
