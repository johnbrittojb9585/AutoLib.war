
package Lib.Auto.TransMas;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;

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


public class TransMas extends HttpServlet implements Serializable {
	
	private static Logger log4jLogger = Logger.getLogger(TransMas.class);
	private static final long serialVersionUID = -8906987252709033668L;

	String flag = "", indexPage = null;

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException {

		performTask(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException {

		performTask(request, response);

	}

	public void performTask(HttpServletRequest request,
			HttpServletResponse response) throws ServletException {

		try {			
			HttpSession session = request.getSession(true);
			String res = Security.checkSecurity(1, session, response, request);		
			if(res.equalsIgnoreCase("Failure"))
			{
				response.sendRedirect("/AutoLib/Tree/sessiontimeout.jsp");  
				return;
			}
			
			
			PrintWriter out = response.getWriter();
			AdminService ss = BusinessServiceFactory.INSTANCE.getAdminService();
			
			
			TransMasBean ob = null;
			TransMasBean beanObject = null;
			flag = request.getParameter("flag");
			
			if (flag.equals("new")) {
				log4jLogger.info("start===========new=====");
				
				ob = new TransMasBean();
				ob=ss.getTransCodeMax();

				request.setAttribute("beanobject", ob);			
				indexPage = "/TransMas/index.jsp?check=newTransCode";
				dispatch(request, response, indexPage);
				

			}
			

			if (flag.equals("save"))
			{
				log4jLogger.info("start===========save=====");
					ob = new TransMasBean();
					ob.setThead(request.getParameter("thead"));
					ob.setTamount(request.getParameter("tamount"));
					ob.setTremarks(request.getParameter("tremarks"));
					ob.setTaddfield1(request.getParameter("tadd1"));
					ob.setTcode(Integer.parseInt(request.getParameter("tcode")));
					
					beanObject = new TransMasBean();
					beanObject = ss.getTransMasSave(ob);
					
					if(beanObject != null)
					{
						request.setAttribute("beanobject", beanObject);			
						indexPage = "/TransMas/index.jsp?check=UpdateCheck";						
					}else {
						indexPage = "/TransMas/index.jsp?check=SaveTransMas";
					}
					
					dispatch(request, response, indexPage);
			}
			
			
			if (flag.equals("search")) {
				log4jLogger.info("start===========search=====");
				ob = new TransMasBean();
				ob=ss.getTransMasSearch(Integer.parseInt(request.getParameter("tcode").trim()));
				
				if(ob!=null){
					request.setAttribute("beanobject", ob);
					
					indexPage = "/TransMas/index.jsp?check=searchTransMas";	
				}else{
					indexPage = "/TransMas/index.jsp?check=FailTransMas";
				}
				
				dispatch(request, response, indexPage);

			}
			
			
			if (flag.equals("delete")) {		
				log4jLogger.info("start===========delete=====");
				ob = new TransMasBean();				
				ob=ss.getTransMasSearch(Integer.parseInt(request.getParameter("tcode").trim()));				
				if(ob!=null){				
					request.setAttribute("beanobject", ob);
					indexPage = "/TransMas/index.jsp?check=deleteCheck";
				}else{
					request.setAttribute("beanobject", ob);
					indexPage = "/TransMas/index.jsp?check=RecordNot";	
				}
					dispatch(request, response, indexPage);
			}
			
			
			if (flag.equals("Confirmdete")) {
				log4jLogger.info("start===========Confirmdete=====");			
				
				int count = ss.getTransMasDelete(Integer.parseInt(request.getParameter("tcode").trim()));

				indexPage = "/TransMas/index.jsp?check=DeleteTransMas";			
				dispatch(request, response, indexPage);
			}			
			
			if (flag.equals("update")) {
				log4jLogger.info("start===========update=====");
				ob = new TransMasBean();
				ob.setThead(request.getParameter("thead"));
				ob.setTamount(request.getParameter("tamount"));
				ob.setTremarks(request.getParameter("tremarks"));
				ob.setTaddfield1(request.getParameter("tadd1"));
				ob.setTcode(Integer.parseInt(request.getParameter("tcode")));
				
				int count=ss.getTransMasUpdate(ob);
				request.setAttribute("beanobject", ob);				
				indexPage = "/TransMas/index.jsp?check=UpdateTransMas";				
				dispatch(request, response, indexPage);				
			}
			
			
		}
		catch (Exception sss) {
			throw new ServletException(sss);
			//sss.printStackTrace();
		} finally {
			
		}

	}

	/** 
	 * Instance variable for SQL statement property
	 */

	/****************************************************************
	 *prepare the sql statement for execution
	 **/
	public void dispatch(HttpServletRequest request,
			HttpServletResponse response, String indexPage)
			throws ServletException, IOException {
		RequestDispatcher dispatch = request.getRequestDispatcher(indexPage);
		dispatch.forward(request, response);
	}

	/* (non-Javadoc)
	 * @see Lib.Auto.Author.Author_Interface#newRecord()
	 */
	
	
}
