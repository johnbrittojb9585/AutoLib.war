<!DOCTYPE html>
<%
String URole=session.getAttribute("UserRights").toString().trim();
if(URole.equalsIgnoreCase("4") || URole.equalsIgnoreCase("5") || URole.equalsIgnoreCase("6") || URole.equalsIgnoreCase("7"))
{		
	response.sendRedirect("sessiontimeout.jsp");
}	
%>

<%@ include file="/Tree/demoFrameset.jsp"%>
<%@ page language="java" session="true" buffer="12kb" import="Common.Security"%>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/button_css.css" />



<%
//
//   Filename: Index.jsp
//   Form name:Department
//%>

<html>
<head>
<meta charset="ISO-8859-1">
 
  <title>AutoLib</title>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/jquery-ui.css"/>
<%-- <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/jquery-ui-base.css"/> --%>
<%-- <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/jquery-ui-darkness.css"/> --%>
<%-- <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/jquery-ui-flick.css"/> --%>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/style1.css"/>
<link rel="stylesheet" type="text/css" href="/AutoLib/style.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-2.2.4.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-ui.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/searchDeptMas.js"></script>


<script type="text/javascript">

function myFunction() {
	
	  var x = document.getElementById("myDIV");
	  if (x.style.display === "none") {
	    x.style.display = "block";
	  } else {
	    x.style.display = "none";
	  }
	}
</script>
</head>

<body background="/AutoLib/soft.jpg" onload="load()">

<form name=Department method="post" action=./DepartmentServlet>
<br><br>

   <h2 align="center">Notes For	Import</h2>
   
 
&nbsp;&nbsp;
<center>
<table align="center" class="contentTable" width="60%" height="50%">
<tr><td>&nbsp;</td></tr>
			
              <tr>&nbsp;&nbsp;&nbsp;
             <td style="text-align: left; padding-left: 30px;"> 	
		    <input type=button name=New Class="submitButton"  Value=Book onclick=myFunction() >		
			</td>&nbsp;&nbsp;&nbsp; </tr>
			
			
			 
			<tr>&nbsp;&nbsp;&nbsp;
           <td style="text-align: left; padding-left: 30px;"> 				
					<input type=button name=New Class="submitButton"  Value=Member onclick=new_no() >		
			</td>&nbsp;&nbsp;&nbsp; </tr>
			
			<tr>&nbsp;&nbsp;&nbsp;
            <td style="text-align: left; padding-left: 30px;"> 				
					<input type=button name=New Class="submitButton"  Value=E-Book onclick=new_no() >		
			</td>&nbsp;&nbsp;&nbsp; </tr>
			
			<tr>&nbsp;&nbsp;&nbsp;
            <td style="text-align: left; padding-left: 30px;"> 				
					<input type=button name=New Class="submitButton"  Value=QuestionBank onclick=new_no() >		
			</td>&nbsp;&nbsp;&nbsp; </tr>
			
			<tr>&nbsp;&nbsp;&nbsp;
            <td style="text-align: left; padding-left: 30px;"> 				
					<input type=button name=New Class="submitButton"  Value=BackVolume onclick=new_no() >		
			</td>&nbsp;&nbsp;&nbsp; </tr>
			
			<tr>&nbsp;&nbsp;&nbsp;
            <td style="text-align: left; padding-left: 30px;"> 				
					<input type=button name=New Class="submitButton"  Value=GateRegister onclick=new_no() >		
			</td>&nbsp;&nbsp;&nbsp; </tr>
			
			<tr><td>&nbsp;</td></tr>

</table>



</center>

 <div class="myDIV"> 
			 <p>Show success!!</p>
			  </div>
</form>

</body>
</html>

