<%@ include file="/Tree/demoFrameset.jsp"%>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/button_css.css" />
<%@ page language="java" session="true" buffer="12kb" import="java.sql.*"  import="Common.Security" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat"%>
<script language="javascript" src="/AutoLib/popcalendar.js"></script>
<%@ page language="java" errorPage="/Error/ErrorPage.jsp" session="true" buffer="12kb" import="java.sql.*,java.util.*"%>


<%
  String check_date=request.getParameter("check");
  if(check_date!=null)
  {
  if(check_date.equals("date"))
  {
  %>
  <script language="javascript">
  alert("From date is greater than or equal to To date!...");
  </script>
  <%
  }
  check_date=null;
  }
  %>
  
  <%
	java.util.Date d =new java.util.Date();
	SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	String dateString = sdf.format(d);
	String Message=null;

%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>AutoLib</title>
<!-- <script language="javascript" src="/AutoLib/popcalendar.js"></script> -->
<meta http-equiv="pragma" content="no-cache"/>
<link rel="stylesheet" type="text/css" href="/AutoLib/style.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-2.2.4.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-ui.js"></script>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/jquery-ui.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/searchUniqueTitle.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/datepicker.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/datepicker2.js"></script>

</head>

<form name="sreport" method="get" action=./UniqueTitleServlet>
<br>
<h2>Unique Title Report</h2>

<table align="center" class="contentTable" width="50%">
<tr>
<td>
<table align="center" width="95%">
<tr><td>&nbsp;</td></tr>

<div class="search-container">
   <div class="ui-widget">

      <tr><td Class="addedit">Department</td>
<!--        <td> <input type="text" name="txtdepartment" readonly="true" value="ALL" size="55"> -->
       <td> <input type="text" name="txtdepartment"  value="ALL" id="searchDept" class="searchUniqueTitleReport" onkeyup="showDept(this.value);" size="55">
       
     </div>
</div>       
        <input type="button" Class="submitButton" value="Find" name="Find_Dept"   onclick="FindValue('Dept')"></td>
</tr>

<div class="search-container">
   <div class="ui-widget">

      <tr>
        <td Class="addedit">Subject</td>
        <td> <input type="text" name="txtsubject"  value="ALL" id="searchSubject" class="searchUniqueTitleReport" onkeyup="showSubject(this.value);" size="55">
        
    </div>
</div>        
		<input type="button" Class="submitButton" value="Find" name="Find_Sub"  onclick="FindValue('Sub')"></td>
      </tr>
      
      
     </table>
     
     
     
     <table align="center" width="90%">
<tr><td> &nbsp; </td></tr>

      <tr>
      <td  Class="addedit"> <input type=radio value=byaccno name=acc onclick=change()>By&nbsp;Acc.&nbsp;No.</td>
      <td  Class="addedit">From</td><td> <input type="text" name="From_Accno" size="17" maxlength=10></td>
      <td  Class="addedit">To</td><td> <input  type="text" name="To_Accno" size="17" maxlength=10></td>
      </tr>
      
      <tr>
        <td  Class="addedit"><input type=radio value=bydate name=acc1 onclick=changes() checked>By Date</td>
        <td  Class="addedit">From</td><td>
<%-- 	<INPUT name=fromdt size=11  onfocus=this.blur(); value="<%=dateString%>" > --%>
	<INPUT name=fromdt size=11 id="datepicker" value="<%=dateString%>" >
								
				</td>
				
        <td  Class="addedit">To</td><td>
<%-- 	<INPUT name=todt size=11  onfocus=this.blur(); value="<%=dateString%>" > --%>
	<INPUT name=todt size=11 id="datepicker2" value="<%=dateString%>" >
							
				</td>
      </tr>
      
      
      
      <tr>
      <td></td>
      
      <td  Class="addedit">Document</td>
      <td><select name="Type" size="1" style="width: 125px">
  	  <option selected value="ALL">ALL</option>
      <option  value="BOOK">BOOK</option>
      <option value="BOOK BANK">BOOK BANK</option>
	  <option value="NON BOOK">NON BOOK </option>
	  <option value="REPORT">REPORT</option>
	  <option value="THESIS">THESIS</option>
	  <option value="STANDARD">STANDARD</option>
	  <option value="PROCEEDING">PROCEEDING</option>	  
	  <option value="BACK VOLUME">BACK VOLUME</option>
 </select></td>
 
 <td  Class="addedit">Avail.</td>
 <td>
 
 
 <select name="avail" size="1" style="width: 125px">
  	  <option selected value="ALL">ALL</option>
  	  <option value="YES">YES</option>
      <option value="REFERENCE">REFERENCE</option>
      <option value="DISPLAY">DISPLAY</option>            
      <option value="MISSING">MISSING</option>
      <option value="WITHDRAWN">WITHDRAWN</option>
      <option value="LOST">LOST</option>            
      <option value="DAMAGED">DAMAGED</option>
      <option value="ISSUED">ISSUED</option>
      <option value="BINDING">BINDING</option>
	  
	  
	  </select></td></tr>
 
 <tr>
	<td></td>
	<td></td>
	<td colspan="3">
	<input type="radio" name="printType" value="pdf" checked>PDF
	<img src="<%= request.getContextPath() %>/iconImages/pdf.png" width="40" height="45" border="0" title="Print PDF">
	
	<input type="radio" name="printType" value="excel">Excel
	 <img src="<%= request.getContextPath() %>/images/xls.gif" width="35" height="40" border="0" title="Print Excel"></a>
	</td>
	</tr>
      
      <tr>


        <td colspan="5">
        <p align="center">
		  <input type="button" Class="submitButton" value="Print" name="Access_Print" onclick="Print_Report()">
          <input type="reset" Class="submitButton" value="Clear" name="Access_clear" onClick="refreshPage()">
		  
		  
		
		  <input type="hidden" name="flag">
		  <input type="hidden" name="flagExcel">		  
		  <input type="hidden" name="flagNo">
		  <input type="hidden" name="flagNotNumber">
		  
		  <input type="hidden" name="firstStr">
		  <input type="hidden" name="secondStr">
		  <input type="hidden" name="firstNum">
		  <input type="hidden" name="secondNum">
		  
		  
	
		  </p></td></tr></table>
		  
		  
		  
<script language="javascript">

function change(){

document.sreport.acc1.checked=false;

}
function changes(){
	
document.sreport.From_Accno.value="";
document.sreport.To_Accno.value="";
document.sreport.acc.checked=false;
}


function Print_Report()
{
	
	

if(document.sreport.acc.checked==true)
{
	var firstNum = document.sreport.From_Accno.value;
	firstNum = firstNum.replace(/[^0-9]+/ig,"");
	//alert(firstNum);//number
	var firstStr = document.sreport.From_Accno.value;
	firstStr = firstStr.replace(/[0-9]+/ig,"");
	//alert(firstStr);//string value
	var secondNum = document.sreport.To_Accno.value;
	secondNum = secondNum.replace(/[^0-9]+/ig,"");
	//alert(secondNum);//number
	var secondStr = document.sreport.To_Accno.value;
	secondStr = secondStr.replace(/[0-9]+/ig,"");
	//alert(secondStr);//string value

	
if(document.sreport.From_Accno.value=="" || document.sreport.To_Accno.value=="" || firstNum=="" || secondNum=="")
{
alert("Insufficient Data !");
}

else if(firstStr!=secondStr){
	alert("Enter correct Range of AccessNo !");
	
}
else
{
	document.sreport.firstStr.value=firstStr;
	document.sreport.secondStr.value=secondStr;
 	document.sreport.firstNum.value=firstNum;
 	document.sreport.secondNum.value=secondNum;
 	
document.sreport.flagExcel.value="PdfReport";
document.sreport.flag.value="AccessNo_Wise";
document.sreport.submit();
}
}
else
{
if(document.sreport.acc1.checked==true)
{
document.sreport.flagExcel.value="PdfReport";
document.sreport.flag.value="Date_Wise";
document.sreport.submit();
}
}

}


</script>

     
<%
String valid=request.getParameter("check");
if(valid!=null){
if(request.getParameter("check")!=null){
if(valid.equals("RecordNot")){
 %>
 <script language="JavaScript">
alert("Record Not Found");
history.back();
</script><%
}
if(valid.equals("NotValidRange")){
%>
            <script >
	alert("Not A Valid Range");
	history.back();
	</script>
			<%
			}


if(valid.equals("NoData")){	%>
  <script >
	alert("No Record Found");
	</script>

<%}


}
}
%>
<script language="JavaScript">
function home()
{
location.href="/AutoLib/Home.jsp";
}

function Logout()
{
location.href="/AutoLib/index.html";
}


function FindValue(val){
winpopup=window.open("search.jsp?flag="+val ,"popup","height=400,width=600,left=100,top=100,scrollbars=yes,toolbar=no,status=yes,menubar=no");
}
</script>
</td></tr></table></form></html>