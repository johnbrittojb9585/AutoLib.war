package Lib.Auto.LibraryCollection;
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
import Common.businessutil.calaloging.CalalogingDaoImpl;
import Common.businessutil.importexportexcel.ExceImportExportService;
import Common.businessutil.importexportxml.ImportExportXMLService;
import Common.businessutil.reports.ReportQueryUtill;
import Common.businessutil.reports.ReportService;
import Lib.Auto.Statistics.StatisticsWiseExportRecord;

import java.sql.*;

public class LibraryCollection extends HttpServlet implements ReportQueryUtill {
	/**
	 * 
	 */
	private static Logger log4jLogger = Logger.getLogger(LibraryCollection.class);	
		
	java.sql.Connection con = null;
		
	String frmaccno,toaccno,strsql="";
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException {

		performTask(request, response);

	}
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

		performTask(request, response);

	}	
	
	public void performTask(
		HttpServletRequest request,
		HttpServletResponse response)  throws ServletException, IOException {
						
		try {		
			log4jLogger.info("Inside Library Collection ");	
			HttpSession session = request.getSession(true);		
			String res = Security.checkSecurity(1, session, response, request);		
			if(res.equalsIgnoreCase("Failure"))
			{
				response.sendRedirect("/AutoLib/Tree/sessiontimeout.jsp");  
				return;
			}
			
			
			String frmaccno=null,toaccno=null;
			String frmdt=null,todt=null;			
			String flag=null,doc_type=null;	
			flag = request.getParameter("flag");
			ImportExportXMLService importExportXMLService = BusinessServiceFactory.INSTANCE.getImportExportXMLService();
			ExceImportExportService csvImportExportService = BusinessServiceFactory.INSTANCE.getExceImportExportService();	
			//ReportService ss = BusinessServiceFactory.INSTANCE.getReportService();			
			//con=ss.getDBConnect();					
			
			 con=SessionHibernateUtil.getSession().connection();
			 System.out.println(":::::FLAG:::::::"+flag);
			if(flag.equalsIgnoreCase("pdf"))
			{
			 Map parameters = new HashMap();		
					
			
				parameters.put("CMP_NAME",ReportQueryUtill.COMPANY_NAME);
				parameters.put("CMP_ADD",ReportQueryUtill.COMPANY_ADD);
				parameters.put("ReportTitle",ReportQueryUtill.Library_Collection_Title);		
				
		
			//InputStream inputStream1 = getClass().getResourceAsStream("/Report/Library_Collection.jasper"); // It is working in JBOSS server but Not Working in Appache Tomcat Server.				
			InputStream inputStream1 = getServletContext().getResourceAsStream("/Report/Library_Collection.jasper");
			JasperReport report = (JasperReport) JRLoader.loadObject(inputStream1);	
			
			JasperPrint jasperPrint  = JasperFillManager.fillReport(report, parameters,con);			
			
			JRAbstractExporter exporterPDF = new JRPdfExporter();
	        exporterPDF.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
	        exporterPDF.setParameter(JRExporterParameter.OUTPUT_STREAM, response.getOutputStream());
	        response.setHeader("Content-Disposition", "attachment;filename="+ReportQueryUtill.Library_Collection_Title+".pdf");
	        response.setContentType("application/pdf");
	        exporterPDF.exportReport(); 
	               
			}
			if(flag.equalsIgnoreCase("excel"))
			{
					List<?> prepareSearchCriteriaLst=null;
					String sqlQuery = "select * from library_collection_final" ;
					prepareSearchCriteriaLst = importExportXMLService.getLibraryCollectionExcel(sqlQuery);
					Map<Object, Object> excelTitleMap = new HashMap<Object, Object>();
					
					Iterator<?> statisticsDataItr = prepareSearchCriteriaLst.iterator();
					LibraryCollectionExcel recordProcessor = new LibraryCollectionExcel();
					response.setContentType("text/csv");
					 //response.setContentType("application/vnd.ms-excel");
					response.setHeader("Content-Disposition","attachment; filename=" + recordProcessor.getExcelFileName());
					csvImportExportService.Export(statisticsDataItr, recordProcessor, response.getOutputStream());
			}
			if(flag.equalsIgnoreCase("chart"))
			{
				strsql="select * from library_collection_final";
				Map parameters = new HashMap();		
				parameters.put("CMP_NAME",ReportQueryUtill.COMPANY_NAME);
				parameters.put("CMP_ADD",ReportQueryUtill.COMPANY_ADD);
				parameters.put("All_Query",strsql);
				parameters.put("ReportTitle",ReportQueryUtill.Library_Collection_Title);		
				
		
			//InputStream inputStream1 = getClass().getResourceAsStream("/Report/Library_Collection.jasper"); // It is working in JBOSS server but Not Working in Appache Tomcat Server.				
			InputStream inputStream1 = getServletContext().getResourceAsStream("/Report/Library_Collection_barchart.jasper");
			JasperReport report = (JasperReport) JRLoader.loadObject(inputStream1);	
			
			JasperPrint jasperPrint  = JasperFillManager.fillReport(report, parameters,con);			
			
			JRAbstractExporter exporterPDF = new JRPdfExporter();
	        exporterPDF.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
	        exporterPDF.setParameter(JRExporterParameter.OUTPUT_STREAM, response.getOutputStream());
	        response.setHeader("Content-Disposition", "attachment;filename="+ReportQueryUtill.Library_Collection_Title+".pdf");
	        response.setContentType("application/pdf");
	        exporterPDF.exportReport(); 
	               
			}
					
		}  catch (Exception  e) {		

			e.printStackTrace();
		}
		finally
		{
			if(con!=null)
			{
				try {					
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
		
		
	
	public void dispatch(
			HttpServletRequest request,
			HttpServletResponse response,
			String indexPage)
			throws ServletException, IOException {
		   // response.sendRedirect(indexPage);
			RequestDispatcher dispatch = request.getRequestDispatcher(indexPage);
			dispatch.forward(request, response);
		}
}
