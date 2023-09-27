/*
 *                 Autolib License Notice
 *
 * The contents of this file are subject to the Autolib  License
 * Version 1.0 (the "License"). You may not use this file except in
 * compliance with the License.The Initial Developer of the Original Code is
 * Autolib Software Systems.
 * Portions Copyright 1998-2010.Autolib Software Systems All Rights Reserved.
 *
 *
 */
package Common.businessutil.importexportxml;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.SQLQuery;

import Lib.Auto.Stock.StockBean;

import com.library.autolib.portal.dbconnectionutil.BaseDBConnection;

/** The Class ImportExportXMLDaoImpl. */
public class ImportExportXMLDaoImpl extends BaseDBConnection implements
		ImportExportXMLDao {
	private static Logger log4jLogger = Logger
			.getLogger(ImportExportXMLDaoImpl.class);

	java.sql.Connection con = null;
	java.sql.PreparedStatement Prest = null;
	java.sql.ResultSet rs = null;

	public List getAccessionWiseReportList(String query) {
		log4jLogger
				.info("================= start: [getAccessionWiseReportList] ====================== ");

		List<Object> finalResults = new ArrayList<Object>();
		;

		try {
			con = getSession().connection();
			Prest = con.prepareStatement(query);
			rs = Prest.executeQuery();

			while (rs.next()) {

				// access_no,call_no,title,author_name,Dept_Name,edition,publisher,year_pub,supplier,invoice_no,isbn,bprice,Received_Date,Edition,Pages,invoice_date

				ImportExportXML importExportXML = new ImportExportXML();
				importExportXML.setAccessNo(rs.getString(1));
				importExportXML.setCallNo(rs.getString(2));
				importExportXML.setTitle(rs.getString(3));
				importExportXML.setAuthorName(rs.getString(4));
				importExportXML.setDeptName(rs.getString(5));
				importExportXML.setEdition(rs.getString(6));
				importExportXML.setPublisherName(rs.getString(7));
				importExportXML.setYearPub(rs.getString(8));
				importExportXML.setVolno(rs.getString("volno"));
				importExportXML.setPartno(rs.getString("part_no"));
				importExportXML.setSupplierName(rs.getString("supplier"));
				importExportXML.setInvoiceNo(rs.getString("invoice_no"));
				importExportXML.setIsbnNo(rs.getString("isbn"));
				importExportXML.setPrice(rs.getString("bprice"));
				importExportXML.setReceivedDate(rs.getString("received_date"));
				importExportXML.setNoOfPages(rs.getString("pages"));
				importExportXML.setInvoiceDate(rs.getString("invoice_date"));

				finalResults.add(importExportXML);
			}

			log4jLogger
					.info("================= End: [getAccessionWiseReportList] ====================== ");

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return finalResults;
	}

	// //////////

	public List getBindingReport(String query) {

		log4jLogger
				.info("================= start: [BindingReportList] ====================== ");

		List<Object> finalResults = new ArrayList<Object>();
		;

		try {
			con = getSession().connection();
			Prest = con.prepareStatement(query);
			rs = Prest.executeQuery();

			while (rs.next()) {
				ImportExportXML importExportXML = new ImportExportXML();
				importExportXML.setAccessNo(rs.getString("access_no"));
				importExportXML.setTitle(rs.getString("Title"));
				importExportXML.setAuthorName(rs.getString("author_name"));
				importExportXML.setBinderName(rs.getString("binder_name"));
				importExportXML
						.setAcceptedPrice(rs.getString("accepted_price"));
				importExportXML.setSendingDate(rs.getString("sending_date"));
				importExportXML.setDocument(rs.getString("doc_type"));
				finalResults.add(importExportXML);
			}

			log4jLogger
					.info("================= End: [getBindingReport] ====================== ");

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return finalResults;

	}

	public List getTransFineCumulativeList(String query) {
		log4jLogger
				.info("================= start: [getTransFineCumulativeList] ====================== ");
		List<Object> finalResults = new ArrayList<Object>();
		;
		try {

			con = getSession().connection();
			Prest = con.prepareStatement(query);
			rs = Prest.executeQuery();

			while (rs.next()) {
				ImportExportXML importExportXML = new ImportExportXML();

				importExportXML.setMemberCode(rs.getString(1));
				importExportXML.setOverdue(rs.getString(2));
				importExportXML.setPhotoCopy(rs.getString(3));
				importExportXML.setPrintout(rs.getString(4));
				importExportXML.setColorPrint(rs.getString(5));
				importExportXML.setStripBinding(rs.getString(6));
				importExportXML.setStickBinding(rs.getString(7));
				importExportXML.setSprialBinding(rs.getString(8));
				importExportXML.setRecovery(rs.getString(9));
				importExportXML.setLossOfResources(rs.getString(10));
				importExportXML.setOthers(rs.getString(11));
				importExportXML.setTotal(rs.getString(12));

				log4jLogger.info("Member_Code    :"
						+ importExportXML.getMemberCode());
				log4jLogger
						.info("OVER UDE    :" + importExportXML.getOverdue());

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return finalResults;

	}

	public List getCounterReportList(String query, String flag, String flag1) {
		log4jLogger
				.info("================= start: [getCounterReportList] ====================== ");

		List<Object> finalResults = new ArrayList<Object>();
		;

		try {
			con = getSession().connection();
			Prest = con.prepareStatement(query);
			rs = Prest.executeQuery();

			while (rs.next()) {

				ImportExportXML importExportXML = new ImportExportXML();

				if (flag.equalsIgnoreCase("CurIssue")) {
					if (flag1.equalsIgnoreCase("listing")) {
						importExportXML.setAccessNo(rs.getString(1));
						importExportXML.setTitle(rs.getString(2));
						importExportXML.setMemberCode(rs.getString(3));
						importExportXML.setMemberName(rs.getString(4));
						importExportXML.setIssueDate(rs.getString(5));
						importExportXML.setDueDate(rs.getString(6));
						importExportXML.setDocument(rs.getString(7));
						importExportXML.setStaffCode(rs.getString(8));
					}
					if (flag1.equalsIgnoreCase("breakup")) {
						importExportXML
								.setIssueDate(rs.getString("issue_date"));
						importExportXML.setNoOfBooks(rs
								.getString("total_books"));
					}
					if (flag1.equalsIgnoreCase("cumulative")) {
						importExportXML.setNoOfBooks(rs
								.getString("total_books"));
					}
				}

				else if (flag.equalsIgnoreCase("Issue")) {
					if (flag1.equalsIgnoreCase("listing")) {
						System.out.println(":::Return_Dtae::::"
								+ rs.getString("return_date"));
						if (rs.getString("return_date").equalsIgnoreCase(
								"01/01/1800")) {
							importExportXML.setReturnDate("Not Yet Returned");
						} else {
							importExportXML.setReturnDate(rs
									.getString("return_date"));
						}
						importExportXML.setAccessNo(rs.getString(1));
						importExportXML.setTitle(rs.getString(2));
						importExportXML.setMemberCode(rs.getString(3));
						importExportXML.setMemberName(rs.getString(4));
						importExportXML.setIssueDate(rs.getString(5));
						importExportXML.setDueDate(rs.getString(6));

						importExportXML.setDocument(rs.getString(8));
						importExportXML.setStaffCode(rs.getString(9));
					}

					if (flag1.equalsIgnoreCase("breakup")) {
						importExportXML
								.setIssueDate(rs.getString("issue_date"));
						importExportXML.setNoOfBooks(rs
								.getString("total_books"));
					}
					if (flag1.equalsIgnoreCase("cumulative")) {
						importExportXML.setNoOfBooks(rs
								.getString("total_books"));
					}

				} else if (flag.equalsIgnoreCase("Return")) {
					if (flag1.equalsIgnoreCase("listing")) {
						importExportXML.setAccessNo(rs.getString(1));
						importExportXML.setTitle(rs.getString(2));
						importExportXML.setMemberCode(rs.getString(3));
						importExportXML.setMemberName(rs.getString(4));
						importExportXML.setIssueDate(rs.getString(5));
						importExportXML.setDueDate(rs.getString(6));
						importExportXML.setReturnDate(rs.getString(7));
						importExportXML.setDocument(rs.getString(8));
						importExportXML.setStaffCode(rs.getString(9));
					}
					if (flag1.equalsIgnoreCase("breakup")) {
						importExportXML.setReturnDate(rs
								.getString("return_date"));
						importExportXML.setNoOfBooks(rs
								.getString("total_books"));
					}
					if (flag1.equalsIgnoreCase("cumulative")) {
						importExportXML.setNoOfBooks(rs
								.getString("total_books"));
					}

				} else if (flag.equalsIgnoreCase("Renewal")) {
					if (flag1.equalsIgnoreCase("listing")) {
						importExportXML.setAccessNo(rs.getString(1));
						importExportXML.setTitle(rs.getString(2));
						importExportXML.setMemberCode(rs.getString(3));
						importExportXML.setMemberName(rs.getString(4));
						importExportXML.setIssueDate(rs.getString(5));
						importExportXML.setDueDate(rs.getString(6));
						importExportXML.setDocument(rs.getString(7));
						importExportXML.setStaffCode(rs.getString(8));
					}

					if (flag1.equalsIgnoreCase("breakup")) {
						importExportXML
								.setIssueDate(rs.getString("issue_date"));
						importExportXML.setNoOfBooks(rs
								.getString("total_books"));
					}
					if (flag1.equalsIgnoreCase("cumulative")) {
						importExportXML.setNoOfBooks(rs
								.getString("total_books"));
					}

				} else if (flag.equalsIgnoreCase("Resreminder")) {
					importExportXML.setAccessNo(rs.getString(1));
					importExportXML.setTitle(rs.getString(2));
					importExportXML.setMemberCode(rs.getString(3));
					importExportXML.setResdate(rs.getString(4));
					importExportXML.setDocument(rs.getString(5));
				} else if (flag.equalsIgnoreCase("Reserve")) {
					importExportXML.setAccessNo(rs.getString(1));
					importExportXML.setTitle(rs.getString(2));
					importExportXML.setMemberCode(rs.getString(3));
					importExportXML.setResdate(rs.getString(4));
					importExportXML.setDocument(rs.getString(5));
				}

				else if (flag.equalsIgnoreCase("Duereminder")) {
					if (flag1.equalsIgnoreCase("listing")) {
						importExportXML.setAccessNo(rs.getString("access_no"));
						importExportXML.setTitle(rs.getString("title"));
						importExportXML.setMemberCode(rs
								.getString("member_code"));
						importExportXML.setMemberName(rs
								.getString("member_name"));
						importExportXML
								.setIssueDate(rs.getString("issue_date"));
						importExportXML.setDueDate(rs.getString("due_date"));
						importExportXML.setDocument(rs.getString("doc_type"));
					}
					if (flag1.equalsIgnoreCase("breakup")) {
						importExportXML.setDueDate(rs.getString("due_date"));
						importExportXML.setNoOfBooks(rs
								.getString("total_books"));
					}
					if (flag1.equalsIgnoreCase("cumulative")) {
						importExportXML.setNoOfBooks(rs
								.getString("total_books"));
					}
				} else if (flag.equalsIgnoreCase("Fine")) {
					if (flag1.equalsIgnoreCase("listing")) {
						importExportXML.setMemberCode(rs.getString(1));
						importExportXML.setMemberName(rs.getString(2));
						importExportXML.setTrans_no(rs.getString(3));
						importExportXML.setTrans_date(rs.getString(4));
						importExportXML.setAccessNo(rs.getString(5));
						importExportXML.setDueDate(rs.getString(6));
						importExportXML.setTrans_amount(rs.getString(7));
					}

					if (flag1.equalsIgnoreCase("breakup")) {
						importExportXML.setTrans_date(rs
								.getString("trans_date"));
						importExportXML.setTrans_amount(rs
								.getString("trans_amount"));
					}
					if (flag1.equalsIgnoreCase("cumulative")) {
						importExportXML.setTrans_amount(rs
								.getString("trans_amount"));
					}
				}

				finalResults.add(importExportXML);

			}

			log4jLogger
					.info("================= End: [getCounterReportList] ====================== ");

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return finalResults;

	}

	public List getCustomReportList(String query, String[] items) {
		log4jLogger
				.info("================= start: [getCustomReportList] ====================== "
						+ query);

		List<Object> finalResults = new ArrayList<Object>();
		;

		try {
			con = getSession().connection();
			Prest = con.prepareStatement(query);
			rs = Prest.executeQuery();

			while (rs.next()) {
				ImportExportXML importExportXML = new ImportExportXML();

				for (int i = 1; i < items.length; i++) {

					if (items[i].equalsIgnoreCase("access_no")) {
						importExportXML.setAccessNo(rs.getString(items[i]));
					} else if (items[i].equalsIgnoreCase("author_name")) {
						importExportXML.setAuthorName(rs.getString(items[i]));
					} else if (items[i].equalsIgnoreCase("title")) {
						importExportXML.setTitle(rs.getString(items[i]));
					} else if (items[i].equalsIgnoreCase("call_no")) {
						importExportXML.setCallNo(rs.getString(items[i]));
					}

					else if (items[i].equalsIgnoreCase("dept_name")) {
						importExportXML.setDepartmentName(rs
								.getString(items[i]));
					} else if (items[i].equalsIgnoreCase("sub_name")) {
						importExportXML.setSubjectName(rs.getString(items[i]));
					} else if (items[i].equalsIgnoreCase("publisher")) {
						importExportXML
								.setPublisherName(rs.getString(items[i]));
					} else if (items[i].equalsIgnoreCase("availability")) {
						importExportXML.setAvailability(rs.getString(items[i]));
					}

					else if (items[i].equalsIgnoreCase("isbn")) {
						importExportXML.setIsbnNo(rs.getString(items[i]));
					} else if (items[i].equalsIgnoreCase("year_pub")) {
						importExportXML.setYearPub(rs.getString(items[i]));
					} else if (items[i].equalsIgnoreCase("bprice")) {
						importExportXML.setPrice(rs.getString(items[i]));
					} else if (items[i].equalsIgnoreCase("received_date")) {
						importExportXML.setReceivedDate(rs.getString(items[i]));
					}

					else if (items[i].equalsIgnoreCase("edition")) {
						importExportXML.setEdition(rs.getString(items[i]));
					} else if (items[i].equalsIgnoreCase("location")) {
						importExportXML.setLocation(rs.getString(items[i]));
					} else if (items[i].equalsIgnoreCase("invoice_no")) {
						importExportXML.setInvoiceNo(rs.getString(items[i]));
					} else if (items[i].equalsIgnoreCase("language")) {
						importExportXML.setLanguage(rs.getString(items[i]));
					} else if (items[i].equalsIgnoreCase("supplier")) {
						importExportXML.setSupplierName(rs.getString(items[i]));
					}

					else if (items[i].equalsIgnoreCase("volno")) {
						importExportXML.setVolno(rs.getString(items[i]));
					} else if (items[i].equalsIgnoreCase("add_field3")) {
						importExportXML.setIssue_no(rs.getString(items[i]));
					} else if (items[i].equalsIgnoreCase("script")) {
						importExportXML.setMonth(rs.getString(items[i]));
					} else if (items[i].equalsIgnoreCase("accepted_price")) {
						importExportXML
								.setAcceptedPrice(rs.getString(items[i]));
					} else if (items[i].equalsIgnoreCase("invoice_date")) {
						importExportXML.setInvoiceDate(rs.getString(items[i]));
					} else if (items[i].equalsIgnoreCase("discount")) {
						importExportXML.setDiscount(rs.getString(items[i]));
					} else if (items[i].equalsIgnoreCase("gift_purchase")) {
						importExportXML.setPurchaseType(rs.getString(items[i]));
					}

					else if (items[i].equalsIgnoreCase("keywords")) {
						importExportXML.setKeywords(rs.getString(items[i]));
					}
				}

				finalResults.add(importExportXML);
			}

			log4jLogger
					.info("================= End: [getCustomReportList] ====================== ");

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return finalResults;

	}

	public List findStatisticsDetailsReportList(String query) {

		List<Object> finalResults = new ArrayList<Object>();
		try {
			log4jLogger
					.info(":::::::::::::::: inside findStatisticsDetailsReportList::::::::");
			con = getSession().connection();
			Prest = con.prepareStatement(query);
			rs = Prest.executeQuery();
			while (rs.next()) {
				ImportExportXML importExportXML = new ImportExportXML();
				importExportXML.setAccessNo(rs.getString("access_no"));
				importExportXML.setAuthorName(rs.getString("author_name"));
				importExportXML.setTitle(rs.getString("title"));
				importExportXML.setCallNo(rs.getString("call_no"));
				importExportXML.setPublisherName(rs.getString("publisher"));
				importExportXML.setDeptName(rs.getString("dept_name"));
				importExportXML.setDiscount(rs.getString("discount"));
				importExportXML.setPrice(rs.getString("accepted_price"));
				finalResults.add(importExportXML);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return finalResults;

	}

	public List getStatisticsWiseReportList(String query) {
		log4jLogger
				.info("================= start: [getAccessionWiseReportList] ====================== ");

		List<Object> finalResults = new ArrayList<Object>();
		;
		int totbook = 0;
		try {
			con = getSession().connection();
			Prest = con.prepareStatement(query);
			rs = Prest.executeQuery();

			while (rs.next()) {
				ImportExportXML importExportXML = new ImportExportXML();
				importExportXML.setDeptName(rs.getString(1));
				importExportXML.setNoOfBooks(rs.getString(2));
				importExportXML.setNoOfTitles(rs.getString(3));
				importExportXML.setPrice(rs.getString(4));
				importExportXML.setDiscount(rs.getString(5));
				importExportXML.setNetPrice(rs.getString(6));
				finalResults.add(importExportXML);
			}

			log4jLogger
					.info("================= End: [getAccessionWiseReportList] ====================== ");

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return finalResults;
	}

	public List findLibraryCollectionExcel(String query) {

		List<Object> finalResults = new ArrayList<Object>();
		try {
			log4jLogger
					.info(":::::::::::::::: inside LibraryCollectionExcel::::::::");
			con = getSession().connection();
			Prest = con.prepareStatement(query);
			rs = Prest.executeQuery();
			while (rs.next()) {
				ImportExportXML importExportXML = new ImportExportXML();
				importExportXML.setDocument(rs.getString(1));
				importExportXML.setVolume(rs.getString(2));
				importExportXML.setTitle(rs.getString(3));
				finalResults.add(importExportXML);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return finalResults;

	}

	public List findBibliographyExcel(String query) {

		List<Object> finalResults = new ArrayList<Object>();
		try {
			log4jLogger
					.info(":::::::::::::::: inside BibliographyExcel::::::::");
			con = getSession().connection();
			Prest = con.prepareStatement(query);
			rs = Prest.executeQuery();
			while (rs.next()) {
				ImportExportXML importExportXML = new ImportExportXML();
				importExportXML.setAccessNo(rs.getString("access_no"));
				importExportXML.setAuthorName(rs.getString("author_name"));
				importExportXML.setTitle(rs.getString("title"));
				importExportXML.setEdition(rs.getString("edition"));
				importExportXML.setCallNo(rs.getString("call_no"));
				importExportXML.setPublisherName(rs.getString("publisher"));
				importExportXML.setYearPub(rs.getString("year_pub"));
				importExportXML.setDeptName(rs.getString("dept_name"));
				importExportXML.setSubjectName(rs.getString("sub_name"));
				importExportXML.setSupplierName(rs.getString("supplier"));
				importExportXML.setBudget(rs.getString("budget"));
				importExportXML.setLocation(rs.getString("location"));
				importExportXML.setIsbnNo(rs.getString("isbn"));
				importExportXML.setBprice(rs.getString("bprice"));
				finalResults.add(importExportXML);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return finalResults;

	}

	public List findMemberExcelReport(String query) {

		List<Object> finalResults = new ArrayList<Object>();
		try {
			log4jLogger
					.info(":::::::::::::::: inside MemberExcelReport::::::::");
			con = getSession().connection();
			Prest = con.prepareStatement(query);
			rs = Prest.executeQuery();
			while (rs.next()) {
				ImportExportXML importExportXML = new ImportExportXML();
				importExportXML.setMemberCode(rs.getString("member_code"));
				importExportXML.setMemberName(rs.getString("member_name"));
				importExportXML.setDesignation(rs.getString("desig_name"));
				importExportXML.setDeptName(rs.getString("dept_name"));
				importExportXML.setGroup(rs.getString("group_name"));
				importExportXML.setYear(rs.getString("cyear"));
				finalResults.add(importExportXML);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return finalResults;

	}

	public List findPaymentInfoExportWizard(String query, String flag) {

		List<Object> finalResults = new ArrayList<Object>();
		try {
			log4jLogger
					.info(":::::::::::::::: inside PaymentInfoExportWizard::::::::");
			con = getSession().connection();
			Prest = con.prepareStatement(query);
			rs = Prest.executeQuery();
			while (rs.next())

			{
				ImportExportXML importExportXML = new ImportExportXML();

				if (flag.equalsIgnoreCase("PaidReport")) {
					importExportXML.setTrans_no(rs.getString("bill_no"));
					importExportXML.setMemberCode(rs.getString("member_code"));
					importExportXML.setMemberName(rs.getString("member_name"));
					importExportXML.setAmount(rs.getString("amount"));
					importExportXML.setPaiddate(rs.getString("payment_date"));
					importExportXML.setPaymode(rs.getString("pay_mode"));
					importExportXML.setStaffCode(rs.getString("staff_code"));
				} else if (flag.equalsIgnoreCase("UnPaidReport")) {
					importExportXML.setMemberCode(rs.getString("member_code"));
					importExportXML.setMemberName(rs.getString("member_name"));
					importExportXML
							.setDepartmentName(rs.getString("dept_name"));
					importExportXML.setCourse(rs.getString("course"));
					importExportXML.setTotalamount(rs.getString("total_fine"));
					importExportXML.setPaidamount(rs.getString("paid_amount"));
					importExportXML.setBalamount(rs.getString("bal_amount"));
				}
				finalResults.add(importExportXML);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return finalResults;

	}

	public List findFineCollectedExportWizard(String query) {
		List<Object> finalResults = new ArrayList<Object>();
		try {
			log4jLogger
					.info(":::::::::::::::: inside FineCollectedExportWizard::::::::");
			con = getSession().connection();
			Prest = con.prepareStatement(query);
			rs = Prest.executeQuery();
			while (rs.next()) {
				ImportExportXML importExportXML = new ImportExportXML();
				importExportXML.setStaffCode(rs.getString("staff_code"));
				importExportXML.setPaiddate(rs.getString("payment_date"));
				importExportXML.setAmount(rs.getString("amount"));
				// importExportXML.setPaymode(rs.getString("pay_mode"));
				finalResults.add(importExportXML);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return finalResults;
	}

	public List getTitleWiseReportList(String query) {
		log4jLogger
				.info("================= start: [getTitleWiseReportList] ====================== ");

		List<Object> finalResults = new ArrayList<Object>();
		;
		int totbook = 0;
		try {
			con = getSession().connection();
			Prest = con.prepareStatement(query);
			rs = Prest.executeQuery();

			while (rs.next()) {
				ImportExportXML importExportXML = new ImportExportXML();
				importExportXML.setTitle(rs.getString(1));
				importExportXML.setAuthorName(rs.getString(2));
				importExportXML.setEdition(rs.getString(3));
				importExportXML.setNoOfBooks(rs.getString(4));
				finalResults.add(importExportXML);
			}

			log4jLogger
					.info("================= End: [getTitleWiseReportList] ====================== ");

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return finalResults;
	}

	public List findGateRegisterReportList(String query) {

		List<Object> finalResults = new ArrayList<Object>();

		try {

			con = getSession().connection();
			Prest = con.prepareStatement(query);
			rs = Prest.executeQuery();
			while (rs.next()) {

				ImportExportXML importExportXML = new ImportExportXML();

				importExportXML.setMemberCode(rs.getString("member_code"));
				importExportXML.setMemberName(rs.getString("member_name"));
				// importExportXML.setDesigName(rs.getString("dsname"));
				importExportXML.setDesigName(rs.getString("Designation"));
				importExportXML.setIntime(rs.getString("in_time"));
				importExportXML.setOuttime(rs.getString("out_time"));
				importExportXML.setMinused(rs.getString("min_used"));
				// importExportXML.setReturnDate(rs.getString("return_time"));
				importExportXML.setReturnDate(rs.getString("return_date"));
				finalResults.add(importExportXML);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return finalResults;
	}

	public List findGateRegisterReportStatisticsList(String query) {

		List<Object> finalResults = new ArrayList<Object>();

		try {

			con = getSession().connection();
			Prest = con.prepareStatement(query);
			rs = Prest.executeQuery();
			while (rs.next()) {

				ImportExportXML importExportXML = new ImportExportXML();

				importExportXML.setBreakupdate(rs.getString(1));
				importExportXML.setBreakupcount(rs.getString(2));
				finalResults.add(importExportXML);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return finalResults;
	}

	public List findTransferReportList(String query) {

		List<Object> finalResults = new ArrayList<Object>();

		try {

			con = getSession().connection();
			Prest = con.prepareStatement(query);
			rs = Prest.executeQuery();
			while (rs.next()) {

				ImportExportXML importExportXML = new ImportExportXML();

				importExportXML.setDeptName(rs.getString("dept_name"));
				importExportXML.setAccessNo(rs.getString("Access_No"));
				importExportXML.setDocument(rs.getString("Doc_Type"));
				importExportXML.setOrderDate(rs.getString("Order_Date"));
				importExportXML.setRecover(rs.getString("Recovery"));
				importExportXML.setRecoverDate(rs.getString("Recovery_Date"));
				importExportXML.setTitle(rs.getString("Title"));
				importExportXML.setAuthorName(rs.getString("Author_Name"));

				finalResults.add(importExportXML);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return finalResults;
	}

	public List findLibrary_Login_Excel(String query) {

		List<Object> finalResults = new ArrayList<Object>();

		try {

			con = getSession().connection();
			Prest = con.prepareStatement(query);
			rs = Prest.executeQuery();
			while (rs.next()) {

				ImportExportXML importExportXML = new ImportExportXML();

				importExportXML.setMemberCode(rs.getString("member_code"));
				importExportXML.setMemberName(rs.getString("member_name"));
				importExportXML.setGroup(rs.getString("group_name"));
				importExportXML.setDeptName(rs.getString("dept_name"));
				importExportXML.setReturnDate(rs.getString("last_visit"));
				finalResults.add(importExportXML);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return finalResults;
	}

	public List findLibrary_Login_Excel_Statistics(String query) {

		List<Object> finalResults = new ArrayList<Object>();

		try {

			con = getSession().connection();
			Prest = con.prepareStatement(query);
			rs = Prest.executeQuery();
			while (rs.next()) {

				ImportExportXML importExportXML = new ImportExportXML();

				importExportXML.setBreakupdate(rs.getString(1));
				importExportXML.setBreakupcount(rs.getString(2));
				finalResults.add(importExportXML);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return finalResults;
	}

	public List findMissResourceExcel(String query) {

		List<Object> finalResults = new ArrayList<Object>();

		try {

			con = getSession().connection();
			Prest = con.prepareStatement(query);
			rs = Prest.executeQuery();
			while (rs.next()) {

				ImportExportXML importExportXML = new ImportExportXML();

				importExportXML.setAccessNo(rs.getString("Access_No"));
				importExportXML.setDocument(rs.getString("Doc_Type"));
				importExportXML.setStatus(rs.getString("status"));
				importExportXML.setRecoverDate(rs.getString("mdate"));

				finalResults.add(importExportXML);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return finalResults;
	}

	public List findFreqUsedResourceExcel(String query, String type) {

		List<Object> finalResults = new ArrayList<Object>();

		try {

			con = getSession().connection();
			Prest = con.prepareStatement(query);
			rs = Prest.executeQuery();
			while (rs.next()) {

				ImportExportXML importExportXML = new ImportExportXML();

				importExportXML.setAccessNo(rs.getString("Access_No"));
				importExportXML.setTitle(rs.getString("title"));
				importExportXML.setPublisherName(rs.getString("publisher"));
				importExportXML.setDepartmentName(rs.getString("department"));
				if (!type.equalsIgnoreCase("unused"))
					importExportXML.setTotal(rs.getString("total"));
				finalResults.add(importExportXML);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return finalResults;
	}

	public List findJournalListExcel(String query) {

		List<Object> finalResults = new ArrayList<Object>();

		try {

			con = getSession().connection();
			Prest = con.prepareStatement(query);
			rs = Prest.executeQuery();
			while (rs.next()) {

				ImportExportXML importExportXML = new ImportExportXML();

				importExportXML.setTitle(rs.getString("title"));
				importExportXML.setFrequency(rs.getString("frequency"));
				importExportXML.setCountry(rs.getString("country"));
				importExportXML.setPublisherName(rs.getString("publisher"));
				importExportXML.setLanguage(rs.getString("language"));
				importExportXML.setDocument(rs.getString("doc_type"));

				finalResults.add(importExportXML);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return finalResults;
	}

	public List getCounterBreakupReportList(String query, String flag) {
		log4jLogger
				.info("================= start: [getCounterReportList] ====================== ");

		List<Object> finalResults = new ArrayList<Object>();

		try {
			con = getSession().connection();
			Prest = con.prepareStatement(query);
			rs = Prest.executeQuery();

			while (rs.next()) {
				ImportExportXML importExportXML = new ImportExportXML();
				importExportXML.setBreakupdate(rs.getString(1));
				importExportXML.setBreakupcount(rs.getString(2));
				if (flag.equalsIgnoreCase("Fine")) {
					importExportXML.setTotfine(rs.getString(3));
				}
				finalResults.add(importExportXML);
			}

			log4jLogger
					.info("================= End: [getCounterReportList] ====================== ");

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return finalResults;

	}

	public List findStockMasDisplayList(StockBean newbean) {

		log4jLogger.info("start===========findStockMasDisplay=====");
		StockBean SB = new StockBean();
		String strsql = "";
		List<Object> finalResults = new ArrayList();

		String sql = "";

		try {
			if (newbean.getBranchCode() > 0) {
				if (newbean.getflag().equals("rdStock")) {
					sql = " and stock_view.branch_code="
							+ newbean.getBranchCode();
				} else if (newbean.getflag().equals("rdNotVerify")) {
					sql = " and stock_view_yes.branch_code="
							+ newbean.getBranchCode();
				} else if (newbean.getflag().equals("rdMissing")) {
					sql = " and stock_view_missing.branch_code="
							+ newbean.getBranchCode();
				} else if (newbean.getflag().equals("rdLost")) {
					sql = " and stock_view_lost.branch_code="
							+ newbean.getBranchCode();
				} else if (newbean.getflag().equals("rdWithdrawn")) {
					sql = " and stock_view_withdrawn.branch_code="
							+ newbean.getBranchCode();
				} else if (newbean.getflag().equals("rdIssued")) {
					sql = " and stock_view_issued.branch_code="
							+ newbean.getBranchCode();
				} else if (newbean.getflag().equals("rdBinding")) {
					sql = " and stock_view_binding.branch_code="
							+ newbean.getBranchCode();
				} else if (newbean.getflag().equals("rdDamaged")) {
					sql = " and stock_view_damaged.branch_code="
							+ newbean.getBranchCode();
				} else if (newbean.getflag().equals("rdTransferred")) {
					sql = " and stock_view_transfer.branch_code="
							+ newbean.getBranchCode();
				} else if (newbean.getflag().equals("rdVerifyIssued")) {
					sql = " and stock_view_verify_issue.branch_code="
							+ newbean.getBranchCode();
				}
			}

			if (newbean.getflag().equals("rdStock")) {
				if (newbean.getdoctype().equals("ALL")) {
					strsql = "Select stock_view.* from stock_view,sort_book Where Exists (Select Access_No from stock_mas where Access_No= stock_view.Access_No) and stock_view.Access_No = sort_book.Access_No "
							+ sql + " order by N1,N2,N3";
				} else {
					strsql = "Select stock_view.* from stock_view,sort_book Where Exists (Select Access_No from stock_mas where Access_No= stock_view.Access_No) and stock_view.Access_No = sort_book.Access_No and stock_view.doc_type='"
							+ newbean.getdoctype()
							+ "' "
							+ sql
							+ " order by N1,N2,N3";
				}
			} else if (newbean.getflag().equals("rdNotVerify")) {
				if (newbean.getdoctype().equals("ALL")) {
					strsql = "Select stock_view_yes.* from stock_view_yes, sort_book Where stock_view_yes.Access_No = sort_book.Access_No "
							+ sql + " Order by N1,N2,N3 ";
				} else {
					strsql = "Select stock_view_yes.* from stock_view_yes, sort_book Where stock_view_yes.Access_No = sort_book.Access_No and stock_view_yes.doc_type='"
							+ newbean.getdoctype()
							+ "'  "
							+ sql
							+ " Order by N1,N2,N3 ";
				}
			} else if (newbean.getflag().equals("rdMissing")) {
				if (newbean.getdoctype().equals("ALL")) {
					strsql = "Select stock_view_missing.* from stock_view_missing, sort_book Where stock_view_missing.Access_No = sort_book.Access_No "
							+ sql + " Order by N1,N2,N3";
				} else {
					strsql = "Select stock_view_missing.* from stock_view_missing, sort_book Where stock_view_missing.Access_No = sort_book.Access_No and stock_view_missing.doc_type='"
							+ newbean.getdoctype()
							+ "' "
							+ sql
							+ " Order by N1,N2,N3";
				}
			} else if (newbean.getflag().equals("rdLost")) {
				if (newbean.getdoctype().equals("ALL")) {
					strsql = "Select stock_view_lost.* from stock_view_lost, sort_book Where stock_view_lost.Access_No = sort_book.Access_No "
							+ sql + " Order by N1,N2,N3";
				} else {
					strsql = "Select stock_view_lost.* from stock_view_lost, sort_book Where stock_view_lost.Access_No = sort_book.Access_No and stock_view_lost.doc_type='"
							+ newbean.getdoctype()
							+ "' "
							+ sql
							+ " Order by N1,N2,N3";
				}
			} else if (newbean.getflag().equals("rdWithdrawn")) {
				if (newbean.getdoctype().equals("ALL")) {
					strsql = "Select stock_view_withdrawn.* from stock_view_withdrawn, sort_book Where stock_view_withdrawn.Access_No = sort_book.Access_No  "
							+ sql + " Order by N1,N2,N3";
				} else {
					strsql = "Select stock_view_withdrawn.* from stock_view_withdrawn, sort_book Where stock_view_withdrawn.Access_No = sort_book.Access_No and stock_view_withdrawn.doc_type='"
							+ newbean.getdoctype()
							+ "' "
							+ sql
							+ " Order by N1,N2,N3";
				}
			} else if (newbean.getflag().equals("rdIssued")) {
				if (newbean.getdoctype().equals("ALL")) {
					strsql = "Select stock_view_issued.* from stock_view_issued, sort_book Where stock_view_issued.Access_No = sort_book.Access_No  "
							+ sql + " Order by N1,N2,N3";
				} else {
					strsql = "Select stock_view_issued.* from stock_view_issued, sort_book Where stock_view_issued.Access_No = sort_book.Access_No and stock_view_issued.doc_type='"
							+ newbean.getdoctype()
							+ "' "
							+ sql
							+ " Order by N1,N2,N3";
				}
			} else if (newbean.getflag().equals("rdBinding")) {
				if (newbean.getdoctype().equals("ALL")) {
					strsql = "Select stock_view_binding.* from stock_view_binding, sort_book Where stock_view_binding.Access_No = sort_book.Access_No  "
							+ sql + " Order by N1,N2,N3";
				} else {
					strsql = "Select stock_view_binding.* from stock_view_binding, sort_book Where stock_view_binding.Access_No = sort_book.Access_No and stock_view_binding.doc_type='"
							+ newbean.getdoctype()
							+ "' "
							+ sql
							+ " Order by N1,N2,N3";
				}
			} else if (newbean.getflag().equals("rdDamaged")) {
				if (newbean.getdoctype().equals("ALL")) {
					strsql = "Select stock_view_damaged.* from stock_view_damaged, sort_book Where stock_view_damaged.Access_No = sort_book.Access_No "
							+ sql + " Order by N1,N2,N3";
				} else {
					strsql = "Select stock_view_damaged.* from stock_view_damaged, sort_book Where stock_view_damaged.Access_No = sort_book.Access_No and stock_view_damaged.doc_type='"
							+ newbean.getdoctype()
							+ "' "
							+ sql
							+ " Order by N1,N2,N3";
				}
			} else if (newbean.getflag().equals("rdTransferred")) {
				if (newbean.getdoctype().equals("ALL")) {
					strsql = "Select stock_view_transfer.* from stock_view_transfer, sort_book Where stock_view_transfer.Access_No = sort_book.Access_No  "
							+ sql + " Order by N1,N2,N3";
				} else {
					strsql = "Select stock_view_transfer.* from stock_view_transfer, sort_book Where stock_view_transfer.Access_No = sort_book.Access_No and stock_view_transfer.doc_type='"
							+ newbean.getdoctype()
							+ "' "
							+ sql
							+ " Order by N1,N2,N3";
				}
			} else if (newbean.getflag().equals("rdVerifyIssued")) {
				if (newbean.getdoctype().equals("ALL")) {
					strsql = "Select stock_view_verify_issue.* from stock_view_verify_issue, sort_book Where stock_view_verify_issue.Access_No = sort_book.Access_No  "
							+ sql + " Order by N1,N2,N3";
				} else {
					strsql = "Select stock_view_verify_issue.* from stock_view_verify_issue, sort_book Where stock_view_verify_issue.Access_No = sort_book.Access_No and stock_view_verify_issue.doc_type='"
							+ newbean.getdoctype()
							+ "' "
							+ sql
							+ " Order by N1,N2,N3";
				}
			}

			con = getSession().connection();
			Prest = con.prepareStatement(strsql);
			rs = Prest.executeQuery();

			while (rs.next()) {
				ImportExportXML importExportXML = new ImportExportXML();

				importExportXML.setAccessNo(rs.getString("Access_No"));
				importExportXML.setTitle(rs.getString("Title"));
				importExportXML.setAuthorName(rs.getString("Author"));
				importExportXML.setPublisherName(rs.getString("publisher"));
				importExportXML.setYear(rs.getString("Year"));
				importExportXML.setDocument(rs.getString("Doc_Type"));
				importExportXML.setPrice(rs.getString("BPrice"));
				importExportXML.setAvailability(rs.getString("Availability"));

				finalResults.add(importExportXML);
			}

		} catch (Exception localException1) {
			try {
				if (rs != null) {
					rs.close();
				}
				if (Prest != null) {
					Prest.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (Prest != null) {
					Prest.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return finalResults;

	}

	public List findQBReportList(String query) {

		log4jLogger
				.info("================= start: [getQBReportExcelList] ====================== ");

		List<Object> finalResults = new ArrayList<Object>();
		int totbook = 0;
		try {
			con = getSession().connection();
			Prest = con.prepareStatement(query);
			rs = Prest.executeQuery();

			while (rs.next()) {
				ImportExportXML importExportXML = new ImportExportXML();
				importExportXML.setQBCode(rs.getString("QB_Code"));
				importExportXML.setSubCode(rs.getString("Sub_Code"));
				importExportXML.setSubName(rs.getString("Sub_Name"));
				importExportXML.setCourse(rs.getString("Course_name"));
				// importExportXML.setCourseMajor(rs.getString("Course_major"));
				importExportXML.setDeptName(rs.getString("Dept_Name"));
				importExportXML.setYear(rs.getString("Year"));
				importExportXML.setSemester(rs.getString("Semester"));
				importExportXML.setMonth(rs.getString("Month"));
				finalResults.add(importExportXML);
			}

			log4jLogger
					.info("================= End: [getQBReportExcelList] ====================== ");

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return finalResults;
	}

	public List findFreqUsedMemberExcel(String query, String type) {

		List<Object> finalResults = new ArrayList<Object>();

		try {

			con = getSession().connection();
			Prest = con.prepareStatement(query);
			rs = Prest.executeQuery();
			while (rs.next()) {

				ImportExportXML importExportXML = new ImportExportXML();

				if (type.equalsIgnoreCase("frequency")) {
					System.out.println("::::Inside Frequency Excel::::::");
					importExportXML.setMemberCode(rs.getString("member_code"));
					importExportXML.setMemberName(rs.getString("member_name"));
					importExportXML
							.setDepartmentName(rs.getString("dept_name"));
					importExportXML.setCourseName(rs.getString("course_name"));
					importExportXML.setCyear(rs.getString("cyear"));
					importExportXML.setTotal(rs.getString("total"));
				} else if (type.equalsIgnoreCase("gate")) {
					System.out
							.println("::::::::Inside Best User Award::::::::");
					importExportXML.setMemberCode(rs.getString("member_code"));
					importExportXML.setMemberName(rs.getString("member_name"));
					importExportXML
							.setDepartmentName(rs.getString("dept_name"));
					importExportXML.setCyear(rs.getString("cyear"));
					importExportXML.setTotal(rs.getString("total"));
					importExportXML.setTotalMins(rs.getString("totalminute"));
				} else if (type.equalsIgnoreCase("member")) {
					System.out.println("::::Inside Not Member Excel::::::");
					importExportXML.setMemberCode(rs.getString("member_code"));
					importExportXML.setMemberName(rs.getString("member_name"));
					importExportXML.setDesigName(rs.getString("designation"));
					importExportXML.setCyear(rs.getString("course_year"));
					importExportXML
							.setDepartmentName(rs.getString("dept_name"));

				}
				finalResults.add(importExportXML);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return finalResults;
	}

}
