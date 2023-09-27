
<%
	String URole = session.getAttribute("UserRights").toString().trim();
	if (URole.equalsIgnoreCase("3") || URole.equalsIgnoreCase("5")
			|| URole.equalsIgnoreCase("6")
			|| URole.equalsIgnoreCase("7")) {
		response.sendRedirect("sessiontimeout.jsp");
	}
%>
<%@ include file="/Tree/demoFrameset.jsp"%>
<%@ page language="java" session="true" buffer="12kb"
	import="java.sql.*"%>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/button_css.css" />
<jsp:useBean id="bean" scope="request"
	class="Lib.Auto.Journal.journalbean" />
	
<!--
//////////////////////////////////////DATABASE CONNECTION//////////////////////////////////////////////////////////////////////// -->
<%
	//
	//   Filename: Index.jsp
	//   Form name:journal
	//
%>
<!--
//////////////////////////////////////////////////////////////////FORM CONTROLS//////////////////////////////////////////////////////////////////////// -->
<html>
<head>
<meta charset="ISO-8859-1">
<title>AutoLib</title>
<meta http-equiv="pragma" content="no-cache" />
<link rel="stylesheet" type="text/css" href="/AutoLib/style.css">
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/jquery-ui.css"/>
<%-- <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/jquery-ui-flick.css"/> --%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-2.2.4.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-ui.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/searchJournalMas.js"></script>

</head>
<body onload="focusText()" background="/AutoLib/soft.jpg">

	<form name="jrl" method="get" action=./JournalServlet>
		<br><br><br>

		<h2>Journal&nbsp;Master</h2>
		
			<table align="center" class="contentTable" width="750">
			<tr><td>&nbsp;</td></tr>
			<tr>
				<td>
					<table border="0" width="60%" cellspacing="0" cellpadding="2" align="center">
						<tr>
<div class="search-container">
	<div class="ui-widget">
						
		<td Class="addedit">Jnl.&nbsp;Code</td>
<!-- 		<td><input name="jcode" size="10" maxlength=8 onKeyUp="return journal_code_val()"></td> -->
		<td><input name="jcode" id="searchJournalCode" class="searchJournal" onkeyup="showJournalCode(this.value);" size="10" maxlength=8 ></td>
		<td Class="addedit">Jnl.&nbsp;Name</td><td><input name="jname" size="35" id="searchJournalName" class="searchJournal" onkeyup="showJournalName(this.value);"></td>
		
    </div>
</div>		
		<td Class="addedit"><input type="button" Value="Find" name="find_name" Class="submitButton" maxlength=150 onclick='Find_Value("Nam")'></td>
		
									<td Class="addedit">DocType</td>
									<td><select size="1" name="doc"  style="width: 120px">
									<option selected value="JOURNAL">JOURNAL</option>
									<option value="MAGAZINE">MAGAZINE</option>
									<option value="NEWSLETTER">NEWS LETTER</option>
									<option value="OTHERS">OTHERS</option>
									</select></td>

			</tr>
			<tr>
			<td></td>
			
<div class="search-container">
	<div class="ui-widget">
			
			<td Class="addedit">Publisher</td> 
<!-- 			<td colspan="2"><input type="text" size="45" readonly="true" name="pname" id="searchJournalPub" class="searchJournal" onkeyup="showJournalPub(this.value);" value="NIL"></td> -->
			<td colspan="2"><input type="text" size="45" name="pname" id="searchJournalPub" class="searchJournal" onkeyup="showJournalPub(this.value);" value="NIL"></td>
			
  </div>
</div>			
			
			<td Class="addedit"><input type="button" value="Find" name="find_pub" Class="submitButton" onclick='Find_Value("Pub")'></td>
			<td Class="addedit">Frequency</td><td><select name="jfreq" style="width: 120px">
									<option value="DAILY">DAILY</option>
									<option value="WEEKLY TWO">WEEKLY TWO</option>
									<option value="WEEKLY">WEEKLY</option>
									<option vlue="FORTNIGHTLY">FORTNIGHTLY</option>
									<option selected value="MONTHLY">MONTHLY</option>
									<option value="BIMONTHLY">BIMONTHLY</option>
									<option value="QUARTERLY">QUARTERLY</option>
									<option value="HALF YEARLY">HALF&nbsp;YEARLY</option>
									<option value="ANNUAL">ANNUAL</option>
									<option value="OTHERS">OTHERS</option>
							</select></td>
			 
			</tr>					
			<tr>
			<td></td>
			
<div class="search-container">
	<div class="ui-widget">
			
<td Class="addedit">Department</td>
<!-- <td Class="addedit" colspan=2><input type="text" name="dname" id="searchJournalDept" class="searchJournal" onkeyup="showJournalDept(this.value);" value="NIL" readonly size="45"></td> -->
<td Class="addedit" colspan=2><input type="text" name="dname" id="searchJournalDept" class="searchJournal" onkeyup="showJournalDept(this.value);" value="NIL" size="45"></td>

   </div>
</div>
<td Class="addedit"><input type="button" value="Find" name="find_dept" Class="submitButton"	onclick='Find_Value("Dept")'></td>

	<td Class="addedit">Country</td> 
	<td colspan="2">
	<select size="1" name="jcountry" style="width: 120px">
									<option selected value="INDIA">INDIA</option>
									<option value="USA">USA</option>
									<option value="UK">UK</option>
									<option value="GERMANY">GERMANY</option>
									<option value="KOREA">KOREA</option>
									<option value="JAPAN">JAPAN</option>
									<option value="SWEDEN">SWEDEN</option>
									<option value="HOLAND">HOLAND</option>
									<option value="NORWAY">NORWAY</option>
									<option value="CHINA">CHINA</option>
									<option value="FRANCE">FRANCE</option>
									<option value="ITALY">ITALY</option>
									<option value="OTHERS">OTHERS</option>
							</select></td>

</tr>

<tr>
<td></td>

<div class="search-container">
	<div class="ui-widget">

<td Class="addedit">Subject</td> 
<!-- <td colspan="2"><input type="text" size="45" name="sname" id="searchJournalSubject" class="searchJournal" onkeyup="showJournalSubject(this.value);" value="NIL" readOnly=true></td> -->
<td colspan="2"><input type="text" size="45" name="sname" id="searchJournalSubject" class="searchJournal" onkeyup="showJournalSubject(this.value);" value="NIL" ></td>

   </div>
</div>

<td Class="addedit"><input type="button" value="Find" name="find_sub" Class="submitButton" onclick='Find_Value("Sub")'></td>

<td Class="addedit">Language</td> <td width="42%" colspan="3"><input name="jlang" style="width: 115px" maxlength=50></td>
</tr>


<tr>

<td></td>

<td  Class="addedit">ISSN</td><td colspan=2><input name="jissn" size="15" maxlength=50>

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>Type</b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<select size="1" name="jtype">
									<option selected value="PAYMENT">PAYMENT</option>
									<option value="EXCHANGE">EXCHANGE</option>
									<option value="GRATIS">GRATIS</option>
									<option value="SAMPLE">SAMPLE</option>
									<option value="OTHERS">OTHERS</option>

							</select></td>
							<td></td>
							
<td Class="addedit">Deli.Mode</td> 
							<td><select size="1" name="jdeli" style="width: 120px">
									<option selected value="SEA MAIL">SEA&nbsp;MAIL</option>
									<option value="AIR MAIL">AIR&nbsp;MAIL</option>
									<option value="SURFACE MAIL">SURFACE&nbsp;MAIL</option>
									<option value="HAND DELIVERY">HAND&nbsp;DELIVERY</option>
									<option value="POST MAIL">POST&nbsp;MAIL</option>
									<option value="OTHERS">OTHERS</option>
							</select></td>

</tr>							

<tr>
<td></td>
<td Class="addedit">Remarks</td><td colspan="5"><input name="jremarks" maxlength=150 size="88"></td>
<td></td></tr>
<tr><td>&nbsp;</td></tr>
</table></td></tr></table>
					
					
					
			
			<p align="center">
			
<input type="button" value="New" Class="submitButton" name="new" onclick="NewRecord()">
 <input type="button" value="Save" Class="submitButton" name="save" onclick="SaveRecord()">
  <input type="button" value="Delete" Class="submitButton" name="delete" onclick="DeleteRecord()"> 
  <input type="submit"	value="Search" Class="submitButton" name="search" onclick="SearchRecord()"> 
  <input type="reset" value="Clear" Class="submitButton" name="clear">
   <input type="hidden" name="flag" value="search">
    <input type="hidden" name="jnlflag">
			
			<input type="hidden" name="dcode" value="1">
			<input name="scode" type="hidden" value="1">
				<input name="jpub" type="hidden" size="15" value="1">
			
			</p>
			
		
	</form>
</body>




</html>
<%
	String valid = request.getParameter("check");
	if (valid != null) {
		if (request.getParameter("check") != null) {
			if (valid.equals("newJournal")) {
%>
<script language="JavaScript">
document.jrl.jcode.value="<%=bean.getJcode()%>";
document.jrl.jcode.focus();
</script>
<%
	}

			if (valid.equals("UpdateCheck")) {
%>
<script language="JavaScript">
		document.jrl.jcode.value="<%=bean.getJcode()%>";
                document.jrl.jname.value="<%=bean.getJname()%>";
                document.jrl.jissn.value="<%=bean.getJissn()%>";
		document.jrl.jfreq.value="<%=bean.getJfreq()%>";
		document.jrl.jcountry.value="<%=bean.getJcountry()%>";
		document.jrl.jlang.value="<%=bean.getJlang()%>";
		document.jrl.jdeli.value="<%=bean.getJdeli()%>";
		document.jrl.jtype.value="<%=bean.getJtype()%>";
		document.jrl.jremarks.value="<%=bean.getJremarks()%>";
		document.jrl.pname.value="<%=bean.getJpname()%>";
		document.jrl.dname.value="<%=bean.getJdname()%>";
		document.jrl.sname.value="<%=bean.getJsname()%>";
		document.jrl.doc.value="<%=bean.getDoc_Type()%>";

                msg=confirm("Record Already Exists Are You Sure To Update?");
                 if(msg)
                   {
				document.jrl.flag.value="update";
		         	document.jrl.submit();
		   }
			    </script>

<%
	}

			if (valid.equals("UpdateJournal")) {
%>
<script language="JavaScript">
			alert("Record Updated Successfully!");
			document.jrl.flag.value="new";
			document.jrl.submit();
		   	</script>
<%
	}

			if (valid.equals("DeleteJournal")) {
%>
<script language="JavaScript">
	    alert("Record Deleted Successfully");
	    document.jrl.flag.value="new";
	    document.jrl.submit();
	    </script>
<%
	}

			if (valid.equals("SaveJournal")) {
%>
<script language="JavaScript">
			 alert("Record Inserted Successfully!");
			 document.jrl.flag.value="new";
			document.jrl.submit();
		     </script>
<%
	}

			if (valid.equals("SuccessJournal")) {
%>
<script language="JavaScript">

		document.jrl.jcode.value="<%=bean.getJcode()%>";
                document.jrl.jname.value="<%=bean.getJname()%>";
                document.jrl.jissn.value="<%=bean.getJissn()%>";
		document.jrl.jfreq.value="<%=bean.getJfreq()%>";
		document.jrl.jcountry.value="<%=bean.getJcountry()%>";
		document.jrl.jlang.value="<%=bean.getJlang()%>";
		document.jrl.jdeli.value="<%=bean.getJdeli()%>";
		document.jrl.jtype.value="<%=bean.getJtype()%>";
		document.jrl.jremarks.value="<%=bean.getJremarks()%>";
		document.jrl.pname.value="<%=bean.getJpname()%>";
		document.jrl.dname.value="<%=bean.getJdname()%>";
		document.jrl.sname.value="<%=bean.getJsname()%>";
		document.jrl.doc.value="<%=bean.getDoc_Type()%>";

</script>
<%
	}
			if (valid.equals("FailureJournal")) {
%>
<script language="JavaScript">
	    alert("Record Not Found");
	    document.jrl.flag.value="new";
	    document.jrl.submit();
	    </script>
<%
	}
			if (valid.equals("deleteCheck")) {
%>
<script language="JavaScript">
		document.jrl.jcode.value="<%=bean.getJcode()%>";
    	document.jrl.jname.value="<%=bean.getJname()%>";
    	document.jrl.jissn.value="<%=bean.getJissn()%>";
		document.jrl.jfreq.value="<%=bean.getJfreq()%>";
		document.jrl.jcountry.value="<%=bean.getJcountry()%>";
		document.jrl.jlang.value="<%=bean.getJlang()%>";
		document.jrl.jdeli.value="<%=bean.getJdeli()%>";
		document.jrl.jtype.value="<%=bean.getJtype()%>";
		document.jrl.jremarks.value="<%=bean.getJremarks()%>";
		document.jrl.pname.value="<%=bean.getJpname()%>";
		document.jrl.dname.value="<%=bean.getJdname()%>";
		document.jrl.sname.value="<%=bean.getJsname()%>";
		document.jrl.doc.value="<%=bean.getDoc_Type()%>";
	msg = confirm("Are You Sure To Delete?");
	if (msg) {

		document.jrl.flag.value = "Confirmdete";
		document.jrl.submit();

	}
</script>
<%
	}
			if (valid.equals("RefferdJournal")) {
%>
<script language="JavaScript">
	alert("You can't delete since the Author has been referred in other masters");
	document.jrl.flag.value = "new";
	document.jrl.submit();
</script>
<%
	}

		}
	}
%>
<script>
	function home() {
		location.href = "/AutoLib/Home.jsp";
	}

	function Logout() {
		location.href = "/AutoLib/index.html";
	}
	function NewRecord() {
		document.jrl.method = "get";
		document.jrl.flag.value = "new";
		document.jrl.submit();

	}

	function SaveRecord() {
		document.jrl.method = "get";
		if (chk()) {
			document.jrl.flag.value = "save";
			document.jrl.submit();
		}

		else {
			alert("Insufficent Data");
		}

	}

	function chk() {
		var flag_s;
		var i;
		var sp = document.jrl.jname.value;
		if (sp == "") {
			document.jrl.jname.focus();
			document.jrl.flag.value = "none";
			document.jrl.jname.value = "";
			return false;
		} else {
			for (i = 0; i < document.jrl.jname.value.length; i++) {
				if (document.jrl.jname.value.charAt(i) == " ") {
					flag_s = 0;
				} else {
					flag_s = 1;
				}
			}
			if (flag_s == 0) {
				document.jrl.jname.focus();
				document.jrl.jname.value = "";
				return false;
			} else if (document.jrl.jcode.value == "") {
				document.jrl.jcode.focus();
				return false;
			} else {
				return true;
			}
		}
	}

	function DeleteRecord() {
		document.jrl.method = "get";
		if (document.jrl.jcode.value == "") {
			document.jrl.jcode.focus();
			alert("Insufficent Data");
			document.jrl.flag.value = "new";
			document.jrl.submit();
		} else {
			document.jrl.flag.value = "delete";
			document.jrl.submit();

		}
	}

	function SearchRecord() {
		document.jrl.method = "get";
		var no = document.jrl.jcode.value;
		if (no == "") {
			document.jrl.jcode.focus();
			alert("Insufficent Data");
			document.jrl.flag.value = "new";
			document.jrl.submit();
		} else {
			document.jrl.flag.value = "search";
			document.jrl.submit();
		}
	}
	function journal_code_val() {
		if ((isNaN(document.jrl.jcode.value))
				|| (document.jrl.jcode.value == ' ')) {
			document.jrl.jcode.select();
			document.jrl.jcode.value = "";

		}
	}
	function Find_Value(val) {

		winpopup = window
				.open(
						"search.jsp?flag=" + val,
						"popup",
						"height=400,width=600,top=100,left=100,toolbar=no,status=yes,menubar=no,scrollbars=yes");
	}
	function focusText() {
		document.jrl.jname.focus();
	}
</script>


