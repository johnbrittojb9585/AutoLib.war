package Lib.Auto.Branch;
import Lib.Auto.Author.AuthorBean;
import Lib.Auto.Book.Book;
import Lib.Auto.Branch.BranchBean;
import Lib.Auto.Subject.subjectbean;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
//import java.sql.DatabaseMetaData;

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
import Common.businessutil.calaloging.CalalogingService;


public class Branch extends HttpServlet implements Serializable {
	private static Logger log4jLogger = Logger.getLogger(Branch.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String flag,Branch_cd,Branch_savecode,Branch_savename,Branch_savedesc,Branch_savemail,Branch_Val_Name;
	String  name="",code="",protocol="";
	int Branch_Mas_code;
	
	String indexPage = null;
	int updateFlag;
	BranchBean ob=new BranchBean();

	singlecodecheck chkcode=new singlecodecheck();
	
	
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) {

		performTask(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) {

		performTask(request, response);

	}
	public void performTask(
		HttpServletRequest request,
		HttpServletResponse response) {

		
		try {
			HttpSession session = request.getSession(true);
			String res = Security.checkSecurity(1, session, response, request);		
			if(res.equalsIgnoreCase("Failure"))
			{
				response.sendRedirect("/AutoLib/Tree/sessiontimeout.jsp");  
				return;
			}
			
			
			response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            CalalogingService ss = BusinessServiceFactory.INSTANCE.getCalalogingService();
            BranchBean ob = null;
			
			flag=request.getParameter("flag");
			String nm=request.getParameter("name");
			
			//out.print(flag);
			
			if(flag.equals("Branch")){
				
				log4jLogger.info("Branch===========flag====="+flag);
				List BranchArrylist = new ArrayList();
				
				if(request.getParameter("name")!=null){
				
					BranchArrylist=ss.getBranchSearchName(request.getParameter("name"));
					request.setAttribute("serarch", BranchArrylist);
			        indexPage = "/Branch/search.jsp?check=ok";
			    	dispatch(request, response, indexPage);
				}
				
			}
			
			if(flag.equals("new")){
				log4jLogger.info("New===========flag====="+flag);
				ob = new BranchBean();
				ob=ss.getBranchMax();

				request.setAttribute("BranchBean", ob);
				indexPage = "/Branch/index.jsp?check=newBranch";
				dispatch(request, response, indexPage);
			
		}
			if(flag.equals("search")){
				log4jLogger.info("search===========flag====="+flag);
				ob = new BranchBean();
				ob=ss.getBranchSearch(Integer.parseInt(request.getParameter("code")));
				if(ob!=null){
					request.setAttribute("BranchBean", ob);
					indexPage = "/Branch/index.jsp?check=searchBranch";
				}
				else
				{
					indexPage = "/Branch/index.jsp?check=FailBranch";
				}
				dispatch(request, response, indexPage);
			}
		
			if(flag.equals("update")){
				log4jLogger.info("update===========flag====="+flag);
				ob = new BranchBean();
				
				ob.setCode(Integer.parseInt(request.getParameter("code")));
				ob.setName(request.getParameter("name"));
				ob.setDesc(request.getParameter("desc"));
				
				int count=ss.getBranchUpdate(ob);
				indexPage = "/Branch/index.jsp?check=UpdateBranch";
				dispatch(request, response, indexPage);
			}
		
		
			if(flag.equals("delete")){
				log4jLogger.info("delete===========flag====="+flag);
				ob = new BranchBean();
				ob=ss.getBranchSearch(Integer.parseInt(request.getParameter("code")));
				
				if(ob!=null){
					request.setAttribute("BranchBean", ob);
					indexPage = "/Branch/index.jsp?check=deleteCheck";
				}
				else
				{
					indexPage = "/Branch/index.jsp?check=FailBranch";
				}
				dispatch(request, response, indexPage);
				
				
			}
		
			if(flag.equals("Confirmdete")){
				log4jLogger.info("Confirmdete===========flag====="+flag);
				int Branch_Interface_code=ss.getBranchInterface(Integer.parseInt(request.getParameter("code")));			

				int Branch_Mas_code=ss.getBranchMas(Integer.parseInt(request.getParameter("code")));
				
				if (Branch_Interface_code == Branch_Mas_code) {
					
					indexPage = "/Branch/index.jsp?check=ReferredBranch";
				}
				else{
					int rk=Integer.parseInt(request.getParameter("code"));

					if(rk==1)
					{
						indexPage = "/Branch/index.jsp?check=DefaultBranch";
					}
					else
					{
					 int count=ss.getBranchDelete(Integer.parseInt(request.getParameter("code")));
					 indexPage = "/Branch/index.jsp?check=DeleteBranch";
				    }
				}
				dispatch(request, response, indexPage);
			}
		
			if(flag.equals("save")){
				log4jLogger.info("save===========flag====="+flag);
				
				
				ob = new BranchBean();
			
				ob.setName(request.getParameter("name").trim());
				ob.setDesc(request.getParameter("desc"));
				ob.setCode(Integer.parseInt(request.getParameter("code")));
				
				
				int Branch_code=ss.getBranchName(ob);
				
				
				int Branch_Interface_code=ss.getBranchInterface(Integer.parseInt(request.getParameter("code")));			

				int Branch_Mas_code=ss.getBranchMas(Integer.parseInt(request.getParameter("code")));
				
				if(Branch_code>0){
					ob.setName(request.getParameter("name").trim());
					ob.setDesc(request.getParameter("desc"));
					ob.setCode(Branch_code);
					//request.setAttribute("branchcode",String.valueOf(Branch_code));
					request.setAttribute("BranchBean", ob);
					indexPage = "/Branch/index.jsp?check=CodeCompareBranch";
				}
				else if(Branch_Interface_code>0){
					
					ob = new BranchBean();
					
					ob.setCode(Integer.parseInt(request.getParameter("code")));
					ob.setName(request.getParameter("name").trim());
					ob.setDesc(request.getParameter("desc"));
					request.setAttribute("BranchBean", ob);
					indexPage = "/Branch/index.jsp?check=UpdateCheck";
				}
				else if(Branch_Mas_code>0){
					ob = new BranchBean();
					
					ob.setCode(Integer.parseInt(request.getParameter("code")));
					ob.setName(request.getParameter("name").trim());
					ob.setDesc(request.getParameter("desc"));
					request.setAttribute("BranchBean", ob);
					indexPage = "/Branch/index.jsp?check=Updatename";
				}
				else{
					ob = new BranchBean();
					
					ob.setCode(Integer.parseInt(request.getParameter("code")));
					ob.setName(request.getParameter("name").trim());
					ob.setDesc(request.getParameter("desc"));
					int count=ss.getBranchSave(ob);
					indexPage = "/Branch/index.jsp?check=SaveBranch";
				}
				dispatch(request, response, indexPage);
			}
		
		 
			
			 } catch (Exception e) {
					
					}
		    catch (Throwable theException) {
			
		   }
		    finally
		    {
		    	try{
					

				}catch(Exception e){
				e.printStackTrace();
				}
		    }
		   
		   
		  
		   
		   
	}

	public void dispatch(HttpServletRequest request,
			HttpServletResponse response, String indexPage)
			throws ServletException, IOException {
		RequestDispatcher dispatch = request.getRequestDispatcher(indexPage);
		dispatch.forward(request, response);
	}
	
	
	

	/**
	 * Instance variable for SQL statement property
	 */

	
	

	
	
}
