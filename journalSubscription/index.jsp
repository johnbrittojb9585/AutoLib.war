<%
	String URole = session.getAttribute("UserRights").toString().trim();
	if (URole.equalsIgnoreCase("3") || URole.equalsIgnoreCase("5")
			|| URole.equalsIgnoreCase("6")
			|| URole.equalsIgnoreCase("7")) {
		response.sendRedirect("sessiontimeout.jsp");
	}
%>
<%@ include file="/Tree/demoFrameset.jsp"%>
<%@ page language="java" session="true" buffer="12kb" import="java.sql.*"
		import="Lib.Auto.Currency.CurrencyBean"
		import="java.text.Format"
		import="java.util.*" import="Common.Security"%>
		<%@ page import="java.text.SimpleDateFormat"%>
		
<%
ArrayList sc=new ArrayList();
sc=(ArrayList)request.getAttribute("currency");
%>


<%
	java.util.Date d =new java.util.Date();
	Calendar date = Calendar.getInstance();
	
	SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	Format f = new SimpleDateFormat("dd-MM-yyyy");
	date.setTime(d);
	date.add(Calendar.DATE,365);
	  
	
	    String dateString = sdf.format(d);
	    String dateString1 = f.format(date.getTime());
	
	
%>


<link rel="stylesheet" type="text/css"	href="<%=request.getContextPath()%>/css/button_css.css"/>
<jsp:useBean id="bean" scope="request" class="Lib.Auto.JournalSubscription.JournalSubscriptionbean"/>

<html>
<head>
<title>AutoLib</title>
<script language="javascript" src="/AutoLib/popcalendar.js"></script>

<meta http-equiv="pragma" content="no-cache" />
<link rel="stylesheet" type="text/css" href="/AutoLib/style.css">
</head>
<body>

	<form name="journalSubscription" method="get" action=./JournalSubscriptionServlet>
<br>
		<h2>Journal&nbsp;Subscription</h2>
		
			<table align="center" class="contentTable" cellpadding="2" width="750">
			<tr><td>&nbsp;</td></tr>
			<tr>
				<td>
					<table border="0" width="60%" cellspacing="0" align="center">
						
<tr>

<td Class="addedit">Subs&nbsp;No.</td><td><input name="subsNo" size="19" maxlength=20 onKeydown="Javascript: if (event.keyCode==13) searchSubsNo();"></td>
	<td><input type="button" Value="Find" name="find_name" Class="submitButton" maxlength=150 onclick='Find_Value("subsNo")'></td>
	
<td Class="addedit">Starting&nbsp;Acc.No.</td><td><input name="startingAccNo" size="19"></td>
		
		

</tr>
			
			
									
<tr>
<td Class="addedit">J&nbsp;/&nbsp;M&nbsp;/&nbsp;O</td><td colspan=4>
<input name="journalName" size="70" maxlength=10 readonly></td>

	<td><input type="button" Value="Find" name="find_name" Class="submitButton" maxlength=150 onclick='Find_Value("jnlName")'></td>
	</tr>
	<tr>
<td Class="addedit">Supplier</td><td colspan=4><input name="Supplier" size="70" readonly></td>
		<td><input type="button" Value="Find" name="find_name" Class="submitButton" maxlength=150 onclick='Find_Value("Sup")'></td>
</tr>			
</table></td></tr>

<tr><td>
<table border="0" width="80%" cellspacing="0" cellpadding="2" align="center">
<tr>
<td Class="addedit">Subs&nbsp;From</td>
  
<td><INPUT name="subsFrom" size=10 id="subsfromdt" onfocus=this.blur(); value="<%=dateString%>" onClick="ChangDate()">
				<SCRIPT language=javascript>
						if (!document.layers) {
						document.write("<input type=button onclick='popUpCalendar(this,journalSubscription.subsFrom, \"dd-mm-yyyy\",\"<%=dateString%>\")'value='...' style='font-size:10 px'>")
						}
				</SCRIPT></td>

        <td Class="addedit">Subs&nbsp;To</td>
        <td>
        <INPUT name="subsTo" size=10 id="substodt" onfocus=this.blur(); value="<%=dateString1%>">
				<SCRIPT language=javascript>
						if (!document.layers) {
						document.write("<input type=button onclick='popUpCalendar(this,journalSubscription.subsTo, \"dd-mm-yyyy\",\"<%=dateString%>\")'value='...' style='font-size:10 px'>")
						}
				</SCRIPT></td>
      	 
		 <td Class="addedit">Availability</td>
        <td>
        <select size="1" name="availability"  style="width: 110px">
            <option selected value="PENDING">PENDING</option>
            <option value="COMPLETED">COMPLETED</option>
            
          </select></td>

</tr>



<tr>

<td Class="addedit">Month</td>
        <td><input type="text" name="month"  maxlength=20  style="width: 110px"  onClick="ChangDate()" readonly></td>
        <td Class="addedit">Year</td>
        <td ><input type="text" name="year" maxlength=4 style="width: 110px" onKeyUp="return access_no_val();" maxlength=4  onClick="ChangDate()" readonly></td>
         <td Class="addedit">Volume</td>
        <td ><input type="text" name="volumeNo" style="width: 110px" onKeyUp="return access_no_val();" maxlength=4></td>

</tr>
<tr>

 <td Class="addedit">IssueNo</td>
        <td ><input type="text" name="issueNo" value="1" style="width: 110px" onKeyUp="return access_no_val();" maxlength=4 readonly></td>
 <td Class="addedit">Frequently</td>
        <td><select size="1" name="frequency" style="width: 110px" id="frequency" onchange="frequencyChange()">
        	<option selected value="MONTHLY">MONTHLY</option>
            <option value="DAILY">DAILY</option>
<!--             <option value="WEEKLY TWO">WEEKLY TWO</option> -->
            <option value="WEEKLY">WEEKLY</option>
            <option value="FORTNIGHTLY">FORTNIGHTLY</option>
            <option value="BIMONTHLY">BIMONTHLY</option>
            <option value="QUARTERLY">QUARTERLY</option>
            <option value="HALF YEARLY">HALF YEARLY</option>
            <option value="ANNUAL">ANNUAL</option>
            <option value="OTHERS">OTHERS</option>
            
          </select></td>
          
          
 <td Class="addedit">No.ofIssues</td>
        <td ><input type="text" name="NoOfIssues" value="12" style="width: 110px" onKeyUp="return access_no_val();"  maxlength=4></td>
</tr>

<tr>


<td Class="addedit">Cost</td>

<td><input type="text" name="bcost" style="width: 110px" value="0" maxlength=5 onkeyup="return BCost_val();"></td>



<td Class="addedit">Currency</td><td>
<SELECT SIZE="1" NAME="currency" onchange="find_amount()" style="width: 110px">

  
 <% Iterator it=sc.iterator();
			                            while(it.hasNext()){
                                        	CurrencyBean view=(CurrencyBean) it.next();                                        	
				                        String curr=view.getCurrency();
				                        String ind=view.getIndian_value();
				                        %>
				                     <%
				                       out.println("<option  value="+ind+">" +curr+"</option>");
                                       }
				                        %>
      		                          
		
	 </SELECT>
    
     <td Class="addedit">BPrice</td><td><input type="text" name="bprice" size="15" value="0" maxlength=6 onkeyup="accpt_amt()"></td>
    
</tr>
<tr>
<td Class="addedit">Discount<td >
   <input type="text" name="discount" size="15" value="0" maxlength=5 onkeyup="net_amt()"></td>
   
   <td Class="addedit">NetPrice</td>
   <td><input type="text"  name="acceptPrice" size="15" value="0"maxlength=9 onkeyup="chkAP_amt()"></td>
   
</tr>
<tr>

 <td Class="addedit">Budget&nbsp;Name</td><td colspan=5><input type="text" name="budgName" size="50" value="NIL" readonly>
 
<input type=button name="FindBud" Value="Find" Class="submitButton" onclick="Find_Value('budName')">
<input type="button" value="Create Issues" Class="submitButton" name="createIssues" onclick="CreateIssues()"></td>

</tr>
</table></td></tr>


</table>
	
<p align="center">
<input type="button" value="New" Class="submitButton" name="new" onclick="NewRecord()">
<input type="button" value="Save" Class="submitButton" name="save" onclick="SaveRecord()">
<input type="button" value="Delete" Class="submitButton" name="delete" onclick="DeleteRecord()"> 
<input type="button"	value="Search" Class="submitButton" name="search" onclick="SearchRecord()"> 
<input type="reset" value="Clear" Class="submitButton" name="clear">
<input type="hidden" name="flag">
<input type="hidden" name="jnlflag">
<input type="hidden" name="jnlCode">
<input type="hidden" name="supCode">
<input type="hidden" value="1" name="budgCode">
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

document.journalSubscription.startingAccNo.value="<%=bean.getStartingAccNo()%>";
document.journalSubscription.subsNo.focus();


</script>
<%
	}		if (valid.equals("load")) {
	
		%>
		<script language="JavaScript">
		
		document.journalSubscription.startingAccNo.value="<%=bean.getStartingAccNo()%>";
		document.journalSubscription.subsNo.focus();

		
		</script>
	
			<%	
			}
	
	if (valid.equals("saveSubscription")) {%>
	<script language="JavaScript">
	 alert("Record Inserted Successfully!");
	 
	 	document.journalSubscription.subsNo.value="<%=bean.getSubsNo()%>";
		document.journalSubscription.journalName.value="<%=bean.getJnlName()%>";
		document.journalSubscription.jnlCode.value="<%=bean.getJnlcode() %>";
		
		
		document.journalSubscription.supCode.value="<%=bean.getSupCode()%>";
		document.journalSubscription.Supplier.value="<%=bean.getSupplier()%>";
		
		document.journalSubscription.subsFrom.value="<%=Security.getdate(bean.getSubsFrom())%>";
		document.journalSubscription.subsTo.value="<%=Security.getdate(bean.getSubsTo())%>";
		
		document.journalSubscription.availability.value="<%=bean.getFlag() %>";
		document.journalSubscription.month.value="<%=bean.getIssueMonth()%>";
		document.journalSubscription.year.value="<%=bean.getIssueYear() %>";
		document.journalSubscription.volumeNo.value="<%=bean.getIssueVolume()%>";
		document.journalSubscription.issueNo.value="<%=bean.getIssueNo()%>";
		document.journalSubscription.frequency.value="<%=bean.getFrequency() %>";
		document.journalSubscription.NoOfIssues.value="<%=bean.getNoOfIssues()%>";
		document.journalSubscription.bcost.value="<%=bean.getjCost() %>";
		document.journalSubscription.currency.value="<%=bean.getjCurrency()%>";
		document.journalSubscription.bprice.value="<%=bean.getjPrice() %>";
		document.journalSubscription.discount.value="<%=bean.getjDiscount() %>";
		document.journalSubscription.acceptPrice.value="<%=bean.getNetPrice() %>";
		document.journalSubscription.budgName.value="<%=bean.getBudName()%>";
	 
	 document.journalSubscription.flag.value="new";
	 document.journalSubscription.submit();
	</script>
	<%}
	

	
	if (valid.equals("saveIssues")) {
	
		%>
		<script language="JavaScript">
	
		alert("Issues Created Successfully!");
		 document.journalSubscription.flag.value="new";
			document.journalSubscription.submit();
		</script>
		<%
			
	}
	
	
	if (valid.equals("AlreadysaveIssues")) {
		
		%>
		<script language="JavaScript">
	
		alert("Issues Saved Already");
		 document.journalSubscription.flag.value="new";
			document.journalSubscription.submit();
		</script>
		<%
			
	}
	if (valid.equals("failedIssuesSave")) {
		
		%>
		<script language="JavaScript">
	
		alert("Issues already generated");
		 document.journalSubscription.flag.value="new";
			document.journalSubscription.submit();
		</script>
		<%
			
	}
	
	
	if (valid.equals("NoSubscriptionsFound")) {
		
		%>
		<script language="JavaScript">
	
		alert("No Subscriptions Data Found");
		 document.journalSubscription.flag.value="new";
			document.journalSubscription.submit();
		</script>
		<%
			
	}
	
	
	
	if (valid.equals("FailureSubs")) {
		%>
		
		<script language="JavaScript">
		alert("Record not found!");
		 document.journalSubscription.flag.value="new";
			document.journalSubscription.submit();
		
		
			</script>
		<%
	}
	
	
	if (valid.equals("Updatename")) {
		
		%>
		<script language="JavaScript">
		
alert("Record Already Exists, You cannot modify!!!");
</script>
		<%
		
	}
	
	if (valid.equals("jnlIssueYes")) {
		%>
		<script language="JavaScript">
		
		alert("Journal issues are available! you can't delete.");
		 document.journalSubscription.flag.value="new";
			document.journalSubscription.submit();
		</script>
		
		<%
		
	}
	
	if (valid.equals("jnlIssueDeleted")) {
		%>
		<script language="JavaScript">
		
		alert("Journal Subscription Deleted Successfully!");
		 document.journalSubscription.flag.value="new";
			document.journalSubscription.submit();
		</script>
		
		<%
		
	}
	
	if (valid.equals("SuccessSubs")) {
		%>
			<script language="JavaScript">
	

	document.journalSubscription.subsNo.value="<%=bean.getSubsNo()%>";
	document.journalSubscription.jnlCode.value="<%=bean.getJnlcode() %>";
	document.journalSubscription.journalName.value="<%=bean.getJnlName()%>";
	document.journalSubscription.supCode.value="<%=bean.getSupCode()%>";
	document.journalSubscription.Supplier.value="<%=bean.getSupplier()%>";
	document.journalSubscription.subsFrom.value="<%=bean.getSubsFrom()%>";
	document.journalSubscription.subsTo.value="<%=bean.getSubsTo()%>";
	document.journalSubscription.availability.value="<%=bean.getFlag() %>";
	document.journalSubscription.month.value="<%=bean.getIssueMonth()%>";
	document.journalSubscription.year.value="<%=bean.getIssueYear() %>";
	document.journalSubscription.volumeNo.value="<%=bean.getIssueVolume()%>";
	document.journalSubscription.issueNo.value="<%=bean.getIssueNo()%>";
	document.journalSubscription.frequency.value="<%=bean.getFrequency() %>";
	document.journalSubscription.NoOfIssues.value="<%=bean.getNoOfIssues()%>";
	document.journalSubscription.bcost.value="<%=bean.getjCost() %>";
	document.journalSubscription.currency.value="<%=bean.getjCurrency()%>";
	document.journalSubscription.bprice.value="<%=bean.getjPrice() %>";
	document.journalSubscription.discount.value="<%=bean.getjDiscount() %>";
	document.journalSubscription.acceptPrice.value="<%=bean.getNetPrice() %>";
	document.journalSubscription.budgName.value="<%=bean.getBudName()%>";
											  
		
		</script>
	<%
	}		if (valid.equals("SaveJournal")) {
%>
			<script language="JavaScript">
			 alert("Record Inserted Successfully!");
			 document.journalSubscription.flag.value="new";
			document.journalSubscription.submit();
		     </script>
<%
	}
			if (valid.equals("FailureJournal")) {
%>
<script language="JavaScript">
	    alert("Record Not Found");
	    document.journalSubscription.flag.value="new";
	    document.journalSubscription.submit();
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
	


	
	function searchSubsNo(){//shek
		
		if(document.journalSubscription.subsNo.value==''){
			alert("Insufficent Data !");
		}else{
			document.journalSubscription.flag.value="searchSubNo";
			document.journalSubscription.submit();
			
			
		}	
	}
	
	function NewRecord() {
		
		
		document.journalSubscription.method = "get";
		document.journalSubscription.flag.value = "new";
		document.journalSubscription.submit();

	}
	
	function CreateIssues() {
		
		
		if((document.journalSubscription.subsNo.value=='') || (document.journalSubscription.month.value=='' ) || (document.journalSubscription.year.value=='' )){
			alert("Please Select Subscriptions Details");
		}
		else if(document.journalSubscription.startingAccNo.value==''){
			alert("Please Select startingAccNo");
		}
		else{
			document.journalSubscription.flag.value="createIssues";
	       	document.journalSubscription.submit();
		}
		
	}
	
	function SaveRecord() {//shek
		var start = new Date(document.getElementById("subsfromdt").value.split("-").reverse().join("-"));
		var end = new Date(document.getElementById("substodt").value.split("-").reverse().join("-"));
	
		if(document.journalSubscription.subsNo.value=='')
			{
			alert("Insufficent data");
		}else if (document.journalSubscription.journalName.value==''){
			alert("Please select journal name");
		}else if(document.journalSubscription.Supplier.value==''){
			alert("Please Select supplier name");
		}else if(start > end){
			alert("Please select valid subscription date");
		}else if(document.journalSubscription.NoOfIssues.value=='' || document.journalSubscription.NoOfIssues.value==0 ){
			alert("Please Select frequency");
		}else if(document.journalSubscription.month.value==''){
			alert("Please select year / month");
		}
		else{
			if(confirm("Are You Sure To Save?"))
            {
				document.journalSubscription.method = "get";
				document.journalSubscription.flag.value = "save";
				document.journalSubscription.submit();
             }
		}
	}
	
	function DeleteRecord() {
		document.journalSubscription.method = "get";
		if (document.journalSubscription.subsNo.value == "") {
			document.journalSubscription.subsNo.focus();
			alert("Insufficent Data");
			document.journalSubscription.flag.value = "new";
			document.journalSubscription.submit();
		}else if(document.journalSubscription.availability.value=='COMPLETED'){
			
			if(confirm("Subscription completed. Are you want to delete?")){
				document.journalSubscription.flag.value = "delete";
				document.journalSubscription.submit();
				
			}
			
		}	else {
			document.journalSubscription.flag.value = "delete";
			document.journalSubscription.submit();

		}
	}

	
	function ChangDate(){
		var m=new Date(document.getElementById("subsfromdt").value.split("-").reverse().join("-")).getMonth(); 
		   var month = new Array();
		    month[0] = "January";
		    month[1] = "February";
		    month[2] = "March";
		    month[3] = "April";
		    month[4] = "May";
		    month[5] = "June";
		    month[6] = "July";
		    month[7] = "August";
		    month[8] = "September";
		    month[9] = "October";
		    month[10] = "November";
		    month[11] = "December";
		    document.journalSubscription.month.value=month[m];
		    document.journalSubscription.year.value = new Date(document.getElementById("subsfromdt").value.split("-").reverse().join("-")).getFullYear();
	}
	
	
	function frequencyChange(){
		
		
		
		var start = new Date(document.getElementById("subsfromdt").value.split("-").reverse().join("-"));
		var end = new Date(document.getElementById("substodt").value.split("-").reverse().join("-"));
		
		
		if(start > end){
			
			alert("Please select valid subscription date");
			
		}else{
		var diff = new Date(end - start);
		var days = Math.round(diff/1000/60/60/24)+1;
		//alert(days);
		
		var freq=document.getElementById("frequency").value;
		//alert("dddd"+freq);
		if(freq=='DAILY'){
				document.journalSubscription.NoOfIssues.value=Math.round(days);
		}else if(freq=='WEEKLY TWO'){
			document.journalSubscription.NoOfIssues.value=Math.round(days/3.5);
		}else if(freq=='WEEKLY'){
			document.journalSubscription.NoOfIssues.value=Math.round(days/7);
		}else if(freq=='FORTNIGHTLY'){
			document.journalSubscription.NoOfIssues.value=Math.round(days/14);
		}else if(freq=='BIMONTHLY'){
			document.journalSubscription.NoOfIssues.value=Math.round(days/60);
		}else if(freq=='MONTHLY'){
			document.journalSubscription.NoOfIssues.value=Math.round(days/30);
		}else if(freq=='QUARTERLY'){
			document.journalSubscription.NoOfIssues.value=Math.round(days/99.25);
		}else if(freq=='HALF YEARLY'){
			document.journalSubscription.NoOfIssues.value=Math.round(days/182.5);
		}else if(freq=='ANNUAL'){
			document.journalSubscription.NoOfIssues.value=Math.round(days/365);
		}else if(freq=='OTHERS'){
			document.journalSubscription.NoOfIssues.value='';
			
		}else{
			document.journalSubscription.NoOfIssues.value='';
		}
		}
		
	}
	
	
	function SearchRecord() {
		document.journalSubscription.method = "get";
		var no = document.journalSubscription.jcode.value;
		if (no == "") {
			document.journalSubscription.jcode.focus();
			alert("Insufficent Data");
			document.journalSubscription.flag.value = "new";
			document.journalSubscription.submit();
		} else {
			document.journalSubscription.flag.value = "search";
			document.journalSubscription.submit();
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
		document.journalSubscription.jname.focus();
	}
	
	function accpt_amt(){

		if((isNaN(document.journalSubscription.bprice.value))||(document.journalSubscription.bprice.value == ' ')) {
		document.journalSubscription.bprice.select();
		document.journalSubscription.bprice.value="";
		}

		document.journalSubscription.acceptPrice.value=document.journalSubscription.bprice.value


		}
		function net_amt(){

		if((isNaN(document.journalSubscription.discount.value))||(document.journalSubscription.discount.value == ' ')) {
		document.journalSubscription.discount.select();
		document.journalSubscription.discount.value="";
		}

		document.journalSubscription.acceptPrice.value=document.journalSubscription.bprice.value-((document.journalSubscription.discount.value/100)* document.journalSubscription.bprice.value)
		}
		function BCost_val() {
			if((isNaN(document.journalSubscription.bcost.value))||(document.journalSubscription.bcost.value == ' ')) {
			document.journalSubscription.bcost.select();
			document.journalSubscription.bcost.value="";

			  }
			}

			function chkAP_amt()
			{
			if((isNaN(document.journalSubscription.acceptPrice.value))||(document.journalSubscription.acceptPrice.value == ' ')) {
			document.journalSubscription.acceptPrice.select();
			document.journalSubscription.acceptPrice.value="";

			  }
			}
			function find_amount(){

				document.journalSubscription.bprice.value=document.journalSubscription.currency.value * document.journalSubscription.bcost.value
				document.journalSubscription.acceptPrice.value=document.journalSubscription.bprice.value

				}
			
			
			
</script>


