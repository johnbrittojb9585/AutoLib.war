package Lib.Auto.FreqUsedResource;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
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

import com.library.autolib.portal.dbconnectionutil.SessionHibernateUtil;

import Common.DataQuery;
import Common.Security;
import Lib.Auto.Counter.COUNTER_QUERY;
import Lib.Auto.Journal_Issues.JnlIssues;
import Lib.Auto.MissingResourceReport.MissResourceExcel;
import Common.DBConnection;
import Common.businessutil.BusinessServiceFactory;
import Common.businessutil.importexportexcel.ExceImportExportService;
import Common.businessutil.importexportxml.ImportExportXMLService;
import Common.businessutil.reports.ReportQueryUtill;
import Common.businessutil.reports.ReportService;


public class FreqUsedServlet extends HttpServlet implements Serializable, COUNTER_QUERY {
	private static final long serialVersionUID = 1L;
	java.sql.Connection con = null;
	java.sql.PreparedStatement Prest = null;
	java.sql.ResultSet rs = null;
	java.sql.ResultSet rs1 = null;
	DataSource datasource;
	
	String flag="",protocol="",strsql="",strsql1="",strsql2="",strsql3="",strsql4="",strsql5="",strsql6="",printType="",frmdt="",todt="";
	String indexPage = null;

	ResourceBundle rb = ResourceBundle.getBundle("LocalStrings");
	 private static Logger log4jLogger = Logger.getLogger(FreqUsedServlet.class);
		
	public void doGet(HttpServletRequest request, HttpServletResponse response)  throws ServletException {

		performTask(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException{

		performTask(request, response);

	}
	public void performTask(
		HttpServletRequest request,
		HttpServletResponse response)  throws ServletException {


		try {
			 HttpSession session = request.getSession(true);
			 String res = Security.checkSecurity(1, session, response, request);		
				if(res.equalsIgnoreCase("Failure"))
				{
					response.sendRedirect("/AutoLib/Tree/sessiontimeout.jsp");  
					return;
				}
				
			response.setContentType("text/html");
         		
			printType = request.getParameter("printType");
			
			con=SessionHibernateUtil.getSession().connection();
			ImportExportXMLService importExportXMLService = BusinessServiceFactory.INSTANCE.getImportExportXMLService();
			ExceImportExportService csvImportExportService = BusinessServiceFactory.INSTANCE.getExceImportExportService();
			ReportService rs=BusinessServiceFactory.INSTANCE.getReportService();
			
			strsql="";
		    frmdt=Security.TextDate_member(request.getParameter("fromdate"));
		    todt=Security.TextDate_member(request.getParameter("todate"));
            int recfrom=Integer.parseInt(request.getParameter("recfrom"));
            int recto_temp=Integer.parseInt(request.getParameter("recto"));
            int recto=recto_temp-recfrom;
            if(recfrom==1){
            recto++;	
            }
            if(recto<0){
            	recto=recto*(-1);
            }
            
            if(request.getParameter("r1").equals("frequency") && request.getParameter("txtdoctype").equals("ALL")){
            	//out.print("in");
            strsql="select Access_No,title,publisher,department,count(*) as total from counter_statistics_issue where issue_date between '"+Security.TextDate_Insert(request.getParameter("fromdate"))+"' and '"+Security.TextDate_Insert(request.getParameter("todate"))+"' group by access_no,title,publisher,department order by count(*) desc limit "+recfrom+","+recto+"";	
            }
            
            if(request.getParameter("r1").equals("frequency") && !request.getParameter("txtdoctype").equals("ALL")){
            	//out.print("inside");
            	strsql="select Access_No,title,publisher,department,count(*) as total from counter_statistics_issue where document='"+request.getParameter("txtdoctype")+"' and issue_date between '"+Security.TextDate_Insert(request.getParameter("fromdate"))+"' and '"+Security.TextDate_Insert(request.getParameter("todate"))+"' group by access_no,title,publisher,department order by count(*) desc limit "+recfrom+","+recto+"";	
            }
            
            if(request.getParameter("r1").equals("access") && request.getParameter("txtdoctype").equals("ALL")){
            strsql="select Access_No,title,publisher,department,member_code,count(*) as total from counter_statistics_issue where access_no='"+request.getParameter("txtaccess").trim()+"' and issue_date between '"+Security.TextDate_Insert(request.getParameter("fromdate"))+"' and '"+Security.TextDate_Insert(request.getParameter("todate"))+"' group by access_no,title,publisher,department,member_code order by Access_No";	
            }
            
            if(request.getParameter("r1").equals("access") && !request.getParameter("txtdoctype").equals("ALL")){
            	 strsql="select Access_No,title,publisher,department,member_code,count(*) as total from counter_statistics_issue where document='"+request.getParameter("txtdoctype")+"' and access_no='"+request.getParameter("txtaccess").trim()+"' and issue_date between '"+Security.TextDate_Insert(request.getParameter("fromdate"))+"' and '"+Security.TextDate_Insert(request.getParameter("todate"))+"' group by access_no,title,publisher,department,member_code order by Access_No";	
            }
            
            if(request.getParameter("r1").equals("title") && request.getParameter("txtdoctype").equals("ALL")){
            strsql="select Access_No,title,publisher,department,count(*) as total from counter_statistics_issue where title like '%" +request.getParameter("txttitle").trim()+ "%' and issue_date between '"+Security.TextDate_Insert(request.getParameter("fromdate"))+"' and '"+Security.TextDate_Insert(request.getParameter("todate"))+"' group by access_no,title,publisher,department order by count(*) desc limit "+recfrom+","+recto+"";	
            }
            
            if(request.getParameter("r1").equals("title") && !request.getParameter("txtdoctype").equals("ALL")){
            	strsql="select Access_No,title,publisher,department,count(*) as total from counter_statistics_issue where document='"+request.getParameter("txtdoctype")+"' and  title like '%" +request.getParameter("txttitle").trim()+ "%' and issue_date between '"+Security.TextDate_Insert(request.getParameter("fromdate"))+"' and '"+Security.TextDate_Insert(request.getParameter("todate"))+"' group by access_no,title,publisher,department order by count(*) desc limit "+recfrom+","+recto+"";	
            }
            
            if(request.getParameter("r1").equals("dept") && request.getParameter("txtdoctype").equals("ALL")){
            strsql="select Access_No,title,publisher,department,count(*) as total from counter_statistics_issue where department='"+request.getParameter("txtdept")+"' and issue_date between '"+Security.TextDate_Insert(request.getParameter("fromdate"))+"' and '"+Security.TextDate_Insert(request.getParameter("todate"))+"' group by access_no,title,publisher,department order by count(*) desc limit "+recfrom+","+recto+"";	
            }
            
            if(request.getParameter("r1").equals("dept") && !request.getParameter("txtdoctype").equals("ALL")){
            	strsql="select Access_No,title,publisher,department,count(*) as total from counter_statistics_issue where document='"+request.getParameter("txtdoctype")+"' and department='"+request.getParameter("txtdept")+"' and issue_date between '"+Security.TextDate_Insert(request.getParameter("fromdate"))+"' and '"+Security.TextDate_Insert(request.getParameter("todate"))+"' group by access_no,title,publisher,department order by count(*) desc limit "+recfrom+","+recto+"";	
            }
            
            
            
            if(request.getParameter("r1").equals("unused")){
            	if(request.getParameter("txtdoctype").equals("ALL")){
            strsql="SELECT Access_No,title,SP_Name AS publisher,dept_name AS department FROM full_search where access_no not in (select access_no from counter_statistics_issue where issue_Date between '"+Security.TextDate_Insert(request.getParameter("fromdate"))+"' and '"+Security.TextDate_Insert(request.getParameter("todate"))+"') limit "+recfrom+","+recto+"";
            	}            	
            	else{
            		strsql="SELECT Access_No,title,SP_Name AS publisher,dept_name AS department FROM full_search where document='"+request.getParameter("txtdoctype")+"' and access_no not in (select access_no from counter_statistics_issue where document='"+request.getParameter("txtdoctype")+"' and issue_Date between '"+Security.TextDate_Insert(request.getParameter("fromdate"))+"' and '"+Security.TextDate_Insert(request.getParameter("todate"))+"') limit "+recfrom+","+recto+"";	
            	}
            	
            	log4jLogger.info("Inside Frequent Unused Resource Report");	  
            
            	try
            	{
            	Map parameters = new HashMap();       	      	
     			String namedQuery=strsql;
     			StringBuffer sb = new StringBuffer();
     			String filterQuery=null;
     			
     			sb.append(namedQuery);
     			       			
     			if(printType.equalsIgnoreCase("pdf"))
     			{
     			parameters.put("CMP_NAME",ReportQueryUtill.COMPANY_NAME);
     			parameters.put("CMP_ADD",ReportQueryUtill.COMPANY_ADD);
     			parameters.put("ReportTitle",ReportQueryUtill.Frequently_UnUsedResource_Title);   				
     			parameters.put("All_Query",sb.toString());	
     			parameters.put("fromdate",Security.getdate(frmdt).replace('-', '/'));
     			parameters.put("todate",Security.getdate(todt).replace('-', '/'));
     			log4jLogger.info("SQL QUERY: " + sb);		
     			
     			InputStream inputStream1 = getServletContext().getResourceAsStream("/Report/Frequently_UnusedResource.jasper");
     			JasperReport report = (JasperReport) JRLoader.loadObject(inputStream1);	
     					
     			JasperPrint jasperPrint  = JasperFillManager.fillReport(report, parameters,con);			
     						
     			JRAbstractExporter exporterPDF = new JRPdfExporter();
     	        exporterPDF.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
     	        exporterPDF.setParameter(JRExporterParameter.OUTPUT_STREAM, response.getOutputStream());
     	        response.setHeader("Content-Disposition", "attachment;filename="+ReportQueryUtill.Frequently_Pdf_Title+".pdf");
     	        response.setContentType("application/pdf");
     	        exporterPDF.exportReport();            
     			}
     			if(printType.equalsIgnoreCase("excel"))
     			{
     				List<?> prepareSearchCriteriaLst=null;
    				prepareSearchCriteriaLst = importExportXMLService.getFreqUsedResourceExcel(sb.toString(),request.getParameter("r1"));
    				
    				
    				Map<Object, Object> excelTitleMap = new HashMap<Object, Object>();
    				excelTitleMap.put("From_Accno",request.getParameter("fromdate"));
    				excelTitleMap.put("To_Accno",request.getParameter("todate"));
    				excelTitleMap.put("Doc", request.getParameter("txtdoctype"));
    				excelTitleMap.put("Type",request.getParameter("r1"));
    				Iterator<?> FreqUsedResourceExcel = prepareSearchCriteriaLst.iterator();
    				
    				FreqUsedResourceExcel recordProcessor = new FreqUsedResourceExcel(excelTitleMap);
    				response.setContentType("text/csv");
    				 //response.setContentType("application/vnd.ms-excel");
    				response.setHeader("Content-Disposition","attachment; filename=" + recordProcessor.getExcelFileName());
    				csvImportExportService.Export(FreqUsedResourceExcel, recordProcessor, response.getOutputStream());
     			}
            	}catch(Exception e)
            	{
            		e.printStackTrace();
            	}
           
            }
            
            else if(request.getParameter("r1").equals("access")){
            	
            	log4jLogger.info("Inside Frequent Used Resource with Access_no Report");	            	
     	      	try{
     	      	Map parameters = new HashMap();       	      	
     			String namedQuery=strsql;
     			StringBuffer sb = new StringBuffer();
     			String filterQuery=null;
     			
     			sb.append(namedQuery);
     				//sb.append(" " +strsql1 +strsql2);
     			   			        			
     			if(printType.equalsIgnoreCase("pdf"))
     			{
     			parameters.put("CMP_NAME",ReportQueryUtill.COMPANY_NAME);
     			parameters.put("CMP_ADD",ReportQueryUtill.COMPANY_ADD);
     			parameters.put("ReportTitle",ReportQueryUtill.Frequently_Resource_Title);   				
     			parameters.put("All_Query",sb.toString());
     			parameters.put("fromdate",Security.getdate(frmdt).replace('-', '/'));
     			parameters.put("todate",Security.getdate(todt).replace('-', '/'));
     			log4jLogger.info("SQL QUERY: " + sb);		
     			
     			InputStream inputStream1 = getServletContext().getResourceAsStream("/Report/Frequently_Resource_AccessNo.jasper");
     			JasperReport report = (JasperReport) JRLoader.loadObject(inputStream1);	
     					
     			JasperPrint jasperPrint  = JasperFillManager.fillReport(report, parameters,con);			
     						
     			JRAbstractExporter exporterPDF = new JRPdfExporter();
     	        exporterPDF.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
     	        exporterPDF.setParameter(JRExporterParameter.OUTPUT_STREAM, response.getOutputStream());
     	        response.setHeader("Content-Disposition", "attachment;filename="+ReportQueryUtill.Frequently_Pdf_Title+".pdf");
     	        response.setContentType("application/pdf");
     	        exporterPDF.exportReport();            
             
     			}	
     			if(printType.equalsIgnoreCase("excel"))
     			{
     				List<?> prepareSearchCriteriaLst=null;
    				prepareSearchCriteriaLst = importExportXMLService.getFreqUsedResourceExcel(sb.toString(),request.getParameter("r1"));
    				
    				
    				Map<Object, Object> excelTitleMap = new HashMap<Object, Object>();
    				excelTitleMap.put("From_Accno",request.getParameter("fromdate"));
    				excelTitleMap.put("To_Accno",request.getParameter("todate"));
    				excelTitleMap.put("Doc", request.getParameter("txtdoctype"));
    				excelTitleMap.put("Type",request.getParameter("r1"));
    				Iterator<?> FreqUsedResourceExcel = prepareSearchCriteriaLst.iterator();
    				
    				FreqUsedResourceExcel recordProcessor = new FreqUsedResourceExcel(excelTitleMap);
    				response.setContentType("text/csv");
    				 //response.setContentType("application/vnd.ms-excel");
    				response.setHeader("Content-Disposition","attachment; filename=" + recordProcessor.getExcelFileName());
    				csvImportExportService.Export(FreqUsedResourceExcel, recordProcessor, response.getOutputStream());
     			}
     	      	}catch(Exception e)
     	      	{
     	      		e.printStackTrace();
     	      	}
            
            }
			 
            
            
            else{
            	
            	log4jLogger.info("Inside Frequent Used Resource Report");	            	
     	      	try{
     	      	Map parameters = new HashMap();       	      	
     			String namedQuery=strsql;
     			StringBuffer sb = new StringBuffer();
     			String filterQuery=null;
     			
     			sb.append(namedQuery);
     				//sb.append(" " +strsql1 +strsql2);
     			   			        			
     			if(printType.equalsIgnoreCase("pdf"))
     			{
     			parameters.put("CMP_NAME",ReportQueryUtill.COMPANY_NAME);
     			parameters.put("CMP_ADD",ReportQueryUtill.COMPANY_ADD);
     			parameters.put("ReportTitle",ReportQueryUtill.Frequently_Resource_Title);   				
     			parameters.put("All_Query",sb.toString());	
     			parameters.put("fromdate",Security.getdate(frmdt).replace('-', '/'));
     			parameters.put("todate",Security.getdate(todt).replace('-', '/'));
     			log4jLogger.info("SQL QUERY: " + sb);		
     			
     			InputStream inputStream1 = getServletContext().getResourceAsStream("/Report/Frequently_Resource.jasper");
     			JasperReport report = (JasperReport) JRLoader.loadObject(inputStream1);	
     					
     			JasperPrint jasperPrint  = JasperFillManager.fillReport(report, parameters,con);			
     						
     			JRAbstractExporter exporterPDF = new JRPdfExporter();
     	        exporterPDF.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
     	        exporterPDF.setParameter(JRExporterParameter.OUTPUT_STREAM, response.getOutputStream());
     	        response.setHeader("Content-Disposition", "attachment;filename="+ReportQueryUtill.Frequently_Pdf_Title+".pdf");
     	        response.setContentType("application/pdf");
     	        exporterPDF.exportReport();            
             
     			}	
     			if(printType.equalsIgnoreCase("excel"))
     			{
     				List<?> prepareSearchCriteriaLst=null;
    				prepareSearchCriteriaLst = importExportXMLService.getFreqUsedResourceExcel(sb.toString(),request.getParameter("r1"));
    				
    				
    				Map<Object, Object> excelTitleMap = new HashMap<Object, Object>();
    				excelTitleMap.put("From_Accno",request.getParameter("fromdate"));
    				excelTitleMap.put("To_Accno",request.getParameter("todate"));
    				excelTitleMap.put("Doc", request.getParameter("txtdoctype"));
    				excelTitleMap.put("Type",request.getParameter("r1"));
    				Iterator<?> FreqUsedResourceExcel = prepareSearchCriteriaLst.iterator();
    				
    				FreqUsedResourceExcel recordProcessor = new FreqUsedResourceExcel(excelTitleMap);
    				response.setContentType("text/csv");
    				 //response.setContentType("application/vnd.ms-excel");
    				response.setHeader("Content-Disposition","attachment; filename=" + recordProcessor.getExcelFileName());
    				csvImportExportService.Export(FreqUsedResourceExcel, recordProcessor, response.getOutputStream());
     			}
     	      	}catch(Exception e)
     	      	{
     	      		e.printStackTrace();
     	      	}
            
            }
			 
	}  catch (Exception e) {

	}
catch (Throwable theException) {
	
	}
finally{
	try{
		if(con!=null)
		{
			try {					
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(rs!=null){
			rs.close();
			rs=null;
		}
		if(Prest!=null){
			Prest.close();
			Prest=null;
		}

	}catch(Exception e){
	e.printStackTrace();
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
