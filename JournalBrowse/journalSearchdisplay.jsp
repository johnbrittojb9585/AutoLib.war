<%@ include file="/jsp/common/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page language="java" errorPage="/Error/ErrorPage.jsp" import="java.io.*" import="java.util.*" import="java.lang.*" session="true" buffer="12kb" import="Common.Security" import="java.util.ArrayList"%>
<jsp:useBean id="bean" scope="page" class="Lib.Auto.JournalBrowse.JournalSearchbean"/>

<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/displaytag.css" />
<style>
.hidden 
{
 display: none;
}
</style>

<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/button_css.css" />

<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>


	<display:table name="sessionScope.JnlSearchArrylist" id="Searchbean" pagesize="20" 
		class="dataTable" sort="list" defaultsort="2" defaultorder="ascending"
		requestURI="/JournalBrowse/JournalSearch" export="true">
		
	    <display:setProperty name="basic.msg.empty_list" value=""/>
		<display:setProperty name="export.excel" value="true" />
		<display:setProperty name="export.csv" value="true" />				
		<display:setProperty name="export.pdf" value="false" />
				
		<display:setProperty name="export.excel.filename" value="JnlSearch.xls"></display:setProperty>
		<display:setProperty name="export.csv.filename" value="JnlSearch.csv"></display:setProperty>
		<display:setProperty name="export.pdf.filename" value="JnlSearch.pdf"></display:setProperty>
		
		
		
		<display:column property="jnlCode" href='JournalSearch' paramId="jnlcode" paramProperty="jnlCode" title="Code"  maxLength="8" sortable="true"> 		  
	    </display:column>
	   
	    <display:column property="jnlName"  sortable="true" title="Journal Name"  maxLength="200" >     	    
	    </display:column>
	    
		<display:column property="frequency" sortable="true" title="Frequency"  maxLength="25" >     
	    </display:column>
	   
	    <display:column property="country" sortable="true" title="Category"  maxLength="15" >   
	    </display:column>

		<display:column property="document" sortable="true" title="Type"  maxLength="10" > 	
	    </display:column>
			
    	<display:column property="language" title="Language"  maxLength="10" sortable="true">     	
		</display:column>
		
		<display:column property="department" title="Department"  maxLength="6" sortable="true">     	
		</display:column>
		
		<display:column property="location" title="Location"  maxLength="10" sortable="true">     	
		</display:column>
		
		<display:column property="url" title="URL"  maxLength="75" sortable="true" href='JournalSearch' paramId="url" paramProperty="url">     	
		</display:column>
				
<%-- 
		<display:column property="issn"   title="ISSN"  maxLength="25">   
		</display:column> --%>
		   
		<display:setProperty name="basic.empty.showtable" value="true" />
		
		
	</display:table>


<p align="center">
<input type=button name=New Class="submitButton"  Value=Back onclick='back()'>
</p>
<script>
function back()
{
	history.go(-1);
}
</script>




