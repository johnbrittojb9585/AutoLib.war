<%@page import="Common.Security_Counter"%>
<%@ include file="/Tree/demoFrameset.jsp"%>
<%@ page language="java" errorPage="/Error/ErrorPage.jsp" session="true"
	buffer="12kb" import="java.sql.*,java.util.*"%>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/button_css.css" />
<%@ page import="java.util.Calendar"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%-- <%@ page import="Lib.Auto.Issue_Mas_Update.IssueMasUpdatebean"%> --%>
<jsp:useBean id="bean" scope="request" class="Lib.Auto.Issue_Mas_Update.IssueMasUpdatebean"  type="Lib.Auto.Issue_Mas_Update.IssueMasUpdatebean" />


<!--
//////////////////////////////////////////////////////////////////FORM CONTROLS//////////////////////////////////////////////////////////////////////// -->
<html>
<head>
<title>Auto Lib</title>
<script language="javascript" src="/AutoLib/popcalendar.js"></script>
<link rel="stylesheet" type="text/css" href="/AutoLib/style.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/jquery-ui.css" />
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jquery-3.1.0.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jquery-ui.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/datepicker.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/datepicker2.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/datepicker3.js"></script>
	
<meta http-equiv="pragma" content="no-cache" />
</head>
<body background="/AutoLib/soft.jpg">
	<%
		java.util.Date d = new java.util.Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		String dateString = sdf.format(d);
		String Message = null;
	%>



	<form name="issue_mas_update" method="Post" action=./IssueMasUpdateServlet>

		<br> <br> <br>

		<h2>Due Date&nbsp;Updation</h2>
		<div align="center">
		<table align="center" class="contentTable" width="40%">
		<tr>
		<td>
		<table align="center" width=80%">
		<tr>
		<td>&nbsp;</td>						
		</tr>					

		  <tr>				
				<td Class="addedit">Due From</td>
				<td>
				<INPUT type="text" name=fromdt size=11 id="datepicker" value="<%=dateString%>">					
				
				</td>					
				
				<td Class="addedit">Due To</td>					
                <td>
				<INPUT type="text" name=todt size=11 id="datepicker2" value="<%=dateString%>">				
                </td>
			</tr>
				<tr></tr><tr></tr><tr></tr>
			<tr>
			<td Class="addedit">Updated Date</td>
				<td>
				<INPUT type="text" name=updt size=11 id="datepicker3" value="<%=dateString%>">					
				</td>	
			</tr>				
								
			<tr>			
        <td colspan="5">
        <p align="center">
		<input type="button" Class="submitButton" value="Update" name="Update" onclick="update_data()">						
		<input  type="reset" Class="submitButton" value="Clear" name="Clear" onClick="refreshPage()"> 							
		</p></td></tr>				
																	
							
	</table></td></tr>
	<input type="hidden" name="flag">
	</table>
	</div>				  
	
<%
String valid=request.getParameter("check");
if(valid!=null){
	if (valid.equals("updated")) {
		%>
		<script>
	alert("Records Updated Successfully!!!..");
	</script>
		<%
			}
	if (valid.equals("updateCheck")) {
		
		
		String updateCount=String.valueOf(request.getAttribute("updateCount"));
		%>
		<script>
		document.issue_mas_update.fromdt.value="<%=Security_Counter.getdate(bean.getFromdt())%>";
		document.issue_mas_update.todt.value="<%=Security_Counter.getdate(bean.getTodt())%>";
		document.issue_mas_update.updt.value="<%=Security_Counter.getdate(bean.getUpdt())%>";
		
		var msg = <%=updateCount%>+" records are going to Update \n Are you Sure want to Update ?";
	
		if(confirm(msg))
		{
		document.issue_mas_update.flag.value="updateConfirm";
		document.issue_mas_update.submit();
		}	
		</script>
		<%
			}
	if (valid.equals("NoData")) 
	{
		%>
		<script>
	alert("No Data Found!!..");
	</script>

		<%
			}
}
%>

<script language="javascript">
function update_data()
{
	msg=confirm("Do you want to update Records!!");
	if(msg)
{
	document.issue_mas_update.flag.value="update";
	document.issue_mas_update.submit();
}
	else
{
		location.href="./index.jsp";
}
}

function refreshPage()
{
   window.location.reload();
}


</script>
</form>
</body>
</html>
