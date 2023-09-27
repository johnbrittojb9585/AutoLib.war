package Common.businessutil.sms;

import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;


import Common.DataQuery;
import Common.Security;
import Common.Security_Counter;
import Common.businessutil.mail.MailQueryUtill;

import com.library.autolib.portal.dbconnectionutil.BaseDBConnection;

public class SmsDaoImpl extends BaseDBConnection implements SmsDao 
{
	
	private static Logger log4jLogger = Logger.getLogger(SmsDaoImpl.class);
	int a=0;	
	static Log log = LogFactory.getLog(SmsDaoImpl.class);
	
	java.sql.Connection con = null;
	java.sql.PreparedStatement Prest = null;
	java.sql.PreparedStatement Prest1 = null;
	java.sql.ResultSet rs = null;
	java.sql.ResultSet rs1 = null;
	java.sql.Statement st = null;
	
	
    //private String URL="http://boancomm.net/boansms/boansmsinterface.aspx?mobileno=";
	//String content = "mobileno=9629019378&smsmsg=Karuppaiah to AutoLib Software Systems .Chennai&uname=autolibsms&pwd=autolib13sms&pid=21";
	
/**	private String username="RKautolibsms";
	private String password="RKautolib13sms";
	private int pid=21; */
	
	private String username="";
	private String password="";
	private String from="";
	private String proxyName="",proxyStatus;
	private int proxyPort;
	
	Properties properties=new Properties();
	
       
 public int findSendSMS(String mobno,String message) {
	 
	 	 log4jLogger.info("=============== Inside SmsDaoImpl ========");
	         
	 	 try {
            
	 		 properties.load(this.getClass().getClassLoader().getResourceAsStream("LocalStrings.properties"));
	 		
	 		 username = properties.getProperty("autolib.sms.username");	
	 		 password = properties.getProperty("autolib.sms.password");	
	 		 from = properties.getProperty("autolib.sms.senderid");
	 		 
	 		 proxyStatus = properties.getProperty("autolib.proxy.status");
	 		 proxyName = properties.getProperty("autolib.proxy.host");
	 		 proxyPort = Integer.parseInt(properties.getProperty("autolib.proxy.port"));
	 		 
	 		
	 		 int TIMEOUT_VALUE = 15000;   // For 15 Seconds
	 		 
		        // 1.Done code for SMS (Method:1)	 		 
		        
		 	//	String url_str="http://103.250.30.5/api/smsapi.aspx?username="+ username +"&password="+ password +"&to="+ mobno +"&from="+ from +"&message="+URLEncoder.encode(message, "UTF-8");
		 		/*String url_str="https://103.250.30.5/SendSMS/sendmsg.php?uname="+ username +"&pass="+ password +"&send="+ from +"&dest="+ mobno +"&msg="+URLEncoder.encode(message, "UTF-8");*/
		 		String url_str="https://www.unicel.in/SendSMS/sendmsg.php?uname="+ username +"&pass="+ password +"&send="+ from +"&dest="+ mobno +"&msg="+URLEncoder.encode(message, "UTF-8");
		 		//log4jLogger.info("path name---url_str----->"+url_str);
		 		URL u=new URL(url_str);	 		
		 		
		 		HttpURLConnection connection;
		 		
		 		if(proxyStatus.equalsIgnoreCase("YES"))  // For Proxy Settings
		 		{
		 			 log4jLogger.info("=============== Inside Proxy Server Settings ====="+mobno);	 		
		 			 Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyName, proxyPort));  // For Proxy Settings
		 			 connection = (HttpURLConnection) u.openConnection(proxy); 		 			 
		 		}
		 		else  {
		 			 connection = (HttpURLConnection) u.openConnection();		 			 
		 		}		 		
		 		
		 		connection.setDoOutput(true);
		 		connection.setDoInput(true);
		 		
		 		connection.setConnectTimeout(TIMEOUT_VALUE);
		 		connection.setReadTimeout(TIMEOUT_VALUE);

		 		//log4jLogger.info("connection :"+connection);
		 		
		 		String res=connection.getResponseMessage();
		 		
		 		//log4jLogger.info("result :"+res);

		 		int code = connection.getResponseCode () ;
		 		
		 		if ( code == HttpURLConnection.HTTP_OK )
		 		{	
		 		  connection.disconnect() ;
		 		  log4jLogger.info("=============== Send Successfully ====="+message.length());	 		
		 		} 	
		 		
		 		
		 	/** For Boan Communiication SMS code:	
	 	    String temp = "";  
	        String content = "mobileno="+mobno+"&smsmsg="+message+"&uname="+username+"&pwd="+password+"&pid="+pid;  
	        URL u = new URL("http://boancomm.net/boansms/boansmsinterface.aspx?");  
	        HttpURLConnection uc = (HttpURLConnection) u.openConnection();  
	        uc.setDoOutput(true);
	        uc.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 6.0; en-US; rv:1.9.0.5) Gecko/2008120122 Firefox/3.0.5");  
	        uc.setRequestProperty("Content-Length", String.valueOf(content.length()));  
	        uc.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");  */
	        //uc.setRequestProperty("Accept", "*/*");  
	       /** uc.setRequestMethod("POST");  
	        uc.setInstanceFollowRedirects(false); // very important line :)  
	        PrintWriter pw = new PrintWriter(new OutputStreamWriter(uc.getOutputStream()), true);  
	        pw.print(content);  
	        pw.flush();      // Send SMS 
	        pw.close();  
	        BufferedReader br = new BufferedReader(new InputStreamReader(uc.getInputStream()));  
	        while ( (temp = br.readLine()) != null ) {}  
	        String cookie = uc.getHeaderField("Set-Cookie");  
	  
	        log4jLogger.info("=============== Content Length ========"+content.length());
	        
	        // Send SMS to each of the phone numbers  
	        u = null; uc = null;  */

		 		
		 		// Send SMS to each of the phone numbers  
		        u = null; 
		        //uc = null;
		        connection=null;  	        
		        a=1;
		        
	 	     	  
	 	 }catch(Exception e) {
	 		 a=0;
	 		 e.printStackTrace();
	 		log4jLogger.info("Error Code: "+e);
	 	 }
	        
              
		return a;
	} 
 
 
 
 
 public int findDueReminderSMS(String code)
 {
	 log4jLogger.info("================= start: [findDueReminderSMS] ====================== " );
	 
	 int count=0,res=0;
	 String sql="";	 
		
	 try {			
			if(!code.equalsIgnoreCase("ALL"))
			{
			  sql="and member_code in(null" + code + ")";
			}		
			
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.Sms_DueReminder.toString() + sql + " order by member_name asc");
			rs = Prest.executeQuery();
			while (rs.next()) {		
				
				boolean chk=Security_Counter.SMSValidator(rs.getString("member_phone").toString());				
				   if(chk==true) {					   
					   
					   Prest1 = con.prepareStatement(DataQuery.Email_DueReminder_Details.toString());
					   Prest1.setString(1,rs.getString("member_code").toString());
					   rs1 = Prest1.executeQuery();
					   
					   String namedQuery=MailQueryUtill.Duereminder_SMS_MessageText;
					   
					   StringBuffer sb = new StringBuffer();
					   sb.append("Dear "+rs.getString("member_name")+", ");
					   sb.append(namedQuery);
		    		   
					   String msg="";
					   
					   while (rs1.next()) {	
						   
						   msg = msg + Security.TextDate_member(rs1.getString("due_date")) + ":" + rs1.getString("access_no") + ", " ;						  
					   }
					   sb.append(msg);
					   sb.append("Regards Librarian.");
					   					   
					   res=findSendSMS(rs.getString("member_phone").toString(),sb.toString());
					   
					   count = count + res;
				   }else{
					  // count=0;  // Invalid Email ID
				   }
				
				
			}
			 log4jLogger.info("================= End: [findDueReminderSMS] ====================== " );
			 
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
 
 
 public int findAdvanceReminderSMS()
 {
	 log4jLogger.info("================= start: [findAdvanceReminderSMS] ====================== " );
	 
	 int count=0,res=0;    
		
	 try {			
			
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.Sms_AdvanceDueReminder.toString() + " order by member_name asc");
			rs = Prest.executeQuery();
			
			while (rs.next()) {		
				
				boolean chk=Security_Counter.SMSValidator(rs.getString("member_phone").toString());				
				   if(chk==true) {					   
					   
					   Prest1 = con.prepareStatement(DataQuery.Sms_AdvanceDueReminderList.toString());
					   Prest1.setString(1,rs.getString("member_code").toString());
					   rs1 = Prest1.executeQuery();
					   
					   String namedQuery=MailQueryUtill.AdvanceDuereminder_SMS_MessageText;
					   
					   StringBuffer sb = new StringBuffer();
					   sb.append("Dear "+rs.getString("member_name")+", ");					   
					   sb.append(namedQuery);
		    		   
					   String msg="";
					   
					   while (rs1.next()) {					   				   
						   
						   msg = msg + Security.TextDate_member(rs1.getString("due_date")) + ":" + rs1.getString("access_no") + ", " ;						  
					   }
					   
					   sb.append(msg);
					   sb.append("Regards Librarian.");
					   
					   res=findSendSMS(rs.getString("member_phone").toString(),sb.toString());
					   
					   count = count + res;
				   }else{
					  // count=0;  // Invalid Email ID
				   }
				
				
			}
			
			log4jLogger.info("================= End: [findAdvanceReminderSMS] ====================== " );
			
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
 