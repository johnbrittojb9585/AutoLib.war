
<%
	String URole = session.getAttribute("UserRights").toString().trim();
	if (URole.equalsIgnoreCase("4") || URole.equalsIgnoreCase("5")
			|| URole.equalsIgnoreCase("6")
			|| URole.equalsIgnoreCase("7")) {
		response.sendRedirect("sessiontimeout.jsp");
	}
%>
<%@ include file="/Tree/demoFrameset.jsp"%>
<%@ page language="java" errorPage="/Error/ErrorPage.jsp" session="true"
	buffer="12kb" import="Common.Security"%>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/button_css.css" />
<%
	//Security.checkSecurity(1,session,response,request);
%>
<jsp:useBean id="beanobject" scope="request"
	class="Lib.Auto.MResource.MResourceBean" />
<!--
//////////////////////////////////////DATABASE CONNECTION//////////////////////////////////////////////////////////////////////// -->
<%
	//
	//   Filename: Index.jsp
	//   Form name:Course
	//
%>
<!--
//////////////////////////////////////FORM CONTROLS//////////////////////////////////////////////////////////////////////// -->
<html>
<head>
<title>AutoLib</title>
<script language="javascript" src="/AutoLib/popcalendar.js"></script>
<link rel="stylesheet" type="text/css" href="/AutoLib/style.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/jquery-ui.css" />
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jquery-3.1.0.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jquery-ui.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/datepicker.js"></script>


<%
	response.setHeader("Pragma", "No-cache");
	response.setHeader("Cache-Control", "no-cache");
	response.setDateHeader("Expires", 0);
%>
</head>

<!--<body background="/AutoLib/soft.jpg" onload="load()">-->
<body background="/AutoLib/soft.jpg" onkeydown="myFunction(event)">

	<!-- Style Sheet -->

	<form name=MResource method="post" action=./MResourceServlet>
		<br> <br> <br>

		<h2>Missing&nbsp;Book&nbsp;Entry</h2>
		<center>
			<table align="center" class="contentTable" width="45%">
				<td>
					<table align="center" width="90%">
						<tr>
							<td>&nbsp;</td>
						</tr>
						<tr>
							<td Class="addedit">Access&nbsp;No
							<td><input type=text name=accno size=15 maxlength=15>
								<!-- onFocus="bookopt()" --></td>


							<td Class="addedit">&nbsp;&nbsp;Doc.Type&nbsp;&nbsp;</td>
							<td><select name="type" size="1">
									<option value="BOOK">BOOK</option>
									<option value="BOOK BANK">BOOK BANK</option>
									<option value="STANDARD">STANDARD</option>
									<option value="BACK VOLUME">BACK VOLUME</option>
									<option value="REPORT">REPORT</option>
									<option value="THESIS">THESIS</option>
									<option value="PROCEEDING">PROCEEDING</option>
									<option value="NON BOOK">NON BOOK</option>
									<option value="CLIPPING">CLIPPING</option>

							</select></td>
						</tr>
						<tr>
							<td Class="addedit">Availability&nbsp;&nbsp;</td>
							<td><select name="availability" size="1">
									<option value="LOST">LOST</option>
									<option value="MISSING">MISSING</option>
									<option value="WITHDRAWN">WITHDRAWN</option>
									<option value="DAMAGED">DAMAGED</option>

							</select></td>

							<td colspan="1" Class="addedit">&nbsp;&nbsp;&nbsp;Date
							<TD colspan="2">
								<%-- 	<INPUT name=mdate size=10  onfocus=this.blur(); value="<%=Security.CalenderDate()%>"> --%>
								<INPUT name=mdate size=10 id="datepicker"
								value="<%=Security.CalenderDate()%>">

							</td>
						</tr>

						<tr>
						<tr>
							<td colspan=4 align=center>

								<center>

									<input type=button name=Save Class="submitButton" value=Save
										onclick=SaveRec()> <input onclick="search_record()"
										type=button Class="submitButton" Value=Search>&nbsp; <input
										onclick="DelRec()" type=button Class="submitButton"
										Value=Delete> <input type=Reset name=Clear
										Class="submitButton" Value=Clear>

								</center> <input type=hidden name=flag>
							</td>
						</tr>
					</table>
					</center>
				</td>
			</table>
		</center>
	</form>
</body>
</html>

<!--
//////////////////////////////////////JAVA SCRIPT USEING CLIENT SIDE///////////////////////////////////////////////// -->

<%
	String valid = request.getParameter("check");
	if (valid != null) {
		if (request.getParameter("check") != null) {
			if (valid.equals("SaveMResource")) {
%>
<script language="JavaScript">
			 alert("Record Inserted Successfully!");
       			document.MResource.accno.focus();
				document.MResource.flag.value="none";
				document.MResource.accno.value="";
		     </script>
<%
	}
			if (valid.equals("UpdateCheck")) {
%>
<script language="JavaScript">
				 alert("Record Already Exist / Record Not Found !");
	       			document.MResource.accno.focus();
					document.MResource.flag.value="none";
					document.MResource.accno.value="";
					document.MResource.reset();
			     </script>
<%
	}
			if (valid.equals("BookCheck")) {
%>
<script language="JavaScript">

								 document.MResource.accno.value="<%=beanobject.getAccessno()%>";
									document.MResource.type.value="<%=beanobject.getDoctype()%>";
									document.MResource.availability.value="<%=beanobject.getAvailability()%>";
									document.MResource.mdate.value="<%=Security.getdate(beanobject.getMdate())%>";
							     </script>
<%
	}

			if (valid.equals("FailCheck")) {
%>
<script language="JavaScript">
	    alert("Record Not Found");
	    document.MResource.accno.focus();
		document.MResource.flag.value="none";
		document.MResource.accno.value="";
		document.MResource.reset();
	    </script>
<%
	}

			if (valid.equals("deleteCheck")) {
%>
<script language="JavaScript">
//alert("Inside deleteCheck!!");
</script>

<%System.out.println("::::accno:::"+beanobject.getAccessno()); %>
<script type="text/javascript">

document.MResource.accno.value="<%=beanobject.getAccessno()%>";
document.MResource.type.value="<%=beanobject.getDoctype()%>";
document.MResource.availability.value="<%=beanobject.getAvailability()%>";
document.MResource.mdate.value="<%=Security.getdate(beanobject.getMdate())%>";

			msg=confirm("Are You Sure To Delete?");
			if(msg)
			{
			 document.MResource.flag.value="Confirmdelete";
		   	document.MResource.submit();	
			}
		   </script>
<%
	}

			if (valid.equals("DeleteFine")) {
%>
<script language="JavaScript">
							alert("Record Deleted Successfully!");
							document.MResource.accno.value="";
							document.MResource.reset();
							
						   </script>
<%
	}

			if (valid.equals("RecordNot")) {
%>
<script language="JavaScript">

							document.MResource.accno.value="<%=beanobject.getAccessno()%>";
							document.MResource.type.value="<%=beanobject.getDoctype()%>";
							document.MResource.availability.value="<%=beanobject.getAvailability()%>";
							document.MResource.mdate.value="<%=beanobject.getMdate()%>";
						    alert("Record Not Present!!!");
						   </script>
<%
	}
		}
	}
%>

<script language=javascript>

function search_record(){
	//alert("Inside func::");
	document.MResource.flag.value="book";
	document.MResource.submit();
			}

function myFunction(event) 
{
    var x = event.keyCode;
    
    if(x=="13")
    {
    	event.preventDefault();
    	search_record();
    }
}
function SaveRec(){
             document.MResource.method="post";
             
         if(chk_mc()){
			  								
				    document.MResource.flag.value="save";
		         	document.MResource.submit();

					
					}
					else
	{
	alert("Insufficent Data");
	}
					}

function chk_mc(){
var flag_cs;
var c;
var mc=document.MResource.accno.value;
if(mc=="")
{
				document.MResource.accno.focus();
				document.MResource.flag.value="none";
				document.MResource.accno.value="";
				return false;
				}
				else{
		                    return true;
		                         } 

 }
 
 
function load(){
	document.MResource.accno.focus();

		 }
		 
function DelRec(){
	document.MResource.method="get";
	if (document.MResource.accno.value==""){
		document.MResource.accno.focus();
					alert("Insufficent Data");
					document.MResource.flag.value="none";
					document.MResource.accno.value="";
					
					}
					else{
					  document.MResource.flag.value="delete";
					  document.MResource.submit();
					 }                         
					
	} 

</script>

