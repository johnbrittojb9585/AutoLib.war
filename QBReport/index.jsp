<%@ include file="/Tree/demoFrameset.jsp"%>
<%@ page language="java" session="true" buffer="12kb" import="java.sql.*,java.util.*"%>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/button_css.css" />
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
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-2.2.4.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-ui.js"></script>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/jquery-ui.css"/>


<meta http-equiv="pragma" content="no-cache"/>
</head>
<body background="/AutoLib/soft.jpg"><!--onload="opt(1)"-->
<%
	java.util.Date d =new java.util.Date();
	SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	String dateString = sdf.format(d);
%>


<!-- Style Sheet -->
<form name="qbreport" method="Post" action=./QBReportServlet>
<br><br><br>

<h2>QB&nbsp;Report</h2>
  <div align="center">
    <center>
  <table align="center" class="contentTable" width="60%">
<td>
<table align="center" width="99%">
<br>

	<div class="search-container">
	<div class="ui-widget">

<tr>
<td Class="addedit">QB Code<td> <input type=text name=qcode size=18  maxlength=5 onKeyUp="return keywords_code_val();" ></td>
<td Class="addedit" >Sub&nbsp;Name<td colspan=3> 
<!-- <input type="text" size="46" name="subname" id="searchSubject" class="searchQBank" onkeyup="showSubject(this.value);"> -->

<select size="1" name="subname" style="width: 300px">
<option value="NO">SELECT</option>
<c:if test="${QBSubjectSearchList ne null }" >
<c:forEach items="${QBSubjectSearchList}" var="std" varStatus="loop">
<option value="<c:out value="${std.desc}"/>"><c:out value="${std.desc}"/></option>
</c:forEach>
</c:if>
</select>

</td>
</tr>

    </div>
</div>

<tr>
<td Class="addedit" >Sub&nbsp;Code<td> <select size="1" name="subcode" style="width: 125px">
<option value="NO">SELECT</option>
<c:if test="${QBSubjectSearchList ne null }" >
<c:forEach items="${QBSubjectSearchList}" var="std" varStatus="loop">
<option value="<c:out value="${std.name}" />"><c:out value="${std.name}" /></option>
</c:forEach>
</c:if>
</select></td>

<td Class="addedit" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Year<td><input type=text name=qyear size=5 maxlength="15" style="width: 85px"> 
</tr>

<div class="search-container">
	<div class="ui-widget">

<tr>
<td Class="addedit" >Dept&nbsp;Name<td colspan="3"> 
<!-- <input type="text" size="46" name="dname" id="searchDept" class="searchQBank" onkeyup="showDept(this.value);"> -->

<select size="1" name="dname"  style="width: 300px">
<option value="NO">SELECT</option>
<c:if test="${departmentSearchList ne null }" >
<c:forEach items="${departmentSearchList}" var="std" varStatus="loop">
<option value="<c:out value="${std.code}"/>"><c:out value="${std.name}" /></option>
</c:forEach>
</c:if>
</select>

</td></tr>

    </div>
</div>

<div class="search-container">
	<div class="ui-widget">

<tr>
<td Class="addedit" >Course&nbsp;Name<td colspan="3"> 
<!-- <input type="text" size="46" name="qcourse" id="searchCourse" class="searchQBank" onkeyup="showCourse(this.value);"> -->

<select size="1" name="qcourse" style="width: 300px">
<option value="NO">SELECT</option>
<c:if test="${courseSearchList ne null }" >
<c:forEach items="${courseSearchList}" var="std" varStatus="loop">
<option value="<c:out value="${std.code}"/>"><c:out value="${std.name}" /> - <c:out value="${std.desc}"/></option>
</c:forEach>
</c:if>
</select>

</td></tr>

   </div>
</div>
  
  <tr><td>&nbsp;</td></tr>
		
	
	</table></table></table>

    </center>
  </div>
	    
      <p align="center">
        <input type="radio" name="printType" value="pdf" checked>PDF
	<img src="<%= request.getContextPath() %>/iconImages/pdf.png" width="40" height="45" border="0" title="Print PDF">
	<input type="radio" name="printType" value="excel">Excel
	<img src="<%= request.getContextPath() %>/images/xls.gif" width="35" height="40" border="0" title="Print Excel">
	 <br><br>
<!--     <input type="button" Class="submitButton" value="Statistics" name="gate_statistics" onclick="Statistics_Report()" > -->
    
	<P align=center>
<input type=button name=Report Class="submitButton" value="Report" onclick="ShowReport()">
<input type=Reset name=Clear Class="submitButton" Value=Clear >
		  <input type="hidden" name="flag">	  
    </p>
	

<!--     </table></table></table> -->

<!--     </center> -->
<!--   </div> -->

</form>
</body>
</html>



<script language="javascript">

function home()
{
location.href="/AutoLib/Home.jsp";
}

function Logout()
{
location.href="/AutoLib/index.html";
}

function ShowReport()
{
	
			document.qbreport.flag.value="Report";
			document.qbreport.submit();
}




function FindValue(val)
{
winpopup=window.open("search.jsp?flag="+val,"popup","height=400,width=600,left=100,top=100,scrollbars=yes,toolbar=no,status=yes,menubar=no");
}


</script>


	