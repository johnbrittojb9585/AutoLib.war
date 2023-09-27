<%
String URole=session.getAttribute("UserRights").toString().trim();
if(URole.equalsIgnoreCase("3") || URole.equalsIgnoreCase("4") || URole.equalsIgnoreCase("6") || URole.equalsIgnoreCase("7"))
{		
	response.sendRedirect("sessiontimeout.jsp");
}	
%>
<%@ include file="/Tree/demoFrameset.jsp"%>
<%@ page language="java" errorPage="/Error/ErrorPage.jsp" import="java.io.*" import="java.util.*" import="Lib.Auto.Currency.CurrencyBean" session="true" buffer="12kb" import="Common.Security"%>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/button_css.css" />
<jsp:useBean id="bean" scope="request" class="Lib.Auto.Indent_Mas.IndentMasDetailsBean" />
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

<form method="post" name="ordinv" action=./IndentMasServlet>
<br><br><br>

<h2 >Indent Processing Master </h2>

  <center>
<table align="center" class="contentTable" width="77%">
<td>
<table align="center" width="95%">
<tr><td> &nbsp; </td></tr>

<c:choose>
<c:when test="${IndentMasSize gt 0}">  
<c:forEach items="${IndentMas}" var="std"  begin="0" end="0">
<tr>
      <td Class="addedit" colspan="8">Indent No&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      <input type="text" name="indtno" value="<c:out value="${std.indtno}"/>" onKeydown="Javascript: if (event.keyCode==13) SearchRecord();" size="15" maxlength=15 >
      <input type="button" value="Find" Class="submitButton" onclick=FindValue("IndentNo")>
           
      &nbsp;&nbsp;&nbsp;Title Status&nbsp;&nbsp;&nbsp;
      &nbsp;&nbsp;&nbsp;<select name="titlestatus" id="titlestatus" >
      <option VALUE="NO" >----------------------------</option>
      <option VALUE="PENDING" selected>PENDING</option>
      <option VALUE="PARTAPPROVED">PARTAPPROVED</option>
      <option VALUE="APPROVED">APPROVED</option>      
      <option VALUE="ORDERED">ORDERED</option>      
      <option VALUE="INCOMPLETE">INCOMPLETE</option>       
      <option VALUE="COMPLETE">COMPLETE</option> </select> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;    
    
            
     &nbsp;&nbsp;&nbsp;&nbsp;Indent Date   &nbsp;&nbsp;&nbsp;     
	  <INPUT name=indtdate size=8  onfocus=this.blur(); value="<c:out value="${std.indtdate}"/>">
	      <SCRIPT language=javascript>
						if (!document.layers) {
						document.write("<input type=button onclick='popUpCalendar(this,ordinv.indtdate, \"dd-mm-yyyy\",\"<%=dateString%>\")'value='...' style='font-size:10 px'>")
						}
		   </SCRIPT>
				
		 </td>	     
</tr>
  
<tr>
	
      <td Class="addedit" colspan="8">Member&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      <input type="hidden" name="memberCode" value="<c:out value="${std.memcode}"/>" size="15" >
      <input type="text" name="member" size="15" value="<c:out value="${std.memname}"/>" readOnly=true >&nbsp;<input type="button" value="Find" Class="submitButton" onclick=FindValue("Member") >
      &nbsp;&nbsp;&nbsp;Indent Status&nbsp;&nbsp;&nbsp;
      <input type="text" name="indtstatus" value="<c:out value="${std.indtstatus}"/>" size="15" readOnly=true >&nbsp;&nbsp;
      &nbsp;&nbsp;Total&nbsp;Indent&nbsp;Amount&nbsp;&nbsp;
      <input type="text" name="indtamt" value="0" size="8" readOnly=true ></td>
</tr>
   
     
<tr><td><br><br></td></tr>
      
<tr>
      <td Class="addedit" colspan="8">Title No
      &nbsp;&nbsp;&nbsp;<input type="text" name="titleno" size="5" value="0" onKeyUp="return TitleNo_Val();" readOnly=true>
      &nbsp;&nbsp;&nbsp;Title&nbsp;&nbsp;
      <input type="text" name="title" size="52" >
      <input type="button" value="Find" Class="submitButton" onclick=FindValue("Title")>
      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;No&nbsp;of&nbsp;Copies&nbsp;&nbsp;&nbsp;
      <input type="text" name="noofcopy" size="5" maxlength="2" value="1" onKeyUp="return NoofCopy_Val();">         
</tr> 
      
<tr>
      <td Class="addedit" colspan="4">Author&nbsp;&nbsp;&nbsp;&nbsp;
      <input type="hidden" name="authorCode" value="1"><input type="text" name="author" size="38" >
      <input type="button" value="Find" Class="submitButton" onclick=FindValue("Author") ></td>

      <td Class="addedit" colspan="4">Publisher&nbsp;&nbsp;
      <input type="hidden" name="pubCode" value="1"><input type="text" name="pubname" size="29" value="NIL" readOnly=true>&nbsp;
      <input type="button" value="Find" Class="submitButton" onclick=FindValue("Pub") ></td>
</tr>

<tr>
      <td Class="addedit" colspan="8">Department&nbsp;&nbsp;
      <input type="text" name="department" size="34" value="NIL" readOnly=true>
      <input type="hidden" name="deptCode" value="1"><input type="button" value="Find" Class="submitButton" onclick=FindValue("Dept") >
      &nbsp;&nbsp;&nbsp;ISBN&nbsp;&nbsp;<input type="text" name="isbn" size="15">&nbsp;
      Pub&nbsp;Year&nbsp;&nbsp;<input type="text" name="pubyear" size="4" value="0" onKeyUp="return YearPub_Val();" >&nbsp;&nbsp;Ed&nbsp;&nbsp;<input type="text" name="edition" size="4"> </td>       
</tr>       

<tr>
<td Class="addedit" colspan="8">
  B-Cost&nbsp;&nbsp;
   <input type="text" name="bcost" size="4" value="0" maxlength=8  onKeyUp="return Bcost_Val();" > 
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Currency&nbsp;&nbsp;
	<SELECT SIZE="1" NAME="bcurrency"   	onchange="find_amount()"  >
    <OPTION  VALUE="1" selected>Rupees&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    </OPTION>    
     <% 
     if(sc!=null) {
			                           Iterator it=sc.iterator();
			                           
                                        while(it.hasNext()){
                                        	                                        	
                                        	CurrencyBean view=(CurrencyBean) it.next();                                        	
				                     
				                        String curr=view.getCurrency();
				                        String ind=view.getIndian_value();
				                        %>
				                       
				                     <%
				                       out.println("<option  value="+ind+">" +curr+"</option>");
                                       }
                   }
				                        %>		                            

	 </SELECT>
      &nbsp;&nbsp;&nbsp;&nbsp;Bprice&nbsp;&nbsp;<input type="text" name="bprice" size="8" maxlength=12 value="0" onKeyUp="return BPrice_Val();">
      &nbsp;&nbsp;&nbsp;&nbsp;Discount&nbsp;&nbsp;
    <input type="text" name="bdiscount" size="4" value="0" maxlength=5 onKeyUp="return Discount_Val();">
    &nbsp;Accepted&nbsp;Price&nbsp;&nbsp;
    <input type="text"  name="bacceptPrice" size="8" maxlength=9 value="0" onKeyUp="return NetAmount_Val();">
</td>
</tr>       

</c:forEach> 

</c:when>
<c:otherwise>
<tr>
      <td Class="addedit" colspan="8">Indent No&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      <input type="text" name="indtno" onKeydown="Javascript: if (event.keyCode==13) SearchRecord();" size="15" maxlength=15 >
      <input type="button" value="Find" Class="submitButton" onclick=FindValue("IndentNo")>
           
      &nbsp;&nbsp;&nbsp;Title Status&nbsp;&nbsp;&nbsp;
      &nbsp;&nbsp;&nbsp;<select name="titlestatus" id="titlestatus" >
      <option VALUE="NO" >----------------------------</option>
      <option VALUE="PENDING" selected>PENDING</option>
      <option VALUE="PARTAPPROVED">PARTAPPROVED</option>
      <option VALUE="APPROVED">APPROVED</option>      
      <option VALUE="ORDERED">ORDERED</option>      
      <option VALUE="INCOMPLETE">INCOMPLETE</option>       
      <option VALUE="COMPLETE">COMPLETE</option> </select> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;    
    
            
     &nbsp;&nbsp;&nbsp;&nbsp;Indent Date   &nbsp;&nbsp;&nbsp;     
<%-- 	  <INPUT name=indtdate size=8  onfocus=this.blur(); value="<%=dateString%>"> --%>
	  <INPUT name=indtdate size=8 id="datepicker" onfocus=this.blur(); value="<%=dateString%>">
				
		 </td>	     
</tr>
  
<tr>
	
      <td Class="addedit" colspan="8">Member&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      <input type="hidden" name="memberCode" size="15" value="0">
      <input type="text" name="member" size="15" readOnly=true>&nbsp;<input type="button" value="Find" Class="submitButton" onclick=FindValue("Member") >
      &nbsp;&nbsp;&nbsp;Indent Status&nbsp;&nbsp;&nbsp;
      <input type="text" name="indtstatus" value="PENDING" size="15" readOnly=true >&nbsp;&nbsp;
      &nbsp;&nbsp;Total&nbsp;Indent&nbsp;Amount&nbsp;&nbsp;
      <input type="text" name="indtamt" value="0" size="8" readOnly=true ></td>
</tr>
    
     
<tr><td><br><br></td></tr>
      
<tr>
      <td Class="addedit" colspan="8">Title No
      &nbsp;&nbsp;&nbsp;<input type="text" name="titleno" value="0" size="5" onKeyUp="return TitleNo_Val();" readOnly=true>
      &nbsp;&nbsp;&nbsp;Title&nbsp;&nbsp;
      <input type="text" name="title" size="52" >
      <input type="button" value="Find" Class="submitButton" onclick=FindValue("Title")>
      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;No&nbsp;of&nbsp;Copies&nbsp;&nbsp;&nbsp;
      <input type="text" name="noofcopy" size="5" maxlength="2" value="1" onKeyUp="return NoofCopy_Val();">         
</tr> 
      
<tr>
      <td Class="addedit" colspan="4">Author&nbsp;&nbsp;&nbsp;&nbsp;
      <input type="hidden" name="authorCode" value="1"><input type="text" name="author" size="38">
      <input type="button" value="Find" Class="submitButton" onclick=FindValue("Author") ></td>

      <td Class="addedit" colspan="4">Publisher&nbsp;&nbsp;
      <input type="hidden" name="pubCode" value="1"><input type="text" name="pubname" value="NIL" size="27" readOnly=true>
      <input type="button" value="Find" Class="submitButton" onclick=FindValue("Pub") ></td>
</tr>

<tr>
      <td Class="addedit" colspan="8">Department&nbsp;&nbsp;
      <input type="text" name="department" size="34" value="NIL" readOnly=true>
      <input type="hidden" name="deptCode" value="1"><input type="button" value="Find" Class="submitButton" onclick=FindValue("Dept") >
      &nbsp;&nbsp;&nbsp;ISBN&nbsp;&nbsp;<input type="text" name="isbn" size="15">&nbsp;
      Pub&nbsp;Year&nbsp;&nbsp;<input type="text" name="pubyear" size="4" value="0" onKeyUp="return YearPub_Val();">&nbsp;&nbsp;Ed&nbsp;&nbsp;<input type="text" name="edition" size="4"> </td>       
</tr>       

<tr>
<td Class="addedit" colspan="8">
  B-Cost&nbsp;&nbsp;
   <input type="text" name="bcost" size="4" value="0" maxlength=8  onKeyUp="return Bcost_Val();" > 
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Currency&nbsp;&nbsp;
	<SELECT SIZE="1" NAME="bcurrency"   	onchange="find_amount()"  >
    <OPTION  VALUE="1" selected>Rupees&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    </OPTION>    
     <% 
     if(sc!=null) {
			                           Iterator it=sc.iterator();
			                           
                                        while(it.hasNext()){
                                        	                                        	
                                        	CurrencyBean view=(CurrencyBean) it.next();                                        	
				                     
				                        String curr=view.getCurrency();
				                        String ind=view.getIndian_value();
				                        %>
				                       
				                     <%
				                       out.println("<option  value="+ind+">" +curr+"</option>");
                                       }
                   }
				                        %>		                            

	 </SELECT>
      &nbsp;&nbsp;&nbsp;&nbsp;Bprice&nbsp;&nbsp;<input type="text" name="bprice" size="8" maxlength=12 value="0" onKeyUp="return BPrice_Val();" >
      &nbsp;&nbsp;&nbsp;&nbsp;Discount&nbsp;&nbsp;
    <input type="text" name="bdiscount" size="4"  maxlength=5 value="0" onKeyUp="return Discount_Val();" >
    &nbspAccepted&nbsp;Price&nbsp;&nbsp;
    <input type="text"  name="bacceptPrice" size="8" maxlength=9 value="0" onKeyUp="return NetAmount_Val();" >
</td>
</tr>       
      
<tr>
<td><input type="hidden"  name="titleNo[]" >
<input type="hidden"  name="title[]" >
<input type="hidden"  name="noofcopy[]" >
<input type="hidden"  name="author[]" >
<input type="hidden"  name="autcode[]" >
<input type="hidden"  name="publisher[]" >
<input type="hidden"  name="pubcode[]" >
<input type="hidden"  name="department[]">
<input type="hidden"  name="deptcode[]" >
<input type="hidden"  name="isbn[]" >
<input type="hidden"  name="yearpub[]" >
<input type="hidden"  name="edition[]" >
<input type="hidden"  name="bcost[]" >
<input type="hidden"  name="currency[]" >
<input type="hidden"  name="bprice[]" >
<input type="hidden"  name="discount[]" >
<input type="hidden"  name="netamount[]" >
<input type="hidden"  name="membercode[]" >
<input type="hidden"  name="titlestatus[]" ></td>
</tr>	          
</c:otherwise>
</c:choose> 

</table>

<table align="center" width="90%">
<tr>
<td><p align="center"><input type="button" value="ADD" Class="submitButton" name="add" onclick="ADDRecord()">
</p></td></tr>
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
  
</td></table><br><br>  
<c:choose>
<c:when test="${IndentMasSize gt 0}"> 
<table align="center" class="contentTable" width="85%">

<tr><td>
 <a href="javascript:void();" onclick="javascript:checkAll('ordinv', true);">All</a> | 
         <a href="javascript:void();" onclick="javascript:checkAll('ordinv', false);">None</a>
</td>
<th>TitleNo<th>Title<th>Author<th>Copy<th>Publisher<th>Department<th>Amount<th>Status
</tr>
<c:forEach items="${IndentMas}" var="std" varStatus="loop">
<tr><td><br></td></tr>

<tr>
<td><input type='checkbox' id="selectedBook[]" name="selectedBook[]" value="<c:out value="${std.titleNo}"/>"></td>
<td><input type="hidden"  name="titleNo[]" value="<c:out value="${std.titleNo}"/>"><c:out value="${std.titleNo}"/></td>
<td><input type="hidden"  name="title[]" value="<c:out value="${std.title}"/>"><c:out value="${std.title}"/></td>

<td><input type="hidden"  name="author[]" value="<c:out value="${std.author}"/>"><c:out value="${std.author}"/>
<input type="hidden"  name="autcode[]" value="<c:out value="${std.authorcode}"/>"></td>

<td><input type="hidden"  name="noofcopy[]" value="<c:out value="${std.noofcopy}"/>"><c:out value="${std.noofcopy}"/></td>

<td><input type="hidden"  name="publisher[]" value="<c:out value="${std.publisher}"/>"><c:out value="${std.publisher}"/>
<input type="hidden"  name="pubcode[]" value="<c:out value="${std.pubcode}"/>"></td>

<td><input type="hidden"  name="department[]" value="<c:out value="${std.department}"/>"><c:out value="${std.department}"/>
<input type="hidden"  name="deptcode[]" value="<c:out value="${std.deptcode}"/>"></td>

<td><input type="hidden"  name="netamount[]" value="<c:out value="${std.netamount}"/>"><c:out value="${std.netamount}"/></td>
<td><input type="hidden"  name="titlestatus[]" value="<c:out value="${std.titlestatus}"/>"><c:out value="${std.titlestatus}"/>

<input type="hidden"  name="isbn[]" value="<c:out value="${std.isbn}"/>">
<input type="hidden"  name="yearpub[]" value="<c:out value="${std.yearpub}"/>">
<input type="hidden"  name="edition[]" value="<c:out value="${std.edition}"/>">
<input type="hidden"  name="bcost[]" value="<c:out value="${std.bcost}"/>">
<input type="hidden"  name="currency[]" value="<c:out value="${std.bcurrency}"/>">
<input type="hidden"  name="bprice[]" value="<c:out value="${std.bprice}"/>">
<input type="hidden"  name="discount[]" value="<c:out value="${std.discount}"/>">

<input type="hidden"  name="membercode[]" value="<c:out value="${std.memcode}"/>">
<input type="hidden"  name="addstatus[]" value="<c:out value="${std.addstatus}"/>">

</td>

</tr>	

</c:forEach>
</table>
<br><br>
</c:when>
</c:choose>

 </CENTER>
</form>
</body>
</html>

<%

session.setAttribute("IndentMas",null);
session.setAttribute("IndentMasSize",null);

%>

<%

String valid=request.getParameter("check");
String titleno=request.getParameter("newno");

if(titleno!=null){
if(request.getParameter("newno")!=null){


	if(titleno.equals("NewTitleNo")){%>
    <script language="JavaScript">

				document.ordinv.titleno.value="<%=bean.getTitleNo() %>";

   	</script><%
	}
}
}


if(valid!=null){
if(request.getParameter("check")!=null){	
	
	if(valid.equals("UpdateCheck")){%>
            <script language="JavaScript">
			 msg=confirm("Record Already Exists. Do you want to Update !");
					if(msg){
						document.ordinv.flag.value="Update";
						document.ordinv.submit();
						}
						else
						{
						alert("Operation Cancelled..!");						
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

function NewRecord()
{
	document.ordinv.method="get";
	document.ordinv.flag.value="new";
	//document.ordinv.action="index.jsp";
	document.ordinv.submit();
		 
}

function ADDRecord()
{
    if (document.ordinv.indtno.value=="")
    {
        alert("Invalid Indent Number..!");	
        return false;            	
    }
    if (document.ordinv.member.value=="")
    {
        alert("Please Choose Member Name !");
        return false;            
    }  
    if (document.ordinv.title.value=="")
    {
        alert("Enter Valid Title !");
        return false;            
    }  
    if (document.ordinv.noofcopy.value=="")
    {
        alert("Please Enter Number of Copies !");
        return false;            
    }  
    if (document.ordinv.pubyear.value=="")
    {
        alert("Invalid Year of Publication !");
        return false;            
    }  
    if (document.ordinv.bcost.value=="")
    {
        alert("Enter Valid Book Cost !");
        return false;            
    }  
    if (document.ordinv.bprice.value=="")
    {
        alert("Enter Valid Book Price !");
        return false;            
    } 
    if (document.ordinv.bdiscount.value=="")
    {
        alert("Enter Valid Book Discount !");
        return false;            
    }     
    if (document.ordinv.bacceptPrice.value=="")
    {
        alert("Enter Valid Book NetPrice !");
        return false;            
    } 
    if(!document.ordinv.author.value)
    {
       document.ordinv.author.value="NIL";
       document.ordinv.authorCode.value="1";
    }
        
            document.ordinv.method="get";
            document.ordinv.flag.value="ADDBOOK";
            document.ordinv.submit();


}

function SearchRecord()
{
if (document.ordinv.indtno.value=="")
{
  alert("Invalid Indent Number..!");		
}else  {
            document.ordinv.method="get";
            document.ordinv.flag.value="Search";
            document.ordinv.submit();
}

}


function find_amount(){

document.ordinv.bprice.value=document.ordinv.bcurrency.value * document.ordinv.bcost.value
document.ordinv.bacceptPrice.value=document.ordinv.bprice.value

document.ordinv.bacceptPrice.value=document.ordinv.bprice.value-((document.ordinv.bdiscount.value/100)* document.ordinv.bprice.value); 
}


function NoofCopy_Val() {
if((isNaN(document.ordinv.noofcopy.value))||(document.ordinv.noofcopy.value == ' ')) {
document.ordinv.noofcopy.select();
document.ordinv.noofcopy.value="";    

  }
}

function YearPub_Val() {
if((isNaN(document.ordinv.pubyear.value))||(document.ordinv.pubyear.value == ' ')) {
document.ordinv.pubyear.select();
document.ordinv.pubyear.value="";    

  }
}

function TitleNo_Val() {
if((isNaN(document.ordinv.titleno.value))||(document.ordinv.titleno.value == ' ')) {
document.ordinv.titleno.select();
document.ordinv.titleno.value="";    

  }
}

function Bcost_Val()
{
if((isNaN(document.ordinv.bcost.value))||(document.ordinv.bcost.value == ' ')) {
document.ordinv.bcost.select();
document.ordinv.bcost.value="";
  }
}

function BPrice_Val() {
if((isNaN(document.ordinv.bprice.value))||(document.ordinv.bprice.value == ' ')) {
document.ordinv.bprice.select();
document.ordinv.bprice.value="";
    
  }
}

function Discount_Val() {
if((isNaN(document.ordinv.bdiscount.value))||(document.ordinv.bdiscount.value == ' ')) {
document.ordinv.bdiscount.select();
document.ordinv.bdiscount.value="";    
  }
document.ordinv.bacceptPrice.value=document.ordinv.bprice.value-((document.ordinv.bdiscount.value/100)* document.ordinv.bprice.value); 
}

function NetAmount_Val() {
if((isNaN(document.ordinv.bacceptPrice.value))||(document.ordinv.bacceptPrice.value == ' ')) {
document.ordinv.bacceptPrice.select();
document.ordinv.bacceptPrice.value="";
    
  }
}

function SaveRecord()
{	
	
	if (document.ordinv.indtno.value=="")
    {
       alert("Please Enter Indent No !");
          return false;     
    }
        
	 var cboxes = document.getElementsByName('title[]');
     var len = cboxes.length;	 
	     
	for (var i=0; i<len; i++) 
    {       
       
        if (!cboxes[i].value)
        {
          alert("Please Add Book(s) First !");
          return false;            
        }
        else {        
        
            var cboxes = document.getElementsByName('selectedBook[]');
            var len = cboxes.length;
            
            var titstatus = document.getElementsByName('titlestatus[]');            
  
            var content="";
            var chkitem=0;
            var itemstatus=0;            
    
            for (var i=0; i<len; i++) {
             if(cboxes[i].checked == true)
             {  
               chkitem++;
               content=content+","+cboxes[i].value;       
             }
                          
             if(titstatus[i].value!='PENDING')
             {
              itemstatus++;
              //alert("hai"+cboxes[i].value);
             }
             
            }
            
            if(itemstatus >= 1)
            {
              alert('Already Approved / Ordered some Book(s). So You can`t Save / Update !');
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
			  alert('Please select atleast one Book to save !');
			  return false;
	        }
	        break; 
        }  
    }    
}	 

	 

function DeleteRecord(){
document.ordinv.method="get";

if (document.ordinv.indtno.value=="")
{
  alert("Invalid Indent Number..!");		
  return false;
}

    var cboxes = document.getElementsByName('selectedBook[]');
    var len = cboxes.length;  
    var titstatus = document.getElementsByName('titlestatus[]');  
       
    var content="";
    var chkitem=0;
    var itemstatus=0;
    
    for (var i=0; i<len; i++) {
    if(cboxes[i].checked == true)
    {  
      chkitem++;
      content=content+","+cboxes[i].value;       
    }
    
    if(titstatus[i].value!='PENDING')
    {
      itemstatus++;
    }     
    }
    
    if(itemstatus >= 1)
    {
       alert('Already Approved / Ordered some Book(s). So You can`t Delete !');
       return false;
    }  
    
    if(chkitem == 0)
	{
			alert('Please select All to Delete !');
			return false;
	}
	else if(chkitem == len)
	{
			//alert('You are selected - '+chkitem);
			//alert(content);
            
	                msg=confirm("Are You Sure To Delete");
					if(msg){
						document.ordinv.flag.value="Delete";
						document.ordinv.flag1.value=content;
						document.ordinv.submit();
						}
						else
						{
						alert("Operation Cancelled..!");						
						document.ordinv.flag.value="new";  
						document.ordinv.submit();
						}
	}
	else if(chkitem < len)
	{
			alert('Please select All to Delete !');
			return false;
	}
}


function FindValue(val)
{
winpopup=window.open("search_nmvc.jsp?check=CommonSubsdt&flag="+val,"popup","height=400,width=600,top=100,left=100,status=yes,menubar=no,scrollbars=yes,toolbar=no");
}

</script>

