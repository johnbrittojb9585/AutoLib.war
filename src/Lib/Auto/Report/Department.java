package Lib.Auto.Report;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.sql.DatabaseMetaData;
import java.util.ArrayList;


import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.*;
import java.sql.*;



import Common.DBConnection;
import Common.Security;

public class Department extends HttpServlet implements Serializable {
                String	ROWSEP 		 = "^";
		String	COLSEP 	     = "~";
		
		java.sql.Connection con=null;
		java.sql.Statement st=null;
        java.sql.ResultSet rs=null;

	//singlecodecheck chkcode=new singlecodecheck();
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
	        
			//con=(java.sql.Connection)session.getAttribute("con");
			con=DBConnection.getInstance();
			//flag=request.getParameter("flag");
			st=con.createStatement();

			RequestDispatcher rd = getServletContext().getRequestDispatcher("/CounterReport/index.jsp");
			String designationInfo,groupInfo;

			String SQLStringCode="select Dept_Code,Dept_Name from Department_Mas order by Dept_Name";
			request.setAttribute("designationInfo", comboInfo(SQLStringCode, rs, st, request, response));
			SQLStringCode="select Group_Code,Group_Name from Group_mas order by Group_Name";
			request.setAttribute("groupInfo", comboInfo(SQLStringCode, rs, st, request, response));
			//SQLStringCode="select Course_Code,Course_Name from Course_Mas order by Course_Name";
			//request.setAttribute("courseInfo", comboInfo(SQLStringCode, rs, st, request, response));
			rd.forward(request,response);



		   }        catch (Exception e) {}
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



protected String comboInfo(String sql, ResultSet rs, Statement stmt, HttpServletRequest request , HttpServletResponse response) throws ServletException,IOException
	            {
		String comboInfo		=	" -Select- "+ COLSEP +" -Select- " ;
		String parameterPassing = null;
		try
		{
		        response.setContentType("text/html");
            		PrintWriter out = response.getWriter();

				rs = stmt.executeQuery(sql);
			//	out.println("rs.getRow() :"+rs.getRow());
				rs.beforeFirst();
			        while ( rs.next())
			comboInfo	=	comboInfo + ROWSEP + rs.getString(1) + COLSEP + rs.getString(2);
			rs.close();

		}
		catch(SQLException sqle)
		{

		}

		return comboInfo;
	}


}
