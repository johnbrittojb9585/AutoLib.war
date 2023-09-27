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
