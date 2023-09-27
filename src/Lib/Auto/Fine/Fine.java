package Lib.Auto.Fine;

import Common.Security;
import Common.businessutil.BusinessServiceFactory;
import Common.businessutil.admin.AdminService;
import Common.businessutil.calaloging.CalalogingService;
import Lib.Auto.Author.AuthorBean;
import Lib.Auto.Book.Book;
import Lib.Auto.Fine.Finebean;
import Lib.Auto.Simples.Searchbean;
import Lib.Auto.Group.GroupBean;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;
import java.util.MissingResourceException;
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
import javax.servlet.annotation.WebServlet;
import com.google.gson.Gson;

@WebServlet("/Fine/FineServlet")


public class Fine extends HttpServlet implements Serializable {
	 private static Logger log4jLogger = Logger.getLogger(Fine.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String flag,Fine_cd,Fine_savefcode,Fine_savegcode,Fine_savefperiod,Fine_savefamount,Fine_saveftype,Fine_Val_Name;
	String  Fine_Mas_Val_code,group_code="";
	String searchfcode,searchgcode,searchfperiod,searchfamount,searchftype;
	int Fine_Interface_code,Fine_Mas_code;
	String term="";
	int updateFlag;
	String indexPage = null;
	Finebean ob=new Finebean();
	singlecodecheck chkcode=new singlecodecheck();
	singlecodecheck code=new singlecodecheck();
	String nm;
	
	
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
			
			PrintWriter out = response.getWriter();
			AdminService ss = BusinessServiceFactory.INSTANCE.getAdminService();
			CalalogingService ss1 = BusinessServiceFactory.INSTANCE.getCalalogingService();
			
            response.setContentType("application/json");
			
			try{
				String term = request.getParameter("FCODE");
				if(!term.equalsIgnoreCase(null) && !term.equalsIgnoreCase(""))
	            {
				System.out.println("Data from ajax call " + term);
							    
				   ArrayList<Finebean> list = ss1.getFineCodeAutoSearch(term);
			       for(Finebean user: list){
					System.out.println(user.getFcode());
				}       

				String searchList = new Gson().toJson(list);
							
				response.getWriter().write(searchList);  
							
				System.out.println("Json Value@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+searchList);
	            }	
		}catch(Exception e){
			//e.printStackTrace();
		}  		 


		try{
				String term = request.getParameter("GROUPNAME");
				
				if(!term.equalsIgnoreCase(null) && !term.equalsIgnoreCase(""))
	             {
				System.out.println("Data from ajax call " + term);
							    
				   ArrayList<GroupBean> list = ss1.getFineGroupAutoSearch(term);
			       for(GroupBean user: list){
					System.out.println(user.getName());
				}       

				String searchList = new Gson().toJson(list);
							
				response.getWriter().write(searchList);  
							
				System.out.println("Json Value@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+searchList);
	             }	
		}catch(Exception e){
			//e.printStackTrace();
		}    		 

    
			flag=request.getParameter("flag");
	
			
			
			if ((request.getParameter("sflag"))!=null){
				
				flag=request.getParameter("sflag");
			}
			
			 nm=code.getParam( request, "name");
			 
						 
			 if(flag.equals("Fine")){
					log4jLogger.info("start===========Fine Search=====");
					List FineArrylist = new ArrayList();
					ob = new Finebean();
								
					if(request.getParameter("name")!=null){
						String fcode=request.getParameter("name");
						FineArrylist=ss.getFineSearchName(fcode);
			
						request.setAttribute("serarch", FineArrylist);
				        indexPage = "/Fine/search.jsp?check=ok&flag="+flag+"&nam="+nm+"";
				    	dispatch(request, response, indexPage);
					}
					
				}
			
			if(flag.equals("new")){
				log4jLogger.info("start===========new=====");
				ob = new Finebean();
				ob=ss.getFineMax();
				request.setAttribute("bean", ob);			
				indexPage = "/Fine/index.jsp?check=newFine";
				dispatch(request, response, indexPage);

		}

		if(flag.equals("search")){
			
			log4jLogger.info("start===========search====="+request.getParameter("FCODE"));
			ob = new Finebean();
			ob=ss.getFineSearch(Integer.parseInt(request.getParameter("FCODE")));
			if(ob!=null){
				request.setAttribute("bean", ob);
				
				indexPage = "/Fine/index.jsp?check=searchFine";	
			}else{
				indexPage = "/Fine/index.jsp?check=FailFine";
			}
			dispatch(request, response, indexPage);
		
		}
		
		
		if(flag.equals("update")){
			log4jLogger.info("start===========update====="+request.getParameter("FCODE"));
			ob.setFcode(Integer.parseInt(request.getParameter("FCODE")));
			ob.setGcode(request.getParameter("GROUPNAME"));
			ob.setFperiod(request.getParameter("PERIOD"));
			ob.setFamount(request.getParameter("AMOUNT"));
			ob.setType(request.getParameter("FTYPE"));
			
			int count=ss.getFineUpdate(ob);
			
			indexPage = "/Fine/index.jsp?check=UpdateFine";
			dispatch(request, response, indexPage);
		
		}
		
		
		
		if(flag.equals("delete")){
			log4jLogger.info("start===========delete====="+request.getParameter("FCODE"));
		ob = new Finebean();				
		ob=ss.getFineSearch(Integer.parseInt(request.getParameter("FCODE")));			
		if(ob!=null){				
			request.setAttribute("bean", ob);
			indexPage = "/Fine/index.jsp?check=deleteCheck";
		}else{
			request.setAttribute("bean", ob);
			indexPage = "/Fine/index.jsp?check=FailFine";	
		}
			dispatch(request, response, indexPage);
		}
		
		
				
		if(flag.equals("Confirmdete")){
			log4jLogger.info("start===========Confirmdete====="+request.getParameter("FCODE"));
			int count=ss.getFineDelete(Integer.parseInt(request.getParameter("FCODE")));
			indexPage = "/Fine/index.jsp?check=DeleteFine";
			dispatch(request, response, indexPage);
		
		}
		
		
		if(flag.equals("save")){
			log4jLogger.info("start===========save====="+request.getParameter("FCODE"));
			ob = new Finebean();
			ob.setFcode(Integer.parseInt(request.getParameter("FCODE")));
			ob.setGcode(request.getParameter("GROUPNAME"));
			ob.setFperiod(request.getParameter("PERIOD"));
			ob.setFamount(request.getParameter("AMOUNT"));
			ob.setType(request.getParameter("FTYPE"));
			
			ob=ss.getFineSearch(Integer.parseInt(request.getParameter("FCODE")));
			
			if(ob!=null){
				
				ob.setFcode(Integer.parseInt(request.getParameter("FCODE")));
				ob.setGcode(request.getParameter("GROUPNAME"));
				ob.setFperiod(request.getParameter("PERIOD"));
				ob.setFamount(request.getParameter("AMOUNT"));
				ob.setType(request.getParameter("FTYPE"));
				request.setAttribute("bean", ob);
				indexPage = "/Fine/index.jsp?check=UpdateCheck";
			}else{

				ob = new Finebean();		
				ob.setFcode(Integer.parseInt(request.getParameter("FCODE")));
				ob.setGcode(request.getParameter("GROUPNAME"));
				ob.setFperiod(request.getParameter("PERIOD"));
				ob.setFamount(request.getParameter("AMOUNT"));
				ob.setType(request.getParameter("FTYPE"));
				
				int count=ss.getFineSave(ob);
				
				indexPage = "/Fine/index.jsp?check=SaveFine";
			}
			dispatch(request, response, indexPage);
		}
		
		
			
		
	    if(flag.equals("Group"))
		 {
	    	log4jLogger.info("start===========Group Search=====");
			List FineArrylist = new ArrayList();
			ob = new Finebean();
						
			if(request.getParameter("name")!=null){
				String fcode=request.getParameter("name");
				FineArrylist=ss.getGroupSearchName(fcode);
				request.setAttribute("serarch", FineArrylist);
		        indexPage = "/Fine/search.jsp?check=ok&flag="+flag+"&nam="+nm+"";
		    	dispatch(request, response, indexPage);
			}
		 }
		
	
			 } catch (Exception e) {
					
					}
		    catch (Throwable theException) {
			
		   }
		    finally{
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
