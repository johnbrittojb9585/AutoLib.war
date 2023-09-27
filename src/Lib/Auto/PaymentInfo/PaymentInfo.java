package Lib.Auto.PaymentInfo;
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

@WebServlet("/PaymentInfo/PaymentInfoServlet")


public class PaymentInfo extends HttpServlet implements Serializable {
	private static Logger log4jLogger = Logger.getLogger(PaymentInfo.class);
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
	Map parameters = new HashMap<>();		
	String namedQuery=ReportQueryUtill.Paymentinfo_query;
	String namedQuery1=ReportQueryUtill.Payment_unpaid_query;
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
		
		try{
			String term = request.getParameter("name");
			if(!term.equalsIgnoreCase(null) && !term.equalsIgnoreCase(""))
            {
			//System.out.println("Data from ajax call " + term);
						    
			   ArrayList<MemberBean> list = rs.getPaymentReportUserAutoSearch(term);
		       for(MemberBean user: list){
				//System.out.println(user.getName());
			}       

			String searchList = new Gson().toJson(list);
						
//			response.getWriter().write(searchList);  
						
			//System.out.println("Json Value@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+searchList);
            }	
	}catch(Exception e){
		//e.printStackTrace();
	}  		 


	try{
			String term = request.getParameter("desig");
			
			if(!term.equalsIgnoreCase(null) && !term.equalsIgnoreCase("") && obj==null)
             {
			//System.out.println("Data from ajax call " + term);
						    
			   ArrayList<DesignationBean> list = rs.getPaymentReportDesigAutoSearch(term);
		       for(DesignationBean user: list){
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
		String term = request.getParameter("group");
		
		if(!term.equalsIgnoreCase(null) && !term.equalsIgnoreCase("") && obj==null)
         {
		//System.out.println("Data from ajax call " + term);
					    
		   ArrayList<GroupBean> list = rs.getPaymentReportGroupAutoSearch(term);
	       for(GroupBean user: list){
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
		String term = request.getParameter("course");
		
		if(!term.equalsIgnoreCase(null) && !term.equalsIgnoreCase("") && obj==null)
         {
		//System.out.println("Data from ajax call " + term);
					    
		   ArrayList<CourseBean> list = rs.getPaymentReportCourseAutoSearch(term);
	       for(CourseBean user: list){
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
		String term = request.getParameter("dept");
		
		if(!term.equalsIgnoreCase(null) && !term.equalsIgnoreCase("") && obj==null)
         {
		//System.out.println("Data from ajax call " + term);
					    
		   ArrayList<DepartmentBean> list = rs.getPaymentReportDeptAutoSearch(term);
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
		
		System.out.println(":::::::::Flagh:::::::"+flag);
		if(flag.equals("load")) 
			
		{
				
			MemberTransRefBean beanObject = new MemberTransRefBean();
			
			List DesigList = rs.getDesigList(beanObject);				
			request.setAttribute("DesigSearchList", DesigList);
			
			List GroupList = rs.getGroupList(beanObject);
			request.setAttribute("GroupSearchList", GroupList);
			
			List DeptList = rs.getDepartmentList(beanObject);
			request.setAttribute("DeptSearchList", DeptList);
			
			List CourseList = rs.getCourseList(beanObject);
			request.setAttribute("CourseSearchList", CourseList);
			
			indexPage = "/PaymentInfo/index.jsp";	
			dispatch(request, response, indexPage);
		}
		
		frmdt = Security.TextDate_member(request.getParameter("fromdate"));
		todt = Security.TextDate_member(request.getParameter("todate"));
				
		if(flag.equals("PaidReport"))
		{		
		
		log4jLogger.info("start=========== Payment Info Report =====");		
		
           if(!request.getParameter("fromdate").equals("") && !request.getParameter("todate").equals(""))
			
			filterQuery = filterQuery+" AND Payment_date BETWEEN '"+frmdt+"' AND '"+todt+"'";
			
		if (!request.getParameter("name").equalsIgnoreCase("ALL") && !request.getParameter("name").isEmpty())
			
			filterQuery = filterQuery+" and member_name='"+request.getParameter("name")+"'";
		
		if (!request.getParameter("desig").equalsIgnoreCase("ALL") && !request.getParameter("desig").isEmpty())
			
			filterQuery = filterQuery+" and desig_name='"+request.getParameter("desig")+"'";
		
		if (!request.getParameter("dept").equalsIgnoreCase("ALL") && !request.getParameter("dept").isEmpty())
			
			filterQuery = filterQuery+" and dept_name='"+request.getParameter("dept")+"'";
		
		if (!request.getParameter("group").equalsIgnoreCase("ALL") && !request.getParameter("group").isEmpty())
			
			filterQuery = filterQuery+" and group_name='"+request.getParameter("group")+"'";
		
		if (!request.getParameter("course").equalsIgnoreCase("ALL") && !request.getParameter("course").isEmpty())
			
			filterQuery = filterQuery+" and course_name='"+request.getParameter("course")+"'";
		 
	
		
			sb.append(namedQuery);
			sb.append(" " + filterQuery);
			sb.append(" "+"order by payment_date");
		
			boolean checkData = rs.getCheckData(sb.toString());// for check no data
			
			if (checkData)
			{
				
				log4jLogger.info("-------All_Query-------Values"+sb.toString());
				
				log4jLogger.info("----------------NO DATA FOUND-------------------");
				  indexPage = "/PaymentInfo/index.jsp?check=NoData";
				  dispatch(request, response, indexPage);
			}
			else
			{
				if(printType.equalsIgnoreCase("pdf"))
				{
					parameters.put("CMP_NAME",ReportQueryUtill.COMPANY_NAME);
					parameters.put("CMP_ADD",ReportQueryUtill.COMPANY_ADD);
					parameters.put("ReportTitle",ReportQueryUtill.Paymentinfo_Title);
					parameters.put("All_Query",sb.toString());				
					parameters.put("frmdt", request.getParameter("fromdate").replace('-', '/'));
					parameters.put("todt",request.getParameter("todate").replace('-', '/'));
					log4jLogger.info("namedQuery: " + sb);					
	
			
					InputStream inputStream1 = getServletContext().getResourceAsStream("/Report/Payment_Report.jasper");
					JasperReport report = (JasperReport) JRLoader.loadObject(inputStream1);			
				
					JasperPrint jasperPrint  = JasperFillManager.fillReport(report, parameters,con);			
				
					JRAbstractExporter exporterPDF = new JRPdfExporter();
					exporterPDF.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
					exporterPDF.setParameter(JRExporterParameter.OUTPUT_STREAM, response.getOutputStream());
					response.setHeader("Content-Disposition", "attachment;filename="+ReportQueryUtill.Paymentinfo_Title+".pdf");
					response.setContentType("application/pdf");
					exporterPDF.exportReport(); 
				}
				if(printType.equalsIgnoreCase("excel"))
				{
					System.out.println(":::::::::INSIDE EXCEL::::::");
					List<?> prepareSearchCriteriaLst=null;
					prepareSearchCriteriaLst = importExportXMLService.getPaymentInfoExportWizard(sb.toString(),flag);
					Map<Object, Object> excelTitleMap = new HashMap<Object, Object>();
//					excelTitleMap.put("From_Accno",frmdt);
//    				excelTitleMap.put("To_Accno",todt);
//    				excelTitleMap.put("Type", request.getParameter("Type"));
    				excelTitleMap.put("rptFlag", flag);
					
					Iterator<?> Paymentinfo = prepareSearchCriteriaLst.iterator();
					PaymentInfoExportWizard recordProcessor = new PaymentInfoExportWizard(excelTitleMap);
					response.setContentType("text/csv");
					 //response.setContentType("application/vnd.ms-excel");
					response.setHeader("Content-Disposition","attachment; filename=" + recordProcessor.getExcelFileName());
					csvImportExportService.Export(Paymentinfo, recordProcessor, response.getOutputStream());
				}
			}
				
		
		}
		
		if(flag.equals("UnPaidReport"))
		{
			log4jLogger.info("start=========== Payment Unpaid Report =====");		
			
			if (!request.getParameter("name").equalsIgnoreCase("ALL") && !request.getParameter("name").isEmpty())
				
				filterQuery = filterQuery+" and member_name='"+request.getParameter("name")+"'";
			
			if (!request.getParameter("desig").equalsIgnoreCase("ALL") && !request.getParameter("desig").isEmpty())
				
				filterQuery = filterQuery+" and desig_name='"+request.getParameter("desig")+"'";
			
			if (!request.getParameter("dept").equalsIgnoreCase("ALL") && !request.getParameter("dept").isEmpty())
				
				filterQuery = filterQuery+" and dept_name='"+request.getParameter("dept")+"'";
			
			if (!request.getParameter("group").equalsIgnoreCase("ALL") && !request.getParameter("group").isEmpty())
				
				filterQuery = filterQuery+" and group_name='"+request.getParameter("group")+"'";
			
			if (!request.getParameter("course").equalsIgnoreCase("ALL") && !request.getParameter("course").isEmpty())
				
				filterQuery = filterQuery+" and course_name='"+request.getParameter("course")+"'";
		
		
		
		sb.append(namedQuery1);
		sb.append(" " + filterQuery);
		/*sb.append(" "+"GROUP BY YEAR,payment ORDER BY YEAR,MONTH(payment_date) ASC");*/
		
		boolean checkData = rs.getCheckData(sb.toString());// for check no data
		
		if (checkData)
		{
			
			log4jLogger.info("-------All_Query-------Values"+sb.toString());
			
			log4jLogger.info("----------------NO DATA FOUND-------------------");
			  indexPage = "/PaymentInfo/index.jsp?check=NoData";
			  dispatch(request, response, indexPage);
		}
		else
		{
			if(printType.equalsIgnoreCase("pdf"))
		{
			parameters.put("CMP_NAME",ReportQueryUtill.COMPANY_NAME);
			parameters.put("CMP_ADD",ReportQueryUtill.COMPANY_ADD);
			parameters.put("ReportTitle",ReportQueryUtill.Paymentinfo_Unpaid);
			parameters.put("All_Query",sb.toString());				
			log4jLogger.info("namedQuery1: " + sb);					

	
			InputStream inputStream1 = getServletContext().getResourceAsStream("/Report/Payment_Unpaid.jasper");
			JasperReport report = (JasperReport) JRLoader.loadObject(inputStream1);			
		
			JasperPrint jasperPrint  = JasperFillManager.fillReport(report, parameters,con);			
		
			JRAbstractExporter exporterPDF = new JRPdfExporter();
			exporterPDF.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
			exporterPDF.setParameter(JRExporterParameter.OUTPUT_STREAM, response.getOutputStream());
			response.setHeader("Content-Disposition", "attachment;filename="+ReportQueryUtill.Paymentinfo_Unpaid +".pdf");
			response.setContentType("application/pdf");
			exporterPDF.exportReport(); 
		}
		if(printType.equalsIgnoreCase("excel"))
		{
			System.out.println(":::::::::INSIDE EXCEL::::::");
			List<?> prepareSearchCriteriaLst=null;
			prepareSearchCriteriaLst = importExportXMLService.getPaymentInfoExportWizard(sb.toString(),flag);
			Map<Object, Object> excelTitleMap = new HashMap<Object, Object>();
//			excelTitleMap.put("From_Accno",frmdt);
//			excelTitleMap.put("To_Accno",todt);
//			excelTitleMap.put("Type", request.getParameter("Type"));
			excelTitleMap.put("rptFlag", flag);
			
			Iterator<?> Paymentinfo = prepareSearchCriteriaLst.iterator();
			PaymentInfoExportWizard recordProcessor = new PaymentInfoExportWizard(excelTitleMap);
			response.setContentType("text/csv");
			 //response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-Disposition","attachment; filename=" + recordProcessor.getExcelFileName());
			csvImportExportService.Export(Paymentinfo, recordProcessor, response.getOutputStream());
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
