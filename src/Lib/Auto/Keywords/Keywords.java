package Lib.Auto.Keywords;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.UnavailableException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import Lib.Auto.Keywords.KeywordsBean;

import org.apache.log4j.Logger;

import Common.DataQuery;
import Common.Security;


import Common.businessutil.BusinessServiceFactory;
import Common.businessutil.calaloging.CalalogingService;
import Lib.Auto.Author.AuthorBean;
import Lib.Auto.Currency.Currency;
import Lib.Auto.Currency.CurrencyBean;


import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.Message.RecipientType;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


import javax.mail.*;
import javax.mail.internet.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.annotation.WebServlet;
import com.google.gson.Gson;

@WebServlet("/Keywords/KeywordsServlet")


public class Keywords extends HttpServlet implements Serializable {
	private static Logger log4jLogger = Logger.getLogger(Keywords.class);

	
	
	private static final long serialVersionUID = -8906987252709033668L;

	String protocol = "", flag = "";
	String term="";
	String Keyword_Name = "", SameCode = "";

	int Author_Interface_code, Author_Mas_code;

	

	String indexPage = null;
	

	KeywordsBean ob = new KeywordsBean();

	

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
			
			
			CalalogingService ss = BusinessServiceFactory.INSTANCE.getCalalogingService();
			PrintWriter out = response.getWriter();
			
            response.setContentType("application/json");
            
			try{
				//if(!term.equalsIgnoreCase(null) && !term.equalsIgnoreCase(""))
	            //{
				String term = request.getParameter("name");
				
				//System.out.println("Data from ajax call " + term);
							    
				   ArrayList<KeywordsBean> list = ss.getKeywordsAutoSearch(term);
			       for(KeywordsBean user: list){
					//System.out.println(user.getKname());
				}       

				String searchList = new Gson().toJson(list);
							
				response.getWriter().write(searchList);  
							
				//System.out.println("Json Value@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+searchList);
	        // }    
		}catch(Exception e){
			e.printStackTrace();
		}  		 

		

			flag = request.getParameter("flag");

			if (flag.equals("new")) {
				log4jLogger.info("new===========flag====="+flag);
				ob = new KeywordsBean();
				ob=ss.getKeywordsMax();
				request.setAttribute("BeanObject",ob);
				indexPage = "/Keywords/index.jsp?check=newKeywords";
				dispatch(request, response, indexPage);
			}
			if (flag.equals("update")) {
				log4jLogger.info("update===========flag====="+flag);
				ob = new KeywordsBean();
				ob.setKcode(Integer.parseInt(request.getParameter("code")));
				ob.setKname(request.getParameter("name"));
				ob.setKdesc(request.getParameter("desc"));
				int count=ss.getKeywordsUpdate(ob);
				indexPage = "/Keywords/index.jsp?check=UpdateKeywords";
				dispatch(request, response, indexPage);
			}
			
			if (flag.equals("search")) {
				log4jLogger.info("search===========flag====="+flag);
				ob = new KeywordsBean();
				
				ob=ss.getKeywordsSearch(Integer.parseInt(request.getParameter("code")));
				if(ob!=null){
					request.setAttribute("BeanObject", ob);
					
					indexPage = "/Keywords/index.jsp?check=searchKeywords";	
				}else{
					indexPage = "/Keywords/index.jsp?check=FailKeywords";
				}
				dispatch(request, response, indexPage);
			}
			if (flag.equals("delete")) {
				log4jLogger.info("delete===========flag====="+flag);
				ob = new KeywordsBean();
				
				ob=ss.getKeywordsSearch(Integer.parseInt(request.getParameter("code")));
				if(ob!=null){
					request.setAttribute("BeanObject", ob);
					
					indexPage = "/Keywords/index.jsp?check=deleteCheck";	
				}else{
					indexPage = "/Keywords/index.jsp?check=FailKeywords";
				}
				dispatch(request, response, indexPage);
			}
			if(flag.equals("Confirmdete")){
				log4jLogger.info("Confirmdete===========flag====="+flag);
							
					int count=ss.getKeywordsDelete(Integer.parseInt(request.getParameter("code")));

					indexPage = "/Keywords/index.jsp?check=DeleteKeywords";
				
				dispatch(request, response, indexPage);
			}
			
			if (flag.equals("save")) {
				log4jLogger.info("save===========flag====="+flag);
				ob = new KeywordsBean();
				ob.setKcode(Integer.parseInt(request.getParameter("code")));
				ob.setKname(request.getParameter("name").trim());
				ob.setKdesc(request.getParameter("desc"));
				
				int Keywords_code=ss.getKeywordsName(ob);
				int Keywords_Mas_code=ss.getKeywordsMas(Integer.parseInt(request.getParameter("code")));
				
				if(Keywords_code>0){
					ob.setKcode(Keywords_code);
					ob.setKname(request.getParameter("name").trim());
					ob.setKdesc(request.getParameter("desc"));
					//request.setAttribute("keywordname", String.valueOf(Keywords_code));
					request.setAttribute("BeanObject", ob);
					indexPage = "/Keywords/index.jsp?check=CodeCompareKeyword&no="+Keywords_code+"";
				}
				else if(Keywords_Mas_code>0){
					ob = new KeywordsBean();
					ob.setKcode(Integer.parseInt(request.getParameter("code")));
					ob.setKname(request.getParameter("name").trim());
					ob.setKdesc(request.getParameter("desc"));
					request.setAttribute("BeanObject", ob);
					indexPage = "/Keywords/index.jsp?check=UpdateCheck";
				}
				else{
					ob = new KeywordsBean();
					ob.setKcode(Integer.parseInt(request.getParameter("code")));
					ob.setKname(request.getParameter("name").trim());
					ob.setKdesc(request.getParameter("desc"));
					int count=ss.getKeywordsSave(ob);
					indexPage = "/Keywords/index.jsp?check=SaveKeyword";
				}
				dispatch(request, response, indexPage);
			}
               
                if (flag.equals("Keywords")) {
    				List KeywordsArrylist = null;
    				
    				KeywordsArrylist = new ArrayList();
    				ob = new KeywordsBean();
   				
    				ob.setKname(request.getParameter("name"));	
    				
    				KeywordsArrylist=ss.getKeywordsSearchName(ob);
  				
    				request.setAttribute("serarch", KeywordsArrylist);
    				indexPage = "/Keywords/search.jsp?check=ok";
    				dispatch(request, response, indexPage);

    			}
                

		} catch (Exception sss) {
			throw new ServletException(sss);
			//sss.printStackTrace();
		} finally {
			try{
				

			}catch(Exception e){
			e.printStackTrace();
			}

		}

	}

	/** 
	 * Instance variable for SQL statement property
	 */

	/****************************************************************
	 *prepare the sql statement for execution
	 **/
	public void dispatch(HttpServletRequest request,
			HttpServletResponse response, String indexPage)
			throws ServletException, IOException {
		RequestDispatcher dispatch = request.getRequestDispatcher(indexPage);
		dispatch.forward(request, response);
	}

}
