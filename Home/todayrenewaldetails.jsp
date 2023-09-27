<%@ include file="/jsp/common/taglibs.jsp"%>
<%@ include file="/Tree/Searchdemoframeset.jsp"%>
<%@ page language="java" errorPage="/Error/ErrorPage.jsp" import="java.io.*" import="java.util.*" session="true" buffer="12kb" import="Common.Security" import="java.util.ArrayList"%>
<%//Security.checkSecurity(1,session,response,request);%>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/button_css.css" />
<jsp:useBean id="bean" scope="page" class="Lib.Auto.Advanced.Adsearchbean"/>

<form name="Account" method="post" action=./CounterReportsAll>

<table>
<tr><td>
<input type=hidden name=flag>
</td></tr>

</table>
</form>
<div style="padding: 0px 0px 0px 0px;">
		<jsp:include page="todayrenewaldetailsdisplay.jsp" flush="true"/>
	</div>
