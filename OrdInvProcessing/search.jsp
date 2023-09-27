<%@ page language="java" session="true" buffer="12kb" import="java.sql.*,java.util.ArrayList" %>
<jsp:useBean id="bean" scope="page" class="Lib.Auto.OrdInvProcessing.orderbean"/>
<!--
//////////////////////////////////////DATABASE CONNECTION//////////////////////////////////////////////////////////////////////// -->
<!--
//////////////////////////////////////DATABASE CONNECTION//////////////////////////////////////////////////////////////////////// -->
<%
//
//   Filename: Search.jsp
//   Form name:Order_Find
//%>

<!--
//////////////////////////////////////FORM CONTROLS//////////////////////////////////////////////////////////////////////// -->
<html>
<head>
<title>AutoLib</title>
<meta http-equiv="pragma" content="no-cache"/>
</head>
<body bgcolor="#ffe6bc" text=blue onload="focuss()">
<form name=Order_Find bgcolor="#d3d3d3" method=post action=./OrderFindServlet onsubmit="return validate()" >

<!--
//////////////////////////////////////JAVA SERVER PAGES SCRIPT ///////////////////////////////////////////////// -->
<div align=center><h2 align=center style="FILTER: glow(color=white,intensity=10); HEIGHT: 15px; weight: 45"><b><font color=blue> Book Statistics Search</font></b></h2></div>
<div align=right><align=center style="FILTER: glow(color=white,intensity=10); HEIGHT: 15px; weight: 45"><b><font color="#800000"><A href=""  onclick="window.close()">Back</A></font></b></div>
<font color=blue size="+1"><b><center>Title:</b></font><input type=text name=name><input type=submit value="Search">
</center>
<input type=hidden name=sflag>
<input type=hidden name=flag value="<%=request.getParameter("flag")%>">
</form>
</html>
<%
String searchPage=request.getParameter("SearchFlag");
if(searchPage!=null){
if(searchPage.equals("dept")){
ArrayList deptSearch=new ArrayList();
deptSearch=(ArrayList)request.getAttribute("searchObjectDept");
}
}
%>
<script language="javascript">

function show(val){

if(document.Order_Find.sflag.value=="Dept")
{
window.opener.document.Inv_Report.dept.value=val;
}

if(document.Order_Find.sflag.value=="Budget"){
window.opener.document.Inv_Report.budget.value=val;
}

if(document.Order_Find.sflag.value=="Sup"){
window.opener.document.Inv_Report.supplier.value=val;
}
window.close();
}
function focuss(){
document.Order_Find.name.focus();
}
function validate()
{
if(t1())
{
alert("Please Specify Name!...");
return false;
}
 else
{
return true;
}

}
function t1()
{
if(document.Order_Find.name.value=="")
{
return true;
}
else
{
return false;
}
}
</script>

<!--
//////////////////////////////////////JAVA SCRIPT USEING CLIENT SIDE///////////////////////////////////////////////// -->
