package Lib.Auto.Currency;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Common.Security;

import org.apache.log4j.Logger;

import Common.businessutil.BusinessServiceFactory;
import Common.businessutil.calaloging.CalalogingService;
import Lib.Auto.Author.AuthorBean;
import Lib.Auto.Currency.CurrencyBean;
import javax.servlet.annotation.WebServlet;
import com.google.gson.Gson;

@WebServlet("/Currency/CurrencyServlet")


public class Currency extends HttpServlet implements Serializable {
	 private static Logger log4jLogger = Logger.getLogger(Currency.class);
	 private static final long serialVersionUID = -8906987252709033668L;
	
	CurrencyBean ob=new CurrencyBean();

	String flag="";
	String term="";
	String nam="";
	String indexPage = null;

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
			
			
			
            PrintWriter out = response.getWriter();
            CalalogingService ss = BusinessServiceFactory.INSTANCE.getCalalogingService();
            
            response.setContentType("application/json");
            
			try{
				//if(!term.equalsIgnoreCase(null) && !term.equalsIgnoreCase(""))
	            //{
				String term = request.getParameter("Currency");
				
				//System.out.println("Data from ajax call " + term);
							    
				   ArrayList<CurrencyBean> list = ss.getCurrencyAutoSearch(term);
			       for(CurrencyBean user: list){
					//System.out.println(user.getCurrency());
				}       

				String searchList = new Gson().toJson(list);
							
				response.getWriter().write(searchList);  
							
				//System.out.println("Json Value@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+searchList);
	        // }    
		}catch(Exception e){
			e.printStackTrace();
		}  		 

	        		
			flag=request.getParameter("flag");
			
			
			
			
			if(flag.equals("new")){
				log4jLogger.info("new===========flag====="+flag);
				ob = new CurrencyBean();
				ob=ss.getCurrencyMax();
				request.setAttribute("CurrencyBean", ob);
				indexPage = "/Currency/index.jsp?check=newCurrency";
				dispatch(request, response, indexPage);

		}
			if(flag.equals("search")){
				log4jLogger.info("search===========flag====="+flag);
				ob = new CurrencyBean();
				
				ob=ss.getCurrencySearch(Integer.parseInt(request.getParameter("Code")));
				if(ob!=null){
					request.setAttribute("CurrencyBean", ob);
					
					indexPage = "/Currency/index.jsp?check=searchCurrency";	
				}else{
					indexPage = "/Currency/index.jsp?check=FailCurrency";
				}
				dispatch(request, response, indexPage);
			}
		
			if(flag.equals("update")){
				log4jLogger.info("update===========flag====="+flag);
				ob = new CurrencyBean();
				ob.setCode(Integer.parseInt(request.getParameter("Code")));
				ob.setCurrency(request.getParameter("Currency"));
				ob.setIndian_value(request.getParameter("Value"));
				ob.setRemarks(request.getParameter("Remarks"));
			
				int count=ss.getCurrencyUpdate(ob);
				indexPage = "/Currency/index.jsp?check=UpdateCurrency";
				dispatch(request, response, indexPage);
			}
			
		
		
			if(flag.equals("delete")){
				log4jLogger.info("search===========flag====="+flag);
				ob = new CurrencyBean();
				
				ob=ss.getCurrencySearch(Integer.parseInt(request.getParameter("Code")));
				if(ob!=null){
					request.setAttribute("CurrencyBean", ob);
					
					indexPage = "/Currency/index.jsp?check=deleteCheck";	
				}else{
					indexPage = "/Currency/index.jsp?check=FailCurrency";
				}
				dispatch(request, response, indexPage);
				
			}
			if(flag.equals("Confirmdete")){
				log4jLogger.info("Confirmdete===========flag====="+flag);
				
				int Currency_Interface_code=ss.getCurrencyInterface(Integer.parseInt(request.getParameter("Code")));			

				int Currency_Mas_code=ss.getCurrencyMas(Integer.parseInt(request.getParameter("Code")));

				if (Currency_Interface_code == Currency_Mas_code) {
					indexPage = "/Currency/index.jsp?check=ReferredCurrency";
				} else {
					int count=ss.getCurrencyDelete(Integer.parseInt(request.getParameter("Code")));

					indexPage = "/Currency/index.jsp?check=DeleteCurrency";
				}
				dispatch(request, response, indexPage);

			}
		
			if(flag.equals("save")){
				log4jLogger.info("save===========flag====="+flag);
				ob = new CurrencyBean();
				ob.setCode(Integer.parseInt(request.getParameter("Code")));
				ob.setCurrency(request.getParameter("Currency").trim());
				ob.setIndian_value(request.getParameter("Value"));
				ob.setRemarks(request.getParameter("Remarks"));
				
			
				
				int Currency_code=ss.getCurrencyName(ob);
				int Currency_Interface_code=ss.getCurrencyInterface(Integer.parseInt(request.getParameter("Code")));			

				int Currency_Mas_code=ss.getCurrencyMas(Integer.parseInt(request.getParameter("Code")));
				
				
	
				
				if (Currency_code>0){
					ob.setCode(Currency_code);
					ob.setCurrency(request.getParameter("Currency").trim());
					ob.setIndian_value(request.getParameter("Value"));
					ob.setRemarks(request.getParameter("Remarks"));
					request.setAttribute("CurrencyBean", ob);
					//request.setAttribute("currency",String.valueOf(Currency_code));
					indexPage = "/Currency/index.jsp?check=CodeComparecurrency";
				}
				else if(Currency_Interface_code>0){
					ob = new CurrencyBean();
					ob.setCode(Integer.parseInt(request.getParameter("Code")));
					ob.setCurrency(request.getParameter("Currency").trim());
					ob.setIndian_value(request.getParameter("Value"));
					ob.setRemarks(request.getParameter("Remarks"));
					request.setAttribute("CurrencyBean", ob);
					indexPage = "/Currency/index.jsp?check=UpdateCheck";
				}
				else if(Currency_Mas_code>0){
					ob = new CurrencyBean();
					ob.setCode(Integer.parseInt(request.getParameter("Code")));
					ob.setCurrency(request.getParameter("Currency").trim());
					ob.setIndian_value(request.getParameter("Value"));
					ob.setRemarks(request.getParameter("Remarks"));
									
					request.setAttribute("CurrencyBean", ob);
					indexPage = "/Currency/index.jsp?check=Updatename";
				}
				else{
					ob = new CurrencyBean();
					ob.setCode(Integer.parseInt(request.getParameter("Code")));
					ob.setCurrency(request.getParameter("Currency").trim());
					ob.setIndian_value(request.getParameter("Value"));
					ob.setRemarks(request.getParameter("Remarks"));
					int count=ss.getCurrencySave(ob);
					indexPage = "/Currency/index.jsp?check=SaveCurrency";
				}
				dispatch(request, response, indexPage);
			}
		
			
			
			if (flag.equals("currency")) {
				List CurrencyArrylist = null;
				CurrencyBean newbean = null;
				
				
				CurrencyArrylist = new ArrayList();
				ob = new CurrencyBean();
				AuthorBean ab = null;
				ab=new AuthorBean();
				ob.setCurrency(request.getParameter("name"));				
				CurrencyArrylist=ss.getCurrencySearchName(ob);

				try {

				} catch (Exception e) {
				}

				request.setAttribute("serarch", CurrencyArrylist);
		      indexPage = "/Currency/search.jsp?check=ok&nam="+nam+"";
			dispatch(request, response, indexPage);

			}
		
			
			
			 } catch (Exception e) {
					
					}
		    catch (Throwable theException) {
			
		   }
		    finally
		    {
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
	

	/**
	 * Instance variable for SQL statement property
	 */

	
	

	
	
}
