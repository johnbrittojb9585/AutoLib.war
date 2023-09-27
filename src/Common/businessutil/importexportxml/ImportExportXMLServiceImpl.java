/*
 *                 Autolib License Notice
 *
 * The contents of this file are subject to the Autolib  License
 * Version 1.0 (the "License"). You may not use this file except in
 * compliance with the License.The Initial Developer of the Original Code is
 * Autolib Software Systems.
 * Portions Copyright 1998-2010.Autolib Software Systems All Rights Reserved.
 *
 *
 */
package Common.businessutil.importexportxml;

import java.util.List;

import Lib.Auto.Stock.StockBean;

/** The Class ImportExportXMLServiceImpl. */
public class ImportExportXMLServiceImpl implements ImportExportXMLService
{
	private ImportExportXMLDao importExportXMLDao;

	/**
	 * @return Returns the importExportXMLDao.
	 */
	public ImportExportXMLDao getImportExportXMLDao( )
	{
		return importExportXMLDao;
	}

	/**
	 * @param importExportXMLDao The importExportXMLDao to set.
	 */
	public void setImportExportXMLDao(ImportExportXMLDao importExportXMLDao)
	{
		this.importExportXMLDao = importExportXMLDao;
	}
	
	
	public List getAccessionWiseReportList(String query) 
	{
		return importExportXMLDao.getAccessionWiseReportList(query);
	}
	
	public List getBindingReport(String query) 
	{
		return importExportXMLDao.getBindingReport(query);
	}
	
	
	public List getTransFineCumulativeList(String query) 
	{
		return importExportXMLDao.getTransFineCumulativeList(query);
	}
	
	public List getCounterReportList(String query, String flag,String flag1)
	{
		return importExportXMLDao.getCounterReportList(query,flag,flag1);
	}
	
	public List getCustomReportList(String query, String[] items)
	{
		return importExportXMLDao.getCustomReportList(query, items);
	}
	
	public List getStatisticsWiseReportList(String query) 
	{
		return importExportXMLDao.getStatisticsWiseReportList(query);
	}
	public List getStatisticsDetailsReportList(String query) 
	{
		return importExportXMLDao.findStatisticsDetailsReportList(query);
	}
	public List getLibraryCollectionExcel(String query) 
	{
		return importExportXMLDao.findLibraryCollectionExcel(query);
	}
	public List getBibliographyExcel(String query) 
	{
		return importExportXMLDao.findBibliographyExcel(query);
	}
	public List getMemberExcelReport(String query) 
	{
		
		return importExportXMLDao.findMemberExcelReport(query);
	}
	public List getPaymentInfoExportWizard(String query,String flag) 
	{
		return importExportXMLDao.findPaymentInfoExportWizard(query,flag);
	}
	public List getFineCollectedExportWizard(String query) 
	{
		return importExportXMLDao.findFineCollectedExportWizard(query);
	}
	public List getTitleWiseReportList(String query) 
	{
		return importExportXMLDao.getTitleWiseReportList(query);
	}
	public List getGateRegisterReportList(String query) 
	{
		return importExportXMLDao.findGateRegisterReportList(query);
	}
	
	public List getGateRegisterReportStatisticsList(String query) 
	{
		return importExportXMLDao.findGateRegisterReportStatisticsList(query);
	}
	
	public List getLibrary_Login_Excel(String query) 
	{
		return importExportXMLDao.findLibrary_Login_Excel(query);
	}
	
	public List getLibrary_Login_Excel_Statistics(String query) 
	{
		return importExportXMLDao.findLibrary_Login_Excel_Statistics(query);
	}
	public List getTransferReportList(String query) 
	{
		return importExportXMLDao.findTransferReportList(query);
	}
	public List getMissResourceExcel(String query) 
	{
		return importExportXMLDao.findMissResourceExcel(query);
	}
	public List getFreqUsedResourceExcel(String query,String type) 
	{
		return importExportXMLDao.findFreqUsedResourceExcel(query,type);
	}
	public List getJournalListExcel(String query) 
	{
		return importExportXMLDao.findJournalListExcel(query);
	}
	public List getCounterBreakupReportList(String query, String flag)
	{
		return importExportXMLDao.getCounterBreakupReportList(query,flag);
	}
	
	public List getStockMasDisplayList(StockBean SB) 
	{
		return importExportXMLDao.findStockMasDisplayList(SB);
	}
	
	public List getQBReportList(String query) 
	{
		return importExportXMLDao.findQBReportList(query);
	}
	
	public List getFreqUsedMemberExcel(String query,String type)
	{
		return importExportXMLDao.findFreqUsedMemberExcel(query,type);
	}
}
