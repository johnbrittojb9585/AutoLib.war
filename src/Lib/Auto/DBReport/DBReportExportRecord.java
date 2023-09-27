package Lib.Auto.DBReport;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import Common.businessutil.importexportexcel.ExportProcessor;
import Common.businessutil.importexportxml.ImportExportXML;

/**
 * The Class DateWiseExportRecord.
 */

public class DBReportExportRecord implements ExportProcessor {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Logger log4jLogger = Logger
			.getLogger(DBReportExportRecord.class);

	String[] rptFlag;

	String rptName;

	String fromAccNo;

	String toAccNo;

	String fromDate;

	String toDate;

	String documentType;

	String departmentName;

	String subjectName;

	String publisherName;

	String supplierName;

	Integer totCount;

	DBReportExportRecord(Map recordProcessorMap) {
		rptFlag = (String[]) recordProcessorMap.get("rptFlag");
		rptName = (String) recordProcessorMap.get("rptTitle");

		fromAccNo = (String) recordProcessorMap.get("fromSelectAccNo");
		toAccNo = (String) recordProcessorMap.get("toSelectAccNo");

		fromDate = (String) recordProcessorMap.get("fromSelectDate");
		toDate = (String) recordProcessorMap.get("toSelectDate");

		departmentName = (String) recordProcessorMap.get("selectDepartment");
		subjectName = (String) recordProcessorMap.get("selectSubject");
		publisherName = (String) recordProcessorMap.get("selectPublisher");
		supplierName = (String) recordProcessorMap.get("selectSupplier");
		documentType = (String) recordProcessorMap.get("selectDocumentType");

		totCount = (Integer) recordProcessorMap.get("totCount");

	}

	public Map getTitleDetails() {
		Map<Object, Object> title = new HashMap<Object, Object>();
		title.put("title", "Report Name :");
		title.put("titleValue", rptName);

		if (totCount != null) {
			int i = 1;

			if (fromAccNo != null) {
				title.put("subTitle" + i, "From :");
				title.put("value" + i, fromAccNo);
				i++;
			}
			if (toAccNo != null) {
				title.put("subTitle" + i, "To :");
				title.put("value" + i, toAccNo);
				i++;
			}
			if (fromDate != null) {
				title.put("subTitle" + i, "From :");
				title.put("value" + i, fromDate);
				i++;
			}
			if (toDate != null) {
				title.put("subTitle" + i, "To :");
				title.put("value" + i, toDate);
				i++;
			}
			if (departmentName != null) {
				title.put("subTitle" + i, "Department Name :");
				title.put("value" + i, departmentName);
				i++;
			}
			if (subjectName != null) {
				title.put("subTitle" + i, "Subject Name :");
				title.put("value" + i, subjectName);
				i++;
			}
			if (publisherName != null) {
				title.put("subTitle" + i, "Publisher Name :");
				title.put("value" + i, publisherName);
				i++;
			}
			if (supplierName != null) {
				title.put("subTitle" + i, "Supplier Name :");
				title.put("value" + i, supplierName);
				i++;
			}
			if (documentType != null) {
				title.put("subTitle" + i, "Document Type :");
				title.put("value" + i, documentType);
				i++;
			}

			title.put("count", totCount); // count value should be equal to
											// value count
		}
		log4jLogger.info("Total Count:" + totCount);

		return title;
	}

	public String getExcelFileName() {
		return rptName + "_Excel.csv";
	}

	public int getHeaderCount() {
		return 33;
	}

	public String[] getExcelHeader() {
		String[] tempHeader = new String[rptFlag.length - 1];
		for (int i = 1; i < rptFlag.length; i++) {
			tempHeader[i - 1] = rptFlag[i];
			//log4jLogger.info("HEADER LENGTH:" + tempHeader[i - 1]);
		}
		log4jLogger.info("HEADER LENGTH:" + tempHeader.length);
		return tempHeader;
	}

	public String[] setExportExcelDataMap(Object entity) {
		ImportExportXML std = (ImportExportXML) entity;
		String[] fields = new String[getHeaderCount()];

		for (int i = 1; i < rptFlag.length; i++) {

			if (rptFlag[i].equalsIgnoreCase("access_no")) {
				fields[i - 1] = std.getAccessNo().toString();
			} else if (rptFlag[i].equalsIgnoreCase("author_name")) {
				fields[i - 1] = std.getAuthorName().toString();
			} else if (rptFlag[i].equalsIgnoreCase("title")) {
				fields[i - 1] = std.getTitle().toString();
			} else if (rptFlag[i].equalsIgnoreCase("call_no")) {
				fields[i - 1] = std.getCallNo().toString();
			}

			else if (rptFlag[i].equalsIgnoreCase("dept_name")) {
				fields[i - 1] = std.getDepartmentName().toString();
			} else if (rptFlag[i].equalsIgnoreCase("sub_name")) {
				fields[i - 1] = std.getSubjectName().toString();
			} else if (rptFlag[i].equalsIgnoreCase("publisher")) {
				fields[i - 1] = std.getPublisherName().toString();
			} else if (rptFlag[i].equalsIgnoreCase("availability")) {
				fields[i - 1] = std.getAvailability().toString();
			}

			else if (rptFlag[i].equalsIgnoreCase("isbn")) {
				fields[i - 1] = std.getIsbnNo().toString();
			} else if (rptFlag[i].equalsIgnoreCase("year_pub")) {
				fields[i - 1] = std.getYearPub().toString();
			} else if (rptFlag[i].equalsIgnoreCase("bprice")) {
				fields[i - 1] = std.getPrice().toString();
			} else if (rptFlag[i].equalsIgnoreCase("received_date")) {
				fields[i - 1] = std.getReceivedDate().toString();
			}

			else if (rptFlag[i].equalsIgnoreCase("edition")) {
				fields[i - 1] = std.getEdition().toString();
			} else if (rptFlag[i].equalsIgnoreCase("location")) {
				fields[i - 1] = std.getLocation().toString();
			} else if (rptFlag[i].equalsIgnoreCase("invoice_no")) {
				fields[i - 1] = std.getInvoiceNo().toString();
			} else if (rptFlag[i].equalsIgnoreCase("language")) {
				fields[i - 1] = std.getLanguage().toString();
			} else if (rptFlag[i].equalsIgnoreCase("supplier")) {
				fields[i - 1] = std.getSupplierName().toString();
			} else if (rptFlag[i].equalsIgnoreCase("volno")) {
				fields[i - 1] = std.getVolno().toString();
			} else if (rptFlag[i].equalsIgnoreCase("script")) {
				fields[i - 1] = std.getMonth().toString();
			} else if (rptFlag[i].equalsIgnoreCase("add_field3")) {
				fields[i - 1] = std.getIssue_no().toString();
			}  else if (rptFlag[i].equalsIgnoreCase("invoice_date")) {
				fields[i - 1] = std.getInvoiceDate().toString();
			}else if (rptFlag[i].equalsIgnoreCase("discount")) {
				fields[i - 1] = std.getDiscount().toString();
			}else if (rptFlag[i].equalsIgnoreCase("accepted_price")) {
				fields[i - 1] = std.getAcceptedPrice().toString();
			}else if (rptFlag[i].equalsIgnoreCase("gift_purchase")) {
				fields[i - 1] = std.getPurchaseType().toString();
			}else if (rptFlag[i].equalsIgnoreCase("keywords")) {
				fields[i - 1] = std.getKeywords().toString();
			}
		}

		return fields;
	}
}
