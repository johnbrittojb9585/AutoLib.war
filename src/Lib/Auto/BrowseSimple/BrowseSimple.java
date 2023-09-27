/*
 * Created on Apr 27, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package Lib.Auto.BrowseSimple;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.UnavailableException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Common.Security;

import Common.connectionpool;
import Common.LibraryConstants;
import Common.businessutil.BusinessServiceFactory;
import Common.businessutil.search.SearchService;
public class BrowseSimple extends HttpServlet implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	List BrowseArrylist;
	String indexPage = null;
	
	String flag = "",title1="";
	String SQL_Query = "", SQL_Query_Cnt = "",SQL_Query_Con="",SQL_Query_std = "",SQL_Query_cur="",SQL_Query_alpha="";

	int i = 0, j = 0, k = 1, s= 0,len=0;
	int rcount = 0;
	int pcount = 0;
	int top = 0, bottom = 1, txtno = 0;
	int book=0;int non=0;int report=0;int std=0;int thesis=0;int journal=0;int proceed=0;
	int bookbank=0;
	public static final String SQLCNT ="select count(access_no) as cnt from full_search where 2>1";	
	public static final String SQL_Query_view =	"select  * from full_search where 2>1";
	public static final String CD_VIEW ="select * from full_search where  remarks like '%+CD%' and access_no=?";
	connectionpool cp=null;
	
		
	public void init() throws ServletException {
		

	}

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
			
			
		
			
		   if (request.getParameter("hid") != null&& request.getParameter("hid").equals("search")) {
			  
				if (!Security.getParam(request, "Title").equals("")) {
					
					
					SQL_Query =
						SQL_Query
							+ " and title like '%"
							+ Security.getParam(request, "Title")
							+ "%'";
				}

			    	
				             if (!Security.getParam(request, "Publisher").equals("")) {
					
					SQL_Query =
						SQL_Query
							+ " and publisher like '%"
							+ Security.getParam(request, "Publisher")
							+ "%'";
				}
				
				
				
				if (!Security.getParam(request, "Keywords").equals("")) {
					
					SQL_Query =
						SQL_Query
							+ " and keywords like '%"
							+ Security.getParam(request, "Keywords")
							+ "%'";
				}
				
				
				if (!Security.getParam(request, "Author").equals("")) {
					
					SQL_Query =
						SQL_Query
							+ " and author like '%"
							+ Security.getParam(request, "Author")
							+ "%'";
				}
				
				if (!Security.getParam(request, "Subject").equals("")) {
					
					SQL_Query =
						SQL_Query
							+ " and subject like '%"
							+ Security.getParam(request, "subject")
							+ "%'";
				}
				
				
				if (!Security.getParam(request, "Year").equals("")) {
					
					SQL_Query =
						SQL_Query
							+ " and year like  '%"
							+ Security.getParam(request, "Year")
							+ "%'";
				}
							
				if (!Security.getParam(request, "accno").equals("")) {
					
					SQL_Query =
						SQL_Query
							+ " and Access_No ='"
							+ Security.getParam(request, "accno")
							+ "'";
				}
				
				
				if(!Security.getParam(request, "isbn").equals("")) {
					
					SQL_Query =
						SQL_Query
							+ " and isbn like  '%"
							+ Security.getParam(request, "isbn")
							+ "%'";
				}
				s=1;
				
				
				
								   
				   BrowseArrylist=ss.getBrowseSearch(SQL_Query,"All","");
					
					session.setAttribute("BrowseArrylist",BrowseArrylist);
					indexPage = "/Advanced/advancedSearch.jsp";
				 	dispatch(request, response, indexPage);
				
			}
		   
		   
		   
		   
		   
		   
		} catch (Exception sss) {
			sss.printStackTrace();
			
		}

		

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

