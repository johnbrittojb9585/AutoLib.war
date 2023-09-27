<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>

<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/displaytag.css" />
<script  src="<%= request.getContextPath()%>/script/campusAjax.js"></script>

<%


%>
<!-- DON"T CHANGE THIS DIV TAG ID VALUE -->
<div id="displayTag">
	<display:table name="sessionScope.AdsearchArrylist" id="Adsearchbean" pagesize="1" 
		class="dataTable" sort="list" defaultsort="2" defaultorder="ascending"
		requestURI="/Advanced/advancedFullView.jsp">
		
<tr>
		<td >
		<div align="left">
		<display:column  title="Access No"  maxLength="25" > 	
	  
	   </display:column>
	   </div>
	   </td>
	   <td>
		<display:column	property="accno"   maxLength="25" > 	
	  
	   </display:column>
	   </td>

	  <td>
	  <div align="left">
		<display:column  title="Title"  maxLength="200" >     
	    
	    </display:column>
		</div >
		</td>
		<td>
		<display:column property="title"   maxLength="200" >     
	    
	    </display:column>
		</td>
	  <td>
	    <div align="left">   
    	<display:column  title="Author Name"  maxLength="50"> 
    	
		</display:column>
         </div> 
		 </td>
		 
		  <td>
		  <display:column property="author"   maxLength="50"> 
    	
		</display:column>
		  
	  </td>
	
	  <td>
	    <div align="left">  	
		<display:column  title="Call no"  maxLength="25" >     
	   </display:column>
	    </div>
	    </td>
  <td>
<display:column property="callno"   maxLength="25" >     
	   </display:column>
  </td>



		
	  <td>
	    <div align="left">  	
	   <display:column  title="Location"  maxLength="50" >     
	   
	   </display:column>
	</div>
	</td>
	<td>
	<display:column property="location"  maxLength="50" >     
	   
	   </display:column>
	
	</td>		
		
		
				
				
				
		<td>
	    <div align="left">  			
				
		<display:column  title="Status"  maxLength="25">   
			</display:column>
</div>
</td>

<td>
<display:column property="availability"   maxLength="25">   
			</display:column>
</td>

<td>
	    <div align="left">  

			<display:column  title="Document"  maxLength="25" > 	
	   </display:column>
	   
	</div>
	</td>
	
	<td>
	
			<display:column property="document"   maxLength="25" > 	
	   </display:column>
	   
	</td>
	
	
	   
	   
	 </tr>  
	   
	   
	   
	   
		<display:setProperty name="basic.empty.showtable" value="true" />
	</display:table>

</div>



