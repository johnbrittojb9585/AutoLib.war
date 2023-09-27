package Common.businessutil.calaloging;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.SQLQuery;

import Common.DataQuery;
import Common.Security;
import Common.Security_Counter;
import Common.SplitAccessNoBean;
import Lib.Auto.Author.AuthorBean;
import Lib.Auto.Binding.BindingBean;
import Lib.Auto.Book.BookSearchBean;
import Lib.Auto.Book.bookbean;
import Lib.Auto.Branch.BranchBean;
import Lib.Auto.Budget.BudgetBean;
import Lib.Auto.City.CityBean;
import Lib.Auto.Course.CourseBean;
import Lib.Auto.Currency.CurrencyBean;
import Lib.Auto.Department.DepartmentBean;
import Lib.Auto.Designation.DesignationBean;
import Lib.Auto.EBook.EBookBean;
import Lib.Auto.EBook.EBookSearchBean;
import Lib.Auto.EResource.EResourceBean;
import Lib.Auto.Fine.Finebean;
import Lib.Auto.Group.GroupBean;
import Lib.Auto.Journal.journalbean;
import Lib.Auto.Journal_Artical.journalArtbean;
import Lib.Auto.Journal_Issues.JnlIssBean;
import Lib.Auto.Keywords.KeywordsBean;
import Lib.Auto.MResource.MResourceBean;
import Lib.Auto.Member.MemberBean;
import Lib.Auto.MsgMas.MsgMasBean;
import Lib.Auto.Newsclliping.NewscllipingBean;
import Lib.Auto.Payment.PaymentBean;
import Lib.Auto.Photo.PhotoBean;
import Lib.Auto.PubSup.PubSupBean;
import Lib.Auto.QBSubject.QBsubjectbean;
import Lib.Auto.QuestionBank.QuestionBankBean;
import Lib.Auto.Review.ReviewBean;
import Lib.Auto.Subject.subjectbean;
import Lib.Auto.Suggestion.SuggestionBean;
import Lib.Auto.Transfer_Books.BookTransferBean;

import com.library.autolib.portal.dbconnectionutil.BaseDBConnection;

public class CalalogingDaoImpl extends BaseDBConnection implements
		CalalogingDao {

	private static Logger log4jLogger = Logger
			.getLogger(CalalogingDaoImpl.class);

	java.sql.Connection con = null;
	java.sql.PreparedStatement Prest = null;
	java.sql.PreparedStatement Prest1 = null;
	java.sql.ResultSet rs = null;
	java.sql.ResultSet rs1 = null;
	java.sql.Statement st = null;

	private String invoice_Date;

	/**
	 * @param userId
	 * @param pwd
	 * @return authorBean
	 */
	// -----------------------Currency Load----------------------

	public List findCurrencyLoad() {
		log4jLogger.info("start===========findCurrencyLoad=====");

		CurrencyBean newauthor = null;
		List finalResults = null;

		try {

			con = getSession().connection();
			st = con.createStatement();

			rs = st.executeQuery(DataQuery.CURRENCYQLStringCode_LOAD);

			finalResults = new ArrayList();

			while (rs.next()) {
				newauthor = new CurrencyBean();
				newauthor.setCurrency(rs.getString("currency"));
				newauthor.setIndian_value(String.valueOf(rs
						.getFloat("indian_value")));
				finalResults.add(newauthor);

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

	public String findNewAccNoLoad(String doctype, String reportType) {
		log4jLogger
				.info("Inside accessno checking weather is Numeric or Alpha Numeric");
		log4jLogger.info("ReportDocument AccessNo Type::::::::::" + reportType);
		String New_no = "";

		try {

			con = getSession().connection();
			if (!doctype.equalsIgnoreCase("REPORT")) {
				Prest = con
						.prepareStatement("SELECT MAX(CONVERT(access_no,SIGNED INTEGER)) AS accno FROM book_mas WHERE  DOCUMENT='"
								+ doctype
								+ "' AND ACCESS_NO NOT REGEXP '^[0-9]'");
			} else {

				if (reportType != null && !reportType.isEmpty()) {
					Prest = con
							.prepareStatement("SELECT MAX(CONVERT(access_no,SIGNED INTEGER)) AS accno FROM book_mas WHERE  DOCUMENT='"
									+ doctype
									+ "' AND ACCESS_NO LIKE '"
									+ reportType
									+ "%' AND ACCESS_NO NOT REGEXP '^[0-9]'");
				} else {
					Prest = con
							.prepareStatement("SELECT MAX(CONVERT(access_no,SIGNED INTEGER)) AS accno FROM book_mas WHERE  DOCUMENT='"
									+ doctype
									+ "' AND ACCESS_NO NOT REGEXP '^[0-9]'");
				}
			}

			rs = Prest.executeQuery();

			if (rs.next()) {
				if (rs.getString("accno") == null) {

					int flen = 0, slen = 0;
					String rk = "", rk1 = "";
					con = getSession().connection();
					// Prest=con.prepareStatement("SELECT MAX(CONVERT(access_no,SIGNED INTEGER)+1) AS accno FROM book_mas WHERE  DOCUMENT='"+doctype+"' AND ACCESS_NO REGEXP '^[0-9]' "+strsql);
					Prest = con
							.prepareStatement("SELECT MAX(CONVERT(access_no,SIGNED INTEGER)+1) AS accno, MAX(access_no) AS oldaccno FROM book_mas WHERE  DOCUMENT='"
									+ doctype
									+ "' AND ACCESS_NO REGEXP '^[0-9]'");
					rs1 = Prest.executeQuery();

					if (rs1.next()) {
						rk = rs1.getString("accno");
						rk1 = rs1.getString("oldaccno");
						if (rk != null && rk1 != null) {
							flen = rk.length();
							slen = rk1.length();
						} else {
							rk = "";
						}
					}

					if (slen > flen) // For Add Zero before. Ex.00001+1 = 00002
					{
						rs1 = null;
						con = getSession().connection();
						Prest = con.prepareStatement("SELECT LPAD(" + rk + ","
								+ slen + ",0) AS accno");
						rs1 = Prest.executeQuery();

						if (rs1.next()) {
							rk = rs1.getString("accno");
						}
					}

					New_no = rk;
					// New_no=""+rk;
					log4jLogger.info("Value for Numeric :" + New_no);

				} else if (rs.getString("accno").equalsIgnoreCase("0")) {

					int i, len, nlength;
					String rk = "", viewchar = "", achar = "";
					con = getSession().connection();
					if (!doctype.equalsIgnoreCase("REPORT")) {
						Prest = con
								.prepareStatement("SELECT MAX(ACCESS_NO) as accno FROM BOOK_MAS WHERE DOCUMENT='"
										+ doctype
										+ "' AND ACCESS_NO NOT REGEXP '^[0-9]'");
					} else {
						if (reportType != null && !reportType.isEmpty()) {
							Prest = con
									.prepareStatement("SELECT MAX(ACCESS_NO) as accno FROM BOOK_MAS WHERE DOCUMENT='"
											+ doctype
											+ "' AND ACCESS_NO LIKE '"
											+ reportType
											+ "%' AND ACCESS_NO NOT REGEXP '^[0-9]'");
						} else {
							Prest = con
									.prepareStatement("SELECT MAX(ACCESS_NO) as accno FROM BOOK_MAS WHERE DOCUMENT='"
											+ doctype
											+ "' AND ACCESS_NO NOT REGEXP '^[0-9]'");
						}
					}

					rs1 = Prest.executeQuery();

					if (rs1.next()) {

						rk = rs1.getString("accno");
						len = rk.length();

						for (i = 0; i < len; i++) {
							achar = rk.substring(i, i + 1);

							if (IsNumeric(achar)) {
								break;
							} else {
								viewchar = viewchar + achar;
							}
						}

						i = i + 1;
						nlength = len - i + 1;
						i = i - 1;

						if (viewchar.isEmpty()) {
							log4jLogger
									.info("**************** No Code for New Button in Book Master");
						} else {
							rs1 = null;
							int lenaccessno, maxaccno;
							String zerochar = "";

							con = getSession().connection();
							Prest = con
									.prepareStatement("SELECT MAX(CONVERT(RIGHT(Access_No, (LENGTH(Access_No)-"
											+ i
											+ ")),SIGNED INTEGER)) AS access_no FROM book_mas WHERE DOCUMENT='"
											+ doctype
											+ "' AND Access_No Like '"
											+ viewchar + "%'");
							rs1 = Prest.executeQuery();

							if (rs1.next()) {
								lenaccessno = rs1.getInt("access_no") + 1;
								maxaccno = lenaccessno;
								String lenacc = "" + lenaccessno;

								if (nlength > lenacc.length()) {
									lenaccessno = nlength - lenacc.length();

									if (lenaccessno == 1) {
										zerochar = "0";
									} else if (lenaccessno == 2) {
										zerochar = "00";
									} else if (lenaccessno == 3) {
										zerochar = "000";
									} else if (lenaccessno == 4) {
										zerochar = "0000";
									} else if (lenaccessno == 5) {
										zerochar = "00000";
									} else if (lenaccessno == 6) {
										zerochar = "000000";
									} else if (lenaccessno == 7) {
										zerochar = "0000000";
									}

									New_no = viewchar + zerochar + maxaccno;
								} else {
									New_no = viewchar + maxaccno;
								}
							}
						}
					}

					log4jLogger.info("Value for Alpha Numeric :" + New_no);

				} else {
					log4jLogger
							.info("No suggestion Available --> New Button in Book Master ");
				}
			} else {
				log4jLogger
						.info("******************** No Use of this code: --> New Button in Book Master ********************************"
								+ rs);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (rs1 != null) {
					rs1.close();
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

		return New_no;

	}

	private boolean IsNumeric(String string) {
		try {
			Integer.parseInt(string);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	// -------------------------Author-----------------------
	public AuthorBean findAuthorMax() {
		log4jLogger.info("start===========findAuthorMax=====");

		AuthorBean authorBean = null;
		try {

			con = getSession().connection();

			Prest = con.prepareStatement(DataQuery.AUTHORSQLStringCode);
			rs = Prest.executeQuery();

			if (rs.next()) {
				authorBean = new AuthorBean();
				authorBean.setCode(rs.getInt("maxno") + 1);

			}

		} catch (Exception e) {

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

		return authorBean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Common.businessutil.calaloging.CalalogingDao#findAuthorSearch(int)
	 */
	public AuthorBean findAuthorSearch(int code) {
		log4jLogger.info("start===========findAuthorSearch=====");
		AuthorBean authorBean = null;
		try {
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.AUTHORSELECT_STRING);
			Prest.setInt(1, code);
			rs = Prest.executeQuery();
			if (rs.next()) {
				authorBean = new AuthorBean();
				authorBean.setCode(rs.getInt(1));
				authorBean.setName(rs.getString(2));
				authorBean.setDesc(rs.getString(3));
				authorBean.setEmail(rs.getString(4));
			}
		} catch (Exception e) {

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

		return authorBean;
	}

	public int authorUpdate(AuthorBean authorBean) {
		log4jLogger.info("start===========authorUpdate=====");
		int count = 0;
		try {

			getSession().update(authorBean);
			getSession().flush();

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

	public int findAuthorInterface(int code) {
		log4jLogger.info("start===========findAuthorInterface=====");
		int author_Interface_code = 0;
		try {
			con = getSession().connection();
			Prest = con
					.prepareStatement(DataQuery.AUTHORSELECT_STRING_AUTHORINTERFACE);
			Prest.setInt(1, code);
			rs = Prest.executeQuery();
			if (rs.next()) {
				author_Interface_code = rs.getInt("Author_Code");

			}
		} catch (Exception e) {

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

		return author_Interface_code;
	}

	public int findAuthorMas(int code) {
		log4jLogger.info("start===========findAuthorMas=====");
		int author_Mas_code = 0;
		try {
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.AUTHORSELECT_STRING);
			Prest.setInt(1, code);
			rs = Prest.executeQuery();
			if (rs.next()) {
				author_Mas_code = rs.getInt("Author_Code");

			}
		} catch (Exception e) {

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

		return author_Mas_code;
	}

	public int authorDelete(int code) {
		log4jLogger.info("start===========authorDelete=====");

		int author_Delete = 0;
		try {

			String query = getSession()
					.getNamedQuery("AuthorDetailDeleteQuery").getQueryString();
			SQLQuery sql = getSession().createSQLQuery(query);
			sql.setParameter("code", code);
			sql.executeUpdate();

			if (rs.next()) {

			}
		} catch (Exception e) {

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

		return author_Delete;
	}

	public int authorSave(AuthorBean authorBean) {
		log4jLogger.info("start===========authorSave=====");
		int count = 0;

		try {

			getSession().save(authorBean);
			getSession().flush();

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

	public int findAuthorName(AuthorBean authorBean) {
		log4jLogger.info("start===========findAuthorName=====");

		int Author_code = 0;
		try {
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.AUTHORSame_Name);
			Prest.setString(1, authorBean.getName());
			rs = Prest.executeQuery();
			if (rs.next()) {

				Author_code = rs.getInt("Author_Code");
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

		return Author_code;
	}

	public int findAuthorNameAuthorInterface(int code) {
		log4jLogger.info("start===========findAuthorNameAuthorInterface=====");
		int Author_Interface_code = 0;
		try {
			con = getSession().connection();
			Prest = con
					.prepareStatement(DataQuery.AUTHORSELECT_STRING_AUTHORINTERFACE);

			Prest.setInt(1, code);
			rs = Prest.executeQuery();
			if (rs.next()) {
				Author_Interface_code = rs.getInt("Author_Code");

			}
		} catch (Exception e) {

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

		return Author_Interface_code;
	}

	public int findAuthorcode(int code) {
		log4jLogger.info("start===========findAuthorcode=====");
		int Author_code = 0;
		try {
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.AUTHORSELECT_STRING);

			Prest.setInt(1, code);
			rs = Prest.executeQuery();
			if (rs.next()) {
				Author_code = rs.getInt("Author_Code");

			}
		} catch (Exception e) {

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

		return Author_code;
	}

	public List findAuthorMasName(AuthorBean authorBean) {
		log4jLogger.info("start===========findAuthorMasName=====");
		AuthorBean ob = null;
		ob = new AuthorBean();
		AuthorBean newauthor = null;
		List finalResults = null;
		newauthor = new AuthorBean();

		try {
			con = getSession().connection();
			st = con.createStatement();

			if (authorBean.getName() == "") {
				rs = st.executeQuery(DataQuery.AUTHORSEARCH_NAME);
			} else {

				rs = st.executeQuery(DataQuery.AUTHORSEARCH_NAME_LIKE
						+ authorBean.getName() + "%' order by Author_Name");

			}

			finalResults = new ArrayList();

			newauthor = new AuthorBean();
			while (rs.next()) {
				newauthor = new AuthorBean();
				newauthor.setCode(rs.getInt("Author_Code"));
				newauthor.setName(rs.getString("Author_Name"));
				newauthor.setDesc(rs.getString("Short_Desc"));
				finalResults.add(newauthor);

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

	// ----------------------AutoComplete---------------------

	public ArrayList<AuthorBean> findAuthorAutoSearch(String term) {
		log4jLogger.info("start===========findAuthorAutoSearch=====");

		ArrayList<AuthorBean> list = new ArrayList<AuthorBean>();
		// PreparedStatement ps = null;
		// String data;

		try {
			con = getSession().connection();

			PreparedStatement ps = con
					.prepareStatement("SELECT Author_Code,Author_Name,short_desc,EmailId FROM author_mas WHERE Author_Name LIKE ? order by Author_Name ASC limit 20");

			ps.setString(1, "" + term + "%");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				AuthorBean user = new AuthorBean();
				user.setCode(rs.getInt("Author_Code"));
				user.setName(rs.getString("Author_Name"));
				user.setDesc(rs.getString("short_desc"));
				user.setEmail(rs.getString("EmailId"));

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

	public ArrayList<DepartmentBean> findDeptAutoSearch(String term) {
		log4jLogger.info("start===========findDeptAutoSearch=====");

		ArrayList<DepartmentBean> list = new ArrayList<DepartmentBean>();
		// PreparedStatement ps = null;
		// String data;

		try {
			con = getSession().connection();

			// PreparedStatement ps =
			// con.prepareStatement("SELECT Dept_Code,Dept_Name,Short_Desc FROM department_mas WHERE Dept_Name LIKE ? order by Dept_Name ASC limit 20");
			PreparedStatement ps = con
					.prepareStatement("SELECT Dept_Code,Dept_Name,Short_Desc FROM department_mas WHERE Dept_Name LIKE ? order by Dept_Name ASC limit 20");

			ps.setString(1, "" + term + "%");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				DepartmentBean user = new DepartmentBean();
				user.setCode(rs.getInt("Dept_Code"));
				user.setName(rs.getString("Dept_Name"));
				user.setDesc(rs.getString("Short_Desc"));

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

	public ArrayList<PaymentBean> findUserNoSearch(String term) {
		log4jLogger.info("start===========findUserNoSearch=====");

		ArrayList<PaymentBean> list = new ArrayList<PaymentBean>();
		// PreparedStatement ps = null;
		// String data;

		try {
			con = getSession().connection();

			// PreparedStatement ps =
			// con.prepareStatement("SELECT Dept_Code,Dept_Name,Short_Desc FROM department_mas WHERE Dept_Name LIKE ? order by Dept_Name ASC limit 20");
			PreparedStatement ps = con
					.prepareStatement("SELECT member_code from TRANS_MAS WHERE member_code LIKE ? order by member_code ASC limit 20");

			ps.setString(1, "" + term + "%");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				PaymentBean user = new PaymentBean();
				user.setMcode(rs.getString("member_code"));

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

	public ArrayList<subjectbean> findSubjectAutoSearch(String term) {
		log4jLogger.info("start===========findSubjectAutoSearch=====");

		ArrayList<subjectbean> list = new ArrayList<subjectbean>();
		// PreparedStatement ps = null;
		// String data;

		try {
			con = getSession().connection();

			PreparedStatement ps = con
					.prepareStatement("SELECT Sub_Code,Sub_Name,Call_No,Short_Desc FROM subject_mas WHERE Sub_Name LIKE ? order by Sub_Name ASC limit 20");

			ps.setString(1, "" + term + "%");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				subjectbean user = new subjectbean();
				user.setCode(rs.getInt("Sub_Code"));
				user.setName(rs.getString("Sub_Name"));
				user.setCallno(rs.getString("Call_No"));
				user.setDesc(rs.getString("Short_Desc"));

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

	public ArrayList<PubSupBean> findPubSupAutoSearch(String term, String type) {
		log4jLogger.info("start===========findPubSupAutoSearch=====");

		ArrayList<PubSupBean> list = new ArrayList<PubSupBean>();
		// PreparedStatement ps = null;
		// String data;

		try {
			con = getSession().connection();

			PreparedStatement ps = con
					.prepareStatement("SELECT * FROM sup_pub_mas WHERE Sp_Name LIKE ? and sp_type=? order by Sp_Name ASC limit 20");

			ps.setString(1, "" + term + "%");
			ps.setString(2, type);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				PubSupBean user = new PubSupBean();
				user.setCode(rs.getInt("Sp_Code"));
				user.setName(rs.getString("Sp_Name"));
				user.setDesc(rs.getString("Short_Desc"));
				user.setAdd1(rs.getString("Address1"));
				user.setAdd2(rs.getString("Address2"));
				user.setCity(rs.getString("City"));
				user.setState(rs.getString("State"));
				user.setCountry(rs.getString("Country"));
				user.setPin(rs.getString("Pincode"));
				user.setPhone(rs.getString("Phone"));
				user.setFax(rs.getString("Fax"));
				user.setEmail(rs.getString("EmailId"));
				user.setUrl(rs.getString("URLID"));
				user.setType(rs.getString("SP_TYPE"));

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

	public ArrayList<CourseBean> findCourseAutoSearch(String term) {
		log4jLogger.info("start===========findCourseAutoSearch=====");

		ArrayList<CourseBean> list = new ArrayList<CourseBean>();
		// PreparedStatement ps = null;
		// String data;

		try {
			con = getSession().connection();

			PreparedStatement ps = con
					.prepareStatement("SELECT Course_Code,Course_Name,Course_Major,Course_Period,Course_Type,Short_Desc FROM course_mas WHERE Course_Name LIKE ? order by Course_Name ASC limit 20");

			ps.setString(1, "" + term + "%");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				CourseBean user = new CourseBean();
				user.setCode(rs.getInt("Course_Code"));
				user.setName(rs.getString("Course_Name"));
				user.setMajor(rs.getString("Course_Major"));
				user.setPeriod(rs.getInt("Course_Period"));
				user.setType(rs.getString("Course_Type"));
				user.setDesc(rs.getString("Short_Desc"));

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

	public ArrayList<DesignationBean> findDesigAutoSearch(String term) {
		log4jLogger.info("start===========findDesigAutoSearch=====");

		ArrayList<DesignationBean> list = new ArrayList<DesignationBean>();
		// PreparedStatement ps = null;
		// String data;

		try {
			con = getSession().connection();

			PreparedStatement ps = con
					.prepareStatement("SELECT desig_code,desig_name,short_desc FROM designation_mas WHERE desig_name LIKE ? order by desig_name ASC limit 20");

			ps.setString(1, "" + term + "%");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				DesignationBean user = new DesignationBean();
				user.setCode(rs.getInt("desig_code"));
				user.setName(rs.getString("desig_name"));
				user.setDesc(rs.getString("short_desc"));

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

	public ArrayList<CurrencyBean> findCurrencyAutoSearch(String term) {
		log4jLogger.info("start===========findCurrencyAutoSearch=====");

		ArrayList<CurrencyBean> list = new ArrayList<CurrencyBean>();
		// PreparedStatement ps = null;
		// String data;

		try {
			con = getSession().connection();

			PreparedStatement ps = con
					.prepareStatement("SELECT Code,Currency,Indian_Value,Remarks FROM currency_mas WHERE Currency LIKE ? order by Currency ASC limit 20");

			ps.setString(1, "" + term + "%");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				CurrencyBean user = new CurrencyBean();
				user.setCode(rs.getInt("Code"));
				user.setCurrency(rs.getString("Currency"));
				user.setIndian_value(rs.getString("Indian_Value"));
				user.setRemarks(rs.getString("Remarks"));

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

	public ArrayList<KeywordsBean> findKeywordsAutoSearch(String term) {
		log4jLogger.info("start===========findKeywordsAutoSearch=====");

		ArrayList<KeywordsBean> list = new ArrayList<KeywordsBean>();
		// PreparedStatement ps = null;
		// String data;

		try {
			con = getSession().connection();

			PreparedStatement ps = con
					.prepareStatement("SELECT keyword_code,keyword_name,short_desc FROM keywords_mas WHERE keyword_name LIKE ? order by keyword_name ASC limit 20");

			ps.setString(1, "" + term + "%");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				KeywordsBean user = new KeywordsBean();
				user.setKcode(rs.getInt("keyword_code"));
				user.setKname(rs.getString("keyword_name"));
				user.setKdesc(rs.getString("short_desc"));

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

	public ArrayList<BindingBean> findBindingAutoSearch(String term) {
		log4jLogger.info("start===========findBindingAutoSearch=====");

		ArrayList<BindingBean> list = new ArrayList<BindingBean>();
		// PreparedStatement ps = null;
		// String data;

		try {
			con = getSession().connection();

			PreparedStatement ps = con
					.prepareStatement("SELECT * FROM binder_mas WHERE Binder_Name LIKE ? order by Binder_Name ASC limit 20");

			ps.setString(1, "" + term + "%");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				BindingBean user = new BindingBean();
				user.setCode(rs.getInt("Binder_Code"));
				user.setName(rs.getString("Binder_Name"));
				user.setDesc(rs.getString("Short_Desc"));
				user.setAdd1(rs.getString("Address1"));
				user.setAdd2(rs.getString("Address2"));
				user.setCity(rs.getString("City"));
				user.setState(rs.getString("State"));
				user.setCountry(rs.getString("Country"));
				user.setPin(rs.getString("Pincode"));
				user.setPhone(rs.getString("Phone"));
				user.setFax(rs.getString("Fax"));
				user.setEmail(rs.getString("EmailId"));
				user.setUrl(rs.getString("URLID"));

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

	public ArrayList<CityBean> findCityAutoSearch(String term) {
		log4jLogger.info("start===========findCityAutoSearch=====");

		ArrayList<CityBean> list = new ArrayList<CityBean>();
		// PreparedStatement ps = null;
		// String data;

		try {
			con = getSession().connection();

			PreparedStatement ps = con
					.prepareStatement("SELECT City_Code,City_Name,State,country,Pincode,Short_Desc FROM city_mas WHERE City_Name LIKE ? order by City_Name ASC limit 20");

			ps.setString(1, "" + term + "%");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				CityBean user = new CityBean();
				user.setCode(rs.getInt("City_Code"));
				user.setName(rs.getString("City_Name"));
				user.setState(rs.getString("State"));
				user.setCountry(rs.getString("country"));
				user.setPincode(rs.getInt("Pincode"));
				user.setDesc(rs.getString("Short_Desc"));

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

	public ArrayList<QBsubjectbean> findQBSubjectAutoSearch(String term) {
		log4jLogger.info("start===========findQBSubjectAutoSearch=====");

		ArrayList<QBsubjectbean> list = new ArrayList<QBsubjectbean>();
		// PreparedStatement ps = null;
		// String data;

		try {
			con = getSession().connection();

			PreparedStatement ps = con
					.prepareStatement("SELECT QB_code,QBSub_Code,QBSub_Name,QBSub_Desc FROM qbsubject_mas WHERE QBSub_Code LIKE ? order by QBSub_Code ASC limit 20");

			ps.setString(1, "" + term + "%");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				QBsubjectbean user = new QBsubjectbean();
				user.setQbcode(rs.getInt("QB_code"));
				user.setQbsubcode(rs.getString("QBSub_Code"));
				user.setQbsubname(rs.getString("QBSub_Name"));
				user.setQbsubdesc(rs.getString("QBSub_Desc"));

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

	public ArrayList<bookbean> findBookAutoAccessNoSearch(String term,
			String doc) {
		log4jLogger.info("start===========findBookAutoAccessNoSearch=====");

		ArrayList<bookbean> list = new ArrayList<bookbean>();
		// PreparedStatement ps = null;
		// String data;

		try {
			con = getSession().connection();

			PreparedStatement ps = con
					.prepareStatement("SELECT DISTINCT(access_no) FROM book_mas WHERE access_no LIKE ? and document='"
							+ doc + "' order by access_no ASC limit 20");

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

	public ArrayList<bookbean> findBookAutoCallNoSearch(String term, String doc) {
		log4jLogger.info("start===========findBookAutoCallNoSearch=====");

		ArrayList<bookbean> list = new ArrayList<bookbean>();
		// PreparedStatement ps = null;
		// String data;

		try {
			con = getSession().connection();

			PreparedStatement ps = con
					.prepareStatement("SELECT DISTINCT(call_no) FROM book_mas WHERE call_no LIKE ? and document='"
							+ doc + "' order by call_no ASC limit 20");

			ps.setString(1, "" + term + "%");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				bookbean user = new bookbean();
				user.setCallNo(rs.getString("call_no"));

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

	public ArrayList<bookbean> findBookAutoTitleSearch(String term, String doc) {
		log4jLogger.info("start===========findBookAutoTitleSearch=====");

		ArrayList<bookbean> list = new ArrayList<bookbean>();
		// PreparedStatement ps = null;
		// String data;

		try {
			con = getSession().connection();
			PreparedStatement ps = null;
			if (doc.equals("BACK VOLUME")) {
				ps = con.prepareStatement("SELECT DISTINCT(jnl_name) FROM journal_mas WHERE jnl_name LIKE ?  order by jnl_name ASC limit 20");
				ps.setString(1, "" + term + "%");
				ResultSet rs = ps.executeQuery();

				while (rs.next()) {
					bookbean user = new bookbean();

					user.setTitle(rs.getString("jnl_name"));

					list.add(user);

				}
			} else {
				ps = con.prepareStatement("SELECT DISTINCT(title) FROM book_mas WHERE title LIKE ? and document='"
						+ doc + "' order by title ASC limit 20");

				ps.setString(1, "" + term + "%");
				ResultSet rs = ps.executeQuery();

				while (rs.next()) {
					bookbean user = new bookbean();

					user.setTitle(rs.getString("title"));

					list.add(user);

				}

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

	public ArrayList<AuthorBean> findBookAutoAuthorSearch(String term) {
		log4jLogger.info("start===========findBookAutoAuthorSearch=====");

		ArrayList<AuthorBean> list = new ArrayList<AuthorBean>();
		// PreparedStatement ps = null;
		// String data;

		try {
			con = getSession().connection();

			PreparedStatement ps = con
					.prepareStatement("SELECT DISTINCT(Author_Name) FROM author_mas WHERE Author_Name LIKE ? order by Author_Name ASC limit 20");

			ps.setString(1, "" + term + "%");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				AuthorBean user = new AuthorBean();

				user.setName(rs.getString("Author_Name"));

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

	public ArrayList<PubSupBean> findBookAutoPublisherSearch(String term) {
		log4jLogger.info("start===========findBookAutoPublisherSearch=====");

		ArrayList<PubSupBean> list = new ArrayList<PubSupBean>();
		// PreparedStatement ps = null;
		// String data;

		try {
			con = getSession().connection();

			PreparedStatement ps = con
					.prepareStatement("SELECT SP_Name,SP_Type FROM sup_pub_mas WHERE SP_Name LIKE ? and SP_Type = 'PUBLISHER' order by SP_Name ASC limit 20");

			ps.setString(1, "" + term + "%");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				PubSupBean user = new PubSupBean();

				user.setName(rs.getString("SP_Name"));
				user.setType(rs.getString("SP_Type"));

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

	public ArrayList<subjectbean> findBookAutoSubjectSearch(String term) {
		log4jLogger.info("start===========findBookAutoSubjectSearch=====");

		ArrayList<subjectbean> list = new ArrayList<subjectbean>();
		// PreparedStatement ps = null;
		// String data;

		try {
			con = getSession().connection();

			PreparedStatement ps = con
					.prepareStatement("SELECT DISTINCT(Sub_Name) FROM subject_mas WHERE Sub_Name LIKE ? order by Sub_Name ASC limit 20");

			ps.setString(1, "" + term + "%");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				subjectbean user = new subjectbean();

				user.setName(rs.getString("Sub_Name"));

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

	public ArrayList<DepartmentBean> findBookAutoDeptSearch(String term) {
		log4jLogger.info("start===========findBookAutoDeptSearch=====");

		ArrayList<DepartmentBean> list = new ArrayList<DepartmentBean>();
		// PreparedStatement ps = null;
		// String data;

		try {
			con = getSession().connection();

			PreparedStatement ps = con
					.prepareStatement("SELECT DISTINCT(Dept_Name) FROM department_mas WHERE Dept_Name LIKE ? order by Dept_Name ASC limit 20");

			ps.setString(1, "" + term + "%");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				DepartmentBean user = new DepartmentBean();

				user.setName(rs.getString("Dept_Name"));

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

	public ArrayList<PubSupBean> findBookAutoSupplierSearch(String term) {
		log4jLogger.info("start===========findBookAutoSupplierSearch=====");

		ArrayList<PubSupBean> list = new ArrayList<PubSupBean>();
		// PreparedStatement ps = null;
		// String data;

		try {
			con = getSession().connection();

			PreparedStatement ps = con
					.prepareStatement("SELECT SP_Name,SP_Type FROM sup_pub_mas WHERE SP_Name LIKE ? and SP_Type='SUPPLIER' order by SP_Name ASC limit 20");

			ps.setString(1, "" + term + "%");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				PubSupBean user = new PubSupBean();

				user.setName(rs.getString("SP_Name"));
				user.setType(rs.getString("SP_Type"));

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

	public ArrayList<BudgetBean> findBookAutoBudgetSearch(String term) {
		log4jLogger.info("start===========findBookAutoBudgetSearch=====");

		ArrayList<BudgetBean> list = new ArrayList<BudgetBean>();
		// PreparedStatement ps = null;
		// String data;

		try {
			con = getSession().connection();

			PreparedStatement ps = con
					.prepareStatement("SELECT DISTINCT(Bud_Head) FROM budget_mas WHERE Bud_Head LIKE ? order by Bud_Head ASC limit 20");

			ps.setString(1, "" + term + "%");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				BudgetBean user = new BudgetBean();

				user.setBudHead(rs.getString("Bud_Head"));

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

	public ArrayList<KeywordsBean> findBookAutoKeywordsSearch(String term) {
		log4jLogger.info("start===========findBookAutoKeywordsSearch=====");

		ArrayList<KeywordsBean> list = new ArrayList<KeywordsBean>();
		// PreparedStatement ps = null;
		// String data;

		try {
			con = getSession().connection();

			PreparedStatement ps = con
					.prepareStatement("SELECT DISTINCT(keyword_name) FROM keywords_mas WHERE keyword_name LIKE ? order by Keyword_name ASC limit 20");

			ps.setString(1, "" + term + "%");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				KeywordsBean user = new KeywordsBean();

				user.setKname(rs.getString("keyword_name"));

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

	public ArrayList<bookbean> findAddfield3Search(String term, String Title) {
		log4jLogger.info("start===========findaddfield3Search=====");

		ArrayList<bookbean> list = new ArrayList<bookbean>();
		// PreparedStatement ps = null;
		// String data;

		try {
			con = getSession().connection();

			PreparedStatement ps = con
					.prepareStatement("SELECT issue_no,issue_volume,issue_month,issue_year  from journal_issues_search where jnl_name=? and issue_no  LIKE ? order by issue_no ASC limit 20");
			ps.setString(1, Title);
			ps.setString(2, "" + term + "%");

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				bookbean user = new bookbean();

				user.setYop(rs.getString("issue_year"));
				user.setScript(rs.getString("issue_month"));
				user.setVolumeNo(rs.getString("issue_volume"));
				user.setAddfield3(rs.getString("issue_no"));

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

	public ArrayList<MemberBean> findMemberAutoIdSearch(String term) {
		log4jLogger.info("start===========findMemberAutoIdSearch=====");

		ArrayList<MemberBean> list = new ArrayList<MemberBean>();
		// PreparedStatement ps = null;
		// String data;

		try {
			con = getSession().connection();

			PreparedStatement ps = con
					.prepareStatement("SELECT Member_Code FROM Member_Mas WHERE Member_Code LIKE ? order by Member_Code ASC limit 20");

			ps.setString(1, "" + term + "%");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				MemberBean user = new MemberBean();
				user.setCode(rs.getString("Member_Code"));

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

	public ArrayList<MemberBean> findMemberAutoNameSearch(String term) {
		log4jLogger.info("start===========findMemberAutoNameSearch=====");

		ArrayList<MemberBean> list = new ArrayList<MemberBean>();
		// PreparedStatement ps = null;
		// String data;

		try {
			con = getSession().connection();

			PreparedStatement ps = con
					.prepareStatement("SELECT DISTINCT(Member_Name) FROM Member_Mas WHERE Member_Name LIKE ? order by Member_Name ASC limit 20");

			ps.setString(1, "" + term + "%");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				MemberBean user = new MemberBean();

				user.setName(rs.getString("Member_Name"));

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

	public ArrayList<DesignationBean> findMemberAutoDesigSearch(String term) {
		log4jLogger.info("start===========findMemberAutoDesigSearch=====");

		ArrayList<DesignationBean> list = new ArrayList<DesignationBean>();
		// PreparedStatement ps = null;
		// String data;

		try {
			con = getSession().connection();

			PreparedStatement ps = con
					.prepareStatement("SELECT DISTINCT(desig_name) FROM Designation_Mas WHERE desig_name LIKE ? order by desig_name ASC limit 20");

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

	public ArrayList<DepartmentBean> findMemberAutoDeptSearch(String term) {
		log4jLogger.info("start===========findMemberAutoDeptSearch=====");

		ArrayList<DepartmentBean> list = new ArrayList<DepartmentBean>();
		// PreparedStatement ps = null;
		// String data;

		try {
			con = getSession().connection();

			PreparedStatement ps = con
					.prepareStatement("SELECT Dept_Name FROM Department_Mas WHERE Dept_Name LIKE ? order by Dept_Name ASC limit 20");

			ps.setString(1, "" + term + "%");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				DepartmentBean user = new DepartmentBean();
				// user.setCode(rs.getInt("Dept_Code"));
				user.setName(rs.getString("Dept_Name"));

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

	public ArrayList<GroupBean> findMemberAutoGroupSearch(String term) {
		log4jLogger.info("start===========findMemberAutoGroupSearch=====");

		ArrayList<GroupBean> list = new ArrayList<GroupBean>();
		// PreparedStatement ps = null;
		// String data;

		try {
			con = getSession().connection();

			PreparedStatement ps = con
					.prepareStatement("SELECT DISTINCT(Group_Name) FROM Group_Mas WHERE Group_Name LIKE ? order by Group_Name ASC limit 20");

			ps.setString(1, "" + term + "%");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				GroupBean user = new GroupBean();
				user.setName(rs.getString("Group_Name"));

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

	public ArrayList<CourseBean> findMemberAutoCourseSearch(String term) {
		log4jLogger.info("start===========findMemberAutoCourseSearch=====");

		ArrayList<CourseBean> list = new ArrayList<CourseBean>();
		// PreparedStatement ps = null;
		// String data;

		try {
			con = getSession().connection();

			PreparedStatement ps = con
					.prepareStatement("SELECT CONCAT(course_name,'-',course_major) as course from course_mas WHERE CONCAT(course_name,'-',course_major) LIKE ?  order by course ASC limit 20");
			// PreparedStatement ps =
			// con.prepareStatement("SELECT DISTINCT(course_name)from course_mas WHERE course_name LIKE ? order by course_name ASC limit 20");

			ps.setString(1, "" + term + "%");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				CourseBean user = new CourseBean();

				user.setName(rs.getString("course"));

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

	public ArrayList<CityBean> findMemberAutoCitySearch(String term) {
		log4jLogger.info("start===========findMemberAutoCitySearch=====");

		ArrayList<CityBean> list = new ArrayList<CityBean>();
		// PreparedStatement ps = null;
		// String data;

		try {
			con = getSession().connection();

			PreparedStatement ps = con
					.prepareStatement("SELECT DISTINCT(City_Name) FROM City_Mas WHERE City_Name LIKE ? order by City_Name ASC limit 20");

			ps.setString(1, "" + term + "%");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				CityBean user = new CityBean();

				user.setName(rs.getString("City_Name"));

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

	public ArrayList<EBookBean> findEBookAutoAccessNoSearch(String term) {
		log4jLogger.info("start===========findEBookAutoAccessNoSearch=====");

		ArrayList<EBookBean> list = new ArrayList<EBookBean>();
		// PreparedStatement ps = null;
		// String data;

		try {
			con = getSession().connection();

			PreparedStatement ps = con
					.prepareStatement("SELECT DISTINCT(Access_No) FROM ebook_mas WHERE Access_No LIKE ? order by Access_No ASC limit 20");

			ps.setString(1, "" + term + "%");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				EBookBean user = new EBookBean();
				user.setAccessNo(rs.getString("Access_No"));

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

	public ArrayList<EBookBean> findEBookAutoCallNoSearch(String term) {
		log4jLogger.info("start===========findEBookAutoCallNoSearch=====");

		ArrayList<EBookBean> list = new ArrayList<EBookBean>();
		// PreparedStatement ps = null;
		// String data;

		try {
			con = getSession().connection();

			PreparedStatement ps = con
					.prepareStatement("SELECT DISTINCT(Call_No) FROM ebook_mas WHERE Call_No LIKE ? order by Call_No ASC limit 20");

			ps.setString(1, "" + term + "%");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				EBookBean user = new EBookBean();
				user.setCallNo(rs.getString("Call_No"));

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

	public ArrayList<EBookBean> findEBookAutoTitleSearch(String term) {
		log4jLogger.info("start===========findEBookAutoTitleSearch=====");

		ArrayList<EBookBean> list = new ArrayList<EBookBean>();
		// PreparedStatement ps = null;
		// String data;

		try {
			con = getSession().connection();

			PreparedStatement ps = con
					.prepareStatement("SELECT DISTINCT(Title) FROM ebook_mas WHERE Title LIKE ? order by Title ASC limit 20");

			ps.setString(1, "" + term + "%");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				EBookBean user = new EBookBean();

				user.setTitle(rs.getString("Title"));

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

	public ArrayList<AuthorBean> findEBookAutoAuthorSearch(String term) {
		log4jLogger.info("start===========findEBookAutoAuthorSearch=====");

		ArrayList<AuthorBean> list = new ArrayList<AuthorBean>();
		// PreparedStatement ps = null;
		// String data;

		try {
			con = getSession().connection();

			PreparedStatement ps = con
					.prepareStatement("SELECT DISTINCT(Author_Name) FROM author_mas WHERE Author_Name LIKE ? order by Author_Name ASC limit 20");

			ps.setString(1, "" + term + "%");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				AuthorBean user = new AuthorBean();

				user.setName(rs.getString("Author_Name"));

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

	public ArrayList<PubSupBean> findEBookAutoPublisherSearch(String term) {
		log4jLogger.info("start===========findEBookAutoPublisherSearch=====");

		ArrayList<PubSupBean> list = new ArrayList<PubSupBean>();
		// PreparedStatement ps = null;
		// String data;

		try {
			con = getSession().connection();

			PreparedStatement ps = con
					.prepareStatement("SELECT SP_Code,SP_Name,SP_Type FROM sup_pub_mas WHERE SP_Name LIKE ? and SP_Type = 'PUBLISHER' order by SP_Name ASC limit 20");

			ps.setString(1, "" + term + "%");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				PubSupBean user = new PubSupBean();

				user.setCode(rs.getInt("SP_Code"));
				user.setName(rs.getString("SP_Name"));
				user.setType(rs.getString("SP_Type"));

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

	public ArrayList<PubSupBean> findEBookAutoSupplierSearch(String term) {
		log4jLogger.info("start===========findEBookAutoSupplierSearch=====");

		ArrayList<PubSupBean> list = new ArrayList<PubSupBean>();
		// PreparedStatement ps = null;
		// String data;

		try {
			con = getSession().connection();

			PreparedStatement ps = con
					.prepareStatement("SELECT SP_Code,SP_Name,SP_Type FROM sup_pub_mas WHERE SP_Name LIKE ? and SP_Type = 'SUPPLIER' order by SP_Name ASC limit 20");

			ps.setString(1, "" + term + "%");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				PubSupBean user = new PubSupBean();

				user.setCode(rs.getInt("SP_Code"));
				user.setName(rs.getString("SP_Name"));
				user.setType(rs.getString("SP_Type"));

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

	public ArrayList<subjectbean> findEBookAutoSubjectSearch(String term) {
		log4jLogger.info("start===========findEBookAutoSubjectSearch=====");

		ArrayList<subjectbean> list = new ArrayList<subjectbean>();
		// PreparedStatement ps = null;
		// String data;

		try {
			con = getSession().connection();

			PreparedStatement ps = con
					.prepareStatement("SELECT sub_code,sub_name FROM subject_mas WHERE Sub_Name LIKE ? order by Sub_Name ASC limit 20");

			ps.setString(1, "" + term + "%");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				subjectbean user = new subjectbean();

				user.setName(rs.getString("Sub_Name"));
				user.setCode(Integer.parseInt(rs.getString("sub_code")));
				System.out.println(rs.getString("Sub_Name"));
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

	public ArrayList<DepartmentBean> findEBookAutoDeptSearch(String term) {
		log4jLogger.info("start===========findEBookAutoDeptSearch=====");

		ArrayList<DepartmentBean> list = new ArrayList<DepartmentBean>();
		// PreparedStatement ps = null;
		// String data;

		try {
			con = getSession().connection();

			PreparedStatement ps = con
					.prepareStatement("SELECT dept_code,dept_name FROM department_mas WHERE Dept_Name LIKE ? order by Dept_Name ASC limit 20");

			ps.setString(1, "" + term + "%");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				DepartmentBean user = new DepartmentBean();

				user.setName(rs.getString("Dept_Name"));
				user.setCode(Integer.parseInt(rs.getString("dept_code")));
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

	public ArrayList<journalbean> findJournalAutoCodeSearch(String term) {
		log4jLogger.info("start===========findJournalAutoCodeSearch=====");

		ArrayList<journalbean> list = new ArrayList<journalbean>();
		// PreparedStatement ps = null;
		// String data;

		try {
			con = getSession().connection();

			PreparedStatement ps = con
					.prepareStatement("SELECT DISTINCT(Jnl_Code) FROM journal_mas WHERE Jnl_Code LIKE ? order by Jnl_Code ASC limit 20");

			ps.setString(1, "" + term + "%");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				journalbean user = new journalbean();
				user.setJcode(rs.getString("Jnl_Code"));

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

	public ArrayList<journalbean> findJournalAutoNameSearch(String term) {
		log4jLogger.info("start===========findJournalAutoNameSearch=====");

		ArrayList<journalbean> list = new ArrayList<journalbean>();
		// PreparedStatement ps = null;
		// String data;

		try {
			con = getSession().connection();

			PreparedStatement ps = con
					.prepareStatement("SELECT DISTINCT(Jnl_Name) FROM journal_mas WHERE Jnl_Name LIKE ? order by Jnl_Name ASC limit 20");

			ps.setString(1, "" + term + "%");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				journalbean user = new journalbean();
				user.setJname(rs.getString("Jnl_Name"));

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

	public ArrayList<PubSupBean> findJournalAutoPubSearch(String term) {
		log4jLogger.info("start===========findJournalAutoPubSearch=====");

		ArrayList<PubSupBean> list = new ArrayList<PubSupBean>();
		// PreparedStatement ps = null;
		// String data;

		try {
			con = getSession().connection();

			PreparedStatement ps = con
					.prepareStatement("SELECT SP_NAME,SP_Type FROM sup_pub_mas WHERE SP_NAME LIKE ? AND SP_Type = 'PUBLISHER' order by SP_NAME ASC limit 20");

			ps.setString(1, "" + term + "%");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				PubSupBean user = new PubSupBean();

				user.setName(rs.getString("SP_NAME"));
				user.setType(rs.getString("SP_Type"));

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

	public ArrayList<subjectbean> findJournalAutoSubjectSearch(String term) {
		log4jLogger.info("start===========findJournalAutoSubjectSearch=====");

		ArrayList<subjectbean> list = new ArrayList<subjectbean>();
		// PreparedStatement ps = null;
		// String data;

		try {
			con = getSession().connection();

			PreparedStatement ps = con
					.prepareStatement("SELECT DISTINCT(Sub_Name) FROM subject_mas WHERE Sub_Name LIKE ? order by Sub_Name ASC limit 20");

			ps.setString(1, "" + term + "%");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				subjectbean user = new subjectbean();
				// user.setAccessNo(rs.getString("access_no"));
				user.setName(rs.getString("Sub_Name"));
				// user.setDesc(rs.getString("short_desc"));
				// user.setEmail(rs.getString("EmailId"));

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

	public ArrayList<DepartmentBean> findJournalAutoDeptSearch(String term) {
		log4jLogger.info("start===========findJournalAutoDeptSearch=====");

		ArrayList<DepartmentBean> list = new ArrayList<DepartmentBean>();
		// PreparedStatement ps = null;
		// String data;

		try {
			con = getSession().connection();

			PreparedStatement ps = con
					.prepareStatement("SELECT DISTINCT(Dept_Name) FROM department_mas WHERE Dept_Name LIKE ? order by Dept_Name ASC limit 20");

			ps.setString(1, "" + term + "%");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				DepartmentBean user = new DepartmentBean();
				// user.setAccessNo(rs.getString("access_no"));
				user.setName(rs.getString("Dept_Name"));
				// user.setDesc(rs.getString("short_desc"));
				// user.setEmail(rs.getString("EmailId"));

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

	public ArrayList<journalArtbean> findJnlAtlCodeAutoSearch(String term) {
		log4jLogger.info("start===========findJnlAtlCodeAutoSearch=====");

		ArrayList<journalArtbean> list = new ArrayList<journalArtbean>();
		// PreparedStatement ps = null;
		// String data;

		try {
			con = getSession().connection();

			PreparedStatement ps = con
					.prepareStatement("SELECT DISTINCT(Jnl_Code),Jnl_Name FROM journal_articles WHERE Jnl_Code LIKE ? order by Jnl_Code ASC limit 20");

			ps.setString(1, "" + term + "%");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				journalArtbean user = new journalArtbean();
				user.setJcode(rs.getString("Jnl_Code"));
				user.setJname(rs.getString("Jnl_Name"));

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

	public ArrayList<journalArtbean> findJnlAtlNameAutoSearch(String term) {
		log4jLogger.info("start===========findJnlAtlNameAutoSearch=====");

		ArrayList<journalArtbean> list = new ArrayList<journalArtbean>();
		// PreparedStatement ps = null;
		// String data;

		try {
			con = getSession().connection();

			PreparedStatement ps = con
					.prepareStatement("SELECT DISTINCT(Jnl_Name) FROM journal_articles WHERE Jnl_Name LIKE ? order by Jnl_Name ASC limit 20");

			ps.setString(1, "" + term + "%");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				journalArtbean user = new journalArtbean();
				user.setJname(rs.getString("Jnl_Name"));

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

	public ArrayList<journalArtbean> findJnlAtlAtlNoAutoSearch(String term) {
		log4jLogger.info("start===========findJnlAtlAtlNoAutoSearch=====");

		ArrayList<journalArtbean> list = new ArrayList<journalArtbean>();
		// PreparedStatement ps = null;
		// String data;

		try {
			con = getSession().connection();

			PreparedStatement ps = con
					.prepareStatement("SELECT Atl_No FROM journal_articles WHERE Atl_No LIKE ? order by Atl_No ASC limit 20");

			ps.setString(1, "" + term + "%");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				journalArtbean user = new journalArtbean();

				user.setAtlno(rs.getString("Atl_No"));

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

	public ArrayList<JnlIssBean> findJnlAtlIssueAccessNoAutoSearch(String term) {
		log4jLogger
				.info("start===========findJnlAtlIssueAccessNoAutoSearch=====");

		ArrayList<JnlIssBean> list = new ArrayList<JnlIssBean>();
		// PreparedStatement ps = null;
		// String data;

		try {
			con = getSession().connection();

			PreparedStatement ps = con
					.prepareStatement("SELECT DISTINCT(Issue_Access_No) FROM journal_issues WHERE Issue_Access_No LIKE ? order by Issue_Access_No ASC limit 20");

			ps.setString(1, "" + term + "%");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				JnlIssBean user = new JnlIssBean();
				user.setIss_acc_no(rs.getString("Issue_Access_No"));

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

	public ArrayList<journalArtbean> findJnlAtlSubjectAutoSearch(String term) {
		log4jLogger.info("start===========findJnlAtlSubjectAutoSearch=====");

		ArrayList<journalArtbean> list = new ArrayList<journalArtbean>();
		// PreparedStatement ps = null;
		// String data;

		try {
			con = getSession().connection();

			PreparedStatement ps = con
					.prepareStatement("SELECT DISTINCT(Atl_Subject) FROM journal_articles WHERE Atl_Subject LIKE ? order by Atl_Subject ASC limit 20");

			ps.setString(1, "" + term + "%");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				journalArtbean user = new journalArtbean();

				user.setSubject(rs.getString("Atl_Subject"));

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

	public ArrayList<journalbean> findJnlSubscriptionNameAutoSearch(String term) {
		log4jLogger
				.info("start===========findJnlSubscriptionNameAutoSearch=====");

		ArrayList<journalbean> list = new ArrayList<journalbean>();
		// PreparedStatement ps = null;
		// String data;

		try {
			con = getSession().connection();

			PreparedStatement ps = con
					.prepareStatement("SELECT DISTINCT(Jnl_Name) FROM journal_mas WHERE Jnl_Name LIKE ? order by Jnl_Name ASC limit 20");

			ps.setString(1, "" + term + "%");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				journalbean user = new journalbean();
				//
				user.setJname(rs.getString("Jnl_Name"));

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

	public ArrayList<PubSupBean> findJnlSubscriptionSupplierAutoSearch(
			String term) {
		log4jLogger
				.info("start===========findJnlSubscriptionSupplierAutoSearch=====");

		ArrayList<PubSupBean> list = new ArrayList<PubSupBean>();
		// PreparedStatement ps = null;
		// String data;

		try {
			con = getSession().connection();

			PreparedStatement ps = con
					.prepareStatement("SELECT SP_Name,SP_Type FROM sup_pub_mas WHERE SP_Name LIKE ? AND SP_Type = 'SUPPLIER' order by SP_Name ASC limit 20");

			ps.setString(1, "" + term + "%");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				PubSupBean user = new PubSupBean();

				user.setName(rs.getString("SP_Name"));
				user.setType(rs.getString("SP_Type"));

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

	public ArrayList<journalbean> findJnlIssuesJnlNameAutoSearch(String term) {
		log4jLogger.info("start===========findJnlIssuesJnlNameAutoSearch=====");

		ArrayList<journalbean> list = new ArrayList<journalbean>();
		// PreparedStatement ps = null;
		// String data;

		try {
			con = getSession().connection();

			PreparedStatement ps = con
					.prepareStatement("SELECT DISTINCT(Jnl_Name) FROM journal_mas WHERE Jnl_Name LIKE ? order by Jnl_Name ASC limit 20");

			ps.setString(1, "" + term + "%");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				journalbean user = new journalbean();
				//
				user.setJname(rs.getString("Jnl_Name"));

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

	public ArrayList<NewscllipingBean> findNewsPaperNameAutoSearch(String term) {
		log4jLogger.info("start===========findNewsPaperNameAutoSearch=====");

		ArrayList<NewscllipingBean> list = new ArrayList<NewscllipingBean>();
		// PreparedStatement ps = null;
		// String data;

		try {
			con = getSession().connection();

			PreparedStatement ps = con
					.prepareStatement("SELECT DISTINCT(Newspaper_name) FROM newsclipping_mas WHERE Newspaper_name LIKE ? order by Newspaper_name ASC limit 20");

			ps.setString(1, "" + term + "%");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				NewscllipingBean user = new NewscllipingBean();
				user.setNname(rs.getString("Newspaper_name"));

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

	public ArrayList<NewscllipingBean> findNewsPaperTypeAutoSearch(String term) {
		log4jLogger.info("start===========findNewsPaperTypeAutoSearch=====");

		ArrayList<NewscllipingBean> list = new ArrayList<NewscllipingBean>();
		// PreparedStatement ps = null;
		// String data;

		try {
			con = getSession().connection();

			PreparedStatement ps = con
					.prepareStatement("SELECT DISTINCT(Newspaper_type) FROM newsclipping_mas WHERE Newspaper_type LIKE ? order by Newspaper_type ASC limit 20");

			ps.setString(1, "" + term + "%");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				NewscllipingBean user = new NewscllipingBean();
				user.setNtype(rs.getString("Newspaper_type"));

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

	public ArrayList<NewscllipingBean> findNewsPaperSubjectAutoSearch(
			String term) {
		log4jLogger.info("start===========findNewsPaperSubjectAutoSearch=====");

		ArrayList<NewscllipingBean> list = new ArrayList<NewscllipingBean>();
		// PreparedStatement ps = null;
		// String data;

		try {
			con = getSession().connection();

			PreparedStatement ps = con
					.prepareStatement("SELECT DISTINCT(Newspaper_subject) FROM newsclipping_mas WHERE Newspaper_subject LIKE ? order by Newspaper_subject ASC limit 20");

			ps.setString(1, "" + term + "%");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				NewscllipingBean user = new NewscllipingBean();
				user.setNsubject(rs.getString("Newspaper_subject"));

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

	public ArrayList<QBsubjectbean> findQBankMasSubjectAutoSearch(String term) {
		log4jLogger.info("start===========findQBankMasSubjectAutoSearch=====");

		ArrayList<QBsubjectbean> list = new ArrayList<QBsubjectbean>();
		// PreparedStatement ps = null;
		// String data;

		try {
			con = getSession().connection();

			PreparedStatement ps = con
					.prepareStatement("SELECT DISTINCT(QBSub_Name) FROM qbsubject_mas WHERE QBSub_Name LIKE ? order by QBSub_Name ASC limit 20");

			ps.setString(1, "" + term + "%");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				QBsubjectbean user = new QBsubjectbean();
				user.setQbsubname(rs.getString("QBSub_Name"));

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

	public ArrayList<DepartmentBean> findQBankMasDeptAutoSearch(String term) {
		log4jLogger.info("start===========findQBankMasDeptAutoSearch=====");

		ArrayList<DepartmentBean> list = new ArrayList<DepartmentBean>();
		// PreparedStatement ps = null;
		// String data;

		try {
			con = getSession().connection();

			PreparedStatement ps = con
					.prepareStatement("SELECT DISTINCT(Dept_Name) FROM department_mas WHERE Dept_Name LIKE ? order by Dept_Name ASC limit 20");

			ps.setString(1, "" + term + "%");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				DepartmentBean user = new DepartmentBean();
				user.setName(rs.getString("Dept_Name"));

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

	public ArrayList<CourseBean> findQBankMasCourseAutoSearch(String term) {
		log4jLogger.info("start===========findQBankMasCourseAutoSearch=====");

		ArrayList<CourseBean> list = new ArrayList<CourseBean>();
		// PreparedStatement ps = null;
		// String data;

		try {
			con = getSession().connection();

			PreparedStatement ps = con
					.prepareStatement("SELECT DISTINCT(Course_Name) FROM course_mas WHERE Course_Name LIKE ? order by Course_Name ASC limit 20");

			ps.setString(1, "" + term + "%");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				CourseBean user = new CourseBean();
				user.setName(rs.getString("Course_Name"));

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

	public ArrayList<DepartmentBean> findStatDeptAutoSearch(String term) {
		log4jLogger.info("start===========findStatDeptAutoSearch=====");

		ArrayList<DepartmentBean> list = new ArrayList<DepartmentBean>();
		// PreparedStatement ps = null;
		// String data;

		try {
			con = getSession().connection();

			PreparedStatement ps = con
					.prepareStatement("SELECT DISTINCT(Dept_Name) from department_mas WHERE Dept_Name LIKE ? order by Dept_Name ASC limit 20");

			ps.setString(1, "" + term + "%");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				DepartmentBean user = new DepartmentBean();
				user.setName(rs.getString("Dept_Name"));

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
			// con.prepareStatement("SELECT Dept_Code,Dept_Name,Short_Desc FROM department_mas WHERE Dept_Name LIKE ? order by Dept_Name ASC limit 20");
			PreparedStatement ps = con
					.prepareStatement("SELECT DISTINCT(Sub_Name) FROM subject_mas WHERE Sub_Name LIKE ? order by Sub_Name ASC limit 20");

			ps.setString(1, "" + term + "%");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				subjectbean user = new subjectbean();

				user.setName(rs.getString("Sub_Name"));

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
					.prepareStatement("SELECT SP_Name,SP_Type from sup_pub_mas WHERE SP_Name LIKE ? AND SP_Type = 'PUBLISHER' order by SP_Name ASC limit 20");

			ps.setString(1, "" + term + "%");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				PubSupBean user = new PubSupBean();

				user.setName(rs.getString("SP_Name"));
				user.setType(rs.getString("SP_Type"));

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
					.prepareStatement("SELECT SP_Name,SP_Type from sup_pub_mas WHERE SP_Name LIKE ? AND SP_Type = 'SUPPLIER' order by SP_Name ASC limit 20");

			ps.setString(1, "" + term + "%");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				PubSupBean user = new PubSupBean();

				user.setName(rs.getString("SP_Name"));
				user.setType(rs.getString("SP_Type"));

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

	public ArrayList<Finebean> findFineCodeAutoSearch(String term) {
		log4jLogger.info("start===========findFineCodeAutoSearch=====");

		ArrayList<Finebean> list = new ArrayList<Finebean>();
		// PreparedStatement ps = null;
		// String data;

		try {
			con = getSession().connection();

			PreparedStatement ps = con
					.prepareStatement("SELECT * from fine_mas where Fine_ID LIKE ? order by Fine_ID ASC limit 20");

			ps.setString(1, "" + term + "%");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Finebean user = new Finebean();

				user.setFcode(rs.getInt("Fine_ID"));
				user.setGcode(rs.getString("Group_Code"));
				user.setFperiod(rs.getString("Fine_Period"));
				user.setFamount(rs.getString("Fine_Amount"));
				user.setType(rs.getString("Period_Type"));

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

	public ArrayList<GroupBean> findFineGroupAutoSearch(String term) {
		log4jLogger.info("start===========findFineGroupAutoSearch=====");

		ArrayList<GroupBean> list = new ArrayList<GroupBean>();
		// PreparedStatement ps = null;
		// String data;

		try {
			con = getSession().connection();

			PreparedStatement ps = con
					.prepareStatement("SELECT Group_Name from group_mas WHERE Group_Name LIKE ? order by Group_Name ASC limit 20");

			ps.setString(1, "" + term + "%");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				GroupBean user = new GroupBean();

				user.setName(rs.getString("Group_Name"));

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

	// ----------------EBook--------------------------

	public EBookBean findEBookMax() {
		log4jLogger.info("start===========findEBookMax=====");
		String New_no = "";
		EBookBean ob = new EBookBean();

		try {

			con = getSession().connection();

			Prest = con
					.prepareStatement("SELECT MAX(CONVERT(access_no,SIGNED INTEGER)) AS accno FROM ebook_mas WHERE ACCESS_NO NOT REGEXP '^[0-9]'");
			rs = Prest.executeQuery();

			if (rs.next()) {

				if (rs.getString("accno") == null) {
					int flen = 0, slen = 0;
					String rk = "", rk1 = "";
					con = getSession().connection();
					Prest = con
							.prepareStatement("SELECT MAX(CONVERT(access_no,SIGNED INTEGER)+1) AS accno, MAX(access_no) AS oldaccno FROM ebook_mas  WHERE  ACCESS_NO REGEXP '^[0-9]'");
					rs1 = Prest.executeQuery();
					if (rs1.next()) {
						rk = rs1.getString("accno");
						rk1 = rs1.getString("oldaccno");
						if (rk != null && rk1 != null) {
							flen = rk.length();
							slen = rk1.length();
						} else {
							rk = "";
						}

					}

					if (slen > flen) // For Add Zero before. Ex.00001+1 = 00002

					{
						rs1 = null;
						con = getSession().connection();
						Prest = con.prepareStatement("SELECT LPAD(" + rk + ","
								+ slen + ",0) AS accno");
						rs1 = Prest.executeQuery();

						if (rs1.next()) {
							rk = rs1.getString("accno");
						}
					}
					New_no = rk;
					log4jLogger.info("Value for Numeric :" + New_no);
					ob.setAccessNo(rs1.getString("accno"));
				}

				else if (rs.getString("accno").equalsIgnoreCase("0")) {

					int i, len, nlength;
					String rk = "", viewchar = "", achar = "";
					con = getSession().connection();

					Prest = con
							.prepareStatement("SELECT MAX(ACCESS_NO) AS accno FROM ebook_mas WHERE ACCESS_NO NOT REGEXP '^[0-9]'");
					rs1 = Prest.executeQuery();
					if (rs1.next()) {

						rk = rs1.getString("accno");
						len = rk.length();

						for (i = 0; i < len; i++)

						{
							achar = rk.substring(i, i + 1);

							if (IsNumeric(achar)) {
								break;
							} else {
								viewchar = viewchar + achar;
							}
						}

						i = i + 1;
						nlength = len - i + 1;
						i = i - 1;
						if (viewchar.isEmpty()) {
							log4jLogger
									.info("**************** No Code for New Button in Book Master");
						}

						else {

							rs1 = null;
							int lenaccessno, maxaccno;
							String zerochar = "";
							con = getSession().connection();
							Prest = con
									.prepareStatement("SELECT MAX(CONVERT(RIGHT(Access_No, (LENGTH(Access_No)-"
											+ i
											+ ")),SIGNED INTEGER)) AS access_no FROM ebook_mas WHERE Access_No LIKE '"
											+ viewchar + "%'");
							rs1 = Prest.executeQuery();
							if (rs1.next()) {
								lenaccessno = rs1.getInt("access_no") + 1;
								maxaccno = lenaccessno;
								String lenacc = "" + lenaccessno;

								if (nlength > lenacc.length()) {
									lenaccessno = nlength - lenacc.length();

									if (lenaccessno == 1) {
										zerochar = "0";
									} else if (lenaccessno == 2) {
										zerochar = "00";
									} else if (lenaccessno == 3) {
										zerochar = "000";
									} else if (lenaccessno == 4) {
										zerochar = "0000";
									} else if (lenaccessno == 5) {
										zerochar = "00000";
									} else if (lenaccessno == 6) {
										zerochar = "000000";
									} else if (lenaccessno == 7) {
										zerochar = "0000000";
									}

									New_no = viewchar + zerochar + maxaccno;
									ob.setAccessNo(New_no);
								} else {
									New_no = viewchar + maxaccno;
									ob.setAccessNo(New_no);

								}
							}
						}

					}

					log4jLogger.info("Value for Alpha Numeric :" + New_no);

				}

				else {
					log4jLogger
							.info("No suggestion Available --> New Button in Book Master ");
				}

			}

			else {
				log4jLogger
						.info("******************** No Use of this code: --> New Button in Book Master ********************************"
								+ rs);
			}

		}

		catch (Exception e) {

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

		return ob;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Common.businessutil.calaloging.CalalogingDao#findAuthorSearch(int)
	 */

	public List findEBookSearchList(EBookBean newBean) {
		log4jLogger.info("start===========findEBookSearchList=====");
		EBookBean ob = null;
		EBookBean neweBean = null;
		List finalResults = null;
		String strsql = "";

		try {

			// if(newBean.getQbbranchcode() > 0)
			// {
			// strsql = " and branch_code="+newBean.getQbbranchcode()+" ";
			// }

			log4jLogger.info("============DISPLAY Access No :"
					+ newBean.getAccessNo());
			log4jLogger
					.info("============DISPLAY Title :" + newBean.getTitle());
			con = getSession().connection();
			st = con.createStatement();

			if (newBean.getTitle() == "" || newBean.getTitle() == null) {

				newBean.setTitle("");
			}

			if (newBean.getTitle() == "" || newBean.getTitle() == null) {
				log4jLogger
						.info("==============INSIDE EBook SEARCH===================");
				// rs =
				// st.executeQuery(DataQuery.EBookSearchName+strsql+" order by title");
				// log4jLogger.info("+++++++++++++=");
				rs = st.executeQuery(DataQuery.EBookSearchName);
			} else {
				log4jLogger
						.info("==============INSIDE EBook SEARCH LIKE===============");
				// rs = st.executeQuery(DataQuery.EBookSearchName_LIKE +
				// newBean.getTitle() + "%' "+strsql+" order by title");
				rs = st.executeQuery(DataQuery.EBookSearchName_LIKE
						+ newBean.getTitle() + "%' order by title");

			}

			finalResults = new ArrayList();

			while (rs.next()) {
				neweBean = new EBookBean();
				neweBean.setAccessNo(rs.getString("access_no"));
				neweBean.setTitle(rs.getString("title"));
				neweBean.setAuthorName(rs.getString("author_name"));

				finalResults.add(neweBean);
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

	public List findEBookCallNoList(EBookBean newBean) {
		log4jLogger.info("start===========findEBookSearchList=====");
		EBookBean ob = null;
		EBookBean neweBean = null;
		List finalResults = null;
		String strsql = "";

		try {

			log4jLogger.info("============Display Call No :"
					+ newBean.getCallNo());
			// log4jLogger.info("============DISPLAY Title :"+newBean.getTitle());
			con = getSession().connection();
			st = con.createStatement();

			if (newBean.getCallNo() == "" || newBean.getCallNo() == null) {

				newBean.setCallNo("");
			}

			if (newBean.getCallNo() == "" || newBean.getCallNo() == null) {
				log4jLogger
						.info("==========Inside EBook Call No Search===================");

				rs = st.executeQuery(DataQuery.EBookCallNoSearch);
			} else {
				log4jLogger
						.info("==========Inside EBook Call No Search LIKE===============");

				rs = st.executeQuery(DataQuery.EBookCallNoSearch_LIKE
						+ newBean.getTitle() + "%' order by call_no");

			}

			finalResults = new ArrayList();

			while (rs.next()) {
				neweBean = new EBookBean();
				neweBean.setAccessNo(rs.getString("call_no"));
				neweBean.setSubName(rs.getString("subject"));

				finalResults.add(neweBean);
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

	public List findEBookSearchTitle(String name) {
		log4jLogger.info("start===========findEBookSearchTitle=====" + name);
		// subjectbean newsubBean = null;
		EBookBean newsubBean = null;

		List finalResults = null;

		try {

			String strsql = "";
			// strsql=" and document='"+doctype+"'";

			con = getSession().connection();
			st = con.createStatement();

			if (name == "") {

				// rs =
				// st.executeQuery(DataQuery.EBookSearchName+strsql+" order by title");
				rs = st.executeQuery(DataQuery.EBookSearchName);
			} else {

				rs = st.executeQuery(DataQuery.EBookSearchName_LIKE + name
						+ "%' order by title");
			}

			finalResults = new ArrayList();

			while (rs.next()) {
				newsubBean = new EBookBean();
				newsubBean.setAccessNo(rs.getString("access_no"));
				newsubBean.setTitle(rs.getString("title"));
				newsubBean.setAuthorName(rs.getString("author_name"));
				finalResults.add(newsubBean);

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

	public List findEBookSearchAuthor(String name) {
		log4jLogger.info("start===========findEBookSearchAuthor=====" + name);
		EBookSearchBean newBean = null;

		List finalResults = null;

		try {
			con = getSession().connection();
			st = con.createStatement();

			if (name == "") {

				rs = st.executeQuery(DataQuery.BOOKSEARCH_AuhtorNAME);
			} else {

				rs = st.executeQuery(DataQuery.BOOKSEARCH_NAME_AuthorLIKE
						+ name + "%' order by author_name");

			}

			finalResults = new ArrayList();

			while (rs.next()) {
				newBean = new EBookSearchBean();
				newBean.setAcode(rs.getInt("author_code"));
				newBean.setAname(rs.getString("author_name"));
				newBean.setAdesc(rs.getString("short_desc"));
				finalResults.add(newBean);

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

	public List findEBookSearchPub(String name) {
		log4jLogger.info("start===========findEBookSearchPub=====" + name);
		EBookSearchBean newBean = null;

		List finalResults = null;

		try {
			con = getSession().connection();
			st = con.createStatement();

			if (name == "") {

				rs = st.executeQuery(DataQuery.BOOKSEARCH_PUBNAME);
			} else {

				rs = st.executeQuery(DataQuery.BOOKSEARCH_NAME_PUBLIKE + name
						+ "%' order by sp_name");

			}

			finalResults = new ArrayList();

			while (rs.next()) {
				newBean = new EBookSearchBean();
				newBean.setSpcode(rs.getInt("sp_code"));
				newBean.setSpname(rs.getString("sp_name"));
				newBean.setSpdesc(rs.getString("short_desc"));
				newBean.setGdesc(rs.getString("city")); // RK on 05-10-2013

				finalResults.add(newBean);

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

	public List findEBookSearchSup(String name) {
		log4jLogger.info("start===========findEBookSearchSup=====" + name);
		EBookSearchBean newBean = null;

		List finalResults = null;

		try {
			con = getSession().connection();
			st = con.createStatement();

			if (name == "") {

				rs = st.executeQuery(DataQuery.BOOKSEARCH_SUPNAME);
			} else {

				rs = st.executeQuery(DataQuery.BOOKSEARCH_NAME_SUPLIKE + name
						+ "%' order by sp_name");

			}

			finalResults = new ArrayList();

			while (rs.next()) {
				newBean = new EBookSearchBean();
				newBean.setSpcode(rs.getInt("sp_code"));
				newBean.setSpname(rs.getString("sp_name"));
				newBean.setSpdesc(rs.getString("short_desc"));
				newBean.setGdesc(rs.getString("city")); // RK on 05-10-2013

				finalResults.add(newBean);

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

	public List findEBookSearchSubject(String name) {
		log4jLogger.info("start===========findEBookSearchSubject=====" + name);
		EBookSearchBean newBean = null;

		List finalResults = null;

		try {
			con = getSession().connection();
			st = con.createStatement();

			if (name == "") {

				rs = st.executeQuery(DataQuery.SUBJECTSEARCH_NAME);
			} else {

				rs = st.executeQuery(DataQuery.SUBJECTSEARCH_NAME_LIKE + name
						+ "%' order by sub_name");

			}

			finalResults = new ArrayList();

			while (rs.next()) {
				newBean = new EBookSearchBean();
				newBean.setScode(rs.getInt("sub_code"));
				newBean.setSname(rs.getString("sub_name"));
				newBean.setSdesc(rs.getString("short_desc"));
				newBean.setBdesc(rs.getString("Call_No"));

				finalResults.add(newBean);

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

	public List findEBookSearchCallNo(String name) {
		log4jLogger.info("start===========findEBookSearchCallNo=====" + name);
		EBookSearchBean newBean = null;

		List finalResults = null;

		try {

			con = getSession().connection();
			st = con.createStatement();

			if (name == "") {

				rs = st.executeQuery(DataQuery.SUBJECTCALL_NOSEARCH_NAME);
			} else {

				rs = st.executeQuery(DataQuery.SUBJECTSEARCH_CALL_NO_LIKE
						+ name + "%' order by sub_name");
			}

			finalResults = new ArrayList();

			while (rs.next()) {
				newBean = new EBookSearchBean();
				newBean.setScode(rs.getInt("sub_code"));
				newBean.setSname(rs.getString("sub_name"));
				newBean.setSdesc(rs.getString("short_desc"));
				newBean.setBdesc(rs.getString("Call_No"));

				finalResults.add(newBean);

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

	public List findEBookSearchDept(String name) {
		log4jLogger.info("start===========findEBookSearchDept=====" + name);
		EBookSearchBean newBean = null;

		List finalResults = null;

		try {
			con = getSession().connection();
			st = con.createStatement();

			if (name == "") {

				rs = st.executeQuery(DataQuery.DEPTSEARCH_NAME);
			} else {

				rs = st.executeQuery(DataQuery.DEPTSEARCH_NAME_LIKE + name
						+ "%' order by dept_name");

			}

			finalResults = new ArrayList();

			while (rs.next()) {
				newBean = new EBookSearchBean();
				newBean.setDcode(rs.getInt("dept_code"));
				newBean.setDname(rs.getString("dept_name"));
				newBean.setDdesc(rs.getString("short_desc"));

				finalResults.add(newBean);

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

	public EBookBean findEBookSearch(String accessNo) {
		log4jLogger.info("start===========findEBookSearch=====" + accessNo);

		EBookBean ebookBean = null;
		try {
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.EBOOKSELECT_STRING);
			Prest.setString(1, accessNo);
			rs = Prest.executeQuery();

			if (rs.next()) {
				ebookBean = new EBookBean();

				System.out.println("Inside Ebook Search");

				ebookBean.setAccessNo(rs.getString("Access_No"));
				ebookBean.setTitle(rs.getString("title"));
				ebookBean.setAuthorName(rs.getString("author_name"));
				ebookBean.setCallNo(rs.getString("call_no"));
				ebookBean.setRole(rs.getString("role"));
				ebookBean.setEdition(rs.getString("edition"));
				ebookBean.setYop(rs.getString("year_pub"));
				ebookBean.setPages(rs.getString("pages"));
				ebookBean.setSubCode(rs.getInt("sub_code"));
				ebookBean.setSubName(rs.getString("Sub_Name"));
				ebookBean.setPubCode(rs.getInt("pub_code"));
				ebookBean.setPubName(rs.getString("publisher"));

				ebookBean.setSupCode(rs.getInt("sup_code"));
				ebookBean.setSupName(rs.getString("supplier"));
				ebookBean.setDeptCode(rs.getInt("dept_code"));
				ebookBean.setDeptName(rs.getString("Dept_Name"));
				ebookBean.setBranchCode(rs.getInt("branch_code"));
				ebookBean.setDoc(rs.getString("document"));
				ebookBean.setType(rs.getString("TYPE"));
				ebookBean.setKeywords(rs.getString("keywords"));

				ebookBean.setRcDate(Security.getdate(rs
						.getString("Received_Date")));
				ebookBean.setContent(rs.getString("Content"));
				ebookBean.setPrice(rs.getString("price"));
				ebookBean.setPtype(rs.getString("gift_purchase"));
				ebookBean.setInvoiceDate(Security.getdate(rs
						.getString("invoice_Date")));
				ebookBean.setInvoiceNo(rs.getString("invoice_no"));

				ebookBean.setIsbn(rs.getString("isbn"));
				ebookBean.setUrl(rs.getString("url"));

				log4jLogger.info("EEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEe"
						+ ebookBean.getRcDate());
				log4jLogger.info("fffffffffffffffffffffffffffffffffff"
						+ ebookBean.getContent());
				log4jLogger.info("JJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJ"
						+ ebookBean.getInvoiceDate());

				/*
				 * ebookBean.setKeywords(""); ebookBean.setAddfield1("");
				 * ebookBean.setAddfield2("");
				 */
			}

		} catch (Exception e) {

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

		return ebookBean;
	}

	public int ebookSave(EBookBean ebookBean) {
		log4jLogger.info("start===========ebookSave=====");
		int count = 0;

		try {
			con = getSession().connection();
			PreparedStatement ps = con.prepareStatement("SELECT sp_code FROM sup_pub_mas WHERE SP_Name =? and SP_Type = 'SUPPLIER' limit 1");
			ps.setString(1, ebookBean.getSupName());
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				System.out.println(":::::RS VALUE:::::"+rs.getInt("sp_code"));	
				ebookBean.setSupCode(rs.getInt("sp_code"));
			}
						
			getSession().save(ebookBean);
			getSession().flush();

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

	public int ebookUpdate(EBookBean ebookBean) {
		log4jLogger.info("start===========ebookUpdate=====");
		int count = 0;
		System.out.println("getInvoiceDate-====="+ ebookBean.getInvoiceDate().toString());
				
		
			try {
				con = getSession().connection();
				PreparedStatement ps = con.prepareStatement("SELECT sp_code FROM sup_pub_mas WHERE SP_Name =? and SP_Type = 'SUPPLIER' limit 1");
				ps.setString(1, ebookBean.getSupName());
				ResultSet rs = ps.executeQuery();
				if(rs.next()){
					System.out.println(":::::RS VALUE:::::"+rs.getInt("sp_code"));	
					ebookBean.setSupCode(rs.getInt("sp_code"));
				}

			getSession().update(ebookBean);
			getSession().flush();

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

	public String findEBookInterface(String accessNo) {
		log4jLogger.info("start===========findEBookInterface=====");
		String ebook_Interface_code = "";
		try {
			con = getSession().connection();
			Prest = con
					.prepareStatement(DataQuery.EBOOKSELECT_STRING_AUTHORINTERFACE);
			Prest.setString(1, accessNo);
			rs = Prest.executeQuery();
			if (rs.next()) {
				ebook_Interface_code = rs.getString("access_no");

			}
		} catch (Exception e) {

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

		return ebook_Interface_code;
	}

	public String findEBookMas(String accessNo) {
		log4jLogger.info("start===========findEBookMas=====");
		String ebook_Mas_code = "";
		try {
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.EBOOKSELECT_STRING);
			Prest.setString(1, accessNo);
			rs = Prest.executeQuery();
			if (rs.next()) {
				ebook_Mas_code = rs.getString("access_no");

			}
		} catch (Exception e) {

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

		return ebook_Mas_code;
	}

	public String findEBookName(EBookBean ebookBean) {
		log4jLogger.info("start===========findEBookName=====");

		String EBook_accessNo = "";
		try {
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.EBookSame_Name);
			Prest.setString(1, ebookBean.getAccessNo());
			rs = Prest.executeQuery();
			if (rs.next()) {

				EBook_accessNo = rs.getString("access_no");
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

		return EBook_accessNo;
	}

	public String findEBookAccessNo(String accessNo) {
		log4jLogger.info("start===========findEBookAccessNo=====");
		String EBook_accessNo_check = "";
		try {
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.EBOOKSELECT_STRING);

			Prest.setString(1, accessNo);
			rs = Prest.executeQuery();
			if (rs.next()) {
				EBook_accessNo_check = rs.getString("access_no");

			}
		} catch (Exception e) {

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

		return EBook_accessNo_check;
	}

	public int ebookDelete(String accessNo) {
		log4jLogger.info("start=========== ebookDelete =====");

		int count = 0;
		// String ebook_delete = "";
		try {
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.EBOOK_DELETE);
			Prest.setString(1, accessNo);
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

	// --------------Suggestion Master---------------------
	public SuggestionBean findSuggestionMax() {
		log4jLogger.info("start===========findSuggestionMax=====");

		SuggestionBean sugBean = null;
		try {

			con = getSession().connection();

			Prest = con.prepareStatement(DataQuery.SuggestionSQLStringCode);
			rs = Prest.executeQuery();

			if (rs.next()) {
				sugBean = new SuggestionBean();
				sugBean.setSugNo(rs.getInt("maxno") + 1);

			}

		} catch (Exception e) {

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

		return sugBean;
	}

	public int suggestionSave(SuggestionBean sugBean) {
		log4jLogger.info("start===========suggestionSave=====");
		int count = 0;

		try {

			getSession().save(sugBean);
			getSession().flush();

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

	public SuggestionBean findSuggestionSearch(int sugNo) {
		log4jLogger.info("start===========findSuggestionSearch=====" + sugNo);

		SuggestionBean sugBean = null;
		try {
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.SuggestionSELECT_STRING);
			Prest.setInt(1, sugNo);
			rs = Prest.executeQuery();
			if (rs.next()) {
				sugBean = new SuggestionBean();

				sugBean.setSugNo(rs.getInt("request_no"));
				sugBean.setMemberCode(rs.getString("member_code"));
				sugBean.setDoc(rs.getString("request_for"));
				sugBean.setRcDate(Security.getdate(rs.getString("request_date")));
				sugBean.setSugName(rs.getString("request_details"));
				sugBean.setRemarks(rs.getString("remarks"));
			}

		} catch (Exception e) {

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

		return sugBean;
	}

	public int suggestionUpdate(SuggestionBean sugBean) {
		log4jLogger.info("start===========suggestionUpdate=====");
		int count = 0;
		try {

			getSession().update(sugBean);
			getSession().flush();

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

	public int suggestionReply(SuggestionBean sugBean) {

		try {
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.Action_taken_date_update);
			Prest.setString(1, sugBean.getActionTaken());
			Prest.setString(2, sugBean.getStatus());
			// Prest.setInt(2, 1);
			Prest.setInt(3, sugBean.getSugNo());
			Prest.executeUpdate();

		} catch (Exception e) {

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
		return 1;

	}

	public int suggestionDelete(int sugNo) {
		log4jLogger.info("start=========== suggestionDelete =====");

		int count = 0;

		try {
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.SUGGESTION_DELETE);
			Prest.setInt(1, sugNo);
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

	public int findSuggestionCount(int todays_count) {
		log4jLogger.info("start=========== findSuggestionCount =====");

		int count = 0;

		try {
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.SUGGESTION_COUNT);
			// Prest.setInt(1, todays_count);
			rs = Prest.executeQuery();
			if (rs.next()) {
				count = rs.getInt(1);
				System.out.println("todaysCount= " + count);
			}

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

	// --------------Review Master---------------------
	public ReviewBean findReviewMax() {
		log4jLogger.info("start===========findReviewMax=====");

		ReviewBean reviewBean = null;
		try {

			con = getSession().connection();

			Prest = con.prepareStatement(DataQuery.ReviewSQLStringCode);
			rs = Prest.executeQuery();

			if (rs.next()) {
				reviewBean = new ReviewBean();
				reviewBean.setReviewNo(rs.getInt("maxno") + 1);

			}

		} catch (Exception e) {

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

		return reviewBean;
	}

	public int reviewSave(ReviewBean reviewBean) {
		log4jLogger.info("start===========reviewSave=====");
		int count = 0;

		try {

			getSession().save(reviewBean);
			getSession().flush();

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

	public ReviewBean findReviewSearch(int reviewNo) {
		log4jLogger.info("start===========findReviewSearch=====" + reviewNo);

		ReviewBean reviewBean = null;
		try {
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.ReviewSELECT_STRING);
			Prest.setInt(1, reviewNo);
			rs = Prest.executeQuery();
			if (rs.next()) {
				reviewBean = new ReviewBean();

				reviewBean.setReviewNo(rs.getInt("review_no"));
				reviewBean.setAccessNo(rs.getString("access_no"));
				reviewBean.setMemberCode(rs.getString("member_code"));
				reviewBean.setRcDate(Security.getdate(rs
						.getString("review_date")));
				reviewBean.setTitle(rs.getString("review_title"));

				reviewBean.setDesc(rs.getString("review_desc"));
				reviewBean.setRating(rs.getInt("rating"));
			}
			log4jLogger.info("start===========findReviewSearch====="
					+ reviewBean.getRating());

		} catch (Exception e) {

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

		return reviewBean;
	}

	public List findReviewDisplay(String accessNo) {
		log4jLogger.info("start===========findReviewDisplay=====");
		// ReviewBean reviewBean = null;
		ReviewBean reviewBean = new ReviewBean();
		List finalResults = null;

		// SQLQuery sql =
		// getSession().createSQLQuery(DataQuery.REVIEWDISPLAY_NAME);
		// sql.setParameter("AccessNo", reviewBean.getAccessNo());

		// List list = sql.list();

		try {
			con = getSession().connection();
			// reviewBean.setAccessNo("AccessNo");
			st = con.createStatement();

			if (accessNo == "") {

				rs = st.executeQuery(DataQuery.REVIEWDISPLAY_NAME);
			}

			finalResults = new ArrayList();

			while (rs.next()) {
				reviewBean = new ReviewBean();
				reviewBean.setAccessNo(rs.getString("access_no"));
				reviewBean.setMemberCode(rs.getString("member_code"));
				reviewBean.setRcDate(Security.getdate(rs
						.getString("review_date")));
				reviewBean.setTitle(rs.getString("review_title"));

				reviewBean.setDesc(rs.getString("review_desc"));
				reviewBean.setRating(rs.getInt("rating"));

				finalResults.add(reviewBean);

			}
			log4jLogger.info("start===========findReviewDisplay====="
					+ reviewBean.getRating());

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

	public int reviewUpdate(ReviewBean reviewBean) {
		log4jLogger.info("start===========suggestionUpdate=====");
		int count = 0;
		try {

			getSession().update(reviewBean);
			getSession().flush();

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

	public int reviewDelete(int reviewNo) {
		log4jLogger.info("start=========== reviewDelete =====");

		int count = 0;

		try {
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.REVIEW_DELETE);
			Prest.setInt(1, reviewNo);
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

	// ------------------------------Subject
	// --------------------------------------

	public subjectbean findSubjectMax() {
		log4jLogger.info("start===========findSubjectMax=====");

		subjectbean newbean = null;
		try {
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.SUBJECTSQLStringCode);
			rs = Prest.executeQuery();
			if (rs.next()) {
				newbean = new subjectbean();
				newbean.setCode(rs.getInt("maxno") + 1);
			}
		} catch (Exception e) {

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

		return newbean;
	}

	public subjectbean findSubjectSearch(int code) {
		log4jLogger.info("start===========findSubjectSearch=====");
		subjectbean newBean = null;
		try {
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.SUBJECTSELECT_STRING);
			Prest.setInt(1, code);
			rs = Prest.executeQuery();
			if (rs.next()) {
				newBean = new subjectbean();
				newBean.setCode(rs.getInt(1));
				newBean.setName(rs.getString(2));
				newBean.setDesc(rs.getString(3));
				newBean.setCallno(rs.getString(4));
			}
		} catch (Exception e) {

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

		return newBean;
	}

	public int subjectUpdate(subjectbean newBean) {
		log4jLogger.info("start===========subjectUpdate=====");
		int count = 0;
		try {

			getSession().update(newBean);
			getSession().flush();

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

	public int findSubjectInterface(int code) {
		log4jLogger.info("start===========findSubjectInterface=====");
		int subject_Interface_code = 0;
		try {
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.SUBJECTDeletecheck);
			Prest.setInt(1, code);
			rs = Prest.executeQuery();
			if (rs.next()) {
				subject_Interface_code = rs.getInt("sub_Code");

			}
		} catch (Exception e) {

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

		return subject_Interface_code;
	}

	public int findSubjectMas(int code) {
		log4jLogger.info("start===========findSubjectMas=====");
		int subject_Mas_code = 0;
		try {
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.SUBJECTSELECT_STRING);
			Prest.setInt(1, code);
			rs = Prest.executeQuery();
			if (rs.next()) {
				subject_Mas_code = rs.getInt("sub_Code");

			}
		} catch (Exception e) {

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

		return subject_Mas_code;
	}

	public int subjectDelete(int code) {
		log4jLogger.info("start===========subjectDelete=====");

		int subject_Delete = 0;
		try {
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.SUBJECTDELETE_STRING);
			Prest.setInt(1, code);
			Prest.executeUpdate();
			if (rs.next()) {

			}
		} catch (Exception e) {

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

		return subject_Delete;
	}

	public int findSubjectName(subjectbean newBean) {
		log4jLogger.info("start===========findSubjectName=====");

		int sub_code = 0;
		try {
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.SUBJECTSame_Name);
			Prest.setString(1, newBean.getName());
			rs = Prest.executeQuery();
			if (rs.next()) {

				sub_code = rs.getInt("sub_Code");
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

		return sub_code;
	}

	public int subjectSave(subjectbean newBean) {
		log4jLogger.info("start===========subjectSave=====");
		int count = 0;

		try {
			/*
			 * con = getSession().connection(); Prest =
			 * con.prepareStatement(DataQuery.SUBJECTINSERT_STRING);
			 * Prest.setString(2, newBean.getName()); Prest.setString(3,
			 * newBean.getDesc()); Prest.setInt(1, newBean.getCode()); count =
			 * Prest.executeUpdate();
			 */
			getSession().save(newBean);
			getSession().flush();

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

	public List findSubjectMasName(subjectbean newBean) {
		log4jLogger.info("start===========findSubjectMasName=====");
		subjectbean ob = null;
		// ob = new subjectbean();
		subjectbean newsubBean = null;
		List finalResults = null;
		// newsubBean = new subjectbean();
		try {
			con = getSession().connection();
			st = con.createStatement();

			if (newBean.getName() == "") {

				rs = st.executeQuery(DataQuery.SUBJECTSEARCH_NAME);
			} else {

				rs = st.executeQuery(DataQuery.SUBJECTSEARCH_NAME_LIKE
						+ newBean.getName() + "%' order by sub_Name");

			}

			finalResults = new ArrayList();

			// newsubBean = new subjectbean();
			while (rs.next()) {
				newsubBean = new subjectbean();
				newsubBean.setCode(rs.getInt("sub_Code"));
				newsubBean.setName(rs.getString("sub_Name"));
				newsubBean.setDesc(rs.getString("Short_Desc"));
				newsubBean.setCallno(rs.getString("Call_No"));
				finalResults.add(newsubBean);

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

	// ----------------------------Department-----------------------------------------

	public DepartmentBean findDeptMax() {
		log4jLogger.info("start===========findDeptMax=====");

		DepartmentBean DepartmentBean = null;
		try {
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.DEPARTMENTSQLStringCode);
			rs = Prest.executeQuery();
			if (rs.next()) {
				DepartmentBean = new DepartmentBean();
				DepartmentBean.setCode(rs.getInt("maxno") + 1);
			}
		} catch (Exception e) {

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

		return DepartmentBean;
	}

	public DepartmentBean findDeptSearch(int code) {
		log4jLogger.info("start===========findDeptSearch=====");
		DepartmentBean DepartmentBean = null;
		try {

			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.DEPARTMENTSELECT_STRING);
			Prest.setInt(1, code);
			rs = Prest.executeQuery();
			if (rs.next()) {

				DepartmentBean = new DepartmentBean();
				DepartmentBean.setCode(rs.getInt(1));
				DepartmentBean.setName(rs.getString(2));
				DepartmentBean.setDesc(rs.getString(3));

			}
		} catch (Exception e) {

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

		return DepartmentBean;
	}

	public int findDeptInterface(int code) {
		log4jLogger.info("start===========findDeptInterface=====");
		int dept_Interface_code = 0;
		try { // Checking in Book_master
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.DEPARTMENTDeletecheck);
			Prest.setInt(1, code);
			rs = Prest.executeQuery();
			if (rs.next()) {
				dept_Interface_code = rs.getInt("dept_Code");

			}
			// Checking in Member_master
			Prest = con.prepareStatement(DataQuery.MemberDEPARTMENTDeletecheck);
			Prest.setInt(1, code);
			rs = Prest.executeQuery();
			if (rs.next()) {
				dept_Interface_code = rs.getInt("dept_Code");

			}

		} catch (Exception e) {

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

		return dept_Interface_code;
	}

	public int findDeptMas(int code) {
		log4jLogger.info("start===========findDeptMas=====");
		int dept_Mas_code = 0;
		try {
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.DEPARTMENTSELECT_STRING);
			Prest.setInt(1, code);
			rs = Prest.executeQuery();
			if (rs.next()) {
				dept_Mas_code = rs.getInt("dept_Code");

			}
		} catch (Exception e) {

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

		return dept_Mas_code;
	}

	public int deptDelete(int code) {
		log4jLogger.info("start===========deptDelete=====");

		int dept_Delete = 0;
		try {
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.DEPARTMENTDELETE_STRING);
			Prest.setInt(1, code);
			Prest.executeUpdate();
			if (rs.next()) {

			}
		} catch (Exception e) {

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

		return dept_Delete;
	}

	public int deptUpdate(DepartmentBean newBean) {
		log4jLogger.info("start===========deptUpdate=====");
		int count = 0;
		try {

			getSession().update(newBean);
			getSession().flush();

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

	public int findDeptName(DepartmentBean newBean) {
		log4jLogger.info("start===========findDeptName=====");

		int dept_code = 0;
		try {
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.DEPARTMENTSame_Name);
			Prest.setString(1, newBean.getName());
			rs = Prest.executeQuery();
			if (rs.next()) {

				dept_code = rs.getInt("dept_Code");
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

		return dept_code;
	}

	public int deptSave(DepartmentBean newBean) {
		log4jLogger.info("start===========deptSave=====");
		int count = 0;

		try {

			getSession().save(newBean);
			getSession().flush();
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

	public List findDeptSearch(DepartmentBean newBean) {

		log4jLogger.info("start===========findDeptSearch=====");
		subjectbean newDeptBean = null;
		List finalResults = null;

		try {
			con = getSession().connection();
			st = con.createStatement();

			if (newBean.getName() == "") {

				rs = st.executeQuery(DataQuery.DEPTSEARCH_NAME);
			} else {

				rs = st.executeQuery(DataQuery.DEPTSEARCH_NAME_LIKE
						+ newBean.getName() + "%' order by dept_name");

			}

			finalResults = new ArrayList();

			while (rs.next()) {
				newDeptBean = new subjectbean();
				newDeptBean.setCode(rs.getInt("dept_Code"));
				newDeptBean.setName(rs.getString("dept_Name"));
				newDeptBean.setDesc(rs.getString("Short_Desc"));
				finalResults.add(newDeptBean);

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

	// ----------------------------City-----------------------------------------

	public CityBean findCityMax() {
		log4jLogger.info("start===========findCityMax=====");

		CityBean CityBean = null;
		try {
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.CitySQLStringCode);
			rs = Prest.executeQuery();
			if (rs.next()) {
				CityBean = new CityBean();
				CityBean.setCode(rs.getInt("maxno") + 1);
			}
		} catch (Exception e) {

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

		return CityBean;
	}

	public CityBean findCitySearch(int code) {
		log4jLogger.info("start===========findCitySearch=====");
		CityBean CityBean = null;
		try {

			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.CitySELECT_STRING);
			Prest.setInt(1, code);
			rs = Prest.executeQuery();
			if (rs.next()) {

				CityBean = new CityBean();
				CityBean.setCode(rs.getInt(1));
				CityBean.setName(rs.getString(2));
				CityBean.setState(rs.getString(3));
				CityBean.setCountry(rs.getString(4));
				CityBean.setPincode(rs.getInt(5));
				CityBean.setDesc(rs.getString(6));

			}
		} catch (Exception e) {

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

		return CityBean;
	}

	public int findCityInterface(int code) {
		log4jLogger.info("start===========findCityInterface=====");
		int City_Interface_code = 0;
		try {
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.CityDeletecheck);
			Prest.setInt(1, code);
			rs = Prest.executeQuery();
			if (rs.next()) {
				City_Interface_code = rs.getInt("City_Code");

			}
		} catch (Exception e) {

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

		return City_Interface_code;
	}

	public int findCityMas(int code) {
		log4jLogger.info("start===========findCityMas=====");
		int City_Mas_code = 0;
		try {
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.CitySELECT_STRING);
			Prest.setInt(1, code);
			rs = Prest.executeQuery();
			if (rs.next()) {
				City_Mas_code = rs.getInt("City_Code");

			}
		} catch (Exception e) {

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

		return City_Mas_code;
	}

	public int CityDelete(int code) {
		log4jLogger.info("start===========CityDelete=====");

		int City_Delete = 0;
		try {
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.CityDELETE_STRING);
			Prest.setInt(1, code);
			Prest.executeUpdate();
			if (rs.next()) {

			}
		} catch (Exception e) {

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

		return City_Delete;
	}

	public int CityUpdate(CityBean newBean) {
		log4jLogger.info("start===========CityUpdate=====");
		int count = 0;
		try {

			getSession().update(newBean);
			getSession().flush();

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

	public int findCityName(CityBean newBean) {
		log4jLogger.info("start===========findCityName=====");

		int City_code = 0;
		try {
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.CitySame_Name);
			Prest.setString(1, newBean.getName());
			rs = Prest.executeQuery();
			if (rs.next()) {

				City_code = rs.getInt("City_Code");
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

		return City_code;
	}

	public int CitySave(CityBean newBean) {
		log4jLogger.info("start===========CitySave=====");
		int count = 0;

		try {

			getSession().save(newBean);
			getSession().flush();
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

	public List findCitySearch(String name) {

		log4jLogger.info("start===========RK==findCitySearch=====");
		CityBean ob = null;
		ob = new CityBean();
		ArrayList ser = new ArrayList();

		try {
			con = getSession().connection();
			st = con.createStatement();

			if (name == "") {
				rs = st.executeQuery(DataQuery.CitySEARCH_NAME);
			} else {

				rs = st.executeQuery(DataQuery.CitySEARCH_NAME_LIKE + name
						+ "%' order by City_name");

			}

			while (rs.next()) {

				ser.add(rs.getInt("City_Code"));
				ser.add(rs.getString("City_Name"));

				if (rs.getString("State") != null) {
					ser.add(rs.getString("State"));

				} else {
					ser.add("-");
				}
				if (rs.getString("Country") != null) {
					ser.add(rs.getString("Country"));
				} else {
					ser.add("-");
				}
				if (rs.getString("Pincode") != null) {
					ser.add(rs.getInt("Pincode"));
				} else {
					ser.add("-");
				}
				if (rs.getString("Short_Desc") != null) {
					ser.add(rs.getString("Short_Desc"));
				} else {
					ser.add("-");
				}

			}

			ob.setAl(ser);

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

		return ser;
	}

	// -----------------------------------Member-----------------------------------------------

	public int findMemberIdChange(String code, String changeCode) {

		int result = 0;

		try {
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.MEMBERVIEWSELECT_STRING);
			Prest.setString(1, changeCode);
			rs = Prest.executeQuery();
			if (rs.next()) {
				result = 1;
			}
			if (result == 0) {
				try {
					int i = 0;
					String value = "";
					for (i = 1; i <= 11; i++) {

						if (i == 1) {
							value = "updateMemberMas";
						} else if (i == 2) {
							value = "updateIssueMas";
						} else if (i == 3) {
							value = "updateIssueHistory";
						} else if (i == 4) {
							value = "updateTransMas";
						} else if (i == 5) {
							value = "updatePaymentClearance";
						} else if (i == 6) {
							value = "updateLibTimeManagement";
						} else if (i == 7) {
							value = "updateLoginMas";
						} else if (i == 8) {
							value = "updateMiscellaneousMas";
						} else if (i == 9) {
							value = "updateRenewalTime";
						} else if (i == 10) {
							value = "updateEntryLog";
						} else if (i == 11) {
							value = "updateReturnLog";
						}

						String queryUpdate = getSession().getNamedQuery(value)
								.getQueryString();
						SQLQuery sqlUpdate = getSession().createSQLQuery(
								queryUpdate);
						sqlUpdate.setParameter("NewMemberCode", changeCode);
						sqlUpdate.setParameter("OldMemberCode", code);
						sqlUpdate.executeUpdate();

					}

				} catch (Exception e) {
					log4jLogger.info(e);
				}

				/*
				 * con = getSession().connection(); Prest =
				 * con.prepareStatement(DataQuery.updateMemberMas);
				 * Prest.setString(1,changeCode); Prest.setString(2,code);
				 * Prest.executeUpdate();
				 * 
				 * Prest = con.prepareStatement(DataQuery.updateIssueMas);
				 * Prest.setString(1,changeCode); Prest.setString(2,code);
				 * Prest.executeUpdate();
				 * 
				 * Prest = con.prepareStatement(DataQuery.updateIssueHistory);
				 * Prest.setString(1,changeCode); Prest.setString(2,code);
				 * Prest.executeUpdate();
				 * 
				 * Prest = con.prepareStatement(DataQuery.updateTransMas);
				 * Prest.setString(1,changeCode); Prest.setString(2,code);
				 * Prest.executeUpdate();
				 * 
				 * Prest =
				 * con.prepareStatement(DataQuery.updatePaymentClearance);
				 * Prest.setString(1,changeCode); Prest.setString(2,code);
				 * Prest.executeUpdate();
				 * 
				 * Prest =
				 * con.prepareStatement(DataQuery.updateLibTimeManagement);
				 * Prest.setString(1,changeCode); Prest.setString(2,code);
				 * Prest.executeUpdate();
				 * 
				 * Prest = con.prepareStatement(DataQuery.updateLoginMas);
				 * Prest.setString(1,changeCode); Prest.setString(2,code);
				 * Prest.executeUpdate();
				 * 
				 * Prest =
				 * con.prepareStatement(DataQuery.updateMiscellaneousMas);
				 * Prest.setString(1,changeCode); Prest.setString(2,code);
				 * Prest.executeUpdate();
				 * 
				 * Prest = con.prepareStatement(DataQuery.updateRenewalTime);
				 * Prest.setString(1,changeCode); Prest.setString(2,code);
				 * Prest.executeUpdate();
				 * 
				 * Prest = con.prepareStatement(DataQuery.updateEntryLog);
				 * Prest.setString(1,changeCode); Prest.setString(2,code);
				 * Prest.executeUpdate();
				 * 
				 * Prest = con.prepareStatement(DataQuery.updateReturnLog);
				 * Prest.setString(1,changeCode); Prest.setString(2,code);
				 * Prest.executeUpdate();
				 */

			}

		} catch (Exception e) {

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
		return result;
	}

	public MemberBean findMemberSearch(String code) {
		log4jLogger.info("start===========findMemberSearch=====");
		MemberBean memberBean = new MemberBean();

		try {
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.MEMBERVIEWSELECT_STRING);
			Prest.setString(1, code);
			rs = Prest.executeQuery();

			if (rs.next()) {

				memberBean.setCode(rs.getString("Member_code"));
				memberBean.setName(rs.getString("Member_name"));
				memberBean
						.setBdate(Security.getdate(rs.getString("Birth_date")));
				memberBean.setEdate(Security.getdate(rs
						.getString("Enroll_date")));
				memberBean.setExdate(Security.getdate(rs
						.getString("Expiry_date")));
				memberBean.setDamount(rs.getString("Deposit_amount"));
				memberBean.setSex(rs.getString("Sex"));
				memberBean.setMadd1(rs.getString("Member_add1"));
				memberBean.setMadd2(rs.getString("Member_add2"));
				memberBean.setMcity(rs.getString("Member_city"));
				memberBean.setMstate(rs.getString("Member_state"));
				memberBean.setMpincode(rs.getString("Member_pincode"));
				memberBean.setMphone(rs.getString("Member_phone"));
				memberBean.setMemail(rs.getString("Member_email"));
				memberBean.setRemarks(rs.getString("Remarks"));
				memberBean.setProfile(rs.getString("Profile"));
				// memberBean.setPhoto(rs.getString("Photo"));
				memberBean.setPhoto1(rs.getBytes("photo"));
				memberBean.setSecurity(rs.getString("SLock"));

				memberBean.setCyear(rs.getString("Cyear"));
				memberBean.setDecode(rs.getString("desig_name"));
				memberBean.setDeptcode(rs.getString("dname"));
				memberBean.setCoursecode(rs.getString("cname"));
				memberBean.setPhoto(rs.getString("cmajor"));
				memberBean.setGroupcode(rs.getString("gname"));
			}

		} catch (Exception e) {

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

		return memberBean;
	}

	public List findMemberMasSearch(MemberBean newBean) {
		log4jLogger.info("start===========findMemberMasSearch=====");
		MemberBean newmemberBean = null;
		List finalResults = null;

		try {
			con = getSession().connection();
			st = con.createStatement();

			if (newBean.getName() == "") {

				rs = st.executeQuery(DataQuery.MEMBERSEARCH_STRING
						+ " order by member_name,member_code");
			} else {

				rs = st.executeQuery(DataQuery.MEMBERSEARCH_STRING_LIKE
						+ newBean.getName()
						+ "%' order by member_name,member_code");

			}

			finalResults = new ArrayList();

			while (rs.next()) {
				newmemberBean = new MemberBean();
				newmemberBean.setCode(rs.getString("member_Code"));
				newmemberBean.setName(rs.getString("member_Name"));
				newmemberBean.setDecode(rs.getString("designation_code"));
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

	public List findDeptMasSearch(MemberBean newBean) {
		log4jLogger.info("start===========findDeptMasSearch=====");
		subjectbean newDeptBean = null;
		List finalResults = null;

		try {
			con = getSession().connection();
			st = con.createStatement();

			if (newBean.getName() == "") {

				rs = st.executeQuery(DataQuery.DEPTSEARCH_NAME);
			} else {

				rs = st.executeQuery(DataQuery.DEPTSEARCH_NAME_LIKE
						+ newBean.getName() + "%' order by dept_name");

			}

			finalResults = new ArrayList();

			while (rs.next()) {
				newDeptBean = new subjectbean();
				newDeptBean.setCode(rs.getInt("dept_Code"));
				newDeptBean.setName(rs.getString("dept_Name"));
				newDeptBean.setDesc(rs.getString("Short_Desc"));
				finalResults.add(newDeptBean);

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

	public List findDesignationSearch(MemberBean newBean) {
		log4jLogger.info("start===========findDesignationSearch=====");
		subjectbean newDesigBean = null;
		List finalResults = null;

		try {
			con = getSession().connection();
			st = con.createStatement();

			if (newBean.getName() == "") {

				rs = st.executeQuery(DataQuery.DESIGNATIONSEARCH_NAME);
			} else {

				rs = st.executeQuery(DataQuery.DESIGNATIONSEARCH_NAME_LIKE
						+ newBean.getName() + "%' order by desig_code");

			}

			finalResults = new ArrayList();

			while (rs.next()) {
				newDesigBean = new subjectbean();
				newDesigBean.setCode(rs.getInt("desig_Code"));
				newDesigBean.setName(rs.getString("desig_Name"));
				newDesigBean.setDesc(rs.getString("Short_Desc"));
				finalResults.add(newDesigBean);

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

	public List findGroupSearch(MemberBean newBean) {
		log4jLogger.info("start===========findGroupSearch=====");
		subjectbean newDesigBean = null;
		List finalResults = null;

		try {
			con = getSession().connection();
			st = con.createStatement();

			if (newBean.getName() == "") {

				rs = st.executeQuery(DataQuery.GROUPSEARCH_NAME);
			} else {

				rs = st.executeQuery(DataQuery.GROUPSEARCH_NAME_LIKE
						+ newBean.getName() + "%' order by group_code");

			}

			finalResults = new ArrayList();

			while (rs.next()) {
				newDesigBean = new subjectbean();
				newDesigBean.setCode(rs.getInt("group_Code"));
				newDesigBean.setName(rs.getString("group_Name"));
				newDesigBean.setDesc(rs.getString("remarks"));
				finalResults.add(newDesigBean);

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

	public List findStaffSearch(MemberBean newBean) {
		log4jLogger.info("start===========findGroupSearch=====");
		subjectbean newDesigBean = null;
		List finalResults = null;

		try {
			con = getSession().connection();
			st = con.createStatement();

			if (newBean.getName() == "") {

				rs = st.executeQuery(DataQuery.CounterStaffCode);
			} else {

				rs = st.executeQuery(DataQuery.CounterStaffCode_LIKE
						+ newBean.getName() + "%' ");

			}

			finalResults = new ArrayList();

			while (rs.next()) {
				newDesigBean = new subjectbean();
				// newDesigBean.setCode(rs.getInt("group_Code"));
				newDesigBean.setName(rs.getString("staffcode"));
				// newDesigBean.setDesc(rs.getString("remarks"));
				finalResults.add(newDesigBean);

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

	public List findCourseSearch(MemberBean newBean) {
		log4jLogger.info("start===========findCourseSearch=====");
		subjectbean newDesigBean = null;
		List finalResults = null;

		try {
			con = getSession().connection();
			st = con.createStatement();

			if (newBean.getName() == "") {

				rs = st.executeQuery(DataQuery.COURSESEARCH_NAME);
			} else {

				rs = st.executeQuery(DataQuery.COURSESEARCH_NAME_LIKE
						+ newBean.getName()
						+ "%' order by course_name,course_major");

			}

			finalResults = new ArrayList();

			while (rs.next()) {
				newDesigBean = new subjectbean();
				newDesigBean.setCode(rs.getInt("course_Code"));
				newDesigBean.setName(rs.getString("course_Name"));
				newDesigBean.setDesc(rs.getString("course_major"));
				finalResults.add(newDesigBean);

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

	public List findCitySearch(MemberBean newBean) {
		log4jLogger.info("start===========findCitySearch=====");
		subjectbean newDesigBean = null;
		List finalResults = null;

		try {
			con = getSession().connection();
			st = con.createStatement();

			if (newBean.getName() == "") {

				rs = st.executeQuery(DataQuery.CITYSEARCH_NAME);
			} else {

				rs = st.executeQuery(DataQuery.CITYSEARCH_NAME_LIKE
						+ newBean.getName() + "%' order by city_name");

			}

			finalResults = new ArrayList();

			while (rs.next()) {
				newDesigBean = new subjectbean();
				newDesigBean.setCode(rs.getInt("Pincode"));
				newDesigBean.setName(rs.getString("City_Name"));
				newDesigBean.setDesc(rs.getString("State"));
				finalResults.add(newDesigBean);

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

	public int findIssueMasCheck(String code) {
		log4jLogger.info("start===========findIssueMasCheck=====");
		int issue_mas_check = 0;
		try {
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.ISSUEMASCHECK_STRING);
			Prest.setString(1, code);
			rs = Prest.executeQuery();

			if (rs.next()) {
				issue_mas_check = 1;

			}
		} catch (Exception e) {

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

		return issue_mas_check;
	}

	public int memberDelete(String code) {
		log4jLogger.info("start===========memberDelete=====");
		int count = 0;
		try {
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.MEMBERDELETE_STRING);
			Prest.setString(1, code);
			Prest.executeUpdate();

			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.LOGIN_DELETE_STRING);
			Prest.setString(1, code);
			Prest.executeUpdate();

			if (rs.next()) {

			}
		} catch (Exception e) {

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

	public int memberUpdate(MemberBean newBean) {
		log4jLogger.info("start===========memberUpdate=====");
		int count = 0;
		try {

			String savedcode = "";
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.MEMBERDESIG_CODE);
			Prest.setString(1, newBean.getDecode());
			rs = Prest.executeQuery();

			if (rs.next()) {
				savedcode = rs.getString("desig_Code");
			}

			String savedecode = "";
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.MEMBERDEPT_CODE);
			Prest.setString(1, newBean.getDeptcode());
			rs = Prest.executeQuery();

			if (rs.next()) {
				savedecode = rs.getString("Dept_code");
			}

			String savecoursecode = "";
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.MEMBERCOURSE_CODE);

			String part1 = "", part2 = "";
			String string = newBean.getCoursecode().toString();
			String[] parts = string.split("-");

			if (parts.length > 1) {
				part1 = parts[0].toString().trim(); // 004
				part2 = parts[1].toString().trim(); // 034556
			} else {
				part1 = "NIL";
				part2 = "NIL";
			}

			Prest.setString(1, part1);
			Prest.setString(2, part2);
			// Prest.setString(1, newBean.getCoursecode());
			rs = Prest.executeQuery();

			if (rs.next()) {
				savecoursecode = rs.getString("course_code");
			}

			String savegroupcode = "";
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.MEMBERGROUP_CODE);
			Prest.setString(1, newBean.getGroupcode());
			rs = Prest.executeQuery();

			if (rs.next()) {
				savegroupcode = rs.getString("group_code");
			}

			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.MEMBERUPDATE_STRING);
			Prest.setString(1, newBean.getName());
			Prest.setString(2, (newBean.getBdate()));
			Prest.setString(3, (newBean.getEdate()));
			Prest.setString(4, (newBean.getExdate()));
			Prest.setString(5, newBean.getDamount());
			Prest.setString(6, savedcode);
			Prest.setString(7, newBean.getSex());
			Prest.setString(8, newBean.getMadd1());
			Prest.setString(9, newBean.getMadd2());
			Prest.setString(10, newBean.getMcity());
			Prest.setString(11, newBean.getMstate());
			Prest.setString(12, newBean.getMpincode());
			Prest.setString(13, newBean.getMphone());
			Prest.setString(14, newBean.getMemail());
			Prest.setString(15, savedecode);
			Prest.setString(16, savecoursecode);
			Prest.setString(17, savegroupcode);
			Prest.setString(18, newBean.getRemarks());
			Prest.setString(19, newBean.getProfile());
			// Prest.setString(20, newBean.getPhoto());
			// Prest.setBinaryStream(20,fin, (int)(imgfile.length()));
			Prest.setString(20, newBean.getSecurity());
			Prest.setString(21, newBean.getCyear());
			Prest.setString(22, newBean.getCode());

			count = Prest.executeUpdate();

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

		return count;
	}

	public int findMemberCheck(String code) {
		log4jLogger.info("start===========findMemberCheck=====");
		int member_check = 0;

		try {
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.MEMBERVIEWSELECT_STRING);
			Prest.setString(1, code);
			rs = Prest.executeQuery();

			if (rs.next()) {

				member_check = 1;
			}

		} catch (Exception e) {

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

		return member_check;
	}

	public int memberSave(MemberBean newBean) {

		log4jLogger.info("start===========memberSave=====");
		int count = 0;
		try {

			String savedcode = "";
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.MEMBERDESIG_CODE);
			Prest.setString(1, newBean.getDecode());
			rs = Prest.executeQuery();

			if (rs.next()) {
				savedcode = rs.getString("desig_Code");
			}

			if (rs != null) {
				rs.close();
			}

			String savedecode = "";
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.MEMBERDEPT_CODE);
			Prest.setString(1, newBean.getDeptcode());
			rs = Prest.executeQuery();

			if (rs.next()) {
				savedecode = rs.getString("Dept_code");
			}

			if (rs != null) {
				rs.close();
			}
			String savecoursecode = "";
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.MEMBERCOURSE_CODE);

			String part1 = "", part2 = "";
			String string = newBean.getCoursecode().toString();
			String[] parts = string.split("-");

			if (parts.length > 1) {
				part1 = parts[0].toString().trim(); // 004
				part2 = parts[1].toString().trim(); // 034556
			} else {
				part1 = "NIL";
				part2 = "NIL";
			}

			Prest.setString(1, part1);
			Prest.setString(2, part2);
			rs = Prest.executeQuery();

			if (rs.next()) {
				savecoursecode = rs.getString("course_code");
			}
			if (rs != null) {
				rs.close();
			}

			String savegroupcode = "";
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.MEMBERGROUP_CODE);
			Prest.setString(1, newBean.getGroupcode());
			rs = Prest.executeQuery();

			if (rs.next()) {
				savegroupcode = rs.getString("group_code");
			}

			if (rs != null) {
				rs.close();
			}
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.MEMBERINSERT_STRING);
			Prest.setString(1, newBean.getName());
			Prest.setString(2, (newBean.getBdate()));
			Prest.setString(3, (newBean.getEdate()));
			Prest.setString(4, (newBean.getExdate()));
			Prest.setString(5, newBean.getDamount());
			Prest.setString(6, savedcode);
			Prest.setString(7, newBean.getSex());
			Prest.setString(8, newBean.getMadd1());
			Prest.setString(9, newBean.getMadd2());
			Prest.setString(10, newBean.getMcity());
			Prest.setString(11, newBean.getMstate());
			Prest.setString(12, newBean.getMpincode());
			Prest.setString(13, newBean.getMphone());
			Prest.setString(14, newBean.getMemail());
			Prest.setString(15, savedecode);
			Prest.setString(16, savecoursecode);
			Prest.setString(17, savegroupcode);
			Prest.setString(18, newBean.getRemarks());
			Prest.setString(19, newBean.getProfile());
			// Prest.setString(20, newBean.getPhoto());
			// Prest.setBinaryStream(20, (InputStream)fis,
			// (int)(image.length()));
			// Prest.setBinaryStream(20,fin, (int)(imgfile.length())); BY RK
			Prest.setString(20, newBean.getSecurity());
			Prest.setString(21, newBean.getCyear());
			Prest.setString(22, newBean.getCode());

			count = Prest.executeUpdate();
			
			java.util.StringTokenizer stz=new java.util.StringTokenizer(newBean.getProfile(),";");
			
			while(stz.hasMoreTokens() ){
			String Profile=stz.nextToken();
			
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.MEMBER_Profile_Details_Insert);
			Prest.setString(1,newBean.getCode());
			Prest.setString(2,newBean.getName());
			Prest.setString(3,Profile);
			Prest.setString(4,newBean.getMemail());
			Prest.setString(5,newBean.getMphone());
			Prest.executeUpdate();
			
			}
			
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.LOGIN_INSERT_STRING);
			Prest.setString(1, newBean.getCode());
			Prest.setString(2, newBean.getCode());
			Prest.setString(3, newBean.getName());
			Prest.setString(4, "7");
			Prest.setString(5, "YES");
			Prest.setInt(6, 0);
			Prest.setString(7, "");
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

		return count;
	}

	public int findBulkPhotoMemberCode(String code, int flag) {
		int count = 0;
		try {
			con = getSession().connection();
			Prest = con
					.prepareStatement(DataQuery.BulkPhotoUploadMemberCodeSave);

			Prest.setString(1, code);
			Prest.setInt(2, flag);

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

	public int findBulkPhotoUploadDelete() {
		int count = 0;

		try {
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.BulkPhotoUploadDelete);
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

	public MemberBean findMemberClear(String code) {
		log4jLogger.info("start===========findMemberClear=====");
		MemberBean memberBean = new MemberBean();
		
		try {
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.MEMBERCLEAR_PHOTO);
			Prest.setString(1, code);
			Prest.executeUpdate();
			}
		catch(Exception e)
			{
				e.printStackTrace();
			}
		finally {
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
		
		return memberBean;
	}
	
	public int MemberPhotoSave(PhotoBean bean) {

		log4jLogger.info("start===========MemberPhotoSave====="
				+ bean.getMemberid());
		int count = 0;
		try {
			// For Photo by R.Karuppaiah

			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.MEMBER_PHOTO_UPDATE_STRING);

			Prest.setBytes(1, bean.getPhoto());
			Prest.setString(2, bean.getMemberid());

			count = Prest.executeUpdate();

			log4jLogger.info("End===========MemberPhotoSave=====");

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

	// --------------------BOOK--------------------

	public bookbean findBookSearch(String accno, String doctype) {
		log4jLogger
				.info("start===========findBookSearch AccessNo=====" + accno);
		log4jLogger.info("start===========findBookSearch Document====="
				+ doctype);

		bookbean newbean = null;
		String TempDoc = null;
		if (doctype == "BOOKNONBOOK") {
			TempDoc = doctype;
			doctype = "BOOK";
		}
		try {

			String strsql = "";

			strsql = "and document='" + doctype + "'";
			String nonbooknewno = findNewAccNoLoad("NON BOOK", "");
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.SELECT_ACCESS + strsql);
			Prest.setString(1, accno);

			rs = Prest.executeQuery();

			if (rs.next()) {
				newbean = new bookbean();
				if (TempDoc != "BOOKNONBOOK") {
					newbean.setAccessNo(rs.getString("access_no"));
					newbean.setDoc(rs.getString(25));
					newbean.setAddfield1(rs.getString(54));
				} else {
					newbean.setAccessNo(nonbooknewno);
					newbean.setDoc("NON BOOK");
					newbean.setAddfield1(rs.getString("access_no"));
				}
				newbean.setTitle(rs.getString(2));
				newbean.setAuthor(rs.getString(3));
				newbean.setCallNo(rs.getString(4));
				newbean.setOtherTitle(rs.getString(5));
				newbean.setRole(rs.getString(6));
				newbean.setStateRes(rs.getString(7));
				newbean.setEdition(rs.getString(8));
				newbean.setLanguage(rs.getString(9));
				newbean.setPlace(rs.getString(10));
				newbean.setYop(rs.getString(11));
				newbean.setPages(rs.getString(12));
				newbean.setSize(rs.getString(13));
				newbean.setIllustration(rs.getString(14));
				newbean.setIsbn(rs.getString(15));
				newbean.setBprice(rs.getString(16));
				newbean.setCopies(rs.getInt(17));
				newbean.setScript(rs.getString(18));
				newbean.setLocation(rs.getString(19));
				newbean.setAvail(rs.getString(20));
				newbean.setSubName(rs.getString("sub_name"));
				newbean.setSubCode(rs.getInt(21));
				newbean.setPubName(rs.getString("Publisher"));
				newbean.setPubCode(rs.getInt(23));
				newbean.setSupName(rs.getString("supplier"));
				newbean.setSupCode(rs.getInt(22));
				newbean.setBranchName(rs.getString("Branch_Name"));
				newbean.setBranchCode(rs.getInt(24));
				newbean.setType(rs.getString(26));
				newbean.setPhysical(rs.getString(27));
				newbean.setBinding(rs.getString(28));
				newbean.setKeywords(rs.getString(29));
				newbean.setNotes(rs.getString(30));
				newbean.setSummary(rs.getString(31));
				newbean.setBibliography(rs.getString(32));
				newbean.setContents(rs.getString(33));
				newbean.setVolumeNo(rs.getString(34));
				newbean.setPartNo(rs.getString(35));
				newbean.setVolumeTitle(rs.getString(36));
				newbean.setVolumeRes(rs.getString(37));
				newbean.setCorAut(rs.getString(38));
				newbean.setCorAdd(rs.getString(39));
				newbean.setSerAut(rs.getString(40));
				newbean.setSerName(rs.getString(41));
				newbean.setSerTitle(rs.getString(42));
				newbean.setIssn(rs.getString(43));
				newbean.setMeeting(rs.getString(44));
				newbean.setSponsor(rs.getString(45));
				newbean.setVenue(rs.getString(46));
				newbean.setRcDate(Security.getdate(rs.getString(47)));
				newbean.setInvNo(rs.getString(48));
				newbean.setInvoiceDate(Security.getdate(rs.getString(49)));
				newbean.setCurrency(rs.getString(50));
				newbean.setBcost(rs.getString(51));
				newbean.setAcceptPrice(rs.getString(52));
				newbean.setDiscount(rs.getDouble(53));
				newbean.setAddfield2(rs.getString(55));
				newbean.setAddfield3(rs.getString(56));
				newbean.setAddfield4(rs.getString(57));
				newbean.setDeptName(rs.getString("Dept_Name"));
				newbean.setDeptCode(rs.getInt(58));
				newbean.setPurchaseType(rs.getString(59));
				newbean.setBudName(rs.getString("Bud_Head"));
				newbean.setBudgetCode(rs.getInt(60));
				newbean.setOtherDate(Security.getdate(rs.getString(61)));

			}

		} catch (Exception e) {
			log4jLogger.info("Error is: " + e);
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

		return newbean;
	}

	public String findIssueCheck(String accno) {
		log4jLogger.info("start===========findIssueCheck=====");
		String issue_accno = "";

		try {
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.SELECT_ISSUE);
			Prest.setString(1, accno);
			rs = Prest.executeQuery();
			if (rs.next()) {
				issue_accno = rs.getString("access_no");
			}

		} catch (Exception e) {

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

		return issue_accno;
	}

	public String findIssueHistoryCheck(String accno) {
		log4jLogger.info("start===========findIssueHistoryCheck=====");
		String issue_history_accno = "";

		try {
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.SELECT_HISTORY);
			Prest.setString(1, accno);
			rs = Prest.executeQuery();
			if (rs.next()) {
				issue_history_accno = rs.getString("access_no");
			}

		} catch (Exception e) {

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

		return issue_history_accno;
	}

	public String findReserveMasCheck(String accno) {
		log4jLogger.info("start===========findReserveMasCheck=====");

		String reserve_accno = "";

		try {
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.SELECT_RESERVE);
			Prest.setString(1, accno);
			rs = Prest.executeQuery();
			if (rs.next()) {
				reserve_accno = rs.getString("access_no");
			}

		} catch (Exception e) {

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

		return reserve_accno;
	}

	public int findDeleteBook(String accno) {
		log4jLogger.info("start===========findDeleteBook=====");

		int count = 0;

		try {

			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.DELETE_BOOK);
			Prest.setString(1, accno);
			Prest.executeUpdate();

			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.INSERT_DELETE);
			Prest.setString(1, accno);
			Prest.executeUpdate();

			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.DELETE_INTERFACE);
			Prest.setString(1, accno);
			Prest.executeUpdate();

		} catch (Exception e) {

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

	public int findDeleteHistory(String accno) {
		log4jLogger.info("start===========findDeleteHistory=====");

		int count = 0;

		try {
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.DELETE_HISTORY);
			Prest.setString(1, accno);
			Prest.executeUpdate();

		} catch (Exception e) {

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

	public int findDeleteReserve(String accno) {
		log4jLogger.info("start===========findDeleteReserve=====");

		int count = 0;

		try {

			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.DELETE_RESERVE);
			Prest.setString(1, accno);
			Prest.executeUpdate();

		} catch (Exception e) {

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

	public List findBookSearchTitle(String name, String doctype) {
		log4jLogger.info("start===========findBookSearchTitle=====" + name);
		subjectbean newsubBean = null;

		List finalResults = null;

		try {

			String strsql = "";
			strsql = " and document='" + doctype + "'";

			con = getSession().connection();
			st = con.createStatement();

			if (name == "") {

				rs = st.executeQuery(DataQuery.BOOKSEARCH_NAME + strsql
						+ " order by title,access_no");
			} else {

				rs = st.executeQuery(DataQuery.BOOKSEARCH_NAME_LIKE + strsql
						+ " and title like '" + name
						+ "%' order by title,access_no");
			}

			finalResults = new ArrayList();

			while (rs.next()) {
				newsubBean = new subjectbean();
				newsubBean.setEmail(rs.getString("access_no"));
				newsubBean.setName(rs.getString("title"));
				newsubBean.setDesc(rs.getString("author_name"));
				finalResults.add(newsubBean);

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

	public List findBookSearchTitleauth(String name, String auth, String doctype) {
		log4jLogger.info("start===========findBookSearchTitle=====" + name);
		subjectbean newsubBean = null;

		List finalResults = null;

		try {

			String strsql = "";
			strsql = " and document='" + doctype + "'";

			if (!auth.equals(null) && !auth.equals("")) {
				strsql = strsql + " and author_name like '" + auth + "%'";
			}

			con = getSession().connection();
			st = con.createStatement();

			if (name == "" && auth == "") {

				rs = st.executeQuery(DataQuery.BOOKSEARCH_NAME + strsql
						+ " order by title,access_no");
			} else {

				rs = st.executeQuery(DataQuery.BOOKSEARCH_NAME_LIKE + strsql
						+ " and title like '" + name
						+ "%' order by title,access_no");
			}

			finalResults = new ArrayList();

			while (rs.next()) {
				newsubBean = new subjectbean();
				newsubBean.setEmail(rs.getString("access_no"));
				newsubBean.setName(rs.getString("title"));
				newsubBean.setDesc(rs.getString("author_name"));
				finalResults.add(newsubBean);

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

	public List findBookSearchAuthor(String name) {
		log4jLogger.info("start===========findBookSearchAuthor=====" + name);
		BookSearchBean newBean = null;

		List finalResults = null;

		try {
			con = getSession().connection();
			st = con.createStatement();

			if (name == "") {

				rs = st.executeQuery(DataQuery.BOOKSEARCH_AuhtorNAME);
			} else {

				rs = st.executeQuery(DataQuery.BOOKSEARCH_NAME_AuthorLIKE
						+ name + "%' order by author_name");

			}

			finalResults = new ArrayList();

			while (rs.next()) {
				newBean = new BookSearchBean();
				newBean.setAcode(rs.getInt("author_code"));
				newBean.setAname(rs.getString("author_name"));
				newBean.setAdesc(rs.getString("short_desc"));
				finalResults.add(newBean);

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

	public List findBookSearchPub(String name) {
		log4jLogger.info("start===========findBookSearchPub=====" + name);
		BookSearchBean newBean = null;

		List finalResults = null;

		try {
			con = getSession().connection();
			st = con.createStatement();

			if (name == "") {

				rs = st.executeQuery(DataQuery.BOOKSEARCH_PUBNAME);
			} else {

				rs = st.executeQuery(DataQuery.BOOKSEARCH_NAME_PUBLIKE + name
						+ "%' order by sp_name");

			}

			finalResults = new ArrayList();

			while (rs.next()) {
				newBean = new BookSearchBean();
				newBean.setSpcode(rs.getInt("sp_code"));
				newBean.setSpname(rs.getString("sp_name"));
				newBean.setSpdesc(rs.getString("short_desc"));
				newBean.setGdesc(rs.getString("city")); // RK on 05-10-2013

				finalResults.add(newBean);

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

	public List findBookSearchSubject(String name) {
		log4jLogger.info("start===========findBookSearchSubject=====" + name);
		BookSearchBean newBean = null;

		List finalResults = null;

		try {
			con = getSession().connection();
			st = con.createStatement();

			if (name == "") {

				rs = st.executeQuery(DataQuery.SUBJECTSEARCH_NAME);
			} else {

				rs = st.executeQuery(DataQuery.SUBJECTSEARCH_NAME_LIKE + name
						+ "%' order by sub_name");

			}

			finalResults = new ArrayList();

			while (rs.next()) {
				newBean = new BookSearchBean();
				newBean.setScode(rs.getInt("sub_code"));
				newBean.setSname(rs.getString("sub_name"));
				newBean.setSdesc(rs.getString("short_desc"));
				newBean.setBdesc(rs.getString("Call_No"));

				finalResults.add(newBean);

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

	public List findBookSearchCallNo(String name) {
		log4jLogger.info("start===========findBookSearchCallNo=====" + name);
		BookSearchBean newBean = null;

		List finalResults = null;

		try {

			con = getSession().connection();
			st = con.createStatement();

			if (name == "") {

				rs = st.executeQuery(DataQuery.SUBJECTCALL_NOSEARCH_NAME);
			} else {

				rs = st.executeQuery(DataQuery.SUBJECTSEARCH_CALL_NO_LIKE
						+ name + "%' order by sub_name");
			}

			finalResults = new ArrayList();

			while (rs.next()) {
				newBean = new BookSearchBean();
				newBean.setScode(rs.getInt("sub_code"));
				newBean.setSname(rs.getString("sub_name"));
				newBean.setSdesc(rs.getString("short_desc"));
				newBean.setBdesc(rs.getString("Call_No"));

				finalResults.add(newBean);

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

	public List findBookSearchBranch(String name) {
		log4jLogger.info("start===========findBookSearchBranch=====" + name);
		BookSearchBean newBean = null;

		List finalResults = null;

		try {
			con = getSession().connection();
			st = con.createStatement();

			if (name == "") {

				rs = st.executeQuery(DataQuery.BOOKSEARCH_BRANCHNAME);
			} else {

				rs = st.executeQuery(DataQuery.BOOKSEARCH_NAME_BRANCHLIKE
						+ name + "%' order by branch_name");

			}

			finalResults = new ArrayList();

			while (rs.next()) {
				newBean = new BookSearchBean();
				newBean.setBcode(rs.getInt("branch_code"));
				newBean.setBname(rs.getString("branch_name"));
				newBean.setBdesc(rs.getString("short_desc"));

				finalResults.add(newBean);

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

	public List findBookSearchDept(String name) {
		log4jLogger.info("start===========findBookSearchDept=====" + name);
		BookSearchBean newBean = null;

		List finalResults = null;

		try {
			con = getSession().connection();
			st = con.createStatement();

			if (name == "") {

				rs = st.executeQuery(DataQuery.DEPTSEARCH_NAME);
			} else {

				rs = st.executeQuery(DataQuery.DEPTSEARCH_NAME_LIKE + name
						+ "%' order by dept_name");

			}

			finalResults = new ArrayList();

			while (rs.next()) {
				newBean = new BookSearchBean();
				newBean.setDcode(rs.getInt("dept_code"));
				newBean.setDname(rs.getString("dept_name"));
				newBean.setDdesc(rs.getString("short_desc"));

				finalResults.add(newBean);

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

	public List findBookSearchBudget(String name) {
		log4jLogger.info("start===========findBookSearchBudget=====" + name);
		BookSearchBean newBean = null;

		List finalResults = null;

		try {
			con = getSession().connection();
			st = con.createStatement();

			if (name == "") {

				rs = st.executeQuery(DataQuery.BOOKSEARCH_BUDGETNAME);
			} else {

				rs = st.executeQuery(DataQuery.BOOKSEARCH_NAME_BUDGETLIKE
						+ name + "%' order by bud_head");

			}

			finalResults = new ArrayList();

			while (rs.next()) {
				newBean = new BookSearchBean();
				newBean.setBudcode(rs.getInt("bud_code"));
				newBean.setBudhead(rs.getString("bud_head"));
				newBean.setBudamt(rs.getDouble("bud_amount"));

				finalResults.add(newBean);

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

	public List findBookSearchKey(String name) {
		log4jLogger.info("start===========findBookSearchKey=====" + name);
		BookSearchBean newBean = null;

		List finalResults = null;

		try {
			con = getSession().connection();
			st = con.createStatement();

			if (name == "") {

				rs = st.executeQuery(DataQuery.BOOKSEARCH_KEYTNAME);
			} else {

				rs = st.executeQuery(DataQuery.BOOKSEARCH_NAME_KEYLIKE + name
						+ "%' order by keyword_name");

			}

			finalResults = new ArrayList();

			while (rs.next()) {
				newBean = new BookSearchBean();
				newBean.setKcode(rs.getInt("keyword_code"));
				newBean.setKname(rs.getString("keyword_name"));
				newBean.setKdesc(rs.getString("short_desc"));

				finalResults.add(newBean);

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

	public List findBookSearchSupplier(String name) {
		log4jLogger.info("start===========findBookSearchSupplier=====" + name);
		BookSearchBean newBean = null;

		List finalResults = null;

		try {
			con = getSession().connection();
			st = con.createStatement();

			if (name == "") {

				rs = st.executeQuery(DataQuery.BOOKSEARCH_SUPNAME);
			} else {

				rs = st.executeQuery(DataQuery.BOOKSEARCH_NAME_SUPLIKE + name
						+ "%' order by SP_name");

			}

			finalResults = new ArrayList();

			while (rs.next()) {
				newBean = new BookSearchBean();
				newBean.setSpcode(rs.getInt("sp_code"));
				newBean.setSpname(rs.getString("sp_name"));
				newBean.setSpdesc(rs.getString("short_desc"));

				finalResults.add(newBean);

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

	public int findBookSubCode(String subName) {
		log4jLogger.info("start===========findBookSubCode=====" + subName);

		int sub = 0;

		try {

			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.BOOKSEARCH_CODE_SUBJECT);
			Prest.setString(1, subName);
			rs = Prest.executeQuery();

			if (rs.next()) {
				sub = rs.getInt("sub_code");
			} else {
				con = getSession().connection();
				Prest = con.prepareStatement(DataQuery.SUBJECTSQLStringCode);
				rs = Prest.executeQuery();
				if (rs.next()) {
					sub = rs.getInt("maxno") + 1;

					con = getSession().connection();
					Prest = con
							.prepareStatement(DataQuery.BOOKSEARCH_CODE_SUBJECT_SAVE);
					Prest.setString(2, subName);
					Prest.setInt(1, sub);
					Prest.executeUpdate();

				}
			}

		} catch (Exception e) {

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

		return sub;
	}

	public int findBookBranchCode(String branchName) {
		log4jLogger
				.info("start===========findBookBranchCode=====" + branchName);

		int branch = 0;

		try {

			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.BOOKSEARCH_CODE_BRANCH);
			Prest.setString(1, branchName);
			rs = Prest.executeQuery();

			if (rs.next()) {
				branch = rs.getInt("branch_code");
			} else {
				con = getSession().connection();
				Prest = con
						.prepareStatement(DataQuery.BOOKSEARCH_CODE_BRANCH_MAX);
				rs = Prest.executeQuery();
				if (rs.next()) {
					branch = rs.getInt("branch_max") + 1;

					con = getSession().connection();
					Prest = con
							.prepareStatement(DataQuery.BOOKSEARCH_CODE_BRANCH_SAVE);
					Prest.setString(2, branchName);
					Prest.setInt(1, branch);
					Prest.executeUpdate();

				}
			}

		} catch (Exception e) {

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

		return branch;
	}

	public int findBookDeptCode(String deptName) {
		log4jLogger.info("start===========findBookDeptCode=====" + deptName);

		int dept = 0;

		try {

			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.DEPARTMENTSame_Name);
			Prest.setString(1, deptName);
			rs = Prest.executeQuery();

			if (rs.next()) {
				dept = rs.getInt("dept_code");
			} else {
				con = getSession().connection();
				Prest = con.prepareStatement(DataQuery.DEPARTMENTSQLStringCode);
				rs = Prest.executeQuery();
				if (rs.next()) {
					dept = rs.getInt("maxno") + 1;

					con = getSession().connection();
					Prest = con.prepareStatement(DataQuery.DEPARTMENTINSERT_STRING);		
					Prest.setString(2, deptName);
					Prest.setString(3, "");
					Prest.setInt(1, dept);
					Prest.executeUpdate();

				}
			}

		} catch (Exception e) {

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

		return dept;
	}

	public int findBookSupplierCode(String supName) {
		log4jLogger.info("start===========findBookSupplierCode=====" + supName);

		int sup = 0;

		try {

			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.BOOKSEARCH_CODE_SUPPLIER);
			Prest.setString(1, supName);
			rs = Prest.executeQuery();

			if (rs.next()) {
				sup = rs.getInt("sp_code");
			} else {
				con = getSession().connection();
				Prest = con.prepareStatement(DataQuery.PUBSUBSQLStringCode);
				rs = Prest.executeQuery();
				if (rs.next()) {
					sup = rs.getInt("maxno") + 1;

					con = getSession().connection();
					Prest = con
							.prepareStatement(DataQuery.BOOKSEARCH_CODE_SUPPLIER_SAVE);
					Prest.setString(2, supName);
					Prest.setString(3, "SUPPLIER");
					Prest.setInt(1, sup);
					Prest.executeUpdate();

				}
			}

		} catch (Exception e) {

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

		return sup;
	}

	public int findBookPubCode(String pubName) {
		log4jLogger.info("start===========findBookPubCode=====" + pubName);

		int pub = 0;

		try {

			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.BOOKSEARCH_CODE_PUB);
			Prest.setString(1, pubName);
			rs = Prest.executeQuery();

			if (rs.next()) {
				pub = rs.getInt("sp_code");
			} else {
				con = getSession().connection();
				Prest = con.prepareStatement(DataQuery.PUBSUBSQLStringCode);
				rs = Prest.executeQuery();
				if (rs.next()) {
					pub = rs.getInt("maxno") + 1;

					con = getSession().connection();
					Prest = con
							.prepareStatement(DataQuery.BOOKSEARCH_CODE_SUPPLIER_SAVE);
					Prest.setString(2, pubName);
					Prest.setString(3, "PUBLISHER");
					Prest.setInt(1, pub);
					Prest.executeUpdate();

				}
			}

		} catch (Exception e) {

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

		return pub;
	}

	public int findBookSave(bookbean newbean) {
		log4jLogger.info("start===========findBookSave====="
				+ newbean.getAccessNo());

		int count = 0;

		try {

			String currentDateString_recedate[] = Security.TextDate_Insert(
					newbean.getRcDate()).split("-");
			int cy = Integer.parseInt(currentDateString_recedate[0]);
			int cm = Integer.parseInt(currentDateString_recedate[1]);
			int cd = Integer.parseInt(currentDateString_recedate[2]);
			java.util.Calendar bir = new java.util.GregorianCalendar();
			bir.set(cy, cm, cd);

			String Rece_date = currentDateString_recedate[0] + "-"
					+ currentDateString_recedate[1] + "-"
					+ currentDateString_recedate[2];

			String currentDateString_invodate[] = Security.TextDate_Insert(
					newbean.getInvoiceDate()).split("-");
			int cy1 = Integer.parseInt(currentDateString_invodate[0]);
			int cm1 = Integer.parseInt(currentDateString_invodate[1]);
			int cd1 = Integer.parseInt(currentDateString_invodate[2]);
			java.util.Calendar invo = new java.util.GregorianCalendar();
			invo.set(cy1, cm1, cd1);

			String Invo_date = currentDateString_invodate[0] + "-"
					+ currentDateString_invodate[1] + "-"
					+ currentDateString_invodate[2];

			String currentDateString_otherdate[] = Security.TextDate_Insert(
					newbean.getOtherDate()).split("-");
			int cy2 = Integer.parseInt(currentDateString_otherdate[0]);
			int cm2 = Integer.parseInt(currentDateString_otherdate[1]);
			int cd2 = Integer.parseInt(currentDateString_otherdate[2]);
			java.util.Calendar other = new java.util.GregorianCalendar();
			other.set(cy2, cm2, cd2);

			String other_date = currentDateString_otherdate[0] + "-"
					+ currentDateString_otherdate[1] + "-"
					+ currentDateString_otherdate[2];

			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.INSERT_STRING);
			Prest.setString(1, newbean.getAccessNo());
			Prest.setString(2, newbean.getTitle());
			Prest.setString(3, newbean.getAuthor());
			Prest.setString(4, newbean.getCallNo());
			Prest.setString(5, newbean.getOtherTitle());
			Prest.setString(6, newbean.getRole());
			Prest.setString(7, newbean.getStateRes());
			Prest.setString(8, newbean.getEdition());
			Prest.setString(9, newbean.getLanguage());
			Prest.setString(10, newbean.getPlace());
			Prest.setString(11, newbean.getYop());
			Prest.setString(12, newbean.getPages());
			Prest.setString(13, newbean.getSize());
			Prest.setString(14, newbean.getIllustration());
			Prest.setString(15, newbean.getIsbn());
			Prest.setString(16, newbean.getBprice());
			Prest.setInt(17, newbean.getCopies());
			Prest.setString(18, newbean.getScript());
			Prest.setString(19, newbean.getLocation());
			Prest.setString(20, newbean.getAvail());
			Prest.setInt(21, newbean.getSubCode());
			Prest.setInt(22, newbean.getSupCode());
			Prest.setInt(23, newbean.getPubCode());
			Prest.setInt(24, newbean.getBranchCode());
			Prest.setString(25, newbean.getDoc());
			System.out.println("::::::TYPE:::::"+ newbean.getType());
			Prest.setString(26, newbean.getType());
			Prest.setString(27, newbean.getPhysical());
			Prest.setString(28, newbean.getBinding());
			Prest.setString(29, newbean.getKeywords());
			Prest.setString(30, newbean.getNotes());
			Prest.setString(31, newbean.getSummary());
			Prest.setString(32, newbean.getBibliography());
			Prest.setString(33, newbean.getContents());
			Prest.setString(34, newbean.getVolumeNo());
			Prest.setString(35, newbean.getPartNo());
			Prest.setString(36, newbean.getVolumeTitle());
			Prest.setString(37, newbean.getVolumeRes());
			Prest.setString(38, newbean.getCorAut());
			Prest.setString(39, newbean.getCorAdd());
			Prest.setString(40, newbean.getSerAut());
			Prest.setString(41, newbean.getSerName());
			Prest.setString(42, newbean.getSerTitle());
			Prest.setString(43, newbean.getIssn());
			Prest.setString(44, newbean.getMeeting());
			Prest.setString(45, newbean.getSponsor());
			Prest.setString(46, newbean.getVenue());
			Prest.setString(47, Rece_date);
			Prest.setString(48, newbean.getInvNo());
			Prest.setString(49, Invo_date);
			Prest.setString(50, newbean.getCurrency());
			Prest.setString(51, newbean.getBcost());
			Prest.setString(52, newbean.getAcceptPrice());
			Prest.setDouble(53, newbean.getDiscount());
			Prest.setString(54, newbean.getAddfield1());
			Prest.setString(55, newbean.getAddfield2());
			Prest.setString(56, newbean.getAddfield3());
			Prest.setString(57, newbean.getAddfield4());
			Prest.setInt(58, newbean.getDeptCode());
			Prest.setString(59, newbean.getPurchaseType());
			Prest.setInt(60, newbean.getBudgetCode());
			Prest.setString(61, other_date);
			count = Prest.executeUpdate();
			
			if(newbean.getKeywords().equals(""))
			{
				con = getSession().connection();
				Prest = con.prepareStatement(DataQuery.INSERT_STRING_Profile_Book);
				Prest.setString(1, newbean.getAccessNo());
				Prest.setString(2, newbean.getTitle());
				Prest.setString(3,Rece_date);
				Prest.setString(4,"");
				Prest.executeUpdate();
			}
			else
			{
				java.util.StringTokenizer stz1=new java.util.StringTokenizer(newbean.getKeywords(),";");
					
				while(stz1.hasMoreTokens() ){
				String keywords=stz1.nextToken();
				
				con = getSession().connection();
				Prest = con.prepareStatement(DataQuery.INSERT_STRING_Profile_Book);
				Prest.setString(1, newbean.getAccessNo());
				Prest.setString(2, newbean.getTitle());
				Prest.setString(3,Rece_date);
				Prest.setString(4,keywords);
				Prest.executeUpdate();
				
				}
			}
				
			log4jLogger.info("start===========InterfaceSave====="+ newbean.getAccessNo());		
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.SELECT_INTERFACE);
			Prest.setString(1, newbean.getAccessNo());
			rs = Prest.executeQuery();

			String accno = "";

			if (rs.next()) {

				accno = rs.getString("access_no");

				con = getSession().connection();
				Prest = con.prepareStatement(DataQuery.DELETE_INTERFACE);
				Prest.setString(1, accno);
				Prest.executeUpdate();
			}

			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.AUTHORSQLStringCode);
			rs = Prest.executeQuery();
			int a_code = 0;
			if (rs.next()) {
				a_code = rs.getInt("maxno") + 1;
			}

			StringBuffer sb = new StringBuffer();
			java.util.StringTokenizer auth = new java.util.StringTokenizer(
					newbean.getAuthor(), ";");
			// int c=0;

			while (auth.hasMoreTokens()) {

				String nameAuthor = auth.nextToken();

				con = getSession().connection();
				Prest = con.prepareStatement(DataQuery.AUTHORSame_Name);
				Prest.setString(1, nameAuthor);
				rs = Prest.executeQuery();
				if (rs.next()) {
					sb.append(";" + rs.getInt("Author_code") + ";");
				} else {

					con = getSession().connection();
					Prest = con.prepareStatement(DataQuery.AUTHORINSERT_STRING);
					Prest.setInt(1, a_code);
					Prest.setString(2, nameAuthor);
					Prest.setString(3, "");
					// Prest.setInt(3,c);
					Prest.setString(4, "");

					Prest.executeUpdate();
					sb.append(";" + a_code + ";");

					// c++;
					a_code++;
				}

			}

			// java.util.StringTokenizer stz=new
			// java.util.StringTokenizer(newbean.getauthorvalue(),";");
			java.util.StringTokenizer stz = new java.util.StringTokenizer(
					sb.toString(), ";");
			int c = 1;

			while (stz.hasMoreTokens()) {
				int CodeAuthor = Integer.parseInt(stz.nextToken());

				con = getSession().connection();
				Prest = con.prepareStatement(DataQuery.INSERT_INTERFACE);
				Prest.setInt(1, CodeAuthor);
				Prest.setString(2, newbean.getAccessNo());
				Prest.setInt(3, c);
				Prest.setString(4, newbean.getRole());
				Prest.setString(5, newbean.getDoc());
				Prest.executeUpdate();
				c++;
			}

			log4jLogger.info("start===========SortBookSave====="
					+ newbean.getAccessNo());
			// String book_Access_No=new String();
			String acc = newbean.getAccessNo().toUpperCase();
			int N1 = 0;// ascii value+length.
			int N2 = 0;// length.
			int N3 = 0;// Number only.
			// book_Access_No=access_no;
			char temp1[] = new char[10];
			char temp2[] = new char[10];
			// char s[]=access_no.toCharArray();
			char s[] = acc.toCharArray();
			for (int i = 0; i < s.length; i++) {
				if ((s[i] >= 'A' || s[i] >= 'a')
						&& (s[i] <= 'Z' || s[i] <= 'z')) {
					temp1[i] += s[i];
				} else {
					temp2[i] += s[i];
				}
			}
			// String t1=String.valueOf(temp1).trim();
			String t2 = String.valueOf(temp2).trim();
			int as = 0;
			int i = 0;
			if (i >= 'A' || i <= 'Z') {
				for (i = 'A'; i <= 'Z'; i++) {
					for (int j = 0; j < temp1.length; j++) {
						if (temp1[j] == i) {
							as += i;

						}
					}
				}
			}
			if (i >= 'a' || i <= 'z') {
				for (i = 'a'; i <= 'z'; i++) {
					for (int j = 0; j < temp1.length; j++) {
						if (temp1[j] == i) {
							as += i;

						}
					}
				}
			}

			N1 = as + newbean.getAccessNo().length();
			N2 = newbean.getAccessNo().length();
			N3 = Integer.parseInt(t2);

			try {
				Prest = con.prepareStatement(DataQuery.INSERT_DELETE);
				Prest.setString(1, newbean.getAccessNo());
				Prest.executeUpdate();
				Prest = con.prepareStatement(DataQuery.INSERT_SORT);
				Prest.setString(1, newbean.getAccessNo());
				Prest.setInt(2, N1);
				Prest.setInt(3, N2);
				Prest.setInt(4, N3);
				Prest.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

			}
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.BUDGET_UpdateAll + "('"
					+ newbean.getBudgetCode() + "')");
			rs = Prest.executeQuery();

		} catch (Exception e) {

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

	public String findBookSaveInterfaceCheck(bookbean newbean) {
		log4jLogger.info("Interface===========findBookSaveInterfaceCheck====="
				+ newbean.getAccessNo());

		String accno = "";

		try {

			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.SELECT_INTERFACE);
			Prest.setString(1, newbean.getAccessNo());
			rs = Prest.executeQuery();

			if (rs.next()) {

				accno = rs.getString("access_no");

				con = getSession().connection();
				Prest = con.prepareStatement(DataQuery.DELETE_INTERFACE);
				Prest.setString(1, accno);
				Prest.executeUpdate();
			}

			java.util.StringTokenizer stz = new java.util.StringTokenizer(
					newbean.getAuthor(), ";");
			int c = 1;
			while (stz.hasMoreTokens()) {
				int CodeAuthor = Integer.parseInt(stz.nextToken());
				con = getSession().connection();
				Prest = con.prepareStatement(DataQuery.INSERT_INTERFACE);
				Prest.setInt(1, CodeAuthor);
				Prest.setString(2, newbean.getAccessNo());
				Prest.setInt(3, c);
				Prest.setString(4, newbean.getRole());
				Prest.setString(5, newbean.getDoc());
				Prest.executeUpdate();
				c++;
			}

		} catch (Exception e) {

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

		return accno;
	}

	public int findBookUpdate(bookbean newbean) {
		log4jLogger.info("start===========findBookUpdate====="
				+ newbean.getAccessNo());

		int count = 0;
		int isschk = 0;
		String dtype = "";
		try {
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.BOOKSUPDATE_STATUS_CHECK);
			Prest.setString(1, newbean.getAccessNo());
			rs = Prest.executeQuery();
			if (rs.next()) {
				dtype = rs.getString("doc_type");
				isschk = 1;
			}

			String currentDateString_recedate[] = Security.TextDate_Insert(
					newbean.getRcDate()).split("-");
			int cy = Integer.parseInt(currentDateString_recedate[0]);
			int cm = Integer.parseInt(currentDateString_recedate[1]);
			int cd = Integer.parseInt(currentDateString_recedate[2]);
			java.util.Calendar bir = new java.util.GregorianCalendar();
			bir.set(cy, cm, cd);

			String Rece_date = currentDateString_recedate[0] + "-"
					+ currentDateString_recedate[1] + "-"
					+ currentDateString_recedate[2];

			String currentDateString_invodate[] = Security.TextDate_Insert(
					newbean.getInvoiceDate()).split("-");
			int cy1 = Integer.parseInt(currentDateString_invodate[0]);
			int cm1 = Integer.parseInt(currentDateString_invodate[1]);
			int cd1 = Integer.parseInt(currentDateString_invodate[2]);
			java.util.Calendar invo = new java.util.GregorianCalendar();
			invo.set(cy1, cm1, cd1);

			String Invo_date = currentDateString_invodate[0] + "-"
					+ currentDateString_invodate[1] + "-"
					+ currentDateString_invodate[2];

			String currentDateString_otherdate[] = Security.TextDate_Insert(
					newbean.getOtherDate()).split("-");
			int cy2 = Integer.parseInt(currentDateString_otherdate[0]);
			int cm2 = Integer.parseInt(currentDateString_otherdate[1]);
			int cd2 = Integer.parseInt(currentDateString_otherdate[2]);
			java.util.Calendar other = new java.util.GregorianCalendar();
			other.set(cy2, cm2, cd2);

			String other_date = currentDateString_otherdate[0] + "-"
					+ currentDateString_otherdate[1] + "-"
					+ currentDateString_otherdate[2];

			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.UPDATE_STRING);
			Prest.setString(1, newbean.getAccessNo());
			Prest.setString(2, newbean.getTitle());
			Prest.setString(3, newbean.getAuthor());
			Prest.setString(4, newbean.getCallNo());
			Prest.setString(5, newbean.getOtherTitle());
			Prest.setString(6, newbean.getRole());
			Prest.setString(7, newbean.getStateRes());
			Prest.setString(8, newbean.getEdition());
			Prest.setString(9, newbean.getLanguage());
			Prest.setString(10, newbean.getPlace());
			Prest.setString(11, newbean.getYop());
			Prest.setString(12, newbean.getPages());
			Prest.setString(13, newbean.getSize());
			Prest.setString(14, newbean.getIllustration());
			Prest.setString(15, newbean.getIsbn());
			Prest.setString(16, newbean.getBprice());
			Prest.setInt(17, newbean.getCopies());
			Prest.setString(18, newbean.getScript());
			Prest.setString(19, newbean.getLocation());
			if (isschk > 0) {
				Prest.setString(20, "ISSUED");
			} else {
				Prest.setString(20, newbean.getAvail());
			}
			Prest.setInt(21, newbean.getSubCode());
			Prest.setInt(22, newbean.getSupCode());
			Prest.setInt(23, newbean.getPubCode());
			Prest.setInt(24, newbean.getBranchCode());
			if (isschk > 0) {
				Prest.setString(25, dtype);
			} else {
				Prest.setString(25, newbean.getDoc());
			}
			Prest.setString(26, newbean.getType());
			Prest.setString(27, newbean.getPhysical());
			Prest.setString(28, newbean.getBinding());
			Prest.setString(29, newbean.getKeywords());
			Prest.setString(30, newbean.getNotes());
			Prest.setString(31, newbean.getSummary());
			Prest.setString(32, newbean.getBibliography());
			Prest.setString(33, newbean.getContents());
			Prest.setString(34, newbean.getVolumeNo());
			Prest.setString(35, newbean.getPartNo());
			Prest.setString(36, newbean.getVolumeTitle());
			Prest.setString(37, newbean.getVolumeRes());
			Prest.setString(38, newbean.getCorAut());
			Prest.setString(39, newbean.getCorAdd());
			Prest.setString(40, newbean.getSerAut());
			Prest.setString(41, newbean.getSerName());
			Prest.setString(42, newbean.getSerTitle());
			Prest.setString(43, newbean.getIssn());
			Prest.setString(44, newbean.getMeeting());
			Prest.setString(45, newbean.getSponsor());
			Prest.setString(46, newbean.getVenue());
			Prest.setString(47, Rece_date);
			Prest.setString(48, newbean.getInvNo());
			Prest.setString(49, Invo_date);
			Prest.setString(50, newbean.getCurrency());
			Prest.setString(51, newbean.getBcost());
			Prest.setString(52, newbean.getAcceptPrice());
			Prest.setDouble(53, newbean.getDiscount());
			Prest.setString(54, newbean.getAddfield1());
			Prest.setString(55, newbean.getAddfield2());
			Prest.setString(56, newbean.getAddfield3());
			Prest.setString(57, newbean.getAddfield4());
			Prest.setInt(58, newbean.getDeptCode());
			Prest.setString(59, newbean.getPurchaseType());
			Prest.setInt(60, newbean.getBudgetCode());
			Prest.setString(61, other_date);
			Prest.setString(62, newbean.getAccessNo());
			count = Prest.executeUpdate();

			log4jLogger.info("start===========InterfaceSave====="
					+ newbean.getAccessNo());
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.SELECT_INTERFACE);
			Prest.setString(1, newbean.getAccessNo());
			rs = Prest.executeQuery();

			String accno = "";

			if (rs.next()) {

				accno = rs.getString("access_no");

				con = getSession().connection();
				Prest = con.prepareStatement(DataQuery.DELETE_INTERFACE);
				Prest.setString(1, accno);
				Prest.executeUpdate();
			}

			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.AUTHORSQLStringCode);
			rs = Prest.executeQuery();
			int a_code = 0;
			if (rs.next()) {
				a_code = rs.getInt("maxno") + 1;
			}

			StringBuffer sb = new StringBuffer();
			java.util.StringTokenizer auth = new java.util.StringTokenizer(
					newbean.getAuthor(), ";");
			// int c=0;

			while (auth.hasMoreTokens()) {

				String nameAuthor = auth.nextToken();

				con = getSession().connection();
				Prest = con.prepareStatement(DataQuery.AUTHORSame_Name);
				Prest.setString(1, nameAuthor);
				rs = Prest.executeQuery();
				if (rs.next()) {
					sb.append(";" + rs.getInt("Author_code") + ";");
				} else {

					con = getSession().connection();
					Prest = con.prepareStatement(DataQuery.AUTHORINSERT_STRING);
					Prest.setInt(1, a_code);
					Prest.setString(2, nameAuthor);
					Prest.setString(3, "");
					// Prest.setInt(3,c);
					Prest.setString(4, "");

					Prest.executeUpdate();
					sb.append(";" + a_code + ";");

					// c++;
					a_code++;
				}

			}

			// java.util.StringTokenizer stz=new
			// java.util.StringTokenizer(newbean.getauthorvalue(),";");
			java.util.StringTokenizer stz = new java.util.StringTokenizer(
					sb.toString(), ";");
			int c = 1;

			while (stz.hasMoreTokens()) {
				int CodeAuthor = Integer.parseInt(stz.nextToken());

				con = getSession().connection();
				Prest = con.prepareStatement(DataQuery.INSERT_INTERFACE);
				Prest.setInt(1, CodeAuthor);
				Prest.setString(2, newbean.getAccessNo());
				Prest.setInt(3, c);
				Prest.setString(4, newbean.getRole());
				Prest.setString(5, newbean.getDoc());
				Prest.executeUpdate();
				c++;
			}

			log4jLogger.info("start===========SortBookSave====="
					+ newbean.getAccessNo());
			// String book_Access_No=new String();
			String acc = newbean.getAccessNo().toUpperCase();
			int N1 = 0;// ascii value+length.
			int N2 = 0;// length.
			int N3 = 0;// Number only.
			// book_Access_No=access_no;
			char temp1[] = new char[10];
			char temp2[] = new char[10];
			// char s[]=access_no.toCharArray();
			char s[] = acc.toCharArray();
			for (int i = 0; i < s.length; i++) {
				if ((s[i] >= 'A' || s[i] >= 'a')
						&& (s[i] <= 'Z' || s[i] <= 'z')) {
					temp1[i] += s[i];
				} else {
					temp2[i] += s[i];
				}
			}
			// String t1=String.valueOf(temp1).trim();
			String t2 = String.valueOf(temp2).trim();
			int as = 0;
			int i = 0;
			if (i >= 'A' || i <= 'Z') {
				for (i = 'A'; i <= 'Z'; i++) {
					for (int j = 0; j < temp1.length; j++) {
						if (temp1[j] == i) {
							as += i;

						}
					}
				}
			}
			if (i >= 'a' || i <= 'z') {
				for (i = 'a'; i <= 'z'; i++) {
					for (int j = 0; j < temp1.length; j++) {
						if (temp1[j] == i) {
							as += i;

						}
					}
				}
			}

			N1 = as + newbean.getAccessNo().length();
			N2 = newbean.getAccessNo().length();
			N3 = Integer.parseInt(t2);

			try {
				Prest = con.prepareStatement(DataQuery.INSERT_DELETE);
				Prest.setString(1, newbean.getAccessNo());
				Prest.executeUpdate();
				Prest = con.prepareStatement(DataQuery.INSERT_SORT);
				Prest.setString(1, newbean.getAccessNo());
				Prest.setInt(2, N1);
				Prest.setInt(3, N2);
				Prest.setInt(4, N3);
				Prest.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

			}

			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.BUDGET_UpdateAll + "('"
					+ newbean.getBudgetCode() + "')");
			rs = Prest.executeQuery();
		} catch (Exception e) {

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

	public List findAccNoList(BookTransferBean newbean) {
		log4jLogger.info("start===========findAccessNoList=====");

		log4jLogger.info("From AccessNo       :" + newbean.getAccess_no());
		log4jLogger.info("To AccessNo         :" + newbean.getToaccess_no());

		BookTransferBean refBean = null;
		List<Object> result = new ArrayList<Object>();

		if (Security.IsNumeric(newbean.getAccess_no())
				&& Security.IsNumeric(newbean.getToaccess_no())) {
			log4jLogger
					.info("Integer value Integer value Integer value Integer value Integer value ");
			SQLQuery sql = getSession().createSQLQuery(
					DataQuery.AccessNo_Select_Integer);

			sql.setParameter("fromAccNo", newbean.getAccess_no());
			sql.setParameter("toAccNo", newbean.getToaccess_no());
			sql.setParameter("document", newbean.getDocument());

			List list = sql.list();

			for (int i = 0; i < list.size(); i++) {
				Object[] obj = (Object[]) list.get(i);
				refBean = new BookTransferBean();
				refBean.setAccess_no(obj[0].toString());

				refBean.setTitle(obj[1].toString());
				result.add(refBean);

			}
		} else {
			SplitAccessNoBean sab = new SplitAccessNoBean();
			String strNum = newbean.getAccess_no();

			StringBuffer num = new StringBuffer(strNum.length() / 2);
			StringBuffer str = new StringBuffer(strNum.length() / 2);

			boolean prevIsDigit = false;
			boolean isDigit = false;
			char ch;

			for (int i = 0; i < strNum.length(); i++) {
				ch = strNum.charAt(i);

				isDigit = Character.isDigit(ch);

				if (isDigit) {

					if (prevIsDigit == false && num.length() > 0)
						num.append(' ');

					num.append(ch);

				} else {

					if (prevIsDigit)
						str.append(' ');

					str.append(ch);

				}

				prevIsDigit = isDigit;

			}

			String first = num.toString();
			String first1 = str.toString();

			sab.setSplitedFirstIntAccNo(first.toString());
			sab.setSplitedFirstCharAccNo(first1.toString());
			sab.setStringFirstCharCount(first1.toString().length() + 1);

			String strNum1 = newbean.getToaccess_no();

			StringBuffer num1 = new StringBuffer(strNum1.length() / 2);
			StringBuffer str1 = new StringBuffer(strNum1.length() / 2);

			boolean prevIsDigit1 = false;
			boolean isDigit1 = false;
			char ch1;

			for (int i = 0; i < strNum1.length(); i++) {
				ch1 = strNum1.charAt(i);

				isDigit1 = Character.isDigit(ch1);

				if (isDigit1) {

					if (prevIsDigit1 == false && num1.length() > 0)
						num1.append(' ');

					num1.append(ch1);

				} else {

					if (prevIsDigit1)
						str1.append(' ');

					str1.append(ch1);

				}

				prevIsDigit1 = isDigit1;

			}
			String second = num1.toString();
			String second1 = str1.toString();

			sab.setSplitedSecondIntAccNo(second.toString());
			sab.setSplitedSecondCharAccNo(second1.toString());
			sab.setStringSecondCharCount(second1.toString().length() + 1);

			log4jLogger
					.info("getSplitedFirstCharAccNo1111111111111111111111111111111"
							+ sab.getSplitedFirstCharAccNo());
			log4jLogger
					.info("getSplitedFirstIntAccNo2222222222222222222222222222222"
							+ sab.getSplitedFirstIntAccNo());
			log4jLogger
					.info("getSplitedSecondCharAccNo3333333333333333333333333333333"
							+ sab.getSplitedSecondCharAccNo());
			log4jLogger
					.info("getSplitedSecondIntAccNo4444444444444444444444444444444"
							+ sab.getSplitedSecondIntAccNo());
			log4jLogger
					.info("getStringFirstCharCount5555555555555555555555555555555"
							+ sab.getStringFirstCharCount());
			log4jLogger
					.info("getStringSecondCharCount6666666666666666666666666666665"
							+ sab.getStringSecondCharCount());

			log4jLogger
					.info("Char Value Char Value Char Value Char Value Char Value Char Value Char Value ");

			SQLQuery sql = getSession().createSQLQuery(
					DataQuery.AccessNo_Select_Char + " AND ACCESS_NO LIKE '"
							+ sab.getSplitedFirstCharAccNo()
							+ "%' AND CAST(SUBSTRING(access_no,"
							+ sab.getStringFirstCharCount()
							+ ") AS SIGNED) BETWEEN '"
							+ sab.getSplitedFirstIntAccNo() + "' AND '"
							+ sab.getSplitedSecondIntAccNo()
							+ "' ORDER BY  LPAD(access_no, 20, '0'); ");
			sql.setParameter("document", newbean.getDocument());

			List list = sql.list();

			for (int i = 0; i < list.size(); i++) {
				Object[] obj = (Object[]) list.get(i);
				refBean = new BookTransferBean();
				refBean.setAccess_no(obj[0].toString());

				refBean.setTitle(obj[1].toString());
				result.add(refBean);

			}
		}
		return result;
	}

	public List findTransAccNoList(BookTransferBean newbean) {
		log4jLogger.info("start===========findAccessNoList=====");
		String strsql = "";

		log4jLogger.info("From AccessNo       :" + newbean.getAccess_no());
		log4jLogger.info("To AccessNo         :" + newbean.getToaccess_no());
		log4jLogger.info("To AccessNo         :" + newbean.getdeptName());
		String dept = newbean.getdeptName();
		BookTransferBean refBean = null;
		SQLQuery sql = null;

		List<Object> result = new ArrayList<Object>();
		if (!dept.equalsIgnoreCase("NO")) {
			sql = getSession().createSQLQuery(
					DataQuery.AccessNo_Transfer_Dept_Select);
			sql.setParameter("Dept_code", newbean.getdeptName());
		} else {
			sql = getSession().createSQLQuery(
					DataQuery.AccessNo_Transfer_Select);
		}

		// sql.setParameter("fromAccNo",newbean.getAccess_no());
		// sql.setParameter("toAccNo",newbean.getToaccess_no());

		List list = sql.list();

		for (int i = 0; i < list.size(); i++) {
			Object[] obj = (Object[]) list.get(i);
			refBean = new BookTransferBean();
			refBean.setAccess_no(obj[0].toString());

			refBean.setTitle(obj[1].toString());
			result.add(refBean);

		}

		return result;
	}

	// --------------------Course--------------------

	public CourseBean findCourseMax() {
		CourseBean courseBean = null;
		try {
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.COURSESQLStringCode);
			rs = Prest.executeQuery();
			if (rs.next()) {
				courseBean = new CourseBean();
				courseBean.setCode(rs.getInt("maxno") + 1);
			}
		} catch (Exception e) {

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

		return courseBean;
	}

	public CourseBean findCourseSearch(int code) {
		CourseBean courseBean = null;
		try {
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.COURSESELECT_STRING);
			Prest.setInt(1, code);
			rs = Prest.executeQuery();
			if (rs.next()) {
				courseBean = new CourseBean();
				courseBean.setCode(rs.getInt("Course_code"));
				courseBean.setName(rs.getString("Course_name"));
				courseBean.setMajor(rs.getString("Course_major"));
				courseBean.setPeriod(rs.getInt("Course_period"));
				courseBean.setType(rs.getString("Course_type"));
				courseBean.setDesc(rs.getString("short_desc"));
			}
		} catch (Exception e) {

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

		return courseBean;
	}

	public int CourseUpdate(CourseBean newBean) {
		int count = 0;
		try {

			getSession().update(newBean);
			getSession().flush();

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

	public int findCourseInterface(int code) {
		int Course_Interface_code = 0;
		try {
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.COURSEDeletecheck);
			Prest.setInt(1, code);
			rs = Prest.executeQuery();
			if (rs.next()) {
				Course_Interface_code = rs.getInt("Course_code");

			}
		} catch (Exception e) {

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

		return Course_Interface_code;
	}

	public int findCourseMas(int code) {
		int Course_Mas_code = 0;
		try {
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.COURSESELECT_STRING);
			Prest.setInt(1, code);
			rs = Prest.executeQuery();
			if (rs.next()) {
				Course_Mas_code = rs.getInt("Course_code");

			}
		} catch (Exception e) {

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

		return Course_Mas_code;
	}

	public int courseDelete(int code) {

		int course_Delete = 0;
		try {
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.COURSEDELETE_STRING);
			Prest.setInt(1, code);
			Prest.executeUpdate();
			if (rs.next()) {

			}
		} catch (Exception e) {

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

		return course_Delete;
	}

	public int courseName(CourseBean newBean) {

		int course_code = 0;
		try {
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.COURSESame_Name);
			Prest.setString(1, newBean.getName());
			rs = Prest.executeQuery();
			if (rs.next()) {

				course_code = rs.getInt("course_Code");
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

		return course_code;
	}

	public int courseSave(CourseBean newBean) {
		int count = 0;

		try {

			getSession().save(newBean);
			getSession().flush();
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

	// --------------------Designation--------------------

	public DesignationBean findDesignationMax() {
		DesignationBean newBean = null;
		try {
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.DesigSQLStringCode);
			rs = Prest.executeQuery();
			if (rs.next()) {
				newBean = new DesignationBean();
				newBean.setCode(rs.getInt("maxno") + 1);
			}
		} catch (Exception e) {

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

		return newBean;
	}

	public DesignationBean findDesignationSearch(int code) {
		DesignationBean newBean = null;
		try {
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.DesigSELECT_STRING);
			Prest.setInt(1, code);
			rs = Prest.executeQuery();
			if (rs.next()) {
				newBean = new DesignationBean();
				newBean.setCode(rs.getInt(1));
				newBean.setName(rs.getString(2));
				newBean.setDesc(rs.getString(3));
			}
		} catch (Exception e) {

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

		return newBean;
	}

	public int findDesigInterface(int code) {
		log4jLogger.info("start===========findDesigInterface=====" + code);
		int Desig_Interface_code = 0;
		try {
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.DesigDeletecheck);
			Prest.setInt(1, code);
			rs = Prest.executeQuery();
			if (rs.next()) {
				Desig_Interface_code = rs.getInt("designation_code");

				log4jLogger.info("start===========Desig_Interface_code====="
						+ Desig_Interface_code);

			}
		} catch (Exception e) {

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

		return Desig_Interface_code;
	}

	public int findDesigMas(int code) {
		log4jLogger.info("start===========findDesigMas=====" + code);
		int Desig_Mas_code = 0;
		try {
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.DesigSELECT_STRING);
			Prest.setInt(1, code);
			rs = Prest.executeQuery();
			if (rs.next()) {
				Desig_Mas_code = rs.getInt("Desig_code");

			}
		} catch (Exception e) {

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

		return Desig_Mas_code;
	}

	public int desigDelete(int code) {
		log4jLogger.info("start===========desigDelete=====" + code);
		int desig_Delete = 0;
		try {
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.DesigDELETE_STRING);
			Prest.setInt(1, code);
			Prest.executeUpdate();
			if (rs.next()) {

			}
		} catch (Exception e) {

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

		return desig_Delete;
	}

	public int desigName(DesignationBean newBean) {
		log4jLogger.info("start===========desigName=====");

		int desig_code = 0;
		try {
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.DesigSame_Name);
			Prest.setString(1, newBean.getName());
			rs = Prest.executeQuery();
			if (rs.next()) {

				desig_code = rs.getInt("Desig_code");
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

		return desig_code;
	}

	public int desigSave(DesignationBean newBean) {
		log4jLogger.info("start===========desigSave=====" + newBean.getCode());
		int count = 0;

		try {

			getSession().save(newBean);
			getSession().flush();
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

	public int desigUpdate(DesignationBean newBean) {
		log4jLogger
				.info("start===========desigUpdate=====" + newBean.getCode());
		int count = 0;
		try {

			getSession().update(newBean);
			getSession().flush();
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

	public List findDesigSearch(DesignationBean newBean) {
		log4jLogger.info("start===========findDesigSearch====="
				+ newBean.getCode());

		subjectbean newDesigBean = null;
		List finalResults = null;

		try {
			con = getSession().connection();
			st = con.createStatement();

			if (newBean.getName() == "") {

				rs = st.executeQuery(DataQuery.DESIGNATIONSEARCH_NAME);
			} else {

				rs = st.executeQuery(DataQuery.DESIGNATIONSEARCH_NAME_LIKE
						+ newBean.getName() + "%' order by desig_name");

			}

			finalResults = new ArrayList();

			while (rs.next()) {
				newDesigBean = new subjectbean();
				newDesigBean.setCode(rs.getInt("Desig_Code"));
				newDesigBean.setName(rs.getString("Desig_Name"));
				newDesigBean.setDesc(rs.getString("Short_Desc"));
				finalResults.add(newDesigBean);

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

	// ----------------------------PubSup------------------

	public PubSupBean findPubSupMax() {
		log4jLogger.info("start===========findPubSupMax=====");

		PubSupBean newBean = null;
		try {

			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.PUBSUBSQLStringCode);
			rs = Prest.executeQuery();
			if (rs.next()) {
				newBean = new PubSupBean();
				newBean.setCode(rs.getInt("maxno") + 1);

			}
		} catch (Exception e) {

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

		return newBean;
	}

	public PubSupBean findPubSupSearch(int code) {
		log4jLogger.info("start===========findPubSupSearch=====" + code);
		PubSupBean ob = null;
		try {
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.PUBSUBSELECT_STRING);
			Prest.setInt(1, code);
			rs = Prest.executeQuery();
			if (rs.next()) {
				ob = new PubSupBean();
				ob.setCode(rs.getInt("sp_code"));
				ob.setName(rs.getString("sp_name"));
				ob.setDesc(rs.getString("short_desc"));
				ob.setAdd1(rs.getString("address1"));
				ob.setAdd2(rs.getString("address2"));
				ob.setCity(rs.getString("city"));
				ob.setState(rs.getString("state"));
				ob.setCountry(rs.getString("country"));
				ob.setPin(rs.getString("pincode"));
				ob.setPhone(rs.getString("phone"));
				ob.setFax(rs.getString("fax"));
				ob.setEmail(rs.getString("emailid"));
				ob.setUrl(rs.getString("urlid"));
				ob.setType(rs.getString("sp_type"));
			}
		} catch (Exception e) {

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

		return ob;
	}

	public int findPubSupInterface(int code) {
		log4jLogger.info("start===========findPubSupInterface=====" + code);
		int PubSup_Interface_code = 0;
		try {
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.PUBSUBDeletecheck);
			Prest.setInt(1, code);
			rs = Prest.executeQuery();
			if (rs.next()) {
				PubSup_Interface_code = rs.getInt("sup_code");

				log4jLogger.info("start===========PubSup_Interface_code====="
						+ PubSup_Interface_code);

			}
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.PUBSUBDeletechecks);
			Prest.setInt(1, code);
			rs = Prest.executeQuery();
			if (rs.next()) {
				PubSup_Interface_code = rs.getInt("pub_code");

				log4jLogger.info("start===========PubSup_Interface_code====="
						+ PubSup_Interface_code);

			}

		} catch (Exception e) {

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

		return PubSup_Interface_code;
	}

	public int findPubSupMas(int code) {
		log4jLogger.info("start===========findPubSupMas=====" + code);
		int PubSup_Mas_code = 0;
		try {
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.PUBSUBSELECT_STRING);
			Prest.setInt(1, code);
			rs = Prest.executeQuery();
			if (rs.next()) {
				PubSup_Mas_code = rs.getInt("sp_code");

			}
		} catch (Exception e) {

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

		return PubSup_Mas_code;
	}

	public int pubSupDelete(int code) {
		log4jLogger.info("start===========pubSupDelete=====" + code);
		int pubSup_Delete = 0;
		try {
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.PUBSUBDELETE_STRING);
			Prest.setInt(1, code);
			Prest.executeUpdate();
			if (rs.next()) {

			}
		} catch (Exception e) {

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

		return pubSup_Delete;
	}

	public int pubSupName(PubSupBean newBean) {
		log4jLogger.info("start===========pubSupName=====");

		int pubSup_code = 0;
		try {
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.PUBSUBSame_Name);
			Prest.setString(1, newBean.getName());
			Prest.setString(2, newBean.getType());
			rs = Prest.executeQuery();
			if (rs.next()) {

				pubSup_code = rs.getInt("sp_code");
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

		return pubSup_code;
	}

	public int pubSupSave(PubSupBean newBean) {
		log4jLogger.info("start===========desigSave=====" + newBean.getCode());
		int count = 0;

		try {
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.PUBSUBINSERT_STRING);
			Prest.setInt(1, newBean.getCode());
			Prest.setString(2, newBean.getName());
			Prest.setString(3, newBean.getDesc());
			Prest.setString(4, newBean.getAdd1());
			Prest.setString(5, newBean.getAdd2());
			Prest.setString(6, newBean.getCity());
			Prest.setString(7, newBean.getState());
			Prest.setString(8, newBean.getCountry());
			Prest.setString(9, newBean.getPin());
			Prest.setString(10, newBean.getPhone());
			Prest.setString(11, newBean.getFax());
			Prest.setString(12, newBean.getEmail());
			Prest.setString(13, newBean.getUrl());
			Prest.setString(14, newBean.getType());
			count = Prest.executeUpdate();
			/*
			 * getSession().save(newBean); getSession().flush();
			 */
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

		return count;
	}

	public int pubSupUpdate(PubSupBean newBean) {
		log4jLogger.info("start===========pubSupUpdate====="
				+ newBean.getCode());
		int count = 0;
		try {

			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.PUBSUBUPDATE_STRING);

			Prest.setString(1, newBean.getName());
			Prest.setString(2, newBean.getDesc());
			Prest.setString(3, newBean.getAdd1());
			Prest.setString(4, newBean.getAdd2());
			Prest.setString(5, newBean.getCity());
			Prest.setString(6, newBean.getState());
			Prest.setString(7, newBean.getCountry());
			Prest.setString(8, newBean.getPin());
			Prest.setString(9, newBean.getPhone());
			Prest.setString(10, newBean.getFax());
			Prest.setString(11, newBean.getEmail());
			Prest.setString(12, newBean.getUrl());
			Prest.setString(13, newBean.getType());
			Prest.setInt(14, newBean.getCode());
			count = Prest.executeUpdate();

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

		return count;
	}

	public List findSupSearch(PubSupBean newBean) {
		log4jLogger.info("start===========findSupSearch====="
				+ newBean.getCode());

		subjectbean newDesigBean = null;
		List finalResults = null;

		try {
			con = getSession().connection();
			st = con.createStatement();

			if (newBean.getName() == "") {

				rs = st.executeQuery(DataQuery.PUBSUBSEARCH_STRING_SUP);
			} else {

				rs = st.executeQuery(DataQuery.PUBSUBSEARCH_STRING_LIKE_SUP
						+ newBean.getName()
						+ "%' and sp_type='SUPPLIER' order by sp_name");

			}

			finalResults = new ArrayList();

			while (rs.next()) {
				newDesigBean = new subjectbean();
				newDesigBean.setCode(rs.getInt("sp_Code"));
				newDesigBean.setName(rs.getString("sp_Name"));
				newDesigBean.setDesc(rs.getString("Short_Desc"));
				finalResults.add(newDesigBean);

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

	public List findPubSearch(PubSupBean newBean) {
		log4jLogger.info("start===========findPubSearch====="
				+ newBean.getCode());

		subjectbean newDesigBean = null;
		List finalResults = null;

		try {
			con = getSession().connection();
			st = con.createStatement();

			if (newBean.getName() == "") {

				rs = st.executeQuery(DataQuery.PUBSUBSEARCH_STRING_PUB);
			} else {

				rs = st.executeQuery(DataQuery.PUBSUBSEARCH_STRING_LIKE_PUB
						+ newBean.getName()
						+ "%' and sp_type='PUBLISHER' order by sp_name");

			}

			finalResults = new ArrayList();

			while (rs.next()) {
				newDesigBean = new subjectbean();
				newDesigBean.setCode(rs.getInt("sp_Code"));
				newDesigBean.setName(rs.getString("sp_Name"));
				newDesigBean.setDesc(rs.getString("Short_Desc"));
				finalResults.add(newDesigBean);

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

	// ---------------------------------------Branch-----------------

	public BranchBean findBranchMax() {
		log4jLogger.info("start===========findBranchMax=====");
		BranchBean newBean = null;
		try {

			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.BRANCHQLStringCode);
			rs = Prest.executeQuery();
			if (rs.next()) {
				newBean = new BranchBean();
				newBean.setCode(rs.getInt("maxno") + 1);

			}
		} catch (Exception e) {

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

		return newBean;
	}

	public BranchBean findBranchSearch(int code) {
		log4jLogger.info("start===========findBranchSearch=====");
		BranchBean newBean = null;
		try {
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.BRANCHSELECT_STRING);
			Prest.setInt(1, code);
			rs = Prest.executeQuery();
			if (rs.next()) {
				newBean = new BranchBean();
				newBean.setCode(rs.getInt(1));
				newBean.setName(rs.getString(2));
				newBean.setDesc(rs.getString(3));

			}
		} catch (Exception e) {

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

		return newBean;
	}

	public int findBranchInterface(int code) {
		log4jLogger.info("start===========findBranchInterface=====");
		int Branch_Interface_code = 0;
		try {
			con = getSession().connection();
			Prest = con
					.prepareStatement(DataQuery.BRANCHSELECT_STRING_INTERFACE);
			Prest.setInt(1, code);
			rs = Prest.executeQuery();
			if (rs.next()) {
				Branch_Interface_code = rs.getInt("branch_code");

			}
		} catch (Exception e) {

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

		return Branch_Interface_code;
	}

	public int findBranchMas(int code) {
		log4jLogger.info("start===========findBranchMas=====");
		int Branch_Mas_code = 0;
		try {
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.BRANCHSELECT_STRING);
			Prest.setInt(1, code);
			rs = Prest.executeQuery();
			if (rs.next()) {
				Branch_Mas_code = rs.getInt("branch_code");

			}
		} catch (Exception e) {

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

		return Branch_Mas_code;
	}

	public int branchDelete(int code) {
		log4jLogger.info("start===========branchDelete=====");

		int branch_Delete = 0;
		try {
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.BRANCHDELETE_STRING);
			Prest.setInt(1, code);
			Prest.executeUpdate();
			if (rs.next()) {

			}
		} catch (Exception e) {

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

		return branch_Delete;
	}

	public int branchName(BranchBean newBean) {
		log4jLogger.info("start===========branchName=====");
		int Branch_code = 0;
		try {
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.BRANCHSame_Name);
			Prest.setString(1, newBean.getName());
			rs = Prest.executeQuery();
			if (rs.next()) {

				Branch_code = rs.getInt("Branch_code");
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

		return Branch_code;
	}

	public int branchSave(BranchBean newBean) {

		log4jLogger.info("start===========branchSave=====");
		int count = 0;

		try {

			getSession().save(newBean);
			getSession().flush();
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

	public int branchUpdate(BranchBean newBean) {
		log4jLogger.info("start===========branchUpdate=====");
		int count = 0;
		try {

			getSession().update(newBean);
			getSession().flush();
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

	public List findBranchSearchName(String name) {
		log4jLogger.info("start===========findBranchSearchName=====");
		BranchBean ob = null;
		ob = new BranchBean();
		ArrayList ser = new ArrayList();

		try {
			con = getSession().connection();
			st = con.createStatement();

			if (name == "") {

				rs = st.executeQuery(DataQuery.BRANCHUPDATE_STRING_SARCH);
			} else {

				rs = st.executeQuery(DataQuery.BRANCHUPDATE_STRING_SARCH_LIKE
						+ name + "%' order by Branch_name");

			}

			while (rs.next()) {
				/*
				 * String f1=rs.getString("Branch_Code"); String
				 * f2=rs.getString("Branch_Name"); String
				 * f3=rs.getString("Short_Desc");
				 */
				ser.add(rs.getString("Branch_Code"));
				ser.add(rs.getString("Branch_Name"));
				ser.add(rs.getString("Short_Desc"));

			}
			ob.setAl(ser);

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

		return ser;
	}

	// --------------------Currency--------------------

	public CurrencyBean findCurrencyMax() {
		log4jLogger.info("start===========findCurrencyMax=====");
		CurrencyBean newBean = null;
		try {

			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.CURRENCYQLStringCode);
			rs = Prest.executeQuery();
			if (rs.next()) {
				newBean = new CurrencyBean();
				newBean.setCode(rs.getInt("maxno") + 1);

			}
		} catch (Exception e) {

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

		return newBean;
	}

	public CurrencyBean findCurrencySearch(int code) {
		log4jLogger.info("start===========findCurrencySearch=====");
		CurrencyBean newBean = null;
		try {
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.CURRENCYSELECT_STRING);
			Prest.setInt(1, code);
			rs = Prest.executeQuery();
			if (rs.next()) {
				newBean = new CurrencyBean();
				newBean.setCode(rs.getInt(1));
				newBean.setCurrency(rs.getString(2));
				newBean.setIndian_value(String.valueOf(Float.valueOf(rs
						.getString(3))));
				newBean.setRemarks(rs.getString(4));
			}
		} catch (Exception e) {

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

		return newBean;
	}

	public int findCurrencyInterface(int code) {
		log4jLogger.info("start===========findCurrencyInterface=====");
		int Currency_Interface_code = 0;
		try {
			con = getSession().connection();
			Prest = con
					.prepareStatement(DataQuery.CURRENCYSELECT_STRING_INTERFACE);
			Prest.setInt(1, code);
			rs = Prest.executeQuery();
			if (rs.next()) {
				Currency_Interface_code = rs.getInt("bcurrency");

			}
		} catch (Exception e) {

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

		return Currency_Interface_code;
	}

	public int findCurrencyMas(int code) {
		log4jLogger.info("start===========findCurrencyMas=====");
		int Currency_Mas_code = 0;
		try {
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.CURRENCYSELECT_STRING);
			Prest.setInt(1, code);
			rs = Prest.executeQuery();
			if (rs.next()) {
				Currency_Mas_code = rs.getInt("code");

			}
		} catch (Exception e) {

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

		return Currency_Mas_code;
	}

	public int currencyDelete(int code) {
		log4jLogger.info("start===========currencyDelete=====");

		int branch_Delete = 0;
		try {
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.CURRENCYDELETE_STRING);
			Prest.setInt(1, code);
			Prest.executeUpdate();
			if (rs.next()) {

			}
		} catch (Exception e) {

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

		return branch_Delete;
	}

	public int currencyName(CurrencyBean newBean) {
		log4jLogger.info("start===========currencyName=====");
		int currency_code = 0;
		try {
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.CURRENCYSame_Name);
			Prest.setString(1, newBean.getCurrency());
			rs = Prest.executeQuery();
			if (rs.next()) {

				currency_code = rs.getInt("code");
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

		return currency_code;
	}

	public int currencySave(CurrencyBean newBean) {

		log4jLogger.info("start===========currencySave=====");
		int count = 0;

		try {
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.CURRENCYINSERT_STRING);
			Prest.setString(2, newBean.getCurrency());
			Prest.setString(3, newBean.getIndian_value());
			Prest.setString(4, newBean.getRemarks());
			Prest.setInt(1, newBean.getCode());
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

	public int currencyUpdate(CurrencyBean newBean) {
		log4jLogger.info("start===========currencyUpdate=====");
		int count = 0;
		try {

			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.CURRENCYUPDATE_STRING);

			Prest.setString(1, newBean.getCurrency());
			Prest.setFloat(2, Float.valueOf(newBean.getIndian_value()));
			Prest.setString(3, newBean.getRemarks());
			Prest.setInt(4, newBean.getCode());
			count = Prest.executeUpdate();

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

		return count;
	}

	public List CurrencySearch(CurrencyBean newBean) {
		log4jLogger.info("start===========CurrencySearch=====");
		CurrencyBean ob = null;
		ob = new CurrencyBean();

		List finalResults = null;
		// newBean = new CurrencyBean();

		try {
			con = getSession().connection();
			st = con.createStatement();

			if (newBean.getCurrency() == "") {

				rs = st.executeQuery(DataQuery.CURRENCYSEARCH_NAME);
			} else {

				rs = st.executeQuery(DataQuery.CURRENCYSEARCH_NAME_LIKE
						+ newBean.getCurrency() + "%' order by Currency");

			}

			finalResults = new ArrayList();

			newBean = new CurrencyBean();
			while (rs.next()) {
				newBean = new CurrencyBean();
				newBean.setCode(rs.getInt("Code"));
				newBean.setCurrency(rs.getString("currency"));
				newBean.setIndian_value(rs.getString("Indian_Value"));
				newBean.setRemarks(rs.getString("remarks"));
				finalResults.add(newBean);

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

	// --------------------Keywords--------------------

	public KeywordsBean findkeywordsMax() {
		log4jLogger.info("start===========findkeywordsMax=====");
		KeywordsBean newBean = null;
		try {

			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.NEW_KEYWORDS);
			rs = Prest.executeQuery();
			if (rs.next()) {
				newBean = new KeywordsBean();
				newBean.setKcode(rs.getInt("max") + 1);

			}
		} catch (Exception e) {

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

		return newBean;
	}

	public KeywordsBean findKeywordsSearch(int code) {
		log4jLogger.info("start===========findKeywordsSearch=====");
		KeywordsBean newBean = null;
		try {
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.SELECT_KEYWORDS);
			Prest.setInt(1, code);
			rs = Prest.executeQuery();
			if (rs.next()) {
				newBean = new KeywordsBean();
				newBean.setKcode(rs.getInt(1));
				newBean.setKname(rs.getString(2));
				newBean.setKdesc(rs.getString(3));

			}
		} catch (Exception e) {

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

		return newBean;
	}

	public int keywordsDelete(int code) {
		log4jLogger.info("start===========keywordsDelete=====");

		int keywords_Delete = 0;
		try {
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.DELETE_KEYWORDS);
			Prest.setInt(1, code);
			Prest.executeUpdate();
			if (rs.next()) {

			}
		} catch (Exception e) {

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

		return keywords_Delete;
	}

	public int keywordsName(KeywordsBean newBean) {
		log4jLogger.info("start===========keywordsName=====");
		int currency_code = 0;
		try {
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.SELECT_SAME_NAME);
			Prest.setString(1, newBean.getKname());
			rs = Prest.executeQuery();
			if (rs.next()) {

				currency_code = rs.getInt("keyword_code");
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

		return currency_code;
	}

	public int findKeywordsMas(int code) {
		log4jLogger.info("start===========findKeywordsMas=====");
		int keyword_Mas_code = 0;
		try {
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.SELECT_KEYWORDS);
			Prest.setInt(1, code);
			rs = Prest.executeQuery();
			if (rs.next()) {
				keyword_Mas_code = rs.getInt("keyword_code");

			}
		} catch (Exception e) {

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

		return keyword_Mas_code;
	}

	public int keywordsSave(KeywordsBean newBean) {

		log4jLogger.info("start===========keywordsSave=====");
		int count = 0;

		try {

			getSession().save(newBean);
			getSession().flush();
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

	public int keywordsUpdate(KeywordsBean newBean) {
		log4jLogger.info("start===========keywordsUpdate=====");
		int count = 0;
		try {

			getSession().update(newBean);
			getSession().flush();
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

	public List keywordsSearchName(KeywordsBean newBean) {
		log4jLogger.info("start===========keywordsSearchName=====");
		KeywordsBean ob = null;
		ob = new KeywordsBean();

		List finalResults = null;
		// newBean = new KeywordsBean();

		try {
			con = getSession().connection();
			st = con.createStatement();

			if (newBean.getKname() == "") {

				rs = st.executeQuery(DataQuery.KEYWORDS_SEARCH_NAME);
			} else {

				rs = st.executeQuery(DataQuery.KEYWORDS_SEARCH_NAME_LIKE
						+ newBean.getKname() + "%' order by keyword_name");

			}

			finalResults = new ArrayList();

			newBean = new KeywordsBean();
			while (rs.next()) {
				newBean = new KeywordsBean();
				newBean.setKcode(rs.getInt("keyword_code"));
				newBean.setKname(rs.getString("keyword_name"));
				newBean.setKdesc(rs.getString("short_desc"));
				finalResults.add(newBean);

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

	// ----------------Report--------------------------

	/*
	 * public byte[] getJasperReportToPdfDisplyDao(JasperReport jasperReport,Map
	 * parameters) {
	 * 
	 * byte[] bytes=null; try { con = getSession().connection(); bytes =
	 * JasperRunManager.runReportToPdf(jasperReport,parameters,con); } catch
	 * (JRException e) {
	 * 
	 * }
	 * 
	 * return bytes; }
	 */

	// --------------Binding---------------------------------------

	public BindingBean findBindingMax() {
		log4jLogger.info("start===========findBindingMax=====");
		BindingBean newBean = null;
		try {

			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.BindingSQLStringCode);
			rs = Prest.executeQuery();
			if (rs.next()) {
				newBean = new BindingBean();
				newBean.setCode(rs.getInt("maxno") + 1);

			}
		} catch (Exception e) {

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

		return newBean;
	}

	public BindingBean findBindingSearch(int code) {
		log4jLogger.info("start===========findBindingSearch=====");
		BindingBean ob = null;
		try {
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.BindingSELECT_STRING);
			Prest.setInt(1, code);
			rs = Prest.executeQuery();
			if (rs.next()) {
				ob = new BindingBean();
				ob.setCode(rs.getInt("binder_code"));
				ob.setName(rs.getString("binder_name"));
				ob.setDesc(rs.getString("short_desc"));
				ob.setAdd1(rs.getString("address1"));
				ob.setAdd2(rs.getString("address2"));
				ob.setCity(rs.getString("city"));
				ob.setState(rs.getString("state"));
				ob.setCountry(rs.getString("country"));
				ob.setPin(rs.getString("pincode"));
				ob.setPhone(rs.getString("phone"));
				ob.setFax(rs.getString("fax"));
				ob.setEmail(rs.getString("emailid"));
				ob.setUrl(rs.getString("urlid"));
			}
		} catch (Exception e) {

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

		return ob;
	}

	public int BindingDelete(BindingBean codebean) {
		log4jLogger.info("start===========authorDelete=====");

		int binder_Delete = 0;
		try {
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.BindingDeletecheck);
			Prest.setInt(1, codebean.getCode());
			rs = Prest.executeQuery();

			if (rs.next()) {
				binder_Delete = 1;

			} else {
				getSession().delete(codebean);
				getSession().flush();
			}
		} catch (Exception e) {

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

		return binder_Delete;
	}

	public int findBindingName(String Name) {
		log4jLogger.info("start===========findBindingName=====" + Name);
		int binder_code = 0;
		try {
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.BindingSame_Name);
			Prest.setString(1, Name);
			rs = Prest.executeQuery();
			if (rs.next()) {

				binder_code = Integer.valueOf(rs.getString("binder_code"));

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

		return binder_code;
	}

	public int findBindingUpdate(BindingBean codebean) {
		log4jLogger.info("start===========findBindingUpdate=====");
		int count = 0;
		try {

			getSession().update(codebean);
			getSession().flush();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return count;
	}

	public int fineBindingSave(BindingBean codebean) {
		log4jLogger.info("start===========fineBindingSave=====");
		int count = 0;
		try {

			getSession().save(codebean);
			getSession().flush();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return count;
	}

	public List findBinderSearchName(String name) {
		log4jLogger.info("start===========findBinderSearchName=====");
		BindingBean ob = null;
		ob = new BindingBean();
		ArrayList ser = new ArrayList();

		try {
			con = getSession().connection();
			st = con.createStatement();

			if (name == "") {

				rs = st.executeQuery(DataQuery.BINDER_SEARCH);
			} else {

				rs = st.executeQuery(DataQuery.BINDER_SEARCH_LIKE + name
						+ "%' order by binder_name");

			}

			while (rs.next()) {
				ser.add(rs.getString("binder_Code"));
				ser.add(rs.getString("binder_Name"));
				ser.add(rs.getString("Short_Desc"));

			}
			ob.setAl(ser);

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

		return ser;
	}

	// ------------------------NewsCllipings------------------
	public NewscllipingBean findNewsMax() {
		log4jLogger.info("start===========findNewsMax=====");
		NewscllipingBean newBean = null;
		try {

			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.NEWSSELECT_NEW);
			rs = Prest.executeQuery();
			if (rs.next()) {
				newBean = new NewscllipingBean();
				newBean.setNcode(rs.getInt("maxno") + 1);

			} else {
				newBean.setNcode(1);

			}
		} catch (Exception e) {

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

		return newBean;
	}

	public NewscllipingBean findNewsSearch(int code) {
		log4jLogger.info("start===========findNewsSearch=====");
		NewscllipingBean newBean = null;
		try {
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.SELECT_NEWSMAS);
			Prest.setInt(1, code);
			rs = Prest.executeQuery();
			if (rs.next()) {
				newBean = new NewscllipingBean();
				newBean.setNcode(rs.getInt(1));
				newBean.setNname(rs.getString(2));
				newBean.setNtype(rs.getString(3));
				newBean.setNdate(rs.getString(4));
				newBean.setPno(rs.getString(5));
				newBean.setNtitle(rs.getString(6));
				newBean.setNsubject(rs.getString(7));
				newBean.setNkey(rs.getString(8));
				newBean.setNabstract(rs.getString(9));
				newBean.setNcontent(rs.getString(10));
			}
		} catch (Exception e) {

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

		return newBean;
	}

	public int findNewscliSearch(int code) {
		log4jLogger.info("start===========findNewscliSearch=====");
		int News_Mas_code = 0;
		try {
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.SELECT_NEWSMAS);
			Prest.setInt(1, code);
			rs = Prest.executeQuery();
			if (rs.next()) {
				News_Mas_code = rs.getInt("newspaper_code");

			}
		} catch (Exception e) {

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

		return News_Mas_code;
	}

	public int fineNewClipSave(NewscllipingBean codebean) {
		log4jLogger.info("start===========fineNewClipSave=====");
		int count = 0;
		try {

			getSession().save(codebean);
			getSession().flush();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return count;
	}

	public int findNewsClipUpdate(NewscllipingBean authorBean) {
		log4jLogger.info("start===========findNewsClipUpdate=====");
		int count = 0;
		try {

			getSession().update(authorBean);
			getSession().flush();

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

	public int NewsClipDelete(int code) {
		log4jLogger.info("start===========NewsClipDelete=====");

		int binder_Delete = 0;
		try {
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.Delete_NEWSMAS);
			Prest.setInt(1, code);
			// rs = Prest.executeQuery();
			Prest.executeUpdate();
			/*
			 * if (rs.next()) { binder_Delete=1;
			 * 
			 * }else{ getSession().delete(codebean); getSession().flush(); }
			 */
		} catch (Exception e) {

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

		return binder_Delete;
	}

	// ------------------------EResourse------------------
	public EResourceBean findEResourceMax() {
		log4jLogger.info("start===========findEResourceMax=====");
		EResourceBean newBean = null;
		try {

			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.EResourse_NEW);
			rs = Prest.executeQuery();
			if (rs.next()) {
				newBean = new EResourceBean();
				newBean.setEcode(rs.getInt("maxno") + 1);

			} else {
				newBean.setEcode(1);

			}
		} catch (Exception e) {

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

		return newBean;
	}

	public int findEResourseMas(int code) {
		log4jLogger.info("start===========findEResourseMas=====");
		int News_Mas_code = 0;
		try {
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.selectEResourse_NEW);
			Prest.setInt(1, code);
			rs = Prest.executeQuery();
			if (rs.next()) {
				News_Mas_code = rs.getInt("code");

			}
		} catch (Exception e) {

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

		return News_Mas_code;
	}

	public int fineEResourseSave(EResourceBean codebean) {
		log4jLogger.info("start===========fineEResourseSave=====");
		int count = 0;
		try {

			getSession().save(codebean);
			getSession().flush();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return count;
	}

	public EResourceBean findEResourseSearch(int code) {
		log4jLogger.info("start===========findEResourseSearch=====");
		EResourceBean newBean = null;
		try {
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.selectEResourse_NEW);
			Prest.setInt(1, code);
			rs = Prest.executeQuery();
			if (rs.next()) {
				newBean = new EResourceBean();
				newBean.setEcode(rs.getInt(1));
				newBean.setEsite(rs.getString(2));
				newBean.setEdetails(rs.getString(3));
				newBean.setEtitle(rs.getString(4));
				newBean.setEsubtitle(rs.getString(5));
				newBean.setType(rs.getString(6));

			}
		} catch (Exception e) {

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

		return newBean;
	}

	public int findEResourceUpdate(EResourceBean authorBean) {
		log4jLogger.info("start===========findEResourceUpdate=====");
		int count = 0;
		try {

			getSession().update(authorBean);
			getSession().flush();

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

	public int EResourceDelete(int code) {
		log4jLogger.info("start===========EResourceDelete=====");

		int binder_Delete = 0;
		try {
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.Delete_EResource);
			Prest.setInt(1, code);
			// rs = Prest.executeQuery();
			Prest.executeUpdate();
			/*
			 * if (rs.next()) { binder_Delete=1;
			 * 
			 * }else{ getSession().delete(codebean); getSession().flush(); }
			 */
		} catch (Exception e) {

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

		return binder_Delete;
	}

	// ------------------------MResourse------------------
	public String findMResourseMas(MResourceBean newbean) {
		log4jLogger.info("start===========findMResourseMas=====");
		String News_Mas_code = "";

		try {

			con = getSession().connection();
			Prest1 = con.prepareStatement(DataQuery.selectBookResourse_NEW);
			Prest1.setString(1, newbean.getAccessno());
			Prest1.setString(2, newbean.getDoctype());

			rs1 = Prest1.executeQuery();

			if (rs1.next()) {

				con = getSession().connection();
				Prest = con.prepareStatement(DataQuery.selectMResourse_NEW);
				Prest.setString(1, newbean.getAccessno());

				rs = Prest.executeQuery();
				if (rs.next()) {
					News_Mas_code = rs.getString("access_no");
				}
			} else {

				News_Mas_code = "111";
			}

		} catch (Exception e) {

		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (rs1 != null) {
					rs1.close();
				}
				if (Prest != null) {
					Prest.close();
				}
				if (Prest1 != null) {
					Prest1.close();
				}

				if (con != null) {
					con.close();
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return News_Mas_code;
	}

	public int fineMResourseSave(MResourceBean codebean) {
		log4jLogger.info("start===========fineMResourseSave=====");
		int count = 0;
		try {

			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.MResourceINSERT_STRING);
			Prest.setString(1, codebean.getAccessno());
			Prest.setString(2, (codebean.getDoctype()));
			Prest.setString(3, (codebean.getAvailability()));
			Prest.setString(4, (codebean.getMdate()));
			count = Prest.executeUpdate();

			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.selectBookResourse_Update);
			Prest.setString(1, codebean.getAvailability());
			Prest.setString(2, codebean.getAccessno());
			Prest.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
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
	
	public MResourceBean findMissingBook(String accno)
	{
		System.out.println("::::Inside Missing Book:::::"+accno);
		MResourceBean newBean = null;
		try {

			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.selectMResourse_NEW);
			Prest.setString(1, accno);
			rs = Prest.executeQuery();
			if (rs.next()) {
				newBean = new MResourceBean();
				newBean.setAccessno(rs.getString(1));
				newBean.setDoctype(rs.getString(2));
				newBean.setAvailability(rs.getString(3));
				newBean.setMdate(rs.getString(4));
			}		
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
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
		return newBean;
	}
	
	public int findMBookDelete(String accno)
	{
		System.out.println("::::Inside Delete Missing Book:::::"+accno);
		//MResourceBean newBean = null;
		int count=0;
		try {

			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.DeleteMResource_Book);
			//Prest = con.prepareStatement(DataQuery.selectMResourse_NEW);
			Prest.setString(1, accno);
			Prest.executeUpdate();
//			if (rs.next()) {
//			
//			}		
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
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

	// --------------Contents Upload----------------------------

	public boolean findCheckContentsNumber(String accno, String document) {
		log4jLogger.info("start===========findCheckContentsNumber=====");

		String query = "";
		SQLQuery sql = null;
		boolean result = false;

		if (document.equalsIgnoreCase("BOOK")
				|| document.equalsIgnoreCase("BOOK BANK")
				|| document.equalsIgnoreCase("NON BOOK")
				|| document.equalsIgnoreCase("REPORT")
				|| document.equalsIgnoreCase("THESIS")
				|| document.equalsIgnoreCase("STANDARD")
				|| document.equalsIgnoreCase("PROCEEDING")
				|| document.equalsIgnoreCase("BACK VOLUME")) {
			query = DataQuery.Content_BookMas;

			sql = getSession().createSQLQuery(query);
			sql.setParameter("accno", accno);
			sql.setParameter("document", document);
		} else if (document.equalsIgnoreCase("JOURNAL ISSUES")) {
			query = DataQuery.Content_JNLIssueMas;

			sql = getSession().createSQLQuery(query);
			sql.setParameter("accno", accno);
		} else if (document.equalsIgnoreCase("NEWS CLIPPING")) {
			query = DataQuery.Content_NewsClipMas;

			sql = getSession().createSQLQuery(query);
			sql.setParameter("accno", accno);
		} else if (document.equalsIgnoreCase("QUESTION BANK")) {
			query = DataQuery.Content_QuestionBankMas;

			sql = getSession().createSQLQuery(query);
			sql.setParameter("accno", accno);
		} else if (document.equalsIgnoreCase("JOURNAL ARTICLE")) {
			query = DataQuery.Content_JNLArticleMas;

			sql = getSession().createSQLQuery(query);
			sql.setParameter("accno", accno);
		} else if (document.equalsIgnoreCase("EBOOK")) {
			query = DataQuery.Content_EBOOKMas;

			sql = getSession().createSQLQuery(query);
			sql.setParameter("accno", accno);
		}

		List list = sql.list();

		if (list.size() > 0) {
			result = true;
		}

		return result;

	}

	public void findUpdateContentFile(String accno, String document,
			String fileName) {
		log4jLogger.info("start===========findUpdateContentFile=====");

		String query = "";
		SQLQuery sql = null;
		boolean result = false;

		if (document.equalsIgnoreCase("BOOK")
				|| document.equalsIgnoreCase("BOOK BANK")
				|| document.equalsIgnoreCase("NON BOOK")
				|| document.equalsIgnoreCase("REPORT")
				|| document.equalsIgnoreCase("THESIS")
				|| document.equalsIgnoreCase("STANDARD")
				|| document.equalsIgnoreCase("PROCEEDING")
				|| document.equalsIgnoreCase("BACK VOLUME")) {
			query = DataQuery.Content_Update_BookMas;

			sql = getSession().createSQLQuery(query);
			sql.setParameter("accno", accno);
			sql.setParameter("document", document);
			sql.setParameter("contents", fileName);

		} else if (document.equalsIgnoreCase("JOURNAL ISSUES")) {
			query = DataQuery.Content_Update_JNLIssueMas;

			sql = getSession().createSQLQuery(query);
			sql.setParameter("accno", accno);
			sql.setParameter("contents", fileName);

		} else if (document.equalsIgnoreCase("NEWS CLIPPING")) {
			query = DataQuery.Content_Update_NewsClipMas;

			sql = getSession().createSQLQuery(query);
			sql.setParameter("accno", accno);
			sql.setParameter("contents", fileName);

		}

		else if (document.equalsIgnoreCase("QUESTION BANK")) {
			query = DataQuery.Content_Update_QuestionBankMas;

			sql = getSession().createSQLQuery(query);
			sql.setParameter("qb_code", accno);
			sql.setParameter("contents", fileName);

		}

		else if (document.equalsIgnoreCase("JOURNAL ARTICLE")) {
			query = DataQuery.Content_Update_JNLArticleMas;

			sql = getSession().createSQLQuery(query);
			sql.setParameter("accno", accno);
			sql.setParameter("contents", fileName);
		}

		else if (document.equalsIgnoreCase("EBOOK")) {
			query = DataQuery.Content_Update_EBOOKMas;

			sql = getSession().createSQLQuery(query);
			sql.setParameter("accno", accno);
			sql.setParameter("contents", fileName);
		}

		sql.executeUpdate();

	}

	// -----------------------------------QuestionBankSubject------------------------------

	public QBsubjectbean findQBSubjectMax() {

		log4jLogger.info("start===========findQBSubjectMax=====");

		QBsubjectbean newbean = null;
		try {
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.QBSUBJECTSQLStringCode);
			rs = Prest.executeQuery();
			if (rs.next()) {
				newbean = new QBsubjectbean();
				newbean.setQbcode(rs.getInt("maxno") + 1);
			}
		} catch (Exception e) {

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

		return newbean;

	}

	public int QBsubjectSave(QBsubjectbean newBean) {
		log4jLogger.info("start===========QBsubjectSave=====");
		int count = 0;

		try {
			getSession().save(newBean);
			getSession().flush();

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

	public int findQBSubjectMas(int code) {
		log4jLogger.info("start===========findSubjectMas=====");
		int subject_Mas_code = 0;
		String strsql = "";

		try {
			// if( branchID > 0 )
			// {
			// strsql = " and branch_code="+branchID+" ";
			// }

			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.QBSUBJECTSELECT_STRING
					+ strsql);
			Prest.setInt(1, code);
			rs = Prest.executeQuery();
			if (rs.next()) {
				subject_Mas_code = rs.getInt("QB_Code");

			}
		} catch (Exception e) {

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

		return subject_Mas_code;
	}

	public int findQBSubjectName(QBsubjectbean newBean) {
		log4jLogger.info("start===========findSubjectName=====");

		int sub_code = 0;
		String strsql = "";
		try {
			// if(newBean.getQbbranchcode() > 0)
			// {
			// strsql = " and branch_code=? ";
			// }

			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.QBSUBJECTSame_Name + strsql);
			Prest.setString(1, newBean.getQbsubcode());

			// if(newBean.getQbbranchcode() > 0)
			// {
			// Prest.setInt(2, newBean.getQbbranchcode());
			// }

			rs = Prest.executeQuery();
			if (rs.next()) {

				// sub_code = 1;//SHEK

				sub_code = rs.getInt("QB_Code");
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

		return sub_code;
	}

	public QBsubjectbean findQBSubjectSearch(int code) {

		log4jLogger.info("start===========findQBSubjectSearch=====");
		QBsubjectbean newBean = null;
		try {
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.QBSUBJECTSELECT_STRING);
			Prest.setInt(1, code);
			rs = Prest.executeQuery();
			if (rs.next()) {
				newBean = new QBsubjectbean();
				newBean.setQbcode(rs.getInt(1));
				newBean.setQbsubcode(rs.getString(2));
				newBean.setQbsubname(rs.getString(3));
				newBean.setQbsubdesc(rs.getString(4));
				// newBean.setQbbranchcode(rs.getInt(5));
			}
		} catch (Exception e) {

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

		return newBean;
	}

	public int QBsubjectUpdate(QBsubjectbean newBean) {
		log4jLogger.info("start===========subjectUpdate=====");
		int count = 0;
		try {

			getSession().update(newBean);
			getSession().flush();

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

	public int QBsubjectDelete(int code) {
		log4jLogger.info("start===========subjectDelete=====");

		int subject_Delete = 0;
		try {
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.QBSUBJECTDELETE_STRING);
			Prest.setInt(1, code);
			Prest.executeUpdate();
			if (rs.next()) {

			}
		} catch (Exception e) {

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

		return subject_Delete;
	}

	public List findQBSubjectMasName(QBsubjectbean newBean) {
		log4jLogger.info("start===========findSubjectMasName=====");
		QBsubjectbean ob = null;
		QBsubjectbean newsubBean = null;
		List finalResults = null;
		String strsql = "";

		try {

			// if(newBean.getQbbranchcode() > 0)
			// {
			// strsql = " and branch_code="+newBean.getQbbranchcode()+" ";
			// }

			log4jLogger.info("============DISPLAY BRANCH CODE :" + strsql);
			log4jLogger.info("============DISPLAY QB_SUBJECT_NAME :"+ newBean.getQbsubname());		
			con = getSession().connection();
			st = con.createStatement();

			if (newBean.getQbsubname() == "" || newBean.getQbsubname() == null) {

				newBean.setQbsubname("");
			}

			if (newBean.getQbsubname() == "" || newBean.getQbsubname() == null) {
				log4jLogger.info("==============INSIDE QUESTION BANK SUBJECT SEARCH===================");		
				rs = st.executeQuery(DataQuery.QBSUBJECTSEARCH_NAME + strsql + " order by qbsub_name asc");
						
				log4jLogger.info("+++++++++++++=");
			}
			else
			{
				log4jLogger.info("==============INSIDE QUESTION BANK SUBJECT SEARCH LIKE===============");					
				rs = st.executeQuery(DataQuery.QBSUBJECTSEARCH_NAME_LIKE + newBean.getQbsubname() + "%' " + strsql + " order by qbsub_Name asc");		

			}

			finalResults = new ArrayList();

			while (rs.next()) {
				newsubBean = new QBsubjectbean();
				newsubBean.setQbcode(rs.getInt("QB_Code"));
				newsubBean.setQbsubcode(rs.getString("QBsub_Code"));
				newsubBean.setQbsubname(rs.getString("QBSub_Name"));
				newsubBean.setQbsubdesc(rs.getString("QBSub_Desc"));
				// newsubBean.setQbbranchcode(rs.getInt("branch_code"));
				finalResults.add(newsubBean);
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

	public int findQBSubjectInterface(int code) {
		log4jLogger.info("start===========findSubjectInterface=====");
		int subject_Interface_code = 0;
		try {
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.QBSUBJECTDeletecheck);
			Prest.setInt(1, code);
			rs = Prest.executeQuery();
			if (rs.next()) {

				subject_Interface_code = rs.getInt("QB_Subject_Code");
			}
		} catch (Exception e) {

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

		return subject_Interface_code;
	}

	public QuestionBankBean getQuestionBankMax() {
		log4jLogger.info("start===========getQuestionBankMax=====");
		QuestionBankBean newBean = null;
		try {
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.QUESTION_BANK_NEW);
			rs = Prest.executeQuery();
			if (rs.next()) {
				newBean = new QuestionBankBean();
				newBean.setQcode(rs.getInt("maxno") + 1);
			} else {
				newBean.setQcode(1);
			}
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

		return newBean;
	}

	public int getQuestionBankUpdate(QuestionBankBean authorBean) {
		log4jLogger.info("start=========== getQuestionBankUpdate =====");
		int count = 0;
		try {

			getSession().update(authorBean);
			getSession().flush();

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

	public QuestionBankBean getQuestionBankSearch(int code) {
		log4jLogger.info("start===========getQuestionBankSearch=====");
		QuestionBankBean newBean = null;
		try {
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.SELECT_QUESTION_BANK);
			Prest.setInt(1, code);
			rs = Prest.executeQuery();
			if (rs.next()) {
				newBean = new QuestionBankBean();
				newBean.setQcode(rs.getInt("QB_Code"));

				newBean.setQdate(Security.getdate(rs.getString("Date")));

				newBean.setUniname(rs.getString("University"));

				newBean.setSubname(rs.getString("Sub_Name"));
				newBean.setSubcode(rs.getString("Sub_Code"));

				newBean.setDname(rs.getString("Dept"));
				newBean.setQcourse(rs.getString("Course"));
				newBean.setQyear(rs.getString("Year"));
				newBean.setQmonth(rs.getString("Month"));
				newBean.setSemester(rs.getString("Semester"));

				newBean.setRemarks1(rs.getString("Add_Field1"));
				newBean.setRemarks2(rs.getString("Add_Field2"));
			}
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

		return newBean;
	}

	public int getQuestionBankSave(QuestionBankBean codebean) {
		log4jLogger.info("start===========getQuestionBankSave=====");
		int count = 0;
		try {

			getSession().save(codebean);
			getSession().flush();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return count;
	}

	public int getQuestionBankMas(int code) {
		log4jLogger.info("start===========getQuestionBankMas=====");
		int count = 0;
		try {
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.SELECT_QUESTION_BANK);
			Prest.setInt(1, code);
			rs = Prest.executeQuery();
			if (rs.next()) {
				count = rs.getInt("QB_Code");
			}
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

	public int getQuestionBankDelete(int code) {
		log4jLogger.info("start=========== getQuestionBankDelete =====");

		int count = 0;
		try {
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.QUESTION_BANK_DELETE);
			Prest.setInt(1, code);
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

	// ----------------------MESSAGE MAS-------------------------------

	public MsgMasBean findMsgMasMax() {
		log4jLogger.info("start===========findMax Message Mas =====");

		MsgMasBean msgnbean = null;

		try {
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.MsgMasSQLStringCode);
			rs = Prest.executeQuery();
			if (rs.next()) {
				msgnbean = new MsgMasBean();
				msgnbean.setMsgCode(rs.getInt("maxno") + 1);
			}
		} catch (Exception e) {

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

		return msgnbean;
	}

	public int findMsgMas(int code) {
		log4jLogger.info("start===========findCityMas=====");
		int msgMasCode = 0;
		try {
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.MsgSELECT_STRING);
			Prest.setInt(1, code);
			rs = Prest.executeQuery();
			if (rs.next()) {
				msgMasCode = rs.getInt("msg_code");

			}
		} catch (Exception e) {

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

		return msgMasCode;
	}

	public int MsgMasSave(MsgMasBean newBean) {
		log4jLogger.info("start===========Message Master Save=====");
		int count = 0;

		try {

			getSession().save(newBean);
			getSession().flush();
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

	public MsgMasBean findMsgMasSearch(int code) {
		log4jLogger.info("start===========findMsgMasSearch=====");
		MsgMasBean msgbean = null;
		try {

			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.MsgSELECT_STRING);
			Prest.setInt(1, code);
			rs = Prest.executeQuery();
			if (rs.next()) {

				msgbean = new MsgMasBean();

				msgbean.setMsgCode(rs.getInt(1));
				msgbean.setLibMsg(rs.getString(2));
				msgbean.setWhatsNew(rs.getString(3));
				msgbean.setDate(Security_Counter.getdate(rs.getString(4)));

			}
		} catch (Exception e) {

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

		return msgbean;
	}

	public int MsgMasDelete(int code) {
		log4jLogger.info("start===========MsgMasDelete=====");

		int City_Delete = 0;
		try {
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.MsgMasDELETE_STRING);
			Prest.setInt(1, code);
			Prest.executeUpdate();
			if (rs.next()) {

			}
		} catch (Exception e) {

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

		return City_Delete;
	}

	public int MsgMasUpdate(MsgMasBean newBean) {
		log4jLogger.info("start===========CityUpdate=====");
		int count = 0;
		try {

			getSession().update(newBean);
			getSession().flush();

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

	public List findMsgMasSearchName(String name) {

		log4jLogger.info("start==========findMsgMasSearchName=====");
		MsgMasBean ob = null;
		ob = new MsgMasBean();

		ArrayList ser = new ArrayList();

		try {
			con = getSession().connection();
			st = con.createStatement();

			if (name == "") {
				rs = st.executeQuery(DataQuery.MsgMasSEARCH_NAME);
			} else {

				rs = st.executeQuery(DataQuery.CitySEARCH_NAME_LIKE + name
						+ "%' order by City_name");

			}

			while (rs.next()) {

				ser.add(rs.getInt("msg_code"));
				ser.add(rs.getString("lib_msg"));
			}

			ob.setAl(ser);

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

		return ser;
	}

	@Override
	public Map<String, Object> findDashboardGraph() {

		Map<String,Object> respMap = new HashMap();
		
		List<String> documentGraphx = new ArrayList<>();
		List<String> documentGraphy = new ArrayList<>();
		
		List<String> memberGraphx = new ArrayList<>();
		List<String> memberGraphy = new ArrayList<>();
		
		List<String> lastOneMonthGraphGateX = new ArrayList<>();
		List<String> lastOneMonthGraphGateY = new ArrayList<>();
		List<String> lastOneMonthGraphOpacX = new ArrayList<>();
		List<String> lastOneMonthGraphOpacY = new ArrayList<>();
		
		
		List<String> deptWiseStatisticsGraphX = new ArrayList<>();
		List<String> deptWiseStatisticsGraphY = new ArrayList<>();
		List<String> subjWiseStatisticsGraphX = new ArrayList<>();
		List<String> subjWiseStatisticsGraphY = new ArrayList<>();
		
		
		
		List<String> lastOneMonthCounterX = new ArrayList<>();
		List<String> lastOneMonthIssue = new ArrayList<>();
		List<String> lastOneMonthReturn= new ArrayList<>();
		List<String> lastOneMonthRenew = new ArrayList<>();
		
		
		
		
		try{
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.DOCUMENT_QUREY_GROUP);
			rs = Prest.executeQuery();
			while(rs.next()) {
				documentGraphx.add(rs.getString("document"));
				documentGraphy.add(rs.getString("count"));
			}
			respMap.put("documentGraphx", documentGraphx);
			respMap.put("documentGraphy", documentGraphy);
			
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.MEMBER_QUREY_GROUP);
			rs = Prest.executeQuery();
			while(rs.next()) {
				memberGraphx.add(rs.getString("dept_name"));
				memberGraphy.add(rs.getString("count"));
			}
			
			respMap.put("memberGraphx", memberGraphx);
			respMap.put("memberGraphy", memberGraphy);
			
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.lastOneMonthGraphGate);
			rs = Prest.executeQuery();
			while(rs.next()) {
				lastOneMonthGraphGateX.add(rs.getString("date"));
				lastOneMonthGraphGateY.add(rs.getString("count"));
			}
			
			respMap.put("lastOneMonthGraphGateX", lastOneMonthGraphGateX);
			respMap.put("lastOneMonthGraphGateY", lastOneMonthGraphGateY);
			
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.lastOneMonthOpacReport);
			rs = Prest.executeQuery();
			while(rs.next()) {
				lastOneMonthGraphOpacX.add(rs.getString("date"));
				lastOneMonthGraphOpacY.add(rs.getString("count"));
			}
			
			respMap.put("lastOneMonthGraphOpacX", lastOneMonthGraphOpacX);
			respMap.put("lastOneMonthGraphOpacY", lastOneMonthGraphOpacY);
			
			
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.DEPTWISE_STATISTICS);
			rs = Prest.executeQuery();
			while(rs.next()) {
				deptWiseStatisticsGraphX.add(rs.getString("dept_name"));
				deptWiseStatisticsGraphY.add(rs.getString("count"));
				
			}
			
			respMap.put("deptWiseStatisticsGraphX", deptWiseStatisticsGraphX);
			respMap.put("deptWiseStatisticsGraphY", deptWiseStatisticsGraphY);
			
			
			
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.SUBJECTTWISE_STATISTICS);
			rs = Prest.executeQuery();
			while(rs.next()) {
				subjWiseStatisticsGraphX.add(rs.getString("sub_name"));
				subjWiseStatisticsGraphY.add(rs.getString("count"));
			}
			
			respMap.put("subjWiseStatisticsGraphX", subjWiseStatisticsGraphX);
			respMap.put("subjWiseStatisticsGraphY", subjWiseStatisticsGraphY);
			
			
			/*con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.lastOneCounterReport);
			rs = Prest.executeQuery();
			while(rs.next()) {
				lastOneMonthCounterX.add(rs.getString("date"));
				lastOneMonthIssue.add(rs.getString("issuecount"));
				lastOneMonthReturn.add(rs.getString("returncount"));
				lastOneMonthRenew.add(rs.getString("renewcount"));
			}
			
			respMap.put("lastOneMonthCounterX", lastOneMonthCounterX);
			respMap.put("lastOneMonthIssue", lastOneMonthIssue);
			respMap.put("lastOneMonthReturn", lastOneMonthReturn);
			respMap.put("lastOneMonthRenew", lastOneMonthRenew);

*/


			
			

		}catch(Exception ex){
			ex.printStackTrace();
		}
		return respMap;
	}

}
