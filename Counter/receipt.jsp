

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored ="false"  import="Common.Security_Counter" %>

<html>
<head>
<title>AutoLib Software Systems</title>
<meta http-equiv="pragma" content="no-cache"/>
<link rel="stylesheet" type="text/css" href="/AutoLib/style.css">
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/button_css.css" />
</head>

<body onload="load()">
<form name="Author" method="post" action=./ReceiptServlet>
<br>

<c:choose>
<c:when test="${ReceiptListSize gt 0}">  
<c:forEach items="${ReceiptList}" var="std"  begin="0" end="0">
 
<table align="left" class="contentTable" width="15%">
<td>
<table align="left" width="40%">
<tr><td colspan="3"> <h4>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<c:out value="${std.status}"/>&nbsp;RECEIPT</h4> </td></tr>
<tr>
<td Class="addedit" colspan="2">UserID&nbsp;:&nbsp;<c:out value="${std.code}"/></td>
<td Class="addedit" colspan="2">Name&nbsp;:&nbsp;<c:out value="${std.name}"/></td>
</tr>    
<tr>
<td Class="addedit" colspan="2">Acc.No&nbsp;:&nbsp;<c:out value="${std.accno}"/></td>
<td Class="addedit" colspan="2">Doc&nbsp;:&nbsp;<c:out value="${std.document}"/></td>
</tr>  
<tr>
<td Class="addedit" colspan="5">Title&nbsp;:&nbsp;<c:out value="${std.title}"/></td>
</tr>
<tr>
<c:choose>
<c:when test="${std.status eq 'RENEW'}">
<td Class="addedit" colspan="2">Renew&nbsp;Date:&nbsp;<c:out value="${std.idate}"/></td>
</c:when>
<c:otherwise>
<td Class="addedit" colspan="2">Issue&nbsp;Date:&nbsp;<c:out value="${std.idate}"/></td>
</c:otherwise>
</c:choose>
<td Class="addedit" colspan="2">Due&nbsp;Date:&nbsp;<c:out value="${std.ddate}"/></td>
</tr>   

<tr>
<c:if test="${std.status eq 'RETURN'}"> 
<td Class="addedit" colspan="2">Return&nbsp;Date:&nbsp;<%=Security_Counter.TodayDate_view()%></td>
</c:if>
<c:if test="${std.fineAmt ne null}"> 
<td Class="addedit" colspan="2">Fine&nbsp;Amount:&nbsp;<c:out value="${std.fineAmt}"/></td>
</c:if>
</tr>

<tr><td>&nbsp;</td></tr>
<tr><td>&nbsp;</td></tr>
<tr>
<td Class="addedit" colspan="2">Staff</td>
<td Class="addedit" colspan="2">Borrower</td>
</tr>
   
</c:forEach>
</c:when>

<c:otherwise>
<td align="left" colspan="3"><font color="RED"><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;No Record Found !</b></font></td>
</c:otherwise>

</c:choose> 

   
<tr><td align="center" colspan="3">
<CENTER>
<!--    <INPUT TYPE="button" class="submitButton" name="print" value="Print" onClick="window.print()">-->
</CENTER>
</td></tr>
</table>
</td>

</table>
</form>
</body>
</html>

<!--
///////////////////////////////////////JAVA SCRIPT USEING CLIENT SIDE///////////////////////////////////////////////// --> 

<c:if test="${ReceiptListSize ne 0 }">
<script language=javascript>
  window.print();
  //window.close();
</script> 
</c:if>

<%
//session.setAttribute("ReceiptList", null);
//session.setAttribute("ReceiptListSize", "0");
%>
