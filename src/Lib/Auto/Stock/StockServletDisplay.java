package Lib.Auto.Stock;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.net.URLEncoder;
import java.util.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;


import org.apache.log4j.Logger;

import Common.Security;
import Common.businessutil.BusinessServiceFactory;
import Common.businessutil.admin.AdminService;
import Lib.Auto.Counter.COUNTER_QUERY;


public class StockServletDisplay extends HttpServlet implements Serializable, COUNTER_QUERY {
	 private static Logger log4jLogger = Logger.getLogger(StockServletDisplay.class);
	private static final long serialVersionUID = 1L;
	
	DataSource datasource;
	
	int count_mas=0,count_yes=0,count_lost=0,count_issued=0,count_binding=0,count_damaged=0,count_transfer=0;
	StockBean SB=new StockBean();
	String flag="",protocol="",strsql="",strsql1="",strsql2="",strsql3="",strsql4="",strsql5="",strsql6="";
	String indexPage = null;
	
	
	ResourceBundle rb = ResourceBundle.getBundle("LocalStrings");
			
	
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
            PrintWriter out = response.getWriter();
            
            AdminService ss = BusinessServiceFactory.INSTANCE.getAdminService();
            
            if(request.getParameter("flag").equals("display")){
            	log4jLogger.info("start===========display=====");
            	
            	String docurl=URLEncoder.encode(request.getParameter("txtdoctype"), "UTF-8");    // For Encode URL
            	
            	out.print("<br><center><b><font face=verdana size=4>Stock&nbsp;Verification&nbsp;Report</font></b></center>");
            	out.print("<br><b><a href=StockServlet?flag="+docurl+"&check=count>Back</a></b>");
                out.print("<br><table border=0 width=70% align=center CELLSPACING='0' BORDERCOLOR='#DADADA'> ");
        		out.print("<tr><td><font face=verdana size=3>Total&nbsp;No&nbsp;Of&nbsp;"+request.getParameter("txtdoctype")+"&nbsp;as&nbsp;per&nbsp;Stock&nbsp;Report:</td><td>"+request.getParameter("txtTotal")+"</font></td></tr>");
        		out.print("<tr><td colspan=2><hr width=100% size=2></td></tr>");
        		out.print("<tr><td>Stock</td><td>"+request.getParameter("txtStock")+"</td></tr>");
        		out.print("<tr><td>Missing</td><td>"+request.getParameter("txtMissing")+"</td></tr>");        		
        		out.print("<tr><td>Lost</td><td>"+request.getParameter("txtLost")+"</td></tr>");
        		out.print("<tr><td>Withdrawn</td><td>"+request.getParameter("txtWithdrawn")+"</td></tr>");        		
        		out.print("<tr><td>Issued</td><td>"+request.getParameter("txtIssued")+"</td></tr>");
        		out.print("<tr><td>Binding</td><td>"+request.getParameter("txtBinding")+"</td></tr>");
        		out.print("<tr><td>Damaged</td><td>"+request.getParameter("txtDamaged")+"</td></tr>");
        		out.print("<tr><td>Transferred</td><td>"+request.getParameter("txtTransfer")+"</td></tr>");
        		out.print("<br>");
        		out.print("<tr><td><font face=verdana size=3>Net&nbsp;Total:</td><td>"+(Integer.parseInt(request.getParameter("txtStock"))+Integer.parseInt(request.getParameter("txtMissing"))+Integer.parseInt(request.getParameter("txtLost"))+Integer.parseInt(request.getParameter("txtWithdrawn"))+Integer.parseInt(request.getParameter("txtIssued"))+Integer.parseInt(request.getParameter("txtBinding"))+Integer.parseInt(request.getParameter("txtDamaged"))+Integer.parseInt(request.getParameter("txtTransfer")))+"</font></td></tr>");
        		out.print("<tr><td colspan=2><hr width=100% size=2></td></tr>");
        		out.print("<tr><td>Total&nbsp;No&nbsp;Missing&nbsp;"+request.getParameter("txtdoctype")+"&nbsp;as&nbsp;per&nbsp;the&nbsp;Stock&nbsp;Report</td><td>"+request.getParameter("txtNotVerify")+"</td></tr>");
        		out.print("<tr><td colspan=2><hr width=100% size=2></td></tr>");
        		out.print("</table>");
        		
        	}
            
            
            
            if(request.getParameter("flag").equals("deleteAll")){            	
            	log4jLogger.info("start===========deleteAll=====");
            	
            	int count=ss.getStockMasDeleteAll();
            	indexPage = "/Stock/index.jsp?status=deletedAll";
    		    dispatch(request, response, indexPage);
            }
            
            if(request.getParameter("flag").equals("insert")){
            	log4jLogger.info("start===========insert=====");
            	
            	SB=ss.getStockMasSave(request.getParameter("access"));
            	
            	if (SB.getavailability()!=""){
            		request.setAttribute("BeanObject",SB);
            		indexPage = "/Stock/index.jsp?status=recordalreadyExist&doc="+request.getParameter("txtdoctype");
            		
            	}else{
            		indexPage = "/Stock/index.jsp?status=inserted&doc="+request.getParameter("txtdoctype");
            	}
            	 dispatch(request, response, indexPage);
            	
            }
            
            
            if(request.getParameter("flag").equals("deleteSingle")){
            	log4jLogger.info("start===========deleteSingle=====");
            	int count=ss.getStockDeleteSingle(request.getParameter("no"));
            	if (count>0){
            		indexPage = "/Stock/index.jsp?status=deletedNotSingle";
            	}else{
            		indexPage = "/Stock/index.jsp?status=deletedSingle";
            	}
            	 dispatch(request, response, indexPage);   
            }
            
            
            
            if(request.getParameter("radioOption").equals("true")){
            	log4jLogger.info("start===========radioOption=====");
            	
            	SB.setflag(request.getParameter("flag"));
            	SB.setdoctype(request.getParameter("txtdoctype"));
            	
            	StockBean newbean=new StockBean();
            	List finalResults = new ArrayList();
            	
            	finalResults=ss.getStockMasDisplay(SB);
            	            	           	
            	 Iterator it=finalResults.iterator();
            	 
            	 String docurl=URLEncoder.encode(request.getParameter("txtdoctype"), "UTF-8");    // For Encode URL        	 
            	 //String decoded = URLDecoder.decode(request.getParameter("txtdoctype"), "UTF-8");// For Decode URL
            	 
            	 out.print("<br><center><b><font face=verdana size=4>Stock&nbsp;Report</font></b></center>");
            	 out.print("<br><b><a href=StockServlet?flag="+docurl+"&check=count>Back</a></b>");
                 out.print("<br><table border=1 width=80% align=center CELLSPACING='0' BORDERCOLOR='#DADADA' BGCOLOR='#F5F5F5'> ");
         		 out.print("<th><font face=verdana size=2>S.No</font></th><th><font face=verdana size=2>Access&nbsp;No</font></th><th><font face=verdana size=2>Title</font></th><th><font face=verdana size=2>Author</font></th><th><font face=verdana size=2>Publisher</font></th><th><font face=verdana size=2>Year</font></th><th><font face=verdana size=2>Document</font></th><th><font face=verdana size=2>BPrice</font></th><th><font face=verdana size=2>Availability</font></th>");
                 //out.print(strsql);                
                 
         	     int i=0;     // For S.No (Serial Number)
         	     
                while(it.hasNext()){
                	i=i+1;   // For S.No (Serial Number)
                	
                	StockBean view=(StockBean) it.next();
             		out.print("<tr>");
            			out.print("<td width=8%><font face=verdana size=2>"+i+"</font></td><td width=10%><font face=verdana size=2>"+view.getaccno()+"</font></td><td width=20%><font face=verdana size=2>"+view.gettitle()+"</font></td>"+"<td width=10%><font face=verdana size=2>"+view.getauthor()+"</font></td>"+"<td width=20%><font face=verdana size=2>"+view.getpublisher()+"</font></td>"+"<td width=20%><font face=verdana size=2>"+view.getyear()+"</font></td><td width=10%>&nbsp;<font face=verdana size=2>"+view.getdocument()+"</font></td><td width=10%><font face=verdana size=2>"+view.getbprice()+"</font></td><td width=10%><font face=verdana size=2>"+view.getavailability()+"</font></td>");
            			out.print("</tr>");	
             	}
             	
             	out.print("</table>");
            	
            }
            
            
            
            
	}  catch (Exception e) {
e.printStackTrace();	}
catch (Throwable theException) {
	
	}
finally{
	try{
		
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
