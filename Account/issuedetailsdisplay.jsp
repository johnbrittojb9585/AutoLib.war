<%@ include file="/jsp/common/taglibs.jsp"%>

<script  src="<%= request.getContextPath()%>/script/campusAjax.js"></script>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/displaytag.css" />
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/font.css" />

<!-- DON"T CHANGE THIS DIV TAG ID VALUE -->
<div id="displayTag">
	<display:table name="sessionScope.AccountArrylist" id="AccountBean" pagesize="20" 
		class="dataTable" sort="list" defaultsort="2"  defaultorder="ascending"
		requestURI="/Account/issuedetailsdisplay.jsp">	
		
		<display:column property="accno"  title="Access No"  maxLength="25" > 	
	  
	    </display:column>
	   
	    <display:column property="title"   title="Title"  maxLength="200" >     
	    
	    </display:column>
	    
    	<display:column property="author" title="Author Name"  maxLength="50"> 
    	
		</display:column>
		
		<display:column property="issuedt"  title="Issue Date"  maxLength="50"> 
    	
		</display:column>
		
		<display:column property="duedt"  title="Due Date"  maxLength="50"> 
    	
		</display:column>		
		
		<display:column property="dtype"  title="Document"  maxLength="25" > 	

	    </display:column>
	    
<%--       <display:column title="Renew" >  <!-- style="text-align:center" --> --%>
        
<%--             <c:url var="userRenewURL" value="AccountServlet"> --%>
<%-- 				<c:param name="document" value="${AccountBean.dtype}" /> --%>
<%-- 				<c:param name="accno" value="${AccountBean.accno}" /> --%>
<%-- 			</c:url>			 --%>
			
<%-- 			<c:if test="${AccountBean.dtype ne 'JOURNAL'}">    <!-- Journal cannot be Renewed --> --%>
<%-- 			<a href="${userRenewURL}" >Renew</a> --%>
<%-- 			</c:if> --%>
					
<%--         </display:column>	 --%>
		
		<display:setProperty name="basic.empty.showtable" value="true" />
	</display:table>

</div>


        
<%
String valid=request.getParameter("check");
if(valid!=null){

   if(valid.equals("YES")){

 String value=(String)request.getAttribute("strobj");
   	
    out.println("<html>");
	out.print("<head>");
	out.print("</head>");
	out.println("<BODY>");
	out.println("<table width='25%' >");
	out.print("<font color='#800000' size='3'>");
	out.print("<div Class='icon-ok'>");
   	out.println(""+value);
   	out.print("</div>");
   	out.print("</font>");
   	out.println("</table>");
   	out.println("</BODY>");
   	out.println("</html>");  	
   }
}

/**

<display:column  title="RK"  > 			
    <a href="AccountServlet?document=${AccountBean.dtype}&accno=${AccountBean.accno}" >Renew</a>		
</display:column>	


<display:column title="Renew"   style="text-align:center" >    

    <c:url var="staffURL" value="AccountServlet">
		<c:param name="document" value="${AccountBean.dtype}" />
		<c:param name="accno" value="${AccountBean.accno}" />
	</c:url>			
	<a href="${staffURL}" >Renew</a>
	
</display:column>	
*/



%>