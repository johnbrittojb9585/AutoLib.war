package Lib.Auto.Indent_Payment_Report;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
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
import Common.businessutil.BusinessServiceFactory;
import Common.businessutil.acquisition.AcquisitionService;
import Common.businessutil.reports.ReportQueryUtill;
import Common.businessutil.serialcontrol.SerialcontrolService;
import Lib.Auto.Indent_Payment.IndentPaymentBean;
import Lib.Auto.JNL_Payment.JnlPaymentBean;


public class IndentPaymentReport extends HttpServlet implements Serializable {
	private static final long serialVersionUID = 1L;

	private static Logger log4jLogger = Logger.getLogger(IndentPaymentReport.class);
	
	java.sql.Connection con = null;
	String indexPage = null;
	String flag = null;


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException {

		performTask(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException {

		performTask(request, response);

	}

	
	
	
	public void performTask(HttpServletRequest request,
			HttpServletResponse response) throws ServletException {
		try {
			HttpSession session = request.getSession(true);
			String res = Security.checkSecurity(1, session, response, request);		
			if(res.equalsIgnoreCase("Failure"))
			{
				response.sendRedirect("/AutoLib/Tree/sessiontimeout.jsp");  
				return;
			}
			
			AcquisitionService ss = BusinessServiceFactory.INSTANCE.getAcquisitionService();
						
			IndentPaymentBean ob=new IndentPaymentBean();			
			
			flag = request.getParameter("flag");			
			
			
			if(flag.equals("PaymentNo"))
			{
				
				log4jLogger.info("==========Payment Number Search========"+flag);
				List PmtArrylist = new ArrayList();
				
				ob=new IndentPaymentBean();
				ob.setInvNo(request.getParameter("name"));
				ob.setAdd2("PaymentNo");
				
				PmtArrylist=ss.getPaymentIndentSearch(ob);	
				
				request.setAttribute("search", PmtArrylist);				
				indexPage ="/Indent_Payment_Report/search_nmvc.jsp?check=ok&flag="+flag+"";
				dispatch(request, response, indexPage);
			}					
			

			if(flag.equals("Print"))
			{				
				log4jLogger.info("=== Inside Print IndentPaymentReport ===");
				
				String pmtno="",strsql="",txtfrmdt="",txttodt;
				String txtreporttype =request.getParameter("reporttype");
				
				if(txtreporttype.equals("PmtNoWise"))  {
					
					pmtno=request.getParameter("pmtno");
					strsql=" and Payment_Ref_No='"+pmtno+"'";
					
				}else {
					
					txtfrmdt=Security.TextDate_member(request.getParameter("txtfdate"));
					txttodt=Security.TextDate_member(request.getParameter("txttdate"));	
					
					strsql=strsql + " and Payment_Sent_Date between '"+txtfrmdt+"' and '"+txttodt+"'";
					
				}
				
				strsql=strsql + " ORDER BY payment_ref_no desc";
				
				con=SessionHibernateUtil.getSession().connection();
	     		
    	      	Map parameters = new HashMap();       	     
    			String namedQuery=ReportQueryUtill.Indent_Payment_Report;
    			StringBuffer sb = new StringBuffer();
    			String filterQuery=null;
    			
    			sb.append(namedQuery);
				sb.append(" " + strsql);				   			        			
    			
    			parameters.put("CMP_NAME",ReportQueryUtill.COMPANY_NAME);
				parameters.put("CMP_ADD",ReportQueryUtill.COMPANY_ADD);
				parameters.put("ReportTitle",ReportQueryUtill.Indent_Payment_Title);   				
				parameters.put("All_Query",sb.toString());				
				log4jLogger.info("SQL QUERY: " + sb);		
				
				

				InputStream inputStream=null;
				
				if(txtreporttype.equals("PmtNoWise"))  {
    	      	
				     inputStream = getServletContext().getResourceAsStream("/Report/Indent_PaymentReceipt_Report.jasper");
				
				}else  {
	    	      	
					inputStream = getServletContext().getResourceAsStream("/Report/Indent_Payment_Report.jasper");
					
				}
				
				
				JasperReport report = (JasperReport) JRLoader.loadObject(inputStream);		  
				JasperPrint jasperPrint  = JasperFillManager.fillReport(report, parameters,con);
				
				JRAbstractExporter exporterPDF = new JRPdfExporter();
		        exporterPDF.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		        exporterPDF.setParameter(JRExporterParameter.OUTPUT_STREAM, response.getOutputStream());
		        response.setHeader("Content-Disposition", "attachment;filename="+ReportQueryUtill.Indent_Payment_Title+".pdf");
		        response.setContentType("application/pdf");
		        exporterPDF.exportReport();
		       
			}
			
			
			
		}catch(Exception e) {
			e.printStackTrace();
			
		}
		finally {
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

	/***************************************************************************
	 * prepare the sql statement for execution
	 */
	public void dispatch(HttpServletRequest request,
			HttpServletResponse response, String indexPage)
			throws ServletException, IOException {
		RequestDispatcher dispatch = request.getRequestDispatcher(indexPage);
		dispatch.forward(request, response);
	}

}
