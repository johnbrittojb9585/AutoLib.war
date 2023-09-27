<%@ include file="/Tree/Searchdemoframeset.jsp"%>
<jsp:useBean id="bean" scope="page" class="Lib.Auto.Advanced.Adsearchbean"/>
<div style="padding: 0px 0px 0px 0px;">

<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/button_css.css" />
		<jsp:include page="advancedSearchdisplay.jsp" flush="true" />
	</div>
<%-- 		<%@ include file="/Tree/back.jsp"%> --%>
		
		  <script language="JavaScript">
		  
		  
		  function Print_Report()
{

 	 	
			winpopup=window.open("<%=request.getContextPath()%>/frameset?__report=/Report/search.rptdesign&query_name="+"<%=bean.getsqlquery()%>");
			


}
		  
		  </script>