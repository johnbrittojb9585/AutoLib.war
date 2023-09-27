package Lib.Auto.Statistics;
import Lib.Auto.Statistics.statbean;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.util.ArrayList;

import javax.servlet.http.HttpServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import javax.servlet.*;


import Common.DBConnection;
import Common.Security;



public class DeptSub extends HttpServlet {
	ArrayList ser=new ArrayList();
	ArrayList sc=new ArrayList();
	statbean ob=new statbean();
	String hid="";
	String type="";
	java.sql.Connection con=null;
	//java.sql.Statement stm=null;
	java.sql.Statement st=null;
    		java.sql.ResultSet rs=null;
	java.sql.ResultSet rs1=null;
String sql1="",sql2="",sql3="",sql4="",report_type="",from_v="",to_v="",strsql="",strsql1="",strsql2="",lblCaption="";


int number=0;
int amount=0;
int no_of_titles=0;
int total_amount=0;
int total_title=0;
String dept="";
int l=1;
int total_book=0;

	singlecodecheck chkcode=new singlecodecheck();
	String protocol;

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

			st=con.createStatement();
		if(!request.getParameter("txtdepartment").equals(""))
		{
		sql1=sql1+ " and department = '" +chkcode.getParam( request, "txtdepartment")+"'";
		}

		if(!request.getParameter("txtsubject").equals(""))
		{
		sql1=sql1+ " and subject ='" +chkcode.getParam( request, "txtsubject")+"'";
		}
		if(!request.getParameter("txtpublisher").equals(""))
		{
		sql1=sql1+ " and publisher ='" +chkcode.getParam( request, "txtpublisher")+"'";
		}
		if(!request.getParameter("txtsupplier").equals(""))
		{
		sql1=sql1+ " and supplier='" +chkcode.getParam( request, "txtsupplier")+ "'";
		}

		if(request.getParameter("R1").equals("V1"))
		{
from_v=request.getParameter("From_M")+"/"+request.getParameter("From_D")+"/"+request.getParameter("From_Y");
	to_v=request.getParameter("To_M")+"/"+request.getParameter("To_D")+"/"+request.getParameter("To_Y");
	sql2 = "where  Received_Date Between '" +from_v+ "' and '" +to_v+ "'";
	}
	if(request.getParameter("R1").equals("V2"))
	{
	from_v=request.getParameter("txtfromacc");
	to_v=request.getParameter("txttoacc");
	sql2=" where access_no between '"+from_v+"' and '"+to_v+"'";
	}



if(request.getParameter("reporttype").equals("Department & Subject"))
{
 	type=request.getParameter("reporttype");
 	report_type =" Book Details (Department & Subject)";

	 if(request.getParameter("doctype").equals("BOOK"))
			{
	sql3="Select distinct(subject) as type,count(access_no) as number, count(distinct Title) as uniquetitle,sum(Bcost) as sum_amount from Book_Statistics " +sql2+sql1 +" and document='BOOK'";
			}
	 else if(request.getParameter("doctype").equals("THESIS"))
	{
	sql3="Select distinct(subject) as type,count(access_no) as number, count(distinct Title) as uniquetitle,sum(Bcost) as sum_amount from Book_Statistics " +sql2+sql1 +" and document='THESIS'";
	}
	else if(request.getParameter("doctype").equals("BACK VOLUME"))
	{
	sql3="Select distinct(subject) as type,count(access_no) as number, count(distinct Title) as uniquetitle,sum(Bcost) as sum_amount from Book_Statistics " +sql2+sql1 +" and document='BACK VOLUME'";
	}
	else if(request.getParameter("doctype").equals("PROCEEDING"))
	{
	sql3="Select distinct(subject) as type,count(access_no) as number, count(distinct Title) as uniquetitle,sum(Bcost) as sum_amount from Book_Statistics " +sql2+sql1 +" and document='PROCEEDING'";
	}
	else if(request.getParameter("doctype").equals("REPORT"))
	{
	sql3="Select distinct(subject) as type,count(access_no) as number, count(distinct Title) as uniquetitle,sum(Bcost) as sum_amount from Book_Statistics " +sql2+sql1 +" and document='REPORT'";
	}
	else if(request.getParameter("doctype").equals("NON BOOK"))
	{
	sql3="Select distinct(subject) as type,count(access_no) as number, count(distinct Title) as uniquetitle,sum(Bcost) as sum_amount from Book_Statistics " +sql2+sql1 +" and document='NON BOOK'";
	}
	else if(request.getParameter("doctype").equals("STANDARD"))
	{
	sql3="Select distinct(subject) as type,count(access_no) as number, count(distinct Title) as uniquetitle,sum(Bcost) as sum_amount from Book_Statistics " +sql2+sql1 +" and document='STANDARD'";
	}
	else if(request.getParameter("doctype").equals("REFERENCE"))
	{
	sql3="Select distinct(subject) as type,count(access_no) as number, count(distinct Title) as uniquetitle,sum(Bcost) as sum_amount from Book_Statistics " +sql2+sql1 +" and document='REFERENCE'";
	}
	else if(request.getParameter("doctype").equals("ALL"))
	{
	sql3="Select distinct(subject) as type,count(access_no) as number, count(distinct Title) as uniquetitle,sum(Bcost) as sum_amount from Book_Statistics " +sql2+sql1 +" ";
	}


	out.print("<font size=4>Document Type : "+report_type+"</font>");
	out.print("<br><table border=1 width=700 align=center CELLSPACING='0' BORDERCOLOR='#DADADA' BGCOLOR='#F5F5F5'> ");
	out.print("<th>Sno</th><th>"+type+"</th><th>No of Books</th><th>No of Titles</th><th>Amount</th>");


			report_type =" Book Details (Department & Subject)";
			strsql="Select distinct(department) from Book_Statistics  " + sql2+sql1;
			out.print(strsql);
			rs=st.executeQuery(strsql);

			while (rs.next())
			{
			String dept_name=rs.getString("department");
			sql4=sql3+ " and department='"+dept_name+"'"+" group by subject ";
			out.print(sql4);
			rs1=st.executeQuery(sql4);


	int j=1;

	out.print("<tr><td colspan=5 align=left>"+dept_name+"</td></tr>");

	while(rs1.next())
		{

		dept=rs1.getString("type");
		number=rs1.getInt("number");
		String no=String.valueOf(number);
		no_of_titles=rs1.getInt("uniquetitle");
		String No_Titles=String.valueOf(no_of_titles);
		amount=rs1.getInt("sum_amount");
		String amt=String.valueOf(amount);
		total_amount=total_amount+amount;
		total_book=total_book+number;
		total_title=total_title+no_of_titles;

		ser.add(dept);
		ser.add(no);
		ser.add(No_Titles);
		ser.add(amt);

	}
	ob.setAl(ser);
	sc=(ArrayList)ob.getAl();
		for(int i=0; i<sc.size();i++){
		out.print("<tr>");
	out.print("<td align=center>"+j+"</td>"+"<td align=left>"+sc.get(i)+"</td>"+"<td 			align=right>"+sc.get(++i)+"</td>"+"<td align=right>"+sc.get(++i)+"</td><td align=right>"+sc.get(++i)+"</td>");
out.print("</tr>");
j++;


	}
	sc.clear();

		}
				rs.close();
				rs1.close();
				sql1="";
				sql2="";
				sql3="";
				sql4="";
				strsql="";

			//}
			}



out.print("<tr><td align=right colspan=3 height=19>");
out.print("<font size=3><b>"+total_book+"</b></font>");
out.print("</td>");
out.print("<td align=right><font size=3><b>"+total_title+"</b></td>");
out.print("<td align=right><font size=3><b>"+total_amount+"</b></font></td>");

out.print("</tr>");
out.print("</table>");




			 }  catch (Exception e) {

					}
					finally{
					sql1="";
					sql2="";
					strsql="";
					total_book=0;
					total_title=0;
					total_amount=0;
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
