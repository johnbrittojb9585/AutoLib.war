package Common.businessutil.acquisition;

import java.util.List;

import Lib.Auto.Indent_Invoice.IndentInvBean;
import Lib.Auto.Indent_Mas.IndentMasDetailsBean;
import Lib.Auto.Indent_Order.IndentOrderDetailsBean;
import Lib.Auto.Indent_Payment.IndentPaymentBean;
import Lib.Auto.OrdInvProcessing.orderbean;

public interface AcquisitionDao {
	
	public int findOrdInvMax();	
	public List findOrdInvSearchName(orderbean newbean);	
	public orderbean findOrdInvSearch(String name);
	public int findOrdInvSave(orderbean newbean);
	public int findOrdInvUpdate(orderbean newbean);
	public int findOrdInvDelete(String name);
	
	
	/**
	 * Start Indent Master Form
	 */
	
	public List findSaveIndentMas(List Details);
	public List findIndentMasSearch(IndentMasDetailsBean newbean);
	public IndentMasDetailsBean findTitleNo();
	public List findFullViewIndentMas(String indentno);
	public void findDeleteIndentMas(String indentno);
	

	/**
	 * Start Indent Approval Form
	 */	
	
	public List findFullViewIndentApproval(String indtno);
	public void findIndentApproval(List Details);
	public void findIndentNotApproval(List Details);
	
	
	/**
	 * Start Indent Order Form
	 */	
		
	public List findOrdIndentSearchName(IndentOrderDetailsBean newbean);
	public List findOrdSelectedIndent(IndentOrderDetailsBean newbean);
	public List findOrdIndentMasSave(List Details,IndentOrderDetailsBean newbean);
	public boolean OrdIndentMasCheck(IndentOrderDetailsBean newbean);
	public List findOrdIndentFullView(String OrderNo);
	public void findIndentOrderDelete(String OrderNo,String TitleNo);
	
	
	/**
	 * Start Indent Invoice Form
	 */	
	
	public List findInvSearchOrdNo(IndentInvBean newbean);
	public List findInvOrdCheckList(IndentInvBean newbean);
	public List findIndentInvoiceSave(List Details,IndentInvBean newbean);
	public boolean IndentInvoiceCheck(IndentInvBean newbean);
	public List findIndentInvoiceFullView(String InvoiceNo);
	public void findIndentInvoiceDelete(String InvoiceNo,String TitleNo);

     /**
	 * Start Indent Payment Form
	 */	
	
	public IndentPaymentBean findPaymentMax();
	public List findPaymentIndentSearch(IndentPaymentBean newbean);
	public List findPaymentIndentInvoice(IndentPaymentBean newbean);
	public void findPaymentIndentDetailsUpdate(List Details);
	public List findPaymentIndentMasSave(IndentPaymentBean newbean);
	public List findPaymentIndentDetailsSearch(String pmtno);
	public void findPaymentIndentDelete(String pmtno);
	
	
	
}