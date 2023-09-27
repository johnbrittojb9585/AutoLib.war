<%@ include file="/Tree/Searchdemoframeset.jsp"%>
<%@ page language="java"  session="true" buffer="12kb" import="Lib.Auto.Advanced.Adsearchbean" import="Common.Security" import="java.util.*"%>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/button_css.css" />

<jsp:useBean id="beanobject" scope="request"  class="Lib.Auto.Simples.Searchbean"  type="Lib.Auto.Simples.Searchbean">

</jsp:useBean>
<div style="padding: 0px 0px 0px 0px;">

		<jsp:include page="simpleSearchdisplay.jsp" flush="true" />
	</div>
	
<%-- 	<%@ include file="/Tree/back.jsp"%> --%>

	