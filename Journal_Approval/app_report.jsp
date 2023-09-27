<%@ page language="java" session="true" buffer="12kb" import="java.sql.*" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat"%>

<!--
//////////////////////////////////////DATABASE CONNECTION//////////////////////////////////////////////////////////////////////// -->
<%
//
//   Filename: Index.jsp
//   Form name:Report_Approve
//
%>
<!--
//////////////////////////////////////////////////////////////////FORM CONTROLS//////////////////////////////////////////////////////////////////////// -->
<html>
<head>
<title>AutoLib</title>
<meta http-equiv="pragma" content="no-cache"/>
<script language="javascript" src="/AutoLib/popcalendar.js"></script>
<link rel="stylesheet" type="text/css" href="/AutoLib/style.css">
</head>
<body background="/AutoLib/soft.jpg">
<%
	java.util.Date d =new java.util.Date();
	SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	String dateString = sdf.format(d);

%>


<!-- Style Sheet -->

<!-- Style Sheet -->
<form name="Report_Approve" method="POST">
<h2>Journal Approval Report</h2>
<div align=right> <A href=""  onclick="window.close()">Back</A></div>
    <div align="center">
    <center>
    <table >
         <tr>
        <td  width=50>
	  Date
        </td>
        <td >
          From
		  <INPUT name=Date_From size=15  onfocus=this.blur(); value="<%=dateString%>">
				<SCRIPT language=javascript>
						if (!document.layers) {
						document.write("<input type=button onclick='popUpCalendar(this,Report_Approve.Date_From, \"dd-mm-yyyy\",\"<%=dateString%>\")'value='...' style='font-size:10 px'>")
						}
				</SCRIPT>
	 </td>
        <td >
         To
		  <INPUT name=Date_To size=15  onfocus=this.blur(); value="<%=dateString%>">
				<SCRIPT language=javascript>
						if (!document.layers) {
						document.write("<input type=button onclick='popUpCalendar(this,Report_Approve.Date_To, \"dd-mm-yyyy\",\"<%=dateString%>\")'value='...' style='font-size:10 px'>")
						}
				</SCRIPT>

        </td>
      </tr>
<tr><td></td><td></td></tr>
<tr><td></td><td></td></tr>
            <tr>
        <td width=50>Status</td>
        <td colspan="2"><input type="radio" value="Pending" name="Pending">PENDING
		<input type="radio" value="APPROVED" name="APPROVED">APPROVED
		<input type="radio" value="CANCEL" name="CANCEL">CANCELLED
		</td>
	 </tr>
<tr><td></td><td></td></tr>
<tr><td></td><td></td></tr>
	 <tr>
        <td ></td>
        <td ><input type="checkbox" name="Sub_Date">Subscription Date</td>
	 </tr>
	

      <tr>
        <td  colspan="3">
          <p align="center">
	<input type="button" value="Print" name="Print"><input type="button" value="Back" name="Back">
  
   </td>
      </tr>
    </table>
    </center>
  </div>

</form>

</body>

</html>
