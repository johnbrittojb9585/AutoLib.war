<%@ page language="java" session="true" buffer="12kb" import="java.sql.*,java.util.*" import="Lib.Auto.Author.AuthorBean" import="Common.Security"  import="Lib.Auto.Subject.subjectbean" import="Lib.Auto.Book.bookbean" import="Lib.Auto.Book.BookSearchBean"%>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/button_css.css" />
<%//Security.checkSecurity(1,session,response,request);%>
<!--
//////////////////////////////////////DATABASE CONNECTION//////////////////////////////////////////////////////////////////////// -->

<%
//
//   Filename: Search.jsp
//   Form name:book_find
//%>
<!--
//////////////////////////////////////FORM CONTROLS//////////////////////////////////////////////////////////////////////// -->
<html>
<head>
<title>Auto Lib</title>
<meta http-equiv="pragma" content="no-cache"/>
<link rel="stylesheet" type="text/css" href="/AutoLib/style.css">
</head>
<!--<body background="/AutoLib/soft.jpg" -->
<body background="/AutoLib/soft.jpg" onload="focuss()">
<form name="Book_Find" method="post" action=./BookSearch>
<!--
//////////////////////////////////////JAVA SERVER PAGES SCRIPT ///////////////////////////////////////////////// -->
<%
String flag=request.getParameter("flag");
String doctype=request.getParameter("docType");


//out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaa"+doctype);

String SQLstr="";
String Title="",mainTitle="";
String t1="",t2="",t3="",t4="";
if (flag!=null){
if (flag.equals("accessNoAuth")){
mainTitle="Title Search";Title="Title";t1="Access_No";t2="Title";t3="Author_Name";t4="access";
// document.Book_Find.author_text.disabled=false;
//document.getElementById("author_text").style.display='block';
}
else if (flag.equals("Dept")){
 mainTitle="Department Search";Title="Department Name";t1="Dept_Code";t2="Dept_Name";t3="Short_Desc";t4="department";

}
else if (flag.equals("Aut")){
 mainTitle="Author Search";Title="Author Name";t2="Author_Name";t1="Author_Code";t3="Short_Desc";t4="author";
}
else if (flag.equals("Sub")){
 mainTitle="Subject Search";Title="Subject Name";t1="Sub_Code";t2="Sub_Name";t3="Short_Desc";t4="subject";

}

else if (flag.equals("callNo")){
mainTitle="Call No Search";Title="Call No";t1="Sub_Code";t2="Sub_Name";t3="Short_Desc";t4="subject";
}

else if (flag.equals("Sup")){
 mainTitle="Supplier Search";Title="Supplier Name";t1="SP_Code";t2="SP_Name";t3="Short_Desc";t4="supplier";

}
else if (flag.equals("Pub")){
 mainTitle="Publisher Search";Title="Publisher Name";t1="SP_Code";t2="SP_Name";t3="Short_Desc";t4="publisher";

}
else if (flag.equals("Group")){
 mainTitle="Group Search";Title="Group Name";t1="Group_Code";t2="Group_Name";t3="Remarks";t4="grp";
}
else if (flag.equals("Inv")){
 mainTitle="Invoice Search";Title="Invoice";t1="Inv_no";t2="inv_date";t3="inv_amount";t4="invoice";
}
else if (flag.equals("Bud")){
 mainTitle="Budget Search";Title="Budget&nbsp;Code";t1="bud_code";t2="bud_head";t3="bud_Amount";t4="budget";
}
else if (flag.equals("Branch")){
 mainTitle="Division Search";Title="Division Head ";t1="branch_code";t2="branch_name";t3="short_desc";t4="bra";
}
else if (flag.equals("Key")){
 mainTitle="Keywords Search";Title="Keywords Name ";t1="Keywords_code";t2="keywords_name";t3="short_desc";t4="keywords";

}
}
%>
<!--
//////////////////////////////////////FORM CONTROLS//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// -->
<div align=center><h2><%=mainTitle%></h2></div>
<div align=right><A href=""  onclick="window.close()">Back</A></div>


<p align="center">
Author <input type=text name=author>
<%=Title%>&nbsp;<input type=text id=tb1 name=name>
<input type=button value="Search" Class="submitButton"  onclick="search()">
</p>
<!--<center><font  face="verdana" size="1">(Enter minimum three letters) </center>-->

<input type=hidden name=sflag value="<%=flag%>">
<input type=hidden name=docType value="<%=doctype%>">
</form>
</body>
</html>
<!--
//////////////////////////////////////JAVA SERVER PAGES SCRIPT /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// -->
<%
String ck=request.getParameter("check");
ArrayList sc=new ArrayList();
ArrayList sc1=new ArrayList();
try{
if(ck!=null){
  %>
  <script language="JavaScript">
  document.Book_Find.name.value="<%=request.getParameter("name")%>";
  document.Book_Find.author.value="<%=request.getParameter("author")%>";
  </script>
  <%

  int i=1;
  
  if (flag.equals("accessNoAuth")){

	  
               sc=(ArrayList)request.getAttribute("search");

              // out.println(sc.size());

		    out.print("<table border=1 bordercolor=#008000 align=center cellspacing=0 >");
		    out.print("<tr><th>S.No<th>Access&nbsp;No<th>Title<th>Author</tr>");
                    Iterator it=sc.iterator();
			while(it.hasNext()){
				subjectbean view=(subjectbean) it.next();
				%>
		<tr onmouseover=this.style.color='red' onmouseout=this.style.color='black' onclick='show("<%=view.getEmail()%>")'>
		<script language=javascript>
		// document.Book_Find.name.value="<%=request.getParameter("name")%>";
		 document.write("<td>"+"<%=i++%>" +"</td>");		
		 document.write("<td>"+"<%=view.getEmail()%>" +"</td>");
		 document.write("<td>"+"<%=view.getName()%>" +"</td>");
		 document.write("<td>"+"&nbsp;<%=view.getDesc()%>"+"</td>");
		 document.write("</tr>");
 		</script>
		<%
		}
		sc.clear();
		}

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
//		 document.write("<td>"+"<%=view.getAcode()%>" +"</td>");
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
		
		

		
		
		

		if (flag.equals("Key")){

               sc=(ArrayList)request.getAttribute("search");

		    out.print("<table border=1 bordercolor=#008000 align=center cellspacing=0 >");
		    out.print("<tr><th>S.No<th>Keywords&nbsp;Name<th>Short&nbsp;Desc</tr>");
                    Iterator it=sc.iterator();
			while(it.hasNext()){
				BookSearchBean view=(BookSearchBean) it.next();
								%>
		<tr onmouseover=this.style.color='red' onmouseout=this.style.color='black' onclick='show("<%=view.getKcode()%>","<%=view.getKname()%>")'>
		<script language=javascript>
//		 document.write("<td>"+"<%=view.getKcode()%>" +"</td>");
		 document.write("<td>"+"<%=i++%>" +"</td>");
		 document.write("<td>"+"<%=view.getKname()%>" +"</td>");
		 document.write("<td>"+"&nbsp;<%=view.getKdesc()%>"+"</td>");
		 document.write("</tr>");
 		</script>
		<%
		}
		sc.clear();
		}

		if (flag.equals("Pub")){

               sc=(ArrayList)request.getAttribute("search");

		    out.print("<table border=1 bordercolor=#008000 align=center cellspacing=0 >");
		    out.print("<tr><th>S.No<th>Pub&nbsp;Name<th>Short&nbsp;Desc<th>Publisher&nbsp;Place</tr>");
                    Iterator it=sc.iterator();
			while(it.hasNext()){
				BookSearchBean view=(BookSearchBean) it.next();
				if(view.getSpcode() == 3)
							%>
		<tr onmouseover=this.style.color='red' onmouseout=this.style.color='black' onclick='showPub("<%=view.getSpcode()%>","<%=view.getSpname()%>","<%=view.getGdesc()%>")'>
		<script language=javascript>
		 //document.write("<td>"+"<%=view.getSpcode()%>" +"</td>");
		 document.write("<td>"+"<%=i++%>" +"</td>");		 
		 document.write("<td>"+"<%=view.getSpname()%>" +"</td>");
		 document.write("<td>"+"&nbsp;<%=view.getSpdesc()%>"+"</td>");
 		 document.write("<td>"+"&nbsp;<%=view.getGdesc()%>"+"</td>");
		 document.write("</tr>");
 		</script>
		<%
		}
		sc.clear();
		}

		if (flag.equals("Sub")){

               sc=(ArrayList)request.getAttribute("search");

		    out.print("<table border=1 bordercolor=#008000 align=center cellspacing=0 >");
		    out.print("<tr><th>S.No<th>Sub&nbsp;Name<th>Call&nbsp;No<th>Location</tr>");
                    Iterator it=sc.iterator();
			while(it.hasNext()){
				BookSearchBean view=(BookSearchBean) it.next();
				%>
		<tr onmouseover=this.style.color='red' onmouseout=this.style.color='black' onclick='showSubject("<%=view.getScode()%>","<%=view.getSname()%>","<%=view.getBdesc()%>","<%=view.getSdesc()%>")'>
		<script language=javascript>
//		 document.write("<td>"+"<%=view.getScode()%>" +"</td>");
		 document.write("<td>"+"<%=i++%>" +"</td>");
		 document.write("<td>"+"<%=view.getSname()%>" +"</td>");
		 document.write("<td>"+"<%=view.getBdesc()%>" +"</td>");		 
		 document.write("<td>"+"&nbsp;<%=view.getSdesc()%>"+"</td>");
		 document.write("</tr>");
 		</script>
		<%
		}
		sc.clear();
		}
		
		if (flag.equals("callNo")){

            sc=(ArrayList)request.getAttribute("search");

		    out.print("<table border=1 bordercolor=#008000 align=center cellspacing=0 >");
		    out.print("<tr><th>S.No<th>Call&nbsp;No<th>Subject&nbsp;Name<th>Location</tr>");
                 Iterator it=sc.iterator();
			while(it.hasNext()){
				BookSearchBean view=(BookSearchBean) it.next();
				%>
		<tr onmouseover=this.style.color='red' onmouseout=this.style.color='black' onclick='showCallNo("<%=view.getScode()%>","<%=view.getSname()%>","<%=view.getBdesc()%>","<%=view.getSdesc()%>")'>
		<script language=javascript>
//		 document.write("<td>"+"<%=view.getScode()%>" +"</td>");
		 document.write("<td>"+"<%=i++%>" +"</td>");
		 document.write("<td>"+"<%=view.getBdesc()%>" +"</td>");
		 document.write("<td>"+"<%=view.getSname()%>" +"</td>");
		 		 
		 document.write("<td>"+"&nbsp;<%=view.getSdesc()%>"+"</td>");
		 
		 document.write("</tr>");
		</script>
		<%
		}
		sc.clear();
		}

		if (flag.equals("Branch")){

               sc=(ArrayList)request.getAttribute("search");

		    out.print("<table border=1 bordercolor=#008000 align=center cellspacing=0 >");
		    out.print("<tr><th>S.No<th>Division&nbsp;Name<th>Short&nbsp;Desc</tr>");
                    Iterator it=sc.iterator();
			while(it.hasNext()){
				BookSearchBean view=(BookSearchBean) it.next();
				%>
		<tr onmouseover=this.style.color='red' onmouseout=this.style.color='black' onclick='show("<%=view.getBcode()%>","<%=view.getBname()%>")'>
		<script language=javascript>
//		 document.write("<td>"+"<%=view.getBcode()%>" +"</td>");
		 document.write("<td>"+"<%=i++%>" +"</td>");
		 document.write("<td>"+"<%=view.getBname()%>" +"</td>");
		 document.write("<td>"+"&nbsp;<%=view.getBdesc()%>"+"</td>");
		 document.write("</tr>");
 		</script>
		<%
		}
		sc.clear();
		}

		if (flag.equals("Dept")){

               sc=(ArrayList)request.getAttribute("search");

		    out.print("<table border=1 bordercolor=#008000 align=center cellspacing=0 >");
		    out.print("<tr><th>S.No<th>Dept&nbsp;Name<th>Short&nbsp;Desc</tr>");
                    Iterator it=sc.iterator();
			while(it.hasNext()){
				BookSearchBean view=(BookSearchBean) it.next();
				%>
		<tr onmouseover=this.style.color='red' onmouseout=this.style.color='black' onclick='show("<%=view.getDcode()%>","<%=view.getDname()%>")'>
		<script language=javascript>
//		 document.write("<td>"+"<%=view.getDcode()%>" +"</td>");
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
		    out.print("<tr><th>S.No<th>Budget&nbsp;Code<th>Budget&nbsp;Amount</tr>");
                    Iterator it=sc.iterator();
			while(it.hasNext()){
				BookSearchBean view=(BookSearchBean) it.next();
				%>
		<tr onmouseover=this.style.color='red' onmouseout=this.style.color='black' onclick='show("<%=view.getBudcode()%>","<%=view.getBudhead()%>")'>
		<script language=javascript>
//		 document.write("<td>"+"<%=view.getBudcode()%>" +"</td>");
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
		    out.print("<tr><th>S.No<th>Sup&nbsp;Name<th>Short&nbsp;Desc</tr>");
                    Iterator it=sc.iterator();
			while(it.hasNext()){
				BookSearchBean view=(BookSearchBean) it.next();
				%>
		<tr onmouseover=this.style.color='red' onmouseout=this.style.color='black' onclick='show("<%=view.getSpcode()%>","<%=view.getSpname()%>")'>
		<script language=javascript>
//		 document.write("<td>"+"<%=view.getSpcode()%>" +"</td>");
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
function show(val,vall){

if(document.Book_Find.sflag.value=="accessNoAuth"){
window.opener.document.Book.accessNo.value=val;
window.opener.document.Book.method="post";
window.opener.document.Book.flag.value="search";

window.opener.document.Book.doc_type.value=document.Book_Find.docType.value;//SHEK

window.opener.document.Book.submit();
}




if(document.Book_Find.sflag.value=="Aut")
{

if(window.opener.document.Book.author.value!=""){

window.opener.document.Book.author.value=window.opener.document.Book.author.value+";"+vall;
window.opener.document.Book.Author_value.value=window.opener.document.Book.Author_value.value+";"+val;

}
else
{
window.opener.document.Book.author.value=vall;
window.opener.document.Book.Author_value.value=val;

}
}
if(document.Book_Find.sflag.value=="Key")
{
if(window.opener.document.Book.keywords.value!=""){
window.opener.document.Book.keywords.value=window.opener.document.Book.keywords.value+";"+vall;
}
else
{
window.opener.document.Book.keywords.value=vall;

}
}
if(document.Book_Find.sflag.value=="Dept")
{
window.opener.document.Book.Dept.value=val;
window.opener.document.Book.deptName.value=vall;
}
if(document.Book_Find.sflag.value=="Sub")
{
window.opener.document.Book.Sub.value=val;
window.opener.document.Book.subName.value=vall;
}



if(document.Book_Find.sflag.value=="callNo")
{
window.opener.document.Book.callNo.value=val;
window.opener.document.Book.subName.value=vall;
}



if(document.Book_Find.sflag.value=="Pub"){
window.opener.document.Book.Pub.value=val;
window.opener.document.Book.pubName.value=vall;

}
if(document.Book_Find.sflag.value=="Inv"){
window.opener.document.Book.invNo.value=val;
window.opener.document.Book.invoiceDate.value=val;
window.opener.document.Book.InvDateVal.value=vall;
}
if(document.Book_Find.sflag.value=="Bud"){
window.opener.document.Book.Bud.value=val;
window.opener.document.Book.budName.value=vall;
}
if(document.Book_Find.sflag.value=="Sup"){
window.opener.document.Book.Sup.value=val;
window.opener.document.Book.supName.value=vall;
}
if(document.Book_Find.sflag.value=="Branch"){
window.opener.document.Book.Branch.value=val;
window.opener.document.Book.branchName.value=vall;
}

if(document.Book_Find.sflag.value=="Cur"){
window.opener.document.Book.currency.value=vall;
}
window.close();
}
function showPub(val,vall,val2){

if(document.Book_Find.sflag.value=="accessNoAuth"){
window.opener.document.Book.accessNo.value=val;
window.opener.document.Book.method="post";
window.opener.document.Book.flag.value="search";
window.opener.document.Book.submit();
}
if(document.Book_Find.sflag.value=="Pub"){
window.opener.document.Book.Pub.value=val;
window.opener.document.Book.pubName.value=vall;
window.opener.document.Book.place.value=val2;
}
window.close();
}

function showSubject(val,vall,val2,val3){

if(document.Book_Find.sflag.value=="Sub")
{
window.opener.document.Book.Sub.value=val;
window.opener.document.Book.subName.value=vall;

if(window.opener.document.Book.callNo.value=='' && val2!='null')
{	
 window.opener.document.Book.callNo.value=val2;
}

if(window.opener.document.Book.location.value=='' && val3!='null')
{	
 window.opener.document.Book.location.value=val3;
}

}
window.close();
}


function showCallNo(val,vall,val2,val3){

if(document.Book_Find.sflag.value=="callNo")
{
window.opener.document.Book.Sub.value=val;
window.opener.document.Book.subName.value=vall;

if(window.opener.document.Book.callNo.value=='' && val2!='null')
{	
 window.opener.document.Book.callNo.value=val2;
}

if(window.opener.document.Book.location.value=='' && val3!='null')
{	
 window.opener.document.Book.location.value=val3;
}

}
window.close();
}



function onkey(){
  document.Book_Find.submit();
}
function SaveRec(){
alert("ddddddddddddddddd");
  document.Book_Find.flag.value="SaveAuthor";
  document.Book_Find.submit();
}
function SerchRec(){

  document.Book_Find.submit();

}
function search(){

  document.Book_Find.submit();
}
function insert(){

 if(document.Book_Find.sflag.value=="Sub")
 {
  if(document.Book_Find.name.value==""){
  window.close();
  }else{
  window.opener.document.Book.subName.value=document.Book_Find.name.value;
  }
 }
 if(document.Book_Find.sflag.value=="Dept")
  {
  if(document.Book_Find.name.value==""){
  window.close();
  }else{
  window.opener.document.Book.deptName.value=document.Book_Find.name.value;
  }
  }
   if(document.Book_Find.sflag.value=="Pub"){
  if(document.Book_Find.name.value==""){
  window.close();
  }else{
  window.opener.document.Book.pubName.value=document.Book_Find.name.value;
  }
 }

 if(document.Book_Find.sflag.value=="Bud"){
 if(document.Book_Find.name.value==""){
  window.close();
  }else{
  window.opener.document.Book.budName.value=document.Book_Find.name.value;
  }
 }
 if(document.Book_Find.sflag.value=="Sup"){
 if(document.Book_Find.name.value==""){
  window.close();
  }else{
 window.opener.document.Book.supName.value=document.Book_Find.name.value;
 }
 }
 if(document.Book_Find.sflag.value=="Branch"){
 if(document.Book_Find.name.value==""){
  window.close();
  }else{
 window.opener.document.Book.branchName.value=document.Book_Find.name.value;
 }
 }
 window.close();

}
function focuss(){

//var tb1 = document.getElementById('tb1');
tb1.focus(); 
//tb1.value = tb1.value; 
}
</script>

