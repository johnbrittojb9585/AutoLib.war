<%@ page language="java" session="true" buffer="12kb" import="java.sql.*,java.util.ArrayList" %>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/button_css.css" />
<jsp:useBean id="bean" scope="request" class="Lib.Auto.Statistics.statbean"/>
<!--
//////////////////////////////////////DATABASE CONNECTION//////////////////////////////////////////////////////////////////////// -->
<!--
//////////////////////////////////////DATABASE CONNECTION//////////////////////////////////////////////////////////////////////// -->
<%
//
//   Filename: Search.jsp
//   Form name:Book_Find
//%>

<!--
//////////////////////////////////////FORM CONTROLS//////////////////////////////////////////////////////////////////////// -->
<html>
<head>
<title>AutoLib</title>
<meta http-equiv="pragma" content="no-cache"/>
<link rel="stylesheet" type="text/css" href="/AutoLib/style.css">
</head>
<body background="/AutoLib/soft.jpg" onload="focuss()">
<form name=Book_Find method=post action=./FindServlet ><!--onsubmit="return validate()" > -->

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
Title="Department Name";t1="Dept_Code";t2="Department Name";t3="Short_Desc";
%>
<script>document.Book_Find.sflag.value="<%=flag%>";</script>
<%
}

else if (flag.equals("Sub")){
Title="Subject Name";t1="Sub_Code";t2="Subject Name";t3="Short_Desc";
%>
<script>document.Book_Find.sflag.value="<%=flag%>";</script>
<%

}
else if (flag.equals("Sup")){
Title="Supplier Name";t1="SP_Code";t2="Supplier Name";t3="Short_Desc";
%>
<script>document.Book_Find.sflag.value="<%=flag%>";</script>
<%

}
else if (flag.equals("Pub")){
Title="Publisher Name";t1="SP_Code";t2="Publisher Name";t3="Short_Desc";
%>
<script>document.Book_Find.sflag.value="<%=flag%>";</script>
<%

}
}

else if (sflag!=null){

if (sflag.equals("Dept")){
Title="Department Name";t1="Dept_Code";t2="Dept_Name";t3="Short_Desc";
}

else if (sflag.equals("Sub")){
Title="Subject Name";t1="Sub_Code";t2="Sub_Name";t3="Short_Desc";
}
else if (sflag.equals("Sup")){
Title="Supplier Name";t1="SP_Code";t2="SP_Name";t3="Short_Desc";
}
else if (sflag.equals("Pub")){
Title="Publisher Name";t1="SP_Code";t2="SP_Name";t3="Short_Desc";
}
}
%>
<!--
//////////////////////////////////////FORM CONTROLS//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// -->
<h2>Book Statistics Search</h2>
<div ><A href=""  onclick="window.close()">Back</A></div>
<center><%=Title%>&nbsp;<input type=text name=name>&nbsp;<input type=submit Class="submitButton" value="Search" ></center>
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

	int j=1;	
	sc=(ArrayList)bean.getAl();
		    %>
  <script language="JavaScript">
document.Book_Find.name.value="<%=request.getParameter("nam")%>";
document.Book_Find.name.focus();
</script>
<%
		out.print("<table border=1 bordercolor=#008000 align=center cellspacing=0 >");
		out.print("<tr><th>S.No</th><th>"+t2+"</th><th>"+t3+"</th></tr>");

		for(int i=0; i<sc.size();i++){
  %>
		<tr onmouseover=this.style.color='red' onmouseout=this.style.color='black' onclick='show("<%=sc.get(++i)%>")'>
		<%
		i--;
		%>
		<script language=javascript>
		 //document.write("<td>"+"<%=sc.get(i)%>" +"</td>");
		 document.write("<td>"+"<%=j++%>" +"</td>");
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

if(document.Book_Find.sflag.value=="Dept")
{
window.opener.document.sreport.txtdepartment.value=val;
}
if(document.Book_Find.sflag.value=="Sub")
{
window.opener.document.sreport.txtsubject.value=val;
}
if(document.Book_Find.sflag.value=="Pub"){
window.opener.document.sreport.txtpublisher.value=val;
}

if(document.Book_Find.sflag.value=="Sup"){
window.opener.document.sreport.txtsupplier.value=val;
}
window.close();
}
function focuss(){
document.Book_Find.name.focus();
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
if(document.Book_Find.name.value=="")
{
return true;
}
else
{
return false;
}
}
function search(){
document.Book_Find.submit();
}
</script> 
<!--
//////////////////////////////////////JAVA SCRIPT USEING CLIENT SIDE///////////////////////////////////////////////// -->
