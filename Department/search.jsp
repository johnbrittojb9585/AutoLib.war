<%@ page language="java"  session="true" buffer="12kb" import="Lib.Auto.Department.DepartmentBean" import="Lib.Auto.Subject.subjectbean" import="Common.Security" import="java.util.*"%>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/button_css.css" />
<%//Security.checkSecurity(1,session,response,request);%>


<!--
//////////////////////////////////////DATABASE CONNECTION//////////////////////////////////////////////////////////////////////// -->
<%
//
//   Filename: Search.jsp
//   Form name:Author_Find
//
%>


<!--
//////////////////////////////////////FORM CONTROLS//////////////////////////////////////////////////////////////////////// -->
<html>
<%
   response.setHeader("Pragma", "No-cache");
   response.setHeader("Cache-Control", "no-cache");
   response.setDateHeader("Expires", 0);
%>
<head>
<title>AutoLib</title>
<link rel="stylesheet" type="text/css" href="/AutoLib/style.css">
</head>
<body background="/AutoLib/soft.jpg" onload="focuss()">
<form name="Department_Find" method="post" action=./DepartmentServlet >

<!--
//////////////////////////////////////JAVA SERVER PAGES SCRIPT ///////////////////////////////////////////////// --> 
<!--
//////////////////////////////////////FORM CONTROLS//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// -->
<h2>Department&nbsp;Search</h2>
<div><A href=""  onclick="window.close()">Back</A></div>
<center>Department&nbsp;Name<input type=text name=name>
<input Class="submitButton" type=submit value="Search" >
<input type=hidden name=flag  value="Department">
</center></form>

</body>
</html>
<!--
//////////////////////////////////////JAVA SERVER PAGES SCRIPT /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// --> 
<!--
//////////////////////////////////////JAVA SCRIPT USEING CLIENT SIDE///////////////////////////////////////////////// --> 

<!--
//////////////////////////////////////JAVA SCRIPT USEING CLIENT SIDE///////////////////////////////////////////////// --> 

<%


String ck=request.getParameter("check");
String loadServlet=request.getParameter("flag");
ArrayList sc=new ArrayList();
try{
	 if(loadServlet.equals("loadServlet"))
	{
    %>
         <script language="JavaScript">		
       
		   document.Department_Find.submit();		
		</script>
		<%
		
	}	
if(ck!=null){

	         int i=1;
             sc=(ArrayList)request.getAttribute("serarch");
      %>
  <script language="JavaScript">
document.Department_Find.name.value="<%=request.getParameter("nam")%>";
document.Department_Find.name.focus();
</script>
<%
			 out.print("<table border=1 bordercolor=#008000 align=center cellspacing=0 >");
		    out.print("<tr><th>S.No<th>Department Name<th>Short Desc</tr>");
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
			
			sc.clear();
}


 }catch (Exception e) {e.printStackTrace();}
   finally{
    sc.clear();
    }
%>
<script  language="javascript">
function show(val){
window.opener.document.Department.code.value=val;
window.opener.document.Department.method="get";
window.opener.document.Department.flag.value="search";
window.opener.document.Department.action="./DepartmentServlet";
window.opener.document.Department.submit();
window.close();
}

function validate()
{
if(t1())
{
alert("Please enter the AuthorName");
return false;
}
 else
{
return true;
}      

   }
function t1()
{
if(document.Department_Find.name.value=="")
{
return true;
}
else
{
return false;
}
}
function search(){
document.Department_Find.submit();
}
function focuss(){
document.Department_Find.name.focus();
}
</script>
