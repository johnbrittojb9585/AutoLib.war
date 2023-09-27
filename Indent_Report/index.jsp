<%
String URole=session.getAttribute("UserRights").toString().trim();
if(URole.equalsIgnoreCase("3") || URole.equalsIgnoreCase("4") || URole.equalsIgnoreCase("6") || URole.equalsIgnoreCase("7"))
{		
	response.sendRedirect("sessiontimeout.jsp");
}	
%>
<%@ include file="/Tree/demoFrameset.jsp"%>
<%@ page language="java" errorPage="/Error/ErrorPage.jsp" import="java.io.*" import="java.util.*" import="Lib.Auto.Currency.CurrencyBean" import="Lib.Auto.JNL_Order.JnlorderBean" session="true" buffer="12kb" import="Common.Security"%>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/button_css.css" />
<%@ page import="java.util.Calendar" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat"%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored ="false" %>

<!--
//////////////////////////////////////////////////////////////////FORM CONTROLS//////////////////////////////////////////////////////////////////////// -->
<html>
<head>
<meta charset="ISO-8859-1">
<title>AutoLib</title>
<!-- <script language="javascript" src="/AutoLib/popcalendar.js"></script> -->
<script language="javascript" src="/AutoLib/datetimepicker_css.js"></script>
<link rel="stylesheet" type="text/css" href="/AutoLib/style.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-3.1.0.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-ui.js"></script>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/jquery-ui.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/datepicker.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/datepicker2.js"></script>

<meta http-equiv="pragma" content="no-cache"/>
</head>
<body background="/AutoLib/soft.jpg" >
<%
	java.util.Date d =new java.util.Date();
	SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	String dateString = sdf.format(d);

%>

<form method="post" name="ordinv" action=./IndentDetailReportServlet>
<br><br><br>
<h2 >Indent Details Report</h2>
<center>
<table align="center" class="contentTable" width="58%">
<td>
<table align="center" width="95%">
<tr><td> &nbsp; </td></tr>

<tr>         
     <td Class="addedit"><INPUT TYPE="radio" VALUE="IndentNoWise" NAME="reporttype" id="r1">&nbsp;Indent.No</td>
      <td ><input type="text" name="indentno" size="17" >
      <input type="button" value="Find" Class="submitButton" onclick=FindValue("IndentNo")></td>   	 
</tr>

<tr>
 <td Class="addedit"><INPUT TYPE="radio" VALUE="DateWise" NAME="reporttype" id="r2" checked>&nbsp;Date From</TD>
<%-- <TD><INPUT name=txtfdate size=15  onfocus=this.blur(); value="<%=dateString%>"> --%>
<TD><INPUT name=txtfdate size=15 id="datepicker" value="<%=dateString%>">
				
				</TD>

            <td Class="addedit">Date To</TD>
<%-- <TD><INPUT name=txttdate size=15  onfocus=this.blur(); value="<%=dateString%>"> --%>
<TD><INPUT name=txttdate size=15 id="datepicker2" value="<%=dateString%>">
				
				</TD>
          
</tr>

    <tr>
      <td  colspan="6" >
        <p align="center">
		<input type="button" value="Print" Class="submitButton" name="search" onclick="PrintRecord()">
		<input type="Reset"  Class="submitButton" value="clear">
		<input type="hidden"  name="flag" value="null">
		</td>
    </tr>    
  </table>
 </center></td></table></center>
</form>
</body>
</html>

<%

String valid=request.getParameter("check");
if(valid!=null){
if(request.getParameter("check")!=null){

	if(valid.equals("DeleteFail")){%>
    		<script language="JavaScript">
	          alert("Record Not Found!");
	        </script>        
	        <%
	}
			
  }
  }
%>
<script>

function home()
{
location.href="/AutoLib/Home.jsp";
}

function Logout()
{
location.href="/AutoLib/index.html";
}


function PrintRecord()
{
var rk="";
if(document.getElementById('r1').checked)
{
  rk=document.getElementById('r1').value;  
}

if (rk=="IndentNoWise")
{
  if(document.ordinv.indentno.value=="")   {
	        alert("Please Enter Indent.No !");
  }else {
            document.ordinv.method="get";
            document.ordinv.flag.value="Print";
            document.ordinv.submit();
  }            
}else  {
            document.ordinv.method="get";
            document.ordinv.flag.value="Print";
            document.ordinv.submit();
}

}

function FindValue(val)
{
var rk="";
if(document.getElementById('r1').checked)
{
   rk=document.getElementById('r1').value;  
}

if (rk=="IndentNoWise")
{
   winpopup=window.open("search_nmvc.jsp?flag="+val,"popup","height=400,width=600,top=100,left=100,status=yes,menubar=no,scrollbars=yes,toolbar=no");
}else {
   alert("First select Indent.No option !");
}
}


</script>

