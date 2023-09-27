package Lib.Auto.Transfer_Report;

import java.util.HashMap;
import java.util.Map;

import Common.businessutil.importexportexcel.ExportProcessor;
import Common.businessutil.importexportxml.ImportExportXML;
import Common.businessutil.reports.ReportQueryUtill;

public class TransferExportRecord implements ExportProcessor{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	String from;
	
	String to;
	
	String doc,type;

	TransferExportRecord(Map recordProcessorMap)
	{
		from = (String)recordProcessorMap.get("from");
		to = (String)recordProcessorMap.get("to");
		doc = (String)recordProcessorMap.get("DocType");
		type = (String)recordProcessorMap.get("ReportType");
	}
	
	public Map getTitleDetails()
	{
		Map<Object,Object> title=new HashMap<Object,Object>();
		title.put("title","Report Name :");
		if(type.equalsIgnoreCase("TRANSFERED"))
			title.put("titleValue",ReportQueryUtill.Transfer_Reports_Title);
		else
			title.put("titleValue",ReportQueryUtill.Re_Transfer_Reports_Title);
		title.put("subTitle1","From :");
		title.put("subTitle2","To :");
		title.put("subTitle3","DocType :");
		
		title.put("value1",from);
		title.put("value2",to);
		title.put("value3",doc);
		title.put("count",3);//count value should be equal to value count
		return title;
	}

	public String getExcelFileName( )
	{
		if(type.equalsIgnoreCase("TRANSFERED"))
			return ReportQueryUtill.Transfer_Reports_Title+"_Excel.csv";
		else
			return ReportQueryUtill.Re_Transfer_Reports_Title+"_Excel.csv";
	}

	public int getHeaderCount()
	{
		return 33;
	}

	public String[] getExcelHeader()
	{
		if(type.equalsIgnoreCase("TRANSFERED"))
			return new String[]{ "Dept Name", "Access No", "Doc Type","Transfered Date", "Title", "Author Name"};
		else
			return new String[]{ "Dept Name", "Access No", "Doc Type","Transfered Date", "Re-Transfered Date", "Title", "Author Name"};
	}

	public String[] setExportExcelDataMap(Object entity)
	{		
		ImportExportXML std = (ImportExportXML) entity;
		String[] fields = new String[getHeaderCount()];
		
		fields[0] = std.getDeptName().toString();
		fields[1] = std.getAccessNo().toString();
		fields[2] = std.getDocument().toString();
		fields[3] = std.getOrderDate().toString();
		if(!type.equalsIgnoreCase("TRANSFERED"))
		{
			fields[4] = std.getRecoverDate().toString();
			fields[5] = std.getTitle().toString();
			fields[6] = std.getAuthorName().toString();
		}
		else
		{
			fields[4] = std.getTitle().toString();
			fields[5] = std.getAuthorName().toString();
		}
		
			
		return fields;
	}

}
