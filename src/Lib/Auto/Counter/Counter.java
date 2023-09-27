package Lib.Auto.Counter;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.ParseException;
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

import Common.Security;
import Common.Security_Counter;
import Common.LibraryConstants;
import Common.businessutil.BusinessServiceFactory;
import Common.businessutil.calaloging.CalalogingService;
import Common.businessutil.circulation.CirculationService;
import Common.businessutil.mail.MailService;
import Lib.Auto.Book.Book;
import Lib.Auto.Member.MemberBean;
import Lib.Auto.Simples.Searchbean;
import Lib.Auto.Book.bookbean;

import javax.servlet.annotation.WebServlet;

import com.google.gson.Gson;

@WebServlet("/Counter/CounterServlet")


/**
 * @author Counter
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class Counter extends HttpServlet implements Serializable, COUNTER_QUERY {
	  private static Logger log4jLogger = Logger.getLogger(Counter.class);
	/**
	 * 
	 */
	  
	  
	/*
	 * Unwnated functions by RK
	 * 1. findIssuedDetails
	 *   
	 *   */
	  
	  
	  
	private static final long serialVersionUID = 1L;
	ResourceBundle rb = ResourceBundle.getBundle("LocalStrings");

	CounterMethods methods = new CounterMethods();
	ArrayList DEATILS=new ArrayList ();
	ArrayList ser=new ArrayList ();
	ArrayList sers=new ArrayList ();
	
	  String availability="",document="";
	  boolean iss=true;
	
	java.sql.PreparedStatement Prest = null;
	
	String indexPage = null;
	String term="";
	String protocol = "",min="",flag = "",temp1="",pri="",no_days="",temp2="",valid="",sql1="";
	String flag1="",flag2="";
		
	public Counter() {
		super();
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException {

		performTask(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException {

		performTask(request, response);

	}
		public void performTask(
			HttpServletRequest request,
			HttpServletResponse response) throws ServletException{

		try {
			HttpSession session = request.getSession(true);
			String res = Security.checkSecurity(1, session, response, request);		
			if(res.equalsIgnoreCase("Failure"))
			{
				response.sendRedirect("/AutoLib/Tree/sessiontimeout.jsp");  
				return;
			}
			
			
			CirculationService ss = BusinessServiceFactory.INSTANCE.getCirculationService();
			CalalogingService ss1 = BusinessServiceFactory.INSTANCE.getCalalogingService();
			                
            response.setContentType("application/json");
                                    
			try{
				String term = request.getParameter("mcode");
				if(!term.equalsIgnoreCase(null) && !term.equalsIgnoreCase(""))
	            {
				//System.out.println("Data from ajax call " + term);
							    
				   //ArrayList<MemberBean> list = ss1.getCounterAutoMemberIdSearch(term);
				   ArrayList<MemberBean> list = ss1.getMemberAutoIdSearch(term);
			       for(MemberBean user: list){
					//System.out.println(user.getCode());
				}       

				String searchList = new Gson().toJson(list);
							
				response.getWriter().write(searchList);  
							
				//System.out.println("Json Value@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+searchList);
	            }	
		}catch(Exception e){
			//e.printStackTrace();
		}  		 
		try{
				String term = request.getParameter("accno");
				
				if(!term.equalsIgnoreCase(null) && !term.equalsIgnoreCase(""))
	            {
				//System.out.println("Data from ajax call " + term);
							    
				   ArrayList<bookbean> list = ss.getCounterAutoAccessNoSearch(term);
				   //ArrayList<bookbean> list = ss1.getBookAutoAccessNoSearch(term);
			       for(bookbean user: list){
					//System.out.println(user.getAccessNo());
				}       

				String searchList = new Gson().toJson(list);
							
				response.getWriter().write(searchList);  
							
				//System.out.println("Json Value@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+searchList);
	             }	
		}catch(Exception e){
			//e.printStackTrace();
		}    		 

			
			PrintWriter out = response.getWriter();
			int Groups = 0;
			CounterBean counterbeanobject=new CounterBean();
			CounterMemberBean beanobject=new CounterMemberBean();
			CounterFineBean beanobject1=new CounterFineBean();
			ReserveBean resbean=new ReserveBean();
			Receipt receiptBean=new Receipt();
			List receiptList = null;
			
			flag = request.getParameter("flag");
			flag1 = request.getParameter("flag1");
			flag2 = request.getParameter("flag2");
			
			
			if(!flag.equalsIgnoreCase("clear") && !flag.equalsIgnoreCase("clar"))
			{
			 if (request.getParameter("barcode").equalsIgnoreCase("ISSUE")){
				flag="issue";
			 }
			 else if(request.getParameter("barcode").equalsIgnoreCase("RETURN")){
				flag="return";
			 }
			 else if(request.getParameter("barcode").equalsIgnoreCase("RENEW")){
				flag="renew";
			 }
			 else if(request.getParameter("barcode").equalsIgnoreCase("RESERVE")){
				flag="reserve";
			 }			
			 else if(request.getParameter("barcode").equalsIgnoreCase("RE-CANCEL")){
				flag="rescancel";
			 }
			}
			
			
			if(flag.equals("") || flag.isEmpty())
			{
				log4jLogger.info("===========Empty Flag=====" + flag);
			
				counterbeanobject.setPhoto1(null);
				indexPage = "/Counter/index.jsp";
				dispatch(request, response, indexPage);
				
			}
			
			if(flag.equals("clear")) {
				log4jLogger.info("clear===========flag====="+flag);
				
				counterbeanobject.setPhoto1(null);
				indexPage="/Counter/index.jsp";
				dispatch(request, response, indexPage);
			}
			//--------------------------------------member-------------------------------------------------------------*/			
			if(flag.equals("member")) {
				log4jLogger.info("member===========flag====="+request.getParameter("mcode"));
				counterbeanobject=new CounterBean();								
				
				counterbeanobject = ss.getCounterMember(request.getParameter("mcode").toString().trim());
				
				Groups=ss.getCounterGroup(counterbeanobject.getGroup().trim());
				
				String SLock="";
				SLock=counterbeanobject.getSLock().trim();
							
				 if(SLock.trim().equals("YES"))
				 {
					 indexPage="/Counter/index.jsp?Message=MemberLock";
					 request.setAttribute("SLock",counterbeanobject.getMcode().trim());					 

				 }else if(SLock.trim().equals("CLEARANCE")){
					 indexPage="/Counter/index.jsp?Message=MemberClearance";
					 request.setAttribute("SLock",counterbeanobject.getMcode().trim());	
				 }
				 
				 
				 else{
				
				ArrayList issue_details=  (ArrayList) ss.getIssueDetailsMember(request.getParameter("mcode").toString().trim());
				counterbeanobject.setCounterList(issue_details);	
				
				
				ArrayList reserve_details1= (ArrayList) ss.getReserveDetailsMember(request.getParameter("mcode").toString().trim());
				int reserve_details=reserve_details1.size();
				counterbeanobject.setCunterList_RESERVE(reserve_details1);				
			
				request.setAttribute("bean",counterbeanobject);
					
				if((Groups>0)&& (reserve_details>0)){
					
				indexPage="/Counter/index.jsp?flag=member&detils=ISSUEDEATILS&Reservedetils=RESERVEDEATILS";
									
				} else if (Groups>0){
					
					indexPage="/Counter/index.jsp?flag=member&detils=ISSUEDEATILS";
				}
				else
				{									
					indexPage="/Counter/index.jsp?Message=MemberNotFound";
				} 
				}
				dispatch(request, response, indexPage);
			}
			
			//--------------------------------------BOOK-------------------------------------------------------------*/			
			if (flag.equals("book")) {
				log4jLogger.info("book===========flag====="+request.getParameter("accno"));	
				beanobject=new CounterMemberBean();
				counterbeanobject=new CounterBean();
								
				beanobject  = ss.getCounterBook(request.getParameter("accno").toString().trim(),request.getParameter("doc").toString().trim(),request.getParameter("mcode"));
				String acc_no=beanobject.getAccno();
				
				String Mem_code=request.getParameter("mcode").toString().trim();
				String doc_type=beanobject.getDoc().toString().trim();
				
				if (acc_no!=""){
					if(beanobject.getAvail().equals("ISSUED") ||  beanobject.getAvail().equals("REFISSUED")){	// For Reference Book Issue								
						
							int chk_code=ss.getIssueCheck(request.getParameter("accno").toString().trim(),request.getParameter("mcode").toString().trim());
							
							if(chk_code<=0)
							{
								
								if(Mem_code!=null && !Mem_code.isEmpty())
								{
								 
								 counterbeanobject =	ss.getMemberLoad(request.getParameter("mcode").toString().trim(),doc_type,beanobject.getAvail());
								 request.setAttribute("bean",counterbeanobject);	
								 
								 indexPage = "/Counter/index.jsp?flag=book";
								}
								else
								{	
									counterbeanobject= ss.getCounterIssueCheck(request.getParameter("accno").toString().trim());
									String issue_check =counterbeanobject.getIssue_Check();
									String memberCode = counterbeanobject.getMcode();
									
									request.setAttribute("bean",counterbeanobject);
									
									if(issue_check=="ITRUE")
									{					
										counterbeanobject=new CounterBean();					
										counterbeanobject = ss.getCounterMember(memberCode.toString().trim());
										
										 if(counterbeanobject.getSLock().trim().equals("YES"))
										 {
											 indexPage="/Counter/index.jsp?Message=MemberLock";
											 request.setAttribute("SLock",counterbeanobject.getMcode().trim());					 
										 }else  {
											 indexPage ="/Counter/index.jsp?flag=member&detils=ISSUEDEATILS&load=loadbook&Reservedetils=RESERVEDEATILS";
										 }									    
									}
									else
									{										
										indexPage = "/Counter/index.jsp?flag=book";
									}									
								}
							}
							
							else
							{	
								counterbeanobject= ss.getCounterIssueCheck(request.getParameter("accno").toString().trim());
								String issue_check =counterbeanobject.getIssue_Check();
								String memberCode = counterbeanobject.getMcode();
								
								request.setAttribute("bean",counterbeanobject);
								
								if(issue_check=="ITRUE")
								{					
									counterbeanobject=new CounterBean();					
									counterbeanobject = ss.getCounterMember(memberCode.toString().trim());
									
									 if(counterbeanobject.getSLock().trim().equals("YES"))
									 {
										 indexPage="/Counter/index.jsp?Message=MemberLock";
										 request.setAttribute("SLock",counterbeanobject.getMcode().trim());					 
									 }else  {
										 indexPage ="/Counter/index.jsp?flag=member&detils=ISSUEDEATILS&load=loadbook&Reservedetils=RESERVEDEATILS";
									 }									    
								}
								else
								{										
									indexPage = "/Counter/index.jsp?flag=book";
								}									
							}
																				
					}
					
					else
					{
						if ((request.getParameter("mcode").equals(""))&& (counterbeanobject.getAvail().equals("YES"))) {
							indexPage ="/Counter/index.jsp?load=loadbook&Reservedetils=RESERVEDEATILS";
						} 
						else if(request.getParameter("mcode").equals("")){
							indexPage = "/Counter/index.jsp?flag=book";
						}
						
						else { // Checked rk.							
							
							counterbeanobject =	ss.getMemberLoad(request.getParameter("mcode").toString().trim(),doc_type, beanobject.getAvail());							
							
							ArrayList issue_details= (ArrayList) ss.getIssueDetailsMember(request.getParameter("mcode").toString().trim());							
							counterbeanobject.setCounterList(issue_details);
							ArrayList reerve_details1=	(ArrayList) ss.getReserveDetailsMember(request.getParameter("mcode").toString().trim());
							counterbeanobject.setCunterList_RESERVE(reerve_details1);
							int reserve_details=reerve_details1.size();											
							request.setAttribute("bean",counterbeanobject);							
							
							
							if (reserve_details>0){
								indexPage ="/Counter/index.jsp?flag=member&load=loadbook&detils=ISSUEDEATILS&Reservedetils=RESERVEDEATILS";
							}	
							else{
							indexPage ="/Counter/index.jsp?flag=member&load=loadbook&detils=ISSUEDEATILS";
							}
						}
					}
				}	
				else{						
						
						if(Mem_code!=null && !Mem_code.isEmpty())
						{
							//int issue_details1=1;
							
							counterbeanobject =	ss.getMemberLoad(request.getParameter("mcode").toString().trim(),doc_type, beanobject.getAvail());
							int issue_details1=counterbeanobject.getCountperiod();
							
							if(counterbeanobject.getMcode()!="" && counterbeanobject.getMcode()!=null) {
								
							     ArrayList Issue_Dtls= (ArrayList) ss.getIssueDetailsMember(request.getParameter("mcode").toString().trim());
							     counterbeanobject.setCounterList(Issue_Dtls);
							}
							request.setAttribute("bean",counterbeanobject);
							
							if(issue_details1>0)
							{
								indexPage="/Counter/index.jsp?flag=member&Message=BookNotbook1&detils=ISSUEDEATILS";
							}
							else
							{
								indexPage="/Counter/index.jsp?Message=BookNotbook1";
							}
							
						}
						else
						{										
							   indexPage="/Counter/index.jsp?Message=BookNotbook"; 
						}
					
				}		
				request.setAttribute("beanmember",beanobject);
								
				dispatch(request, response, indexPage);
			}
		

			// BOOK ISSUE-----------------------------------------------------------------------------------//
						
			if (flag.equals("issue")) {
				log4jLogger.info("book issue counter===========flag====="+flag);
				valid=Security_Counter.TextDate_Insert(request.getParameter("validDate"));
				String today=Security_Counter.TodayDate();
				 String date_valid =ss.getValidDate(Security_Counter.TextDate_Insert(request.getParameter("validDate")));			
				 
				 counterbeanobject=new CounterBean();
				 
				if(date_valid!="") {
					no_days = date_valid;					
				}
				String memb_code =ss.getMemberCode(request.getParameter("mcode"));			
								
			       if(memb_code!=""){
			    	   iss=true;
			       }
			       else
			       {
                        indexPage="/Counter/index.jsp?Message=validMem";
			    	   iss=false; 
			       }
			       
				int s=Integer.parseInt(no_days);		
		 
				if (s < 0) {					
					indexPage="/Counter/index.jsp?Message=valid";
				}
				else if(iss)
				{
					beanobject=new CounterMemberBean();
					beanobject =ss.getIssueMasCheck(request.getParameter("accno").toString().trim(),request.getParameter("doc").toString().trim());				
					
			          if(beanobject.getAvail()!=""){
			    	  availability=beanobject.getAvail();
			    	  document=beanobject.getDoc();
			      }			          			          
			       else
			       {
			    	  	   indexPage="/Counter/index.jsp?Message=booknot";
			       }
			       if(availability.equals("ISSUED") ||  availability.equals("REFISSUED")|| (!request.getParameter("doc").equals(document))) //for ref issue
			       {
			    	   
			    	   indexPage="/Counter/index.jsp?Message=alreadyissued";
			       }
			       
				else
				{									
				String	reserve_check =ss.getReserveMasCheck(request.getParameter("accno"));
			       if(reserve_check!=""){			    	  
				    	 min=reserve_check;			    				    	  
	               }
			       beanobject=new CounterMemberBean();
			       beanobject.setMcode(request.getParameter("mcode"));
					beanobject.setAccno(request.getParameter("accno"));
					beanobject.setIdate(Security_Counter.TextDate_Insert(request.getParameter("idate")));
					beanobject.setDdate(Security_Counter.TextDate_Insert(request.getParameter("ddate")));
					beanobject.setTitle(request.getParameter("title"));
					beanobject.setAuthor(String.valueOf(session.getAttribute("UserID")));
					beanobject.setDoc(request.getParameter("doc"));
					beanobject.setMname(request.getParameter("mname"));
			      
			       resbean=new ReserveBean();
			       resbean =ss.getReserveMssSelect(request.getParameter("accno"));
			       
					if(resbean.getMcode()!=""){
					temp1=resbean.getMcode();
					pri=resbean.getRcode();
					temp2=resbean.getMname();
					if((temp1.equalsIgnoreCase(request.getParameter("mcode")) && (pri.equals(min))))   // Ignore case added by RK
					{	
												
						beanobject.setMcode(request.getParameter("mcode"));
						beanobject.setAccno(request.getParameter("accno"));
						int res_delete =ss.getReserveMssDelete(beanobject);	
						
												
						int doc_issue =ss.getIssueMasInsert(beanobject);
		
						if (doc_issue>0){
							
							receiptBean = new Receipt();
							receiptBean.setCode(request.getParameter("mcode"));
							receiptBean.setName(request.getParameter("mname"));
							receiptBean.setAccno(request.getParameter("accno"));					
							receiptBean.setTitle(request.getParameter("title"));					
							receiptBean.setIdate(request.getParameter("idate"));
							receiptBean.setDdate(request.getParameter("ddate"));					
							receiptBean.setDocument(request.getParameter("doc"));
							receiptBean.setStatus("ISSUE");
							
								indexPage="/Counter/index.jsp?Message=issued&detils=ISSUEDEATILS";
							}
																		
				}
				
				else
				{		
							request.setAttribute("temp1",temp1);
							request.setAttribute("temp2",temp2);
							int issue_details =ss.getIssuedDetails(beanobject);
							
							indexPage="/Counter/index.jsp?Message=issue_error&detils=ISSUEDEATILS";			
				}
			}
			else
			{
								
				int doc_issue =ss.getIssueMasInsert(beanobject);			
				
					if (doc_issue>0) {	
						receiptBean = new Receipt();
						receiptBean.setCode(request.getParameter("mcode"));
						receiptBean.setName(request.getParameter("mname"));
						receiptBean.setAccno(request.getParameter("accno"));					
						receiptBean.setTitle(request.getParameter("title"));					
						receiptBean.setIdate(request.getParameter("idate"));
						receiptBean.setDdate(request.getParameter("ddate"));					
						receiptBean.setDocument(request.getParameter("doc"));
						receiptBean.setStatus("ISSUE");
						
						indexPage="/Counter/index.jsp?Message=issued&detils=ISSUEDEATILS&avoid=error";		
					}		
						  
			}			
				}
			   	       
				}	
				
                counterbeanobject = ss.getCounterMember(request.getParameter("mcode").toString().trim());                			
				ArrayList issue_details=	(ArrayList) ss.getIssueDetailsMember(request.getParameter("mcode").toString().trim());
				counterbeanobject.setCounterList(issue_details);				

				request.setAttribute("bean",counterbeanobject);			
				
				if(receiptBean!=null)
				{
					receiptList = new ArrayList();
					receiptList.add(receiptBean);
					session.setAttribute("ReceiptList",receiptList);	
					session.setAttribute("ReceiptListSize",receiptList.size());					
				}
				
				dispatch(request, response, indexPage);
			}
		

			//--------------------------------------RETURN------------------------------------------------------------*/

			if (flag.equals("return")) {
				log4jLogger.info("book return===========flag====="+flag);
				counterbeanobject=new CounterBean();
				beanobject=new CounterMemberBean();
				    
				    beanobject.setMcode(request.getParameter("mcode"));
					beanobject.setAccno(request.getParameter("accno"));
					beanobject.setIdate(Security_Counter.TextDate_Insert(request.getParameter("idate")));
					beanobject.setDdate(Security_Counter.TextDate_Insert(request.getParameter("ddate")));
					beanobject.setRdate(Security_Counter.TextDate_Insert(request.getParameter("rdate")));
					beanobject.setTitle(request.getParameter("title"));
					beanobject.setAuthor(String.valueOf(session.getAttribute("UserID")));
					beanobject.setDoc(request.getParameter("doc"));
					beanobject.setMname(request.getParameter("mname"));
					
					counterbeanobject=ss.getDocmentReturn(beanobject);
					MailService ss2=BusinessServiceFactory.INSTANCE.getMailService();
					if(!counterbeanobject.getRes_member().isEmpty())
					{
						String mcode = counterbeanobject.getRes_member();
						String accno = beanobject.getAccno();
						ss2.getReserveReminderMail(mcode,accno);
					}
				   
					int doc_return=counterbeanobject.getDoc_Return();
					
			
				if (doc_return>0) {
					
					receiptBean = new Receipt();
					receiptBean.setCode(request.getParameter("mcode"));
					receiptBean.setName(request.getParameter("mname"));
					receiptBean.setAccno(request.getParameter("accno"));					
					receiptBean.setTitle(request.getParameter("title"));					
					receiptBean.setIdate(request.getParameter("idate"));
					receiptBean.setDdate(request.getParameter("ddate"));					
					receiptBean.setDocument(request.getParameter("doc"));
					receiptBean.setStatus("RETURN");
					
							Double Temp = counterbeanobject.getTemp();
							if (Temp.doubleValue() == 0.0) {
								
								indexPage="/Counter/index.jsp?Message=return&detils=ISSUEDEATILS";
							} else {
								
								session.setAttribute("FINE", Temp);								
								
								beanobject=new CounterMemberBean();
							    beanobject.setMcode(request.getParameter("mcode"));
								beanobject.setAccno(request.getParameter("accno"));
								beanobject.setIdate(Security_Counter.TextDate_Insert(request.getParameter("idate")));
								beanobject.setDdate(Security_Counter.TextDate_Insert(request.getParameter("ddate")));
								beanobject.setRdate(Security_Counter.TextDate_Insert(request.getParameter("rdate")));
								beanobject.setTitle(request.getParameter("title"));
								beanobject.setAuthor(String.valueOf(session.getAttribute("UserID")));
								beanobject.setDoc(request.getParameter("doc"));
								beanobject.setMname(request.getParameter("mname"));
								beanobject.setTemp(counterbeanobject.getTemp());	
								
								receiptBean.setFineAmt(counterbeanobject.getTemp());    // For Fine amount to Receipt
																
								beanobject1=new CounterFineBean();
								
							    beanobject1.setMcode(request.getParameter("mcode"));
								beanobject1.setAccno(request.getParameter("accno"));
								beanobject1.setIdate(Security_Counter.TextDate_Insert(request.getParameter("idate")));
								beanobject1.setDdate(Security_Counter.TextDate_Insert(request.getParameter("ddate")));
								beanobject1.setRdate(Security_Counter.TextDate_Insert(request.getParameter("rdate")));
								beanobject1.setTitle(request.getParameter("title"));
								beanobject1.setAuthor(String.valueOf(session.getAttribute("UserID")));
								beanobject1.setDoc(request.getParameter("doc"));
								beanobject1.setMname(request.getParameter("mname"));
								beanobject1.setTemp(counterbeanobject.getTemp());
								
								
								request.setAttribute("beanobject",beanobject1);
								
								String rights = String.valueOf(session.getAttribute("UserRights"));
								
								// For Fine Change
						/*	if(rights.equalsIgnoreCase("1"))
							{
								indexPage="/Counter/index.jsp?flag=adminreturn&detils=ISSUEDEATILS";
							}
							else { */
								int doc_fine =ss.getDocmentFine(beanobject);
								indexPage="/Counter/index.jsp?flag=freturn&detils=ISSUEDEATILS";
							//}										
								//int doc_fine =ss.getDocmentFine(beanobject);
								//indexPage="/Counter/index.jsp?flag=freturn&detils=ISSUEDEATILS";
							
							}
				}
				else
				{
		 						indexPage="/Counter/index.jsp?flag=noissue&detils=ISSUEDEATILS";
				}

				
				

				
				counterbeanobject = ss.getCounterMember(request.getParameter("mcode").toString().trim());
				
				ArrayList issue_details=	(ArrayList) ss.getIssueDetailsMember(request.getParameter("mcode").toString().trim());
				counterbeanobject.setCounterList(issue_details);
				
				request.setAttribute("bean",counterbeanobject);
				
				if(receiptBean!=null)
				{
					receiptList = new ArrayList();
					receiptList.add(receiptBean);
					session.setAttribute("ReceiptList",receiptList);	
					session.setAttribute("ReceiptListSize",receiptList.size());					
				}
						
				dispatch(request, response, indexPage);

			}
			//-----------------------------------RESERVE----------------------------------------------------//
			if (flag.equals("reserve")) {
				log4jLogger.info("book reserve===========flag====="+flag);				
				
				int cnt = 0;				
					
				int select_member=	ss.getMemberMasSelect(request.getParameter("mcode").toString().trim());
				
				if (select_member>0) {		
					
				beanobject=new CounterMemberBean();
			    beanobject.setMcode(request.getParameter("mcode"));
				beanobject.setAccno(request.getParameter("accno"));
				int issue_only=	ss.getIssueMasSelect(beanobject);			

				if (issue_only>0) {		
							indexPage="/Counter/index.jsp?Message=reser&detils=ISSUEDEATILS";
				} else {					
					beanobject=new CounterMemberBean();
				    beanobject.setMcode(request.getParameter("mcode"));
					beanobject.setAccno(request.getParameter("accno"));
					int reserve_only=	ss.getReserveMasSelect(beanobject);					
					if (reserve_only>0) {					

						indexPage="/Counter/index.jsp?Message=res_error&detils=ISSUEDEATILS";
						
					} else {
						int reserve_count=	ss.getReserveMasCount(request.getParameter("mcode").trim());
						counterbeanobject = new CounterBean();
						counterbeanobject = ss.getCounterMember(request.getParameter("mcode").toString().trim());  // RK Start 17-09-2014
						
						if( reserve_count < counterbeanobject.getReserve() ){							
						
						beanobject=new CounterMemberBean();
					    beanobject.setMcode(request.getParameter("mcode"));
						beanobject.setAccno(request.getParameter("accno"));
						beanobject.setDoc(request.getParameter("doc"));
						beanobject.setRdate(Security_Counter.TextDate_Insert(request.getParameter("rdate")));						
						int reserve_save=	ss.getReserveMasSave(beanobject);												
						indexPage="/Counter/index.jsp?Message=reserved&Reservedetils=RESERVEDEATILS&detils=ISSUEDEATILS";
					}else{
						indexPage="/Counter/index.jsp?Message=maxreserved&Reservedetils=RESERVEDEATILS&detils=ISSUEDEATILS";							
					}						
				  }
				}
				
				counterbeanobject=new CounterBean();
                counterbeanobject = ss.getCounterMember(request.getParameter("mcode").toString().trim());
				
				ArrayList issue_details=	(ArrayList) ss.getIssueDetailsMember(request.getParameter("mcode").toString().trim());
				counterbeanobject.setCounterList(issue_details);
				ArrayList reerve_details=	(ArrayList) ss.getReserveDetailsMember(request.getParameter("mcode").toString().trim());
				counterbeanobject.setCunterList_RESERVE(reerve_details);
				
				request.setAttribute("bean",counterbeanobject);
				}
				else
				{					
					indexPage="/Counter/index.jsp?Message=MemberNotFound";					
				} 
				dispatch(request, response, indexPage);
			}
			//----------------------------------*-------Rescancel-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\\

			if (flag.equals("rescancel")) {
				log4jLogger.info("book rescancel===========flag====="+flag);
				counterbeanobject=new CounterBean();
								
				beanobject=new CounterMemberBean();
			    beanobject.setMcode(request.getParameter("mcode"));
				beanobject.setAccno(request.getParameter("accno"));
				int reserve_only=	ss.getReserveMasSelect(beanobject);		
				

				if (reserve_only>0) 
				{
					beanobject=new CounterMemberBean();
					beanobject.setMcode(request.getParameter("mcode"));
					beanobject.setAccno(request.getParameter("accno"));
					int res_delete =ss.getReserveMssDelete(beanobject);	
					
					indexPage="/Counter/index.jsp?Message=rescancel&detils=ISSUEDEATILS&Reservedetils=RESERVEDEATILS";
				} 
				else 
				{
					
					indexPage="/Counter/index.jsp?Message=rescancel_error&detils=ISSUEDEATILS";
				}
                counterbeanobject = ss.getCounterMember(request.getParameter("mcode").toString().trim());
				
				ArrayList issue_details=	(ArrayList) ss.getIssueDetailsMember(request.getParameter("mcode").toString().trim());
				counterbeanobject.setCounterList(issue_details);
				
				ArrayList reerve_details=	(ArrayList) ss.getReserveDetailsMember(request.getParameter("mcode").toString().trim());
				counterbeanobject.setCunterList_RESERVE(reerve_details);
				request.setAttribute("bean",counterbeanobject);	
				
				dispatch(request, response, indexPage);
			}
			
            //			--------------------------  Payment Clearance ----------------------------------------------------//
			
			
			if (flag.equals("savefine_payment")) {
				log4jLogger.info("Payment ===========flag=====" + flag);

				counterbeanobject = new CounterBean();
				beanobject = new CounterMemberBean();
				beanobject1 = new CounterFineBean();

				beanobject1.setMcode(request.getParameter("mcode"));
				beanobject1.setAccno(request.getParameter("accno"));
				beanobject1.setIdate(request.getParameter("idate"));
				beanobject1.setDdate(request.getParameter("ddate"));
				beanobject1.setRdate(request.getParameter("rdate"));

				beanobject1.setDoc(request.getParameter("doc"));
				beanobject1.setTemp(Double.parseDouble(request
						.getParameter("fine")));

				beanobject.setMcode(beanobject1.getMcode());
				beanobject.setAccno(beanobject1.getAccno());
				beanobject.setIdate(beanobject1.getIdate());
				beanobject.setDdate(beanobject1.getDdate());
				beanobject.setRdate(beanobject1.getRdate());
				beanobject.setDoc(beanobject1.getDoc());
				beanobject.setTemp(beanobject1.getTemp());
				beanobject.setAuthor(String.valueOf(session
						.getAttribute("UserID")));

				int issue_details1 = 0;

				if (flag1.equals("adminsavefine"))

				{
					int doc_fine = ss.getDocmentFine(beanobject);
					issue_details1 = ss.getFineDetail(beanobject);
				} else {
					issue_details1 = ss.getFineDetail(beanobject);
				}

				if (issue_details1 > 0) {

					counterbeanobject = ss.getCounterMember(beanobject
							.getMcode().toString().trim());

					ArrayList issue_details = (ArrayList) ss
							.getIssueDetailsMember(request
									.getParameter("mcode").toString().trim());

					counterbeanobject.setCounterList(issue_details);

					request.setAttribute("bean", counterbeanobject);

		//			indexPage = "/Counter/index.jsp?flag=rkfine&detils=ISSUEDEATILS";
					
					indexPage = "/Counter/index.jsp?Message=return&detils=ISSUEDEATILS";	// for instant return with fine
				} else {
					indexPage = "/Counter/index.jsp?";
					// return;
				}

				dispatch(request, response, indexPage);
			}	
		

			//--------------------------RENEW BOOK ----------------------------------------------------//		

			if (flag.equals("renew")) {
				
				log4jLogger.info("book renew===========flag====="+flag);				
				counterbeanobject=new CounterBean();
				
				String DueDate = "";
				String no_of_days = "";
				String sql = "";
				int no = 1;
				String ddate = "";
				String Rdate = "";
				String Ddate = "";
				int n = 0;
				int Holiday_Check = 0, cal = 0, Holiday = 0;
				double temp = 0;
				
				
				
				int select_member=	ss.getMemberMasSelect(request.getParameter("mcode").toString().trim());
				

		
				if (select_member>0) {					
				 		
				beanobject=new CounterMemberBean();
				
				beanobject.setMcode(request.getParameter("mcode").toString().trim());
				beanobject.setAccno(request.getParameter("accno"));		
				int renewcheck=ss.getRenewCheck(beanobject);				
				
				if(renewcheck==0){
					
					counterbeanobject = ss.getCounterMember(request.getParameter("mcode").toString().trim());					
					ArrayList issue_details=	(ArrayList) ss.getIssueDetailsMember(request.getParameter("mcode").toString().trim());
					counterbeanobject.setCounterList(issue_details);				
					request.setAttribute("bean",counterbeanobject);

					indexPage="/Counter/index.jsp?flag=noissue&detils=ISSUEDEATILS";					
				}
                else if(renewcheck==2){
					
					counterbeanobject = ss.getCounterMember(request.getParameter("mcode").toString().trim());					
					ArrayList issue_details=	(ArrayList) ss.getIssueDetailsMember(request.getParameter("mcode").toString().trim());
					counterbeanobject.setCounterList(issue_details);				
					request.setAttribute("bean",counterbeanobject);

					indexPage="/Counter/index.jsp?flag=reservedbook&detils=ISSUEDEATILS";					
				}
				else {
				
				beanobject=ss.getIssueMas(request.getParameter("accno"));
				
				
				
				if (beanobject.getGroup()!="") {					
					DueDate=beanobject.getDdate();
					Groups=Integer.parseInt(beanobject.getGroup());
								
				}		
			
				java.util.StringTokenizer stz_du =
					new java.util.StringTokenizer(Security_Counter.TodayDate(), "-");
					int diy = Integer.parseInt(stz_du.nextToken());
					int dim = Integer.parseInt(stz_du.nextToken());
					int did = Integer.parseInt(stz_du.nextToken());
					Rdate = diy + "-" + dim + "-" + did;

			java.util.StringTokenizer stz_du1 =
				new java.util.StringTokenizer(DueDate, "-");
				int diy1 = Integer.parseInt(stz_du1.nextToken());
				int dim1 = Integer.parseInt(stz_du1.nextToken());
				int did1= Integer.parseInt(stz_du1.nextToken());
				ddate = diy1 + "-" + dim1 + "-" + did1;

				
				beanobject.setRdate(request.getParameter("rdate"));
				beanobject.setDdate(request.getParameter("ddate"));
				beanobject.setMcode(request.getParameter("mcode"));
				beanobject.setDoc(request.getParameter("doc"));
												
				counterbeanobject=ss.getNumberofDays(beanobject);
				
			
				 if (counterbeanobject.getAccno()!=null) {
					
					 no_of_days=counterbeanobject.getTitle();
					 n=Integer.parseInt(no_of_days);
					 
					 
					 beanobject.setRissued(Groups);
					 beanobject.setBissued(n);		
					 counterbeanobject=ss.getFineCall(beanobject);    // Please add CounterBean here for fine Amount						
					 Double Temp = counterbeanobject.getTemp();
					 
					if(((n< 0)||(n==0)) || (Temp == 0.0)) {
						
						
						n = counterbeanobject.getCountperiod();
						
						
						Ddate=Security_Counter
						.TextDate_Insert(counterbeanobject.getCalldate());				
						
						
						beanobject.setRdate(Rdate);
						beanobject.setDdate(Ddate);
						beanobject.setMcode(request.getParameter("mcode"));
						beanobject.setDoc(request.getParameter("doc"));
						beanobject.setTperiod(Groups);
						beanobject.setAccno(request.getParameter("accno"));
						
						beanobject.setValidDate(Security_Counter.TextDate_Insert(request.getParameter("ddate").toString()));
						
						beanobject =ss.getUpdateRenewMasNofine(beanobject);
						
						 
						// Changed by RK
						
						if (beanobject.getSperiod()>0){						
													
						
							beanobject.setRdate(Rdate);
							beanobject.setDdate(Ddate);
							beanobject.setMcode(request.getParameter("mcode"));
							beanobject.setDoc(request.getParameter("doc"));
							beanobject.setTperiod(Groups);
							beanobject.setAccno(request.getParameter("accno"));
							beanobject.setTeligible(beanobject.getTeligible());
							beanobject.setIdate(request.getParameter("idate"));
							beanobject.setTitle(String.valueOf(session.getAttribute("UserID")));
							
							beanobject.setValidDate(Security_Counter.TextDate_Insert(request.getParameter("ddate").toString()));
							
							int renew =ss.getUpdateRenewMas(beanobject);
							
							receiptBean = new Receipt();
							receiptBean.setCode(request.getParameter("mcode"));
							receiptBean.setName(request.getParameter("mname"));
							receiptBean.setAccno(request.getParameter("accno"));					
							receiptBean.setTitle(request.getParameter("title"));					
							receiptBean.setIdate(Security_Counter.getdate(Rdate));
							receiptBean.setDdate(Security_Counter.getdate(Ddate));					
							receiptBean.setDocument(request.getParameter("doc"));
							receiptBean.setStatus("RENEW");
						
							indexPage="/Counter/index.jsp?Message=renew&detils=ISSUEDEATILS";
						}
						else
						{
							indexPage="/Counter/index.jsp?Message=nrenew&mcode=membercode";
						}
						
						
						
																	
					} else {
						
						
						//Holiday=n;		
					beanobject.setRissued(Groups);
					beanobject.setBissued(n);					
					n = counterbeanobject.getCountperiod();
										
					Ddate=Security_Counter.TextDate_Insert(counterbeanobject.getCalldate());				
						
 				     //counterbeanobject=ss.getFineCall(beanobject);    // Please add CounterBean here for fine Amount					
					//Temp = counterbeanobject.getTemp();
					
					session.setAttribute("FINE", Temp);
					
					String doctype=request.getParameter("doc");
					//out.print("Doc Type"+doctype);
					
					
					beanobject.setRdate(Rdate);
					beanobject.setDdate(Ddate);
					beanobject.setMcode(request.getParameter("mcode"));
					beanobject.setDoc(request.getParameter("doc"));
					beanobject.setTperiod(Groups);
					beanobject.setAccno(request.getParameter("accno"));
					
					beanobject.setValidDate(Security_Counter.TextDate_Insert(request.getParameter("ddate").toString()));
					
					beanobject =ss.getUpdateRenewMasNofine(beanobject);
					
					// Changed by RK
					
					 if (beanobject.getSperiod()>0){
						 
						    beanobject.setRdate(Rdate);
							beanobject.setDdate(Ddate);
							beanobject.setMcode(request.getParameter("mcode"));
							beanobject.setDoc(request.getParameter("doc"));
							beanobject.setTperiod(Groups);
							beanobject.setAccno(request.getParameter("accno"));
							beanobject.setTeligible(beanobject.getTeligible());
							beanobject.setIdate(request.getParameter("idate"));
							beanobject.setTitle(String.valueOf(session.getAttribute("UserID")));
							beanobject.setTemp(Temp);
							
							beanobject.setValidDate(Security_Counter.TextDate_Insert(request.getParameter("ddate").toString()));
							
							int renew =ss.getUpdateRenewMasFine(beanobject);
							
							receiptBean = new Receipt();
							receiptBean.setCode(request.getParameter("mcode"));
							receiptBean.setName(request.getParameter("mname"));
							receiptBean.setAccno(request.getParameter("accno"));					
							receiptBean.setTitle(request.getParameter("title"));					
							receiptBean.setIdate(Security_Counter.getdate(Rdate));
							receiptBean.setDdate(Security_Counter.getdate(Ddate));					
							receiptBean.setDocument(request.getParameter("doc"));
							receiptBean.setStatus("RENEW");
							receiptBean.setFineAmt(Temp);    // For Fine amount to Receipt
																					
							indexPage="/Counter/index.jsp?flag=fwreturn&detils=ISSUEDEATILS";							
					 }
					 else
					 {						
						 indexPage="/Counter/index.jsp?Message=nrenew&mcode=membercode";
					 }
					
		}
			} 
				}	
				    counterbeanobject = ss.getCounterMember(request.getParameter("mcode").toString().trim());					
					ArrayList issue_details=	(ArrayList) ss.getIssueDetailsMember(request.getParameter("mcode").toString().trim());
					counterbeanobject.setCounterList(issue_details);
							
					request.setAttribute("bean",counterbeanobject);
				}
				else
				{				
					indexPage="/Counter/index.jsp?Message=MemberNotFound";

				}	
				
				if(receiptBean!=null)
				{
					receiptList = new ArrayList();
					receiptList.add(receiptBean);
					session.setAttribute("ReceiptList",receiptList);	
					session.setAttribute("ReceiptListSize",receiptList.size());					
				}
				
				dispatch(request, response, indexPage);
				
			}
			
			
			System.out.println("IndexPage::::"+indexPage);
			//-----------------------------------------------------------------END TRY CATCH----------------//
		} catch (Exception e) {
			throw new ServletException(e);
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
