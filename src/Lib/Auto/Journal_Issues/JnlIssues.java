package Lib.Auto.Journal_Issues;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.sql.DriverManager;
import java.sql.SQLException;
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

import Common.businessutil.BusinessServiceFactory;
import Common.businessutil.calaloging.CalalogingService;
import Common.businessutil.serialcontrol.SerialcontrolService;
import Common.Security;
import Common.DataQuery;
import Lib.Auto.Counter.COUNTER_QUERY;
import Lib.Auto.Journal.Journal;
import Lib.Auto.Journal.journalbean;
import Lib.Auto.Journal_Issues.JnlIssBean;
import Lib.Auto.Simples.Searchbean;

import javax.servlet.annotation.WebServlet;
import com.google.gson.Gson;

@WebServlet("/Journal_Issues/JnlIssServlet")


public class JnlIssues extends HttpServlet implements Serializable {
	  private static Logger log4jLogger = Logger.getLogger(JnlIssues.class);
	/**
	 * 
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String flag;
	String protocol="";
	String name="",code="",jnlname="",SameCode="";
	int Department_Mas_code,Deptcode;
	String term="";
	int updateFlag;
	int max=0;
	int max1=0;
	String jnlcode="";
	JnlIssBean ob=new JnlIssBean();
	JnlIssBean beanobject=new JnlIssBean();
	ArrayList ser=new ArrayList ();
	
	String indexPage = null;
		
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
			
			SerialcontrolService ss = BusinessServiceFactory.INSTANCE.getSerialcontrolService();
			CalalogingService ss1 = BusinessServiceFactory.INSTANCE.getCalalogingService();
	
            response.setContentType("application/json");
			
			try{
				String term = request.getParameter("jname");
				if(!term.equalsIgnoreCase(null) && !term.equalsIgnoreCase(""))
	            {
				//System.out.println("Data from ajax call " + term);
							    
				   ArrayList<journalbean> list = ss1.getJnlIssuesJnlNameAutoSearch(term);
			       for(journalbean user: list){
					//System.out.println(user.getJname());
				}       

				String searchList = new Gson().toJson(list);
							
				response.getWriter().write(searchList);  
							
				//System.out.println("Json Value@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+searchList);
	            }	
		}catch(Exception e){
			//e.printStackTrace();
		}  		 

			
			flag = request.getParameter("flag");
			String nm=request.getParameter("name");
			
			log4jLogger.info("::::::::::::flag value::::::::::"+flag);
			
			if(flag.equals("clear")) {
			
				indexPage ="/Journal_Issues/index.jsp";
				dispatch(request, response, indexPage);
			}
			if(flag.equals("JnlName")){
				log4jLogger.info("list===========JnlName====="+flag);
								
				if(request.getParameter("name")!=null){
				    ob=new JnlIssBean();
					
				    
				    ob=ss.getJnlIssuesSearchName(request.getParameter("name"));
					
					request.setAttribute("bean",ob);			
					
			        indexPage = "/Journal_Issues/search.jsp?check=ok&find_flag="+flag+"&nam="+nm+"";
			    	dispatch(request, response, indexPage);
				}
			}
			
			
			if(flag.equals("new")){
				log4jLogger.info("new===========flag====="+flag);
				ob=new JnlIssBean();
				beanobject=new JnlIssBean();
				String jnlname=request.getParameter("jname");
				
                ob.setJnl_name(jnlname);				
				beanobject=ss.getJnlSearch(ob);
				
				ob=new JnlIssBean();
				ob=ss.getJnlIssuesMax();
				beanobject.setIss_acc_no(ob.getIss_acc_no());
				
				request.setAttribute("JnlBean",beanobject);
				
				indexPage="/Journal_Issues/index.jsp?check=JnlIssNew&details=Issues&jnlname="+jnlname+"";
				dispatch(request, response, indexPage);

				}

			if(flag.equals("search")){
				log4jLogger.info("search===========flag====="+request.getParameter("iaccno"));
				ob=new JnlIssBean();
				beanobject=new JnlIssBean();	
				String jnlname=request.getParameter("jname");
				
				ob.setIss_acc_no(request.getParameter("iaccno"));
				ob.setJnl_name(request.getParameter("jname"));
				
				beanobject=ss.getJnlSearch(ob);
				request.setAttribute("JnlBean",beanobject);
				
				if (beanobject.getMax() >0){
					
					indexPage = "/Journal_Issues/index.jsp?check=searchJnlIss&details=Issues";
					
				} else {
					indexPage = "/Journal_Issues/index.jsp?check=FailJnl&details=Issues&jnlname="+jnlname+"";
				}

				dispatch(request, response, indexPage);
			}
			
		
		
		if(flag.equals("find")){
			log4jLogger.info("find===========JnlName====="+flag);
			ob=new JnlIssBean();
			ob.setJnl_name(request.getParameter("jname"));
			ob.setFromDt(Security.TextDate_member(request.getParameter("subsFrom")));
			ob.setToDt(Security.TextDate_member(request.getParameter("subsTo")));
			ob.setFlag(request.getParameter("flagRadio"));
			log4jLogger.info("FromDate:::::::::::::::::: first"+ob.getFromDt());
			log4jLogger.info("ToDate:::::::::::::::::: first"+ob.getToDt());
			ob=ss.getJnlFindIssues(ob);
			log4jLogger.info("FromDate:::::::::::::::::: second"+ob.getFromDt());
			log4jLogger.info("ToDate:::::::::::::::::: second"+ob.getToDt());
			
			
			
			
			request.setAttribute("JnlBean",ob);
			
			if (ob.getMax() >0){
							
				indexPage = "/Journal_Issues/index.jsp?check=searchJnl&details=Issues";
				
			} else {
				
				indexPage = "/Journal_Issues/index.jsp?check=FailJnl";
			}

			dispatch(request, response, indexPage);
		}
		
		if(flag.equals("find1")){
				ob=new JnlIssBean();
			
			String aa=request.getParameter("jname");
			log4jLogger.info("*****************"+aa);
			
			
			
			
			
		}
		
		
		
		if(flag.equals("update")){
			log4jLogger.info("update===========JnlName====="+flag);
			ob=new JnlIssBean();
			beanobject=new JnlIssBean();
			
			ob.setIss_acc_no(request.getParameter("iaccno"));
			ob.setIss_year(request.getParameter("iyear"));
			ob.setIss_month(request.getParameter("imonth"));
			ob.setIss_vol(request.getParameter("ivol"));
			ob.setIss_no(request.getParameter("ino"));
			ob.setIss_date(Security.TextDate_Insert(request.getParameter("issuedate")));
			ob.setRec_date(Security.TextDate_Insert(request.getParameter("receivedate")));
			ob.setAvail(request.getParameter("avail"));
			ob.setRemarks(request.getParameter("iremarks"));
			ob.setPage(request.getParameter("Content"));
			ob.setJnl_name(request.getParameter("jname"));
			
			beanobject=ss.getJnlIssuesUpdate(ob);
			request.setAttribute("JnlBean",beanobject);
			 
			indexPage = "/Journal_Issues/index.jsp?check=UpdateJnl&details=Issues&jnlname="+request.getParameter("jname")+"";
			dispatch(request, response, indexPage);
		}
		
		if(flag.equals("delete")){
			log4jLogger.info("delete===========flag====="+request.getParameter("iaccno"));
			ob=new JnlIssBean();
			beanobject=new JnlIssBean();
			
			ob.setIss_acc_no(request.getParameter("iaccno"));
			ob.setJnl_name(request.getParameter("jname"));
			
			beanobject=ss.getJnlSearch(ob);			
			request.setAttribute("JnlBean",beanobject);
			
			if (beanobject.getMax() >0){
				
				indexPage = "/Journal_Issues/index.jsp?check=deleteCheck";
				
			} else {
				indexPage = "/Journal_Issues/index.jsp?check=FailJnl&details=Issues&jnlname="+request.getParameter("jname")+"";
			}

			dispatch(request, response, indexPage);
		}
		
		if(flag.equals("supdate"))
		{
			System.out.println("==============efeffe"+flag);
			ob=new JnlIssBean();
			beanobject=new JnlIssBean();
		//	ob.setJnl_code(Integer.parseInt(request.getParameter("jcode")));
			ob.setJnl_name(request.getParameter("jname"));
			beanobject=ss.getsup_date(ob);
			request.setAttribute("JnlBean",beanobject);
			if(beanobject.getJnl_name() != null)
			{
			indexPage = "/Journal_Issues/index.jsp?check=supdate"+"";
			}
			else			
			{
				indexPage = "/Journal_Issues/index.jsp?check=FailJnl&details=Issues&jnlname="+request.getParameter("jname")+"";
			}
			dispatch(request, response, indexPage);
		}
		
		if(flag.equals("Confirmdete")){
			log4jLogger.info("delete===========flag====="+request.getParameter("iaccno"));
			ob=new JnlIssBean();
			beanobject=new JnlIssBean();
			
			ob.setIss_acc_no(request.getParameter("iaccno"));
			ob.setJnl_name(request.getParameter("jname"));
			
			beanobject=ss.getJnlDelete(ob);
			request.setAttribute("JnlBean",beanobject);
			
			indexPage = "/Journal_Issues/index.jsp?check=DeleteJnl&details=Issues&jnlname="+request.getParameter("jname")+"";
			
			dispatch(request, response, indexPage);
		}
		
		if(flag.equals("save")){
			log4jLogger.info("save===========JnlName====="+flag);
			beanobject=new JnlIssBean();
			
			ob.setIss_acc_no(request.getParameter("iaccno"));
			ob.setIss_year(request.getParameter("iyear"));
			ob.setIss_month(request.getParameter("imonth"));
			ob.setIss_vol(request.getParameter("ivol"));
			ob.setIss_no(request.getParameter("ino"));
			ob.setIss_date(Security.TextDate_Insert(request.getParameter("issuedate")));
			ob.setRec_date(Security.TextDate_Insert(request.getParameter("receivedate")));
			ob.setAvail(request.getParameter("avail"));
			ob.setRemarks(request.getParameter("iremarks"));
			ob.setPage(request.getParameter("Content"));
			ob.setJnl_name(request.getParameter("jname"));
			
			
			int count=ss.getJnlIssuesAccno(request.getParameter("iaccno"));
			
			if(count>0){
				ob=new JnlIssBean();
				
				ob.setIss_acc_no(request.getParameter("iaccno"));
				ob.setIss_year(request.getParameter("iyear"));
				ob.setIss_month(request.getParameter("imonth"));
				ob.setIss_vol(request.getParameter("ivol"));
				ob.setIss_no(request.getParameter("ino"));
				ob.setIss_date(Security.TextDate_Insert(request.getParameter("issuedate")));
				ob.setRec_date(Security.TextDate_Insert(request.getParameter("receivedate")));
				ob.setAvail(request.getParameter("avail"));
				ob.setRemarks(request.getParameter("iremarks"));
				ob.setPage(request.getParameter("Content"));
				ob.setJnl_name(request.getParameter("jname"));
				
				request.setAttribute("JnlBean",ob);
				indexPage = "/Journal_Issues/index.jsp?check=UpdateCheck";
				
			}else{
				ob=new JnlIssBean();
				beanobject=new JnlIssBean();
				ob.setJnl_code(Integer.parseInt(request.getParameter("jcode")));
				ob.setIss_acc_no(request.getParameter("iaccno"));
				ob.setIss_year(request.getParameter("iyear"));
				ob.setIss_month(request.getParameter("imonth"));
				ob.setIss_vol(request.getParameter("ivol"));
				ob.setIss_no(request.getParameter("ino"));
				ob.setIss_date(Security.TextDate_Insert(request.getParameter("issuedate")));
				ob.setRec_date(Security.TextDate_Insert(request.getParameter("receivedate")));
				ob.setAvail(request.getParameter("avail"));
				ob.setRemarks(request.getParameter("iremarks"));
				ob.setPage(request.getParameter("Content"));
				ob.setJnl_name(request.getParameter("jname"));
				ob.setPart_no("");
				ob.setSub_code("");
				ob.setBvol_no("");
				 beanobject=ss.getJnlIssuesSave(ob);
				 
				 ob=ss.getJnlIssuesMax();				 
				 beanobject.setIss_acc_no(ob.getIss_acc_no());
				 
				 request.setAttribute("JnlBean",beanobject);
				 
				indexPage = "/Journal_Issues/index.jsp?check=SaveJnl&details=Issues&jnlname="+request.getParameter("jname")+"";
			}
			
			dispatch(request, response, indexPage);
		}
		
		 
			
			 } catch (Exception sss) {throw new ServletException(sss);}
					
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
	
	
	
	
	
}
