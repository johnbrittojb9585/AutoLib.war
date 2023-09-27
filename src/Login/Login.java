package Login;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;

import Common.License;
import Common.Security;
import Common.businessutil.BusinessServiceFactory;
import Common.businessutil.LoginUserService;
import Common.businessutil.search.SearchService;
import Lib.Auto.Login.UserIdbean;

import com.library.autolib.portal.prototype.LibraryServiceFactory;


/**
 * @author selva
 *
 */
public class Login extends HttpServlet {
	
	UserIdbean newbean=new UserIdbean();
	String indexPage = null;
	
	private static Logger log4jLogger = Logger.getLogger(Login.class);
	String sLoginErr = "";
	String txtuserid = "";
	String txtpasword = "";
	
	/* (non-Javadoc)
	 * @see javax.servlet.GenericServlet#init()
	 */
	public void init() throws ServletException{
	
		log4jLogger.info("[Login:process init()] Inside");
	}
	
	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */

	public void doPost(HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServletException {
		
		log4jLogger.info("[Login:process doGet()] Inside");
		
		
		HttpSession session = request.getSession(true);		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		try {
			SearchService rr = BusinessServiceFactory.INSTANCE
					.getSearchService(); // For Titan

			String flag = "";
			flag = request.getParameter("flag");

			if (flag.equals("load")) {
				log4jLogger.info("start===========Login Branch List load=====");
				List BranchArrylist = new ArrayList();
				BranchArrylist = rr.getLoadBranchList();
				session.setAttribute("UserBranchList", BranchArrylist);
				response.sendRedirect("/AutoLib/index.jsp");
			}

			if (flag.equals("login")) {
				
			//if (!License.valid())
			//	response.sendRedirect("/AutoLib/something-went-wrong.jsp");

				String txtuserid = Security.getParam(request, "txtuserid");
				String txtpasword = Security.getParam(request, "txtpasword");
				String txtbranch = ""; // Security.getParam(request,
										// "txtBranch");
				log4jLogger.info(txtpasword);

			//	byte[] decoded = Base64.decodeBase64(txtpasword.getBytes());

			//	log4jLogger.info(new String(decoded));
				
			//	txtpasword = new String(decoded);

				LoginUserService ss = LibraryServiceFactory.INSTANCE.getLoginUserService();						
				User user = ss.getUser(txtuserid, txtpasword);
			
			if(user!=null){
				
				if( (!user.getUserId().equals(txtuserid)) || (!user.getPassword().equals(txtpasword)) )	{
					response.sendRedirect("/AutoLib/InvalidUser.jsp");
				}
				else if((user.getLoginFlag().trim()).equalsIgnoreCase("NO"))
				{
					response.sendRedirect("/AutoLib/index.jsp");
				}
				else
				{
					session.setAttribute("UserID", user.getUserId());
					session.setAttribute("UserRights", user.getUserRights());					
					session.setAttribute("visitMember", user.getFirstName());   // Gets Member Name
					
                    List time = ss.getTimeDate(user.getUserId());
					
					/** --------------- Last Visiting Time --------------- */
					/**if (time != null && time.size() >= 1)
					{
						String dateTime = time.get(0).toString();
						String DT[] = dateTime.split(" ");
						String day = DT[0];
						String temp = DT[1];
						String date = day.split("-")[2] + "/" + day.split("-")[1] + "/" + day.split("-")[0];
						String lastVisitedTime = date + " " + temp;
						session.setAttribute("visitDateTime", lastVisitedTime);
						ss.updateTimeDate(user.getUserId());
					}
					else
					{*/
						session.setAttribute("visitDateTime", new Date());
						ss.saveTimeDate(user.getUserId());
					//}
					
					
					if(Integer.parseInt(user.getUserRights().toString().trim())!=7)
					{		
						
						byte[] userPhoto=null;
					    // Load Home Events
                        Map result = ss.loadHomeEvent(user.getUserId());				
					
					    session.setAttribute("TotalCollection", result.get("TotalCollection"));
					    session.setAttribute("TotalMember", result.get("TotalMember"));
					    session.setAttribute("DueListCount", result.get("DueListCount"));	
					
					    session.setAttribute("IssueListCount", result.get("IssueListCount"));
					    session.setAttribute("ReturnListCount", result.get("ReturnListCount"));
					    session.setAttribute("RenewListCount", result.get("RenewListCount"));
					
					    
					    session.setAttribute("todayIssueListCount", result.get("todayIssueListCount"));
						session.setAttribute("todayReturnListCount", result.get("todayReturnListCount"));
						session.setAttribute("todayRenewListCount", result.get("todayRenewListCount"));
						  
						  
						session.setAttribute("todayTransAmount", result.get("todayTransAmount"));
						session.setAttribute("todaypaidAmount", result.get("todaypaidAmount"));
						session.setAttribute("todayBalAmount", result.get("todayBalAmount"));
					    
					    
					    session.setAttribute("member_code", result.get("member_code"));
						session.setAttribute("member_name", result.get("member_name"));
						session.setAttribute("desig_name", result.get("desig_name"));
						session.setAttribute("dname", result.get("dname"));
						session.setAttribute("expiry_date", result.get("expiry_date"));
						
						session.setAttribute("sugCount", result.get("suggestionCount"));
						
						session.setAttribute("totalUser", result.get("totalUserCount"));
						session.setAttribute("todayUser", result.get("todayUserCount"));
						session.setAttribute("todayGateUser", result.get("totalGateLogin"));
	
						byte[] userImage=ss.getUserImage(userPhoto,user.getUserId());// shek
						session.setAttribute("userImage", userImage);
					}
					else
					{
                        // Load transaction details for user 
						Map result = ss.loadUserTransactionHome(user.getUserId());	
						byte[] userPhoto=null;
						session.setAttribute("UserIssueCount", result.get("UserIssueCount"));
						session.setAttribute("UserReturnCount", result.get("UserReturnCount"));
						session.setAttribute("UserReserveCount", result.get("UserReserveCount"));						
					
						
						session.setAttribute("totalAmount", result.get("totalAmount"));
						session.setAttribute("paidAmount", result.get("paidAmount"));
						session.setAttribute("balAmount", result.get("balAmount"));
						
						
						session.setAttribute("member_code", result.get("member_code"));
						session.setAttribute("member_name", result.get("member_name"));
						session.setAttribute("desig_name", result.get("desig_name"));
						session.setAttribute("dname", result.get("dname"));
						session.setAttribute("expiry_date", result.get("expiry_date"));
		
						byte[] userImage=ss.getUserImage(userPhoto,user.getUserId());// shek
						session.setAttribute("userImage", userImage);
						
						session.setAttribute("LibraryMessage", result.get("LibraryMessage"));
						session.setAttribute("WhatsNew", result.get("WhatsNew"));
					
					}
					
				    //newbean.setstaffcode(user.getUserId()); //Stopped By RK on 13-09-2013 . For (Error In Counter transaction Module) Staff_Code taken the userid who have recently logged in.  
				    response.sendRedirect("new_index.jsp");
				}
				
		  }else{

				response.sendRedirect("/AutoLib/InvalidUser.jsp");

			 	//dispatch(request, response, indexPage);
			}
			}
		} catch (Exception e) {
			//throw new ServletException(e);
		}
	}
	/**
		 * Post Method of the servlet
	 * @param request request Object
	 * @param response response Object
	 * @return none
	 * @throws IOException
	 * @throws ServletException 
	 * @since 2004
	 */
	public void dispatch(HttpServletRequest request,
			HttpServletResponse response, String indexPage)
			throws ServletException, IOException {
		RequestDispatcher dispatch = request.getRequestDispatcher(indexPage);
		dispatch.forward(request, response);
	}

}
