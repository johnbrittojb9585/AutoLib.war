<%@ include file="/Tree/demoFrameset.jsp"%>
<%@ page language="java" session="true" buffer="12kb" import="java.sql.*" import="java.util.*"%>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/button_css.css" />
<jsp:useBean id="bean" scope="request" class="Lib.Auto.Budget.BudgetBean"/>
<%@ page import="java.util.Calendar" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat"%>

<!--
//////////////////////////////////////DATABASE CONNECTION//////////////////////////////////////////////////////////////////////// -->
<%
//
//   Filename: Index.jsp
//   Form name:binding
//%>
<!--
//////////////////////////////////////FORM CONTROLS//////////////////////////////////////////////////////////////////////// -->
<html>
<head>
<meta charset="ISO-8859-1">
<title>AutoLib</title>
<link rel="stylesheet" type="text/css" href="/AutoLib/style.css">
<!-- <script language="javascript" src="/AutoLib/popcalendar.js"></script> -->
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-2.2.4.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-ui.js"></script>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/jquery-ui.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/searchPaymentReport.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/datepicker3.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/datepicker4.js"></script>

<meta http-equiv="pragma" content="no-cache"/>
</head>

<%
	java.util.Date d =new java.util.Date();
	SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	String dateString = sdf.format(d);

%>

<body background="/AutoLib/soft.jpg">
<!-- Style Sheet -->
<form name=Payment method="post" action=./PaymentInfoServlet>

<br><br><br><br>
<h2>Payment&nbsp;Report</h2>
<center>
<br>
<table align="center" class="contentTable" width="45%">
<td>
<table align="center" width="90%">
<tr><td> &nbsp; </td></tr>

<tr>
   
 <div class="search-container">
   <div class="ui-widget">
   
      <td Class="addedit">User Name</td><td>
<!--       <input type="text" name="name" value="ALL" size="30" readonly> -->
      <input type="text" name="name" id="searchUser" class="searchPaymentReport" onkeyup="showUser(this.value);" size="30" >
      
    </div>
</div>      
           
      <input type="button" Class="submitButton" value="Find" name="name_find" onclick="FindValue('name')"></td>
    </tr>
    
<tr>

<div class="search-container">
   <div class="ui-widget">
   
<td Class="addedit">Designation</td>
<td> <input type="text" size="52" name="desig" id="searchDesig" class="searchPaymentReport" onkeyup="showDesig(this.value);"></td>
<!-- <td><select size="1" name="desig" style="width: 380px"> -->
<!-- <option value="ALL">ALL</option> -->
<%-- <c:if test="${DesigSearchList ne null }" > --%>
<%-- <c:forEach items="${DesigSearchList}" var="std" varStatus="loop"> --%>
<%-- <option value="<c:out value="${std.code}"/>"><c:out value="${std.name}" /></option> --%>
<%-- </c:forEach> --%>
<%-- </c:if> --%>
<!-- </select> -->
<!-- </td> -->

   </div>
</div>
</tr>

      
<tr>
<div class="search-container">
   <div class="ui-widget">

<td Class="addedit">Group&nbsp;Name</td>
<td> <input type="text" size="52" name="group" id="searchGroup" class="searchPaymentReport" onkeyup="showGroup(this.value);"></td>
<!-- <td><select size="1" name="group" style="width: 380px"> -->
<!-- <option value="ALL">ALL</option> -->
<%-- <c:if test="${GroupSearchList ne null }" > --%>
<%-- <c:forEach items="${GroupSearchList}" var="std" varStatus="loop"> --%>
<%-- <option value="<c:out value="${std.code}"/>"><c:out value="${std.name}" /></option> --%>
<%-- </c:forEach> --%>
<%-- </c:if> --%>
<!-- </select> -->
<!-- </td> -->

    </div>
</div>
</tr>

<tr>
<div class="search-container">
   <div class="ui-widget">

<td Class="addedit">Course&nbsp;Name</td>
<td> <input type="text" size="52" name="course" id="searchCourse" class="searchPaymentReport" onkeyup="showCourse(this.value);"></td>
<!-- <td><select size="1" name="course" style="width: 380px"> -->
<!-- <option value="ALL">ALL</option> -->
<%-- <c:if test="${CourseSearchList ne null }" > --%>
<%-- <c:forEach items="${CourseSearchList}" var="std" varStatus="loop"> --%>
<%-- <option value="<c:out value="${std.code}"/>"><c:out value="${std.name}" /> - <c:out value="${std.desc}"/></option> --%>
<%-- </c:forEach> --%>
<%-- </c:if> --%>
<!-- </select> -->
<!-- </td> -->
   </div>
</div>
</tr>

<tr>
<div class="search-container">
   <div class="ui-widget">

<td Class="addedit">Dept&nbsp;Name</td>
<td> <input type="text" size="52" name="dept" id="searchDept" class="searchPaymentReport" onkeyup="showDept(this.value);"></td>
<!-- <td><select size="1" name="dept" style="width: 380px"> -->
<!-- <option value="ALL">ALL</option> -->
<%-- <c:if test="${DeptSearchList ne null }"> --%>
<%-- <c:forEach items="${DeptSearchList}" var="std" varStatus="loop"> --%>
<%-- <option value="<c:out value="${std.code}"/>"><c:out value="${std.name}" /></option> --%>
<%-- </c:forEach> --%>
<%-- </c:if> --%>
<!-- </select> -->
<!-- </td> -->
   </div>
</div>
</tr>

  <tr>
    <td Class="addedit">From Date</td>
    <td>
	<%-- <INPUT name=fromdate size=10  onfocus=this.blur(); value="<%=dateString%>"> --%>
	<INPUT name=fromdate size=10 id="datepicker3" value="<%=dateString%>">
				
		 

    To Date
    
	<%-- <INPUT name=todate size=10  onfocus=this.blur(); value="<%=dateString%>"> --%>
	<INPUT name=todate size=10 id="datepicker4" value="<%=dateString%>">
								
		</td> </tr>
		<!-- <tr>
		<td Class="addedit">Pay.Mode</td>
		<td>
	 <select name="Type" size="1" style="width: 125px">
  	  	<option selected value="ALL">ALL</option>
      	<option  value="OVERDUE">OVERDUE</option>
     	<option value="Photocopy">Photocopy</option>
		<option value="Printout">Printout</option>
		<option value="Color Print">Color Print</option>
		<option value="Strip Binding">Strip Binding</option>
		<option value="Stick Binding">Stick Binding</option>
		<option value="Spiral Binding">Spiral Binding</option>
		<option value="Recovery">Recovery</option>
		<option value="Loss of Resource">Loss of Resource</option>
		<option value="Others">Others</option>
 	</select>
		</td></tr> -->
		 <tr><td>&nbsp;</td></tr>
<tr>
	<td></td>
	<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<input type="radio" name="printType" value="pdf" checked>PDF
	<img src="<%= request.getContextPath() %>/iconImages/pdf.png" width="40" height="45" border="0" title="Print PDF">
	
	<input type="radio" name="printType" value="excel">Excel
	 <img src="<%= request.getContextPath() %>/images/xls.gif" width="35" height="40" border="0" title="Print Excel"></a>
	 
</table></td></table></center>

<P align=center>
<input type=button name=Report Class="submitButton" value=Paid onclick=ShowReport()>
<input type=button name=Report Class="submitButton" value=UnPaid onclick=ShowUnPaidReport()>
<input type=Reset name=Clear Class="submitButton" Value=Clear >
<input type=hidden name=flag>
</P>


</form>
</body>
</html>
<!--
//////////////////////////////////////JAVA SERVER PAGES SCRIPT ///////////////////////////////////////////////// -->


<%
ArrayList sc=new ArrayList();
ArrayList pc=new ArrayList();
String message="",info="";
%>

<%
String valid=request.getParameter("check");

if(valid!=null){
	
	
if(request.getParameter("check")!=null){
	if(valid.equals("NoData")){	%>
	  <script >
		alert("No Record Found");
		document.Payment.flag.value="load";
		
		document.Payment.submit();
		</script>

	<%
	}
if(valid.equals("userdetails"))
{
	   sc=(ArrayList)request.getAttribute("MemberDetails");

for(int i=0;i<sc.size();i+=5){
%>
 <script language="JavaScript">
	
	document.Payment.user_no.value="<%=sc.get(i)%>";
	document.Payment.user_name.value="<%=sc.get(i+1)%>";
    document.Payment.user_dept.value="<%=sc.get(i+2)%>";
    document.Payment.user_course.value="<%=sc.get(i+3)%>";        
    document.Payment.flag.value="";
	
</script>
	 <%
}
sc.clear();
}
if(valid.equals("usernotfound"))
{
	%>
	
	 <script language="JavaScript">
	 
	alert("Member Not Found");
	 
	 </script>
	 
<%
}

}
}

%>
<!--
///////////////////////////////////////JAVA SCRIPT USEING CLIENT SIDE///////////////////////////////////////////////// --> 


<script language="javascript">

function FindValue(val)
{
winpopup=window.open("search_nmvc.jsp?flag="+val,"popup","height=400,width=600,top=100,left=100,status=yes,menubar=no,scrollbars=yes,toolbar=no");
}
function home()
{
location.href="/AutoLib/Home.jsp";
}
function Logout()
{
location.href="/AutoLib/index.html";
}


function user()   
{         
var ab=document.Payment.user_no.value;

if(ab=="")
{
alert("Insufficient Data");

}else{

document.Payment.flag.value="user";
document.Payment.submit();

}

}
function ShowReport()
{
document.Payment.flag.value="PaidReport";
document.Payment.submit();
}


function ShowUnPaidReport(){
	document.Payment.flag.value="UnPaidReport";
	document.Payment.submit();
	}



function load(){
	document.Payment.user_no.focus();
	 
		 }	
		 
	

</script>
