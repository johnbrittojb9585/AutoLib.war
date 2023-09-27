<!DOCTYPE html>
<%
String URole=session.getAttribute("UserRights").toString().trim();
if(URole.equalsIgnoreCase("4") || URole.equalsIgnoreCase("5") || URole.equalsIgnoreCase("6") || URole.equalsIgnoreCase("7"))
{		
	response.sendRedirect("sessiontimeout.jsp");
}	
%>

<%@ include file="/Tree/demoFrameset.jsp"%>
<%@ page import="java.util.Properties"%>
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

   <h2 align="center">Backup Download</h2>
   
 
&nbsp;&nbsp;
<center>
<table align="center" class="contentTable" width="60%" height="50%">
<tr><td>&nbsp;</td></tr>
<tr>&nbsp;&nbsp;&nbsp;
<td style="text-align: center; "> 
				
				
					<a href="<%= request.getContextPath() %>/filePath/AutoLibRecent.sql" target=_base style="font-size:17px;color: red; text-align: center;"> click here to download recent backup file&nbsp;<img src="<%= request.getContextPath() %>/webcontent/birt/images/download.png" width="3%" height="3%"></a> 
					
<%-- 						<a href="<%=filePath%>AutoLibRecent.sql" target=_base style="font-size:17px;color: red; text-align: center;"> click to download recent backup file&nbsp;<img src="<%= request.getContextPath() %>/webcontent/birt/images/download.png" width="3%" height="3%"></a>
 --%>				
			</td>&nbsp;&nbsp;&nbsp; </tr>
			<tr><td>&nbsp;</td></tr>

</table>

<table cellspacing="0" cellpadding="2" id="noteStruct">
	<tr> <td id="noteHead">**Admin Notes: </td> </tr> 
		<tr id="noteBody"> 
			<td> 
				<ul> 
				<li><strong style="color:blue"> **Copy the Backup File to Pendrive or Another Secondary Storage Device**</strong></li>
					<li><strong style="color:blue">**Data Backup Having Maintaining On Your Side Only** </strong></li>
					<li><strong style="color:blue">**If You Loss The Backup AutoLib Not Be Responseble You Have Kept Carefully!! **</strong></li>
				</ul> 
			</td> 
		</tr> 
	</table>
</center>
</form>
</body>
</html>

