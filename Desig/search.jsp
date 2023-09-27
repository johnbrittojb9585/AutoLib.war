<%@ page language="java" session="true" buffer="12kb" import="java.sql.*" import="Lib.Auto.Subject.subjectbean" import="java.util.*" import="Common.Security" import="Lib.Auto.Designation.DesignationBean"%>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/button_css.css" />
<%//Security.checkSecurity(1,session,response,request);%>
<!--
//////////////////////////////////////DATABASE CONNECTION//////////////////////////////////////////////////////////////////////// -->
<%
//
//   Filename: Search.jsp
//   Form name:Desig_Find
//%>
<!--
//////////////////////////////////////FORM CONTROLS//////////////////////////////////////////////////////////////////////// -->
<html>
<head>
<title>AutoLib</title>
<meta http-equiv="pragma" content="no-cache"/>
<link rel="stylesheet" type="text/css" href="/AutoLib/style.css">
</head>
<body background="/AutoLib/soft.jpg" onload="focuss()">
<form name="Desig_Find" method="post" action=./DesignationServlet ><!--onsubmit="return validate()"-->
<input type=hidden name=flag value="Desig">
<!--
//////////////////////////////////////JAVA SERVER PAGES SCRIPT ///////////////////////////////////////////////// --> 

<!--
//////////////////////////////////////FORM CONTROLS//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// -->
<h2> Designation&nbsp;Search</h2>
<div><A href=""  onclick="window.close()">Back</A></div>
<center>Designation&nbsp;Name<input type=text name=name>
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
	       
			   document.Desig_Find.submit();		
			</script>
			<%
			
		}	
if(ck!=null){
	int i=1;
	sc=(ArrayList)request.getAttribute("serarch");
		  %>
  <script language="JavaScript">
document.Desig_Find.name.value="<%=request.getParameter("nam")%>";
document.Desig_Find.name.focus();
</script>
<%
		out.print("<table border=1 bordercolor=#008000 align=center cellspacing=0 >");
		out.print("<tr><th>S.No<th>Designation Name<th>Short Desc</tr>");
		Iterator it=sc.iterator();
        
        while(it.hasNext()){
        	subjectbean view=(subjectbean) it.next();
	%>
<tr onmouseover=this.style.color='red' onmouseout=this.style.color='black' onclick='show("<%=view.getCode()%>")'>
<script language=javascript>
//document.write("<td>"+"<%=view.getCode()%>" +"</td>");
document.write("<td>"+"<%=i++%>" +"</td>");
document.write("<td>"+"<%=view.getName()%>" +"</td>");
document.write("<td>"+"&nbsp;<%=view.getDesc()%>"+"</td>");
document.write("</tr>");
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
window.opener.document.Desig.code.value=val;
window.opener.document.Desig.method="post";
window.opener.document.Desig.flag.value="search";
window.opener.document.Desig.action="./DesignationServlet";
window.opener.document.Desig.submit();
window.close();
}
function focuss(){
document.Desig_Find.name.focus();
}

function validate()
{
if(t1())
{
alert("Please enter the DesignationName");
return false;
}
 else
{
return true;
}

   }

   function t1()
{
if(document.Desig_Find.name.value=="")
{
return true;
}
else
{
return false;
}
}
function search(){
document.Desig_Find.submit();
}


</script>
<!--
//////////////////////////////////////JAVA SCRIPT USEING CLIENT SIDE///////////////////////////////////////////////// --> 

