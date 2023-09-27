package Lib.Auto.bulkTransfer_Books;

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
import Common.SplitAccessNoBean;// for split accessno
import Common.businessutil.BusinessServiceFactory;
import Common.businessutil.calaloging.CalalogingService;
import Common.businessutil.circulation.CirculationService;
import Lib.Auto.Transfer_Books.BookTransferBean;


public class bulkTransferBooksAction extends HttpServlet implements
		Serializable {
	private static Logger log4jLogger = Logger
			.getLogger(bulkTransferBooksAction.class);
	private static final long serialVersionUID = 1L;

	String flag = "";
	String indexPage = null;

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
			if (res.equalsIgnoreCase("Failure")) {
				response.sendRedirect("/AutoLib/Tree/sessiontimeout.jsp");
				return;
			}

			PrintWriter out = response.getWriter();
			CirculationService ss = BusinessServiceFactory.INSTANCE
					.getCirculationService();
			CalalogingService ss1 = BusinessServiceFactory.INSTANCE
					.getCalalogingService();


			BookTransferBean ob = new BookTransferBean();
			BookTransferBean newbean = new BookTransferBean();

			if (request.getParameter("flag").equals("search")) {

				ob = new BookTransferBean();
				
				log4jLogger.info("start===========SEARCH====");

				ob.setAccess_no(request.getParameter("fromAccessNo"));
				ob.setToaccess_no(request.getParameter("toAccessNo"));
				ob.setordno(Integer.parseInt(request.getParameter("ordno")));
				ob.setdeptName(request.getParameter("txtBinder"));
				ob.setDocument(request.getParameter("doc"));
				ob.setDate(Security.TextDate_Insert(request
						.getParameter("SendDate")));

				
				List AccNoList = ss1.getAccNoList(ob);
				

				request.setAttribute("AccessNoSearchList", AccNoList);
				request.setAttribute("AccessNoSearchListSize", AccNoList.size());

				List BindingArrylist = new ArrayList();
				BindingArrylist = ss.getLoadDeptName();
				request.setAttribute("binding", BindingArrylist);

				request.setAttribute("BeanObject", ob);

				indexPage = "/bulkTransferBooks/index.jsp?check=displayAccessNo";
				dispatch(request, response, indexPage);
			}
			
			
			
		

			if (request.getParameter("flag").equals("transferBookSearch")) {
				log4jLogger.info("start===========transfer Book SEARCH====");

				ob.setAccess_no(request.getParameter("fromAccessNo"));
				ob.setToaccess_no(request.getParameter("toAccessNo"));
				ob.setdeptName(request.getParameter("txtBinder"));

				List TransAccNoList = ss1.getTransAccNoList(ob);

				request.setAttribute("AccessNoSearchList", TransAccNoList);
				request.setAttribute("AccessNoSearchListSize",
						TransAccNoList.size());

				List BindingArrylist = new ArrayList();
				BindingArrylist = ss.getLoadDeptName();
				request.setAttribute("binding", BindingArrylist);

				request.setAttribute("BeanObject", ob);

				indexPage = "/bulkTransferBooks/index.jsp?check=displayAccessNo";
				dispatch(request, response, indexPage);
			}

			if (request.getParameter("flag").equals("load")) {
				log4jLogger.info("start===========load=====");
				List BindingArrylist = new ArrayList();
				BindingArrylist = ss.getLoadDeptName();
				request.setAttribute("binding", BindingArrylist);
				indexPage = "/bulkTransferBooks/index.jsp";
				dispatch(request, response, indexPage);
			}

			if (request.getParameter("flag").equals("new")) {
				log4jLogger.info("start===========new=====");

				ob = ss.getTransferMax();
				request.setAttribute("BeanObject", ob);

				List BindingArrylist = new ArrayList();
				BindingArrylist = ss.getLoadDeptName();
				request.setAttribute("binding", BindingArrylist);
				indexPage = "/bulkTransferBooks/index.jsp?check=newordno";
				dispatch(request, response, indexPage);
			}
			if (request.getParameter("flag").equals("bulksave")) {

				log4jLogger.info("start===========bulksave=====");
				String sss = request.getParameter("flag1");
				ob.setordno(Integer.parseInt(request.getParameter("ordno")));
				ob.setAccess_no(request.getParameter("AccessNo"));
				ob.setdeptName(request.getParameter("txtBinder"));
				ob.setDocument(request.getParameter("doc"));
				ob.setDate(Security.TextDate_Insert(request
						.getParameter("SendDate")));
				ob.setAuthor(request.getParameter("flag1"));// for bulk
															// access_no

				log4jLogger.info("Bulk AccessNo Values" + ob.getAuthor());

				newbean = ss.getbulkTransferBooksSave(ob);
								
				List BindingArrylist = new ArrayList();
				BindingArrylist = ss.getLoadDeptName();
				request.setAttribute("binding", BindingArrylist);
				if(!newbean.getAvail().equalsIgnoreCase("Transfered"))
				{
					indexPage = "/bulkTransferBooks/index.jsp?check=saved";
				}
				else
					indexPage = "/bulkTransferBooks/index.jsp?check=Transfered";
				dispatch(request, response, indexPage);
			}

			if (request.getParameter("flag").equals("display")) {
				log4jLogger.info("start===========display=====");

				List BindingArrylist = new ArrayList();
				String dept=request.getParameter("txtBinder");
				BindingArrylist = ss.getLoadDeptName();
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

				indexPage = "/bulkTransferBooks/index.jsp?check=display";
				dispatch(request, response, indexPage);
			}

			if (request.getParameter("flag").equals("bulkreturn")) {
				log4jLogger.info("start===========bulk transfer return====="
						+ request.getParameter("flag1"));

				int count = ss.getbulkTransferBooksReturn(request.getParameter("flag1"));

				List BindingArrylist = new ArrayList();
				BindingArrylist = ss.getLoadDeptName();
				request.setAttribute("binding", BindingArrylist);
				
				if (count > 0) 
				{
					indexPage = "/bulkTransferBooks/index.jsp?check=deleted";
				}
				else
					indexPage = "/bulkTransferBooks/index.jsp?check=notpresent";
				
				dispatch(request, response, indexPage);
			}
		} catch (Exception sss) {
			throw new ServletException(sss);

		} finally {

			try {
			} catch (Exception e) {
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
