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

public interface AdminService {
	
	
	//Fine
	public Finebean getFineMax();	
	public Finebean getFineSearch(int code);	
	public int getFineDelete(int code);	
	public int getFineSave(Finebean newbean);	
	public int getFineUpdate(Finebean newbean);	
	public List getFineSearchName(String code);
	public List getGroupSearchName(String code);	
	
	//Holiday
		
	public List getHolidaySearch();	
	public int getHolidayDeleteAll();	
	public int getHolidayDelete(String date);	
	public int getHolidaySave(Holidaybean newbean);
	
    //WeakEndHoliday
	public int getWeakEndHolidaySave(int code,int code1);
	public int getWeakEndHolidaySearch();
	
//  --------------------- Suggestion -------------------
	
	public List getSuggestionList(SuggestionBean sugBean,String query);
	
//  --------------------- Book Review -------------------
	
	public List getReviewList(ReviewBean revBean);	
	
	//Budget
	
	public BudgetBean getBudgetMax();		
	public BudgetBean getBudgetSearch(String code);	
	public int getBudgetInterface(String code);	
	public int getBudgetDelete(String code);
	public BudgetBean getBudgetDeptSearchName(String code);
	public BudgetBean getBudgetUpdateSAmount();
	public BudgetBean getBudgetSearchName(String code);
	public int getBudgetSave(BudgetBean newbean);
	public int getBudgetUpdate(BudgetBean newbean);
	
	//Group
	
	public GroupBean getGroupMax();
	public GroupBean getGroupSearch(String code);
	public int getGroupInterface(String code);	
	public int getGroupNameInterface(String code);	
	public int getGroupDelete(String code);
	public int getGroupSave(GroupBean newbean);
	public int getGroupUpdate(GroupBean newbean);
	public List getGroupGenSearch(GroupBean newbean);
	
	
	//Login
	public LoginBean getLoginSearch(String code);
	public int getLoginDelete(String code);
	public int getLoginSave(LoginBean newbean);
	public int getLoginUpdate(LoginBean newbean);
	public LoginBean getLoginSearchName(String name);
	
	//Stock
	
	public StockBean getStock(String code);
	public StockBean getStockAll();
	public StockBean getStockMasSave(String code);
	public int getStockMasDeleteAll();
	public List getStockMasDisplay(StockBean newbean);
	public int getStockDeleteSingle(String code);
	
	public int getBulkStockSave(List details);
	
    // Book Data Import
	
	public BookDataMessage getCheckAccessNo(BookDataBean newbean);
	public int getImportBookData(List details);
	
	// BackVolume Data Import
	
	public BvolumeDataMessage getCheckAccessNo(BvolumeDataBean newbean);
	public int getImportBvolumeData(List details);
	
	//Data Changes Import
		public ChangesDataMessage getCheckAccessNo(ChangesDataBean newbean);
		public int getImportChangesData(List details);
	
	// EResource Data Import
	
	public EResourceDataMessage getCheckcode(EResourceBean newbean);
	public int getImportEResourceData(List details);
	
    // EBook Data Import
	
	public EBookDataMessage getCheckEBookAccessNo(EBookDataBean bookData);
	public int getImportEBookData(List details);

    // Member Data Import
	
	public MemberDataMessage getCheckMemberCode(MemberDataBean newbean);
	public int getImportMemberData(List details);
	public int getGroupCode(String groupName);
	public int getBudgetCode(String budget);
	
	//QB Data Import
	
		public QBDataMessage getCheckQBCode(QBDataBean bookData);
		public int getImportQBData(List details);
		
		
	// Gate Register Import
		
		public int getImportGateData(List details);
		
		

	
    //  ---------------------- Transaction Master ----------	
	
	public TransMasBean getTransCodeMax();	
	public TransMasBean getTransMasSave(TransMasBean transBean);	
	public int getTransMasUpdate(TransMasBean transBean);	
	public TransMasBean getTransMasSearch(int code);
	public int getTransMasDelete(int code);
	
	
//  ---------------------- Miscellaneous & Trans_Mas Master ----------	
	
	public MiscellaneousBean getMiscellaneousMax();		
	public MiscellaneousBean getMiscellaneousSave(MiscellaneousBean transBean);	
	public MiscellaneousBean getMiscellaneousMember(String code);
	public List getMiscellaneousTHead();	
	public MiscellaneousBean getTransMasPayment(MiscellaneousBean transBean);
	public MiscellaneousBean getMiscellaneousSearch(int code);
	public int getMiscellaneousDelete(int code);
	public int getMiscellaneousUpdate(MiscellaneousBean transBean);

//  ---------------------- Member Transfer ----------		
	
	public List getDepartmentList();
	public List getCourseList();
	public List getGroupList();
	public List getQBSubjectList();
	
	public List getMemberList(MemberTransRefBean newBean);
	public int getMemberTransfer(MemberTransRefBean newBean);
		
//  ---------------------- Bulk Updation ----------	
	
	public List getBulkUpdateList(String query, String flag);	
	public int getBulkUpdateSave(BulkUpdateMsgBean newBean);	
	
//------------------bulk member Update----------------------
	
	
	public List getBulkMemberUpdateList(String query, String flag);	
	
	public int getBulkMemberUpdateSave(BulkUpdateMsgBean newBean);
	
	//--------------suggestion Emai-----------------------------
	public int getsugEmailsave(SugmailBean newBean);
	public SugmailBean getsugEmail();
//  ---------------------- Reference Book Due Days ----------	
	
	public RefDaysBean getRefDueDays();	
	public int getduedaysSave(RefDaysBean newBean);
	
	public int getBranchCode(String branch);
	
	//---------------------Due Date Update-------
	
		public int getDueDateCount(IssueMasUpdatebean newBean);
		public int getDueDateUpdate(IssueMasUpdatebean newBean);
}
