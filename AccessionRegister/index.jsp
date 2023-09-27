<%@ include file="/Tree/demoFrameset.jsp"%>
<%@ page language="java" errorPage="/Error/ErrorPage.jsp" session="true" buffer="12kb" import="java.sql.*,java.util.*"%>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/button_css.css" />
<%@ page import="java.util.Calendar" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat"%>



<!--
//////////////////////////////////////////////////////////////////FORM CONTROLS//////////////////////////////////////////////////////////////////////// -->
<html>
<head>
<title>Auto Lib</title>
<script language="javascript" src="/AutoLib/popcalendar.js"></script>
<link rel="stylesheet" type="text/css" href="/AutoLib/style.css">
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/jquery-ui.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-3.1.0.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-ui.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/datepicker.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/datepicker2.js"></script>

<meta http-equiv="pragma" content="no-cache"/>
</head>
<body background="/AutoLib/soft.jpg">
<%
	java.util.Date d =new java.util.Date();
	SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	String dateString = sdf.format(d);
	String Message=null;

%>

	

<form name="Access_Register" method="Post" action=./AccessionServlet>

<br><br><br>

<h2>Accession&nbsp;Register</h2>
  <div align="center">
    
  <table align="center" class="contentTable" width="55%">
  <tr><td>
  
<table align="center" width="90%">
<tr><td> &nbsp; </td></tr>

      <tr>
      <td  Class="addedit"> <input type=radio value=byaccno name=acc onclick=change()>By&nbsp;Acc.&nbsp;No.</td>
      <td  Class="addedit">From</td><td> <input type="text" name="From_Accno" size="17" maxlength=10></td>
      <td  Class="addedit">To</td><td> <input  type="text" name="To_Accno" size="17" maxlength=10></td>
      </tr>
      
      <tr>
        <td  Class="addedit"><input type=radio value=bydate name=acc1 onclick=changes() checked>By Date</td>
        <td  Class="addedit">From</td><td>
<%-- 	<INPUT name=fromdt size=11  onfocus=this.blur(); value="<%=dateString%>" > --%>
	<INPUT name=fromdt size=11 id="datepicker" value="<%=dateString%>" >
								
				</td>
				
        <td  Class="addedit">To</td><td>
<%-- 	<INPUT name=todt size=11  onfocus=this.blur(); value="<%=dateString%>" > --%>
	<INPUT name=todt size=11 id="datepicker2" value="<%=dateString%>" >
								
				</td>
      </tr>
      

      
      <tr>
      <td></td>
      
      <td  Class="addedit">Document</td>
      <td><select name="Type" size="1" style="width: 125px">
  	  <option selected value="ALL">ALL</option>
      <option  value="BOOK">BOOK</option>
      <option value="BOOK BANK">BOOK BANK</option>
	  <option value="NON BOOK">NON BOOK </option>
	  <option value="REPORT">REPORT</option>
	  <option value="THESIS">THESIS</option>
	  <option value="STANDARD">STANDARD</option>
	  <option value="PROCEEDING">PROCEEDING</option>	  
	  <option value="BACK VOLUME">BACK VOLUME</option>
 </select></td>
 
 <td  Class="addedit">Avail.</td>
 <td>
 
 
 <select name="avail" size="1" style="width: 125px">
  	  <option selected value="ALL">ALL</option>
  	  <option value="YES">YES</option>
      <option value="REFERENCE">REFERENCE</option>
      <option value="DISPLAY">DISPLAY</option>            
      <option value="MISSING">MISSING</option>
      <option value="WITHDRAWN">WITHDRAWN</option>
      <option value="LOST">LOST</option>            
      <option value="DAMAGED">DAMAGED</option>
      <option value="ISSUED">ISSUED</option>
      <option value="BINDING">BINDING</option>
	  <option value="TRANSFERED">TRANSFERED</option>
	  </select></td></tr>
 
 <tr>
 
 <td  Class="addedit">

 <input type=radio value=random name=randomNumber onclick=RndChanges()>Rnd.No.</td>
  <td Class="addedit">Acc.No.</td>
  <td><input type=text name="RndNo" size="17" maxlength=15 onKeydown='Javascript: if (event.keyCode==13) myRndNoInsertFunction()'></td>.
	 <td Class="addedit">Purchase Type<td>
	 <select size="1" name="purchaseType" style="width: 110px">
	 <option selected value="ALL">ALL</option>
      <option  value="Purchase" >Purchase</option>
      <option value="Gift" >Gift</option>
      <option value="Others" >Others</option>
    </select>
 </tr>
 
       <tr>
       <td></td>
      <br/>
      <td Class="addedit">Year_Pub</td><td><input type=text name="year_pub" size="17" maxlength=15 ></td>
      
      </tr>
      	
      <tr>


        <td colspan="5">
        <p align="center">
		  <input type="button" Class="submitButton" value="Print" name="Access_Print" onclick="Print_Report()">
          <input type="button" Class="submitButton" value="MisAccNo" name="Missing_Access_Print" onclick="Missing_Print_Report()" >
          <input type="reset" Class="submitButton" value="Clear" name="Access_clear" onClick="refreshPage()">
		  <a href="#" onclick="Print_ExcelReport()"> <img src="<%= request.getContextPath() %>/images/xls.gif" width="35" height="40" border="0" title="Print Excel"></a>
		  
		  <input type="hidden" name="flag">
		  <input type="hidden" name="flagExcel">		  
		  <input type="hidden" name="flagNo">
		  <input type="hidden" name="flagNotNumber">
		  <input type="hidden" name="randomNumberList">
		  
		  <input type="hidden" name="firstStr">
		  <input type="hidden" name="secondStr">
		  <input type="hidden" name="firstNum">
		  <input type="hidden" name="secondNum">
		  
		  
	
		  </p></td></tr></table></td></tr></table></div>
		  
<table align="center">	
<tr><td>&nbsp;</td></tr>

<tr>
<td Class="addedit"><select id="mySelect" size="15"><option>AccessNo</option></select></td>
</tr>

</table>





<%
String valid=request.getParameter("check");
if(valid!=null){

if(valid.equals("date")){
	
%>
            <script >
	alert("From date grater then or  equal to To date");
	</script>
			<%
			}

if(valid.equals("NoData")){	%>
  <script >
	alert("No Record Found");
	</script>

<%}


if(valid.equals("RecordNot")){
%>
            <script >
	alert("Record Not Found");
	history.back();
	</script>
			<%
			}
			if(valid.equals("NotValidRange")){
%>
            <script >
	alert("Not A Valid Range");
	history.back();
	</script>
			<%
			}
}%>

<script language="javascript">


function myRndNoInsertFunction() {

    var x = document.getElementById("mySelect");
    var option = document.createElement("option");
    

    option.text = document.Access_Register.RndNo.value;
 
    x.add(option);
     document.Access_Register.RndNo.value="";
       
}


function home()
{
location.href="/AutoLib/Home.jsp";
}

function Logout()
{
location.href="/AutoLib/index.html";
}

function refreshPage(){
   window.location.reload();
}


function Print_ExcelReport()
{


if(document.Access_Register.acc.checked==true)
{
	var firstNum = document.Access_Register.From_Accno.value;
	firstNum = firstNum.replace(/[^0-9]+/ig,"");
	//alert(firstNum);//number
	var firstStr = document.Access_Register.From_Accno.value;
	firstStr = firstStr.replace(/[0-9]+/ig,"");
	//alert(firstStr);//string value
	var secondNum = document.Access_Register.To_Accno.value;
	secondNum = secondNum.replace(/[^0-9]+/ig,"");
	//alert(secondNum);//number
	var secondStr = document.Access_Register.To_Accno.value;
	secondStr = secondStr.replace(/[0-9]+/ig,"");
	//alert(secondStr);//string value
	
  if(document.Access_Register.From_Accno.value=="" || document.Access_Register.To_Accno.value=="" || firstNum=="" || secondNum=="")
  {
     alert("Insufficient Data !");
  }else if(firstStr!=secondStr){
		alert("Enter correct Range of AccessNo !");
		
  }
  else
  {
	  document.Access_Register.firstStr.value=firstStr;
		document.Access_Register.secondStr.value=secondStr;
	 	document.Access_Register.firstNum.value=firstNum;
	 	document.Access_Register.secondNum.value=secondNum;
	 	
	 	
    document.Access_Register.flagExcel.value="ExcelReport";
    document.Access_Register.flag.value="AccessNo_Wise";
    document.Access_Register.submit();
  }
}
else if(document.Access_Register.randomNumber.checked==true)
{

	document.Access_Register.flagExcel.value="ExcelReport";
    document.Access_Register.flag.value="RndNumber";

    var x = document.getElementById("mySelect");
    var txt = "ZXCVB";//temp
    var i;
    for (i = 0; i < x.length; i++) {
       
        txt = txt + "','" + x.options[i].value;
         document.Access_Register.randomNumberList.value=txt;
    }
    document.Access_Register.submit();
}

else if(document.Access_Register.acc1.checked==true)
{
    document.Access_Register.flagExcel.value="ExcelReport";
    document.Access_Register.flag.value="Date_Wise";
    document.Access_Register.submit();
}

else
{
   alert("Choose either by AccessNo or by Date wise !");
}

}

function Missing_Print_Report(){

	if(document.Access_Register.acc.checked==true){
		
		var firstNum = document.Access_Register.From_Accno.value;
		firstNum = firstNum.replace(/[^0-9]+/ig,"");
		//alert(firstNum);//number
		var firstStr = document.Access_Register.From_Accno.value;
		firstStr = firstStr.replace(/[0-9]+/ig,"");
		//alert(firstStr);//string value
		var secondNum = document.Access_Register.To_Accno.value;
		secondNum = secondNum.replace(/[^0-9]+/ig,"");
		//alert(secondNum);//number
		var secondStr = document.Access_Register.To_Accno.value;
		secondStr = secondStr.replace(/[0-9]+/ig,"");
		//alert(secondStr);//string value

	if(document.Access_Register.From_Accno.value=="" || document.Access_Register.To_Accno.value=="" || firstNum=="" || secondNum=="")
	{
	alert("Insufficient Data !");
	}else if(firstStr!=secondStr){
		alert("Enter correct Range of AccessNo !");
		}else{
	document.Access_Register.flagExcel.value="PdfMissingReport";
	document.Access_Register.flag.value="Missing_AccessNo_Wise";
	document.Access_Register.submit();
	}
		}
				}


function Print_Report()
{
	
	

if(document.Access_Register.acc.checked==true)
{
	var firstNum = document.Access_Register.From_Accno.value;
	firstNum = firstNum.replace(/[^0-9]+/ig,"");
	//alert(firstNum);//number
	var firstStr = document.Access_Register.From_Accno.value;
	firstStr = firstStr.replace(/[0-9]+/ig,"");
	//alert(firstStr);//string value
	var secondNum = document.Access_Register.To_Accno.value;
	secondNum = secondNum.replace(/[^0-9]+/ig,"");
	//alert(secondNum);//number
	var secondStr = document.Access_Register.To_Accno.value;
	secondStr = secondStr.replace(/[0-9]+/ig,"");
	//alert(secondStr);//string value

	
if(document.Access_Register.From_Accno.value=="" || document.Access_Register.To_Accno.value=="" || firstNum=="" || secondNum=="")
{
alert("Insufficient Data !");
}

else if(firstStr!=secondStr){
	alert("Enter correct Range of AccessNo !");
	
}
else
{
	document.Access_Register.firstStr.value=firstStr;
	document.Access_Register.secondStr.value=secondStr;
 	document.Access_Register.firstNum.value=firstNum;
 	document.Access_Register.secondNum.value=secondNum;
 	
document.Access_Register.flagExcel.value="PdfReport";
document.Access_Register.flag.value="AccessNo_Wise";
document.Access_Register.submit();
}
}
else
{
if(document.Access_Register.acc1.checked==true)
{
document.Access_Register.flagExcel.value="PdfReport";
document.Access_Register.flag.value="Date_Wise";
document.Access_Register.submit();
}
}

}
function change(){//accessno

document.Access_Register.acc1.checked=false;
document.Access_Register.randomNumber.checked=false;
}

function changes(){//date
document.Access_Register.acc.checked=false;
document.Access_Register.randomNumber.checked=false;
}

function RndChanges(){//random number
document.Access_Register.acc1.checked=false;
document.Access_Register.acc.checked=false;


//document.Access_Register.rndNumber.readonly="true";

}


function chk(){

		  if(document.Access_Register.From_Accno.value=="")
		  {
		    alert("Insufficent Data");
			document.Access_Register.From_Accno.focus();
			return false;
			}
		    else if (document.Access_Register.To_Accno.value=="")
		    {
			document.Access_Register.To_Accno.focus();
			alert("Insufficent Data");
			return false;
		    }

         else{
		return true;
		}
}



</script>



</form>
</body>
</html>
