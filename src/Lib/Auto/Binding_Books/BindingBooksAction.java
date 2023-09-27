package Lib.Auto.Binding_Books;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.UnavailableException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import Common.Security;
import Common.businessutil.BusinessServiceFactory;
import Common.businessutil.circulation.CirculationService;
import Lib.Auto.Author.AuthorBean;
import Lib.Auto.Binding.Binding;



public class BindingBooksAction extends HttpServlet implements Serializable {
	private static Logger log4jLogger = Logger.getLogger(BindingBooksAction.class);
	private static final long serialVersionUID = 1L;
		
	String flag="",protocol="",binder="",DISPLAY_BINDDOC_MAS="";
	
	String indexPage = null;
	ResourceBundle rb =null;
	BookBindingBean ob=new BookBindingBean();
	
	
	
	
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException {

		performTask(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException {

		performTask(request, response);

	}
	public void performTask(
		HttpServletRequest request,
		HttpServletResponse response) throws ServletException {

		
		try {
			HttpSession session = request.getSession(true);
			String res = Security.checkSecurity(1, session, response, request);		
			if(res.equalsIgnoreCase("Failure"))
			{
				response.sendRedirect("/AutoLib/Tree/sessiontimeout.jsp");  
				return;
			}
			
			PrintWriter out=response.getWriter();
			CirculationService ss = BusinessServiceFactory.INSTANCE.getCirculationService();
			
			if(request.getParameter("flag").equals("clear")){
				log4jLogger.info("start===========clear=====");
				List BindingArrylist = new ArrayList();
				BindingArrylist=ss.getLoadBinderName();
				request.setAttribute("binding", BindingArrylist);
				indexPage = "/BindingBooks/index.jsp";
				dispatch(request, response, indexPage);
				
			}
			if(request.getParameter("flag").equals("load")){
				log4jLogger.info("start===========load=====");
				List BindingArrylist = new ArrayList();
				BindingArrylist=ss.getLoadBinderName();
				request.setAttribute("binding", BindingArrylist);
				indexPage = "/BindingBooks/index.jsp";
				dispatch(request, response, indexPage);
			}
			
			
			if(request.getParameter("flag").equals("save")){
				log4jLogger.info("start===========save=====");
				
				ob.setAccess_no(request.getParameter("AccessNo"));
				ob.setBinderName(request.getParameter("txtBinder"));
				ob.setDocument(request.getParameter("doc"));
				ob.setDate(Security.TextDate_Insert(request.getParameter("SendDate")));
				
				int count=ss.getBindingBooksSave(ob);
				
				if(count==1){
					
					indexPage = "/BindingBooks/index.jsp?check=Issuedbook";
					
					List BindingArrylist = new ArrayList();
					BindingArrylist=ss.getLoadBinderName();
					request.setAttribute("binding", BindingArrylist);
					
				}else if(count==2){
					
					List BindingArrylist = new ArrayList();
					BindingArrylist=ss.getLoadBinderName();
					request.setAttribute("binding", BindingArrylist);
					
					indexPage = "/BindingBooks/index.jsp?check=BindingBooks";
				}else if(count==4){
					
					List BindingArrylist = new ArrayList();
					BindingArrylist=ss.getLoadBinderName();
					request.setAttribute("binding", BindingArrylist);
					
					indexPage = "/BindingBooks/index.jsp?check=notPresent";
				}else if(count==5){
					
					List BindingArrylist = new ArrayList();
					BindingArrylist=ss.getLoadBinderName();
					request.setAttribute("binding", BindingArrylist);
					
					indexPage = "/BindingBooks/index.jsp?check=Transfered";
				}else if(count==6){
					
					List BindingArrylist = new ArrayList();
					BindingArrylist=ss.getLoadBinderName();
					request.setAttribute("binding", BindingArrylist);
					
					indexPage = "/BindingBooks/index.jsp?check=Lost";
				}else if(count==7){
					
					List BindingArrylist = new ArrayList();
					BindingArrylist=ss.getLoadBinderName();
					request.setAttribute("binding", BindingArrylist);
					
					indexPage = "/BindingBooks/index.jsp?check=Missing";
				}else if(count==8){
					
					List BindingArrylist = new ArrayList();
					BindingArrylist=ss.getLoadBinderName();
					request.setAttribute("binding", BindingArrylist);
					
					indexPage = "/BindingBooks/index.jsp?check=Withdrawn";
				}else{
					
					List BindingArrylist = new ArrayList();
					BindingArrylist=ss.getLoadBinderName();
					request.setAttribute("binding", BindingArrylist);
					
					indexPage = "/BindingBooks/index.jsp?check=saved";
				}
				dispatch(request, response, indexPage);
			}
			
			if(request.getParameter("flag").equals("display")){
				log4jLogger.info("start===========display=====");
				List BindingArrylist = new ArrayList();
				BindingArrylist=ss.getLoadBinderName();
				request.setAttribute("binding", BindingArrylist);
				
				
				List BindingDisplayArrylist = new ArrayList();
				BindingDisplayArrylist=ss.getBindingDisplay();
				request.setAttribute("search", BindingDisplayArrylist);
				
				indexPage = "/BindingBooks/index.jsp?check=display";
				dispatch(request, response, indexPage);
			}
			
			
			if(request.getParameter("flag").equals("return")){
				log4jLogger.info("start===========return=====");
				int count=ss.getBindingBooksReturn(request.getParameter("AccessNo"));
				
				if(count>0){
					List BindingArrylist = new ArrayList();
					BindingArrylist=ss.getLoadBinderName();
					request.setAttribute("binding", BindingArrylist);
					indexPage = "/BindingBooks/index.jsp?check=deleted";
					
				}else{
					List BindingArrylist = new ArrayList();
					BindingArrylist=ss.getLoadBinderName();
					request.setAttribute("binding", BindingArrylist);
					indexPage = "/BindingBooks/index.jsp?check=notPresent";
					
				}
				dispatch(request, response, indexPage);
			}
		
		
			 } catch (Exception sss) {
					throw new ServletException(sss);
					
				} finally {
					
					try{
						

					}catch(Exception e){
					e.printStackTrace();
					}
					

				}

	}
	

	/**
	 * Instance variable for SQL statement property
	 */
	
	public void dispatch(HttpServletRequest request,
			HttpServletResponse response, String indexPage)
			throws ServletException, IOException {
		RequestDispatcher dispatch = request.getRequestDispatcher(indexPage);
		dispatch.forward(request, response);
	}
	
	
}
