package Lib.Auto.MemberReport;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.sql.DriverManager;
import java.util.ResourceBundle;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.UnavailableException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import Common.DataQuery;
import Common.Security;
import Lib.Auto.Counter.COUNTER_QUERY;
import Lib.Auto.OrdInvProcessing.orderbean;


import Common.DBConnection;

public class FreqAccessReport extends HttpServlet implements Serializable, COUNTER_QUERY {
	private static final long serialVersionUID = 1L;
	java.sql.Connection con = null;
	java.sql.PreparedStatement Prest = null;
	java.sql.ResultSet rs = null;
	java.sql.ResultSet rs1 = null;
	DataSource datasource;

	String flag="",protocol="",strsql="",strsql1="",strsql2="",strsql3="",strsql4="",strsql5="",strsql6="";
	String indexPage = null;
	orderbean orderObject=new orderbean();
	ResourceBundle rb = ResourceBundle.getBundle("LocalStrings");
	
    public static final String SELECT_MEMBER="select member_code from member_mas where member_name=?";
    public static final String SELECT_DEPT="select dept_code from department_mas where dept_name=?";
    public static final String SELECT_GROUP="select group_code from group_mas where group_name=?";
    public static final String SELECT_COURSE="select course_code from course_mas where course_name=?";
		
	public void doGet(HttpServletRequest request, HttpServletResponse response)  throws ServletException {

		performTask(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException{

		performTask(request, response);

	}
	public void performTask(
		HttpServletRequest request,
		HttpServletResponse response)  throws ServletException {


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
                            
           if(!request.getParameter("name").equals("")){
        	   strsql="select member_code from member_mas where member_name='"+request.getParameter("name")+"'";
        	   Prest=con.prepareStatement(strsql);
        	   rs=Prest.executeQuery();
        	   if(rs.next()){
        		   strsql1=" and M.member_code='"+rs.getString("member_code")+"'";	   
        	   }
            
            }
           if(!request.getParameter("dept").equals("")){
        	   strsql="select dept_code from department_mas where dept_name='"+request.getParameter("dept")+"'";
        	   Prest=con.prepareStatement(strsql);
        	   rs=Prest.executeQuery();
        	   if(rs.next()){
        		   strsql2=" and M.dept_code='"+rs.getInt("dept_code")+"'";	   
        	   }
            
            }
        
           if(!request.getParameter("group").equals("")){
        	   strsql="select group_code from group_mas where group_name='"+request.getParameter("group")+"'";
        	   Prest=con.prepareStatement(strsql);
        	   rs=Prest.executeQuery();
        	   if(rs.next()){
        		   strsql3=" and M.group_code='"+rs.getInt("group_code")+"'";	   
        	   }
            
            }
              
           if(!request.getParameter("course").equals("")){
        	   strsql="select course_code from course_mas where course_name='"+request.getParameter("course")+"'";
        	   Prest=con.prepareStatement(strsql);
        	   rs=Prest.executeQuery();
        	   if(rs.next()){
        		   strsql4=" and M.course_code='"+rs.getInt("course_code")+"'";	   
        	   }
            
            }
          
            if(request.getParameter("status").equals("ACTIVE")){
            strsql5=" and Expiry_Date>='"+Security.Counter_DateText()+"'";	
                }
            
            if(request.getParameter("status").equals("IN ACTIVE")){
                strsql6=" and Expiry_Date<='"+Security.Counter_DateText()+"'";	
                    }
            
            out.print("<br><br><br><div align=right><a href=/AutoLib/MemberReport/countrep.jsp><img border='0' src='/AutoLib/images/Back.gif'></a></div>");
            if(request.getParameter("access").equals("By Frequently Accessed Members")){
            	strsql="select count(*) as count,group_name,dept_name,course_name,I.member_code,member_name,designation_code from member_mas M,group_mas G, department_mas D, course_mas C,issue_history I Where M.Group_Code = G.Group_Code and M.Dept_Code = D.Dept_Code and M.Course_Code = C.Course_Code And M.Member_Code = I.Member_Code and return_date between '"+Security.TextDate_Insert(request.getParameter("txtfdate"))+"' and '"+Security.TextDate_Insert(request.getParameter("txttodate"))+"'" +strsql1 +strsql2 +strsql3 +strsql4 +strsql5 +strsql6 + " group by M.member_code,group_name,dept_name,course_name,member_name,designation_code";
            	
            	Prest=con.prepareStatement(strsql);
            	
            	rs=Prest.executeQuery();
            	if(rs.next()){
            		out.print("<br><center><b><font face=verdana size=4>Frequently Accessed Members Report</font></b></center");
            		out.print("<br><table border=1 width=70% align=center CELLSPACING='0' BORDERCOLOR='#DADADA' BGCOLOR='#F5F5F5'> ");
            		out.print("<th><font face=verdana size=2>Count</font></th><th><font face=verdana size=2>Group&nbsp;Name</font></th><th><font face=verdana size=2>Dept&nbsp;Name</font></th><th><font face=verdana size=2>Course&nbsp;Name</font></th><th><font face=verdana size=2>Member&nbsp;Code</font></th><th><font face=verdana size=2>Member&nbsp;Name</font></th><th><font face=verdana size=2>Design&nbsp;Code</font></th>");
            		
            	}else{
            		out.print("<br><br><br><br><br><br><br><br><center><font face=verdana size=3 color=blue>Record Not Found</font></center");
            	}
            	rs.beforeFirst(); 
            	while(rs.next()){
            		out.print("<tr>");
           			out.print("<td width=10%><font face=verdana size=2>"+rs.getString("count")+"</font></td>"+"<td width=10%><font face=verdana size=2>"+rs.getString("group_name")+"</font></td>"+"<td width=10%><font face=verdana size=2>"+rs.getString("dept_name")+"</font></td>"+"<td width=20%><font face=verdana size=2>"+rs.getString("course_name")+"</font></td>"+"<td width=20%><font face=verdana size=2>"+rs.getString("member_code")+"</font></td><td width=10%><font face=verdana size=2>&nbsp;"+rs.getString("member_name")+"</font></td><td width=10%><font face=verdana size=2>"+rs.getString("designation_code")+"</font></td>");
           			
           			out.print("</tr>");
            	}
            }
            else{
            	
            	strsql="select count(*) as count, group_name,dept_name,course_name,M.member_code,member_name,designation_code from member_mas M,group_mas G, department_mas D, course_mas C Where M.Group_Code = G.Group_Code and M.Dept_Code = D.Dept_Code and M.Course_Code = C.Course_Code " +strsql1 +strsql2 +strsql3 +strsql4 +strsql5 +strsql6 + " group by M.member_code,group_name,dept_name,course_name,member_name,designation_code " +" And M.Member_Code not in(select member_code from issue_history where Return_Date between '"+Security.TextDate_Insert(request.getParameter("txtfdate"))+"' and '"+Security.TextDate_Insert(request.getParameter("txttodate"))+"')";
           
            	Prest=con.prepareStatement(strsql);
            	rs=Prest.executeQuery();
            	
               if(rs.next()){
            	   out.print("<br><center><b><font face=verdana size=4>Least Accessed Members Report</font></b></center");
            	   out.print("<br><table border=1 width=70% align=center CELLSPACING='0' BORDERCOLOR='#DADADA' BGCOLOR='#F5F5F5'> ");
           		  
            	   out.print("<th><font face=verdana size=2>Count</font></th><th><font face=verdana size=2>Group&nbsp;Name</font></th><th><font face=verdana size=2>Dept&nbsp;Name</font></th><th><font face=verdana size=2>Course&nbsp;Name</font></th><th><font face=verdana size=2>Member&nbsp;Code</font></th><th><font face=verdana size=2>Member&nbsp;Name</font></th><th><font face=verdana size=2>Design&nbsp;Code</font></th>");
            	}else{
            		out.print("<br><br><br><br><br><br><br><br><center><font face=verdana size=3 color=blue>Record Not Found</font></center>");
            	}
            	rs.beforeFirst(); 
            	while(rs.next()){
            		out.print("<tr>");
           		
            		out.print("<td width=10%><font face=verdana size=2>"+rs.getString("count")+"</font></td>"+"<td width=10%><font face=verdana size=2>"+rs.getString("group_name")+"</font></td>"+"<td width=10%><font face=verdana size=2>"+rs.getString("dept_name")+"</font></td>"+"<td width=20%><font face=verdana size=2>"+rs.getString("course_name")+"</font></td>"+"<td width=20%><font face=verdana size=2>"+rs.getString("member_code")+"</font></td><td width=10%><font face=verdana size=2>&nbsp;"+rs.getString("member_name")+"</font></td><td width=10%><font face=verdana size=2>"+rs.getString("designation_code")+"</font></td>");	
           			out.print("</tr>");
            	}
            	
            }
            out.print("</table>");
            
        	 
	}  catch (Exception e) {

	}
catch (Throwable theException) {
	
	}
finally{
	strsql="";
	strsql1="";
	strsql2="";
	strsql3="";
	strsql4="";
	strsql5="";
	strsql6="";
	try{
		if(rs!=null){
			rs.close();
			rs=null;
		}
		if(Prest!=null){
			Prest.close();
			Prest=null;
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
		   // response.sendRedirect(indexPage);
			RequestDispatcher dispatch = request.getRequestDispatcher(indexPage);
			dispatch.forward(request, response);
		}

}
