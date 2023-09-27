<%@ include file="/Tree/demoFrameset.jsp"%>
<%@ page language="java" errorPage="/Error/ErrorPage.jsp" session="true"
	buffer="12kb" import="java.sql.*,java.util.*"%>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/button_css.css" />
<html>
<head>
<meta charset="ISO-8859-1">
<title>AutoLib</title>
<meta http-equiv="pragma" content="no-cache">
<link rel="stylesheet" type="text/css" href="/AutoLib/style.css">
<!-- <meta http-equiv="pragma" content="no-cache"> -->
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-2.2.4.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-ui.js"></script>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/jquery-ui.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/searchQueryBuilder.js"></script>

</head>
<body background="/AutoLib/soft.jpg">



	<script language="javascript">
		function selec1() {
			var a = (query.LIST1.options[query.LIST1.selectedIndex].value);
			var l = document.query.condition1.length;
			for (i = 0; i < l; i++) {
				for (j = 0; j < l; j++) {
					document.query.condition1.options[i] = null;
				}
			}
			if((a=="year_pub") || (a=="Bprice")  || (a=="invoice_date") || (a=="received_date") ) 			
			{
				query.condition1.options[0]= new Option('=                   ','=');
				query.condition1.options[1]= new Option('Less than                  ','<');
				query.condition1.options[2]= new Option('Greater than                  ','>');
				query.condition1.options[3]= new Option('Less than or Equal to                  ','<=');
				query.condition1.options[4]= new Option('Greater than or Equal to','>=');

				}
				else
				{
				query.condition1.options[0]= new Option('Like             ','like');
				query.condition1.options[1]= new Option('Starting with         ','start');
				query.condition1.options[2]= new Option('Ending with          ','end');
				query.condition1.options[3]= new Option('=             ','=');
				// query.condition1.options[4]= new Option('Word             ','word');
				}
		}
		function selec2() {
			var a = (query.LIST2.options[query.LIST2.selectedIndex].value);
			var l = document.query.condition2.length;
			for (i = 0; i < l; i++) {
				for (j = 0; j < l; j++) {
					document.query.condition2.options[i] = null;
				}
			}
			if((a=="year_pub") || (a=="Bprice")  || (a=="invoice_date") || (a=="received_date") ) 
			{
				query.condition2.options[0]= new Option('=                   ','=');
				query.condition2.options[1]= new Option('Less than                  ','<');
				query.condition2.options[2]= new Option('Greater than                  ','>');
				query.condition2.options[3]= new Option('Less than or Equal to                  ','<=');
				query.condition2.options[4]= new Option('Greater than or Equal to','>=');

				}
				else
				{
				query.condition2.options[0]= new Option('Like             ','like');
				query.condition2.options[1]= new Option('Starting with         ','start');
				query.condition2.options[2]= new Option('Ending with          ','end');
				query.condition2.options[3]= new Option('=             ','=');
				// query.condition1.options[4]= new Option('Word             ','word');
				}
		}
		function selec3() {
			var a = (query.LIST3.options[query.LIST3.selectedIndex].value);
			var l = document.query.condition3.length;
			for (i = 0; i < l; i++) {
				for (j = 0; j < l; j++) {
					document.query.condition3.options[i] = null;
				}
			}
			if((a=="year_pub") || (a=="Bprice")  || (a=="invoice_date") || (a=="received_date") ) 
			{
				query.condition3.options[0]= new Option('=                   ','=');
				query.condition3.options[1]= new Option('Less than                  ','<');
				query.condition3.options[2]= new Option('Greater than                  ','>');
				query.condition3.options[3]= new Option('Less than or Equal to                  ','<=');
				query.condition3.options[4]= new Option('Greater than or Equal to','>=');

				}
				else
				{
				query.condition3.options[0]= new Option('Like             ','like');
				query.condition3.options[1]= new Option('Starting with         ','start');
				query.condition3.options[2]= new Option('Ending with          ','end');
				query.condition3.options[3]= new Option('=             ','=');
				// query.condition1.options[4]= new Option('Word             ','word');
				}
		}
		function selec4() {
			var a = (query.field4.options[query.field4.selectedIndex].value);
			var l = document.query.operator4.length;
			for (i = 0; i < l; i++) {
				for (j = 0; j < l; j++) {
					document.query.operator4.options[i] = null;
				}
			}
			if((a=="year_pub") || (a=="Bprice")  || (a=="invoice_date") || (a=="received_date") ) 
			{

				query.operator4.options[0] = new Option('=                   ',
						'=');
				query.operator4.options[1] = new Option('LT                  ',
						'<');
				query.operator4.options[2] = new Option('GT                  ',
						'>');
				query.operator4.options[3] = new Option('LE                  ',
						'<=');
				query.operator4.options[4] = new Option('GE                  ',
						'>=');
				query.operator4.options[5] = new Option('Between        ',
						'between');
				query.operator4.options[6] = new Option('One of          ',
						'in');
			} else {
				query.operator4.options[0] = new Option('=                ',
						'=');
				query.operator4.options[1] = new Option('Starting         ',
						'start');
				query.operator4.options[2] = new Option('Ending           ',
						'end');
				query.operator4.options[3] = new Option('Like             ',
						'like');
				query.operator4.options[4] = new Option('Word             ',
						'word');
			}
		}
		function selec5() {
			var a = (query.field5.options[query.field5.selectedIndex].value);
			var l = document.query.operator5.length;
			for (i = 0; i < l; i++) {
				for (j = 0; j < l; j++) {
					document.query.operator5.options[i] = null;
				}
			}
			if((a=="year_pub") || (a=="Bprice")  || (a=="invoice_date") || (a=="received_date") ) 
			{

				query.operator5.options[0] = new Option('=                   ',
						'=');
				query.operator5.options[1] = new Option('LT                  ',
						'<');
				query.operator5.options[2] = new Option('GT                  ',
						'>');
				query.operator5.options[3] = new Option('LE                  ',
						'<=');
				query.operator5.options[4] = new Option('GE                  ',
						'>=');
				query.operator5.options[5] = new Option('Between        ',
						'between');
				query.operator5.options[6] = new Option('One of          ',
						'in');
			} else {
				query.operator5.options[0] = new Option('=                ',
						'=');
				query.operator5.options[1] = new Option('Starting         ',
						'start');
				query.operator5.options[2] = new Option('Ending           ',
						'end');
				query.operator5.options[3] = new Option('Like             ',
						'like');
				query.operator5.options[4] = new Option('Word             ',
						'word');
			}
		}
		function selec6() {
			var a = (query.field6.options[query.field6.selectedIndex].value);
			var l = document.query.operator6.length;
			for (i = 0; i < l; i++) {
				for (j = 0; j < l; j++) {
					document.query.operator6.options[i] = null;
				}
			}
			if((a=="year_pub") || (a=="Bprice")  || (a=="invoice_date") || (a=="received_date") ) 
			{

				query.operator6.options[0] = new Option('=                   ',
						'=');
				query.operator6.options[1] = new Option('LT                  ',
						'<');
				query.operator6.options[2] = new Option('GT                  ',
						'>');
				query.operator6.options[3] = new Option('LE                  ',
						'<=');
				query.operator6.options[4] = new Option('GE                  ',
						'>=');
				query.operator6.options[5] = new Option('Between        ',
						'between');
				query.operator6.options[6] = new Option('One of          ',
						'in');
			} else {
				query.operator6.options[0] = new Option('=                ',
						'=');
				query.operator6.options[1] = new Option('Starting         ',
						'start');
				query.operator6.options[2] = new Option('Ending           ',
						'end');
				query.operator6.options[3] = new Option('Like             ',
						'like');
				query.operator6.options[4] = new Option('Word             ',
						'word');
			}
		}

		function selec7() {
			var a = (query.field7.options[query.field7.selectedIndex].value);
			var l = document.query.operator7.length;
			for (i = 0; i < l; i++) {
				for (j = 0; j < l; j++) {
					document.query.operator7.options[i] = null;
				}
			}
			if((a=="year_pub") || (a=="Bprice")  || (a=="invoice_date") || (a=="received_date") ) 
			{

				query.operator7.options[0] = new Option('=                   ',
						'=');
				query.operator7.options[1] = new Option('LT                  ',
						'<');
				query.operator7.options[2] = new Option('GT                  ',
						'>');
				query.operator7.options[3] = new Option('LE                  ',
						'<=');
				query.operator7.options[4] = new Option('GE                  ',
						'>=');
				query.operator7.options[5] = new Option('Between        ',
						'between');
				query.operator7.options[6] = new Option('One of          ',
						'in');
			} else {
				query.operator7.options[0] = new Option('=                ',
						'=');
				query.operator7.options[1] = new Option('Starting         ',
						'start');
				query.operator7.options[2] = new Option('Ending           ',
						'end');
				query.operator7.options[3] = new Option('Like             ',
						'like');
				query.operator7.options[4] = new Option('Word             ',
						'word');
			}
		}

		function order11() {
			if ((document.query.order1.value != "NO")
					&& (document.query.order2.value != "NO")) {
				if ((document.query.order1.value == document.query.order2.value)
						&& (document.query.order1.value != document.query.order3.value)) {
					alert("Cant select more then one order by value same option 1 and 2");
					document.query.order1[0].selected = true;
				}
			}
			if ((document.query.order1.value != "NO")
					&& (document.query.order3.value != "NO")) {
				if ((document.query.order1.value == document.query.order3.value)
						&& (document.query.order1.value != document.query.order2.value)) {
					alert("Cant select more then one order by value same option 1 and 3");
					document.query.order1[0].selected = true;
				}
			}

			if ((document.query.order1.value != "NO")
					&& (document.query.order3.value != "NO")) {
				if ((document.query.order1.value == document.query.order3.value)
						&& (document.query.order1.value == document.query.order2.value)) {
					alert("Cant select more then one order by value same option 1 and 2 and 3");
				}
			}
		}

		function order22() {
			if ((document.query.order1.value != "NO")
					&& (document.query.order2.value != "NO")) {
				if ((document.query.order1.value == document.query.order2.value)
						&& (document.query.order1.value != document.query.order3.value)) {
					alert("Cant select more then one order by value same option 1 and 2");
					document.query.order2[0].selected = true;
				}
			}
			if ((document.query.order3.value != "NO")
					&& (document.query.order2.value != "NO")) {
				if ((document.query.order2.value == document.query.order3.value)
						&& (document.query.order2.value != document.query.order1.value)) {
					alert("Cant select more then one order by value same option 2 and 3");
					document.query.order3[0].selected = true;
				}
			}
			if ((document.query.order3.value != "NO")
					&& (document.query.order2.value != "NO")) {
				if ((document.query.order2.value == document.query.order3.value)
						&& (document.query.order2.value == document.query.order1.value)) {
					alert("Cant select more then one order by value same option 1 and 2 and 3");
					document.query.order2[0].selected = true;
				}
			}
		}

		function order33() {
			if ((document.query.order1.value != "NO")
					&& (document.query.order3.value != "NO")) {
				if ((document.query.order1.value == document.query.order3.value)
						&& (document.query.order2.value != document.query.order3.value)) {
					alert("Cant select more then one order by value same option 1 and 3");
					document.query.order3[0].selected = true;
				}
			}
			if ((document.query.order2.value != "NO")
					&& (document.query.order3.value != "NO")) {
				if ((document.query.order2.value == document.query.order3.value)
						&& (document.query.order2.value != document.query.order1.value)) {
					alert("Cant select more then one order by value same option 2 and 3");
					document.query.order3[0].selected = true;
				}
			}
			if ((document.query.order2.value != "NO")
					&& (document.query.order3.value != "NO")) {
				if ((document.query.order2.value == document.query.order3.value)
						&& (document.query.order2.value == document.query.order1.value)) {
					alert("Cant select more then one order by value same option 1 and 2 and 3");
					document.query.order3[0].selected = true;
					document.query.order2[0].selected = true;
				}
			}
		}

		function validate() {
			
			var content="";
		    var chkitem=0;	
		    
		    var len = document.query.destField.length;    
		    for(var i=0; i<len; i++)
			{
		    	chkitem++;
		        content=content+","+document.query.destField[i].value;		
			}    
		    
		    if(chkitem >= 1)
		 	{	   
			   //alert(content);
		       document.query.flag1.value = content;     
		 	}
		    else if(chkitem == 0)
		    {
			  alert('Please select atleast one column !');
			  return false;
		    }
			
			
			if (t1() && t2() && t3()) {
				alert("Please Enter the Value ");

				return false;
			}

			else {
				if (((document.query.text1.value.length)
						+ (document.query.text2.value.length) + (document.query.text3.value)) < 3) {
					alert("Atleast three letters Should Be Entered ");
					return false;

				}
				return true;
			}

		}
		
		function clear_text()
		{
			document.query.action="index.jsp";	
			document.query.submit();
		}
		
		

		function t1() {
			if (document.query.text1.value == "") {

				return true;
			} else {
				return false;
			}
		}

		function t2() {
			if (document.query.text2.value == "") {
				return true;
			} else {
				return false;
			}
		}

		function t3() {
			if (document.query.text3.value == "") {
				return true;
			} else {
				return false;
			}
		}

		function User() {

			if (t1() && t2() && t3() && t4() && t5() && t6() && t7()) {
				alert("Atleast One Value Should Be Entered ");
				return false;
			}

			else {

				query.method = "post";
				query.action = "Userdefinedreport.asp";
				query.target = "_parent";
				query.submit();
			}
		}
	</script>


	<FORM NAME="query" ACTION="./QueryBuildServlet" METHOD="post"
		ONSUBMIT="return validate()">
		<br>
		<h2>Query Builder (Search & Report)</h2>

		<%
			if (request.getParameter("flags") != null) {
		%>
		<h3>-- Record Not Found --</h3>
		<%
			}
		%>
		<CENTER>
			<table align="center" class="contentTable" width="60%">
				<td>
					<table align="center" width="90%">
						<TR>
							<TD Class="addedit">Select the Field</TD>
							<TD Class="addedit">
								<CENTER>Operator</CENTER>
							</TD>
							<TD Class="addedit">
								<CENTER>Type the String to Search</CENTER>
							</TD>
							<TD Class="addedit">Logical</TD>
						</TR>
						<TR>
							<TD></TD>
							<TD></TD>
							<TD></TD>
							<TD></TD>
						</TR>
						<TR>
							<TD><SELECT NAME="LIST1" id="LIST1" SIZE="1" onChange="selec1()"
								style="font-family: verdana; font-size: 8pt">
		    <OPTION SELECTED VALUE="Title">Title</OPTION>
		    <OPTION VALUE="Access_no">Access no</OPTION>
            <OPTION VALUE="call_no">call no</OPTION>
		    <OPTION VALUE="Author_name">Author</OPTION>
		    <OPTION VALUE="sp_name">Publisher</OPTION>
		    <OPTION VALUE="edition">Edition</OPTION>
            <OPTION VALUE="location">Location</OPTION>
            <OPTION VALUE="availability">Availability</OPTION>
            <OPTION VALUE="document">document</OPTION> 
		    <OPTION VALUE="Sub_name">Subject</OPTION>
		    <OPTION VALUE="keywords">Keywords</OPTION>
		    <OPTION VALUE="year_pub">Publication Year</OPTION>
		    <OPTION VALUE="supplier">Supplier</OPTION>
            <OPTION VALUE="isbn">ISBN</OPTION>
            <OPTION VALUE="Dept_Name">Department</OPTION>
            <OPTION VALUE="media">Media</OPTION>
            <OPTION VALUE="binding">Binding</OPTION>
            <OPTION VALUE="received_date">Received date</OPTION>
            <OPTION VALUE="invoice_No">Invoice no</OPTION>
            <OPTION VALUE="invoice_date">Invoice date</OPTION>
            <OPTION VALUE="Bprice">BPrice</OPTION>
            <OPTION VALUE="purchase">Gift_Purchase</OPTION>
            <OPTION VALUE="add_field1">Add field1</OPTION>
            <OPTION VALUE="add_field2">Add field2</OPTION>
            <OPTION VALUE="add_field3">Add field3</OPTION>
							</SELECT></TD>


							<TD><SELECT NAME="condition1" SIZE="1"
								style="font-family: Verdana; font-size: 8pt">
									<OPTION VALUE="like" selected>Like</OPTION>
									<OPTION VALUE="=">=</OPTION>
									<OPTION VALUE="start">Starting With</OPTION>
									<OPTION VALUE="end">Ending With</OPTION>
							</SELECT></TD>
                    <div class="search-container">
	                     <div class="ui-widget">
                    							
							<TD><INPUT TYPE="text" NAME="text1" SIZE="45" id="search" class="search"></TD>
							
					    </div>
					</div>		
							<TD><SELECT SIZE="1" NAME="boolean1"
								style="font-size: 8pt; font-family: verdana">
									<OPTION VALUE="AND" SELECTED>AND</OPTION>
									<OPTION VALUE="OR">OR</OPTION>
									<OPTION VALUE="AND NOT">NOT</OPTION>
							</SELECT></TD>
						</TR>
						<TR ALIGN="center">
							<TD><SELECT SIZE="1" NAME="LIST2" id="LIST2" onChange="selec2()"
								style="font-family: Verdana; font-size: 8pt">
									 <OPTION SELECTED VALUE="Title">Title</OPTION>
		    <OPTION VALUE="Access_no">Access no</OPTION>
            <OPTION VALUE="call_no">call no</OPTION>
		    <OPTION VALUE="Author_name">Author</OPTION>
		    <OPTION VALUE="sp_name">Publisher</OPTION>
		    <OPTION VALUE="edition">Edition</OPTION>
            <OPTION VALUE="location">Location</OPTION>
            <OPTION VALUE="availability">Availability</OPTION>
            <OPTION VALUE="document">document</OPTION> 
		    <OPTION VALUE="Sub_name">Subject</OPTION>
		    <OPTION VALUE="keywords">Keywords</OPTION>
		    <OPTION VALUE="year_pub">Publication Year</OPTION>
		    <OPTION VALUE="supplier">Supplier</OPTION>
            <OPTION VALUE="isbn">ISBN</OPTION>
            <OPTION VALUE="Dept_Name">Department</OPTION>
            <OPTION VALUE="media">Media</OPTION>
            <OPTION VALUE="binding">Binding</OPTION>
            <OPTION VALUE="received_date">Received date</OPTION>
            <OPTION VALUE="invoice_No">Invoice no</OPTION>
            <OPTION VALUE="invoice_date">Invoice date</OPTION>
            <OPTION VALUE="Bprice">BPrice</OPTION>
            <OPTION VALUE="purchase">Gift_Purchase</OPTION>
            <OPTION VALUE="add_field1">Add field1</OPTION>
            <OPTION VALUE="add_field2">Add field2</OPTION>
            <OPTION VALUE="add_field3">Add field3</OPTION>
							</SELECT></TD>
							<TD><SELECT SIZE="1" NAME="condition2"
								style="font-family: Verdana; font-size: 8pt">
									<OPTION VALUE="like" selected>Like</OPTION>
									<OPTION VALUE="=">=</OPTION>
									<OPTION VALUE="start">Starting With</OPTION>
									<OPTION VALUE="end">Ending With</OPTION>
							</SELECT></TD>
					<div class="search-container">
	                     <div class="ui-widget">
							
							<TD><INPUT TYPE="text" NAME="text2" SIZE="45" id="search2" class="search"></TD>
							
				        </div>
				    </div>			
							<TD><SELECT SIZE="1" NAME="boolean2"
								style="font-family: Verdana; font-size: 8pt">
									<OPTION VALUE="AND" SELECTED>AND</OPTION>
									<OPTION VALUE="OR">OR</OPTION>
									<OPTION VALUE="AND NOT">NOT</OPTION>
							</SELECT></TD>
						</TR>
						<TR>
							<TD><SELECT SIZE="1" NAME="LIST3" id="LIST3" onChange="selec3()"
								style="font-family: Verdana; font-size: 8pt">
									 <OPTION SELECTED VALUE="Title">Title</OPTION>
		    <OPTION VALUE="Access_no">Access no</OPTION>
            <OPTION VALUE="call_no">call no</OPTION>
		    <OPTION VALUE="Author_name">Author</OPTION>
		    <OPTION VALUE="sp_name">Publisher</OPTION>
		    <OPTION VALUE="edition">Edition</OPTION>
            <OPTION VALUE="location">Location</OPTION>
            <OPTION VALUE="availability">Availability</OPTION>
            <OPTION VALUE="document">document</OPTION> 
		    <OPTION VALUE="Sub_name">Subject</OPTION>
		    <OPTION VALUE="keywords">Keywords</OPTION>
		    <OPTION VALUE="year_pub">Publication Year</OPTION>
		    <OPTION VALUE="supplier">Supplier</OPTION>
            <OPTION VALUE="isbn">ISBN</OPTION>
            <OPTION VALUE="Dept_Name">Department</OPTION>
            <OPTION VALUE="media">Media</OPTION>
            <OPTION VALUE="binding">Binding</OPTION>
            <OPTION VALUE="received_date">Received date</OPTION>
            <OPTION VALUE="invoice_No">Invoice no</OPTION>
            <OPTION VALUE="invoice_date">Invoice date</OPTION>
            <OPTION VALUE="Bprice">BPrice</OPTION>
            <OPTION VALUE="purchase">Gift_Purchase</OPTION>
            <OPTION VALUE="add_field1">Add field1</OPTION>
            <OPTION VALUE="add_field2">Add field2</OPTION>
            <OPTION VALUE="add_field3">Add field3</OPTION>
							</SELECT></TD>
							<TD><SELECT SIZE="1" NAME="condition3"
								style="font-size: 8pt; font-family: Verdana">
									<OPTION VALUE="like" selected>Like</OPTION>
									<OPTION VALUE="=">=</OPTION>
									<OPTION VALUE="start">Starting With</OPTION>
									<OPTION VALUE="end">Ending With</OPTION>
							</SELECT></TD>
							
			<div class="search-container">
	             <div class="ui-widget">
							
							<TD><INPUT TYPE="text" NAME="text3" SIZE="45" id="search3" class="search"></TD>
							
			    </div>
			</div>				
							<TD></TD>
						</TR>
						
						<BR>
						<BR>
						<TR>
							<td colspan="3"><center>
									<input type="submit" Class="submitButton" value="search"
										name="B1"> <input type="reset" Class="submitButton"
										value="Clear" name="B2" onclick="clear_text()"> <input type="hidden"
										name="hid" value="search">
								</center></td>
						
							<td Class="addedit">Order&nbsp;By&nbsp;&nbsp;&nbsp;<select size="4" name="orderBy" multiple>
									<OPTION VALUE="Access_no">Access no</OPTION>
            <OPTION VALUE="call_no">call no</OPTION>
		    <OPTION VALUE="Author_name">Author</OPTION>
		    <OPTION VALUE="sp_name">Publisher</OPTION>
		    <OPTION VALUE="edition">Edition</OPTION>
            <OPTION VALUE="location">Location</OPTION>
            <OPTION VALUE="availability">Availability</OPTION>
            <OPTION VALUE="document">document</OPTION> 
		    <OPTION VALUE="Sub_name">Subject</OPTION>
		    <OPTION VALUE="keywords">Keywords</OPTION>
		    <OPTION VALUE="year_pub">Publication Year</OPTION>
		    <OPTION VALUE="supplier">Supplier</OPTION>
            <OPTION VALUE="isbn">ISBN</OPTION>
            <OPTION VALUE="Dept_Name">Department</OPTION>
            <OPTION VALUE="media">Media</OPTION>
            <OPTION VALUE="binding">Binding</OPTION>
            <OPTION VALUE="received_date">Received date</OPTION>
            <OPTION VALUE="invoice_No">Invoice no</OPTION>
            <OPTION VALUE="invoice_date">Invoice date</OPTION>
            <OPTION VALUE="Bprice">BPrice</OPTION>
            <OPTION VALUE="purchase">Gift_Purchase</OPTION>
            <OPTION VALUE="add_field1">Add field1</OPTION>
            <OPTION VALUE="add_field2">Add field2</OPTION>
            <OPTION VALUE="add_field3">Add field3</OPTION>
							</select></td>
							
						</tr>
					</TABLE>
					</CENTER>
				</td>
			</table>

			<br>

			<input type="hidden" name="flag1">
			
			<table border="0" cellspacing="0" cellpadding="3" align="center">
				<tr>
					<td rowspan="16" align="center"><select size="16"
						name="sourceField" multiple>

							<option value="access_no">Access No</option>
							<option value="author_name">Author Name</option>
							<option value="title">Title</option>
							<option value="call_no">Call No</option>
							<option value="edition">Edition</option>
						
                            <option value="year_pub">Year</option>
							<option value="isbn">ISBN</option>							
							<option value="bprice">Price</option>
							<option value="net_price">Net Price</option>
							<option value="received_date">Recv.Date</option>
                            <option value="invoice_no">Invoice No</option>
							<option value="invoice_date">Invoice.Date</option>														
							
							<option value="dept_name">Department</option>
							<option value="sub_name">Subject</option>
							<option value="sp_name">Publisher</option>
							<option value="supplier">Supplier</option>
							<option value="availability">Availability</option>
							<option value ="document">Document</option>
							<option value="language">Language</option>
							<option value="location">Location</option>
							<option value="keywords">Keywords</option>
							<option value="volno">Volume No</option>
							<option value="purchase">Purchase Mode</option>
							<option value="pages">Pages</option>

					</select></td>

					<td rowspan="16" valign="middle" align="center">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					 <a
						href="javascript:moveSelectedOptions(document.query.destField,document.query.sourceField)">
							<img src="<%=request.getContextPath()%>/images/arrow_left.jpg"
							border="0" alt="Remove" width="20">
					</a>
					<a
						href="javascript:moveSelectedOptions(document.query.sourceField,document.query.destField)">
							<img src="<%=request.getContextPath()%>/images/arrow_right.jpg"
							border="0" alt="Add" width="20">
					</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</td>

					<td rowspan="16" align="center"><select size="16"
						name="destField" multiple>
					</select></td>

				</tr>
			</table>




<input type="hidden" name="flag">


		</CENTER>


	</form>
	</DIV>
	</CENTER>

	<script language='javascript'>
	
    $( "#search" ).autocomplete({
    	width: 500,
        max: 20,
        delay: 100,
        minLength: 1,
        autoFocus: false,
        cacheLength: 1,
        scroll: true,
        highlight: false,
    	source: function (request, response) {
    		var type = document.getElementById('LIST1').value;
            $.ajax({
                url: "/AutoLib/QueryBuilder/QueryBuildServlet",
                type: "GET",
                async : false,
                dataType: "json",
                data : {
                	text1 : request.term ,
                	flag  : type
				 },
                success: function (data) {
					
					//response(data);
					response ($.map(data, function (item)
            {
            console.log (item.title);
                            // NOTE: BRACKET START IN THE SAME LINE AS RETURN IN 
                            //       THE FOLLOWING LINE
            return {
                value: item.title };
            }));
                	
			     },   
            });
            
            	
        }  

    	
      });

    $( "#search2" ).autocomplete({
    	width: 500,
        max: 20,
        delay: 100,
        minLength: 1,
        autoFocus: false,
        cacheLength: 1,
        scroll: true,
        highlight: false,
    	source: function (request, response) {
    		var type = document.getElementById('LIST2').value;
            $.ajax({
                url: "/AutoLib/QueryBuilder/QueryBuildServlet",
                type: "GET",
                async : false,
                dataType: "json",
                data : {
                	text2 : request.term ,
                	flag  : type
				 },
                success: function (data) {
					
					//response(data);
					response ($.map(data, function (item)
            {
            console.log (item.title);
                            // NOTE: BRACKET START IN THE SAME LINE AS RETURN IN 
                            //       THE FOLLOWING LINE
            return {
                value: item.title };
            }));
                	
			     },   
            });
            
            	
        }  

    	
      });

    $( "#search3" ).autocomplete({
    	width: 500,
        max: 20,
        delay: 100,
        minLength: 1,
        autoFocus: false,
        cacheLength: 1,
        scroll: true,
        highlight: false,
    	source: function (request, response) {
    		var type = document.getElementById('LIST3').value;
            $.ajax({
                url: "/AutoLib/QueryBuilder/QueryBuildServlet",
                type: "GET",
                async : false,
                dataType: "json",
                data : {
                	text3 : request.term ,
                	flag  : type
				 },
                success: function (data) {
					
					//response(data);
					response ($.map(data, function (item)
            {
            console.log (item.title);
                            // NOTE: BRACKET START IN THE SAME LINE AS RETURN IN 
                            //       THE FOLLOWING LINE
            return {
                 value: item.title };
            }));
                	
			     },   
            });
           	
        }  
	
      });

	
		function search() {
			location.href = "index.asp"
		}

		function Simple() {
			location.href = "/AutoLib/Simple/index.jsp"
		}

		function home() {
			location.href = "/AutoLib/Home.jsp";
		}
		function newarrival() {
			location.href = "newarrivalhome.asp"
		}

		function account() {
			location.href = "/portal/admin.html";
		}

		function Logout() {
			location.href = "/AutoLib/index.html";
		}

		function moveSelectedOptions(from, to, dest) {

			if (!hasOptions(from)) {
				return;
			}
			for ( var i = 0; i < from.options.length; i++) {
				var o = from.options[i];
				if (o.selected) {
					if (dest == "destField") {
						if (hasOptions(to)) {
							d = to.options[0];
							if (typeof (d) != "undefined" && d != null) {
								from.options[from.options.length] = new Option(
										d.text, d.value, false, false);
								to.options[0] = null;
							}
						}
					}
					if (!hasOptions(to)) {
						var index = 0;
					} else {
						var index = to.options.length;
					}
					to.options[index] = new Option(o.text, o.value, false,
							false);
					if (dest == "destField") {
						to.options[index].selected = true;
					}
				}
			}
			// Delete them from original
			for ( var i = (from.options.length - 1); i >= 0; i--) {
				var o = from.options[i];
				if (o.selected) {
					from.options[i] = null;
				}
			}
		}

		function hasOptions(obj) {
			if (obj != null && obj.options != null) {
				return true;
			}
			return false;
		}
	</script>
</BODY>

</HTML>




