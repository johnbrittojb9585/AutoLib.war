package Common.businessutil.serialcontrol;

import Lib.Auto.Author.AuthorBean;
import Lib.Auto.JNL_Enquiry.JnlenquiryBean;
import Lib.Auto.JNL_Invoice.JnlInvoiceBean;
import Lib.Auto.JNL_Order.JnlorderBean;
import Lib.Auto.JNL_Payment.JnlPaymentBean;
import Lib.Auto.JNL_Supplier_Invoice.JnlSupInvBean;
import Lib.Auto.Journal.journalbean;

import java.util.ArrayList;
import java.util.List;

import Lib.Auto.JournalSubscription.JournalSubscriptionbean;
import Lib.Auto.Journal_Issues.JnlIssBean;
import Lib.Auto.Journal_Artical.journalArtbean;
public interface SerialcontrolDao {
	
	
	//----------------journal subscription--------------
	
		public JournalSubscriptionbean findJnlSubscriptionMax(int jnlcode);
		public int findJnlSubscriptionSave(JournalSubscriptionbean newbean);
		public int findJnlSubscriptionUpdate(JournalSubscriptionbean newbean);
		public JournalSubscriptionbean findJnlSearchName(JournalSubscriptionbean newbean);
		public JournalSubscriptionbean findjnlSubscriptionSearch(JournalSubscriptionbean newbean);
		
		public int findjnlSubscriptionDateSearch(JournalSubscriptionbean newbean);
		
		
		
		
		public JournalSubscriptionbean findjnlSubscriptionDelete(JournalSubscriptionbean newbean);
		
		
		
		public JournalSubscriptionbean findjnlSubsFlag(JournalSubscriptionbean newbean);
		public int findjnlSubsFlagUpdate(String code);
		public String findjnlSubsCalc(String frequency,String issueDate);
		public String findjnlfrequencyFirstTime(String frequency,String issueDate);
		
		
		
		
		
		
		
	//Journal
	public journalbean findJnlMax();
	public journalbean findJnlSearch(int code);
	public int findJnlInterface(int code);
	public int findJnlDelete(int code);
	public int findJnlSave(journalbean newbean);
	public int findJnlUpdate(journalbean newbean);
	public journalbean findJnlSearchName(journalbean newbean);
	
	//Journal Issues
	public JnlIssBean findJnlIssuesMax();
	public JnlIssBean findJnlIssuesSearchName(String name);
	public JnlIssBean findJnlFindIssues(JnlIssBean newbean);
	public int findJnlIssuesAccno(String code);
	public JnlIssBean findJnlIssuesUpdate(JnlIssBean newbean);
	public JnlIssBean findJnlIssuesSave(JnlIssBean newbean);
	public JnlIssBean getJnlSearch(JnlIssBean newbean);
	public JnlIssBean findJnlDelete(JnlIssBean newbean);
	public JnlIssBean findJnlsup_date(JnlIssBean newbean);
	
	//Journal Atl
	
	public String findJnlAtlMax();
	public journalArtbean findJnlSearchAtl(int code);
	public int findJnlDeleteAtl(int code);
	public int findJnlAtlSave(journalArtbean newbean);
	public int findJnlAtlUpdate(journalArtbean newbean);
	
	
    // Journal Enquiry Processing
	
	public List findEnqJNLSearchName(JnlenquiryBean newbean);		
	public List findEnqSelectedJNL(JnlenquiryBean newbean);
	public List findEnqJNLMasSave(JnlenquiryBean newbean);		
	public List findEnqJNLDetailsSave(List details);	
	public List findEnqJNLFullview(String enqno);	
	public void findEnqJNLDelete(String enqno);
	public List findOrdJNLCheck(String enqno);
   
	//Journal Order Processing
	
	public List findOrdJNLSearchName(JnlorderBean newbean);		
	public List findOrdSelectedJNL(JnlorderBean newbean);
	public List findOrdJNLMasSave(JnlorderBean newbean);		
	public List findOrdJNLDetailsSave(List details);	
	public List findOrdJNLFullview(String ordno);	
	public void findOrdJNLDelete(String ordno);
	public List findSupInvCheck(String ordno);
	public List findEnqJNLFullviewForOrder(JnlorderBean newbean);	
	
	
	
    // Journal Invoice Processing
	
	public JnlInvoiceBean findInvoiceMax();
	public List findSupInvNoSearch(JnlInvoiceBean newbean); 
	//public List findSupInvDetailsLoad(JnlInvoiceBean newbean);
	public List findInvJNLEntrySave(JnlInvoiceBean newbean);	
	public List findInvJNLSearch(String invno);
	public void findInvJNLDelete(String invno);
	
	
    // Journal Invoice Processing
	
	public JnlPaymentBean findPaymentMax();
	public List findPaymentInvSearch(JnlPaymentBean newbean);
	public List findPaymentSelectedInvoice(JnlPaymentBean newbean);
	public void findPaymentJNLDetailsUpdate(List Details);
	public List findPaymentJNLMasSave(JnlPaymentBean newbean);
	public List findPaymentJNLDetailsSearch(String pmtno);
	public void findPaymentJNLDelete(String pmtno);
	
//	Journal Supplier Invoice Processing
	public List findSupInvOrdJNLCheckList(JnlSupInvBean newbean);
	public List findInvSearchOrdNoSup(JnlSupInvBean newbean); 
	public List findSupInvOrdJNLMasSave(JnlSupInvBean newbean);
	public List findSupInvOrdJNLDetailsSave(List details);	
	public void findSupInvOrdJNLUpdate(String jnlscode,String status);
	public List findSupInvOrdJNLFullview(String invno);
	public void findSupInvOrdJNLDelete(String invno);

}