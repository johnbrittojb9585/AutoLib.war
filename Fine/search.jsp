<%@ page language="java" session="true" buffer="12kb" import="java.sql.*" import="java.util.*" import="Common.Security" import="Lib.Auto.Fine.Finebean"%>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/button_css.css" />
<%//Security.checkSecurity(1,session,response,request);%>
<!--
//////////////////////////////////////DATABASE CONNECTION//////////////////////////////////////////////////////////////////////// -->

<%
//
//   Filename: Search.jsp
//   Form name:Fine_Find
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
<form name="Fine_search" method="post" action="./FineServlet">

<%

String flag=null,sflag=null,SQLstr="",Title="",t1="",t2="",t3="",nm="",f1="",f2="",f3="",mainTitle="";

flag=request.getParameter("flag");
sflag=request.getParameter("sflag");
nm=request.getParameter("name");

if (flag!=null){
if (flag.equals("Fine")){
mainTitle="Fine";Title="Fine ID ";t1="Group Id";t2="Fine_Amount";


}
else if (flag.equals("Group")){
mainTitle="Group";Title="Group Id";t1="Group Name";t2="Group_Name";t3="Remarks";

}

}

%>
<!--
//////////////////////////////////////FORM CONTROLS//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// -->
<h2><%=mainTitle%> Search</h2>
<div><A href=""  onclick="window.close()">Back</A></div>
<center><%=Title%><input type=text name=name onkeyup=search()>
<input type=submit Class="submitButton" value="Search" ></center>
<input type=hidden name=sflag value="<%=flag%>"></form>
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
		<script language="javascript">
		document.Fine_search.name.value="<%=request.getParameter("nam")%>";
		document.Fine_search.name.focus();
		</script>
		<%
if (flag.equals("Group")){
		
	sc=(ArrayList)request.getAttribute("serarch");
	
		out.print("<table border=1 bordercolor=#008000 align=center cellspacing=0 >");
		out.print("<tr><th>"+Title+"</th><th>"+t1+"</th><th>"+t3+"</th></tr>");
		Iterator it=sc.iterator();

		while(it.hasNext()){
			Finebean view=(Finebean) it.next();
		%>
	<tr onmouseover=this.style.color='red' onmouseout=this.style.color='black' onclick='show("<%=view.getGcode()%>")'>
	<script language=javascript>
	 document.write("<td>"+"<%=view.getFcode()%>" +"</td>");
	 document.write("<td>"+"<%=view.getGcode()%>" +"</td>");
	 document.write("<td>"+"&nbsp;<%=view.getFamount()%>"+"</td>");
	 document.write("</tr>");    
		</script>	
	<%
		}
		}
		else
		{
			sc=(ArrayList)request.getAttribute("serarch");
	
		out.print("<table border=1 bordercolor=#008000 align=center cellspacing=0 >");
		out.print("<tr><th>"+Title+"</th><th>"+t1+"</th><th>"+t2+"</th></tr>");
		Iterator it=sc.iterator();

		while(it.hasNext()){
			Finebean view=(Finebean) it.next();
		%>
	<tr onmouseover=this.style.color='red' onmouseout=this.style.color='black' onclick='show1("<%=view.getFcode()%>")'>
	<script language=javascript>
	 document.write("<td>"+"<%=view.getFcode()%>" +"</td>");
	 document.write("<td>"+"<%=view.getGcode()%>" +"</td>");
	 document.write("<td>"+"&nbsp;<%=view.getFamount()%>"+"</td>");
	 document.write("</tr>");    
		</script>	
	<%
		}

		}
	 }
 }catch (Exception e) {out.println(e.toString());}
   finally{
    sc.clear();
    }
%>

<script  language="javascript">
function show(val){

window.opener.document.Fine.GROUPNAME.value=val;
window.close();
}

function show1(val){

window.opener.document.Fine.FCODE.value=val;
window.opener.document.Fine.flag.value="search";
window.opener.document.Fine.action="./FineServlet";
window.opener.document.Fine.submit();
window.close();
}

function focuss(){
document.Fine_search.name.focus();
}
function search(){
document.Fine_search.submit();
}
</script>
<!--
//////////////////////////////////////JAVA SCRIPT USEING CLIENT SIDE///////////////////////////////////////////////// --> 
