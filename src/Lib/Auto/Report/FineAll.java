package Lib.Auto.Report;
import Lib.Auto.Report.reportbean;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.*;


import Common.DBConnection;
import Common.Security;

public class FineAll extends HttpServlet{

	String txtreporttype="",txtaccessno="",txtmembercode="",txtdepartment="",txtfdate="",txttdate="",txtgroup="";
	String order1="",order2="",order3="",str="",strsql="",order,strsqlq="",fa="";
	String deptname="",groupname="",staff="",Reptfrom="",txtcourse="",txtdoctype="",report_type="",coursename="", count_book="", fine_amount="", coursename1="";
	int sno=0, total=0;
	float fine=0,subcount=0,faa=0;
	String protocol="";

	ArrayList ser=new ArrayList();
	ArrayList sc=new ArrayList();
	reportbean ob=new reportbean();
	ResourceBundle rb = ResourceBundle.getBundle("LocalStrings");
	java.sql.Connection con=null;
	java.sql.Statement st=null;
    java.sql.ResultSet rs=null;
	//con=(java.sql.Connection)session.getAttribute("con");
	//st=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
	java.sql.PreparedStatement pstmt=null;
	/* String getdate(String datechk) {
    	    	   java.util.StringTokenizer stz_is=new java.util.StringTokenizer(datechk,"-");
		    int biy=Integer.parseInt(stz_is.nextToken());
		     int bim=Integer.parseInt(stz_is.nextToken());
		    int bid=Integer.parseInt(stz_is.nextToken());
	     	   String bissue_m=new Integer(bim).toString();
				    if(bissue_m.length()==1)
				    bissue_m="0"+bissue_m;
					String bissue_d=new Integer(bid).toString();
					if(bissue_d.length()==1)
				    bissue_d="0"+bissue_d;
					String BISSDATE=bissue_d+"/"+bissue_m+"/"+biy;
	return BISSDATE;
  } */

	/*public void init() throws ServletException
	{
	String driver=getServletContext().getInitParameter("driver");
	protocol=getServletContext().getInitParameter("protocol");


		if(driver==null || protocol==null){
			throw new UnavailableException("Not Found");
		}


		try {
			rb = ResourceBundle.getBundle("LocalStrings");
		} catch (Exception e) {throw new ServletException(e);}

		try {
			
			Class.forName(driver);
			con =
				DriverManager.getConnection(
					protocol,
					rb.getString("Username"),
					rb.getString("Password"));
				} catch (Exception e) {
			throw new ServletException(e);

		}

	}*/

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
		    //out.print(Today_Date);
/*-----------------------------------------------------------------------------------------------------------------------------------------------------------------------*/
 	txtreporttype =request.getParameter("reporttype");
	txtaccessno =request.getParameter("txtaccessno");
	txtmembercode =request.getParameter("txtmembercode");
	txtdepartment =request.getParameter("txtdepartment");
	txtgroup =request.getParameter("txtgroup");
	txtcourse =request.getParameter("txtcourse");
	txtdoctype=request.getParameter("txtdoctype");
	txtfdate =request.getParameter("txtfdate");
	txttdate =request.getParameter("txttdate");
	//txtaccessno=getParam( request, "txtaccessno");
	//txtmembercode =getParam( request, "txtmembercode");
	//txtfdate =getParam( request, "txtfdate");
	//txttdate =getParam( request, "txttdate");
   // Reptfrom=request.getParameter("Reptfrom");
	/*---------------oder by--------------------------------------------------------*/

	order1 =request.getParameter("order1");
	order2 =request.getParameter("order2");
	order3 =request.getParameter("order3");

	report_type=request.getParameter("report_type");
	if(!txtdepartment.equals("NO")){
	str = "select Dept_Name from department_mas where Dept_Code =" + txtdepartment;
	rs=st.executeQuery(str);
	while (rs.next()){
      deptname = rs.getString("Dept_Name");
	  }
	//%><script>alert("<%=deptname%>");</script><%
	}

	if(!txtgroup.equals("NO")){
	str = "select Group_Name from group_mas where Group_Code ="+txtgroup;
	rs=st.executeQuery(str);
	while (rs.next()) groupname = rs.getString("Group_Name");
	//%><script>alert("<%=groupname%>");</script><%
	}

	if(!txtcourse.equals("NO")){
	str = "select course_Name from course_mas where course_code="+txtcourse;
	rs=st.executeQuery(str);
	while (rs.next()) coursename = rs.getString("Course_Name");
	//%><script>alert("<%=coursename%>");</script><%
	}
	strsql = "";
	if (!txtmembercode.equals("") && (txtreporttype.equals("Fine")) )
	{
	strsql = strsql + " and member_mas.Member_Code = '"+txtmembercode+"'";
	}
	if (!txtdepartment.equals("NO") && (txtreporttype.equals("Fine")) )
	{
	strsql = strsql + " and member_mas.Dept_Code = '"+txtdepartment+"'";
	}

	if (!txtgroup.equals("NO"))
	strsql = strsql + "  and Group_Code = '" +txtgroup+"'";

	if (!txtcourse.equals("NO"))
	strsql = strsql + "  and course_code = '" +txtcourse+"'";
	if(!txtaccessno.equals("") && txtreporttype.equals("Fine") )
	{
	strsql = strsql + " and book_mas.access_no='"+txtaccessno+"'";
	}
	if ((!txttdate.equals("")) &&  (!txtfdate.equals("")))
	{
	String Date_From=getdate1(request.getParameter("txtfdate"));
	String Date_To=getdate1(request.getParameter("txttdate"));
	if ((txtreporttype.equals("Fine")) ||(txtreporttype.equals("Return")))
	strsql = strsql + " and Return_Date between '"+Date_From+"' and '" +Date_To+"'";
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

	//strsqlq="select sum(fine_amount) as sum_fine, issue_history.member_code as member_code, member_mas.member_name as member_name, issue_history.access_no, book_mas.title, issue_history.issue_date, issue_history.due_date, issue_history.return_date, issue_history.fine_amount from issue_history, member_mas, book_mas where 1<2 "+strsql+" and issue_history.member_code=member_mas.member_code and issue_history.access_no=book_mas.access_no and fine_amount>0 and doc_type='BOOK' group by issue_history.member_code, member_mas.member_name, issue_history.access_no, book_mas.title, issue_history.issue_date, issue_history.due_date, issue_history.return_date, issue_history.fine_amount "+order+"";
	
	if(!order.equals("")){
		//strsqlq = "select * from juserstat_all where 1<2 "+strsql+"  and issue_type='ISSUE' order by '"+order+"'";
		if(!order.contains("Issue_Date") && !order.contains("Due_Date")){
		strsqlq="select sum(fine_amount) as sum_fine, issue_history.member_code as member_code, member_mas.member_name as member_name, issue_history.access_no, book_mas.title, issue_history.issue_date, issue_history.due_date, issue_history.return_date, issue_history.fine_amount from issue_history, member_mas, book_mas, sort_book where 1<2 and issue_history.access_no=sort_book.access_no "+strsql+" and issue_history.member_code=member_mas.member_code and issue_history.access_no=book_mas.access_no and fine_amount>0 and doc_type='BOOK' group by issue_history.member_code, member_mas.member_name, issue_history.access_no, book_mas.title, issue_history.issue_date, issue_history.due_date, issue_history.return_date, issue_history.fine_amount order by n1,n2,n3";
		}
		else if(order.contains("Issue_Date") && !order.contains("Due_Date")){
		strsqlq="select sum(fine_amount) as sum_fine, issue_history.member_code as member_code, member_mas.member_name as member_name, issue_history.access_no, book_mas.title, issue_history.issue_date, issue_history.due_date, issue_history.return_date, issue_history.fine_amount from issue_history, member_mas, book_mas, sort_book where 1<2 and issue_history.access_no=sort_book.access_no "+strsql+" and issue_history.member_code=member_mas.member_code and issue_history.access_no=book_mas.access_no and fine_amount>0 and doc_type='BOOK' group by issue_history.member_code, member_mas.member_name, issue_history.access_no, book_mas.title, issue_history.issue_date, issue_history.due_date, issue_history.return_date, issue_history.fine_amount order by Issue_Date";	
		}
		else if(!order.contains("Issue_Date") && order.contains("Due_Date")){
		strsqlq="select sum(fine_amount) as sum_fine, issue_history.member_code as member_code, member_mas.member_name as member_name, issue_history.access_no, book_mas.title, issue_history.issue_date, issue_history.due_date, issue_history.return_date, issue_history.fine_amount from issue_history, member_mas, book_mas, sort_book where 1<2 and issue_history.access_no=sort_book.access_no "+strsql+" and issue_history.member_code=member_mas.member_code and issue_history.access_no=book_mas.access_no and fine_amount>0 and doc_type='BOOK' group by issue_history.member_code, member_mas.member_name, issue_history.access_no, book_mas.title, issue_history.issue_date, issue_history.due_date, issue_history.return_date, issue_history.fine_amount order by Due_Date";	
		}
		else if(order.contains("Issue_Date") && order.contains("Due_Date")){
		strsqlq="select sum(fine_amount) as sum_fine, issue_history.member_code as member_code, member_mas.member_name as member_name, issue_history.access_no, book_mas.title, issue_history.issue_date, issue_history.due_date, issue_history.return_date, issue_history.fine_amount from issue_history, member_mas, book_mas, sort_book where 1<2 and issue_history.access_no=sort_book.access_no "+strsql+" and issue_history.member_code=member_mas.member_code and issue_history.access_no=book_mas.access_no and fine_amount>0 and doc_type='BOOK' group by issue_history.member_code, member_mas.member_name, issue_history.access_no, book_mas.title, issue_history.issue_date, issue_history.due_date, issue_history.return_date, issue_history.fine_amount order by Issue_Date,Due_Date";	
		}
		}
	else{
		strsqlq="select sum(fine_amount) as sum_fine, issue_history.member_code as member_code, member_mas.member_name as member_name, issue_history.access_no, book_mas.title, issue_history.issue_date, issue_history.due_date, issue_history.return_date, issue_history.fine_amount from issue_history, member_mas, book_mas where 1<2 "+strsql+" and issue_history.member_code=member_mas.member_code and issue_history.access_no=book_mas.access_no and fine_amount>0 and doc_type='BOOK' group by issue_history.member_code, member_mas.member_name, issue_history.access_no, book_mas.title, issue_history.issue_date, issue_history.due_date, issue_history.return_date, issue_history.fine_amount "+order+"";
	}
	// out.print(strsqlq);
	pstmt=con.prepareStatement(strsqlq);
	rs=pstmt.executeQuery();
	//rs=st.executeQuery(strsqlq);
  	if(!rs.next())
	{

	}
	else
	{
	rs.beforeFirst();
	sno=1;
	subcount=0;
	fine=0;
	out.print("<font size=4>Document Type :BOOK</font><br>");
	out.print("<br><table border=1 width=700 align=center CELLSPACING='0' BORDERCOLOR='#DADADA' BGCOLOR='#F5F5F5'>");
	out.print("<tr><th>Sno</th><th>Member code</th><th>Member Name</th><th>Access No</th><th>Title</th><th>Issue Date</th><th>Due Date</th><th>Return Date</th><th>Fine Amount</th></tr>");

	while(rs.next())
	{
		String member_code=rs.getString("member_code");
		String member_name=rs.getString("member_name");
		String access_no=rs.getString("access_no");
		String title=rs.getString("title");
		String issue_date=getdate(rs.getString("issue_date"));
		String due_date=getdate(rs.getString("due_date"));
		String return_date=getdate(rs.getString("return_date"));
		String fa=rs.getString("sum_fine");


		ser.add(member_code);
		ser.add(member_name);
		ser.add(access_no);
		ser.add(title);
		ser.add(issue_date);
		ser.add(due_date);
		ser.add(return_date);
		ser.add(fa);
	}
		ob.setAl(ser);
		sc=(ArrayList)ob.getAl();
		for(int i=0;i<sc.size();i++){
		 out.print("<tr><td><font size=2>"+sno+"</td>");
		 out.print("<td>"+sc.get(i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 String fa=(String)sc.get(++i);
		 out.print("<td>"+fa+"</td>");
                 out.print("</tr>");
		 sno=sno+1;
		  float tfa=Float.parseFloat(fa);
		 fine=fine+tfa;
		 subcount=subcount+tfa;
	}
	sc.clear();


	out.print("</table>");
	out.print("<br>");
	out.print("<br>");
	out.print("<br>");
	out.print("<font face=times new roman size=4><center>Grand Total:  "+fine+"</center></font>");
	out.print("<font face=times new roman size=4><center>Net Total:  "+subcount+"</center></font>");
	}
	//strsqlq="select sum(fine_amount) as sum_fine, issue_history.member_code as member_code, member_mas.member_name as member_name, issue_history.access_no, book_mas.title, issue_history.issue_date, issue_history.due_date, issue_history.return_date, issue_history.fine_amount from issue_history, member_mas, book_mas where 1<2 "+strsql+" and issue_history.member_code=member_mas.member_code and issue_history.access_no=book_mas.access_no and fine_amount>0 and doc_type='PROCEEDING' group by issue_history.member_code, member_mas.member_name, issue_history.access_no, book_mas.title, issue_history.issue_date, issue_history.due_date, issue_history.return_date, issue_history.fine_amount "+order+"";
	if(!order.equals("")){
		//strsqlq = "select * from juserstat_all where 1<2 "+strsql+"  and issue_type='ISSUE' order by '"+order+"'";
		if(!order.contains("Issue_Date") && !order.contains("Due_Date")){
		strsqlq="select sum(fine_amount) as sum_fine, issue_history.member_code as member_code, member_mas.member_name as member_name, issue_history.access_no, book_mas.title, issue_history.issue_date, issue_history.due_date, issue_history.return_date, issue_history.fine_amount from issue_history, member_mas, book_mas, sort_book where 1<2 and issue_history.access_no=sort_book.access_no "+strsql+" and issue_history.member_code=member_mas.member_code and issue_history.access_no=book_mas.access_no and fine_amount>0 and doc_type='PROCEEDING' group by issue_history.member_code, member_mas.member_name, issue_history.access_no, book_mas.title, issue_history.issue_date, issue_history.due_date, issue_history.return_date, issue_history.fine_amount order by n1,n2,n3";
		}
		else if(order.contains("Issue_Date") && !order.contains("Due_Date")){
		strsqlq="select sum(fine_amount) as sum_fine, issue_history.member_code as member_code, member_mas.member_name as member_name, issue_history.access_no, book_mas.title, issue_history.issue_date, issue_history.due_date, issue_history.return_date, issue_history.fine_amount from issue_history, member_mas, book_mas, sort_book where 1<2 and issue_history.access_no=sort_book.access_no "+strsql+" and issue_history.member_code=member_mas.member_code and issue_history.access_no=book_mas.access_no and fine_amount>0 and doc_type='PROCEEDING' group by issue_history.member_code, member_mas.member_name, issue_history.access_no, book_mas.title, issue_history.issue_date, issue_history.due_date, issue_history.return_date, issue_history.fine_amount order by Issue_Date";	
		}
		else if(!order.contains("Issue_Date") && order.contains("Due_Date")){
		strsqlq="select sum(fine_amount) as sum_fine, issue_history.member_code as member_code, member_mas.member_name as member_name, issue_history.access_no, book_mas.title, issue_history.issue_date, issue_history.due_date, issue_history.return_date, issue_history.fine_amount from issue_history, member_mas, book_mas, sort_book where 1<2 and issue_history.access_no=sort_book.access_no "+strsql+" and issue_history.member_code=member_mas.member_code and issue_history.access_no=book_mas.access_no and fine_amount>0 and doc_type='PROCEEDING' group by issue_history.member_code, member_mas.member_name, issue_history.access_no, book_mas.title, issue_history.issue_date, issue_history.due_date, issue_history.return_date, issue_history.fine_amount order by Due_Date";	
		}
		else if(order.contains("Issue_Date") && order.contains("Due_Date")){
		strsqlq="select sum(fine_amount) as sum_fine, issue_history.member_code as member_code, member_mas.member_name as member_name, issue_history.access_no, book_mas.title, issue_history.issue_date, issue_history.due_date, issue_history.return_date, issue_history.fine_amount from issue_history, member_mas, book_mas, sort_book where 1<2 and issue_history.access_no=sort_book.access_no "+strsql+" and issue_history.member_code=member_mas.member_code and issue_history.access_no=book_mas.access_no and fine_amount>0 and doc_type='PROCEEDING' group by issue_history.member_code, member_mas.member_name, issue_history.access_no, book_mas.title, issue_history.issue_date, issue_history.due_date, issue_history.return_date, issue_history.fine_amount order by Issue_Date,Due_Date";	
		}
		}
	else{
		strsqlq="select sum(fine_amount) as sum_fine, issue_history.member_code as member_code, member_mas.member_name as member_name, issue_history.access_no, book_mas.title, issue_history.issue_date, issue_history.due_date, issue_history.return_date, issue_history.fine_amount from issue_history, member_mas, book_mas where 1<2 "+strsql+" and issue_history.member_code=member_mas.member_code and issue_history.access_no=book_mas.access_no and fine_amount>0 and doc_type='PROCEEDING' group by issue_history.member_code, member_mas.member_name, issue_history.access_no, book_mas.title, issue_history.issue_date, issue_history.due_date, issue_history.return_date, issue_history.fine_amount "+order+"";
	}

	//out.print(strsqlq);
	//rs=st.executeQuery(strsqlq);
	pstmt=con.prepareStatement(strsqlq);
	rs=pstmt.executeQuery();
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
	out.print("<tr><th>Sno</th><th>Member code</th><th>Member Name</th><th>Access No</th><th>Title</th><th>Issue Date</th><th>Due Date</th><th>Return Date</th><th>Fine Amount</th></tr>");

	while(rs.next())
	{

		String member_code=rs.getString("member_code");
		String member_name=rs.getString("member_name");
		String access_no=rs.getString("access_no");
		String title=rs.getString("title");
		String issue_date=getdate(rs.getString("issue_date"));
		String due_date=getdate(rs.getString("due_date"));
		String return_date=getdate(rs.getString("return_date"));
		String fa=rs.getString("sum_fine");


		ser.add(member_code);
		ser.add(member_name);
		ser.add(access_no);
		ser.add(title);
		ser.add(issue_date);
		ser.add(due_date);
		ser.add(return_date);
		ser.add(fa);


	}
		ob.setAl(ser);
		sc=(ArrayList)ob.getAl();
		for(int i=0;i<sc.size();i++){
		 out.print("<tr><td><font size=2>"+sno+"</td>");
		 out.print("<td>"+sc.get(i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 String fa=(String)sc.get(++i);
		 out.print("<td>"+fa+"</td>");
                 out.print("</tr>");
		 sno=sno+1;
		  float tfa=Float.parseFloat(fa);
		 fine=fine+tfa;
		 subcount=subcount+tfa;
	}
	sc.clear();
	out.print("</table>");
	out.print("<br>");
	out.print("<br>");
	out.print("<font face=times new roman size=4><center>Grand Total:  "+fine+"</center></font>");
	out.print("<font face=times new roman size=4><center>Net Total:  "+subcount+"</center></font>");
	}
	//strsqlq="select sum(fine_amount) as sum_fine, issue_history.member_code as member_code, member_mas.member_name as member_name, issue_history.access_no, book_mas.title, issue_history.issue_date, issue_history.due_date, issue_history.return_date, issue_history.fine_amount from issue_history, member_mas, book_mas where 1<2 "+strsql+" and issue_history.member_code=member_mas.member_code and issue_history.access_no=book_mas.access_no and fine_amount>0 and doc_type='THESIS' group by issue_history.member_code, member_mas.member_name, issue_history.access_no, book_mas.title, issue_history.issue_date, issue_history.due_date, issue_history.return_date, issue_history.fine_amount "+order+"";
	if(!order.equals("")){
		//strsqlq = "select * from juserstat_all where 1<2 "+strsql+"  and issue_type='ISSUE' order by '"+order+"'";
		if(!order.contains("Issue_Date") && !order.contains("Due_Date")){
		strsqlq="select sum(fine_amount) as sum_fine, issue_history.member_code as member_code, member_mas.member_name as member_name, issue_history.access_no, book_mas.title, issue_history.issue_date, issue_history.due_date, issue_history.return_date, issue_history.fine_amount from issue_history, member_mas, book_mas, sort_book where 1<2 and issue_history.access_no=sort_book.access_no "+strsql+" and issue_history.member_code=member_mas.member_code and issue_history.access_no=book_mas.access_no and fine_amount>0 and doc_type='THESIS' group by issue_history.member_code, member_mas.member_name, issue_history.access_no, book_mas.title, issue_history.issue_date, issue_history.due_date, issue_history.return_date, issue_history.fine_amount order by n1,n2,n3";
		}
		else if(order.contains("Issue_Date") && !order.contains("Due_Date")){
		strsqlq="select sum(fine_amount) as sum_fine, issue_history.member_code as member_code, member_mas.member_name as member_name, issue_history.access_no, book_mas.title, issue_history.issue_date, issue_history.due_date, issue_history.return_date, issue_history.fine_amount from issue_history, member_mas, book_mas, sort_book where 1<2 and issue_history.access_no=sort_book.access_no "+strsql+" and issue_history.member_code=member_mas.member_code and issue_history.access_no=book_mas.access_no and fine_amount>0 and doc_type='THESIS' group by issue_history.member_code, member_mas.member_name, issue_history.access_no, book_mas.title, issue_history.issue_date, issue_history.due_date, issue_history.return_date, issue_history.fine_amount order by Issue_Date";	
		}
		else if(!order.contains("Issue_Date") && order.contains("Due_Date")){
		strsqlq="select sum(fine_amount) as sum_fine, issue_history.member_code as member_code, member_mas.member_name as member_name, issue_history.access_no, book_mas.title, issue_history.issue_date, issue_history.due_date, issue_history.return_date, issue_history.fine_amount from issue_history, member_mas, book_mas, sort_book where 1<2 and issue_history.access_no=sort_book.access_no "+strsql+" and issue_history.member_code=member_mas.member_code and issue_history.access_no=book_mas.access_no and fine_amount>0 and doc_type='THESIS' group by issue_history.member_code, member_mas.member_name, issue_history.access_no, book_mas.title, issue_history.issue_date, issue_history.due_date, issue_history.return_date, issue_history.fine_amount order by Due_Date";	
		}
		else if(order.contains("Issue_Date") && order.contains("Due_Date")){
		strsqlq="select sum(fine_amount) as sum_fine, issue_history.member_code as member_code, member_mas.member_name as member_name, issue_history.access_no, book_mas.title, issue_history.issue_date, issue_history.due_date, issue_history.return_date, issue_history.fine_amount from issue_history, member_mas, book_mas, sort_book where 1<2 and issue_history.access_no=sort_book.access_no "+strsql+" and issue_history.member_code=member_mas.member_code and issue_history.access_no=book_mas.access_no and fine_amount>0 and doc_type='THESIS' group by issue_history.member_code, member_mas.member_name, issue_history.access_no, book_mas.title, issue_history.issue_date, issue_history.due_date, issue_history.return_date, issue_history.fine_amount order by Issue_Date,Due_Date";	
		}
		}
	else{
		strsqlq="select sum(fine_amount) as sum_fine, issue_history.member_code as member_code, member_mas.member_name as member_name, issue_history.access_no, book_mas.title, issue_history.issue_date, issue_history.due_date, issue_history.return_date, issue_history.fine_amount from issue_history, member_mas, book_mas where 1<2 "+strsql+" and issue_history.member_code=member_mas.member_code and issue_history.access_no=book_mas.access_no and fine_amount>0 and doc_type='THESIS' group by issue_history.member_code, member_mas.member_name, issue_history.access_no, book_mas.title, issue_history.issue_date, issue_history.due_date, issue_history.return_date, issue_history.fine_amount "+order+"";
	}

	//out.print(strsqlq);
	//rs=st.executeQuery(strsqlq);
	pstmt=con.prepareStatement(strsqlq);
	rs=pstmt.executeQuery();
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
	out.print("<tr><th>Sno</th><th>Member code</th><th>Member Name</th><th>Access No</th><th>Title</th><th>Issue Date</th><th>Due Date</th><th>Return Date</th><th>Fine Amount</th></tr>");

	while(rs.next())
	{

		String member_code=rs.getString("member_code");
		String member_name=rs.getString("member_name");
		String access_no=rs.getString("access_no");
		String title=rs.getString("title");
		String issue_date=getdate(rs.getString("issue_date"));
		String due_date=getdate(rs.getString("due_date"));
		String return_date=getdate(rs.getString("return_date"));
		String fa=rs.getString("sum_fine");


		ser.add(member_code);
		ser.add(member_name);
		ser.add(access_no);
		ser.add(title);
		ser.add(issue_date);
		ser.add(due_date);
		ser.add(return_date);
		ser.add(fa);


	}
		ob.setAl(ser);
		sc=(ArrayList)ob.getAl();
		for(int i=0;i<sc.size();i++){
		 out.print("<tr><td><font size=2>"+sno+"</td>");
		 out.print("<td>"+sc.get(i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 String fa=(String)sc.get(++i);
		 out.print("<td>"+fa+"</td>");
                 out.print("</tr>");
		 sno=sno+1;
		  float tfa=Float.parseFloat(fa);
		 fine=fine+tfa;
		 subcount=subcount+tfa;
	}
	sc.clear();
	out.print("</table>");
	out.print("<br>");
	out.print("<br>");
	out.print("<font face=times new roman size=4><center>Grand Total:  "+fine+"</center></font>");
	out.print("<font face=times new roman size=4><center>Net Total:  "+subcount+"</center></font>");
	}
	//strsqlq="select sum(fine_amount) as sum_fine, issue_history.member_code as member_code, member_mas.member_name as member_name, issue_history.access_no, book_mas.title, issue_history.issue_date, issue_history.due_date, issue_history.return_date, issue_history.fine_amount from issue_history, member_mas, book_mas where 1<2 "+strsql+" and issue_history.member_code=member_mas.member_code and issue_history.access_no=book_mas.access_no and fine_amount>0 and doc_type='BACK VOLUME' group by issue_history.member_code, member_mas.member_name, issue_history.access_no, book_mas.title, issue_history.issue_date, issue_history.due_date, issue_history.return_date, issue_history.fine_amount "+order+"";
	if(!order.equals("")){
		//strsqlq = "select * from juserstat_all where 1<2 "+strsql+"  and issue_type='ISSUE' order by '"+order+"'";
		if(!order.contains("Issue_Date") && !order.contains("Due_Date")){
		strsqlq="select sum(fine_amount) as sum_fine, issue_history.member_code as member_code, member_mas.member_name as member_name, issue_history.access_no, book_mas.title, issue_history.issue_date, issue_history.due_date, issue_history.return_date, issue_history.fine_amount from issue_history, member_mas, book_mas, sort_book where 1<2 and issue_history.access_no=sort_book.access_no "+strsql+" and issue_history.member_code=member_mas.member_code and issue_history.access_no=book_mas.access_no and fine_amount>0 and doc_type='BACK VOLUME' group by issue_history.member_code, member_mas.member_name, issue_history.access_no, book_mas.title, issue_history.issue_date, issue_history.due_date, issue_history.return_date, issue_history.fine_amount order by n1,n2,n3";
		}
		else if(order.contains("Issue_Date") && !order.contains("Due_Date")){
		strsqlq="select sum(fine_amount) as sum_fine, issue_history.member_code as member_code, member_mas.member_name as member_name, issue_history.access_no, book_mas.title, issue_history.issue_date, issue_history.due_date, issue_history.return_date, issue_history.fine_amount from issue_history, member_mas, book_mas, sort_book where 1<2 and issue_history.access_no=sort_book.access_no "+strsql+" and issue_history.member_code=member_mas.member_code and issue_history.access_no=book_mas.access_no and fine_amount>0 and doc_type='BACK VOLUME' group by issue_history.member_code, member_mas.member_name, issue_history.access_no, book_mas.title, issue_history.issue_date, issue_history.due_date, issue_history.return_date, issue_history.fine_amount order by Issue_Date";	
		}
		else if(!order.contains("Issue_Date") && order.contains("Due_Date")){
		strsqlq="select sum(fine_amount) as sum_fine, issue_history.member_code as member_code, member_mas.member_name as member_name, issue_history.access_no, book_mas.title, issue_history.issue_date, issue_history.due_date, issue_history.return_date, issue_history.fine_amount from issue_history, member_mas, book_mas, sort_book where 1<2 and issue_history.access_no=sort_book.access_no "+strsql+" and issue_history.member_code=member_mas.member_code and issue_history.access_no=book_mas.access_no and fine_amount>0 and doc_type='BACK VOLUME' group by issue_history.member_code, member_mas.member_name, issue_history.access_no, book_mas.title, issue_history.issue_date, issue_history.due_date, issue_history.return_date, issue_history.fine_amount order by Due_Date";	
		}
		else if(order.contains("Issue_Date") && order.contains("Due_Date")){
		strsqlq="select sum(fine_amount) as sum_fine, issue_history.member_code as member_code, member_mas.member_name as member_name, issue_history.access_no, book_mas.title, issue_history.issue_date, issue_history.due_date, issue_history.return_date, issue_history.fine_amount from issue_history, member_mas, book_mas, sort_book where 1<2 and issue_history.access_no=sort_book.access_no "+strsql+" and issue_history.member_code=member_mas.member_code and issue_history.access_no=book_mas.access_no and fine_amount>0 and doc_type='BACK VOLUME' group by issue_history.member_code, member_mas.member_name, issue_history.access_no, book_mas.title, issue_history.issue_date, issue_history.due_date, issue_history.return_date, issue_history.fine_amount order by Issue_Date,Due_Date";	
		}
		}
	else{
		strsqlq="select sum(fine_amount) as sum_fine, issue_history.member_code as member_code, member_mas.member_name as member_name, issue_history.access_no, book_mas.title, issue_history.issue_date, issue_history.due_date, issue_history.return_date, issue_history.fine_amount from issue_history, member_mas, book_mas where 1<2 "+strsql+" and issue_history.member_code=member_mas.member_code and issue_history.access_no=book_mas.access_no and fine_amount>0 and doc_type='BACK VOLUME' group by issue_history.member_code, member_mas.member_name, issue_history.access_no, book_mas.title, issue_history.issue_date, issue_history.due_date, issue_history.return_date, issue_history.fine_amount "+order+"";
	}

	//out.print(strsqlq);
	//rs=st.executeQuery(strsqlq);
	pstmt=con.prepareStatement(strsqlq);
	rs=pstmt.executeQuery();
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
	out.print("<tr><th>Sno</th><th>Member code</th><th>Member Name</th><th>Access No</th><th>Title</th><th>Issue Date</th><th>Due Date</th><th>Return Date</th><th>Fine Amount</th></tr>");

	while(rs.next())
	{

		String member_code=rs.getString("member_code");
		String member_name=rs.getString("member_name");
		String access_no=rs.getString("access_no");
		String title=rs.getString("title");
		String issue_date=getdate(rs.getString("issue_date"));
		String due_date=getdate(rs.getString("due_date"));
		String return_date=getdate(rs.getString("return_date"));
		String fa=rs.getString("sum_fine");


		ser.add(member_code);
		ser.add(member_name);
		ser.add(access_no);
		ser.add(title);
		ser.add(issue_date);
		ser.add(due_date);
		ser.add(return_date);
		ser.add(fa);


	}
		ob.setAl(ser);
		sc=(ArrayList)ob.getAl();
		for(int i=0;i<sc.size();i++){
		 out.print("<tr><td><font size=2>"+sno+"</td>");
		 out.print("<td>"+sc.get(i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 String fa=(String)sc.get(++i);
		 out.print("<td>"+fa+"</td>");
                 out.print("</tr>");
		 sno=sno+1;
		  float tfa=Float.parseFloat(fa);
		 fine=fine+tfa;
		 subcount=subcount+tfa;
	}
	sc.clear();
	out.print("</table>");
	out.print("<br>");
	out.print("<br>");
	out.print("<font face=times new roman size=4><center>Grand Total:  "+fine+"</center></font>");
	out.print("<font face=times new roman size=4><center>Net Total:  "+subcount+"</center></font>");
	}
	//strsqlq="select sum(fine_amount) as sum_fine, issue_history.member_code as member_code, member_mas.member_name as member_name, issue_history.access_no, book_mas.title, issue_history.issue_date, issue_history.due_date, issue_history.return_date, issue_history.fine_amount from issue_history, member_mas, book_mas where 1<2 "+strsql+" and issue_history.member_code=member_mas.member_code and issue_history.access_no=book_mas.access_no and fine_amount>0 and doc_type='REPORT' group by issue_history.member_code, member_mas.member_name, issue_history.access_no, book_mas.title, issue_history.issue_date, issue_history.due_date, issue_history.return_date, issue_history.fine_amount "+order+"";
	if(!order.equals("")){
		//strsqlq = "select * from juserstat_all where 1<2 "+strsql+"  and issue_type='ISSUE' order by '"+order+"'";
		if(!order.contains("Issue_Date") && !order.contains("Due_Date")){
		strsqlq="select sum(fine_amount) as sum_fine, issue_history.member_code as member_code, member_mas.member_name as member_name, issue_history.access_no, book_mas.title, issue_history.issue_date, issue_history.due_date, issue_history.return_date, issue_history.fine_amount from issue_history, member_mas, book_mas, sort_book where 1<2 and issue_history.access_no=sort_book.access_no "+strsql+" and issue_history.member_code=member_mas.member_code and issue_history.access_no=book_mas.access_no and fine_amount>0 and doc_type='REPORT' group by issue_history.member_code, member_mas.member_name, issue_history.access_no, book_mas.title, issue_history.issue_date, issue_history.due_date, issue_history.return_date, issue_history.fine_amount order by n1,n2,n3";
		}
		else if(order.contains("Issue_Date") && !order.contains("Due_Date")){
		strsqlq="select sum(fine_amount) as sum_fine, issue_history.member_code as member_code, member_mas.member_name as member_name, issue_history.access_no, book_mas.title, issue_history.issue_date, issue_history.due_date, issue_history.return_date, issue_history.fine_amount from issue_history, member_mas, book_mas, sort_book where 1<2 and issue_history.access_no=sort_book.access_no "+strsql+" and issue_history.member_code=member_mas.member_code and issue_history.access_no=book_mas.access_no and fine_amount>0 and doc_type='REPORT' group by issue_history.member_code, member_mas.member_name, issue_history.access_no, book_mas.title, issue_history.issue_date, issue_history.due_date, issue_history.return_date, issue_history.fine_amount order by Issue_Date";	
		}
		else if(!order.contains("Issue_Date") && order.contains("Due_Date")){
		strsqlq="select sum(fine_amount) as sum_fine, issue_history.member_code as member_code, member_mas.member_name as member_name, issue_history.access_no, book_mas.title, issue_history.issue_date, issue_history.due_date, issue_history.return_date, issue_history.fine_amount from issue_history, member_mas, book_mas, sort_book where 1<2 and issue_history.access_no=sort_book.access_no "+strsql+" and issue_history.member_code=member_mas.member_code and issue_history.access_no=book_mas.access_no and fine_amount>0 and doc_type='REPORT' group by issue_history.member_code, member_mas.member_name, issue_history.access_no, book_mas.title, issue_history.issue_date, issue_history.due_date, issue_history.return_date, issue_history.fine_amount order by Due_Date";	
		}
		else if(order.contains("Issue_Date") && order.contains("Due_Date")){
		strsqlq="select sum(fine_amount) as sum_fine, issue_history.member_code as member_code, member_mas.member_name as member_name, issue_history.access_no, book_mas.title, issue_history.issue_date, issue_history.due_date, issue_history.return_date, issue_history.fine_amount from issue_history, member_mas, book_mas, sort_book where 1<2 and issue_history.access_no=sort_book.access_no "+strsql+" and issue_history.member_code=member_mas.member_code and issue_history.access_no=book_mas.access_no and fine_amount>0 and doc_type='REPORT' group by issue_history.member_code, member_mas.member_name, issue_history.access_no, book_mas.title, issue_history.issue_date, issue_history.due_date, issue_history.return_date, issue_history.fine_amount order by Issue_Date,Due_Date";	
		}
		}
	else{
		strsqlq="select sum(fine_amount) as sum_fine, issue_history.member_code as member_code, member_mas.member_name as member_name, issue_history.access_no, book_mas.title, issue_history.issue_date, issue_history.due_date, issue_history.return_date, issue_history.fine_amount from issue_history, member_mas, book_mas where 1<2 "+strsql+" and issue_history.member_code=member_mas.member_code and issue_history.access_no=book_mas.access_no and fine_amount>0 and doc_type='REPORT' group by issue_history.member_code, member_mas.member_name, issue_history.access_no, book_mas.title, issue_history.issue_date, issue_history.due_date, issue_history.return_date, issue_history.fine_amount "+order+"";
	}

	//out.print(strsqlq);
	//rs=st.executeQuery(strsqlq);
	pstmt=con.prepareStatement(strsqlq);
	rs=pstmt.executeQuery();
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
	out.print("<tr><th>Sno</th><th>Member code</th><th>Member Name</th><th>Access No</th><th>Title</th><th>Issue Date</th><th>Due Date</th><th>Return Date</th><th>Fine Amount</th></tr>");

	while(rs.next())
	{

		String member_code=rs.getString("member_code");
		String member_name=rs.getString("member_name");
		String access_no=rs.getString("access_no");
		String title=rs.getString("title");
		String issue_date=getdate(rs.getString("issue_date"));
		String due_date=getdate(rs.getString("due_date"));
		String return_date=getdate(rs.getString("return_date"));
		String fa=rs.getString("sum_fine");


		ser.add(member_code);
		ser.add(member_name);
		ser.add(access_no);
		ser.add(title);
		ser.add(issue_date);
		ser.add(due_date);
		ser.add(return_date);
		ser.add(fa);


	}
		ob.setAl(ser);
		sc=(ArrayList)ob.getAl();
		for(int i=0;i<sc.size();i++){
		 out.print("<tr><td><font size=2>"+sno+"</td>");
		 out.print("<td>"+sc.get(i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 String fa=(String)sc.get(++i);
		 out.print("<td>"+fa+"</td>");
                 out.print("</tr>");
		 sno=sno+1;
		  float tfa=Float.parseFloat(fa);
		 fine=fine+tfa;
		 subcount=subcount+tfa;
	}
	sc.clear();
	out.print("</table>");
	out.print("<br>");
	out.print("<br>");
	out.print("<font face=times new roman size=4><center>Grand Total:  "+fine+"</center></font>");
	out.print("<font face=times new roman size=4><center>Net Total:  "+subcount+"</center></font>");
	}
	//strsqlq="select sum(fine_amount) as sum_fine, issue_history.member_code as member_code, member_mas.member_name as member_name, issue_history.access_no, book_mas.title, issue_history.issue_date, issue_history.due_date, issue_history.return_date, issue_history.fine_amount from issue_history, member_mas, book_mas where 1<2 "+strsql+" and issue_history.member_code=member_mas.member_code and issue_history.access_no=book_mas.access_no and fine_amount>0 and doc_type='NON BOOK' group by issue_history.member_code, member_mas.member_name, issue_history.access_no, book_mas.title, issue_history.issue_date, issue_history.due_date, issue_history.return_date, issue_history.fine_amount "+order+"";
	if(!order.equals("")){
		//strsqlq = "select * from juserstat_all where 1<2 "+strsql+"  and issue_type='ISSUE' order by '"+order+"'";
		if(!order.contains("Issue_Date") && !order.contains("Due_Date")){
		strsqlq="select sum(fine_amount) as sum_fine, issue_history.member_code as member_code, member_mas.member_name as member_name, issue_history.access_no, book_mas.title, issue_history.issue_date, issue_history.due_date, issue_history.return_date, issue_history.fine_amount from issue_history, member_mas, book_mas, sort_book where 1<2 and issue_history.access_no=sort_book.access_no "+strsql+" and issue_history.member_code=member_mas.member_code and issue_history.access_no=book_mas.access_no and fine_amount>0 and doc_type='NON BOOK' group by issue_history.member_code, member_mas.member_name, issue_history.access_no, book_mas.title, issue_history.issue_date, issue_history.due_date, issue_history.return_date, issue_history.fine_amount order by n1,n2,n3";
		}
		else if(order.contains("Issue_Date") && !order.contains("Due_Date")){
		strsqlq="select sum(fine_amount) as sum_fine, issue_history.member_code as member_code, member_mas.member_name as member_name, issue_history.access_no, book_mas.title, issue_history.issue_date, issue_history.due_date, issue_history.return_date, issue_history.fine_amount from issue_history, member_mas, book_mas, sort_book where 1<2 and issue_history.access_no=sort_book.access_no "+strsql+" and issue_history.member_code=member_mas.member_code and issue_history.access_no=book_mas.access_no and fine_amount>0 and doc_type='NON BOOK' group by issue_history.member_code, member_mas.member_name, issue_history.access_no, book_mas.title, issue_history.issue_date, issue_history.due_date, issue_history.return_date, issue_history.fine_amount order by Issue_Date";	
		}
		else if(!order.contains("Issue_Date") && order.contains("Due_Date")){
		strsqlq="select sum(fine_amount) as sum_fine, issue_history.member_code as member_code, member_mas.member_name as member_name, issue_history.access_no, book_mas.title, issue_history.issue_date, issue_history.due_date, issue_history.return_date, issue_history.fine_amount from issue_history, member_mas, book_mas, sort_book where 1<2 and issue_history.access_no=sort_book.access_no "+strsql+" and issue_history.member_code=member_mas.member_code and issue_history.access_no=book_mas.access_no and fine_amount>0 and doc_type='NON BOOK' group by issue_history.member_code, member_mas.member_name, issue_history.access_no, book_mas.title, issue_history.issue_date, issue_history.due_date, issue_history.return_date, issue_history.fine_amount order by Due_Date";	
		}
		else if(order.contains("Issue_Date") && order.contains("Due_Date")){
		strsqlq="select sum(fine_amount) as sum_fine, issue_history.member_code as member_code, member_mas.member_name as member_name, issue_history.access_no, book_mas.title, issue_history.issue_date, issue_history.due_date, issue_history.return_date, issue_history.fine_amount from issue_history, member_mas, book_mas, sort_book where 1<2 and issue_history.access_no=sort_book.access_no "+strsql+" and issue_history.member_code=member_mas.member_code and issue_history.access_no=book_mas.access_no and fine_amount>0 and doc_type='NON BOOK' group by issue_history.member_code, member_mas.member_name, issue_history.access_no, book_mas.title, issue_history.issue_date, issue_history.due_date, issue_history.return_date, issue_history.fine_amount order by Issue_Date,Due_Date";	
		}
		}
	else{
		strsqlq="select sum(fine_amount) as sum_fine, issue_history.member_code as member_code, member_mas.member_name as member_name, issue_history.access_no, book_mas.title, issue_history.issue_date, issue_history.due_date, issue_history.return_date, issue_history.fine_amount from issue_history, member_mas, book_mas where 1<2 "+strsql+" and issue_history.member_code=member_mas.member_code and issue_history.access_no=book_mas.access_no and fine_amount>0 and doc_type='NON BOOK' group by issue_history.member_code, member_mas.member_name, issue_history.access_no, book_mas.title, issue_history.issue_date, issue_history.due_date, issue_history.return_date, issue_history.fine_amount "+order+"";
	}

	//out.print(strsqlq);
	//rs=st.executeQuery(strsqlq);
	pstmt=con.prepareStatement(strsqlq);
	rs=pstmt.executeQuery();
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
	out.print("<tr><th>Sno</th><th>Member code</th><th>Member Name</th><th>Access No</th><th>Title</th><th>Issue Date</th><th>Due Date</th><th>Return Date</th><th>Fine Amount</th></tr>");

	while(rs.next())
	{

		String member_code=rs.getString("member_code");
		String member_name=rs.getString("member_name");
		String access_no=rs.getString("access_no");
		String title=rs.getString("title");
		String issue_date=getdate(rs.getString("issue_date"));
		String due_date=getdate(rs.getString("due_date"));
		String return_date=getdate(rs.getString("return_date"));
		String fa=rs.getString("sum_fine");


		ser.add(member_code);
		ser.add(member_name);
		ser.add(access_no);
		ser.add(title);
		ser.add(issue_date);
		ser.add(due_date);
		ser.add(return_date);
		ser.add(fa);


	}
		ob.setAl(ser);
		sc=(ArrayList)ob.getAl();
		for(int i=0;i<sc.size();i++){
		 out.print("<tr><td><font size=2>"+sno+"</td>");
		 out.print("<td>"+sc.get(i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 String fa=(String)sc.get(++i);
		 out.print("<td>"+fa+"</td>");
                 out.print("</tr>");
		 sno=sno+1;
		  float tfa=Float.parseFloat(fa);
		 fine=fine+tfa;
		 subcount=subcount+tfa;
	}
	sc.clear();
	out.print("</table>");
	out.print("<br>");
	out.print("<br>");
	out.print("<font face=times new roman size=4><center>Grand Total:  "+fine+"</center></font>");
	out.print("<font face=times new roman size=4><center>Net Total:  "+subcount+"</center></font>");
	}
	//strsqlq="select sum(fine_amount) as sum_fine, issue_history.member_code as member_code, member_mas.member_name as member_name, issue_history.access_no, book_mas.title, issue_history.issue_date, issue_history.due_date, issue_history.return_date, issue_history.fine_amount from issue_history, member_mas, book_mas where 1<2 "+strsql+" and issue_history.member_code=member_mas.member_code and issue_history.access_no=book_mas.access_no and fine_amount>0 and doc_type='STANDARD' group by issue_history.member_code, member_mas.member_name, issue_history.access_no, book_mas.title, issue_history.issue_date, issue_history.due_date, issue_history.return_date, issue_history.fine_amount "+order+"";
	if(!order.equals("")){
		//strsqlq = "select * from juserstat_all where 1<2 "+strsql+"  and issue_type='ISSUE' order by '"+order+"'";
		if(!order.contains("Issue_Date") && !order.contains("Due_Date")){
		strsqlq="select sum(fine_amount) as sum_fine, issue_history.member_code as member_code, member_mas.member_name as member_name, issue_history.access_no, book_mas.title, issue_history.issue_date, issue_history.due_date, issue_history.return_date, issue_history.fine_amount from issue_history, member_mas, book_mas, sort_book where 1<2 and issue_history.access_no=sort_book.access_no "+strsql+" and issue_history.member_code=member_mas.member_code and issue_history.access_no=book_mas.access_no and fine_amount>0 and doc_type='STANDARD' group by issue_history.member_code, member_mas.member_name, issue_history.access_no, book_mas.title, issue_history.issue_date, issue_history.due_date, issue_history.return_date, issue_history.fine_amount order by n1,n2,n3";
		}
		else if(order.contains("Issue_Date") && !order.contains("Due_Date")){
		strsqlq="select sum(fine_amount) as sum_fine, issue_history.member_code as member_code, member_mas.member_name as member_name, issue_history.access_no, book_mas.title, issue_history.issue_date, issue_history.due_date, issue_history.return_date, issue_history.fine_amount from issue_history, member_mas, book_mas, sort_book where 1<2 and issue_history.access_no=sort_book.access_no "+strsql+" and issue_history.member_code=member_mas.member_code and issue_history.access_no=book_mas.access_no and fine_amount>0 and doc_type='STANDARD' group by issue_history.member_code, member_mas.member_name, issue_history.access_no, book_mas.title, issue_history.issue_date, issue_history.due_date, issue_history.return_date, issue_history.fine_amount order by Issue_Date";	
		}
		else if(!order.contains("Issue_Date") && order.contains("Due_Date")){
		strsqlq="select sum(fine_amount) as sum_fine, issue_history.member_code as member_code, member_mas.member_name as member_name, issue_history.access_no, book_mas.title, issue_history.issue_date, issue_history.due_date, issue_history.return_date, issue_history.fine_amount from issue_history, member_mas, book_mas, sort_book where 1<2 and issue_history.access_no=sort_book.access_no "+strsql+" and issue_history.member_code=member_mas.member_code and issue_history.access_no=book_mas.access_no and fine_amount>0 and doc_type='STANDARD' group by issue_history.member_code, member_mas.member_name, issue_history.access_no, book_mas.title, issue_history.issue_date, issue_history.due_date, issue_history.return_date, issue_history.fine_amount order by Due_Date";	
		}
		else if(order.contains("Issue_Date") && order.contains("Due_Date")){
		strsqlq="select sum(fine_amount) as sum_fine, issue_history.member_code as member_code, member_mas.member_name as member_name, issue_history.access_no, book_mas.title, issue_history.issue_date, issue_history.due_date, issue_history.return_date, issue_history.fine_amount from issue_history, member_mas, book_mas, sort_book where 1<2 and issue_history.access_no=sort_book.access_no "+strsql+" and issue_history.member_code=member_mas.member_code and issue_history.access_no=book_mas.access_no and fine_amount>0 and doc_type='STANDARD' group by issue_history.member_code, member_mas.member_name, issue_history.access_no, book_mas.title, issue_history.issue_date, issue_history.due_date, issue_history.return_date, issue_history.fine_amount order by Issue_Date,Due_Date";	
		}
		}
	else{
		strsqlq="select sum(fine_amount) as sum_fine, issue_history.member_code as member_code, member_mas.member_name as member_name, issue_history.access_no, book_mas.title, issue_history.issue_date, issue_history.due_date, issue_history.return_date, issue_history.fine_amount from issue_history, member_mas, book_mas where 1<2 "+strsql+" and issue_history.member_code=member_mas.member_code and issue_history.access_no=book_mas.access_no and fine_amount>0 and doc_type='STANDARD' group by issue_history.member_code, member_mas.member_name, issue_history.access_no, book_mas.title, issue_history.issue_date, issue_history.due_date, issue_history.return_date, issue_history.fine_amount "+order+"";
	}

	//out.print(strsqlq);
	//rs=st.executeQuery(strsqlq);
	pstmt=con.prepareStatement(strsqlq);
	rs=pstmt.executeQuery();
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
	out.print("<tr><th>Sno</th><th>Member code</th><th>Member Name</th><th>Access No</th><th>Title</th><th>Issue Date</th><th>Due Date</th><th>Return Date</th><th>Fine Amount</th></tr>");

	while(rs.next())
	{

		String member_code=rs.getString("member_code");
		String member_name=rs.getString("member_name");
		String access_no=rs.getString("access_no");
		String title=rs.getString("title");
		String issue_date=getdate(rs.getString("issue_date"));
		String due_date=getdate(rs.getString("due_date"));
		String return_date=getdate(rs.getString("return_date"));
		String fa=rs.getString("sum_fine");


		ser.add(member_code);
		ser.add(member_name);
		ser.add(access_no);
		ser.add(title);
		ser.add(issue_date);
		ser.add(due_date);
		ser.add(return_date);
		ser.add(fa);


	}
		ob.setAl(ser);
		sc=(ArrayList)ob.getAl();
		for(int i=0;i<sc.size();i++){
		 out.print("<tr><td><font size=2>"+sno+"</td>");
		 out.print("<td>"+sc.get(i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 String fa=(String)sc.get(++i);
		 out.print("<td>"+fa+"</td>");
                 out.print("</tr>");
		 sno=sno+1;
		  float tfa=Float.parseFloat(fa);
		 fine=fine+tfa;
		 subcount=subcount+tfa;
	}
	sc.clear();
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
	 strsqlq="select course_mas.course_name, issue_history.member_code as member_code, department_mas.dept_name as dept_name, member_mas.member_name, member_mas.Course_Code, sum(fine_amount) as amount from issue_history, department_mas, member_mas, course_mas,book_mas where 1<2 "+strsql+" and issue_history.member_code=member_mas.member_code and issue_history.access_no=book_mas.access_no and course_mas.course_code=member_mas.course_code and department_mas.dept_code=member_mas.dept_code and fine_amount>0 group by issue_history.member_code, dept_name, member_mas.Course_Code, member_mas.member_name, course_mas.course_name";
	 /* strsqlq="select issue_history.member_code as member_code, department_mas.dept_name as dept_name, member_mas.member_name, Member_mas.Course_Code, sum(fine_amount) as amount from issue_history, department_mas, member_mas where 1<2 "+strsql+" and issue_history.member_code=member_mas.member_code and issue_history.access_no=book_mas.access_no and department_mas.dept_code=member_mas.dept_code and fine_amount>0 group by issue_history.member_code, dept_name, Member_mas.Course_Code, member_mas.member_name, issue_history.due_date, member_mas.member_code order by issue_history.due_date, member_mas.member_code"; */
	/* strsqlq="select sum(fine_amount) as sum_fine, issue_history.return_date from issue_history, member_mas, book_mas where 1<2 "+strsql+" and issue_history.member_code=member_mas.member_code and issue_history.access_no=book_mas.access_no and fine_amount>0  group by member_mas.dept_code, issue_history.return_date order by  member_mas.dept_code, issue_history.return_date"; */
	// out.print(strsqlq);
	//rs=st.executeQuery(strsqlq);
	pstmt=con.prepareStatement(strsqlq);
	rs=pstmt.executeQuery();
	if(!rs.next())
	{
	out.print("<font face=times new roman size=5 color=blue><center>No Record Found</center></font><br>");
	//out.print("<center><input type=button name=click value=back onclick=backward();></center>");

	}
	else
	{
	rs.beforeFirst();
	sno=1;
	fine=0;
	out.print("<center><font size=5>FINE REPORT</center></font>");
	out.print("<font size=3>Document Type :"+txtdoctype+"</font>");
	out.print("<br><table border=1 width=700 align=center CELLSPACING='0' BORDERCOLOR='#DADADA' BGCOLOR='#F5F5F5'> ");
	out.print("<tr><th>Sno</th><th>Member Code</th><th>Member Name</th><th>Dept Name</th><th>Course Name</th><th>Fine Amount</th></tr>");

	while(rs.next())
	{
		String member_code=rs.getString("member_code");
		String member_name=rs.getString("member_name");
		String Dept_Name=rs.getString("Dept_Name");
		String Course_Name=rs.getString("Course_Name");
		float faa=rs.getFloat("amount");

		ser.add(member_code);
		ser.add(member_name);
		ser.add(Dept_Name);
		ser.add(Course_Name);
		ser.add(String.valueOf(faa));

	}
	ob.setAl(ser);
	sc=(ArrayList)ob.getAl();

		 for(int i=0; i<sc.size();i++){
		 out.print("<tr><td><font size=2>"+sno+"</td>");
		 out.print("<td>"+sc.get(i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 String fine_amo=(String)sc.get(++i);
		 out.print("<td>"+fine_amo+"</td>");
		 out.print("</tr>");
		 faa=Float.parseFloat(fine_amo);
		 sno=sno+1;
		 fine=fine+faa;

	}
		sc.clear();

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
	strsqlq="select sum(fine_amount) as sum_fine, issue_history.member_code as member_code, member_mas.member_name as member_name, issue_history.access_no, book_mas.title, issue_history.issue_date, issue_history.due_date, issue_history.return_date, issue_history.fine_amount from issue_history, member_mas, book_mas where 1<2 "+strsql+" and issue_history.member_code=member_mas.member_code and issue_history.access_no=book_mas.access_no and fine_amount>0  group by issue_history.member_code, member_mas.member_name, issue_history.access_no, book_mas.title, issue_history.issue_date, issue_history.due_date, issue_history.return_date, issue_history.fine_amount";
	// out.print(strsqlq);
	//rs=st.executeQuery(strsqlq);
	pstmt=con.prepareStatement(strsqlq);
	rs=pstmt.executeQuery();
	if(!rs.next())
	{
	out.print("<font face=times new roman size=5 color=blue><center>No Record Found</center></font><br>");
	out.print("<center><input type=button name=click value=back onclick=backward();></center>");

	}
	else
	{
	rs.beforeFirst();
	sno=1;
	fine=0;
	out.print("<center><font size=5>FINE REPORT</center></font>");
	out.print("<font size=3>Document Type :"+txtdoctype+"</font>");
	out.print("<br><table border=0 width=700 align=center CELLSPACING='0' BORDERCOLOR='#DADADA' BGCOLOR='#F5F5F5'> ");


	while(rs.next())
	{
		float faa=rs.getFloat("sum_fine");
		ser.add(String.valueOf(faa));

	}
		ob.setAl(ser);
		sc=(ArrayList)ob.getAl();
		for(int i=0; i<sc.size();i++){
		 String fine_amo=(String)sc.get(i);
		 faa=Float.parseFloat(fine_amo);
		 sno=sno+1;
		 fine=fine+faa;


	}
	sc.clear();
	out.print("</table>");
        out.print("<br>");
	out.print("<br>");
	out.print("<br>");
	out.print("<font face=times new roman size=4><center>Total:  "+fine+"</center></font>");

	}

	}
	}


//FINE FOR BOOK

	if ((txtreporttype.equals("Fine")) && (!txtdoctype.equals("ALL"))){
	if(report_type.equals("listing"))
	{
	 //strsqlq="select sum(fine_amount) as sum_fine, issue_history.member_code as member_code, member_mas.member_name as member_name, issue_history.access_no, book_mas.title, issue_history.issue_date, issue_history.due_date, issue_history.return_date, issue_history.fine_amount from issue_history, member_mas, book_mas where 1<2 "+strsql+" and issue_history.member_code=member_mas.member_code and issue_history.access_no=book_mas.access_no and fine_amount>0 and doc_type='"+txtdoctype+"' group by issue_history.member_code, member_mas.member_name, issue_history.access_no, book_mas.title, issue_history.issue_date, issue_history.due_date, issue_history.return_date, issue_history.fine_amount "+order+"";
	 if(!order.equals("")){
			//strsqlq = "select * from juserstat_all where 1<2 "+strsql+"  and issue_type='ISSUE' order by '"+order+"'";
			if(!order.contains("Issue_Date") && !order.contains("Due_Date")){
			strsqlq="select sum(fine_amount) as sum_fine, issue_history.member_code as member_code, member_mas.member_name as member_name, issue_history.access_no, book_mas.title, issue_history.issue_date, issue_history.due_date, issue_history.return_date, issue_history.fine_amount from issue_history, member_mas, book_mas, sort_book where 1<2 and issue_history.access_no=sort_book.access_no "+strsql+" and issue_history.member_code=member_mas.member_code and issue_history.access_no=book_mas.access_no and fine_amount>0 and doc_type='"+txtdoctype+"' group by issue_history.member_code, member_mas.member_name, issue_history.access_no, book_mas.title, issue_history.issue_date, issue_history.due_date, issue_history.return_date, issue_history.fine_amount order by n1,n2,n3";
			}
			else if(order.contains("Issue_Date") && !order.contains("Due_Date")){
			strsqlq="select sum(fine_amount) as sum_fine, issue_history.member_code as member_code, member_mas.member_name as member_name, issue_history.access_no, book_mas.title, issue_history.issue_date, issue_history.due_date, issue_history.return_date, issue_history.fine_amount from issue_history, member_mas, book_mas, sort_book where 1<2 and issue_history.access_no=sort_book.access_no "+strsql+" and issue_history.member_code=member_mas.member_code and issue_history.access_no=book_mas.access_no and fine_amount>0 and doc_type='"+txtdoctype+"' group by issue_history.member_code, member_mas.member_name, issue_history.access_no, book_mas.title, issue_history.issue_date, issue_history.due_date, issue_history.return_date, issue_history.fine_amount order by Issue_Date";	
			}
			else if(!order.contains("Issue_Date") && order.contains("Due_Date")){
			strsqlq="select sum(fine_amount) as sum_fine, issue_history.member_code as member_code, member_mas.member_name as member_name, issue_history.access_no, book_mas.title, issue_history.issue_date, issue_history.due_date, issue_history.return_date, issue_history.fine_amount from issue_history, member_mas, book_mas, sort_book where 1<2 and issue_history.access_no=sort_book.access_no "+strsql+" and issue_history.member_code=member_mas.member_code and issue_history.access_no=book_mas.access_no and fine_amount>0 and doc_type='"+txtdoctype+"' group by issue_history.member_code, member_mas.member_name, issue_history.access_no, book_mas.title, issue_history.issue_date, issue_history.due_date, issue_history.return_date, issue_history.fine_amount order by Due_Date";	
			}
			else if(order.contains("Issue_Date") && order.contains("Due_Date")){
			strsqlq="select sum(fine_amount) as sum_fine, issue_history.member_code as member_code, member_mas.member_name as member_name, issue_history.access_no, book_mas.title, issue_history.issue_date, issue_history.due_date, issue_history.return_date, issue_history.fine_amount from issue_history, member_mas, book_mas, sort_book where 1<2 and issue_history.access_no=sort_book.access_no "+strsql+" and issue_history.member_code=member_mas.member_code and issue_history.access_no=book_mas.access_no and fine_amount>0 and doc_type='"+txtdoctype+"' group by issue_history.member_code, member_mas.member_name, issue_history.access_no, book_mas.title, issue_history.issue_date, issue_history.due_date, issue_history.return_date, issue_history.fine_amount order by Issue_Date,Due_Date";	
			}
			}
		else{
			strsqlq="select sum(fine_amount) as sum_fine, issue_history.member_code as member_code, member_mas.member_name as member_name, issue_history.access_no, book_mas.title, issue_history.issue_date, issue_history.due_date, issue_history.return_date, issue_history.fine_amount from issue_history, member_mas, book_mas where 1<2 "+strsql+" and issue_history.member_code=member_mas.member_code and issue_history.access_no=book_mas.access_no and fine_amount>0 and doc_type='"+txtdoctype+"' group by issue_history.member_code, member_mas.member_name, issue_history.access_no, book_mas.title, issue_history.issue_date, issue_history.due_date, issue_history.return_date, issue_history.fine_amount "+order+"";
		}
	 

	// out.print(strsqlq);
	//rs=st.executeQuery(strsqlq);
	pstmt=con.prepareStatement(strsqlq);
	rs=pstmt.executeQuery();
	if(!rs.next())
	{
	out.print("<font face=times new roman size=5 color=blue><center>No Record Found</center></font><br>");
	//out.print("<tr><td><center><input type=button name=click value=back onclick=backward();></center></td></tr>");

	}
	else
	{
	rs.beforeFirst();
	sno=1;
	fine=0;
	subcount=0;
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
	out.print("<tr><th>Sno</th><th>Member code</th><th>Member Name</th><th>Access No</th><th>Title</th><th>Issue Date</th><th>Due Date</th><th>Return Date</th><th>Fine Amount</th></tr>");

	while(rs.next())
	{

		String member_code=rs.getString("member_code");
		String member_name=rs.getString("member_name");
		String access_no=rs.getString("access_no");
		String title=rs.getString("title");
		String issue_date=getdate(rs.getString("issue_date"));
		String due_date=getdate(rs.getString("due_date"));
		String return_date=getdate(rs.getString("return_date"));
		String fa=rs.getString("sum_fine");


		ser.add(member_code);
		ser.add(member_name);
		ser.add(access_no);
		ser.add(title);
		ser.add(issue_date);
		ser.add(due_date);
		ser.add(return_date);
		ser.add(fa);


	}
		ob.setAl(ser);
		sc=(ArrayList)ob.getAl();
		for(int i=0;i<sc.size();i++){
		 out.print("<tr><td><font size=2>"+sno+"</td>");
		 out.print("<td>"+sc.get(i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 String fa=(String)sc.get(++i);
		 out.print("<td>"+fa+"</td>");
                 out.print("</tr>");
		 sno=sno+1;
		  float tfa=Float.parseFloat(fa);
		 fine=fine+tfa;
	}
	sc.clear();
	out.print("</table>");
	out.print("<br>");
	out.print("<br>");
	out.print("<font face=times new roman size=4><center>Grand Total:  "+fine+"</center></font>");
	}
	}

	if(report_type.equals("breakup"))
	{

	strsqlq="select course_mas.course_name, issue_history.member_code as member_code, department_mas.dept_name as dept_name, member_mas.member_name, member_mas.Course_Code, sum(fine_amount) as amount from issue_history, department_mas, member_mas, course_mas, book_mas where 1<2 "+strsql+" and doc_type='"+txtdoctype+"' and issue_history.member_code=member_mas.member_code and issue_history.access_no=book_mas.access_no and course_mas.course_code=member_mas.course_code and department_mas.dept_code=member_mas.dept_code and fine_amount>0 group by issue_history.member_code, dept_name, member_mas.Course_Code, member_mas.member_name, course_mas.course_name";

	// out.print(strsqlq);
	//rs=st.executeQuery(strsqlq);
	pstmt=con.prepareStatement(strsqlq);
	rs=pstmt.executeQuery();
	if(!rs.next())
	{
	out.print("<font face=times new roman size=5 color=blue><center>No Record Found</center></font><br>");
	//out.print("<center><input type=button name=click value=back onclick=backward();></center>");

	}
	else
	{
	rs.beforeFirst();
	sno=1;
	fine=0;
	subcount=0;
	out.print("<center><font size=5>FINE REPORT</center></font>");
	out.print("<font size=3>Document Type :"+txtdoctype+"</font>");
	out.print("<br><table border=1 width=700 align=center CELLSPACING='0' BORDERCOLOR='#DADADA' BGCOLOR='#F5F5F5'> ");
	out.print("<tr><th>Sno</th><th>Member Code</th><th>Member Name</th><th>Dept Name</th><th>Course Name</th><th>Fine Amount</th></tr>");

	while(rs.next())
	{
		String member_code=rs.getString("member_code");
		String member_name=rs.getString("member_name");
		String Dept_Name=rs.getString("Dept_Name");
		String Course_Name=rs.getString("Course_Name");
		float faa=rs.getFloat("amount");

		ser.add(member_code);
		ser.add(member_name);
		ser.add(Dept_Name);
		ser.add(Course_Name);
		ser.add(String.valueOf(faa));

	}
	ob.setAl(ser);
	sc=(ArrayList)ob.getAl();

		 for(int i=0; i<sc.size();i++){
		 out.print("<tr><td><font size=2>"+sno+"</td>");
		 out.print("<td>"+sc.get(i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 String fine_amo=(String)sc.get(++i);
		 out.print("<td>"+fine_amo+"</td>");
		 out.print("</tr>");
		 faa=Float.parseFloat(fine_amo);
		 sno=sno+1;
		 fine=fine+faa;
		 //subcount=subcount+faa;
	}
		sc.clear();
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

	strsqlq="select sum(fine_amount) as sum_fine, issue_history.member_code as member_code, member_mas.member_name as member_name, issue_history.access_no, book_mas.title, issue_history.issue_date, issue_history.due_date, issue_history.return_date, issue_history.fine_amount from issue_history, member_mas, book_mas where 1<2 "+strsql+" and issue_history.member_code=member_mas.member_code and issue_history.access_no=book_mas.access_no and fine_amount>0 and doc_type='"+txtdoctype+"' group by issue_history.member_code, member_mas.member_name, issue_history.access_no, book_mas.title, issue_history.issue_date, issue_history.due_date, issue_history.return_date, issue_history.fine_amount";
	// out.print(strsqlq);
	//rs=st.executeQuery(strsqlq);
	pstmt=con.prepareStatement(strsqlq);
	rs=pstmt.executeQuery();
	if(!rs.next())
	{
	out.print("<font face=times new roman size=5 color=blue><center>No Record Found</center></font><br>");
	//out.print("<center><input type=button name=click value=back onclick=backward();></center>");

	}
	else
	{
	rs.beforeFirst();
	sno=1;
	fine=0;
	out.print("<center><font size=5>FINE REPORT</center></font>");
	out.print("<font size=3>Document Type :"+txtdoctype+"</font>");
	out.print("<br><table border=0 width=700 align=center CELLSPACING='0' BORDERCOLOR='#DADADA' BGCOLOR='#F5F5F5'> ");


	while(rs.next())
	{
		float faa=rs.getFloat("sum_fine");
		ser.add(String.valueOf(faa));

	}
		ob.setAl(ser);
		sc=(ArrayList)ob.getAl();
		for(int i=0; i<sc.size();i++){
		 String fine_amo=(String)sc.get(i);
		 faa=Float.parseFloat(fine_amo);
		 sno=sno+1;
		 fine=fine+faa;
		 //subcount=subcount+faa;

	}
	sc.clear();
	out.print("</table>");
        out.print("<br>");
	out.print("<br>");
	out.print("<br>");
	out.print("<font face=times new roman size=4><center>Total:  "+fine+"</center></font>");


	}

	}
	}


		   }        catch (Exception e) {

					}
		    catch (Throwable theException) {

		   }
		    finally
		    {
		    	try{
					if(rs!=null){
						rs.close();
						rs=null;
					}
					if(pstmt!=null){
						pstmt.close();
						pstmt=null;
					}

				}catch(Exception e){
				e.printStackTrace();
				}
		    }

	}


String getdate(String datechk) {
    	//datechk=datechk.substring(0,datechk.length()-13);
    	   java.util.StringTokenizer stz_is=new java.util.StringTokenizer(datechk,"-");
		    int biy=Integer.parseInt(stz_is.nextToken());
		     int bim=Integer.parseInt(stz_is.nextToken());
		    int bid=Integer.parseInt(stz_is.nextToken());
	     	   String bissue_m=new Integer(bim).toString();
				    if(bissue_m.length()==1)
				    bissue_m="0"+bissue_m;
					String bissue_d=new Integer(bid).toString();
					if(bissue_d.length()==1)
				    bissue_d="0"+bissue_d;
					String BISSDATE=bissue_d+"-"+bissue_m+"-"+biy;
	return BISSDATE;
  }

  String getdate1(String datechk) {
    	//datechk=datechk.substring(0,datechk.length()-13);
    	   java.util.StringTokenizer stz_ie=new java.util.StringTokenizer(datechk,"-");
		    int bd=Integer.parseInt(stz_ie.nextToken());
		     int bm=Integer.parseInt(stz_ie.nextToken());
		    int by=Integer.parseInt(stz_ie.nextToken());
	     	   String bissu_m=new Integer(bm).toString();
				    if(bissu_m.length()==1)
				    bissu_m="0"+bissu_m;
					String bissu_d=new Integer(bd).toString();
					if(bissu_d.length()==1)
				    bissu_d="0"+bissu_d;
					String BISSDATE=by+"-"+bissu_m+"-"+bissu_d;
	return BISSDATE;
  }


	/**
	 * Instance variable for SQL statement property
	 */

}
