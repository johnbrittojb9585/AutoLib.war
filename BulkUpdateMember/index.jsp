
<%
	String URole = session.getAttribute("UserRights").toString().trim();
	if (URole.equalsIgnoreCase("3") || URole.equalsIgnoreCase("4")
			|| URole.equalsIgnoreCase("5")
			|| URole.equalsIgnoreCase("6")
			|| URole.equalsIgnoreCase("7")
			|| URole.equalsIgnoreCase("8")) {
		response.sendRedirect("sessiontimeout.jsp");
	}
%>

<%@ include file="/Tree/demoFrameset.jsp"%>
<%@ page language="java" errorPage="/Error/ErrorPage.jsp"
	import="java.text.SimpleDateFormat"%>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/button_css.css" />

<html>
<head>
<meta http-equiv="Content-Type"
	content="text/html; charset=windows-1252">
<meta name="GENERATOR" content="Microsoft FrontPage 4.0">
<meta name="ProgId" content="FrontPage.Editor.Document">
<title>AutoLib</title>

<script language="javascript" src="/AutoLib/popcalendar.js"></script>
<link rel="stylesheet" type="text/css" href="/AutoLib/style.css">
</head>
<%
	java.util.Date d = new java.util.Date();
	SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	String dateString = sdf.format(d);
%>

<body background="/AutoLib/soft.jpg">

	<form name=BulkUpdateMember method="POST"
		action="./BulkUpdateMemberServlet">
		<br>
		<br>
		<br>
		<h2>Member&nbsp;Updation</h2>

		<table align="center" class="contentTable" width="45%">
			<tr>
				<td>
					<table border="0" width="80%" cellspacing="0" cellpadding="5"
						align="center">
						<tr>
							<td>&nbsp;</td>
						</tr>
						<tr>
						<tr>
							<td Class="addedit">Choose&nbsp;From</td>
							<td><select size="1" name="frmUpdate" style="width: 50mm"
								onchange="opt()">
									<option value="NO">ChooseFrom</option>
									<option value="Desig">Designation</option>
									<option value="Dept">Department</option>
									<option value="Group">Group</option>
									<option value="Course">Course</option>
									<option value="Slock">SLock</option>
									<option value="CourseYear">Course Year</option>
									<option value="Sex">Sex</option>
									
							</select></td>

							<td Class="addedit">Choose&nbsp;To</td>
							<td><select size="1" name="toUpdate" style="width: 50mm"
								onchange="optNew()">
									<option value="NO">ChooseTo</option>
									<option value="Desig">Designation</option>
									<option value="Dept">Department</option>
									<option value="Group">Group</option>
									<option value="Course">Course</option>
									<option value="Slock">SLock</option>
									<option value="CourseYear">Course Year</option>
									<option value="Sex">Sex</option>
							</select></td>

						</tr>

						<tr>
							<td Class="addedit">From&nbsp;Value</td>
							<td><select name="frmValue" size="1" style="width: 50mm">
									<option value="NO">From Value</option>
									<c:if test="${resultBulkList1 ne null}">
										<c:forEach items="${resultBulkList1}" var="std"
											varStatus="loop">
											<option value="<c:out value="${std.code}" />">
												<c:out value="${std.name}" />
											</option>
										</c:forEach>
									</c:if>
							</select></td>

							<td Class="addedit">To&nbsp;Value</td>
							<td><select name="toValue" size="1" style="width: 50mm">
									<option value="NO">To Value</option>
									<c:if test="${resultBulkList2 ne null}">
										<c:forEach items="${resultBulkList2}" var="std"
											varStatus="loop">
											<option value="<c:out value="${std.code}" />">
												<c:out value="${std.name}" />
											</option>
										</c:forEach>
									</c:if>
							</select></td>
						</tr>

						<tr>
							<td>&nbsp;</td>
						</tr>

					</table>
				</td>
			</tr>
		</table>
		<p align="center">
			<input type="button" Class="submitButton" value="Update" name="print"
				onclick="saveRec()">&nbsp; <input type="reset"
				Class="submitButton" value="Clear" name="clear" onclick="clearRec()">
			<input type="hidden" name="flag">
		</p>

	</form>
	
	
	 <c:if test="${beanForm ne null }">
	 <script language="JavaScript">
	   document.BulkUpdateMember.frmUpdate.value="<c:out value="${beanForm.frmUpdate}" />";
	   document.BulkUpdateMember.toUpdate.value="<c:out value="${beanForm.toUpdate}" />";
	   
	   document.BulkUpdateMember.frmValue.value="<c:out value="${beanForm.frmValue}" />";
	   document.BulkUpdateMember.toValue.value="<c:out value="${beanForm.toValue}" />";   
	   </script>
	   </c:if>
	   
	   <c:if test="${resultDone gt 0 }">
 <script language="JavaScript">
   alert(<c:out value="${resultDone}" /> + " Record updated successfully !");
 </script>
</c:if>
 
<c:if test="${resultDone eq 0 }">
 <script language="JavaScript">
   alert("Record not found / Updation failed !");
 </script>
</c:if>
	
	 <script language="JavaScript">
	 	 function opt(){
		document.BulkUpdateMember.frmValue.value="NO";
		 var str=document.BulkUpdateMember.frmUpdate.value;
		 document.BulkUpdateMember.flag.value="search";
		 document.BulkUpdateMember.submit();
	 }
	 	 
	 	 function optNew(){
	 		document.BulkUpdateMember.toValue.value="NO";
	 		 var str=document.BulkUpdateMember.toUpdate.value;
	 		 document.BulkUpdateMember.flag.value="search";
	 		 document.BulkUpdateMember.submit();
	 	 }
	 	 	
	 	 
	 	 
	 	 
function saveRec(){
	if(document.BulkUpdateMember.frmUpdate.value=='NO'){
		alert("Please select choose from")
	}else if(document.BulkUpdateMember.toUpdate.value=='NO'){
		alert("Please select choose to")
	}else if(document.BulkUpdateMember.frmValue.value=='NO'){
		alert("Please select from value")
	}
	
	else if(document.BulkUpdateMember.toValue.value=='NO'){
		alert("Please select to value")
	}
	
	
	else{
		
		msg=confirm("Are You Sure To Update?");
		if(msg){
			document.BulkUpdateMember.flag.value="update";
			  document.BulkUpdateMember.submit();
			
		}
		
		
	}
}


function clearRec()
{
	document.BulkUpdateMember.action="index.jsp";
	document.BulkUpdateMember.submit();  
}





</script>
	
	
	