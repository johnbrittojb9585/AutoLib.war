<%
String URole=session.getAttribute("UserRights").toString().trim();
if(URole.equalsIgnoreCase("2") || URole.equalsIgnoreCase("3") || URole.equalsIgnoreCase("4") || URole.equalsIgnoreCase("5") || URole.equalsIgnoreCase("6") || URole.equalsIgnoreCase("7"))
{		
	response.sendRedirect("sessiontimeout.jsp");
}	
%>
<%@ include file="/Tree/demoFrameset.jsp"%>
<%@ page language="java" session="true" buffer="12kb" import="java.sql.*" import="java.util.*"%>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/button_css.css" />
<jsp:useBean id="bean" scope="page" class="Lib.Auto.Holiday.Holidaybean"/>
<%@ page import="java.util.Calendar" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat"%>

<%
//
//   Filename: Index.jsp
//   Form name:Holiday
//%>


<html>
<head>
<link rel="stylesheet" type="text/css" href="/AutoLib/style.css">
<title>AutoLib</title>
<script language="javascript" src="/AutoLib/popcalendar.js"></script>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/jquery-ui.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-3.1.0.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-ui.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/datepicker.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/datepicker2.js"></script>

</head>
<body background="/AutoLib/soft.jpg"><!--onload="cur_date()"-->
<%
	java.util.Date d =new java.util.Date();
	SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	String dateString = sdf.format(d);

%>

<!-- Style Sheet -->
<FORM name="holiday" method=post  action=./HolidayServlet>
<br><br><br>


<h2>Holiday&nbsp;Master</h2>
<CENTER>
<table align="center" class="contentTable" width="45%">
<td>
<table align="center" width="90%">
<tr><td> &nbsp; </td></tr>
 <TBODY>
  <TR>
    <TD Class="addedit">Leave&nbsp;From</TD>

<TD >
<%-- 	<INPUT name=leavefrom size=15  onfocus=this.blur(); value="<%=dateString%>"> --%>
	<INPUT name=leavefrom size=15 id="datepicker" value="<%=dateString%>">
				
		 </td>
  </tr>
<TR>
    <TD Class="addedit">Leave&nbsp;To</TD>

<TD >
<%-- 	<INPUT name=leaveto size=15  onfocus=this.blur(); value="<%=dateString%>"> --%>
	<INPUT name=leaveto size=15 id="datepicker2" value="<%=dateString%>">
				
		 </td>
  </tr>

  <TR>
    <TD Class="addedit">Remarks</TD>
    <TD ><INPUT  type="text" size=56 name=remarks></TD></TR>
  <TR>
    <TD colspan=4 Class="addedit"><center>

	  <INPUT type=button value=Save name=save Class="submitButton" onclick=SaveRecord()>
	  <INPUT  type=button value="List Of Holidays" Class="submitButton" name=list onclick=ListRecord()>
      <INPUT type=reset value=Clear Class="submitButton" name=clear ><!-- onclick="cur_date()"-->
      <INPUT  type=button value=Delete Class="submitButton" name=delete onclick=DeleteRecord()> 
	  <INPUT   type=hidden name=flag> 
	  <INPUT type=hidden name=all>
	  <INPUT onclick=check() type=checkbox value=""    name=alldelete>Delete All</center></TD></TR></CENTER>
  <DIV></DIV></TBODY></TABLE>

 <%
String valid=request.getParameter("check");
if(valid!=null){
if(request.getParameter("check")!=null){
if(valid.equals("searchHoliday")){
		ArrayList sc=new ArrayList();

		sc=(ArrayList)bean.getAl();
		out.print("<br><br>");
		out.print("<table border=1 bordercolor=#008000 align=center cellspacing=0 >");
		out.print("<tr><th>Leave Date&nbsp;<th>Days&nbsp;<th>Remarks</tr>");
		for(int i=0; i<sc.size();i++){
		%>
		<tr onmouseover=this.style.color='red' onmouseout=this.style.color='black' onclick='show("<%=sc.get(i)%>","<%=sc.get(i+2)%>")'>

		<script language=javascript>
		 document.write("<td>"+"<%=sc.get(i)%>" +"</td>");
		 document.write("<td>"+"&nbsp;&nbsp;&nbsp;<%=sc.get(++i)%>" +"</td>");
		 document.write("<td>"+"&nbsp;<%=sc.get(++i)%>"+"</td>");
		 document.write("</tr>");
 		</script>
		<%
		}

}
if(valid.equals("saveHoliday")){
%>             <script language="JavaScript">
			 alert("Record Inserted/Updated Successfully!");
			 document.holiday.flag.value="none";
			 location.href="index.jsp";
		     </script>
			<%
			}
			if(valid.equals("norecord")){
%>             <script language="JavaScript">
			 alert("Record Not found !");
			 document.holiday.flag.value="none";
			 location.href="index.jsp";
		     </script>
			<%
			}


if(valid.equals("deleteHoliday")){

%>       
            <script language="JavaScript">
			alert("Record Deleted Successfully!");
			document.holiday.flag.value="none";
			location.href="index.jsp";
		   </script>		
			<%
}
			
			
if(valid.equals("Insufficient")){

%>
            <script language="JavaScript">
	    alert("Insufficient Date");
	   </script>
			<%
			}

}
}
%>

  <SCRIPT language="JavaScript">

function home()
{
location.href="/AutoLib/Home.jsp";
}

function Logout()
{
location.href="/AutoLib/index.html";
}

function SaveRecord()
{
if(document.holiday.leavefrom.value=="" && document.holiday.leaveto.value=="" && document.holiday.remarks.value=="")
{alert("Insuffician Data");}
else{document.holiday.flag.value="save";}
document.holiday.method="post";
document.holiday.submit();
}


function DeleteRecord()
{
check();
document.holiday.method="post";
document.holiday.flag.value="delete";
document.holiday.submit();
}

function ListRecord()
{
document.holiday.all.value="";
document.holiday.method="post";
document.holiday.flag.value="list";
document.holiday.submit();
}


function show(val1,val2)
{
document.holiday.remarks.value=val2;
document.holiday.leavefrom.value=val1;
document.holiday.leaveto.value=val1;
document.holiday.leavefrom.focus();
}

function check()
{
if(document.holiday.alldelete.checked==true)
{document.holiday.all.value="all";}
else{document.holiday.all.value="delete";}
}
function cur_date()
{

} 
</SCRIPT>



