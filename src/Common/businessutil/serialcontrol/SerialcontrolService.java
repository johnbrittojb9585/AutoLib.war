package Common.businessutil.serialcontrol;


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

public interface SerialcontrolService {
	
	
	// -----------journal Subscription----------
	
	
		public JournalSubscriptionbean getJnlSubscriptionMax(int jnlcode);
		
		public int getJnlSubscriptionSave(JournalSubscriptionbean newbean);
		
		public int getJnlSubscriptionUpdate(JournalSubscriptionbean newbean);
		
		public JournalSubscriptionbean getJnlSearchName(JournalSubscriptionbean newbean);
		
		public JournalSubscriptionbean getjnlSubscriptionSearch(JournalSubscriptionbean newbean);
		
		public int getjnlSubscriptionDateSearch(JournalSubscriptionbean newbean);
		
		
		public JournalSubscriptionbean getjnlSubscriptionDelete(JournalSubscriptionbean newbean);
		
		
		public JournalSubscriptionbean getjnlSubsFlag(JournalSubscriptionbean newbean);
		
		public int getjnlSubsFlagUpdate(String code);
		
		public String getjnlSubsCalc(String frequency,String issueDate);
		public String getjnlfrequencyFirstTime(String frequency,String issueDate);
		
		
		
		//
	
	//Journal
	public journalbean getJnlMax();
	public journalbean getJnlSearch(int code);
	public int getJnlInterface(int code);
	public int getJnlDelete(int code);
	public int getJnlSave(journalbean newbean);
	public int getJnlUpdate(journalbean newbean);
	public journalbean getJnlSearchName(journalbean newbean);
	
	//Journal Issues
		public JnlIssBean getJnlIssuesMax();
		public JnlIssBean getJnlIssuesSearchName(String name);
		public JnlIssBean getJnlFindIssues(JnlIssBean newbean);
		public int getJnlIssuesAccno(String code);
		public JnlIssBean getJnlIssuesUpdate(JnlIssBean newbean);
		public JnlIssBean getJnlIssuesSave(JnlIssBean newbean);		
		public JnlIssBean getJnlSearch(JnlIssBean newbean);
		public JnlIssBean getJnlDelete(JnlIssBean newbean);
		public JnlIssBean getsup_date(JnlIssBean newbean);
	
	//Journal Atl
	
	public String getJnlAtlMax();	
	public journalArtbean getJnlSearchAtl(int code);
	public int getJnlDeleteAtl(int code);
	public int getJnlAtlSave(journalArtbean newbean);
	public int getJnlAtlUpdate(journalArtbean newbean);

	
	
   // Journal Enquiry Processing
	
	public List getEnqJNLSearchName(JnlenquiryBean newbean);	
	public List getEnqSelectedJNL(JnlenquiryBean newbean);	
	public List getEnqJNLMasSave(JnlenquiryBean newbean);	
	public List getEnqJNLDetailsSave(List Details);	
	public List getEnqJNLFullview(String enqno);	
	public void getEnqJNLDelete(String enqno);
	public List getOrdJNLCheck(String enqno);	
	
	
	
	//Journal Order Processing
	
	public List getOrdJNLSearchName(JnlorderBean newbean);	
	public List getOrdSelectedJNL(JnlorderBean newbean);	
	public List getOrdJNLMasSave(JnlorderBean newbean);	
	public List getOrdJNLDetailsSave(List Details);	
	public List getOrdJNLFullview(String ordno);	
	public void getOrdJNLDelete(String ordno);
	public List getSupInvCheck(String ordno);	
	public List getEnqJNLFullviewForOrder(JnlorderBean newbean);
	
	
	//Journal Invoice Processing
	
	public JnlInvoiceBean getInvoiceMax();
	public List getSupInvNoSearch(JnlInvoiceBean newbean);
	//public List getSupInvDetailsLoad(JnlInvoiceBean newbean);
	public List getInvJNLEntrySave(JnlInvoiceBean newbean);	
	public List getInvJNLSearch(String invno);	
	public void getInvJNLDelete(String invno);
	
    //Journal Payment Processing
	
	public JnlPaymentBean getPaymentMax();
	public List getPaymentInvSearch(JnlPaymentBean newbean);
	public List getPaymentSelectedInvoice(JnlPaymentBean newbean);	
	public void getPaymentJNLDetailsUpdate(List Details);	
	public List getPaymentJNLMasSave(JnlPaymentBean newbean);	
	public List getPaymentJNLDetailsSearch(String pmtno);
	public void getPaymentJNLDelete(String pmtno);
	
    //Journal Supplier Invoice Processing
	public List getSupInvOrdJNLCheckList(JnlSupInvBean newbean);
	public List getInvSearchOrdNoSup(JnlSupInvBean newbean);	
	public List getSupInvOrdJNLMasSave(JnlSupInvBean newbean);	
	public List getSupInvOrdJNLDetailsSave(List Details);	
	public void getSupInvOrdJNLUpdate(String jnlscode,String status);
	public List getSupInvOrdJNLFullview(String invno);	
	public void getSupInvOrdJNLDelete(String invno);

	
}
