package Lib.Auto.QBReport;

import java.util.HashMap;
import java.util.Map;

import Common.businessutil.importexportexcel.ExportProcessor;
import Common.businessutil.importexportxml.ImportExportXML;
import Common.businessutil.reports.ReportQueryUtill;

public class QuestionExportRecord implements ExportProcessor{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	String rptName;

	QuestionExportRecord(Map recordProcessorMap)
	{
		rptName = (String) recordProcessorMap.get("rptTitle");
	}
	
	public Map getTitleDetails()
	{
		Map<Object,Object> title=new HashMap<Object,Object>();
		title.put("title","Report Name :");
		title.put("titleValue",rptName);
		title.put("count",1);
		return title;
	}
	public String getExcelFileName( )
	{
		return ReportQueryUtill.QB_Report_Title + "_Excel.csv";
	}

	public int getHeaderCount()
	{
		return 33;
	}

	public String[] getExcelHeader()
	{
		return new String[]
		{ "Qb Code", "Sub Code", "Sub Name", "Course Name", "Dept Name", "Year", "Semester", "Month"};
	}

	public String[] setExportExcelDataMap(Object entity)
	{		
		ImportExportXML std = (ImportExportXML) entity;
		String[] fields = new String[getHeaderCount()];
		fields[0] = std.getQBCode().toString();
		fields[1] = std.getSubCode().toString();
		fields[2] = std.getSubName().toString();
		fields[3] = std.getCourse().toString();
		//fields[4] = std.getCourseMajor().toString();
		fields[4] = std.getDeptName().toString();
		fields[5] = std.getYear().toString();
		fields[6] = std.getSemester().toString();
		fields[7] = std.getMonth().toString();
		return fields;
	}
}
