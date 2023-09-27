package Common.businessutil.sms;


public interface SmsDao
{
	public int findSendSMS(String mobino,String messgae);
		
    public int findDueReminderSMS(String code);
	
	public int findAdvanceReminderSMS();	
}