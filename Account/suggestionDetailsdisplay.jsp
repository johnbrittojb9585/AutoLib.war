
<%@ page language="java" errorPage="/Error/ErrorPage.jsp" import="Lib.Auto.Account.AccountBean"  import="java.util.*"  session="true" buffer="12kb" import="Common.Security"%>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/font.css" />
<jsp:useBean id="beanobject" scope="request"  class="Lib.Auto.Account.AccountBean"  type="Lib.Auto.Account.AccountBean">
</jsp:useBean>

<html>
<%
   response.setHeader("Pragma", "No-cache");
   response.setHeader("Cache-Control", "no-cache");
   response.setDateHeader("Expires", 0); 
%>

<head>
<title>AutoLib Software Systems</title>
<meta http-equiv="pragma" content="no-cache"/>

<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<%@ include file="/taglibs.jsp"%>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/displaytag.css" />
<script  src="<%= request.getContextPath()%>/script/campusAjax.js"></script>


<!-- DON"T CHANGE THIS DIV TAG ID VALUE -->
<div id="displayTag">
	<display:table name="sessionScope.AccountArrylist" id="AccountBean" pagesize="20" 
		class="dataTable" sort="list" defaultsort="2"  defaultorder="ascending"
		requestURI="/Account/suggestionDetails.jsp">		
		
		<display:column property="resdat"  title="Requested Date"  maxLength="25" > 	
	  
	    </display:column>
	   
	    <display:column property="dtype"   title="Request For"  maxLength="200" >     
	    
	    </display:column>
	    
    	<display:column property="detail"  title="Details"  maxLength="50"> 
    	
		</display:column>
		
		<display:column property="action"  title="Action Taken"  maxLength="50"> 
    	
		</display:column>
		
		<display:column property="availability"  title="status"  maxLength="50"> 
    	
		</display:column>
		
	
	   
		<display:setProperty name="basic.empty.showtable" value="true" />
	</display:table>

</div>



