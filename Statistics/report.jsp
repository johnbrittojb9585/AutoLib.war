<%@ page language="java" session="true" buffer="12kb" import="java.sql.*" %>
<%@ include file="/Common.jsp" %>
<%
String cSec = checkSecurity(1, session, response, request);
if ("s1".equals(cSec) ) return;
%>
<html>
<head>
<title>AutoLib</title>
<meta http-equiv="pragma" content="no-cache"/>
</head>
<form name="simple" action="report.jsp">
<input type="hidden" name="flag"> 
<%
try
{
java.sql.Connection con=null;
java.sql.Connection con1=null;
java.sql.Statement st=null;
java.sql.Statement stm=null;
java.sql.ResultSet rs=null;
java.sql.ResultSet rs1=null;
String type="";
String sql1="",sql2="",sql3="",report_type="",from_v="",to_v="",strsql="",strsql2="",lblCaption="";
String fd,fm,fy,td,tm,ty;
int statistic_count=0,statistic_amount=0;

int number=0;
int amount=0;
int no_of_titles=0;
int total_amount=0;
int total_title=0;
String dept="";
int l=1;
int total_book=0;
String sErr = loadDriver();
if ( ! sErr.equals("") ) {
 try {
   out.println(sErr);
 }
 catch (Exception e) {}
}
con1 = cn();
st=con1.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
con = cn();
stm=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);

if(request.getParameter("hid")!=null && request.getParameter("hid").equals("statis"))
{

	if(!request.getParameter("txtdepartment").equals(""))
	{
	sql1=sql1+ " and department = '" +getParam( request, "txtdepartment")+"'";
	}
	
	if(!request.getParameter("txtsubject").equals(""))
	{
	sql1=sql1+ " and subject ='" +getParam( request, "txtsubject")+"'";
	}
	if(!request.getParameter("txtpublisher").equals(""))
	{
	sql1=sql1+ " and publisher ='" +getParam( request, "txtpublisher")+"'";
	}
	if(!request.getParameter("txtsupplier").equals(""))
	{
	sql1=sql1+ " and supplier='" +getParam( request, "txtsupplier")+ "'";
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
	
	if(request.getParameter("reporttype").equals("Department"))
    {
	report_type = " Book Details (Department)";
	type=request.getParameter("reporttype");
	strsql="Select distinct(department) as type,count(access_no)as number,count(distinct Title) as uniquetitle,sum(Bcost) as sum_amount from Book_Statistics  " + sql2+sql1 +" group by department";
	}
	if(request.getParameter("reporttype").equals("Subject"))
   	{
    report_type =" Book Details (Subject)";
    type=request.getParameter("reporttype");
	strsql="Select distinct(subject) as type,count(access_no)as number,count(distinct Title) as uniquetitle,sum(Bcost) as sum_amount from Book_Statistics  " + sql2+sql1 +" group by subject";
	}   

	if(request.getParameter("reporttype").equals("Publisher"))
    {
	type=request.getParameter("reporttype");
	report_type = " Book Details (Publisher)";
	strsql="Select distinct(publisher) as type,count(access_no)as number,count(distinct Title) as uniquetitle,sum(Bcost) as sum_amount from Book_Statistics  " + sql2+sql1 +" group by publisher";
	}
	if(request.getParameter("reporttype").equals("Supplier"))
	{
	type=request.getParameter("reporttype");
    report_type = " Book Details (Supplier)";
	strsql="Select distinct(supplier) as type,count(access_no)as number,count(distinct Title) as uniquetitle,sum(Bcost) as sum_amount from Book_Statistics  " + sql2+sql1 +" group by supplier";
	}
	if(request.getParameter("reporttype").equals("Department & Subject"))
	{
	type=request.getParameter("reporttype");
 	report_type =" Book Details (Department & Subject)";
	}
   
	if(request.getParameter("reporttype").equals("Subject & Department"))
  	{
	type=request.getParameter("reporttype");
	 report_type =" Book Details (Subject & Department)";}
	%>
	
	<center><h3><%=report_type%> </center></h3>
	<h4>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;From:<%=from_v%>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	To:<%=to_v%>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

		<table border="1" align="Center" cellpadding=0 cellspacing=0>
		<td bgcolor="#C0C0C0" width="71" align="left">
		<b><font color="#FF0000">Sl No</font></b></td> 
		<td width=250 bgcolor="#C0C0C0" align="left">
		<b><font color="#FF0000"><%=type%></font></b></td>
		<td width=150 bgcolor="#C0C0C0" align="right">
		<b><font color="#FF0000">No of Books</font></b></td>
		<td width=150 bgcolor="#C0C0C0" align="right">
		<b><font color="#FF0000">No of Titles</font></b></td>
		<td bgcolor="#C0C0C0" width="76" align="right">
		<b><font color="#FF0000">Amount</font></b></td>
		</th></tr>
		<%
		//out.print(strsql);
		if(!strsql.equals("")){
		out.print(strsql);
		rs=stm.executeQuery(strsql);
		
		while(rs.next())
		{
		dept=rs.getString("type");
		number=rs.getInt("number");
		no_of_titles=rs.getInt("uniquetitle");
		amount=rs.getInt("sum_amount");
		total_amount=total_amount+amount;
		total_book=total_book+number;
		total_title=total_title+no_of_titles;
		out.print("<tr>");
		out.print("<td align=center>"+l+"</td>"+"<td align=left>"+dept+"</td>"+"<td align=right>"+number+"</td>"+"<td align=right>"+no_of_titles+"</td><td align=right>"+amount+"</td>");
		out.print("</tr>");
		l++;
		}
		}

		else
		{
		if(request.getParameter("reporttype").equals("Department & Subject"))
   			{ report_type =" Book Details (Department & Subject)";
   			strsql="Select distinct(department) from Book_Statistics  " + sql2+sql1;
			rs=stm.executeQuery(strsql);
			while (rs.next())
			{
				String dept_name=rs.getString("department");
				//String subb_name=getParam( request, "sub_name");
				strsql="Select distinct(subject) as type,count(access_no)as number, count(distinct Title) as uniquetitle,sum(Bcost) as sum_amount from Book_Statistics " +sql2+sql1 +" and department='"+dept_name+"'"+" group by subject";
	//out.print(strsql);
	rs1=st.executeQuery(strsql);
	int j=1;
	out.print("<tr><td colspan=5 align=left>"+dept_name+"</td></tr>");
while(rs1.next())
{
dept=rs1.getString("type");
number=rs1.getInt("number");
no_of_titles=rs1.getInt("uniquetitle");
amount=rs1.getInt("sum_amount");
total_amount=total_amount+amount;
total_book=total_book+number;
total_title=total_title+no_of_titles;
out.print("<tr>");
out.print("<td align=center>"+j+"</td>"+"<td align=left>"+dept+"</td>"+"<td align=right>"+number+"</td>"+"<td align=right>"+no_of_titles+"</td><td align=right>"+amount+"</td>");
out.print("</tr>");
j++;
				}
		}
}
			if(request.getParameter("reporttype").equals("Subject & Department"))
   			{ report_type =" Book Details (Subjects & Department)";
   			strsql="Select distinct(subject) from Book_Statistics  " + sql2+sql1;
			rs=stm.executeQuery(strsql);
			while (rs.next())
			{
				String sub_name=rs.getString("subject");
				String subb_name=getParam( request, "sub_name");
				strsql="Select distinct(department) as type,count(access_no)as number, count(distinct Title) as uniquetitle,sum(Bcost) as sum_amount from Book_Statistics " +sql2+sql1 +" and subject='"+sub_name +"'"+" group by department";
	rs1=st.executeQuery(strsql);
	int j=1;
	out.print("<tr><td colspan=5 align=left>"+sub_name+"</td></tr>");
while(rs1.next())
{
dept=rs1.getString("type");
number=rs1.getInt("number");
no_of_titles=rs1.getInt("uniquetitle");
amount=rs1.getInt("sum_amount");
total_amount=total_amount+amount;
total_book=total_book+number;
total_title=total_title+no_of_titles;
out.print("<tr>");
out.print("<td align=center>"+j+"</td>"+"<td align=left>"+dept+"</td>"+"<td align=right>"+number+"</td>"+"<td align=right>"+no_of_titles+"</td><td align=right>"+amount+"</td>");
out.print("</tr>");
j++;
}
}
}
}
%>
<tr><td align="right" colspan="3" height="19">
<font size=3><b>Total:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<%=total_book%>
</td>
<td align="right"><font size=3><b><%=total_title%></td>
<td align="right"><font size=3><b><%=total_amount%></b></font></td>

</tr>
</table> 
<%
}
}catch(Exception e){out.println("Error" + e);}
%>
</form>
</html>
