<%@ include file="/Tree/demoFrameset.jsp"%>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/button_css.css" />
<%@ page import="java.util.Calendar" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat"%>


<!--
//////////////////////////////////////////////////////////////////FORM CONTROLS//////////////////////////////////////////////////////////////////////// -->
<html>
<head>
<title>AutoLib</title>
<script language="javascript" src="/AutoLib/popcalendar.js"></script>
<link rel="stylesheet" type="text/css" href="/AutoLib/style.css">
<meta http-equiv="pragma" content="no-cache"/>
</head>
<body background="/AutoLib/soft.jpg"><!--onload="opt(1)"-->
<%
	java.util.Date d =new java.util.Date();
	SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	String dateString = sdf.format(d);

%>


<!-- Style Sheet -->

<!-- Style Sheet -->
<form name="Lib_Collection" method="Post" action=./LibCollectionServlet>
<br><br><br>
<br><br><br>
  <div align="center">
    <center>
  <table align="center" class="contentTable" width="35%">
<td>
<table align="center" width="45%">    
      <td>
      <h2>Library&nbsp;Collection</h2><br>        
    <tr>
    <td>
	<input type="radio" name="printType" value="pdf" checked>PDF
	<img src="<%= request.getContextPath() %>/iconImages/pdf.png" width="40" height="45" border="0" title="Print PDF">
	
	<input type="radio" name="printType" value="excel">Excel
	 <img src="<%= request.getContextPath() %>/images/xls.gif" width="40" height="45" border="0" title="Print Excel"></a>
	</td>
	</tr>
	<tr></tr>
	<tr></tr>
	<tr></tr>
<tr>

<td>      <center> 
		  <input type="submit" Class="submitButton" value="Print" name="Lib_Print" onclick="printreport()">
		  <input type="submit" Class="submitButton" value="Chart" name="Lib_Print" onclick="chart()">
		  <input type="hidden" name="flag">		  		  
	   </td>
      </tr>   
    </table></table>
    </center>
  </div>

</form>
</body>
</html>

<script language="javascript">
function printreport()
{
var type = document.Lib_Collection.printType.value;
document.Lib_Collection.flag.value=type;
document.Lib_Collection.submit();
}
function chart()
{
document.Lib_Collection.flag.value="chart";
document.Lib_Collection.submit();
}
function home()
{
location.href="/AutoLib/Home.jsp";
}

function Logout()
{
location.href="/AutoLib/index.html";
}

</script>


