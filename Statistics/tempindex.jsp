<%@ page language="java" session="true" buffer="12kb" import="java.sql.*" import="Common.Security" %>
<!--
//////////////////////////////////////DATABASE CONNECTION//////////////////////////////////////////////////////////////////////// -->
<%
//
//   Filename: Index.jsp
//   Form name:sreport
//
%>
<!--
//////////////////////////////////////////////////////////////////FORM CONTROLS//////////////////////////////////////////////////////////////////////// -->
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
<html>
<head>
<title>AutoLib</title>
<script language="javascript" src="/AutoLib/popcalendar.js"></script>
<meta http-equiv="pragma" content="no-cache"/>
</head>
<body bgcolor="#d3d3d3" text=blue >
<b><font color="#800000"><A href='<%= response.encodeURL("/AutoLib/Home.jsp") %>'>Home</a></font></b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<b><font color="#800000"><A href='<%= response.encodeURL("/AutoLib/Logout.jsp") %>'>Logout</a></font></b>
<!-- Style Sheet -->
<h2 align=center style="FILTER: glow(color=white,intensity=10); HEIGHT: 15px; weight: 45"><b><font color="#800000">Book Statistics Report</font></b> </h2>
<!-- Style Sheet -->
<form name=sreport method="get" action=./StatisticsServlet>
    <div align="center">
    <center>
    <table border="1" width="97%" height="216">
      <tr>
        <td width="4%" height="19">
          <p align="left"><b><font color="#800000" size="3">Department</font></b></td>
        <td width="96%" align="left" colspan="2" height="19"><font size="3"><input type="text" name="txtdepartment" readonly="true" size="71">
		<input type="button" value="Find" name="Find_Dept"   onclick="FindValue('Dept')"></font></td>
      </tr>
      <tr>
        <td width="4%" height="19"><b><font color="#800000" size="3">Subject</font></b></td>
        <td width="96%" align="left" colspan="2" height="19"><font size="3"><input type="text" name="txtsubject" readonly="true" size="71">
		<input type="button" value="Find" name="Find_Sub"  onclick="FindValue('Sub')"></font></td>
      </tr>
      <tr>
        <td width="4%" align="left" height="19"><b><font color="#800000" size="3">Publisher</font></b></td>
        <td width="96%" align="left" colspan="2" height="19"><font size="3"><input type="text" name="txtpublisher" readonly="true"  size="71">
		<input type="button" value="Find" name="Find_Pub" onclick="FindValue('Pub')"></font></td>
      </tr>
      <tr>
        <td width="4%" align="left" height="19"><b><font color="#800000" size="3">Supplier</font></b></td>
        <td width="96%" align="left" colspan="2" height="19"><font size="3"><input type="text" name="txtsupplier" readonly="true" size="71">
		<input type="button" value="Find" name="Find_Sup"  onclick="FindValue('Sup')"></font></td>
      </tr>
      <tr>
        <td width="18%" align="left" height="20">
          <p align="left"><b><font color="#800000" size="3">
		  <input type="radio" value="V1" checked name="R1">Recd
          Date&nbsp;</font></b></p>
        </td>
        <td width="41%" align="left" height="20">
          <p align="left"><b><font color="#800000" size="3">From
		 	<INPUT name=recfrom size=15  onfocus=this.blur(); value="<%=Security.CalenderDate()%>">
				<SCRIPT language=javascript>
						if (!document.layers) {
						document.write("<input type=button onclick='popUpCalendar(this,sreport.recfrom, \"dd-mm-yyyy\",\"<%=Security.CalenderDate()%>\")'value='...' style='font-size:10 px'>")
						}
				</SCRIPT>
</td>
        <td width="41%" align="left" height="20">
          <b><font color="#800000" size="3">To
    <INPUT name=recto size=15  onfocus=this.blur(); value="<%=Security.CalenderDate()%>">
				<SCRIPT language=javascript>
						if (!document.layers) {
						document.write("<input type=button onclick='popUpCalendar(this,sreport.recto, \"dd-mm-yyyy\",\"<%=Security.CalenderDate()%>\")'value='...' style='font-size:10 px'>")
						}
				</SCRIPT>
		 </td>
      </tr>
      <tr>
        <td width="27%" align="left" height="11"><b><font color="#800000" size="3">
		<input type="radio"  value="V2" name="R1">Access
          Number</font></b></td>
        <td width="25%" align="left" height="11"><b><font color="#800000" size="3">From</font></b><font size="3">
		<input type="text" name="txtfromacc" size="26"></font></td>
        <td width="57%" align="left" height="11"><b><font color="#800000" size="3">To</font></b><font size="3">
		<input type="text" name="txttoacc" size="26"></font></td>
      </tr>
      <tr>
        <td width="20%" align="left" height="17"><b><font color="#800000" size="3">Report
          Type</font></b></td>
        <td width="96%" align="left" height="17" colspan="2"><b><font face="Arial" size="7"><select size="1" name="reporttype" >
            <option >Department</option>
            <option>Subject</option>
            <option>Publisher</option>
            <option>Department & Subject</option>
            <option>Subject & Department</option>
            <option>Supplier</option>
          </select></font></b></td>
	 </tr>
	 <tr>
        <td width="20%" align="left" height="17"><b><font color="#800000" size="3">Document
          Type</font></b></td>
        <td width="96%" align="left" height="17" colspan="2"><b><font face="Arial" size="7"><select size="1" name="doctype" >
            	<OPTION VALUE="BOOK">BOOK</OPTION>
                <OPTION VALUE="THESIS">THESIS</OPTION>
                <option value="BACK VOLUME">BACK VOLUME</option>
                <option value="PROCEEDING">PROCEEDING</option>
                <option value="REPORT">REPORTS</option>
                <option value="NON BOOK">NON BOOK</option>
		<option value="STANDARD">STANDARD</option>
		<option value="REFERENCE">REFERENCE</option>
                <option value="ALL" selected>ALL</option>

          </select></font></b></td>
	 </tr>

      <tr>
        <td width="102%" align="left" height="17" colspan="3">
          <p align="center"><font size="3">
		  <input type="bu" value="Statistics" name="Statistics" onclick=check_status("statis")>
		  <input type="button" value="Details" name="Details" onclick=check_status("detail")>
		  <input type="button" value="Clear"  onclick="clear_text()">
		  <input type="hidden" name="subno">
		  <input type="hidden" name="supno">
		  <input type="hidden" name="deptno">
		  <input type="hidden" name="pubno">
		  <input type="hidden" name="hid">
		  <input type="hidden" name="from_date">
		  <input type="hidden" name="to_date">
          </td>
      </tr>
    </table>
    </center>
  </div>
  <p>&nbsp;</p>
  <p>&nbsp;</p>
  <p>&nbsp;</p>
  <p>&nbsp;</p>
  <p>&nbsp;</p>

<script language="JavaScript">
function check_status(val)
{
if(check())
{
document.sreport.method="post";
document.sreport.hid.value=val;
document.sreport.submit();
}
else
{
alert("Insufficient Data..!");
window.history.back();
}
}
if(check())
{
if(document.sreport.R1[0].checked==true)
{
 if (from_check && to_check) return true;
 else return false;
}

if(document.sreport.R1[1].checked==true)
{   if(document.sreport.txtfromacc.value=="" || document.sreport.txttoacc.value=="")
	return false;
	else return true;
}
}
function clear_text()
{
document.sreport.txtdepartment.value="";
document.sreport.txtpublisher.value="";
document.sreport.txtsupplier.value="";
document.sreport.txtsubject.value="";
document.sreport.txtfromacc.value="";
document.sreport.txttoacc.value="";

}
function FindValue(val){
winpopup=window.open("search.jsp?flag="+val ,"popup","height=400,width=600,left=100,top=100,scrollbars=yes,toolbar=no,status=yes,menubar=no");
}
</script>

</form>
</body>

</html>
