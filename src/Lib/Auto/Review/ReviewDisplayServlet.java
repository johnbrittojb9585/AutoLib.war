package Lib.Auto.Review;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import javax.swing.JOptionPane;

import org.apache.log4j.Logger;

import Common.Security;
import Common.businessutil.BusinessServiceFactory;
import Common.businessutil.admin.AdminService;
import Common.businessutil.calaloging.CalalogingService;
import Lib.Auto.Author.Author;
import Lib.Auto.Author.AuthorBean;
import Lib.Auto.Review.ReviewBean;
import Lib.Auto.Advanced.Adsearchbean;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Serializable;
import java.sql.*;
import java.util.*;

public class ReviewDisplayServlet extends HttpServlet implements Serializable{
	 private static Logger log4jLogger = Logger.getLogger(ReviewDisplayServlet.class);
		private static final long serialVersionUID = -8906987252709033668L;
		//String SQL_Query = "";
		String indexPage = null;
		String flag = "";
		String AccessNo="";
										
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
    		    		
    		ReviewBean revBean = new ReviewBean();
    		    		
    		revBean.setAccessNo((String)session.getAttribute("AccessNo"));
    		
    		log4jLogger.info("AccessNo value::::::::::"+revBean.getAccessNo());    		
		  		    		
    		List reviewList = ss1.getReviewList(revBean);
    		    		    		    		    		    		
			request.setAttribute("reviewSearchList", reviewList);
			
			request.setAttribute("reviewSearchListSize", reviewList.size());
			
			System.out.println("======ReviewList Size=============== "+reviewList.size());
    		    				
			log4jLogger.info("AccessNo values::::::::::"+revBean.getAccessNo());
				
				String indexPage = "/Review/displayReview.jsp";
				dispatch(request, response, indexPage);      
        }		
   
    catch (Exception sss) {
		throw new ServletException(sss);
		//sss.printStackTrace();
	}
    }     
    public void dispatch(HttpServletRequest request,
			HttpServletResponse response, String indexPage)
			throws ServletException, IOException {
		RequestDispatcher dispatch = request.getRequestDispatcher(indexPage);
		dispatch.forward(request, response);
	}
}
