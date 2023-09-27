<%@ page language="java" errorPage="/Error/ErrorPage.jsp" import="java.io.*" import="java.util.*" session="true" buffer="12kb" import="Common.Security" import="java.util.ArrayList"%>
<%//Security.checkSecurity(1,session,response,request);%>
<script  src="<%= request.getContextPath()%>/script/campusAjax.js"></script>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>

<!-- DON"T CHANGE THIS DIV TAG ID VALUE -->
<div id="displayTag">

<display:table name="sessionScope.SearchArrylist" id="questionSearchBean" pagesize="20" 
		class="dataTable" sort="list" defaultsort="2" defaultorder="ascending"
		requestURI="/QBSearch/simpleSearch.jsp" export="true" >
			
     	<display:setProperty name="basic.msg.empty_list" value=""/>
		<display:setProperty name="export.excel" value="true" />
		<display:setProperty name="export.csv" value="true" />				
		<display:setProperty name="export.pdf" value="false" />
				
		<display:setProperty name="export.excel.filename" value="SimpSearch.xls"></display:setProperty>
		<display:setProperty name="export.csv.filename" value="SimpSearch.csv"></display:setProperty>
		<display:setProperty name="export.pdf.filename" value="SimpSearch.pdf"></display:setProperty>
			
		<display:column property="qcode"  href='QBSearchServlet' paramId="accno" paramProperty="qcode" title="QB Code"  maxLength="10" > 		   
	   </display:column>
	   
	   <display:column property="subcode" sortable="true" title="Subject Code"  maxLength="50"> 
	   </display:column>
		
	   <display:column property="sname"  title="Subject Name"  maxLength="100" >     
 	   </display:column>
 	    
	   <display:column property="dname"  title="Dept Name"  maxLength="150" >     
	   </display:column>
				
	   <display:column property="qcourse"  title="Course Name"  maxLength="100">   
	   </display:column> 
	   
	   <display:column property="remarks2"  title="Course Major"  maxLength="100">   
	   </display:column> 
	   
	  
	   <display:column property="qyear"  title="Year"  maxLength="4">   
	   </display:column> 
	  
	   
	   <display:column property="qmonth"  title="Month"  maxLength="10">   
	   </display:column> 
	   
	  
	  <display:column property="remarks1"  title="Semester"  maxLength="5">   
	   </display:column> 
	       
	       <display:setProperty name="basic.empty.showtable" value="true" />
	   
	</display:table>

</div>
</center>
<p align="center">
<input type=button name=New Class="submitButton"  Value=Back onclick='back()'>
</p>

<script>
function back()
{
	location.href="./index.jsp";
}
</script>


