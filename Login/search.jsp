<%@ page language="java" errorPage="/Error/ErrorPage.jsp" session="true" buffer="12kb" import="Common.Security" import="java.util.ArrayList"%>
<%//Security.checkSecurity(1,session,response,request);%>
<jsp:useBean id="bean" scope="request" class="Lib.Auto.Login.LoginBean"/>
<!--
//////////////////////////////////////DATABASE CONNECTION//////////////////////////////////////////////////////////////////////// -->
<%
//
//   Filename: Search.jsp
//   Form name:Course_Find
//%>
<!--
//////////////////////////////////////FORM CONTROLS//////////////////////////////////////////////////////////////////////// -->
<html>
<head>
<title>AutoLib</title>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/button_css.css" />
<link rel="stylesheet" type="text/css" href="/AutoLib/style.css">
<%
   response.setHeader("Pragma", "No-cache");
   response.setHeader("Cache-Control", "no-cache");
   response.setDateHeader("Expires", 0);
%>
</head>
<body background="/AutoLib/soft.jpg" onload=focuss()>
<form name="Login_Find" method="post" action=./LoginServlet ><!--onsubmit="return validate()"-->
<input type=hidden name=flag value="Login">
<!--
//////////////////////////////////////JAVA SERVER PAGES SCRIPT ///////////////////////////////////////////////// -->

<!--
//////////////////////////////////////FORM CONTROLS//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// -->
<h2>Login&nbsp;Search</h2>
<div><A href=""  onclick="window.close()">Back</A></div>
<center>Login&nbsp;Id&nbsp;<input type=text name=name>&nbsp;<input type=submit Class="submitButton" value="Search" ></center></form>
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
		%>
		<script language="javascript">
		document.Login_Find.name.value="<%=request.getParameter("nam")%>";
		document.Login_Find.name.focus();
		</script>
		<%
		out.print("<table border=1 bordercolor=#008000 align=center cellspacing=0 >");
		out.print("<tr><th>Login Id<th>Login Name<th>User Rights</tr>");
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
window.opener.document.Login.code.value=val;
window.opener.document.Login.method="get";
window.opener.document.Login.flag.value="search";
window.opener.document.Login.action="./LoginServlet";
window.opener.document.Login.submit();
window.close();
}

function validate()
{
if(t1())
{
alert("Please enter the Login Id");
return false;
}
 else
{
return true;
}      

   }
function t1()
{
if(document.Login_Find.name.value=="")
{
return true;
}
else
{
return false;
}
}
function focuss(){

document.Login_Find.name.value="";
document.Login_Find.name.focus();
}
function search(){
document.Login_Find.submit();
}

</script>
<!--
//////////////////////////////////////JAVA SCRIPT USEING CLIENT SIDE///////////////////////////////////////////////// --> 
