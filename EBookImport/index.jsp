<%
String URole=session.getAttribute("UserRights").toString().trim();
if(URole.equalsIgnoreCase("2") || URole.equalsIgnoreCase("3") || URole.equalsIgnoreCase("4") || URole.equalsIgnoreCase("5") || URole.equalsIgnoreCase("6") || URole.equalsIgnoreCase("7"))
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

<jsp:useBean id="bean" scope="request" class="Lib.Auto.EBookImport.EBookDataMessage" type="Lib.Auto.EBookImport.EBookDataMessage">
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
<body background="/AutoLib/soft.jpg" >

<form name="bookimport" enctype="multipart/form-data" method="post" action=./ImportEBookServlet>

<br><br><br>

 <h2>E-Book Data Import</h2>
<center>
<table align="center" class="contentTable" width="40%">
<td>
<table align="center" width="70%">
<tr><td> &nbsp; </td></tr>
<tr>
<td  Class="addedit">
File<td>
<input type="file" name=bfile size=20? accept=".xls/*" >
</td></tr> 
<tr>
<td colspan=6 ><center>
<br><br>
<input type=button name=Save Class="submitButton" value=Save onclick=SaveRec()>
<input type=Reset name=Clear Class="submitButton" Value=Clear onclick=ClearRec()></center></td></tr>
<input type=hidden name=flag>
</table>
</td></table>


<br><br><br>
	<table cellspacing="0" cellpadding="2" id="noteStruct">
	<tr> <td id="noteHead"> Note: </td> </tr> 
		<tr id="noteBody"> 
			<td> 
				<ul> 
					<li><strong style="color:blue"> Excel File Only (.xls). </strong></li><br>
					<li><a href="<%= request.getContextPath() %>/webcontent/birt/Templates/E-BOOK_TEMPLATE.xls" target=_base><strong style="color:red">Click here to download Template</strong><img src="<%= request.getContextPath() %>/webcontent/birt/images/download.png" width="1%" height="1%"></a> </li>
				</ul> 
			</td> 
		</tr> 
	</table>


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
alert("File Uploaded Successfully!!");
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
             document.bookimport.method="post";
            
                
	    if(chk_mc()){				   
		         	 document.bookimport.submit();

				}
				else
	{
	alert("Please select a file !");
	return false;
	}		
					
}
function chk_mc(){
var flag_cs;
var c;
var mc=document.bookimport.bfile.value;
if(mc=="")
{				
				document.bookimport.flag.value="none";
				document.bookimport.bfile.value="";
				return false;
				}
				else{
		        return true;
		       } 
 } 
 
function ClearRec(){ 

				document.bookimport.flag.value="none";
				document.bookimport.action="index.jsp";
				document.bookimport.submit();	   	


}

</script>


<!--
///////////////////////////////////////////////Java Script//////////////////////////////////////////////
-->