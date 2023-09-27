package Common.businessutil.sms.schedule;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import Common.businessutil.BusinessServiceFactory;
import Common.businessutil.admin.AdminService;


public class StableConnectionSchedule implements Job
{
	private static Logger log4jLogger = Logger.getLogger(StableConnectionSchedule.class);
	AdminService ss = BusinessServiceFactory.INSTANCE.getAdminService();
	
	public void execute(JobExecutionContext context) throws JobExecutionException {
		
		try
		{
            //log4jLogger.info("================= start: [Automatic:StableConnectionSchedule] ====================== " );
			
			int count = ss.getBranchCode("NIL");
		    
			log4jLogger.info("======== [Check:ActiveConnection is true] ========:" +count);  /** Database connection was closed when the DB is going to idle state after 8 hours.*/
			
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
}