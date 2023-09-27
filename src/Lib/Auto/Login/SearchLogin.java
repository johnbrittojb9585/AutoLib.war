package Lib.Auto.Login;
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

public class SearchLogin extends HttpServlet implements Serializable {
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
String flag,protocol="";
String nm;
String SQLString="",f3="";

ArrayList ser=new ArrayList ();
LoginBean ob=new LoginBean();
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
			 
  			rs = st.executeQuery("select * from login_mas where 2>1 and login_id like '" +Security.getParam(request,"name") + "%' order by staff_name");
   		    while(rs.next()){
             
	   		ser.add(rs.getString("login_id"));
			ser.add(rs.getString("staff_name"));
			int right=Integer.parseInt(rs.getString("user_rights"));
			switch(right)
			{
			case 1:
			f3="ADMIN-I";
			break;
			case 2:
			f3="ADMIN-II";
			break;
			case 3:
			f3="DATA ENTRY";
			break;
			case 4:
			f3="JOURNAL";
			break;
			case 5:
			f3="ACQUISITION";
			break;
			case 6:
			f3="COUNTER";
			break;
			case 7:
			f3="PUBLIC";
			break;
			}
			ser.add(f3);
		    }
		ob.setAl(ser);
		nextPage="/Login/search.jsp?check=ok&nam="+nm+"";
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

