<%
String URole=session.getAttribute("UserRights").toString().trim();
if(URole.equalsIgnoreCase("3") || URole.equalsIgnoreCase("4") || URole.equalsIgnoreCase("5") || URole.equalsIgnoreCase("7"))
{		
	response.sendRedirect("sessiontimeout.jsp");
}	
%>
<%@ include file="/Tree/demoFrameset.jsp"%>
<%@ page language="java" session="true" buffer="12kb" import="java.util.*" import="Common.Security"%>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/button_css.css" />
<jsp:useBean id="bean" scope="request" class="Lib.Auto.Payment.PaymentBean"/>

<%
ArrayList sc=new ArrayList();
ArrayList pc=new ArrayList();
String message="",info="";
%>

<!--
//////////////////////////////////////DATABASE CONNECTION//////////////////////////////////////////////////////////////////////// -->
<%
//
//   Filename: Index.jsp
//   Form name:binding
//%>
<!--
//////////////////////////////////////FORM CONTROLS//////////////////////////////////////////////////////////////////////// -->
<html>
<head>
<title>AutoLib</title>
<meta http-equiv="pragma" content="no-cache"/>
<link rel="stylesheet" type="text/css" href="/AutoLib/style.css">
<script language="javascript" src="/AutoLib/popcalendar.js"></script>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/jquery-ui.css"/>
<%-- <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/jquery-ui-flick.css"/> --%>
<%-- <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-3.1.0.js"></script> --%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-2.2.4.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-ui.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/datepicker.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/searchUserPayment.js"></script>

</head>
<body background="/AutoLib/soft.jpg" >  <!--onload="load()"-->

<!-- Style Sheet -->
<form name=Payment method="post" action=./PaymentServlet>

<h2>Payment&nbsp;Master</h2>
<center>
<table align="center" class="contentTable" width="45%">
<td>
<br>
<table align="center" class="contentTable" width="45%">
<td>
<table align="center" width="90%">
<tr><td> &nbsp; </td></tr>
<tr><td Class="addedit">User&nbsp;Code</td><td ><input type="textbox" name="user_no" id="userNo" class="searchUserNo" size=15  maxlength="15" onkeyup="showUserNo(this.value);"  onKeydown="Javascript: if (event.keyCode==13) user();">
</td></tr>
<tr><td Class="addedit">User&nbsp;Name</td><td colspan="3" >
<input type="textbox" name="user_name"  size=50 maxlength="50" readonly=true></td></tr>
<tr><td Class="addedit">Department</td><td colspan="3" >
<input type="textbox" name="user_dept"  size=50 maxlength="50" readonly=true></td></tr>
<tr ><td Class="addedit">Course</td><td colspan="3" >
<input type="textbox" name="user_course"  size=50 maxlength="50" readonly=true> </td></tr>
<tr><td> &nbsp; </td></tr>
</table>
</table>
<br>
<table class="contentTable" align="center" width="90%">
<td>
<table align="left" width="90%">
<tr><td> &nbsp; </td></tr>
<tr><td Class="addedit">Total&nbsp;&nbsp;Amount</td><td colspan="3" >
<input type="textbox" name="tot_amt"  size=10 maxlength="50" readonly=true></td></tr>
<tr><td Class="addedit">Paid&nbsp;&nbsp;&nbsp;Amount</td><td colspan="3" >
<input type="textbox" name="paid_amt"  size=10 maxlength="50" readonly=true></td></tr>
<tr><td Class="addedit">Balance&nbsp;Amount</td><td colspan="3" >
<input type="textbox" name="balance_amt"  size=10 maxlength="50" readonly=true></td></tr>
<tr><td> &nbsp; </td></tr>
</table>
</table>
<br>
<table class="contentTable" align="center" width="90%">
<td>
<table align="left" width="90%">
<tr><td> &nbsp; </td></tr>
<tr><td Class="addedit">Bill&nbsp;&nbsp;No</td><td colspan="3" >
<input type="textbox" name="bill_no"  size=10 maxlength="50" readonly=true></td></tr>

<tr><td Class="addedit">Date

<TD>
<%-- 	<INPUT name=pdate size=10  onfocus=this.blur(); value="<%=Security.CalenderDate()%>"> --%>
	<INPUT name=pdate size=10 id="datepicker" value="<%=Security.CalenderDate()%>">
												
		 </td>
</tr>
<tr><td Class="addedit">Current&nbsp;&nbsp;Payment&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td><td colspan="3" >
<input type="textbox" name="current_amt"  size=10 maxlength="50" onKeyUp="return Payment_code_val();"></td></tr>
<tr>
		<td Class="addedit">Pay.Mode</td>
		<td>
	 <select name="Type" size="1" style="width: 125px">
      	<option selected  value="OVERDUE">OVERDUE</option>
     	<option value="Photocopy">Photocopy</option>
		<option value="Printout">Printout</option>
		<option value="Color Print">Color Print</option>
		<option value="Strip Binding">Strip Binding</option>
		<option value="Stick Binding">Stick Binding</option>
		<option value="Spiral Binding">Spiral Binding</option>
		<option value="Recovery">Recovery</option>
		<option value="Loss of Resource">Loss of Resource</option>
		<option value="Others">Others</option>
 	</select>
		</td></tr>
<tr><td> &nbsp; </td></tr>
</table>
</table>

<P align=center>
<input type=hidden name=New Class="submitButton" Value=New onclick=new_no()>
<input type=button name=Save Class="submitButton" value=Save onclick=SaveRec()>
<input type=Reset name=Clear Class="submitButton" Value=Clear onclick=rsetpage()>
<input type=hidden name=flag>
</table>
<br>
</CENTER>
</form>
</body>
</html>
<!--
//////////////////////////////////////JAVA SERVER PAGES SCRIPT ///////////////////////////////////////////////// -->
<%
String valid=request.getParameter("check");
String detail=request.getParameter("details");

message=request.getParameter("message");
info=request.getParameter("info");
if(detail!=null)
{
	if(detail.equals("SavePayment")){
		%>             <script language="JavaScript">

					 alert("Record Inserted Successfully!");
					
					 document.Payment.flag.value="user";
				     </script>		
					<%
					}	
}



if(valid!=null){
if(request.getParameter("check")!=null){

if(valid.equals("userdetails"))
{
	%>
	
 <script language="JavaScript">
	
	
	document.Payment.user_no.value="<%=bean.getMcode()%>";
	document.Payment.user_name.value="<%=bean.getMname()%>";
    document.Payment.user_dept.value="<%=bean.getDept()%>";
    document.Payment.user_course.value="<%=bean.getCourse()%>";
        
    document.Payment.tot_amt.value="<%=bean.getTot_Amt()%>";
	document.Payment.paid_amt.value="<%=bean.getPaid_Amt()%>";
	document.Payment.balance_amt.value="<%=bean.getBalance_Amt()%>";
	 
	document.Payment.bill_no.value="<%=bean.getBill_No()%>";	
	document.Payment.current_amt.value="";
	document.Payment.current_amt.focus();
	 	 
	 document.Payment.flag.value="";
</script>
	 
	 <%
}

if(valid.equals("FailMember")){ 

	%>
	            <script language="JavaScript">
	            alert("Member Not Found");
				document.Payment.flag.value="clear";
			    document.Payment.submit();				
			   	</script><%
				}

if(valid.equals("newbillno")){
	%>         
	    <script language="JavaScript">

				 document.Payment.bill_no.value="<%=bean.getBill_No()%>";
				 document.Payment.current_amt.focus();
				 
	    </script>		
				<%
				}

}
}

if(message!=null)
{
	if(message.equals("TRANSDETAILS"))
	{
			out.print("<b><center>Fine Details</center></b>");
            out.print("<table border=1 bordercolor=#008000 align=center cellspacing=0 width='70%'>");
            out.print("<tr><th>Trans&nbsp;No<th>Trans&nbsp;Date&nbsp;&nbsp;&nbsp;<th>Access No<th>Trans Amount<th>Trans Head</tr>");

sc=(ArrayList)bean.getPaymentList();

for(int i=0;i<sc.size();i+=5){
%>
 <tr onmouseover=this.style.color='#ff9900' onmouseout=this.style.color='black' >
 <script language=javascript>
 document.write("<tr>");
 document.write("<td>"+"<%=sc.get(i)%>" +"</td>");
 document.write("<td>"+"<%=sc.get(i+1)%>" +"</td>");
 document.write("<td>"+"<%=sc.get(i+2)%>"+"</td>");
 document.write("<td>"+"<%=sc.get(i+3)%>"+"</td>");
 document.write("<td>"+"<%=sc.get(i+4)%>"+"</td>");
 document.write("</tr>");
</script>
<%
	}	
	out.print("</table><br>");
	sc.clear();	
	}
}

if(info!=null)
{
	if(info.equals("PAIDDETAILS"))
	{
      		out.print("<br>");
		    out.print("<b><center>Payment Details</center></b>");
            out.print("<table border=1 bordercolor=#008000 align=center cellspacing=0 width='70%'>");
            out.print("<tr><th>Bill No&nbsp;<th>Payment Date<th>Paid Amount<th>Pay Mode<th>Staff Code</tr>");

pc=(ArrayList)bean.getPaidList();

for(int i=0;i<pc.size();i+=5){
%>
 <tr onmouseover=this.style.color='#ff9900' onmouseout=this.style.color='black' >
 <script language=javascript>
 document.write("<tr>");
 document.write("<td>"+"<%=pc.get(i)%>" +"</td>");
 document.write("<td>"+"<%=pc.get(i+1)%>" +"</td>");
 document.write("<td>"+"<%=pc.get(i+2)%>"+"</td>");
 document.write("<td>"+"<%=pc.get(i+3)%>"+"</td>");
 document.write("<td>"+"<%=pc.get(i+4)%>"+"</td>");
 document.write("</tr>");
</script>
<%
	}	
	out.print("</table><br>");
	pc.clear();	
	}
}

%>
<!--
///////////////////////////////////////JAVA SCRIPT USEING CLIENT SIDE///////////////////////////////////////////////// --> 


<script language="javascript">


function home()
{
location.href="/AutoLib/Home.jsp";
}

function Logout()
{
location.href="/AutoLib/index.html";
}

function new_no(){
document.Payment.method="get";
document.Payment.flag.value="new";
}

function user()   
{         
var ab=document.Payment.user_no.value;

if(ab=="")
{
alert("Insufficient Data");

}else{

document.Payment.flag.value="user";
document.Payment.submit();

}

}

function SaveRec(){
             document.Payment.method="get";
			   if(chk()){

				    document.Payment.flag.value="save";
		         	document.Payment.submit();
					}
	else
	{
	alert("Insufficient Data");
	}
	
}


function chk(){

var sp=Number(document.Payment.current_amt.value);
var ca=Number(document.Payment.balance_amt.value);

if(sp=="")
{
				document.Payment.current_amt.focus();
				document.Payment.flag.value="none";
				document.Payment.current_amt.value="";
				return false;
				}
				else
				{
	                             
		            if (document.Payment.user_no.value==""){
		              	document.Payment.user_no.focus();
			            return false;
		              }
		             else if(document.Payment.bill_no.vallue=="") 
		             {
		             alert("Please select Bill No!");
			         return false;
		             }
		             else if(sp>ca || sp==0)
		             {
		              alert("Current Amount is Greter than the Payable Amount !");
		              document.Payment.current_amt.value="";
		              document.Payment.current_amt.focus();
		             return false;
		             }
        else{
		return true;
		}
     }
 }

function Payment_code_val() {
if((isNaN(document.Payment.current_amt.value))||(document.Payment.current_amt.value == ' ')) {
document.Payment.current_amt.select();
document.Payment.current_amt.value="";

  }
}


function ClearRec(){
	 document.Payment.user_no.value="";
	 document.Payment.user_name.value="";
     document.Payment.user_dept.value="";
     document.Payment.user_course.value="";
         
     document.Payment.tot_amt.value="";
	 document.Payment.paid_amt.value="";
	 document.Payment.balance_amt.value="";
	 
	 document.Payment.bill_no.value="";
	 document.Payment.current_amt.value="";
	 	 
	 document.Payment.flag.value="";
		 
}
function load(){
	document.Payment.user_no.focus();
		 }	
		 
function rsetpage(){
document.Payment.flag.value="clear";
document.Payment.submit();
}	

</script>
<!--
////////////////////////*if(document.frm.sel_search[0].checked==true){*////////////////JAVA SCRIPT USEING CLIENT SIDE///////////////////////////////////////////////// -->

