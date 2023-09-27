<%@ page language="java" session="true" buffer="12kb" import="java.sql.*" import="java.util.*"%>
<jsp:useBean id="bean" scope="request" class="Lib.Auto.Jnl_Approval.Jnlapprovebean"/>
<html>
<head>
<title>AutoLib</title>
<meta http-equiv="pragma" content="no-cache"/>
<link rel="stylesheet" type="text/css" href="/AutoLib/style.css">
</head>
<body background="/AutoLib/soft.jpg" onload="load()" >
<form name=approval method=post action="/AutoLib/Jnl_Approval/JournalAppSearch" onsubmit="return validate()">

<%

String flag=request.getParameter("flag");

String SQLstr="";
String Title="";
String t1="",t2="",t3="";
String nm=request.getParameter("name");
if (flag!=null){

if(flag.equals("Journal"))
	{
	Title="Journal Name   ";t1="Jnl_Code";t2="Jnl_Type";t3="language";

	%>
	<script>document.approval.sflag.value="<%=flag%>";</script>
	<%
	}
	if(flag.equals("Dept"))
	{
	Title="Department Name   ";t1="Dept. Code";t2="Dept. Name";t3="Short Desc.";

	%>
	<script>document.approval.sflag.value="<%=flag%>";</script>
	<%
	}
}

%>
<!--
//////////////////////////////////////FORM CONTROLS//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// -->
<h2 > Journal Search</h2>
<div align=right> <A href=""  onclick="window.close()">Back</A></div>
<center><%=Title%><input type=text name=name><input type=submit value="Search" ></center>
<input type=hidden name=sflag value="<%=flag%>">
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
		out.print("<table border=1 bordercolor=#008000 align=center cellspacing=0 >");
		out.print("<tr><th>"+t1+"</th><th>"+Title+"</th><th>"+t2+"</th></tr>");

		for(int i=0; i<sc.size();i++){
  %>
		<tr onmouseover=this.style.color='red' onmouseout=this.style.color='black' onclick='show("<%=sc.get(i+1)%>")'>
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
function show(val){
if(document.approval.sflag.value=="Journal"){
window.opener.document.Jnl_Approve.journal.value=val;
}
if(document.approval.sflag.value=="Dept"){
window.opener.document.Jnl_Approve.dept.value=val;
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
if(document.approval.name.value=="")
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
document.approval.name.focus();
}
</script>
