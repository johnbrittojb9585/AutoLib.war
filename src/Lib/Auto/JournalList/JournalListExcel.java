package Lib.Auto.JournalList;

import java.util.HashMap;
import java.util.Map;

import Common.businessutil.importexportexcel.ExportProcessor;
import Common.businessutil.importexportxml.ImportExportXML;
import Common.businessutil.reports.ReportQueryUtill;

public class JournalListExcel implements ExportProcessor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	String fromAccNo,toAccNo,doc,avail;
	
	

	public JournalListExcel(Map recordProcessorMap)
	{
		

	}
	
	public Map getTitleDetails()
	{
		Map<Object,Object> title=new HashMap<Object,Object>();
		title.put("title","Report Name :");
		title.put("titleValue",ReportQueryUtill.JNL_List_Title);
		
		title.put("count",0);
		return title;
	}

	public String getExcelFileName( )
	{
		return ReportQueryUtill.JNL_List_Title + "_Excel.csv";
	}

	public int getHeaderCount()
	{
		return 33;
	}

	public String[] getExcelHeader()
	{
		
		return new String[]
		{ "Journal Name","Frequency","Country","Publisher","Language","Type"};
	
		
	}

	public String[] setExportExcelDataMap(Object entity)
	{
		
		ImportExportXML std = (ImportExportXML) entity;
		String[] fields = new String[getHeaderCount()];
		fields[0] = std.getTitle().toString();
		fields[1] = std.getFrequency().toString();
		fields[2] = std.getCountry().toString();
		fields[3] = std.getPublisherName().toString();
		fields[4] = std.getLanguage().toString();
		fields[5] = std.getDocument().toString();

		return fields;
	}

}
