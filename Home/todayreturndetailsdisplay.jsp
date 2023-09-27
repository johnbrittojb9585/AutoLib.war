<%@ include file="/jsp/common/taglibs.jsp"%>

<%-- <script  src="<%= request.getContextPath()%>/script/campusAjax.js"></script> --%>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/displaytag.css" />
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/font.css" />
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<!-- DON"T CHANGE THIS DIV TAG ID VALUE -->
<div id="displayTag">
	<display:table name="sessionScope.ReturnReportArrylist" id="reportbean" pagesize="20" 
		class="dataTable" sort="list" defaultsort="2"
		 requestURI="/Home/todayreturndetails.jsp"  export="true">	
		
		
		
		
	    <display:setProperty name="basic.msg.empty_list" value=""/>
		<display:setProperty name="export.excel" value="true" />
		<display:setProperty name="export.csv" value="true" />				
		<display:setProperty name="export.pdf" value="false" />
				
		<display:setProperty name="export.excel.filename" value="todayReturnedDetails.xls"></display:setProperty>
		<display:setProperty name="export.csv.filename" value="todayReturnedDetails.csv"></display:setProperty>
		<display:setProperty name="export.pdf.filename" value="todayReturnedDetails.pdf"></display:setProperty>
	
			
		<display:column property="ucode"  title="User&nbsp;ID"  maxLength="25" > 	
	  
	    </display:column>
	    
		<display:column property="uname"  title="User&nbsp;Name"  maxLength="25" > 	
	  
	    </display:column>
	    
	    <display:column property="accno"  title="Access&nbsp;No"  maxLength="25" > 	
	  
	    </display:column>
	    <display:column property="title"  title="Title"  maxLength="150" > 	
	  
	    </display:column>
		
		<display:column property="authorName"  title="Author&nbsp;Name"  maxLength="150" > 	
	  
	    </display:column>
	   
	    <display:column property="issueDate"   title="Issue&nbsp;Date"  maxLength="200" >     
	    
	    </display:column>
	    
    	<display:column property="dueDate" title="Due&nbsp;Date"  maxLength="25"> 
    	
		</display:column>
		
		<display:column property="docType"  title="Document"  maxLength="25"> 
    	
		</display:column>
		
		<display:column property="staffCode"  title="Staff&nbsp;Code"  maxLength="50"> 
    	
		</display:column>		
		
		
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

%>

<p align="center">
<input type=button name=New Class="submitButton"  Value=Back onclick='back()'>
</p>
<script>
function back()
{
	history.go(-1);
}
</script>