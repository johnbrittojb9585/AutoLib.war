package Common.businessutil.sms;


public interface SmsService
{
	public int getSendSMS(String mobino,String message);
	
    public int getDueReminderSMS(String code);
	
	public int getAdvanceReminderSMS();
	
}