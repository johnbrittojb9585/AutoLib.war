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

<form name="fphoto" enctype="multipart/form-data" method="post" action=./ContentUploadServlet>

<br><br><br>

 <h2>Contents Upload</h2>
<center>
<table align="center" class="contentTable" width="40%">
<td>
<table align="center" width="70%">
<tr><td> &nbsp; </td></tr>
<tr>
<td Class="addedit"><label id="ContentType">BAccessNo</label><td>
<input type=text name=accno size=20 maxlength=15 onKeydown="Javascript: if (event.keyCode==13) return SaveRec();"></td>
</tr>

<tr>
<td Class="addedit">Document&nbsp;type</td><td><!-- shek -->
  <select name="document" size="1" id="alldoctype" onchange="ChangeLabel();">
<option  value="BOOK">BOOK</option>
      <option value="BOOK BANK">BOOK BANK</option>
	  <option value="NON BOOK">NON BOOK</option>
	  <option value="REPORT">REPORT</option>
	  <option value="THESIS">THESIS</option>
	  <option value="STANDARD">STANDARD</option>
	  <option value="PROCEEDING">PROCEEDING</option>
	  <option value="BACK VOLUME">BACK VOLUME</option>
	  <option value="JOURNAL ISSUES">JOURNAL ISSUES</option>		  
	  <option value="NEWS CLIPPING">NEWS CLIPPING</option>
  	  <option value="QUESTION BANK">QUESTION BANK</option>
	  <option value="JOURNAL ARTICLE">JOURNAL ARTICLE</option>
	  <option value="EBOOK">EBOOK</option>	  	  
</select>
</td>
</tr>
<tr></tr>
<tr>
<td  Class="addedit">
File<td>
<input type="file" name=photo size=20? >
</td></tr> 
<tr>
<td colspan=6 ><center>
<br><br>
<input type=button name=Save Class="submitButton" value=Save onclick=SaveRec()>
<input type=Reset name=Clear Class="submitButton" Value=Clear onclick=ClearRec()></center></td></tr>
<input type=hidden name=flag>
<input type=hidden name=lebelhead>
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
</script>
<%
}
if(valid.equals("FailMember")){ 
%>
            <script language="JavaScript">
            alert("Record Not Found");
			document.fphoto.accessNo.focus();
			document.fphoto.flag.value="none";
			location.href="index.jsp";
		   	</script><%
			}
if(valid.equals("NotRightUser")){
	  %>
	  	  <script language="JavaScript">
	  	  alert("Access denied !");			
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
				    document.fphoto.flag.value="photo";
		         	document.fphoto.submit();
				}
				else
	{
	alert("Type Valid Access Number !");
	return false;
	}		
					
}
function chk_mc(){
var flag_cs;
var c;
var mc=document.fphoto.accno.value;
if(mc=="")
{
				document.fphoto.flag.value="none";
				document.fphoto.accno.value="";
				document.fphoto.accno.focus();				
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

function ChangeLabel(){

var el = document.getElementById('alldoctype');
var text = el.options[el.selectedIndex].innerHTML;
document.fphoto.lebelhead.value=text;	

if(text == "BOOK"){
document.getElementById('ContentType').innerHTML = 'BAccessNo';
}

if(text == "BOOK BANK"){
document.getElementById('ContentType').innerHTML = 'BBAccessNo';
}

if(text == "REPORT"){
document.getElementById('ContentType').innerHTML = 'ReportNo';
}

if(text == "THESIS"){
document.getElementById('ContentType').innerHTML = 'ThesisNo';
}

if(text == "STANDARD"){
document.getElementById('ContentType').innerHTML = 'StandardNo';
}

if(text == "BACK VOLUME"){
document.getElementById('ContentType').innerHTML = 'BVNo';
}

if(text == "JOURNAL ISSUES"){
document.getElementById('ContentType').innerHTML = 'JnlIssueNo';
}

if(text == "NEWS CLIPPING"){
document.getElementById('ContentType').innerHTML = 'NewClippingNo';
}

if(text == "NON BOOK"){
document.getElementById('ContentType').innerHTML = 'NBAccessNo';
}

if(text == "PROCEEDING"){
document.getElementById('ContentType').innerHTML = 'ProceedingNo';
}

if(text == "QUESTION BANK"){
document.getElementById('ContentType').innerHTML = 'QB No';
}

if(text == "JOURNAL ARTICLE"){
document.getElementById('ContentType').innerHTML = 'JnlArticleNo';
}
if(text == "EBOOK"){
	document.getElementById('ContentType').innerHTML = 'EBOOKNo';
	}
}


function load(){
	document.fphoto.accno.focus();

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