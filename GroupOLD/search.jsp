<%@ page language="java" session="true" buffer="12kb" import="java.sql.*" import="java.util.*"%>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/button_css.css" />
<jsp:useBean id="bean" scope="request" class="Lib.Auto.Group.GroupBean"/>
<!--
//////////////////////////////////////DATABASE CONNECTION//////////////////////////////////////////////////////////////////////// -->
<%
//
//   Filename: Search.jsp
//   Form name:Grp_Find
//%>
<!--
//////////////////////////////////////FORM CONTROLS//////////////////////////////////////////////////////////////////////// -->
<html>
<head>
<title>AutoLib</title>
<meta http-equiv="pragma" content="no-cache"/>
</head>
<link rel="stylesheet" type="text/css" href="/AutoLib/style.css">
<body onload="focuss()" background="/AutoLib/soft.jpg">
<form name="Grp_Find" method="post" action=./GroupServlet ><!--onsubmit="return validate()"-->


<%
String flag=request.getParameter("flag");
String mainTitle="";
String Title="";
String t1="",t2="",t3="";
if (flag!=null){

if (flag.equals("Gen")){
mainTitle="General "; Title="Group Id";t1="Group Name";t2="Remarks";
}
else if (flag.equals("Spe")){
mainTitle="Specified";Title="Group Id";t1="Group Name";t2="Remarks";
}
else if (flag.equals("Gs")){
mainTitle="General&Specified";Title="Group Id";t1="Group Name";t2="Remarks";
}
}
%>
<!--
//////////////////////////////////////FORM CONTROLS//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// -->
<h2><%=mainTitle%> Search</h2>
<div><A href=""  onclick="window.close()">Back</A></div>
<center><%=t1%><input type=text name=name>
<input type=submit Class="submitButton" value="Search" ></center>
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
		sc=(ArrayList)bean.getAl();
		out.print("<table border=1 bordercolor=#008000 align=center cellspacing=0 >");
		out.print("<tr><th>"+Title+"</th><th>"+t1+"</th><th>"+t2+"</th></tr>");
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
 }catch (Exception e) {out.println(e.toString());}
   finally{
    sc.clear();
    }
%>
<script  language="javascript">
function show(val){
window.opener.document.Grp.GCode.value=val;
window.opener.document.Grp.method="get";
window.opener.document.Grp.flag.value="search";
window.opener.document.Grp.action="./GroupServlet";
window.opener.document.Grp.submit();
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
if(document.Grp_Find.name.value=="")
{
return true;
}
else
{
return false;
}
}
function focuss(){
document.Grp_Find.name.focus();
}
</script>

