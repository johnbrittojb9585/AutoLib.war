<%@ include file="/jsp/common/taglibs.jsp"%>
 <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<script  src="<%= request.getContextPath()%>/script/campusAjax.js"></script>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/font.css" />

<!-- DON"T CHANGE THIS DIV TAG ID VALUE -->
<form name="query" method="post"  action=./SuggestionDisplayServlet>
		 <input type="hidden" name="flag">
  		<input type="hidden" name="sugNo">
		  <input type="hidden" name="commentText">
		  <input type="hidden" name="status">
	<c:set var="i" value="0"/>

<div id="displayTag">

	<display:table name="requestScope.suggestionSearchList" id="searchBean" pagesize="30" 
		class="dataTable" sort="list" defaultsort="2" defaultorder="descending"
		requestURI="/counterlistBean/SuggestionSearchdisplay.jsp">

		<display:column>

		
<%-- 				<input type="checkbox" name="selectedIds[]" checked value='<c:out value="${counterlistBean.accessNo}"/>'> --%>

<%-- 	            <input type="hidden" name="baccno[]" value='<c:out value="${counterlistBean.accessNo}"/>'>	  --%>
<%-- 	            <input type="hidden" name="btitle[]" value='<c:out value="${counterlistBean.title}"/>'>	    	             --%>
<%-- 	            <input type="hidden" name="bauthor[]" value='<c:out value="${counterlistBean.authorName}"/>'>	        		             --%>
<%-- 	            <input type="hidden" name="bpublisher[]" value='<c:out value="${counterlistBean.publisher}"/>'>	        	    		                       				 --%>
<%-- 	            <input type="hidden" name="bavailability[]" value='<c:out value="${counterlistBean.availability}"/>'>	        	    		             --%>
<%-- 	            <input type="hidden" name="bdocument[]" value='<c:out value="${counterlistBean.document}"/>'>	 --%>
	            
<%--  	            <input type="hidden" name="bissdate[]" value='<c:out value="${counterlistBean.issueDate}"/>'>	                        --%>
<%--  	            <input type="hidden" name="bduedate[]" value='<c:out value="${counterlistBean.dueDate}"/>'>	 --%>
<%-- <%--  	            <input type="hidden" name="blfine[]" value='<c:out value="${counterlistBean.temp}"/>'>	 --%>
 	            
		</display:column>
				   
		<display:column property="memberCode"  title="Member Code"   > 	
	    </display:column>
	    
		
<%-- 		<display:column property="publisher" title="Member Name"  maxLength="50">  --%>
<%-- 		</display:column> --%>
		
		<display:column property="rcDate"  title="Request Date"  > 
	    </display:column>
		
		<display:column property="doc" title="Request For" > 
		</display:column>
		
		<display:column property="sugName" title="Requested Detail"  > 
		</display:column>
		
		<display:column property="actionTaken" title="Action Taken"  > 
		</display:column>
		   
		<display:column title="Action"> 
		<textarea type="text" size="1000" cols="43" name="comment[]" /></textarea></br>
		<input type="button"  Class="submitButton" value="Submit" onclick='Reply(<c:out value='${searchBean.sugNo}'/>,<c:out value='${i}'/>)'>
		<input type="button"  Class="submitButton" value="Delete" onclick='Del_rec(${searchBean.sugNo})'>
		</display:column>
		
		<display:column  title="Status"  maxLength="1000"> 
		
		<c:set var="doctype" value="Pending,Processing,Completed"/>
<select  name="type[]" style="width: 175px">
    <c:forEach items="${fn:split(doctype, ',')}" var="document">
        <option value="${document}" ${searchBean.status == document ? 'selected' : ''}>${document}</option>
    </c:forEach>
</select>
		


		 
<%--      value="Active" ${searchBean.isActionTaken=='1'?'checked':''}/> --%>
		</display:column>
		   
		<display:setProperty name="basic.empty.showtable" value="true" />
		<c:set var="i" value="${i+1}"/>
	</display:table>

</div>
</form>
<script language=javascript>

function Del_rec(val){
	alert(val)
	document.query.sugNo.value = val;
	document.query.flag.value="delete";
	document.query.action="./SuggestionDisplayServlet";
	document.query.submit();
	}
function Reply(val,val2){

	document.query.sugNo.value = val;
// document.query.status.value = status;
	document.query.commentText.value = document.getElementsByName('comment[]')[val2].value;	
	document.query.status.value = document.getElementsByName('type[]')[val2].value;
	document.query.flag.value="reply";
	document.query.action="./SuggestionDisplayServlet";

	document.query.submit();
	}
	
</script>

