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
<form name=Order_Find mebgcolor="#d3d3d3"thod=post action=./OrderFindServlet onsubmit="return validate()" >

<!--
//////////////////////////////////////JAVA SERVER PAGES SCRIPT ///////////////////////////////////////////////// -->
<%
String flag=request.getParameter("flag");


String sflag=request.getParameter("sflag");
String nm=request.getParameter("name");
String Title="",t1="",t2="",t3="";

/*-----------------------------------BOOK_SEARCH_FLAG-----------------------------------------------------------------------------------*/
if (flag!=null){

%>
<%
if (flag.equals("Dept")){
Title="Department Name";t1="Dept_Code";t2="Dept_Name";t3="Short_Desc";
%>
<script>document.Order_Find.sflag.value="<%=flag%>";</script>
<%
}

else if (flag.equals("Sup")){
Title="Supplier Name";t1="SP_Code";t2="SP_Name";t3="Short_Desc";
%>
<script>document.Order_Find.sflag.value="<%=flag%>";</script>
<%

}
else if (flag.equals("Budget")){
Title="Budget Name";t1="Bud_Code";t2="Bud_Head";t3="Remarks";
%>
<script>document.Order_Find.sflag.value="<%=flag%>";</script>
<%

}
}

else if (sflag!=null){

if (sflag.equals("Dept")){
Title="Department Name";t1="Dept_Code";t2="Dept_Name";t3="Short_Desc";
}

else if (sflag.equals("Sup")){
Title="Supplier Name";t1="SP_Code";t2="SP_Name";t3="Short_Desc";
}
else if (sflag.equals("Budget")){
Title="Budget Name";t1="Bud_Code";t2="Bud_Name";t3="Remarks";
}
}
%>
<!--
//////////////////////////////////////FORM CONTROLS//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// -->
<div align=center><h2 align=center style="FILTER: glow(color=white,intensity=10); HEIGHT: 15px; weight: 45"><b><font color=blue> Book Statistics Search</font></b></h2></div>
<div align=right><align=center style="FILTER: glow(color=white,intensity=10); HEIGHT: 15px; weight: 45"><b><font color="#800000"><A href=""  onclick="window.close()">Back</A></font></b></div>
<font color=blue size="+1"><b><center><%=Title%></b></font><input type=text name=name><input type=submit value="Search" ></center>
<input type=hidden name=sflag value="<%=flag%>">
</form>
</html>
<!--
//////////////////////////////////////JAVA SERVER PAGES SCRIPT /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// -->
	<!--
      //////////////////////////////////////JAVA SCRIPT USEING CLIENT SIDE////////////////////////////////////////////////////////////// -->
<!--
//////////////////////////////////////JAVA SCRIPT USEING CLIENT SIDE///////////////////////////////////////////////// -->
<%
String ck=request.getParameter("check");
ArrayList sc=new ArrayList();
try{
if(ck!=null){
if(flag!=null)
{

		sc=(ArrayList)bean.getAl();
		out.print("<table border=1 bordercolor=#008000 align=center cellspacing=0 >");
		out.print("<tr><th>"+t1+"</th><th>"+t2+"</th><th>"+t3+"</th></tr>");

		for(int i=0; i<sc.size();i++){
  %>
		<tr onmouseover=this.style.color='red' onmouseout=this.style.color='black' onclick='show("<%=sc.get(++i)%>")'>
		<%
		i--;
		%>
		<script language=javascript>
		 document.write("<td>"+"<%=sc.get(i)%>" +"</td>");
		 document.write("<td>"+"<%=sc.get(++i)%>" +"</td>");
		 document.write("<td>"+"&nbsp;<%=sc.get(++i)%>"+"</td>");
		 document.write("</tr>");
 		</script>
		<%

		}

		}

	 }
 }catch (Exception e) {out.println(e.toString());}
   finally{
	sc.clear();
    }
 %>
<script  language="javascript">

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
