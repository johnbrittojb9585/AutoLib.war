/*
 * Created on May 8, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package Lib.Auto.BrowseAdvanced;

import java.io.PrintWriter;
import java.io.Serializable;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ResourceBundle;


//import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.UnavailableException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//import Common.Security;


import Common.DBConnection;
import Common.Security;
/**
 * @author Administrator
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class BrowseAdvanced extends HttpServlet implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String flag = "",Title="";
	String SQL_Query = "",title1="",tit="",SQL_Query_Cnt = "",SQL_Query_Con="",SQL_Query_std = "",SQL_Query_cur="",SQL_Query_alpha="";

	int i = 0, j = 0, k = 1, s= 0,len=0;
	int rcount = 0;
	int pcount = 0;
	int top = 0, bottom = 1, txtno = 0;
	int book=0;int non=0;int report=0;int std=0;int thesis=0;int journal=0;int proceed=0;
	int bookbank=0;
	public static final String SQLCNT =
		"select count(access_no) as cnt from full_search where 2>1";
	
	public static final String SQL_Query_view =
		"select * from full_search where 2>1 ";
	
	java.sql.PreparedStatement Prest = null;
	java.sql.Connection con = null;
	java.sql.Statement st = null;
	java.sql.ResultSet rs = null;
	ResourceBundle rb =null;
	

	public void doGet(HttpServletRequest request, HttpServletResponse response) {

		performTask(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) {

		performTask(request, response);

	}
	
	
	
	
	public synchronized void performTask(
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
			
			con=DBConnection.getInstance();
		    st=con.createStatement();
					
			
		    if((request.getParameter("hid"))!= null && request.getParameter("hid").equalsIgnoreCase("search")){
			     
				   	if( !(request.getParameter("text1")).equalsIgnoreCase(""))
					{				   
				   	SQL_Query="and ";
					SQL_Query=SQL_Query+"("+request.getParameter("LIST1");
				   	tit=request.getParameter("LIST1");
				   	if(tit.equals("Title"))
				   	{
				   		title1=request.getParameter("text1");
				   	}
				   	else
				   	{
				   		title1="";
				   	}
				   	
					if(request.getParameter("condition1").equalsIgnoreCase("="))
					SQL_Query=SQL_Query+" = '"+request.getParameter("text1")+"'";
					else if(request.getParameter("condition1").equalsIgnoreCase("start"))
					SQL_Query=SQL_Query+" like '"+request.getParameter("text1")+"%' ";
					else if(request.getParameter("condition1").equalsIgnoreCase("end"))
					SQL_Query=SQL_Query+" like '%"+request.getParameter("text1")+"'";
					else if(request.getParameter("condition1").equalsIgnoreCase("like"))
					SQL_Query=SQL_Query+" like '%"+request.getParameter("text1")+"%' ";
					else if(request.getParameter("condition1").equalsIgnoreCase("word"))
					SQL_Query=SQL_Query+" like '%"+request.getParameter("text1")+"%'";
										
					}


					if( !(request.getParameter("text2")).equalsIgnoreCase(""))
					{

					SQL_Query=SQL_Query+" "+request.getParameter("boolean1")+" "+request.getParameter("LIST2");
					if(request.getParameter("condition2").equalsIgnoreCase("="))
					SQL_Query=SQL_Query+" = '"+request.getParameter("text2")+"'";
					else if(request.getParameter("condition2").equalsIgnoreCase("start"))
					SQL_Query=SQL_Query+" like '"+request.getParameter("text2")+"%' ";
					else if(request.getParameter("condition2").equalsIgnoreCase("end"))
					SQL_Query=SQL_Query+" like '%"+request.getParameter("text2")+"'";
					else if(request.getParameter("condition2").equalsIgnoreCase("like"))
					SQL_Query=SQL_Query+" like '%"+request.getParameter("text2")+"%' ";
					else if(request.getParameter("condition2").equalsIgnoreCase("word"))
					SQL_Query=SQL_Query+" like '%"+request.getParameter("text2")+"%'";
					
					}	
					
					if( !(request.getParameter("text3")).equalsIgnoreCase(""))
					{

					SQL_Query=SQL_Query+" "+request.getParameter("boolean2")+" "+request.getParameter("LIST3");
					if(request.getParameter("condition3").equalsIgnoreCase("="))
					SQL_Query=SQL_Query+" = '"+request.getParameter("text3")+"'";
					else if(request.getParameter("condition3").equalsIgnoreCase("start"))
					SQL_Query=SQL_Query+" like '"+request.getParameter("text3")+"%' ";
					else if(request.getParameter("condition3").equalsIgnoreCase("end"))
					SQL_Query=SQL_Query+" like '%"+request.getParameter("text3")+"'";
					else if(request.getParameter("condition3").equalsIgnoreCase("like"))
					SQL_Query=SQL_Query+" like '%"+request.getParameter("text3")+"%' ";
					else if(request.getParameter("condition3").equalsIgnoreCase("word"))
					SQL_Query=SQL_Query+" like '%"+request.getParameter("text3")+"%'";
					}	
					SQL_Query=SQL_Query+")";
										
					s=1;
					SQL_Query_Cnt = SQL_Query_view;
					SQL_Query_Con = SQL_Query_view + SQL_Query;
					session.setAttribute("SQL_Query", SQL_Query);
					session.setAttribute("SQL_Query_Con", SQL_Query_Con);
					session.setAttribute("SQL_Query_Cnt", SQL_Query_Cnt);
					
						
			}	

			   if (request.getParameter("flag") != null)//request.getParameter("vflag") != null ||
			   {
			   
				if(request.getParameter("flag").equals("next"))
				{
				s=s+1;
				}
				else
				{
					s=s-1;
				}
				SQL_Query = session.getAttribute("SQL_Query").toString();
				SQL_Query_Cnt =	session.getAttribute("SQL_Query_Cnt").toString();
				SQL_Query_Con =	session.getAttribute("SQL_Query_Con").toString();
				if(request.getParameter("tag") != null){
				SQL_Query_cur =	session.getAttribute("SQL_Query_cur").toString();
				}
				session.setAttribute("SQL_Query", SQL_Query);
				session.setAttribute("SQL_Query_Cnt", SQL_Query_Cnt);
						
			}
			   
			   if (request.getParameter("orderby")!= null)//request.getParameter("vflag") != null ||
			   {
			   				
				SQL_Query = session.getAttribute("SQL_Query").toString();
				SQL_Query_Cnt =	session.getAttribute("SQL_Query_Cnt").toString();
				SQL_Query_Con =	session.getAttribute("SQL_Query_Con").toString();
				session.setAttribute("SQL_Query", SQL_Query);
				session.setAttribute("SQL_Query_Cnt", SQL_Query_Cnt);
				String alpha=request.getParameter("order");
				String author=request.getParameter("order");
				if(!alpha.equals(null) && alpha.equals("alpha"))
				{
					SQL_Query_alpha="order by title";
					
				}	
				if(!author.equals(null) && author.equals("author"))
				{
					SQL_Query_alpha="order by Author";
					
					
				}
			
								
			}
			   if(request.getParameter("tag") != null)
			   {	   	
			   	String type=request.getParameter("doc");
			   	SQL_Query = session.getAttribute("SQL_Query").toString();
				SQL_Query_Cnt =session.getAttribute("SQL_Query_Cnt").toString();
				SQL_Query_Con =	session.getAttribute("SQL_Query_Con").toString();
				
				if(type.equals("NON"))
				{
					SQL_Query_cur =" and Document='NON BOOK'";	
				}
				if(type.equals("BOOKBANK"))
				{
					SQL_Query_cur =" and Document='BOOK BANK'";	
				}
				else
				{
					SQL_Query_cur =" and Document ='"+ type+ "'";
				}
				session.setAttribute("SQL_Query", SQL_Query);
				session.setAttribute("SQL_Query_Cnt", SQL_Query_Cnt);
				session.setAttribute("SQL_Query_cur", SQL_Query_cur);
				
			   }
			 
			   Prest =
				con.prepareStatement(
					SQL_Query_Con,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			
			rs = Prest.executeQuery();
			while(rs.next())
			{
				 String Doc=rs.getString("Document");
				 if(Doc.equals("BOOK"))
				 {
				 book=book+1;
				 }
				 if(Doc.equals("BOOK BANK"))
				 {
				 bookbank=bookbank+1;
				 }
				if(Doc.equals("REPORT"))
				 {
				 	report=report+1;
				 }
				 if(Doc.equals("NON BOOK"))
				 {
				 	non=non+1;
				 }
				 if(Doc.equals("PROCEEDING"))
				 {
				 	proceed=proceed+1;
				 }
				 if(Doc.equals("JOURNAL"))
				 {
				 	journal=journal+1;
				 }
				 if(Doc.equals("STANDARD"))
				 {
				 	std=std+1;
				 }
				 if(Doc.equals("THESIS"))
				 {
				 	thesis=thesis+1;
				 }
			}
			
		
			   Prest =con.prepareStatement(
					SQLCNT,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			rs = Prest.executeQuery();
			if (rs.next())
				rcount = rs.getInt("cnt");
				out.println("<html>");
				response.setHeader("Pragma", "No-cache");
				response.setHeader("Cache-Control", "no-cache");
				response.setDateHeader("Expires", 0);
				out.print("<head>");
				out.print("</head>");
				out.println("<BODY  vlink='#of2e4f' alink='#of2e4f'> ");//bgcolor='#C5C5C5'
				out.print("<form name=simple action=./Browseviewad>");
				out.print("<input type=hidden name=flag>");
				out.print("<input type=hidden name=iflag>");
				out.print("<input type=hidden name=len>");
				out.print("<input type=hidden name=val>");
				out.print("<input type=hidden name=bottom>");
				out.print("<input type=hidden name=top>");
				out.print("<center><b><font size=3 color=#0f2e4f face=Verdana>Advanced Search Results</font></b></center>");
				out.print("<div align=right><a href=/AutoLib/BrowseAdvanced/index.jsp><img border='0' src='/AutoLib/images/Back.gif'></a></div>");
				out.print("<table align=center>");
				out.print("<input type=submit size=3 name=View value=View>");
				out.print("<td ><font color='#of2e4f' size='2' face='Verdana'><b>Result page "+s+"&nbsp;<b></font></td>");
				if(book!=0)
				{
				out.print("<td ><font color='#of2e4f' size='2' face='Verdana'>&nbsp;Book(<a href=./AdvancedBrowse?tag=type&doc=BOOK >"+book+"</a>)</td>");
				}
				
				if(bookbank!=0)
				{
				out.print("<td ><font color='#of2e4f' size='2' face='Verdana'>&nbsp;Book Bank(<a href=./AdvancedBrowse?tag=type&doc=BOOKBANK >"+bookbank+"</a>)</td>");
				}
				
				if(non!=0)
				{
				out.print("<td ><font color='#of2e4f' size='2' face='Verdana'>&nbsp;Non Book(<a href=./AdvancedBrowse?tag=type&doc=NON>"+non+"</a>)</td>");
				}
				
				if(std!=0)
				{
				out.print("<td ><font color='#of2e4f' size='2' face='Verdana'>&nbsp;Standard(<a href=./AdvancedBrowse?tag=type&doc=STANDARD>"+std+"</a>)</td>");
				}
				
				if(thesis!=0)
				{
				out.print("<td ><font color='#of2e4f' size='2' face='Verdana'>&nbsp;Thesis(<a href=./AdvancedBrowse?tag=type&doc=THESIS>"+thesis+"</a>)</td>");//style=\"TEXT-DECORATION:none\"
				}
				
				if(proceed!=0)
				{
				out.print("<td ><font color='#of2e4f' size='2' face='Verdana'>&nbsp;Proceeding(<a href=./AdvancedBrowse?tag=type&doc=PROCEEDING>"+proceed+"</a>)</td>");
				}
				
				if(journal!=0)
				{
				out.print("<td ><font color='#of2e4f' size='2' face='Verdana'>&nbsp;Journal(<a href=./AdvancedBrowse?tag=type&doc=JOURNAL >"+journal+"</a>)</td>");
				}
				
				if(report!=0)
				{
				out.print("<td ><font color='#of2e4f' size='2' face='Verdana'>&nbsp;Report(<a href=./AdvancedBrowse?tag=type&doc=REPORT>"+report+"</a>)</td>");
				}
				
				out.print("</table>");
				
				out.print("<TABLE BORDER ='0' cellpadding='0' cellspacing='0' align='center' width='700'>");
				out.print("<TR bgcolor='#AFAFD8'>");

				out.print(
					"<Th align='left'><font size='2' color='#of2e4f' face='Verdana'>  ");
				out.print("View");
				out.print("</Th> ");
				
				out.print(
				"<Th align='right'><font size='2' color='#of2e4f' face='Verdana'> ");
				out.print("Acc.No");
				out.print("</Th>");

				out.print("<Th><font size='2' color='#of2e4f' face='Verdana'>  ");
				out.print("<a href=\"./AdvancedBrowse?orderby=type&order=alpha\">Title</a>");
				out.print("</Th> ");

				out.print(
					"<Th align='center'><font size='2' color='#of2e4f' face='Verdana'> ");
				out.print("<a href=\"./AdvancedBrowse?orderby=type&order=author\">Author</a>");
				out.print("</Th>");

				out.print(
					"<Th align='left'><font size='2' color='#of2e4f' face='Verdana'>  ");
				out.print("Location");
				out.print("</Th>");

				

				out.print("<Th><font size='2' color='#of2e4f' face='Verdana'> ");
				out.print("Status");
				out.print("</Th>");
				
				out.print("<Th><font size='2' color='#of2e4f' face='Verdana'> ");
				out.print("Type");
				out.print("</Th>");

				out.print("</TR>");
				if(request.getParameter("tag") == null && request.getParameter("top")==null)
				{
					SQL_Query_cur="";
				}
				SQL_Query_std = SQL_Query_view + SQL_Query+SQL_Query_cur+SQL_Query_alpha;
				//out.print(SQL_Query_std);
				Prest =
					con.prepareStatement(
						SQL_Query_std,
						ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY);
				
				rs = Prest.executeQuery();
				
				if (rs.next()) {
					
					if (request.getParameter("flag") == null) {
						if (rcount >= 10)
							top = 10;
						else
							top = rcount;
					} else {

						if (request.getParameter("flag").equalsIgnoreCase("next")) {
							bottom =
								Integer.parseInt(request.getParameter("top")) + 1;
							if (rcount - bottom >= 10)
								top = bottom + 9;
							else
								top = rcount;

						}
						if (request.getParameter("flag").equalsIgnoreCase("previous")) {
							top =
								Integer.parseInt(request.getParameter("bottom"))
									- 1;
							if ((top - txtno) >= 10)
								bottom = top - 9;
							else
								bottom = 1;
						}

					}

					for (i = bottom; i <= top; i++) {
						rs.absolute(i);
							
						//String acc=rs.getString(1);
						out.print("<tr>");
						out.print("<td><input type=Checkbox name=code value="+rs.getString("Access_no")+ " ></td>");
						
						
						out.print(
								"<td><font size='2' color='#of2e4f' face='Verdana'>&nbsp;"
									+ rs.getString("Access_No")
									+ "</a></td>");
						
							out.print("<td><font size='2' color='#of2e4f' face='Verdana'>"+ rs.getString("Title")+ "</td>");//<a href=\"./Selectfullview?flag=sa&code="+acc+"\"></a>
			
						
						out.print(
							"<td><font size='2' color='#of2e4f' face='Verdana'>&nbsp;"
								+ rs.getString("Author")
								+ "</td>");
						out.print(
							"<td><font size='2' color='#of2e4f' face='Verdana'>&nbsp;"
								+ rs.getString("Location")
								+ "</td>");
						
						String avl = rs.getString("availability");
						if (avl.equals("ISSUED")) {
						out.print("<td align=left>"+ "<a href=><font size='2' color='#of2e4f' face='Verdana'>"+avl+ "</font></a></td>");//AllSearch/Reserveall.jsp?&flag="../Book_res/Reservebook.jsp?&flag="+rs.getString("Access_No")+ " 
						} else {
							out.print(
								"<td  align=left><font size='2' color='#of2e4f' face='Verdana'>"
									+ rs.getString("availability")
									+ "</font></td>");
						}
						out.print(
								"<td><font size='2' color='#of2e4f' face='Verdana'>&nbsp;"
									+ rs.getString("Document")
									+ "</a></td>");

					}
					rs.close();
				} else {
					response.sendRedirect("/AutoLib/BrowseAdvanced/index.jsp?flags=ok");
				}
				out.print("</table>");
				out.print("<center>");
				
				
				if(top==10){
					out.print("<table width=0% align=center>");
					out.print("<br><td align=center><a href=./AdvancedBrowse?flag=next&top="+top+"><img border='0' src='/AutoLib/images/Next.gif'></td>");
					out.print("</table>");
				}else
				{
					out.print("<table width=0% align=center>");
					if (bottom > 1) {
						
						out.print("<br><td align=left><a href=./AdvancedBrowse?flag=previous&bottom="+bottom+"><img border='0' src='/AutoLib/images/Previous.gif'></td>");
					
					}
					if (top < rcount) {
						out.print("<br><td align=right><a href=./AdvancedBrowse?flag=next&top="+top+"><img border='0' src='/AutoLib/images/Next.gif'></td>");
					}
					out.print("</table>");
				}
				
				
			

				out.print("</center>");
				out.print("</form>");
				out.print("</body>");
				out.print("</html>");
	
			
		} catch (Exception e) {e.printStackTrace();}
		finally {
			SQL_Query = "";
			SQL_Query_Cnt = "";
		
			bottom = 1;
			book=0;
			bookbank=0;
			std=0;
			proceed=0;
			report=0;
			thesis=0;
			journal=0;
			non=0;
			try{
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

}
}
