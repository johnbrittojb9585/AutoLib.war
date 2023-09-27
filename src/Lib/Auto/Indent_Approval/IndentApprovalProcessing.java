package Lib.Auto.Indent_Approval;

import java.io.IOException;
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

import Common.LibraryConstants;
import Common.Security;
import Common.businessutil.BusinessServiceFactory;
import Common.businessutil.acquisition.AcquisitionService;
import Common.businessutil.calaloging.CalalogingService;
import Common.businessutil.serialcontrol.SerialcontrolService;
import Lib.Auto.Indent_Mas.IndentMasDetailsBean;
import Login.Login;

public class IndentApprovalProcessing extends HttpServlet implements Serializable {
	private static final long serialVersionUID = 1L;

	private static Logger log4jLogger = Logger.getLogger(IndentApprovalProcessing.class);

	String indexPage = null;

	String flag = null;


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException {

		performTask(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException {

		performTask(request, response);

	}	
	
	
	public void performTask(HttpServletRequest request,
			HttpServletResponse response) throws ServletException {
		try {
			
			HttpSession session = request.getSession(true);
			String res = Security.checkSecurity(1, session, response, request);		
			if(res.equalsIgnoreCase("Failure"))
			{
				response.sendRedirect("/AutoLib/Tree/sessiontimeout.jsp");  
				return;
			}
			
			AcquisitionService ss=BusinessServiceFactory.INSTANCE.getAcquisitionService();
			CalalogingService ss1 = BusinessServiceFactory.INSTANCE.getCalalogingService();
			
			IndentApproveBean ob=new IndentApproveBean();				
						
			flag = request.getParameter("flag");			
					
			
			
			
			if(flag.equals("IndentNo"))
			{					
				List OrdJNLArrylist = new ArrayList();
				
				IndentMasDetailsBean beanObject=new IndentMasDetailsBean();
				beanObject.setMemname(request.getParameter("name"));
				beanObject.setFlag(request.getParameter("flag"));
				
				OrdJNLArrylist=ss.getIndentMasSearch(beanObject);				
																						
				request.setAttribute("search", OrdJNLArrylist);
				indexPage ="/Indent_Approval/search_nmvc.jsp?check=ok&flag="+flag+"";
				dispatch(request, response, indexPage);
			}
										
			
			if (flag.equals("Save")) {
				
				log4jLogger.info("New===========flag=====" + flag);
								
				ob=new IndentApproveBean();
				
				String indtno="",membercode="",indtdate="",member="",aprvdate="";
				
				indtno=request.getParameter("indtno");
				
				indtdate=Security.TextDate_member(request.getParameter("indtdate"));
				aprvdate=Security.TextDate_member(request.getParameter("aprvdate"));
				
				membercode=request.getParameter("memberCode");
				member=request.getParameter("member");
				
				 
				String[] a=null,b=null,c=null,d=null,e=null,f=null,g=null,h=null;

					a=request.getParameterValues("titleNo[]");
					b=request.getParameterValues("title[]");
					c=request.getParameterValues("indentNo[]");
					
					d=request.getParameterValues("author[]");
					e=request.getParameterValues("autcode[]");
					
					f=request.getParameterValues("noofcopy[]");
					g=request.getParameterValues("pendingcopy[]");
					h=request.getParameterValues("approvecopy[]");
					
					String commaSeparated=request.getParameter("flag1");					
					String [] items = commaSeparated.split(",");
						
					int	len=a.length;						
					List<Object> approveDetail = new ArrayList<Object>();
					List<Object> approveNotDetail = new ArrayList<Object>();	 
					
					try {						
					
					String approve=null,notapprove=null;
					
					for(int i=0;i<len;i++)
					{ 
						
						IndentApproveBean approvebean=new IndentApproveBean();
						IndentApproveBean notapprovebean=new IndentApproveBean();
						
							 if(Integer.parseInt(h[i])== 0) {							 
								 
								 notapprove = notapprove + ',' + a[i];
								 
								 notapprovebean.setTitleNo(Integer.parseInt(a[i]));
								 notapprovebean.setIndtno(c[i]);
								 
								 notapprovebean.setApprvedate(null);
								 notapprovebean.setApprvecopy(Integer.parseInt(h[i]));
								 notapprovebean.setPendingcopy(Integer.parseInt(g[i]));
								
								 approveNotDetail.add(notapprovebean);
								 
							 }else  {								 
								 
								 approve = approve + ',' + a[i];	
								 
								 approvebean.setTitleNo(Integer.parseInt(a[i]));
								 approvebean.setIndtno(c[i]);
								 
								 approvebean.setApprvedate(aprvdate);
								 approvebean.setApprvecopy(Integer.parseInt(h[i]));
								 approvebean.setPendingcopy(Integer.parseInt(g[i]));
								 
								 approveDetail.add(approvebean);
							 }							 
					}
					
					if(approve!=null)
					{						
						log4jLogger.info("Approved    :"+approve);

						ss.getIndentApproval(approveDetail);
					}
					
					if(notapprove!=null)
					{
						log4jLogger.info("Not Approved:"+notapprove);
						
						ss.getIndentNotApproval(approveNotDetail);
						
					}
					
					}catch(Exception err)  {
						log4jLogger.info("Error in For Loop ----> Indent Approval    :"+err);
					}
							
					 
					indexPage ="/Indent_Approval/index.jsp?check=SaveSuccess";				
				    dispatch(request, response, indexPage);
			}
			
			
            if (flag.equals("Search")) {
				
				log4jLogger.info("New===========flag=====" + flag);
				
				String indtno="";				
				indtno=request.getParameter("indtno");				
				
				 List SaveList=null;					 
				 SaveList=ss.getFullViewIndentApproval(indtno);					
				
				 if(SaveList.size()>0)   {
					 
						session.setAttribute("IndentApprove",SaveList);
						session.setAttribute("IndentApproveSize",SaveList.size());		
						
						indexPage ="/Indent_Approval/index.jsp";
						
				 }else {
					 
					   indexPage ="/Indent_Approval/index.jsp?check=DeleteFail";					 
				 }

			    dispatch(request, response, indexPage);
		     }
            
            if(flag.equals("Clear")){
            	IndentApproveBean IndentDetails=new IndentApproveBean();
            	session.setAttribute("IndentApprove",IndentDetails);
            	session.setAttribute("IndentApproveSize",0);	
            	indexPage ="/Indent_Approval/index.jsp";
            	
            	dispatch(request, response, indexPage);
            	
			}
            
           			
		}catch(Exception e) {
			e.printStackTrace();
			
		}
		
		
		
	}

	/***************************************************************************
	 * prepare the sql statement for execution
	 */
	public void dispatch(HttpServletRequest request,
			HttpServletResponse response, String indexPage)
			throws ServletException, IOException {
		RequestDispatcher dispatch = request.getRequestDispatcher(indexPage);
		dispatch.forward(request, response);
	}

}
