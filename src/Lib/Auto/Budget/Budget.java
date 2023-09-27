package Lib.Auto.Budget;
import Lib.Auto.Author.AuthorBean;
import Lib.Auto.Budget.BudgetBean;
import Lib.Auto.Fine.Finebean;
import Lib.Auto.Holiday.Holiday;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;
import java.util.MissingResourceException;
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
import Common.businessutil.admin.AdminService;




public class Budget extends HttpServlet implements Serializable {
	private static Logger log4jLogger = Logger.getLogger(Budget.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String flag,Budget_cd,Budget_savecode,Budget_savehead,Budget_savedname,Budget_saveamount,Budget_savesamount,Budget_savefrom,Budget_saveto,Budget_saverem,Budget_Val_Name;

	String fromdate="",todate="";
	int Budget_Mas_code;
	int updateFlag;
	BudgetBean ob=new BudgetBean();
	singlecodecheck chkcode=new singlecodecheck();
	
	String indexPage = null;
	
	
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
            AdminService ss = BusinessServiceFactory.INSTANCE.getAdminService();
	        
			
			flag=request.getParameter("flag");
			
			String nm=request.getParameter("name");
			
			
			
			if(flag.equals("dcode")){


				log4jLogger.info("start===========dept Search=====");
				List FineArrylist = new ArrayList();
				ob = new BudgetBean();
							
				if(request.getParameter("name")!=null){
					String fcode=request.getParameter("name");
					ob=ss.getBudgetDeptSearchName(fcode);
		
					
					request.setAttribute("bean", ob);
			        indexPage = "/Budget/search.jsp?check=ok&flag="+flag+"&nam="+nm+"";
			    	dispatch(request, response, indexPage);
				}
				
			}
			
			if(flag.equals("bcode")){
				log4jLogger.info("start===========budget Search=====");
				List FineArrylist = new ArrayList();
				ob = new BudgetBean();
							
				if(request.getParameter("name")!=null){
					String fcode=request.getParameter("name");
					ob=ss.getBudgetSearchName(fcode);
		
				
					request.setAttribute("bean", ob);
			        indexPage = "/Budget/search.jsp?check=ok&flag="+flag+"&nam="+nm+"";
			    	dispatch(request, response, indexPage);
				}
				
			}
			
			if(flag.equals("new")){
				
				log4jLogger.info("start===========Fine Search=====");
				ob = new BudgetBean();
				ob=ss.getBudgetMax();
				request.setAttribute("bean", ob);			
				indexPage = "/Budget/index.jsp?check=newBudget";
				dispatch(request, response, indexPage);
			
		}

			if(flag.equals("search")){
				
				log4jLogger.info("start===========search====="+request.getParameter("Bud_Code"));
				ob = new BudgetBean();
				ob=ss.getBudgetSearch(request.getParameter("Bud_Code"));
				
				if(ob!=null){
					request.setAttribute("bean", ob);
					
					indexPage = "/Budget/index.jsp?check=searchBudget";	
				}else{
					indexPage = "/Budget/index.jsp?check=FailBudget";
				}
				dispatch(request, response, indexPage);
			}
	
			if(flag.equals("update")){


				log4jLogger.info("start===========update====="+request.getParameter("Bud_Code"));
				
				 ob = new BudgetBean();
				 
				 ob.setBudCode(Integer.parseInt(request.getParameter("Bud_Code")));
				 ob.setBudHead(request.getParameter("Bud_Head"));
				 ob.setDeptname(request.getParameter("dname"));
				 ob.setBudAmount(request.getParameter("Amount"));
				 ob.setBudSAmount(request.getParameter("Aspent"));
				 Budget_savefrom=getDate(request.getParameter("fromdate"));
				 Budget_saveto=getDate(request.getParameter("todate"));
				 ob.setFrom(Budget_savefrom);
				 ob.setTo(Budget_saveto);
				 ob.setRemarks(request.getParameter("Remarks"));
				 
				 
				 ob.setbAmount(request.getParameter("book"));
				 ob.setbSAmount(request.getParameter("bSpntAmt"));
				 
				 ob.setBbAmount(request.getParameter("bookbank"));
				 ob.setBbSAmount(request.getParameter("bbSpntAmt"));
				 
				 ob.setNbAmount(request.getParameter("nonbook"));
				 ob.setNbSAmount(request.getParameter("nbSpntAmt"));
				 
				 ob.setrAmount(request.getParameter("report"));
				 ob.setrSAmount(request.getParameter("rSpntAmt"));
				 
				 ob.settAmount(request.getParameter("thesis"));
				 ob.settSAmount(request.getParameter("tSpntAmt"));
				 
				 ob.setsAmount(request.getParameter("standard"));
				 ob.setsSAmount(request.getParameter("sSpntAmt"));
				 
				 ob.setpAmount(request.getParameter("proceeding"));
				 ob.setpSAmount(request.getParameter("pSpntAmt"));
				 
				 ob.setjAmount(request.getParameter("journal"));
				 ob.setjSAmount(request.getParameter("jSpntAmt"));
				 
				 ob.setmAmount(request.getParameter("misc"));
				 ob.setmSAmount(request.getParameter("mSpntAmt"));
				 
			
				
				 int count=ss.getBudgetUpdate(ob);
				 indexPage = "/Budget/index.jsp?check=UpdateBudget";
				 
				 dispatch(request, response, indexPage);
			}
		
		
			
			if(flag.equals("delete")){
				log4jLogger.info("start===========delete====="+request.getParameter("Bud_Code"));
				ob = new BudgetBean();				
				ob=ss.getBudgetSearch(request.getParameter("Bud_Code"));			
				if(ob!=null){				
					request.setAttribute("bean", ob);
					indexPage = "/Budget/index.jsp?check=deleteCheck";	
				}else{
					
					indexPage = "/Budget/index.jsp?check=FailBudget";	
				}
					dispatch(request, response, indexPage);
			}

			
		
			if(flag.equals("Confirmdete")){
				log4jLogger.info("start===========Confirmdete====="+request.getParameter("Bud_Code"));
				int count=ss.getBudgetInterface(request.getParameter("Bud_Code"));
				if(count>0){
					indexPage = "/Budget/index.jsp?check=ReferredBudget";
				}else{
					int rk=Integer.parseInt(request.getParameter("Bud_Code"));
				
					if(rk==1)
					{
						indexPage = "/Budget/index.jsp?check=DefaultBudget";
					}
					else
					{					
					count=ss.getBudgetDelete(request.getParameter("Bud_Code"));
					indexPage = "/Budget/index.jsp?check=DeleteBudget";
				    }
				}
				dispatch(request, response, indexPage);
			}
		
			if(flag.equals("save")){
				

				log4jLogger.info("start===========save====="+request.getParameter("Bud_Code"));
				 ob = new BudgetBean();
				 	 ob=ss.getBudgetSearch(request.getParameter("Bud_Code"));
				 
				 if(ob!=null){
					 ob = new BudgetBean();
					 ob.setBudCode(Integer.parseInt(request.getParameter("Bud_Code")));
					 
					 ob.setBudHead(request.getParameter("Bud_Head"));
					 ob.setDeptname(request.getParameter("dname"));
					 ob.setBudAmount(request.getParameter("Amount"));
					 ob.setBudSAmount(request.getParameter("Aspent"));
					 Budget_savefrom=getDate(request.getParameter("fromdate"));
					 Budget_saveto=getDate(request.getParameter("todate"));
					 ob.setFrom(request.getParameter("fromdate"));
					 ob.setTo(request.getParameter("todate"));
					 ob.setRemarks(request.getParameter("Remarks"));
					
					 ob.setbAmount(request.getParameter("book"));
					 ob.setbSAmount(request.getParameter("bSpntAmt"));
					 
					 ob.setBbAmount(request.getParameter("bookbank"));
					 ob.setBbSAmount(request.getParameter("bbSpntAmt"));
					 
					 ob.setNbAmount(request.getParameter("nonbook"));
					 ob.setNbSAmount(request.getParameter("nbSpntAmt"));
					 
					 ob.setrAmount(request.getParameter("report"));
					 ob.setrSAmount(request.getParameter("rSpntAmt"));
					 
					 ob.settAmount(request.getParameter("thesis"));
					 ob.settSAmount(request.getParameter("tSpntAmt"));
					 
					 ob.setsAmount(request.getParameter("standard"));
					 ob.setsSAmount(request.getParameter("sSpntAmt"));
					 
					 ob.setpAmount(request.getParameter("proceeding"));
					 ob.setpSAmount(request.getParameter("pSpntAmt"));
					 
					 ob.setjAmount(request.getParameter("journal"));
					 ob.setjSAmount(request.getParameter("jSpntAmt"));
					 
					 ob.setmAmount(request.getParameter("misc"));
					 ob.setmSAmount(request.getParameter("mSpntAmt"));
					 
				
					
					 request.setAttribute("bean", ob);
					 indexPage = "/Budget/index.jsp?check=UpdateCheck";
					 					 
				 }else{
					 ob = new BudgetBean();
					 ob = ss.getBudgetMax();
					 
					 ob.setBudHead(request.getParameter("Bud_Head"));
					 ob.setDeptname(request.getParameter("dname"));
					 ob.setBudAmount(request.getParameter("Amount"));
					 ob.setBudSAmount(request.getParameter("Aspent"));
					 Budget_savefrom=getDate(request.getParameter("fromdate"));
					 Budget_saveto=getDate(request.getParameter("todate"));
					 ob.setFrom(Budget_savefrom);
					 ob.setTo(Budget_saveto);
					 ob.setRemarks(request.getParameter("Remarks"));
					
					 ob.setbAmount(request.getParameter("book"));
					 ob.setbSAmount(request.getParameter("bSpntAmt"));
					 
					 ob.setBbAmount(request.getParameter("bookbank"));
					 ob.setBbSAmount(request.getParameter("bbSpntAmt"));
					 
					 ob.setNbAmount(request.getParameter("nonbook"));
					 ob.setNbSAmount(request.getParameter("nbSpntAmt"));
					 
					 ob.setrAmount(request.getParameter("report"));
					 ob.setrSAmount(request.getParameter("rSpntAmt"));
					 
					 ob.settAmount(request.getParameter("thesis"));
					 ob.settSAmount(request.getParameter("tSpntAmt"));
					 
					 ob.setsAmount(request.getParameter("standard"));
					 ob.setsSAmount(request.getParameter("sSpntAmt"));
					 
					 ob.setpAmount(request.getParameter("proceeding"));
					 ob.setpSAmount(request.getParameter("pSpntAmt"));
					 
					 ob.setjAmount(request.getParameter("journal"));
					 ob.setjSAmount(request.getParameter("jSpntAmt"));
					 
					 ob.setmAmount(request.getParameter("misc"));
					 ob.setmSAmount(request.getParameter("mSpntAmt"));
				
					 
					 int count=ss.getBudgetSave(ob);
					 
					 indexPage = "/Budget/index.jsp?check=SaveBudget";
				 }
				 dispatch(request, response, indexPage);
			
			}
			
			
            if(flag.equals("loadSAmount")){
				
				log4jLogger.info("start===========Budget Load And Update Spent Amount=====");
				ob = new BudgetBean();
				ob=ss.getBudgetUpdateSAmount();
				request.setAttribute("bean", ob);
				

				ob=ss.getBudgetMax();
				request.setAttribute("bean", ob);	
				
				
				indexPage = "/Budget/index.jsp?check=newBudget";
				dispatch(request, response, indexPage);
			
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

	String getDate(String datechk) {
       java.util.StringTokenizer stz_is=new java.util.StringTokenizer(datechk,"-");
		    int bid=Integer.parseInt(stz_is.nextToken());
		     int bim=Integer.parseInt(stz_is.nextToken());
		    int biy=Integer.parseInt(stz_is.nextToken());
	     	   String bissue_m=new Integer(bim).toString();
				    if(bissue_m.length()==1)
				    bissue_m="0"+bissue_m;
					String bissue_d=new Integer(bid).toString();
					if(bissue_d.length()==1)
				    bissue_d="0"+bissue_d;
					String BISSDATE=biy+"-"+bissue_m+"-"+bissue_d;
	return BISSDATE;
  }
  String getholiday(String holiday) {
     java.util.StringTokenizer stz_h=new java.util.StringTokenizer(holiday,"-");
		    int hy=Integer.parseInt(stz_h.nextToken());
		     int hm=Integer.parseInt(stz_h.nextToken());
		    int hd=Integer.parseInt(stz_h.nextToken());
	     	   String hissue_m=new Integer(hm).toString();
				    if(hissue_m.length()==1)
				    hissue_m="0"+hissue_m;
					String hissue_d=new Integer(hd).toString();
					if(hissue_d.length()==1)
				    hissue_d="0"+hissue_d;
					String HOLIDAYDATE=hissue_d+"-"+hissue_m+"-"+hy;
	return HOLIDAYDATE;
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
