
<%@page import="javax.print.Doc"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="document" value="${param.doc_type}" />

<%
	String URole = session.getAttribute("UserRights").toString().trim();
	if (URole.equalsIgnoreCase("4") || URole.equalsIgnoreCase("5")
			|| URole.equalsIgnoreCase("6")
			|| URole.equalsIgnoreCase("7")
			|| URole.equalsIgnoreCase("8")) {
		response.sendRedirect("sessiontimeout.jsp");
	}
%>
<%@ include file="/Tree/demoFrameset.jsp"%>
<%@ page language="java" errorPage="/Error/ErrorPage.jsp"
	import="java.io.*" import="java.util.*"%>


<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/button_css.css" />


<jsp:useBean id="bean" scope="request" class="Lib.Auto.Book.bookbean"
	type="Lib.Auto.Book.bookbean">
</jsp:useBean>
<%@ page import="java.util.Calendar"%>
<%@ page import="java.util.Date"%>


<SCRIPT LANGUAGE="JavaScript">
function checkBoxstatus(chkbox,num,index)
 {
   if(chkbox.checked)
    {
	  var chkName=chkbox.name;
	  for(var i=0;i<num;i++)
	  {
	    if(i!=index)
		 {
		   eval("document.Book."+chkName)[i].checked=false;
		 }
	   }
    }
  }
 function showTable(chkbox,str)
{
  if( (chkbox.checked) && (str=='table1') )
  {
    document.all.table1.style.display="inline";
	document.all.table2.style.display="none";
	document.all.table3.style.display="none";
	document.all.table4.style.display="none";
  }
  if( (chkbox.checked) && (str=='table2') )
  {
        document.all.table1.style.display="none";
	document.all.table2.style.display="inline";
	document.all.table3.style.display="none";
	document.all.table4.style.display="none";
   }
  if( (chkbox.checked) && (str=='table3') )
  {
       document.all.table1.style.display="none";
	document.all.table2.style.display="none";
	document.all.table3.style.display="inline";
	document.all.table4.style.display="none";
  }
  if( (chkbox.checked) && (str=='table4') )
  {
    	document.all.table1.style.display="none";
	document.all.table2.style.display="none";
	document.all.table3.style.display="none";
	document.all.table4.style.display="inline";

  }
  }


</script>
<!--
//////////////////////////////////////FORM CONTROLS//////////////////////////////////////////////////////////////////////// -->
<html>
<%
	response.setHeader("Pragma", "No-cache");
	response.setHeader("Cache-Control", "no-cache");
	response.setDateHeader("Expires", 0);
%>
<head>
<meta charset="ISO-8859-1">
<title>BOOK MASTER</title>
<script language="javascript" src="/AutoLib/popcalendar.js"></script>
<link rel="stylesheet" type="text/css" href="/AutoLib/style.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/jquery-ui.css" />
<%-- <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/jquery-ui-flick.css"/> --%>
<%-- <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/jquery-ui-hive.css"/> --%>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jquery-2.2.4.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jquery-ui.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/searchBookAll2.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/datepicker.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/datepicker2.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/datepicker3.js"></script>

<title>AutoLib Software Systems</title>
</head>

<body background="/AutoLib/soft.jpg" onkeydown="myFunction(event)">
	<!--<body  background="/AutoLib/soft.jpg" onload="load()">-->

	<form name="Book" method="post" action=./BookServlet>

		<br>
		<c:choose>
			<c:when test="${document eq 'BOOK'}">
				<h2>Book&nbsp;Master</h2>
			</c:when>
			<c:when test="${document eq 'BOOK BANK'}">
				<h2>Book Bank&nbsp;Master</h2>
			</c:when>

			<c:when test="${document eq 'NON BOOK'}">
				<h2>Non Book&nbsp;Master</h2>
			</c:when>

			<c:when test="${document eq 'REPORT'}">
				<h2>Report&nbsp;Master</h2>
			</c:when>

			<c:when test="${document eq 'THESIS'}">
				<h2>Thesis&nbsp;Master</h2>
			</c:when>

			<c:when test="${document eq 'STANDARD'}">
				<h2>Standard&nbsp;Master</h2>
			</c:when>

			<c:when test="${document eq 'PROCEEDING'}">
				<h2>Proceeding&nbsp;Master</h2>
			</c:when>

			<c:when test="${document eq 'BACK VOLUME'}">
				<h2>BackVolume&nbsp;Master</h2>
			</c:when>

			<c:otherwise>
				<h2>OTHER&nbsp;ENTRY</h2>
			</c:otherwise>
		</c:choose>


		<p align="center">
			<input type="BUTTON" name="BOOK" id="BOOK" Class="submitButton"
				value="BOOK" onclick="reply_click(this.id)"> <input
				type="BUTTON" name="BOOK BANK" id="BOOK BANK" Class="submitButton"
				value="BOOK BANK" onclick="reply_click(this.id)"> <input
				type="BUTTON" name="NON BOOK" id="NON BOOK" Class="submitButton"
				value="NON BOOK" onclick="reply_click(this.id)"> <input
				type="BUTTON" name="REPORT" id="REPORT" Class="submitButton"
				value="REPORT" onclick="reply_click(this.id)"> <input
				type="BUTTON" name="THESIS" id="THESIS" Class="submitButton"
				value="THESIS" onclick="reply_click(this.id)"> <input
				type="BUTTON" name="STANDARD" id="STANDARD" Class="submitButton"
				value="STANDARD" onclick="reply_click(this.id)"> <input
				type="BUTTON" name="PROCEEDING" id="PROCEEDING" Class="submitButton"
				value="PROCEEDING" onclick="reply_click(this.id)"> <input
				type="BUTTON" name="BACK VOLUME" id="BACK VOLUME"
				Class="submitButton" value="BACK VOLUME"
				onclick="reply_click(this.id)">
		</p>



		<script language="javascript" type="text/javascript">

function reply_click(clicked_id)
{
	//alert(clicked_id);
    if(document.Book.title.value=="")
    	{
    	document.Book.method="get";
		document.Book.flag.value="New";
		document.Book.doc_type.value=clicked_id;
		document.Book.submit();
    	}
    
}

function ReportType(clicked_id){

//alert(clicked_id);
document.Book.method="get";
		document.Book.flag.value="New";
		document.Book.doc_type.value="REPORT";
		document.Book.reportType.value=clicked_id;
		document.Book.submit();

}

</SCRIPT>

		<table align="center" class="contentTable" width="75%">
			<tr>
				<td>
					<table align="center" width="100%">
						<!-- <tr><td> &nbsp; </td></tr> -->
						<tr>
							<td>


								<table align="center">

									<tr>


										<c:if
											test="${(document == 'BOOK') || (document == 'BOOK BANK')}">


											<td align="center" onmouseover="this.style.color='red'"
												onmouseout="this.style.color='blue'"><font
												face="Times New Roman" size="3"><input type="radio"
													name="checkbox1"
													onclick="checkBoxstatus(this,4,0);showTable(this,'table1');"
													checked>Main Entry</font></td>
											<td align="center" onmouseover="this.style.color='red'"
												onmouseout="this.style.color='blue'"><font
												face="Times New Roman"><input type="radio"
													name="checkbox1"
													onclick="checkBoxstatus(this,4,1);showTable(this,'table2');"><font
													face="Times New Roman" size="3">Additional
														Information</font></td>


											<td align="center" onmouseover="this.style.color='red'"
												onmouseout="this.style.color='blue'"><font
												face="Times New Roman" size="3"><input type="radio"
													name="checkbox1"
													onclick="checkBoxstatus(this,4,2);showTable(this,'table3');"><font
													face="Times New Roman" size="3">Keywords/Notes</font></td>
											<td align="center" onmouseover="this.style.color='red'"
												onmouseout="this.style.color='blue'"><font
												face="Times New Roman"><input type="radio"
													name="checkbox1"
													onclick="checkBoxstatus(this,4,3);showTable(this,'table4');"
													style="display: none"> <font face="Times New Roman"
													size="3"></font></td>

										</c:if>
										<c:if
											test="${(document == 'NON BOOK') || (document == 'REPORT')|| (document == 'THESIS')|| (document == 'STANDARD')|| (document == 'PROCEEDING')}">
											<td align="center" onmouseover="this.style.color='red'"
												onmouseout="this.style.color='blue'"><font
												face="Times New Roman" size="3"><input type="radio"
													name="checkbox1"
													onclick="checkBoxstatus(this,4,0);showTable(this,'table1');"
													checked>Main Entry</font></td>
											<td align="center" onmouseover="this.style.color='red'"
												onmouseout="this.style.color='blue'"><font
												face="Times New Roman"><input type="radio"
													name="checkbox1"
													onclick="checkBoxstatus(this,4,1);showTable(this,'table2');"><font
													face="Times New Roman" size="3">Additional
														Information</font></td>


											<td align="center" onmouseover="this.style.color='red'"
												onmouseout="this.style.color='blue'"><font
												face="Times New Roman" size="3"><input type="radio"
													name="checkbox1"
													onclick="checkBoxstatus(this,4,2);showTable(this,'table3');"
													style="display: none"><font face="Times New Roman"
													size="3"></font></td>
											<td align="center" onmouseover="this.style.color='red'"
												onmouseout="this.style.color='blue'"><font
												face="Times New Roman"><input type="radio"
													name="checkbox1"
													onclick="checkBoxstatus(this,4,3);showTable(this,'table4');"
													style="display: none"><font face="Times New Roman"
													size="3"></font></td>

										</c:if>


									</tr>
								</table>

								<p align="center">
									<font color="#800000" face="Times New Roman"> <input
										type=button name=New Class="submitButton" value=New-F6
										onclick=NewRec()> <input type=button name=Save
										Class="submitButton" value=Save-F9 onclick=SaveRec()>

										<c:choose>
											<c:when test="${UserRights eq 1}">
												<input type=button name=Delete Class="submitButton"
													Value=Delete-F7 onclick=DelRec()>
											</c:when>
										</c:choose> <input type=submit name=search Class="submitButton"
										Value=Search onclick=SearchRec()> <input type=button
										name=Clear Class="submitButton" Value=Clear-F3
										onclick=ClrRec()></font>

									<!-- <tr><td> &nbsp; </td></tr> -->
								</p>
					</table>
		</table>

		<c:forEach var="instanceVar" items="${instanceList}">
			<jsp:include page="instance.jsp">
				<jsp:param name="myVar" value="${instanceVar}" />
			</jsp:include>
		</c:forEach>
		<br>

		<table align="center" width="75%">
			<tr>
				<td>

					<table align="center" class="contentTable" width="100%"
						cellpadding="5" cellspacing="0">
						<tr>
							<td>

								<table align="center" width="100%" cellpadding="0"
									cellspacing="0">

									<tr>
										<td>&nbsp;</td>
									</tr>
									<tr>
										<td><c:choose>

												<c:when test="${document eq 'BOOK'}">
													<jsp:include page="/Book/Book.jsp" />
												</c:when>



												<c:when test="${document eq 'BOOK BANK'}">
													<jsp:include page="/Book/BookBank.jsp" />
												</c:when>

												<c:when test="${document eq 'THESIS'}">
													<jsp:include page="/Book/Thesis.jsp" />
												</c:when>

												<c:when test="${document eq 'NON BOOK'}">
													<jsp:include page="/Book/NonBook.jsp" />
												</c:when>


												<c:when test="${document eq 'PROCEEDING'}">
													<jsp:include page="/Book/Proceeding.jsp" />
												</c:when>


												<c:when test="${document eq 'REPORT'}">
													<jsp:include page="/Book/Report.jsp" />
												</c:when>


												<c:when test="${document eq 'STANDARD'}">
													<jsp:include page="/Book/Standard.jsp" />
												</c:when>



												<c:when test="${document eq 'BACK VOLUME'}">
													<jsp:include page="/Book/BackVolume.jsp" />
												</c:when>
											</c:choose></td>
									</tr>
								</table>
							</td>
						</tr>



					</table>
				</td>
			</tr>



		</table>


	</form>
</body>
</html>



<script language=javascript>
var test="0";

function split(val) 
{
    return val.split(/;\s*/);
}
function extractLast(term) 
{
    return split(term).pop();
}
function splitVolume(val)
{
	return val.split(/,\s*/);
}
function extractLastVolume(term)
{
	return splitVolume(term).pop();
}


$(document).ready(function() {
$(function showAuthor(data) {	  
	  $( "#searchAuthor" ).autocomplete({
  	width: 500,
      max: 20,
      delay: 100,
      minLength: 1,
      autoFocus: true,
      cacheLength: 1,
      scroll: true,
      highlight: false,
          	
  	source: function (request, response) {
          $.ajax({
              url: "/AutoLib/Book/BookServlet?flag="+data,
              type: "GET",
              async : false,
              dataType: "json",
              data : {
              	author : extractLast(request.term)
				 },
              success: function (data) {
					
					//response(data);
					response ($.map(data, function (item)
          {
          
                          // NOTE: BRACKET START IN THE SAME LINE AS RETURN IN 
                          //       THE FOLLOWING LINE
          return {
              id: item.code, value: item.name };
          }));
              	
			     },   
				  
          }); 
      },
      search: function () 
	    {
	        // custom minLength
	        var term = extractLast(this.value);
	        if (term.length < 1) 
	        {
	            return false;
	        }
	    },
	   
	    select: function (event, ui) 
	    {
	    	var terms = split(this.value);
	        // remove the current input
	        terms.pop();
	        // add the selected item
	        terms.push(ui.item.value);
	        // add placeholder to get the comma-and-space at the end
	        terms.push("");
	        this.value = terms.join(";");
	        return false;
	    }
    });	  
	  
});

$(function showaddfield3(data) {
	
    $( "#addfield3" ).autocomplete({
    	width: 100,
        max: 20,
        delay: 100,
        minLength: 1,
        autoFocus: true,
        cacheLength: 1,
        scroll: false,
        highlight: true,
            	
    	source: function (request, response) {
			
			var journal = document.getElementById('searchTitle').value;
       		
            $.ajax({
                url: "/AutoLib/Book/BookServlet",
                type: "GET",
                async : true,
                dataType: "json",
                data : {
                	addfield3 : extractLastVolume(request.term),
                	jnl : journal,
                	flag : ""
				 },
                success: function (data) {
					
					//response(data);
					response ($.map(data, function (item)
            {
            console.log (item.addfield3);
                            // NOTE: BRACKET START IN THE SAME LINE AS RETURN IN 
                            //       THE FOLLOWING LINE
            return {
            	value: item.addfield3,id: item.title ,volume: item.volumeNo,month: item.script,year: item.yop };
            }));
                	
			     },   
			}); 
        },
select: function (event, ui) {
	//alert(ui.item ? ("You picked '" + ui.item.value + "' with an ID of " + ui.item.id) : "Nothing selected, input was " + this.value);
//		$("#searchTitle").val(ui.item.id);
var terms = splitVolume(this.value);
         terms.pop();
         terms.push(ui.item.value);
         this.value = terms.join(",");
         var monthvalue = document.getElementById('searchMonth').value;
         var volNo = document.getElementById('searchVolume').value;
         var issueyear = document.getElementById('searchYear').value;
         if(monthvalue == "")
       	 {
        	 $("#searchMonth").val(ui.item.month); 
       	 }
         else
        {
        	 monthvalue = monthvalue.split(",");
        	var i = 0,status=0;
        	var comma = ","
        	for(i=0;i<monthvalue.length;i++)
       		{
       			if(monthvalue[i] == ui.item.month)
       			{
       				status=1;
       			}
       		}
        	if(status==0)
        		$("#searchMonth").val(monthvalue+comma+ui.item.month);
        }
         
 //for volume split    
        if(volNo == "")
       	 {
        	 $("#searchVolume").val(ui.item.volume); 
       	 }
         else
        {
        	 volNo = volNo.split(",");
        	var i = 0,status=0;
        	var comma = ","
        	for(i=0;i<volNo.length;i++)
       		{
       			if(volNo[i] == ui.item.volume)
       			{
       				status=1;
       			}
       		}
        	if(status==0)
        		$("#searchVolume").val(volNo+comma+ui.item.volume);
        }
         //year split
          if(issueyear == "")
       	 {
        	 $("#searchYear").val(ui.item.year); 
       	 }
         else
        {
         issueyear = issueyear.split(",");
        	var i = 0,status=0;
        	var comma = ","
        	for(i=0;i<issueyear.length;i++)
       		{
       			if(issueyear[i] == ui.item.year)
       			{
       				status=1;
       			}
       		}
        	if(status==0)
        		$("#searchYear").val(issueyear+comma+ui.item.year);
        }
    
         
         

         
         return false;
     },
  
     search: function () 
	    {
	        // custom minLength
	        var term = extractLastVolume(this.value);
	        if (term.length < 1) 
	        {
	            return false;
	        }
	    },
	   

     
     
      });
  });



});

function load(){
	document.Book.accessNo.focus();

		 }
function home()
{
location.href="/AutoLib/Home.jsp";
}

function Logout()
{
location.href="/AutoLib/index.html";
}

function NewRec(){
document.Book.method="get";
document.Book.flag.value="New";
document.Book.doc_type.value=document.Book.doc.value;
document.Book.submit();
}
function leaveChange()
{
if(document.Book.title.value=="")
{
document.Book.method="get";
document.Book.flag.value="New";
document.Book.doc_type.value=document.getElementById("alldoctype").value;
document.Book.submit();
}

}

function ClrRec(){
document.Book.method="post";
document.Book.flag.value="New";
document.Book.doc_type.value=document.Book.doc.value;
document.Book.submit();
}
function Auth_Select(){
//alert("Select Author from Find option");
}
function find_amount(){

document.Book.bprice.value=document.Book.currency.value * document.Book.bcost.value
//document.Book.acceptPrice.value=document.Book.bprice.value
document.Book.acceptPrice.value=parseFloat(document.Book.currency.value * document.Book.addfield4.value)+parseFloat(document.Book.bprice.value-((document.Book.discount.value/100)* document.Book.bprice.value))


}


function accpt_amt(){

if((isNaN(document.Book.bprice.value))||(document.Book.bprice.value == ' ')) {
document.Book.bprice.select();
document.Book.bprice.value="";
}

//document.Book.acceptPrice.value=document.Book.bprice.value
document.Book.acceptPrice.value=document.Book.bprice.value-((document.Book.discount.value/100)* document.Book.bprice.value)


}

function nonbookkey(e)
{
	var code = (e.keyCode ? e.keyCode : e.which);
	if(code == 13) 
		{
			test="13";
			
		}
}
function net_amt(){

if((isNaN(document.Book.discount.value))||(document.Book.discount.value == ' ')) {
document.Book.discount.select();
document.Book.discount.value="";
}

document.Book.acceptPrice.value=parseFloat(document.Book.currency.value * document.Book.addfield4.value)+parseFloat(document.Book.bprice.value-((document.Book.discount.value/100)* document.Book.bprice.value))
}

function SearchRec(){

document.Book.method="post";
if (test=="13") {
	document.Book.flag.value="searchnb";
	document.Book.submit();
}
else if (document.Book.accessNo.value=="") {
	
				alert("Insufficent Data");
				document.Book.flag.value="New";
				document.Book.doc_type.value=document.Book.doc.value;
				//document.Book.submit();
				
				}
			else{
		         document.Book.flag.value="search";
			     document.Book.submit();
				
				 }
}

function searchchk(){
alert("selvam");
var flag_s;
var i;
var sp=document.Book.accessNo.value;
if(sp=="")
{
document.Book.accessNo.focus();
document.Book.flag.value="none";
document.Book.accessNo.value="";
return false;
}
  else
	{
	   for(i=0;i<document.Book.accessNo.value.length;i++)
 	      {
 	           if(document.Book.accessNo.value.charAt(i)==" ")
 	                              {flag_s=0; }
 	                                      else{flag_s=1;}
	                                                         }

		                   if (flag_s==0)
		                    {
		                   	document.Book.accessNo.focus();
		                   	document.Book.accessNo.value="";
			                return false;
		                      }
		                   else if (document.Book.accessNo.value==""){
		                 	document.Book.accessNo.focus();
			                return false;
		                      }
						  else{
		                          return true;
		}
     }
 }


	function DelRec(){
	document.Book.method="post";
	if(chk()){
	document.Book.flag.value="checkDatabase";
	document.Book
             msg=confirm("Are You Sure To Delete");
	if(msg){
	    document.Book.flag.value="delete";
	    document.Book.doc_type.value=document.Book.doc.value;//SHEK
	    document.Book.submit();
	    }
	}
	else
	{
	alert("Insufficent Data");
	document.Book.method="post";
	document.Book.flag.value="New";
	document.Book.doc_type.value=document.Book.doc.value;
	//document.Book.submit();
	}
	}

function numValidate() {
if((isNaN(document.Book.copies.value))||(document.Book.copies.value == ' ')) {
document.Book.copies.select();
document.Book.copies.value="";
return false;
   }
}
function Year_val() {
if((isNaN(document.Book.yop.value))||(document.Book.yop.value == ' ')) {
document.Book.yop.select();
document.Book.yop.value="";
return false;
   }
}
function page_val() {
if((isNaN(document.Book.size.value))||(document.Book.size.value == ' ')) {
document.Book.size.select();
document.Book.size.value="";
return false;
   }
}

function checkDivision()
{

if(document.Book.branchName.value=='NIL')
{
 alert("Choose right division name");
 return false;         
}else if(document.Book.deptName.value=='NIL')
{
 alert("Choose right department name");
 return false;         
}else if(document.Book.subName.value=='NIL')
{
 alert("Choose right subject name");
 return false;         
}else if(document.Book.pubName.value=='NIL')
{
 alert("Choose right publisher name");
 return false;         
}else if(document.Book.supName.value=='NIL')
{
 alert("Choose right supplier name");
 return false;         
}else if(document.Book.budName.value=='NIL')
{
 alert("Choose right budget head name");
 return false;         
}else {
  return true;         
}

}

function SaveRec(){
document.Book.method="get";

 if(chk_ac()){
		if(chk()){
		
		
		
		if(confirm("Are You Sure To Save?"))
                   {
				    document.Book.flag.value="save";
				    document.Book.doc_type.value=document.Book.doc.value;
                    document.Book.submit();
                    }
                    
                   
		  }
		  else
	            {
	             alert("Insufficient Data");
				document.Book.flag.value="New";
				document.Book.doc_type.value=document.Book.doc.value;
				//document.Book.submit();
	             }
		}
	else
	{
				alert("Insufficent Data");
				document.Book.flag.value="New";
				document.Book.doc_type.value=document.Book.doc.value;
				//document.Book.submit();
	}

}
function chk_ac(){
var flag_as;
var a;
var ac=document.Book.accessNo.value;
if(ac=="")
{
document.Book.accessNo.focus();
document.Book.flag.value="none";
document.Book.accessNo.value="";
return false;
}
  else
	{
	   for(a=0;a<document.Book.accessNo.value.length;a++)
 	      {
 	           if(document.Book.accessNo.value.charAt(a)==" ")
 	                 {flag_as=0; }
 	                 else{flag_as=1;}
	                 }
		                  if (flag_as==0)
		                    {
		                   	document.Book.accessNo.focus();
		                   	document.Book.accessNo.value="";
			                return false;
		                      }
		                      
		                      
		                   else if (document.Book.title.value==""){
		                   
		                   
		                 	document.Book.title.focus();
			                return false;
		                      }
                 else{
		             return true;
		}
     }
 }





function chk(){
var flag_s;
var i;
var sp=document.Book.title.value;

if(sp=="")
{
document.Book.title.focus();
document.Book.flag.value="none";
document.Book.title.value="";
return false;
}
  else
	{
	   for(i=0;i<document.Book.title.value.length;i++)
 	      {
 	           if(document.Book.title.value.charAt(i)==" ")
 	                              {flag_s=0; }
 	                                      else{flag_s=1;}
	      }

		                   if (flag_s==0)
		                    {		                    
		                   	document.Book.title.focus();
		                   	document.Book.title.value="";
			                return false;
		                      }
		                   else if (document.Book.accessNo.value==""){
		                 	document.Book.accessNo.focus();
			                return false;
		                      }
						  else{
		                          return true;
		}
     }
 }
 
 /**
function chk_ac(){
var flag_as;
var a;
var ac=document.Book.accessNo.value;
if(ac=="")
{
document.Book.accessNo.focus();
document.Book.flag.value="none";
document.Book.accessNo.value="";
return false;
}
  else
	{
	   for(a=0;a<document.Book.accessNo.value.length;a++)
 	      {
 	           if(document.Book.accessNo.value.charAt(a)==" ")
 	                 {flag_as=0; }
 	                 else{flag_as=1;}
	                 }
		                  if (flag_as==0)
		                    {
		                   	document.Book.accessNo.focus();
		                   	document.Book.accessNo.value="";
			                return false;
		                      }
		                   else if (document.Book.title.value==""){
		                 	document.Book.title.focus();
			                return false;
		                      }
                 else{
		             return true;
		}
     }
 } */

 function FindValue(val,val1){
	 				
winpopup=window.open("search_nmvc.jsp?flag="+val+"&docType="+val1 ,"popup","height=400,width=600,left=100,top=100,scrollbars=yes,toolbar=no,status=yes,menubar=no");
}
 
 function FindValueaccno(val,val1){
		
	 winpopup=window.open("search_accno.jsp?flag="+val+"&docType="+val1 ,"popup","height=400,width=600,left=100,top=100,scrollbars=yes,toolbar=no,status=yes,menubar=no");
	 }

function Book_code() {
if((isNaN(document.Book.accessNo.value))||(document.Book.accessNo.value == ' ')) {
document.Book.accessNo.select();
document.Book.accessNo.value="";

  }
}

function fine_currency() {

}

function BCost_val() {
if((isNaN(document.Book.bcost.value))||(document.Book.bcost.value == ' ')) {
document.Book.bcost.select();
document.Book.bcost.value="0";

  }else
  {
    document.Book.bprice.value=document.Book.currency.value * document.Book.bcost.value
    document.Book.acceptPrice.value=parseFloat(document.Book.currency.value * document.Book.addfield4.value)+parseFloat(document.Book.bprice.value-((document.Book.discount.value/100)* document.Book.bprice.value))
  }
}

function shipment_amt(){
	
	if((isNaN(document.Book.addfield4.value))||(document.Book.addfield4.value == ' ')) {
		document.Book.addfield4.select();
		document.Book.addfield4.value="0";
	}else{
		
		//document.Book.bprice.value=document.Book.currency.value * document.Book.bcost.value
	   
		document.Book.acceptPrice.value=parseFloat(document.Book.currency.value * document.Book.addfield4.value)+parseFloat(document.Book.bprice.value-((document.Book.discount.value/100)* document.Book.bprice.value))
		
	}
	
	
}



function chkAP_amt()
{
if((isNaN(document.Book.acceptPrice.value))||(document.Book.acceptPrice.value == ' ')) {
document.Book.acceptPrice.select();
document.Book.acceptPrice.value="";

  }
}

function isWhitespace(str)
	{
		var re = /[\S]/g
		if (re.test(str)) return false;
		return true;
	}

function valid_space() { 
     if (isWhitespace(document.Book.accessNo.value)) {
        document.Book.accessNo.select();
        document.Book.accessNo.value="";      
    }
}

function validTitle_space() { 
     if (isWhitespace(document.Book.title.value)) {
        document.Book.title.select();
        document.Book.title.value="";      
    }
}

function myFunction(event) 
{
    var x = event.keyCode;
    
//     if(x=="13")
//     {
//     	event.preventDefault();
//     	SearchRec();	
//     }
    if(x=="114")
    	{
    	event.preventDefault();
    	ClrRec();
    	}
    else if(x=="117")
    	{
    	event.preventDefault();
    	NewRec();
    	}
    else if(x=="118")
	{
    	event.preventDefault();
    	DelRec();
	}
     else if(x=="120")
 	{
     	event.preventDefault();
     	SaveRec();
 	}
    else if(x=="9")
	{
    	return event();
    	//event.preventDefault();
    	//document.counter.accno.focus();
	}
    
}	
</script>

<%
	String valid = request.getParameter("check");

	if (valid != null) {
		if (request.getParameter("check") != null) {
			if (valid.equals("newBook")) {

				String valid1 = request.getParameter("doc_type");

				if (valid1.equals("REPORT")
						&& request.getParameter("reportType") != null) {
					String reportType = request
							.getParameter("reportType");
					//		out.println("Report Type :    :::::::::::"+reportType);

					if (reportType.equalsIgnoreCase("PS")) {//shek
%>
<script language="JavaScript">
	document.getElementById("PS").checked = true;
	</script>
<%
	} else if (reportType.equalsIgnoreCase("PR")) {
%>
<script language="JavaScript">
	document.getElementById("PR").checked = true;
	</script>
<%
	} else {
%>
<script language="JavaScript">
			 document.getElementById("PS").checked = true;
			 </script>

<%
	}

				}
				if (valid1 != null) {
%>
<script language="JavaScript">
     		document.Book.accessNo.value="<%=bean.getAccessNo()%>";
			document.Book.doc.value="<%=valid1%>";
			document.Book.doc_type.value="<%=valid1%>";
			document.Book.title.focus();
		</script>

<%
	}
			}
			if (valid.equals("CopyBook")) {
				String n = (String) request.getAttribute("no_of_copy");
%>
<script language="JavaScript">
         document.Book.purchaseType.value="<%=bean.getPurchaseType()%>";
        document.Book.contents.value="<%=bean.getContents()%>";
		document.Book.keywords.value="<%=bean.getKeywords()%>";
		document.Book.notes.value="<%=bean.getNotes()%>";
		document.Book.summary.value="<%=bean.getSummary()%>";
		document.Book.bibliography.value="<%=bean.getBibliography()%>";
		document.Book.supName.value="<%=bean.getSupName()%>";
  		document.Book.Sup.value="<%=bean.getSupCode()%>";
		document.Book.pubName.value="<%=bean.getPubName()%>";
  		document.Book.Pub.value="<%=bean.getPubCode()%>";
		document.Book.invNo.value="<%=bean.getInvNo()%>";
		document.Book.invoiceDate.value="<%=bean.getInvoiceDate()%>";
		document.Book.bcost.value="<%=bean.getBcost()%>";
		document.Book.currency.value="<%=bean.getCurrency()%>";
		document.Book.acceptPrice.value="<%=bean.getAcceptPrice()%>";
		document.Book.discount.value="<%=bean.getDiscount()%>";
		document.Book.accessNo.value="<%=bean.getAccessNo()%>";
		document.Book.callNo.value="<%=bean.getCallNo()%>";
		document.Book.rcDate.value="<%=bean.getRcDate()%>";
		document.Book.author.value="<%=bean.getAuthor()%>";
		document.Book.title.value="<%=bean.getTitle()%>";
		document.Book.addfield1.value="<%=bean.getAddfield1()%>";
		document.Book.addfield2.value="<%=bean.getAddfield2()%>";
		document.Book.addfield3.value="<%=bean.getAddfield3()%>";
		document.Book.addfield4.value="<%=bean.getAddfield4()%>";
		document.Book.volumeNo.value="<%=bean.getVolumeNo()%>";
		document.Book.partNo.value="<%=bean.getPartNo()%>";
		document.Book.volumeTitle.value="<%=bean.getVolumeTitle()%>";
		document.Book.volumeRes.value="<%=bean.getVolumeRes()%>";
		document.Book.meeting.value="<%=bean.getMeeting()%>";
		document.Book.sponsor.value="<%=bean.getSponsor()%>";
		document.Book.venue.value="<%=bean.getVenue()%>";
		document.Book.otherTitle.value="<%=bean.getOtherTitle()%>";
		document.Book.role.value="<%=bean.getRole()%>";
		document.Book.stateRes.value="<%=bean.getStateRes()%>";
		document.Book.edition.value="<%=bean.getEdition()%>";
		document.Book.language.value="<%=bean.getLanguage()%>";
		document.Book.corAut.value="<%=bean.getCorAut()%>";
		document.Book.corAdd.value="<%=bean.getCorAdd()%>";
		document.Book.serAut.value="<%=bean.getSerAut()%>";
		document.Book.serName.value="<%=bean.getSerName()%>";
		document.Book.serTitle.value="<%=bean.getSerTitle()%>";
		document.Book.issn.value="<%=bean.getIssn()%>";
		document.Book.location.value="<%=bean.getLocation()%>";
		document.Book.avail.value="<%=bean.getAvail()%>";
		//document.Book.publisherName.value="<%=bean.getPubName()%>";
		document.Book.place.value="<%=bean.getPlace()%>";
		document.Book.yop.value="<%=bean.getYop()%>";
		document.Book.pages.value="<%=bean.getPages()%>";
		document.Book.size.value="<%=bean.getSize()%>";
		document.Book.illustration.value="<%=bean.getIllustration()%>";
		document.Book.isbn.value="<%=bean.getIsbn()%>";
		document.Book.bprice.value="<%=bean.getBprice()%>";
		document.Book.copies.value="<%=bean.getCopies()%>";
		document.Book.script.value="<%=bean.getScript()%>";
		document.Book.subName.value="<%=bean.getSubName()%>";
  		document.Book.Sub.value="<%=bean.getSubCode()%>";
		document.Book.deptName.value="<%=bean.getDeptName()%>";
  		document.Book.Dept.value="<%=bean.getDeptCode()%>";
		document.Book.branchName.value="<%=bean.getBranchName()%>";
  		document.Book.Branch.value="<%=bean.getBranchCode()%>";
		document.Book.doc.value="<%=bean.getDoc()%>";
		document.Book.physical.value="<%=bean.getPhysical()%>";
		document.Book.binding.value="<%=bean.getBinding()%>";
		document.Book.budName.value="<%=bean.getBudName()%>";
  		document.Book.Bud.value="<%=bean.getBudgetCode()%>";
  		document.Book.otherDate.value="<%=bean.getOtherDate()%>";


  var msg=confirm("Do you want to create <%=n%> copy?");
  if(msg)
  {
   document.Book.flag.value="MoreCopy";
   document.Book.doc_type.value=document.Book.doc.value;//SHEK
   document.Book.submit();
  }
  else
  {

    }
  </script>
<%
	}
			if (valid.equals("MoreBookSave")) {
				String n1 = (String) request.getAttribute("no_of_copy");
%>
<script language="JavaScript">
  alert("<%=n1%> copy created!..");
   document.Book.flag.value="New";
   //document.Book.doc_type.value=document.Book.doc.value;
   document.Book.doc_type.value="${document}";
   document.Book.submit();//SHEK
   
   
   </script>
<%
	}
			if (valid.equals("FailureBook")) {
%>
<script language="JavaScript">
 
 alert("Record Not Found!..");
document.Book.flag.value="New";

//document.Book.doc_type.value=document.Book.doc.value;
document.Book.doc_type.value="${document}";//SHEK
document.Book.submit();
</script>
<%
	}

			if (valid.equals("ReferBook")) {
%>
<script language="JavaScript">
 alert("You can't delete since this Book has been referred in other masters");

document.Book.method="get";
document.Book.flag.value="New";
document.Book.doc_type.value=document.Book.doc.value;
//document.Book.submit();
</script>
<%
	}

			if (valid.equals("DeleteBook")) {
%>
<script language="JavaScript">
 alert("Record Deleted Successfully!");

document.Book.method="get";
document.Book.flag.value="New";

//document.Book.doc_type.value=document.Book.doc.value;
document.Book.doc_type.value="${document}";


document.Book.submit();
</script>
<%
	}
			if (valid.equals("NotRightUser")) {
%>
<script language="JavaScript">
	  	  alert("Access denied !");			
	  	 </script>
<%
	}

			if (valid.equals("deleteCheck")) {
%>
<script language="JavaScript">
	  document.Book.purchaseType.value="<%=bean.getPurchaseType()%>";
	  document.Book.contents.value="<%=bean.getContents()%>";
	  document.Book.keywords.value="<%=bean.getKeywords()%>";
		document.Book.notes.value="<%=bean.getNotes()%>";
		document.Book.summary.value="<%=bean.getSummary()%>";
		document.Book.bibliography.value="<%=bean.getBibliography()%>";
		document.Book.supName.value="<%=bean.getSupName()%>";
  		document.Book.Sup.value="<%=bean.getSupCode()%>";
		document.Book.pubName.value="<%=bean.getPubName()%>";
  		document.Book.Pub.value="<%=bean.getPubCode()%>";
		document.Book.invNo.value="<%=bean.getInvNo()%>";
		document.Book.invoiceDate.value="<%=bean.getInvoiceDate()%>";
		document.Book.bcost.value="<%=bean.getBcost()%>";
		document.Book.currency.value="<%=bean.getCurrency()%>";
		document.Book.acceptPrice.value="<%=bean.getAcceptPrice()%>";
		document.Book.discount.value="<%=bean.getDiscount()%>";
		document.Book.accessNo.value="<%=bean.getAccessNo()%>";
		document.Book.callNo.value="<%=bean.getCallNo()%>";
		document.Book.rcDate.value="<%=bean.getRcDate()%>";
		document.Book.author.value="<%=bean.getAuthor()%>";
		document.Book.title.value="<%=bean.getTitle()%>";
		document.Book.addfield1.value="<%=bean.getAddfield1()%>";
		document.Book.addfield2.value="<%=bean.getAddfield2()%>";
		document.Book.addfield3.value="<%=bean.getAddfield3()%>";
		document.Book.addfield4.value="<%=bean.getAddfield4()%>";
		document.Book.volumeNo.value="<%=bean.getVolumeNo()%>";
		document.Book.partNo.value="<%=bean.getPartNo()%>";
		document.Book.volumeTitle.value="<%=bean.getVolumeTitle()%>";
		document.Book.volumeRes.value="<%=bean.getVolumeRes()%>";
		document.Book.meeting.value="<%=bean.getMeeting()%>";
		document.Book.sponsor.value="<%=bean.getSponsor()%>";
		document.Book.venue.value="<%=bean.getVenue()%>";
		document.Book.otherTitle.value="<%=bean.getOtherTitle()%>";
		document.Book.role.value="<%=bean.getRole()%>";
		document.Book.stateRes.value="<%=bean.getStateRes()%>";
		document.Book.edition.value="<%=bean.getEdition()%>";
		document.Book.language.value="<%=bean.getLanguage()%>";
		document.Book.corAut.value="<%=bean.getCorAut()%>";
		document.Book.corAdd.value="<%=bean.getCorAdd()%>";
		document.Book.serAut.value="<%=bean.getSerAut()%>";
		document.Book.serName.value="<%=bean.getSerName()%>";
		document.Book.serTitle.value="<%=bean.getSerTitle()%>";
		document.Book.issn.value="<%=bean.getIssn()%>";
		document.Book.location.value="<%=bean.getLocation()%>";
		document.Book.avail.value="<%=bean.getAvail()%>";
		document.Book.place.value="<%=bean.getPlace()%>";
		document.Book.yop.value="<%=bean.getYop()%>";
		document.Book.pages.value="<%=bean.getPages()%>";
		document.Book.size.value="<%=bean.getSize()%>";
		document.Book.illustration.value="<%=bean.getIllustration()%>";
		document.Book.isbn.value="<%=bean.getIsbn()%>";
		document.Book.bprice.value="<%=bean.getBprice()%>";
		document.Book.copies.value="<%=bean.getCopies()%>";
		document.Book.script.value="<%=bean.getScript()%>";
		document.Book.subName.value="<%=bean.getSubName()%>";
  		document.Book.Sub.value="<%=bean.getSubCode()%>";
		document.Book.deptName.value="<%=bean.getDeptName()%>";
  		document.Book.Dept.value="<%=bean.getDeptCode()%>";
		document.Book.branchName.value="<%=bean.getBranchName()%>";
  		document.Book.Branch.value="<%=bean.getBranchCode()%>";
		document.Book.doc.value="<%=bean.getDoc()%>";
		document.Book.physical.value="<%=bean.getPhysical()%>";
		document.Book.binding.value="<%=bean.getBinding()%>";
		document.Book.budName.value="<%=bean.getBudName()%>";
  		document.Book.Bud.value="<%=bean.getBudgetCode()%>";
  		document.Book.otherDate.value="<%=bean.getOtherDate()%>";
	  
	         msg=confirm("Are You Sure To Delete?");
			if(msg){
			
			 document.Book.flag.value="Confirmdete";
			 
		   	document.member.submit();

			}
	 </script>
<%
	}
			if (valid.equals("updateBook")) {
%>
<script language="JavaScript">
	  document.Book.purchaseType.value="<%=bean.getPurchaseType()%>";
         document.Book.contents.value="<%=bean.getContents()%>";
	     document.Book.keywords.value="<%=bean.getKeywords()%>";
		document.Book.notes.value="<%=bean.getNotes()%>";
		document.Book.summary.value="<%=bean.getSummary()%>";
		document.Book.bibliography.value="<%=bean.getBibliography()%>";
		document.Book.supName.value="<%=bean.getSupName()%>";
  		document.Book.Sup.value="<%=bean.getSupCode()%>";
		document.Book.pubName.value="<%=bean.getPubName()%>";
  		document.Book.Pub.value="<%=bean.getPubCode()%>";
		document.Book.invNo.value="<%=bean.getInvNo()%>";
		document.Book.invoiceDate.value="<%=bean.getInvoiceDate()%>";
		document.Book.bcost.value="<%=bean.getBcost()%>";
		document.Book.currency.value="<%=bean.getCurrency()%>";
		document.Book.acceptPrice.value="<%=bean.getAcceptPrice()%>";
		document.Book.discount.value="<%=bean.getDiscount()%>";
		document.Book.accessNo.value="<%=bean.getAccessNo()%>";
		document.Book.callNo.value="<%=bean.getCallNo()%>";
		document.Book.rcDate.value="<%=bean.getRcDate()%>";
		document.Book.author.value="<%=bean.getAuthor()%>";
		document.Book.title.value="<%=bean.getTitle()%>";
		document.Book.addfield1.value="<%=bean.getAddfield1()%>";
		document.Book.addfield2.value="<%=bean.getAddfield2()%>";
		document.Book.addfield3.value="<%=bean.getAddfield3()%>";
		document.Book.addfield4.value="<%=bean.getAddfield4()%>";
			 document.Book.type.value="<%=bean.getType()%>";
		
		document.Book.volumeNo.value="<%=bean.getVolumeNo()%>";
		document.Book.partNo.value="<%=bean.getPartNo()%>";
		document.Book.volumeTitle.value="<%=bean.getVolumeTitle()%>";
		document.Book.volumeRes.value="<%=bean.getVolumeRes()%>";
		document.Book.meeting.value="<%=bean.getMeeting()%>";
		document.Book.sponsor.value="<%=bean.getSponsor()%>";
		document.Book.venue.value="<%=bean.getVenue()%>";
		document.Book.otherTitle.value="<%=bean.getOtherTitle()%>";
		document.Book.role.value="<%=bean.getRole()%>";
		document.Book.stateRes.value="<%=bean.getStateRes()%>";
		document.Book.edition.value="<%=bean.getEdition()%>";
		document.Book.language.value="<%=bean.getLanguage()%>";
		document.Book.corAut.value="<%=bean.getCorAut()%>";
		document.Book.corAdd.value="<%=bean.getCorAdd()%>";
		document.Book.serAut.value="<%=bean.getSerAut()%>";
		document.Book.serName.value="<%=bean.getSerName()%>";
		document.Book.serTitle.value="<%=bean.getSerTitle()%>";
		document.Book.issn.value="<%=bean.getIssn()%>";
		document.Book.location.value="<%=bean.getLocation()%>";
		document.Book.avail.value="<%=bean.getAvail()%>";
		document.Book.place.value="<%=bean.getPlace()%>";
		document.Book.yop.value="<%=bean.getYop()%>";
		document.Book.pages.value="<%=bean.getPages()%>";
		document.Book.size.value="<%=bean.getSize()%>";
		document.Book.illustration.value="<%=bean.getIllustration()%>";
		document.Book.isbn.value="<%=bean.getIsbn()%>";
		document.Book.bprice.value="<%=bean.getBprice()%>";
		document.Book.copies.value="<%=bean.getCopies()%>";
		document.Book.script.value="<%=bean.getScript()%>";
		document.Book.subName.value="<%=bean.getSubName()%>";
  		document.Book.Sub.value="<%=bean.getSubCode()%>";
		document.Book.deptName.value="<%=bean.getDeptName()%>";
  		document.Book.Dept.value="<%=bean.getDeptCode()%>";
		document.Book.branchName.value="<%=bean.getBranchName()%>";
  		document.Book.Branch.value="<%=bean.getBranchCode()%>";
		document.Book.doc.value="<%=bean.getDoc()%>";
		document.Book.physical.value="<%=bean.getPhysical()%>";
		document.Book.binding.value="<%=bean.getBinding()%>";
		document.Book.budName.value="<%=bean.getBudName()%>";
  		document.Book.Bud.value="<%=bean.getBudgetCode()%>";
  		document.Book.otherDate.value="<%=bean.getOtherDate()%>";
	  
	         msg=confirm("Document Already Exists,Do you want Update?");
			if(msg){
			
			 document.Book.flag.value="update";
			 document.Book.doc_type.value=document.Book.doc.value;
		   	document.Book.submit();

			}
	 </script>
<%
	}
			if (valid.equals("SingleBookSave")) {
%>
<script language="JavaScript">
 alert("Record Saved Successfully!");
document.Book.method="get";
document.Book.flag.value="New";
//document.Book.doc_type.value=document.Book.doc.value;

document.Book.doc_type.value="${document}";//SHEK
document.Book.submit();
</script>
<%
	}

			if (valid.equals("BookUpdate")) {
%>
<script language="JavaScript">
	 alert("Record Modified Successfully!");
	document.Book.method="get";
	document.Book.flag.value="New";
	
	//document.Book.doc_type.value=document.Book.doc.value;
	document.Book.doc_type.value="${document}";//SHEK
	document.Book.submit();
	</script>
<%
	}
			if (valid.equals("SearchBook")) {

				//if(bean.getAccessNo().substring(0,2).equals("PS")){
				if (bean.getDoc().equalsIgnoreCase("REPORT")
						&& bean.getAccessNo().substring(0, 2)
								.equals("PS")) {
					//if((bean.getAccessNo().substring(0,2).equals("PS")) && (bean.getDoc().equalsIgnoreCase("REPORT"))){
%>
<script language="JavaScript">
				document.getElementById("PS").checked = true;
				</script>
<%
	} else {
%>
<script language="JavaScript">
				document.getElementById("PR").checked = true;
				</script>

<%
	}
%>


<script language="JavaScript">
		document.Book.purchaseType.value="<%=bean.getPurchaseType()%>";
	    document.Book.contents.value="<%=bean.getContents()%>";
		document.Book.keywords.value="<%=bean.getKeywords()%>";
		document.Book.notes.value="<%=bean.getNotes()%>";
		document.Book.summary.value="<%=bean.getSummary()%>";
		document.Book.bibliography.value="<%=bean.getBibliography()%>";
		document.Book.supName.value="<%=bean.getSupName()%>";
  		document.Book.Sup.value="<%=bean.getSupCode()%>";
		document.Book.pubName.value="<%=bean.getPubName()%>";
  		document.Book.Pub.value="<%=bean.getPubCode()%>";
		document.Book.invNo.value="<%=bean.getInvNo()%>";
		document.Book.invoiceDate.value="<%=bean.getInvoiceDate()%>";
		document.Book.bcost.value="<%=bean.getBcost()%>";
		document.Book.currency.value="<%=bean.getCurrency()%>";
		document.Book.acceptPrice.value="<%=bean.getAcceptPrice()%>";
		document.Book.discount.value="<%=bean.getDiscount()%>";
		document.Book.accessNo.value="<%=bean.getAccessNo()%>";
		document.Book.callNo.value="<%=bean.getCallNo()%>";
		document.Book.rcDate.value="<%=bean.getRcDate()%>";
		document.Book.author.value="<%=bean.getAuthor()%>";
		document.Book.title.value="<%=bean.getTitle()%>";
		document.Book.addfield1.value="<%=bean.getAddfield1()%>";
		document.Book.addfield2.value="<%=bean.getAddfield2()%>";
		document.Book.addfield3.value="<%=bean.getAddfield3()%>";
		document.Book.addfield4.value="<%=bean.getAddfield4()%>";
		document.Book.type.value="<%=bean.getType()%>";
		document.Book.volumeNo.value="<%=bean.getVolumeNo()%>";
		document.Book.partNo.value="<%=bean.getPartNo()%>";
		document.Book.volumeTitle.value="<%=bean.getVolumeTitle()%>";
		document.Book.volumeRes.value="<%=bean.getVolumeRes()%>";
		document.Book.meeting.value="<%=bean.getMeeting()%>";
		document.Book.sponsor.value="<%=bean.getSponsor()%>";
		document.Book.venue.value="<%=bean.getVenue()%>";
		document.Book.otherTitle.value="<%=bean.getOtherTitle()%>";
		document.Book.role.value="<%=bean.getRole()%>";
		document.Book.stateRes.value="<%=bean.getStateRes()%>";
		document.Book.edition.value="<%=bean.getEdition()%>";
		document.Book.language.value="<%=bean.getLanguage()%>";
		document.Book.corAut.value="<%=bean.getCorAut()%>";
		document.Book.corAdd.value="<%=bean.getCorAdd()%>";
		document.Book.serAut.value="<%=bean.getSerAut()%>";
		document.Book.serName.value="<%=bean.getSerName()%>";
		document.Book.serTitle.value="<%=bean.getSerTitle()%>";
		document.Book.issn.value="<%=bean.getIssn()%>";
		document.Book.location.value="<%=bean.getLocation()%>";
		document.Book.avail.value="<%=bean.getAvail()%>";
		document.Book.place.value="<%=bean.getPlace()%>";
		document.Book.yop.value="<%=bean.getYop()%>";
		document.Book.pages.value="<%=bean.getPages()%>";
		document.Book.size.value="<%=bean.getSize()%>";
		document.Book.illustration.value="<%=bean.getIllustration()%>";
		document.Book.isbn.value="<%=bean.getIsbn()%>";
		document.Book.bprice.value="<%=bean.getBprice()%>";
		document.Book.copies.value="<%=bean.getCopies()%>";
		document.Book.script.value="<%=bean.getScript()%>";
		document.Book.subName.value="<%=bean.getSubName()%>";
  		document.Book.Sub.value="<%=bean.getSubCode()%>";
		document.Book.deptName.value="<%=bean.getDeptName()%>";
  		document.Book.Dept.value="<%=bean.getDeptCode()%>";
		document.Book.branchName.value="<%=bean.getBranchName()%>";
  		document.Book.Branch.value="<%=bean.getBranchCode()%>";
		document.Book.doc.value="<%=bean.getDoc()%>";
		document.Book.physical.value="<%=bean.getPhysical()%>";
		document.Book.binding.value="<%=bean.getBinding()%>";
		document.Book.budName.value="<%=bean.getBudName()%>";
  		document.Book.Bud.value="<%=bean.getBudgetCode()%>";
  		document.Book.otherDate.value="<%=bean.getOtherDate()%>";
  		document.Book.doc_type.value=document.Book.doc.value;//shek
		</script>
<%
	}
		}
	}
%>

<!--
//////////////////////////////////////JAVA SERVER PAGES SCRIPT ///////////////////////////////////////////////// -->
