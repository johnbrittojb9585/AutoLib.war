<%@ page language="java" session="true" buffer="12kb" import="java.sql.*,java.util.*" import="Common.Security"  import="Lib.Auto.Indent_Order.IndentOrderDetailsBean"%>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/button_css.css" />
<%@ include file="/Common.jsp" %>
<%//Security.checkSecurity(1,session,response,request);%>
<%@ page import="java.text.SimpleDateFormat"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored ="false" %>

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
<form name=ord_find method=post action=./IndentOrderServlet>
<!--
//////////////////////////////////////JAVA SERVER PAGES SCRIPT ///////////////////////////////////////////////// -->
<%
String flag=request.getParameter("flag");
String check=request.getParameter("check");

String SQLstr="";
String Title="";
String t1="",t2="",t3="";

if (flag!=null){
	
if (flag.equals("Sup")){
Title="Supplier Name";t1="SP_Code";t2="SP_Name";t3="Short_Desc";
}
else if (flag.equals("IndentNo")){
Title="Member Name";t1="Member_Code";t2="Member_Name";t3="Short_Desc";
}
else if (flag.equals("OrderNo")){
	Title="Order Number";t1="SP_Code";t2="SP_Name";t3="Short_Desc";
}
}

%>
<!--
//////////////////////////////////////FORM CONTROLS//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// -->
<h2 >Indent Order Master Search</h2>
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
String ck=null;
if(!request.getParameter("check").equals("CommonSubsdt")){
 ck=request.getParameter("check");
}
ArrayList sc=new ArrayList();
try{
if(ck!=null){
                      %>
  <script language="JavaScript">
document.ord_find.name.value="<%=request.getParameter("name")%>";
document.ord_find.name.focus();
</script>
<%

		if (flag.equals("Sup")){

           sc=(ArrayList)request.getAttribute("search");

		   out.print("<table border=1 bordercolor=#008000 align=center cellspacing=0 >");
		   out.print("<tr><th>SP_Code<th>SP_Name<th>Short_Desc</tr>");
           
		   Iterator it=sc.iterator();
			while(it.hasNext()){
				IndentOrderDetailsBean view=(IndentOrderDetailsBean) it.next();
				
				%>				
				
		<tr onmouseover=this.style.color='red' onmouseout=this.style.color='black' onclick='show("<%=view.getTitle()%>","<%=view.getIndentno()%>")'>
		<script language=javascript>	 
		 document.write("<td>"+"<%=view.getIndentno()%>" +"</td>");
		 document.write("<td>"+"<%=view.getTitle()%>" +"</td>");
		 document.write("<td>"+"&nbsp;<%=view.getAdd1()%>"+"</td>");
		 document.write("</tr>");		
 		</script>
		<%
		}
		sc.clear();
		}



		if (flag.equals("IndentNo")){

               sc=(ArrayList)request.getAttribute("search");

			out.print("<table border=1 bordercolor=#008000 align=center cellspacing=0 >");
		    out.print("<tr><th>ID<th>Member_Code<th>Member_Name<th>Indent_No</tr>");

		    Iterator it=sc.iterator();
			while(it.hasNext()){
				IndentOrderDetailsBean view=(IndentOrderDetailsBean) it.next();
				%>
		<script language=javascript>
		 document.write("<tr>");   
 		 document.write("<td><input type='checkbox' id='selectedIds[]' name='selectedIds[]' value='<%=view.getAdd1()%>' /></td>");
		 document.write("<td>"+"<%=view.getIndentno()%>" +"</td>");
		 document.write("<td>"+"<%=view.getTitle()%>" +"</td>");
		 document.write("<td>"+"&nbsp;<%=view.getAdd1()%>"+"</td>");
		 document.write("</tr>");
 		</script>
		<%
		}
		sc.clear();
		%>
    <script type="text/javascript">
     document.write("<center><input type='button' onclick='test()'>Go!</center>");       
    </script><%
		}
		
		
		if (flag.equals("OrderNo")){

	           sc=(ArrayList)request.getAttribute("search");

			   out.print("<table border=1 bordercolor=#008000 align=center cellspacing=0 >");
			   out.print("<tr><th>OrderNo<th>SP_Name<th>SP_Code</tr>");
	           
			   Iterator it=sc.iterator();
				while(it.hasNext()){
					IndentOrderDetailsBean view=(IndentOrderDetailsBean) it.next();
					
					%>				
					
			<tr onmouseover=this.style.color='red' onmouseout=this.style.color='black' onclick='show("<%=view.getIndentno()%>","<%=view.getTitle()%>")'>
			<script language=javascript>	 	
			 document.write("<td>"+"<%=view.getIndentno()%>" +"</td>");		
			 document.write("<td>"+"&nbsp;<%=view.getAdd1()%>"+"</td>");
			 document.write("<td>"+"<%=view.getTitle()%>" +"</td>");			 
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

function test() {
    var cboxes = document.getElementsByName('selectedIds[]');
    var len = cboxes.length;
    var content="";
    var res=[];
   
    for (var i=0; i<len; i++) {
    if(cboxes[i].checked)
    {
      content=content+",'"+ cboxes[i].value +"'";      
    }      
    }
   
    if(content)   {    
       window.opener.document.ordinv.flag.value="selectV";
       window.opener.document.ordinv.flag1.value=content;
       window.opener.document.ordinv.submit();    
       window.close();
    }else {
      alert("Invalid Selection !");
    }  
   
}

function show(val,vname){
if(document.ord_find.flag.value=="IndentNo"){
window.opener.document.ordinv.jnlname.value=val;
}
if(document.ord_find.flag.value=="Sup"){
window.opener.document.ordinv.sname.value=val;
window.opener.document.ordinv.sup_code.value=vname;
}
if(document.ord_find.flag.value=="OrderNo"){
window.opener.document.ordinv.ordno.value=val;
window.opener.document.ordinv.sup_code.value=vname;
window.opener.document.ordinv.method="get";
window.opener.document.ordinv.flag.value="search";
window.opener.document.ordinv.submit();
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

