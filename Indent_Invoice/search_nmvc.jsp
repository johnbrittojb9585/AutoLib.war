<%@ page language="java" session="true" buffer="12kb" import="java.sql.*,java.util.*" import="Common.Security"  import="Lib.Auto.Indent_Invoice.IndentInvBean"%>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/button_css.css" />
<%@ include file="/Common.jsp" %>
<%//Security.checkSecurity(1,session,response,request);%>
<%@ page import="java.text.SimpleDateFormat"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored ="false" %>

<html>
<head>
<title>AutoLib</title>
<meta http-equiv="pragma" content="no-cache"/>
<script language="javascript" src="/AutoLib/popcalendar.js"></script>
<link rel="stylesheet" type="text/css" href="/AutoLib/style.css">
</head>

<body background="/AutoLib/soft.jpg">
<form name=ord_find method=post action=./IndentInvServlet>
<!--
//////////////////////////////////////JAVA SERVER PAGES SCRIPT ///////////////////////////////////////////////// -->
<%
String flag=request.getParameter("flag");

String SQLstr="";
String Title="",Title1="";
String t1="",t2="",t3="";

if (flag!=null){
	
if (flag.equals("Sup")){
Title="Supplier Name";Title1="OrderNo";t1="Order Number";t2="SP_Name";t3="SP_Code";
}
if (flag.equals("InvoiceNo")){
	Title="Supplier Name";Title1="InvoiceNo";t1="Invoice Number";t2="SP_Name";t3="SP_Code";
	}
}

%>
<!--
//////////////////////////////////////FORM CONTROLS//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// -->
<h2 >Indent Invoice Search</h2>
<div ><A href=""  onclick="window.close()">Back</A></div>
<center><%=Title1%><input type=text name=ordno onkeyup=search()>&nbsp;&nbsp;<%=Title%><input type=text name=name onkeyup=search()>
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
document.ord_find.ordno.value="<%=request.getParameter("ordno")%>";
document.ord_find.name.focus();
</script>
<%
		if (flag.equals("Sup")){

           sc=(ArrayList)request.getAttribute("search");

		   out.print("<table border=1 bordercolor=#008000 align=center cellspacing=0 >");
		   out.print("<tr><th>Opt<th>Order Number<th>SP_Name<th>SP_Code</tr>");
           
		   Iterator it=sc.iterator();
			while(it.hasNext()){
				IndentInvBean view=(IndentInvBean) it.next();
				
				%>				
				
		<script language=javascript>	 
		 document.write("<tr>");   
 		 document.write("<td><input type='checkbox' id='selectedIds[]' name='selectedIds[]' value='<%=view.getOrdNo()%>' /></td>");		
		 document.write("<td>"+"<%=view.getOrdNo()%>" +"</td>");
		 document.write("<td>"+"<%=view.getAdd1()%>" +"</td>");
		 document.write("<td>"+"&nbsp;<%=view.getSupCode()%>"+"</td>");
		 document.write("</tr>");		
 		</script>
		<%
		}
		sc.clear();
		
%><script type="text/javascript">
document.write("<center><input type='button' onclick='test()'>Go!</center>");
  
</script>
<%
	 }
if (flag.equals("InvoiceNo")){

    sc=(ArrayList)request.getAttribute("search");

	   out.print("<table border=1 bordercolor=#008000 align=center cellspacing=0 >");
	   out.print("<tr><th>Invoice Number<th>SP_Name<th>SP_Code</tr>");
    
	   Iterator it=sc.iterator();
		while(it.hasNext()){
			IndentInvBean view=(IndentInvBean) it.next();
			
			%>				
	
	<tr onmouseover=this.style.color='red' onmouseout=this.style.color='black' onclick='show("<%=view.getOrdNo()%>")'>					
	<script language=javascript>	 
	 document.write("<td>"+"<%=view.getOrdNo()%>" +"</td>");
	 document.write("<td>"+"<%=view.getAdd1()%>" +"</td>");
	 document.write("<td>"+"&nbsp;<%=view.getSupCode()%>"+"</td>");
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
      //content=content+","+cboxes[i].value;  
        
      content=content+",'"+cboxes[i].value+"'";        
    } 
      
    }
//    alert("hai"+content);
    if(content)   {    
       window.opener.document.ordinv.flag.value="selectV1";
       window.opener.document.ordinv.flag1.value=content;    
       window.opener.document.ordinv.submit();    
       window.close();
    }else {
      alert("Invalid Selection !");
    }   
   
}

function show(val){
if(document.ord_find.flag.value=="InvoiceNo"){
window.opener.document.ordinv.invno.value=val;
window.opener.document.ordinv.flag.value="search";
window.opener.document.ordinv.method="get";
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

