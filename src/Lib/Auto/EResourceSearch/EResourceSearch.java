/*
 * Created on Apr 27, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package Lib.Auto.EResourceSearch;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
import java.io.IOException;
import java.io.Serializable;
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

public class EResourceSearch extends HttpServlet implements Serializable {
	private static Logger log4jLogger = Logger.getLogger(EResourceSearch.class);
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
			
			
			flag = request.getParameter("flag");
			
						
			if (request.getParameter("flag")!=null) {
				
				
				
				SQL_Query="and ";
				   SQL_Query=SQL_Query+"webtype"+" = '"+request.getParameter("type")+"'";
				  
				SearchArrylist=ss.getEResourceSimpleSearch(SQL_Query);
			
				request.setAttribute("SearchArrylist",SearchArrylist);
		 
		 	    indexPage = "/EResourceSearch/fullView.jsp";
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


