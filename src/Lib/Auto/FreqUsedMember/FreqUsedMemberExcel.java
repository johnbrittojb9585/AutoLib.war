package Lib.Auto.FreqUsedMember;

import java.util.HashMap;
import java.util.Map;

import Common.businessutil.importexportexcel.ExportProcessor;
import Common.businessutil.importexportxml.ImportExportXML;
import Common.businessutil.reports.ReportQueryUtill;

public class FreqUsedMemberExcel implements ExportProcessor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	String fromAccNo,toAccNo,doc,type;
	
	

	public FreqUsedMemberExcel(Map recordProcessorMap)
	{
		fromAccNo = (String)recordProcessorMap.get("From_Accno");
		toAccNo = (String)recordProcessorMap.get("To_Accno");
		doc = (String)recordProcessorMap.get("Doc");
		type = (String)recordProcessorMap.get("Type");

	}
	
	public Map getTitleDetails()
	{
		Map<Object,Object> title=new HashMap<Object,Object>();
		title.put("title","Report Name :");
		if(type.equalsIgnoreCase("frequency"))
			title.put("titleValue",ReportQueryUtill.Frequent_Member_Title);
		else if(type.equalsIgnoreCase("member"))
			title.put("titleValue",ReportQueryUtill.Member_unused);
		else if(type.equalsIgnoreCase("gate"))
			title.put("titleValue",ReportQueryUtill.Frequently_USER_Title);	
		
			
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
	if(type.equalsIgnoreCase("frequency"))
	{
		return ReportQueryUtill.Frequent_Member_Title + "_Excel.csv";
	}
	else if(type.equalsIgnoreCase("member"))
	{
		return ReportQueryUtill.Member_unused + "_Excel.csv";
	}
	else if(type.equalsIgnoreCase("gate"))
	{
		return ReportQueryUtill.Frequently_USER_Title + "_Excel.csv";
	}
	else
	{
		return ReportQueryUtill.Frequent_Member_Title + "_Excel.csv";
	}
	}
	public int getHeaderCount()
	{
		return 33;
	}

	public String[] getExcelHeader()
	{
		if(type.equalsIgnoreCase("frequency"))
		{
			return new String[]
					{ "Member code","Member Name","Department","Course","Cyear","Total"};
		}
		if(type.equalsIgnoreCase("gate"))
		{
			return new String[]
					{ "Member code","Member Name","Department","Cyear","Total","Total Minutes"};
		}
		if(type.equalsIgnoreCase("member")){
			return new String[]
					{"Member Code", "Member Name", "Designation","Cyear","Department"};
		}	
		else{
			return new String[]
				{"Member Code", "Member Name", "Designation","Cyear","Department"};
		}
	}

	public String[] setExportExcelDataMap(Object entity)
	{
		
		ImportExportXML std = (ImportExportXML) entity;
		String[] fields = new String[getHeaderCount()];
		
		if(type.equalsIgnoreCase("frequency"))
		{
			fields[0]=std.getMemberCode().toString();
			fields[1]=std.getMemberName().toString();
			fields[2]=std.getDepartmentName().toString();
			fields[3]=std.getCourseName().toString();
			fields[4]=std.getCyear().toString();
			fields[5]=std.getTotal().toString();
		}
		if(type.equalsIgnoreCase("gate"))
		{
			fields[0]=std.getMemberCode().toString();
			fields[1]=std.getMemberName().toString();
			fields[2]=std.getDepartmentName().toString();
			fields[3]=std.getCyear().toString();
			fields[4]=std.getTotal().toString();
			fields[5]=std.getTotalMins().toString();
		}
		if(type.equalsIgnoreCase("member"))
		{
			fields[0]=std.getMemberCode().toString();
			fields[1]=std.getMemberName().toString();
			fields[2]=std.getDesigName().toString();
			fields[3]=std.getCyear().toString();
			fields[4]=std.getDepartmentName().toString();
			
		}
		return fields;
	}

}
