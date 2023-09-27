<%@ page language="java" errorPage="/Error/ErrorPage.jsp" import="java.io.*" import="java.util.*" session="true" buffer="12kb" import="Common.Security" import="java.util.ArrayList"%>
<%//Security.checkSecurity(1,session,response,request);%>

<script  src="<%= request.getContextPath()%>/script/campusAjax.js"></script>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/displaytag.css" />
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/button_css.css" />


<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>

<!-- DON"T CHANGE THIS DIV TAG ID VALUE -->
<div  id="displayTag">
	<display:table name="sessionScope.SearchArrylist" id="JournalAtlSearchbean" pagesize="20" 
		class="dataTable" sort="list" defaultsort="2" defaultorder="ascending"
		requestURI="/Journal_ArticleSearch/jnlAltsimpleSearch.jsp">
		
		<display:column property="jname"  title="Name"  maxLength="105" > 		   
	    </display:column>
	    
    	<display:column property="atitle" class="red" href='JNLArticleSearchServlet' paramId="ncode" paramProperty="atlno" title="Title"  maxLength="250"> 
		</display:column>
		
    	<display:column property="author" title="Author"  maxLength="80"> 
		</display:column>		
		
    	<display:column property="volno" title="Volume"  maxLength="80"> 
		</display:column>			

    	<display:column property="iyear" title="Year"  maxLength="80"> 
		</display:column>	
						
		<display:column property="asubject"  title="Subject"  maxLength="25" >     
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

