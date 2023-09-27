package Common.businessutil.reports;

public interface ReportQueryUtill {
	// Company address for Report (Report Parameters)

	public static final String COMPANY_NAME = "Panimalar College Of Nursing";
	public static final String COMPANY_ADD = "VARADHARAJAPURAM, POONAMALLEE, CHENNAI 600123";
	// SpineLabel

	public static final String SpineLabelQuery = "SELECT call_no,LEFT(author_name,3) AS author_name,access_no,year_pub FROM accession_reg where";
	public static final String SpineLabel_Title = "Spine-Label";

	// Accession Register Report

	public static final String Accession_Title = "Accession-Report";

	public static final String Missing_Number_Title = "Missing Number";

	public static final String AccessionQuery_Acc_no = "SELECT access_no,call_no,title,author_name,dept_name,edition,publisher,year_pub,supplier,invoice_no,isbn,bprice,DATE_FORMAT(received_date,'%d/%m/%Y') as received_date,pages,invoice_date,volno,part_no FROM accession_reg where 2>1 ";
	public static final String Miss_Accessno = "select access_no from miss_no";
	// Library Collection Report

	public static final String Library_Collection_Title = "Library-Collection";

	// Transfered Report

	public static final String Transfer_Report_Query = "Select * from transfer_view where 2>1";
	public static final String Transfer_Reports_Title = "Transfer-Report";
	public static final String Re_Transfer_Reports_Title = "Re-Transfer-Report";

	// Question Bank Report

	public static final String QB_Report_Title = "QB_Report";
	public static final String QB_Report_Query = "Select qb_code,sub_code,sub_name,dept_name,concat(course_name,'-',course_major) As course_name,year,semester,month from question_bank_fullview where 2>1 ";

	// Counter Reports

	public static final String Counter_Reports_IssueTitle = "Issue-Report";
	public static final String Counter_Reports_ReturnTitle = "Return-Report";
	public static final String Counter_Reports_RenewTitle = "Renew-Report";
	public static final String Counter_Reports_ReserveTitle = "Reserve-Report";
	public static final String Counter_Reports_ReserveReminderTitle = "Reservation-Reminder";
	public static final String Counter_Reports_DueReminderTitle = "Due-Reminder";
	public static final String Counter_Reports_FineTitle = "Library-Overdue";
	public static final String Counter_Reports_CurrentIssueTitle = "Book-Current-Issue-Report";

	public static final String Query_CurrentIssue_Report = "SELECT access_no,title,member_code,issue_date,due_date,doc_type,staff_code,member_name FROM issuedbooks where 2>1 and issue_type='issue'";
	public static final String Query_Issue_Report = "SELECT access_no,title,member_code,member_name,DATE_FORMAT(issue_date,'%d/%m/%Y') AS issue_date,DATE_FORMAT(due_date,'%d/%m/%Y') AS due_date,DATE_FORMAT(return_date,'%d/%m/%Y') AS return_date,doc_type,staff_code FROM issue_transaction where 2>1";
	public static final String Query_Issue_Breakup_Report = "SELECT DATE_FORMAT(issue_date,'%d/%m/%Y') AS issue_date,COUNT(*) as total_books FROM issue_transaction WHERE 2>1 ";
	public static final String Query_Issue_statistics = "SELECT DISTINCT(issue_date) AS issue_date ,dept_name,COUNT(access_no) AS access_no FROM issue_transaction where 2>1";
	public static final String Query_curIssue_statistics = "SELECT DISTINCT(issue_date) AS issue_date ,dept_name,COUNT(access_no) AS access_no FROM issuedbooks where 2>1";
	public static final String Query_Return_statistics = "SELECT DISTINCT(return_date) AS return_date ,dept_name,COUNT(access_no) AS access_no FROM jissue_history where 2>1 ";
	public static final String Query_Issue_Chart = "SELECT YEAR(issue_date) AS YEAR,LEFT(MONTHNAME(issue_date),3) AS MONTH,COUNT(access_no) AS COUNT FROM issue_transaction WHERE 2>1";
	public static final String Query_Return_Chart = "SELECT YEAR(return_date) AS YEAR,LEFT(MONTHNAME(return_date),3) AS MONTH,COUNT(access_no) AS COUNT FROM jissue_history WHERE 2>1";
	public static final String Query_Return_Report = "SELECT access_no,title,member_code,member_name,DATE_FORMAT(issue_date,'%d/%m/%Y') AS issue_date,DATE_FORMAT(due_date,'%d/%m/%Y') AS due_date,DATE_FORMAT(return_date,'%d/%m/%Y') AS return_date,doc_type,staff_code FROM jissue_history where 2>1 and issue_type='Return'";
	public static final String Query_Renew_Report = "SELECT access_no,title,member_code,member_name,DATE_FORMAT(issue_date,'%d/%m/%Y') AS issue_date,DATE_FORMAT(due_date,'%d/%m/%Y') AS due_date,doc_type,staff_code FROM renewbooks where issue_type='RENEW'";
	public static final String Query_Reserve_Report = "SELECT access_no,title,member_code,res_date,doc_type FROM member_reserve_view where 2>1";
	public static final String Query_ReserveReminder_Report = "SELECT access_no,title,member_code,res_date,doc_type FROM reserve_reminder_view where 2>1";
	public static final String Query_DueReminder_Report = "SELECT access_no,title,member_code,member_name,DATE_FORMAT(issue_date,'%d/%m/%Y') AS issue_date,DATE_FORMAT(due_date,'%d/%m/%Y') AS due_date,doc_type FROM due_reminder WHERE 2>1";
	public static final String Query_FineBreakup_Report = "SELECT trans_no,access_no,DATE_FORMAT(issue_date,'%d/%m/%Y') as issue_date,DATE_FORMAT(due_date,'%d/%m/%Y') as due_date,DATE_FORMAT(trans_date,'%d/%m/%Y') as trans_date,trans_amount,member_code,member_name FROM trans_member_view where trans_head='OVERDUE'";
	public static final String Query_FineListing_Report = "SELECT member_code,member_name,trans_no,DATE_FORMAT(trans_date,'%d/%m/%Y') as trans_date,access_no,DATE_FORMAT(due_date,'%d/%m/%Y') as due_date,trans_amount,remarks FROM trans_member_view  where trans_head='OVERDUE'";
	public static final String Query_FineCumulative_Report = "SELECT SUM(trans_amount) AS trans_amount,member_code,member_name FROM trans_member_view  where trans_head='OVERDUE'";
	public static final String Breakup_Issue_Report = "SELECT issue_date,COUNT(*) AS issuecount FROM issue_transaction where 2>1";
	public static final String Counter_Reports_Breakup_IssueTitle = "Book-Issue-Report-Breakup";
	public static final String Breakup_Return_Report = "SELECT return_date,COUNT(*) AS returncount FROM  jissue_history where 2>1";
	public static final String Counter_Reports_Breakup_ReturnTitle = "Book-Return-Breakup-Report";
	public static final String Breakup_Renewal_Report = "SELECT issue_date AS renewal_date,COUNT(*) AS renewalcount FROM issuedbooks WHERE issue_type='RENEW'";
	public static final String Counter_Reports_Breakup_RenewTitle = "Book-Renew-Breakup-Report";
	public static final String Breakup_Reserve_Report = "SELECT res_date AS reserve_date,COUNT(*) AS reservecount FROM member_reserve_view where 2>1";
	public static final String Counter_Reports_Breakup_ReserveTitle = "Reserve-Breakup-Report";
	public static final String Breakup_DueReminder_Report = "SELECT due_date AS due_date,COUNT(*) AS remindercount FROM due_reminder where 2>1";
	public static final String Counter_Reports_Breakup_DueReminderTitle = "Due-Reminder-Breakup";

	// Member Reports
	// public static final String Counter_MemberReports_Title="Member-Details";
	public static final String Counter_MemberReports_Title = "MEMBER-DETAILS";
	public static final String Counter_MemberReports_Inactive = "Inactive-Member-Details";
	public static final String Counter_MemberReports_Active_Statistics_title = "Active-Statistics-Details";
	public static final String Counter_MemberReports_InActive_Statistics_title = "Inactive-Statistics-Details";

	public static final String Query_Member_Report = "SELECT * from member_report_view where 2>1";

	// No Dues Certificate
	public static final String NoDues_Title = "NO DUES CERTIFICATE";
	public static final String NoDuesReport_Title = "NO-DUES";

	// Binder Reports

	public static final String Binding_report_Title = "Binding-Report";
	public static final String Binding_report = "SELECT access_no,title,author_name,binder_name,date_format(sending_date,'%d/%m/%Y') as sending_date,accepted_price,year_pub,doc_type,availability FROM binding_view where 2>1";

	// Gate Regiter Report
	public static final String GateReg_Title = "Gate-Register";
	public static final String GateRegFull_Title = "Gate Register Full Date-Wise";
	public static final String GateRegQuery_Date = "SELECT * FROM return_log_view";
	public static final String Gate_detail = "SELECT member_code,member_name,dept_name,return_date,designation,cyear,TIME_FORMAT(in_time,'%H:%i') AS in_time,out_time,min_used FROM return_log_view";
	public static final String GateRegQuery_Statistics = "Select dept_name,substring(return_date,1,10) as return_date,count(return_date) as totalCount from return_log_view where 2>1 ";
	// Library_Login Report
	public static final String Library_Login_Title = "Library-Login-Details";
	public static final String Library_Login_Statitics_Title = "Library-Login-Statistics";
	public static final String Library_Login_Date = "SELECT member_code,member_name,dept_name,group_name,last_visit FROM library_login_view";
	public static final String Library_Login_Statistics = "SELECT last_visit,COUNT(*) AS totalCount,group_name FROM library_login_view WHERE 2>1 ";

	// Statistics Report

	public static final String Statistics_Title = "Statistics-Report";

	// Frequent Accessed Resource Report

	public static final String Frequently_Resource_Title = "Library Frequently Used Resource";
	public static final String Frequently_UnUsedResource_Title = "Library UnUsed Resource";
	public static final String Frequently_Pdf_Title = "Resource-Frequent";

	// Frequent Member Resource Report

	public static final String Frequently_Member = "Library Frequently Unused Member";
	public static final String Frequently_Pdf_Member = "Member-Frequent";

	public static final String Frequently_Member_Title = "Library Frequently Used Member";
	public static final String Frequently_Pdf_Member_Title = "Member-Frequent";

	public static final String Best_User = "Best_User_Member";
	public static final String Best_Used_Member = "Best_User_Award";

	public static final String Frequent_Member_Title = "Frequently-Borrowers";
	public static final String Frequently_USER_Title = "Frequently-Library-Users";
	public static final String Member_unused = "Not-Borrowed-Members";

	// Budget Report

	public static final String Budget_Title = "Budget-Details";

	public static final String DetailedBudget_Title = "Detailed Budget Report";

	public static final String Budget_Date_wise = "SELECT * FROM budget_full_view where 2>1";

	public static final String Budget_book_Detail = "SELECT access_no,author_name,title,dept_name,bud_head,bud_from,bud_to,accepted_price FROM budget_book_view where 2>1";

	public static final String DistinctBudgetReportQuery = "SELECT DISTINCT bud_code,bud_head,dept_code,dept_name FROM budget_full_view Where bud_code<>1 ";
	// Suggestion Report
	public static final String Suggestion_Title = "Suggestion-Report";
	public static final String Suffestioninfo = "SELECT * FROM suggestion_view where 2>1 ";
	// Review Report

	public static final String ReviewTitle = "Review - Report";
	public static final String ReviewInfo = "SELECT * FROM view_review where 2>1 ";

	// Payment Report

	public static final String Paymentinfo_Title = "Payment-Details";

	public static final String FineCollected_Title = "FineCollected-Details";

	public static final String Paymentinfo_Chart = "Payment-Chart";

	public static final String Paymentinfo_Unpaid = "Payment-Unpaid";

	public static final String Paymentinfo_query = "SELECT * FROM payment_clear_view where 2>1 ";

	public static final String Payment_unpaid_query = "SELECT member_code,member_name,dept_name,CONCAT(course_name,'-',course_major) AS course,total_fine,paid_amount,bal_amount FROM fine_allview where 2>1 ";

	public static final String Paymentinfochart_query = "SELECT YEAR(SUBSTRING(payment_date,1,10)) AS YEAR,MONTHNAME(SUBSTRING(payment_date,1,10)) AS payment,SUM(amount) AS Amount FROM payment_clear_view WHERE 2>1 ";

	public static final String FineCollectedReport = "SELECT staff_code,SUM(Amount) AS Amount,payment_date FROM payment_clearance WHERE 2>1";

	// Journal List Report

	public static final String JNL_List_Title = "Journal-Details";

	public static final String JNL_Query = "SELECT * FROM f_journal_master where 2>1";

	// Journal Issues Report

	public static final String JNL_Issues_Title = "Journal-Issues";

	public static final String JNL_Issues_Query = "select * from f_journal_issues where 1<2";

	// Bibliography Report

	public static final String Biblio_Title = "Bibliography-Report";
	public static final String BiblioCallno_Title = "Bibliography-Call_No";
	public static final String BiblioAccession_Title = "Bibliography-Accession";
	public static final String BiblioRevd_Title = "Bibliography-PurchaseDate";
	public static final String BiblioBudged_Title = "Bibliography-Budget";
	public static final String BiblioAuthor_Title = "Bibliography-AuthorName";
	public static final String BiblioDept_Title = "Bibliography-Department";
	public static final String BiblioPub_Title = "Bibliography-publisher";
	public static final String BiblioSup_Title = "Bibliography-Supplier";
	public static final String Bibliosubject_Title = "Bibliography-Subject";

	public static final String Biblio_Query = "SELECT access_no,author_name,title,edition,call_no,publisher,year_pub,dept_name,sub_name,supplier,budget,location,isbn,bprice FROM bibliography where 1<2";

	// Unique title Report

	public static final String Unique_Title = "Unique-title";
	// public static final String
	// UniqueTitle_Query="select distinct title,author,count(*) as bcopy from title_temp group by title,author order by title";

	public static final String UniqueTitle_Query = "select distinct title,author_name,edition,count(*) as bcopy from accession_reg where 2>1 "; // shek

	// Journal Enquiry Reports
	public static final String JNL_Enquiry_Title = "Enquiry-Details";
	public static final String JNL_Enquiry_Report = "SELECT enq_no,enq_date,jnl_name,sp_name,subs_from,subs_to,vol_no,no_of_issues,address1,city,pincode FROM jnl_enquiry_fullview WHERE 2>1";

	// Journal Order Reports
	public static final String JNL_Order_Title = "Order-Details";
	public static final String JNL_Order_Report = "SELECT jnl_order_no,jnl_order_date,enq_no,enq_date,jnl_name,sp_name,subs_from,subs_to,vol_no,no_of_issues,accepted_price,address1,city,pincode FROM jnl_order_fullview where 2>1";

	// Journal Invoice Reports
	public static final String JNL_Invoice_Title = "Invoice-Details";
	public static final String JNL_Invoice_Report = "SELECT invoice_no,order_ref_no,sp_name,invoice_date,invoice_amount,payment_flag FROM jnl_invoice_fullview where 2>1";

	// Journal Payment Reports
	public static final String JNL_Payment_Title = "Payment-Details";
	public static final String JNL_Payment_Report = "SELECT payment_ref_no,payment_sent_date,cheque_no,cheque_date,tot_amount,trans_details,invoice_no,invoice_amount,invoice_date,sp_name,address1,city,pincode from jnl_payment_fullview where 2>1";

	// Indent Order Reports
	public static final String Indent_Order_Title = "Order-Details";
	public static final String Indent_Order_Report = "SELECT order_no,order_date,author_name,title,publisher,copies,edition,isbn,yearpub,supplier,address1,city,pincode FROM indent_order_fullview where 2>1";

	// Indent Payment Reports
	public static final String Indent_Payment_Title = "Payment-Details";
	public static final String Indent_Payment_Report = "SELECT payment_ref_no,payment_sent_date,cheque_no,cheque_date,tot_amount,trans_details,invoice_no,inv_tot_amount,invoice_date,supplier,address1,city,pincode FROM indent_payment_fullview where 2>1";

	// Indent Details Reports
	public static final String Indent_Detail_Title = "Indent-Details";
	public static final String Indent_Detail_Report = "SELECT indent_no,indent_date,member_name,author_name,title,pub_name,yearpub,edition,isbn,no_of_copy FROM indent_details_mas where 2>1";

	// DataBase (Custom) Reports
	public static final String Database_Reports_RPTTitle = "DataBase-Report";

	// Missing Resource Report
	public static final String MissResourceReport_Title = "MissingResource-Report";

	public static final String MissResourceReportQuery_Acc_no = "SELECT access_no,doc_type,status,mdate from missing_mas WHERE";

	// ------------------excel report--------------------

	public static final String TransFineCumulativeReport = "Unpaid Amount";

	public static final String fine_trans_cumulative = "SELECT IFNULL(t.member_code, 'TOTAL') AS member_code,m.member_name,"
			+ "SUM(CASE WHEN t.trans_head='OVERDUE' THEN trans_amount ELSE 0 END)OverDue,"
			+ "SUM(CASE WHEN t.trans_head='Photocopy' THEN trans_amount ELSE 0 END)Photocopy,"
			+ "SUM(CASE WHEN t.trans_head='Printout' THEN trans_amount ELSE 0 END)Printout,"
			+ "SUM(CASE WHEN t.trans_head='Color Print' THEN trans_amount ELSE 0 END)ColorPrint,"
			+ "SUM(CASE WHEN t.trans_head='Strip Binding' THEN trans_amount ELSE 0 END)StripBinding,"
			+ "SUM(CASE WHEN t.trans_head='Stick Binding' THEN trans_amount ELSE 0 END)StickBinding,"
			+ "SUM(CASE WHEN t.trans_head='Spiral Binding' THEN trans_amount ELSE 0 END)SprialBinding,"
			+ "SUM(CASE WHEN t.trans_head='Recovery' THEN trans_amount ELSE 0 END)Recovery,"
			+ "SUM(CASE WHEN t.trans_head='Loss of Resource' THEN trans_amount ELSE 0 END)LossofResource,"
			+ "SUM(CASE WHEN t.trans_head='Others' THEN trans_amount ELSE 0 END)Others,"
			+ "SUM(trans_amount) AS Total FROM trans_mas t inner join member_mas m on t.member_code=m.member_code ";

	public static final String Query_BranchFineListing_Report = "SELECT member_code,member_name,trans_no,trans_date,access_no,due_date,trans_amount,dept_name FROM trans_book_view  where trans_head='OVERDUE'";

}
