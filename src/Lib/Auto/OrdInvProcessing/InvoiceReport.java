package Lib.Auto.OrdInvProcessing;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.sql.SQLException;


import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletException;
import javax.servlet.*;


import Common.DBConnection;
import Common.Security;

public class InvoiceReport extends HttpServlet {
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected static final String SELECT_NEW =
		"select * from view_invoice_statistics";
	protected static final String SELECT_SPNAME =
		"select sp_code from sup_pub_mas where sp_name= ?  and sp_type='SUPPLIER' ";
	protected static final String SELECT_BUDGET =
		"select bud_code from budget_mas where bud_head= ?";
		protected static final String SELECT_DEPT =
		"select dept_code from department_mas where dept_name= ?";
		
String protocol;
String Sqlstr="",Sqlstr_Inner="",sup_name="",bud_head="",dept_name="",ord_no="",ord_date="",inv_no="",inv_date="",amount="",year="",doc_type="",paid="",pay_date="",mode="",sno="",spname="",budhead="",deptname="";
int sup_code=0,bud_code=0,dept_code=0;
double total=0.0;
int no_books=0;
			java.sql.ResultSet rs=null;
			java.sql.ResultSet rs1=null;
			java.sql.Connection con=null;
			java.sql.Statement st=null;
			ResourceBundle rb = ResourceBundle.getBundle("LocalStrings");

		
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException {

		performTask(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException{
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
	        ArrayList ser=new ArrayList();
			ArrayList sc=new ArrayList();
			
			java.sql.PreparedStatement pstmt=null;
			
			String flag=request.getParameter("flag");
			
			
	if(flag!=null)
	{
	if(flag.equals("Inv_Report"))
   	{
		 Sqlstr = "select inv_pay.sno,inv_pay.ord_no,inv_pay.ord_date,inv_pay.sup_code,inv_pay.bud_code,inv_pay.inv_no,inv_pay.inv_date,inv_pay.amount,inv_pay.dept_code,inv_pay.year,inv_pay.doc_type,inv_pay.paid,inv_pay.pay_date,inv_pay.mode,sup_pub.sp_name,budget.bud_head,dept.dept_name from or_inv_pay inv_pay,sup_pub_mas sup_pub,budget_mas budget,department_mas dept where   2>1  and  inv_pay.sup_code=sup_pub.sp_code and inv_pay.bud_code=budget.bud_code and inv_pay.dept_code=dept.dept_code ";
      
		if(!request.getParameter("supplier").equals(""))
		{
		
		pstmt=con.prepareStatement(SELECT_SPNAME);
		pstmt.setString(1,request.getParameter("supplier"));
		rs=pstmt.executeQuery();
		if(rs.next())
		{
		String scode=rs.getString("sp_code");
		
		Sqlstr=Sqlstr+ " and inv_pay.sup_code = '"+scode+"'" ;
		}
		}
		if(!request.getParameter("budget").equals(""))
		{
		
		pstmt=con.prepareStatement(SELECT_BUDGET);
		pstmt.setString(1,request.getParameter("budget"));
		rs=pstmt.executeQuery();
		if(rs.next())
		{
		String bcode=rs.getString("bud_code");
		
		Sqlstr=Sqlstr+ " and inv_pay.bud_code = '"+bcode+"'";
		}
		}
		if(!request.getParameter("dept").equals(""))
		{
		pstmt=con.prepareStatement(SELECT_DEPT);
		pstmt.setString(1,request.getParameter("dept"));
		rs=pstmt.executeQuery();
		if(rs.next())
		{
		String dcode=rs.getString("dept_code");
		Sqlstr=Sqlstr+ " and inv_pay.dept_code = '"+dcode+"' ";
		}
		}
		if(request.getParameter("OrderFlag").equals("OrderDate"))
		{
		 String Ord_From=getdate1(request.getParameter("Ord_From"));
		 String Ord_To=getdate1(request.getParameter("Ord_To"));
         Sqlstr=Sqlstr+ " and ord_date between '"+Ord_From+"' and '"+Ord_To+"' ";
		}
		if(request.getParameter("InvoiceFlag").equals("InvoiceDate"))
		{
		 String Inv_From=getdate1(request.getParameter("Inv_From"));
		 String Inv_To=getdate1(request.getParameter("Inv_To"));
         Sqlstr=Sqlstr+ " and inv_date between '"+Inv_From+"' and '"+Inv_To+"' ";
		}
		if(request.getParameter("PaidFlag").equals("PaidDate"))
		{
		 String Paid_From=getdate1(request.getParameter("Paid_From"));
		 String Paid_To=getdate1(request.getParameter("Paid_To"));
         Sqlstr=Sqlstr+ " and pay_date between '"+Paid_From+"' and '"+Paid_To+"' ";
	 		}
		if(!request.getParameter("Mode").equals("ALL"))
		{
		 String mode=request.getParameter("Mode");
		 Sqlstr=Sqlstr+ " and mode='"+mode+"' ";
		}
		if(!request.getParameter("Paid").equals("BOTH"))
		{
		 String paid=request.getParameter("Paid");
		 Sqlstr=Sqlstr+ " and paid='"+paid+"' ";
		}
		if(!request.getParameter("doc").equals("ALL"))
		{
		 String doc=request.getParameter("doc");
		 Sqlstr=Sqlstr+ " and doc_type='"+doc+"' ";
		}
		Sqlstr=Sqlstr+ "order by inv_pay.sno";

		pstmt=con.prepareStatement(Sqlstr);
		rs=pstmt.executeQuery();
		if(rs.next()){
			out.print("<center><font size=4><b>Report</b></font></center>");
		    out.print("<br><table border=1 width=700 align=center CELLSPACING='0' BORDERCOLOR='#DADADA' BGCOLOR='#F5F5F5'> ");
		    out.print("<th>Sno</th><th>Order No</th><th>Order Date</th><th>Sup Name</th><th>Budget Head</th><th>Invoice No</th><th>Invoice Date</th><th>Amount</th><th>Dept Name</th><th>Year</th><th>Document</th><th>Paid</th><th>Paid Date</th><th>Mode</th>");	
			}
		else{
			out.print("<br><br><br><br><br><br><br><br><br>");
			out.print("<center><font size=4>Record Not found</font></center><br><br>");
			out.print("<center><a href='/AutoLib/OrdInvProcessing/report.jsp'><img border=0 src='/AutoLib/images/Back.gif'></a></center>");
		    }
		rs.beforeFirst();
		while(rs.next())
		{
			 out.print("<tr><td><font size=2>"+rs.getString("sno")+"</td>");
			 out.print("<td>"+rs.getString("ord_no")+"</td>");
			 out.print("<td>"+getdate(rs.getString("ord_date"))+"</td>");
			 out.print("<td>"+rs.getString("sp_name")+"</td>");
			 out.print("<td>"+rs.getString("bud_head")+"</td>");
			 out.print("<td>"+rs.getString("inv_no")+"</td>");
			 out.print("<td>"+getdate(rs.getString("inv_date"))+"</td>");
			 out.print("<td>"+rs.getString("amount")+"</td>");
			 out.print("<td>"+rs.getString("dept_name")+"</td>");
			 out.print("<td>"+rs.getString("year")+"</td>");
			 out.print("<td>"+rs.getString("doc_type")+"</td>");
			 out.print("<td>"+rs.getString("paid")+"</td>");
			 out.print("<td>"+getdate(rs.getString("pay_date"))+"</td>");
			 out.print("<td>"+rs.getString("mode")+"</td>");
			 out.print("</tr>");
		


	}
	out.print("</table>");
	 }
	  if(flag.equals("Inv_Stat"))
	 {	

		pstmt=con.prepareStatement(SELECT_NEW);

		rs=pstmt.executeQuery();
		if(rs.next()){
			out.print("<center><font size=4><b>Invoice Statistics</b></font></center>");
			out.print("<br><table border=1 width=700 align=center CELLSPACING='0' BORDERCOLOR='#DADADA' BGCOLOR='#F5F5F5'>");
		    out.print("<th>Sno</th><th>inv_no</th><th>inv_date</th><th>sp_name</th><th>amount</th><th>year</th><th>totalofbooks</th><th>bud_head</th>");	
			}
		else{
			out.print("<br><br><br><br><br><br><br><br><br>");
			out.print("<center><font size=4>Record Not found</font></center><br><br>");
			out.print("<center><a href='/AutoLib/OrdInvProcessing/report.jsp'><img border=0 src='/AutoLib/images/Back.gif'></a></center>");
			}
		rs.beforeFirst();
		int sno=1;
		while(rs.next())
		{
			 out.print("<tr><td><font size=2>"+sno+"</td>");
			 out.print("<td>"+rs.getString("inv_no")+"</td>");
			 out.print("<td>"+getdate(rs.getString("inv_date"))+"</td>");
			 out.print("<td>"+rs.getString("sp_name")+"</td>");
			 out.print("<td>"+rs.getString("amount")+"</td>");
			 out.print("<td>"+rs.getString("year")+"</td>");
			 out.print("<td>"+rs.getString("totalofbooks")+"</td>");
			 out.print("<td>"+rs.getString("bud_head")+"</td>");
			 out.print("</tr>");
			 sno++;
	   	}
	out.print("</table>");
	}
	if(flag.equals("Bud_Stat"))
	 {
	Sqlstr = "select * from view_book_statistics";
		pstmt=con.prepareStatement(Sqlstr);
		rs1=pstmt.executeQuery();
		if(rs1.next()){
			out.print("<center><font size=4><b>Budget Statistics</b></font></center>");
			out.print("<br><table border=1 width=700 align=center CELLSPACING='0' BORDERCOLOR='#DADADA' BGCOLOR='#F5F5F5'>");
		    out.print("<th>Sno</th><th>bud_head</th><th>bud_amount</th><th>amount</th><th>noofbooks</th>");	
				}
		else{
			out.print("<br><br><br><br><br><br><br><br><br>");
			out.print("<center><font size=4>Record Not found</font></center><br><br>");
			out.print("<center><a href='/AutoLib/OrdInvProcessing/report.jsp'><img border=0 src='/AutoLib/images/Back.gif'></a></center>");
			}
		rs1.beforeFirst();
		
		int sno=1;
		while(rs1.next())
		{
		String bud_head=rs1.getString(1);
		double bud_amount=rs1.getDouble(2);
		double amount=rs1.getDouble(3);
		int noofbooks=rs1.getInt(4);

		total=total+amount;
		no_books=no_books+noofbooks;
		out.print("<tr><td><font size=2>"+sno+"</td>");
		out.print("<td>"+bud_head+"</td>");
		out.print("<td>"+bud_amount+"</td>");
		out.print("<td>"+amount+"</td>");
		out.print("<td>"+noofbooks+"</td>");

		 out.print("</tr>");
		 sno++;
		}
		out.print("<tr><td><font size=2></td>");
		out.print("<td></td>");
		out.print("<td></td>");
		out.print("<td>"+total+"</td>");
		out.print("<td>"+no_books+"</td>");

		out.print("</table>");

	} 

	}
		}  catch (Exception e) {
		e.printStackTrace();

					}
					finally{
					/*try{
					
					}
					catch(Exception exp){
					}*/
					total=0.0;
					no_books=0;
					Sqlstr_Inner="";
					Sqlstr="";
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
	String getdate(String datechk) {
    	//datechk=datechk.substring(0,datechk.length()-13);
    	   java.util.StringTokenizer stz_is=new java.util.StringTokenizer(datechk,"-");
		    int biy=Integer.parseInt(stz_is.nextToken());
		     int bim=Integer.parseInt(stz_is.nextToken());
		    int bid=Integer.parseInt(stz_is.nextToken());
	     	   String bissue_m=new Integer(bim).toString();
				    if(bissue_m.length()==1)
				    bissue_m="0"+bissue_m;
					String bissue_d=new Integer(bid).toString();
					if(bissue_d.length()==1)
				    bissue_d="0"+bissue_d;
					String BISSDATE=bissue_d+"-"+bissue_m+"-"+biy;
	return BISSDATE;
  }

  String getdate1(String datechk) {
    	//datechk=datechk.substring(0,datechk.length()-13);
    	   java.util.StringTokenizer stz_ie=new java.util.StringTokenizer(datechk,"-");
		    int bd=Integer.parseInt(stz_ie.nextToken());
		     int bm=Integer.parseInt(stz_ie.nextToken());
		    int by=Integer.parseInt(stz_ie.nextToken());
	     	   String bissu_m=new Integer(bm).toString();
				    if(bissu_m.length()==1)
				    bissu_m="0"+bissu_m;
					String bissu_d=new Integer(bd).toString();
					if(bissu_d.length()==1)
				    bissu_d="0"+bissu_d;
					String BISSDATE=by+"-"+bissu_m+"-"+bissu_d;
	return BISSDATE;
  }
  

}
