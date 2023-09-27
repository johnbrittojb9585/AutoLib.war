package Lib.Auto.Holiday;
import Lib.Auto.Fine.Fine;
import Lib.Auto.Fine.Finebean;
import Lib.Auto.Holiday.Holidaybean;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
//import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;
import java.util.MissingResourceException;
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
import Common.businessutil.admin.AdminService;


public class Holiday extends HttpServlet implements Serializable {
	 private static Logger log4jLogger = Logger.getLogger(Holiday.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String flag,all,Holiday_cd,Holiday_savecode,Holiday_savename,Holiday_savedesc,Holiday_savemail,Holiday_Val_Name;
	String  Holiday_Mas_Val_code;
	int Holiday_Interface_code,Holiday_Mas_code;
	String holiday_date, holiday_day,holiday_remarks;
	int updateFlag;
	Holidaybean ob=new Holidaybean();
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
				            AdminService ss = BusinessServiceFactory.INSTANCE.getAdminService();
							ArrayList ser=new ArrayList();
							flag=request.getParameter("flag");
							all=request.getParameter("all");
			

					if(flag.equals("list"))
					{
						log4jLogger.info("start===========Fine Search=====");
						List FineArrylist = new ArrayList();
		                    FineArrylist=ss.getHolidaySearch();
					
							request.setAttribute("serarch", FineArrylist);
					        indexPage = "/Holiday/index.jsp?check=searchHoliday";
					    	dispatch(request, response, indexPage);
						
						
					}
					if(flag.equals("delete")){
						log4jLogger.info("start===========delete=====");
						if(all.equals("all")){
							
							int count= ss.getHolidayDeleteAll();
							indexPage = "/Holiday/index.jsp?check=deleteHoliday";
					    	
							
						}else{
							
							String delete_date=getDate(request.getParameter("leavefrom"));

							int count= ss.getHolidayDelete(delete_date);
							
							if(count>0){
								indexPage = "/Holiday/index.jsp?check=deleteHoliday";
							}else{
								indexPage = "/Holiday/index.jsp?check=norecord";
							}
						}
						dispatch(request, response, indexPage);
					}


				


						if(flag.equals("save"))
						{
						 String Ddate="";
						 String ddt=getDate(request.getParameter("leavefrom"));
						 String idt=getDate(request.getParameter("leaveto"));
						
						 String remark=getParam(request, "remarks");
						
								String no_of_days="";
								
								int no=1;
								
								ob=new Holidaybean();
								
								ob.setFrom(ddt);
								ob.setTo(idt);
								ob.setRemarks(remark);
								
								int count=ss.getHolidaySave(ob);
								
								if(count>0){
									indexPage = "/Holiday/index.jsp?check=Insufficient";
								}else{
									indexPage = "/Holiday/index.jsp?check=saveHoliday";
								}
								
								dispatch(request, response, indexPage);
						}



			 } catch (Exception e) {
					
					}
		    catch (Throwable theException) {
			
		   }
		    finally{
		    	try{
					
				}catch(Exception e){
				e.printStackTrace();
				}
		    }

	}

	String getDate(String datechk) {
       java.util.StringTokenizer stz_is=new java.util.StringTokenizer(datechk,"-");
		    int bid=Integer.parseInt(stz_is.nextToken());
		     int bim=Integer.parseInt(stz_is.nextToken());
		    int biy=Integer.parseInt(stz_is.nextToken());
	     	   String bissue_m=new Integer(bim).toString();
				    if(bissue_m.length()==1)
				    bissue_m="0"+bissue_m;
					String bissue_d=new Integer(bid).toString();
					if(bissue_d.length()==1)
				    bissue_d="0"+bissue_d;
					String BISSDATE=biy+"-"+bissue_m+"-"+bissue_d;
	return BISSDATE;
  }
  String getholiday(String holiday) {
	  //String Ddate=holiday.substring(0,holiday.length()-9);
     java.util.StringTokenizer stz_h=new java.util.StringTokenizer(holiday,"-");
		    int hy=Integer.parseInt(stz_h.nextToken());
		     int hm=Integer.parseInt(stz_h.nextToken());
		    int hd=Integer.parseInt(stz_h.nextToken());
	     	   String hissue_m=new Integer(hm).toString();
				    if(hissue_m.length()==1)
				    hissue_m="0"+hissue_m;
					String hissue_d=new Integer(hd).toString();
					if(hissue_d.length()==1)
				    hissue_d="0"+hissue_d;
					String HOLIDAYDATE=hissue_d+"-"+hissue_m+"-"+hy;
	return HOLIDAYDATE;
  }
	String getParam(javax.servlet.http.HttpServletRequest req, String paramName) {
    String param = req.getParameter(paramName);
    if ( param == null || param.equals("") ) return "";
	param = replace(param,"&amp;","&");
    param = replace(param,"&lt;","<");
    param = replace(param,"&gt;",">");
    param = replace(param,"&amp;lt;","<");
    param = replace(param,"&amp;gt;",">");
    param=  param.replace('"',' ');
    param = replace(param, "'", "''");
   java.util.StringTokenizer st=new java.util.StringTokenizer(param,"\\");
   String s="";
   while(st.hasMoreElements())
	{
	s=s+st.nextElement();
	}
    param=s;
	return param;
  }
    private String replace(String str, String pattern, String replace) {
    if (replace == null) {
      replace = "";
    }
    int s = 0, e = 0;
    StringBuffer result = new StringBuffer((int) str.length()*2);
    while ((e = str.indexOf(pattern, s)) >= 0) {
      result.append(str.substring(s, e));
      result.append(replace);
      s = e + pattern.length();
    }
    result.append(str.substring(s));
    return result.toString();
  }
	
	
    public void dispatch(HttpServletRequest request,
			HttpServletResponse response, String indexPage)
			throws ServletException, IOException {
		RequestDispatcher dispatch = request.getRequestDispatcher(indexPage);
		dispatch.forward(request, response);
	}

	/** 
	 * Instance variable for SQL statement property
	 */
		
	
}
