
package Lib.Auto.BindingReport;

import java.util.HashMap;
import java.util.Map;

import Common.businessutil.importexportexcel.ExportProcessor;
import Common.businessutil.importexportxml.ImportExportXML;
import Common.businessutil.reports.ReportQueryUtill;


/**
 * The Class DateWiseExportRecord.
 */

public class BindingReportXL implements ExportProcessor
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	String fromAccNo;
	
	String toAccNo;
	
	String documentType;

	BindingReportXL(Map recordProcessorMap)
	{
		fromAccNo = (String)recordProcessorMap.get("fromAccNo");
		toAccNo = (String)recordProcessorMap.get("toAccNo");
		documentType = (String)recordProcessorMap.get("documentType");
	}
	
	public Map getTitleDetails()
	{
		Map<Object,Object> title=new HashMap<Object,Object>();
		title.put("title","Report Name :");
		title.put("titleValue",ReportQueryUtill.Binding_report_Title);
		title.put("subTitle1","From :");
		title.put("subTitle2","To :");
		title.put("subTitle3","Document Type :");
		
		title.put("value1",fromAccNo);
		title.put("value2",toAccNo);
		title.put("value3",documentType);
		title.put("count",3);//count value should be equal to value count
		return title;
	}

	public String getExcelFileName( )
	{
		return ReportQueryUtill.Binding_report_Title + "_Excel.csv";
	}

	public int getHeaderCount()
	{
		return 33;
	}

	public String[] getExcelHeader()
	{
		return new String[]
		{ "Access No", "Title", "Author Name","Binder Name" , "Price", "Sending Date","Document Type" };
	}

	public String[] setExportExcelDataMap(Object entity)
	{		
		
		ImportExportXML std = (ImportExportXML) entity;
		String[] fields = new String[getHeaderCount()];
		fields[0] = std.getAccessNo().toString();
		fields[1] = std.getTitle().toString();
		fields[2] = std.getAuthorName().toString();
		fields[3] = std.getBinderName().toString();
		fields[4] = std.getAcceptedPrice().toString();
		fields[5] = std.getSendingDate().toString();
		fields[6] = std.getDocument().toString();
		
		
		return fields;
	}
}





