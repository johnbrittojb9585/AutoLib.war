package Lib.Auto.Budget;
import Common.Security;
import Lib.Auto.Budget.BudgetBean;
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



public class SearchBudget extends HttpServlet implements Serializable {
/**
	 * 
	 */
private static final long serialVersionUID = 1L;
String flag,t1,t2,t3;
String nm;
String SQLString,protocol;
ArrayList ser=new ArrayList ();
BudgetBean ob=new BudgetBean();

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
		   
		    if(type.equals("dcode"))
		    {
		    
			SQLString ="select * from department_mas where 2>1 and dept_name like '" +Security.getParam(request,"name") + "%'   order by dept_name";
			out.print(SQLString);
  st=con.createStatement();
  rs = st.executeQuery(SQLString);
    while(rs.next()){
    		ser.add(rs.getString("dept_name"));
			ser.add(rs.getString("dept_code"));
			ser.add(rs.getString("Short_Desc"));
			
		    }
		ob.setAl(ser);
	 getServletConfig().getServletContext().getRequestDispatcher("/Budget/search.jsp?check=ok&flag="+type+"&nam="+nm+"").forward(request, response);
	
	 }
	if(type.equals("bcode"))
	 {
	 SQLString ="select bud_code,bud_head,bud_amount from budget_mas where 2>1 and bud_head like '" + Security.getParam(request,"name") + "%' order by bud_head";
  st=con.createStatement();
  rs = st.executeQuery(SQLString);
    while(rs.next()){

    	ser.add(rs.getString("bud_code"));
		ser.add(rs.getString("bud_head"));
		ser.add(rs.getString("bud_amount"));
		    }
		ob.setAl(ser);
	getServletConfig().getServletContext().getRequestDispatcher("/Budget/search.jsp?check=ok&flag="+type+"&nam="+nm+"").forward(request, response);
		
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

