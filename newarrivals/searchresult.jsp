

<%
String URole=session.getAttribute("UserRights").toString().trim();
if( URole.equalsIgnoreCase("4") || URole.equalsIgnoreCase("5") || URole.equalsIgnoreCase("8"))
{		
	response.sendRedirect("sessiontimeout.jsp");
}	
%>

<%@ include file="/Tree/demoFrameset.jsp"%>

<%@ include file="/jsp/common/taglibs.jsp"%>

<%@ page language="java" session="true" buffer="12kb" import="java.util.*"%>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/button_css.css" />
<jsp:useBean id="bean" scope="request" class="Lib.Auto.NewArrivals.NewArrivalsBean" />
<%@ page language="java" errorPage="/Error/ErrorPage.jsp"
 import="java.io.*" import="java.util.*"  

  import="Lib.Auto.NewArrivals.NewArrivalsBean" 
   session="true" buffer="12kb" import="Common.Security"%> 
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<br>
<h2>New Arrivals</h2>

<table bgcolor="E3E3E3" align="center" width=57% cellpadding=0 cellspacing=0>
<tr><td>&nbsp;</td></tr>


<tr>
<c:if test="${NewArrivalDisply ne null }" >
<c:forEach items="${NewArrivalDisply}" var="std" varStatus="loop">


<c:if test = "${fn:contains(std.contents, 'jpg')}">
<td rowspan=9 align="center">
<a href="<%= request.getContextPath() %>/filePath/BOOK/${std.contents}" target=_base>
 <img src="<%= request.getContextPath() %>/filePath/BOOK/${std.contents}" target=_base width="75" height="100" border="1" align="middle"
 title="Click here to, view Contents Pages."></a>
 </td>
</c:if>

<c:if test = "${fn:contains(std.contents, 'png')}">
<td rowspan=9 align="center">
<a href="<%= request.getContextPath() %>/filePath/BOOK/${std.contents}" target=_base>
 <img src="<%= request.getContextPath() %>/filePath/BOOK/${std.contents}" target=_base width="75" height="100" border="1" align="middle"
 title="Click here to, view Contents Pages."></a>
 </td>
</c:if>



     <c:if test="${fn:contains(std.contents, 'pdf')}">
     <td  rowspan=9 align="center">
     
     <a href="<%= request.getContextPath() %>/filePath/BOOK/${std.contents}" target=_base>
    <img src="<%= request.getContextPath() %>/iconImages/pdf.png" width="75" height="100" border="0" align="middle"></a>
    </td>
    </c:if>
     
 
 
<c:if test="${std.contents eq ''}">
 <td rowspan=9 align="center">
 <img src="<%= request.getContextPath() %>/iconImages/noImages.png" width="75" height="100" border="1" align="middle">
 </td>
 </c:if>
 
  
  
  
 <td class="addedit">AccessNo</td><td>:&nbsp;&nbsp;<font size="1"  face="verdana"><c:out value="${std.accessno}" /></font></td>
<tr>
<c:if test="${std.sres ne ''}">
<td class="addedit">Author</td><td>:&nbsp;&nbsp;<font size="1"  face="verdana"><c:out value="${std.keyword1}."/><c:out value=" / ${std.sres}"/></font></td>
</c:if>

<c:if test="${std.sres eq ''}">
<td class="addedit">Author</td><td>:&nbsp;&nbsp;<font size="1"  face="verdana"><c:out value="${std.keyword1}."/></font></td>

</c:if></tr>




<tr><td class="addedit">Title</td><td>:&nbsp;&nbsp;<font size="1"  face="verdana"><c:out value="${std.keyword2}" /></font></td>
</tr>


<tr>
<c:if test="${std.editon ne ''}">
<td class="addedit">Edition</td><td>:&nbsp;&nbsp;<font size="1"  face="verdana"><c:out value="${std.editon}" /></font></td>
</c:if>
</tr>

<tr><td class="addedit">PubDetails</td><td>:&nbsp;<font size="1"  face="verdana">
<c:if test="${std.pubplace ne ''}">
 <c:out value="${std.pubplace}:"/>
 </c:if>
<c:if test="${std.publisher ne ''}">
<c:out value="${std.publisher}"/> 
</c:if>
<c:if test="${std.yearPub ne ''}">
<c:out value=",${std.yearPub}"/>
  </c:if></font>
  
</td></tr>


<tr>
<c:if test="${std.callNo ne ''}">
<td class="addedit">Call No</td><td>:&nbsp;&nbsp;<font size="1"  face="verdana"><c:out value="${std.callNo}"/></font></td>
</c:if>

</tr>
<tr>
<td class="addedit">SubjectName</td><td>:&nbsp;&nbsp;<font size="1"  face="verdana"><c:out value="${std.subjectName}" /></font></td>
</tr>

<tr>

<c:if test="${std.deptName ne ''}">
<td class="addedit">Department</td><td>:&nbsp;&nbsp;<font size="1"  face="verdana"><c:out value="${std.deptName}"/></font></td>
</c:if>

</tr>

<c:if test="${std.location ne ''}">
<tr><td class="addedit">Location</td><td>:&nbsp;&nbsp;<font size="1"  face="verdana"><c:out value="${std.location}"/></font></td>
</tr></c:if>

<tr>

<td></td>

<c:if test="${std.pages ne ''}">
<td class="addedit">Material</td><td>:&nbsp;&nbsp;<font size="1"  face="verdana"><c:out value="${std.pages}" /></font> </td>
</c:if>
</tr>


<tr>

<td></td>

<c:if test="${std.availability eq 'YES'}">
<td class="addedit">Availability</td><td>:<font size="2"  face="verdana" color="green"></font>&nbsp;&nbsp;<font size="1"  face="verdana"><b><c:out value="${std.availability}" /></b></font></td>
</c:if>



<c:if test="${std.availability ne 'YES'}">
<td class="addedit">Availability</td><td>:<font size="2"  face="verdana" color="red">&nbsp;&nbsp;</font><font size="1"  face="verdana"><b><c:out value="${std.availability}" /></b></font></td>
</c:if>


</tr>

<tr><th colspan=3>----------------------------------------------------------------------------------------------------------------------------</th></tr>

</c:forEach></c:if></tr>

<c:if test="${NewArrivalDisplySize eq 0}">
<tr><td>
<p align="center"><h2>No Data Found</p></h2>
</td></tr>
</c:if>



<tr>
<td align="center" colspan=3>
<a href="<%= request.getContextPath() %>/newarrivals/NewArraivalsServlet?flag=loadMonthYear" id="bodysearch" title="Click Here to take NewArrivals">
<img src="<%= request.getContextPath() %>/iconImages/back.png" width="40" height="40" border="0" align="middle" ></a>
<br>
<font id="profileLink">BACK</font>
</td>
</tr>










 
</table> 


</html>
                                       
				                       