<%
String URole=session.getAttribute("UserRights").toString().trim();
String User=session.getAttribute("UserID").toString().trim();
if( URole.equalsIgnoreCase("7") &&  User.equalsIgnoreCase("WebOpac"))
{		
	response.sendRedirect("opacPage.jsp");
}	
else if( URole.equalsIgnoreCase("1") ||  URole.equalsIgnoreCase("2") ||  URole.equalsIgnoreCase("3") || URole.equalsIgnoreCase("4") || URole.equalsIgnoreCase("5") ||  URole.equalsIgnoreCase("6") || URole.equalsIgnoreCase("8"))
{		
	response.sendRedirect("sessiontimeout.jsp");
}	
%>
<%@ include file="/Tree/Searchdemoframeset.jsp"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<%@ page language="java" session="true" buffer="12kb" import="java.sql.*"%>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/button_css.css" />

<html>
<link rel="stylesheet" type="text/css" href="/AutoLib/style.css">
<body background="/AutoLib/soft.jpg">
<form name="Home" method="post" action=./HomeServlet>
<br>
<br>
<br>
<br>


<%
byte[] UserPhoto=null;
UserPhoto=(byte[])session.getAttribute("UserPhoto");
%>




<table align="right" width="75%"  border="0" cellpadding="0" cellspacing="0">

<tr><td>
<table width="90%" id="campusnewsheadBoard" border="0" align="right" cellpadding="1" cellspacing="1" >
<tr>
	<td width="50%">
	<table width="95%" id="campusnewsheadBoard" border="0" align="center" cellpadding="2" cellspacing="2" class="indexTab">
	<tr><td Class="addedit">User&nbsp;Id</td><td class="addedit">:&nbsp;<c:out value="${member_code}"/></td>
	<td Class="addedit">Department:</td><td class="addedit">:&nbsp;<c:out value="${dname}"/></td>
	
<c:set var="str" scope="session" value="${userImage}"/>
<c:choose>
<c:when test="${str ne null}">
<td align="right" rowspan="3">
<img src="<%=request.getContextPath() %>/Home/photo.jsp" height="75px" width="70px" align=left/></td>
</c:when>
<c:otherwise>
<td align="right" rowspan="3">
<img src="<%=request.getContextPath() %>/images/noimage.jpg" height="75px" width="70px" align=left/></td>
</c:otherwise>
</c:choose>
	</tr>

	<tr><td  Class="addedit">User&nbsp;Name</td><td class="addedit">:&nbsp;<c:out value="${member_name}"/></td>
	<td  Class="addedit">Valid&nbsp;Date</td><td class="addedit">:&nbsp;<c:out value="${expiry_date}"/></td>
	</tr>
	<tr><td Class="addedit">Designation</td><td class="addedit">:&nbsp;<c:out value="${desig_name}"/></td></tr>
	<tr><td>
	</td></tr>
	</table></td></tr></table></td></tr></table>

<table width="100%"  border="0" cellpadding="0" cellspacing="0">
<tr>
<td>
<table width="98%" id="campusnewsheadBoard" border="0" align="center" cellpadding="1" cellspacing="1" >
		<tr>
		<br>
			<td width="50%">

				<table width="100%" id="campusnewsheadBoard" border="0" align="center" cellpadding="2" cellspacing="2" class="indexTab">
					<tr> <td colspan="2" align="center" bgcolor="#104E8B" id="campusnewsheadBoard">User Transaction Details </td> </tr> 
					<tr>
						<td colspan="2" align="center" id="bodysearchBoard">
							<table width="100%" border="0" align="center" cellpadding="5" cellspacing="0">
								<tr>
																
                                 <td align="center">
										<c:if test="${UserIssueCount eq 0}">
											<a href="#" id="bodysearch">
												<img src="<%= request.getContextPath() %>/iconImages/LibraryCollection.png" width="35" height="35" border="0" align="middle" title="Click here to, view issue details.">
												<br>
												Issued Books (0)
											</a>
										</c:if>
										<c:if test="${UserIssueCount ne 0}">
											<c:url var="userIssueCountURL" value="AccountServlet" >
											    <c:param name="issueuserid" value="${UserID}" />
											</c:url>
											<a href="<%= request.getContextPath() %>/Account/<c:out value="${userIssueCountURL}"/>" id="bodysearch">
												<img src="<%= request.getContextPath() %>/iconImages/LibraryCollection.png" width="35" height="35" border="0" align="middle" title="Click here to, view issue details.">
												<br>
												Issued Books (<c:out value="${UserIssueCount}"/>)
											</a>
										</c:if>
									</td>
									
									
                                 <td align="center">
										<c:if test="${UserReturnCount eq 0}">
											<a href="#" id="bodysearch">
												<img src="<%= request.getContextPath() %>/iconImages/Member.png" width="35" height="35" border="0" align="middle" title="Click here to, view return details.">
												<br>
												Returned Books (0)
											</a>
										</c:if>
										<c:if test="${UserReturnCount ne 0}">
											<c:url var="userReturnCountURL" value="AccountServlet">
												<c:param name="returnuserid" value="${UserID}" />
											</c:url>
											<a href="<%= request.getContextPath() %>/Account/<c:out value="${userReturnCountURL}"/>" id="bodysearch">
												<img src="<%= request.getContextPath() %>/iconImages/Member.png" width="35" height="35" border="0" align="middle" title="Click here to, view return details.">
												<br>
												Returned Books (<c:out value="${UserReturnCount}"/>)
											</a>
										</c:if>
									</td>									
									


                                 <td align="center">
										<c:if test="${UserReserveCount eq 0}">
											<a href="#" id="bodysearch">
												<img src="<%= request.getContextPath() %>/iconImages/LibraryDue.png" width="35" height="35" border="0" align="middle" title="Click here to, view reserved details.">
												<br>
												Reserved Books (0)
											</a>
										</c:if>
										<c:if test="${UserReserveCount ne 0}">
											<c:url var="userReserveCountURL" value="AccountServlet">
											    <c:param name="reserveuserid" value="${UserID}" />
											</c:url>
											<a href="<%= request.getContextPath() %>/Account/<c:out value="${userReserveCountURL}"/>" id="bodysearch">
												<img src="<%= request.getContextPath() %>/iconImages/LibraryDue.png" width="35" height="35" border="0" align="middle" title="Click here to, view reserved details.">
												<br>
												Reserved Books (<c:out value="${UserReserveCount}"/>)
											</a>
										</c:if>
									</td>    
                </tr>

							</table></td></tr></table></td></tr></table>
							
							
							
							<td>
							
							
							
<table width="98%" id="campusnewsheadBoard" border="0" align="center" cellpadding="1" cellspacing="1" >
		<tr>
		<br>
			<td width="50%">

				<table width="100%" id="campusnewsheadBoard" border="0" align="center" cellpadding="2" cellspacing="2" class="indexTab">
					<tr> <td colspan="2" align="center" bgcolor="#104E8B" id="campusnewsheadBoard">User Fine Details </td> </tr> 
					<tr>
						<td colspan="2" align="center" id="bodysearchBoard">
							<table width="100%" border="0" align="center" cellpadding="5" cellspacing="0">
								<tr>
																
                               
                               
                               
                               
                            
                                 <td align="center">
										<c:if test="${totalAmount eq 0.00}">
											<a href="#" id="bodysearch">
												<img src="<%= request.getContextPath() %>/iconImages/Member.png" width="35" height="35" border="0" align="middle" title="Click here to, view return details.">
												<br>
												Total Fine (0.00)
											</a>
										</c:if>
										<c:if test="${totalAmount ne 0.00}">
											<c:url var="userReturnCountURL" value="AccountServlet">
												<c:param name="returnuserfineid" value="${UserID}" />
											</c:url>
											<a href="<%= request.getContextPath() %>/Account/<c:out value="${userReturnCountURL}"/>" id="bodysearch">
												<img src="<%= request.getContextPath() %>/iconImages/Member.png" width="35" height="35" border="0" align="middle" title="Click here to, view return details.">
												<br>
												Total Fine (<c:out value="${totalAmount}"/>)
											</a>
										</c:if>
									</td>
									
									
									
									
									
                                 <td align="center">
										<c:if test="${paidAmount eq 0.00}">
											<a href="#" id="bodysearch">
												<img src="<%= request.getContextPath() %>/iconImages/paidpng.png" width="35" height="35" border="0" align="middle" title="Click here to, view return details.">
												<br>
												Paid (0.00)
											</a>
										</c:if>
										<c:if test="${paidAmount ne 0.00}">
										<c:url var="userpaidAmtURL" value="AccountServlet">
												<c:param name="userpaidamtid" value="${UserID}" />
											</c:url>
											<a href="<%= request.getContextPath() %>/Account/<c:out value="${userpaidAmtURL}"/>" id="bodysearch">
												<img src="<%= request.getContextPath() %>/iconImages/paidpng.png" width="35" height="35" border="0" align="middle" title="Click here to, view return details.">
												<br>
											Paid (<c:out value="${paidAmount}"/>)
												</a>
										</c:if>
									</td>
								
								
								 <td align="center">
										<c:if test="${balAmount eq 0.00}">
											<a href="#" id="bodysearch">
												<img src="<%= request.getContextPath() %>/iconImages/Member.png" width="35" height="35" border="0" align="middle" title="Click here to, view return details.">
												<br>
												Unpaid Amount (0.00)
											</a>
										</c:if>
										<c:if test="${balAmount ne 0.00}">
												<img src="<%= request.getContextPath() %>/iconImages/Member.png" width="35" height="35" border="0" align="middle" title="Click here to, view return details.">
												<br>
												<font id="profileLink">Unpaid (<c:out value="${balAmount}"/>)</font>
										</c:if>
									</td>
									               </tr>

							</table></td></tr></table></td></tr></table>
									</td>
							
							</tr></table>
									
					

				<br>
				<table width="98%" border="0" id="campusnewsheadBoard" align="center" cellpadding="0" cellspacing="0" class="indexTab" >
					<tr> <td colspan="2" align="center"  bgcolor="#104E8B" id="campusnewsheadBoard">Related Links</td> </tr> 
					<tr>
						<td colspan="2" align="center" id="bodysearch">
							<table width="90%" border="0" align="center" cellpadding="2" cellspacing="0">
								<tr>
								
								<td align="center">
									<a href="<%= request.getContextPath() %>/newarrivals/NewArraivalsServlet?flag=loadMonthYear" id="bodysearch" title="Click Here to New Arrivals">
										<img src="<%= request.getContextPath() %>/iconImages/newarrivals.png" width="35" height="35" border="0" align="middle" >
											<br>
									<font id="profileLink">New Arrivals</font>
									</a>
								</td>
											
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
								
								<td align="center">	
									<a href="<%= request.getContextPath() %>/JournalBrowse/" id="bodysearch" title="Click Here to search resource">
										<img src="<%= request.getContextPath() %>/iconImages/journalsearch.png" width="35" height="35" border="0" align="middle" >
											<br>
									<font id="profileLink">Journal Search</font>
									</a>
								</td>
								
								<td align="center">	
									<a href="<%= request.getContextPath() %>/Account/changepwd.jsp" id="bodysearch" title="Click Here to change your password">
										<img src="<%= request.getContextPath() %>/iconImages/checkAccount.png" width="35" height="35" border="0" align="middle" >
											<br>
									<font id="profileLink">Change Password</font>
									</a>
								</td>
								
								 <td align="center">
										
											<c:url var="sugDisplay" value="AccountServlet">
												<c:param name="Suguserid" value="${UserID}" />
											</c:url>
											<a href="<%= request.getContextPath() %>/Account/<c:out value="${sugDisplay}"/>" id="bodysearch">
												<img src="<%= request.getContextPath() %>/iconImages/suggestion.png" width="35" height="35" border="0" align="middle" title="Click here to, view Suggestion details.">	
												<br>
												<font id="profileLink">See Your Suggestion</font>
											</a>
									</td>
								
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
