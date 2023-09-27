package Lib.Auto.Library_Login;

import java.util.HashMap;
import java.util.Map;

import Common.businessutil.importexportexcel.ExportProcessor;
import Common.businessutil.importexportxml.ImportExportXML;
import Common.businessutil.reports.ReportQueryUtill;

public class Library_Login_Excel implements ExportProcessor{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	String fromAccNo;
	
	String toAccNo;
	
	String rptFlag;
	

	Library_Login_Excel(Map recordProcessorMap)
	{
		fromAccNo = (String)recordProcessorMap.get("fromAccNo");
		toAccNo = (String)recordProcessorMap.get("toAccNo");
		rptFlag = (String)recordProcessorMap.get("rptFlag");
	}
	
	public Map getTitleDetails()
	{
		Map<Object,Object> title=new HashMap<Object,Object>();
		title.put("title","Report Name :");
		title.put("titleValue",ReportQueryUtill.Library_Login_Title);
		title.put("subTitle1","From :");
		title.put("subTitle2","To :");
				
		title.put("value1",fromAccNo);
		title.put("value2",toAccNo);
		title.put("count",3);//count value should be equal to value count
		return title;
	}

	public String getExcelFileName( )
	{
		return ReportQueryUtill.Library_Login_Title+".csv";
	}

	public int getHeaderCount()
	{
		return 33;
	}
	
	public String[] getExcelHeader()
	{		
		if(rptFlag.equals("Gate_Report")){
			return new String[]
					{ "Date","User ID", "User Name", "Department", "Group"};		
		}		
		else{
			
			return new String[] { "Date","No of Users"};
		}
		
		
	}

	

	public String[] setExportExcelDataMap(Object entity)
	{		
		ImportExportXML std = (ImportExportXML) entity;
		String[] fields = new String[getHeaderCount()];
		
		if(rptFlag.equals("Gate_Report")){
		
		fields[0] = std.getReturnDate().toString();
		fields[1] = std.getMemberCode().toString();
		fields[2] = std.getMemberName().toString();
		fields[3] = std.getDeptName().toString();
		fields[4] = std.getGroup().toString();
		}
		else{
			fields[0] = std.getBreakupdate().toString();
			fields[1] = std.getBreakupcount().toString();
		}
		return fields;
	}

}
