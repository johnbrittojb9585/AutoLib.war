<%@ include file="/Tree/demoFrameset.jsp"%>
<%@ page language="java" session="true" buffer="12kb" import="java.sql.*,java.util.*"%>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/button_css.css" />
<%@ include file="/Common.jsp" %>
<jsp:useBean id="BeanObject" scope="request"  class="Lib.Auto.Member.MemberBean"  type="Lib.Auto.Member.MemberBean">
</jsp:useBean>

<html>
<head>
<meta http-equiv="Content-Language" content="en-us">
<meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
<meta name="GENERATOR" content="Microsoft FrontPage 4.0">
<meta name="ProgId" content="FrontPage.Editor.Document">
<title>AutoLib</title>
<link rel="stylesheet" type="text/css" href="/AutoLib/style.css">
</head>
<body background="/AutoLib/soft.jpg">
<form method="POST" action="./NoDuesServlet" name="nodues">
<br><br><br>

<h2>NoDues&nbsp;Certificate</h2>

 <table align="center" class="contentTable" width="55%">
 <tr>
<td>
<table align="center" width="90%">
<tr><td> &nbsp; </td></tr>
    <tr>
      <td class="addedit">User&nbsp;ID</td>
      <td><input type="text" name="txtUserId" size="11" onKeydown="Javascript: if (event.keyCode==13) MemberSearch();"> <input type="button" Class="submitButton" value="Find" name="find" onclick="Find_Member('member')"></td>
      <td class="addedit">Name</td><td><input type="text" name="txtName" size="20" readonly></td>
       </tr>
    <tr>
      <td class="addedit">Designation</td><td><input type="text" name="txtDesign" size="20" readonly></td>
      <td class="addedit">Course</td><td><input type="text" name="txtCourse" size="20" readonly></td>
    </tr>
    <tr>
      <td class="addedit">Department</td><td><input type="text" name="txtDept" size="20" readonly></td>
      <td class="addedit">Group</td><td><input type="text" name="txtGroup" size="20" readonly></td>
    </tr>
</table></td></tr>
<tr><td>&nbsp;</td></tr></table>


<p align="center">
	  <input type="button" Class="submitButton" value="Print" name="print" onclick="Print_Report()">
	  <input type="button" Class="submitButton" value="Check" name="check" onclick="Check_Print()">
	  <input type="reset" Class="submitButton" value="Clear" name="clear">
      <input type="hidden" name="Flag_Search">
      <input type="hidden" name="flag">
</p>

</form>
</body>
</html>
<%
String valid=request.getParameter("check");
 if(valid!=null){
 if(request.getParameter("check")!=null){
 if(valid.equals("memberYes")){
 %>
<script language="JavaScript">

 document.nodues.txtUserId.value="<%=BeanObject.getCode()%>";
 document.nodues.txtName.value="<%=BeanObject.getName()%>";
 document.nodues.txtDesign.value="<%=BeanObject.getDecode()%>";
 document.nodues.txtCourse.value="<%=BeanObject.getCoursecode()%>";
 document.nodues.txtDept.value="<%=BeanObject.getDeptcode()%>";
 document.nodues.txtGroup.value="<%=BeanObject.getGroupcode()%>";
 

</script><%

}
 if(valid.equals("returnYes")){
 %>
<script language="JavaScript">
alert("This member has to return the books....!");
</script><%

}
 
 
 if(valid.equals("FailMember")){
	 %>
	<script language="JavaScript">
         alert("Invalid Member Code !");
         document.nodues.txtUserId.focus();
    </script><%

	} 
 
 
 
 if(valid.equals("IssueYes")){
	 %>
	<script language="JavaScript">
	alert("User has not returned book...!");
	</script><%

	}
 if(valid.equals("IssueNo")){
	 %>
	<script language="JavaScript">
	alert("No Book is due from this User...!");
	</script><%

	}

}
}

%>
<script language="JavaScript">
function home()
{
location.href="/AutoLib/Home.jsp";
}

function Logout()
{
location.href="/AutoLib/index.html";
}
function Find_Member(val){
winpopup=window.open("search_nmvc.jsp?flag="+val,"popup","height=400,width=600,top=100,left=100,status=yes,menubar=no,scrollbars=yes,toolbar=no");
}
function Check_Print(){

if(document.nodues.txtUserId.value!=""){
 document.nodues.method="get";
 document.nodues.flag.value="IssueCheck";
 document.nodues.submit();
 }else{
 alert("choose the Member....");
 }
}

function MemberSearch(){
  document.nodues.method="post";
  document.nodues.Flag_Search.value="search";
  document.nodues.submit();
}


function Print_Report()
{
if(document.nodues.txtUserId.value!=""){
 document.nodues.method="get";
 document.nodues.flag.value="Print";
 document.nodues.submit();
 }else{
 alert("choose the Member....");
 }
}




function Check_Status(){
 if(document.nodues.txtUserId.value!=""){
 document.nodues.method="get";
 document.nodues.Flag_Search.value="check";
 document.nodues.submit();
 }else{
 alert("choose the Member....");
 }

}
function Print_Due(){
 if(document.nodues.txtUserId.value!=""){
 document.nodues.method="get";
 document.nodues.Flag_Search.value="print";
 document.nodues.submit();
 }else{
 alert("choose the Member....");
 }
 }
</script>

