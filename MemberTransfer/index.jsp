<%
String URole=session.getAttribute("UserRights").toString().trim();
if(URole.equalsIgnoreCase("2") || URole.equalsIgnoreCase("3") || URole.equalsIgnoreCase("4") || URole.equalsIgnoreCase("5") || URole.equalsIgnoreCase("6") || URole.equalsIgnoreCase("7"))
{		
	response.sendRedirect("sessiontimeout.jsp");
}	
%>

<%@ include file="/Tree/demoFrameset.jsp"%>
<%@ page language="java" session="true" buffer="12kb" errorPage="/Error/ErrorPage.jsp" import="java.sql.*" import="Common.Security"  import="java.util.*" import="Lib.Auto.MemberTransfer.MemberTransRefBean" %>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/button_css.css" />
<jsp:useBean id="bean" scope="request" class="Lib.Auto.MemberTransfer.MemberTransRefBean" />

<%//Security.checkSecurity(1,session,response,request);%>

<!--
//////////////////////////////////////DATABASE CONNECTION//////////////////////////////////////////////////////////////////////// -->
<%
//
//   Filename: Index.jsp
//   Form name: Mtrans
//%>
<!--
//////////////////////////////////////FORM CONTROLS//////////////////////////////////////////////////////////////////////// -->
<html>

<%
   response.setHeader("Pragma", "No-cache");
   response.setHeader("Cache-Control", "no-cache");
   response.setDateHeader("Expires", 0);
%>
<head>
<title>AutoLib</title>
<link rel="stylesheet" type="text/css" href="/AutoLib/style.css">
</head>
<body background="/AutoLib/soft.jpg" 
onload="load()">

<!-- Style Sheet -->
<form name=Mtrans method="post" action=./MemberTransServlet>

<br><br><br>

<h2>Member&nbsp;Transfer</h2>

<center>   
<table align="center" class="contentTable" width="45%">
<td>
<table align="center" width="90%">
<tr><td> &nbsp; </td></tr>

<tr><td Class="addedit">Department&nbsp;Name:</td>
<td>
<select size="1" name="department" >
<option value="NO"> --------------------------- </option>
<c:if test="${departmentSearchList ne null }" >
<c:forEach items="${departmentSearchList}" var="std" varStatus="loop">
<option value="<c:out value="${std.code}"/>"><c:out value="${std.name}" /></option>
</c:forEach>
</c:if>
</select>
</td>
</tr>

<tr><td Class="addedit">Course&nbsp;Name</td>
<td>
<select size="1" name="course" >
<option value="NO"> --------------------------- </option>
<c:if test="${courseSearchList ne null }" >
<c:forEach items="${courseSearchList}" var="std" varStatus="loop">
<option value="<c:out value="${std.code}"/>"><c:out value="${std.name}" /> - <c:out value="${std.desc}"/></option>
</c:forEach>
</c:if>
</select>
</td>
</tr>


<tr><td Class="addedit">Group&nbsp;Name</td>
<td>
<select size="1" name="group" >
<option value="NO"> --------------------------- </option>
<c:if test="${groupSearchList ne null }" >
<c:forEach items="${groupSearchList}" var="std" varStatus="loop">
<option value="<c:out value="${std.code}"/>"><c:out value="${std.name}" /></option>
</c:forEach>
</c:if>
</select> 
</td>
</tr>


<tr><td Class="addedit">Year</td >
<td><select name="frmYear" size="1">
<option value="NO" selected >..........</option>
<option value="1" >I</option>
<option value="2">II</option>
<option value="3">III</option>
<option value="4">IV</option>
<option value="5">V</option>
<option value="passout">Pass Out</option>
</select></td></tr>

<tr><td Class="addedit">To Year</td >
<td><select name="toYear" size="1">
<option value="NO" selected >..........</option>
<option value="1" >I</option>
<option value="2">II</option>
<option value="3">III</option>
<option value="4">IV</option>
<option value="5">V</option>
<option value="passout">Pass Out</option>
</select></td></tr>

<tr><td> &nbsp; </td></tr>

<tr><td colspan=2 align=center>
<center>
<input type=button name=Save Class="submitButton" value=Transfer onclick="SaveRec()">
<input type=button name=search Class="submitButton" Value=Search  onclick="SearchRec()">
<input type=button name=Clear Class="submitButton" Value=Clear onclick="new_no()">
</center>
<input type=hidden name=flag>
<input type=hidden name=flag1>

</table>
</td></table></center>

<br>
<br>
<c:if test="${memberSearchListSize gt 0 }" >


<b><center>Member Details</center></b>
<table border=1 bordercolor=#008000 align=center cellspacing=0 width='27%' >
<tr><td colspan="2">
 <a href="javascript:void();" onclick="javascript:checkAll('Mtrans', true);">All</a> | 
         <a href="javascript:void();" onclick="javascript:checkAll('Mtrans', false);">None</a>
</td>
<td>
Total User:&nbsp;&nbsp;<font color="red"><c:out value="${memberSearchListSize}"/></font>
</td>
</tr>

<tr><th>S.No<th>Member Code<th>Member Name</tr>

<c:forEach items="${memberSearchList}" var="std" varStatus="loop">
<tr>
<td width='1%'><input type='checkbox' id="selectedList[]" name="selectedList[]" value="<c:out value="${std.name}"/>"></td>
<td width='4%'><input type="hidden"  name="memCode[]" value="<c:out value="${std.name}"/>"><c:out value="${std.name}"/></td>
<td width='7%'><input type="hidden"  name="memName[]" value="<c:out value="${std.desc}"/>"><c:out value="${std.desc}"/></td>
</tr>
</c:forEach>

</table>
</c:if>

<c:if test="${memberSearchListSize eq 0}">
			<table align="center" border="1" width="15%" cellspacing="0" cellpadding="5" class="contentmTbl" >
				<br>
				<td class="bodytext" align="center">
					<img id="imgInfo" src="<%=request.getContextPath()%>/images/info.gif" border="0" alt="Info" align="absmiddle">&nbsp;
					<font size="2" color="Red" face="verdana"><b>Record Not Found</b></font>
				</td>
			</table>
</c:if>

</form>
</body>
</html>

<%

String valid=request.getParameter("check");

if(valid!=null){
if(request.getParameter("check")!=null){
	
if(valid.equals("member")){
 %>
<script language="JavaScript">
document.Mtrans.department.value="<%=bean.getDeptCode()%>";
document.Mtrans.course.value="<%=bean.getCourseCode()%>";
document.Mtrans.group.value="<%=bean.getGroupCode()%>";
document.Mtrans.frmYear.value="<%=bean.getAdd1()%>";
document.Mtrans.toYear.value="<%=bean.getAdd2()%>";

</script><%
}

if(valid.equals("transferred")){
	 %>
	<script language="JavaScript">
	  alert("Transferred Successfully !");
  	</script><%
	}
}
}
%>


<!--
///////////////////////////////////////JAVA SCRIPT USEING CLIENT SIDE///////////////////////////////////////////////// --> 
<script language=javascript>


function home()
{
location.href="/AutoLib/Home.jsp";
}

function Logout()
{
location.href="/AutoLib/index.html";
}

function new_no(){

document.Mtrans.method="post";
document.Mtrans.flag.value="new";
document.Mtrans.submit();
}

function SearchRec(){
if(chk_mc()){
  document.Mtrans.flag.value="search";
  document.Mtrans.submit();	
}  
}


function SaveRec(){
if(chk_list()){

            var cboxes = document.getElementsByName('selectedList[]');
            var len = cboxes.length;
  
            var content="";
            var chkitem=0;
   
            for (var i=0; i<len; i++) {
             if(cboxes[i].checked == true)
             {  
               chkitem++;
               content=content+",'"+cboxes[i].value+"'";              
             }           
            }
    
            if(chkitem >= 1)
         	{
			  //alert('You are selected - '+chkitem);
			  //alert(content);
              document.Mtrans.flag1.value=content;
			  document.Mtrans.flag.value="transfer";
              document.Mtrans.submit();
         	}
	        else if(chkitem == 0)
	        {
			  alert('Please select atleast one user / click search again !');
			  return false;
	        }        
}	        
}


function chk_mc(){

if(document.Mtrans.department.value == 'NO')
{
  alert('Please select any one of Department !');
  return false;				
}
else if(document.Mtrans.course.value == 'NO')
{
  alert('Please select any one of Course !');
  return false;				
}
else if(document.Mtrans.group.value == 'NO')
{
  alert('Please select any one of Group !');
  return false;				
}
else if(document.Mtrans.frmYear.value == 'NO')
{
  alert('Please select any one of Year !');
  return false;				
}
else{
  return true;	
}

}

function chk_list(){
if(document.Mtrans.toYear.value == 'NO')
{
  alert('Please select any one of To Year !');
  return false;				
}
else{
  return true;	
}

}

function ClearRec(){ 
		 document.Mtrans.action="index.jsp";
         document.Mtrans.submit();
}
		 
function checkAll(formname, checktoggle)
{
     var checkboxes = new Array();
      checkboxes = document[formname].getElementsByTagName('input');

      for (var i = 0; i < checkboxes.length; i++) {
          if (checkboxes[i].type === 'checkbox') {
               checkboxes[i].checked = checktoggle;
          }
      }
}		 

</script>




<!--
//////////////////////////////////////JAVA SCRIPT USEING CLIENT SIDE///////////////////////////////////////////////// --> 
