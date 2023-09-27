package Lib.Auto.JournalList;

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
import java.util.ResourceBundle;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.UnavailableException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.apache.log4j.Logger;

import com.library.autolib.portal.dbconnectionutil.SessionHibernateUtil;

import net.sf.jasperreports.engine.JRAbstractExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import Common.DataQuery;
import Common.Security;
import Lib.Auto.Counter.COUNTER_QUERY;
import Lib.Auto.Journal_ArticleSearch.JournalAtlSearchbean;
import Lib.Auto.MemberReport.MemberReport;
import Lib.Auto.MissingResourceReport.MissResourceExcel;
import Lib.Auto.OrdInvProcessing.orderbean;
import Lib.Auto.Department.DepartmentBean;
import Lib.Auto.Subject.subjectbean;
import Common.DBConnection;
import Common.businessutil.BusinessServiceFactory;
import Common.businessutil.importexportexcel.ExceImportExportService;
import Common.businessutil.importexportxml.ImportExportXMLService;
import Common.businessutil.reports.ReportQueryUtill;
import Common.businessutil.reports.ReportService;
import javax.servlet.annotation.WebServlet;
import com.google.gson.Gson;

@WebServlet("/JournalList/JournalListServlet")


public class JournalList extends HttpServlet implements Serializable, COUNTER_QUERY {
	private static final long serialVersionUID = 1L;
	java.sql.Connection con = null;
	java.sql.PreparedStatement Prest = null;
	java.sql.ResultSet rs = null;
	java.sql.ResultSet rs1 = null;
	DataSource datasource;
	
	String flag="",protocol="",strsql="",strsql1="",strsql2="",strsql3="",strsql4="",strsql5="",strsql6="",printType="";
	String indexPage = null;
	orderbean orderObject=new orderbean();
	ResourceBundle rb = ResourceBundle.getBundle("LocalStrings");
	
	private static Logger log4jLogger = Logger.getLogger(JournalList.class);
	
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
           
			strsql="";
			strsql1="";
			strsql2="";
			strsql3="";
			strsql4="";
			strsql5="";
			
			ImportExportXMLService importExportXMLService = BusinessServiceFactory.INSTANCE.getImportExportXMLService();
			ExceImportExportService csvImportExportService = BusinessServiceFactory.INSTANCE.getExceImportExportService();
			ReportService rs=BusinessServiceFactory.INSTANCE.getReportService();
			
            response.setContentType("application/json");
            Object obj=request.getParameter("printType");
			
			try{
				String term = request.getParameter("dept_name");
				if(!term.equalsIgnoreCase(null) && !term.equalsIgnoreCase("") && obj==null)
	            {
				//System.out.println("Data from ajax call " + term);
							    
				   ArrayList<DepartmentBean> list = rs.getJournalListDeptAutoSearch(term);
			       for(DepartmentBean user: list){
					//System.out.println(user.getName());
				}       

				String searchList = new Gson().toJson(list);
							
				response.getWriter().write(searchList);  
							
				//System.out.println("Json Value@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+searchList);
	            }	
		}catch(Exception e){
			//e.printStackTrace();
		}  		 


		try{
				String term = request.getParameter("sub_name");
				
				if(!term.equalsIgnoreCase(null) && !term.equalsIgnoreCase("") && obj==null)
	             {
				//System.out.println("Data from ajax call " + term);
							    
				   ArrayList<subjectbean> list = rs.getJournalListSubjectAutoSearch(term);
			       for(subjectbean user: list){
					//System.out.println(user.getName());
				}       

				String searchList = new Gson().toJson(list);
							
				response.getWriter().write(searchList);  
							
				//System.out.println("Json Value@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+searchList);
	             }	
		}catch(Exception e){
			//e.printStackTrace();
		}

			
			printType = request.getParameter("printType");
           if(!request.getParameter("jmo").equals("NO")){
        	  strsql1=" and doc_type='"+request.getParameter("jmo")+"'";
              }
           if(!request.getParameter("country").equals("NO")){
              strsql2=" and country='"+request.getParameter("country")+"'";
              }
            
           if(!request.getParameter("frequency").equals("NO")){
              strsql3=" and frequency='"+request.getParameter("frequency")+"'";
              }
                  
           if(!request.getParameter("dept_name").equals("")){
              strsql4=" and department='"+request.getParameter("dept_name")+"'";
              }
           if(!request.getParameter("sub_name").equals("")){
              strsql5=" and sub_name='"+request.getParameter("sub_name")+"'";
              }
           
           
           
            
           
             con=SessionHibernateUtil.getSession().connection();
	      	
	      	Map parameters = new HashMap();       	      	
			String namedQuery=ReportQueryUtill.JNL_Query;
			StringBuffer sb = new StringBuffer();
			String filterQuery=null;
			
			sb.append(namedQuery);
				sb.append(" " +strsql1 +strsql2 +strsql3 +strsql4 +strsql5);
			   			        			
			
			parameters.put("CMP_NAME",ReportQueryUtill.COMPANY_NAME);
			parameters.put("CMP_ADD",ReportQueryUtill.COMPANY_ADD);
			parameters.put("ReportTitle",ReportQueryUtill.JNL_List_Title);   				
			parameters.put("All_Query",sb.toString());				
			log4jLogger.info("SQL QUERY: " + sb);		
			
			if(printType.equalsIgnoreCase("pdf"))
			{
			InputStream inputStream1 = getServletContext().getResourceAsStream("/Report/Journal_List.jasper");
			JasperReport report = (JasperReport) JRLoader.loadObject(inputStream1);	
					
			JasperPrint jasperPrint  = JasperFillManager.fillReport(report, parameters,con);			
						
			JRAbstractExporter exporterPDF = new JRPdfExporter();
	        exporterPDF.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
	        exporterPDF.setParameter(JRExporterParameter.OUTPUT_STREAM, response.getOutputStream());
	        response.setHeader("Content-Disposition", "attachment;filename="+ReportQueryUtill.JNL_List_Title+".pdf");
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
					prepareSearchCriteriaLst = importExportXMLService.getJournalListExcel(sb.toString());
					
					
					Map<Object, Object> excelTitleMap = new HashMap<Object, Object>();
					
					Iterator<?> JournalListExcel = prepareSearchCriteriaLst.iterator();
					
					JournalListExcel recordProcessor = new JournalListExcel(excelTitleMap);
					response.setContentType("text/csv");
					 //response.setContentType("application/vnd.ms-excel");
					response.setHeader("Content-Disposition","attachment; filename=" + recordProcessor.getExcelFileName());
					csvImportExportService.Export(JournalListExcel, recordProcessor, response.getOutputStream());
					
				}
			}
         	        
	}  catch (Exception e) {

	}
catch (Throwable theException) {
	
	}
finally{
	strsql="";
	strsql1="";
	strsql2="";
	strsql3="";
	strsql4="";
	strsql5="";
	strsql6="";

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
