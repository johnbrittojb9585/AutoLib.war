<%@ page language="java" errorPage="/Error/ErrorPage.jsp" session="true" buffer="12kb" import="Common.Security" import="Lib.Auto.Subject.subjectbean" import="java.util.*" import="java.util.ArrayList"%>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/button_css.css" />
<%//Security.checkSecurity(1,session,response,request);%>

<!--
//////////////////////////////////////DATABASE CONNECTION//////////////////////////////////////////////////////////////////////// -->
<%
//
//   Filename: Search.jsp
//   Form name:Course_Find
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
<body background="/AutoLib/soft.jpg" onload="focuss()">
<form name="Course_Find" method="post" action=./CourseServlet ><!--onsubmit="return validate()"-->
<input type=hidden name=flag value="Course">
<!--
//////////////////////////////////////JAVA SERVER PAGES SCRIPT ///////////////////////////////////////////////// --> 

<!--
//////////////////////////////////////FORM CONTROLS//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// -->
<h2>Course&nbsp;Search</h2>
<div><A href=""  onclick="window.close()">Back</A></div>
<center>Course&nbsp;Name<input type=text  name=name>
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
	       
			   document.Course_Find.submit();		
			</script>
			<%
			
		}	
if(ck!=null){
	int i=1;
	sc=(ArrayList)request.getAttribute("serarch");
			     	     %>
  <script language="JavaScript">
document.Course_Find.name.value="<%=request.getParameter("nam")%>";
document.Course_Find.name.focus();
</script>

<%

		out.print("<table border=1 bordercolor=#008000 align=center cellspacing=0 >");
		out.print("<tr><th>S.No<th>Course Name<th>Course Major</tr>");
		
		 Iterator it=sc.iterator();
		 
			while(it.hasNext()){
				subjectbean view=(subjectbean) it.next();
		%>
		<tr onmouseover=this.style.color='red' onmouseout=this.style.color='black' onclick='show("<%=view.getCode()%>")'>
		<script language=javascript>
		 //document.write("<td>"+"<%=view.getCode()%>" +"</td>");
         document.write("<td>"+"<%=i++%>" +"</td>");		 
		 document.write("<td>"+"<%=view.getName()%>" +"</td>");
		 document.write("<td>"+"&nbsp;<%=view. getDesc()%>"+"</td>");
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
window.opener.document.Course.code.value=val;
window.opener.document.Course.method="get";
window.opener.document.Course.flag.value="search";
window.opener.document.Course.action="./CourseServlet";
window.opener.document.Course.submit();
window.close();
}

function validate()
{
if(t1())
{
alert("Please enter the Course Name");
return false;
}
 else
{
return true;
}      

   }
function t1()
{
if(document.Course_Find.name.value=="")
{
return true;
}
else
{
return false;
}
}
function focuss(){
document.Course_Find.name.focus();
}
function search(){
document.Course_Find.submit();
}
</script>
<!--
//////////////////////////////////////JAVA SCRIPT USEING CLIENT SIDE///////////////////////////////////////////////// --> 
