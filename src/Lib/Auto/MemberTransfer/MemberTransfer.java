package Lib.Auto.MemberTransfer;

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
import Common.businessutil.BusinessServiceFactory;
import Common.businessutil.admin.AdminService;





public class MemberTransfer extends HttpServlet implements Serializable {
	 private static Logger log4jLogger = Logger.getLogger(MemberTransfer.class);

	private static final long serialVersionUID = -8906987252709033668L;
	
	String flag;
	String indexPage = null;
	
			
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
			
			
			AdminService ss = BusinessServiceFactory.INSTANCE.getAdminService();
			flag = request.getParameter("flag");						
			
			List departmentList = ss.getDepartmentList();				
			request.setAttribute("departmentSearchList", departmentList);
			
			List courseList = ss.getCourseList();
			request.setAttribute("courseSearchList", courseList);
			
			List groupList = ss.getGroupList();
			request.setAttribute("groupSearchList", groupList);
			
			indexPage = "/MemberTransfer/index.jsp";	
			
					
			if(flag.equals("search"))
			{
				MemberTransRefBean beanObject = new MemberTransRefBean();
				
				if(!request.getParameter("department").equalsIgnoreCase("NO"))
					beanObject.setDeptCode(Integer.parseInt(request.getParameter("department")));
				
				if(!request.getParameter("course").equalsIgnoreCase("NO"))
					beanObject.setCourseCode(Integer.parseInt(request.getParameter("course")));
				
				if(!request.getParameter("group").equalsIgnoreCase("NO"))
					beanObject.setGroupCode(Integer.parseInt(request.getParameter("group")));
				
				if(!request.getParameter("frmYear").equalsIgnoreCase("NO"))
					beanObject.setAdd1(request.getParameter("frmYear"));
				
				if(!request.getParameter("toYear").equalsIgnoreCase("NO"))
					beanObject.setAdd2(request.getParameter("toYear"));
				
				//log4jLogger.info("Dept:"+beanObject.getDeptCode()+" ,Course:"+beanObject.getCourseCode()+" ,Group:"+beanObject.getGroupCode()+" ,Year:"+beanObject.getAdd1());	
				
				List memberList = ss.getMemberList(beanObject);
				
				request.setAttribute("memberSearchList", memberList);
				request.setAttribute("memberSearchListSize", memberList.size());
				
				request.setAttribute("bean",beanObject);
				indexPage = "/MemberTransfer/index.jsp?check=member";	
			}
			
			if(flag.equals("transfer"))
			{				
				log4jLogger.info("<=========== Inside Transfer ==========>");	
				
				MemberTransRefBean beanObject = new MemberTransRefBean();						
				beanObject.setAdd1(request.getParameter("flag1").toString().trim());
				beanObject.setAdd2(request.getParameter("toYear"));
								
				int count = ss.getMemberTransfer(beanObject);
				
				indexPage = "/MemberTransfer/index.jsp?check=transferred";
			}
					
			dispatch(request, response, indexPage);
			
			
			
		}
		catch (Exception sss) {
			throw new ServletException(sss);
			//sss.printStackTrace();
		} finally {
			
		}
	}
	
	public void dispatch(HttpServletRequest request,
			HttpServletResponse response, String indexPage)
			throws ServletException, IOException {
		RequestDispatcher dispatch = request.getRequestDispatcher(indexPage);
		dispatch.forward(request, response);
	}

}
