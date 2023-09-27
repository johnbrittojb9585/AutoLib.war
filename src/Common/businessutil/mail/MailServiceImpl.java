package Common.businessutil.mail;

import java.util.ArrayList;
import java.util.List;

import Common.Security;
import Common.Security_Counter;
import Lib.Auto.Email_Reminder.ReminderBean;
import Lib.Auto.Suggestion.SuggestionBean;


public class MailServiceImpl implements MailService
{
	private MailDao mailDao;
	
	public  MailServiceImpl(){
		
	}
	
	
	
	public int getSendEmail(String[] toaddress,String subject,String message){
		return mailDao.findSendEmail(toaddress,subject,message);
	}
	
	public int getForgetPassword(String Email) {
		return mailDao.findForgetPassword(Email);
	}
	
	public List getDueReminderList(String filterQuery)
	{
		List results=mailDao.findDueReminderList(filterQuery);
		
		List finalResults = null;		 
		if(results != null)
		{
			finalResults = new ArrayList();
			for(int i = 0; i < results.size(); i++)
			{				
				Object[] obj = (Object[])results.get(i);
				ReminderBean prt= new ReminderBean();
				prt.setMcode(obj[0].toString());
				prt.setMname(obj[1].toString());
				prt.setMemail(obj[2].toString());
				prt.setMphone(obj[3].toString());
				prt.setDueDate(Security.getdate(obj[4].toString()));			
				
				finalResults.add(prt);
			}
		}
		return finalResults;
	
	}
	
	public int getDueReminderMail(String code)
	{
			return mailDao.findDueReminderMail(code);
	
	}
	
	
	
	public int getReserveReminderMail(String mcode,String accno)
	{
			return mailDao.findReserveReminderMail(mcode,accno);
	
	}
	
	public int getSuggestionMail(SuggestionBean ob)
	{
			return mailDao.findSuggestionReply(ob);
	
	}
	public int getSugReqMail(SuggestionBean ob)
	{
			return mailDao.findSugReqMail(ob);
	}
	
	public MailDao getMailDao(){
		return mailDao;
	}
	
	public void setMailDao(MailDao mailDao){
		this.mailDao=mailDao;
	}
	public int getProfileMail()
	{
			return mailDao.findProfileMail();
	
	}
	
}