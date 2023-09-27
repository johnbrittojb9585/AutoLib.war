<%@ include file="/Tree/Searchdemoframeset.jsp"%>
<%@ page language="java" errorPage="/Error/ErrorPage.jsp" import="java.util.*" import="Common.Security" import="Lib.Auto.EBookSearch.EBookSearchBean" %>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/button_css.css" />
<!-- #include file="logo.html" -->
<html>

<head>
<meta charset="ISO-8859-1">
<!-- <meta http-equiv="Content-Type" content="text/html; charset=windows-1252"> -->
<title>Autolib Software Systems,Chennai</title>
<meta http-equiv="pragma" content="no-cache">

<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/jquery-ui.css"/>
<%-- <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/jquery-ui-flick.css"/> --%>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/style1.css"/>
<link rel="stylesheet" type="text/css" href="/AutoLib/style.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-2.2.4.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-ui.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/searchEBookAll.js"></script>

</head>
<body>

  <form method="get" name="query" action="./EBookSearchServlet" ONSUBMIT="return validate(this)">
  <br><br><br><br>

    <h2>EBook Search</h2>

     <%
   if(request.getParameter("flags")!=null)
   {
   %>
<h3> -- Record Not Found --</h3>
   <%
   }
   %>

  <table align="center" class="contentTable" width="45%">
  <tr>
<td>
<table align="center" width="83%">
<tr><td> &nbsp; </td></tr>

            <tr>
 <div class="search-container">
	<div class="ui-widget">
            
            <td Class="addedit">Title</td> <td colspan="4"><input type="text" name="title" id="searchEBookTitle" class="search" onkeyup="showEBookTitle(this.value);" size="74" maxlength=255></td>
            
    </div>
</div>			
            
			</tr>
			
             
             <tr>
             
<div class="search-container">
	<div class="ui-widget">
             
              <td Class="addedit">Author</td> <td><input type="text" name="authorName" id="searchEBookAuthor" class="search" onkeyup="showEBookAuthor(this.value);" size="30" maxlength=225 ></td>
              
   </div>              
</div>   
   			  <td Class="addedit">Call No</td> <td><input type="text" name="callNo" size="21" maxlength=50></td>
            </tr>
            
            
               <tr><td Class="addedit">Publisher</td> <td><input type="text" name="pubName" size="30" maxlength=150 ></td>
              <td Class="addedit">Acc.No</td><td><input type="text" name="accessNo" size="21" maxlength=20 ></td>
            </tr>
             <tr><td Class="addedit">Supplier</td> <td><input type="text" name="supName" size="30" maxlength=150 ></td>
              <td Class="addedit">ISBN</td><td><input type="text" name="isbn" size="21" maxlength=20 ></td>
            </tr>
            
               
            <tr>
            
<div class="search-container">
	<div class="ui-widget">
        
              <td Class="addedit">Subject</td>
              <td><input type="text" name="subName" id="searchEBookSubject" class="search" onkeyup="showEBookSubject(this.value);" size="30" maxlength=150 ></td>
   </div>           
</div>              
              <td Class="addedit">Year</td><td><input type="text" name="yop" size="21" maxlength=25 ></td>
            </tr>
            
            
             <tr>
              <td Class="addedit">Department</td>
              <td><input type="text" name="deptName" size="30" maxlength=255></td>
              <td Class="addedit">Course</td><td><input type="text" name="type" size="21" maxlength=4></td>
            </tr>
            
<tr>
<td Class="addedit">Pages</td> <td> <input type="text" name="pages" size="30" maxlength=100>
<td Class="addedit">Edition</td> <td> <input type="text" name="edition" size="21" maxlength=25></td>
</tr> 
<tr><td Class="addedit">Keyword</td> <td> <input type="text" name="keywords" size="30" maxlength=100>
</tr>           
       
            
   
<tr><td>&nbsp;</td></tr>

</table></td></tr></table>
<p align="center">
<input type="hidden" name="flag">
<input type="submit" Class="submitButton" value="Search" name="hid" >
<input type="reset" Class="submitButton" value="Clear" name="B2" >

</p>

</form>

<%

ArrayList sc=new ArrayList();
sc=(ArrayList)request.getAttribute("BranchList");
%>


<script language="javascript">
function validate()
{
if ((query.title.value=="")&&(query.authorName.value=="")&& (query.callNo.value=="")&& (query.pubNamae.value=="")&&(query.subName.value=="")&&(query.deptName.value=="")&&(query.yop.value=="")&&(query.accessNo.value=="")&&(query.edition.value=="")&&(query.type.value=="")&&(query.pages.value=="")&&(query.type.supName=="")&&(query.type.isbn=="")&&(query.type.keywords==""))
{
alert("Please Enter Valid information !");
return false;
}
if(query.accessNo.value.length!=""){

var len =3;
query.accessNo.value.length=len;
}

if((query.title.value.length!="") || (query.authorName.value.length!="") || (query.pubName.value.length!="") || (query.subName.value.length!="") || (query.deptName.value.length!="")||(query.yop.value.length!="") || (query.accessNo.value.length!="") || (query.callNo.value.length!="")|| (query.supName.value.length!="")|| (query.isbn.value.length!="")|| (query.keywords.value.length!=""))
{
if(((query.title.value.length)+(query.authorName.value.length)+(query.pubName.value.length)+(query.subName.value.length)+(query.deptName.value.length)+(query.yop.value.length)+(query.accessNo.value.length)+(query.callNo.value.length)+(query.supName.value.length)+(query.isbn.value.length)+(query.keywords.value.length))<3)
{
alert("Please Enter Minimum three letters !");
return false;
}
}

}

function lenvalidate()
{
if(query.accessNo.value!=""){

query.accessNo.value.length=3
}

if(((query.title.value.length)+(query.authorName.value.length)+(query.pubName.value.length)+(query.subName.value.length)+(query.deptName.value.length)+(query.yop.value.length)+(query.accessNo.value.length)+(query.callNo.value.length)+)<3)
{

alert("Please Enter Minimum three letters !");

return false;
}
return true;
}

</script>

<script language='javascript'>

function search()
{
location.href="index.jsp"
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

function showEBookTitle(data)
{
	var data;
	
	data=document.query.title.value;
		
} 


function Logout()
{
location.href="/AutoLib/index.html";
}

function Year_val() {
if((isNaN(document.query.yop.value))||(document.query.yop.value == ' ')) {
document.query.Year.select();
document.query.Year.value="";
  }
}


</script>

</body>

</html>

