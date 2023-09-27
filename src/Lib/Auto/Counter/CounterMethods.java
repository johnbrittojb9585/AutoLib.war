/*
 * Created on Nov 21, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package Lib.Auto.Counter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import Common.Security_Counter;




/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */

public class CounterMethods implements COUNTER_QUERY {

	CounterMemberBean counterbeanobject = new CounterMemberBean();

	public CounterMethods() {
		super();
		
	}

	/**
	 * @param ssuemcode
	 * @param con
	 * @throws SQLException
	 * @throws ServletException
	 */
	
	
	public void ISSUEDETAILSMEMBER(String ssuemcode, java.sql.Connection con)
	throws SQLException, ServletException {
		

	java.sql.PreparedStatement Prest = null;
	java.sql.ResultSet rs = null;
	java.sql.Statement st = null;
	ArrayList DEATILS = new ArrayList();
	

	try {
		Prest = con.prepareStatement(COUNTER_QUERY.SELECT_ISSUE_MEMBER);
		Prest.setString(1, ssuemcode);
		rs = Prest.executeQuery();
		while (rs.next()) {
			DEATILS.add(rs.getString("Access_no"));
			DEATILS.add(rs.getString("title"));
			DEATILS.add(rs.getString("author_name"));
			DEATILS.add(rs.getString("Availability"));
			DEATILS.add(rs.getString("Member_Code"));
			DEATILS.add(Security_Counter.Counter_DateGet(rs.getDate("issue_date")));
			DEATILS.add(Security_Counter.Counter_DateGet(rs.getDate("due_date")));
			DEATILS.add(rs.getString("Issue_Type"));
			DEATILS.add(rs.getString("Staff_Code"));
			DEATILS.add(rs.getString("Doc_Type"));
			
			
				
		}

		counterbeanobject.setCounterList(DEATILS);

	} catch (SQLException e) {
		throw new ServletException(e);
	} finally {
		Security_Counter.closePreparedStatement(Prest);
		Security_Counter.closeResultSet(rs);
	}

}

	/**
	 * @param mctcode
	 * @param out
	 * @param con
	 * @throws SQLException
	 * @throws IOException
	 * @throws ServletException
	 */
	//--------------------------------------------------------------------------------------//

	public void bookcount_doctype(
		String mctcode,
		PrintWriter out,
		Connection con)
		throws SQLException, IOException, ServletException {
		java.sql.PreparedStatement Prest = null;
		java.sql.ResultSet rs = null;
		java.sql.Statement st = null;

		try {

			Prest = con.prepareStatement(COUNTER_QUERY.SELECT_BOOKCOUNT);
			Prest.setString(1, mctcode);
			Prest.setString(2, "BOOK");
			rs = Prest.executeQuery();

			if (rs.next()) {
				counterbeanobject.setBissued(rs.getInt("cnt"));
			}

			Prest = con.prepareStatement(COUNTER_QUERY.SELECT_BOOKCOUNT);
			Prest.setString(1, mctcode);
			Prest.setString(2, "NON BOOK");
			rs = Prest.executeQuery();
			if (rs.next()) {
				counterbeanobject.setNbissued(rs.getInt("cnt"));
			}

			Prest = con.prepareStatement(COUNTER_QUERY.SELECT_BOOKCOUNT);
			Prest.setString(1, mctcode);
			Prest.setString(2, "BOOK BANK");
			rs = Prest.executeQuery();
			if (rs.next()) {
				counterbeanobject.setBbissued(rs.getInt("cnt"));
			}

			Prest = con.prepareStatement(COUNTER_QUERY.SELECT_BOOKCOUNT);
			Prest.setString(1, mctcode);
			Prest.setString(2, "STANDARD");
			rs = Prest.executeQuery();
			if (rs.next()) {
				counterbeanobject.setSissued(rs.getInt("cnt"));
			}

			Prest = con.prepareStatement(COUNTER_QUERY.SELECT_BOOKCOUNT);
			Prest.setString(1, mctcode);
			Prest.setString(2, "BACK VOLUME");
			rs = Prest.executeQuery();
			if (rs.next()) {
				counterbeanobject.setBvissued(rs.getInt("cnt"));
			}

			Prest = con.prepareStatement(COUNTER_QUERY.SELECT_BOOKCOUNT);
			Prest.setString(1, mctcode);
			Prest.setString(2, "REPORT");
			rs = Prest.executeQuery();
			if (rs.next()) {
				counterbeanobject.setRissued(rs.getInt("cnt"));
			}

			Prest = con.prepareStatement(COUNTER_QUERY.SELECT_BOOKCOUNT);
			Prest.setString(1, mctcode);
			Prest.setString(2, "THESIS");
			rs = Prest.executeQuery();
			if (rs.next()) {
				counterbeanobject.setTissued(rs.getInt("cnt"));
			}

			Prest = con.prepareStatement(COUNTER_QUERY.SELECT_BOOKCOUNT);
			Prest.setString(1, mctcode);
			Prest.setString(2, "PROCEEDING");
			rs = Prest.executeQuery();
			if (rs.next()) {
				counterbeanobject.setPissued(rs.getInt("cnt"));
			}

			Prest = con.prepareStatement(COUNTER_QUERY.SELECT_BOOKCOUNT);
			Prest.setString(1, mctcode);
			Prest.setString(2, "JOURNAL");
			rs = Prest.executeQuery();
			if (rs.next()) {
				counterbeanobject.setJissued(rs.getInt("cnt"));
			}

		} catch (SQLException e) {
			throw new ServletException(e);
		} finally {
			Security_Counter.closePreparedStatement(Prest);
			Security_Counter.closeResultSet(rs);
		}

	}

	/**
	 * @param loadmemcode
	 * @param doctype
	 * @param con
	 * @throws SQLException
	 * @throws IOException
	 * @throws javax.servlet.ServletException
	 */
	//--------------------------------------------------------------------------------------------//

	public void MEMBER_LOAD_ISSUE(
		String loadmemcode,
		String doctype,
		Connection con)
		throws SQLException, IOException, javax.servlet.ServletException {
		int period_all = 0;
		String status = "";
		java.sql.PreparedStatement Prest = null;
		java.sql.ResultSet rs = null;
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
				Security_Counter.Counter_DateGet(rs.getDate("expiry_date")));
			counterbeanobject.setImg(rs.getString("photo"));
			counterbeanobject.setCourse(rs.getString("course_name"));
			counterbeanobject.setYear(rs.getString("cyear"));
			//  Groups = rs.getInt("group_code");
			status = rs.getString("status");
			if (status.equals("V1")) {

				counterbeanobject.setGeligible(rs.getInt("gelg"));
				period_all = rs.getInt("gper");
				counterbeanobject.setGperiod(period_all);
				//DAYSCAL(c1_gper, st, rs, res);
			} else if (status.equals("V2")) {
				if (doctype.equals("BOOK")) {
					counterbeanobject.setBbeligible(rs.getInt("belg"));
					period_all = rs.getInt("bper");
					counterbeanobject.setBbperiod(period_all);
					//DAYSCAL(c_bper, st, rs, res);
				} else if (doctype.equals("BOOK BANK")) {
					counterbeanobject.setBbeligible(rs.getInt("bbelg"));
					period_all = rs.getInt("bbper");
					counterbeanobject.setBbperiod(period_all);
					//DAYSCAL(c_bbper, st, rs, res);

				} else if (doctype.equals("NON BOOK")) {

					counterbeanobject.setNbeligible(rs.getInt("nbelg"));
					period_all = rs.getInt("nbper");
					counterbeanobject.setNbperiod(period_all);
					//DAYSCAL(c_nbper, st, rs, res);

				} else if (doctype.equals("JOURNAL")) {

					counterbeanobject.setJeligible(rs.getInt("jelg"));
					period_all = rs.getInt("jlper");
					counterbeanobject.setJperiod(period_all);
					//DAYSCAL(c_jlper, st, rs, res);

				} else if (doctype.equals("BACK VOLUME")) {

					counterbeanobject.setBveligible(rs.getInt("bvelg"));
					period_all = rs.getInt("bvper");
					counterbeanobject.setBvperiod(period_all);
					//DAYSCAL(c_bvper, st, rs, res);

				} else if (doctype.equals("THESIS")) {

					counterbeanobject.setTeligible(rs.getInt("telg"));
					period_all = rs.getInt("tper");
					counterbeanobject.setTperiod(period_all);
					//DAYSCAL(c_tper, st, rs, res);

				} else if (doctype.equals("STANDARD")) {

					counterbeanobject.setSeligible(rs.getInt("selg"));
					period_all = rs.getInt("sper");
					counterbeanobject.setSperiod(period_all);
					//DAYSCAL(c_sper, st, rs, res);

				} else if (doctype.equals("PROCEEDING")) {

					counterbeanobject.setPeligible(rs.getInt("pelg"));
					period_all = rs.getInt("pper");
					counterbeanobject.setPperiod(period_all);
					//DAYSCAL(c_pper, st, rs, res);

				} else if (doctype.equals("REPORT")) {

					counterbeanobject.setReligible(rs.getInt("relg"));
					period_all = rs.getInt("rper");
					counterbeanobject.setRperiod(period_all);
					//DAYSCAL(c_rper, st, rs, res);
				} else {
					String STOP = "STOP";
				}

			} else if (status.equals("V3")) {

				counterbeanobject.setGeligible(rs.getInt("gelg"));
				period_all = rs.getInt("gper");
				counterbeanobject.setGperiod(period_all);
				//DAYSCAL(c3_gper, st, rs, res);

				if (doctype.equals("BOOK")) {
					counterbeanobject.setBbeligible(rs.getInt("belg"));
					period_all = rs.getInt("bper");
					counterbeanobject.setBbperiod(period_all);
					///DAYSCAL(c3_bper, st, rs, res);
				} else if (doctype.equals("BOOK BANK")) {
					counterbeanobject.setBbeligible(rs.getInt("bbelg"));
					period_all = rs.getInt("bbper");
					counterbeanobject.setBbperiod(period_all);
					//DAYSCAL(c3_bbper, st, rs, res);

				} else if (doctype.equals("NON BOOK")) {

					counterbeanobject.setNbeligible(rs.getInt("nbelg"));
					period_all = rs.getInt("nbper");
					counterbeanobject.setNbperiod(period_all);
					//DAYSCAL(c3_nbper, st, rs, res);

				} else if (doctype.equals("JOURNAL")) {

					counterbeanobject.setJeligible(rs.getInt("jelg"));
					period_all = rs.getInt("jlper");
					counterbeanobject.setJperiod(period_all);
					//DAYSCAL(c3_jlper, st, rs, res);

				} else if (doctype.equals("BACK VOLUME")) {

					counterbeanobject.setBveligible(rs.getInt("bvelg"));
					period_all = rs.getInt("bvper");
					counterbeanobject.setBvperiod(period_all);
					//DAYSCAL(c3_bvper, st, rs, res);

				} else if (doctype.equals("THESIS")) {

					counterbeanobject.setTeligible(rs.getInt("telg"));
					period_all = rs.getInt("tper");
					counterbeanobject.setTperiod(period_all);
					//DAYSCAL(c3_tper, st, rs, res);

				} else if (doctype.equals("STANDARD")) {

					counterbeanobject.setSeligible(rs.getInt("selg"));
					period_all = rs.getInt("sper");
					counterbeanobject.setSperiod(period_all);
					//DAYSCAL(c3_sper, st, rs, res);

				} else if (doctype.equals("PROCEEDING")) {

					counterbeanobject.setPeligible(rs.getInt("pelg"));
					period_all = rs.getInt("pper");
					counterbeanobject.setPperiod(period_all);
					//DAYSCAL(c3_pper, st, rs, res);

				} else if (doctype.equals("REPORT")) {

					counterbeanobject.setReligible(rs.getInt("relg"));
					period_all = rs.getInt("rper");
					counterbeanobject.setRperiod(period_all);
					//DAYSCAL(c3_rper, st, rs, res);
				} else {
					String STOP_WORK = "_WORKSTOP";
				}

			}

			DAYSCAL(period_all, con);

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

		}

	}

	/**
	 * @param period
	 * @param con
	 * @throws SQLException
	 * @throws IOException
	 */
	//----------------------------------------------------------------------------------------//
	public void DAYSCAL(int period, Connection con)
		throws SQLException, IOException {
		java.sql.PreparedStatement Prest = null;
		java.sql.ResultSet rs = null;

		String sql = "";
		int no = 1;
		int a;
		java.util.Date Ddate = new Date();
		a = 0;
		//a = period * 86400;
		a=period;
		/* Prest =
			con.prepareStatement(
				"select timestamp '"
					+ Security_Counter.Counter_DateText()
					+ "' + '"
					+ a
					+ "' as days"); */
		Prest =
			con.prepareStatement(
				"select adddate('"+Security_Counter.Counter_DateText()+"', '"+a+"') as days");
				
		rs = Prest.executeQuery();
		if (rs.next()) {
			Ddate = rs.getDate("days");
			//Ddate=Ddate.substring(0,Ddate.length()-9);

		}
		boolean Rflag = true;
		while (Rflag == true) {

			/* Prest =
				con.prepareStatement(
					"select extract(dow from timestamp '"
						+ Ddate
						+ "') as days"); */
			Prest=con.prepareStatement("select date_format('"+Ddate+"','%w') as days");
			rs = Prest.executeQuery();

			if (rs.next()) {
				String day = rs.getString("days");
				int name_of_day = Integer.parseInt(day);
				if (name_of_day == 0) {
					//a = 86400;
					a=1;

					/* Prest =
						con.prepareStatement(
							"select timestamp '"
								+ Ddate
								+ "' + '"
								+ a
								+ "' as days"); */
					Prest =
						con.prepareStatement(
							"select adddate('"+Ddate+"', '"+a+"') as days");
					rs = Prest.executeQuery();
					if (rs.next()) {
						Ddate = rs.getDate("days");
						//Ddate=Ddate.substring(0,Ddate.length()-9);

					}
				}

				if (name_of_day == 6) {
					//a = 2 * 86400;
					a=2;
					/* Prest =
						con.prepareStatement(
							"select timestamp '"
								+ Ddate
								+ "' + '"
								+ a
								+ "' as days"); */
					Prest =
						con.prepareStatement(
							"select adddate('"+Ddate+"', '"+a+"') as days");
					
					rs = Prest.executeQuery();
					if (rs.next()) {
						Ddate = rs.getDate("days");
						//Ddate=Ddate.substring(0,Ddate.length()-9);

					}
				}

				Prest =
					con.prepareStatement(
						"select Leave_date  from holiday_mas where leave_date='"
							+ Ddate
							+ "'");
				rs = Prest.executeQuery();
				if (rs.next()) {
					//a = 86400;
					a=1;
					/* Prest =
						con.prepareStatement(
							"select timestamp '"
								+ Ddate
								+ "' + '"
								+ a
								+ "' as days"); */
					Prest =
						con.prepareStatement(
							"select adddate('"+Ddate+"', '"+a+"') as days");
					
					rs = Prest.executeQuery();

					if (rs.next()) {
						Ddate = rs.getDate("days");
						//Ddate=Ddate.substring(0,Ddate.length()-9);
						Rflag = true;

					}

				} else {
					Rflag = false;
				}

			}

		}

		counterbeanobject.setCalldate(Security_Counter.Counter_DateSet(Ddate));

	}
	/**
	 * @param request
	 * @param session
	 * @param con
	 * @throws SQLException
	 * @throws ParseException
	 */
	//----------------------------------------------------------------------------------------//

	public void RETURN_UPDATE(
		HttpServletRequest request,
		HttpSession session,
		Connection con)
		throws SQLException, ParseException {
		java.sql.PreparedStatement Prest = null;
		java.sql.ResultSet rs = null;
		Prest = con.prepareStatement(COUNTER_QUERY.INSERT_HISTORY);
		Prest.setString(1, request.getParameter("mcode"));
		Prest.setString(2, request.getParameter("accno"));
		Prest.setString(3, Security_Counter.TextDate_Insert(request.getParameter("idate")));
		Prest.setString(4, Security_Counter.TextDate_Insert(request.getParameter("ddate")));
		Prest.setString(5, Security_Counter.TextDate_Insert(request.getParameter("rdate")));
		Prest.setDouble(6, 0.0);
		Prest.setObject(7, session.getAttribute("UserID"));
		Prest.setString(8, request.getParameter("doc"));
		
		Prest.executeUpdate();
		Prest = con.prepareStatement(COUNTER_QUERY.DELETE_ISSUEMAS);
		Prest.setString(1, request.getParameter("mcode"));
		Prest.setString(2, request.getParameter("accno"));
		Prest.executeUpdate();

	}
	public void RETURN_UPDATE_FINE(
			HttpServletRequest request,
			HttpSession session,
			Connection con, Double fine)
			throws SQLException, ParseException {
			java.sql.PreparedStatement Prest = null;
			java.sql.ResultSet rs = null;
			Prest = con.prepareStatement(COUNTER_QUERY.INSERT_HISTORY);
			Prest.setString(1, request.getParameter("mcode"));
			Prest.setString(2, request.getParameter("accno"));
			Prest.setString(3, Security_Counter.TextDate_Insert(request.getParameter("idate")));
			Prest.setString(4, Security_Counter.TextDate_Insert(request.getParameter("ddate")));
			Prest.setString(5, Security_Counter.TextDate_Insert(request.getParameter("rdate")));
			Prest.setDouble(6, fine.doubleValue());
			Prest.setObject(7, session.getAttribute("UserID"));
			Prest.setString(8, request.getParameter("doc"));
			Prest.executeUpdate();
			Prest = con.prepareStatement(COUNTER_QUERY.DELETE_ISSUEMAS);
			Prest.setString(1, request.getParameter("mcode"));
			Prest.setString(2, request.getParameter("accno"));
			Prest.executeUpdate();

		}



	/**
	 * @param request
	 * @param session
	 * @param out
	 * @param Rdate
	 * @param Ddate
	 * @param con
	 * @throws SQLException
	 * @throws ParseException
	 */
	//-----------------------------------------------------------------------------//
	public void RETURN_UPDATE_RENEW(
		HttpServletRequest request,
		HttpSession session,
		PrintWriter out,
		String Rdate,
		String Ddate,
		Connection con,Double renewFine)
		throws SQLException, ParseException {
		java.sql.PreparedStatement Prest = null;
		java.sql.Statement st=null;
		java.sql.ResultSet rs = null;
		double renewfine=renewFine.doubleValue();
		Prest = con.prepareStatement(COUNTER_QUERY.INSERT_HISTORY);
		Prest.setString(1, request.getParameter("mcode"));
		Prest.setString(2, request.getParameter("accno"));
		Prest.setString(3, Security_Counter.TextDate_Insert(request.getParameter("idate")));
		Prest.setString(4, Rdate);
		Prest.setString(5, Ddate);
		Prest.setDouble(6, renewfine);
		Prest.setObject(7, session.getAttribute("UserID"));
		Prest.setString(8, request.getParameter("doc"));
		Prest.executeUpdate();

		//String UPDATE_ISSUEMAS="update issue_mas set due_date=adddate(sysdate(),datediff(due_date,issue_date)),issue_date=sysdate(),issue_type='RENEW' where member_code='"+request.getParameter("mcode")+"' and access_no='"+request.getParameter("accno")+"'";
		//out.print(UPDATE_ISSUEMAS);
		//st.executeUpdate(UPDATE_ISSUEMAS);
		Prest = con.prepareStatement(COUNTER_QUERY.UPDATE_ISSUEMAS);
		Prest.setString(1, Rdate);
		Prest.setString(2, Ddate);
		Prest.setString(3, "RENEW");
		Prest.setObject(4, session.getAttribute("UserID")); // For Staff Code
		Prest.setString(5, request.getParameter("mcode"));
		Prest.setString(6, request.getParameter("accno")); 
		
		Prest.executeUpdate(); 

	}
	public void RETURN_UPDATE_RENEW_NOFINE(
			HttpServletRequest request,
			HttpSession session,
			PrintWriter out,
			String Rdate,
			String Ddate,
			Connection con)
			throws SQLException, ParseException {
			java.sql.PreparedStatement Prest = null;
			java.sql.Statement st=null;
			java.sql.ResultSet rs = null;

			
			Prest = con.prepareStatement(COUNTER_QUERY.UPDATE_ISSUEMAS);
			Prest.setString(1, Rdate);
			Prest.setString(2, Ddate);
			Prest.setString(3, "RENEW");
			Prest.setObject(4, session.getAttribute("UserID")); // For Staff Code
			Prest.setString(5, request.getParameter("mcode"));
			Prest.setString(6, request.getParameter("accno")); 
			Prest.executeUpdate(); 
			
			Prest = con.prepareStatement(COUNTER_QUERY.INSERT_HISTORY);
			Prest.setString(1, request.getParameter("mcode"));
			Prest.setString(2, request.getParameter("accno"));
			Prest.setString(3, Security_Counter.TextDate_Insert(request.getParameter("idate")));
			Prest.setString(4, Rdate);
			Prest.setString(5, Ddate);
			Prest.setDouble(6, 0.0);
			Prest.setObject(7, session.getAttribute("UserID"));
			Prest.setString(8, request.getParameter("doc"));
			Prest.executeUpdate();

		}
	/**
	 * @param gd
	 * @param Holiday
	 * @param con
	 * @throws SQLException
	 */
	//--------------------------------------------------------------------------------------//
	public void FINE_CALL(int gd, int Holiday, Connection con)
		throws SQLException {
		java.sql.PreparedStatement Prest = null;
		java.sql.ResultSet rs = null;
		double temp = 0;
		int cal = 0;
		ArrayList DEATILS = new ArrayList();
		Prest = con.prepareStatement(COUNTER_QUERY.SELECT_FINEMAS);
		Prest.setInt(1, gd);
		rs = Prest.executeQuery();
		while (rs.next() && (Holiday > 0)) {
			int period = rs.getInt("Fine_Period");
			float amt = rs.getFloat("Fine_Amount");
			String type = rs.getString("period_type");

			if (type.equals("DAILY")) {
				if (Holiday < period) {
					temp = temp + (Holiday * amt);
					Holiday = Holiday - period;

				} else if (Holiday >= period) {
					temp = temp + (period * amt);

					Holiday = Holiday - period;

				}
			} else if (type.equals("WEEKLY")) {
				temp = temp + amt;
				if (Holiday <= (period * 7))
					temp = temp;
				else if (cal > (period * 7)) {
					cal = cal - (period * 7);
				}
			} else if (type.equals("MONTHLY")) {
				temp = temp + amt;
				if (cal <= (period * 30))
					temp = temp;
				else if (cal > (period * 30)) {
					cal = cal - (period * 30);
				}
			} else if (type.equals("YEARLY")) {
				temp = temp + amt;
				if (cal <= (period * 365))
					temp = temp;
				else if (cal > (period * 365)) {
					cal = cal - (period * 365);
				}
			}

		}

		counterbeanobject.setTemp(new Double(temp));
		

	}
	/**
	 * @param DueDate
	 * @param Groups
	 * @param con
	 * @throws SQLException
	 */
	//--------------------------------------------------------------------------------------//

	public void DAYSCALRETURN(String DueDate, int Groups, Connection con)
		throws SQLException {
		java.sql.PreparedStatement Prest = null;
		java.sql.ResultSet rs = null;

		String no_of_days = "";
		String sql = "";
		int no = 1;
		String ddate = "";
		String Rdate = "";
		String Ddate = "";
		int Holiday_Check = 0, cal = 0, Holiday = 0;
		double temp = 0;
		ArrayList DEATILS = new ArrayList();

		java.util.StringTokenizer stz_du =
			new java.util.StringTokenizer(DueDate, "-");
		int diy = Integer.parseInt(stz_du.nextToken());
		int dim = Integer.parseInt(stz_du.nextToken());
		int did = Integer.parseInt(stz_du.nextToken());
		ddate = diy + "-" + dim + "-" + did;

	
		Prest=con.prepareStatement("select datediff('"+Security_Counter.Counter_DateText()+"','"+ddate+"') as no_of_days");
		
		rs = Prest.executeQuery();
	
		if (rs.next()) {
			no_of_days = rs.getString("no_of_days");
			
			
			int n = Integer.parseInt(no_of_days);
			
			if (n < 0) {

				counterbeanobject.setTemp(new Double(temp));
				
				

			} 
			Holiday=n;
			

			

		}

		FINE_CALL(Groups, Holiday, con);
	}

	/**
	 * @param ssueaccno
	 * @param con
	 * @throws SQLException
	 */
	//-----------------------------------------------------------------------------------------//
	public void RESERVEDETAILS(String ssueaccno, Connection con)
		throws SQLException {
		ArrayList RCRESERVE = new ArrayList();
		java.sql.PreparedStatement Prest = null;
		java.sql.ResultSet rs = null;

		try {

			Prest =
				con.prepareStatement(
					"select * from member_reserve_view where access_no='"
						+ ssueaccno
						+ "'");
			rs = Prest.executeQuery();
			while (rs.next()) {
				String r1 = rs.getString("ID");
				String r2 = rs.getString("Member_Code");
				String r3 = rs.getString("Access_No");
				String r4 = rs.getString("Doc_Type");
				String r5 = Security_Counter.Counter_DateGet(rs.getDate("Res_Date"));
				String r6 = rs.getString("Member_Name");

				RCRESERVE.add(r1);
				RCRESERVE.add(r2);
				RCRESERVE.add(r3);
				RCRESERVE.add(r4);
				RCRESERVE.add(r5);
				RCRESERVE.add(r6);

			}
			counterbeanobject.setCunterList_RESERVE(RCRESERVE);
		} catch (Exception e) {
		}

	}

}
