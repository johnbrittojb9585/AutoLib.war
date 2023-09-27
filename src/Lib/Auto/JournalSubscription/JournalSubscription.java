package Lib.Auto.JournalSubscription;

import java.io.IOException;
import java.sql.Date;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.eclipse.birt.core.data.DateUtil;

import com.ibm.icu.text.DateFormat;
import com.ibm.icu.text.SimpleDateFormat;
import com.ibm.icu.util.Calendar;

import Common.Security;
import Common.businessutil.BusinessServiceFactory;
import Common.businessutil.calaloging.CalalogingService;
import Common.businessutil.serialcontrol.SerialcontrolService;
import Lib.Auto.Journal.journalbean;
import Lib.Auto.Journal_Issues.JnlIssBean;
import Lib.Auto.PubSup.PubSupBean;

import javax.servlet.annotation.WebServlet;
import com.google.gson.Gson;

@WebServlet("/journalSubscription/JournalSubscriptionServlet")


public class JournalSubscription extends HttpServlet {
	  private static Logger log4jLogger = Logger.getLogger(JournalSubscription.class);
	
	  
	private static final long serialVersionUID = 1L;
	
	String flag;
	
	
	
	
	JournalSubscriptionbean ob=new JournalSubscriptionbean();
	JournalSubscriptionbean newbean=new JournalSubscriptionbean();
	String indexPage = null;
	
	
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		performTask(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		performTask(request, response);
	}

	public void performTask(HttpServletRequest request,HttpServletResponse response){

		try {
			HttpSession session = request.getSession(true);
			String res = Security.checkSecurity(1, session, response, request);		
			if(res.equalsIgnoreCase("Failure"))
			{
				response.sendRedirect("/AutoLib/Tree/sessiontimeout.jsp");  
				return;
			}

					response.setContentType("text/html");
            		SerialcontrolService ss = BusinessServiceFactory.INSTANCE.getSerialcontrolService();
            		CalalogingService ss1 = BusinessServiceFactory.INSTANCE.getCalalogingService();
            		
            		response.setContentType("application/json");
            		
            		try{
        				String term = request.getParameter("journalName");
        				
        				if(!term.equalsIgnoreCase(null) && !term.equalsIgnoreCase(""))
        	             {
        				//System.out.println("Data from ajax call " + term);
        							    
        				   ArrayList<journalbean> list = ss1.getJournalAutoNameSearch(term);
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
            		
            		try{
        				String term = request.getParameter("Supplier");
        				
        				if(!term.equalsIgnoreCase(null) && !term.equalsIgnoreCase(""))
        	             {
        				//System.out.println("Data from ajax call " + term);
        							    
        				   ArrayList<PubSupBean> list = ss1.getStatSupAutoSearch(term);
        			       for(PubSupBean user: list){
        					//System.out.println(user.getName());
        				}       

        				String searchList = new Gson().toJson(list);
        							
        				response.getWriter().write(searchList);  
        							
        				//System.out.println("Json Value@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+searchList);
        	             }	
        		}catch(Exception e){
        			//e.printStackTrace();
        		}    		 
            		

            		
            		flag=request.getParameter("flag");
            		String nm=request.getParameter("name");
            		log4jLogger.info("Flag Values===========flag===== "+flag);
            	
            																								  
            		if(flag.equals("Sup") || flag.equals("jnlName") || flag.equals("budName") || flag.equals("subsNo")){
            			log4jLogger.info("---------------------Inside Search--------------------flag value  "+flag);
            			List AuthorArrylist = new ArrayList();
            			
            			newbean=new JournalSubscriptionbean();
            			
            			newbean.setFlag(flag);
            			newbean.setSearchValue(request.getParameter("name"));
            			
            			ob=ss.getJnlSearchName(newbean);
            			request.setAttribute("bean", ob);
        				indexPage = "/journalSubscription/search.jsp?check=ok&flag="+flag+"&nam="+nm+"";
        					
        			    	dispatch(request, response, indexPage);
            			
            		}
            		if(flag.equals("load")){
            			log4jLogger.info("-----------load flag----------------");
            			
            			List AuthorArrylist = new ArrayList();
        				AuthorArrylist=ss1.getCurrencyLoad();
        				request.setAttribute("currency", AuthorArrylist);
        				request.setAttribute("bean", ob);	
        				
        				ob = new JournalSubscriptionbean();
            			ob=ss.getJnlSubscriptionMax(0);
            			
            			
            			
            			
            			indexPage = "/journalSubscription/index.jsp?check=load";
        				dispatch(request, response, indexPage);
            		}
            		if(flag.equals("new")){
            		
            			log4jLogger.info("new===========flag====="+flag);
            			ob = new JournalSubscriptionbean();
            			ob=ss.getJnlSubscriptionMax(0);
            			request.setAttribute("bean", ob);	
            			
            			List AuthorArrylist = new ArrayList();
        				AuthorArrylist=ss1.getCurrencyLoad();
        				request.setAttribute("currency", AuthorArrylist);
        				indexPage = "/journalSubscription/index.jsp?check=newJournal";
            			dispatch(request, response, indexPage);
            		}

            		
            		
            		if(flag.equals("createIssues")){
            			ob = new JournalSubscriptionbean();
            		
            			int ncopy=0;
            			String IssAccNo=null;
            			log4jLogger.info("create Issues====="+flag);
            			 
            			ob.setSubsNo(request.getParameter("subsNo"));
            			ob.setSubsFrom(Security.TextDate_member(request.getParameter("subsFrom")));
        				ob.setSubsTo(Security.TextDate_member(request.getParameter("subsTo")));
        				ob.setStartingAccNo(request.getParameter("startingAccNo"));
        				
        				log4jLogger.info("From Date:::::::::::::::::"+ob.getSubsFrom());
        				log4jLogger.info("To Date:::::::::::::::::"+ob.getSubsTo());
        				
        				
            			
            			int result=ss.getjnlSubscriptionDateSearch(ob);
            			
            			log4jLogger.info("::::::::::::"+result);
            			
            			if(result==0){
            				ob.setSubsNo(request.getParameter("subsNo"));
                			ob.setSubsFrom(Security.TextDate_member(request.getParameter("subsFrom")));
            				ob.setSubsTo(Security.TextDate_member(request.getParameter("subsTo")));
            				
            				log4jLogger.info(":::::::::::::::::::Journal is available:::::::::::::::::::::::");
            				
            				ob.setSubsNo(request.getParameter("subsNo"));
            				ob.setSubsFrom(Security.TextDate_member(request.getParameter("subsFrom")));
            				
            			          			
            					//ob=ss.getJnlSubscriptionMax();
            					IssAccNo=ob.getStartingAccNo();
            					
            					ob.setJnlcode(Integer.parseInt(request.getParameter("jnlCode")));
            					ob.setSubsNo(request.getParameter("subsNo"));
            					ob.setSubsFrom(Security.TextDate_member(request.getParameter("subsFrom")));
            					ob.setNoOfIssues(Integer.parseInt(request.getParameter("NoOfIssues")));
            					ob.setFrequency(request.getParameter("frequency"));
            					ob.setIssueVolume(request.getParameter("volumeNo"));

            					ob.setIssueNo(request.getParameter(request.getParameter("issueNo")));
            					ob.setNetPrice(request.getParameter("acceptPrice"));
            					ncopy=ob.getNoOfIssues();
            			
            			log4jLogger.info("noOfcopies"+ncopy);
            			log4jLogger.info("Starting AccessNo "+IssAccNo);
            			
            			if (ncopy > 0) {
            					log4jLogger.info("inside save");
            					
            					
            					JnlIssBean newbean=new JnlIssBean();
            					
            					newbean.setIss_acc_no(IssAccNo);
            					String Nums = "";
            					String Chars = "";
            					String bookaccno = "";
            					int issueNo=0;
            					String issueDate="";
            					issueDate=ob.getSubsFrom();
            					log4jLogger.info("::::::::::::Starting IssueDate:::::::::::::"+issueDate);
            					issueDate = ss.getjnlfrequencyFirstTime(ob.getFrequency(),issueDate);
            					String s = "";
            					String zero = "";
            					boolean temp = true;
            					boolean isNumFound = false;
            					if (Character.isDigit(IssAccNo.charAt(0)))
            						isNumFound = true;
            					for (int l = 0; l < IssAccNo.length(); l++) {
            						if (Character.isDigit(IssAccNo.charAt(l))) {
            							char t = IssAccNo.charAt(l);
            							if (t != '0' && temp == true) {
            								s = s + String.valueOf(t);
            								int pos = IssAccNo.indexOf(s);
            								s = IssAccNo.substring(pos, IssAccNo.length());
            								temp = false;
            							}
            							Nums = Nums + IssAccNo.charAt(l);
            						} 
            						else {
            							Chars = Chars + IssAccNo.charAt(l);
            						}
            					}
            					String zeros = "";
            						for (int n = 0; n < Nums.length(); n++) {
            						if (String.valueOf(Nums.charAt(n)).equals("0"))
            							zeros = zeros + Nums.charAt(n);
            						else {
            							s = Nums.substring(n, Nums.length());
            							break;
            						}
            					}
            					long Numm = Long.parseLong(Nums.substring(zeros.length(), Nums.length()));

            					for (int v = 0; v < ncopy; v++) {
            						if (isNumFound) {
            							if (count(s, v)) {
            								if (zeros.equals("")) {
            								} else {
            									zero = zeros.substring(0, zeros.length() - 1);
            								}
            								bookaccno = Chars + zero + (Numm + v);
            								issueNo=issueNo+1;
            								issueDate = ss.getjnlSubsCalc(ob.getFrequency(),issueDate);
            							} 
            							else {
            								bookaccno = zeros + (Numm + v) + Chars;
            								issueNo=issueNo+1;
            								issueDate = ss.getjnlSubsCalc(ob.getFrequency(),issueDate);
            							}
            						} else {
            							if (count(s, v)) {
            								if (zeros.equals("")) {
            								} else {
            									zero = zeros.substring(0, zeros.length() - 1);
            								}
            								bookaccno = Chars + zero + (Numm + v);
            								issueNo=issueNo+1;
            								issueDate = ss.getjnlSubsCalc(ob.getFrequency(),issueDate);
            							
            							} else {
            								bookaccno = Chars + zeros + (Numm + v);
            								issueNo=issueNo+1;   
            								issueDate = ss.getjnlSubsCalc(ob.getFrequency(),issueDate);
            								}
            						}
            					newbean.setIss_acc_no(bookaccno);
            					newbean.setJnl_code(ob.getJnlcode());
            					newbean.setSub_code(ob.getSubsNo());
            					newbean.setIss_year(issueDate.substring(0, 4));
            					//-------------------------------
            					String month = "December";
            					int m=Integer.parseInt(issueDate.substring(5, 7));
            					DateFormatSymbols dfs = new DateFormatSymbols();
            				    String[] months = dfs.getMonths();
            				    if (m >= 0 && m <= 11 ) {
            				        month = months[m-1];
            				    }
            				    newbean.setIss_month(month);
            				    //------------------------------
            				    newbean.setIss_vol(ob.getIssueVolume());
            					newbean.setIss_no(String.valueOf(issueNo));
            					newbean.setPart_no("");
            					newbean.setIss_date(issueDate);
            					newbean.setExp_date(issueDate);
            					newbean.setRec_date("2012-01-01");
            					newbean.setAvail("PENDING");
            					newbean.setBvol_no("");
            					newbean.setRemarks("");
            					
            					String amount=ob.getNetPrice();
            					float amount1=Float.parseFloat(amount);
            					amount1=amount1/ncopy;//for divide each journal
            					newbean.setIss_amount(amount1);
            					newbean.setPage("");
            					newbean=ss.getJnlIssuesSave(newbean);
            					}
            					int count=ss.getjnlSubsFlagUpdate(ob.getSubsNo());// for update flag pending into completed in journal subscription
            				}
            			
                			indexPage = "/journalSubscription/index.jsp?check=saveIssues";
            				
            			}else if(result==1){
            				indexPage = "/journalSubscription/index.jsp?check=failedIssuesSave";
            			}else{
            				indexPage = "/journalSubscription/index.jsp?check=NoSubscriptionsFound";
            			}
            			
            				List AuthorArrylist = new ArrayList();
            				AuthorArrylist=ss1.getCurrencyLoad();
            				request.setAttribute("currency", AuthorArrylist);
                			dispatch(request, response, indexPage);
            			
            		}
            		if(flag.equals("searchSubNo")){
            			
            			log4jLogger.info("--------------inside search Subsription Number--------------------");
            			ob=new JournalSubscriptionbean();
            			ob.setSubsNo(request.getParameter("subsNo"));
            			log4jLogger.info("------SubsNo--------::::: "+ob.getSubsNo());
            			ob=ss.getjnlSubscriptionSearch(ob);
            			
            			List AuthorArrylist = new ArrayList();
        				AuthorArrylist=ss1.getCurrencyLoad();
        				request.setAttribute("currency", AuthorArrylist);
            			if(ob!=null){
            				
            				request.setAttribute("bean", ob);
            							 
        					indexPage = "/journalSubscription/index.jsp?check=SuccessSubs";
        					
        				}
            			
            			else{
            				indexPage = "/journalSubscription/index.jsp?check=FailureSubs";
            			}
            			
            			dispatch(request, response, indexPage);
            		}
            		if(flag.equals("update")){
            			
            			ob=new JournalSubscriptionbean();
            			ob.setSubsNo(request.getParameter("subsNo"));
            			ob.setStartingAccNo(request.getParameter("startingAccNo"));
            			ob.setJnlcode(Integer.parseInt(request.getParameter("jnlCode")));
            			ob.setSupCode(Integer.parseInt(request.getParameter("supCode")));
            			ob.setSubsFrom(Security.TextDate_member(request.getParameter("subsFrom")));
            			ob.setSubsTo(Security.TextDate_member(request.getParameter("subsTo")));
            			ob.setAvailability(request.getParameter("availability"));
            			ob.setIssueMonth(request.getParameter("month"));
            			ob.setIssueYear(request.getParameter("year"));
            			ob.setIssueVolume(request.getParameter("volumeNo"));
            			ob.setIssueNo(request.getParameter(request.getParameter("issueNo")));
            			ob.setFrequency(request.getParameter("frequency"));
            			ob.setNoOfIssues(Integer.parseInt(request.getParameter("NoOfIssues")));
            			ob.setExceptedDays(7); //for expected in 7 days. its used for reminder
            			ob.setFlag(request.getParameter("availability"));
            			ob.setIssueNo(request.getParameter("issueNo"));
            			ob.setjCost(request.getParameter("bcost"));
            			ob.setjCurrency(request.getParameter("currency"));
            			ob.setjPrice(request.getParameter("bprice"));
            			ob.setjDiscount(request.getParameter("discount"));
            			ob.setNetPrice(request.getParameter("acceptPrice"));
            			ob.setBudgCode(Integer.parseInt(request.getParameter("budgCode")));
            			ob.setBudName(request.getParameter("budgName"));
            			int count=ss.getJnlSubscriptionUpdate(ob);
            			List AuthorArrylist = new ArrayList();
        				AuthorArrylist=ss1.getCurrencyLoad();
        				request.setAttribute("currency", AuthorArrylist);
        				
        			
        				request.setAttribute("bean", ob);
        				
        				
        				indexPage = "/journalSubscription/index.jsp?check=UpdateJournal";
            			dispatch(request, response, indexPage);
            			
            			
            		}
            
            		
            		
            		
            		if(flag.equals("delete")){
            			ob=new JournalSubscriptionbean();
            			
            			ob.setSubsNo(request.getParameter("subsNo"));
            			
            			ob=ss.getjnlSubscriptionDelete(ob);	
            		
            			List AuthorArrylist = new ArrayList();
        				AuthorArrylist=ss1.getCurrencyLoad();
        				request.setAttribute("currency", AuthorArrylist);
            		
        				if(ob.getFlag().equals("0")){
            				indexPage = "/journalSubscription/index.jsp?check=jnlIssueYes";
            			}else{
            				indexPage = "/journalSubscription/index.jsp?check=jnlIssueDeleted";
            			}
            			dispatch(request, response, indexPage);
            		}
            		
            		
            		if(flag.equals("save"))
            		{
            			
            			ob=new JournalSubscriptionbean();
            			
            			
            			ob.setSubsNo(request.getParameter("subsNo"));
            			           			
            			ob=ss.getjnlSubscriptionSearch(ob);	
            			if(ob!=null){
            				request.setAttribute("bean", ob);
            				
            				List AuthorArrylist = new ArrayList();
            				AuthorArrylist=ss1.getCurrencyLoad();
            				request.setAttribute("currency", AuthorArrylist);
            				
            				indexPage = "/journalSubscription/index.jsp?check=Updatename";
            				dispatch(request, response, indexPage);
            				
            			}else{
            			log4jLogger.info("save===========flag====="+flag);
            			ob=new JournalSubscriptionbean();
            			
            			ob.setSubsNo(request.getParameter("subsNo"));
            			ob.setStartingAccNo(request.getParameter("startingAccNo"));
            			ob.setJnlcode(Integer.parseInt(request.getParameter("jnlCode")));
            			ob.setSupCode(Integer.parseInt(request.getParameter("supCode")));
            			ob.setSubsFrom(Security.TextDate_member(request.getParameter("subsFrom")));
            			ob.setSubsTo(Security.TextDate_member(request.getParameter("subsTo")));
            			ob.setAvailability(request.getParameter("availability"));
            			ob.setIssueMonth(request.getParameter("month"));
            			ob.setIssueYear(request.getParameter("year"));
            			ob.setIssueVolume(request.getParameter("volumeNo"));
            			ob.setIssueNo(request.getParameter(request.getParameter("issueNo")));
            			ob.setFrequency(request.getParameter("frequency"));
            			ob.setNoOfIssues(Integer.parseInt(request.getParameter("NoOfIssues")));
            			ob.setExceptedDays(7); //for expected in 7 days. its used for reminder
            			ob.setFlag("PENDING");
            			ob.setIssueNo(request.getParameter("issueNo"));
            			ob.setjCost(request.getParameter("bcost"));
            			ob.setjCurrency(request.getParameter("currency"));
            			ob.setjPrice(request.getParameter("bprice"));
            			ob.setjDiscount(request.getParameter("discount"));
            			ob.setNetPrice(request.getParameter("acceptPrice"));
            			ob.setBudgCode(Integer.parseInt(request.getParameter("budgCode")));
            			ob.setBudName(request.getParameter("budgName"));
            			
            			
            			int count=ss.getJnlSubscriptionSave(ob);
            			
            			List AuthorArrylist = new ArrayList();
        				AuthorArrylist=ss1.getCurrencyLoad();
        				request.setAttribute("currency", AuthorArrylist);
        				
        			
        				request.setAttribute("bean", ob);
        				
        				
        				indexPage = "/journalSubscription/index.jsp?check=saveSubscription";
            			dispatch(request, response, indexPage);
            		}
            		}
						}  catch (Exception e) {

					}
			 finally
			 {
				 try{
					}catch(Exception e){
					e.printStackTrace();
					}
			 }

	}
	
	
	public boolean count(String sa, int as) {
		int len = sa.length();
		int acc = Integer.parseInt(sa);
		int noc = as;
		int tot = acc + noc;
		String total = String.valueOf(tot);
		int len1 = total.length();
		if (len == len1) {
			return false;
		} else {
			return true;
		}
	}
	
	
	
	

	public void dispatch(HttpServletRequest request,
			HttpServletResponse response, String indexPage)
			throws ServletException, IOException {
		RequestDispatcher dispatch = request.getRequestDispatcher(indexPage);
		dispatch.forward(request, response);
	}

}
