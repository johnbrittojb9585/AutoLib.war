package Lib.Auto.MemberReport;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
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
import Lib.Auto.Department.DepartmentBean;
import Lib.Auto.Group.GroupBean;
import Lib.Auto.LibraryCollection.LibraryCollectionExcel;
import Lib.Auto.MemberTransfer.MemberTransRefBean;
import Lib.Auto.OrdInvProcessing.orderbean;
import Lib.Auto.PubSup.PubSupBean;
import Lib.Auto.Subject.subjectbean;
import Lib.Auto.Member.MemberBean;
import Lib.Auto.Department.DepartmentBean;
import Lib.Auto.Course.CourseBean;
import Lib.Auto.Designation.DesignationBean;
import Common.businessutil.BusinessServiceFactory;
import Common.businessutil.importexportexcel.ExceImportExportService;
import Common.businessutil.importexportxml.ImportExportXMLService;
import Common.businessutil.reports.ReportQueryUtill;
import Common.businessutil.reports.ReportService;

import javax.servlet.annotation.WebServlet;
import com.google.gson.Gson;

@WebServlet("/MemberReport/MemberReportServlet")

public class MemberReport extends HttpServlet implements Serializable, COUNTER_QUERY {
	private static final long serialVersionUID = 1L;
	java.sql.Connection con = null;
	java.sql.PreparedStatement Prest = null;
	java.sql.ResultSet rs = null;
	java.sql.ResultSet rs1 = null;
	
	DataSource datasource;

	String term="";
	String flag = "",flag1="", order1="",order2="",order3="",order="",sorting="",printType="";
	
	String indexPage = null;
	
	orderbean orderObject=new orderbean();
	
	//ResourceBundle rb = ResourceBundle.getBundle("LocalStrings");
	
	private static Logger log4jLogger = Logger.getLogger(MemberReport.class);
	
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
			

			ReportService ss1 = BusinessServiceFactory.INSTANCE.getReportService();
			
			ReportService rs = BusinessServiceFactory.INSTANCE.getReportService();
			
			response.setContentType("application/json");
			Object obj= request.getParameter("flag");
			
			try{
				String term = request.getParameter("name");
				if(!term.equalsIgnoreCase(null) && !term.equalsIgnoreCase("") && obj== null)
	            {
				//System.out.println("Data from ajax call " + term);
							    
				   ArrayList<MemberBean> list = ss1.getMemberReportUserAutoSearch(term);
			       for(MemberBean user: list){
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
				String term = request.getParameter("desig");
				
				if(!term.equalsIgnoreCase(null) && !term.equalsIgnoreCase("") && obj== null)
	             {
				//System.out.println("Data from ajax call " + term);
							    
				   ArrayList<DesignationBean> list = ss1.getMemberReportDesigAutoSearch(term);
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
			
			if(!term.equalsIgnoreCase(null) && !term.equalsIgnoreCase("") && obj== null)
             {
			//System.out.println("Data from ajax call " + term);
						    
			   ArrayList<GroupBean> list = ss1.getMemberReportGroupAutoSearch(term);
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
			
			if(!term.equalsIgnoreCase(null) && !term.equalsIgnoreCase("") && obj== null)
             {
			//System.out.println("Data from ajax call " + term);
						    
			   ArrayList<CourseBean> list = ss1.getMemberReportCourseAutoSearch(term);
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
			
			if(!term.equalsIgnoreCase(null) && !term.equalsIgnoreCase("") && obj== null)
             {
			//System.out.println("Data from ajax call " + term);
						    
			   ArrayList<DepartmentBean> list = ss1.getMemberReportDeptAutoSearch(term);
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
		
			
			String namedQuery = ReportQueryUtill.Query_Member_Report;
			
			StringBuffer sb = new StringBuffer();
			String filterQuery = "";
			
			flag = request.getParameter("flag");
			flag1=request.getParameter("flag1");
			printType = request.getParameter("printType");
//			Object obj = flag ;
			if(obj==null)
			{
				flag = "";
				flag1 = "";
				printType = "";
			}
			log4jLogger.info("--flag values------"+flag);
			log4jLogger.info("--flag1 values------"+flag1);
			
			ImportExportXMLService importExportXMLService = BusinessServiceFactory.INSTANCE.getImportExportXMLService();
			ExceImportExportService csvImportExportService = BusinessServiceFactory.INSTANCE.getExceImportExportService();
			if(flag.equals("load"))
			{
					
				MemberTransRefBean beanObject = new MemberTransRefBean();
				
				List DesigList = ss1.getDesigList(beanObject);				
				request.setAttribute("DesigSearchList", DesigList);
				
				List GroupList = ss1.getGroupList(beanObject);
				request.setAttribute("GroupSearchList", GroupList);
				
				List DeptList = ss1.getDepartmentList(beanObject);
				request.setAttribute("DeptSearchList", DeptList);
				
				List CourseList = ss1.getCourseList(beanObject);
				request.setAttribute("CourseSearchList", CourseList);
				
				indexPage = "/MemberReport/index.jsp";	
				dispatch(request, response, indexPage);
			}
			
			if(flag.equals("print"))
			{
				
				log4jLogger.info("----------------Inside Printing---------------");
				
				if (!request.getParameter("name").equalsIgnoreCase("ALL") && !request.getParameter("name").isEmpty())
				
					filterQuery = filterQuery+" and member_name='"+request.getParameter("name")+"'";
				
				if (!request.getParameter("desig").equalsIgnoreCase("ALL") && !request.getParameter("desig").isEmpty())
					
					filterQuery = filterQuery+" and desig_name='"+request.getParameter("desig")+"'";
				
				if (!request.getParameter("dept").equalsIgnoreCase("ALL") && !request.getParameter("dept").isEmpty())
					
					filterQuery = filterQuery+" and dept_name='"+request.getParameter("dept")+"'";
				
				if (!request.getParameter("group").equalsIgnoreCase("ALL") && !request.getParameter("group").isEmpty())
					
					filterQuery = filterQuery+" and group_name='"+request.getParameter("group")+"'";
				
				if (!request.getParameter("course").equalsIgnoreCase("ALL") && !request.getParameter("course").isEmpty())
					
					filterQuery = filterQuery+" and course_code='"+request.getParameter("course")+"'";
				
				if (!request.getParameter("year").equalsIgnoreCase("ALL") && !request.getParameter("year").isEmpty())
					
					filterQuery = filterQuery+" and cyear='"+request.getParameter("year")+"'";
				
				if (!request.getParameter("gender").equalsIgnoreCase("ALL") && !request.getParameter("gender").isEmpty())
					
					filterQuery = filterQuery+" and sex='"+request.getParameter("gender")+"'";
	
				if (!request.getParameter("status").equalsIgnoreCase("ALL") && !request.getParameter("status").isEmpty()){
					
					if(request.getParameter("status").equals("ACTIVE"))
						
						filterQuery = filterQuery+ " and Expiry_Date>='" + Security.Counter_DateText()+ "'";
					else{
						filterQuery = filterQuery+ " and Expiry_Date<='" + Security.Counter_DateText()+ "'";
					}
				}
					
				if (!request.getParameter("lock").equalsIgnoreCase("ALL") && !request.getParameter("lock").isEmpty())
					
					filterQuery = filterQuery+" and slock='"+request.getParameter("lock")+"'";
				
			
				order1 =request.getParameter("order1");
				order2 =request.getParameter("order2");
				order3 =request.getParameter("order3");
				sorting=request.getParameter("sorting");
				
				order="";
				if (!order1.equals("NO" )) order = order+order1;
				if (!order2.equals("NO" )) order = order+", "+order2;
				if (!order3.equals("NO"))  order = order+", "+order3;
				if (!sorting.equals("Asc")) order=order+" DESC";
				if (!sorting.equals("Desc"))order=order+" ASC";
				
				sb.append(namedQuery);
				sb.append(" " + filterQuery+"ORDER BY "+ order);
			
				boolean checkData = rs.getCheckData(sb.toString());// for check no data
				
				if (checkData)
				{
					
					log4jLogger.info("-------All_Query-------Values"+sb.toString());
					
					log4jLogger.info("----------------NO DATA FOUND-------------------");
					  indexPage = "/MemberReport/index.jsp?check=NoData";
					  dispatch(request, response, indexPage);
				}
				
				else
				{// data is available
					
					if(flag1.equals("Details"))
					{	//Active user Details
						if(printType.equalsIgnoreCase("pdf"))
						{
						con = SessionHibernateUtil.getSession().connection();		
						InputStream inputStream1 = null;
						Map<String, Object> parameters = new HashMap<String, Object>();
					
						parameters.put("CMP_NAME", ReportQueryUtill.COMPANY_NAME);
						parameters.put("CMP_ADD",ReportQueryUtill.COMPANY_ADD);
						parameters.put("All_Query", sb.toString());
						log4jLogger.info("-------All_Query-------Values"+sb.toString());
						if (request.getParameter("status").equalsIgnoreCase("IN ACTIVE")) 
						{
				
							log4jLogger.info("---------------inside InActive Details Report-----------");
							inputStream1 = getServletContext().getResourceAsStream("/Report/Member_Report_Inactive.jasper");
							parameters.put("ReportTitle", ReportQueryUtill.Counter_MemberReports_Inactive);
							response.setHeader("Content-Disposition","attachment;filename="	+ ReportQueryUtill.Counter_MemberReports_Inactive + ".pdf");

						}
						else
						{
												
						//In Active user Details
						
							log4jLogger.info("---------------inside Active Details Report-----------");
						
							parameters.put("ReportTitle", ReportQueryUtill.Counter_MemberReports_Title);
							inputStream1 = getServletContext().getResourceAsStream("/Report/Member_Report.jasper");
							response.setHeader("Content-Disposition","attachment;filename="	+ ReportQueryUtill.Counter_MemberReports_Title + ".pdf");
						}
						JasperReport report = (JasperReport) JRLoader.loadObject(inputStream1);
						JasperPrint jasperPrint = JasperFillManager.fillReport(report, parameters, con);
						JRAbstractExporter exporterPDF = new JRPdfExporter();
						exporterPDF.setParameter(JRExporterParameter.JASPER_PRINT,jasperPrint);
						exporterPDF.setParameter(JRExporterParameter.OUTPUT_STREAM,response.getOutputStream());
						response.setContentType("application/pdf");
						exporterPDF.exportReport();
						}
						if(printType.equalsIgnoreCase("excel"))
						{
							List<?> prepareSearchCriteriaLst=null;
							prepareSearchCriteriaLst = importExportXMLService.getMemberExcelReport(sb.toString());
							Map<Object, Object> excelTitleMap = new HashMap<Object, Object>();
							
							Iterator<?> Member = prepareSearchCriteriaLst.iterator();
							MemberExcelReport recordProcessor = new MemberExcelReport();
							response.setContentType("text/csv");
							 //response.setContentType("application/vnd.ms-excel");
							response.setHeader("Content-Disposition","attachment; filename=" + recordProcessor.getExcelFileName());
							csvImportExportService.Export(Member, recordProcessor, response.getOutputStream());
						}
					}
					
				}
			}
			
			
	          
    			
    			 
		} catch (Exception e) {
			e.printStackTrace();

		} catch (Throwable theException) {
			theException.printStackTrace();
		} finally {
			
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}
	
	public void dispatch(HttpServletRequest request,
			HttpServletResponse response,String indexPage)
			throws ServletException, IOException {
		 	RequestDispatcher dispatch = request.getRequestDispatcher(indexPage);
			dispatch.forward(request, response);
		}

}
