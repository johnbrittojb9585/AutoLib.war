<%!
//
//   Filename: Common.jsp
//
  static final int adText = 1;
  static final int adDate = 2;
  static final int adNumber = 3;
  static final int adSearch_ = 4;
  static final int ad_Search_ = 5;
//Database connection string

  static final String DBDriver  ="org.postgresql.Driver";
  static final String strConn   ="jdbc:postgresql://192.0.0.3:5432/AutoLib";
  static final String DBusername="selva";
  static final String DBpassword="iloveyou";
// Load the Driver
  public static String loadDriver () {
    String sErr = "";
    try {
      java.sql.DriverManager.registerDriver((java.sql.Driver)(Class.forName(DBDriver).newInstance()));
    }
    catch (Exception e) {
      sErr = e.toString();
    }
    return (sErr);
  }
//connection0
     java.sql.Connection cn() throws java.sql.SQLException {
    return java.sql.DriverManager.getConnection(strConn , DBusername, DBpassword);
  }
  
    java.sql.ResultSet openrs(java.sql.Statement stat, String sql) throws java.sql.SQLException {
    java.sql.ResultSet rs = stat.executeQuery(sql);
    return (rs);
  }

  String toHTML(String value) {
    if ( value == null ) return "";
    value = replace(value, "&", "&amp;");
    value = replace(value, "<", "&lt;");
    value = replace(value, ">", "&gt;");
    value = replace(value, "\"", "&" + "quot;");
    return value;
  }


  String getParam(javax.servlet.http.HttpServletRequest req, String paramName) {
    String param = req.getParameter(paramName);
    if ( param == null || param.equals("") ) return "";
	param = replace(param,"&amp;","&");
    param = replace(param,"&lt;","<");
    param = replace(param,"&gt;",">");
    param = replace(param,"&amp;lt;","<");
    param = replace(param,"&amp;gt;",">");
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

  String getsearch(String chk){
  chk=chk.replace('"',' ');
   return chk;
  }
  
  boolean isNumber (String param) {
    boolean result;
    if ( param == null || param.equals("")) return true;
    param=param.replace('d','_').replace('f','_');
    try {
      Double dbl = new Double(param);
      result = true;
    }
    catch (NumberFormatException nfe) {
      result = false;
    }
    return result;
  }

  String toSQL(String value, int type) {
    if ( value == null ) return "Null";
    String param = value;
    if ("".equals(param) && (type == adText || type == adDate) ) {
      return "Null";
    } 
    switch (type) {
      case adText: {
        param = replace(param, "'", "''");
        param = "'" + param + "'";
        break;
      }
      case adSearch_:
      case ad_Search_: {
        param = replace(param, "'", "''");
        break;
      }
      case adNumber: {
        try {
          if (! isNumber(value) || "".equals(param)) param="null";
          else param = value;
        }
        catch (NumberFormatException nfe) {
          param = "null";
        }
        break;
      }
      case adDate: {
        param = "'" + param + "'";
        break;      
      }
    }
    return param;
  }
  private String replace(String str, String pattern, String replace) {
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
        String getdate(String datechk) {
    	//datechk=datechk.substring(0,datechk.length()-13);
    	   java.util.StringTokenizer stz_is=new java.util.StringTokenizer(datechk,"-");
		    int biy=Integer.parseInt(stz_is.nextToken());
		     int bim=Integer.parseInt(stz_is.nextToken());
		    int bid=Integer.parseInt(stz_is.nextToken());
	     	   String bissue_m=new Integer(bim).toString();
				    if(bissue_m.length()==1)
				    bissue_m="0"+bissue_m;
					String bissue_d=new Integer(bid).toString();
					if(bissue_d.length()==1)
				    bissue_d="0"+bissue_d;
					String BISSDATE=bissue_d+"/"+bissue_m+"/"+biy;
	return BISSDATE;
  }

 String getorder(String chk) {
    	//chk=chk.substring(0,chk.length()-13);
    	   java.util.StringTokenizer stz_ord=new java.util.StringTokenizer(chk,"-");
		     int oy=Integer.parseInt(stz_ord.nextToken());
		     int om=Integer.parseInt(stz_ord.nextToken());
	         int od=Integer.parseInt(stz_ord.nextToken());
		   String ord_m=new Integer(om).toString();
				    if(ord_m.length()==1)
				    ord_m="0"+ord_m;
					String ord_d=new Integer(od).toString();
					if(ord_d.length()==1)
				    ord_d="0"+ord_d;
					String ORD=ord_m+"/"+ord_d+"/"+oy;
	return ORD;
  }
   String toURL(String strValue){
    if ( strValue == null ) return "";
    if ( strValue.compareTo("") == 0 ) return "";
    return java.net.URLEncoder.encode(strValue);
  }
  
  
  String getholiday(String holiday) {
    	//holiday=holiday.substring(0,holiday.length()-9);
    	   java.util.StringTokenizer stz_h=new java.util.StringTokenizer(holiday,"-");
		    int hy=Integer.parseInt(stz_h.nextToken());
		     int hm=Integer.parseInt(stz_h.nextToken());
		    int hd=Integer.parseInt(stz_h.nextToken());
	     	   String hissue_m=new Integer(hm).toString();
				    if(hissue_m.length()==1)
				    hissue_m="0"+hissue_m;
					String hissue_d=new Integer(hd).toString();
					if(hissue_d.length()==1)
				    hissue_d="0"+hissue_d;
					String HOLIDAYDATE=hissue_d+"/"+hissue_m+"/"+hy;
	return HOLIDAYDATE;
  }
  
  
 String getbooksort(String [] booksort){
 int i=0;
	  	 for (i=0;i<booksort.length;i++)
		{
	  		for(int j=i+1;j<booksort.length;j++)
			{
			 	if(booksort[i].compareToIgnoreCase(booksort[j]) > 0 )
				{
			  		String temp=booksort[i];
			    	booksort[i]=booksort[j];
	  				booksort[j]=temp;
				    
	        	}
	     	}
	    }
		

			return  booksort[i];
		 }
		 
String checkSecurity(int iLevel, javax.servlet.http.HttpSession session, javax.servlet.http.HttpServletResponse response, javax.servlet.http.HttpServletRequest request){
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
       response.sendRedirect("/AutoLib/Login.jsp?querystring=" + toURL(request.getQueryString()) + "&SessionID=" + toURL(session.getId()));
	      }
    }
	catch(Exception e){};
    return "";
  }
  
  
  int MonthCal(int ddf,int mdf,int ydf ,int cm,int dm, int dy){
  int month[]={31,28,31,30,31,30,31,31,30,31,30,31};
  while(mdf!=0 || ydf!=0){
					if(ddf<0 && mdf>0){
					mdf--;
					ddf=ddf+month[(cm-1)-1];
					}
					if(mdf<0 && ydf>0){
					ydf--;
					mdf=mdf+12;
					}
					if(mdf>0){
					mdf--;
					ddf=ddf+month[(dm+1)-1];
					}
					if(ydf>0){
					if((dy+1)%4==0)
					ddf=ddf+366;
					else
					ddf=ddf+365;		
					ydf--;
					}
			}
  return ddf;
  }
  
  
/*Float  FineMasCal(String type,Float amt,int period,int cal){
  double temp=0;
  if (type.equals("DAILY"))
		 {
		 if(cal <= period)
		 temp=temp+(cal * amt);
		 else if(cal > period)
		 {
		 temp=temp+(period * amt);
		 cal=cal-period;

		 }
		 }
		 else if(type.equals("WEEKLY"))
		 {
		 temp=temp+amt;
		 if(cal<=(period * 7))
		 temp=temp;
		 else if(cal >(period * 7))
		 {
		 cal=cal-(period * 7);
		 }
		 }
		 else if(type.equals("MONTHLY"))
		 {
		 temp=temp+amt;
		 if(cal<=(period * 30))
		 temp=temp;
		 else if(cal >(period * 30))
		 {
		 cal=cal-(period * 30);
		 }
		 }
		 else if(type.equals("YEARLY"))
		 {
		 temp=temp+amt;
		 if(cal<=(period * 365))
		 temp=temp;
		 else if(cal >(period * 365))
		 {
		 cal=cal-(period * 365);
		 }
		 }
		 return temp;
  }*/
  
%>
