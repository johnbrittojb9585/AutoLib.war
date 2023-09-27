package Common.businessutil.sms.schedule;

import java.util.Properties;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;
import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;

import org.quartz.impl.StdSchedulerFactory;

public class ScheduleAction implements ServletContextListener
{
	private static Logger log4jLogger = Logger.getLogger(ScheduleAction.class);
	
	public void contextDestroyed(ServletContextEvent arg0)
    {
		log4jLogger.info("Stopping Scheduler Application successfully");
    }
    
    public void contextInitialized(ServletContextEvent arg0) 
    {
    	log4jLogger.info("Initializing Application successfully");
       
       try{    	   
    	  
    	Properties properties=new Properties();
		properties.load(this.getClass().getClassLoader().getResourceAsStream("LocalStrings.properties"));
			
		String tbackup = properties.getProperty("autolib.backup.timings");	
		String tchekdb = properties.getProperty("autolib.chekdb.timings");
		String duemail = properties.getProperty("autolib.duemail.timings");
		String resduemail = properties.getProperty("autolib.resduemail.timings");
		String profilemail = properties.getProperty("autolib.profilemail.timings");
	//	String Gatetime = properties.getProperty("autolib.gateclose.timings");
		
		//String tadvdue = properties.getProperty("autolib.advdue.timings"); 
    	   
        // Job Type
    	   
       	JobDetail job = new JobDetail();
       	job.setName("backupDBName");
       	job.setJobClass(BackupJob.class);
     
       	JobDetail checkDBJob = new JobDetail();
       	checkDBJob.setName("checkDBJobName");
       	checkDBJob.setJobClass(StableConnectionSchedule.class);

       	
    	JobDetail sendduemail = new JobDetail();
    	sendduemail.setName("sendDueMailName");
    	sendduemail.setJobClass(SendDueReminderEmailJob.class);
    	
    	JobDetail sendprofilemail = new JobDetail();
    	sendprofilemail.setName("sendProfileMailName");
    	sendprofilemail.setJobClass(SendProfileEmailJob.class);
    	
    	JobDetail sendresduemail = new JobDetail();
    	sendresduemail.setName("sendResDueMailName");
    	sendresduemail.setJobClass(SendResDueReminderEmailJob.class);
       	
//    	JobDetail All_Logout = new JobDetail();
//    	All_Logout.setName("All_Logout");
//    	All_Logout.setJobClass(GateJob.class);
    	
    	
       	//JobDetail advDueJob = new JobDetail();
       	//advDueJob.setName("AdvDueSMSName");
       	//advDueJob.setJobClass(AdvanceDueSMSJob.class);
       
       	// Trigger

       	CronTrigger trigger = new CronTrigger();
       	trigger.setName("backupDBName");
       	trigger.setCronExpression(tbackup);
       	
       	CronTrigger checkDBTrigger = new CronTrigger();
       	checkDBTrigger.setName("checkDBJobName");
       	checkDBTrigger.setCronExpression(tchekdb);
    	
       	
    	CronTrigger dueSendMailTrigger = new CronTrigger();
    	dueSendMailTrigger.setName("sendDueMailName");
    	dueSendMailTrigger.setCronExpression(duemail);
    	
    	CronTrigger profileSendMailTrigger = new CronTrigger();
    	profileSendMailTrigger.setName("sendProfileMailName");
    	profileSendMailTrigger.setCronExpression(profilemail);
    	
    	CronTrigger resdueSendMailTrigger = new CronTrigger();
    	resdueSendMailTrigger.setName("sendResDueMailName");
    	resdueSendMailTrigger.setCronExpression(resduemail);
    	
//    	CronTrigger gatelogoutTrigger = new CronTrigger();
//    	gatelogoutTrigger.setName("All_Logout");
//    	gatelogoutTrigger.setCronExpression(Gatetime);
       
       	
       	//CronTrigger advDueTrigger = new CronTrigger();
       	//advDueTrigger.setName("AdvDueSMSNamexxx");
       	//advDueTrigger.setCronExpression(tadvdue);       	
       
       	//schedule it
       	Scheduler scheduler = new StdSchedulerFactory().getScheduler();
       	scheduler.start();
      	
       	
       	scheduler.scheduleJob(job, trigger);       	
     	scheduler.scheduleJob(checkDBJob, checkDBTrigger); 
     	scheduler.scheduleJob(sendduemail, dueSendMailTrigger);  
     	scheduler.scheduleJob(sendresduemail, resdueSendMailTrigger);
     	scheduler.scheduleJob(sendprofilemail, profileSendMailTrigger);
       	//scheduler.scheduleJob(advDueJob, advDueTrigger);
     //	scheduler.scheduleJob(All_Logout, gatelogoutTrigger);
       }
       catch(Exception e)
       {
           e.printStackTrace();
       }
     }   
    
   
}
