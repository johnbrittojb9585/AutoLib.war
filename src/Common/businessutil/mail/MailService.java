package Common.businessutil.mail;

import java.util.List;

import Lib.Auto.Suggestion.SuggestionBean;

public interface MailService
{
	public int getSendEmail(String[] toAddress,String subject,String message);
	public int getForgetPassword(String email);	
	
	public List getDueReminderList(String filterQuery);
	public int getDueReminderMail(String code);
	
	public int getReserveReminderMail(String mcode,String accno);
	public int getSuggestionMail(SuggestionBean ob);
	public int getSugReqMail(SuggestionBean ob);
	public int getProfileMail();
}