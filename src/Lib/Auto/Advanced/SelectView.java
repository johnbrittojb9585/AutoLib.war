/*
 * Created on Apr 27, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package Lib.Auto.Advanced;
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
				out.println("<BODY background='/AutoLib/soft.jpg'>");
				out.print("<center>");
				out.print("<div align=right><a href=/AutoLib/Advanced/index.jsp><img border='0' src='/AutoLib/images/Back.gif'></a></div>");
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
	out.print("<font color='#800000' size='5'>Advanced Search Result Full-View</font>");
	
	
	// out.print(s);
	 //String cheange_acc=s;
	 //java.util.StringTokenizer stringst=new java.util.StringTokenizer(cheange_acc,"/");
	//String acc_no_doc=stringst.nextToken();
//	out.print(acc_no_doc);
	
	
	
	SQL="select * from full_search  where Access_No "+s+"";
	rs=st.executeQuery(SQL);
	 while(rs.next()){
	 	
	 	
				out.print("<table border=1 bordercolor=blue width=480 height=202 cellspacing=0 cellpadding=0>");
				out.print("<tr>"+"<td >" +"<b>"+"<font color='#800000' size='3'>"+"Access No"+"</font>"+"</b>"+"</td>");
				out.print("<td><b>"+rs.getString("access_no")+"</td>"+"</tr>");
				out.print("<tr>"+"<td >" +"<b>"+"<font color='#800000' size='3'>"+"Call No"+"</font>"+"</b>"+"</td>");
				out.print("<td><b>&nbsp;"+rs.getString("call_no")+"</td>"+"</tr>");
				out.print("<tr>"+"<td>" +"<b>"+"<font color='#800000' size='3'>"+"Author"+"</font>"+"</b>"+"</td>");
				out.print("<td>&nbsp;"+rs.getString("Author")+"</td>"+"</tr>");
				out.print("<tr>"+"<td >" +"<b>"+"<font color='#800000' size='3'>"+"Title"+"</font>"+"</b>"+"</td>");
				out.print("<td>&nbsp;"+rs.getString("Title")+"</td>"+"</tr>");
				out.print("<tr>"+"<td >" +"<b>"+"<font color='#800000' size='3'>"+"Edition"+"</font>"+"</b>"+"</td>");
				out.print("<td>&nbsp;"+rs.getString("Edition")+"</td>"+"</tr>");
				out.print("<tr>"+"<td >" +"<b>"+"<font color='#800000' size='3'>"+"Supplier"+"</font>"+"</b>"+"</td>");
				out.print("<td>&nbsp;"+rs.getString("supplier")+"</td>"+"</tr>");
				out.print("<tr>"+"<td >" +"<b>"+"<font color='#800000' size='3'>"+"Place"+"</font>"+"</b>"+"</td>");
				out.print("<td>&nbsp;"+rs.getString("place")+"</td>"+"</tr>");
				out.print("<tr>"+"<td >" +"<b>"+"<font color='#800000' size='3'>"+"Year"+"</font>"+"</b>"+"</td>");
				out.print("<td>&nbsp;"+rs.getString("Year")+"</td>"+"</tr>");
				out.print("<tr>"+"<td >" +"<b>"+"<font color='#800000' size='3'>"+"Price"+"</font>"+"</b>"+"</td>");
				out.print("<td>&nbsp;"+rs.getString("price")+"</td>"+"</tr>");
				//out.print("<tr>"+"<td >" +"<b>"+"<font color='#800000' size='3'>"+"Department"+"</font>"+"</b>"+"</td>");
				//out.print("<td>"+rs.getString(9)+"</td>"+"</tr>");
				//out.print("<tr>"+"<td >" +"<b>"+"<font color='#800000' size='3'>"+"Subject"+"</font>"+"</b>"+"</td>");
				//out.print("<td>"+rs.getString(8)+"</td>"+"</tr>");
				//out.print("<tr>"+"<td >" +"<b>"+"<font color='#800000' size='3'>"+"Key Words"+"</font>"+"</b>"+"</td>");
				//out.print("<td>"+rs.getString(15)+"</td>"+"</tr>");
				out.print("<tr>"+"<td >" +"<b>"+"<font color='#800000' size='3'>"+"Availability"+"</font>"+"</b>"+"</td>");
				out.print("<td>&nbsp;"+rs.getString("Availability")+"</td>"+"</tr>");
				out.print("<tr>"+"<td >" +"<b>"+"<font color='#800000' size='3'>"+"Location"+"</font>"+"</b>"+"</td>");
				out.print("<td>&nbsp;"+rs.getString("Location")+"</td>"+"</tr>");
				out.print("</table>");
				out.print("<br><br>");		
				}
	 DEATILS.clear();
	
	
	
	
	
	
	
	
	
	
	
	//DEATILS.clear();
	}
	else
	{
//	  getServletConfig().getServletContext().getRequestDispatcher("/Simple/RecNot.html").include(request, response);
	out.print("<style=FILTER: glow(color=white,intensity=10); HEIGHT: 15px; weight: 45><b><font color=#800000 size=+4>Please Select Record(s)!!!</font></b><br><br><br><br><br><br>");
//	  out.print("<h3>Please Select Record(s)!!!!!!!</h3>");
//	  out.print("<p>Please Select Record(s)</p>");
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

