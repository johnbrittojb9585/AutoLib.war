package Lib.Auto.Stock;

import Common.businessutil.importexportexcel.ExportProcessor;
import Common.businessutil.importexportxml.ImportExportXML;
import java.util.HashMap;
import java.util.Map;

public class StockExportRecord
implements ExportProcessor
{
	private static final long serialVersionUID = 1L;
//	String fromAccNo;
//	String toAccNo;
	String documentType;

	StockExportRecord(Map recordProcessorMap)
	{
//		fromAccNo = "";
//		toAccNo = "";
		documentType = ((String)recordProcessorMap.get("documentType"));
	}


	public Map getTitleDetails()
	{
		Map<Object, Object> title = new HashMap();
		title.put("title", "Report Name :");
		title.put("titleValue", "Stock Report");
//		title.put("subTitle1", "From :");
//		title.put("subTitle2", "To :");
		title.put("subTitle3", "Document Type :");

//		title.put("value1", fromAccNo);
//		title.put("value2", toAccNo);
		title.put("value3", documentType);
		title.put("count", Integer.valueOf(3));
		return title;
	}

	public String getExcelFileName() {
		return "Stock_Report_Excel.csv";
	}

	public int getHeaderCount()
	{
		return 33;
	}

	public String[] getExcelHeader() {
		return new String[] { "Access No", "Title", "Author Name", "Publisher", 
				"Year Of Publication", "Document", "Price", "availability" };
	}


	public String[] setExportExcelDataMap(Object entity)
	{
		ImportExportXML std = (ImportExportXML)entity;

		String[] fields = new String[getHeaderCount()];
		fields[0] = std.getAccessNo().toString();
		fields[1] = std.getTitle().toString();
		fields[2] = std.getAuthorName().toString();
		fields[3] = std.getPublisherName().toString();
		fields[4] = std.getYear().toString();
		fields[5] = std.getDocument().toString();
		fields[6] = std.getPrice().toString();
		fields[7] = std.getAvailability().toString();

		return fields;
	}
}