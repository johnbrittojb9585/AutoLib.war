<%@ page language="java" session="true" buffer="12kb" import="java.sql.*,java.util.*" import="Common.Security"  import="Lib.Auto.Indent_Payment.IndentPaymentBean"%>
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
<form name=ord_find method=post action=./PaymentIndentServlet>
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
if (flag.equals("PaymentNo")){
	Title="Supplier Name";t1="Payment Number";t2="SP_Name";t3="Amount";
}
}

%>
<!--
//////////////////////////////////////FORM CONTROLS//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// -->
<h2 >Indent Payment Search</h2>
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
		   out.print("<tr><th>Opt<th>Invoice Number<th>SP_Name<th>Amount</tr>");
           
		   Iterator it=sc.iterator();
			while(it.hasNext()){
				IndentPaymentBean view=(IndentPaymentBean) it.next();
				
				%>		
		
		<script language=javascript>	 
		 document.write("<tr>");   
 		 document.write("<td><input type='checkbox' id='selectedIds[]' name='selectedIds[]' value='<%=view.getInvNo()%>' /></td>");		
		 document.write("<td>"+"<%=view.getInvNo()%>" +"</td>");
		 document.write("<td>"+"<%=view.getSupplier()%>" +"</td>");
		 document.write("<td>"+"&nbsp;<%=view.getNetamount()%>"+"</td>");
		 document.write("</tr>");		
 		</script>
		<%
		}
		sc.clear();
		
    %>
    <script type="text/javascript">    
       document.write("<center><input type='button' onclick='test()'>Go!</center>");       
    </script>
<%
	 }

if (flag.equals("PaymentNo")){

    sc=(ArrayList)request.getAttribute("search");

	   out.print("<table border=1 bordercolor=#008000 align=center cellspacing=0 >");
	   out.print("<tr><th>PaymentRefNo<th>SP_Name<th>Amount</tr>");
    
	   Iterator it=sc.iterator();
		while(it.hasNext()){
			IndentPaymentBean view=(IndentPaymentBean) it.next();			
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

    if(content)  {    
      window.opener.document.ordinv.flag.value="selectV";
      window.opener.document.ordinv.flag1.value=content;    
      window.opener.document.ordinv.submit();    
      window.close();
    } else {
      alert("Invalid Selection !");
    }
   
}


function show(val){

window.opener.document.ordinv.payrno.value=val;
window.opener.document.ordinv.flag.value="search";
window.opener.document.ordinv.method="get";
window.opener.document.ordinv.submit();

window.close();
}


function focuss(){
document.ord_find.name.focus();
}
function search(){
//document.ord_find.submit();
}
</script>

