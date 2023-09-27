package Lib.Auto.AccessionRegister;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Common.Security;

public class Accession_ch extends HttpServlet implements Serializable {
	String PubSup_Mas_Val_code;
	int parse=0;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) {

		performTask(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) {

		performTask(request, response);

	}
	public void performTask(
		HttpServletRequest request,
		HttpServletResponse response) {


		try {
			HttpSession session = request.getSession(true);
			String res = Security.checkSecurity(1, session, response, request);		
			if(res.equalsIgnoreCase("Failure"))
			{
				response.sendRedirect("/AutoLib/Tree/sessiontimeout.jsp");  
				return;
			}
			
			response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            java.sql.PreparedStatement pstmt=null;
	        java.sql.Connection con=null;
			java.sql.Statement st=null;
            java.sql.ResultSet rs=null;
			con=(java.sql.Connection)session.getAttribute("con");
			java.sql.ResultSet rs1=null;
			st=con.createStatement();
			

ArrayList ser=new ArrayList();
	ArrayList sc=new ArrayList();
	accessbean ob=new accessbean();
String Acc_no,Author,Title,Edition,Publisher,Place,Year,Price,NoPage,Callno,Subject,Supplier,Invoice;
String sql="",Flag_Status="",from_acc="",valid="",to_acc="",Received_Date,from_date="",to_date="",fromdate="",todate="";
int rowcount=0;

Flag_Status=request.getParameter("flag");
if(Flag_Status!=null)
{
	from_acc=getParam( request, "From_Accno");
	to_acc=getParam( request, "To_Accno");
	fromdate=from_acc;
	todate=to_acc;
	out.print("<center><h3> Accession Register</center></h3>");
	out.print("<h4>From:"+fromdate+" ");
	out.print("To:"+todate+" ");
	out.print("<table border=1 cellpadding=0 cellspacing=0 >"+"<tr><th width='75'>"+"Sno"+"</th><th width='75'>"+"AccNo"+"</th><th>"+"Author"+"</th><th>"+"Title/Edition"+"</th><th>"+"Publisher/place"+"</th><th>"+"Year"+"</th><th>"+"Price"+"</th><th>"+"Page"+"</th><th>"+"Call_No/Subject"+"</th><th>"+"Supplier/Invoice"+"</th><th>"+"Date"+"</th></tr>");
	int sno=1;

if(Flag_Status.equals("Accessno"))
{
from_acc=getParam( request, "From_Accno");
to_acc=getParam( request, "To_Accno");

char a1[]=from_acc.toCharArray();
char b1[]=to_acc.toCharArray();

 for(int i=0;i<a1.length;i++)
{
	 if(Character.isDigit(a1[i])){
		valid="No";
	}
	else{
		valid="notNo";
		i=i+a1.length;
	}
		
}
for(int i=0;i<b1.length;i++)
{
	if(Character.isDigit(b1[i])){
		valid="No";
	}
	else{
		valid="notNo";
		i=i+b1.length;
	}
		
}  


fromdate=from_acc;
todate=to_acc;
if(valid.equalsIgnoreCase("No"))
{	
	int a=Integer.parseInt(from_acc);
	int b=Integer.parseInt(to_acc);
	if(a>b)
	{			
		String temp;
		temp=from_acc;
		from_acc=to_acc;
			to_acc=temp;
	}
	fromdate=from_acc;
	todate=to_acc;

sql="select * from Sorting_view where n3  between "+from_acc+" and "+to_acc+" order by n1,n2,n3";
}
else
{
	
sql="select * from Sorting_view where access_no between '"+from_acc.toUpperCase()+"' and '"+to_acc.toUpperCase()+"'";
}
pstmt=con.prepareStatement(sql);
rs1=pstmt.executeQuery();

boolean t=rs1.next();
out.print("h is ="+t);
	while(rs1.next())
	/*{
		rowcount++;
	}
	if(rowcount>0)*/
	{
		
		 out.print("<tr><td><font size=2>"+sno+"</td>");
		 out.print("<td>"+rs1.getString("access_no")+"</td>");
		 out.print("<td>"+rs1.getString("Author_name")+"</td>");
		 out.print("<td>"+rs1.getString("title")+"/"+rs1.getString("Edition")+"</td>");
		 out.print("<td>"+rs1.getString("publisher")+"/"+rs1.getString("pubplace")+"</td>");
		 out.print("<td>"+rs1.getString("year_pub")+"</td>");
		 out.print("<td>"+rs1.getString("Bprice")+"</td>");
		 out.print("<td>"+rs1.getString("pages")+"</td>");
		 out.print("<td>"+rs1.getString("call_no")+"/"+rs1.getString("sub_name")+"</td>");
		 out.print("<td>"+rs1.getString("supplier")+"/"+rs1.getString("invoice_no")+"</td>");
		 out.print("<td>"+getdate(rs1.getString("Received_Date"))+"</td>");

		 out.print("</tr>");
		 sno=sno+1;
		 rowcount--;
		
			
	}
	if(!t)
	{
		getServletConfig().getServletContext().getRequestDispatcher("/AccessionRegister/index.jsp?check=RecordNot").forward(request, response);
	}
	
	}


if(Flag_Status.equals("bydate"))
{
from_date=getdate(request.getParameter("from"));
to_date=getdate(request.getParameter("to"));
fromdate=getholiday(from_date);
todate=getholiday(to_date);


 java.util.StringTokenizer stz=new java.util.StringTokenizer(from_date,"-");
                          int cy=Integer.parseInt(stz.nextToken());
                          int cm=Integer.parseInt(stz.nextToken());
                          int cd=Integer.parseInt(stz.nextToken());
	        	  java.util.Calendar bir = new java.util.GregorianCalendar();
			  bir.set(cy,cm,cd);


		         stz=new java.util.StringTokenizer(to_date,"-");
                         int sy=Integer.parseInt(stz.nextToken());
                         int sm=Integer.parseInt(stz.nextToken());
                         int sd=Integer.parseInt(stz.nextToken());
	        	 java.util.Calendar enr = new java.util.GregorianCalendar();
			 enr.set(sy,sm,sd);

			  if((bir).after(enr)){
//getServletConfig().getServletContext().getRequestDispatcher("/AccessionRegister/index.jsp?check=date").forward(request, response);
				  String temp;
				  temp=from_date;
				  from_date=to_date;
				  to_date=temp;
				  fromdate=getholiday(from_date);
				  todate=getholiday(to_date);


		}

 sql="select * from Sorting_view where received_date between '"+from_date+"' and '"+to_date+"'";
 pstmt=con.prepareStatement(sql);
	rs=pstmt.executeQuery();
	boolean t=rs.next();
	out.print("h is ="+t);
	
		while(rs.next())
		{

			out.print("<tr><td><font size=2>"+sno+"</td>");
			 out.print("<td>"+rs1.getString("access_no")+"</td>");
			 out.print("<td>"+rs1.getString("Author_name")+"</td>");
			 out.print("<td>"+rs1.getString("title")+"/"+rs1.getString("Edition")+"</td>");
			 out.print("<td>"+rs1.getString("publisher")+"/"+rs1.getString("pubplace")+"</td>");
			 out.print("<td>"+rs1.getString("year_pub")+"</td>");
			 out.print("<td>"+rs1.getString("Bprice")+"</td>");
			 out.print("<td>"+rs1.getString("pages")+"</td>");
			 out.print("<td>"+rs1.getString("call_no")+"/"+rs1.getString("sub_name")+"</td>");
			 out.print("<td>"+rs1.getString("supplier")+"/"+rs1.getString("invoice_no")+"</td>");
			 out.print("<td>"+getdate(rs1.getString("Received_Date"))+"</td>");

			 out.print("</tr>");
			 sno=sno+1;
			 rowcount--;
			
	}
		if(!t)
		{
			getServletConfig().getServletContext().getRequestDispatcher("/AccessionRegister/index.jsp?check=RecordNot").forward(request, response);
		}
		
}
		}



 } catch (Exception e) {
					handleError(request, response, e);
					}
		    catch (Throwable theException) {
			handleError(request, response, theException);
		   }

	}
	String getdate(String datechk) {
       java.util.StringTokenizer stz_is=new java.util.StringTokenizer(datechk,"-");
		    int bid=Integer.parseInt(stz_is.nextToken());
		     int bim=Integer.parseInt(stz_is.nextToken());
		    int biy=Integer.parseInt(stz_is.nextToken());
	     	   String bissue_m=new Integer(bim).toString();
				    if(bissue_m.length()==1)
				    bissue_m="0"+bissue_m;
					String bissue_d=new Integer(bid).toString();
					if(bissue_d.length()==1)
				    bissue_d="0"+bissue_d;
					String BISSDATE=biy+"-"+bissue_m+"-"+bissue_d;
	return BISSDATE;
  }
  String getholiday(String holiday) {
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
					String HOLIDAYDATE=hissue_d+"-"+hissue_m+"-"+hy;
	return HOLIDAYDATE;
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

	
	public void handleError(
		HttpServletRequest request,
		HttpServletResponse response,
		Throwable e) {
		try {
			String message = e.getLocalizedMessage();
			String stackTrace = printStackToString(e);
			response.sendError(
				HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
				message + "<BR><BR><PRE>\n" + stackTrace + "</PRE>");
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	public static synchronized String printStackToString(Throwable aThrowable) {
		try {
			ByteArrayOutputStream stream = new ByteArrayOutputStream();
			PrintWriter aPrintWriter = new PrintWriter(stream, true);
			aThrowable.printStackTrace(aPrintWriter);
			aPrintWriter.flush();
			aPrintWriter.close();
			stream.flush();
			stream.close();
			return stream.toString();
		} catch (Throwable ex) {
			//** could not format Throwable as String. return simple toString()
			return aThrowable.toString();
		}
	}

	/**
	 * Instance variable for SQL statement property
	 */
	protected java.lang.String SQLStringCode ="select max(Sp_code) as maxno from Sup_Pub_mas";
	
	

	
	
}
