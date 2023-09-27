<%@ include file="/Tree/demoFrameset.jsp"%>
<%@ page language="java" errorPage="/Error/ErrorPage.jsp" import="java.io.*" import="java.util.*" session="true" buffer="12kb" import="Common.Security" import="java.util.ArrayList"%>
<%//Security.checkSecurity(1,session,response,request);%>

<%@ include file="/jsp/common/taglibs.jsp"%>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/displaytag.css" />
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/font.css" />
<script  src="<%= request.getContextPath()%>/script/campusAjax.js"></script>


<!-- DON"T CHANGE THIS DIV TAG ID VALUE -->
<div id="displayTag">
	<display:table name="sessionScope.AccountArrylist" id="AccountBean" pagesize="20" 
		class="dataTable" sort="list" defaultsort="2"  defaultorder="ascending"
		requestURI="/Account/issuedetails_new.jsp">
		
		<c:set var="status" value="1"/>
		
		<display:column property="accno"  title="Access No"  maxLength="25" > 	
	  
	    </display:column>
	   
	    <display:column property="title"  sortable="true" title="Title"  maxLength="200" >     
	    
	    </display:column>
	    
    	<display:column property="author" sortable="true" title="Author Name"  maxLength="50"> 
    	
		</display:column>
		
		<display:column property="issuedt"  title="Issue Date"  maxLength="50"> 
    	
		</display:column>
		<display:column property="duedt"  title="Due Date"  maxLength="50"> 
    	
		</display:column>
		
		
		<display:column property="dtype"  title="Document"  maxLength="25" > 	
	    </display:column>
	    
	   <display:column value='Renew' href='AccountServlet' paramId="accno" paramProperty="accno"   maxLength="25" > 	
	  
	    </display:column>
	    
		
		<display:setProperty name="basic.empty.showtable" value="true" />
	</display:table>


</div>



