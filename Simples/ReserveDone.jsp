<%@ include file="/Tree/demoFrameset.jsp"%>
<%@ page language="java" errorPage="/Error/ErrorPage.jsp"  import="java.util.*"  session="true" buffer="12kb" import="Common.Security,Common.Security_Counter"%>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/button_css.css" />


<jsp:useBean id="bean" scope="request" class="Lib.Auto.Account.AccountBean"/>

<html>
<%
   response.setHeader("Pragma", "No-cache");
   response.setHeader("Cache-Control", "no-cache");
   response.setDateHeader("Expires", 0);
%>
<head>
<title>AutoLib Software Systems</title>
<meta http-equiv="pragma" content="no-cache"/>
<link rel="stylesheet" type="text/css" href="/AutoLib/style.css">


</head>
<body>
<script language = "Javascript">
window.history.forward(0)
</script>
<form name="Reserve" method="post" action=./AccountServlet>
<br><br><br>

 <P ALIGN="left"><BR>
 <h2>  </h2>
 <center>
   <h2> Reservation&nbsp;Status  </h2>
<table align="center" class="contentTable" width="80%">
<td>

<table align="center" width="90%">
<tr><td> &nbsp; </td></tr>
<tr>

<%

String strrply=bean.getauthor();
if(strrply!=null){
	out.println("<center>");
    out.println("<tr>");
	out.print("<td Class='addedit'>User ID:&nbsp;&nbsp;&nbsp;");
   	out.println(""+bean.getuid());
   	out.print("</td>");
   	out.print("<td></td>");
   	
   	out.print("<td Class='addedit'>User Name:&nbsp;&nbsp;&nbsp;");
   	out.println(""+bean.getuname());
   	out.print("</td>");
   	out.println("</tr>");
   	out.println("<tr>");
   	out.println("<td><br></td>");
   	out.println("</tr>");
   	out.println("<tr>");
   	out.print("<td Class='addedit'>Access No:&nbsp;&nbsp;&nbsp;");
   	out.println(""+bean.getaccno());
   	out.print("</td>");  	
   	out.print("<td></td>");
   	
   	out.print("<td Class='addedit'>Title:&nbsp;&nbsp;&nbsp;");
   	out.println(""+bean.gettitle());
   	out.print("</td>");  	
   	out.println("</tr>");    
   	out.println("<tr>");
   	out.println("<td><br></td>");
   	out.println("</tr>");
   	
   	out.println("<tr>");
   	out.print("<td></td>");   	
   	out.print("<td Class='addedit'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;This Resource has been Reserved.Your queue position is ");
   	out.println(""+bean.getavailability());
   	out.print("</td>");  	
   	out.println("</tr>");
   	out.println("</tr>");
   	out.println("<tr>");
   	out.println("<td><br></td>");
   	out.println("</tr>");
   	out.println("</center>");
}
%>


<td>&nbsp;&nbsp;
</tr>
<tr>
<td></td>
<td align="right">
<CENTER>
<c:choose>
<c:when test="${UserRights eq 7}">
<a href="<%= request.getContextPath() %>/Home/HomeServlet" id="bodysearch">
<img src="<%= request.getContextPath() %>/iconImages/back.png" width="50" height="50" border="0" align="middle" title="Click here to go home.">
</a>
</c:when>
<c:otherwise>
<input type=button name=New Class="submitButton"  Value=Back onclick="SaveRec();">
</c:otherwise>
</c:choose>
</CENTER>

</td></tr>
</table>
</td><tr><td> &nbsp; </td></tr>
</table>
</form>
</body>
</html>

<!--
////////////////////////////////////////////////JAVA SERVER PAGES SCRIPT ///////////////////////////////////////////////// --> 

<script language="javascript">
function SaveRec() {
  
  location.href="<%=request.getContextPath()%>/Account/index.jsp";

}
</script>

