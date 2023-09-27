package Common.businessutil.reports;

import java.util.ArrayList;
import java.util.List;

import Lib.Auto.AccessionRegister.accessbean;
import Lib.Auto.Budget.BudgetBean;
import Lib.Auto.Department.DepartmentBean;
import Lib.Auto.JnlIssueReport.JnlIssueReportBean;
import Lib.Auto.Journal.journalbean;
import Lib.Auto.JournalList.JournalListBean;
import Lib.Auto.MemberReport.MemberReportBean;
import Lib.Auto.MemberTransfer.MemberTransRefBean;
import Lib.Auto.PubSup.PubSupBean;
import Lib.Auto.Book.bookbean;
import Lib.Auto.Subject.subjectbean;
import Lib.Auto.Member.MemberBean;
import Lib.Auto.Designation.DesignationBean;
import Lib.Auto.Course.CourseBean;
import Lib.Auto.Group.GroupBean;



public interface ReportService {
	
	//Member Report
	public List getMemberSearch(MemberReportBean ReportBean);
	
	public List getDeptSearch(MemberReportBean ReportBean);
	
	public List getGroupSearch(MemberReportBean ReportBean);
	
	public List getCourseSearch(MemberReportBean ReportBean);
	
	
	
	
	public List gettodayIssueListDetails(String search);
	
	public List gettodayReturnListDetails(String search);
	
	public List gettodayRenewalListDetails(String search);
	
	public List gettodayTransAmountDetails(String search);
	
	public List gettodayPaidDetails(String search);
	
	//Journal List
	
	public List getDeptJnlSearch(JournalListBean ReportBean);
	
	public List getPubJnlSearch(JournalListBean ReportBean);
	
	public List getSubJnlSearch(JournalListBean ReportBean);
	
	//Journal Issue Report
	
	public List getJnlNameSearch(JnlIssueReportBean ReportBean);

	//	For Report Connection
	public java.sql.Connection getDBConnect();
	
	public boolean getCheckData(String query);
	
	public int getAccessNo(accessbean NewBean);
	public int getAccessNoSave(accessbean NewBean);
	public int getDeleteMisNo();

	//:::::::::::::::::::member report::::::::::::::::
	
	public List getDesigList(MemberTransRefBean newBean);
	public List getDepartmentList(MemberTransRefBean newBean);
	public List getGroupList(MemberTransRefBean newBean);
	public List getCourseList(MemberTransRefBean newBean);
	
	//budget report
	
		public List getSearchBudgetList(BudgetBean newBean);
		
    // Auto Complete   Statistics Report
		
		public ArrayList<DepartmentBean> getStatDeptAutoSearch(String term);
		public ArrayList<subjectbean> getStatSubjectAutoSearch(String term);
		public ArrayList<PubSupBean> getStatPubAutoSearch(String term);
		public ArrayList<PubSupBean> getStatSupAutoSearch(String term);
		
		// Member Report Auto Complete
		
		public ArrayList<MemberBean> getMemberReportUserAutoSearch(String term);
		public ArrayList<DesignationBean> getMemberReportDesigAutoSearch(String term);
		public ArrayList<GroupBean> getMemberReportGroupAutoSearch(String term);
		public ArrayList<CourseBean> getMemberReportCourseAutoSearch(String term);
		public ArrayList<DepartmentBean> getMemberReportDeptAutoSearch(String term);
		
		//  Payment Report Auto Complete
		
		public ArrayList<MemberBean> getPaymentReportUserAutoSearch(String term);
		public ArrayList<DesignationBean> getPaymentReportDesigAutoSearch(String term);
		public ArrayList<GroupBean> getPaymentReportGroupAutoSearch(String term);
		public ArrayList<CourseBean> getPaymentReportCourseAutoSearch(String term);
		public ArrayList<DepartmentBean> getPaymentReportDeptAutoSearch(String term);
		
		//  Counter Report Auto Complete
		
		public ArrayList<MemberBean> getCounterReportUserAutoSearch(String term);
		public ArrayList<bookbean> getCounterReportAccessNoAutoSearch(String term);
		public ArrayList<GroupBean> getCounterReportGroupAutoSearch(String term);
		public ArrayList<DepartmentBean> getCounterReportDeptAutoSearch(String term);
	
		//  Unique Title Report Auto Complete
		
		public ArrayList<DepartmentBean> getUniqueTitleReportDeptAutoSearch(String term);
		public ArrayList<subjectbean> getUniqueTitleReportSubjectAutoSearch(String term);
		
		//  DB Report Auto Complete
		
		public ArrayList<DepartmentBean> getDBReportDeptAutoSearch(String term);
		public ArrayList<subjectbean> getDBReportSubjectAutoSearch(String term);
		public ArrayList<PubSupBean> getDBReportPubAutoSearch(String term);
		public ArrayList<PubSupBean> getDBReportSupAutoSearch(String term);
		
		// Journal List Auto Complete

		public ArrayList<DepartmentBean> getJournalListDeptAutoSearch(String term);
		public ArrayList<subjectbean> getJournalListSubjectAutoSearch(String term);

		// Journal Issue Report Auto Complete

		public ArrayList<journalbean> getJnlIssuesReportJnlNameAutoSearch(String term);
		
		//  Gate Register Report Auto Complete
		
		public ArrayList<MemberBean> getGateRegisterMemberCodeAutoSearch(String term);
		public ArrayList<GroupBean> getGateRegisterGroupAutoSearch(String term);
		public ArrayList<DepartmentBean> getGateRegisterDeptAutoSearch(String term);
		public ArrayList<DesignationBean> getGateRegisterDesigAutoSearch(String term);


		
}