<%
	String URole = session.getAttribute("UserRights").toString().trim();
	if (URole.equalsIgnoreCase("4") || URole.equalsIgnoreCase("5")	|| URole.equalsIgnoreCase("6")	|| URole.equalsIgnoreCase("7")) {
		response.sendRedirect("sessiontimeout.jsp");
	}
%>

<html>
<jsp:useBean id="beanobject" scope="request" class="Lib.Auto.RefDueDays.RefDaysBean" type="Lib.Auto.RefDueDays.RefDaysBean">
</jsp:useBean>


<%@ include file="/Tree/demoFrameset.jsp"%>
<%@ page language="java" session="true" buffer="12kb"%>

<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/button_css.css" />
<head>
<title>AutoLib</title>

<link rel="stylesheet" type="text/css" href="/AutoLib/style.css">
<%
	response.setHeader("Pragma", "No-cache");
	response.setHeader("Cache-Control", "no-cache");
	response.setDateHeader("Expires", 0);
%>

</head>
<body background="/AutoLib/soft.jpg">
	<form name=refduedays method="post" action=./RefDueDaysServlet>
		<br> <br> <br>
		<h2>Reference&nbsp;Due&nbsp;Date</h2>
		
		<center>
			<table align="center" class="contentTable" width="20%">
				<tr>
					<td>
						<table align="center" width="60%">
							<tr>
								<td>&nbsp;</td>
							</tr>

							<tr>
								<td Class="addedit">&nbsp;&nbsp;&nbsp;&nbsp;Days&nbsp;&nbsp;&nbsp;<input type="text" name="duedays"
									size=7 maxlength="3" onKeyUp="return ref_duedays();">
							<tr>
								<td>&nbsp;</td>
							</tr>

							<tr>
								<td colspan=2 align=center>

									<center>
										<input type=button name=save Class="submitButton" value=Save onclick=SaveRec()> 
									</center> <input type=hidden name=flag>
							<tr>
								<td>&nbsp;</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</center>
	</form>
</body>




<script language=javascript>
function home()
{
location.href="/AutoLib/Home.jsp";
}
function Logout()
{
location.href="/AutoLib/index.html";
}

function SaveRec(){
	if(!document.refduedays.duedays.value=="")
	{
		document.refduedays.flag.value="save";
		document.refduedays.submit();		
	}else 
	{
		alert("Please Enter Vaild Days !");
		document.refduedays.duedays.focus();
	}
	
}

function ref_duedays() {
if((isNaN(document.refduedays.duedays.value))||(document.refduedays.duedays.value == ' ')) {
document.refduedays.duedays.select();
document.refduedays.duedays.value="";
}

}
</script>
<%
	String valid = request.getParameter("check");
	if (valid != null) {
		if (request.getParameter("check") != null) {
			if (valid.equals("display")) {
%>

<script language="JavaScript">	
document.refduedays.duedays.value="<%=beanobject.getDue_days()%>";
</script>

<%
	}
	if (valid.equals("Saveduedays")) {
		int count = beanobject.getResult();
		
		if(count > 0)
		{
			%>
			<script language="JavaScript">
					
					alert("Record Added/Updated Successfully");
					document.refduedays.flag.value="load";
					document.refduedays.submit();						
					</script>

			<%
		}
		else {
			
			%>
			<script language="JavaScript">					
					alert("Updation Failed !");
			</script>

			<%
		}
	}
		}
	}
%>


</html>
