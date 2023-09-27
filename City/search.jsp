<%@ page language="java"  session="true" buffer="12kb" import="Lib.Auto.City.CityBean" import="Lib.Auto.Subject.subjectbean" import="Common.Security" import="java.util.*"%>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/button_css.css" />
<%//Security.checkSecurity(1,session,response,request);%>
<jsp:useBean id="bean" scope="page" class="Lib.Auto.City.CityBean"/>

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
<form name="City_Find" method="post" action=./CityServlet >

<!--
//////////////////////////////////////JAVA SERVER PAGES SCRIPT ///////////////////////////////////////////////// --> 
<!--
//////////////////////////////////////FORM CONTROLS//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// -->
<h2>City&nbsp;Search</h2>
<div><A href=""  onclick="window.close()">Back</A></div>
<center>City&nbsp;Name<input type=text name=name>
<input Class="submitButton" type=submit value="Search">
<input type=hidden name=flag  value="City">
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
	       
			   document.City_Find.submit();		
			</script>
			<%
			
		}	
if(ck!=null){
             sc=bean.getAl();
      %>
      
  <script language="JavaScript">
document.City_Find.name.value="<%=request.getParameter("nam")%>";
document.City_Find.name.focus();
</script>
<%
			 out.print("<table border=1 bordercolor=#008000 align=center cellspacing=0 >");
		    out.print("<tr><th>City Name<th>State<th>Country<th>Pincode<th>Short Desc</tr>");
		    
		    for(int i=0; i<sc.size();i++){
		    	
		%>
		<tr onmouseover=this.style.color='red' onmouseout=this.style.color='black' onclick='show("<%=sc.get(i)%>")'>
		<script language=javascript>
		 //document.write("<td>"+"<%=sc.get(i)%>" +"</td>");
		 document.write("<td>"+"<%=sc.get(++i)%>" +"</td>");
		 document.write("<td>"+"<%=sc.get(++i)%>" +"</td>");
		 document.write("<td>"+"<%=sc.get(++i)%>" +"</td>");
		 document.write("<td>"+"<%=sc.get(++i)%>" +"</td>");
		 document.write("<td>"+"&nbsp;<%=sc.get(++i)%>"+"</td>");
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
window.opener.document.City.code.value=val;
window.opener.document.City.method="get";
window.opener.document.City.flag.value="search";
window.opener.document.City.action="./CityServlet";
window.opener.document.City.submit();
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
if(document.City_Find.name.value=="")
{
return true;
}
else
{
return false;
}
}
function search(){
document.City_Find.submit();
}
function focuss(){
document.City_Find.name.focus();
}
</script>
