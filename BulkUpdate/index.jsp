
<%
String URole=session.getAttribute("UserRights").toString().trim();
if(URole.equalsIgnoreCase("2") || URole.equalsIgnoreCase("3") || URole.equalsIgnoreCase("4") || URole.equalsIgnoreCase("5") || URole.equalsIgnoreCase("6") || URole.equalsIgnoreCase("7"))
{		
	response.sendRedirect("sessiontimeout.jsp");
}	
%>

<%@ include file="/Tree/demoFrameset.jsp"%>
<%@ page language="java" errorPage="/Error/ErrorPage.jsp" import="java.text.SimpleDateFormat"  %>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/button_css.css" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
<meta name="GENERATOR" content="Microsoft FrontPage 4.0">
<meta name="ProgId" content="FrontPage.Editor.Document">
<title>AutoLib</title>

<script language="javascript" src="/AutoLib/popcalendar.js"></script>
<link rel="stylesheet" type="text/css" href="/AutoLib/style.css">
</head>
<%
	java.util.Date d =new java.util.Date();
	SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	String dateString = sdf.format(d);

%>


<body background="/AutoLib/soft.jpg">

<form method="POST" action="./BulkUpdateServlet" name=Bupdate>
<br><br><br>
  <h2 >Bulk&nbsp;Updation</h2>
<table align="center" class="contentTable" width="45%">
<tr>
<td>
<table border="0" width="80%" cellspacing="0" cellpadding="5" align="center">
  <tr>
    <tr>
      <td Class="addedit">Choose&nbsp;From</td>
      <td><select size="1" name="frmUpdate" style="width: 50mm" onchange="opt()">
          <option value="NO">-------------------------------</option>
          <option value="ACCESSION">Accession Number</option>
          <option value="Aut">Author Name</option>
          <option value="CALLNO">Call Number</option>
          <option value="Title">Title</option>          
          <option value="Edition">Edition</option>
          <option value="YearPub">Year</option>
          <option value="Dept">Department Name</option>
          <option value="Pub">Publisher Name</option>          
          <option value="Sub">Subject Name</option>
          <option value="Sup">Supplier Name</option>
          <option value="Bud">Budget Name</option>
          <option value="Availability">Availability</option>
          <option value="Invoice_No">Invoice_No</option> 
          <option value="Invoice_date">Invoice Date</option> 
          <option value="RECEIVEDDATE">Received Date</option>
          <option value="ISBN">ISBN</option>
          <option value="Bprice">Bprice</option> 
          <option value="Discount">Discount</option> 
          <option value="VolumeNo">Volume Number</option>
          <option value="Language">Language</option>
          <option value="Location">Location</option>
          <option value="PurchaseType">Purchase Type</option>
          <option value="biblio">Notes</option>
          <option value="Keywords">Keywords</option>
                    
          
          
       
        </select></td>
        
        <td Class="addedit">Choose&nbsp;To</td>
        <td><select size="1" name="toUpdate" style="width: 50mm" onchange="optNew()">
          <option value="NO">-------------------------------</option>          
          <option value="Aut">Author Name</option>
          <option value="CALLNO">Call Number</option>
           <option value="Title">Title</option>          
          <option value="Edition">Edition</option>
          <option value="YearPub">Year</option>
          <option value="Dept">Department Name</option>
          <option value="Pub">Publisher Name</option>          
          <option value="Sub">Subject Name</option>
          <option value="Sup">Supplier Name</option>
          <option value="Bud">Budget Name</option>
          <option value="Availability">Availability</option>
          <option value="Invoice_No">Invoice_No</option> 
          <option value="RECEIVEDDATE">Received Date</option>
          <option value="Invoice_date">Invoice Date</option> 
          <option value="ISBN">ISBN</option>
          <option value="Bprice">Bprice</option> 
          <option value="Discount">Discount</option> 
          <option value="VolumeNo">Volume Number</option>
          <option value="Language">Language</option>
          <option value="Location">Location</option>
          <option value="PurchaseType">Purchase Type</option>
          <option value="biblio">Notes</option>
          <option value="Keywords">Keywords</option>          
<!--           <option value="BPrice">Book Price</option>           -->
         
         
       
        </select>
        </td>
        
    </tr>
    
     <tr>
      <td Class="addedit">From&nbsp;Value</td>
      <td>
      <select name="frmValue" size="1"  style="width: 50mm">
      <option value="NO">************</option>            
      <c:if test="${resultBulkList1 ne null}">      
       <c:forEach items="${resultBulkList1}" var="std" varStatus="loop" >
         <option value="<c:out value="${std.code}" />"> <c:out value="${std.name}" /> </option>
       </c:forEach>
      </c:if>     
      </select>  
      </td>   
      
      <td Class="addedit">To&nbsp;Value</td>
      <td>
      <select name="toValue" size="1"  style="width: 50mm">
      <option value="NO">************</option>            
      <c:if test="${resultBulkList2 ne null}">      
       <c:forEach items="${resultBulkList2}" var="std" varStatus="loop" >
         <option value="<c:out value="${std.code}" />"> <c:out value="${std.name}" /> </option>
       </c:forEach>
      </c:if>     
      </select>  
      </td> 
    </tr>
    
   <tr>
      <td Class="addedit">New Value</td>
      <td colspan="3"><input type="text" name="optionValue" size="53" disabled></td>
    </tr>
    
    <tr>
      <td></td>
      <td></td>
    </tr>
    <tr>
    <td Class="addedit" >From&nbsp;Acc.No&nbsp;</td>
    <td><input type="text" name="From_Accno" size="10" maxlength=10 ></td>
    <td Class="addedit" >To&nbsp;Acc.No&nbsp;</td>
    <td><input  type="text" name="To_Accno" size="10" maxlength=10 ></td>
      </tr>
      <tr>
    <td Class="addedit" >Rnd&nbsp;Acc.No&nbsp;</td><td><input type="text" name="accno" style="height:75px;" size="50" maxlength=500 ></td> 
    </tr>
    
    <tr>
    <td colspan="4" Class="addedit" name="doc">Document:
    <input type="checkbox" name="docValue[]" value="ALL">ALL
    <input type="checkbox" name="docValue[]" value="BOOK">BOOK
    <input type="checkbox" name="docValue[]" value="BOOK BANK">BOOK BANK
    <input type="checkbox" name="docValue[]" value="NON BOOK">NON BOOK
    <input type="checkbox" name="docValue[]" value="THESIS">THESIS
    <input type="checkbox" name="docValue[]"  value="PROCEEDING">PROCEEDING
    <input type="checkbox" name="docValue[]" value="REPORT">REPORT
    </td>
    </tr>
      <tr>
        <td Class="addedit" >From&nbsp;Date&nbsp;</td><td>
	<INPUT name=fromdt size=10  onfocus=this.blur(); value="<%=dateString%>" disabled>
				<SCRIPT language=javascript>
						if (!document.layers) {
						document.write("<input type=button onclick='popUpCalendar(this,Bupdate.fromdt, \"dd-mm-yyyy\",\"<%=dateString%>\")'value='...' style='font-size:10 px'>")
						}
				</SCRIPT>
		 </td>
        <td Class="addedit" >To&nbsp;Date&nbsp;</td><td>        
	<INPUT name=todt size=10  onfocus=this.blur(); value="<%=dateString%>" disabled>
				<SCRIPT language=javascript>
						if (!document.layers) {
						document.write("<input type=button onclick='popUpCalendar(this,Bupdate.todt, \"dd-mm-yyyy\",\"<%=dateString%>\")'value='...' style='font-size:10 px'>")
						}
				</SCRIPT>
		 </td>
      </tr>
    <tr>
      <td></td>
      <td></td>
    </tr>
    <tr>    
    <td>&nbsp;</td>
            <td align="center" colspan="2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <input type="button" Class="submitButton" value="Update" name="print" onclick="saveRec()">&nbsp;
            <input type="reset" Class="submitButton" value="Clear" name="clear" onclick="clearRec()">
            <input type="hidden" name="flag" >
            <input type="hidden" name="flag1">
            <input type="hidden" name="firstNum">
		    <input type="hidden" name="secondNum">		   
            <input type="hidden" name="firstStr">
		    <input type="hidden" name="secondStr">
            </td>
    </tr>
  </table></td></tr></table>

<br><br><br>
<table cellspacing="0" cellpadding="2" id="noteStruct">
		<tr> <td id="noteHead"> Note: </td> </tr> 
		<tr id="noteBody"> 
			<td> 
				<ul> 
					<li> In Author Update Author Must be a Single Author. </li>
				</ul> 
			</td> 
		</tr> 
	</table>

</form>	
</body>
</html>

<c:if test="${beanForm ne null }">
 <script language="JavaScript">
   document.Bupdate.frmUpdate.value="<c:out value="${beanForm.frmUpdate}" />";
   document.Bupdate.toUpdate.value="<c:out value="${beanForm.toUpdate}" />";
   
   document.Bupdate.frmValue.value="<c:out value="${beanForm.frmValue}" />";
   document.Bupdate.toValue.value="<c:out value="${beanForm.toValue}" />";   
 </script>
</c:if>

<c:if test="${resultDone gt 0 }">
 <script language="JavaScript">
   alert(<c:out value="${resultDone}" /> + " Record updated successfully !");
 </script>
</c:if>
 
<c:if test="${resultDone eq 0 }">
 <script language="JavaScript">
   alert("Record not found / Updation failed !");
 </script>
</c:if>



<script language="JavaScript">

if(document.Bupdate.frmUpdate.value=="ACCESSION")
{
document.Bupdate.From_Accno.disabled=false;
document.Bupdate.To_Accno.disabled=false;
document.Bupdate.frmValue.disabled=true;
}

if(document.Bupdate.frmUpdate.value=="RECEIVEDDATE")
{
document.Bupdate.fromdt.disabled=false;
document.Bupdate.todt.disabled=false;
document.Bupdate.frmValue.disabled=true;
}

if(document.Bupdate.toUpdate.value=="RECEIVEDDATE")
{
document.Bupdate.fromdt.disabled=false;
document.Bupdate.todt.disabled=false;
document.Bupdate.toValue.disabled=true;
}
if(document.Bupdate.frmUpdate.value=="Invoice_date")
{
document.Bupdate.fromdt.disabled=false;
document.Bupdate.todt.disabled=false;
document.Bupdate.frmValue.disabled=true;
}

if(document.Bupdate.toUpdate.value=="Invoice_date")
{
document.Bupdate.fromdt.disabled=false;
document.Bupdate.todt.disabled=false;
document.Bupdate.toValue.disabled=true;
}

if(document.Bupdate.toUpdate.value=="CALLNO" || document.Bupdate.toUpdate.value=="Title" || document.Bupdate.toUpdate.value=="Edition" || document.Bupdate.toUpdate.value=="YearPub" || document.Bupdate.toUpdate.value=="Keywords" || document.Bupdate.toUpdate.value=="BPrice" || document.Bupdate.toUpdate.value=="ISBN" || document.Bupdate.toUpdate.value=="VolumeNo" || document.Bupdate.toUpdate.value=="Location" || document.Bupdate.toUpdate.value=="Invoice_No")
{
document.Bupdate.optionValue.disabled=false;
document.Bupdate.toValue.disabled=true;
document.Bupdate.optionValue.focus();
}





function saveRec()
{
	
	var content="";
    var chkitem=0;	
	var cboxes = document.getElementsByName('docValue[]');	 
    var len = cboxes.length;	
     
    for(i=0;i<len;i++)  {
    	if(cboxes[i].checked == true)
        {
    		chkitem++;
            content=content+","+cboxes[i].value;
    		//alert("From "+cboxes[i].value);
        }
    }
    if(chkitem >= 1)
 	{	   
	   //alert(content);
       document.Bupdate.flag1.value=content;     
 	}
    else
    	{
    	alert("Please Select the Document !");
    	return false;
    	}
	
if(document.Bupdate.From_Accno.value=="" && document.Bupdate.To_Accno.value!="")
{
	alert("Please fill from access number !");    
	document.Bupdate.From_Accno.focus();
	return false;	
}

if(document.Bupdate.From_Accno.value!="" && document.Bupdate.To_Accno.value=="")
{
	alert("Please fill to access number !");
	document.Bupdate.To_Accno.focus();
	return false;
}
if(document.Bupdate.From_Accno.value!="" && document.Bupdate.To_Accno.value!="")
{
	var firstNum = document.Bupdate.From_Accno.value;
	firstNum = firstNum.replace(/[^0-9]+/ig,"");
	//alert(firstNum);//number
	var firstStr = document.Bupdate.From_Accno.value;
	firstStr = firstStr.replace(/[0-9]+/ig,"");
	//alert(firstStr);//string value
	var secondNum = document.Bupdate.To_Accno.value;
	secondNum = secondNum.replace(/[^0-9]+/ig,"");
	//alert(secondNum);//number
	var secondStr = document.Bupdate.To_Accno.value;
	secondStr = secondStr.replace(/[0-9]+/ig,"");
	//alert(secondStr);//string value
	
	document.Bupdate.firstStr.value=firstStr;
	document.Bupdate.secondStr.value=secondStr;
 	document.Bupdate.firstNum.value=firstNum;
 	document.Bupdate.secondNum.value=secondNum;
 	
 	 document.Bupdate.flag.value="update";
 	 document.Bupdate.submit();
 	
 	
}
	
/**if(document.Bupdate.frmUpdate.value=="ACCESSION")
{
  if(document.Bupdate.From_Accno.value=="")
  {
    alert("Please fill the field !");    
    document.Bupdate.From_Accno.focus();
    return false;
  }else if(document.Bupdate.To_Accno.value=="")
  {
    alert("Please fill the field !");
    document.Bupdate.To_Accno.focus();
    return false;
  }else {
    //document.Bupdate.flag.value="update";
    //document.Bupdate.submit();    
  }
}*/


if(document.Bupdate.frmUpdate.value=="RECEIVEDDATE")
{
  if(document.Bupdate.fromdt.value=="" || document.Bupdate.todt.value=="")
  {
    alert("Please select the correct date !");
    document.Bupdate.fromdt.focus();
    return false;
  }else {
    //document.Bupdate.flag.value="update";
    //document.Bupdate.submit();    
  }

}
if(document.Bupdate.frmUpdate.value=="Invoice_date")
{
  if(document.Bupdate.fromdt.value=="" || document.Bupdate.todt.value=="")
  {
    alert("Please select the correct date !");
    document.Bupdate.fromdt.focus();
    return false;
  }else {
    //document.Bupdate.flag.value="update";
    //document.Bupdate.submit();    
  }

}

if(document.Bupdate.frmValue.value=="NO" && document.Bupdate.frmUpdate.value!="ACCESSION" && document.Bupdate.frmUpdate.value!="RECEIVEDDATE" && document.Bupdate.frmUpdate.value!="Invoice_date")
{
  alert("Invalid selection of from value !");
  return false;
}




if(document.Bupdate.toUpdate.value=="RECEIVEDDATE")
{
  if(document.Bupdate.fromdt.value=="" || document.Bupdate.todt.value=="")
  {
    alert("Please select the correct date !");
    document.Bupdate.fromdt.focus();
    return false;
  }else {
    //document.Bupdate.flag.value="update";
    //document.Bupdate.submit();    
  }

}
if(document.Bupdate.toUpdate.value=="Invoice_date")
{
  if(document.Bupdate.fromdt.value=="" || document.Bupdate.todt.value=="")
  {
    alert("Please select the correct date !");
    document.Bupdate.fromdt.focus();
    return false;
  }else {
    //document.Bupdate.flag.value="update";
    //document.Bupdate.submit();    
  }

}

if(document.Bupdate.toUpdate.value=="CALLNO")
{
  if(document.Bupdate.optionValue.value=="")
  {
   alert("Enter Valid Call No !");
   document.Bupdate.optionValue.focus();
   return false;
  }else {
   document.Bupdate.flag.value="update";
   document.Bupdate.submit();
  }
}

else if(document.Bupdate.toUpdate.value=="Title")
{
  if(document.Bupdate.optionValue.value=="")
  {
   alert("Enter Valid Title !");
   document.Bupdate.optionValue.focus();
   return false;
  }else {
   document.Bupdate.flag.value="update";
   document.Bupdate.submit();
  }
}

else if(document.Bupdate.toUpdate.value=="Edition")
{
  if(document.Bupdate.optionValue.value=="")
  {
   alert("Enter Valid Edition !");
   document.Bupdate.optionValue.focus();
   return false;
  }else {
   document.Bupdate.flag.value="update";
   document.Bupdate.submit();
  }
}

else if(document.Bupdate.toUpdate.value=="YearPub")
{
  if(document.Bupdate.optionValue.value=="")
  {
      alert("Enter Valid YearPub !");
      document.Bupdate.optionValue.focus();
      return false;
  }else if((isNaN(document.Bupdate.optionValue.value))||(document.Bupdate.optionValue.value == ' ')) {
	  alert("Enter Valid YearPub !");
	  document.Bupdate.optionValue.select();
	  document.Bupdate.optionValue.value="";
      return false;
  }else {
      document.Bupdate.flag.value="update";
      document.Bupdate.submit();
  }
}

else if(document.Bupdate.toUpdate.value=="Keywords")
{
  if(document.Bupdate.optionValue.value=="")
  {
   alert("Enter Valid Keywords !");
   document.Bupdate.optionValue.focus();
   return false;
  }else {
   document.Bupdate.flag.value="update";
   document.Bupdate.submit();
  }
}

else if(document.Bupdate.toUpdate.value=="Location")
{
  if(document.Bupdate.optionValue.value=="")
  {
   alert("Enter Valid Location !");
   document.Bupdate.optionValue.focus();
   return false;
  }else {
   document.Bupdate.flag.value="update";
   document.Bupdate.submit();
  }
}

else if(document.Bupdate.toUpdate.value=="VolumeNo")
{
  if(document.Bupdate.optionValue.value=="")
  {
   alert("Enter Valid VolumeNo !");
   document.Bupdate.optionValue.focus();
   return false;
  }else {
   document.Bupdate.flag.value="update";
   document.Bupdate.submit();
  }
}

else if(document.Bupdate.toUpdate.value=="ISBN")
{
  if(document.Bupdate.optionValue.value=="")
  {
   alert("Enter Valid ISBN !");
   document.Bupdate.optionValue.focus();
   return false;
  }else {
   document.Bupdate.flag.value="update";
   document.Bupdate.submit();
  }
}

else if(document.Bupdate.toUpdate.value=="Invoice_No")
{
  if(document.Bupdate.optionValue.value=="")
  {
   alert("Enter Valid Invoice_No !");
   document.Bupdate.optionValue.focus();
   return false;
  }else {
   document.Bupdate.flag.value="update";
   document.Bupdate.submit();
  }
}



else if(document.Bupdate.toUpdate.value=="BPrice")
{
  if(document.Bupdate.optionValue.value=="")
  {
      alert("Enter Valid Book Price !");
      document.Bupdate.optionValue.focus();
      return false;
  }else if((isNaN(document.Bupdate.optionValue.value))||(document.Bupdate.optionValue.value == ' ')) {
	  alert("Enter Valid Book Price !");
	  document.Bupdate.optionValue.select();
	  document.Bupdate.optionValue.value="";
      return false;
  }else {
      document.Bupdate.flag.value="update";
      document.Bupdate.submit();
  }
}


else if(document.Bupdate.frmUpdate.value=="NO")
{
  alert("Invalid selection of from value !");
  return false;
}
else if(document.Bupdate.toUpdate.value=="NO")
{
  alert("Invalid selection of to value !");
  return false;
}
else if(document.Bupdate.toValue.value=="NO" && document.Bupdate.toUpdate.value!="RECEIVEDDATE" && document.Bupdate.toUpdate.value!="Invoice_date")
{
  alert("Invalid selection of to value !");
  return false;
}
else{
  document.Bupdate.flag.value="update";
  document.Bupdate.submit();
}
}


function opt()
{
document.Bupdate.frmValue.value="NO";
var str=document.Bupdate.frmUpdate.value;

if(document.Bupdate.toUpdate.value=="CALLNO" || document.Bupdate.toUpdate.value=="Title" || document.Bupdate.toUpdate.value=="Edition" || document.Bupdate.toUpdate.value=="YearPub" || document.Bupdate.toUpdate.value=="Keywords" || document.Bupdate.toUpdate.value=="BPrice" || document.Bupdate.toUpdate.value=="ISBN" || document.Bupdate.toUpdate.value=="VolumeNo" || document.Bupdate.toUpdate.value=="Location" || document.Bupdate.toUpdate.value=="Invoice_No")
{
document.Bupdate.optionValue.disabled=false;
document.Bupdate.toValue.disabled=true;
document.Bupdate.optionValue.focus();
}

if(str=="ACCESSION")
{
document.Bupdate.From_Accno.disabled=false;
document.Bupdate.To_Accno.disabled=false;
//document.Bupdate.optionValue.disabled=true;
document.Bupdate.fromdt.disabled=true;
document.Bupdate.todt.disabled=true;
document.Bupdate.frmValue.disabled=true;
document.Bupdate.From_Accno.focus();
}
else if(str=="RECEIVEDDATE")
{
//document.Bupdate.From_Accno.disabled=true;
//document.Bupdate.To_Accno.disabled=true;
document.Bupdate.fromdt.disabled=false;
document.Bupdate.todt.disabled=false;
document.Bupdate.frmValue.disabled=true;
}
else if(str=="Invoice_date")
{
//document.Bupdate.From_Accno.disabled=true;
//document.Bupdate.To_Accno.disabled=true;
document.Bupdate.fromdt.disabled=false;
document.Bupdate.todt.disabled=false;
document.Bupdate.frmValue.disabled=true;
}
else if(str=="NO")
{
document.Bupdate.frmValue.disabled=true;
}
else
{
document.Bupdate.fromdt.disabled=true;
document.Bupdate.todt.disabled=true;
//document.Bupdate.From_Accno.disabled=true;
//document.Bupdate.To_Accno.disabled=true;
document.Bupdate.optionValue.disabled=true;
document.Bupdate.flag.value="search";
document.Bupdate.submit();
}




}

function optNew()
{
document.Bupdate.toValue.value="NO";
var str=document.Bupdate.toUpdate.value;

if(str=="RECEIVEDDATE")
{
//document.Bupdate.From_Accno.disabled=true;
//document.Bupdate.To_Accno.disabled=true;
document.Bupdate.optionValue.disabled=true;
document.Bupdate.fromdt.disabled=false;
document.Bupdate.todt.disabled=true;
document.Bupdate.toValue.disabled=true;
document.Bupdate.optionValue.disabled=true;
}
else if(str=="Invoice_date")
{
//document.Bupdate.From_Accno.disabled=true;
//document.Bupdate.To_Accno.disabled=true;
document.Bupdate.optionValue.disabled=true;
document.Bupdate.fromdt.disabled=false;
document.Bupdate.todt.disabled=true;
document.Bupdate.toValue.disabled=true;
document.Bupdate.optionValue.disabled=true;
}

else if(str=="CALLNO")
{
//document.Bupdate.From_Accno.disabled=true;
//document.Bupdate.To_Accno.disabled=true;
document.Bupdate.optionValue.disabled=false;
document.Bupdate.fromdt.disabled=true;
document.Bupdate.todt.disabled=true;
document.Bupdate.toValue.disabled=true;
document.Bupdate.optionValue.focus();
}

else if(str=="Title")
{
//document.Bupdate.From_Accno.disabled=true;
//document.Bupdate.To_Accno.disabled=true;
document.Bupdate.optionValue.disabled=false;
document.Bupdate.fromdt.disabled=true;
document.Bupdate.todt.disabled=true;
document.Bupdate.toValue.disabled=true;
document.Bupdate.optionValue.focus();
}

else if(str=="Edition")
{
//document.Bupdate.From_Accno.disabled=true;
//document.Bupdate.To_Accno.disabled=true;
document.Bupdate.optionValue.disabled=false;
document.Bupdate.fromdt.disabled=true;
document.Bupdate.todt.disabled=true;
document.Bupdate.toValue.disabled=true;
document.Bupdate.optionValue.focus();
}

else if(str=="YearPub")
{
//document.Bupdate.From_Accno.disabled=true;
//document.Bupdate.To_Accno.disabled=true;
document.Bupdate.optionValue.disabled=false;
document.Bupdate.fromdt.disabled=true;
document.Bupdate.todt.disabled=true;
document.Bupdate.toValue.disabled=true;
document.Bupdate.optionValue.focus();
}

else if(str=="Keywords")
{
//document.Bupdate.From_Accno.disabled=true;
//document.Bupdate.To_Accno.disabled=true;
document.Bupdate.optionValue.disabled=false;
document.Bupdate.fromdt.disabled=true;
document.Bupdate.todt.disabled=true;
document.Bupdate.toValue.disabled=true;
document.Bupdate.optionValue.focus();
}

else if(str=="Location")
{
//document.Bupdate.From_Accno.disabled=true;
//document.Bupdate.To_Accno.disabled=true;
document.Bupdate.optionValue.disabled=false;
document.Bupdate.fromdt.disabled=true;
document.Bupdate.todt.disabled=true;
document.Bupdate.toValue.disabled=true;
document.Bupdate.optionValue.focus();
}



else if(str=="VolumeNo")
{
//document.Bupdate.From_Accno.disabled=true;
//document.Bupdate.To_Accno.disabled=true;
document.Bupdate.optionValue.disabled=false;
document.Bupdate.fromdt.disabled=true;
document.Bupdate.todt.disabled=true;
document.Bupdate.toValue.disabled=true;
document.Bupdate.optionValue.focus();
}



else if(str=="Invoice_No")
{
//document.Bupdate.From_Accno.disabled=true;
//document.Bupdate.To_Accno.disabled=true;
document.Bupdate.optionValue.disabled=false;
document.Bupdate.fromdt.disabled=true;
document.Bupdate.todt.disabled=true;
document.Bupdate.toValue.disabled=true;
document.Bupdate.optionValue.focus();
}


else if(str=="ISBN")
{
//document.Bupdate.From_Accno.disabled=true;
//document.Bupdate.To_Accno.disabled=true;
document.Bupdate.optionValue.disabled=false;
document.Bupdate.fromdt.disabled=true;
document.Bupdate.todt.disabled=true;
document.Bupdate.toValue.disabled=true;
document.Bupdate.optionValue.focus();
}

else if(str=="BPrice")
{
//document.Bupdate.From_Accno.disabled=true;
//document.Bupdate.To_Accno.disabled=true;
document.Bupdate.optionValue.disabled=false;
document.Bupdate.fromdt.disabled=true;
document.Bupdate.todt.disabled=true;
document.Bupdate.toValue.disabled=true;
document.Bupdate.optionValue.focus();
}

else if(str=="NO")
{
document.Bupdate.toValue.disabled=true;
}
else
{
document.Bupdate.fromdt.disabled=true;
document.Bupdate.todt.disabled=true;
//document.Bupdate.From_Accno.disabled=true;
//document.Bupdate.To_Accno.disabled=true;
document.Bupdate.optionValue.disabled=true;
document.Bupdate.flag.value="search";
document.Bupdate.submit();
}

if(document.Bupdate.frmUpdate.value=="ACCESSION")
{
document.Bupdate.From_Accno.disabled=false;
document.Bupdate.To_Accno.disabled=false;
document.Bupdate.frmValue.disabled=true;
}

if(document.Bupdate.frmUpdate.value=="RECEIVEDDATE")
{
document.Bupdate.fromdt.disabled=false;
document.Bupdate.todt.disabled=false;
document.Bupdate.frmValue.disabled=true;
}
if(document.Bupdate.frmUpdate.value=="Invoice_date")
{
document.Bupdate.fromdt.disabled=false;
document.Bupdate.todt.disabled=false;
document.Bupdate.frmValue.disabled=true;
}

}

function clearRec()
{
	document.Bupdate.action="index.jsp";
	document.Bupdate.submit();  
}

function home()
{
location.href="/AutoLib/Home.jsp";
}

function Logout()
{
location.href="/AutoLib/index.html";
}
</script>
