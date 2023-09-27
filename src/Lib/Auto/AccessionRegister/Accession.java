package Lib.Auto.AccessionRegister;

import java.io.IOException;
import java.io.InputStream;

import org.apache.log4j.Logger;

import com.ibm.icu.text.DecimalFormat;
import com.library.autolib.portal.dbconnectionutil.SessionHibernateUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import Common.Security;
import Common.businessutil.BusinessServiceFactory;
import Common.businessutil.importexportexcel.ExceImportExportService;
import Common.businessutil.importexportxml.ImportExportXMLService;
import Common.businessutil.reports.ReportQueryUtill;
import Common.businessutil.reports.ReportService;

import java.sql.*;

import net.sf.jasperreports.engine.JRAbstractExporter;

public class Accession extends HttpServlet implements ReportQueryUtill {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private static Logger log4jLogger = Logger.getLogger(Accession.class);

	java.sql.Connection con = null;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		performTask(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		performTask(request, response);

	}

	public void performTask(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		try {
			HttpSession session = request.getSession(true);
			String res = Security.checkSecurity(1, session, response, request);
			if (res.equalsIgnoreCase("Failure")) {
				response.sendRedirect("/AutoLib/Tree/sessiontimeout.jsp");
				return;
			}
			 //boolean checkData;
			String frmaccno = null, toaccno = null, frmdt = null, todt = null, flag = null, doc_type = null, flag1 = null;
			String indexPage ="";
			String firstStr = null, secondStr = null;
			int firstNum = 0, secondNum = 0, firstStrCount = 0;
			accessbean ob=new accessbean();
			ImportExportXMLService importExportXMLService = BusinessServiceFactory.INSTANCE
					.getImportExportXMLService();
			ExceImportExportService csvImportExportService = BusinessServiceFactory.INSTANCE
					.getExceImportExportService();
			
			
			 ReportService rs=BusinessServiceFactory.INSTANCE.getReportService();
			 
			 
			con = SessionHibernateUtil.getSession().connection();

			flag = request.getParameter("flag");
			flag1 = request.getParameter("flagExcel");

			
			Map<String, Object> parameters = new HashMap<String, Object>();

			String namedQuery = ReportQueryUtill.AccessionQuery_Acc_no;
			String BVnamedQuery = "select * from accession_reg where 2>1 and document='Back Volume'";
			
			String MissingnamedQuery=ReportQueryUtill.Miss_Accessno;

			StringBuffer sb = new StringBuffer();
			StringBuffer sbv = new StringBuffer();
			
			
			String filterQuery = "";
			frmaccno=request.getParameter("From_Accno").trim();
			toaccno=request.getParameter("To_Accno").trim();	
			doc_type=request.getParameter("Type");
			String purchase=request.getParameter("purchaseType");
		
			if (flag.equals("AccessNo_Wise")) {

				
				if (Security.IsNumeric(frmaccno) && Security.IsNumeric(toaccno)) {
				
					
					log4jLogger.info("inside Accession number wise numbers only");
					
					firstNum = Integer.parseInt(request.getParameter("firstNum"));
					secondNum = Integer.parseInt(request.getParameter("secondNum"));

					firstStr = request.getParameter("firstStr");
					firstStrCount = firstStr.length() + 1;
					secondStr = request.getParameter("secondStr");
					frmaccno = request.getParameter("From_Accno");
					toaccno = request.getParameter("To_Accno");
					
					log4jLogger.info("FROM ACCESS NO"+frmaccno);
					
					log4jLogger.info("TO ACCESS NO"+toaccno);
					
					if (!request.getParameter("Type").equalsIgnoreCase("ALL"))
						filterQuery = filterQuery+" and Document='"+request.getParameter("Type")+"'";
					if (!request.getParameter("year_pub").equals("") && !request.getParameter("year_pub").equals(null)&& !request.getParameter("year_pub").isEmpty())
						filterQuery = filterQuery+" and year_pub='"+request.getParameter("year_pub")+"'";


					if (!request.getParameter("avail").equalsIgnoreCase("ALL"))
						filterQuery = filterQuery + " and Availability='"+request.getParameter("avail")+"'";
					if (!request.getParameter("purchaseType").equalsIgnoreCase("ALL"))
						filterQuery = filterQuery + " and Gift_Purchase='"+request.getParameter("purchaseType")+"'";
					filterQuery = filterQuery+ " and  access_no REGEXP '^[0-9]+$' and CONVERT(access_no,SIGNED) between '"+ frmaccno + "' and '" + toaccno+ "' order by CONVERT(access_no,SIGNED) ASC";

				} else {
					
					firstNum = Integer.parseInt(request.getParameter("firstNum"));
					secondNum = Integer.parseInt(request.getParameter("secondNum"));

					firstStr = request.getParameter("firstStr");
					firstStrCount = firstStr.length() + 1;
					secondStr = request.getParameter("secondStr");
					frmaccno = request.getParameter("From_Accno");
					toaccno = request.getParameter("To_Accno");

					
					log4jLogger.info("inside Accession number Alpha numeric values");

					if (!request.getParameter("Type").equalsIgnoreCase("ALL"))
						filterQuery = filterQuery+" and Document='"+request.getParameter("Type")+"'";
					if (!request.getParameter("year_pub").equals("") && !request.getParameter("year_pub").equals(null) && !request.getParameter("year_pub").isEmpty())
						filterQuery = filterQuery+" and year_pub='"+request.getParameter("year_pub")+"'";

					if (!request.getParameter("avail").equalsIgnoreCase("ALL"))
						filterQuery = filterQuery + " and Availability='"+request.getParameter("avail")+"'";
					if (!request.getParameter("purchaseType").equalsIgnoreCase("ALL"))
						filterQuery = filterQuery + " and Gift_Purchase='"+request.getParameter("purchaseType")+"'";
					filterQuery = filterQuery+" and NOT access_no REGEXP '[^0-9]+$' And Access_No Like '"+firstStr+"%' And CAST(SUBSTRING(Access_No,"+firstStrCount+") AS SIGNED) BETWEEN '"+firstNum+ "' AND '" + secondNum+ "' ORDER BY LPAD(ACCESS_NO,20,'0')";

				}

				sb.append(namedQuery);
				sb.append(" " + filterQuery);
			
				log4jLogger.info("Query Value    :  "+sb.toString());
				
				sbv.append(BVnamedQuery);
				sbv.append(" " + filterQuery);
				
				
				
			}

			else if (flag.equals("Date_Wise")) {
if(!request.getParameter("fromdt").equalsIgnoreCase("") && !request.getParameter("todt").equalsIgnoreCase(""));
{
				frmdt = Security
						.TextDate_member(request.getParameter("fromdt"));
				todt = Security.TextDate_member(request.getParameter("todt"));
}
				if (!request.getParameter("Type").equalsIgnoreCase("ALL"))
					filterQuery = filterQuery + " and Document='"
							+ request.getParameter("Type") + "'";
				if (!request.getParameter("year_pub").equalsIgnoreCase("") && !request.getParameter("year_pub").equals(null) && !request.getParameter("year_pub").isEmpty())
					filterQuery = filterQuery+" and year_pub='"+request.getParameter("year_pub")+"'";

				if (!request.getParameter("avail").equalsIgnoreCase("ALL"))
					filterQuery = filterQuery + " and Availability='"
							+ request.getParameter("avail") + "'";
				if (!request.getParameter("purchaseType").equalsIgnoreCase("ALL"))
					filterQuery = filterQuery + " and Gift_Purchase='"+request.getParameter("purchaseType")+"'";

				filterQuery = filterQuery + " and received_date between '"
						+ frmdt + "' and '" + todt + "'";

				sb.append(namedQuery);
				sb.append(" " + filterQuery);
		//		log4jLogger.info("SQL Query 22222222222222222222222222222222 " + sb.toString());

				sbv.append(BVnamedQuery);
				sbv.append(" " + filterQuery);
				
				

			}
			
			else if(flag.equals("Missing_AccessNo_Wise")){				//Gopal 04-08-2015
				
				log4jLogger.info(":::::::::inside missing accessNo::::::::::");
					rs.getDeleteMisNo();//delete missing accessNo table
					
					
					int i,k,x,y;
					
					
					int status=0,st=0;
					String c=null,d=null,e=null,f=null,g=null,h="0";
					for(i=0;i<frmaccno.length();i++)
					{
						if(Character.isLetter(frmaccno.charAt(i))||Character.isWhitespace(frmaccno.charAt(i)))
						{
							
							k=i;
							c=frmaccno.substring(0, k+1);
							d=frmaccno.substring(k+1);
							status=1;
						}
					}
					for(i=0;i<toaccno.length();i++)
					{
						if(Character.isLetter(toaccno.charAt(i))||Character.isWhitespace(toaccno.charAt(i)))
						{
							st=1;
							k=i;
							e=toaccno.substring(0, k+1);
							f=toaccno.substring(k+1);
						}
					}
					if(status==1&&st==1)
					{
					x=Integer.parseInt(d);
					y=Integer.parseInt(f);
					for(i=1;i<d.length();i++)
					{
						h=h+"0";
					}
					DecimalFormat df=new DecimalFormat(h);
					for(i=x;i<=y;i++)
					{
						String str=df.format(i);
						g=c+str;
						ob =new accessbean();
						ob.setAccessno(g);
						ob.setDoctype(doc_type);
						int accessno=rs.getAccessNo(ob);
						if (accessno==0)
						{
							ob =new accessbean();
							ob.setAccessno(g);
							rs.getAccessNoSave(ob);	//save missing accessNo table
						}
					}
					}
					
					if(status==0&&st==0&&!frmaccno.isEmpty()&&!toaccno.isEmpty())
					{
						log4jLogger.info("missing number");
						int l=Integer.parseInt(frmaccno);
						int m=Integer.parseInt(toaccno);
						
						for(i=1;i<frmaccno.length();i++)
						{
							h=h+"0";
						}
						DecimalFormat df=new DecimalFormat(h);
							for(i=l;i<=m;i++)
							{
								String s=df.format(i);
								ob =new accessbean();
								ob.setAccessno(s);
								ob.setDoctype(doc_type);
								int accessno=rs.getAccessNo(ob);
								if (accessno==0)
								{
									ob =new accessbean();
									ob.setAccessno(s);
									rs.getAccessNoSave(ob);	//save missing accessNo table
								}
								
							}
							
					}
					//sb.append(MissingnamedQuery);
			}
			
			
			else if(flag.equals("RndNumber")){
				//Str.replace
				//String Temp=request.getParameter("rndNumber");
				//String Temp1=Temp.replace(",","','");
				
				//filterQuery = filterQuery + "and access_no in('"+request.getParameter("rndNumber")+"')";
				filterQuery = filterQuery + "and access_no in('"+request.getParameter("randomNumberList")+"')";
				sb.append(namedQuery);
				System.out.println(filterQuery);
				
				sb.append(" " + filterQuery);
	//			log4jLogger.info("Random Query Value::::::::::::::: " + sb.toString());
				
				sbv.append(namedQuery);
				sbv.append(" " + filterQuery);
				
				
				
				
			}
			
			

			if (flag1.equalsIgnoreCase("PdfReport")) {
				boolean checkData = rs.getCheckData(sb.toString());
				  if (checkData)
					{
					 //no data
						log4jLogger.info("----------------NO DATA FOUND-------------------");
					  indexPage = "/AccessionRegister/index.jsp?check=NoData";
					  dispatch(request, response, indexPage);
					 
					}
				 
				 else{
					 
					 if(request.getParameter("Type").equalsIgnoreCase("Back Volume"))
					 {
						 System.out.println(":::::::::Back Volume Report:::::::::::");
							parameters.put("CMP_NAME", ReportQueryUtill.COMPANY_NAME);
							parameters.put("CMP_ADD", ReportQueryUtill.COMPANY_ADD);
							parameters.put("ReportTitle", "Back Volume Accession Register");
							parameters.put("All_Query", sbv.toString());
							log4jLogger.info("namedQuery: " + sbv);

							InputStream inputStream1 = getServletContext()
									.getResourceAsStream("/Report/BackVolume_Accession_Report.jasper");
							JasperReport report = (JasperReport) JRLoader
									.loadObject(inputStream1);

							JasperPrint jasperPrint = JasperFillManager.fillReport(report,
									parameters, con);
							JRAbstractExporter exporterPDF = new JRPdfExporter();
							exporterPDF.setParameter(JRExporterParameter.JASPER_PRINT,
									jasperPrint);
							exporterPDF.setParameter(JRExporterParameter.OUTPUT_STREAM,
									response.getOutputStream());
							response.setHeader("Content-Disposition",
									"attachment;filename="
											+ ReportQueryUtill.Accession_Title + ".pdf");
							response.setContentType("application/pdf");
							exporterPDF.exportReport();
					 }else
					 {
						 System.out.println(":::::::::Allllllll Report:::::::::::");
							parameters.put("CMP_NAME", ReportQueryUtill.COMPANY_NAME);
							parameters.put("CMP_ADD", ReportQueryUtill.COMPANY_ADD);
							parameters.put("ReportTitle", ReportQueryUtill.Accession_Title);
							parameters.put("All_Query", sb.toString());
							if(flag.equals("Date_Wise")){
								parameters.put("fromdt", Security.getdate(frmdt).replace('-', '/'));
								parameters.put("todt", Security.getdate(todt).replace('-', '/'));
							}
							else if(flag.equals("AccessNo_Wise")){
								parameters.put("fromdt",request.getParameter("From_Accno"));
								parameters.put("todt",request.getParameter("To_Accno"));							
							}
							else {
								parameters.put("fromdt", "");
								parameters.put("todt", "");
							}
							log4jLogger.info("namedQuery: " + sb);

							InputStream inputStream1 = getServletContext()
									.getResourceAsStream("/Report/Accession_Report.jasper");
							JasperReport report = (JasperReport) JRLoader
									.loadObject(inputStream1);

							JasperPrint jasperPrint = JasperFillManager.fillReport(report,
									parameters, con);
							JRAbstractExporter exporterPDF = new JRPdfExporter();
							exporterPDF.setParameter(JRExporterParameter.JASPER_PRINT,
									jasperPrint);
							exporterPDF.setParameter(JRExporterParameter.OUTPUT_STREAM,
									response.getOutputStream());
							response.setHeader("Content-Disposition",
									"attachment;filename="
											+ ReportQueryUtill.Accession_Title + ".pdf");
							response.setContentType("application/pdf");
							exporterPDF.exportReport();
					 }
				 }

			} else if (flag1.equalsIgnoreCase("ExcelReport")){

				// For Excel Report.
				boolean checkData = rs.getCheckData(sb.toString());
				  if (checkData)
					{
					 //no data
						log4jLogger.info("----------------NO DATA FOUND-------------------");
					  indexPage = "/AccessionRegister/index.jsp?check=NoData";
					  dispatch(request, response, indexPage);
					 
					}else{
						
					
				
				List prepareSearchCriteriaLst = importExportXMLService.getAccessionWiseReportList(sb.toString());

				Map<Object, Object> excelTitleMap = new HashMap<Object, Object>();

				if (flag.equals("AccessNo_Wise")) {
					excelTitleMap.put("fromAccNo", frmaccno);
					excelTitleMap.put("toAccNo", toaccno);
					excelTitleMap.put("documentType", doc_type);

				} else {
					excelTitleMap.put("fromAccNo", frmdt);
					excelTitleMap.put("toAccNo", todt);
					excelTitleMap.put("documentType", doc_type);
				}

				Iterator studentDataItr = prepareSearchCriteriaLst.iterator();
				AccessionWiseExportRecord recordProcessor = new AccessionWiseExportRecord(excelTitleMap);

				response.setContentType("text/csv");
				response.setHeader(
						"Content-Disposition",
						"attachment; filename="
								+ recordProcessor.getExcelFileName());

				csvImportExportService.Export(studentDataItr, recordProcessor,
						response.getOutputStream());

			}
			}
			
			
			else if(flag1.equalsIgnoreCase("PdfMissingReport")) {

				boolean checkData = rs.getCheckData(MissingnamedQuery);
				  if (checkData)
					{
					 //no data
						log4jLogger.info("----------------NO DATA FOUND-------------------");
					  indexPage = "/AccessionRegister/index.jsp?check=NoData";
					  dispatch(request, response, indexPage);
					 
					}
				 
				 else{

				parameters.put("CMP_NAME", ReportQueryUtill.COMPANY_NAME);
				parameters.put("CMP_ADD",ReportQueryUtill.COMPANY_ADD);
				parameters.put("ReportTitle", ReportQueryUtill.Missing_Number_Title);
				parameters.put("All_Query", MissingnamedQuery);
				log4jLogger.info("namedQuery: " + MissingnamedQuery);

				InputStream inputStream1 = getServletContext().getResourceAsStream("/Report/MissingNumber.jasper");
				JasperReport report = (JasperReport) JRLoader.loadObject(inputStream1);
				JasperPrint jasperPrint = JasperFillManager.fillReport(report,parameters, con);
				JRAbstractExporter exporterPDF = new JRPdfExporter();
				
				
				exporterPDF.setParameter(JRExporterParameter.JASPER_PRINT,jasperPrint);
				exporterPDF.setParameter(JRExporterParameter.OUTPUT_STREAM,response.getOutputStream());
				response.setHeader("Content-Disposition","attachment;filename="	+ ReportQueryUtill.Missing_Number_Title + ".pdf");
				response.setContentType("application/pdf");
				exporterPDF.exportReport();
				 }

			
				
				
			}

		}

		catch (Exception e) {

			e.printStackTrace();
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {

					e.printStackTrace();
				}
			}
		}

	}

	//String indexPage = "/AccessionRegister/index.jsp";

	public void dispatch(HttpServletRequest request,
			HttpServletResponse response, String indexPage)
			throws ServletException, IOException {
		RequestDispatcher dispatch = request.getRequestDispatcher(indexPage);
		dispatch.forward(request, response);
	}
}
