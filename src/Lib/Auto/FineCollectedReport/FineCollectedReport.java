package Lib.Auto.FineCollectedReport;
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
import Lib.Auto.PaymentInfo.PaymentInfoExportWizard;

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




public class FineCollectedReport extends HttpServlet implements Serializable {
	private static Logger log4jLogger = Logger.getLogger(FineCollectedReport.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String flag=null;
	String term="";
	String indexPage = null;
	String frmdt=null,todt=null;	
	java.sql.Connection con = null;
	java.sql.PreparedStatement Prest = null;
	java.sql.ResultSet rs = null;
	Map parameters = new HashMap();		
	String namedQuery= "SELECT staff_code,SUM(Amount) AS Amount,DATE_FORMAT(Payment_date,'%d/%m/%Y') as payment_date FROM payment_clearance WHERE 2>1";
	String namedQuery1=ReportQueryUtill.Paymentinfochart_query;
	String Strsql="",printType="";
	public void doGet(HttpServletRequest request, HttpServletResponse response) {

		performTask(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) {

		performTask(request, response);

	}
	public void performTask(
		HttpServletRequest request,
		HttpServletResponse response) {			
		try 
		{
		HttpSession session = request.getSession(true);
		String res = Security.checkSecurity(1, session, response, request);		
		if(res.equalsIgnoreCase("Failure"))
		{
			try {
				response.sendRedirect("/AutoLib/Tree/sessiontimeout.jsp");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
			return;
		}
		
		ReportService rs = BusinessServiceFactory.INSTANCE.getReportService();
		ImportExportXMLService importExportXMLService = BusinessServiceFactory.INSTANCE.getImportExportXMLService();
		ExceImportExportService csvImportExportService = BusinessServiceFactory.INSTANCE.getExceImportExportService();
						
		con=SessionHibernateUtil.getSession().connection();
		
		response.setContentType("application/json");
		Object obj=request.getParameter("flag");
		
   
		
		flag=request.getParameter("flag");
		printType = request.getParameter("printType");
		
//		Object obj = flag ;
		if(obj==null)
		{
			flag = "";
			printType = "";
		}
		
		StringBuffer sb = new StringBuffer();
		String filterQuery="";
		
		if(flag.equals("PaidReport"))
		{		
		
		log4jLogger.info("start=========== Payment Info Report =====");			
	
		
		if (!request.getParameter("Sname").equalsIgnoreCase("ALL") && !request.getParameter("Sname").isEmpty())
			
			filterQuery = filterQuery+" and staff_code='"+request.getParameter("Sname")+"'";
		
		frmdt=Security.TextDate_member(request.getParameter("fromdate"));
		todt=Security.TextDate_member(request.getParameter("todate"));
						
		 
		 if(!frmdt.equals("") && (!todt.equals("")))
		 {
			 
			 filterQuery=filterQuery+" and payment_date>='"+frmdt+"' and payment_date<='"+todt+"'";
			 
		 }
		 
		 if(!request.getParameter("Type").equalsIgnoreCase("ALL") && !request.getParameter("Type").isEmpty())
		 {
			 
			 filterQuery = filterQuery+" and pay_mode='"+request.getParameter("Type")+"'";
			 
		 }
			
			sb.append(namedQuery);
			sb.append(" " + filterQuery);
			sb.append(" "+" GROUP BY staff_code,payment_date ORDER BY payment_date ASC");
		
			boolean checkData = rs.getCheckData(sb.toString());// for check no data
			
			log4jLogger.info("-------All_Query-------Values"+sb.toString());
			if(checkData){
				log4jLogger.info("-------No Record Found-------Values");
				indexPage = "/FineCollectionReport/index.jsp?check=NoData";
				dispatch(request, response, indexPage);

			}
			else{
			
			//log4jLogger.info("-------All_Query-------Values"+sb.toString());
			
		
				if(printType.equalsIgnoreCase("pdf"))
				{
					parameters.put("CMP_NAME",ReportQueryUtill.COMPANY_NAME);
					parameters.put("CMP_ADD",ReportQueryUtill.COMPANY_ADD);
					parameters.put("ReportTitle",ReportQueryUtill.FineCollected_Title);
					parameters.put("All_Query",sb.toString());	
					parameters.put("frmdt",request.getParameter("fromdate").replace('-', '/'));
					parameters.put("todt",request.getParameter("todate").replace('-', '/'));
					log4jLogger.info("namedQuery ::::::::::::: " + sb);					
	
			
					InputStream inputStream1 = getServletContext().getResourceAsStream("/Report/FineCollected_Report.jasper");
					JasperReport report = (JasperReport) JRLoader.loadObject(inputStream1);			
				
					JasperPrint jasperPrint  = JasperFillManager.fillReport(report, parameters,con);			
				
					JRAbstractExporter exporterPDF = new JRPdfExporter();
					exporterPDF.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
					exporterPDF.setParameter(JRExporterParameter.OUTPUT_STREAM, response.getOutputStream());
					response.setHeader("Content-Disposition", "attachment;filename="+ReportQueryUtill.FineCollected_Title+".pdf");
					response.setContentType("application/pdf");
					exporterPDF.exportReport(); 
				}
		if(printType.equalsIgnoreCase("excel")){
			List<?> prepareSearchCriteriaLst=null;
			prepareSearchCriteriaLst = importExportXMLService.getFineCollectedExportWizard(sb.toString());
			Map<Object, Object> excelTitleMap = new HashMap<Object, Object>();
			excelTitleMap.put("From_Accno",frmdt);
			excelTitleMap.put("To_Accno",todt);
			excelTitleMap.put("Type", request.getParameter("Type"));
			
			Iterator<?> Finecollected = prepareSearchCriteriaLst.iterator();
		     FineCollectedExportWizard recordProcessor = new FineCollectedExportWizard(excelTitleMap);
			response.setContentType("text/csv");
			 //response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-Disposition","attachment; filename=" + recordProcessor.getExcelFileName());
			csvImportExportService.Export(Finecollected, recordProcessor, response.getOutputStream());
		}
		}
		
		}
}	  
		catch (Exception  e)
		{		

			e.printStackTrace();
		}
		finally
		{
			if(con!=null)
			{
				try 
				{
					con.close();
				} catch (SQLException e) 
				{
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
