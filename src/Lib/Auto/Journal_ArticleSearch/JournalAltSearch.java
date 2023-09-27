/*
 * Created on Apr 27, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package Lib.Auto.Journal_ArticleSearch;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import Common.Security;
import Common.businessutil.BusinessServiceFactory;
import Common.businessutil.search.SearchService;
import Lib.Auto.Newsclliping.NewscllipingBean;
import Lib.Auto.Journal_ArticleSearch.JournalAtlSearchbean;

import javax.servlet.annotation.WebServlet;
import com.google.gson.Gson;

@WebServlet("/Journal_ArticleSearch/JNLArticleSearchServlet")


public class JournalAltSearch extends HttpServlet implements Serializable {
	
	private static Logger log4jLogger = Logger.getLogger(JournalAltSearch.class);
	private static final long serialVersionUID = 1L;
			
	String indexPage = null;
	List SearchArrylist;
	List AdsearchArrylist;
		
	String flag = "",title1="";
	String SQL_Query = "", SQL_Query_Cnt = "",SQL_Query_Con="",SQL_Query_std = "",SQL_Query_cur="",SQL_Query_alpha="";

	public void doGet(HttpServletRequest request, HttpServletResponse response) {

		performTask(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) {

		performTask(request, response);
	}
	
	public void performTask(
		HttpServletRequest request,
		HttpServletResponse response) {

		try {
			HttpSession session = request.getSession(true);
			String res = Security.checkSecurity(1, session, response, request);		
			if(res.equalsIgnoreCase("Failure"))
			{
				response.sendRedirect("/AutoLib/Tree/sessiontimeout.jsp");  
				return;
			}
			
			SearchService ss = BusinessServiceFactory.INSTANCE.getSearchService();
			
            response.setContentType("application/json");
			
			try{
				String term = request.getParameter("name");
				if(!term.equalsIgnoreCase(null) && !term.equalsIgnoreCase(""))
	            {
				System.out.println("Data from ajax call " + term);
							    
				   ArrayList<JournalAtlSearchbean> list = ss.getJournalArticlesNameAutoSearch(term);
			       for(JournalAtlSearchbean user: list){
					System.out.println(user.getJname());
				}       

				String searchList = new Gson().toJson(list);
							
				response.getWriter().write(searchList);  
							
				System.out.println("Json Value@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+searchList);
	            }	
		}catch(Exception e){
			//e.printStackTrace();
		}  		 


		try{
				String term = request.getParameter("title");
				
				if(!term.equalsIgnoreCase(null) && !term.equalsIgnoreCase(""))
	             {
				System.out.println("Data from ajax call " + term);
							    
				   ArrayList<JournalAtlSearchbean> list = ss.getJournalArticlesTitleAutoSearch(term);
			       for(JournalAtlSearchbean user: list){
					System.out.println(user.getAtitle());
				}       

				String searchList = new Gson().toJson(list);
							
				response.getWriter().write(searchList);  
							
				System.out.println("Json Value@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+searchList);
	             }	
		}catch(Exception e){
			//e.printStackTrace();
		}
		
		try{
			String term = request.getParameter("author");
			
			if(!term.equalsIgnoreCase(null) && !term.equalsIgnoreCase(""))
             {
			System.out.println("Data from ajax call " + term);
						    
			   ArrayList<JournalAtlSearchbean> list = ss.getJournalArticlesAuthorAutoSearch(term);
		       for(JournalAtlSearchbean user: list){
				System.out.println(user.getAuthor());
			}       

			String searchList = new Gson().toJson(list);
						
			response.getWriter().write(searchList);  
						
			System.out.println("Json Value@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+searchList);
             }	
	}catch(Exception e){
		//e.printStackTrace();
	}    		 


		try{
			String term = request.getParameter("subject");
			
			if(!term.equalsIgnoreCase(null) && !term.equalsIgnoreCase(""))
             {
			System.out.println("Data from ajax call " + term);
						    
			   ArrayList<JournalAtlSearchbean> list = ss.getJournalArticlesSubjectAutoSearch(term);
		       for(JournalAtlSearchbean user: list){
				System.out.println(user.getAsubject());
			}       

			String searchList = new Gson().toJson(list);
						
			response.getWriter().write(searchList);  
						
			System.out.println("Json Value@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+searchList);
             }	
	}catch(Exception e){
		//e.printStackTrace();
	}
		
		try{
			String term = request.getParameter("abstract");
			
			if(!term.equalsIgnoreCase(null) && !term.equalsIgnoreCase(""))
             {
			System.out.println("Data from ajax call " + term);
						    
			   ArrayList<JournalAtlSearchbean> list = ss.getJournalArticlesAbstractAutoSearch(term);
		       for(JournalAtlSearchbean user: list){
				System.out.println(user.getNabstract());
			}       

			String searchList = new Gson().toJson(list);
						
			response.getWriter().write(searchList);  
						
			System.out.println("Json Value@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+searchList);
             }	
	}catch(Exception e){
		//e.printStackTrace();
	}    		 
		

		try{
			String term = request.getParameter("keywords");
			
			if(!term.equalsIgnoreCase(null) && !term.equalsIgnoreCase(""))
             {
			System.out.println("Data from ajax call " + term);
						    
			   ArrayList<JournalAtlSearchbean> list = ss.getJournalArticlesKeywordsAutoSearch(term);
		       for(JournalAtlSearchbean user: list){
				System.out.println(user.getAkeywords());
			}       

			String searchList = new Gson().toJson(list);
						
			response.getWriter().write(searchList);  
						
			System.out.println("Json Value@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+searchList);
             }	
	}catch(Exception e){
		//e.printStackTrace();
	}    		 

			
			  if (request.getParameter("hid") != null&& request.getParameter("hid").equals("search")) {
			  
				  SQL_Query = "";
				  
				
				if (!Security.getParam(request, "name").equals("")) {					
						
						SQL_Query =	SQL_Query+ " and jnl_name like '%"	+ Security.getParam(request, "name")+ "%'";
				}				  
				  
				if (!Security.getParam(request, "title").equals("")) {					
					
					SQL_Query =	SQL_Query+ " and atl_title like '%"	+ Security.getParam(request, "title")+ "%'";
				}
				
				if (!Security.getParam(request, "author").equals("")) {					
					
					SQL_Query =	SQL_Query+ " and atl_author like '%"	+ Security.getParam(request, "author")+ "%'";
				}				
				
				if (!Security.getParam(request, "altno").equals("")) {					
					
					SQL_Query =	SQL_Query+ " and atl_no like '%"	+ Security.getParam(request, "altno")+ "%'";
				}								
				
				if (!Security.getParam(request, "year").equals("")) {					
					
					SQL_Query =	SQL_Query+ " and issue_year like '%"	+ Security.getParam(request, "year")+ "%'";
				}								
				
				if (!Security.getParam(request, "month").equals("")) {					
					
					SQL_Query =	SQL_Query+ " and issue_month like '%"	+ Security.getParam(request, "month")+ "%'";
				}				
				
				if (!Security.getParam(request, "bvolno").equals("")) {					
					
					SQL_Query =	SQL_Query+ " and bvol_no like '%"	+ Security.getParam(request, "bvolno")+ "%'";
				}								
				
				if (!Security.getParam(request, "volno").equals("")) {					
					
					SQL_Query =	SQL_Query+ " and vol_no like '%"	+ Security.getParam(request, "volno")+ "%'";
				}								
				
				if (!Security.getParam(request, "issueno").equals("")) {					
					
					SQL_Query =	SQL_Query+ " and issue_no like '%"	+ Security.getParam(request, "issueno")+ "%'";
				}
				
				if (!Security.getParam(request, "subject").equals("")) {
					
					SQL_Query =SQL_Query+ " and atl_subject like '%"+ Security.getParam(request, "subject")+ "%'";
				}
				
				if (!Security.getParam(request, "abstract").equals("")) {
					
					SQL_Query =SQL_Query+ " and atl_abstract like '%"+ Security.getParam(request, "abstract")+ "%'";
				}
							
				if (!Security.getParam(request, "keywords").equals("")) {
					
					SQL_Query =SQL_Query+ " and atl_keywords like '%"+ Security.getParam(request, "keywords")+ "%'";
				}	
				
			log4jLogger.info("======================SQL_Query================="+SQL_Query);
				
			SearchArrylist=ss.getJournalArticleSearch(SQL_Query);
		    			
			session.setAttribute("SearchArrylist",SearchArrylist);		     
		 	indexPage = "/Journal_ArticleSearch/jnlAltsimpleSearch.jsp";
		 	dispatch(request, response, indexPage);

		}			  
			  
		if(request.getParameter("ncode")!=null){
			String accno=request.getParameter("ncode");
		    log4jLogger.info("=========== Inside Get Journal Article =========="+accno);
			SQL_Query="and ";
			SQL_Query=SQL_Query+"Atl_No"+" = '"+request.getParameter("ncode")+"'";
			  
			  SearchArrylist=ss.getJournalArticleSearch(SQL_Query);
			 
			request.setAttribute("SearchArrylist",SearchArrylist);
			indexPage = "/Journal_ArticleSearch/fullView.jsp";
			dispatch(request, response, indexPage);
	   }
			 
	   
		} catch (Exception sss) {
			sss.printStackTrace();
			
		}
	}
	
	public void dispatch(HttpServletRequest request,
			HttpServletResponse response, String indexPage)
			throws ServletException, IOException {
		RequestDispatcher dispatch = request.getRequestDispatcher(indexPage);
		dispatch.forward(request, response);
	}
}


