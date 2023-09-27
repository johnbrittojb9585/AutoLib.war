package Common.businessutil.sms.schedule;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import Common.businessutil.BusinessServiceFactory;
import Common.businessutil.sms.SmsService;

public class AdvanceDueSMSJob implements Job
{
	private static Logger log4jLogger = Logger.getLogger(AdvanceDueSMSJob.class);
	SmsService ss=BusinessServiceFactory.INSTANCE.getSmsService();
	
	
	public void execute(JobExecutionContext context)
	throws JobExecutionException {		

		try 
		{			
			log4jLogger.info("================= start: [Automatic:Advance Due Reminder for User] ====================== " );
			
			int count = ss.getAdvanceReminderSMS();
		    
			log4jLogger.info("================= End: [Automatic:Advance Due Reminder for User] ====================== SMS SENT TO:" +count);
			
		}catch(Exception e)  {
			e.printStackTrace();
		}
		
	}
	
}
