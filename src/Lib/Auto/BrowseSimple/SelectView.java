/*
 * Created on Apr 27, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package Lib.Auto.BrowseSimple;
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
				out.println("<BODY >");//bgcolor='#C5C5C5'
				out.print("<center>");
				out.print("<div align=right><a href=/AutoLib/BrowseSimple/index.jsp><img border='0' src='/AutoLib/images/Back.gif'></a></div>");
				out.print("<form name=view");
				

	if(flag!=null)
	{ 
		
	String s="";
	if(flag.equals("")){
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
	out.print("<div align=center><font color='#of2e4f' size='4'>Simple Search Result Full-View</font></div>");
	out.print("<br>");
	
	SQL="select * from adv_search_browse  where Access_No "+s+"";
	rs=st.executeQuery(SQL);
	 while(rs.next()){
	 	
	 	
				out.print("<table border=0  width=500 height=202 cellspacing=0 cellpadding=0>");
				out.print("<tr>"+"<td width=100>" +"<b>"+"<font color='#of2e4f' size='1'>"+"Access No"+"</font>"+"</b>"+"</td>");
				out.print("<td width=400><b>&nbsp;<font color='#of2e4f' size='1'>"+rs.getString("access_no")+"</font></td>"+"</tr>");
				out.print("<tr>"+"<td width=100>" +"<b>"+"<font color='#of2e4f' size='1'>"+"Call No"+"</font>"+"</b>"+"</td>");
				out.print("<td width=400><b>&nbsp;<font color='#of2e4f' size='1'>"+rs.getString("call_no")+"</font></td>"+"</tr>");
				out.print("<tr>"+"<td>" +"<b>"+"<font color='#of2e4f' size='1'>"+"Author"+"</font>"+"</b>"+"</td>");
				out.print("<td width=400>&nbsp;<font color='#of2e4f' size='1'>"+rs.getString("Author_name")+"</font></td>"+"</tr>");
				out.print("<tr>"+"<td width=100>" +"<b>"+"<font color='#of2e4f' size='1'>"+"Title"+"</font>"+"</b>"+"</td>");
				out.print("<td width=400>&nbsp;<font color='#of2e4f' size='1'>"+rs.getString("Title")+"</font></td>"+"</tr>");
				out.print("<tr>"+"<td width=100>" +"<b>"+"<font color='#of2e4f' size='1'>"+"Edition"+"</font>"+"</b>"+"</td>");
				out.print("<td width=400>&nbsp;<font color='#of2e4f' size='1'>"+rs.getString("Edition")+"</font></td>"+"</tr>");
				out.print("<tr>"+"<td width=100>" +"<b>"+"<font color='#of2e4f' size='1'>"+"Publisher"+"</font>"+"</b>"+"</td>");
				out.print("<td width=400>&nbsp;<font color='#of2e4f' size='1'>"+rs.getString("sp_name")+"</font></td>"+"</tr>");
				out.print("<tr>"+"<td width=100>" +"<b>"+"<font color='#of2e4f' size='1'>"+"Place"+"</font>"+"</b>"+"</td>");
				out.print("<td width=400>&nbsp;<font color='#of2e4f' size='1'>"+rs.getString("place")+"</font></td>"+"</tr>");
				out.print("<tr>"+"<td width=100>" +"<b>"+"<font color='#of2e4f' size='1'>"+"Year"+"</font>"+"</b>"+"</td>");
				out.print("<td width=400>&nbsp;<font color='#of2e4f' size='1'>"+rs.getString("Year_pub")+"</font></td>"+"</tr>");
				out.print("<tr>"+"<td width=100>" +"<b>"+"<font color='#of2e4f' size='1'>"+"Subject"+"</font>"+"</b>"+"</td>");
				out.print("<td width=400>&nbsp; <font color='#of2e4f' size='1'>"+rs.getString("sub_name")+"</font></td>"+"</tr>");
				
			
				out.print("<tr>"+"<td width=100>" +"<b>"+"<font color='#of2e4f' size='1'>"+"Availability"+"</font>"+"</b>"+"</td>");
				out.print("<td width=400>&nbsp;<font color='#of2e4f' size='1'>"+rs.getString("Availability")+"</font></td>"+"</tr>");
				out.print("<tr>"+"<td width=100>" +"<b>"+"<font color='#of2e4f' size='1'>"+"Location"+"</font>"+"</b>"+"</td>");
				out.print("<td width=400>&nbsp;<font color='#of2e4f' size='1'>"+rs.getString("Location")+"</font></td>"+"</tr>");
				out.print("<tr>"+"<td width=100>" +"<b>"+"<font color='#of2e4f' size='1'>"+"Keyword"+"</font>"+"</b>"+"</td>");
				out.print("<td width=400>&nbsp; <font color='#of2e4f' size='1'>"+rs.getString("keywords")+"</font></td>"+"</tr>");
				
				out.print("</table>");
				out.print("<br><br>");		
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

