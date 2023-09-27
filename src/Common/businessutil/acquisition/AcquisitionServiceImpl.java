package Common.businessutil.acquisition;

import Common.businessutil.acquisition.AcquisitionService;
import Common.businessutil.acquisition.AcquisitionDao;
import Common.businessutil.acquisition.AcquisitionDao;
import Lib.Auto.Fine.Finebean;
import Lib.Auto.Indent_Invoice.IndentInvBean;
import Lib.Auto.Indent_Mas.IndentMasDetailsBean;
import Lib.Auto.Indent_Order.IndentOrderDetailsBean;
import Lib.Auto.Indent_Payment.IndentPaymentBean;
import Lib.Auto.OrdInvProcessing.orderbean;

import java.util.ArrayList;
import java.util.List;
public class AcquisitionServiceImpl implements AcquisitionService {
	
	private AcquisitionDao acquisitionDao;
	
	
	public int getOrdInvMax() {
		return acquisitionDao.findOrdInvMax();
	}
	
	public List getOrdInvSearchName(orderbean newbean) {
		return acquisitionDao.findOrdInvSearchName(newbean);
	}
	
	public orderbean getOrdInvSearch(String name) {
		return acquisitionDao.findOrdInvSearch(name);
	}
	
	public int getOrdInvSave(orderbean newbean) {
		return acquisitionDao.findOrdInvSave(newbean);
	}
	public int getOrdInvUpdate(orderbean newbean) {
		return acquisitionDao.findOrdInvUpdate(newbean);
	}
	public int getOrdInvDelete(String name) {
		return acquisitionDao.findOrdInvDelete(name);
	}
	
	
	
	/**
	 * Start Indent Master Form
	 * @return
	 */
	
	
	public List getSaveIndentMas(List Details)
	{		
		return getAcquisitionDao().findSaveIndentMas(Details);
	}
	
    public List getIndentMasSearch(IndentMasDetailsBean newbean)   {
		
		List results=getAcquisitionDao().findIndentMasSearch(newbean);
		
		
		
		List<Object> finalResults = null;
		if(results != null)
		{
			finalResults = new ArrayList<Object>();
			for(int i = 0; i < results.size(); i++)
			{
				
				if(newbean.getFlag().equals("Title"))  {
					
					IndentMasDetailsBean SearchDetails = new IndentMasDetailsBean();
					SearchDetails.setMemname(results.get(i).toString());
					finalResults.add(SearchDetails);
					
				}else {
				
				Object[] obj = (Object[])results.get(i);
				IndentMasDetailsBean SearchDetails = new IndentMasDetailsBean();
				
				    SearchDetails.setMemcode(obj[0].toString());
				    SearchDetails.setMemname(obj[1].toString());
					SearchDetails.setFlag(obj[2].toString());
					
					finalResults.add(SearchDetails);
				}
			}
		}
		return finalResults;
	}
    
	public IndentMasDetailsBean getTitleNo()    {
		
		return getAcquisitionDao().findTitleNo();
	}
	
	public List getFullViewIndentMas(String indentno)  {
		
		return getAcquisitionDao().findFullViewIndentMas(indentno);
	}
	
    public void getDeleteIndentMas(String indentno)  {
		
		 getAcquisitionDao().findDeleteIndentMas(indentno);
	}
	
	
	
	/**
	 * Start Indent Approval Form
	 * @return
	 */
    
    
	public List getFullViewIndentApproval(String indtno)   {
		
		return getAcquisitionDao().findFullViewIndentApproval(indtno);
	}
    
    
    
    public void getIndentApproval(List Details)  {
		
		 getAcquisitionDao().findIndentApproval(Details);
	}
    
    public void getIndentNotApproval(List Details)  {
		
		 getAcquisitionDao().findIndentNotApproval(Details);
	}
    

	/**
	 * Start Indent Order Form
	 * @return
	 */    
    
    public List getOrdIndentSearchName(IndentOrderDetailsBean newbean)   {
    	
    	List results= getAcquisitionDao().findOrdIndentSearchName(newbean);
    	
    	List<Object> finalResults = null;
		if(results != null)
		{
			finalResults = new ArrayList<Object>();
			for(int i = 0; i < results.size(); i++)
			{
				Object[] obj = (Object[])results.get(i);
				IndentOrderDetailsBean ordDetails = new IndentOrderDetailsBean();
				
				ordDetails.setIndentno(obj[0].toString());
				ordDetails.setTitle(obj[1].toString());
				ordDetails.setAdd1(obj[2].toString());
								
				finalResults.add(ordDetails);
			}
		}
		return finalResults;
    }
	
    public List getOrdSelectedIndent(IndentOrderDetailsBean newbean)    {
    	
    	return getAcquisitionDao().findOrdSelectedIndent(newbean);
    }
    
    public List getOrdIndentMasSave(List Details,IndentOrderDetailsBean newbean)      {
    	
    	return getAcquisitionDao().findOrdIndentMasSave(Details,newbean);    	
    }
    
    public boolean OrdIndentMasCheck(IndentOrderDetailsBean newbean) {
    	
    	return getAcquisitionDao().OrdIndentMasCheck(newbean);
    }
    
    public List getOrdIndentFullView(String OrderNo)  {
    	
    	return getAcquisitionDao().findOrdIndentFullView(OrderNo);
    }
    
    public void getIndentOrderDelete(String OrderNo,String TitleNo)  {
    	
    	getAcquisitionDao().findIndentOrderDelete(OrderNo,TitleNo);
    }
	
	
    /**
	 * Start Indent Invoice Form
	 * @return
	 */    
    
    public List getInvSearchOrdNo(IndentInvBean newbean)   {
    	
    	List results= getAcquisitionDao().findInvSearchOrdNo(newbean);
    	
    	List<Object> finalResults = null;
    	if(results != null)
		{
			finalResults = new ArrayList<Object>();
			for(int i = 0; i < results.size(); i++)
			{
				Object[] obj = (Object[])results.get(i);
				IndentInvBean invSearch = new IndentInvBean();
				
				invSearch.setOrdNo(obj[0].toString());
				invSearch.setAdd1(obj[1].toString());
				invSearch.setSupCode(Integer.valueOf(obj[2].toString()));
								
				finalResults.add(invSearch);
			}
		}
		return finalResults;
    }
    
    public List getInvOrdCheckList(IndentInvBean newbean)
    {
    	return getAcquisitionDao().findInvOrdCheckList(newbean);    	
    }
    
    public List getIndentInvoiceSave(List Details,IndentInvBean newbean)
    {
    	return getAcquisitionDao().findIndentInvoiceSave(Details,newbean);        	
    }
    
    public boolean IndentInvoiceCheck(IndentInvBean newbean)
    {
    	return getAcquisitionDao().IndentInvoiceCheck(newbean);    	    
    }
    
    public List getIndentInvoiceFullView(String InvoiceNo)  {
    	
    	return getAcquisitionDao().findIndentInvoiceFullView(InvoiceNo);    	    	
    }
    
    public void getIndentInvoiceDelete(String InvoiceNo,String TitleNo)
    {
    	getAcquisitionDao().findIndentInvoiceDelete(InvoiceNo,TitleNo);    	    	
    }
    
    
    /**
	 * Start Indent Payment Form
	 * @return
	 */    
    
    public IndentPaymentBean getPaymentMax()  {
    	
    	return getAcquisitionDao().findPaymentMax();	
    }

    public List getPaymentIndentSearch(IndentPaymentBean newbean)  {
    	
    	return   getAcquisitionDao().findPaymentIndentSearch(newbean);
    }

    public List getPaymentIndentInvoice(IndentPaymentBean newbean)  {
    	
    	return   getAcquisitionDao().findPaymentIndentInvoice(newbean);
    }

    public void getPaymentIndentDetailsUpdate(List Details)   {
    	
    	getAcquisitionDao().findPaymentIndentDetailsUpdate(Details);
    }

    public List getPaymentIndentMasSave(IndentPaymentBean newbean) {
    	
    	return   getAcquisitionDao().findPaymentIndentMasSave(newbean);	
    }

    public List getPaymentIndentDetailsSearch(String pmtno) {

    	return   getAcquisitionDao().findPaymentIndentDetailsSearch(pmtno);	
    }

    public void getPaymentIndentDelete(String pmtno)  {
    	
    	getAcquisitionDao().findPaymentIndentDelete(pmtno);
    }

    
    

    
	
	public AcquisitionDao getAcquisitionDao() {
		return acquisitionDao;
	}

	public void setAcquisitionDao(AcquisitionDao acquisitionDao) {
		this.acquisitionDao = acquisitionDao;
	}
		
	
	
}