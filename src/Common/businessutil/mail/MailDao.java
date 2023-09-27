package Common.businessutil.mail;

import java.util.List;

import Lib.Auto.Suggestion.SuggestionBean;

public interface MailDao
{
	public int findSendEmail(String[] toaddress,String subject,String Messgae);
	
	public int findForgetPassword(String email);
	
	public List findDueReminderList(String filterQuery);
	
	public int findDueReminderMail(String code);
	
	public int findReserveReminderMail(String mcode,String accno);
	
	public int findSuggestionReply(SuggestionBean ob);
	
	public int findSugReqMail(SuggestionBean ob);
	
	public int findProfileMail();
}