<%@ page language="java" errorPage="/Error/ErrorPage.jsp" session="true" buffer="12kb" import="Lib.Auto.Designation.DesignationBean" import="Lib.Auto.Subject.subjectbean" import="Lib.Auto.Member.MemberBean" import="Lib.Auto.Department.DepartmentBean" import="Common.Security" import="java.util.ArrayList,java.util.Iterator" import="Lib.Auto.Book.BookSearchBean"%>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/button_css.css" />
<%//Security.checkSecurity(1,session,response,request);%>
<!--
//////////////////////////////////////DATABASE CONNECTION//////////////////////////////////////////////////////////////////////// -->
<%
//
//   Filename: Search.jsp
//   Form name:Subject_Find
//%>
<!--
//////////////////////////////////////FORM CONTROLS//////////////////////////////////////////////////////////////////////// -->
<html>
<head>
<title>AutoLib</title>
<%
   response.setHeader("Pragma", "No-cache");
   response.setHeader("Cache-Control", "no-cache");
   response.setDateHeader("Expires", 0);
%>
<link rel="stylesheet" type="text/css" href="/AutoLib/style.css">
</head>
<body onload="focuss()" background="/AutoLib/soft.jpg">
<form name="Member_Find" method="post" action=./SearchMemberServlet  ><!--onsubmit="return validate(this)"-->
<%
String flag=request.getParameter("flag");
//out.println(flag);
String SQLstr="";
String Title="";
String t1="",t2="";
String mainTitle="";
if (flag!=null){
if (flag.equals("Member")){
mainTitle="Member"; Title="Member&nbsp;Name";t1="Member&nbsp;Code";t2="Designation&nbsp;Code";
%>
<script>document.Member_Find.sflag.value="<%=flag%>";</script>
<%
}
else if (flag.equals("Department")){
mainTitle="Department"; Title="Department&nbsp;Name";t1="Department&nbsp;Code";t2="Short&nbsp;Desc";
%>
<script>document.Member_Find.sflag.value="<%=flag%>";</script>
<%
}
else if (flag.equals("Desig")){
mainTitle="Designation"; Title="Designation&nbsp;Name";t1="Designation&nbsp;Code";t2="Short&nbsp;Desc";
%>
<script>document.Member_Find.sflag.value="<%=flag%>";</script>
<%
}
else if (flag.equals("Group")){
mainTitle="Group"; Title="Group&nbsp;Name";t1="Group&nbsp;Code";t2="Remarks";
%>
<script>document.Member_Find.sflag.value="<%=flag%>";</script>
<%
}
else if (flag.equals("Course")){
mainTitle="Course"; Title="Course&nbsp;Name";t1="Course&nbsp;Code";t2="Course&nbsp;Major";
%>
<script>document.Member_Find.sflag.value="<%=flag%>";</script>
<%
}
else if (flag.equals("City")){
	mainTitle="City"; Title="State";t1="City&nbsp;Name";t2="Pincode";
	%>
	<script>document.Member_Find.sflag.value="<%=flag%>";</script>
	<%
	}
else if (flag.equals("Branch")){
	 mainTitle="Branch";Title="Branch&nbsp;Head ";t1="Branch&nbsp;Code";t2="Short&nbsp;Desc";
	%>
	 <script>document.Member_Find.sflag.value="<%=flag%>";</script>
	<%	 
	}
}

%>
<!--
//////////////////////////////////////JAVA SERVER PAGES SCRIPT ///////////////////////////////////////////////// -->
<h2><%=mainTitle%>&nbsp;Search</h2>
<div><A href=""  onclick="window.close()">Back</A></div>
<center><%=Title%>
<input type=text name=name>
<input Class="submitButton" type=submit value="Search" >
</center>
<input type=hidden name=sflag value="<%=flag%>">
</center></form>
</body>
</html>
<!--
//////////////////////////////////////JAVA SERVER PAGES SCRIPT /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// -->
<%


String ck=request.getParameter("check");
ArrayList sc=new ArrayList();
try{
if(ck!=null){
             
	         int i =1;
             sc=(ArrayList)request.getAttribute("serarch");

	     	     %>
  <script language="JavaScript">

document.Member_Find.name.value="<%=request.getParameter("nam")%>";
document.Member_Find.name.focus();
</script>
<%
if (flag.equals("Member")){
		out.print("<table border=1 bordercolor=#008000 align=center cellspacing=0 >");
		out.print("<tr><th>S.No</th><th>"+t1+"</th><th>"+Title+"</th></tr>");

		
		Iterator it=sc.iterator();
		while(it.hasNext()){
			MemberBean view=(MemberBean) it.next();
			  %>
					<tr onmouseover=this.style.color='red' onmouseout=this.style.color='black' onclick='show("<%=view.getCode()%>")'>
					<script language=javascript>
					 document.write("<td>"+"<%=i++%>" +"</td>");					
					 document.write("<td>"+"<%=view.getCode()%>" +"</td>");
					 document.write("<td>"+"<%=view.getName()%>" +"</td>");
					 //document.write("<td>"+"&nbsp;<%=view.getDecode()%>"+"</td>");
					 document.write("</tr>");
			 		</script>
					<%

					}
					}
					
if (flag.equals("Department")){

	out.print("<table border=1 bordercolor=#008000 align=center cellspacing=0 >");
	out.print("<tr><th>S.No</th><th>"+Title+"</th><th>"+t2+"</th></tr>");

	
	Iterator it=sc.iterator();
	while(it.hasNext()){
		subjectbean view=(subjectbean) it.next();
		  %>
				<tr onmouseover=this.style.color='red' onmouseout=this.style.color='black' onclick='show1("<%=view.getName()%>")'>
				<script language=javascript>
				 //document.write("<td>"+"<%=view.getCode()%>" +"</td>");
				 document.write("<td>"+"<%=i++%>" +"</td>");					
				 document.write("<td>"+"<%=view.getName()%>" +"</td>");
				 document.write("<td>"+"<%=view.getDesc()%>" +"</td>");
				 document.write("</tr>");
		 		</script>
				<%

				}
				}

if (flag.equals("Desig")){

	out.print("<table border=1 bordercolor=#008000 align=center cellspacing=0 >");
	out.print("<tr><th>S.No</th><th>"+Title+"</th><th>"+t2+"</th></tr>");

	
	Iterator it=sc.iterator();
	while(it.hasNext()){
		subjectbean view=(subjectbean) it.next();
		  %>
				<tr onmouseover=this.style.color='red' onmouseout=this.style.color='black' onclick='show1("<%=view.getName()%>")'>
				<script language=javascript>
				 //document.write("<td>"+"<%=view.getCode()%>" +"</td>");
				 document.write("<td>"+"<%=i++%>" +"</td>");					
				 document.write("<td>"+"<%=view.getName()%>" +"</td>");
				 document.write("<td>"+"<%=view.getDesc()%>" +"</td>");
				 document.write("</tr>");
		 		</script>
				<%

				}
				}
if (flag.equals("Group")){

	out.print("<table border=1 bordercolor=#008000 align=center cellspacing=0 >");
	out.print("<tr><th>S.No</th><th>"+Title+"</th><th>"+t2+"</th></tr>");

	
	Iterator it=sc.iterator();
	while(it.hasNext()){
		subjectbean view=(subjectbean) it.next();
		  %>
				<tr onmouseover=this.style.color='red' onmouseout=this.style.color='black' onclick='show1("<%=view.getName()%>")'>
				<script language=javascript>
				 //document.write("<td>"+"<%=view.getCode()%>" +"</td>");
				 document.write("<td>"+"<%=i++%>" +"</td>");					
				 document.write("<td>"+"<%=view.getName()%>" +"</td>");
				 document.write("<td>"+"<%=view.getDesc()%>" +"</td>");
				 document.write("</tr>");
		 		</script>
				<%

				}
				}
if (flag.equals("Course")){
	
	out.print("<table border=1 bordercolor=#008000 align=center cellspacing=0 >");
	out.print("<tr><th>S.No</th><th>"+Title+"</th><th>"+t2+"</th></tr>");

	
	Iterator it=sc.iterator();
	while(it.hasNext()){
		subjectbean view=(subjectbean) it.next();
		  %>
				<tr onmouseover=this.style.color='red' onmouseout=this.style.color='black' onclick='show1("<%=view.getName()%>-<%=view.getDesc()%>")'>
				<script language=javascript>
				 //document.write("<td>"+"<%=view.getCode()%>" +"</td>");
				 document.write("<td>"+"<%=i++%>" +"</td>");					
				 document.write("<td>"+"<%=view.getName()%>" +"</td>");
				 document.write("<td>"+"<%=view.getDesc()%>" +"</td>");
				 document.write("</tr>");
		 		</script>
				<%

				}
				}

if (flag.equals("Branch")){
	
	out.print("<table border=1 bordercolor=#008000 align=center cellspacing=0 >");
	out.print("<tr><th>S.No</th><th>"+Title+"</th><th>"+t2+"</th></tr>");

	
	Iterator it=sc.iterator();
	while(it.hasNext()){
		BookSearchBean view=(BookSearchBean) it.next();
		  %>
		<tr onmouseover=this.style.color='red' onmouseout=this.style.color='black' onclick='show1("<%=view.getBname()%>")'>
		<script language=javascript>
		 //document.write("<td>"+"<%=view.getBcode()%>" +"</td>");
		 document.write("<td>"+"<%=i++%>" +"</td>");					
		 document.write("<td>"+"<%=view.getBname()%>" +"</td>");
		 document.write("<td>"+"&nbsp;<%=view.getBdesc()%>"+"</td>");
		 document.write("</tr>");
 		</script>

				<%

				}
				}

if (flag.equals("City")){

	out.print("<table border=1 bordercolor=#008000 align=center cellspacing=0 >");
	out.print("<tr><th>S.No</th><th>"+t1+"</th><th>"+Title+"</th><th>"+t2+"</th></tr>");

	
	Iterator it=sc.iterator();
	while(it.hasNext()){
		subjectbean view=(subjectbean) it.next();
		  %>
				<tr onmouseover=this.style.color='red' onmouseout=this.style.color='black' onclick='show12("<%=view.getName()%>","<%=view.getDesc()%>","<%=view.getCode()%>")'>
				<script language=javascript>				 
				 document.write("<td>"+"<%=i++%>" +"</td>");					
				 document.write("<td>"+"<%=view.getName()%>" +"</td>");
				 document.write("<td>"+"<%=view.getDesc()%>" +"</td>");
				 document.write("<td>"+"<%=view.getCode()%>" +"</td>");
				 document.write("</tr>");
		 		</script>
				<%
				}
				}
sc.clear();
}
 }catch (Exception e) {e.printStackTrace();}
   finally{
    sc.clear();
    }
%>
<script  language="javascript">
function show(val){
window.opener.document.member.Code.value=val;
window.opener.document.member.method="post";
window.opener.document.member.flag.value="search";
window.opener.document.member.action="./MemberServlet";
window.opener.document.member.submit();
window.close();
}
function show1(val){

if(document.Member_Find.sflag.value=="Department"){
window.opener.document.member.Dname.value=val;
}
if(document.Member_Find.sflag.value=="Group"){
window.opener.document.member.Gname.value=val;
}
if(document.Member_Find.sflag.value=="Course"){
window.opener.document.member.Cname.value=val;
}
if(document.Member_Find.sflag.value=="Desig"){
window.opener.document.member.Desig.value=val;
}
if(document.Member_Find.sflag.value=="Branch"){
window.opener.document.member.Add2.value=val;
}
window.close();
}

function show12(val1,val2,val3){

if(document.Member_Find.sflag.value=="City"){
window.opener.document.member.City.value=val1;
window.opener.document.member.State.value=val2;
window.opener.document.member.Pin.value=val3;
}
window.close();
}

function validate()
{
if(t1())
{
alert("Please enter the Name");
return false;
}
 else
{
return true;
}

   }
function t1()
{
if(document.Member_Find.name.value=="")
{
return true;
}
else
{
return false;
}
}
function focuss(){
document.Member_Find.name.focus();
}
function search(){
document.Member_Find.submit();
}
</script>
