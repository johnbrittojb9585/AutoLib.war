<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
<title>Autolib Software Systems,Chennai</title>
<meta http-equiv="pragma" content="no-cache">
<link rel="stylesheet" type="text/css" href="/AutoLib/style.css">
</head>
<body ><!--bgcolor="#C5C5C5"-->
<script language="javascript">
function validate()
{
if ((query.Title.value=="")&&(query.Author.value=="")&& (query.Publisher.value=="")&&(query.Subject.value=="")&&(query.Keywords.value=="")&&(query.Year.value=="")&&(query.accno.value=="")&&(query.isbn.value==""))
{
alert("Please Enter Valid information !");
return false;
}
return true;

}

</script>

  <form method="get" name="query" action="./SimpleBrowse"  ONSUBMIT="return validate(this)">
 <br>

<center>

<img border='0' src='Image/Account.gif' onclick='account()'> -->
<img border='0' src='/AutoLib/images/Advanced.gif' onclick='adv()'>
</center>

    <h2>Simple Search</h2>

     <%
   if(request.getParameter("flags")!=null)
   {
   %>
<h3> -- Record Not Found --</h3>
   <%
   }
   %>
   <center>
    <table >
            <tr>
              <td >Title</td>
              <td colspan="2"  >
            <input type="text" name="Title" size="60" ></td>
            </tr>
            <tr>
              <td >Author</td>
              <td colspan="2" >
            <input type="text" name="Author" size="60" ></td>
            </tr>
            <tr>
              <td>Publisher</td>
              <td >
            <input type="text" name="Publisher" size="30" ></td>
              <td >
            Acc.No<input type="text" name="accno" size="21" ></td>
            </tr>
            <tr>
              <td>Subject</td>
              <td >
            <input type="text" name="Subject" size="30" ></td>
              <td >
            ISBN&nbsp;&nbsp; <input type="text" name="isbn" size="21" ></td>
            </tr>
            <tr>
              <td>Keyword</td>
              <td >
            <input type="text" name="Keywords" size="30" ></td>
              <td >
            Year&nbsp;&nbsp;&nbsp; <input type="text" name="Year" size="21" ></td>
            </tr>

<tr><td colspan=3 >
<center>
<input type="submit" value="search" name="hid" >
<input type="reset" value="Clear" name="B2" ></center>
</table></center>


    </form>

<script language='javascript'>

function search()
{
location.href="index.asp"
}

function adv()
{
location.href="/AutoLib/BrowseAdvanced/index.jsp"
}

function home()
{
location.href="/AutoLib/Browse/index.jsp";
}
function newarrival()
{
location.href="newarrivalhome.asp"
}

function account()
{
location.href="/portal/admin.html";}

function Logout()
{
location.href="/AutoLib/index.html";
}

</script>

</body>

</html>

