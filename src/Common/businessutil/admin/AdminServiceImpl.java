package Common.businessutil.admin;
import java.util.ArrayList;
import java.util.List;

import Lib.Auto.BackVolume_Import.BvolumeDataBean;
import Lib.Auto.BackVolume_Import.BvolumeDataMessage;
import Lib.Auto.Book_Import.BookDataBean;
import Lib.Auto.Book_Import.BookDataMessage;
import Lib.Auto.Budget.BudgetBean;
import Lib.Auto.BulkChangesImport.ChangesDataBean;
import Lib.Auto.BulkChangesImport.ChangesDataMessage;
import Lib.Auto.BulkUpdate.BulkUpdateBean;
import Lib.Auto.BulkUpdate.BulkUpdateMsgBean;
import Lib.Auto.EBookImport.EBookDataBean;
import Lib.Auto.EBookImport.EBookDataMessage;
import Lib.Auto.EResource.EResourceBean;
import Lib.Auto.EResourceImport.EResourceDataMessage;
import Lib.Auto.Fine.Finebean;
import Lib.Auto.GateRegister_Import.GateDataBean;
import Lib.Auto.Group.GroupBean;
import Lib.Auto.Holiday.Holidaybean;
import Lib.Auto.Issue_Mas_Update.IssueMasUpdatebean;
import Lib.Auto.Login.LoginBean;
import Lib.Auto.MemberTransfer.MemberTransRefBean;
import Lib.Auto.Member_Import.MemberDataBean;
import Lib.Auto.Member_Import.MemberDataMessage;
import Lib.Auto.Miscellaneous.MiscellaneousBean;
import Lib.Auto.QB_Import.QBDataBean;
import Lib.Auto.QB_Import.QBDataMessage;
import Lib.Auto.RefDueDays.RefDaysBean;
import Lib.Auto.Review.ReviewBean;
import Lib.Auto.Stock.StockBean;
import Lib.Auto.SugEmail.SugmailBean;
import Lib.Auto.Suggestion.SuggestionBean;
import Lib.Auto.TransMas.TransMasBean;

public class AdminServiceImpl implements AdminService {
	
	private AdminDao adminDao;

	public AdminServiceImpl() {
	}
	
	//Fine
	
	public Finebean getFineMax() {
		return adminDao.findFineMax();
	}
	
	public Finebean getFineSearch(int code) {
		return adminDao.findFineSearch(code);
	}
	
	public int getFineDelete(int code) {
		return adminDao.findFineDelete(code);
	}
	
	public int getFineSave(Finebean newbean) {
		return adminDao.findFineSave(newbean);
	}
	
	public int getFineUpdate(Finebean newbean) {
		return adminDao.findFineUpdate(newbean);
	}
	
	public List getFineSearchName(String code) {
		return adminDao.findFineSearchName(code);
	}
	
	public List getGroupSearchName(String code) {
		return adminDao.findGroupSearchName(code);
	}
	
	//Holiday
	
	
	public List getHolidaySearch() {
		return adminDao.findHolidaySearch();
	}
	
	public int getHolidayDeleteAll() {
		return adminDao.findHolidayDeleteAll();
	}
	
	public int getHolidayDelete(String date) {
		return adminDao.findHolidayDelete(date);
	}
	
	public int getHolidaySave(Holidaybean newbean) {
		return adminDao.findHolidaySave(newbean);
	}
	
    //WeakEndHoliday
	public int getWeakEndHolidaySave(int code,int code1)
	{
		return adminDao.findWeakEndHolidaySave(code,code1);
	}
	
	public int getWeakEndHolidaySearch()
	{
		return adminDao.findWeakEndHolidaySearch();
	}
	
	// --------------- Suggestion ------------------
	
				public List getSuggestionList(SuggestionBean sugBean,String Query)
				{
					return adminDao.findSuggestionList(sugBean,Query);
				}	
				
				// --------------- Book Review ------------------
				
				public List getReviewList(ReviewBean revBean)
				{
					return adminDao.findReviewList(revBean);
				} 				

	
	//Budget
	
	
	public BudgetBean getBudgetMax() {
		return adminDao.findBudgetMax();
	}
	
	public BudgetBean getBudgetSearch(String code) {
		return adminDao.findBudgetSearch(code);
	}
	
	public int getBudgetInterface(String code) {
		return adminDao.findBudgetInterface(code);
	}
	public int getBudgetDelete(String code) {
		return adminDao.findBudgetDelete(code);
	}
	
	public BudgetBean getBudgetDeptSearchName(String code) {
		return adminDao.findBudgetDeptSearchName(code);
	}
	
	public BudgetBean getBudgetUpdateSAmount()    {
		return adminDao.findBudgetUpdateSAmount();
	}
	
	public BudgetBean getBudgetSearchName(String code) {
		return adminDao.findBudgetSearchName(code);
	}
	
	
	public int getBudgetSave(BudgetBean newbean) {
		return adminDao.findBudgetSave(newbean);
	}
	
	public int getBudgetUpdate(BudgetBean newbean) {
		return adminDao.findBudgetUpdate(newbean);
	}
	
	
	//Group
	
	public GroupBean getGroupMax() {
		return adminDao.findGroupMax();
	}
	public GroupBean getGroupSearch(String code) {
		return adminDao.findGroupSearch(code);
	}
	public int getGroupInterface(String code) {
		return adminDao.findGroupInterface(code);
	}
	public int getGroupNameInterface(String code) {
		return adminDao.findGroupNameInterface(code);
	}
	public int getGroupDelete(String code) {
		return adminDao.findGroupDelete(code);
	}
	
	public int getGroupSave(GroupBean newbean) {
		return adminDao.findGroupSave(newbean);
	}
	
	public int getGroupUpdate(GroupBean newbean) {
		return adminDao.findGroupUpdate(newbean);
	}
	public List getGroupGenSearch(GroupBean newbean) {
		return adminDao.findGroupGenSearch(newbean);
	}
	
	//Login
	
	public LoginBean getLoginSearch(String code) {
		return adminDao.findLoginSearch(code);
	}
	public int getLoginDelete(String code) {
		return adminDao.findLoginDelete(code);
	}
	
	public int getLoginSave(LoginBean newbean) {
		return adminDao.findLoginSave(newbean);
	}
	
	public int getLoginUpdate(LoginBean newbean) {
		return adminDao.findLoginUpdate(newbean);
	}
	
	public LoginBean getLoginSearchName(String name) {
		return adminDao.findLoginSearchName(name);
	}
	
	
	//Stock
	
	public StockBean getStock(String code) {
		return adminDao.findStock(code);
	}
	
	public StockBean getStockAll() {
		return adminDao.findStockAll();
	}
	
	public StockBean getStockMasSave(String code) {
		return adminDao.findStockMasSave(code);
	}
	public int getStockMasDeleteAll() {
		return adminDao.findStockMasDeleteAll();
	}
	public List getStockMasDisplay(StockBean newbean) {
		return adminDao.findStockMasDisplay(newbean);
	}
	
	public int getStockDeleteSingle(String code) {
		return adminDao.findStockDeleteSingle(code);
	}
	
    // For Bulk Stock
	
	public int getBulkStockSave(List details)
	{
		return adminDao.findBulkStockSave(details);		
	}
	
	// Book Data Import
	
	public BookDataMessage getCheckAccessNo(BookDataBean newbean)
	{
		return adminDao.findCheckAccessNo(newbean);
	}
	
	
	public int getImportBookData(List details)
	{
		return adminDao.findImportBookData(details);
	}
	
	//Data Changes Import
	
		public ChangesDataMessage getCheckAccessNo(ChangesDataBean newbean) {
			return adminDao.findCheckAccessNo(newbean);
		}
		
		public int getImportChangesData(List details) {
			return adminDao.findImportChangesData(details);
		}

		
	// EBook Data Import
	
	public EBookDataMessage getCheckEBookAccessNo(EBookDataBean newbean)
	{
		return adminDao.findCheckEBookAccessNo(newbean);
	}
	
	
	public int getImportEBookData(List details)
	{
		return adminDao.findImportEBookData(details);
	}
	
	//BackVolume Data Import
	
			public BvolumeDataMessage getCheckAccessNo(BvolumeDataBean newbean)
			{
				return adminDao.findCheckAccessNo(newbean);
			}
			
			
			public int getImportBvolumeData(List details)
			{
				return adminDao.findImportBvolumeData(details);
			}
	
	// EResource Data Import
	
		public EResourceDataMessage getCheckcode(EResourceBean newbean)
		{
			return adminDao.findCheckcode(newbean);
		}
		
		
		public int getImportEResourceData(List details)
		{
			return adminDao.findImportEResourceData(details);
		}
	

	
    // Member Data Import
	
	public MemberDataMessage getCheckMemberCode(MemberDataBean newbean)
	{
		return adminDao.findCheckMemberCode(newbean);
	}
	
	public int getImportMemberData(List details)
	{
		return adminDao.findImportMemberData(details);
	}
		
	public int getGroupCode(String groupName)
	{
		return adminDao.getGroupCode(groupName);
	}
	

	public int getBudgetCode(String budget) {
		return adminDao.getBudgetCode(budget);
	}
	
	//QB Data import
	
		public QBDataMessage getCheckQBCode(QBDataBean newbean)
		{
			return adminDao.findCheckQBCode(newbean);
		}
		
		
		public int getImportQBData(List details)
		{
			return adminDao.findImportQBData(details);
		}
	
	
	
    //  ---------------------- Transaction Master ----------	
	
	public TransMasBean getTransCodeMax() {
	    return adminDao.findTransCodeMax();
    }	
	
	public TransMasBean getTransMasSave(TransMasBean transBean)  {
		
		return adminDao.findTransMasSave(transBean);
	}
	
	public int getTransMasUpdate(TransMasBean transBean)	
	{
		return adminDao.findTransMasUpdate(transBean);
	}
	public TransMasBean getTransMasSearch(int code)  {
		
		return adminDao.findTransMasSearch(code);
	}
	
	public int getTransMasDelete(int code)    {
		
		return adminDao.findTransMasDelete(code);
	}
	
	
//  ---------------------- Miscellaneous & Trans_Mas Master ----------		
	
	public MiscellaneousBean getMiscellaneousMax()
	{
		return adminDao.findMiscellaneousMax();
	}
	
	public MiscellaneousBean getMiscellaneousSave(MiscellaneousBean transBean)
	{
		return adminDao.findMiscellaneousSave(transBean);
	}
	
	public MiscellaneousBean getMiscellaneousMember(String code)
	{
		return adminDao.findMiscellaneousMember(code);
	}
	
	public List getMiscellaneousTHead()
	{
		return adminDao.findMiscellaneousTHead();
	}
	
	public MiscellaneousBean getTransMasPayment(MiscellaneousBean transBean)
	{
		return adminDao.findTransMasPayment(transBean);
	}
	
	public MiscellaneousBean getMiscellaneousSearch(int code)
	{
		return adminDao.findMiscellaneousSearch(code);
	}
	
	public int getMiscellaneousDelete(int code)
	{
		return adminDao.findMiscellaneousDelete(code);
	}
	
	public int getMiscellaneousUpdate(MiscellaneousBean transBean)
	{
		return adminDao.findMiscellaneousUpdate(transBean);
	}
	

//  ---------------------- Member Transfer ----------		
	
	public List getDepartmentList()
	{
		return adminDao.findDepartmentList();
	}
	
	public List getCourseList()
	{
		return adminDao.findCourseList();
	}
	
	public List getGroupList()
	{
		return adminDao.findGroupList();
	}
	
	public List getQBSubjectList()
	{
		return adminDao.findQBSubjectList();
	}
	
		
	public List getMemberList(MemberTransRefBean newBean)
	{
		return adminDao.findMemberList(newBean);
	}
	
	public int getMemberTransfer(MemberTransRefBean newBean)
	{
		return adminDao.findMemberTransfer(newBean);
	}
		
	
// --------------- Bulk Updation ---------------	
	
	public List getBulkUpdateList(String query, String flag)
	{		
		List list = adminDao.findBulkUpdateList(query,flag);
		List<Object> finalResults = null;
		
		if(list != null && list.size() > 0)
		{
			finalResults = new ArrayList<Object>();			
			for(int i=0; i < list.size(); i++)
			{
				
				if(flag.equalsIgnoreCase("Invoice_No"))
				{
//					System.out.println("list.get(i)"+list.get(i));
					//Object[] obj = (Object[]) list.get(i);				
					BulkUpdateBean bean = new BulkUpdateBean();
					
					//bean.setCode(Integer.parseInt(obj[0].toString()));
					bean.setCode(String.valueOf(list.get(i)));
					bean.setName(String.valueOf(list.get(i)));	
					finalResults.add(bean);
				}
				else
				{
					Object[] obj = (Object[]) list.get(i);				
					BulkUpdateBean bean = new BulkUpdateBean();
				
					//bean.setCode(Integer.parseInt(obj[0].toString()));
					bean.setCode(obj[0].toString());
  				bean.setName(obj[1].toString());
  				finalResults.add(bean);
  				
				}			
				
			}		
		}	
		return finalResults;
	}
	
	public int getBulkUpdateSave(BulkUpdateMsgBean newBean)
	{
		return adminDao.findBulkUpdateSave(newBean);
	}
	
// --------------- Bulk Member Update ---------------	
	

	public List getBulkMemberUpdateList(String query, String flag)
	{		
		List list = adminDao.findBulkMemberUpdateList(query,flag);
		List<Object> finalResults = null;
		
		if(list != null && list.size() > 0)
		{
			finalResults = new ArrayList<Object>();			
			for(int i=0; i < list.size(); i++)
			{
				Object[] obj = (Object[]) list.get(i);				
				BulkUpdateBean bean = new BulkUpdateBean();
				
				//bean.setCode(Integer.parseInt(obj[0].toString()));
				bean.setCode(obj[0].toString());
				bean.setName(obj[1].toString());			
				finalResults.add(bean);
			}		
		}	
		return finalResults;
	}
	
	
	public int getBulkMemberUpdateSave(BulkUpdateMsgBean newBean)
	{
		return adminDao.findBulkMemberUpdateSave(newBean);
	}
	
	public SugmailBean getsugEmail()
	{
		return adminDao.getsugEmail();
	}
	
	public RefDaysBean getRefDueDays()
	{
		return adminDao.getRefDueDays();
	}
	
	public int getsugEmailsave(SugmailBean newBean)
	{
		return adminDao.getsugEmailsave(newBean);
	}
	
	public int getduedaysSave(RefDaysBean newBean)
	{
		return adminDao.getduedaysSave(newBean);
	}
	
	public AdminDao getAdminDao() {
		return adminDao;
	}
	
	public void setAdminDao(AdminDao adminDao) {
		this.adminDao = adminDao;
	}
	
	public int getBranchCode(String branch)
	{
		return adminDao.getBranchCode(branch);
	}


	// ------------Due Date Update--------------

		public int getDueDateCount(IssueMasUpdatebean newBean) {
			return adminDao.findDueDateCount(newBean);
		}

		public int getDueDateUpdate(IssueMasUpdatebean newBean) {
			return adminDao.findDueDateUpdate(newBean);
		}

		@Override
		public int getImportGateData(List details) {
			
			
			for (int k = 0; k < details.size(); k++)
			{
				GateDataBean detailsbean=(GateDataBean) details.get(k);
				
			/*int inTime=	Integer.parseInt(detailsbean.getInTime());
			int outTime = Integer.parseInt(detailsbean.getOutTime());
			*/
			
			
			 		  
			 // Start Save Data into Web_mas Table
			  
						//getSession().save(detailsbean);
						//getSession().flush();
						//getSession().clear();
						
						
				}
			
			
			return adminDao.findImportGateData(details);
		}



		
	}