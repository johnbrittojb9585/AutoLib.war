package Lib.Auto.Binding;
import Common.DataQuery;
import Common.Security;
import Common.businessutil.BusinessServiceFactory;
import Common.businessutil.calaloging.CalalogingService;
import Lib.Auto.Author.Author;
import Lib.Auto.Author.AuthorBean;
import Lib.Auto.Binding.BindingBean;
import Lib.Auto.Transfer_Books.BookTransferBean;

//import java.io.ByteArrayOutputStream;
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

import javax.servlet.annotation.WebServlet;

import com.google.gson.Gson;

@WebServlet("/Binding/BindingServlet")

public class Binding extends HttpServlet implements Serializable {
	 private static Logger log4jLogger = Logger.getLogger(Binding.class);
	/**
	 * 
	 * 
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String flag,suppubname,newname;
	String protocol,SameCode,pubsupname;
	int PubSup_Mas_Val_code;
		
	String term="";
	String indexPage = null; 
	
	BindingBean ob=new BindingBean();

	
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException {

		performTask(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException {

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
			
			PrintWriter out=response.getWriter();
			Security.checkSecurity(1, session, response, request);
			CalalogingService ss = BusinessServiceFactory.INSTANCE.getCalalogingService();
			
			response.setContentType("application/json");
            
			try{
				//if(!term.equalsIgnoreCase(null) && !term.equalsIgnoreCase(""))
	            //{
				String term = request.getParameter("sp_name");
				
				//System.out.println("Data from ajax call " + term);
							    
				   ArrayList<BindingBean> list = ss.getBindingAutoSearch(term);
			       for(BindingBean user: list){
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
			
			String nm=request.getParameter("name");
			
			if(flag.equals("new")){
				log4jLogger.info("start===========new=====");
				
				ob=ss.getBindingMax();
				request.setAttribute("beanobject", ob);
				indexPage = "/Binding/index.jsp?check=newbinding";
				dispatch(request, response, indexPage);

		}
			

					
			if(flag.equals("search")){
				log4jLogger.info("start===========search=====");
				
				ob=ss.getBindingSearch(Integer.parseInt(request.getParameter("sp_no")));
				
				if(ob!=null){
					request.setAttribute("beanobject", ob);
					indexPage = "/Binding/index.jsp?check=searchbinding";
					
				}else{
					
					indexPage = "/Binding/index.jsp?check=Failbinding";
				}
				
				dispatch(request, response, indexPage);
			}
		
			if(flag.equals("update")){
				log4jLogger.info("start===========update=====");
				ob=new BindingBean();
				ob.setCode(Integer.parseInt(request.getParameter("sp_no")));
				pubsupname=request.getParameter("sp_name");
				ob.setName(pubsupname);
				ob.setDesc(request.getParameter("sp_shortdesc"));
				ob.setAdd1(request.getParameter("sp_address1"));
				ob.setAdd2(request.getParameter("sp_address2"));
				ob.setCity(request.getParameter("sp_city"));
				ob.setState(request.getParameter("sp_state"));
				ob.setCountry(request.getParameter("sp_country"));
				ob.setPin(request.getParameter("sp_pincode"));
				ob.setPhone(request.getParameter("sp_phone"));
				ob.setFax(request.getParameter("sp_fax"));
				ob.setEmail(request.getParameter("sp_email"));
				ob.setUrl(request.getParameter("sp_url"));
				
				int code=ss.getBindingUpdate(ob);
				indexPage = "/Binding/index.jsp?check=Updatebinding";
				dispatch(request, response, indexPage);
			}
		
		
			if(flag.equals("delete")){
				log4jLogger.info("start===========delete=====");
				
				ob=ss.getBindingSearch(Integer.parseInt(request.getParameter("sp_no")));
				
				if(ob!=null){
					request.setAttribute("beanobject", ob);
					indexPage = "/Binding/index.jsp?check=deleteCheck";
					
				}else{
					
					indexPage = "/Binding/index.jsp?check=Failbinding";
				}
				
				dispatch(request, response, indexPage);
			}

		
			if(flag.equals("Confirmdete")){
				log4jLogger.info("start===========Confirmdete=====");
				ob.setCode(Integer.parseInt(request.getParameter("sp_no")));
				
				int count=ss.getBindingDelete(ob);
				
				if(count>0){
					indexPage = "/Binding/index.jsp?check=Referredbinding";
					
				}else{
					
					indexPage = "/Binding/index.jsp?check=Deletebinding";
				}
				dispatch(request, response, indexPage);
			}
		
			if(flag.equals("save")){
				log4jLogger.info("start===========save=====");
				ob=ss.getBindingSearch(Integer.parseInt(request.getParameter("sp_no")));
				
				int binber_code=ss.getBindingName(request.getParameter("sp_name"));
				
				
				if(ob!=null){
					
					ob.setCode(Integer.parseInt(request.getParameter("sp_no")));
					pubsupname=request.getParameter("sp_name");
					ob.setName(pubsupname);
					ob.setDesc(request.getParameter("sp_shortdesc"));
					ob.setAdd1(request.getParameter("sp_address1"));
					ob.setAdd2(request.getParameter("sp_address2"));
					ob.setCity(request.getParameter("sp_city"));
					ob.setState(request.getParameter("sp_state"));
					ob.setCountry(request.getParameter("sp_country"));
					ob.setPin(request.getParameter("sp_pincode"));
					ob.setPhone(request.getParameter("sp_phone"));
					ob.setFax(request.getParameter("sp_fax"));
					ob.setEmail(request.getParameter("sp_email"));
					ob.setUrl(request.getParameter("sp_url"));
					
					request.setAttribute("beanobject", ob);									
					indexPage = "/Binding/index.jsp?check=UpdateCheck";
					
				}else if(binber_code>0){
					ob=new BindingBean();
					ob.setCode(binber_code);
					pubsupname=request.getParameter("sp_name");
					ob.setName(pubsupname);
					ob.setDesc(request.getParameter("sp_shortdesc"));
					ob.setAdd1(request.getParameter("sp_address1"));
					ob.setAdd2(request.getParameter("sp_address2"));
					ob.setCity(request.getParameter("sp_city"));
					ob.setState(request.getParameter("sp_state"));
					ob.setCountry(request.getParameter("sp_country"));
					ob.setPin(request.getParameter("sp_pincode"));
					ob.setPhone(request.getParameter("sp_phone"));
					ob.setFax(request.getParameter("sp_fax"));
					ob.setEmail(request.getParameter("sp_email"));
					ob.setUrl(request.getParameter("sp_url"));
					
					request.setAttribute("beanobject", ob);
					indexPage = "/Binding/index.jsp?check=CodeComparebinding";
				}else{
					ob=new BindingBean();
					
					ob.setCode(Integer.parseInt(request.getParameter("sp_no")));
					pubsupname=request.getParameter("sp_name");
					ob.setName(pubsupname);
					ob.setDesc(request.getParameter("sp_shortdesc"));
					ob.setAdd1(request.getParameter("sp_address1"));
					ob.setAdd2(request.getParameter("sp_address2"));
					ob.setCity(request.getParameter("sp_city"));
					ob.setState(request.getParameter("sp_state"));
					ob.setCountry(request.getParameter("sp_country"));
					ob.setPin(request.getParameter("sp_pincode"));
					ob.setPhone(request.getParameter("sp_phone"));
					ob.setFax(request.getParameter("sp_fax"));
					ob.setEmail(request.getParameter("sp_email"));
					ob.setUrl(request.getParameter("sp_url"));
					
					int code=ss.getBindingSave(ob);
					
					
					indexPage = "/Binding/index.jsp?check=Savebinding";
				}
				
				dispatch(request, response, indexPage);
				
			}
		
			if(flag.equals("Bind")){
				log4jLogger.info("start===========Bind=====");
				List BinderArrylist = new ArrayList();
				
			
				
				if(request.getParameter("name")!=null){
					
					BinderArrylist=ss.getBinderSearchName(request.getParameter("name"));
					request.setAttribute("serarch", BinderArrylist);
			        indexPage = "/Binding/search.jsp?check=ok&flag="+flag+"&nam="+nm+"";
			    	dispatch(request, response, indexPage);
				}
				
			}
		
			
			 } catch (Exception sss) {
					throw new ServletException(sss);
					
				} finally {
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
	

	
	
}

