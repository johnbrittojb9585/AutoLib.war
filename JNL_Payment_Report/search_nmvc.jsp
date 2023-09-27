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
<form name=ord_find method=post action=./JnlPaymentReportServlet>
<!--
//////////////////////////////////////JAVA SERVER PAGES SCRIPT ///////////////////////////////////////////////// -->
<%
String flag=request.getParameter("flag");

String SQLstr="";
String Title="";
String t1="",t2="",t3="";

if (flag!=null){
	
	if (flag.equals("PaymentNo")){
		Title="PaymentNo No";t1="PaymentNo Number";t2="SP_Name";t3="Amount";
	}
}

%>
<!--
//////////////////////////////////////FORM CONTROLS//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// -->
<h2 >Journal Payment Report Search</h2>
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

 if (flag.equals("PaymentNo")){

    sc=(ArrayList)request.getAttribute("search");

	   out.print("<table border=1 bordercolor=#008000 align=center cellspacing=0 >");
	   out.print("<tr><th>PaymentRefNo<th>SP_Name<th>Amount</tr>");
    
	   Iterator it=sc.iterator();
		while(it.hasNext()){
			JnlPaymentBean view=(JnlPaymentBean) it.next();
			
			%>		
	<tr onmouseover=this.style.color='red' onmouseout=this.style.color='black' onclick='show("<%=view.getInvNo()%>")'>
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

function show(val){
      window.opener.document.ordinv.pmtno.value=val;
      window.close();
}

function focuss(){
document.ord_find.name.focus();
}
function search(){
//document.ord_find.submit();
}
</script>

