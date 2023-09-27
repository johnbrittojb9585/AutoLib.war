

<%@ include file="/jsp/common/taglibs.jsp"%>

<script  src="<%= request.getContextPath()%>/script/campusAjax.js"></script>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/font.css" />   

<!-- DON"T CHANGE THIS DIV TAG ID VALUE -->
<div id="displayTag">
	<display:table name="sessionScope.AccountArrylist" id="RegisterBean" pagesize="20" 
		class="dataTable" sort="list" defaultsort="6"  defaultorder="descending"
		requestURI="/Account/gateresister.jsp">
		
		<c:set var="status" value="1"/>
		
		<display:column property="gmembercode" sortable="true" title="Member Code"  maxLength="25" > 	
	  
	    </display:column>
	   
	    <display:column property="gmembername"  sortable="true" title="Member Name"  maxLength="200" >     
	    
	    </display:column>
	    
	     <display:column property="cyear"  sortable="true" title="Year"  maxLength="200" >     
	    
	    </display:column>
	    
    	<display:column property="gdeptname" sortable="true" title="Dept Name"  maxLength="50"> 
    	
		</display:column>
		
		<display:column property="gcoursename" sortable="true" title="Course Name"  maxLength="50"> 
    	
		</display:column>
		<display:column property="gentrytime"  sortable="true" title="Entry Time"  maxLength="50"> 
    	
		</display:column>
		
		
		<display:column property="gdesignation" sortable="true" title="Designation"  maxLength="25" > 	
	    </display:column>
	    

	    
		
	<display:setProperty name="basic.empty.showtable" value="true" />
	</display:table>


</div>


