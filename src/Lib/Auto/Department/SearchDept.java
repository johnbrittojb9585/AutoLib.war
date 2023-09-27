package Lib.Auto.Department;
import java.io.IOException;
import java.io.Serializable;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.UnavailableException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Common.Security;
import Common.businessutil.BusinessServiceFactory;
import Common.businessutil.calaloging.CalalogingService;
//import Common.DBConnection;

public class SearchDept extends HttpServlet implements Serializable {


/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
String flag;
String nam="";
String SQLString,protocol="";
ArrayList ser=new ArrayList ();
DepartmentBean ob=new DepartmentBean();
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
//			st = con.createStatement();
			HttpSession session = request.getSession(true);
			String res = Security.checkSecurity(1, session, response, request);		
			if(res.equalsIgnoreCase("Failure"))
			{
				response.sendRedirect("/AutoLib/Tree/sessiontimeout.jsp");  
				return;
			}
			
			nam=request.getParameter("name");
			CalalogingService ss = BusinessServiceFactory.INSTANCE.getCalalogingService();
			List DepartmentArrylist = null;
			DepartmentBean newbean = null;
			DepartmentBean newauthor=null;
			
			DepartmentArrylist = new ArrayList();
			ob = new DepartmentBean();
			ob.setName(request.getParameter("name"));				
			DepartmentArrylist=ss.getDeptSearchName(ob);
	
		   nextPage="/Department/search.jsp?check=ok&nam="+nam+"";
		    dispatch(request, response, nextPage);
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
