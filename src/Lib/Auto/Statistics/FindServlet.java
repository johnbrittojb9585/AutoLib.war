package Lib.Auto.Statistics;
import Lib.Auto.Statistics.statbean;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;

import javax.servlet.ServletException;
import javax.servlet.UnavailableException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.library.autolib.portal.dbconnectionutil.SessionHibernateUtil;

import java.util.*;



import Common.DBConnection;
import Common.Security;
import Common.businessutil.BusinessServiceFactory;
import Common.businessutil.reports.ReportService;

public class FindServlet extends HttpServlet implements Serializable{
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
String flag,f1,f2,f3;
String nm;
String SQLStr="",protocol;
ArrayList ser=new ArrayList ();
statbean ob=new statbean();

java.sql.Connection con=null;
java.sql.Statement st=null;
java.sql.ResultSet rs=null;
ResourceBundle rb =null;
java.sql.PreparedStatement pstmt=null; 


	
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
         
         //ReportService ss = BusinessServiceFactory.INSTANCE.getReportService();						
		 //con=ss.getDBConnect(); 
         
         con=SessionHibernateUtil.getSession().connection();

		 nm=request.getParameter("name");
		String value=request.getParameter("sflag");
		   
	 if(value.equals("Dept"))
		    {

			SQLStr ="select dept_code,dept_name,short_desc from department_mas where 2>1 and dept_name like '" + nm + "%'   order by dept_code";
 st=con.createStatement();
  rs = st.executeQuery(SQLStr);

    while(rs.next()){

    	   f1=rs.getString("dept_Code");
           f2=rs.getString("dept_Name");
           f3=rs.getString("Short_Desc");

			ser.add(f1);
			ser.add(f2);
			ser.add(f3);
		    }
		ob.setAl(ser);
		
		request.setAttribute("bean",ob);

	 getServletConfig().getServletContext().getRequestDispatcher("/Statistics/search.jsp?check=ok&flag="+value+"&nam="+nm+"").forward(request, response);
	 }
	 if(value.equals("Sub"))
		    {

			SQLStr="select * from subject_mas where 2>1 and sub_name like '" + nm + "%'  order by sub_code";
  st=con.createStatement();
  rs = st.executeQuery(SQLStr);
    while(rs.next()){

	   f1=rs.getString("sub_code");
           f2=rs.getString("sub_name");
           f3=rs.getString("Short_Desc");

			ser.add(f1);
			ser.add(f2);
			ser.add(f3);
		    }
		ob.setAl(ser);
		
		request.setAttribute("bean",ob);

	 getServletConfig().getServletContext().getRequestDispatcher("/Statistics/search.jsp?check=ok&flag="+value+"&nam="+nm+"").forward(request, response);
	 }
 if(value.equals("Sup"))
		    {

			SQLStr="select * from sup_pub_mas where 2>1 and SP_name like  '" + nm + "%' and sp_type='SUPPLIER'";
  st=con.createStatement();
  rs = st.executeQuery(SQLStr);
    while(rs.next()){

	   f1=rs.getString("sp_code");
           f2=rs.getString("sp_name");
           f3=rs.getString("Short_Desc");

			ser.add(f1);
			ser.add(f2);
			ser.add(f3);
		    }
		ob.setAl(ser);
		
		request.setAttribute("bean",ob);

	 getServletConfig().getServletContext().getRequestDispatcher("/Statistics/search.jsp?check=ok&flag="+value+"&nam="+nm+"").forward(request, response);
	 }

if(value.equals("Pub"))
		    {

			SQLStr="select * from sup_pub_mas where 2>1 and SP_name like '" + nm + "%'  and sp_type='PUBLISHER'";
  st=con.createStatement();
  rs = st.executeQuery(SQLStr);
    while(rs.next()){

	   f1=rs.getString("sp_code");
           f2=rs.getString("sp_name");
           f3=rs.getString("Short_Desc");

			ser.add(f1);
			ser.add(f2);
			ser.add(f3);
		    }
		ob.setAl(ser);
		
		request.setAttribute("bean",ob);

	 getServletConfig().getServletContext().getRequestDispatcher("/Statistics/search.jsp?check=ok&flag="+value+"&nam="+nm+"").forward(request, response);
	 }



			 } catch (Exception e) {

					}
			 finally
			 {
				 try{
					 if(con!=null){
							con.close();
							con=null;
						}
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



