package Lib.Auto.MsgMas;


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

import com.lowagie.text.pdf.hyphenation.TernaryTree.Iterator;

import Common.Security;
import Common.Security_Counter;
import Common.businessutil.BusinessServiceFactory;
import Common.businessutil.calaloging.CalalogingService;
import Lib.Auto.City.CityBean;


public class MsgMas extends HttpServlet implements Serializable {
	 private static Logger log4jLogger = Logger.getLogger(MsgMas.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String flag;
	String protocol="";
	String name="",code="",state="",country="";
	int City_Mas_code=0;int Citycode=0;
	int updateFlag;

	String nam="";
	MsgMasBean msgbean=new MsgMasBean();

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
			CalalogingService ss = BusinessServiceFactory.INSTANCE.getCalalogingService();
			
			MsgMasBean msgbean = null;
			
			flag = request.getParameter("flag");
			
			String nm=request.getParameter("name");
			
			
			
			log4jLogger.info("---------------------Flag Values---------------"+flag);
			
			
			
			
			
			
			
			
			if(flag.equals("new")){
				
				log4jLogger.info("start===========new=====");
				msgbean = new MsgMasBean();
				msgbean=ss.getMsgMasMax();
				request.setAttribute("MsgMasBean", msgbean);	
				indexPage = "/msgMas/index.jsp?check=newMsgCode";
				dispatch(request, response, indexPage);
					}
			
	
						 
		if(flag.equals("search")){
			log4jLogger.info("start===========search=====");
			msgbean = new MsgMasBean();
			
			msgbean=ss.getMsgMasSearch(Integer.parseInt(request.getParameter("msgCode")));
			
			if(msgbean!=null){
			request.setAttribute("MsgMasBean", msgbean);	
			indexPage = "/msgMas/index.jsp?check=searchMsg";
			}
			else
			{
				indexPage = "/msgMas/index.jsp?check=FailMsg";
			}
			
			dispatch(request, response, indexPage);
		}				
					
		
		if(flag.equals("update")){
			log4jLogger.info("start===========update=====");
			msgbean = new MsgMasBean();
			 msgbean.setMsgCode(Integer.parseInt(request.getParameter("msgCode")));
			 msgbean.setLibMsg((request.getParameter("libMsg").trim()));
			 msgbean.setWhatsNew(request.getParameter("whatsNew").trim());
			 //msgbean.setDate(Security_Counter.TextDate_Insert(request.getParameter("date")));	
			 msgbean.setDate(request.getParameter("date"));
			
			 int count=ss.getMsgMasUpdate(msgbean);
			request.setAttribute("MsgMasBean", msgbean);	
			indexPage = "/msgMas/index.jsp?check=UpdateMasMas";
			dispatch(request, response, indexPage);
		}
	
		if(flag.equals("delete")){
			log4jLogger.info("start===========delete=====");
			msgbean = new MsgMasBean();
			
			msgbean=ss.getMsgMasSearch(Integer.parseInt(request.getParameter("msgCode")));
			if(msgbean!=null){
			request.setAttribute("MsgMasBean", msgbean);	
	
				indexPage = "/msgMas/index.jsp?check=deleteCheck";
					} 
		 else 
					{
			     request.setAttribute("MsgMasBean", msgbean);	
				indexPage = "//msgMas/index.jsp?check=RecordNot";
					}
			dispatch(request, response, indexPage);
	}
		
		
		
		if(flag.equals("Confirmdete")){
			
			
			log4jLogger.info("start===========Confirmdete=====");
			
			int count=ss.getMsgMasDelete(Integer.parseInt(request.getParameter("msgCode")));

					indexPage = "/msgMas/index.jsp?check=DeleteMsg";
				
				dispatch(request, response, indexPage);
			
		}

		if(flag.equals("save")){
			log4jLogger.info("start===========save=====");
			msgbean = new MsgMasBean();

			int City_Mas_code=ss.getMsgMas(Integer.parseInt(request.getParameter("msgCode")));

			if (City_Mas_code >0)
				{
					 msgbean.setMsgCode(Integer.parseInt(request.getParameter("msgCode")));
					 msgbean.setLibMsg((request.getParameter("libMsg").trim()));
					 msgbean.setWhatsNew(request.getParameter("whatsNew").trim());
					 msgbean.setDate(Security_Counter.TextDate_Insert(request.getParameter("date")));	
					 
					 request.setAttribute("MsgMasBean", msgbean);
					 indexPage = "/msgMas/index.jsp?check=UpdateMsg";		
				}
				
					else
					 {
						msgbean=ss.getMsgMasMax();

						 msgbean.setMsgCode(Integer.parseInt(request.getParameter("msgCode")));
						 msgbean.setLibMsg((request.getParameter("libMsg").trim()));
						 msgbean.setWhatsNew(request.getParameter("whatsNew").trim());
						 //msgbean.setDate(request.getParameter("date"));
						 
						 msgbean.setDate(Security_Counter.TextDate_Insert(request.getParameter("date")));	
						 
						int count=ss.getMsgMasSave(msgbean);
						
				indexPage = "/msgMas/index.jsp?check=SaveCity";
				
			}
			dispatch(request, response, indexPage);
		
		}
		
		if (flag.equals("msgFindData")) {
			log4jLogger.info("start===========searchMsg====="+flag);
				
			List MsgMasArrylist = new ArrayList();
			
			
			msgbean = new MsgMasBean();
			
			
			if (request.getParameter("name").trim() != null)
			{
				
				MsgMasArrylist=ss.getMsgMasSearchName(request.getParameter("name"));
				request.setAttribute("serarch", MsgMasArrylist);
				indexPage = "/msgMas/search.jsp?check=ok&flag="+flag+"&nam="+nm+"";
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
