

<%@ page language="java" errorPage="/Error/ErrorPage.jsp"  session="true" buffer="12kb"
 import="java.sql.*" import="java.util.*" 
    import="java.util.ArrayList,java.util.Iterator"
     import="Lib.Auto.Subject.subjectbean"
     import="Common.Security"
    %>
    




<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/button_css.css" />
<jsp:useBean id="bean" scope="request" class="Lib.Auto.Budget.BudgetBean"/>

<%
   response.setHeader("Pragma", "No-cache");
   response.setHeader("Cache-Control", "no-cache");
   response.setDateHeader("Expires", 0);
%>


<html>
<head>
<title>AutoLib</title>
<meta http-equiv="pragma" content="no-cache"/>
</head>
<link rel="stylesheet" type="text/css" href="/AutoLib/style.css">
<body onload="focuss()" background="/AutoLib/soft.jpg">

<form name="Bud" method="post" action="./BudgetServlet">

<%
String t1="",t2="",t3="",Title="",mainTitle="";
String flag=request.getParameter("flag");
//String sflag=request.getParameter("sflag");
String nm=request.getParameter("name");
if (flag!=null){
if (flag.equals("dcode")){
mainTitle="Department";Title="Department Name";t1="Department Code";t3="Dept_Name";t2="Short Desc";

}
else if (flag.equals("bcode")){
mainTitle="Budget";Title="Budget&nbsp;Code";t1="Ref. No";t3="Budget Head";t2="Budget Amount";

}


}

%>
<!--
//////////////////////////////////////FORM CONTROLS//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// -->

<h2><%=mainTitle%>Search</h2>
<p align="center">



<%=Title%><input type=text name=name>
<input type=submit Class="submitButton" value="Search">
<input type=hidden name=flag value="<%=flag%>">

<A href=""  onclick="window.close()">Back</A>
</p>
</form>
</body>

<!--
//////////////////////////////////////JAVA SERVER PAGES SCRIPT /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// -->


<%
String ck=request.getParameter("check");
ArrayList sc=new ArrayList();
try{
	
	
if(ck!=null){
	int j=1;
%>
<script language="javascript">
document.Bud.name.value="<%=request.getParameter("nam")%>";
document.Bud.name.focus();
</script>
<%
if (flag.equals("bcode")){
		sc=(ArrayList)bean.getAl();
		out.print("<table border=1 bordercolor=#008000 align=center cellspacing=0 >");
		out.print("<tr><th>S.No</th><th>"+Title+"</th><th>"+t2+"</th></tr>");

		for(int i=0; i<sc.size();i++){
  %>
		<tr onmouseover=this.style.color='red' onmouseout=this.style.color='black' onclick='show1("<%=sc.get(i)%>")'>
		<script language=javascript>
		 document.write("<td>"+"<%=j++%>" +"</td>");		
//		 document.write("<td>"+"<%=sc.get(i)%>" +"</td>");
		 document.write("<td>"+"<%=sc.get(++i)%>" +"</td>");
		 document.write("<td>"+"&nbsp;<%=sc.get(++i)%>"+"</td>");
		 document.write("</tr>");
 		</script>
		<%

		}
		}



		else
		{
		sc=(ArrayList)bean.getAl();
		out.print("<table border=1 bordercolor=#008000 align=center cellspacing=0 >");
		out.print("<tr><th>S.No</th><th>"+Title+"</th><th>"+t2+"</th></tr>");

		for(int i=0; i<sc.size();i++){
  %>
		<tr onmouseover=this.style.color='red' onmouseout=this.style.color='black' onclick='show("<%=sc.get(i)%>")'>
		<script language=javascript>
		 document.write("<td>"+"<%=j++%>" +"</td>");		
		 document.write("<td>"+"<%=sc.get(i)%>" +"</td>");
//		 document.write("<td>"+"<%=sc.get(++i)%>" +"</td>");
		 document.write("<td>"+"&nbsp;<%=sc.get(++i)%>"+"</td>");
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
function show1(val){
	
	if(document.Budget.flag.value=="dept"){
		window.opener.document.Budget.dname.value=val;
		}
	
}
function show(val){
window.opener.document.Budget.dname.value=val;
window.close();
}
function show1(val){
window.opener.document.Budget.Bud_Code.value=val;
window.opener.document.Budget.method="post";
window.opener.document.Budget.flag.value="search";
window.opener.document.Budget.action="./BudgetServlet";
window.opener.document.Budget.submit();
window.close();
}

function focuss(){
document.Bud.name.focus();
}
function search(){
document.Bud.submit();
}

</script>
</html>