<html>
<%
String URole=session.getAttribute("UserRights").toString().trim();
if(URole.equalsIgnoreCase("4") || URole.equalsIgnoreCase("5") || URole.equalsIgnoreCase("6") || URole.equalsIgnoreCase("7") || URole.equalsIgnoreCase("8"))
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

<form name="fphoto" enctype="multipart/form-data" method="post" action=./BulkPhotoServlet>

<br><br><br>

 <h2>Bulk Member Photo Upload</h2>
 
<table align="center" class="contentTable" width="40%">
<tr>
<td>
<table align="center" width="70%">
<tr><td> &nbsp; </td></tr>
<tr>

<tr><td  Class="addedit">Choose File<td>
<!-- <input type="file" name=photo size=20? accept="image/*" ></td></tr> -->

<input type="file" id="fileUpload" name=photo size=20? accept=".zip" required></td></tr>

</table></td></tr>
<tr><td>&nbsp;</td></tr>
</table>


<p align="center">
<font align="center" color="red"><b>Zip.&nbsp;File&nbsp;Format&nbsp;Only</b></font>
</p>


<p align="center">
<input type=button name=Search Class="submitButton" value=Save onclick=SearchRec()>
<input type=Reset name=Clear Class="submitButton" Value=Clear onclick=ClearRec()>
<input type=hidden name=flag>
</p>




</form>
</body>

<!--
//////////////////////////////////////JAVA SERVER PAGES SCRIPT///////////////////////////////////////////////// -->
<%
String valid=request.getParameter("check");
if(valid!=null){

if(request.getParameter("check")!=null){
if(valid.equals("success")){
 %>
 
  <script language="JavaScript">
alert(<c:out value="${failedPhotoCount}" />+" Image(s) Failed, "+<c:out value="${successPhotoCount}" />+" Image(s) Uploaded Successfully");
</script>
<%
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

<script language=javascript>

function home()
{
  location.href="/AutoLib/Home.jsp";
}

function Logout()
{
  location.href="/AutoLib/index.html";
}



function SearchRec(){
	
	var checkZipFile = document.getElementById('fileUpload').value.substr(document.getElementById('fileUpload').value.length - 4);

	if(document.getElementById('fileUpload').value == '' || document.getElementById('fileUpload').value == null ){
		alert("No file selected!");
	}else if(checkZipFile != '.zip') {
		alert("Only zip file format is requird.");
	}else
		{
		document.fphoto.submit();
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
</html>
