<%@ include file="/jsp/common/taglibs.jsp"%>
<%@ include file="/Tree/Searchdemoframeset.jsp"%>
<%@ page language="java" errorPage="/Error/ErrorPage.jsp" import="java.io.*" import="java.util.*" session="true" buffer="12kb" import="Common.Security" import="java.util.ArrayList"%>
<%//Security.checkSecurity(1,session,response,request);%>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/button_css.css" />
<jsp:useBean id="bean" scope="page" class="Lib.Auto.Advanced.Adsearchbean"/>

<form name="Account" method="post" action=./AccountServlet>
<P ALIGN="left">
<CENTER>
<table >
<tr>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
</tr></CENTER>
<input type=hidden name=flag>
</table>
</form>
</body>
</html>

<div style="padding: 0px 0px 0px 0px;">
		<jsp:include page="finepaiddetailsdisplay.jsp" flush="true" />
	</div>	
<CENTER>

<c:choose>
<c:when test="${UserRights eq 7}">
<a href="<%= request.getContextPath() %>/Home/HomeServlet" id="bodysearch">
<img src="<%= request.getContextPath() %>/iconImages/back.png" width="50" height="50" border="0" align="middle" title="Click here to go back.">
</a>
</c:when>
<c:otherwise>
<input type=button name=New Class="submitButton"  Value=Back onclick="back();">
</c:otherwise>
</c:choose>

</CENTER>
<script language="javascript">

function back()
{
document.Account.flag.value="back";
document.Account.submit();
}
</script>

