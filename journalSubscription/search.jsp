<%@ page language="java" session="true" buffer="12kb" import="java.sql.*" import="java.util.*"%>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/button_css.css" />
<jsp:useBean id="bean" scope="request" class="Lib.Auto.JournalSubscription.JournalSubscriptionbean"/>

<!--
//////////////////////////////////////DATABASE CONNECTION//////////////////////////////////////////////////////////////////////// -->

<%
//
//   Filename: Search.jsp
//   Form name:journal_Find
//%>
<html>
<head>
<title>AutoLib</title>
<meta http-equiv="pragma" content="no-cache"/>
<link rel="stylesheet" type="text/css" href="/AutoLib/style.css">
</head>
<body onload="focuss()" background="/AutoLib/soft.jpg" >
<form name=dept method=post action=./JournalSubscriptionServlet>

<%
String flag=request.getParameter("flag");

String SQLstr="";
String Title="";
String t1="",t2="",t3="";

if (flag!=null){
if (flag.equals("Sup")){
Title="Supplier Name";t1="S No";t2="Sub Name";t3="Short_Desc";
}

else if (flag.equals("JnlName")){
Title="Publisher Name";t1="SP_Code";t2="SP_Name";t3="Short_Desc";
}

else if (flag.equals("budName")){
Title="Budget Name";t1="BudCode";t2="Bud Amount";t3="Short_Desc";
}


else if (flag.equals("Nam")){
Title="Journal Name";t1="jnl_code";t2="Type";t3="frequency";
}

else if (flag.equals("subsNo")){
Title="Journal Name";t1="S No";t2="Subs&nbsp;No";t3="Supplier";
}

else{
	Title="Journal Name";t1="jnl_code";t2="Type";t3="frequency";
}
}

%>
<!--
//////////////////////////////////////FORM CONTROLS//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// -->
 <h2> Journal Subscription Search</h2>
<div align=right><A href=""  onclick="window.close()">Back</A></div>
<p align="center">

<b><%=Title%></b>
<input type=text name=name>
<input Class="submitButton" type=submit value="Search">
<input type=hidden name=flag value="<%=flag%>">
</p>
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
	int j=1;
%>
  <script language="JavaScript">
document.dept.name.value="<%=request.getParameter("nam")%>";
document.dept.name.focus();
</script>
<%
if (flag.equals("Sup")){
		sc=(ArrayList)bean.getAl();

		out.print("<table border=1 bordercolor=#008000 align=center cellspacing=0 >");
		out.print("<tr><th>"+t1+"</th><th>"+Title+"</th><th>"+t2+"</th></tr>");

		for(int i=0; i<sc.size();i++){
  %>
		<tr onmouseover=this.style.color='red' onmouseout=this.style.color='black' onclick='show("<%=sc.get(i)%>","<%=sc.get(i+1)%>")'>
		<script language=javascript>
		 document.write("<td>"+"<%=j++%>" +"</td>");
		 //document.write("<td>"+"<%=sc.get(i)%>"+"</td>");
		 document.write("<td>"+"<%=sc.get(++i)%>" +"</td>");
		 document.write("<td>"+"<%=sc.get(++i)%>"+"</td>");
		 document.write("</tr>");
 		</script>
		<%

		}
		}else if(flag.equals("budName")){
			sc=(ArrayList)bean.getAl();
			out.print("<table border=1 bordercolor=#008000 align=center cellspacing=0 >");
			out.print("<tr><th>"+t1+"</th><th>"+Title+"</th><th>"+t2+"</th></tr>");
			for(int i=0; i<sc.size();i++){
				  %>
						<tr onmouseover=this.style.color='red' onmouseout=this.style.color='black' onclick='show2("<%=sc.get(i)%>","<%=sc.get(i+1)%>")'>
						<script language=javascript>
						 document.write("<td>"+"<%=j++%>" +"</td>");
						 //document.write("<td>"+"<%=sc.get(i)%>"+"</td>");
						 document.write("<td>"+"<%=sc.get(++i)%>" +"</td>");
						 document.write("<td>"+"<%=sc.get(++i)%>"+"</td>");
						 document.write("</tr>");
				 		</script>
						<%

						}
			
			
			
		}else if(flag.equals("subsNo")){
			
			sc=(ArrayList)bean.getAl();
			out.print("<table border=1 bordercolor=#008000 align=center cellspacing=0 >");
			out.print("<tr><th>"+t1+"</th><th>"+t2+"</th><th>"+Title+"</th><th>"+t3+"</th></tr>");
			for(int i=0; i<sc.size();i++){
				  %>
						<tr onmouseover=this.style.color='red' onmouseout=this.style.color='black' onclick='show3("<%=sc.get(i)%>","<%=sc.get(i+1)%>")'>
						<script language=javascript>
						 document.write("<td>"+"<%=j++%>" +"</td>");
						 
						 document.write("<td>"+"<%=sc.get(i)%>"+"</td>");
						 document.write("<td>"+"<%=sc.get(++i)%>" +"</td>");
						 document.write("<td>"+"<%=sc.get(++i)%>"+"</td>");
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
		<tr onmouseover=this.style.color='red' onmouseout=this.style.color='black' onclick='show1("<%=sc.get(i)%>","<%=sc.get(i+1)%>")'>
		<script language=javascript>
		 document.write("<td>"+"<%=j++%>" +"</td>");
		
		 document.write("<td>"+"<%=sc.get(++i)%>" +"</td>");
		 document.write("<td>"+"<%=sc.get(++i)%>"+"</td>");
		 
		 
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

 </script>

<script  language="javascript">
function show(val,val1){
	if(document.dept.flag.value=="Sup"){
		window.opener.document.journalSubscription.supCode.value=val;
		window.opener.document.journalSubscription.Supplier.value=val1;
	}
window.close();
}
function show1(val,val1){
	if(document.dept.flag.value=="jnlName"){
		window.opener.document.journalSubscription.jnlCode.value=val;
		window.opener.document.journalSubscription.journalName.value=val1;
	}	
window.close();
}

function show2(val,val1){
	if(document.dept.flag.value=="budName"){
		
		
		window.opener.document.journalSubscription.budgCode.value=val;
		window.opener.document.journalSubscription.budgName.value=val1;
		
		
	}	
window.close();
}
function show3(val,val1){
	if(document.dept.flag.value=="subsNo"){
		window.opener.document.journalSubscription.subsNo.value=val;
		window.opener.document.journalSubscription.method="get";
		window.opener.document.journalSubscription.flag.value="searchSubNo";
		window.opener.document.journalSubscription.action="./JournalSubscriptionServlet";
		window.opener.document.journalSubscription.submit();
		window.close();
		
		
	}

}

function validate()
{
if(t1())
{
alert("Please enter the Name");
return false;
}
 else
{
return true;
}      

   }
function t1()
{
if(document.dept.name.value=="")
{
return true;
}
else
{
return false;
}
}

function focuss(){
document.dept.name.focus();
}
function search(){
document.dept.submit();
}

</script>

