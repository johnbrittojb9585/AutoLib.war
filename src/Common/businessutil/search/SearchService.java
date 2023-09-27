package Common.businessutil.search;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import Lib.Auto.Simples.Searchbean;
import Lib.Auto.EBookSearch.EBookSearchBean;
import Lib.Auto.Account.AccountBean;
import Lib.Auto.NewArrivals.NewArrivalsBean;
import Lib.Auto.QueryBuilder.QueryBuilderBean;
import Lib.Auto.JournalBrowse.JournalSearchbean;
import Lib.Auto.Newsclliping.NewscllipingBean;
import Lib.Auto.Journal_ArticleSearch.JournalAtlSearchbean;


public interface SearchService {
	
	public List getSimpleSearch(String search,String document,String format);
	public List getAdvancedSearch(String search,String document,String format,String order);
	public List getFullView(String search,String document);
	public List getFullViewSearch(String search);
	public List getBrowseSearch(String search,String document,String format);
	public List getLoadBranchList();
	
//	----------------Journal Search-----------------------	
	public List getJournalSearch(String search);	
	public List getJournalIssueSearch(String search);
	public List getJournalFullView(String search);
	public JournalSearchbean getJournalFullViewDetails(String sQL_Query);	
//	----------------AutoComplete-----------------------	
	
	public ArrayList<Searchbean> getTitleSearch(String term);
	public ArrayList<Searchbean> getAuthorSearch(String term);
	public ArrayList<Searchbean> getSubjectSearch(String term);
	public ArrayList<Searchbean> getAdvAutoSearch(String term,String flag);
	public ArrayList<Searchbean> getQueryAutoSearch(String term,String flag);
	public ArrayList<Searchbean> getQuickAutoSearch(String term,String flag);
	public ArrayList<JournalSearchbean> getJournalAutoSearch(String term);
	
	public ArrayList<EBookSearchBean> getEBookAutoTitleSearch(String term);
	public ArrayList<EBookSearchBean> getEBookAutoAuthorSearch(String term);
	public ArrayList<EBookSearchBean> getEBookAutoSubjectSearch(String term);

	public ArrayList<NewscllipingBean> getNewsclippingNameAutoSearch(String term);
	public ArrayList<NewscllipingBean> getNewsclippingTypeAutoSearch(String term);
	public ArrayList<NewscllipingBean> getNewsclippingTitleAutoSearch(String term);
	public ArrayList<NewscllipingBean> getNewsclippingSubjectAutoSearch(String term);
	public ArrayList<NewscllipingBean> getNewsclippingKeywordsAutoSearch(String term);
	
	public ArrayList<JournalAtlSearchbean> getJournalArticlesNameAutoSearch(String term);
	public ArrayList<JournalAtlSearchbean> getJournalArticlesTitleAutoSearch(String term);
	public ArrayList<JournalAtlSearchbean> getJournalArticlesAuthorAutoSearch(String term);
	public ArrayList<JournalAtlSearchbean> getJournalArticlesSubjectAutoSearch(String term);
	public ArrayList<JournalAtlSearchbean> getJournalArticlesAbstractAutoSearch(String term);
	public ArrayList<JournalAtlSearchbean> getJournalArticlesKeywordsAutoSearch(String term);

	
	
	//----------------EBook Search-----------------------
	
		public List getEBookSearch(String search);	
	
	
//----------------Account-----------------------
	public AccountBean getCheckAccount(String uid,String pwd);
	public List getAccountDetails(String search);
	public List getAccountTransDetails(String search);
	public List getAccountDetailsIssue(String search);
	
	public List getAccountSugDetails(String search);
	
	public List getAccountPaidDetails(String search);
	
	public String getOnlineRenewSave(AccountBean newbean);
	public List getAccountDetailsReserve(String search);
	public String getOnlineReserveCancel(AccountBean newbean);
	public String getChangePwd(AccountBean newbean);
	public List getRegisterLoad();
	public List getTodayRegisterLoad();
	
	public int getRegisterAllLogout();	
	public AccountBean getRegisterEntry(String search);
	public List getIssueDetails(String code,String doc);
	public AccountBean getReserveCheck(AccountBean newbean);
	

	//-------------------------------------------------
	
	public List getNewsClipSimpleSearch(String search);
	
	public List getEResourceSimpleSearch(String search);
	
	public List getJournalArticleSearch(String search);
	
	//---------------- Query Builder ---------------
	
	public List getQueryBuilderSearch(QueryBuilderBean bean);
	
//	 Question Bank Search
	
	public List getQBSearch(String search);
	
	public List getFullViewQBSearch(String search);
	
	//--------------------New Arrivals----------------------------
	
	
		public List getMonthYearList(NewArrivalsBean bean);
		public List getSubjectList();
		public List getNewArrivalSearchResult(NewArrivalsBean newbean);
		
		
		
}