<%@ page language="java" session="true" buffer="12kb" import="java.sql.*" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat"%>

<!--
//////////////////////////////////////DATABASE CONNECTION//////////////////////////////////////////////////////////////////////// -->
<%
//
//   Filename: Index.jsp
//   Form name:sreport
//
%>
<!--
//////////////////////////////////////////////////////////////////FORM CONTROLS//////////////////////////////////////////////////////////////////////// -->
<html>
<head>
<title>AutoLib</title>
<meta http-equiv="pragma" content="no-cache"/>
<link rel="stylesheet" type="text/css" href="/AutoLib/style.css">
<script language="javascript" src="/AutoLib/popcalendar.js"></script>
</head>
<body background="/AutoLib/soft.jpg" >

<%
	java.util.Date d =new java.util.Date();
	SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	String dateString = sdf.format(d);

%>

<!-- Style Sheet -->
<form name="Inv_Report" method="POST" action=./InvReportServlet>
<br><br><br>

<h2 >Invoice Statistics Report</h2>
    <div>
    <center>
    <table >
      <tr>
        <td >
         Department</td>
        <td  colspan="2" ><input type="text" name="dept" readonly="true" size="45">
		<input type="button" value="Find" name="Find_Dept"   onclick="FindValue('Dept_Report')"></td>
      </tr>
      <tr>
        <td >Budget</td>
        <td colspan="2" ><input type="text" name="budget" readonly="true" size="45">
		<input type="button" value="Find" name="Find_Budget"  onclick="FindValue('Budget_Report')"></td>
      </tr>
      <tr>
        <td >Supplier</td>
        <td  colspan="2" ><input type="text" name="supplier" readonly="true" size="45">
		<input type="button" value="Find" name="Find_Sup"  onclick="FindValue('Sup_Report')"></td>
      </tr>
      <tr>
        <td >
            <input type="radio" name="Order_Check" value="Order" onclick="check(1)">Order Date&nbsp;
        </td>
        <td >
          From
		  <INPUT name=Ord_From size=15  onfocus=this.blur(); value="<%=dateString%>">
				<SCRIPT language=javascript>
						if (!document.layers) {
						document.write("<input type=button onclick='popUpCalendar(this,Inv_Report.Ord_From, \"dd-mm-yyyy\",\"<%=dateString%>\")'value='...' style='font-size:10 px'>")
						}
				</SCRIPT>
	 </td>
        <td >
          To
		  <INPUT name=Ord_To size=15  onfocus=this.blur(); value="<%=dateString%>">
				<SCRIPT language=javascript>
						if (!document.layers) {
						document.write("<input type=button onclick='popUpCalendar(this,Inv_Report.Ord_To, \"dd-mm-yyyy\",\"<%=dateString%>\")'value='...' style='font-size:10 px'>")
						}
				</SCRIPT>
	   </td>
      </tr>
      <tr>
        <td >
		 <input type="radio" name="Invoice_Check" value="Invoice" onclick="check(2)">Invoice Date</td>
        <td >
	From
	<INPUT name=Inv_From size=15  onfocus=this.blur(); value="<%=dateString%>">
				<SCRIPT language=javascript>
						if (!document.layers) {
						document.write("<input type=button onclick='popUpCalendar(this,Inv_Report.Inv_From, \"dd-mm-yyyy\",\"<%=dateString%>\")'value='...' style='font-size:10 px'>")
						}
				</SCRIPT>
		</td>
        <td >To
	<INPUT name=Inv_To size=15  onfocus=this.blur(); value="<%=dateString%>">
				<SCRIPT language=javascript>
						if (!document.layers) {
						document.write("<input type=button onclick='popUpCalendar(this,Inv_Report.Inv_To, \"dd-mm-yyyy\",\"<%=dateString%>\")'value='...' style='font-size:10 px'>")
						}
				</SCRIPT>

      </tr>
       <tr>
        <td >
		<input type="radio" name="Paid_Check" value="Paid" onclick="check(3)">Paid Date</td>
        <td >
	From
	<INPUT name=Paid_From size=15  onfocus=this.blur(); value="<%=dateString%>">
				<SCRIPT language=javascript>
						if (!document.layers) {
						document.write("<input type=button onclick='popUpCalendar(this,Inv_Report.Paid_From, \"dd-mm-yyyy\",\"<%=dateString%>\")'value='...' style='font-size:10 px'>")
						}
				</SCRIPT>
        <td >To
		<INPUT name=Paid_To size=15  onfocus=this.blur(); value="<%=dateString%>">
				<SCRIPT language=javascript>
						if (!document.layers) {
						document.write("<input type=button onclick='popUpCalendar(this,Inv_Report.Paid_To, \"dd-mm-yyyy\",\"<%=dateString%>\")'value='...' style='font-size:10 px'>")
						}
				</SCRIPT>
      </tr>
      <tr>
        <td  colspan="4">Document
     <select size="1" name="doc">
    <option selected>ALL</option>
    <option>BOOK</option>
    <option>THESIS</option>
    <option>BACK VOLUME</option>
    <option>PROCEEDING</option>
    <option>REPORT</option>
    <option>NON BOOK</option>
    <option>STANDARD</option>
    <option>REFERENCE</option>
     </select>
     Mode
    <select size="1" name="Mode">
    <option selected>ALL</option>
    <option>CASH</option>
    <option>CHEQUE</option>
    <option>DD</option>
    <option>OTHERS</option>
  </select>
       Paid
      <select size="1" name="Paid">
    <option selected>BOTH</option>
    <option>YES</option>
    <option>NO</option>
  </select>
  </td>
	 </tr>
      <tr>
        <td colspan="3"><center>

	  <input type="button" value="Invoice Stat" name="Inv_Stat" onclick="Invoice_Stat()">&nbsp;&nbsp;&nbsp;        <input type="button" value="Bud Stat" name="Bud_Stat" onclick="Budget_Stat()">&nbsp;
  <input type="button" value="Report" name="Report" onclick="Order_Report()">&nbsp;
   <input type="reset" value="Clear" name="Clear">
   <input type="hidden" name="flag">
  <input type="hidden" name="OrderFlag">
  <input type="hidden" name="InvoiceFlag">
  <input type="hidden" name="PaidFlag">
   <center></td>
      </tr>
    </table>
    </center>
  </div>

</form>

</body>
</html>

<script language="JavaScript">

function home()
{
location.href="/AutoLib/Home.jsp";
}

function Logout()
{
location.href="/AutoLib/index.html";
}
function back()
{
window.history.back();
}

function Invoice_Stat()
{
document.Inv_Report.method="post";
document.Inv_Report.flag.value="Inv_Stat";
document.Inv_Report.submit();
}
function Budget_Stat()
{
document.Inv_Report.method="post";
document.Inv_Report.flag.value="Bud_Stat";
document.Inv_Report.submit();
}

function Order_Report()
{
if(document.Inv_Report.Paid_Check.checked==false && document.Inv_Report.Invoice_Check.checked==false && document.Inv_Report.Order_Check.checked==false){
document.Inv_Report.InvoiceFlag.value="";
document.Inv_Report.PaidFlag.value="";
document.Inv_Report.OrderFlag.value="";
}
document.Inv_Report.method="post";
document.Inv_Report.flag.value="Inv_Report";
document.Inv_Report.submit();
}

function check(val)
{
if(val==1)
{
document.Inv_Report.Paid_Check.checked=false;
document.Inv_Report.Order_Check.checked=true;
document.Inv_Report.Invoice_Check.checked=false;
document.Inv_Report.InvoiceFlag.value="";
document.Inv_Report.PaidFlag.value="";
document.Inv_Report.OrderFlag.value="OrderDate";
}
else if(val==2)
{
document.Inv_Report.Paid_Check.checked=false;
document.Inv_Report.Order_Check.checked=false;
document.Inv_Report.Invoice_Check.checked=true;
document.Inv_Report.OrderFlag.value="";
document.Inv_Report.PaidFlag.value="";
document.Inv_Report.InvoiceFlag.value="InvoiceDate";
}
else if(val==3)
{
document.Inv_Report.Paid_Check.checked=true;
document.Inv_Report.Order_Check.checked=false;
document.Inv_Report.Invoice_Check.checked=false;
document.Inv_Report.OrderFlag.value="";
document.Inv_Report.InvoiceFlag.value="";
document.Inv_Report.PaidFlag.value="PaidDate";
}
else
{
document.Inv_Report.OrderFlag.value="";
document.Inv_Report.PaidFlag.value="";
document.Inv_Report.InvoiceFlag.value="";
}
}

function FindValue(val)
{
winpopup=window.open("search_nmvc.jsp?flag="+val,"popup","height=400,width=600,top=100,left=100,status=yes,menubar=no,scrollbars=yes");
}


</script>
