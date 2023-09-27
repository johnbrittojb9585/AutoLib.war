package Common.businessutil.sms.schedule;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import Common.businessutil.BusinessServiceFactory;
import Common.businessutil.mail.MailService;
import Common.businessutil.search.SearchService;
import Common.businessutil.sms.SmsService;

public class GateJob implements Job
{
	private static Logger log4jLogger = Logger.getLogger(GateJob.class);
	SearchService ss = BusinessServiceFactory.INSTANCE.getSearchService();
	
	
	
	
	public void execute(JobExecutionContext context)
	throws JobExecutionException {		

		try 
		{			
			log4jLogger.info("================= start: [Automatic: Logout for User] ====================== " );
			
			//int count = ss.getAdvanceReminderSMS();
			
			int count=0;
			ss.getRegisterAllLogout();	
			
		    
			log4jLogger.info("================= End: [Automatic: LogOut] ====================== EMail ");
			
		}catch(Exception e)  {
			e.printStackTrace();
		}
		
	}
	
}

