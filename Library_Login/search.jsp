<%@ page language="java" errorPage="/Error/ErrorPage.jsp" session="true" buffer="12kb" import="Lib.Auto.Subject.subjectbean" import="java.util.ArrayList,java.util.Iterator" %>
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
<title>AutoLib</title>
<%
   response.setHeader("Pragma", "No-cache");
   response.setHeader("Cache-Control", "no-cache");
   response.setDateHeader("Expires", 0);
%>
<link rel="stylesheet" type="text/css" href="/AutoLib/style.css">
</head>
<body onload="focuss()" background="/AutoLib/soft.jpg">
<form name="Member_Find" method="post" action=./LibraryLoginReportsSearch >
<%
String flag=request.getParameter("flag");
//out.println(flag);
String SQLstr="";
String Title="";
String t1="",t2="";
String mainTitle="";
if (flag!=null){
if (flag.equals("Department")){
	mainTitle="Department"; Title="Department&nbsp;Name";t1="Department&nbsp;Code";t2="Short&nbsp;Desc";
	%>
	<script>document.Member_Find.sflag.value="<%=flag%>";</script>
	<%
}
else if (flag.equals("Group")){
mainTitle="Group"; Title="Group&nbsp;Name";t1="Group&nbsp;Code";t2="Remarks";
%>
<script>document.Member_Find.sflag.value="<%=flag%>";</script>
<%
}
}

%>
<!--
//////////////////////////////////////JAVA SERVER PAGES SCRIPT ///////////////////////////////////////////////// -->
<h2><%=mainTitle%>&nbsp;Search</h2>
<div><A href=""  onclick="window.close()">Back</A></div>
<center><%=Title%>
<input type=text name=name>
<input Class="submitButton" type=submit value="Search" >
</center>
<input type=hidden name=sflag value="<%=flag%>">
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

document.Member_Find.name.value="<%=request.getParameter("nam")%>";
document.Member_Find.name.focus();
</script>
<%
if (flag.equals("Department")){

	out.print("<table border=1 bordercolor=#008000 align=center cellspacing=0 >");
	out.print("<tr><th>S.No</th><th>"+Title+"</th><th>"+t2+"</th></tr>");

	
	Iterator it=sc.iterator();
	while(it.hasNext()){
		subjectbean view=(subjectbean) it.next();
		  %>
				<tr onmouseover=this.style.color='red' onmouseout=this.style.color='black' onclick='show("<%=view.getName()%>")'>
				<script language=javascript>
				 //document.write("<td>"+"<%=view.getCode()%>" +"</td>");
		   		 document.write("<td>"+"<%=i++%>" +"</td>");
				 document.write("<td>"+"<%=view.getName()%>" +"</td>");
				 document.write("<td>"+"<%=view.getDesc()%>" +"</td>");
				 document.write("</tr>");
		 		</script>
				<%

				}
				}

if (flag.equals("Group")){

	out.print("<table border=1 bordercolor=#008000 align=center cellspacing=0 >");
	out.print("<tr><th>S.No</th><th>"+Title+"</th><th>"+t2+"</th></tr>");

	
	Iterator it=sc.iterator();
	while(it.hasNext()){
		subjectbean view=(subjectbean) it.next();
		  %>
				<tr onmouseover=this.style.color='red' onmouseout=this.style.color='black' onclick='show("<%=view.getName()%>")'>
				<script language=javascript>
				 //document.write("<td>"+"<%=view.getCode()%>" +"</td>");
		   		 document.write("<td>"+"<%=i++%>" +"</td>");
				 document.write("<td>"+"<%=view.getName()%>" +"</td>");
				 document.write("<td>"+"<%=view.getDesc()%>" +"</td>");
				 document.write("</tr>");
		 		</script>
				<%

				}
				}

sc.clear();
}
 }catch (Exception e) {e.printStackTrace();}
   finally{
    sc.clear();
    }
%>

<script  language="javascript">

function show(val){
if(document.Member_Find.sflag.value=="Department"){
window.opener.document.Library_Login.Dname.value=val;
}
if(document.Member_Find.sflag.value=="Group"){
window.opener.document.Library_Login.Gname.value=val;
}

window.close();
}

function focuss(){
document.Member_Find.name.focus();
}

</script>
