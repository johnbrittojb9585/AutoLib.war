<%@ include file="/jsp/common/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page language="java" errorPage="/Error/ErrorPage.jsp" import="java.io.*" import="java.util.*" session="true" buffer="12kb" import="Common.Security" import="java.util.ArrayList"%>
<%//Security.checkSecurity(1,session,response,request);%>
<jsp:useBean id="bean" scope="page" class="Lib.Auto.Advanced.Adsearchbean"/>

<style>
.hidden 
{
 display: none;
}
</style>



<%
ArrayList sc=new ArrayList();
try{
sc=(ArrayList)bean.getAl();
//out.print("<table>");
for(int i=0; i<sc.size();i++){
	%>
<tr>
	<script language=javascript>
	      document.write("<td ><a href='?doc=<%=sc.get(i)%>'>"+"<%=sc.get(i)%>" +"</a></td>");
	     document.write("<td >"+"(" +"</td>");
		 document.write("<td>"+"<%=sc.get(++i)%>" +"</td>");
		 document.write("<td >"+")" +"</td>");
		 
	 
		</script>	
	<%
}
}catch (Exception e) {out.println(e.toString());}
finally{
	 sc.clear();
}

%>


<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>


<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/displaytag.css" />
<%-- <script  src="<%= request.getContextPath()%>/script/campusAjax.js"></script> --%>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/button_css.css" />


<!-- DON"T CHANGE THIS DIV TAG ID VALUE -->
<div id="displayTag">
	<display:table name="sessionScope.BrowseArrylist" id="Searchbean" pagesize="20" 
		class="dataTable" sort="list" defaultsort="2" defaultorder="ascending"
		requestURI="/Browse/browseSearch.jsp" export="true">
		
	    <display:setProperty name="basic.msg.empty_list" value=""/>
		<display:setProperty name="export.excel" value="true" />
		<display:setProperty name="export.csv" value="true" />				
		<display:setProperty name="export.pdf" value="false" />
				
		<display:setProperty name="export.excel.filename" value="BrowSearch.xls"></display:setProperty>
		<display:setProperty name="export.csv.filename" value="BrowSearch.csv"></display:setProperty>
		<display:setProperty name="export.pdf.filename" value="BrowSearch.pdf"></display:setProperty>
		
		<display:caption media="html">
			<c:if test="${Searchbean.document eq 'BOOK' || Searchbean.document eq 'Article'}">
			<c:url var="APAView" value="BrowseSearch">
				<c:param name="format" value="APA" />
				<c:param name="doc" value="${Searchbean.document}" />
			</c:url> 	
			
			<a href="${APAView}">APA Format</a>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			</c:if>
			<c:if test="${Searchbean.document eq 'BOOK'}">
			<c:url var="MarcView" value="BrowseSearch">
				<c:param name="format" value="Marc" />
				<c:param name="doc" value="${Searchbean.document}" />
			</c:url> 	
			<a href="${MarcView}">Marc Format</a>
			</c:if>
		</display:caption>
		
		<c:if test="${Searchbean.document ne 'Article' && Searchbean.format eq ''}">
		
<%-- 		<display:column property="accno" href='BrowseSearch' paramId="accno" paramProperty="accno" title="Access No"  maxLength="25" > 		   --%> --%>
<%-- 	    </display:column> --%>
	   
 	<display:column sortable="true" title="Title" property="title" class="hidden" headerClass="hidden"  maxLength="200">    --%>
 	   </display:column>
 	       <display:column title="Access No"  maxLength="45" media="html">
       		<c:url var="accnosearch" value="BrowseSearch">
				<c:param name="accno" value="${Searchbean.accno}" />
				<c:param name="document" value="${Searchbean.document}" />
			</c:url> 	
			<a href="${accnosearch}">${Searchbean.accno}</a>	   
	   </display:column>
 	   
 	    <display:column property="title"  sortable="true" title="Title"  maxLength="200" >
 	    </display:column>


        
	    
    	<display:column property="author" sortable="true" title="Author Name"  maxLength="50"> 
		</display:column>
		
		<display:column property="edition"  title="edition"  maxLength="25" >     
	    </display:column>
	    
	   <display:column property="callno"  title="Call no"  maxLength="25" >     
	    </display:column>
	    
	    <display:column property="location"  title="Location"  maxLength="50" >   
	    </display:column>
	
		<display:column property="availability"   title="Status"  maxLength="25">   
		</display:column>
				
		<display:column property="document"  title="Document"  maxLength="25" > 	
	    </display:column>
	    
	    </c:if>
	    
	    <c:if test="${Searchbean.format eq 'APA' && Searchbean.document eq 'BOOK'}">
	    
	    <display:column property="accno"  title="Access No"  maxLength="25">
	   </display:column>
	    	    
	   <display:column title="APA Format" maxLength="1000">   
	   		${Searchbean.author}.(${Searchbean.year}).${Searchbean.title}.(${Searchbean.volno}).${Searchbean.place}:${Searchbean.publisher}.
	   </display:column>
	   
	   </c:if>

	<c:if test="${Searchbean.format eq 'APA' && Searchbean.document eq 'Article'}">
	    
	     <display:column property="accno"  title="Article No"  maxLength="35">
	   </display:column>
	   
	   <display:column title="APA Format" maxLength="1000">   
	   		${Searchbean.author}.(${Searchbean.year}).${Searchbean.title}.${Searchbean.journal},${Searchbean.volno}(${Searchbean.issueNo}),${Searchbean.pages}.
	   </display:column>
	   
	   </c:if>

	<c:if test="${Searchbean.format eq 'Marc' && Searchbean.document eq 'BOOK'}">
	    
	    <display:column property="accno"  title="Access No"  maxLength="25">
	   </display:column>
	    	    
	   <display:column title="Marc Format" maxLength="1000" media="html">   
	   		245&nbsp;&nbsp;10&nbsp;&nbsp;$a&nbsp;&nbsp;${Searchbean.title}/$c&nbsp;&nbsp;${Searchbean.sres}</br>
	   		100&nbsp;&nbsp;1&nbsp;&nbsp;&nbsp;&nbsp;$a&nbsp;&nbsp;${Searchbean.author}</br>
	   		082&nbsp;&nbsp;0&nbsp;&nbsp;&nbsp;&nbsp;$a&nbsp;&nbsp;${Searchbean.callno}</br>
	   		260&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;$a&nbsp;&nbsp;${Searchbean.place}&nbsp;&nbsp;$b&nbsp;&nbsp;${Adsearchbean.publisher},$c&nbsp;&nbsp;${Adsearchbean.year}</br>
	   		650&nbsp;&nbsp;0&nbsp;&nbsp;&nbsp;&nbsp;$a&nbsp;&nbsp;${Searchbean.subject}</br>
	   		300&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;$a&nbsp;&nbsp;${Searchbean.pages}&nbsp;&nbsp;$c&nbsp;&nbsp;${Adsearchbean.phyDesc}
	   </display:column>
	   
	   <display:column media="xml">   
	   		245 10 $a ${Searchbean.title}/$c ${Searchbean.sres}
	   </display:column>
	   
	   <display:column media="xml">   
	   		100 1 $a ${Searchbean.author}
	   </display:column>
	   
	    <display:column media="xml">   
	   		082 0 $a  ${Searchbean.callno}
	   </display:column>
	   
	   <display:column media="xml">   
	   		260 $a ${Searchbean.place} $b ${Searchbean.publisher},$c ${Searchbean.year}
	   </display:column>
	   
	   <display:column media="xml">   
	   		650 0 $a ${Searchbean.subject}</br>
	   </display:column>
	   
	   <display:column media="xml">   
	   		300 $a ${Searchbean.pages} $c ${Searchbean.phyDesc}
	   </display:column>
	   
	   </c:if>

	   
	   <c:if test="${Searchbean.document eq 'Article' && Searchbean.format eq ''}">
		
       <display:column title="Article No"  maxLength="35" media="html">
       		<c:url var="accnosearch" value="BrowseSearch">
				<c:param name="accno" value="${Searchbean.accno}" />
				<c:param name="document" value="${Searchbean.document}" />
			</c:url> 	
			<a href="${accnosearch}">${Searchbean.accno}</a>	   
	   </display:column>
	   
	   <display:column property="accno"  title="Article No"  maxLength="25" class="hidden" headerClass="hidden">
	   </display:column>
	   
	   <display:column sortable="true" title="Journal" property="journal" maxLength="200">   
	   </display:column>
	   
	   <display:column sortable="true" title="Article Title" property="title" class="hidden" headerClass="hidden"  maxLength="200">   
	   </display:column>
	   
	   <display:column sortable="true" title="Article Title"  maxLength="200" media="html">   
	   <c:set var="string1" value="${Searchbean.title}"/>
	   <c:set var="searchstr" value="${Searchbean.searchstring}"/>
	   <%
    	String str="",strsearch="",finalstring="",repstr=""; 
		str = (String)pageContext.getAttribute("string1");
		strsearch = (String)pageContext.getAttribute("searchstr");
		repstr = "<span style='background-color:yellow;'>"+strsearch+"</span>";
		finalstring = str.replaceAll("(?i)"+strsearch, repstr);
    	out.println(finalstring);
 	  %> 
	   </display:column>
	    
       <display:column property="author" sortable="true" title="Author Name"  maxLength="50"> 
	   </display:column>
		
	   <display:column property="volno"  title="Volume"  maxLength="25" >     
 	   </display:column>
	   
	   <display:column property="year"  title="Year"  maxLength="50">     
	   </display:column>
	   
	   <display:column property="subject" title="Subject"> 
	    </display:column>
	   
	   </c:if>
	   
	   
	   
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
