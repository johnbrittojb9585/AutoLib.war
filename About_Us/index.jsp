<!DOCTYPE html>
<%
String URole=session.getAttribute("UserRights").toString().trim();
if(URole.equalsIgnoreCase("4") || URole.equalsIgnoreCase("5") || URole.equalsIgnoreCase("6") || URole.equalsIgnoreCase("7"))
{		
	response.sendRedirect("sessiontimeout.jsp");
}	
%>

<%@ include file="/Tree/demoFrameset.jsp"%>
<%@ page language="java" session="true" buffer="12kb" import="Common.Security"%>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/button_css.css" />



<%
//
//   Filename: Index.jsp
//   Form name:Department
//%>

<html>
<head>
<meta charset="ISO-8859-1">
 
  <title>AutoLib</title>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/jquery-ui.css"/>
<%-- <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/jquery-ui-base.css"/> --%>
<%-- <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/jquery-ui-darkness.css"/> --%>
<%-- <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/jquery-ui-flick.css"/> --%>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/style1.css"/>
<link rel="stylesheet" type="text/css" href="/AutoLib/style.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-2.2.4.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-ui.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/searchDeptMas.js"></script>


</head>

<body background="/AutoLib/soft.jpg" onload="load()">

<form name=Department method="post" action=./DepartmentServlet>
<br><br>
<!-- <div class="header"> -->
   <h2 align="center">About</h2>
<!-- </div> -->
<center>
<table align="center" class="contentTable" width="60%">

<tr>
<td> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img src="/AutoLib/images/AutoLibFinLogo.bmp" width="70" height="70" border="0">&nbsp;</td>
<td>	
<h4 style="line-height: 17px;">	<p style="font-style: italic;"> 
This software license issued to  : </p><p><strong style="color:#472183; font-size: 15px;">Panimalar College Of Nursing </strong></p>	
Version  :&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; AutoLib - College Library Software - Advanced Edition &nbsp;&nbsp;   V.22.01<br>
Installed  :&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Mar 2022<br>
Developed by  : &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; AutoLib Software Systems, Chennai - 600125<br>
Technical Support  :&nbsp;   Tel - 044-2252 2448   E-mail: autolibsoftware@gmail.com<br>
Web  : &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="http://autolib-india.net/" target="_blank"> autolib-india.net</a></h4></td>

</tr>
</h4>
</table>
</center>
</form>
</body>
</html>

