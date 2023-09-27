package Lib.Auto.Stock;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.sql.DriverManager;
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

import Common.DataQuery;
import Common.Security;

import Common.businessutil.BusinessServiceFactory;
import Common.businessutil.admin.AdminService;

import Lib.Auto.Counter.COUNTER_QUERY;
import Lib.Auto.Fine.Fine;
import Lib.Auto.OrdInvProcessing.orderbean;

public class StockServlet extends HttpServlet implements Serializable, COUNTER_QUERY {
	private static final long serialVersionUID = 1L;
	
	private static Logger log4jLogger = Logger.getLogger(StockServlet.class);

	int count_mas=0,count_yes=0,count_lost=0,count_issued=0,count_binding=0,count_damaged=0,count_transfer=0;
	StockBean SB=new StockBean();
	String flag="",protocol="";
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
                  
 
           
            if(request.getParameter("flag").equals("LOAD")){
            	
            	SB=ss.getStockAll();
            	request.setAttribute("BeanObject",SB);
    			indexPage = "/Stock/index.jsp?doc=ALL";
    		    dispatch(request, response, indexPage);
            }
            
           
            if(request.getParameter("check").equals("count")){
            	if(!request.getParameter("flag").equals("ALL")){
            		
            		SB=ss.getStock(request.getParameter("flag"));
            	
            	}
            	else{
            		SB=ss.getStockAll();
            		           		
            	}
    			request.setAttribute("BeanObject",SB);
    			indexPage = "/Stock/index.jsp?doc="+request.getParameter("flag")+"";
    		    dispatch(request, response, indexPage);
            }
            
            
	}  catch (Exception e) {

	}
catch (Throwable theException) {
	
	}
finally{
	try{
		

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
