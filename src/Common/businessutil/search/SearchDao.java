package Common.businessutil.search;

import Lib.Auto.Account.AccountBean;
import Lib.Auto.NewArrivals.NewArrivalsBean;
import Lib.Auto.QueryBuilder.QueryBuilderBean;
import Lib.Auto.Simples.Searchbean;
import Lib.Auto.EBookSearch.EBookSearchBean;
import Lib.Auto.JournalBrowse.JournalSearchbean;
import Lib.Auto.Newsclliping.NewscllipingBean;
import Lib.Auto.Journal_ArticleSearch.JournalAtlSearchbean;

import java.util.ArrayList;
import java.util.List;

public interface SearchDao {
	
	
	public List findSimpleSearch(String search,String document);
	public List findAdvancedSearch(String search,String document,String order);
	public List findFullView(String search,String document);
	public List findFullViewSearch(String search);
	public List findBrowseSearch(String search,String document);
	public List findLoadBranchList();
	
//	 ----------------- Journal Search ---------------
	public List findJournalSearch(String search);
	public List findJournalIssueSearch(String search);	
	public List findJournalFullView(String search);	
	public JournalSearchbean findJournalFullViewDetails(String sQL_Query);	
//	 ----------------- AutoComplete ---------------	
	
	public ArrayList<Searchbean> findTitleSearch(String term);
	public ArrayList<Searchbean> findAuthorSearch(String term);
	public ArrayList<Searchbean> findSubjectSearch(String term);
	public ArrayList<Searchbean> findAdvAutoSearch(String term,String flag);
	public ArrayList<Searchbean> findQueryAutoSearch(String term,String flag);
	public ArrayList<Searchbean> findQuickAutoSearch(String term,String flag);
	public ArrayList<JournalSearchbean> findJournalAutoSearch(String term);
	
	public ArrayList<EBookSearchBean> findEBookAutoTitleSearch(String term);
	public ArrayList<EBookSearchBean> findEBookAutoAuthorSearch(String term);
	public ArrayList<EBookSearchBean> findEBookAutoSubjectSearch(String term);

	public ArrayList<JournalAtlSearchbean> findJournalArticlesNameAutoSearch(String term);
	public ArrayList<JournalAtlSearchbean> findJournalArticlesTitleAutoSearch(String term);
	public ArrayList<JournalAtlSearchbean> findJournalArticlesAuthorAutoSearch(String term);
	public ArrayList<JournalAtlSearchbean> findJournalArticlesSubjectAutoSearch(String term);
	public ArrayList<JournalAtlSearchbean> findJournalArticlesAbstractAutoSearch(String term);
	public ArrayList<JournalAtlSearchbean> findJournalArticlesKeywordsAutoSearch(String term);

	public ArrayList<NewscllipingBean> findNewsclippingNameAutoSearch(String term);
	public ArrayList<NewscllipingBean> findNewsclippingTypeAutoSearch(String term);
	public ArrayList<NewscllipingBean> findNewsclippingTitleAutoSearch(String term);
	public ArrayList<NewscllipingBean> findNewsclippingSubjectAutoSearch(String term);
	public ArrayList<NewscllipingBean> findNewsclippingKeywordsAutoSearch(String term);

	
	
// -------------------EBook Search-------------------
	public List findEBookSearch(String search);	
	
//-------------------Account------------------	
	public AccountBean findCheckAccount(String uid,String pwd);
	public List findAccountDetails(String search);
	public List findAccountTransDetails(String search);
	public List findAccountDetailsIssue(String search);
	
	
	public List findAccountpaymentDetails(String search);
	public List findAccountSugDetails(String search);
	
	public String findOnlineRenewSave(AccountBean newbean);
	public List findAccountDetailsReserve(String search);
	public String findOnlineReserveCancel(AccountBean newbean);
	public String findChangePwd(AccountBean newbean);
	public List findRegisterLoad();
	public List findTodayRegisterLoad();
	public int findRegisterAllLogout();
	public AccountBean findRegisterEntry(String search);
	public List findIssueDetails(String code,String doc);
	public AccountBean findReserveCheck(AccountBean newbean);
	
	//------------------------------------------------
	
	public List findNewsClipSimpleSearch(String search);
	
	public List findEResourceSimpleSearch(String search);
	
	public List findJournalArticleSearch(String search);
	
	// Query Builder

		public List getQueryBuilderSearch(QueryBuilderBean newBean);
		
//		 Question Bank Search
		
		public List getQBSearch(String search);	
		
		public List findFullViewQBSearch(String search);
	
		//-----------------------NewArrivals-------------------
		
		
		public List findMonthYearList(NewArrivalsBean bean);
		public List findSubjectList();
		public List findNewArrivalSearchResult(NewArrivalsBean newbean);
}