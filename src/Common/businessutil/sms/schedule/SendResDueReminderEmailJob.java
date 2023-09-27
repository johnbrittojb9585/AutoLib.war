package Common.businessutil.sms.schedule;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import Common.businessutil.BusinessServiceFactory;
import Common.businessutil.mail.MailService;

public class SendResDueReminderEmailJob implements Job
{
	private static Logger log4jLogger = Logger.getLogger(SendDueReminderEmailJob.class);
	MailService ss=BusinessServiceFactory.INSTANCE.getMailService();
		
	public void execute(JobExecutionContext context)
	throws JobExecutionException 
	{		

		try 
		{			
			log4jLogger.info("================= start: [Automatic: Reservation Due Reminder for User] ====================== " );
			
			//int count = ss.getAdvanceReminderSMS();
			
			int count=0;
			ss.getReserveReminderMail("ALL","");	
			
		    
			log4jLogger.info("================= End: [Automatic: Reservation Due Reminder for User] ====================== EMail SENT TO:" +count);
			
		}catch(Exception e)  {
			e.printStackTrace();
		}
		
	}
}
