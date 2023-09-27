<%@ page language="java"  session="true" buffer="12kb" import="Lib.Auto.Keywords.KeywordsBean" import="Common.Security" import="java.util.*"%>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/button_css.css" />
<%//Security.checkSecurity(1,session,response,request);%>


<!--
//////////////////////////////////////DATABASE CONNECTION//////////////////////////////////////////////////////////////////////// -->
<%
//
//   Filename: Search.jsp
//   Form name:Keywords_Find
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
<form name="Keywords_Find" method="post" action=./KeywordsServlet><!-- onsubmit="return validate()"> -->

<!--
//////////////////////////////////////JAVA SERVER PAGES SCRIPT ///////////////////////////////////////////////// --> 
<!--
//////////////////////////////////////FORM CONTROLS//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// -->
<div><H2>Keywords&nbsp;Search</h2></div>
<div><A href="" onclick="window.close()">Back</A></div>
<center>
<td>Keywords Name:<input type=text name=name></td>
<input type=submit Class="submitButton" value="Search">
<input type=hidden name=flag  value="Keywords">
</center></form>

</body>
</html>

<%


String ck=request.getParameter("check");
String loadServlet=request.getParameter("flag");
ArrayList sc=new ArrayList();
try{
	 if(loadServlet.equals("loadServlet"))
		{
	    %>
	         <script language="JavaScript">		
	       
			   document.Keywords_Find.submit();		
			</script>
			<%
			
		}	
if(ck!=null){
			
	int i=1;
             sc=(ArrayList)request.getAttribute("serarch");
			 
			 out.print("<table border=1 bordercolor=#008000 align=center cellspacing=0 >");
		    out.print("<tr><th>S.No<th>Keyword&nbsp;Name<th>Short&nbsp;Desc</tr>");
           Iterator it=sc.iterator();
			while(it.hasNext()){
				KeywordsBean view=(KeywordsBean) it.next();
				%>
		<tr onmouseover=this.style.color='red' onmouseout=this.style.color='black' onclick='show("<%=view.getKcode()%>")'>
		<script language=javascript>
		 //document.write("<td>"+"<%=view.getKcode()%>" +"</td>");
    	 document.write("<td>"+"<%=i++%>" +"</td>");		 		 
		 document.write("<td>"+"<%=view.getKname()%>" +"</td>");
		 document.write("<td>"+"&nbsp;<%=view.getKdesc()%>"+"</td>");
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
window.opener.document.Keywords.code.value=val;
window.opener.document.Keywords.method="get";
window.opener.document.Keywords.flag.value="search";
window.opener.document.Keywords.action="./KeywordsServlet";
window.opener.document.Keywords.submit();
window.close();
}

function validate()
{
if(t1())
{
alert("Please enter the Keyword Name");
return false;
}
 else
{
return true;
}      

   }
function t1()
{
if(document.Keywords_Find.name.value=="")
{
return true;
}
else
{
return false;
}
}
function focuss(){
document.Keywords_Find.name.focus();
}
</script>
