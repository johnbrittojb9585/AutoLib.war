<%@ page language="java" session="true" buffer="12kb" import="java.sql.*" import="java.util.*"%>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/button_css.css" />
<jsp:useBean id="bean" scope="request" class="Lib.Auto.Journal.journalbean"/>

<!--
//////////////////////////////////////DATABASE CONNECTION//////////////////////////////////////////////////////////////////////// -->

<%
//
//   Filename: Search.jsp
//   Form name:journal_Find
//%>
<html>
<head>
<title>AutoLib</title>
<meta http-equiv="pragma" content="no-cache"/>
<link rel="stylesheet" type="text/css" href="/AutoLib/style.css">
</head>
<body onload="focuss()" background="/AutoLib/soft.jpg" >
<form name=dept method=post action=./JournalServlet ><!--onsubmit="return validate()"-->

<%
String flag=request.getParameter("flag");

String SQLstr="";
String Title="";
String t1="",t2="",t3="";

if (flag!=null){
if (flag.equals("Dept")){
Title="Department Name";t1="Dept_Code";t2="Dept_Name";t3="Short_Desc";
}
else if (flag.equals("Sub")){
Title="Subject Name";t1="Sub_Code";t2="Sub_Name";t3="Short_Desc";
}
else if (flag.equals("Pub")){
Title="Publisher Name";t1="SP_Code";t2="SP_Name";t3="Short_Desc";
}
else if (flag.equals("Nam")){
Title="Journal Name";t1="jnl_code";t2="Type";t3="frequency";
}
}

%>
<!--
//////////////////////////////////////FORM CONTROLS//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// -->
 <h2> Journal Master Search</h2>
<div align=right><A href=""  onclick="window.close()">Back</A></div>
<center><%=Title%><input type=text name=name>
<input Class="submitButton" type=submit value="Search" ></center>
<input type=hidden name=flag value="<%=flag%>">
</form>
</body>


</html>
<!--
//////////////////////////////////////JAVA SERVER PAGES SCRIPT /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// -->
<%
String ck=request.getParameter("check");
ArrayList sc=new ArrayList();
try{
if(ck!=null){
	int j=1;
%>
  <script language="JavaScript">
document.dept.name.value="<%=request.getParameter("nam")%>";
document.dept.name.focus();
</script>
<%
if (flag.equals("Nam")){
		sc=(ArrayList)bean.getAl();

		out.print("<table border=1 bordercolor=#008000 align=center cellspacing=0 >");
		out.print("<tr><th>"+t1+"</th><th>"+Title+"</th><th>"+t2+"</th></tr>");

		for(int i=0; i<sc.size();i++){
  %>
		<tr onmouseover=this.style.color='red' onmouseout=this.style.color='black' onclick='show("<%=sc.get(i)%>")'>
		<script language=javascript>
		 document.write("<td>"+"<%=sc.get(i)%>" +"</td>");
		 document.write("<td>"+"<%=sc.get(++i)%>" +"</td>");
		 document.write("<td>"+"&nbsp;<%=sc.get(++i)%>"+"</td>");
		 document.write("</tr>");
 		</script>
		<%

		}
		}
		else
		{
		sc=(ArrayList)bean.getAl();
		out.print("<table border=1 bordercolor=#008000 align=center cellspacing=0 >");
		out.print("<tr><th>S.No</th><th>"+Title+"</th><th>"+t2+"</th></tr>");

		for(int i=0; i<sc.size();i++){
  %>
		<tr onmouseover=this.style.color='red' onmouseout=this.style.color='black' onclick='show1("<%=sc.get(i+1)%>")'>
		<script language=javascript>
		 //document.write("<td>"+"<%=sc.get(i)%>" +"</td>");
		 document.write("<td>"+"<%=j++%>" +"</td>");		
		 document.write("<td>"+"<%=sc.get(++i)%>" +"</td>");
		 document.write("<td>"+"&nbsp;<%=sc.get(++i)%>"+"</td>");
		 document.write("</tr>");
 		</script>
		<%

		}



}
	 }
 }catch (Exception e) {out.println(e.toString());}
   finally{
	sc.clear();
    }
%>

 </script>

<script  language="javascript">
function show(val){

window.opener.document.jrl.jcode.value=val;
window.opener.document.jrl.method="post";
window.opener.document.jrl.flag.value="search";
window.opener.document.jrl.action="./JournalServlet";
window.opener.document.jrl.submit();

window.close();
}
function show1(val){

if(document.dept.flag.value=="Dept"){
window.opener.document.jrl.dname.value=val;
}
if(document.dept.flag.value=="Pub"){
window.opener.document.jrl.pname.value=val;
}
if(document.dept.flag.value=="Sub"){
window.opener.document.jrl.sname.value=val;
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
if(document.dept.name.value=="")
{
return true;
}
else
{
return false;
}
}

function focuss(){
document.dept.name.focus();
}
function search(){
document.dept.submit();
}

</script>
