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

<meta http-equiv="pragma" content="no-cache"/>
</head>
<body background="/AutoLib/soft.jpg" >
<%
	java.util.Date d =new java.util.Date();
	SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	String dateString = sdf.format(d);

%>

<!-- Style Sheet -->

<form method="post" name="ordinv" action=./IndentInvServlet>
<br><br><br>

<h2 >Indent Invoice Processing</h2>

  <center>
<table align="center" class="contentTable" width="76%">
<td>
<table align="center" width="95%">
<tr><td> &nbsp; </td></tr>


<c:choose>
<c:when test="${JNLSearchSize gt 0}">  
<c:forEach items="${JNLSearch}" var="std"  begin="0" end="0">
    <tr>      
      <td Class="addedit">Inv. No</td>
      <td colspan="2"><input type="text" name="invno" size="17" maxlength=15 value="<c:out value="${std.invoiceno}"/>" onKeydown="Javascript: if (event.keyCode==13) SearchRecord();">
            <input type="button" value="Find" Class="submitButton" onclick=FindValue("InvoiceNo")></td>
      <td Class="addedit" colspan="2">&nbsp;&nbsp;&nbsp;&nbsp;Inv. Date
	<INPUT name=invdate size=8  onfocus=this.blur(); value="<c:out value="${std.invoicedate}"/>">
				<SCRIPT language=javascript>
						if (!document.layers) {
						document.write("<input type=button onclick='popUpCalendar(this,ordinv.invdate, \"dd-mm-yyyy\",\"<%=dateString%>\")'value='...' style='font-size:10 px'>")
						}
				</SCRIPT>
		 </td>
	
   <td Class="addedit"  colspan="3">OrderNo
      <input type="text" name="ordno" value="<c:out value="${std.ordno}"/>" readonly=true size="17" maxlength=15>
      <input type="button" value="Find" Class="submitButton" onclick=FindValue("Sup")></td>
   </tr>           
    
<tr><td colspan=2>
 <a href="javascript:void();" onclick="javascript:checkAll('ordinv', true);">Check All</a> | 
         <a href="javascript:void();" onclick="javascript:checkAll('ordinv', false);">UnCheck All</a>
</td>
</tr>
</c:forEach> 

</c:when>
<c:otherwise>
<tr>      
      <td Class="addedit">Inv. No</td>
      <td><input type="text" name="invno" size="15" maxlength=15 onKeydown="Javascript: if (event.keyCode==13) SearchRecord();">
      <input type="button" value="Find" Class="submitButton" onclick=FindValue("InvoiceNo")></td>
      <td Class="addedit">Inv. Date</td>
      <TD >
<%-- 	<INPUT name=invdate size=8  onfocus=this.blur(); value="<%=dateString%>"> --%>
	<INPUT name=invdate size=8 id="datepicker" value="<%=dateString%>">
				
		 </td>	 

      <td Class="addedit">OrderNo</td>
      <td colspan=2><input type="text" name="ordno" readonly=true value="" size="15" maxlength=15>
      <input type="button" value="Find" Class="submitButton" onclick=FindValue("Sup")></td>
   </tr>
         
</c:otherwise>
</c:choose> 

<c:forEach items="${JNLSearch}" var="std" varStatus="loop">

<tr><td><br><br> </td></tr>
<tr><td colspan="4"><input type='checkbox' id="selectedJNL[]" name="selectedJNL[]" value="<c:out value="${std.titleno}"/>" /><c:out value="${std.titleno}"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<input type="hidden" name="titleno[]" value="<c:out value="${std.titleno}"/>"><input type="hidden" name="title[]" value="<c:out value="${std.title}"/>"><c:out value="${std.title}"/></td>
<td colspan="4"><input type="hidden" name="copies[]" value="<c:out value="${std.copies}"/>"><font color="#E72715">Copy:&nbsp;<c:out value="${std.copies}"/></font>&nbsp;&nbsp;&nbsp;
<input type="hidden" name="supcode[]" value="<c:out value="${std.supcode}"/>"><input type="hidden" name="sname[]" value="<c:out value="${std.supplier}"/>"><c:out value="${std.supplier}"/>&nbsp;-&nbsp;
<input type="hidden" name="ordno[]"  value="<c:out value="${std.ordno}"/>"><c:out value="${std.ordno}"/>
<input type="hidden" name="indentno[]"  value="<c:out value="${std.indentno}"/>">
<input type="hidden" name="status[]"  value="<c:out value="${std.flag}"/>">
</td></tr>

<tr>
<td Class="addedit" colspan="8">
  B-Cost&nbsp;&nbsp;&nbsp;
   <input type="text" name="bcost[]" size="6" value="<c:out value="${std.bcost}"/>" maxlength=8  onKeyUp="find_amount()" > <!--onKeyUp="return author_code_val1();"-->
&nbsp;&nbsp;&nbsp;&nbsp;Currency&nbsp;&nbsp;&nbsp;
	<SELECT SIZE="1" NAME="currency[]"   	onchange="find_amount()" >
    <OPTION  VALUE="1">Rupees&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    </OPTION>
    
     <% 
			                           Iterator it=sc.iterator();
			                           
                                        while(it.hasNext()){
                                        	                                        	
                                        	CurrencyBean view=(CurrencyBean) it.next();                                        	
				                     
				                        String curr=view.getCurrency();
				                        String ind=view.getIndian_value();
				                        %>
				                       
				                     <%
				                       out.println("<option  value="+ind+">" +curr+"</option>");
                                       }
				                        %>
			                            

	 </SELECT>
	 <input type="hidden" name="currencylist[]" value="<c:out value="${std.bcurrency}"/>" >	  	 
	 
&nbsp;&nbsp;&nbsp;&nbsp;Bprice&nbsp;&nbsp;&nbsp;<input type="text" name="bprice[]" size="6" value="<c:out value="${std.bprice}"/>" maxlength=12 onKeyUp="return author_code_val1();">

&nbsp;&nbsp;&nbsp;&nbsp;Discount&nbsp;&nbsp;&nbsp;
    <input type="text" name="discount[]" size="6" value="<c:out value="${std.discount}"/>" maxlength=5 onKeyUp="return author_code_val1();">
&nbsp;&nbsp;&nbsp;&nbsp;Net.Price	
&nbsp;&nbsp;&nbsp;<input type="text"  name="acceptPrice[]" size="6" value="<c:out value="${std.amount}"/>"  maxlength=8 onKeyUp="return author_code_val1();">
</td>  </tr>




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

<c:when test="${JNLSearchSize gt 0}">  
<c:forEach items="${JNLSearch}" var="std"  begin="0" end="0">
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
		<input type="button" value="New" Class="submitButton" name="new" onclick="test()"> <!--onclick="NewRecord()"-->
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
 </center></td></table></center>
</form>
</body>
</html>

<%
session.setAttribute("JNLSearch",null);
session.setAttribute("JNLSearchSize",null);

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
					
					var cboxes = document.getElementsByName('selectedJNL[]');
                    var len = cboxes.length;
  
                    var content="";
                    var chkitem=0;
    
                    for (var i=0; i<len; i++) {
                    cboxes[i].checked = true;
                     if(cboxes[i].checked == true)
                     {  
                       chkitem++;
                       content=content+","+cboxes[i].value;       
                     }      
                    }
    
                    if(chkitem >= 1)
	                {
			           alert('You are selected - '+chkitem);
			           alert(content);
                       document.ordinv.flag1.value=content;
	                   document.ordinv.flag.value="update";
					   document.ordinv.submit();
	                }
	                else if(chkitem == 0)
	                {
			           alert('Please select atleast one Journal from Order to save !'+len);
			           //return false;
	                }   

						}
						else
						{
						alert("Operation Cancelled..!");						
						//document.ordinv.flag.value="new";  
						//document.ordinv.submit();
						}			
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
  }
  }
%>
<script>

function checkAll(formname, checktoggle)
{
     var checkboxes = new Array();
      checkboxes = document[formname].getElementsByTagName('input');

      for (var i = 0; i < checkboxes.length; i++) {
          if (checkboxes[i].type === 'checkbox') {
               checkboxes[i].checked = checktoggle;
          }
      }
}


function test() {
    var cboxes = document.getElementsByName('selectedJNL[]');
    var len = cboxes.length;    
  
   var content="";
   var res=[];
    for (var i=0; i<len; i++) {
    if(cboxes[i].checked)
    {      
      content=content+","+cboxes[i].value;   
    } 
      
    }
    
    alert(content);
    document.ordinv.flag.value="selectV1";
    document.ordinv.flag1.value=content;
    document.ordinv.submit();
   
}

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
	//document.ordinv.flag.value="new";
	document.ordinv.action="index.jsp";
	document.ordinv.submit();

}

function SearchRecord()
{
if (document.ordinv.invno.value=="")
{
  alert("Invalid Invoice Number..!");		
}else  {
            document.ordinv.method="get";
            document.ordinv.flag.value="search";
            document.ordinv.submit();
}

}

function author_code_val1() {
	 var cboxes1 = document.getElementsByName('bcost[]');
     var len = cboxes.length;	
      
	 var cboxes2 = document.getElementsByName('bprice[]');
	 var cboxes3 = document.getElementsByName('discount[]');
	 var cboxes4 = document.getElementsByName('acceptPrice[]');	 
	 
	
for (var i=0; i<len; i++) 
{           

if((isNaN(cboxes1[i].value))||(cboxes1[i].value == ' ')) {
cboxes1[i].select();
cboxes1[i].value="";
}
if((isNaN(cboxes2[i].value))||(cboxes2[i].value == ' ')) {
cboxes2[i].select();
cboxes2[i].value="";
}
if((isNaN(cboxes3[i].value))||(cboxes3[i].value == ' ')) {
cboxes3[i].select();
cboxes3[i].value="";
}  
if((isNaN(cboxes4[i].value))||(cboxes4[i].value == ' ')) {
cboxes4[i].select();
cboxes4[i].value="";
}
cboxes4[i].value=cboxes2[i].value-((cboxes3[i].value/100)* cboxes2[i].value);

}
}


function find_amount(){

	 var cboxes = document.getElementsByName('bprice[]');
     var len = cboxes.length;	 
     
	 var cboxes1 = document.getElementsByName('bcost[]');
	 var cboxes2 = document.getElementsByName('currency[]');
	 var cboxes3 = document.getElementsByName('acceptPrice[]');
	 var cboxes4 = document.getElementsByName('discount[]');
	 	
for (var i=0; i<len; i++) 
{       
cboxes[i].value=(cboxes2[i].value*cboxes1[i].value);
cboxes3[i].value=cboxes[i].value-((cboxes4[i].value/100)* cboxes[i].value);
      
}
author_code_val1();

}

function SaveRecord()
{	
	if (document.ordinv.invno.value=="")
    {
       alert("Please Enter Invoice No !");
          return false;     
    }
    
    if (document.ordinv.ordno.value=="")
    {
       alert("Please choose Order No !");
          return false;     
    }
      
	 var cboxes2 = document.getElementsByName('bcost[]');
     var len = cboxes2.length;	 	 
	 var cboxes3 = document.getElementsByName('bprice[]');
	 var cboxes4 = document.getElementsByName('discount[]');
	 var cboxes5 = document.getElementsByName('acceptPrice[]');	 
	 
	 
	for (var i=0; i<len; i++) 
    {       
        
        if (cboxes2[i].value=="")
        {
        alert("Please fillup atleast one textbox");
        cboxes2[i].focus();
        return false;            
        }
        
        if (cboxes3[i].value > 0)
        {
        if (cboxes2[i].value==0)
        {
        alert("Please must fillup Bcost");
        cboxes2[i].focus();
        return false; 
        }           
        }
        
        if (cboxes3[i].value=="")
        {
        alert("Please fillup atleast one textbox");
        cboxes3[i].focus();
        return false;            
        }
        
        if (cboxes4[i].value=="")
        {
        alert("Please fillup atleast one textbox");
        cboxes4[i].focus();
        return false;            
        }
        
        if (cboxes5[i].value=="")
        {
        alert("Please fillup atleast one textbox");
        cboxes5[i].focus();
        return false;            
        }
      
    }                     
  
    var cboxes = document.getElementsByName('selectedJNL[]');
    var len = cboxes.length;
    
	var indtstatus = document.getElementsByName('status[]');            
	var itemstatus=0;        
  
    var content="";
    var chkitem=0;
    
    for (var i=0; i<len; i++) {
    if(cboxes[i].checked == true)
    {  
      chkitem++;
      content=content+","+cboxes[i].value;       
    }   
       
    if(indtstatus[i].value =='COMPLETED')
    {
      itemstatus++;
    }      
    }

	if(itemstatus >= 1)
    {
      alert('Payment prepared for this Book(s). So You can`t Save / Update !');
	  return false;
    }    
        
    if(chkitem >= 1)
	{
			//alert('You are selected - '+chkitem);
			//alert(content);
            document.ordinv.flag1.value=content;
	        document.ordinv.flag.value="Save";
	        document.ordinv.submit();
	}
	else if(chkitem == 0)
	{
			alert('Please select atleast one Journal from Order to save !');
			return false;
	}     
}	 
	 

function DeleteRecord(){
document.ordinv.method="get";

if (document.ordinv.invno.value=="")
{
  alert("Invalid Invoice Number..!");	
  return false;	
}

    var cboxes = document.getElementsByName('selectedJNL[]');
    var len = cboxes.length;

	var indtstatus = document.getElementsByName('status[]');            
	var itemstatus=0;
  
    var content="";
    var chkitem=0;
    
    for (var i=0; i<len; i++) {
    if(cboxes[i].checked == true)
    {  
      chkitem++;
      content=content+","+cboxes[i].value;       
    }      
    
    if(indtstatus[i].value =='COMPLETED')
    {
      itemstatus++;
    }    
    }    
    
	if(itemstatus >= 1)
    {
      alert('Payment prepared for this Book(s). So You can`t Delete !');
	  return false;
    }        
        
    if(chkitem == 0)
	{
			alert('Please select All Journal to Delete !');
			return false;
	}
	else if(chkitem == len)
	{
			//alert('You are selected - '+chkitem);
			alert(content);
            
	                msg=confirm("Are You Sure To Delete");
					if(msg){
						document.ordinv.flag.value="delete";
						document.ordinv.flag1.value=content;
						document.ordinv.submit();
						}
						else
						{
						alert("Operation Cancelled..!");						
						document.ordinv.flag.value="new";  
						//document.ordinv.submit();
						}
	}
	else if(chkitem < len)
	{
			alert('Please select All Journal to Delete !');
			return false;
	}

}




function FindValue(val)
{
  winpopup=window.open("search_nmvc.jsp?flag="+val,"popup","height=400,width=600,top=100,left=100,status=yes,menubar=no,scrollbars=yes,toolbar=no");
}


</script>

