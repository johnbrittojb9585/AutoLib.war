<%@ include file="/Tree/Searchdemoframeset.jsp"%>
<%@ page language="java" session="true" buffer="12kb"
	import="java.sql.*" import="java.util.*"%>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/button_css.css" />
<jsp:useBean id="bean" scope="request"
	class="Lib.Auto.Budget.BudgetBean" />
<%@ page import="java.util.Calendar"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.text.SimpleDateFormat"%>

<!--
//////////////////////////////////////DATABASE CONNECTION//////////////////////////////////////////////////////////////////////// -->
<%
	//
	//   Filename: Index.jsp
	//   Form name:binding
	//
%>
<!--
//////////////////////////////////////FORM CONTROLS//////////////////////////////////////////////////////////////////////// -->
<html>
<head>
<meta charset="ISO-8859-1">
<title>AutoLib</title>
<link rel="stylesheet" type="text/css" href="/AutoLib/style.css">
<!-- <script language="javascript" src="/AutoLib/popcalendar.js"></script> -->
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jquery-2.2.4.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jquery-ui.js"></script>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/jquery-ui.css" />
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/searchPaymentReport.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/datepicker.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/datepicker2.js"></script>

<meta http-equiv="pragma" content="no-cache" />
</head>

<%
	java.util.Date d = new java.util.Date();
	SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	String dateString = sdf.format(d);
%>

<body background="/AutoLib/soft.jpg">
	<!-- Style Sheet -->
	<form name="review" method="post" action=./ReviewReportServlet>

		<br> <br> <br> <br>
		<h2>Review Report</h2>
		<center>
			<br>
			<table align="center" class="contentTable" width="45%">
				<td>
					<table align="center" width="90%">
						<tr>
							<td>&nbsp;</td>
						</tr>
						<tr>
							<td Class="addedit">Access&nbsp;No</td>
							<td><input type="text" name='accno'</td>
						</tr>

						<tr>
							<td Class="addedit">User&nbsp;ID</td>
							<td><input type="text" name='user'</td>
						</tr>

						<tr>
							<td Class="addedit">Document</td>
							<td><select name="type" size="1" style="width: 125px">
									<option selected value="ALL">ALL</option>
									<option value="BOOK">BOOK</option>
									<option value="BOOK BANK">BOOK BANK</option>
									<option value="NON BOOK">NON BOOK</option>
									<option value="THESIS">THESIS</option>
									<option value="REPORT">REPORT</option>
									<option value="STANDARD">STANDARD</option>
									<option value="PROCEEDING">PROCEEDING</option>
									<option value="BACK VOLUME">BACK VOLUME</option>


							</select></td>
						</tr>
						<tr>
							<td Class="addedit">Rating</td>
							<td><select name="rating" size="1" style="width: 125px">
									<option selected value="ALL">ALL</option>
									<option value="1">1</option>
									<option value="2">2</option>
									<option value="3">3</option>
									<option value="4">4</option>
									<option value="5">5</option>

							</select></td>
						</tr>




						<tr>
							<td Class="addedit">From Date</td>
							<td>
								<%-- 	<INPUT name=fromdate size=10  onfocus=this.blur(); value="<%=dateString%>"> --%>
								<INPUT name=fromdate size=10 id="datepicker"
								value="<%=dateString%>"> To Date <INPUT name=todate
								size=10 id="datepicker2" value="<%=dateString%>">

							</td>
						</tr>


						<tr>
							<td>&nbsp;</td>
						</tr>

					</table>
				</td>
			</table>
		</center>

		<P align=center>
			<input type=button name=Report Class="submitButton" value=Print
				onclick="review_report()">
				 <input type=Reset name=Clear
				Class="submitButton" Value=Clear> <input type=hidden
				name=flag>
		</P>

	</form>
</body>
</html>
<!--
//////////////////////////////////////JAVA SERVER PAGES SCRIPT ///////////////////////////////////////////////// -->


<%
	ArrayList sc = new ArrayList();
	ArrayList pc = new ArrayList();
	String message = "", info = "";
%>

<%
	String valid = request.getParameter("check");

	if (valid != null) {

		if (request.getParameter("check") != null) {
			if (valid.equals("NoData")) {
%>
<script>
		alert("No Record Found");
		document.review.flag.value="load";
		</script>

<%
	}
			if (valid.equals("userdetails")) {
				sc = (ArrayList) request.getAttribute("MemberDetails");

				for (int i = 0; i < sc.size(); i += 5) {
%>
<script language="JavaScript">
	
	document.review.user_no.value="<%=sc.get(i)%>";
	document.review.user_name.value="<%=sc.get(i + 1)%>";
    document.review.user_dept.value="<%=sc.get(i + 2)%>";
    document.review.user_course.value="<%=sc.get(i + 3)%>";        
    document.review.flag.value="";
	
</script>
<%
	}
				sc.clear();
			}
			if (valid.equals("usernotfound")) {
%>

<script language="JavaScript">
	 
	alert("Member Not Found");
	 
	 </script>

<%
	}

		}
	}
%>
<!--
///////////////////////////////////////JAVA SCRIPT USEING CLIENT SIDE///////////////////////////////////////////////// -->


<script language="javascript">

function review_report()
{

	document.review.flag.value="review";

	 document.review.submit();
	
	}

	

</script>
