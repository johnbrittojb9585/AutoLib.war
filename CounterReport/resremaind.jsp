<%@ page language="java" session="true" buffer="12kb" import="java.sql.*" %>
<%@ include file="/Common.jsp" %>
<form name=resremaind_all>
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
	if (!txtmembercode.equals("") && (txtreporttype.equals("Resreminder")) )
	{
	strsql = strsql + " and Reserve_Mas.Member_Code = '" +txtmembercode+"'";
	}
	if (!txtaccessno.equals("") && (txtreporttype.equals("Resreminder")) )
	{
	strsql = strsql + " and Reserve_Mas.access_no = '" +txtaccessno+"'";
	}

	if (!txtdepartment.equals("NO"))
	strsql = strsql + "  and member_mas.Dept_Code = '" +txtdepartment+"'";

	if (!txtgroup.equals("NO"))
	strsql = strsql + "  and Group_Code = '" +txtgroup+"'";

	if (!txtcourse.equals("NO"))
	strsql = strsql + "  and course_code = '" +txtcourse+"'";

	if ((!txttdate.equals("")) &&  (!txtfdate.equals("")))
	{
	if (txtreporttype.equals("Resreminder"))
	strsql = strsql + " and  Res_Date between '"+txtfdate+"' and '" +txttdate+"'";
	}
	if(!txtgroup.equals("NO")) strsql = strsql + " and Group_Code = '" +txtgroup+"'";
	order = "";
	if (!order1.equals("NO" )) order = order+order1;
	if (!order2.equals("NO" )) order = order+","+order2;
	if (!order3.equals("NO")) order = order+","+order3;




//RESERVATION REMAINDER FOR ALL


if ((txtreporttype.equals("Resreminder")) && (txtdoctype.equals("ALL"))){
	if(report_type.equals("listing"))
	{
	/* strsqlq = "select member_mas.member_name, reserve_mas.access_no, reserve_mas.res_date, reserve_mas.member_code from member_mas, reserve_mas, book_mas where 1<2 "+strsql+" and reserve_mas.access_no=book_mas.access_no and book_mas.availability='YES' and member_mas.member_code=reserve_mas.member_code order by "+order+"";   "+strsql+"";  and Issue_Type='ISSUE' order by  "+order; */
	out.print("<center><font size=5>RESERVE REMAINDER REPORT</center></font>");
	out.print("<font size=4>Report Date :"+Today_Date+"</font>");
	out.print(" <table border='1' width='258'  CELLSPACING='0' BORDERCOLOR='#DADADA' BGCOLOR='#F5F5F5'>");
	if(!txtmembercode.equals(""))out.print("<tr><td width='111' height='10'>Member Code</td><td width='129' height='10'>"+txtmembercode+"</td></tr>");
	if(!txtdepartment.equals("NO")) out.print("<tr><td width='111' height='10'>Department</td><td width='129' height='10'>"+deptname+"</td></tr>");
	if(!txtfdate.equals(""))  out.print("<tr><td width='111' height='10'>From Date</td><td width='129' height='10'>"+txtfdate+"</td></tr>");
	if(!txttdate.equals(""))  out.print("<tr><td width='111' height='10'>To Date</td><td width='129' height='10'>"+txttdate+"</td> </tr>");
	if(!txtgroup.equals("NO")) out.print("<tr><td width='111' height='10'>Group</td><td width='129' height='10'>"+groupname+"</td></tr>");
	if(!txtcourse.equals("NO")) out.print("<tr><td width='111' height='10'>Course</td><td width='129' height='10'>"+ coursename+"</td></tr>");
	//if(!Reptfrom.equals(""))  out.print("<tr><td width='111' height='10'>Reptfrom</td><td width='129' height='10'>"+Reptfrom+"</td> </tr>");
	out.print("</table>");
	
	strsqlq = "select member_mas.member_name, reserve_mas.access_no, reserve_mas.res_date, reserve_mas.member_code from member_mas, reserve_mas, book_mas where 1<2 "+strsql+" and doc_type='BOOK' and reserve_mas.access_no=book_mas.access_no and book_mas.availability='YES' and member_mas.member_code=reserve_mas.member_code order by "+order+"";
	//out.print(strsqlq);
	rs=st.executeQuery(strsqlq);
	if(!rs.next())
	{
	}
	else
	{
	rs.beforeFirst();
	sno=1;
	out.print("<font size=4>Document Type: BOOK</font>");
	out.print("<br><table border=1 width=700 align=center CELLSPACING='0' BORDERCOLOR='#DADADA' BGCOLOR='#F5F5F5'> ");
	out.print("<th>Sno</th><th>Access_No</th><th>Member_code</th><th>Member_Name</th><th>Res_Date</th>");

	while(rs.next())
	{
	 	out.print("<tr><td><font size=2>"+sno+"</td>");
		out.print("<td><font size=2>"+rs.getString("access_no")+"</td>");
		out.print("<td><font size=2>"+rs.getString("member_code")+"</td>");
		out.print("<td><font size=2>"+rs.getString("Member_Name")+"</td>");
		out.print("<td><font size=2>"+getdate(rs.getString("res_date"))+"</td>");
		sno=sno+1;
	}
	out.print("</table>");
	}
	strsqlq = "select member_mas.member_name, reserve_mas.access_no, reserve_mas.res_date, reserve_mas.member_code from member_mas, reserve_mas, book_mas where 1<2 "+strsql+" and doc_type='THESIS' and reserve_mas.access_no=book_mas.access_no and book_mas.availability='YES' and member_mas.member_code=reserve_mas.member_code order by "+order+"";
	//out.print(strsqlq);
	rs=st.executeQuery(strsqlq);
	if(!rs.next())
	{
	}
	else
	{
	rs.beforeFirst();
	sno=1;
	out.print("<font size=4>Document Type: THESIS</font>");
	out.print("<br><table border=1 width=700 align=center CELLSPACING='0' BORDERCOLOR='#DADADA' BGCOLOR='#F5F5F5'> ");
	out.print("<th>Sno</th><th>Access_No</th><th>Member_code</th><th>Member_Name</th><th>Res_Date</th>");

	while(rs.next())
	{
	 	out.print("<tr><td><font size=2>"+sno+"</td>");
		out.print("<td><font size=2>"+rs.getString("access_no")+"</td>");
		out.print("<td><font size=2>"+rs.getString("member_code")+"</td>");
		out.print("<td><font size=2>"+rs.getString("Member_Name")+"</td>");
		out.print("<td><font size=2>"+getdate(rs.getString("res_date"))+"</td>");
		sno=sno+1;
	}
	out.print("</table>");
	}
	strsqlq = "select member_mas.member_name, reserve_mas.access_no, reserve_mas.res_date, reserve_mas.member_code from member_mas, reserve_mas, book_mas where 1<2 "+strsql+" and doc_type='BACK VOLUME' and reserve_mas.access_no=book_mas.access_no and book_mas.availability='YES' and member_mas.member_code=reserve_mas.member_code order by "+order+"";
	//out.print(strsqlq);
	rs=st.executeQuery(strsqlq);
	if(!rs.next())
	{
	}
	else
	{
	rs.beforeFirst();
	sno=1;
	out.print("<font size=4>Document Type: BACK VOLUME</font>");
	out.print("<br><table border=1 width=700 align=center CELLSPACING='0' BORDERCOLOR='#DADADA' BGCOLOR='#F5F5F5'> ");
	out.print("<th>Sno</th><th>Access_No</th><th>Member_code</th><th>Member_Name</th><th>Res_Date</th>");

	while(rs.next())
	{
	 	out.print("<tr><td><font size=2>"+sno+"</td>");
		out.print("<td><font size=2>"+rs.getString("access_no")+"</td>");
		out.print("<td><font size=2>"+rs.getString("member_code")+"</td>");
		out.print("<td><font size=2>"+rs.getString("Member_Name")+"</td>");
		out.print("<td><font size=2>"+getdate(rs.getString("res_date"))+"</td>");
		sno=sno+1;
	}
	out.print("</table>");
	}
	strsqlq = "select member_mas.member_name, reserve_mas.access_no, reserve_mas.res_date, reserve_mas.member_code from member_mas, reserve_mas, book_mas where 1<2 "+strsql+" and doc_type='PROCEEDING' and reserve_mas.access_no=book_mas.access_no and book_mas.availability='YES' and member_mas.member_code=reserve_mas.member_code order by "+order+"";
	//out.print(strsqlq);
	rs=st.executeQuery(strsqlq);
	if(!rs.next())
	{
	}
	else
	{
	rs.beforeFirst();
	sno=1;
	out.print("<font size=4>Document Type: PROCEEDING</font>");
	out.print("<br><table border=1 width=700 align=center CELLSPACING='0' BORDERCOLOR='#DADADA' BGCOLOR='#F5F5F5'> ");
	out.print("<th>Sno</th><th>Access_No</th><th>Member_code</th><th>Member_Name</th><th>Res_Date</th>");

	while(rs.next())
	{
	 	out.print("<tr><td><font size=2>"+sno+"</td>");
		out.print("<td><font size=2>"+rs.getString("access_no")+"</td>");
		out.print("<td><font size=2>"+rs.getString("member_code")+"</td>");
		out.print("<td><font size=2>"+rs.getString("Member_Name")+"</td>");
		out.print("<td><font size=2>"+getdate(rs.getString("res_date"))+"</td>");
		sno=sno+1;
	}
	out.print("</table>");
	}
	strsqlq = "select member_mas.member_name, reserve_mas.access_no, reserve_mas.res_date, reserve_mas.member_code from member_mas, reserve_mas, book_mas where 1<2 "+strsql+" and doc_type='REPORT' and reserve_mas.access_no=book_mas.access_no and book_mas.availability='YES' and member_mas.member_code=reserve_mas.member_code order by "+order+"";
	//out.print(strsqlq);
	rs=st.executeQuery(strsqlq);
	if(!rs.next())
	{
	}
	else
	{
	rs.beforeFirst();
	sno=1;
	out.print("<font size=4>Document Type: REPORT</font>");
	out.print("<br><table border=1 width=700 align=center CELLSPACING='0' BORDERCOLOR='#DADADA' BGCOLOR='#F5F5F5'> ");
	out.print("<th>Sno</th><th>Access_No</th><th>Member_code</th><th>Member_Name</th><th>Res_Date</th>");

	while(rs.next())
	{
	 	out.print("<tr><td><font size=2>"+sno+"</td>");
		out.print("<td><font size=2>"+rs.getString("access_no")+"</td>");
		out.print("<td><font size=2>"+rs.getString("member_code")+"</td>");
		out.print("<td><font size=2>"+rs.getString("Member_Name")+"</td>");
		out.print("<td><font size=2>"+getdate(rs.getString("res_date"))+"</td>");
		sno=sno+1;
	}
	out.print("</table>");
	}
	strsqlq = "select member_mas.member_name, reserve_mas.access_no, reserve_mas.res_date, reserve_mas.member_code from member_mas, reserve_mas, book_mas where 1<2 "+strsql+" and doc_type='NON BOOK' and reserve_mas.access_no=book_mas.access_no and book_mas.availability='YES' and member_mas.member_code=reserve_mas.member_code order by "+order+"";
	//out.print(strsqlq);
	rs=st.executeQuery(strsqlq);
	if(!rs.next())
	{
	}
	else
	{
	rs.beforeFirst();
	sno=1;
	out.print("<font size=4>Document Type: NON BOOK</font>");
	out.print("<br><table border=1 width=700 align=center CELLSPACING='0' BORDERCOLOR='#DADADA' BGCOLOR='#F5F5F5'> ");
	out.print("<th>Sno</th><th>Access_No</th><th>Member_code</th><th>Member_Name</th><th>Res_Date</th>");

	while(rs.next())
	{
	 	out.print("<tr><td><font size=2>"+sno+"</td>");
		out.print("<td><font size=2>"+rs.getString("access_no")+"</td>");
		out.print("<td><font size=2>"+rs.getString("member_code")+"</td>");
		out.print("<td><font size=2>"+rs.getString("Member_Name")+"</td>");
		out.print("<td><font size=2>"+getdate(rs.getString("res_date"))+"</td>");
		sno=sno+1;
	}
	out.print("</table>");
	}
	strsqlq = "select member_mas.member_name, reserve_mas.access_no, reserve_mas.res_date, reserve_mas.member_code from member_mas, reserve_mas, book_mas where 1<2 "+strsql+" and doc_type='STANDARD' and reserve_mas.access_no=book_mas.access_no and book_mas.availability='YES' and member_mas.member_code=reserve_mas.member_code order by "+order+"";
	//out.print(strsqlq);
	rs=st.executeQuery(strsqlq);
	if(!rs.next())
	{
	}
	else
	{
	rs.beforeFirst();
	sno=1;
	out.print("<font size=4>Document Type: STANDARD</font>");
	out.print("<br><table border=1 width=700 align=center CELLSPACING='0' BORDERCOLOR='#DADADA' BGCOLOR='#F5F5F5'> ");
	out.print("<th>Sno</th><th>Access_No</th><th>Member_code</th><th>Member_Name</th><th>Res_Date</th>");

	while(rs.next())
	{
	 	out.print("<tr><td><font size=2>"+sno+"</td>");
		out.print("<td><font size=2>"+rs.getString("access_no")+"</td>");
		out.print("<td><font size=2>"+rs.getString("member_code")+"</td>");
		out.print("<td><font size=2>"+rs.getString("Member_Name")+"</td>");
		out.print("<td><font size=2>"+getdate(rs.getString("res_date"))+"</td>");
		sno=sno+1;
	}
	out.print("</table>");
	}
	}

	if(report_type.equals("breakup"))
	{
	strsqlq="select count(*) as count_book, reserve_mas.res_date, reserve_mas.access_no, book_mas.access_no, book_mas.availability from  reserve_mas, book_mas where 1<2 "+strsql+"  and reserve_mas.access_no=book_mas.access_no and book_mas.availability='YES' group by res_date,reserve_mas.access_no,book_mas.access_no,book_mas.availability order by  res_date";

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
	out.print("<center><font size=5>RESERVE REMAINDER REPORT</center></font>");
	out.print("<font size=3>Document Type :"+txtdoctype+"</font>");
	out.print("<br><table border=1 width=700 align=center CELLSPACING='0' BORDERCOLOR='#DADADA' BGCOLOR='#F5F5F5'> ");
	out.print("<th>Sno</th><th>ReserveDate</th><th>No Of Books</th>");

	while(rs.next())
	{
	 	out.print("<tr><td><font size=2>"+sno+"</td>");
		String idate=getdate(rs.getString("Res_Date"));
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
	strsqlq="select count(*) as count_book, reserve_mas.res_date, reserve_mas.access_no, book_mas.access_no, book_mas.availability from  reserve_mas, book_mas where 1<2 "+strsql+"  and reserve_mas.access_no=book_mas.access_no and book_mas.availability='YES' group by res_date,reserve_mas.access_no,book_mas.access_no,book_mas.availability order by res_date";
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
	out.print("<center><font size=5>RESERVE REMAINDER REPORT</center></font>");
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





//RESERVATION REMAINDER FOR BOOK


if ((txtreporttype.equals("Resreminder")) && (txtdoctype.equals("BOOK"))){
	if(report_type.equals("listing"))
	{
   	strsqlq = "select member_mas.member_name, reserve_mas.access_no, reserve_mas.res_date, reserve_mas.member_code from member_mas, reserve_mas, book_mas where 1<2 "+strsql+" and doc_type='BOOK' and reserve_mas.access_no=book_mas.access_no and book_mas.availability='YES' and member_mas.member_code=reserve_mas.member_code order by "+order+"";  /* "+strsql+"";  and Issue_Type='ISSUE' order by  "+order; */
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
	out.print("<center><font size=5>RESERVE REMAINDER REPORT</center></font>");
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
	out.print("<th>Sno</th><th>Access_No</th><th>Member_code</th><th>Member_Name</th><th>Res_Date</th>");

	while(rs.next())
	{
	 	out.print("<tr><td><font size=2>"+sno+"</td>");
		out.print("<td><font size=2>"+rs.getString("access_no")+"</td>");
		out.print("<td><font size=2>"+rs.getString("member_code")+"</td>");
		out.print("<td><font size=2>"+rs.getString("Member_Name")+"</td>");
		out.print("<td><font size=2>"+getdate(rs.getString("res_date"))+"</td>");
		sno=sno+1;
	}
	out.print("</table>");
	}
	}

	if(report_type.equals("breakup"))
	{
	strsqlq="select count(*) as count_book, reserve_mas.res_date, reserve_mas.access_no, book_mas.access_no, book_mas.availability from  reserve_mas, book_mas where 1<2 "+strsql+" and doc_type='BOOK' and reserve_mas.access_no=book_mas.access_no and book_mas.availability='YES' group by res_date,reserve_mas.access_no,book_mas.access_no,book_mas.availability order by res_date";
	//select count(*) as count_book, res_date from reserve_mas where 1<2 "+strsql+" and //doc_type='STANDARD' group by res_date order by res_date
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
	out.print("<center><font size=5>RESERVE REMAINDER REPORT</center></font>");
	out.print("<font size=3>Document Type :"+txtdoctype+"</font>");
	out.print("<br><table border=1 width=700 align=center CELLSPACING='0' BORDERCOLOR='#DADADA' BGCOLOR='#F5F5F5'> ");
	out.print("<th>Sno</th><th>ReserveDate</th><th>No Of Books</th>");

	while(rs.next())
	{
	 	out.print("<tr><td><font size=2>"+sno+"</td>");
		String idate=getdate(rs.getString("Res_Date"));
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
	strsqlq="select count(*) as count_book, reserve_mas.res_date, reserve_mas.access_no, book_mas.access_no, book_mas.availability from  reserve_mas, book_mas where 1<2 "+strsql+" and doc_type='BOOK' and reserve_mas.access_no=book_mas.access_no and book_mas.availability='YES' group by res_date,reserve_mas.access_no,book_mas.access_no,book_mas.availability order by res_date";
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
	out.print("<center><font size=5>RESERVE REMAINDER REPORT</center></font>");
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


//RESERVATION REMAINDER FOR THESIS


if ((txtreporttype.equals("Resreminder")) && (txtdoctype.equals("THESIS"))){
	if(report_type.equals("listing"))
	{
   	strsqlq = "select member_mas.member_name, reserve_mas.access_no, reserve_mas.res_date, reserve_mas.member_code from member_mas, reserve_mas, book_mas where 1<2 "+strsql+" and doc_type='THESIS' and reserve_mas.access_no=book_mas.access_no and book_mas.availability='YES' and member_mas.member_code=reserve_mas.member_code order by "+order+"";  /* "+strsql+"";  and Issue_Type='ISSUE' order by  "+order; */
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
	out.print("<center><font size=5>RESERVE REMAINDER REPORT</center></font>");
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
	out.print("<th>Sno</th><th>Access_No</th><th>Member_code</th><th>Member_Name</th><th>Res_Date</th>");

	while(rs.next())
	{
	 	out.print("<tr><td><font size=2>"+sno+"</td>");
		out.print("<td><font size=2>"+rs.getString("access_no")+"</td>");
		out.print("<td><font size=2>"+rs.getString("member_code")+"</td>");
		out.print("<td><font size=2>"+rs.getString("Member_Name")+"</td>");
		out.print("<td><font size=2>"+getdate(rs.getString("res_date"))+"</td>");
		sno=sno+1;
	}
	out.print("</table>");
	}
	}

	if(report_type.equals("breakup"))
	{
	strsqlq="select count(*) as count_book, reserve_mas.res_date, reserve_mas.access_no, book_mas.access_no, book_mas.availability from  reserve_mas, book_mas where 1<2 "+strsql+" and doc_type='THESIS' and reserve_mas.access_no=book_mas.access_no and book_mas.availability='YES' group by res_date,reserve_mas.access_no,book_mas.access_no,book_mas.availability order by res_date";
	//select count(*) as count_book, res_date from reserve_mas where 1<2 "+strsql+" and //doc_type='STANDARD' group by res_date order by res_date
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
	out.print("<center><font size=5>RESERVE REMAINDER REPORT</center></font>");
	out.print("<font size=3>Document Type :"+txtdoctype+"</font>");
	out.print("<br><table border=1 width=700 align=center CELLSPACING='0' BORDERCOLOR='#DADADA' BGCOLOR='#F5F5F5'> ");
	out.print("<th>Sno</th><th>ReserveDate</th><th>No Of Books</th>");

	while(rs.next())
	{
	 	out.print("<tr><td><font size=2>"+sno+"</td>");
		String idate=getdate(rs.getString("Res_Date"));
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
	strsqlq="select count(*) as count_book, reserve_mas.res_date, reserve_mas.access_no, book_mas.access_no, book_mas.availability from  reserve_mas, book_mas where 1<2 "+strsql+" and doc_type='THESIS' and reserve_mas.access_no=book_mas.access_no and book_mas.availability='YES' group by res_date,reserve_mas.access_no,book_mas.access_no,book_mas.availability order by res_date";
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
	out.print("<center><font size=5>RESERVE REMAINDER REPORT</center></font>");
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

//RESERVATION REMAINDER FOR BACK VOLUME


if ((txtreporttype.equals("Resreminder")) && (txtdoctype.equals("BACK VOLUME"))){
	if(report_type.equals("listing"))
	{
   	strsqlq = "select member_mas.member_name, reserve_mas.access_no, reserve_mas.res_date, reserve_mas.member_code from member_mas, reserve_mas, book_mas where 1<2 "+strsql+" and doc_type='BACK VOLUME' and reserve_mas.access_no=book_mas.access_no and book_mas.availability='YES' and member_mas.member_code=reserve_mas.member_code order by "+order+"";  /* "+strsql+"";  and Issue_Type='ISSUE' order by  "+order; */
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
	out.print("<center><font size=5>RESERVE REMAINDER REPORT</center></font>");
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
	out.print("<th>Sno</th><th>Access_No</th><th>Member_code</th><th>Member_Name</th><th>Res_Date</th>");

	while(rs.next())
	{
	 	out.print("<tr><td><font size=2>"+sno+"</td>");
		out.print("<td><font size=2>"+rs.getString("access_no")+"</td>");
		out.print("<td><font size=2>"+rs.getString("member_code")+"</td>");
		out.print("<td><font size=2>"+rs.getString("Member_Name")+"</td>");
		out.print("<td><font size=2>"+getdate(rs.getString("res_date"))+"</td>");
		sno=sno+1;
	}
	out.print("</table>");
	}
	}

	if(report_type.equals("breakup"))
	{
	strsqlq="select count(*) as count_book, reserve_mas.res_date, reserve_mas.access_no, book_mas.access_no, book_mas.availability from  reserve_mas, book_mas where 1<2 "+strsql+" and doc_type='BACK VOLUME' and reserve_mas.access_no=book_mas.access_no and book_mas.availability='YES' group by res_date,reserve_mas.access_no,book_mas.access_no,book_mas.availability order by res_date";
	//select count(*) as count_book, res_date from reserve_mas where 1<2 "+strsql+" and //doc_type='STANDARD' group by res_date order by res_date
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
	out.print("<center><font size=5>RESERVE REMAINDER REPORT</center></font>");
	out.print("<font size=3>Document Type :"+txtdoctype+"</font>");
	out.print("<br><table border=1 width=700 align=center CELLSPACING='0' BORDERCOLOR='#DADADA' BGCOLOR='#F5F5F5'> ");
	out.print("<th>Sno</th><th>ReserveDate</th><th>No Of Books</th>");

	while(rs.next())
	{
	 	out.print("<tr><td><font size=2>"+sno+"</td>");
		String idate=getdate(rs.getString("Res_Date"));
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
	strsqlq="select count(*) as count_book, reserve_mas.res_date, reserve_mas.access_no, book_mas.access_no, book_mas.availability from  reserve_mas, book_mas where 1<2 "+strsql+" and doc_type='BACK VOLUME' and reserve_mas.access_no=book_mas.access_no and book_mas.availability='YES' group by res_date,reserve_mas.access_no,book_mas.access_no,book_mas.availability order by res_date";
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
	out.print("<center><font size=5>RESERVE REMAINDER REPORT</center></font>");
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


//RESERVATION REMAINDER FOR PROCEEDING


if ((txtreporttype.equals("Resreminder")) && (txtdoctype.equals("PROCEEDING"))){
	if(report_type.equals("listing"))
	{
   	strsqlq = "select member_mas.member_name, reserve_mas.access_no, reserve_mas.res_date, reserve_mas.member_code from member_mas, reserve_mas, book_mas where 1<2 "+strsql+" and doc_type='PROCEEDING' and reserve_mas.access_no=book_mas.access_no and book_mas.availability='YES' and member_mas.member_code=reserve_mas.member_code order by "+order+"";  /* "+strsql+"";  and Issue_Type='ISSUE' order by  "+order; */
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
	out.print("<center><font size=5>RESERVE REMAINDER REPORT</center></font>");
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
	out.print("<th>Sno</th><th>Access_No</th><th>Member_code</th><th>Member_Name</th><th>Res_Date</th>");

	while(rs.next())
	{
	 	out.print("<tr><td><font size=2>"+sno+"</td>");
		out.print("<td><font size=2>"+rs.getString("access_no")+"</td>");
		out.print("<td><font size=2>"+rs.getString("member_code")+"</td>");
		out.print("<td><font size=2>"+rs.getString("Member_Name")+"</td>");
		out.print("<td><font size=2>"+getdate(rs.getString("res_date"))+"</td>");
		sno=sno+1;
	}
	out.print("</table>");
	}
	}

	if(report_type.equals("breakup"))
	{
	strsqlq="select count(*) as count_book, reserve_mas.res_date, reserve_mas.access_no, book_mas.access_no, book_mas.availability from  reserve_mas, book_mas where 1<2 "+strsql+" and doc_type='PROCEEDING' and reserve_mas.access_no=book_mas.access_no and book_mas.availability='YES' group by res_date,reserve_mas.access_no,book_mas.access_no,book_mas.availability order by res_date";
	//select count(*) as count_book, res_date from reserve_mas where 1<2 "+strsql+" and //doc_type='STANDARD' group by res_date order by res_date
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
	out.print("<center><font size=5>RESERVE REMAINDER REPORT</center></font>");
	out.print("<font size=3>Document Type :"+txtdoctype+"</font>");
	out.print("<br><table border=1 width=700 align=center CELLSPACING='0' BORDERCOLOR='#DADADA' BGCOLOR='#F5F5F5'> ");
	out.print("<th>Sno</th><th>ReserveDate</th><th>No Of Books</th>");

	while(rs.next())
	{
	 	out.print("<tr><td><font size=2>"+sno+"</td>");
		String idate=getdate(rs.getString("Res_Date"));
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
	strsqlq="select count(*) as count_book, reserve_mas.res_date, reserve_mas.access_no, book_mas.access_no, book_mas.availability from  reserve_mas, book_mas where 1<2 "+strsql+" and doc_type='PROCEEDING' and reserve_mas.access_no=book_mas.access_no and book_mas.availability='YES' group by res_date,reserve_mas.access_no,book_mas.access_no,book_mas.availability order by res_date";
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
	out.print("<center><font size=5>RESERVE REMAINDER REPORT</center></font>");
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



//RESERVATION REMAINDER FOR REPORT


if ((txtreporttype.equals("Resreminder")) && (txtdoctype.equals("REPORT"))){
	if(report_type.equals("listing"))
	{
   	strsqlq = "select member_mas.member_name, reserve_mas.access_no, reserve_mas.res_date, reserve_mas.member_code from member_mas, reserve_mas, book_mas where 1<2 "+strsql+" and doc_type='REPORT' and reserve_mas.access_no=book_mas.access_no and book_mas.availability='YES' and member_mas.member_code=reserve_mas.member_code order by "+order+"";  /* "+strsql+"";  and Issue_Type='ISSUE' order by  "+order; */
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
	out.print("<center><font size=5>RESERVE REMAINDER REPORT</center></font>");
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
	out.print("<th>Sno</th><th>Access_No</th><th>Member_code</th><th>Member_Name</th><th>Res_Date</th>");

	while(rs.next())
	{
	 	out.print("<tr><td><font size=2>"+sno+"</td>");
		out.print("<td><font size=2>"+rs.getString("access_no")+"</td>");
		out.print("<td><font size=2>"+rs.getString("member_code")+"</td>");
		out.print("<td><font size=2>"+rs.getString("Member_Name")+"</td>");
		out.print("<td><font size=2>"+getdate(rs.getString("res_date"))+"</td>");
		sno=sno+1;
	}
	out.print("</table>");
	}
	}

	if(report_type.equals("breakup"))
	{
	strsqlq="select count(*) as count_book, reserve_mas.res_date, reserve_mas.access_no, book_mas.access_no, book_mas.availability from  reserve_mas, book_mas where 1<2 "+strsql+" and doc_type='REPORT' and reserve_mas.access_no=book_mas.access_no and book_mas.availability='YES' group by res_date,reserve_mas.access_no,book_mas.access_no,book_mas.availability order by res_date";
	//select count(*) as count_book, res_date from reserve_mas where 1<2 "+strsql+" and //doc_type='STANDARD' group by res_date order by res_date
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
	out.print("<center><font size=5>RESERVE REMAINDER REPORT</center></font>");
	out.print("<font size=3>Document Type :"+txtdoctype+"</font>");
	out.print("<br><table border=1 width=700 align=center CELLSPACING='0' BORDERCOLOR='#DADADA' BGCOLOR='#F5F5F5'> ");
	out.print("<th>Sno</th><th>ReserveDate</th><th>No Of Books</th>");

	while(rs.next())
	{
	 	out.print("<tr><td><font size=2>"+sno+"</td>");
		String idate=getdate(rs.getString("Res_Date"));
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
	strsqlq="select count(*) as count_book, reserve_mas.res_date, reserve_mas.access_no, book_mas.access_no, book_mas.availability from  reserve_mas, book_mas where 1<2 "+strsql+" and doc_type='REPORT' and reserve_mas.access_no=book_mas.access_no and book_mas.availability='YES' group by res_date,reserve_mas.access_no,book_mas.access_no,book_mas.availability order by res_date";
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
	out.print("<center><font size=5>RESERVE REMAINDER REPORT</center></font>");
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

//RESERVATION REMAINDER FOR NON BOOK


if ((txtreporttype.equals("Resreminder")) && (txtdoctype.equals("NON BOOK"))){
	if(report_type.equals("listing"))
	{
   	strsqlq = "select member_mas.member_name, reserve_mas.access_no, reserve_mas.res_date, reserve_mas.member_code from member_mas, reserve_mas, book_mas where 1<2 "+strsql+" and doc_type='NON BOOK' and reserve_mas.access_no=book_mas.access_no and book_mas.availability='YES' and member_mas.member_code=reserve_mas.member_code order by "+order+"";  /* "+strsql+"";  and Issue_Type='ISSUE' order by  "+order; */
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
	out.print("<center><font size=5>RESERVE REMAINDER REPORT</center></font>");
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
	out.print("<th>Sno</th><th>Access_No</th><th>Member_code</th><th>Member_Name</th><th>Res_Date</th>");

	while(rs.next())
	{
	 	out.print("<tr><td><font size=2>"+sno+"</td>");
		out.print("<td><font size=2>"+rs.getString("access_no")+"</td>");
		out.print("<td><font size=2>"+rs.getString("member_code")+"</td>");
		out.print("<td><font size=2>"+rs.getString("Member_Name")+"</td>");
		out.print("<td><font size=2>"+getdate(rs.getString("res_date"))+"</td>");
		sno=sno+1;
	}
	out.print("</table>");
	}
	}

	if(report_type.equals("breakup"))
	{
	strsqlq="select count(*) as count_book, reserve_mas.res_date, reserve_mas.access_no, book_mas.access_no, book_mas.availability from  reserve_mas, book_mas where 1<2 "+strsql+" and doc_type='NON BOOK' and reserve_mas.access_no=book_mas.access_no and book_mas.availability='YES' group by res_date,reserve_mas.access_no,book_mas.access_no,book_mas.availability order by res_date";
	//select count(*) as count_book, res_date from reserve_mas where 1<2 "+strsql+" and //doc_type='STANDARD' group by res_date order by res_date
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
	out.print("<center><font size=5>RESERVE REMAINDER REPORT</center></font>");
	out.print("<font size=3>Document Type :"+txtdoctype+"</font>");
	out.print("<br><table border=1 width=700 align=center CELLSPACING='0' BORDERCOLOR='#DADADA' BGCOLOR='#F5F5F5'> ");
	out.print("<th>Sno</th><th>ReserveDate</th><th>No Of Books</th>");

	while(rs.next())
	{
	 	out.print("<tr><td><font size=2>"+sno+"</td>");
		String idate=getdate(rs.getString("Res_Date"));
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
	strsqlq="select count(*) as count_book, reserve_mas.res_date, reserve_mas.access_no, book_mas.access_no, book_mas.availability from  reserve_mas, book_mas where 1<2 "+strsql+" and doc_type='NON BOOK' and reserve_mas.access_no=book_mas.access_no and book_mas.availability='YES' group by res_date,reserve_mas.access_no,book_mas.access_no,book_mas.availability order by res_date";
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
	out.print("<center><font size=5>RESERVE REMAINDER REPORT</center></font>");
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

//RESERVATION REMAINDER FOR STANDARD



if ((txtreporttype.equals("Resreminder")) && (txtdoctype.equals("STANDARD"))){
	if(report_type.equals("listing"))
	{
   	strsqlq = "select member_mas.member_name, reserve_mas.access_no, reserve_mas.res_date, reserve_mas.member_code from member_mas, reserve_mas, book_mas where 1<2 "+strsql+" and doc_type='STANDARD' and reserve_mas.access_no=book_mas.access_no and book_mas.availability='YES' and member_mas.member_code=reserve_mas.member_code order by "+order+"";  /* "+strsql+"";  and Issue_Type='ISSUE' order by  "+order; */
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
	out.print("<center><font size=5>RESERVE REMAINDER REPORT</center></font>");
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
	out.print("<th>Sno</th><th>Access_No</th><th>Member_code</th><th>Member_Name</th><th>Res_Date</th>");

	while(rs.next())
	{
	 	out.print("<tr><td><font size=2>"+sno+"</td>");
		out.print("<td><font size=2>"+rs.getString("access_no")+"</td>");
		out.print("<td><font size=2>"+rs.getString("member_code")+"</td>");
		out.print("<td><font size=2>"+rs.getString("Member_Name")+"</td>");
		out.print("<td><font size=2>"+getdate(rs.getString("res_date"))+"</td>");
		sno=sno+1;
	}
	out.print("</table>");
	}
	}

	if(report_type.equals("breakup"))
	{
	strsqlq="select count(*) as count_book, reserve_mas.res_date, reserve_mas.access_no, book_mas.access_no, book_mas.availability from  reserve_mas, book_mas where 1<2 "+strsql+" and doc_type='STANDARD' and reserve_mas.access_no=book_mas.access_no and book_mas.availability='YES' group by res_date,reserve_mas.access_no,book_mas.access_no,book_mas.availability order by res_date";
	//select count(*) as count_book, res_date from reserve_mas where 1<2 "+strsql+" and //doc_type='STANDARD' group by res_date order by res_date
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
	out.print("<center><font size=5>RESERVE REMAINDER REPORT</center></font>");
	out.print("<font size=3>Document Type :"+txtdoctype+"</font>");
	out.print("<br><table border=1 width=700 align=center CELLSPACING='0' BORDERCOLOR='#DADADA' BGCOLOR='#F5F5F5'> ");
	out.print("<th>Sno</th><th>ReserveDate</th><th>No Of Books</th>");

	while(rs.next())
	{
	 	out.print("<tr><td><font size=2>"+sno+"</td>");
		String idate=getdate(rs.getString("Res_Date"));
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
	strsqlq="select count(*) as count_book, reserve_mas.res_date, reserve_mas.access_no, book_mas.access_no, book_mas.availability from  reserve_mas, book_mas where 1<2 "+strsql+" and doc_type='STANDARD' and reserve_mas.access_no=book_mas.access_no and book_mas.availability='YES' group by res_date,reserve_mas.access_no,book_mas.access_no,book_mas.availability order by res_date";
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
	out.print("<center><font size=5>RESERVE REMAINDER REPORT</center></font>");
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






