
package Lib.Auto.EBook;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import Common.Security;
import Common.businessutil.BusinessServiceFactory;
import Common.businessutil.calaloging.CalalogingService;
import Lib.Auto.Author.AuthorBean;
import Lib.Auto.Department.DepartmentBean;
import Lib.Auto.PubSup.PubSupBean;
import Lib.Auto.Subject.subjectbean;

import com.google.gson.Gson;

@WebServlet("/EBook/EBookServlet")
public class EBookServlet extends HttpServlet implements Serializable {
	private static Logger log4jLogger = Logger.getLogger(EBookServlet.class);

	private static final long serialVersionUID = -8906987252709033668L;

	String protocol = "", flag = "";
	String term = "";
	String authorName = "", SameCode = "";
	String sql = "";
	String nam = "";
	String filename = "";

	int sub, pub, dept, branch;
	// int Author_Interface_code=0;
	// int EBook_Mas_code=0;
	String EBook_accessNo_check = "";
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
			CalalogingService ss = BusinessServiceFactory.INSTANCE
					.getCalalogingService();

			response.setContentType("application/json");

			try {
				String term = request.getParameter("accessNo");
				if (!term.equalsIgnoreCase(null) && !term.equalsIgnoreCase("")) {
					// System.out.println("Data from ajax call " + term);

					ArrayList<EBookBean> list = ss
							.getEBookAutoAccessNoSearch(term);
					for (EBookBean user : list) {
						// System.out.println(user.getAccessNo());
					}

					String searchList = new Gson().toJson(list);

					response.getWriter().write(searchList);

					// System.out.println("Json Value@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+searchList);
				}
			} catch (Exception e) {
				// e.printStackTrace();
			}

			try {
				String term = request.getParameter("callNo");

				if (!term.equalsIgnoreCase(null) && !term.equalsIgnoreCase("")) {
					// System.out.println("Data from ajax call " + term);

					ArrayList<EBookBean> list = ss
							.getEBookAutoCallNoSearch(term);
					for (EBookBean user : list) {
						// System.out.println(user.getCallNo());
					}

					String searchList = new Gson().toJson(list);

					response.getWriter().write(searchList);

					// System.out.println("Json Value@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+searchList);
				}
			} catch (Exception e) {
				// e.printStackTrace();
			}

			try {
				String term = request.getParameter("title");

				if (!term.equalsIgnoreCase(null) && !term.equalsIgnoreCase("")) {
					// System.out.println("Data from ajax call " + term);

					ArrayList<EBookBean> list = ss
							.getEBookAutoTitleSearch(term);
					for (EBookBean user : list) {
						// System.out.println(user.getTitle());
					}

					String searchList = new Gson().toJson(list);

					response.getWriter().write(searchList);

					// System.out.println("Json Value@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+searchList);
				}
			} catch (Exception e) {
				// e.printStackTrace();
			}

			try {
				String term = request.getParameter("authorName");

				if (!term.equalsIgnoreCase(null) && !term.equalsIgnoreCase("")) {
					// System.out.println("Data from ajax call " + term);

					ArrayList<AuthorBean> list = ss
							.getEBookAutoAuthorSearch(term);
					for (AuthorBean user : list) {
						// System.out.println(user.getName());
					}

					String searchList = new Gson().toJson(list);

					response.getWriter().write(searchList);

					// System.out.println("Json Value@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+searchList);
				}
			} catch (Exception e) {
				// e.printStackTrace();
			}

			try {
				String term = request.getParameter("pubName");
			
				if (!term.equalsIgnoreCase(null) && !term.equalsIgnoreCase("")) {
					// System.out.println("Data from ajax call " + term);

					ArrayList<PubSupBean> list = ss
							.getEBookAutoPublisherSearch(term);
					for (PubSupBean user : list) {
						System.out.println(user.getName());
					}

					String searchList = new Gson().toJson(list);

					response.getWriter().write(searchList);

					// System.out.println("Json Value@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+searchList);
				}
			} catch (Exception e) {
				// e.printStackTrace();
			}
			try {
				String term = request.getParameter("supName");
				
				if (!term.equalsIgnoreCase("null") && !term.equalsIgnoreCase("")) {
					// System.out.println("Data from ajax call " + term);

					ArrayList<PubSupBean> list = ss
							.getBookAutoSupplierSearch(term);
					for (PubSupBean user : list) {
						// System.out.println(user.getName());
					}

					String searchList = new Gson().toJson(list);

					response.getWriter().write(searchList);

					// System.out.println("Json Value@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+searchList);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				String term = request.getParameter("subName");

				if (!term.equalsIgnoreCase(null) && !term.equalsIgnoreCase("")) {
					// System.out.println("Data from ajax call " + term);

					ArrayList<subjectbean> list = ss
							.getEBookAutoSubjectSearch(term);
					for (subjectbean user : list) {
						// System.out.println(user.getName());
					}

					String searchList = new Gson().toJson(list);

					response.getWriter().write(searchList);

					// System.out.println("Json Value@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+searchList);
				}
			} catch (Exception e) {
				// e.printStackTrace();
			}

			try {
				String term = request.getParameter("deptName");

				if (!term.equalsIgnoreCase(null) && !term.equalsIgnoreCase("")) {
					// System.out.println("Data from ajax call " + term);

					ArrayList<DepartmentBean> list = ss
							.getEBookAutoDeptSearch(term);
					for (DepartmentBean user : list) {
						// System.out.println(user.getName());
					}

					String searchList = new Gson().toJson(list);

					response.getWriter().write(searchList);

					// System.out.println("Json Value@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+searchList);
				}
			} catch (Exception e) {
				// e.printStackTrace();
			}

			EBookBean ob = null;
			flag = request.getParameter("flag");
			System.out.println("flag:::::" + flag);
			if (flag.equals("new")) {
				log4jLogger.info("start===========new=====");
				ob = new EBookBean();
				ob = ss.getEBookMax();

				if (ob.getAccessNo() != null && !ob.getAccessNo().isEmpty()) {
					ob = ss.getEBookMax();
				} else {
					ob.setAccessNo("1");
				}

				request.setAttribute("BeanObject", ob);
				indexPage = "/EBook/index.jsp?check=newEBook";
				dispatch(request, response, indexPage);

			}

			if (flag.equals("search")) {
				log4jLogger.info("start===========search=====" + flag);
				ob = new EBookBean();

				ob = ss.getEBookSearch(request.getParameter("accessNo"));

				if (ob != null) {
					request.setAttribute("BeanObject", ob);

					indexPage = "/EBook/index.jsp?check=searchEBook";
				} else {
					indexPage = "/EBook/index.jsp?check=FailEBook";
				}
				dispatch(request, response, indexPage);

			}

			if (flag.equals("update")) {
				log4jLogger.info("start===========update=====");
				ob = new EBookBean();

				ob.setAccessNo(request.getParameter("accessNo"));
				ob.setCallNo(request.getParameter("callNo"));
				ob.setRcDate(Security.TextDate_Update(request
						.getParameter("rcDate")));

				log4jLogger.info("RECEIVED DATE>>>>" + ob.getRcDate());

				ob.setTitle(request.getParameter("title"));
				ob.setEdition(request.getParameter("edition"));
				ob.setAuthorName(request.getParameter("authorName"));
				ob.setRole(request.getParameter("role"));
				ob.setPubName(request.getParameter("pubName"));
				ob.setSupName(request.getParameter("supName"));
				ob.setYop(request.getParameter("yop"));
				ob.setPages(request.getParameter("pages"));
				ob.setSubName(request.getParameter("subName"));
				ob.setDeptName(request.getParameter("deptName"));

				ob.setSubCode(Integer.parseInt(request.getParameter("Sub")));
				ob.setPubCode(Integer.parseInt(request.getParameter("Pub")));
				/*ob.setSupCode(Integer.parseInt(request.getParameter("Sup")));*/
				System.out.println(":::::::SUPNAME::::"+request.getParameter("supName"));
				ob.setSupName(request.getParameter("supName"));
				ob.setDeptCode(Integer.parseInt(request.getParameter("Dept")));
				ob.setBranchCode(Integer.parseInt(request
						.getParameter("Branch")));

				ob.setKeywords(request.getParameter("keywords"));

				ob.setIsbn(request.getParameter("isbn"));
				ob.setUrl(request.getParameter("url"));
				ob.setInvoiceNo(request.getParameter("inv_no"));

				log4jLogger.info("INVOICE DATE>>>>" + ob.getInvoiceDate());
				ob.setInvoiceDate(Security.TextDate_Update(request
						.getParameter("invDate")));

				ob.setPtype(request.getParameter("ptype"));
				ob.setPrice(request.getParameter("price"));

				ob.setAddfield1("");
				ob.setAddfield2("");

				ob.setType(request.getParameter("type"));
				ob.setContent(request.getParameter("content"));
				ob.setDoc(request.getParameter("doc"));

				int count = ss.getEBookUpdate(ob);
				request.setAttribute("BeanObject", ob);
				indexPage = "/EBook/index.jsp?check=UpdateEBook";
				dispatch(request, response, indexPage);
			}

			if (flag.equals("delete")) {
				log4jLogger.info("start===========delete=====");
				ob = new EBookBean();
				ob = ss.getEBookSearch(request.getParameter("accessNo"));
				if (ob != null) {
					request.setAttribute("BeanObject", ob);
					indexPage = "/EBook/index.jsp?check=deleteCheck";
				} else {
					request.setAttribute("BeanObject", ob);
					indexPage = "/EBook/index.jsp?check=RecordNot";
				}
				dispatch(request, response, indexPage);
			}

			if (flag.equals("Confirmdelete")) {
				log4jLogger.info("Confirmdelete===========flag=====" + flag);

				int count = ss.getEBookDelete(request.getParameter("accessNo"));

				indexPage = "/EBook/index.jsp?check=DeleteEBook";
				dispatch(request, response, indexPage);
			}

			if (flag.equals("save")) {
				ob = new EBookBean();
				EBookBean eb = null;

				ob.setAccessNo(request.getParameter("accessNo"));
				ob.setTitle(request.getParameter("title"));
				ob.setAuthorName(request.getParameter("authorName"));
				ob.setCallNo(request.getParameter("callNo"));
				ob.setRole(request.getParameter("role"));
				ob.setEdition(request.getParameter("edition"));
				ob.setYop(request.getParameter("yop"));
				ob.setPages(request.getParameter("pages"));

				ob.setSubCode(Integer.parseInt(request.getParameter("Sub")));
				ob.setPubCode(Integer.parseInt(request.getParameter("Pub")));
				System.out.println(":::::sup::::::"+ request.getParameter("supName"));						
				ob.setSupName(request.getParameter("supName"));
				System.out.println(ob.getSupName());
				ob.setDeptCode(Integer.parseInt(request.getParameter("Dept")));
				ob.setBranchCode(Integer.parseInt(request.getParameter("Branch")));
						

				ob.setDoc(request.getParameter("doc"));
				ob.setType(request.getParameter("type"));
				ob.setKeywords(request.getParameter("keywords"));
				System.out.println("KEYWORDS"+ request.getParameter("keywords"));
				ob.setIsbn(request.getParameter("isbn"));
				ob.setUrl(request.getParameter("url"));
				
				ob.setInvoiceNo(request.getParameter("inv_no"));
				System.out.println("Invoice No"+ request.getParameter("inv_no"));						
				System.out.println("Invoice Date"+ request.getParameter("invDate"));						
				ob.setInvoiceDate(Security.TextDate_Insert(request.getParameter("invDate")));						
				ob.setPtype(request.getParameter("ptype"));
				ob.setPrice(request.getParameter("price"));
				ob.setAddfield1("");
				ob.setAddfield2("");
				ob.setRcDate(Security.TextDate_Insert(request.getParameter("rcDate")));						
				ob.setContent(request.getParameter("content"));

				eb = ss.getEBookSearch(request.getParameter("accessNo"));

				if (eb != null) {

					ob = new EBookBean();
					log4jLogger
							.info("::::::::::::::::::::::::::::::::::::::::::::::::::::::");

					ob.setAccessNo(request.getParameter("accessNo"));
					// ob.setTitle(request.getParameter("title"));
					if ((request.getParameter("title") != null)
							&& (request.getParameter("title") != "")) {
						ob.setTitle(request.getParameter("title"));
					} else {
						ob.setTitle("Nil");
					}

					if ((request.getParameter("authorName") != null)
							&& (request.getParameter("authorName") != "")) {
						ob.setAuthorName(request.getParameter("authorName"));
					} else {
						ob.setAuthorName("Nil");
					}

					if (request.getParameter("callNo") != null) {
						ob.setCallNo(request.getParameter("callNo"));
					} else {
						ob.setCallNo("");
					}
					// ob.setAuthorName(request.getParameter("authorName"));
					// ob.setCallNo(request.getParameter("callNo"));
					ob.setRole(request.getParameter("role"));
					ob.setEdition(request.getParameter("edition"));
					ob.setYop(request.getParameter("yop"));
					ob.setPages(request.getParameter("pages"));
					ob.setSupName(request.getParameter("supName"));
					System.out.println(ob.getSupName());

					ob.setSubCode(Integer.parseInt(request.getParameter("Sub")));
					ob.setPubCode(Integer.parseInt(request.getParameter("Pub")));
					ob.setSupCode(Integer.parseInt(request.getParameter("Sup")));
					ob.setDeptCode(Integer.parseInt(request
							.getParameter("Dept")));
					ob.setBranchCode(Integer.parseInt(request
							.getParameter("Branch")));
					ob.setSubCode(Integer.parseInt(request.getParameter("Sub")));

					ob.setDoc(request.getParameter("doc"));
					ob.setType(request.getParameter("type"));

					ob.setRcDate(Security.TextDate_Insert(request
							.getParameter("rcDate")));
					ob.setContent(request.getParameter("content"));

					ob.setKeywords(request.getParameter("keywords"));
					ob.setIsbn(request.getParameter("isbn"));
					ob.setUrl(request.getParameter("url"));
					ob.setInvoiceNo(request.getParameter("inv_no"));
					ob.setInvoiceDate(Security.TextDate_Insert(request
							.getParameter("invDate")));
					ob.setPtype(request.getParameter("ptype"));
					ob.setPrice(request.getParameter("price"));

					ob.setAddfield1("");
					ob.setAddfield2("");

					log4jLogger.info("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$44444");

					request.setAttribute("BeanObject", ob);
					indexPage = "/EBook/index.jsp?check=UpdateCheck";
					dispatch(request, response, indexPage);
				} else {

					ob.setAccessNo(request.getParameter("accessNo"));
					ob.setTitle(request.getParameter("title"));
					ob.setAuthorName(request.getParameter("authorName"));
					ob.setCallNo(request.getParameter("callNo"));
					ob.setRole(request.getParameter("role"));
					ob.setEdition(request.getParameter("edition"));
					ob.setYop(request.getParameter("yop"));
					ob.setPages(request.getParameter("pages"));

					ob.setSubCode(Integer.parseInt(request.getParameter("Sub")));
					ob.setPubCode(Integer.parseInt(request.getParameter("Pub")));
					/*ob.setSupCode(Integer.parseInt(request.getParameter("Sup")));*/
					ob.setSupName(request.getParameter("supName"));
					ob.setDeptCode(Integer.parseInt(request
							.getParameter("Dept")));
					ob.setBranchCode(Integer.parseInt(request
							.getParameter("Branch")));

					ob.setKeywords(request.getParameter("keywords"));
					ob.setIsbn(request.getParameter("isbn"));
					ob.setUrl(request.getParameter("url"));
					ob.setInvoiceNo(request.getParameter("inv_no"));

					ob.setPtype(request.getParameter("ptype"));
					ob.setPrice(request.getParameter("price"));

					ob.setDoc(request.getParameter("doc"));
					ob.setType(request.getParameter("type"));

					ob.setAddfield1("");
					ob.setAddfield2("");

					ob.setRcDate(Security.TextDate_Insert(request
							.getParameter("rcDate")));
					ob.setInvoiceDate(Security.TextDate_Insert(request
							.getParameter("invDate")));
					ob.setContent(request.getParameter("content"));

					int count = ss.getEBookSave(ob);
					indexPage = "/EBook/index.jsp?check=SaveEBook";
					dispatch(request, response, indexPage);
				}
			}

			if (flag.equals("ebook")) {
				log4jLogger.info("start===========ebook=====");
				List EBookArraylist = new ArrayList();
				ob = new EBookBean();
				EBookBean eb = null;
				eb = new EBookBean();

				if (request.getParameter("title").trim() != null) {
					ob.setTitle(request.getParameter("title").trim());
					EBookArraylist = ss.getEBookSearchList(ob);
					request.setAttribute("serarch", EBookArraylist);
					indexPage = "/EBook/search.jsp?check=ok&nam=" + nam + "";
					dispatch(request, response, indexPage);
				}
			}

			if (flag.equals("callNo")) {
				log4jLogger.info("start===========ebook==callNo===");
				List EBookArraylist = new ArrayList();
				// EBookBean ob;
				ob = new EBookBean();
				EBookBean eb = null;
				eb = new EBookBean();

				if (request.getParameter("callNo").trim() != null) {
					ob.setTitle(request.getParameter("callNo").trim());
					EBookArraylist = ss.getEBookCallNoList(ob);
					request.setAttribute("search1", EBookArraylist);
					indexPage = "/EBook/searchCallNo.jsp?check=ok&nam=" + nam
							+ "";
					dispatch(request, response, indexPage);
				}
			}
		} catch (Exception sss) {
			// throw new ServletException(sss);
			sss.printStackTrace();
		} finally {

		}

	}

	public void dispatch(HttpServletRequest request,
			HttpServletResponse response, String indexPage)
			throws ServletException, IOException {
		System.out.println(":::::"+indexPage);
		RequestDispatcher dispatch = request.getRequestDispatcher(indexPage);
		
		dispatch.forward(request, response);
	}

}
