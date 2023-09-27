<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/Tree/autolib.css" />
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/button_css.css" />
<!-- DW6 -->

<head>
<!-- 

**********  index.jsp   *********

Copyright 2005 Macromedia, Inc. All rights reserved. -->
<title>Panimalar College Of Nursing</title>
<title>John Britto</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<link rel="stylesheet" href="mm_travel2.css" type="text/css" />

<script language="JavaScript" type="text/javascript">

function validate()
   {

if(t1())
{
alert("Please enter Username & Password");
document.form1.txtuserid.focus();
document.form1.action = "index.jsp";
document.form1.method = "post";
document.form1.submit();
return false;
}
else
{
document.form1.flag.value="login";
document.form1.libSubmit.disabled=true;
document.form1.submit();
return true;
}

}

function isBase64(str) {
	console.log("Checking isBase64 ::" + str);
	if (str === '' || str.trim() === '') {
		return false;
	}
	try {
		return btoa(atob(str)) == str;
	} catch (err) {
		return false;
	}
}




  function t1()
{
if(document.form1.txtuserid.value=="")
{
return true;
}
else
{
return false;
}
}

//--------------- LOCALIZEABLE GLOBALS ---------------
var d=new Date();
var monthname=new Array("January","February","March","April","May","June","July","August","September","October","November","December");
//Ensure correct for language. English is "January 1, 2004"
var TODAY = monthname[d.getMonth()] + " " + d.getDate() + ", " + d.getFullYear();
//---------------   END LOCALIZEABLE   ---------------
</script>

<style type="text/css">
#dateformat{
clear: both;
padding: 0;
width: 100%;
height: 2px;
line-height: 16px;
background: #104e8b ;
/*border-bottom: 1px solid #fff; /*Remove this to remove border between bar and tabs*/
text-align: right;
}
#tablebrowser{
	position:absolute;
	bottom:0;
	width: 100%;
	border: 1px solid #666;
	background:#104e8b; 
	}

</style>

	
<script language = "Javascript">
window.history.forward(0)
</script>
</head>
<body class="front" onload="login()">

<form action="/AutoLib/Login" name="form1" method="post"  onsubmit="return validate()" >
<table border="0" width="100%" cellspacing="0" cellpadding="5" height="400">
  <tr>
    <td width="100%" height="100">
      <table border="0" width="100%" cellspacing="0" cellpadding="0" height="128">
        <tr>
          <td width="17%" height="80">
<!--          <img src="/AutoLib/images/S1.jpg" alt="" width="130" height="146" border="0" />-->
          </td>
          <td width="50%" height="80" align="center" id="clgtitle">
          <font size=6 face="Bookman Old style"  color="#104e8b">Panimalar College Of Nursing</font><br>
         <font size=4 face="Bookman Old style" color="#310202" ><b>VARADHARAJAPURAM, POONAMALLEE, CHENNAI 600123</b></font>
          </td>
          <td width="17%" height="80">
          &nbsp;
          </td> 
        </tr>
        <tr>
          <td width="100%" colspan="3" id="dateformat"><font size="2" face="verdana" color="white">
          <script language="JavaScript" type="text/javascript">
      	document.write(TODAY);	</script>
      	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      	Powered by AutoLib Software Systems</font>
          </td>
        </tr>
       
      </table>

    </td>
  </tr>
  <tr>
    <td width="100%" height="300">
      <table border="0" width="100%" cellspacing="0" cellpadding="5" height="150">
        <tr>
          <td width="70%" height="100">&nbsp;</td>
          <td width="30%" height="100">
            <table border="0" width="100%" cellspacing="0" cellpadding="5" class="indexTab">
            <tr>
            <tr>
            <p>(CARE: Username and password are case sensitive.)</p>
            </tr>
            <tr>
                <td width="100%" colspan="2" align="left"  bgcolor="#104e8b">
                <font color="white"><b>Login</font>
                </td>
              </tr>
              <tr>
                <td width="33%">
                User Name
                </td>
                <td width="70%">
                <input type="text" name="txtuserid" value=""  />
                </td>
              </tr>
              <tr>
                <td width="33%">
                Password
                </td>
                <td width="70%">
                 <input type="password" name="txtpasword" value=""/>
                </td>
              </tr>
              <tr>
                <td width="100%" colspan="2" align="center">
                <input type="submit" name="libSubmit" Class="submitButton" value="Login" />
           <input type="reset"  name="clear" Class="submitButton" value="Clear" onclick="return clearLogin()" />
           <input type="hidden" name="flag">           
                </td>                
              </tr>              
            </table>
          </td>          
        </tr>
        <tr> <td></td><td align="right">
        <a href="" onclick="FindForgotPWD()">Can't access your account?</a></td>  </tr>
      </table>
    </td>
  </tr>
</table>

<div id="tablebrowser">
	
<font color="white"><b><center></>This Application is best viewed in Chrome Browser and Firefox 20+ @ 1024 x 768 resolution and above.      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;              Version V22.01</center></b> </font>

</div>

</form>
</body>	
</html>

<script>

function login()
{
  document.form1.txtuserid.focus();
}
function FindForgotPWD()
{
  winpopup=window.open("SendMail/Forgotpwd.jsp","popup","height=200,width=600,left=100,top=100,scrollbars=yes,toolbar=no,status=yes,menubar=no")
}

function clearLogin()
{
  document.form1.method = "post";
  document.form1.action = "index.jsp";
  document.form1.submit();
}

</script>
