package Lib.Auto.JNL_Order;

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
import Common.businessutil.calaloging.CalalogingService;
import Common.businessutil.serialcontrol.SerialcontrolService;
import Login.Login;

public class OrdJNLProcessing extends HttpServlet implements Serializable {
	private static final long serialVersionUID = 1L;

	private static Logger log4jLogger = Logger.getLogger(OrdJNLProcessing.class);

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
			
			
			SerialcontrolService ss = BusinessServiceFactory.INSTANCE.getSerialcontrolService();
			CalalogingService ss1 = BusinessServiceFactory.INSTANCE.getCalalogingService();
			
			JnlorderBean ob=new JnlorderBean();			
			
			String access_no = "";
			String New_Access_no= "",doctype="";	
			
			flag = request.getParameter("flag");			
			doctype=request.getParameter("doc_type");
			
			
			
			
			if(flag.equals("selectV"))
			{
			log4jLogger.info("Inside======flag====="+request.getParameter("flag1"));
						
			
			List OrdJNLArrylist = new ArrayList();
			
			String order_no="",order_date="",quote_no,quote_date="",sup_name="",csubsdt="",cstodt="";
			int sup_code;
			 
			
			 order_no=request.getParameter("ordno");
			 order_date=request.getParameter("orddate");
			 quote_no=request.getParameter("quoteno");
			 quote_date=request.getParameter("quotedate");
			 
			 csubsdt=request.getParameter("csubsdate");
			 cstodt=request.getParameter("cstodate");
			 
			 ob=new JnlorderBean();
			 ob.setJNLName(request.getParameter("flag1"));
			 
			 if(request.getParameter("sup_code")!="" && request.getParameter("sup_code")!=null)  {
				 
				 sup_code=Integer.parseInt(request.getParameter("sup_code"));
				 ob.setSupCode(sup_code);				 
				 sup_name=request.getParameter("sname");
				 ob.setRemarks(sup_name);
				 log4jLogger.info("Supplier Code:"+request.getParameter("sup_code")+" And Name is:"+sup_name);
			 }
			 
			 ob.setOrdNo(order_no);
			 ob.setOrdate(order_date);
			 ob.setQuoteNo(quote_no);
			 ob.setQuotedate(quote_date);
			 ob.setAdd1(csubsdt);
			 ob.setAdd2(cstodt);
			
			OrdJNLArrylist=ss.getOrdSelectedJNL(ob);
			
			//OrdJNLArrylist=ss.getOrdJNLSearchName(ob);
			
			
			//request.setAttribute("search", OrdJNLArrylist);
			session.setAttribute("JNLSearch",OrdJNLArrylist);
			session.setAttribute("JNLSearchSize",OrdJNLArrylist.size());
			
			List AuthorArrylist = new ArrayList();
			AuthorArrylist=ss1.getCurrencyLoad();
			request.setAttribute("currency", AuthorArrylist);
			
			
			//indexPage ="/JNL_Order/index.jsp?check=showJNL&flag="+flag+"";
			indexPage ="/JNL_Order/index.jsp";
			dispatch(request, response, indexPage);
			/*
			String[] flag1 = request.getParameterValues("flag1");
			//log4jLogger.info("000000==="+flag1);
			
			
			String ids[] = flag1;
			
			log4jLogger.info("111111==="+ids.length);
			
			for (int i = 0; i < ids.length; i++)
			{
				log4jLogger.info("77777==="+ids[i].split("-")[0].trim());
				log4jLogger.info("77777==="+ids[i].split("-")[1].trim());
			}
			
			
			
			//String[] flag2 = request.getParameterValues("flag1");
			//log4jLogger.info("111111==="+flag2.length+" And "+flag2);
			//int len4=flag2.length;
			//for (int i=0; i<len4; i++) {
				 //log4jLogger.info("++++++" +  flag2[i]); 

				 //   }*/
			
			}
			
			
			
			if (flag.equals("EnquiryNoListView")) {
				log4jLogger.info("Inside JNLOrder--->Enquiry Search And Flag=" + flag);
								
				ob=new JnlorderBean();
				
				String enq_no="";				 
				
				enq_no=request.getParameter("quoteno");			 
				ob.setQuoteNo(enq_no);
				ob.setOrdNo(request.getParameter("ordno"));
				ob.setOrdate(request.getParameter("orddate"));
				 
				List OrderView=null;
				OrderView=ss.getEnqJNLFullviewForOrder(ob);
				 
				if(OrderView.size()>0)  {
				  session.setAttribute("JNLSearch",OrderView);
				  session.setAttribute("JNLSearchSize",OrderView.size());
				
				List AuthorArrylist = new ArrayList();
				AuthorArrylist=ss1.getCurrencyLoad();
				request.setAttribute("currency", AuthorArrylist);
				indexPage ="/JNL_Order/index.jsp";				
				} else {
					indexPage ="/JNL_Order/index.jsp?check=DeleteFail";							
				}
				
				dispatch(request, response, indexPage);
				
			}
			
			
			
			
			
			
			
			
			if((flag.equals("Sup"))||(flag.equals("Journal")) ||(flag.equals("OrderNo")) || flag.equals("EnquiryNo"))
			{
				
				log4jLogger.info("OrdJNLSearch===========flag====="+flag);
				List OrdJNLArrylist = new ArrayList();
				ArrayList list=null;
				
				ob=new JnlorderBean();
				ob.setJNLName(request.getParameter("name"));
				ob.setAdd1(request.getParameter("flag"));
				
				OrdJNLArrylist=ss.getOrdJNLSearchName(ob);
				
																						
				request.setAttribute("search", OrdJNLArrylist);
				indexPage ="/JNL_Order/search_nmvc.jsp?check=ok&flag="+flag+"";
				dispatch(request, response, indexPage);
			}
			
			
			if(flag.equals("LoadJournal"))
			{
				
				log4jLogger.info("OrdJNLSearch===========flag====="+flag);
				List OrdJNLArrylist = new ArrayList();
				ArrayList list=null;
				
				ob=new JnlorderBean();
				ob.setJNLName("");
				ob.setAdd1("Journal");
				
				List OrdJNLArrylist1=ss.getOrdJNLSearchName(ob);
				
																						
				request.setAttribute("search", OrdJNLArrylist1);
				session.setAttribute("JNLSearch",OrdJNLArrylist1);
				
				
				List AuthorArrylist = new ArrayList();
				AuthorArrylist=ss1.getCurrencyLoad();
				request.setAttribute("currency", AuthorArrylist);
				
				
				indexPage ="/JNL_Order/index.jsp?check=showJNL&flag="+flag+"";
				dispatch(request, response, indexPage);
			}
			
			
			
			
			
			if (flag.equals("new")) {
				List AuthorArrylist = new ArrayList();
				AuthorArrylist=ss1.getCurrencyLoad();
				request.setAttribute("currency", AuthorArrylist);				
				
				indexPage ="/JNL_Order/index.jsp";
				dispatch(request, response, indexPage);
				
			}
			
			
			
		/*	if (flag.equals("Save_OLD")) {
				log4jLogger.info("Journal_Order===========Save=====" + flag);
								
				ob=new JnlorderBean();
								
				String[] a=null,b=null,c=null,d=null,e=null,f=null,g=null,h=null,j=null;
				a=request.getParameterValues("jnlno[]");
				
				c=request.getParameterValues("noofissue[]");
				d=request.getParameterValues("volno[]");
				e=request.getParameterValues("bcost[]");
				f=request.getParameterValues("currency[]");
				g=request.getParameterValues("bprice[]");
				h=request.getParameterValues("discount[]");
				j=request.getParameterValues("acceptPrice[]");
				
				int len=a.length;
								
				log4jLogger.info("11111111=====: " + len);
				
				
				
				 for (int i=0; i<len; i++) {
					 log4jLogger.info("*********************************");
					 log4jLogger.info("Lib :"+ a[i]);
					 ob.setJNL_Accno(a[i]);
					 
					 log4jLogger.info("Lib :"+ c[i]);
					 log4jLogger.info("Lib :"+ d[i]);
					 log4jLogger.info("Lib :"+ e[i]);
					 log4jLogger.info("Lib :"+ f[i]);
					 log4jLogger.info("Lib :"+ g[i]);
					 log4jLogger.info("Lib :"+ h[i]);
					 log4jLogger.info("Lib :"+ j[i]);
					 log4jLogger.info("");
					 log4jLogger.info("*********************************");
					 log4jLogger.info("");
					 log4jLogger.info("Subsription form:"+Security.TextDate_member(request.getParameter("subsfrom"+a[i]+"")));
					 
					    }
				
				indexPage ="/JNL_Order/index.jsp";
				
				
				dispatch(request, response, indexPage);
			}		*/	
			
			
		

			if (flag.equals("Save")) {
				log4jLogger.info("New===========flag=====" + flag);
								
				ob=new JnlorderBean();
				
				String order_no="",order_date="",quote_no,quote_date="",remarks="",add1="",add2="";
				int sup_code;
				 
				
				 order_no=request.getParameter("ordno");
				 order_date=Security.TextDate_member(request.getParameter("orddate"));
				 quote_no=request.getParameter("quoteno");
				 quote_date=Security.TextDate_member(request.getParameter("quotedate"));
				 remarks=request.getParameter("remarks");
				 add1=request.getParameter("add1");
				 add2=request.getParameter("add2");
				 
				 sup_code=Integer.parseInt(request.getParameter("sup_code"));
				 
				 
				 ob.setOrdNo(order_no);
				 ob.setOrdate(order_date);
				 ob.setQuoteNo(quote_no);
				 ob.setQuotedate(quote_date);
				 ob.setSupCode(sup_code);
				 ob.setRemarks(remarks);
				 ob.setAdd1(add1);
				 ob.setAdd2(add2);
				 	
				 
				 String[] a=null,b=null,c=null,d=null,e=null,f=null,g=null,h=null,j=null;
					
					a=request.getParameterValues("jnlno[]");
					b=request.getParameterValues("frequency[]");		
					c=request.getParameterValues("noofissue[]");
					d=request.getParameterValues("volno[]");
					e=request.getParameterValues("bcost[]");
					f=request.getParameterValues("currency[]");
					g=request.getParameterValues("bprice[]");
					h=request.getParameterValues("discount[]");
					j=request.getParameterValues("acceptPrice[]");
					
				 
				 log4jLogger.info("OrderNo=====: " + ob.getOrdNo());
				 log4jLogger.info("OrderDate===: " + ob.getOrdate());
				 log4jLogger.info("QuoteNo=====: " + ob.getQuoteNo());
				 log4jLogger.info("QuoteDate===: " + ob.getQuotedate());
				 log4jLogger.info("Supplier====: " + ob.getSupCode());
				 log4jLogger.info("Remarks=====: " + ob.getRemarks());
				 log4jLogger.info("add1   =====: " + ob.getAdd1());
				 log4jLogger.info("add2   =====: " + ob.getAdd2());
				
				
				 log4jLogger.info("////////////////////////////////////////////////////////");
				
				
				 List OrderSave=null;
				 
				 OrderSave=ss.getOrdJNLMasSave(ob);		 
				 
				 if(OrderSave.size()==1){
										 
					//List OrderView=null;
					//OrderView=ss.getOrdJNLFullview(order_no);
					
					int len=a.length;
					
					List<Object> saveDetail = new ArrayList<Object>();
					//OrderSave=null;
					 
					 for (int i=0; i<len; i++) {

						 ob.setJNL_Accno(a[i]);
				
						 JnlorderDetailsBean detailsbean=new JnlorderDetailsBean();
						 
						 detailsbean.setJnlCode(Integer.parseInt(a[i]));
						 detailsbean.setFrequency(b[i]);
						 detailsbean.setSubsfrmdate(request.getParameter("subsfrom"+a[i]+""));
						 detailsbean.setSubstodate(request.getParameter("substo"+a[i]+""));
						 detailsbean.setNoofissue(Integer.parseInt(c[i]));
						 detailsbean.setVolumeNo(d[i]);
						 detailsbean.setBcost(Double.parseDouble(e[i]));
						 detailsbean.setBcurrency(f[i]);
						 detailsbean.setBprice(Double.parseDouble(g[i]));
						 detailsbean.setDiscount(Double.parseDouble(h[i]));
						 detailsbean.setNetamount(Double.parseDouble(j[i]));
						 
						 detailsbean.setOrdNo(order_no);
						 detailsbean.setOrdDate(Security.getdate(order_date));
						 detailsbean.setQuoteNo(quote_no);
						 detailsbean.setQuoteDate(Security.getdate(quote_date));
						 detailsbean.setSupCode(sup_code);		
						 detailsbean.setSupplier(request.getParameter("sname"));
						 detailsbean.setRemarks(remarks);
						 detailsbean.setAdd1(add1);
						 detailsbean.setAdd2(add2);	 
						 
						 saveDetail.add(detailsbean);				 
						 
						}
					
					List AuthorArrylist = new ArrayList();
					AuthorArrylist=ss1.getCurrencyLoad();
					request.setAttribute("currency", AuthorArrylist);
					
					session.setAttribute("JNLSearch",saveDetail);
					session.setAttribute("JNLSearchSize",saveDetail.size());
					
					 log4jLogger.info("----Update Check For Order-------"+OrderSave.size());
					 indexPage ="/JNL_Order/index.jsp?check=UpdateCheck";
					 
				 }else {
				
				int len=a.length;
				
				List<Object> saveDetail = new ArrayList<Object>();
				OrderSave=null;
				 
				 for (int i=0; i<len; i++) {
					 log4jLogger.info("*********************************");
					 log4jLogger.info("Lib :"+ a[i]);
					 ob.setJNL_Accno(a[i]);
					 
					 log4jLogger.info("Lib :"+ c[i]);
					 log4jLogger.info("Lib :"+ d[i]);
					 log4jLogger.info("Lib :"+ e[i]);
					 log4jLogger.info("Lib :"+ f[i]);
					 log4jLogger.info("Lib :"+ g[i]);
					 log4jLogger.info("Lib :"+ h[i]);
					 log4jLogger.info("Lib :"+ j[i]);
					 log4jLogger.info("");
					 log4jLogger.info("*********************************");
					 log4jLogger.info("");
					 log4jLogger.info("Subsription form:"+Security.TextDate_member(request.getParameter("subsfrom"+a[i]+"")));
					 
					 JnlorderDetailsBean detailsbean=new JnlorderDetailsBean();
					 
					 detailsbean.setJnlCode(Integer.parseInt(a[i]));
					 detailsbean.setSubsfrmdate(Security.TextDate_member(request.getParameter("subsfrom"+a[i]+"")));
					 detailsbean.setSubstodate(Security.TextDate_member(request.getParameter("substo"+a[i]+"")));
					 detailsbean.setNoofissue(Integer.parseInt(c[i]));
					 detailsbean.setVolumeNo(d[i]);
					 detailsbean.setBcost(Double.parseDouble(e[i]));
					 detailsbean.setBcurrency(f[i]);
					 detailsbean.setBprice(Double.parseDouble(g[i]));
					 detailsbean.setDiscount(Double.parseDouble(h[i]));
					 detailsbean.setNetamount(Double.parseDouble(j[i]));
					 
					 
					 detailsbean.setOrdNo(order_no);
					 detailsbean.setSupCode(sup_code);					 
					 detailsbean.setRemarks(remarks);
					 detailsbean.setAdd1(add1);
					 detailsbean.setAdd2(add2);	 
					 
					 saveDetail.add(detailsbean);				 
					 
					}								 
				 				 
				 OrderSave=ss.getOrdJNLDetailsSave(saveDetail);
	
				indexPage ="/JNL_Order/index.jsp?check=SaveSuccess";
			   }				 
				
				dispatch(request, response, indexPage);
			}
			
			
			
			if (flag.equals("search")) {
				log4jLogger.info("Search===========flag=====" + flag);
								
				ob=new JnlorderBean();
				
				String order_no="";
				 
				
				 order_no=request.getParameter("ordno");			 
				 ob.setOrdNo(order_no);
				 
				List OrderView=null;
				OrderView=ss.getOrdJNLFullview(order_no);
				 
				if(OrderView.size()>0)  {
				  session.setAttribute("JNLSearch",OrderView);
				  session.setAttribute("JNLSearchSize",OrderView.size());
				
				List AuthorArrylist = new ArrayList();
				AuthorArrylist=ss1.getCurrencyLoad();
				request.setAttribute("currency", AuthorArrylist);
				indexPage ="/JNL_Order/index.jsp";				
				} else {
					indexPage ="/JNL_Order/index.jsp?check=DeleteFail";							
				}
				
				dispatch(request, response, indexPage);
				
			}
			
			
			if (flag.equals("delete")) {
				log4jLogger.info("Delete===========flag=====" + flag);
				
				String order_no="";
				 
				
				 order_no=request.getParameter("ordno");			 
								 
				List OrderView=null;
				OrderView=ss.getOrdJNLFullview(order_no);
				 
				
				if(OrderView.size()>0)
				{	
					
					List CheckView=null;
					CheckView = ss.getSupInvCheck(order_no);
					String checkinv="";
					if(CheckView.size()>0) {
						 checkinv=(String) CheckView.get(0);
					}
					
					if(checkinv.equalsIgnoreCase(order_no)) {
						indexPage ="/JNL_Order/index.jsp?check=RefferedOther";
					}else {					
					  ss.getOrdJNLDelete(order_no);					
					  indexPage ="/JNL_Order/index.jsp?check=DeletedSuccess";
					}
					
				}else{
					
					indexPage ="/JNL_Order/index.jsp?check=DeleteFail";
				}
				
				
				dispatch(request, response, indexPage);
				
			}
			
			
			if (flag.equals("update")) {
				log4jLogger.info("Update===========flag=====" + flag);
								
				ob=new JnlorderBean();
				
				String order_no="",order_date="",quote_no,quote_date="",remarks="",add1="",add2="";
				int sup_code;
				 
				
				 order_no=request.getParameter("ordno");
				 order_date=Security.TextDate_member(request.getParameter("orddate"));
				 quote_no=request.getParameter("quoteno");
				 quote_date=Security.TextDate_member(request.getParameter("quotedate"));
				 remarks=request.getParameter("remarks");
				 add1=request.getParameter("add1");
				 add2=request.getParameter("add2");
				 
				 sup_code=Integer.parseInt(request.getParameter("sup_code"));
				 
				 
				 ob.setOrdNo(order_no);
				 ob.setOrdate(order_date);
				 ob.setQuoteNo(quote_no);
				 ob.setQuotedate(quote_date);
				 ob.setSupCode(sup_code);
				 ob.setRemarks(remarks);
				 ob.setAdd1(add1);
				 ob.setAdd2(add2);
				 
				 
				 List CheckView=null;
				 CheckView = ss.getSupInvCheck(order_no);
				 String checkinv="";
				 if(CheckView.size()>0) {
					 checkinv=(String) CheckView.get(0);
				 }
				 
					
				 if(checkinv.equalsIgnoreCase(order_no)) {
						indexPage ="/JNL_Order/index.jsp?check=RefferedOther";
				 }else {
				 
				 ss.getOrdJNLDelete(order_no);   // To Delete Record
				 
				 List OrderSave=null;				 
				 OrderSave=ss.getOrdJNLMasSave(ob);		 
				 
				 
				String[] a=null,b=null,c=null,d=null,e=null,f=null,g=null,h=null,j=null;
				
				a=request.getParameterValues("jnlno[]");				
				c=request.getParameterValues("noofissue[]");
				d=request.getParameterValues("volno[]");
				e=request.getParameterValues("bcost[]");
				f=request.getParameterValues("currency[]");
				g=request.getParameterValues("bprice[]");
				h=request.getParameterValues("discount[]");
				j=request.getParameterValues("acceptPrice[]");
				
				int len=a.length;
				
				List<Object> saveDetail = new ArrayList<Object>();
				OrderSave=null;
				 
				 for (int i=0; i<len; i++) {

					 ob.setJNL_Accno(a[i]);

					 log4jLogger.info("Subsription form:"+Security.TextDate_member(request.getParameter("subsfrom"+a[i]+"")));
					 
					 JnlorderDetailsBean detailsbean=new JnlorderDetailsBean();
					 
					 detailsbean.setJnlCode(Integer.parseInt(a[i]));
					 detailsbean.setSubsfrmdate(Security.TextDate_member(request.getParameter("subsfrom"+a[i]+"")));
					 detailsbean.setSubstodate(Security.TextDate_member(request.getParameter("substo"+a[i]+"")));
					 detailsbean.setNoofissue(Integer.parseInt(c[i]));
					 detailsbean.setVolumeNo(d[i]);
					 detailsbean.setBcost(Double.parseDouble(e[i]));
					 detailsbean.setBcurrency(f[i]);
					 detailsbean.setBprice(Double.parseDouble(g[i]));
					 detailsbean.setDiscount(Double.parseDouble(h[i]));
					 detailsbean.setNetamount(Double.parseDouble(j[i]));
					 
					 detailsbean.setOrdNo(order_no);
					 detailsbean.setSupCode(sup_code);					 
					 detailsbean.setRemarks(remarks);
					 detailsbean.setAdd1(add1);
					 detailsbean.setAdd2(add2);	 
					 
					 saveDetail.add(detailsbean);				 
					 
					}								 
				 				 
				  OrderSave=ss.getOrdJNLDetailsSave(saveDetail);	
				  indexPage ="/JNL_Order/index.jsp?check=UpdateSuccess";
				}
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
