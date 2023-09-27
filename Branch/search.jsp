<%@ page language="java" session="true" buffer="12kb" import="Common.Security" import="java.sql.*" import="java.util.*"%>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/button_css.css" />
<%//Security.checkSecurity(1,session,response,request);%>
<jsp:useBean id="bean" scope="page" class="Lib.Auto.Branch.BranchBean"/>
<!--
//////////////////////////////////////DATABASE CONNECTION//////////////////////////////////////////////////////////////////////// -->
<%
//
//   Filename: Search.jsp
//   Form name:Branch_Find
//%>
<!--
//////////////////////////////////////FORM CONTROLS//////////////////////////////////////////////////////////////////////// -->
<html>
<head>
<title>AutoLib</title>
<meta http-equiv="pragma" content="no-cache"/>
<link rel="stylesheet" type="text/css" href="/AutoLib/style.css">
<%
   response.setHeader("Pragma", "No-cache");
   response.setHeader("Cache-Control", "no-cache");
   response.setDateHeader("Expires", 0);
  
%>
</head>
<body background="/AutoLib/soft.jpg" onload="focuss()">
<form name="Branch_Find" method="post" action=./BranchServlet ><!--onsubmit="return validate()" -->

<!--
//////////////////////////////////////JAVA SERVER PAGES SCRIPT ///////////////////////////////////////////////// -->

<!--
//////////////////////////////////////FORM CONTROLS//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// -->
<h2>Branch&nbsp;Search</h2>
<div><A href=""  onclick="window.close()">Back</A></div>
<center>Branch&nbsp;Name<input type=text name=name>
<input type=submit Class="submitButton" value="Search" >
<input type=hidden name=flag  value="Branch">
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
	
	int j=1;
	sc=(ArrayList)request.getAttribute("serarch");
				  %>
  
<%
		out.print("<table border=1 bordercolor=#008000 align=center cellspacing=0 >");
		out.print("<tr><th>S.No<th>Branch Name<th>Short Desc</tr>");
		for(int i=0; i<sc.size();i++){
		%>
		<tr onmouseover=this.style.color='red' onmouseout=this.style.color='black' onclick='show("<%=sc.get(i)%>")'>
		<script language=javascript>
		 //document.write("<td>"+"<%=sc.get(i)%>" +"</td>");
		 document.write("<td>"+"<%=j++%>" +"</td>");				 
		 document.write("<td>"+"<%=sc.get(++i)%>" +"</td>");
		 document.write("<td>&nbsp;"+"<%=sc.get(++i)%>"+"</td>");
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
window.opener.document.Branch.code.value=val;
window.opener.document.Branch.method="post";
window.opener.document.Branch.flag.value="search";
window.opener.document.Branch.action="./BranchServlet";
window.opener.document.Branch.submit();
window.close();
}
function focuss(){
document.Branch_Find.name.focus();
}

function validate()
{
if(t1())
{
alert("Please enter the BranchName");
return false;
}
 else
{
return true;
}

   }

   function t1()
{
if(document.Branch_Find.name.value=="")
{
return true;
}
else
{
return false;
}
}
function search(){
document.Branch_Find.submit();
}


</script>
<!--
//////////////////////////////////////JAVA SCRIPT USEING CLIENT SIDE///////////////////////////////////////////////// --> 
