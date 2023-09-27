package Lib.Auto.BudgetReport;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.HashMap;
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

import Common.Security;
import Common.businessutil.BusinessServiceFactory;
import Common.businessutil.reports.ReportQueryUtill;
import Common.businessutil.reports.ReportService;
import Lib.Auto.Budget.BudgetBean;

import com.library.autolib.portal.dbconnectionutil.SessionHibernateUtil;

public class BudgetReport extends HttpServlet implements Serializable {
	private static Logger log4jLogger = Logger.getLogger(BudgetReport.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	String frmdt = null, todt = null, strsql = "";
	String flag = null, flag1 = null, doc_type = null;
	
	int deptCode = 0, budCode = 0;
	

	String indexPage = "";
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
			HttpSession session = request.getSession(true);
			String res = Security.checkSecurity(1, session, response, request);
			if (res.equalsIgnoreCase("Failure")) {
				response.sendRedirect("/AutoLib/Tree/sessiontimeout.jsp");
				return;
			}
			
			ReportService rs = BusinessServiceFactory.INSTANCE
					.getReportService();
			BudgetBean beanObject = new BudgetBean();
			
			//PrintWriter out = response.getWriter();

			flag = request.getParameter("flag");
			flag1 = request.getParameter("flag1");
				
			String namedQuery="";
			if(flag.equalsIgnoreCase("Print")){
				namedQuery = ReportQueryUtill.Budget_Date_wise;
			}
			else{
				namedQuery = ReportQueryUtill.Budget_book_Detail;
			}
			StringBuffer sb = new StringBuffer();
			String filterQuery = null;

			log4jLogger
					.info("------------------Flag Value-----------------   :"
							+ flag);

			if (flag.equals("load"))

			{	flag1="";
				List BudgetSearchReport = rs.getSearchBudgetList(beanObject);
				request.setAttribute("budgetSearchList", BudgetSearchReport);
				indexPage = "/BudgetReport/index.jsp";
				dispatch(request, response, indexPage);
			}
	
				if (flag1.equals("Report")) {

					Map<String, Object> parameters = new HashMap<String, Object>();
					
					
					

					

					frmdt = Security.TextDate_member(request
							.getParameter("fromdate"));
					todt = Security.TextDate_member(request.getParameter("todate"));

					filterQuery = " and bud_from >='" + frmdt + "' and bud_to <='"
							+ todt + "'" + strsql;

					if (!request.getParameter("budcode").equals("NO"))
						filterQuery = filterQuery + " and Bud_head='"
								+ request.getParameter("budcode")
								+ "'";

					if (!request.getParameter("deptcode").equals("NO"))
						filterQuery = filterQuery
								+ " and dept_name='"
								+ request.getParameter("deptcode")
								+ "'";

					/*
					 * if(!request.getParameter("doctype").equals("ALL"))
					 * filterQuery =
					 * filterQuery+" and dept_code="+Integer.parseInt(request
					 * .getParameter("deptcode"))+"";
					 */
					sb.append(namedQuery);
					sb.append(" " + filterQuery);

					
					log4jLogger.info("eteeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee"
							+ sb.toString());
					boolean checkData = rs.getCheckData(sb.toString());
					if (checkData) {
						// no data
						log4jLogger
								.info("----------------NO DATA FOUND-------------------");
						indexPage = "/BudgetReport/index.jsp?check=NoData";
						dispatch(request, response, indexPage);
					}

					else {
						
						
						con = SessionHibernateUtil.getSession().connection();
						parameters.put("CMP_NAME",
								ReportQueryUtill.COMPANY_NAME);
						parameters
								.put("CMP_ADD",ReportQueryUtill.COMPANY_ADD);
						
						
						log4jLogger.info("namedQuery: " + sb);
						if(flag.equalsIgnoreCase("Print"))
						{
							
							parameters.put("ReportTitle",
									ReportQueryUtill.Budget_Title);
							parameters.put("All_Query", sb.toString());
							
							InputStream inputStream1 = getServletContext()
									.getResourceAsStream(
											"/Report/Budget_Report_Full.jasper");
							JasperReport report = (JasperReport) JRLoader
									.loadObject(inputStream1);
							
							JasperPrint jasperPrint = JasperFillManager.fillReport(
									report, parameters, con);

							JRAbstractExporter exporterPDF = new JRPdfExporter();

							exporterPDF.setParameter(
									JRExporterParameter.JASPER_PRINT, jasperPrint);
							log4jLogger
									.info("99999999999999999999999999999999999999999999999999999");
							exporterPDF.setParameter(
									JRExporterParameter.OUTPUT_STREAM,
									response.getOutputStream());

							response.setHeader("Content-Disposition",
									"attachment;filename="
											+ ReportQueryUtill.Budget_Title
											+ ".pdf");
							response.setContentType("application/pdf");
							exporterPDF.exportReport();
						}
						else
						{
							sb.append(" " + "ORDER BY access_no * 1 ASC" );
							
							parameters.put("ReportTitle",
									ReportQueryUtill.DetailedBudget_Title);
							parameters.put("All_Query", sb.toString());		
							parameters.put("frmdt", frmdt);
							parameters.put("todt", todt);
							
							InputStream inputStream1 = getServletContext()
									.getResourceAsStream(
											"/Report/Budget_book.jasper");
							JasperReport report = (JasperReport) JRLoader
									.loadObject(inputStream1);
							
							JasperPrint jasperPrint = JasperFillManager.fillReport(
									report, parameters, con);

							JRAbstractExporter exporterPDF = new JRPdfExporter();

							exporterPDF.setParameter(
									JRExporterParameter.JASPER_PRINT, jasperPrint);
							log4jLogger
									.info("99999999999999999999999999999999999999999999999999999");
							exporterPDF.setParameter(
									JRExporterParameter.OUTPUT_STREAM,
									response.getOutputStream());

							response.setHeader("Content-Disposition",
									"attachment;filename="
											+ ReportQueryUtill.Budget_Title
											+ ".pdf");
							response.setContentType("application/pdf");
							exporterPDF.exportReport();
						}

						

						

					}

				}

			

		}

		catch (Exception e) {

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
