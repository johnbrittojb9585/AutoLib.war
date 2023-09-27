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
<%@ page import="java.util.Calendar" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat"%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored ="false" %>

<!--
//////////////////////////////////////////////////////////////////FORM CONTROLS//////////////////////////////////////////////////////////////////////// -->
<html>
<head>
<title>AutoLib</title>
<script language="javascript" src="/AutoLib/popcalendar.js"></script>
<script language="javascript" src="/AutoLib/datetimepicker_css.js"></script>
<link rel="stylesheet" type="text/css" href="/AutoLib/style.css">
<meta http-equiv="pragma" content="no-cache"/>
</head>
<body background="/AutoLib/soft.jpg" >

<form method="post" name="ordinv" action=./JnlInvoiceReportServlet>
<br><br><br>
<h2 >Journal Invoice Report</h2>
<center>
<table align="center" class="contentTable" width="58%">
<td>
<table align="center" width="95%">
<tr><td> &nbsp; </td></tr>

<tr>         
      <td Class="addedit" colspan="2">Inv.Ref-No&nbsp;&nbsp;&nbsp;&nbsp;
      <input type="text" name="invno" size="17" readonly="true">
      <input type="button" value="Find" Class="submitButton" onclick=FindValue("INVOICE")></td>   	 
      <td Class="addedit">Supplier</td>
      <td><input type="hidden" name="sup_code">
      <input type="text" name="sname" size="30" readOnly=true>
      </td>              
</tr>

<tr>
<td Class="addedit">Payment.Flag</td>
      <td ><select name="status" id="status" >
      <option VALUE="ALL" selected>ALL</option>
      <option VALUE="PENDING">PENDING</option>
      <option VALUE="COMPLETED">COMPLETED</option>      
      </td>
</tr>

    <tr>
      <td  colspan="6" >
        <p align="center">
		<input type="button" value="Print" Class="submitButton" name="search" onclick="PrintRecord()">
		<input type="Reset"  Class="submitButton" value="clear">
		<input type="hidden"  name="flag" value="null">
		</td>
    </tr>    
  </table>
 </center></td></table></center>
</form>
</body>
</html>

<%

String valid=request.getParameter("check");
if(valid!=null){
if(request.getParameter("check")!=null){

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


function PrintRecord()
{
/*if (document.ordinv.invno.value=="")
{
  alert("Invalid Order Number..!");		
}else  {*/
            document.ordinv.method="get";
            document.ordinv.flag.value="Print";
            document.ordinv.submit();
//}

}

function FindValue(val)
{
winpopup=window.open("search_nmvc.jsp?flag="+val,"popup","height=400,width=600,top=100,left=100,status=yes,menubar=no,scrollbars=yes,toolbar=no");
}


</script>

