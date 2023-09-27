
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/button_css.css" />
<script type="text/javascript" src="<%= request.getContextPath() %>/script/common.js" ></script>
<script type="text/javascript" src="<%= request.getContextPath() %>/script/tamil.js" ></script>

<html>
<%
   response.setHeader("Pragma", "No-cache");
   response.setHeader("Cache-Control", "no-cache");
   response.setDateHeader("Expires", 0);
%>
<head>
<title>AutoLib</title>
<link rel="stylesheet" type="text/css" href="/AutoLib/style.css">
</head>
<body background="/AutoLib/soft.jpg" onload="focuss()">
<form name="Forget_Pwd" method="post" action=./ForgetPWD >

<!--
//////////////////////////////////////JAVA SERVER PAGES SCRIPT ///////////////////////////////////////////////// --> 
<!--
//////////////////////////////////////FORM CONTROLS//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// -->
<div><H2>Having trouble signing in?</h2></div>
<div><A href="" onclick="window.close()">Back</A></div>
<center>
<td>Email ID:<input type=text name=emailid  size=25></td>
<input type=button Class="submitButton" value="Send" onclick=SendRec()>
<input type=hidden name=flag  value="Author">
</center></form>

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
	 
	alert("Password has been send to your E-Mail Successfully !");
	window.close()
	 </script>
	 
<%
}
if(valid.equals("SaveFail"))
{
	%>
	
	 <script language="JavaScript">
	 
	alert("Invalid E-Mail or User!");

	 </script>
	 
<%
}
}
}

%>
<script  language="javascript">
function focuss(){
document.Forget_Pwd.emailid.focus();
}
function SendRec() {
if(document.Forget_Pwd.emailid.value=="") {
alert("Invalid Entry");
}
else
{
document.Forget_Pwd.flag.value="ForgetPWD";
document.Forget_Pwd.method="get";
document.Forget_Pwd.submit();
}
}
</script>