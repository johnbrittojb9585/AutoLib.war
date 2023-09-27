package Lib.Auto.bulkTransfer_Books;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.sql.DriverManager;
import java.util.ResourceBundle;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.UnavailableException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Common.Security;


import Common.DBConnection;

public class BindingBooksServlet extends HttpServlet implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String SELECT_BINDER_MAS="select binder_code,binder_name from binder_mas";
	String flag="",protocol="";
	java.sql.Connection con = null;
	java.sql.PreparedStatement Prest = null;
	java.sql.ResultSet rs = null;

	String indexPage = null;
	ResourceBundle rb =null;
	

	public void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException {

		performTask(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException {

		performTask(request, response);

	}
	public void performTask(
		HttpServletRequest request,
		HttpServletResponse response) throws ServletException {

		
		try {
			
			PrintWriter out=response.getWriter();
			out.print("haiiiiiiiii");
			con=DBConnection.getInstance();
			Prest=con.prepareStatement(SELECT_BINDER_MAS);
			rs=Prest.executeQuery();
			request.setAttribute("Binder_Set",rs);
					
			indexPage = "/BindingBooks/index.jsp";
			dispatch(request, response, indexPage);
			
			 } catch (Exception sss) {
					throw new ServletException(sss);
					
				} finally {
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
	

	/**
	 * Instance variable for SQL statement property
	 */
	
	
	public void dispatch(
			HttpServletRequest request,
			HttpServletResponse response,
			String indexPage)
			throws ServletException, IOException {
			//	response.sendRedirect(indexPage);
			RequestDispatcher dispatch = request.getRequestDispatcher(indexPage);
			dispatch.forward(request, response);
		}

	
	
}
