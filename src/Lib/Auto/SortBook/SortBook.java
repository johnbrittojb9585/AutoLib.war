package Lib.Auto.SortBook;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

import org.apache.log4j.Logger;

import com.library.autolib.portal.dbconnectionutil.SessionHibernateUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import Common.DataQuery;
import Common.Security;
import Common.businessutil.BusinessServiceFactory;

import Common.businessutil.reports.ReportQueryUtill;
import Common.businessutil.reports.ReportService;
import java.sql.*;

public class SortBook extends HttpServlet implements ReportQueryUtill {
	/**
	 * 
	 */
	private static Logger log4jLogger = Logger.getLogger(SortBook.class);	
		
	java.sql.Connection con = null;
	java.sql.PreparedStatement Prest = null;
	java.sql.PreparedStatement Prest1 = null;
	java.sql.ResultSet rs = null;
	java.sql.ResultSet rs1 = null;
	java.sql.Statement st = null;
	
	String namedQuery=null;
		
	
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException {

		performTask(request, response);

	}
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

		performTask(request, response);

	}	
	
	public void performTask(
		HttpServletRequest request,
		HttpServletResponse response)  throws ServletException, IOException {
						
		try {		
			
						
						
			//ReportService ss = BusinessServiceFactory.INSTANCE.getReportService();			
			//con=ss.getDBConnect();
			
			HttpSession session = request.getSession(true);
			String res = Security.checkSecurity(1, session, response, request);		
			if(res.equalsIgnoreCase("Failure"))
			{
				response.sendRedirect("/AutoLib/Tree/sessiontimeout.jsp");  
				return;
			}
			
			con=SessionHibernateUtil.getSession().connection();
			
			log4jLogger.info("start===========SortBookSave=====");
			
			namedQuery="SELECT access_no FROM book_mas ORDER BY CONVERT(access_no,SIGNED INTEGER) ASC";
			st = con.createStatement();
			rs = st.executeQuery(namedQuery);
			
			while (rs.next()) {
				
			String acc_no=null;
			acc_no=rs.getString("access_no");
			
//			String book_Access_No=new String();
			String acc=acc_no.toUpperCase();
			int N1=0;//ascii value+length.
			int N2=0;//length.
			int N3=0;//Number only.
		    //book_Access_No=access_no;
			char temp1[]=new char[10];
			char temp2[]=new char[10];
			//char s[]=access_no.toCharArray();
			char s[]=acc.toCharArray();
			for(int i=0;i<s.length;i++)
				{
					if ((s[i]>='A'||s[i]>='a') && (s[i]<='Z'||s[i]<='z'))
						{
							temp1[i]+=s[i];
						}else
						{
							temp2[i]+=s[i];
						}
				}
			//String t1=String.valueOf(temp1).trim();
			String t2=String.valueOf(temp2).trim();
			int as=0;
			int i=0;
			if(i>='A'||i<='Z')
			{
			for(i='A';i<='Z';i++)
			{
				for(int j=0;j<temp1.length;j++)
					{
					if(temp1[j]==i)
						{
							as+=i;
							
						}
					}
			}
			}
			if(i>='a'||i<='z')
			{
			for(i='a';i<='z';i++)
			{
				for(int j=0;j<temp1.length;j++)
					{
					if(temp1[j]==i)
						{
							as+=i;
							
						}
					}
			}
			}
			
			N1=as+acc_no.length();
			N2=acc_no.length();
			N3=Integer.parseInt(t2);
			//log4jLogger.info("start=========== 002 =====");
			try {
				Prest = con.prepareStatement(DataQuery.INSERT_DELETE);
				Prest.setString(1, acc_no);
				Prest.executeUpdate();
				Prest = con.prepareStatement(DataQuery.INSERT_SORT);
				Prest.setString(1, acc_no);
				Prest.setInt(2, N1);
				Prest.setInt(3, N2);
				Prest.setInt(4, N3);
				Prest.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			}
       
			}
			
		}  catch (Exception  e) {		

			e.printStackTrace();
		}
		finally
		{
			try {
				if (rs != null) {
					rs.close();
				}
				if (Prest != null) {
					Prest.close();
				}	
				
				
				if (con != null) {
					con.close();
				}

		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
		
		 String indexPage="/SortBook/index.jsp?check=SaveSuccess";
		 dispatch(request,response,indexPage);
	}
         

      
		
	
	public void dispatch(
			HttpServletRequest request,
			HttpServletResponse response,
			String indexPage)
			throws ServletException, IOException {
		   // response.sendRedirect(indexPage);
			RequestDispatcher dispatch = request.getRequestDispatcher(indexPage);
			dispatch.forward(request, response);
		}
}
