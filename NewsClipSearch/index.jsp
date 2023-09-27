
<%@ include file="/Tree/Searchdemoframeset.jsp"%>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/button_css.css" />

<html>

<head>
<meta charset="ISO-8859-1">
<meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
<title>Autolib Software Systems,Chennai</title>
<meta http-equiv="pragma" content="no-cache">
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/jquery-ui.css"/>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/style1.css"/>
<link rel="stylesheet" type="text/css" href="/AutoLib/style.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-2.2.4.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-ui.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/searchNewsClipping.js"></script>

</head>
<body >




<script language="javascript">
function validate()
{


if ((query.Title.value=="")&&(query.Author.value=="")&& (query.Call_no.value=="")&& (query.Publisher.value=="")&&(query.subject.value=="")&&(query.Keywords.value=="")&&(query.Year.value=="")&&(query.acc_no.value=="")&&(query.isbn.value==""))
{
alert("Please Enter Valid information !");
return false;
}
if(query.acc_no.value.length!=""){

var len =3;
query.acc_no.value.length=len;
}
if (((query.Title.value.length)+(query.Author.value.length)+(query.Call_no.value.length)+(query.Publisher.value.length)+(query.subject.value.length)+(query.Keywords.value.length)+(query.Year.value.length)+(query.acc_no.value.length)+(query.isbn.value.length))<3)
{

alert("Please Enter Minimum three letters !");

return false;

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

  <form method="get" name="query" action="./NewsClipSearchServlet"  ONSUBMIT="return validate(this)">
  <br><br><br><br>

    <h2>News Clipping Search</h2>

     <%
   if(request.getParameter("flags")!=null)
   {
   %>
<h3> -- Record Not Found --</h3>
   <%
   }
   %>
   <center>
  <table align="center" class="contentTable" width="45%">
<td>
<table align="center" width="90%">
<tr><td> &nbsp; </td></tr>

<div class="search-container">
	<div class="ui-widget">

            <tr>
              <td Class="addedit">Name</td>
              <td colspan="2"  >
            <input type="text" name="name" id="searchName" class="searchNewsClipping" onkeyup="showName(this.value);" size="63" maxlength="40" ></td>
            </tr>
            <tr>
              <td Class="addedit">Type</td>
              <td colspan="2"  >
            <input type="text" name="type" id="searchType" class="searchNewsClipping" onkeyup="showType(this.value);" size="63" maxlength="40" ></td>
            </tr>
            <tr>
              <td Class="addedit">Title</td>
              <td colspan="2"  >
            <input type="text" name="title" id="searchTitle" class="searchNewsClipping" onkeyup="showTitle(this.value);" size="63" maxlength="40" ></td>
            </tr>
            <tr>
              <td Class="addedit">Subject</td>
              <td colspan="2"  >
            <input type="text" name="subject" id="searchSubject" class="searchNewsClipping" onkeyup="showSubject(this.value);" size="63" maxlength="40" ></td>
            </tr>
    </div>
</div>            
            <tr>
              <td Class="addedit">Abstract</td>
              <td colspan="2"  >
            <input type="text" name="abstract" size="63" maxlength="40" ></td>
            </tr>
<div class="search-container">
	<div class="ui-widget">
            
            <tr>
              <td Class="addedit">Keywords</td>
              <td colspan="2"  >
            <input type="text" name="keywords" id="searchKeywords" class="searchNewsClipping" onkeyup="showKeywords(this.value);" size="63" maxlength="40" ></td>
            </tr>
    </div>
</div>            

<tr><td colspan=3 >
<center>
<input type="submit" Class="submitButton" value="search" name="hid" >
<input type="reset" Class="submitButton" value="Clear" name="B2" ></center>
</table></center>
</td></table></center>

    </form>



<script language='javascript'>

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

</script>

</body>

</html>

