<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page errorPage="/Error/ErrorPage.jsp" session="true" buffer="12kb" %>
<%@ include file="/Common/College.jsp"%>

<%@ include file="/jsp/common/taglibs.jsp"%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>AutoLib Software Systems</title>
<script language="JavaScript">
navHover = function() {
	var lis = document.getElementById("navmenu").getElementsByTagName("LI");
	for (var i=0; i<lis.length; i++) {
		lis[i].onmouseover=function() {
			this.className+=" iehover";
		}
		lis[i].onmouseout=function() {
			this.className=this.className.replace(new RegExp(" iehover\\b"), "");
		}
	}
}
if (window.attachEvent) window.attachEvent("onload", navHover);

</script>

<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/common.css" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />


<script language="javascript">

	function TimeTick()
	{
		time = time+1000;
		if(rTimer) 
		{
			clearTimeout(rTimer);
		}
		rTimer = setTimeout('TimeTick()', 1000);
	}
	function updateClock()
	{
		var date = new Date();
		//date.setTime(time);
		var min = date.getMinutes();
		var hrs = date.getHours();
		var sec = date.getSeconds();
		if(min <= 9) 
		{
			min = "0" + min;
		}
		if(hrs <= 9) 
		{
			hrs = "0" + hrs;
		}
		if(sec <= 9) 
		{
			sec = "0" + sec;
		}
		document.getElementById('Timer').innerHTML=date.getDate() + " " + monthMap[date.getMonth()] + " " + date.getFullYear() + ", "+ hrs+" : "+ min + " : "+sec;
		if(uTimer)
		{
			clearTimeout(uTimer);
		}
		uTimer = setTimeout('updateClock()', 1000);
	}
	
	var time = 0;
	var rTimer,uTimer;
	var monthMap = new Object();
	
	function displayClock(curTime) 
	{
		time = curTime;
		monthMap["0"] = "Jan";
		monthMap["1"] = "Feb";
		monthMap["2"] = "Mar";
		monthMap["3"] = "Apr";
		monthMap["4"] = "May";
		monthMap["5"] = "Jun";
		monthMap["6"] = "Jul";
		monthMap["7"] = "Aug";
		monthMap["8"] = "Sep";
		monthMap["9"] = "Oct";
		monthMap["10"] = "Nov";
		monthMap["11"] = "Dec";
		TimeTick();
		updateClock();
	}
</script>
</head>

<body>
<%
String right="";
right=(String)session.getAttribute("UserRights");
String UID="";
UID=(String)session.getAttribute("UserID");

if((right.equalsIgnoreCase(""))||(right.equalsIgnoreCase("null"))){
	response.sendRedirect("sessiontimeout.jsp");
	
}


if (right.equalsIgnoreCase("1")){

%>
<%@ include file="/Tree/header.jsp"%>
<ul id="navmenu">

 <li>
       <a href="<%= request.getContextPath() %>/Home/HomeServlet">Home</a>
        </li>
        
	<li>
	<a href="#">Master</a>
	<ul>
			<li><a href="<%= request.getContextPath() %>/Author/AuthorServlet?flag=new">Author</a></li>
			<li><a href="<%= request.getContextPath() %>/Department/DepartmentServlet?flag=new">Department</a></li>			
			<li><a href="<%= request.getContextPath() %>/Subject/SubjectServlet?flag=new">Subject</a></li>
			<li><a href="<%= request.getContextPath() %>/PubSup/PubSupServlet?flag=new">Publisher/Supplier</a></li>
			<li><a href="<%= request.getContextPath() %>/Course/CourseServlet?flag=new">Course</a></li>   
			<li><a href="<%= request.getContextPath() %>/Desig/DesignationServlet?flag=new">Designation</a></li>
			<li><a href="<%= request.getContextPath() %>/Currency/CurrencyServlet?flag=new">Currency</a></li>	
			<li><a href="<%= request.getContextPath() %>/Keywords/KeywordsServlet?flag=new">Keywords</a></li>	
			<li><a href="<%= request.getContextPath() %>/Binding/BindingServlet?flag=new">Binding</a></li>						
			<li><a href="<%= request.getContextPath() %>/City/CityServlet?flag=new">City</a></li>			
	</ul>
	</li>	
	
	<li>
	<a href="#">Cataloguing</a>
		<ul>
			
			<li><a href="<%= request.getContextPath() %>/Book/BookServlet?flag=loadBook&doc_type=BOOK">Resource Entry</a></li>             
			<%-- <li><a href="<%= request.getContextPath() %>/Member/MemberServlet?flagCheck=new">Member</a></li> --%>
			<li><a href="<%= request.getContextPath() %>/Member/">Member</a></li>
			  <li>
			  <a href="#">PhotoUpload&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;>></a>
			  <ul>
			 	<li><a href="<%= request.getContextPath() %>/Photo/">Photo</a></li>
				<li><a href="<%= request.getContextPath() %>/BulkPhoto/">BulkPhoto</a></li>
			  </ul>
			  </li>
			<li><a href="<%= request.getContextPath() %>/EResource/EResourceServlet?flag=new">E-Resource</a></li>
			<li><a href="<%= request.getContextPath() %>/Newsclliping/NewscllipingServlet?flag=new">NewsClipping</a></li>
			<li><a href="<%= request.getContextPath() %>/MResource/">Missing-Resource</a></li>						
			<li><a href="<%= request.getContextPath() %>/ContentUpload/">Contents</a></li>
			<li><a href="#">Question Bank &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;>></a>
			<ul>
			<li><a href="<%= request.getContextPath() %>/QBSubject/QBSubjectServlet?flag=new">QBSubjectMas</a></li>
			<li><a href="<%= request.getContextPath() %>/QuestionBank/QuestionBankServlet?flag=new">Question Bank</a></li>
			</ul>
			</li>		
			<li><a href="<%= request.getContextPath() %>/EBook/EBookServlet?flag=new">EBook</a></li>					
		</ul>
	</li>
	
	<li>
	<a href="#">Circulation</a>
		<ul>
			<li><a href="<%= request.getContextPath() %>/Counter/">Counter</a></li>
			<li><a href="<%= request.getContextPath() %>/Bulk_Counter/">Bulk Counter</a></li>
			<li><a href="<%= request.getContextPath() %>/BulkReturnCounter/">BulkReturnCounter</a></li>
            <li><a href="<%= request.getContextPath() %>/Payment/">User Payment</a></li>
            <li>
			  <a href="#">Transfer&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;>></a>
			  <ul>
			 	<li><a href="<%= request.getContextPath() %>/Transfer_Books/TransferAction?flag=new">Single Transfer</a></li>
				<li><a href="<%= request.getContextPath() %>/bulkTransferBooks/bulkTransferAction?flag=new">Bulk Transfer</a></li>
			  </ul>
			  </li>
            <li><a href="<%= request.getContextPath() %>/Binding_Books/BindingAction?flag=load">Binding Books</a></li>   
            
            <li><a href="<%= request.getContextPath() %>/Miscellaneous/MiscellaneousServlet?flag=new">Misc.Charges</a></li>                                             
       </ul>   
    </li>
      
  <li>
  <a href="#">Serial Control</a>
    <ul>      
          <li><a href="<%= request.getContextPath() %>/Journal/JournalServlet?flag=new">Journal</a></li>
         <li><a href="<%= request.getContextPath() %>/journalSubscription/JournalSubscriptionServlet?flag=load">Journal Subscriptions</a></li>
          <li><a href="<%= request.getContextPath() %>/Journal_Issues/">Journal Issues</a></li>
          <li><a href="<%= request.getContextPath() %>/Journal_Artical/">Journal Article</a></li>
     
    <li><a href="#">Journal&nbsp;Order&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;>></a>
    <ul>          
          <li><a href="<%= request.getContextPath() %>/JNL_Enquiry/">Journal Enquiry</a></li>      
          <li><a href="<%= request.getContextPath() %>/JNL_Order/">Journal Order</a></li>            
          <li><a href="<%= request.getContextPath() %>/JNL_Supplier_Invoice/">Journal SupInvoice</a></li>             
          <li><a href="<%= request.getContextPath() %>/JNL_Invoice/InvoiceJNLServlet?flag=new">Invoice Processing</a></li>                  
          <li><a href="<%= request.getContextPath() %>/JNL_Payment/PaymentJNLServlet?flag=new">Journal Payment</a></li>                  
    </ul>
    </li>
              
    </ul>    
  </li>
     
  
 <%-- <li><a href="#">Acquisition</a>
    <ul>
<!--      <li><a href="<%= request.getContextPath() %>/OrdInvProcessing/OrderInvServlet?flag=new">Order Invoice Process</a></li>-->
          <li><a href="<%= request.getContextPath() %>/Indent_Mas/IndentMasServlet?flag=new">Indent Master</a></li>
          <li><a href="<%= request.getContextPath() %>/Indent_Approval/">Indent Approval</a></li>
          <li><a href="<%= request.getContextPath() %>/Indent_Order/">Indent Order</a></li>            
          <li><a href="<%= request.getContextPath() %>/Indent_Invoice/">Indent Invoice</a></li>             
          <li><a href="<%= request.getContextPath() %>/Indent_Payment/PaymentIndentServlet?flag=new">Indent Payment</a></li>                                         
    </ul>    
  </li> --%>
  
 <li>
 <a href="#">Admin</a>
    <ul>
     <li><a href="<%= request.getContextPath() %>/Dashboard/DashboardServlet?flag=load">Dashboard</a></li>
          <li><a href="<%= request.getContextPath() %>/Login/">Login</a></li>
          <li><a href="<%= request.getContextPath() %>/Fine/FineServlet?flag=new">Fine</a></li>
          <li><a href="<%= request.getContextPath() %>/Group/GroupServlet?flag=new">Group</a></li>
          
          <li><a href="<%= request.getContextPath() %>/Budget/BudgetServlet?flag=loadSAmount">Budget</a></li>
          <li><a href="<%= request.getContextPath() %>/Stock/StockServlet?flag=LOAD">Stock</a></li>   	
          <li><a href="<%= request.getContextPath() %>/Holiday/">Holiday</a></li>
          
          <li><a href="<%= request.getContextPath() %>/WeekEndHoliday/WeekEndHolidayServlet?flag=search">Week&nbsp;End&nbsp;Holidays</a></li>        
          <li><a href="<%= request.getContextPath() %>/Email_Reminder/">Email Reminder</a></li>
          
         
        <li>
        <a href="#">Import&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;>></a>
         <ul>
         <li><a href="<%= request.getContextPath() %>/Book_Import/">Book Import</a></li>   
          <li><a href="<%= request.getContextPath() %>/Member_Import/">Member Import</a></li>           
<%--            <li><a href="<%= request.getContextPath() %>/EResourceImport/">E-Resource Import</a></li>   --%>
             <li><a href="<%= request.getContextPath() %>/EBookImport/">E-Book Import</a></li>  
             <li><a href="<%= request.getContextPath() %>/QB_Import/">QB Import</a></li>       
             <li><a href="<%= request.getContextPath() %>/BackVolume_Import/">BackVolume Import</a></li>  
             <li><a href="<%= request.getContextPath() %>/BulkChangesImport/">Data Change Import</a></li> 
              <li><a href="<%= request.getContextPath() %>/GateRegisterImport/">Gate Register Import</a></li>             
         
         </ul>
        </li>
        
           <li>
         <a href="#">Updation&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;>></a>
          <ul>
          
            <li><a href="<%= request.getContextPath() %>/BulkUpdate">Book Updation</a></li>
          <li><a href="<%= request.getContextPath() %>/BulkUpdateMember/">Member Updation</a></li>
          <li><a href="<%= request.getContextPath() %>/Issue_Mas_Update/">Due Date Updation</a></li>
          
          
          </ul>
         </li>
          
         
           
          <li><a href="<%= request.getContextPath() %>/TransMas/TransMasServlet?flag=new">Trans Master</a></li> 
          <li><a href="<%= request.getContextPath() %>/MemberTransfer/MemberTransServlet?flag=new">Member Transfer</a></li> 
           
          
          <li><a href="<%= request.getContextPath() %>/RefDueDays/RefDueDaysServlet?flag=load">Ref.Due Days</a></li> 
<%--           <li><a href="<%= request.getContextPath() %>/SugEmail/sugmailServlet?flag=load">Sug.Email ID</a></li>                                       --%>
   		  <li><a href="<%= request.getContextPath() %>/msgMas/MsgMasServlet?flag=new">Msg Master</a></li>   
   		 <li><a href="<%= request.getContextPath() %>/Backup/spinner.jsp">Backup</a></li>
   
    </ul>    
  </li>
  
  <li>
  <a href="#">Search</a>
    <ul>
          <li><a href="<%= request.getContextPath() %>/Simples/SimpleServlet?flag=load">Simple Search</a></li>
          <li><a href="<%= request.getContextPath() %>/Advanced/">Advanced Search</a></li>
          <li><a href="<%= request.getContextPath() %>/Browse/">Quick Search</a></li>
          <li><a href="<%= request.getContextPath() %>/QueryBuilder/">Query Builder</a></li>
          <li><a href="<%= request.getContextPath() %>/Journal_ArticleSearch/">Journal Article</a></li>                    
          <li><a href="<%= request.getContextPath() %>/EResourceSearch/">E-Resource</a></li>
          <li><a href="<%= request.getContextPath() %>/NewsClipSearch/">NewsClipping</a></li>
          <li><a href="<%= request.getContextPath() %>/Account/">User Account</a></li> 
          <li><a href="<%= request.getContextPath() %>/JournalBrowse/">Journal Search</a></li>
          <li><a href="<%= request.getContextPath() %>/QBSearch/QBSearchServlet?flag=load">Question Bank</a></li>   
          <li><a href="<%= request.getContextPath() %>/EBookSearch/EBookSearchServlet?flag=load">EBook Search</a></li>        
    </ul>
  </li>     
  
  <li><a href="#">Gate</a>
    <ul>    
          <li><a href="<%= request.getContextPath() %>/Account/AccountServlet?flag=register">Gate Register</a></li>         
    </ul>    
  </li>  
  
  <li>
  <a href="#">Reports</a>
    <ul>
          <li><a href="<%= request.getContextPath() %>/LibraryCollection/">Library Collection</a></li>
          <li><a href="<%= request.getContextPath() %>/AccessionRegister/">Accession Register</a></li>
          <li><a href="<%= request.getContextPath() %>/BibliographyReport/">Bibliography Report</a></li>          
          <li><a href="<%= request.getContextPath() %>/CounterReport/">Counter Report</a></li>
          <li><a href="<%= request.getContextPath() %>/FineCollectionReport/">FineCollectionReport</a></li>          
          <li><a href="<%= request.getContextPath() %>/Statistics/">Statistics</a></li>
          <li><a href="<%= request.getContextPath() %>/BindingReport/">Binding Report</a></li>   
          <li><a href="<%= request.getContextPath() %>/DBReport/">Database Report</a></li>        
<%--           <li><a href="<%= request.getContextPath() %>/MemberReport/">Member Report</a></li> --%>
          <li><a href="<%= request.getContextPath() %>/MemberReport/MemberReportServlet?flag=load">Member Report</a></li>
          <li><a href="<%= request.getContextPath() %>/PaymentInfo/PaymentInfoServlet?flag=load">Payment Report</a></li>
<%--           <li><a href="<%= request.getContextPath() %>/PaymentInfo/PaymentInfoServlet">Payment Report</a></li> --%>
   
   <li><a href="#">Journal&nbsp;Reports&nbsp;&nbsp;&nbsp;&nbsp;>></a>  
   <ul>
          <li><a href="<%= request.getContextPath() %>/JournalList/">Journal List</a></li>
          <li><a href="<%= request.getContextPath() %>/JnlIssueReport/">Journal Issues</a></li>
          <li><a href="<%= request.getContextPath() %>/JNL_Enquiry_Report/">Journal Enquiry</a></li>
          <li><a href="<%= request.getContextPath() %>/JNL_Order_Report/">Journal Order</a></li>
          <li><a href="<%= request.getContextPath() %>/JNL_Invoice_Report/">Journal Invoice</a></li>          
          <li><a href="<%= request.getContextPath() %>/JNL_Payment_Report/">Journal Payment</a></li>   
   
   </ul>
   </li> 
   
   <%-- <li><a href="#">Indent&nbsp;Reports&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;>></a>
    <ul>
          <li><a href="<%= request.getContextPath() %>/Indent_Report/">Indent Details</a></li>    
          <li><a href="<%= request.getContextPath() %>/Indent_Order_Report/">Indent Order</a></li>          
          <li><a href="<%= request.getContextPath() %>/Indent_Payment_Report/">Indent Payment</a></li>                              
    </ul>
    </li>  --%>    
          <li><a href="<%= request.getContextPath() %>/NoDues/">No Dues</a></li>
          <li><a href="<%= request.getContextPath() %>/QBReport/QBReportServlet?flag=load">QB Report</a></li>
          <li><a href="<%= request.getContextPath() %>/FrequentlyUsedResource/">Resource</a></li>
          <li><a href="<%= request.getContextPath() %>/FrequentlyUsedMember/FreqUsedMember?flag=load&r1=">FreqMember</a></li>
          <li><a href="<%= request.getContextPath() %>/BudgetReport/BudgetReportServlet?flag=load">Budget Report</a></li>
          <li><a href="<%= request.getContextPath() %>/Gate_Register/">Gate Register Report</a></li>
          <li><a href="<%= request.getContextPath() %>/Library_Login/">Library Login</a></li>
          <li><a href="<%= request.getContextPath() %>/SpineLabel/">SpineLabel</a></li>
          <li><a href="<%= request.getContextPath() %>/Transfer_Report/Transfer_ReportServlet?flag=load">Transfer Report</a></li>
          <li><a href="<%= request.getContextPath() %>/Unique_Report/">Unique Title Report</a></li>
          <li><a href="<%= request.getContextPath() %>/MissingResourceReport/">MissingResourceReport</a></li>
          <li><a href="<%= request.getContextPath() %>/SuggestionReport/">Suggestion&nbsp;Report</a></li>
    </ul>
    </li>  
    <li>  
   <a href="#">Help</a>
    <ul>
     <li><a href="http://smallcart.in:9009/" target="_blank" >Ticket Raise</a></li>
        <%--  <li><a href="<%= request.getContextPath() %>/Ticket_Raise/" >Ticket Raise</a></li> --%>
             <li><a href="<%= request.getContextPath() %>/Notes/" >Notes</a></li> 
          <li><a href="<%= request.getContextPath() %>/webcontent/birt/pdf_files/manual_guide.pdf" target="_blank" </a>Manual Guide</li>     
           <li><a href="<%= request.getContextPath() %>/Backup/backup_download.jsp" >Backup Download</a></li>
           <li><a href="<%= request.getContextPath() %>/About_Us/index.jsp">About AutoLib</a></li>
             </ul>
  </li> 
    
   <li><a href="/AutoLib/Logout.jsp">Logout</a></li>
   
</ul>

<%
}
%>

<%

if (right.equalsIgnoreCase("2")){
	
	


%>
<%@ include file="/Tree/header.jsp"%>
<ul id="navmenu">

 <li>
       <a href="<%= request.getContextPath() %>/Home/HomeServlet">Home</a>
        </li>
        
	<li>
	<a href="#">Master</a>
	<ul>
			<li><a href="<%= request.getContextPath() %>/Author/AuthorServlet?flag=new">Author</a></li>
			<li><a href="<%= request.getContextPath() %>/Department/DepartmentServlet?flag=new">Department</a></li>			
			<li><a href="<%= request.getContextPath() %>/Subject/SubjectServlet?flag=new">Subject</a></li>
			<li><a href="<%= request.getContextPath() %>/PubSup/PubSupServlet?flag=new">Publisher/Supplier</a></li>
			<li><a href="<%= request.getContextPath() %>/Course/CourseServlet?flag=new">Course</a></li>   
			<li><a href="<%= request.getContextPath() %>/Desig/DesignationServlet?flag=new">Designation</a></li>
			<li><a href="<%= request.getContextPath() %>/Currency/CurrencyServlet?flag=new">Currency</a></li>	
			<li><a href="<%= request.getContextPath() %>/Keywords/KeywordsServlet?flag=new">Keywords</a></li>	
			<li><a href="<%= request.getContextPath() %>/Binding/BindingServlet?flag=new">Binding</a></li>						
			<li><a href="<%= request.getContextPath() %>/City/CityServlet?flag=new">City</a></li>			
	</ul>
	</li>	
	
	<li>
	<a href="#">Cataloguing</a>
		<ul>
			<li><a href="<%= request.getContextPath() %>/Book/BookServlet?flag=loadBook&doc_type=BOOK">Resource Entry</a></li>             
			<li><a href="<%= request.getContextPath() %>/Member/">Member</a></li>
			 <li>
			  <a href="#">PhotoUpload&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;>></a>
			  <ul>
			 	<li><a href="<%= request.getContextPath() %>/Photo/">Photo</a></li>
				<li><a href="<%= request.getContextPath() %>/BulkPhoto/">BulkPhoto</a></li>
			  </ul>
			  </li>
			<li><a href="<%= request.getContextPath() %>/EResource/EResourceServlet?flag=new">E-Resource</a></li>
			<li><a href="<%= request.getContextPath() %>/Newsclliping/NewscllipingServlet?flag=new">NewsClipping</a></li>
			<li><a href="<%= request.getContextPath() %>/MResource/">Missing-Resource</a></li>						
			<li><a href="<%= request.getContextPath() %>/ContentUpload/">Contents</a></li>	
			<li><a href="<%= request.getContextPath() %>/EBook/EBookServlet?flag=new">EBook</a></li>						
		</ul>
	</li>
	
	<li>
	<a href="#">Circulation</a>
		<ul>
			<li><a href="<%= request.getContextPath() %>/Counter/">Counter</a></li>
			<li><a href="<%= request.getContextPath() %>/Bulk_Counter/">Bulk Counter</a></li>
            <li><a href="<%= request.getContextPath() %>/Payment/">User Payment</a></li>
            <li><a href="<%= request.getContextPath() %>/Transfer_Books/TransferAction?flag=new">Transfer Books</a></li>
            <li><a href="<%= request.getContextPath() %>/Binding_Books/BindingAction?flag=load">Binding Books</a></li>   
            <li><a href="<%= request.getContextPath() %>/Miscellaneous/MiscellaneousServlet?flag=new">Misc.Charges</a></li>                                             
       </ul>   
    </li>
      
  <li>
  <a href="#">Serial Control</a>
    <ul>      
          <li><a href="<%= request.getContextPath() %>/Journal/JournalServlet?flag=new">Journal</a></li>
          <li><a href="<%= request.getContextPath() %>/Journal_Issues/">Journal Issues</a></li>
          <li><a href="<%= request.getContextPath() %>/Journal_Artical/">Journal Article</a></li>
     
    <li><a href="#">Journal&nbsp;Order&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;>></a>
    <ul>          
          <li><a href="<%= request.getContextPath() %>/JNL_Enquiry/">Journal Enquiry</a></li>      
          <li><a href="<%= request.getContextPath() %>/JNL_Order/">Journal Order</a></li>            
          <li><a href="<%= request.getContextPath() %>/JNL_Supplier_Invoice/">Journal SupInvoice</a></li>             
          <li><a href="<%= request.getContextPath() %>/JNL_Invoice/InvoiceJNLServlet?flag=new">Invoice Processing</a></li>                  
          <li><a href="<%= request.getContextPath() %>/JNL_Payment/PaymentJNLServlet?flag=new">Journal Payment</a></li>
          <li><a href="#">Question Bank</a>
			<ul>
			<li><a href="<%= request.getContextPath() %>/QBSubject/QBSubjectServlet?flag=new">QBSubjectMas</a></li>
			<li><a href="<%= request.getContextPath() %>/QuestionBank/QuestionBankServlet?flag=new">Question Bank</a></li>
			</ul>
			</li>                  
    </ul>
    </li>
              
    </ul>    
  </li>
     
  
 <%-- <li><a href="#">Acquisition</a>
    <ul>
<!--      <li><a href="<%= request.getContextPath() %>/OrdInvProcessing/OrderInvServlet?flag=new">Order Invoice Process</a></li>-->
          <li><a href="<%= request.getContextPath() %>/Indent_Mas/IndentMasServlet?flag=new">Indent Master</a></li>
          <li><a href="<%= request.getContextPath() %>/Indent_Approval/">Indent Approval</a></li>
          <li><a href="<%= request.getContextPath() %>/Indent_Order/">Indent Order</a></li>            
          <li><a href="<%= request.getContextPath() %>/Indent_Invoice/">Indent Invoice</a></li>             
          <li><a href="<%= request.getContextPath() %>/Indent_Payment/PaymentIndentServlet?flag=new">Indent Payment</a></li>                                         
    </ul>    
  </li> --%>
  

  
  <li>
  <a href="#">Search</a>
    <ul>
          <li><a href="<%= request.getContextPath() %>/Simples/SimpleServlet?flag=load">Simple Search</a></li>
          <li><a href="<%= request.getContextPath() %>/Advanced/">Advanced Search</a></li>
          <li><a href="<%= request.getContextPath() %>/Browse/">Quick Search</a></li>
          <li><a href="<%= request.getContextPath() %>/QueryBuilder/">Query Builder</a></li>
          <li><a href="<%= request.getContextPath() %>/Journal_ArticleSearch/">Journal Article</a></li>                    
          <li><a href="<%= request.getContextPath() %>/EResourceSearch/">E-Resource</a></li>
          <li><a href="<%= request.getContextPath() %>/NewsClipSearch/">NewsClipping</a></li>
          <li><a href="<%= request.getContextPath() %>/Account/">User Account</a></li> 
          <li><a href="<%= request.getContextPath() %>/JournalBrowse/">Journal Search</a></li>
          <li><a href="<%= request.getContextPath() %>/QBSearch/QBSearchServlet?flag=load">QBSearch</a></li>   
          <li><a href="<%= request.getContextPath() %>/EBookSearch/EBookSearchServlet?flag=load">EBook</a></li>        
    </ul>
  </li>     
  
  <li><a href="#">Gate</a>
    <ul>    
          <li><a href="<%= request.getContextPath() %>/Account/AccountServlet?flag=register">Gate Register</a></li>         
    </ul>    
  </li>  
  
  <li>
  <a href="#">Reports</a>
    <ul>
          <li><a href="<%= request.getContextPath() %>/LibraryCollection/">Library Collection</a></li>
          <li><a href="<%= request.getContextPath() %>/AccessionRegister/">Accession Register</a></li>
          <li><a href="<%= request.getContextPath() %>/BibliographyReport/">Bibliography Report</a></li>          
          <li><a href="<%= request.getContextPath() %>/CounterReport/">Counter Report</a></li>          
          <li><a href="<%= request.getContextPath() %>/Statistics/">Statistics</a></li>  
          <li><a href="<%= request.getContextPath() %>/DBReport/">Database Report</a></li>        
          <li><a href="<%= request.getContextPath() %>/MemberReport/">Member Report</a></li>
          <li><a href="<%= request.getContextPath() %>/PaymentInfo/PaymentInfoServlet?flag=load">Payment Report</a></li>
<%--           <li><a href="<%= request.getContextPath() %>/PaymentInfo/PaymentInfoServlet">Payment Report</a></li> --%>
   
   <li><a href="#">Journal&nbsp;Reports&nbsp;&nbsp;&nbsp;&nbsp;>></a>  
   <ul>
          <li><a href="<%= request.getContextPath() %>/JournalList/">Journal List</a></li>
          <li><a href="<%= request.getContextPath() %>/JnlIssueReport/">Journal Issues</a></li>
          <li><a href="<%= request.getContextPath() %>/JNL_Enquiry_Report/">Journal Enquiry</a></li>
          <li><a href="<%= request.getContextPath() %>/JNL_Order_Report/">Journal Order</a></li>
          <li><a href="<%= request.getContextPath() %>/JNL_Invoice_Report/">Journal Invoice</a></li>          
          <li><a href="<%= request.getContextPath() %>/JNL_Payment_Report/">Journal Payment</a></li>   
   
   </ul>
   </li> 
   
  <%--  <li><a href="#">Indent&nbsp;Reports&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;>></a>
    <ul>
          <li><a href="<%= request.getContextPath() %>/Indent_Report/">Indent Details</a></li>    
          <li><a href="<%= request.getContextPath() %>/Indent_Order_Report/">Indent Order</a></li>          
          <li><a href="<%= request.getContextPath() %>/Indent_Payment_Report/">Indent Payment</a></li>                              
    </ul>
    </li>   --%>   
          <li><a href="<%= request.getContextPath() %>/NoDues/">No Dues</a></li>
          <li><a href="<%= request.getContextPath() %>/FrequentlyUsedResource/">Resource</a></li>
          <li><a href="<%= request.getContextPath() %>/FrequentlyUsedMember/FreqUsedMember?flag=load&r1=">FreqMember</a></li>
          <li><a href="<%= request.getContextPath() %>/BudgetReport/">Budget Report</a></li>
          <li><a href="<%= request.getContextPath() %>/Gate_Register/">Gate Register Report</a></li>
          <li><a href="<%= request.getContextPath() %>/SpineLabel/">SpineLabel</a></li>
          <li><a href="<%= request.getContextPath() %>/Transfer_Report/Transfer_ReportServlet?flag=load">Transfer Report</a></li>
          <li><a href="<%= request.getContextPath() %>/Unique_Report/">Unique Title Report</a></li>
          <li><a href="<%= request.getContextPath() %>/MissingResourceReport/">MissingResourceReport</a></li>
          <li><a href="<%= request.getContextPath() %>/SuggestionReport/">Suggestion&nbsp;Report</a></li>
                    
    </ul>
    </li>    
    
   <li><a href="/AutoLib/Logout.jsp">Logout</a></li>
   
</ul>

<%

}
%>


<%

if (right.equalsIgnoreCase("3")){  //DATA ENTRY
	


%>
<%@ include file="/Tree/header.jsp"%>
<ul id="navmenu">


	<li>
	<a href="#">Master</a>
	<ul>
			<li><a href="<%= request.getContextPath() %>/Author/AuthorServlet?flag=new">Author</a></li>
			<li><a href="<%= request.getContextPath() %>/Department/DepartmentServlet?flag=new">Department</a></li>			
			<li><a href="<%= request.getContextPath() %>/Subject/SubjectServlet?flag=new">Subject</a></li>
			<li><a href="<%= request.getContextPath() %>/PubSup/PubSupServlet?flag=new">Publisher/Supplier</a></li>
			<li><a href="<%= request.getContextPath() %>/Course/CourseServlet?flag=new">Course</a></li>   
			<li><a href="<%= request.getContextPath() %>/Desig/DesignationServlet?flag=new">Designation</a></li>
			<li><a href="<%= request.getContextPath() %>/Currency/CurrencyServlet?flag=new">Currency</a></li>	
			<li><a href="<%= request.getContextPath() %>/Keywords/KeywordsServlet?flag=new">Keywords</a></li>	
			<li><a href="<%= request.getContextPath() %>/Binding/BindingServlet?flag=new">Binding</a></li>						
			<li><a href="<%= request.getContextPath() %>/City/CityServlet?flag=new">City</a></li>			
	</ul>
	</li>	
	
	<li>
	<a href="#">Cataloguing</a>
		<ul>
			<li><a href="<%= request.getContextPath() %>/Book/BookServlet?flag=loadBook&doc_type=BOOK">Resource Entry</a></li>             
			<li><a href="<%= request.getContextPath() %>/Member/">Member</a></li>
			 <li>
			  <a href="#">PhotoUpload&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;>></a>
			  <ul>
			 	<li><a href="<%= request.getContextPath() %>/Photo/">Photo</a></li>
				<li><a href="<%= request.getContextPath() %>/BulkPhoto/">BulkPhoto</a></li>
			  </ul>
			  </li>
			<li><a href="<%= request.getContextPath() %>/EResource/EResourceServlet?flag=new">E-Resource</a></li>
			<li><a href="<%= request.getContextPath() %>/Newsclliping/NewscllipingServlet?flag=new">NewsClipping</a></li>
			<li><a href="<%= request.getContextPath() %>/ContentUpload/">Contents</a></li>
			<li><a href="#">Question Bank &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;>></a>
			<ul>
			<li><a href="<%= request.getContextPath() %>/QBSubject/QBSubjectServlet?flag=new">QBSubjectMas</a></li>
			<li><a href="<%= request.getContextPath() %>/QuestionBank/QuestionBankServlet?flag=new">Question Bank</a></li>
			</ul>
			</li>	
			<li><a href="<%= request.getContextPath() %>/EBook/EBookServlet?flag=new">EBook</a></li>						
		</ul>
	</li>


  

  

  
  <li>
  <a href="#">Search</a>
    <ul>
          <li><a href="<%= request.getContextPath() %>/Simples/SimpleServlet?flag=load">Simple Search</a></li>
          <li><a href="<%= request.getContextPath() %>/Advanced/">Advanced Search</a></li>
          <li><a href="<%= request.getContextPath() %>/Browse/">Quick Search</a></li>
          <li><a href="<%= request.getContextPath() %>/QueryBuilder/">Query Builder</a></li>
          <li><a href="<%= request.getContextPath() %>/Journal_ArticleSearch/">Journal Article</a></li>                    
          <li><a href="<%= request.getContextPath() %>/EResourceSearch/">E-Resource</a></li>
          <li><a href="<%= request.getContextPath() %>/NewsClipSearch/">NewsClipping</a></li>
          <li><a href="<%= request.getContextPath() %>/Account/">User Account</a></li> 
          <li><a href="<%= request.getContextPath() %>/JournalBrowse/">Journal Search</a></li>
          <li><a href="<%= request.getContextPath() %>/QBSearch/QBSearchServlet?flag=load">QBSearch</a></li>  
          <li><a href="<%= request.getContextPath() %>/EBookSearch/EBookSearchSerlvet?flag=load">EBook</a></li>         
    </ul>
  </li>     
  

  
  <li>
  <a href="#">Reports</a>
    <ul>
          <li><a href="<%= request.getContextPath() %>/LibraryCollection/">Library Collection</a></li>
          <li><a href="<%= request.getContextPath() %>/AccessionRegister/">Accession Register</a></li>
          <li><a href="<%= request.getContextPath() %>/BibliographyReport/">Bibliography Report</a></li>          
          <li><a href="<%= request.getContextPath() %>/MemberReport/">Member Report</a></li>
          
                    
    </ul>
    </li>    
    
   <li><a href="/AutoLib/Logout.jsp">Logout</a></li>
   
</ul>

<%


	
	
}
%>
<%

if (right.equalsIgnoreCase("4")){  //JOURNAL
	
	

	
	


%>
<%@ include file="/Tree/header.jsp"%>
<ul id="navmenu">



	

      
  <li>
  <a href="#">Serial Control</a>
    <ul>      
          <li><a href="<%= request.getContextPath() %>/Journal/JournalServlet?flag=new">Journal</a></li>
          <li><a href="<%= request.getContextPath() %>/Journal_Issues/">Journal Issues</a></li>
          <li><a href="<%= request.getContextPath() %>/Journal_Artical/">Journal Article</a></li>
     
    <li><a href="#">Journal&nbsp;Order&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;>></a>
    <ul>          
          <li><a href="<%= request.getContextPath() %>/JNL_Enquiry/">Journal Enquiry</a></li>      
          <li><a href="<%= request.getContextPath() %>/JNL_Order/">Journal Order</a></li>            
          <li><a href="<%= request.getContextPath() %>/JNL_Supplier_Invoice/">Journal SupInvoice</a></li>             
          <li><a href="<%= request.getContextPath() %>/JNL_Invoice/InvoiceJNLServlet?flag=new">Invoice Processing</a></li>                  
          <li><a href="<%= request.getContextPath() %>/JNL_Payment/PaymentJNLServlet?flag=new">Journal Payment</a></li>                  
    </ul>
    </li>
              
    </ul>    
  </li>
     
  <li>
  <a href="#">Search</a>
    <ul>
          <li><a href="<%= request.getContextPath() %>/Simples/SimpleServlet?flag=load">Simple Search</a></li>
          <li><a href="<%= request.getContextPath() %>/Advanced/">Advanced Search</a></li>
          <li><a href="<%= request.getContextPath() %>/Browse/">Quick Search</a></li>
          <li><a href="<%= request.getContextPath() %>/QueryBuilder/">Query Builder</a></li>
          <li><a href="<%= request.getContextPath() %>/Journal_ArticleSearch/">Journal Article</a></li>                    
          <li><a href="<%= request.getContextPath() %>/EResourceSearch/">E-Resource</a></li>
          <li><a href="<%= request.getContextPath() %>/NewsClipSearch/">NewsClipping</a></li>
          <li><a href="<%= request.getContextPath() %>/Account/">User Account</a></li> 
          <li><a href="<%= request.getContextPath() %>/JournalBrowse/">Journal Search</a></li>
          <li><a href="<%= request.getContextPath() %>/QBSearch/QBSearchServlet?flag=load">QBSearch</a></li> 
          <li><a href="<%= request.getContextPath() %>/EBookSearch/EBookSearchServlet?flag=load">EBook</a></li>          
    </ul>
  </li>     
  

  <li>
  <a href="#">Reports</a>
    <ul>
     <li><a href="#">Journal&nbsp;Reports&nbsp;&nbsp;&nbsp;&nbsp;>></a>  
   <ul>
          <li><a href="<%= request.getContextPath() %>/JournalList/">Journal List</a></li>
          <li><a href="<%= request.getContextPath() %>/JnlIssueReport/">Journal Issues</a></li>
          <li><a href="<%= request.getContextPath() %>/JNL_Enquiry_Report/">Journal Enquiry</a></li>
          <li><a href="<%= request.getContextPath() %>/JNL_Order_Report/">Journal Order</a></li>
          <li><a href="<%= request.getContextPath() %>/JNL_Invoice_Report/">Journal Invoice</a></li>          
          <li><a href="<%= request.getContextPath() %>/JNL_Payment_Report/">Journal Payment</a></li>   
   
   </ul>
   </li> 
    </ul>
    </li>    
    
   <li><a href="/AutoLib/Logout.jsp">Logout</a></li>
   
</ul>

<%


}
%>

<%

if (right.equalsIgnoreCase("5")){  //ACQUSITION

%>
<%@ include file="/Tree/header.jsp"%>
<ul id="navmenu">
 
 <%-- <li><a href="#">Acquisition</a>
    <ul>
<!--          <li><a href="<%= request.getContextPath() %>/OrdInvProcessing/OrderInvServlet?flag=new">Order Invoice Process</a></li>-->
          <li><a href="<%= request.getContextPath() %>/Indent_Mas/IndentMasServlet?flag=new">Indent Master</a></li>
          <li><a href="<%= request.getContextPath() %>/Indent_Approval/">Indent Approval</a></li>
          <li><a href="<%= request.getContextPath() %>/Indent_Order/">Indent Order</a></li>            
          <li><a href="<%= request.getContextPath() %>/Indent_Invoice/">Indent Invoice</a></li>             
          <li><a href="<%= request.getContextPath() %>/Indent_Payment/PaymentIndentServlet?flag=new">Indent Payment</a></li>                                         
    </ul>    
  </li> --%>

  <li>
  <a href="#">Search</a>
    <ul>
          <li><a href="<%= request.getContextPath() %>/Simples/SimpleServlet?flag=load">Simple Search</a></li>
          <li><a href="<%= request.getContextPath() %>/Advanced/">Advanced Search</a></li>
          <li><a href="<%= request.getContextPath() %>/Browse/">Quick Search</a></li>
          <li><a href="<%= request.getContextPath() %>/QueryBuilder/">Query Builder</a></li>
          <li><a href="<%= request.getContextPath() %>/Journal_ArticleSearch/">Journal Article</a></li>                    
          <li><a href="<%= request.getContextPath() %>/EResourceSearch/">E-Resource</a></li>
          <li><a href="<%= request.getContextPath() %>/NewsClipSearch/">NewsClipping</a></li>
          <li><a href="<%= request.getContextPath() %>/Account/">User Account</a></li> 
          <li><a href="<%= request.getContextPath() %>/JournalBrowse/">Journal Search</a></li>
          <li><a href="<%= request.getContextPath() %>/QBSearch/QBSearchServlet?flag=load">QBSearch</a></li>   
          <li><a href="<%= request.getContextPath() %>/EBookSearch/EBookSearchServlet?flag=load">EBook</a></li>           
                  
    </ul>
  </li>     
  
 
  
  <%-- <li>
  <a href="#">Reports</a>
    <ul>

   
   <li><a href="#">Indent&nbsp;Reports&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;>></a>
    <ul>
          <li><a href="<%= request.getContextPath() %>/Indent_Report/">Indent Details</a></li>    
          <li><a href="<%= request.getContextPath() %>/Indent_Order_Report/">Indent Order</a></li>          
          <li><a href="<%= request.getContextPath() %>/Indent_Payment_Report/">Indent Payment</a></li>                              
    </ul>
    </li>     
          
                    
    </ul>
    </li> --%>    
    
   <li><a href="/AutoLib/Logout.jsp">Logout</a></li>
   
</ul>
<%
}



if (right.equalsIgnoreCase("6")){

%>
<%@ include file="/Tree/header.jsp"%>
<ul id="navmenu">

 <li>
       <a href="<%= request.getContextPath() %>/Home/HomeServlet">Home</a>
        </li>
        

	

	<li>
	<a href="#">Circulation</a>
		<ul>
			<li><a href="<%= request.getContextPath() %>/Counter/">Counter</a></li>
			<li><a href="<%= request.getContextPath() %>/Bulk_Counter/">Bulk Counter</a></li>
			<li><a href="<%= request.getContextPath() %>/BulkReturnCounter/">BulkReturnCounter</a></li>
            <li><a href="<%= request.getContextPath() %>/Payment/">User Payment</a></li>
            <li><a href="<%= request.getContextPath() %>/Miscellaneous/MiscellaneousServlet?flag=new">Misc.Charges</a></li>                                             
       </ul>   
    </li>
      
 
     
  

 
  
  <li>
  <a href="#">Search</a>
    <ul>
          <li><a href="<%= request.getContextPath() %>/Simples/SimpleServlet?flag=load">Simple Search</a></li>
          <li><a href="<%= request.getContextPath() %>/Advanced/">Advanced Search</a></li>
          <li><a href="<%= request.getContextPath() %>/Browse/">Quick Search</a></li>
          <li><a href="<%= request.getContextPath() %>/QueryBuilder/">Query Builder</a></li>
          <li><a href="<%= request.getContextPath() %>/Journal_ArticleSearch/">Journal Article</a></li>                    
          <li><a href="<%= request.getContextPath() %>/EResourceSearch/">E-Resource</a></li>
          <li><a href="<%= request.getContextPath() %>/NewsClipSearch/">NewsClipping</a></li>
          <li><a href="<%= request.getContextPath() %>/Account/">User Account</a></li> 
          <li><a href="<%= request.getContextPath() %>/JournalBrowse/">Journal Search</a></li>
          <li><a href="<%= request.getContextPath() %>/QBSearch/QBSearchServlet?flag=load">QBSearch</a></li>
          <li><a href="<%= request.getContextPath() %>/EBookSearch/EBookSearchServlet?flag=load">EBook</a></li>          
                     
    </ul>
  </li>     
  
 
  
  <li>
  <a href="#">Reports</a>
    <ul>
          <li><a href="<%= request.getContextPath() %>/LibraryCollection/">Library Collection</a></li>
          <li><a href="<%= request.getContextPath() %>/AccessionRegister/">Accession Register</a></li>
          <li><a href="<%= request.getContextPath() %>/CounterReport/">Counter Report</a></li>          
<%--           <li><a href="<%= request.getContextPath() %>/PaymentInfo/PaymentInfoServlet?flag=load">Payment Report</a></li> --%>
          <li><a href="<%= request.getContextPath() %>/PaymentInfo/PaymentInfoServlet?flag=load">Payment Report</a></li>
          <li><a href="<%= request.getContextPath() %>/NoDues/">No Dues</a></li>
          <li><a href="<%= request.getContextPath() %>/FrequentlyUsedResource/">Resource</a></li>
          
                    
    </ul>
    </li>    
    
   <li><a href="/AutoLib/Logout.jsp">Logout</a></li>
   
</ul>

<%
}
%>


<%

if ((right.equalsIgnoreCase("7")) && (!UID.equalsIgnoreCase("WebOpac"))){

%>

<%@ include file="/Tree/header.jsp"%>
<ul id="navmenu">
 <li>
       <a href="<%= request.getContextPath() %>/Home/HomeServlet">Home</a>
 </li>
  <li>
       <a href="<%= request.getContextPath() %>/Suggestion/SuggestionServlet?flag=new">Suggestion</a>
 </li>
  <li>
  <a href="#">Search</a>
    <ul>
          <li><a href="<%= request.getContextPath() %>/Simples/SimpleServlet?flag=load">Simple Search</a></li>
          <li><a href="<%= request.getContextPath() %>/Advanced/">Advanced Search</a></li>
          <li><a href="<%= request.getContextPath() %>/Browse/">Quick Search</a></li>
          <li><a href="<%= request.getContextPath() %>/QueryBuilder/">Query Builder</a></li>
          <li><a href="<%= request.getContextPath() %>/Journal_ArticleSearch/">Journal Article</a></li>                    
          <li><a href="<%= request.getContextPath() %>/EResourceSearch/">E-Resource</a></li>
          <li><a href="<%= request.getContextPath() %>/NewsClipSearch/">NewsClipping</a></li>
          <li><a href="<%= request.getContextPath() %>/JournalBrowse/">Journal Search</a></li>
          <li><a href="<%= request.getContextPath() %>/QBSearch/QBSearchServlet?flag=load">QBSearch</a></li> 
          <li><a href="<%= request.getContextPath() %>/EBookSearch/EBookSearchServlet?flag=load">EBook</a></li>          
                    
    </ul>
  </li>
  
  <li>
       <a href="<%= request.getContextPath() %>/newarrivals/NewArraivalsServlet?flag=loadMonthYear">New Arrivals</a>
 </li>
  
 <li>
   <a href="/AutoLib/Logout.jsp">Logout</a></li>
</ul>


<%
}
%>

<%

if ((right.equalsIgnoreCase("7")) && (UID.equalsIgnoreCase("WebOpac"))){

%>

<%@ include file="/Tree/header.jsp"%>
<ul id="navmenu">
 <li>
       <a href="<%= request.getContextPath() %>/Home/opacPage.jsp">Home</a>
 </li>
<!--   <li> -->
<%--        <a href="<%= request.getContextPath() %>/Suggestion/SuggestionDisplayServlet?flag=new">Suggestion</a> --%>
<!--  </li> -->
 
 
          <li><a href="<%= request.getContextPath() %>/Simples/SimpleServlet?flag=load">Simple Search</a></li>
          <li><a href="<%= request.getContextPath() %>/Advanced/">Advanced Search</a></li>
          <li><a href="<%= request.getContextPath() %>/Browse/">Quick Search</a></li>
          <li><a href="<%= request.getContextPath() %>/QueryBuilder/">Query Builder</a></li>
          <li><a href="<%= request.getContextPath() %>/Journal_ArticleSearch/">Journal Article</a></li>                    
          <li><a href="<%= request.getContextPath() %>/EResourceSearch/">E-Resource</a></li>
          <li><a href="<%= request.getContextPath() %>/NewsClipSearch/">NewsClipping</a></li>
          <li><a href="<%= request.getContextPath() %>/QBSearch/QBSearchServlet?flag=load">QBSearch</a></li>           
          <li><a href="<%= request.getContextPath() %>/EBookSearch/EBookSearchServlet?flag=load">EBook</a></li>          
          
  

</ul>


<%
}
%>


<%

if (right.equalsIgnoreCase("8")){

%>

<%@ include file="/Tree/header.jsp"%>
<ul id="navmenu">
 
   <li>
  <a href="#">Gate</a>
    <ul>    
          <li><a href="<%= request.getContextPath() %>/Account/AccountServlet?flag=register">Gate Register</a></li>         
    </ul>    
  </li>   
  
  <li>
  <a href="#">Search</a>
    <ul>
          <li><a href="<%= request.getContextPath() %>/Simples/SimpleServlet?flag=load">Simple Search</a></li>
          <li><a href="<%= request.getContextPath() %>/Advanced/">Advanced Search</a></li>
          <li><a href="<%= request.getContextPath() %>/Browse/">Quick Search</a></li>
          <li><a href="<%= request.getContextPath() %>/QueryBuilder/">Query Builder</a></li>
          <li><a href="<%= request.getContextPath() %>/Journal_ArticleSearch/">Journal Article</a></li>                    
          <li><a href="<%= request.getContextPath() %>/EResourceSearch/">E-Resource</a></li>
          <li><a href="<%= request.getContextPath() %>/NewsClipSearch/">NewsClipping</a></li>
          <li><a href="<%= request.getContextPath() %>/JournalBrowse/">Journal Search</a></li>
          <li><a href="<%= request.getContextPath() %>/QBSearch/QBSearchServlet?flag=load">QBSearch</a></li>  
          <li><a href="<%= request.getContextPath() %>/EBookSearch/EBookSearchServlet?flag=load">EBook</a></li>          
                   
    </ul>
  </li> 
  
  
  <li>
   <a href="/AutoLib/Logout.jsp">Logout</a></li>
</ul>


<%
}
%>

<%

if (right.equalsIgnoreCase(null)){

%>

<%@ include file="/Tree/header.jsp"%>



<%
}
%>

<br>
<table align="right">
<tr>
<td>&nbsp;&nbsp;</td>
								<td width="87%" style="border-top: 1.0pt lightgreen dotted;border-bottom: 1.0pt lightgreen dotted; border-color: #000000;">
									<c:if test="${not empty visitDateTime}">
										<div class="vist1">
											 Last Visited Time is :&nbsp;&nbsp;
										</div>
										<div class="vist1" align="center">
											<c:out value="${visitDateTime}"/>&nbsp;
										</div>
									</c:if>
								</td>
								<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
							</tr>
							</table>

</body>
</html>
