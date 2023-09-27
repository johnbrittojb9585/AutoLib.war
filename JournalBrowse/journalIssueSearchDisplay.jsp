<%-- <%@ include file="/Tree/Searchdemoframeset.jsp"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>


<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/displaytag.css" />

<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/button_css.css" />



	<display:table name="sessionScope.JnlIssueSearchList" id="Searchbean" pagesize="20" 
		class="dataTable" sort="list" defaultsort="2" defaultorder="ascending"
		requestURI="/JournalBrowse/JournalSearch" export="true">
		
	    <display:setProperty name="basic.msg.empty_list" value=""/>
		<display:setProperty name="export.excel" value="true" />
		<display:setProperty name="export.csv" value="true" />				
		<display:setProperty name="export.pdf" value="false" />
				
		<display:setProperty name="export.excel.filename" value="JnlSearch.xls"></display:setProperty>
		<display:setProperty name="export.csv.filename" value="JnlSearch.csv"></display:setProperty>
		<display:setProperty name="export.pdf.filename" value="JnlSearch.pdf"></display:setProperty>
		
		
		
		<display:column property="jnlIssueAccNo" href='JournalSearch' paramId="accno" paramProperty="jnlIssueAccNo" title="Code"  maxLength="25" > 		  
	    </display:column>
	   
	    <display:column property="jnlName"  sortable="true" title="Journal Name"  maxLength="200" >     	    
	    </display:column>
	    
		<display:column property="issueYear" sortable="true" title="Year"  maxLength="25" >     
	    </display:column>
	    
	    <display:column property="issueVolume" sortable="true" title="Volume No"  maxLength="25" >   
	    </display:column>

		<display:column property="issueNo" sortable="true" title="Issue No"  maxLength="25" > 	
	    </display:column>
			
	    <display:column property="issueMonth" sortable="true" title="Month"  maxLength="25" >   
	    </display:column>
	    			
    	<display:column property="receiveDate" sortable="true" title="Received Date"  maxLength="25">     	
		</display:column>

		<display:column property="availability"   title="Status"  maxLength="25">   
		</display:column>
		   
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
 --%>