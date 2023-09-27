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
import Lib.Auto.Subject.subjectbean;
import Lib.Auto.Member.MemberBean;
import Lib.Auto.Designation.DesignationBean;
import Lib.Auto.Course.CourseBean;
import Lib.Auto.Group.GroupBean;
import Lib.Auto.Book.bookbean;


public interface ReportDao {
	
	public List findMemberSearch(MemberReportBean ReportBean);
	
	public List findDeptSearch(MemberReportBean ReportBean);
	
	public List findGroupSearch(MemberReportBean ReportBean);
	
	public List findCourseSearch(MemberReportBean ReportBean);
	
	
	
	public List findtodayIssueListDetails(String search);
	
	public List findtodayReturnListDetails(String search);
	
	public List findtodayRenewalListDetails(String search);
	
	public List findtodayTransAmountDetails(String search);
	
	public List findtodayPaidDetails(String search);
	
	
	
	//Journal List Report
	public List findDeptJnlSearch(JournalListBean ReportBean);
	
	public List findPubJnlSearch(JournalListBean ReportBean);
	
	public List findSubJnlSearch(JournalListBean ReportBean);
	
	//Journal Issue Report
	
	public List findJnlNameSearch(JnlIssueReportBean ReportBean);
	
	//For Report Connection
	
	public java.sql.Connection findDBConnect();
	
	public boolean  findCheckData(String query);
	
	public int findAccessNo(accessbean newBean);
	public int findAccessNoSave(accessbean newBean);
	public int findDeleteMisNo();
	
	//::::::::::::::::::member report::::::::::::::::::::::::::::::::

	public List findDesigList(MemberTransRefBean newBean);
	
	public List findDepartmentList(MemberTransRefBean newBean);
	
	
	
	public List findGroupList(MemberTransRefBean newBean);
	public List findCourseList(MemberTransRefBean newBean);
	
	
	public List findSearchBudgetList(BudgetBean newBean);
	
	//  AutoComplete Statistics Report
	
	public ArrayList<DepartmentBean> findStatDeptAutoSearch(String term);
	public ArrayList<subjectbean> findStatSubjectAutoSearch(String term);
	public ArrayList<PubSupBean> findStatPubAutoSearch(String term);
	public ArrayList<PubSupBean> findStatSupAutoSearch(String term);
	
	// AutoComplete Member Report
	
	public ArrayList<MemberBean> findMemberReportUserAutoSearch(String term);
	public ArrayList<DesignationBean> findMemberReportDesigAutoSearch(String term);
	public ArrayList<GroupBean> findMemberReportGroupAutoSearch(String term);
	public ArrayList<CourseBean> findMemberReportCourseAutoSearch(String term);
	public ArrayList<DepartmentBean> findMemberReportDeptAutoSearch(String term);

	// AutoComplete Payment Report
	
		public ArrayList<MemberBean> findPaymentReportUserAutoSearch(String term);
		public ArrayList<DesignationBean> findPaymentReportDesigAutoSearch(String term);
		public ArrayList<GroupBean> findPaymentReportGroupAutoSearch(String term);
		public ArrayList<CourseBean> findPaymentReportCourseAutoSearch(String term);
		public ArrayList<DepartmentBean> findPaymentReportDeptAutoSearch(String term);
		
		// AutoComplete Counter Report
		
		public ArrayList<MemberBean> findCounterReportUserAutoSearch(String term);
		public ArrayList<bookbean> findCounterReportAccessNoAutoSearch(String term);
		public ArrayList<GroupBean> findCounterReportGroupAutoSearch(String term);
		public ArrayList<DepartmentBean> findCounterReportDeptAutoSearch(String term);
		
		// AutoComplete Counter Report
		
		public ArrayList<DepartmentBean> findUniqueTitleReportDeptAutoSearch(String term);
		public ArrayList<subjectbean> findUniqueTitleReportSubjectAutoSearch(String term);
		
		// AutoComplete DB Report
		
		public ArrayList<DepartmentBean> findDBReportDeptAutoSearch(String term);
		public ArrayList<subjectbean> findDBReportSubjectAutoSearch(String term);
		public ArrayList<PubSupBean> findDBReportPubAutoSearch(String term);
		public ArrayList<PubSupBean> findDBReportSupAutoSearch(String term);

        //  AutoComplete Journal List
		
		public ArrayList<DepartmentBean> findJournalListDeptAutoSearch(String term);
		public ArrayList<subjectbean> findJournalListSubjectAutoSearch(String term);
		
	//  AutoComplete Journal Issues Report
		
		public ArrayList<journalbean> findJnlIssuesReportJnlNameAutoSearch(String term);
		
    // AutoComplete DB Report
		
		public ArrayList<MemberBean> findGateRegisterMemberCodeAutoSearch(String term);
		public ArrayList<GroupBean> findGateRegisterGroupAutoSearch(String term);
		public ArrayList<DepartmentBean> findGateRegisterDeptAutoSearch(String term);
		public ArrayList<DesignationBean> findGateRegisterDesigAutoSearch(String term);	

}