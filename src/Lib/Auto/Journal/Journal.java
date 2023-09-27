package Lib.Auto.Journal;
import Lib.Auto.Author.AuthorBean;
import Lib.Auto.Counter.Counter;
import Lib.Auto.EBook.EBookBean;
import Lib.Auto.Fine.Finebean;
import Lib.Auto.Journal.journalbean;
import Lib.Auto.PubSup.PubSupBean;
import Lib.Auto.Subject.subjectbean;
import Lib.Auto.Department.DepartmentBean;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.sql.DatabaseMetaData;
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
import javax.servlet.annotation.WebServlet;
import com.google.gson.Gson;

@WebServlet("/Journal/JournalServlet")


public class Journal extends HttpServlet {
	  private static Logger log4jLogger = Logger.getLogger(Journal.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String flag,Subject_Code,Doc_Type,Journal_jcode,Journal_jname,Journal_jissn,Journal_jfreq,Journal_jcountry,Journal_jlang,Journal_jdeli,Journal_jtype,Journal_jremarks,Journal_jpname,Journal_jdname,Journal_jsname,Journal_Val_Name;
	String Journal_Mas_Val_code,protocol;
	int Journal_Interface_code,Journal_Mas_code;
	int updateFlag;
	String term="";
	int dept_code=0, pub_code=0, sub_code=0;
	journalbean ob=new journalbean();
	journalbean newbean=new journalbean();
	   ResourceBundle rb =null;
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
            		
            		SerialcontrolService ss = BusinessServiceFactory.INSTANCE.getSerialcontrolService();
            		CalalogingService ss1 = BusinessServiceFactory.INSTANCE.getCalalogingService();
            		
            		response.setContentType("application/json");
        			
        			try{
        				String term = request.getParameter("jcode");
        				if(!term.equalsIgnoreCase(null) && !term.equalsIgnoreCase(""))
        	            {
        				//System.out.println("Data from ajax call " + term);
        							    
        				   ArrayList<journalbean> list = ss1.getJournalAutoCodeSearch(term);
        			       for(journalbean user: list){
        					//System.out.println(user.getJcode());
        				}       

        				String searchList = new Gson().toJson(list);
        							
        				response.getWriter().write(searchList);  
        							
        				//System.out.println("Json Value@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+searchList);
        	            }	
        		}catch(Exception e){
        			//e.printStackTrace();
        		}  		 


        		try{
        				String term = request.getParameter("jname");
        				
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
        			String term = request.getParameter("pname");
        			
        			if(!term.equalsIgnoreCase(null) && !term.equalsIgnoreCase(""))
                     {
        			//System.out.println("Data from ajax call " + term);
        						    
        			   ArrayList<PubSupBean> list = ss1.getJournalAutoPubSearch(term);
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

        		try{
        			String term = request.getParameter("sname");
        			
        			if(!term.equalsIgnoreCase(null) && !term.equalsIgnoreCase(""))
                     {
        			//System.out.println("Data from ajax call " + term);
        						    
        			   ArrayList<subjectbean> list = ss1.getJournalAutoSubjectSearch(term);
        		       for(subjectbean user: list){
        				//System.out.println(user.getName());
        			}       

        			String searchList = new Gson().toJson(list);
        						
        			response.getWriter().write(searchList);  
        						
        			//System.out.println("Json Value@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+searchList);
                     }	
        	}catch(Exception e){
        		//e.printStackTrace();
        	}    		 

        		try{
        			String term = request.getParameter("dname");
        			
        			if(!term.equalsIgnoreCase(null) && !term.equalsIgnoreCase(""))
                     {
        			//System.out.println("Data from ajax call " + term);
        						    
        			   ArrayList<DepartmentBean> list = ss1.getJournalAutoDeptSearch(term);
        		       for(DepartmentBean user: list){
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
			if(flag.equals("new"))
	   			{
				log4jLogger.info("new===========flag====="+flag);
				ob = new journalbean();
				ob=ss.getJnlMax();
				request.setAttribute("bean", ob);			
				indexPage = "/Journal/index.jsp?check=newJournal";
				dispatch(request, response, indexPage);
   				
				}

			if(flag.equals("search")){
				log4jLogger.info("search===========flag====="+flag);
				Journal_jcode=request.getParameter("jcode");
				
				ob=new journalbean();
				ob=ss.getJnlSearch(Integer.parseInt(Journal_jcode));
				if(ob!=null){
					request.setAttribute("bean", ob);
					indexPage = "/Journal/index.jsp?check=SuccessJournal";
				} else {
					indexPage = "/Journal/index.jsp?check=FailureJournal";
				}
				dispatch(request, response, indexPage);
				
				}
				
			
			if(flag.equals("delete")){
				log4jLogger.info("search===========flag====="+flag);
				Journal_jcode=request.getParameter("jcode");
				ob=ss.getJnlSearch(Integer.parseInt(Journal_jcode));
				
				if(ob!=null){
					request.setAttribute("bean", ob);
					indexPage = "/Journal/index.jsp?check=deleteCheck";
				} else {
					indexPage = "/Journal/index.jsp?check=FailureJournal";
				}
				dispatch(request, response, indexPage);
			}
			
			if(flag.equals("Confirmdete")){
				
				int Jnl_Interface_code=ss.getJnlInterface(Integer.parseInt(request.getParameter("jcode")));
				
				if(Jnl_Interface_code >0){
					indexPage = "/Journal/index.jsp?check=RefferdJournal";
				}else{
					
					int delete_code=ss.getJnlDelete(Integer.parseInt(request.getParameter("jcode")));
					indexPage = "/Journal/index.jsp?check=DeleteJournal";
				}
				dispatch(request, response, indexPage);
			}

			if(flag.equals("save"))
			 {
				log4jLogger.info("save===========flag====="+flag);
				Journal_jcode=request.getParameter("jcode");
				Journal_jname=chkcode.getParam( request, "jname");
				Journal_jissn=chkcode.getParam( request, "jissn");
				Journal_jfreq=chkcode.getParam( request, "jfreq");
				Journal_jcountry=chkcode.getParam( request, "jcountry");
				Journal_jlang=chkcode.getParam( request, "jlang");
				Journal_jdeli=chkcode.getParam( request, "jdeli");
				Journal_jtype=chkcode.getParam( request, "jtype");
				Journal_jremarks=chkcode.getParam( request, "jremarks");
				Journal_jpname=chkcode.getParam( request, "pname");
				Journal_jdname=chkcode.getParam( request, "dname");
				Journal_jsname=chkcode.getParam( request, "sname");
				Doc_Type=request.getParameter("doc");
				
				ob=new journalbean();
				
				 ob.setJcode(Journal_jcode);
				 ob.setJname(Journal_jname);
				 ob.setJissn(Journal_jissn);
				 ob.setJfreq(Journal_jfreq);
				 ob.setJcountry(Journal_jcountry);
				 ob.setJlang(Journal_jlang);
				 ob.setJdeli(Journal_jdeli);
				 ob.setJtype(Journal_jtype);
				 ob.setJremarks(Journal_jremarks);
				 ob.setJpname(Journal_jpname);
				 ob.setJdname(Journal_jdname);
				 ob.setJsname(Journal_jsname);
			     ob.setDoc_Type(Doc_Type);
			     
			     newbean=new journalbean();
			     newbean=ss.getJnlSearch(Integer.parseInt(Journal_jcode));
					if(newbean!=null){
						newbean.setJcode(Journal_jcode);
						newbean.setJname(Journal_jname);
						newbean.setJissn(Journal_jissn);
						newbean.setJfreq(Journal_jfreq);
						newbean.setJcountry(Journal_jcountry);
						newbean.setJlang(Journal_jlang);
						newbean.setJdeli(Journal_jdeli);
						newbean.setJtype(Journal_jtype);
						newbean.setJremarks(Journal_jremarks);
						newbean.setJpname(Journal_jpname);
						newbean.setJdname(Journal_jdname);
						newbean.setJsname(Journal_jsname);
						newbean.setDoc_Type(Doc_Type);
						request.setAttribute("bean", newbean);
						indexPage = "/Journal/index.jsp?check=UpdateCheck";
					} else {
						
						 ob.setJcode(Journal_jcode);
						 ob.setJname(Journal_jname);
						 ob.setJissn(Journal_jissn);
						 ob.setJfreq(Journal_jfreq);
						 ob.setJcountry(Journal_jcountry);
						 ob.setJlang(Journal_jlang);
						 ob.setJdeli(Journal_jdeli);
						 ob.setJtype(Journal_jtype);
						 ob.setJremarks(Journal_jremarks);
						 ob.setJpname(Journal_jpname);
						 ob.setJdname(Journal_jdname);
						 ob.setJsname(Journal_jsname);
					     ob.setDoc_Type(Doc_Type);
					     
					     int count=ss.getJnlSave(ob);
					     
						indexPage = "/Journal/index.jsp?check=SaveJournal";
					}
			     
					dispatch(request, response, indexPage);
			 }
			
			
			if(flag.equals("update")){
				log4jLogger.info("update===========flag====="+flag);
				
				Journal_jcode=request.getParameter("jcode");
				Journal_jname=chkcode.getParam( request, "jname");
				Journal_jissn=chkcode.getParam( request, "jissn");
				Journal_jfreq=chkcode.getParam( request, "jfreq");
				Journal_jcountry=chkcode.getParam( request, "jcountry");
				Journal_jlang=chkcode.getParam( request, "jlang");
				Journal_jdeli=chkcode.getParam( request, "jdeli");
				Journal_jtype=chkcode.getParam( request, "jtype");
				Journal_jremarks=chkcode.getParam( request, "jremarks");
				Journal_jpname=chkcode.getParam( request, "pname");
				Journal_jdname=chkcode.getParam( request, "dname");
				Journal_jsname=chkcode.getParam( request, "sname");
				Doc_Type=chkcode.getParam( request, "doc");
				
				 ob.setJcode(Journal_jcode);
				 ob.setJname(Journal_jname);
				 ob.setJissn(Journal_jissn);
				 ob.setJfreq(Journal_jfreq);
				 ob.setJcountry(Journal_jcountry);
				 ob.setJlang(Journal_jlang);
				 ob.setJdeli(Journal_jdeli);
				 ob.setJtype(Journal_jtype);
				 ob.setJremarks(Journal_jremarks);
				 ob.setJpname(Journal_jpname);
				 ob.setJdname(Journal_jdname);
				 ob.setJsname(Journal_jsname);
			     ob.setDoc_Type(Doc_Type);
				
				 int count=ss.getJnlUpdate(ob);
				
				indexPage = "/Journal/index.jsp?check=UpdateJournal";
				dispatch(request, response, indexPage);
			}

			if((flag.equals("Nam"))||(flag.equals("Dept"))||(flag.equals("Pub"))||(flag.equals("Sub"))){
				
				List AuthorArrylist = new ArrayList();
				newbean=new journalbean();
				if(request.getParameter("name")!=null){
					newbean.setJname(request.getParameter("name"));
					newbean.setJremarks(request.getParameter("flag"));
					ob=new journalbean();
					ob=ss.getJnlSearchName(newbean);
					request.setAttribute("bean", ob);
					indexPage = "/Journal/search.jsp?check=ok&flag="+flag+"&nam="+nm+"";
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

	public void dispatch(HttpServletRequest request,
			HttpServletResponse response, String indexPage)
			throws ServletException, IOException {
		RequestDispatcher dispatch = request.getRequestDispatcher(indexPage);
		dispatch.forward(request, response);
	}

}
