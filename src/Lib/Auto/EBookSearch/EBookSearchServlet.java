package Lib.Auto.EBookSearch;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.google.gson.Gson;

import Common.Security;
import Common.businessutil.BusinessServiceFactory;
import Common.businessutil.search.SearchService;
import Lib.Auto.EBookSearch.EBookSearchBean;

@WebServlet("/EBookSearch/EBookSearchServlet")

public class EBookSearchServlet extends HttpServlet implements Serializable {
	private static Logger log4jLogger = Logger.getLogger(EBookSearchServlet.class);
	String indexPage = null;
	EBookSearchBean ob = null;
	ArrayList<EBookSearchBean> ob1=null;

	List SearchArrylist;
	List EBooksearchArraylist;
	private static final long serialVersionUID = 1L;

	String flag = "",title1="";
	String SQL_Query = "", SQL_Query_Cnt = "",SQL_Query_Con="",SQL_Query_std = "",SQL_Query_cur="",SQL_Query_alpha="";

	public static final String SQLCNT =	"select count(access_no) as cnt from ebook_mas_view where 2>1";
	public static final String SQL_Query_view =	"select  * from ebook_mas_view where 2>1";

	EBookSearchBean bean = new EBookSearchBean();

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
				String term = request.getParameter("title");
				if(!term.equalsIgnoreCase(null) && !term.equalsIgnoreCase(""))
				{
					//System.out.println("Data from ajax call " + term);

					ArrayList<Lib.Auto.EBookSearch.EBookSearchBean> list = ss.getEBookAutoTitleSearch(term);
					for(EBookSearchBean user: list){
						//System.out.println(user.getTitle());
					}       

					String searchList = new Gson().toJson(list);

					response.getWriter().write(searchList);  

					//System.out.println("Json Value@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+searchList);
				}	
			}catch(Exception e){
				//e.printStackTrace();
			}  		 

			try{
				String term = request.getParameter("authorName");
				if(!term.equalsIgnoreCase(null) && !term.equalsIgnoreCase(""))
				{
					//System.out.println("Data from ajax call " + term);

					ArrayList<Lib.Auto.EBookSearch.EBookSearchBean> list = ss.getEBookAutoAuthorSearch(term);
					for(EBookSearchBean user: list){
						//System.out.println(user.getAuthorName());
					}       

					String searchList = new Gson().toJson(list);

					response.getWriter().write(searchList);  

					//System.out.println("Json Value@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+searchList);
				}	
			}catch(Exception e){
				//e.printStackTrace();
			}  		 

			try{
				String term = request.getParameter("subName");
				if(!term.equalsIgnoreCase(null) && !term.equalsIgnoreCase(""))
				{
					//System.out.println("Data from ajax call " + term);

					ArrayList<Lib.Auto.EBookSearch.EBookSearchBean> list = ss.getEBookAutoSubjectSearch(term);
					for(EBookSearchBean user: list){
						//System.out.println(user.getSubName());
					}       

					String searchList = new Gson().toJson(list);

					response.getWriter().write(searchList);  

					//System.out.println("Json Value@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+searchList);
				}	
			}catch(Exception e){
				//e.printStackTrace();
			}  		 
System.out.println("Get Start...!!!");

			if (request.getParameter("hid") != null && request.getParameter("hid").equals("Search")) {
				SQL_Query = "";

				if (!Security.getParam(request, "title").equals("")) {					

					SQL_Query =	SQL_Query+ " and title like '%"	+ Security.getParam(request, "title")+ "%'";
				}
				if (!Security.getParam(request, "callNo").equals("")) {		

					SQL_Query =SQL_Query+ " and call_no like '"	+ Security.getParam(request, "callNo")	+ "%'";
				}

				if (!Security.getParam(request, "pubName").equals("")) {

					SQL_Query =SQL_Query+ " and publisher like '%"+ Security.getParam(request, "pubName")+ "%'";
				}
				
				if (!Security.getParam(request, "supName").equals("")) {

					SQL_Query =SQL_Query+ " and supplier like '%"+ Security.getParam(request, "supName")+ "%'";
				}


				if (!Security.getParam(request, "authorName").equals("")) {

					SQL_Query =SQL_Query+ " and author_name like '%"+ Security.getParam(request, "authorName")+ "%'";
				}

				if (!Security.getParam(request, "subName").equals("")) {

					SQL_Query =SQL_Query+ " and Sub_Name like '%"+ Security.getParam(request, "subName")+ "%'";
				}

				if (!Security.getParam(request, "deptName").equals("")) {

					SQL_Query =SQL_Query+ " and Dept_Name like '%"+ Security.getParam(request, "deptName")+ "%'";
				}

				if (!Security.getParam(request, "yop").equals("")) {

					SQL_Query =SQL_Query+ " and year_pub =  '"+ Security.getParam(request, "yop")+ "'";
				}

				if (!Security.getParam(request, "accessNo").equals("")) {

					SQL_Query =SQL_Query+ " and access_no ='"+ Security.getParam(request, "accessNo")+ "'";
				}

				if(!Security.getParam(request, "edition").equals("")) {

					SQL_Query =SQL_Query+ " and edition like  '%"+ Security.getParam(request, "edition")+ "%'";
				}

				if(!Security.getParam(request, "type").equals("")) {

					SQL_Query =SQL_Query+ " and type like  '%"+ Security.getParam(request, "type")+ "%'";
				}

				if(!Security.getParam(request, "pages").equals("")) {

					SQL_Query =SQL_Query+ " and pages like  '%"+ Security.getParam(request, "pages")+ "%'";
				}
				
				if(!Security.getParam(request, "isbn").equals("")) {

					SQL_Query =SQL_Query+ " and isbn like  '%"+ Security.getParam(request, "isbn")+ "%'";
				}
				
				if(!Security.getParam(request, "keywords").equals("")) {

					SQL_Query =SQL_Query+ " and keywords like  '%"+ Security.getParam(request, "keywords")+ "%'";
				}
				


				bean.setsqlquery(SQL_Query);
				log4jLogger.info("::::::::::::::::Query::::::::::::::::::::"+SQL_Query);
				SearchArrylist=ss.getEBookSearch(SQL_Query);


				session.setAttribute("SearchArrylist",SearchArrylist);

				indexPage = "/EBookSearch/EBookSearch.jsp";
				dispatch(request, response, indexPage);

			}
              System.out.println("<<<<<<<<<<<<<<<<<<<<"+request.getParameter("AccNoSearch"));
			if(request.getParameter("AccNoSearch")!=null){
				log4jLogger.info("start===========ebooksearchaccno====="+request.getParameter("AccNoSearch"));
				System.out.println("accNo"+request.getParameter("AccNoSearch"));

				SQL_Query="and ";
				SQL_Query=SQL_Query+"access_no"+" = '"+request.getParameter("AccNoSearch")+"'";

				EBooksearchArraylist=ss.getEBookSearch(SQL_Query);
				request.setAttribute("SearchArrylist",EBooksearchArraylist);
				indexPage = "/EBookSearch/fullView.jsp";
				dispatch(request, response, indexPage);			   
			}

			if(request.getParameter("flag")!=null)  {
				if(request.getParameter("flag").equals("load")){
					log4jLogger.info("start===========Branch List load=====");
					List BranchArrylist = new ArrayList();
					BranchArrylist=ss.getLoadBranchList();
					request.setAttribute("BranchList", BranchArrylist);
					indexPage = "/EBookSearch/index.jsp";
					dispatch(request, response, indexPage);
				}			  
			}
		} catch (Exception sss) {
			sss.printStackTrace();
		}
	}

	public void dispatch(HttpServletRequest request,
			HttpServletResponse response, String indexPage)
					throws ServletException, IOException {
		System.out.println("iNDEX"+indexPage);
		RequestDispatcher dispatch = request.getRequestDispatcher(indexPage);
		dispatch.forward(request, response);
	}
}


