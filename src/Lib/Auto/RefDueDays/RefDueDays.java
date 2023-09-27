package Lib.Auto.RefDueDays;

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


public class RefDueDays extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static Logger log4jLogger = Logger.getLogger(RefDueDays.class);

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

			RefDaysBean ob = new RefDaysBean();
			String flag = null;
			String indexPage = null;

			flag = request.getParameter("flag");
			
			
			if (!flag.isEmpty() && flag!=null && flag.equals("load")) {
				log4jLogger.info("start===========LOAD=====");
				ob = new RefDaysBean();
				ob = ss.getRefDueDays();

				request.setAttribute("beanobject", ob);
				indexPage = "/RefDueDays/index.jsp?check=display";
				dispatch(request, response, indexPage);
			}

			if (flag.equals("save"))
			{
				log4jLogger.info("start===========save=====");
				ob = new RefDaysBean();
				ob.setDue_days(Integer.parseInt(request.getParameter("duedays")));
				log4jLogger.info("start===========save===== :"+ ob.getDue_days());
				
				int count = ss.getduedaysSave(ob);
				
				ob = new RefDaysBean();				
				ob.setResult(count);				
				request.setAttribute("beanobject", ob);		
				
				indexPage = "/RefDueDays/index.jsp?check=Saveduedays";
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
