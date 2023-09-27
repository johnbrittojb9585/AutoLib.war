package Lib.Auto.Report;

import java.util.HashMap;
import java.util.Map;

import Common.businessutil.importexportexcel.ExportProcessor;
import Common.businessutil.importexportxml.ImportExportXML;

public class ReportAllBreakup implements ExportProcessor
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	String rptFlag;
	
	String rptName;
	
	String fromAccNo;
	
	String toAccNo;
	
	String documentType;

	ReportAllBreakup(Map recordProcessorMap)
	{
		rptFlag = (String) recordProcessorMap.get("rptFlag");
		rptName = (String) recordProcessorMap.get("rptTitle");
		fromAccNo = (String)recordProcessorMap.get("fromAccNo");
		toAccNo = (String)recordProcessorMap.get("toAccNo");
		documentType = (String)recordProcessorMap.get("documentType");
	}
	
	public Map getTitleDetails()
	{
		Map<Object,Object> title=new HashMap<Object,Object>();
		title.put("title","Report Name :");
		title.put("titleValue",rptName);
		title.put("subTitle1","From :");
		title.put("subTitle2","To :");
		title.put("subTitle3","Document Type :");
		
		title.put("value1",fromAccNo);
		title.put("value2",toAccNo);
		title.put("value3",documentType);
		title.put("count",3);//count value should be equal to value count
		return title;
	}

	public String getExcelFileName( )
	{
		return rptName + "_Excel.csv";
	}

	public int getHeaderCount()
	{
		return 33;
	}

	public String[] getExcelHeader()
	{
		if(rptFlag.equalsIgnoreCase("Issue"))
		{
			return new String[]
			{ "Issue_Date", "Issue_Count"};
			          	
		}else if(rptFlag.equalsIgnoreCase("Return"))
		{
		    return new String[]
		    { "Return_Date", "Return_Count"};	
		}else if(rptFlag.equalsIgnoreCase("Renewal"))
		{
			return new String[]
			{ "Renewal_Date", "Renewal_Count"};			      		
		}
		else if(rptFlag.equalsIgnoreCase("Reserve"))
		{
			return new String[]
			{ "Reserve_Date", "Reserve_Count"};			      		
		}
		else if(rptFlag.equalsIgnoreCase("Duereminder"))
		{
			return new String[]
			{ "Due_Date", "Reminder_Count"};			      		
		}
		else
		{
			return new String[]
					{ "Fine_Date", "Total_Student", "Total_Fine_Amount"};	
		}
	}
		

	public String[] setExportExcelDataMap(Object entity)
	{		
		ImportExportXML std = (ImportExportXML) entity;
		
		String[] fields = new String[getHeaderCount()];
		
		fields[0] = std.getBreakupdate().toString();
		fields[1] = std.getBreakupcount().toString();
		if(rptFlag.equalsIgnoreCase("Fine"))
		{
			fields[2] = std.getTotfine().toString();
		}
		return fields;
	}

}
