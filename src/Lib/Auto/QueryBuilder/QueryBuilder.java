package Lib.Auto.QueryBuilder;

import java.io.IOException;
import java.io.PrintWriter;
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

import Common.Security;
import Common.businessutil.BusinessServiceFactory;
import Common.businessutil.search.SearchService;
import Lib.Auto.Simples.Searchbean;

import com.google.gson.Gson;

@WebServlet("/QueryBuilder/QueryBuildServlet")

public class QueryBuilder extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Logger log4jLogger = Logger.getLogger(QueryBuilder.class);
	String term="";
	String flag = "",flag1="";

	public void doGet(HttpServletRequest request, HttpServletResponse response) {

		performTask(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) {

		performTask(request, response);

	}

	public synchronized void performTask(HttpServletRequest request,
			HttpServletResponse response) {

		try {

			HttpSession session = request.getSession(true);
			String res = Security.checkSecurity(1, session, response, request);
			if (res.equalsIgnoreCase("Failure")) {
				response.sendRedirect("/AutoLib/Tree/sessiontimeout.jsp");
				return;
			}

			String SQL_Query = "", tit = "", title1 = "", orderBy = "", indexPage;
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			SearchService ss = BusinessServiceFactory.INSTANCE.getSearchService();
			
			flag = request.getParameter("flag");
			response.setContentType("application/json");

			try{
				String term = request.getParameter("text1");
				if(!term.equalsIgnoreCase(null) && !term.equalsIgnoreCase("") && !flag.equalsIgnoreCase(""))
	             {
				System.out.println("Data from ajax call " + term);
							    
				   ArrayList<Searchbean> list = ss.getQueryAutoSearch(term,flag);
			       for(Searchbean user: list){
					System.out.println(user.getTitle());
				}       

				String searchList = new Gson().toJson(list);
							
				response.getWriter().write(searchList);  
							
				System.out.println("Json Value@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+searchList);
	             }		
		}catch(Exception e){
			//e.printStackTrace();
		}  	


			try{
				String term = request.getParameter("text2");
				if(!term.equalsIgnoreCase(null) && !term.equalsIgnoreCase("") && !flag.equalsIgnoreCase(""))
	             {
				System.out.println("Data from ajax call " + term);
							    
				   ArrayList<Searchbean> list = ss.getQueryAutoSearch(term,flag);
			       for(Searchbean user: list){
					System.out.println(user.getTitle());
				}       

				String searchList = new Gson().toJson(list);
							
				response.getWriter().write(searchList);  
							
				System.out.println("Json Value@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+searchList);
	             }		
		}catch(Exception e){
			//e.printStackTrace();
		}  	

			if(flag.equalsIgnoreCase("load"))
			{
				System.out.println("Inside flag");
				indexPage = "/QueryBuilder/index.jsp";
				dispatch(request, response, indexPage);
			}
			try{
				String term = request.getParameter("text3");
				if(!term.equalsIgnoreCase(null) && !term.equalsIgnoreCase("") && !flag.equalsIgnoreCase(""))
	             {
				System.out.println("Data from ajax call " + term);
							    
				   ArrayList<Searchbean> list = ss.getQueryAutoSearch(term,flag);
			       for(Searchbean user: list){
					System.out.println(user.getTitle());
				}       

				String searchList = new Gson().toJson(list);
							
				response.getWriter().write(searchList);  
							
				System.out.println("Json Value@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+searchList);
	             }		
		}catch(Exception e){
			//e.printStackTrace();
		}  	


			if ((request.getParameter("hid")) != null && request.getParameter("hid").equalsIgnoreCase("search"))
			
			{

				log4jLogger.info("start=========== Query Builder Search =====");
				
				if (!(request.getParameter("text1")).equalsIgnoreCase("")) {
					
					SQL_Query = "and ";
					SQL_Query = SQL_Query +  request.getParameter("LIST1");

					tit = request.getParameter("LIST1");
					if (tit.equals("Title")) 
					{
						title1 = request.getParameter("text1");
					}
					else
					{
						title1 = "";
					}

					if (request.getParameter("condition1").equalsIgnoreCase("="))
							
						SQL_Query = SQL_Query + " = '"+ request.getParameter("text1") + "'";
								
					else if (request.getParameter("condition1").equalsIgnoreCase("start"))
							
						SQL_Query = SQL_Query + " like '"+ request.getParameter("text1") + "%' ";
								
					else if (request.getParameter("condition1").equalsIgnoreCase("end"))
							
						SQL_Query = SQL_Query + " like '%"+ request.getParameter("text1") + "'";
								
					else if (request.getParameter("condition1").equalsIgnoreCase("like"))
							
						SQL_Query = SQL_Query + " like '%"+ request.getParameter("text1") + "%' ";
								
					else if (request.getParameter("condition1").equalsIgnoreCase("word"))
							
						SQL_Query = SQL_Query + " like '%"+ request.getParameter("text1") + "%'";
					
					else if(request.getParameter("condition1").equalsIgnoreCase("<"))
						
						SQL_Query=SQL_Query+" < '"+request.getParameter("text1")+"%'";
					
					else if(request.getParameter("condition1").equalsIgnoreCase(">"))
							
						SQL_Query=SQL_Query+" > '"+request.getParameter("text1")+"%'";
					
					else if(request.getParameter("condition1").equalsIgnoreCase("<="))
							
						SQL_Query=SQL_Query+" <= '"+request.getParameter("text1")+"%'";
					
					else if(request.getParameter("condition1").equalsIgnoreCase(">="))
							
						SQL_Query=SQL_Query+" >= '"+request.getParameter("text1")+"%'";
					
				}

				if (!(request.getParameter("text2")).equalsIgnoreCase(""))
				{
					SQL_Query = SQL_Query + " "+ request.getParameter("boolean1") + " "+ request.getParameter("LIST2");
							
							
					if (request.getParameter("condition2").equalsIgnoreCase("="))
							
						SQL_Query = SQL_Query + " = '"+ request.getParameter("text2") + "'";
								
					else if (request.getParameter("condition2").equalsIgnoreCase("start"))
							
						SQL_Query = SQL_Query + " like '"+ request.getParameter("text2") + "%' ";
								
					else if (request.getParameter("condition2").equalsIgnoreCase("end"))
							
						SQL_Query = SQL_Query + " like '%"+ request.getParameter("text2") + "'";
							
					else if (request.getParameter("condition2").equalsIgnoreCase("like"))
							
						SQL_Query = SQL_Query + " like '%"+ request.getParameter("text2") + "%' ";
								
					else if (request.getParameter("condition2").equalsIgnoreCase("word"))
							
						SQL_Query = SQL_Query + " like '%"+ request.getParameter("text2") + "%'";
					else if(request.getParameter("condition2").equalsIgnoreCase("<"))
						SQL_Query=SQL_Query+" < '"+request.getParameter("text2")+"%'";
						else if(request.getParameter("condition2").equalsIgnoreCase(">"))
						SQL_Query=SQL_Query+" > '"+request.getParameter("text2")+"%'";
						else if(request.getParameter("condition2").equalsIgnoreCase("<="))
						SQL_Query=SQL_Query+" <= '"+request.getParameter("text2")+"%'";
						else if(request.getParameter("condition2").equalsIgnoreCase(">="))
						SQL_Query=SQL_Query+" >= '"+request.getParameter("text2")+"%'";

				}

				if (!(request.getParameter("text3")).equalsIgnoreCase("")) {

					SQL_Query = SQL_Query + " "+ request.getParameter("boolean2") + " "+ request.getParameter("LIST3");
							
							
					if (request.getParameter("condition3").equalsIgnoreCase("="))
							
						SQL_Query = SQL_Query + " = '"+ request.getParameter("text3") + "'";
								
					else if (request.getParameter("condition3").equalsIgnoreCase("start"))
							
						SQL_Query = SQL_Query + " like '"+ request.getParameter("text3") + "%' ";
								
					else if (request.getParameter("condition3").equalsIgnoreCase("end"))
							
						SQL_Query = SQL_Query + " like '%"+ request.getParameter("text3") + "'";
								
					else if (request.getParameter("condition3").equalsIgnoreCase("like"))
							
						SQL_Query = SQL_Query + " like '%"+ request.getParameter("text3") + "%' ";
								
					else if (request.getParameter("condition3").equalsIgnoreCase("word"))
							
						SQL_Query = SQL_Query + " like '%"+ request.getParameter("text3") + "%'";
					
					else if(request.getParameter("condition3").equalsIgnoreCase("<"))
						
						SQL_Query=SQL_Query+" < '"+request.getParameter("text3")+"%'";
					
					else if(request.getParameter("condition3").equalsIgnoreCase(">"))
					
						SQL_Query=SQL_Query+" > '"+request.getParameter("text3")+"%'";
					
					else if(request.getParameter("condition3").equalsIgnoreCase("<="))
					
						SQL_Query=SQL_Query+" <= '"+request.getParameter("text3")+"%'";
					
					else if(request.getParameter("condition3").equalsIgnoreCase(">="))
					
						SQL_Query=SQL_Query+" >= '"+request.getParameter("text3")+"%'";
								
				}
				
				SQL_Query = SQL_Query ;

				
				String valOrder[] = request.getParameterValues("orderBy");			
				
				if( valOrder != null && valOrder.length > 0 )
				{			
					StringBuffer txtTemp = new StringBuffer();									
					for(int i=0; i < valOrder.length; i++)
					{
						txtTemp.append(valOrder[i]);
						
						if( i != (valOrder.length-1) )
						txtTemp.append(", ");
					}
					orderBy = " Order By "+txtTemp.toString();
				}				
				
				String commaSeparatedQuery = request.getParameter("flag1");
				String commaSeparated = commaSeparatedQuery.startsWith(",") ? commaSeparatedQuery.replaceFirst(",", "") : commaSeparatedQuery;
				String[] items = commaSeparated.split(",");
				
				StringBuffer itemsb = new StringBuffer();
				itemsb.append("<th><font face=verdana size=2>S.No</font></th>");
				for(String item : items){
					itemsb.append("<th><font face=verdana size=2>"+item+"</font></th>");
				}
				
				response.setContentType("text/html");
           	 out.print("<br><center><b><font face=verdana size=4>Query&nbsp;Builder</font></b></center>");
           	 out.print("<br><b><a href=QueryBuildServlet?flag=load>Back</a></b>");           	 
             out.print("<br><table border=1 width=80% align=center CELLSPACING='0' BORDERCOLOR='#DADADA' BGCOLOR='#F5F5F5'> ");
//        	 out.print("<th><font face=verdana size=2>S.No</font></th><th><font face=verdana size=2>Access&nbsp;No</font></th><th><font face=verdana size=2>Title</font></th><th><font face=verdana size=2>Author</font></th><th><font face=verdana size=2>Publisher</font></th><th><font face=verdana size=2>Year</font></th><th><font face=verdana size=2>Document</font></th><th><font face=verdana size=2>BPrice</font></th><th><font face=verdana size=2>Availability</font></th>");
             out.print(itemsb);              //header Print  
                
                //After Header Display

				StringBuffer sb = new StringBuffer();
				sb.append("select 0" + commaSeparatedQuery + " FROM full_search where 2>1 " + SQL_Query + orderBy);			
				QueryBuilderBean bean = new QueryBuilderBean();
				bean.setQueryText(sb.toString());
				bean.setListColumn(commaSeparatedQuery.split(","));		
				List<QueryBuilderBean> arySearchResult = ss.getQueryBuilderSearch(bean);
				System.out.println("::::SIZE::::"+arySearchResult.size());
				
				for(int k=0;k < arySearchResult.size();k++){
					
					
					QueryBuilderBean qb = arySearchResult.get(k);
					
					out.print("<tr><td><font face=verdana size=2>"+(k+1)+"</font></td>");
					
					for(String item : items){
						if(item.equalsIgnoreCase("access_no"))
							out.print("<td><font face=verdana size=2>"+qb.getAccessNo()+"</font></td>");
						else if(item.equalsIgnoreCase("author_name"))
							out.print("<td><font face=verdana size=2>"+qb.getAuthorName()+"</font></td>");
						else if(item.equalsIgnoreCase("title"))
							out.print("<td><font face=verdana size=2>"+qb.getTitle()+"</font></td>");
						else if(item.equalsIgnoreCase("call_no"))
							out.print("<td><font face=verdana size=2>"+qb.getCallNo()+"</font></td>");
						else if(item.equalsIgnoreCase("edition"))
							out.print("<td><font face=verdana size=2>"+qb.getEdition()+"</font></td>");
						else if(item.equalsIgnoreCase("year_pub"))
							out.print("<td><font face=verdana size=2>"+qb.getYear()+"</font></td>");
						else if(item.equalsIgnoreCase("isbn"))
							out.print("<td><font face=verdana size=2>"+qb.getIsbn()+"</font></td>");
						else if(item.equalsIgnoreCase("bprice"))
							out.print("<td><font face=verdana size=2>"+qb.getPrice()+"</font></td>");
						else if(item.equalsIgnoreCase("net_price"))
							out.print("<td><font face=verdana size=2>"+qb.getNetPrice()+"</font></td>");
						else if(item.equalsIgnoreCase("received_date"))
							out.print("<td><font face=verdana size=2>"+qb.getReceivedDate()+"</font></td>");
						else if(item.equalsIgnoreCase("invoice_no"))
							out.print("<td><font face=verdana size=2>"+qb.getInvoiceNo()+"</font></td>");
						else if(item.equalsIgnoreCase("invoice_date"))
							out.print("<td><font face=verdana size=2>"+qb.getInvoiceDate()+"</font></td>");
						else if(item.equalsIgnoreCase("dept_name"))
							out.print("<td><font face=verdana size=2>"+qb.getDepartment()+"</font></td>");
						else if(item.equalsIgnoreCase("sub_name"))
							out.print("<td><font face=verdana size=2>"+qb.getSubject()+"</font></td>");
						else if(item.equalsIgnoreCase("sp_name"))
							out.print("<td><font face=verdana size=2>"+qb.getPublisher()+"</font></td>");
						else if(item.equalsIgnoreCase("supplier"))
							out.print("<td><font face=verdana size=2>"+qb.getSupplier()+"</font></td>");
						else if(item.equalsIgnoreCase("availability"))
							out.print("<td><font face=verdana size=2>"+qb.getAvailability()+"</font></td>");
						else if(item.equalsIgnoreCase("document"))
							out.print("<td><font face=verdana size=2>"+qb.getDocument()+"</font></td>");
						else if(item.equalsIgnoreCase("language"))
							out.print("<td><font face=verdana size=2>"+qb.getLanguage()+"</font></td>");
						else if(item.equalsIgnoreCase("location"))
							out.print("<td><font face=verdana size=2>"+qb.getLocation()+"</font></td>");
						else if(item.equalsIgnoreCase("keywords"))
							out.print("<td><font face=verdana size=2>"+qb.getKeywords()+"</font></td>");
						else if(item.equalsIgnoreCase("volno"))
							out.print("<td><font face=verdana size=2>"+qb.getVolumeNo()+"</font></td>");
						else if(item.equalsIgnoreCase("purchase"))
							out.print("<td><font face=verdana size=2>"+qb.getPurchase()+"</font></td>");
						else if(item.equalsIgnoreCase("pages"))
							out.print("<td><font face=verdana size=2>"+qb.getPages()+"</font></td>");
						else 
							out.print("<td><font face=verdana size=2></font></td>");
					}
					
           			out.print("</tr>");	
				}

       		   	out.print("</table>");
//
//				request.setAttribute("searchResult", arySearchResult);
//				indexPage = "/QueryBuilder/searchPage.jsp";
//				dispatch(request, response, indexPage);

			}

		} catch (Exception e) 
		{
			e.printStackTrace();
		}

	}

	public void dispatch(HttpServletRequest request,
			HttpServletResponse response, String indexPage)
			throws ServletException, IOException {
		RequestDispatcher dispatch = request.getRequestDispatcher(indexPage);
		dispatch.forward(request, response);
	}

}
