package Common.businessutil.reports;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.SQLQuery;

import Common.DataQuery;
import Lib.Auto.AccessionRegister.accessbean;
import Lib.Auto.Book.bookbean;
import Lib.Auto.Budget.BudgetBean;
import Lib.Auto.Course.CourseBean;
import Lib.Auto.Department.DepartmentBean;
import Lib.Auto.Designation.DesignationBean;
import Lib.Auto.Group.GroupBean;
import Lib.Auto.JnlIssueReport.JnlIssueReportBean;
import Lib.Auto.Journal.journalbean;
import Lib.Auto.JournalList.JournalListBean;
import Lib.Auto.Member.MemberBean;
import Lib.Auto.MemberReport.MemberReportBean;
import Lib.Auto.MemberTransfer.MemberTransRefBean;
import Lib.Auto.PubSup.PubSupBean;
import Lib.Auto.Report.reportbean;
import Lib.Auto.Subject.subjectbean;

import com.library.autolib.portal.dbconnectionutil.BaseDBConnection;

public class ReportDaoImpl extends BaseDBConnection implements ReportDao {
	private static Logger log4jLogger = Logger.getLogger(ReportDaoImpl.class);

	java.sql.Connection con = null;
	java.sql.PreparedStatement Prest = null;
	java.sql.ResultSet rs = null;
	java.sql.Statement st = null;

	// For Report Connection
	java.sql.Connection con1 = null;

	public java.sql.Connection findDBConnect() {
		try {
			// String url="jdbc:mysql://localhost:3306/autolib";
			// Class.forName("com.mysql.jdbc.Driver").newInstance();
			// con1=DriverManager.getConnection(url,"root","admin");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return con1;
	}

	public List findMemberSearch(MemberReportBean newBean) {
		log4jLogger.info("start===========findMemberSearch=====");
		MemberReportBean newmemberBean = null;
		List finalResults = null;

		try {
			con = getSession().connection();
			st = con.createStatement();

			if (newBean.getMname() == "") {

				rs = st.executeQuery(DataQuery.MEMBERSEARCH_STRING
						+ " order by member_name,member_code");
			} else {

				rs = st.executeQuery(DataQuery.MEMBERSEARCH_STRING_LIKE
						+ newBean.getMname()
						+ "%' order by member_name,member_code");

			}

			finalResults = new ArrayList();

			while (rs.next()) {
				newmemberBean = new MemberReportBean();
				newmemberBean.setMcode(rs.getString("member_code"));
				newmemberBean.setMname(rs.getString("member_name"));
				finalResults.add(newmemberBean);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
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

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return finalResults;
	}

	public List findDeptSearch(MemberReportBean newBean) {
		log4jLogger.info("start===========findDeptSearch=====");
		MemberReportBean newmemberBean = null;
		List finalResults = null;

		try {
			con = getSession().connection();
			st = con.createStatement();

			if (newBean.getMname() == "") {

				rs = st.executeQuery(DataQuery.DEPTSEARCH_NAME);
			} else {

				rs = st.executeQuery(DataQuery.DEPTSEARCH_NAME_LIKE
						+ newBean.getMname() + "%' order by dept_name");

			}

			finalResults = new ArrayList();

			while (rs.next()) {
				newmemberBean = new MemberReportBean();
				newmemberBean.setDcode(rs.getInt("dept_code"));
				newmemberBean.setDname(rs.getString("dept_name"));
				newmemberBean.setDdesc(rs.getString("short_desc"));
				finalResults.add(newmemberBean);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
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

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return finalResults;
	}

	public List findGroupSearch(MemberReportBean newBean) {
		log4jLogger.info("start===========findGroupSearch=====");
		MemberReportBean newmemberBean = null;
		List finalResults = null;

		try {
			con = getSession().connection();
			st = con.createStatement();

			if (newBean.getMname() == "") {

				rs = st.executeQuery(DataQuery.GROUPSEARCH_NAME);
			} else {

				rs = st.executeQuery(DataQuery.GROUPSEARCH_NAME_LIKE
						+ newBean.getMname() + "%' order by group_name");

			}

			finalResults = new ArrayList();

			while (rs.next()) {
				newmemberBean = new MemberReportBean();
				newmemberBean.setGcode(rs.getInt("group_code"));
				newmemberBean.setGname(rs.getString("group_name"));
				newmemberBean.setRemarks(rs.getString("remarks"));
				finalResults.add(newmemberBean);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
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

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return finalResults;
	}

	public List findCourseSearch(MemberReportBean newBean) {
		log4jLogger.info("start===========findCourseSearch=====");
		MemberReportBean newmemberBean = null;
		List finalResults = null;

		try {
			con = getSession().connection();
			st = con.createStatement();

			if (newBean.getMname() == "") {

				rs = st.executeQuery(DataQuery.COURSESEARCH_NAME);
			} else {

				rs = st.executeQuery(DataQuery.COURSESEARCH_NAME_LIKE
						+ newBean.getMname() + "%' order by course_name");

			}

			finalResults = new ArrayList();

			while (rs.next()) {
				newmemberBean = new MemberReportBean();
				newmemberBean.setCcode(rs.getInt("course_code"));
				newmemberBean.setCname(rs.getString("course_name"));
				newmemberBean.setCdesc(rs.getString("course_major"));
				finalResults.add(newmemberBean);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
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

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return finalResults;
	}

	// Journal List Report

	public List findPubJnlSearch(JournalListBean newBean) {
		log4jLogger.info("start===========findPubJnlSearch=====");
		JournalListBean newmemberBean = null;
		List finalResults = null;

		try {
			con = getSession().connection();
			st = con.createStatement();

			if (newBean.getDname() == "") {

				rs = st.executeQuery(DataQuery.PUBSUBSEARCH_STRING_PUB);
			} else {

				rs = st.executeQuery(DataQuery.PUBSUBSEARCH_STRING_LIKE_PUB
						+ newBean.getDname() + "%' order by sp_name");

			}

			finalResults = new ArrayList();

			while (rs.next()) {
				newmemberBean = new JournalListBean();
				newmemberBean.setDcode(rs.getInt("sp_code"));
				newmemberBean.setDname(rs.getString("sp_name"));
				newmemberBean.setDdesc(rs.getString("short_desc"));
				finalResults.add(newmemberBean);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
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

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return finalResults;
	}

	public List findSubJnlSearch(JournalListBean newBean) {
		log4jLogger.info("start===========findSubJnlSearch=====");
		JournalListBean newmemberBean = null;
		List finalResults = null;

		try {
			con = getSession().connection();
			st = con.createStatement();

			if (newBean.getDname() == "") {

				rs = st.executeQuery(DataQuery.SUBJECTSEARCH_NAME);
			} else {

				rs = st.executeQuery(DataQuery.SUBJECTSEARCH_NAME_LIKE
						+ newBean.getDname() + "%' order by sub_name");

			}

			finalResults = new ArrayList();

			while (rs.next()) {
				newmemberBean = new JournalListBean();
				newmemberBean.setScode(rs.getInt("sub_code"));
				newmemberBean.setSname(rs.getString("sub_name"));
				newmemberBean.setSdesc(rs.getString("short_desc"));
				finalResults.add(newmemberBean);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
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

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return finalResults;
	}

	// Journal Issue Report
	public List findJnlNameSearch(JnlIssueReportBean newBean) {
		log4jLogger.info("start===========findJnlNameSearch=====");
		JnlIssueReportBean newmemberBean = null;
		List finalResults = null;

		try {
			con = getSession().connection();
			st = con.createStatement();

			if (newBean.getJname() == "") {

				rs = st.executeQuery(DataQuery.JOURNAL_SEARCH_NAME);
			} else {

				rs = st.executeQuery(DataQuery.JOURNAL_SEARCH_NAME_LIKE
						+ newBean.getJname() + "%' order by jnl_name");

			}

			finalResults = new ArrayList();

			while (rs.next()) {
				newmemberBean = new JnlIssueReportBean();
				newmemberBean.setJcode(rs.getInt("jnl_code"));
				newmemberBean.setJname(rs.getString("jnl_name"));
				newmemberBean.setFreq(rs.getString("doc_type"));
				finalResults.add(newmemberBean);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
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

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return finalResults;
	}

	public boolean findCheckData(String query) {

		boolean result = true;

		try {

			con = getSession().connection();

			String Query = query;
			Prest = con.prepareStatement(Query);

			rs = Prest.executeQuery();

			if (rs.next()) {
				result = false;
			} else {
				result = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
					rs = null;
				}
				if (Prest != null) {
					Prest.close();
					Prest = null;
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	public int findAccessNo(accessbean newBean) {
		log4jLogger
				.info("start===========Fetching Missing AccessNo From Database=====");
		int accessno = 0;
		String accno;
		try {
			con = getSession().connection();
			String doctype = newBean.getDoctype();
			if (!doctype.equals("ALL")) {
				Prest = con
						.prepareStatement(DataQuery.MissingAccessionQuery_Acc_no);
				Prest.setString(1, newBean.getAccessno());
				Prest.setString(2, newBean.getDoctype());
			} else {
				Prest = con
						.prepareStatement(DataQuery.MissingAccessionQuery_Acc_no_ALL);
				Prest.setString(1, newBean.getAccessno());
			}
			rs = Prest.executeQuery();
			if (rs.next()) {

				accno = rs.getString("access_no");
				accessno = 1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
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

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return accessno;
	}

	public int findAccessNoSave(accessbean newBean) {
		// log4jLogger.info("start===========Missing AccessNo Save=====");
		int count = 0;
		try {

			// getSession().save(newBean);
			// getSession().flush();

			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.MissingAccessNo);
			Prest.setString(1, newBean.getAccessno());
			count = Prest.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
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

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return count;
	}

	public int findDeleteMisNo() {
		log4jLogger.info("start===========Missing AccessNo Delete=====");
		int accessno = 0;
		try {
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.Missingdelete);
			Prest.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
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

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return accessno;
	}

	// ::::::::::::::::::::::member report::::::::::::::::::::::::

	public List findDesigList(MemberTransRefBean newBean) {
		log4jLogger.info("start===========getDesignationList=====");
		String strsql = "";

		MemberTransRefBean refBean = null;
		List<Object> result = new ArrayList<Object>();

		SQLQuery sql = getSession()
				.createSQLQuery(
						"SELECT DISTINCT(designation_code) AS designation_code,desig_name AS desig_name FROM member_report_view WHERE 2>1 "
								+ strsql + "Order by desig_name ASC");
		List list = sql.list();

		for (int i = 0; i < list.size(); i++) {
			Object[] obj = (Object[]) list.get(i);
			refBean = new MemberTransRefBean();

			refBean.setCode(Integer.parseInt(obj[0].toString()));
			refBean.setName(obj[1].toString());

			result.add(refBean);
		}

		return result;
	}

	public List findDepartmentList(MemberTransRefBean newBean) {
		log4jLogger.info("start===========findDepartmentList=====");
		String strsql = "";

		MemberTransRefBean refBean = null;
		List<Object> result = new ArrayList<Object>();

		SQLQuery sql = getSession().createSQLQuery(
				DataQuery.distinctDepartment + strsql
						+ "Order by dept_name ASC");
		List list = sql.list();

		for (int i = 0; i < list.size(); i++) {
			Object[] obj = (Object[]) list.get(i);
			refBean = new MemberTransRefBean();

			refBean.setCode(Integer.parseInt(obj[0].toString()));
			refBean.setName(obj[1].toString());

			result.add(refBean);
		}

		return result;
	}

	public List findGroupList(MemberTransRefBean newBean) {
		log4jLogger.info("start===========getGroupList=====");
		String strsql = "";

		MemberTransRefBean refBean = null;
		List<Object> result = new ArrayList<Object>();

		SQLQuery sql = getSession().createSQLQuery(
				DataQuery.distinctGroup + strsql);
		List list = sql.list();

		for (int i = 0; i < list.size(); i++) {
			Object[] obj = (Object[]) list.get(i);
			refBean = new MemberTransRefBean();

			refBean.setCode(Integer.parseInt(obj[0].toString()));
			refBean.setName(obj[1].toString());
			// refBean.setDesc(obj[2].toString());

			result.add(refBean);
		}

		return result;
	}

	public List findCourseList(MemberTransRefBean newBean) {
		log4jLogger.info("start===========getCourseList=====");
		String strsql = "";

		MemberTransRefBean refBean = null;
		List<Object> result = new ArrayList<Object>();

		SQLQuery sql = getSession().createSQLQuery(
				DataQuery.distinctCourse + strsql);
		List list = sql.list();

		for (int i = 0; i < list.size(); i++) {
			Object[] obj = (Object[]) list.get(i);
			refBean = new MemberTransRefBean();

			refBean.setCode(Integer.parseInt(obj[0].toString()));
			refBean.setName(obj[1].toString());
			refBean.setDesc(obj[2].toString());

			result.add(refBean);
		}

		return result;
	}

	public List findSearchBudgetList(BudgetBean newBean) {
		log4jLogger.info("start===========getSearchBudgetList=====");
		String strsql = "";

		BudgetBean refBean = null;
		List<Object> result = new ArrayList<Object>();

		SQLQuery sql = getSession().createSQLQuery(
				ReportQueryUtill.DistinctBudgetReportQuery + strsql);
		List list = sql.list();

		for (int i = 0; i < list.size(); i++) {
			Object[] obj = (Object[]) list.get(i);
			refBean = new BudgetBean();

			refBean.setBudCode(Integer.parseInt(obj[0].toString()));

			refBean.setBudHead(obj[1].toString());

			refBean.setDeptCode(Integer.parseInt(obj[2].toString()));
			refBean.setDeptname(obj[3].toString());

			result.add(refBean);
		}

		return result;
	}

	// Auto Complete Statistics Report

	public ArrayList<DepartmentBean> findStatDeptAutoSearch(String term) {
		log4jLogger.info("start===========findStatDeptAutoSearch=====");

		ArrayList<DepartmentBean> list = new ArrayList<DepartmentBean>();
		// PreparedStatement ps = null;
		// String data;

		try {
			con = getSession().connection();

			PreparedStatement ps = con
					.prepareStatement("SELECT DISTINCT(dept_name) from department_mas WHERE dept_name LIKE ? order by dept_name ASC limit 20");

			ps.setString(1, "" + term + "%");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				DepartmentBean user = new DepartmentBean();
				user.setName(rs.getString("dept_name"));

				list.add(user);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
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

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return list;
	}

	public ArrayList<subjectbean> findStatSubjectAutoSearch(String term) {
		log4jLogger.info("start===========findStatSubjectAutoSearch=====");

		ArrayList<subjectbean> list = new ArrayList<subjectbean>();
		// PreparedStatement ps = null;
		// String data;

		try {
			con = getSession().connection();

			// PreparedStatement ps =
			// con.prepareStatement("SELECT dept_code,dept_name,short_desc FROM department_mas WHERE dept_name LIKE ? order by dept_name ASC limit 20");
			PreparedStatement ps = con
					.prepareStatement("SELECT DISTINCT(sub_name) FROM subject_mas WHERE sub_name LIKE ? order by sub_name ASC limit 20");

			ps.setString(1, "" + term + "%");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				subjectbean user = new subjectbean();

				user.setName(rs.getString("sub_name"));

				list.add(user);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
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

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return list;
	}

	public ArrayList<PubSupBean> findStatPubAutoSearch(String term) {
		log4jLogger.info("start===========findStatPubAutoSearch=====");

		ArrayList<PubSupBean> list = new ArrayList<PubSupBean>();
		// PreparedStatement ps = null;
		// String data;

		try {
			con = getSession().connection();

			PreparedStatement ps = con
					.prepareStatement("SELECT sp_name,sp_type from sup_pub_mas WHERE sp_name LIKE ? AND sp_type = 'PUBLISHER' order by sp_name ASC limit 20");

			ps.setString(1, "" + term + "%");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				PubSupBean user = new PubSupBean();

				user.setName(rs.getString("sp_name"));
				user.setType(rs.getString("sp_type"));

				list.add(user);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
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

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return list;
	}

	public ArrayList<PubSupBean> findStatSupAutoSearch(String term) {
		log4jLogger.info("start===========findStatSupAutoSearch=====");

		ArrayList<PubSupBean> list = new ArrayList<PubSupBean>();
		// PreparedStatement ps = null;
		// String data;

		try {
			con = getSession().connection();

			PreparedStatement ps = con
					.prepareStatement("SELECT sp_name,sp_type from sup_pub_mas WHERE sp_name LIKE ? AND sp_type = 'SUPPLIER' order by sp_name ASC limit 20");

			ps.setString(1, "" + term + "%");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				PubSupBean user = new PubSupBean();

				user.setName(rs.getString("sp_name"));
				user.setType(rs.getString("sp_type"));

				list.add(user);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
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

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return list;
	}

	// Auto Complete Member Report

	public ArrayList<MemberBean> findMemberReportUserAutoSearch(String term) {
		log4jLogger.info("start===========findMemberReportUserAutoSearch=====");

		ArrayList<MemberBean> list = new ArrayList<MemberBean>();
		// PreparedStatement ps = null;
		// String data;

		try {
			con = getSession().connection();

			PreparedStatement ps = con
					.prepareStatement("SELECT DISTINCT(member_name) from member_mas WHERE member_name LIKE ? order by member_name ASC limit 20");

			ps.setString(1, "" + term + "%");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				MemberBean user = new MemberBean();
				user.setName(rs.getString("member_name"));

				list.add(user);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
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

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return list;
	}

	public ArrayList<DesignationBean> findMemberReportDesigAutoSearch(
			String term) {
		log4jLogger.info("start===========findStatSubjectAutoSearch=====");

		ArrayList<DesignationBean> list = new ArrayList<DesignationBean>();
		// PreparedStatement ps = null;
		// String data;

		try {
			con = getSession().connection();

			// PreparedStatement ps =
			// con.prepareStatement("SELECT dept_code,dept_name,short_desc FROM department_mas WHERE dept_name LIKE ? order by dept_name ASC limit 20");
			PreparedStatement ps = con
					.prepareStatement("SELECT DISTINCT(desig_name) FROM designation_mas WHERE desig_name LIKE ? order by desig_name ASC limit 20");

			ps.setString(1, "" + term + "%");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				DesignationBean user = new DesignationBean();

				user.setName(rs.getString("desig_name"));

				list.add(user);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
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

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return list;
	}

	public ArrayList<GroupBean> findMemberReportGroupAutoSearch(String term) {
		log4jLogger.info("start===========findStatPubAutoSearch=====");

		ArrayList<GroupBean> list = new ArrayList<GroupBean>();
		// PreparedStatement ps = null;
		// String data;

		try {
			con = getSession().connection();

			PreparedStatement ps = con
					.prepareStatement("SELECT group_name from group_mas WHERE group_name LIKE ? order by group_name ASC limit 20");

			ps.setString(1, "" + term + "%");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				GroupBean user = new GroupBean();

				user.setName(rs.getString("group_name"));

				list.add(user);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
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

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return list;
	}

	public ArrayList<CourseBean> findMemberReportCourseAutoSearch(String term) {
		log4jLogger
				.info("start===========findMemberReportCourseAutoSearch=====");

		ArrayList<CourseBean> list = new ArrayList<CourseBean>();
		// PreparedStatement ps = null;
		// String data;

		try {
			con = getSession().connection();

			PreparedStatement ps = con
					.prepareStatement("SELECT course_name from course_mas WHERE course_name LIKE ? order by course_name ASC limit 20");

			ps.setString(1, "" + term + "%");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				CourseBean user = new CourseBean();

				user.setName(rs.getString("course_name"));

				list.add(user);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
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

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return list;
	}

	public ArrayList<DepartmentBean> findMemberReportDeptAutoSearch(String term) {
		log4jLogger.info("start===========findMemberReportDeptAutoSearch=====");

		ArrayList<DepartmentBean> list = new ArrayList<DepartmentBean>();
		// PreparedStatement ps = null;
		// String data;

		try {
			con = getSession().connection();

			PreparedStatement ps = con
					.prepareStatement("SELECT dept_name from department_mas WHERE dept_name LIKE ? order by dept_name ASC limit 20");

			ps.setString(1, "" + term + "%");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				DepartmentBean user = new DepartmentBean();

				user.setName(rs.getString("dept_name"));

				list.add(user);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
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

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return list;
	}

	// Auto Complete Payment Report

	public ArrayList<MemberBean> findPaymentReportUserAutoSearch(String term) {
		log4jLogger
				.info("start===========findPaymentReportUserAutoSearch=====");

		ArrayList<MemberBean> list = new ArrayList<MemberBean>();
		// PreparedStatement ps = null;
		// String data;

		try {
			con = getSession().connection();

			PreparedStatement ps = con
					.prepareStatement("SELECT DISTINCT(member_name) from member_mas WHERE member_name LIKE ? order by member_name ASC limit 20");

			ps.setString(1, "" + term + "%");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				MemberBean user = new MemberBean();
				user.setName(rs.getString("member_name"));

				list.add(user);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
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

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return list;
	}

	public ArrayList<DesignationBean> findPaymentReportDesigAutoSearch(
			String term) {
		log4jLogger
				.info("start===========findPaymentReportDesigAutoSearch=====");

		ArrayList<DesignationBean> list = new ArrayList<DesignationBean>();
		// PreparedStatement ps = null;
		// String data;

		try {
			con = getSession().connection();

			// PreparedStatement ps =
			// con.prepareStatement("SELECT dept_code,dept_name,short_desc FROM department_mas WHERE dept_name LIKE ? order by dept_name ASC limit 20");
			PreparedStatement ps = con
					.prepareStatement("SELECT DISTINCT(desig_name) FROM designation_mas WHERE desig_name LIKE ? order by desig_name ASC limit 20");

			ps.setString(1, "" + term + "%");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				DesignationBean user = new DesignationBean();

				user.setName(rs.getString("desig_name"));

				list.add(user);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
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

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return list;
	}

	public ArrayList<GroupBean> findPaymentReportGroupAutoSearch(String term) {
		log4jLogger
				.info("start===========findPaymentReportGroupAutoSearch=====");

		ArrayList<GroupBean> list = new ArrayList<GroupBean>();
		// PreparedStatement ps = null;
		// String data;

		try {
			con = getSession().connection();

			PreparedStatement ps = con
					.prepareStatement("SELECT group_name from group_mas WHERE group_name LIKE ? order by group_name ASC limit 20");

			ps.setString(1, "" + term + "%");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				GroupBean user = new GroupBean();

				user.setName(rs.getString("group_name"));

				list.add(user);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
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

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return list;
	}

	public ArrayList<CourseBean> findPaymentReportCourseAutoSearch(String term) {
		log4jLogger
				.info("start===========findPaymentReportCourseAutoSearch=====");

		ArrayList<CourseBean> list = new ArrayList<CourseBean>();
		// PreparedStatement ps = null;
		// String data;

		try {
			con = getSession().connection();

			PreparedStatement ps = con
					.prepareStatement("SELECT course_name from course_mas WHERE course_name LIKE ? order by course_name ASC limit 20");

			ps.setString(1, "" + term + "%");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				CourseBean user = new CourseBean();

				user.setName(rs.getString("course_name"));

				list.add(user);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
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

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return list;
	}

	public ArrayList<DepartmentBean> findPaymentReportDeptAutoSearch(String term) {
		log4jLogger
				.info("start===========findPaymentReportDeptAutoSearch=====");

		ArrayList<DepartmentBean> list = new ArrayList<DepartmentBean>();
		// PreparedStatement ps = null;
		// String data;

		try {
			con = getSession().connection();

			PreparedStatement ps = con
					.prepareStatement("SELECT dept_name from department_mas WHERE dept_name LIKE ? order by dept_name ASC limit 20");

			ps.setString(1, "" + term + "%");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				DepartmentBean user = new DepartmentBean();

				user.setName(rs.getString("dept_name"));

				list.add(user);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
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

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return list;
	}

	// Auto Complete Counter Report

	public ArrayList<MemberBean> findCounterReportUserAutoSearch(String term) {
		log4jLogger
				.info("start===========findCounterReportUserAutoSearch=====");

		ArrayList<MemberBean> list = new ArrayList<MemberBean>();
		// PreparedStatement ps = null;
		// String data;

		try {
			con = getSession().connection();

			PreparedStatement ps = con
					.prepareStatement("SELECT DISTINCT(member_code) from member_mas WHERE member_code LIKE ? order by member_code ASC limit 20");

			ps.setString(1, "" + term + "%");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				MemberBean user = new MemberBean();
				user.setName(rs.getString("member_code"));

				list.add(user);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
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

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return list;
	}

	public ArrayList<bookbean> findCounterReportAccessNoAutoSearch(String term) {
		log4jLogger
				.info("start===========findCounterReportAccessNoAutoSearch=====");

		ArrayList<bookbean> list = new ArrayList<bookbean>();
		// PreparedStatement ps = null;
		// String data;

		try {
			con = getSession().connection();

			// PreparedStatement ps =
			// con.prepareStatement("SELECT dept_code,dept_name,short_desc FROM department_mas WHERE dept_name LIKE ? order by dept_name ASC limit 20");
			PreparedStatement ps = con
					.prepareStatement("SELECT DISTINCT(access_no) FROM book_mas WHERE access_no LIKE ? order by access_no ASC limit 20");

			ps.setString(1, "" + term + "%");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				bookbean user = new bookbean();

				user.setAccessNo(rs.getString("access_no"));

				list.add(user);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
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

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return list;
	}

	public ArrayList<GroupBean> findCounterReportGroupAutoSearch(String term) {
		log4jLogger
				.info("start===========findCounterReportGroupAutoSearch=====");

		ArrayList<GroupBean> list = new ArrayList<GroupBean>();
		// PreparedStatement ps = null;
		// String data;

		try {
			con = getSession().connection();

			PreparedStatement ps = con
					.prepareStatement("SELECT group_name from group_mas WHERE group_name LIKE ? order by group_name ASC limit 20");

			ps.setString(1, "" + term + "%");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				GroupBean user = new GroupBean();

				user.setName(rs.getString("group_name"));

				list.add(user);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
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

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return list;
	}

	public ArrayList<DepartmentBean> findCounterReportDeptAutoSearch(String term) {
		log4jLogger
				.info("start===========findCounterReportDeptAutoSearch=====");

		ArrayList<DepartmentBean> list = new ArrayList<DepartmentBean>();
		// PreparedStatement ps = null;
		// String data;

		try {
			con = getSession().connection();

			PreparedStatement ps = con
					.prepareStatement("SELECT dept_name from department_mas WHERE dept_name LIKE ? order by dept_name ASC limit 20");

			ps.setString(1, "" + term + "%");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				DepartmentBean user = new DepartmentBean();

				user.setName(rs.getString("dept_name"));

				list.add(user);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
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

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return list;
	}

	// Auto Complete Unique Title Report

	public ArrayList<DepartmentBean> findUniqueTitleReportDeptAutoSearch(
			String term) {
		log4jLogger
				.info("start===========findUniqueTitleReportDeptAutoSearch=====");

		ArrayList<DepartmentBean> list = new ArrayList<DepartmentBean>();
		// PreparedStatement ps = null;
		// String data;

		try {
			con = getSession().connection();

			PreparedStatement ps = con
					.prepareStatement("SELECT DISTINCT(dept_name) from department_mas WHERE dept_name LIKE ? order by dept_name ASC limit 20");

			ps.setString(1, "" + term + "%");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				DepartmentBean user = new DepartmentBean();
				user.setName(rs.getString("dept_name"));

				list.add(user);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
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

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return list;
	}

	public ArrayList<subjectbean> findUniqueTitleReportSubjectAutoSearch(
			String term) {
		log4jLogger
				.info("start===========findUniqueTitleReportSubjectAutoSearch=====");

		ArrayList<subjectbean> list = new ArrayList<subjectbean>();
		// PreparedStatement ps = null;
		// String data;

		try {
			con = getSession().connection();

			// PreparedStatement ps =
			// con.prepareStatement("SELECT dept_code,dept_name,short_desc FROM department_mas WHERE dept_name LIKE ? order by dept_name ASC limit 20");
			PreparedStatement ps = con
					.prepareStatement("SELECT DISTINCT(sub_name) FROM subject_mas WHERE sub_name LIKE ? order by sub_name ASC limit 20");

			ps.setString(1, "" + term + "%");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				subjectbean user = new subjectbean();

				user.setName(rs.getString("sub_name"));

				list.add(user);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
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

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return list;
	}

	// Auto Complete DB Report

	public ArrayList<DepartmentBean> findDBReportDeptAutoSearch(String term) {
		log4jLogger.info("start===========findDBReportDeptAutoSearch=====");

		ArrayList<DepartmentBean> list = new ArrayList<DepartmentBean>();
		// PreparedStatement ps = null;
		// String data;

		try {
			con = getSession().connection();

			PreparedStatement ps = con
					.prepareStatement("SELECT DISTINCT(dept_name) from department_mas WHERE dept_name LIKE ? order by dept_name ASC limit 20");

			ps.setString(1, "" + term + "%");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				DepartmentBean user = new DepartmentBean();
				user.setName(rs.getString("dept_name"));

				list.add(user);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
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

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return list;
	}

	public ArrayList<subjectbean> findDBReportSubjectAutoSearch(String term) {
		log4jLogger.info("start===========findDBReportSubjectAutoSearch=====");

		ArrayList<subjectbean> list = new ArrayList<subjectbean>();
		// PreparedStatement ps = null;
		// String data;

		try {
			con = getSession().connection();

			// PreparedStatement ps =
			// con.prepareStatement("SELECT dept_code,dept_name,short_desc FROM department_mas WHERE dept_name LIKE ? order by dept_name ASC limit 20");
			PreparedStatement ps = con
					.prepareStatement("SELECT DISTINCT(sub_name) FROM subject_mas WHERE sub_name LIKE ? order by sub_name ASC limit 20");

			ps.setString(1, "" + term + "%");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				subjectbean user = new subjectbean();

				user.setName(rs.getString("sub_name"));

				list.add(user);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
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

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return list;
	}

	public ArrayList<PubSupBean> findDBReportPubAutoSearch(String term) {
		log4jLogger.info("start===========findDBReportPubAutoSearch=====");

		ArrayList<PubSupBean> list = new ArrayList<PubSupBean>();
		// PreparedStatement ps = null;
		// String data;

		try {
			con = getSession().connection();

			PreparedStatement ps = con
					.prepareStatement("SELECT sp_name,sp_type from sup_pub_mas WHERE sp_name LIKE ? AND sp_type = 'PUBLISHER' order by sp_name ASC limit 20");

			ps.setString(1, "" + term + "%");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				PubSupBean user = new PubSupBean();

				user.setName(rs.getString("sp_name"));
				user.setType(rs.getString("sp_type"));

				list.add(user);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
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

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return list;
	}

	public ArrayList<PubSupBean> findDBReportSupAutoSearch(String term) {
		log4jLogger.info("start===========findDBReportSupAutoSearch=====");

		ArrayList<PubSupBean> list = new ArrayList<PubSupBean>();
		// PreparedStatement ps = null;
		// String data;

		try {
			con = getSession().connection();

			PreparedStatement ps = con
					.prepareStatement("SELECT sp_name,sp_type from sup_pub_mas WHERE sp_name LIKE ? AND sp_type = 'SUPPLIER' order by sp_name ASC limit 20");

			ps.setString(1, "" + term + "%");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				PubSupBean user = new PubSupBean();

				user.setName(rs.getString("sp_name"));
				user.setType(rs.getString("sp_type"));

				list.add(user);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
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

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return list;
	}

	// Auto Complete Journal List

	public ArrayList<DepartmentBean> findJournalListDeptAutoSearch(String term) {
		log4jLogger.info("start===========findJournalListDeptAutoSearch=====");

		ArrayList<DepartmentBean> list = new ArrayList<DepartmentBean>();
		// PreparedStatement ps = null;
		// String data;

		try {
			con = getSession().connection();

			PreparedStatement ps = con
					.prepareStatement("SELECT DISTINCT(dept_name) from department_mas WHERE dept_name LIKE ? order by dept_name ASC limit 20");

			ps.setString(1, "" + term + "%");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				DepartmentBean user = new DepartmentBean();
				user.setName(rs.getString("dept_name"));

				list.add(user);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
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

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return list;
	}

	public ArrayList<subjectbean> findJournalListSubjectAutoSearch(String term) {
		log4jLogger
				.info("start===========findJournalListSubjectAutoSearch=====");

		ArrayList<subjectbean> list = new ArrayList<subjectbean>();
		// PreparedStatement ps = null;
		// String data;

		try {
			con = getSession().connection();

			PreparedStatement ps = con
					.prepareStatement("SELECT DISTINCT(sub_name) FROM subject_mas WHERE sub_name LIKE ? order by sub_name ASC limit 20");

			ps.setString(1, "" + term + "%");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				subjectbean user = new subjectbean();

				user.setName(rs.getString("sub_name"));

				list.add(user);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
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

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return list;
	}

	// Auto Complete Journal Issues Report

	public ArrayList<journalbean> findJnlIssuesReportJnlNameAutoSearch(
			String term) {
		log4jLogger
				.info("start===========findJnlIssuesReportJnlNameAutoSearch=====");

		ArrayList<journalbean> list = new ArrayList<journalbean>();
		// PreparedStatement ps = null;
		// String data;

		try {
			con = getSession().connection();

			PreparedStatement ps = con
					.prepareStatement("SELECT DISTINCT(jnl_name) from journal_mas WHERE jnl_name LIKE ? order by jnl_name ASC limit 20");

			ps.setString(1, "" + term + "%");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				journalbean user = new journalbean();
				user.setJname(rs.getString("jnl_name"));

				list.add(user);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
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

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return list;
	}

	// Auto Complete Gate Register Report

	public ArrayList<MemberBean> findGateRegisterMemberCodeAutoSearch(
			String term) {
		log4jLogger
				.info("start===========findGateRegisterMemberCodeAutoSearch=====");

		ArrayList<MemberBean> list = new ArrayList<MemberBean>();
		// PreparedStatement ps = null;
		// String data;

		try {
			con = getSession().connection();

			PreparedStatement ps = con
					.prepareStatement("SELECT DISTINCT(member_code) from member_mas WHERE member_code LIKE ? order by member_code ASC limit 20");

			ps.setString(1, "" + term + "%");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				MemberBean user = new MemberBean();
				user.setCode(rs.getString("member_code"));

				list.add(user);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
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

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return list;
	}

	public ArrayList<GroupBean> findGateRegisterGroupAutoSearch(String term) {
		log4jLogger
				.info("start===========findGateRegisterGroupAutoSearch=====");

		ArrayList<GroupBean> list = new ArrayList<GroupBean>();
		// PreparedStatement ps = null;
		// String data;

		try {
			con = getSession().connection();

			// PreparedStatement ps =
			// con.prepareStatement("SELECT dept_code,dept_name,short_desc FROM department_mas WHERE dept_name LIKE ? order by dept_name ASC limit 20");
			PreparedStatement ps = con
					.prepareStatement("SELECT DISTINCT(group_name) FROM group_mas WHERE group_name LIKE ? order by group_name ASC limit 20");

			ps.setString(1, "" + term + "%");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				GroupBean user = new GroupBean();

				user.setName(rs.getString("group_name"));

				list.add(user);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
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

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return list;
	}

	public ArrayList<DepartmentBean> findGateRegisterDeptAutoSearch(String term) {
		log4jLogger.info("start===========findGateRegisterDeptAutoSearch=====");

		ArrayList<DepartmentBean> list = new ArrayList<DepartmentBean>();
		// PreparedStatement ps = null;
		// String data;

		try {
			con = getSession().connection();

			PreparedStatement ps = con
					.prepareStatement("SELECT dept_name from department_mas WHERE dept_name LIKE ? order by dept_name ASC limit 20");

			ps.setString(1, "" + term + "%");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				DepartmentBean user = new DepartmentBean();

				user.setName(rs.getString("dept_name"));

				list.add(user);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
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

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return list;
	}

	public ArrayList<DesignationBean> findGateRegisterDesigAutoSearch(
			String term) {
		log4jLogger
				.info("start===========findGateRegisterDesigAutoSearch=====");

		ArrayList<DesignationBean> list = new ArrayList<DesignationBean>();
		// PreparedStatement ps = null;
		// String data;

		try {
			con = getSession().connection();

			PreparedStatement ps = con
					.prepareStatement("SELECT desig_name from designation_mas WHERE desig_name LIKE ? order by desig_name ASC limit 20");

			ps.setString(1, "" + term + "%");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				DesignationBean user = new DesignationBean();

				user.setName(rs.getString("desig_name"));

				list.add(user);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
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

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return list;
	}

	public List findtodayIssueListDetails(String filterQuery) {

		log4jLogger.info("start===========findtodayIssueListDetails====="
				+ filterQuery);
		StringBuffer sb = new StringBuffer();
		String namedQuery = getSession().getNamedQuery("todayIssueListQuery")
				.getQueryString();

		reportbean newreportbean = null;
		List finalResults = null;

		try {
			con = getSession().connection();
			st = con.createStatement();

			if (filterQuery != null) {

				rs = st.executeQuery(namedQuery + filterQuery);
			} else {
				rs = st.executeQuery(namedQuery);
			}

			finalResults = new ArrayList();

			while (rs.next()) {
				newreportbean = new reportbean();

				newreportbean.setUcode(rs.getString("member_code"));
				newreportbean.setUname(rs.getString("member_name"));
				newreportbean.setAccno(rs.getString("access_no"));
				newreportbean.setTitle(rs.getString("title"));
				newreportbean.setAuthorName(rs.getString("author_name"));
				newreportbean.setIssueDate(rs.getString("issue_date"));
				newreportbean.setDueDate(rs.getString("due_date"));
				newreportbean.setDocType(rs.getString("doc_type"));
				newreportbean.setStaffCode(rs.getString("staff_code"));
				finalResults.add(newreportbean);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
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

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return finalResults;

	}

	public List findtodayReturnListDetails(String filterQuery) {

		log4jLogger.info("start===========findtodayReturnListDetails====="
				+ filterQuery);
		StringBuffer sb = new StringBuffer();
		String namedQuery = getSession().getNamedQuery("todayReturnListQuery")
				.getQueryString();
		if (filterQuery != null) {
			sb.append(namedQuery);
			sb.append(" " + filterQuery);
		} else {
			sb.append(namedQuery);
		}
		SQLQuery sql = getSession().createSQLQuery(sb.toString());

		return sql.list();

	}

	public List findtodayRenewalListDetails(String filterQuery) {

		log4jLogger.info("start===========findtodayRenewalListDetails====="
				+ filterQuery);
		StringBuffer sb = new StringBuffer();
		String namedQuery = getSession().getNamedQuery("todayRenewListQuery")
				.getQueryString();
		reportbean newreportbean = null;
		List finalResults = null;

		try {
			con = getSession().connection();
			st = con.createStatement();

			if (filterQuery != null) {

				rs = st.executeQuery(namedQuery + filterQuery);
			} else {
				rs = st.executeQuery(namedQuery);
			}

			finalResults = new ArrayList();

			while (rs.next()) {
				newreportbean = new reportbean();

				newreportbean.setUcode(rs.getString("member_code"));
				newreportbean.setUname(rs.getString("member_name"));
				newreportbean.setAccno(rs.getString("access_no"));
				newreportbean.setTitle(rs.getString("title"));
				newreportbean.setAuthorName(rs.getString("author_name"));
				newreportbean.setIssueDate(rs.getString("issue_date"));
				newreportbean.setDueDate(rs.getString("due_date"));
				newreportbean.setDocType(rs.getString("doc_type"));
				newreportbean.setStaffCode(rs.getString("staff_code"));
				finalResults.add(newreportbean);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
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

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return finalResults;

	}

	public List findtodayTransAmountDetails(String filterQuery) {

		log4jLogger.info("start===========findtodayTransAmountDetails====="
				+ filterQuery);
		StringBuffer sb = new StringBuffer();
		String namedQuery = getSession().getNamedQuery(
				"todayTransAmountListQuery").getQueryString();
		reportbean newreportbean = null;
		List finalResults = null;

		try {
			con = getSession().connection();
			st = con.createStatement();

			if (filterQuery != null) {

				rs = st.executeQuery(namedQuery + filterQuery);
			} else {
				rs = st.executeQuery(namedQuery);
			}

			finalResults = new ArrayList();

			while (rs.next()) {
				newreportbean = new reportbean();

				newreportbean.setUcode(rs.getString("member_code"));
				newreportbean.setUname(rs.getString("member_name"));
				newreportbean.setAccno(rs.getString("access_no"));
				newreportbean.setTitle(rs.getString("title"));
				newreportbean.setAuthorName(rs.getString("author_name"));
				newreportbean.setIssueDate(rs.getString("issue_date"));
				newreportbean.setDueDate(rs.getString("due_date"));
				newreportbean.setStaffCode(rs.getString("remarks"));
				newreportbean.setFineAmount(rs.getString("trans_amount"));
				newreportbean.setDocType(rs.getString("trans_head"));
				finalResults.add(newreportbean);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
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

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return finalResults;

	}

	public List findtodayPaidDetails(String filterQuery) {

		log4jLogger.info("start===========findtodayPaidDetails====="
				+ filterQuery);
		StringBuffer sb = new StringBuffer();
		String namedQuery = getSession().getNamedQuery("todayPaidListQuery")
				.getQueryString();
		if (filterQuery != null) {
			sb.append(namedQuery);
			sb.append(" " + filterQuery);
		} else {
			sb.append(namedQuery);
		}
		SQLQuery sql = getSession().createSQLQuery(sb.toString());

		return sql.list();

	}

	// Journal List Report

	public List findDeptJnlSearch(JournalListBean newBean) {
		log4jLogger.info("start===========findDeptJnlSearch=====");
		JournalListBean newmemberBean = null;
		List finalResults = null;

		try {
			con = getSession().connection();
			st = con.createStatement();

			if (newBean.getDname() == "") {

				rs = st.executeQuery(DataQuery.DEPTSEARCH_NAME);
			} else {

				rs = st.executeQuery(DataQuery.DEPTSEARCH_NAME_LIKE
						+ newBean.getDname() + "%' order by dept_name");

			}

			finalResults = new ArrayList();

			while (rs.next()) {
				newmemberBean = new JournalListBean();
				newmemberBean.setDcode(rs.getInt("dept_code"));
				newmemberBean.setDname(rs.getString("dept_name"));
				newmemberBean.setDdesc(rs.getString("short_desc"));
				finalResults.add(newmemberBean);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
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

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return finalResults;
	}

}
