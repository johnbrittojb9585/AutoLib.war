package Lib.Auto.Browse;

import java.io.PrintWriter;
import java.io.Serializable;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.UnavailableException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Common.Security;


import Common.DBConnection;

public class Alldocument extends HttpServlet implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	java.sql.Connection con = null;
	java.sql.PreparedStatement Prest = null;
	java.sql.ResultSet rs = null;
	java.sql.ResultSet rs_cd = null;
	java.sql.Statement st=null;
	ResourceBundle rb =null;
	
	String flag = "",title1="";
	String SQL_Query = "", SQL_Query_Cnt = "",SQL_Query_Con="",SQL_Query_std = "",SQL_Query_cur="",SQL_Query_alpha="";

	int i = 0, j = 0, k = 1, s= 0,len=0;
	int rcount = 0;
	int pcount = 0;
	int top = 0, bottom = 1, txtno = 0;
	int book=0;int non=0;int report=0;int std=0;int thesis=0;int journal=0;int proceed=0;
	int bookbank=0;
	public static final String SQLCNT =
		"select count(access_no) as cnt from adv_search_browse where 2>1";

	public static final String SQL_Query_view =
		"select  * from adv_search_browse where ";
	
	public static final String journals =
		"select  * from journal_mas where ";
	
	public static final String journalsatl =
		"select  * from journal_articles where ";
	
	public static final String CD_VIEW ="select * from full_search where  remarks like '%+CD%' and access_no=?";


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
			
			out.println("<html>");
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
			out.print("<head>");
			out.print("</head>");
			out.println("<BODY>");//background='/AutoLib/soft.jpg'bgcolor='#COCOCO'
			out.print("<form name=simple action=./BrowseFull>");
			if(Security.getParam(request, "ignore").equals("jnl_name"))
			{
				out.print("<input type=hidden name=flag value=journal>");
			}else if(Security.getParam(request, "ignore").equals("jnl_atl"))
			{
				out.print("<input type=hidden name=flag value=journalatl>");
			}else
			{
				out.print("<input type=hidden name=flag value=book>");
			}
			out.print("<input type=hidden name=iflag>");
			out.print("<input type=hidden name=len>");
			out.print("<input type=hidden name=val>");
			out.print("<input type=hidden name=bottom>");
			out.print("<input type=hidden name=top>");
			
		   if (request.getParameter("hid") != null&& request.getParameter("hid").equals("search")) {
			 
			   if(Security.getParam(request, "ignore").equals("jnl_name"))
			   {
				   
				   if(!Security.getParam(request, "name").equals(""))
					{
					SQL_Query =
						SQL_Query
						+ "(jnl_name like '%"
						+ Security.getParam(request, "name")
						+ "%' or issn like '%"
						+ Security.getParam(request, "name")
						+ "%' or frequency like '%"
						+ Security.getParam(request, "name")
						+ "%' or country like '%"
						+ Security.getParam(request, "name")
						+ "%' or language like '%"
						+ Security.getParam(request, "name")
						+ "%')";
												
					}
				   	
				    s=1;
				    SQL_Query_Cnt = journals;
					SQL_Query_Con = journals + SQL_Query;
					session.setAttribute("SQL_Query", SQL_Query);
					session.setAttribute("SQL_Query_Con", SQL_Query_Con);
					session.setAttribute("SQL_Query_Cnt", SQL_Query_Cnt);
				   
				   
			   }else if(Security.getParam(request, "ignore").equals("jnl_atl"))
			   {
				   
				   if(!Security.getParam(request, "name").equals(""))
					{
					SQL_Query =
						SQL_Query
						+ "(jnl_name like '%"
						+ Security.getParam(request, "name")
						+ "%' or atl_title like '%"
						+ Security.getParam(request, "name")
						+ "%' or atl_author like '%"
						+ Security.getParam(request, "name")
						+ "%' or atl_subject like '%"
						+ Security.getParam(request, "name")
						+ "%')";
										
					}
				   	
				    s=1;
				    SQL_Query_Cnt = journalsatl;
					SQL_Query_Con = journalsatl + SQL_Query;
					session.setAttribute("SQL_Query", SQL_Query);
					session.setAttribute("SQL_Query_Con", SQL_Query_Con);
					session.setAttribute("SQL_Query_Cnt", SQL_Query_Cnt);
				   
				   
			   }else
			   {
					if(!Security.getParam(request, "name").equals(""))
					{
						
					SQL_Query =
						SQL_Query
						+ "(title like '%"
						+ Security.getParam(request, "name")
						+ "%' or sp_name like '%"
						+ Security.getParam(request, "name")
						+ "%' or year_pub like '%"
						+ Security.getParam(request, "name")
						+ "%' or author_name like '%"
						+ Security.getParam(request, "name")
						+ "%' or sub_name like '%"
						+ Security.getParam(request, "name")
						+ "%')";
					
					}
					if(!Security.getParam(request, "ignore").equals(""))
					{
						if(Security.getParam(request, "ignore").equals("Any"))
						{
							
						}
						else
						{
						
							
					SQL_Query =
						SQL_Query
							+ " and Document = '"
							+ Security.getParam(request, "ignore")
							+ "'  ";
							
						}
					
					}
		  
				
				
				s=1;

				SQL_Query_Cnt = SQL_Query_view;
				SQL_Query_Con = SQL_Query_view + SQL_Query;
				session.setAttribute("SQL_Query", SQL_Query);
				session.setAttribute("SQL_Query_Con", SQL_Query_Con);
				session.setAttribute("SQL_Query_Cnt", SQL_Query_Cnt);
				
			}
		   }
		   
		   
		   
		   
		   
		   if (request.getParameter("flag") != null)
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
		   if(!Security.getParam(request, "ignore").equals("jnl_name") && !Security.getParam(request, "ignore").equals("jnl_atl"))
		   {
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
		   }
		
		   Prest =con.prepareStatement(
				SQLCNT,
				ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_READ_ONLY);
		rs = Prest.executeQuery();
		if (rs.next())
			rcount = rs.getInt("cnt");
		   	
		   	
			out.print("<table>");
			out.print("<tr><td width=450 align=left><B><a href=\"/AutoLib/BrowseSimple/index.jsp\" style=\"TEXT-DECORATION:none\"><font color='#of2e4f' size='2' face='Verdana'>Simple Search</font></a></B></td>");
			out.print("<td width=250 align=right><B><a href=\"/AutoLib/BrowseAdvanced/index.jsp\" style=\"TEXT-DECORATION:none\"><font color='#of2e4f' size='2' face='Verdana'>Advanced Search</font></a></B></td>");
			out.print("</tr></table>");
			out.print("<center><b><font size=2 color=#0f2e4f face=Verdana>Search Results !</font></b></center>");
			out.print("<table align=center>");

			out.print("<td ><font color='#of2e4f' size='2' face='Verdana'><b>Result page "+s+"&nbsp;<b></font></td>");
			if(book!=0)
			{
			out.print("<td ><font color='#of2e4f' size='2' face='Verdana'>&nbsp;Book(<a href=./BrowseDocument?tag=type&doc=BOOK >"+book+"</a>)</td>");
			}
			if(bookbank!=0)
			{
			out.print("<td ><font color='#of2e4f' size='2' face='Verdana'>&nbsp;Book Bank(<a href=./BrowseDocument?tag=type&doc=BOOKBANK >"+bookbank+"</a>)</td>");
			}
			if(non!=0)
			{
			out.print("<td ><font color='#of2e4f' size='2' face='Verdana'>&nbsp;Non Book(<a href=./BrowseDocument?tag=type&doc=NON>"+non+"</a>)</td>");
			}
			
			if(std!=0)
			{
			out.print("<td ><font color='#of2e4f' size='2' face='Verdana'>&nbsp;Standard(<a href=./BrowseDocument?tag=type&doc=STANDARD>"+std+"</a>)</td>");
			}
			
			if(thesis!=0)
			{
			out.print("<td ><font color='#of2e4f' size='2' face='Verdana'>&nbsp;Thesis(<a href=./BrowseDocument?tag=type&doc=THESIS>"+thesis+"</a>)</td>");//style=\"TEXT-DECORATION:none\"
			}
			
			if(proceed!=0)
			{
			out.print("<td ><font color='#of2e4f' size='2' face='Verdana'>&nbsp;Proceeding(<a href=./BrowseDocument?tag=type&doc=PROCEEDING>"+proceed+"</a>)</td>");
			}
			
			if(journal!=0)
			{
			out.print("<td ><font color='#of2e4f' size='2' face='Verdana'>&nbsp;Journal(<a href=./BrowseDocument?tag=type&doc=JOURNAL >"+journal+"</a>)</td>");
			}
			
			if(report!=0)
			{
			out.print("<td ><font color='#of2e4f' size='2' face='Verdana'>&nbsp;Report(<a href=./BrowseDocument?tag=type&doc=REPORT>"+report+"</a>)</td>");
			}
			
			out.print("</table>");
			//out.print("<div align=left><input type=submit name=View value=Full&nbsp;Details></div>");
			
			
			if(request.getParameter("tag") == null)
			{
				SQL_Query_cur="";
			}
						
			SQL_Query_std =SQL_Query_Cnt+SQL_Query+SQL_Query_cur;//+SQL_Query_alpha
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
						top =Integer.parseInt(request.getParameter("bottom"))- 1;
						if ((top - txtno) >= 10)
							bottom = top - 9;
						else
							bottom = 1;
					}

				}
				if(Security.getParam(request, "ignore").equals("jnl_name"))
				 {     
					out.print("<div align=left><input type=submit name=View value=Full&nbsp;Details></div>");
					for(i = bottom; i <= top; i++) {
						
						rs.absolute(i);
						String jnl=rs.getString("jnl_code");
						out.print("<table>");
						out.print("<tr><td><div align=left><img border='0' src='/AutoLib/images/image.JPG'><font size=1 color=#0f2e4f face=Verdana>&nbsp;&nbsp;"+i+"</font></div><td></tr>");
						out.print("</table>");
						//out.print("<table align=center border=0 width=500>");
						out.print("<table align=center border=0 width=500>");						
						//out.print("<tr><td align=center><img border='0' src='/AutoLib/images/image.JPG'></td><td><font size=4 color=#0f2e4f face=Verdana>"+i+"</font></td></tr>");
						
						out.print("<tr>");
						out.print("<td width=100><font size='2' color='#of2e4f' face='Verdana'><b>  ");
						out.print("Journal Code");
						out.print("</b></td> ");
						out.print(
								"<td width=400><font size='2' color='#of2e4f' face='Verdana'>&nbsp;"
									+ rs.getString("jnl_code")
									+ "</td>");
						out.print("</tr>");
						out.print("<tr>");
						out.print("<td width=100><font size='2' color='#of2e4f' face='Verdana'><b>  ");
						out.print("Journal Name");
						out.print("</b></td> ");
						out.print(
								"<td width=400><font size='2' color='#of2e4f' face='Verdana'>&nbsp;"
									+ rs.getString("jnl_name")
									+ "</td>");
						out.print("</tr>");
						out.print("<tr>");
						out.print("<td width=100><font size='2' color='#of2e4f' face='Verdana'><b>  ");
						out.print("Language");
						out.print("</b></td> ");
						out.print(
								"<td width=400><font size='2' color='#of2e4f' face='Verdana'>&nbsp;"
									+ rs.getString("language")
									+ "</td>");
						out.print("</tr>");
						out.print("<tr>");
						out.print("<td><input type=Checkbox name=code value="+jnl+ "><font size='1' color='#of2e4f' face='Verdana'><b>Add to Cart</td>");
						out.print("</tr>");
						
					}
				}else if(Security.getParam(request, "ignore").equals("jnl_atl"))
				 {     
					out.print("<div align=left><input type=submit name=View value=Full&nbsp;Details></div>");
					for(i = bottom; i <= top; i++) {
						
						rs.absolute(i);
						String jnl=rs.getString("atl_no");
						out.print("<table>");
						out.print("<tr><td><div align=left><img border='0' src='/AutoLib/images/image.JPG'><font size=1 color=#0f2e4f face=Verdana>&nbsp;&nbsp;"+i+"</font></div><td></tr>");
						out.print("</table>");
						//out.print("<table align=center border=0 width=500>");
						out.print("<table align=center border=0 width=500>");						
						//out.print("<tr><td align=center><img border='0' src='/AutoLib/images/image.JPG'></td><td><font size=4 color=#0f2e4f face=Verdana>"+i+"</font></td></tr>");
						
						out.print("<tr>");
						out.print("<td width=100><font size='1' color='#of2e4f' face='Verdana'><b>  ");
						out.print("Article No.");
						out.print("</b></td> ");
						out.print(
								"<td width=400><font size='1' color='#of2e4f' face='Verdana'>&nbsp;"
									+ rs.getString("atl_no")
									+ "</td>");
						out.print("</tr>");
						out.print("<tr>");
						out.print("<td width=100><font size='1' color='#of2e4f' face='Verdana'><b>  ");
						out.print("Author");
						out.print("</b></td> ");
						out.print(
								"<td width=400><font size='1' color='#of2e4f' face='Verdana'>&nbsp;"
									+ rs.getString("atl_author")
									+ "</td>");
						out.print("</tr>");
						out.print("<tr>");
						out.print("<td width=100><font size='1' color='#of2e4f' face='Verdana'><b>  ");
						out.print("Article Title");
						out.print("</b></td> ");
						out.print(
								"<td width=400><font size='1' color='#of2e4f' face='Verdana'>&nbsp;"
									+ rs.getString("atl_title")
									+ "</td>");
						out.print("</tr>");
						out.print("<tr>");
						out.print("<td width=100><font size='1' color='#of2e4f' face='Verdana'><b>  ");
						out.print("Journal Name");
						out.print("</b></td> ");
						out.print(
								"<td width=400><font size='1' color='#of2e4f' face='Verdana'>&nbsp;"
									+ rs.getString("jnl_name")
									+ "</td>");
						out.print("</tr>");
						out.print("<tr>");
						out.print("<td width=100><font size='1' color='#of2e4f' face='Verdana'><b>  ");
						out.print("Year");
						out.print("</b></td> ");
						out.print(
								"<td width=400><font size='1' color='#of2e4f' face='Verdana'>&nbsp;"
									+ rs.getString("issue_year")
									+ "</td>");
						out.print("</tr>");
						out.print("<tr>");
						out.print("<td width=100><font size='1' color='#of2e4f' face='Verdana'><b>  ");
						out.print("Volume No.");
						out.print("</b></td> ");
						out.print(
								"<td width=400><font size='1' color='#of2e4f' face='Verdana'>&nbsp;"
									+ rs.getString("vol_no")
									+ "</td>");
						out.print("</tr>");
						out.print("<tr>");
						out.print("<td width=100><font size='1' color='#of2e4f' face='Verdana'><b>  ");
						out.print("Issue Month");
						out.print("</b></td> ");
						out.print(
								"<td width=400><font size='1' color='#of2e4f' face='Verdana'>&nbsp;"
									+ rs.getString("issue_month")
									+ "</td>");
						out.print("</tr>");
						out.print("<tr>");
						out.print("<td width=100><font size='1' color='#of2e4f' face='Verdana'><b>  ");
						out.print("Pages");
						out.print("</b></td> ");
						out.print(
								"<td width=400><font size='1' color='#of2e4f' face='Verdana'>&nbsp;"
									+ rs.getString("atl_page_nos")
									+ "</td>");
						out.print("</tr>");
						out.print("<tr>");
						out.print("<td><input type=Checkbox name=code value="+jnl+ "><font size='1' color='#of2e4f' face='Verdana'><b>Add to Cart</td>");
						out.print("</tr>");
						
					}
				}else
				{
					out.print("<div align=left><input type=submit name=View value=Full&nbsp;Details></div>");

				for(i = bottom; i <= top; i++) {

					rs.absolute(i);
					
					String acc=rs.getString("access_no");
					out.print("<table>");
					out.print("<tr><td><div align=left><img border='0' src='/AutoLib/images/image.JPG'><font size=1 color=#0f2e4f face=Verdana>&nbsp;&nbsp;"+i+"</font></div><td></tr>");
					out.print("</table>");
					out.print("<table align=center border=0 width=500>");
					

					out.print("<tr>");
					out.print("<td width=100><font size='1' color='#of2e4f' face='Verdana'><b>  ");
					out.print("Access No");
					out.print("</b></td> ");
					out.print(
							"<td width=400><font size='1' color='#of2e4f' face='Verdana'>&nbsp;"
								+ rs.getString("access_no")
								+ "</td>");
					out.print("</tr>");
					out.print("<tr>");
					out.print("<td width=100><font size='1' color='#of2e4f' face='Verdana'><b>  ");
					out.print("Author");
					out.print("</b></td> ");
					out.print(
							"<td width=400><font size='1' color='#of2e4f' face='Verdana'>&nbsp;"
								+ rs.getString("author_name")
								+ "</td>");
					out.print("</tr>");
					out.print("<tr>");
					out.print("<td width=100><font size='1' color='#of2e4f' face='Verdana'><b>  ");
					out.print("Title");
					out.print("</b></td> ");
					if( !rs.getString("sres").equals("") || !rs.getString("edition").equals(""))
					{
						if(rs.getString("sres").equals("") && ! rs.getString("edition").equals("") )
						{
					out.print(
						"<td width=400><font size='1' color='#of2e4f' face='Verdana'>&nbsp;"
							+ rs.getString("title")
							+"/"+rs.getString("edition")
							+ "</td>");
					out.print("</tr>");
						}
						if(rs.getString("edition").equals("") && !rs.getString("sres").equals(""))
						{
					out.print(
						"<td width=400><font size='1' color='#of2e4f' face='Verdana'>&nbsp;"
							+ rs.getString("title")
							+" / by "+rs.getString("sres")
							+ "</td>");
					out.print("</tr>");
						}
						if(! rs.getString("edition").equals("") && !rs.getString("sres").equals("") )
						{
							out.print(
									"<td width=400><font size='1' color='#of2e4f' face='Verdana'>&nbsp;"
										+ rs.getString("title")
										+" / by "+rs.getString("sres")+".-"+rs.getString("edition")
										+ "</td>");
								out.print("</tr>");
						}
					}
					else
					{
					out.print(
							"<td width=400><font size='1' color='#of2e4f' face='Verdana'>&nbsp;"
								+ rs.getString("title")
								+ "</td>");
						out.print("</tr>");
					}
					out.print("<tr>");
					
					
					
					out.print("<td width=100><font size='1' color='#of2e4f' face='Verdana'><b>  ");
					out.print("Imprint");
					out.print("</b></td> ");
					if(!rs.getString("year_pub").equals("") || !rs.getString("place").equals(""))
					{
						if(rs.getString("year_pub").equals("") && ! rs.getString("place").equals(""))
						{						
					out.print(
						"<td width=400><font size='1' color='#of2e4f' face='Verdana'>&nbsp;"+ rs.getString("place")+": "+rs.getString("sp_name")
							+ "");
					out.print("</tr>");
					out.print("<tr>");
						}
						if(rs.getString("place").equals("") && ! rs.getString("year_pub").equals(""))
						{
						
					out.print(
						"<td width=400><font size='1' color='#of2e4f' face='Verdana'>&nbsp;"+rs.getString("sp_name")
							+ ","+rs.getString("year_pub")+"</td>");
					out.print("</tr>");
					out.print("<tr>");
						}
						if(!rs.getString("year_pub").equals("") && !rs.getString("place").equals(""))
						{
							out.print(
									"<td width=400><font size='1' color='#of2e4f' face='Verdana'>&nbsp;"+rs.getString("place")
										+ ":"+rs.getString("sp_name")+","+rs.getString("year_pub")+"</td>");
								out.print("</tr>");
								out.print("<tr>");
							
						}
						
					}
					else
					{	
						out.print(
								"<td width=400><font size='1' color='#of2e4f' face='Verdana'>&nbsp;"+ rs.getString("place")+": "+rs.getString("sp_name")
									+ ","+rs.getString("year_pub")+"</td>");
							out.print("</tr>");
							out.print("<tr>");
					}
					
					out.print("<tr>");
					out.print("<td width=100><font size='1' color='#of2e4f' face='Verdana'><b>  ");
					out.print("Call No");
					out.print("</b></td> ");
					out.print(
							"<td width=400><font size='1' color='#of2e4f' face='Verdana'>&nbsp;"
								+ rs.getString("call_no")
								+ "</td>");
					out.print("</tr>");
					
					
					out.print("<tr>");
					out.print("<td width=100><font size='1' color='#of2e4f' face='Verdana'><b>  ");
					out.print("Location");
					out.print("</b></td> ");
					
					out.print(
							"<td width=400><font size='1' color='#of2e4f' face='Verdana'>&nbsp;"
								+ rs.getString("location")
								+ "</td>");
					out.print("<tr>");
					out.print("<td width=100><font size='1' color='#of2e4f' face='Verdana'><b>  ");
					out.print("Status");
					out.print("</b></td> ");
					if(rs.getString("availability").equals("YES"))
					{
					out.print(
							"<td width=400><font size='1' color='#of2e4f' face='Verdana'>&nbsp;"
								+ "Available"
								+ "</td>");
					}else
					{
						out.print(
								"<td width=400><font size='1' color='#of2e4f' face='Verdana'>&nbsp;"
									+ rs.getString("availability")
									+ "</td>");
					}
					
					
					out.print("</tr>");
										
					out.print("<tr>");
					out.print("<td><input type=Checkbox name=code value="+acc+ "><font size='1' color='#of2e4f' face='Verdana'><b>Add to Cart</td>");
					out.print("</tr>");
									
				}
			}
				rs.close();
				
			out.print("</table>");
			out.print("<table width=0% align=left>");

			out.print("<td><input type=submit name=View value=Full&nbsp;Details>");

			out.print("</table>");
			
			out.print("<center>");
			
			if(top==10){
				if(Security.getParam(request, "ignore").equals("jnl_name"))
				{
					out.print("<table width=0% align=center>");
					out.print("<br><td align=center><a href=./BrowseDocument?flag=next&ignore=jnl_name&top="+top+"><img border='0' src='/AutoLib/images/Next.gif'></td>");
					out.print("</table>");
				}else if(Security.getParam(request, "ignore").equals("jnl_atl"))
				{
					out.print("<table width=0% align=center>");
					out.print("<br><td align=center><a href=./BrowseDocument?flag=next&ignore=jnl_atl&top="+top+"><img border='0' src='/AutoLib/images/Next.gif'></td>");
					out.print("</table>");
				}
				else
				{
				out.print("<table width=0% align=center>");
				out.print("<br><td align=center><a href=./BrowseDocument?flag=next&top="+top+"><img border='0' src='/AutoLib/images/Next.gif'></td>");
				out.print("</table>");
				}
			}else
			{
				out.print("<table width=0% align=center>");
				if (bottom > 1) {
					if(Security.getParam(request, "ignore").equals("jnl_name"))
					{
						out.print("<br><td align=left><a href=./BrowseDocument?flag=previous&ignore=jnl_name&bottom="+bottom+"><img border='0' src='/AutoLib/images/Previous.gif'></td>");
					}else if(Security.getParam(request, "ignore").equals("jnl_atl"))
					{
						out.print("<br><td align=left><a href=./BrowseDocument?flag=previous&ignore=jnl_atl&bottom="+bottom+"><img border='0' src='/AutoLib/images/Previous.gif'></td>");
					}else{
						out.print("<br><td align=left><a href=./BrowseDocument?flag=previous&bottom="+bottom+"><img border='0' src='/AutoLib/images/Previous.gif'></td>");
					}
					
					
				
				}
				if (top < rcount) {
					if(Security.getParam(request, "ignore").equals("jnl_name"))
					{
						out.print("<br><td align=right><a href=./BrowseDocument?flag=next&ignore=jnl_name&top="+top+"><img border='0' src='/AutoLib/images/Next.gif'></td>");
					}else if(Security.getParam(request, "ignore").equals("jnl_atl"))
					{
						out.print("<br><td align=right><a href=./BrowseDocument?flag=next&ignore=jnl_atl&top="+top+"><img border='0' src='/AutoLib/images/Next.gif'></td>");
					}else{
					out.print("<br><td align=right><a href=./BrowseDocument?flag=next&top="+top+"><img border='0' src='/AutoLib/images/Next.gif'></td>");
					}
				}
				out.print("</table>");
			}
			
			
			out.print("</center>");
		
			out.print("</form>");
			out.print("</body>");
			out.print("</html>");
			} else {

				out.print("<center><b><font size=4 color=#0f2e4f face=Verdana>Record Not Found !</font></b></center>");
				
			}
			
		} catch (Exception sss) {
			sss.printStackTrace();

		}

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

