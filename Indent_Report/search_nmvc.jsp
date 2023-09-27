<%@ page language="java" session="true" buffer="12kb" import="java.sql.*,java.util.*" import="Common.Security"  import="Lib.Auto.Indent_Mas.IndentMasDetailsBean" %>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/button_css.css" />
<%@ include file="/Common.jsp" %>
<%//Security.checkSecurity(1,session,response,request);%>
<%@ page import="java.text.SimpleDateFormat"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored ="false" %>

<%
	java.util.Date d =new java.util.Date();
	SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	String dateString = sdf.format(d);

	Calendar cal = Calendar.getInstance();
	cal.add(Calendar.YEAR, 1); // to get previous year add -1
    cal.add(Calendar.DATE, -1);	
    
    java.util.Date nextYear = cal.getTime();
	String todatestring= sdf.format(nextYear);

%>
<!--
//////////////////////////////////////DATABASE CONNECTION//////////////////////////////////////////////////////////////////////// -->

<%
//
//   Filename: Search.jsp
//   Form name:order_Find
//%>
<!--
//////////////////////////////////////FORM CONTROLS//////////////////////////////////////////////////////////////////////// -->
<html>
<head>
<title>AutoLib</title>
<meta http-equiv="pragma" content="no-cache"/>
<script language="javascript" src="/AutoLib/popcalendar.js"></script>
<link rel="stylesheet" type="text/css" href="/AutoLib/style.css">
</head>
<!--<body background="/AutoLib/soft.jpg"  onload="focuss()" >-->
<body background="/AutoLib/soft.jpg">
<form name=ord_find method=post action=./IndentDetailReportServlet>
<!--
//////////////////////////////////////JAVA SERVER PAGES SCRIPT ///////////////////////////////////////////////// -->
<%
String flag=request.getParameter("flag");
String check=request.getParameter("check");

String SQLstr="";
String Title="";
String t1="",t2="",t3="";

if (flag!=null){
if (flag.equals("IndentNo")){
	Title="Member Name";t1="Indent_No";t2="Member_Name";t3="Member_Code";
}
}

%>
<!--
//////////////////////////////////////FORM CONTROLS//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// -->
<h2 >Indent Report Search</h2>
<div ><A href=""  onclick="window.close()">Back</A></div>
<center><%=Title%><input type=text name=name onkeyup=search()>
<input type=submit Class="submitButton" value="Search"></center>
<input type=hidden name=flag value="<%=flag%>">
<input type=hidden name="flag1" >
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
document.ord_find.name.value="<%=request.getParameter("name")%>";
document.ord_find.name.focus();
</script>
<%

		
		if (flag.equals("IndentNo")){

	           sc=(ArrayList)request.getAttribute("search");

			   out.print("<table border=1 bordercolor=#008000 align=center cellspacing=0 >");
			   out.print("<tr><th>Indent_No<th>Member_Name<th>Member_Code</tr>");
	           
			   Iterator it=sc.iterator();
				while(it.hasNext()){
					IndentMasDetailsBean view=(IndentMasDetailsBean) it.next();
					
					%>				
					
			<tr onmouseover=this.style.color='red' onmouseout=this.style.color='black' onclick='show("<%=view.getFlag()%>","<%=view.getMemcode()%>")'>
			<script language=javascript>	 			 

			 document.write("<td>"+"<%=view.getFlag()%>"+"</td>");
			 document.write("<td>"+"<%=view.getMemname()%>" +"</td>");				 
			 document.write("<td>"+"<%=view.getMemcode()%>" +"</td>");		
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

function show(val,vname){
if(document.ord_find.flag.value=="IndentNo"){
window.opener.document.ordinv.indentno.value=val;
}
window.close();
}

function focuss(){
document.ord_find.name.focus();
}
function search(){
//document.ord_find.submit();
}
</script>

