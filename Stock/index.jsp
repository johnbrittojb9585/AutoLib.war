<%
String URole=session.getAttribute("UserRights").toString().trim();
if(URole.equalsIgnoreCase("3") || URole.equalsIgnoreCase("4") || URole.equalsIgnoreCase("5") || URole.equalsIgnoreCase("6") || URole.equalsIgnoreCase("7") || URole.equalsIgnoreCase("8"))
{		
	response.sendRedirect("sessiontimeout.jsp");
}	
%>
<%@ include file="/Tree/demoFrameset.jsp"%>
<%@ page language="java"  session="true" buffer="12kb" import="java.sql.*" import="Common.Security" %>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/button_css.css" />
<jsp:useBean id="BeanObject" scope="request" class="Lib.Auto.Stock.StockBean"/>
<html>
<head>
<meta http-equiv="Content-Language" content="en-us">
<meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
<meta name="GENERATOR" content="Microsoft FrontPage 4.0">
<meta name="ProgId" content="FrontPage.Editor.Document">
<title>Stock</title>
<script language="javascript" src="/AutoLib/popcalendar.js"></script>
<link rel="stylesheet" type="text/css" href="/AutoLib/style.css">
</head>
<body background="/AutoLib/soft.jpg">
<div id="wait">
<form method="POST" action="--WEBBOT-SELF--" name="stock">
<br>
<h2>Stock Verification</h2>
<table align="center" class="contentTable" width="65%">
<td>
<table align="center" width="90%">
<tr><td> &nbsp; </td></tr>


    <tr>
        <td Class="addedit">Doc&nbsp;Type&nbsp;&nbsp;<SELECT SIZE="1" NAME="txtdoctype" id="txtdoctype" onchange="doc_type()">

		<OPTION VALUE="BOOK">BOOK</OPTION>
		<OPTION VALUE="BOOK BANK">BOOK BANK</OPTION>
                <OPTION VALUE="THESIS">THESIS</OPTION>
                <option value="BACK VOLUME">BACK VOLUME</option>
                <option value="PROCEEDING">PROCEEDING</option>
                <option value="REPORT">REPORTS</option>
                <option value="NON BOOK">NON BOOK</option>
		<option value="STANDARD">STANDARD</option>
                <option value="ALL" selected>ALL</option>
		</SELECT>
		</td>  
      
       
      <td Class="addedit">Access No&nbsp;&nbsp;<input type="text" name="access" size="10" onKeydown="Javascript: if (event.keyCode==13) Insert_Stock();" >   </td>  
      
		<td>&nbsp;</td> <td>&nbsp;</td> 
      </tr>

    <tr>
            <td>&nbsp;</td>       
    </tr>

    <tr>
            <td></td>
            <td></td>
            <td></td>            
			<td Class="addedit">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Document</td>
			<td>
        <input type="text" name="doc_assign" size="12" readonly>
        </td>
    </tr>    
    
    
    
    <tr>
      <td Class="addedit" colspan="2">Bulk Stock from Excel:&nbsp;<a href="bulkindex.jsp"><font color="blue" size="2"><b><u>Click Here !</u></b></font></a> </td>		   
      <td >&nbsp;</td>      
      <td Class="addedit"><input type="radio" value="rdStock"  name="R1">Stock</td>
      <td >
      <input type="text" name="txtStock" size="10" readonly >
      <script language="JavaScript">
      document.stock.txtStock.value="<%=BeanObject.getCmas()%>";
      </script>
      </td>
    </tr>
    
    <tr>
      <td >&nbsp;</td>
      <td >&nbsp;</td> 
      <td >&nbsp;</td>           
      <td Class="addedit"><input type="radio" name="R1" value="rdNotVerify">Not Verified</td>
      <td ><input type="text" name="txtNotVerify" size="10" readonly>
      <script language="JavaScript">
      document.stock.txtNotVerify.value="<%=BeanObject.getCyes()%>";
      </script>
      </td>
    </tr>
    
    <tr>      
<td Class="addedit" colspan="2"><input type="radio" name="R1" value="rdVerifyIssued">Verify&nbsp;But&nbsp;Issued&nbsp;&nbsp;
      <input type="text" name="txtVerifyIssued" size="6" readonly>
      <script language="JavaScript">
      document.stock.txtVerifyIssued.value="<%=BeanObject.getCverifyIssued()%>";
      </script>
      </td>
      <td >&nbsp;</td>
      <td Class="addedit"><input type="radio" name="R1" value="rdMissing">Missing</td>
      <td ><input type="text" name="txtMissing" size="10" readonly>
      <script language="JavaScript">
      document.stock.txtMissing.value="<%=BeanObject.getCmissing()%>";
      </script>
      </td>
    </tr>
    <tr>
      <td >&nbsp;</td>
      <td >&nbsp;</td>
      <td >&nbsp;</td>
      <td Class="addedit"><input type="radio" name="R1" value="rdLost">Lost</td>
      <td ><input type="text" name="txtLost" size="10" readonly>
      <script language="JavaScript">
      document.stock.txtLost.value="<%=BeanObject.getClost()%>";
      </script>
      </td>
    </tr>
    <tr>
      <td >&nbsp;</td>
      <td >&nbsp;</td>            
      <td >&nbsp;</td>
      <td Class="addedit"><input type="radio" name="R1" value="rdWithdrawn">Withdrawn</td>
      <td ><input type="text" name="txtWithdrawn" size="10" readonly>
      <script language="JavaScript">
      document.stock.txtWithdrawn.value="<%=BeanObject.getCwithdrawn()%>";
      </script>
      </td>
    </tr>        
    <tr>
    <td colspan="2">
    <input type="button" value="Report" name="report" Class="submitButton" onclick="report_stock()">
      <input type="button" value="Display" name="display" Class="submitButton" onclick="display_stock()">
      <input type="button" value="Clear" Class="submitButton" name="clear" onclick="Clear_All()">  
      <input type="button" value="Stock Details" Class="submitButton" name="stockdetails" onclick="Stock_Details()">        
      </td>
      <td >&nbsp;</td>

      <td Class="addedit"><input type="radio" name="R1" value="rdIssued">Issued</td>
      <td ><input type="text" name="txtIssued" size="10" readonly>
      <script language="JavaScript">
      document.stock.txtIssued.value="<%=BeanObject.getCissued()%>";
      </script>
      </td>
    </tr>
<!--    <tr>-->
<!--      <td Class="addedit">Access No<input type="text" name="access" size="10" onKeydown="Javascript: if (event.keyCode==13) Insert_Stock();" >-->
<!--      </td>-->
<!--      </tr>-->
    <tr>
      <td >&nbsp;</td>
      <td >&nbsp;</td>
      <td >&nbsp;</td>
      <td Class="addedit"><input type="radio" name="R1" value="rdBinding">Binding</td>
      <td ><input type="text" name="txtBinding" size="10" readonly>
       <script language="JavaScript">
      document.stock.txtBinding.value="<%=BeanObject.getCbinding()%>";
      </script>
      </td>
    </tr>
    <tr>
<!--      <td Class="addedit">Doc Type <SELECT SIZE="1" NAME="txtdoctype" onchange="doc_type()">-->
<!---->
<!--		<OPTION VALUE="BOOK">BOOK</OPTION>-->
<!--		<OPTION VALUE="BOOK BANK">BOOK BANK</OPTION>-->
<!--                <OPTION VALUE="THESIS">THESIS</OPTION>-->
<!--                <option value="BACK VOLUME">BACK VOLUME</option>-->
<!--                <option value="PROCEEDING">PROCEEDING</option>-->
<!--                <option value="REPORT">REPORTS</option>-->
<!--                <option value="NON BOOK">NON BOOK</option>-->
<!--		<option value="STANDARD">STANDARD</option>-->
<!--                <option value="ALL" selected>ALL</option>-->
<!--		</SELECT></td>-->
<!--      <td >  <input type="button" value="Stock Details" Class="submitButton" name="stockdetails" onclick="Stock_Details()">    </td>-->
    <td colspan="2">
      <input type="button" value="Delete Single Entry" name="delsing" Class="submitButton" onclick="Delete_Sing()">	
      <input type="button" value="Delete All" name="deleteall" Class="submitButton" onclick="Delete_All()">      
    </td>
      <td>&nbsp;</td>
      <!--<td ><input type="button" value="Library Holdings" name="libhold"></td> -->
      <td Class="addedit"><input type="radio" name="R1" value="rdDamaged">Damaged</td>
      <td ><input type="text" name="txtDamaged" size="10" readonly>
      <script language="JavaScript">
      document.stock.txtDamaged.value="<%=BeanObject.getCdamaged()%>";
      </script>
      </td>
    </tr>
    <tr>
      <td >&nbsp;</td>    
      <td >&nbsp;</td>
      <td >&nbsp;</td>
      <td Class="addedit"><input type="radio" name="R1" value="rdTransferred">Transferred</td>
      <td ><input type="text" name="txtTransfer" size="10" readonly>
      <script language="JavaScript">
      document.stock.txtTransfer.value="<%=BeanObject.getCtransfer()%>";
      document.stock.doc_assign.value="<%=request.getParameter("doc")%>";
      document.stock.txtdoctype.value="<%=request.getParameter("doc")%>";
      </script>
      </td>
    </tr>
    <tr>
      <td >&nbsp;</td>
      <td >&nbsp;</td>
      <td >&nbsp;</td>
      <td Class="addedit">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Total</td>
      <td ><input type="text" name="txtTotal" size="10" readonly>
      <script language="JavaScript">
      document.stock.txtTotal.value=parseInt(document.stock.txtStock.value)+parseInt(document.stock.txtNotVerify.value)+parseInt(document.stock.txtMissing.value)+parseInt(document.stock.txtLost.value)+parseInt(document.stock.txtWithdrawn.value)+parseInt(document.stock.txtIssued.value)+parseInt(document.stock.txtBinding.value)+parseInt(document.stock.txtDamaged.value)+parseInt(document.stock.txtTransfer.value);
      </script>
      </td>
    </tr>
    <tr>
      <td >&nbsp;</td>
      <td >&nbsp;</td>
      <td >&nbsp;</td>
      <td >&nbsp;</td>
      <td >&nbsp;</td>
    </tr>

<!--    <tr>-->
<!--      <td width="100%" colspan="5" align="center"><center>-->
<!--      <input type="button" value="Display" name="display" Class="submitButton" onclick="display_stock()">-->
<!--      <input type="button" value="Clear" Class="submitButton" name="clear" onclick="Clear_All()">       -->
<!--      <input type="button" value="Delete All" name="deleteall" Class="submitButton" onclick="Delete_All()">-->
<!--      <input type="button" value="Del Sing" name="delsing" Class="submitButton" onclick="Delete_Sing()">-->
<!--      <input type="button" value="Insert" name="insert" Class="submitButton" onclick="Insert_Stock()">-->
<!--      <input type="button" value="Stock Details" Class="submitButton" name="stockdetails" onclick="Stock_Details()">-->
<!--      </center>-->
<!--      </td>-->
<!--    </tr>-->
  </table>
  </td></table>
</form>
</div>
<div id="pleaseWaitDiv" style="display: none;">
<h1>Loading......</h1>
</div>
</body>
</html>
<%
  String status=request.getParameter("status");
  if(status!=null){
  if(status.equals("deletedAll")){
  %>
  <script language="JavaScript">
  alert("All the records have been deleted from Stock Mas");
  document.stock.method="post";
  document.stock.action="./StockServlet?flag=LOAD";
  document.stock.submit();
  </script>
  <%
  }
  if(status.equals("deletedSingle")){
  %>
  <script language="JavaScript">
  alert("Record has been deleted from Stock Mas");
  document.stock.method="post";
  document.stock.action="./StockServlet?flag=LOAD";
  document.stock.submit();
  </script>
  <%
  }
  if(status.equals("alreadyExist")){
  %>
  <script language="JavaScript">
  alert("Record Already Exist in Stock Mas");
  document.getElementById('wait').style.display = 'none';
 document.getElementById('pleaseWaitDiv').style.display = 'block';
  document.stock.doc_assign.value=document.stock.txtdoctype.value;
 document.stock.action="./StockServlet?flag="+document.stock.txtdoctype.value+"&check=count";
 document.stock.submit();
  </script>
  <%
  }
  if(status.equals("recordalreadyExist")){
	  
	  %>
	  <script language="JavaScript">
	  alert("Document is "+"<%=BeanObject.getavailability() %>"+"");
	  document.getElementById('wait').style.display = 'none';
	  document.getElementById('pleaseWaitDiv').style.display = 'block';
      document.stock.txtdoctype.value="<%=request.getParameter("doc")%>";	  
	  document.stock.doc_assign.value=document.stock.txtdoctype.value;
      document.stock.action="./StockServlet?flag="+document.stock.txtdoctype.value+"&check=count";
	  document.stock.submit();
	  </script>
	  <%
	  }
  
  if(status.equals("inserted")){
	 
  %>
  <script language="JavaScript">

  alert("Record has been inserted into Stock Mas");
  document.getElementById('wait').style.display = 'none';
 document.getElementById('pleaseWaitDiv').style.display = 'block';
  document.stock.doc_assign.value=document.stock.txtdoctype.value;
 
 document.stock.action="./StockServlet?flag="+document.stock.txtdoctype.value+"&check=count";
 document.stock.submit();
  </script>
  <%
  }
  if(status.equals("deletedNotSingle")){
  %>
  <script language="JavaScript">
  alert("There is no Record in the Stock Mas");
  document.stock.method="post";
  document.stock.action="./StockServlet?flag=LOAD";
  document.stock.submit();
  </script>
  <%
  }
  }
%>
<script language="JavaScript">

function display_stock() {
chosen = "";
len = document.stock.R1.length;

for (i = 0; i <len; i++) {
if (document.stock.R1[i].checked) {
chosen = document.stock.R1[i].value;
}
}

if (chosen == "") {
alert("No Radio Option Chosen")
}
else {
 document.stock.action="./StockServletDisplay?flag="+chosen+"&radioOption=true";
 document.stock.submit();
}
}


function report_stock() {
	chosen = "";
	len = document.stock.R1.length;

	for (i = 0; i <len; i++) {
	if (document.stock.R1[i].checked) {
	chosen = document.stock.R1[i].value;
	}
	}

	if (chosen == "") {
	alert("No Radio Option Chosen")
	}
	else {
	 document.stock.action="./StockReportServlet?flag="+chosen+"&radioOption=report";
	 document.stock.submit();
	}
	}

 function doc_type(){
 document.getElementById('wait').style.display = 'none';
 document.getElementById('pleaseWaitDiv').style.display = 'block';
 document.stock.doc_assign.value=document.stock.txtdoctype.value;
 document.stock.action="./StockServlet?flag="+document.stock.txtdoctype.value+"&check=count";
 document.stock.submit();
 }
 function Stock_Details() {
 document.stock.action="./StockServletDisplay?flag=display";
 document.stock.submit();
    }
    function Insert_Stock() {
    
    if(document.stock.access.value!="" || document.stock.access.value!=0){
 document.stock.action="./StockServletDisplay?flag=insert";
 document.stock.submit();
     }else{
     alert("Access No can not be left blank");
     }
    }
    function Delete_All() {
    if(document.stock.txtStock.value!=0){
    if(confirm("do you want to delete all the records from stock mas?"))
    {
    document.stock.action="./StockServletDisplay?flag=deleteAll";
    document.stock.submit();
    }
    else{

    }
    }else{
    alert("There is no Records in the Stock Mas");
    }

    }
    function total_find(){
     var stock=document.stock.txtStock.value;
      var lost=document.stock.txtLost.value;
      alert(parseInt(lost)+parseInt(stock));
      document.stock.txtTotal.value=stock+lost;
    }
    function Delete_Sing(){
     var access=prompt("Enter the access no to delete","");
     if(access!=null){
     document.stock.action="./StockServletDisplay?flag=deleteSingle&no="+access+"";
     document.stock.submit();
     }
    }
    function FindValue(val){
  winpopup=window.open("search_nmvc.jsp?flag="+val    ,"popup","height=400,width=600,left=100,top=100,scrollbars=yes,toolbar=no,status=yes,menubar=no");
    }

function Clear_All() {
document.stock.method="post";
document.stock.action="./StockServlet?flag=LOAD";
document.stock.submit();
}

</script>


