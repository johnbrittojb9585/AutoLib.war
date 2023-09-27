package Common.businessutil.acquisition;
import Lib.Auto.Indent_Invoice.IndentInvBean;
import Lib.Auto.Indent_Mas.IndentMasDetailsBean;
import Lib.Auto.Indent_Order.IndentOrderDetailsBean;
import Lib.Auto.Indent_Payment.IndentPaymentBean;
import Lib.Auto.OrdInvProcessing.orderbean;
import java.util.List;

public interface AcquisitionService {
	
	public int getOrdInvMax();
	
	public List getOrdInvSearchName(orderbean newbean);
	public orderbean getOrdInvSearch(String name);
	public int getOrdInvSave(orderbean newbean);
	public int getOrdInvUpdate(orderbean newbean);
	public int getOrdInvDelete(String name);
	
	
	/**
	 * Start Indent Master Form
	 * @param Details
	 * @return
	 */
	
	public List getSaveIndentMas(List Details);
	public List getIndentMasSearch(IndentMasDetailsBean newbean);	
	public IndentMasDetailsBean getTitleNo();
	public List getFullViewIndentMas(String indentno);
	public void getDeleteIndentMas(String indentno);
	
	
	/**
	 * 
	 * Start Indent Approval Form
	 * 
	 */
	
	public List getFullViewIndentApproval(String indtno);	
	public void getIndentApproval(List Details);
	public void getIndentNotApproval(List Details);
	
	
	/**
	 * 
	 * Start Indent Order Form
	 * 
	 */
	
	public List getOrdIndentSearchName(IndentOrderDetailsBean newbean);	
	public List getOrdSelectedIndent(IndentOrderDetailsBean newbean);	
	public List getOrdIndentMasSave(List Details,IndentOrderDetailsBean newbean);
	public boolean OrdIndentMasCheck(IndentOrderDetailsBean newbean);	
	public List getOrdIndentFullView(String OrderNo);
	public void getIndentOrderDelete(String OrderNo,String TitleNo);
	
	
	/**
	 * 
	 * Start Indent Invoice Form
	 * 
	 */
	
	public List getInvSearchOrdNo(IndentInvBean newbean);	
	public List getInvOrdCheckList(IndentInvBean newbean);	
	public List getIndentInvoiceSave(List Details,IndentInvBean beanobject);
	public boolean IndentInvoiceCheck(IndentInvBean newbean);	
	public List getIndentInvoiceFullView(String InvoiceNo);
	public void getIndentInvoiceDelete(String InvoiceNo,String TitleNo);

	/**
	 * 
	 * Start Indent Payment Form
	 * 
	 */
	
	public IndentPaymentBean getPaymentMax();
	public List getPaymentIndentSearch(IndentPaymentBean newbean);
	public List getPaymentIndentInvoice(IndentPaymentBean newbean);	
	public void getPaymentIndentDetailsUpdate(List Details);	
	public List getPaymentIndentMasSave(IndentPaymentBean newbean);	
	public List getPaymentIndentDetailsSearch(String pmtno);
	public void getPaymentIndentDelete(String pmtno);
	


	
}
