package Lib.Auto.JNL_Order_Report;

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

import Common.LibraryConstants;
import Common.Security;
import Common.businessutil.BusinessServiceFactory;
import Common.businessutil.calaloging.CalalogingService;
import Common.businessutil.reports.ReportQueryUtill;
import Common.businessutil.serialcontrol.SerialcontrolService;
import Lib.Auto.Author.AuthorBean;
import Lib.Auto.JNL_Invoice.JnlInvoiceBean;
import Lib.Auto.JNL_Supplier_Invoice.JnlSupInvBean;
import Login.Login;

public class JNLOrdReportProcessing extends HttpServlet implements Serializable {
	private static final long serialVersionUID = 1L;

	private static Logger log4jLogger = Logger.getLogger(JNLOrdReportProcessing.class);
	
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
			
			
			SerialcontrolService ss1 = BusinessServiceFactory.INSTANCE.getSerialcontrolService();
						
			JnlInvoiceBean ob=new JnlInvoiceBean();			
			
			flag = request.getParameter("flag");			
			
			
			if(flag.equals("Sup"))
			{
				
				log4jLogger.info("=== JnlOrderPrint Search OrderNo and Supplier ==="+flag);
				List InvArrylist = new ArrayList();
				
				JnlSupInvBean ob1=new JnlSupInvBean();
				ob1.setAdd1(request.getParameter("name"));
				ob1.setOrdNo(request.getParameter("ordno"));
				ob1.setAdd2("");
				InvArrylist=ss1.getInvSearchOrdNoSup(ob1);				
																						
				request.setAttribute("search", InvArrylist);
				indexPage ="/JNL_Order_Report/search_nmvc.jsp?check=ok&flag="+flag+"";
				dispatch(request, response, indexPage);
			}
					
			

			if(flag.equals("Print"))
			{
				String txtreporttype =request.getParameter("reporttype");
				
				log4jLogger.info("=== Inside Print JnlOrder ==="+txtreporttype);
				
				String ordno=null,strsql1=null;
				
				ordno=request.getParameter("ordno");
	            strsql1="and jnl_order_no='"+ordno+"'";				
				
				con=SessionHibernateUtil.getSession().connection();
	     		
    	      	Map parameters = new HashMap();       	     
    			String namedQuery=ReportQueryUtill.JNL_Order_Report;
    			StringBuffer sb = new StringBuffer();
    			String filterQuery=null;
    			
    			sb.append(namedQuery);
				sb.append(" " + strsql1);				   			        			
    			
    			parameters.put("CMP_NAME",ReportQueryUtill.COMPANY_NAME);
				parameters.put("CMP_ADD",ReportQueryUtill.COMPANY_ADD);
				parameters.put("ReportTitle",ReportQueryUtill.JNL_Order_Title);   				
				parameters.put("All_Query",sb.toString());				
				log4jLogger.info("SQL QUERY: " + sb);		
				
				InputStream inputStream1=null;
				
				if(txtreporttype.equals("Advanced"))  {
    	      	
				     inputStream1 = getServletContext().getResourceAsStream("/Report/JNL_Order_Report.jasper");
				
				}else  {
	    	      	
					 inputStream1 = getServletContext().getResourceAsStream("/Report/JNL_OrderSimple_Report.jasper");
					
				}
				
				JasperReport report = (JasperReport) JRLoader.loadObject(inputStream1);		  
				JasperPrint jasperPrint  = JasperFillManager.fillReport(report, parameters,con);
				
				
				JRAbstractExporter exporterPDF = new JRPdfExporter();
		        exporterPDF.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		        exporterPDF.setParameter(JRExporterParameter.OUTPUT_STREAM, response.getOutputStream());
		        response.setHeader("Content-Disposition", "attachment;filename="+ReportQueryUtill.JNL_Order_Title+".pdf");
		        response.setContentType("application/pdf");
		        exporterPDF.exportReport();
		        
				
		        
				//indexPage ="/JNL_Order_Report/index.jsp?check=ok&flag="+flag+"";
				//dispatch(request, response, indexPage);
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
