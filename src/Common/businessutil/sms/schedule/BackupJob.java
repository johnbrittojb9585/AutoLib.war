package Common.businessutil.sms.schedule;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import Common.RecentBackup;

public class BackupJob implements Job
{
	private static Logger log4jLogger = Logger.getLogger(BackupJob.class);
	
	
	public void execute(JobExecutionContext context)
	throws JobExecutionException {		

		try 
		{
			log4jLogger.info("================= start: [Automatic Backup taken] ====================== " );
			
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
		    
		    
		    RecentBackup backup = new RecentBackup();
		    backup.recentBakupManual();
		    String line;
		    while( (line=br.readLine()) != null )  
		    {
		        fw.write(line + "\n");
		    }

		    fw.close();
		    irs.close();
		    br.close();
		    
		    log4jLogger.info("================= end: [Automatic Backup Completed] ====================== " );
		    
		}catch(Exception e)  {
			e.printStackTrace();
		}
		
	}
	
}
