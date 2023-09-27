package Lib.Auto.Stock;
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

public class StockIndexServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	java.sql.Connection con=null;
	ResourceBundle rb = ResourceBundle.getBundle("LocalStrings");
	java.sql.PreparedStatement pstmt=null;
	java.sql.ResultSet rs=null;
	String indexPage = null,protocol;
	int count_mas=0,count_yes=0,count_lost=0,count_issued=0,count_binding=0,count_damaged=0,count_transfer=0;
	StockBean SB=new StockBean();
	
	protected static final String COUNT_STOCK_MAS ="select count(*)  from stock_report_book";
	protected static final String COUNT_STOCK_YES ="select count(*) from stock_view_yes";
	protected static final String COUNT_STOCK_LOST ="select count(*) from stock_view_lost";
	protected static final String COUNT_STOCK_ISSUED ="select count(*) from stock_view_issued";
	protected static final String COUNT_STOCK_BINDING ="select count(*) from stock_view_binding";
	protected static final String COUNT_STOCK_DAMAGED ="select count(*) from stock_view_damaged";
	protected static final String COUNT_STOCK_TRANSFER ="select count(*) from stock_view_transfer";
	
	
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
                              
		    pstmt = con.prepareStatement(COUNT_STOCK_MAS);
			rs = pstmt.executeQuery();
			if(rs.next()){
				count_mas=Integer.parseInt(rs.getString(1));
				SB.setCmas(count_mas);	
				}
			
			pstmt = con.prepareStatement(COUNT_STOCK_YES);
			rs = pstmt.executeQuery();
			if(rs.next()){
				count_yes=Integer.parseInt(rs.getString(1));
				SB.setCyes(count_yes);
				}
			
			pstmt = con.prepareStatement(COUNT_STOCK_LOST);
			rs = pstmt.executeQuery();
			if(rs.next()){
				count_lost=Integer.parseInt(rs.getString(1));
				SB.setClost(count_lost);
			}
			
			pstmt = con.prepareStatement(COUNT_STOCK_ISSUED);
			rs = pstmt.executeQuery();
			if(rs.next()){
				count_issued=Integer.parseInt(rs.getString(1));
				SB.setCissued(count_issued);
				}
			
			pstmt = con.prepareStatement(COUNT_STOCK_BINDING);
			rs = pstmt.executeQuery();
			if(rs.next()){
				count_binding=Integer.parseInt(rs.getString(1));
				SB.setCbinding(count_binding);
				}
			
			pstmt = con.prepareStatement(COUNT_STOCK_DAMAGED);
			rs = pstmt.executeQuery();
			if(rs.next()){
				count_damaged=Integer.parseInt(rs.getString(1));
				SB.setCdamaged(count_damaged);
				}
			
			pstmt = con.prepareStatement(COUNT_STOCK_TRANSFER);
			rs = pstmt.executeQuery();
			if(rs.next()){
				count_transfer=Integer.parseInt(rs.getString(1));
				SB.setCtransfer(count_transfer);
				}
			request.setAttribute("BeanObject",SB);
			indexPage = "/Stock/index.jsp";
		    dispatch(request, response, indexPage);
		               
            
		}catch(Exception e){}
		finally{
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