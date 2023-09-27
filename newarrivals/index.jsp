<%
String URole=session.getAttribute("UserRights").toString().trim();
if( URole.equalsIgnoreCase("4") || URole.equalsIgnoreCase("5") ||  URole.equalsIgnoreCase("8"))
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


<br>



<%
   response.setHeader("Pragma", "No-cache");
   response.setHeader("Cache-Control", "no-cache");
   response.setDateHeader("Expires", 0);
%>


<%
ArrayList sc=new ArrayList();
sc=(ArrayList)request.getAttribute("monthYear");
%>

<html>
<title>AutoLib</title>
<head>

</head>

<form name=NewArrival method="post" action=./NewArraivalsServlet>


<h2>New Arrivals</h2>
<table align="center" class="contentTable"  width="60%">
<tr><td>

<table align="center" width="50%">

<tr><td>&nbsp;</td></tr>


<tr><td Class="addedit">DocType</td><td>
<select name="docType" size="1" style="width: 130px" style="font-family: Verdana; font-size: 10pt">
      <option value="BOOK">BOOK</option>
      <option value="BOOK BANK">BOOK BANK</option>
	  <option value="NON BOOK">NON BOOK </option>
	  <option value="REPORT">REPORT</option>
	  <option value="THESIS">THESIS</option>
	  <option value="STANDARD">STANDARD</option>
	  <option value="PROCEEDING">PROCEEDING</option>	  
	  <option value="BACK VOLUME">BACK VOLUME</option>
	  
</select></td>


    

<td Class="addedit">Month</td><td>

<select name="Year" size="1" style="width: 130px">
    
	    <% 
			                           Iterator it=sc.iterator();
			                           
                                        while(it.hasNext()){
                                        	                                        	
                                        	NewArrivalsBean view=(NewArrivalsBean) it.next();                                        	
				                     
				                        String month=view.getMonth();
				                        String year=view.getYear();
				                        %>
				                       
				                     <%
				                       //out.println("<option  value="+curr+">" +curr+"</option>");
				                     
				                     out.println("<option  value="+month+"-"+year+">" +month+"-"+year+"</option>");
				                   
                                        }
                                       
				                        %>
	 
	  
</select></td>
</tr>

<tr><td Class="addedit">Title<td colspan=3><input type=text name=title size=50 maxlength="50" onKeydown="Javascript: if (event.keyCode==13) return SearchRec()"></td></tr>
  
 <tr> <td Class="addedit">Author<td colspan=3><input type=text name=author size=50 maxlength="50" onKeydown="Javascript: if (event.keyCode==13) return SearchRec()"></td></tr>



<tr>
<td Class="addedit">SubName</td><td colspan=4>
<select size="1" name="Subject" style="width: 325px">

<option value="ALL">ALL</option>
<c:if test="${SubjectSearchList ne null }" >
<c:forEach items="${SubjectSearchList}" var="std" varStatus="loop">
<option value="<c:out value="${std.subjectName}"/>"><c:out value="${std.subjectName}" /></option>
</c:forEach>
</c:if>
</select>
</td>
</select></td>
</tr>



</table>

<p align="center">
<input type=button name=Search Class="submitButton"  Value=Search onclick="SearchRec()">
</p>
</table>
<input type=hidden name=flag>
</form>

<script language=javascript>
function SearchRec(){
  document.NewArrival.flag.value="Search";
  document.NewArrival.submit();	
}
</script>
</html>