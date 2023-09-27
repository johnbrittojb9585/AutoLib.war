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
<title>AutoLib</title>
<script language="javascript" src="/AutoLib/popcalendar.js"></script>
<link rel="stylesheet" type="text/css" href="/AutoLib/style.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-2.2.4.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-ui.js"></script>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/jquery-ui.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/datepicker.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/datepicker2.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/searchGateRegister.js"></script>
<meta http-equiv="pragma" content="no-cache"/>
</head>
<body background="/AutoLib/soft.jpg"><!--onload="opt(1)"-->
<%
	java.util.Date d =new java.util.Date();
	SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	String dateString = sdf.format(d);
%>


<!-- Style Sheet -->
<form name="Library_Login" method="Post" action=./LibraryLoginServlet>
<br><br><br>

<h2>Library&nbsp;Login</h2>
  <div align="center">
    <center>
  <table align="center" class="contentTable" width="60%">
<td>
<table align="center" width="99%">
<br>
<tr>

<td colspan="5" Class="addedit">Member&nbsp;Code&nbsp;&nbsp;
              <INPUT TYPE="text" NAME="txtmembercode" SIZE="15">
           &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;From&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<INPUT name=gate_from size=10 id="datepicker" value="<%=dateString%>" >
				
		&nbsp;&nbsp;To&nbsp;&nbsp;
<%-- 	<INPUT name=gate_to size=10  onfocus=this.blur(); value="<%=dateString%>" > --%>
	<INPUT name=gate_to size=10 id="datepicker2" value="<%=dateString%>" >
				<%-- <SCRIPT language=javascript>
						if (!document.layers) {
						document.write("<input type=button onclick='popUpCalendar(this,Library_Login.gate_to, \"dd-mm-yyyy\",\"<%=dateString%>\")'value='...' style='font-size:10 px'>")
						}
				</SCRIPT> --%>
		 </td>
      </tr>      

  <tr>
  <td colspan="5" Class="addedit">Group&nbsp;&nbsp;&nbsp;
	<input type=text name=Gname size=22 readonly="true" >
	<input type=button name=Find_Group Class="submitButton" Value="Find" onclick="FindValue('Group')">
	
   Department&nbsp;&nbsp;&nbsp;
   <input type=text name=Dname size=27 readonly="true" >&nbsp;<input type=button name=Find_Member Class="submitButton" Value="Find" onclick=FindValue('Department')>
   </td>	
  </tr>
  
  <tr><td>&nbsp;</td></tr>

	
	</table></table></table>

    </center>
  </div>
	    
      

      
      <p align="center">
<input type="radio" name="printType" value="pdf" checked>PDF
	<img src="<%= request.getContextPath() %>/iconImages/pdf.png" width="40" height="45" border="0" title="Print PDF">
	
	<input type="radio" name="printType" value="excel">Excel
	 <img src="<%= request.getContextPath() %>/images/xls.gif" width="35" height="40" border="0" title="Print Excel"></a>
	 <br><br>
    <input type="button" Class="submitButton" value="Statistics" name="gate_statistics" onclick="Statistics_Report()" >
    <input type="button" Class="submitButton" value="Detail" name="gate_Print" onclick="Print_Report()" >
		  <input type="reset" Class="submitButton" value="Clear" name="gate_clear">
		  <input type="hidden" name="flag">	
		  <input type="hidden" name="flagExcel">	  
    </p>
	

<!--     </table></table></table> -->

<!--     </center> -->
<!--   </div> -->

</form>
</body>
</html>

<%
String uchek=request.getParameter("check");
if(uchek!=null){
if(uchek.equals("success")){
	 %>
	<script language="JavaScript">
	   alert("All Logout Successfully !");
	</script><%
	}else  {
  %>
	<script language="JavaScript">
	   alert("No User to Logout !");
	</script><%
  }
}
%>

<script language="javascript">

function home()
{
location.href="/AutoLib/Home.jsp";
}

function Logout()
{
location.href="/AutoLib/index.html";
}
function Statistics_Report()
{
	
	//alert(document.Library_Login.gate_from.value);
			document.Library_Login.flag.value="Statistics_Report";
			document.Library_Login.method="post";
			document.Library_Login.submit();
}
function Print_Report()
{
			document.Library_Login.flag.value="Gate_Report";
			document.Library_Login.method="post";
			document.Library_Login.submit();
}



function FindValue(val)
{
winpopup=window.open("search.jsp?flag="+val,"popup","height=400,width=600,left=100,top=100,scrollbars=yes,toolbar=no,status=yes,menubar=no");
}


</script>


