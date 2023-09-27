package Lib.Auto.Journal_Artical;

import java.io.IOException;
import java.io.PrintWriter;

import java.sql.DriverManager;
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
import Common.businessutil.BusinessServiceFactory;
import Common.businessutil.calaloging.CalalogingService;
import Common.businessutil.serialcontrol.SerialcontrolService;
import Lib.Auto.Journal.journalbean;
import Lib.Auto.Journal_Issues.JnlIssues;

import Lib.Auto.Journal_Artical.journalArtbean;

public class JournalArt extends HttpServlet {
	private static Logger log4jLogger = Logger.getLogger(JournalArt.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String flag,flag2,Subject_Code,Doc_Type,Journal_jcode,Journal_jname,Journal_atlno,Journal_bvolno,Journal_atltitle,Journal_atlauthor,Journal_volno,Journal_issueno,Journal_issueyear,Journal_issuemonth,Journal_pages,Journal_subject,Journal_key,Journal_abst,Journal_issue_access_no,Journal_abstract;
	String Journal_Mas_Val_code,protocol;
	int Journal_Interface_code,Journal_Mas_code;
	int updateFlag;
	int dept_code=0, pub_code=0, sub_code=0;
	journalArtbean ob=new journalArtbean();

	singlecodecheck chkcode=new singlecodecheck();
	
	String indexPage = null;
	
	
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) {

		performTask(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) {

		performTask(request, response);

	}
	public void performTask(
		HttpServletRequest request,
		HttpServletResponse response) {


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
            		SerialcontrolService ss = BusinessServiceFactory.INSTANCE.getSerialcontrolService();
			flag=request.getParameter("flag");
			flag2=request.getParameter("flag2");
			String nm=request.getParameter("name");
			if(flag.equals("new"))
	   			{
				log4jLogger.info("new==============="+flag);
				String jcode=request.getParameter("jcode");
				String jname=request.getParameter("jname");
				String issueaccessno=request.getParameter("issueaccessno");
				String jvolno=request.getParameter("jvolno");
				String issueno=request.getParameter("issueno");
				String year=request.getParameter("year");
				String month=request.getParameter("month");
					String code=ss.getJnlAtlMax();
					ob.setAtlno(code);
					request.setAttribute("bean", ob);
				indexPage="/Journal_Artical/index.jsp?jname="+jname+"&jcode="+jcode+"&issueaccessno="+issueaccessno+"&jvolno="+jvolno+"&issueno="+issueno+"&year="+year+"&month="+month+"&check=newJournal";
				dispatch(request, response, indexPage);
   				
				}

			
		
			
      if((flag.equals("Nam"))||(flag.equals("Sub"))||(flag.equals("Atl"))||(flag.equals("Iss"))){
    	  log4jLogger.info("flag==============="+flag);
	          journalbean newbean=new journalbean();
				List AuthorArrylist = new ArrayList();
				newbean=new journalbean();
				if(request.getParameter("name")!=null){
					newbean.setJname(request.getParameter("name"));
					newbean.setJremarks(request.getParameter("flag"));
					if((!flag2.equalsIgnoreCase(""))&&(!flag2.equalsIgnoreCase("null")))
					{
						newbean.setJcode(flag2);
					}
					else
						newbean.setJcode("");
					
					journalbean beanobject=new journalbean();
					beanobject=ss.getJnlSearchName(newbean);
					request.setAttribute("bean", beanobject);
			        indexPage = "/Journal_Artical/search.jsp?check=ok&flag="+flag+"&nam="+nm+"";
			    	dispatch(request, response, indexPage);
				}
				
			}
			
			
			
			
      if(flag.equals("save"))
		 {
    		log4jLogger.info("save==============="+flag);
    	  
    		Journal_jcode=request.getParameter("jcode");
    		Journal_jname=chkcode.getParam( request, "jname");
    		Journal_atlno=chkcode.getParam( request, "atlno");
    		Journal_bvolno=chkcode.getParam( request, "bvolno");
    		Journal_atltitle=chkcode.getParam( request, "title");
    		Journal_atlauthor=chkcode.getParam( request, "author");
    		Journal_volno=chkcode.getParam( request, "jvolno");
    		Journal_issueno=chkcode.getParam( request, "issueno");
    		Journal_issueyear=chkcode.getParam( request, "year");
    		Journal_issuemonth=chkcode.getParam( request, "month");
    		Journal_pages=chkcode.getParam( request, "page");
    		Journal_subject=chkcode.getParam( request, "subject");
    		Journal_key=chkcode.getParam( request, "key");
    		Journal_abst=chkcode.getParam( request, "content");
    		Journal_issue_access_no=chkcode.getParam( request, "issueaccessno");
    		Journal_abstract=chkcode.getParam( request, "abstract");
    		
    		journalArtbean ob=new journalArtbean();
    		ob=ss.getJnlSearchAtl(Integer.parseInt(request.getParameter("atlno")));    		
			
			
			if (ob.getAtlno()!=null){
					
			     ob=new journalArtbean();
				 ob.setJcode(Journal_jcode);
				 ob.setJname(Journal_jname);
				 ob.setAtlno(Journal_atlno);
				 ob.setBvolno(Journal_bvolno);
				 ob.setTitle(Journal_atltitle);
				 ob.setAuthor(Journal_atlauthor);
				 ob.setVolno(Journal_volno);
				 ob.setIssueno(Journal_issueno);
				 ob.setIssueyear(Journal_issueyear);
				 ob.setIssuemonth(Journal_issuemonth);
				 ob.setPages(Journal_pages);
				 ob.setSubject(Journal_subject);
			     ob.setKeywords(Journal_key);
			     ob.setAbstracts(Journal_abst);
			     ob.setAtl_abstracts(Journal_abstract);
			     ob.setIssue_acc_no(Journal_issue_access_no);
			     
			     request.setAttribute("bean", ob);
				indexPage = "/Journal_Artical/index.jsp?check=UpdateCheck";
			}else{
				 ob=new journalArtbean();
				 ob.setJcode(Journal_jcode);
				 ob.setJname(Journal_jname);
				 ob.setAtlno(Journal_atlno);
				 ob.setBvolno(Journal_bvolno);
				 ob.setTitle(Journal_atltitle);
				 ob.setAuthor(Journal_atlauthor);
				 ob.setVolno(Journal_volno);
				 ob.setIssueno(Journal_issueno);
				 ob.setIssueyear(Journal_issueyear);
				 ob.setIssuemonth(Journal_issuemonth);
				 ob.setPages(Journal_pages);
				 ob.setSubject(Journal_subject);
			     ob.setKeywords(Journal_key);
			     ob.setAbstracts(Journal_abst);
			     ob.setAtl_abstracts(Journal_abstract);
			     ob.setIssue_acc_no(Journal_issue_access_no);
			     
			     int count=ss.getJnlAtlSave(ob);
				
				indexPage = "/Journal_Artical/index.jsp?jname="+Journal_jname+"&jcode="+Journal_jcode+"&issueaccessno="+Journal_issue_access_no+"&jvolno="+Journal_volno+"&issueno="+Journal_issueno+"&year="+Journal_issueyear+"&month="+Journal_issuemonth+"&check=SaveJournal";
			}
			dispatch(request, response, indexPage);
    		
		 }
			

		 
		 
			if(flag.equals("search")){
				log4jLogger.info("search==============="+flag);
				
				Journal_jcode=request.getParameter("jcode");
				journalbean ob1=new journalbean();
				
				ob1=ss.getJnlSearch(Integer.parseInt(request.getParameter("jcode")));
				if(ob1!=null){

				ob.setJcode(ob1.getJcode());
				ob.setJname(ob1.getJname());
				request.setAttribute("bean", ob);
				indexPage = "/Journal_Artical/index.jsp?check=SuccessJournal";
				}else{
					indexPage = "/Journal_Artical/index.jsp?check=FailureJournal";
				}
				dispatch(request, response, indexPage);
			}
		
		
			if(flag.equals("searchAtl")){
				log4jLogger.info("searchAtl==============="+flag);
				journalArtbean ob=new journalArtbean();
				
				ob=ss.getJnlSearchAtl(Integer.parseInt(request.getParameter("atlno")));
				request.setAttribute("bean",ob);
				
				if (ob.getAtlno()!=null){
					indexPage = "/Journal_Artical/index.jsp?check=SuccessJournalAtl";
				}else{
					indexPage = "/Journal_Artical/index.jsp?check=FailureJournal";
				}
				dispatch(request, response, indexPage);
			}
	
			if(flag.equals("delete"))				
							{
				log4jLogger.info("delete==============="+flag);
				journalArtbean ob=new journalArtbean();
				
				Journal_jcode=request.getParameter("atlno");
				 String Journal_jcodes=request.getParameter("jcode");
				 Journal_jname=request.getParameter("jname");
				 String issueaccessno=request.getParameter("issueaccessno");
				 String jvolno=request.getParameter("jvolno");
				 String issueno=request.getParameter("issueno");
				 String year=request.getParameter("year");
				 String month=request.getParameter("month");
				 int jcode=Integer.parseInt(Journal_jcode);
				 
				ob=ss.getJnlSearchAtl(Integer.parseInt(request.getParameter("atlno")));
				request.setAttribute("bean",ob);
				
				if (ob.getAtlno()!=null){
					int count=ss.getJnlDeleteAtl(Integer.parseInt(request.getParameter("atlno")));
					indexPage = "/Journal_Artical/index.jsp?jname="+Journal_jname+"&jcode="+Journal_jcodes+"&issueaccessno="+issueaccessno+"&jvolno="+jvolno+"&issueno="+issueno+"&year="+year+"&month="+month+"&check=DeleteJournal";
				}else{
					indexPage = "/Journal_Artical/index.jsp?check=FailureJournal";
				}
				dispatch(request, response, indexPage);
				
				
				
			}
		
	
			if(flag.equals("update")){
				log4jLogger.info("update==============="+flag);
				
				Journal_jcode=request.getParameter("jcode");
				Journal_jname=chkcode.getParam( request, "jname");				
				Journal_atlno=chkcode.getParam( request, "atlno");
				Journal_bvolno=chkcode.getParam( request, "bvolno");
				Journal_atltitle=chkcode.getParam( request, "title");
				Journal_atlauthor=chkcode.getParam( request, "author");
				Journal_volno=chkcode.getParam( request, "jvolno");
				Journal_issueno=chkcode.getParam( request, "issueno");
				Journal_issueyear=chkcode.getParam( request, "year");
				Journal_issuemonth=chkcode.getParam( request, "month");
				Journal_pages=chkcode.getParam( request, "page");
				Journal_subject=chkcode.getParam( request, "subject");
				Journal_key=chkcode.getParam( request, "key");
				Journal_abst=chkcode.getParam( request, "content");
				Journal_issue_access_no=chkcode.getParam( request, "issueaccessno");
	    		Journal_abstract=chkcode.getParam( request, "abstract");
				
				ob=new journalArtbean();
				 ob.setJcode(Journal_jcode);
				 ob.setJname(Journal_jname);
				 ob.setAtlno(Journal_atlno);
				 ob.setBvolno(Journal_bvolno);
				 ob.setTitle(Journal_atltitle);
				 ob.setAuthor(Journal_atlauthor);
				 ob.setVolno(Journal_volno);
				 ob.setIssueno(Journal_issueno);
				 ob.setIssueyear(Journal_issueyear);
				 ob.setIssuemonth(Journal_issuemonth);
				 ob.setPages(Journal_pages);
				 ob.setSubject(Journal_subject);
			     ob.setKeywords(Journal_key);
			     ob.setAbstracts(Journal_abst);
			     ob.setAtl_abstracts(Journal_abstract);
			     ob.setIssue_acc_no(Journal_issue_access_no);
				
				int count=ss.getJnlAtlUpdate(ob);
				indexPage = "/Journal_Artical/index.jsp?jname="+Journal_jname+"&jcode="+Journal_jcode+"&issueaccessno="+Journal_issue_access_no+"&jvolno="+Journal_volno+"&issueno="+Journal_issueno+"&year="+Journal_issueyear+"&month="+Journal_issuemonth+"&check=UpdateJournal";
				dispatch(request, response, indexPage);
			}
			
			

			 }  catch (Exception e) {
e.printStackTrace();
					}
			 finally
			 {
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
