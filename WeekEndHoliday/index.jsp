<%
String URole=session.getAttribute("UserRights").toString().trim();
if(URole.equalsIgnoreCase("2") || URole.equalsIgnoreCase("3") || URole.equalsIgnoreCase("4") || URole.equalsIgnoreCase("5") || URole.equalsIgnoreCase("6") || URole.equalsIgnoreCase("7"))
{		
	response.sendRedirect("sessiontimeout.jsp");
}	
%>
<%@ include file="/Tree/demoFrameset.jsp"%>
<%@ page language="java" session="true" buffer="12kb" import="java.sql.*,java.util.*"  import="Common.Security" %>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/button_css.css" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
<meta name="GENERATOR" content="Microsoft FrontPage 4.0">
<meta name="ProgId" content="FrontPage.Editor.Document">
<title>Week End Holidays</title>
<link rel="stylesheet" type="text/css" href="/AutoLib/style.css">
<jsp:useBean id="bean" scope="request" class="Lib.Auto.WeekEndHoliday.weekendholidaybean" type="Lib.Auto.WeekEndHoliday.weekendholidaybean">
</jsp:useBean>
<%
ArrayList sc=new ArrayList();
%>
<script language="javascript" src="/AutoLib/popcalendar.js"></script>
</head>

<body background="/AutoLib/soft.jpg">

<form method="POST" action="./WeekEndHolidayServlet" name="WeekEndHoliday">
<br><br><br>
  <h2 >WEEK&nbsp;END&nbsp;HOLIDAYS</h2>
<table align="center" class="contentTable" width="25%">
<td>
<table border="0" width="60%" cellspacing="0" cellpadding="5" align="center">
  <tr>
    <tr>
    </tr><tr><td>
      <td><input type=checkbox name="SUNDAY" value=1 >Sunday</td>
      </tr><tr><td>
      <td><input type=checkbox name="MONDAY" value=2 >Monday</td>
      </tr><tr><td>
      <td><input type=checkbox name="TUESDAY" value=3 >Tuesday</td>
      </tr><tr><td>
      <td><input type=checkbox name="WEDNESDAY" value=4 >Wednesday</td>
      </tr><tr><td>
      <td><input type=checkbox name="THURSDAY" value=5 >Thursday</td>
      </tr><tr><td>
      <td><input type=checkbox name="FRIDAY" value=6 >Friday</td>
      </tr><tr><td>
      <td><input type=checkbox name="SATURDAY" value=7 >Saturday</td>   
    </tr>
    <tr>
      <td></td>
      <td></td>
    </tr>
    <tr>
      <td></td>
            <td><input type=button name=Save Class="submitButton"  Value=Save onclick=check()>&nbsp;&nbsp;<input type=button Class="submitButton"  name=load value=Load onclick=RetriveWeekEnd()><input type="hidden" name="flag"><input type="hidden" name="flag1"><input type="hidden" name="flag2"></td>
    </tr>
  </table></td></table>
</form>

</body>

</html>
<%
String check=null;
check=request.getParameter("check");
if(check!=null)
{	
if(check.equals("SaveWeakEnd"))	
{
	%>
	
<script language="javascript"	>
alert("WeakEndHolidays Updated !");
</script>
	<%
	
}
if(check.equals("RetriveWeakEnd"))	
{
	String Fholi=null,SHoli=null;
	int i=0,j=0;
	
    sc=(ArrayList)bean.getDay_ID();
    if(sc.size()==1){   
    	Fholi=sc.get(0).toString();  
    	i = Integer.parseInt(Fholi); 
    }else if(sc.size()==2){   
    	Fholi=sc.get(0).toString();    	   	
    	SHoli=sc.get(1).toString();   
    	i = Integer.parseInt(Fholi); 
    	j = Integer.parseInt(SHoli); 
    }  
    
	sc.clear();  	
	
	if(i==1 || j==1)
	{	%>
	<script language=javascript>		
	 document.WeekEndHoliday.SUNDAY.checked=true
	</script>
		  <%
	}if(i==2 || j==2)
	{	%>
	<script language=javascript>	
	 document.WeekEndHoliday.MONDAY.checked=true
	</script>
	  <% 
	}if(i==3 || j==3)
	{	%>
	<script language=javascript>	
	 document.WeekEndHoliday.TUESDAY.checked=true
	</script>
	  <% 
	}if(i==4 || j==4)
	{	%>
	<script language=javascript>	
	 document.WeekEndHoliday.WEDNESDAY.checked=true
	</script>
	  <% 
	}if(i==5 || j==5)
	{	%>
	<script language=javascript>	
	 document.WeekEndHoliday.THURSDAY.checked=true
	</script>
	  <% 
	}if(i==6 || j==6)
	{	%>
	<script language=javascript>	
 	 document.WeekEndHoliday.FRIDAY.checked=true
	</script>
	  <% 
	}if(i==7 || j==7)
	{	%>
	<script language=javascript>	
	 document.WeekEndHoliday.SATURDAY.checked=true
	</script>
	  <% 
	}	
}
}


%>
<script language="JavaScript">

function check(){
var count=0
var fholiday = 0
var sholiday = 0

if(document.WeekEndHoliday.SUNDAY.checked==true)
{count=count+1
 if(fholiday==0){
        fholiday = 1
 }else{
        sholiday = 1
 }
}
if(document.WeekEndHoliday.MONDAY.checked==true)
{count=count+1
 if(fholiday==0){
        fholiday = 2
 }else{
        sholiday = 2
 }}
if(document.WeekEndHoliday.TUESDAY.checked==true)
{count=count+1
 if(fholiday==0){
        fholiday = 3
 }else{
        sholiday = 3
 }}
if(document.WeekEndHoliday.WEDNESDAY.checked==true)
{count=count+1
 if(fholiday==0){
        fholiday = 4
 }else{
        sholiday = 4
 }}
if(document.WeekEndHoliday.THURSDAY.checked==true)
{count=count+1
 if(fholiday==0){
        fholiday = 5
 }else{
        sholiday = 5
 }}
if(document.WeekEndHoliday.FRIDAY.checked==true)
{count=count+1
 if(fholiday==0){
        fholiday = 6
 }else{
        sholiday = 6
 }}
if(document.WeekEndHoliday.SATURDAY.checked==true)
{count=count+1
 if(fholiday==0){
        fholiday = 7
 }else{
        sholiday = 7
 }}


if(count==2)
{
 if((sholiday-fholiday)==1)
 { 
 document.WeekEndHoliday.flag.value="save";
 document.WeekEndHoliday.flag1.value=fholiday;
 document.WeekEndHoliday.flag2.value=sholiday;  
 document.WeekEndHoliday.submit();
 }
 else if(fholiday==1 && sholiday==7 ){
 document.WeekEndHoliday.flag.value="save";
 document.WeekEndHoliday.flag1.value=fholiday;
 document.WeekEndHoliday.flag2.value=sholiday;  
 document.WeekEndHoliday.submit();
 }
 else
 {
  alert("Please Select Continous Days !");
 }
 
}
else if(count==1)
{
 document.WeekEndHoliday.flag.value="save";
 document.WeekEndHoliday.flag1.value=fholiday;
 document.WeekEndHoliday.flag2.value=sholiday;  
 document.WeekEndHoliday.submit();
}
else
{
 alert('Pick Just One/Two Days Only')
}
}

function RetriveWeekEnd()
{
 document.WeekEndHoliday.flag.value="search";
 document.WeekEndHoliday.submit();
}

function home()
{
location.href="/AutoLib/Home.jsp";
}

function Logout()
{
location.href="/AutoLib/index.html";
}
</script>

