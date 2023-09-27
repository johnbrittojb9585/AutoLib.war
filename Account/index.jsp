<%@ include file="/Tree/demoFrameset.jsp"%>
<%@ page language="java" errorPage="/Error/ErrorPage.jsp"  import="java.util.*"  session="true" buffer="12kb" import="Common.Security"%>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/button_css.css" />

<!--
//////////////////////////////////////DATABASE CONNECTION//////////////////////////////////////////////////////////////////////// -->
<%
//
//   Filename: Index.jsp
//   Form name:Author
//
%>
<!--
//////////////////////////////////////////////////////////////////FORM CONTROLS//////////////////////////////////////////////////////////////////////// -->
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
<form name="Account" method="post" action=./AccountServlet>
<br><br><br>

 <P ALIGN="left"><BR>
 <h2>  </h2>
 <center>
   <h2> Check&nbsp;Account </h2>
<table align="center" class="contentTable" width="35%">
<td>

<table align="center" width="90%">
<tr><td> &nbsp; </td></tr>
<tr>
<td>&nbsp;&nbsp;
</tr>
<tr><td Class="addedit">User&nbsp;ID<td><input type=text name="uid" size="20"  maxlength="20">

    
<tr><td Class="addedit">Password<td><input type=password name="pwd" size="20" maxlength="20">
   
   
<tr><td colspan=3 align=center >
<CENTER>





<input type=submit name=Save Class="submitButton" value=Login onclick=SaveRec()>
<input type=reset name=Clear Class="submitButton"  Value=Clear >

</CENTER>
<input type=hidden name=flag>
</table>
<tr><td> &nbsp; </td></tr>
</table></table><CENTER><tr><td><br></td></tr>
<tr><td></td><td></td><td></td><td></td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<td Class="addedit"><a href="changepwd.jsp" ><font face="veranda" color="#8B4513">Change Password</font></a><td></td></tr>
</CENTER>
</form>
</body>
</html>

<!--
////////////////////////////////////////////////JAVA SERVER PAGES SCRIPT ///////////////////////////////////////////////// --> 
<%
String uchek=request.getParameter("check");
if(uchek!=null){
if(uchek.equals("usernotfound")){
	 %>
	<script language="JavaScript">
	alert("Invalid UserId/Password!!!");
	document.Account.uid.focus();
	</script><%

	}
}

%>

<!--
///////////////////////////////////////JAVA SCRIPT USEING CLIENT SIDE///////////////////////////////////////////////// --> 
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

            
			   if((document.Account.uid.value=="")||(document.Account.pwd.value=="")){
			    	
					alert("Insufficent Data");
					document.Account.flag.value="new";
		         	document.Account.submit();	
			}		
	else
	{
					document.Account.flag.value="save";
		         	document.Account.submit();	
	
	}
	
}


function isWhitespace(str)
		{
	
			var re = /[\S]/g
			if (re.test(str)) return false;
			return true;
			
		}
		
			


</script> 
<!--
//////////////////////////////////////JAVA SCRIPT USEING CLIENT SIDE///////////////////////////////////////////////// --> 
