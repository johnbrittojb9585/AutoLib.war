package Lib.Auto.FreqUsedMember;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.util.ResourceBundle;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.UnavailableException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import Common.DBConnection;
import Common.Security;
import Lib.Auto.FreqUsedResource.FreqUsedBean;

public class FreqUsedMIndex extends HttpServlet{

	/**
	 * 
	 * 
	 */
	private static final long serialVersionUID = 1L;
	java.sql.Connection con=null;
	ResourceBundle rb = ResourceBundle.getBundle("LocalStrings");
	java.sql.Statement st=null;

	java.sql.ResultSet rs_Dept=null;
	
	java.sql.PreparedStatement pstmt=null;
	String indexPage = null,protocol;
	FreqUsedMBean BeanOb=new FreqUsedMBean();
	
	protected static final String DEPT_STRING ="select * from department_mas";
	
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
                             
		            pstmt = con.prepareStatement(DEPT_STRING);
					rs_Dept = pstmt.executeQuery();
			        BeanOb.setDept_al(rs_Dept);
			               
			indexPage = "/FrequentlyUsedMember/index.jsp";
			dispatch(request, response, indexPage);
            
            
		}catch(Exception e){}
		finally{
			try{
				if(rs_Dept!=null){
					rs_Dept.close();
					rs_Dept=null;
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
		String indexPage)
		throws ServletException, IOException {
		RequestDispatcher dispatch = request.getRequestDispatcher(indexPage);
		dispatch.forward(request, response);
	}
}