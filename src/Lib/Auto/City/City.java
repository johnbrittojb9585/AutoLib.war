package Lib.Auto.City;


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
import Common.businessutil.calaloging.CalalogingService;
import Lib.Auto.Author.AuthorBean;

import javax.servlet.annotation.WebServlet;
import com.google.gson.Gson;

@WebServlet("/City/CityServlet")

public class City extends HttpServlet implements Serializable {
	 private static Logger log4jLogger = Logger.getLogger(City.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String flag;
	String protocol="";
	String term="";
	String name="",code="",state="",country="";
	int City_Mas_code=0;int Citycode=0;
	int updateFlag;

	String nam="";
	CityBean ob=new CityBean();

	String indexPage = null;
	//CityBean ob = null;
	ArrayList<CityBean> ob1=null;

	
	
	
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
			PrintWriter out = response.getWriter();
			CalalogingService ss = BusinessServiceFactory.INSTANCE.getCalalogingService();
			
            response.setContentType("application/json");
            
			try{
				//if(!term.equalsIgnoreCase(null) && !term.equalsIgnoreCase(""))
	            //{
				String term = request.getParameter("name");
				
				//System.out.println("Data from ajax call " + term);
							    
				   ArrayList<CityBean> list = ss.getCityAutoSearch(term);
			       for(CityBean user: list){
					//System.out.println(user.getName());
				}       

				String searchList = new Gson().toJson(list);
							
				response.getWriter().write(searchList);  
							
				//System.out.println("Json Value@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+searchList);
	        // }    
		}catch(Exception e){
			e.printStackTrace();
		}  		 

			
			CityBean ob = null;
			
			flag = request.getParameter("flag");
			
			String nm=request.getParameter("name");
			
			if(flag.equals("new")){
				log4jLogger.info("start===========new=====");
				ob = new CityBean();
				ob=ss.getCityMax();
				
				request.setAttribute("CityBean", ob);	
				indexPage = "/City/index.jsp?check=newCity";
				dispatch(request, response, indexPage);
					}
			
	
						 
		if(flag.equals("search")){
			log4jLogger.info("start===========search=====");
			ob = new CityBean();
			
			ob=ss.getCitySearch(Integer.parseInt(request.getParameter("code")));
			if(ob!=null){
			request.setAttribute("CityBean", ob);	
			indexPage = "/City/index.jsp?check=searchCity";
			}
			else
			{
				indexPage = "/City/index.jsp?check=FailCity";
			}
			
			dispatch(request, response, indexPage);
		}				
					
		
		if(flag.equals("update")){
			log4jLogger.info("start===========update=====");
			ob = new CityBean();
			ob.setName(request.getParameter("name").trim());
			ob.setDesc(request.getParameter("desc"));
			ob.setState(request.getParameter("state"));
			ob.setCountry(request.getParameter("country"));
			ob.setPincode(Integer.parseInt(request.getParameter("pcode")));
			ob.setCode(Integer.parseInt(request.getParameter("code")));
			int count=ss.getCityUpdate(ob);

			request.setAttribute("CityBean", ob);	
			indexPage = "/City/index.jsp?check=UpdateAuthor";
			dispatch(request, response, indexPage);
		}
		
		if(flag.equals("delete")){
			log4jLogger.info("start===========delete=====");
			ob = new CityBean();
			
			ob=ss.getCitySearch(Integer.parseInt(request.getParameter("code")));
			if(ob!=null){
			request.setAttribute("CityBean", ob);	
	
				indexPage = "/City/index.jsp?check=deleteCheck";
					} 
		 else 
					{
			     request.setAttribute("CityBean", ob);	
				indexPage = "/City/index.jsp?check=RecordNot";
					}
			dispatch(request, response, indexPage);
	}
		if(flag.equals("Confirmdete")){
			log4jLogger.info("start===========Confirmdete=====");
			int City_Interface_code=ss.getCityInterface(Integer.parseInt(request.getParameter("code")));			

			int City_Mas_code=ss.getCityMas(Integer.parseInt(request.getParameter("code")));

			if (City_Interface_code == City_Mas_code) {
				indexPage = "/City/index.jsp?check=ReferredCity";
		} 
		else {
			int count=ss.getCityDelete(Integer.parseInt(request.getParameter("code")));

					indexPage = "/City/index.jsp?check=DeleteCity";
				}
				dispatch(request, response, indexPage);
			
		}

		if(flag.equals("save")){
			log4jLogger.info("start===========save=====");
			ob = new CityBean();
			ob.setCode(Integer.parseInt(request.getParameter("code")));
			ob.setName(request.getParameter("name").trim());
			ob.setState(request.getParameter("state"));
			ob.setCountry(request.getParameter("country"));
			ob.setPincode(Integer.parseInt(request.getParameter("pcode")));
			ob.setDesc(request.getParameter("desc"));
			int City_code=ss.getCityName(ob);
			int City_Interface_code=ss.getCityInterface(Integer.parseInt(request.getParameter("code")));	
			int City_Mas_code=ss.getCityMas(Integer.parseInt(request.getParameter("code")));

			if (City_code >0) {
				ob.setCode(City_code);
				ob.setName(request.getParameter("name").trim());
				ob.setDesc(request.getParameter("desc"));
				ob.setState(request.getParameter("state"));
				ob.setCountry(request.getParameter("country"));
				ob.setPincode(Integer.parseInt(request.getParameter("pcode")));
				//request.setAttribute("Cityname",String.valueOf(City_code));
				request.setAttribute("CityBean", ob);
				indexPage = "/City/index.jsp?check=CodeCompareCity";

			} 
			else if (City_Interface_code>0)
			{
				ob.setCode(Integer.parseInt(request.getParameter("code")));
				ob.setName(request.getParameter("name"));
				ob.setDesc(request.getParameter("desc"));
				ob.setState(request.getParameter("state"));
				ob.setCountry(request.getParameter("country"));
				ob.setPincode(Integer.parseInt(request.getParameter("pcode")));
				request.setAttribute("CityBean", ob);
				indexPage = "/City/index.jsp?check=UpdateCheck";
			}

				else if (City_Mas_code >0)
				{
					ob.setCode(Integer.parseInt(request.getParameter("code")));
					ob.setName(request.getParameter("name"));
					ob.setDesc(request.getParameter("desc"));
					ob.setState(request.getParameter("state"));
					ob.setCountry(request.getParameter("country"));
					ob.setPincode(Integer.parseInt(request.getParameter("pcode")));
					request.setAttribute("CityBean", ob);
					indexPage = "/City/index.jsp?check=Updatename";		

				}
				
					else
					 {
				
						ob.setCode(Integer.parseInt(request.getParameter("code")));
						ob.setName(request.getParameter("name").trim());
						ob.setDesc(request.getParameter("desc"));
						ob.setState(request.getParameter("state"));
						ob.setCountry(request.getParameter("country"));
						ob.setPincode(Integer.parseInt(request.getParameter("pcode")));
						int count=ss.getCitySave(ob);
				indexPage = "/City/index.jsp?check=SaveCity";
				
			}
			dispatch(request, response, indexPage);
		
		}
		
		if (flag.equals("City")) {
			log4jLogger.info("start===========City=====");
			
			List CityArrylist = new ArrayList();
			ob = new CityBean();
			
			
			if (request.getParameter("name").trim() != null)
			{
				
				CityArrylist=ss.getCitySearchName(request.getParameter("name"));
				request.setAttribute("serarch", CityArrylist);
				indexPage = "/City/search.jsp?check=ok&flag="+flag+"&nam="+nm+"";
				dispatch(request, response, indexPage);
			}

		}
		
		
		}
		catch (Exception sss) {
			throw new ServletException(sss);
				}
					   
						}
		   
		   
		  
		   
		   
//	}
	public void dispatch(
			HttpServletRequest request,
			HttpServletResponse response,
			String indexPage)
			throws ServletException, IOException {
			RequestDispatcher dispatch = request.getRequestDispatcher(indexPage);
			dispatch.forward(request, response);
		}
	
	
	
	
}
