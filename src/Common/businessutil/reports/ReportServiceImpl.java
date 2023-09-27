package Common.businessutil.reports;

import java.util.ArrayList;
import java.util.List;

import Common.Security;
import Lib.Auto.AccessionRegister.accessbean;
import Lib.Auto.Budget.BudgetBean;
import Lib.Auto.Department.DepartmentBean;
import Lib.Auto.JnlIssueReport.JnlIssueReportBean;
import Lib.Auto.Journal.journalbean;
import Lib.Auto.JournalList.JournalListBean;
import Lib.Auto.MemberReport.MemberReportBean;
import Lib.Auto.MemberTransfer.MemberTransRefBean;
import Lib.Auto.PubSup.PubSupBean;
import Lib.Auto.Report.reportbean;
import Lib.Auto.Subject.subjectbean;
import Lib.Auto.Member.MemberBean;
import Lib.Auto.Book.bookbean;
import Lib.Auto.Designation.DesignationBean;
import Lib.Auto.Course.CourseBean;
import Lib.Auto.Group.GroupBean;


public class ReportServiceImpl implements ReportService {
	
	private ReportDao reportDao;

	public ReportServiceImpl() {
		
			}
	

	public List getMemberSearch(MemberReportBean ReportBean) {
		
		return reportDao.findMemberSearch(ReportBean);
	}

	public List getDeptSearch(MemberReportBean ReportBean) {
		
		return reportDao.findDeptSearch(ReportBean);
	}


	public List getGroupSearch(MemberReportBean ReportBean) {
		
		return reportDao.findGroupSearch(ReportBean);
	}



	public List getCourseSearch(MemberReportBean ReportBean) {
		
		return reportDao.findCourseSearch(ReportBean);
	}
	
	
	
public List gettodayIssueListDetails(String search) {
		
		
		
		
		{
			List results=getReportDao().findtodayIssueListDetails(search);
			List finalResults = null;
			if(results != null)
			{
				finalResults = new ArrayList();
				for(int i = 0; i < results.size(); i++)
				{
					System.out.println("=====results.get(i)===="+results.get(i));
//					Object[] obj = (Object[])results.get(i);
					reportbean prt= new reportbean();
					prt = (reportbean) results.get(i);
//					prt.setUcode(obj[0].toString());
//					prt.setUname(obj[1].toString());
//					prt.setAccno(obj[2].toString());
//					prt.setTitle(obj[3].toString());
//					prt.setAuthorName(obj[4].toString());
//					prt.setIssueDate(Security.getdate(obj[5].toString()));
//					prt.setDueDate(Security.getdate(obj[6].toString()));
//				    prt.setDocType(obj[7].toString());
//				    prt.setStaffCode(obj[8].toString());
//				    
					finalResults.add(prt);
				}
			}
			return finalResults;
		}
	}
	
	
public List gettodayReturnListDetails(String search) {
		
		
		
		
		{
			List results=getReportDao().findtodayReturnListDetails(search);
			List finalResults = null;
			if(results != null)
			{
				finalResults = new ArrayList();
				for(int i = 0; i < results.size(); i++)
				{
					Object[] obj = (Object[])results.get(i);
					reportbean prt= new reportbean();
					prt.setUcode(obj[0].toString());
					prt.setUname(obj[1].toString());
					prt.setAccno(obj[2].toString());
					prt.setTitle(obj[3].toString());
					prt.setAuthorName(obj[4].toString());
					prt.setIssueDate(Security.getdate(obj[5].toString()));
					prt.setDueDate(Security.getdate(obj[6].toString()));		
					prt.setReturnDate(Security.getdate(obj[7].toString()));
				    prt.setDocType(obj[8].toString());
				    prt.setStaffCode(obj[9].toString());
				    
					finalResults.add(prt);
				}
			}
			return finalResults;
		}
	}

public List gettodayRenewalListDetails(String search) {
	
	
	
	{
		List results=getReportDao().findtodayRenewalListDetails(search);
		List finalResults = null;
		if(results != null)
		{
			finalResults = new ArrayList();
			for(int i = 0; i < results.size(); i++)
			{
				
				reportbean prt= new reportbean();
				prt = (reportbean)results.get(i);
//				prt.setUcode(obj[0].toString());
//				prt.setUname(obj[1].toString());
//				prt.setAccno(obj[2].toString());
//				prt.setTitle(obj[3].toString());
//				prt.setAuthorName(obj[4].toString());
//				prt.setIssueDate(Security.getdate(obj[5].toString()));
//				prt.setDueDate(Security.getdate(obj[6].toString()));
//			    prt.setDocType(obj[7].toString());
//			    prt.setStaffCode(obj[8].toString());
			    
				finalResults.add(prt);
			}
		}
		return finalResults;
	}
}


public List gettodayTransAmountDetails(String search) {
	
	
	
	{
		List results=getReportDao().findtodayTransAmountDetails(search);
		List finalResults = null;
		if(results != null)
		{
			finalResults = new ArrayList();
			for(int i = 0; i < results.size(); i++)
			{
				reportbean prt= new reportbean();
				prt = (reportbean)results.get(i);
//				prt.setUcode(obj[0].toString());
//				prt.setUname(obj[1].toString());
//				
//				if(obj[2].toString()!=null && !obj[2].toString().isEmpty()){
//					prt.setAccno(obj[2].toString());
//				}else{
//					prt.setAccno("");
//				}
//				
//				if(obj[3].toString()!=null && !obj[3].toString().isEmpty()){
//					
//					prt.setTitle(obj[3].toString());
//				}else{
//					prt.setTitle("");
//				}
//				
//				if(obj[4].toString()!=null && !obj[4].toString().isEmpty()){
//					prt.setAuthorName(obj[4].toString());
//				}else{
//					prt.setAuthorName("");
//				}
//				
//				if(obj[5].toString()!=null && !obj[5].toString().isEmpty()){
//					prt.setIssueDate(Security.getdate(obj[5].toString()));
//				}else{
//					prt.setIssueDate("");
//				}
//				
//				if(obj[6].toString()!=null && !obj[6].toString().isEmpty()){
//					prt.setDueDate(Security.getdate(obj[6].toString()));
//				}else{
//					prt.setDueDate("");
//				}
//				prt.setReturnDate(Security.getdate(obj[7].toString()));
//				
//				prt.setFineAmount(obj[8].toString());
//				prt.setDocType(obj[9].toString());
//			    prt.setStaffCode(obj[10].toString());
			
			    
				finalResults.add(prt);
			}
		}
		return finalResults;
	}
}


public List gettodayPaidDetails(String search) {
	
	{
		List results=getReportDao().findtodayPaidDetails(search);
		List finalResults = null;
		if(results != null)
		{
			finalResults = new ArrayList();
			for(int i = 0; i < results.size(); i++)
			{
				Object[] obj = (Object[])results.get(i);
				reportbean prt= new reportbean();
				
				prt.setBillNo(Integer.parseInt(obj[0].toString()));
				prt.setUcode(obj[1].toString());
				prt.setUname(obj[2].toString());
				prt.setFineAmount(obj[3].toString());
				prt.setReturnDate(Security.getdate(obj[4].toString()));
				prt.setPaymode(obj[5].toString());
				prt.setStaffCode(obj[6].toString());
				prt.setDocType(obj[7].toString());
				
					finalResults.add(prt);
			}
		}
		return finalResults;
	}
}

	
	
	
	

//Journal List Report
	
	public List getDeptJnlSearch(JournalListBean ReportBean) {
		
		return reportDao.findDeptJnlSearch(ReportBean);
	}
	
	public List getPubJnlSearch(JournalListBean ReportBean) {
		
		return reportDao.findPubJnlSearch(ReportBean);
	}

	public List getSubJnlSearch(JournalListBean ReportBean) {
		
		return reportDao.findSubJnlSearch(ReportBean);
	}
	
	//Journal Issue Report
	public List getJnlNameSearch(JnlIssueReportBean ReportBean) {
		
		return reportDao.findJnlNameSearch(ReportBean);
	}

	
	public java.sql.Connection getDBConnect()
	{
		return reportDao.findDBConnect();
	}
	
	public ReportDao getReportDao() {
		return reportDao;
	}

	public void setReportDao(ReportDao ReportDao) {
		this.reportDao = ReportDao;
	}	
		public boolean  getCheckData(String query) {
		return reportDao.findCheckData(query);
	}
	
		public int getAccessNo(accessbean newbean)
		{
			return reportDao.findAccessNo(newbean);
		}
		public int getAccessNoSave(accessbean newbean)
		{
			return reportDao.findAccessNoSave(newbean);
		}
		public int getDeleteMisNo()
		{
			return reportDao.findDeleteMisNo();
		}	
		
		//::::::::::::::::::::::::::::::::member report::::::::::::::::::::::::::::::::::::
		
		

		public List getDesigList(MemberTransRefBean newBean)
		{
			return reportDao.findDesigList(newBean);
		}
		
		
		public List getDepartmentList(MemberTransRefBean newBean)
		{
			return reportDao.findDepartmentList(newBean);
		}
		
		
		public List getGroupList(MemberTransRefBean newBean)
		{
			return reportDao.findGroupList(newBean);
		}
		public List getCourseList(MemberTransRefBean newBean)
		{
			return reportDao.findCourseList(newBean);
		}
		
		
		public List getSearchBudgetList(BudgetBean newBean)
		{
			return reportDao.findSearchBudgetList(newBean);
		}
		
	//---------AutoComplete Statistics Report------------
		
		public ArrayList<DepartmentBean> getStatDeptAutoSearch(String term) {
			 
			return reportDao.findStatDeptAutoSearch(term);
			}

		public ArrayList<subjectbean> getStatSubjectAutoSearch(String term) {
			 
			return reportDao.findStatSubjectAutoSearch(term);
			}

		public ArrayList<PubSupBean> getStatPubAutoSearch(String term) {
			 
			return reportDao.findStatPubAutoSearch(term);
			}
		
		public ArrayList<PubSupBean> getStatSupAutoSearch(String term) {
			 
			return reportDao.findStatSupAutoSearch(term);
			}
		
		//  AutoComplete Member Report

		public ArrayList<MemberBean> getMemberReportUserAutoSearch(String term) {
			 
			return reportDao.findMemberReportUserAutoSearch(term);
			}

		
		public ArrayList<DesignationBean> getMemberReportDesigAutoSearch(String term) {
			 
			return reportDao.findMemberReportDesigAutoSearch(term);
			}

		public ArrayList<GroupBean> getMemberReportGroupAutoSearch(String term) {
			 
			return reportDao.findMemberReportGroupAutoSearch(term);
			}

		public ArrayList<CourseBean> getMemberReportCourseAutoSearch(String term) {
			 
			return reportDao.findMemberReportCourseAutoSearch(term);
			}
				
		public ArrayList<DepartmentBean> getMemberReportDeptAutoSearch(String term) {
			 
			return reportDao.findMemberReportDeptAutoSearch(term);
			}

		//  AutoComplete Payment Report

		public ArrayList<MemberBean> getPaymentReportUserAutoSearch(String term) {
			 
			return reportDao.findPaymentReportUserAutoSearch(term);
			}

		
		public ArrayList<DesignationBean> getPaymentReportDesigAutoSearch(String term) {
			 
			return reportDao.findPaymentReportDesigAutoSearch(term);
			}

		public ArrayList<GroupBean> getPaymentReportGroupAutoSearch(String term) {
			 
			return reportDao.findPaymentReportGroupAutoSearch(term);
			}

		public ArrayList<CourseBean> getPaymentReportCourseAutoSearch(String term) {
			 
			return reportDao.findPaymentReportCourseAutoSearch(term);
			}
				
		public ArrayList<DepartmentBean> getPaymentReportDeptAutoSearch(String term) {
			 
			return reportDao.findPaymentReportDeptAutoSearch(term);
			}

		//  AutoComplete Counter Report

		public ArrayList<MemberBean> getCounterReportUserAutoSearch(String term) {
			 
			return reportDao.findCounterReportUserAutoSearch(term);
			}

		
		public ArrayList<bookbean> getCounterReportAccessNoAutoSearch(String term) {
			 
			return reportDao.findCounterReportAccessNoAutoSearch(term);
			}

		public ArrayList<GroupBean> getCounterReportGroupAutoSearch(String term) {
			 
			return reportDao.findCounterReportGroupAutoSearch(term);
			}

		public ArrayList<DepartmentBean> getCounterReportDeptAutoSearch(String term) {
			 
			return reportDao.findCounterReportDeptAutoSearch(term);
			}
        
		//  AutoComplete Unique Title Report

		public ArrayList<DepartmentBean> getUniqueTitleReportDeptAutoSearch(String term) {
			 
			return reportDao.findUniqueTitleReportDeptAutoSearch(term);
			}

		
		public ArrayList<subjectbean> getUniqueTitleReportSubjectAutoSearch(String term) {
			 
			return reportDao.findUniqueTitleReportSubjectAutoSearch(term);
			}
		
		//  AutoComplete DB Report

		public ArrayList<DepartmentBean> getDBReportDeptAutoSearch(String term) {
			 
			return reportDao.findDBReportDeptAutoSearch(term);
			}

		
		public ArrayList<subjectbean> getDBReportSubjectAutoSearch(String term) {
			 
			return reportDao.findDBReportSubjectAutoSearch(term);
			}

		public ArrayList<PubSupBean> getDBReportPubAutoSearch(String term) {
			 
			return reportDao.findDBReportPubAutoSearch(term);
			}

		public ArrayList<PubSupBean> getDBReportSupAutoSearch(String term) {
			 
			return reportDao.findDBReportSupAutoSearch(term);
			}

       
		//  AutoComplete Journal List

		public ArrayList<DepartmentBean> getJournalListDeptAutoSearch(String term) {
			 
			return reportDao.findJournalListDeptAutoSearch(term);
			}

		
		public ArrayList<subjectbean> getJournalListSubjectAutoSearch(String term) {
			 
			return reportDao.findJournalListSubjectAutoSearch(term);
			}
		
	//  AutoComplete Journal Issues Report

			public ArrayList<journalbean> getJnlIssuesReportJnlNameAutoSearch(String term) {
				 
				return reportDao.findJnlIssuesReportJnlNameAutoSearch(term);
				}
   
			//  AutoComplete Gate Register Report

			public ArrayList<MemberBean> getGateRegisterMemberCodeAutoSearch(String term) {
				 
				return reportDao.findGateRegisterMemberCodeAutoSearch(term);
				}

			
			public ArrayList<GroupBean> getGateRegisterGroupAutoSearch(String term) {
				 
				return reportDao.findGateRegisterGroupAutoSearch(term);
				}

			public ArrayList<DepartmentBean> getGateRegisterDeptAutoSearch(String term) {
				 
				return reportDao.findGateRegisterDeptAutoSearch(term);
				}
			public ArrayList<DesignationBean> getGateRegisterDesigAutoSearch(String term) {
				 
				return reportDao.findGateRegisterDesigAutoSearch(term);
				}
		
			
	
	}