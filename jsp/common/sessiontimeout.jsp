<!--
 * 
 * Copyright (C) Autolib Software Systems 2010.
 * 
 -->
<%@page isELIgnored="true"%>
<%@ include file="/Common/College.jsp"%>

<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/Tree/autolib.css" />
<link rel="stylesheet" type="text/css" href="/AutoLib/style.css">
<script language="JavaScript">
			window.history.forward(0);
</script>


<html>
<body >
<script>
	function getURL(uri) 
	{
		uri.dir = location.href.substring(0,location.href.lastIndexOf('\/'));
		uri.dom = uri.dir; if (uri.dom.substr(0,7) == 'http:\/\/') uri.dom = uri.dom.substr(7);
		uri.path = ''; var pos = uri.dom.indexOf('\/'); if (pos > -1) {uri.path = uri.dom.substr(pos+1); uri.dom = uri.dom.substr(0,pos);}
		uri.page = location.href.substring(uri.dir.length+1,location.href.length+1);
		pos = uri.page.indexOf('?');if (pos > -1) {uri.page = uri.page.substring(0, pos);}
		pos = uri.page.indexOf('#');if (pos > -1) {uri.page = uri.page.substring(0, pos);}
		uri.ext = ''; pos = uri.page.indexOf('.');if (pos > -1) {uri.ext =uri.page.substring(pos+1); uri.page = uri.page.substr(0,pos);}
		uri.file = uri.page;
		if (uri.ext != '') uri.file += '.' + uri.ext;
		if (uri.file == '') uri.page = 'index';
		uri.args = location.search.substr(1).split("?");
		return uri;
	}
	function forwardToMainPage()
	{
		var uri = new Object();
		uri = getURL(uri);
		document.location.href = "http://"+uri.dom+"<%= request.getContextPath() %>/index.jsp";
	}
</script>
<table border="0" cellpadding="5" cellspacing="0" width="100%" height="500">
	<tr>
		<td width="100%">
			<table cellpadding="1" cellspacing="3" WIDTH="50%" align="center">
			<tr><td>
				<div class="info"> 
					Your session has been expired.
					<a href="javascript:forwardToMainPage()"> Click Here </a> to Login AutoLib Portal.&nbsp;&nbsp;&nbsp;&nbsp;
				</div>
				</td></tr>
			</table>
		</td>
	</tr>
</table>
</body>
</html>

