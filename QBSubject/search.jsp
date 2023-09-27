<%@ page language="java" errorPage="/Error/ErrorPage.jsp" session="true" buffer="12kb" import="Lib.Auto.QBSubject.QBsubjectbean" import="Common.Security" import="java.util.ArrayList,java.util.Iterator"%>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/button_css.css" />
<%//Security.checkSecurity(1,session,response,request);%>
<!--
//////////////////////////////////////DATABASE CONNECTION//////////////////////////////////////////////////////////////////////// -->
<%
//
//   Filename: Search.jsp
//   Form name:Subject_Find
//%>
<!--
//////////////////////////////////////FORM CONTROLS//////////////////////////////////////////////////////////////////////// -->
<html>
<head>
<title>Auto Lib</title>
<%
   response.setHeader("Pragma", "No-cache");
   response.setHeader("Cache-Control", "no-cache");
   response.setDateHeader("Expires", 0);
%>
<link rel="stylesheet" type="text/css" href="/AutoLib/style.css">
</head>
<body onload="focuss()" background="/AutoLib/soft.jpg">
<form name=Subject_Find method=post action=./QBSubjectServlet>

<h2>Question Bank&nbsp;Search</h2>
<div><A href=""  onclick="window.close()">Back</A></div>


<p align="center">QBSubject Name:<input type=text name=name> <input type=submit Class="submitButton" value="Search"></p>

<input type=hidden name=flag  value="Subject">

</form>
</body>
</html>
<!--
//////////////////////////////////////JAVA SERVER PAGES SCRIPT /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// -->
<%


String ck=request.getParameter("check");
ArrayList sc=new ArrayList();
try{
if(ck!=null){

	int i=1;
             sc=(ArrayList)request.getAttribute("serarch");

	     	     %>
  <script language="JavaScript">
document.Subject_Find.name.value="<%=request.getParameter("nam")%>";
document.Subject_Find.name.focus();
</script>

<%
			 out.print("<table border=1 bordercolor=#008000 align=center cellspacing=0 >");
             out.print("<tr><th>S.No<th>Sub Code<th>Sub Name<th>Desc</tr>");
           Iterator it=sc.iterator();
			while(it.hasNext()){
				QBsubjectbean view=(QBsubjectbean) it.next();
				%>
		<tr onmouseover=this.style.color='red' onmouseout=this.style.color='black' onclick='show("<%=view.getQbcode()%>")'>
		<script language=javascript>
//		 document.write("<td>"+"<%=view.getQbcode()%>" +"</td>");
		 document.write("<td>"+"<%=i++%>" +"</td>");
		 document.write("<td>"+"<%=view.getQbsubcode() %>" +"</td>");
		 document.write("<td>"+"<%=view.getQbsubname()%>" +"</td>");
		 document.write("<td>&nbsp;"+"<%=view.getQbsubdesc()%>"+"</td>");
		 document.write("</tr>");
 		</script>
		<%
			}

			sc.clear();

}


 }catch (Exception e) {e.printStackTrace();}
   finally{
    sc.clear();
    }
%>


	
	<script language="javascript">
function show(val){
window.opener.document.Subject.code.value=val;
window.opener.document.Subject.method="get";
window.opener.document.Subject.flag.value="search";
window.opener.document.Subject.action="./QBSubjectServlet";
window.opener.document.Subject.submit();
window.close();
}

function validate()
{
if(t1())
{
alert("Please enter the SubjectName");
return false;
}
 else
{
return true;
}

   }
function t1()
{
if(document.Subject_Find.name.value=="")
{
return true;
}
else
{
return false;
}
}
function focuss(){
document.Subject_Find.name.focus();
}
function search(){
document.Subject_Find.submit();
}
</script>