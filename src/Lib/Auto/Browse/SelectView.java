/*
 * Created on Apr 27, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package Lib.Auto.Browse;
import java.io.PrintWriter;
import java.io.Serializable;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.UnavailableException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;

//import Common.Security;
import Common.DBConnection;
/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class SelectView extends HttpServlet implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String flag=null,SQL="";
	ArrayList DEATILS=new ArrayList ();
	java.sql.Connection con=null;
	java.sql.Statement st=null;
	java.sql.ResultSet rs=null;
	ResourceBundle rb =null;
	
	
	
			public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException {

			performTask(request, response);

		}

		public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException {

			performTask(request, response);

		}
		public synchronized void performTask(
			HttpServletRequest request,
			HttpServletResponse response)  throws ServletException{

		
			try {

				response.setContentType("text/html");
				PrintWriter out = response.getWriter();

				con=DBConnection.getInstance();
				flag=request.getParameter("flag");

				st=con.createStatement();

				out.println("<html>");
				out.print("<head>");
				out.print("</head>");
				out.println("<BODY >");//bgcolor='#COCOCO'
				out.print("<center>");

				out.print("<form name=view");
				

	if(flag!=null)
	{ 
		
	String s="";
	if(flag.equals("book") || flag.equals("journal") || flag.equals("journalatl")){
	String[] itemselected;
	String code="";
	itemselected=request.getParameterValues("code");
	
	
	
	
	
	
	
	if(itemselected!=null){
	for(int i=0;i<itemselected.length;i++){
	code=itemselected[i];
	DEATILS.add(code);
	}
 
	Iterator it=DEATILS.iterator();
	String t="";
	while(it.hasNext()){
	t=(String)it.next();
	if(s.equals(""))
	s="'"+t+"'";
	else
	s=s+",'"+t+"'";
	}
	s="in ("+s+")";
	out.print("<div align=center><font color='#of2e4f' size='2'><b>Search Result Full-View</b></font></div>");
	out.print("<br><br>");
	
	
	if(flag.equals("book")){
	SQL="select * from adv_search_browse  where Access_No "+s+"";
	rs=st.executeQuery(SQL);
	 while(rs.next()){
	 	
		 		
				out.print("<table border=0 width=500 cellspacing=0 cellpadding=0>");
				out.print("<tr>"+"<td width=100>" +"<b>"+"<font color='#of2e4f' size='1'>"+"Access No"+"</font>"+"</b>"+"</td>");
				out.print("<td width=400><b>&nbsp;<font color='#of2e4f' size='1'>"+rs.getString("access_no")+"</font></td>"+"</tr>");
				out.print("<tr>"+"<td width=100>" +"<b>"+"<font color='#of2e4f' size='1'>"+"Call No"+"</font>"+"</b>"+"</td>");
				out.print("<td width=400><b>&nbsp; <font color='#of2e4f' size='1'>"+rs.getString("call_no")+"</font></td>"+"</tr>");
				out.print("<tr>"+"<td width=100>" +"<b>"+"<font color='#of2e4f' size='1'>"+"Author"+"</font>"+"</b>"+"</td>");
				out.print("<td width=400>&nbsp; <font color='#of2e4f' size='1'>"+rs.getString("Author_name")+"</font></td>"+"</tr>");
				out.print("<tr>"+"<td width=100>" +"<b>"+"<font color='#of2e4f' size='1'>"+"Title"+"</font>"+"</b>"+"</td>");
				out.print("<td width=400>&nbsp; <font color='#of2e4f' size='1'>"+rs.getString("Title")+"</font></td>"+"</tr>");
				out.print("<tr>"+"<td width=100>" +"<b>"+"<font color='#of2e4f' size='1'>"+"Edition"+"</font>"+"</b>"+"</td>");
				out.print("<td width=400>&nbsp; <font color='#of2e4f' size='1'>"+rs.getString("Edition")+"</font></td>"+"</tr>");
				out.print("<tr>"+"<td width=100>" +"<b>"+"<font color='#of2e4f' size='1'>"+"Publisher"+"</font>"+"</b>"+"</td>");
				out.print("<td width=400>&nbsp; <font color='#of2e4f' size='1'>"+rs.getString("sp_name")+"</font></td>"+"</tr>");
				out.print("<tr>"+"<td width=100>" +"<b>"+"<font color='#of2e4f' size='1'>"+"Place"+"</font>"+"</b>"+"</td>");
				out.print("<td width=400>&nbsp; <font color='#of2e4f' size='1'>"+rs.getString("place")+"</font></td>"+"</tr>");
				out.print("<tr>"+"<td width=100>" +"<b>"+"<font color='#of2e4f' size='1'>"+"Year"+"</font>"+"</b>"+"</td>");
				out.print("<td width=400>&nbsp; <font color='#of2e4f' size='1'>"+rs.getString("Year_pub")+"</font></td>"+"</tr>");
				out.print("<tr>"+"<td width=100>" +"<b>"+"<font color='#of2e4f' size='1'>"+"Subject"+"</font>"+"</b>"+"</td>");
				out.print("<td width=400>&nbsp; <font color='#of2e4f' size='1'>"+rs.getString("sub_name")+"</font></td>"+"</tr>");

				out.print("<tr>"+"<td width=100>" +"<b>"+"<font color='#of2e4f' size='1'>"+"Availability"+"</font>"+"</b>"+"</td>");
				out.print("<td width=400>&nbsp; <font color='#of2e4f' size='1'>"+rs.getString("Availability")+"</font></td>"+"</tr>");
				out.print("<tr>"+"<td width=100>" +"<b>"+"<font color='#of2e4f' size='1'>"+"Location"+"</font>"+"</b>"+"</td>");
				out.print("<td width=400>&nbsp; <font color='#of2e4f' size='1'>"+rs.getString("Location")+"</font></td>"+"</tr>");
				out.print("<tr>"+"<td width=100>" +"<b>"+"<font color='#of2e4f' size='1'>"+"Keyword"+"</font>"+"</b>"+"</td>");
				out.print("<td width=400>&nbsp; <font color='#of2e4f' size='1'>"+rs.getString("keywords")+"</font></td>"+"</tr>");
				
				out.print("</table>");
				out.print("<br>");		
				}
	}else if(flag.equals("journalatl")){
		SQL="select * from journal_articles  where atl_no "+s+"";
		rs=st.executeQuery(SQL);
		 while(rs.next()){
		 	
			 		
					out.print("<table border=0 width=500 cellspacing=0 cellpadding=0>");
					out.print("<tr>"+"<td width=100>" +"<b>"+"<font color='#of2e4f' size='1'>"+"Article No."+"</font>"+"</b>"+"</td>");
					out.print("<td width=400><b>&nbsp;<font color='#of2e4f' size='1'>"+rs.getString("atl_no")+"</font></td>"+"</tr>");
					out.print("<tr>"+"<td width=100>" +"<b>"+"<font color='#of2e4f' size='1'>"+"Author"+"</font>"+"</b>"+"</td>");
					out.print("<td width=400><b>&nbsp; <font color='#of2e4f' size='1'>"+rs.getString("atl_author")+"</font></td>"+"</tr>");
					out.print("<tr>"+"<td width=100>" +"<b>"+"<font color='#of2e4f' size='1'>"+"Article Title"+"</font>"+"</b>"+"</td>");
					out.print("<td width=400>&nbsp; <font color='#of2e4f' size='1'>"+rs.getString("atl_title")+"</font></td>"+"</tr>");
					out.print("<tr>"+"<td width=100>" +"<b>"+"<font color='#of2e4f' size='1'>"+"Journal Name"+"</font>"+"</b>"+"</td>");
					out.print("<td width=400>&nbsp; <font color='#of2e4f' size='1'>"+rs.getString("jnl_name")+"</font></td>"+"</tr>");
					out.print("<tr>"+"<td width=100>" +"<b>"+"<font color='#of2e4f' size='1'>"+"Year"+"</font>"+"</b>"+"</td>");
					out.print("<td width=400>&nbsp; <font color='#of2e4f' size='1'>"+rs.getString("issue_year")+"</font></td>"+"</tr>");
					out.print("<tr>"+"<td width=100>" +"<b>"+"<font color='#of2e4f' size='1'>"+"Vloume No."+"</font>"+"</b>"+"</td>");
					out.print("<td width=400>&nbsp; <font color='#of2e4f' size='1'>"+rs.getString("vol_no")+"</font></td>"+"</tr>");
					
					out.print("<tr>"+"<td width=100>" +"<b>"+"<font color='#of2e4f' size='1'>"+"Issue Month"+"</font>"+"</b>"+"</td>");
					out.print("<td width=400>&nbsp; <font color='#of2e4f' size='1'>"+rs.getString("issue_month")+"</font></td>"+"</tr>");
					out.print("<tr>"+"<td width=100>" +"<b>"+"<font color='#of2e4f' size='1'>"+"Pages"+"</font>"+"</b>"+"</td>");
					out.print("<td width=400>&nbsp; <font color='#of2e4f' size='1'>"+rs.getString("atl_page_nos")+"</font></td>"+"</tr>");
					out.print("<tr>"+"<td width=100>" +"<b>"+"<font color='#of2e4f' size='1'>"+"Abstract"+"</font>"+"</b>"+"</td>");
					out.print("<td width=400>&nbsp; <font color='#of2e4f' size='1'>"+rs.getString("atl_abstract")+"</font></td>"+"</tr>");
					out.print("<tr>"+"<td width=100>" +"<b>"+"<font color='#of2e4f' size='1'>"+"Subject"+"</font>"+"</b>"+"</td>");
					out.print("<td width=400>&nbsp; <font color='#of2e4f' size='1'>"+rs.getString("atl_subject")+"</font></td>"+"</tr>");
					out.print("<tr>"+"<td width=100>" +"<b>"+"<font color='#of2e4f' size='1'>"+"Keywords"+"</font>"+"</b>"+"</td>");
					out.print("<td width=400>&nbsp; <font color='#of2e4f' size='1'>"+rs.getString("atl_keywords")+"</font></td>"+"</tr>");
					
					out.print("</table>");
					out.print("<br>");		
					}
		}else
		{
		SQL="select * from journal_mas where jnl_code "+s+"";
		
		rs=st.executeQuery(SQL);
		 while(rs.next()){
		 	
			 		
					out.print("<table border=0 width=500 cellspacing=0 cellpadding=0>");
					out.print("<tr>"+"<td width=100>" +"<b>"+"<font color='#of2e4f' size='1'>"+"Journal Code"+"</font>"+"</b>"+"</td>");
					out.print("<td width=400><b>&nbsp;<font color='#of2e4f' size='1'>"+rs.getString("jnl_code")+"</font></td>"+"</tr>");
					out.print("<tr>"+"<td width=100>" +"<b>"+"<font color='#of2e4f' size='1'>"+"Journal Name"+"</font>"+"</b>"+"</td>");
					out.print("<td width=400><b>&nbsp; <font color='#of2e4f' size='1'>"+rs.getString("jnl_name")+"</font></td>"+"</tr>");
					out.print("<tr>"+"<td width=100>" +"<b>"+"<font color='#of2e4f' size='1'>"+"ISSN"+"</font>"+"</b>"+"</td>");
					out.print("<td width=400>&nbsp; <font color='#of2e4f' size='1'>"+rs.getString("ISSN")+"</font></td>"+"</tr>");
					out.print("<tr>"+"<td width=100>" +"<b>"+"<font color='#of2e4f' size='1'>"+"Frequency"+"</font>"+"</b>"+"</td>");
					out.print("<td width=400>&nbsp; <font color='#of2e4f' size='1'>"+rs.getString("frequency")+"</font></td>"+"</tr>");
					out.print("<tr>"+"<td width=100>" +"<b>"+"<font color='#of2e4f' size='1'>"+"Country"+"</font>"+"</b>"+"</td>");
					out.print("<td width=400>&nbsp; <font color='#of2e4f' size='1'>"+rs.getString("country")+"</font></td>"+"</tr>");
					out.print("<tr>"+"<td width=100>" +"<b>"+"<font color='#of2e4f' size='1'>"+"Language"+"</font>"+"</b>"+"</td>");
					out.print("<td width=400>&nbsp; <font color='#of2e4f' size='1'>"+rs.getString("language")+"</font></td>"+"</tr>");
					out.print("</table>");
					out.print("<br>");
		 }
		
	}
	 DEATILS.clear();
	
	}
	else
	{
//	  getServletConfig().getServletContext().getRequestDispatcher("/Simple/RecNot.html").include(request, response);
	out.print("<style=FILTER: glow(color=white,intensity=10); HEIGHT: 15px; weight: 45><b><font color=#of2e4f size=+1>Please Select Record(s)!!!</font></b><br><br><br><br><br><br>");
//	  out.print("<h3>Please Select Record(s)!!!!!!!</h3>");
	}
	}
		
	} 
	out.print("</center>");
	out.print("</form>");
	out.print("</body>");
	out.print("</html>");
		


			
				 } catch (Exception sss) {throw new ServletException(sss);}	
				 finally {
						
						try{
							if(rs!=null){
								rs.close();
								rs=null;
							}
							if(st!=null){
								st.close();
								st=null;
							}

						}catch(Exception e){
						e.printStackTrace();
						}
					}
		  
		}


}

