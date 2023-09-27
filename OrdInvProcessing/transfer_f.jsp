<%@ page language="java" session="true" buffer="12kb" import="java.sql.*" %>
<%@ include file="/Common.jsp" %>
<html>
<head>
<title>AutoLib</title>
<meta http-equiv="pragma" content="no-cache"/>
</head>
<body bgcolor="#d3d3d3" text=blue>
<align=center style="FILTER: glow(color=white,intensity=10); HEIGHT: 15px; weight: 45"><b><font color="#800000"><A href='<%= response.encodeURL("index.jsp") %>'>BACK</a></font></b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<align=left style="FILTER: glow(color=white,intensity=10); HEIGHT: 15px; weight: 45"><b><font color="#800000"><A href='<%= response.encodeURL("/AutoLib/Login.jsp") %>'>LOGIN HOME</a></font></b>
<center><center>
<h2 align=center style="FILTER: glow(color=white,intensity=10); HEIGHT: 15px; weight: 45"><b><font color="#800000">Transfer Book Information</font></b> </h2>
<form name=Trans  method=post >
<center>
<table border="1" width="77%" height="78" cellpadding="0" cellspacing="0" bordercolor=#008000 >
  <tr>
    <td width="30%" height="22" align="left">
      <p><b><font color="#993333">Invoice Number</font></b></p>
    </td>
    <td width="79%" colspan="3" height="22" align="left">
      <p><input type="text" name="InvNo"  readonly="true" size="65"></p>
    </td>
  </tr>
  <tr>
    <td width="30%" height="13" align="left">
      <p><font color="#993333"><b>AccNo From</b></font></p>
    </td>
    <td width="29%" height="13" align="left">
      <p><input type="text" name="AccFrom" size="20"></p>
    </td>
    <td width="14%" height="13" align="left">
      <p><font color="#993333"><b>AccNo To</b></font></p>
    </td>
    <td width="36%" height="13" align="left">
      <p>&nbsp;<input type="text" name="AccTo" size="20"></p>
    </td>
  </tr>
  <tr>
    <td width="100%" colspan="4" height="25" align="left">
      <p align="center">
	  <input type="button" value="Transfer"  onclick=transfer()>
	  <input type="reset" value="Clear"  name="B2">
	  <input type="hidden" name="flag">
	  </td>
  </tr>
</table>
<center>
</form>
</body>
</html>
<%
int iLevel=0;
      Object o1 = session.getAttribute("UserID");
      Object o2 = session.getAttribute("UserRights");
      boolean bRedirect = false;
      if ( o1 == null || o2 == null ) { bRedirect = true; }
      if ( ! bRedirect ) {
        if ( (o1).equals("")) { bRedirect = true; }
        else if ( (new Integer(o2.toString())).intValue() < iLevel) { bRedirect = true; }
      }
      if ( bRedirect ) {
      response.sendRedirect("/AutoLib/Login.jsp?querystring=" + toURL(request.getQueryString()) + "&SessionID=" + toURL(session.getId()));
  }
java.sql.Connection con=null;
java.sql.Statement st=null;
java.sql.ResultSet rs=null;
try
{
String sErr = loadDriver();
if ( ! sErr.equals("") ) {
 try {
   out.println(sErr);
 }
 catch (Exception e) {}
}
con = cn();
st = con.createStatement();
String Flag_Status="",invoice_no="",invoice_date="",invoice_day="",invoice_month="",invoice_year="",Accto="",Accfrom="",Transfer_Invoice="",sql="";
String invtext=request.getParameter("InvNo");
java.util.StringTokenizer stz1=new java.util.StringTokenizer(invtext,";");
						String in_no=stz1.nextToken();
						String in_date=stz1.nextToken();
						Accto=request.getParameter("AccTo");
Accfrom=request.getParameter("AccFrom");
out.print(in_no);
out.print(in_date);
sql="update book_mas set  invoice_no='" +in_no+ "',Invoice_Date='" +in_date+ "' where access_no  between '"+  Accfrom +"' and '"+ Accto+"'";
//out.print(sql);
st.execute(sql);
%><script language="JavaScript">
      alert("Record Transfered Successfully!");
      	  window.history.back();
	//location.href="index.jsp";
</script><%
}catch(Exception e){out.print("Error"+e); }
finally{
/*-------------------------------DATABASE CONNECTION && STATEMENT IS CLOSED----------------------------------------------------------------------*/	
if ( st != null ) st.close();
try{
if ( con != null ) con.close();
}catch(SQLException sl){out.println(sl.toString());}
  }				   
  %>
