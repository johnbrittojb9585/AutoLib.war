<%@ page language="java" session="true" buffer="12kb" import="java.sql.*,java.util.*" import="Lib.Auto.Binding_Books.BookBindingBean"%>
<%@ include file="/Common.jsp" %>
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
<title>AutoLib</title>

<meta http-equiv="pragma" content="no-cache"/>
<link rel="stylesheet" type="text/css" href="/AutoLib/style.css">
</head>

<body  onload="focuss()">
<form name=Book_Find method=post action=../Binding_Books/BindingSearch>
<!--
//////////////////////////////////////JAVA SERVER PAGES SCRIPT ///////////////////////////////////////////////// -->
<%
String flag=request.getParameter("flag");

String SQLstr="";
String Title="",mainTitle="";
String t1="",t2="",t3="",t4="";
if (flag!=null){
if (flag.equals("accessNo")){
mainTitle="Book Search";Title="Title";t1="Access_No";t2="Title";t3="Author_Name";t4="access";

}

}
%>
<!--
//////////////////////////////////////FORM CONTROLS//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// -->
<h2><%=mainTitle%></h2>
<div align=right><A href=""  onclick="window.close()">Back</A></div>
<center><%=Title%>&nbsp;<input type=text name=name ><input type=button value="Search" onclick="search()"><input type=submit id="insnew" value="Insert" onClick="insert()"></center>
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
  %>
  <script language="JavaScript">
  document.Book_Find.name.value="<%=request.getParameter("name")%>";
  </script>
  <%

  if (flag.equals("accessNo")){

               sc=(ArrayList)request.getAttribute("search");

		    out.print("<table border=1 bordercolor=#008000 align=center cellspacing=0 >");
		    out.print("<tr><th>Access&nbsp;No<th>Title<th>Author</tr>");
                    Iterator it=sc.iterator();
			while(it.hasNext()){
				BookBindingBean view=(BookBindingBean) it.next();
				%>
		<tr onmouseover=this.style.color='red' onmouseout=this.style.color='black' onclick='show("<%=view.getAccess_no()%>")'>
		<script language=javascript>
		 document.write("<td>"+"<%=view.getAccess_no()%>" +"</td>");
		 document.write("<td>"+"<%=view.getTitle()%>" +"</td>");
		 document.write("<td>"+"&nbsp;<%=view.getAuthor()%>"+"</td>");
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
function show(vall){
if(document.Book_Find.sflag.value=="accessNo"){
window.opener.document.Book_Bindings.AccessNo.value=vall;

}

window.close();
}
function onkey(){
  document.Book_Find.submit();
}
function search(){

  document.Book_Find.submit();
}
 function insert(){
  window.close();
 }
function focuss(){
document.Book_Find.name.focus();
document.getElementById('insnew').style.visibility ="hidden";
}
</script>


