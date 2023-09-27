<%
String URole=session.getAttribute("UserRights").toString().trim();
if(URole.equalsIgnoreCase("3") || URole.equalsIgnoreCase("4") || URole.equalsIgnoreCase("6") || URole.equalsIgnoreCase("7"))
{		
	response.sendRedirect("sessiontimeout.jsp");
}	
%>
<%@ include file="/Tree/demoFrameset.jsp"%>
<%@ page language="java" errorPage="/Error/ErrorPage.jsp" import="java.io.*" import="java.util.*" import="Lib.Auto.Currency.CurrencyBean" import="Lib.Auto.JNL_Order.JnlorderBean" session="true" buffer="12kb" import="Common.Security"%>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/button_css.css" />
<jsp:useBean id="viewObject" scope="request" class="Lib.Auto.JNL_Order.JnlorderBean"/>
<%@ page import="java.util.Calendar" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat"%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored ="false" %>

<!--
//////////////////////////////////////////////////////////////////FORM CONTROLS//////////////////////////////////////////////////////////////////////// -->
<html>
<head>
<meta charset="ISO-8859-1">
<title>AutoLib</title>
<!-- <script language="javascript" src="/AutoLib/popcalendar.js"></script> -->
<script language="javascript" src="/AutoLib/datetimepicker_css.js"></script>
<link rel="stylesheet" type="text/css" href="/AutoLib/style.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-3.1.0.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-ui.js"></script>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/jquery-ui.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/datepicker.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/datepicker2.js"></script>

<meta http-equiv="pragma" content="no-cache"/>
</head>
<body background="/AutoLib/soft.jpg" >
<%
	java.util.Date d =new java.util.Date();
	SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	String dateString = sdf.format(d);

%>


<form method="post" name="ordinv" action=./InvoiceJNLServlet>
<br><br><br>
<h2 >Journal Invoice Processing</h2>
<center>
<table align="center" class="contentTable" width="76%">
<td>
<table align="center" width="95%">
<tr><td> &nbsp; </td></tr>

<c:choose>
<c:when test="${JNLInvoiceSize gt 0}">  
<c:forEach items="${JNLInvoice}" var="std"  begin="0" end="0">
<tr>
      <td Class="addedit">Inv. RefNo</td>
      <td ><input type="text" name="invrno" size="10" value="<c:out value="${std.sno}"/>" maxlength=15 readonly="true"></td>
      <td Class="addedit">Inv. RDate</td>
        <TD >
	<INPUT name=invrdate size=8  onfocus=this.blur(); value="<c:out value="${std.receiveddate}"/>" >
				<SCRIPT language=javascript>
						if (!document.layers) {
						document.write("<input type=button onclick='popUpCalendar(this,ordinv.invrdate, \"dd-mm-yyyy\",\"<%=dateString%>\")'value='...' style='font-size:10 px'>")
						}
				</SCRIPT>
		 </td>    
      <td Class="addedit">Supplier Inv. No</td>
      <td ><input type="text" name="supinvno" value="<c:out value="${std.invoiceno}"/>" size="17" readonly="true">
      <input type="button" value="Find" Class="submitButton" onclick=FindValue("Invoice")></td>   	   
      </tr>
<tr>    
      <td Class="addedit">Supplier</td>
      <td><input type="hidden" name="sup_code" value="<c:out value="${std.supCode}"/>">
      <input type="text" name="sname" value="<c:out value="${std.supplier}"/>" size="25" readOnly=true>
      </td>        
      <td Class="addedit">Payment.Flag</td>
      <td ><select name="pmtflag" id="pmtflag">
      <option value="<c:out value="${std.paymentflag}"/>"><c:out value="${std.paymentflag}"/></option>
      </td>
      <td Class="addedit">Payment.Ref-No</td>
      <td ><input type="text" name="pmtno" value="<c:out value="${std.paymentno}"/>" size="17" maxlength=15 readonly="true"></td>  
</tr>
<tr><td></td><td></td></tr>
<tr><td></td></tr>
<tr><td></td><td></td></tr>
<tr><td></td></tr>
<tr><td></td></tr>
<tr><td></td></tr>
<tr>
      <td Class="addedit">Inv. No</td>
      <td ><input type="text" name="invno" onKeydown="Javascript: if (event.keyCode==13) SearchRecord();" value="<c:out value="${std.invoiceno}"/>" size="17" maxlength=15>
            <input type="button" value="Find" Class="submitButton" onclick=FindValue("SInvoiceNo")></td>
      <td Class="addedit">Inv. Date</td>
        <TD >
	<INPUT name=invdate size=8  onfocus=this.blur(); value="<c:out value="${std.invoicedate}"/>" >
				<SCRIPT language=javascript>
						if (!document.layers) {
						document.write("<input type=button onclick='popUpCalendar(this,ordinv.invdate, \"dd-mm-yyyy\",\"<%=dateString%>\")'value='...' style='font-size:10 px'>")
						}
				</SCRIPT>
		 </td>      
      <td Class="addedit">Inv. Amount</td>
      <td colspan="3" ><input type="text" name="invamount" value="<c:out value="${std.netamount}"/>" size="10" maxlength="8" onKeyUp="return invamt_val()">
      </td>
    </tr>             

    <tr>
     <td Class="addedit">Remarks</td>
      <td colspan="5"><input type="text" name="remarks" value="<c:out value="${std.remarks}"/>" size="75" maxlength=100></td>
    </tr>
    <tr>
      <td Class="addedit">Add1</td>
      <td  colspan="5"><input type="text" name="add1" value="<c:out value="${std.add1}"/>" size="75" maxlength=200></td>
    </tr>
    <tr>
      <td Class="addedit">Add2</td>
      <td  colspan="5" ><input type="text" name="add2" value="<c:out value="${std.add2}"/>" size="75" maxlength=200></td>
    </tr>
</c:forEach>
</c:when>
<c:otherwise>

<tr>
      <td Class="addedit">Inv. RefNo</td>
      <td ><input type="text" name="invrno" size="10" value="<c:out value="${JNLInvoice.sno}"/>" maxlength=15 readonly="true"></td>
      <td Class="addedit">Inv. RDate</td>
        <TD >
<%-- 	<INPUT name=invrdate size=8  onfocus=this.blur(); value="<%=dateString%>"> --%>
	<INPUT name=invrdate size=8 id="datepicker" value="<%=dateString%>">
				
		 </td>    
      <td Class="addedit">Supplier Inv. No</td>
      <td ><input type="text" name="supinvno" size="17" readonly="true">
      <input type="button" value="Find" Class="submitButton" onclick=FindValue("Invoice")></td>   	   
      </tr>
<tr>    
      <td Class="addedit">Supplier</td>
      <td><input type="hidden" name="sup_code">
      <input type="text" name="sname" size="25" readOnly=true>
      </td>        
      <td Class="addedit">Payment.Flag</td>
      <td ><select name="pmtflag" id="pmtflag" >
      <option VALUE="PENDING">PENDING</option>
      </td>
      <td Class="addedit">Payment.Ref-No</td>
      <td ><input type="text" name="pmtno" size="17" maxlength=15 readonly="true"></td>  
</tr>
<tr><td></td><td></td></tr>
<tr><td></td></tr>
<tr><td></td><td></td></tr>
<tr><td></td></tr>
<tr><td></td></tr>
<tr><td></td></tr>
<tr>
      <td Class="addedit">Inv. No</td>
      <td ><input type="text" name="invno" onKeydown="Javascript: if (event.keyCode==13) SearchRecord();" size="17" maxlength=15>
            <input type="button" value="Find" Class="submitButton" onclick=FindValue("SInvoiceNo")></td>
      <td Class="addedit">Inv. Date</td>
        <TD >
<%-- 	<INPUT name=invdate size=8  onfocus=this.blur(); value="<%=dateString%>"> --%>
	<INPUT name=invdate size=8 id="datepicker2" value="<%=dateString%>">
				
						 </td>      
      <td Class="addedit">Inv. Amount</td>
      <td colspan="3" ><input type="text" name="invamount" value="0" size="10" maxlength="8" onKeyUp="return invamt_val()">
      </td>
    </tr>             

    <tr>
     <td Class="addedit">Remarks</td>
      <td colspan="5"><input type="text" name="remarks" size="75" maxlength=100></td>
    </tr>
    <tr>
      <td Class="addedit">Add1</td>
      <td  colspan="5"><input type="text" name="add1" size="75" maxlength=200></td>
    </tr>
    <tr>
      <td Class="addedit">Add2</td>
      <td  colspan="5" ><input type="text" name="add2" size="75" maxlength=200></td>
    </tr>
</c:otherwise>
</c:choose>
    <tr>
      <td  colspan="6" >
        <p align="center">
		<input type="button" value="New" Class="submitButton" name="new" onclick="NewRecord()">
		<input type="button" value="Save" Class="submitButton" name="save" onclick="SaveRecord()">
		<input type="button" value="Delete" Class="submitButton" name="delete" onclick="DeleteRecord()">
		<input type="button" value="Search" Class="submitButton" name="search" onclick="SearchRecord()">
		<input type="reset"  Class="submitButton" value="clear" onclick="NewRecord()">
		<input type="hidden"  name="flag" value="null">
		<input type=hidden name="flag1" >
		</td>
    </tr>    
  </table>
 </center></td></table></center>
</form>
</body>
</html>

<%

session.setAttribute("JNLInvoice",null);
session.setAttribute("JNLInvoiceSize",null);

%>

<%

String valid=request.getParameter("check");
if(valid!=null){
if(request.getParameter("check")!=null){
if(valid.equals("newInvoice")){
 %>
 <script language="JavaScript">
document.ordinv.slno.value="<%=viewObject.getImax()%>";
document.ordinv.invno.focus();
</script>
<%
  }
			if(valid.equals("UpdateCheck")){%>
            <script language="JavaScript">
			 msg=confirm("Record Already Exists. Do you want to Update !");
					if(msg){
						document.ordinv.flag.value="update";
						document.ordinv.submit();
						}
						else
						{
						alert("Operation Cancelled..!");						
						}

		   	</script><%
			}

			if(valid.equals("SaveSuccess")){%>
            		<script language="JavaScript">
			alert("Record Saved Successfully!");
			//history.back();
			//document.ordinv.flag.value="new";
			//document.ordinv.submit();
		   	</script><%
			}
			
			if(valid.equals("UpdateSuccess")){%>
    		<script language="JavaScript">
	          alert("Record Updated Successfully!");
	        </script><%
	}
			

			if(valid.equals("DeletedSuccess")){%>
    		<script language="JavaScript">
	          alert("Record Deleted Successfully!");
	        </script>        
	        <%
	}
			if(valid.equals("DeleteFail")){%>
    		<script language="JavaScript">
	          alert("Record Not Found!");
	        </script>        
	        <%
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
	//document.ordinv.action="index.jsp";
	document.ordinv.submit();

}


function SearchRecord()
{
if (document.ordinv.invno.value=="")
{
  alert("Invalid Invoice Number..!");		
}else  {
            document.ordinv.method="get";
            document.ordinv.flag.value="search";
            document.ordinv.submit();
}

}

function SaveRecord()
	{
	if (document.ordinv.invrno.value=="")
    {
       alert("Please Enter Invoice Reference No !");
          return false;     
    }
	
	
	if (document.ordinv.supinvno.value=="")
    {
       alert("Please Enter Supplier Invoice No !");
          return false;     
    }
    
    if (document.ordinv.sname.value=="")
    {
        alert("Please Choose Supplier !");
          return false;            
    }  
    
    if (document.ordinv.invno.value=="")
    {
        alert("Please Enter Invoice No !");
          return false;            
    }  
    
    if (document.ordinv.invno.value != document.ordinv.supinvno.value)
    {
        alert("Invoice No & Supplier Invoice No doesn't match !");
          return false;            
    }
     
	 document.ordinv.flag.value="Save";
	 document.ordinv.submit();
}	 
	 
	 
	 
	
function DeleteRecord(){
document.ordinv.method="get";

if (document.ordinv.invno.value=="")
{
  alert("Invalid Invoice Number..!");		
}else  {
                  msg=confirm("Are You Sure To Delete");
					if(msg){
						document.ordinv.flag.value="delete";
						document.ordinv.submit();
						}
						else
						{
						alert("Operation Cancelled..!");						
						document.ordinv.flag.value="new";  
						document.ordinv.submit();
						}
}

}


function FindValue(val)
{
if(val=="Invoice")
{
if(document.ordinv.invrno.value=="")
{
   alert("Click New button before Find !");
}else{
   winpopup=window.open("search_nmvc.jsp?flag="+val,"popup","height=400,width=600,top=100,left=100,status=yes,menubar=no,scrollbars=yes,toolbar=no");
}
}else {
   winpopup=window.open("search_nmvc.jsp?flag="+val,"popup","height=400,width=600,top=100,left=100,status=yes,menubar=no,scrollbars=yes,toolbar=no");
}

}

function invamt_val() {
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

