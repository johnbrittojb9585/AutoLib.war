package Lib.Auto.spineLabel;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

import org.apache.log4j.Logger;

import com.library.autolib.portal.dbconnectionutil.SessionHibernateUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.jasperreports.engine.JRAbstractExporter;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import Common.Security;
import Common.businessutil.BusinessServiceFactory;
import Common.businessutil.circulation.CirculationService;
import Common.businessutil.importexportexcel.ExceImportExportService;
import Common.businessutil.importexportxml.ImportExportXMLService;
import Common.businessutil.reports.ReportQueryUtill;
import Common.businessutil.reports.ReportService;
import java.sql.*;

public class spineLabel extends HttpServlet implements ReportQueryUtill {

	private static Logger log4jLogger = Logger.getLogger(spineLabel.class);

	java.sql.Connection con = null;

	String frmaccno, toaccno;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException {

		performTask(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException {

		performTask(request, response);

	}

	public void performTask(HttpServletRequest request,
			HttpServletResponse response) throws ServletException {

		try {
		

			String frmaccno = null, toaccno = null;
			String frmdt = null, todt = null;
			String flag = null, doc_type = null;

			HttpSession session = request.getSession(true);
			String res = Security.checkSecurity(1, session, response, request);
			if (res.equalsIgnoreCase("Failure")) {
				response.sendRedirect("/AutoLib/Tree/sessiontimeout.jsp");
				return;
			}
			con = SessionHibernateUtil.getSession().connection();

			flag = request.getParameter("flag");
			
			log4jLogger.info("Inside Access_no Wise SpineLabel");
			
			Map parameters = new HashMap();
			String namedQuery = ReportQueryUtill.SpineLabelQuery;
			StringBuffer sb = new StringBuffer();
			String filterQuery = null;
			String rkStr = "";
		
			
			

			if (flag.equals("AccessNo_Wise")) {

				log4jLogger.info("Inside Access_no Wise SpineLabel");
				
				frmaccno = request.getParameter("From_Accno");
				toaccno = request.getParameter("To_Accno");
				doc_type = request.getParameter("Type");
				
				if (Security.IsNumeric(frmaccno)) {
					 rkStr = "CONVERT(access_no,SIGNED INTEGER) ";
					//rkStr = "LPAD(access_no, 50,''))";
				} else {
					 rkStr = "CONVERT(access_no,CHAR) ";
					//rkStr = "LPAD(access_no, 50,''))";
				}

				if (doc_type.equals("ALL")) {

					filterQuery = rkStr + " between '" + frmaccno + "' and '"
							+ toaccno + "' ORDER BY " + rkStr;
				} else {

					filterQuery = rkStr + " between '" + frmaccno + "' and '"
							+ toaccno + "' and document='" + doc_type
							+ "' ORDER BY " + rkStr;
				}

			}

			if (flag.equals("Date_Wise")) {
				log4jLogger.info("Inside Received Date Wise SpineLabel");
				frmdt = Security
						.TextDate_member(request.getParameter("fromdt"));
				todt = Security.TextDate_member(request.getParameter("todt"));
				doc_type = request.getParameter("Type");

				if (doc_type.equals("ALL")) {
					filterQuery = "received_date between '" + frmdt + "' and '"
							+ todt + "'ORDER BY LPAD(access_no, 50,'')";
				} else {
					filterQuery = "received_date between '" + frmdt + "' and '"
							+ todt + "' and document='" + doc_type
							+ "'ORDER BY LPAD(access_no, 50,'')";
				}

			}

			if (filterQuery != null) {
				sb.append(namedQuery);
				sb.append(" " + filterQuery);
			} else {
				sb.append(namedQuery);
			}

			
			
			if (flag != null)
			{
				
			
			
			
			parameters.put("CMP_NAME", ReportQueryUtill.COMPANY_NAME);
			parameters.put("CMP_ADD", ReportQueryUtill.COMPANY_ADD);
			parameters.put("ReportTitle", ReportQueryUtill.SpineLabel_Title);
			parameters.put("All_Query", sb.toString());
			log4jLogger.info("namedQuery: " + sb);

			parameters.put("All_Query", sb.toString());

			
			log4jLogger.info("INSIDE PRINT REPORT");
			
			
			InputStream inputStream1 = getServletContext().getResourceAsStream(
					"/Report/Spine_Label.jasper");
			JasperReport report = (JasperReport) JRLoader
					.loadObject(inputStream1);
			JasperPrint jasperPrint = JasperFillManager.fillReport(report,
					parameters, con);
			JRAbstractExporter exporterPDF = new JRPdfExporter();
			exporterPDF.setParameter(JRExporterParameter.JASPER_PRINT,
					jasperPrint);
			exporterPDF.setParameter(JRExporterParameter.OUTPUT_STREAM,
					response.getOutputStream());
			response.setHeader("Content-Disposition", "attachment;filename="
					+ ReportQueryUtill.SpineLabel_Title + ".pdf");
			response.setContentType("application/pdf");
			exporterPDF.exportReport();
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

	String indexPage = "/SpineLabel/index.jsp";

	public void dispatch(HttpServletRequest request,
			HttpServletResponse response, String indexPage)
			throws ServletException, IOException {
		RequestDispatcher dispatch = request.getRequestDispatcher(indexPage);
		dispatch.forward(request, response);
	}
}
