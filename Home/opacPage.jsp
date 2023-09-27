<%
String URole=session.getAttribute("UserRights").toString().trim();
if( URole.equalsIgnoreCase("1") ||  URole.equalsIgnoreCase("2") ||  URole.equalsIgnoreCase("3") || URole.equalsIgnoreCase("4") || URole.equalsIgnoreCase("5") ||  URole.equalsIgnoreCase("6") || URole.equalsIgnoreCase("8"))
{		
	response.sendRedirect("sessiontimeout.jsp");
}	
%>
<%@ include file="/Tree/demoFrameset.jsp"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<%@ page language="java" session="true" buffer="12kb" import="java.sql.*"%>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/button_css.css" />

<html>
<link rel="stylesheet" type="text/css" href="/AutoLib/style.css">
<body background="/AutoLib/soft.jpg">
<form name="Home" method="post">
<br>
<br>
<br>
<br>

				<table width="98%" border="0" id="campusnewsheadBoard" align="center" cellpadding="0" cellspacing="0" class="indexTab" >
					<tr> <td colspan="2" align="center"  bgcolor="#104E8B" id="campusnewsheadBoard">Related Links</td> </tr> 
					<tr>
						<td colspan="2" align="center" id="bodysearch">
							<table width="90%" border="0" align="center" cellpadding="2" cellspacing="0">
								<tr>
								
								<td align="center">
									<a href="<%= request.getContextPath() %>/Simples/SimpleServlet?flag=load" id="bodysearch" title="Click Here to search resource">
										<img src="<%= request.getContextPath() %>/iconImages/searchBook.png" width="35" height="35" border="0" align="middle" >
											<br>
									<font id="profileLink">Search</font>
									</a>
								</td>								
								
								<td align="center">
									<a href="<%= request.getContextPath() %>/Advanced/" id="bodysearch" title="Click Here to search resource">
										<img src="<%= request.getContextPath() %>/iconImages/NewsearchBook.png" width="35" height="35" border="0" align="middle" >
											<br>
									<font id="profileLink">Advanced Search</font>
									</a>
								</td>	
								
								
								<td align="center">
									<a href="<%= request.getContextPath() %>/Browse/" id="bodysearch" title="Click Here to search resource">
										<img src="<%= request.getContextPath() %>/iconImages/quicksearch.png" width="35" height="35" border="0" align="middle" >
											<br>
									<font id="profileLink">Quick Search</font>
									</a>
								</td>	
								
<!-- 								<td align="center">	 -->
<%-- 									<a href="<%= request.getContextPath() %>/JournalBrowse/" id="bodysearch" title="Click Here to search resource"> --%>
<%-- 										<img src="<%= request.getContextPath() %>/iconImages/journalsearch.png" width="35" height="35" border="0" align="middle" > --%>
<!-- 											<br> -->
<!-- 									<font id="profileLink">Journal Search</font> -->
<!-- 									</a> -->
<!-- 								</td> -->
								
								
								
								</tr>
							</table>
						</td>
					</tr>
				</table>									
				<br>							
							 			
							<table width="98%" border="0" id="campusnewsheadBoard" align="center" cellpadding="0" cellspacing="0" class="indexTab" >
							
							<tr> <td  align="center"  bgcolor="#104E8B" id="campusnewsheadBoard">Librarian's Message</td> </tr>
							
							<tr>
						<td colspan="2" align="center" id="bodysearch">
							<table width="95%" border="0" align="center" cellpadding="2" cellspacing="0">
							<tr><td>&nbsp;</td></tr>
							
							<tr>
								<td width="10%"><font id="libHeading">Message</font></td>
							<td width="80%" align="justify"><font id="libMessage">:&nbsp;<c:out value="${LibraryMessage}"/></font></td>
							</tr>
							<tr><td>&nbsp;</td></tr>
							<tr>
							<td width="10%"><font id="libHeading">Whats&nbsp;New</font></td>
							<td width="80%" align="justify"><font id="libMessage">:&nbsp;<c:out value="${WhatsNew}"/></font></td>
							</tr>
							
							</table></td></tr>
							<tr><td>&nbsp;</td></tr>
							
							
							</table>		
									
</form>									
</body>
</html>

<%

session.setAttribute("UserIssueCount", "0");
session.setAttribute("UserReturnCount", "0");
session.setAttribute("UserReserveCount", "0");	


session.setAttribute("totalAmount", "0.00");
session.setAttribute("paidAmount", "0.00");
session.setAttribute("balAmount", "0.00");

%>
