<%
String URole=session.getAttribute("UserRights").toString().trim();
if(URole.equalsIgnoreCase("4") || URole.equalsIgnoreCase("5") || URole.equalsIgnoreCase("6") || URole.equalsIgnoreCase("7"))
{		
	response.sendRedirect("sessiontimeout.jsp");
}	
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored ="false" %>
<%@ include file="/Tree/demoFrameset.jsp"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page language="java" errorPage="/Error/ErrorPage.jsp"  import="java.util.*"  session="true" buffer="12kb" import="Common.Security"%>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/button_css.css" />
<jsp:useBean id="ss" scope="request" class="Lib.Auto.Member.MemberBean" type="Lib.Auto.Member.MemberBean">
</jsp:useBean>



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
<script language="javascript" src="/AutoLib/popcalendar.js"></script>
<link rel="stylesheet" type="text/css" href="/AutoLib/style.css">
</head>
<body background="/AutoLib/soft.jpg" onload="load()">

<form name="fphoto" enctype="multipart/form-data" method="post" action=./PhotoServlet>

<br><br><br>

 <h2>Member Photo</h2>
<center>
<table align="center" class="contentTable" width="40%">
<td>
<table align="center" width="70%">
<tr><td> &nbsp; </td></tr>
<tr>
<td Class="addedit">Member&nbsp;Code
<td >
<input type=text name=member size=20 maxlength=15 onKeydown="Javascript: if (event.keyCode==13) return SaveRec();"></td></tr>
<tr>
<td  Class="addedit">
Photograph<td>
<input type="file" name=photo size=20? accept="image/*" >
</td></tr> 
<tr>
<td colspan=6 ><center>
<br><br>
<input type=button name=Save Class="submitButton" value=Save onclick=SaveRec()>
<input type=Reset name=Clear Class="submitButton" Value=Clear onclick=ClearRec()></center></td></tr>
<input type=hidden name=flag>
</table>
</td></table>


<!--<br><br><br>-->
<!--	<table cellspacing="0" cellpadding="4" id="noteStruct">-->
<!--	<tr></tr>-->
<!--		<tr id="noteBody"> -->
<!--			<td> -->
<!--				<ul> -->
<!--					<li> Photo size less than 70KB. </li>-->
<!--				</ul> -->
<!--			</td> -->
<!--		</tr> -->
<!--	</table>-->


</form>
</body>
</html>
<!--
//////////////////////////////////////JAVA SERVER PAGES SCRIPT///////////////////////////////////////////////// -->
<%
String valid=request.getParameter("check");
if(valid!=null){

if(request.getParameter("check")!=null){
if(valid.equals("success")){
 %>
  <script language="JavaScript">
alert("Done");
</script>
<%
}
if(valid.equals("FailMember")){ 
%>
            <script language="JavaScript">
            alert("Record Not Found");
			document.fphoto.member.focus();
			document.fphoto.flag.value="none";
			location.href="index.jsp";
		   	</script><%
			}
	    

if(valid.equals("LargeFile")){
	  %>
	  	  <script language="JavaScript">
	  	  alert("File is Too Large !");			
	  	 </script>
	  <%
	  }
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
             document.fphoto.method="post";
                 
	      if(chk_mc()){
	          
	          if(document.fphoto.photo.value=='')	
	          {
	            document.fphoto.flag.value="Clear";
		        document.fphoto.submit();
	          }else  {
				document.fphoto.flag.value="SavePhoto";
		        document.fphoto.submit();
		      }
				}
				else
	            {
	               alert("Type Valid Member ID !");
	               return false;
	            }		
					
}
function chk_mc(){
var flag_cs;
var c;
var mc=document.fphoto.member.value;
if(mc=="")
{
				document.fphoto.member.focus();
				document.fphoto.flag.value="none";
				document.fphoto.member.value="";
				return false;
				}
				else{
		                    return true;
		                         } 

 } 
function ClearRec(){ 

				document.fphoto.flag.value="none";
				document.fphoto.action="index.jsp";
				document.fphoto.submit();	   	


}
function load(){
	document.fphoto.member.focus();

		 }
</script>

<script type="text/javascript">
function openDialog() {
    var result = window.showModalDialog("http://www.java2s.com", form, "dialogWidth:300px; dialogHeight:201px; center:yes");
}
</script>
<!--
///////////////////////////////////////////////Java Script//////////////////////////////////////////////
-->