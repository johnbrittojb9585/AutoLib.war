package Lib.Auto.Indent_Mas;

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
import Login.Login;

public class IndentMasProcessing extends HttpServlet implements Serializable {
	private static final long serialVersionUID = 1L;

	private static Logger log4jLogger = Logger.getLogger(IndentMasProcessing.class);

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
			
			IndentMasDetailsBean ob=new IndentMasDetailsBean();				
						
			flag = request.getParameter("flag");			
			
			log4jLogger.info("Inside Class Indent Master Processing");
			
			
		
			
			
			if((flag.equals("Pub"))|| (flag.equals("Member")) || flag.equals("Dept") || flag.equals("Author") || flag.equals("Title") || flag.equals("IndentNo"))
			{
				
				log4jLogger.info("IndentMasSearch===========flag====="+flag);
				List OrdJNLArrylist = new ArrayList();
								
				ob=new IndentMasDetailsBean();
				ob.setMemname(request.getParameter("name"));
				ob.setFlag(request.getParameter("flag"));
				
				OrdJNLArrylist=ss.getIndentMasSearch(ob);
				
																						
				request.setAttribute("search", OrdJNLArrylist);
				indexPage ="/Indent_Mas/search_nmvc.jsp?check=ok&flag="+flag+"";
				dispatch(request, response, indexPage);
			}
						
			
			
			
			if (flag.equals("new")) {
				List AuthorArrylist = new ArrayList();
				AuthorArrylist=ss1.getCurrencyLoad();
				request.setAttribute("currency", AuthorArrylist);				
				
				ob=new IndentMasDetailsBean();
				ob=ss.getTitleNo();
				request.setAttribute("bean", ob);
				
				indexPage ="/Indent_Mas/index.jsp?newno=NewTitleNo";
				dispatch(request, response, indexPage);
				
			}
			
			
			
				

			if (flag.equals("ADDBOOK")) {
				log4jLogger.info("New===========flag=====" + flag);
								
				ob=new IndentMasDetailsBean();
				
				String indtno="",titlestatus="",memberCode="",indtdate="",member="",indentstatus="";				
				int titleno,noofcopy,pubcode,deptcode,pubyear;
				String title="",author="",authorcode="",publisher="",department="",isbn="",edition="",bcurrency; 
				double bcost=0.0,bprice=0.0,netamt=0.0,bdiscount=0.0;
				
				indtno=request.getParameter("indtno");
				titlestatus=request.getParameter("titlestatus");				
				indtdate=request.getParameter("indtdate");
				
				memberCode=request.getParameter("memberCode");
				member=request.getParameter("member");
				indentstatus=request.getParameter("indtstatus");
				
				titleno=Integer.parseInt(request.getParameter("titleno"));	
				title=request.getParameter("title");
				noofcopy=Integer.parseInt(request.getParameter("noofcopy"));
				
				authorcode=request.getParameter("authorCode");	
				author=request.getParameter("author");
				
				pubcode=Integer.parseInt(request.getParameter("pubCode"));	
				publisher=request.getParameter("pubname");
				
				deptcode=Integer.parseInt(request.getParameter("deptCode"));	
				department=request.getParameter("department");
				
				isbn=request.getParameter("isbn");
				pubyear=Integer.parseInt(request.getParameter("pubyear"));
				edition=request.getParameter("edition");
				
				bcurrency=request.getParameter("bcurrency");		
				bcost=Double.parseDouble(request.getParameter("bcost"));
				bprice=Double.parseDouble(request.getParameter("bprice"));
				bdiscount=Double.parseDouble(request.getParameter("bdiscount"));
				netamt=Double.parseDouble(request.getParameter("bacceptPrice"));
					
				
				
				 ob=new IndentMasDetailsBean();
				 ob=ss.getTitleNo();
				 int max_title_no=ob.getTitleNo();
				 
				 
				 
				 String[] a=null,b=null,c=null,d=null,e=null,f=null,g=null,h=null,acost=null,
				 acurrency=null,aprice=null,adiscount=null,autcode=null,adeptcode=null,apubcode=null,aisbn=null,ayearpub=null,aedition=null,amemcode=null,addstatus=null;
					
					a=request.getParameterValues("titleNo[]");
					b=request.getParameterValues("title[]");	
					f=request.getParameterValues("noofcopy[]");
					c=request.getParameterValues("author[]");
					autcode=request.getParameterValues("autcode[]");
					e=request.getParameterValues("publisher[]");
					apubcode=request.getParameterValues("pubcode[]");
					
					g=request.getParameterValues("department[]");
					adeptcode=request.getParameterValues("deptcode[]");
					aisbn=request.getParameterValues("isbn[]");
					ayearpub=request.getParameterValues("yearpub[]");
					aedition=request.getParameterValues("edition[]");
					h=request.getParameterValues("titlestatus[]");					           
				
					acost=request.getParameterValues("bcost[]");
					acurrency=request.getParameterValues("currency[]");
					aprice=request.getParameterValues("bprice[]");
					adiscount=request.getParameterValues("discount[]");
					d=request.getParameterValues("netamount[]");
					amemcode=request.getParameterValues("membercode[]");
					
					addstatus=request.getParameterValues("addstatus[]");
				
				 log4jLogger.info("////////////////////////////////////////////////////////");
				 


					List<Object> saveDetail = new ArrayList<Object>();
					 
					 if (!indtno.equals("") || indtno!=null) {
						 
						 log4jLogger.info("---- Inside IF Condition ------");
						 IndentMasDetailsBean detailsbean=new IndentMasDetailsBean();
						 
						 detailsbean.setIndtno(indtno);
						 detailsbean.setTitlestatus(titlestatus);
						 detailsbean.setIndtdate(request.getParameter("indtdate")); 
						 detailsbean.setMemcode(memberCode);
						 detailsbean.setMemname(member);					 
						 detailsbean.setIndtstatus(indentstatus);
						 
						 detailsbean.setTitleNo(titleno);
						 detailsbean.setTitle(title);
						 detailsbean.setNoofcopy(noofcopy);						 
						 detailsbean.setAuthor(author);
						 detailsbean.setAuthorcode(authorcode);
						 detailsbean.setPublisher(publisher);
						 detailsbean.setPubcode(pubcode);
						 detailsbean.setDepartment(department);
						 detailsbean.setDeptcode(deptcode);
						 detailsbean.setIsbn(isbn);
						 detailsbean.setYearpub(pubyear);
						 detailsbean.setEdition(edition);					 
						 
						 detailsbean.setBcost(bcost);
						 detailsbean.setBcurrency(bcurrency);
						 detailsbean.setBprice(bprice);
						 detailsbean.setDiscount(bdiscount);
						 detailsbean.setNetamount(netamt);	
						 
						 detailsbean.setAddstatus("Added");
						 
						    saveDetail.add(detailsbean);	
						    max_title_no=max_title_no+1;
						}
					 
					 
					 
					 int len=0;
					 
					 if (!a[0].equals("") && !a[0].isEmpty()) {
						 len=a.length;
					 }
							
					 for(int i=0;i<len;i++)
					 { 
						 log4jLogger.info("---- Inside For Loop Adding Book To Grid ------");
						 IndentMasDetailsBean detailsbean=new IndentMasDetailsBean();
						 
						 detailsbean.setTitleNo(Integer.parseInt(a[i].toString()));
						 detailsbean.setTitle(b[i]);
						 detailsbean.setNoofcopy(Integer.parseInt(f[i].toString()));
						 detailsbean.setAuthor(c[i]);
						 detailsbean.setAuthorcode(autcode[i]);
						 detailsbean.setPublisher(e[i]);
						 detailsbean.setPubcode(Integer.parseInt(apubcode[i]));
						 detailsbean.setDepartment(g[i]);
						 detailsbean.setDeptcode(Integer.parseInt(adeptcode[i]));						 
						 detailsbean.setIsbn(aisbn[i]);
						 detailsbean.setYearpub(Integer.parseInt(ayearpub[i]));
						 detailsbean.setEdition(aedition[i]);
						 
						 detailsbean.setBcost(Double.parseDouble(acost[i]));
						 detailsbean.setBcurrency(acurrency[i]);
						 detailsbean.setBprice(Double.parseDouble(aprice[i]));
						 detailsbean.setDiscount(Double.parseDouble(adiscount[i]));
						 detailsbean.setNetamount(Double.parseDouble(d[i]));	
						 
						 detailsbean.setTitlestatus(h[i]);	
						 detailsbean.setMemcode(amemcode[i]);
						 detailsbean.setIndtno(indtno);
						 //detailsbean.setIndtdate(indtdate);
						 //detailsbean.setMemcode(memberCode);
						 detailsbean.setMemname(member);					 
						 detailsbean.setIndtstatus(indentstatus);
						 
						 detailsbean.setAddstatus(addstatus[i].toString());
						 
						 saveDetail.add(detailsbean);
						 log4jLogger.info("New===========Adding Status =====" + addstatus[i]);
						 if(!addstatus[i].equals("Pending"))  {
						     max_title_no=max_title_no+1;						 
						 }
						 
					 }
					 	
					session.setAttribute("IndentMas",saveDetail);
					session.setAttribute("IndentMasSize",saveDetail.size());

					List AuthorArrylist = new ArrayList();
					AuthorArrylist=ss1.getCurrencyLoad();
					request.setAttribute("currency", AuthorArrylist);
					
					ob=new IndentMasDetailsBean();
					//ob=ss.getTitleNo();
					ob.setTitleNo(max_title_no);
					request.setAttribute("bean", ob);
					
					indexPage ="/Indent_Mas/index.jsp?newno=NewTitleNo";
					
				    dispatch(request, response, indexPage);
			}
			
			
			if (flag.equals("Save")) {
				
				log4jLogger.info("New===========flag=====" + flag);
								
				ob=new IndentMasDetailsBean();
				
				String indtno="",titlestatus="",memberCode="",indtdate="",member="",indentstatus="";
				
				indtno=request.getParameter("indtno");
				titlestatus=request.getParameter("titlestatus");				
				indtdate=Security.TextDate_member(request.getParameter("indtdate"));
				
				memberCode=request.getParameter("memberCode");
				member=request.getParameter("member");
				indentstatus=request.getParameter("indtstatus");
				
				 
				 String[] a=null,b=null,c=null,d=null,e=null,f=null,g=null,h=null,acost=null,
				 acurrency=null,aprice=null,adiscount=null,autcode=null,adeptcode=null,apubcode=null,aisbn=null,ayearpub=null,aedition=null,amemcode=null;
					
					a=request.getParameterValues("titleNo[]");
					b=request.getParameterValues("title[]");	
					f=request.getParameterValues("noofcopy[]");
					c=request.getParameterValues("author[]");
					autcode=request.getParameterValues("autcode[]");
					e=request.getParameterValues("publisher[]");
					apubcode=request.getParameterValues("pubcode[]");
					
					g=request.getParameterValues("department[]");
					adeptcode=request.getParameterValues("deptcode[]");
					aisbn=request.getParameterValues("isbn[]");
					ayearpub=request.getParameterValues("yearpub[]");
					aedition=request.getParameterValues("edition[]");
					h=request.getParameterValues("titlestatus[]");					           
				
					acost=request.getParameterValues("bcost[]");
					acurrency=request.getParameterValues("currency[]");
					aprice=request.getParameterValues("bprice[]");
					adiscount=request.getParameterValues("discount[]");
					d=request.getParameterValues("netamount[]");
					
					amemcode=request.getParameterValues("membercode[]");					
					
					
					List IndentSave=null;					 
					IndentSave=ss.getFullViewIndentMas(indtno);							 
					log4jLogger.info("----Size of List------"+IndentSave.size());					
					
					String commaSeparated=request.getParameter("flag1");					
					String [] items = commaSeparated.split(",");
					
					if(IndentSave.size() > 0){
						
						int	len=a.length;						
						List<Object> saveDetail = new ArrayList<Object>();
						 
						for (int j1=1;j1<items.length;j1++)  {
						
						 for(int i=0;i<len;i++)
						 { 
							 if(a[i].equals(items[j1])) {

							 IndentMasDetailsBean detailsbean=new IndentMasDetailsBean();
							 
							 detailsbean.setTitleNo(Integer.parseInt(a[i].toString()));
							 detailsbean.setTitle(b[i]);
							 detailsbean.setNoofcopy(Integer.parseInt(f[i].toString()));
							 detailsbean.setPendingcopy(Integer.parseInt(f[i].toString()));
							 detailsbean.setAuthor(c[i]);
							 detailsbean.setAuthorcode(autcode[i]);
							 detailsbean.setPublisher(e[i]);
							 detailsbean.setPubcode(Integer.parseInt(apubcode[i]));
							 detailsbean.setDepartment(g[i]);
							 detailsbean.setDeptcode(Integer.parseInt(adeptcode[i]));						 
							 detailsbean.setIsbn(aisbn[i]);
							 detailsbean.setYearpub(Integer.parseInt(ayearpub[i]));
							 detailsbean.setEdition(aedition[i]);	
							 
							 detailsbean.setBcost(Double.parseDouble(acost[i]));
							 detailsbean.setBcurrency(acurrency[i]);
							 detailsbean.setBprice(Double.parseDouble(aprice[i]));
							 detailsbean.setDiscount(Double.parseDouble(adiscount[i]));
							 detailsbean.setNetamount(Double.parseDouble(d[i]));	
							 detailsbean.setTitlestatus(h[i]);	
							 
							 detailsbean.setIndtno(indtno);
							 detailsbean.setIndtdate(request.getParameter("indtdate"));
							 detailsbean.setMemcode(amemcode[i]);
							 detailsbean.setMemname(member);							 
							 detailsbean.setTitlestatus(titlestatus);
							 detailsbean.setIndtstatus(indentstatus);
							 
							 saveDetail.add(detailsbean);
							 
							 //log4jLogger.info("=========== Added Values ====="+items[j1]);
							 break;
							 }else{
								 
								 log4jLogger.info("=========== Inner Loop False ====="+a[i]);
							 }
						 }
						 //log4jLogger.info("=========== Outer Loop ====="+j1);					    					 
						}
						
						session.setAttribute("IndentMas",saveDetail);
						session.setAttribute("IndentMasSize",saveDetail.size());
						
						indexPage ="/Indent_Mas/index.jsp?newno=NewTitleNo&check=UpdateCheck";
					
						
					}else {							
					
				     int	len=a.length;					
					 List<Object> saveDetail = new ArrayList<Object>();
					 
				    for (int j1=1;j1<items.length;j1++)  {
							
					 for(int i=0;i<len;i++)
					 { 
						 if(a[i].equals(items[j1])) {
							 
						 
						 IndentMasDetailsBean detailsbean=new IndentMasDetailsBean();
						 
						 detailsbean.setTitleNo(Integer.parseInt(a[i].toString()));
						 detailsbean.setTitle(b[i]);
						 detailsbean.setNoofcopy(Integer.parseInt(f[i].toString()));
						 detailsbean.setPendingcopy(Integer.parseInt(f[i].toString()));
						 detailsbean.setAuthor(c[i]);
						 detailsbean.setAuthorcode(autcode[i]);
						 detailsbean.setPublisher(e[i]);
						 detailsbean.setPubcode(Integer.parseInt(apubcode[i]));
						 detailsbean.setDepartment(g[i]);
						 detailsbean.setDeptcode(Integer.parseInt(adeptcode[i]));						 
						 detailsbean.setIsbn(aisbn[i]);
						 detailsbean.setYearpub(Integer.parseInt(ayearpub[i]));
						 detailsbean.setEdition(aedition[i]);	
						 
						 detailsbean.setBcost(Double.parseDouble(acost[i]));
						 detailsbean.setBcurrency(acurrency[i]);
						 detailsbean.setBprice(Double.parseDouble(aprice[i]));
						 detailsbean.setDiscount(Double.parseDouble(adiscount[i]));
						 detailsbean.setNetamount(Double.parseDouble(d[i]));	
						 detailsbean.setTitlestatus(h[i]);	
						 
						 detailsbean.setIndtno(indtno);
						 detailsbean.setIndtdate(indtdate);
						 detailsbean.setMemcode(amemcode[i]);
						 detailsbean.setMemname(member);							 
						 detailsbean.setTitlestatus(titlestatus);
						 detailsbean.setIndtstatus(indentstatus);
						 
						 saveDetail.add(detailsbean);
						 
						 //log4jLogger.info("===========Save Added Values ====="+items[j1]);
						 break;
						 }else{
							 
							 log4jLogger.info("===========Save Inner Loop False ====="+a[i]);
						 }
					 }
					 //log4jLogger.info("===========Save Outer Loop ====="+j1);
				    }
					 
					 if(!(saveDetail==null) || !(saveDetail.size() ==0))
					 {
						 List SaveList=null;					 
						 SaveList=ss.getSaveIndentMas(saveDetail);
					 }					
					 
					 indexPage ="/Indent_Mas/index.jsp?newno=NewTitleNo&check=SaveSuccess";					 
					}
					

					List AuthorArrylist = new ArrayList();
					AuthorArrylist=ss1.getCurrencyLoad();
					request.setAttribute("currency", AuthorArrylist);
					
					ob=new IndentMasDetailsBean();
					ob=ss.getTitleNo();
					request.setAttribute("bean", ob);
								
				    dispatch(request, response, indexPage);
			}
			
			
            if (flag.equals("Search")) {
				
				log4jLogger.info("New===========flag=====" + flag);
				
				String indtno="";				
				indtno=request.getParameter("indtno");				
				
				 List SaveList=null;					 
				 SaveList=ss.getFullViewIndentMas(indtno);					
				
				 if(SaveList.size()>0)   {
					 
						session.setAttribute("IndentMas",SaveList);
						session.setAttribute("IndentMasSize",SaveList.size());		
						
						indexPage ="/Indent_Mas/index.jsp?newno=NewTitleNo";
						
				 }else {
					 
					   indexPage ="/Indent_Mas/index.jsp?newno=NewTitleNo&check=DeleteFail";					 
				 }
				
				List AuthorArrylist = new ArrayList();
				AuthorArrylist=ss1.getCurrencyLoad();
				request.setAttribute("currency", AuthorArrylist);
				
				ob=new IndentMasDetailsBean();
				ob=ss.getTitleNo();
				request.setAttribute("bean", ob);
							
			    dispatch(request, response, indexPage);
		     }
            
            
            if (flag.equals("Delete")) {
				
				log4jLogger.info("New===========flag=====" + flag);
				
				String indtno="";				
				indtno=request.getParameter("indtno");				
				
				List OrderView=null;
				OrderView=ss.getFullViewIndentMas(indtno);			 
				
				if(OrderView.size()>0)
				{					
				   ss.getDeleteIndentMas(indtno);
				   indexPage ="/Indent_Mas/index.jsp?newno=NewTitleNo&check=DeletedSuccess";
				}else {
					
				   indexPage ="/Indent_Mas/index.jsp?newno=NewTitleNo&check=DeleteFail";
				}
								
				List AuthorArrylist = new ArrayList();
				AuthorArrylist=ss1.getCurrencyLoad();
				request.setAttribute("currency", AuthorArrylist);	
				
				ob=new IndentMasDetailsBean();
				ob=ss.getTitleNo();
				request.setAttribute("bean", ob);				
							
			    dispatch(request, response, indexPage);
		     }
            

            if (flag.equals("Update")) {

				log4jLogger.info("New===========flag=====" + flag);
								
				ob=new IndentMasDetailsBean();
				
				String indtno="",titlestatus="",memberCode="",indtdate="",member="",indentstatus="";
				
				indtno=request.getParameter("indtno");
				titlestatus=request.getParameter("titlestatus");				
				indtdate=Security.TextDate_member(request.getParameter("indtdate"));
				
				memberCode=request.getParameter("memberCode");
				member=request.getParameter("member");
				indentstatus=request.getParameter("indtstatus");				
				 
				String[] a=null,b=null,c=null,d=null,e=null,f=null,g=null,h=null,acost=null,
				acurrency=null,aprice=null,adiscount=null,autcode=null,adeptcode=null,apubcode=null,aisbn=null,ayearpub=null,aedition=null,amemcode=null;
					
					a=request.getParameterValues("titleNo[]");
					b=request.getParameterValues("title[]");	
					f=request.getParameterValues("noofcopy[]");
					c=request.getParameterValues("author[]");
					autcode=request.getParameterValues("autcode[]");
					e=request.getParameterValues("publisher[]");
					apubcode=request.getParameterValues("pubcode[]");
					
					g=request.getParameterValues("department[]");
					adeptcode=request.getParameterValues("deptcode[]");
					aisbn=request.getParameterValues("isbn[]");
					ayearpub=request.getParameterValues("yearpub[]");
					aedition=request.getParameterValues("edition[]");
					h=request.getParameterValues("titlestatus[]");					           
				
					acost=request.getParameterValues("bcost[]");
					acurrency=request.getParameterValues("currency[]");
					aprice=request.getParameterValues("bprice[]");
					adiscount=request.getParameterValues("discount[]");
					d=request.getParameterValues("netamount[]");
					
					amemcode=request.getParameterValues("membercode[]");					
				 
					ss.getDeleteIndentMas(indtno);						 
					
				    int	len=a.length;					
					List<Object> saveDetail = new ArrayList<Object>();
				
					 for(int i=0;i<len;i++)
					 { 
						 
						 IndentMasDetailsBean detailsbean=new IndentMasDetailsBean();
						 
						 detailsbean.setTitleNo(Integer.parseInt(a[i].toString()));
						 detailsbean.setTitle(b[i]);
						 detailsbean.setNoofcopy(Integer.parseInt(f[i].toString()));
						 detailsbean.setPendingcopy(Integer.parseInt(f[i].toString()));
						 detailsbean.setAuthor(c[i]);
						 detailsbean.setAuthorcode(autcode[i]);
						 detailsbean.setPublisher(e[i]);
						 detailsbean.setPubcode(Integer.parseInt(apubcode[i]));
						 detailsbean.setDepartment(g[i]);
						 detailsbean.setDeptcode(Integer.parseInt(adeptcode[i]));						 
						 detailsbean.setIsbn(aisbn[i]);
						 detailsbean.setYearpub(Integer.parseInt(ayearpub[i]));
						 detailsbean.setEdition(aedition[i]);	
						 
						 detailsbean.setBcost(Double.parseDouble(acost[i]));
						 detailsbean.setBcurrency(acurrency[i]);
						 detailsbean.setBprice(Double.parseDouble(aprice[i]));
						 detailsbean.setDiscount(Double.parseDouble(adiscount[i]));
						 detailsbean.setNetamount(Double.parseDouble(d[i]));	
						 detailsbean.setTitlestatus(h[i]);	
						 
						 detailsbean.setIndtno(indtno);
						 detailsbean.setIndtdate(indtdate);
						 detailsbean.setMemcode(amemcode[i]);
						 detailsbean.setMemname(member);							 
						 detailsbean.setTitlestatus(titlestatus);
						 detailsbean.setIndtstatus(indentstatus);
						 
						 saveDetail.add(detailsbean);

				    }
					 
					 if(!(saveDetail==null) || !(saveDetail.size() ==0))
					 {
						 List SaveList=null;					 
						 SaveList=ss.getSaveIndentMas(saveDetail);
					 }					
					 
					indexPage ="/Indent_Mas/index.jsp?newno=NewTitleNo&check=UpdateSuccess";					

					List AuthorArrylist = new ArrayList();
					AuthorArrylist=ss1.getCurrencyLoad();
					request.setAttribute("currency", AuthorArrylist);
					
					ob=new IndentMasDetailsBean();
					ob=ss.getTitleNo();
					request.setAttribute("bean", ob);
								
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
