package Common.businessutil.circulation;

import java.util.ArrayList;
import java.util.List;

import Lib.Auto.Binding_Books.BookBindingBean;
import Lib.Auto.Counter.CounterBean;
import Lib.Auto.Counter.CounterMemberBean;
import Lib.Auto.Counter.ReserveBean;
import Lib.Auto.Course.CourseBean;
import Lib.Auto.Payment.PaymentBean;
import Lib.Auto.Transfer_Books.BookTransferBean;
import Lib.Auto.Member.MemberBean;
import Lib.Auto.Book.bookbean;


public class CirculationServiceImpl implements CirculationService {
	
	private CirculationDao circulationDao;

	public CirculationServiceImpl() {
	}
//member
	//public String deletetransaction(String code) {
		//return circulationDao.deletetransaction(code);
	
	//}
	public String getCheckEligible(String accNo) {
		return circulationDao.findCheckEligible(accNo);
	}
	
	public CounterBean getCounterMember(String code) {
		return circulationDao.findCounterMember(code);
	}
	public int getCounterGroup(String group) {
		return circulationDao.findCounterGroup(group);
	}
	
	public List getIssueDetailsMember(String group) {
		return circulationDao.findIssueDetailsMember(group);
	}
	
	public int getFineDetail(CounterMemberBean newbean){
		return circulationDao.findFineDetail(newbean);
	}
	
	public CounterMemberBean getBookCountDoctype(String group) {
		return circulationDao.findBookCountDoctype(group);
	}
	
	public CounterMemberBean getCounterBook(String code,String document,String mcode) {
		return circulationDao.findCounterBook(code,document,mcode);
	}
	
	public CounterBean getCounterIssueCheck(String code) {
		return circulationDao.findCounterIssueCheck(code);
	}
	
	public int getIssueCheck(String code,String mcode) {
		return circulationDao.findIssueCheck(code,mcode);
	}
	
	public CounterBean getMemberLoad(String group,String code, String availability) {
		return circulationDao.findMemberLoad(group,code,availability);
	}
	
	public String getValidDate(String code) {
		return circulationDao.findValidDate(code);
	}
	
	public String getMemberCode(String code) {
		return circulationDao.findMemberCode(code);
	}
	
	public CounterMemberBean getIssueMasCheck(String code,String document) {
		return circulationDao.findIssueMasCheck(code,document);
	}
	
	
	public String getReserveMasCheck(String code) {
		return circulationDao.findReserveMasCheck(code);
	}
	
	public ReserveBean getReserveMssSelect(String code) {
		return circulationDao.findReserveMssSelect(code);
	}
	
	public int getReserveMssDelete(CounterMemberBean newbean) {
		return circulationDao.findReserveMssDelete(newbean);
	}
	
	public int getIssueMasInsert(CounterMemberBean newbean) {
		return circulationDao.findIssueMasInsert(newbean);
	}
	public int getIssuedDetails(CounterMemberBean newbean) {
		return circulationDao.findIssuedDetails(newbean);
	}
	
	
	public CounterBean getDocmentReturn(CounterMemberBean newbean) {
		return circulationDao.findDocmentReturn(newbean);
	}
	
	public int getDocmentFine(CounterMemberBean newbean) {
		return circulationDao.findDocmentFine(newbean);
	}
	
	public int getIssueMasSelect(CounterMemberBean newbean) {
		return circulationDao.findIssueMasSelect(newbean);
	}
	
	public int getReserveMasSelect(CounterMemberBean newbean) {
		return circulationDao.findReserveMasSelect(newbean);
	}
	
	public int getReserveMasSave(CounterMemberBean newbean) {
		return circulationDao.findReserveMasSave(newbean);
	}
	
	public int getReserveMasCount(String code) {
		return circulationDao.findReserveMasCount(code);
	}
	
	public int getMemberMasSelect(String code) {
		return circulationDao.findMemberMasSelect(code);
	}
	
	public List getReserveDetailsMember(String code) {
		return circulationDao.findReserveDetailsMember(code);
	}
	
	public List getReserveDetailsBook(String code) {
		return circulationDao.findReserveDetailsBook(code);
	}
	
	public CounterMemberBean getIssueMas(String code) {
		return circulationDao.findIssueMas(code);
	}
	
	public CounterBean getNumberofDays(CounterMemberBean newbean) {
		return circulationDao.findNumberofDays(newbean);
	}
	
	public CounterMemberBean getDdate(CounterMemberBean newbean) {
		return circulationDao.findDdate(newbean);
	}
	
	public String getLeaveDate(String code) {
		return circulationDao.findLeaveDate(code);
	}
	
	public CounterMemberBean getUpdateRenewMasNofine(CounterMemberBean newbean) {
		return circulationDao.findUpdateRenewMasNofine(newbean);
	}
	
	
	public int getUpdateRenewMas(CounterMemberBean newbean) {
		return circulationDao.findUpdateRenewMas(newbean);
	}
	
	public CounterBean getFineCall(CounterMemberBean newbean) {
		return circulationDao.findFineCall(newbean);
	}
	
	
	public int getUpdateRenewMasFine(CounterMemberBean newbean) {
		return circulationDao.findUpdateRenewMasFine(newbean);
	}
	
	
	public int getRenewCheck(CounterMemberBean newbean) {
		return circulationDao.findRenewCheck(newbean);
	}
	
	//----------------Auto Complete---------------------
	public ArrayList<MemberBean> getCounterAutoMemberIdSearch(String term) {
		 
		return circulationDao.findCounterAutoMemberIdSearch(term);
		}

	public ArrayList<bookbean> getCounterAutoAccessNoSearch(String term) {
		 
		return circulationDao.findCounterAutoAccessNoSearch(term);
		}

	
	
	//------------------Binding Books----------------------
	
	public List getLoadBinderName() {
		return circulationDao.findLoadBinderName();
	}
	
	public int getBindingBooksSave(BookBindingBean newbean) {
		return circulationDao.findBindingBooksSave(newbean);
	}
	
	public List getBindingDisplay() {
		return circulationDao.findBindingDisplay();
	}
	
	public int getBindingBooksReturn(String accno) {
		return circulationDao.findBindingBooksReturn(accno);
	}
	
	//--------------Transfer Books-----------------------------
	
	public List getLoadDeptName() {
		return circulationDao.findLoadDeptName();
	}
	
	public List getTransferedDeptName() {
		return circulationDao.findTransferedDeptName();
	}
	
	
	public BookTransferBean getTransferBooksSave(BookTransferBean newbean) {
		return circulationDao.findTransferBooksSave(newbean);
	}
	
	public BookTransferBean getbulkTransferBooksSave(BookTransferBean newbean) {
		return circulationDao.findbulkTransferBooksSave(newbean);
	}
	
	public BookTransferBean getTransferMax() {
		return circulationDao.findTransferMax();
	}
	
	public List getDeptTransferDisplay(String dept) {
		return circulationDao.findDeptTransferDisplay(dept);
	}
	
	public List getTransferDisplay() {
		return circulationDao.findTransferDisplay();
	}
	public int getTransferBooksReturn(String accno) {
		return circulationDao.findTransferBooksReturn(accno);
	}
	public int getbulkTransferBooksReturn(String accno) {
		return circulationDao.findbulkTransferBooksReturn(accno);
	}
//	Payment member
	
	public PaymentBean getPaymentMember(String code) {
		return circulationDao.findPaymentMember(code);
	}
	
	public int getPaymentBill_no() {
		return circulationDao.findPaymentBill_no();
	}
	
	public int getAddPayment(PaymentBean bean) {
		return circulationDao.findAddPayment(bean);
	}
		
	public CirculationDao getCirculationDao() {
		return circulationDao;
	}

	public void setCirculationDao(CirculationDao circulationDao) {
		this.circulationDao = circulationDao;
	}


	

	
}
