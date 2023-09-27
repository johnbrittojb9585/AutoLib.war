package Lib.Auto.Suggestion;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import javax.swing.JOptionPane;

import org.apache.log4j.Logger;

import Common.DataQuery;
import Common.Security;
import Common.businessutil.BusinessServiceFactory;
import Common.businessutil.admin.AdminService;
import Common.businessutil.calaloging.CalalogingService;
import Common.businessutil.mail.MailService;
import Lib.Auto.Author.Author;
import Lib.Auto.Author.AuthorBean;
import Lib.Auto.Review.ReviewBean;
import Lib.Auto.Suggestion.SuggestionBean;
import Lib.Auto.Advanced.Adsearchbean;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Serializable;
import java.sql.*;
import java.util.*;


public class SuggestionDisplayServlet extends HttpServlet implements Serializable{
	 private static Logger log4jLogger = Logger.getLogger(SuggestionDisplayServlet.class);
		private static final long serialVersionUID = -8906987252709033668L;
		//String SQL_Query = "";
		String indexPage = null;
		String flag = "";
		String rdate= "";
		String AccessNo="";
		String frmdt="";
		String todt="";
		String Final_query="";
		String NamedQuery="";
		int  mailcount=0;
		java.sql.Connection con = null;
		StringBuffer sb=new StringBuffer();
		//public static final String SUGGESTIONDISPLAY_NAME = "select request_no,member_code,request_for,request_date,request_details,remarks from suggestion_mas where 2>1";

								
		public void doGet(HttpServletRequest request, HttpServletResponse response)
				throws ServletException,IOException {

			performTask(request, response);

		}

		public void doPost(HttpServletRequest request, HttpServletResponse response)
				throws ServletException,IOException {
		 
			performTask(request, response);

		}

    public void performTask(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, java.io.IOException {
    	        
        try {
        	HttpSession session = request.getSession(true);
    		String res = Security.checkSecurity(1, session, response, request);		
    		if(res.equalsIgnoreCase("Failure"))
    		{
    			response.sendRedirect("/AutoLib/Tree/sessiontimeout.jsp");  
    			return;
    		}
    		
    		PrintWriter out = response.getWriter();
    		CalalogingService ss = BusinessServiceFactory.INSTANCE.getCalalogingService();    		
    		AdminService ss1 = BusinessServiceFactory.INSTANCE.getAdminService();
    		MailService ss2 = BusinessServiceFactory.INSTANCE.getMailService();
    		    		
    		flag=request.getParameter("flag");
    		rdate=request.getParameter("rdate");
    		NamedQuery=DataQuery.SUGGESTIONDISPLAY_NAME;
    		log4jLogger.info("=================inside SUGGESTION=================="+flag);
    		
    		String	filterQuery="";
    		if (flag.equals("delete")) {		
				log4jLogger.info("start===========deletecheck=====");
				Object	ob = new SuggestionBean();				
				ob=ss.getSuggestionSearch(Integer.parseInt(request.getParameter("sugNo")));				
				if(ob!=null){				
				
						log4jLogger.info("Confirmdelete===========flag====="+flag);
						
									
							int count=ss.getSuggestionDelete(Integer.parseInt(request.getParameter("sugNo")));

							indexPage = "/Suggestion/SuggestionSearch.jsp?check=DeleteSuggestion";
							
							SuggestionBean sugBean = new SuggestionBean();
	    		    		
	    		    		List suggestionList = ss1.getSuggestionList(sugBean,Final_query);
	    		    		    		
	    					request.setAttribute("suggestionSearchList", suggestionList);
	    											
	    					System.out.println("======SuggestionList Size=============== "+suggestionList.size());
												
					   
				}else{
					
					
					request.setAttribute("beanobject", ob);
					indexPage = "/Suggestion/SuggestionSearch.jsp?check=RecordNot";	
				}
				dispatch(request, response, indexPage);
				
			}
    		
    		if (flag.equals("reply")) {
				log4jLogger.info("start===========update=====");
				SuggestionBean ob = new SuggestionBean();
				
				ob.setSugNo(Integer.parseInt(request.getParameter("sugNo")));
		        ob.setActionTaken(request.getParameter("commentText"));
		        ob.setStatus(request.getParameter("status"));			
				int count=ss.getSuggestionReply(ob);
				
				mailcount=ss2.getSuggestionMail(ob);	
				
				SuggestionBean sugBean = new SuggestionBean();
	    		
	    		List suggestionList = ss1.getSuggestionList(sugBean,Final_query);
	    		    		
				request.setAttribute("suggestionSearchList", suggestionList);
				
					if(count==1)
					{
						System.out.println("inside true condition");
						indexPage = "/Suggestion/SuggestionSearch.jsp?check=UpdateSuggestion";		
					}
					else
					{
						System.out.println("inside Fail condition");
						indexPage = "/Suggestion/SuggestionSearch.jsp?check=UpdationFailed";		
					}
					
					
				dispatch(request, response, indexPage);
			}	

    		
    		
    		
    		
    		if(flag.equalsIgnoreCase("suggestion"))
    		{
    			frmdt=Security.TextDate_member(request.getParameter("fromdate"));
    			
    			todt=Security.TextDate_member(request.getParameter("todate"));
    			if(rdate.equalsIgnoreCase("reqdate"))
    			{
    				if(!frmdt.equals("") && (!todt.equals("")))
       			 {	 
       				 filterQuery=filterQuery+" and request_date>='"+frmdt+"' and request_date<='"+todt+"'";
       				 
       			 }
    			}
    			 
    			 
    			 if(!request.getParameter("type").equalsIgnoreCase("ALL") && !request.getParameter("type").isEmpty())
    			 {	 
    				 filterQuery = filterQuery+" and request_for='"+request.getParameter("type")+"'"; 
    			 }
    				
    			 if(!request.getParameter("status").equalsIgnoreCase("ALL") && !request.getParameter("status").isEmpty())
    			 {	 
    				 filterQuery = filterQuery+" and status='"+request.getParameter("status")+"'"; 
    			 }	
    			 
    				Final_query=(NamedQuery + filterQuery + " ORDER BY request_no DESC ");
    		    	//	sb.append(NamedQuery + filterQuery);
    		    		//sb.append(filterQuery);
    		    		
    		    		log4jLogger.info("===================================Query:"+Final_query);
    		    		
    		    		SuggestionBean sugBean = new SuggestionBean();
    		    		    		
    		    		List suggestionList = ss1.getSuggestionList(sugBean,Final_query);
    		    		    		
    					request.setAttribute("suggestionSearchList", suggestionList);
    											
    					System.out.println("======SuggestionList Size=============== "+suggestionList.size());
    		    					
    					String indexPage = "/Suggestion/SuggestionSearch.jsp";
    					dispatch(request, response, indexPage);
    		}
    		
    		
    	
			    
    	   
        }
    catch (Exception sss) {
		throw new ServletException(sss);
		//sss.printStackTrace();
	}
        finally {
       

			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
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
