package Common.businessutil.circulation;

import java.util.ArrayList;
import java.util.List;

import Lib.Auto.Author.AuthorBean;
import Lib.Auto.Binding_Books.BookBindingBean;
import Lib.Auto.Counter.CounterBean;
import Lib.Auto.Counter.CounterMemberBean;
import Lib.Auto.Counter.ReserveBean;
import Lib.Auto.Payment.PaymentBean;
import Lib.Auto.Transfer_Books.BookTransferBean;
import Lib.Auto.Member.MemberBean;
import Lib.Auto.Book.bookbean;

public interface CirculationService {
	
	//Member
	//public String deletetransaction(String code);
	
	public String getCheckEligible(String accNo);
	
	public CounterBean getCounterMember(String code);
	
	public int getCounterGroup(String group);
	
	public List getIssueDetailsMember(String code);
	
	public int getFineDetail(CounterMemberBean newbean);
	
	public CounterMemberBean getBookCountDoctype(String code);
	
	public CounterMemberBean getCounterBook(String code,String document,String mcode);
	
	public CounterBean getCounterIssueCheck(String code);
	
	public int getIssueCheck(String code,String mcode);
	
	public CounterBean getMemberLoad(String code,String code1,String availability);
	
	public String getValidDate(String code);
	
	public String getMemberCode(String code);
	
	public CounterMemberBean getIssueMasCheck(String code,String document);
	
	public String getReserveMasCheck(String code);
	
	public ReserveBean getReserveMssSelect(String code);
	
	public int getReserveMssDelete(CounterMemberBean newbean);
	
	public int getIssueMasInsert(CounterMemberBean newbean);
	
	public int getIssuedDetails(CounterMemberBean newbean);
	
	public CounterBean getDocmentReturn(CounterMemberBean newbean);
	
	public int getDocmentFine(CounterMemberBean newbean);
	
	public int getIssueMasSelect(CounterMemberBean newbean);
	
	public int getReserveMasSelect(CounterMemberBean newbean);
	
	public int getReserveMasSave(CounterMemberBean newbean);
	
	public int getReserveMasCount(String code);
	
	public int getMemberMasSelect(String code);
	
	public List getReserveDetailsMember(String code);
	
	public List getReserveDetailsBook(String code);
	
	public CounterMemberBean getIssueMas(String code);
	
	public CounterBean getNumberofDays(CounterMemberBean newbean);
	
	public CounterMemberBean getDdate(CounterMemberBean newbean);
	
	public String getLeaveDate(String code);
	
	public CounterMemberBean getUpdateRenewMasNofine(CounterMemberBean newbean);
	
	public int getUpdateRenewMas(CounterMemberBean newbean);
	
	public CounterBean getFineCall(CounterMemberBean newbean);
	
	public int getUpdateRenewMasFine(CounterMemberBean newbean);
	
	public int getRenewCheck(CounterMemberBean newbean);
	
	//--------------------Auto Complete-----------------------
	public ArrayList<MemberBean> getCounterAutoMemberIdSearch(String term);
	public ArrayList<bookbean> getCounterAutoAccessNoSearch(String term);

	
	
	//--------------Binding Books-----------------------------
	public List getLoadBinderName();
	
	public int getBindingBooksSave(BookBindingBean newbean);
	
	public List getBindingDisplay();
	
	public int getBindingBooksReturn(String accno);
	
	//--------------Transfer Books-----------------------------
	
	public List getLoadDeptName();
	
	public List getTransferedDeptName();
	
	public BookTransferBean getTransferBooksSave(BookTransferBean newbean);
	
	public BookTransferBean getbulkTransferBooksSave(BookTransferBean newbean);
	
	public BookTransferBean getTransferMax();
	
	public List getDeptTransferDisplay(String dept);
	
	public List getTransferDisplay();
	
	public int getTransferBooksReturn(String accno);
	
	public int getbulkTransferBooksReturn(String accno);
	
	
//------------------Member Paymet-----------------------
	
	public PaymentBean getPaymentMember(String code);	
	public int getPaymentBill_no();
	public int getAddPayment(PaymentBean bean);

}
