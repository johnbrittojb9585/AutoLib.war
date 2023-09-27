<%@ page language="java" session="true" buffer="12kb" import="java.sql.*" import="java.util.*"%>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/button_css.css" />
<jsp:useBean id="bean" scope="request" class="Lib.Auto.Journal_Issues.JnlIssBean"/>
<html>
<head>
<title>AutoLib</title>
<meta http-equiv="pragma" content="no-cache"/>
<link rel="stylesheet" type="text/css" href="/AutoLib/style.css">
</head>
<body background="/AutoLib/soft.jpg" onload="load()" >
<form name=dept method=post action="./JnlIssServlet" ><!--onsubmit="return validate()" -->

<%

String flag=request.getParameter("find_flag");
String SQLstr="";
String Title="";
String t1="",t2="",t3="";
String nm=request.getParameter("name");
if (flag!=null){

if(flag.equals("JnlName"))
	{
	Title="Journal Name   ";t1="Jnl_Code";t2="Jnl_Type";t3="language";

	%>
	<script>document.dept.flag.value="<%=flag%>";</script>
	<%
	}
}

%>
<!--
//////////////////////////////////////FORM CONTROLS//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// -->
<h2 > Journal Issue</h2>
<div align=right> <A href=""  onclick="window.close()">Back</A></div>
<center><%=Title%><input type=text name=name>
<input type=submit Class="submitButton" value="Search" ></center>
<input type=hidden name=flag value="<%=flag%>">
</form>
</html>
<!--
//////////////////////////////////////JAVA SERVER PAGES SCRIPT /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// -->
<%
String ck=request.getParameter("check");
ArrayList sc=new ArrayList();
try{
if(ck!=null){

		sc=(ArrayList)bean.getAl();
		%>
  <script language="JavaScript">
document.dept.name.value="<%=request.getParameter("nam")%>";
document.dept.name.focus();
</script>
<%
		out.print("<table border=1 bordercolor=#008000 align=center cellspacing=0 >");
		out.print("<tr><th>"+t1+"</th><th>"+Title+"</th><th>"+t2+"</th></tr>");

		for(int i=0; i<sc.size();i++){
  %>
		<tr onmouseover=this.style.color='red' onmouseout=this.style.color='black' onclick='show("<%=sc.get(i)%>","<%=sc.get(i+1)%>")'>
		<script language=javascript>
		 document.write("<td>"+"<%=sc.get(i)%>" +"</td>");
		 document.write("<td>"+"<%=sc.get(++i)%>" +"</td>");
		 document.write("<td>"+"&nbsp;<%=sc.get(++i)%>"+"</td>");
		 document.write("</tr>");
 		</script>
		<%

		}

	 }
 }catch (Exception e) {out.println(e.toString());}
   finally{
	sc.clear();
    }
%>
<script  language="javascript">
function show(val,val1){
window.opener.document.JnlIssues.jcode.value=val;
window.opener.document.JnlIssues.jname.value=val1;
 window.opener.document.JnlIssues.method="post";
 window.opener.document.JnlIssues.flag.value="supdate";
// window.opener.document.JnlIssues.flag.value="find";
// window.opener.document.JnlIssues.flagRadio.value="PENDING";
// window.opener.document.JnlIssues.action="./JnlIssServlet";
 window.opener.document.JnlIssues.submit();
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
function load()
{
document.dept.name.focus();
}
function search(){
document.dept.submit();
}
</script>
