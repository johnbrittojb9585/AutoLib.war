/*
 * Created on May 8, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package Lib.Auto.Advanced;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;



//import javax.servlet.RequestDispatcher;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.UnavailableException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import org.apache.log4j.Logger;

import Common.LibraryConstants;
import Common.Security;
//import Common.Security;

import Common.businessutil.BusinessServiceFactory;
import Common.businessutil.admin.AdminDaoImpl;
import Common.businessutil.search.SearchService;
import Lib.Auto.Simples.Searchbean;

import javax.servlet.annotation.WebServlet;
/**
 * @author Administrator
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

@WebServlet("/Advanced/AdvancedServlet")

public class Advanced extends HttpServlet implements Serializable{
	private static Logger log4jLogger = Logger.getLogger(Advanced.class);
	/**
	 * 
	 */
	List AdsearchArrylist;
	String indexPage = null;
	String term="";
	private static final long serialVersionUID = 1L;
	String flag = "",flag1="",Title="";
	String SQL_Query = "",title1="",tit="",SQL_Query_Cnt = "",SQL_Query_Con="",SQL_Query_std = "",SQL_Query_cur="",SQL_Query_alpha="";
String OrderQuery="";
String year;
String substr = "";
	int i = 0, j = 0, k = 1, s= 0,len=0;
	int rcount = 0;
	int pcount = 0;
	int top = 0, bottom = 1, txtno = 0;
	int book=0;int non=0;int report=0;int std=0;int thesis=0;int journal=0;int proceed=0;
	int bookbank=0;
	Adsearchbean bean=new Adsearchbean();
	public static final String SQLCNT =
		"select count(access_no) as cnt from full_search where 2>1";
	
	public static final String SQL_Query_view =	"select * from full_search where 2>1 ";


	public void doGet(HttpServletRequest request, HttpServletResponse response) {

		performTask(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) {

		performTask(request, response);

	}
		
	public synchronized void performTask(
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
			
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			SearchService ss = BusinessServiceFactory.INSTANCE.getSearchService();
			
			flag = request.getParameter("flag");
			response.setContentType("application/json");
			flag = request.getParameter("flag");year = request.getParameter("year");
			String commaSeparated = request.getParameter("flag1");
			String commaSeparated1 = request.getParameter("flag2");
			
			try{
				String term = request.getParameter("text1");
				if(!term.equalsIgnoreCase(null) && !term.equalsIgnoreCase("") && !flag.equalsIgnoreCase(""))
	             {
				//System.out.println("Data from ajax call " + term);
							    
				   ArrayList<Searchbean> list = ss.getAdvAutoSearch(term,flag);
			       for(Searchbean user: list){
					//System.out.println(user.getTitle());
				}       

				String searchList = new Gson().toJson(list);
							
				response.getWriter().write(searchList);  
							
				//System.out.println("Json Value@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+searchList);
	             }		
		}catch(Exception e){
			//e.printStackTrace();
		}  	

			flag = request.getParameter("flag");
			try{
				String term = request.getParameter("text2");
				if(!term.equalsIgnoreCase(null) && !term.equalsIgnoreCase("") && !flag.equalsIgnoreCase(""))
	             {
				//System.out.println("Data from ajax call " + term);
							    
				   ArrayList<Searchbean> list = ss.getAdvAutoSearch(term,flag);
			       for(Searchbean user: list){
					//System.out.println(user.getTitle());
				}       

				String searchList = new Gson().toJson(list);
							
				response.getWriter().write(searchList);  
							
				//System.out.println("Json Value@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+searchList);
	             }		
		}catch(Exception e){
			//e.printStackTrace();
		}  	

			flag = request.getParameter("flag");	
			try{
				String term = request.getParameter("text3");
				if(!term.equalsIgnoreCase(null) && !term.equalsIgnoreCase("") && !flag.equalsIgnoreCase(""))
	             {
				//System.out.println("Data from ajax call " + term);
							    
				   ArrayList<Searchbean> list = ss.getAdvAutoSearch(term,flag);
			       for(Searchbean user: list){
					//System.out.println(user.getTitle());
				}       

				String searchList = new Gson().toJson(list);
							
				response.getWriter().write(searchList);  
							
				//System.out.println("Json Value@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+searchList);
	             }		
		}catch(Exception e){
			//e.printStackTrace();
		}  	
			
			Adsearchbean obs = new Adsearchbean();
			//obs.setSearchstring(request.getParameter("text1"));
							
		    if((request.getParameter("hid"))!= null && request.getParameter("hid").equalsIgnoreCase("search")){
			     
		    	log4jLogger.info("start===========adsearch=====");
				   	if( !(request.getParameter("text1")).equalsIgnoreCase(""))
					{				   
				   	obs.setSearchstring(request.getParameter("text1"));	
				   		
				   	SQL_Query="and ";
					SQL_Query=SQL_Query+" "+request.getParameter("LIST1");
				   	tit=request.getParameter("LIST1");
				   	if(tit.equals("Title"))
				   	{
				   		title1=request.getParameter("text1");
				   	}
				   	else
				   	{
				   		title1="";
				   	}
				   	
					if(request.getParameter("condition1").equalsIgnoreCase("="))
					SQL_Query=SQL_Query+" = '"+request.getParameter("text1")+"'";
					else if(request.getParameter("condition1").equalsIgnoreCase("start"))
					SQL_Query=SQL_Query+" like '"+request.getParameter("text1")+"%' ";
					else if(request.getParameter("condition1").equalsIgnoreCase("end"))
					SQL_Query=SQL_Query+" like '%"+request.getParameter("text1")+"'";
					else if(request.getParameter("condition1").equalsIgnoreCase("like"))
					SQL_Query=SQL_Query+" like '%"+request.getParameter("text1")+"%' ";
					else if(request.getParameter("condition1").equalsIgnoreCase("word"))
					SQL_Query=SQL_Query+" like '%"+request.getParameter("text1")+"%'";
					else if(request.getParameter("condition1").equalsIgnoreCase("<"))
					SQL_Query=SQL_Query+" < '"+request.getParameter("text1")+"%'";
					else if(request.getParameter("condition1").equalsIgnoreCase(">"))
					SQL_Query=SQL_Query+" > '"+request.getParameter("text1")+"%'";
					else if(request.getParameter("condition1").equalsIgnoreCase("<="))
					SQL_Query=SQL_Query+" <= '"+request.getParameter("text1")+"%'";
					else if(request.getParameter("condition1").equalsIgnoreCase(">="))
					SQL_Query=SQL_Query+" >= '"+request.getParameter("text1")+"%'";
					
					}


					if( !(request.getParameter("text2")).equalsIgnoreCase(""))
					{
					obs.setSearchstring(request.getParameter("text2"));	
				   
					SQL_Query=SQL_Query+" "+request.getParameter("boolean1")+" "+request.getParameter("LIST2");
					if(request.getParameter("condition2").equalsIgnoreCase("="))
					SQL_Query=SQL_Query+" = '"+request.getParameter("text2")+"'";
					else if(request.getParameter("condition2").equalsIgnoreCase("start"))
					SQL_Query=SQL_Query+" like '"+request.getParameter("text2")+"%' ";
					else if(request.getParameter("condition2").equalsIgnoreCase("end"))
					SQL_Query=SQL_Query+" like '%"+request.getParameter("text2")+"'";
					else if(request.getParameter("condition2").equalsIgnoreCase("like"))
					SQL_Query=SQL_Query+" like '%"+request.getParameter("text2")+"%' ";
					else if(request.getParameter("condition2").equalsIgnoreCase("word"))
					SQL_Query=SQL_Query+" like '%"+request.getParameter("text2")+"%'";
					else if(request.getParameter("condition2").equalsIgnoreCase("<"))
					SQL_Query=SQL_Query+" < '"+request.getParameter("text2")+"%'";
					else if(request.getParameter("condition2").equalsIgnoreCase(">"))
					SQL_Query=SQL_Query+" > '"+request.getParameter("text2")+"%'";
					else if(request.getParameter("condition2").equalsIgnoreCase("<="))
					SQL_Query=SQL_Query+" <= '"+request.getParameter("text2")+"%'";
					else if(request.getParameter("condition2").equalsIgnoreCase(">="))
					SQL_Query=SQL_Query+" >= '"+request.getParameter("text2")+"%'";
					
					}	
					
					if( !(request.getParameter("text3")).equalsIgnoreCase(""))
					{
					obs.setSearchstring(request.getParameter("text3"));						
					
					SQL_Query=SQL_Query+" "+request.getParameter("boolean2")+" "+request.getParameter("LIST3");
					if(request.getParameter("condition3").equalsIgnoreCase("="))
					SQL_Query=SQL_Query+" = '"+request.getParameter("text3")+"'";
					else if(request.getParameter("condition3").equalsIgnoreCase("start"))
					SQL_Query=SQL_Query+" like '"+request.getParameter("text3")+"%' ";
					else if(request.getParameter("condition3").equalsIgnoreCase("end"))
					SQL_Query=SQL_Query+" like '%"+request.getParameter("text3")+"'";
					else if(request.getParameter("condition3").equalsIgnoreCase("like"))
					SQL_Query=SQL_Query+" like '%"+request.getParameter("text3")+"%' ";
					else if(request.getParameter("condition3").equalsIgnoreCase("word"))
					SQL_Query=SQL_Query+" like '%"+request.getParameter("text3")+"%'";
					else if(request.getParameter("condition3").equalsIgnoreCase("<"))
					SQL_Query=SQL_Query+" < '"+request.getParameter("text3")+"%'";
					else if(request.getParameter("condition3").equalsIgnoreCase(">"))
					SQL_Query=SQL_Query+" > '"+request.getParameter("text3")+"%'";
					else if(request.getParameter("condition3").equalsIgnoreCase("<="))
					SQL_Query=SQL_Query+" <= '"+request.getParameter("text3")+"%'";
					else if(request.getParameter("condition3").equalsIgnoreCase(">="))
					SQL_Query=SQL_Query+" >= '"+request.getParameter("text3")+"%'";
					
					}	
					
					System.out.println("YEAR::::::"
							+ Security.getParam(request, "year"));

					if (!Security.getParam(request, "year").equals("")
							&& !Security.getParam(request, "year").equals("ALL")) {
						System.out.println(Security.getParam(request, "year").startsWith("-"));
						System.out.println(Security.getParam(request, "year").endsWith("-"));
						System.out.println(Security.getParam(request, "year").length() == 9);
						System.out.println(Security.getParam(request, "year").length() == 4);
						
						if (Security.getParam(request, "year").startsWith("-")) {
							String str = Security.getParam(request, "year");
							substr = str.substring(1, 5);
							SQL_Query = SQL_Query + " and year_pub < "
									+ substr;
						}else if (Security.getParam(request, "year").endsWith("-")) {
							String str = Security.getParam(request, "year");
							substr = str.substring(0, 4);
							SQL_Query = SQL_Query + " and year_pub > "
									+ substr;
						} else if (Security.getParam(request, "year").length() == 9){
							String str = Security.getParam(request, "year");
							SQL_Query = SQL_Query + " and year_pub between "
									+  str.substring(0, 4)+ " and "+  str.substring(5, 9);
						}
						else if (Security.getParam(request, "year").length() == 4){
							String str = Security.getParam(request, "year");
							SQL_Query = SQL_Query + " and year_pub = '"+ str +"'";
									
						}

					}
					
					if(Security.getParam(request, "content").equals("ALL"))
					{
						SQL_Query = SQL_Query;
					}
					if (Security.getParam(request, "content").equals("conavail"))
					{
						SQL_Query = SQL_Query + " and contents !=  '" + "" + "'";			
					}
					if (Security.getParam(request, "content").equals("connotavail"))
					{
						SQL_Query = SQL_Query + " and contents =  '" + "" + "'";				
					}
					
					System.out.println("::::::SORTING:::::"+ Security.getParam(request, "sort"));
							

					if (!Security.getParam(request, "sort").equals("ALL"))
					{
						OrderQuery = " Order by access_no asc";
					}				
				    if (Security.getParam(request, "sort").equals("subasc"))
					{
				    	OrderQuery = " Order by sub_name asc"; 
					} 
					  if (Security.getParam(request, "sort").equals("subdesc"))
					{
						OrderQuery = " Order by sub_name asc";
					}	
					if (Security.getParam(request, "sort").equals("deptasc"))
					{
					    OrderQuery = " Order by dept_name asc"; 
					} 
					if (Security.getParam(request, "sort").equals("deptdesc"))
					{
						OrderQuery = " Order by dept_name desc";
					}
					if (Security.getParam(request, "sort").equals("authorasc")) {
						OrderQuery = " Order by author_name asc";
					}
					if (Security.getParam(request, "sort").equals("authordesc")) {
						OrderQuery = " Order by author_name desc";
					}
					if (Security.getParam(request, "sort").equals("titleasc")) {
						OrderQuery = " Order by title asc";
					}
					if (Security.getParam(request, "sort").equals("titledesc")) {
						OrderQuery = " Order by title desc";
					}
					if (Security.getParam(request, "sort").equals("dateasc")) {
						OrderQuery = " Order by received_date asc";
					}
					if (Security.getParam(request, "sort").equals("datedesc")) {
						OrderQuery = " Order by received_date desc";
					}

					commaSeparated = commaSeparated.replaceFirst(",", "");
					if (commaSeparated.equalsIgnoreCase(null) || commaSeparated.equalsIgnoreCase(""))
					{
						commaSeparated = "";
						SQL_Query = SQL_Query;
					}
					else
					{
						commaSeparated = commaSeparated.replaceAll(",", "','");
						SQL_Query = SQL_Query + " and document in  ('" + commaSeparated + "')";
					}
					
					commaSeparated1 = commaSeparated1.replaceFirst(",", "");
					if (commaSeparated1.equalsIgnoreCase(null) || commaSeparated1.equalsIgnoreCase(""))
					{
						commaSeparated1 = "";
						SQL_Query = SQL_Query;
					}
					else
					{
						commaSeparated1= commaSeparated1.replaceAll(",", "','");
						SQL_Query = SQL_Query + " and availability in  ('" + commaSeparated1 + "')";
					}

					bean.setsqlquery(SQL_Query);

					System.out.println("::::::<<<<<QUERY_SERVLET>>>>:::::"+ SQL_Query);
				
					
					AdsearchArrylist=ss.getAdvancedSearch(SQL_Query, "ALL", "", OrderQuery);
					
					session.setAttribute("AdsearchArrylist",AdsearchArrylist);
					indexPage = "/Advanced/advancedSearch.jsp";
				 	dispatch(request, response, indexPage);
				 	
			}	

		    
		    if(request.getParameter("doc")!=null){
		    	log4jLogger.info("start===========adsearchdoc=====");
		    	String document = request.getParameter("doc");
		    	
		    	String query="", format = "";		    	
		  
		    	query=bean.getsqlquery();
		    	
		    	SQL_Query=query + " and document=";
		    	
		    	SQL_Query=SQL_Query+"'"+request.getParameter("doc")+ "'";
		    	
		    	if (request.getParameter("format") != null) {
					format = request.getParameter("format");
				}
		    	
					AdsearchArrylist=ss.getAdvancedSearch(SQL_Query,"ALL","",OrderQuery);
					
					session.setAttribute("AdsearchArrylist",AdsearchArrylist);
					indexPage = "/Advanced/advancedSearch.jsp";
				 	dispatch(request, response, indexPage);
				 	
				
			}	

		    
		    if(request.getParameter("doc")!=null){
		    	log4jLogger.info("start===========adsearchdoc=====");
		    	
		    	String document = request.getParameter("doc");
		    	
		    	String query="",format="";
		    	
		  
		    	query=bean.getsqlquery();
		    	
		    	SQL_Query=query+"and document=";
		    	
		    	SQL_Query=SQL_Query+"'"+request.getParameter("doc")+ "'";
		    	
		    	if(request.getParameter("format")!=null)
		    	{
		    		format = request.getParameter("format");
		    	}
		    	
		    	AdsearchArrylist=ss.getAdvancedSearch(SQL_Query,document,format,OrderQuery);
		    				
				session.setAttribute("AdsearchArrylist",AdsearchArrylist);
				indexPage = "/Advanced/advancedSearch.jsp";
			 	dispatch(request, response, indexPage);
		    	
		    }
		   		    
		
		   if(request.getParameter("accno")!=null){
		    	log4jLogger.info("start===========adsearchaccno=====");
			   String accno=request.getParameter("accno");

			   SQL_Query="and ";
			   SQL_Query=SQL_Query+"access_no"+" = '"+request.getParameter("accno")+"'";
			   
			   
			   AdsearchArrylist=ss.getFullView(SQL_Query,request.getParameter("document"));
			   
			   
			   //session.setAttribute("AdsearchArrylist",AdsearchArrylist);
			   request.setAttribute("AdsearchArrylist",AdsearchArrylist);
			   indexPage = "/Advanced/fullView.jsp";
			   dispatch(request, response, indexPage);
			   
			   
		   }
		    
	
			
		} catch (Exception e) {e.printStackTrace();}
		finally {
			SQL_Query = "";
			SQL_Query_Cnt = "";
			
			bottom = 1;
			book=0;
			bookbank=0;
			std=0;
			proceed=0;
			report=0;
			thesis=0;
			journal=0;
			non=0;
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
