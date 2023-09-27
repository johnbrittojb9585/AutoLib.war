<%-- <%@ include file="/jsp/common/taglibs.jsp"%> --%>
<%@ include file="/Tree/Searchdemoframeset.jsp"%>
<%@ page language="java" errorPage="/Error/ErrorPage.jsp" import="java.io.*" import="java.util.*" session="true" buffer="12kb" import="Common.Security" import="java.util.ArrayList"%>


<script  src="<%= request.getContextPath()%>/script/campusAjax.js"></script>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/displaytag.css" />
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/font.css" />   
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/button_css.css" />

<!-- DON"T CHANGE THIS DIV TAG ID VALUE -->
<div id="displayTag">
	<display:table name="sessionScope.AccountArrylist" id="RegisterBean" pagesize="20" 
		class="dataTable" sort="list" defaultsort="6"  defaultorder="ascending"
		requestURI="/Home/userid.jsp">
		
		<c:set var="status" value="1"/>
		
		
<%-- 	    <display:setProperty name="basic.msg.empty_list" value=""/> --%>
<%-- 		<display:setProperty name="export.excel" value="true" /> --%>
<%-- 		<display:setProperty name="export.csv" value="true" />				 --%>
<%-- 		<display:setProperty name="export.pdf" value="false" /> --%>
				
<%-- 		<display:setProperty name="export.excel.filename" value="todayIssuedDetails.xls"></display:setProperty> --%>
<%-- 		<display:setProperty name="export.csv.filename" value="todayIssuedDetails.csv"></display:setProperty> --%>
<%-- 		<display:setProperty name="export.pdf.filename" value="todayIssuedDetails.pdf"></display:setProperty> --%>
	
		
		
		<display:column property="gmembercode"  title="Member Code"  maxLength="25" > 	
	  
	    </display:column>
	   
	    <display:column property="gmembername"   title="Member Name"  maxLength="200" >     
	    
	    </display:column>
	    
	     <display:column property="cyear"   title="Year"  maxLength="200" >     
	    
	    </display:column>
	    
    	<display:column property="gdeptname"  title="Dept Name"  maxLength="50"> 
    	
		</display:column>
		
		<display:column property="gcoursename"  title="Course Name"  maxLength="50"> 
    	
		</display:column>
		<display:column property="gentrytime"  title="Entry Time"  maxLength="50"> 
    	
		</display:column>
		
		
		<display:column property="gdesignation"  title="Designation"  maxLength="25" > 	
	    </display:column>
	    

	    
		
	<display:setProperty name="basic.empty.showtable" value="true" />
	</display:table>


</div>


<p align="center">
<input type=button name=New Class="submitButton"  Value=Back onclick='back()'>
</p>
<script>
function back()
{
	history.go(-1);
}
</script>