package Lib.Auto.Review;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

import org.apache.log4j.Logger;

import com.library.autolib.portal.prototype.LibraryServiceFactory;

import Common.Security;
import Common.businessutil.BusinessServiceFactory;
import Common.businessutil.LoginUserService;
import Common.businessutil.calaloging.CalalogingService;
import Common.businessutil.search.SearchService;
import Lib.Auto.Advanced.Adsearchbean;
import Lib.Auto.Author.AuthorBean;
import Lib.Auto.Book.bookbean;
import Lib.Auto.EBook.EBookBean;
import Lib.Auto.Review.ReviewBean;
import Login.User;


	public class ReviewServlet extends HttpServlet implements Serializable {
		 private static Logger log4jLogger = Logger.getLogger(ReviewServlet.class);
		 List SearchArrylist;
		 List AdsearchArrylist;

		private static final long serialVersionUID = -8906987252709033668L;

		String protocol = "", flag = "",accNo="";
		String SQL_Query = "";
		
		String desc = "", SameCode = "";
		String sql="";
		String nam="";
	    String filename="";
	        
		String indexPage = null;
		Adsearchbean bean=new Adsearchbean();
		
		public void doGet(HttpServletRequest request, HttpServletResponse response)
				throws ServletException {

			performTask(request, response);
		}

		public void doPost(HttpServletRequest request, HttpServletResponse response)
				throws ServletException {

			performTask(request, response);
		}

		public void performTask(HttpServletRequest request,HttpServletResponse response) throws ServletException {

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
								
				//session.setAttribute("UserID", user.getUserId());
												
				ReviewBean ob = null;
				Adsearchbean asb = null;
				flag = request.getParameter("flag");
				accNo=request.getParameter("accNo");
				//log4jLogger.info("DDDDDDDDDDDDDd"+accNo);
				
				
				if (flag.equals("new")) {
					log4jLogger.info("start===========new=====");
					ob = new ReviewBean();
					ob=ss.getReviewMax();
					ob.setAccessNo(accNo);
					/*if(ob.getSugNo()!= 0){
					       ob=ss.getSuggestionMax();      	
					}else{
						ob.setSugNo(1);
					} */
					
					
					//log4jLogger.info("#####################"+ob.getAccessNo());
					request.setAttribute("beanobject", ob);
					//request.setAttribute("AccessNo", accNo);
					indexPage = "/Review/index.jsp?check=newReview";
					dispatch(request, response, indexPage);
				
				}
				
										
				if (flag.equals("search")) {
					log4jLogger.info("start===========search====="+flag);
					ob = new ReviewBean();
					
					ob=ss.getReviewSearch(Integer.parseInt(request.getParameter("reviewNo")));
					
					if(ob!=null){
						request.setAttribute("beanobject", ob);
						
						indexPage = "/Review/index.jsp?check=searchReview";	
					}else{
						indexPage = "/Review/index.jsp?check=FailReview";
					}
					dispatch(request, response, indexPage);

				}
				
				if (flag.equals("delete")) {		
					log4jLogger.info("start===========delete=====");
					ob = new ReviewBean();				
					ob=ss.getReviewSearch(Integer.parseInt(request.getParameter("reviewNo")));				
					if(ob!=null){				
						request.setAttribute("beanobject", ob);
						indexPage = "/Review/index.jsp?check=deleteCheck";
					}else{
						request.setAttribute("beanobject", ob);
						indexPage = "/Review/index.jsp?check=RecordNot";	
					}
					dispatch(request, response, indexPage);
				}
			
					if(flag.equals("Confirmdelete")){
						log4jLogger.info("Confirmdelete===========flag====="+flag);
									
							int count=ss.getReviewDelete(Integer.parseInt(request.getParameter("reviewNo")));

							indexPage = "/Review/index.jsp?check=DeleteReview";
							dispatch(request, response, indexPage);
					   }

				
				if (flag.equals("update")) {
					log4jLogger.info("start===========update=====");
					ob = new ReviewBean();
					
					ob.setReviewNo(Integer.parseInt(request.getParameter("reviewNo")));
					ob.setAccessNo(request.getParameter("accessNo"));
					ob.setMemberCode(request.getParameter("memberCode"));
					ob.setRcDate(Security.TextDate_Update(request.getParameter("rcDate")));
			        ob.setTitle(request.getParameter("title"));
			        ob.setDesc(request.getParameter("desc"));
			        ob.setRating(Integer.parseInt(request.getParameter("rating")));
			        //ob.setRating(request.getParameter("rating"));
			        
			        
					int count=ss.getReviewUpdate(ob);
					request.setAttribute("BeanObject", ob);				
					indexPage = "/Review/index.jsp?check=UpdateReview";				
					dispatch(request, response, indexPage);
				}	

					
					if (flag.equals("save"))
					{
						 		ob = new ReviewBean();
							    ReviewBean eb = null;
							    
							    ob.setReviewNo(Integer.parseInt(request.getParameter("reviewNo")));
								ob.setAccessNo(request.getParameter("accessNo"));
								ob.setMemberCode(request.getParameter("memberCode"));
						 		ob.setRcDate(Security.TextDate_Insert(request.getParameter("rcDate")));
						        //ob.setMemberCode(String.valueOf(session.getAttribute("UserID")));
						 		ob.setTitle(request.getParameter("title"));
						        ob.setDesc(request.getParameter("desc"));
						        ob.setRating(Integer.parseInt(request.getParameter("rating")));
						        //ob.setRating(request.getParameter("rating"));
						              
						        eb=ss.getReviewSearch(Integer.parseInt(request.getParameter("reviewNo")));
						        
						        if (eb != null) {
									
									 ob = new ReviewBean();
									 
									    ob.setReviewNo(Integer.parseInt(request.getParameter("reviewNo")));
										ob.setAccessNo(request.getParameter("accessNo"));
										ob.setMemberCode(request.getParameter("memberCode"));
								 		ob.setRcDate(Security.TextDate_Insert(request.getParameter("rcDate")));
								        //ob.setMemberCode(String.valueOf(session.getAttribute("UserID")));
								 		ob.setTitle(request.getParameter("title"));
								        ob.setDesc(request.getParameter("desc"));
								        ob.setRating(Integer.parseInt(request.getParameter("rating")));
								        //ob.setRating(request.getParameter("rating"));
								     
								     request.setAttribute("beanobject", ob);
									 indexPage = "/Review/index.jsp?check=UpdateCheck";
									 dispatch(request, response, indexPage);
						        }
						        else {
						        	ob.setReviewNo(Integer.parseInt(request.getParameter("reviewNo")));
									ob.setAccessNo(request.getParameter("accessNo"));
							        ob.setMemberCode(request.getParameter("memberCode"));
							 		ob.setRcDate(Security.TextDate_Insert(request.getParameter("rcDate")));
							        //ob.setMemberCode(String.valueOf(session.getAttribute("UserID")));
							 		ob.setTitle(request.getParameter("title"));
							        ob.setDesc(request.getParameter("desc"));
							        ob.setRating(Integer.parseInt(request.getParameter("rating")));
							        //ob.setRating(request.getParameter("rating"));
						        					        										
								     int count=ss.getReviewSave(ob);
								     indexPage = "/Review/index.jsp?check=SaveReview";	
								     dispatch(request, response, indexPage);
						}		
					
					}
				
			}
		catch (Exception sss) {
			throw new ServletException(sss);
			//sss.printStackTrace();
		} finally {
						
		}

		}
					
		public void dispatch(HttpServletRequest request,HttpServletResponse response, String indexPage)
				throws ServletException, IOException {
			RequestDispatcher dispatch = request.getRequestDispatcher(indexPage);
			dispatch.forward(request, response);
		}
		
	}




