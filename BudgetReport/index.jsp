<%
String URole=session.getAttribute("UserRights").toString().trim();
if(URole.equalsIgnoreCase("4") || URole.equalsIgnoreCase("5") || URole.equalsIgnoreCase("6") || URole.equalsIgnoreCase("7"))
{		
	response.sendRedirect("sessiontimeout.jsp");
}
%>

<%@ include file="/Tree/demoFrameset.jsp"%>
<%@ page import="java.util.Calendar"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.text.SimpleDateFormat"%>

<%@ page language="java" session="true" buffer="12kb" import="java.sql.*" import="java.util.*"%>
<link rel="stylesheet" type="text/css"	href="<%= request.getContextPath() %>/css/button_css.css"/>

<jsp:useBean id="BeanObject" scope="request"  class="Lib.Auto.Budget.BudgetBean"  type="Lib.Auto.Budget.BudgetBean">
</jsp:useBean>


<!--
//////////////////////////////////////////////////////////////////FORM CONTROLS//////////////////////////////////////////////////////////////////////// -->
<html>
<head>
<meta charset="ISO-8859-1">
<title>Auto Lib</title>
<link rel="stylesheet" type="text/css" href="/AutoLib/style.css">
<%-- <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-3.1.0.js"></script> --%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-2.2.4.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-ui.js"></script>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/jquery-ui.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/datepicker.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/datepicker2.js"></script>

<!-- <script language="javascript" src="/AutoLib/popcalendar.js"></script> -->
<meta http-equiv="pragma" content="no-cache" />
</head>
<body background="/AutoLib/soft.jpg">
	<%
	java.util.Date d =new java.util.Date();
	SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	String dateString = sdf.format(d);

%>

	<form name=Budget method="post" action=./BudgetReportServlet>
		<br>
		<br>
		<br>
		<h2>Budget&nbsp;Report</h2>

		<table align="center" class="contentTable" width="50%">
			<tr>
				<td>
					<table align="center" width="65%">
						<tr>
							<td>&nbsp;</td>
						</tr>

<tr>
<td Class="addedit" >Bud&nbsp;Name<td colspan=4> <select size="1" name="budcode" style="width: 340px">
<option value="NO">ALL</option>
<c:if test="${budgetSearchList ne null }" >
<c:forEach items="${budgetSearchList}" var="std" varStatus="loop">

<option value="<c:out value="${std.budHead}"/>"><c:out value="${std.budHead}"/></option>

</c:forEach>
</c:if>
</select></td>
</tr>

<tr>
<td Class="addedit" >Dept&nbsp;Name<td colspan=4> <select size="1" name="deptcode" style="width: 340px">
<option value="NO">SELECT</option>
<c:if test="${budgetSearchList ne null }" >
<c:forEach items="${budgetSearchList}" var="std" varStatus="loop">

<option value="<c:out value="${std.deptname}"/>"><c:out value="${std.deptname}"/></option>

</c:forEach>
</c:if>
</select></td>

    
<!-- <td Class="addedit">DocType</td> -->
<!-- <td><select name="doc" size="1" id="doctype" style="width: 120px"> -->
	  
<!-- 	  <option  value="ALL">ALL</option> -->
<!-- 	  <option  value="BOOK">BOOK</option> -->
<!--       <option value="BOOK BANK">BOOK BANK</option> -->
<!-- 	  <option value="NON BOOK">NON BOOK </option> -->
<!-- 	  <option value="REPORT">REPORT</option> -->
<!-- 	  <option value="THESIS">THESIS</option> -->
<!-- 	  <option value="STANDARD">STANDARD</option> -->
<!-- 	  <option value="PROCEEDING">PROCEEDING</option> -->
<!-- 	  <option value="BACK VOLUME">BACK VOLUME</option> -->
	  
	  
<!-- </select></td> -->



</tr>
			

						<tr>
							<td Class="addedit">From Date</td>
<%-- 							<TD><INPUT name=fromdate size=10 onfocus=this.blur(); value="<%=dateString%>">  --%>
							<TD><INPUT name=fromdate size=10 id="datepicker" value="<%=dateString%>"> 
						
				       </td>

							<td Class="addedit">To Date</td>
<%-- 							<TD><INPUT name=todate size=10 onfocus=this.blur(); value="<%=dateString%>">  --%>
							<TD><INPUT name=todate size=10 id="datepicker2" value="<%=dateString%>"> 
							
							</td>
						</tr>
		
						

			
					



					</table>
				</td>
			</tr>
				<tr><td>&nbsp;</td></tr>
			
		</table>
		
		<p align="center">
<input type="button" value="Report" name="Report" Class="submitButton" onClick="Print_Report()">
<input type="button" value="Book Detail" name="Report" Class="submitButton" onClick="detail()">
<!-- <input type="button" value="Statistics" name="Statistics" Class="submitButton"> -->
<input type="button" value="Clear" name="Clear"	Class="submitButton">
<input type="hidden" name="flag">
<input type="hidden" name="flag1">
 </p>
					

	</form>
</body>
<%
String valid=request.getParameter("check");
if(valid!=null){

	if(valid.equals("NoData")){	%>
  <script>
	alert("No Record Found");
	document.Budget.flag.value="load";
	document.Budget.submit();
	
	</script>
	

<%}
}
%>
<script>

function Print_Report(){
	
	
	document.Budget.flag.value="Print";
	document.Budget.flag1.value="Report";
	
	document.Budget.submit();
	
	
}
function detail(){

	document.Budget.flag.value="Detail";
	document.Budget.flag1.value="Report";
	
	document.Budget.submit();
}

</script>




</html>

