package Common;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import java.sql.Connection;

public class Security{
	
	Connection con;
	public Connection getConnection(){
    try{
		Context initContext = new InitialContext();
		DataSource dataSource =(DataSource) initContext.lookup("java:mysql");
		con=dataSource.getConnection();
	}catch(NamingException ne){
	}catch(SQLException sq){
     }
	return con;
	}
	
public static String checkSecurity(int iLevel, javax.servlet.http.HttpSession session, javax.servlet.http.HttpServletResponse response, javax.servlet.http.HttpServletRequest request){
	String result = "";
	
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
    	
          // response.sendRedirect("/AutoLib/index.html");
    	    result = "Failure";
	      }else {
	    	 //Error in JNL_Enquiry when click on clear button when i saw the old record information. removedSession(response,request);  // To remove a session value by R.Karuppaiah on 24-05-2014
	      }
    }
	catch(Exception e){};
    return result;
  }

protected static void removedSession(javax.servlet.http.HttpServletResponse response, javax.servlet.http.HttpServletRequest request)
{
	
	HashSet localHashSet = new HashSet();
	localHashSet.add("UserID");
	localHashSet.add("UserRights");
	localHashSet.add("visitMember");
	localHashSet.add("visitDateTime");
	localHashSet.add("SearchArrylist");
	

	HttpSession localHttpSession = request.getSession();
	Enumeration localEnumeration = localHttpSession.getAttributeNames();

	ArrayList localArrayList = new ArrayList();

	while (localEnumeration.hasMoreElements())
	{
		String str =(String) localEnumeration.nextElement();
		
		if(!localHashSet.contains(str))
			localArrayList.add(str);		
	}

	int j = localArrayList.size();

	for(int k = 0; k < j ; k++)
	{		
		localHttpSession.removeAttribute((String)localArrayList.get(k));
	}
		
	
	
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
public static String TodayDate_view() {
   	java.util.Date d=new java.util.Date();

	SimpleDateFormat OrderDate = new SimpleDateFormat("dd-MM-yyyy");
return  OrderDate.format(d);

}

public static String TextDate_Insert(String date_Text) throws ParseException {
	
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

 
 
public static String getdate(String date) {
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
						String DisplayDate=day+"-"+month+"-"+year;
		return DisplayDate;
		
}

public static String TodayDate() {
	               	java.util.Date d=new java.util.Date();
					SimpleDateFormat OrderDate = new SimpleDateFormat("dd-MM-yyyy");
	return  OrderDate.format(d);
		
}

public static String Counter_DateFormat(java.sql.Date cdate) {
    SimpleDateFormat OrderDate = new SimpleDateFormat("dd-MM-yyyy");
return OrderDate.format(cdate);

}

  
  
  
public static String Counter_DateGet(java.sql.Date cdate) {
	                     SimpleDateFormat OrderDate = new SimpleDateFormat("dd-MM-yyyy");
	return OrderDate.format(cdate);
		
}
  
  
public static String Counter_DateText() {
	                     java.util.Date d=new java.util.Date();
						 SimpleDateFormat OrderDate = new SimpleDateFormat("yyyy-MM-dd");
	return OrderDate.format(d);
		
}
  
  
  
public static String Counter_DateSet(java.util.Date cdate) {
						 SimpleDateFormat OrderDate = new SimpleDateFormat("dd-MM-yyyy");
	return OrderDate.format(cdate);
		
}
public static String Counter_DateSettime(java.util.Date cdate) {
	 SimpleDateFormat OrderDate = new SimpleDateFormat("hh:mm");
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
	
	public static boolean IsNumeric(String string)
    {	 
	  try
	  {
		Integer.parseInt(string);
		return  true;
	  }catch(Exception e){
		return  false;
	  }			
    }
	
	public static void closeConnection(java.sql.Connection con) throws SQLException{
		
		
	if(con!=null){
		con.close();
	}
	con=null;

	
}

	public static String getBranchSession(javax.servlet.http.HttpSession session, javax.servlet.http.HttpServletResponse response, javax.servlet.http.HttpServletRequest request){
		String result = null;
		
		try {
	      Object o1 = session.getAttribute("UserBranchID");
	      Object o2 = session.getAttribute("visitBranch");	      
	      
		  boolean bRedirect = false;
		  
	      if ( o1 == null || o2 == null ) { bRedirect = true; }
	      
		  if ( ! bRedirect ) {
	        if ( (o1).equals("")) { bRedirect = true; }
			else 
				result = o2.toString();
	      }
		  
	       /**   if ( o1.equals(2) ) {  // For Report subtitle alignment
	    	    result = "        "+ result ;
		    
		      }else if( o1.equals(3) )
		      {
		    	result = "      "+ result ;  
		      }*/
	    }
		catch(Exception e){};
	    return result;
	  }	


}