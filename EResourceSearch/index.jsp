
<%@ include file="/Tree/demoFrameset.jsp"%>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/button_css.css" />

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
<title>Autolib Software Systems,Chennai</title>
<meta http-equiv="pragma" content="no-cache">

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

  <form method="get" name="query" action="./EResourceSearchServlet"  ONSUBMIT="return validate(this)">
  <br><br><br><br>
    <h2>E-Resource Search</h2>

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
<p align="center">
        <td Class="addedit">Resource Type<td>
    <select name="type" size="1">
  	  <option value="E-journal">E-journal</option>
  	  <option value="E-Books">E-Books</option>
   	 <option value="E-journal Portal">E-journal Portal</option>
   	 <option value="E-Books Portal">E-Books Portal</option>
      <option value="Database">Database</option>
      <option value="Others">Others</option>
      
&nbsp; </select>
    </td>          
<p>

<!-- <p align="center"><td></td> -->
<!--         <td Class="addedit">Resource Type<td> -->
<!--     <select name="type" size="1"> -->
<!--   	  <option value="E-journal">E-journal</option> -->
<!--   	  <option value="E-Books">E-Books</option> -->
<!--    	 <option value="E-journal Portal">E-journal Portal</option> -->
<!--    	 <option value="E-Books Portal">E-Books Portal</option> -->
<!--       <option value="Database">Database</option> -->
<!--       <option value="Others">Others</option> -->
      
<!-- &nbsp; </select> -->
<!--     </td>           -->
<!-- <p> -->
<tr><td colspan=3 >
<center>
<input type="button" Class="submitButton" value="Search" onclick=database() name="hid" >
<!-- <input type="button" Class="submitButton" value="Others" onclick=others() name="B2" ></center> -->
<input type=hidden name=flag>
</table></center>
</td></table></center>

    </form>



<script language='javascript'>

function database(){

document.query.method="get";
document.query.flag.value="Database";
document.query.submit();
}

// function others(){
// document.query.method="get";
// document.query.flag.value="others";
// document.query.submit();
// }


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

