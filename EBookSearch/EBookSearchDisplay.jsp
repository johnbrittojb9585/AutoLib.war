<%@ page language="java" errorPage="/Error/ErrorPage.jsp" import="java.io.*" import="java.util.*" session="true" buffer="12kb" import="Common.Security" import="java.util.ArrayList"%>
<%//Security.checkSecurity(1,session,response,request);%>
<jsp:useBean id="bean" scope="page" class="Lib.Auto.EBook.EBookBean"/>

<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/displaytag.css" />
<%-- <script  src="<%= request.getContextPath()%>/script/campusAjax.js"></script> --%>

<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>

<!-- DON"T CHANGE THIS DIV TAG ID VALUE -->
<div id="displayTag">
	<display:table name="sessionScope.SearchArrylist" id="EBookBean" pagesize="20" 
		class="dataTable" sort="list" defaultsort="2" defaultorder="ascending"
		requestURI="/EBookSearch/EBookSearch.jsp" export="true">
	
		<display:setProperty name="basic.msg.empty_list" value=""/>
		<display:setProperty name="export.excel" value="true" />
		<display:setProperty name="export.csv" value="true" />				
		<display:setProperty name="export.pdf" value="false" />
				
		<display:setProperty name="export.excel.filename" value="EBookSearch.xls"></display:setProperty>
		<display:setProperty name="export.csv.filename" value="EBookSearch.csv"></display:setProperty>
		<display:setProperty name="export.pdf.filename" value="EBookSearch.pdf"></display:setProperty>
			
       <display:column property="accessNo"  href='EBookSearchServlet' paramId="AccNoSearch" paramProperty="accessNo" title="Access No"  maxLength="25"> 		   
	   </display:column>
		   
	   <display:column property="callNo"  title="Call no"  maxLength="25" >     
 	   </display:column>
	   
	   <display:column property="title"  sortable="true" title="Title"  maxLength="200" >      
	   </display:column>
	   
	   <display:column property="edition"  title="Edition"  maxLength="50" >     
	   </display:column>
	   
	   <display:column property="url"  title="ISBN"  maxLength="25">
	   </display:column>
	   
	    
	   	   
       <display:column property="authorName" sortable="true" title="Author Name"  maxLength="50"> 
	   </display:column>
			     
	   <display:column property="pubName"  title="Publisher"  maxLength="25">   
	   </display:column>
	   
		
	   <display:column property="yop"  title="Year"  maxLength="25" > 	
	   </display:column>
	   
	   <display:column property="pages"  title="Pages"  maxLength="25" > 	
	   </display:column>
	   
	   <display:column property="subName"  title="Subject"  maxLength="25" > 	
	   </display:column>
	   
	   <display:column property="isbn"  href='EBookSearchServlet' paramId="AccNoSearch" paramProperty="accessNo" title="URL"  maxLength="25"> 		   
	   </display:column>
	   
	   <%-- <display:column property="deptName"  title="Department"  maxLength="25" > 	
	   </display:column> --%>
	   
	   <%-- <display:column property="type"  title="Course"  maxLength="25" > 	
	   </display:column> --%>   
	     
	   
	   
	   <display:setProperty name="basic.empty.showtable" value="true" />
	   
	</display:table>
	<script type="text/javascript">
	
	</script>

</div>



