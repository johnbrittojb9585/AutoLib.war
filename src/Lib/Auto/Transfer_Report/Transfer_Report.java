package Lib.Auto.Transfer_Report;


import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.UnavailableException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import net.sf.jasperreports.engine.JRAbstractExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;

import org.apache.log4j.Logger;

//import antlr.collections.List;

import com.library.autolib.portal.dbconnectionutil.SessionHibernateUtil;

import Common.DataQuery;
import Common.Security;
import Lib.Auto.Account.GateRegisterExportRecord;
import Lib.Auto.Counter.COUNTER_QUERY;
import Lib.Auto.OrdInvProcessing.orderbean;
import Lib.Auto.Report.ReportAll;



import Common.DBConnection;
import Common.businessutil.BusinessServiceFactory;
import Common.businessutil.circulation.CirculationService;
import Common.businessutil.importexportexcel.ExceImportExportService;
import Common.businessutil.importexportxml.ImportExportXMLService;
import Common.businessutil.reports.ReportQueryUtill;
import Common.businessutil.reports.ReportService;
public class Transfer_Report extends HttpServlet implements Serializable,ReportQueryUtill {
	private static Logger log4jLogger = Logger.getLogger(Transfer_Report.class);
	private static final long serialVersionUID = 1L;
		java.sql.Connection con = null;
		java.sql.PreparedStatement Prest = null;
		String indexPage = null;
		String printType="",reportType="",flag="",index="",status="",frmdt="",todt="",doc="";
	public void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException {

		performTask(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException {

		performTask(request, response);

	}
	public void performTask(
			HttpServletRequest request,
			HttpServletResponse response) throws ServletException {

		try {
				
				HttpSession session = request.getSession(true);
				String res = Security.checkSecurity(1, session, response, request);		
				if(res.equalsIgnoreCase("Failure"))
				{
					response.sendRedirect("/AutoLib/Tree/sessiontimeout.jsp");  
					return;
				}
				
				log4jLogger.info("Inside Transfer  Reports");
				
				con=SessionHibernateUtil.getSession().connection();
				
				CirculationService ss = BusinessServiceFactory.INSTANCE.getCirculationService();
												
				ImportExportXMLService importExportXMLService = BusinessServiceFactory.INSTANCE.getImportExportXMLService();
				ExceImportExportService csvImportExportService = BusinessServiceFactory.INSTANCE.getExceImportExportService();

			
				
			if (request.getParameter("flag").equals("load")) {
				log4jLogger.info("start===========load=====");
				java.util.List BindingArrylist = new ArrayList();
				BindingArrylist = ss.getTransferedDeptName();

				request.setAttribute("binding", BindingArrylist);
				indexPage = "/Transfer_Report/index.jsp";
				dispatch(request, response, indexPage);
			}  
				
				
				
				if(request.getParameter("flag").equals("print")){
					flag = request.getParameter("flag");
					frmdt=Security.TextDate_member(request.getParameter("fromdate"));
					todt=Security.TextDate_member(request.getParameter("todate"));
					status = request.getParameter("status");
					doc = request.getParameter("doc");
					
					String flag="",strsql="",strsql1="",strsql2="",strsql3="";
					
					log4jLogger.info("start===========Printing report=====");
						
					strsql="";
					strsql1="";
					strsql2="";
					strsql3="";
					
					
					
				if (!request.getParameter("dept").equals("ALL")) {
					strsql1 = " and dept_code='" + request.getParameter("dept")
							+ "'";
				}
				if (!request.getParameter("doc").equals("ALL")) {

					strsql2 = "and doc_type='" + request.getParameter("doc")
							+ "'";

				}
				if (request.getParameter("status").equals("TRANSFERED")) {

					strsql3 = "and order_date between '" + frmdt + "' and '"
							+ todt + "' and recovery IN('',NULL)";

				} else {

					strsql3 = "and recovery_date between '" + frmdt + "' and '"
							+ todt + "' and recovery IN('YES')";
				}
					 
					 con=SessionHibernateUtil.getSession().connection();
		    	      	
		    	      	Map parameters = new HashMap();       	      	
		    			String namedQuery=ReportQueryUtill.Transfer_Report_Query;
		    			StringBuffer sb = new StringBuffer();
		    			String filterQuery=null;
		    			sb.append(namedQuery);
						sb.append(" " + strsql1+strsql2+strsql3);
						
						log4jLogger.info(":::::::::::::Report:::Type:::::::"+request.getParameter("printType"));
						reportType=request.getParameter("printType");
						
						if(reportType.equals("pdf"))
						{			
					   			        
							parameters.put("CMP_NAME",ReportQueryUtill.COMPANY_NAME);
							parameters.put("CMP_ADD",ReportQueryUtill.COMPANY_ADD);
					
							if (request.getParameter("status").equals("TRANSFERED")) 
							{
								parameters.put("ReportTitle",ReportQueryUtill.Transfer_Reports_Title);
							}else
							{
								parameters.put("ReportTitle",ReportQueryUtill.Re_Transfer_Reports_Title);
							}
						
							parameters.put("From",Security.getdate(frmdt).replace('-','/'));
							parameters.put("To",Security.getdate(todt).replace('-','/'));
							parameters.put("All_Query",sb.toString());				
					
					
							log4jLogger.info("SQL QUERY: " + sb);			
				
							InputStream inputStream1 = getServletContext().getResourceAsStream("/Report/Transfered_Report.jasper");			
							JasperReport report = (JasperReport) JRLoader.loadObject(inputStream1);	
						
							JasperPrint jasperPrint  = JasperFillManager.fillReport(report, parameters,con);	
					
							JRAbstractExporter exporterPDF = new JRPdfExporter();	        
							exporterPDF.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);	        
							exporterPDF.setParameter(JRExporterParameter.OUTPUT_STREAM, response.getOutputStream());
					
							response.setHeader("Content-Disposition", "attachment;filename="+ReportQueryUtill.Transfer_Reports_Title+".pdf");	
					
							response.setContentType("application/pdf");	        
							exporterPDF.exportReport(); 	               
						}
						else 
						{		
						List prepareSearchCriteriaLst = importExportXMLService.getTransferReportList(sb.toString());
						
						Map<Object, Object> excelTitleMap = new HashMap<Object, Object>();
					
						excelTitleMap.put("from", frmdt);
						excelTitleMap.put("to", todt);
						excelTitleMap.put("DocType", doc);
						excelTitleMap.put("ReportType", status);
						
						Iterator Transfer = (prepareSearchCriteriaLst).iterator();
						TransferExportRecord recordProcessor = new TransferExportRecord(excelTitleMap);
						response.setContentType("text/csv");
						response.setHeader("Content-Disposition","attachment; filename=" + recordProcessor.getExcelFileName());
						csvImportExportService.Export(Transfer, recordProcessor,response.getOutputStream());
			        
				}
				}	
					
				
			} catch (Exception sss) {
				throw new ServletException(sss);
				
			} finally {
				
				try{					
					con.close();
				}catch(Exception e){
				e.printStackTrace();
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