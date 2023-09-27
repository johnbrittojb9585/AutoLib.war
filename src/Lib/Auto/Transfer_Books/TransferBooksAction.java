package Lib.Auto.Transfer_Books;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import Common.Security;
import Common.businessutil.BusinessServiceFactory;
import Common.businessutil.calaloging.CalalogingService;
import Common.businessutil.circulation.CirculationService;
import Lib.Auto.Transfer_Books.BookTransferBean;



public class TransferBooksAction extends HttpServlet implements Serializable {
	private static Logger log4jLogger = Logger.getLogger(TransferBooksAction.class);
	private static final long serialVersionUID = 1L;
		
	String flag="",protocol="",binder="",DISPLAY_BINDDOC_MAS="";
	
	String indexPage = null;
	ResourceBundle rb =null;
	BookTransferBean ob=new BookTransferBean();
	
	BookTransferBean newbean=new BookTransferBean();
	
	
	
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
			
			CalalogingService ss1 = BusinessServiceFactory.INSTANCE.getCalalogingService();
			
			
			
			
			
			if(request.getParameter("flag").equals("load")){
				log4jLogger.info("start===========load=====");
				List BindingArrylist = new ArrayList();
				
				
				
				BindingArrylist=ss.getLoadDeptName();
			
				request.setAttribute("binding", BindingArrylist);
				indexPage = "/TransferBooks/index.jsp";
				dispatch(request, response, indexPage);
			}
			
			if(request.getParameter("flag").equals("new")){
				log4jLogger.info("start===========new=====");
				
				ob=ss.getTransferMax();
				request.setAttribute("BeanObject", ob);
				
				List BindingArrylist = new ArrayList();
				
				
				BindingArrylist=ss.getLoadDeptName();
				
				
				request.setAttribute("binding", BindingArrylist); 
				
				indexPage = "/TransferBooks/index.jsp?check=newordno";
				dispatch(request, response, indexPage);
			}
			
			if(request.getParameter("flag").equals("save")){
				log4jLogger.info("start===========save=====");
				ob.setordno(Integer.parseInt(request.getParameter("ordno")));
				ob.setAccess_no(request.getParameter("AccessNo"));
				ob.setdeptName(request.getParameter("txtBinder"));
				ob.setDocument(request.getParameter("doc"));
				ob.setDate(Security.TextDate_Insert(request.getParameter("SendDate")));
			
				newbean=ss.getTransferBooksSave(ob);
				String availability=newbean.getAvail();
				
				if(availability!=""){
					
					
					List BindingArrylist = new ArrayList();
					BindingArrylist=ss.getLoadDeptName();
					request.setAttribute("binding", BindingArrylist);
					
					request.setAttribute("BeanObject", newbean);
					indexPage = "/TransferBooks/index.jsp?check=Availbable";
			
				}else{
					
					List BindingArrylist = new ArrayList();
					BindingArrylist=ss.getLoadDeptName();
					request.setAttribute("binding", BindingArrylist);
					
					indexPage = "/TransferBooks/index.jsp?check=saved";
				}
				dispatch(request, response, indexPage);
			}
			
			if(request.getParameter("flag").equals("display")){
				log4jLogger.info("start===========display=====");
				List BindingArrylist = new ArrayList();
				
				String dept=request.getParameter("txtBinder");
				//BindingArrylist=ss.getLoadDeptName(branchID);
				BindingArrylist=ss.getLoadDeptName();
				
				request.setAttribute("binding", BindingArrylist);
				
				List BindingDisplayArrylist = new ArrayList();
				if(!dept.equalsIgnoreCase("NO"))
				{
					BindingDisplayArrylist=ss.getDeptTransferDisplay(dept);
				}
				else
				{
					BindingDisplayArrylist=ss.getTransferDisplay();
				}
				
				request.setAttribute("search", BindingDisplayArrylist);
				
				indexPage = "/TransferBooks/index.jsp?check=display";
				dispatch(request, response, indexPage);
			}
			
			
			if(request.getParameter("flag").equals("return")){
				log4jLogger.info("start===========return=====");
				int count=ss.getTransferBooksReturn(request.getParameter("AccessNo"));
				
				if(count>0){
					List BindingArrylist = new ArrayList();
					BindingArrylist=ss.getLoadDeptName();
					request.setAttribute("binding", BindingArrylist);
					indexPage = "/TransferBooks/index.jsp?check=deleted";
					
				}else{
					List BindingArrylist = new ArrayList();
					BindingArrylist=ss.getLoadDeptName();
					request.setAttribute("binding", BindingArrylist);
					indexPage = "/TransferBooks/index.jsp?check=notPresent";
					
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
