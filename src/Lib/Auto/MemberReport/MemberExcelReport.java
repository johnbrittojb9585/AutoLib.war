package Lib.Auto.MemberReport;

import java.util.HashMap;
import java.util.Map;

import Common.businessutil.importexportexcel.ExportProcessor;
import Common.businessutil.importexportxml.ImportExportXML;
import Common.businessutil.reports.ReportQueryUtill;

public class MemberExcelReport implements ExportProcessor 
{

	public MemberExcelReport() 
	{
		
	}

	public Map getTitleDetails()
	{
		Map<Object,Object> title=new HashMap<Object,Object>();
		title.put("title","Report Name :");
		title.put("titleValue",ReportQueryUtill.Counter_MemberReports_Title);
		title.put("count",0);
		return title;
	}

	public String getExcelFileName( )
	{
		return ReportQueryUtill.Counter_MemberReports_Title + "_Excel.csv";
	}

	public int getHeaderCount()
	{
		return 33;
	}

	public String[] getExcelHeader()
	{
		return new String[]
		{"Member Code","Member Name","Designation","Department","Group Name","Year"};
	
		
	}

	public String[] setExportExcelDataMap(Object entity)
	{
		ImportExportXML std = (ImportExportXML) entity;
		String[] fields = new String[getHeaderCount()];
		fields[0] = std.getMemberCode().toString();
		fields[1] = std.getMemberName().toString();
		fields[2] = std.getDesignation().toString();
		fields[3] = std.getDeptName().toString();
		fields[4] = std.getGroup().toString();
		fields[5] = std.getYear().toString();
		return fields;
	}


}
