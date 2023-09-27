package Lib.Auto.JnlIssueReport;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
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
import Lib.Auto.JournalList.JournalList;
import Lib.Auto.OrdInvProcessing.orderbean;


import Common.DBConnection;
import Common.businessutil.BusinessServiceFactory;
import Common.businessutil.reports.ReportQueryUtill;
import Common.businessutil.reports.ReportService;

public class JnlIssueReport extends HttpServlet implements Serializable, COUNTER_QUERY {
	private static final long serialVersionUID = 1L;
	java.sql.Connection con = null;
	java.sql.PreparedStatement Prest = null;
	java.sql.ResultSet rs = null;
	java.sql.ResultSet rs1 = null;
	DataSource datasource;

	String flag="",protocol="",strsql="",strsql1="",strsql2="",strsql3="";;
	String indexPage = null;
	orderbean orderObject=new orderbean();
	ResourceBundle rb = ResourceBundle.getBundle("LocalStrings");
	private static Logger log4jLogger = Logger.getLogger(JnlIssueReport.class);
    
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
			
            if(!request.getParameter("jnlname").equals("")){
       	       strsql1=" and jnl_name='"+request.getParameter("jnlname")+"'";       	  
              }    
            
            if(!request.getParameter("avail").equals("")&& !request.getParameter("avail").equals("ALL")){
        	       strsql1=strsql1 + " and availability ='"+request.getParameter("avail")+"'";       	  
               }  
                         
            if(request.getParameter("flag").equals("isdate")){
                strsql2=" and issue_date between '"+Security.TextDate_Insert(request.getParameter("fromdate"))+"' and '"+Security.TextDate_Insert(request.getParameter("todate"))+"'";
              } 
            
            if(!request.getParameter("deptname").equals("")&& !request.getParameter("deptname").equals("ALL")){
                strsql3=" and dept_name='"+request.getParameter("deptname")+"  '";
              }
            
            if(!request.getParameter("pubname").equals("")&& !request.getParameter("pubname").equals("ALL")){
                strsql3= strsql3 + " and publisher='"+request.getParameter("pubname")+"'";
              }
                     
            //ReportService ss = BusinessServiceFactory.INSTANCE.getReportService();		
	      	//con=ss.getDBConnect();	
	      	
	      	con=SessionHibernateUtil.getSession().connection();
	      	
	      	Map parameters = new HashMap();       	      	
			String namedQuery="select issue_access_no,jnl_name,issue_year,issue_month,issue_volume,issue_no,bvol_no,Date_format(received_date,'%d/%m/%Y')as received_date,availability from f_journal_issues where 2>1";
			StringBuffer sb = new StringBuffer();
			String filterQuery=null;
			
			sb.append(namedQuery);
				sb.append(" " +strsql1 +" " +strsql2+" " + strsql3);
			   			        			
			
			parameters.put("CMP_NAME",ReportQueryUtill.COMPANY_NAME);
			parameters.put("CMP_ADD",ReportQueryUtill.COMPANY_ADD);
			parameters.put("ReportTitle",ReportQueryUtill.JNL_Issues_Title);   				
			parameters.put("All_Query",sb.toString());				
			log4jLogger.info("SQL QUERY: " + sb);		
			
			
			InputStream inputStream1 = getServletContext().getResourceAsStream("/Report/Journal_Issue.jasper");
			JasperReport report = (JasperReport) JRLoader.loadObject(inputStream1);	
			log4jLogger.info("SQL QUERYkkkkkkkkkkk: ");
			JasperPrint jasperPrint  = JasperFillManager.fillReport(report, parameters,con);			
			log4jLogger.info("SQL QUERYlllllllllllllllll: ");	
			JRAbstractExporter exporterPDF = new JRPdfExporter();
	        exporterPDF.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
	        exporterPDF.setParameter(JRExporterParameter.OUTPUT_STREAM, response.getOutputStream());
	        response.setHeader("Content-Disposition", "attachment;filename="+ReportQueryUtill.JNL_Issues_Title+".pdf");
	        response.setContentType("application/pdf");
	        exporterPDF.exportReport();            
        
        
        
	}  catch (Exception e) {
e.printStackTrace();
	}
catch (Throwable theException) {
	
	}
finally{
	strsql="";
	strsql1="";
	strsql2="";
	strsql3="";
	flag="";
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
		   
			RequestDispatcher dispatch = request.getRequestDispatcher(indexPage);
			dispatch.forward(request, response);
		}

}
