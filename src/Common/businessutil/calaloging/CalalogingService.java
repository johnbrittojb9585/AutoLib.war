package Common.businessutil.calaloging;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import Lib.Auto.Author.AuthorBean;
import Lib.Auto.Binding.BindingBean;
import Lib.Auto.Book.bookbean;
import Lib.Auto.Branch.BranchBean;
import Lib.Auto.Course.CourseBean;
import Lib.Auto.Currency.CurrencyBean;
import Lib.Auto.Department.DepartmentBean;
import Lib.Auto.EBook.EBookBean;
import Lib.Auto.Budget.BudgetBean;
import Lib.Auto.Journal.journalbean;
import Lib.Auto.City.CityBean;
import Lib.Auto.Group.GroupBean;
import Lib.Auto.Designation.DesignationBean;
import Lib.Auto.EResource.EResourceBean;
import Lib.Auto.MResource.MResourceBean;
import Lib.Auto.Keywords.KeywordsBean;
import Lib.Auto.Member.MemberBean;
import Lib.Auto.MsgMas.MsgMasBean;
import Lib.Auto.Newsclliping.NewscllipingBean;
import Lib.Auto.Payment.PaymentBean;
import Lib.Auto.Photo.PhotoBean;
import Lib.Auto.PubSup.PubSupBean;
import Lib.Auto.QBSubject.QBsubjectbean;
import Lib.Auto.QuestionBank.QuestionBankBean;
import Lib.Auto.Subject.subjectbean;
import Lib.Auto.Transfer_Books.BookTransferBean;
import Lib.Auto.EBook.EBookBean;
import Lib.Auto.EBook.EBookSearchBean;
import Lib.Auto.Suggestion.SuggestionBean;
import Lib.Auto.Review.ReviewBean;
import Lib.Auto.JournalSubscription.JournalSubscriptionbean;
import Lib.Auto.Journal_Artical.journalArtbean;
import Lib.Auto.Journal_Issues.JnlIssBean;
import Lib.Auto.Fine.Finebean;


public interface CalalogingService {
	
//---------------Currency load----------------
	
	public List getCurrencyLoad();
	public String getNewAccNoLoad(String doctype,String reportType);
//  ----------------------Author----------	
	
	public AuthorBean getAuthorMax();
	
	public AuthorBean getAuthorSearch(int code);
	
	public int getAuthorUpdate(AuthorBean authorBean);
	
	public int getAuthorInterface(int code);
	
	public int getAuthorMas(int code);

	public int getAuthorDelete(int code);

	public int getAuthorSave(AuthorBean authorBean);
	
	public int getAuthorName(AuthorBean authorBean);
	
	public int getAuthorNameAuthorInterface(int code);
	
	public int getAuthorCode(int code);
	
	public List getAuthorSearchName(AuthorBean authorBean);
	
	// ------------------ AutoComplete --------------------
	
	public ArrayList<AuthorBean> getAuthorAutoSearch(String term);
	public ArrayList<DepartmentBean> getDeptAutoSearch(String term);
	public ArrayList<PaymentBean> getUserNoSearch(String term);
	public ArrayList<subjectbean> getSubjectAutoSearch(String term);
	public ArrayList<PubSupBean> getPubSupAutoSearch(String term,String type);
	public ArrayList<CourseBean> getCourseAutoSearch(String term);
	public ArrayList<DesignationBean> getDesigAutoSearch(String term);
	public ArrayList<CurrencyBean> getCurrencyAutoSearch(String term);
	public ArrayList<KeywordsBean> getKeywordsAutoSearch(String term);
	public ArrayList<BindingBean> getBindingAutoSearch(String term);
	public ArrayList<CityBean> getCityAutoSearch(String term);
	public ArrayList<QBsubjectbean> getQBSubjectAutoSearch(String term);
	
	public ArrayList<bookbean> getBookAutoAccessNoSearch(String term,String doc);
	public ArrayList<bookbean> getBookAutoCallNoSearch(String term,String doc);
	public ArrayList<bookbean> getBookAutoTitleSearch(String term,String doc);
	public ArrayList<AuthorBean> getBookAutoAuthorSearch(String term);
	public ArrayList<PubSupBean> getBookAutoPublisherSearch(String term);
	public ArrayList<subjectbean> getBookAutoSubjectSearch(String term);
	public ArrayList<DepartmentBean> getBookAutoDeptSearch(String term);
	public ArrayList<PubSupBean> getBookAutoSupplierSearch(String term);
	public ArrayList<BudgetBean> getBookAutoBudgetSearch(String term);
	public ArrayList<KeywordsBean> getBookAutoKeywordsSearch(String term);
	public ArrayList<bookbean> getaddfield3Search(String term,String Title);
	
	//public ArrayList<bookbean> getBookBankAutoAccessNoSearch(String term);
	
	public ArrayList<MemberBean> getMemberAutoIdSearch(String term);
	public ArrayList<MemberBean> getMemberAutoNameSearch(String term);
	public ArrayList<DesignationBean> getMemberAutoDesigSearch(String term);
	public ArrayList<DepartmentBean> getMemberAutoDeptSearch(String term);
	public ArrayList<GroupBean> getMemberAutoGroupSearch(String term);
	public ArrayList<CourseBean> getMemberAutoCourseSearch(String term);
	public ArrayList<CityBean> getMemberAutoCitySearch(String term);
	
	public ArrayList<EBookBean> getEBookAutoAccessNoSearch(String term);
	public ArrayList<EBookBean> getEBookAutoCallNoSearch(String term);
	public ArrayList<EBookBean> getEBookAutoTitleSearch(String term);
	public ArrayList<AuthorBean> getEBookAutoAuthorSearch(String term);
	public ArrayList<PubSupBean> getEBookAutoPublisherSearch(String term);
	public ArrayList<PubSupBean> getEBookAutoSupplierSearch(String term);
	public ArrayList<subjectbean> getEBookAutoSubjectSearch(String term);
	public ArrayList<DepartmentBean> getEBookAutoDeptSearch(String term);
	
	public ArrayList<journalbean> getJournalAutoCodeSearch(String term);
	public ArrayList<journalbean> getJournalAutoNameSearch(String term);
	public ArrayList<PubSupBean> getJournalAutoPubSearch(String term);
	public ArrayList<subjectbean> getJournalAutoSubjectSearch(String term);
	public ArrayList<DepartmentBean> getJournalAutoDeptSearch(String term);
	
	public ArrayList<journalArtbean> getJnlAtlCodeAutoSearch(String term);
	public ArrayList<journalArtbean> getJnlAtlNameAutoSearch(String term);
	public ArrayList<journalArtbean> getJnlAtlAtlNoAutoSearch(String term);
	public ArrayList<JnlIssBean> getJnlAtlIssueAccessNoAutoSearch(String term);
	public ArrayList<journalArtbean> getJnlAtlSubjectAutoSearch(String term);
	
	public ArrayList<journalbean> getJnlSubscriptionNameAutoSearch(String term);
	public ArrayList<PubSupBean> getJnlSubscriptionSupplierAutoSearch(String term);
	
	public ArrayList<journalbean> getJnlIssuesJnlNameAutoSearch(String term);
	
	public ArrayList<Finebean> getFineCodeAutoSearch(String term);
	public ArrayList<GroupBean> getFineGroupAutoSearch(String term);
	
	public ArrayList<NewscllipingBean> getNewsPaperNameAutoSearch(String term);
	public ArrayList<NewscllipingBean> getNewsPaperTypeAutoSearch(String term);
	public ArrayList<NewscllipingBean> getNewsPaperSubjectAutoSearch(String term);
	
	public ArrayList<DepartmentBean> getStatDeptAutoSearch(String term);
	public ArrayList<subjectbean> getStatSubjectAutoSearch(String term);
	public ArrayList<PubSupBean> getStatPubAutoSearch(String term);
	public ArrayList<PubSupBean> getStatSupAutoSearch(String term);
	
	public ArrayList<QBsubjectbean> getQBankMasSubjectAutoSearch(String term);
	public ArrayList<DepartmentBean> getQBankMasDeptAutoSearch(String term);
	public ArrayList<CourseBean> getQBankMasCourseAutoSearch(String term);
	
	// ------------------ EBook --------------------
	
	public EBookBean getEBookMax();
		
		public EBookBean getEBookSearch(String accessNo);
		public int getEBookSave(EBookBean ebookBean);
		public int getEBookUpdate(EBookBean ebookBean);
		public int getEBookDelete(String accessNo);
		public String getEBookName(EBookBean ebookBean);
		public String getEBookAccessNo(String accessNo);
		public List getEBookSearchList(EBookBean ob);
		public List getEBookCallNoList(EBookBean ob);
		
	    public List getEBookSearchTitle(String name);
		//public List getEBookSearchTitle(EBookBean ob);
		public List getEBookSearchAuthor(String name);
		public List getEBookSearchPub(String name);
		public List getEBookSearchSup(String name);
		public List getEBookSearchSubject(String name);
		public List getEBookSearchCallNo(String name);
		public List getEBookSearchDept(String name);
		
	//  ----------------Suggestion Master-------------
		public SuggestionBean getSuggestionMax();
		public int getSuggestionSave(SuggestionBean sugBean);
		public SuggestionBean getSuggestionSearch(int sugNo);
		public int getSuggestionUpdate(SuggestionBean sugBean);
		public int getSuggestionDelete(int sugNo);
		public int getSuggestionCount(int sugCount);
		public int getSuggestionReply(SuggestionBean sugBean);
		
	//  -----------------Review Master------------------
		public ReviewBean getReviewMax();
		public int getReviewSave(ReviewBean reviewBean);
		public ReviewBean getReviewSearch(int reviewNo);
		public int getReviewUpdate(ReviewBean reviewBean);
		public int getReviewDelete(int reviewNo);
		public List getReviewDisplay(String accessNo);

	
//  ----------------------Sublect----------
	
	public subjectbean getSubjectMax();
	
	public subjectbean getSubjectSearch(int code);
	
	public int getSubjectUpdate(subjectbean newBean);
	
	public int getSubjectInterface(int code);
	
	public int getSubjectMas(int code);
	
	public int getSubjectDelete(int code);
	
	public int getSubjectName(subjectbean newBean);
	
	public int getSubjectSave(subjectbean newBean);
	
	public List getSubjectSearchName(subjectbean newBean);
	
//--------------------Department--------------------	
	
	public DepartmentBean getDeptMax();
	
	public DepartmentBean getDeptSearch(int code);
	
	public int getDeptInterface(int code);
	
	public int getDeptMas(int code);
	
	public int getDeptDelete(int code);
	
	public int getDeptUpdate(DepartmentBean newBean);
	
	public int getDeptName(DepartmentBean newBean);
	
	public int getDeptSave(DepartmentBean newBean);
	
	public List getDeptSearchName(DepartmentBean newBean);
	
	
	//--------------------City--------------------	
	
	public CityBean getCityMax();
	
	public CityBean getCitySearch(int code);
	
	public int getCityInterface(int code);
	
	public int getCityMas(int code);
	
	public int getCityDelete(int code);
	
	public int getCityUpdate(CityBean newBean);
	
	public int getCityName(CityBean newBean);
	
	public int getCitySave(CityBean newBean);
	
	public List getCitySearchName(String newBean);
	
	
//	--------------------Member--------------------
	
	public MemberBean getMemberSearch(String code);
	
	public int getMemberIdChange(String code,String changeCode);
	
	public List getMemberMasSearch(MemberBean newbean);
	
	public List getDeptMasSearch(MemberBean newbean);
	
	public List getDesignationSearch(MemberBean newbean);
	
	public List getGroupSearch(MemberBean newbean);
	
	public List getStaffSearch(MemberBean newbean);
	
	public List getCourseSearch(MemberBean newbean);
	
	public List getCitySearch(MemberBean newbean);
	
	public int getIssueMasCheck(String code);
	
	public int getMemberDelete(String code);
	
	public MemberBean getMemberClear(String code);
	
	public int getMemberUpdate(MemberBean newbean);
	
	public int getMemberCheck(String code);
	
	public int getMemberSave(MemberBean newbean);
	
	public int getMemberPhotoSave(PhotoBean bean);
	
	public int getBulkPhotoMemberCode(String code,int flag);
	public int getBulkPhotoUploadDelete();
	
//	--------------------BOOK--------------------
	public bookbean getBookSearch(String accno,String doctype);
	
	public String getIssueCheck(String accno);
	
	public String getIssueHistoryCheck(String accno);
	
	public String getReserveMasCheck(String accno);
	
	public int getDeleteBook(String accno);
	
	public int getDeleteHistory(String accno);
	
	public int getDeleteReserve(String accno);
	
	public List getBookSearchTitle(String name,String doctype);
	
	public List getBookSearchTitleauth(String name,String auth,String doctype);
	
	public List getBookSearchAuthor(String name);
	
	public List getBookSearchPub(String name);
	
	public List getBookSearchSubject(String name);
	
	public List getBookSearchCallNo(String name);
	
	public List getBookSearchBranch(String name);
	
	public List getBookSearchDept(String name);
	
	public List getBookSearchBudget(String name);
	
	public List getBookSearchKey(String name);
	
	public List getBookSearchSupplier(String name);
	
	public int getBookSubCode(String subName);
	
	public int getBookBranchCode(String branchName);
	
	public int getBookDeptCode(String deptName);
	
	public int getBookSupplierCode(String supName);
	
	public int getBookPubCode(String pubName);
	
	public int getBookSave(bookbean newbean);
	
	public String getBookSaveInterfaceCheck(bookbean newbean);
	
	public int getBookUpdate(bookbean newbean);
	//Transfer / Re transfer Book
	
		public List getAccNoList(BookTransferBean newbean);
		public List getTransAccNoList(BookTransferBean newbean);
	
	
//	--------------------Course--------------------
	public CourseBean getCourseMax();
	
	public CourseBean getCourseSearch(int code);
	
	public int getCourseUpdate(CourseBean newBean);
	
    public int getCourseInterface(int code);
	
	public int getCourseMas(int code);
	
	public int getCourseDelete(int code);
	
	public int getCourseName(CourseBean newBean);	
	
	public int getCourseSave(CourseBean newBean);
	
//	--------------------Designation--------------------	
	
	public DesignationBean getDesignationMax();
	
	public DesignationBean getDesignationSearch(int code);
	
	public int getDesigInterface(int code);
	
	public int getDesigMas(int code);
	
	public int getDesigDelete(int code);
	
	public int getDesigName(DesignationBean newBean);
	
	public int getDesigSave(DesignationBean newBean);
	
	public int getDesigUpdate(DesignationBean newBean);
	
	public List getDesigSearchName(DesignationBean newBean);
	
//	--------------------PubSup--------------------	
	
	public PubSupBean getPubSupMax();
	
	public PubSupBean getPubSupSearch(int code);
	
    public int getPubSupInterface(int code);
	
	public int getPubSupMas(int code);
	
	public int getPubSupDelete(int code);
	
	public int getPubSupName(PubSupBean newBean);
	
	public int getPubSupSave(PubSupBean newBean);
	
	public int getPubSupUpdate(PubSupBean newBean);
	
	public List getSupSearchName(PubSupBean newBean);
	
	public List getPubSearchName(PubSupBean newBean);
	
	
//	--------------------Branch--------------------
	public BranchBean getBranchMax();
	
	public BranchBean getBranchSearch(int code);
	
    public int getBranchInterface(int code);
	
	public int getBranchMas(int code);
	
	public int getBranchDelete(int code);
	
	public int getBranchName(BranchBean newBean);
	
	public int getBranchSave(BranchBean newBean);
	
	public int getBranchUpdate(BranchBean newBean);
	
	public List getBranchSearchName(String name);
	
//	--------------------Currency--------------------
	
	public CurrencyBean getCurrencyMax();
	
	public CurrencyBean getCurrencySearch(int code);
	
    public int getCurrencyInterface(int code);
	
	public int getCurrencyMas(int code);
	
	public int getCurrencyDelete(int code);
	
	public int getCurrencyName(CurrencyBean newBean);
	
    public int getCurrencySave(CurrencyBean newBean);
	
	public int getCurrencyUpdate(CurrencyBean newBean);
	
	public List getCurrencySearchName(CurrencyBean newBean);
	
//	--------------------Keywords--------------------
	
	public KeywordsBean getKeywordsMax();
	
	public KeywordsBean getKeywordsSearch(int code);
	
	public int getKeywordsDelete(int code);
	
	public int getKeywordsName(KeywordsBean newBean);
	
	public int getKeywordsMas(int code);
	
    public int getKeywordsSave(KeywordsBean newBean);
	
	public int getKeywordsUpdate(KeywordsBean newBean);
	
	public List getKeywordsSearchName(KeywordsBean newBean);
	
	//---------------Report-----------------------------
	
	//public byte[] getJasperReportToPdfDisply(JasperReport jasperReport,Map parameters);
	
	
	
	//------------Binding------------------------------
	
	public BindingBean getBindingMax();
	
	public BindingBean getBindingSearch(int code);
	
	public int getBindingDelete(BindingBean codebean);
	
	public int getBindingName(String Name);
	
	public int getBindingUpdate(BindingBean codebean);
	
	public int getBindingSave(BindingBean codebean);
	
	public List getBinderSearchName(String name);
	
	
	
	//--------------NewsCllipings----------------------------
	
	
     public NewscllipingBean getNewsMax();
	
	 public NewscllipingBean getNewsSearch(int code);
	
	 public int getNewsCliMas(int code);
	 
	 public int getNewClipSave(NewscllipingBean codebean);
	 
	 public int getNewsClipDelete(int code);
	
	//public int getBindingName(String Name);
	
	 public int getNewsClipUpdate(NewscllipingBean codebean);
	
	//public int getBindingSave(BindingBean codebean);
	
	//public List getBinderSearchName(String name);*/
	
	
//	--------------EResource----------------------------
	
	
	public EResourceBean getEResourceMax();
	
	public int getEResourseMas(int code);
	
	public int getEResourseSave(EResourceBean codebean);
	 
	 public EResourceBean getEResourseSearch(int code);
	 
	 public int getEResourceUpdate(EResourceBean codebean);
	 
	 public int getEResourceDelete(int code);


//--------------MResource----------------------------

public String getMResourseMas(MResourceBean newbean);

public int getMResourseSave(MResourceBean codebean);

public MResourceBean getMissingBook(String accno);

public int getMBookDelete(String accno);

//--------------Contents Upload----------------------------

public boolean getCheckContentsNumber(String accno,String document);
public void getUpdateContentFile(String accno,String document,String fileName);



//--------------------------------------QuestionBankSubject Mas--------------------------

public QBsubjectbean getQBSubjectMax();

public int getQBSubjectSave(QBsubjectbean newBean);

public int getQBSubjectName(QBsubjectbean newBean);

public int getQBSubjectMas(int code);

public int getQBSubjectInterface(int code);

public QBsubjectbean getQBSubjectSearch(int code);

public int getQBSubjectUpdate(QBsubjectbean newBean);

public int getQBSubjectDelete(int code);

public List getQBSubjectSearchName(QBsubjectbean newBean);


//--------------Question Bank ----------------------------


public QuestionBankBean getQuestionBankMax();
public int getQuestionBankUpdate(QuestionBankBean codebean);
public QuestionBankBean getQuestionBankSearch(int code);    
public int getQuestionBankMas(int code);	 
public int getQuestionBankSave(QuestionBankBean codebean);	 
public int getQuestionBankDelete(int code);



//-----------------message master--------------------
public MsgMasBean getMsgMasMax();
public int getMsgMas(int code);
public int getMsgMasSave(MsgMasBean newBean);
public MsgMasBean getMsgMasSearch(int code);
public int getMsgMasDelete(int code);
public int getMsgMasUpdate(MsgMasBean newBean);
public List getMsgMasSearchName(String newBean);
public Map<String, Object> getDashboardGraph();


}
