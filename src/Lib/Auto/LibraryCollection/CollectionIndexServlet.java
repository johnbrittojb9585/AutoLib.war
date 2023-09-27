package Lib.Auto.LibraryCollection;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.UnavailableException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Common.Security;



import Common.DBConnection;

public class CollectionIndexServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	java.sql.Connection con=null;
	ResourceBundle rb = ResourceBundle.getBundle("LocalStrings");
	java.sql.PreparedStatement pstmt=null;
	java.sql.ResultSet rs=null;
	String indexPage = null,protocol;
	
	int count_book=0,count_bbank=0,count_nbook=0,count_stan=0,count_bvol=0,count_report=0,count_thesis=0,count_proceed=0;
	int count_tbook=0,count_tbbank=0,count_tnbook=0,count_tstan=0,count_tbvol=0,count_treport=0,count_tthesis=0,count_tproceed=0;
	protected static final String COUNT_COL="select count(*)  from full_search where document=?";
	protected static final String COUNT_COL_TITLE="select count(distinct(title)) from full_search where document=?";
	
	
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
            con=DBConnection.getInstance();
                           
		    pstmt = con.prepareStatement(COUNT_COL);
		    pstmt.setString(1,"BOOK");
			rs = pstmt.executeQuery();
			if(rs.next()){
				count_book=Integer.parseInt(rs.getString(1));
				
				}
			
			pstmt = con.prepareStatement(COUNT_COL);
		    pstmt.setString(1,"BOOK BANK");
			rs = pstmt.executeQuery();
			if(rs.next()){
				count_bbank=Integer.parseInt(rs.getString(1));
				
				}
			
			pstmt = con.prepareStatement(COUNT_COL);
		    pstmt.setString(1,"NON BOOK");
			rs = pstmt.executeQuery();
			if(rs.next()){
				count_nbook=Integer.parseInt(rs.getString(1));
				
				}
			
			pstmt = con.prepareStatement(COUNT_COL);
		    pstmt.setString(1,"STANDARD");
			rs = pstmt.executeQuery();
			if(rs.next()){
				count_stan=Integer.parseInt(rs.getString(1));
				
				}
			
			pstmt = con.prepareStatement(COUNT_COL);
		    pstmt.setString(1,"BACK VOLUME");
			rs = pstmt.executeQuery();
			if(rs.next()){
				count_bvol=Integer.parseInt(rs.getString(1));
				
				}
			
			pstmt = con.prepareStatement(COUNT_COL);
		    pstmt.setString(1,"REPORT");
			rs = pstmt.executeQuery();
			if(rs.next()){
				count_report=Integer.parseInt(rs.getString(1));
				
				}
			
			pstmt = con.prepareStatement(COUNT_COL);
		    pstmt.setString(1,"THESIS");
			rs = pstmt.executeQuery();
			if(rs.next()){
				count_thesis=Integer.parseInt(rs.getString(1));
				
				}
			pstmt = con.prepareStatement(COUNT_COL);
			pstmt.setString(1,"PROCEEDING");
			rs = pstmt.executeQuery();
			if(rs.next()){
				count_proceed=Integer.parseInt(rs.getString(1));
			
				}
			
			pstmt = con.prepareStatement(COUNT_COL_TITLE);
		    pstmt.setString(1,"BOOK");
			rs = pstmt.executeQuery();
			if(rs.next()){
				count_tbook=Integer.parseInt(rs.getString(1));
			
				}
			
			pstmt = con.prepareStatement(COUNT_COL_TITLE);
		    pstmt.setString(1,"BOOK BANK");
			rs = pstmt.executeQuery();
			if(rs.next()){
				count_tbbank=Integer.parseInt(rs.getString(1));
			
				}
			
			pstmt = con.prepareStatement(COUNT_COL_TITLE);
		    pstmt.setString(1,"NON BOOK");
			rs = pstmt.executeQuery();
			if(rs.next()){
				count_tnbook=Integer.parseInt(rs.getString(1));
				
				}
			
			pstmt = con.prepareStatement(COUNT_COL_TITLE);
		    pstmt.setString(1,"STANDARD");
			rs = pstmt.executeQuery();
			if(rs.next()){
				count_tstan=Integer.parseInt(rs.getString(1));
			
				}
			
			pstmt = con.prepareStatement(COUNT_COL_TITLE);
		    pstmt.setString(1,"BACK VOLUME");
			rs = pstmt.executeQuery();
			if(rs.next()){
				count_tbvol=Integer.parseInt(rs.getString(1));
			
				}
			
			pstmt = con.prepareStatement(COUNT_COL_TITLE);
		    pstmt.setString(1,"REPORT");
			rs = pstmt.executeQuery();
			if(rs.next()){
				count_treport=Integer.parseInt(rs.getString(1));
			
				}
			
			pstmt = con.prepareStatement(COUNT_COL_TITLE);
		    pstmt.setString(1,"THESIS");
			rs = pstmt.executeQuery();
			if(rs.next()){
				count_tthesis=Integer.parseInt(rs.getString(1));
			
				}
			pstmt = con.prepareStatement(COUNT_COL_TITLE);
			pstmt.setString(1,"PROCEEDING");
			rs = pstmt.executeQuery();
			if(rs.next()){
				count_tproceed=Integer.parseInt(rs.getString(1));
			
				}
			
	        out.print("<br><center><b><font face=verdana size=4>Library&nbsp;Collection</font></b></center");
	        out.print("<br><table border=1 width=70% align=center CELLSPACING='0' BORDERCOLOR='#DADADA' BGCOLOR='#F5F5F5'> ");
	   		out.print("<th><font face=verdana size=2>Resources</font></th><th><font face=verdana size=2>Total&nbsp;No&nbsp;Of&nbsp;Volume</font></th><th><font face=verdana size=2>Total&nbsp;No&nbsp;Of&nbsp;Title</font></th>");			
	   		out.print("<tr>");
   			out.print("<td width=10%><font face=verdana size=2>BOOK</font></td>"+"<td width=10% align=center><font face=verdana size=2>"+count_book+"</font></td>"+"<td width=20% align=center><font face=verdana size=2>"+count_tbook+"</font></td>");
   			out.print("</tr>");
   			out.print("<tr>");
   			out.print("<td width=10%><font face=verdana size=2>BOOK BANK</font></td>"+"<td width=10% align=center><font face=verdana size=2>"+count_bbank+"</font></td>"+"<td width=20% align=center><font face=verdana size=2>"+count_tbbank+"</font></td>");
   			out.print("</tr>");
   			out.print("<tr>");
   			out.print("<td width=10%><font face=verdana size=2>NON BOOK</font></td>"+"<td width=10% align=center><font face=verdana size=2>"+count_nbook+"</font></td>"+"<td width=20% align=center><font face=verdana size=2>"+count_tnbook+"</font></td>");
   			out.print("</tr>");
   			out.print("<tr>");
   			out.print("<td width=10%><font face=verdana size=2>STANDARD</font></td>"+"<td width=10% align=center><font face=verdana size=2>"+count_stan+"</font></td>"+"<td width=20% align=center><font face=verdana size=2>"+count_tstan+"</font></td>");
   			out.print("</tr>");
   			out.print("<tr>");
   			out.print("<td width=10%><font face=verdana size=2>BACK VOLUME</font></td>"+"<td width=10% align=center><font face=verdana size=2>"+count_bvol+"</font></td>"+"<td width=20% align=center><font face=verdana size=2>"+count_tbvol+"</font></td>");
   			out.print("</tr>");
   			out.print("<tr>");
   			out.print("<td width=10%><font face=verdana size=2>REPORT</font></td>"+"<td width=10% align=center><font face=verdana size=2>"+count_report+"</font></td>"+"<td width=20% align=center><font face=verdana size=2>"+count_treport+"</font></td>");
   			out.print("</tr>");
   			out.print("<tr>");
   			out.print("<td width=10%><font face=verdana size=2>THESIS</font></td>"+"<td width=10% align=center><font face=verdana size=2>"+count_thesis+"</font></td>"+"<td width=20% align=center><font face=verdana size=2>"+count_tthesis+"</font></td>");
   			out.print("</tr>");
   			out.print("<tr>");
   			out.print("<td width=10%><font face=verdana size=2>PROCEEDING</font></td>"+"<td width=10% align=center><font face=verdana size=2>"+count_proceed+"</font></td>"+"<td width=20% align=center><font face=verdana size=2>"+count_tproceed+"</font></td>");
   			out.print("</tr>");
   			out.print("<tr>");
   			out.print("<td width=10%><font face=verdana size=2>Total</font></td>"+"<td width=10% align=right><font face=verdana size=2>"+(count_book+count_bbank+count_nbook+count_stan+count_bvol+count_report+count_thesis+count_proceed)+"</font></td>"+"<td width=20% align=right><font face=verdana size=2>"+(count_tbook+count_tbbank+count_tnbook+count_tstan+count_tbvol+count_treport+count_tthesis+count_tproceed)+"</font></td>");
   			out.print("</tr>");
   			
   			out.print("</table>");
            
		}catch(Exception e){}
		finally
		{
			try{
				if(rs!=null){
					rs.close();
					rs=null;
				}
				if(pstmt!=null){
					pstmt.close();
					pstmt=null;
				}

			}catch(Exception e){
			e.printStackTrace();
			}
		}
}
public void dispatch(

		HttpServletRequest request,
		HttpServletResponse response,
		String indexPage)
		throws ServletException, IOException {
		RequestDispatcher dispatch = request.getRequestDispatcher(indexPage);
		dispatch.forward(request, response);
	}
}