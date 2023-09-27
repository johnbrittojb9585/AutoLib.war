package Lib.Auto.EBook;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.io.File;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.UnavailableException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import Common.Security;

import Common.businessutil.BusinessServiceFactory;
import Common.businessutil.calaloging.CalalogingService;
import Lib.Auto.Member.MemberBean;
import Lib.Auto.EBook.EBookBean;
import Lib.Auto.EBook.EBookSearchBean;
import Lib.Auto.Author.AuthorBean;

public class EBookSearch extends HttpServlet {
	String protocol="",nm="",SQLString="",f1="",f2="",f3="";
	ResourceBundle rb = ResourceBundle.getBundle("LocalStrings");
	
	private static Logger log4jLogger = Logger.getLogger(EBookSearch.class);
	
	String indexPage = null;
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
	            CalalogingService ss = BusinessServiceFactory.INSTANCE.getCalalogingService();
	            
	            nm=request.getParameter("name");
	            //doc_type=request.getParameter("docType");
			    out.print("Name"+nm);
			    String value=request.getParameter("sflag");
			    String kar=request.getParameter("iflag");
			    
			    String authsave=request.getParameter("flag");
			    
			    out.print(value);
			    
			    
			    log4jLogger.info("=======new====flag====="+authsave);
			    			    
			    
			    if(value.equals("accessNo"))
				  
			    {
			    	 log4jLogger.info("accessNo===========flag====="+nm);
                 ArrayList list=null;
                 EBookBean ob = new EBookBean();                                 
                 List EBookArraylist = new ArrayList();
                 
                 		    	 		    	 		    	
                 //AuthorArrylist=ss.getBookSearchTitle(nm,doc_type);
                 EBookArraylist=ss.getEBookSearchTitle(nm);
                 request.setAttribute("search", EBookArraylist);
 				indexPage ="/EBook/search_all.jsp?check=ok&flag="+value+"&name="+nm+"";
 				dispatch(request, response, indexPage);	
 		 
                 }	    
			   		   
			    if(value.equals("Aut"))
			    	
			    		{
			    	log4jLogger.info("Author===========flag====="+nm);
			    			ArrayList list=null;
                            
			                 List AuthorArraylist = new ArrayList();
			 		    	 		    	 		    	
			                 AuthorArraylist=ss.getEBookSearchAuthor(nm);
			                 request.setAttribute("search", AuthorArraylist);
			 				indexPage ="/EBook/search_all.jsp?check=ok&flag="+value+"&name="+nm+"";
			 				dispatch(request, response, indexPage);	
			    		}
	      
			    if(value.equals("Pub")){
			    	log4jLogger.info("Publisher===========flag====="+nm);
			    	ArrayList list=null;
                    
	                 List AuthorArrylist = new ArrayList();
	 		    	 		    	 		    	
	                 AuthorArrylist=ss.getEBookSearchPub(nm);
	                request.setAttribute("search", AuthorArrylist);
	 				indexPage ="/EBook/search_all.jsp?check=ok&flag="+value+"&name="+nm+"";
	 				dispatch(request, response, indexPage);	
			    	
			    }
			    if(value.equals("Sup")){
			    	log4jLogger.info("Supplier===========flag====="+nm);
			    	ArrayList list=null;
                    
	                 List AuthorArrylist = new ArrayList();
	 		    	 		    	 		    	
	                 AuthorArrylist=ss.getEBookSearchSup(nm);
	                request.setAttribute("search", AuthorArrylist);
	 				indexPage ="/EBook/search_all.jsp?check=ok&flag="+value+"&name="+nm+"";
	 				dispatch(request, response, indexPage);	
			    	
			    }
			    
			    if(value.equals("Sub")){
			    	log4jLogger.info("Subject===========flag====="+nm);
			    	ArrayList list=null;
                    
	                 List AuthorArrylist = new ArrayList();
	 		    	 		    	 		    	
	                 AuthorArrylist=ss.getEBookSearchSubject(nm);
	                 request.setAttribute("search", AuthorArrylist);
	 				indexPage ="/EBook/search_all.jsp?check=ok&flag="+value+"&name="+nm+"";
	 				dispatch(request, response, indexPage);	
			    }
			    
			    if(value.equals("callNo")){
			    	log4jLogger.info("Call_No===========flag====="+nm);
			    	ArrayList list=null;
                    
	                 List AuthorArrylist = new ArrayList();
	 		    	 		    	 		    	
	                 AuthorArrylist=ss.getEBookSearchCallNo(nm);
	                 request.setAttribute("search", AuthorArrylist);
	 				indexPage ="/EBook/search_all.jsp?check=ok&flag="+value+"&name="+nm+"";
	 				dispatch(request, response, indexPage);	
			    }
				    
			    if(value.equals("Dept")){
			    	log4jLogger.info("Dept===========flag====="+nm);
			    	ArrayList list=null;
                    
	                 List AuthorArrylist = new ArrayList();
	 		    	 		    	 		    	
	                 AuthorArrylist=ss.getEBookSearchDept(nm);
	                 request.setAttribute("search", AuthorArrylist);
	 				indexPage ="/EBook/search_all.jsp?check=ok&flag="+value+"&name="+nm+"";
	 				dispatch(request, response, indexPage);	
			    }  
				    
			}   
			   catch (Exception e) {

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
}
