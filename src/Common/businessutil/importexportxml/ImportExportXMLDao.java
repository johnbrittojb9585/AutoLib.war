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


/** The Interface ImportExportXMLDao. */
public interface ImportExportXMLDao
{
	public List getAccessionWiseReportList(String query);
	
	public List getBindingReport(String query);
	
	public List getTransFineCumulativeList(String query);
	
	public List getCounterReportList(String query, String flag,String flag1);
	
	public List getCustomReportList(String query, String[] items);
	
	public List getStatisticsWiseReportList(String query);
	
	public List findStatisticsDetailsReportList(String query);
		
	public List findLibraryCollectionExcel(String query);
	
	public List findBibliographyExcel(String query);
	
	public List findMemberExcelReport(String query);
	
	public List findPaymentInfoExportWizard(String query,String flag);
	
	public List findFineCollectedExportWizard(String query);
	
	public List getTitleWiseReportList(String query);
	
	public List findGateRegisterReportList(String query);
	
	public List findGateRegisterReportStatisticsList(String query);
	
	public List findLibrary_Login_Excel(String query);
	
	public List findLibrary_Login_Excel_Statistics(String query);
	
	public List findTransferReportList(String query);
	
	public List findMissResourceExcel(String query);
	
	public List findFreqUsedResourceExcel(String query,String type);
	
	public List findJournalListExcel(String query);
	
	public List getCounterBreakupReportList(String query, String flag);
	
	public List findStockMasDisplayList(StockBean sB);
	
	public List findQBReportList(String query);
	
	public List findFreqUsedMemberExcel(String query,String type);

}
