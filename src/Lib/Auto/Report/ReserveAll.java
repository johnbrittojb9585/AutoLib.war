package Lib.Auto.Report;
import Lib.Auto.Report.reportbean;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.sql.DriverManager;

import javax.servlet.ServletException;
import javax.servlet.UnavailableException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.*;



import Common.DBConnection;
import Common.Security;

public class ReserveAll extends HttpServlet{

	String txtreporttype="",txtaccessno="",txtmembercode="",txtdepartment="",txtfdate="",txttdate="",txtgroup="";

	String order1="",order2="",order3="",str="",strsql="",order,strsqlq="";
	String deptname="",groupname="",staff="",Reptfrom="",txtcourse="",txtdoctype="",report_type="",coursename="", count_book="", fine_amount="";
	int sno=0, total=0;
	float fine=0,subcount=0;
	String protocol="";
	ArrayList ser=new ArrayList();
	ArrayList sc=new ArrayList();
	reportbean ob=new reportbean();
	java.sql.Connection con=null;
	java.sql.Statement st=null;
    java.sql.ResultSet rs=null;
	java.sql.PreparedStatement pstmt=null;
	ResourceBundle rb = ResourceBundle.getBundle("LocalStrings");

	
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
	if (!txtmembercode.equals("") && (txtreporttype.equals("Reserve")) )
	{
	strsql = strsql + " and reserve_mas.Member_Code = '" +txtmembercode+"'";
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


	if ((!txttdate.equals("")) &&  (!txtfdate.equals("")))
	{
	String Date_From=getdate1(request.getParameter("txtfdate"));
	String Date_To=getdate1(request.getParameter("txttdate"));
	if (txtreporttype.equals("Reserve"))
	strsql = strsql + " and Res_Date between '"+Date_From+"' and '" +Date_To+"'";
	}
	if(!txtgroup.equals("NO")) strsql = strsql + " and Group_Code = '" +txtgroup+"'";
	order = "";
	if (!order1.equals("NO" )) order = order+order1;
	if (!order2.equals("NO" )) order = order+","+order2;
	if (!order3.equals("NO")) order = order+","+order3;


// CODE FOR RESERVE


  if ((txtreporttype.equals("Reserve")) && (txtdoctype.equals("ALL"))){
	if(report_type.equals("listing"))
	{
   
	out.print("<center><font size=5>RESERVE REPORT</center></font>");
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
	
	if(!order.equals("")){
		
		strsqlq = "select reserve_mas.member_code, reserve_mas.id, reserve_mas.access_no, reserve_mas.doc_type, reserve_mas.res_date, member_mas.member_name from reserve_mas, member_mas,sort_book where 1<2 and sort_book.Access_no=reserve_mas.access_no and member_mas.member_code=reserve_mas.member_code "+strsql+" and doc_type='BOOK' order by n1,n2,n3";
		}
	else{
		strsqlq = "select reserve_mas.member_code, reserve_mas.id, reserve_mas.access_no, reserve_mas.doc_type, reserve_mas.res_date, member_mas.member_name from reserve_mas, member_mas where 1<2 and member_mas.member_code=reserve_mas.member_code "+strsql+" and doc_type='BOOK' "+order+"";
	}
	
	
	pstmt=con.prepareStatement(strsqlq);
	rs=pstmt.executeQuery();
	if(!rs.next())
	{
	}
	else
	{
	rs.beforeFirst();

	sno=1;
	out.print("<font size=4>Document Type : BOOK</font>");
	out.print("<br><table border=1 width=700 align=center CELLSPACING='0' BORDERCOLOR='#DADADA' BGCOLOR='#F5F5F5'> ");
	out.print("<th>Sno</th><th>Id</th><th>Member_Code</th><th>Member_Name</th><th>AccNo</th><th>Doc_type</th><th>Res_Date</th>");

	while(rs.next())
	{
		String id=rs.getString("id");
		String member_code=rs.getString("member_code");
		String member_name=rs.getString("member_name");
		String access_no=rs.getString("access_no");
		String doc_type=rs.getString("doc_type");
		String res_date=getdate(rs.getString("res_date"));


		ser.add(id);
		ser.add(member_code);
		ser.add(member_name);
		ser.add(access_no);
		ser.add(doc_type);
		ser.add(res_date);

	}
	ob.setAl(ser);
	sc=(ArrayList)ob.getAl();
		int size=sc.size();
		for(int i=0;i<size;i++){
		 out.print("<tr><td><font size=2>"+sno+"</td>");
		 out.print("<td>"+sc.get(i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
                 out.print("</tr>");
		 sno=sno+1;
	}
	sc.clear();
	out.print("</table>");
	}
	
	if(!order.equals("")){
		strsqlq = "select reserve_mas.member_code, reserve_mas.id, reserve_mas.access_no, reserve_mas.doc_type, reserve_mas.res_date, member_mas.member_name from reserve_mas, member_mas,sort_book where 1<2 and sort_book.Access_no=reserve_mas.access_no and member_mas.member_code=reserve_mas.member_code "+strsql+" and doc_type='THESIS' order by n1,n2,n3";
		}
	else{
		strsqlq = "select reserve_mas.member_code, reserve_mas.id, reserve_mas.access_no, reserve_mas.doc_type, reserve_mas.res_date, member_mas.member_name from reserve_mas, member_mas where 1<2 and member_mas.member_code=reserve_mas.member_code "+strsql+" and doc_type='THESIS' "+order+"";
	    }
	
	
	pstmt=con.prepareStatement(strsqlq);
	rs=pstmt.executeQuery();
	if(!rs.next())
	{
	}
	else
	{
	rs.beforeFirst();

	sno=1;
	out.print("<font size=4>Document Type : THESIS</font>");
	out.print("<br><table border=1 width=700 align=center CELLSPACING='0' BORDERCOLOR='#DADADA' BGCOLOR='#F5F5F5'> ");
	out.print("<th>Sno</th><th>Id</th><th>Member_Code</th><th>Member_Name</th><th>AccNo</th><th>Doc_type</th><th>Res_Date</th>");

	while(rs.next())
	{
		String id=rs.getString("id");
		String member_code=rs.getString("member_code");
		String member_name=rs.getString("member_name");
		String access_no=rs.getString("access_no");
		String doc_type=rs.getString("doc_type");
		String res_date=getdate(rs.getString("res_date"));


		ser.add(id);
		ser.add(member_code);
		ser.add(member_name);
		ser.add(access_no);
		ser.add(doc_type);
		ser.add(res_date);

	}
	ob.setAl(ser);
	sc=(ArrayList)ob.getAl();
		int size=sc.size();
		for(int i=0;i<size;i++){
		 out.print("<tr><td><font size=2>"+sno+"</td>");
		 out.print("<td>"+sc.get(i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
                 out.print("</tr>");
		 sno=sno+1;
	}
	sc.clear();

	out.print("</table>");
	}
	
	
	
	if(!order.equals("")){
		strsqlq = "select reserve_mas.member_code, reserve_mas.id, reserve_mas.access_no, reserve_mas.doc_type, reserve_mas.res_date, member_mas.member_name from reserve_mas, member_mas,sort_book where 1<2 and sort_book.Access_no=reserve_mas.access_no and member_mas.member_code=reserve_mas.member_code "+strsql+" and doc_type='BACK VOLUME' order by n1,n2,n3";
		}
		else{
		strsqlq = "select reserve_mas.member_code, reserve_mas.id, reserve_mas.access_no, reserve_mas.doc_type, reserve_mas.res_date, member_mas.member_name from reserve_mas, member_mas where 1<2 and member_mas.member_code=reserve_mas.member_code "+strsql+" and doc_type='BACK VOLUME' "+order+"";
	    }
	
	
	pstmt=con.prepareStatement(strsqlq);
	rs=pstmt.executeQuery();
	if(!rs.next())
	{
	}
	else
	{
	rs.beforeFirst();

	sno=1;
	out.print("<font size=4>Document Type : BACK VOLUME</font>");
	out.print("<br><table border=1 width=700 align=center CELLSPACING='0' BORDERCOLOR='#DADADA' BGCOLOR='#F5F5F5'> ");
	out.print("<th>Sno</th><th>Id</th><th>Member_Code</th><th>Member_Name</th><th>AccNo</th><th>Doc_type</th><th>Res_Date</th>");

	while(rs.next())
	{
		String id=rs.getString("id");
		String member_code=rs.getString("member_code");
		String member_name=rs.getString("member_name");
		String access_no=rs.getString("access_no");
		String doc_type=rs.getString("doc_type");
		String res_date=getdate(rs.getString("res_date"));


		ser.add(id);
		ser.add(member_code);
		ser.add(member_name);
		ser.add(access_no);
		ser.add(doc_type);
		ser.add(res_date);

	}
	ob.setAl(ser);
	sc=(ArrayList)ob.getAl();
		int size=sc.size();
		for(int i=0;i<size;i++){
		out.print("<tr><td><font size=2>"+sno+"</td>");
		 out.print("<td>"+sc.get(i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
                 out.print("</tr>");
		 sno=sno+1;
	}
	sc.clear();

	out.print("</table>");
	}
	
	
	if(!order.equals("")){
		strsqlq = "select reserve_mas.member_code, reserve_mas.id, reserve_mas.access_no, reserve_mas.doc_type, reserve_mas.res_date, member_mas.member_name from reserve_mas, member_mas,sort_book where 1<2 and sort_book.Access_no=reserve_mas.access_no and member_mas.member_code=reserve_mas.member_code "+strsql+" and doc_type='PROCEEDING' order by n1,n2,n3";
	}
	 else{
		strsqlq = "select reserve_mas.member_code, reserve_mas.id, reserve_mas.access_no, reserve_mas.doc_type, reserve_mas.res_date, member_mas.member_name from reserve_mas, member_mas where 1<2 and member_mas.member_code=reserve_mas.member_code "+strsql+" and doc_type='PROCEEDING' "+order+"";
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
	out.print("<font size=4>Document Type : PROCEEDING</font>");
	out.print("<br><table border=1 width=700 align=center CELLSPACING='0' BORDERCOLOR='#DADADA' BGCOLOR='#F5F5F5'> ");
	out.print("<th>Sno</th><th>Id</th><th>Member_Code</th><th>Member_Name</th><th>AccNo</th><th>Doc_type</th><th>Res_Date</th>");

	while(rs.next())
	{
		String id=rs.getString("id");
		String member_code=rs.getString("member_code");
		String member_name=rs.getString("member_name");
		String access_no=rs.getString("access_no");
		String doc_type=rs.getString("doc_type");
		String res_date=getdate(rs.getString("res_date"));


		ser.add(id);
		ser.add(member_code);
		ser.add(member_name);
		ser.add(access_no);
		ser.add(doc_type);
		ser.add(res_date);

	}
	ob.setAl(ser);
	sc=(ArrayList)ob.getAl();
		int size=sc.size();
		for(int i=0;i<size;i++){
		out.print("<tr><td><font size=2>"+sno+"</td>");
		 out.print("<td>"+sc.get(i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
                 out.print("</tr>");
		 sno=sno+1;
	}
	sc.clear();

	out.print("</table>");
	}
	
	if(!order.equals("")){
		strsqlq = "select reserve_mas.member_code, reserve_mas.id, reserve_mas.access_no, reserve_mas.doc_type, reserve_mas.res_date, member_mas.member_name from reserve_mas, member_mas,sort_book where 1<2 and sort_book.Access_no=reserve_mas.access_no and member_mas.member_code=reserve_mas.member_code "+strsql+" and doc_type='REPORT' order by n1,n2,n3";
		}
	else{
		strsqlq = "select reserve_mas.member_code, reserve_mas.id, reserve_mas.access_no, reserve_mas.doc_type, reserve_mas.res_date, member_mas.member_name from reserve_mas, member_mas where 1<2 and member_mas.member_code=reserve_mas.member_code "+strsql+" and doc_type='REPORT' "+order+"";
	}
	
	pstmt=con.prepareStatement(strsqlq);
	rs=pstmt.executeQuery();
	if(!rs.next())
	{
	}
	else
	{
	rs.beforeFirst();

	sno=1;
	out.print("<font size=4>Document Type : REPORT</font>");
	out.print("<br><table border=1 width=700 align=center CELLSPACING='0' BORDERCOLOR='#DADADA' BGCOLOR='#F5F5F5'> ");
	out.print("<th>Sno</th><th>Id</th><th>Member_Code</th><th>Member_Name</th><th>AccNo</th><th>Doc_type</th><th>Res_Date</th>");

	while(rs.next())
	{
		String id=rs.getString("id");
		String member_code=rs.getString("member_code");
		String member_name=rs.getString("member_name");
		String access_no=rs.getString("access_no");
		String doc_type=rs.getString("doc_type");
		String res_date=getdate(rs.getString("res_date"));


		ser.add(id);
		ser.add(member_code);
		ser.add(member_name);
		ser.add(access_no);
		ser.add(doc_type);
		ser.add(res_date);

	}
	ob.setAl(ser);
	sc=(ArrayList)ob.getAl();
		int size=sc.size();
		for(int i=0;i<size;i++){
		out.print("<tr><td><font size=2>"+sno+"</td>");
		 out.print("<td>"+sc.get(i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
                 out.print("</tr>");
		 sno=sno+1;
	}
	sc.clear();

	out.print("</table>");
	}
	
	
	if(!order.equals("")){
		strsqlq = "select reserve_mas.member_code, reserve_mas.id, reserve_mas.access_no, reserve_mas.doc_type, reserve_mas.res_date, member_mas.member_name from reserve_mas, member_mas,sort_book where 1<2 and sort_book.Access_no=reserve_mas.access_no and member_mas.member_code=reserve_mas.member_code "+strsql+" and doc_type='NON BOOK' order by n1,n2,n3";
		}
		else{
		strsqlq = "select reserve_mas.member_code, reserve_mas.id, reserve_mas.access_no, reserve_mas.doc_type, reserve_mas.res_date, member_mas.member_name from reserve_mas, member_mas where 1<2 and member_mas.member_code=reserve_mas.member_code "+strsql+" and doc_type='NON BOOK' "+order+"";
	}
	
	pstmt=con.prepareStatement(strsqlq);
	rs=pstmt.executeQuery();
	if(!rs.next())
	{
	}
	else
	{
	rs.beforeFirst();

	sno=1;
	out.print("<font size=4>Document Type : NON BOOK</font>");
	out.print("<br><table border=1 width=700 align=center CELLSPACING='0' BORDERCOLOR='#DADADA' BGCOLOR='#F5F5F5'> ");
	out.print("<th>Sno</th><th>Id</th><th>Member_Code</th><th>Member_Name</th><th>AccNo</th><th>Doc_type</th><th>Res_Date</th>");

	while(rs.next())
	{
		String id=rs.getString("id");
		String member_code=rs.getString("member_code");
		String member_name=rs.getString("member_name");
		String access_no=rs.getString("access_no");
		String doc_type=rs.getString("doc_type");
		String res_date=getdate(rs.getString("res_date"));


		ser.add(id);
		ser.add(member_code);
		ser.add(member_name);
		ser.add(access_no);
		ser.add(doc_type);
		ser.add(res_date);

	}
	ob.setAl(ser);
	sc=(ArrayList)ob.getAl();
		int size=sc.size();
		for(int i=0;i<size;i++){
		 out.print("<tr><td><font size=2>"+sno+"</td>");
		 out.print("<td>"+sc.get(i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
                 out.print("</tr>");
		 sno=sno+1;
	}
	sc.clear();

	out.print("</table>");
	}
	
	if(!order.equals("")){
	 strsqlq = "select reserve_mas.member_code, reserve_mas.id, reserve_mas.access_no, reserve_mas.doc_type, reserve_mas.res_date, member_mas.member_name from reserve_mas, member_mas,sort_book where 1<2 and sort_book.Access_no=reserve_mas.access_no and member_mas.member_code=reserve_mas.member_code "+strsql+" and doc_type='STANDARD' order by n1,n2,n3";
		}
		else{
		strsqlq = "select reserve_mas.member_code, reserve_mas.id, reserve_mas.access_no, reserve_mas.doc_type, reserve_mas.res_date, member_mas.member_name from reserve_mas, member_mas where 1<2 and member_mas.member_code=reserve_mas.member_code "+strsql+" and doc_type='STANDARD' "+order+"";
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
	out.print("<font size=4>Document Type : STANDARD</font>");
	out.print("<br><table border=1 width=700 align=center CELLSPACING='0' BORDERCOLOR='#DADADA' BGCOLOR='#F5F5F5'> ");
	out.print("<th>Sno</th><th>Id</th><th>Member_Code</th><th>Member_Name</th><th>AccNo</th><th>Doc_type</th><th>Res_Date</th>");

	while(rs.next())
	{
		String id=rs.getString("id");
		String member_code=rs.getString("member_code");
		String member_name=rs.getString("member_name");
		String access_no=rs.getString("access_no");
		String doc_type=rs.getString("doc_type");
		String res_date=getdate(rs.getString("res_date"));


		ser.add(id);
		ser.add(member_code);
		ser.add(member_name);
		ser.add(access_no);
		ser.add(doc_type);
		ser.add(res_date);

	}
	ob.setAl(ser);
	sc=(ArrayList)ob.getAl();
		int size=sc.size();
		for(int i=0;i<size;i++){
		 out.print("<tr><td><font size=2>"+sno+"</td>");
		 out.print("<td>"+sc.get(i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
                 out.print("</tr>");
		 sno=sno+1;
	}
	sc.clear();

	out.print("</table>");
	}
	}

	if(report_type.equals("breakup"))
	{
	strsqlq="select count(*) as count_book, res_date from reserve_mas where 1<2 "+strsql+" group by res_date order by res_date";
	
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
	total=0;
	out.print("<center><font size=5>RESERVE REPORT</center></font>");
	out.print("<font size=3>Document Type :ALL</font>");
	out.print("<br><table border=1 width=700 align=center CELLSPACING='0' BORDERCOLOR='#DADADA' BGCOLOR='#F5F5F5'> ");
	out.print("<th>Sno</th><th>ReserveDate</th><th>No Of Documents</th>");

	while(rs.next())
	{
		String idate=getdate(rs.getString("Res_Date"));
		count_book=rs.getString("count_book");

		ser.add(idate);
		ser.add(count_book);



	}
	ob.setAl(ser);
	sc=(ArrayList)ob.getAl();

		for(int i=0;i<sc.size();i++){
		 out.print("<tr><td><font size=2>"+sno+"</td>");
		 out.print("<td>"+sc.get(i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 out.print("</tr>");
		
		}
		sc.clear();
		rs.beforeFirst();
		 while(rs.next())
	{
		count_book=rs.getString("count_book");
		ser.add(count_book);
		
	}
	ob.setAl(ser);
	sc=(ArrayList)ob.getAl();

		int size=sc.size();
		for(int i=0;i<size;i++){
		String count_book=(String)sc.get(i);
		sno=sno+1;
		int x=Integer.parseInt(count_book);
		
		total=total+x;
		 }
	sc.clear();


	out.print("</table>");
	out.print("<br>");
	out.print("<br>");
	out.print("<br>");
	out.print("<font face=times new roman size=4><center>Total:  "+total+"</center></font>");

	}

	}


	if(report_type.equals("cumulative"))
	{
	strsqlq="select count(*) as count_book, res_date from reserve_mas where 1<2 "+strsql+" group by res_date order by res_date";
	
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
	total=0;
	out.print("<center><font size=5>RESERVE REPORT</center></font>");
	out.print("<font size=3>Document Type :ALL</font>");
	out.print("<br><table border=0 width=700 align=center CELLSPACING='0' BORDERCOLOR='#DADADA' BGCOLOR='#F5F5F5'> ");


	while(rs.next())
	{
		count_book=rs.getString("count_book");
		ser.add(count_book);

		
	}
		ob.setAl(ser);
		sc=(ArrayList)ob.getAl();

		int size=sc.size();
		for(int i=0;i<size;i++){
		String count_book=(String)sc.get(i);
		sno=sno+1;
		int x=Integer.parseInt(count_book);
		total=total+x;
		 }
		 sc.clear();

	out.print("</table>");
        out.print("<br>");
	out.print("<br>");
	out.print("<br>");
	out.print("<font face=times new roman size=4><center>No Of Documents:  "+total+"</center></font>");

	}

	}
	}


/*---------------------------- RESERVE BOOK----------------------------------------------------------------------------------------------------------------*/

	if ((txtreporttype.equals("Reserve")) && (txtdoctype.equals("BOOK"))){
	if(report_type.equals("listing"))
	{
   	
   	if(!order.equals("")){
   	 strsqlq = "select reserve_mas.member_code, reserve_mas.id, reserve_mas.access_no, reserve_mas.doc_type, reserve_mas.res_date, member_mas.member_name from reserve_mas, member_mas,sort_book where 1<2 and sort_book.Access_no=reserve_mas.access_no and member_mas.member_code=reserve_mas.member_code "+strsql+" and doc_type='BOOK' order by n1,n2,n3";
   		}
   		else{
   			strsqlq = "select reserve_mas.member_code, reserve_mas.id, reserve_mas.access_no, reserve_mas.doc_type, reserve_mas.res_date, member_mas.member_name from reserve_mas, member_mas where 1<2 and member_mas.member_code=reserve_mas.member_code "+strsql+" and doc_type='BOOK' "+order+"";
   	}
   	
	
	pstmt=con.prepareStatement(strsqlq);
	rs=pstmt.executeQuery();
	if(!rs.next())
	{
	out.print("<font face=times new roman size=5 color=blue><center>No Record Found</center></font><br>");
	out.print("<tr><td><center><input type=button name=click value=back onclick=backward();></center></td></tr>");

	}
	else
	{
	rs.beforeFirst();

	sno=1;

	out.print("<center><font size=5>RESERVE REPORT</center></font>");
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
	out.print("<th>Sno</th><th>Id</th><th>Member_Code</th><th>Member_Name</th><th>AccNo</th><th>Doc_type</th><th>Res_Date</th>");

	while(rs.next())
	{
		String id=rs.getString("id");
		String member_code=rs.getString("member_code");
		String member_name=rs.getString("member_name");
		String access_no=rs.getString("access_no");
		String doc_type=rs.getString("doc_type");
		String res_date=getdate(rs.getString("res_date"));


		ser.add(id);
		ser.add(member_code);
		ser.add(member_name);
		ser.add(access_no);
		ser.add(doc_type);
		ser.add(res_date);


	}
	ob.setAl(ser);
	sc=(ArrayList)ob.getAl();
		int size=sc.size();
		for(int i=0;i<size;i++){
		 out.print("<tr><td><font size=2>"+sno+"</td>");
		 out.print("<td>"+sc.get(i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
                 out.print("</tr>");
		 sno=sno+1;
	}
	sc.clear();
	out.print("</table>");
	}
	}

	if(report_type.equals("breakup"))
	{
	total=0;
	strsqlq="select count(*) as count_book, res_date from reserve_mas where 1<2 "+strsql+" and doc_type='BOOK' group by res_date order by res_date";
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
	out.print("<center><font size=5>RESERVE REPORT</center></font>");
	out.print("<font size=3>Document Type :"+txtdoctype+"</font>");
	out.print("<br><table border=1 width=700 align=center CELLSPACING='0' BORDERCOLOR='#DADADA' BGCOLOR='#F5F5F5'> ");
	out.print("<th>Sno</th><th>ReserveDate</th><th>No Of Books</th>");
	while(rs.next())
	{
		String idate=getdate(rs.getString("Res_Date"));
		count_book=rs.getString("count_book");

		ser.add(idate);
		ser.add(count_book);



	}
	ob.setAl(ser);
	sc=(ArrayList)ob.getAl();

		for(int i=0;i<sc.size();i++){
		 out.print("<tr><td><font size=2>"+sno+"</td>");
		 out.print("<td>"+sc.get(i)+"</td>");
		 out.print("<td>"+sc.get(++i)+"</td>");
		 out.print("</tr>");
		
		}
		sc.clear();
		rs.beforeFirst();
		 while(rs.next())
	{
		count_book=rs.getString("count_book");
		ser.add(count_book);
	
	}
	ob.setAl(ser);
	sc=(ArrayList)ob.getAl();

		int size=sc.size();
		for(int i=0;i<size;i++){
		String count_book=(String)sc.get(i);
		sno=sno+1;
		int x=Integer.parseInt(count_book);
		// out.print(x);
		total=total+x;
		 }
	sc.clear();


	out.print("</table>");
	out.print("<br>");
	out.print("<br>");
	out.print("<br>");
	out.print("<font face=times new roman size=4><center>Total:  "+total+"</center></font>");

	}

	}


	if(report_type.equals("cumulative"))
	{
	total=0;
	strsqlq="select count(*) as count_book, res_date from reserve_mas where 1<2 "+strsql+" and doc_type='BOOK' group by res_date order by res_date";
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
	out.print("<center><font size=5>RESERVE REPORT</center></font>");
	out.print("<font size=3>Document Type :"+txtdoctype+"</font>");
	out.print("<br><table border=0 width=700 align=center CELLSPACING='0' BORDERCOLOR='#DADADA' BGCOLOR='#F5F5F5'> ");


	while(rs.next())
	{
		count_book=rs.getString("count_book");
		ser.add(count_book);

		
	}
		ob.setAl(ser);
		sc=(ArrayList)ob.getAl();

		int size=sc.size();
		for(int i=0;i<size;i++){
		String count_book=(String)sc.get(i);
		sno=sno+1;
		int x=Integer.parseInt(count_book);
		total=total+x;
		 }
		 sc.clear();
	out.print("</table>");
        out.print("<br>");
	out.print("<br>");
	out.print("<br>");
	out.print("<font face=times new roman size=4><center>No Of Books:  "+total+"</center></font>");

	}

	}
	}
	
	if ((txtreporttype.equals("Reserve")) && (txtdoctype.equals("THESIS"))){
		if(report_type.equals("listing"))
		{	   	
	   	if(!order.equals("")){
	      strsqlq = "select reserve_mas.member_code, reserve_mas.id, reserve_mas.access_no, reserve_mas.doc_type, reserve_mas.res_date, member_mas.member_name from reserve_mas, member_mas,sort_book where 1<2 and sort_book.Access_no=reserve_mas.access_no and member_mas.member_code=reserve_mas.member_code "+strsql+" and doc_type='THESIS' order by n1,n2,n3";
	     }
	     else{
	      strsqlq = "select reserve_mas.member_code, reserve_mas.id, reserve_mas.access_no, reserve_mas.doc_type, reserve_mas.res_date, member_mas.member_name from reserve_mas, member_mas where 1<2 and member_mas.member_code=reserve_mas.member_code "+strsql+" and doc_type='THESIS' "+order+"";
	     	}
		
		pstmt=con.prepareStatement(strsqlq);
		rs=pstmt.executeQuery();
		if(!rs.next())
		{
		out.print("<font face=times new roman size=5 color=blue><center>No Record Found</center></font><br>");
		out.print("<tr><td><center><input type=button name=click value=back onclick=backward();></center></td></tr>");

		}
		else
		{
		rs.beforeFirst();

		sno=1;

		out.print("<center><font size=5>RESERVE REPORT</center></font>");
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
		out.print("<th>Sno</th><th>Id</th><th>Member_Code</th><th>Member_Name</th><th>AccNo</th><th>Doc_type</th><th>Res_Date</th>");

		while(rs.next())
		{
			String id=rs.getString("id");
			String member_code=rs.getString("member_code");
			String member_name=rs.getString("member_name");
			String access_no=rs.getString("access_no");
			String doc_type=rs.getString("doc_type");
			String res_date=getdate(rs.getString("res_date"));


			ser.add(id);
			ser.add(member_code);
			ser.add(member_name);
			ser.add(access_no);
			ser.add(doc_type);
			ser.add(res_date);


		}
		ob.setAl(ser);
		sc=(ArrayList)ob.getAl();
			int size=sc.size();
			for(int i=0;i<size;i++){
			 out.print("<tr><td><font size=2>"+sno+"</td>");
			 out.print("<td>"+sc.get(i)+"</td>");
			 out.print("<td>"+sc.get(++i)+"</td>");
			 out.print("<td>"+sc.get(++i)+"</td>");
			 out.print("<td>"+sc.get(++i)+"</td>");
			 out.print("<td>"+sc.get(++i)+"</td>");
			 out.print("<td>"+sc.get(++i)+"</td>");
	                 out.print("</tr>");
			 sno=sno+1;
		}
		sc.clear();
		out.print("</table>");
		}
		}

		if(report_type.equals("breakup"))
		{
		total=0;
		strsqlq="select count(*) as count_book, res_date from reserve_mas where 1<2 "+strsql+" and doc_type='THESIS' group by res_date order by res_date";
		
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
		out.print("<center><font size=5>RESERVE REPORT</center></font>");
		out.print("<font size=3>Document Type :"+txtdoctype+"</font>");
		out.print("<br><table border=1 width=700 align=center CELLSPACING='0' BORDERCOLOR='#DADADA' BGCOLOR='#F5F5F5'> ");
		out.print("<th>Sno</th><th>ReserveDate</th><th>No Of Books</th>");
		while(rs.next())
		{
			String idate=getdate(rs.getString("Res_Date"));
			count_book=rs.getString("count_book");

			ser.add(idate);
			ser.add(count_book);



		}
		ob.setAl(ser);
		sc=(ArrayList)ob.getAl();

			for(int i=0;i<sc.size();i++){
			 out.print("<tr><td><font size=2>"+sno+"</td>");
			 out.print("<td>"+sc.get(i)+"</td>");
			 out.print("<td>"+sc.get(++i)+"</td>");
			 out.print("</tr>");
			
			}
			sc.clear();
			rs.beforeFirst();
			 while(rs.next())
		{
			count_book=rs.getString("count_book");
			ser.add(count_book);
			
		}
		ob.setAl(ser);
		sc=(ArrayList)ob.getAl();

			int size=sc.size();
			for(int i=0;i<size;i++){
			String count_book=(String)sc.get(i);
			sno=sno+1;
			int x=Integer.parseInt(count_book);
			// out.print(x);
			total=total+x;
			 }
		sc.clear();


		out.print("</table>");
		out.print("<br>");
		out.print("<br>");
		out.print("<br>");
		out.print("<font face=times new roman size=4><center>Total:  "+total+"</center></font>");

		}

		}


		if(report_type.equals("cumulative"))
		{
		total=0;
		strsqlq="select count(*) as count_book, res_date from reserve_mas where 1<2 "+strsql+" and doc_type='THESIS' group by res_date order by res_date";
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
		out.print("<center><font size=5>RESERVE REPORT</center></font>");
		out.print("<font size=3>Document Type :"+txtdoctype+"</font>");
		out.print("<br><table border=0 width=700 align=center CELLSPACING='0' BORDERCOLOR='#DADADA' BGCOLOR='#F5F5F5'> ");


		while(rs.next())
		{
			count_book=rs.getString("count_book");
			ser.add(count_book);
			
		}
			ob.setAl(ser);
			sc=(ArrayList)ob.getAl();

			int size=sc.size();
			for(int i=0;i<size;i++){
			String count_book=(String)sc.get(i);
			sno=sno+1;
			int x=Integer.parseInt(count_book);
			total=total+x;
			 }
			 sc.clear();
		out.print("</table>");
	        out.print("<br>");
		out.print("<br>");
		out.print("<br>");
		out.print("<font face=times new roman size=4><center>No Of Thesis:  "+total+"</center></font>");

		}

		}
		}
	
	if ((txtreporttype.equals("Reserve")) && (txtdoctype.equals("BACK VOLUME"))){
		if(report_type.equals("listing"))
		{
	   	//strsqlq = "select reserve_mas.member_code, reserve_mas.id, reserve_mas.access_no, reserve_mas.doc_type, reserve_mas.res_date, member_mas.member_name from reserve_mas, member_mas where 1<2 and member_mas.member_code=reserve_mas.member_code "+strsql+" and doc_type='BACK VOLUME' "+order+"";
	   	if(!order.equals("")){
		strsqlq = "select reserve_mas.member_code, reserve_mas.id, reserve_mas.access_no, reserve_mas.doc_type, reserve_mas.res_date, member_mas.member_name from reserve_mas, member_mas,sort_book where 1<2 and sort_book.Access_no=reserve_mas.access_no and member_mas.member_code=reserve_mas.member_code "+strsql+" and doc_type='BACK VOLUME' order by n1,n2,n3";
		}
	    else{
		strsqlq = "select reserve_mas.member_code, reserve_mas.id, reserve_mas.access_no, reserve_mas.doc_type, reserve_mas.res_date, member_mas.member_name from reserve_mas, member_mas where 1<2 and member_mas.member_code=reserve_mas.member_code "+strsql+" and doc_type='BACK VOLUME' "+order+"";
		}
		// out.print(strsqlq);
		//rs=st.executeQuery(strsqlq);
		pstmt=con.prepareStatement(strsqlq);
		rs=pstmt.executeQuery();
		if(!rs.next())
		{
		out.print("<font face=times new roman size=5 color=blue><center>No Record Found</center></font><br>");
		out.print("<tr><td><center><input type=button name=click value=back onclick=backward();></center></td></tr>");

		}
		else
		{
		rs.beforeFirst();

		sno=1;

		out.print("<center><font size=5>RESERVE REPORT</center></font>");
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
		out.print("<th>Sno</th><th>Id</th><th>Member_Code</th><th>Member_Name</th><th>AccNo</th><th>Doc_type</th><th>Res_Date</th>");

		while(rs.next())
		{
			String id=rs.getString("id");
			String member_code=rs.getString("member_code");
			String member_name=rs.getString("member_name");
			String access_no=rs.getString("access_no");
			String doc_type=rs.getString("doc_type");
			String res_date=getdate(rs.getString("res_date"));


			ser.add(id);
			ser.add(member_code);
			ser.add(member_name);
			ser.add(access_no);
			ser.add(doc_type);
			ser.add(res_date);


		}
		ob.setAl(ser);
		sc=(ArrayList)ob.getAl();
			int size=sc.size();
			for(int i=0;i<size;i++){
			 out.print("<tr><td><font size=2>"+sno+"</td>");
			 out.print("<td>"+sc.get(i)+"</td>");
			 out.print("<td>"+sc.get(++i)+"</td>");
			 out.print("<td>"+sc.get(++i)+"</td>");
			 out.print("<td>"+sc.get(++i)+"</td>");
			 out.print("<td>"+sc.get(++i)+"</td>");
			 out.print("<td>"+sc.get(++i)+"</td>");
	                 out.print("</tr>");
			 sno=sno+1;
		}
		sc.clear();
		out.print("</table>");
		}
		}

		if(report_type.equals("breakup"))
		{
		total=0;
		strsqlq="select count(*) as count_book, res_date from reserve_mas where 1<2 "+strsql+" and doc_type='BACK VOLUME' group by res_date order by res_date";
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
		out.print("<center><font size=5>RESERVE REPORT</center></font>");
		out.print("<font size=3>Document Type :"+txtdoctype+"</font>");
		out.print("<br><table border=1 width=700 align=center CELLSPACING='0' BORDERCOLOR='#DADADA' BGCOLOR='#F5F5F5'> ");
		out.print("<th>Sno</th><th>ReserveDate</th><th>No Of Books</th>");
		while(rs.next())
		{
			String idate=getdate(rs.getString("Res_Date"));
			count_book=rs.getString("count_book");

			ser.add(idate);
			ser.add(count_book);



		}
		ob.setAl(ser);
		sc=(ArrayList)ob.getAl();

			for(int i=0;i<sc.size();i++){
			 out.print("<tr><td><font size=2>"+sno+"</td>");
			 out.print("<td>"+sc.get(i)+"</td>");
			 out.print("<td>"+sc.get(++i)+"</td>");
			 out.print("</tr>");
			
			}
			sc.clear();
			rs.beforeFirst();
			 while(rs.next())
		{
			count_book=rs.getString("count_book");
			ser.add(count_book);
			
		}
		ob.setAl(ser);
		sc=(ArrayList)ob.getAl();

			int size=sc.size();
			for(int i=0;i<size;i++){
			String count_book=(String)sc.get(i);
			sno=sno+1;
			int x=Integer.parseInt(count_book);
			
			total=total+x;
			 }
		sc.clear();


		out.print("</table>");
		out.print("<br>");
		out.print("<br>");
		out.print("<br>");
		out.print("<font face=times new roman size=4><center>Total:  "+total+"</center></font>");

		}

		}


		if(report_type.equals("cumulative"))
		{
		total=0;
		strsqlq="select count(*) as count_book, res_date from reserve_mas where 1<2 "+strsql+" and doc_type='BACK VOLUME' group by res_date order by res_date";
		
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
		out.print("<center><font size=5>RESERVE REPORT</center></font>");
		out.print("<font size=3>Document Type :"+txtdoctype+"</font>");
		out.print("<br><table border=0 width=700 align=center CELLSPACING='0' BORDERCOLOR='#DADADA' BGCOLOR='#F5F5F5'> ");


		while(rs.next())
		{
			count_book=rs.getString("count_book");
			ser.add(count_book);

			/* count_book=rs.getString("count_book");
			sno=sno+1;
			int x=Integer.parseInt(count_book);
			total=total+x; */
		}
			ob.setAl(ser);
			sc=(ArrayList)ob.getAl();

			int size=sc.size();
			for(int i=0;i<size;i++){
			String count_book=(String)sc.get(i);
			sno=sno+1;
			int x=Integer.parseInt(count_book);
			total=total+x;
			 }
			 sc.clear();
		out.print("</table>");
	        out.print("<br>");
		out.print("<br>");
		out.print("<br>");
		out.print("<font face=times new roman size=4><center>No Of Back Volume:  "+total+"</center></font>");

		}

		}
		}
	
	if ((txtreporttype.equals("Reserve")) && (txtdoctype.equals("PROCEEDING"))){
		if(report_type.equals("listing"))
		{
	   	//strsqlq = "select reserve_mas.member_code, reserve_mas.id, reserve_mas.access_no, reserve_mas.doc_type, reserve_mas.res_date, member_mas.member_name from reserve_mas, member_mas where 1<2 and member_mas.member_code=reserve_mas.member_code "+strsql+" and doc_type='PROCEEDING' "+order+"";
	   	if(!order.equals("")){
			strsqlq = "select reserve_mas.member_code, reserve_mas.id, reserve_mas.access_no, reserve_mas.doc_type, reserve_mas.res_date, member_mas.member_name from reserve_mas, member_mas,sort_book where 1<2 and sort_book.Access_no=reserve_mas.access_no and member_mas.member_code=reserve_mas.member_code "+strsql+" and doc_type='PROCEEDING' order by n1,n2,n3";
			}
		    else{
			strsqlq = "select reserve_mas.member_code, reserve_mas.id, reserve_mas.access_no, reserve_mas.doc_type, reserve_mas.res_date, member_mas.member_name from reserve_mas, member_mas where 1<2 and member_mas.member_code=reserve_mas.member_code "+strsql+" and doc_type='PROCEEDING' "+order+"";
			}
		
		pstmt=con.prepareStatement(strsqlq);
		rs=pstmt.executeQuery();
		if(!rs.next())
		{
		out.print("<font face=times new roman size=5 color=blue><center>No Record Found</center></font><br>");
		out.print("<tr><td><center><input type=button name=click value=back onclick=backward();></center></td></tr>");

		}
		else
		{
		rs.beforeFirst();

		sno=1;

		out.print("<center><font size=5>RESERVE REPORT</center></font>");
		out.print("<font size=5>Report Date :"+Today_Date+"</font>");
		out.print(" <table border='1' width='258'  CELLSPACING='0' BORDERCOLOR='#DADADA' BGCOLOR='#F5F5F5'>");
		if(!txtmembercode.equals(""))out.print("<tr><td width='111' height='10'>Member Code</td><td width='129' height='10'>"+txtmembercode+"</td></tr>");
		if(!txtdepartment.equals("NO")) out.print("<tr><td width='111' height='10'>Department</td><td width='129' height='10'>"+deptname+"</td></tr>");
		if(!txtfdate.equals(""))  out.print("<tr><td width='111' height='10'>From Date</td><td width='129' height='10'>"+txtfdate+"</td></tr>");
		if(!txttdate.equals(""))  out.print("<tr><td width='111' height='10'>To Date</td><td width='129' height='10'>"+txttdate+"</td> </tr>");
		if(!txtgroup.equals("NO")) out.print("<tr><td width='111' height='10'>Group</td><td width='129' height='10'>"+groupname+"</td></tr>");
		if(!txtcourse.equals("NO")) out.print("<tr><td width='111' height='10'>Course</td><td width='129' height='10'>"+ coursename+"</td></tr>");
		
		out.print("</table>");
		out.print("<br><table border=1 width=700 align=center CELLSPACING='0' BORDERCOLOR='#DADADA' BGCOLOR='#F5F5F5'> ");
		out.print("<th>Sno</th><th>Id</th><th>Member_Code</th><th>Member_Name</th><th>AccNo</th><th>Doc_type</th><th>Res_Date</th>");

		while(rs.next())
		{
			String id=rs.getString("id");
			String member_code=rs.getString("member_code");
			String member_name=rs.getString("member_name");
			String access_no=rs.getString("access_no");
			String doc_type=rs.getString("doc_type");
			String res_date=getdate(rs.getString("res_date"));


			ser.add(id);
			ser.add(member_code);
			ser.add(member_name);
			ser.add(access_no);
			ser.add(doc_type);
			ser.add(res_date);


		}
		ob.setAl(ser);
		sc=(ArrayList)ob.getAl();
			int size=sc.size();
			for(int i=0;i<size;i++){
			 out.print("<tr><td><font size=2>"+sno+"</td>");
			 out.print("<td>"+sc.get(i)+"</td>");
			 out.print("<td>"+sc.get(++i)+"</td>");
			 out.print("<td>"+sc.get(++i)+"</td>");
			 out.print("<td>"+sc.get(++i)+"</td>");
			 out.print("<td>"+sc.get(++i)+"</td>");
			 out.print("<td>"+sc.get(++i)+"</td>");
	                 out.print("</tr>");
			 sno=sno+1;
		}
		sc.clear();
		out.print("</table>");
		}
		}

		if(report_type.equals("breakup"))
		{
		total=0;
		strsqlq="select count(*) as count_book, res_date from reserve_mas where 1<2 "+strsql+" and doc_type='PROCEEDING' group by res_date order by res_date";
		
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
		out.print("<center><font size=5>RESERVE REPORT</center></font>");
		out.print("<font size=3>Document Type :"+txtdoctype+"</font>");
		out.print("<br><table border=1 width=700 align=center CELLSPACING='0' BORDERCOLOR='#DADADA' BGCOLOR='#F5F5F5'> ");
		out.print("<th>Sno</th><th>ReserveDate</th><th>No Of Books</th>");
		while(rs.next())
		{
			String idate=getdate(rs.getString("Res_Date"));
			count_book=rs.getString("count_book");

			ser.add(idate);
			ser.add(count_book);



		}
		ob.setAl(ser);
		sc=(ArrayList)ob.getAl();

			for(int i=0;i<sc.size();i++){
			 out.print("<tr><td><font size=2>"+sno+"</td>");
			 out.print("<td>"+sc.get(i)+"</td>");
			 out.print("<td>"+sc.get(++i)+"</td>");
			 out.print("</tr>");
			
			}
			sc.clear();
			rs.beforeFirst();
			 while(rs.next())
		{
			count_book=rs.getString("count_book");
			ser.add(count_book);
			
		}
		ob.setAl(ser);
		sc=(ArrayList)ob.getAl();

			int size=sc.size();
			for(int i=0;i<size;i++){
			String count_book=(String)sc.get(i);
			sno=sno+1;
			int x=Integer.parseInt(count_book);
			
			total=total+x;
			 }
		sc.clear();


		out.print("</table>");
		out.print("<br>");
		out.print("<br>");
		out.print("<br>");
		out.print("<font face=times new roman size=4><center>Total:  "+total+"</center></font>");

		}

		}


		if(report_type.equals("cumulative"))
		{
		total=0;
		strsqlq="select count(*) as count_book, res_date from reserve_mas where 1<2 "+strsql+" and doc_type='PROCEEDING' group by res_date order by res_date";
		
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
		out.print("<center><font size=5>RESERVE REPORT</center></font>");
		out.print("<font size=3>Document Type :"+txtdoctype+"</font>");
		out.print("<br><table border=0 width=700 align=center CELLSPACING='0' BORDERCOLOR='#DADADA' BGCOLOR='#F5F5F5'> ");


		while(rs.next())
		{
			count_book=rs.getString("count_book");
			ser.add(count_book);
			
		}
			ob.setAl(ser);
			sc=(ArrayList)ob.getAl();

			int size=sc.size();
			for(int i=0;i<size;i++){
			String count_book=(String)sc.get(i);
			sno=sno+1;
			int x=Integer.parseInt(count_book);
			total=total+x;
			 }
			 sc.clear();
		out.print("</table>");
	        out.print("<br>");
		out.print("<br>");
		out.print("<br>");
		out.print("<font face=times new roman size=4><center>No Of Proceeding:  "+total+"</center></font>");

		}

		}
		}
	
	if ((txtreporttype.equals("Reserve")) && (txtdoctype.equals("REPORT"))){
		if(report_type.equals("listing"))
		{
	   	if(!order.equals("")){
		strsqlq = "select reserve_mas.member_code, reserve_mas.id, reserve_mas.access_no, reserve_mas.doc_type, reserve_mas.res_date, member_mas.member_name from reserve_mas, member_mas,sort_book where 1<2 and sort_book.Access_no=reserve_mas.access_no and member_mas.member_code=reserve_mas.member_code "+strsql+" and doc_type='REPORT' order by n1,n2,n3";
		}
		else{
		strsqlq = "select reserve_mas.member_code, reserve_mas.id, reserve_mas.access_no, reserve_mas.doc_type, reserve_mas.res_date, member_mas.member_name from reserve_mas, member_mas where 1<2 and member_mas.member_code=reserve_mas.member_code "+strsql+" and doc_type='REPORT' "+order+"";
		}
		
		pstmt=con.prepareStatement(strsqlq);
		rs=pstmt.executeQuery();
		if(!rs.next())
		{
		out.print("<font face=times new roman size=5 color=blue><center>No Record Found</center></font><br>");
		out.print("<tr><td><center><input type=button name=click value=back onclick=backward();></center></td></tr>");

		}
		else
		{
		rs.beforeFirst();

		sno=1;

		out.print("<center><font size=5>RESERVE REPORT</center></font>");
		out.print("<font size=5>Report Date :"+Today_Date+"</font>");
		out.print(" <table border='1' width='258'  CELLSPACING='0' BORDERCOLOR='#DADADA' BGCOLOR='#F5F5F5'>");
		if(!txtmembercode.equals(""))out.print("<tr><td width='111' height='10'>Member Code</td><td width='129' height='10'>"+txtmembercode+"</td></tr>");
		if(!txtdepartment.equals("NO")) out.print("<tr><td width='111' height='10'>Department</td><td width='129' height='10'>"+deptname+"</td></tr>");
		if(!txtfdate.equals(""))  out.print("<tr><td width='111' height='10'>From Date</td><td width='129' height='10'>"+txtfdate+"</td></tr>");
		if(!txttdate.equals(""))  out.print("<tr><td width='111' height='10'>To Date</td><td width='129' height='10'>"+txttdate+"</td> </tr>");
		if(!txtgroup.equals("NO")) out.print("<tr><td width='111' height='10'>Group</td><td width='129' height='10'>"+groupname+"</td></tr>");
		if(!txtcourse.equals("NO")) out.print("<tr><td width='111' height='10'>Course</td><td width='129' height='10'>"+ coursename+"</td></tr>");
		
		out.print("</table>");
		out.print("<br><table border=1 width=700 align=center CELLSPACING='0' BORDERCOLOR='#DADADA' BGCOLOR='#F5F5F5'> ");
		out.print("<th>Sno</th><th>Id</th><th>Member_Code</th><th>Member_Name</th><th>AccNo</th><th>Doc_type</th><th>Res_Date</th>");

		while(rs.next())
		{
			String id=rs.getString("id");
			String member_code=rs.getString("member_code");
			String member_name=rs.getString("member_name");
			String access_no=rs.getString("access_no");
			String doc_type=rs.getString("doc_type");
			String res_date=getdate(rs.getString("res_date"));


			ser.add(id);
			ser.add(member_code);
			ser.add(member_name);
			ser.add(access_no);
			ser.add(doc_type);
			ser.add(res_date);


		}
		ob.setAl(ser);
		sc=(ArrayList)ob.getAl();
			int size=sc.size();
			for(int i=0;i<size;i++){
			 out.print("<tr><td><font size=2>"+sno+"</td>");
			 out.print("<td>"+sc.get(i)+"</td>");
			 out.print("<td>"+sc.get(++i)+"</td>");
			 out.print("<td>"+sc.get(++i)+"</td>");
			 out.print("<td>"+sc.get(++i)+"</td>");
			 out.print("<td>"+sc.get(++i)+"</td>");
			 out.print("<td>"+sc.get(++i)+"</td>");
	                 out.print("</tr>");
			 sno=sno+1;
		}
		sc.clear();
		out.print("</table>");
		}
		}

		if(report_type.equals("breakup"))
		{
		total=0;
		strsqlq="select count(*) as count_book, res_date from reserve_mas where 1<2 "+strsql+" and doc_type='REPORT' group by res_date order by res_date";
		
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
		out.print("<center><font size=5>RESERVE REPORT</center></font>");
		out.print("<font size=3>Document Type :"+txtdoctype+"</font>");
		out.print("<br><table border=1 width=700 align=center CELLSPACING='0' BORDERCOLOR='#DADADA' BGCOLOR='#F5F5F5'> ");
		out.print("<th>Sno</th><th>ReserveDate</th><th>No Of Books</th>");
		while(rs.next())
		{
			String idate=getdate(rs.getString("Res_Date"));
			count_book=rs.getString("count_book");

			ser.add(idate);
			ser.add(count_book);



		}
		ob.setAl(ser);
		sc=(ArrayList)ob.getAl();

			for(int i=0;i<sc.size();i++){
			 out.print("<tr><td><font size=2>"+sno+"</td>");
			 out.print("<td>"+sc.get(i)+"</td>");
			 out.print("<td>"+sc.get(++i)+"</td>");
			 out.print("</tr>");
			
			}
			sc.clear();
			rs.beforeFirst();
			 while(rs.next())
		{
			count_book=rs.getString("count_book");
			ser.add(count_book);
			
		}
		ob.setAl(ser);
		sc=(ArrayList)ob.getAl();

			int size=sc.size();
			for(int i=0;i<size;i++){
			String count_book=(String)sc.get(i);
			sno=sno+1;
			int x=Integer.parseInt(count_book);
			
			total=total+x;
			 }
		sc.clear();


		out.print("</table>");
		out.print("<br>");
		out.print("<br>");
		out.print("<br>");
		out.print("<font face=times new roman size=4><center>Total:  "+total+"</center></font>");

		}

		}


		if(report_type.equals("cumulative"))
		{
		total=0;
		strsqlq="select count(*) as count_book, res_date from reserve_mas where 1<2 "+strsql+" and doc_type='REPORT' group by res_date order by res_date";
		
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
		out.print("<center><font size=5>RESERVE REPORT</center></font>");
		out.print("<font size=3>Document Type :"+txtdoctype+"</font>");
		out.print("<br><table border=0 width=700 align=center CELLSPACING='0' BORDERCOLOR='#DADADA' BGCOLOR='#F5F5F5'> ");


		while(rs.next())
		{
			count_book=rs.getString("count_book");
			ser.add(count_book);

			
		}
			ob.setAl(ser);
			sc=(ArrayList)ob.getAl();

			int size=sc.size();
			for(int i=0;i<size;i++){
			String count_book=(String)sc.get(i);
			sno=sno+1;
			int x=Integer.parseInt(count_book);
			total=total+x;
			 }
			 sc.clear();
		out.print("</table>");
	        out.print("<br>");
		out.print("<br>");
		out.print("<br>");
		out.print("<font face=times new roman size=4><center>No Of Report:  "+total+"</center></font>");

		}

		}
		}
	
	if ((txtreporttype.equals("Reserve")) && (txtdoctype.equals("NON BOOK"))){
		if(report_type.equals("listing"))
		{
	   
	   	if(!order.equals("")){
			strsqlq = "select reserve_mas.member_code, reserve_mas.id, reserve_mas.access_no, reserve_mas.doc_type, reserve_mas.res_date, member_mas.member_name from reserve_mas, member_mas,sort_book where 1<2 and sort_book.Access_no=reserve_mas.access_no and member_mas.member_code=reserve_mas.member_code "+strsql+" and doc_type='NON BOOK' order by n1,n2,n3";
			}
			else{
			strsqlq = "select reserve_mas.member_code, reserve_mas.id, reserve_mas.access_no, reserve_mas.doc_type, reserve_mas.res_date, member_mas.member_name from reserve_mas, member_mas where 1<2 and member_mas.member_code=reserve_mas.member_code "+strsql+" and doc_type='NON BOOK' "+order+"";
			}
		
		pstmt=con.prepareStatement(strsqlq);
		rs=pstmt.executeQuery();
		if(!rs.next())
		{
		out.print("<font face=times new roman size=5 color=blue><center>No Record Found</center></font><br>");
		out.print("<tr><td><center><input type=button name=click value=back onclick=backward();></center></td></tr>");

		}
		else
		{
		rs.beforeFirst();

		sno=1;

		out.print("<center><font size=5>RESERVE REPORT</center></font>");
		out.print("<font size=5>Report Date :"+Today_Date+"</font>");
		out.print(" <table border='1' width='258'  CELLSPACING='0' BORDERCOLOR='#DADADA' BGCOLOR='#F5F5F5'>");
		if(!txtmembercode.equals(""))out.print("<tr><td width='111' height='10'>Member Code</td><td width='129' height='10'>"+txtmembercode+"</td></tr>");
		if(!txtdepartment.equals("NO")) out.print("<tr><td width='111' height='10'>Department</td><td width='129' height='10'>"+deptname+"</td></tr>");
		if(!txtfdate.equals(""))  out.print("<tr><td width='111' height='10'>From Date</td><td width='129' height='10'>"+txtfdate+"</td></tr>");
		if(!txttdate.equals(""))  out.print("<tr><td width='111' height='10'>To Date</td><td width='129' height='10'>"+txttdate+"</td> </tr>");
		if(!txtgroup.equals("NO")) out.print("<tr><td width='111' height='10'>Group</td><td width='129' height='10'>"+groupname+"</td></tr>");
		if(!txtcourse.equals("NO")) out.print("<tr><td width='111' height='10'>Course</td><td width='129' height='10'>"+ coursename+"</td></tr>");
		
		out.print("</table>");
		out.print("<br><table border=1 width=700 align=center CELLSPACING='0' BORDERCOLOR='#DADADA' BGCOLOR='#F5F5F5'> ");
		out.print("<th>Sno</th><th>Id</th><th>Member_Code</th><th>Member_Name</th><th>AccNo</th><th>Doc_type</th><th>Res_Date</th>");

		while(rs.next())
		{
			String id=rs.getString("id");
			String member_code=rs.getString("member_code");
			String member_name=rs.getString("member_name");
			String access_no=rs.getString("access_no");
			String doc_type=rs.getString("doc_type");
			String res_date=getdate(rs.getString("res_date"));


			ser.add(id);
			ser.add(member_code);
			ser.add(member_name);
			ser.add(access_no);
			ser.add(doc_type);
			ser.add(res_date);


		}
		ob.setAl(ser);
		sc=(ArrayList)ob.getAl();
			int size=sc.size();
			for(int i=0;i<size;i++){
			 out.print("<tr><td><font size=2>"+sno+"</td>");
			 out.print("<td>"+sc.get(i)+"</td>");
			 out.print("<td>"+sc.get(++i)+"</td>");
			 out.print("<td>"+sc.get(++i)+"</td>");
			 out.print("<td>"+sc.get(++i)+"</td>");
			 out.print("<td>"+sc.get(++i)+"</td>");
			 out.print("<td>"+sc.get(++i)+"</td>");
	                 out.print("</tr>");
			 sno=sno+1;
		}
		sc.clear();
		out.print("</table>");
		}
		}

		if(report_type.equals("breakup"))
		{
		total=0;
		strsqlq="select count(*) as count_book, res_date from reserve_mas where 1<2 "+strsql+" and doc_type='NON BOOK' group by res_date order by res_date";
		
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
		out.print("<center><font size=5>RESERVE REPORT</center></font>");
		out.print("<font size=3>Document Type :"+txtdoctype+"</font>");
		out.print("<br><table border=1 width=700 align=center CELLSPACING='0' BORDERCOLOR='#DADADA' BGCOLOR='#F5F5F5'> ");
		out.print("<th>Sno</th><th>ReserveDate</th><th>No Of Books</th>");
		while(rs.next())
		{
			String idate=getdate(rs.getString("Res_Date"));
			count_book=rs.getString("count_book");

			ser.add(idate);
			ser.add(count_book);



		}
		ob.setAl(ser);
		sc=(ArrayList)ob.getAl();

			for(int i=0;i<sc.size();i++){
			 out.print("<tr><td><font size=2>"+sno+"</td>");
			 out.print("<td>"+sc.get(i)+"</td>");
			 out.print("<td>"+sc.get(++i)+"</td>");
			 out.print("</tr>");
			
			}
			sc.clear();
			rs.beforeFirst();
			 while(rs.next())
		{
			count_book=rs.getString("count_book");
			ser.add(count_book);
			
		}
		ob.setAl(ser);
		sc=(ArrayList)ob.getAl();

			int size=sc.size();
			for(int i=0;i<size;i++){
			String count_book=(String)sc.get(i);
			sno=sno+1;
			int x=Integer.parseInt(count_book);
			// out.print(x);
			total=total+x;
			 }
		sc.clear();


		out.print("</table>");
		out.print("<br>");
		out.print("<br>");
		out.print("<br>");
		out.print("<font face=times new roman size=4><center>Total:  "+total+"</center></font>");

		}

		}


		if(report_type.equals("cumulative"))
		{
		total=0;
		strsqlq="select count(*) as count_book, res_date from reserve_mas where 1<2 "+strsql+" and doc_type='NON BOOK' group by res_date order by res_date";
		
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
		out.print("<center><font size=5>RESERVE REPORT</center></font>");
		out.print("<font size=3>Document Type :"+txtdoctype+"</font>");
		out.print("<br><table border=0 width=700 align=center CELLSPACING='0' BORDERCOLOR='#DADADA' BGCOLOR='#F5F5F5'> ");


		while(rs.next())
		{
			count_book=rs.getString("count_book");
			ser.add(count_book);

		}
			ob.setAl(ser);
			sc=(ArrayList)ob.getAl();

			int size=sc.size();
			for(int i=0;i<size;i++){
			String count_book=(String)sc.get(i);
			sno=sno+1;
			int x=Integer.parseInt(count_book);
			total=total+x;
			 }
			 sc.clear();
		out.print("</table>");
	        out.print("<br>");
		out.print("<br>");
		out.print("<br>");
		out.print("<font face=times new roman size=4><center>No Of Non Book:  "+total+"</center></font>");

		}

		}
		}
	
	if ((txtreporttype.equals("Reserve")) && (txtdoctype.equals("STANDARD"))){
		if(report_type.equals("listing"))
		{
	   
	   	if(!order.equals("")){
			strsqlq = "select reserve_mas.member_code, reserve_mas.id, reserve_mas.access_no, reserve_mas.doc_type, reserve_mas.res_date, member_mas.member_name from reserve_mas, member_mas,sort_book where 1<2 and sort_book.Access_no=reserve_mas.access_no and member_mas.member_code=reserve_mas.member_code "+strsql+" and doc_type='STANDARD' order by n1,n2,n3";
			}
			else{
			strsqlq = "select reserve_mas.member_code, reserve_mas.id, reserve_mas.access_no, reserve_mas.doc_type, reserve_mas.res_date, member_mas.member_name from reserve_mas, member_mas where 1<2 and member_mas.member_code=reserve_mas.member_code "+strsql+" and doc_type='STANDARD' "+order+"";
			}
		
		pstmt=con.prepareStatement(strsqlq);
		rs=pstmt.executeQuery();
		if(!rs.next())
		{
		out.print("<font face=times new roman size=5 color=blue><center>No Record Found</center></font><br>");
		out.print("<tr><td><center><input type=button name=click value=back onclick=backward();></center></td></tr>");

		}
		else
		{
		rs.beforeFirst();

		sno=1;

		out.print("<center><font size=5>RESERVE REPORT</center></font>");
		out.print("<font size=5>Report Date :"+Today_Date+"</font>");
		out.print(" <table border='1' width='258'  CELLSPACING='0' BORDERCOLOR='#DADADA' BGCOLOR='#F5F5F5'>");
		if(!txtmembercode.equals(""))out.print("<tr><td width='111' height='10'>Member Code</td><td width='129' height='10'>"+txtmembercode+"</td></tr>");
		if(!txtdepartment.equals("NO")) out.print("<tr><td width='111' height='10'>Department</td><td width='129' height='10'>"+deptname+"</td></tr>");
		if(!txtfdate.equals(""))  out.print("<tr><td width='111' height='10'>From Date</td><td width='129' height='10'>"+txtfdate+"</td></tr>");
		if(!txttdate.equals(""))  out.print("<tr><td width='111' height='10'>To Date</td><td width='129' height='10'>"+txttdate+"</td> </tr>");
		if(!txtgroup.equals("NO")) out.print("<tr><td width='111' height='10'>Group</td><td width='129' height='10'>"+groupname+"</td></tr>");
		if(!txtcourse.equals("NO")) out.print("<tr><td width='111' height='10'>Course</td><td width='129' height='10'>"+ coursename+"</td></tr>");
		
		out.print("</table>");
		out.print("<br><table border=1 width=700 align=center CELLSPACING='0' BORDERCOLOR='#DADADA' BGCOLOR='#F5F5F5'> ");
		out.print("<th>Sno</th><th>Id</th><th>Member_Code</th><th>Member_Name</th><th>AccNo</th><th>Doc_type</th><th>Res_Date</th>");

		while(rs.next())
		{
			String id=rs.getString("id");
			String member_code=rs.getString("member_code");
			String member_name=rs.getString("member_name");
			String access_no=rs.getString("access_no");
			String doc_type=rs.getString("doc_type");
			String res_date=getdate(rs.getString("res_date"));


			ser.add(id);
			ser.add(member_code);
			ser.add(member_name);
			ser.add(access_no);
			ser.add(doc_type);
			ser.add(res_date);


		}
		ob.setAl(ser);
		sc=(ArrayList)ob.getAl();
			int size=sc.size();
			for(int i=0;i<size;i++){
			 out.print("<tr><td><font size=2>"+sno+"</td>");
			 out.print("<td>"+sc.get(i)+"</td>");
			 out.print("<td>"+sc.get(++i)+"</td>");
			 out.print("<td>"+sc.get(++i)+"</td>");
			 out.print("<td>"+sc.get(++i)+"</td>");
			 out.print("<td>"+sc.get(++i)+"</td>");
			 out.print("<td>"+sc.get(++i)+"</td>");
	                 out.print("</tr>");
			 sno=sno+1;
		}
		sc.clear();
		out.print("</table>");
		}
		}

		if(report_type.equals("breakup"))
		{
		total=0;
		strsqlq="select count(*) as count_book, res_date from reserve_mas where 1<2 "+strsql+" and doc_type='STANDARD' group by res_date order by res_date";
		
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
		out.print("<center><font size=5>RESERVE REPORT</center></font>");
		out.print("<font size=3>Document Type :"+txtdoctype+"</font>");
		out.print("<br><table border=1 width=700 align=center CELLSPACING='0' BORDERCOLOR='#DADADA' BGCOLOR='#F5F5F5'> ");
		out.print("<th>Sno</th><th>ReserveDate</th><th>No Of Books</th>");
		while(rs.next())
		{
			String idate=getdate(rs.getString("Res_Date"));
			count_book=rs.getString("count_book");

			ser.add(idate);
			ser.add(count_book);



		}
		ob.setAl(ser);
		sc=(ArrayList)ob.getAl();

			for(int i=0;i<sc.size();i++){
			 out.print("<tr><td><font size=2>"+sno+"</td>");
			 out.print("<td>"+sc.get(i)+"</td>");
			 out.print("<td>"+sc.get(++i)+"</td>");
			 out.print("</tr>");
			
			}
			sc.clear();
			rs.beforeFirst();
			 while(rs.next())
		{
			count_book=rs.getString("count_book");
			ser.add(count_book);
			
		}
		ob.setAl(ser);
		sc=(ArrayList)ob.getAl();

			int size=sc.size();
			for(int i=0;i<size;i++){
			String count_book=(String)sc.get(i);
			sno=sno+1;
			int x=Integer.parseInt(count_book);
			// out.print(x);
			total=total+x;
			 }
		sc.clear();


		out.print("</table>");
		out.print("<br>");
		out.print("<br>");
		out.print("<br>");
		out.print("<font face=times new roman size=4><center>Total:  "+total+"</center></font>");

		}

		}


		if(report_type.equals("cumulative"))
		{
		total=0;
		strsqlq="select count(*) as count_book, res_date from reserve_mas where 1<2 "+strsql+" and doc_type='STANDARD' group by res_date order by res_date";
		
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
		out.print("<center><font size=5>RESERVE REPORT</center></font>");
		out.print("<font size=3>Document Type :"+txtdoctype+"</font>");
		out.print("<br><table border=0 width=700 align=center CELLSPACING='0' BORDERCOLOR='#DADADA' BGCOLOR='#F5F5F5'> ");


		while(rs.next())
		{
			count_book=rs.getString("count_book");
			ser.add(count_book);

		}
			ob.setAl(ser);
			sc=(ArrayList)ob.getAl();

			int size=sc.size();
			for(int i=0;i<size;i++){
			String count_book=(String)sc.get(i);
			sno=sno+1;
			int x=Integer.parseInt(count_book);
			total=total+x;
			 }
			 sc.clear();
		out.print("</table>");
	        out.print("<br>");
		out.print("<br>");
		out.print("<br>");
		out.print("<font face=times new roman size=4><center>No Of Standard:  "+total+"</center></font>");

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
					if(st!=null){
						st.close();
						st=null;
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
