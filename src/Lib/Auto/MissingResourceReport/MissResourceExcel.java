package Lib.Auto.MissingResourceReport;

import java.util.HashMap;
import java.util.Map;

import Common.businessutil.importexportexcel.ExportProcessor;
import Common.businessutil.importexportxml.ImportExportXML;
import Common.businessutil.reports.ReportQueryUtill;

public class MissResourceExcel implements ExportProcessor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	String fromAccNo,toAccNo,doc,avail;
	
	

	public MissResourceExcel(Map recordProcessorMap)
	{
		fromAccNo = (String)recordProcessorMap.get("From_Accno");
		toAccNo = (String)recordProcessorMap.get("To_Accno");
		doc = (String)recordProcessorMap.get("Type");
		avail = (String)recordProcessorMap.get("avail");

	}
	
	public Map getTitleDetails()
	{
		Map<Object,Object> title=new HashMap<Object,Object>();
		title.put("title","Report Name :");
		title.put("titleValue",ReportQueryUtill.MissResourceReport_Title);
		title.put("subTitle1","From :");
		title.put("subTitle2","To :");
		title.put("subTitle3","DocType :");
		title.put("subTitle4", "Available:");
		
		title.put("value1",fromAccNo);
		title.put("value2",toAccNo);
		title.put("value3",doc);
		title.put("value4",avail);
		title.put("count",4);
		return title;
	}

	public String getExcelFileName( )
	{
		return ReportQueryUtill.MissResourceReport_Title + "_Excel.csv";
	}

	public int getHeaderCount()
	{
		return 33;
	}

	public String[] getExcelHeader()
	{
		
		return new String[]
		{ "Access No","Document","Status","Missing Date"};
	
		
	}

	public String[] setExportExcelDataMap(Object entity)
	{
		
		ImportExportXML std = (ImportExportXML) entity;
		String[] fields = new String[getHeaderCount()];
		fields[0] = std.getAccessNo().toString();
		fields[1] = std.getDocument().toString();
		fields[2] = std.getStatus().toString();
		fields[3] = std.getRecoverDate().toString();

		return fields;
	}

}
