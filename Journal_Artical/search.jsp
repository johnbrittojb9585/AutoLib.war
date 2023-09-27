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
<form name=dept method=post action=./JournalArtServlet ><!--onsubmit="return validate()"-->

<%
String flag=request.getParameter("flag");
String flag2=request.getParameter("flag2");
String SQLstr="";
String Title="";
String t1="",t2="",t3="",t4="",t5="";

if (flag!=null){

if (flag.equals("Sub")){
Title="Subject Name";t1="Sub_Code";t2="Sub_Name";t3="Short_Desc";
}
else if (flag.equals("Atl")){
Title="Article Title ";t1="Artical No";t2="Artical Author";t3="Author";
}
else if (flag.equals("Nam")){
Title="Journal Name";t1="jnl_code";t2="Type";t3="frequency";
}
else if (flag.equals("Iss")){
Title="Issue Acc.No.";t1="Issue Acc.No.";t2="Issue No.";t3="Volume";t4="Month";t5="Year";
}
}

%>
<!--
//////////////////////////////////////FORM CONTROLS//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// -->
 <h2> Journal Master Search</h2>
<div align=right><A href=""  onclick="window.close()">Back</A></div>
<center><%=Title%><input type=text name=name>
<input type=submit Class="submitButton" value="Search" ></center>
<input type=hidden name=flag value="<%=flag%>">
<input type=hidden name=flag2 value="<%=flag2%>">
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
		if (flag.equals("Atl")){
		sc=(ArrayList)bean.getAl();
		
		out.print("<table border=1 bordercolor=#008000 align=center cellspacing=0 >");
		out.print("<tr><th>"+t1+"</th><th>"+Title+"</th><th>"+t2+"</th></tr>");

		for(int i=0; i<sc.size();i++){
  %>
		<tr onmouseover=this.style.color='red' onmouseout=this.style.color='black' onclick='show2("<%=sc.get(i)%>")'>
		<script language=javascript>
		 document.write("<td>"+"<%=sc.get(i)%>" +"</td>");
		 document.write("<td>"+"<%=sc.get(++i)%>" +"</td>");
		 document.write("<td>"+"&nbsp;<%=sc.get(++i)%>"+"</td>");
		 document.write("</tr>");
 		</script>
		<%
		}
		}
		if (flag.equals("Sub"))
		{
		sc=(ArrayList)bean.getAl();
		out.print("<table border=1 bordercolor=#008000 align=center cellspacing=0 >");
		out.print("<tr><th>"+t1+"</th><th>"+t2+"</th><th>"+t3+"</th></tr>");

		for(int i=0; i<sc.size();i++){
  %>
		<tr onmouseover=this.style.color='red' onmouseout=this.style.color='black' onclick='show1("<%=sc.get(i+1)%>")'>
		<script language=javascript>
		 document.write("<td>"+"<%=sc.get(i)%>" +"</td>");
		 document.write("<td>"+"<%=sc.get(++i)%>" +"</td>");
		 document.write("<td>"+"&nbsp;<%=sc.get(++i)%>"+"</td>");
		 document.write("</tr>");
 		</script>
		<%

		}



}
		if (flag.equals("Iss"))
		{
		sc=(ArrayList)bean.getAl();
		out.print("<table border=1 bordercolor=#008000 align=center cellspacing=0 >");
		out.print("<tr><th>"+t1+"</th><th>"+t2+"</th><th>"+t3+"</th><th>"+t4+"</th><th>"+t5+"</th></tr>");

		for(int i=0; i<sc.size();i++){
  %>
		<tr onmouseover=this.style.color='red' onmouseout=this.style.color='black' onclick='show3("<%=sc.get(i)%>","<%=sc.get(i+1)%>","<%=sc.get(i+2)%>","<%=sc.get(i+3)%>","<%=sc.get(i+4)%>")'>
		<script language=javascript>
		 document.write("<td>"+"<%=sc.get(i)%>" +"</td>");
		 document.write("<td>"+"<%=sc.get(++i)%>" +"</td>");
		 document.write("<td>"+"&nbsp;<%=sc.get(++i)%>"+"</td>");
		 document.write("<td>"+"&nbsp;<%=sc.get(++i)%>"+"</td>");
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
function show2(val){

window.opener.document.jrl.atlno.value=val;
window.opener.document.jrl.method="post";
window.opener.document.jrl.flag.value="searchAtl";
window.opener.document.jrl.action="./JournalArtServlet";
window.opener.document.jrl.submit();

window.close();
}
function show(val){

window.opener.document.jrl.jcode.value=val;
window.opener.document.jrl.method="post";
window.opener.document.jrl.flag.value="search";
window.opener.document.jrl.action="./JournalArtServlet";
window.opener.document.jrl.submit();

window.close();
}
function show1(val){

if(document.dept.flag.value=="Sub"){
window.opener.document.jrl.subject.value=val;
}
window.close();
}
function show3(val,val2,val3,val4,val5){

	if(document.dept.flag.value=="Iss"){
	window.opener.document.jrl.issueaccessno.value=val;
	window.opener.document.jrl.issueno.value=val2;
	window.opener.document.jrl.jvolno.value=val3;
	window.opener.document.jrl.month.value=val4;
	window.opener.document.jrl.year.value=val5;
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
function search(){
document.dept.submit();
}

function focuss(){
document.dept.name.focus();
}

</script>
