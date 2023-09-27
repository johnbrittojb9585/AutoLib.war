package Lib.Auto.NewArrivals;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import Common.businessutil.BusinessServiceFactory;
import Common.businessutil.search.SearchService;

import java.util.ArrayList;




public class NewArrivals extends HttpServlet implements Serializable {

	private static final long serialVersionUID = 1L;
	
	String flag="";
	String indexPage = null;
	
	private static Logger log4jLogger = Logger.getLogger(NewArrivals.class);
	 
	 public void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException {
			performTask(request, response);
		}

		public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException {
			performTask(request, response);
		}
		public void performTask(
			HttpServletRequest request,
			HttpServletResponse response)throws ServletException {
			
			try {
				
					
				flag = request.getParameter("flag");	
				
				log4jLogger.info("start===========findMonthYearList=====");
				NewArrivalsBean ob=new NewArrivalsBean();
				SearchService ss = BusinessServiceFactory.INSTANCE.getSearchService();
			
				if(flag.equals("loadMonthYear")){
				ob.setDocument("BOOK");
				
				List AuthorArrylist = new ArrayList();
				
				AuthorArrylist=ss.getMonthYearList(ob);
				request.setAttribute("monthYear", AuthorArrylist);
			
				
				List subjectList = ss.getSubjectList();				
				request.setAttribute("SubjectSearchList", subjectList);
				
				
				indexPage = "/newarrivals/index.jsp";
				dispatch(request, response, indexPage);
				
				}
				
				if(flag.equals("Search")){

					
					NewArrivalsBean bean = new NewArrivalsBean();
					
					
					if(!request.getParameter("docType").equalsIgnoreCase("ALL"))
						bean.setDocument(request.getParameter("docType").trim());
					
					
					if(!request.getParameter("Year").equalsIgnoreCase("ALL"))
						bean.setReceivedDate(request.getParameter("Year").trim());
					
					if(!request.getParameter("Subject").equalsIgnoreCase("ALL"))
						bean.setSubjectName(request.getParameter("Subject").trim());
						bean.setKeyword1(request.getParameter("title").trim());
						bean.setKeyword2(request.getParameter("author").trim());
					
					List<?> NewArrivalDisply = ss.getNewArrivalSearchResult(bean);		
							
					request.setAttribute("NewArrivalDisply", NewArrivalDisply);
				    request.setAttribute("NewArrivalDisplySize", NewArrivalDisply.size());
					request.setAttribute("bean",ob);
					
					indexPage = "/newarrivals/searchresult.jsp";
					dispatch(request, response, indexPage);

					
				}
				 }
			
			
	
			catch (Exception sss) {
				throw new ServletException(sss);
					}
		}

		public void dispatch(
				HttpServletRequest request,
				HttpServletResponse response,
				String indexPage)
				throws ServletException, IOException {
				RequestDispatcher dispatch = request.getRequestDispatcher(indexPage);
				dispatch.forward(request, response);
			}
	 
}
	
