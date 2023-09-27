<%@ page language="java" session="true" buffer="12kb" import="java.sql.*" %>
<%@ include file="/Common.jsp" %>
<form name=fine_all>
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
	String deptname="",groupname="",staff="",Reptfrom="",txtcourse="",txtdoctype="",report_type="",coursename="", count_book="", fine_amount="", coursename1="";
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
	if (!txtmembercode.equals("") && (txtreporttype.equals("Fine")) )
	{
	strsql = strsql + " and Member_Mas.Member_Code = '"+txtmembercode+"'";
	}
	if (!txtdepartment.equals("NO") && (txtreporttype.equals("Fine")) )
	{
	strsql = strsql + " and Member_Mas.Dept_Code = '"+txtdepartment+"'";
	}

	if (!txtgroup.equals("NO"))
	strsql = strsql + "  and Group_Code = '" +txtgroup+"'";

	if (!txtcourse.equals("NO"))
	strsql = strsql + "  and course_code = '" +txtcourse+"'";
	if(!txtaccessno.equals("") && txtreporttype.equals("Fine") )
	{
	strsql = strsql + " and Book_mas.access_no='"+txtaccessno+"'";
	}
	if ((!txttdate.equals("")) &&  (!txtfdate.equals("")))
	{
	if ((txtreporttype.equals("Fine")) ||(txtreporttype.equals("Return")))
	strsql = strsql + " and Return_Date between '"+txtfdate+"' and '" +txttdate+"'";
	}
	if(!txtgroup.equals("NO")) strsql = strsql + " and Group_Code = '" +txtgroup+"'";
	order = "";
	if (!order1.equals("NO" )) order = order+order1;
	if (!order2.equals("NO" )) order = order+","+order2;
	if (!order3.equals("NO")) order = order+","+order3;






//FINE FOR ALL


if ((txtreporttype.equals("Fine")) && (txtdoctype.equals("ALL")))
	{
	if(report_type.equals("listing"))
	{
	out.print("<center><font size=5>FINE REPORT</center></font>");
	out.print("<font size=4>Report Date :"+Today_Date+"</font><br>");
	out.print(" <table border='1' width='258'  CELLSPACING='0' BORDERCOLOR='#DADADA' BGCOLOR='#F5F5F5'>");
	if(!txtmembercode.equals(""))out.print("<tr><td width='111' height='10'>Member Code</td><td width='129' height='10'>"+txtmembercode+"</td></tr>");
	if(!txtaccessno.equals(""))out.print("<tr><td width='111' height='10'>Access No</td><td width='129' height='10'>"+txtaccessno+"</td></tr>");
	if(!txtdepartment.equals("NO")) out.print("<tr><td width='111' height='10'>Department</td><td width='129' height='10'>"+deptname+"</td></tr>");
	if(!txtfdate.equals(""))  out.print("<tr><td width='111' height='10'>From Date</td><td width='129' height='10'>"+txtfdate+"</td></tr>");
	if(!txttdate.equals(""))  out.print("<tr><td width='111' height='10'>To Date</td><td width='129' height='10'>"+txttdate+"</td> </tr>");
	if(!txtgroup.equals("NO")) out.print("<tr><td width='111' height='10'>Group</td><td width='129' height='10'>"+groupname+"</td></tr>");
	if(!txtcourse.equals("NO")) out.print("<tr><td width='111' height='10'>Course</td><td width='129' height='10'>"+ coursename+"</td></tr>");
	//if(!Reptfrom.equals(""))  out.print("<tr><td width='111' height='10'>Reptfrom</td><td width='129' height='10'>"+Reptfrom+"</td> </tr>");
	out.print("</table>");

	strsqlq="select sum(fine_amount) as sum_fine, issue_history.member_code as member_code, member_mas.member_name as member_name, issue_history.access_no, book_mas.title, issue_history.issue_date, issue_history.due_date, issue_history.return_date, issue_history.fine_amount from issue_history, member_mas, book_mas where 1<2 "+strsql+" and issue_history.member_code=member_mas.member_code and issue_history.access_no=book_mas.access_no and fine_amount>0 and doc_type='BOOK' group by issue_history.member_code, member_mas.member_name, issue_history.access_no, book_mas.title, issue_history.issue_date, issue_history.due_date, issue_history.return_date, issue_history.fine_amount order by "+order+"";
	//out.print(strsqlq);
	rs=st.executeQuery(strsqlq);
  	if(!rs.next())
	{

	}
	else
	{
	rs.beforeFirst();
	sno=1;
	out.print("<font size=4>Document Type :BOOK</font><br>");
	out.print("<br><table border=1 width=700 align=center CELLSPACING='0' BORDERCOLOR='#DADADA' BGCOLOR='#F5F5F5'> ");
	out.print("<th>Sno</th><th>Member code</th><th>Member Name</th><th>Access No</th><th>Title</th><th>Issue Date</th><th>Due Date</th><th>Return Date</th><th>Fine Amount</th>");

	while(rs.next())
	{
	 	out.print("<tr><td><font size=2>"+sno+"</td>");
		out.print("<td><font size=2>"+rs.getString("member_code")+"</td>");
		out.print("<td><font size=2>"+rs.getString("member_name")+"</td>");
		out.print("<td><font size=2>"+rs.getString("access_no")+"</td>");
		out.print("<td><font size=2>"+rs.getString("title")+"</td>");
		out.print("<td><font size=2>"+getdate(rs.getString("issue_date"))+"</td>");
		out.print("<td><font size=2>"+getdate(rs.getString("due_date"))+"</td>");
		out.print("<td><font size=2>"+getdate(rs.getString("return_date"))+"</td>");
		sno=sno+1;
		float faa=rs.getFloat("sum_fine");
		out.print("<td><font size=2>"+faa+"</td>");
		fine=fine+faa;
		subcount=subcount+faa;
	}
	out.print("</table>");
	out.print("<br>");
	out.print("<br>");
	out.print("<br>");
	out.print("<font face=times new roman size=4><center>Grand Total:  "+fine+"</center></font>");
	out.print("<font face=times new roman size=4><center>Net Total:  "+subcount+"</center></font>");
	}
	strsqlq="select sum(fine_amount) as sum_fine, issue_history.member_code as member_code, member_mas.member_name as member_name, issue_history.access_no, book_mas.title, issue_history.issue_date, issue_history.due_date, issue_history.return_date, issue_history.fine_amount from issue_history, member_mas, book_mas where 1<2 "+strsql+" and issue_history.member_code=member_mas.member_code and issue_history.access_no=book_mas.access_no and fine_amount>0 and doc_type='PROCEEDING' group by issue_history.member_code, member_mas.member_name, issue_history.access_no, book_mas.title, issue_history.issue_date, issue_history.due_date, issue_history.return_date, issue_history.fine_amount order by "+order+"";

	//out.print(strsqlq);
	rs=st.executeQuery(strsqlq);
	if(!rs.next())
	{

	}
	else
	{
	rs.beforeFirst();
	sno=1;
	fine=0;
	out.print("<center><font size=5></center></font>");
	//out.print("<font size=5>Report Date :"+Today_Date+"</font>");
	out.print("<font size=4>Document Type :PROCEEDING</font><br>");
	out.print("<br><table border=1 width=700 align=center CELLSPACING='0' BORDERCOLOR='#DADADA' BGCOLOR='#F5F5F5'> ");
	out.print("<th>Sno</th><th>Member code</th><th>Member Name</th><th>Access No</th><th>Title</th><th>Issue Date</th><th>Due Date</th><th>Return Date</th><th>Fine Amount</th>");

	while(rs.next())
	{
	 	out.print("<tr><td><font size=2>"+sno+"</td>");
		out.print("<td><font size=2>"+rs.getString("member_code")+"</td>");
		out.print("<td><font size=2>"+rs.getString("member_name")+"</td>");
		out.print("<td><font size=2>"+rs.getString("access_no")+"</td>");
		out.print("<td><font size=2>"+rs.getString("title")+"</td>");
		out.print("<td><font size=2>"+getdate(rs.getString("issue_date"))+"</td>");
		out.print("<td><font size=2>"+getdate(rs.getString("due_date"))+"</td>");
		out.print("<td><font size=2>"+getdate(rs.getString("return_date"))+"</td>");
                String fa=rs.getString("fine_amount");
		out.print("<td><font size=2>"+fa+"</td>");
		sno=sno+1;
		float tfa=Float.parseFloat(fa);
		fine=fine+tfa;
		subcount=subcount+tfa;

	}
	out.print("</table>");
	out.print("<br>");
	out.print("<br>");
	out.print("<font face=times new roman size=4><center>Grand Total:  "+fine+"</center></font>");
	out.print("<font face=times new roman size=4><center>Net Total:  "+subcount+"</center></font>");
	}
	strsqlq="select sum(fine_amount) as sum_fine, issue_history.member_code as member_code, member_mas.member_name as member_name, issue_history.access_no, book_mas.title, issue_history.issue_date, issue_history.due_date, issue_history.return_date, issue_history.fine_amount from issue_history, member_mas, book_mas where 1<2 "+strsql+" and issue_history.member_code=member_mas.member_code and issue_history.access_no=book_mas.access_no and fine_amount>0 and doc_type='THESIS' group by issue_history.member_code, member_mas.member_name, issue_history.access_no, book_mas.title, issue_history.issue_date, issue_history.due_date, issue_history.return_date, issue_history.fine_amount order by "+order+"";

	//out.print(strsqlq);
	rs=st.executeQuery(strsqlq);
	if(!rs.next())
	{

	}
	else
	{
	rs.beforeFirst();
	sno=1;
	fine=0;
	//out.print("<center><font size=5>FINE REPORT</center></font>");
	out.print("<font size=4>Document Type :THESIS</font><br>");
	out.print("<br><table border=1 width=700 align=center CELLSPACING='0' BORDERCOLOR='#DADADA' BGCOLOR='#F5F5F5'> ");
	out.print("<th>Sno</th><th>Member code</th><th>Member Name</th><th>Access No</th><th>Title</th><th>Issue Date</th><th>Due Date</th><th>Return Date</th><th>Fine Amount</th>");

	while(rs.next())
	{
	 	out.print("<tr><td><font size=2>"+sno+"</td>");
		out.print("<td><font size=2>"+rs.getString("member_code")+"</td>");
		out.print("<td><font size=2>"+rs.getString("member_name")+"</td>");
		out.print("<td><font size=2>"+rs.getString("access_no")+"</td>");
		out.print("<td><font size=2>"+rs.getString("title")+"</td>");
		out.print("<td><font size=2>"+getdate(rs.getString("issue_date"))+"</td>");
		out.print("<td><font size=2>"+getdate(rs.getString("due_date"))+"</td>");
		out.print("<td><font size=2>"+getdate(rs.getString("return_date"))+"</td>");
                String fa=rs.getString("fine_amount");
		out.print("<td><font size=2>"+fa+"</td>");
		sno=sno+1;
		float tfa=Float.parseFloat(fa);
		fine=fine+tfa;
		subcount=subcount+tfa;

	}
	out.print("</table>");
	out.print("<br>");
	out.print("<br>");
	out.print("<font face=times new roman size=4><center>Grand Total:  "+fine+"</center></font>");
	out.print("<font face=times new roman size=4><center>Net Total:  "+subcount+"</center></font>");
	}
	strsqlq="select sum(fine_amount) as sum_fine, issue_history.member_code as member_code, member_mas.member_name as member_name, issue_history.access_no, book_mas.title, issue_history.issue_date, issue_history.due_date, issue_history.return_date, issue_history.fine_amount from issue_history, member_mas, book_mas where 1<2 "+strsql+" and issue_history.member_code=member_mas.member_code and issue_history.access_no=book_mas.access_no and fine_amount>0 and doc_type='BACK VOLUME' group by issue_history.member_code, member_mas.member_name, issue_history.access_no, book_mas.title, issue_history.issue_date, issue_history.due_date, issue_history.return_date, issue_history.fine_amount order by "+order+"";

	//out.print(strsqlq);
	rs=st.executeQuery(strsqlq);
	if(!rs.next())
	{

	}
	else
	{
	rs.beforeFirst();
	sno=1;
	fine=0;
	//out.print("<center><font size=5>FINE REPORT</center></font>");
	out.print("<font size=4>Document Type :BACK VOLUME</font><br>");
	out.print("<br><table border=1 width=700 align=center CELLSPACING='0' BORDERCOLOR='#DADADA' BGCOLOR='#F5F5F5'> ");
	out.print("<th>Sno</th><th>Member code</th><th>Member Name</th><th>Access No</th><th>Title</th><th>Issue Date</th><th>Due Date</th><th>Return Date</th><th>Fine Amount</th>");

	while(rs.next())
	{
	 	out.print("<tr><td><font size=2>"+sno+"</td>");
		out.print("<td><font size=2>"+rs.getString("member_code")+"</td>");
		out.print("<td><font size=2>"+rs.getString("member_name")+"</td>");
		out.print("<td><font size=2>"+rs.getString("access_no")+"</td>");
		out.print("<td><font size=2>"+rs.getString("title")+"</td>");
		out.print("<td><font size=2>"+getdate(rs.getString("issue_date"))+"</td>");
		out.print("<td><font size=2>"+getdate(rs.getString("due_date"))+"</td>");
		out.print("<td><font size=2>"+getdate(rs.getString("return_date"))+"</td>");
                String fa=rs.getString("fine_amount");
		out.print("<td><font size=2>"+fa+"</td>");
		sno=sno+1;
		float tfa=Float.parseFloat(fa);
		fine=fine+tfa;
		subcount=subcount+tfa;

	}
	out.print("</table>");
	out.print("<br>");
	out.print("<br>");
	out.print("<font face=times new roman size=4><center>Grand Total:  "+fine+"</center></font>");
	out.print("<font face=times new roman size=4><center>Net Total:  "+subcount+"</center></font>");
	}
	strsqlq="select sum(fine_amount) as sum_fine, issue_history.member_code as member_code, member_mas.member_name as member_name, issue_history.access_no, book_mas.title, issue_history.issue_date, issue_history.due_date, issue_history.return_date, issue_history.fine_amount from issue_history, member_mas, book_mas where 1<2 "+strsql+" and issue_history.member_code=member_mas.member_code and issue_history.access_no=book_mas.access_no and fine_amount>0 and doc_type='REPORT' group by issue_history.member_code, member_mas.member_name, issue_history.access_no, book_mas.title, issue_history.issue_date, issue_history.due_date, issue_history.return_date, issue_history.fine_amount order by "+order+"";

	//out.print(strsqlq);
	rs=st.executeQuery(strsqlq);
	if(!rs.next())
	{

	}
	else
	{
	rs.beforeFirst();
	sno=1;
	fine=0;
	//out.print("<center><font size=5>FINE REPORT</center></font>");
	out.print("<font size=4>Document Type :REPORT</font><br>");
	out.print("<br><table border=1 width=700 align=center CELLSPACING='0' BORDERCOLOR='#DADADA' BGCOLOR='#F5F5F5'> ");
	out.print("<th>Sno</th><th>Member code</th><th>Member Name</th><th>Access No</th><th>Title</th><th>Issue Date</th><th>Due Date</th><th>Return Date</th><th>Fine Amount</th>");

	while(rs.next())
	{
	 	out.print("<tr><td><font size=2>"+sno+"</td>");
		out.print("<td><font size=2>"+rs.getString("member_code")+"</td>");
		out.print("<td><font size=2>"+rs.getString("member_name")+"</td>");
		out.print("<td><font size=2>"+rs.getString("access_no")+"</td>");
		out.print("<td><font size=2>"+rs.getString("title")+"</td>");
		out.print("<td><font size=2>"+getdate(rs.getString("issue_date"))+"</td>");
		out.print("<td><font size=2>"+getdate(rs.getString("due_date"))+"</td>");
		out.print("<td><font size=2>"+getdate(rs.getString("return_date"))+"</td>");
                String fa=rs.getString("fine_amount");
		out.print("<td><font size=2>"+fa+"</td>");
		sno=sno+1;
		float tfa=Float.parseFloat(fa);
		fine=fine+tfa;
		subcount=subcount+tfa;

	}
	out.print("</table>");
	out.print("<br>");
	out.print("<br>");
	out.print("<font face=times new roman size=4><center>Grand Total:  "+fine+"</center></font>");
	out.print("<font face=times new roman size=4><center>Net Total:  "+subcount+"</center></font>");
	}
	strsqlq="select sum(fine_amount) as sum_fine, issue_history.member_code as member_code, member_mas.member_name as member_name, issue_history.access_no, book_mas.title, issue_history.issue_date, issue_history.due_date, issue_history.return_date, issue_history.fine_amount from issue_history, member_mas, book_mas where 1<2 "+strsql+" and issue_history.member_code=member_mas.member_code and issue_history.access_no=book_mas.access_no and fine_amount>0 and doc_type='NON BOOK' group by issue_history.member_code, member_mas.member_name, issue_history.access_no, book_mas.title, issue_history.issue_date, issue_history.due_date, issue_history.return_date, issue_history.fine_amount order by "+order+"";

	//out.print(strsqlq);
	rs=st.executeQuery(strsqlq);
	if(!rs.next())
	{

	}
	else
	{
	rs.beforeFirst();
	sno=1;
	fine=0;
	//out.print("<center><font size=5>FINE REPORT</center></font>");
	out.print("<font size=4>Document Type :NON BOOK</font><br>");
	out.print("<br><table border=1 width=700 align=center CELLSPACING='0' BORDERCOLOR='#DADADA' BGCOLOR='#F5F5F5'> ");
	out.print("<th>Sno</th><th>Member code</th><th>Member Name</th><th>Access No</th><th>Title</th><th>Issue Date</th><th>Due Date</th><th>Return Date</th><th>Fine Amount</th>");

	while(rs.next())
	{
	 	out.print("<tr><td><font size=2>"+sno+"</td>");
		out.print("<td><font size=2>"+rs.getString("member_code")+"</td>");
		out.print("<td><font size=2>"+rs.getString("member_name")+"</td>");
		out.print("<td><font size=2>"+rs.getString("access_no")+"</td>");
		out.print("<td><font size=2>"+rs.getString("title")+"</td>");
		out.print("<td><font size=2>"+getdate(rs.getString("issue_date"))+"</td>");
		out.print("<td><font size=2>"+getdate(rs.getString("due_date"))+"</td>");
		out.print("<td><font size=2>"+getdate(rs.getString("return_date"))+"</td>");
                String fa=rs.getString("fine_amount");
		out.print("<td><font size=2>"+fa+"</td>");
		sno=sno+1;
		float tfa=Float.parseFloat(fa);
		fine=fine+tfa;
		subcount=subcount+tfa;

	}
	out.print("</table>");
	out.print("<br>");
	out.print("<br>");
	out.print("<font face=times new roman size=4><center>Grand Total:  "+fine+"</center></font>");
	out.print("<font face=times new roman size=4><center>Net Total:  "+subcount+"</center></font>");
	}
	strsqlq="select sum(fine_amount) as sum_fine, issue_history.member_code as member_code, member_mas.member_name as member_name, issue_history.access_no, book_mas.title, issue_history.issue_date, issue_history.due_date, issue_history.return_date, issue_history.fine_amount from issue_history, member_mas, book_mas where 1<2 "+strsql+" and issue_history.member_code=member_mas.member_code and issue_history.access_no=book_mas.access_no and fine_amount>0 and doc_type='STANDARD' group by issue_history.member_code, member_mas.member_name, issue_history.access_no, book_mas.title, issue_history.issue_date, issue_history.due_date, issue_history.return_date, issue_history.fine_amount order by "+order+"";

	//out.print(strsqlq);
	rs=st.executeQuery(strsqlq);
	if(!rs.next())
	{
	}
	else
	{
	rs.beforeFirst();
	sno=1;
	fine=0;
	//out.print("<center><font size=5>FINE REPORT</center></font>");
	out.print("<font size=4>Document Type :STANDARD</font><br>");
	out.print("<br><table border=1 width=700 align=center CELLSPACING='0' BORDERCOLOR='#DADADA' BGCOLOR='#F5F5F5'> ");
	out.print("<th>Sno</th><th>Member code</th><th>Member Name</th><th>Access No</th><th>Title</th><th>Issue Date</th><th>Due Date</th><th>Return Date</th><th>Fine Amount</th>");

	while(rs.next())
	{
	 	out.print("<tr><td><font size=2>"+sno+"</td>");
		out.print("<td><font size=2>"+rs.getString("member_code")+"</td>");
		out.print("<td><font size=2>"+rs.getString("member_name")+"</td>");
		out.print("<td><font size=2>"+rs.getString("access_no")+"</td>");
		out.print("<td><font size=2>"+rs.getString("title")+"</td>");
		out.print("<td><font size=2>"+getdate(rs.getString("issue_date"))+"</td>");
		out.print("<td><font size=2>"+getdate(rs.getString("due_date"))+"</td>");
		out.print("<td><font size=2>"+getdate(rs.getString("return_date"))+"</td>");
                String fa=rs.getString("fine_amount");
		out.print("<td><font size=2>"+fa+"</td>");
		sno=sno+1;
		float tfa=Float.parseFloat(fa);
		fine=fine+tfa;
		subcount=subcount+tfa;

	}
	out.print("</table>");
	out.print("<br>");
	out.print("<br>");
	out.print("<font face=times new roman size=4><center>Grand Total:  "+fine+"</center></font>");
	out.print("<font face=times new roman size=4><center>Net Total:  "+subcount+"</center></font>");
	}
	out.print("<font face=times new roman size=4><center>Total:  "+subcount+"</center></font>");
	}


	if(report_type.equals("breakup"))
	{
	/* strsqlq="select sum(fine_amount) as sum_fine, issue_history.member_code as member_code, member_mas.member_name as member_name, issue_history.access_no, book_mas.title, issue_history.issue_date, issue_history.due_date, issue_history.return_date, issue_history.fine_amount from issue_history, member_mas, book_mas where 1<2 "+strsql+" and issue_history.member_code=member_mas.member_code and issue_history.access_no=book_mas.access_no and fine_amount>0  group by issue_history.issue_date, issue_history.member_code, member_mas.member_name, issue_history.access_no, book_mas.title,  issue_history.due_date, issue_history.return_date, issue_history.fine_amount order by issue_history.return_date"; */
	  strsqlq="select course_mas.course_name, issue_history.member_code as member_code, department_mas.dept_name as dept_name, member_mas.member_name, Member_mas.Course_Code, sum(fine_amount) as amount from issue_history, department_mas, member_mas, course_mas where 1<2 "+strsql+" and issue_history.member_code=member_mas.member_code and issue_history.access_no=book_mas.access_no and course_mas.course_code=member_mas.course_code and department_mas.dept_code=member_mas.dept_code and fine_amount>0 group by issue_history.member_code, dept_name, Member_mas.Course_Code, member_mas.member_name, course_mas.course_name";
	  	 /* strsqlq="select issue_history.member_code as member_code, department_mas.dept_name as dept_name, member_mas.member_name, Member_mas.Course_Code, sum(fine_amount) as amount from issue_history, department_mas, member_mas where 1<2 "+strsql+" and issue_history.member_code=member_mas.member_code and issue_history.access_no=book_mas.access_no and department_mas.dept_code=member_mas.dept_code and fine_amount>0 group by issue_history.member_code, dept_name, Member_mas.Course_Code, member_mas.member_name, issue_history.due_date, member_mas.member_code order by issue_history.due_date, member_mas.member_code"; */
	/* strsqlq="select sum(fine_amount) as sum_fine, issue_history.return_date from issue_history, member_mas, book_mas where 1<2 "+strsql+" and issue_history.member_code=member_mas.member_code and issue_history.access_no=book_mas.access_no and fine_amount>0  group by member_mas.dept_code, issue_history.return_date order by  member_mas.dept_code, issue_history.return_date"; */
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
	out.print("<center><font size=5>FINE REPORT</center></font>");
	out.print("<font size=3>Document Type :"+txtdoctype+"</font>");
	out.print("<br><table border=1 width=700 align=center CELLSPACING='0' BORDERCOLOR='#DADADA' BGCOLOR='#F5F5F5'> ");
	out.print("<th>Sno</th><th>Member Code</th><th>Member Name</th><th>Dept Name</th><th>Course Name</th><th>Fine Amount</th>");

	while(rs.next())
	{
	 	out.print("<tr><td><font size=2>"+sno+"</td>");
		String memcode=rs.getString("Member_Code");
		String memname=rs.getString("Member_Name");
		String deptname1=rs.getString("Dept_Name");
		String course_name=rs.getString("Course_Name");
		out.print("<td><font size=2>"+memcode+"</td>");
		out.print("<td><font size=2>"+memname+"</td>");
		out.print("<td><font size=2>"+deptname1+"</td>");
		out.print("<td><font size=2>"+course_name+"</td>");
		float faa=rs.getFloat("amount");
		out.print("<td><font size=2>"+faa+"</td>");
		sno=sno+1;
		fine=fine+faa;
		subcount=subcount+faa;

	}

	out.print("</table>");
	out.print("<br>");
	out.print("<br>");
	out.print("<br>");
	out.print("<font face=times new roman size=4><center>Total:  "+fine+"</center></font>");
	//out.print("<font face=times new roman size=4><center>Sub Total:  "+subcount+"</center></font>");

	}

	}


	if(report_type.equals("cumulative"))
	{
	strsqlq="select sum(fine_amount) as sum_fine, issue_history.member_code as member_code, member_mas.member_name as member_name, issue_history.access_no, book_mas.title, issue_history.issue_date, issue_history.due_date, issue_history.return_date, issue_history.fine_amount from issue_history, member_mas, book_mas where 1<2 "+strsql+" and issue_history.member_code=member_mas.member_code and issue_history.access_no=book_mas.access_no and fine_amount>0  group by issue_history.member_code, member_mas.member_name, issue_history.access_no, book_mas.title, issue_history.issue_date, issue_history.due_date, issue_history.return_date, issue_history.fine_amount order by "+order+"";
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
	out.print("<center><font size=5>FINE REPORT</center></font>");
	out.print("<font size=3>Document Type :"+txtdoctype+"</font>");
	out.print("<br><table border=0 width=700 align=center CELLSPACING='0' BORDERCOLOR='#DADADA' BGCOLOR='#F5F5F5'> ");


	while(rs.next())
	{

	        float faa=rs.getFloat("sum_fine");
		sno=sno+1;
		fine=fine+faa;
		//subcount=subcount+tfa;

	}
	out.print("</table>");
        out.print("<br>");
	out.print("<br>");
	out.print("<br>");
	out.print("<font face=times new roman size=4><center>Total:  "+fine+"</center></font>");

	}

	}
	}


//FINE FOR BOOK

	if ((txtreporttype.equals("Fine")) && (txtdoctype.equals("BOOK"))){
	if(report_type.equals("listing"))
	{
	 strsqlq="select sum(fine_amount) as sum_fine, issue_history.member_code as member_code, member_mas.member_name as member_name, issue_history.access_no, book_mas.title, issue_history.issue_date, issue_history.due_date, issue_history.return_date, issue_history.fine_amount from issue_history, member_mas, book_mas where 1<2 "+strsql+" and issue_history.member_code=member_mas.member_code and issue_history.access_no=book_mas.access_no and fine_amount>0 and doc_type='BOOK' group by issue_history.member_code, member_mas.member_name, issue_history.access_no, book_mas.title, issue_history.issue_date, issue_history.due_date, issue_history.return_date, issue_history.fine_amount order by "+order+"";

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
	out.print("<center><font size=5>FINE REPORT</center></font>");
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
	out.print("<th>Sno</th><th>Member code</th><th>Member Name</th><th>Access No</th><th>Title</th><th>Issue Date</th><th>Due Date</th><th>Return Date</th><th>Fine Amount</th>");

	while(rs.next())
	{
	 	out.print("<tr><td><font size=2>"+sno+"</td>");
		out.print("<td><font size=2>"+rs.getString("member_code")+"</td>");
		out.print("<td><font size=2>"+rs.getString("member_name")+"</td>");
		out.print("<td><font size=2>"+rs.getString("access_no")+"</td>");
		out.print("<td><font size=2>"+rs.getString("title")+"</td>");
		out.print("<td><font size=2>"+getdate(rs.getString("issue_date"))+"</td>");
		out.print("<td><font size=2>"+getdate(rs.getString("due_date"))+"</td>");
		out.print("<td><font size=2>"+getdate(rs.getString("return_date"))+"</td>");
                String fa=rs.getString("fine_amount");
		out.print("<td><font size=2>"+fa+"</td>");
		sno=sno+1;
		float tfa=Float.parseFloat(fa);
		fine=fine+tfa;
		subcount=subcount+tfa;

	}
	out.print("</table>");
	out.print("<br>");
	out.print("<br>");
	out.print("<font face=times new roman size=4><center>Grand Total:  "+fine+"</center></font>");
	}
	}

	if(report_type.equals("breakup"))
	{
	/* strsqlq="select sum(fine_amount) as sum_fine, issue_history.member_code as member_code, member_mas.member_name as member_name, issue_history.access_no, book_mas.title, issue_history.issue_date, issue_history.due_date, issue_history.return_date, issue_history.fine_amount from issue_history, member_mas, book_mas where 1<2 "+strsql+" and issue_history.member_code=member_mas.member_code and issue_history.access_no=book_mas.access_no and fine_amount>0 and doc_type='BOOK' group by issue_history.member_code, member_mas.member_name, issue_history.access_no, book_mas.title, issue_history.issue_date, issue_history.due_date, issue_history.return_date, issue_history.fine_amount order by "+order+""; */
	strsqlq="select course_mas.course_name, issue_history.member_code as member_code, department_mas.dept_name as dept_name, member_mas.member_name, Member_mas.Course_Code, sum(fine_amount) as amount from issue_history, department_mas, member_mas, course_mas where 1<2 "+strsql+" and doc_type='BOOK' and issue_history.member_code=member_mas.member_code and issue_history.access_no=book_mas.access_no and course_mas.course_code=member_mas.course_code and department_mas.dept_code=member_mas.dept_code and fine_amount>0 group by issue_history.member_code, dept_name, Member_mas.Course_Code, member_mas.member_name, course_mas.course_name";

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
	out.print("<center><font size=5>FINE REPORT</center></font>");
	out.print("<font size=3>Document Type :"+txtdoctype+"</font>");
	out.print("<br><table border=1 width=700 align=center CELLSPACING='0' BORDERCOLOR='#DADADA' BGCOLOR='#F5F5F5'> ");
	out.print("<th>Sno</th><th>Member Code</th><th>Member Name</th><th>Dept Name</th><th>Course Name</th><th>Fine Amount</th>");

	while(rs.next())
	{
	 	out.print("<tr><td><font size=2>"+sno+"</td>");
		String memcode=rs.getString("Member_Code");
		String memname=rs.getString("Member_Name");
		String deptname1=rs.getString("Dept_Name");
		String course_name=rs.getString("Course_Name");
		out.print("<td><font size=2>"+memcode+"</td>");
		out.print("<td><font size=2>"+memname+"</td>");
		out.print("<td><font size=2>"+deptname1+"</td>");
		out.print("<td><font size=2>"+course_name+"</td>");
		float faa=rs.getFloat("amount");
		out.print("<td><font size=2>"+faa+"</td>");
		sno=sno+1;
		fine=fine+faa;
		subcount=subcount+faa;

	}

	out.print("</table>");
	out.print("<br>");
	out.print("<br>");
	out.print("<br>");
	out.print("<font face=times new roman size=4><center>Total:  "+fine+"</center></font>");
	//out.print("<font face=times new roman size=4><center>Sub Total:  "+subcount+"</center></font>");

	}

	}


	if(report_type.equals("cumulative"))
	{

	strsqlq="select sum(fine_amount) as sum_fine, issue_history.member_code as member_code, member_mas.member_name as member_name, issue_history.access_no, book_mas.title, issue_history.issue_date, issue_history.due_date, issue_history.return_date, issue_history.fine_amount from issue_history, member_mas, book_mas where 1<2 "+strsql+" and issue_history.member_code=member_mas.member_code and issue_history.access_no=book_mas.access_no and fine_amount>0 and doc_type='BOOK' group by issue_history.member_code, member_mas.member_name, issue_history.access_no, book_mas.title, issue_history.issue_date, issue_history.due_date, issue_history.return_date, issue_history.fine_amount order by "+order+"";
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
	out.print("<center><font size=5>FINE REPORT</center></font>");
	out.print("<font size=3>Document Type :"+txtdoctype+"</font>");
	out.print("<br><table border=0 width=700 align=center CELLSPACING='0' BORDERCOLOR='#DADADA' BGCOLOR='#F5F5F5'> ");


	while(rs.next())
	{

	        float faa=rs.getFloat("sum_fine");
		sno=sno+1;
		fine=fine+faa;
		//subcount=subcount+tfa;

	}
	out.print("</table>");
        out.print("<br>");
	out.print("<br>");
	out.print("<br>");
	out.print("<font face=times new roman size=4><center>Total:  "+fine+"</center></font>");


	}

	}
	}

//FINE FOR THESIS

	if ((txtreporttype.equals("Fine")) && (txtdoctype.equals("THESIS"))){
	if(report_type.equals("listing"))
	{
	strsqlq="select sum(fine_amount) as sum_fine, issue_history.member_code as member_code, member_mas.member_name as member_name, issue_history.access_no, book_mas.title, issue_history.issue_date, issue_history.due_date, issue_history.return_date, issue_history.fine_amount from issue_history, member_mas, book_mas where 1<2 "+strsql+" and issue_history.member_code=member_mas.member_code and issue_history.access_no=book_mas.access_no and fine_amount>0 and doc_type='THESIS' group by issue_history.member_code, member_mas.member_name, issue_history.access_no, book_mas.title, issue_history.issue_date, issue_history.due_date, issue_history.return_date, issue_history.fine_amount order by "+order+"";

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
	out.print("<center><font size=5>FINE REPORT</center></font>");
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
	out.print("<th>Sno</th><th>Member code</th><th>Member Name</th><th>Access No</th><th>Title</th><th>Issue Date</th><th>Due Date</th><th>Return Date</th><th>Fine Amount</th>");

	while(rs.next())
	{
	 	out.print("<tr><td><font size=2>"+sno+"</td>");
		out.print("<td><font size=2>"+rs.getString("member_code")+"</td>");
		out.print("<td><font size=2>"+rs.getString("member_name")+"</td>");
		out.print("<td><font size=2>"+rs.getString("access_no")+"</td>");
		out.print("<td><font size=2>"+rs.getString("title")+"</td>");
		out.print("<td><font size=2>"+getdate(rs.getString("issue_date"))+"</td>");
		out.print("<td><font size=2>"+getdate(rs.getString("due_date"))+"</td>");
		out.print("<td><font size=2>"+getdate(rs.getString("return_date"))+"</td>");
                String fa=rs.getString("fine_amount");
		out.print("<td><font size=2>"+fa+"</td>");
		sno=sno+1;
		float tfa=Float.parseFloat(fa);
		fine=fine+tfa;
		subcount=subcount+tfa;

	}
	out.print("</table>");
	out.print("<br>");
	out.print("<br>");
	out.print("<font face=times new roman size=4><center>Grand Total:  "+fine+"</center></font>");
	}
	}

	if(report_type.equals("breakup"))
	{
	/* strsqlq="select sum(fine_amount) as sum_fine, issue_history.member_code as member_code, member_mas.member_name as member_name, issue_history.access_no, book_mas.title, issue_history.issue_date, issue_history.due_date, issue_history.return_date, issue_history.fine_amount from issue_history, member_mas, book_mas where 1<2 "+strsql+" and issue_history.member_code=member_mas.member_code and issue_history.access_no=book_mas.access_no and fine_amount>0 and doc_type='THESIS' group by issue_history.member_code, member_mas.member_name, issue_history.access_no, book_mas.title, issue_history.issue_date, issue_history.due_date, issue_history.return_date, issue_history.fine_amount order by "+order+""; */
	strsqlq="select course_mas.course_name, issue_history.member_code as member_code, department_mas.dept_name as dept_name, member_mas.member_name, Member_mas.Course_Code, sum(fine_amount) as amount from issue_history, department_mas, member_mas, course_mas where 1<2 "+strsql+" and doc_type='THESIS' and issue_history.member_code=member_mas.member_code and issue_history.access_no=book_mas.access_no and course_mas.course_code=member_mas.course_code and department_mas.dept_code=member_mas.dept_code and fine_amount>0 group by issue_history.member_code, dept_name, Member_mas.Course_Code, member_mas.member_name, course_mas.course_name";

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
	out.print("<center><font size=5>FINE REPORT</center></font>");
	out.print("<font size=3>Document Type :"+txtdoctype+"</font>");
	out.print("<br><table border=1 width=700 align=center CELLSPACING='0' BORDERCOLOR='#DADADA' BGCOLOR='#F5F5F5'> ");
	out.print("<th>Sno</th><th>Member Code</th><th>Member Name</th><th>Dept Name</th><th>Course Name</th><th>Fine Amount</th>");

	while(rs.next())
	{
	 	out.print("<tr><td><font size=2>"+sno+"</td>");
		String memcode=rs.getString("Member_Code");
		String memname=rs.getString("Member_Name");
		String deptname1=rs.getString("Dept_Name");
		String course_name=rs.getString("Course_Name");
		out.print("<td><font size=2>"+memcode+"</td>");
		out.print("<td><font size=2>"+memname+"</td>");
		out.print("<td><font size=2>"+deptname1+"</td>");
		out.print("<td><font size=2>"+course_name+"</td>");
		float faa=rs.getFloat("amount");
		out.print("<td><font size=2>"+faa+"</td>");
		sno=sno+1;
		fine=fine+faa;
		subcount=subcount+faa;

	}

	out.print("</table>");
	out.print("<br>");
	out.print("<br>");
	out.print("<br>");
	out.print("<font face=times new roman size=4><center>Total:  "+fine+"</center></font>");
	//out.print("<font face=times new roman size=4><center>Sub Total:  "+subcount+"</center></font>");

	}

	}


	if(report_type.equals("cumulative"))
	{

	strsqlq="select sum(fine_amount) as sum_fine, issue_history.member_code as member_code, member_mas.member_name as member_name, issue_history.access_no, book_mas.title, issue_history.issue_date, issue_history.due_date, issue_history.return_date, issue_history.fine_amount from issue_history, member_mas, book_mas where 1<2 "+strsql+" and issue_history.member_code=member_mas.member_code and issue_history.access_no=book_mas.access_no and fine_amount>0 and doc_type='THESIS' group by issue_history.member_code, member_mas.member_name, issue_history.access_no, book_mas.title, issue_history.issue_date, issue_history.due_date, issue_history.return_date, issue_history.fine_amount order by "+order+"";
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
	out.print("<center><font size=5>FINE REPORT</center></font>");
	out.print("<font size=3>Document Type :"+txtdoctype+"</font>");
	out.print("<br><table border=0 width=700 align=center CELLSPACING='0' BORDERCOLOR='#DADADA' BGCOLOR='#F5F5F5'> ");


	while(rs.next())
	{

	        float faa=rs.getFloat("sum_fine");
		sno=sno+1;
		fine=fine+faa;
		//subcount=subcount+tfa;

	}
	out.print("</table>");
        out.print("<br>");
	out.print("<br>");
	out.print("<br>");
	out.print("<font face=times new roman size=4><center>Total:  "+fine+"</center></font>");


	}

	}
	}


//FINE FOR BACK VOLUME

	if ((txtreporttype.equals("Fine")) && (txtdoctype.equals("BACK VOLUME"))){
	if(report_type.equals("listing"))
	{
	strsqlq="select sum(fine_amount) as sum_fine, issue_history.member_code as member_code, member_mas.member_name as member_name, issue_history.access_no, book_mas.title, issue_history.issue_date, issue_history.due_date, issue_history.return_date, issue_history.fine_amount from issue_history, member_mas, book_mas where 1<2 "+strsql+" and issue_history.member_code=member_mas.member_code and issue_history.access_no=book_mas.access_no and fine_amount>0 and doc_type='BACK VOLUME' group by issue_history.member_code, member_mas.member_name, issue_history.access_no, book_mas.title, issue_history.issue_date, issue_history.due_date, issue_history.return_date, issue_history.fine_amount order by "+order+"";

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
	out.print("<center><font size=5>FINE REPORT</center></font>");
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
	out.print("<th>Sno</th><th>Member code</th><th>Member Name</th><th>Access No</th><th>Title</th><th>Issue Date</th><th>Due Date</th><th>Return Date</th><th>Fine Amount</th>");

	while(rs.next())
	{
	 	out.print("<tr><td><font size=2>"+sno+"</td>");
		out.print("<td><font size=2>"+rs.getString("member_code")+"</td>");
		out.print("<td><font size=2>"+rs.getString("member_name")+"</td>");
		out.print("<td><font size=2>"+rs.getString("access_no")+"</td>");
		out.print("<td><font size=2>"+rs.getString("title")+"</td>");
		out.print("<td><font size=2>"+getdate(rs.getString("issue_date"))+"</td>");
		out.print("<td><font size=2>"+getdate(rs.getString("due_date"))+"</td>");
		out.print("<td><font size=2>"+getdate(rs.getString("return_date"))+"</td>");
                String fa=rs.getString("fine_amount");
		out.print("<td><font size=2>"+fa+"</td>");
		sno=sno+1;
		float tfa=Float.parseFloat(fa);
		fine=fine+tfa;
		subcount=subcount+tfa;

	}
	out.print("</table>");
	out.print("<br>");
	out.print("<br>");
	out.print("<font face=times new roman size=4><center>Grand Total:  "+fine+"</center></font>");
	}
	}

	if(report_type.equals("breakup"))
	{
	/* strsqlq="select sum(fine_amount) as sum_fine, issue_history.member_code as member_code, member_mas.member_name as member_name, issue_history.access_no, book_mas.title, issue_history.issue_date, issue_history.due_date, issue_history.return_date, issue_history.fine_amount from issue_history, member_mas, book_mas where 1<2 "+strsql+" and issue_history.member_code=member_mas.member_code and issue_history.access_no=book_mas.access_no and fine_amount>0 and doc_type='BACK VOLUME' group by issue_history.member_code, member_mas.member_name, issue_history.access_no, book_mas.title, issue_history.issue_date, issue_history.due_date, issue_history.return_date, issue_history.fine_amount order by "+order+""; */
 strsqlq="select course_mas.course_name, issue_history.member_code as member_code, department_mas.dept_name as dept_name, member_mas.member_name, Member_mas.Course_Code, sum(fine_amount) as amount from issue_history, department_mas, member_mas, course_mas where 1<2 "+strsql+" and doc_type='BACK VOLUME' and issue_history.member_code=member_mas.member_code and issue_history.access_no=book_mas.access_no and course_mas.course_code=member_mas.course_code and department_mas.dept_code=member_mas.dept_code and fine_amount>0 group by issue_history.member_code, dept_name, Member_mas.Course_Code, member_mas.member_name, course_mas.course_name";

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
	out.print("<center><font size=5>FINE REPORT</center></font>");
	out.print("<font size=3>Document Type :"+txtdoctype+"</font>");
	out.print("<br><table border=1 width=700 align=center CELLSPACING='0' BORDERCOLOR='#DADADA' BGCOLOR='#F5F5F5'> ");
	out.print("<th>Sno</th><th>Member Code</th><th>Member Name</th><th>Dept Name</th><th>Course Name</th><th>Fine Amount</th>");

	while(rs.next())
	{
	 	out.print("<tr><td><font size=2>"+sno+"</td>");
		String memcode=rs.getString("Member_Code");
		String memname=rs.getString("Member_Name");
		String deptname1=rs.getString("Dept_Name");
		String course_name=rs.getString("Course_Name");
		out.print("<td><font size=2>"+memcode+"</td>");
		out.print("<td><font size=2>"+memname+"</td>");
		out.print("<td><font size=2>"+deptname1+"</td>");
		out.print("<td><font size=2>"+course_name+"</td>");
		float faa=rs.getFloat("amount");
		out.print("<td><font size=2>"+faa+"</td>");
		sno=sno+1;
		fine=fine+faa;
		subcount=subcount+faa;

	}

	out.print("</table>");
	out.print("<br>");
	out.print("<br>");
	out.print("<br>");
	out.print("<font face=times new roman size=4><center>Total:  "+fine+"</center></font>");
	//out.print("<font face=times new roman size=4><center>Sub Total:  "+subcount+"</center></font>");

	}
	}


	if(report_type.equals("cumulative"))
	{

	strsqlq="select sum(fine_amount) as sum_fine, issue_history.member_code as member_code, member_mas.member_name as member_name, issue_history.access_no, book_mas.title, issue_history.issue_date, issue_history.due_date, issue_history.return_date, issue_history.fine_amount from issue_history, member_mas, book_mas where 1<2 "+strsql+" and issue_history.member_code=member_mas.member_code and issue_history.access_no=book_mas.access_no and fine_amount>0 and doc_type='BACK VOLUME' group by issue_history.member_code, member_mas.member_name, issue_history.access_no, book_mas.title, issue_history.issue_date, issue_history.due_date, issue_history.return_date, issue_history.fine_amount order by "+order+"";
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
	out.print("<center><font size=5>FINE REPORT</center></font>");
	out.print("<font size=3>Document Type :"+txtdoctype+"</font>");
	out.print("<br><table border=0 width=700 align=center CELLSPACING='0' BORDERCOLOR='#DADADA' BGCOLOR='#F5F5F5'> ");


	while(rs.next())
	{

	        float faa=rs.getFloat("sum_fine");
		sno=sno+1;
		fine=fine+faa;
		//subcount=subcount+tfa;

	}
	out.print("</table>");
        out.print("<br>");
	out.print("<br>");
	out.print("<br>");
	out.print("<font face=times new roman size=4><center>Total:  "+fine+"</center></font>");


	}

	}
	}

//FINE FOR PROCEEDING

	if ((txtreporttype.equals("Fine")) && (txtdoctype.equals("PROCEEDING"))){
	if(report_type.equals("listing"))
	{
	strsqlq="select sum(fine_amount) as sum_fine, issue_history.member_code as member_code, member_mas.member_name as member_name, issue_history.access_no, book_mas.title, issue_history.issue_date, issue_history.due_date, issue_history.return_date, issue_history.fine_amount from issue_history, member_mas, book_mas where 1<2 "+strsql+" and issue_history.member_code=member_mas.member_code and issue_history.access_no=book_mas.access_no and fine_amount>0 and doc_type='PROCEEDING' group by issue_history.member_code, member_mas.member_name, issue_history.access_no, book_mas.title, issue_history.issue_date, issue_history.due_date, issue_history.return_date, issue_history.fine_amount order by "+order+"";

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
	out.print("<center><font size=5>FINE REPORT</center></font>");
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
	out.print("<th>Sno</th><th>Member code</th><th>Member Name</th><th>Access No</th><th>Title</th><th>Issue Date</th><th>Due Date</th><th>Return Date</th><th>Fine Amount</th>");

	while(rs.next())
	{
	 	out.print("<tr><td><font size=2>"+sno+"</td>");
		out.print("<td><font size=2>"+rs.getString("member_code")+"</td>");
		out.print("<td><font size=2>"+rs.getString("member_name")+"</td>");
		out.print("<td><font size=2>"+rs.getString("access_no")+"</td>");
		out.print("<td><font size=2>"+rs.getString("title")+"</td>");
		out.print("<td><font size=2>"+getdate(rs.getString("issue_date"))+"</td>");
		out.print("<td><font size=2>"+getdate(rs.getString("due_date"))+"</td>");
		out.print("<td><font size=2>"+getdate(rs.getString("return_date"))+"</td>");
                String fa=rs.getString("fine_amount");
		out.print("<td><font size=2>"+fa+"</td>");
		sno=sno+1;
		float tfa=Float.parseFloat(fa);
		fine=fine+tfa;
		subcount=subcount+tfa;

	}
	out.print("</table>");
	out.print("<br>");
	out.print("<br>");
	out.print("<font face=times new roman size=4><center>Grand Total:  "+fine+"</center></font>");
	}
	}

	if(report_type.equals("breakup"))
	{
	/* strsqlq="select sum(fine_amount) as sum_fine, issue_history.member_code as member_code, member_mas.member_name as member_name, issue_history.access_no, book_mas.title, issue_history.issue_date, issue_history.due_date, issue_history.return_date, issue_history.fine_amount from issue_history, member_mas, book_mas where 1<2 "+strsql+" and issue_history.member_code=member_mas.member_code and issue_history.access_no=book_mas.access_no and fine_amount>0 and doc_type='PROCEEDING' group by issue_history.member_code, member_mas.member_name, issue_history.access_no, book_mas.title, issue_history.issue_date, issue_history.due_date, issue_history.return_date, issue_history.fine_amount order by "+order+""; */

 	strsqlq="select course_mas.course_name, issue_history.member_code as member_code, department_mas.dept_name as dept_name, member_mas.member_name, Member_mas.Course_Code, sum(fine_amount) as amount from issue_history, department_mas, member_mas, course_mas where 1<2 "+strsql+" and doc_type='PROCEEDING' and issue_history.member_code=member_mas.member_code and issue_history.access_no=book_mas.access_no and course_mas.course_code=member_mas.course_code and department_mas.dept_code=member_mas.dept_code and fine_amount>0 group by issue_history.member_code, dept_name, Member_mas.Course_Code, member_mas.member_name, course_mas.course_name";

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
	out.print("<center><font size=5>FINE REPORT</center></font>");
	out.print("<font size=3>Document Type :"+txtdoctype+"</font>");
	out.print("<br><table border=1 width=700 align=center CELLSPACING='0' BORDERCOLOR='#DADADA' BGCOLOR='#F5F5F5'> ");
	out.print("<th>Sno</th><th>Member Code</th><th>Member Name</th><th>Dept Name</th><th>Course Name</th><th>Fine Amount</th>");

	while(rs.next())
	{
	 	out.print("<tr><td><font size=2>"+sno+"</td>");
		String memcode=rs.getString("Member_Code");
		String memname=rs.getString("Member_Name");
		String deptname1=rs.getString("Dept_Name");
		String course_name=rs.getString("Course_Name");
		out.print("<td><font size=2>"+memcode+"</td>");
		out.print("<td><font size=2>"+memname+"</td>");
		out.print("<td><font size=2>"+deptname1+"</td>");
		out.print("<td><font size=2>"+course_name+"</td>");
		float faa=rs.getFloat("amount");
		out.print("<td><font size=2>"+faa+"</td>");
		sno=sno+1;
		fine=fine+faa;
		subcount=subcount+faa;

	}

	out.print("</table>");
	out.print("<br>");
	out.print("<br>");
	out.print("<br>");
	out.print("<font face=times new roman size=4><center>Total:  "+fine+"</center></font>");
	//out.print("<font face=times new roman size=4><center>Sub Total:  "+subcount+"</center></font>");

	}

	}


	if(report_type.equals("cumulative"))
	{

	strsqlq="select sum(fine_amount) as sum_fine, issue_history.member_code as member_code, member_mas.member_name as member_name, issue_history.access_no, book_mas.title, issue_history.issue_date, issue_history.due_date, issue_history.return_date, issue_history.fine_amount from issue_history, member_mas, book_mas where 1<2 "+strsql+" and issue_history.member_code=member_mas.member_code and issue_history.access_no=book_mas.access_no and fine_amount>0 and doc_type='PROCEEDING' group by issue_history.member_code, member_mas.member_name, issue_history.access_no, book_mas.title, issue_history.issue_date, issue_history.due_date, issue_history.return_date, issue_history.fine_amount order by "+order+"";
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
	out.print("<center><font size=5>FINE REPORT</center></font>");
	out.print("<font size=3>Document Type :"+txtdoctype+"</font>");
	out.print("<br><table border=0 width=700 align=center CELLSPACING='0' BORDERCOLOR='#DADADA' BGCOLOR='#F5F5F5'> ");


	while(rs.next())
	{

	        float faa=rs.getFloat("sum_fine");
		sno=sno+1;
		fine=fine+faa;
		//subcount=subcount+tfa;

	}
	out.print("</table>");
        out.print("<br>");
	out.print("<br>");
	out.print("<br>");
	out.print("<font face=times new roman size=4><center>Total:  "+fine+"</center></font>");


	}

	}
	}



	//FINE FOR REPORTS

	if ((txtreporttype.equals("Fine")) && (txtdoctype.equals("REPORT"))){
	if(report_type.equals("listing"))
	{
	strsqlq="select sum(fine_amount) as sum_fine, issue_history.member_code as member_code, member_mas.member_name as member_name, issue_history.access_no, book_mas.title, issue_history.issue_date, issue_history.due_date, issue_history.return_date, issue_history.fine_amount from issue_history, member_mas, book_mas where 1<2 "+strsql+" and issue_history.member_code=member_mas.member_code and issue_history.access_no=book_mas.access_no and fine_amount>0 and doc_type='REPORT' group by issue_history.member_code, member_mas.member_name, issue_history.access_no, book_mas.title, issue_history.issue_date, issue_history.due_date, issue_history.return_date, issue_history.fine_amount order by "+order+"";

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
	out.print("<center><font size=5>FINE REPORT</center></font>");
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
	out.print("<th>Sno</th><th>Member code</th><th>Member Name</th><th>Access No</th><th>Title</th><th>Issue Date</th><th>Due Date</th><th>Return Date</th><th>Fine Amount</th>");

	while(rs.next())
	{
	 	out.print("<tr><td><font size=2>"+sno+"</td>");
		out.print("<td><font size=2>"+rs.getString("member_code")+"</td>");
		out.print("<td><font size=2>"+rs.getString("member_name")+"</td>");
		out.print("<td><font size=2>"+rs.getString("access_no")+"</td>");
		out.print("<td><font size=2>"+rs.getString("title")+"</td>");
		out.print("<td><font size=2>"+getdate(rs.getString("issue_date"))+"</td>");
		out.print("<td><font size=2>"+getdate(rs.getString("due_date"))+"</td>");
		out.print("<td><font size=2>"+getdate(rs.getString("return_date"))+"</td>");
                String fa=rs.getString("fine_amount");
		out.print("<td><font size=2>"+fa+"</td>");
		sno=sno+1;
		float tfa=Float.parseFloat(fa);
		fine=fine+tfa;
		subcount=subcount+tfa;

	}
	out.print("</table>");
	out.print("<br>");
	out.print("<br>");
	out.print("<font face=times new roman size=4><center>Grand Total:  "+fine+"</center></font>");
	}
	}

	if(report_type.equals("breakup"))
	{
	/* strsqlq="select sum(fine_amount) as sum_fine, issue_history.member_code as member_code, member_mas.member_name as member_name, issue_history.access_no, book_mas.title, issue_history.issue_date, issue_history.due_date, issue_history.return_date, issue_history.fine_amount from issue_history, member_mas, book_mas where 1<2 "+strsql+" and issue_history.member_code=member_mas.member_code and issue_history.access_no=book_mas.access_no and fine_amount>0 and doc_type='REPORT' group by issue_history.member_code, member_mas.member_name, issue_history.access_no, book_mas.title, issue_history.issue_date, issue_history.due_date, issue_history.return_date, issue_history.fine_amount order by "+order+""; */
 	strsqlq="select course_mas.course_name, issue_history.member_code as member_code, department_mas.dept_name as dept_name, member_mas.member_name, Member_mas.Course_Code, sum(fine_amount) as amount from issue_history, department_mas, member_mas, course_mas where 1<2 "+strsql+" and doc_type='REPORT' and issue_history.member_code=member_mas.member_code and issue_history.access_no=book_mas.access_no and course_mas.course_code=member_mas.course_code and department_mas.dept_code=member_mas.dept_code and fine_amount>0 group by issue_history.member_code, dept_name, Member_mas.Course_Code, member_mas.member_name, course_mas.course_name";

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
	out.print("<center><font size=5>FINE REPORT</center></font>");
	out.print("<font size=3>Document Type :"+txtdoctype+"</font>");
	out.print("<br><table border=1 width=700 align=center CELLSPACING='0' BORDERCOLOR='#DADADA' BGCOLOR='#F5F5F5'> ");
	out.print("<th>Sno</th><th>Member Code</th><th>Member Name</th><th>Dept Name</th><th>Course Name</th><th>Fine Amount</th>");

	while(rs.next())
	{
	 	out.print("<tr><td><font size=2>"+sno+"</td>");
		String memcode=rs.getString("Member_Code");
		String memname=rs.getString("Member_Name");
		String deptname1=rs.getString("Dept_Name");
		String course_name=rs.getString("Course_Name");
		out.print("<td><font size=2>"+memcode+"</td>");
		out.print("<td><font size=2>"+memname+"</td>");
		out.print("<td><font size=2>"+deptname1+"</td>");
		out.print("<td><font size=2>"+course_name+"</td>");
		float faa=rs.getFloat("amount");
		out.print("<td><font size=2>"+faa+"</td>");
		sno=sno+1;
		fine=fine+faa;
		subcount=subcount+faa;

	}

	out.print("</table>");
	out.print("<br>");
	out.print("<br>");
	out.print("<br>");
	out.print("<font face=times new roman size=4><center>Total:  "+fine+"</center></font>");
	//out.print("<font face=times new roman size=4><center>Sub Total:  "+subcount+"</center></font>");

	}

	}


	if(report_type.equals("cumulative"))
	{

	strsqlq="select sum(fine_amount) as sum_fine, issue_history.member_code as member_code, member_mas.member_name as member_name, issue_history.access_no, book_mas.title, issue_history.issue_date, issue_history.due_date, issue_history.return_date, issue_history.fine_amount from issue_history, member_mas, book_mas where 1<2 "+strsql+" and issue_history.member_code=member_mas.member_code and issue_history.access_no=book_mas.access_no and fine_amount>0 and doc_type='REPORT' group by issue_history.member_code, member_mas.member_name, issue_history.access_no, book_mas.title, issue_history.issue_date, issue_history.due_date, issue_history.return_date, issue_history.fine_amount order by "+order+"";
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
	out.print("<center><font size=5>FINE REPORT</center></font>");
	out.print("<font size=3>Document Type :"+txtdoctype+"</font>");
	out.print("<br><table border=0 width=700 align=center CELLSPACING='0' BORDERCOLOR='#DADADA' BGCOLOR='#F5F5F5'> ");


	while(rs.next())
	{

	        float faa=rs.getFloat("sum_fine");
		sno=sno+1;
		fine=fine+faa;
		//subcount=subcount+tfa;

	}
	out.print("</table>");
        out.print("<br>");
	out.print("<br>");
	out.print("<br>");
	out.print("<font face=times new roman size=4><center>Total:  "+fine+"</center></font>");


	}

	}
	}

//FINE FOR NON BOOK

	if ((txtreporttype.equals("Fine")) && (txtdoctype.equals("NON BOOK"))){
	if(report_type.equals("listing"))
	{
	strsqlq="select sum(fine_amount) as sum_fine, issue_history.member_code as member_code, member_mas.member_name as member_name, issue_history.access_no, book_mas.title, issue_history.issue_date, issue_history.due_date, issue_history.return_date, issue_history.fine_amount from issue_history, member_mas, book_mas where 1<2 "+strsql+" and issue_history.member_code=member_mas.member_code and issue_history.access_no=book_mas.access_no and fine_amount>0 and doc_type='NON BOOK' group by issue_history.member_code, member_mas.member_name, issue_history.access_no, book_mas.title, issue_history.issue_date, issue_history.due_date, issue_history.return_date, issue_history.fine_amount order by "+order+"";

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
	out.print("<center><font size=5>FINE REPORT</center></font>");
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
	out.print("<th>Sno</th><th>Member code</th><th>Member Name</th><th>Access No</th><th>Title</th><th>Issue Date</th><th>Due Date</th><th>Return Date</th><th>Fine Amount</th>");

	while(rs.next())
	{
	 	out.print("<tr><td><font size=2>"+sno+"</td>");
		out.print("<td><font size=2>"+rs.getString("member_code")+"</td>");
		out.print("<td><font size=2>"+rs.getString("member_name")+"</td>");
		out.print("<td><font size=2>"+rs.getString("access_no")+"</td>");
		out.print("<td><font size=2>"+rs.getString("title")+"</td>");
		out.print("<td><font size=2>"+getdate(rs.getString("issue_date"))+"</td>");
		out.print("<td><font size=2>"+getdate(rs.getString("due_date"))+"</td>");
		out.print("<td><font size=2>"+getdate(rs.getString("return_date"))+"</td>");
                String fa=rs.getString("fine_amount");
		out.print("<td><font size=2>"+fa+"</td>");
		sno=sno+1;
		float tfa=Float.parseFloat(fa);
		fine=fine+tfa;
		subcount=subcount+tfa;

	}
	out.print("</table>");
	out.print("<br>");
	out.print("<br>");
	out.print("<font face=times new roman size=4><center>Grand Total:  "+fine+"</center></font>");
	}
	}

	if(report_type.equals("breakup"))
	{
	/* strsqlq="select sum(fine_amount) as sum_fine, issue_history.member_code as member_code, member_mas.member_name as member_name, issue_history.access_no, book_mas.title, issue_history.issue_date, issue_history.due_date, issue_history.return_date, issue_history.fine_amount from issue_history, member_mas, book_mas where 1<2 "+strsql+" and issue_history.member_code=member_mas.member_code and issue_history.access_no=book_mas.access_no and fine_amount>0 and doc_type='NON BOOK' group by issue_history.member_code, member_mas.member_name, issue_history.access_no, book_mas.title, issue_history.issue_date, issue_history.due_date, issue_history.return_date, issue_history.fine_amount order by "+order+""; */

 	strsqlq="select course_mas.course_name, issue_history.member_code as member_code, department_mas.dept_name as dept_name, member_mas.member_name, Member_mas.Course_Code, sum(fine_amount) as amount from issue_history, department_mas, member_mas, course_mas where 1<2 "+strsql+" and doc_type='NON BOOK' and issue_history.member_code=member_mas.member_code and issue_history.access_no=book_mas.access_no and course_mas.course_code=member_mas.course_code and department_mas.dept_code=member_mas.dept_code and fine_amount>0 group by issue_history.member_code, dept_name, Member_mas.Course_Code, member_mas.member_name, course_mas.course_name";

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
	out.print("<center><font size=5>FINE REPORT</center></font>");
	out.print("<font size=3>Document Type :"+txtdoctype+"</font>");
	out.print("<br><table border=1 width=700 align=center CELLSPACING='0' BORDERCOLOR='#DADADA' BGCOLOR='#F5F5F5'> ");
	out.print("<th>Sno</th><th>Member Code</th><th>Member Name</th><th>Dept Name</th><th>Course Name</th><th>Fine Amount</th>");

	while(rs.next())
	{
	 	out.print("<tr><td><font size=2>"+sno+"</td>");
		String memcode=rs.getString("Member_Code");
		String memname=rs.getString("Member_Name");
		String deptname1=rs.getString("Dept_Name");
		String course_name=rs.getString("Course_Name");
		out.print("<td><font size=2>"+memcode+"</td>");
		out.print("<td><font size=2>"+memname+"</td>");
		out.print("<td><font size=2>"+deptname1+"</td>");
		out.print("<td><font size=2>"+course_name+"</td>");
		float faa=rs.getFloat("amount");
		out.print("<td><font size=2>"+faa+"</td>");
		sno=sno+1;
		fine=fine+faa;
		subcount=subcount+faa;

	}

	out.print("</table>");
	out.print("<br>");
	out.print("<br>");
	out.print("<br>");
	out.print("<font face=times new roman size=4><center>Total:  "+fine+"</center></font>");
	//out.print("<font face=times new roman size=4><center>Sub Total:  "+subcount+"</center></font>");

	}

	}


	if(report_type.equals("cumulative"))
	{

	strsqlq="select sum(fine_amount) as sum_fine, issue_history.member_code as member_code, member_mas.member_name as member_name, issue_history.access_no, book_mas.title, issue_history.issue_date, issue_history.due_date, issue_history.return_date, issue_history.fine_amount from issue_history, member_mas, book_mas where 1<2 "+strsql+" and issue_history.member_code=member_mas.member_code and issue_history.access_no=book_mas.access_no and fine_amount>0 and doc_type='NON BOOK' group by issue_history.member_code, member_mas.member_name, issue_history.access_no, book_mas.title, issue_history.issue_date, issue_history.due_date, issue_history.return_date, issue_history.fine_amount order by "+order+"";
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
	out.print("<center><font size=5>FINE REPORT</center></font>");
	out.print("<font size=3>Document Type :"+txtdoctype+"</font>");
	out.print("<br><table border=0 width=700 align=center CELLSPACING='0' BORDERCOLOR='#DADADA' BGCOLOR='#F5F5F5'> ");


	while(rs.next())
	{

	        float faa=rs.getFloat("sum_fine");
		sno=sno+1;
		fine=fine+faa;
		//subcount=subcount+tfa;

	}
	out.print("</table>");
        out.print("<br>");
	out.print("<br>");
	out.print("<br>");
	out.print("<font face=times new roman size=4><center>Total:  "+fine+"</center></font>");


	}

	}
	}


//FINE FOR STANDARD

	if ((txtreporttype.equals("Fine")) && (txtdoctype.equals("STANDARD"))){
	if(report_type.equals("listing"))
	{
	strsqlq="select sum(fine_amount) as sum_fine, issue_history.member_code as member_code, member_mas.member_name as member_name, issue_history.access_no, book_mas.title, issue_history.issue_date, issue_history.due_date, issue_history.return_date, issue_history.fine_amount from issue_history, member_mas, book_mas where 1<2 "+strsql+" and issue_history.member_code=member_mas.member_code and issue_history.access_no=book_mas.access_no and fine_amount>0 and doc_type='STANDARD' group by issue_history.member_code, member_mas.member_name, issue_history.access_no, book_mas.title, issue_history.issue_date, issue_history.due_date, issue_history.return_date, issue_history.fine_amount order by "+order+"";

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
	out.print("<center><font size=5>FINE REPORT</center></font>");
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
	out.print("<th>Sno</th><th>Member code</th><th>Member Name</th><th>Access No</th><th>Title</th><th>Issue Date</th><th>Due Date</th><th>Return Date</th><th>Fine Amount</th>");

	while(rs.next())
	{
	 	out.print("<tr><td><font size=2>"+sno+"</td>");
		out.print("<td><font size=2>"+rs.getString("member_code")+"</td>");
		out.print("<td><font size=2>"+rs.getString("member_name")+"</td>");
		out.print("<td><font size=2>"+rs.getString("access_no")+"</td>");
		out.print("<td><font size=2>"+rs.getString("title")+"</td>");
		out.print("<td><font size=2>"+getdate(rs.getString("issue_date"))+"</td>");
		out.print("<td><font size=2>"+getdate(rs.getString("due_date"))+"</td>");
		out.print("<td><font size=2>"+getdate(rs.getString("return_date"))+"</td>");
                String fa=rs.getString("fine_amount");
		out.print("<td><font size=2>"+fa+"</td>");
		sno=sno+1;
		float tfa=Float.parseFloat(fa);
		fine=fine+tfa;
		subcount=subcount+tfa;

	}
	out.print("</table>");
	out.print("<br>");
	out.print("<br>");
	out.print("<font face=times new roman size=4><center>Grand Total:  "+fine+"</center></font>");
	}
	}

	if(report_type.equals("breakup"))
	{
	/* strsqlq="select sum(fine_amount) as sum_fine, issue_history.member_code as member_code, member_mas.member_name as member_name, issue_history.access_no, book_mas.title, issue_history.issue_date, issue_history.due_date, issue_history.return_date, issue_history.fine_amount from issue_history, member_mas, book_mas where 1<2 "+strsql+" and issue_history.member_code=member_mas.member_code and issue_history.access_no=book_mas.access_no and fine_amount>0 and doc_type='STANDARD' group by issue_history.member_code, member_mas.member_name, issue_history.access_no, book_mas.title, issue_history.issue_date, issue_history.due_date, issue_history.return_date, issue_history.fine_amount order by "+order+""; */

 strsqlq="select course_mas.course_name, issue_history.member_code as member_code, department_mas.dept_name as dept_name, member_mas.member_name, Member_mas.Course_Code, sum(fine_amount) as amount from issue_history, department_mas, member_mas, course_mas where 1<2 "+strsql+" and doc_type='STANDARD' and issue_history.member_code=member_mas.member_code and issue_history.access_no=book_mas.access_no and course_mas.course_code=member_mas.course_code and department_mas.dept_code=member_mas.dept_code and fine_amount>0 group by issue_history.member_code, dept_name, Member_mas.Course_Code, member_mas.member_name, course_mas.course_name";

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
	out.print("<center><font size=5>FINE REPORT</center></font>");
	out.print("<font size=3>Document Type :"+txtdoctype+"</font>");
	out.print("<br><table border=1 width=700 align=center CELLSPACING='0' BORDERCOLOR='#DADADA' BGCOLOR='#F5F5F5'> ");
	out.print("<th>Sno</th><th>Member Code</th><th>Member Name</th><th>Dept Name</th><th>Course Name</th><th>Fine Amount</th>");

	while(rs.next())
	{
	 	out.print("<tr><td><font size=2>"+sno+"</td>");
		String memcode=rs.getString("Member_Code");
		String memname=rs.getString("Member_Name");
		String deptname1=rs.getString("Dept_Name");
		String course_name=rs.getString("Course_Name");
		out.print("<td><font size=2>"+memcode+"</td>");
		out.print("<td><font size=2>"+memname+"</td>");
		out.print("<td><font size=2>"+deptname1+"</td>");
		out.print("<td><font size=2>"+course_name+"</td>");
		float faa=rs.getFloat("amount");
		out.print("<td><font size=2>"+faa+"</td>");
		sno=sno+1;
		fine=fine+faa;
		subcount=subcount+faa;

	}

	out.print("</table>");
	out.print("<br>");
	out.print("<br>");
	out.print("<br>");
	out.print("<font face=times new roman size=4><center>Total:  "+fine+"</center></font>");
	//out.print("<font face=times new roman size=4><center>Sub Total:  "+subcount+"</center></font>");

	}

	}


	if(report_type.equals("cumulative"))
	{

	strsqlq="select sum(fine_amount) as sum_fine, issue_history.member_code as member_code, member_mas.member_name as member_name, issue_history.access_no, book_mas.title, issue_history.issue_date, issue_history.due_date, issue_history.return_date, issue_history.fine_amount from issue_history, member_mas, book_mas where 1<2 "+strsql+" and issue_history.member_code=member_mas.member_code and issue_history.access_no=book_mas.access_no and fine_amount>0 and doc_type='STANDARD' group by issue_history.member_code, member_mas.member_name, issue_history.access_no, book_mas.title, issue_history.issue_date, issue_history.due_date, issue_history.return_date, issue_history.fine_amount order by "+order+"";
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
	out.print("<center><font size=5>FINE REPORT</center></font>");
	out.print("<font size=3>Document Type :"+txtdoctype+"</font>");
	out.print("<br><table border=0 width=700 align=center CELLSPACING='0' BORDERCOLOR='#DADADA' BGCOLOR='#F5F5F5'> ");


	while(rs.next())
	{

	        float faa=rs.getFloat("sum_fine");
		sno=sno+1;
		fine=fine+faa;
		//subcount=subcount+tfa;

	}
	out.print("</table>");
        out.print("<br>");
	out.print("<br>");
	out.print("<br>");
	out.print("<font face=times new roman size=4><center>Total:  "+fine+"</center></font>");


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






