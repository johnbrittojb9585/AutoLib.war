<%@ page language="java"  session="true" buffer="12kb" import="Lib.Auto.Simples.Searchbean" import="Common.Security" import="java.util.*"%>
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
<form name="Author_Find" method="post" action=./AuthorServlet >

<!--
//////////////////////////////////////JAVA SERVER PAGES SCRIPT ///////////////////////////////////////////////// --> 
<!--
//////////////////////////////////////FORM CONTROLS//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// -->
<div><H2>Simple&nbsp;Search</h2></div>
<div><A href="" onclick="window.close()">Back</A></div>
<center>

<input type=hidden name=flag  value="Author">
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



ArrayList sc=new ArrayList();
try{



             sc=(ArrayList)request.getAttribute("SearchArrylist");
      %>
  <script language="JavaScript">

</script>
<%
			out.print("<table border=1 bordercolor=#800000 align=center width=900 cellspacing=0 >");
		    out.print("<tr><th>Access No<th>Title<th>Call No<th>Status</tr>");
            Iterator it=sc.iterator();

			while(it.hasNext()){
				Searchbean view=(Searchbean) it.next();
				

				
				%>
		<tr>
		<script language=javascript>
		 document.write("<td>"+"<%=view.getAccno()%>" +"</td>");
		 document.write("<td>"+"<%=view.getTitle()%>" +"</td>");
		 document.write("<td>"+"&nbsp;<%=view.getCallno()%>"+"</td>");
		 document.write("<td>"+"&nbsp;<%=view.getavailability()%>"+"</td>");
		 document.write("</tr>");    
 		</script>	
		<%
			}
			
			sc.clear();











 }catch (Exception e) {e.printStackTrace();}
   finally{
    sc.clear();
    }
%>
<script  language="javascript">
function show(val){
window.opener.document.Author.code.value=val;
window.opener.document.Author.method="get";
window.opener.document.Author.flag.value="search";
window.opener.document.Author.action="./AuthorServlet";
window.opener.document.Author.submit();
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
if(document.Author_Find.name.value=="")
{
return true;
}
else
{
return false;
}
}
function search(){
document.Author_Find.submit();
}
function focuss(){
document.Author_Find.name.focus();
}
</script>
