<%@ include file="/Tree/demoFrameset.jsp"%>
<%@ page language="java" errorPage="/Error/ErrorPage.jsp" session="true" buffer="12kb" import="java.sql.*,java.util.*"%>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/button_css.css" />
<%@ page import="java.util.Calendar" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat"%>

<!--
//////////////////////////////////////////////////////////////////FORM CONTROLS//////////////////////////////////////////////////////////////////////// -->
<html>
<head>
<meta charset="ISO-8859-1">
<title>Auto Lib</title>
<!-- <script language="javascript" src="/AutoLib/popcalendar.js"></script> -->
<link rel="stylesheet" type="text/css" href="/AutoLib/style.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-3.1.0.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-ui.js"></script>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/jquery-ui.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/datepicker.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/datepicker2.js"></script>

<meta http-equiv="pragma" content="no-cache"/>
</head>
<body background="/AutoLib/soft.jpg"><!--onload="opt(1)"-->
<%
	java.util.Date d =new java.util.Date();
	SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	String dateString = sdf.format(d);

%>
<form name=MissingResourceReport method="Post" action=./MissResourceReportServlet>
<br><br><br>

<h2>Missing Resource</h2>
<div align="center">
    <center>
  <table align="center" class="contentTable" width="45%">
<td>
  <table align="center" width="70%">
<br>
      <tr>
      <td Class="addedit">Document&nbsp;Type</td>
      <td><select name="Type" size="1" style="width: 150px">
  	  <option selected value="ALL">ALL</option>
  	  <option value="BOOK">BOOK</option>
      <option value="BOOK BANK">BOOK BANK</option>
	  <option value="NON BOOK">NON BOOK </option>
	  <option value="REPORT">REPORT</option>
	  <option value="THESIS">THESIS</option>
	  <option value="STANDARD">STANDARD</option>
	  <option value="PROCEEDING">PROCEEDING</option>	  
	  <option value="BACK VOLUME">BACK VOLUME</option>
      </select>
      </td>
      </tr>
      
      
  <tr>
  <td Class="addedit">Availability</td>
  <td><select name="availability" size="1" style="width: 150px">
  <option selected value="ALL">ALL</option>
  <option value="LOST">LOST</option>
  <option value="MISSING">MISSING</option>
  <option value="WITHDRAWN">WITHDRAWN</option>
  <option value="DAMAGED">DAMAGED</option>
  </select>
  </td>
  </tr>
  </table>
  
    <table align="center" width="70%">
    <tr></tr>
        <tr>
        <td><input type=radio value=bydate name=acc1 checked><b>By Date</td>
        <td Class="addedit">From</td>
<%--         <td><INPUT name=fromdt size=10  onfocus=this.blur(); value="<%=dateString%>" > --%>
        <td><INPUT name=fromdt size=10 id="datepicker" value="<%=dateString%>" >
	   </td>
        
      <td Class="addedit">To</td>
        <td>
<%-- 	    <INPUT name=todt size=10  onfocus=this.blur(); value="<%=dateString%>" > --%>
	    <INPUT name=todt size=10 id="datepicker2" value="<%=dateString%>" >
    </td>
        </tr>
        <tr></tr><tr></tr><tr></tr>
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

        </table>
          <tr>
          <td colspan="5">
          <p align="center">
          <input type="button" Class="submitButton" value="Print" name="Access_Print" onclick="Print_Report()" >
          <input type="reset" Class="submitButton" value="Clear" name="Access_clear">
          <input type="hidden" name="flag">
		  <input type="hidden" name="flagNo">
		  <input type="hidden" name="flagNotNumber">
          </table></td>
          </center>
          </div>
         </form>
         </body>
         </html>
      
  <%
String valid=request.getParameter("check");
if(valid!=null){

if(valid.equals("date")){
%>
            <script>
	alert("From date grater then or  equal to To date");
	</script>
			<%
			}
if(valid.equals("RecordNot")){
	%>
	            <script>
		alert("Record Not Found");
		history.back();
		</script>
				<%
				}
				
				}%>
                
                
                
                
<script language="javascript">

function home()
{
location.href="/AutoLib/Home.jsp";
}

function Logout()
{
location.href="/AutoLib/index.html";
}
function Print_Report()
{
if(document.MissingResourceReport.acc1.checked==true)
{
document.MissingResourceReport.flag.value="Date_Wise";
document.MissingResourceReport.submit();
}
else
{
alert("Select Date");
}


}

function changes(){
document.MissingResourceReport.acc1.checked=true;

}
</script>
           
			
