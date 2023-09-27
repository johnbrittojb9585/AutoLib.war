package Lib.Auto.BibliographyReport;

import java.util.HashMap;
import java.util.Map;

import Common.businessutil.importexportexcel.ExportProcessor;
import Common.businessutil.importexportxml.ImportExportXML;
import Common.businessutil.reports.ReportQueryUtill;

public class BibliographyExcel implements ExportProcessor
{



	
	private static final long serialVersionUID = 1L;

	String type,reportname;
	


	BibliographyExcel(Map recordProcessorMap)
	{
		type = (String)recordProcessorMap.get("Frequency");
		reportname = (String)recordProcessorMap.get("ReportName");
	}
	
	public Map getTitleDetails()
	{
		Map<Object,Object> title=new HashMap<Object,Object>();
		title.put("title","Report Name :");
		title.put("titleValue",reportname);
		title.put("subTitle1","Availability :");
				
		
		title.put("value1",type);
		title.put("count",3);
		return title;
	}

	public String getExcelFileName( )
	{
		return reportname+".csv";
	}

	public int getHeaderCount()
	{
		return 33;
	}

	public String[] getExcelHeader()
	{
		return new String[]
		{ "Access No","Author Name","Title","Edition","Call No","Publisher","Year","Department","Subject","Supplier","Budget","Location","ISBN","Price"};
	}

	
	
	public String[] setExportExcelDataMap(Object entity)
	{	
		ImportExportXML std = (ImportExportXML) entity;
		String[] fields = new String[getHeaderCount()];
		fields[0] = std.getAccessNo().toString();
		fields[1] = std.getAuthorName().toString();
		fields[2] = std.getTitle().toString();
		fields[3] = std.getEdition().toString();
		fields[4] = std.getCallNo().toString();
		fields[5] = std.getPublisherName().toString();
		fields[6] = std.getYearPub().toString();
		fields[7] = std.getDeptName().toString();
		fields[8] = std.getSubjectName().toString();
		fields[9] = std.getSupplierName().toString();
		fields[10] = std.getBudget().toString();
		fields[11] = std.getLocation().toString();
		fields[12] = std.getIsbnNo().toString();
		fields[13] = std.getBprice().toString();
		return fields;
	}


}
