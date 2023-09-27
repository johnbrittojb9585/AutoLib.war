
<%@ include file="/Tree/demoFrameset.jsp"%>
<%@ page language="java"  session="true" buffer="12kb" import="java.sql.*" import="Common.Security" %>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/button_css.css" />
<jsp:useBean id="bean" scope="page" class="Lib.Auto.FreqUsedMember.FreqUsedMBean"/>
<html>
<head>
<title>Auto Lib</title>
<script language="javascript" src="/AutoLib/popcalendar.js"></script>
<link rel="stylesheet" type="text/css" href="/AutoLib/style.css">
<meta http-equiv="pragma" content="no-cache"/>
</head>
<body background="/AutoLib/soft.jpg">

<form name="freqused" action="./FreqUsedMember">
<br><br><br>
<h2>Frequently Used Member</h2>

  <table align="center" class="contentTable" width="45%">
  <tr>
<td>
<table align="center" width="60%">


<tr><td> &nbsp; </td></tr>


  <tr>
    <td Class="addedit"><input type="radio" value="frequency"  name="r1" onclick="change_resource()" checked>Frequent Borrowers</td>
    <td Class="addedit"><input type="radio" name="r1" value="gate" onclick="change_resource()">Library Visitors</td>
    <td Class="addedit"><input type="radio" name="r1" value="member" onclick="change_resource()">Members Not Borrowed Resources</td>
   </tr>
   <tr><td>&nbsp;&nbsp;</td></tr>
  </table>
  <table align="center">
  <tr><td Class="addedit" >Department<td colspan="1"> <select size="1" name="dept"  style="width: 300px">
<option value="ALL">ALL</option>
<c:if test="${DepartmentList ne null }" >
<c:forEach items="${DepartmentList}" var="std" varStatus="loop">
<option value="<c:out value="${std.code}"/>"><c:out value="${std.name}" /></option>
</c:forEach>
</c:if>
</select></td></tr>
      
      
 <tr><td Class="addedit">Group&nbsp;Name
<td><select size="1" name="group" style="width: 300px">
<option value="ALL">ALL</option>
<c:if test="${distinctBranchWiseGroupSearchList ne null }" >
<c:forEach items="${distinctBranchWiseGroupSearchList}" var="std" varStatus="loop">
<option value="<c:out value="${std.code}"/>"><c:out value="${std.name}" /></option>
</c:forEach>
</c:if>
</select>
</td>
</tr>

  <tr>
   <td Class="addedit">Date From</td><td><INPUT name=fromdate size=10  onfocus=this.blur(); value="<%=Security.CalenderDate()%>">
	<SCRIPT language=javascript>
		if (!document.layers) 
			{
			document.write("<input type=button onclick='popUpCalendar(this,freqused.fromdate, \"dd-mm-yyyy\",\"<%=Security.CalenderDate()%>\")'value='...' style='font-size:10 px'>")
			}
	</SCRIPT></td>
	<td Class="addedit">Date To</td><td><INPUT name=todate size=10  onfocus=this.blur(); value="<%=Security.CalenderDate()%>">
	<SCRIPT language=javascript>
			if (!document.layers) 
			{
			document.write("<input type=button onclick='popUpCalendar(this,freqused.todate, \"dd-mm-yyyy\",\"<%=Security.CalenderDate()%>\")'value='...' style='font-size:10 px'>")
			}	
	</SCRIPT> </td>
  </tr>
  
  <tr>
  <td Class="addedit"> Limit From</td><td><input type="text" name="recfrom" size="16" maxlength="5" value="0"></td>
  <td Class="addedit">Limit To</td><td><input type="text" name="recto" size="16" maxlength="5" value="20"></td>
  </tr>
  
  <td Class="addedit">Year</td><td><select size="1" name="year"  style="width: 70px">
        <option selected value="ALL">ALL</option>
        <option value="1">1 Year</option>
        <option value="2">2 Year</option>
        <option value="3">3 Year</option>
        <option value="4">4 Year</option>
        
        </select></td>
  
<!--   <tr> -->
<!--     <td Class="addedit">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" name="r1" value="title" onclick="change_title()">Title</td> -->
<!--     <td colspan=4><input type="text" name="txttitle" size="45" disabled></td> -->
<!--   </tr> -->
  <tr><td>&nbsp;</td></tr>
  
  <tr>
	<td></td>
	
	<td colspan="3">
	<input type="radio" name="printType" value="pdf" checked>PDF
	<img src="<%= request.getContextPath() %>/iconImages/pdf.png" width="40" height="45" border="0" title="Print PDF">
	
	<input type="radio" name="printType" value="excel">Excel
	 <img src="<%= request.getContextPath() %>/images/xls.gif" width="35" height="40" border="0" title="Print Excel"></a>
	</td>
	</tr>
  
  </table></td></tr></table>

<p align="center">
	<input type="button" Class="submitButton"  value="Print" name="print" onclick="Show_report()">
    <input type="reset" value="Clear" name="clear" Class="submitButton"  onclick="change_resource()">
    <input type="hidden" name="flag">
</p>
</form></body></html>
 <%
String valid=request.getParameter("check");
if(valid!=null){
	if(valid.equals("NoData")){	%>
	  <script >
		alert("No Record Found");
		
		document.freqused.flag.value="load";
		
		document.freqused.submit();
		</script>

<%
	}
	
}
	%>

<script language="JavaScript">
function Show_report()
{
if((document.freqused.r1[0].checked==true) || (document.freqused.r1[1].checked==true) || (document.freqused.r1[2].checked==true))
{
 document.freqused.submit();
}
else
{
alert("Select any one Choice !");
}
}

function home()
{
location.href="/AutoLib/Home.jsp";
}

function Logout()
{
location.href="/AutoLib/index.html";
}

function rec_code_val() {
if(isNaN(document.freqused.recfrom.value) || isNaN(document.freqused.recto.value) || document.freqused.recfrom.value=='' || document.freqused.recto.value=='') {
document.freqused.action="index.jsp";
  }
}
function change_access(){
  txt_clear();
 document.freqused.txtaccess.disabled=false;
 document.freqused.txttitle.disabled=true;
}
function change_title(){
 txt_clear();
 document.freqused.txtaccess.disabled=true;
 document.freqused.txttitle.disabled=false;
}
function change_dept(){
 txt_clear();
 document.freqused.txtaccess.disabled=true;
 document.freqused.txttitle.disabled=true;
}
function change_resource(){
  txt_clear();
 document.freqused.txtaccess.disabled=true;
 document.freqused.txttitle.disabled=true;
}
function txt_clear(){
 document.freqused.txtaccess.value="";
 document.freqused.txttitle.value="";
}
function FindValue(val){
	winpopup=window.open("search.jsp?flag="+val ,"popup","height=400,width=600,left=100,top=100,scrollbars=yes,toolbar=no,status=yes,menubar=no");
	}
	
	
	
	

</script>

