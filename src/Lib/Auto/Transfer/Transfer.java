package Lib.Auto.Transfer;
import Lib.Auto.Transfer.transferbean;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Common.Security;

import javax.servlet.ServletException;
import javax.servlet.UnavailableException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Transfer extends HttpServlet {
	java.sql.Connection con = null;
	java.sql.PreparedStatement Prest = null;
	java.sql.ResultSet rs = null;
	transferbean ob=new transferbean();
	ResourceBundle rb = ResourceBundle.getBundle("LocalStrings");

	ArrayList ser=new ArrayList();
	ArrayList sc=new ArrayList();
	String flag="",Accto="",Accfrom="",sql="",protocol="",valid="";
	int sno=0;
	protected static final String SELECT_UPDATE =
		"update book_mas set invoice_no=?,Invoice_Date=? where access_no=?";
	protected static final String SELECT_ACCESS =
		"select * from sort_book where n3 between ? and ? order by n1,n2,n3";
	protected static final String SELECT_STRING =
	"select * from sort_book where access_no  between ? and ? ";

	protected static final String SELECT_INVOICE =
		"select inv_no,inv_date from or_inv_pay where inv_no=? and inv_date=?";
		
	public void init() throws ServletException
	{
	String driver=getServletContext().getInitParameter("driver");
	protocol=getServletContext().getInitParameter("protocol");


		if(driver==null || protocol==null){
			throw new UnavailableException("Not Found");
		}


		try {
			rb = ResourceBundle.getBundle("LocalStrings");
		} catch (Exception e) {throw new ServletException(e);}

		try {
			
			Class.forName(driver);
			con =
				DriverManager.getConnection(
					protocol,
					rb.getString("Username"),
					rb.getString("Password"));
				} catch (Exception e) {
			throw new ServletException(e);

		}

	}


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
        	flag=request.getParameter("flag");

			if(flag.equals("transfer_f"))
			{
			Accto=request.getParameter("AccTo");
			Accfrom=request.getParameter("AccFrom");
			char a1[]=Accfrom.toCharArray();
			char b1[]=Accto.toCharArray();

			 for(int i=0;i<a1.length;i++)
			{
				 if(Character.isDigit(a1[i])){
					valid="No";
				}
				else{
					valid="notNo";
					i=i+a1.length;
				}
					
			}
			for(int i=0;i<b1.length;i++)
			{
				if(Character.isDigit(b1[i])){
					valid="No";
				}
				else{
					valid="notNo";
					i=i+b1.length;
				}
					
			}  
			String invtext=request.getParameter("InvNo");
			out.print(invtext);
            java.util.StringTokenizer stz1=new java.util.StringTokenizer(invtext,";");
			String in_no=stz1.nextToken();
			String in_date=Security.TextDate_member(stz1.nextToken());

						Prest = con.prepareStatement(SELECT_INVOICE);
						Prest.setString(1,in_no);
						Prest.setString(2,in_date);
						rs = Prest.executeQuery();

if(rs.next())
     {
	if(valid.equalsIgnoreCase("No"))
	{
	Prest = con.prepareStatement(SELECT_ACCESS);
	}
	else
	{
	Prest = con.prepareStatement(SELECT_STRING);
	}
	Prest.setString(1,Accfrom);
	Prest.setString(2,Accto);
	rs = Prest.executeQuery();
	
	while(rs.next())
    {
	String access =rs.getString("access_no");
	Prest = con.prepareStatement(SELECT_UPDATE);
	Prest.setString(1,in_no);
	Prest.setString(2,in_date);
	Prest.setString(3,access);
	Prest.executeUpdate();
   }
	out.print("i am here");
	getServletConfig().getServletContext().getRequestDispatcher("/OrdInvProcessing/transfer.jsp?flag=SuccessTransfer").forward(request, response);
     }
else
{
getServletConfig().getServletContext().getRequestDispatcher("/OrdInvProcessing/transfer.jsp?flag=FailureTransfer").forward(request, response);

}

}

		}  catch (Exception e) {

					}

	}



}
