package Lib.Auto.Author;

import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServlet;
import java.text.SimpleDateFormat;

public class EmployeeDetailsReport extends HttpServlet
{


	/**********************	Declaration ***********************/

		Connection dbase;
    	Connection conn 		= null;
    	Statement stmt 			= null;
        ResultSet rs 			= null;
		FileWriter fw 			= null;
		String path 			= null;
		PrintWriter out;
		String	ROWSEP 			= "^";
		String	COLSEP 			= "~";

		public void service(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException
		{

			/***********************	Variable Declaration	*****************************/

			String sql 					= null;
			String tranType 			= null;
			String entry 				= null;
			String Emp_Id 			    = null;
			String Emp_Name 			= null;
			String Password 		    = null;
			String Designation_Id 		= null;
			String Dept_Id 			    = null;
			String Email_Id 			= null;
			String ContactNo 			= null;
			String Extension			= null;
			String Location_Id 			= null;
			String Doj					= null;
			String WorkExperience		= null;
			String Skills				= null;
			String Status				= null;
			String SkillType			= null;
			String Sess_Emp_Id 			= "";
			String Sess_Emp_Name 		= "";


			
			int rows 					= 0;
			int count 					= 0;
			int rowCount 				= 0;
			String empInfo 				= " ";
			String LocationInfo 		= " ";
			String deptInfo     		= " ";
			String designationInfo 		= " ";
			String parameterPassing 	= null;
			try
			{
				res.setContentType("text/html");
				out = res.getWriter();
				path = getServletContext().getRealPath("/");
				String contextPath = req.getContextPath();
				Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			 	dbase=DriverManager.getConnection("jdbc:odbc:timesheet","sa","sa");
				stmt=dbase.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);

				
		    

			HttpSession session =req.getSession(true);
			
			if ( session == null )
				{
						out.println("<pre> user NO ");
						RequestDispatcher rd = req.getRequestDispatcher("/ConfirmAlert.jsp");
						req.setAttribute("message","<font color=green>Session Expired - Relogin Again ! </font>");
						req.setAttribute("parameterPassing","/timesheet/Login.jsp");
						rd.forward(req,res);
				}
				if ( session.getAttribute("Emp_Id") == null )
				{
						parameterPassing = "/timesheet/Login.jsp";
						RequestDispatcher rd = req.getRequestDispatcher("/ConfirmAlert.jsp");
						req.setAttribute("message","<font color=green>Session Expired - Relogin Again ! </font>");
						req.setAttribute("parameterPassing",parameterPassing);
						rd.forward(req,res);
				}
				else 
				{
				
				//out.println("<pre> user Session ");
				Sess_Emp_Id = session.getAttribute("Emp_Id").toString();
				//out.println("Sess_Emp_Id: "+Sess_Emp_Id);
			
			
		
			
			tranType = req.getParameter("tranType");
				if (tranType == null)
					tranType = "null";

				//out.print("tranType :"+tranType);
				entry =	req.getParameter("entry");
				if ( entry == null )
					 entry = "null";

				//out.print("entry: "+entry);
			
			
			//out.println(" entry :1"+ entry);
			String loginUserPermission= (String) session.getAttribute("EmpDetail");
			//out.println("LoginUserPermision   :"+loginUserPermission);
			
				     if(tranType.equals("Employee Details"))
			          {
			          	if(loginUserPermission.equalsIgnoreCase("Y") )
			          	{
			          	
			             if(entry.equals("First"))

					       {
					       		RequestDispatcher rd = getServletContext().getRequestDispatcher("/employeeEmployeeDetails.jsp");
					       		rd.forward(req,res);
					       }

						else if(entry.equals("Second"))
				    	{
							if(req.getParameter("Completed") .equals("Act"))
							{


							String reportdate = "";
							String empName = "";

							sql = "SELECT GETDATE() AS TODAYDATE FROM EMPLOYEE ";
							rs = stmt.executeQuery(sql);

							if(rs.next())

								reportdate = rs.getString("TODAYDATE");//============



//							sql ="SELECT PROJECT_ID, CURRENCY_ID, COST_PER_HOUR FROM PROJECTEMPLINK WHERE EMP_ID='"+Emp_Id+"'";
							//sql = "SELECT E.EMP_NAME,E.Designation_Id,E.Dept_Id,E.Email_Id,E.ContactNo,E.Extension,E.Location_Id,E.Doj,E.WorkExperience,E.Skills,E.Status,E.SkillType GETDATE() AS TODAYDATE FROM EMPLOYEE E WHERE E.EMP_ID = '"+Emp_Id+"'";
						//	sql = "SELECT EMP_NAME, Designation_Id, Dept_Id, Email_Id, ContactNo, Extension, Location_Id, Doj, WorkExperience, Skills, Status, SkillType FROM EMPLOYEE  WHERE STATUS = '1'";
							sql = "SELECT PEL.EMP_NAME, D.DESIGNATION_DESC, DE.DEPT_DESC, PEL.EMAIL_ID, PEL.CONTACTNO, PEL.EXTENSION,"+
							      "L.LOCATION_NAME, PEL.DOJ, PEL.WORKEXPERIENCE, PEL.SKILLS, PEL.STATUS, PEL.SKILLTYPE "+
							      "FROM EMPLOYEE PEL INNER JOIN DESIGNATION D ON D.DESIGNATION_ID = PEL.DESIGNATION_ID "+
							      "INNER JOIN DEPARTMENT DE ON DE.DEPT_ID = PEL.DEPT_ID INNER JOIN LOCATION L ON L.LOCATION_ID = PEL.LOCATION_ID WHERE STATUS = '1'";
							int i=1;
								rs = stmt.executeQuery(sql);
								//out.println("rs  :"+rs.getRow());
								//out.println("sql  :"+sql);


							try
							{
									fw = new FileWriter(path+"//Reports//PrjRptActive.txt",false);
									fw.write("\r\n Report for Active Employee Details");
									fw.write("\r\n"+pad("",50, true)+"Date : "+reportdate + "\r\n");
									fw.write("\r\n**************************************************************************************************************************************************************************************************************************************************");
									fw.write("\r\nS.No   Emp Name        Designation Id       	Department Id       		Email Id        	ContactNo        	Extension        	Location Id        	Doj        		WorkExperience        	Skills        		Status        	SkillType   ");
									fw.write("\r\n**************************************************************************************************************************************************************************************************************************************************");
									path = getServletContext().getRealPath("/");

									out.println("<html>");
									out.println("<head>");
									out.println("<script language=\"JavaScript1.2\"> dqm__codebase = \"/timesheet/script/\"</script>");
									out.println("<script language=\"JavaScript1.2\" src=\"/timesheet/sample1_data.js\"></script>");
									out.println("<script language=\"JavaScript1.2\" src=\"/timesheet/script/tdqm_loader.js\"></script> ");
									out.println("<script language=\"JavaScript1.2\"> generate_mainitems()</script>");
									out.println("</head>");
									out.println("<body bgcolor=#e2e0d2><center>");
									out.println("<form name=f method=post action=/timesheet/servlet/DownloadReport>");
									
							   while(rs.next()) {

									fw.write("\r\n");
									fw.write(pad(String.valueOf(i), 7, false));
									fw.write(pad(rs.getString("Emp_Name"), 			20, false));
									fw.write(pad(rs.getString("Designation_Desc"), 	20, false));
									fw.write(pad(rs.getString("Dept_Desc"),         20, false));
									fw.write(pad(rs.getString("Email_Id"),          20, false));
									fw.write(pad(rs.getString("ContactNo"), 		20, false));
									fw.write(pad(rs.getString("Extension"), 		20, false));
									fw.write(pad(rs.getString("Location_Name"), 	20, false));
									fw.write(pad(rs.getString("Doj"), 				20, false));
									fw.write(pad(rs.getString("WorkExperience"), 	20, false));
									fw.write(pad(rs.getString("Skills"), 			20, false));
									fw.write(pad(rs.getString("Status"), 			20, false));
									fw.write(pad(rs.getString("SkillType"), 		0, true));

									fw.flush();
									i++;

						   	   	}
									fw.write("\r\n***************************************************************************************************************************************************************************************************************************************************");
									fw.close();

							   if(i == 1) {

									parameterPassing 	="/timesheet/main.jsp";
									RequestDispatcher rd = getServletContext().getRequestDispatcher("/ConfirmAlert.jsp");
									req.setAttribute("message","<font color=green>Records Not Found Employee</font> ");
									out.println("rowCount  :"+rowCount);
									out.println("Emp_Id  :"+Emp_Id);
									req.setAttribute("parameterPassing",parameterPassing);
									rd.forward(req,res);

								} else {

											out.println("<table border=3 cellpadding=2 width=60%>");
											out.println("<tr><td align=center colspan=2><b>Report Successfully Generated</b></td></tr>");
											out.println("<tr><td align=right><b>Total Details Processed</b></td>");
											out.println("<td>" + (i-1) + "</td></tr>");
											out.println("<tr><td align=right><b>Employee Details Report Filename</b></td>");
											out.println("<td>PrjRptActive.txt</td></tr>");
											out.println("</table>");
											out.println("<br><input type=submit name=download value=\"Click here to download\"><br>");
											out.println("<center><a href=\"/timesheet/main.jsp\">Cancel</a></center>");//<input type=Button value=Cancel name=b2>
											out.println("<input type=hidden name=filename value=\"PrjRptActive.txt\">");
											out.println("</center></form>");
											out.println("</body></html>");


										}
					   	     }
						   catch(SQLException se)
						   {
								se.printStackTrace(out);
								parameterPassing 	="/timesheet/servlet/EmployeeDetailsReport?entry=First&tranType=Employee Details";
								RequestDispatcher rd = getServletContext().getRequestDispatcher("/ConfirmAlert.jsp");
								req.setAttribute("parameterPassing",parameterPassing);
								req.setAttribute("message", "No Records Found for Employee");
								rd.forward(req,res);
						   }

					}//
					else if(req.getParameter("Completed") .equals("InAct"))
					{
						   String reportdate = "";
							String empName = "";
							int i=1;

							sql = "SELECT GETDATE() AS TODAYDATE FROM EMPLOYEE ";
							rs = stmt.executeQuery(sql);

							if(rs.next())
							reportdate = rs.getString("TODAYDATE");
							
							sql = "SELECT PEL.EMP_NAME, D.DESIGNATION_DESC, DE.DEPT_DESC, PEL.EMAIL_ID, PEL.CONTACTNO, PEL.EXTENSION,"+
							      "L.LOCATION_NAME, PEL.DOJ, PEL.WORKEXPERIENCE, PEL.SKILLS, PEL.STATUS, PEL.SKILLTYPE "+
							      "FROM EMPLOYEE PEL INNER JOIN DESIGNATION D ON D.DESIGNATION_ID = PEL.DESIGNATION_ID "+
							      "INNER JOIN DEPARTMENT DE ON DE.DEPT_ID = PEL.DEPT_ID INNER JOIN LOCATION L ON L.LOCATION_ID = PEL.LOCATION_ID WHERE STATUS = '0'";
							
							
							
							/*sql = "SELECT EMP_NAME, DESIGNATION_ID, DEPT_ID, EMAIL_ID, CONTACTNO, EXTENSION, "+
							      "LOCATION_ID, DOJ, WORKEXPERIENCE, SKILLS, STATUS, SKILLTYPE FROM EMPLOYEE  "+
							      "WHERE STATUS = '0'";*/
							      
							      rs = stmt.executeQuery(sql);
								
								try
								{
								 	fw = new FileWriter(path+"//Reports//PrjRptInActive.txt",false);
									fw.write("\r\n Report for InActive Employee Details");
									fw.write("\r\n"+pad("",50, true)+"Date : "+reportdate + "\r\n");
									fw.write("\r\n*******************************************************************************************************************************************************************************************************************");
									fw.write("\r\nS.No   Emp Name        Designation Id       Department Id       Email Id        ContactNo        Extension        Location Id        Doj        WorkExperience        Skills        Status        SkillType        ");
									fw.write("\r\n*******************************************************************************************************************************************************************************************************************");
									path = getServletContext().getRealPath("/");

									out.println("<html>");
									out.println("<head>");
									out.println("<script language=\"JavaScript1.2\"> dqm__codebase = \"/timesheet/script/\"</script>");
									out.println("<script language=\"JavaScript1.2\" src=\"/timesheet/sample1_data.js\"></script>");
									out.println("<script language=\"JavaScript1.2\" src=\"/timesheet/script/tdqm_loader.js\"></script> ");
									out.println("<script language=\"JavaScript1.2\"> generate_mainitems()</script>");
									out.println("</head>");
									out.println("<body bgcolor=#e2e0d2><center>");
									out.println("<form name=f method=post action=/timesheet/servlet/DownloadReport>");

							   while(rs.next()) {

									fw.write("\r\n");
									fw.write(pad(String.valueOf(i), 7, false));
									fw.write(pad(rs.getString("Emp_Name"), 30, false));
									fw.write(pad(rs.getString("Designation_Desc"), 30, false));
									fw.write(pad(rs.getString("Dept_Desc"), 30, false));
									fw.write(pad(rs.getString("Email_Id"), 30, false));
									fw.write(pad(rs.getString("ContactNo"), 30, false));
									fw.write(pad(rs.getString("Extension"), 30, false));
									fw.write(pad(rs.getString("Location_Name"), 30, false));
									fw.write(pad(rs.getString("Doj"), 30, false));
									fw.write(pad(rs.getString("WorkExperience"), 30, false));
									fw.write(pad(rs.getString("Skills"), 30, false));
									fw.write(pad(rs.getString("Status"), 30, false));
									fw.write(pad(rs.getString("SkillType"), 30, false));

									fw.flush();
									i++;

						   	   	}
						   	   	
									fw.write("\r\n*******************************************************************************************************************************************************************************************************************");
									fw.close();

							   if(i == 1) {

									parameterPassing 	="/timesheet/main.jsp";
									RequestDispatcher rd = getServletContext().getRequestDispatcher("/ConfirmAlert.jsp");
									req.setAttribute("message","<font color=green>Records Not Found in Employee</font> ");
									req.setAttribute("parameterPassing",parameterPassing);
									
								} else {

											out.println("<table border=3 cellpadding=2 width=60%>");
											out.println("<tr><td align=center colspan=2><b>Report Successfully Generated</b></td></tr>");
											out.println("<tr><td align=right><b>Total Details Processed</b></td>");
											out.println("<td>" + (i-1) + "</td></tr>");
											out.println("<tr><td align=right><b>InActive Employee Details Report Filename</b></td>");
											out.println("<td>PrjRptInActive.txt</td></tr>");
											out.println("</table>");
											out.println("<br><input type=submit name=download value=\"Click here to download\"><br>");
											out.println("<center><a href=\"/timesheet/main.jsp\">Cancel</a></center>");//<input type=\"Button\" value=\"Cancel\" name=b2>
											out.println("<input type=hidden name=filename value=\"PrjRptInActive.txt\">");
											out.println("</center></form>");
											out.println("</body></html>");


										}
					   	     }
						   catch(SQLException se)
						   {
								se.printStackTrace(out);
								parameterPassing 	="/timesheet/servlet/EmployeeDetailsReport?entry=First&tranType=Employee Details";
								RequestDispatcher rd = getServletContext().getRequestDispatcher("/ConfirmAlert.jsp");
								req.setAttribute("parameterPassing",parameterPassing);
								req.setAttribute("message", "<font color=red>No Records Found for Employee</font>");
								//rd.forward(req,res);
						   }

					}

					else if(req.getParameter("Completed") .equals("All"))
					{

						String reportdate = "";
							String empName = "";

							sql = "SELECT GETDATE() AS TODAYDATE FROM EMPLOYEE ";
							rs = stmt.executeQuery(sql);

							if(rs.next())

								reportdate = rs.getString("TODAYDATE");//============




							sql = "SELECT EMP_NAME, DESIGNATION_ID, DEPT_ID, EMAIL_ID, CONTACTNO, EXTENSION, "+
							      "LOCATION_ID, DOJ, WORKEXPERIENCE, SKILLS, STATUS, SKILLTYPE FROM EMPLOYEE ";
						
							int i=1;
								rs = stmt.executeQuery(sql);
								

							try {
									fw = new FileWriter(path+"//Reports//PrjRptAllemoloyee.txt",false);
									fw.write("\r\n Report for All Employee Details");
									fw.write("\r\n"+pad("",50, true)+"Date : "+reportdate + "\r\n");
									fw.write("\r\n*******************************************************************************************************************************************************************************************************************");
									fw.write("\r\nS.No   Emp Name        Designation Id       Department Id       Email Id        ContactNo        Extension        Location Id        Doj        WorkExperience        Skills        Status        SkillType        ");
									fw.write("\r\n*******************************************************************************************************************************************************************************************************************");
									path = getServletContext().getRealPath("/");

									out.println("<html>");
									out.println("<script language=\"JavaScript1.2\"> dqm__codebase = \"/timesheet/script/\"</script>");
									out.println("<script language=\"JavaScript1.2\" src=\"/timesheet/sample1_data.js\"></script>");
									out.println("<script language=\"JavaScript1.2\" src=\"/timesheet/script/tdqm_loader.js\"></script> ");
									out.println("<script language=\"JavaScript1.2\"> generate_mainitems()</script>");
									out.println("<head>");
									out.println("</head>");
									out.println("<body bgcolor=#e2e0d2><center>");
									out.println("<form name=f method=post action=/timesheet/servlet/DownloadReport>");

							   while(rs.next()) {

									fw.write("\r\n");
									fw.write(pad(String.valueOf(i), 7, false));
									fw.write(pad(rs.getString("Emp_Name"), 30, false));
									fw.write(pad(rs.getString("Designation_Id"), 30, false));
									fw.write(pad(rs.getString("Dept_Id"), 30, false));
									fw.write(pad(rs.getString("Email_Id"), 30, false));
									fw.write(pad(rs.getString("ContactNo"), 30, false));
									fw.write(pad(rs.getString("Extension"), 30, false));
									fw.write(pad(rs.getString("Location_Id"), 30, false));
									fw.write(pad(rs.getString("Doj"), 30, false));
									fw.write(pad(rs.getString("WorkExperience"), 30, false));
									fw.write(pad(rs.getString("Skills"), 30, false));
									fw.write(pad(rs.getString("Status"), 30, false));
									fw.write(pad(rs.getString("SkillType"), 30, false));

									fw.flush();
									i++;

						   	   	}
									fw.write("\r\n*******************************************************************************************************************************************************************************************************************");
									fw.close();

							   if(i == 1) {

									parameterPassing 	="/timesheet/main.jsp";
									RequestDispatcher rd = getServletContext().getRequestDispatcher("/ConfirmAlert.jsp");
									req.setAttribute("message","<font color=green>Records Not Found Employee</font> ");
									req.setAttribute("parameterPassing",parameterPassing);
									rd.forward(req,res);

								} else {

											out.println("<table border=3 cellpadding=2 width=60%>");
											out.println("<tr><td align=center colspan=2><b>Report Successfully Generated</b></td></tr>");
											out.println("<tr><td align=right><b>Total Details Processed</b></td>");
											out.println("<td>" + (i-1) + "</td></tr>");
											out.println("<tr><td align=right><b> All Employee Details Report Filename</b></td>");
											out.println("<td>PrjRptAllemoloyee.txt</td></tr>");
											out.println("</table>");
											out.println("<br><input type=submit name=download value=\"Click here to download\"><br>");
											out.println("<center><a href=\"/timesheet/main.jsp\">Cancel</a></center>");//<input type=\"Button\" value=\"Cancel\" name=b2>
											out.println("<input type=hidden name=filename value=\"PrjRptAllemoloyee.txt\">");
											out.println("</center></form>");
											out.println("</body></html>");


										}
					   	     }
						   catch(SQLException se)
						   {
								se.printStackTrace(out);
								parameterPassing 	="/timesheet/servlet/EmployeeDetailsReport?entry=First&tranType=Employee Details";
								RequestDispatcher rd = getServletContext().getRequestDispatcher("/ConfirmAlert.jsp");
								req.setAttribute("parameterPassing",parameterPassing);
								req.setAttribute("message", "No Records Found for Employee");
								rd.forward(req,res);
						   }

					}

					}
				}
				else
				{
					
					
					out.println("hello");
					RequestDispatcher rd = req.getRequestDispatcher("/ConfirmAlert.jsp");
					req.setAttribute("parameterPassing","/timesheet/main.jsp");
					req.setAttribute("message","<font color=red > Access Denied </font>");
					rd.forward(req,res);

			
				}
		}
	}

				}
				catch(Exception sqle)
				{
					sqle.printStackTrace(out);
					parameterPassing		=	"/timesheet/main.jsp";
					req.setAttribute("parameterPassing",parameterPassing);
					req.setAttribute("message","No Registered Report Codes");
				}
			}

	private String pad (String str, int length, boolean padFront)
	{

		str = (null == str)?new String ():str;
		String padStr = new String (str);
		if (length < str.length ()) {

			return str.substring (0, length);

		}

		for (int i = str.length (); i < length; i++) {

			if(!padFront) padStr = padStr + ' ';
			else padStr = ' ' + padStr;
		}
		return padStr;
	}


	private String padZeros (String str, int length, boolean padFront) {

		str = (null == str)?new String ():str;
		String padStr = new String (str);
		if (length < str.length ()) {

			return str.substring (0, length);

		}

		for (int i = str.length (); i < length; i++) {

			if(padFront) padStr = '0' + padStr;
			else padStr = padStr + '0';

		}
		return padStr;
	}

		protected String comboInfo (String sql, ResultSet rs, Statement stmt, HttpServletRequest req , HttpServletResponse res) throws ServletException,IOException
		{
			String comboInfo		=	"   -Select-   "+ COLSEP +"   -Select-   " ;
			String parameterPassing = null;
			try
			{
				rs = stmt.executeQuery(sql);
				if(!rs.last())
				{
					RequestDispatcher rd = getServletContext().getRequestDispatcher("/EmployeeDetailsReport.jsp");
					req.setAttribute("message","<font color=red>Records Not Found</font> ");
					req.setAttribute("parameterPassing","/timesheet/main.jsp");
					rd.forward(req,res);
				}
				else
				{
					out.println("rs.getRow() :"+rs.getRow());
					rs.beforeFirst();
					while ( rs.next())
					comboInfo	=	comboInfo + ROWSEP + rs.getString(1) + COLSEP + rs.getString(2);
					//+ COLSEP + rs.getString(3)+ COLSEP + rs.getString(4)+ COLSEP + rs.getString(5)+ COLSEP + rs.getString(6)+ COLSEP + rs.getString(7)+ COLSEP + rs.getString(8)+ COLSEP + rs.getString(9)+ COLSEP + rs.getString(10)+ COLSEP + rs.getString(11)+ COLSEP + rs.getString(12)+ COLSEP + rs.getString(13)
				    rs.close();
			    }
			}
			catch(SQLException sqle)
			{
				sqle.printStackTrace(out);
				parameterPassing		=	"/timesheet/main.jsp";
				req.setAttribute("parameterPassing",parameterPassing);
				req.setAttribute("message","No Registered Report Codes");
			}
			return comboInfo;
		}
 }