<%@ page language="java"  session="true" buffer="12kb" import="Common.Security" import="java.util.ArrayList"%>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/button_css.css" />
<%//Security.checkSecurity(1,session,response,request);%>
<jsp:useBean id="bean" scope="page" class="Lib.Auto.Binding.BindingBean"/>
<!--
//////////////////////////////////////DATABASE CONNECTION//////////////////////////////////////////////////////////////////////// -->
<%
//
//   Filename: Search.jsp
//   Form name:Binding Search
//%>
<!--
//////////////////////////////////////FORM CONTROLS//////////////////////////////////////////////////////////////////////// -->
<html>
<head>
<title>AutoLib</title>
<link rel="stylesheet" type="text/css" href="/AutoLib/style.css">
<%
   response.setHeader("Pragma", "No-cache");
   response.setHeader("Cache-Control", "no-cache");
   response.setDateHeader("Expires", 0);
%>
</head>
<body background="/AutoLib/soft.jpg" onload="load()">
<form name="Binding_Find" method="post" action=./BindingServlet ><!--onsubmit="return validate()"-->
<input type=hidden name=flag value=Bind>
<!--
//////////////////////////////////////JAVA SERVER PAGES SCRIPT ///////////////////////////////////////////////// --> 

<!--
//////////////////////////////////////FORM CONTROLS//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// -->
<h2>Binder&nbsp;Search</h2>
<div><A href=""  onclick="window.close()">Back</A></div>
<center>Binder&nbsp;Name<input type=text name=name>
<input type=submit Class="submitButton" value="Search" ></center></form>
</body>
</html>
<!--
//////////////////////////////////////JAVA SERVER PAGES SCRIPT /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// --> 
<%
String ck=request.getParameter("check");
String loadServlet=request.getParameter("flag");
ArrayList sc=new ArrayList();
try{
	
 if(loadServlet.equals("loadServlet"))
		{
	    %>
	         <script language="JavaScript">		
	       
			   document.Binding_Find.submit();		
			</script>
			<%
			
		}	
if(ck!=null){
	
	    int j=1;
		sc=bean.getAl();
		 %>
  <script language="JavaScript">
document.Binding_Find.name.value="<%=request.getParameter("nam")%>";
document.Binding_Find.name.focus();
</script>
<%
		out.print("<table border=1 bordercolor=#008000 align=center cellspacing=0 >");
		out.print("<tr><th>S.No<th>Binder Name<th>Short Desc</tr>");
		for(int i=0; i<sc.size();i++){
		%>
		<tr onmouseover=this.style.color='red' onmouseout=this.style.color='black' onclick='show("<%=sc.get(i)%>")'>
		<script language=javascript>
		 //document.write("<td>"+"<%=sc.get(i)%>" +"</td>");
		 document.write("<td>"+"<%=j++%>" +"</td>");		 
		 document.write("<td>"+"<%=sc.get(++i)%>" +"</td>");
		 document.write("<td>&nbsp;"+"<%=sc.get(++i)%>"+"</td>");
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
window.opener.document.binding.sp_no.value=val;
window.opener.document.binding.method="post";
window.opener.document.binding.flag.value="search";
window.opener.document.binding.action="./BindingServlet";
window.opener.document.binding.submit();
window.close();
}
function focuss(){
document.Binding_Find.name.focus();
}

function validate()
{
if(t1())
{
alert("Please enter the Binder Name");
return false;
}
 else
{
return true;
}

   }

   function t1()
{
if(document.Binding_Find.name.value=="")
{
return true;
}
else
{
return false;
}
}

function load()
{
document.Binding_Find.name.focus();
}
function search(){
document.Binding_Find.submit();
}

</script>
<!--
//////////////////////////////////////JAVA SCRIPT USEING CLIENT SIDE///////////////////////////////////////////////// -->
