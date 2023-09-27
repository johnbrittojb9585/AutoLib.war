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


<jsp:useBean id="bean" scope="request" class="Lib.Auto.Stock.StockDataMessage" type="Lib.Auto.Stock.StockDataMessage">
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

<form name="bulkstock" enctype="multipart/form-data" method="post" action=./BulkStockServlet>

<br><br><br>

 <h2>Bulk Stock</h2>
<center>
<table align="center" class="contentTable" width="40%">
<td>
<table align="center" width="70%">
<tr><td> &nbsp; </td></tr>
<tr>
<td  Class="addedit" colspan="3">
File&nbsp;:</td><td>
&nbsp;&nbsp;&nbsp;&nbsp;<input type="file" name=sfile size=20? accept=".xls/*" >
</td></tr> 
<tr>
<td colspan=6 ><center>
<br><br>
<input type=button name=Save Class="submitButton" value=Save onclick=SaveRec()>
<input type=Reset name=Clear Class="submitButton" Value=Clear onclick=ClearRec()></center></td></tr>
<input type=hidden name=flag>
</table>
</td></table>

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
document.bulkstock.method="post";
document.bulkstock.action="./StockServlet?flag=LOAD";
document.bulkstock.submit();
</script>

<%
}

if(valid.equals("dataerror")){
	
	out.print("<br><br>");
	out.print("<b><font face=Verdana size=2 color=#ff00ff>"+bean.getErrorMsg()+"</font></b>");	

}

if(valid.equals("InvalidFile")){
	
	out.print("<br><br>");
	out.print("<b><font face=Verdana size=2 color=#ff00ff>Invalid File Format !</font></b>");	

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
     document.bulkstock.method="post";
                
    if(chk_mc()){				   
		  document.bulkstock.submit();

	}else
	{
	   alert("Please select a file !");
	   return false;
	}
	document.bulkstock.submit();			
}

function chk_mc(){
var flag_cs;
var c;
var mc=document.bulkstock.sfile.value;
if(mc=="")
{				
				document.bulkstock.flag.value="none";
				document.bulkstock.sfile.value="";
				return false;
				}
				else{
		        return true;
		       } 
 } 
 
 
function ClearRec(){ 

				document.bulkstock.flag.value="none";
				document.bulkstock.action="bulkindex.jsp";
				document.bulkstock.submit();	  
}

function load(){
	document.bulkstock.member.focus();

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