
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
import Lib.Auto.Journal.journalbean;
import Lib.Auto.City.CityBean;
import Lib.Auto.Group.GroupBean;
import Lib.Auto.Designation.DesignationBean;
import Lib.Auto.EResource.EResourceBean;
import Lib.Auto.MResource.MResourceBean;
import Lib.Auto.Keywords.KeywordsBean;
import Lib.Auto.Budget.BudgetBean;
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


public interface CalalogingDao {

//--------Currency Load-----------	
	public List findCurrencyLoad();
	public String findNewAccNoLoad(String doctype,String reportType);
//--------Author-----------	
	public AuthorBean findAuthorMax();
	public AuthorBean findAuthorSearch(int code);
	public int authorUpdate(AuthorBean authorBean);
	public int findAuthorInterface(int code);
	public int findAuthorMas(int code);
	public int authorDelete(int code);
	public int authorSave(AuthorBean authorBean);
	public int findAuthorName(AuthorBean authorBean);
	public int findAuthorNameAuthorInterface(int code);
	public int findAuthorcode(int code);
	public List findAuthorMasName(AuthorBean authorBean);
	
//----------------AutoComplete---------------
	
	public ArrayList<AuthorBean> findAuthorAutoSearch(String term);
	public ArrayList<DepartmentBean> findDeptAutoSearch(String term);
	public ArrayList<PaymentBean> findUserNoSearch(String term);
	public ArrayList<subjectbean> findSubjectAutoSearch(String term);
	public ArrayList<PubSupBean> findPubSupAutoSearch(String term,String type);
	public ArrayList<CourseBean> findCourseAutoSearch(String term);
	public ArrayList<DesignationBean> findDesigAutoSearch(String term);
	public ArrayList<CurrencyBean> findCurrencyAutoSearch(String term);
	public ArrayList<KeywordsBean> findKeywordsAutoSearch(String term);
	public ArrayList<BindingBean> findBindingAutoSearch(String term);
	public ArrayList<CityBean> findCityAutoSearch(String term);
	public ArrayList<QBsubjectbean> findQBSubjectAutoSearch(String term);
	
	public ArrayList<bookbean> findBookAutoAccessNoSearch(String term,String doc);
	public ArrayList<bookbean> findBookAutoCallNoSearch(String term,String doc);
	public ArrayList<bookbean> findBookAutoTitleSearch(String term,String doc);
	public ArrayList<AuthorBean> findBookAutoAuthorSearch(String term);
	public ArrayList<PubSupBean> findBookAutoPublisherSearch(String term);
	public ArrayList<subjectbean> findBookAutoSubjectSearch(String term);
	public ArrayList<DepartmentBean> findBookAutoDeptSearch(String term);
	public ArrayList<PubSupBean> findBookAutoSupplierSearch(String term);
	public ArrayList<BudgetBean> findBookAutoBudgetSearch(String term);
	public ArrayList<KeywordsBean> findBookAutoKeywordsSearch(String term);
	public ArrayList<bookbean> findAddfield3Search(String term,String Title);
	//public ArrayList<bookbean> findBookBankAutoAccessNoSearch(String term);
	
	public ArrayList<MemberBean> findMemberAutoIdSearch(String term);
	public ArrayList<MemberBean> findMemberAutoNameSearch(String term);
	public ArrayList<DesignationBean> findMemberAutoDesigSearch(String term);
	public ArrayList<DepartmentBean> findMemberAutoDeptSearch(String term);
	public ArrayList<GroupBean> findMemberAutoGroupSearch(String term);
	public ArrayList<CourseBean> findMemberAutoCourseSearch(String term);
	public ArrayList<CityBean> findMemberAutoCitySearch(String term);
	
	public ArrayList<EBookBean> findEBookAutoAccessNoSearch(String term);
	public ArrayList<EBookBean> findEBookAutoCallNoSearch(String term);
	public ArrayList<EBookBean> findEBookAutoTitleSearch(String term);
	public ArrayList<AuthorBean> findEBookAutoAuthorSearch(String term);
	public ArrayList<PubSupBean> findEBookAutoPublisherSearch(String term);
	public ArrayList<PubSupBean> findEBookAutoSupplierSearch(String term);
	public ArrayList<subjectbean> findEBookAutoSubjectSearch(String term);
	public ArrayList<DepartmentBean> findEBookAutoDeptSearch(String term);

	public ArrayList<journalbean> findJournalAutoCodeSearch(String term);
	public ArrayList<journalbean> findJournalAutoNameSearch(String term);
	public ArrayList<PubSupBean> findJournalAutoPubSearch(String term);
	public ArrayList<subjectbean> findJournalAutoSubjectSearch(String term);
	public ArrayList<DepartmentBean> findJournalAutoDeptSearch(String term);
	
	public ArrayList<journalArtbean> findJnlAtlCodeAutoSearch(String term);
	public ArrayList<journalArtbean> findJnlAtlNameAutoSearch(String term);
	public ArrayList<journalArtbean> findJnlAtlAtlNoAutoSearch(String term);
	public ArrayList<JnlIssBean> findJnlAtlIssueAccessNoAutoSearch(String term);
	public ArrayList<journalArtbean> findJnlAtlSubjectAutoSearch(String term);

	public ArrayList<journalbean> findJnlSubscriptionNameAutoSearch(String term);
	public ArrayList<PubSupBean> findJnlSubscriptionSupplierAutoSearch(String term);
	
	public ArrayList<journalbean> findJnlIssuesJnlNameAutoSearch(String term);
	
	public ArrayList<NewscllipingBean> findNewsPaperNameAutoSearch(String term);
	public ArrayList<NewscllipingBean> findNewsPaperTypeAutoSearch(String term);
	public ArrayList<NewscllipingBean> findNewsPaperSubjectAutoSearch(String term);
		
	public ArrayList<DepartmentBean> findStatDeptAutoSearch(String term);
	public ArrayList<subjectbean> findStatSubjectAutoSearch(String term);
	public ArrayList<PubSupBean> findStatPubAutoSearch(String term);
	public ArrayList<PubSupBean> findStatSupAutoSearch(String term);
	
	public ArrayList<QBsubjectbean> findQBankMasSubjectAutoSearch(String term);
	public ArrayList<DepartmentBean> findQBankMasDeptAutoSearch(String term);
	public ArrayList<CourseBean> findQBankMasCourseAutoSearch(String term);
	
	public ArrayList<Finebean> findFineCodeAutoSearch(String term);
	public ArrayList<GroupBean> findFineGroupAutoSearch(String term);

	
	//---------------EBook-----------------------
	
		public int ebookSave(EBookBean ebookBean);
		public EBookBean findEBookMax();
		public int ebookDelete(String accessNo);
		public EBookBean findEBookSearch(String accessNo);
		public int ebookUpdate(EBookBean ebookBean);
		public String findEBookName(EBookBean ebookBean);
		public String findEBookAccessNo(String accessNo);
		public List findEBookSearchList(EBookBean ob);
		public List findEBookCallNoList(EBookBean ob);
		
		public List findEBookSearchTitle(String name);
		//public List findEBookSearchTitle(EBookBean ob);
		public List findEBookSearchAuthor(String name);
		public List findEBookSearchPub(String name);
		public List findEBookSearchSup(String name);
		public List findEBookSearchSubject(String name);
		public List findEBookSearchCallNo(String name);
		public List findEBookSearchDept(String name);
		
	//--------------Suggestion Master-------------------
		public SuggestionBean findSuggestionMax();
		public int suggestionSave(SuggestionBean sugBean);
		public SuggestionBean findSuggestionSearch(int sugNo);
		public int suggestionUpdate(SuggestionBean sugBean);
		public int suggestionReply(SuggestionBean sugBean);
		public int suggestionDelete(int sugNo);
		public int findSuggestionCount(int sugCount);

		//--------------Review Master-------------------
			public ReviewBean findReviewMax();
			public int reviewSave(ReviewBean reviewBean);
			public ReviewBean findReviewSearch(int reviewNo);
			public int reviewUpdate(ReviewBean reviewBean);
			public int reviewDelete(int reviewNo);
			public List findReviewDisplay(String accessNo);
	
	
//-------Subjct---------------------------	
	
	public subjectbean findSubjectMax();
	public subjectbean findSubjectSearch(int code);
	public int subjectUpdate(subjectbean newBean);
	public int findSubjectInterface(int code);
	public int findSubjectMas(int code);
	public int subjectDelete(int code);
	public int findSubjectName(subjectbean newBean);
	public int subjectSave(subjectbean newBean);
	public List findSubjectMasName(subjectbean newBean);
	
//---------Department------------------------	
	public DepartmentBean findDeptMax();
	public DepartmentBean findDeptSearch(int code);
	public int findDeptInterface(int code);
	public int findDeptMas(int code);
	public int deptDelete(int code);
	public int deptUpdate(DepartmentBean newBean);
	public int findDeptName(DepartmentBean newBean);
	public int deptSave(DepartmentBean newBean);
	public List findDeptSearch(DepartmentBean newBean);
	
		
//---------City------------------------	
	public CityBean findCityMax();
	public CityBean findCitySearch(int code);
	public int findCityInterface(int code);
	public int findCityMas(int code);
	public int CityDelete(int code);
	public int CityUpdate(CityBean newBean);
	public int findCityName(CityBean newBean);
	public int CitySave(CityBean newBean);
	public List findCitySearch(String newBean);
	
//	--------------------Member--------------------
	
	public MemberBean findMemberSearch(String code);
	public int findMemberIdChange(String code,String changeCode);
	public List findMemberMasSearch(MemberBean newbean);
	public List findDeptMasSearch(MemberBean newbean);
	public List findDesignationSearch(MemberBean newbean);
	public List findGroupSearch(MemberBean newbean);
	public List findStaffSearch(MemberBean newbean);
	public List findCourseSearch(MemberBean newbean);
	public List findCitySearch(MemberBean newbean);
	public int findIssueMasCheck(String code);
	public int memberDelete(String code);
	public int memberUpdate(MemberBean newbean);
	public int findMemberCheck(String code);
	public int memberSave(MemberBean newbean);	
	public int MemberPhotoSave(PhotoBean bean);
	public int findBulkPhotoMemberCode(String code,int flag);
	public MemberBean findMemberClear(String code);
	public int findBulkPhotoUploadDelete();
	
//	--------------------Book--------------------
	public bookbean findBookSearch(String accno,String doctype);	
	public String findIssueCheck(String accno);	
	public String findIssueHistoryCheck(String accno);
	public String findReserveMasCheck(String accno);
	public int findDeleteBook(String accno);
	public int findDeleteHistory(String accno);
	public int findDeleteReserve(String accno);
	public List findBookSearchTitle(String name,String doctype);
	public List findBookSearchTitleauth(String name,String auth,String doctype);
	public List findBookSearchAuthor(String name);
	public List findBookSearchPub(String name);
	public List findBookSearchSubject(String name);
	public List findBookSearchCallNo(String name);
	public List findBookSearchBranch(String name);
	public List findBookSearchDept(String name);
	public List findBookSearchBudget(String name);
	public List findBookSearchKey(String name);
	public List findBookSearchSupplier(String name);
	public int findBookSubCode(String subName);
	public int findBookBranchCode(String branchName);
	public int findBookDeptCode(String deptName);
	public int findBookSupplierCode(String supName);
	public int findBookPubCode(String pubName);
	public int findBookSave(bookbean newbean);
	public String findBookSaveInterfaceCheck(bookbean newbean);
	public int findBookUpdate(bookbean newbean);
	//Transfer / Re transfer Book
	
		public List findAccNoList(BookTransferBean newbean);
		public List findTransAccNoList(BookTransferBean newbean);
//	--------------------Course--------------------
	
	public CourseBean findCourseMax();
	
	public CourseBean findCourseSearch(int code);
	
	public int CourseUpdate(CourseBean newBean);
	
	public int findCourseInterface(int code);
	
	public int findCourseMas(int code);
	
	public int courseDelete(int code);
	
	public int courseName(CourseBean newBean);
	
	public int courseSave(CourseBean newBean);
//	--------------------Designation--------------------	
	
	public DesignationBean findDesignationMax();
	
	public DesignationBean findDesignationSearch(int code);
	
    public int findDesigInterface(int code);
	
	public int findDesigMas(int code);
	
	public int desigDelete(int code);
	
	public int desigName(DesignationBean newBean);
	
	public int desigSave(DesignationBean newBean);
	
	public int desigUpdate(DesignationBean newBean);
	
	public List findDesigSearch(DesignationBean newBean);
	
//	--------------------PubSup--------------------
	public PubSupBean findPubSupMax();
	
	public PubSupBean findPubSupSearch(int code);
	
   public int findPubSupInterface(int code);
	
	public int findPubSupMas(int code);
	
	public int pubSupDelete(int code);
	
	public int pubSupName(PubSupBean newBean);
	
	public int pubSupSave(PubSupBean newBean);
	
	public int pubSupUpdate(PubSupBean newBean);
	
	public List findSupSearch(PubSupBean newBean);
	
	public List findPubSearch(PubSupBean newBean);
	
//	--------------------Branch--------------------
	public BranchBean findBranchMax();
	
	public BranchBean findBranchSearch(int code);
	
   public int findBranchInterface(int code);
	
	public int findBranchMas(int code);
	
	public int branchDelete(int code);
	
	public int branchName(BranchBean newBean);
	
	public int branchSave(BranchBean newBean);
	
	public int branchUpdate(BranchBean newBean);
	
	public List findBranchSearchName(String name);
	
//	--------------------Currency--------------------	
	public CurrencyBean findCurrencyMax();
	
	public CurrencyBean findCurrencySearch(int code);
	
    public int findCurrencyInterface(int code);
	
	public int findCurrencyMas(int code);
	
	public int currencyDelete(int code);
	
	public int currencyName(CurrencyBean newBean);	
	
    public int currencySave(CurrencyBean newBean);
	
	public int currencyUpdate(CurrencyBean newBean);
	
	public List CurrencySearch(CurrencyBean newBean);
	
//	--------------------Keywords--------------------	
	public KeywordsBean findkeywordsMax();
	public KeywordsBean findKeywordsSearch(int code);
	public int keywordsDelete(int code);
	
	public int keywordsName(KeywordsBean newBean);
	public int findKeywordsMas(int code);
    public int keywordsSave(KeywordsBean newBean);
	
	public int keywordsUpdate(KeywordsBean newBean);
	public List keywordsSearchName(KeywordsBean newBean);
	
	//----------------Report-----------------
	//public byte[] getJasperReportToPdfDisplyDao(JasperReport jasperReport,Map parameters);
	
	
	
	//-----------------Binding--------------------
	
	public BindingBean findBindingMax();
	
	public BindingBean findBindingSearch(int code);
	
	public int BindingDelete(BindingBean codebean);
	
	public int findBindingName(String Name);
	
	public int findBindingUpdate(BindingBean codebean);
	
	public int fineBindingSave(BindingBean codebean);
	
	public List findBinderSearchName(String name);
	
	
	//-----------------NewsCllipings------------
	
	public NewscllipingBean findNewsMax();
	
	public NewscllipingBean findNewsSearch(int code);
	
	public int findNewscliSearch(int code);
	
	public int fineNewClipSave(NewscllipingBean codebean);
	
	public int findNewsClipUpdate(NewscllipingBean codebean);
	
	public int NewsClipDelete(int code);
	
//	-----------------EResourse------------
	
	public EResourceBean findEResourceMax();
	
	public int findEResourseMas(int code);
	
	public int fineEResourseSave(EResourceBean codebean);
	
	public EResourceBean findEResourseSearch(int code);
	
	public int findEResourceUpdate(EResourceBean codebean);
	
	public int EResourceDelete(int code);
	
//	-----------------MResourse------------
	
	public String findMResourseMas(MResourceBean newbean);
	
	public int fineMResourseSave(MResourceBean codebean);
	
public MResourceBean findMissingBook(String accno);
	
	public int findMBookDelete(String accno);
	

//	--------------Contents Upload----------------------------
	
	public boolean findCheckContentsNumber(String accno,String document);	
	public void findUpdateContentFile(String accno,String document,String fileName);	
	
	
//--------------------------------------QuestionBankSubject Mas--------------------------
	
	public QBsubjectbean findQBSubjectMax();
	public int QBsubjectSave(QBsubjectbean newBean);
	public int findQBSubjectName(QBsubjectbean newBean);
	public int findQBSubjectMas(int code);
	public int findQBSubjectInterface(int code);
	public QBsubjectbean findQBSubjectSearch(int code);
	public int QBsubjectUpdate(QBsubjectbean newBean);
	public int QBsubjectDelete(int code);
	public List findQBSubjectMasName(QBsubjectbean newBean);
	
	
//  --------------Question Bank ----------------------------
	
	public QuestionBankBean getQuestionBankMax();	
	public int getQuestionBankUpdate(QuestionBankBean codebean);	
	public QuestionBankBean getQuestionBankSearch(int code);
    public int getQuestionBankMas(int code);	 
	public int getQuestionBankSave(QuestionBankBean codebean);	 
	public int getQuestionBankDelete(int code);
	
	
	//--------------------message mas-----------------------
	
	public MsgMasBean findMsgMasMax();
	public int findMsgMas(int code);
	public int MsgMasSave(MsgMasBean newBean);
	public MsgMasBean findMsgMasSearch(int code);
	public int MsgMasDelete(int code);
	public int MsgMasUpdate(MsgMasBean newBean);
	public List findMsgMasSearchName(String newBean);
	public Map<String, Object> findDashboardGraph();
	
	
	
}
