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
<script type="text/javascript" src="<%=request.getContextPath()%>/js/datepicker.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/datepicker2.js"></script>

<meta http-equiv="pragma" content="no-cache"/>
</head>

<%
	java.util.Date d =new java.util.Date();
	SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	String dateString = sdf.format(d);

%>

<body background="/AutoLib/soft.jpg">
<!-- Style Sheet -->
<form name=Payment method="post" action=./FineCollectedServlet>

<br><br><br><br>
<h2>Fine&nbsp;Collected&nbsp;Report</h2>
<center>
<br>
<table align="center" class="contentTable" width="45%">
<td>
<table align="center" width="90%">
<tr><td> &nbsp; </td></tr>

<tr>
   
 <div class="search-container">
   <div class="ui-widget">
   
      <td Class="addedit">Staff Name</td><td>
<!--       <input type="text" name="name" value="ALL" size="30" readonly> -->
      <input type="text" name="Sname" id="searchUser" class="searchPaymentReport" value="ALL" size="30" >
      
    </div>
</div>      
           
      <input type="button" Class="submitButton" value="Find" name="staffcode_find" onclick="FindValue('staffcode')"></td>
    </tr>
    
<tr>



  <tr>
    <td Class="addedit">From Date</td>
    <td>
<%-- 	<INPUT name=fromdate size=10  onfocus=this.blur(); value="<%=dateString%>"> --%>
	<INPUT name=fromdate size=10 id="datepicker" value="<%=dateString%>">
				
		 

    To Date
    
<%-- 	<INPUT name=todate size=10  onfocus=this.blur(); value="<%=dateString%>"> --%>
	<INPUT name=todate size=10 id="datepicker2" value="<%=dateString%>">
								
		</td> </tr>
		<tr>
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
		</td></tr>
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
<input type=button name=Report Class="submitButton" value=Report onclick=ShowReport()>

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
		document.Payment.reload();
		
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


function Showcharteport(){
document.Payment.flag.value="Showcharteport";
document.Payment.submit();
}




function load(){
	document.Payment.user_no.focus();
	 
		 }	
		 
	

</script>
