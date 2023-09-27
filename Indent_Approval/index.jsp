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

<form method="post" name="ordinv" action=./IndentApproveServlet>
<br><br><br>

<h2 >Indent Approval Processing </h2>

  <center>
<table align="center" class="contentTable" width="50%">
<td>
<table align="center" width="90%">
<tr><td> &nbsp; </td></tr>

<c:choose>
<c:when test="${IndentApproveSize gt 0}">  
<c:forEach items="${IndentApprove}" var="std"  begin="0" end="0">
     
<tr>
<td Class="addedit" colspan="2">Indent No
      <input type="text" name="indtno" value="<c:out value="${std.indtno}"/>" onKeydown="Javascript: if (event.keyCode==13) SearchRecord();" size="15" maxlength=15 >
      <input type="button" value="Find" Class="submitButton" onclick=FindValue("IndentNo")></td>
       
<td Class="addedit">Approve&nbsp;Date
	  <INPUT name=aprvdate size=8  value="<c:out value="${std.indtdate}"/>" >
				<SCRIPT language=javascript>
						if (!document.layers) {
						document.write("<input type=button onclick='popUpCalendar(this,ordinv.aprvdate, \"dd-mm-yyyy\",\"<%=dateString%>\")'value='...' style='font-size:10 px'>")
						}
				</SCRIPT>
		 </td>	     
</tr>
  
<tr>	
<td Class="addedit" colspan="2">Member&nbsp;&nbsp;
      <input type="hidden" name="memberCode" size="15" value="<c:out value="${std.memcode}"/>" >
      <input type="text" name="member" size="23" readOnly=true value="<c:out value="${std.memname}"/>" >
</td>
    
<td Class="addedit">    
      Indent&nbsp;Date&nbsp;&nbsp;&nbsp;&nbsp;
	  <INPUT name=indtdate size=8  onfocus=this.blur(); value="<c:out value="${std.indtdate}"/>" >
				<SCRIPT language=javascript>
						if (!document.layers) {
						document.write("<input type=button onclick='popUpCalendar(this,ordinv.indtdate, \"dd-mm-yyyy\",\"<%=dateString%>\")'value='...' style='font-size:10 px'>")
						}
				</SCRIPT>
</td>	  
</tr>     
</c:forEach> 

</c:when>
<c:otherwise>
<tr>
      <td Class="addedit">Indent No</td><td>
      <input type="text" name="indtno" onKeydown="Javascript: if (event.keyCode==13) SearchRecord();" size="15" maxlength=15 >
      <input type="button" value="Find" Class="submitButton" onclick=FindValue("IndentNo")>
</td>           

<td Class="addedit">Approve&nbsp;Date

<%-- 	  <INPUT name=aprvdate size=8  onfocus=this.blur(); value="<%=dateString%>"> --%>
	  <INPUT name=aprvdate size=8 id="datepicker"  value="<%=dateString%>">
				
		 </td>	     
</tr>
  
<tr>	
<td Class="addedit" colspan="2">Member&nbsp;&nbsp;&nbsp;&nbsp;
      <input type="hidden" name="memberCode" size="15" value="0">
      <input type="text" name="member" size="23" readOnly=true>&nbsp;</td>
      
<td Class="addedit">    
      Indent&nbsp;Date&nbsp;&nbsp;&nbsp;&nbsp;
<%-- 	  <INPUT name=indtdate size=8  onfocus=this.blur(); value="<%=dateString%>"> --%>
	  <INPUT name=indtdate size=8 id="datepicker2" onfocus=this.blur(); value="<%=dateString%>">
				
		 </td>	  
</tr>     
<input type="hidden"  name="title[]" >
<input type="hidden"  name="titlestatus[]" >
</c:otherwise>
</c:choose> 

</table>

<table align="center" width="90%">

    <tr>
      <td  colspan="6" >
        <p align="center">
		<input type="button" value="Approve" Class="submitButton" name="save" onclick="SaveRecord()">
		<input type="button" value="Search" Class="submitButton" name="search" onclick="SearchRecord()">
		<!-- <input type="reset"  Class="submitButton" value="clear" onclick="NewRecord()"> -->
		<input type="button" Class="submitButton" value="clear" onclick="ClearRec()">
										
		<input type="hidden"  name="flag" value="null">
		<input type=hidden name="flag1" >
		</p>
	</td>
    </tr>    
  </table>
  
</td></table><br><br>  
<c:choose>
<c:when test="${IndentApproveSize gt 0}"> 
<table align="center" class="contentTable" width="85%">

<tr><td>
 <a href="javascript:void();" onclick="javascript:checkAll('ordinv', true);">All</a> | 
         <a href="javascript:void();" onclick="javascript:checkAll('ordinv', false);">None</a>
</td>
<th>TitleNo<th>Title<th>Author<th>Copies<th>Pending<th>Approve
</tr>
<c:forEach items="${IndentApprove}" var="std" varStatus="loop">
<tr><td><br></td></tr>

<tr>

<td><input type='checkbox' id="selectedBook[]" name="selectedBook[]" value="<c:out value="${std.titleNo}"/>">
<input type="hidden"  name="indentNo[]" value="<c:out value="${std.indtno}"/>"></td>

<td><input type="hidden"  name="titleNo[]" value="<c:out value="${std.titleNo}"/>"><c:out value="${std.titleNo}"/></td>
<td><input type="hidden"  name="title[]" value="<c:out value="${std.title}"/>"><c:out value="${std.title}"/>
<input type="hidden"  name="titlestatus[]" value="<c:out value="${std.titlestatus}"/>"></td>

<td><input type="hidden"  name="author[]" value="<c:out value="${std.author}"/>"><c:out value="${std.author}"/>
<input type="hidden"  name="autcode[]" value="<c:out value="${std.authorcode}"/>"></td>

<td><input type="text" size="2" maxlength="2" name="noofcopy[]" value="<c:out value="${std.noofcopy}"/>" readOnly="true"></td>
<td><input type="text" size="2" maxlength="2"  name="pendingcopy[]" value="<c:out value="${std.pendingcopy}"/>" readOnly="true"></td>
<td><input type="text" size="2" maxlength="2"  name="approvecopy[]" value="<c:out value="${std.apprvecopy}"/>" onKeyUp="return Approve_Check();"></td>

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
/**
session.setAttribute("IndentApprove",null);
session.setAttribute("IndentApproveSize",null);
*/
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
	
			if(valid.equals("SaveSuccess")){%>
            		<script language="JavaScript">
			alert("Record Saved Successfully!");
		   	</script><%
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
//	document.ordinv.flag.value="new";
	document.ordinv.action="index.jsp";
	document.ordinv.submit();
		 
}

function ClearRec(){
	document.ordinv.flag.value="Clear";
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


function Approve_Check() {
     var cboxes = document.getElementsByName('approvecopy[]');
     var len = cboxes.length;	 
     
	 var cboxes1 = document.getElementsByName('noofcopy[]');
	 var cboxes2 = document.getElementsByName('pendingcopy[]');
	 	
for (var i=0; i<len; i++) 
{       
      

if((isNaN(cboxes[i].value))||(cboxes[i].value == ' ')) {
cboxes[i].select();
cboxes[i].value="";
} 
if((isNaN(cboxes1[i].value))||(cboxes1[i].value == ' ')) {
cboxes1[i].select();
cboxes1[i].value="";
}
if((isNaN(cboxes2[i].value))||(cboxes2[i].value == ' ')) {
cboxes2[i].select();
cboxes2[i].value="";
}


if((Number(cboxes1[i].value) < Number(cboxes[i].value)))
{
alert("Approve Copy > Indent Copy !");
cboxes[i].select();
cboxes[i].value="";
}


cboxes2[i].value=(cboxes1[i].value-cboxes[i].value);

}
}

function SaveRecord()
{
	if (document.ordinv.indtno.value=="")
    {
       alert("Please Enter Indent No !");
          return false;     
    }
    
    //var cboxes1 = document.getElementsByName('approvecopy[]');
    //var cboxes2 = document.getElementsByName('noofcopy[]');        
    //var cboxes3 = document.getElementsByName('pendingcopy[]');        
	var cboxes = document.getElementsByName('title[]');
    var len = cboxes.length;	 
    
	for (var i=0; i<len; i++) 
    {        
        if (!cboxes[i].value)
        {
          alert("Please Enter Valid Indent !");
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
               
              if(titstatus[i].value=='PENDING' || titstatus[i].value=='APPROVED')
              {
                
              }else{
                  itemstatus++;              
              }
                           
             }      
            }
            
            if(itemstatus >= 1)
            {
              alert('Ordered  some Book(s). So You can`t Approve / Cancel !');
			  return false;
            }            
            
            //if(count==chkitem && chkitem > 0) {
              //alert("Already Approved !");
			  //return false;               
            //}
            if(chkitem >= 1)
         	{
			   //alert('You are selected - '+chkitem);
			   alert(content);
               document.ordinv.flag1.value=content;
	           document.ordinv.flag.value="Save";
	           document.ordinv.submit();
         	}
	        else if(chkitem == 0)
	        {
			  alert('Please select atleast one !');
			  return false;
	        }
	        break; 
        }  
    }           
}	 

	 

function FindValue(val)
{
winpopup=window.open("search_nmvc.jsp?check=CommonSubsdt&flag="+val,"popup","height=400,width=600,top=100,left=100,status=yes,menubar=no,scrollbars=yes,toolbar=no");
}

</script>

