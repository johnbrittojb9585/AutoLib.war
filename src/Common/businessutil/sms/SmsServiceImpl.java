package Common.businessutil.sms;

public class SmsServiceImpl implements SmsService
{
	private SmsDao smsDao;
	
	public  SmsServiceImpl(){
		
	}
	
	public int getSendSMS(String mobino,String message){
		return smsDao.findSendSMS(mobino,message);
	}
	
	public int getDueReminderSMS(String code)
	{
		return smsDao.findDueReminderSMS(code);
	}
	
	public int getAdvanceReminderSMS()
	{
		return smsDao.findAdvanceReminderSMS();
	}
	
	
	public SmsDao getSmsDao(){
		return smsDao;
	}
	
	public void setSmsDao(SmsDao smsDao){
		this.smsDao=smsDao;
	}
	
	
}