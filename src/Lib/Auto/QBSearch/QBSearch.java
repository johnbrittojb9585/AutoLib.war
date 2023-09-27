package Lib.Auto.QBSearch;

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
import Common.businessutil.admin.AdminService;
import Common.businessutil.search.SearchService;
import Lib.Auto.Advanced.Adsearchbean;
import Lib.Auto.MemberTransfer.MemberTransRefBean;

public class QBSearch extends HttpServlet implements Serializable {
	private static Logger log4jLogger = Logger.getLogger(QBSearch.class);
	String indexPage = null;
	/**
	 * 
	 */
	List SearchArrylist;
	List AdsearchArrylist;
	private static final long serialVersionUID = 1L;
		
	String flag = "",title1="";
	String SQL_Query = "";
	Adsearchbean bean=new Adsearchbean();
	
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
			log4jLogger.info("=================INSIDE QUESTION BANK SEARCH====================== :");
		
			  if (request.getParameter("hid") != null && request.getParameter("hid").equals("search")) {
				  SQL_Query = "";
				  
				if (!Security.getParam(request, "dname").equals("") && !Security.getParam(request, "dname").equals("NO")) {					
					
					SQL_Query =	SQL_Query+ " and Dept = "	+ Security.getParam(request, "dname");
				}
				
				if (!Security.getParam(request, "qcourse").equals("") && !Security.getParam(request, "qcourse").equals("NO")) {		
					
					SQL_Query = SQL_Query+ " and course = "	+ Security.getParam(request, "qcourse");
				}
		    	
				if (!Security.getParam(request, "subject").equals("")) {
					
					//SQL_Query =SQL_Query+ " and QBSub_Name like '%"+ Security.getParam(request, "subject")+ "%'";
					
					SQL_Query =SQL_Query+ " and (Sub_Code like '%"+ Security.getParam(request, "subject") +"%' OR Sub_Name like '%"+ Security.getParam(request, "subject")+ "%')";
				}				
				
				if (!Security.getParam(request, "Year").equals("")) {
					
					SQL_Query = SQL_Query+ " and Year =  '"+ Security.getParam(request, "Year")+ "'";
				}
							
				if (!Security.getParam(request, "months").equals("") && !Security.getParam(request, "months").equals("NO")) {
					
					SQL_Query =SQL_Query+ " and Month ='"+ Security.getParam(request, "months")+ "'";
				}
				
				if (!Security.getParam(request, "semester").equals("") && !Security.getParam(request, "semester").equals("ALL")) {
					
					SQL_Query =SQL_Query+ " and Semester ='"+ Security.getParam(request, "semester")+ "'";
				}
				
               /** if (!Security.getParam(request, "txtBranch").equals("NO")) {
			
					SQL_Query = SQL_Query+ " and branch_Code =  '"+ Security.getParam(request, "txtBranch")+ "'";
				}			*/
				
				
				log4jLogger.info(" =========Print SQL Query ==========:"+SQL_Query);
							
			 SearchArrylist = ss.getQBSearch(SQL_Query);		
			
			 session.setAttribute("SearchArrylist",SearchArrylist);
		     
		 	indexPage = "/QBSearch/simpleSearch.jsp";
		 	dispatch(request, response, indexPage);
		 	
		 	}
			
			 
			  if(request.getParameter("accno")!=null){
			    log4jLogger.info("start===========adsearchaccno====="+request.getParameter("accno"));
				 
				   SQL_Query = " and QB_Code = "+request.getParameter("accno");
				   
				   AdsearchArrylist = ss.getFullViewQBSearch(SQL_Query);
				   
				   request.setAttribute("SearchArrylist",AdsearchArrylist);
				   indexPage = "/QBSearch/fullView.jsp";
				   dispatch(request, response, indexPage);			   
			   }
			  
			  
			  
			/**  if(request.getParameter("flag")!=null)  {
			   if(request.getParameter("flag").equals("load")){
					log4jLogger.info("start===========Branch List load=====");
					List BranchArrylist = new ArrayList();
					BranchArrylist=ss.getLoadBranchList();
					request.setAttribute("BranchList", BranchArrylist);
					indexPage = "/QBSearch/index.jsp";
					dispatch(request, response, indexPage);
				}
			  }  */
			  
			  if(request.getParameter("flag")!=null)  {
				   if(request.getParameter("flag").equals("load")){
			  
			    AdminService ss1 = BusinessServiceFactory.INSTANCE.getAdminService();
//			    int branchID=(Integer.parseInt((String.valueOf(session.getAttribute("UserBranchID")))));  // For Titan
//			    MemberTransRefBean beanObject = new MemberTransRefBean();
//				beanObject.setBranchCode(branchID);
				
				List departmentList = ss1.getDepartmentList();				
				request.setAttribute("departmentSearchList", departmentList);
				
				List courseList = ss1.getCourseList();
				request.setAttribute("courseSearchList", courseList);
				   
				indexPage = "/QBSearch/index.jsp";
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
		RequestDispatcher dispatch = request.getRequestDispatcher(indexPage);
		dispatch.forward(request, response);
	}
}


