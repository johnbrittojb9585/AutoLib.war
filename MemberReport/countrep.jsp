<%@ page language="java" session="true" buffer="12kb" import="java.sql.*"%>
<%@ page import="java.util.Calendar" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat"%>
<html>
<head>
<meta http-equiv="Content-Language" content="en-us">
<meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
<meta name="GENERATOR" content="Microsoft FrontPage 4.0">
<meta name="ProgId" content="FrontPage.Editor.Document">
<title>Counter</title>
<link rel="stylesheet" type="text/css" href="/AutoLib/style.css">
<script language="javascript" src="/AutoLib/popcalendar.js"></script>
</head>
<body background="/AutoLib/soft.jpg" >
>
 <%
	java.util.Date d =new java.util.Date();
	SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	String dateString = sdf.format(d);
 %>
<form method="POST" name="counter" action="./FreqAccessReport" >

<h2>Counter</h2>
  <table align="center">
    <tr>
      <td>Report</td>
      <td><select size="1" name="access">
        <option selected value="FAM">By Frequently Accessed Members</option>
        <option value="LAM">By LeastAccessed Members</option>
        &nbsp;
        </select></td>
    </tr>
    <tr>
      <td>From</td>

      <td><b><font color="#000000">&nbsp; <INPUT name=txtfdate size=15  onfocus=this.blur(); value="<%=dateString%>">
				<SCRIPT language=javascript>
						if (!document.layers) {
						document.write("<input type=button onclick='popUpCalendar(this,counter.txtfdate, \"dd-mm-yyyy\",\"<%=dateString%>\")'value='...' style='font-size:10 px'>")
						}
				</SCRIPT></font></b></td>
				<td></tr>

	<tr>
      <td>To</td>

      <td><b><font color="#000000">&nbsp; <INPUT name=txttodate size=15  onfocus=this.blur(); value="<%=dateString%>">
				<SCRIPT language=javascript>
						if (!document.layers) {
						document.write("<input type=button onclick='popUpCalendar(this,counter.txttodate, \"dd-mm-yyyy\",\"<%=dateString%>\")'value='...' style='font-size:10 px'>")
						}
				</SCRIPT></font></b></td>
				<td></tr>

    <tr>
      <td>
        <p align="right">&nbsp;</p>
      </td>
      <td><input type="button" value="Print" name="print" onclick="Print_Report()">&nbsp;<input type="reset" value="Clear" name="clear"><input type="button" value="Exit" name="exit" onclick=close_window()></td>
    </tr>
  </table>
  <input type=hidden name=name value='<%=request.getParameter("name")%>'>
  <input type=hidden name=dept value='<%=request.getParameter("dept")%>'>
  <input type=hidden name=group value='<%=request.getParameter("group")%>'>
  <input type=hidden name=course value='<%=request.getParameter("course")%>'>
  <input type=hidden name=status value='<%=request.getParameter("status")%>'>

</form>
</body>

<!-- window.onblur=restoreFocus;
function restoreFocus()
{
self.focus();

} -->
</html>
<script language="JavaScript">
function close_window(){
window.close();
}

function Print_Report()
{

			var date=document.counter.txtfdate.value;
		  	var frdt=date.split("-");
		  	var frmdate=frdt[2]+"-"+frdt[1]+"-"+frdt[0];	 
		  	
		  	var date1=document.counter.txttodate.value;
		  	var frdt1=date1.split("-");
		  	var todate=frdt1[2]+"-"+frdt1[1]+"-"+frdt1[0];	 
					if (document.counter.access.value=="FAM")
		
						{
						
	   							location.href="<%=request.getContextPath()%>/frameset?__report=/Report/frequently_acc_member.rptdesign&companyname="+"AutoLib Software Systems"+"&frmdate="+frmdate+"&todate="+todate;
		    	
						}
					else
						
						{
								location.href="<%=request.getContextPath()%>/frameset?__report=/Report/least_acc_member.rptdesign&companyname="+"AutoLib Software Systems"+"&frmdate="+frmdate+"&todate="+todate;
						}

}

</script>

