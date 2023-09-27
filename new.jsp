<%
String flag="";
String o1 =(String)session.getAttribute("UserID");
if(o1==null || o1.equals("")){String A="0";}
String A = (String)session.getAttribute("UserRights");
if(A==null || A.equals("")){A="0";}
int CHK=Integer.parseInt(A);
out.print(CHK);
switch(CHK)
{
case 1:
flag="ADMIN-I";
break;
case 2:
flag="ADMIN-II";
break;
case 3:
flag="DATA ENTRY";
break;
case 4:
flag="JOURNAL";
break;
case 5:
flag="ACQUISITION";
break;
case 6:
flag="COUNTER";
break;
case 7:
flag="PUBLIC";
break;
case 0:
flag="ADMIN-I";
break;
}
if(request.getParameter("flag")!=null)
{
flag=request.getParameter("flag");
}
%>
<!--TO SEE THE FILE BETTER USE WORDWRAP-->
<html>
<head>
<script src=menu.js></script>
<style>
.s{font-family:verdana;font-size:12px;font-weight:bold;color:'#003399';cursor:default}
</style>
<title>AutoLib Software Systems</title>
<meta http-equiv="pragma" content="no-cache"/>


</head>
<body  background="/AutoLib/soft.jpg"  leftmargin=0  onload="closes()"><!--bgcolor="#66FFCC"-->

<table width=100% style=position:absolute;left:0;top:0; cellpadding=0 cellspacing=0 border=0>
  <tr>
     <td bgcolor=#6699FF height=1 class=s style=font-size:1px colspan=2>x</td>
  </tr>
  <tr>
     <td bgcolor=#6699FF height=1 class=s style=font-size:1px colspan=2>x</td>
  </tr>
  <tr>
 <td bgcolor=#6699FF>
 <%


 
 if (flag.equalsIgnoreCase("ADMIN-I"))
 
 {
	  //response.sendRedirect("/AutoLib/Tree/demoFrameset.jsp");
 }
 if (flag.equalsIgnoreCase("ADMIN-II")){
 response.sendRedirect("/AutoLib/Tree/demoFrameset_admin2.html");
 }
 if (flag.equalsIgnoreCase("COUNTER")){
  response.sendRedirect("/AutoLib/Tree/Counter.html");   
 }
 if (flag.equalsIgnoreCase("PUBLIC")){
response.sendRedirect("/AutoLib/Tree/public.html");

 }
  if (flag.equalsIgnoreCase("ACQUISITION")){
  response.sendRedirect("/AutoLib/Tree/acquisition.html");
 }
 if (flag.equalsIgnoreCase("JOURNAL")){
 response.sendRedirect("/AutoLib/Tree/journal.html");
 }
 if (flag.equalsIgnoreCase("DATA ENTRY")){
  response.sendRedirect("/AutoLib/Tree/dataentry.html");
 }
 %>
 </tr>
 </td>
<!-- Only added for better visual effect of the sample -->
   <tr>
     <td bgcolor=#6699FF height=1 class=s style=font-size:1px colspan=2>x</td>
  </tr>
  <tr>
     <td bgcolor=#6699FF height=1 class=s style=font-size:1px colspan=2>x</td>
  </tr>
</table><br><br>
<table width="10%" align="right">
   <div align=right>
<img border='0' src='/AutoLib/images/Logout.gif' onclick='Logout()'></div>
   </table>
</center>

</body>
</html>
<script language=javascript>
function Logout()
{
location.href="/AutoLib/index.html";
}

</script>


