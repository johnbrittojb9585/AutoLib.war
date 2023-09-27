package Lib.Auto.LibraryCollection;

import java.util.HashMap;
import java.util.Map;

import Common.businessutil.importexportexcel.ExportProcessor;
import Common.businessutil.importexportxml.ImportExportXML;
import Common.businessutil.reports.ReportQueryUtill;

public class LibraryCollectionExcel implements ExportProcessor{
	
	
	
	public LibraryCollectionExcel() {
		// TODO Auto-generated constructor stub
	}

	public Map getTitleDetails()
	{
		Map<Object,Object> title=new HashMap<Object,Object>();
		title.put("title","Report Name :");
		title.put("titleValue",ReportQueryUtill.Library_Collection_Title);
		title.put("count",0);
		return title;
	}

	public String getExcelFileName( )
	{
		return ReportQueryUtill.Library_Collection_Title + "_Excel.csv";
	}

	public int getHeaderCount()
	{
		return 33;
	}

	public String[] getExcelHeader()
	{
		return new String[]
		{ "Document","Total No.of Volumes","Total No.of Title"};
	
		
	}

	public String[] setExportExcelDataMap(Object entity)
	{
		ImportExportXML std = (ImportExportXML) entity;
		String[] fields = new String[getHeaderCount()];
		fields[0] = std.getDocument().toString();
		fields[1] = std.getVolume().toString();
		fields[2] = std.getTitle().toString();
		return fields;
	}

}
