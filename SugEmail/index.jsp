<%
	String URole = session.getAttribute("UserRights").toString().trim();
	if (URole.equalsIgnoreCase("4") || URole.equalsIgnoreCase("5")	|| URole.equalsIgnoreCase("6")	|| URole.equalsIgnoreCase("7")) {
		response.sendRedirect("sessiontimeout.jsp");
	}
%>

<html>
<jsp:useBean id="beanobject" scope="request" class="Lib.Auto.SugEmail.SugmailBean" type="Lib.Auto.SugEmail.SugmailBean">
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
	<form name=sugmail method="post" action=./sugmailServlet>
		<br> <br> <br>
		<h2>Email-ID&nbsp;For&nbsp;Suggestion</h2>
		
		<center>
			<table align="center" class="contentTable" width="20%">
				<tr>
					<td>
						<table align="center" width="60%">
							<tr>
								<td>&nbsp;</td>
							</tr>

							<tr>
								<th Class="addedit">&nbsp;&nbsp;&nbsp;&nbsp;Email&nbsp;&nbsp;&nbsp;</th>
								<td><input type="email" name="email"
									size=25 maxlength="35"></td></tr>
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
	if(!document.sugmail.email.value=="")
	{
		document.sugmail.flag.value="save";
		document.sugmail.submit();		
	}else 
	{
		alert("Please Enter Vaild Email !");
		document.sugmail.email.focus();
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
document.sugmail.email.value="<%=beanobject.getEmail_id()%>";
</script>

<%
	}
			
			
			
	if (valid.equals("SaveEmail")) {
		int count = beanobject.getResult();

		if(count > 0)
		{
			%>
			<script language="JavaScript">
					
					alert("Email Added/Updated Successfully");
					document.sugmail.flag.value="load";
					document.sugmail.submit();						
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
