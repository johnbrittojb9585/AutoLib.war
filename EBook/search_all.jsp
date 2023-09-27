<%@ page language="java" session="true" buffer="12kb" import="java.sql.*,java.util.*" import="Lib.Auto.EBook.EBookBean" import="Lib.Auto.EBook.EBookSearchBean" import="Lib.Auto.Author.AuthorBean" import="Common.Security" %>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/button_css.css" />
<%//Security.checkSecurity(1,session,response,request);%>
<!--
//////////////////////////////////////DATABASE CONNECTION//////////////////////////////////////////////////////////////////////// -->

<%
//
//   Filename: search_all.jsp
//   Form name:Ebook_Find
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
<form name="EBook_Find" method="post" action=./EBookSearch>
<!--
//////////////////////////////////////JAVA SERVER PAGES SCRIPT ///////////////////////////////////////////////// -->
<%
String flag=request.getParameter("flag");


String SQLstr="";
String Title="",mainTitle="";
String t1="",t2="",t3="",t4="";
if (flag!=null){
if (flag.equals("accessNo")){
mainTitle="Title Search";Title="Title";t1="Access_No";t2="Title";t3="Author_Name";t4="access";

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

else if (flag.equals("Pub")){
 mainTitle="Publisher Search";Title="Publisher Name";t1="SP_Code";t2="SP_Name";t3="Short_Desc";t4="publisher";
}
 else if (flag.equals("Sup")){
		 mainTitle="Supplier Search";Title="Supplier Name";t1="SP_Code";t2="SP_Name";t3="Short_Desc";t4="supplier";
	}
}
%>
<!--
//////////////////////////////////////FORM CONTROLS//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// -->
<div align=center><h2><%=mainTitle%></h2></div>
<div align=right><A href=""  onclick="window.close()">Back</A></div>

<p align="center">
<%=Title%>&nbsp;<input type=text id=tb1 name=name>
<input type=button value="Search" Class="submitButton"  onclick="search()">
</p>
<!--<center><font  face="verdana" size="1">(Enter minimum three letters) </center>-->

<input type=hidden name=sflag value="<%=flag%>">

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
  document.EBook_Find.name.value="<%=request.getParameter("name")%>";
  </script>
  <%

  int i=1;
  
  if (flag.equals("accessNo")){

	  
               sc=(ArrayList)request.getAttribute("search");

              // out.println(sc.size());

		    out.print("<table border=1 bordercolor=#008000 align=center cellspacing=0 >");
		    out.print("<tr><th>S.No<th>Access&nbsp;No<th>Title<th>Author</tr>");
                    Iterator it=sc.iterator();
			while(it.hasNext()){
				EBookBean view=(EBookBean) it.next();
				%>
		<tr onmouseover=this.style.color='red' onmouseout=this.style.color='black' onclick='show("<%=view.getAccessNo()%>")'>
		<script language=javascript>
		 document.write("<td>"+"<%=i++%>" +"</td>");		
		 document.write("<td>"+"<%=view.getAccessNo()%>" +"</td>");
		 document.write("<td>"+"<%=view.getTitle()%>" +"</td>");
		 document.write("<td>"+"&nbsp;<%=view.getAuthorName()%>"+"</td>");
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
           	EBookSearchBean view=(EBookSearchBean) it.next();
						%>
<tr onmouseover=this.style.color='red' onmouseout=this.style.color='black' onclick='showAuthor("<%=view.getAcode()%>","<%=view.getAname()%>","<%=view.getAdesc()%>")'>
<script language=javascript>
document.write("<td>"+"<%=i++%>" +"</td>");

document.write("<td>"+"<%=view.getAname()%>" +"</td>");
document.write("<td>"+"&nbsp;<%=view.getAdesc()%>"+"</td>");
document.write("</tr>");

</script>
<%
}
sc.clear();
}

						
		if (flag.equals("Pub")){

               sc=(ArrayList)request.getAttribute("search");

		    out.print("<table border=1 bordercolor=#008000 align=center cellspacing=0 >");
		    out.print("<tr><th>S.No<th>Pub&nbsp;Name<th>Short&nbsp;Desc</tr>");
                    Iterator it=sc.iterator();
			while(it.hasNext()){
				EBookSearchBean view=(EBookSearchBean) it.next();
				
							%>
		
		<tr onmouseover=this.style.color='red' onmouseout=this.style.color='black' onclick='showPub("<%=view.getSpcode()%>","<%=view.getSpname()%>")'>					
		
		<script language=javascript>
				
		 document.write("<td>"+"<%=i++%>" +"</td>");
		 
		 document.write("<td>"+"<%=view.getSpname()%>" +"</td>");
		 document.write("<td>"+"&nbsp;<%=view.getSpdesc()%>"+"</td>");
		 
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
				EBookSearchBean view=(EBookSearchBean) it.next();
				
							%>
		
		<tr onmouseover=this.style.color='red' onmouseout=this.style.color='black' onclick='showSup("<%=view.getSpcode()%>","<%=view.getSpname()%>")'>					
		
		<script language=javascript>
				
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
		    out.print("<tr><th>S.No<th>Sub&nbsp;Name<th>Call&nbsp;No<th>Location</tr>");
                 Iterator it=sc.iterator();
			while(it.hasNext()){
				EBookSearchBean view=(EBookSearchBean) it.next();
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
				EBookSearchBean view=(EBookSearchBean) it.next();
				%>
		<tr onmouseover=this.style.color='red' onmouseout=this.style.color='black' onclick='showCallNo("<%=view.getScode()%>","<%=view.getSname()%>","<%=view.getBdesc()%>","<%=view.getSdesc()%>")'>
		<script language=javascript>
  
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

				
		if (flag.equals("Dept")){
            sc=(ArrayList)request.getAttribute("search");

		    out.print("<table border=1 bordercolor=#008000 align=center cellspacing=0 >");
		    out.print("<tr><th>S.No<th>Dept&nbsp;Name<th>Short&nbsp;Desc</tr>");
                 Iterator it=sc.iterator();
			while(it.hasNext()){
				EBookSearchBean view=(EBookSearchBean) it.next();
				%>
		<tr onmouseover=this.style.color='red' onmouseout=this.style.color='black' onclick='showDept("<%=view.getDcode()%>","<%=view.getDname()%>")'>
		<script language=javascript>
		 
		 document.write("<td>"+"<%=i++%>" +"</td>");

		 document.write("<td>"+"<%=view.getDname()%>" +"</td>");
		 document.write("<td>"+"&nbsp;<%=view.getDdesc()%>"+"</td>");
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
function show(val){

if(document.EBook_Find.sflag.value=="accessNo"){
window.opener.document.ebook.accessNo.value=val;
window.opener.document.ebook.method="post";
window.opener.document.ebook.flag.value="search";

window.opener.document.ebook.submit();
}

if(document.EBook_Find.sflag.value=="Aut")
{
	window.opener.document.ebook.authorName.value=vall;
	window.opener.document.ebook.Author_value.value=val;
}	

if(document.EBook_Find.sflag.value=="Dept")
{
window.opener.document.ebook.Dept.value=val;
window.opener.document.ebook.deptName.value=vall;
}
if(document.EBook_Find.sflag.value=="Sub")
{
window.opener.document.ebook.Sub.value=val;
window.opener.document.ebook.subName.value=vall;
}

if(document.EBook_Find.sflag.value=="callNo")
{
window.opener.document.ebook.callNo.value=val;
window.opener.document.ebook.subName.value=vall;
}

if(document.EBook_Find.sflag.value=="Pub"){
window.opener.document.ebook.Pub.value=val;
window.opener.document.ebook.pubName.value=vall;
}
 if(document.EBook_Find.sflag.value=="Sup"){
	window.opener.document.ebook.Sup.value=val;
	window.opener.document.ebook.supName.value=vall;
	}
	
window.close();
}

function showAuthor(val,val1){
	
		if(document.EBook_Find.sflag.value=="Aut"){
		window.opener.document.ebook.Author_value.value=val;
		window.opener.document.ebook.authorName.value=val1;
		
		}
		window.close();
		} 
	
function showPub(val,vall){
if(document.EBook_Find.sflag.value=="Pub"){
window.opener.document.ebook.Pub.value=val;
window.opener.document.ebook.pubName.value=vall;

}
window.close();
}
function showSup(val,vall){
if(document.EBook_Find.sflag.value=="Sup"){
window.opener.document.ebook.Sup.value=val;
window.opener.document.ebook.supName.value=vall;
}
window.close();
}

function showDept(val,vall){

	if(document.EBook_Find.sflag.value=="Dept"){
	window.opener.document.ebook.Dept.value=val;
	window.opener.document.ebook.deptName.value=vall;

	}
	window.close();
	}


function showSubject(val,vall,val2){

if(document.EBook_Find.sflag.value=="Sub")
{
window.opener.document.ebook.Sub.value=val;
window.opener.document.ebook.subName.value=vall;

if(window.opener.document.ebook.callNo.value=='' && val2!='null')
{	
 window.opener.document.ebook.callNo.value=val2;
}

}
window.close();
}


function showCallNo(val,vall,val2){

if(document.EBook_Find.sflag.value=="callNo")
{
window.opener.document.ebook.Sub.value=val;
window.opener.document.ebook.subName.value=vall;

if(window.opener.document.ebook.callNo.value=='' || val2!='null')
{	
 window.opener.document.ebook.callNo.value=val2;
}

}
window.close();
}

function onkey(){
  document.EBook_Find.submit();
}
function SaveRec(){
alert("ddddddddddddddddd");
  document.EBook_Find.flag.value="SaveAuthor";
  document.EBook_Find.submit();
}
function SerchRec(){

  document.EBook_Find.submit();

}
function search(){

  document.EBook_Find.submit();
}
function insert(){

 if(document.EBook_Find.sflag.value=="Sub")
 {
  if(document.EBook_Find.name.value==""){
  window.close();
  }else{
  window.opener.document.ebook.subName.value=document.EBook_Find.name.value;
  }
 }
 if(document.EBook_Find.sflag.value=="Dept")
  {
  if(document.EBook_Find.name.value==""){
  window.close();
  }else{
  window.opener.document.ebook.deptName.value=document.EBook_Find.name.value;
  }
  }
   if(document.EBook_Find.sflag.value=="Pub"){
  if(document.EBook_Find.name.value==""){
  window.close();
  }else{
  window.opener.document.ebook.pubName.value=document.EBook_Find.name.value;
  }
 }
   if(document.EBook_Find.sflag.value=="Sup"){
	   if(document.EBook_Find.name.value==""){
	   window.close();
	   }else{
	   window.opener.document.ebook.supName.value=document.EBook_Find.name.value;
	   }
	  }
  window.close();
}
function focuss(){
	document.EBook_Find.title.focus();
}
</script>

