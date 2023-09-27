<%@ include file="/Tree/demoFrameset.jsp"%>
<%@ page language="java" errorPage="/Error/ErrorPage.jsp" import="java.io.*" import="java.util.*" session="true" buffer="12kb" import="Common.Security" import="java.util.ArrayList"%>
<%//Security.checkSecurity(1,session,response,request);%>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/button_css.css" />
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/font.css" />

<jsp:useBean id="bean" scope="request" class="Lib.Auto.Account.AccountBean"/>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored ="false" %>


<html>
<body onload="LoadId()">
<form name="Account" method="post" action=./AccountServlet>


 <P ALIGN="left"><BR>

 <center>
<table>

<tr><td Class="addedit">User&nbsp;Id<td><input type=text name=userid size=50 maxlength="30">

<td>
<c:set var="str" value="<%=bean.getPhoto1()%>"/>
<c:choose>
<c:when test="${str ne null}">

<td align="right" colspan="2" rowspan="4">
<img src="<%=request.getContextPath() %>/Account/photo.jsp" height="100px" width="80px" align=left/></td>

 
</c:when>
<c:otherwise>

<td align="right" colspan="2" rowspan="4">
<img src="<%=request.getContextPath() %>/images/noimage.jpg" height="100px" width="80px" align=left/></td>

</c:otherwise>
</c:choose>
</td>




<input type=hidden name=flag value=register>

</tr>
</table>

<%
String strname=bean.getauthor();
String strdept = bean.getdepartment();
String strdesign = bean.getdesignation();
String strregstatus = bean.getregstatus();
if(strname!=null && strdept!=null && strdesign!=null){
	out.println("<html>");
	out.print("<head>");
	out.print("</head>");
	out.println("<BODY>");
	out.println("<table width='25%' >");
	out.print("<font color='#037a07' size='3'>");
	out.print("<div Class='icon-ok'>");
   	out.println(""+strname+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+strdept+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+strdesign);
   	out.print("</font>");
   	out.print("</br>");
   	out.print("<font id=profileLink>");
   	out.println(strregstatus);
   	out.print("</div>");
   	out.print("</font>");
   	out.println("</table>");
   	out.println("</BODY>");
   	out.println("</html>");

}
%>

<table>
<tr><td></br></td></tr>
</table>
							
<table width="100%" id="campusnewsheadBoard" border="0" align="center" cellpadding="2" cellspacing="2" class="indexTab">
								<tr> <td colspan="5" align="center" bgcolor="#104E8B" id="campusnewsheadBoard">Gate Login Details </td> </tr>
								<tr>
								
						
                                 <td align="center">
										<c:if test="${totalvisted eq 0}">
											<a href="#" id="bodysearch">
												<img src="<%= request.getContextPath() %>/iconImages/GateEntryList.png" width="35" height="35" border="0" align="middle" title="Click here to, view total due user.">
												<br>
													Total&nbsp;Visited(0)
											</a>
										</c:if>
										<c:if test="${totalvisted ne 0}">
											<c:url var="gatereportURL" value="AccountServlet">
												<c:param name="flag" value="Gate_Report_Full" />
											</c:url>
											
											<a href="<%= request.getContextPath() %>/Account/<c:out value="${gatereportURL}"/>" id="bodysearch">
												<img src="<%= request.getContextPath() %>/iconImages/GateEntryList.png" width="35" height="35" border="0" align="middle" title="Click here to, view total due user.">
												<br>
												Total&nbsp;Visited (<c:out value="${totalvisted}"/>)
											</a>
										</c:if>
									</td>  
							

										
										<td align="center">
										<c:if test="${todayGateVisitedCount	 eq 0.00}">
											<a href="#" id="bodysearch">
												<img src="<%= request.getContextPath() %>/iconImages/GateReturn.png" width="35" height="35" border="0" align="middle" title="Click here to, view today paid amount.">
												<br>
												Today&nbsp;Visited(0) 
											</a>
										</c:if>
										<c:if test="${todayGateVisitedCount ne 0.00}">
									<c:url var="todayGateUserURL" value="GateRegisterReportsAll">
											   
											<c:param name="flag" value="todayvisited"/>	
											</c:url>
											<a href="<%= request.getContextPath() %>/Account/AccountServlet?flag=todayvisited" id="bodysearch">
<!-- 											<a href="#" id="bodysearch"> -->
												<img src="<%= request.getContextPath() %>/iconImages/GateReturn.png" width="35" height="35" border="0" align="middle" title="Click here to, view Total Active Gate Users.">
												<br>
											Today&nbsp;Visited (<c:out value="${todayGateVisitedCount}"/>)
											</a>
										</c:if>
										</td>
										
										<td align="center">
										<c:if test="${todayGateUser	 eq 0.00}">
											<a href="#" id="bodysearch">
												<img src="<%= request.getContextPath() %>/iconImages/GateEntry.png" width="35" height="35" border="0" align="middle" title="Click here to, view today paid amount.">
												<br>
											Active&nbsp;Logins (0)
											</a>
										</c:if>
										<c:if test="${todayGateUser ne 0.00}">
									<c:url var="todayGateUserURL" value="GateRegisterReportsAll">
											   
											<c:param name="flag" value="homegate"/>	
											</c:url>
											<a href="<%= request.getContextPath() %>/Account/AccountServlet?flag=homegate" id="bodysearch">
<!-- 											<a href="#" id="bodysearch"> -->
												<img src="<%= request.getContextPath() %>/iconImages/GateEntry.png" width="35" height="35" border="0" align="middle" title="Click here to, view Total Active Gate Users.">
												<br>
												Active&nbsp;Logins (<c:out value="${todayGateUser}"/>)
											</a>
										</c:if>
										</td>
										
<td>&nbsp;</td>										
									</tr>
										
								</table>
	

</form>
</body>
</html>
<div style="padding: 0px 0px 0px 0px;">
		<jsp:include page="userid.jsp" flush="true" />
</div>

<script  language="javascript">
function LoadId(){

document.Account.userid.focus();

}
</script>