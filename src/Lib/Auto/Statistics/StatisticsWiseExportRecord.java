package Lib.Auto.Statistics;
import java.util.HashMap;
import java.util.Map;

import Common.businessutil.importexportexcel.ExportProcessor;
import Common.businessutil.importexportxml.ImportExportXML;
import Common.businessutil.reports.ReportQueryUtill;


public class StatisticsWiseExportRecord implements ExportProcessor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	String fromAccNo,toAccNo,doc,type;
	
	

	StatisticsWiseExportRecord(Map recordProcessorMap)
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
		return ReportQueryUtill.Statistics_Title + "_Excel.csv";
	}

	public int getHeaderCount()
	{
		return 33;
	}

	public String[] getExcelHeader()
	{
		System.out.println("########");
		return new String[]
		{ type,"No.OfBooks","No.OfTitles","Price","Net Price"};
	
		
	}

	public String[] setExportExcelDataMap(Object entity)
	{
		System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
		ImportExportXML std = (ImportExportXML) entity;
		String[] fields = new String[getHeaderCount()];
		fields[0] = std.getDeptName().toString();
		fields[1] = std.getNoOfBooks().toString();
		fields[2] = std.getNoOfTitles().toString();
		fields[3] = std.getPrice().toString();
//		fields[4] = std.getDiscount().toString();
		fields[4] = std.getNetPrice().toString();
		return fields;
	}

}
