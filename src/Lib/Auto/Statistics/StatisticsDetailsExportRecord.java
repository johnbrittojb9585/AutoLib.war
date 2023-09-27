package Lib.Auto.Statistics;
import java.util.HashMap;
import java.util.Map;

import Common.businessutil.importexportexcel.ExportProcessor;
import Common.businessutil.importexportxml.ImportExportXML;
import Common.businessutil.reports.ReportQueryUtill;


public class StatisticsDetailsExportRecord implements ExportProcessor 
{


	
	private static final long serialVersionUID = 1L;

	String fromAccNo,toAccNo,doc,type;
	


	StatisticsDetailsExportRecord(Map recordProcessorMap)
	{
		fromAccNo = (String)recordProcessorMap.get("fromAccNo");
		toAccNo = (String)recordProcessorMap.get("toAccNo");
		doc = (String)recordProcessorMap.get("DocType");
		type = (String)recordProcessorMap.get("ReportType");
	}
	
	public Map getTitleDetails()
	{
		Map<Object,Object> title=new HashMap<Object,Object>();
		title.put("title","Report Name :");
		title.put("titleValue",ReportQueryUtill.Statistics_Title);
		title.put("subTitle1","From :");
		title.put("subTitle2","To :");
		title.put("subTitle3","DocType :");
		
		
		title.put("value1",fromAccNo);
		title.put("value2",toAccNo);
		title.put("value3",doc);
		title.put("count",3);
		return title;
	}

	public String getExcelFileName( )
	{
		return "Statistics-Detailed-Report_Excel.csv";
	}

	public int getHeaderCount()
	{
		return 33;
	}

	public String[] getExcelHeader()
	{
		return new String[]
		{ "Access No","Author Name","Title","Call No","Publisher","Department","Net Price"};
	}

	
	
	public String[] setExportExcelDataMap(Object entity)
	{	
		ImportExportXML std = (ImportExportXML) entity;
		String[] fields = new String[getHeaderCount()];
		fields[0] = std.getAccessNo().toString();
		fields[1] = std.getAuthorName().toString();
		fields[2] = std.getTitle().toString();
		fields[3] = std.getCallNo().toString();
		fields[4] = std.getPublisherName().toString();
		fields[5] = std.getDeptName().toString();
//		fields[6] = std.getDiscount().toString();
		fields[6] = std.getPrice().toString();
		return fields;
	}

}
