package Lib.Auto.BulkUpdateMember;


import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import Common.Security;
import Lib.Auto.BulkUpdateMember.BulkUpdateMember;
import Lib.Auto.BulkUpdate.BulkUpdateMsgBean;
import Lib.Auto.Counter.COUNTER_QUERY;
import Common.businessutil.BusinessServiceFactory;
import Common.businessutil.admin.AdminService;

public class BulkUpdateMember extends HttpServlet implements Serializable, COUNTER_QUERY {
	private static final long serialVersionUID = 1L;
	private static Logger log4jLogger = Logger.getLogger(BulkUpdateMember.class);
	
	String flag="",strsql="",strsql1="",strsql2="";
	String indexPage = null;	
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)  throws ServletException {

		performTask(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException{

		performTask(request, response);

	}
	public void performTask(
		HttpServletRequest request,
		HttpServletResponse response)  throws ServletException {
		try {

			HttpSession session = request.getSession(true);
			String res = Security.checkSecurity(1, session, response, request);		
			if(res.equalsIgnoreCase("Failure"))
			{
				response.sendRedirect("/AutoLib/Tree/sessiontimeout.jsp");  
				return;
			}	
			
			AdminService ss = BusinessServiceFactory.INSTANCE.getAdminService();
 	
			BulkUpdateMsgBean beanObject = new BulkUpdateMsgBean();
			
			String frmUpdate = "",toUpdate = "",frmValue = "",toValue = "";		
			
			flag = request.getParameter("flag");			
			
			
			if(flag.equalsIgnoreCase("search") && flag != null && !flag.isEmpty())
			{
				log4jLogger.info("start==============>flag:"+flag);
				
				frmUpdate = request.getParameter("frmUpdate");	
				toUpdate = request.getParameter("toUpdate");
				
			   if(!frmUpdate.equals("") && !frmUpdate.equals("NO")){
				   log4jLogger.info(":::::::::::From Update Value:::::::::"+frmUpdate);
				   
				      
				   List<?> resultList = ss.getBulkMemberUpdateList(flag, frmUpdate);           
				      request.setAttribute("resultBulkList1", resultList);
			   }
			   
			   if(!toUpdate.equals("") && !toUpdate.equals("NO")){
				   log4jLogger.info(":::::::::::From Update Value:::::::::"+toUpdate);
				   List<?> resultList = ss.getBulkMemberUpdateList(flag, toUpdate);  
				   request.setAttribute("resultBulkList2", resultList);
				   
			   }
			 
		           
		           if(!frmUpdate.isEmpty() && !toUpdate.isEmpty())
				   {
					   beanObject = new BulkUpdateMsgBean();
					   beanObject.setFrmUpdate(frmUpdate);           
				       beanObject.setToUpdate(toUpdate);
				       
				       beanObject.setFrmValue(request.getParameter("frmValue"));
				       beanObject.setToValue(request.getParameter("toValue"));
				       
				       request.setAttribute("beanForm",beanObject);
			           indexPage = "/BulkUpdateMember/index.jsp";	
			           dispatch(request, response, indexPage);
				   }
			}
			
			
			
			
			if(flag.equalsIgnoreCase("update") && flag != null && !flag.isEmpty()){
				log4jLogger.info("start==============>flag:"+flag);
				
				frmUpdate = request.getParameter("frmUpdate");	
				toUpdate = request.getParameter("toUpdate");
				frmValue = request.getParameter("frmValue");
				toValue = request.getParameter("toValue");
				
				if( !frmUpdate.equals("") && !frmUpdate.equals("NO") ){
					if(frmUpdate.equals("Desig"))  {
						strsql1 = " and designation_code = "+frmValue;
					}else if(frmUpdate.equals("Dept")){
						strsql1 = " and dept_code = "+frmValue;
					}
					else if(frmUpdate.equals("Group")){
						strsql1 = " and group_code = "+frmValue;
					}
					else if(frmUpdate.equals("Course")){
						strsql1 = " and course_code = "+frmValue;
					}
					else if(frmUpdate.equals("Slock")){
						strsql1 = " and slock = "+frmValue;
					}
					else if(frmUpdate.equals("CourseYear")){
						strsql1 = " and cyear = "+frmValue;
					}
					
					else if(frmUpdate.equals("Sex")){
						strsql1 = " and sex = '"+frmValue+"'";
					}
					log4jLogger.info("::::::::::::::::: From::: "+strsql1);
				}
				
				
				
				if( !toUpdate.equals("") && !toUpdate.equals("NO") ){
					if(toUpdate.equals("Desig"))  {
						strsql2 = " designation_code = "+toValue;
					}else if(toUpdate.equals("Dept")){
						strsql2 = " dept_code = "+toValue;
					}
					else if(toUpdate.equals("Group")){
						strsql2 = " group_code = "+toValue;
					}
					else if(toUpdate.equals("Course")){
						strsql2 = " course_code = "+toValue;
					}
					else if(toUpdate.equals("Slock")){
						strsql2 = " slock = '"+toValue+"'";
					}
					else if(toUpdate.equals("CourseYear")){
						strsql2 = " cyear = "+toValue;
					}
					
					else if(toUpdate.equals("Sex")){
						strsql2 = " sex = '"+toValue+"'";
					}
					log4jLogger.info("::::::::::::::::: To::: "+strsql2);
				}
				beanObject = new BulkUpdateMsgBean();
				beanObject.setFrmUpdate(frmUpdate);           
			    beanObject.setToUpdate(toUpdate);			       
			    beanObject.setFrmValue(strsql1);
			    beanObject.setToValue(strsql2);
			 
			    
			    log4jLogger.info(":::::::::from :"+beanObject.getFrmValue());
			    log4jLogger.info(":::::::::To :"+beanObject.getToValue());
			    
			    int count = ss.getBulkMemberUpdateSave(beanObject);	
			    
			    log4jLogger.info("start==============>resultDone:"+count);
			    
		        request.setAttribute("resultDone", count);
		        indexPage = "/BulkUpdateMember/index.jsp";
		        dispatch(request, response, indexPage);
			}
			
			
			
			
			
			
			
			
			
			
			
			
			
			
		}
		catch (Exception e) {
		      e.printStackTrace();
			}
		catch (Throwable theException) {
			
			}
		finally{
			strsql="";
			strsql1="";
			strsql2="";		
			}
		}	
			public void dispatch(
					HttpServletRequest request,
					HttpServletResponse response,
					String indexPage)
					throws ServletException, IOException {
				   // response.sendRedirect(indexPage);
					RequestDispatcher dispatch = request.getRequestDispatcher(indexPage);
					dispatch.forward(request, response);
				}

		
		
		
	}
	
	
	
	
	
	

