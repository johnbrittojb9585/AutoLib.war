
package Lib.Auto.Backup;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

import org.apache.log4j.Logger;

import Common.RecentBackup;
import Common.Security;
import Common.businessutil.BusinessServiceFactory;
import Common.businessutil.calaloging.CalalogingService;

import javax.servlet.annotation.WebServlet;

import com.google.gson.Gson;

@WebServlet("/Backup/BackupServlet")

public class Backup extends HttpServlet implements Serializable {
	/**
	 *  @author Prabaji & John.A
	 */
	 private static Logger log4jLogger = Logger.getLogger(Backup.class);

	private static final long serialVersionUID = -8906987252709033668L;

	String protocol = "", flag = "";
	String term="";
	String Auth_Name = "", SameCode = "";
	String sql="";
	String nam="";
    String filename="";
	int Author_Interface_code=0;
	int Author_Mas_code=0;
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
			
			//flag = request.getParameter("flag");
			
		try {		
			log4jLogger.info("==========================Backup Start ========================");
			HttpSession session = request.getSession(true);
			String res = Security.checkSecurity(1, session, response, request);		
			if(res.equalsIgnoreCase("Failure"))
			{
				response.sendRedirect("/AutoLib/Tree/sessiontimeout.jsp");  
				return;
			}
			int count=0;
		
			String filePath="",mysqlPath="",uname="",pwd;
			Properties properties=new Properties();
			properties.load(this.getClass().getClassLoader().getResourceAsStream("LocalStrings.properties"));
			
			filePath = properties.getProperty("autolib.backup.filepath");	
			mysqlPath = properties.getProperty("autolib.backup.mysqlpath");	
			uname = properties.getProperty("autolib.mysql.userName");	
			pwd = properties.getProperty("autolib.mysql.passWord");	
			
			
		    Date date = new Date();
		    String name = date.toString().substring(date.toString().length()-4,date.toString().length())+date.toString().substring(4,7)+date.toString().substring(8,10)+"_"+date.toString().substring(11,13)+date.toString().substring(14,16)+date.toString().substring(17,19);
		    Runtime runtime = Runtime.getRuntime();
		    
		    //File backupFile = new File("D:/Java Librarry 2013/AutoBackup/AutoLibBackup_"+name+".sql");
		    File backupFile = new File(filePath+"AutoLibBackup_"+name+".sql");
		    FileWriter fw = new FileWriter(backupFile);
		    //Process child = runtime.exec("C:/Program Files/MySQL/MySQL Server 5.0/bin/mysqldump --user=root --password=admin --lock-all-tables --opt autolib");
		    Process child = runtime.exec(mysqlPath+"mysqldump --user="+uname+" --password="+pwd+" --lock-all-tables --opt autolib");
		    InputStreamReader irs = new InputStreamReader(child.getInputStream());
		    BufferedReader br = new BufferedReader(irs);

		    String line;
		    while( (line=br.readLine()) != null )  
		    {
		        fw.write(line + "\n");
		    }

		    fw.close();
		    irs.close();
		    br.close();
		    count = 1;
		    
		    RecentBackup backup = new RecentBackup();
		    backup.recentBakupManual();
		    log4jLogger.info("==========================Backup Completed!! ========================");
		    if(count>0){
		    indexPage = "/Backup/index.jsp?message=success";
			dispatch(request, response, indexPage);
		    	
		    }
		    else if(count ==0){
		    	  indexPage = "/Backup/index.jsp?message=failed";
					dispatch(request, response, indexPage);
				    	
		    }
			
		}
		catch(Exception e){
			
			 
			// indexPage = "/Backup/index.jsp?message=failed";             //john.A       v22.01   (Modified jsp to servlet & using flag req,res)
				try {
					indexPage = "/Backup/index.jsp?message=failed";
					dispatch(request, response, indexPage);
				} catch (IOException ex) {
					
					ex.printStackTrace();
					 log4jLogger.error("==========================Error!! ========================");
				}
			//e.printStackTrace();
	}
		
		
		
	}

	public void dispatch(HttpServletRequest request,
			HttpServletResponse response, String indexPage)
			throws ServletException, IOException {
		RequestDispatcher dispatch = request.getRequestDispatcher(indexPage);
		dispatch.forward(request, response);
	}

	/* (non-Javadoc)
	 * @see Lib.Auto.Author.Author_Interface#newRecord()
	 */
	
	
}
