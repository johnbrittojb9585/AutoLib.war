package Lib.Auto.Designation;
import java.io.IOException;

import java.io.Serializable;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.UnavailableException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.google.gson.Gson;

import Common.Security;
import Common.businessutil.BusinessServiceFactory;
import Common.businessutil.calaloging.CalalogingService;
import Lib.Auto.Author.AuthorBean;
import Lib.Auto.Book.Book;
import Lib.Auto.Course.CourseBean;


@WebServlet("/Desig/DesignationServlet")
public class Designation extends HttpServlet implements Serializable {
	 private static Logger log4jLogger = Logger.getLogger(Designation.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String flag,Designation_cd,Designation_savecode,Designation_savename,Designation_savedesc,Designation_savemail,Designation_Val_Name;
	String  name="",code="",Desi_Name="",Desi_Code="";
	int Desicode=0;int Desi_Mas_code=0;
	int Designation_Mas_code;
	int updateFlag;
	String nam="";
	String term="";
	DesignationBean ob=null;
	ArrayList<String> ob1=null;

    String indexPage = null,protocol="";
  
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
			
			
			response.setContentType("text/html");
			CalalogingService ss = BusinessServiceFactory.INSTANCE.getCalalogingService();
			
			response.setContentType("application/json");
			
			try{
				//if(!term.equalsIgnoreCase(null) && !term.equalsIgnoreCase(""))
	            //{
				String term = request.getParameter("name");
				
				//System.out.println("Data from ajax call " + term);
							    
				   ArrayList<DesignationBean> list = ss.getDesigAutoSearch(term);
			       for(DesignationBean user: list){
					//System.out.println(user.getName());
				}
			       
				String searchList = new Gson().toJson(list);
							
				response.getWriter().write(searchList);  
							
				//System.out.println("Json Value@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+searchList);
	        // }    
		}catch(Exception e){
			e.printStackTrace();
		}  				
   	        flag=request.getParameter("flag");
   	        
   	     
			if(flag.equals("new")){
				log4jLogger.info("new===========flag====="+flag);
				ob = new DesignationBean();
				ob=ss.getDesignationMax();
				request.setAttribute("DesignationBean", ob);
				indexPage = "/Desig/index.jsp?check=newDesig";
				dispatch(request, response, indexPage);

			}
			
			if(flag.equals("search")){
				log4jLogger.info("search===========flag====="+flag);
				ob = new DesignationBean();
				ob=ss.getDesignationSearch(Integer.parseInt(request.getParameter("code")));
				if(ob!=null){
					request.setAttribute("DesignationBean", ob);
					indexPage = "/Desig/index.jsp?check=searchDesig";
				}
				else
				{
					indexPage = "/Desig/index.jsp?check=FailDesig";
				}
				dispatch(request, response, indexPage);
			}
		
			if(flag.equals("update")){
				log4jLogger.info("update===========flag====="+flag);
				ob = new DesignationBean();
				ob.setCode(Integer.parseInt(request.getParameter("code")));
				ob.setName(request.getParameter("name"));
				ob.setDesc(request.getParameter("desc"));
				int count=ss.getDesigUpdate(ob);
				indexPage = "/Desig/index.jsp?check=UpdateDesig";
			    dispatch(request, response, indexPage);
			}
	
		if(flag.equals("delete")){
			log4jLogger.info("delete===========flag====="+flag);
			ob = new DesignationBean();
			ob=ss.getDesignationSearch(Integer.parseInt(request.getParameter("code")));
			
			if(ob!=null){
				request.setAttribute("DesignationBean", ob);
			indexPage = "/Desig/index.jsp?check=deleteCheck";
			} else {
				
					indexPage = "/Desig/index.jsp?check=RecordNot";
					}
					dispatch(request, response, indexPage);
	}
		
		if(flag.equals("Confirmdete")){
			log4jLogger.info("Confirmdete===========flag====="+flag);
			int Desig_Interface_code=ss.getDesigInterface(Integer.parseInt(request.getParameter("code")));			

			int Desig_Mas_code=ss.getDesigMas(Integer.parseInt(request.getParameter("code")));
			
		
			if (Desig_Interface_code == Desig_Mas_code) {					
				indexPage = "/Desig/index.jsp?check=ReferredDesig";
			}
			
			else
			{				
				int rk=Integer.parseInt(request.getParameter("code"));
				
				if(rk==1)
				{
					indexPage = "/Desig/index.jsp?check=DefaultDesig";
				}
				else
				{
				  int count=ss.getDesigDelete(Integer.parseInt(request.getParameter("code")));
				  indexPage = "/Desig/index.jsp?check=DeleteDesig";
			    }	
		   }
			dispatch(request, response, indexPage);
		}

		if(flag.equals("save")){
			log4jLogger.info("save===========flag====="+flag);
			ob = new DesignationBean();
			ob.setCode(Integer.parseInt(request.getParameter("code")));
			ob.setName(request.getParameter("name").trim());
			ob.setDesc(request.getParameter("desc"));
			int Desig_code=ss.getDesigName(ob);
			int Desig_Interface_code=ss.getDesigInterface(Integer.parseInt(request.getParameter("code")));			

			int Desig_Mas_code=ss.getDesigMas(Integer.parseInt(request.getParameter("code")));

			if (Desig_code >0){
				ob.setCode(Desig_code);
				ob.setName(request.getParameter("name").trim());
				ob.setDesc(request.getParameter("desc"));
				
				request.setAttribute("DesignationBean", ob);
				indexPage = "/Desig/index.jsp?check=CodeCompareDesig";
			}
			else if (Desig_Interface_code>0){
				ob.setCode(Integer.parseInt(request.getParameter("code")));
				ob.setName(request.getParameter("name").trim());
				ob.setDesc(request.getParameter("desc"));
				request.setAttribute("DesignationBean", ob);
				indexPage = "/Desig/index.jsp?check=UpdateCheck";
			}
			else if (Desig_Mas_code>0){
				
				ob.setCode(Integer.parseInt(request.getParameter("code")));
				ob.setName(request.getParameter("name").trim());
				ob.setDesc(request.getParameter("desc"));
				request.setAttribute("DesignationBean", ob);
				indexPage = "/Desig/index.jsp?check=Updatename";
			}
			else {
				ob = new DesignationBean();
				ob.setCode(Integer.parseInt(request.getParameter("code")));
				ob.setName(request.getParameter("name").trim());
				ob.setDesc(request.getParameter("desc"));
				int count=ss.getDesigSave(ob);
				indexPage = "/Desig/index.jsp?check=SaveDesig";
			}
			dispatch(request, response, indexPage);
		}
		
			
		
		if (flag.equals("Desig")) {
			log4jLogger.info("Search name===========flag====="+flag);
			List DesignationArrylist = null;
			DesignationBean desigbean = null;
			
			
			DesignationArrylist = new ArrayList();
			ob = new DesignationBean();
			DesignationBean ab = null;
			ab=new DesignationBean();
			ob.setName(request.getParameter("name"));				
			DesignationArrylist=ss.getDesigSearchName(ob);

			try {

			} catch (Exception e) {
			}

			request.setAttribute("serarch", DesignationArrylist);
	     indexPage = "/Desig/search.jsp?check=ok&nam="+nam+"";
		 dispatch(request, response, indexPage);

		}
		
		
		
			 } catch (Exception sss) {throw new ServletException(sss);}	
				
				finally{
					Desi_Mas_code=0;
					Desicode=0;
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
