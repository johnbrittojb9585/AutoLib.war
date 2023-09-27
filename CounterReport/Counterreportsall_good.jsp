<%@ page language="java" session="true" buffer="12kb" import="java.sql.*" %>
<%@ include file="/Common.jsp" %>
<form name=counterall>
<%
int iLevel=0;
      Object o1 = session.getAttribute("UserID");

      Object o2 = session.getAttribute("UserRights");
      boolean bRedirect = false;
      if ( o1 == null || o2 == null ) { bRedirect = true; }
      if ( ! bRedirect ) {
        if ( (o1).equals("")) { bRedirect = true; }
        else if ( (new Integer(o2.toString())).intValue() < iLevel) { bRedirect = true; }
      }
      if ( bRedirect ) {
	   response.sendRedirect("/AutoLib/Login.jsp");
  }
	String userrights=o2.toString();

java.sql.Connection con=null;
java.sql.Statement st=null;
java.sql.ResultSet rs=null;
try{
String sErr = loadDriver();
con = cn();
st=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
if ( ! sErr.equals("") ) {
 try {
   out.println(sErr);
 }
 catch (Exception e) {}
}

	String txtreporttype="",txtaccessno="",txtmembercode="",txtdepartment="",txtfdate="",txttdate="",txtgroup="";
	String order1="",order2="",order3="",str="",strsql="",order,strsqlq="";
	String deptname="",groupname="",staff="",Reptfrom="",txtcourse="",txtdoctype="",report_type="",coursename="", count_book="", fine_amount="";
	int sno=0, total=0;
	float fine=0,subcount=0;
/*--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/
	            java.util.Calendar calendar = new java.util.GregorianCalendar();
				java.util.Date r=new java.util.Date();
				calendar.setTime(r);
					int rd=calendar.get(java.util.Calendar.DAY_OF_MONTH);
					int rm=calendar.get(java.util.Calendar.MONTH)+1;
				     int ry=calendar.get(java.util.Calendar.YEAR);
				    String temp_rm=new Integer(rm).toString();
				    if(temp_rm.length()==1)
				    temp_rm="0"+temp_rm;
					String temp_rd=new Integer(rd).toString();
					if(temp_rd.length()==1)
				    temp_rd="0"+temp_rd;
					String Today_Date=temp_rd+"/"+temp_rm+"/"+ry;
/*-----------------------------------------------------------------------------------------------------------------------------------------------------------------------*/
	txtreporttype =request.getParameter("reporttype");
	txtaccessno=getParam( request, "txtaccessno");
	txtmembercode =getParam( request, "txtmembercode");
	txtdepartment =request.getParameter("txtdepartment");
	txtgroup =request.getParameter("txtgroup");
	txtcourse =request.getParameter("txtcourse");
	txtdoctype=request.getParameter("txtdoctype");
	txtfdate =getParam( request, "txtfdate");
	txttdate =getParam( request, "txttdate");
   // Reptfrom=request.getParameter("Reptfrom");
	/*---------------oder by--------------------------------------------------------*/

	order1 =request.getParameter("order1");
	order2 =request.getParameter("order2");
	order3 =request.getParameter("order3");

	report_type=request.getParameter("report_type");
	if(!txtdepartment.equals("NO")){
	str = "select Dept_Name from Department_Mas where Dept_Code =" + txtdepartment;
	rs=st.executeQuery(str);
	while (rs.next()){
      deptname = rs.getString("Dept_Name");
	  }
	%><script>alert("<%=deptname%>");</script><%
	}

	if(!txtgroup.equals("NO")){
	str = "select Group_Name from Group_Mas where Group_Code ="+txtgroup;
	rs=st.executeQuery(str);
	while (rs.next()) groupname = rs.getString("Group_Name");
	%><script>alert("<%=groupname%>");</script><%
	}

	if(!txtcourse.equals("NO")){
	str = "select course_Name from course_Mas where course_code="+txtcourse;
	rs=st.executeQuery(str);
	while (rs.next()) coursename = rs.getString("Course_Name");
	%><script>alert("<%=coursename%>");</script><%
	}
	strsql = "";
	if (!txtmembercode.equals("") && (txtreporttype.equals("Issue")) )
	{
	strsql = strsql + " and Member_Code = '" +txtmembercode+"'";
	}
	if (!txtmembercode.equals("") && (txtreporttype.equals("Return")) )
	{
	strsql = strsql + " and Member_Code = '" +txtmembercode+"'";
	}
	if (!txtmembercode.equals("") && (txtreporttype.equals("Renewal")) )
	{
	strsql = strsql + " and Member_Code = '" +txtmembercode+"'";
	}
	if (!txtmembercode.equals("") && (txtreporttype.equals("Reserve")) )
	{
	strsql = strsql + " and Reserve_Mas.Member_Code = '" +txtmembercode+"'";
	}
	if (!txtmembercode.equals("") && (txtreporttype.equals("Resreminder")) )
	{
	strsql = strsql + " and Reserve_Mas.Member_Code = '" +txtmembercode+"'";
	}
	if (!txtmembercode.equals("") && (txtreporttype.equals("Duereminder")) )
	{
	strsql = strsql + " and Issue_Mas.Member_Code = '" +txtmembercode+"'";
	}
	if (!txtaccessno.equals("") && (txtreporttype.equals("Resreminder")) )
	{
	strsql = strsql + " and Reserve_Mas.access_no = '" +txtaccessno+"'";
	}

	if(!txtaccessno.equals("") && !txtreporttype.equals("Resreminder") ){
	strsql = strsql + " and access_no='"+txtaccessno+"'";
	}

	if (!txtdepartment.equals("NO"))
	strsql = strsql + "  and Dept_Code = '" +txtdepartment+"'";

	if (!txtgroup.equals("NO"))
	strsql = strsql + "  and Group_Code = '" +txtgroup+"'";

	if (!txtcourse.equals("NO"))
	strsql = strsql + "  and course_code = '" +txtcourse+"'";

	/* if((!txtfdate.equals("")) && (!txttdate.equals("")))
	{
	if((txtreporttype.equals("Issue")) || (txtreporttype.equals("Renewal")))
	strsql = strsql + " and Issue_Date  like '" +txtfdate+"'";
	if (txtreporttype.equals("Duereminder"))
	strsql = strsql + " and Due_Date like '" +txtfdate+"'";
	if((txtreporttype.equals("Fine")) || (txtreporttype.equals("Return")))
	strsql = strsql + " and Return_Date like '" +txtfdate+"'";
	}


	if ((!txttdate.equals("")) &&  (!txtfdate.equals("")))
	{
	if ((txtreporttype.equals("Issue")) || (txtreporttype.equals("Renewal")))
	strsql = strsql + " and Issue_Date like '" +txttdate+"'";
	if (txtreporttype.equals("Duereminder"))
	strsql = strsql + " and Due_Date like '" +txttdate+"'";
	if((txtreporttype.equals("Fine")) ||(txtreporttype.equals("Return")))
	strsql = strsql + " and Return_Date like '" +txttdate+"'";
	} */

	if ((!txttdate.equals("")) &&  (!txtfdate.equals("")))
	{
	if ((txtreporttype.equals("Issue")) ||(txtreporttype.equals("Renewal")))
	strsql = strsql + " and Issue_Date between '"+txtfdate+"' and '" +txttdate+"'";

	if (txtreporttype.equals("Duereminder"))
	strsql = strsql + " and  Due_Date between '"+txtfdate+"' and '" +txttdate+"'";
	if (txtreporttype.equals("Resreminder"))
	strsql = strsql + " and  Res_Date between '"+txtfdate+"' and '" +txttdate+"'";
	if ((txtreporttype.equals("Fine")) ||(txtreporttype.equals("Return")))
	strsql = strsql + " and Return_Date between '"+txtfdate+"' and '" +txttdate+"'";

	if (txtreporttype.equals("Reserve"))
	strsql = strsql + " and Res_Date between '"+txtfdate+"' and '" +txttdate+"'";

	}
	if(!txtgroup.equals("NO")) strsql = strsql + " and Group_Code = '" +txtgroup+"'";
	order = "";
	if (!order1.equals("NO" )) order = order+order1;
	if (!order2.equals("NO" )) order = order+","+order2;
	if (!order3.equals("NO")) order = order+","+order3;


	/*------------------ THIS IS FOR ISSUE ------------------------------*/

/*----------------------------ALL DOC----------------------------------------------------------------------------------------------------------------*/


	if ((txtreporttype.equals("Issue")) && (txtdoctype.equals("ALL")))
	{
	if(report_type.equals("listing"))
	{
	 /* strsqlq = "select * from juserstat_all where 1<2 "+strsql+" and issue_type='ISSUE' union select * from jissue_history where 1<2 "+strsql+" order by "+order+"" ; */
	 strsqlq = "select * from juserstat_all where 1<2 "+strsql+" and issue_type='ISSUE'";

	out.print(strsqlq);
	rs=st.executeQuery(strsqlq);
	if(!rs.next())
	{
	out.print("<font face=times new roman size=5 color=blue><center>No Record Found</center></font><br>");
	out.print("<tr><td><center><input type=button name=click value=back onclick=backward();></center></td></tr>");
	out.print("</table>");
	}
	else
	{
	rs.beforeFirst();
	sno=1;
	out.print("<center><font size=5>ISSUE REPORT</center></font>");
	out.print("<font size=5>Report Date :"+Today_Date+"</font>");
	out.print(" <table border='1' width='258'  CELLSPACING='0' BORDERCOLOR='#DADADA' BGCOLOR='#F5F5F5'>");
	if(!txtaccessno.equals(""))out.print("<tr><td width='111' height='10'>Access No</td><td width='129' height='10'>"+txtaccessno+"</td></tr>");
	if(!txtmembercode.equals(""))out.print("<tr><td width='111' height='10'>Member Code</td><td width='129' height='10'>"+txtmembercode+"</td></tr>");
	if(!txtdepartment.equals("NO")) out.print("<tr><td width='111' height='10'>Department</td><td width='129' height='10'>"+deptname+"</td></tr>");
	if(!txtfdate.equals(""))  out.print("<tr><td width='111' height='10'>From Date</td><td width='129' height='10'>"+txtfdate+"</td></tr>");
	if(!txttdate.equals(""))  out.print("<tr><td width='111' height='10'>To Date</td><td width='129' height='10'>"+txttdate+"</td> </tr>");
	if(!txtgroup.equals("NO")) out.print("<tr><td width='111' height='10'>Group</td><td width='129' height='10'>"+groupname+"</td></tr>");
	if(!txtcourse.equals("NO")) out.print("<tr><td width='111' height='10'>Course</td><td width='129' height='10'>"+ coursename+"</td></tr>");
	//if(!Reptfrom.equals(""))  out.print("<tr><td width='111' height='10'>Reptfrom</td><td width='129' height='10'>"+Reptfrom+"</td> </tr>");
	out.print("</table>");
	out.print("<br><table border=1 width=700 align=center CELLSPACING='0' BORDERCOLOR='#DADADA' BGCOLOR='#F5F5F5'> ");
	out.print("<th>Sno</th><th>Membercode</th><th>AccessNo</th><th>Title</th><th>IssueDate</th><th>DueDate</th><th>Department</th><th>Group</th><th>Course</th>");

	while(rs.next())
	{
	 	out.print("<tr><td><font size=2>"+sno+"</td>");
		out.print("<td><font size=2>"+rs.getString("Member_Code")+"</td>");
		out.print("<td><font size=2>"+rs.getString("Access_No")+"</td>");
		out.print("<td><font size=2>"+rs.getString("Title")+"</td>");
		String idate=getdate(rs.getString("Issue_Date"));
	  	out.print("<td><font size=2>"+idate+"</td>");
		String dte=getdate(rs.getString("Due_Date"));
		out.print("<td><font size=2>"+dte+"</td>");
		out.print("<td><font size=2>"+rs.getString("dept_name")+"</td>");
		out.print("<td><font size=2>"+rs.getString("group_name")+"</td>");
		out.print("<td><font size=2>"+rs.getString("course_name")+"</td>");
		//out.print("<td><font size=2>"+rs.getString("Member_Name")+"</td>");
		sno=sno+1;
	}

	out.print("</table>");
	}
	}

	if(report_type.equals("breakup"))
	{

	/* strsqlq="select count(*) as count_book, issue_date from juserstat_all where 1<2 "+strsql+" and issue_type='ISSUE' group by issue_date union select count(*) as count_book, issue_date from jissue_history where 1<2 "+strsql+"  group by issue_date order by issue_date"; */
	strsqlq="select count(*) as count_book, issue_date from juserstat_all where 1<2 "+strsql+" and issue_type='ISSUE' group by issue_date order by issue_date";
	out.print(strsqlq);
	rs=st.executeQuery(strsqlq);
	if(!rs.next())
	{
	out.print("<font face=times new roman size=5 color=blue><center>No Record Found</center></font><br>");
	out.print("<center><input type=button name=click value=back onclick=backward();></center>");

	}
	else
	{
	rs.beforeFirst();
	sno=1;
	out.print("<center><font size=5>ISSUE REPORT</center></font>");
	out.print("<font size=3>Document Type :"+txtdoctype+"</font>");
	out.print("<br><table border=1 width=700 align=center CELLSPACING='0' BORDERCOLOR='#DADADA' BGCOLOR='#F5F5F5'> ");
	out.print("<th>Sno</th><th>IssueDate</th><th>No Of Books</th>");

	while(rs.next())
	{
	 	out.print("<tr><td><font size=2>"+sno+"</td>");
		String idate=getdate(rs.getString("Issue_Date"));
	  	out.print("<td><font size=2>"+idate+"</td>");
		count_book=rs.getString("count_book");
		out.print("<td><font size=2>"+count_book+"</td>");
		count_book=rs.getString("count_book");
		sno=sno+1;
		int x=Integer.parseInt(count_book);
		total=total+x;
	}

	out.print("</table>");
	out.print("<br>");
	out.print("<br>");
	out.print("<br>");
	out.print("<font face=times new roman size=4><center>Total:  "+total+"</center></font>");

	}

	}


	if(report_type.equals("cumulative"))
	{
	/* strsqlq="select distinct(count(*)) as count_book, issue_date from juserstat_all where 1<2 "+strsql+" and issue_type='ISSUE' group by issue_date union select distinct(count(*)) as count_book, issue_date from jissue_history where 1<2 "+strsql+"  group by issue_date order by issue_date"; */
	strsqlq="select distinct(count(*)) as count_book, issue_date from juserstat_all where 1<2 "+strsql+" and issue_type='ISSUE' group by issue_date order by issue_date";
	out.print(strsqlq);
	rs=st.executeQuery(strsqlq);
	if(!rs.next())
	{
	out.print("<font face=times new roman size=5 color=blue><center>No Record Found</center></font><br>");
	out.print("<center><input type=button name=click value=back onclick=backward();></center>");

	}
	else
	{
	rs.beforeFirst();
	sno=1;
	out.print("<center><font size=5>ISSUE REPORT</center></font>");
	out.print("<font size=3>Document Type :"+txtdoctype+"</font>");
	out.print("<br><table border=0 width=700 align=center CELLSPACING='0' BORDERCOLOR='#DADADA' BGCOLOR='#F5F5F5'> ");


	while(rs.next())
	{
		count_book=rs.getString("count_book");

		count_book=rs.getString("count_book");
		sno=sno+1;
		int x=Integer.parseInt(count_book);
		total=total+x;
	}
	out.print("</table>");
        out.print("<br>");
	out.print("<br>");
	out.print("<br>");
	out.print("<font face=times new roman size=4><center>No Of Books:  "+total+"</center></font>");

	}

	}
	}




/*----------------------------BOOK DOC----------------------------------------------------------------------------------------------------------------*/
		if ((txtreporttype.equals("Issue")) && (txtdoctype.equals("BOOK"))){
		if(report_type.equals("listing"))
		{
		strsqlq = "select * from juserstat where 1<2 "+strsql+" and Issue_Type='ISSUE' and doc_type='BOOK' order by  "+order;
		
	out.print(strsqlq);
	rs=st.executeQuery(strsqlq);
	if(!rs.next())
	{
	out.print("<font face=times new roman size=5 color=blue><center>No Record Found</center></font><br>");
	out.print("<center><input type=button name=click value=back onclick=backward();></center>");

	}
	else
	{
	rs.beforeFirst();
	sno=1;

	out.print("<center><font size=5>ISSUE REPORT</center></font>");
	out.print("<font size=3>Document Type :"+txtdoctype+"</font>");
	out.print(" <table border='1' width='258'  CELLSPACING='0' BORDERCOLOR='#DADADA' BGCOLOR='#F5F5F5'>");
	if(!txtmembercode.equals(""))out.print("<tr><td width='111' height='10'>Member Code</td><td width='129' height='10'>"+txtmembercode+"</td></tr>");
	if(!txtdepartment.equals("NO")) out.print("<tr><td width='111' height='10'>Department</td><td width='129' height='10'>"+deptname+"</td></tr>");
	if(!txtfdate.equals(""))  out.print("<tr><td width='111' height='10'>From Date</td><td width='129' height='10'>"+txtfdate+"</td></tr>");
	if(!txttdate.equals(""))  out.print("<tr><td width='111' height='10'>To Date</td><td width='129' height='10'>"+txttdate+"</td> </tr>");
	if(!txtgroup.equals("NO")) out.print("<tr><td width='111' height='10'>Group</td><td width='129' height='10'>"+groupname+"</td></tr>");
	if(!txtcourse.equals("NO")) out.print("<tr><td width='111' height='10'>Course</td><td width='129' height='10'>"+ coursename+"</td></tr>");
	//if(!Reptfrom.equals(""))  out.print("<tr><td width='111' height='10'>Reptfrom</td><td width='129' height='10'>"+Reptfrom+"</td> </tr>");
	out.print("</table>");
	out.print("<br><table border=1 width=700 align=center CELLSPACING='0' BORDERCOLOR='#DADADA' BGCOLOR='#F5F5F5'> ");
	out.print("<th>Sno</th><th>Membercode</th><th>AccessNo</th><th>Title</th><th>IssueDate</th><th>DueDate</th><th>Department</th><th>Group</th><th>Course</th>");

	while(rs.next())
	{
	 	out.print("<tr><td><font size=2>"+sno+"</td>");
		out.print("<td><font size=2>"+rs.getString("Member_Code")+"</td>");
		out.print("<td><font size=2>"+rs.getString("Access_No")+"</td>");
		out.print("<td><font size=2>"+rs.getString("Title")+"</td>");
		String idate=getdate(rs.getString("Issue_Date"));
	  	out.print("<td><font size=2>"+idate+"</td>");
		String dte=getdate(rs.getString("Due_Date"));
		out.print("<td><font size=2>"+dte+"</td>");
		out.print("<td><font size=2>"+rs.getString("dept_name")+"</td>");
		out.print("<td><font size=2>"+rs.getString("group_name")+"</td>");
		out.print("<td><font size=2>"+rs.getString("course_name")+"</td>");
		//out.print("<td><font size=2>"+rs.getString("Member_Name")+"</td>");
		sno=sno+1;
	}
	out.print("</table>");
	}
	}
	if(report_type.equals("breakup"))
	{
	strsqlq="select count(*) as count_book, issue_date from juserstat where 1<2 "+strsql+" and Issue_Type='ISSUE' and doc_type='BOOK' group by issue_date order by issue_date";
	out.print(strsqlq);
	rs=st.executeQuery(strsqlq);
	if(!rs.next())
	{
	out.print("<font face=times new roman size=5 color=blue><center>No Record Found</center></font><br>");
	out.print("<center><input type=button name=click value=back onclick=backward();></center>");

	}
	else
	{
	rs.beforeFirst();
	sno=1;
	out.print("<center><font size=5>ISSUE REPORT</center></font>");
	out.print("<font size=3>Document Type :"+txtdoctype+"</font>");
	out.print("<br><table border=1 width=700 align=center CELLSPACING='0' BORDERCOLOR='#DADADA' BGCOLOR='#F5F5F5'> ");
	out.print("<th>Sno</th><th>IssueDate</th><th>No Of Books</th>");

	while(rs.next())
	{
	 	out.print("<tr><td><font size=2>"+sno+"</td>");
		String idate=getdate(rs.getString("Issue_Date"));
	  	out.print("<td><font size=2>"+idate+"</td>");
		count_book=rs.getString("count_book");
		out.print("<td><font size=2>"+count_book+"</td>");
		count_book=rs.getString("count_book");
		sno=sno+1;
		int x=Integer.parseInt(count_book);
		total=total+x;
	}

	out.print("</table>");
	out.print("<br>");
	out.print("<br>");
	out.print("<br>");
	out.print("<font face=times new roman size=4><center>Total:  "+total+"</center></font>");

	}

	}


	if(report_type.equals("cumulative"))
	{
	strsqlq="select count(*) as count_book, issue_date from juserstat where 1<2 "+strsql+" and Issue_Type='ISSUE' and doc_type='BOOK' group by issue_date order by issue_date";
	out.print(strsqlq);
	rs=st.executeQuery(strsqlq);
	if(!rs.next())
	{
	out.print("<font face=times new roman size=5 color=blue><center>No Record Found</center></font><br>");
	out.print("<center><input type=button name=click value=back onclick=backward();></center>");

	}
	else
	{
	rs.beforeFirst();
	sno=1;
	out.print("<center><font size=5>ISSUE REPORT</center></font>");
	out.print("<font size=3>Document Type :"+txtdoctype+"</font>");
	out.print("<br><table border=0 width=700 align=center CELLSPACING='0' BORDERCOLOR='#DADADA' BGCOLOR='#F5F5F5'> ");


	while(rs.next())
	{
		count_book=rs.getString("count_book");

		count_book=rs.getString("count_book");
		sno=sno+1;
		int x=Integer.parseInt(count_book);
		total=total+x;
	}
	out.print("</table>");
        out.print("<br>");
	out.print("<br>");
	out.print("<br>");
	out.print("<font face=times new roman size=4><center>No Of Books:  "+total+"</center></font>");

	}

	}
	}

/*----------------------------BOOK BANK DOC----------------------------------------------------------------------------------------------------------------*/

if ((txtreporttype.equals("Issue")) && (txtdoctype.equals("BOOK BANK"))){
		if(report_type.equals("listing"))
		{
		strsqlq = "select * from juserstat_bookbank where 1<2 "+strsql+" and Issue_Type='ISSUE' order by  "+order;
	out.print(strsqlq);
	rs=st.executeQuery(strsqlq);
	if(!rs.next())
	{
	out.print("<font face=times new roman size=5 color=blue><center>No Record Found</center></font><br>");
	out.print("<tr><td><center><input type=button name=click value=back onclick=backward();></center></td></tr>");

	}
	else
	{
	rs.beforeFirst();
	sno=1;

	out.print("<center><font size=5>ISSUE REPORT</center></font>");
	out.print("<font size=5>Report Date :"+Today_Date+"</font>");
	out.print(" <table border='1' width='258'  CELLSPACING='0' BORDERCOLOR='#DADADA' BGCOLOR='#F5F5F5'>");
	if(!txtmembercode.equals(""))out.print("<tr><td width='111' height='10'>Member Code</td><td width='129' height='10'>"+txtmembercode+"</td></tr>");
	if(!txtdepartment.equals("NO")) out.print("<tr><td width='111' height='10'>Department</td><td width='129' height='10'>"+deptname+"</td></tr>");
	if(!txtfdate.equals(""))  out.print("<tr><td width='111' height='10'>From Date</td><td width='129' height='10'>"+txtfdate+"</td></tr>");
	if(!txttdate.equals(""))  out.print("<tr><td width='111' height='10'>To Date</td><td width='129' height='10'>"+txttdate+"</td> </tr>");
	if(!txtgroup.equals("NO")) out.print("<tr><td width='111' height='10'>Group</td><td width='129' height='10'>"+groupname+"</td></tr>");
	if(!txtcourse.equals("NO")) out.print("<tr><td width='111' height='10'>Course</td><td width='129' height='10'>"+ coursename+"</td></tr>");
	//if(!Reptfrom.equals(""))  out.print("<tr><td width='111' height='10'>Reptfrom</td><td width='129' height='10'>"+Reptfrom+"</td> </tr>");
	out.print("</table>");
	out.print("<br><table border=1 width=700 align=center CELLSPACING='0' BORDERCOLOR='#DADADA' BGCOLOR='#F5F5F5'> ");
	out.print("<th>Sno</th><th>Membercode</th><th>AccessNo</th><th>Title</th><th>IssueDate</th><th>DueDate</th><th>Department</th><th>Group</th><th>Course</th>");

	while(rs.next())
	{
	 	out.print("<tr><td><font size=2>"+sno+"</td>");
		out.print("<td><font size=2>"+rs.getString("Member_Code")+"</td>");
		out.print("<td><font size=2>"+rs.getString("Access_No")+"</td>");
		out.print("<td><font size=2>"+rs.getString("Title")+"</td>");
		String idate=getdate(rs.getString("Issue_Date"));
	  	out.print("<td><font size=2>"+idate+"</td>");
		String dte=getdate(rs.getString("Due_Date"));
		out.print("<td><font size=2>"+dte+"</td>");
		out.print("<td><font size=2>"+rs.getString("dept_name")+"</td>");
		out.print("<td><font size=2>"+rs.getString("group_name")+"</td>");
		out.print("<td><font size=2>"+rs.getString("course_name")+"</td>");
		//out.print("<td><font size=2>"+rs.getString("Member_Name")+"</td>");
		sno=sno+1;
	}
	out.print("</table>");
	}
	}

if(report_type.equals("breakup"))
	{
	strsqlq="select count(*) as count_book, issue_date from juserstat_bookbank where 1<2 "+strsql+" group by issue_date order by issue_date";
	out.print(strsqlq);
	rs=st.executeQuery(strsqlq);
	if(!rs.next())
	{
	out.print("<font face=times new roman size=5 color=blue><center>No Record Found</center></font><br>");
	out.print("<center><input type=button name=click value=back onclick=backward();></center>");

	}
	else
	{
	rs.beforeFirst();
	sno=1;
	out.print("<center><font size=5>ISSUE REPORT</center></font>");
	out.print("<font size=3>Document Type :"+txtdoctype+"</font>");
	out.print("<br><table border=1 width=700 align=center CELLSPACING='0' BORDERCOLOR='#DADADA' BGCOLOR='#F5F5F5'> ");
	out.print("<th>Sno</th><th>IssueDate</th><th>No Of Books</th>");

	while(rs.next())
	{
	 	out.print("<tr><td><font size=2>"+sno+"</td>");
		String idate=getdate(rs.getString("Issue_Date"));
	  	out.print("<td><font size=2>"+idate+"</td>");
		count_book=rs.getString("count_book");
		out.print("<td><font size=2>"+count_book+"</td>");
		count_book=rs.getString("count_book");
		sno=sno+1;
		int x=Integer.parseInt(count_book);
		total=total+x;
	}

	out.print("</table>");
	out.print("<br>");
	out.print("<br>");
	out.print("<br>");
	out.print("<font face=times new roman size=4><center>Total:  "+total+"</center></font>");

	}

	}


	if(report_type.equals("cumulative"))
	{
	strsqlq="select count(*) as count_book, issue_date from juserstat_bookbank where 1<2 "+strsql+" group by issue_date order by issue_date";
	out.print(strsqlq);
	rs=st.executeQuery(strsqlq);
	if(!rs.next())
	{
	out.print("<font face=times new roman size=5 color=blue><center>No Record Found</center></font><br>");
	out.print("<center><input type=button name=click value=back onclick=backward();></center>");

	}
	else
	{
	rs.beforeFirst();
	sno=1;
	out.print("<center><font size=5>ISSUE REPORT</center></font>");
	out.print("<font size=3>Document Type :"+txtdoctype+"</font>");
	out.print("<br><table border=0 width=700 align=center CELLSPACING='0' BORDERCOLOR='#DADADA' BGCOLOR='#F5F5F5'> ");


	while(rs.next())
	{
		count_book=rs.getString("count_book");

		count_book=rs.getString("count_book");
		sno=sno+1;
		int x=Integer.parseInt(count_book);
		total=total+x;
	}
	out.print("</table>");
        out.print("<br>");
	out.print("<br>");
	out.print("<br>");
	out.print("<font face=times new roman size=4><center>No Of Books:  "+total+"</center></font>");

	}

	}
	}

	/*----------------------------JOURNALS----------------------------------------------------------------------------------------------------------------*/

	if ((txtreporttype.equals("Issue")) && (txtdoctype.equals("JOURNAL"))){
		if(report_type.equals("listing"))
		{
		strsqlq = "select * from juserstat_journal where 1<2 "+strsql+" and Issue_Type='ISSUE' order by  "+order;
	out.print(strsqlq);
	rs=st.executeQuery(strsqlq);
	if(!rs.next())
	{
	out.print("<font face=times new roman size=5 color=blue><center>No Record Found</center></font><br>");
	out.print("<tr><td><center><input type=button name=click value=back onclick=backward();></center></td></tr>");

	}
	else
	{
	rs.beforeFirst();
	sno=1;

	out.print("<center><font size=5>ISSUE REPORT</center></font>");
	out.print("<font size=5>Report Date :"+Today_Date+"</font>");
	out.print(" <table border='1' width='258'  CELLSPACING='0' BORDERCOLOR='#DADADA' BGCOLOR='#F5F5F5'>");
	if(!txtmembercode.equals(""))out.print("<tr><td width='111' height='10'>Member Code</td><td width='129' height='10'>"+txtmembercode+"</td></tr>");
	if(!txtdepartment.equals("NO")) out.print("<tr><td width='111' height='10'>Department</td><td width='129' height='10'>"+deptname+"</td></tr>");
	if(!txtfdate.equals(""))  out.print("<tr><td width='111' height='10'>From Date</td><td width='129' height='10'>"+txtfdate+"</td></tr>");
	if(!txttdate.equals(""))  out.print("<tr><td width='111' height='10'>To Date</td><td width='129' height='10'>"+txttdate+"</td> </tr>");
	if(!txtgroup.equals("NO")) out.print("<tr><td width='111' height='10'>Group</td><td width='129' height='10'>"+groupname+"</td></tr>");
	if(!txtcourse.equals("NO")) out.print("<tr><td width='111' height='10'>Course</td><td width='129' height='10'>"+ coursename+"</td></tr>");
	//if(!Reptfrom.equals(""))  out.print("<tr><td width='111' height='10'>Reptfrom</td><td width='129' height='10'>"+Reptfrom+"</td> </tr>");
	out.print("</table>");
	out.print("<br><table border=1 width=700 align=center CELLSPACING='0' BORDERCOLOR='#DADADA' BGCOLOR='#F5F5F5'> ");
	out.print("<th>Sno</th><th>Membercode</th><th>AccessNo</th><th>Title</th><th>IssueDate</th><th>DueDate</th><th>Department</th><th>Group</th><th>Course</th>");

	while(rs.next())
	{
	 	out.print("<tr><td><font size=2>"+sno+"</td>");
		out.print("<td><font size=2>"+rs.getString("Member_Code")+"</td>");
		out.print("<td><font size=2>"+rs.getString("Access_No")+"</td>");
		out.print("<td><font size=2>"+rs.getString("Title")+"</td>");
		String idate=getdate(rs.getString("Issue_Date"));
	  	out.print("<td><font size=2>"+idate+"</td>");
		String dte=getdate(rs.getString("Due_Date"));
		out.print("<td><font size=2>"+dte+"</td>");
		out.print("<td><font size=2>"+rs.getString("dept_name")+"</td>");
		out.print("<td><font size=2>"+rs.getString("group_name")+"</td>");
		out.print("<td><font size=2>"+rs.getString("course_name")+"</td>");
		//out.print("<td><font size=2>"+rs.getString("Member_Name")+"</td>");
		sno=sno+1;
	}
	out.print("</table>");
	}
	}


	if(report_type.equals("breakup"))
	{
	strsqlq="select count(*) as count_book, issue_date from juserstat_journal where 1<2 "+strsql+" group by issue_date order by issue_date";
	out.print(strsqlq);
	rs=st.executeQuery(strsqlq);
	if(!rs.next())
	{
	out.print("<font face=times new roman size=5 color=blue><center>No Record Found</center></font><br>");
	out.print("<center><input type=button name=click value=back onclick=backward();></center>");

	}
	else
	{
	rs.beforeFirst();
	sno=1;
	out.print("<center><font size=5>ISSUE REPORT</center></font>");
	out.print("<font size=3>Document Type :"+txtdoctype+"</font>");
	out.print("<br><table border=1 width=700 align=center CELLSPACING='0' BORDERCOLOR='#DADADA' BGCOLOR='#F5F5F5'> ");
	out.print("<th>Sno</th><th>IssueDate</th><th>No Of Books</th>");

	while(rs.next())
	{
	 	out.print("<tr><td><font size=2>"+sno+"</td>");
		String idate=getdate(rs.getString("Issue_Date"));
	  	out.print("<td><font size=2>"+idate+"</td>");
		count_book=rs.getString("count_book");
		out.print("<td><font size=2>"+count_book+"</td>");
		count_book=rs.getString("count_book");
		sno=sno+1;
		int x=Integer.parseInt(count_book);
		total=total+x;
	}

	out.print("</table>");
	out.print("<br>");
	out.print("<br>");
	out.print("<br>");
	out.print("<font face=times new roman size=4><center>Total:  "+total+"</center></font>");

	}

	}


	if(report_type.equals("cumulative"))
	{
	strsqlq="select count(*) as count_book, issue_date from juserstat_journal where 1<2 "+strsql+" group by issue_date order by issue_date";
	out.print(strsqlq);
	rs=st.executeQuery(strsqlq);
	if(!rs.next())
	{
	out.print("<font face=times new roman size=5 color=blue><center>No Record Found</center></font><br>");
	out.print("<center><input type=button name=click value=back onclick=backward();></center>");
 
	}
	else
	{
	rs.beforeFirst();
	sno=1;
	out.print("<center><font size=5>ISSUE REPORT</center></font>");
	out.print("<font size=3>Document Type :"+txtdoctype+"</font>");
	out.print("<br><table border=0 width=700 align=center CELLSPACING='0' BORDERCOLOR='#DADADA' BGCOLOR='#F5F5F5'> ");


	while(rs.next())
	{
		count_book=rs.getString("count_book");

		count_book=rs.getString("count_book");
		sno=sno+1;
		int x=Integer.parseInt(count_book);
		total=total+x;
	}
	out.print("</table>");
        out.print("<br>");
	out.print("<br>");
	out.print("<br>");
	out.print("<font face=times new roman size=4><center>No Of Books:  "+total+"</center></font>");

	}

	}
	}

/*----------------------------THESIS----------------------------------------------------------------------------------------------------------------*/

	if ((txtreporttype.equals("Issue")) && (txtdoctype.equals("THESIS"))){
		if(report_type.equals("listing"))
		{
		strsqlq = "select * from juserstat_thesis where 1<2 "+strsql+" and Issue_Type='ISSUE' order by  "+order;
	out.print(strsqlq);
	rs=st.executeQuery(strsqlq);
	if(!rs.next())
	{
	out.print("<font face=times new roman size=5 color=blue><center>No Record Found</center></font><br>");
	out.print("<tr><td><center><input type=button name=click value=back onclick=backward();></center></td></tr>");

	}
	else
	{
	rs.beforeFirst();
	sno=1;

	out.print("<center><font size=5>ISSUE REPORT</center></font>");
	out.print("<font size=5>Report Date :"+Today_Date+"</font>");
	out.print(" <table border='1' width='258'  CELLSPACING='0' BORDERCOLOR='#DADADA' BGCOLOR='#F5F5F5'>");
	if(!txtmembercode.equals(""))out.print("<tr><td width='111' height='10'>Member Code</td><td width='129' height='10'>"+txtmembercode+"</td></tr>");
	if(!txtdepartment.equals("NO")) out.print("<tr><td width='111' height='10'>Department</td><td width='129' height='10'>"+deptname+"</td></tr>");
	if(!txtfdate.equals(""))  out.print("<tr><td width='111' height='10'>From Date</td><td width='129' height='10'>"+txtfdate+"</td></tr>");
	if(!txttdate.equals(""))  out.print("<tr><td width='111' height='10'>To Date</td><td width='129' height='10'>"+txttdate+"</td> </tr>");
	if(!txtgroup.equals("NO")) out.print("<tr><td width='111' height='10'>Group</td><td width='129' height='10'>"+groupname+"</td></tr>");
	if(!txtcourse.equals("NO")) out.print("<tr><td width='111' height='10'>Course</td><td width='129' height='10'>"+ coursename+"</td></tr>");
	//if(!Reptfrom.equals(""))  out.print("<tr><td width='111' height='10'>Reptfrom</td><td width='129' height='10'>"+Reptfrom+"</td> </tr>");
	out.print("</table>");
	out.print("<br><table border=1 width=700 align=center CELLSPACING='0' BORDERCOLOR='#DADADA' BGCOLOR='#F5F5F5'> ");
	out.print("<th>Sno</th><th>Membercode</th><th>AccessNo</th><th>Title</th><th>IssueDate</th><th>DueDate</th><th>Department</th><th>Group</th><th>Course</th>");

	while(rs.next())
	{
	 	out.print("<tr><td><font size=2>"+sno+"</td>");
		out.print("<td><font size=2>"+rs.getString("Member_Code")+"</td>");
		out.print("<td><font size=2>"+rs.getString("Access_No")+"</td>");
		out.print("<td><font size=2>"+rs.getString("Title")+"</td>");
		String idate=getdate(rs.getString("Issue_Date"));
	  	out.print("<td><font size=2>"+idate+"</td>");
		String dte=getdate(rs.getString("Due_Date"));
		out.print("<td><font size=2>"+dte+"</td>");
		out.print("<td><font size=2>"+rs.getString("dept_name")+"</td>");
		out.print("<td><font size=2>"+rs.getString("group_name")+"</td>");
		out.print("<td><font size=2>"+rs.getString("course_name")+"</td>");
		//out.print("<td><font size=2>"+rs.getString("Member_Name")+"</td>");
		sno=sno+1;
	}
	out.print("</table>");
	}
	}

	if(report_type.equals("breakup"))
	{
	strsqlq="select count(*) as count_book, issue_date from juserstat_thesis where 1<2 "+strsql+" group by issue_date order by issue_date";
	out.print(strsqlq);
	rs=st.executeQuery(strsqlq);
	if(!rs.next())
	{
	out.print("<font face=times new roman size=5 color=blue><center>No Record Found</center></font><br>");
	out.print("<center><input type=button name=click value=back onclick=backward();></center>");

	}
	else
	{
	rs.beforeFirst();
	sno=1;
	out.print("<center><font size=5>ISSUE REPORT</center></font>");
	out.print("<font size=3>Document Type :"+txtdoctype+"</font>");
	out.print("<br><table border=1 width=700 align=center CELLSPACING='0' BORDERCOLOR='#DADADA' BGCOLOR='#F5F5F5'> ");
	out.print("<th>Sno</th><th>IssueDate</th><th>No Of Books</th>");

	while(rs.next())
	{
	 	out.print("<tr><td><font size=2>"+sno+"</td>");
		String idate=getdate(rs.getString("Issue_Date"));
	  	out.print("<td><font size=2>"+idate+"</td>");
		count_book=rs.getString("count_book");
		out.print("<td><font size=2>"+count_book+"</td>");
		count_book=rs.getString("count_book");
		sno=sno+1;
		int x=Integer.parseInt(count_book);
		total=total+x;
	}

	out.print("</table>");
	out.print("<br>");
	out.print("<br>");
	out.print("<br>");
	out.print("<font face=times new roman size=4><center>Total:  "+total+"</center></font>");

	}

	}


	if(report_type.equals("cumulative"))
	{
	strsqlq="select count(*) as count_book, issue_date from juserstat_thesis where 1<2 "+strsql+" group by issue_date order by issue_date";
	out.print(strsqlq);
	rs=st.executeQuery(strsqlq);
	if(!rs.next())
	{
	out.print("<font face=times new roman size=5 color=blue><center>No Record Found</center></font><br>");
	out.print("<center><input type=button name=click value=back onclick=backward();></center>");

	}
	else
	{
	rs.beforeFirst();
	sno=1;
	out.print("<center><font size=5>ISSUE REPORT</center></font>");
	out.print("<font size=3>Document Type :"+txtdoctype+"</font>");
	out.print("<br><table border=0 width=700 align=center CELLSPACING='0' BORDERCOLOR='#DADADA' BGCOLOR='#F5F5F5'> ");


	while(rs.next())
	{
		count_book=rs.getString("count_book");

		count_book=rs.getString("count_book");
		sno=sno+1;
		int x=Integer.parseInt(count_book);
		total=total+x;
	}
	out.print("</table>");
        out.print("<br>");
	out.print("<br>");
	out.print("<br>");
	out.print("<font face=times new roman size=4><center>No Of Books:  "+total+"</center></font>");

	}

	}
	}


	/*----------------------------BACK VOLUME----------------------------------------------------------------------------------------------------------------*/

	if ((txtreporttype.equals("Issue")) && (txtdoctype.equals("BACK VOLUME"))){
		if(report_type.equals("listing"))
		{
	strsqlq = "select * from juserstat_backvolume where 1<2 "+strsql+" and Issue_Type='ISSUE' order by  "+order;
	out.print(strsqlq);
	rs=st.executeQuery(strsqlq);
	if(!rs.next())
	{
	out.print("<font face=times new roman size=5 color=blue><center>No Record Found</center></font><br>");
	out.print("<tr><td><center><input type=button name=click value=back onclick=backward();></center></td></tr>");

	}
	else
	{
	rs.beforeFirst();
	sno=1;

	out.print("<center><font size=5>ISSUE REPORT</center></font>");
	out.print("<font size=5>Report Date :"+Today_Date+"</font>");
	out.print(" <table border='1' width='258'  CELLSPACING='0' BORDERCOLOR='#DADADA' BGCOLOR='#F5F5F5'>");
	if(!txtmembercode.equals(""))out.print("<tr><td width='111' height='10'>Member Code</td><td width='129' height='10'>"+txtmembercode+"</td></tr>");
	if(!txtdepartment.equals("NO")) out.print("<tr><td width='111' height='10'>Department</td><td width='129' height='10'>"+deptname+"</td></tr>");
	if(!txtfdate.equals(""))  out.print("<tr><td width='111' height='10'>From Date</td><td width='129' height='10'>"+txtfdate+"</td></tr>");
	if(!txttdate.equals(""))  out.print("<tr><td width='111' height='10'>To Date</td><td width='129' height='10'>"+txttdate+"</td> </tr>");
	if(!txtgroup.equals("NO")) out.print("<tr><td width='111' height='10'>Group</td><td width='129' height='10'>"+groupname+"</td></tr>");
	if(!txtcourse.equals("NO")) out.print("<tr><td width='111' height='10'>Course</td><td width='129' height='10'>"+ coursename+"</td></tr>");
	//if(!Reptfrom.equals(""))  out.print("<tr><td width='111' height='10'>Reptfrom</td><td width='129' height='10'>"+Reptfrom+"</td> </tr>");
	out.print("</table>");
	out.print("<br><table border=1 width=700 align=center CELLSPACING='0' BORDERCOLOR='#DADADA' BGCOLOR='#F5F5F5'> ");
	out.print("<th>Sno</th><th>Membercode</th><th>AccessNo</th><th>Title</th><th>IssueDate</th><th>DueDate</th><th>Department</th><th>Group</th><th>Course</th>");

	while(rs.next())
	{
	 	out.print("<tr><td><font size=2>"+sno+"</td>");
		out.print("<td><font size=2>"+rs.getString("Member_Code")+"</td>");
		out.print("<td><font size=2>"+rs.getString("Access_No")+"</td>");
		out.print("<td><font size=2>"+rs.getString("Title")+"</td>");
		String idate=getdate(rs.getString("Issue_Date"));
	  	out.print("<td><font size=2>"+idate+"</td>");
		String dte=getdate(rs.getString("Due_Date"));
		out.print("<td><font size=2>"+dte+"</td>");
		out.print("<td><font size=2>"+rs.getString("dept_name")+"</td>");
		out.print("<td><font size=2>"+rs.getString("group_name")+"</td>");
		out.print("<td><font size=2>"+rs.getString("course_name")+"</td>");
		//out.print("<td><font size=2>"+rs.getString("Member_Name")+"</td>");
		sno=sno+1;
	}
	out.print("</table>");
	}
	}


	if(report_type.equals("breakup"))
	{
	strsqlq="select count(*) as count_book, issue_date from juserstat_backvolume where 1<2 "+strsql+" group by issue_date order by issue_date";
	out.print(strsqlq);
	rs=st.executeQuery(strsqlq);
	if(!rs.next())
	{
	out.print("<font face=times new roman size=5 color=blue><center>No Record Found</center></font><br>");
	out.print("<center><input type=button name=click value=back onclick=backward();></center>");

	}
	else
	{
	rs.beforeFirst();
	sno=1;
	out.print("<center><font size=5>ISSUE REPORT</center></font>");
	out.print("<font size=3>Document Type :"+txtdoctype+"</font>");
	out.print("<br><table border=1 width=700 align=center CELLSPACING='0' BORDERCOLOR='#DADADA' BGCOLOR='#F5F5F5'> ");
	out.print("<th>Sno</th><th>IssueDate</th><th>No Of Books</th>");

	while(rs.next())
	{
	 	out.print("<tr><td><font size=2>"+sno+"</td>");
		String idate=getdate(rs.getString("Issue_Date"));
	  	out.print("<td><font size=2>"+idate+"</td>");
		count_book=rs.getString("count_book");
		out.print("<td><font size=2>"+count_book+"</td>");
		count_book=rs.getString("count_book");
		sno=sno+1;
		int x=Integer.parseInt(count_book);
		total=total+x;
	}

	out.print("</table>");
	out.print("<br>");
	out.print("<br>");
	out.print("<br>");
	out.print("<font face=times new roman size=4><center>Total:  "+total+"</center></font>");

	}

	}


	if(report_type.equals("cumulative"))
	{
	strsqlq="select count(*) as count_book, issue_date from juserstat_backvolume where 1<2 "+strsql+" group by issue_date order by issue_date";
	out.print(strsqlq);
	rs=st.executeQuery(strsqlq);
	if(!rs.next())
	{
	out.print("<font face=times new roman size=5 color=blue><center>No Record Found</center></font><br>");
	out.print("<center><input type=button name=click value=back onclick=backward();></center>");

	}
	else
	{
	rs.beforeFirst();
	sno=1;
	out.print("<center><font size=5>ISSUE REPORT</center></font>");
	out.print("<font size=3>Document Type :"+txtdoctype+"</font>");
	out.print("<br><table border=0 width=700 align=center CELLSPACING='0' BORDERCOLOR='#DADADA' BGCOLOR='#F5F5F5'> ");


	while(rs.next())
	{
		count_book=rs.getString("count_book");

		count_book=rs.getString("count_book");
		sno=sno+1;
		int x=Integer.parseInt(count_book);
		total=total+x;
	}
	out.print("</table>");
        out.print("<br>");
	out.print("<br>");
	out.print("<br>");
	out.print("<font face=times new roman size=4><center>No Of Books:  "+total+"</center></font>");

	}

	}
	}


	/*----------------------------PROCEEDING----------------------------------------------------------------------------------------------------------------*/

	if ((txtreporttype.equals("Issue")) && (txtdoctype.equals("PROCEEDING"))){
	if(report_type.equals("listing"))
		{
		strsqlq = "select * from juserstat_proceeding where 1<2 "+strsql+" and Issue_Type='ISSUE' order by  "+order;
	out.print(strsqlq);
	rs=st.executeQuery(strsqlq);
	if(!rs.next())
	{
	out.print("<font face=times new roman size=5 color=blue><center>No Record Found</center></font><br>");
	out.print("<tr><td><center><input type=button name=click value=back onclick=backward();></center></td></tr>");

	}
	else
	{
	rs.beforeFirst();
	sno=1;

	out.print("<center><font size=5>ISSUE REPORT</center></font>");
	out.print("<font size=5>Report Date :"+Today_Date+"</font>");
	out.print(" <table border='1' width='258'  CELLSPACING='0' BORDERCOLOR='#DADADA' BGCOLOR='#F5F5F5'>");
	if(!txtmembercode.equals(""))out.print("<tr><td width='111' height='10'>Member Code</td><td width='129' height='10'>"+txtmembercode+"</td></tr>");
	if(!txtdepartment.equals("NO")) out.print("<tr><td width='111' height='10'>Department</td><td width='129' height='10'>"+deptname+"</td></tr>");
	if(!txtfdate.equals(""))  out.print("<tr><td width='111' height='10'>From Date</td><td width='129' height='10'>"+txtfdate+"</td></tr>");
	if(!txttdate.equals(""))  out.print("<tr><td width='111' height='10'>To Date</td><td width='129' height='10'>"+txttdate+"</td> </tr>");
	if(!txtgroup.equals("NO")) out.print("<tr><td width='111' height='10'>Group</td><td width='129' height='10'>"+groupname+"</td></tr>");
	if(!txtcourse.equals("NO")) out.print("<tr><td width='111' height='10'>Course</td><td width='129' height='10'>"+ coursename+"</td></tr>");
	//if(!Reptfrom.equals(""))  out.print("<tr><td width='111' height='10'>Reptfrom</td><td width='129' height='10'>"+Reptfrom+"</td> </tr>");
	out.print("</table>");
	out.print("<br><table border=1 width=700 align=center CELLSPACING='0' BORDERCOLOR='#DADADA' BGCOLOR='#F5F5F5'> ");
	out.print("<th>Sno</th><th>Membercode</th><th>AccessNo</th><th>Title</th><th>IssueDate</th><th>DueDate</th><th>Department</th><th>Group</th><th>Course</th>");

	while(rs.next())
	{
	 	out.print("<tr><td><font size=2>"+sno+"</td>");
		out.print("<td><font size=2>"+rs.getString("Member_Code")+"</td>");
		out.print("<td><font size=2>"+rs.getString("Access_No")+"</td>");
		out.print("<td><font size=2>"+rs.getString("Title")+"</td>");
		String idate=getdate(rs.getString("Issue_Date"));
	  	out.print("<td><font size=2>"+idate+"</td>");
		String dte=getdate(rs.getString("Due_Date"));
		out.print("<td><font size=2>"+dte+"</td>");
		out.print("<td><font size=2>"+rs.getString("dept_name")+"</td>");
		out.print("<td><font size=2>"+rs.getString("group_name")+"</td>");
		out.print("<td><font size=2>"+rs.getString("course_name")+"</td>");
		//out.print("<td><font size=2>"+rs.getString("Member_Name")+"</td>");
		sno=sno+1;
	}
	out.print("</table>");
	}
	}

	if(report_type.equals("breakup"))
	{
	strsqlq="select count(*) as count_book, issue_date from juserstat_proceeding where 1<2 "+strsql+" group by issue_date order by issue_date";
	out.print(strsqlq);
	rs=st.executeQuery(strsqlq);
	if(!rs.next())
	{
	out.print("<font face=times new roman size=5 color=blue><center>No Record Found</center></font><br>");
	out.print("<center><input type=button name=click value=back onclick=backward();></center>");

	}
	else
	{
	rs.beforeFirst();
	sno=1;
	out.print("<center><font size=5>ISSUE REPORT</center></font>");
	out.print("<font size=3>Document Type :"+txtdoctype+"</font>");
	out.print("<br><table border=1 width=700 align=center CELLSPACING='0' BORDERCOLOR='#DADADA' BGCOLOR='#F5F5F5'> ");
	out.print("<th>Sno</th><th>IssueDate</th><th>No Of Books</th>");

	while(rs.next())
	{
	 	out.print("<tr><td><font size=2>"+sno+"</td>");
		String idate=getdate(rs.getString("Issue_Date"));
	  	out.print("<td><font size=2>"+idate+"</td>");
		count_book=rs.getString("count_book");
		out.print("<td><font size=2>"+count_book+"</td>");
		count_book=rs.getString("count_book");
		sno=sno+1;
		int x=Integer.parseInt(count_book);
		total=total+x;
	}

	out.print("</table>");
	out.print("<br>");
	out.print("<br>");
	out.print("<br>");
	out.print("<font face=times new roman size=4><center>Total:  "+total+"</center></font>");

	}

	}


	if(report_type.equals("cumulative"))
	{
	strsqlq="select count(*) as count_book, issue_date from juserstat_proceeding where 1<2 "+strsql+" group by issue_date order by issue_date";
	out.print(strsqlq);
	rs=st.executeQuery(strsqlq);
	if(!rs.next())
	{
	out.print("<font face=times new roman size=5 color=blue><center>No Record Found</center></font><br>");
	out.print("<center><input type=button name=click value=back onclick=backward();></center>");

	}
	else
	{
	rs.beforeFirst();
	sno=1;
	out.print("<center><font size=5>ISSUE REPORT</center></font>");
	out.print("<font size=3>Document Type :"+txtdoctype+"</font>");
	out.print("<br><table border=0 width=700 align=center CELLSPACING='0' BORDERCOLOR='#DADADA' BGCOLOR='#F5F5F5'> ");


	while(rs.next())
	{
		count_book=rs.getString("count_book");

		count_book=rs.getString("count_book");
		sno=sno+1;
		int x=Integer.parseInt(count_book);
		total=total+x;
	}
	out.print("</table>");
        out.print("<br>");
	out.print("<br>");
	out.print("<br>");
	out.print("<font face=times new roman size=4><center>No Of Books:  "+total+"</center></font>");

	}

	}
	}


	/*----------------------------REPORTS----------------------------------------------------------------------------------------------------------------*/

	if ((txtreporttype.equals("Issue")) && (txtdoctype.equals("REPORT"))){
	if(report_type.equals("listing"))
		{
		strsqlq = "select * from juserstat_report where 1<2 "+strsql+" and Issue_Type='ISSUE' order by  "+order;
	out.print(strsqlq);
	rs=st.executeQuery(strsqlq);
	if(!rs.next())
	{
	out.print("<font face=times new roman size=5 color=blue><center>No Record Found</center></font><br>");
	out.print("<tr><td><center><input type=button name=click value=back onclick=backward();></center></td></tr>");

	}
	else
	{
	rs.beforeFirst();
	sno=1;

	out.print("<center><font size=5>ISSUE REPORT</center></font>");
	out.print("<font size=5>Report Date :"+Today_Date+"</font>");
	out.print(" <table border='1' width='258'  CELLSPACING='0' BORDERCOLOR='#DADADA' BGCOLOR='#F5F5F5'>");
	if(!txtmembercode.equals(""))out.print("<tr><td width='111' height='10'>Member Code</td><td width='129' height='10'>"+txtmembercode+"</td></tr>");
	if(!txtdepartment.equals("NO")) out.print("<tr><td width='111' height='10'>Department</td><td width='129' height='10'>"+deptname+"</td></tr>");
	if(!txtfdate.equals(""))  out.print("<tr><td width='111' height='10'>From Date</td><td width='129' height='10'>"+txtfdate+"</td></tr>");
	if(!txttdate.equals(""))  out.print("<tr><td width='111' height='10'>To Date</td><td width='129' height='10'>"+txttdate+"</td> </tr>");
	if(!txtgroup.equals("NO")) out.print("<tr><td width='111' height='10'>Group</td><td width='129' height='10'>"+groupname+"</td></tr>");
	if(!txtcourse.equals("NO")) out.print("<tr><td width='111' height='10'>Course</td><td width='129' height='10'>"+ coursename+"</td></tr>");
	//if(!Reptfrom.equals(""))  out.print("<tr><td width='111' height='10'>Reptfrom</td><td width='129' height='10'>"+Reptfrom+"</td> </tr>");
	out.print("</table>");
	out.print("<br><table border=1 width=700 align=center CELLSPACING='0' BORDERCOLOR='#DADADA' BGCOLOR='#F5F5F5'> ");
	out.print("<th>Sno</th><th>Membercode</th><th>AccessNo</th><th>Title</th><th>IssueDate</th><th>DueDate</th><th>Department</th><th>Group</th><th>Course</th>");

	while(rs.next())
	{
	 	out.print("<tr><td><font size=2>"+sno+"</td>");
		out.print("<td><font size=2>"+rs.getString("Member_Code")+"</td>");
		out.print("<td><font size=2>"+rs.getString("Access_No")+"</td>");
		out.print("<td><font size=2>"+rs.getString("Title")+"</td>");
		String idate=getdate(rs.getString("Issue_Date"));
	  	out.print("<td><font size=2>"+idate+"</td>");
		String dte=getdate(rs.getString("Due_Date"));
		out.print("<td><font size=2>"+dte+"</td>");
		out.print("<td><font size=2>"+rs.getString("dept_name")+"</td>");
		out.print("<td><font size=2>"+rs.getString("group_name")+"</td>");
		out.print("<td><font size=2>"+rs.getString("course_name")+"</td>");
		//out.print("<td><font size=2>"+rs.getString("Member_Name")+"</td>");
		sno=sno+1;
	}
	out.print("</table>");
	}
	}

	if(report_type.equals("breakup"))
	{
	strsqlq="select count(*) as count_book, issue_date from juserstat_report where 1<2 "+strsql+" group by issue_date order by issue_date";
	out.print(strsqlq);
	rs=st.executeQuery(strsqlq);
	if(!rs.next())
	{
	out.print("<font face=times new roman size=5 color=blue><center>No Record Found</center></font><br>");
	out.print("<center><input type=button name=click value=back onclick=backward();></center>");

	}
	else
	{
	rs.beforeFirst();
	sno=1;
	out.print("<center><font size=5>ISSUE REPORT</center></font>");
	out.print("<font size=3>Document Type :"+txtdoctype+"</font>");
	out.print("<br><table border=1 width=700 align=center CELLSPACING='0' BORDERCOLOR='#DADADA' BGCOLOR='#F5F5F5'> ");
	out.print("<th>Sno</th><th>IssueDate</th><th>No Of Books</th>");

	while(rs.next())
	{
	 	out.print("<tr><td><font size=2>"+sno+"</td>");
		String idate=getdate(rs.getString("Issue_Date"));
	  	out.print("<td><font size=2>"+idate+"</td>");
		count_book=rs.getString("count_book");
		out.print("<td><font size=2>"+count_book+"</td>");
		count_book=rs.getString("count_book");
		sno=sno+1;
		int x=Integer.parseInt(count_book);
		total=total+x;
	}

	out.print("</table>");
	out.print("<br>");
	out.print("<br>");
	out.print("<br>");
	out.print("<font face=times new roman size=4><center>Total:  "+total+"</center></font>");

	}

	}


	if(report_type.equals("cumulative"))
	{
	strsqlq="select count(*) as count_book, issue_date from juserstat_report where 1<2 "+strsql+" group by issue_date order by issue_date";
	out.print(strsqlq);
	rs=st.executeQuery(strsqlq);
	if(!rs.next())
	{
	out.print("<font face=times new roman size=5 color=blue><center>No Record Found</center></font><br>");
	out.print("<center><input type=button name=click value=back onclick=backward();></center>");

	}
	else
	{
	rs.beforeFirst();
	sno=1;
	out.print("<center><font size=5>ISSUE REPORT</center></font>");
	out.print("<font size=3>Document Type :"+txtdoctype+"</font>");
	out.print("<br><table border=0 width=700 align=center CELLSPACING='0' BORDERCOLOR='#DADADA' BGCOLOR='#F5F5F5'> ");


	while(rs.next())
	{
		count_book=rs.getString("count_book");

		count_book=rs.getString("count_book");
		sno=sno+1;
		int x=Integer.parseInt(count_book);
		total=total+x;
	}
	out.print("</table>");
        out.print("<br>");
	out.print("<br>");
	out.print("<br>");
	out.print("<font face=times new roman size=4><center>No Of Books:  "+total+"</center></font>");

	}

	}
	}


	/*----------------------------NON BOOK----------------------------------------------------------------------------------------------------------------*/

	if ((txtreporttype.equals("Issue")) && (txtdoctype.equals("NON BOOK"))){
	if(report_type.equals("listing"))
		{

		strsqlq = "select * from juserstat_nonbook where 1<2 "+strsql+" and Issue_Type='ISSUE' order by  "+order;
	out.print(strsqlq);
	rs=st.executeQuery(strsqlq);
	if(!rs.next())
	{
	out.print("<font face=times new roman size=5 color=blue><center>No Record Found</center></font><br>");
	out.print("<tr><td><center><input type=button name=click value=back onclick=backward();></center></td></tr>");

	}
	else
	{
	rs.beforeFirst();
	sno=1;

	out.print("<center><font size=5>ISSUE REPORT</center></font>");
	out.print("<font size=5>Report Date :"+Today_Date+"</font>");
	out.print(" <table border='1' width='258'  CELLSPACING='0' BORDERCOLOR='#DADADA' BGCOLOR='#F5F5F5'>");
	if(!txtmembercode.equals(""))out.print("<tr><td width='111' height='10'>Member Code</td><td width='129' height='10'>"+txtmembercode+"</td></tr>");
	if(!txtdepartment.equals("NO")) out.print("<tr><td width='111' height='10'>Department</td><td width='129' height='10'>"+deptname+"</td></tr>");
	if(!txtfdate.equals(""))  out.print("<tr><td width='111' height='10'>From Date</td><td width='129' height='10'>"+txtfdate+"</td></tr>");
	if(!txttdate.equals(""))  out.print("<tr><td width='111' height='10'>To Date</td><td width='129' height='10'>"+txttdate+"</td> </tr>");
	if(!txtgroup.equals("NO")) out.print("<tr><td width='111' height='10'>Group</td><td width='129' height='10'>"+groupname+"</td></tr>");
	if(!txtcourse.equals("NO")) out.print("<tr><td width='111' height='10'>Course</td><td width='129' height='10'>"+ coursename+"</td></tr>");
	//if(!Reptfrom.equals(""))  out.print("<tr><td width='111' height='10'>Reptfrom</td><td width='129' height='10'>"+Reptfrom+"</td> </tr>");
	out.print("</table>");
	out.print("<br><table border=1 width=700 align=center CELLSPACING='0' BORDERCOLOR='#DADADA' BGCOLOR='#F5F5F5'> ");
	out.print("<th>Sno</th><th>Membercode</th><th>AccessNo</th><th>Title</th><th>IssueDate</th><th>DueDate</th><th>Department</th><th>Group</th><th>Course</th>");

	while(rs.next())
	{
	 	out.print("<tr><td><font size=2>"+sno+"</td>");
		out.print("<td><font size=2>"+rs.getString("Member_Code")+"</td>");
		out.print("<td><font size=2>"+rs.getString("Access_No")+"</td>");
		out.print("<td><font size=2>"+rs.getString("Title")+"</td>");
		String idate=getdate(rs.getString("Issue_Date"));
	  	out.print("<td><font size=2>"+idate+"</td>");
		String dte=getdate(rs.getString("Due_Date"));
		out.print("<td><font size=2>"+dte+"</td>");
		out.print("<td><font size=2>"+rs.getString("dept_name")+"</td>");
		out.print("<td><font size=2>"+rs.getString("group_name")+"</td>");
		out.print("<td><font size=2>"+rs.getString("course_name")+"</td>");
		//out.print("<td><font size=2>"+rs.getString("Member_Name")+"</td>");
		sno=sno+1;
	}
	out.print("</table>");
	}
	}

	if(report_type.equals("breakup"))
	{
	strsqlq="select count(*) as count_book, issue_date from juserstat_nonbook where 1<2 "+strsql+" group by issue_date order by issue_date";
	out.print(strsqlq);
	rs=st.executeQuery(strsqlq);
	if(!rs.next())
	{
	out.print("<font face=times new roman size=5 color=blue><center>No Record Found</center></font><br>");
	out.print("<center><input type=button name=click value=back onclick=backward();></center>");

	}
	else
	{
	rs.beforeFirst();
	sno=1;
	out.print("<center><font size=5>ISSUE REPORT</center></font>");
	out.print("<font size=3>Document Type :"+txtdoctype+"</font>");
	out.print("<br><table border=1 width=700 align=center CELLSPACING='0' BORDERCOLOR='#DADADA' BGCOLOR='#F5F5F5'> ");
	out.print("<th>Sno</th><th>IssueDate</th><th>No Of Books</th>");

	while(rs.next())
	{
	 	out.print("<tr><td><font size=2>"+sno+"</td>");
		String idate=getdate(rs.getString("Issue_Date"));
	  	out.print("<td><font size=2>"+idate+"</td>");
		count_book=rs.getString("count_book");
		out.print("<td><font size=2>"+count_book+"</td>");
		count_book=rs.getString("count_book");
		sno=sno+1;
		int x=Integer.parseInt(count_book);
		total=total+x;
	}

	out.print("</table>");
	out.print("<br>");
	out.print("<br>");
	out.print("<br>");
	out.print("<font face=times new roman size=4><center>Total:  "+total+"</center></font>");

	}

	}


	if(report_type.equals("cumulative"))
	{
	strsqlq="select count(*) as count_book, issue_date from juserstat_nonbook where 1<2 "+strsql+" group by issue_date order by issue_date";
	out.print(strsqlq);
	rs=st.executeQuery(strsqlq);
	if(!rs.next())
	{
	out.print("<font face=times new roman size=5 color=blue><center>No Record Found</center></font><br>");
	out.print("<center><input type=button name=click value=back onclick=backward();></center>");

	}
	else
	{
	rs.beforeFirst();
	sno=1;
	out.print("<center><font size=5>ISSUE REPORT</center></font>");
	out.print("<font size=3>Document Type :"+txtdoctype+"</font>");
	out.print("<br><table border=0 width=700 align=center CELLSPACING='0' BORDERCOLOR='#DADADA' BGCOLOR='#F5F5F5'> ");


	while(rs.next())
	{
		count_book=rs.getString("count_book");

		count_book=rs.getString("count_book");
		sno=sno+1;
		int x=Integer.parseInt(count_book);
		total=total+x;
	}
	out.print("</table>");
        out.print("<br>");
	out.print("<br>");
	out.print("<br>");
	out.print("<font face=times new roman size=4><center>No Of Books:  "+total+"</center></font>");

	}

	}
	}

/*----------------------------STANDARD ----------------------------------------------------------------------------------------------------------------*/

	if ((txtreporttype.equals("Issue")) && (txtdoctype.equals("STANDARD"))){
	if(report_type.equals("listing"))
		{

		strsqlq = "select * from juserstat_standard where 1<2 "+strsql+" and Issue_Type='ISSUE' order by  "+order;
	out.print(strsqlq);
	rs=st.executeQuery(strsqlq);
	if(!rs.next())
	{
	out.print("<font face=times new roman size=5 color=blue><center>No Record Found</center></font><br>");
	out.print("<tr><td><center><input type=button name=click value=back onclick=backward();></center></td></tr>");

	}
	else
	{
	rs.beforeFirst();
	sno=1;

	out.print("<center><font size=5>ISSUE REPORT</center></font>");
	out.print("<font size=5>Report Date :"+Today_Date+"</font>");
	out.print(" <table border='1' width='258'  CELLSPACING='0' BORDERCOLOR='#DADADA' BGCOLOR='#F5F5F5'>");
	if(!txtmembercode.equals(""))out.print("<tr><td width='111' height='10'>Member Code</td><td width='129' height='10'>"+txtmembercode+"</td></tr>");
	if(!txtdepartment.equals("NO")) out.print("<tr><td width='111' height='10'>Department</td><td width='129' height='10'>"+deptname+"</td></tr>");
	if(!txtfdate.equals(""))  out.print("<tr><td width='111' height='10'>From Date</td><td width='129' height='10'>"+txtfdate+"</td></tr>");
	if(!txttdate.equals(""))  out.print("<tr><td width='111' height='10'>To Date</td><td width='129' height='10'>"+txttdate+"</td> </tr>");
	if(!txtgroup.equals("NO")) out.print("<tr><td width='111' height='10'>Group</td><td width='129' height='10'>"+groupname+"</td></tr>");
	if(!txtcourse.equals("NO")) out.print("<tr><td width='111' height='10'>Course</td><td width='129' height='10'>"+ coursename+"</td></tr>");
	//if(!Reptfrom.equals(""))  out.print("<tr><td width='111' height='10'>Reptfrom</td><td width='129' height='10'>"+Reptfrom+"</td> </tr>");
	out.print("</table>");
	out.print("<br><table border=1 width=700 align=center CELLSPACING='0' BORDERCOLOR='#DADADA' BGCOLOR='#F5F5F5'> ");
	out.print("<th>Sno</th><th>Membercode</th><th>AccessNo</th><th>Title</th><th>IssueDate</th><th>DueDate</th><th>Department</th><th>Group</th><th>Course</th>");

	while(rs.next())
	{
	 	out.print("<tr><td><font size=2>"+sno+"</td>");
		out.print("<td><font size=2>"+rs.getString("Member_Code")+"</td>");
		out.print("<td><font size=2>"+rs.getString("Access_No")+"</td>");
		out.print("<td><font size=2>"+rs.getString("Title")+"</td>");
		String idate=getdate(rs.getString("Issue_Date"));
	  	out.print("<td><font size=2>"+idate+"</td>");
		String dte=getdate(rs.getString("Due_Date"));
		out.print("<td><font size=2>"+dte+"</td>");
		out.print("<td><font size=2>"+rs.getString("dept_name")+"</td>");
		out.print("<td><font size=2>"+rs.getString("group_name")+"</td>");
		out.print("<td><font size=2>"+rs.getString("course_name")+"</td>");
		//out.print("<td><font size=2>"+rs.getString("Member_Name")+"</td>");
		sno=sno+1;
	}
	out.print("</table>");
	}
	}

	if(report_type.equals("breakup"))
	{
	strsqlq="select count(*) as count_book, issue_date from juserstat_standard where 1<2 "+strsql+" group by issue_date order by issue_date";
	out.print(strsqlq);
	rs=st.executeQuery(strsqlq);
	if(!rs.next())
	{
	out.print("<font face=times new roman size=5 color=blue><center>No Record Found</center></font><br>");
	out.print("<center><input type=button name=click value=back onclick=backward();></center>");

	}
	else
	{
	rs.beforeFirst();
	sno=1;
	out.print("<center><font size=5>ISSUE REPORT</center></font>");
	out.print("<font size=3>Document Type :"+txtdoctype+"</font>");
	out.print("<br><table border=1 width=700 align=center CELLSPACING='0' BORDERCOLOR='#DADADA' BGCOLOR='#F5F5F5'> ");
	out.print("<th>Sno</th><th>IssueDate</th><th>No Of Books</th>");

	while(rs.next())
	{
	 	out.print("<tr><td><font size=2>"+sno+"</td>");
		String idate=getdate(rs.getString("Issue_Date"));
	  	out.print("<td><font size=2>"+idate+"</td>");
		count_book=rs.getString("count_book");
		out.print("<td><font size=2>"+count_book+"</td>");
		count_book=rs.getString("count_book");
		sno=sno+1;
		int x=Integer.parseInt(count_book);
		total=total+x;
	}

	out.print("</table>");
	out.print("<br>");
	out.print("<br>");
	out.print("<br>");
	out.print("<font face=times new roman size=4><center>Total:  "+total+"</center></font>");

	}

	}


	if(report_type.equals("cumulative"))
	{
	strsqlq="select count(*) as count_book, issue_date from juserstat_standard where 1<2 "+strsql+" group by issue_date order by issue_date";
	out.print(strsqlq);
	rs=st.executeQuery(strsqlq);
	if(!rs.next())
	{
	out.print("<font face=times new roman size=5 color=blue><center>No Record Found</center></font><br>");
	out.print("<center><input type=button name=click value=back onclick=backward();></center>");

	}
	else
	{
	rs.beforeFirst();
	sno=1;
	out.print("<center><font size=5>ISSUE REPORT</center></font>");
	out.print("<font size=3>Document Type :"+txtdoctype+"</font>");
	out.print("<br><table border=0 width=700 align=center CELLSPACING='0' BORDERCOLOR='#DADADA' BGCOLOR='#F5F5F5'> ");


	while(rs.next())
	{
		count_book=rs.getString("count_book");

		count_book=rs.getString("count_book");
		sno=sno+1;
		int x=Integer.parseInt(count_book);
		total=total+x;
	}
	out.print("</table>");
        out.print("<br>");
	out.print("<br>");
	out.print("<br>");
	out.print("<font face=times new roman size=4><center>No Of Books:  "+total+"</center></font>");

	}

	}
	}



/*----------------------------RETURN REPORTS----------------------------------------------------------------------------------------------------------------*/

/*--------------------------DOC TYPE FOR ALL----------------------------*/

	if ((txtreporttype.equals("Return")) && (txtdoctype.equals("ALL")))
	{
	%>
	<jsp:forward page="return.jsp" />
	<%
	}

/*----------------------------BOOK DOC----------------------------------------------------------------------------------------------------------------*/
		if ((txtreporttype.equals("Return")) && (txtdoctype.equals("BOOK")))
		{
		%>
		<jsp:forward page="return.jsp" />
		<%
		}



	/*----------------------------BOOK BANK DOC----------------------------------------------------------------------------------------------------------------*/
		if ((txtreporttype.equals("Return")) && (txtdoctype.equals("BOOK BANK")))
		{
		%>
		<jsp:forward page="return.jsp" />
		<%
		}




	/*----------------------------JOURNALS DOC----------------------------------------------------------------------------------------------------------------*/
		if ((txtreporttype.equals("Return")) && (txtdoctype.equals("JOURNAL")))
		{
		%>
		<jsp:forward page="return.jsp" />
		<%
		}



/*----------------------------THESIS DOC----------------------------------------------------------------------------------------------------------------*/
		if ((txtreporttype.equals("Return")) && (txtdoctype.equals("THESIS")))
		{
		%>
		<jsp:forward page="return.jsp" />
		<%
		}



/*----------------------------BACK VOLUME----------------------------------------------------------------------------------------------------------------*/
		if ((txtreporttype.equals("Return")) && (txtdoctype.equals("BACK VOLUME")))
		{
		%>
		<jsp:forward page="return.jsp" />
		<%
		}






	/*----------------------------PROCEEDING----------------------------------------------------------------------------------------------------------------*/
		if ((txtreporttype.equals("Return")) && (txtdoctype.equals("PROCEEDING")))
		{
		%>
		<jsp:forward page="return.jsp" />
		<%
		}


	/*----------------------------REPORTS----------------------------------------------------------------------------------------------------------------*/
		if ((txtreporttype.equals("Return")) && (txtdoctype.equals("REPORT")))
		{
		%>
		<jsp:forward page="return.jsp" />
		<%
		}



	/*----------------------------NON BOOK----------------------------------------------------------------------------------------------------------------*/
		if ((txtreporttype.equals("Return")) && (txtdoctype.equals("NON BOOK")))
		{
		%>
		<jsp:forward page="return.jsp" />
		<%
		}


/*----------------------------STANDARD----------------------------------------------------------------------------------------------------------------*/
		if ((txtreporttype.equals("Return")) && (txtdoctype.equals("STANDARD")))
		{
		%>
		<jsp:forward page="return.jsp" />
		<%
		}


// FOR RENEWAL ALL

	if ((txtreporttype.equals("Renewal")) && (txtdoctype.equals("ALL")))
	{
	%>
	<jsp:forward page="renewal.jsp" />
	<%
	}

/*---------------------------- RENEWAL  REPORT FOR BOOK----------------------------------------------------------------------------------------------------------------*/
		if ((txtreporttype.equals("Renewal")) && (txtdoctype.equals("BOOK")))
		{
		%>
		<jsp:forward page="renewal.jsp" />
		<%
		}

/*---------------------------- RENEWAL  REPORT FOR THESIS----------------------------------------------------------------------------------------------------------------*/
		if ((txtreporttype.equals("Renewal")) && (txtdoctype.equals("THESIS")))
		{
		%>
		<jsp:forward page="renewal.jsp" />
		<%
		}

/*---------------------------- RENEWAL  REPORT FOR BACK VOLUME----------------------------------------------------------------------------------------------------------------*/
		if ((txtreporttype.equals("Renewal")) && (txtdoctype.equals("BACK VOLUME")))
		{
		%>
		<jsp:forward page="renewal.jsp" />
		<%
		}


/*---------------------------- RENEWAL  REPORT FOR PROCEEDING----------------------------------------------------------------------------------------------------------------*/
		if ((txtreporttype.equals("Renewal")) && (txtdoctype.equals("PROCEEDING")))
		{
		%>
		<jsp:forward page="renewal.jsp" />
		<%
		}


/*---------------------------- RENEWAL  REPORT FOR REPORTS----------------------------------------------------------------------------------------------------------------*/
		if ((txtreporttype.equals("Renewal")) && (txtdoctype.equals("REPORT")))
		{
		%>
		<jsp:forward page="renewal.jsp" />
		<%
		}

/*---------------------------- RENEWAL  REPORT FOR NON BOOKS----------------------------------------------------------------------------------------------------------------*/
		if ((txtreporttype.equals("Renewal")) && (txtdoctype.equals("NON BOOK")))
		{
		%>
		<jsp:forward page="renewal.jsp" />
		<%
		}


/*---------------------------- RENEWAL  REPORT FOR STANDARD----------------------------------------------------------------------------------------------------------------*/
		if ((txtreporttype.equals("Renewal")) && (txtdoctype.equals("STANDARD")))
		{
		%>
		<jsp:forward page="renewal.jsp" />
		<%
		}






// CODE FOR RESERVE


  	if ((txtreporttype.equals("Reserve")) && (txtdoctype.equals("ALL")))
  	{
	/* if(order1.equals(Issue_Date) && order2.equals(Access_No) && order3.equals(Access_No))
	{
	%>
	<script>
	alert(Invalid Order);
	</script>
	<%
	} */
	%>
	<script>
	alert("<%=order1%>");
	alert("<%=order2%>");
	alert("<%=order3%>");
	</script>
	<%
	%>
	<jsp:forward page="reserve.jsp" />
	<%
	}


/*---------------------------- RESERVE BOOK----------------------------------------------------------------------------------------------------------------*/

	if ((txtreporttype.equals("Reserve")) && (txtdoctype.equals("BOOK")))
	{
	%>
	<jsp:forward page="reserve.jsp" />
	<%
	}


/*---------------------------- RESERVE THESIS----------------------------------------------------------------------------------------------------------------*/

	if ((txtreporttype.equals("Reserve")) && (txtdoctype.equals("THESIS")))
	{
	%>
	<jsp:forward page="reserve.jsp" />
	<%
	}


/*---------------------------- RESERVE BACK VOLUME----------------------------------------------------------------------------------------------------------------*/

	if ((txtreporttype.equals("Reserve")) && (txtdoctype.equals("BACK VOLUME")))
	{
	%>
	<jsp:forward page="reserve.jsp" />
	<%
	}


/*---------------------------- RESERVE PROCEEDING----------------------------------------------------------------------------------------------------------------*/

	if ((txtreporttype.equals("Reserve")) && (txtdoctype.equals("PROCEEDING")))
	{
	%>
	<jsp:forward page="reserve.jsp" />
	<%
	}



/*---------------------------- RESERVE REPORTS----------------------------------------------------------------------------------------------------------------*/

	if ((txtreporttype.equals("Reserve")) && (txtdoctype.equals("REPORT")))
	{
	%>
	<jsp:forward page="reserve.jsp" />
	<%
	}

/*---------------------------- RESERVE NON BOOKS----------------------------------------------------------------------------------------------------------------*/

	if ((txtreporttype.equals("Reserve")) && (txtdoctype.equals("NON BOOK")))
	{
	%>
	<jsp:forward page="reserve.jsp" />
	<%
	}

/*---------------------------- RESERVE STANDARD----------------------------------------------------------------------------------------------------------------*/

	if ((txtreporttype.equals("Reserve")) && (txtdoctype.equals("STANDARD")))
	{
	%>
	<jsp:forward page="reserve.jsp" />
	<%
	}


//RESERVATION REMAINDER FOR ALL


	if ((txtreporttype.equals("Resreminder")) && (txtdoctype.equals("ALL")))
	{
	%>
	<jsp:forward page="resremaind.jsp" />
	<%
	}

//RESERVATION REMAINDER FOR BOOK


	if ((txtreporttype.equals("Resreminder")) && (txtdoctype.equals("BOOK")))
	{
	%>
	<jsp:forward page="resremaind.jsp" />
	<%
	}


//RESERVATION REMAINDER FOR THESIS


	if ((txtreporttype.equals("Resreminder")) && (txtdoctype.equals("THESIS")))
	{
	%>
	<jsp:forward page="resremaind.jsp" />
	<%
	}

//RESERVATION REMAINDER FOR BACK VOLUME


	if ((txtreporttype.equals("Resreminder")) && (txtdoctype.equals("BACK VOLUME")))
	{
	%>
	<jsp:forward page="resremaind.jsp" />
	<%
	}

//RESERVATION REMAINDER FOR PROCEEDING


	if ((txtreporttype.equals("Resreminder")) && (txtdoctype.equals("PROCEEDING")))
	{
	%>
	<jsp:forward page="resremaind.jsp" />
	<%
	}


//RESERVATION REMAINDER FOR REPORT


	if ((txtreporttype.equals("Resreminder")) && (txtdoctype.equals("REPORT")))
	{
	%>
	<jsp:forward page="resremaind.jsp" />
	<%
	}

//RESERVATION REMAINDER FOR NON BOOK


	if ((txtreporttype.equals("Resreminder")) && (txtdoctype.equals("NON BOOK")))
	{
	%>
	<jsp:forward page="resremaind.jsp" />
	<%
	}

//RESERVATION REMAINDER FOR STANDARD

	if ((txtreporttype.equals("Resreminder")) && (txtdoctype.equals("STANDARD")))
	{
	%>
	<jsp:forward page="resremaind.jsp" />
	<%
	}

	// CODE FOR DUE REMAINDER ALL


	if ((txtreporttype.equals("Duereminder")) && (txtdoctype.equals("ALL")))
	{
	%>
	<jsp:forward page="dueremaind.jsp" />
	<%
	}


	// CODE FOR DUE REMAINDER BOOK


	if ((txtreporttype.equals("Duereminder")) && (txtdoctype.equals("BOOK")))
	{
	%>
	<jsp:forward page="dueremaind.jsp" />
	<%
	}

	// CODE FOR DUE REMAINDER THESIS


	if ((txtreporttype.equals("Duereminder")) && (txtdoctype.equals("THESIS")))
	{
	%>
	<jsp:forward page="dueremaind.jsp" />
	<%
	}

	// CODE FOR DUE REMAINDER BACK VOLUME


	if ((txtreporttype.equals("Duereminder")) && (txtdoctype.equals("BACK VOLUME")))
	{
	%>
	<jsp:forward page="dueremaind.jsp" />
	<%
	}

	// CODE FOR DUE REMAINDER PROCEEDING


	if ((txtreporttype.equals("Duereminder")) && (txtdoctype.equals("PROCEEDING")))
	{
	%>
	<jsp:forward page="dueremaind.jsp" />
	<%
	}

	// CODE FOR DUE REMAINDER REPORTS


	if ((txtreporttype.equals("Duereminder")) && (txtdoctype.equals("REPORT")))
	{
	%>
	<jsp:forward page="dueremaind.jsp" />
	<%
	}

	// CODE FOR DUE REMAINDER NON BOOK


	if ((txtreporttype.equals("Duereminder")) && (txtdoctype.equals("NON BOOK")))
	{
	%>
	<jsp:forward page="dueremaind.jsp" />
	<%
	}

	// CODE FOR DUE REMAINDER STANDARD


	if ((txtreporttype.equals("Duereminder")) && (txtdoctype.equals("STANDARD")))
	{
	%>
	<jsp:forward page="dueremaind.jsp" />
	<%
	}

	
	//FINE FOR ALL


	if ((txtreporttype.equals("Fine")) && (txtdoctype.equals("ALL")))
	{
	%>
	<jsp:forward page="fine.jsp" />
	<%
	}


	//FINE FOR BOOK

	if ((txtreporttype.equals("Fine")) && (txtdoctype.equals("BOOK")))
	{
	%>
	<jsp:forward page="fine.jsp" />
	<%
	}

	//FINE FOR THESIS

	if ((txtreporttype.equals("Fine")) && (txtdoctype.equals("THESIS")))
	{
	%>
	<jsp:forward page="fine.jsp" />
	<%
	}



	//FINE FOR REPORT

	if ((txtreporttype.equals("Fine")) && (txtdoctype.equals("REPORT")))
	{
	%>
	<jsp:forward page="fine.jsp" />
	<%
	}

	//FINE FOR BACK VOLUME

	if ((txtreporttype.equals("Fine")) && (txtdoctype.equals("BACK VOLUME")))
	{
	%>
	<jsp:forward page="fine.jsp" />
	<%
	}

	//FINE FOR PROCEEDING

	if ((txtreporttype.equals("Fine")) && (txtdoctype.equals("PROCEEDING")))
	{
	%>
	<jsp:forward page="fine.jsp" />
	<%
	}

	//FINE FOR NON BOOK

	if ((txtreporttype.equals("Fine")) && (txtdoctype.equals("NON BOOK")))
	{
	%>
	<jsp:forward page="fine.jsp" />
	<%
	}

	//FINE FOR STANDARD

	if ((txtreporttype.equals("Fine")) && (txtdoctype.equals("STANDARD")))
	{
	%>
	<jsp:forward page="fine.jsp" />
	<%
	}


	}  catch (Exception e) {
 out.println(e.toString());
}

finally{
/*-------------------------------DATABASE CONNECTION && STATEMENT IS CLOSED----------------------------------------------------------------------*/

if ( st != null ) st.close();
try{
if ( con != null ) con.close();
}catch(SQLException sl){out.println(sl.toString());}
  }
  %>
</form>

<script language="javascript">
function backward()
{
 location.href="index.jsp";
}
</script>





