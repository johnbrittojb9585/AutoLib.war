<%@ include file="/Tree/demoFrameset.jsp"%>
<%@ page language="java"  session="true" buffer="12kb" import="java.sql.*" import="Common.Security" %>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/button_css.css" />
<jsp:useBean id="bean" scope="page" class="Lib.Auto.FreqUsedResource.FreqUsedBean"/>
<html>
<head>
<meta charset="ISO-8859-1">
<title>AutoLib</title>
<!-- <script language="javascript" src="/AutoLib/popcalendar.js"></script> -->
<link rel="stylesheet" type="text/css" href="/AutoLib/style.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-3.1.0.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-ui.js"></script>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/jquery-ui.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/datepicker.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/datepicker2.js"></script>

<meta http-equiv="pragma" content="no-cache"/>
</head>
<body background="/AutoLib/soft.jpg">

<form name="freqused" action="./FreqUsedResource">

<h2>Frequently Used Resource</h2>

  <table align="center" class="contentTable" width="65%">
<td>
<table align="center" width="90%">
<tr><td> &nbsp; </td></tr>
  <tr>
    <td ><input type="radio" value="frequency"  name="r1" onclick="change_resource()" checked> Frequently
      Accessed Resource </td>
    <td >&nbsp;</td>
  </tr>
  <tr>
    <td ><input type="radio" name="r1" value="unused" onclick="change_resource()">Unused Resource</td>
    <td ></td>
  </tr>
  <tr>
    <td ><input type="radio" name="r1" value="access" onclick="change_access()">Access No</td>
    <td ><input type="text" name="txtaccess" size=10" disabled></td>
  </tr>
  <tr>
    <td ><input type="radio" name="r1" value="title" onclick="change_title()">Title</td>
    <td ><input type="text" name="txttitle" size="42" disabled></td>
  </tr>

                <%
			
				java.sql.ResultSet rs_Dept=bean.getDept_al();
			                   %>


  <TD >Document
              Type</TD>

            <TD >
              <SELECT SIZE="1" NAME="txtdoctype" >

		<OPTION VALUE="BOOK">BOOK</OPTION>
		<OPTION VALUE="BOOK BANK">BOOK BANK</OPTION>
                <option value="NON BOOK">NON BOOK</option>		
                <option value="REPORT">REPORTS</option>		
                <OPTION VALUE="THESIS">THESIS</OPTION>
		        <option value="STANDARD">STANDARD</option>
                <option value="PROCEEDING">PROCEEDING</option>		                
                <option value="BACK VOLUME">BACK VOLUME</option>
                <option value="ALL" selected>ALL</option>
			    </SELECT>
  </TD>
  </tr>
  <tr></tr>
  <tr></tr>
  <tr></tr>
  <tr>
    <td></td>
    <td colspan=2>From <input type="text" name="recfrom" size="10" value="1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    To <input type="text" name="recto" size="10" value="20"></td>
  </tr>
  <tr></tr>
  <tr></tr>
  <tr></tr>
  <tr>
        <td></td>
        <TD colspan=2>From
<%-- 	<INPUT name=fromdate size=15  onfocus=this.blur(); value="<%=Security.CalenderDate()%>"> --%>
	<INPUT name=fromdate size=15 id="datepicker" value="<%=Security.CalenderDate()%>">
				
	To
<%-- 	<INPUT name=todate size=15  onfocus=this.blur(); value="<%=Security.CalenderDate()%>"> --%>
	<INPUT name=todate size=15 id="datepicker2" value="<%=Security.CalenderDate()%>">
				
				</td>
      </tr>
      <tr>
	<td></td>
	
	<td colspan="3">
	<input type="radio" name="printType" value="pdf" checked>PDF
	<img src="<%= request.getContextPath() %>/iconImages/pdf.png" width="40" height="45" border="0" title="Print PDF">
	
	<input type="radio" name="printType" value="excel">Excel
	 <img src="<%= request.getContextPath() %>/images/xls.gif" width="35" height="40" border="0" title="Print Excel"></a>
	</td>
	</tr>
      <tr></tr>
  <tr></tr>
  <tr></tr>
 <tr><td colspan=3 align=center >
<CENTER>
    <input type="button" Class="submitButton"  value="Print" name="print" onclick="Show_report()">
    <input type="reset" value="Clear" name="clear" Class="submitButton"  onclick="change_resource()">
  

</table></td></table>
</form>
</body>
</html>
<script language="JavaScript">
function Show_report()
{
if((document.freqused.r1[0].checked==true) || (document.freqused.r1[1].checked==true) || (document.freqused.r1[2].checked==true) || (document.freqused.r1[3].checked==true))
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

</script>

