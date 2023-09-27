package Common.businessutil.search;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import Common.Security;
import Lib.Auto.Account.AccountBean;
import Lib.Auto.Account.RegisterBean;
import Lib.Auto.Advanced.Adsearchbean;
import Lib.Auto.EResourceSearch.EResourceSearchbean;
import Lib.Auto.JournalBrowse.JournalSearchbean;
import Lib.Auto.Journal_ArticleSearch.JournalAtlSearchbean;
import Lib.Auto.NewArrivals.NewArrivalsBean;
import Lib.Auto.NewsClipSearch.NewsSearchbean;
import Lib.Auto.QBSearch.QuestionSearchBean;
import Lib.Auto.QueryBuilder.QueryBuilderBean;
import Lib.Auto.Report.reportbean;
import Lib.Auto.JournalBrowse.JournalSearchbean;
import Lib.Auto.Simples.Searchbean;
import Lib.Auto.Journal_ArticleSearch.JournalAtlSearchbean;
import Lib.Auto.Newsclliping.NewscllipingBean;
import Lib.Auto.EBook.EBookBean;
import Lib.Auto.EBookSearch.EBookSearchBean;


public class SearchServiceImpl implements SearchService {

	private SearchDao searchDao;

	public SearchServiceImpl() {

	}

	//-----------------AutoComplete-------------------------

	public ArrayList<Searchbean> getTitleSearch(String term) {

		return searchDao.findTitleSearch(term);
	}

	public ArrayList<Searchbean> getAuthorSearch(String term) {

		return searchDao.findAuthorSearch(term);
	}

	public ArrayList<Searchbean> getSubjectSearch(String term) {

		return searchDao.findSubjectSearch(term);
	}

	public ArrayList<Searchbean> getAdvAutoSearch(String term,String flag) {

		return searchDao.findAdvAutoSearch(term,flag);
	}

	public ArrayList<Searchbean> getQueryAutoSearch(String term,String flag) {

		return searchDao.findQueryAutoSearch(term,flag);
	}

	public ArrayList<Searchbean> getQuickAutoSearch(String term,String flag) {

		return searchDao.findQuickAutoSearch(term,flag);
	}

	public ArrayList<JournalSearchbean> getJournalAutoSearch(String term) {

		return searchDao.findJournalAutoSearch(term);
	}


	public ArrayList<EBookSearchBean> getEBookAutoTitleSearch(String term) {

		return searchDao.findEBookAutoTitleSearch(term);
	}

	public ArrayList<EBookSearchBean> getEBookAutoAuthorSearch(String term) {

		return searchDao.findEBookAutoAuthorSearch(term);
	}

	public ArrayList<EBookSearchBean> getEBookAutoSubjectSearch(String term) {

		return searchDao.findEBookAutoSubjectSearch(term);
	}

	public ArrayList<NewscllipingBean> getNewsclippingNameAutoSearch(String term) {

		return searchDao.findNewsclippingNameAutoSearch(term);
	}

	public ArrayList<NewscllipingBean> getNewsclippingTypeAutoSearch(String term) {

		return searchDao.findNewsclippingTypeAutoSearch(term);
	}

	public ArrayList<NewscllipingBean> getNewsclippingTitleAutoSearch(String term) {

		return searchDao.findNewsclippingTitleAutoSearch(term);
	}

	public ArrayList<NewscllipingBean> getNewsclippingSubjectAutoSearch(String term) {

		return searchDao.findNewsclippingSubjectAutoSearch(term);
	}

	public ArrayList<NewscllipingBean> getNewsclippingKeywordsAutoSearch(String term) {

		return searchDao.findNewsclippingKeywordsAutoSearch(term);
	}

	public ArrayList<JournalAtlSearchbean> getJournalArticlesNameAutoSearch(String term) {

		return searchDao.findJournalArticlesNameAutoSearch(term);
	}

	public ArrayList<JournalAtlSearchbean> getJournalArticlesTitleAutoSearch(String term) {

		return searchDao.findJournalArticlesTitleAutoSearch(term);
	}

	public ArrayList<JournalAtlSearchbean> getJournalArticlesAuthorAutoSearch(String term) {

		return searchDao.findJournalArticlesAuthorAutoSearch(term);
	}

	public ArrayList<JournalAtlSearchbean> getJournalArticlesSubjectAutoSearch(String term) {

		return searchDao.findJournalArticlesSubjectAutoSearch(term);
	}

	public ArrayList<JournalAtlSearchbean> getJournalArticlesAbstractAutoSearch(String term) {

		return searchDao.findJournalArticlesAbstractAutoSearch(term);
	}

	public ArrayList<JournalAtlSearchbean> getJournalArticlesKeywordsAutoSearch(String term) {

		return searchDao.findJournalArticlesKeywordsAutoSearch(term);
	}



	public List getSimpleSearch(String search,String document,String format) {



		{
			List results=getSearchDao().findSimpleSearch(search,document);

			List finalResults = null;
			if(results != null)
			{
				finalResults = new ArrayList();
				for(int i = 0; i < results.size(); i++)
				{
					Object[] obj = (Object[])results.get(i);
					Searchbean prt= new Searchbean();
					prt.setAccno(obj[0].toString());
					prt.setCallno(obj[1].toString());
					prt.setTitle(obj[2].toString());
					prt.setAuthor(obj[3].toString());
					prt.setEdition(obj[4].toString());
					prt.setlocation(obj[5].toString());
					prt.setavailability(obj[6].toString());
					prt.setdocument(obj[7].toString());
					prt.setYear(obj[8].toString());
					prt.setBprice(obj[9].toString());
					prt.setPlace(obj[10].toString());
					prt.setPublisher(obj[11].toString());
					prt.setVolno(obj[12].toString());

					if(document.equalsIgnoreCase("Article"))
					{
						prt.setSubject(obj[13].toString());
						prt.setJournal(obj[14].toString());
						prt.setIssueNo(obj[15].toString());
						prt.setPages(obj[16].toString());
					}

					if(document.equalsIgnoreCase("BOOK"))
					{
						prt.setSres(obj[13].toString());
						prt.setSubject(obj[14].toString());
						prt.setPages(obj[15].toString());
						prt.setPhyDesc(obj[16].toString());
						prt.setIsbn(obj[17].toString());
						prt.setSubtitle(obj[18].toString());
					}

					prt.setFormat(format);
					finalResults.add(prt);
				}
			}
			return finalResults;
		}

	}

	public List getEBookSearch(String search) {

		{
			List results=getSearchDao().findEBookSearch(search);

			List finalResults = null;
			if(results != null)
			{
				finalResults = new ArrayList();
				for(int i = 0; i < results.size(); i++)
				{
					Object[] obj = (Object[])results.get(i);

					EBookBean esb=new EBookBean();


					esb.setAccessNo(obj[0].toString());
					esb.setTitle(obj[1].toString());
					esb.setAuthorName(obj[2].toString());
					esb.setCallNo(obj[3].toString());
					esb.setEdition(obj[5].toString());
					esb.setYop(obj[6].toString());
					esb.setPages(obj[7].toString());
					esb.setSubName(obj[9].toString());
					esb.setPubName(obj[11].toString());
					esb.setDeptName(obj[12].toString());
					esb.setSupName(obj[13].toString());
					esb.setDoc(obj[15].toString());
					esb.setType(obj[16].toString());				    
					esb.setContent(obj[18].toString());
					esb.setIsbn(obj[19].toString());
					esb.setUrl(obj[20].toString());
					esb.setKeywords(obj[21].toString());

					finalResults.add(esb);
				}
			}
			return finalResults;
		}
	}


public List getAdvancedSearch(String search,String document,String format,String order) {
		
		
		
		{
			List results=getSearchDao().findAdvancedSearch(search,document,order);
			
			List finalResults = null;
			if(results != null)
			{
				finalResults = new ArrayList();
				for(int i = 0; i < results.size(); i++)
				{
					
					Object[] obj = (Object[])results.get(i);
					Searchbean prt= new Searchbean();
					prt.setAccno(obj[0].toString());
					prt.setCallno(obj[1].toString());
					prt.setTitle(obj[2].toString());
					prt.setAuthor(obj[3].toString());
					prt.setEdition(obj[4].toString());
					prt.setlocation(obj[5].toString());
				    prt.setavailability(obj[6].toString());
				    prt.setdocument(obj[7].toString());
				    prt.setYear(obj[8].toString());
				    prt.setBprice(obj[9].toString());
				    prt.setPlace(obj[10].toString());
				    //System.out.println("=========="+obj[11]);
				   // prt.setPublisher(obj[11].toString()); 
				    prt.setVolno(obj[12].toString());
				    prt.setMedia(obj[13].toString());
				    prt.setBinding(obj[14].toString());
				    prt.setInvoiceno(obj[15].toString());
				    prt.setInvoicedate(obj[16].toString());
				    prt.setPurchase(obj[17].toString());
				    prt.setAddfield1(obj[18].toString());
				    prt.setAddfield2(obj[19].toString());
				    prt.setAddfield3(obj[20].toString());
//				    prt.setSupplier(obj[21].toString());
//				    prt.setDeptname(obj[22].toString());
//				    prt.setSres(obj[23].toString());
//				    prt.setPages(obj[24].toString());
//				    prt.setScript(obj[25].toString());
//				    prt.setSubject(obj[26].toString());
				    				    
				    if(document.equalsIgnoreCase("Article"))
				    {
				    	prt.setSubject(obj[13].toString());
				    	prt.setJournal(obj[14].toString());
				    	prt.setIssueNo(obj[15].toString());
				    	prt.setPages(obj[16].toString());
				    }
				    
				    if(document.equalsIgnoreCase("BOOK"))
				    {
				    	prt.setSres(obj[13].toString());
				    	prt.setSubject(obj[14].toString());
				    	prt.setPages(obj[15].toString());
				    	prt.setPhyDesc(obj[16].toString());
				    }
				    
				    prt.setFormat(format);
					finalResults.add(prt);
					}
			}
			return finalResults;
		
		}
		}

	public List getFullViewSearch(String search) {



		{
			List results=getSearchDao().findFullViewSearch(search);

			List finalResults = null;
			if(results != null)
			{
				finalResults = new ArrayList();
				for(int i = 0; i < results.size(); i++)
				{
					Object[] obj = (Object[])results.get(i);
					Adsearchbean prt= new Adsearchbean();
					prt.setAccno(obj[0].toString());
					prt.setCallno(obj[1].toString());
					prt.setTitle(obj[2].toString());
					prt.setAuthor(obj[3].toString());
					prt.setlocation(obj[4].toString());
					prt.setavailability(obj[5].toString());
					prt.setdocument(obj[6].toString());			    
					prt.setPublisher(obj[7].toString());
					prt.setYear(obj[8].toString());
					prt.setSubject(obj[9].toString());
					prt.setdepartment(obj[10].toString());
					prt.setsupplier(obj[11].toString());
					// prt.setprice(Integer.parseInt((obj[12].toString())));
					String Price=obj[12].toString();//shek 05-05-2015
					prt.setprice(Double.parseDouble(Price));
					prt.setcontent(obj[13].toString());
					prt.setSRes(obj[14].toString());  // BY RK on 05-10-2013
					prt.setPlace(obj[15].toString());
					prt.setVolno(obj[16].toString());
					prt.setIssues(obj[17].toString());
					prt.setKeywords(obj[18].toString());
					finalResults.add(prt);

				}
			}
			return finalResults;
		}



	}


	public List getFullView(String search,String document) {



		{
			List results=getSearchDao().findFullView(search,document);

			List finalResults = null;
			if(results != null)
			{
				finalResults = new ArrayList();
				for(int i = 0; i < results.size(); i++)
				{
					Object[] obj = (Object[])results.get(i);
					Adsearchbean prt= new Adsearchbean();
					prt.setAccno(obj[0].toString());
					prt.setCallno(obj[1].toString());
					prt.setTitle(obj[2].toString());
					prt.setAuthor(obj[3].toString());
					prt.setlocation(obj[4].toString());
					prt.setavailability(obj[5].toString());
					prt.setdocument(obj[6].toString());			    
					prt.setPublisher(obj[7].toString());
					prt.setYear(obj[8].toString());
					prt.setSubject(obj[9].toString());
					prt.setdepartment(obj[10].toString());
					prt.setsupplier(obj[11].toString());
					//prt.setprice(Integer.parseInt((obj[12].toString())));
					String Price=obj[12].toString();//shek 05-05-2015
					prt.setprice(Double.parseDouble(Price));
					prt.setcontent(obj[13].toString());
					prt.setSRes(obj[14].toString());  // BY RK on 05-10-2013
					prt.setPlace(obj[15].toString());
					prt.setVolno(obj[16].toString());
					prt.setIssues(obj[17].toString());

					if(document.equalsIgnoreCase("Article"))
					{
						prt.setSubject(obj[18].toString());
						prt.setJournal(obj[19].toString());
						prt.setIssueMonth(obj[20].toString());
						prt.setPageNo(obj[21].toString());
					}
					finalResults.add(prt);

				}
			}
			return finalResults;
		}



	}





	public List getBrowseSearch(String search,String document,String format) {



		{
			List results=getSearchDao().findBrowseSearch(search,document);

			List finalResults = null;
			if(results != null)
			{
				finalResults = new ArrayList();
				for(int i = 0; i < results.size(); i++)
				{
					Object[] obj = (Object[])results.get(i);
					Searchbean prt= new Searchbean();
					prt.setAccno(obj[0].toString());
					prt.setCallno(obj[1].toString());
					prt.setTitle(obj[2].toString());
					prt.setAuthor(obj[3].toString());
					prt.setEdition(obj[4].toString());
					prt.setlocation(obj[5].toString());
					prt.setavailability(obj[6].toString());
					prt.setdocument(obj[7].toString());
					prt.setYear(obj[8].toString());
					prt.setBprice(obj[9].toString());
					prt.setPlace(obj[10].toString());
					prt.setPublisher(obj[11].toString());
					prt.setVolno(obj[12].toString());

					if(document.equalsIgnoreCase("Article"))
					{
						prt.setSubject(obj[13].toString());
						prt.setJournal(obj[14].toString());
						prt.setIssueNo(obj[15].toString());
						prt.setPages(obj[16].toString());
					}

					if(document.equalsIgnoreCase("BOOK"))
					{
						prt.setSres(obj[13].toString());
						prt.setSubject(obj[14].toString());
						prt.setPages(obj[15].toString());
						prt.setPhyDesc(obj[16].toString());
					}

					prt.setFormat(format);
					finalResults.add(prt);}
			}
			return finalResults;
		}



	}



	// ----------------- Journal Search ---------------

	public List getJournalSearch(String search) {	
		{
			List results=getSearchDao().findJournalSearch(search);

			List finalResults = null;
			if(results != null)
			{
				finalResults = new ArrayList();
				for(int i = 0; i < results.size(); i++)
				{
					Object[] obj = (Object[])results.get(i);
					JournalSearchbean prt= new JournalSearchbean();
					prt.setJnlCode(obj[0].toString());
					prt.setJnlName(obj[1].toString());
					prt.setFrequency(obj[2].toString());	
					prt.setDocument(obj[3].toString());	
					prt.setCountry(obj[4].toString());								
					prt.setLanguage(obj[5].toString());			
					prt.setIssn(obj[6].toString());


					finalResults.add(prt);
				}
			}
			return finalResults;
		}	
	}

	public List getJournalIssueSearch(String search) {
		{
			List results=getSearchDao().findJournalIssueSearch(search);

			List finalResults = null;
			if(results != null)
			{
				finalResults = new ArrayList();
				for(int i = 0; i < results.size(); i++)
				{
					Object[] obj = (Object[])results.get(i);
					JournalSearchbean prt= new JournalSearchbean();
					prt.setJnlIssueAccNo(obj[0].toString());
					prt.setJnlName(obj[1].toString());
					prt.setIssueYear(obj[2].toString());	
					prt.setIssueMonth(obj[3].toString());	
					prt.setIssueNo(obj[4].toString());								
					prt.setReceiveDate(Security.getdate(obj[5].toString()));			
					prt.setAvailability(obj[6].toString());
					prt.setIssueVolume(obj[7].toString());


					finalResults.add(prt);
				}
			}
			return finalResults;
		}
	}


	public List getJournalFullView(String search) {
		{
			List results=getSearchDao().findJournalFullView(search);

			List finalResults = null;
			if(results != null)
			{
				finalResults = new ArrayList();
				for(int i = 0; i < results.size(); i++)
				{
					Object[] obj = (Object[])results.get(i);
					JournalSearchbean prt= new JournalSearchbean();
					prt.setJnlIssueAccNo(obj[0].toString());
					prt.setJnlName(obj[1].toString());
					prt.setFrequency(obj[2].toString());
					prt.setCountry(obj[3].toString());
					prt.setDocument(obj[4].toString());
					prt.setIssueYear(obj[5].toString());
					prt.setIssueMonth(obj[6].toString());			    
					prt.setIssueNo(obj[7].toString());
					prt.setIssueVolume(obj[8].toString());
					prt.setReceiveDate(Security.getdate(obj[9].toString()));
					prt.setAvailability(obj[10].toString());
					prt.setAddField1(obj[11].toString());

					finalResults.add(prt);

				}
			}
			return finalResults;
		}

	}







	public List getAccountDetails(String search) {



		{
			List results=getSearchDao().findAccountDetails(search);

			List finalResults = null;
			if(results != null)
			{
				finalResults = new ArrayList();
				for(int i = 0; i < results.size(); i++)
				{
					System.out.println("<<<>>>>>>");
					AccountBean prt= new AccountBean();
					prt = (AccountBean)results.get(i);
//					Object[] obj = (Object[])results.get(i);
//                  
//
//					
//
//					if(obj[0].toString()!=null && !obj[0].toString().isEmpty()){
//						prt.setaccno(obj[0].toString());
//					}else{
//						prt.setaccno("");
//					}
//
//					if(obj[1].toString()!=null && !obj[1].toString().isEmpty()){
//						prt.settitle(obj[1].toString());
//					}else{
//						prt.settitle("");
//					}
//
//					if(obj[2].toString()!=null && !obj[2].toString().isEmpty()){
//						prt.setauthor(obj[2].toString());
//					}else{
//						prt.setauthor("");
//					}
//
//					if(obj[3].toString()!=null && !obj[3].toString().isEmpty()){
//						prt.setissuedt(Security.getdate(obj[3].toString()));
//					}else{
//						prt.setissuedt("");
//					}
//
//					if(obj[4].toString()!=null && !obj[4].toString().isEmpty()){
//						prt.setduedt(Security.getdate(obj[4].toString()));
//					}else{
//						prt.setduedt("");
//					}
//
//					if(obj[5].toString()!=null && !obj[5].toString().isEmpty()){
//						prt.setreturndt(Security.getdate(obj[5].toString()));
//					}else{
//						prt.setreturndt("");
//					}
//
//					if(obj[6].toString()!=null && !obj[6].toString().isEmpty()){
//						prt.setdtype(obj[6].toString());
//					}else{
//						prt.setdtype("");
//					}
//
//					if(obj[7].toString()!=null && !obj[7].toString().isEmpty()){
//						prt.setFineamount(obj[7].toString());			
//					}else{
//						prt.setFineamount("");
//					}
//
					finalResults.add(prt);


				}

			}
			return finalResults;
		}



	}





	public List getAccountTransDetails(String search) {



		{
			List results=getSearchDao().findAccountTransDetails(search);

			List finalResults = null;
			if(results != null)
			{
				finalResults = new ArrayList();
				for(int i = 0; i < results.size(); i++)
				{
					Object[] obj = (Object[])results.get(i);


					AccountBean prt= new AccountBean();

					if(obj[0].toString()!=null && !obj[0].toString().isEmpty()){
						prt.setaccno(obj[0].toString());
					}else{
						prt.setaccno("");
					}

					if(obj[1].toString()!=null && !obj[1].toString().isEmpty()){
						prt.settitle(obj[1].toString());
					}else{
						prt.settitle("");
					}

					if(obj[2].toString()!=null && !obj[2].toString().isEmpty()){
						prt.setauthor(obj[2].toString());
					}else{
						prt.setauthor("");
					}

					if(obj[3].toString()!=null && !obj[3].toString().isEmpty()){
						prt.setissuedt(Security.getdate(obj[3].toString()));
					}else{
						prt.setissuedt("");
					}

					if(obj[4].toString()!=null && !obj[4].toString().isEmpty()){
						prt.setduedt(Security.getdate(obj[4].toString()));
					}else{
						prt.setduedt("");
					}

					if(obj[5].toString()!=null && !obj[5].toString().isEmpty()){
						prt.setreturndt(Security.getdate(obj[5].toString()));
					}else{
						prt.setreturndt("");
					}

					if(obj[6].toString()!=null && !obj[6].toString().isEmpty()){
						prt.setdtype(obj[6].toString());
					}else{
						prt.setdtype("");
					}

					if(obj[7].toString()!=null && !obj[7].toString().isEmpty()){
						prt.setFineamount(obj[7].toString());			
					}else{
						prt.setFineamount("");
					}

					finalResults.add(prt);


				}

			}
			return finalResults;
		}



	}











	public List getAccountDetailsIssue(String search) {



		{
			List results=getSearchDao().findAccountDetailsIssue(search);

			List finalResults = null;
			if(results != null)
			{
				finalResults = new ArrayList();
				for(int i = 0; i < results.size(); i++)
				{
					AccountBean prt= new AccountBean();
					prt=(AccountBean)results.get(i);
					
//					Object[] obj = (Object[])results.get(i);
//					prt.setaccno(obj[0].toString());
//					prt.settitle(obj[1].toString());
//					prt.setauthor(obj[2].toString());
//					prt.setissuedt(Security.getdate(obj[3].toString()));
//					prt.setduedt(Security.getdate(obj[4].toString()));				
//					prt.setdtype(obj[5].toString());


					finalResults.add(prt);


				}

			}
			return finalResults;
		}	
	}


	public List getAccountPaidDetails(String search) {
		{
			List results=getSearchDao().findAccountpaymentDetails(search);
			List finalResults = null;
			if(results != null)
			{
				finalResults = new ArrayList();
				for(int i = 0; i < results.size(); i++)
				{
					Object[] obj = (Object[])results.get(i);
					AccountBean prt= new AccountBean();
					prt.setBillNo(obj[0].toString());
					prt.setuid(obj[1].toString());
					prt.setFineamount(obj[2].toString());
					prt.setissuedt(Security.getdate(obj[3].toString()));
					finalResults.add(prt);
				}
			}
			return finalResults;
		}
	}


	public List getAccountSugDetails(String search) {
		{
			List results=getSearchDao().findAccountSugDetails(search);
			System.out.println("lkkkkkkkkkkkkkkkkkkk");
			List finalResults = null;
			if(results != null)
			{			
				//------------------
				finalResults = new ArrayList();

				for(int i = 0; i < results.size(); i++)
				{

					Object[] obj = (Object[])results.get(i);

					AccountBean prt= new AccountBean();

					prt.setresdat(obj[0].toString());
					prt.setdtype(obj[1].toString());
					prt.setDetail(obj[2].toString());
					prt.setAction(obj[3].toString());	
					prt.setavailability(obj[4].toString());
					finalResults.add(prt);


				}
			}
			return finalResults;
		}
	}

	public List getAccountDetailsReserve(String search) {



		{
			List results=getSearchDao().findAccountDetailsReserve(search);

			List finalResults = null;
			if(results != null)
			{
				finalResults = new ArrayList();
				for(int i = 0; i < results.size(); i++)
				{
					Object[] obj = (Object[])results.get(i);


					AccountBean prt= new AccountBean();
					prt.setaccno(obj[0].toString());
					prt.settitle(obj[1].toString());
					prt.setauthor(obj[2].toString());
					prt.setresdat(Security.getdate(obj[3].toString()));
					prt.setavailability(obj[4].toString());				
					prt.setdtype(obj[5].toString());


					finalResults.add(prt);


				}

			}
			return finalResults;
		}



	}

	public List getLoadBranchList() {
		return searchDao.findLoadBranchList();
	}

	public AccountBean getCheckAccount(String uid,String pwd) {
		return searchDao.findCheckAccount(uid,pwd);
	}

	public String getOnlineRenewSave(AccountBean newbean) {
		return searchDao.findOnlineRenewSave(newbean);
	}	

	public String getOnlineReserveCancel(AccountBean newbean) {
		return searchDao.findOnlineReserveCancel(newbean);
	}

	public String getChangePwd(AccountBean newbean) {
		return searchDao.findChangePwd(newbean);
	}


	public List getRegisterLoad() {



		{
			List results=getSearchDao().findRegisterLoad();

			List finalResults = null;
			if(results != null)
			{
				finalResults = new ArrayList();
				for(int i = 0; i < results.size(); i++)
				{
					Object[] obj = (Object[])results.get(i);
					RegisterBean prt= new RegisterBean();
					prt.setgmembercode(obj[0].toString());
					prt.setgmembername(obj[1].toString());
					prt.setCyear(obj[2].toString());
					prt.setgdeptname(obj[3].toString());
					prt.setgcoursename(obj[4].toString());				
					prt.setgentrytime(obj[5].toString());
					prt.setgdesignation(obj[6].toString());


					finalResults.add(prt);

				}
			}
			return finalResults;
		}


	}


	public List getTodayRegisterLoad() {



		{
			List results=getSearchDao().findTodayRegisterLoad();

			List finalResults = null;
			if(results != null)
			{
				finalResults = new ArrayList();
				for(int i = 0; i < results.size(); i++)
				{
					Object[] obj = (Object[])results.get(i);
					RegisterBean prt= new RegisterBean();
					prt.setgmembercode(obj[0].toString());
					prt.setgmembername(obj[1].toString());
					prt.setCyear(obj[2].toString());
					prt.setgdeptname(obj[3].toString());
					prt.setgcoursename(obj[4].toString());				
					prt.setgentrytime(obj[5].toString());
					prt.setgexittime(obj[6].toString());
					prt.setgdesignation(obj[7].toString());

					//				System.out.print(obj[0].toString());
					//				System.out.print(obj[1].toString());
					//				System.out.print(obj[2].toString());
					//				System.out.print(obj[3].toString());
					//				System.out.print(obj[4].toString());
					//				System.out.print(obj[5].toString());
					//				System.out.print(obj[6].toString());
					//				System.out.print(obj[7].toString());




					finalResults.add(prt);

				}
			}
			return finalResults;
		}


	}


	public int getRegisterAllLogout()
	{	
		int count = searchDao.findRegisterAllLogout();
		return count;
	}

	public AccountBean getRegisterEntry(String search) {
		return searchDao.findRegisterEntry(search);
	}



	//-----------------------------------------------------

	public List getNewsClipSimpleSearch(String search) {



		{
			List results=getSearchDao().findNewsClipSimpleSearch(search);



			List finalResults = null;
			if(results != null)
			{
				finalResults = new ArrayList();
				for(int i = 0; i < results.size(); i++)
				{
					Object[] obj = (Object[])results.get(i);
					NewsSearchbean prt= new NewsSearchbean();
					prt.setNname(obj[0].toString());
					prt.setNtype(obj[1].toString());
					prt.setNtitle(obj[2].toString());
					prt.setNabstract(obj[3].toString());
					prt.setNsubject(obj[4].toString());
					prt.setNkey(obj[5].toString());
					prt.setNcode(obj[6].toString());
					prt.setNcontent(obj[7].toString());
					finalResults.add(prt);
				}
			}
			return finalResults;
		}



	}




	public List getEResourceSimpleSearch(String search) {



		{
			List results=getSearchDao().findEResourceSimpleSearch(search);



			List finalResults = null;
			if(results != null)
			{
				finalResults = new ArrayList();
				for(int i = 0; i < results.size(); i++)
				{
					Object[] obj = (Object[])results.get(i);
					EResourceSearchbean prt= new EResourceSearchbean();
					prt.setEcode(obj[0].toString());
					prt.setEsite(obj[1].toString());
					prt.setEtitle(obj[2].toString());
					prt.setEsubtitle(obj[3].toString());
					prt.setEtype(obj[4].toString());
					prt.setEdetails(obj[5].toString());

					finalResults.add(prt);
				}
			}
			return finalResults;
		}	
	}


	public List getJournalArticleSearch(String search) 	
	{
		List results=getSearchDao().findJournalArticleSearch(search);

		List finalResults = null;
		if(results != null)
		{
			finalResults = new ArrayList();
			for(int i = 0; i < results.size(); i++)
			{
				Object[] obj = (Object[])results.get(i);
				JournalAtlSearchbean prt= new JournalAtlSearchbean();
				prt.setJname(obj[0].toString());
				prt.setAtitle(obj[1].toString());
				prt.setAuthor(obj[2].toString());
				prt.setAsubject(obj[3].toString());
				prt.setAkeywords(obj[4].toString());
				prt.setContent(obj[5].toString());
				prt.setPageno(obj[6].toString());
				prt.setAtlno(Integer.parseInt(obj[7].toString()));
				prt.setVolno(obj[8].toString());
				prt.setIssueno(obj[9].toString());
				prt.setIyear(obj[10].toString());
				prt.setImonth(obj[11].toString());

				finalResults.add(prt);
			}
		}
		return finalResults;
	}	



	public List getIssueDetails(String code,String doc) {

		{
			
				List results=getSearchDao().findIssueDetails(code,doc);
				
				List finalResults = null;
				if(results != null)
				{
					finalResults = new ArrayList();
					for(int i = 0; i < results.size(); i++)
					{
						AccountBean prt= new AccountBean();
						prt = (AccountBean)results.get(i);
//						prt.setuid(obj[0].toString());
//						prt.setuname(obj[1].toString());
//						prt.setaccno(obj[2].toString());
//						prt.settitle(obj[3].toString());
//						prt.setduedt(Security.getdate(obj[4].toString()));				
//					    prt.setdtype(obj[5].toString());			 
						
						finalResults.add(prt);				
						
					}
					
				}
				return finalResults;
			}	
	}




	public AccountBean getReserveCheck(AccountBean newbean)
	{
		return searchDao.findReserveCheck(newbean);	
	}

	public SearchDao getSearchDao() {
		return searchDao;
	}

	public void setSearchDao(SearchDao SearchDao) {
		this.searchDao = SearchDao;
	}

	// Query Builder

	public List getQueryBuilderSearch(QueryBuilderBean newBean) {
		return searchDao.getQueryBuilderSearch(newBean);
	}


	//-------------Question Bank Search---------


	public List getFullViewQBSearch(String search) {

		List results = getSearchDao().findFullViewQBSearch(search);

		List finalResults = null;
		if (results != null) {
			finalResults = new ArrayList();
			for (int i = 0; i < results.size(); i++) {					
				Object[] obj = (Object[]) results.get(i);

				QuestionSearchBean prt = new QuestionSearchBean();
				prt.setQcode(Integer.parseInt(obj[0].toString()));
				prt.setQdate(Security.getdate(obj[1].toString()));
				prt.setUniname(obj[2].toString());
				prt.setSname(obj[3].toString());
				prt.setSubcode(obj[4].toString());					
				prt.setDname(obj[5].toString());
				prt.setQcourse(obj[6].toString());

				prt.setQyear(obj[8].toString());
				prt.setQmonth(obj[9].toString());
				prt.setSemester(obj[10].toString());
				prt.setRemarks1(obj[11].toString());
				prt.setRemarks1(obj[12].toString());
				if(obj[13]!=null)
					prt.setContents(obj[13].toString());


				finalResults.add(prt);

			}
		}
		return finalResults;

	}

	public List<QuestionSearchBean> getQBSearch(String search) {
		{
			List<?> results = getSearchDao().getQBSearch(search);

			List<QuestionSearchBean> finalResults = null;
			if (results != null) {
				finalResults = new ArrayList<QuestionSearchBean>();
				for (int i = 0; i < results.size(); i++) {
					Object[] obj = (Object[]) results.get(i);

					QuestionSearchBean prt = new QuestionSearchBean();

					prt.setQcode(Integer.parseInt(obj[0].toString()));//ref no
					prt.setSubcode(obj[1].toString());//subject code
					prt.setSname(obj[2].toString());//subject name
					prt.setDname(obj[3].toString());//department
					prt.setQcourse(obj[4].toString());//course name
					prt.setRemarks2(obj[5].toString());//course major
					prt.setQmonth(obj[6].toString());//month
					prt.setQyear(obj[7].toString());//year
					prt.setRemarks1(obj[8].toString());//semester
					prt.setContents(obj[9].toString());//contents



					finalResults.add(prt);
				}
			}
			return finalResults;
		}
	}
	//-------------------------New Arrivals-----------------------


	public List getMonthYearList(NewArrivalsBean bean)
	{
		return searchDao.findMonthYearList(bean);
	}
	public List getSubjectList()
	{
		return searchDao.findSubjectList();
	}
	public List getNewArrivalSearchResult(NewArrivalsBean newbean)
	{
		return searchDao.findNewArrivalSearchResult(newbean);
	}
	
	public JournalSearchbean getJournalFullViewDetails(String sQL_Query) {
		
		return searchDao.findJournalFullViewDetails(sQL_Query);
	}



	//-------------------------Gate Counts-----------------------
}

