<%@ include file="/Tree/demoFrameset.jsp"%>
<%@ page language="java" session="true" buffer="12kb" import="java.sql.*,java.util.*"%>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/button_css.css" />
<jsp:useBean id="bean" scope="page" class="Lib.Auto.Report.reportbean"/>
<%@ page import="java.util.Calendar" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat"%>


<!--
//////////////////////////////////////////////////////////////////FORM CONTROLS//////////////////////////////////////////////////////////////////////// -->
<html>
<head>
<meta charset="ISO-8859-1">
<title>AutoLib</title>
<!-- <script language="javascript" src="/AutoLib/popcalendar.js"></script> -->
<link rel="stylesheet" type="text/css" href="/AutoLib/style.css">
<%-- <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-3.1.0.js"></script> --%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-2.2.4.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-ui.js"></script>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/jquery-ui.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/searchCounterReport.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/datepicker.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/datepicker2.js"></script>

<meta http-equiv="pragma" content="no-cache"/>
</head>
<body>
<%
	java.util.Date d =new java.util.Date();
	SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	String dateString = sdf.format(d);
%>


<FORM METHOD="get" ACTION="./CounterReportsAll" NAME="query">

<p align="center">
<br>
<h2>COUNTER REPORTS</h2>
</p>



    <div align="center">

  <table align="center" class="contentTable" width="55%">
 <tr>
<td>
<table align="center" width="100%">
<tr><td> &nbsp; </td></tr>

         
         
         <tr>
         	<td Class="addedit"><INPUT TYPE="radio" VALUE="curIssue" NAME="reporttype" checked>CurrentIssue</td>
            <td Class="addedit"><INPUT TYPE="radio" VALUE="Issue" NAME="reporttype">Issue</td>
            <td Class="addedit"><INPUT TYPE="radio" VALUE="Return" NAME="reporttype">&nbsp;Return</td>
            <td Class="addedit"><INPUT TYPE="radio" VALUE="Renewal" NAME="reporttype">Renewal</td>
            <td Class="addedit"><INPUT TYPE="radio" VALUE="Reserve" NAME="reporttype">&nbsp;Reserve</td>
            <td Class="addedit"><INPUT TYPE="radio" VALUE="Resreminder" NAME="reporttype">Res.Reminder&nbsp;</td>
            <td Class="addedit"><INPUT TYPE="radio" VALUE="Duereminder" NAME="reporttype">Due Reminder</td>
            <td Class="addedit"><INPUT TYPE="radio" VALUE="Fine" NAME="reporttype">&nbsp;Fine</td>
          </tr>
          <tr><td> &nbsp; </td></tr>
          <tr><td colspan=14>----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------</td></tr>
       </table>
       <br>
       
       <table align="center">
  
          <tr>
          
  <div class="search-container">
        <div class="ui-widget">
          
            <td Class="addedit">Member Code</td>
            <td><INPUT TYPE="text" NAME="txtmembercode" id="searchMember" class="searchCounterReport" onkeyup="showMember(this.value);" SIZE="16"></td>
            
      </div>
  </div>            
            
            
            <td Class="addedit">DocType</td>
            <td><SELECT SIZE="1" NAME="txtdoctype" style="width: 120px">

				<option value="ALL" selected>ALL</option>
				<OPTION VALUE="BOOK">BOOK</OPTION>
                <OPTION VALUE="THESIS">THESIS</OPTION>
                <option value="BOOK BANK">BOOK BANK</option>
                <option value="BACK VOLUME">BACK VOLUME</option>
                <option value="PROCEEDING">PROCEEDING</option>
                <option value="REPORT">REPORTS</option>
                <option value="NON BOOK">NON BOOK</option>
		        <option value="STANDARD">STANDARD</option>
                </SELECT></td>
          </tr>
          <tr>
          
     <div class="search-container">
        <div class="ui-widget">
          
            <td Class="addedit">Access No</td>

            <td Class="addedit"><INPUT TYPE="text" NAME="txtaccessno" id="searchAccessNo" class="searchCounterReport" onkeyup="showAccessNo(this.value);" SIZE="16"></td>
            <td Class="addedit">Year</td>
			<td><select name="Year" size="1">
				<option value="ALL" selected >.....</option>
					<option value="1" >I</option>
					<option value="2">II</option>
					<option value="3">III</option>
					<option value="4">IV</option>
					<option value="5">V</option>
					<option value="6">pass out</option>
				</select></td>
         </div>
     </div>       
            
          <tr>
            <td Class="addedit">Date From</td>
<td>
<%--      <INPUT name=txtfdate size=10  onfocus=this.blur(); value="<%=dateString%>"> --%>
     <INPUT name=txtfdate size=10 id="datepicker" value="<%=dateString%>">
				
				</td>

            <td Class="addedit">Date To</td>
<td>
<%--      <INPUT name=txttdate size=10  onfocus=this.blur(); value="<%=dateString%>"> --%>
     <INPUT name=txttdate size=10 id="datepicker2" value="<%=dateString%>">
								
				</td>
          </tr>
          <tr>
<div class="search-container">
        <div class="ui-widget">
          
  <td Class="addedit">Group</td>
<!--     <td colspan=3><input type=text name=Gname size=40 readonly> -->
    <td colspan=3><input type=text name=Gname id="searchGroup" class="searchCounterReport" onkeyup="showGroup(this.value);" size=40>&nbsp;
    
    </div>
</div>    
	<input type=button name=Find_Group Class="submitButton" Value="Find" onclick="FindValue('Group')"></td>
	</tr>
	<tr>

<div class="search-container">
        <div class="ui-widget">
	
   <td Class="addedit">Department</td>
    <td colspan=50>  <input type=text name=Dname id="searchDept" class="searchCounterReport" onkeyup="showDept(this.value);" size=40>&nbsp;
    
    </div>
</div>    
    <input type=button name=Find_Member Class="submitButton" Value="Find" onclick=FindValue('Department')>
	   </td>	
          </tr>          
            
<!--             <tr> -->
<!--              <td Class="addedit">Staff Name</td> -->
<!--     <td colspan=50>  <input type=text name=Sname class="searchCounterReport" size=40>&nbsp; -->
<!--      <input type=button name=Find_Member Class="submitButton" Value="Find" onclick=FindValue('staffcode')> -->
<!--             </tr> -->
            
            
       </table><br>
       
       
       
       <table align="center">
          <TR>
            <TD Class="addedit">Order By</TD>
            <TD>
               <SELECT SIZE="1" NAME="order1" ONCHANGE="order11()">
               <OPTION VALUE="NO">--------------</OPTION>
		       <OPTION VALUE="Access_No">Accessno</OPTION>
               <OPTION VALUE="Member_Code">Userid</OPTION>
               <OPTION VALUE="Issue_Date">Issue Date</OPTION>
               <OPTION VALUE="Due_Date">Due Date</OPTION>
               </SELECT>
            </TD>
            <TD>
      	       <SELECT SIZE="1" NAME="order2" ONCHANGE="order22()">
               <OPTION VALUE="NO">--------------</OPTION>
               <OPTION VALUE="Access_No">Accessno</OPTION>
               <OPTION VALUE="Member_Code">Userid</OPTION>
               <OPTION VALUE="Issue_Date">Issue Date</OPTION>
               <OPTION VALUE="Due_Date">Due Date</OPTION>
               </SELECT>
            </TD>
            <TD>
                <SELECT SIZE="1" NAME="order3" ONCHANGE="order33()">
                <OPTION VALUE="NO">--------------</OPTION>
                <OPTION VALUE="Access_No">Accessno</OPTION>
                <OPTION VALUE="Member_Code">Userid</OPTION>
                <OPTION VALUE="Issue_Date">Issue Date</OPTION>
                <OPTION VALUE="Due_Date">Due Date</OPTION></SELECT>
            </TD>
             <TD Class="addedit">Report Type</TD>
            <TD>
              	<SELECT SIZE="1" NAME="report_type" >
                <OPTION VALUE="listing" SELECTED>Listing</OPTION>
                <OPTION VALUE="breakup">Breakup</OPTION>
                <OPTION VALUE="cumulative">Cumulative</OPTION>
                </SELECT>
            </TD>
		</TR>

        </TABLE><br>
       <p align="center">
       <INPUT TYPE="button" Class="submitButton" VALUE="Delete Old Transaction" NAME="B3" onclick="deletetransaction()">&nbsp;
    <INPUT TYPE="button" Class="submitButton" VALUE="Report" NAME="B1" onclick="Print_Report()">&nbsp;
    <INPUT TYPE="button" Class="submitButton" VALUE="Chart" NAME="B1" onclick="Print_Chart()">
<!--     <input type="button" Class="submitButton" value="Statistics" name="Statistics" onclick="Print_Report1()"> -->
 	<INPUT TYPE="reset" Class="submitButton" VALUE="Clear" NAME="B2" >&nbsp;&nbsp;

    <a href="#" onclick="Print_ExcelReport()" >
		<img src="<%= request.getContextPath() %>/images/xls.gif" width="35" height="45" border="0" title="Print Excel"> 
	</a>
    <input type="hidden" name="flagExcel">
    <input type="hidden" name="flagStat">
     <input type="hidden" name="gettodayreport">
    <input type="hidden" name="curIssue" value="NO">		
	</p>
	
       
       </td></tr></table></div></FORM></body></html>

<%
String valid=request.getParameter("check");
if(valid!=null){

if(valid.equals("date")){
%>
            <script >
	alert("From date grater then or  equal to To date");
	</script>
			<%
			}


if(valid.equals("NoData")){	%>
<script >
	alert("No Record Found");
	</script>

<%}



if(valid.equals("RecordNot")){
%>
            <script >
	alert("Record Not Found");
	history.back();
	</script>
			<%
			}
			if(valid.equals("NotValidRange")){
%>
            <script >
	alert("Not A Valid Range");
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

function FindValue(val)
{
winpopup=window.open("search.jsp?flag="+val,"popup","height=400,width=600,left=100,top=100,scrollbars=yes,toolbar=no,status=yes,menubar=no");
}

function Print_ExcelReport()
{

var radios = document.getElementsByName('reporttype');
window.rdValue = null;

for (var i=0; i<radios.length; i++) {
                var aRadio = radios[i];
                if (aRadio.checked) {
                    var foundCheckedRadio = aRadio;
                    rdValue = foundCheckedRadio.value;
                    break;
                }
                else rdValue = 'noRadioChecked';
            }             

 if(rdValue=='curIssue' || rdValue=='Issue' || rdValue=='Return' || rdValue=='Renewal' || rdValue=='Duereminder' || rdValue=='Resreminder' || rdValue=='Reserve' || rdValue=='Fine')
 {
    document.query.flagExcel.value="ExcelReport";
    document.query.submit();
 }
 else
	{
    alert('CurrentIssue is not export to excel'); 
 }
 
}

function Print_Report1()
{		
	var reporttype = document.query.reporttype.value;
	if(reporttype=='curIssue' || reporttype=='Issue' || reporttype=='Return')
		{
		document.query.flagStat.value="statistics";
		document.query.flagExcel.value="statistics";
	    document.query.gettodayreport.value="nothing";
	    document.query.submit()
		}
	else
		{
		alert("Choose CurrentIssue / Issue / Return only");
		}
}


function Print_Report()
{

    document.query.flagExcel.value="PdfReport";
    document.query.gettodayreport.value="nothing";
    document.query.submit();
}
function Print_Chart()
{
	var charttype=document.query.reporttype.value;
	if(charttype=='Issue' || charttype=='Return')
	{
		document.query.flagExcel.value="chart";
	    document.query.gettodayreport.value="nothing";
	    document.query.submit();
	}
	else
		alert("Choose Issue/Return only");
}
function deletetransaction()
{
	var mce=document.query.txtmembercode.value;
	if(mce=="")
		{
		alert("Enter Member Code");
		}
	else
		{
		 	document.query.flagExcel.value="delete";
		    document.query.gettodayreport.value="nothing";
		    document.query.submit();
		}
   
}

</script>


