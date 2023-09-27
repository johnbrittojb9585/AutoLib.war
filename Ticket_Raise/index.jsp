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
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/Tree/autolib.css" />



<%
//   @author  john.A
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

<form name="Author" method="post" action=./AuthorServlet>
<br><br><br>
 <P ALIGN="left"><BR>
 <h2>Ticket&nbsp;Raise </h2>

<table align="center" width="90%">
<tr><td> &nbsp; </td></tr>
<!--<tr><td Class="addedit">Author&nbsp;Code<td><input type=text name=code size=20  maxlength=5 onKeyUp="return author_code_val();"  >-->
<!--<input type=button name=find Class="submitButton" Value="Find" onclick="FindAuthor();"></td></tr>-->


<table border="0" cellpadding="5" cellspacing="0" width="100%" height="100">
	<tr>
		<td width="100%">
			<table cellpadding="1" cellspacing="3" WIDTH="60%" align="center">
			<tr><td>
				<div class="info"> 
					If You Have any Quries or doubts in Autolib Software
					<a href="http://smallcart.in:9009" style="color: red; text-decoration: underline;" target="_blank"> Click Here </a> to raise ticket.in AutoLib Helpdesk Portal&nbsp;&nbsp;&nbsp;&nbsp;
				</div>
				</td></tr>
			</table>
		</td>
	</tr>
</table>

	
		
			
		
	</table>
	
	<table cellspacing="0" cellpadding="2" id="noteStruct">
	<tr> <td id="noteHead"> Notes: </td> </tr> 
		<tr id="noteBody"> 
			<td> 
				<ul> 
					<li><strong style="color:red"> Having trouble in Raise Ticket? Send E-Mail to autolibsoftware@gmail.com</strong></li>
					<br>
					<li><strong style="color:red">Before raise ticket note you are in AMC</strong></li>
				</ul> 
			</td> 
		</tr> 
	</table>

</table>
</form>
</body>
</html>

