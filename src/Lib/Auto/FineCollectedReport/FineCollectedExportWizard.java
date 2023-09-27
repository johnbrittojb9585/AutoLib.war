package Lib.Auto.FineCollectedReport;


import java.util.HashMap;
import java.util.Map;

import Common.businessutil.importexportexcel.ExportProcessor;
import Common.businessutil.importexportxml.ImportExportXML;
import Common.businessutil.reports.ReportQueryUtill;

public class FineCollectedExportWizard implements ExportProcessor{
	
	
	String fromAccNo;
	String toAccNo;

	String Type;
	FineCollectedExportWizard(Map recordProcessorMap)
	{
		fromAccNo = (String)recordProcessorMap.get("From_Accno");
		toAccNo = (String)recordProcessorMap.get("To_Accno");
		//Type = (String)recordProcessorMap.get("Type");
	}

	
	public Map getTitleDetails()
	{
		Map<Object,Object> title=new HashMap<Object,Object>();
		title.put("title","Report Name :");
		title.put("titleValue",ReportQueryUtill.FineCollected_Title);
		title.put("subTitle1","From :");
		title.put("subTitle2","To :");
		//title.put("subTitle3","Pay.Mode :");
		
		
		title.put("value1",fromAccNo);
		title.put("value2",toAccNo);
		//title.put("value3",Type);
		title.put("count",3);//count value should be equal to value count
		
		return title;
	}
	
	
	
	public String getExcelFileName(){
		
		return ReportQueryUtill.FineCollected_Title + "_Excel.csv";
	}

	public int getHeaderCount()
	{
		return 33;
	}
	
	public String[] getExcelHeader()
	{
		
		return new String[]
		{ "Staff Code", "Paid Date" , "Amount" };
	}
	
	public String[] setExportExcelDataMap(Object entity)
	{
				
		ImportExportXML std = (ImportExportXML) entity;
		
		String[] fields = new String[getHeaderCount()];
		
		fields[0] = std.getStaffCode().toString();
		fields[1] = std.getPaiddate().toString();
		fields[2] = std.getAmount().toString();
		
			
		return fields;
	}

	
}

