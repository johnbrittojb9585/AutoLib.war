package Common.businessutil.serialcontrol;

import java.util.ArrayList;
import java.util.List;

import Lib.Auto.Author.AuthorBean;
import Lib.Auto.JNL_Enquiry.JnlenquiryBean;
import Lib.Auto.JNL_Enquiry.JnlenquiryDetailsBean;
import Lib.Auto.JNL_Invoice.JnlInvoiceBean;
import Lib.Auto.JNL_Order.JnlorderBean;
import Lib.Auto.JNL_Order.JnlorderDetailsBean;
import Lib.Auto.JNL_Payment.JnlPaymentBean;
import Lib.Auto.JNL_Supplier_Invoice.JnlSupInvBean;
import Lib.Auto.Journal.journalbean;
import Lib.Auto.JournalSubscription.JournalSubscriptionbean;
import Lib.Auto.Journal_Issues.JnlIssBean;
import Lib.Auto.Journal_Artical.journalArtbean;
import Common.businessutil.serialcontrol.SerialcontrolService;
import Common.businessutil.serialcontrol.SerialcontrolDao;


public class SerialcontrolServiceImpl implements SerialcontrolService {
	
	private SerialcontrolDao serialcontrolDao;

	public SerialcontrolServiceImpl() {
	}
	
	
	//-----------------journalSubscription-------------------------
	
		public JournalSubscriptionbean getJnlSubscriptionMax(int jnlcode) {
			return serialcontrolDao.findJnlSubscriptionMax(jnlcode);
		}
		
		public int getJnlSubscriptionSave(JournalSubscriptionbean newbean) {
			return serialcontrolDao.findJnlSubscriptionSave(newbean);
		}
		
		public int getJnlSubscriptionUpdate(JournalSubscriptionbean newbean) {
			return serialcontrolDao.findJnlSubscriptionUpdate(newbean);
		}
		
		public JournalSubscriptionbean getJnlSearchName(JournalSubscriptionbean newbean) {
			return serialcontrolDao.findJnlSearchName(newbean);
		}
		
		public JournalSubscriptionbean getjnlSubscriptionSearch(JournalSubscriptionbean newbean) {
			return serialcontrolDao.findjnlSubscriptionSearch(newbean);
		}
		
		
		public int getjnlSubscriptionDateSearch(JournalSubscriptionbean newbean) {
			return serialcontrolDao.findjnlSubscriptionDateSearch(newbean);
		}
		
		public JournalSubscriptionbean getjnlSubscriptionDelete(JournalSubscriptionbean newbean) {
			return serialcontrolDao.findjnlSubscriptionDelete(newbean);
		}
		
		
		
		public JournalSubscriptionbean getjnlSubsFlag(JournalSubscriptionbean newbean) {
			return serialcontrolDao.findjnlSubsFlag(newbean);
		}
		
		public int getjnlSubsFlagUpdate(String code) {
			
			return serialcontrolDao.findjnlSubsFlagUpdate(code);
		}
		
		
	public String getjnlSubsCalc(String frequency,String issueDate) {
			
			return serialcontrolDao.findjnlSubsCalc(frequency,issueDate);
		}


	public String getjnlfrequencyFirstTime(String frequency,String issueDate) {
			
			return serialcontrolDao.findjnlfrequencyFirstTime(frequency,issueDate);
		}

		
		
		//findjnlSubscriptionSearch
		
	//Journal
	public journalbean getJnlMax() {
		return serialcontrolDao.findJnlMax();
	}
	
	public journalbean getJnlSearch(int code) {
		return serialcontrolDao.findJnlSearch(code);
	}
	
	public int getJnlInterface(int code) {
		return serialcontrolDao.findJnlInterface(code);
	}
	
	public int getJnlDelete(int code) {
		return serialcontrolDao.findJnlDelete(code);
	}
	
	public int getJnlSave(journalbean newbean) {
		return serialcontrolDao.findJnlSave(newbean);
	}
	
	public int getJnlUpdate(journalbean newbean) {
		return serialcontrolDao.findJnlUpdate(newbean);
	}
	
	public journalbean getJnlSearchName(journalbean newbean) {
		return serialcontrolDao.findJnlSearchName(newbean);
	}
	
	//Journal Issues
	
		public JnlIssBean getJnlIssuesMax() {
			return serialcontrolDao.findJnlIssuesMax();
		}
		
		public JnlIssBean getJnlIssuesSearchName(String name) {
			return serialcontrolDao.findJnlIssuesSearchName(name);
		}
		
		public JnlIssBean getJnlFindIssues(JnlIssBean newbean) {
			return serialcontrolDao.findJnlFindIssues(newbean);
		}
		
		public int getJnlIssuesAccno(String code) {
			return serialcontrolDao.findJnlIssuesAccno(code);
		}
		
		
		public JnlIssBean getJnlIssuesUpdate(JnlIssBean newbean) {
			return serialcontrolDao.findJnlIssuesUpdate(newbean);
		}
		
		public JnlIssBean getJnlIssuesSave(JnlIssBean newbean) {
			return serialcontrolDao.findJnlIssuesSave(newbean);
		}
		
		public JnlIssBean getJnlSearch(JnlIssBean newbean) {
			return serialcontrolDao.getJnlSearch(newbean);
		}
		public JnlIssBean getJnlDelete(JnlIssBean newbean) {
			return serialcontrolDao.findJnlDelete(newbean);
		}
		public JnlIssBean getsup_date(JnlIssBean newbean) {
			return serialcontrolDao.findJnlsup_date(newbean);
		}
	
	//Journal Atl
	
	public String getJnlAtlMax() {
		return serialcontrolDao.findJnlAtlMax();
	}
	
	public journalArtbean getJnlSearchAtl(int code) {
		return serialcontrolDao.findJnlSearchAtl(code);
	}
	public int getJnlDeleteAtl(int code) {
		return serialcontrolDao.findJnlDeleteAtl(code);
	}
	
	public int getJnlAtlSave(journalArtbean newbean) {
		return serialcontrolDao.findJnlAtlSave(newbean);
	}
	
	public int getJnlAtlUpdate(journalArtbean newbean) {
		return serialcontrolDao.findJnlAtlUpdate(newbean);
	}

	
	
//	Journal Enquiry Processing
	
	 public List getEnqJNLSearchName(JnlenquiryBean newbean)   {
			
			List results=getSerialcontrolDao().findEnqJNLSearchName(newbean);
									
			List finalResults = null;
			if(results != null)
			{
				finalResults = new ArrayList();
				for(int i = 0; i < results.size(); i++)
				{
					Object[] obj = (Object[])results.get(i);
					JnlenquiryDetailsBean enqDetails = new JnlenquiryDetailsBean();
					
					enqDetails.setJnlCode(Integer.valueOf(obj[0].toString()));
					//prt.setAdd2(obj[0].toString());
					enqDetails.setJournal(obj[1].toString());
					enqDetails.setAdd1(obj[2].toString());
									
					finalResults.add(enqDetails);
				}
			}
			return finalResults;
		}
	    
	    
	    
   public List getEnqSelectedJNL(JnlenquiryBean newbean)   {
	    	
			return serialcontrolDao.findEnqSelectedJNL(newbean);			
	}
	    
	    
	public List getEnqJNLMasSave(JnlenquiryBean newbean)   {
		
			return serialcontrolDao.findEnqJNLMasSave(newbean);			
		}

	public List getEnqJNLDetailsSave(List Details)  {
		
		    return serialcontrolDao.findEnqJNLDetailsSave(Details);
	}

	public List getEnqJNLFullview(String enqno)  {
		    
		    return serialcontrolDao.findEnqJNLFullview(enqno);
	}

	public void getEnqJNLDelete(String enqno)  {
		
		    serialcontrolDao.findEnqJNLDelete(enqno);	
	}
	public List getOrdJNLCheck(String enqno)   {
		
		return serialcontrolDao.findOrdJNLCheck(enqno);	
	}

	
  // Journal Order Processing

   public List getOrdJNLSearchName(JnlorderBean newbean)   {
		
		List results=getSerialcontrolDao().findOrdJNLSearchName(newbean);
		
		
		
		List finalResults = null;
		if(results != null)
		{
			finalResults = new ArrayList();
			for(int i = 0; i < results.size(); i++)
			{
				Object[] obj = (Object[])results.get(i);
				JnlorderDetailsBean ordDetails = new JnlorderDetailsBean();
				
				ordDetails.setJnlCode(Integer.valueOf(obj[0].toString()));
				//prt.setAdd2(obj[0].toString());
				ordDetails.setJournal(obj[1].toString());
				ordDetails.setAdd1(obj[2].toString());
								
				finalResults.add(ordDetails);
			}
		}
		return finalResults;
		//return serialcontrolDao.findOrdJNLSearchName(newbean);		
	}
   
   
   
   public List getOrdSelectedJNL(JnlorderBean newbean)   {
   	
		return serialcontrolDao.findOrdSelectedJNL(newbean);			
	}
   
   
   
   
	
public List getOrdJNLMasSave(JnlorderBean newbean)   {
	
		return serialcontrolDao.findOrdJNLMasSave(newbean);			
	}

public List getOrdJNLDetailsSave(List Details)  {
	
	    return serialcontrolDao.findOrdJNLDetailsSave(Details);
}

public List getOrdJNLFullview(String ordno)  {
	    
	    return serialcontrolDao.findOrdJNLFullview(ordno);
}

public void getOrdJNLDelete(String ordno)  {
	
	    serialcontrolDao.findOrdJNLDelete(ordno);	
}
public List getSupInvCheck(String ordno)   {
	
	return serialcontrolDao.findSupInvCheck(ordno);	
}

public List getEnqJNLFullviewForOrder(JnlorderBean newbean)   {
	
	return serialcontrolDao.findEnqJNLFullviewForOrder(newbean);	
}



//Journal Invoice Processing

public JnlInvoiceBean getInvoiceMax()  {
	
	return   serialcontrolDao.findInvoiceMax();	
}

public List getSupInvNoSearch(JnlInvoiceBean newbean)   {
	
	return serialcontrolDao.findSupInvNoSearch(newbean);	
}

/*public List getSupInvDetailsLoad(JnlInvoiceBean newbean)  {
	
	return serialcontrolDao.findSupInvDetailsLoad(newbean);	
}*/

public List getInvJNLEntrySave(JnlInvoiceBean newbean)  {	

	return serialcontrolDao.findInvJNLEntrySave(newbean);
}

public List getInvJNLSearch(String invno)    {
	
	return serialcontrolDao.findInvJNLSearch(invno);
}

public void getInvJNLDelete(String invno)  {
	
	 serialcontrolDao.findInvJNLDelete(invno);
}


//Journal Payment Processing

public JnlPaymentBean getPaymentMax()  {
	
	return   serialcontrolDao.findPaymentMax();	
}

public List getPaymentInvSearch(JnlPaymentBean newbean)  {
	
	return   serialcontrolDao.findPaymentInvSearch(newbean);
}

public List getPaymentSelectedInvoice(JnlPaymentBean newbean)  {
	
	return   serialcontrolDao.findPaymentSelectedInvoice(newbean);
}

public void getPaymentJNLDetailsUpdate(List Details)   {
	
	   serialcontrolDao.findPaymentJNLDetailsUpdate(Details);
}

public List getPaymentJNLMasSave(JnlPaymentBean newbean) {
	
	return   serialcontrolDao.findPaymentJNLMasSave(newbean);	
}

public List getPaymentJNLDetailsSearch(String pmtno) {

	return   serialcontrolDao.findPaymentJNLDetailsSearch(pmtno);	
}

public void getPaymentJNLDelete(String pmtno)  {
	
	serialcontrolDao.findPaymentJNLDelete(pmtno);
}


//Journal Supplier Invoice Processing

public List getSupInvOrdJNLCheckList(JnlSupInvBean newbean)  {
	
	return serialcontrolDao.findSupInvOrdJNLCheckList(newbean);
}

public List getInvSearchOrdNoSup(JnlSupInvBean newbean)   {
	
	return serialcontrolDao.findInvSearchOrdNoSup(newbean);	
}

public List getSupInvOrdJNLMasSave(JnlSupInvBean newbean)   {
	
	return serialcontrolDao.findSupInvOrdJNLMasSave(newbean);			
}

public List getSupInvOrdJNLDetailsSave(List Details)  {
	
   return serialcontrolDao.findSupInvOrdJNLDetailsSave(Details);
}


public void getSupInvOrdJNLUpdate(String jnlscode,String status)  {
	
	 serialcontrolDao.findSupInvOrdJNLUpdate(jnlscode,status);
}

public List getSupInvOrdJNLFullview(String invno)  {
	
	return serialcontrolDao.findSupInvOrdJNLFullview(invno);
}

public void getSupInvOrdJNLDelete(String invno)  {
	
   serialcontrolDao.findSupInvOrdJNLDelete(invno);	
}

	
	
	
	
	
	
	
	
	
	
	
	
	
	public SerialcontrolDao getSerialcontrolDao() {
		return serialcontrolDao;
	}

	public void setSerialcontrolDao(SerialcontrolDao serialcontrolDao) {
		this.serialcontrolDao = serialcontrolDao;
	}

	
	
	

	
}