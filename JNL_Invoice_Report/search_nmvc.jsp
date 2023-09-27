<%@ page language="java" session="true" buffer="12kb" import="java.sql.*,java.util.*" import="Common.Security"  import="Lib.Auto.JNL_Payment.JnlPaymentBean"%>
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
<form name=ord_find method=post action=./JnlInvoiceReportServlet>
<!--
//////////////////////////////////////JAVA SERVER PAGES SCRIPT ///////////////////////////////////////////////// -->
<%
String flag=request.getParameter("flag");

String SQLstr="";
String Title="";
String t1="",t2="",t3="";

if (flag!=null){
	
	if (flag.equals("INVOICE")){
		Title="Invoice No";t1="Invoice Number";t2="SP_Name";t3="Amount";
		}
}

%>
<!--
//////////////////////////////////////FORM CONTROLS//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// -->
<h2 >Journal Invoice Report Search</h2>
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
		if (flag.equals("INVOICE")){

         sc=(ArrayList)request.getAttribute("search");

		   out.print("<table border=1 bordercolor=#008000 align=center cellspacing=0 >");
		   out.print("<tr><th>Invoice Number<th>SP_Name<th>Amount</tr>");
         
		   Iterator it=sc.iterator();
			while(it.hasNext()){
				JnlPaymentBean view=(JnlPaymentBean) it.next();
				
				%>		
        <tr onmouseover=this.style.color='red' onmouseout=this.style.color='black' onclick='show("<%=view.getInvNo()%>","<%=view.getSupplier()%>","<%=view.getSupCode()%>")'>		
		<script language=javascript>		
		 document.write("<td>"+"<%=view.getInvNo()%>" +"</td>");
		 document.write("<td>"+"<%=view.getSupplier()%>" +"</td>");
		 document.write("<td>"+"&nbsp;<%=view.getNetamount()%>"+"</td>");
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

function show(val,sname,scode){
if(document.ord_find.flag.value=="INVOICE"){
window.opener.document.ordinv.invno.value=val;
window.opener.document.ordinv.sname.value=sname;
window.opener.document.ordinv.sup_code.value=scode;
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

