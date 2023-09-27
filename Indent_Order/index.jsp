<%
String URole=session.getAttribute("UserRights").toString().trim();
if(URole.equalsIgnoreCase("3") || URole.equalsIgnoreCase("4") || URole.equalsIgnoreCase("6") || URole.equalsIgnoreCase("7"))
{		
	response.sendRedirect("sessiontimeout.jsp");
}	
%>
<%@ include file="/Tree/demoFrameset.jsp"%>
<%@ page language="java" errorPage="/Error/ErrorPage.jsp" import="java.io.*" import="java.util.*" import="Lib.Auto.Currency.CurrencyBean" import="Lib.Auto.JNL_Order.JnlorderBean" session="true" buffer="12kb" import="Common.Security"%>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/button_css.css" />
<jsp:useBean id="viewObject" scope="request" class="Lib.Auto.JNL_Order.JnlorderBean"/>
<%@ page import="java.util.Calendar" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat"%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored ="false" %>


<%
ArrayList sc=new ArrayList();
sc=(ArrayList)request.getAttribute("currency");
%>
				<!--
//////////////////////////////////////////////////////////////////FORM CONTROLS//////////////////////////////////////////////////////////////////////// -->
<html>
<head>
<meta charset="ISO-8859-1">
<title>AutoLib</title>
<!-- <script language="javascript" src="/AutoLib/popcalendar.js"></script> -->
<script language="javascript" src="/AutoLib/datetimepicker_css.js"></script>
<link rel="stylesheet" type="text/css" href="/AutoLib/style.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-3.1.0.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-ui.js"></script>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/jquery-ui.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/datepicker.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/datepicker2.js"></script>

<meta http-equiv="pragma" content="no-cache"/>
</head>
<body background="/AutoLib/soft.jpg" >
<%
	java.util.Date d =new java.util.Date();
	SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	String dateString = sdf.format(d);

%>

<!-- Style Sheet -->

<form method="post" name="ordinv" action=./IndentOrderServlet>
<br><br><br>

<h2 >Indent Order Processing</h2>

  <center>
<table align="center" class="contentTable" width="80%">
<td>
<table align="center" width="95%">
<tr><td> &nbsp; </td></tr>

<c:choose>
<c:when test="${IndentOrdSize gt 0}">  
<c:forEach items="${IndentOrd}" var="std"  begin="0" end="0">
    <tr>
      <td Class="addedit" colspan="3">Order No&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      <input type="text" name="ordno" value="<c:out value="${std.ordno}"/>" onKeydown="Javascript: if (event.keyCode==13) SearchRecord();" size="15" maxlength=15>
      <input type="button" value="Find" Class="submitButton" onclick=FindValue("OrderNo")></td>
      
      <td Class="addedit" colspan="2">&nbsp;Ord. Date
        
	<INPUT name=orddate size=8  onfocus=this.blur(); value="<c:out value="${std.orddate}"/>">
				<SCRIPT language=javascript>
						if (!document.layers) {
						document.write("<input type=button onclick='popUpCalendar(this,ordinv.orddate, \"dd-mm-yyyy\",\"<%=dateString%>\")'value='...' style='font-size:10 px'>")
						}
				</SCRIPT>
		 </td>
      <td Class="addedit" colspan="3">Quote. No&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      <input type="text" name="quoteno" size="15" maxlength=15 value="<c:out value="${std.quoteno}"/>" >
      </td>

    </tr>
    <tr>
    <td Class="addedit" colspan="2">Quote. Date&nbsp;&nbsp;&nbsp;&nbsp;
    
	<INPUT name=quotedate size=8  onfocus=this.blur(); value="<c:out value="${std.quotedate}"/>">
				<SCRIPT language=javascript>
						if (!document.layers) {
						document.write("<input type=button onclick='popUpCalendar(this,ordinv.quotedate, \"dd-mm-yyyy\",\"<%=dateString%>\")'value='...' style='font-size:10 px'>")
						}
				</SCRIPT>
		 </td>
    
      <td Class="addedit" colspan="6">Supplier
      <input type="hidden" name="sup_code" value="<c:out value="${std.supcode}"/>"><input type="text" name="sname" value="<c:out value="${std.supplier}"/>" size="32" readOnly=true>
      <input type="button" value="Find" Class="submitButton" onclick=FindValue("Sup")>
     &nbsp;&nbsp;Indent&nbsp;&nbsp;
      <input type="text" name="indtno" size="10" readonly="true" value="<c:out value="${std.indentno}"/>">
      <input type="button" value="Find" Class="submitButton" onclick=FindValue("IndentNo")></td>
    </tr>    
<tr><td><br></td></tr>
<tr><td Class="addedit" colspan="5">TitleNo&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Title</td>
<td Class="addedit" colspan="2">Author</td>
<td Class="addedit" colspan="2">Copies&nbsp;&nbsp;&nbsp;&nbsp;IndentNo</td></tr>

</c:forEach> 

</c:when>
<c:otherwise>
<tr>
      <td Class="addedit" colspan="3">Order No&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      <input type="text" name="ordno" onKeydown="Javascript: if (event.keyCode==13) SearchRecord();" size="15" maxlength=15 >
      <input type="button" value="Find" Class="submitButton" onclick=FindValue("OrderNo")></td>
      
      <td Class="addedit" colspan="2">&nbsp;Ord. Date
        
<%-- 	<INPUT name=orddate size=8  onfocus=this.blur(); value="<%=dateString%>"> --%>
	<INPUT name=orddate size=8  id="datepicker" value="<%=dateString%>">
				
		 </td>
		 
      <td Class="addedit" colspan="3">Quote. No&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      <input type="text" name="quoteno" size="15" maxlength=15 >
      </td>
      
      </tr>
      <tr>
      <td Class="addedit" colspan="2">Quote. Date&nbsp;&nbsp;&nbsp;&nbsp;    
     
<%-- 	  <INPUT name=quotedate size=8  onfocus=this.blur(); value="<%=dateString%>"> --%>
	  <INPUT name=quotedate size=8 id="datepicker2" value="<%=dateString%>">
				
		 </td>
      <td Class="addedit" colspan="6">Supplier&nbsp;&nbsp;
      <input type="hidden" name="sup_code"><input type="text" name="sname" size="18" readOnly=true>
      <input type="button" value="Find" Class="submitButton" onclick=FindValue("Sup")>
      &nbsp;&nbsp;Indent&nbsp;&nbsp;&nbsp;
      <input type="text" name="indtno" size="11" readonly="true">
      <input type="button" value="Find" Class="submitButton" onclick=FindValue("IndentNo")></td>      
    </tr>
         
</c:otherwise>
</c:choose> 

<c:forEach items="${IndentOrd}" var="std" varStatus="loop">

<tr><td colspan="5"><INPUT type="hidden" name="titleno[]" value="<c:out value="${std.titleno}"/>" /><c:out value="${std.titleno}"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<INPUT type="hidden" name="title[]" value="<c:out value="${std.title}"/>" />
<INPUT type="hidden" name="titlestatus[]" value="<c:out value="${std.titlestatus}"/>" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<c:out value="${std.title}"/></td>
<td colspan="2"><INPUT type="hidden" name="author[]" value="<c:out value="${std.author}"/>" /><c:out value="${std.author}"/></td>
<td colspan="2">&nbsp;&nbsp;&nbsp;&nbsp;<INPUT type="hidden" name="copies[]" value="<c:out value="${std.apprvoedcopy}"/>" /><c:out value="${std.apprvoedcopy}"/>&nbsp;&nbsp;&nbsp;&nbsp;
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<INPUT type="hidden" name="indentno[]" value="<c:out value="${std.indentno}"/>" /><c:out value="${std.indentno}"/></td></tr>	
</c:forEach>

 <script language="javaScript" >

      var cboxes = document.getElementsByName('currencylist[]');
      var len = cboxes.length;	     
      for (var i=0; i<len; i++) 
      {          
         if(cboxes[i].value) {
                     //alert("hai IF:"+cboxes[i].value);
           document.getElementsByName('currency[]').item(i).value=cboxes[i].value;
         
         }else {
                     //alert("hai ELSE:"+cboxes[i].value);
            document.getElementsByName('currency[]').item(i).value=1;
         }
      } 
 </script>
  
</table>
<br><br>
<table align="center" width="90%">
<tr>
<td></td><td></td></tr>
<tr>
<td></td></tr>

<c:choose>
<c:when test="${IndentOrdSize gt 0}">  
<c:forEach items="${IndentOrd}" var="std"  begin="0" end="0">
<tr><td></td></tr>
    <tr>
     <td Class="addedit">Remarks</td>
      <td colspan="5"><input type="text" name="remarks" value="<c:out value="${std.remarks}"/>" size="75" maxlength=100></td>
    </tr>
    <tr>
      <td Class="addedit">Add1</td>
      <td  colspan="5"><input type="text" name="add1" value="<c:out value="${std.add1}"/>" size="75" maxlength=200></td>
    </tr>
    <tr>
      <td Class="addedit">Add2</td>
      <td  colspan="5" ><input type="text" name="add2" value="<c:out value="${std.add2}"/>" size="75" maxlength=200></td>
    </tr>  
</c:forEach>
</c:when>
<c:otherwise>
<tr><td></td></tr>
    <tr>
     <td Class="addedit">Remarks</td>
      <td colspan="5"><input type="text" name="remarks" size="75" maxlength=100></td>
    </tr>
    <tr>
      <td Class="addedit">Add1</td>
      <td  colspan="5"><input type="text" name="add1" size="75" maxlength=200></td>
    </tr>
    <tr>
      <td Class="addedit">Add2</td>
      <td  colspan="5" ><input type="text" name="add2" size="75" maxlength=200></td>
    </tr>
</c:otherwise>
</c:choose>    
 
    <tr>
      <td  colspan="6" >
        <p align="center">
		<input type="button" value="New" Class="submitButton" name="new" onclick="NewRecord()">
		<input type="button" value="Save" Class="submitButton" name="save" onclick="SaveRecord()">
		<input type="button" value="Delete" Class="submitButton" name="delete" onclick="DeleteRecord()">
		<input type="button" value="Search" Class="submitButton" name="search" onclick="SearchRecord()">
		<input type="reset"  Class="submitButton" value="clear" onclick="NewRecord()">
		<input type="hidden"  name="flag" value="null">
		<input type=hidden name="flag1" >
		<input type="hidden"  name="csubsdate" >
		<input type="hidden"  name="cstodate" >
		</td>
    </tr>    
  </table>
 </CENTER></td></table></center>
</form>
</body>
</html>

<%

session.setAttribute("IndentOrd",null);
session.setAttribute("IndentOrdSize",null);

%>



<%



String valid=request.getParameter("check");
if(valid!=null){
if(request.getParameter("check")!=null){
if(valid.equals("newInvoice")){
 %>
 <script language="JavaScript">
document.ordinv.slno.value="<%=viewObject.getImax()%>";
document.ordinv.ordno.focus();
</script>
<%
  }
			if(valid.equals("UpdateCheck")){%>
            <script language="JavaScript">
			 msg=confirm("Record Already Exists. Do you want to Update !");
					if(msg){
						document.ordinv.flag.value="update";
						document.ordinv.submit();
						}
						else
						{
						alert("Operation Cancelled..!");						
						//document.ordinv.flag.value="new";  
						//document.ordinv.submit();
						}
			//document.ordinv.flag.value="new";
			//document.ordinv.submit();
		   	</script><%
			}

			if(valid.equals("SaveSuccess")){%>
            		<script language="JavaScript">
			alert("Record Saved Successfully!");
		   	</script><%
			}
			
			if(valid.equals("UpdateSuccess")){%>
    		<script language="JavaScript">
	          alert("Record Updated Successfully!");
	        </script><%
	}
			

			if(valid.equals("DeletedSuccess")){%>
    		<script language="JavaScript">
	          alert("Record Deleted Successfully!");
	        </script>        
	        <%
	}
			if(valid.equals("DeleteFail")){%>
    		<script language="JavaScript">
	          alert("Record Not Found!");
	        </script>        
	        <%
	}
			if(valid.equals("RefferedOther")){%>
    		<script language="JavaScript">
	          alert("Invoice Prepared for this Order Number. So cannot be Delete / Update !");
	        </script>        
	        <%
	}
			
  }
  }
%>
<script>

function home()
{
location.href="/AutoLib/Home.jsp";
}

function Logout()
{
location.href="/AutoLib/index.html";
}

function NewRecord()
{
	document.ordinv.method="get";
	document.ordinv.action="index.jsp";
	document.ordinv.submit();		 
}

function SearchRecord()
{
if (document.ordinv.ordno.value=="")
{
  alert("Invalid Order Number..!");		
}else  {
            document.ordinv.method="get";
            document.ordinv.flag.value="search";
            document.ordinv.submit();
}

}

function SaveRecord()
{
	
	if (document.ordinv.ordno.value=="")
    {
       alert("Please Enter Order No !");
          return false;     
    }
    
    if (document.ordinv.sname.value=="")
    {
        alert("Please Choose Supplier !");
          return false;            
    }  
    if (document.ordinv.indtno.value=="")
    {
       alert("Please Choose Indent !");
          return false;     
    }
    
	 var cboxes = document.getElementsByName('title[]');
     var len = cboxes.length;    
     
	 var titstatus = document.getElementsByName('titlestatus[]');            
	 var itemstatus=0;            
	 
	 for (var i=0; i<len; i++) 
     {       
        if(titstatus[i].value =='BILLED')
        {
           itemstatus++;
        }      
     }
	  
	 if(itemstatus >= 1)
     {
        alert('Invoice prepared for this Book(s). So You can`t Save / Update !');
	    return false;
     }
	
	 
	 document.ordinv.flag.value="Save";
	 document.ordinv.submit();
}	 
	 
	 
function DeleteRecord(){
document.ordinv.method="get";

if (document.ordinv.ordno.value=="")
{
  alert("Invalid Order Number..!");		
}
if (document.ordinv.indtno.value=="")
    {
       alert("Insufficient Data !");
          return false;     
}
else  {

  var titstatus = document.getElementsByName('titlestatus[]'); 
  var len = titstatus.length;  
  var itemstatus=0;
  
  for (var i=0; i<len; i++) { 
  
    if(titstatus[i].value == 'BILLED')
    {
      itemstatus++;
    }     
  }  
 
  	 if(itemstatus >= 1)
     {
        alert('Invoice prepared for this Book(s). So You can`t Delete !');
	    return false;
     }

                  msg=confirm("Are You Sure To Delete");
					if(msg){
						document.ordinv.flag.value="delete";
						document.ordinv.submit();
						}
						else
						{
						alert("Operation Cancelled..!");						
						document.ordinv.flag.value="new";  
						document.ordinv.submit();
						}
}

}


function FindValue(val)
{
winpopup=window.open("search_nmvc.jsp?check=CommonSubsdt&flag="+val,"popup","height=400,width=600,top=100,left=100,status=yes,menubar=no,scrollbars=yes,toolbar=no");
}



</script>

