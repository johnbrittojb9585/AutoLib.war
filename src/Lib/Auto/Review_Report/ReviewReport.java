package Lib.Auto.Review_Report;

import Lib.Auto.AccessionRegister.AccessionWiseExportRecord;
import Lib.Auto.Author.AuthorBean;
import Lib.Auto.Budget.BudgetBean;
import Lib.Auto.Course.CourseBean;
import Lib.Auto.Department.DepartmentBean;
import Lib.Auto.Designation.DesignationBean;
import Lib.Auto.Fine.Finebean;
import Lib.Auto.Group.GroupBean;
import Lib.Auto.Holiday.Holiday;
import Lib.Auto.Member.MemberBean;
import Lib.Auto.MemberReport.MemberExcelReport;
import Lib.Auto.MemberTransfer.MemberTransRefBean;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.UnavailableException;
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

import Common.DataQuery;
import Common.Security;
import Common.businessutil.BusinessServiceFactory;
import Common.businessutil.admin.AdminService;
import Common.businessutil.importexportexcel.ExceImportExportService;
import Common.businessutil.importexportxml.ImportExportXMLService;
import Common.businessutil.reports.ReportQueryUtill;
import Common.businessutil.reports.ReportService;
import javax.servlet.annotation.WebServlet;
import com.google.gson.Gson;

public class ReviewReport extends HttpServlet implements Serializable {
	private static Logger log4jLogger = Logger.getLogger(ReviewReport.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String flag = null;
	String term = "";
	String indexPage = null;
	String frmdt = null, todt = null;
	java.sql.Connection con = null;
	java.sql.PreparedStatement Prest = null;
	java.sql.ResultSet rs = null;
	Map parameters = new HashMap();
	String namedQuery = ReportQueryUtill.ReviewInfo;
	String Strsql = "", printType = "";

	public void doGet(HttpServletRequest request, HttpServletResponse response) {

		performTask(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) {

		performTask(request, response);

	}

	public void performTask(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			HttpSession session = request.getSession(true);
			String res = Security.checkSecurity(1, session, response, request);
			if (res.equalsIgnoreCase("Failure")) {
				try {
					response.sendRedirect("/AutoLib/Tree/sessiontimeout.jsp");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return;
			}

			ReportService rs = BusinessServiceFactory.INSTANCE
					.getReportService();
			ImportExportXMLService importExportXMLService = BusinessServiceFactory.INSTANCE
					.getImportExportXMLService();
			ExceImportExportService csvImportExportService = BusinessServiceFactory.INSTANCE
					.getExceImportExportService();

			con = SessionHibernateUtil.getSession().connection();
			// =========================================================

			StringBuffer sb = new StringBuffer();
			String filterQuery = "";
			String flag = request.getParameter("flag");

			if (flag.equals("review")) {

				log4jLogger.info("start=========== Review Report =====");

				frmdt = Security.TextDate_member(request
						.getParameter("fromdate"));
				todt = Security.TextDate_member(request.getParameter("todate"));

				if (!frmdt.equals("") && (!todt.equals(""))) {
					filterQuery = filterQuery + " and review_date>='" + frmdt
							+ "' and review_date<='" + todt + "'";

				}

				if (!request.getParameter("accno").equals("")
						&& !request.getParameter("accno").equals(null)) {
					filterQuery = filterQuery + " and access_no='"
							+ request.getParameter("accno") + "'";
				}
				if (!request.getParameter("user").equals("")
						&& !request.getParameter("user").equals(null)) {
					filterQuery = filterQuery + " and member_code='"
							+ request.getParameter("user") + "'";
				}
				if (!request.getParameter("type").equalsIgnoreCase("ALL")
						&& !request.getParameter("type").isEmpty()) {
					filterQuery = filterQuery + " and document='"
							+ request.getParameter("type") + "'";
				}
				if (!request.getParameter("rating").equalsIgnoreCase("ALL")
						&& !request.getParameter("rating").isEmpty()) {
					filterQuery = filterQuery + " and rating='"
							+ request.getParameter("rating") + "'";
				}

				sb.append(namedQuery);
				sb.append(" " + filterQuery);
				sb.append(" " + "order by rating,review_date");
				log4jLogger.info("start=========== final Query ====="
						+ sb.toString());
				boolean checkData = rs.getCheckData(sb.toString());// for check
																	// no data

				if (checkData) {

					log4jLogger.info("-------All_Query-------Values"
							+ sb.toString());

					log4jLogger
							.info("----------------NO DATA FOUND-------------------");
					indexPage = "/Review_Report/index.jsp?check=NoData";
					dispatch(request, response, indexPage);
				} else {

					parameters.put("CMP_NAME", ReportQueryUtill.COMPANY_NAME);
					parameters.put("CMP_ADD", ReportQueryUtill.COMPANY_ADD);
					parameters.put("ReportTitle",
							ReportQueryUtill.ReviewTitle);
					parameters.put("All_Query", sb.toString());
					log4jLogger.info("namedQuery: " + sb);

					InputStream inputStream1 = getServletContext()
							.getResourceAsStream(
									"/Report/Review_Report.jasper");
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
									+ ReportQueryUtill.ReviewTitle
									+ ".pdf");
					response.setContentType("application/pdf");
					exporterPDF.exportReport();

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

	/**
	 * Instance variable for SQL statement property
	 */

}
