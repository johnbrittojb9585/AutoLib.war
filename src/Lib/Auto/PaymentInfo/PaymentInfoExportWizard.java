package Lib.Auto.PaymentInfo;


import java.util.HashMap;
import java.util.Map;

import Common.businessutil.importexportexcel.ExportProcessor;
import Common.businessutil.importexportxml.ImportExportXML;
import Common.businessutil.reports.ReportQueryUtill;

public class PaymentInfoExportWizard implements ExportProcessor{


	String fromAccNo;
	String toAccNo;
	String rptFlag;

	String Type;
	PaymentInfoExportWizard(Map recordProcessorMap)
	{
		rptFlag = (String) recordProcessorMap.get("rptFlag");
		fromAccNo = (String)recordProcessorMap.get("From_Accno");
		toAccNo = (String)recordProcessorMap.get("To_Accno");
		Type = (String)recordProcessorMap.get("Type");
	}


	public Map getTitleDetails()
	{
		Map<Object,Object> title=new HashMap<Object,Object>();
		title.put("title","Report Name :");
		title.put("titleValue",ReportQueryUtill.Paymentinfo_Title);
		title.put("subTitle1","From :");
		title.put("subTitle2","To :");
		title.put("subTitle3","Pay.Mode :");


		title.put("value1",fromAccNo);
		title.put("value2",toAccNo);
		title.put("value3",Type);
		title.put("count",3);//count value should be equal to value count

		return title;
	}



	public String getExcelFileName(){

		return ReportQueryUtill.Paymentinfo_Title + "_Excel.csv";
	}

	public int getHeaderCount()
	{
		return 33;
	}

	public String[] getExcelHeader()
	{
		System.out.println("::::::::::rptFlag:::::::"+rptFlag);
		if(rptFlag.equalsIgnoreCase("PaidReport"))
		{
			return new String[]
					{ "Bill No", "Member Code", "Member Name" ,"Amount" , "Paid Date" , "Payment Mode" , "Staff Code"};
		}
		else
		{
			return new String[]
					{ "Member Code", "Member Name" , "Department" , "course", "Total Amount", "Paid Amount" , "Balance Amount"};
		}
	}
	public String[] setExportExcelDataMap(Object entity)
	{

		ImportExportXML std = (ImportExportXML) entity;

		String[] fields = new String[getHeaderCount()];

		
		if(rptFlag.equalsIgnoreCase("PaidReport"))
		{
		
			fields[0] = std.getTrans_no().toString();
			fields[1] = std.getMemberCode().toString();
			fields[2] = std.getMemberName().toString();
			fields[3] = std.getAmount().toString();
			fields[4] = std.getPaiddate().toString();
			fields[5] = std.getPaymode().toString();
			fields[6] = std.getStaffCode().toString();


		}
		else if(rptFlag.equalsIgnoreCase("UnPaidReport"))
		{
			fields[0] = std.getMemberCode().toString();
			fields[1] = std.getMemberName().toString();
			fields[2] = std.getDepartmentName().toString();
			fields[3] = std.getCourse().toString();
			fields[4] = std.getTotalamount().toString();
			fields[5] = std.getPaidamount().toString();
			fields[6] = std.getBalamount().toString();


		}
		return fields;
	}


}

