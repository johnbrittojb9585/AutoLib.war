<%@ page language="java"  session="true" buffer="12kb"  import="Common.Security" import="java.util.*"%>
<!--
//////////////////////////////////////DATABASE CONNECTION//////////////////////////////////////////////////////////////////////// -->
<%
//
//   Filename: Search.jsp
//   Form name:Author_Find
//
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", -1);
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control", "max-age=" + (1*60) +" , must-revalidate" );
%>


<!--
//////////////////////////////////////FORM CONTROLS//////////////////////////////////////////////////////////////////////// -->
<html>
  
  <%
   java.sql.Connection con = null;
	java.sql.PreparedStatement Prest = null;
	java.sql.ResultSet rs = null;
  Security conn_object=new Security();
  con=conn_object.getConnection();
  String loginstatus="";
  String flag=request.getParameter("flag");
if(flag!=null)
{

if(flag.equals("Login")){
Prest =con.prepareStatement("select * from login_mas where login_id='"+request.getParameter("user_id")+"' and login_password='"+request.getParameter("pwd")+"' ");    //and login_flag='NO'

rs = Prest.executeQuery();
if (rs.next())
{
session.setAttribute("UserID", rs.getString(1));
%>
<jsp:forward page="/Book_res/ReserveMessage.jsp"/>
<%
}
else
{
loginstatus="<h3>Username and password does't match.</h3>";
}






}


}

%>



  
  
<html>
<head>
<title>Autolib Software Systems,Chennai</title>
<meta http-equiv="pragma" content="no-cache">
<link rel="stylesheet" type="text/css" href="/AutoLib/style.css">
<title>Sign in to Yahoo!</title>
<!-- Refresh login page every 15 minutes -->
<meta http-equiv="Pragma" content="no-cache">
<meta http-equiv="Expires" content="0">
<meta http-equiv="refresh" content="900">
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

</head>
<body  background="/AutoLib/soft.jpg">
<br>
<br>
<br>
<center>
<p></p>
<form method="post" action="Reserve.jsp" name="f1" >

<center>
	 <br><br><br><br><br>
				<table >
				        <tr>
			          <td ></td>
          			<td >
                     Enter Your
			         ID and password</td>
        			</tr>

					<tr>
						<th >
						<label >User ID:</label></th>
						<td >

                        <input name="user_id"  size="25" type="text" ></td>
					</tr>
					<tr>
						<th >
						<label >Password</label></th>
						<td >

                        <input name="pwd" size="25"  type="password" ></td>
					</tr>
				        <tr>
          <td >
          </td>
          <td ><%=loginstatus%></td>
	  <td><a href="pwdchange.jsp" onclick="popup()" style="TEXT-DECORATION :none">change&nbsp;password</a></td>
        </tr>
        <tr>
          <td  colspan="2" ><center>

   <input type=submit value=Reserve  onclick=validate()>
   <input type=reset value=Clear  ></center></td><!-- onclick=AllClr()-->
        </tr>

				</table>	
				
		
   <input type=hidden name="id">
   <input type="hidden" name="flag">
</form>


</center>

</body>
</html>

<%
String valid=request.getParameter("loginmsg");
if(valid!=null){

if(valid.equals("reserved")){
%>
<script>alert("This Resource is aleardy reserved by You!");
history.back();
</script><%
}

if(valid.equals("issued")){
%>
<script>alert("This Resource is Aleardy issued to You!!!!");
history.back();
</script><%
}

if(valid.equals("ReservedNow")){
%>
<script>alert("This Resource has been Reserved.!!!");
history.back();
</script><%
}
if(valid.equals("Reservemax")){
%>
<script>alert("Maximum User Reserved.!!!");
history.back();
</script><%
}


}
%>




<script language=javascript>

function AllClr(){
  location.href="Reserve.jsp";
  }

function validate()
{

if ((f1.user_id.value=="") || (f1.pwd.value==""))
  {
  alert("UserId/Passowrd!...");
  f1.user_id.focus();
  return false;
  }
  else
  {
  document.f1.flag.value="Login";
  document.f1.method="post";
  document.f1.action="Reserve.jsp";
  } 
  
  

	}
	
	
	
	function vaild(n){
if(n==1){
history.back();
}
if(n==2){
location.href="/portal/Logout.jsp?chk=memberlogin";
}


}

function search()
{
location.href="index.asp"
}

function adv()
{
location.href="/AutoLib/Advanced/index.jsp"
}

function home()
{
location.href="/AutoLib/Home.jsp";
}
function newarrival()
{
location.href="newarrivalhome.asp"
}

function account()
{
location.href="/portal/admin.html";}

function Logout()
{
location.href="/AutoLib/index.html";
}


</script>
