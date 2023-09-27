<%@ page language="java" session="true" buffer="12kb" import="java.sql.*,java.util.*" import="Lib.Auto.Author.AuthorBean" import="Common.Security"  import="Lib.Auto.Subject.subjectbean" import="Lib.Auto.Book.bookbean" import="Lib.Auto.Book.BookSearchBean"%>
<%@ include file="/Common.jsp" %>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/button_css.css" />
<!--
//////////////////////////////////////DATABASE CONNECTION//////////////////////////////////////////////////////////////////////// -->

<%
//
//   Filename: Search.jsp
//   Form name:order_Find
//%>
<!--
//////////////////////////////////////FORM CONTROLS//////////////////////////////////////////////////////////////////////// -->
<html>
<head>
<title>AutoLib</title>
<meta http-equiv="pragma" content="no-cache"/>
<link rel="stylesheet" type="text/css" href="/AutoLib/style.css">
</head>
<body background="/AutoLib/soft.jpg" onload="focuss()" >
<form name=Bib_Find method=post action=./BibSearch>
<!--
//////////////////////////////////////JAVA SERVER PAGES SCRIPT ///////////////////////////////////////////////// -->
<%
String flag=request.getParameter("flag");

String SQLstr="";
String Title="";
String t1="",t2="",t3="",t4="";
if (flag!=null){
if (flag.equals("Dept")){
Title="Department Name";t1="Dept_Code";t2="Dept_Name";t3="Short_Desc";
t4="Department";
}
if (flag.equals("Sub")){
Title="Subject Name";t1="Sub_Code";t2="Sub_Name";t3="Short_Desc";
t4="Subject";
}
if (flag.equals("Aut")){
	Title="Author Name";t1="Author_Code";t2="Author_Name";t3="Short_Desc";
	t4="Author";
}
if (flag.equals("Pub")){
	Title="Publisher Name";t1="Pub_Code";t2="Publisher_Name";t3="Short_Desc";
	t4="Publisher";
}
if (flag.equals("Sup")){
	Title="Supplier Name";t1="Sup_Code";t2="Supplier_Name";t3="Short_Desc";
	t4="Supplier";
}
if (flag.equals("Bud")){
	Title="Budget Head";t1="Budget_Code";t2="Budget_Head";t3="Short_Desc";
	t4="Budget";
}
if(flag.equals("ACCESSION") || flag.equals("RECEIVEDDATE") || flag.equals("CALLNO") || flag.equals("NO")){
%>
<script>
alert("Visible to Search Options Only !");
window.close();
</script>
<%	
}
}

%>
<!--
//////////////////////////////////////FORM CONTROLS//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// -->
<h2><%=t4%>&nbsp;Search</h2>
<div align=right><A href=""  onclick="window.close()">Back</A></div>
<center><%=Title%>&nbsp;<input type=text name=name value="">&nbsp;<input type=submit Class="submitbutton" value="Search"></center>
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
 %>
  <script language="JavaScript">

document.Bib_Find.name.focus();
</script>
<%

if (flag.equals("Aut")){
	

    sc=(ArrayList)request.getAttribute("search");

    
    
 out.print("<table border=1 bordercolor=#008000 align=center cellspacing=0 >");
 out.print("<tr><th>S.No<th>Author&nbsp;Name<th>Short&nbsp;Desc</tr>");
         Iterator it=sc.iterator();
    
         while(it.hasNext()){
         	BookSearchBean view=(BookSearchBean) it.next();

		%>
<tr onmouseover=this.style.color='red' onmouseout=this.style.color='black' onclick='show("<%=view.getAcode()%>","<%=view.getAname()%>")'>
<script language=javascript>
//document.write("<td>"+"<%=view.getAcode()%>" +"</td>");
document.write("<td>"+"<%=i++%>" +"</td>");			
document.write("<td>"+"<%=view.getAname()%>" +"</td>");
document.write("<td>"+"&nbsp;<%=view.getAdesc()%>"+"</td>");
document.write("</tr>");
document.write("</tr>");
</script>
<%
}
sc.clear();
}

if (flag.equals("Pub")){

    sc=(ArrayList)request.getAttribute("search");

 out.print("<table border=1 bordercolor=#008000 align=center cellspacing=0 >");
 out.print("<tr><th>S.No<th>Publisher&nbsp;Name<th>Short&nbsp;Desc</tr>");
         Iterator it=sc.iterator();
	while(it.hasNext()){
		BookSearchBean view=(BookSearchBean) it.next();
		
		%>
<tr onmouseover=this.style.color='red' onmouseout=this.style.color='black' onclick='show("<%=view.getSpcode()%>","<%=view.getSpname()%>")'>
<script language=javascript>
// document.Book_Find.name.value="<%=request.getParameter("name")%>";
//document.write("<td>"+"<%=view.getSpcode()%>" +"</td>");
document.write("<td>"+"<%=i++%>" +"</td>");			
document.write("<td>"+"<%=view.getSpname()%>" +"</td>");
document.write("<td>"+"&nbsp;<%=view.getSpdesc()%>"+"</td>");
document.write("</tr>");
</script>
<%
}
sc.clear();
}

if (flag.equals("Sub")){

    sc=(ArrayList)request.getAttribute("search");

 out.print("<table border=1 bordercolor=#008000 align=center cellspacing=0 >");
 out.print("<tr><th>S.No<th>Subject&nbsp;Name<th>Short&nbsp;Desc</tr>");
         Iterator it=sc.iterator();
	while(it.hasNext()){
		BookSearchBean view=(BookSearchBean) it.next();

		%>
<tr onmouseover=this.style.color='red' onmouseout=this.style.color='black' onclick='show("<%=view.getScode()%>","<%=view.getSname()%>")'>
<script language=javascript>
//document.write("<td>"+"<%=view.getScode()%>" +"</td>");
document.write("<td>"+"<%=i++%>" +"</td>");			
document.write("<td>"+"<%=view.getSname()%>" +"</td>");
document.write("<td>"+"&nbsp;<%=view.getSdesc()%>"+"</td>");
document.write("</tr>");
</script>
<%
}
sc.clear();
}

if (flag.equals("Dept")){

    sc=(ArrayList)request.getAttribute("search");

 out.print("<table border=1 bordercolor=#008000 align=center cellspacing=0 >");
 out.print("<tr><th>S.No<th>Department&nbsp;Name<th>Short&nbsp;Desc</tr>");
         Iterator it=sc.iterator();
	while(it.hasNext()){
		BookSearchBean view=(BookSearchBean) it.next();
		%>
<tr onmouseover=this.style.color='red' onmouseout=this.style.color='black' onclick='show("<%=view.getDcode()%>","<%=view.getDname()%>")'>
<script language=javascript>
//document.write("<td>"+"<%=view.getDcode()%>" +"</td>");
document.write("<td>"+"<%=i++%>" +"</td>");			
document.write("<td>"+"<%=view.getDname()%>" +"</td>");
document.write("<td>"+"&nbsp;<%=view.getDdesc()%>"+"</td>");
document.write("</tr>");
</script>
<%
}
sc.clear();
}

if (flag.equals("Bud")){

    sc=(ArrayList)request.getAttribute("search");

 out.print("<table border=1 bordercolor=#008000 align=center cellspacing=0 >");
 out.print("<tr><th>S.No<th>Budget&nbsp;Name<th>Bud&nbsp;Amount</tr>");
         Iterator it=sc.iterator();
	while(it.hasNext()){
		BookSearchBean view=(BookSearchBean) it.next();
	%>
<tr onmouseover=this.style.color='red' onmouseout=this.style.color='black' onclick='show("<%=view.getBudcode()%>","<%=view.getBudhead()%>")'>
<script language=javascript>
//document.write("<td>"+"<%=view.getBudcode()%>" +"</td>");
document.write("<td>"+"<%=i++%>" +"</td>");			
document.write("<td>"+"<%=view.getBudhead()%>" +"</td>");
document.write("<td>"+"&nbsp;<%=view.getBudamt()%>"+"</td>");
document.write("</tr>");
</script>
<%
}
sc.clear();
}

if (flag.equals("Sup")){
    
    sc=(ArrayList)request.getAttribute("search");

 out.print("<table border=1 bordercolor=#008000 align=center cellspacing=0 >");
 out.print("<tr><th>S.No<th>Supplier&nbsp;Name<th>Short&nbsp;Desc</tr>");
         Iterator it=sc.iterator();
	while(it.hasNext()){
		BookSearchBean view=(BookSearchBean) it.next();
		%>
<tr onmouseover=this.style.color='red' onmouseout=this.style.color='black' onclick='show("<%=view.getSpcode()%>","<%=view.getSpname()%>")'>
<script language=javascript>
//document.write("<td>"+"<%=view.getSpcode()%>" +"</td>");
document.write("<td>"+"<%=i++%>" +"</td>");			
document.write("<td>"+"<%=view.getSpname()%>" +"</td>");
document.write("<td>"+"&nbsp;<%=view.getSpdesc()%>"+"</td>");
document.write("</tr>");
</script>
<%
}
sc.clear();
}




}
}catch (Exception e) {out.println(e.toString());}
finally{
sc.clear();
}
%>

<script  language="javascript">
function show(vname,vall){
if(document.Bib_Find.sflag.value=="Dept"){
window.opener.document.Biblio.option_name.value=vall;
}
if(document.Bib_Find.sflag.value=="Sub"){
window.opener.document.Biblio.option_name.value=vall;
}
if(document.Bib_Find.sflag.value=="Pub"){
window.opener.document.Biblio.option_name.value=vall;
}
if(document.Bib_Find.sflag.value=="Sup"){
window.opener.document.Biblio.option_name.value=vall;
}
if(document.Bib_Find.sflag.value=="Bud"){
window.opener.document.Biblio.option_name.value=vall;
}
if(document.Bib_Find.sflag.value=="Aut"){
window.opener.document.Biblio.option_name.value=vall;
}


window.close();
}
function focuss(){
document.Bib_Find.name.focus();
}
function search(){
document.Bib_Find.submit();
}
</script>
