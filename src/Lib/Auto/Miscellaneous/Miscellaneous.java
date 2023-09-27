
package Lib.Auto.Miscellaneous;



import java.io.IOException;
import java.io.PrintWriter;
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



public class Miscellaneous extends HttpServlet implements Serializable {
	
	private static Logger log4jLogger = Logger.getLogger(Miscellaneous.class);
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
			
			
			MiscellaneousBean ob = null;
			MiscellaneousBean beanObject = null;
			flag = request.getParameter("flag");
			
			if (flag.equals("new")) {
				log4jLogger.info("start===========new=====");
				
				ob = new MiscellaneousBean();
				ob=ss.getMiscellaneousMax();
				
				List transHeadArrylist = new ArrayList();
				transHeadArrylist = ss.getMiscellaneousTHead();

				request.setAttribute("beanobject", ob);		
				request.setAttribute("transactionmaster", transHeadArrylist);	
				
				indexPage = "/Miscellaneous/index.jsp?check=newMiscellaneousCode";
				dispatch(request, response, indexPage);
			}
			

			if (flag.equals("save"))
			{
				log4jLogger.info("start===========save=====");
					ob = new MiscellaneousBean();
					ob.setThead(request.getParameter("lebelhead"));
					ob.setTamount(request.getParameter("tamount"));
					ob.setTremarks(request.getParameter("tremarks"));
					ob.setTaddfield1(request.getParameter("tadd1"));
					
					ob.setMrno(Integer.parseInt(request.getParameter("mrno")));
					ob.setTdate(Security.getdate(request.getParameter("tdate").toString()));
					ob.setMuserID(request.getParameter("muserid"));
					ob.setMname(request.getParameter("mname"));
					ob.setCourse(request.getParameter("mcourse"));
					ob.setQuantity(Integer.parseInt(request.getParameter("mqty")));
					ob.setStaffCode(String.valueOf(session.getAttribute("UserID")));
					
					beanObject = new MiscellaneousBean();
					beanObject = ss.getMiscellaneousSave(ob);
					
					if(beanObject.getMrno() != 0)
					{
						request.setAttribute("beanobject", ob);			
						indexPage = "/Miscellaneous/index.jsp?check=UpdateCheck";						
					}else {
						request.setAttribute("beanobject", ob);	
						indexPage = "/Miscellaneous/index.jsp?check=PayCheck";
					}
					
					List transHeadArrylist = new ArrayList();
					transHeadArrylist = ss.getMiscellaneousTHead();
					request.setAttribute("transactionmaster", transHeadArrylist);

					dispatch(request, response, indexPage);
			}
			
			if (flag.equals("savepayment")) 
			{
				log4jLogger.info("start===========savepayment=====");
				ob = new MiscellaneousBean();
				
				ob.setMrno(Integer.parseInt(request.getParameter("mrno")));
				ob.setMuserID(request.getParameter("muserid"));	
				ob.setTdate(Security.getdate(request.getParameter("tdate").toString()));
				ob.setTamount(request.getParameter("tamount"));
				ob.setThead(request.getParameter("lebelhead"));
				ob.setTaddfield1(String.valueOf(session.getAttribute("UserID")));
				
				beanObject = new MiscellaneousBean();
				beanObject=ss.getTransMasPayment(ob);
				
				if(beanObject.getMrno() > 0){
					
					request.setAttribute("beanobject", ob);					
					indexPage = "/Miscellaneous/index.jsp?check=SaveMiscellaneous";	
				}else{
					indexPage = "/Miscellaneous/index.jsp?check=FailSaveMiscellaneous";
				}
				
				List transHeadArrylist = new ArrayList();
				transHeadArrylist = ss.getMiscellaneousTHead();
				request.setAttribute("transactionmaster", transHeadArrylist);
				
				dispatch(request, response, indexPage);

			}
			
			if (flag.equals("search")) {
				log4jLogger.info("start===========search=====");
				ob = new MiscellaneousBean();
				ob=ss.getMiscellaneousSearch(Integer.parseInt(request.getParameter("mrno").trim()));
				
				if(ob!=null){
					request.setAttribute("beanobject", ob);
					
					indexPage = "/Miscellaneous/index.jsp?check=searchMiscellaneous";	
				}else{
					indexPage = "/Miscellaneous/index.jsp?check=FailSearch";
				}
				
				List transHeadArrylist = new ArrayList();
				transHeadArrylist = ss.getMiscellaneousTHead();
				request.setAttribute("transactionmaster", transHeadArrylist);
				
				dispatch(request, response, indexPage);

			}
			
			if (flag.equals("MemberSearch")) {
				log4jLogger.info("start===========MemberSearch=====");
				ob = new MiscellaneousBean();
				ob=ss.getMiscellaneousMember(request.getParameter("muserid").toString().trim());
				
				if(ob!=null){
					ob.setMrno(Integer.parseInt(request.getParameter("mrno").trim()));
					request.setAttribute("beanobject", ob);
					
					
					if(ob.getSlock().equals("YES") || ob.getSlock().equalsIgnoreCase("CLEARANCE") || ob.getCyear().equals("6")){
						indexPage = "/Miscellaneous/index.jsp?check=searchMemberLock";	
					}else{
						indexPage = "/Miscellaneous/index.jsp?check=searchMember";	
					}
						
				}else{
					indexPage = "/Miscellaneous/index.jsp?check=FailMember";
				}
				
				List transHeadArrylist = new ArrayList();
				transHeadArrylist = ss.getMiscellaneousTHead();
				request.setAttribute("transactionmaster", transHeadArrylist);
				
				dispatch(request, response, indexPage);

			}
			
			
			if (flag.equals("delete")) {		
				log4jLogger.info("start===========delete=====");
				ob = new MiscellaneousBean();				
				ob=ss.getMiscellaneousSearch(Integer.parseInt(request.getParameter("mrno").trim()));
				
				if(ob!=null){				
					request.setAttribute("beanobject", ob);
					indexPage = "/Miscellaneous/index.jsp?check=deleteCheck";
				}else{
					
					indexPage = "/Miscellaneous/index.jsp?check=FailSearch";	
				}
				
				List transHeadArrylist = new ArrayList();
				transHeadArrylist = ss.getMiscellaneousTHead();
				request.setAttribute("transactionmaster", transHeadArrylist);
				
				dispatch(request, response, indexPage);
			}
			
			
			if (flag.equals("Confirmdete")) {
				log4jLogger.info("start===========Confirmdete=====");			
				
				int count = ss.getMiscellaneousDelete(Integer.parseInt(request.getParameter("mrno").trim()));
				
				List transHeadArrylist = new ArrayList();
				transHeadArrylist = ss.getMiscellaneousTHead();
				request.setAttribute("transactionmaster", transHeadArrylist);
				
				indexPage = "/Miscellaneous/index.jsp?check=DeleteMiscellaneous";			
				dispatch(request, response, indexPage);
			}			
			
			
			if (flag.equals("update")) {
				log4jLogger.info("start===========update=====");
				ob = new MiscellaneousBean();
				ob.setThead(request.getParameter("lebelhead"));
				ob.setTamount(request.getParameter("tamount"));
				ob.setTremarks(request.getParameter("tremarks"));
				ob.setTaddfield1(request.getParameter("tadd1"));
				
				ob.setMrno(Integer.parseInt(request.getParameter("mrno")));
				ob.setTdate(Security.getdate(request.getParameter("tdate").toString()));
				ob.setMuserID(request.getParameter("muserid"));
				ob.setMname(request.getParameter("mname"));
				ob.setCourse(request.getParameter("mcourse"));
				ob.setQuantity(Integer.parseInt(request.getParameter("mqty")));
				ob.setPayFlag(String.valueOf(session.getAttribute("UserID")));
				
				int count=ss.getMiscellaneousUpdate(ob);
				request.setAttribute("beanobject", ob);				
				
				List transHeadArrylist = new ArrayList();
				transHeadArrylist = ss.getMiscellaneousTHead();
				request.setAttribute("transactionmaster", transHeadArrylist);
				
				indexPage = "/Miscellaneous/index.jsp?check=UpdateMiscellaneousMas";				
				dispatch(request, response, indexPage);				
			}
			
			
		}
		catch (Exception sss) {
			throw new ServletException(sss);
			//sss.printStackTrace();
		} finally {
			
		}

	}

	
	public void dispatch(HttpServletRequest request,
			HttpServletResponse response, String indexPage)
			throws ServletException, IOException {
		RequestDispatcher dispatch = request.getRequestDispatcher(indexPage);
		dispatch.forward(request, response);
	}

	
	
}
