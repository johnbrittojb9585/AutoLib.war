package Lib.Auto.Unique_Report;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.sql.DriverManager;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import Common.DBConnection;
import Common.businessutil.BusinessServiceFactory;
import Common.businessutil.importexportexcel.ExceImportExportService;
import Common.businessutil.importexportxml.ImportExportXMLService;
import Common.businessutil.reports.ReportQueryUtill;
import Common.businessutil.reports.ReportService;
import Lib.Auto.Designation.DesignationBean;
import Lib.Auto.Department.DepartmentBean;
import Lib.Auto.Subject.subjectbean;
import Lib.Auto.Fine.Finebean;
import Lib.Auto.LibraryCollection.LibraryCollection;
import Lib.Auto.Member.MemberBean;
import Lib.Auto.Statistics.StatisticsDetailsExportRecord;
import Lib.Auto.Statistics.StatisticsWiseExportRecord;
import javax.servlet.annotation.WebServlet;
import com.google.gson.Gson;

@WebServlet("/Unique_Report/UniqueTitleServlet")

public class UniqueTitle extends HttpServlet implements Serializable {
	
	private static Logger log4jLogger = Logger.getLogger(UniqueTitle.class);	
	private static final long serialVersionUID = 8672487184590862910L;

	String sqlQuery="",fromValue="",toValue="";
	String term="";
	String available;
	java.sql.Connection con=null;
	java.sql.ResultSet rs=null;
	java.sql.PreparedStatement pstmt=null; 
		
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		performTask(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		performTask(request, response);
	}
	public void performTask(
		HttpServletRequest request,
		HttpServletResponse response) {
		try {
			HttpSession session = request.getSession(true);
			String res = Security.checkSecurity(1, session, response, request);
			if (res.equalsIgnoreCase("Failure")) {
				response.sendRedirect("/AutoLib/Tree/sessiontimeout.jsp");
				return;
			}
			
			String frmaccno = null, toaccno = null, frmdt = null, todt = null, flag = null,  printType = null;
			String indexPage ="";
			String firstStr = null,dept=null,subject=null;
			int firstNum = 0, secondNum = 0, firstStrCount = 0;
			
			
			ImportExportXMLService importExportXMLService = BusinessServiceFactory.INSTANCE.getImportExportXMLService();
			ExceImportExportService csvImportExportService = BusinessServiceFactory.INSTANCE.getExceImportExportService();
		
			
			 ReportService rs=BusinessServiceFactory.INSTANCE.getReportService();
			
			 con = SessionHibernateUtil.getSession().connection();
			 
			 response.setContentType("application/json");
			 
			 Object obj = request.getParameter("flag");
				
				try{
					String term = request.getParameter("txtdepartment");
					if(!term.equalsIgnoreCase(null) && !term.equalsIgnoreCase("") && obj==null)
		            {
					//System.out.println("Data from ajax call " + term);
								    
					   ArrayList<DepartmentBean> list = rs.getUniqueTitleReportDeptAutoSearch(term);
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
					String term = request.getParameter("txtsubject");
					
					if(!term.equalsIgnoreCase(null) && !term.equalsIgnoreCase("") && obj==null)
		             {
					//System.out.println("Data from ajax call " + term);
								    
					   ArrayList<subjectbean> list = rs.getUniqueTitleReportSubjectAutoSearch(term);
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


				flag = request.getParameter("flag");
				printType = request.getParameter("printType");

			
				Map<String, Object> parameters = new HashMap<String, Object>();
				
				String namedQuery = ReportQueryUtill.UniqueTitle_Query;
				//group by title,author_name order by Title
			
				
				
				StringBuffer sb = new StringBuffer();
				
				String filterQuery = "";
				
				dept=request.getParameter("txtdepartment");
				subject=request.getParameter("txtsubject");
				
				
				if (flag.equals("AccessNo_Wise")) {

					frmaccno = request.getParameter("From_Accno");
					toaccno = request.getParameter("To_Accno");
					
					
					
					if (Security.IsNumeric(frmaccno) && Security.IsNumeric(toaccno)) {
					
						
						log4jLogger.info("inside Accession number wise numbers only");
						
						firstNum = Integer.parseInt(request.getParameter("firstNum"));
						secondNum = Integer.parseInt(request.getParameter("secondNum"));

						firstStr = request.getParameter("firstStr");
						firstStrCount = firstStr.length() + 1;
						//secondStr = request.getParameter("secondStr");
						frmaccno = request.getParameter("From_Accno");
						toaccno = request.getParameter("To_Accno");
						
						log4jLogger.info("FROM ACCESS NO"+frmaccno);
						
						log4jLogger.info("TO ACCESS NO"+toaccno);
						
						
						if(!request.getParameter("txtsubject").equalsIgnoreCase("ALL"))
							filterQuery=filterQuery+" and sub_name= '"+request.getParameter("txtsubject")+"'";
						
						
						if(!request.getParameter("txtdepartment").equalsIgnoreCase("ALL"))
							filterQuery=filterQuery+" and dept_name= '"+request.getParameter("txtdepartment")+"'";
						
						
						
						
						
						if (!request.getParameter("Type").equalsIgnoreCase("ALL"))
							filterQuery = filterQuery+" and Document='"+request.getParameter("Type")+"'";

						if (!request.getParameter("avail").equalsIgnoreCase("ALL"))
							filterQuery = filterQuery + " and Availability='"+request.getParameter("avail")+"'";

						filterQuery = filterQuery+ " and  access_no REGEXP '^[0-9]+$' and CONVERT(access_no,SIGNED) between '"+ frmaccno + "' and '" + toaccno + "'";

					} else {
						
						firstNum = Integer.parseInt(request.getParameter("firstNum"));
						secondNum = Integer.parseInt(request.getParameter("secondNum"));

						firstStr = request.getParameter("firstStr");
						firstStrCount = firstStr.length() + 1;
						//secondStr = request.getParameter("secondStr");
						frmaccno = request.getParameter("From_Accno");
						toaccno = request.getParameter("To_Accno");

						
						log4jLogger.info("inside Accession number Alpha numeric values");

						if(!request.getParameter("txtsubject").equalsIgnoreCase("ALL"))
							filterQuery=filterQuery+" and sub_name= '"+request.getParameter("txtsubject")+"'";
						
						
						if(!request.getParameter("txtdepartment").equalsIgnoreCase("ALL"))
							filterQuery=filterQuery+" and dept_name= '"+request.getParameter("txtdepartment")+"'";
						
						
						
						if (!request.getParameter("Type").equalsIgnoreCase("ALL"))
							filterQuery = filterQuery+" and Document='"+request.getParameter("Type")+"'";

						if (!request.getParameter("avail").equalsIgnoreCase("ALL"))
							filterQuery = filterQuery + " and Availability='"+request.getParameter("avail")+"'";

						filterQuery = filterQuery+" and NOT access_no REGEXP '[^0-9]+$' And Access_No Like '"+firstStr+"%' And CAST(SUBSTRING(Access_No,"+firstStrCount+") AS SIGNED) BETWEEN '"+firstNum+ "' AND '" + secondNum+ "'";

					}

					sb.append(namedQuery);
					sb.append(" " + filterQuery);
				
					
					
				}
				
				else if (flag.equals("Date_Wise")) {

					frmdt = Security.TextDate_member(request.getParameter("fromdt"));
				    todt = Security.TextDate_member(request.getParameter("todt"));

					
					
					
					if(!request.getParameter("txtsubject").equalsIgnoreCase("ALL"))
						filterQuery=filterQuery+" and sub_name= '"+request.getParameter("txtsubject")+"'";
					
					if(!request.getParameter("txtdepartment").equalsIgnoreCase("ALL"))
						filterQuery=filterQuery+" and dept_name= '"+request.getParameter("txtdepartment")+"'";
					
					if (!request.getParameter("Type").equalsIgnoreCase("ALL"))
						filterQuery = filterQuery + " and Document='"
								+ request.getParameter("Type") + "'";

					if (!request.getParameter("avail").equalsIgnoreCase("ALL"))
						filterQuery = filterQuery + " and Availability='"
								+ request.getParameter("avail") + "'";

					filterQuery = filterQuery + " and received_date between '"
							+ frmdt + "' and '" + todt + "'";
					
					
					log4jLogger.info("Datawise filterQuery   : "+filterQuery);

					sb.append(namedQuery);
					sb.append(" " + filterQuery);
					log4jLogger.info("SQL Query DateWise :   " + sb.toString()+" group by title,author_name order by Title");


				}
				
				
				if (printType.equalsIgnoreCase("pdf")) 
				{
				
					
					log4jLogger.info("--------------Inside Report Printing Process----------------");
					
					
					log4jLogger.info("namedQuery: " + sb);

					boolean checkData = rs.getCheckData(sb.toString()+" group by title,author_name order by Title");
					 
					if (checkData)
						{
						 //no data
							log4jLogger.info("----------------NO DATA FOUND-------------------");
						  indexPage = "/Unique_Report/index.jsp?check=NoData";
						  dispatch(request, response, indexPage);
						 
						}
					 
					 else{
						 
						
						
						String StrSql=" GROUP BY title,author_name";
						
						sb.append(filterQuery+StrSql);
					
						 log4jLogger.info("---Final Report Query---------   : "+sb.toString());
						
					parameters.put("CMP_NAME", ReportQueryUtill.COMPANY_NAME);
					parameters.put("CMP_ADD", ReportQueryUtill.COMPANY_ADD);
					parameters.put("ReportTitle", ReportQueryUtill.Unique_Title);
					if(flag.equals("Date_Wise"))
					{
					parameters.put("fromdt", Security.getdate(frmdt).replace('-','/'));
					parameters.put("todt", Security.getdate(todt).replace('-','/'));
					}
					else
					{
						parameters.put("fromdt", frmaccno);
						parameters.put("todt", toaccno);
					}
					parameters.put("All_Query", sb.toString());
				
					InputStream inputStream1 = getServletContext().getResourceAsStream("/Report/Unique_Title.jasper");
					JasperReport report = (JasperReport) JRLoader.loadObject(inputStream1);	
					
					JasperPrint jasperPrint  = JasperFillManager.fillReport(report, parameters,con);			
					
					JRAbstractExporter exporterPDF = new JRPdfExporter();
				    exporterPDF.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
				    exporterPDF.setParameter(JRExporterParameter.OUTPUT_STREAM, response.getOutputStream());
				    response.setHeader("Content-Disposition", "attachment;filename="+ReportQueryUtill.Unique_Title+".pdf");
				    response.setContentType("application/pdf");
				    exporterPDF.exportReport(); 
					 }

				
					
				}

				
				if (printType.equalsIgnoreCase("excel")) {
				
					
					log4jLogger.info("--------------Inside excelReport Printing Process----------------");
					
					
					log4jLogger.info("namedQuery: " + sb);

					boolean checkData = rs.getCheckData(sb.toString()+" group by title,author_name order by Title");
					 
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
						prepareSearchCriteriaLst = importExportXMLService.getTitleWiseReportList(sb.toString()+" group by title,author_name order by Title");
						
						
						Map<Object, Object> excelTitleMap = new HashMap<Object, Object>();
						excelTitleMap.put("From_Accno",request.getParameter("From_Accno"));
						excelTitleMap.put("To_Accno",request.getParameter("To_Accno"));
						excelTitleMap.put("Type", request.getParameter("Type"));
						excelTitleMap.put("avail",request.getParameter("avail"));
						Iterator<?> Uniquetitle = prepareSearchCriteriaLst.iterator();
						
						UniqueexcelReport recordProcessor = new UniqueexcelReport(excelTitleMap);
						response.setContentType("text/csv");
						 //response.setContentType("application/vnd.ms-excel");
						response.setHeader("Content-Disposition","attachment; filename=" + recordProcessor.getExcelFileName());
						csvImportExportService.Export(Uniquetitle, recordProcessor, response.getOutputStream());
						
					}
				
}		
			
			
		}  catch (Exception e) {

					}
					finally{
					
						try{
						if(rs!=null){
							rs.close();
							rs=null;
						}
						
						if(pstmt!=null){
							pstmt.close();
							pstmt=null;
						}
						if (con != null) {
							con.close();
						}

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
