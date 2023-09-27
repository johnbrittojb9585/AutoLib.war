package Lib.Auto.Report;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.jasperreports.engine.JRAbstractExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;

import org.apache.log4j.Logger;

import Common.Security;
import Common.Security_Counter;
import Common.businessutil.BusinessServiceFactory;
import Common.businessutil.circulation.CirculationService;
import Common.businessutil.importexportexcel.ExceImportExportService;
import Common.businessutil.importexportxml.ImportExportXMLService;
import Common.businessutil.reports.ReportQueryUtill;
import Common.businessutil.reports.ReportService;
import Lib.Auto.Book.bookbean;
import Lib.Auto.Department.DepartmentBean;
import Lib.Auto.Group.GroupBean;
import Lib.Auto.Member.MemberBean;

import com.google.gson.Gson;
import com.library.autolib.portal.dbconnectionutil.SessionHibernateUtil;

@WebServlet("/CounterReport/CounterReportsAll")
public class ReportAll extends HttpServlet implements ReportQueryUtill {
	String term = "";

	private static Logger log4jLogger = Logger.getLogger(ReportAll.class);

	private static final long serialVersionUID = 1L;

	java.sql.Connection con = null;

	public void doGet(HttpServletRequest request, HttpServletResponse response) {

		performTask(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) {

		performTask(request, response);
	}

	public void performTask(HttpServletRequest request,
			HttpServletResponse response) {

		try {
			reportbean ob = new reportbean();
			ReportService rs = BusinessServiceFactory.INSTANCE
					.getReportService();

			HttpSession session = request.getSession(true);
			String res = Security.checkSecurity(1, session, response, request);

			if (res.equalsIgnoreCase("Failure")) {
				response.sendRedirect("/AutoLib/Tree/sessiontimeout.jsp");
				return;
			}

			response.setContentType("application/json");
			Object obj = request.getParameter("flagExcel");

			try {
				String term = request.getParameter("txtmembercode");
				if (!term.equalsIgnoreCase(null) && !term.equalsIgnoreCase("")
						&& obj == null) {
					System.out.println("Data from ajax call " + term);

					ArrayList<MemberBean> list = rs
							.getCounterReportUserAutoSearch(term);
					for (MemberBean user : list) {
						System.out.println(user.getName());
					}

					String searchList = new Gson().toJson(list);

					response.getWriter().write(searchList);

					System.out
							.println("Json Value@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"
									+ searchList);

				}
			} catch (Exception e) {
				// e.printStackTrace();
			}

			try {
				String term = request.getParameter("txtaccessno");

				if (!term.equalsIgnoreCase(null) && !term.equalsIgnoreCase("")
						&& obj == null) {
					// System.out.println("Data from ajax call " + term);

					ArrayList<bookbean> list = rs
							.getCounterReportAccessNoAutoSearch(term);
					for (bookbean user : list) {
						// System.out.println(user.getAccessNo());
					}

					String searchList = new Gson().toJson(list);

					response.getWriter().write(searchList);

					// System.out.println("Json Value@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+searchList);
				}
			} catch (Exception e) {
				// e.printStackTrace();
			}

			try {
				String term = request.getParameter("Gname");

				if (!term.equalsIgnoreCase(null) && !term.equalsIgnoreCase("")
						&& obj == null) {
					// System.out.println("Data from ajax call " + term);

					ArrayList<GroupBean> list = rs
							.getCounterReportGroupAutoSearch(term);
					for (GroupBean user : list) {
						// System.out.println(user.getName());
					}

					String searchList = new Gson().toJson(list);

					response.getWriter().write(searchList);

					// System.out.println("Json Value@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+searchList);
				}
			} catch (Exception e) {
				// e.printStackTrace();
			}

			try {
				String term = request.getParameter("Dname");

				if (!term.equalsIgnoreCase(null) && !term.equalsIgnoreCase("")
						&& obj == null) {
					// System.out.println("Data from ajax call " + term);

					ArrayList<DepartmentBean> list = rs
							.getCounterReportDeptAutoSearch(term);
					for (DepartmentBean user : list) {
						// System.out.println(user.getName());
					}

					String searchList = new Gson().toJson(list);

					response.getWriter().write(searchList);

					// System.out.println("Json Value@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+searchList);
				}
			} catch (Exception e) {
				// e.printStackTrace();
			}

			ImportExportXMLService importExportXMLService = BusinessServiceFactory.INSTANCE
					.getImportExportXMLService();
			ExceImportExportService csvImportExportService = BusinessServiceFactory.INSTANCE
					.getExceImportExportService();

			List ReportArrylist;
			String indexPage = "";
			String frmdt = "", todt = "";
			String txtreporttype = "", columnName = "", order = "", orderQuery = "", flag = null, flag1 = null, curIssue = "", report_type = "", namedQuery = "", SQL_Query = "", Chart_GroupQuery = "", filterQuery = "", reportName = "", reportTitle = "";

			StringBuffer sb = new StringBuffer();

			flag = request.getParameter("flagExcel");
			curIssue = request.getParameter("curIssue");
			String Dname = request.getParameter("Dname");
			String Sname = request.getParameter("Sname");
			String Year = request.getParameter("Year");
			flag1 = request.getParameter("flagStat");

			System.out.println("----------------"
					+ request.getParameter("txtfdate"));

			if (!request.getParameter("txtfdate").equalsIgnoreCase("")
					&& !request.getParameter("txttdate").equalsIgnoreCase("")) {
				frmdt = Security.TextDate_member(request
						.getParameter("txtfdate"));
				todt = Security.TextDate_member(request
						.getParameter("txttdate"));
			}

			CirculationService ss = BusinessServiceFactory.INSTANCE
					.getCirculationService();
			txtreporttype = request.getParameter("reporttype");
			report_type = request.getParameter("report_type");
			con = SessionHibernateUtil.getSession().connection();
			if (flag.equalsIgnoreCase("delete")) {
				String mcode = request.getParameter("txtmembercode");
				// String count=ss.deletetransaction(mcode);
			}
			if (request.getParameter("gettodayreport").equals("todayIssue")) {
				log4jLogger.info("start===========todayIssueList=====");
				ob = new reportbean();
				SQL_Query = "";

				SQL_Query = SQL_Query
						+ " and  issue_type='ISSUE' and  issue_date=curdate() order by title asc";

				ReportArrylist = rs.gettodayIssueListDetails(SQL_Query);

				session.setAttribute("IssueReportArrylist", ReportArrylist);
				indexPage = "/Home/todayissuedetails.jsp";
				dispatch(request, response, indexPage);

			}

			if (request.getParameter("gettodayreport").equals("todayReturn")) {
				log4jLogger.info("start===========todayReturnList=====");
				ob = new reportbean();
				SQL_Query = "";

				SQL_Query = SQL_Query
						+ " and return_date=curdate() and issue_type='RETURN' order by title asc";

				ReportArrylist = rs.gettodayReturnListDetails(SQL_Query);

				session.setAttribute("ReturnReportArrylist", ReportArrylist);
				indexPage = "/Home/todayreturndetails.jsp";
				dispatch(request, response, indexPage);

			}

			if (request.getParameter("gettodayreport").equals("todayRenewal")) {
				log4jLogger.info("start===========todayReturnList=====");
				ob = new reportbean();
				SQL_Query = "";

				SQL_Query = SQL_Query
						+ " and  issue_date=curdate() and issue_type='RENEW' order by title asc";

				ReportArrylist = rs.gettodayRenewalListDetails(SQL_Query);

				session.setAttribute("RenewalReportArrylist", ReportArrylist);
				indexPage = "/Home/todayrenewaldetails.jsp";
				dispatch(request, response, indexPage);

			}

			if (request.getParameter("gettodayreport").equals(
					"todayamountdisplay")) {
				log4jLogger.info("start===========todayamountdisplay=====");
				ob = new reportbean();
				SQL_Query = "";

				SQL_Query = SQL_Query
						+ " and trans_date=curdate() and trans_amount<>'0.00'  order by title desc";

				ReportArrylist = rs.gettodayTransAmountDetails(SQL_Query);

				session.setAttribute("TransAmountListReportArrylist",
						ReportArrylist);
				indexPage = "/Home/todayreturnfinedetails.jsp";
				dispatch(request, response, indexPage);

			}

			if (request.getParameter("gettodayreport").equals(
					"todaypaidamountdisplay")) {
				log4jLogger.info("start===========todaypaidamountdisplay=====");
				ob = new reportbean();
				SQL_Query = "";

				SQL_Query = SQL_Query
						+ " and payment_date=curdate() order by bill_no";

				ReportArrylist = rs.gettodayPaidDetails(SQL_Query);

				session.setAttribute("todayPaidListReportArrylist",
						ReportArrylist);
				indexPage = "/Home/todayreturnfinepaiddetails.jsp";
				dispatch(request, response, indexPage);
			}

			if (!flag.equals("") && !flag.isEmpty()) {

				if (txtreporttype.equals("Issue")
						|| txtreporttype.equals("Renewal")
						|| txtreporttype.equals("curIssue")) {
					columnName = "issue_date";
				} else if (txtreporttype.equals("Return")) {
					columnName = "return_date";
				} else if (txtreporttype.equals("Renewal")) {
					columnName = "issue_date";
				} else if (txtreporttype.equals("Reserve")) {
					columnName = "res_date";
				} else if (txtreporttype.equals("Duereminder")) {
					columnName = "due_date";
				} else if (txtreporttype.equals("Resreminder")) {
					columnName = "res_date";
				} else if (txtreporttype.equals("Fine")) {
					columnName = "trans_date";
				} else {
					columnName = "";
				}
				// =======================FOR
				// STATISTICS-=============================
				if (flag.equalsIgnoreCase("statistics")) {
					System.out
							.println("=============inside statistics flag=================");
					if (!request.getParameter("txtfdate").equalsIgnoreCase("")
							&& !request.getParameter("txttdate")
									.equalsIgnoreCase("")
							&& flag1.equalsIgnoreCase("statistics")
							&& txtreporttype.equals("Issue")) {

						System.out
								.println("=============inside issue statistics=============");
						filterQuery = filterQuery
								+ " and "
								+ columnName
								+ " between '"
								+ Security_Counter.TextDate_Insert(request
										.getParameter("txtfdate"))
								+ "' and '"
								+ Security_Counter.TextDate_Insert(request
										.getParameter("txttdate"))
								+ "' group by issue_date,dept_name";
					}

					if (!request.getParameter("txtfdate").equalsIgnoreCase("")
							&& !request.getParameter("txttdate")
									.equalsIgnoreCase("")
							&& flag1.equalsIgnoreCase("statistics")
							&& txtreporttype.equals("Return")) {

						filterQuery = filterQuery
								+ " and "
								+ columnName
								+ " between '"
								+ Security_Counter.TextDate_Insert(request
										.getParameter("txtfdate"))
								+ "' and '"
								+ Security_Counter.TextDate_Insert(request
										.getParameter("txttdate"))
								+ "' group by return_date,dept_name";
					}
					if (!request.getParameter("txtfdate").equalsIgnoreCase("")
							&& !request.getParameter("txttdate")
									.equalsIgnoreCase("")
							&& flag1.equalsIgnoreCase("statistics")
							&& txtreporttype.equals("curIssue")) {

						filterQuery = filterQuery
								+ " and "
								+ columnName
								+ " between '"
								+ Security_Counter.TextDate_Insert(request
										.getParameter("txtfdate"))
								+ "' and '"
								+ Security_Counter.TextDate_Insert(request
										.getParameter("txttdate"))
								+ "' group by issue_date,dept_name";
					}

				}

				// =======================end
				// STATISTICS-================================
				else {
					System.out
							.println("=============inside else  date=================");
					if (!request.getParameter("txtfdate").equalsIgnoreCase("")
							&& !request.getParameter("txttdate")
									.equalsIgnoreCase("")) {
						filterQuery = filterQuery
								+ " and "
								+ columnName
								+ " between '"
								+ Security_Counter.TextDate_Insert(request
										.getParameter("txtfdate"))
								+ "' and '"
								+ Security_Counter.TextDate_Insert(request
										.getParameter("txttdate")) + "'";

					}

				}

				if (!request.getParameter("Dname").equalsIgnoreCase("")
						&& !request.getParameter("Dname").isEmpty()) {
					filterQuery = filterQuery + " and dept_name='"
							+ request.getParameter("Dname") + "'";
				}
				if (!request.getParameter("Gname").equalsIgnoreCase("")
						&& !request.getParameter("Gname").isEmpty()) {
					filterQuery = filterQuery + " and group_name='"
							+ request.getParameter("Gname") + "'";
				}
				if (!request.getParameter("txtmembercode").equalsIgnoreCase("")
						&& !request.getParameter("txtmembercode").isEmpty()) {
					filterQuery = filterQuery + " and member_code='"
							+ request.getParameter("txtmembercode") + "'";
				}
				if (!request.getParameter("txtaccessno").equalsIgnoreCase("")
						&& !request.getParameter("txtaccessno").isEmpty()) {
					filterQuery = filterQuery + " and access_no='"
							+ request.getParameter("txtaccessno") + "'";
				}
				if (!request.getParameter("txtdoctype").equalsIgnoreCase("ALL")
						&& !request.getParameter("txtdoctype").isEmpty()
						&& !request.getParameter("reporttype").equals("Fine")) {
					filterQuery = filterQuery + " and doc_type='"
							+ request.getParameter("txtdoctype") + "'";
				}
				if (!request.getParameter("Year").equalsIgnoreCase("ALL")
						&& !request.getParameter("Year").isEmpty()) {
					filterQuery = filterQuery + " and cyear='"
							+ request.getParameter("Year") + "'";
				}

				if (!request.getParameter("order1").equals("NO")) {
					order = order + request.getParameter("order1");
				}
				if (!request.getParameter("order2").equals("NO")) {
					order = order + "," + request.getParameter("order2");
				}
				if (!request.getParameter("order3").equals("NO")) {
					order = order + "," + request.getParameter("order3");
				}
				System.out.println("::::txtReportType::::::::"+txtreporttype);
				
				if (txtreporttype.equals("curIssue")) {
					System.out.println("::::ReportType::::::::"+report_type);
					if (report_type.equals("listing")) {
						reportName = "Current_Issue_Report";
						reportTitle = "Current_Issue_Report";
						namedQuery = ReportQueryUtill.Query_CurrentIssue_Report;
						orderQuery = " Order by convert(access_no,signed) asc";
					} else if (report_type.equals("breakup")) {
						reportName = "Current_Issue_Breakup_Report";
						reportTitle = "Current_Issue-Breakup-Report";
						namedQuery = "SELECT DATE_FORMAT(issue_date,'%d/%m/%Y') AS issue_date,COUNT(*) as total_books FROM issue_mas WHERE 2>1 ";
						orderQuery = "  GROUP BY issue_date ORDER BY Date(issue_date),COUNT(*) asc  ";
					} else if (report_type.equals("cumulative")) {
						reportName = "Current_Issue_cumulative_Report";
						reportTitle = "Current_Issue_cumulative_Report";
						namedQuery = "SELECT COUNT(*) AS total_books FROM issue_mas WHERE 2>1 ";
					} else if (flag.equalsIgnoreCase("statistics")) {
						reportName = "Issue_Report_statistics";
						reportTitle = "Issue-Report";
						namedQuery = ReportQueryUtill.Query_curIssue_statistics;
					} else
					{
						reportName = "Current_Issue_Breakup_Report";
						reportTitle = "Current_Issue-Breakup-Report";
						namedQuery = "SELECT DATE_FORMAT(issue_date,'%d/%m/%Y') AS issue_date,COUNT(*) as total_books FROM issue_mas WHERE 2>1 ";
						orderQuery = "  GROUP BY issue_date ORDER BY Date(issue_date),COUNT(*) asc  ";
					}
				}

				if (txtreporttype.equals("Issue")) {

					if (report_type.equals("listing")) {
						reportName = "Issue_Report";
						reportTitle = "Issue-Report";
						namedQuery = ReportQueryUtill.Query_Issue_Report;
					} 
					else if (report_type.equals("breakup"))
                        {
						reportName = "Issue_Breakup_Report";
						reportTitle = "Issue-Breakup-Report";
						namedQuery = ReportQueryUtill.Query_Issue_Breakup_Report;
						orderQuery = "  GROUP BY issue_date ORDER BY Date(issue_date),COUNT(*) asc  ";
					} else if (report_type.equals("cumulative"))
					{
						reportName = "Issue_cumulative_Report";
						reportTitle = "Issue_cumulative_Report";
						namedQuery = "SELECT COUNT(*) AS total_books FROM issue_transaction WHERE 2>1 ";

					} else if (flag.equalsIgnoreCase("statistics")) 
					{

						reportName = "Issue_Report_statistics";
						reportTitle = "Issue-Report-statistics";
						namedQuery = ReportQueryUtill.Query_Issue_statistics;

					}

					else if (!flag.equalsIgnoreCase("chart"))
					{
						namedQuery = ReportQueryUtill.Query_Issue_Report;
					} else {

						reportTitle = "Issue-Report-Chart";
						namedQuery = ReportQueryUtill.Query_Issue_Chart;
						Chart_GroupQuery = "GROUP BY YEAR,MONTH(issue_date) ORDER BY MONTH(issue_date),YEAR";
					}

				}

				else if (txtreporttype.equals("Return"))
				 {
					if (report_type.equals("listing"))
					 {
						reportName = "Return_Report";
						reportTitle = "Return-Report";
						namedQuery = ReportQueryUtill.Query_Return_Report;
					}
					else if (report_type.equals("breakup"))
					{
						reportName = "Return_Breakup_Report";
						reportTitle = "Return-Breakup-Report";
						namedQuery = " SELECT DATE_FORMAT(return_date,'%d/%m/%Y') AS return_date,COUNT(*) as total_books FROM issue_history WHERE 2>1 and issue_type='return' ";
						orderQuery = "  GROUP BY return_date ORDER BY Date(return_date),COUNT(*) asc  ";
					}
					else if (report_type.equals("cumulative"))
					{
						reportName = "Return_cumulative_Report";
						reportTitle = "Return-cumulative-Report";
						namedQuery = "SELECT COUNT(*) AS total_books FROM issue_history WHERE 2>1 and issue_type='return'";

					}
					else if (flag.equalsIgnoreCase("statistics"))
					{
						System.out.println("==================================== "+ flag);				
						reportName = "Return_Report_statistics";
						reportTitle = "Return-Report-statistics";
						namedQuery = ReportQueryUtill.Query_Return_statistics;
					} 
					else if (!flag.equalsIgnoreCase("chart"))
					{
						reportName = "Return_Breakup_Report";
						reportTitle = "Return-Breakup-Report";
						namedQuery = " SELECT DATE_FORMAT(return_date,'%d/%m/%Y') AS return_date,COUNT(*) as total_books FROM issue_history WHERE 2>1 and issue_type='return' ";
						orderQuery = "  GROUP BY return_date ORDER BY Date(return_date),COUNT(*) asc  ";												
					} 
					else 
					{
						reportTitle = "Return-Report-Chart";
						namedQuery = ReportQueryUtill.Query_Return_Chart;
						Chart_GroupQuery = "GROUP BY YEAR,MONTH(return_date) ORDER BY MONTH(return_date),YEAR";
					}
				
				}

				else if (txtreporttype.equals("Renewal")) 
				{
					if (report_type.equals("listing"))
					{
					reportName = "Renew_Report";
					reportTitle = "Renew-Report";
					namedQuery = ReportQueryUtill.Query_Renew_Report;
				} else if (report_type.equals("breakup"))
				{
					reportName = "Renewal_Breakup_Report";
					reportTitle = "Renewal-Breakup-Report";
					namedQuery = " SELECT DATE_FORMAT(issue_date,'%d/%m/%Y') AS issue_date,COUNT(*) AS total_books FROM renewbooks WHERE issue_type='RENEW' ";
					orderQuery = "  GROUP BY issue_date ORDER BY Date(issue_date),COUNT(*) asc  ";
				}
				else if (report_type.equals("cumulative"))
				{
					reportName = "Renewal_cumulative_Report";
					reportTitle = "Renewal-cumulative-Report";
					namedQuery = " SELECT DATE_FORMAT(issue_date,'%d/%m/%Y') AS issue_date,COUNT(*) AS total_books FROM renewbooks WHERE issue_type='RENEW'  ";
				}
				else
				{
					reportName = "Renewal_Breakup_Report";
					reportTitle = "Renewal-Breakup-Report";
					namedQuery = " SELECT DATE_FORMAT(issue_date,'%d/%m/%Y') AS issue_date,COUNT(*) AS total_books FROM renewbooks WHERE issue_type='RENEW' ";
					orderQuery = "  GROUP BY issue_date ORDER BY Date(issue_date),COUNT(*) asc  ";
				}
				}

				else if (txtreporttype.equals("Reserve")) {
					reportName = "Reserve_Report";
					reportTitle = "Reserve-Report";
					namedQuery = ReportQueryUtill.Query_Reserve_Report;
				}

				else if (txtreporttype.equals("Resreminder")) {
					reportName = "ReserveReminder_Report";
					reportTitle = "Reservation-Reminder";
					namedQuery = ReportQueryUtill.Query_ReserveReminder_Report;
				}

				else if (txtreporttype.equals("Duereminder")) {
					
				if (report_type.equals("listing"))
				{
					reportName = "DueReminder_Report";
					reportTitle = "Due-Reminder";
					namedQuery = ReportQueryUtill.Query_DueReminder_Report;	
					}			
					else if(report_type.equals("breakup"))
					{
						System.out.println("++++++++BREAKUP+===");
						reportName = "DueReminder_Breakup_Report";
						reportTitle = "Due-Reminder_Breakup";
						namedQuery = "SELECT DATE_FORMAT(Due_date,'%d/%m/%Y') AS Due_date,COUNT(*) AS total_books FROM Due_Reminder WHERE 2>1";
						orderQuery = "  GROUP BY Due_date ORDER BY Date(Due_date),COUNT(*) asc  ";
					}
					else if(report_type.equals("cumulative"))
					{
						reportName = "DueReminder_cumulative_Report";
					reportTitle = "Due-Reminder_cumulative";
						namedQuery = "SELECT COUNT(*) AS total_books FROM Due_Reminder WHERE 2>1";	
					}
					else
					{
						reportName = "DueReminder_Breakup_Report";
						reportTitle = "Due-Reminder_Breakup";
						namedQuery = "SELECT DATE_FORMAT(Due_date,'%d/%m/%Y') AS Due_date,COUNT(*) AS total_books FROM Due_Reminder WHERE 2>1";
						orderQuery = "  GROUP BY Due_date ORDER BY Date(Due_date),COUNT(*) asc  ";
					}
				}

				else if (txtreporttype.equals("Fine")) {

					if (report_type.equals("cumulative")) {
						reportName = "Trans_Cumulative_Report";
						reportTitle = "Fine-Cumulative-Report";
						namedQuery = ReportQueryUtill.Query_FineCumulative_Report;
					} else if (report_type.equals("listing")) {
						reportName = "Trans_Listing_Report";
						reportTitle = "Fine-Listing-Report";
						namedQuery = "SELECT Member_Code,Member_Name,Trans_No,DATE_FORMAT(Trans_Date,'%d/%m/%Y') AS Trans_Date,Access_No,DATE_FORMAT(Due_Date,'%d/%m/%Y') AS Due_Date,Trans_Amount,Remarks FROM trans_member_view  WHERE Trans_Head='OVERDUE' ";
						order = " ABS(Trans_Amount) desc ";
					} else if (report_type.equals("breakup")) {
						reportName = "Trans_Breakup_Report";
						reportTitle = "Fine-Breakup-Report";
						namedQuery = ReportQueryUtill.Query_FineBreakup_Report;
					}
				}
				if (!flag.equalsIgnoreCase("chart")) {
					if (!order.equals("") && !order.isEmpty()) {
						sb.append(namedQuery + filterQuery + " order by "
								+ order);
						System.out
								.println("===============orderquery======================");
					} else {
						System.out
								.println("===============filterquery======================");
						sb.append(namedQuery + filterQuery + orderQuery);
					}
				}

				else { // Chart

					if (flag.equalsIgnoreCase("chart")) {
						if (txtreporttype.equalsIgnoreCase("issue")) {
							reportTitle = "Issue-Report-Chart";
							namedQuery = ReportQueryUtill.Query_Issue_Chart;
							Chart_GroupQuery = " GROUP BY YEAR,MONTH(issue_date) ORDER BY MONTH(issue_date),YEAR ";
						} else if (txtreporttype.equalsIgnoreCase("return")) {
							reportTitle = "Return-Report-Chart";
							namedQuery = ReportQueryUtill.Query_Return_Chart;
							Chart_GroupQuery = " GROUP BY YEAR,MONTH(return_date) ORDER BY MONTH(return_date),YEAR ";
						}
					}

					sb.append(namedQuery + filterQuery + " " + orderQuery);
				}

				log4jLogger.info("::::::::::::::SQL Query::::::::::::: "
						+ sb.toString());
				boolean checkData = rs.getCheckData(sb.toString());

				if (checkData) {
					// no data
					log4jLogger
							.info("----------------NO DATA FOUND-------------------");
					indexPage = "/CounterReport/index.jsp?check=NoData";
					dispatch(request, response, indexPage);

				} 
				else 
				{
					if (flag.equalsIgnoreCase("PdfReport")) 
					{
						log4jLogger.info(":::::::::::::::inside pdf report::::::::::::::::::::");
						Map<String, Object> parameters = new HashMap<String, Object>();
						parameters.put("CMP_NAME",ReportQueryUtill.COMPANY_NAME);								
						parameters.put("CMP_ADD", ReportQueryUtill.COMPANY_ADD);
						parameters.put("ReportTitle", reportTitle);
						parameters.put("All_Query", sb.toString());
						if (!request.getParameter("txtfdate").equalsIgnoreCase("")&& !request.getParameter("txttdate").equalsIgnoreCase(""))
						 {		
								
										
							parameters.put("txtfdate","From : " + Security.getdate(frmdt).replace('-', '/'));
							parameters.put("txttdate", "To : "	+ Security.getdate(todt).replace('-', '/'));						
						} 
						else 
						{
							parameters.put("txtfdate", "");
							parameters.put("txttdate", "");
						}
						parameters.put("Dname", "Department : " + Dname);
						InputStream inputStream1 = getServletContext().getResourceAsStream("/Report/" + reportName + ".jasper");																
						System.out.println(">>>>>>>>>>>>>." + reportName);
						JasperReport report = (JasperReport) JRLoader.loadObject(inputStream1);								
						JasperPrint jasperPrint = JasperFillManager.fillReport(report, parameters, con);								
						JRAbstractExporter exporterPDF = new JRPdfExporter();
						exporterPDF.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);							
						exporterPDF.setParameter(JRExporterParameter.OUTPUT_STREAM,response.getOutputStream());
						response.setHeader("Content-Disposition","attachment;filename=" + reportTitle + ".pdf");							
						response.setContentType("application/pdf");
						exporterPDF.exportReport();
					}
					if (flag.equalsIgnoreCase("statistics")) {

						log4jLogger.info(":::::::::::::::inside pdf report::::::::::::::::::::");
						Map<String, Object> parameters = new HashMap<String, Object>();
						parameters.put("From", frmdt);
						parameters.put("To", todt);
						// parameters.put("CMP_NAME2",ReportQueryUtill.COMPANY_NAME2);
						parameters.put("CMP_NAME",ReportQueryUtill.COMPANY_NAME);
								
						parameters.put("CMP_ADD", ReportQueryUtill.COMPANY_ADD);
						parameters.put("ReportTitle", reportTitle);
						parameters.put("All_Query", sb.toString());

						InputStream inputStream1 = getServletContext().getResourceAsStream("/Report/" + reportName + ".jasper");
						JasperReport report = (JasperReport) JRLoader.loadObject(inputStream1);								
						JasperPrint jasperPrint = JasperFillManager.fillReport(report, parameters, con);
						JRAbstractExporter exporterPDF = new JRPdfExporter();
						exporterPDF.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);								
						exporterPDF.setParameter(JRExporterParameter.OUTPUT_STREAM,response.getOutputStream());
						response.setHeader("Content-Disposition","attachment;filename=" + reportTitle + ".pdf");								
						response.setContentType("application/pdf");
						exporterPDF.exportReport();
					}

					if (flag.equalsIgnoreCase("ExcelReport")) 
					{
						log4jLogger.info(":::::::::::::::inside Excel report::::::::::::::::::::");
						List prepareSearchCriteriaLst = importExportXMLService.getCounterReportList(sb.toString(),txtreporttype,report_type);
						Map<Object, Object> excelTitleMap = new HashMap<Object, Object>();
						excelTitleMap.put("rptFlag", txtreporttype);
						excelTitleMap.put("report_type", report_type);
						excelTitleMap.put("rptTitle", reportTitle);
						excelTitleMap.put("fromAccNo", Security_Counter.TextDate_Insert(request.getParameter("txtfdate")));
						excelTitleMap.put("toAccNo", Security_Counter.TextDate_Insert(request.getParameter("txttdate")));
						excelTitleMap.put("documentType",request.getParameter("txtdoctype"));
						Iterator studentDataItr = prepareSearchCriteriaLst.iterator();								
						ReportAllExportRecord recordProcessor = new ReportAllExportRecord(excelTitleMap);								
						response.setContentType("text/csv");
						response.setHeader("Content-Disposition","attachment; filename="+ recordProcessor.getExcelFileName());
						csvImportExportService.Export(studentDataItr,recordProcessor, response.getOutputStream());
					}

					if (flag.equalsIgnoreCase("chart")) {
						log4jLogger.info(":::::::::::::::inside Chart report::::::::::::::::::::");
						Map<String, Object> parameters = new HashMap<String, Object>();
						parameters.put("CMP_NAME",ReportQueryUtill.COMPANY_NAME);								
						parameters.put("CMP_ADD", ReportQueryUtill.COMPANY_ADD);
						parameters.put("ReportTitle", reportTitle);
						parameters.put("All_Query", sb.toString());
						InputStream inputStream1 = getServletContext().getResourceAsStream("/Report/Issue_BarChart.jasper");
						JasperReport report = (JasperReport) JRLoader.loadObject(inputStream1);								
						JasperPrint jasperPrint = JasperFillManager.fillReport(report, parameters, con);								
						JRAbstractExporter exporterPDF = new JRPdfExporter();
						exporterPDF.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);							
						exporterPDF.setParameter(JRExporterParameter.OUTPUT_STREAM,response.getOutputStream());		
						response.setHeader("Content-Disposition","attachment;filename=" + reportTitle + ".pdf");								
						response.setContentType("application/pdf");
						exporterPDF.exportReport();
					}
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}

	public void dispatch(HttpServletRequest request,
			HttpServletResponse response, String indexPage)
			throws ServletException, IOException {
		RequestDispatcher dispatch = request.getRequestDispatcher(indexPage);
		dispatch.forward(request, response);
	}

}
