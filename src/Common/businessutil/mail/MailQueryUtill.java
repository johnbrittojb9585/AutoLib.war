package Common.businessutil.mail;

public interface MailQueryUtill{

/*private static final String SMTP_HOST = "smtp.gmail.com";  
private static final String FROM_ADDRESS = "autolibsoftware@gmail.com";  
private static final String PASSWORD = "libcampus";  
private static final String FROM_NAME = "KARUPPAIAH.R"; */
	
	//public static final String Regards_Text = "With Regards<br> Librarian, BPDC.";
	public static final String COMPANY_NAME = "Panimalar College Of Nursing";
	public static final String COMPANY_ADD = "VARADHARAJAPURAM, POONAMALLEE, CHENNAI 600123";
	
	public static final String MAIL_UTIL = "select * from mail_util";
	public static final String Profile_Message_Text = "The folloing resource are matched with your profile";
	public static final String Profile_Subject_Text = "Profile Matched Books - "+COMPANY_NAME;
	public static final String Regards_Text = "With Regards<br> Librarian";
	public static final String Company_Text = COMPANY_NAME;	
	
	public static final String Issue_Subject_Text = "Issue - "+Company_Text;
	public static final String Issue_Message_Text = "The following book(s) has been issued to you, ";
			
	public static final String Return_Subject_Text = "Return - "+Company_Text;
	public static final String Return_Message_Text = "The following book(s) has been returned, ";
	
	public static final String Renew_Subject_Text = "Renewal - "+Company_Text;
	public static final String Renew_Message_Text = "The following book(s) has been renewed, ";
	
	public static final String Reserve_Subject_Text = "Reservation - "+Company_Text;
	public static final String Reserve_Message_Text = "The following book(s) has been reserved ";
	
	public static final String Forgot_Subject_Text = "Reset Password - "+Company_Text;
	public static final String Forgot_Message_Text = "Kindly reset your password.";
	public static final String Suggestion_Subject_Text ="Suggestion-"+Company_Text;
	public static final String Duereminder_Subject_Text = "Library Due Reminder - "+Company_Text;
	public static final String Duereminder_Message_Text = "Please return the following book(s) immediately. Fine amount will be collected after the due date as per library policy. Kindly ignore this reminder if you have already returned the book(s).";
	
	public static final String Reservereminder_Subject_Text = "Library Reserve Reminder - "+Company_Text;
	public static final String Reservereminder_Message_Text = "The following reserved book is available in Library , You can borrow this book within 2 days otherwise we cancel your reservation. ";
	
    public static final String Duereminder_SMS_MessageText = "Please return the following book(s) which are due on (Dt:AcsNo) ";	
	public static final String AdvanceDuereminder_SMS_MessageText = "Reminder-The following book(s) are due on (Dt:AcNo) ";
}