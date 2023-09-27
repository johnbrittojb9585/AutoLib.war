package Lib.Auto.MissingResourceReport;

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
import Common.businessutil.importexportexcel.ExceImportExportService;
import Common.businessutil.importexportxml.ImportExportXMLService;
import Common.businessutil.reports.ReportQueryUtill;
import Common.businessutil.reports.ReportService;
import Lib.Auto.Unique_Report.UniqueexcelReport;

import java.sql.*;

public class MissResourceReport extends HttpServlet implements ReportQueryUtill {
	
	private static Logger log4jLogger = Logger.getLogger(MissResourceReport.class);	
	
	java.sql.Connection con = null;
	
	
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
			
		String frmdt=null,todt=null;
		String access_no=null; String flag=null;
		String doc_type=null; String availability=null;
		String ALL=null,printType=null;
		
		
		con=SessionHibernateUtil.getSession().connection();
		ImportExportXMLService importExportXMLService = BusinessServiceFactory.INSTANCE.getImportExportXMLService();
		ExceImportExportService csvImportExportService = BusinessServiceFactory.INSTANCE.getExceImportExportService();
		ReportService rs=BusinessServiceFactory.INSTANCE.getReportService();
		
		flag=request.getParameter("flag");
		Map parameters = new HashMap();		
		String namedQuery=ReportQueryUtill.MissResourceReportQuery_Acc_no;
		StringBuffer sb = new StringBuffer();
		String filterQuery=null;
		printType = request.getParameter("printType");
		if(flag.equals("Date_Wise"))
		{
			log4jLogger.info("Inside Received Date Wise Report");
			frmdt=Security.TextDate_member(request.getParameter("fromdt"));
			todt=Security.TextDate_member(request.getParameter("todt"));
			doc_type=request.getParameter("Type");
			availability=request.getParameter("availability");

			if((doc_type==doc_type) && (availability==availability))
			{
				filterQuery="mdate between '"+frmdt+"' and '"+todt+"' and doc_type='"+doc_type+"' and status='"+availability+"'";
			}
			
			if((doc_type==doc_type) && (availability.equals("ALL")))
			{
				filterQuery="mdate between '"+frmdt+"' and '"+todt+"' and doc_type='"+doc_type+"'";
			}
			
			if((availability==availability) && (doc_type.equals("ALL")))
			{
				filterQuery="mdate between '"+frmdt+"' and '"+todt+"' and status='"+availability+"'";
			}
			

			if(doc_type.equals("ALL")&& (availability.equals("ALL")))
			{
				filterQuery="mdate between '"+frmdt+"' and '"+todt+"'";
			}
		
			
		 
		 if (filterQuery != null)
			{
				sb.append(namedQuery);
				sb.append(" " + filterQuery);
			}
			else
			{
				sb.append(namedQuery);
			}
			
			parameters.put("CMP_NAME",ReportQueryUtill.COMPANY_NAME);
			parameters.put("CMP_ADD",ReportQueryUtill.COMPANY_ADD);
			parameters.put("ReportTitle",ReportQueryUtill.MissResourceReport_Title);
			parameters.put("All_Query",sb.toString());	
			parameters.put("fromdt", Security.getdate(frmdt).replace('-','/'));
			parameters.put("todt", Security.getdate(todt).replace('-','/'));
			log4jLogger.info("namedQuery: " + sb);				
			
		}
		if(printType.equalsIgnoreCase("pdf"))
		{
		InputStream inputStream1 = getServletContext().getResourceAsStream("/Report/MissingResource_Report.jasper");
		JasperReport report = (JasperReport) JRLoader.loadObject(inputStream1);				
				
		JasperPrint jasperPrint  = JasperFillManager.fillReport(report, parameters,con);			
	
		JRAbstractExporter exporterPDF = new JRPdfExporter();
        exporterPDF.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        exporterPDF.setParameter(JRExporterParameter.OUTPUT_STREAM, response.getOutputStream());
        response.setHeader("Content-Disposition", "attachment;filename="+ReportQueryUtill.MissResourceReport_Title+".pdf");
        response.setContentType("application/pdf");
        exporterPDF.exportReport(); 
		}
		if(printType.equalsIgnoreCase("excel"))
		{
			log4jLogger.info("--------------Inside excelReport Printing Process----------------");
			
			
			log4jLogger.info("namedQuery: " + sb);

			boolean checkData = rs.getCheckData(sb.toString());
			 
			if (checkData)
				{
				 //no data
					log4jLogger.info("----------------NO DATA FOUND-------------------");
				  indexPage = "/Unique_Report/index.jsp?check=NoData";
				  dispatch(request, response, indexPage);
				 
				}
			else
			{   
				List<?> prepareSearchCriteriaLst=null;
				prepareSearchCriteriaLst = importExportXMLService.getMissResourceExcel(sb.toString());
				
				
				Map<Object, Object> excelTitleMap = new HashMap<Object, Object>();
				excelTitleMap.put("From_Accno",frmdt);
				excelTitleMap.put("To_Accno",todt);
				excelTitleMap.put("Type", doc_type);
				excelTitleMap.put("avail",availability);
				Iterator<?> MissResourceExcel = prepareSearchCriteriaLst.iterator();
				
				MissResourceExcel recordProcessor = new MissResourceExcel(excelTitleMap);
				response.setContentType("text/csv");
				 //response.setContentType("application/vnd.ms-excel");
				response.setHeader("Content-Disposition","attachment; filename=" + recordProcessor.getExcelFileName());
				csvImportExportService.Export(MissResourceExcel, recordProcessor, response.getOutputStream());
				
			}
		}
		}catch (Exception  e) {		

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
	String indexPage="/MissingResourceReport/index.jsp";
	
	public void dispatch(
			HttpServletRequest request,
			HttpServletResponse response,
			String indexPage)
			throws ServletException, IOException {
			RequestDispatcher dispatch = request.getRequestDispatcher(indexPage);
			dispatch.forward(request, response);
		}
}

