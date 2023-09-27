package Lib.Auto.Account;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
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

import com.library.autolib.portal.dbconnectionutil.SessionHibernateUtil;
import com.library.autolib.portal.prototype.LibraryServiceFactory;

import Common.Security;
import Common.Security_Counter;
import Common.businessutil.BusinessServiceFactory;
import Common.businessutil.LoginUserService;
import Common.businessutil.importexportexcel.ExceImportExportService;
import Common.businessutil.importexportxml.ImportExportXMLService;
import Common.businessutil.reports.ReportQueryUtill;
import Common.businessutil.reports.ReportService;
import Common.businessutil.search.SearchService;
import Lib.Auto.Department.DepartmentBean;
import Lib.Auto.Designation.DesignationBean;
import Lib.Auto.Group.GroupBean;
import Lib.Auto.Member.MemberBean;

import javax.servlet.annotation.WebServlet;

import com.google.gson.Gson;

//@WebServlet("/Gate_Register/AccountServlet")

public class Account extends HttpServlet implements Serializable {
	private static Logger log4jLogger = Logger.getLogger(Account.class);

	private static final long serialVersionUID = -8906987252709033668L;

	String protocol = "", flag = "";
	String term = "";

	String Auth_Name = "", SameCode = "", reportType = "";
	String sql = "";
	String nam = "";
	String filename = "";
	int Author_Interface_code = 0;
	int Author_Mas_code = 0;
	String indexPage = null;
	List AccountArrylist;
	String SQL_Query = "";
	String frmdt = "", todt = "";

	public void doGet(HttpServletRequest request, HttpServletResponse response) {

		performTask(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) {

		performTask(request, response);

	}

	public synchronized void performTask(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			HttpSession session = request.getSession(true);

			String res = Security.checkSecurity(1, session, response, request);
			if (res.equalsIgnoreCase("Failure")) {
				response.sendRedirect("/AutoLib/Tree/sessiontimeout.jsp");
				return;
			}

			SearchService ss = BusinessServiceFactory.INSTANCE
					.getSearchService();
			ReportService rs = BusinessServiceFactory.INSTANCE
					.getReportService();
			ImportExportXMLService importExportXMLService = BusinessServiceFactory.INSTANCE
					.getImportExportXMLService();
			ExceImportExportService csvImportExportService = BusinessServiceFactory.INSTANCE
					.getExceImportExportService();

			response.setContentType("application/json");
			Object obj = request.getParameter("flag");

			try {
				String term = request.getParameter("txtmembercode");
				if (!term.equalsIgnoreCase(null) && !term.equalsIgnoreCase("")
						&& obj == null) {
					// System.out.println("Data from ajax call " + term);

					ArrayList<MemberBean> list = rs
							.getGateRegisterMemberCodeAutoSearch(term);
					for (MemberBean user : list) {
						// System.out.println(user.getCode());
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
							.getGateRegisterGroupAutoSearch(term);
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
							.getGateRegisterDeptAutoSearch(term);
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
			try{
				String term = request.getParameter("Desig");
				
				if(!term.equalsIgnoreCase(null) && !term.equalsIgnoreCase(""))
	             {
				//System.out.println("Data from ajax call " + term);
							    
				   ArrayList<DesignationBean> list = rs.getGateRegisterDesigAutoSearch(term);
			       for(DesignationBean user: list){
					//System.out.println(user.getName());
				}       

				String searchList = new Gson().toJson(list);
							
//				response.getWriter().write(searchList);  
							
				//System.out.println("Json Value@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+searchList);
	             }	
		}catch(Exception e){
			//e.printStackTrace();
		}   

			AccountBean ob = new AccountBean();
			flag = request.getParameter("flag");

			if (request.getParameter("returnuserid") != null) {
				log4jLogger.info("start===========returnuserid====="
						+ request.getParameter("returnuserid"));

				SQL_Query = "and member_code =";
				SQL_Query = SQL_Query + "'"
						+ request.getParameter("returnuserid") + "'";
				AccountArrylist = ss.getAccountDetails(SQL_Query);

				session.setAttribute("AccountArrylist", AccountArrylist);
				indexPage = "/Account/retrundetails.jsp";
				dispatch(request, response, indexPage);

			}

			if (request.getParameter("returnuserfineid") != null) {
				log4jLogger.info("start===========returnuserid====="
						+ request.getParameter("returnuserid"));

				SQL_Query = "and member_code = ";
				SQL_Query = SQL_Query + "'"
						+ request.getParameter("returnuserfineid") + "'"
						+ "and trans_amount<>'0.00' order by trans_date desc ";
				AccountArrylist = ss.getAccountTransDetails(SQL_Query);

				session.setAttribute("AccountArrylist", AccountArrylist);
				indexPage = "/Account/returnfinedetails.jsp";
				dispatch(request, response, indexPage);

			}

			if (request.getParameter("userpaidamtid") != null) {
				log4jLogger.info("start===========returnuserid====="
						+ request.getParameter("userpaidamtid"));

				SQL_Query = "and member_code = ";
				SQL_Query = SQL_Query + "'"
						+ request.getParameter("userpaidamtid") + "'"
						+ " order by payment_date desc ";
				AccountArrylist = ss.getAccountPaidDetails(SQL_Query);

				session.setAttribute("AccountArrylist", AccountArrylist);
				indexPage = "/Account/finepaiddetails.jsp";
				dispatch(request, response, indexPage);

			}

			if (request.getParameter("Suguserid") != null) {
				log4jLogger.info("start===========returnuserid====="
						+ request.getParameter("Suguserid"));
				SQL_Query = "and member_code = ";
				SQL_Query = SQL_Query + "'" + request.getParameter("Suguserid")
						+ "'" + " order by request_date desc ";

				AccountArrylist = ss.getAccountSugDetails(SQL_Query);

				session.setAttribute("AccountArrylist", AccountArrylist);

				indexPage = "/Account/suggestionDetails.jsp";
				dispatch(request, response, indexPage);

			}

			if (request.getParameter("issueuserid") != null) {
				log4jLogger.info("start===========issueuserid====="
						+ request.getParameter("issueuserid"));
				ob = new AccountBean();
				ob.setuid(request.getParameter("issueuserid"));
				SQL_Query = "and member_code =";
				SQL_Query = SQL_Query + "'"
						+ request.getParameter("issueuserid") + "'";
				AccountArrylist = ss.getAccountDetailsIssue(SQL_Query);

				session.setAttribute("AccountArrylist", AccountArrylist);
				indexPage = "/Account/issuedetails.jsp";
				dispatch(request, response, indexPage);

			}
			if (request.getParameter("reserveuserid") != null) {
				log4jLogger.info("start===========reserveuserid====="
						+ request.getParameter("reserveuserid"));
				ob = new AccountBean();
				ob.setuid(request.getParameter("reserveuserid"));
				SQL_Query = "and member_code =";
				SQL_Query = SQL_Query + "'"
						+ request.getParameter("reserveuserid") + "'";
				AccountArrylist = ss.getAccountDetailsReserve(SQL_Query);

				session.setAttribute("AccountArrylist", AccountArrylist);
				indexPage = "/Account/reservedetails.jsp";
				dispatch(request, response, indexPage);

			}
			if (request.getParameter("accno") != null) {
				ob = new AccountBean();
				log4jLogger.info("start===========accno====="
						+ request.getParameter("accno"));
				ob.setaccno(request.getParameter("accno"));

				String opacID = String.valueOf(session.getAttribute("OpacID"));

				ob.setuid(opacID); // By RK on 07-10-2013
				ob.settitle(opacID);

				if (opacID.equalsIgnoreCase("null")) {
					ob.setuid(String.valueOf(session.getAttribute("UserID")
							.toString()));
					ob.settitle(String.valueOf(session.getAttribute("UserID")
							.toString()));
				}

				String retstring = ss.getOnlineRenewSave(ob);

				SQL_Query = "and member_code =";
				SQL_Query = SQL_Query + "'" + ob.getuid() + "'";
				AccountArrylist = ss.getAccountDetailsIssue(SQL_Query);

				session.setAttribute("AccountArrylist", AccountArrylist);
				request.setAttribute("strobj", retstring);
				// indexPage = "/Account/issuedetailsdisplay.jsp?check=YES";
				indexPage = "/Account/issuedetails.jsp?check=YES";

				dispatch(request, response, indexPage);

			}

			if (request.getParameter("resaccno") != null) {
				ob = new AccountBean();
				log4jLogger.info("start===========resaccno====="
						+ request.getParameter("resaccno"));

				ob.setaccno(request.getParameter("resaccno"));

				String opacID = String.valueOf(session.getAttribute("OpacID"));

				ob.setuid(opacID); // By RK on 07-10-2013

				if (opacID.equalsIgnoreCase("null")) {
					ob.setuid(String.valueOf(session.getAttribute("UserID")
							.toString()));
				}

				String retstring = ss.getOnlineReserveCancel(ob);

				SQL_Query = "and member_code =";
				SQL_Query = SQL_Query + "'" + ob.getuid() + "'";

				AccountArrylist = ss.getAccountDetailsReserve(SQL_Query);

				session.setAttribute("AccountArrylist", AccountArrylist);
				request.setAttribute("strobj", retstring);
				indexPage = "/Account/reservedetailsdisplay.jsp?check=YES";
				dispatch(request, response, indexPage);

			}

			// For Online Reservation

			if ((request.getParameter("reservecheck") != null)
					&& (request.getParameter("document") != null)) {
				log4jLogger.info("start===========reservecheck====="
						+ request.getParameter("reservecheck") + " And "
						+ request.getParameter("document"));

				String accno = request.getParameter("reservecheck").toString();
				String doc = request.getParameter("document").toString();

				AccountArrylist = ss.getIssueDetails(accno, doc);

				session.setAttribute("IssueArrylist", AccountArrylist);
				indexPage = "/Simples/ReserveView.jsp";
				dispatch(request, response, indexPage);

			}

			if (flag.equals("Reserve")) {

				log4jLogger.info("start=========== OnlineReservation =====");

				ob = new AccountBean();
				ob = ss.getCheckAccount(request.getParameter("ruid").trim(),
						request.getParameter("rpwd").trim());

				if (ob.getuid() != null) {

					ob = new AccountBean();

					ob.setaccno(request.getParameter("raccno"));
					ob.setdtype(request.getParameter("rdtype"));
					ob.setuid(request.getParameter("ruid"));
					ob.setresdat(Security_Counter.TodayDate());

					if (!ob.getaccno().isEmpty() && ob.getaccno() != null) {

						ob = ss.getReserveCheck(ob);

					} else {
						ob.setauthor("Error Occured");
					}

					if (ob.getauthor().equals("DONE")) {
						request.setAttribute("bean", ob);
						indexPage = "/Simples/ReserveDone.jsp";

					} else {
						request.setAttribute("bean", ob);

						String accno = request.getParameter("raccno")
								.toString();
						String doc = request.getParameter("rdtype").toString();
						AccountArrylist = ss.getIssueDetails(accno, doc);
						session.setAttribute("IssueArrylist", AccountArrylist);

						indexPage = "/Simples/ReserveView.jsp";
					}

				} else {

					String accno = request.getParameter("raccno").toString();
					String doc = request.getParameter("rdtype").toString();
					AccountArrylist = ss.getIssueDetails(accno, doc);
					session.setAttribute("IssueArrylist", AccountArrylist);

					indexPage = "/Simples/ReserveView.jsp?check=usernotfound";
				}
				dispatch(request, response, indexPage);

			}

			if (flag.equals("registerload")) {

				log4jLogger.info("start===========register====="
						+ request.getParameter("userid"));
				SQL_Query = "and member_code =";
				SQL_Query = SQL_Query + "'" + request.getParameter("userid")
						+ "'";

				ob = new AccountBean();
				String usercode = request.getParameter("userid");

				if (usercode != null && !usercode.isEmpty()) {
					ob = ss.getRegisterEntry(request.getParameter("userid"));
				}

				AccountArrylist = ss.getRegisterLoad();
				session.setAttribute("AccountArrylist", AccountArrylist);

				request.setAttribute("bean", ob);
				indexPage = "/Home/index.jsp";
				dispatch(request, response, indexPage);

			}

			if (flag.equals("homegate")) {

				System.out.println("=======Inside Home Gate=============");
				ob = new AccountBean();

				AccountArrylist = ss.getRegisterLoad();
				session.setAttribute("AccountArrylist", AccountArrylist);

				request.setAttribute("bean", ob);
				indexPage = "/Home/userid.jsp";
				dispatch(request, response, indexPage);

			}

			if (flag.equals("todayvisited")) {

				log4jLogger.info("=======Inside Toady Visited =============");
				ob = new AccountBean();

				AccountArrylist = ss.getTodayRegisterLoad();
				session.setAttribute("AccountArrylist", AccountArrylist);

				request.setAttribute("bean", ob);
				indexPage = "/Account/todayvisited.jsp";
				dispatch(request, response, indexPage);

			}

			if (flag.equals("register")) {

				log4jLogger.info("start===========register====="
						+ request.getParameter("userid"));

				SQL_Query = "and member_code =";
				SQL_Query = SQL_Query + "'" + request.getParameter("userid")
						+ "'";

				ob = new AccountBean();
				String usercode = request.getParameter("userid");

				if (usercode != null && !usercode.isEmpty()) {
					ob = ss.getRegisterEntry(request.getParameter("userid"));
				}

				AccountArrylist = ss.getRegisterLoad();
				session.setAttribute("AccountArrylist", AccountArrylist);

				request.setAttribute("bean", ob);

				// -----------gate Counts Starts-------------

				String mcode = String.valueOf(session.getAttribute("UserID"));
				LoginUserService ss_temp = LibraryServiceFactory.INSTANCE
						.getLoginUserService();
				Map result = ss_temp.loadHomeEvent(mcode);

				session.setAttribute("todayGateUser",
						result.get("totalGateLogin"));
				session.setAttribute("todayGateVisitedCount",
						result.get("todayGateVisitedCount"));
				session.setAttribute("totalvisted", result.get("totalvisted"));

				// -----------gate Counts Ends---------------

				indexPage = "/Account/gateresister.jsp";
				dispatch(request, response, indexPage);

			}

			if (flag.equals("Gate_Report_Full")) // For Full Gate Report
			{
				log4jLogger
						.info("=========Inside Gate Register Date Wise Report Full=========");
				java.sql.Connection con = null;

				try {

					con = SessionHibernateUtil.getSession().connection();

					Map parameters = new HashMap();

					StringBuffer sb = new StringBuffer();

					String strsql = "select * from return_log_view where 2>1";

					sb.append(strsql);

					parameters.put("CMP_NAME", ReportQueryUtill.COMPANY_NAME);
					parameters.put("CMP_ADD", ReportQueryUtill.COMPANY_ADD);
					parameters.put("ReportTitle",ReportQueryUtill.GateRegFull_Title);
					parameters.put("All_Query", sb.toString());
					parameters.put("gate_from", "");
					parameters.put("gate_to", "");
					log4jLogger.info("namedQuery: " + sb);

					InputStream inputStream1 = getServletContext()
							.getResourceAsStream("/Report/Gate_Register.jasper");
					JasperReport report = (JasperReport) JRLoader
							.loadObject(inputStream1);

					JasperPrint jasperPrint = JasperFillManager.fillReport(
							report, parameters, con);

					JRAbstractExporter exporterPDF = new JRPdfExporter();
					exporterPDF.setParameter(JRExporterParameter.JASPER_PRINT,
							jasperPrint);
					exporterPDF.setParameter(JRExporterParameter.OUTPUT_STREAM,
							response.getOutputStream());
					response.setHeader("Content-Disposition",
							"attachment;filename="
									+ ReportQueryUtill.GateReg_Title + ".pdf");
					response.setContentType("application/pdf");
					exporterPDF.exportReport();

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

			if (flag.equals("Gate_Report")) {
				log4jLogger.info("Inside Gate Register Date Wise Report");
				java.sql.Connection con = null;

				try {

					String deptname = "", groupname = "",designame="", gate_from = "", gate_to = "", txtmembercode = "", strsql = "";

					// ReportService ss1 =
					// BusinessServiceFactory.INSTANCE.getReportService();
					// con=ss1.getDBConnect();

					con = SessionHibernateUtil.getSession().connection();

					Map parameters = new HashMap();
					String namedQuery = ReportQueryUtill.Gate_detail;
					StringBuffer sb = new StringBuffer();
					String filterQuery = null;

					frmdt = Security.TextDate_member(request
							.getParameter("gate_from"));
					todt = Security.TextDate_member(request
							.getParameter("gate_to"));

					txtmembercode = request.getParameter("txtmembercode");
					deptname = request.getParameter("Dname");
					groupname = request.getParameter("Gname");
					designame= request.getParameter("Desig");

					if (!deptname.equals("") && deptname != null) {
						strsql = strsql + " and dept_name = '" + deptname + "'";
					}
					if (!groupname.equals("") && groupname != null) {
						strsql = strsql + " and group_name = '" + groupname
								+ "'";
					}
					if (!designame.equals("") && designame != null) {
						strsql = strsql + " and designation = '" + designame
								+ "'";
					}
					if (!txtmembercode.equals("")) {
						strsql = strsql + " and Member_Code = '"
								+ txtmembercode + "'";
					}

					filterQuery = "where return_date between '" + frmdt
							+ "' and '" + todt + "' " + strsql
							+ " order by return_date,in_time,out_time";

					sb.append(namedQuery);
					sb.append(" " + filterQuery);

					log4jLogger.info(":::::::::::::Report:::Type:::::::"
							+ request.getParameter("printType"));
					reportType = request.getParameter("printType");

					if (reportType.equals("pdf")) {
						parameters.put("CMP_NAME",ReportQueryUtill.COMPANY_NAME);
								
						parameters.put("CMP_ADD", ReportQueryUtill.COMPANY_ADD);
						parameters.put("ReportTitle",ReportQueryUtill.GateReg_Title);
								
						parameters.put("All_Query", sb.toString());
						if (!request.getParameter("gate_from").equalsIgnoreCase("") && !request.getParameter("gate_to").equalsIgnoreCase(""))
				        {
							parameters.put("gate_from","From : "+ Security.getdate(frmdt).replace('-', '/'));
							parameters.put("gate_to", "To : "+ Security.getdate(todt).replace('-', '/'));		
						}
						log4jLogger.info("namedQuery: " + sb);
						InputStream inputStream1 = getServletContext().getResourceAsStream("/Report/Gate_Register.jasper");
								
					JasperReport report = (JasperReport) JRLoader.loadObject(inputStream1);
					JasperPrint jasperPrint = JasperFillManager.fillReport(report, parameters, con);
                    JRAbstractExporter exporterPDF = new JRPdfExporter();
						exporterPDF.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
						exporterPDF.setParameter(JRExporterParameter.OUTPUT_STREAM,	response.getOutputStream());	
						response.setHeader("Content-Disposition","attachment;filename="+ ReportQueryUtill.GateReg_Title+ ".pdf");
						response.setContentType("application/pdf");
						exporterPDF.exportReport();
					} else {
						List prepareSearchCriteriaLst = importExportXMLService
								.getGateRegisterReportList(sb.toString());

						Map<Object, Object> excelTitleMap = new HashMap<Object, Object>();

						// excelTitleMap.put("fromAccNo", "1");
						// excelTitleMap.put("toAccNo", "1");
						// excelTitleMap.put("documentType", "1");

						Iterator studentDataItr = prepareSearchCriteriaLst
								.iterator();
						GateRegisterExportRecord recordProcessor = new GateRegisterExportRecord(
								excelTitleMap);
						response.setContentType("text/csv");
						response.setHeader(
								"Content-Disposition",
								"attachment; filename="
										+ recordProcessor.getExcelFileName());
						csvImportExportService.Export(studentDataItr,
								recordProcessor, response.getOutputStream());

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

			if (flag.equals("Statistics_Report")) {
				log4jLogger
						.info("Inside Gate Register Date Wise Statistics Report");
				java.sql.Connection con = null;

				try {

					String deptname = "", groupname = "",designame="", gate_from = "", gate_to = "", txtmembercode = "", strsql = "";

					// ReportService ss1 =
					// BusinessServiceFactory.INSTANCE.getReportService();
					// con=ss1.getDBConnect();

					con = SessionHibernateUtil.getSession().connection();

					Map parameters = new HashMap();
					String namedQuery = ReportQueryUtill.GateRegQuery_Statistics;
					StringBuffer sb = new StringBuffer();
					String filterQuery = null;

					frmdt = Security.TextDate_member(request
							.getParameter("gate_from"));
					todt = Security.TextDate_member(request
							.getParameter("gate_to"));

					log4jLogger
							.info("::::::::::::::::::from:::::;" + gate_from);
					log4jLogger.info("::::::::::::::::::to:::::;" + gate_to);

					txtmembercode = request.getParameter("txtmembercode");
					deptname = request.getParameter("Dname");
					groupname = request.getParameter("Gname");
					designame =request.getParameter("Desig");

					if (!deptname.equals("") && deptname != null) {
						strsql = strsql + " and dept_name = '" + deptname + "'";
					}
					if (!groupname.equals("") && groupname != null) {
						strsql = strsql + " and group_name = '" + groupname
								+ "'";
					}
					if (!designame.equals("") && designame != null) {
						strsql = strsql + " and designation = '" + designame
								+ "'";
					}
					if (!txtmembercode.equals("")) {
						strsql = strsql + " and Member_Code = '"
								+ txtmembercode + "'";
					}

					// filterQuery="where return_date between '"+gate_from+"' and '"+gate_to+"' group by return_date order by count(return_date) DESC";

					filterQuery = strsql
							+ " and SUBSTRING(return_date,1,10) BETWEEN SUBSTRING('"
							+ frmdt
							+ "',1,10) and SUBSTRING('"
							+ todt
							+ "',1,10)GROUP BY SUBSTRING(return_date,1,10) order by SUBSTRING(return_date,1,10) ASC";

					sb.append(namedQuery);
					sb.append(" " + filterQuery);

					log4jLogger.info(":::::::::::::Report:::Type:::::::"
							+ request.getParameter("printType"));
					reportType = request.getParameter("printType");

					if (reportType.equals("pdf")) {

						parameters.put("CMP_NAME",
								ReportQueryUtill.COMPANY_NAME);
						parameters.put("CMP_ADD", ReportQueryUtill.COMPANY_ADD);
						parameters.put("ReportTitle",
								ReportQueryUtill.GateReg_Title);
						parameters.put("All_Query", sb.toString());
						parameters.put("gate_from", Security.getdate(frmdt)
								.replace('-', '/'));
						parameters.put("gate_to", Security.getdate(todt)
								.replace('-', '/'));
						log4jLogger.info("namedQuery: " + sb);

						InputStream inputStream1 = getServletContext()
								.getResourceAsStream(
										"/Report/Gate_Register_statistics.jasper");
						// InputStream inputStream1 =
						// getServletContext().getResourceAsStream("/Report/Gate_Register_statistics-old.jasper");
						JasperReport report = (JasperReport) JRLoader
								.loadObject(inputStream1);

						JasperPrint jasperPrint = JasperFillManager.fillReport(
								report, parameters, con);

						JRAbstractExporter exporterPDF = new JRPdfExporter();
						exporterPDF.setParameter(
								JRExporterParameter.JASPER_PRINT, jasperPrint);
						exporterPDF.setParameter(
								JRExporterParameter.OUTPUT_STREAM,
								response.getOutputStream());
						response.setHeader("Content-Disposition",
								"attachment;filename="
										+ ReportQueryUtill.GateReg_Title
										+ ".pdf");
						response.setContentType("application/pdf");
						exporterPDF.exportReport();

					} else {
						List prepareSearchCriteriaLst = importExportXMLService
								.getGateRegisterReportStatisticsList(sb
										.toString());

						Map<Object, Object> excelTitleMap = new HashMap<Object, Object>();

						// excelTitleMap.put("fromAccNo", "1");
						// excelTitleMap.put("toAccNo", "1");
						// excelTitleMap.put("documentType", "1");

						Iterator studentDataItr = prepareSearchCriteriaLst
								.iterator();
						GateRegisterStatisticsExport recordProcessor = new GateRegisterStatisticsExport(
								excelTitleMap);
						response.setContentType("text/csv");
						response.setHeader(
								"Content-Disposition",
								"attachment; filename="
										+ recordProcessor.getExcelFileName());
						csvImportExportService.Export(studentDataItr,
								recordProcessor, response.getOutputStream());
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

			if (flag.equals("All_Logout")) {
				log4jLogger.info("start===========All_Logout=====");
				int count;
				count = ss.getRegisterAllLogout();

				if (count > 0) {
					request.setAttribute("resultDone", count);
					indexPage = "/Gate_Register/index.jsp?check=success";
				} else {
					indexPage = "/Gate_Register/index.jsp?check=fail";
				}
				dispatch(request, response, indexPage);
			}

			if (flag.equals("chpwd")) {
				log4jLogger.info("start===========chpwd=====");
				ob = new AccountBean();
				ob.setuid(request.getParameter("uid"));
				ob.setpwd(request.getParameter("pwd"));
				ob.setnewpwd(request.getParameter("newpwd"));
				ob.setcfmpwd(request.getParameter("cfmpwd"));
				String retstr = ss.getChangePwd(ob);

				request.setAttribute("strobj", retstr);
				indexPage = "/Account/changepwd.jsp?check=cpwd";
				dispatch(request, response, indexPage);

			}
			if (flag.equals("save")) {
				log4jLogger.info("start===========save=====");
				ob = new AccountBean();
				ob = ss.getCheckAccount(request.getParameter("uid").trim(),
						request.getParameter("pwd").trim());

				if (ob.getuid() != null) {
					session.setAttribute("OpacID", ob.getuid()); // By RK on
																	// 17-10-2013
					session.setAttribute("OpacPWD", ob.getauthor()); // By RK on
																		// 17-10-2013
					request.setAttribute("beanobject", ob);
					indexPage = "/Account/account.jsp";
				} else {

					indexPage = "/Account/index.jsp?check=usernotfound";
				}
				dispatch(request, response, indexPage);

			}
			if (flag.equals("back")) {
				log4jLogger.info("start===========BACK to Account Page=====");
				ob = new AccountBean();
				ob = ss.getCheckAccount(
						(String.valueOf(session.getAttribute("OpacID")).trim()),
						(String.valueOf(session.getAttribute("OpacPWD")).trim()));

				if (ob.getuid() != null) {
					request.setAttribute("beanobject", ob);
					indexPage = "/Account/account.jsp";
				} else {

					indexPage = "/Account/index.jsp?check=usernotfound";
				}

				dispatch(request, response, indexPage);
			}

			if (flag.equals("new")) {
				log4jLogger.info("start===========new=====");
				indexPage = "/Account/index.jsp";
				dispatch(request, response, indexPage);
			}

		} catch (Exception sss) {
			sss.printStackTrace();

		} finally {

		}

	}

	/**
	 * Instance variable for SQL statement property
	 */

	/****************************************************************
	 * prepare the sql statement for execution
	 **/
	public void dispatch(HttpServletRequest request,
			HttpServletResponse response, String indexPage)
			throws ServletException, IOException {
		RequestDispatcher dispatch = request.getRequestDispatcher(indexPage);
		dispatch.forward(request, response);
	}

}
