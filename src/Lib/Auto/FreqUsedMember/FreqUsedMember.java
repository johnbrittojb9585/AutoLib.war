package Lib.Auto.FreqUsedMember;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
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

import Common.Security;
import Lib.Auto.Counter.COUNTER_QUERY;

import Lib.Auto.MemberTransfer.MemberTransRefBean;
import Lib.Auto.Statistics.singlecodecheck;
import Common.businessutil.BusinessServiceFactory;
import Common.businessutil.importexportexcel.ExceImportExportService;
import Common.businessutil.importexportxml.ImportExportXMLService;
import Common.businessutil.reports.ReportQueryUtill;
import Common.businessutil.reports.ReportService;


public class FreqUsedMember extends HttpServlet implements Serializable, COUNTER_QUERY {
	private static final long serialVersionUID = 1L;
	java.sql.Connection con = null;
	java.sql.PreparedStatement Prest = null;
	java.sql.ResultSet rs = null;
	java.sql.ResultSet rs1 = null;
	DataSource datasource;
	singlecodecheck chkcode=new singlecodecheck();
	String flag="",flag1="",r1="",protocol="",strsql="",strsql1="",strsql2="",strsql3="",strsql7="",strsql8="",strsql9="",strsql10;
	String indexPage = null,printType="",frmdt="",todt="";

	ResourceBundle rb = ResourceBundle.getBundle("LocalStrings");
	 int recfrom;
     int recto_temp;
	 private static Logger log4jLogger = Logger.getLogger(FreqUsedMember.class);
		
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
			log4jLogger.info("===========");	
			 HttpSession session = request.getSession(true);
			 String res = Security.checkSecurity(1, session, response, request);		
				if(res.equalsIgnoreCase("Failure"))
				{
					response.sendRedirect("/AutoLib/Tree/sessiontimeout.jsp");  
					return;
				}
				
			 response.setContentType("text/html");

			 con=SessionHibernateUtil.getSession().connection();
			 flag=request.getParameter("flag");
			 r1=request.getParameter("r1");
			
             
            
           ReportService ss1 = BusinessServiceFactory.INSTANCE.getReportService();
			
			ReportService rs=BusinessServiceFactory.INSTANCE.getReportService();
			

		System.out.println(":::::::::::::::::Before Load::::::::::::::");
					
			ImportExportXMLService importExportXMLService = BusinessServiceFactory.INSTANCE.getImportExportXMLService();
			ExceImportExportService csvImportExportService = BusinessServiceFactory.INSTANCE.getExceImportExportService();
			
			System.out.println(":::::Flag::::::"+flag);
			
			if(flag.equals("load")){
				System.out.println(":::::::::::::::::After Load::::::::::::::");
				
				log4jLogger.info("---------------- Frequent Member Report Load Details----------------");
				
				
			MemberTransRefBean beanObject = new MemberTransRefBean();
			/*List<?> branchWiseList = ss1.getBranchList(beanObject);				
			request.setAttribute("distinctBranchSearchList", branchWiseList);*/
			
			List BranchWiseDesigList = ss1.getDesigList(beanObject);				
			request.setAttribute("DesigSearchList", BranchWiseDesigList);
			
			List BranchWiseGroupList = ss1.getGroupList(beanObject);
			request.setAttribute("distinctBranchWiseGroupSearchList", BranchWiseGroupList);
			
			
			List BranchWiseDepartmentList = ss1.getDepartmentList(beanObject);				
			request.setAttribute("DepartmentList", BranchWiseDepartmentList);
			
			List BranchWiseCourseList = ss1.getCourseList(beanObject);
			request.setAttribute("distinctBranchWiseCourseSearchList", BranchWiseCourseList);
			
			indexPage = "/FrequentlyUsedMember/index.jsp";	
			dispatch(request, response, indexPage);
			
			
			}
			/*int rk=(Integer.parseInt((String.valueOf(session.getAttribute("UserBranchID")))));      // For Titan
			
			 if(rk==0){
				 strsql3 = "";				
			 }else  {
				 strsql3 = " and Branch_Code="+rk;	 
			 }			*/
			 printType = request.getParameter("printType");
			
			 frmdt=Security.TextDate_member(request.getParameter("fromdate"));
			 todt=Security.TextDate_member(request.getParameter("todate"));
            
			 System.out.println(frmdt+":::::::::frmdt::::::::::");
			 System.out.println(todt+":::::::::todt::::::::::");
//			  recfrom=Integer.parseInt(request.getParameter("recfrom"));
//	           recto_temp=Integer.parseInt(request.getParameter("recto"));
	            int recto=recto_temp-recfrom;
	            if(recfrom==1){
	            recto++;	
	            }
	            if(recto<0){
	            	recto=recto*(-1);
	            }
            
        

			 System.out.println(":::::::Button::::::::::::"+r1);
            
           
            if(r1.equals("member"))
            {	
            	
            strsql="SELECT MEMBER_CODE,MEMBER_NAME,DESIGNATION ,COURSE_YEAR,dept_name FROM MEMBER_REPORT_VIEW where MEMBER_CODE not in (select MEMBER_CODE from counter_statistics_issue where issue_Date between '"+Security.TextDate_Insert (request.getParameter("fromdate"))+"' and '"+Security.TextDate_Insert(request.getParameter("todate"))+"' "+ strsql3 + ") ";
            
            
            if(!request.getParameter("dept").equals("ALL"))
            {
            	strsql= strsql +" and dept_code='" +chkcode.getParam( request, "dept")+"'";
            }
            if(!request.getParameter("group").equals("ALL"))
            {
            	strsql= strsql +" and group_code='" +chkcode.getParam( request, "group")+"'";
            }
            if(!request.getParameter("year").equals("ALL"))
            {
            	strsql=strsql+" and course_year='" +request.getParameter("year")+"'";
            }
            strsql=strsql+  " limit "+request.getParameter("recfrom")+","+request.getParameter("recto")+"";
            System.out.println(":::::Limit To::::"+request.getParameter("recto"));
            
            
            log4jLogger.info("Inside Unused Member Report");	  
            
        	try
        	{
        	Map<String, Object> parameters = new HashMap<String, Object>();       	      	
 			String namedQuery=strsql;
 			StringBuffer sb = new StringBuffer();
 			     			
 			sb.append(namedQuery);
 			log4jLogger.info("SQL QUERY: " + sb);	
            
 			System.out.println(":::::::PRINTTYPE>>>>>>>>"+printType);
            if(printType.equals("pdf")){
                      	
            	parameters.put("CMP_NAME",ReportQueryUtill.COMPANY_NAME);
     			parameters.put("CMP_ADD",ReportQueryUtill.COMPANY_ADD);
     			parameters.put("ReportTitle",ReportQueryUtill.Frequently_Member);
     			parameters.put("fromdate",Security.getdate(frmdt).replace('-', '/'));
     			parameters.put("todate",Security.getdate(todt).replace('-', '/'));
     			parameters.put("All_Query",sb.toString());				
     				
     			
     			InputStream inputStream1 = getServletContext().getResourceAsStream("/Report/Frequently_UnusedMember.jasper");
     			JasperReport report = (JasperReport) JRLoader.loadObject(inputStream1);	
     					
     			JasperPrint jasperPrint  = JasperFillManager.fillReport(report, parameters,con);			
     						
     			JRAbstractExporter exporterPDF = new JRPdfExporter();
     	        exporterPDF.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
     	        exporterPDF.setParameter(JRExporterParameter.OUTPUT_STREAM, response.getOutputStream());
     	        response.setHeader("Content-Disposition", "attachment;filename="+ReportQueryUtill.Frequently_Pdf_Member+".pdf");
     	        response.setContentType("application/pdf");
     	        exporterPDF.exportReport();            
            	
            	}
          
            else
            {
            	System.out.println("::::::::::Inside EXCEL:::::::::::");
            	List<?> prepareSearchCriteriaLst=null;
				prepareSearchCriteriaLst = importExportXMLService.getFreqUsedMemberExcel(sb.toString(),request.getParameter("r1"));
				
				
				Map<Object, Object> excelTitleMap = new HashMap<Object, Object>();
				excelTitleMap.put("From_Accno",request.getParameter("fromdate"));
				excelTitleMap.put("To_Accno",request.getParameter("todate"));
				excelTitleMap.put("Doc", request.getParameter("txtdoctype"));
				excelTitleMap.put("Type",request.getParameter("r1"));
				Iterator<?> FreqUsedMemberExcel = prepareSearchCriteriaLst.iterator();
				
				FreqUsedMemberExcel recordProcessor = new FreqUsedMemberExcel(excelTitleMap);
				response.setContentType("text/csv");
				 //response.setContentType("application/vnd.ms-excel");
				response.setHeader("Content-Disposition","attachment; filename=" + recordProcessor.getExcelFileName());
				csvImportExportService.Export(FreqUsedMemberExcel, recordProcessor, response.getOutputStream());
            }
        	}catch(Exception e)
        	{
        		e.printStackTrace();
        	}
            
            }
            
           
            
            if(r1.equals("frequency"))
            {
                 strsql="SELECT member_code,member_name,dept_name,course_name,cyear,COUNT(*) AS total FROM counter_statistics_issue WHERE issue_date between'"+Security.TextDate_Insert(request.getParameter("fromdate"))+"' and '"+Security.TextDate_Insert(request.getParameter("todate"))+"'" ;	
                 if(!request.getParameter("dept").equals("ALL"))
                 {
                 	strsql= strsql +" and dept_code='" +chkcode.getParam( request, "dept")+"'";
                 }
                 if(!request.getParameter("group").equals("ALL"))
                 {
                 	strsql= strsql +" and group_code='" +chkcode.getParam( request, "group")+"'";
                 }
            if(!request.getParameter("year").equals("ALL"))
            {
            	strsql=strsql+" and cyear='" +request.getParameter("year")+"'";
            }
            
            strsql= strsql +" group by member_code,member_name,dept_name,course_name,cyear order by count(*) desc ";
            
            strsql=strsql+ " limit "+request.getParameter("recfrom")+","+request.getParameter("recto")+"";
            System.out.println(":::::Limit To::::"+request.getParameter("recto"));
            
            	
            	log4jLogger.info("Inside Frequent Used Member Report");	            	
     	      	try{
     	      	Map<String, Object> parameters = new HashMap<String, Object>();       	      	
     			String namedQuery=strsql;
     			StringBuffer sb = new StringBuffer();
     			     			
     			sb.append(namedQuery);
     				//sb.append(" " +strsql1 +strsql2);
     			log4jLogger.info("SQL QUERY: " + sb);	
     			System.out.println("::::::<<<<<<>>>>>>>>>"+printType);
     			   			        			
     			if(printType.equals("pdf"))
     			{
     			parameters.put("CMP_NAME",ReportQueryUtill.COMPANY_NAME);
     			parameters.put("CMP_ADD",ReportQueryUtill.COMPANY_ADD);
     			parameters.put("ReportTitle",ReportQueryUtill.Frequently_Member_Title);
     			parameters.put("fromdate",Security.getdate(frmdt).replace('-', '/'));
     			parameters.put("todate",Security.getdate(todt).replace('-', '/'));
     			parameters.put("All_Query",sb.toString());				
     				
     			
     			InputStream inputStream1 = getServletContext().getResourceAsStream("/Report/Frequently_Member.jasper");
     			JasperReport report = (JasperReport) JRLoader.loadObject(inputStream1);	
     					
     			JasperPrint jasperPrint  = JasperFillManager.fillReport(report, parameters,con);			
     						
     			JRAbstractExporter exporterPDF = new JRPdfExporter();
     	        exporterPDF.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
     	        exporterPDF.setParameter(JRExporterParameter.OUTPUT_STREAM, response.getOutputStream());
     	        response.setHeader("Content-Disposition", "attachment;filename="+ReportQueryUtill.Frequently_Pdf_Member_Title+".pdf");
     	        response.setContentType("application/pdf");
     	        exporterPDF.exportReport();            
                         	         	
     	      	}
     			else{
     				System.out.println("::::::::::Inside EXCEL:::::::::::");
     				List<?> prepareSearchCriteriaLst=null;
    				prepareSearchCriteriaLst = importExportXMLService.getFreqUsedMemberExcel(sb.toString(),request.getParameter("r1"));
    				System.out.println(prepareSearchCriteriaLst.size());
    				
    				Map<Object, Object> excelTitleMap = new HashMap<Object, Object>();
    				excelTitleMap.put("From_Accno",request.getParameter("fromdate"));
    				excelTitleMap.put("To_Accno",request.getParameter("todate"));
    				excelTitleMap.put("Doc", request.getParameter("txtdoctype"));
    				excelTitleMap.put("Type",request.getParameter("r1"));
    				Iterator<?> FreqUsedMemberExcel = prepareSearchCriteriaLst.iterator();
    				
    				FreqUsedMemberExcel recordProcessor = new FreqUsedMemberExcel(excelTitleMap);
    				response.setContentType("text/csv");
    				 //response.setContentType("application/vnd.ms-excel");
    				response.setHeader("Content-Disposition","attachment; filename=" + recordProcessor.getExcelFileName());
    				csvImportExportService.Export(FreqUsedMemberExcel, recordProcessor, response.getOutputStream());
     			}
     				
     			}
     			catch(Exception e)
     	      	{
     	      		e.printStackTrace();
     	      	}
            
            }
            
            	
            if(r1.equals("gate")){
            	
                strsql10="SELECT member_code,member_name,cyear,dept_name,COUNT(member_code) AS total,SUM(min_used) AS totalminute FROM return_log_view WHERE return_time BETWEEN'"+Security.TextDate_Insert(request.getParameter("fromdate"))+"' AND '"+Security.TextDate_Insert(request.getParameter("todate"))+"' " + strsql3 +  " ";
           
                if(!request.getParameter("dept").equals("ALL"))
                {
                	strsql10= strsql10 +" and dept_code='" +chkcode.getParam( request, "dept")+"'";
                }
                if(!request.getParameter("group").equals("ALL"))
                {
                	strsql10= strsql10 +" and group_code='" +chkcode.getParam( request, "group")+"'";
                }
                
                if(!request.getParameter("year").equals("ALL"))
                {
                	strsql10=strsql10+" and cyear='" +request.getParameter("year")+"'";
                }
                
                strsql10= strsql10 +" GROUP BY member_code ORDER BY SUM(min_used) DESC limit "+request.getParameter("recfrom")+","+request.getParameter("recto")+"";
                System.out.println(":::::Limit To::::"+request.getParameter("recto"));
               
                
                	log4jLogger.info("Best Used Award");	  
                
                	try
                	{
                	Map<String, Object> parameters = new HashMap<String, Object>();       	      	
         			String namedQuery=strsql10;
         			StringBuffer sb1 = new StringBuffer();
         			     			
         			sb1.append(namedQuery);
         			log4jLogger.info("SQL QUERY: " + sb1);	
         			System.out.println("::::::<<<<<<2>>>>>>>>>"+printType);
         			if(printType.equals("pdf"))
         			{
          			parameters.put("CMP_NAME",ReportQueryUtill.COMPANY_NAME);
         			parameters.put("CMP_ADD",ReportQueryUtill.COMPANY_ADD);
         			parameters.put("ReportTitle",ReportQueryUtill.Best_User);  
         			parameters.put("fromdate",Security.getdate(frmdt).replace('-', '/'));
         			parameters.put("todate",Security.getdate(todt).replace('-', '/'));
         			parameters.put("All_Query",sb1.toString());				
         				
         			
         			InputStream inputStream1 = getServletContext().getResourceAsStream("/Report/Best_User_Award.jasper");
         			JasperReport report = (JasperReport) JRLoader.loadObject(inputStream1);	
         					
         			JasperPrint jasperPrint  = JasperFillManager.fillReport(report, parameters,con);			
         						
         			JRAbstractExporter exporterPDF = new JRPdfExporter();
         	        exporterPDF.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
         	        exporterPDF.setParameter(JRExporterParameter.OUTPUT_STREAM, response.getOutputStream());
         	        response.setHeader("Content-Disposition", "attachment;filename="+ReportQueryUtill.Best_Used_Member+".pdf");
         	        response.setContentType("application/pdf");
         	        exporterPDF.exportReport();            
                	
                	}
         			else
         			{
         				System.out.println("::::::::::Inside EXCEL:::::::::::"+request.getParameter("r1"));
         				List<?> prepareSearchCriteriaLst=null;
        				prepareSearchCriteriaLst = importExportXMLService.getFreqUsedMemberExcel(sb1.toString(),request.getParameter("r1"));
        				
        				
        				Map<Object, Object> excelTitleMap = new HashMap<Object, Object>();
        				excelTitleMap.put("From_Accno",request.getParameter("fromdate"));
        				excelTitleMap.put("To_Accno",request.getParameter("todate"));
        				excelTitleMap.put("Doc", request.getParameter("txtdoctype"));
        				excelTitleMap.put("Type",request.getParameter("r1"));
        				Iterator<?> FreqUsedMemberExcel = prepareSearchCriteriaLst.iterator();
        				
        				FreqUsedMemberExcel recordProcessor = new FreqUsedMemberExcel(excelTitleMap);
        				response.setContentType("text/csv");
        				 //response.setContentType("application/vnd.ms-excel");
        				response.setHeader("Content-Disposition","attachment; filename=" + recordProcessor.getExcelFileName());
        				csvImportExportService.Export(FreqUsedMemberExcel, recordProcessor, response.getOutputStream());
         			}
                	}
         				catch(Exception e)
                	{
                		e.printStackTrace();
                	}
               
                }
			 
	}  catch (Exception e) {
		e.printStackTrace();
	}
catch (Throwable theException) {
	
	theException.printStackTrace();
	
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
