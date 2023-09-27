package Lib.Auto.Jnl_Approval;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.UnavailableException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Common.Security;
//import javax.servlet.http.HttpSession;

public class JnlApprove extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String SQLStringsave="",journal="",dept="",Date_From="",Date_To="",Status="",Amount="",deptname="";
	int jnl_code=0,mas_code=0,app_code=0,deptcode=0;
	Jnlapprovebean ob=new Jnlapprovebean();
	String protocol="";
	
	java.sql.Connection con=null;
	java.sql.Statement st=null;
    java.sql.ResultSet rs=null;
	ResourceBundle rb =null;
	
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
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{

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
	        
			String flag=request.getParameter("flag");
			st=con.createStatement();


		if(flag.equals("update")){
		out.print(flag);
		journal=request.getParameter("journal");
		dept=request.getParameter("dept");
		Date_From=getdate1(request.getParameter("Date_From"));
		Date_To=getdate1(request.getParameter("Date_To"));
		String from =getdate1(Date_From);
		String to =getdate1(Date_To);
		out.print(from);
		out.print(to);
		Status=request.getParameter("Status");
		Amount=request.getParameter("Amount");


		
		SQLStringsave ="select jmas.jnl_code as mas,depa.dept_name as deptname,depa.dept_code as deptcode from Journal_Mas jmas,department_mas depa where jmas.jnl_name='"+journal+"' and depa.dept_name='"+dept+"' ";

			out.print(SQLStringsave);
			rs = st.executeQuery(SQLStringsave);
			if (rs.next()){
			mas_code=rs.getInt("mas");
			deptname=rs.getString("deptname");
			deptcode=rs.getInt("deptcode");
			}

		String SQLStringupdate="update Journal_Approval set dept_code="+deptcode+",fromdate='"+from+"',todate='"+to+"',subamount="+Amount+",status='"+Status+"' where jnl_code= "+mas_code+"";
		out.print(SQLStringupdate);
		st.executeUpdate(SQLStringupdate);

		getServletConfig().getServletContext().getRequestDispatcher("/Journal_Approval/index.jsp?check=UpdateJournal").forward(request, response);
		}


		 if(flag.equals("save")){
			 out.print("cursor Here");
		journal=request.getParameter("journal");
		dept=request.getParameter("dept");
		Date_From=getdate1(request.getParameter("Date_From"));
		Date_To=getdate1(request.getParameter("Date_To"));
		Status=request.getParameter("Status");
		Amount=request.getParameter("Amount");

		
		SQLStringsave ="select jmas.jnl_code as mas,depa.dept_name as deptname,depa.dept_code as deptcode from Journal_Mas jmas,department_mas depa where jmas.jnl_name='"+journal+"' and depa.dept_name='"+dept+"' ";


			out.print(SQLStringsave);
			rs = st.executeQuery(SQLStringsave);
			if (rs.next()){
			mas_code=rs.getInt("mas");
   			deptname=rs.getString("deptname");
			deptcode=rs.getInt("deptcode");

			out.print("mas_code"+mas_code);
			out.print("deptname"+deptname);
			out.print("deptcode"+deptcode);
			}

			SQLStringsave="select jnl_code from journal_approval where jnl_code="+mas_code+"";
			out.print(SQLStringsave);
			rs = st.executeQuery(SQLStringsave);
			if (rs.next()){
			app_code=rs.getInt("jnl_code");
			}
			if(mas_code==app_code)
			{
			ob.setJname(journal);
			ob.setJdname(deptname);
			ob.setJfrom(Date_From);
			ob.setJto(Date_To);
			ob.setJstatus(Status);
			ob.setJamount(Amount);
			request.setAttribute("bean",ob);
getServletConfig().getServletContext().getRequestDispatcher("/Journal_Approval/index.jsp?check=UpdateCheck").forward(request, response);
			}

		else
		{
		out.print("app_code"+app_code);
		out.print("deptcode"+deptcode);
		out.print("date from"+Date_From);
		out.print("date to"+Date_To);
		out.print("amount"+Amount);
		out.print("status"+Status);
	
		 out.print("inside else");
		SQLStringsave="insert into Journal_Approval values("+mas_code+","+deptcode+",'"+Date_From+"','"+Date_To+"',"+Amount+",'"+Status+"')";
		out.print(SQLStringsave);
		   st.executeUpdate(SQLStringsave);
	getServletConfig().getServletContext().getRequestDispatcher("/Journal_Approval/index.jsp?check=SaveJournal").forward(request, response);
		}

		rs.close();


		}

		

			 }  catch (Exception e) {

					}
		    catch (Throwable theException) {

		   }
		
	}

String getdate(String datechk) {
    	
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
