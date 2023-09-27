package Lib.Auto.OrdInvProcessing;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ResourceBundle;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.UnavailableException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Common.DataQuery;
import Common.Security;
import Common.Security_Counter;


import Common.DBConnection;

public class InvoiceSearch extends HttpServlet {
	String protocol="",nm="",SQLString="",f1="",f2="",f3="";
	java.sql.Connection con = null;
	java.sql.PreparedStatement Prest = null;
	java.sql.Statement st=null;
	java.sql.ResultSet rs = null;
	ResourceBundle rb = ResourceBundle.getBundle("LocalStrings");
	
	

	public void doGet(HttpServletRequest request, HttpServletResponse response)  throws ServletException {

		performTask(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException{

		performTask(request, response);

	}
	public void performTask(
			HttpServletRequest request,
			HttpServletResponse response)  throws ServletException {


			try {
				HttpSession session = request.getSession(true);
				String res = Security.checkSecurity(1, session, response, request);		
				if(res.equalsIgnoreCase("Failure"))
				{
					response.sendRedirect("/AutoLib/Tree/sessiontimeout.jsp");  
					return;
				}
				
				response.setContentType("text/html");
				con=DBConnection.getInstance();
	            PrintWriter out = response.getWriter();
	            nm=request.getParameter("name");
			    out.print("Name"+nm);
			    String value=request.getParameter("sflag");
			    out.print(value);
			    if(value.equals("Slno"))
			    {
                 ArrayList list=null;
                 orderbean ob=null;
	  SQLString ="select sno,ord_no,ord_date from or_inv_pay where 2>1 and sno like('" + nm + "%')";
	  Prest=con.prepareStatement(SQLString);
	  rs = Prest.executeQuery();
	  list=new ArrayList();
	    while(rs.next()){
               ob=new orderbean();
		       ob.setSno(rs.getInt("sno"));
	           ob.setIord(rs.getString("ord_no"));
	           ob.setIordate(Security_Counter.Counter_DateFormat(rs.getDate("ord_date")));
               list.add(ob);
			    }
	    request.setAttribute("search", list);
			//ob.setAl(ser);
		 getServletConfig().getServletContext().getRequestDispatcher("/OrdInvProcessing/search_nmvc.jsp?check=ok&flag="+value+"").forward(request, response);
		 }
			    if(value.equals("Dept"))
			    {
			    	ArrayList list=null;
			    	orderbean ob=null;
	  SQLString ="select * from department_mas where 2>1 and Dept_name like('" + nm + "%')";
	  out.print(SQLString);
	  st=con.createStatement();
	  rs = st.executeQuery(SQLString);
	  list=new ArrayList();
	    while(rs.next()){
               ob=new orderbean();
		       ob.setIdcode(rs.getInt("Dept_Code"));
	           ob.setIdname(rs.getString("Dept_Name"));
	           ob.setShort_desc(rs.getString("Short_Desc"));
               list.add(ob);
			    }
	    out.print("karthikeyan1");
	   	    request.setAttribute("search", list);
	   	    out.print("karthikeyan2");
			//ob.setAl(ser);
		 getServletConfig().getServletContext().getRequestDispatcher("/OrdInvProcessing/search_nmvc.jsp?check=ok&flag="+value+"").forward(request, response);
		 }
	      if(value.equals("Sup"))
	      {
			ArrayList list=null;
			orderbean ob=null;
	  SQLString ="select * from sup_pub_mas where 2>1 and SP_name like('" + nm + "%') and sp_type='SUPPLIER'";
	  Prest=con.prepareStatement(SQLString);
	  rs = Prest.executeQuery();
	  list=new ArrayList();
	    while(rs.next()){
               ob=new orderbean();
		       ob.setIscode(rs.getInt("SP_Code"));
	           ob.setIsname(rs.getString("SP_Name"));
	           ob.setShort_desc(rs.getString("Short_Desc"));
               list.add(ob);
			    }
	    	    request.setAttribute("search", list);
			//ob.setAl(ser);
		 getServletConfig().getServletContext().getRequestDispatcher("/OrdInvProcessing/search_nmvc.jsp?check=ok&flag="+value+"").forward(request, response);
		 }
	      
	      if(value.equals("Bud"))
	      {
			ArrayList list=null;
			orderbean ob=null;
	  SQLString ="select * from budget_mas where 2>1 and bud_head like('" + nm + "%')";
	  Prest=con.prepareStatement(SQLString);
	  rs = Prest.executeQuery();
	  out.print(Prest);
	  list=new ArrayList();
	    while(rs.next()){
               ob=new orderbean();
		       ob.setIbcode(rs.getInt("bud_code"));
	           ob.setIbhead(rs.getString("bud_head"));
	           ob.setIamount(rs.getDouble("bud_Amount"));
               list.add(ob);
			    }
	    	    request.setAttribute("search", list);
			//ob.setAl(ser);
		 getServletConfig().getServletContext().getRequestDispatcher("/OrdInvProcessing/search_nmvc.jsp?check=ok&flag="+value+"").forward(request, response);
		 }
	      
	      if(value.equals("Inv"))
	      {
			ArrayList list=null;
			orderbean ob=null;
	        SQLString ="select * from invoice_mas where 2>1 and inv_no like('" + nm + "%')";
	        Prest=con.prepareStatement(SQLString);
	        rs = Prest.executeQuery();
	        list=new ArrayList();
	    while(rs.next()){
               ob=new orderbean();
		       ob.setIinvno(rs.getString("Inv_no"));
	           ob.setIscode(rs.getInt("Sup_code"));
	           ob.setIinvdate(Security_Counter.Counter_DateFormat(rs.getDate("inv_date")));
               list.add(ob);
			    }
	    	    request.setAttribute("search", list);
			//ob.setAl(ser);
		 getServletConfig().getServletContext().getRequestDispatcher("/OrdInvProcessing/search_nmvc.jsp?check=ok&flag="+value+"").forward(request, response);
		 }
	      
	      if(value.equals("Dept_Report"))
		    {
		    	ArrayList list=null;
		    	orderbean ob=null;
SQLString ="select * from department_mas where 2>1 and Dept_name like('" + nm + "%')";
out.print(SQLString);
st=con.createStatement();
rs = st.executeQuery(SQLString);
list=new ArrayList();
  while(rs.next()){
         ob=new orderbean();
	       ob.setIdcode(rs.getInt("Dept_Code"));
         ob.setIdname(rs.getString("Dept_Name"));
         ob.setShort_desc(rs.getString("Short_Desc"));
         list.add(ob);
		    }
  out.print("karthikeyan1");
 	    request.setAttribute("search", list);
 	    out.print("karthikeyan2");
		//ob.setAl(ser);
	 getServletConfig().getServletContext().getRequestDispatcher("/OrdInvProcessing/search_nmvc.jsp?check=ok&flag="+value+"").forward(request, response);
	 }
	      
	      if(value.equals("Sup_Report"))
	      {
			ArrayList list=null;
			orderbean ob=null;
	  SQLString ="select * from sup_pub_mas where 2>1 and SP_name like('" + nm + "%') and sp_type='SUPPLIER'";
	  Prest=con.prepareStatement(SQLString);
	  rs = Prest.executeQuery();
	  list=new ArrayList();
	    while(rs.next()){
               ob=new orderbean();
		       ob.setIscode(rs.getInt("SP_Code"));
	           ob.setIsname(rs.getString("SP_Name"));
	           ob.setShort_desc(rs.getString("Short_Desc"));
               list.add(ob);
			    }
	    	    request.setAttribute("search", list);
			//ob.setAl(ser);
		 getServletConfig().getServletContext().getRequestDispatcher("/OrdInvProcessing/search_nmvc.jsp?check=ok&flag="+value+"").forward(request, response);
		 }
	      if(value.equals("Budget_Report"))
	      {
			ArrayList list=null;
			orderbean ob=null;
	  SQLString ="select * from budget_mas where 2>1 and bud_head like('" + nm + "%')";
	  Prest=con.prepareStatement(SQLString);
	  rs = Prest.executeQuery();
	  out.print(Prest);
	  list=new ArrayList();
	    while(rs.next()){
               ob=new orderbean();
		       ob.setIbcode(rs.getInt("bud_code"));
	           ob.setIbhead(rs.getString("bud_head"));
	           ob.setIamount(rs.getDouble("bud_Amount"));
               list.add(ob);
			    }
	    	    request.setAttribute("search", list);
			//ob.setAl(ser);
		 getServletConfig().getServletContext().getRequestDispatcher("/OrdInvProcessing/search_nmvc.jsp?check=ok&flag="+value+"").forward(request, response);
		 }
	      
	      
	   
			}  catch (Exception e) {

		}
	catch (Throwable theException) {

	}
	finally{
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
