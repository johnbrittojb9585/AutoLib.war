package Lib.Auto.SugEmail;

import java.io.IOException;
import java.sql.SQLException;

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


public class SugEmail extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static Logger log4jLogger = Logger.getLogger(SugEmail.class);

	java.sql.Connection con = null;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		performTask(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		performTask(request, response);

	}

	public void performTask(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		try {
			HttpSession session = request.getSession(true);
			String res = Security.checkSecurity(1, session, response, request);
			if (res.equalsIgnoreCase("Failure")) {
				response.sendRedirect("/AutoLib/Tree/sessiontimeout.jsp");
				return;
			}
			
			AdminService ss = BusinessServiceFactory.INSTANCE.getAdminService();

			SugmailBean ob = new SugmailBean();
			String flag = null;
			String indexPage = null;

			flag = request.getParameter("flag");
			
			
			if (!flag.isEmpty() && flag!=null && flag.equals("load")) {
				log4jLogger.info("start===========LOAD=====");
				ob = new SugmailBean();
				ob = ss.getsugEmail();

				request.setAttribute("beanobject", ob);
				indexPage = "/SugEmail/index.jsp?check=display";
				dispatch(request, response, indexPage);
			}

			if (flag.equals("save"))
			{
				log4jLogger.info("start===========save=====");
				ob = new SugmailBean();
				ob.setEmail_id(request.getParameter("email"));
			
				int count = ss.getsugEmailsave(ob);
				
				ob = new SugmailBean();				
				ob.setResult(count);
				
				request.setAttribute("beanobject", ob);		
				
				indexPage = "/SugEmail/index.jsp?check=SaveEmail";
				dispatch(request, response, indexPage);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {

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
