package Common.businessutil.admin;
import java.util.List;

import Lib.Auto.BackVolume_Import.BvolumeDataBean;
import Lib.Auto.BackVolume_Import.BvolumeDataMessage;
import Lib.Auto.Book_Import.BookDataBean;
import Lib.Auto.Book_Import.BookDataMessage;
import Lib.Auto.Budget.BudgetBean;
import Lib.Auto.BulkChangesImport.ChangesDataBean;
import Lib.Auto.BulkChangesImport.ChangesDataMessage;
import Lib.Auto.BulkUpdate.BulkUpdateMsgBean;
import Lib.Auto.EBookImport.EBookDataBean;
import Lib.Auto.EBookImport.EBookDataMessage;
import Lib.Auto.EResource.EResourceBean;
//import Lib.Auto.EResourceImport.EResourceDataBean;
import Lib.Auto.EResourceImport.EResourceDataMessage;
import Lib.Auto.Fine.Finebean;
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

public interface AdminDao {
	
	
	//Fine
	public Finebean findFineMax();	
	public Finebean findFineSearch(int code);	
	public int findFineDelete(int code);	
	public int findFineSave(Finebean newbean);	
	public int findFineUpdate(Finebean newbean);	
	public List findFineSearchName(String code);	
	public List findGroupSearchName(String code);
	
	//Holiday
	
	public List findHolidaySearch();	
	public int findHolidayDeleteAll();	
	public int findHolidayDelete(String date);	
	public int findHolidaySave(Holidaybean newbean);
	
   //WeakEndHoliday
	public int findWeakEndHolidaySave(int code,int code1);
	public int findWeakEndHolidaySearch();
	
//  --------------------- Suggestion -----------------
	
	public List findSuggestionList(SuggestionBean sugBean,String Query);	

//  --------------------- Book Review -----------------
	
	public List findReviewList(ReviewBean revBean);	
	
	//Budget
	
	public BudgetBean findBudgetMax();	
	public BudgetBean findBudgetSearch(String code);
	public int findBudgetInterface(String code);
	public int findBudgetDelete(String code);
	public BudgetBean findBudgetDeptSearchName(String code);
	public BudgetBean findBudgetUpdateSAmount();
	public BudgetBean findBudgetSearchName(String code);
	public int findBudgetSave(BudgetBean newbean);	
	public int findBudgetUpdate(BudgetBean newbean);
	
	
	//Group
	
	public GroupBean findGroupMax();	
	public GroupBean findGroupSearch(String code);
	public int findGroupInterface(String code);
	public int findGroupNameInterface(String code);
	public int findGroupDelete(String code);
	public int findGroupSave(GroupBean newbean);	
	public int findGroupUpdate(GroupBean newbean);
	public List findGroupGenSearch(GroupBean newbean);
	
	//Login
	public LoginBean findLoginSearch(String code);
	public int findLoginDelete(String code);
	public int findLoginSave(LoginBean newbean);	
	public int findLoginUpdate(LoginBean newbean);
	public LoginBean findLoginSearchName(String name);
	
	//Stock
	
	public StockBean findStock(String code);
	public StockBean findStockAll();
	public StockBean findStockMasSave(String code);
	public int findStockMasDeleteAll();
	public List findStockMasDisplay(StockBean newbean);
	public int findStockDeleteSingle(String code);
	
	public int findBulkStockSave(List details);
	
    //  Book Data Import
	
	public BookDataMessage findCheckAccessNo(BookDataBean newbean);
	public int findImportBookData(List details);	
	
	
	//BackVolume Data Import
	
			public BvolumeDataMessage findCheckAccessNo(BvolumeDataBean newbean);
			public int findImportBvolumeData(List details);
		
    //  EBook Data Import
	
	public EBookDataMessage findCheckEBookAccessNo(EBookDataBean newbean);
	public int findImportEBookData(List details);
	
	//  EResorce Data Import
	
	public EResourceDataMessage findCheckcode(EResourceBean newbean);
	public int findImportEResourceData(List details);	
	
	//Data Changes Import
	
		public ChangesDataMessage findCheckAccessNo(ChangesDataBean newbean);
		public int findImportChangesData(List details);
		
	
	
    // Member Data Import
	
	public MemberDataMessage findCheckMemberCode(MemberDataBean newbean);
	public int findImportMemberData(List details);
	
	public int getGroupCode(String groupName);
	public int getBudgetCode(String budget);
	
	
	//QB Data Import
	
	public QBDataMessage findCheckQBCode(QBDataBean newbean);
	public int findImportQBData(List details);

    //  ---------------------- Transaction Master ----------	
	
	public TransMasBean findTransCodeMax();	
	public TransMasBean findTransMasSave(TransMasBean transBean);
	public int findTransMasUpdate(TransMasBean transBean);	
	public TransMasBean findTransMasSearch(int code);
	public int findTransMasDelete(int code);
	
	
//  ---------------------- Miscellaneous & Trans_Mas Master ----------		
		
	public MiscellaneousBean findMiscellaneousMax();
	public MiscellaneousBean findMiscellaneousSave(MiscellaneousBean transBean);
	public MiscellaneousBean findMiscellaneousMember(String code);
	public List findMiscellaneousTHead();
	public MiscellaneousBean findTransMasPayment(MiscellaneousBean transBean);
	public MiscellaneousBean findMiscellaneousSearch(int code);
	public int findMiscellaneousDelete(int code);
	public int findMiscellaneousUpdate(MiscellaneousBean transBean);
	

//  ---------------------- Member Transfer ----------		
	
	public List findDepartmentList();
	public List findCourseList();
	public List findGroupList();
	public List findQBSubjectList();
	
	public List findMemberList(MemberTransRefBean newBean);
	public int findMemberTransfer(MemberTransRefBean newBean);

//  ---------------------- Bulk Updation ----------	
	public List findBulkUpdateList(String query,String flag);
	public int findBulkUpdateSave(BulkUpdateMsgBean newBean);
	
	
//------------------Bulk Member Update----------------------
	
	
	public List findBulkMemberUpdateList(String query,String flag);
	public int findBulkMemberUpdateSave(BulkUpdateMsgBean newBean);
//-------------------Suggestion Emai id-----------
	public int getsugEmailsave(SugmailBean newBean);	
	public SugmailBean getsugEmail();	
//  ---------------------- Reference Book Due Days ----------	
		
	public RefDaysBean getRefDueDays();	
	public int getduedaysSave(RefDaysBean newBean);	
	
	public int getBranchCode(String branch);
	
	
	//-------------------Due Date Update-------------
	
		public int findDueDateCount(IssueMasUpdatebean newBean);
		public int findDueDateUpdate(IssueMasUpdatebean newBean);
		
		
		//
		public int findImportGateData(List details);
}