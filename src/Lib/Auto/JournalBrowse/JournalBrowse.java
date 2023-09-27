package Lib.Auto.JournalBrowse;

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
import Common.businessutil.serialcontrol.SerialcontrolService;
import Lib.Auto.Journal.journalbean;



public class JournalBrowse extends HttpServlet implements Serializable {
	private static Logger log4jLogger = Logger.getLogger(JournalBrowse.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	List BrowseArrylist;
	List AdsearchArrylist;
	String indexPage = null;
	
	String flag = "";
	String SQL_Query = "";	
	journalbean ob=new journalbean();
	journalbean newbean=new journalbean();
	
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
			
			SearchService ss = BusinessServiceFactory.INSTANCE.getSearchService();
			SerialcontrolService ss1 = BusinessServiceFactory.INSTANCE.getSerialcontrolService();
			//int branchID=(Integer.parseInt((String.valueOf(session.getAttribute("UserBranchID")))));    // For Titan
			
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
			
			flag=request.getParameter("flag");
            String nm=request.getParameter("name");
            
            
            System.out.println("=========nm========"+nm);
            System.out.println("=======flag=========="+flag);
            
          if(flag !=null){  
            if((flag.equals("Nam"))||(flag.equals("Dept"))||(flag.equals("Pub"))||(flag.equals("Sub"))){
				
 			   System.out.println("JournalBrowse/search.jsp**************"+flag);
 			   
 				List AuthorArrylist = new ArrayList();
 				newbean=new journalbean();
 				if(request.getParameter("name")!=null){
 					newbean.setJname(request.getParameter("name"));
 					newbean.setJremarks(request.getParameter("flag"));					
 					//newbean.setBranchCode(branchID);
 					
 					ob=new journalbean();
 					ob=ss1.getJnlSearchName(newbean);
 					request.setAttribute("bean", ob);
 					indexPage = "/JournalBrowse/search.jsp?check=ok&flag="+flag+"&nam="+nm+"";
 			    	dispatch(request, response, indexPage);
 				}
 				
 			}
		}
		
			
		   if (request.getParameter("hid") != null && request.getParameter("hid").equals("search")) {			

			   if(!Security.getParam(request, "name").equals(""))
				{
				 SQL_Query =
					SQL_Query
						+ " and  jnl_name like '%"
						+ Security.getParam(request, "name")
						+ "%'";
				
				}
			   
			   if(!Security.getParam(request, "document").equals("ALL"))
				{
				 SQL_Query =
					SQL_Query
						+ " and  doc_type ='"
						+ Security.getParam(request, "document")
						+ "'";
				
				}
			   
			   if(!Security.getParam(request, "pname").equals("NIL"))
				{
				   SQL_Query =
							SQL_Query
								+ " and  publisher like '%"
								+ Security.getParam(request, "pname")
								+ "%'";
				
				}
			   
			   if(!Security.getParam(request, "dname").equals("NIL"))
				{
				   SQL_Query =
							SQL_Query
								+ " and  dept_name like '%"
								+ Security.getParam(request, "dname")
								+ "%'";
				
				}
			   
			   if(!Security.getParam(request, "sname").equals("NIL"))
				{
				   SQL_Query =
							SQL_Query
								+ " and  sub_name like '%"
								+ Security.getParam(request, "sname")
								+ "%'";
				
				}
			   
			   if(!Security.getParam(request, "jissn").equals(""))
				{
				   SQL_Query =
							SQL_Query
								+ " and  issn like '%"
								+ Security.getParam(request, "jissn")
								+ "%'";
				
				}
			   
			   
			   
			   
			   if(!Security.getParam(request, "jfreq").equals("ALL"))
				{
				 SQL_Query =
					SQL_Query
						+ " and  frequency ='"
						+ Security.getParam(request, "jfreq")
						+ "'";
				
				}
			   if(!Security.getParam(request, "jcountry").equals("ALL"))
				{
				   
//				   if(!Security.getParam(request, "jcountry").equals("INTERNATIONAL")){
					   SQL_Query =
								SQL_Query
									+ " and  country ='"
									+ Security.getParam(request, "jcountry")
									+ "'";
					   
				  // }
//				   else{
//					   
//					   SQL_Query =
//								SQL_Query
//									+ " and  country !='INDIA'";
//					   
//				   }
				 
				
				}
			   
			   if(!Security.getParam(request, "jlang").equals(""))
				{
				 SQL_Query =
					SQL_Query
						+ " and  language like '"
						+ Security.getParam(request, "jlang")
						+ "%'";
				
				}
			   
			   
			  
			
				    BrowseArrylist=ss.getJournalSearch(SQL_Query);
					
					session.setAttribute("JnlSearchArrylist",BrowseArrylist);
					indexPage = "/JournalBrowse/journalSearch.jsp";
				 	dispatch(request, response, indexPage);
				
			   }
		   
		   
		   
			   
	// Journal Issues Display		   
		   
		   
		 /*  if(request.getParameter("jnlcode")!=null){
		       log4jLogger.info("start======= Browse Journal Issues =====");
		       
			   SQL_Query="and ";
			   SQL_Query=SQL_Query+"jnl_code"+" = '"+request.getParameter("jnlcode")+"' ";
  
			   AdsearchArrylist=ss.getJournalIssueSearch(SQL_Query);

			   session.setAttribute("JnlIssueSearchList",AdsearchArrylist);
			   indexPage = "/JournalBrowse/journalIssueSearchDisplay.jsp";
			   dispatch(request, response, indexPage);			   
			   
		   }*/
		   
		   
		  /* if(request.getParameter("jnlcode")!=null){
		       log4jLogger.info("start======= Browse Journal Issues ====="+request.getParameter("jnlcode"));
		       
			   SQL_Query="and ";
			   SQL_Query=SQL_Query+"jnl_code"+" = '"+request.getParameter("jnlcode")+"' ";
  
			   AdsearchArrylist=ss.getJournalFullView(SQL_Query);

			   session.setAttribute("JnlIssueArrylist",AdsearchArrylist);
			   indexPage = "/JournalBrowse/JournalfullView.jsp";
			   dispatch(request, response, indexPage);			   
			   
		   }*/
		   
		   
		   if(request.getParameter("jnlcode")!=null){
		    	try{
		    		
		    		log4jLogger.info("start======= Browse Journal Issues ====="+request.getParameter("jnlcode"));

			    	 SQL_Query="and ";
					   SQL_Query=SQL_Query+"jnl_code"+" = '"+request.getParameter("jnlcode")+"' ";
	   
				  //AdsearchArrylist=ss.getJournalFullView(SQL_Query);
					   
					   JournalSearchbean jnlfullView = new JournalSearchbean();
					   
					   jnlfullView =ss.getJournalFullViewDetails(SQL_Query);
				 
				 System.out.println("jnlfullView========="+jnlfullView.toString());
		
				     request.setAttribute("beanobject",jnlfullView);
				 
				    indexPage = "/JournalBrowse/JournalfullView.jsp";
				    dispatch(request, response, indexPage);	
		    		
		    	}catch(Exception ex){
		    		
		    		ex.printStackTrace();
		    		
		    	}
		    	 		   
			   
		    }
			
		    
		    if(request.getParameter("url")!=null || request.getParameter("url")!=""){
		    	 log4jLogger.info("start======= Journal Url ====="+request.getParameter("url"));

		    	 response.sendRedirect(request.getParameter("url")); 
			   
		    }
			

		   
/*		   if(request.getParameter("accno")!=null){
		       log4jLogger.info("start=========== Browse Journal Issues Fullview ======");

			   SQL_Query="and ";
			   SQL_Query=SQL_Query+"Issue_Access_No"+" = '"+request.getParameter("accno")+"'";
   
			   AdsearchArrylist=ss.getJournalFullView(SQL_Query);
			   
			   request.setAttribute("JnlIssueArrylist",AdsearchArrylist);
			   indexPage = "/JournalBrowse/fullView.jsp";
			   dispatch(request, response, indexPage);			   
			   
		   }*/			   	
			
		   
	

			
		} catch (Exception sss) {
			sss.printStackTrace();
			
		}

		

		finally {
			SQL_Query = "";					
		}
		
		System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&"+indexPage);

	}
	public void dispatch(HttpServletRequest request,
			HttpServletResponse response, String indexPage)
			throws ServletException, IOException {
		RequestDispatcher dispatch = request.getRequestDispatcher(indexPage);
		dispatch.forward(request, response);
	}
}

