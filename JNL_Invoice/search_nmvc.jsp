<%@ page language="java" session="true" buffer="12kb" import="java.sql.*,java.util.*" import="Common.Security"  import="Lib.Auto.JNL_Invoice.JnlInvoiceBean"%>
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
<form name=ord_find method=post action=./InvoiceJNLServlet>
<!--
//////////////////////////////////////JAVA SERVER PAGES SCRIPT ///////////////////////////////////////////////// -->
<%
String flag=request.getParameter("flag");

String SQLstr="";
String Title="",Title1="";
String t1="",t2="",t3="";

if (flag!=null){
	
if (flag.equals("Invoice")){
Title="Supplier Name";Title1="InvoiceNo";t1="Invoice Number";t2="SP_Name";t3="SP_Code";
}
if (flag.equals("SInvoiceNo")){
	Title="Supplier Name";Title1="InvoiceNo";t1="Invoice Number";t2="SP_Name";t3="SP_Code";
}
}

%>
<!--
//////////////////////////////////////FORM CONTROLS//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// -->
<h2 >Journal Invoice Processing Search</h2>
<div ><A href=""  onclick="window.close()">Back</A></div>
<center><%=Title1%><input type=text name=invno onkeyup=search()>&nbsp;&nbsp;<%=Title%><input type=text name=name onkeyup=search()>
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
document.ord_find.invno.value="<%=request.getParameter("invno")%>";
document.ord_find.name.focus();
</script>
<%
		if (flag.equals("Invoice")){

           sc=(ArrayList)request.getAttribute("search");

		   out.print("<table border=1 bordercolor=#008000 align=center cellspacing=0 >");
		   out.print("<tr><th>Invoice Number<th>SP_Name</tr>");
           
		   Iterator it=sc.iterator();
			while(it.hasNext()){
				JnlInvoiceBean view=(JnlInvoiceBean) it.next();
				
				%>				
				
		<tr onmouseover=this.style.color='red' onmouseout=this.style.color='black' onclick='show("<%=view.getInvoiceno()%>","<%=view.getInvoicedate()%>","<%=view.getSupplier()%>","<%=view.getSupCode()%>")'>
		<script language=javascript>	 
		 document.write("<td>"+"<%=view.getInvoiceno()%>" +"</td>");
		 document.write("<td>"+"<%=view.getSupplier()%>" +"</td>");
		 //document.write("<td>"+"&nbsp;<%=view.getSupCode()%>"+"</td>");
		 document.write("</tr>");		
 		</script>
		<%
		}
		sc.clear();
		}

    if (flag.equals("SInvoiceNo")){

     sc=(ArrayList)request.getAttribute("search");

	   out.print("<table border=1 bordercolor=#008000 align=center cellspacing=0 >");
	   out.print("<tr><th>Invoice Number<th>SP_Name</tr>");
    
	   Iterator it=sc.iterator();
		while(it.hasNext()){
			JnlInvoiceBean view=(JnlInvoiceBean) it.next();
			
			%>				
			
	<tr onmouseover=this.style.color='red' onmouseout=this.style.color='black' onclick='show("<%=view.getInvoiceno()%>","<%=view.getInvoicedate()%>","<%=view.getSupplier()%>","<%=view.getSupCode()%>")'>
	<script language=javascript>	 
	 document.write("<td>"+"<%=view.getInvoiceno()%>" +"</td>");
	 document.write("<td>"+"<%=view.getSupplier()%>" +"</td>");
	 //document.write("<td>"+"&nbsp;<%=view.getSupCode()%>"+"</td>");
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

function show(val,invdate,sname,scode){

if(document.ord_find.flag.value=="Invoice"){

window.opener.document.ordinv.invno.value=val;
window.opener.document.ordinv.supinvno.value=val;
window.opener.document.ordinv.invdate.value=invdate;
window.opener.document.ordinv.sname.value=sname;
window.opener.document.ordinv.sup_code.value=scode;

}
else if(document.ord_find.flag.value=="SInvoiceNo"){

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

