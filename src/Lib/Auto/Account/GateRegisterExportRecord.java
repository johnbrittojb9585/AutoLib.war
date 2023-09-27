package Lib.Auto.Account;

import java.util.HashMap;
import java.util.Map;

import Common.businessutil.importexportexcel.ExportProcessor;
import Common.businessutil.importexportxml.ImportExportXML;
import Common.businessutil.reports.ReportQueryUtill;

public class GateRegisterExportRecord implements ExportProcessor{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	String fromAccNo;
	
	String toAccNo;
	
	String documentType;

	GateRegisterExportRecord(Map recordProcessorMap)
	{
		
	}
	
	public Map getTitleDetails()
	{
		Map<Object,Object> title=new HashMap<Object,Object>();
		title.put("title","Report Name :");
		title.put("titleValue",ReportQueryUtill.GateReg_Title);
		title.put("count",0);//count value should be equal to value count
		return title;
	}

	public String getExcelFileName( )
	{
		return ReportQueryUtill.GateReg_Title+".csv";
	}

	public int getHeaderCount()
	{
		return 33;
	}

	public String[] getExcelHeader()
	{
		return new String[]
		{ "User ID", "User Name", "Designation", "In Time", "Out Time", "Min Spent", "Date"};
	}

	public String[] setExportExcelDataMap(Object entity)
	{		
		ImportExportXML std = (ImportExportXML) entity;
		String[] fields = new String[getHeaderCount()];
		
		fields[0] = std.getMemberCode().toString();
		fields[1] = std.getMemberName().toString();
		fields[2] = std.getDesigName().toString();
		fields[3] = std.getIntime().toString();
		fields[4] = std.getOuttime().toString();
		fields[5] = std.getMinused().toString();
		fields[6] = std.getReturnDate().toString();
			
		return fields;
	}

}
