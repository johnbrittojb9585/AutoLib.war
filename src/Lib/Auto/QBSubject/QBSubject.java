package Lib.Auto.QBSubject;

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

import Common.Security;
import Common.businessutil.BusinessServiceFactory;
import Common.businessutil.admin.AdminService;
import Common.businessutil.calaloging.CalalogingService;
import Lib.Auto.Author.AuthorBean;
import Lib.Auto.QBSubject.QBsubjectbean;
import Lib.Auto.Subject.subjectbean;
import javax.servlet.annotation.WebServlet;
import com.google.gson.Gson;

import org.apache.log4j.Logger;

@WebServlet("/QBSubject/QBSubjectServlet")

public class QBSubject extends HttpServlet implements Serializable {
	private static Logger log4jLogger = Logger.getLogger(QBSubject.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = -3581534260047108283L;

	String flag = "";
	String term="";
	String protocol = "", Sub_Name = "", SameCode = "";

	int Subject_Interface_code=0;
	int Subject_Mas_code=0;

	int updateFlag;
	String nam="";

	QBsubjectbean ob = new QBsubjectbean();
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
			if(res.equalsIgnoreCase("Failure"))
			{
				response.sendRedirect("/AutoLib/Tree/sessiontimeout.jsp");  
				return;
			}
			
			PrintWriter out = response.getWriter();
			CalalogingService ss = BusinessServiceFactory.INSTANCE.getCalalogingService();
			AdminService as = BusinessServiceFactory.INSTANCE.getAdminService();
			
            response.setContentType("application/json");
            
			try{
				//if(!term.equalsIgnoreCase(null) && !term.equalsIgnoreCase(""))
	            //{
				String term = request.getParameter("qbsubcode");
				
				//System.out.println("Data from ajax call " + term);
							    
				   ArrayList<QBsubjectbean> list = ss.getQBSubjectAutoSearch(term);
			       for(QBsubjectbean user: list){
					//System.out.println(user.getQbsubcode());
				}       

				String searchList = new Gson().toJson(list);
							
				response.getWriter().write(searchList);  
							
				//System.out.println("Json Value@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+searchList);
	        // }    
		}catch(Exception e){
			e.printStackTrace();
		}  		 

			
			QBsubjectbean ob = null;
			flag = request.getParameter("flag");
			
			log4jLogger.info("====INSIDE=======QB Subject Java File ===================="+flag);
			
//			int branchID=(Integer.parseInt((String.valueOf(session.getAttribute("UserBranchID")))));    // For Titan
//			if(branchID == 0)  // Super Admin Rights only
//			{
//			 List BranchArrylist = new ArrayList();
//			 BranchArrylist=as.getLoadBranchList(branchID);
//			 request.setAttribute("UserBranchList", BranchArrylist);
//			}
			
			
			if (flag.equals("new")) {
				
				ob = new QBsubjectbean();
				
				ob=ss.getQBSubjectMax();//1111111111
				
				request.setAttribute("beanobject", ob);
				indexPage = "/QBSubject/index.jsp?check=newSubject";
				dispatch(request, response, indexPage);
 
			}
			
			if (flag.equals("save")) {
			
				
				log4jLogger.info("===========qb subject save ====================");
				
				ob=new QBsubjectbean();
				
				ob.setQbcode(Integer.parseInt(request.getParameter("code")));
				ob.setQbsubcode(request.getParameter("qbsubcode").trim());
				ob.setQbsubname(request.getParameter("qbsubname"));
				ob.setQbsubdesc(request.getParameter("desc"));
//				ob.setQbbranchcode(Integer.parseInt(request.getParameter("txtBranch")));
				
				
				
				int sub_code=ss.getQBSubjectName(ob);//Check Same Question bank subject code
				
				int QBSubject_Interface_code=ss.getQBSubjectInterface(Integer.parseInt(request.getParameter("code"))); //check refer question bank master
				
				int QBSubject_Mas_code=ss.getQBSubjectMas(Integer.parseInt(request.getParameter("code")));// check QuestionBanK SubjectCode Already Exist.
				
				if (sub_code >0) {
					
					log4jLogger.info("====INSIDE=======CodeCompareSubject ====================");
					
					ob.setQbcode(sub_code);
					ob.setQbsubcode(request.getParameter("qbsubcode").trim());
					ob.setQbsubname(request.getParameter("qbsubname"));
					ob.setQbsubdesc(request.getParameter("desc"));
//					ob.setQbbranchcode(Integer.parseInt(request.getParameter("txtBranch")));
					
					request.setAttribute("beanobject", ob);
					
					indexPage = "/QBSubject/index.jsp?check=CodeCompareSubject";

				} 
				else if(QBSubject_Interface_code>0)
				{
					log4jLogger.info("====INSIDE=======UpdateCheck ====================");
					ob.setQbcode(Integer.parseInt(request.getParameter("code")));
					ob.setQbsubcode(request.getParameter("qbsubcode").trim());
					ob.setQbsubname(request.getParameter("qbsubname"));
					ob.setQbsubdesc(request.getParameter("desc"));
//					ob.setQbbranchcode(Integer.parseInt(request.getParameter("txtBranch")));
					
					request.setAttribute("beanobject", ob);
					indexPage = "/QBSubject/index.jsp?check=UpdateCheck";
					
				}
				else if(QBSubject_Mas_code>0)
			   {
					log4jLogger.info("====INSIDE=======Updatename ====================");

					ob.setQbcode(Integer.parseInt(request.getParameter("code")));
					ob.setQbsubcode(request.getParameter("qbsubcode").trim());
					ob.setQbsubname(request.getParameter("qbsubname"));
					ob.setQbsubdesc(request.getParameter("desc"));
//					ob.setQbbranchcode(Integer.parseInt(request.getParameter("txtBranch")));
					request.setAttribute("beanobject", ob);
					indexPage = "/QBSubject/index.jsp?check=Updatename";
					

					} 
				else 
				{

					ob = new QBsubjectbean();
					
					ob=ss.getQBSubjectMax();
				
					ob.setQbsubcode(request.getParameter("qbsubcode").trim());
					ob.setQbsubname(request.getParameter("qbsubname"));
					ob.setQbsubdesc(request.getParameter("desc"));
//					ob.setQbbranchcode(Integer.parseInt(request.getParameter("txtBranch")));
					
					int count=ss.getQBSubjectSave(ob);
					indexPage = "/QBSubject/index.jsp?check=SaveSubject";
					}
				dispatch(request, response, indexPage);
			}

			if (flag.equals("search")) {
				ob=new QBsubjectbean();
				ob=ss.getQBSubjectSearch(Integer.parseInt(request.getParameter("code")));
				if(ob!=null){
					request.setAttribute("beanobject", ob);
					indexPage = "/QBSubject/index.jsp?check=searchSubject";
				} else {
					indexPage = "/QBSubject/index.jsp?check=FailSubject";
				}
				dispatch(request, response, indexPage);
			}
			
			

			if (flag.equals("update")){
				
				ob=new QBsubjectbean();
				
				ob.setQbcode(Integer.parseInt(request.getParameter("code")));
				ob.setQbsubcode(request.getParameter("qbsubcode").trim());
				ob.setQbsubname(request.getParameter("qbsubname"));
				ob.setQbsubdesc(request.getParameter("desc"));
//				ob.setQbbranchcode(Integer.parseInt(request.getParameter("txtBranch")));
				
				int count=ss.getQBSubjectUpdate(ob);
				request.setAttribute("beanobject", ob);
				indexPage = "/QBSubject/index.jsp?check=UpdateSubject";
				dispatch(request, response, indexPage);
			}

			if (flag.equals("delete")) {
				ob=new QBsubjectbean();
				ob=ss.getQBSubjectSearch(Integer.parseInt(request.getParameter("code")));	
				if(ob!=null){
					request.setAttribute("beanobject", ob);
					indexPage = "/QBSubject/index.jsp?check=deleteCheck";
				} else {
					request.setAttribute("beanobject", ob);
					indexPage = "/QBSubject/index.jsp?check=RecordNot";
				}
				dispatch(request, response, indexPage);
			}

	
			if (flag.equals("Confirmdelete")) {
				
				int Subject_Interface_code=ss.getQBSubjectInterface(Integer.parseInt(request.getParameter("code")));			

				int Subject_Mas_code=ss.getQBSubjectMas(Integer.parseInt(request.getParameter("code")));

				if (Subject_Interface_code == Subject_Mas_code) {
					indexPage = "/QBSubject/index.jsp?check=ReferredSubject";
				} else {
					int rk=Integer.parseInt(request.getParameter("code"));

					if(rk==1)
					{
						indexPage = "/QBSubject/index.jsp?check=DefaultSubject";
					}
					else
					{
					
					int count=ss.getQBSubjectDelete(Integer.parseInt(request.getParameter("code")));
					indexPage = "/QBSubject/index.jsp?check=DeleteSubject";
					}
				}
				dispatch(request, response, indexPage);
			}

	
			
			if (flag.equals("Subject")) {
				List SubjectArrylist = null;
				//AuthorBean authorbean = null;
				//AuthorBean newauthor=null;
				
				SubjectArrylist = new ArrayList();
				ob = new QBsubjectbean();
				System.out.println(":::::::SUBNAME:::::"+request.getParameter("qbsubname"));
				ob.setQbsubname(request.getParameter("name"));	
//				ob.setQbbranchcode(branchID);
				
				SubjectArrylist=ss.getQBSubjectSearchName(ob);

				try {

				} catch (Exception e) {
					e.printStackTrace();
				}

				request.setAttribute("serarch", SubjectArrylist);
		     indexPage = "/QBSubject/search.jsp?check=ok&nam="+nam+"";
			dispatch(request, response, indexPage);

			}
		
			}
		catch (Exception sss) {
			throw new ServletException(sss);
			}

		}
//	}

	public void dispatch(HttpServletRequest request,
			HttpServletResponse response, String indexPage)
			throws ServletException, IOException {
		RequestDispatcher dispatch = request.getRequestDispatcher(indexPage);
		dispatch.forward(request, response);
	}

}
