package Lib.Auto.BulkUpdate;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
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
import Lib.Auto.Author.AuthorBean;
import Lib.Auto.Counter.COUNTER_QUERY;
import Lib.Auto.OrdInvProcessing.orderbean;

import Common.businessutil.BusinessServiceFactory;
import Common.businessutil.admin.AdminService;
import Common.businessutil.admin.AdminServiceImpl;
import Common.businessutil.calaloging.CalalogingService;
import Common.businessutil.reports.ReportQueryUtill;


public class BulkUpdate extends HttpServlet implements Serializable, COUNTER_QUERY {
	
	private static final long serialVersionUID = 1L;
	private static Logger log4jLogger = Logger.getLogger(BulkUpdate.class);
	
	String flag="",strsql="",strsql1="",strsql2="";
	String indexPage = null;	
	String frm_accno="",to_accno="",frm_dt="",to_dt="",optionValue="",firstStr="";	
	int firstNum = 0, secondNum = 0, firstStrCount = 0;

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
						
			AdminService ss = BusinessServiceFactory.INSTANCE.getAdminService();
			BulkUpdateMsgBean beanObject = new BulkUpdateMsgBean();
			
			String frmUpdate = "",toUpdate = "",frmValue = "",toValue = "";		
			
			flag = request.getParameter("flag");			
			
			if(flag.equalsIgnoreCase("search") && flag != null && !flag.isEmpty())
			{
				log4jLogger.info("start==============>flag:"+flag);
				
				frmUpdate = request.getParameter("frmUpdate");	
				toUpdate = request.getParameter("toUpdate");
				
			   if(!frmUpdate.equals("") && !frmUpdate.equals("NO") && !frmUpdate.equals("ACCESSION") && !frmUpdate.equals("RECEIVEDDATE") && !frmUpdate.equals("Invoice_date") )
			   {
				   System.out.println("::::::::::RECEIVEDDATE::::::::::::"+frmUpdate);
				   List resultList = ss.getBulkUpdateList(flag, frmUpdate);           
		           request.setAttribute("resultBulkList1", resultList);  
			   }			   
			   
			   if(!toUpdate.equals("") && !toUpdate.equals("NO") && !toUpdate.equals("ACCESSION") && !toUpdate.equals("CALLNO") && !toUpdate.equals("RECEIVEDDATE")  && !frmUpdate.equals("Invoice_date")  && !toUpdate.equals("Title")  
			      && !toUpdate.equals("Edition") && !toUpdate.equals("YearPub") && !toUpdate.equals("Keywords") && !toUpdate.equals("Location") && !toUpdate.equals("VolumeNo") && !toUpdate.equals("ISBN")  && !toUpdate.equals("Invoice_no")  && !toUpdate.equals("BPrice") )
			   {				   
		           List resultList = ss.getBulkUpdateList(flag, toUpdate);
		           request.setAttribute("resultBulkList2", resultList); 
			   }

			   if(!frmUpdate.isEmpty() && !toUpdate.isEmpty())
			   {
				   beanObject = new BulkUpdateMsgBean();
				   beanObject.setFrmUpdate(frmUpdate);           
			       beanObject.setToUpdate(toUpdate);
			       
			       beanObject.setFrmValue(request.getParameter("frmValue"));
			       beanObject.setToValue(request.getParameter("toValue"));
			       
		           request.setAttribute("beanForm",beanObject);
		           indexPage = "/BulkUpdate/index.jsp";	
		           dispatch(request, response, indexPage);
			   }	   
			   
			}
			
			
			if(flag.equalsIgnoreCase("update") && flag != null && !flag.isEmpty())
			{
				log4jLogger.info("start==============>flag:"+flag);
				
				frmUpdate = request.getParameter("frmUpdate");	
				toUpdate = request.getParameter("toUpdate");
				frmValue = request.getParameter("frmValue");
				toValue = request.getParameter("toValue");
				
				System.out.println("::::frmUpdate:::::"+frmUpdate);
				if( !frmUpdate.equals("") && !frmUpdate.equals("NO") )
				{					   
					if(frmUpdate.equals("Aut"))  // will be update author_interface table in future.
			        {						
			        	strsql1 = " and author_code = "+frmValue;
			        }
					else if(frmUpdate.equals("Dept"))  			        
					{	
			        	strsql1 = " and dept_code = "+frmValue;
			        }
					
					else if(frmUpdate.equals("Invoice_No"))  			        
					{	
						System.out.println("::::Invoice_no:::::"+frmValue);
			        	strsql1 = " and Invoice_no = "+frmValue;
			        }
					else if(frmUpdate.equals("Discount"))  			        
					{	
			        	strsql1 = " and Discount = "+frmValue;
			        }
					else if(frmUpdate.equals("Pub"))
			        {			        	
			        	strsql1 = " and pub_code = "+frmValue;		        	
			        }
					else if(frmUpdate.equals("Sup"))
			        {						
			        	strsql1 = " and sup_code = "+frmValue;		        	
			        }
					else if(frmUpdate.equals("Sub"))
			        {						
			        	strsql1 = " and sub_code = "+frmValue;		        	
			        }
					else if(frmUpdate.equals("Bud"))
			        {						
			        	strsql1 = " and budg_code = "+frmValue;		        	
			        }
					
					else if(frmUpdate.equals("CALLNO"))
			        {						
						strsql1 = " and call_no = '"+frmValue+"'";
			        }
					else if(frmUpdate.equals("Title"))
			        {
						strsql1 = " and Title = '"+frmValue+"'";
			        }
			        else if(frmUpdate.equals("Edition"))
			        {
			        	strsql1 = " and Edition = '"+frmValue+"'";
			        }
			        else if(frmUpdate.equals("YearPub"))
			        {
			        	strsql1 = " and Year_Pub = '"+frmValue+"'";
			        }
			        else if(frmUpdate.equals("Keywords"))
			        {
			        	strsql1 = " and Keywords = '"+frmValue+"'";
			        }
			        else if(frmUpdate.equals("Language"))
			        {
			        	strsql1 = " and Language = '"+frmValue+"'";
			        }
			        else if(frmUpdate.equals("Location"))
			        {
			        	strsql1 = " and Location = '"+frmValue+"'";
			        }
			        else if(frmUpdate.equals("VolumeNo"))
			        {						
						strsql1 = " and VolNo = '"+frmValue+"'";
			        }
			        else if(frmUpdate.equals("ISBN"))
			        {						
						strsql1 = " and ISBN = '"+frmValue+"'";
			        }
			        else if(frmUpdate.equals("Bprice"))
			        {						
						strsql1 = " and Bprice = '"+frmValue+"'";
			        }
			        else if(frmUpdate.equals("Invoice_date"))
			        {						
						strsql1 = " and Invoice_date = '"+frmValue+"'";
			        }
					
			        else if(frmUpdate.equals("PurchaseType"))
			        {						
						strsql1 = " and gift_purchase = '"+frmValue+"'";
			        }
					
			        else if(frmUpdate.equals("biblio"))
			        {						
						strsql1 = " and biblio = '"+frmValue+"'";
			        }
			       
					
			        else if(frmUpdate.equals("Availability"))
			        {						
						strsql1 = " and availability = '"+frmValue+"'";
			        }
					
					
					
					
					/**if(frmUpdate.equals("ACCESSION"))
					{*/
						 frm_accno = request.getParameter("From_Accno");
						 to_accno = request.getParameter("To_Accno");		
						 						 
						 if(!frm_accno.isEmpty() && !to_accno.isEmpty() && frm_accno!="" && to_accno!="")
						 {
							if(Security.IsNumeric(frm_accno) && Security.IsNumeric(to_accno))    // For Checking Numeric or Alpha Numeric Series
							{
								if(frmUpdate.equals("Aut"))  // will be update author_interface table in future.
						        {
									strsql1 = strsql1 +";"+ " and access_no between "+frm_accno+" and "+to_accno;		// There is no ' (single quatation)
						        }else {
						        	strsql1 = strsql1 + " and access_no between "+frm_accno+" and "+to_accno;		// There is no ' (single quatation)
						        }
							}
							else{
								if(frmUpdate.equals("Aut"))
								{
									strsql1 = strsql1 +";"+ " and access_no between '"+frm_accno+"' and '"+to_accno+"'";
								}else {
									strsql1 = strsql1 + " and access_no between '"+frm_accno+"' and '"+to_accno+"'";
								}
							}			   
						 }
						 
					//}					
					
					
					if(frmUpdate.equals("RECEIVEDDATE"))
			        {
						frm_dt = Security.TextDate_member(request.getParameter("fromdt"));
						to_dt = Security.TextDate_member(request.getParameter("todt"));
						
						strsql1 = " and received_date = '"+frm_dt+"'";
			        }
					
					if(frmUpdate.equals("Invoice_date"))
			        {
						frm_dt = Security.TextDate_member(request.getParameter("fromdt"));
						to_dt = Security.TextDate_member(request.getParameter("todt"));
						
						strsql1 = " and invoice_date = '"+frm_dt+"'";
			        }
					
									
				}
				
				String commaSeparated = request.getParameter("flag1");
				if(commaSeparated!=null)
				{
				String[] items = commaSeparated.split(",");
				StringBuffer sb = new StringBuffer();

				for (int i = 1; i < items.length; i++) {

					if (items[i].equalsIgnoreCase("BOOK")) {
						sb.append(",'"+ "BOOK" +"'");
					} else if (items[i].equalsIgnoreCase("BOOK BANK")) {
						sb.append(",'"+ "BOOK BANK" +"'");
					} else if (items[i].equalsIgnoreCase("NON BOOK")) {
						sb.append(",'"+ "NON BOOK" +"'");
					} else if (items[i].equalsIgnoreCase("REPORT")) {
						sb.append(",'"+ "REPORT" +"'");
					}else if (items[i].equalsIgnoreCase("THESIS")) {
						sb.append(",'"+ "THESIS" +"'");
					}else if (items[i].equalsIgnoreCase("PROCEEDING")) {
						sb.append(",'"+ "PROCEEDING" +"'");
				    }else if (items[i].equalsIgnoreCase("ALL")) {
						sb.append(" ");
				    }
			      }	
				
				System.out.println(":::::SB_VALUE1111:::::"+sb.toString().substring(1));
				if(sb.toString().substring(1).equals(""))
				{
				strsql1 = strsql1 + "";
				}
				else
				{
				strsql1= strsql1 + " and document in (" + (sb.toString().substring(1)) +")";
				System.out.println(":::::DOC:::::"+strsql1);
				}
				}
		
				
					 //**************Random Access Number WISE******************//
				    String accno=request.getParameter("accno"); 
					if(!accno.isEmpty() && accno!="")
					 {
						String accno1=accno.replaceAll(",", "','");
						 System.out.println(":::::::INSIDE RANDOM::::::");
						 strsql1=strsql1 + "  and access_no in('"+accno1+"')";
						 System.out.println(":::::QUERY:::"+strsql1);
					 }
					
					
					 //************* Access Number WISE******************//
					   System.out.println(":::::From_Accno:::::"+request.getParameter("From_Accno"));
					   frm_accno = request.getParameter("From_Accno");
					   System.out.println(":::::To_Accno:::::"+request.getParameter("To_Accno"));
					   to_accno = request.getParameter("To_Accno");
					   
					 if(!frm_accno.isEmpty() && !to_accno.isEmpty() && frm_accno!="" && to_accno!="")
						{					
					     
						 System.out.println(":::::FIRSTNUM:::::"+Integer.parseInt(request.getParameter("firstNum")));
						 firstNum = Integer.parseInt(request.getParameter("firstNum"));
						 System.out.println(":::::secondNum:::::"+Integer.parseInt(request.getParameter("secondNum")));
						 secondNum = Integer.parseInt(request.getParameter("secondNum"));
						 System.out.println(":::::firstStr:::::"+request.getParameter("firstStr"));
						 firstStr = request.getParameter("firstStr");
						 
						 firstStrCount = firstStr.length() + 1;
						 System.out.println(":::::firstStrCount:::::"+firstStrCount);
						
						 if(!frm_accno.isEmpty() && !to_accno.isEmpty() && frm_accno!="" && to_accno!="")
							{
						
							 System.out.println(":::::::INSIDE NOT RANDOM::::::");
							 
							if(Security.IsNumeric(frm_accno) && Security.IsNumeric(to_accno))    // For Checking Numeric or Alpha Numeric Series
							{							
								if(frmUpdate.equals("Aut"))  // will be update author_interface table in future.
						        {
									System.out.println("::::::::INSIDE NUMERIC IF:::::::::");
									strsql1 = strsql1 +";"+ " and  access_no REGEXP '^[0-9]+$' and CONVERT(access_no,SIGNED) between '"+ frm_accno + "' and '" + to_accno+ "'";		// There is no ' (single quatation)
						        }else {
						        	System.out.println("::::::::INSIDE NUMERIC ELSE:::::::::");
						        	strsql1 = strsql1 + " and  access_no REGEXP '^[0-9]+$' and CONVERT(access_no,SIGNED) between '"+ frm_accno + "' and '" + to_accno+ "'";		// There is no ' (single quatation)
						        }
							}
							else{
								if(frmUpdate.equals("Aut"))
								{
									System.out.println("::::::::INSIDE ALPHA NUMERIC IF:::::::::");
									strsql1 = strsql1 +";"+ " and NOT access_no REGEXP '[^0-9]+$' And Access_No Like '"+firstStr+"%' And CAST(SUBSTRING(Access_No,"+firstStrCount+") AS SIGNED) BETWEEN '"+firstNum+ "' AND '" + secondNum+ "' ORDER BY LPAD(ACCESS_NO,20,'0')";
								}else {
									System.out.println("::::::::INSIDE ALPHA NUMERIC ELSE:::::::::");
									strsql1 = strsql1 + " and NOT access_no REGEXP '[^0-9]+$' And Access_No Like '"+firstStr+"%' And CAST(SUBSTRING(Access_No,"+firstStrCount+") AS SIGNED) BETWEEN '"+firstNum+ "' AND '" + secondNum+ "' ORDER BY LPAD(ACCESS_NO,20,'0')";
								}
							}			   
						 }
					 
					 
						}
				
				
				if(!toUpdate.equals("") && !toUpdate.equals("NO") && !toUpdate.equals("ACCESSION"))
				{					   
					if(toUpdate.equals("Aut"))  // will be update author_interface table in future.
			        {
						strsql2 = " and author_code = "+toValue;
			        }
					else if(toUpdate.equals("Dept"))  			        
					{	
						strsql2 = " dept_code= "+toValue;
			        }
					else if(toUpdate.equals("Pub"))
			        {			        	
						strsql2 = " pub_code = "+toValue;		        	
			        }
					else if(toUpdate.equals("Sup"))
			        {						
						strsql2 = " sup_code = "+toValue;		        	
			        }
					else if(toUpdate.equals("Sub"))
			        {						
						strsql2 = " sub_code = "+toValue;		        	
			        }
					else if(toUpdate.equals("Bud"))
			        {						
						strsql2 = " budg_code = "+toValue;		        	
			        }
					else if(toUpdate.equals("Language"))
			        {
			        	strsql2 = " Language = '"+toValue+"'";
			        }
					else if(toUpdate.equals("Discount"))
			        {
			        	strsql2 = " Discount = '"+toValue+"'";
			        }
					else if(toUpdate.equals("Availability"))
			        {
			        	strsql2 = " availability = '"+toValue+"'";
			        }
					else if(toUpdate.equals("PurchaseType"))
			        {
			        	strsql2 = " gift_purchase = '"+toValue+"'";
			        }
					else if(toUpdate.equals("biblio"))
			        {
			        	strsql2 = " biblio = '"+toValue+"'";
			        }
					else if(toUpdate.equals("Bprice"))
			        {
			        	strsql2 = " Bprice = '"+toValue+"'";
			        }
					else if(toUpdate.equals("Invoice_date"))
			        {
			        	strsql2 = " invoice_date = '"+toValue+"'";
			        }
					
					else if(toUpdate.equals("CALLNO"))
			        {	
						optionValue=request.getParameter("optionValue");
						strsql2 = " call_no = '"+optionValue+"'";
			        }
					else if(toUpdate.equals("Title"))
			        {
						optionValue=request.getParameter("optionValue");
						strsql2 = " Title = '"+optionValue+"'";
			        }
			        else if(toUpdate.equals("Edition"))
			        {
			        	optionValue=request.getParameter("optionValue");
			        	strsql2 = " Edition = '"+optionValue+"'";
			        }
			        else if(toUpdate.equals("YearPub"))
			        {
			        	optionValue=request.getParameter("optionValue").toString().trim();
			        	strsql2 = " Year_Pub = '"+optionValue+"'";
			        }
			        else if(toUpdate.equals("Keywords"))
			        {
			        	optionValue=request.getParameter("optionValue");
			        	strsql2 = " Keywords = '"+optionValue+"'";
			        }			        
			        else if(toUpdate.equals("Location"))
			        {
			        	optionValue=request.getParameter("optionValue");
			        	strsql2 = " Location = '"+optionValue+"'";
			        }
			        else if(toUpdate.equals("VolumeNo"))
			        {
			        	optionValue=request.getParameter("optionValue");
			        	strsql2 = " VolNo = '"+optionValue+"'";
			        }
			        else if(toUpdate.equals("ISBN"))
			        {
			        	optionValue=request.getParameter("optionValue");
			        	strsql2 = " ISBN = '"+optionValue+"'";
			        }
					
			        else if(toUpdate.equals("Invoice_No"))
			        {
			        	optionValue=request.getParameter("optionValue");
			        	strsql2 = " invoice_no = '"+optionValue+"'";
			        }
			        
			        else if(toUpdate.equals("BPrice"))
			        {	
						optionValue=request.getParameter("optionValue").toString().trim();
						strsql2 = " Bprice = "+optionValue+", Accepted_price = "+optionValue+", BCurrency = 1, BCost = "+optionValue;
			        }
					
					if(toUpdate.equals("RECEIVEDDATE"))
			        {
						to_dt = Security.TextDate_member(request.getParameter("todt"));												
						strsql2 = " received_date = '"+to_dt+"'";
			        }
					if(toUpdate.equals("Invoice_date"))
			        {
						to_dt = Security.TextDate_member(request.getParameter("todt"));												
						strsql2 = " invoice_date = '"+to_dt+"'";
			        }
				}				
				
				beanObject = new BulkUpdateMsgBean();
				beanObject.setFrmUpdate(frmUpdate);           
			    beanObject.setToUpdate(toUpdate);			       
			    beanObject.setFrmValue(strsql1);
			    beanObject.setToValue(strsql2);
				
			    int count = ss.getBulkUpdateSave(beanObject);	
			    log4jLogger.info("start==============>resultDone:"+count);
			    
		        request.setAttribute("resultDone", count);
		        indexPage = "/BulkUpdate/index.jsp";
		        dispatch(request, response, indexPage);
			}
			
			
    	      	
	               			 
	}  catch (Exception e) {
      e.printStackTrace();
	}
catch (Throwable theException) {
	
	}
finally{
	strsql="";
	strsql1="";
	strsql2="";		
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
