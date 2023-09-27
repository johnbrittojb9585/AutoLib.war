package Common;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Security_Counter{
public static String checkSecurity(int iLevel, javax.servlet.http.HttpSession session, javax.servlet.http.HttpServletResponse response, javax.servlet.http.HttpServletRequest request){
    try {
      Object o1 = session.getAttribute("UserID");
      Object o2 = session.getAttribute("UserRights");
	  boolean bRedirect = false;
      if ( o1 == null || o2 == null ) { bRedirect = true; }
	  if ( ! bRedirect ) {
        if ( (o1).equals("")) { bRedirect = true; }
		else 
		if ( (new Integer(o2.toString())).intValue() < iLevel) { bRedirect = true; }
      }
      if ( bRedirect ) {
	    // response.sendRedirect("/AutoLib/index.html?querystring=" + request.getQueryString() + "&ret_page=" + request.getRequestURI());
           response.sendRedirect("/AutoLib/index.html");
	      }
    }
	catch(Exception e){};
    return "";
  }
  
  
  	 public static String replace(String str, String pattern, String replace) {
    if (replace == null) {
      replace = "";
    }
    int s = 0, e = 0;
    StringBuffer result = new StringBuffer((int) str.length()*2);
    while ((e = str.indexOf(pattern, s)) >= 0) {
      result.append(str.substring(s, e));
      result.append(replace);
      s = e + pattern.length();
    }
    result.append(str.substring(s));
    return result.toString();
  }
  
  public static String getParam(javax.servlet.http.HttpServletRequest req, String paramName) {
    String param = req.getParameter(paramName);
    if ( param == null || param.equals("") ) return "";
	param=  param.replace('"',' ');
    param = replace(param, "'", "''");
   java.util.StringTokenizer st=new java.util.StringTokenizer(param,"\\");
   String s="";
   while(st.hasMoreElements())
	{
	s=s+st.nextElement();
	}
    param=s;
	return param;
  }


/**
 * @param session
 * @param request
 * @param response
 */

public static String TodayDate_Insert() {
java.util.Calendar calendar = new java.util.GregorianCalendar();
				java.util.Date d=new java.util.Date();
				calendar.setTime(d);
				int Year=calendar.get(java.util.Calendar.YEAR);
				int nd=calendar.get(java.util.Calendar.DAY_OF_MONTH);
				int nm=calendar.get(java.util.Calendar.MONTH)+1;
				String Month=new Integer(nm).toString();
				if(Month.length()==1)
	            Month="0"+Month;
				String Day=new Integer(nd).toString();
				if(Day.length()==1)
	            Day="0"+Day;
				String Idate=Month+"-"+Day+"-"+Year;
	return  Idate;
		
}

public static String TextDate_Insert(String date_Text) throws ParseException {
	System.out.println("==================date===================="+date_Text);
	java.util.StringTokenizer stz=new java.util.StringTokenizer(date_Text,"-");
					int bid=Integer.parseInt(stz.nextToken());
					int bim=Integer.parseInt(stz.nextToken());
					int Year=Integer.parseInt(stz.nextToken());
					   String Month=new Integer(bim).toString();
							if(Month.length()==1)
							Month="0"+Month;
							String Day=new Integer(bid).toString();
							if(Day.length()==1)
							 Day="0"+Day;
					String ITdate=Year+"-"+Month+"-"+Day;
	return  ITdate;
	
	
	
		
}

public static String TextDate_statisties(String dateText) {
	java.util.StringTokenizer stz=new java.util.StringTokenizer(dateText,"-");
					int bid=Integer.parseInt(stz.nextToken());
					int bim=Integer.parseInt(stz.nextToken());
					int Year=Integer.parseInt(stz.nextToken());
					   String Month=new Integer(bim).toString();
							if(Month.length()==1)
							Month="0"+Month;
							String Day=new Integer(bid).toString();
							if(Day.length()==1)
							 Day="0"+Day;
					String ITdate=Month+"/"+Day+"/"+Year;
	return  ITdate;
		
}

 
 
public static String getdate(String date) throws ParseException {
	           java.util.StringTokenizer stz=new java.util.StringTokenizer(date,"-");
				int year=Integer.parseInt(stz.nextToken());
				int bim=Integer.parseInt(stz.nextToken());
				int bid=Integer.parseInt(stz.nextToken());
				   String month=new Integer(bim).toString();
						if(month.length()==1)
	                    month="0"+month;
						String day=new Integer(bid).toString();
						if(day.length()==1)
	                    day="0"+day;
						String year1=new Integer(year).toString();
						String DisplayDate=day+"-"+month+"-"+year1;
		return DisplayDate;
		
}

public static String TodayDate() {
	               	java.util.Date d=new java.util.Date();
					SimpleDateFormat OrderDate = new SimpleDateFormat("yyyy-MM-dd");
	return  OrderDate.format(d);
		
}
public static String TodayDate_view() {
   	java.util.Date d=new java.util.Date();
   	
	SimpleDateFormat OrderDate = new SimpleDateFormat("dd-MM-yyyy");
return  OrderDate.format(d);

}

  
  
  
public static String Counter_DateGet(java.sql.Date cdate) {
	                     SimpleDateFormat OrderDate = new SimpleDateFormat("yyyy-MM-dd");
	return OrderDate.format(cdate);
		
}
public static boolean check() {

	
	return  true;

	}

public static String Counter_DateFormat(java.sql.Date cdate) {
    SimpleDateFormat OrderDate = new SimpleDateFormat("dd-MM-yyyy");
return OrderDate.format(cdate);

}
  
  
public static String Counter_DateText() {
	                     java.util.Date d=new java.util.Date();
						 SimpleDateFormat OrderDate = new SimpleDateFormat("yyyy-MM-dd");
	return OrderDate.format(d);
		
}
  
public static String Counter_DateTexttime() {
    java.util.Date d=new java.util.Date();
	 SimpleDateFormat OrderDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
return OrderDate.format(d);

}
  
public static String Counter_DateSet(java.util.Date cdate) {
						 SimpleDateFormat OrderDate = new SimpleDateFormat("dd-MM-yyyy");
	return OrderDate.format(cdate);
		
}
 
 public static void closeResultSet(ResultSet rs) 
	{
	try{
		if(rs!=null){
			rs.close();
		}
		rs=null;	
	}
	catch(SQLException e){
	e.printStackTrace();	
	}
	}
	public static void closeStatement(Statement st){
		try{
			if(st!=null){
				st.close();
			}
			st=null;
		}
		catch(SQLException e){
			e.printStackTrace();
		}
	}
	public static void closePreparedStatement(PreparedStatement pstmt){
		
		try{
			if(pstmt!=null){
				pstmt.close();
			}
			pstmt=null;
		}catch(SQLException e){
		e.printStackTrace();	
		}
	}
	
	public static String CalenderDate() {

		java.util.Date d =new java.util.Date();
		SimpleDateFormat dateformat = new SimpleDateFormat("dd-MM-yyyy");
		return  dateformat.format(d);

		}
	
	public static String TextDate_member(String dateText) {
		java.util.StringTokenizer stz=new java.util.StringTokenizer(dateText,"-");
						int bid=Integer.parseInt(stz.nextToken());
						int bim=Integer.parseInt(stz.nextToken());
						int Year=Integer.parseInt(stz.nextToken());
						   String Month=new Integer(bim).toString();
								if(Month.length()==1)
								Month="0"+Month;
								String Day=new Integer(bid).toString();
								if(Day.length()==1)
								 Day="0"+Day;
						String ITdate=Year+"-"+Month+"-"+Day;
		return  ITdate;

	}
	
	public static String TextDate_Update(String date_Text) {
		java.util.StringTokenizer stz=new java.util.StringTokenizer(date_Text,"-");
	    int bid=Integer.parseInt(stz.nextToken());
	    int bim=Integer.parseInt(stz.nextToken());
		int Year=Integer.parseInt(stz.nextToken());
		   String Month=new Integer(bim).toString();
				if(Month.length()==1)
	            Month="0"+Month;
				String Day=new Integer(bid).toString();
				if(Day.length()==1)
	             Day="0"+Day;
		String ITdate=Day+"-"+Month+"-"+Year;
	return  ITdate;

	}
	
	public static boolean EmailValidator(String email) {
		boolean chk = false;
		// String email = "checkme@youremail.com"; 
		 String regEx = "\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}\\b";
		 
		 Pattern p = Pattern.compile(regEx);
		 Matcher m = p.matcher(email);
		 
		 if(m.find()) 
		 {
			 chk=true;
		 }
		 else
		 {
			 chk = false;
		 }	
		
		return chk;	
		
	}
	
	public static boolean SMSValidator(String phone) {
		boolean chk = false;
		 
		 String regEx = "\\d{10}";
		 
		 Pattern p = Pattern.compile(regEx);
		 Matcher m = p.matcher(phone);
		 
		 if(m.find()) 
		 {
			 chk=true;
		 }
		 else
		 {
			 chk = false;
		 }	
		
		return chk;	
		
	}
	
	
	public static void closeConnection(java.sql.Connection con) throws SQLException{
		
		
	if(con!=null){
		con.close();
	}
	con=null;

	
}
}