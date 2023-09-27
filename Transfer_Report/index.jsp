<html>
<%
String URole=session.getAttribute("UserRights").toString().trim();
if(URole.equalsIgnoreCase("3") || URole.equalsIgnoreCase("4") || URole.equalsIgnoreCase("5") || URole.equalsIgnoreCase("7"))
{		
	response.sendRedirect("sessiontimeout.jsp");
}	
%>
<%@ include file="/Tree/demoFrameset.jsp"%>
<%@ page language="java" session="true" buffer="12kb" 
 import="java.sql.*"
 import="java.util.*" 
 import="Lib.Auto.Transfer_Books.BookTransferBean" 
 import="Lib.Auto.Binding.BindingBean"
  import="Common.Security"
   import="Lib.Auto.Binding_Books.BookBindingBean" %>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/button_css.css" />

 <jsp:useBean id="BeanObject" scope="request" class="Lib.Auto.Transfer_Books.BookTransferBean"
   type="Lib.Auto.Transfer_Books.BookTransferBean"> 

</jsp:useBean>

<head>
<meta charset="ISO-8859-1">
<meta http-equiv="Content-Language" content="en-us">
<meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
<meta name="GENERATOR" content="Microsoft FrontPage 4.0">
<meta name="ProgId" content="FrontPage.Editor.Document">
<title>Transfer&nbsp;Books</title>
<!-- <script language="javascript" src="/AutoLib/popcalendar.js"></script> -->
<link rel="stylesheet" type="text/css" href="/AutoLib/style.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-3.1.0.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-ui.js"></script>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/jquery-ui.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/datepicker.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/datepicker2.js"></script>

</head>
<body background="/AutoLib/soft.jpg"> <!--onload="load()"-->
<!-- <form method="POST" name="Book_Bindings" action="../Transfer_Books/TransferAction"> -->

<form method="POST" name="Transfer_Report" action="../Transfer_Report/Transfer_ReportServlet">
 <br><br><br>								        

<%
ArrayList sc=new ArrayList();
sc=(ArrayList)request.getAttribute("binding");
%>
<h2 >Transfer&nbsp;Reports</h2>

<table align="center" class="contentTable" width="40%">

<tr><td>&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>

<tr><td><table align="center" width="100%">


<tr>
<td Class="addedit">From&nbsp;Date</td>
<%-- <td><input name=fromdate size=15  onfocus=this.blur(); value="<%=Security.CalenderDate()%>"> --%>
<td><input name=fromdate size=15 id="datepicker" value="<%=Security.CalenderDate()%>">

 </td>
		 <td Class="addedit">To&nbsp;Date</td>
<%--         <td><INPUT name=todate size=15  onfocus=this.blur(); value="<%=Security.CalenderDate()%>"> --%>
        <td><INPUT name=todate size=15 id="datepicker2" value="<%=Security.CalenderDate()%>">
				
				</table>
				
				

<table align="center" width="100%">
	   <tr> <td Class="addedit">Dept Name</td>
      <td><SELECT SIZE="1" NAME="dept" style="width:400px">


<OPTION SELECTED VALUE="ALL">ALL</OPTION>
<% 
Iterator it=sc.iterator();
 while(it.hasNext()){
    BindingBean view=(BindingBean) it.next();                                        	
	 int dept_code=view.getCode();
		     String dept_name=view.getName();
		
		     out.print("<option  value="+dept_code+">" +dept_name+"</option>");
		    
		    // out.print("<option>"+dept_name+"</option>");
     }
  %>

              </SELECT>
              </td>
             </tr>
             </table>
             <table align="center" width="100%">
             
    <tr><td Class="addedit">Doc.type</td>
    <td>
    
  <select name="doc" size="1">
      <option  value="ALL">ALL</option>
      <option  value="BOOK">BOOK</option>
      <option value="BOOK BANK">BOOK BANK</option>
	  <option value="NON BOOK">NON BOOK </option>          
	  <option value="REPORT">REPORT</option>
	  <option value="THESIS">THESIS</option>          
	  <option value="STANDARD">STANDARD</option>
	  <option value="PROCEEDING">PROCEEDING</option>	  
	  <option value="BACK VOLUME">BACK VOLUME</option>
</select>
<td Class="addedit">Status
<select name="status" size="1">

      <option value="TRANSFERED">TRANSFERED</option>
      <option value="Re-TRANSFERED">Re-TRANSFERED</option>

</select>
</td>
</table>

<p align="center">
<table>
<tr><td>&nbsp;</td></tr>
	<tr>
	<td></td>
	<td>
	<input type="radio" name="printType" value="pdf" checked>PDF
	<img src="<%= request.getContextPath() %>/iconImages/pdf.png" width="40" height="45" border="0" title="Print PDF">
	
	<input type="radio" name="printType" value="excel">Excel
	 <img src="<%= request.getContextPath() %>/images/xls.gif" width="35" height="40" border="0" title="Print Excel"></a>
	</td>
	</tr>
</table>	
  </p>
  
  </td></table>
  <p align="center">
<!-- <table align="center" width="100%"> -->
<!-- <tr><td >&nbsp;</td><td >&nbsp;</td></tr> -->


<!-- <tr><td><center> -->

      <input type="button" value="Print" name="New" Class="submitButton" onclick="Print()">
<!--       <input type="reset" Class="submitButton" value="Clear" name="clear"> -->
      <input type="button" value="Clear" Class="submitButton" name="clear" onclick="ClearRec()">

<!-- </center></td></tr></table> -->
</p>
  <input type="hidden" name="flag">
  <input type="hidden" name="index">
  
  
</form>
</body>
  


<script language="JavaScript">
	function ClearRec() {

		document.Transfer_Report.flag.value = "load";
		document.Transfer_Report.submit();
	}

	function Print() {
		document.Transfer_Report.flag.value = "print";
		document.Transfer_Report.submit();

	}
</script>
</html>
