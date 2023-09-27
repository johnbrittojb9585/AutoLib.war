<%@page import="Common.RecentBackup"%>
<%
	String URole = session.getAttribute("UserRights").toString().trim();
	if (URole.equalsIgnoreCase("2") || URole.equalsIgnoreCase("3")
			|| URole.equalsIgnoreCase("4")
			|| URole.equalsIgnoreCase("5")
			|| URole.equalsIgnoreCase("6")
			|| URole.equalsIgnoreCase("7")) {
		response.sendRedirect("sessiontimeout.jsp");
	}
%>
<%@ include file="/Tree/demoFrameset.jsp"%>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/font.css" />

<%@ page language="java" session="true" buffer="12kb"
	import="java.io.BufferedReader" import="Common.Security"%>
<%@ page language="java" session="true" buffer="12kb"
	import="java.io.File"%>
<%@ page language="java" session="true" buffer="12kb"
	import="java.io.FileWriter"%>
<%@ page language="java" session="true" buffer="12kb"
	import="java.io.InputStreamReader"%>
<%@ page language="java" session="true" buffer="12kb"
	import="java.util.Date"%>
<%@ page language="java" session="true" buffer="12kb"
	import="java.util.Properties"%>

<style>/* 
h2 span:before {
    animation: dots 2s linear infinite;
    content: '';
  }

  @keyframes dots {
    0%, 20% {
      content: '.';
    }
    40% {
      content: '..';
    }
    60% {
      content: '...';
    }
    90%, 100% {
      content: '';
    }
}
 */
</style>
<html>
<body onload="load()">

	<form name="Backup" action="./BackupServlet" method="post">

<table width="50%" valign="middle" align="center" height="500"
			border="0">
			<td>
				<h2 style="font-weight: 20px; text-align: left; padding-left: 250px;">Backup Retriving Please Wait<span id="myspan"></span></h2>
			</td>
		</table>

<%-- 
		<table width="80%" height="500"
			border="0">
			
		    	
		    	<h2 style="font-weight: 20px; text-align: center">Loading<span id="myspan"></span></h2>
		    <!-- 	<h2 style="color: black;">Backup Retriving Please Wait....</h2> -->
				<h2 style="color: blue;">Backup Retriving Please Wait<img alt="" style="margin-top 5px;"  width ="8%" height="10%" src="<%=request.getContextPath() %>/images/loading.gif"> </h2>
				
			
		</table> --%>

	</form>

	<script>
		function load() {

			document.Backup.submit();

		}
		
		var span = document.getElementById('myspan');

		var int = setInterval(function() {
		    if ((span.innerHTML += '.').length == 6) 
		        span.innerHTML = '';
		    //clearInterval( int ); // at some point, clear the setInterval
		}, 500);
	</script>
</body>
</html>