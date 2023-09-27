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


/** The Interface ImportExportXMLService. */
public interface ImportExportXMLService
{
	public List getAccessionWiseReportList(String query);
	
	public List getBindingReport(String query);
		
	public List getTransFineCumulativeList(String query);
	
	public List getCounterReportList(String query, String flag,String flag1);
	
	public List getCustomReportList(String query, String[] items);
	
	public List getStatisticsWiseReportList(String query);
	
	public List getStatisticsDetailsReportList(String query);
	
	public List getLibraryCollectionExcel(String query);
	
	public List getBibliographyExcel(String query);
	
	public List getMemberExcelReport(String query);
	
	public List getPaymentInfoExportWizard(String query,String flag);
	
	public List getFineCollectedExportWizard(String query);
	
	public List getTitleWiseReportList(String sb);
	
	public List getGateRegisterReportList(String query);
	
	public List getGateRegisterReportStatisticsList(String query);
	
	public List getLibrary_Login_Excel(String query);
	
	public List getLibrary_Login_Excel_Statistics(String query);
		
	public List getTransferReportList(String query);
	
	public List getMissResourceExcel(String sb);
	
	public List getFreqUsedResourceExcel(String sb,String type);
	
	public List getJournalListExcel(String sb);

	public List getCounterBreakupReportList(String query, String flag);
	
	public List getStockMasDisplayList(StockBean sB);
	
	public List getQBReportList(String query);
	
	public List getFreqUsedMemberExcel(String sb,String type);	
	
	
}
