package Lib.Auto.BibliographyReport;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.sql.SQLException;
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

import Common.Security;
import Lib.Auto.Counter.COUNTER_QUERY;
import Lib.Auto.LibraryCollection.LibraryCollectionExcel;
import Common.businessutil.BusinessServiceFactory;
import Common.businessutil.importexportexcel.ExceImportExportService;
import Common.businessutil.importexportxml.ImportExportXMLService;
import Common.businessutil.reports.ReportQueryUtill;
import Common.businessutil.reports.ReportService;


public class BibliographyReport extends HttpServlet implements Serializable, COUNTER_QUERY {
	
	
	private static final long serialVersionUID = 1L;
	java.sql.Connection con = null;
	java.sql.ResultSet rs = null;

	String flag="",option_type="",strsql="",strsql1="",strsql2="",strsql3="",strsql4="",strsql5="",flagreport="";
	String indexPage = null;
	
	
	String frm_accno="",to_accno="",frm_dt="",to_dt="",call_no="",ExcelReportName="";
	
	String firstStr = null, secondStr = null;
	int firstNum = 0, secondNum = 0, firstStrCount = 0;
	InputStream inputStream1 = null;

	
	Map parameters = new HashMap();  
	
	public static boolean IsNumeric(String string)
    {	 
	  try
	  {
		Integer.parseInt(string);
		return  true;
	  }catch(Exception e){
		return  false;
	  }			
    }
	
	private static Logger log4jLogger = Logger.getLogger(BibliographyReport.class);

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
		
			log4jLogger.info("Inside from BibliographyReport Reports");
			
			strsql="";
			strsql1="";
			strsql2="";
			strsql3="";
			strsql4="";
			String strsql_year="";
			
			ReportService rs1=BusinessServiceFactory.INSTANCE.getReportService();
			ImportExportXMLService importExportXMLService = BusinessServiceFactory.INSTANCE.getImportExportXMLService();
			ExceImportExportService csvImportExportService = BusinessServiceFactory.INSTANCE.getExceImportExportService();
			flag=request.getParameter("report_type");
			flagreport=request.getParameter("flag");
			   if(!flag.equals("") && !flag.equals("ACCESSION") && !flag.equals("CALLNO") && !flag.equals("RECEIVEDDATE") )
			   {	           
				   strsql5="";
	        	   option_type=request.getParameter("option_name");
	           
	          
	           if(flag.equals("Aut"))
	           {
	        	   strsql5="";
	        	   if(!option_type.isEmpty() && option_type != null){
	        		   strsql2=" and author_name like '%"+option_type+"%'";   
	        	   }
	        	   
				if (!request.getParameter("year_pub").equals("") && !request.getParameter("year_pub").equals(null))
	        		   strsql_year = " and year_pub='"+request.getParameter("year_pub")+"'";
				
	        	   inputStream1 = getServletContext().getResourceAsStream("/Report/Bibliography_Report.jasper");
	        	   parameters.put("ReportTitle",ReportQueryUtill.BiblioAuthor_Title);
	        	   ExcelReportName = ReportQueryUtill.BiblioAuthor_Title;
	        	   strsql5 = " ORDER BY convert(access_no,signed) ASC ";
	           }
	           if(flag.equals("Dept"))
	           {
	        	   strsql5="";
	        	   if(!option_type.isEmpty() && option_type != null){
	        	   strsql2=" and dept_name = '"+option_type+"'";
	        	   }
	        	   if (!request.getParameter("year_pub").equals("") && !request.getParameter("year_pub").equals(null))
	        		   strsql_year = " and year_pub='"+request.getParameter("year_pub")+"'";
	        	   inputStream1 = getServletContext().getResourceAsStream("/Report/Bibliography_Department.jasper");
	        	   parameters.put("ReportTitle",ReportQueryUtill.BiblioDept_Title); 
	        	   ExcelReportName = ReportQueryUtill.BiblioDept_Title;
	        	   strsql5 = " ORDER BY convert(access_no,signed) ASC ";
	           }
	           if(flag.equals("Pub"))
	           {
	        	   strsql5="";
	        	   if(!option_type.isEmpty() && option_type != null){
	        	   strsql2=" and publisher = '"+option_type+"'";
	        	   }
	        	   if (!request.getParameter("year_pub").equals("") && !request.getParameter("year_pub").equals(null))
	        		   strsql_year = " and year_pub='"+request.getParameter("year_pub")+"'";
	        	   
	        	   inputStream1 = getServletContext().getResourceAsStream("/Report/Bibliography_Publisher.jasper");
	        	   parameters.put("ReportTitle",ReportQueryUtill.BiblioPub_Title); 
	        	   ExcelReportName = ReportQueryUtill.BiblioPub_Title;
	        	   strsql5 = " ORDER BY convert(access_no,signed) ASC  ";
	           }
	           if(flag.equals("Sup"))
	           {
	        	   strsql5="";
	        	   if(!option_type.isEmpty() && option_type != null){
	        	   strsql2=" and supplier = '"+option_type+"'";
	        	   }
	        	   if (!request.getParameter("year_pub").equals("") && !request.getParameter("year_pub").equals(null))
	        		   strsql_year = " and year_pub='"+request.getParameter("year_pub")+"'";
	        	   inputStream1 = getServletContext().getResourceAsStream("/Report/Bibliography_Supplier.jasper");
	        	   parameters.put("ReportTitle",ReportQueryUtill.BiblioSup_Title); 
	        	   ExcelReportName = ReportQueryUtill.BiblioSup_Title;
	        	   strsql5 = " ORDER BY convert(access_no,signed) ASC ";
	           }
	           if(flag.equals("Sub"))
	           {
	        	   strsql5="";
	        	   if(!option_type.isEmpty() && option_type != null){
	        	   strsql2=" and sub_name = '"+option_type+"'";
	        	   }
	        	   if (!request.getParameter("year_pub").equals("") && !request.getParameter("year_pub").equals(null))
	        		   strsql_year = " and year_pub='"+request.getParameter("year_pub")+"'";
	        	   inputStream1 = getServletContext().getResourceAsStream("/Report/Bibliography_Subject.jasper");
	        	   parameters.put("ReportTitle",ReportQueryUtill.Bibliosubject_Title); 
	        	   ExcelReportName = ReportQueryUtill.Bibliosubject_Title;
	        	   strsql5 = " ORDER BY convert(access_no,signed) ASC ";
	           }
	           if(flag.equals("Bud"))
	           {
	        	   strsql5="";
	        	   if(!option_type.isEmpty() && option_type != null){
	        	   strsql2=" and budget = '"+option_type+"'";
	        	   }
	        	   if (!request.getParameter("year_pub").equals("") && !request.getParameter("year_pub").equals(null))
	        		   strsql_year =" and year_pub='"+request.getParameter("year_pub")+"'";
	        	   inputStream1 = getServletContext().getResourceAsStream("/Report/Bibliography_Budget.jasper");
	        	   parameters.put("ReportTitle",ReportQueryUtill.BiblioBudged_Title); 
	        	   ExcelReportName = ReportQueryUtill.BiblioBudged_Title;
	        	   strsql5 = " ORDER BY convert(Access_no,signed),Budget ASC ";
	           }
	           
	                   
			   }
			   
			   if(flag.equals("ACCESSION"))
	           {
				   strsql5="";
				   frm_accno=request.getParameter("From_Accno");
				   to_accno=request.getParameter("To_Accno");
				 
				 
				if (IsNumeric(request.getParameter("From_Accno"))
						&& IsNumeric(request.getParameter("To_Accno"))) {

					log4jLogger
							.info("inside Accession number wise numbers only");

					strsql1 = " and access_no REGEXP '^[0-9]+$' AND CAST(access_no AS SIGNED)  BETWEEN '"
							+ frm_accno + "' and '" + to_accno + "' ";
					strsql="";
					strsql5 = " ORDER BY CAST(access_no AS SIGNED)";
					

				}

				else {
					strsql5="";
					log4jLogger.info("Inside STringData");

					firstNum = Integer.parseInt(request.getParameter("firstNum"));
					secondNum = Integer.parseInt(request.getParameter("secondNum"));

					firstStr = request.getParameter("firstStr");
					
					firstStrCount = firstStr.length() + 1;
					secondStr = request.getParameter("secondStr");
					
					strsql1 = " and  access_no NOT REGEXP '^[0-9]+$' AND ACCESS_NO LIKE '"+firstStr+"%' AND  CAST(SUBSTRING(access_no,'"+firstStrCount+"') AS SIGNED) BETWEEN '"+ firstNum + "' and '" + secondNum + "' ";
					strsql="";
					strsql5 = " ORDER BY LPAD(access_no, 25, '0');";
					
					
				}
				
				
				
					inputStream1 = getServletContext().getResourceAsStream(
							"/Report/Bibliography_Report.jasper");
					parameters.put("ReportTitle",
							ReportQueryUtill.BiblioAccession_Title);
					ExcelReportName = ReportQueryUtill.BiblioAccession_Title;
			}

			
			   
			   if(flag.equals("CALLNO"))
	           {
				   strsql5="";
				   if(!option_type.isEmpty() && option_type != null){
				   frm_accno=request.getParameter("From_Accno");
				   }
				   if (!request.getParameter("year_pub").equals("") && !request.getParameter("year_pub").equals(null))
	        		   strsql_year = " and year_pub='"+request.getParameter("year_pub")+"'";
				   //to_accno=request.getParameter("To_Accno");
				   
	        	   strsql1=" and call_no like '%"+frm_accno+"%'";
	        	   inputStream1 = getServletContext().getResourceAsStream("/Report/Bibliography_Report.jasper");
	        	   parameters.put("ReportTitle",ReportQueryUtill.BiblioCallno_Title); 
	        	   ExcelReportName = ReportQueryUtill.BiblioCallno_Title;
	        	   strsql5 = " ORDER BY Call_No ASC ;";
	           }
			   if(flag.equals("RECEIVEDDATE"))
	           {
				   strsql5="";
				   frm_dt=Security.TextDate_member(request.getParameter("fromdt"));
				   to_dt=Security.TextDate_member(request.getParameter("todt"));
				   
	        	   strsql1=" and received_date between '"+frm_dt+"' and '"+to_dt+"'";
	        	   inputStream1 = getServletContext().getResourceAsStream("/Report/Bibliography_Report.jasper");
	        	   parameters.put("ReportTitle",ReportQueryUtill.BiblioRevd_Title);  
	        	   ExcelReportName = ReportQueryUtill.BiblioRevd_Title;
	        	   strsql5 = " ORDER BY Received_Date ASC ";
	           }
	        
	           if(!request.getParameter("status").equals("") && !request.getParameter("status").equals("ALL")){
	            	 
	                 strsql3=" and availability='"+request.getParameter("status")+"'";
	           }
	           
	      	
	            con=SessionHibernateUtil.getSession().connection();
    	      	     	      	
    			String namedQuery=ReportQueryUtill.Biblio_Query;
    			StringBuffer sb = new StringBuffer();
    			String filterQuery=null;
    			
    			sb.append(namedQuery);
					sb.append(" " + strsql1+strsql2+strsql3+strsql4+strsql_year+strsql5);
					
					System.out.println(sb);
				   			        	
				if(flagreport.equalsIgnoreCase("pdf"))
				{
					boolean checkData = rs1.getCheckData(sb.toString());
					  if (checkData)
						{
						 //no data
							log4jLogger.info("----------------NO DATA FOUND-------------------");
						  indexPage = "/BibliographyReport/index.jsp?check=NoData";
						  dispatch(request, response, indexPage);
						 
						}
					  else
					  {    			
						  parameters.put("CMP_NAME",ReportQueryUtill.COMPANY_NAME);
						  parameters.put("CMP_ADD",ReportQueryUtill.COMPANY_ADD);
						  parameters.put("All_Query",sb.toString());				
						  log4jLogger.info("SQL QUERY: " + sb);		
				
						  JasperReport report = (JasperReport) JRLoader.loadObject(inputStream1);	
						  JasperPrint jasperPrint  = JasperFillManager.fillReport(report, parameters,con);			
							
						  JRAbstractExporter exporterPDF = new JRPdfExporter();
						  exporterPDF.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
						  exporterPDF.setParameter(JRExporterParameter.OUTPUT_STREAM, response.getOutputStream());
						  response.setHeader("Content-Disposition", "attachment;filename="+ReportQueryUtill.Biblio_Title+".pdf");
						  response.setContentType("application/pdf");
						  exporterPDF.exportReport(); 
					  }
				}	
				if(flagreport.equalsIgnoreCase("excel"))
				{
					List<?> prepareSearchCriteriaLst=null;
					prepareSearchCriteriaLst = importExportXMLService.getBibliographyExcel(sb.toString());
					Map<Object, Object> excelTitleMap = new HashMap<Object, Object>();
					excelTitleMap.put("Frequency", request.getParameter("status"));
					excelTitleMap.put("ReportName", ExcelReportName);	
					
					Iterator<?> Biblio = prepareSearchCriteriaLst.iterator();
					BibliographyExcel recordProcessor = new BibliographyExcel(excelTitleMap);
					response.setContentType("text/csv");
					response.setHeader("Content-Disposition","attachment; filename=" + recordProcessor.getExcelFileName());
					csvImportExportService.Export(Biblio, recordProcessor, response.getOutputStream());
				}
		}
		catch (Exception e) {
      e.printStackTrace();
	}
catch (Throwable theException) {
	
	}
		
		
	
		
finally{
	strsql="";
	strsql1="";
	strsql2="";
	strsql3="";
	strsql4="";	
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
