<%
String URole=session.getAttribute("UserRights").toString().trim();
if(URole.equalsIgnoreCase("3") || URole.equalsIgnoreCase("4") || URole.equalsIgnoreCase("6") || URole.equalsIgnoreCase("7"))
{		
	response.sendRedirect("sessiontimeout.jsp");
}	
%>
<%@ include file="/Tree/demoFrameset.jsp"%>
<%@ page language="java" session="true" buffer="12kb" import="java.sql.*" %>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/button_css.css" />
<jsp:useBean id="viewObject" scope="request" class="Lib.Auto.OrdInvProcessing.orderbean"/>
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
<body background="/AutoLib/soft.jpg" >
<%
	java.util.Date d =new java.util.Date();
	SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	String dateString = sdf.format(d);

%>

<!-- Style Sheet -->

<form method="post" name="ordinv" action=./OrderInvServlet>
<br><br><br>

<h2 >Order Invoice Processing</h2>

  <center>
<table align="center" class="contentTable" width="65%">
<td>
<table align="center" width="90%">
<tr><td> &nbsp; </td></tr>
    <tr>
      <td Class="addedit">Sl No</td>
      <td><input type="text" name="slno" size="8"  maxlength=8 >
	  <input type="button" value="Find" Class="submitButton" onclick=FindValue("Slno")></td>
      <td Class="addedit">Order No</td>
      <td ><input type="text" name="ordno" size="17" maxlength=15></td>
      <td Class="addedit">Ord. Date</td>
      <TD >
	<INPUT name=orddate size=10  onfocus=this.blur(); value="<%=dateString%>">
				<SCRIPT language=javascript>
						if (!document.layers) {
						document.write("<input type=button onclick='popUpCalendar(this,ordinv.orddate, \"dd-mm-yyyy\",\"<%=dateString%>\")'value='...' style='font-size:10 px'>")
						}
				</SCRIPT>
		 </td>
    </tr>
    <tr>
      <td Class="addedit">Supplier</td>
      <td><input type="text" name="scode" size="8" readonly="true">
      
      <input type="button" value="Find" Class="submitButton" onclick=FindValue("Sup")></td>
      <td colspan="4" ><input type="text" name="sname" size="57" readOnly=true></td>
    </tr>
    <tr>
      <td Class="addedit">Budget</td>
      <td ><input type="text" name="bcode" size="8" readonly="true">
      <input type="button" value="Find" Class="submitButton" onclick=FindValue("Bud")></td>
      <td colspan="4" ><input type="text" name="bname" size="57" readOnly=true></td>
    </tr>
    <tr>
      <td Class="addedit">Inv. No</td>
      <td ><input type="text" name="invno" size="14" maxlength=14></td>
      <td Class="addedit">Inv. Date</td>
      <TD >
	<INPUT name=invdate size=10  onfocus=this.blur(); value="<%=dateString%>">
				<SCRIPT language=javascript>
						if (!document.layers) {
						document.write("<input type=button onclick='popUpCalendar(this,ordinv.invdate, \"dd-mm-yyyy\",\"<%=dateString%>\")'value='...' style='font-size:10 px'>")
						}
				</SCRIPT>
		 </td>
      <td Class="addedit">Amount</b></td>
      <td ><input type="text" name="invamount" size="10"  maxlength=8 onKeyUp="return ordamt_val();"></td>
    </tr>
    <tr>
      <td Class="addedit">Dept</td>
      <td ><input type="text" name="dcode" size="8" readonly="true">
      <input type="button" value="Find" Class="submitButton" onclick=FindValue("Dept")></td>
      <td colspan="4" ><input type="text" name="dname" size="57" readOnly=true></td>
    </tr>
    <tr>
      <td Class="addedit">Year</td>
      <td><input type="text" name="year" size="14" maxlength=4 onKeyUp="return year_val();"></td>
      <td Class="addedit">Document</td>
      <td >
          <select name="docname" size="1">
          <option value="BOOK">BOOK</option>
          <option value="THESIS">THESIS</option>
          <option value="BACK VOLUME">BACK VOLUME</option>
          <option value="PROCEEDING">PROCEEDING</option>
          <option value="REPORT">REPORT</option>
          <option value="NON BOOK">NON BOOK</option>
	  <option value="STANDARD">STANDARD</option>
	  <option value="REFERENCE">REFERENCE</option>
	  </select>
	  </td>
      <td colspan="2" Class="addedit">
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <input type="radio" value="V1" checked  name="R1" onclick=optcheck("Debit")>Debit&nbsp;&nbsp;
        <input type="radio" value="V2" name="R1" onclick=optcheck("Credit")>Credit
      </td>
    </tr>
    <tr>
      <td Class="addedit">Paid</td>
      <td ><select size="1" name="paid">
          <option selected value="YES">YES</option>
          <option value="NO">NO</option>
        </select></td>
      <td Class="addedit">Paid Date</td>
      <TD >
	<INPUT name=paiddate size=10  onfocus=this.blur(); value="<%=dateString%>">
				<SCRIPT language=javascript>
						if (!document.layers) {
						document.write("<input type=button onclick='popUpCalendar(this,ordinv.paiddate, \"dd-mm-yyyy\",\"<%=dateString%>\")'value='...' style='font-size:10 px'>")
						}
				</SCRIPT>
		 </td>
</td>
 <td Class="addedit">Mode</td>
      <td ><select size="1" name="mode">
          <option selected value="CASH" >CASH</option>
          <option value="CHEQUE" >CHEQUE</option>
          <option value="DD"> DD</option>
          <option value="OTHERS">OTHERS</option>
        </select></td>
    </tr>
    <tr>
     <td Class="addedit">Pay Detail</td>
      <td colspan="5"><input type="text" name="paydetails" size="75" maxlength=100></td>
    </tr>
    <tr>
      <td Class="addedit">Add1</td>
      <td  colspan="5"><input type="text" name="add1" size="75" maxlength=200></td>
    </tr>
    <tr>
      <td Class="addedit">Add2</td>
      <td  colspan="5" ><input type="text" name="add2" size="75" maxlength=200></td>
    </tr>
    <tr>
      <td  colspan="6" >
        <p align="center">
		<input type="button" value="New" Class="submitButton" name="new" onclick="NewRecord()">
		<input type="button" value="Save" Class="submitButton" name="save" onclick="SaveRecord()">
		<input type="button" value="Delete" Class="submitButton" name="delete" onclick="DeleteRecord()">
		<input type="submit" value="Search" Class="submitButton" name="search" onclick="SearchRecord()">
		<input type="reset"  Class="submitButton" value="clear">
		<input type="hidden"  name="flag" value="null">
		<input type="hidden"  name="crdbflag" value="null">
		<input type="hidden"  name="ord_flag" value="null">
		</td>
    </tr>
  </table>
 </CENTER>
</form>
</body>
</html>
<%
String valid=request.getParameter("check");
if(valid!=null){
if(request.getParameter("check")!=null){
if(valid.equals("newInvoice")){
 %>
 <script language="JavaScript">
document.ordinv.slno.value="<%=viewObject.getImax()%>";
document.ordinv.ordno.focus();
</script>
<%
  }
  if(valid.equals("SuccessInvoice")){
 %>
  <script language="JavaScript">



            document.ordinv.mode.value="<%=viewObject.getImode()%>";
			document.ordinv.slno.value="<%=viewObject.getImax()%>";
			document.ordinv.ordno.value="<%=viewObject.getIord()%>";
			document.ordinv.orddate.value="<%=viewObject.getIordate()%>";
			document.ordinv.scode.value="<%=viewObject.getIscode()%>";
			document.ordinv.bcode.value="<%=viewObject.getIbcode()%>";
			document.ordinv.invno.value="<%=viewObject.getIinvno()%>";
			document.ordinv.invdate.value="<%=viewObject.getIinvdate()%>";
			document.ordinv.invamount.value="<%=viewObject.getIamount()%>";
			document.ordinv.dcode.value="<%=viewObject.getIdcode()%>";
			document.ordinv.year.value="<%=viewObject.getIyear()%>";
			document.ordinv.docname.value="<%=viewObject.getIdtype()%>";
			document.ordinv.dname.value="<%=viewObject.getIdname()%>";
			document.ordinv.sname.value="<%=viewObject.getIsname()%>";
			document.ordinv.bname.value="<%=viewObject.getIbhead()%>";
			
			
opt="<%=viewObject.getIcrdeb()%>";

if(opt=="Debit")
{document.ordinv.R1[0].checked=true;}
else
{document.ordinv.R1[1].checked=true;}

			document.ordinv.paid.value="<%=viewObject.getIpaid()%>";
			document.ordinv.paiddate.value="<%=viewObject.getIpaydate()%>";
			document.ordinv.mode.value="<%=viewObject.getImode()%>";
			document.ordinv.paydetails.value="<%=viewObject.getIpaydet()%>";
			document.ordinv.add1.value="<%=viewObject.getIadd1()%>";
			document.ordinv.add2.value="<%=viewObject.getIadd2()%>";
			document.ordinv.sname.value="<%=viewObject.getIsname()%>";
			document.ordinv.dname.value="<%=viewObject.getIdname()%>";
			document.ordinv.bname.value="<%=viewObject.getIbhead()%>";
			document.ordinv.slno.focus();

</script>
<%
  }
  if(valid.equals("FailureInvoice")){
 %>
  <script language="JavaScript">
  	alert("Record not found");
	window.history.back();
	document.ordinv.flag.value="new";
	document.ordinv.submit();
  </script>
  <%
  }

  if(valid.equals("SaveInvoice")){
%>             <script language="JavaScript">
			 alert("Record Inserted Successfully!");
			 history.back();
			 document.ordinv.flag.value="new";
			document.ordinv.submit();
		     </script>
			<%
			}

if(valid.equals("ConfirmUpdate")){
%>             <script language="JavaScript">

			document.ordinv.slno.value="<%=viewObject.getImax()%>";
			document.ordinv.ordno.value="<%=viewObject.getIord()%>";
			document.ordinv.orddate.value="<%=viewObject.getIordate()%>";
			document.ordinv.scode.value="<%=viewObject.getIscode()%>";
			document.ordinv.bcode.value="<%=viewObject.getIbcode()%>";
			document.ordinv.invno.value="<%=viewObject.getIinvno()%>";
			document.ordinv.invdate.value="<%=viewObject.getIinvdate()%>";
			document.ordinv.invamount.value="<%=viewObject.getIamount()%>";
			document.ordinv.dcode.value="<%=viewObject.getIdcode()%>";
			document.ordinv.year.value="<%=viewObject.getIyear()%>";
			document.ordinv.docname.value="<%=viewObject.getIdtype()%>";

opt="<%=viewObject.getIcrdeb()%>";
if(opt=="Debit")
{document.ordinv.R1[0].checked=true;}
else
{document.ordinv.R1[1].checked=true;}

			document.ordinv.paid.value="<%=viewObject.getIpaid()%>";
			document.ordinv.paiddate.value="<%=viewObject.getIpaydate()%>";
			document.ordinv.mode.value="<%=viewObject.getImode()%>";
			document.ordinv.paydetails.value="<%=viewObject.getIpaydet()%>";
			document.ordinv.add1.value="<%=viewObject.getIadd1()%>";
			document.ordinv.add2.value="<%=viewObject.getIadd2()%>";
			document.ordinv.sname.value="<%=viewObject.getIsname()%>";
			document.ordinv.dname.value="<%=viewObject.getIdname()%>";
			document.ordinv.bname.value="<%=viewObject.getIbhead()%>";

			 msg=confirm("Record Already Exists Are You Sure To Update?");
                 if(msg)
                   {
		   		if(document.ordinv.R1[0].checked==true)
				{document.ordinv.crdbflag.value="Debit";}
				if(document.ordinv.R1[1].checked==true)
				{document.ordinv.crdbflag.value="Credit";}
				document.ordinv.flag.value="update";
		         	document.ordinv.submit();
		   }

		     </script>
			<%
			}


			if(valid.equals("UpdateInvoice")){%>
            <script language="JavaScript">
			alert("Record Updated Successfully!");
			history.back();
			document.ordinv.flag.value="new";
			document.ordinv.submit();
		   	</script><%
			}

			if(valid.equals("DeleteInvoice")){%>
            		<script language="JavaScript">
			alert("Record Deleted Successfully!");
			history.back();
			document.ordinv.flag.value="new";
			document.ordinv.submit();
		   	</script><%
			}
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

function NewRecord()
{
	document.ordinv.method="get";
	document.ordinv.flag.value="new";
	document.ordinv.submit();

}

function SearchRecord()
{
document.ordinv.method="get";
var no=document.ordinv.slno.value;
	if(no=="")
	{
				document.ordinv.slno.focus();
				alert("Insufficent Data");
				document.ordinv.flag.value="new";
				document.ordinv.submit();
	}
	else if(isNaN(no)){
				document.ordinv.slno.focus();
				alert("Insufficent Data");
				document.ordinv.flag.value="new";
				document.ordinv.submit()

	}
	else
        {
document.ordinv.flag.value="search";
document.ordinv.submit();
}
}


function SaveRecord()
	{
if(document.ordinv.slno.value=="" || document.ordinv.ordno.value=="" || document.ordinv.invno.value==""
	|| document.ordinv.scode.value=="" || document.ordinv.bcode.value=="" || document.ordinv.invamount.value==""
	|| document.ordinv.dcode.value=="")
	{
	alert("Insufficient Data");
	window.history.back();
	document.ordinv.flag.value="new";
	document.ordinv.submit();
	}
	else{
	if(document.ordinv.R1[0].checked==true)
	{document.ordinv.crdbflag.value="Debit";}
	if(document.ordinv.R1[1].checked==true)
	{document.ordinv.crdbflag.value="Credit";}
    	document.ordinv.method="post";
    	document.ordinv.flag.value="save";
	document.ordinv.submit();
	}
	}

function DeleteRecord(){
document.ordinv.method="get";

		if (document.ordinv.slno.value=="" || document.ordinv.ordno.value=="" || document.ordinv.invno.value==""
	|| document.ordinv.scode.value=="" || document.ordinv.bcode.value=="" || document.ordinv.invamount.value==""
	|| document.ordinv.dcode.value==""){
				document.ordinv.slno.focus();
				alert("Insufficent Data");
				window.history.back();
				document.ordinv.flag.value="new";
				document.ordinv.submit();

				}
			else{
				msg=confirm("Are You Sure To Delete");
					if(msg){
						document.ordinv.flag.value="delete";
						document.ordinv.submit();
						}
						else
						{
						alert("Operation Cancelled..!");
						window.history.back();
						document.ordinv.flag.value="new";                         						document.ordinv.submit();
						}
				}
}


function FindValue(val)
{
winpopup=window.open("search_nmvc.jsp?flag="+val,"popup","height=400,width=600,top=100,left=100,status=yes,menubar=no,scrollbars=yes,toolbar=no");
}

function optcheck(val)
{
if(val=="Debit")
document.ordinv.crdbflag.value="Debit";
else
document.ordinv.crdbflag.value="Credit";
}

function transfer()
{
 if(document.ordinv.slno.value=="" || document.ordinv.invno.value=="" ){
 alert("Please Select the Record");
document.ordinv.flag.value="new";
document.ordinv.submit();
 }
 else
 {
   document.ordinv.flag.value="transfer";
   document.ordinv.action="transfer.jsp";
  document.ordinv.submit();
}
}
function report()
{
   document.ordinv.flag.value="report";
    document.ordinv.action="report.jsp";
   document.ordinv.submit();
}
function ordamt_val() {
if((isNaN(document.ordinv.invamount.value))||(document.ordinv.invamount.value == ' ')) {
document.ordinv.invamount.select();
document.ordinv.invamount.value="";
return false;
   }
}
function year_val() {
if((isNaN(document.ordinv.year.value))||(document.ordinv.year.value == ' ')) {
document.ordinv.year.select();
document.ordinv.year.value="";
return false;
   }
}

</script>

