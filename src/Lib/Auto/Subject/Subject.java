package Lib.Auto.Subject;

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
import Common.businessutil.calaloging.CalalogingService;
import Lib.Auto.Author.AuthorBean;
import Lib.Auto.Subject.subjectbean;
import javax.servlet.annotation.WebServlet;
import com.google.gson.Gson;

@WebServlet("/Subject/SubjectServlet")



public class Subject extends HttpServlet implements Serializable {

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

	subjectbean ob = new subjectbean();

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
			
            response.setContentType("application/json");
            
			try{
				//if(!term.equalsIgnoreCase(null) && !term.equalsIgnoreCase(""))
	            //{
				String term = request.getParameter("name");
				
				//System.out.println("Data from ajax call " + term);
							    
				   ArrayList<subjectbean> list = ss.getSubjectAutoSearch(term);
			       for(subjectbean user: list){
					//System.out.println(user.getName());
				}       

				String searchList = new Gson().toJson(list);
							
				response.getWriter().write(searchList);  
							
				//System.out.println("Json Value@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+searchList);
	        // }    
		}catch(Exception e){
			e.printStackTrace();
		}  		 
			
			subjectbean ob = null;
			flag = request.getParameter("flag");
			if (flag.equals("new")) {
				ob = new subjectbean();
				ob=ss.getSubjectMax();
				request.setAttribute("beanobject", ob);
				indexPage = "/Subject/index.jsp?check=newSubject";
				dispatch(request, response, indexPage);
 
			}

			if (flag.equals("search")) {
				ob=new subjectbean();
				ob=ss.getSubjectSearch(Integer.parseInt(request.getParameter("code")));
				if(ob!=null){
					request.setAttribute("beanobject", ob);
					indexPage = "/Subject/index.jsp?check=searchSubject";
				} else {
					indexPage = "/Subject/index.jsp?check=FailSubject";
				}
				dispatch(request, response, indexPage);
			}

			if (flag.equals("update")) {
				ob=new subjectbean();
				ob.setName(request.getParameter("name").trim());
				ob.setDesc(request.getParameter("desc"));
				ob.setCode(Integer.parseInt(request.getParameter("code")));
				ob.setCallno(request.getParameter("callno").trim());
				
				int count=ss.getSubjectUpdate(ob);
				request.setAttribute("beanobject", ob);
				indexPage = "/Subject/index.jsp?check=UpdateSubject";
				dispatch(request, response, indexPage);
			}

			if (flag.equals("delete")) {
				ob=new subjectbean();
				ob=ss.getSubjectSearch(Integer.parseInt(request.getParameter("code")));	
				if(ob!=null){
					request.setAttribute("beanobject", ob);
					indexPage = "/Subject/index.jsp?check=deleteCheck";
				} else {
					request.setAttribute("beanobject", ob);
					indexPage = "/Subject/index.jsp?check=RecordNot";
				}
				dispatch(request, response, indexPage);
			}
			if (flag.equals("Confirmdelete")) {
				
				int Subject_Interface_code=ss.getSubjectInterface(Integer.parseInt(request.getParameter("code")));			

				int Subject_Mas_code=ss.getSubjectMas(Integer.parseInt(request.getParameter("code")));

				if (Subject_Interface_code == Subject_Mas_code) {
					indexPage = "/Subject/index.jsp?check=ReferredSubject";
				} else {
					int rk=Integer.parseInt(request.getParameter("code"));

					if(rk==1)
					{
						indexPage = "/Subject/index.jsp?check=DefaultSubject";
					}
					else
					{
					
					int count=ss.getSubjectDelete(Integer.parseInt(request.getParameter("code")));
					indexPage = "/Subject/index.jsp?check=DeleteSubject";
					}
				}
				dispatch(request, response, indexPage);
			}

			if (flag.equals("save")) {
				ob=new subjectbean();
				ob.setName(request.getParameter("name"));
				ob.setDesc(request.getParameter("desc"));
				ob.setCallno(request.getParameter("callno").trim());
				ob.setCode(Integer.parseInt(request.getParameter("code")));
				
				int sub_code=ss.getSubjectName(ob);
				int Subject_Interface_code=ss.getSubjectInterface(Integer.parseInt(request.getParameter("code")));	
				int Subject_Mas_code=ss.getSubjectMas(Integer.parseInt(request.getParameter("code")));

				if (sub_code >0) {
					ob.setName(request.getParameter("name"));
					ob.setDesc(request.getParameter("desc"));
					ob.setCallno(request.getParameter("callno").trim());
					ob.setCode(sub_code);
					request.setAttribute("beanobject", ob);
					//request.setAttribute("subname", String.valueOf(sub_code));
					indexPage = "/Subject/index.jsp?check=CodeCompareSubject";

				} 
				else if(Subject_Interface_code>0)
				{
					ob.setCode(Integer.parseInt(request.getParameter("code")));
					ob.setName(request.getParameter("name"));
					ob.setDesc(request.getParameter("desc"));
					ob.setCallno(request.getParameter("callno").trim());
					request.setAttribute("beanobject", ob);
					indexPage = "/Subject/index.jsp?check=UpdateCheck";
					
				}
				else if(Subject_Mas_code>0)
			   {

					ob.setCode(Integer.parseInt(request.getParameter("code")));
					ob.setName(request.getParameter("name"));
					ob.setDesc(request.getParameter("desc"));
					ob.setCallno(request.getParameter("callno").trim());
					request.setAttribute("beanobject", ob);
					indexPage = "/Subject/index.jsp?check=Updatename";
					

					} 
				else 
				{

					ob.setCode(Integer.parseInt(request.getParameter("code")));
					ob.setName(request.getParameter("name").trim());
					ob.setDesc(request.getParameter("desc"));
					ob.setCallno(request.getParameter("callno").trim());
					
					int count=ss.getSubjectSave(ob);
					indexPage = "/Subject/index.jsp?check=SaveSubject";
					}
//				}
				dispatch(request, response, indexPage);
			}
			
			if (flag.equals("Subject")) {
				List SubjectArrylist = null;
				AuthorBean authorbean = null;
				AuthorBean newauthor=null;
				
				SubjectArrylist = new ArrayList();
				ob = new subjectbean();
				ob.setName(request.getParameter("name"));				
				SubjectArrylist=ss.getSubjectSearchName(ob);

				try {

				} catch (Exception e) {
				}

				request.setAttribute("serarch", SubjectArrylist);
		     indexPage = "/Subject/search.jsp?check=ok&nam="+nam+"";
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
