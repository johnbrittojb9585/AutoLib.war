<%@page import="Common.RecentBackup"%>
<%
String URole=session.getAttribute("UserRights").toString().trim();
if(URole.equalsIgnoreCase("2") || URole.equalsIgnoreCase("3") || URole.equalsIgnoreCase("4") || URole.equalsIgnoreCase("5") || URole.equalsIgnoreCase("6") || URole.equalsIgnoreCase("7"))
{		
	response.sendRedirect("sessiontimeout.jsp");
}	
%>
<%@ include file="/Tree/demoFrameset.jsp"%>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/font.css" />

<%@ page language="java"  session="true" buffer="12kb" import="java.io.BufferedReader" import="Common.Security"%>
<%@ page language="java"  session="true" buffer="12kb" import="java.io.File" %>
<%@ page language="java"  session="true" buffer="12kb" import="java.io.FileWriter" %>
<%@ page language="java"  session="true" buffer="12kb" import="java.io.InputStreamReader" %>
<%@ page language="java"  session="true" buffer="12kb" import="java.util.Date" %>
<%@ page language="java"  session="true" buffer="12kb" import="java.util.Properties" %>

<html>
<body>

	<% 
	 String valid = "";
	 valid = request.getParameter("message"); %>

	
<table width="50%" valign="middle" align="center" height="400" border="0">
<td>

<%
if(valid.equals("success")) 
{
%>
  <div class="icon-ok" width="20%">  
    Backup Completed !
   <a href="<%= request.getContextPath() %>/Backup/backup_download.jsp" ><img src="<%= request.getContextPath() %>/images/arrow.png" width="3%" height="3%" border="0" title="Click here to go Download Page"> </a>
  </div>
  
</table>

	<table cellspacing="0" cellpadding="2" id="noteStruct">
	<tr> <td id="noteHead"> Important Notes: </td> </tr> 
		<tr id="noteBody"> 
			<td> 
				<ul> 
					<li><strong style="color:red">To Check Backup File go to Autolib_Files--> Manual_Backup</strong></li>					
					<li><strong style="color:red">The File Size should not reduce </strong></li>					
					<li><strong style="color:red">If It Reduce Please Contact AutoLib Team</strong></li>					
					<li><strong style="color:red">Copy One Recent Backup File to other system </strong></li>
				</ul> 
			</td> 
		</tr> 
	</table>
<%
}else if(valid.equals("failed"))  {
%>
  <div style="color:red; "    class="icon-ok" width="20%">
    Error Occurred !
  </div>
  


</td>

<table  cellspacing="0" cellpadding="2" id="noteStruct" >
	<tr> <td id="noteHead"> Important Notes: </td> </tr> 
		<tr id="noteBody"> 
			<td> 
				<ul> 
				<li><strong style="color:red">Please Check Storage on the Drive</strong></li>
					<li><strong style="color:red">Or Contact technical team </strong></li>
				</ul> 
			</td> 
		</tr> 
	</table>
	<%} %>
</body>
</html>