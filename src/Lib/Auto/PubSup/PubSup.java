package Lib.Auto.PubSup;
import Common.DataQuery;
import Common.Security;
import Lib.Auto.Author.AuthorBean;
import Lib.Auto.Designation.Designation;
import Lib.Auto.Designation.DesignationBean;
import Lib.Auto.PubSup.PubSupBean;
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

import Common.businessutil.BusinessServiceFactory;
import Common.businessutil.calaloging.CalalogingService;

import javax.servlet.annotation.WebServlet;
import com.google.gson.Gson;

@WebServlet("/PubSup/PubSupServlet")


public class PubSup extends HttpServlet implements Serializable {
	 private static Logger log4jLogger = Logger.getLogger(PubSup.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String flag,flagl,suppubname,newname;
	String protocol,SameCode,pubsupname;
	int PubSup_Mas_Val_code;
	
	String valid="";
	String nm="";
	String term="";
	String indexPage = null; 
	
	PubSupBean ob=new PubSupBean();
	
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
			CalalogingService ss = BusinessServiceFactory.INSTANCE.getCalalogingService();
			
            response.setContentType("application/json");
            
			try{
				//if(!term.equalsIgnoreCase(null) && !term.equalsIgnoreCase(""))
	            //{
				String term = request.getParameter("sp_name");
				String type = request.getParameter("sptype");
				System.out.println("Json Value@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+type);
				
				//System.out.println("Data from ajax call " + term);
							    
				   ArrayList<PubSupBean> list = ss.getPubSupAutoSearch(term,type);
			       for(PubSupBean user: list){
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
			flagl=request.getParameter("flagl");
				//System.out.println(flag+"flag value get successfulllyll");
			System.out.println("flag value get succeess in servlet "+flag);
			String type=request.getParameter("flag");
			
			if(flag.equals("new")){
				log4jLogger.info("new===========flag====="+flag);
				ob = new PubSupBean();
				ob=ss.getPubSupMax();
				request.setAttribute("beanobject", ob);
				indexPage = "/PubSup/index.jsp?check=newPubSup";
				dispatch(request, response, indexPage);

		}
			if(flag.equals("search")){
				log4jLogger.info("search===========flag====="+flag);
				ob = new PubSupBean();
				ob=ss.getPubSupSearch(Integer.parseInt(request.getParameter("sp_no")));
				if(ob!=null){
					request.setAttribute("beanobject", ob);
					indexPage = "/PubSup/index.jsp?check=searchPubSup";
				}
				else{
					indexPage = "/PubSup/index.jsp?check=FailPubSup";
				}
				dispatch(request, response, indexPage);
			}
			
		
			if(flag.equals("update")){
				log4jLogger.info("update===========flag====="+flag);
				ob = new PubSupBean();
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
				String PubSuptype=request.getParameter("sup");
				if(PubSuptype==null)
				{
					PubSuptype=request.getParameter("pub");
				}
				ob.setType(PubSuptype);
				int count=ss.getPubSupUpdate(ob);
				indexPage = "/PubSup/index.jsp?check=UpdatePubSup";
				dispatch(request, response, indexPage);
			}
		
			if(flag.equals("delete")){
				log4jLogger.info("delete===========flag====="+flag);
				ob = new PubSupBean();
				ob=ss.getPubSupSearch(Integer.parseInt(request.getParameter("sp_no")));
				if(ob!=null){
					request.setAttribute("beanobject", ob);
					indexPage = "/PubSup/index.jsp?check=deleteCheck";
				}
				else{
					indexPage = "/PubSup/index.jsp?check=RecordNot";
				}
				dispatch(request, response, indexPage);
			}
			
		
			
			if(flag.equals("Confirmdete")){
				log4jLogger.info("Confirmdete===========flag====="+flag);
				int PubSup_Interface_code=ss.getPubSupInterface(Integer.parseInt(request.getParameter("sp_no")));			

				int PubSup_Mas_code=ss.getPubSupMas(Integer.parseInt(request.getParameter("sp_no")));
				
				if (PubSup_Interface_code == PubSup_Mas_code) {
					
					indexPage = "/PubSup/index.jsp?check=ReferredPubSup";
				}
				else{
					int rk=Integer.parseInt(request.getParameter("sp_no"));

					if(rk==1 || rk==2)
					{
						indexPage = "/PubSup/index.jsp?check=DefaultSupPub";
					}
					else
					{
						int count=ss.getPubSupDelete(Integer.parseInt(request.getParameter("sp_no")));
						indexPage = "/PubSup/index.jsp?check=DeletePubSup";
					}
					
				}
				dispatch(request, response, indexPage);
			}
		

			if(flag.equals("save")){
				log4jLogger.info("save===========flag====="+flag);
				ob = new PubSupBean();
				ob.setCode(Integer.parseInt(request.getParameter("sp_no")));
				pubsupname=request.getParameter("sp_name").trim();
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
				String PubSuptype=request.getParameter("sup");
				if(PubSuptype==null)
				{
					PubSuptype=request.getParameter("pub");
				}
				ob.setType(PubSuptype);
				int PubSup_code=ss.getPubSupName(ob);
				
				int PubSup_Interface_code=ss.getPubSupInterface(Integer.parseInt(request.getParameter("sp_no")));			

				int PubSup_Mas_code=ss.getPubSupMas(Integer.parseInt(request.getParameter("sp_no")));
										
				
				if (PubSup_code >0){
					ob.setCode(PubSup_code);
					pubsupname=request.getParameter("sp_name").trim();
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
					 PubSuptype=request.getParameter("sup");
					if(PubSuptype==null)
					{
						PubSuptype=request.getParameter("pub");
					}
					ob.setType(PubSuptype);
					request.setAttribute("beanobject", ob);
					//request.setAttribute("suppub",String.valueOf(PubSup_code));
					indexPage = "/PubSup/index.jsp?check=CodeCompareSupPub";
				}
				else if (PubSup_Interface_code>0){
					ob.setCode(Integer.parseInt(request.getParameter("sp_no")));
					pubsupname=request.getParameter("sp_name").trim();
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
					PubSuptype=request.getParameter("sup");
					if(PubSuptype==null)
					{
						PubSuptype=request.getParameter("pub");
					}
					ob.setType(PubSuptype);
					request.setAttribute("beanobject", ob);
					indexPage = "/PubSup/index.jsp?check=UpdateCheck";
				}
				else if (PubSup_Mas_code>0){
					
					ob.setCode(Integer.parseInt(request.getParameter("sp_no")));
					pubsupname=request.getParameter("sp_name").trim();
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
					PubSuptype=request.getParameter("sup");
					if(PubSuptype==null)
					{
						PubSuptype=request.getParameter("pub");
					}
					ob.setType(PubSuptype);
					request.setAttribute("beanobject", ob);
					indexPage = "/PubSup/index.jsp?check=Updatename";
				}
				else{
					
					ob.setCode(Integer.parseInt(request.getParameter("sp_no")));
					pubsupname=request.getParameter("sp_name").trim();
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
					PubSuptype=request.getParameter("sup");
					if(PubSuptype==null)
					{
						PubSuptype=request.getParameter("pub");
					}
					ob.setType(PubSuptype);
					int count=ss.getPubSupSave(ob);
					indexPage = "/PubSup/index.jsp?check=SavePubSup";
				}
				dispatch(request, response, indexPage);
			}
				
			if (flag.equals("Sup")) {
				log4jLogger.info("Sup Search name===========flag====="+flag);
				List PubSupArrylist = null;
			
				
				
				PubSupArrylist = new ArrayList();
				ob = new PubSupBean();
				PubSupBean ab = null;
				ab=new PubSupBean();
				ob.setName(request.getParameter("name"));				
				PubSupArrylist=ss.getSupSearchName(ob);

				try {

				} catch (Exception e) {
				}

			request.setAttribute("serarch", PubSupArrylist);
		     indexPage = "/PubSup/search.jsp?check=ok&valid=selectPub&nam="+nm+"";
			 dispatch(request, response, indexPage);

			}
		 
			if (flag.equals("Pub")) {
				log4jLogger.info("Pub Search name===========flag====="+flag);
				List PubSupArrylist = null;
				
				
				
				PubSupArrylist = new ArrayList();
			
				PubSupBean ab = null;
				ab=new PubSupBean();
				ob.setName(request.getParameter("name"));				
				PubSupArrylist=ss.getPubSearchName(ob);

				try {

				} catch (Exception e) {
				}

				request.setAttribute("serarch", PubSupArrylist);
		     indexPage = "/PubSup/search.jsp?check=ok&flag="+type+"&nam="+nm+"";
			 dispatch(request, response, indexPage);

			}
			
			
			
			
			
			 } catch (Exception sss) {
					throw new ServletException(sss);
					//sss.printStackTrace();
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
