<%
String URole=session.getAttribute("UserRights").toString().trim();
if(URole.equalsIgnoreCase("2") || URole.equalsIgnoreCase("3") || URole.equalsIgnoreCase("4") || URole.equalsIgnoreCase("5") || URole.equalsIgnoreCase("6") || URole.equalsIgnoreCase("7"))
{		
	response.sendRedirect("sessiontimeout.jsp");
}	
%>
<%@ include file="/Tree/demoFrameset.jsp"%>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/button_css.css" />
<%@ page import="java.util.Calendar" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat"%>


<!--
//////////////////////////////////////////////////////////////////FORM CONTROLS//////////////////////////////////////////////////////////////////////// -->
<html>
<head>
<title>AutoLib</title>
<script language="javascript" src="/AutoLib/popcalendar.js"></script>
<link rel="stylesheet" type="text/css" href="/AutoLib/style.css">
<meta http-equiv="pragma" content="no-cache"/>
</head>
<body background="/AutoLib/soft.jpg"><!--onload="opt(1)"-->
<%
	java.util.Date d =new java.util.Date();
	SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	String dateString = sdf.format(d);

%>


<!-- Style Sheet -->

<form name="Send_Mail" method="Post" action=./LibMailServlet>
<br><br><br>

<br><br><br>
  <div align="center">
    <center>
  <table align="center" class="contentTable" width="25%">
<td>
<table align="center" width="25%">    
      <tr>
      <h2>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Email&nbsp;/&nbsp;SMS&nbsp;Testing</h2><br>  </tr>      
          <p align="center">
<tr><td>  <input type="button" Class="submitButton" value="Send E-Mail" onclick=SendRec1()></td><td>
		  <input type="button" Class="submitButton" value="Send SMS" onclick=SendRec()>		 </td>
		  <input type="hidden" name="flag">		  		  
	   </td>
      </tr>   
    </table></table></table>
    </center>
  </div>

</form>
</body>
</html>

<%
String valid=request.getParameter("check");

if(valid!=null){
if(request.getParameter("check")!=null){

if(valid.equals("SaveSuccess"))
{
	%>
	
	 <script language="JavaScript">
	 
	alert("E-Mail Send Successfully !");
	 
	 </script>
	 
<%
}
if(valid.equals("SaveFail"))
{
	%>
	
	 <script language="JavaScript">
	 
	alert("E-Mail Sending Failed !");
	 
	 </script>
	 
<%
}
if(valid.equals("SaveSMSSuccess"))
{
	%>
	
	 <script language="JavaScript">
	 
	alert("SMS Send Successfully !");
	 
	 </script>
	 
<%
}
if(valid.equals("SaveSMSFail"))
{
	%>
	
	 <script language="JavaScript">
	 
	alert("SMS Sending Failed !");
	 
	 </script>
	 
<%
}
}
}

%>

<script language="javascript">

function home()
{
location.href="/AutoLib/Home.jsp";
}

function Logout()
{
location.href="/AutoLib/index.html";
}
function SendRec() {
//document.Send_Mail.flag.value="SendMail";
document.Send_Mail.flag.value="SendSMS";
document.Send_Mail.method="get";
document.Send_Mail.submit();
}
function SendRec1() {
document.Send_Mail.flag.value="SendMail";
//document.Send_Mail.flag.value="SendSMS";
document.Send_Mail.method="get";
document.Send_Mail.submit();
}
</script>


