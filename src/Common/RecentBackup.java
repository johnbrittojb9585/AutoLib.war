package Common;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

import org.apache.log4j.Logger;

import Lib.Auto.Backup.Backup;

public class RecentBackup {
	 private static Logger log4jLogger = Logger.getLogger(RecentBackup.class);
		public void recentBakupManual() throws IOException{
			
			
			String filePath="",mysqlPath="",uname="",pwd;
			Properties properties=new Properties();
			properties.load(this.getClass().getClassLoader().getResourceAsStream("LocalStrings.properties"));
			
			filePath = properties.getProperty("contents.upload.filepath");	
			mysqlPath = properties.getProperty("autolib.backup.mysqlpath");	
			uname = properties.getProperty("autolib.mysql.userName");	
			pwd = properties.getProperty("autolib.mysql.passWord");	
			
		//String filepath ="C://Program Files (x86)/MySQL/MySQL Server 5.0/bin/";
		//String backup = "E://AutoLib_Files/Recent Backup/";
		//String uname ="root";
		//String pwd = "citlib";
		
		try{
			log4jLogger.info("==========================Recent Backup Start ========================");
			
		File f = new File(filePath);    
		
		if(f.mkdir()){
			
			log4jLogger.info("==========================Contents folder created Successfully========================");
		}
		File f1 = new File(f.toString()+"/Recent_Backup/");    
			if(f1.mkdir()){
				log4jLogger.info("==========================Recent_Backup folder created Successfully========================");
			}
		f1.toString().concat("/").replaceAll("'/'", "'\'");
		File file = new File(f1+"/AutoLibRecent.sql");		
		FileWriter fw = new FileWriter(file);		
		Runtime runtime =Runtime.getRuntime();		
		Process child =runtime.exec(mysqlPath+"mysqldump --user="+uname+" --password="+pwd+" --lock-all-tables --opt autolib");
		InputStreamReader isr = new InputStreamReader(child.getInputStream());		
		BufferedReader br = new BufferedReader(isr);		
		String line;		
		while( (line=br.readLine()) != null )  
	    {
	        fw.write(line + "\n");
	       
	    }
	    fw.close();
	  
	    isr.close();
	    br.close();
	    log4jLogger.info("==========================Recent Backup Completed!! ========================");
		}catch(Exception ex)
		{
			ex.printStackTrace();
			log4jLogger.error("==========================Error========================");
		}
		
	}
		
}

