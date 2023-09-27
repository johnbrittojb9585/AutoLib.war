package Lib.Auto.FreqUsedResource;

import java.util.HashMap;
import java.util.Map;

import Common.businessutil.importexportexcel.ExportProcessor;
import Common.businessutil.importexportxml.ImportExportXML;
import Common.businessutil.reports.ReportQueryUtill;

public class FreqUsedResourceExcel implements ExportProcessor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	String fromAccNo,toAccNo,doc,Type;
	
	

	public FreqUsedResourceExcel(Map recordProcessorMap)
	{
		fromAccNo = (String)recordProcessorMap.get("From_Accno");
		toAccNo = (String)recordProcessorMap.get("To_Accno");
		doc = (String)recordProcessorMap.get("Doc");
		Type = (String)recordProcessorMap.get("Type");

	}
	
	public Map getTitleDetails()
	{
		Map<Object,Object> title=new HashMap<Object,Object>();
		title.put("title","Report Name :");
		if(Type.equalsIgnoreCase("unused"))
			title.put("titleValue",ReportQueryUtill.Frequently_UnUsedResource_Title);
		else
			title.put("titleValue",ReportQueryUtill.Frequently_Resource_Title);
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
		return ReportQueryUtill.Frequently_Pdf_Title + "_Excel.csv";
	}

	public int getHeaderCount()
	{
		return 33;
	}

	public String[] getExcelHeader()
	{
		if(!Type.equalsIgnoreCase("unused"))
		{
			return new String[]
					{ "Access No","Title","Publisher","Department","Total"};
		}
		else
		{
			return new String[]
					{ "Access No","Title","Publisher","Department"};
		}
				
	}

	public String[] setExportExcelDataMap(Object entity)
	{
		
		ImportExportXML std = (ImportExportXML) entity;
		String[] fields = new String[getHeaderCount()];
		fields[0] = std.getAccessNo().toString();
		fields[1] = std.getTitle().toString();
		fields[2] = std.getPublisherName().toString();
		fields[3] = std.getDepartmentName().toString();
		if(!Type.equalsIgnoreCase("unused"))
			fields[4] = std.getTotal().toString();
		return fields;
	}

}
