package Common.businessutil.mail;


import java.io.UnsupportedEncodingException;  
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;  
import java.util.logging.Level;  
//import java.util.logging.Logger;  


import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;  
import javax.mail.Message;  
import javax.mail.MessagingException;  
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;  
import javax.mail.Session;  
import javax.mail.Transport;  
import javax.mail.internet.InternetAddress; 
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage; 
import javax.mail.internet.MimeMultipart;



import Common.DataQuery;
import Common.Security;
import Common.Security_Counter;
import Lib.Auto.Suggestion.SuggestionBean;

import org.apache.log4j.Logger;
import org.hibernate.SQLQuery;



import com.library.autolib.portal.dbconnectionutil.BaseDBConnection;

public class MailDaoImpl extends BaseDBConnection implements MailDao 
{
	
	private static Logger log4jLogger = Logger.getLogger(MailDaoImpl.class);
	
	int a=0;	 
	
	private static final String SMTP_HOST = "smtp.gmail.com";  
    private static final String FROM_ADDRESS = "autolibsoftware@gmail.com";
    private static final String PASSWORD = "rbhxnscjdpepiuyl"; 
    private static final String FROM_NAME = "Librarian,";

    java.sql.Connection con = null;
	java.sql.PreparedStatement Prest = null;
	java.sql.PreparedStatement Prest1 = null;
	java.sql.ResultSet rs = null;
	java.sql.ResultSet rs1 = null;
	java.sql.Statement st = null;
	
       
 public int findSendEmail(String[] TO_ADDRESS,String subject,String message) {
	
	 
	 log4jLogger.info("=============== Inside MailDaoImpl ========");
	         
     try {  
    	 
         Properties props = new Properties();         
                 
         props.put("mail.transport.protocol", "smtp");
         props.put("mail.smtp.host", "smtp.gmail.com");  
         props.put("mail.smtp.starttls.enable", "true");
         props.put("mail.smtp.auth", "true");
         
         Session session = Session.getInstance(props, new SocialAuth());  
         Message msg = new MimeMessage(session);  

         InternetAddress from = new InternetAddress(FROM_ADDRESS, FROM_NAME);  
         
         msg.setFrom(from);  // From Address

         InternetAddress[] toAddresses = new InternetAddress[TO_ADDRESS.length];
         
         for (int i = 0; i < TO_ADDRESS.length; i++)
         {
        	 toAddresses[i] = new InternetAddress(TO_ADDRESS[i]);
        	 
        	 log4jLogger.info("==== MAILID ==== "+toAddresses[i]);
        	 
         }
                    
         msg.setRecipients(Message.RecipientType.TO, toAddresses);        // Set To: header field of the header.

         msg.setSubject(subject);  // Set Subject: header field
         
                  
         //msg.setContent(message, "text/plain");
         msg.setContent(message, "text/html; charset=utf-8");   
         Transport transport=session.getTransport("smtp");
         transport.connect();
         log4jLogger.info("Transport connect successfully....");                          
         Transport.send(msg);
         transport.close();
         
         a=1;  
     } catch (UnsupportedEncodingException ex) {  
         a=0;

     } catch (MessagingException ex) {  
         a=0;  
     }  
              
		return a;
	} 
 
 
 class SocialAuth extends Authenticator {  
     
     @Override  
     protected PasswordAuthentication getPasswordAuthentication() {  

         return new PasswordAuthentication(FROM_ADDRESS, PASSWORD);  

     }  
 }
 
 public int findSendEmailNew(String toAddresses,String subject,String message) {
	 
	 
	 log4jLogger.info("=============== Inside findSendEmailNew ========");
	         
     try {  
    	 
         Properties props = new Properties();         
                 
         props.put("mail.transport.protocol", "smtp");
         props.put("mail.smtp.host", "smtp.gmail.com");  
         props.put("mail.smtp.starttls.enable", "true");
         props.put("mail.smtp.auth", "true");

         Session session = Session.getInstance(props, new SocialAuth());  
         Message msg = new MimeMessage(session);  

         InternetAddress from = new InternetAddress(FROM_ADDRESS, FROM_NAME);  
         
         msg.setFrom(from);  // From Address
                   
         msg.addRecipient(Message.RecipientType.TO, new InternetAddress(toAddresses));   

         msg.setSubject(subject);  // Set Subject: header field
                                  
         msg.setContent(message, "text/html; charset=utf-8");
         
         Transport transport=session.getTransport("smtp");
         transport.connect();
         log4jLogger.info("Transport connect successfully....");                          
         Transport.send(msg);
         transport.close();

         a=1;  
     } catch (UnsupportedEncodingException ex) {  
         a=0;

     } catch (MessagingException ex) {        
         a=0;  
     }  
              
		return a;
	} 
 
 
 
 
 
 public int findForgetPassword(String email)
 {
	 log4jLogger.info("==== Inside Forgot Password --> MailDaoImpl ====");
	 String mcode="",mpass="",emailid="",name="";
	 int count=0;
	 
	 try {
		 
	   con=getSession().connection();	 
	   Prest = con.prepareStatement(DataQuery.EMAILCheck_STRING);
	   Prest.setString(1, email);
	   rs=Prest.executeQuery();	   
	   
	if (rs.next()) {		
		
		   mcode =rs.getString("member_code");
		   name=rs.getString("member_name");
		   mpass =rs.getString("login_password");
		   emailid=rs.getString("member_email");	  		   
	   }
	else
	{
		count=0;
	}
	   	   
	   if(!emailid.isEmpty() && !emailid.equals(""))
	   {	
		   
	     String[] strArray = new String[] {emailid};     
	     
	     String namedQuery=MailQueryUtill.Forgot_Message_Text;
		 StringBuffer sb = new StringBuffer();
		 
		    sb.append("Dear "+name+",<br><br>");    			
			sb.append(namedQuery);
			
			sb.append("<br>Login ID  :"+mcode+"<br>Password :"+mpass+"<br>");				
			sb.append("<br><br>"+MailQueryUtill.Regards_Text);
			
			count=findSendEmail(strArray,MailQueryUtill.Forgot_Subject_Text,sb.toString());

	   }  
	
	 }catch(Exception e) {
		 e.printStackTrace();
	 }finally {
			try{
				if(con!=null){
					con.close();
					con=null;
				}			
				if(rs!=null){
					rs.close();
					rs=null;
				}
				if(Prest!=null){
					Prest.close();
					Prest=null;
				}
				}catch(Exception e){
					e.printStackTrace();
				}
	          }
	 
	 return count;
 }
 
 
 public List findDueReminderList(String filterQuery)  {
	
	 log4jLogger.info("Inside Due Reminder List --> E-Mail");
	 
	 
	 SQLQuery sql = getSession().createSQLQuery(DataQuery.DueReminder_List.toString() +" AND Member_Email<>'' "+ filterQuery +" ORDER BY due_date DESC");//shek
		
	 return  sql.list(); 
 
 }
 
 public int findDueReminderMail(String code)  {
		
	 log4jLogger.info("Inside Due Reminder MAIL --> E-Mail:"+code);
	 
	 int count=0;
	 String sql="";
	 
		
	 try {			
			if(!code.equalsIgnoreCase("ALL"))
			{
			  sql="and member_code in(null" + code + ")";
			}			
			
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.Email_DueReminder.toString() + sql + " ORDER BY due_date DESC");
			rs = Prest.executeQuery();
			while (rs.next()) {		
				
				boolean chk=Security_Counter.EmailValidator(rs.getString("member_email").toString());				
				   if(chk==true) {					   
					   
					   Prest1 = con.prepareStatement(DataQuery.Email_DueReminder_Details.toString());
					   Prest1.setString(1,rs.getString("member_code").toString());
					   rs1 = Prest1.executeQuery();
					   
					   String namedQuery=MailQueryUtill.Duereminder_Message_Text;
					   StringBuffer sb = new StringBuffer();
					   sb.append("Dear "+rs.getString("member_name")+",<br><br>");
					   sb.append(namedQuery);
		    			
		    			sb.append("<br><br>");
		    			sb.append("<table border=1 width=700 align=center CELLSPACING='1' cellpadding='3' style='border-collapse: collapse' bordercolor='#of2e4f'>");	    			    			
		    			sb.append("<tr bgcolor='#CCEEFF'><th><b><font color='#000000' size='1' face='Verdana'></b>Access No</th>");		    			
		    			sb.append("<th><b><font color='#000000' size='1' face='Verdana'></b>Title</th>");
		    			sb.append("<th><b><font color='#000000' size='1' face='Verdana'></b>Issue Date</th>");
		    			sb.append("<th><b><font color='#000000' size='1' face='Verdana'></b>Due Date</th>");
		    			sb.append("<th><b><font color='#000000' size='1' face='Verdana'></b>Document</th></tr>");
		    			
					   
					   while (rs1.next()) {				   
			    			
			    			sb.append("<tr bgcolor='#CCFFFF'><td align=left width=70><font color='a62121' size='1' face='Verdana'>"+ rs1.getString("access_no") +"</td><td align=left><font color='a62121' size='1' face='Verdana'>"+ rs1.getString("title") +"</td><td align=left width=70><font color='a62121' size='1' face='Verdana'>"+ Security.TextDate_member(rs1.getString("issue_date")) +"</td><td align=left width=70><font color='a62121' size='1' face='Verdana'>"+ Security.TextDate_member(rs1.getString("due_date")) +"</td><td align=left width=70><font color='a62121' size='1' face='Verdana'>"+ rs1.getString("doc_type") +"</td></tr>");	
			    			
					   }	
					   sb.append("</table><br><br>");
					   sb.append(MailQueryUtill.Regards_Text);
					   sb.append("<br><br>");
					   //sb.append("xxxxxxx");
					   //sb.append("<font size='5' color='blue' face='Arial'><b>XXXXXXXXXXXX</b></font>");
					   
					   sb.append("<br>");
				//	   sb.append("College Name");
				//     sb.append("<br><br>");
				//     sb.append("xxxCollege");
				//     sb.append("<br>");
					   sb.append("Salem, Tamil Nad");
					   sb.append("<br><br>");
				//	   sb.append("Tel:  ");
					   sb.append("<br>");
				//	   sb.append("Fax: 044-0000");
				//	   sb.append("<br>");
				//	   sb.append("E.mail: " + "xxxxx@gmail.com");
					   sb.append("<br>");
					  // sb.append("http://www.mssw.in/");
					   
					   
					   //shek
					   
					   
					   count=findSendEmailNew(rs.getString("member_email").toString(),MailQueryUtill.Duereminder_Subject_Text,sb.toString());
					    
				   }else{
					  // count=0;  // Invalid Email ID
				   }
				
				
			}

		} catch (Exception e) {

		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (Prest != null) {
					Prest.close();
				}
				if (con != null) {
					con.close();
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return count;

 }
 
 public int findReserveReminderMail(String mcode,String accno)  
 {
		
	 log4jLogger.info("Inside Reserve Reminder MAIL --> E-Mail:"+mcode);
	 
	 int count=0;
	 SQLQuery sql = null;
	 List list = null;
	 String mail="",mailtype="";
	 try {			
			if(!mcode.equalsIgnoreCase("ALL"))
			{
				sql = getSession().createSQLQuery("SELECT member_email FROM member_mas WHERE member_code='"+mcode+"'");
				list = sql.list();
				mailtype = "single";
			}		
			else
			{
				sql = getSession().createSQLQuery("SELECT access_no  FROM reserve_mas WHERE mail_date <> '' AND DATEDIFF(CURDATE(),mail_date)>2 ORDER BY id DESC LIMIT 1");
				list = sql.list();
				mailtype = "bulk";
			}
			Iterator it = list.iterator();
			while(it.hasNext()) 
			{		
				if(!mailtype.equalsIgnoreCase("bulk"))
				{
					mail = it.next().toString();
				}
				else
				{
					accno = it.next().toString();
					sql = getSession().createSQLQuery("SELECT member_code FROM reserve_mas WHERE access_no='"+accno+"' AND mail_date='' ORDER BY id ASC LIMIT 1");
					list = sql.list();
					
					if(list.isEmpty())
					{
						continue;
					}
					else
					{
						mcode = list.get(0).toString();
						sql = getSession().createSQLQuery("SELECT member_email FROM member_mas WHERE member_code='"+mcode+"'");
						list = sql.list();
						mail = list.get(0).toString();
					}
				}
				
				boolean chk=Security_Counter.EmailValidator(mail);				
				   if(chk==true) 
				   {		
					   if(!mailtype.equalsIgnoreCase("bulk"))
					   {
						   sql = getSession().createSQLQuery("SELECT member_name,access_no,title,res_date,doc_type FROM reserve_reminder_view where member_code='"+mcode+"' and access_no ='"+accno+"'");
						   list = sql.list();
					   }
					   else
					   {
						   sql = getSession().createSQLQuery("SELECT member_name,access_no,title,res_date,doc_type FROM reservedbooks where member_code='"+mcode+"' and access_no ='"+accno+"'");
						   list = sql.list();
					   }
					   
					   
					   Object[] obj = (Object[])list.get(0);
					   
					   String namedQuery=MailQueryUtill.Reservereminder_Message_Text;
					   StringBuffer sb = new StringBuffer();
					   sb.append("Dear "+obj[0].toString()+",<br><br>");
					   sb.append(namedQuery);
		    			
		    			sb.append("<br><br>");
		    			sb.append("<table border=1 width=700 align=center CELLSPACING='1' cellpadding='3' style='border-collapse: collapse' bordercolor='#of2e4f'>");	    			    			
		    			sb.append("<tr bgcolor='#CCEEFF'><th><b><font color='#000000' size='1' face='Verdana'></b>Access No</th>");		    			
		    			sb.append("<th><b><font color='#000000' size='1' face='Verdana'></b>Title</th>");
		    			sb.append("<th><b><font color='#000000' size='1' face='Verdana'></b>Reserved Date</th>");
		    			sb.append("<th><b><font color='#000000' size='1' face='Verdana'></b>Document</th></tr>");
		    								   
					 
		    			sb.append("<tr bgcolor='#CCFFFF'><td align=left width=70><font color='a62121' size='1' face='Verdana'>"+ obj[1].toString() +"</td><td align=left><font color='a62121' size='1' face='Verdana'>"+ obj[2].toString() +"</td><td align=left width=70><font color='a62121' size='1' face='Verdana'>"+ obj[3].toString() +"</td><td align=left width=70><font color='a62121' size='1' face='Verdana'>"+ obj[4].toString() +"</td></tr>");	

					   sb.append("</table><br><br>");
					   sb.append(MailQueryUtill.Regards_Text);
					   sb.append("<br><br>");
 					   
					   count=findSendEmailNew(mail,MailQueryUtill.Reservereminder_Subject_Text,sb.toString());
					   
					   if(count == 1)
					   {
						   sql = getSession().createSQLQuery("Update reserve_mas set mail_Date=curdate() where member_code='"+mcode+"' and access_no ='"+accno+"'");
						   sql.executeUpdate();
					   }
					    
				   }else{
					  // count=0;  // Invalid Email ID
				   }
				
				
			}

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (Prest != null) {
					Prest.close();
				}
				if (con != null) {
					con.close();
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return count;

 }
	
 public int findSuggestionReply(SuggestionBean ob)
 {
	 log4jLogger.info("Inside Suggestion Reply MAIL --> E-Mail:");
	 int count=0;
	 SQLQuery sql = null;
	 List list = null;
	 String mail="",mailtype="",mcode="",mname="",action="",reqfor="",req_date="",status="",Details="";
	 con = getSession().connection();
		try {
			Prest = con.prepareStatement(DataQuery.SUGGESTIONMEMBER_NAME.toString());
			Prest.setInt(1, ob.getSugNo());
			rs = Prest.executeQuery();
			while(rs.next())
			{
				 mcode =rs.getString("member_code");
				 mail=rs.getString("member_email");
				 action=rs.getString("action_taken");
				 mname=rs.getString("member_name");
				 reqfor=rs.getString("request");
				 req_date=rs.getString("request_date");
				 status=rs.getString("status");
				 Details=rs.getString("Detail");
				 boolean chk=Security_Counter.EmailValidator(rs.getString("member_email").toString());				
				   if(chk==true) {
					   StringBuffer sb = new StringBuffer();
					   sb.append("Dear "+mname.toString()+",<br><br>");
					   String namedQuery="  The Following Action is taken from Library!!";
					   sb.append(namedQuery);
					   sb.append("<br><br>");
//					   sb.append(action);
//					   sb.append("<br><br>");
					   
		    			sb.append("<table border=1 width=700 align=center CELLSPACING='1' cellpadding='3' style='border-collapse: collapse' bordercolor='#of2e4f'>");	    			    			
		    			sb.append("<tr bgcolor='#CCEEFF'><th><b><font color='#000000' size='1' face='Verdana'></b>Request Date</th>");		    			
		    			sb.append("<th><b><font color='#000000' size='1' face='Verdana'></b>Request For</th>");
		    			sb.append("<th><b><font color='#000000' size='1' face='Verdana'></b>Request Details</th>");
		    			sb.append("<th><b><font color='#000000' size='1' face='Verdana'></b>Action Taken</th>");
		    			sb.append("<th><b><font color='#000000' size='1' face='Verdana'></b>Status</th></tr>");
		    	
		    			sb.append("<tr bgcolor='#CCFFFF'><td align=left width=10><font color='a62121' size='1' face='Verdana'>"+ req_date +"</td><td align=left><font color='a62121' size='1' face='Verdana'>"+reqfor +"</td><td align=left><font color='a62121' size='1' face='Verdana'>"+ Details +"</td><td align=left><font color='a62121' size='1' face='Verdana'>"+ action +"</td><td align=left><font color='a62121' size='1' face='Verdana'>"+status+"</td></tr>");
		    		
		    			 sb.append("</table><br><br>");
					   sb.append(MailQueryUtill.Regards_Text);
					   sb.append("<font size='2' color='blue' face='Arial'><b>Oriental University</b></font>");
					   
					   sb.append("<br>");
					   count=findSendEmailNew(rs.getString("member_email").toString(),MailQueryUtill.Suggestion_Subject_Text,sb.toString());
				   }
				   else{
					   
				   }
				   
					
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	 
	 
	 
	return a;

	 
 }
 
 public int findSugReqMail(SuggestionBean ob)
 {
	 log4jLogger.info("Inside Suggestion Request MAIL --> E-Mail:");
	 con = getSession().connection();
	 String mail="",mailtype="",mcode="",mname="",action="",reqfor="",req_date="",status="",Details="";
	 try {
		 int count=0;
		Prest = con.prepareStatement(DataQuery.SUGGESTIONREQMAIL.toString());
		rs = Prest.executeQuery();
		while (rs.next()) {		
			
			boolean chk=Security_Counter.EmailValidator(rs.getString("EMAIL_ID").toString());	
			if(chk==true) {
				System.out.println("mail validate is true"+ob.getSugNo());
				 Prest1 = con.prepareStatement(DataQuery.SUGGESTIONMEMBER_NAME.toString());
				   Prest1.setInt(1,ob.getSugNo());
				   rs1 = Prest1.executeQuery();
				   while(rs1.next())
				   {
					    mcode=rs1.getString("member_code");
					    mname=rs1.getString("member_name");
					    req_date=rs1.getString("request_date");
					    reqfor=rs1.getString("request");
					    Details=rs1.getString("Detail");
				   }
				   StringBuffer sb = new StringBuffer();
				   sb.append("Dear Sir/Madam,<br><br>");
				   String namedQuery="The Following Suggestion is Given by "+mname+"<br><br>";
				   sb.append(namedQuery);
				   sb.append("<table border=1 width=700 align=center CELLSPACING='1' cellpadding='3' style='border-collapse: collapse' bordercolor='#of2e4f'>");	
				   
	    			sb.append("<tr bgcolor='#CCEEFF'><th><b><font color='#000000' size='1' face='Verdana'></b>Request Date</th>");	
	    			
	    			sb.append("<th><b><font color='#000000' size='1' face='Verdana'></b>Member Code</th>");
	    			sb.append("<th><b><font color='#000000' size='1' face='Verdana'></b>Request For</th>");
	    			sb.append("<th><b><font color='#000000' size='1' face='Verdana'></b>Request Details</th>");
	    			
	    			
	    				sb.append("<tr bgcolor='#CCFFFF'><td align=left width=10><font color='a62121' size='1' face='Verdana'>"+ req_date +"</td><td align=left width=10><font color='a62121' size='1' face='Verdana'>"+ mcode +"</td><td align=left><font color='a62121' size='1' face='Verdana'>"+reqfor+"</td><td align=left><font color='a62121' size='1' face='Verdana'>"+Details+"</td></tr>");
	    			
	    			sb.append("</table><br><br>");
	    			
	    			 count=findSendEmailNew(rs.getString("Email_ID").toString(),MailQueryUtill.Suggestion_Subject_Text,sb.toString());
			}
			
			else{
				
			}
			
			
		}
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return a;
	 

	 
 }




//Start Profile Mail================================================================


	public int findProfileMail()  {

		log4jLogger.info("Inside Profile MAIL --> E-Mail:");

		int count=0;
		String sql="";
		
		try {		


			log4jLogger.info("Inside Profile MAIL --> E-fdgdgdgdgd:");

			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.Profile_Mail.toString());
			rs = Prest.executeQuery();
			while (rs.next()) {		
						
			//	boolean chk=Security_Counter.EmailValidator(rs.getString("member_email").toString());				
				//if(chk==true) {					   

					Prest1 = con.prepareStatement(DataQuery.Email_Profile_Details.toString());
					Prest1.setString(1,rs.getString("member_code").toString());
					rs1 = Prest1.executeQuery();
					
					String namedQuery=MailQueryUtill.Profile_Message_Text;
					StringBuffer sb = new StringBuffer();
					sb.append("Dear "+rs.getString("member_name")+",<br><br>");
					sb.append(namedQuery);

					sb.append("<br><br>");
					sb.append("<table border=1 width=700 align=center CELLSPACING='1' cellpadding='3' style='border-collapse: collapse' bordercolor='#of2e4f'>");	    			    			
					sb.append("<tr bgcolor='#CCEEFF'><th><b><font color='#000000' size='1' face='Verdana'></b>Access No</th>");		    			
					sb.append("<th><b><font color='#000000' size='1' face='Verdana'></b>Title</th>");
					sb.append("<th><b><font color='#000000' size='1' face='Verdana'></b>Member code</th>");
					sb.append("<th><b><font color='#000000' size='1' face='Verdana'></b>Member Name</th>");

					while (rs1.next()) {				   

						sb.append("<tr bgcolor='#CCFFFF'><td align=left width=70><font color='a62121' size='1' face='Verdana'>"+ rs1.getString("access_no") +"</td><td align=left width=70><font color='a62121' size='1' face='Verdana'>"+ rs1.getString("title") +"</td><td align=left width=70><font color='a62121' size='1' face='Verdana'>"+ rs1.getString("member_code") +"</td><td align=left width=70><font color='a62121' size='1' face='Verdana'>"+ rs1.getString("member_name") +"</td></tr>");	
						
					}	
					sb.append("</table><br><br>");
					sb.append(MailQueryUtill.Regards_Text);
					sb.append("<br><br>");
					
					count=findSendEmailNew(rs.getString("member_email").toString(),MailQueryUtill.Profile_Subject_Text,sb.toString());


					log4jLogger.info("Mail Sent:::::"+count);
			
				//}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (Prest != null) {
					Prest.close();
				}
				if (con != null) {
					con.close();
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return count;
	}
	
	
 
}