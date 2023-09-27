package Lib.Auto.Bulk_Counter;

import java.io.IOException;
import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import Common.Security;
import Common.Security_Counter;

import Common.businessutil.BusinessServiceFactory;
import Common.businessutil.calaloging.CalalogingService;
import Common.businessutil.circulation.CirculationService;

import Lib.Auto.Book.bookbean;
import Lib.Auto.Counter.COUNTER_QUERY;
import Lib.Auto.Counter.CounterBean;
import Lib.Auto.Counter.CounterFineBean;
import Lib.Auto.Counter.CounterMemberBean;
import Lib.Auto.Counter.ReserveBean;
import Lib.Auto.Member.MemberBean;

import javax.servlet.annotation.WebServlet;
import com.google.gson.Gson;

@WebServlet("/Bulk_Counter/BulkCounterServlet")

/**
 * @author Counter
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class BulkCounter extends HttpServlet implements Serializable, COUNTER_QUERY {
	
	private static Logger log4jLogger = Logger.getLogger(BulkCounter.class);  
	private static final long serialVersionUID = 1L;
	ResourceBundle rb = ResourceBundle.getBundle("LocalStrings");

	CounterList listBulk = new CounterList();
	String availability="",document="";
	String term="";
	boolean iss=true;
	
	java.sql.PreparedStatement Prest = null;
	
	String indexPage = null;	
	String protocol = "",min="",flag = "",temp1="",pri="",no_days="",temp2="",valid="",sql1="";
	String flag1="",flag2="";
	
	public BulkCounter() {
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

			
			int Groups = 0;
			CounterBean counterbeanobject=new CounterBean();
			CounterMemberBean beanobject=new CounterMemberBean();
			CounterFineBean beanobject1=new CounterFineBean();
			ReserveBean resbean=new ReserveBean();
			
			flag = request.getParameter("flag");
			flag1 = request.getParameter("flag1");
			flag2 = request.getParameter("flag2");
			
						
			if(!flag.equalsIgnoreCase("clear") && !flag.equalsIgnoreCase("clar"))
			{
			 if (request.getParameter("barcode").equals("ISSUE")){
				flag="issue";
			 }
			 else if(request.getParameter("barcode").equals("RETURN")){
				flag="return";
			 }
			 else if(request.getParameter("barcode").equals("RENEW")){
				flag="renew";
			 }			 
			}
			
			
			if(flag.equals("") || flag.isEmpty())
			{
				log4jLogger.info("===========Empty Flag=====" + flag);
			
				counterbeanobject.setPhoto1(null);
				indexPage = "/Bulk_Counter/index.jsp";
				dispatch(request, response, indexPage);
				
			}
			
			if(flag.equals("clear")) {
				log4jLogger.info("clear===========flag====="+flag);
				
				counterbeanobject.setPhoto1(null);
				indexPage="/Bulk_Counter/index.jsp";			
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
					 indexPage="/Bulk_Counter/index.jsp?Message=MemberLock";
					 request.setAttribute("SLock",counterbeanobject.getMcode().trim());					 

				 }
				 
				 else if(SLock.trim().equals("CLEARANCE")){
					 indexPage="/Bulk_Counter/index.jsp?Message=MemberClearance";
					 request.setAttribute("SLock",counterbeanobject.getMcode().trim());	
				 }
				 else{
				
				ArrayList issue_details=  (ArrayList) ss.getIssueDetailsMember(request.getParameter("mcode").toString().trim());
				counterbeanobject.setCounterList(issue_details);
				
				request.setAttribute("bean",counterbeanobject);
					
				if(Groups>0) {					
				    indexPage="/Bulk_Counter/index.jsp?flag=member&detils=ISSUEDEATILS";									
				} 
				else
				{									
					indexPage="/Bulk_Counter/index.jsp?Message=MemberNotFound";
				}
				
				}
				dispatch(request, response, indexPage);
			}
			
			
			//--------------------------------------BOOK-------------------------------------------------------------*/			
			if (flag.equals("book")) {
				log4jLogger.info("book===========flag====="+request.getParameter("accno"));	
				
				beanobject=new CounterMemberBean();
				counterbeanobject=new CounterBean();
								
				beanobject  = ss.getCounterBook(request.getParameter("accno").toString().trim(),request.getParameter("doc").toString().trim(),request.getParameter("mcode").toString().trim());
				String acc_no=beanobject.getAccno();
				
				String Mem_code=request.getParameter("mcode").toString().trim();
				String doc_type=beanobject.getDoc().toString().trim();				
				
				
				if (acc_no!=""){
					if(beanobject.getAvail().equals("ISSUED")){									
						
							int chk_code=ss.getIssueCheck(request.getParameter("accno").toString().trim(),request.getParameter("mcode").toString().trim());
							
							if(chk_code<=0)
							{
								
								if(Mem_code!=null && !Mem_code.isEmpty())
								{
								 
								 counterbeanobject =	ss.getMemberLoad(request.getParameter("mcode").toString().trim(),doc_type, availability);
								
								 ArrayList issue_details= (ArrayList) ss.getIssueDetailsMember(request.getParameter("mcode").toString().trim());							
								 counterbeanobject.setCounterList(issue_details);										
								 
								 request.setAttribute("bean",counterbeanobject);										
								 
								 //indexPage = "/Bulk_Counter/index.jsp?flag=book";
								 indexPage = "/Bulk_Counter/index.jsp?flag=noissue&detils=ISSUEDEATILS";
								 
								 beanobject.setAccno("");   // This document is issued to other people by RK on 14-08-2014. (Without this line you can reserve the book)
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
											 indexPage="/Bulk_Counter/index.jsp?Message=MemberLock";
											 beanobject.setAccno(""); 
											 request.setAttribute("SLock",counterbeanobject.getMcode().trim());					 
										 }else  {
											 indexPage ="/Bulk_Counter/index.jsp?flag=member&detils=ISSUEDEATILS&load=loadbook";
										 }									    
									}
									else
									{										
										indexPage = "/Bulk_Counter/index.jsp?flag=book";
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
										 indexPage="/Bulk_Counter/index.jsp?Message=MemberLock";
										 beanobject.setAccno(""); 
										 request.setAttribute("SLock",counterbeanobject.getMcode().trim());					 
									 }else  {
										 indexPage ="/Bulk_Counter/index.jsp?flag=member&detils=ISSUEDEATILS&load=loadbook";
									 }									    
								}
								else
								{										
									indexPage = "/Bulk_Counter/index.jsp?flag=book";
								}									
							}																				
					}
					
					else
					{
						if ((request.getParameter("mcode").equals(""))&& (counterbeanobject.getAvail().equals("YES"))) {
							//indexPage ="/Bulk_Counter/index.jsp?load=loadbook";
							indexPage ="/Bulk_Counter/index.jsp?Message=MemberNotFound";
							beanobject.setAccno("");
						} 
						else if(request.getParameter("mcode").equals("")){
							//indexPage = "/Bulk_Counter/index.jsp?flag=book";
							indexPage ="/Bulk_Counter/index.jsp?Message=MemberNotFound";
							beanobject.setAccno("");
						}
						
						else { // Checked rk.							
							
							counterbeanobject =	ss.getMemberLoad(request.getParameter("mcode").toString().trim(),doc_type, availability);							
							
							ArrayList issue_details= (ArrayList) ss.getIssueDetailsMember(request.getParameter("mcode").toString().trim());							
							counterbeanobject.setCounterList(issue_details);	
							
							request.setAttribute("bean",counterbeanobject);								
							indexPage ="/Bulk_Counter/index.jsp?flag=member&load=loadbook&detils=ISSUEDEATILS";							
						}
					}
				}	
				else{						
						
						if(Mem_code!=null && !Mem_code.isEmpty())
						{
							//int issue_details1=1;
							
							counterbeanobject =	ss.getMemberLoad(request.getParameter("mcode").toString().trim(),doc_type, availability);
							int issue_details1=counterbeanobject.getCountperiod();
							
							if(counterbeanobject.getMcode()!="" && counterbeanobject.getMcode()!=null) {
								
							     ArrayList Issue_Dtls= (ArrayList) ss.getIssueDetailsMember(request.getParameter("mcode").toString().trim());
							     counterbeanobject.setCounterList(Issue_Dtls);
							}
							request.setAttribute("bean",counterbeanobject);
							
							if(issue_details1>0)
							{
								indexPage="/Bulk_Counter/index.jsp?flag=member&Message=BookNotbook1&detils=ISSUEDEATILS";
							}
							else
							{
								indexPage="/Bulk_Counter/index.jsp?Message=BookNotbook1";
							}
							
						}
						else
						{										
							   indexPage="/Bulk_Counter/index.jsp?Message=BookNotbook"; 
						}
					
				}		
				request.setAttribute("beanmember",beanobject);				
				
				
				
				String[] baccno=null,btitle=null,bauthor=null,bpublisher=null,bdocument=null,bavailability=null,bissdate=null,bduedate=null,blfine=null;
				
				baccno=request.getParameterValues("baccno[]");				
				btitle=request.getParameterValues("btitle[]");	
				bauthor=request.getParameterValues("bauthor[]");
				bpublisher=request.getParameterValues("bpublisher[]");
				bavailability=request.getParameterValues("bavailability[]");
				bdocument=request.getParameterValues("bdocument[]");
				
				bissdate = request.getParameterValues("bissdate[]");
				bduedate=request.getParameterValues("bduedate[]");
				blfine = request.getParameterValues("blfine[]");
				
				int len= 0;
				if(baccno!=null)
				len = baccno.length;				
				
				List<Object> saveDetail = new ArrayList<Object>();
				 
				 for (int i=0; i<len; i++) {
					 
					listBulk = new CounterList();
					
					listBulk.setAccessNo(baccno[i]);
					listBulk.setTitle(btitle[i]);
					listBulk.setAuthorName(bauthor[i]);
					listBulk.setDocument(bdocument[i]);
					listBulk.setPublisher(bpublisher[i]);
					listBulk.setAvailability(bavailability[i]);
					
					listBulk.setIssueDate(bissdate[i]);
					listBulk.setDueDate(bduedate[i]);
					listBulk.setTemp(Double.parseDouble(blfine[i]));
					
					saveDetail.add(listBulk);				 
				 }
				
				 
				if(beanobject.getAccno()!=null && !beanobject.getAccno().isEmpty())
				{					
				 listBulk = new CounterList();
				
				 if(len>0)
				 {
					 int count =0 ;
					 for (int i=0; i<len; i++) {	
						 
						 if(baccno[i].equalsIgnoreCase(beanobject.getAccno()) && bdocument[i].equalsIgnoreCase(beanobject.getDoc()))
						 {
							 count++;						 
							 break;					 
						 }	 
					 }				 
					 
					 if(count == 0)
					 {						 
						 listBulk.setAccessNo(beanobject.getAccno());
						 listBulk.setTitle(beanobject.getTitle());
						 listBulk.setAuthorName(beanobject.getAuthor());
						 listBulk.setDocument(beanobject.getDoc());
						 listBulk.setPublisher(beanobject.getPublisher());
						 listBulk.setAvailability(beanobject.getAvail());		
						 listBulk.setTemp(counterbeanobject.getTemp());

						 if(counterbeanobject.getCallissdate()!=null && !counterbeanobject.getCallissdate().isEmpty())  // For Issue Date
						 {
						   listBulk.setIssueDate(counterbeanobject.getCallissdate());  // For Existing Issue Date
						   listBulk.setDueDate(counterbeanobject.getCallduedate());    // For Existing Due Date
						 }else  {
						   listBulk.setIssueDate(Security_Counter.TodayDate_view());   // For Issue Date as Today Date
						   listBulk.setDueDate(counterbeanobject.getCalldate());       // For New Due date
						 }
						 
						 saveDetail.add(listBulk);					 
					 }				 
				 }
				 else{
					 
					 listBulk.setAccessNo(beanobject.getAccno());
					 listBulk.setTitle(beanobject.getTitle());
					 listBulk.setAuthorName(beanobject.getAuthor());
					 listBulk.setDocument(beanobject.getDoc());
					 listBulk.setPublisher(beanobject.getPublisher());
					 listBulk.setAvailability(beanobject.getAvail());		
					 listBulk.setTemp(counterbeanobject.getTemp());

					 if(counterbeanobject.getCallissdate()!=null && !counterbeanobject.getCallissdate().isEmpty())  // For Issue Date
					 {
					   listBulk.setIssueDate(counterbeanobject.getCallissdate());  // For Existing Issue Date
					   listBulk.setDueDate(counterbeanobject.getCallduedate());    // For Existing Due Date
					 }else  {
					   listBulk.setIssueDate(Security_Counter.TodayDate_view());   // For Issue Date as Today Date
					   listBulk.setDueDate(counterbeanobject.getCalldate());       // For New Due date
					 }
					 
					 saveDetail.add(listBulk);
				 }		 
				 
				 //log4jLogger.info("+++++++++++++++AccNo:"+beanobject.getAccno()+" IssueDate:"+counterbeanobject.getCallissdate()+" Issue DueDate:"+counterbeanobject.getCallduedate()+" NEW DueDate:"+counterbeanobject.getCalldate());
				 							 
				}
				
				request.setAttribute("BulkCounterList", saveDetail);
				//if(saveDetail!=null)
				//session.setAttribute("BulkCounterSize", saveDetail.size());					
				
				dispatch(request, response, indexPage);
			}
		

			// BOOK ISSUE-----------------------------------------------------------------------------------//
						
			if (flag.equals("issue")) {
				log4jLogger.info("book issue Bulk_Counter===========flag====="+flag);				

				String[] baccno = null,btitle=null,bdocument=null,bissdate=null,bduedate=null;
				
				baccno = request.getParameterValues("baccno[]");				
				btitle = request.getParameterValues("btitle[]");	
				bdocument = request.getParameterValues("bdocument[]");
				
				bissdate = request.getParameterValues("bissdate[]");	
				bduedate = request.getParameterValues("bduedate[]");						
				
				String commaSeparated=request.getParameter("flagAccno");					
				String [] sAccno = commaSeparated.split(",");
				
				String commaSeparated1=request.getParameter("flagDoc");					
				String [] sDocument = commaSeparated1.split(",");
				
				
				int len = baccno.length;
				//int sumEligiple = 0,sumIssued = 0;
			    
				
				for (int j=1; j<sAccno.length; j++)  {
				 
				for (int i=0; i<len; i++) {		
					
					if(baccno[i].equals(sAccno[j])) {	
						
				
				valid=Security_Counter.TextDate_Insert(request.getParameter("validDate"));
				String date_valid =ss.getValidDate(Security_Counter.TextDate_Insert(request.getParameter("validDate")));
				
				counterbeanobject = new CounterBean();
				 
				if(date_valid!="") {
					no_days = date_valid;					
				}
				
				String memb_code =ss.getMemberCode(request.getParameter("mcode"));			
								
			       if(memb_code!=""){
			    	   iss=true;
			       }
			       else
			       {
                       indexPage="/Bulk_Counter/index.jsp?Message=validMem";
			    	   iss=false; 
			       }
			       
				int s=Integer.parseInt(no_days);		
		 
				if (s < 0) {					
					indexPage="/Bulk_Counter/index.jsp?Message=valid";
				}
				else if(iss)
				{
					beanobject=new CounterMemberBean();
					beanobject =ss.getIssueMasCheck(baccno[i].toString().trim(),bdocument[i].toString().trim());				
					
			       if(beanobject.getAvail()!=""){
			    	  availability=beanobject.getAvail();
			    	  document=beanobject.getDoc();
			       }			          			          
			       else
			       {
			    	   indexPage="/Bulk_Counter/index.jsp?Message=booknot";
			       }
			       if(availability.equals("ISSUED") || (!bdocument[i].equals(document)))
			       {			    	   
			    	   indexPage="/Bulk_Counter/index.jsp?Message=alreadyissued";
			       }
			       
				else
				{									
				
				   String	reserve_check =ss.getReserveMasCheck(baccno[i]);
			       if(reserve_check!=""){			    	  
				    	 min=reserve_check;			    				    	  
	               }
			        beanobject=new CounterMemberBean();
			        beanobject.setMcode(request.getParameter("mcode"));
					beanobject.setAccno(baccno[i]);
					beanobject.setIdate(Security.TextDate_member(bissdate[i]));
					beanobject.setDdate(Security.TextDate_member(bduedate[i]));
					beanobject.setTitle(btitle[i]);
					beanobject.setAuthor(String.valueOf(session.getAttribute("UserID")));
					beanobject.setDoc(bdocument[i]);
					beanobject.setMname(request.getParameter("mname")); 
			      
			        resbean=new ReserveBean();
			        resbean =ss.getReserveMssSelect(baccno[i]);
			       
					if(resbean.getMcode()!=""){
					temp1=resbean.getMcode();
					pri=resbean.getRcode();
					temp2=resbean.getMname();
					
					if((temp1.equalsIgnoreCase(request.getParameter("mcode")) && (pri.equals(min))))   // Ignore case added by RK
					{												
						beanobject.setMcode(request.getParameter("mcode"));
						beanobject.setAccno(baccno[i]);
						int res_delete =ss.getReserveMssDelete(beanobject);	
						
												
						int doc_issue =ss.getIssueMasInsert(beanobject);
		
						if (doc_issue>0){
							
								indexPage="/Bulk_Counter/index.jsp?Message=issued&detils=ISSUEDEATILS";
							}																		
				}
				
				else
				{		
							request.setAttribute("temp1",temp1);
							request.setAttribute("temp2",temp2);
							int issue_details =ss.getIssuedDetails(beanobject);
							
							indexPage="/Bulk_Counter/index.jsp?Message=issue_error&detils=ISSUEDEATILS";			
				}
			}
			else
			{
								
				int doc_issue =ss.getIssueMasInsert(beanobject);			
				
					if (doc_issue>0) {			
						indexPage="/Bulk_Counter/index.jsp?Message=issued&detils=ISSUEDEATILS&avoid=error";		
					}		
						  
			}			
				}
			   	       
				}	
				
                counterbeanobject = ss.getCounterMember(request.getParameter("mcode").toString().trim());                			
				ArrayList issue_details=	(ArrayList) ss.getIssueDetailsMember(request.getParameter("mcode").toString().trim());
				counterbeanobject.setCounterList(issue_details);				

				request.setAttribute("bean",counterbeanobject);	
				
				/**
			    sumEligiple = counterbeanobject.getGeligible() +
			                      counterbeanobject.getBeligible() +
			                      counterbeanobject.getBbeligible() +
			                      counterbeanobject.getNbeligible() +
			                      counterbeanobject.getJeligible() +
			                      counterbeanobject.getBveligible() +
			                      counterbeanobject.getTeligible() +
			                      counterbeanobject.getSeligible() +
			                      counterbeanobject.getPeligible() +
			                      counterbeanobject.getReligible();
			    
			    sumIssued = counterbeanobject.getGissued() +
                                counterbeanobject.getBissued() +
                                counterbeanobject.getBbissued() +
                                counterbeanobject.getNbissued() +
                                counterbeanobject.getJissued() +
                                counterbeanobject.getBvissued() +
                                counterbeanobject.getTissued() +
                                counterbeanobject.getSissued() +
                                counterbeanobject.getPissued() +
                                counterbeanobject.getRissued();		
			    
			    if (sumEligiple == sumIssued && sumEligiple != 0)
			    {
			    	log4jLogger.info("----For Loop1 ****** MAX RESOURCE ISSUED ******-------"+sumEligiple+" and "+sumIssued);
			    	break;
			    }*/
			    
			                      
					
					}else
					 {
						 log4jLogger.info("----Unselected value is-------"+baccno[i]);
					 }
				}
				
				/**if (sumEligiple == sumIssued && sumEligiple != 0)
			    {
					log4jLogger.info("----For Loop2 ****** MAX RESOURCE ISSUED ******-------"+sumEligiple+" and "+sumIssued);
			    	break;
			    }*/
			  }
				dispatch(request, response, indexPage);
					
			}

			//--------------------------------------RETURN------------------------------------------------------------*/

			if (flag.equals("return")) {
				log4jLogger.info("book return===========flag====="+flag);
				
				String[] baccno = null,btitle=null,bdocument=null,bissdate=null,bduedate=null;
				String breturndate = null;
				
				baccno = request.getParameterValues("baccno[]");				
				btitle = request.getParameterValues("btitle[]");	
				bdocument = request.getParameterValues("bdocument[]");
				
				bissdate = request.getParameterValues("bissdate[]");
				bduedate = request.getParameterValues("bduedate[]");				
				breturndate = Security_Counter.TodayDate_view();				
				
				String commaSeparated=request.getParameter("flagAccno");					
				String [] sAccno = commaSeparated.split(",");
				
				String commaSeparated1=request.getParameter("flagDoc");					
				String [] sDocument = commaSeparated1.split(",");
				
				
				int len = baccno.length;
				//int sumEligiple = 0,sumIssued = 0;
			    
				
				for (int j=1; j<sAccno.length; j++)  {
				 
				for (int i=0; i<len; i++) {		
					
					if(baccno[i].equals(sAccno[j])) {	
				 
				    counterbeanobject=new CounterBean();
				
				    beanobject=new CounterMemberBean();
				    beanobject.setMcode(request.getParameter("mcode"));
					beanobject.setAccno(baccno[i]);
					beanobject.setIdate(Security_Counter.TextDate_Insert(bissdate[i]));
					beanobject.setDdate(Security_Counter.TextDate_Insert(bduedate[i]));
					beanobject.setRdate(Security_Counter.TextDate_Insert(breturndate));
					beanobject.setTitle(btitle[i]);
					beanobject.setAuthor(String.valueOf(session.getAttribute("UserID")));
					beanobject.setDoc(bdocument[i]);
					beanobject.setMname(request.getParameter("mname"));
					
					counterbeanobject=ss.getDocmentReturn(beanobject);
				   
					int doc_return=counterbeanobject.getDoc_Return();
					
			
				if (doc_return>0) {
					
							Double Temp = counterbeanobject.getTemp();
							if (Temp.doubleValue() == 0.0) {
								
								indexPage="/Bulk_Counter/index.jsp?Message=return&detils=ISSUEDEATILS";
							} else {
								
								session.setAttribute("FINE", Temp);								
								
								beanobject=new CounterMemberBean();
							    beanobject.setMcode(request.getParameter("mcode"));
								beanobject.setAccno(baccno[i]);
								beanobject.setIdate(Security_Counter.TextDate_Insert(bissdate[i]));
								beanobject.setDdate(Security_Counter.TextDate_Insert(bduedate[i]));
								beanobject.setRdate(Security_Counter.TextDate_Insert(breturndate));
								beanobject.setTitle(btitle[i]);
								beanobject.setAuthor(String.valueOf(session.getAttribute("UserID")));
								beanobject.setDoc(bdocument[i]);
								beanobject.setMname(request.getParameter("mname"));
								beanobject.setTemp(counterbeanobject.getTemp());													

//								 For Add Payment to the user 
								if(request.getParameter("payflag").equalsIgnoreCase("YES"))
								{
									int fineDetails = ss.getFineDetail(beanobject);									
								}					
								
								beanobject1=new CounterFineBean();
								
							    beanobject1.setMcode(request.getParameter("mcode"));
								beanobject1.setAccno(baccno[i]);
								beanobject1.setIdate(Security_Counter.TextDate_Insert(bissdate[i]));
								beanobject1.setDdate(Security_Counter.TextDate_Insert(bduedate[i]));
								beanobject1.setRdate(Security_Counter.TextDate_Insert(breturndate));
								beanobject1.setTitle(btitle[i]);
								beanobject1.setAuthor(String.valueOf(session.getAttribute("UserID")));
								beanobject1.setDoc(bdocument[i]);
								beanobject1.setMname(request.getParameter("mname"));
								beanobject1.setTemp(counterbeanobject.getTemp());
								
								
								request.setAttribute("beanobject",beanobject1);
								
								int doc_fine =ss.getDocmentFine(beanobject);
								
								
								//indexPage="/Bulk_Counter/index.jsp?flag=freturn&detils=ISSUEDEATILS";
								indexPage="/Bulk_Counter/index.jsp?Message=return&detils=ISSUEDEATILS";
							}
				}
				else
				{
		 						indexPage="/Bulk_Counter/index.jsp?flag=noissue&detils=ISSUEDEATILS";
				}
				
				counterbeanobject = ss.getCounterMember(request.getParameter("mcode").toString().trim());
				
				ArrayList issue_details=	(ArrayList) ss.getIssueDetailsMember(request.getParameter("mcode").toString().trim());
				counterbeanobject.setCounterList(issue_details);
				
				request.setAttribute("bean",counterbeanobject);
						

					}else
					 {
						 log4jLogger.info("----Unselected value is-------"+baccno[i]);
					 }
				}				
				
			  }
				
				dispatch(request, response, indexPage);

			}

			
            //			--------------------------  Payment Clearance ----------------------------------------------------//
			
			
			if (flag.equals("savefine_payment")) {				
				log4jLogger.info("Payment ===========flag====="+flag);
				
				counterbeanobject=new CounterBean();				
				beanobject=new CounterMemberBean();				
				beanobject1=new CounterFineBean();	
				
				beanobject1.setMcode(request.getParameter("mcode"));
				beanobject1.setAccno(request.getParameter("accno"));				
				beanobject1.setRdate(request.getParameter("rdate"));
				beanobject1.setDoc(request.getParameter("doc"));
				beanobject1.setTemp(Double.parseDouble(request.getParameter("fine")));
												
			    beanobject.setMcode(beanobject1.getMcode());	
			   	beanobject.setAccno(beanobject1.getAccno());
				beanobject.setRdate(beanobject1.getRdate());
				beanobject.setDoc(beanobject1.getDoc());
				beanobject.setTemp(beanobject1.getTemp());	
				beanobject.setAuthor(String.valueOf(session.getAttribute("UserID")));
				
				int issue_details1=ss.getFineDetail(beanobject);
				
				if (issue_details1>0) {		
					
					counterbeanobject = ss.getCounterMember(beanobject.getMcode().toString().trim());					
					ArrayList issue_details=	(ArrayList) ss.getIssueDetailsMember(request.getParameter("mcode").toString().trim());
					counterbeanobject.setCounterList(issue_details);	
					request.setAttribute("bean",counterbeanobject);	
					
					indexPage="/Bulk_Counter/index.jsp?flag=rkfine&detils=ISSUEDEATILS";
				}
				else
				{				
					indexPage="/Bulk_Counter/index.jsp?";
					//return;
				}           
				
				dispatch(request, response, indexPage);				
			}	
			

			//--------------------------RENEW BOOK ----------------------------------------------------//		

			if (flag.equals("renew")) {
				
				log4jLogger.info("book renew===========flag====="+flag);	
				
				String[] baccno = null,btitle=null,bdocument=null,bissdate=null,bduedate=null;
				String breturndate = null;
				
				baccno = request.getParameterValues("baccno[]");				
				btitle = request.getParameterValues("btitle[]");	
				bdocument = request.getParameterValues("bdocument[]");
				
				bissdate = request.getParameterValues("bissdate[]");
				bduedate = request.getParameterValues("bduedate[]");				
				breturndate = Security_Counter.TodayDate_view();				
				
				String commaSeparated=request.getParameter("flagAccno");					
				String [] sAccno = commaSeparated.split(",");
				
				String commaSeparated1=request.getParameter("flagDoc");					
				String [] sDocument = commaSeparated1.split(",");
				
				
				int len = baccno.length;
				//int sumEligiple = 0,sumIssued = 0;
			    
				
				for (int j=1; j<sAccno.length; j++)  {
				 
				for (int i=0; i<len; i++) {		
					
				  if(baccno[i].equals(sAccno[j])) {	
						
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
				beanobject.setAccno(baccno[i]);		
				int renewcheck=ss.getRenewCheck(beanobject);				
				
				if(renewcheck==0){
					
					counterbeanobject = ss.getCounterMember(request.getParameter("mcode").toString().trim());					
					ArrayList issue_details=	(ArrayList) ss.getIssueDetailsMember(request.getParameter("mcode").toString().trim());
					counterbeanobject.setCounterList(issue_details);				
					request.setAttribute("bean",counterbeanobject);

					indexPage="/Bulk_Counter/index.jsp?flag=noissue&detils=ISSUEDEATILS";					
				}
                else if(renewcheck==2){
					
					counterbeanobject = ss.getCounterMember(request.getParameter("mcode").toString().trim());					
					ArrayList issue_details=	(ArrayList) ss.getIssueDetailsMember(request.getParameter("mcode").toString().trim());
					counterbeanobject.setCounterList(issue_details);				
					request.setAttribute("bean",counterbeanobject);

					indexPage="/Bulk_Counter/index.jsp?flag=reservedbook&detils=ISSUEDEATILS";					
				}
				else {
				
				beanobject=ss.getIssueMas(baccno[i]);				
				
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

				
				beanobject.setRdate(breturndate);
				beanobject.setDdate(bduedate[i]);
				beanobject.setMcode(request.getParameter("mcode"));
				beanobject.setDoc(bdocument[i]);
												
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
						beanobject.setDoc(bdocument[i]);
						beanobject.setTperiod(Groups);
						beanobject.setAccno(baccno[i]);
						
						beanobject.setValidDate(Security_Counter.TextDate_Insert(bduedate[i].toString()));
						
						beanobject =ss.getUpdateRenewMasNofine(beanobject);
						
						 
						// Changed by RK
						
						if (beanobject.getSperiod()>0){															
						
							beanobject.setRdate(Rdate);
							beanobject.setDdate(Ddate);
							beanobject.setMcode(request.getParameter("mcode"));
							beanobject.setDoc(bdocument[i]);
							beanobject.setTperiod(Groups);
							beanobject.setAccno(baccno[i]);
							beanobject.setTeligible(beanobject.getTeligible());
							beanobject.setIdate(bissdate[i]);
							beanobject.setTitle(String.valueOf(session.getAttribute("UserID")));
							
							beanobject.setValidDate(Security_Counter.TextDate_Insert(bduedate[i].toString()));
							
							int renew =ss.getUpdateRenewMas(beanobject);
						
							indexPage="/Bulk_Counter/index.jsp?Message=renew&detils=ISSUEDEATILS";
						}
						else
						{
							indexPage="/Bulk_Counter/index.jsp?Message=nrenew&mcode=membercode";
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
				
					beanobject.setRdate(Rdate);
					beanobject.setDdate(Ddate);
					beanobject.setMcode(request.getParameter("mcode"));
					beanobject.setDoc(bdocument[i]);
					beanobject.setTperiod(Groups);
					beanobject.setAccno(baccno[i]);
					
					beanobject.setValidDate(Security_Counter.TextDate_Insert(bduedate[i].toString()));
					
					beanobject =ss.getUpdateRenewMasNofine(beanobject);
					
					// Changed by RK
					
					 if (beanobject.getSperiod()>0){
						 
						    beanobject.setRdate(Rdate);
							beanobject.setDdate(Ddate);
							beanobject.setMcode(request.getParameter("mcode"));
							beanobject.setDoc(bdocument[i]);
							beanobject.setTperiod(Groups);
							beanobject.setAccno(baccno[i]);
							beanobject.setTeligible(beanobject.getTeligible());
							beanobject.setIdate(bissdate[i]);
							beanobject.setTitle(String.valueOf(session.getAttribute("UserID")));   // For issue history and trans master
							beanobject.setAuthor(String.valueOf(session.getAttribute("UserID"))); // For Payment clearance table
							beanobject.setTemp(Temp);
							
							beanobject.setValidDate(Security_Counter.TextDate_Insert(bduedate[i].toString()));
							
							int renew =ss.getUpdateRenewMasFine(beanobject);
							
//							 For Add Payment to the user 
							if(request.getParameter("payflag").equalsIgnoreCase("YES"))
							{
								int fineDetails = ss.getFineDetail(beanobject);									
							}
							
							//indexPage="/Bulk_Counter/index.jsp?flag=fwreturn&detils=ISSUEDEATILS";	
							indexPage="/Bulk_Counter/index.jsp?Message=renew&detils=ISSUEDEATILS";
					 }
					 else
					 {						
						 indexPage="/Bulk_Counter/index.jsp?Message=nrenew&mcode=membercode";
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
					indexPage="/Bulk_Counter/index.jsp?Message=MemberNotFound";

				}	
					
				
				 
				  
				  }else
					 {
						 log4jLogger.info("----RENEW Unselected value is-------"+baccno[i]);
					 }
				}				
				
			  }
				
				 dispatch(request, response, indexPage);
				
			}
			//-----------------------------------------------------------------END TRY CATCH----------------//
		} catch (Exception e) {
			throw new ServletException(e);
		} finally {
			try{
				

			}catch(Exception e){
			e.printStackTrace();
			}

		

		}
                System.out.println("+++++++++++++++++++++++"+indexPage);
	}

	
	
	public void dispatch(HttpServletRequest request,
			HttpServletResponse response, String indexPage)
			throws ServletException, IOException {
		RequestDispatcher dispatch = request.getRequestDispatcher(indexPage);
		dispatch.forward(request, response);
	}
	
/**
	 public static String getClientIpAddr(HttpServletRequest request) { 
	        String ip = request.getHeader("X-FORWARDED-FOR"); 
	        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
	            ip = request.getHeader("Proxy-Client-IP"); 
	        } 
	        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
	            ip = request.getHeader("WL-Proxy-Client-IP"); 
	        } 
	        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
	            ip = request.getHeader("HTTP_CLIENT_IP"); 
	        } 
	        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
	            ip = request.getHeader("HTTP_X_FORWARDED_FOR"); 
	        } 
	        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
	            ip = request.getRemoteAddr(); 
	        } 
	        return ip; 
	    }	
	 
	 String rk = getClientIpAddr(request);
		System.out.print("IPPPPPPPIIIIIIIIIIPPPPPPPPPPPPPPP:"+rk);  */
	
}
