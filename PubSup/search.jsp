<%@ page language="java" session="true" buffer="12kb" import="java.sql.*" import="Lib.Auto.Subject.subjectbean" import="Common.Security" import="java.util.*"%>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/button_css.css" />
<%//Security.checkSecurity(1,session,response,request);%>
<!--
//////////////////////////////////////DATABASE CONNECTION//////////////////////////////////////////////////////////////////////// -->
<%
//
//   Filename: Search.jsp
//   Form name:pub_Findb
//%>
<!--
//////////////////////////////////////FORM CONTROLS//////////////////////////////////////////////////////////////////////// -->
<html>
<head>
<title>AutoLib</title>
<meta http-equiv="pragma" content="no-cache"/>
</head>
<link rel="stylesheet" type="text/css" href="/AutoLib/style.css">
<body background="/AutoLib/soft.jpg" onload="focuss()">
<form name="pub_Find" method="post" action=./PubSupServlet ><!--onsubmit="return validate()"-->

<%
String flag=request.getParameter("flag");
String valid=request.getParameter("valid");
String mainTitle="";
String Title="";
String t1="",t2="",t3="";
if (flag!=null){
//out.print("Saravnan"+flag);
if (flag.equals("Sup")){
mainTitle="Supplier"; Title="Supplier&nbsp;Name";t1="Supplier&nbsp;Code";t2="Supplier&nbsp;Name";
}
else if (flag.equals("Pub")){
		mainTitle="Supplier"; Title="Supplier&nbsp;Name";t1="Supplier&nbsp;Code";t2="Supplier&nbsp;Name";
		
	}
}
%>
<!--
//////////////////////////////////////FORM CONTROLS//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// -->
<h2><%=mainTitle%>&nbsp;Search</h2>
<div><A href=""  onclick="window.close()">Back</A></div>
<center><%=Title%><input type=text name=name>
<input type=submit Class="submitButton" value="Search" ></center>
<input type=hidden name=flag value="<%=flag%>">
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
document.pub_Find.name.value="<%=request.getParameter("nam")%>";
document.pub_Find.name.focus();
</script>
<%
		out.print("<table border=1 bordercolor=#008000 align=center cellspacing=0 >");
		out.print("<tr><th>S.No</th><th>"+t2+"</th><th>Short Desc</th></tr>");
		Iterator it=sc.iterator();
		while(it.hasNext()){
			subjectbean view=(subjectbean) it.next();
			%>
	<tr onmouseover=this.style.color='red' onmouseout=this.style.color='black' onclick='show("<%=view.getCode()%>")'>
	<script language=javascript>
	 //document.write("<td>"+"<%=view.getCode()%>" +"</td>");
 	 document.write("<td>"+"<%=i++%>" +"</td>");
	 document.write("<td>"+"<%=view.getName()%>" +"</td>");
	 document.write("<td>&nbsp;"+"<%=view. getDesc()%>"+"</td>");
	 document.write("</tr>");
 		</script>	
		<%

		}
	 }
 }catch (Exception e) {out.println(e.toString());}
   finally{
    sc.clear();
    }
%>
<script  language="javascript">
function show(val){
window.opener.document.pubsup.sp_no.value=val;
window.opener.document.pubsup.method="get";
window.opener.document.pubsup.flag.value="search";
window.opener.document.pubsup.action="./PubSupServlet";
window.opener.document.pubsup.submit();
window.close();
}

function validate()
{
if(t1())
{
alert("Please enter the Name");
return false;
}
 else
{
return true;
}

   }
function t1()
{
if(document.pub_Find.name.value=="")
{
return true;
}
else
{
return false;
}
}
function focuss(){
document.pub_Find.name.focus();
}
function search(){
document.pub_Find.submit();
}
</script>

