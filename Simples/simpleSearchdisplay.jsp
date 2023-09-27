<%@ include file="/jsp/common/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" errorPage="/Error/ErrorPage.jsp"
	import="java.io.*" import="java.util.*" import="java.lang.*"
	session="true" buffer="12kb" import="Common.Security"
	import="java.util.ArrayList"%>
<%//Security.checkSecurity(1,session,response,request);%>

<jsp:useBean id="bean" scope="page"
	class="Lib.Auto.Advanced.Adsearchbean" />

<link rel="stylesheet" type="text/css"
	href="<%= request.getContextPath() %>/css/displaytag.css" />
<style>
.hidden {
	display: none;
}
</style>
<%-- <script  src="<%= request.getContextPath()%>/script/campusAjax.js"></script> --%>

<%

ArrayList sc=new ArrayList();
try{
sc=(ArrayList)bean.getAl();
//out.print("<table>");
for(int i=0; i<sc.size();i++){
	out.print("<a href='?doc="+sc.get(i)+"'>"+sc.get(i)+"("+sc.get(++i)+")</a> ");
	%>




	<%
		}
		} catch (Exception e) {
			out.println(e.toString());
		} finally {
			sc.clear();
		}
	%>

	<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
	<center>
		<display:table name="sessionScope.SearchArrylist" id="Searchbean"
			pagesize="20" class="dataTable" sort="list" defaultsort="2"
			defaultorder="ascending" requestURI="/Simples/simpleSearch.jsp"
			export="true">


			<display:setProperty name="basic.msg.empty_list" value="" />
			<display:setProperty name="export.excel" value="true" />
			<display:setProperty name="export.csv" value="true" />
			<display:setProperty name="export.pdf" value="false" />

			<display:setProperty name="export.excel.filename"
				value="SimpSearch.xls"></display:setProperty>
			<display:setProperty name="export.csv.filename"
				value="SimpSearch.csv"></display:setProperty>
			<display:setProperty name="export.pdf.filename"
				value="SimpSearch.pdf"></display:setProperty>


			<display:caption media="html">
				<c:if
					test="${Searchbean.document eq 'BOOK' || Searchbean.document eq 'Article'}">
					<c:url var="APAView" value="SimpleServlet">
						<c:param name="format" value="APA" />
						<c:param name="doc" value="${Searchbean.document}" />
					</c:url>
					<a href="${APAView}">APA Format</a>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			</c:if>
				<c:if test="${Searchbean.document eq 'BOOK'}">
					<c:url var="MarcView" value="SimpleServlet">
						<c:param name="format" value="Marc" />
						<c:param name="doc" value="${Searchbean.document}" />
					</c:url>
					<a href="${MarcView}">Marc Format</a>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<c:url var="AACRView" value="SimpleServlet">
						<c:param name="format" value="AACR" />
						<c:param name="doc" value="${Searchbean.document}" />
					</c:url>
					<a href="${AACRView}">AACR Format</a>
				</c:if>

			</display:caption>

			<c:if
				test="${Searchbean.document ne 'Article' && Searchbean.format eq ''}">

				<display:column title="Access No" maxLength="35" media="html">
					<c:url var="accnosearch" value="SimpleServlet">
						<c:param name="accno" value="${Searchbean.accno}" />
						<c:param name="document" value="${Searchbean.document}" />
					</c:url>
					<a href="${accnosearch}">${Searchbean.accno}</a>
				</display:column>

				<display:column property="accno" title="Access No" maxLength="25"
					class="hidden" headerClass="hidden">
				</display:column>

				<display:column sortable="true" title="Title" maxLength="200"
					media="html">
					<c:set var="string1" value="${Searchbean.title}" />
					<c:set var="searchstr" value="${Searchbean.searchstring}" />
					<%
						String str = "", strsearch = "", finalstring = "", repstr = "";
									str = (String) pageContext.getAttribute("string1");
									strsearch = (String) pageContext
											.getAttribute("searchstr");
									repstr = "<span style='background-color:yellow;'>"
											+ strsearch + "</span>";
									finalstring = str
											.replaceAll("(?i)" + strsearch, repstr);
									out.println(finalstring);
					%>
				</display:column>

				<display:column sortable="true" title="Title" property="title"
					class="hidden" headerClass="hidden" maxLength="200">
				</display:column>


				<display:column property="author" sortable="true"
					title="Author Name" maxLength="50">
				</display:column>

				<display:column property="callno" title="Call no" maxLength="25">
				</display:column>

				<display:column property="publisher" title="Publisher"
					maxLength="25">
				</display:column>

				<display:column property="location" title="Location" maxLength="50">
				</display:column>

				<display:column property="edition" title="Edition" maxLength="50">
				</display:column>

				<display:column property="year" title="Year" maxLength="50">
				</display:column>

				<display:column property="availability" title="Status"
					class="hidden" headerClass="hidden">
				</display:column>
				<display:column title="Status" media="html">
					<c:choose>
						<c:when
							test="${Searchbean.availability eq 'ISSUED' || Searchbean.availability eq 'REFISSUED'}">

							<c:url var="userReserveURL" value="AccountServlet">
								<c:param name="reservecheck" value="${Searchbean.accno}" />
								<c:param name="document" value="${Searchbean.document}" />
							</c:url>

							<a href="${userReserveURL}"> <font color="red" size="2">
									<c:out value="${Searchbean.availability}"></c:out>
							</font>
							</a>

						</c:when>

						<c:otherwise>
							<c:out value="${Searchbean.availability}"></c:out>
						</c:otherwise>
					</c:choose>
				</display:column>

				<%-- 	   <display:column property="document"  title="Document"  maxLength="25" > 	 --%>
				<%-- 	   </display:column> --%>

				<display:column property="bprice" title="Price" maxLength="25">
				</display:column>

			</c:if>


			<c:if
				test="${Searchbean.format eq 'APA' && Searchbean.document eq 'BOOK'}">

				<display:column property="accno" title="Access No" maxLength="25">
				</display:column>

				<display:column title="APA Format" maxLength="1000">   
	   		${Searchbean.author}.(${Searchbean.year}).${Searchbean.title}.(${Searchbean.volno}).${Searchbean.place}:${Searchbean.publisher}.
	   </display:column>

			</c:if>

			<c:if
				test="${Searchbean.format eq 'APA' && Searchbean.document eq 'Article'}">

				<display:column property="accno" title="Article No" maxLength="35">
				</display:column>

				<display:column title="APA Format" maxLength="1000">   
	   		${Searchbean.author}.(${Searchbean.year}).${Searchbean.title}.${Searchbean.journal},${Searchbean.volno}(${Searchbean.issueNo}),${Searchbean.pages}.
	   </display:column>

			</c:if>

			<!-- 	   =======AACR======= -->


			<c:if
				test="${Searchbean.format eq 'AACR' && Searchbean.document eq 'BOOK'}">

				<display:column title="" maxLength="1000" media="html" style="width:6%">

					<b>${Searchbean.callno}</b>
					</br>
					</br>
					${Searchbean.accno}
					</br>
					</br>
				</display:column>

				<display:column title="AACR Format" maxLength="1000" style="width:50%" media="html">
						   		<table>
						   		<tr>
						   		<td style="width:50%">
						   		<b>${Searchbean.author}</b></br>
						   		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>${Searchbean.title}&nbsp;:${Searchbean.subtitle}</b>&nbsp;/&nbsp;by&nbsp;${Searchbean.sres}<c:if test="${Searchbean.edition ne ''}">.&nbsp;-${Searchbean.edition}</c:if><c:if test="${Searchbean.place ne ''}">.&nbsp;-${Searchbean.place}</c:if>:&nbsp;${Searchbean.publisher},&nbsp;${Searchbean.year}.</br>
						   		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${Searchbean.pages},${Searchbean.phyDesc}</br>
						   		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;ISBN&nbsp;:&nbsp;${Searchbean.isbn}
						   		</td>
						   		<td style="width:50%">
						   		&nbsp;
						   		</td>
						   		</tr>
						   		</table>
				</display:column>
				

<%-- &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>${Searchbean.title}</b>&nbsp;.&nbsp;${Searchbean.edition}&nbsp;/&nbsp;by&nbsp;${Searchbean.sres}&nbsp;.-${Searchbean.place}:&nbsp;${Searchbean.publisher},&nbsp;${Searchbean.year}</br> --%>
<%-- 						   		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${Searchbean.phyDesc}</br> --%>
<%-- 						   		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;ISBN&nbsp;:&nbsp;${Searchbean.isbn} --%>

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

			<!-- 	   ============AACR=========== -->

			<c:if
				test="${Searchbean.format eq 'Marc' && Searchbean.document eq 'BOOK'}">

				<display:column property="accno" title="Access No" maxLength="25">
				</display:column>

				<display:column title="Marc Format" maxLength="1000" media="html">   
	   		245&nbsp;&nbsp;10&nbsp;&nbsp;$a&nbsp;&nbsp;${Searchbean.title}/$c&nbsp;&nbsp;${Searchbean.sres}</br>
	   		100&nbsp;&nbsp;1&nbsp;&nbsp;&nbsp;&nbsp;$a&nbsp;&nbsp;${Searchbean.author}</br>
	   		082&nbsp;&nbsp;0&nbsp;&nbsp;&nbsp;&nbsp;$a&nbsp;&nbsp;${Searchbean.callno}</br>
	   		260&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;$a&nbsp;&nbsp;${Searchbean.place}&nbsp;&nbsp;$b&nbsp;&nbsp;${Searchbean.publisher},$c&nbsp;&nbsp;${Searchbean.year}</br>
	   		650&nbsp;&nbsp;0&nbsp;&nbsp;&nbsp;&nbsp;$a&nbsp;&nbsp;${Searchbean.subject}</br>
	   		300&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;$a&nbsp;&nbsp;${Searchbean.pages}&nbsp;&nbsp;$c&nbsp;&nbsp;${Searchbean.phyDesc}
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

			<c:if
				test="${Searchbean.document eq 'Article' && Searchbean.format eq ''}">

				<display:column title="Article No" maxLength="35" media="html">
					<c:url var="accnosearch" value="SimpleServlet">
						<c:param name="accno" value="${Searchbean.accno}" />
						<c:param name="document" value="${Searchbean.document}" />
					</c:url>
					<a href="${accnosearch}">${Searchbean.accno}</a>
				</display:column>

				<display:column property="accno" title="Article No" maxLength="25"
					class="hidden" headerClass="hidden">
				</display:column>

				<display:column sortable="true" title="Journal" property="journal"
					maxLength="200">
				</display:column>

				<display:column sortable="true" title="Article Title"
					property="title" class="hidden" headerClass="hidden"
					maxLength="200">
				</display:column>

				<display:column sortable="true" title="Article Title"
					maxLength="200" media="html">
					<c:set var="string1" value="${Searchbean.title}" />
					<c:set var="searchstr" value="${Searchbean.searchstring}" />
					<%
						String str = "", strsearch = "", finalstring = "", repstr = "";
									str = (String) pageContext.getAttribute("string1");
									strsearch = (String) pageContext
											.getAttribute("searchstr");
									repstr = "<span style='background-color:yellow;'>"
											+ strsearch + "</span>";
									finalstring = str
											.replaceAll("(?i)" + strsearch, repstr);
									out.println(finalstring);
					%>
				</display:column>

				<display:column property="author" sortable="true"
					title="Author Name" maxLength="50">
				</display:column>

				<display:column property="volno" title="Volume" maxLength="25">
				</display:column>

				<display:column property="year" title="Year" maxLength="50">
				</display:column>

				<display:column property="subject" title="Subject">
				</display:column>

				<%-- 	   <display:column property="document"  title="Document"  maxLength="25" > 	 --%>
				<%-- 	   </display:column> --%>

				<%-- 	   <display:column property="bprice"  title="Price"  maxLength="25" >  --%>
				<%-- 	   </display:column> --%>

			</c:if>

			<display:setProperty name="basic.empty.showtable" value="true" />

		</display:table>
	</center>
	<p align="center">
		<input type=button name=New Class="submitButton" Value=Back
			onclick='back()'>
	</p>

	<script>
		function back() {
			history.go(-1);
		}
	</script>