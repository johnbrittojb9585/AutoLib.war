<html>

<%@ include file="/Tree/demoFrameset.jsp"%>
<%@ page language="java" errorPage="/Error/ErrorPage.jsp" session="true"
	buffer="12kb" import="java.sql.*,java.util.*"%>
<link rel="stylesheet" type="text/css"
	href="<%= request.getContextPath() %>/css/button_css.css" />
<%@ page import="java.util.Calendar"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.text.SimpleDateFormat"%>
<head>
<meta charset="ISO-8859-1">
<title>Auto Lib</title>
<!-- <script language="javascript" src="/AutoLib/popcalendar.js"></script> -->
<link rel="stylesheet" type="text/css" href="/AutoLib/style.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-3.1.0.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-ui.js"></script>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/jquery-ui.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/datepicker.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/datepicker2.js"></script>

<meta http-equiv="pragma" content="no-cache" />
</head>
<body background="/AutoLib/soft.jpg">

<%
java.util.Date d = new java.util.Date();
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			String dateString = sdf.format(d);

			%>





<form name="SpineLabel" method="Post" action=./SpineLabelServlet><br>
<br>
<br>

<h2>Spine&nbsp;Label</h2>
<div align="center">
<center>
<table align="center" class="contentTable" width="45%">
	<tr>
		<td>
		<table align="center" width="90%">
			<tr>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td><input type=radio value=byaccno name=acc onclick=change()>By&nbsp;Access&nbsp;No</td>
				<td>From</td>
				<td><input type="text" name="From_Accno" size="15" maxlength=10></td>

				<td>To</td>
				<td><input type="text" name="To_Accno" size="15" maxlength=10></td>
			</tr>
			<tr>
				<td><input type=radio value=bydate name=acc1 onclick=changes()>By Date</td>
				<td>From</td>
<%-- 				<TD><INPUT name=fromdt size=10 onfocus=this.blur(); value="<%=dateString%>">  --%>
				<TD><INPUT name=fromdt size=10 id="datepicker" value="<%=dateString%>"> 
			</td>
				<td>To</td>
<%-- 				<TD><INPUT name=todt size=10 onfocus=this.blur(); value="<%=dateString%>"> --%>
				<TD><INPUT name=todt size=10 id="datepicker2" value="<%=dateString%>"> 
				
		    	</td>
			</tr>
			<tr>
				<td align="center">&nbsp;&nbsp;Document&nbsp;Type</td>
				<td colspan="3">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<select name="Type" size="1">
					<option selected value="ALL">ALL</option>
					<option value="BOOK">BOOK</option>
					<option value="BOOK BANK">BOOK BANK</option>
					<option value="NON BOOK">NON BOOK</option>
					<option value="REPORT">REPORT</option>
					<option value="THESIS">THESIS</option>
					<option value="STANDARD">STANDARD</option>
					<option value="PROCEEDING">PROCEEDING</option>
					<option value="BACK VOLUME">BACK VOLUME</option>
				</select></td>
			</tr>
			<tr>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td colspan="5">
				<p align="center"><input type="button" Class="submitButton"
					value="Print" name="Access_Print" onclick="Print_Report()"> <input
					type="reset" Class="submitButton" value="Clear" name="Access_clear">

				<input type="hidden" name="flag"> <input type="hidden" name="flagNo">
				</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
			</tr>
		</table>
</table>

</center>
</div>

</form>
</body>



<script language="javascript">

function home()
{
location.href="/AutoLib/Home.jsp";
}

function Logout()
{
location.href="/AutoLib/index.html";
}

function Print_ExcelReport()
{

if(document.SpineLabel.acc1.checked==true)
{
  if(document.SpineLabel.From_Accno.value=="" || document.SpineLabel.To_Accno.value=="")
  {
     alert("Insufficient Data !");
  }
  else
  {
    alert("ACCESSION NO WISE !");
    document.SpineLabel.flag.value="AccessNo_Wise";
    document.SpineLabel.submit();
  }
}else if(document.SpineLabel.acc.checked==true)
{
alert("DATE WISE");
    document.SpineLabel.flag.value="Date_Wise";
    document.SpineLabel.submit();
}
else
{
   alert("Choose either by AccessNo or by Date wise !");
}

}


function Print_Report()
{

if(document.SpineLabel.acc.checked==true)
{
if(document.SpineLabel.From_Accno.value=="" || document.SpineLabel.To_Accno.value=="")
{
alert("Insufficient Data !");
}
else
{




document.SpineLabel.flag.value="AccessNo_Wise";
document.SpineLabel.submit();


}
}



else
{
if(document.SpineLabel.acc1.checked==true)
{

document.SpineLabel.flag.value="Date_Wise";
document.SpineLabel.submit();
}
}

}







function change(){

document.SpineLabel.acc1.checked=false;

}
function changes(){

document.SpineLabel.acc.checked=false;

}


function chk(){

		  if(document.SpineLabel.From_Accno.value=="")
		  {
		    alert("Insufficent Data");
			document.SpineLabel.From_Accno.focus();
			return false;
			}
		    else if (document.SpineLabel.To_Accno.value=="")
		    {
			document.SpineLabel.To_Accno.focus();
			alert("Insufficent Data");
			return false;
		    }

         else{
		return true;
		}
}



</script>


</html>
