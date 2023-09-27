package Lib.Auto.Course;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

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
import Lib.Auto.Author.Author;
import Lib.Auto.Author.AuthorBean;
import Lib.Auto.Member.MemberBean;
import Lib.Auto.Subject.subjectbean;
import javax.servlet.annotation.WebServlet;
import com.google.gson.Gson;

@WebServlet("/Course/CourseServlet")

public class Course extends HttpServlet implements Serializable {
	 private static Logger log4jLogger = Logger.getLogger(Course.class);

	String flag;
	String name="",code="",protocol="",Cour_Name="",Course_Code="";
	int Course_Mas_code=0;int Coursecode=0;
	int updateFlag;
	String term="";
	CourseBean ob=new CourseBean();

	   String indexPage = null;
	   
	String nm="";
	

	public void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException {

		performTask(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException {

		performTask(request, response);

	}
	public void performTask(
		HttpServletRequest request,
		HttpServletResponse response)throws ServletException {

		
		try {
			HttpSession session = request.getSession(true);
			String res = Security.checkSecurity(1, session, response, request);		
			if(res.equalsIgnoreCase("Failure"))
			{
				response.sendRedirect("/AutoLib/Tree/sessiontimeout.jsp");  
				return;
			}
			
			
			//response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			CalalogingService ss = BusinessServiceFactory.INSTANCE.getCalalogingService();
			
            response.setContentType("application/json");
            
			try{
				//if(!term.equalsIgnoreCase(null) && !term.equalsIgnoreCase(""))
	            //{
				String term = request.getParameter("name");
				
				//System.out.println("Data from ajax call " + term);
							    
				   ArrayList<CourseBean> list = ss.getCourseAutoSearch(term);
			       for(CourseBean user: list){
					//System.out.println(user.getName());
				}       

				String searchList = new Gson().toJson(list);
							
				response.getWriter().write(searchList);  
							
				//System.out.println("Json Value@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+searchList);
	        // }    
		}catch(Exception e){
			e.printStackTrace();
		}  		 

			
			flag = request.getParameter("flag");
			
			
			if(flag.equals("new")) {
				log4jLogger.info("start===========new=====");
				ob = new CourseBean();
				
				ob=ss.getCourseMax();
				log4jLogger.info("start===========new====="+ob.getCode());
				request.setAttribute("bean", ob);
				indexPage = "/Course/index.jsp?check=newCourse";
				dispatch(request, response, indexPage);
			}
			
						 
		if(flag.equals("search")){
			log4jLogger.info("start===========search=====");
			ob = new CourseBean();
			ob=ss.getCourseSearch(Integer.parseInt(request.getParameter("code")));
			if (ob!=null) {
				request.setAttribute("bean", ob);
			        indexPage = "/Course/index.jsp?check=searchCourse";
				} else {
					indexPage = "/Course/index.jsp?check=FailCourse";
				}

				dispatch(request, response, indexPage);
		}
		
		if(flag.equals("update")){
			log4jLogger.info("start===========update=====");

			ob=new CourseBean();
			ob.setName(request.getParameter("name"));
			ob.setMajor(request.getParameter("major"));
			ob.setPeriod(Integer.parseInt(request.getParameter("period")));
			ob.setType(request.getParameter("type"));
			ob.setDesc(request.getParameter("desc"));
			ob.setCode(Integer.parseInt(request.getParameter("code")));
			int count=ss.getCourseUpdate(ob);
		  indexPage = "/Course/index.jsp?check=UpdateCourse";
		  dispatch(request, response, indexPage);
		}
		

		if(flag.equals("delete")){
			log4jLogger.info("start===========delete=====");
			CourseBean ob=null;
			ob = new CourseBean();
			ob=ss.getCourseSearch(Integer.parseInt(request.getParameter("code")));

			if (ob!=null) {
				request.setAttribute("bean", ob);
			indexPage = "/Course/index.jsp?check=deleteCheck";
			} else {
					request.setAttribute("bean", ob);
					indexPage = "/Course/index.jsp?check=RecordNot";
					}
					dispatch(request, response, indexPage);
		}
		
		if(flag.equals("Confirmdete")){
			log4jLogger.info("start===========Confirmdete=====");

			int Course_Interface_code=ss.getCourseInterface(Integer.parseInt(request.getParameter("code")));			

			int Course_Mas_code=ss.getCourseMas(Integer.parseInt(request.getParameter("code")));
		

			if (Course_Interface_code == Course_Mas_code) {

				indexPage = "/Course/index.jsp?check=ReferredCourse";
			} else {
				
				int rk=Integer.parseInt(request.getParameter("code"));

				if(rk==1)
				{
					indexPage = "/Course/index.jsp?check=DefaultCourse";
				}
				else
				{
					int count=ss.getCourseDelete(Integer.parseInt(request.getParameter("code")));
					indexPage = "/Course/index.jsp?check=DeleteCourse";
				}
				
			}
		
			dispatch(request, response, indexPage);
			
		}

		if(flag.equals("save")){
			log4jLogger.info("start===========save=====");
			ob=new CourseBean();
			ob.setName(request.getParameter("name").trim());
			ob.setMajor(request.getParameter("major"));
			ob.setPeriod(Integer.parseInt(request.getParameter("period")));
			ob.setType(request.getParameter("type"));
			ob.setDesc(request.getParameter("desc"));
			ob.setCode(Integer.parseInt(request.getParameter("code")));
			
			int Course_code=ss.getCourseName(ob);
			
			int Course_Interface_code=ss.getCourseInterface(Integer.parseInt(request.getParameter("code")));			

			int Course_Mas_code=ss.getCourseMas(Integer.parseInt(request.getParameter("code")));
			
			if (Course_Interface_code>0)
			{
				ob.setName(request.getParameter("name").trim());
				ob.setMajor(request.getParameter("major"));
				ob.setPeriod(Integer.parseInt(request.getParameter("period")));
				ob.setType(request.getParameter("type"));
				ob.setDesc(request.getParameter("desc"));
				ob.setCode(Integer.parseInt(request.getParameter("code")));
				request.setAttribute("bean", ob);
				indexPage = "/Course/index.jsp?check=UpdateCheck";
			}

				else if (Course_Mas_code >0)
				{
					ob.setName(request.getParameter("name").trim());
					ob.setMajor(request.getParameter("major"));
					ob.setPeriod(Integer.parseInt(request.getParameter("period")));
					ob.setType(request.getParameter("type"));
					ob.setDesc(request.getParameter("desc"));
					ob.setCode(Integer.parseInt(request.getParameter("code")));
					request.setAttribute("bean", ob);
					indexPage = "/Course/index.jsp?check=Updatename";		

				}
				
					else
					 {
				
						ob.setName(request.getParameter("name").trim());
						ob.setMajor(request.getParameter("major"));
						ob.setPeriod(Integer.parseInt(request.getParameter("period")));
						ob.setType(request.getParameter("type"));
						ob.setDesc(request.getParameter("desc"));
						ob.setCode(Integer.parseInt(request.getParameter("code")));
						int count=ss.getCourseSave(ob);
				indexPage = "/Course/index.jsp?check=SaveCourse";
				
			}
			dispatch(request, response, indexPage);
		
		}
			
		if(flag.equals("Course"))
		  {
			log4jLogger.info("start===========Course=====");
			
			List CourseArrylist = new ArrayList();
			  MemberBean newbean=new MemberBean();
			  newbean=new MemberBean();
			  newbean.setName(request.getParameter("name"));
		    	CourseArrylist=ss.getCourseSearch(newbean);
		    	
				request.setAttribute("serarch", CourseArrylist);
			     indexPage = "/Course/search.jsp?check=ok&nam="+nm+"";
				dispatch(request, response, indexPage);	
		  }
			
			 }catch (Exception sss) {throw new ServletException(sss);}	
					
			finally{
				Coursecode=0;
				Course_Mas_code=0;
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
