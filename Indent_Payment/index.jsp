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


<form method="post" name="ordinv" action=./PaymentIndentServlet>
<br><br><br>
<h2 >Indent Payment Entry</h2>
<center>
<table align="center" class="contentTable" width="80%">
<td>
<table align="center" width="98%">
<tr><td> &nbsp; </td></tr>

<c:choose>
<c:when test="${IndentPaymentSize gt 0}">  
<c:forEach items="${IndentPayment}" var="std"  begin="0" end="0">
<tr>
      <td Class="addedit">Payment. RefNo</td>
      <td ><input type="text" name="payrno" size="10" onKeydown="Javascript: if (event.keyCode==13) SearchRecord();" value="<c:out value="${std.paymentno}"/>" maxlength=15 >
      <input type="button" value="Find" Class="submitButton" onclick=FindValue("PaymentNo")></td>
      <td Class="addedit">Payment. SDate</td>
        <TD >
	<INPUT name=paysdate size=8  onfocus=this.blur(); value="<c:out value="${std.paymentsenddate}"/>" >
				<SCRIPT language=javascript>
						if (!document.layers) {
						document.write("<input type=button onclick='popUpCalendar(this,ordinv.paysdate, \"dd-mm-yyyy\",\"<%=dateString%>\")'value='...' style='font-size:10 px'>")
						}
				</SCRIPT>
		 </td>    
      <td Class="addedit">Inv.Ref-No</td>
      <td ><input type="text" name="invno" value="<c:out value="${std.invNo}"/>" size="17" readonly="true">
      <input type="button" value="Find" Class="submitButton" onclick=FindValue("INVOICE")></td>   	   
      </tr>
 <tr>
     <td Class="addedit">Online Trans Details</td>
      <td colspan="5"><input type="text" name="transdetail" value="<c:out value="${std.transdetails}"/>" size="72" maxlength=100></td>
    </tr>  

<tr>
      <td Class="addedit">Cheque. No</td>
      <td ><input type="text" name="chequeno" value="<c:out value="${std.checkno}"/>" size="25" maxlength=15></td>
      <td Class="addedit">Cheque. Date</td>
        <TD >
	<INPUT name=chequedate size=8  onfocus=this.blur(); value="<c:out value="${std.chequedate}"/>">
				<SCRIPT language=javascript>
						if (!document.layers) {
						document.write("<input type=button onclick='popUpCalendar(this,ordinv.chequedate, \"dd-mm-yyyy\",\"<c:out value="${std.chequedate}"/>\")'value='...' style='font-size:10 px'>")
						}
				</SCRIPT>
		 </td>      
      <td Class="addedit">Amount. Sent</td>
      <td colspan="3" ><input type="text" name="totamount" value="<c:out value="${std.netamount}"/>" size="10" maxlength="8" onKeyUp="return invamt_val()">
      </td>
    </tr>             

    <tr>
     <td Class="addedit">Remarks</td>
      <td colspan="5"><input type="text" name="remarks" value="<c:out value="${std.remarks}"/>" size="72" maxlength=100></td>
    </tr>
    <tr>
      <td Class="addedit">Add1</td>
      <td  colspan="5"><input type="text" name="add1" value="<c:out value="${std.add1}"/>" size="72" maxlength=200></td>
    </tr>
    <tr>
      <td Class="addedit">Add2</td>
      <td  colspan="5" ><input type="text" name="add2" value="<c:out value="${std.add2}"/>" size="72" maxlength=200></td>
    </tr>
</c:forEach>
</c:when>
<c:otherwise>

<tr>
      <td Class="addedit">Pmt. RefNo</td>
      <td ><input type="text" name="payrno" size="10" onKeydown="Javascript: if (event.keyCode==13) SearchRecord();" value="<c:out value="${IndentPayment.paymentno}"/>" maxlength=15 >
      <input type="button" value="Find" Class="submitButton" onclick=FindValue("PaymentNo")></td>
      <td Class="addedit">Pmt. SDate</td>
        <TD >
<%-- 	<INPUT name=paysdate size=8  onfocus=this.blur(); value="<%=dateString%>"> --%>
	<INPUT name=paysdate size=8 id="datepicker" value="<%=dateString%>">
				
		 </td>    
      <td Class="addedit">Inv.Ref-No</td>
      <td ><input type="text" name="invno" size="17" readonly="true">
      <input type="button" value="Find" Class="submitButton" onclick=FindValue("INVOICE")></td>   	   
      </tr>
    <tr>
     <td Class="addedit">Online Trans Details</td>
      <td colspan="5"><input type="text" name="transdetail" size="72" maxlength=100></td>
    </tr>  
   
<tr>
      <td Class="addedit">Cheque. No</td>
      <td ><input type="text" name="chequeno" size="25" maxlength=15></td>
      <td Class="addedit">Cheque. Date</td>
        <TD >
<%-- 	<INPUT name=chequedate size=8  onfocus=this.blur(); value="<%=dateString%>"> --%>
	<INPUT name=chequedate size=8 id="datepicker2" value="<%=dateString%>">
				
		 </td>      
      <td Class="addedit">Amount. Sent</td>
      <td colspan="3" ><input type="text" name="totamount" value="0" size="10" maxlength="8" onKeyUp="return invamt_val()">
      </td>
    </tr>             

    <tr>
     <td Class="addedit">Remarks</td>
      <td colspan="5"><input type="text" name="remarks" size="72" maxlength=100></td>
    </tr>
    <tr>
      <td Class="addedit">Add1</td>
      <td  colspan="5"><input type="text" name="add1" size="72" maxlength=200></td>
    </tr>
    <tr>
      <td Class="addedit">Add2</td>
      <td  colspan="5" ><input type="text" name="add2" size="72" maxlength=200></td>
    </tr>
</c:otherwise>
</c:choose>
<c:choose><c:when test="${IndentPaymentSize gt 0}">  
<tr><td><br><br> </td></tr>
<c:forEach items="${IndentPayment}" var="std" varStatus="loop">
<table align="center">
<tr>
     <td Class="addedit">Invoice No</td>
      <td colspan="5"><input type="text" name="sinvno[]" value="<c:out value="${std.invNo}" />" size="20" maxlength=40 readonly=true></td>   
      <td Class="addedit">Amount</td>
      <td  colspan="5"><input type="text" name="samount[]" value="<c:out value="${std.invoiceamount}"/>" size="10" maxlength=10 readonly=true></td>
    </tr>
</c:forEach>
</table>
<tr><td><br><br> </td></tr>
</c:when>
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

session.setAttribute("IndentPayment",null);
session.setAttribute("IndentPaymentSize",null);

%>

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
			if(valid.equals("UpdateCheck")){%>
            <script language="JavaScript">
			 msg=confirm("Record Already Exists. Do you want to Update !");
					if(msg){
					var cboxes = document.getElementsByName('sinvno[]');
                    var len = cboxes.length;
  
                    var content="";
                    var chkitem=0;
                    for (var i=0; i<len; i++) {   
                       chkitem++;
                       content=content+",'"+cboxes[i].value+"'";          
                    }
                        alert("Values: "+content);
                        alert("Count: "+chkitem);         
	                    document.ordinv.flag1.value=content;  
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
	       if(valid.equals("PaidCheck")){%>
	          <script language="JavaScript">
                alert("Already Paid for This Invoice !");
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

function paymentflag()
{
    document.getElementById('pmtflag').disabled = true;
}



function SearchRecord()
{
if (document.ordinv.payrno.value=="")
{
  alert("Invalid Payment Number..!");		
}else  {
            document.ordinv.method="get";
            document.ordinv.flag.value="search";
            document.ordinv.submit();
}

}

function SaveRecord()
	{
	if (document.ordinv.payrno.value=="")
    {
       alert("Please Enter Payment Reference No !");
          return false;     
    }
	
	
	if (document.ordinv.invno.value=="")
    {
       alert("Please choose Invoice Reference No !");
          return false;     
    }
    
    var cboxes = document.getElementsByName('sinvno[]');
    var len = cboxes.length;
  
    var content="";
    var chkitem=0;
    for (var i=0; i<len; i++) {   
       chkitem++;
       content=content+",'"+cboxes[i].value+"'";          
    }
    //alert("Values: "+content);
    //alert("Count: "+chkitem);         
	 document.ordinv.flag1.value=content;      
	 document.ordinv.flag.value="Save";
	 document.ordinv.submit();
}	 
	 
	 
	 
	
function DeleteRecord(){
document.ordinv.method="get";

if (document.ordinv.payrno.value=="")
{
  alert("Invalid Payment Number..!");		
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

if(val!="PaymentNo")
{
  if(document.ordinv.payrno.value=="")
  {
     alert("Click New button before Find !");
  }else{
     winpopup=window.open("search_nmvc.jsp?flag="+val,"popup","height=400,width=600,top=100,left=100,status=yes,menubar=no,scrollbars=yes,toolbar=no");
  }
}else  {
     winpopup=window.open("search_nmvc.jsp?flag="+val,"popup","height=400,width=600,top=100,left=100,status=yes,menubar=no,scrollbars=yes,toolbar=no");
}

}

function invamt_val() {
if((isNaN(document.ordinv.totamount.value))||(document.ordinv.totamount.value == ' ')) {
document.ordinv.totamount.select();
document.ordinv.totamount.value="";
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

