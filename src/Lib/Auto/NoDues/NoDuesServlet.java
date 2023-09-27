package Lib.Auto.NoDues;

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
import Lib.Auto.Counter.COUNTER_QUERY;
import Lib.Auto.Member.MemberBean;
import Lib.Auto.MemberReport.MemberReport;


import Common.DBConnection;
import Common.businessutil.BusinessServiceFactory;
import Common.businessutil.calaloging.CalalogingService;
import Common.businessutil.reports.ReportQueryUtill;
import Common.businessutil.reports.ReportService;

public class NoDuesServlet extends HttpServlet implements Serializable, COUNTER_QUERY {
	private static final long serialVersionUID = 1L;
	java.sql.Connection con = null;
	private static Logger log4jLogger = Logger.getLogger(NoDuesServlet.class);
	String indexPage = null;
	String flag="";
	String flag1="";
	
	
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
       
            flag=request.getParameter("Flag_Search");
            flag1=request.getParameter("flag");
            
            CalalogingService ss = BusinessServiceFactory.INSTANCE.getCalalogingService();
            
            
            if (flag.equals("search")) {			

				MemberBean ob = ss.getMemberSearch(request.getParameter("txtUserId"));
				if (ob.getCode()!=null) {	
					
								
					request.setAttribute("BeanObject", ob);				
					indexPage = "/NoDues/index.jsp?check=memberYes";			
				} else {					
					request.setAttribute("ss", ob);
				indexPage ="/NoDues/index.jsp?check=FailMember";				
				}
				dispatch(request, response, indexPage);
			}
            

        //ReportService ss1 = BusinessServiceFactory.INSTANCE.getReportService();		
      	//con=ss1.getDBConnect();
            
            con=SessionHibernateUtil.getSession().connection();
            
      	String filterQuery=request.getParameter("txtUserId");
      	
        if (flag1.equals("IssueCheck")) {
        	log4jLogger.info("Inside from NoDues Report For Check");
           int User_check=0;
           User_check=ss.getIssueMasCheck(filterQuery);
           
           if(User_check>0)
           {        	 
        	   indexPage = "/NoDues/index.jsp?check=IssueYes";  	   
        	   
           }else{        	   
        	   indexPage = "/NoDues/index.jsp?check=IssueNo";  
           }
          
           dispatch(request, response, indexPage);
        }
        
        if (flag1.equals("Print"))
        {  
        	log4jLogger.info("Inside from NoDues Report For Print");
        	
        	int User_check=0;        
            User_check=ss.getIssueMasCheck(filterQuery);
        
        if(User_check>0)
        {   	
          	 indexPage = "/NoDues/index.jsp?check=IssueYes";  	   
          	 dispatch(request, response, indexPage);
          	 
        }else{          	  
        	
        	Map parameters = new HashMap();       	      	
			
			parameters.put("CMP_NAME",ReportQueryUtill.COMPANY_NAME);
			parameters.put("CMP_ADD",ReportQueryUtill.COMPANY_ADD);
			parameters.put("ReportTitle",ReportQueryUtill.NoDues_Title);   				
			parameters.put("Query_Param",filterQuery);				
						
			InputStream inputStream1 = getServletContext().getResourceAsStream("/Report/NoDues_Report.jasper");
			JasperReport report = (JasperReport) JRLoader.loadObject(inputStream1);	
					
			JasperPrint jasperPrint  = JasperFillManager.fillReport(report, parameters,con);			
						
			JRAbstractExporter exporterPDF = new JRPdfExporter();
	        exporterPDF.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
	        exporterPDF.setParameter(JRExporterParameter.OUTPUT_STREAM, response.getOutputStream());
	        response.setHeader("Content-Disposition", "attachment;filename="+ReportQueryUtill.NoDuesReport_Title+".pdf");
	        response.setContentType("application/pdf");
	        exporterPDF.exportReport(); 
          	  
             }
            
        }    
        
        
			 
	}  catch (Exception e) {
         e.printStackTrace();
	}
catch (Throwable theException) {
	
	}
finally{
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
	
	public void dispatch(HttpServletRequest request,
			HttpServletResponse response, String indexPage)
			throws ServletException, IOException {
		RequestDispatcher dispatch = request.getRequestDispatcher(indexPage);
		dispatch.forward(request, response);
	}

}

