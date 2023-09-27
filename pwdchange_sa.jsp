<%@ page language="java" import="Common.Security"  import="java.io.*,java.sql.*,javax.naming.*,javax.sql.*"%>
<%!
int count=0;
%> 
<% 
	java.sql.Connection con = null;
	java.sql.PreparedStatement Prest = null;
	java.sql.ResultSet rs = null;
  	Security conn_object=new Security();
 	con=conn_object.getConnection();
	String empty=request.getParameter("flag");
	String EMPTY_MESSAGE="Noone field can be empty...";
	String SELECT_LOGIN="select * from login_mas where login_id=? and login_password=?";
	String UPDATE_LOGIN="update login_mas set login_password=? where login_id=?";
	String login_Status="",update_Status="",update_Check="";
	Prest =con.prepareStatement(SELECT_LOGIN);
	Prest.setString(1,request.getParameter("user_id"));
	Prest.setString(2,request.getParameter("pwd"));
	rs=Prest.executeQuery();
	if(rs.next()){
	update_Check="ok";
	}
	else{
	if(count!=0){
	login_Status="User id and old password doesn't match.";
	}
	else{
	login_Status="";
	}
	}
	if(request.getParameter("pwd1")!=null&&request.getParameter("pwd2")!=null){
	if(request.getParameter("pwd1").equals(request.getParameter("pwd2"))){
	if(update_Check.equals("ok")){
	Prest =con.prepareStatement(UPDATE_LOGIN);
	Prest.setString(1,request.getParameter("pwd1"));
	Prest.setString(2,request.getParameter("user_id"));
	Prest.executeUpdate();
	update_Status="Your password has been successfully changed.";
	}
	if(update_Status.equals("")){
	login_Status="User id and old password doesn't match.";
	}
	}
	else{
	login_Status="New Password and Confirm new password doesn't match.";

	}
	}
	
%>
<html>
<head>
<title>Autolib Software Systems,Chennai</title>
<meta http-equiv="pragma" content="no-cache">
<!-- Refresh login page every 15 minutes -->
<meta http-equiv="Pragma" content="no-cache">
<meta http-equiv="Expires" content="0">
<meta http-equiv="refresh" content="900">
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" type="text/css" href="/AutoLib/style.css">

</head>
<body background="/AutoLib/soft.jpg">

<br><br><br>
<div >

<A href="" onclick="cl()" ><img border='0' src='/AutoLib/images/Login.gif' ></a>

</div>
<br><br>
<h2>Change Password</h2>

<form method="get" action="pwdchange.jsp" name="pwdChange" onsubmit="return validate()">

<center>
				<table>
				        <tr>
			          <td ></td>
          			<td >
                     Enter Your ID and password</td>
        			</tr>
			<tr>
    		<td>
						<b>User ID:</b></td>
						<td >

                        <input name="user_id" id="username" value="" size="27" type="text" style="font-family: Verdana; font-size: 8pt"></td>
					</tr>
					<tr>
     					<td >
						<b >
                        Old Password:</b></td>
						<td >

                        <input name="pwd" id="passwd" value="" size="27"  type="password" style="font-family: Verdana; font-size: 8pt"></td>
					</tr>
				        <tr>
          <td >
          <label for="passwd">
          New Password:</td>
          <td >
                        <input name="pwd1" id="passwd1" value="" size="27"  type="password" style="font-family: Verdana; font-size: 8pt"></td>
        </tr>

				        <tr>
          <td >
  <label for="passwd">
          Confirm New Password:</label></td>
          <td >
                      <input name="pwd2" id="passwd0" value="" size="27"  type="password" style="font-family: Verdana; font-size: 8pt"></td>
        </tr>

				        <tr>
          <td  colspan="2" >

 <%
 if(login_Status!=null)
 {
 out.print(login_Status);
 }
 if(empty!=null)
 {
 out.print(EMPTY_MESSAGE);
 }
 %>
 <%
 if(update_Status!=null)
 {
 out.print(update_Status);
 }
 
 %>
</td>
        </tr>

				        <tr>
          <td colspan="2" ><center>
          <input type=submit value=Submit> <input type=reset value=Clear ></center></td><!--onclick="clear();" -->
        </tr>

				</table></center>
	<br>
                &nbsp;&nbsp;
   <input type=hidden name="id">
</form>
</body>
</html>
<script language="javascript">
function validate(){
if ((document.pwdChange.user_id.value=="") || (document.pwdChange.pwd.value=="") || (document.pwdChange.pwd1.value=="") || (document.pwdChange.pwd2.value=="") )
{
 location.href="pwdchange.jsp?flag=empty";
  return false;
  }
else
 {
  return true;
   }
}
function clear(){
location.href="pwdchange.jsp";
}
function cl()
{
window.open("http://192.0.0.30/AutoLib/index.html");
window.close();
}
</script>
