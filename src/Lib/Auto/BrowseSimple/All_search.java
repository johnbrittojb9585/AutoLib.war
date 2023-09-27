/*
 * Created on Apr 27, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package Lib.Auto.BrowseSimple;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
import java.io.PrintWriter;
import java.io.Serializable;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Common.Security;

public class All_search extends HttpServlet implements Serializable {

	java.sql.Connection con = null;
	java.sql.PreparedStatement Prest = null;
	java.sql.ResultSet rs = null;
	java.sql.ResultSet rs_cd = null;
	java.sql.Statement st=null;
	
	String flag = "",title1="";
	String SQL_Query = "", SQL_Query_Cnt = "",SQL_Query_Con="",SQL_Query_std = "",SQL_Query_cur="",SQL_Query_alpha="";

	int i = 0, j = 0, k = 1, s= 0,len=0;
	int rcount = 0;
	int pcount = 0;
	int top = 0, bottom = 1, txtno = 0;
	int book=0;int non=0;int report=0;int std=0;int thesis=0;int journal=0;int proceed=0;

	public static final String SQLCNT =
		"select count(access_no) as cnt from full_search where 2>1";
	
	public static final String SQL_Query_view =
		"select  * from full_search where 2>1";
	public static final String CD_VIEW ="select * from full_search where  remarks like '%+CD%' and access_no=?";
		
	public void service(
		HttpServletRequest request,
		HttpServletResponse response)
		throws ServletException {

		try {
			
			HttpSession session = request.getSession(true);
			String res = Security.checkSecurity(1, session, response, request);		
			if(res.equalsIgnoreCase("Failure"))
			{
				response.sendRedirect("/AutoLib/Tree/sessiontimeout.jsp");  
				return;
			}
			
			Security Con_Object = new Security();
			
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			con = Con_Object.getConnection();
			
			out.println("<html>");
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
			out.print("<head>");
			out.print("</head>");
			out.println("<BODY bgcolor=#FFFFFF background=images/new.JPG> ");
			out.print("<form name=simple action=./Selectfullview>");
			out.print("<input type=hidden name=flag>");
			out.print("<input type=hidden name=iflag>");
			out.print("<input type=hidden name=len>");
			out.print("<input type=hidden name=val>");
			out.print("<input type=hidden name=bottom>");
			out.print("<input type=hidden name=top>");
			
		   if (request.getParameter("hid") != null&& request.getParameter("hid").equals("search")) {
			
				if (!Security.getParam(request, "Title").equals("")) {
				
					
					SQL_Query =
						SQL_Query
							+ " and title like '%"
							+ Security.getParam(request, "Title")
							+ "%'";
				}

			    	
				             if (!Security.getParam(request, "Publisher").equals("")) {
					
					SQL_Query =
						SQL_Query
							+ " and publisher like '%"
							+ Security.getParam(request, "Publisher")
							+ "%'";
				}
				
				
				
				if (!Security.getParam(request, "Keywords").equals("")) {
					
					SQL_Query =
						SQL_Query
							+ " and keywords like '%"
							+ Security.getParam(request, "Keywords")
							+ "%'";
				}
				
				
				if (!Security.getParam(request, "Author").equals("")) {
					
					SQL_Query =
						SQL_Query
							+ " and author like '%"
							+ Security.getParam(request, "Author")
							+ "%'";
				}
				
				if (!Security.getParam(request, "Subject").equals("")) {
					
					SQL_Query =
						SQL_Query
							+ " and subject like '%"
							+ Security.getParam(request, "subject")
							+ "%'";
				}
				
				
				if (!Security.getParam(request, "Year").equals("")) {
					
					SQL_Query =
						SQL_Query
							+ " and year like  '%"
							+ Security.getParam(request, "Year")
							+ "%'";
				}
							
				if (!Security.getParam(request, "accno").equals("")) {
					;
					SQL_Query =
						SQL_Query
							+ " and Access_No ='"
							+ Security.getParam(request, "accno")
							+ "'";
				}
				
				
				if(!Security.getParam(request, "isbn").equals("")) {
					
					SQL_Query =
						SQL_Query
							+ " and isbn like  '%"
							+ Security.getParam(request, "isbn")
							+ "%'";
				}
				s=1;
				
				SQL_Query_Cnt = SQL_Query_view;
				SQL_Query_Con = SQL_Query_view + SQL_Query;
				session.setAttribute("SQL_Query", SQL_Query);
				session.setAttribute("SQL_Query_Con", SQL_Query_Con);
				session.setAttribute("SQL_Query_Cnt", SQL_Query_Cnt);
			
			}
		   
		   
		   
		   
		   
		   if (request.getParameter("flag") != null)//request.getParameter("vflag") != null ||
		   {
		   	//out.print("Cursor here saravnanan");
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
		   	out.print("<center><b><font size=4 color=#0f2e4f face=Verdana>Simple Search Results</font></b></center>");
			out.print("<br><table width=0% align=right>");
			out.print("<td align=right><B><a href=\"./All_Search.html\" style=\"TEXT-DECORATION:none\">HOME</a></B></td>");
			out.print("</table>");
			out.print("<table align=center>");
			
			out.print("<input type=submit size=3 name=View value=View>");
			
			out.print("<td ><font color='#of2e4f' size='2' face='Verdana'><b>Result page "+s+"&nbsp;<b></font></td>");
			if(book!=0)
			{
			out.print("<td ><font color='#of2e4f' size='2' face='Verdana'>&nbsp;Book(<a href=./AllsearchServlet?tag=type&doc=BOOK >"+book+"</a>)</td>");
			}
			
			if(non!=0)
			{
			out.print("<td ><font color='#of2e4f' size='2' face='Verdana'>&nbsp;Non Book(<a href=./AllsearchServlet?tag=type&doc=NON>"+non+"</a>)</td>");
			}
			
			if(std!=0)
			{
			out.print("<td ><font color='#of2e4f' size='2' face='Verdana'>&nbsp;Standard(<a href=./AllsearchServlet?tag=type&doc=STANDARD>"+std+"</a>)</td>");
			}
			
			if(thesis!=0)
			{
			out.print("<td ><font color='#of2e4f' size='2' face='Verdana'>&nbsp;Thesis(<a href=./AllsearchServlet?tag=type&doc=THESIS>"+thesis+"</a>)</td>");//style=\"TEXT-DECORATION:none\"
			}
			
			if(proceed!=0)
			{
			out.print("<td ><font color='#of2e4f' size='2' face='Verdana'>&nbsp;Proceeding(<a href=./AllsearchServlet?tag=type&doc=PROCEEDING>"+proceed+"</a>)</td>");
			}
			
			if(journal!=0)
			{
			out.print("<td ><font color='#of2e4f' size='2' face='Verdana'>&nbsp;Journal(<a href=./AllsearchServlet?tag=type&doc=JOURNAL >"+journal+"</a>)</td>");
			}
			
			if(report!=0)
			{
			out.print("<td ><font color='#of2e4f' size='2' face='Verdana'>&nbsp;Report(<a href=./AllsearchServlet?tag=type&doc=REPORT>"+report+"</a>)</td>");
			}
			
			out.print("</table><br>");
			
			out.print("<TABLE BORDER =1 cellpadding='0' cellspacing='0' align='center' width='800'>");
			out.print("<TR bgcolor='#AFAFD8'>");
			
			out.print(
				"<Th align='left'><font size='2' color='#of2e4f' face='Verdana'>  ");
			out.print("View");
			out.print("</Th> ");

			out.print("<Th><font size='2' color='#of2e4f' face='Verdana'>  ");
			out.print("<a href=\"./AllsearchServlet?orderby=type&order=alpha\">Title</a>");
			out.print("</Th> ");

			out.print(
				"<Th align='center'><font size='2' color='#of2e4f' face='Verdana'> ");
			out.print("<a href=\"./AllsearchServlet?orderby=type&order=author\">Author</a>");
			out.print("</Th>");

			out.print(
				"<Th align='left'><font size='2' color='#of2e4f' face='Verdana'>  ");
			out.print("Location");
			out.print("</Th>");

			out.print(
				"<Th align='right'><font size='2' color='#of2e4f' face='Verdana'> ");
			out.print("Acc.No");
			out.print("</Th>");

			out.print("<Th><font size='2' color='#of2e4f' face='Verdana'> ");
			out.print("Status");
			out.print("</Th>");
			
			out.print("<Th><font size='2' color='#of2e4f' face='Verdana'> ");
			out.print("Type");
			out.print("</Th>");

			out.print("</TR>");
			
			SQL_Query_std = SQL_Query_Cnt + SQL_Query+SQL_Query_cur+SQL_Query_alpha;
			
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
						
					String acc=rs.getString(1);
					out.print("<tr>");
					out.print("<td><input type=Checkbox name=code value="+ acc+ " ></td>");
					
						out.print("<td><font size='2' color='#of2e4f' face='Verdana'>"+ rs.getString("Title")+ "</td>");//<a href=\"./Selectfullview?flag=sa&code="+acc+"\"></a>
				
						
					out.print(
						"<td><font size='2' color='#of2e4f' face='Verdana'>&nbsp;"
							+ rs.getString("Author")
							+ "</td>");
					out.print(
						"<td><font size='2' color='#of2e4f' face='Verdana'>&nbsp;"
							+ rs.getString("Location")
							+ "</td>");
					out.print(
						"<td><font size='2' color='#of2e4f' face='Verdana'>&nbsp;"
							+ rs.getString("Access_No")
							+ "</a></td>");
					String avl = rs.getString("availability");
					if (avl.equals("ISSUED")) {
					out.print("<td align=left>"+ "<a href=Book/Reservebook.jsp?&flag="+rs.getString("Access_No")+ " ><font size='2' color='#of2e4f' face='Verdana'>"+avl+ "</font></a></td>");//AllSearch/Reserveall.jsp?&flag="
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
			out.print("</table>");
			
			out.print("<center>");
			
			if(top==10){
				out.print("<table width=0% align=center>");
				out.print("<br><td align=center><a href=./AllsearchServlet?flag=next&top="+top+"><img border='0' src='Image/Next.gif'></td>");
				out.print("</table>");
			}else
			{
				out.print("<table width=0% align=center>");
				if (bottom > 1) {
					
					out.print("<br><td align=left><a href=./AllsearchServlet?flag=previous&bottom="+bottom+"><img border='0' src='Image/Previous.gif'></td>");
				
				}
				if (top < rcount) {
					out.print("<br><td align=right><a href=./AllsearchServlet?flag=next&top="+top+"><img border='0' src='Image/Next.gif'></td>");
				}
				out.print("</table>");
			}
			
			
			
			out.print("</center>");
			
			
			out.print("</form>");
			out.print("</body>");
			out.print("</html>");
			} else {
				response.sendRedirect("/portal/All_Search/All_SearchNotFound.jsp");
			}
			
		} catch (Exception sss) {
			sss.printStackTrace();
			//throw new ServletException(sss);
		}

		

		finally {
			SQL_Query = "";
			SQL_Query_Cnt = "";
		
			bottom = 1;
			book=0;
			std=0;
			proceed=0;
			report=0;
			thesis=0;
			journal=0;
			non=0;
		}

	}
}
