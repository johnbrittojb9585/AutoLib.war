package Lib.Auto.Counter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.ParseException;
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


/**
 * @author Counter
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class Counter_karthi
	extends HttpServlet
	implements Serializable, COUNTER_QUERY {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ResourceBundle rb = ResourceBundle.getBundle("LocalStrings");
	CounterMemberBean counterbeanobject = new CounterMemberBean();
	CounterMethods methods = new CounterMethods();
	ArrayList DEATILS=new ArrayList ();
	java.sql.Connection con = null;
	
	java.sql.PreparedStatement Prest = null;
	java.sql.ResultSet rs = null;
	java.sql.Statement st = null;
	String indexPage = null;
	int Groups = 0;
	String protocol = "",min="",flag = "",temp1="",pri="",no_days="",temp2="",valid="",sql1="";

	
	

	
	
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

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServletException {

		performTask(request, response);

	}

	public void doPost(
		HttpServletRequest request,
		HttpServletResponse response)
		throws ServletException, IOException {

		performTask(request, response);

	}
	
	/**
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 */
	public void performTask(
		HttpServletRequest request,
		HttpServletResponse response)
		throws IOException, ServletException {

		try {
			HttpSession session = request.getSession(true);
			String res = Security.checkSecurity(1, session, response, request);		
			if(res.equalsIgnoreCase("Failure"))
			{
				response.sendRedirect("/AutoLib/Tree/sessiontimeout.jsp");  
				return;
			}
			
			con =
				DriverManager.getConnection(
					protocol,
					rb.getString("Username"),
					rb.getString("Password"));
			response.setContentType("text/html");
			
			
			PrintWriter out = response.getWriter();
			flag = request.getParameter("flag");
			st = con.createStatement();
			if(flag.equals("clear")) {
				response.sendRedirect(COUNTER_QUERY.CLEAR_PAGE);
			}
			//--------------------------------------member-------------------------------------------------------------*/			
			if(flag.equals("member")) {

				Prest = con.prepareStatement(COUNTER_QUERY.SELECT_MEMBER);
				Prest.setString(1, request.getParameter("mcode"));
				rs = Prest.executeQuery();
				if (rs.next()) {
					counterbeanobject.setMcode(rs.getString("member_code"));
					counterbeanobject.setMname(rs.getString("member_name"));
					counterbeanobject.setGroup(rs.getString("group_name"));
					counterbeanobject.setDesig(rs.getString("desig_name"));
					counterbeanobject.setDept(rs.getString("dept_name"));
					counterbeanobject.setValidDate(
					Security.Counter_DateGet(rs.getDate("expiry_date")));
					counterbeanobject.setImg(rs.getString("photo"));
					counterbeanobject.setCourse(rs.getString("course_name"));
					counterbeanobject.setYear(rs.getString("cyear"));
					counterbeanobject.setGeligible(rs.getInt("gelg"));
					counterbeanobject.setGperiod(rs.getInt("gper"));
					counterbeanobject.setBeligible(rs.getInt("belg"));
					counterbeanobject.setBperiod(rs.getInt("bper"));
					counterbeanobject.setBbeligible(rs.getInt("bbelg"));
					counterbeanobject.setBbperiod(rs.getInt("bbper"));
					counterbeanobject.setNbeligible(rs.getInt("nbelg"));
					counterbeanobject.setNbperiod(rs.getInt("nbper"));
					counterbeanobject.setJeligible(rs.getInt("jelg"));
					counterbeanobject.setJperiod(rs.getInt("jlper"));
					counterbeanobject.setBveligible(rs.getInt("bvelg"));
					counterbeanobject.setBvperiod(rs.getInt("bvper"));
					counterbeanobject.setTeligible(rs.getInt("telg"));
					counterbeanobject.setTperiod(rs.getInt("tper"));
					counterbeanobject.setSeligible(rs.getInt("selg"));
					counterbeanobject.setSperiod(rs.getInt("sper"));
					counterbeanobject.setPeligible(rs.getInt("pelg"));
					counterbeanobject.setPperiod(rs.getInt("pper"));
					counterbeanobject.setReligible(rs.getInt("relg"));
					counterbeanobject.setRperiod(rs.getInt("rper"));
					methods.ISSUEDETAILSMEMBER(
						request.getParameter("mcode"),
						con);
					methods.bookcount_doctype(
						request.getParameter("mcode"),
						out,
						con);
					indexPage =MEMBER_DISPLAY;
					dispatch(request, response, indexPage);
				} else {
					response.sendRedirect(MEMBER_NOTFOUND);
					//indexPage ="/Counter/index.jsp?Message=MemberNotFound";
				} 

			}
			//--------------------------------------BOOK-------------------------------------------------------------*/			

			if (flag.equals("book")) {
				Prest = con.prepareStatement(COUNTER_QUERY.SELECT_BOOK);
				Prest.setString(1, request.getParameter("accno"));
				rs = Prest.executeQuery();
				if (rs.next()) {
					counterbeanobject.setAccno(rs.getString("access_no"));
					counterbeanobject.setTitle(rs.getString("title"));
					counterbeanobject.setAuthor(rs.getString("author_name"));
					counterbeanobject.setCallNo(rs.getString("call_no"));
					counterbeanobject.setPublisher(rs.getString("publisher"));
					String BOOK_AVL = rs.getString("Availability");
					counterbeanobject.setAvail(BOOK_AVL);
					String doctype = rs.getString("document");
					counterbeanobject.setDoc(doctype);
					if (BOOK_AVL.equals("ISSUED")) {
						BOOK_ISSUED(request.getParameter("accno"), out);
						indexPage =
							"/Counter/index.jsp?flag=member&detils=ISSUEDEATILS&load=loadbook&Reservedetils=RESERVEDEATILS";
					} else {
						if (request.getParameter("mcode").equals("")) {
							indexPage = "/Counter/index.jsp?flag=book";
						} else {
							MEMBER_LOAD(
								request.getParameter("mcode"),
								doctype,
								response);
							methods.ISSUEDETAILSMEMBER(
								request.getParameter("mcode"),
								con);
							indexPage =
								"/Counter/index.jsp?flag=member&load=loadbook&detils=ISSUEDEATILS";
						}

					}

				} else {
					indexPage = "/Counter/index.jsp?Message=BookNotbook";
				}

				dispatch(request, response, indexPage);
			}

			// BOOK ISSUE-----------------------------------------------------------------------------------//
			if (flag.equals("issue")) {
				valid=Security.TextDate_Insert(request.getParameter("validDate"));
				String today=Security.TodayDate();
				Prest = con.prepareStatement(COUNTER_QUERY.valid_date);
				Prest.setString(1, valid);
				Prest.setString(2, today);
				
				rs = Prest.executeQuery();
				if (rs.next()) {
					no_days = rs.getString("no_of_days");
					
				
				}
				int s=Integer.parseInt(no_days);
				if (s < 0) {
					response.sendRedirect("/AutoLib/Counter/index.jsp?flags=valid");

				}
				else
				{
				Prest = con.prepareStatement(COUNTER_QUERY.SELECT_RESERVE_PRI);
				Prest.setString(1,request.getParameter("accno"));
				rs = Prest.executeQuery();
			       if(rs.next()){
			    	   if(rs.getString("mm")!=null){
				    	 min=rs.getString("mm");
			    	   }
			    	  
	               }
			       out.print("haiiiii"+min);
			        Prest = con.prepareStatement(COUNTER_QUERY.SELECT_RESERVE_PRI1);
					Prest.setString(1, request.getParameter("accno"));
					rs = Prest.executeQuery();
	if(rs.next()){
	temp1=rs.getString("member_code");
	pri=rs.getString("id");
	temp2=rs.getString("member_name");
	if((temp1.equals(request.getParameter("mcode")) && (pri.equals(min)))){
		
	
	
	Prest = con.prepareStatement(COUNTER_QUERY.DELETE_RESERVE_MAS);
	Prest.setString(1, request.getParameter("mcode"));
	Prest.setString(2, request.getParameter("accno"));
	Prest.executeUpdate();

			Prest = con.prepareStatement(COUNTER_QUERY.SELECT_BOOKMAS);
			Prest.setString(1, request.getParameter("accno"));
			rs = Prest.executeQuery();
			if (rs.next()) {
				String avail = rs.getString("availability");
				if (avail.equals("YES")) {
					rs.absolute(1);
					st.execute(
						"update book_mas set availability='ISSUED' where access_no='"
							+ request.getParameter("accno")
							+ "'");
				}
				Prest = con.prepareStatement(COUNTER_QUERY.INSERT_STRING);
				Prest.setString(1, request.getParameter("mcode"));
				Prest.setString(2, request.getParameter("accno"));
				Prest.setString(3, Security.TextDate_Insert(
						request.getParameter("idate")));
				Prest.setString(
					4,
					Security.TextDate_Insert(
						request.getParameter("ddate")));
				Prest.setString(5, "ISSUE");
				Prest.setObject(6, session.getAttribute("UserID"));
				Prest.setString(7, request.getParameter("doc"));
				Prest.executeUpdate();
				response.sendRedirect(
					"/AutoLib/Counter/index.jsp?Message=issued&detils=ISSUEDEATILS");

			}
			

			methods.ISSUEDETAILSMEMBER(request.getParameter("mcode"), con);
			methods.bookcount_doctype(
				request.getParameter("mcode"),
				out,
				con);

		
	
	
	//praba
	
	}
	
	else
	{
		
		//praba
		
		request.setAttribute("temp1",temp1);
		request.setAttribute("temp2",temp2);
		ISSUEDETAILS(request.getParameter("mcode"),request.getParameter("accno"),st,rs);
		getServletConfig().getServletContext().getRequestDispatcher("/Counter/index.jsp?Message=issue_error&detils=ISSUEDEATILS").forward(request, response);
		
		
		
		
			}
	}
	else
	{
		Prest = con.prepareStatement(COUNTER_QUERY.SELECT_BOOKMAS);
		Prest.setString(1, request.getParameter("accno"));
		rs = Prest.executeQuery();
		if (rs.next()) {
			
						String avail=rs.getString("availability");
						if(avail.equals("YES")){
						rs.absolute(1);
	                    st.execute("update book_mas set availability='ISSUED' where access_no='"
								+ request.getParameter("accno")
								+ "'");
						
				  		}
			
						Prest = con.prepareStatement(COUNTER_QUERY.INSERT_STRING);
						Prest.setString(1, request.getParameter("mcode"));
						Prest.setString(2, request.getParameter("accno"));
						Prest.setString(3, Security.TextDate_Insert(
								request.getParameter("idate")));
						Prest.setString(
							4,
							Security.TextDate_Insert(
								request.getParameter("ddate")));
						Prest.setString(5, "ISSUE");
						Prest.setObject(6, session.getAttribute("UserID"));
						Prest.setString(7, request.getParameter("doc"));
						Prest.executeUpdate();
			ISSUEDETAILS(request.getParameter("mcode"),request.getParameter("accno"),st,rs);
			
			response.sendRedirect(
			"/AutoLib/Counter/index.jsp?Message=issued&detils=ISSUEDEATILS&avoid=error");

					}
			   }
	
	methods.bookcount_doctype(
			request.getParameter("mcode"),
			out,
			con);
	
			}
		}

			//--------------------------------------RETURN------------------------------------------------------------*/

			if (flag.equals("return")) {

				Prest = con.prepareStatement(COUNTER_QUERY.SELECT_RETURN_BOOK);
				Prest.setString(1, request.getParameter("accno"));
				Prest.setString(2, request.getParameter("mcode"));
				rs = Prest.executeQuery();
				if (rs.next()) {
					String avail = rs.getString("availability");
					if (avail.equals("ISSUED")) {
						rs.absolute(1);
						st.execute(
							"update book_mas set availability='YES' where access_no='"
								+ request.getParameter("accno")
								+ "'");
						
						Prest = con.prepareStatement(COUNTER_QUERY.DELETE_RENEWAL);
						Prest.setString(1, request.getParameter("accno"));
						Prest.setString(2, request.getParameter("mcode"));
						Prest.executeUpdate();

						Prest = con.prepareStatement(COUNTER_QUERY.SELECT_ISSUEMAS);
						Prest.setString(1, request.getParameter("accno"));
						rs = Prest.executeQuery();
						if (rs.next()) {
							methods.DAYSCALRETURN(
								Security.Counter_DateGet(
									rs.getDate("due_date")),
								Integer.parseInt(rs.getString("group_code")),
								con);
							Double Temp = counterbeanobject.getTemp();
							if (Temp.doubleValue() == 0.0) {
								methods.RETURN_UPDATE(request, session, con);
							
								response.sendRedirect(
								"/AutoLib/Counter/index.jsp?Message=return&detils=ISSUEDEATILS");

							} else {
								out.print("fine for this doc"+Temp);
								session.setAttribute("FINE", Temp);
								methods.RETURN_UPDATE_FINE(request, session, con, Temp);
								
								
								response.sendRedirect(
								"/AutoLib/Counter/index.jsp?flag=freturn&detils=ISSUEDEATILS");

							}
						}

					}

				}

				methods.ISSUEDETAILSMEMBER(request.getParameter("mcode"), con);
				methods.bookcount_doctype(
					request.getParameter("mcode"),
					out,
					con);

			}
			//-----------------------------------RESERVE----------------------------------------------------//
			if (flag.equals("reserve")) {
				int cnt = 0;
				methods.ISSUEDETAILSMEMBER(request.getParameter("mcode"), con);
				Prest = con.prepareStatement(COUNTER_QUERY.SELECT_ISSUEMAS_ONLY);
				Prest.setString(1, request.getParameter("mcode"));
				Prest.setString(2, request.getParameter("accno"));
				rs = Prest.executeQuery();
				if (rs.next()) {
					//indexPage =
					response.sendRedirect(
						"/AutoLib/Counter/index.jsp?Message=reser&detils=ISSUEDEATILS");
				} else {

					Prest = con.prepareStatement(COUNTER_QUERY.SELECT_RESERVEMAS);
					Prest.setString(1, request.getParameter("mcode"));
					Prest.setString(2, request.getParameter("accno"));
					rs = Prest.executeQuery();
					if (rs.next()) {

						response.sendRedirect(
							"/AutoLib/Counter/index.jsp?Message=res_error&detils=ISSUEDEATILS");
					} else {
						String SQLstr =
							"select max(ID) as maxno from reserve_mas";
						rs = st.executeQuery(SQLstr);
						if (rs.next()) {
							cnt = rs.getInt("maxno");
							cnt = cnt + 1;
						}
						Prest = con.prepareStatement(COUNTER_QUERY.INSERT_RESERVEMAS);
						Prest.setInt(1, cnt);
						Prest.setString(2, request.getParameter("mcode"));
						Prest.setString(3, request.getParameter("accno"));
						Prest.setString(4, request.getParameter("doc"));
						Prest.setString(
							5,
							Security.TextDate_Insert(
								request.getParameter("rdate")));
						Prest.executeUpdate();
						methods.RESERVEDETAILS(
							request.getParameter("accno"),
							con);
						response.sendRedirect(
							"/AutoLib/Counter/index.jsp?Message=reserved&Reservedetils=RESERVEDEATILS&detils=ISSUEDEATILS");
					}

				}

			}
			//----------------------------------*--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\\

			if (flag.equals("rescancel")) {
				methods.ISSUEDETAILSMEMBER(request.getParameter("mcode"), con);

				Prest = con.prepareStatement(COUNTER_QUERY.SELECT_RESERVEMAS);
				Prest.setString(1, request.getParameter("mcode"));
				Prest.setString(2, request.getParameter("accno"));
				rs = Prest.executeQuery();

				if (rs.next()) {

					Prest = con.prepareStatement(COUNTER_QUERY.DELETE_RESERVEMAS);
					Prest.setString(1, request.getParameter("mcode"));
					Prest.setString(2, request.getParameter("accno"));
					Prest.executeUpdate();
					response.sendRedirect(
						"/AutoLib/Counter/index.jsp?Message=rescancel&detils=ISSUEDEATILS");

				} else {
					response.sendRedirect(
						"/AutoLib/Counter/index.jsp?Message=rescancel_error&detils=ISSUEDEATILS");
				}

			}

			//--------------------------RENEW BOOK ----------------------------------------------------//		

			if (flag.equals("renew")) {

				String DueDate = "";
				String no_of_days = "";
				String sql = "";
				int no = 1;
				String ddate = "";
				String Rdate = "";
				String Ddate = "";
				int n = 0;
				int Holiday_Check = 0, cal = 0, Holiday = 0;
				double temp = 0;
				Prest = con.prepareStatement(COUNTER_QUERY.SELECT_ISSUEMAS);
				Prest.setString(1, request.getParameter("accno"));
				rs = Prest.executeQuery();
				if (rs.next()) {

					DueDate = Security.Counter_DateGet(rs.getDate("due_date"));
					sql="select datediff(sysdate(),'"+rs.getDate("due_date")+"') as no_of_days";
					Groups = Integer.parseInt(rs.getString("group_code"));
				}
				java.util.StringTokenizer stz_du =
					new java.util.StringTokenizer(Security.TodayDate(), "-");
					int diy = Integer.parseInt(stz_du.nextToken());
					int dim = Integer.parseInt(stz_du.nextToken());
					int did = Integer.parseInt(stz_du.nextToken());
					Rdate = diy + "-" + dim + "-" + did;

			java.util.StringTokenizer stz_du1 =
				new java.util.StringTokenizer(DueDate, "-");
				int diy1 = Integer.parseInt(stz_du1.nextToken());
				int dim1 = Integer.parseInt(stz_du1.nextToken());
				int did1= Integer.parseInt(stz_du1.nextToken());
				ddate = diy1 + "-" + dim1 + "-" + did1;

			
				
				rs = st.executeQuery(sql);
				 if (rs.next()) {
					no_of_days = rs.getString("no_of_days");
					

					
					n=Integer.parseInt(no_of_days);
					if (n < 0) {
						out.print("Inside with out fine");

						MEMBER_LOAD(
							request.getParameter("mcode"),
							request.getParameter("doc"),
							response);
						n = counterbeanobject.getCountperiod();
						int a = 0;
						
						a = n * 1;
						
						sql="select adddate('"+Rdate+"', '"+a+"') as days";
						
						
						rs = st.executeQuery(sql);
						if (rs.next()) {
							Ddate = rs.getString("days");
							
						}

						boolean Rflag = true;
						while (Rflag == true) {
							
							sql="select date_format('"+Ddate+"','%w') as days";
							rs = st.executeQuery(sql);
							if (rs.next()) {
								String day = rs.getString("days");
								int name_of_day = Integer.parseInt(day);

								if (name_of_day == 0) {

									
									a = 1;
									
									sql="select adddate('"+Ddate+"', '"+a+"') as days";

									rs = st.executeQuery(sql);
									if (rs.next()) {
										Ddate = rs.getString("days");
										

									}
								}

								if (name_of_day == 6) {

									
									a = 2 * 1;
									
									sql="select adddate('"+Ddate+"', '"+a+"') as days";

									rs = st.executeQuery(sql);
									if (rs.next()) {
										Ddate = rs.getString("days");
										

									}
								}

								sql =
									"select Leave_date  from holiday_mas where leave_date='"
										+ Ddate
										+ "'";
								rs = st.executeQuery(sql);

								if (rs.next()) {
									
									a = 1;
									
									sql="select adddate('"+Ddate+"', '"+a+"') as days";

									rs = st.executeQuery(sql);

									if (rs.next()) {
										Ddate = rs.getString("days");
										
										Rflag = true;

									}

								} else {
									Rflag = false;
								}
							}
						}
						
						UPDATE_RENEW_MAS_NOFINE(request,response,Groups,session,out,Ddate,Rdate);
						methods.ISSUEDETAILSMEMBER(request.getParameter("mcode"),con);
						response.sendRedirect("/AutoLib/Counter/index.jsp?Message=renew&detils=ISSUEDEATILS");
						
																	
					} else {
						out.print("Inside with fine");
						for (int i = 0; i < n; i++) {

							int a = 0;
							
							a = i * 1;
							
							sql="select adddate('"+ddate+"', '"+a+"') as days";
							
							rs = st.executeQuery(sql);
							if (rs.next()) {
								Ddate = rs.getString("days");
								
								sql="select date_format('"+Ddate+"','%w') as days";
								
								rs = st.executeQuery(sql);
								if (rs.next()) {
									String day = rs.getString("days");
									out.print("ERROR DATE"+day);
									int name_of_day = Integer.parseInt(day);

									if ((name_of_day == 0)
										|| (name_of_day == 6)) {
										Holiday = Holiday + 0;

									} else {

										sql =
											"select Leave_date  from holiday_mas where leave_date='"
												+ Ddate
												+ "'";
										rs = st.executeQuery(sql);
										if (rs.next()) {
											String datew =
												rs.getString("Leave_date");

											Holiday = Holiday + 0;

										} else {
											Holiday = Holiday + 1;

										}

									}
								}
							}
						}
						
					out.print("duedate2"+Ddate);
					out.print("returndate2"+Rdate);
					methods.FINE_CALL(Groups, Holiday, con);
					Double Temp = counterbeanobject.getTemp();
					out.print("sdfsadfas"+Temp);
					session.setAttribute("FINE", Temp);
					 UPDATE_RENEW_MAS(
						request,
						response,
						Groups,
						session,
						out,
						Ddate,
						Rdate,Temp); 
					methods.ISSUEDETAILSMEMBER(
						request.getParameter("mcode"),
						con);
					response.sendRedirect(
					"/AutoLib/Counter/index.jsp?flag=fwreturn&detils=ISSUEDEATILS");

		}
				} 

				//------------------------------------------
			}
			//-----------------------------------------------------------------END TRY CATCH----------------//
		} catch (Exception e) {
			throw new ServletException(e);
		} finally {

			Security.closePreparedStatement(Prest);
			try {
				Security.closeConnection(con);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			Security.closeResultSet(rs);
			indexPage = "";

		}

	}

	/**
	 * @param request
	 * @param response
	 * @param indexPage
	 * @throws ServletException
	 * @throws IOException
	 */
	//-------------------------------------------METHOD---------------------------------------------//

	public void dispatch(
		HttpServletRequest request,
		HttpServletResponse response,
		String indexPage)
		throws ServletException, IOException {
		response.getWriter().print(indexPage);
		//response.sendRedirect(indexPage);
		RequestDispatcher dispatch = request.getRequestDispatcher(indexPage);
		dispatch.forward(request, response);
	}

	/**
	 * @param loadmemcode
	 * @param doctype
	 * @param res
	 * @throws SQLException
	 * @throws IOException
	 * @throws javax.servlet.ServletException
	 */
	public void MEMBER_LOAD(
		String loadmemcode,
		String doctype,
		HttpServletResponse res)
		throws SQLException, IOException, javax.servlet.ServletException {
		int member_period = 0;
		res.setContentType("text/html");
		PrintWriter response = res.getWriter();
		Prest = con.prepareStatement(COUNTER_QUERY.SELECT_MEMBER);
		Prest.setString(1, loadmemcode);
		rs = Prest.executeQuery();
		if (rs.next()) {
			counterbeanobject.setMcode(rs.getString("member_code"));
			counterbeanobject.setMname(rs.getString("member_name"));
			counterbeanobject.setGroup(rs.getString("group_name"));
			counterbeanobject.setDesig(rs.getString("desig_name"));
			counterbeanobject.setDept(rs.getString("dept_name"));
			counterbeanobject.setValidDate(
			Security.Counter_DateGet(rs.getDate("expiry_date")));
			counterbeanobject.setImg(rs.getString("photo"));
			counterbeanobject.setCourse(rs.getString("course_name"));
			counterbeanobject.setYear(rs.getString("cyear"));

			Groups = rs.getInt("group_code");
			String status = rs.getString("status");
			if (status.equals("V1")) {
				response.print("g");
				counterbeanobject.setGeligible(rs.getInt("gelg"));
				member_period = rs.getInt("gper");
				counterbeanobject.setGperiod(member_period);
				//DAYSCAL(c1_gper, st, rs, res);
			} else if (status.equals("V2")) {

				if (doctype.equals("BOOK")) {
					counterbeanobject.setBbeligible(rs.getInt("belg"));
					member_period = rs.getInt("bper");
					counterbeanobject.setBbperiod(member_period);
					//DAYSCAL(c_bper, st, rs, res);
				} else if (doctype.equals("BOOK BANK")) {
					counterbeanobject.setBbeligible(rs.getInt("bbelg"));
					member_period = rs.getInt("bbper");
					counterbeanobject.setBbperiod(member_period);
					//DAYSCAL(c_bbper, st, rs, res);

				} else if (doctype.equals("NON BOOK")) {

					counterbeanobject.setNbeligible(rs.getInt("nbelg"));
					member_period = rs.getInt("nbper");
					counterbeanobject.setNbperiod(member_period);
					//DAYSCAL(c_nbper, st, rs, res);

				} else if (doctype.equals("JOURNAL")) {

					counterbeanobject.setJeligible(rs.getInt("jelg"));
					member_period = rs.getInt("jlper");
					counterbeanobject.setJperiod(member_period);
					//DAYSCAL(c_jlper, st, rs, res);

				} else if (doctype.equals("BACK VOLUME")) {

					counterbeanobject.setBveligible(rs.getInt("bvelg"));
					member_period = rs.getInt("bvper");
					counterbeanobject.setBvperiod(member_period);
					//DAYSCAL(c_bvper, st, rs, res);

				} else if (doctype.equals("THESIS")) {

					counterbeanobject.setTeligible(rs.getInt("telg"));
					member_period = rs.getInt("tper");
					counterbeanobject.setTperiod(member_period);
					//DAYSCAL(c_tper, st, rs, res);

				} else if (doctype.equals("STANDARD")) {

					counterbeanobject.setSeligible(rs.getInt("selg"));
					member_period = rs.getInt("sper");
					counterbeanobject.setSperiod(member_period);
					//DAYSCAL(c_sper, st, rs, res);

				} else if (doctype.equals("PROCEEDING")) {

					counterbeanobject.setPeligible(rs.getInt("pelg"));
					member_period = rs.getInt("pper");
					counterbeanobject.setPperiod(member_period);
					//DAYSCAL(c_pper, st, rs, res);

				} else if (doctype.equals("REPORT")) {

					counterbeanobject.setReligible(rs.getInt("relg"));
					member_period = rs.getInt("rper");
					counterbeanobject.setRperiod(member_period);
					//DAYSCAL(c_rper, st, rs, res);
				} else {
					String STOP = "STOP";
				}

				response.print("sp");

			} else if (status.equals("V3")) {

				counterbeanobject.setGeligible(rs.getInt("gelg"));
				member_period = rs.getInt("gper");
				counterbeanobject.setGperiod(member_period);
				//DAYSCAL(c3_gper, st, rs, res);

				if (doctype.equals("BOOK")) {
					counterbeanobject.setBbeligible(rs.getInt("belg"));
					member_period = rs.getInt("bper");
					counterbeanobject.setBbperiod(member_period);
					//DAYSCAL(c3_bper, st, rs, res);
				} else if (doctype.equals("BOOK BANK")) {
					counterbeanobject.setBbeligible(rs.getInt("bbelg"));
					member_period = rs.getInt("bbper");
					counterbeanobject.setBbperiod(member_period);
					//DAYSCAL(c3_bbper, st, rs, res);

				} else if (doctype.equals("NON BOOK")) {

					counterbeanobject.setNbeligible(rs.getInt("nbelg"));
					member_period = rs.getInt("nbper");
					counterbeanobject.setNbperiod(member_period);
					//DAYSCAL(c3_nbper, st, rs, res);

				} else if (doctype.equals("JOURNAL")) {

					counterbeanobject.setJeligible(rs.getInt("jelg"));
					member_period = rs.getInt("jlper");
					counterbeanobject.setJperiod(member_period);
					//DAYSCAL(c3_jlper, st, rs, res);

				} else if (doctype.equals("BACK VOLUME")) {

					counterbeanobject.setBveligible(rs.getInt("bvelg"));
					member_period = rs.getInt("bvper");
					counterbeanobject.setBvperiod(member_period);
					//DAYSCAL(c3_bvper, st, rs, res);

				} else if (doctype.equals("THESIS")) {

					counterbeanobject.setTeligible(rs.getInt("telg"));
					member_period = rs.getInt("tper");
					counterbeanobject.setTperiod(member_period);
					//DAYSCAL(c3_tper, st, rs, res);

				} else if (doctype.equals("STANDARD")) {

					counterbeanobject.setSeligible(rs.getInt("selg"));
					member_period = rs.getInt("sper");
					counterbeanobject.setSperiod(member_period);
					//DAYSCAL(c3_sper, st, rs, res);

				} else if (doctype.equals("PROCEEDING")) {

					counterbeanobject.setPeligible(rs.getInt("pelg"));
					member_period = rs.getInt("pper");
					counterbeanobject.setPperiod(member_period);
					//DAYSCAL(c3_pper, st, rs, res);

				} else if (doctype.equals("REPORT")) {

					counterbeanobject.setReligible(rs.getInt("relg"));
					member_period = rs.getInt("rper");
					counterbeanobject.setRperiod(member_period);
					//DAYSCAL(c3_rper, st, rs, res);
				} else {
					String STOP_WORK = "_WORKSTOP";
				}

				response.print("g&s");
			}
			counterbeanobject.setCountperiod(member_period);
			methods.DAYSCAL(member_period, con);

		}

	}
	
	
	
	public void ISSUEDETAILS(String ssuemcode,String ssueaccno,java.sql.Statement st,java.sql.ResultSet rs)throws SQLException{
		   try{
		   String md=ssuemcode;
		   String issmemcode="select member_code from issue_mas where access_no='"+ssueaccno+"'";
		   rs=st.executeQuery(issmemcode);
		     if(rs.next()){md=rs.getString("member_code");}
			 
			
			String sql="select * from issuedbooks where member_code ilike '"+md+"'";
			rs=st.executeQuery(sql);
          while(rs.next()){					
	String a1=rs.getString("Access_no");
	String a2=rs.getString("title");
	String a3=rs.getString("author_name");
	String a4=rs.getString("Availability");
	String a5=rs.getString("Member_Code");
	String a6=Security.getdate(rs.getString("issue_date"));
	String a7=Security.getdate(rs.getString("due_date"));
	String a8=rs.getString("Issue_Type");
	String a9=rs.getString("Staff_Code");
	String a10=rs.getString("Doc_Type");
	DEATILS.add(a1);
	DEATILS.add(a2);
	DEATILS.add(a3);
	DEATILS.add(a4);
	DEATILS.add(a5);
	DEATILS.add(a6);
	DEATILS.add(a7);
	DEATILS.add(a8);
	DEATILS.add(a9);
	DEATILS.add(a10);
	}
    counterbeanobject.setAl(DEATILS);
	}catch(Exception e){}
	
}	

	/**
	 * @param bookissue_no
	 * @param out
	 * @throws SQLException
	 * @throws IOException
	 * @throws javax.servlet.ServletException
	 */
	public void BOOK_ISSUED(String bookissue_no, PrintWriter out)
		throws SQLException, IOException, javax.servlet.ServletException {

		Prest = con.prepareStatement(COUNTER_QUERY.SELECT_ISSUEMAS);
		Prest.setString(1, bookissue_no);
		rs = Prest.executeQuery();
		if (rs.next()) {
			String loadmemcode = rs.getString("member_code");
			String issuedate =
			Security.Counter_DateGet(rs.getDate("issue_date"));
			String duedate = Security.Counter_DateGet(rs.getDate("due_date"));
			String issuetype = rs.getString("issue_type");
			String doctype_Issue = rs.getString("doc_type");
			Groups = Integer.parseInt(rs.getString("group_code"));
			counterbeanobject.setCallduedate(duedate);
			counterbeanobject.setCallissdate(issuedate);
			methods.DAYSCALRETURN(duedate, Groups, con);
			methods.ISSUEDETAILSMEMBER(loadmemcode, con);
			methods.RESERVEDETAILS(bookissue_no, con);
			methods.bookcount_doctype(loadmemcode, out, con);
			methods.MEMBER_LOAD_ISSUE(loadmemcode, doctype_Issue, con);
			
		}

	}

	/**
	 * @param request
	 * @param response
	 * @param Groups
	 * @param session
	 * @param out
	 * @param Ddate
	 * @param Rdate
	 * @throws IOException
	 * @throws ParseException
	 * @throws SQLException
	 * @throws ServletException
	 */
	public void UPDATE_RENEW_MAS(
		HttpServletRequest request,
		HttpServletResponse response,
		int Groups,
		HttpSession session,
		PrintWriter out,
		String Ddate,
		String Rdate, Double fine)
		throws IOException, ParseException, SQLException, ServletException {

		int time = 0;
		int renew = 0;
		String sql =
			"select time_renew from renewal_time where access_no='"
				+ request.getParameter("accno")
				+ "' and member_code='"
				+ request.getParameter("mcode")
				+ "' ";
		rs = st.executeQuery(sql);
		if (rs.next()) {
			time = Integer.parseInt(rs.getString("time_renew"));
			time++;

		} else {
			time = 1;
			st.execute(
				"insert into renewal_time(member_code,access_no,group_code,doc_type,time_renew) values ('"
					+ request.getParameter("mcode")
					+ "','"
					+ request.getParameter("accno")
					+ "','"
					+ Groups
					+ "','"
					+ request.getParameter("doc")
					+ "','"
					+ time
					+ "')");
		}
		sql =
			"select renewal from group_mas where group_code='" + Groups + "' ";
		rs = st.executeQuery(sql);
		if (rs.next()) {
			renew = rs.getInt("renewal");
		}

		if (time > renew) {
	//response.sendRedirect(
			//	"/AutoLib/Counter/index.jsp?Message=nrenew&detils=ISSUEDEATILS");
			 getServletConfig().getServletContext().getRequestDispatcher("/Counter/index.jsp?flag=nrenew&mcode=membercode").forward(request, response);
		} else {

			st.execute(
				"update renewal_time set time_renew='"
					+ time
					+ "' where access_no= '"
					+ request.getParameter("accno")
					+ "'and member_code='"
					+ request.getParameter("mcode")
					+ "' ");

			Prest = con.prepareStatement(COUNTER_QUERY.SELECT_BOOKMAS);
			Prest.setString(1, request.getParameter("accno"));
			rs = Prest.executeQuery();
			if (rs.next()) {
				String avail = rs.getString("availability");
				if (avail.equals("ISSUED")) {
					rs.absolute(1);
					st.execute(
						"update book_mas set availability='ISSUED' where access_no='"
							+ request.getParameter("accno")
							+ "'");
					out.print("due date"+Ddate);
					out.print("return date"+Rdate);
					methods.RETURN_UPDATE_RENEW(request,session,out,Ddate,Rdate,con,fine); 

				}

			}
		}

	}
	public void UPDATE_RENEW_MAS_NOFINE(
			HttpServletRequest request,
			HttpServletResponse response,
			int Groups,
			HttpSession session,
			PrintWriter out,
			String Ddate,
			String Rdate)
			throws IOException, ParseException, SQLException, ServletException {

			int time = 0;
			int renew = 0;
			String sql =
				"select time_renew from renewal_time where access_no='"
					+ request.getParameter("accno")
					+ "' and member_code='"
					+ request.getParameter("mcode")
					+ "' ";
			rs = st.executeQuery(sql);
			if (rs.next()) {
				time = Integer.parseInt(rs.getString("time_renew"));
				time++;

			} else {
				time = 1;
				st.execute(
					"insert into renewal_time(member_code,access_no,group_code,doc_type,time_renew) values ('"
						+ request.getParameter("mcode")
						+ "','"
						+ request.getParameter("accno")
						+ "','"
						+ Groups
						+ "','"
						+ request.getParameter("doc")
						+ "','"
						+ time
						+ "')");
			}
			sql =
				"select renewal from group_mas where group_code='" + Groups + "' ";
			rs = st.executeQuery(sql);
			if (rs.next()) {
				renew = rs.getInt("renewal");
			}

			if (time > renew) {

				 getServletConfig().getServletContext().getRequestDispatcher("/Counter/index.jsp?Message=nrenew&mcode=membercode").forward(request, response);
			} else {

				st.execute(
					"update renewal_time set time_renew='"
						+ time
						+ "' where access_no= '"
						+ request.getParameter("accno")
						+ "'and member_code='"
						+ request.getParameter("mcode")
						+ "' ");

				Prest = con.prepareStatement(COUNTER_QUERY.SELECT_BOOKMAS);
				Prest.setString(1, request.getParameter("accno"));
				rs = Prest.executeQuery();
				if (rs.next()) {
					String avail = rs.getString("availability");
					if (avail.equals("ISSUED")) {
						rs.absolute(1);
						st.execute(
							"update book_mas set availability='ISSUED' where access_no='"
								+ request.getParameter("accno")
								+ "'");

						methods.RETURN_UPDATE_RENEW_NOFINE(request,session,out,Ddate,Rdate,con); 

					}

				}
			}

		}

}