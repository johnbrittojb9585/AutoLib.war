<%@ include file="/jsp/common/taglibs.jsp"%>

<script  src="<%= request.getContextPath()%>/script/campusAjax.js"></script>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/font.css" />

<!-- DON"T CHANGE THIS DIV TAG ID VALUE -->
<div id="displayTag">

	<display:table name="requestScope.BulkCounterList" id="counterlistBean" pagesize="30" 
		class="dataTable" sort="list" defaultsort="2" defaultorder="ascending"
		requestURI="/Bulk_Counter/CounterListdisplay.jsp">

		<display:column>
				<input type="checkbox" name="selectedIds[]" checked value='<c:out value="${counterlistBean.accessNo}"/>'>

	            <input type="hidden" name="baccno[]" value='<c:out value="${counterlistBean.accessNo}"/>'>	 
	            <input type="hidden" name="btitle[]" value='<c:out value="${counterlistBean.title}"/>'>	    	            
	            <input type="hidden" name="bauthor[]" value='<c:out value="${counterlistBean.authorName}"/>'>	        		            
	            <input type="hidden" name="bpublisher[]" value='<c:out value="${counterlistBean.publisher}"/>'>	        	    		                       				
	            <input type="hidden" name="bavailability[]" value='<c:out value="${counterlistBean.availability}"/>'>	        	    		            
	            <input type="hidden" name="bdocument[]" value='<c:out value="${counterlistBean.document}"/>'>	
	            
 	            <input type="hidden" name="bissdate[]" value='<c:out value="${counterlistBean.issueDate}"/>'>	                       
 	            <input type="hidden" name="bduedate[]" value='<c:out value="${counterlistBean.dueDate}"/>'>	
<%--  	            <input type="hidden" name="blfine[]" value='<c:out value="${counterlistBean.temp}"/>'>	 --%>
 	            
		</display:column>
				   
		<display:column property="accessNo"  title="Access No"  maxLength="25" > 	
	    </display:column>
	    
    	<display:column property="authorName" title="Member Code"  maxLength="50"> 
		</display:column>
		
		<display:column property="publisher" title="Member Name"  maxLength="50"> 
		</display:column>
		
		<display:column property="title"  title="Title"  maxLength="100" > 
	    </display:column>
		
		<display:column property="availability" title="Status"  maxLength="50"> 
		</display:column>
		
		<display:column property="document" title="Document Type"  maxLength="50"> 
		</display:column>
		
		<display:column property="issueDate" title="Issue Date"  maxLength="10"> 
		</display:column>
		
		<display:column property="dueDate" title="Due Date"  maxLength="10"> 
		</display:column>
		   
		<display:column title="Fine Amount"> 
		<input type="text" size="10" name="blfine[]" value='<c:out value="${counterlistBean.temp}"/>'>
		</display:column>
		   
		<display:setProperty name="basic.empty.showtable" value="true" />
		
	</display:table>

</div>



