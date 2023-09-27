package Common.businessutil.circulation;

import java.util.ArrayList;
import java.util.List;

import Lib.Auto.Binding_Books.BookBindingBean;
import Lib.Auto.Counter.CounterBean;
import Lib.Auto.Counter.CounterMemberBean;
import Lib.Auto.Counter.ReserveBean;
import Lib.Auto.Designation.DesignationBean;
import Lib.Auto.Payment.PaymentBean;
import Lib.Auto.Transfer_Books.BookTransferBean;
import Lib.Auto.Member.MemberBean;
import Lib.Auto.Book.bookbean;


public interface CirculationDao {
	
	//public CounterBean deletetransaction(String code);
	
	public String findCheckEligible(String accNo);
	
	public CounterBean findCounterMember(String code);
	
	public int findCounterGroup(String group);
	
	public List findIssueDetailsMember(String code);
	
	public int findFineDetail(CounterMemberBean newbean);
	
	public CounterMemberBean findBookCountDoctype(String code);
	
	public CounterMemberBean findCounterBook(String code,String document,String mcode);
	
	public CounterBean findCounterIssueCheck(String code);
	
	public int findIssueCheck(String code,String mcode);
	
	public CounterBean findMemberLoad(String code,String code1,String availability);
		
	public String findValidDate(String code);
	
	public String findMemberCode(String code);
	
	public CounterMemberBean findIssueMasCheck(String code,String document);
	
	public String findReserveMasCheck(String code);
	
	public ReserveBean findReserveMssSelect(String code);
	
	public int findReserveMssDelete(CounterMemberBean newbean);
	
	public int findIssueMasInsert(CounterMemberBean newbean);
	
	public int findIssuedDetails(CounterMemberBean newbean);
	
	
	public CounterBean findDocmentReturn(CounterMemberBean newbean);
	
	
	public int findDocmentFine(CounterMemberBean newbean);
	
	public int findIssueMasSelect(CounterMemberBean newbean);
	
	public int findReserveMasSelect(CounterMemberBean newbean);
	
	public int findReserveMasSave(CounterMemberBean newbean);
	
	public int findReserveMasCount(String code);
	
	public int findMemberMasSelect(String code);
	
	public List findReserveDetailsMember(String code);
	
	public List findReserveDetailsBook(String code);
	
	public CounterMemberBean findIssueMas(String code);
	
	public CounterBean findNumberofDays(CounterMemberBean newbean);
	
	public CounterMemberBean findDdate(CounterMemberBean newbean);
	
	public String findLeaveDate(String code);
	
	public CounterMemberBean findUpdateRenewMasNofine(CounterMemberBean newbean);
	
	public int findUpdateRenewMas(CounterMemberBean newbean);
	
	public CounterBean findFineCall(CounterMemberBean newbean);
	
	
	public int findUpdateRenewMasFine(CounterMemberBean newbean);
	
	public int findRenewCheck(CounterMemberBean newbean);
	
	//-----------------Auto Complete-----------------------
	public ArrayList<MemberBean> findCounterAutoMemberIdSearch(String term);
	public ArrayList<bookbean> findCounterAutoAccessNoSearch(String term);
	
	
	//------------------Binding Books-----------------------
	
	public List findLoadBinderName();
	
	public int findBindingBooksSave(BookBindingBean newbean);
	
	public List findBindingDisplay();
	
	public int findBindingBooksReturn(String name);
	
	//------------------Transfer Books-----------------------
	
	public List findLoadDeptName();
	
	public List findTransferedDeptName();
	
	public BookTransferBean findTransferBooksSave(BookTransferBean newbean);
	
	public BookTransferBean findbulkTransferBooksSave(BookTransferBean newbean);
	
	public BookTransferBean findTransferMax();
	
	public List findDeptTransferDisplay(String dept);
	
	public List findTransferDisplay();
	
	public int findTransferBooksReturn(String name);
	
	public int findbulkTransferBooksReturn(String name);
	
//------------------Member Paymet-----------------------
	
	public PaymentBean findPaymentMember(String code);
	
	public int findPaymentBill_no();
	
	public int findAddPayment(PaymentBean bean);
	
}
