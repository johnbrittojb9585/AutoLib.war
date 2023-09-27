<%@ page language="java" session="true" buffer="12kb" import="java.sql.*" %>
<html>
<head>
<title>AutoLib</title>
<meta http-equiv="pragma" content="no-cache"/>
<link rel="stylesheet" type="text/css" href="/AutoLib/style.css">
</head>
<body background="/AutoLib/soft.jpg"  onload="focuss()" >

<form name=Trans  method=get action=./TransferServlet>
<br><br><br>
<!--<center>
<img border='0' src='/AutoLib/images/home.gif' onclick='home()'>
<img border='0' src='/AutoLib/images/Logout.gif' onclick='Logout()'>
<img border='0' src='/AutoLib/images/Back.gif' onclick='back()'>
</center><br>-->
<h2>Transfer Book Information</h2>
<center>
<table >
  <tr>
    <td >
      Inv No/Date
    </td>
    <td >
      <input type="text" name="InvNo"  readonly="true" size="20"><td></td>
    </td>
  </tr>
  <tr>
    <td >
      AccNo From
    </td>
    <td >
   <input type="text" name="AccFrom" size="20">
    </td>
    <td colspan="3">
      AccNo To
    <input type="text" name="AccTo" size="20">
    </td>
  </tr>
  <tr>
    <td colspan=3>
      <center>
      	  <!--<input type="button" value="List"  onclick=list()>-->
	  <input type="button" value="Transfer"  onclick=transfer()>
	  <input type="button" value="Clear"  onclick="accclr()">
	  <input type="hidden" name="flag">
	  <input type="hidden" name=Transfer_Invoice>
	  <input type="hidden" name="listflag"></center>
	  </td>
  </tr>
</table>
<center>
</form>
</body>
</html>
<%
String Transfer_Invoice="";
int iLevel=0; String list="";
java.sql.Connection con=null;
java.sql.Statement st=null;
java.sql.ResultSet rs=null;
String flag=request.getParameter("flag");
if(flag!=null){
if(flag.equals("transfer"))
{
String Flag_Status="", invoice_no="",invoice_date="",invoice_day="",invoice_month="",invoice_year="",Accto="",Accfrom="",sql="";
String inv_no_trans=request.getParameter("invno");
String inv_date_trans=request.getParameter("invdate");
String slno=request.getParameter("slno");
String ordno=request.getParameter("ordno");
String orddate=request.getParameter("orddate");
String invamount=request.getParameter("invamount");
String year=request.getParameter("year");
String docname=request.getParameter("docname");
String R1=request.getParameter("R1");
String paid=request.getParameter("paid");
String paiddate=request.getParameter("paiddate");
String mode=request.getParameter("mode");
String paydetails=request.getParameter("paydetails");
String add1=request.getParameter("add1");
String add2=request.getParameter("add2");
Accto=request.getParameter("AccTo");
Accfrom=request.getParameter("AccFrom");
Transfer_Invoice=inv_no_trans +";"+inv_date_trans;
%><script>document.Trans.InvNo.value="<%=Transfer_Invoice%>";</script><%
}
if(flag.equals("SuccessTransfer"))
{
%>
<script language="javascript">
alert("Record Transfered Successfully!");
       window.history.back();
       </script>
<%
}
if(flag.equals("FailureTransfer"))
{
%>
<script language="javascript">
alert("The Invoice Number or Invoice Date is not saved. Please Save that before proceed....");
window.history.back();
location.href="index.jsp";
</script>
<%
}

}
%>

<script>

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
//location.href="/AutoLib/index.html";
window.history.back();
}


function transfer()
{
if(document.Trans.AccTo.value=="" || document.Trans.AccFrom.value=="")
{
alert("Specify the Access Number")
document.Trans.AccFrom.focus();
}
else
{
document.Trans.flag.value="transfer_f";
document.Trans.submit();
document.Trans.AccTo.value="";
document.Trans.AccFrom.value="";
}
}
function accclr(){
document.Trans.AccTo.value="";
document.Trans.AccFrom.value="";
}
function focuss(){
document.Trans.AccFrom.focus();
}
function list()
{
document.Trans.flag.value="list";
document.Trans.submit();

}
function show(a1)
{
document.Trans.AccTo.value=a1;
document.Trans.AccFrom.value=a1;

}
</script>


