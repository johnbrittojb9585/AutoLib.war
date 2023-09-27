<%@ include file="/Tree/Searchdemoframeset.jsp"%>
<%@ page language="java" session="true" buffer="12kb" import="java.sql.*" import="java.util.*"%>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/button_css.css" />
<jsp:useBean id="bean" scope="request" class="Lib.Auto.Budget.BudgetBean"/>
<%@ page import="java.util.Calendar" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat"%>

<!--
//////////////////////////////////////DATABASE CONNECTION//////////////////////////////////////////////////////////////////////// -->
<%
//
//   Filename: Index.jsp
//   Form name:binding
//%>
<!--
//////////////////////////////////////FORM CONTROLS//////////////////////////////////////////////////////////////////////// -->
<html>
<head>
<meta charset="ISO-8859-1">
<title>AutoLib</title>
<link rel="stylesheet" type="text/css" href="/AutoLib/style.css">
<!-- <script language="javascript" src="/AutoLib/popcalendar.js"></script> -->
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-2.2.4.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-ui.js"></script>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/jquery-ui.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/searchPaymentReport.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/datepicker.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/datepicker2.js"></script>

<meta http-equiv="pragma" content="no-cache"/>
</head>

<%
	java.util.Date d =new java.util.Date();
	SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	String dateString = sdf.format(d);

%>

<body background="/AutoLib/soft.jpg">
<!-- Style Sheet -->
<form name=suggestion method="post" action=./SuggestionDisplayServlet>

<br><br><br><br>
<h2>Suggestion</h2>
<center>
<br>
<table align="center" class="contentTable" width="45%">
<td>
<table align="center" width="90%">
<tr><td> &nbsp; </td></tr>

<!-- <tr> -->
   
<!--  <div class="search-container"> -->
<!--    <div class="ui-widget"> -->
   
<!--       <td Class="addedit">User Name</td><td> -->

<!--       <input type="text" name="name" id="searchUser" class="searchPaymentReport" onkeyup="showUser(this.value);" size="30" > -->
      
<!--     </div> -->
<!-- </div>       -->
           
<!--       <input type="button" Class="submitButton" value="Find" name="name_find" onclick="FindValue('name')"></td> -->
<!--     </tr> -->
    


		<tr>
		<td Class="addedit">Request For</td>
		<td>
	 <select name="type" size="1" style="width: 125px">
  	  	<option selected value="ALL">ALL</option>
      	<option value="BOOK">BOOK</option>
	 <option value="JOURNAL">JOURNAL</option>
	 <option value="PHOTO COPY">PHOTO COPY</option>
	 <option value="SERVICE">SERVICE</option>
	 <option value="OTHERS">OTHERS</option>
	 <option value="SUGGESTION">SUGGESTION</option>
      	 	
 	</select>
		</td></tr>
		
	<tr>
		<td Class="addedit">Status</td>
		<td>
	 <select name="status" size="1" style="width: 125px">
  	  	<option selected value="ALL">ALL</option>
      	<option value="Pending">Pending</option>
	 <option value="Processing">Processing</option>
	 <option value="Completed">Completed</option>
      	 	
 	</select>
		</td></tr>	
		
		  <tr>
    <td Class="addedit"><input type=checkbox name="checkbox" value="check">From Date</td>
    <td>

	<INPUT name=fromdate size=10 id="datepicker" value="<%=dateString%>">
	
    To Date
    
	<INPUT name=todate size=10 id="datepicker2" value="<%=dateString%>">
								
		</td> </tr>
		
		
		 <tr><td>&nbsp;</td></tr>
	 
</table></td></table></center>

<P align=center>
<input type=button name=Report Class="submitButton" value=Show onclick=ShowSuggestion()>

<input type=Reset name=Clear Class="submitButton" Value=Clear >
<input type=hidden name=flag>
<input type=hidden name=rdate>
</P>
	
</form>
</body>
</html>
<!--
//////////////////////////////////////JAVA SERVER PAGES SCRIPT ///////////////////////////////////////////////// -->


<%
ArrayList sc=new ArrayList();
ArrayList pc=new ArrayList();
String message="",info="";
%>

<%
String valid=request.getParameter("check");

if(valid!=null){
	
	
if(request.getParameter("check")!=null){
	if(valid.equals("NoData")){	%>
	  <script >
		alert("No Record Found");
		document.suggestion.flag.value="load";
		
		document.suggestion.submit();
		</script>

	<%
	}
if(valid.equals("userdetails"))
{
	   sc=(ArrayList)request.getAttribute("MemberDetails");

for(int i=0;i<sc.size();i+=5){
%>
 <script language="JavaScript">
	
	document.suggestion.user_no.value="<%=sc.get(i)%>";
	document.suggestion.user_name.value="<%=sc.get(i+1)%>";
    document.suggestion.user_dept.value="<%=sc.get(i+2)%>";
    document.suggestion.user_course.value="<%=sc.get(i+3)%>";        
    document.suggestion.flag.value="";
	
</script>
	 <%
}
sc.clear();
}
if(valid.equals("usernotfound"))
{
	%>
	
	 <script language="JavaScript">
	 
	alert("Member Not Found");
	 
	 </script>
	 
<%
}

}
}

%>
<!--
///////////////////////////////////////JAVA SCRIPT USEING CLIENT SIDE///////////////////////////////////////////////// --> 


<script language="javascript">

function FindValue(val)
{
winpopup=window.open("search_nmvc.jsp?flag="+val,"popup","height=400,width=600,top=100,left=100,status=yes,menubar=no,scrollbars=yes,toolbar=no");
}
function home()
{
location.href="/AutoLib/Home.jsp";
}
function Logout()
{
location.href="/AutoLib/index.html";
}


function ShowSuggestion()
{

	 if(document.suggestion.checkbox.checked==true)
		{
		 document.suggestion.rdate.value="reqdate";
		}
	else{	
		document.suggestion.rdate.value="";
	}
document.suggestion.flag.value="suggestion";
document.suggestion.submit();
}


function load(){
	document.Payment.user_no.focus();
	 
		 }	
		 
	

</script>
