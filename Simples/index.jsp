<%@ include file="/Tree/Searchdemoframeset.jsp"%>
<%@ page language="java" errorPage="/Error/ErrorPage.jsp" import="java.util.*" import="Common.Security" import="Lib.Auto.Branch.BranchBean" %>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/button_css.css" />
<!-- #include file="logo.html" -->
<html>

<head>
<meta charset="ISO-8859-1">
<!-- <meta http-equiv="Content-Type" content="text/html; charset=windows-1252"> -->
<title>Autolib Software Systems,Chennai</title>
<%-- <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/jquery-ui-base.css"/> --%>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/jquery-ui-flick.css"/>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/style1.css"/>
<link rel="stylesheet" type="text/css" href="/AutoLib/style.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-2.2.4.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-ui.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/searchAll2.js"></script>

<script type="text/javascript" src="<%=request.getContextPath()%>/js/datepicker.js"></script>	
<script type="text/javascript" src="<%=request.getContextPath()%>/js/datepicker2.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/searchEBookMasAll.js"></script>
<!-- <meta http-equiv="pragma" content="no-cache"> -->

</head>
<body >
<%
session.getAttribute("Title");
%>
<%
try {
ArrayList sc=new ArrayList();
sc=(ArrayList)request.getAttribute("BranchList");
%>

<script language="javascript">

function validate()
{

if((query.Title.value=="")&&(query.Author.value=="")&& (query.Call_no.value=="")&& (query.Publisher.value=="")&&(query.subject.value=="")&&(query.Keywords.value=="")&&(query.Year.value=="")&&(query.acc_no.value=="")&&(query.isbn.value=="")&&(query.location.value=="")&&(query.edition.value==""))
{
return true;
}
if(query.acc_no.value.length!=""){

var len =3;
query.acc_no.value.length=len;
}

if ((query.Title.value.length!="") || (query.Author.value.length!="") || (query.Publisher.value.length!="") || (query.subject.value.length!="") || (query.Keywords.value.length!="") || (query.location.value.length!="") || (query.Year.value.length!="") || (query.acc_no.value.length!="") || (query.isbn.value.length!="") || (query.edition.value.length!="") || (query.Call_no.value.length!=""))
{
if (((query.Title.value.length)+(query.Author.value.length)+(query.Publisher.value.length)+(query.subject.value.length)+(query.Keywords.value.length)+(query.location.value.length)+(query.Year.value.length)+(query.acc_no.value.length)+(query.isbn.value.length)+(query.edition.value.length)+(query.Call_no.value.length))<3)
{
return true;
}
}
}

function lenvalidate()
{
if(query.acc_no.value!=""){

query.acc_no.value.length=3
}

if (((query.Title.value.length)+(query.Author.value.length)+(query.Call_no.value.length)+(query.Publisher.value.length)+(query.Subject.value.length)+(query.Keywords.value.length)+(query.Year.value.length)+(query.acc_no.value.length)+(query.isbn.value.length))<3)
{
	
alert("Please Enter Minimum three letters !");

return false;
}
return true;

}

</script>

  <form method="get" name="query" action="./SimpleServlet"  ONSUBMIT="return validate(this)">
  <br><br><br><br>

    <h2>Simple Search</h2>

     <%
   if(request.getParameter("flags")!=null)
   {
   %>
<h3> -- Record Not Found --</h3>
   <%
   }
   %>

  <table align="center" class="contentTable" width="55%">
  <tr>
<td>
<table align="center" width="93%">
<tr><td> &nbsp; </td></tr>

<div class="search-container">
	<div class="ui-widget">

            <tr>
            <td Class="addedit">Title</td> <td colspan="4"><input type="text" name="Title" id="searchTitle" class="search" onkeyup="showTitle(this.value);" size="74" maxlength=255></td>
			</tr>
 </div>
</div>           			           
            
             <tr>
             
<div class="search-container">
	<div class="ui-widget">
             
              <td Class="addedit">Author</td> <td><input type="text" name="Author" id="searchAuthor" class="search" onkeyup="showAuthor(this.value);" size="30" maxlength=225 ></td>
              
  </div>
</div>           			           
              
   			  <td Class="addedit">Call.No</td> <td><input type="text" name="Call_no" size="21" maxlength=50></td>
            </tr>
            
            
               <tr><td Class="addedit">Publisher</td> <td><input type="text" name="Publisher" size="30" maxlength=150 ></td>
              <td Class="addedit">Acc.No</td><td><input type="text" name="acc_no" size="21" maxlength=20 ></td>
            </tr>
              
            <tr>
<div class="search-container">
	<div class="ui-widget">
            
              <td Class="addedit">Subject</td>
              <td><input type="text" name="subject" id="searchSubject" class="search" onkeyup="showSubject(this.value);" size="30" maxlength=150 ></td>
  </div>
</div>           			           
              
              
              
              <td Class="addedit">ISBN</td><td><input type="text" name="isbn" size="21" maxlength=25 ></td>
            </tr>
            
            
            
             <tr>
              <td Class="addedit">Keyword</td>
              <td><input type="text" name="Keywords" size="30" maxlength=255></td>
              <td Class="addedit">Year</td><td><input type="text" name="Year" size="21" maxlength=4 onKeyUp="return Year_val();"></td>
            </tr>
              
<tr>
<td Class="addedit">Location</td> <td> <input type="text" name="location" size="30" maxlength=100></td>
<td Class="addedit">Edition</td> <td> <input type="text" name="edition" size="21" maxlength=25></td>
</tr>
<tr>
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
 
 <td  Class="addedit">Availability</td>
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
			<td class="addedit"><input type="checkbox" name="datesel">FromDate</td>
			<td><INPUT name=fromdate size=14 id="datepicker" value="<%=Security.CalenderDate()%>"></td>
							
			<td class="addedit">ToDate</td>
			<td><INPUT name=todate size=14 id="datepicker2" value="<%=Security.CalenderDate()%>"></td>						
			</tr>
			
			
			<tr>		
			<td class="addedit"><input type="checkbox" name="accnocheck">FromAcc</td>
			 <td><input type="text" name="fromAcc" size="21" maxlength=25 ></td>	
							
			<td class="addedit">To Acc</td>
			  <td><input type="text" name="toAcc" size="21" maxlength=25 ></td>				
			</tr>
<!-- <div class="search-container"> -->
<!-- 	<div class="ui-widget"> -->
             
<!--               <td Class="addedit">Department</td> <td><input type="text" name="Department" id="searchDepartment" class="search" onkeyup="showDepartment(this.value);" size="21" maxlength=225 ></td> -->
              
<!--   </div> -->
<!-- </div>           			            -->
       	 
	 
	  </tr>            
       
            
    <tr>
    
      <td Class="addedit"></td>
      <td><SELECT size="1" name="txtBranch" style="display:none"/>
                <OPTION SELECTED VALUE="NO">ALL</OPTION>
                                        <% 
			                           Iterator it=sc.iterator();
                                        while(it.hasNext()){
                                        	                                        	
                                        	BranchBean view=(BranchBean) it.next();                                        	
				                     
				                        int b_code=view.getCode();
				                        String b_name=view.getName();
				                       
				                       out.print("<option  value="+b_code+">" +b_name+"</option>");
                                       }
                                        
}catch(Exception e)
{
	System.out.print("Error at Line:143 "+e);
	//e.printStackTrace();
}
				                        %>

              </SELECT>
             
              
</tr>

<tr><td>&nbsp;</td></tr>

</table></td></tr></table>
<p align="center">
<input type="hidden" name="flag">
<input type="hidden" name="datechecked"> 
<input type="hidden" name="fromToAcc"> 
<input type="submit" Class="submitButton" value="search" name="hid" onclick="datecheck(); acc_check();">
<input type="reset" Class="submitButton" value="Clear" name="B2" >

</p>

    </form>

<script language='javascript'>

function datecheck()
{	
if(document.query.datesel.checked== true)
{			 
	document.query.datechecked.value="checked";
}
else 
{
document.query.datechecked.value="unchecked";
}
}

function acc_check()
{
	
if(document.query.accnocheck.checked== true)
{	
	
	document.query.fromToAcc.value="checked";
	
	
}
else 
{
document.query.fromToAcc.value="unchecked";
}
}

function search()
{
location.href="index.asp"
}

function adv()
{
location.href="/AutoLib/Advanced/index.jsp"
}

function home()
{
location.href="/AutoLib/Home.jsp";
}
function newarrival()
{
location.href="newarrivalhome.asp"
}

function account()
{
location.href="/portal/admin.html";
}

function Logout()
{
location.href="/AutoLib/index.html";
}

function Year_val() {
if((isNaN(document.query.Year.value))||(document.query.Year.value == ' ')) {
document.query.Year.select();
document.query.Year.value="";
  }
}


</script>

</body>

</html>

