<%
String URole=session.getAttribute("UserRights").toString().trim();
if(URole.equalsIgnoreCase("2") || URole.equalsIgnoreCase("3") || URole.equalsIgnoreCase("4") || URole.equalsIgnoreCase("5") || URole.equalsIgnoreCase("6") || URole.equalsIgnoreCase("7"))
{		
	response.sendRedirect("sessiontimeout.jsp");
}	
%>

<%@ include file="/Tree/demoFrameset.jsp"%>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/button_css.css" />

<!--
//////////////////////////////////////////////////////////////////FORM CONTROLS//////////////////////////////////////////////////////////////////////// -->
<html>
<head>
<meta charset="ISO-8859-1">

<title>AutoLib</title>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/jquery-ui.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-2.2.4.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-ui.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/searchEmailMember.js"></script>
<script language="javascript" src="/AutoLib/popcalendar.js"></script>
<link rel="stylesheet" type="text/css" href="/AutoLib/style.css">
<meta http-equiv="pragma" content="no-cache"/>
</head>
<body background="/AutoLib/soft.jpg"><!--onload="opt(1)"-->

<!-- Style Sheet -->

<form name="Mail_Reminder" method="Post" action=./MailReminder>
<br><br><br>

<br><br><br>
  <div align="center">
    <center>
  <table align="center" class="contentTable" width="25%">
<td>
<table align="center" width="25%">    
      <tr>
      <h2>&nbsp;&nbsp;&nbsp;&nbsp;Email/SMS&nbsp;&nbsp;Reminder</h2><br>  </tr>            
          <p align="center">
      <div class="search-container">
	<div class="ui-widget">   
     <tr>
     	<td Class="addedit">Department</td>
     	<td> <input type=text name=Dname id="searchMemberDept" class="searchMember" onkeyup="showMemberDept(this.value);" size=20 value="ALL"></td>
     </tr>  
     </div></div> 
       <div class="search-container">
	<div class="ui-widget"
     <tr> 
     	<td Class="addedit">Group&nbsp;Name</td>
     	 <td> <input type=text name=Gname id="searchMemberGroup" class="searchMember" onkeyup="showMemberGroup(this.value);" size=20 value="ALL"></td>
     </tr>
     </div></div> 
    <div class="search-container">
	<div class="ui-widget"
     
     <tr>
   	  <td  Class="addedit">Course</td>
   	  <td><input type=text name=Cname id="searchMemberCourse" class="searchMember" onkeyup="showMemberCourse(this.value);" size=20 value="ALL"></td>
     </tr>  
     </div></div>
        <div class="search-container">
	<div class="ui-widget"
     <tr>  
      <td  Class="addedit">Designation</td> 
      <td><input type=text name=Desig size=20 id="searchMemberDesig" class="searchMember" onkeyup="showMemberDesig(this.value);" value="ALL"></td>
     </tr>
      </div></div>
      <tr>
      <td Class="addedit">Year</td><td><select size="1" name="year"  style="width: 70px">
        <option selected value="ALL">ALL</option>
        <option value="1">1 Year</option>
        <option value="2">2 Year</option>
        <option value="3">3 Year</option>
        <option value="4">4 Year</option>
        <option value="5">5 Year</option>
        <option value="6">Pass out</option>
        
        </select></td>
      </tr>
      <tr></tr><tr></tr>
      
<tr>		   
      </tr>   
    </table></table>
    <p align="center">
  		  <input type="button" Class="submitButton" value="Due List" onclick=SendRec1()>
		  <input type="button" Class="submitButton" value="Send E-Mail" onclick=SendRec()>
		  <input type="reset" Class="submitButton" value="Clear"></td>
		  		  <!-- <input type="button" Class="submitButton" value="Send SMS" onclick=SendSms()>		 </td> -->
		  <input type="hidden" name="flag">	 
		  <input type="hidden" name="flag1" value="ALL">
    </p>
    
    
    </center>
  </div>
<br>
</form>
</body>
</html>

<%
String valid=request.getParameter("check");
String mailcount=request.getParameter("mailCount");

if(valid!=null){
if(request.getParameter("check")!=null){

if(valid.equals("SaveSuccess"))
{
	%>
	
	 <script language="JavaScript">
	 
<%-- 	alert("E-Mail Send Successfully. Count: "+<%= mailcount %>); --%>
alert("E-Mail Send Successfully.");
	 
	 </script>
	 
<%
}
if(valid.equals("SaveFail"))
{
	%>
	
	 <script language="JavaScript">
	 
	alert("E-Mail Sending Failed !");
	 
	 </script>
	 
<%
}

if(valid.equals("SaveSuccessSMS"))
{
	%>
	
	 <script language="JavaScript">
	 
	alert("SMS Send Successfully. Count: "+<%= mailcount %>);
	 
	 </script>
	 
<%
}
if(valid.equals("SaveFailSMS"))
{
	%>
	
	 <script language="JavaScript">
	 
	alert("SMS Sending Failed !");
	 
	 </script>
	 
<%
}

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

function SendRec() {
document.Mail_Reminder.flag.value="DueReminderMail";
document.Mail_Reminder.method="get";
document.Mail_Reminder.submit();
}

function SendRec1() {
document.Mail_Reminder.flag.value="DueReminderList";
document.Mail_Reminder.method="get";
document.Mail_Reminder.submit();
}

function SendSms()
{
document.Mail_Reminder.flag.value="DueReminderSMS";
document.Mail_Reminder.method="get";
document.Mail_Reminder.submit();
}

</script>


