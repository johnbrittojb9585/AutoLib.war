package Lib.Auto.QBReport;


import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
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

import Common.Security;
import Common.businessutil.BusinessServiceFactory;
import Common.businessutil.admin.AdminService;
import Common.businessutil.calaloging.CalalogingService;
import Common.businessutil.importexportexcel.ExceImportExportService;
import Common.businessutil.importexportxml.ImportExportXMLService;
import Common.businessutil.reports.ReportQueryUtill;

import com.library.autolib.portal.dbconnectionutil.SessionHibernateUtil;
//import antlr.collections.List;
public class QBReport extends HttpServlet implements Serializable,ReportQueryUtill {
	private static Logger log4jLogger = Logger.getLogger(QBReport.class);
	private static final long serialVersionUID = 1L;
		java.sql.Connection con = null;
		java.sql.PreparedStatement Prest = null;
		String indexPage = null;
		String print="",reportType="",flag="",index="",status="",frmdt="",todt="",doc="",printType="";
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
				
				log4jLogger.info("Inside QB  Reports");
				
				con=SessionHibernateUtil.getSession().connection();
				
				CalalogingService ss = BusinessServiceFactory.INSTANCE.getCalalogingService();
				AdminService ss1 = BusinessServiceFactory.INSTANCE.getAdminService();								
				ImportExportXMLService importExportXMLService = BusinessServiceFactory.INSTANCE.getImportExportXMLService();
				ExceImportExportService csvImportExportService = BusinessServiceFactory.INSTANCE.getExceImportExportService();
				
				response.setContentType("application/json");
				
				flag = request.getParameter("flag");
				printType = request.getParameter("printType");


			 if(request.getParameter("flag")!=null) {
			if (request.getParameter("flag").equals("load")) {
				List departmentList = ss1.getDepartmentList();				
				request.setAttribute("departmentSearchList", departmentList);
				
				List courseList = ss1.getCourseList();
				request.setAttribute("courseSearchList", courseList);
				
				List QBSubjectList = ss1.getQBSubjectList();
				request.setAttribute("QBSubjectSearchList", QBSubjectList);
				indexPage = "/QBReport/index.jsp";
				dispatch(request, response, indexPage);
			}	
			 }
				
			
			 if(flag.equals("Report")){
					System.out.println("+++++++++++++++++++++++++++");
					String flag="",strsql="",strsql1="",strsql2="",strsql3="",strsql4="",strsql5="",strsql6="";
					
					log4jLogger.info("start===========Printing report=====");
						
					strsql="";
					strsql1="";
					strsql2="";
					strsql3="";
					strsql4="";
					strsql5="";
					strsql6="";
					
					
				if (!request.getParameter("dname").equals("NO")) {
					strsql1 = " and dept='" + request.getParameter("dname")
							+ "'";
				}
			 if (!request.getParameter("qcourse").equals("NO")) {

					strsql2 = " and Course='" + request.getParameter("qcourse")
							+ "'";

				}
			 if(!request.getParameter("subname").equals("NO")){
					strsql3= "and Sub_name='" + request.getParameter("subname") + "'";	
             }
					
			 if(!request.getParameter("subcode").equals("NO")){
					strsql4= " and Sub_code='" + request.getParameter("subcode") + "'";	
             }
				
			if(!request.getParameter("qyear").equals("")){
					strsql5= " and Year ='" + request.getParameter("qyear") + "'";	
            }
			if(!request.getParameter("qcode").equals("")){
				strsql6= " and Qb_code= '" + request.getParameter("qcode")+  "'";
			}
			
					 con=SessionHibernateUtil.getSession().connection();
		    	      	
		    	      	Map parameters = new HashMap();       	      	
		    			String namedQuery=ReportQueryUtill.QB_Report_Query;
		    			StringBuffer sb = new StringBuffer();
		    			String filterQuery=null;
		    			sb.append(namedQuery);
						sb.append(" " + strsql1+strsql2+strsql3+strsql4+strsql5+strsql6);
						
						log4jLogger.info(":::::::::::::Report:::Type:::::::"+request.getParameter("printType"));
						reportType=request.getParameter("printType");
						System.out.println("======================================"+reportType);
						
						if(printType.equals("pdf"))
						{			
					   			        
							parameters.put("CMP_NAME",ReportQueryUtill.COMPANY_NAME);
							parameters.put("CMP_ADD",ReportQueryUtill.COMPANY_ADD);
							parameters.put("ReportTitle",ReportQueryUtill.QB_Report_Title);
							parameters.put("All_Query",sb.toString());				
					
					
							log4jLogger.info("SQL QUERY: " + sb);			
				
							InputStream inputStream1 = getServletContext().getResourceAsStream("/Report/QB_Report.jasper");			
							JasperReport report = (JasperReport) JRLoader.loadObject(inputStream1);	
						
							JasperPrint jasperPrint  = JasperFillManager.fillReport(report, parameters,con);	
					
							JRAbstractExporter exporterPDF = new JRPdfExporter();	        
							exporterPDF.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);	        
							exporterPDF.setParameter(JRExporterParameter.OUTPUT_STREAM, response.getOutputStream());
					
							response.setHeader("Content-Disposition", "attachment;filename="+ReportQueryUtill.QB_Report_Title+".pdf");	
					
							response.setContentType("application/pdf");	        
							exporterPDF.exportReport(); 	               
						}
					
						 if(printType.equalsIgnoreCase("excel")) 
						{		
					
						List prepareSearchCriteriaLst = importExportXMLService.getQBReportList(sb.toString());
						
						Map<Object, Object> excelTitleMap = new HashMap<Object, Object>();
					     
						excelTitleMap.put("rptTitle", ReportQueryUtill.QB_Report_Title);
		
						Iterator Transfer = (prepareSearchCriteriaLst).iterator();
						QuestionExportRecord recordProcessor = new QuestionExportRecord(excelTitleMap);
						response.setContentType("text/csv");
						response.setHeader("Content-Disposition","attachment; filename=" + recordProcessor.getExcelFileName());
						csvImportExportService.Export(Transfer, recordProcessor,response.getOutputStream());
			        
				}
						
					
				}	
					
				
			} catch (Exception ss1) {
//				throw new ServletException(ss1);
				ss1.printStackTrace();
				
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