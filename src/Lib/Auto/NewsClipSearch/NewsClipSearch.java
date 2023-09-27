/*
 * Created on Apr 27, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package Lib.Auto.NewsClipSearch;

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
import Lib.Auto.Simples.Searchbean;
import Lib.Auto.Newsclliping.NewscllipingBean;

import javax.servlet.annotation.WebServlet;
import com.google.gson.Gson;

@WebServlet("/NewsClipSearch/NewsClipSearchServlet")

public class NewsClipSearch extends HttpServlet implements Serializable {
	private static Logger log4jLogger = Logger.getLogger(NewsClipSearch.class);
	String indexPage = null;
	/**
	 * 
	 */
	List SearchArrylist;
	List AdsearchArrylist;
	private static final long serialVersionUID = 1L;
		
	String flag = "",title1="";
	String SQL_Query = "", SQL_Query_Cnt = "",SQL_Query_Con="",SQL_Query_std = "",SQL_Query_cur="",SQL_Query_alpha="";
	
	public static final String SQLCNT =	"select count(access_no) as cnt from full_search where 2>1";	
	public static final String SQL_Query_view =	"select  * from full_search where 2>1";
	public static final String CD_VIEW ="select * from full_search where  remarks like '%+CD%' and access_no=?";
	
	NewsSearchbean bean=new NewsSearchbean();
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
							    
				   ArrayList<NewscllipingBean> list = ss.getNewsclippingNameAutoSearch(term);
			       for(NewscllipingBean user: list){
					System.out.println(user.getNname());
				}       

				String searchList = new Gson().toJson(list);
							
				response.getWriter().write(searchList);  
							
				System.out.println("Json Value@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+searchList);
	            }	
		}catch(Exception e){
			//e.printStackTrace();
		}  		 


		try{
				String term = request.getParameter("type");
				
				if(!term.equalsIgnoreCase(null) && !term.equalsIgnoreCase(""))
	             {
				System.out.println("Data from ajax call " + term);
							    
				   ArrayList<NewscllipingBean> list = ss.getNewsclippingTypeAutoSearch(term);
			       for(NewscllipingBean user: list){
					System.out.println(user.getNtype());
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
						    
			   ArrayList<NewscllipingBean> list = ss.getNewsclippingTitleAutoSearch(term);
		       for(NewscllipingBean user: list){
				System.out.println(user.getNtitle());
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
						    
			   ArrayList<NewscllipingBean> list = ss.getNewsclippingSubjectAutoSearch(term);
		       for(NewscllipingBean user: list){
				System.out.println(user.getNsubject());
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
						    
			   ArrayList<NewscllipingBean> list = ss.getNewsclippingKeywordsAutoSearch(term);
		       for(NewscllipingBean user: list){
				System.out.println(user.getNkey());
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
				  
				if (!Security.getParam(request, "title").equals("")) {					
					
					SQL_Query =	SQL_Query+ " and newspaper_title like '%"	+ Security.getParam(request, "title")+ "%'";
				}
				
				if (!Security.getParam(request, "name").equals("")) {					
					
					SQL_Query =	SQL_Query+ " and Newspaper_name like '%"	+ Security.getParam(request, "name")+ "%'";
				}
				
				
				if (!Security.getParam(request, "keywords").equals("")) {
					
					SQL_Query =SQL_Query+ " and newspaper_key like '%"+ Security.getParam(request, "keywords")+ "%'";
				}
				
				
				
				if (!Security.getParam(request, "subject").equals("")) {
					
					SQL_Query =SQL_Query+ " and newspaper_subject like '%"+ Security.getParam(request, "subject")+ "%'";
				}
				
				if (!Security.getParam(request, "abstract").equals("")) {
					
					SQL_Query =SQL_Query+ " and newspaper_abstract like '%"+ Security.getParam(request, "abstract")+ "%'";
				}
							
				if (!Security.getParam(request, "type").equals("")) {
					
					SQL_Query =SQL_Query+ " and newspaper_type like '%"+ Security.getParam(request, "type")+ "%'";
				}
				
				
				
				bean.setSqlquery(SQL_Query);
				
			 SearchArrylist=ss.getNewsClipSimpleSearch(SQL_Query);
    		 session.setAttribute("SearchArrylist",SearchArrylist);
		     
		 	indexPage = "/NewsClipSearch/newssimpleSearch.jsp";
		 	dispatch(request, response, indexPage);

			  }
			  
			  
			  if(request.getParameter("ncode")!=null){
				  
				  String accno=request.getParameter("ncode");

				   SQL_Query="and ";
				   SQL_Query=SQL_Query+"Newspaper_code"+" = '"+request.getParameter("ncode")+"'";
				  
				  
				  
				  SearchArrylist=ss.getNewsClipSimpleSearch(SQL_Query);
				  
				  request.setAttribute("SearchArrylist",SearchArrylist);
				   indexPage = "/NewsClipSearch/fullView.jsp";
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


