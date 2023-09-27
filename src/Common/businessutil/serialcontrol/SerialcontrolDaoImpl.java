package Common.businessutil.serialcontrol;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import Lib.Auto.JNL_Enquiry.JnlenquiryBean;
import Lib.Auto.JNL_Enquiry.JnlenquiryDetailsBean;
import Lib.Auto.JNL_Invoice.JnlInvoiceBean;
import Lib.Auto.JNL_Order.JnlorderBean;
import Lib.Auto.JNL_Order.JnlorderDetailsBean;
import Lib.Auto.JNL_Payment.JnlPaymentBean;
import Lib.Auto.JNL_Supplier_Invoice.JnlSupInvBean;
import Lib.Auto.JNL_Supplier_Invoice.JnlSupInvDetailsBean;
import Lib.Auto.JournalSubscription.JournalSubscriptionbean;
import Lib.Auto.Journal_Artical.journalArtbean;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Restrictions;

import Common.DataQuery;
import Common.Security;
import Common.businessutil.calaloging.CalalogingDaoImpl;
import Common.businessutil.serialcontrol.SerialcontrolDao;
import Lib.Auto.Author.AuthorBean;
import Lib.Auto.Group.GroupBean;
import Lib.Auto.Journal.journalbean;
import Lib.Auto.Journal_Artical.journalArtbean;
import Lib.Auto.Journal_Issues.JnlIssBean;
import Lib.Auto.Login.LoginBean;

import com.ibm.icu.util.Calendar;
import com.library.autolib.portal.dbconnectionutil.BaseDBConnection;


public class SerialcontrolDaoImpl extends BaseDBConnection implements
SerialcontrolDao {
	
	private static Logger log4jLogger = Logger.getLogger(SerialcontrolDaoImpl.class);
	
	private static final int MILLISECONDS_IN_DAY = 1000 * 60 * 60 * 24;
	
	java.sql.Connection con = null;

	java.sql.PreparedStatement Prest = null;

	java.sql.ResultSet rs = null;
	
	java.sql.ResultSet rs1 = null;
	
	java.sql.Statement st = null;
	
	
	
	
	
	public journalbean findJnlMax() {
		log4jLogger.info("start===========findAuthorMax=====");
		journalbean newbean=null;
		
		try {
			
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.JOURNAL_MAX);
			rs = Prest.executeQuery();
			if (rs.next()) {
				newbean = new journalbean();
				newbean.setJcode(String.valueOf(rs.getInt("maxno") + 1));
				
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
	
	public journalbean findJnlSearch(int code) {
		log4jLogger.info("start===========findJnlSearch=====");
		journalbean ob = null;
		try {
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.JOURNAL_SEARCH);
			Prest.setInt(1, code);
			rs = Prest.executeQuery();
			if (rs.next()) {
				ob = new journalbean();
			
				
				  int journ_code=rs.getInt(1);
				String x=String.valueOf(journ_code);
				 ob.setJcode(x);

				 ob.setJname(rs.getString(2));
			
				 ob.setJissn(rs.getString(3));
				
				 ob.setJfreq(rs.getString(4));
				
				 int s_code=rs.getInt(5);
				 String d_code=rs.getString(6);
				 int p_code=rs.getInt(7);
				 ob.setJcountry(rs.getString(8));
				
				 ob.setJlang(rs.getString(9));
				
				 ob.setJdeli(rs.getString(10));
			
				 ob.setJtype(rs.getString(11));
			
				 ob.setJremarks(rs.getString(12));
				 
				 ob.setDoc_Type(rs.getString(13));

				 
				 int d_scode=Integer.parseInt(d_code);
				

				 
				    Prest = con.prepareStatement(DataQuery.JOURNAL_SEARCH_PUB);
					Prest.setInt(1, p_code);
					rs = Prest.executeQuery();			
						if(rs.next())
						{							
						  ob.setJpname(rs.getString(1));						 
						}
				 
						Prest = con.prepareStatement(DataQuery.JOURNAL_SEARCH_SUB);
						Prest.setInt(1, s_code);
						rs = Prest.executeQuery();			
							if(rs.next())
							{							
								ob.setJsname(rs.getString(1));						 
							}
							Prest = con.prepareStatement(DataQuery.JOURNAL_SEARCH_DEPT);
							Prest.setInt(1, d_scode);
							rs = Prest.executeQuery();			
								if(rs.next())
								{							
									ob.setJdname(rs.getString(1));						 
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

		return ob;
	}
	
	
	public int findJnlInterface(int code) {
		log4jLogger.info("start===========findJnlInterface=====");
	int count=0;
		
		try {
			
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.JOURNAL_INTERFACE);
			Prest.setInt(1, code);
			rs = Prest.executeQuery();
			if (rs.next()) {
				count=1;
				
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
	
	public int findJnlDelete(int code) {
		log4jLogger.info("start===========findJnlDelete=====");
		int count=0;
		
		try {
			
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.JOURNAL_DELETE);
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

		return count;
	}
	
	
	public int findJnlSave(journalbean newbean) {
		log4jLogger.info("start===========findJnlSave=====");
		int count=0;
		
		int Pub_Code=0,Dept_Code=0,Sub_Code=0;
		try {
			
			con = getSession().connection();
			
			Prest = con.prepareStatement(DataQuery.JOURNAL_SAVE_PUB);
			Prest.setString(1, newbean.getJpname());
			rs = Prest.executeQuery();
			if (rs.next()) {
				
				 Pub_Code=rs.getInt(1);
			}
			
			Prest = con.prepareStatement(DataQuery.JOURNAL_SAVE_DEPT);
			Prest.setString(1, newbean.getJdname());
			rs = Prest.executeQuery();
			if (rs.next()) {
				
				 Dept_Code=rs.getInt(1);
			}
			
			Prest = con.prepareStatement(DataQuery.JOURNAL_SAVE_SUB);
			Prest.setString(1, newbean.getJsname());
			rs = Prest.executeQuery();
			if (rs.next()) {
				
				 Sub_Code=rs.getInt(1);
			}
			
			
			Prest = con.prepareStatement(DataQuery.JOURNAL_INSERT);
			Prest.setString(1, newbean.getJcode());
			Prest.setString(2, newbean.getJname());
			Prest.setString(3, newbean.getJissn());
			Prest.setString(4, newbean.getJfreq());			
			Prest.setInt(5, Sub_Code);
			Prest.setInt(6, Dept_Code);
			Prest.setInt(7, Pub_Code);			
			Prest.setString(8, newbean.getJcountry());			
			Prest.setString(9, newbean.getJlang());
			Prest.setString(10, newbean.getJdeli());
			Prest.setString(11, newbean.getJtype());
			Prest.setString(12, newbean.getJremarks());
			Prest.setString(13, newbean.getDoc_Type());
			
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
	public int findJnlUpdate(journalbean newbean) {
		log4jLogger.info("start===========findJnlUpdate=====");
		int count=0;
		int Pub_Code=0,Dept_Code=0,Sub_Code=0;
		try {
			
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.JOURNAL_SAVE_PUB);
			Prest.setString(1, newbean.getJpname());
			rs = Prest.executeQuery();
			if (rs.next()) {
				
				 Pub_Code=rs.getInt(1);
			}
			
			Prest = con.prepareStatement(DataQuery.JOURNAL_SAVE_DEPT);
			Prest.setString(1, newbean.getJdname());
			rs = Prest.executeQuery();
			if (rs.next()) {
				
				 Dept_Code=rs.getInt(1);
			}
			
			Prest = con.prepareStatement(DataQuery.JOURNAL_SAVE_SUB);
			Prest.setString(1, newbean.getJsname());
			rs = Prest.executeQuery();
			if (rs.next()) {
				
				 Sub_Code=rs.getInt(1);
			}
			
			
			Prest = con.prepareStatement(DataQuery.JOURNAL_UPDATE);
			Prest.setString(13, newbean.getJcode());
			Prest.setString(1, newbean.getJname());
			Prest.setString(2, newbean.getJissn());
			Prest.setString(3, newbean.getJfreq());			
			Prest.setInt(4, Sub_Code);
			Prest.setInt(5, Dept_Code);
			Prest.setInt(6, Pub_Code);			
			Prest.setString(7, newbean.getJcountry());			
			Prest.setString(8, newbean.getJlang());
			Prest.setString(9, newbean.getJdeli());
			Prest.setString(10, newbean.getJtype());
			Prest.setString(11, newbean.getJremarks());
			Prest.setString(12, newbean.getDoc_Type());
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
	
	
	public journalbean findJnlSearchName(journalbean newbean) {
		log4jLogger.info("start===========findJnlSearchName=====");
		ArrayList finalResults = new ArrayList();
		journalbean ob=new journalbean();
		ArrayList ser=new ArrayList ();
		String flag,f1,f2,f3,f4,f5;
		
		try {
			
			
			if (newbean.getJremarks().equals("Nam"))
			{
			con = getSession().connection();
			st = con.createStatement();
			if (newbean.getJname() == "") {
				rs = st.executeQuery(DataQuery.JOURNAL_SEARCH_NAME );
			}else{
				rs = st.executeQuery(DataQuery.JOURNAL_SEARCH_NAME_LIKE
						
						+newbean.getJname() + "%'  order by jnl_name");
			}
		
			while (rs.next()) {
				
				f1=rs.getString("Jnl_Code");
		           f2=rs.getString("Jnl_Name");
		           f3=rs.getString("doc_Type");


					ser.add(f1);
					ser.add(f2);
					ser.add(f3);
				
			    }
			ob.setAl(ser);
			}

			if (newbean.getJremarks().equals("Dept"))
			{
			con = getSession().connection();
			st = con.createStatement();
			if (newbean.getJname() == "") {
				rs = st.executeQuery(DataQuery.DEPTSEARCH_NAME );
			}else{
				rs = st.executeQuery(DataQuery.DEPTSEARCH_NAME_LIKE
						
						+newbean.getJname() + "%'  order by dept_name");
			}
		
			while (rs.next()) {
				
				f1=rs.getString("dept_Code");
		           f2=rs.getString("dept_Name");
		           f3=rs.getString("Short_Desc");


					ser.add(f1);
					ser.add(f2);
					ser.add(f3);
				
			    }
			ob.setAl(ser);
			}
			if (newbean.getJremarks().equals("Pub"))
			{
			con = getSession().connection();
			st = con.createStatement();
			if (newbean.getJname() == "") {
				rs = st.executeQuery(DataQuery.PUBSUBSEARCH_STRING_PUB );
			}else{
				rs = st.executeQuery(DataQuery.PUBSUBSEARCH_STRING_LIKE_PUB
						
						+newbean.getJname() + "%'  and sp_type='PUBLISHER' order by sp_name");
			}
		
			while (rs.next()) {
				
				f1=rs.getString("sp_code");
		           f2=rs.getString("sp_name");
		           f3=rs.getString("short_desc");


					ser.add(f1);
					ser.add(f2);
					ser.add(f3);
				
			    }
			ob.setAl(ser);
			}
			if (newbean.getJremarks().equals("Sub"))
			{
			con = getSession().connection();
			st = con.createStatement();
			if (newbean.getJname() == "") {
				rs = st.executeQuery(DataQuery.SUBJECTSEARCH_NAME );
			}else{
				rs = st.executeQuery(DataQuery.SUBJECTSEARCH_NAME_LIKE
						
						+newbean.getJname() + "%'   order by sub_name");
			}
		
			while (rs.next()) {
				
				 f1=rs.getString("sub_Code");
		           f2=rs.getString("sub_Name");
		           f3=rs.getString("short_desc");


					ser.add(f1);
					ser.add(f2);
					ser.add(f3);
				
			    }
			ob.setAl(ser);
			}
			if (newbean.getJremarks().equals("Atl"))
			{
			con = getSession().connection();
			st = con.createStatement();
			if (newbean.getJname() == "") {
				rs = st.executeQuery(DataQuery.ATLSEARCH_NAME );
			}else{
				rs = st.executeQuery(DataQuery.ATLSEARCH_NAME_LIKE
						
						+newbean.getJname() + "%'   order by atl_no");
			}
		
			while (rs.next()) {
				
				f1=rs.getString("atl_no");
		           f2=rs.getString("atl_title");
		           f3=rs.getString("atl_author");


					ser.add(f1);
					ser.add(f2);
					ser.add(f3);
				
			    }
			ob.setAl(ser);
			}
			if (newbean.getJremarks().equals("Iss"))
			{
			con = getSession().connection();
			st = con.createStatement();
			if (newbean.getJname() == "") {
				if((!newbean.getJcode().equalsIgnoreCase(""))&&(!newbean.getJcode().equalsIgnoreCase("null")))
				{
					Prest = con.prepareStatement(DataQuery.JOURNAL_ISSUES_jnlcode);
					Prest.setInt(1, Integer.parseInt(newbean.getJcode()));
					rs=Prest.executeQuery();
				}
				else
				{
					rs = st.executeQuery(DataQuery.JOURNAL_ISSUES);
				}
			}else{
				if((!newbean.getJcode().equalsIgnoreCase(""))&&(!newbean.getJcode().equalsIgnoreCase("null")))
				{
					Prest = con.prepareStatement(DataQuery.JOURNAL_ISSUES_LIKE_jnlcode
							
							+newbean.getJname() + "%'   order by Issue_Access_No");
					Prest.setInt(1, Integer.parseInt(newbean.getJcode()));
					rs=Prest.executeQuery();
				}
				else
				{
					rs = st.executeQuery(DataQuery.JOURNAL_ISSUES_LIKE
						
						+newbean.getJname() + "%'   order by Issue_Access_No");
				}
			}
		
			while (rs.next()) {
				
					f1=rs.getString("Issue_Access_No");
					f2=rs.getString("Issue_No");
					f3=rs.getString("Issue_volume");
					f4=rs.getString("Issue_Month");
					f5=rs.getString("Issue_year");
					ser.add(f1);
					ser.add(f2);
					ser.add(f3);
					ser.add(f4);
					ser.add(f5);
				
			    }
			ob.setAl(ser);
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

		return ob;
	}
	//==================Journal Issues=====================================
	
	
	public JnlIssBean findJnlIssuesMax() {
		log4jLogger.info("start===========findJnlIssuesMax=====");
		JnlIssBean newbean=null;
		
	/**	try {
			
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.JOURNAL_ISSUES_MAX);
			rs = Prest.executeQuery();
			if (rs.next()) {
				newbean = new JnlIssBean();
				newbean.setMax(rs.getInt("maxno") + 1);
				
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

		return newbean; */
		
		
		//log4jLogger.info("Inside accessno checking weather is Numeric or Alpha Numeric");
		String New_no="";
		newbean = new JnlIssBean();
		java.sql.ResultSet rs1 = null;
		
		try {		
		
		con = getSession().connection();
		Prest=con.prepareStatement("SELECT MAX(CONVERT(issue_access_no,SIGNED INTEGER)) AS accno FROM journal_issues WHERE issue_access_no NOT REGEXP '^[0-9]'");
		rs=Prest.executeQuery();
		
		if(rs.next()) {
			
		if(rs.getString("accno")==null) {
			
			int rk=0;
			con = getSession().connection();
			Prest=con.prepareStatement("SELECT MAX(CONVERT(issue_access_no,SIGNED INTEGER)+1) AS accno FROM journal_issues WHERE issue_access_no REGEXP '^[0-9]'");
			rs1=Prest.executeQuery();
			
			if(rs1.next()) {				  
				rk=rs1.getInt("accno");
			}
			New_no=""+rk;
			//log4jLogger.info("Value for Numeric :"+New_no);
			newbean.setIss_acc_no(New_no);
		}		
		else if(rs.getString("accno").equalsIgnoreCase("0")) {
			
			int i,len,nlength;
			String rk="",viewchar="",achar="";
			con = getSession().connection();
			Prest=con.prepareStatement("SELECT MAX(issue_access_no) as accno FROM journal_issues WHERE issue_access_no NOT REGEXP '^[0-9]'");
			rs1=Prest.executeQuery();
			
			if(rs1.next()) {
				  
				rk=rs1.getString("accno");				
				len=rk.length();
				
				for(i=0;i<len;i++)
				{					
					achar = rk.substring(i,i+1);
					
					if(IsNumeric(achar))
					{
						break;						
					}else{
						viewchar = viewchar + achar;
					}					
				}
				
				i = i+1;
				nlength = len - i + 1;					
				i = i-1;

				if(viewchar.isEmpty())
				{
					log4jLogger.info("**************** No Code for New Button in Book Master");
				}
				else{
					rs1=null;
					int lenaccessno,maxaccno;
					String zerochar="";
					
					con = getSession().connection();
					Prest=con.prepareStatement("SELECT MAX(CONVERT(RIGHT(issue_access_no, (LENGTH(issue_access_no)-"+ i +")),SIGNED INTEGER)) AS issue_access_no FROM journal_issues WHERE issue_access_no Like '"+viewchar+"%'");
					rs1=Prest.executeQuery();
					
					if(rs1.next())
					{
					  lenaccessno = rs1.getInt("issue_access_no") + 1;
					  maxaccno = lenaccessno;					  
					  String lenacc=""+lenaccessno;
					  
					  if(nlength > lenacc.length()) 
					  {						  
						  lenaccessno = nlength - lenacc.length();
						  
						  if(lenaccessno == 1)
						  {
							  zerochar="0";
						  }else if(lenaccessno == 2)
						  {
							  zerochar="00";
						  }else if(lenaccessno == 3)
						  {
							  zerochar="000";
						  }else if(lenaccessno == 4)
						  {
							  zerochar="0000";
						  }else if(lenaccessno == 5)
						  {
							  zerochar="00000";
						  }else if(lenaccessno == 6)
						  {
							  zerochar="000000";
						  }else if(lenaccessno == 7)
						  {
							  zerochar="0000000";
						  }
						  
						  New_no = viewchar + zerochar + maxaccno;					        
					  }else {      
						  New_no = viewchar + maxaccno;  						  
					  }					  
					}				
				}			
			}
			
			//log4jLogger.info("Value for Alpha Numeric :"+New_no);
			newbean.setIss_acc_no(New_no);
		}
		else
		{
			log4jLogger.info("No suggestion Available --> New Button in Book Master ");
		}			
		}
		else {
		    log4jLogger.info("******************** No Use of this code: --> New Button in Book Master ********************************"+rs);
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

		return newbean;
	}	
	
	private boolean IsNumeric(String string)
	{
		//log4jLogger.info("Inside IsNumeric Checking");
		try
		{
			Integer.parseInt(string);
			return  true;
		}catch(Exception e){
			return  false;
		}			
	}
	
	public JnlIssBean findJnlIssuesSearchName(String name) {
		log4jLogger.info("start===========findJnlSearchName=====");

		ArrayList finalResults = new ArrayList();
		JnlIssBean ob=new JnlIssBean();
		ArrayList ser=new ArrayList ();
		String flag,f1,f2,f3;
		
		try {
			
			
			
			con = getSession().connection();
			st = con.createStatement();
			if (name == "") {
				rs = st.executeQuery(DataQuery.JOURNAL_SEARCH_NAME );
			}else{
				rs = st.executeQuery(DataQuery.JOURNAL_SEARCH_NAME_LIKE
						
						+name + "%'  order by jnl_name");
			}
		
			while (rs.next()) {
				
				f1=rs.getString("Jnl_Code");
		           f2=rs.getString("Jnl_Name");
		           f3=rs.getString("doc_Type");


					ser.add(f1);
					ser.add(f2);
					ser.add(f3);
				
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

		return ob;
	}
	
	public JnlIssBean findJnlFindIssues(JnlIssBean newbean) {
		log4jLogger.info("start===========findJnlFindIssues====="+newbean);
	int count=0;		
	JnlIssBean ob=new JnlIssBean();
	String jnlcode="";
		try {
			String fromDt="",toDt="",available="";
			fromDt=newbean.getFromDt();
			toDt=newbean.getToDt();
			available=newbean.getFlag();
		
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.JOURNAL_SEARCH_Name);
			Prest.setString(1, newbean.getJnl_name());
			rs = Prest.executeQuery();
			if (rs.next()) {
				ob.setJnl_name(rs.getString("jnl_name"));
				ob.setJnl_code(rs.getInt("jnl_code"));
				count=1;
				ob.setMax(count);
			}
			
			
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.JOURNAL_SEARCH);
			Prest.setInt(1, ob.getJnl_code());
			rs = Prest.executeQuery();
			if (rs.next()) {
				ob.setJnl_name(rs.getString("jnl_name"));
				
				count=1;
				ob.setMax(count);
				
			}
			ArrayList finalResults = Issue_details(String.valueOf(ob.getJnl_code()),fromDt,toDt,available);
			
			if(finalResults.size()>0) {
			    ob.setAl(finalResults);	
			  
			}
			  ob.setFromDt(fromDt);
			    ob.setToDt(toDt);
			    ob.setFlag(available);
			    log4jLogger.info("##############"+ob.getFromDt());
			    log4jLogger.info("##############"+ob.getToDt());
			    log4jLogger.info("##############"+ob.getFlag());
			    
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
	
	public ArrayList Issue_details(String loadmemcode,String fromDt,String toDt,String available)
	 {
		ArrayList ser=new ArrayList ();
	
	try{
		if(available.equals("All")){
			available="";
		}
		String sss="";					
		sss=" and availability like '%"+available+"%'"+"order by convert(issue_no, signed integer)";
		log4jLogger.info("LogFile Value "+sss);
				con = getSession().connection();
				Prest = con.prepareStatement(DataQuery.SELECT_STRING_Jnlissues+sss);
				Prest.setString(1, loadmemcode);
				Prest.setString(2, fromDt);
				Prest.setString(3, toDt);
				rs = Prest.executeQuery();
	while (rs.next()) {
				String f1=rs.getString("Issue_Access_No");
				String f2=rs.getString("Issue_year");
				String f3=rs.getString("Issue_Month");
				String f4=rs.getString("Issue_Volume");
				String f5=rs.getString("Issue_No");
				String f6=rs.getString("Issue_Date");
				String f7=rs.getString("Received_Date");
				String f8=rs.getString("Availability");
				String f9=rs.getString("Remarks");
				String f10=rs.getString("content_page");
				
				ser.add(f1);
				ser.add(f2);
				ser.add(f3);
				ser.add(f4);
				ser.add(f5);
				ser.add(f6);
				ser.add(f7);
				ser.add(f8);
				ser.add(f9);
				ser.add(f10);
											    
			    }	
			//ob.setAl(ser);	
			
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
	
	  return ser;
	 }
	
	
	
	
	public int findJnlIssuesAccno(String code) {
		log4jLogger.info("start===========findJnlIssuesAccno=====");
	int count=0;

		try {
			
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.SELECT_STRING_Jnlissues_ACCNO);
			Prest.setString(1, code);
			rs = Prest.executeQuery();
			if (rs.next()) {
				count=1;				
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
	
	
	public JnlIssBean findJnlIssuesUpdate(JnlIssBean newbean) {
		log4jLogger.info("start====Issue=======findJnlIssuesUpdate=====");
	int count=0;
	String jnl_code="";
	JnlIssBean ob=new JnlIssBean();
	
	String fromDt="",toDt="",available="";
	fromDt=newbean.getFromDt();
	toDt=newbean.getToDt();
	available=newbean.getFlag();
	
		try {
		
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.JOURNAL_SEARCH_CODE);
			Prest.setString(1, newbean.getJnl_name());
			rs = Prest.executeQuery();
			if (rs.next()) {
					
				jnl_code=rs.getString("jnl_code");
			}
			
			
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.JNL_ISSUES_UPDATE_STRING);
			Prest.setString(11, newbean.getIss_acc_no());
			Prest.setString(1, jnl_code);
			Prest.setString(2, newbean.getIss_year());
			Prest.setString(3, newbean.getIss_month());
			Prest.setString(4, newbean.getIss_vol());
			Prest.setString(5, newbean.getIss_no());
			Prest.setString(6, newbean.getIss_date());
			Prest.setString(7, newbean.getRec_date());
			Prest.setString(8, newbean.getAvail());
			Prest.setString(9, newbean.getRemarks());
			Prest.setString(10, newbean.getPage());
			
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
			
		ArrayList finalResults = Issue_details(String.valueOf(ob.getJnl_code()),fromDt,toDt,available);
		
		if(finalResults.size()>0) {
		    ob.setAl(finalResults);		
		}
		
		//Issue_details(String.valueOf(jnl_code));

		return ob;
	}
	public JnlIssBean findJnlIssuesSave(JnlIssBean newbean) {
		log4jLogger.info("start===========findJnlIssuesSave=====");
	int count=0;
	JnlIssBean ob=new JnlIssBean();
		try {
			/*con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.JOURNAL_SEARCH_CODE);
			Prest.setString(1, newbean.getJnl_name());
			rs = Prest.executeQuery();
			if (rs.next()) {
				jnl_code=rs.getString("jnl_code");
			}*/
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.JNL_ISSUES_INSERT_STRING);
			
			Prest.setString(1, newbean.getIss_acc_no());
			Prest.setInt(2, newbean.getJnl_code());
			Prest.setString(3, newbean.getSub_code());
			Prest.setString(4, newbean.getIss_year());
			Prest.setString(5, newbean.getIss_month());
			Prest.setString(6, newbean.getIss_vol());
			Prest.setString(7, newbean.getIss_no());
			Prest.setString(8, newbean.getPart_no());
			Prest.setString(9, newbean.getIss_date());
			Prest.setString(10, newbean.getIss_date());
			Prest.setString(11, newbean.getIss_date());
			
			Prest.setString(12, newbean.getAvail());
			Prest.setString(13, newbean.getBvol_no());
			Prest.setString(14, newbean.getRemarks());
			Prest.setFloat(15, newbean.getIss_amount());
			Prest.setString(16, newbean.getPage());
			Prest.executeUpdate();
			
			log4jLogger.info("::::::::::::::::inserted:::::::::::::::::::");
		
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
	public JnlIssBean getJnlSearch(JnlIssBean newbean) {
		log4jLogger.info("start===========getJnlSearch====="+newbean.getIss_acc_no());
	int count=0;
	JnlIssBean ob=new JnlIssBean();
	String jnlcode="";
	String fromDt="",toDt="",available="";
	fromDt=newbean.getFromDt();
	toDt=newbean.getToDt();
	available=newbean.getFlag();
	
		try {
			
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.JOURNAL_SEARCH_CODE);
			Prest.setString(1, newbean.getJnl_name());
			rs = Prest.executeQuery();
			if (rs.next()) {
				jnlcode=rs.getString("jnl_code");
				ob.setJnl_code(Integer.parseInt(jnlcode));
				
			}
						
			Prest = con.prepareStatement(DataQuery.SELECT_STRING_Issues);
			Prest.setString(1, jnlcode);
			Prest.setString(2, newbean.getIss_acc_no());
			rs = Prest.executeQuery();
			if (rs.next()) {
				
				ob=new JnlIssBean();
				ob.setIss_acc_no(rs.getString("Issue_Access_No"));
				ob.setIss_year(rs.getString("Issue_year"));
				ob.setIss_month(rs.getString("Issue_Month"));
				ob.setIss_vol(rs.getString("Issue_Volume"));
				ob.setIss_no(rs.getString("Issue_No"));
				ob.setIss_date(rs.getString("Issue_Date"));
				ob.setRec_date(rs.getString("Received_Date"));
				ob.setAvail(rs.getString("Availability"));				
				ob.setRemarks(rs.getString("Remarks"));
				ob.setPage(rs.getString("content_page"));			
				ob.setJnl_name(newbean.getJnl_name());
				
				count=1;
				ob.setMax(count);
			}
			
			
			//Issue_details(jnlcode);
			
			ArrayList finalResults = Issue_details(String.valueOf(ob.getJnl_code()),fromDt,toDt,available);			
			
			if(finalResults.size()>0) {
			    ob.setAl(finalResults);		
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
	
	public JnlIssBean findJnlDelete(JnlIssBean newbean) {
		log4jLogger.info("start===========findJnlDelete====="+newbean.getIss_acc_no());
	int count=0;
	JnlIssBean ob=new JnlIssBean();
	String jnlcode="";
	
	String fromDt="",toDt="",available="";
	fromDt=newbean.getFromDt();
	toDt=newbean.getToDt();
	available=newbean.getFlag();
		try {
			
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.JOURNAL_SEARCH_CODE);
			Prest.setString(1, newbean.getJnl_name());
			rs = Prest.executeQuery();
			if (rs.next()) {
				jnlcode=rs.getString("jnl_code");
				
				
			}
						
			Prest = con.prepareStatement(DataQuery.JNL_ISSUES_DELETE_STRING);
			Prest.setString(1, jnlcode);
			Prest.setString(2, newbean.getIss_acc_no());
			Prest.executeUpdate();
			
			
			//Issue_details(jnlcode);
			ArrayList finalResults = Issue_details(String.valueOf(ob.getJnl_code()),fromDt,toDt,available);			
			
			if(finalResults.size()>0) {
			    ob.setAl(finalResults);		
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
	
	//=============================get supscriptiondate========================//
		public JnlIssBean findJnlsup_date(JnlIssBean newbean) {
			log4jLogger.info("start===========findSub_Date====="+newbean.getJnl_code());
		int count=0;
		JnlIssBean ob=new JnlIssBean();
		
		
		String fromDt="",toDt="",maxdate="",mindate="",jnl_name="";
		toDt=newbean.getToDt();

			try {
				
				con = getSession().connection();
				Prest = con.prepareStatement(DataQuery.Maxsups_date_query);
				Prest.setString(1, newbean.getJnl_name());				
				rs = Prest.executeQuery();
				if (rs.next()) {
					System.out.println("===============inside find supdate========");
				//	ob.setJnl_code(rs.getInt("jnl_code"));
					ob.setJnl_name(rs.getString("jnl_name"));
					ob.setFromDt(rs.getString("subs_from"));
					ob.setToDt(rs.getString("subs_to"));
			
					System.out.println("journalname...............;"+ob.getJnl_name());
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

	
	
	
	//==============================Journal Atl==================================
	
	public String findJnlAtlMax() {
		log4jLogger.info("start===========findJnlAtlMax=====");
		String code="";
		
		try {
			
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.JOURNAL_ATL_MAX);
			rs = Prest.executeQuery();
			if (rs.next()) {
				
				code=(String.valueOf(rs.getInt("maxno") + 1));
				
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

		return code;
	}
	public journalArtbean findJnlSearchAtl(int code) {
		log4jLogger.info("start===========findJnlSearchAtl====="+code);
		int count=0;
		journalArtbean ob=new journalArtbean();
		try {
			
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.JOURNAL_ATL_SEARCH);
			Prest.setInt(1, code);
			rs = Prest.executeQuery();
			if (rs.next()) {
				
				ob.setAtlno(rs.getString("atl_no"));
				 ob.setJcode(rs.getString("jnl_code"));
				 ob.setJname(rs.getString("jnl_name"));
				 ob.setBvolno(rs.getString("bvol_no"));
				 ob.setTitle(rs.getString("atl_title"));
				 ob.setAuthor(rs.getString("atl_author"));
				 ob.setVolno(rs.getString("vol_no"));
				 ob.setIssueno(rs.getString("issue_no"));
				 ob.setIssueyear(rs.getString("issue_year"));
				 ob.setIssuemonth(rs.getString("issue_month"));
				 ob.setPages(rs.getString("atl_page_nos"));
				 ob.setSubject(rs.getString("atl_subject"));
				 ob.setKeywords(rs.getString("atl_keywords"));
				 ob.setAbstracts(rs.getString("atl_abstract"));
				 ob.setIssue_acc_no(rs.getString("Issue_Access_No"));
				 ob.setAtl_abstracts(rs.getString("Abstract"));
				 
				 count=1;
				
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
	public int findJnlDeleteAtl(int code) {
		log4jLogger.info("start===========findJnlDeleteAtl=====");
		int count=0;
		
		try {
			
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.JOURNAL_ATL_DELETE);
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

		return count;
	}
	public int findJnlAtlUpdate(journalArtbean newbean) {
		log4jLogger.info("start===========findJnlAtlUpdate=====");
		int count=0;
		
		try {
			
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.JOURNAL_ATL_UPDATE);
			
			Prest.setString(1, newbean.getJcode());
			Prest.setString(2, newbean.getJname());
			Prest.setString(16, newbean.getAtlno());
			Prest.setString(3, newbean.getBvolno());
			Prest.setString(4, newbean.getTitle());
			Prest.setString(5, newbean.getAuthor());
			Prest.setString(6, newbean.getVolno());			
			Prest.setString(7, newbean.getIssueno());
			Prest.setString(8, newbean.getIssueyear());
			Prest.setString(9, newbean.getIssuemonth());
			Prest.setString(10, newbean.getPages());
			Prest.setString(11, newbean.getSubject());
			Prest.setString(12, newbean.getKeywords());
			Prest.setString(13, newbean.getAbstracts());
			Prest.setString(14, newbean.getIssue_acc_no());
			Prest.setString(15, newbean.getAtl_abstracts());
			
		
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
	public int findJnlAtlSave(journalArtbean newbean) {
		log4jLogger.info("start===========findJnlAtlSave=====");
		int count=0;
		
		try {
			
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.JOURNAL_ATL_SAVE);
			
			Prest.setString(1, newbean.getAtlno());
			Prest.setString(2, newbean.getJcode());
			Prest.setString(3, newbean.getJname());			
			Prest.setString(4, newbean.getBvolno());
			Prest.setString(5, newbean.getTitle());
			Prest.setString(6, newbean.getAuthor());
			Prest.setString(7, newbean.getVolno());			
			Prest.setString(8, newbean.getIssueno());
			Prest.setString(9, newbean.getIssueyear());
			Prest.setString(10, newbean.getIssuemonth());
			Prest.setString(11, newbean.getPages());
			Prest.setString(12, newbean.getSubject());
			Prest.setString(13, newbean.getKeywords());
			Prest.setString(14, newbean.getAbstracts());
			Prest.setString(15, newbean.getIssue_acc_no());
			Prest.setString(16, newbean.getAtl_abstracts());
			
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
	
	
	
    // Journal Enquiry Processing
	
	
    public List findEnqJNLSearchName(JnlenquiryBean newbean) {
		
		log4jLogger.info("start===========findEnqJNLSearchName====="+newbean.getAdd1());

		SQLQuery sql = null;
		
		
		if ((newbean.getAdd1().equals("Journal")))
		{
			
            if (newbean.getJNLName() == "") {
				
            	sql = getSession().createSQLQuery(DataQuery.JOURNAL_SEARCH_NAME);
			
			}else{
				
				sql = getSession().createSQLQuery(DataQuery.JOURNAL_SEARCH_NAME_LIKE
						
						+newbean.getJNLName() + "%' order by jnl_name");
				
			}
			 
		}
		
		
		
		if ((newbean.getAdd1().equals("Sup")))
		{
			if (newbean.getJNLName() == "") {
				
			    sql = getSession().createSQLQuery(DataQuery.PUBSUBSEARCH_STRING_SUP);
			
			}else{
				
				sql = getSession().createSQLQuery(DataQuery.PUBSUBSEARCH_STRING_LIKE_SUP
						
						+newbean.getJNLName() + "%' and sp_type='SUPPLIER' order by sp_name");
				
			}
		}
		
		
		if ((newbean.getAdd1().equals("EnquiryNo")))
		{
			
            if (newbean.getJNLName() == "") {
				
            	sql = getSession().createSQLQuery(DataQuery.JOURNAL_EnquiryNo_Search+" GROUP BY enq_no order by enq_no");
			
			}else{
				
				sql = getSession().createSQLQuery(DataQuery.JOURNAL_EnquiryNo_Search+" and enq_no like '"
						
						+newbean.getJNLName() + "%' GROUP BY enq_no order by enq_no");
				
			}
			 
		}
		
		
		return  sql.list();
	

	}

    
    public List findEnqSelectedJNL(JnlenquiryBean newbean)
    {
    	log4jLogger.info("start===========findEnqSelectedJNL====="+newbean.getJNLName());

		     SQLQuery sql = null;		
			
			 sql = getSession().createSQLQuery(DataQuery.JOURNAL_CheckList+" and jnl_code in(null"+newbean.getJNLName()+")");
			 
			 List list = sql.list();
			 
			  
			 
			  String frmdt="",todt="";
		      
		      
		      frmdt=newbean.getAdd1();      // For Hot code of Parameterized Subscription dates
		      todt=newbean.getAdd2();
		      
		      SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		      
		      Date date1 = null;
		      Date date2 = null;

		      try {
		          date1 = sdf.parse(frmdt);
		          date2 = sdf.parse(todt);
		      } catch (Exception e) {
		          // TODO Auto-generated catch block
		          e.printStackTrace();
		      }
		      long ab=((date2.getTime() - date1.getTime()) / MILLISECONDS_IN_DAY)+1;  // Add +1 for next day 		      
		      int nodays=Math.round(ab);
		      
	      
				List<Object> finalResults = null;
				if (list != null)
				{			
					finalResults = new ArrayList<Object>();
					for (int i = 0; i < list.size(); i++)
					{				
						Object[] obj = (Object[]) list.get(i);
						JnlenquiryDetailsBean ordDetails = new JnlenquiryDetailsBean();
						ordDetails.setJnlCode(Integer.valueOf(obj[0].toString()));
						ordDetails.setJournal(obj[1].toString());
						//ordDetails.setAdd1(obj[2].toString());
						//ordDetails.setAdd2(obj[3].toString());
						
						ordDetails.setQuoteNo(newbean.getQuoteNo());
						ordDetails.setQuoteDate(newbean.getQuotedate());
						ordDetails.setSupCode(newbean.getSupCode());
						ordDetails.setSupplier(newbean.getRemarks());
						
						ordDetails.setSubsfrmdate(frmdt);
						ordDetails.setSubstodate(todt);
						ordDetails.setFrequency(obj[3].toString());
						
						int noiss=0;						
						if(obj[3].toString().equals("DAILY"))
						{							
							 noiss =Math.round(nodays);							
						}
						else if(obj[3].toString().equals("WEEKLY TWO"))
						{							
							 noiss =(int) Math.round(nodays/3.5);							
						}
						else if(obj[3].toString().equals("WEEKLY"))
						{							
							 noiss =Math.round(nodays/7);							
						}
						else if(obj[3].toString().equals("FORTNIGHTLY"))
						{							
							 noiss =Math.round(nodays/14);							
						}
						else if(obj[3].toString().equals("MONTHLY"))
						{							
							 noiss =Math.round(nodays/30);							
						}
						else if(obj[3].toString().equals("BIMONTHLY"))
						{							
							 noiss =Math.round(nodays/60);							
						}
						else if(obj[3].toString().equals("QUARTERLY"))
						{							
							 noiss =Math.round(nodays/90);							
						}
						else if(obj[3].toString().equals("HALF YEARLY"))
						{							
							 noiss =Math.round(nodays/180);							
						}
						else if(obj[3].toString().equals("ANNUAL"))
						{							
							 noiss =Math.round(nodays/365);							
						}
						else if(obj[3].toString().equals("OTHERS"))
						{							
							// noiss =Math.round(0);							
						}
						
						ordDetails.setNoofissue(noiss);
						ordDetails.setVolumeNo("0");
						
						finalResults.add(ordDetails);				
					}
				}
				log4jLogger.info("End===========findEnqSelectedJNL=====");
				return finalResults;	
    	
    }

	
    public List findEnqJNLMasSave(JnlenquiryBean newbean)
    {
    	log4jLogger.info("Inside ====== findEnqJNLMasSave ========");
    	JnlenquiryBean ob=new JnlenquiryBean();
    	List<Object> enq=new ArrayList<Object>();
    	try {
    		
    	if(JNLEnqMasCheck(newbean))
    	{
    	  getSession().save(newbean);
    	  getSession().flush();
    	  getSession().clear();   
    	  
    	}
    	else {
    		ob.setAdd1("AlreadyExists");
    		enq.add(ob);
    	}
    	
    	}catch(Exception e)
    	{    		
    		e.printStackTrace();
    	}
    	    	
    	log4jLogger.info("End ====== findEnqJNLMasSave ========");
    	
      return enq;    	
    }
    
    
    public boolean JNLEnqMasCheck(JnlenquiryBean newbean)
    {
    	
    	boolean check=false;
    	
    	if (newbean.getQuoteNo() != null)
		{
    		Criteria crit = getSession().createCriteria(JnlenquiryBean.class);
			crit.add(Restrictions.eq("QuoteNo", newbean.getQuoteNo()));
			List list = crit.list();
			if (list == null || list.size() == 0)
			{
				check = true;
			}    		
		}
    	
    	return check;
    }
	
    public List findEnqJNLDetailsSave(List details) 
    {
    	log4jLogger.info("Inside ====== findEnqJNLDetailsSave ========");   	
    	
    	List<Object> user = null;
		for (int i = 0; i < details.size(); i++)
		{
			user = new ArrayList<Object>();
			JnlenquiryDetailsBean detailsbean=(JnlenquiryDetailsBean) details.get(i);
			
					getSession().save(detailsbean);
					getSession().flush();
					getSession().clear();
				
	    }
		
		log4jLogger.info("End ====== findEnqJNLDetailsSave ========");
		
	  return user;
	}
	
    
    public List findEnqJNLFullview(String enqno)
    {   	
    	log4jLogger.info("start===========findEnqJNLFullview====="+enqno);
    	String query = getSession().getNamedQuery("EnquiryDetailFullView").getQueryString();
		SQLQuery sql = getSession().createSQLQuery(query);
		sql.setParameter("enqno", enqno);
				
		List list = sql.list();
		List<Object> finalResults = null;
		if (list != null)
		{			
			finalResults = new ArrayList<Object>();
			for (int i = 0; i < list.size(); i++)
			{				
				Object[] obj = (Object[]) list.get(i);
				JnlenquiryDetailsBean ordDetails = new JnlenquiryDetailsBean();
				
				ordDetails.setQuoteNo(obj[0].toString());
				ordDetails.setQuoteDate(Security.getdate(obj[1].toString()));
				ordDetails.setSupCode(Integer.parseInt(obj[2].toString()));
				ordDetails.setSupplier(obj[3].toString());
				ordDetails.setJnlCode(Integer.parseInt(obj[4].toString()));
				ordDetails.setJournal(obj[5].toString());
				ordDetails.setSubsfrmdate(Security.getdate(obj[6].toString()));
				ordDetails.setSubstodate(Security.getdate(obj[7].toString()));
				ordDetails.setVolumeNo(obj[8].toString());
				ordDetails.setNoofissue(Integer.parseInt(obj[9].toString()));
				ordDetails.setBcurrency(obj[10].toString());
				ordDetails.setBcost(Double.parseDouble(obj[11].toString()));
				ordDetails.setBprice(Double.parseDouble(obj[12].toString()));
				ordDetails.setDiscount(Double.parseDouble(obj[13].toString()));
				ordDetails.setNetamount(Double.parseDouble(obj[14].toString()));
				ordDetails.setRemarks(obj[15].toString());
				ordDetails.setAdd1(obj[16].toString());
				ordDetails.setAdd2(obj[17].toString());		
				ordDetails.setFrequency(obj[18].toString());	
				
				finalResults.add(ordDetails);				
			}
		}
		log4jLogger.info("End===========findEnqJNLFullview=====");
		return finalResults;
    }
		
    
    public void findEnqJNLDelete(String enqno)  {
	
    	log4jLogger.info("Start===========findEnqJNLDelete=====");
    	
    	String query = getSession().getNamedQuery("DeleteJNLEnquiryMas").getQueryString();
		SQLQuery sql = getSession().createSQLQuery(query);
		sql.setParameter("enqno", enqno);
		sql.executeUpdate();    	
    	
		log4jLogger.info("===========EnquiryMaster Deleted SuccessFully=====");
		
		String query1 = getSession().getNamedQuery("DeleteJNLEnquiryDetail").getQueryString();
		SQLQuery sql1 = getSession().createSQLQuery(query1);
		sql1.setParameter("enqno", enqno);
		sql1.executeUpdate(); 
		
		log4jLogger.info("===========EnquiryDetailsMaster Deleted SuccessFully=====");
		
    }
    
    public List findOrdJNLCheck(String enqno)   {
    	log4jLogger.info("Start===========findOrdJNLCheck=====");
    	
    	String query = getSession().getNamedQuery("CheckEnqOrdJNL").getQueryString();
		SQLQuery sql = getSession().createSQLQuery(query);
		sql.setParameter("enqno", enqno);
		List list = sql.list(); 

		log4jLogger.info("End===========findOrdJNLCheck=====");
		
    	return list;
    }
    
    
	
	// Journal Order Processing
	
	
    public List findOrdJNLSearchName(JnlorderBean newbean) {
		
		log4jLogger.info("start===========findOrdJNLSearchName====="+newbean.getAdd1());
		//ArrayList<Object> finalResults = new ArrayList<Object>();
		//JnlorderBean ob=null;
		SQLQuery sql = null;
		
	/*	if ((newbean.getAdd1().equals("selectV"))) {
			log4jLogger.info("start===========findOrdJNLSearchName====="+newbean.getJNLName());
			 sql = getSession().createSQLQuery(DataQuery.JOURNAL_CheckList+" and jnl_code in(null"+newbean.getJNLName()+")");
			 
			 log4jLogger.info("start===========findOrdJNLSearchName====="+sql);
			
		}*/
		
		
		
		if ((newbean.getAdd1().equals("Journal")))
		{
			
            if (newbean.getJNLName() == "") {
				
            	sql = getSession().createSQLQuery(DataQuery.JOURNAL_SEARCH_NAME);
			
			}else{
				
				sql = getSession().createSQLQuery(DataQuery.JOURNAL_SEARCH_NAME_LIKE
						
						+newbean.getJNLName() + "%' order by jnl_name");
				
			}
			 
		}
		
		
		
		if ((newbean.getAdd1().equals("Sup")))
		{
			if (newbean.getJNLName() == "") {
				
			    sql = getSession().createSQLQuery(DataQuery.PUBSUBSEARCH_STRING_SUP);
			
			}else{
				
				sql = getSession().createSQLQuery(DataQuery.PUBSUBSEARCH_STRING_LIKE_SUP
						
						+newbean.getJNLName() + "%' and sp_type='SUPPLIER' order by sp_name");
				
			}
		}
		
		
		if ((newbean.getAdd1().equals("OrderNo")))
		{
			
            if (newbean.getJNLName() == "") {
				
            	sql = getSession().createSQLQuery(DataQuery.JOURNAL_OrderNo_Search+" GROUP BY jnl_order_no order by jnl_order_no");
			
			}else{
				
				sql = getSession().createSQLQuery(DataQuery.JOURNAL_OrderNo_Search+" and jnl_order_no like '"
						
						+newbean.getJNLName() + "%' GROUP BY jnl_order_no order by jnl_order_no");
				
			}
			 
		}
		
		if ((newbean.getAdd1().equals("EnquiryNo")))
		{
			
            if (newbean.getJNLName() == "") {
				
            	sql = getSession().createSQLQuery(DataQuery.JOURNAL_EnquiryNo_Search+" GROUP BY enq_no order by enq_no");
			
			}else{
				
				sql = getSession().createSQLQuery(DataQuery.JOURNAL_EnquiryNo_Search+" and enq_no like '"
						
						+newbean.getJNLName() + "%' GROUP BY enq_no order by enq_no");
				
			}
			 
		}
		
		
		return  sql.list();
	
/*		try{
		
			
			
			if ((newbean.getAdd1().equals("Sup")))
			{
			    con = getSession().connection();
			    st = con.createStatement();
			    
			if (newbean.getSupname() == "") {
				rs = st.executeQuery(DataQuery.PUBSUBSEARCH_STRING_SUP );
			}else{
				rs = st.executeQuery(DataQuery.PUBSUBSEARCH_STRING_LIKE_SUP
						
						+newbean.getSupname() + "%' and sp_type='SUPPLIER' order by sp_name");
			}
		
			while (rs.next()) {
				
				   ob=new JnlorderBean();
				   ob.setSno(rs.getInt("SP_Code"));
		           ob.setSupname(rs.getString("SP_Name"));
		           ob.setRemarks(rs.getString("Short_Desc"));
		           finalResults.add(ob);
	   	
				}			
			}	
			
			
			
			if ((newbean.getAdd1().equals("Journal")))
			{
			    con = getSession().connection();
			    st = con.createStatement();
			    
			if (newbean.getSupname() == "") {
				rs = st.executeQuery(DataQuery.JOURNAL_SEARCH_NAME );
			}else{
				rs = st.executeQuery(DataQuery.JOURNAL_SEARCH_NAME_LIKE
						
						+newbean.getSupname() + "%' order by jnl_name");
			}
		
			while (rs.next()) {
				   ob=new JnlorderBean();
				   ob.setSno(rs.getInt("Jnl_Code"));
		           ob.setJNLName(rs.getString("Jnl_Name"));
		           ob.setRemarks(rs.getString("doc_Type"));
		           finalResults.add(ob);	   	
				}	
			}
				
		}catch (SQLException e) {
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
	
	return finalResults; */
	}

    
    public List findOrdSelectedJNL(JnlorderBean newbean)
    {
    	log4jLogger.info("start===========findOrdSelectedJNL====="+newbean.getJNLName());

		     SQLQuery sql = null;		
			
			 sql = getSession().createSQLQuery(DataQuery.JOURNAL_CheckList+" and jnl_code in(null"+newbean.getJNLName()+")");
			 
			 List list = sql.list();
			 
			  
			 
			  String frmdt="",todt="";
		      
		     /* Calendar cal = Calendar.getInstance();       // For Hot code of Subscription dates
		      cal.add(Calendar.HOUR_OF_DAY, 0);
		      cal.add(Calendar.MINUTE, 0);
		      cal.add(Calendar.SECOND, 0);
		      
		      Date today = cal.getTime();
		      long startTime = cal.getTimeInMillis();		      
		      
		      cal.add(Calendar.YEAR, 1); // to get previous year add -1
		      cal.add(Calendar.DATE, -1);	
		      
		      Date nextYear = cal.getTime();
		      long endTime = cal.getTimeInMillis();
		      
		      long ab=((endTime - startTime) / MILLISECONDS_IN_DAY)+1;  // Add +1 for next day 		      
		      int nodays=Math.round(ab);
		      
		      SimpleDateFormat ft = new SimpleDateFormat ("dd-MM-yyyy");
		      frmdt=ft.format(today);    // For Default Subscription date Hot code 
		      todt=ft.format(nextYear);  */
		      
		    
		      
		      frmdt=newbean.getAdd1();      // For Hot code of Parameterized Subscription dates
		      todt=newbean.getAdd2();
		      
		      SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		      
		      Date date1 = null;
		      Date date2 = null;

		      try {
		          date1 = sdf.parse(frmdt);
		          date2 = sdf.parse(todt);
		      } catch (Exception e) {
		          // TODO Auto-generated catch block
		          e.printStackTrace();
		      }
		      long ab=((date2.getTime() - date1.getTime()) / MILLISECONDS_IN_DAY)+1;  // Add +1 for next day 		      
		      int nodays=Math.round(ab);
		      
		      
				List<Object> finalResults = null;
				if (list != null)
				{			
					finalResults = new ArrayList<Object>();
					for (int i = 0; i < list.size(); i++)
					{				
						Object[] obj = (Object[]) list.get(i);
						JnlorderDetailsBean ordDetails = new JnlorderDetailsBean();
						ordDetails.setJnlCode(Integer.valueOf(obj[0].toString()));
						ordDetails.setJournal(obj[1].toString());
						//ordDetails.setAdd1(obj[2].toString());
						//ordDetails.setAdd2(obj[3].toString());
						
						ordDetails.setOrdNo(newbean.getOrdNo());
						ordDetails.setOrdDate(newbean.getOrdate());
						ordDetails.setQuoteNo(newbean.getQuoteNo());
						ordDetails.setQuoteDate(newbean.getQuotedate());
						ordDetails.setSupCode(newbean.getSupCode());
						ordDetails.setSupplier(newbean.getRemarks());
						
						ordDetails.setSubsfrmdate(frmdt);
						ordDetails.setSubstodate(todt);
						ordDetails.setFrequency(obj[3].toString());
						
						int noiss=0;						
						if(obj[3].toString().equals("DAILY"))
						{							
							 noiss =Math.round(nodays);							
						}
						else if(obj[3].toString().equals("WEEKLY TWO"))
						{							
							 noiss =(int) Math.round(nodays/3.5);							
						}
						else if(obj[3].toString().equals("WEEKLY"))
						{							
							 noiss =Math.round(nodays/7);							
						}
						else if(obj[3].toString().equals("FORTNIGHTLY"))
						{							
							 noiss =Math.round(nodays/14);							
						}
						else if(obj[3].toString().equals("MONTHLY"))
						{							
							 noiss =Math.round(nodays/30);							
						}
						else if(obj[3].toString().equals("BIMONTHLY"))
						{							
							 noiss =Math.round(nodays/60);							
						}
						else if(obj[3].toString().equals("QUARTERLY"))
						{							
							 noiss =Math.round(nodays/90);							
						}
						else if(obj[3].toString().equals("HALF YEARLY"))
						{							
							 noiss =Math.round(nodays/180);							
						}
						else if(obj[3].toString().equals("ANNUAL"))
						{							
							 noiss =Math.round(nodays/365);							
						}
						else if(obj[3].toString().equals("OTHERS"))
						{							
							// noiss =Math.round(0);							
						}
						
						ordDetails.setNoofissue(noiss);
						ordDetails.setVolumeNo("0");
						
						
						
						
						/*
						 
			  Date dNow = new Date( );
		      SimpleDateFormat ft = 
		      new SimpleDateFormat ("E yyyy.MM.dd 'at' hh:mm:ss a zzz");
		      log4jLogger.info("Current Date: " + ft.format(dNow));
		      
		      
		      SimpleDateFormat ft1 = 
			      new SimpleDateFormat ("yyyy-MM-dd");
		      log4jLogger.info("Current Date: " + ft1.format(dNow));
		      
		      SimpleDateFormat ft2 = 
			      new SimpleDateFormat ("dd-MM-yyyy");
		      log4jLogger.info("Current Date: " + ft2.format(dNow));
		      
		      
		      
						
						
						
						ordDetails.setOrdDate(Security.getdate(obj[1].toString()));
						
						ordDetails.setQuoteDate(Security.getdate(obj[3].toString()));
						
						ordDetails.setJnlCode(Integer.parseInt(obj[6].toString()));
						ordDetails.setJournal(obj[7].toString());
						ordDetails.setSubsfrmdate(Security.getdate(obj[8].toString()));
						ordDetails.setSubstodate(Security.getdate(obj[9].toString()));
						ordDetails.setVolumeNo(obj[10].toString());
						ordDetails.setNoofissue(Integer.parseInt(obj[11].toString()));
						ordDetails.setBcurrency(obj[12].toString());
						ordDetails.setBcost(Double.parseDouble(obj[13].toString()));
						ordDetails.setBprice(Double.parseDouble(obj[14].toString()));
						ordDetails.setDiscount(Double.parseDouble(obj[15].toString()));
						ordDetails.setNetamount(Double.parseDouble(obj[16].toString()));
						ordDetails.setRemarks(obj[17].toString());
						ordDetails.setAdd1(obj[18].toString());
						ordDetails.setAdd2(obj[19].toString());		*/		
						
						finalResults.add(ordDetails);				
					}
				}
				log4jLogger.info("End===========findOrdSelectedJNL=====");
				return finalResults;	
    	
    }
    
	
    
    public List findOrdJNLMasSave(JnlorderBean newbean)
    {
    	log4jLogger.info("Inside ====== findOrdJNLMasSave ========");
    	JnlorderBean ob=new JnlorderBean();
    	List<Object> ord=new ArrayList<Object>();
    	try {
    		
    	if(JNLOrdMasCheck(newbean))
    	{
    	  getSession().save(newbean);
    	  getSession().flush();
    	  getSession().clear();   
    	  
    	}
    	else {
    		ob.setAdd1("AlreadyExists");
    		ord.add(ob);
    	}
    	
    	}catch(Exception e)
    	{    		
    		e.printStackTrace();
    	}
    	    	
    	log4jLogger.info("End ====== findOrdJNLMasSave ========");
    	
      return ord;    	
    }
    
    public boolean JNLOrdMasCheck(JnlorderBean newbean)
    {
    	
    	boolean check=false;
    	
    	if (newbean.getOrdNo() != null)
		{
    		Criteria crit = getSession().createCriteria(JnlorderBean.class);
			crit.add(Restrictions.eq("ordNo", newbean.getOrdNo()));
			List list = crit.list();
			if (list == null || list.size() == 0)
			{
				check = true;
			}    		
		}
    	
    	return check;
    }
    
    
    
	
    public List findOrdJNLDetailsSave(List details) 
    {
    	log4jLogger.info("Inside ====== findOrdJNLDetailsSave ========");   	
    	
    	List<Object> user = null;
		for (int i = 0; i < details.size(); i++)
		{
			user = new ArrayList<Object>();
			JnlorderDetailsBean detailsbean=(JnlorderDetailsBean) details.get(i);
			
					getSession().save(detailsbean);
					getSession().flush();
					getSession().clear();
				
	    }
		
		log4jLogger.info("End ====== findOrdJNLDetailsSave ========");
		
	  return user;
	}
    
    public List findOrdJNLFullview(String ordno)
    {   	
    	log4jLogger.info("start===========findOrdJNLFullview====="+ordno);
    	String query = getSession().getNamedQuery("OrderDetailFullView").getQueryString();
		SQLQuery sql = getSession().createSQLQuery(query);
		sql.setParameter("ordno", ordno);
				
		List list = sql.list();
		List<Object> finalResults = null;
		if (list != null)
		{			
			finalResults = new ArrayList<Object>();
			for (int i = 0; i < list.size(); i++)
			{				
				Object[] obj = (Object[]) list.get(i);
				JnlorderDetailsBean ordDetails = new JnlorderDetailsBean();
				ordDetails.setOrdNo(obj[0].toString());
				ordDetails.setOrdDate(Security.getdate(obj[1].toString()));
				ordDetails.setQuoteNo(obj[2].toString());
				ordDetails.setQuoteDate(Security.getdate(obj[3].toString()));
				ordDetails.setSupCode(Integer.parseInt(obj[4].toString()));
				ordDetails.setSupplier(obj[5].toString());
				ordDetails.setJnlCode(Integer.parseInt(obj[6].toString()));
				ordDetails.setJournal(obj[7].toString());
				ordDetails.setSubsfrmdate(Security.getdate(obj[8].toString()));
				ordDetails.setSubstodate(Security.getdate(obj[9].toString()));
				ordDetails.setVolumeNo(obj[10].toString());
				ordDetails.setNoofissue(Integer.parseInt(obj[11].toString()));
				ordDetails.setBcurrency(obj[12].toString());
				ordDetails.setBcost(Double.parseDouble(obj[13].toString()));
				ordDetails.setBprice(Double.parseDouble(obj[14].toString()));
				ordDetails.setDiscount(Double.parseDouble(obj[15].toString()));
				ordDetails.setNetamount(Double.parseDouble(obj[16].toString()));
				ordDetails.setRemarks(obj[17].toString());
				ordDetails.setAdd1(obj[18].toString());
				ordDetails.setAdd2(obj[19].toString());		
				ordDetails.setFrequency(obj[20].toString());	
				ordDetails.setFlag(obj[21].toString());
				
				finalResults.add(ordDetails);				
			}
		}
		log4jLogger.info("End===========findOrdJNLFullview=====");
		return finalResults;
    }
		
    
    public void findOrdJNLDelete(String ordno)  {
	
    	log4jLogger.info("Start===========findOrdJNLDelete=====");
    	
    	String query = getSession().getNamedQuery("DeleteJNLOrderMas").getQueryString();
		SQLQuery sql = getSession().createSQLQuery(query);
		sql.setParameter("ordno", ordno);
		sql.executeUpdate();    	
    	
		log4jLogger.info("===========OrderMaster Deleted SuccessFully=====");
		
		String query1 = getSession().getNamedQuery("DeleteJNLOrderDetail").getQueryString();
		SQLQuery sql1 = getSession().createSQLQuery(query1);
		sql1.setParameter("ordno", ordno);
		sql1.executeUpdate(); 
		
		log4jLogger.info("===========OrderDetailsMaster Deleted SuccessFully=====");
		
    }
    
    public List findSupInvCheck(String ordno)   {
    	log4jLogger.info("Start===========findSupInvCheck=====");
    	
    	String query = getSession().getNamedQuery("CheckOrdJNLSupInvMas").getQueryString();
		SQLQuery sql = getSession().createSQLQuery(query);
		sql.setParameter("ordno", ordno);
		List list = sql.list(); 
    	
		if(list.size()>0)  {
		log4jLogger.info("End===========findSupInvCheck====="+list.get(0));
        }
		
    	return list;
    }
    
    public List findEnqJNLFullviewForOrder(JnlorderBean newbean)
    {   	
    	log4jLogger.info("start===========findEnqJNLFullviewForOrder====="+newbean.getQuoteNo());
    	String query = getSession().getNamedQuery("EnquiryDetailFullView").getQueryString();
		SQLQuery sql = getSession().createSQLQuery(query);
		sql.setParameter("enqno", newbean.getQuoteNo());
				
		List list = sql.list();
		List<Object> finalResults = null;
		if (list != null)
		{			
			finalResults = new ArrayList<Object>();
			for (int i = 0; i < list.size(); i++)
			{				
				Object[] obj = (Object[]) list.get(i);
				JnlorderDetailsBean ordDetails = new JnlorderDetailsBean();
				
				ordDetails.setQuoteNo(obj[0].toString());
				ordDetails.setQuoteDate(Security.getdate(obj[1].toString()));
				ordDetails.setSupCode(Integer.parseInt(obj[2].toString()));
				ordDetails.setSupplier(obj[3].toString());
				ordDetails.setJnlCode(Integer.parseInt(obj[4].toString()));
				ordDetails.setJournal(obj[5].toString());
				ordDetails.setSubsfrmdate(Security.getdate(obj[6].toString()));
				ordDetails.setSubstodate(Security.getdate(obj[7].toString()));
				ordDetails.setVolumeNo(obj[8].toString());
				ordDetails.setNoofissue(Integer.parseInt(obj[9].toString()));
				ordDetails.setBcurrency(obj[10].toString());
				ordDetails.setBcost(Double.parseDouble(obj[11].toString()));
				ordDetails.setBprice(Double.parseDouble(obj[12].toString()));
				ordDetails.setDiscount(Double.parseDouble(obj[13].toString()));
				ordDetails.setNetamount(Double.parseDouble(obj[14].toString()));
				ordDetails.setRemarks(obj[15].toString());
				ordDetails.setAdd1(obj[16].toString());
				ordDetails.setAdd2(obj[17].toString());		
				ordDetails.setFrequency(obj[18].toString());	
				
				ordDetails.setOrdNo(newbean.getOrdNo());
				ordDetails.setOrdDate(newbean.getOrdate());
				
				finalResults.add(ordDetails);				
			}
		}
		log4jLogger.info("End===========findEnqJNLFullviewForOrder=====");
		return finalResults;
    }
    
    	

//  Journal Invoice Processing
    
	public JnlInvoiceBean findInvoiceMax()  {
		log4jLogger.info("start===========findInvoiceMax=====");
		JnlInvoiceBean newbean = new JnlInvoiceBean();
		
       try {
			
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.JNL_INV_MAX);
			rs = Prest.executeQuery();
			if (rs.next()) {			
				
				newbean.setSno(Integer.valueOf(rs.getInt("maxno") + 1));				
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

		/*
		
		
		JnlInvoiceBean newbean = new JnlInvoiceBean();
		try {			 
			String sql = getSession().getNamedQuery("MaxInvoiceRefNo").toString();
			SQLQuery query= getSession().createSQLQuery(sql);
			List list = query.list();
			
			log4jLogger.info("start===========Size====="+list.size());
			List<Object> finalResults = null;
			if (list != null)
			{			
				finalResults = new ArrayList<Object>();
				if(list.size()>0)
				{				
					Object obj = (Object) list.get(0);
					int rk=Integer.parseInt(obj.toString());
					JnlInvoiceBean invDetails = new JnlInvoiceBean();
					invDetails.setSno((rk)+1);				
					finalResults.add(invDetails);
					log4jLogger.info("start========0000000===="+invDetails.getSno());
				}
			}
			
		} catch (Exception e) {
               e.printStackTrace();
		}		
*/
		return newbean;
		
	}	 
	 
	
	public List findSupInvNoSearch(JnlInvoiceBean newbean) {
		
		log4jLogger.info("start===========findSupInvNoSearch====="+newbean.getInvoiceno()+" And "+newbean.getSupplier());
		ArrayList<Object> finalResults = new ArrayList<Object>();
		SQLQuery sql = null;		
		
		String invno="",sup_name="",query="";
		     if(newbean.getInvoiceno()=="")  {
		    	 invno="EMPTYSTRING";
		     }else {
		    	 invno=newbean.getInvoiceno();
		     }
		     if(newbean.getSupplier()=="")  {
		    	 sup_name="EMPTYSTRING";
		     }else {
		    	 sup_name=newbean.getSupplier();
		     }
		     
				
		     if(newbean.getAdd2().equals("SInvoiceNo"))  {
		    	 
		    	  query = getSession().getNamedQuery("SummaryInvNoSearch").getQueryString();
		     }else {
		    	 
			      query = getSession().getNamedQuery("SupInvRefNo").getQueryString();
		     }
		     
		     
			sql=getSession().createSQLQuery(query);
			
			//sql.addScalar("totamount", Hibernate.STRING);           // addScalar for Aliases Names
			sql.setParameter("invno", invno+"%");
			sql.setParameter("supplier", sup_name+"%");
		
	List results = sql.list();
    
	finalResults = null;
	if(results != null)
	{
		finalResults = new ArrayList<Object>();
		for(int i = 0; i < results.size(); i++)
		{
			Object[] obj = (Object[])results.get(i);
			JnlInvoiceBean invSearch = new JnlInvoiceBean();
			
			invSearch.setInvoiceno(obj[0].toString());
			invSearch.setSupplier(obj[1].toString());
			invSearch.setSupCode(Integer.valueOf(obj[2].toString()));
			invSearch.setInvoicedate(Security.getdate(obj[3].toString()));
			//invSearch.setNetamount(Double.parseDouble(obj[4].toString()));
							
			finalResults.add(invSearch);
		}
	}
	   return finalResults;
   }
	
   
	
/*    public List findSupInvDetailsLoad(JnlInvoiceBean newbean)  {
    	
    	log4jLogger.info("start===========findSupInvDetailsLoad====="+newbean.getInvoiceno());
    	String query = getSession().getNamedQuery("SupInvDetailLoad").getQueryString();
		SQLQuery sql = getSession().createSQLQuery(query);
		sql.setParameter("invno", newbean.getInvoiceno());
				
		List list = sql.list();
		List<Object> finalResults = null;
		if (list != null)
		{			
			finalResults = new ArrayList<Object>();
			for (int i = 0; i < list.size(); i++)
			{				
				Object[] obj = (Object[]) list.get(i);
				JnlInvoiceBean invDetails = new JnlInvoiceBean();
				
				
				invDetails.setSno(Integer.parseInt(obj[0].toString()));
				invDetails.setReceiveddate(Security.getdate(obj[1].toString()));
				invDetails.setOrdNo(obj[2].toString());
				invDetails.setSupCode(Integer.parseInt(obj[3].toString()));
				invDetails.setSupplier(obj[4].toString());
				invDetails.setInvoiceno(obj[5].toString());
				invDetails.setInvoicedate(Security.getdate(obj[6].toString()));
				invDetails.setNetamount(Double.parseDouble(obj[7].toString()));	
				invDetails.setPaymentflag(obj[8].toString());
				invDetails.setPaymentno(obj[9].toString());
				invDetails.setRemarks(obj[10].toString());
				invDetails.setAdd1(obj[11].toString());
				invDetails.setAdd2(obj[12].toString());				
				
				finalResults.add(invDetails);				
			}
		}
		log4jLogger.info("End===========findSupInvDetailsLoad=====");
		return finalResults;
    	
		
	}  */
	
	
	
	
   public List findInvJNLEntrySave(JnlInvoiceBean newbean)  {
		 

	    	log4jLogger.info("Inside ====== findInvJNLEntrySave ========");
	    	JnlInvoiceBean ob=new JnlInvoiceBean();
	    	List<Object> inv=new ArrayList<Object>();
	    	try {
	    		
	    	if(JNLInvMasCheck(newbean))
	    	{
	    	  getSession().save(newbean);
	    	  getSession().flush();
	    	  getSession().clear();   
	    	  
	    	}
	    	else {
	    		ob.setAdd1("AlreadyExists");
	    		inv.add(ob);
	    	}
	    	
	    	}catch(Exception e)
	    	{    		
	    		e.printStackTrace();
	    	}
	    	    	
	    	log4jLogger.info("End ====== findInvJNLEntrySave ========");
	    	
	      return inv;    	
		 
	 }
	 
	 public boolean JNLInvMasCheck(JnlInvoiceBean newbean)
	 {
	    	
	    	boolean check=false;
	    	
	    	if (newbean.getInvoiceno() != null)
			{
	    		Criteria crit = getSession().createCriteria(JnlInvoiceBean.class);
				crit.add(Restrictions.eq("Invoiceno", newbean.getInvoiceno()));
				List list = crit.list();
				if (list == null || list.size() == 0)
				{
					check = true;
				}    		
			}
	    	
	    	return check;
	 }
	 
	 
	 public List findInvJNLSearch(String invno)
	 {   	
	    	log4jLogger.info("start===========findInvJNLSearch====="+invno);
	    	String query = getSession().getNamedQuery("InvFullview").getQueryString();
			SQLQuery sql = getSession().createSQLQuery(query);
			sql.setParameter("invno", invno);
					
			List list = sql.list();
			List<Object> finalResults = null;
			if (list != null)
			{			
				finalResults = new ArrayList<Object>();
				for (int i = 0; i < list.size(); i++)
				{				
					Object[] obj = (Object[]) list.get(i);
					JnlInvoiceBean invDetails = new JnlInvoiceBean();
					
					
					invDetails.setSno(Integer.parseInt(obj[0].toString()));
					invDetails.setReceiveddate(Security.getdate(obj[1].toString()));
					//invDetails.setOrdNo(obj[2].toString());
					invDetails.setSupCode(Integer.parseInt(obj[3].toString()));
					invDetails.setSupplier(obj[4].toString());
					invDetails.setInvoiceno(obj[5].toString());
					invDetails.setInvoicedate(Security.getdate(obj[6].toString()));
					invDetails.setNetamount(Double.parseDouble(obj[7].toString()));	
					invDetails.setPaymentflag(obj[8].toString());
					invDetails.setPaymentno(obj[9].toString());
					invDetails.setRemarks(obj[10].toString());
					invDetails.setAdd1(obj[11].toString());
					invDetails.setAdd2(obj[12].toString());				
					
					finalResults.add(invDetails);				
				}
			}
			log4jLogger.info("End===========findInvJNLSearch=====");
			return finalResults;
	 }
	 
	 public void findInvJNLDelete(String invno) {
		    log4jLogger.info("Start===========findInvJNLDelete=====");
	    	
	    	String query = getSession().getNamedQuery("DeleteJNLInvoiceMas").getQueryString();
			SQLQuery sql = getSession().createSQLQuery(query);
			sql.setParameter("invno", invno);
			sql.executeUpdate();    	
	    	
			log4jLogger.info("===========findInvJNLDelete SuccessFully=====");
	 }
	 
	 
//	  Journal Payment Processing
	    
		public JnlPaymentBean findPaymentMax()  {
			log4jLogger.info("start===========findPaymentMax=====");
			JnlPaymentBean newbean = new JnlPaymentBean();
			
	       try {
				
				con = getSession().connection();
				Prest = con.prepareStatement(DataQuery.JNL_Payment_MAX);
				rs = Prest.executeQuery();
				if (rs.next()) {			
					
					newbean.setPaymentno(Integer.valueOf(rs.getInt("maxno") + 1));				
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
		
		
		public List findPaymentInvSearch(JnlPaymentBean newbean)  
		{   	
		    	log4jLogger.info("start===========findPaymentInvSearch====="+newbean.getInvNo());
		    	String query ="";
		    	
		    	if(newbean.getAdd2().equals("PaymentNo"))
		    	{
		    		query = getSession().getNamedQuery("PaymentNoSearch").getQueryString();		    		
		    	}else {
		    		
		    		query = getSession().getNamedQuery("PaymentInvRefNo").getQueryString();
		    	}
		    	
		    	
				SQLQuery sql = getSession().createSQLQuery(query);
				sql.setParameter("invno", newbean.getInvNo()+"%");
						
				List list = sql.list();
				List<Object> finalResults = null;
				if (list != null)
				{			
					finalResults = new ArrayList<Object>();
					for (int i = 0; i < list.size(); i++)
					{				
						Object[] obj = (Object[]) list.get(i);
						JnlPaymentBean pmtDetails = new JnlPaymentBean();
						
						pmtDetails.setInvNo(obj[0].toString());
						pmtDetails.setSupplier(obj[1].toString());				
						pmtDetails.setNetamount(Double.parseDouble(obj[2].toString()));	
						finalResults.add(pmtDetails);				
					}
				}
				log4jLogger.info("End===========findPaymentInvSearch=====");
				return finalResults;
		 }
	 
	 
		public List findPaymentSelectedInvoice(JnlPaymentBean newbean)   
		{
			
			log4jLogger.info("start===========findPaymentSelectedInvoice====="+newbean.getAdd1());

		     SQLQuery sql = null;					
			 sql = getSession().createSQLQuery(DataQuery.JNL_Invoice_CheckList+" and invoice_no in(null"+newbean.getAdd1()+")");			 
			 List list = sql.list();
			 
				List<Object> finalResults = null;
				if (list != null)
				{			
					finalResults = new ArrayList<Object>();
					for (int i = 0; i < list.size(); i++)
					{				
						Object[] obj = (Object[]) list.get(i);
						JnlPaymentBean PaymentDetails = new JnlPaymentBean();
						
						PaymentDetails.setInvNo(obj[0].toString());
						PaymentDetails.setSupplier(obj[1].toString());
						PaymentDetails.setSupCode(Integer.parseInt(obj[2].toString()));
						PaymentDetails.setInvoiceamount(Double.parseDouble(obj[3].toString()));	
						
						PaymentDetails.setPaymentno(newbean.getPaymentno());
						PaymentDetails.setPaymentsenddate(newbean.getPaymentsenddate());
						PaymentDetails.setTransdetails(newbean.getTransdetails());
						PaymentDetails.setCheckno(newbean.getCheckno());
						PaymentDetails.setChequedate(newbean.getChequedate());
						PaymentDetails.setNetamount(newbean.getNetamount());						
						PaymentDetails.setRemarks(newbean.getRemarks());						
						
						finalResults.add(PaymentDetails);				
					}
				}
				log4jLogger.info("End===========findPaymentSelectedInvoice=====");
				return finalResults;
			
			
		}
	 
		public void findPaymentJNLDetailsUpdate(List details)  
		{
			log4jLogger.info("Inside ====== findPaymentJNLDetailsUpdate ========");   
						
			try {

				String query = getSession().getNamedQuery("PaymentInvoiceUpdate").getQueryString();
				SQLQuery sql = getSession().createSQLQuery(query);	
	    	
			for (int i = 0; i < details.size(); i++)
			{
				//List<Object> user = null;
				//user = new ArrayList<Object>();
				JnlPaymentBean pmtbean=(JnlPaymentBean) details.get(i);
				
				sql.setParameter("pmtno", pmtbean.getPaymentno());
				sql.setParameter("invno",pmtbean.getInvNo());	
				sql.executeUpdate();
				
				//log4jLogger.info("=== Inside Loop ==="+pmtbean.getInvNo()+" And "+pmtbean.getPaymentno());
		    }
			
			}catch(Exception e)  {
				e.printStackTrace();
			}
			
			log4jLogger.info("End ====== findPaymentJNLDetailsUpdate ========");
						
					
		}
		
		public List findPaymentJNLMasSave(JnlPaymentBean newbean)
		{			
			log4jLogger.info("Inside ====== findPaymentJNLMasSave ========");
			
	    	List<String> pmt=new ArrayList<String>();
	    	try {
	    		
	    	if(JNLPaymentMasCheck(newbean))
	    	{
	    	 if(JNLInvoiceMasCheck(newbean))
		     {
	    		
	    	  getSession().save(newbean);
	    	  getSession().flush();
	    	  getSession().clear();   
	    	  
		     }else  {

		    		pmt.add("InvoiceAlreadyExists");
		    		pmt.add("AlreadyPaid");
		     }
	    	}
	    	else {

	    		pmt.add("AlreadyExists");
	    	}
	    	
	    	}catch(Exception e)
	    	{    		
	    		e.printStackTrace();
	    	}
	    	    	
	    	log4jLogger.info("End ====== findPaymentJNLMasSave ========");
	    	
	      return pmt;    	
			
			
		}
		
		 public boolean JNLPaymentMasCheck(JnlPaymentBean newbean)
		 {
		    	
		    	boolean check=false;
		    	
		    	if (newbean.getPaymentno() != 0)
				{
		    		Criteria crit = getSession().createCriteria(JnlPaymentBean.class);
					crit.add(Restrictions.eq("paymentno", newbean.getPaymentno()));
					List list = crit.list();
					if (list == null || list.size() == 0)
					{
						check = true;
					}    		
				}
		    	
		    	return check;
		 }
		 
		 public boolean JNLInvoiceMasCheck(JnlPaymentBean newbean)
		 {
		    	
		    	boolean check=false;
		    	
		    	if (newbean.getInvNo() != null)
				{
		    		SQLQuery sql = getSession().createSQLQuery(DataQuery.JNL_CheckPaidInvoice+" and invoice_no IN(null"+newbean.getInvNo()+")");					
		    		
		    		List list = sql.list();
					if (list == null || list.size() == 0)
					{
						check = true;
					}
				}
		    	
		    	return check;
		 }
		 
		 
		 
		 public List findPaymentJNLDetailsSearch(String pmtno)
		 {
			 log4jLogger.info("start===========findPaymentJNLDetailsSearch====="+pmtno);
			 
		    	String query = getSession().getNamedQuery("PaymentFullView").getQueryString();
				SQLQuery sql = getSession().createSQLQuery(query);
				sql.setParameter("pmtno", pmtno);
						
				List list = sql.list();
				List<Object> finalResults = null;
				if (list != null)
				{			
					finalResults = new ArrayList<Object>();
					for (int i = 0; i < list.size(); i++)
					{				
						Object[] obj = (Object[]) list.get(i);
						JnlPaymentBean  pmtDetails = new JnlPaymentBean();
						
						pmtDetails.setPaymentno(Integer.parseInt(obj[0].toString()));
						pmtDetails.setPaymentsenddate(Security.getdate(obj[1].toString()));
						pmtDetails.setCheckno(obj[2].toString());
						pmtDetails.setChequedate(Security.getdate(obj[3].toString()));
						pmtDetails.setNetamount(Double.parseDouble(obj[4].toString()));						
						pmtDetails.setTransdetails(obj[5].toString());			
						
						pmtDetails.setRemarks(obj[6].toString());
						pmtDetails.setAdd1(obj[7].toString());
						pmtDetails.setAdd2(obj[8].toString());	
						pmtDetails.setInvNo(obj[9].toString());
						pmtDetails.setInvoiceamount(Double.parseDouble(obj[10].toString()));
						
						finalResults.add(pmtDetails);				
					}
				}
				log4jLogger.info("End===========findPaymentJNLDetailsSearch=====");
				return finalResults;			 
		 }
		 
		 public void findPaymentJNLDelete(String pmtno)  {
			    log4jLogger.info("Start===========findPaymentJNLDelete====="+pmtno);
		    	
			    try {
			    	SQLQuery sql1;
			    	String rk = getSession().getNamedQuery("PaymentInvoiceDeleteUpdate").getQueryString();
			    	
		    		con=getSession().connection();
		    		Prest=con.prepareStatement(DataQuery.JNL_Invoice_DelUpdate);
		    		Prest.setString(1,pmtno);
		    		rs=Prest.executeQuery();
		    		
		    		while(rs.next())
		    		{		    			
		    			sql1 = getSession().createSQLQuery(rk);
		    			sql1.setParameter("invno", rs.getString(1));
		    			sql1.executeUpdate();		    			
		    		}
		    		
		    		}catch(Exception e){
		    			e.printStackTrace();
		    		} 
			    
		    	String query = getSession().getNamedQuery("DeleteJNLPaymentMas").getQueryString();
				SQLQuery sql = getSession().createSQLQuery(query);
				sql.setParameter("pmtno", pmtno);
				sql.executeUpdate();    	
		    	
				log4jLogger.info("===========findPaymentJNLDelete SuccessFully=====");
			 
		 }

		 
		 
		 
// Journal Supplier Invoice Processing		 
		 
		 public List findSupInvOrdJNLCheckList(JnlSupInvBean newbean)
		 {   	
			    
			    log4jLogger.info("start===========findSupInvOrdJNLCheckList====="+newbean.getAdd1());
				
			    SQLQuery sql = getSession().createSQLQuery(DataQuery.JNL_SupInv_CheckList+" and Jnl_Order_No in(null"+newbean.getAdd1()+") and flag='process' order by Jnl_Order_No");
						
				List list = sql.list();
				List<Object> finalResults = null;
				if (list != null)
				{			
					finalResults = new ArrayList<Object>();
					for (int i = 0; i < list.size(); i++)
					{				
						Object[] obj = (Object[]) list.get(i);
						JnlSupInvDetailsBean ordDetails = new JnlSupInvDetailsBean();
						ordDetails.setQuoteNo(newbean.getQuoteNo());
						ordDetails.setQuoteDate(newbean.getQuotedate());
						ordDetails.setOrdNo(obj[0].toString());
						ordDetails.setOrdDate(Security.getdate(obj[1].toString()));
						ordDetails.setSupCode(Integer.parseInt(obj[2].toString()));
						ordDetails.setSupplier(obj[3].toString());
						ordDetails.setJnlCode(Integer.parseInt(obj[4].toString()));
						ordDetails.setJournal(obj[5].toString());
						ordDetails.setSubsfrmdate(Security.getdate(obj[6].toString()));
						ordDetails.setSubstodate(Security.getdate(obj[7].toString()));
						ordDetails.setVolumeNo(obj[8].toString());
						ordDetails.setNoofissue(Integer.parseInt(obj[9].toString()));
						ordDetails.setBcurrency(obj[10].toString());
						ordDetails.setBcost(Double.parseDouble(obj[11].toString()));
						ordDetails.setBprice(Double.parseDouble(obj[12].toString()));
						ordDetails.setDiscount(Double.parseDouble(obj[13].toString()));
						ordDetails.setNetamount(Double.parseDouble(obj[14].toString()));							
						ordDetails.setFrequency(obj[15].toString());	
						
						finalResults.add(ordDetails);				
					}
				}
				log4jLogger.info("End===========findSupInvOrdJNLFullview=====");
				return finalResults;
		    }	 
		 
		    public List findInvSearchOrdNoSup(JnlSupInvBean newbean) {
				
				log4jLogger.info("start===========findInvSearchOrdNoSup====="+newbean.getOrdNo()+" And "+newbean.getAdd1() +"And Flag="+newbean.getAdd2());
				List<Object> finalResults = new ArrayList<Object>();
				SQLQuery sql = null;		
				
				String ordno="",sup_name="",query="";
				
				     if(newbean.getOrdNo()=="")  {
				    	 ordno="EMPTYSTRING";
				     }else {
				    	 ordno=newbean.getOrdNo();
				     }
				     if(newbean.getAdd1()=="")  {
				    	 sup_name="EMPTYSTRING";
				     }else {
				    	 sup_name=newbean.getAdd1();
				     }
				     
				    
				     if(newbean.getAdd2().equals("SupInvoice"))  {
				    	 
				    	   query = getSession().getNamedQuery("InvNoSearch").getQueryString();
				    	   
				     }
				     else if(newbean.getAdd2().equals("EnquiryReport"))  {
				    	 
				    	   query = getSession().getNamedQuery("EnquiryNoReport").getQueryString();				    	   
				     }
				     else {
				    	 
				    	   query = getSession().getNamedQuery("InvOrderRefNo").getQueryString();
				     }     
				     
					
					sql=getSession().createSQLQuery(query);
					sql.setParameter("ordno", ordno+"%");
					sql.setParameter("supplier", sup_name+"%");
				
			    List results = sql.list();
			   
			   
		    
			finalResults = null;
			if(results != null)
			{
				finalResults = new ArrayList<Object>();
				for(int i = 0; i < results.size(); i++)
				{
					Object[] obj = (Object[])results.get(i);
					JnlSupInvBean invSearch = new JnlSupInvBean();
					
					invSearch.setOrdNo(obj[0].toString());
					invSearch.setAdd1(obj[1].toString());
					invSearch.setSupCode(Integer.valueOf(obj[2].toString()));
									
					finalResults.add(invSearch);
				}
			}
			   return finalResults;
		   }
		 

		    public List findSupInvOrdJNLMasSave(JnlSupInvBean newbean)
		    {
		    	log4jLogger.info("Inside ====== findSupInvOrdJNLMasSave ========");
		    	JnlSupInvBean ob=new JnlSupInvBean();
		    	List<Object> ord=new ArrayList<Object>();
		    	try {
		    		
		    	if(JNLSupInvOrdMasCheck(newbean))
		    	{
		    	  getSession().save(newbean);
		    	  getSession().flush();
		    	  getSession().clear();   
		    	  
		    	}
		    	else {
		    		ob.setAdd1("AlreadyExists");
		    		ord.add(ob);
		    	}
		    	
		    	}catch(Exception e)
		    	{    		
		    		e.printStackTrace();
		    	}
		    	    	
		    	log4jLogger.info("End ====== findSupInvOrdJNLMasSave ========");
		    	
		      return ord;    	
		    }
		    
		    public boolean JNLSupInvOrdMasCheck(JnlSupInvBean newbean)
		    {
		    	
		    	boolean check=false;
		    	
		    	if (newbean.getQuoteNo() != null)
				{
		    		Criteria crit = getSession().createCriteria(JnlSupInvBean.class);
					crit.add(Restrictions.eq("QuoteNo", newbean.getQuoteNo()));
					List list = crit.list();
					if (list == null || list.size() == 0)
					{
						check = true;
					}    		
				}
		    	
		    	return check;
		    }		    
		 	 
		 
		 public List findSupInvOrdJNLDetailsSave(List details) 
		 {
		    	log4jLogger.info("Inside ====== findSupInvOrdJNLDetailsSave ========");   	
		    	
		    	List<Object> user = null;
				for (int i = 0; i < details.size(); i++)
				{
					user = new ArrayList<Object>();
					JnlSupInvDetailsBean detailsbean=(JnlSupInvDetailsBean) details.get(i);
					
							getSession().save(detailsbean);
							getSession().flush();
							getSession().clear();
						
			    }
				
				log4jLogger.info("End ====== findSupInvOrdJNLDetailsSave ========");
				
			  return user;
	   }
		 
		 public void findSupInvOrdJNLUpdate(String jnlscode,String status)
		 {   	
			    
			    log4jLogger.info("start===========findSupInvOrdJNLUpdate====="+jnlscode+" Status:"+status);
			    
			    SQLQuery sql =null;
		    	if(status.equals("PROCESS"))
		    	{
		    		 sql = getSession().createSQLQuery(DataQuery.JNL_SupInv_UpdatePendingJNL+" and Jnl_Code in(null"+jnlscode+")");
		    		 sql.executeUpdate();
		    		
		    	}else if(status.equals("SAVEUPDATE")){
		    		
		    		try {
		    		con=getSession().connection();
		    		Prest=con.prepareStatement(DataQuery.JNL_SupInv_SaveUpdateChk);
		    		Prest.setString(1,jnlscode);
		    		rs=Prest.executeQuery();
		    		
		    		while(rs.next())
		    		{
		    			sql = getSession().createSQLQuery(DataQuery.JNL_SupInv_UpdatePendingJNL+" and Jnl_Code ="+rs.getInt(1));
		    			sql.executeUpdate();		    			
		    		}
		    		
		    		}catch(Exception e){
		    			e.printStackTrace();
		    		}   		
		    		 
		    	}else{
		    		 sql = getSession().createSQLQuery(DataQuery.JNL_SupInv_Updatejnl+" and Jnl_Code in(null"+jnlscode+")");
		    		 sql.executeUpdate();
		    	}
                
		 }
		 
		 
		 public List findSupInvOrdJNLFullview(String invno)
		    {   	
		    	log4jLogger.info("start===========findSupInvOrdJNLFullview====="+invno);
		    	String query = getSession().getNamedQuery("SupInvDetailFullView").getQueryString();
				SQLQuery sql = getSession().createSQLQuery(query);
				sql.setParameter("invno", invno);
						
				List list = sql.list();
				List<Object> finalResults = null;
				if (list != null)
				{			
					finalResults = new ArrayList<Object>();
					for (int i = 0; i < list.size(); i++)
					{				
						Object[] obj = (Object[]) list.get(i);
						JnlSupInvDetailsBean invDetails = new JnlSupInvDetailsBean();
						invDetails.setOrdNo(obj[0].toString());
						invDetails.setOrdDate(Security.getdate(obj[1].toString()));
						invDetails.setQuoteNo(obj[2].toString());
						invDetails.setQuoteDate(Security.getdate(obj[3].toString()));
						invDetails.setSupCode(Integer.parseInt(obj[4].toString()));
						invDetails.setSupplier(obj[5].toString());
						invDetails.setJnlCode(Integer.parseInt(obj[6].toString()));
						invDetails.setJournal(obj[7].toString());
						invDetails.setSubsfrmdate(Security.getdate(obj[8].toString()));
						invDetails.setSubstodate(Security.getdate(obj[9].toString()));
						invDetails.setVolumeNo(obj[10].toString());
						invDetails.setNoofissue(Integer.parseInt(obj[11].toString()));
						invDetails.setBcurrency(obj[12].toString());
						invDetails.setBcost(Double.parseDouble(obj[13].toString()));
						invDetails.setBprice(Double.parseDouble(obj[14].toString()));
						invDetails.setDiscount(Double.parseDouble(obj[15].toString()));
						invDetails.setNetamount(Double.parseDouble(obj[16].toString()));
						invDetails.setRemarks(obj[17].toString());
						invDetails.setAdd1(obj[18].toString());
						invDetails.setAdd2(obj[19].toString());		
						invDetails.setFrequency(obj[20].toString());	
						
						finalResults.add(invDetails);				
					}
				}
				log4jLogger.info("End===========findSupInvOrdJNLFullview=====");
				return finalResults;
		    }
			
		 public void findSupInvOrdJNLDelete(String invno)  {
				
		    	log4jLogger.info("Start===========findSupInvOrdJNLDelete=====");
		    	
		    	String query = getSession().getNamedQuery("DeleteJNLSupInvMas").getQueryString();
				SQLQuery sql = getSession().createSQLQuery(query);
				sql.setParameter("invno", invno);
				sql.executeUpdate();    	
		    	
				log4jLogger.info("========== Supplier Invoice Master Deleted SuccessFully=====");
				
				String query1 = getSession().getNamedQuery("DeleteJNLSupDetail").getQueryString();
				SQLQuery sql1 = getSession().createSQLQuery(query1);
				sql1.setParameter("invno", invno);
				sql1.executeUpdate(); 
				
				log4jLogger.info("=========== Supplier Invoice Details Master Deleted SuccessFully=====");
				
		    }
	
 public JournalSubscriptionbean findJnlSubscriptionMax(int jnlcode){
			 
			 JournalSubscriptionbean newbean=null;
			 
			 newbean = new JournalSubscriptionbean();
			
			 log4jLogger.info("Inside accessno checking weather is Numeric or Alpha Numeric");
				String New_no="";
				
				try {		
					 
				con = getSession().connection();
				Prest=con.prepareStatement("SELECT MAX(CONVERT(Issue_Access_No,SIGNED INTEGER)) AS accno FROM JOURNAL_ISSUES WHERE Issue_Access_No NOT REGEXP '^[0-9]'");
				rs=Prest.executeQuery();
				
				if(rs.next()) {
					
				if(rs.getString("accno")==null) {
					
					
					int flen=0,slen=0;
					String rk="",rk1="";
					
					con = getSession().connection();
					
					Prest=con.prepareStatement("SELECT MAX(CONVERT(Issue_Access_No,SIGNED INTEGER)+1) AS accno, MAX(Issue_Access_No) AS oldaccno FROM JOURNAL_ISSUES WHERE Issue_Access_No REGEXP '^[0-9]'");
					rs1=Prest.executeQuery();
					
					if(rs1.next()) {				  
						rk=rs1.getString("accno");
						rk1=rs1.getString("oldaccno");
						if(rk!=null && rk1!=null)
						{
						   flen = rk.length();
						   slen = rk1.length();
						}else {
							rk="";
						}
					}
					
					if(slen > flen)    // For Add Zero before. Ex.00001+1 = 00002
					{
						rs1=null;
						con = getSession().connection();				
						Prest=con.prepareStatement("SELECT LPAD("+rk+","+slen+",0) AS accno");
						rs1=Prest.executeQuery();
							
						if(rs1.next()) {				  
							rk=rs1.getString("accno");					
						}					
					}
					
					New_no=rk;	
					log4jLogger.info("Value fddd"+rk);
					
					if(rs1.getString("accno")!=null && !rs1.getString("accno").isEmpty())
					{
						newbean.setStartingAccNo(rs1.getString("accno"));	
					}else{
						
							newbean.setStartingAccNo("JL001");
						
					}
					log4jLogger.info("MaximumAccessNo:::"+newbean.getStartingAccNo());
					
					
				}		
				else if(rs.getString("accno").equalsIgnoreCase("0")) {
					
					int i,len,nlength;
					String rk="",viewchar="",achar="";
					con = getSession().connection();
					Prest=con.prepareStatement("SELECT MAX(Issue_Access_No) as accno FROM JOURNAL_ISSUES WHERE Issue_Access_No NOT REGEXP '^[0-9]'");
					rs1=Prest.executeQuery();
					
					if(rs1.next()) {
						  
						rk=rs1.getString("accno");				
						len=rk.length();
						
						for(i=0;i<len;i++)
						{					
							achar = rk.substring(i,i+1);
							
							if(IsNumeric(achar))
							{
								break;						
							}else{
								viewchar = viewchar + achar;
							}					
						}
						
						i = i+1;
						nlength = len - i + 1;					
						i = i-1;

						if(viewchar.isEmpty())
						{
							log4jLogger.info("**************** No Code for New Button in Book Master");
						}
						else{
							rs1=null;
							int lenaccessno,maxaccno;
							String zerochar="";
							
							con = getSession().connection();
							Prest=con.prepareStatement("SELECT MAX(CONVERT(RIGHT(Issue_Access_No, (LENGTH(Issue_Access_No)-"+ i +")),SIGNED INTEGER)) AS access_no FROM JOURNAL_ISSUES WHERE Issue_Access_No Like '"+viewchar+"%'");
							rs1=Prest.executeQuery();
							
							if(rs1.next())
							{
							  lenaccessno = rs1.getInt("access_no") + 1;
							  maxaccno = lenaccessno;					  
							  String lenacc=""+lenaccessno;
							  
							  if(nlength > lenacc.length()) 
							  {						  
								  lenaccessno = nlength - lenacc.length();
								  
								  if(lenaccessno == 1)
								  {
									  zerochar="0";
								  }else if(lenaccessno == 2)
								  {
									  zerochar="00";
								  }else if(lenaccessno == 3)
								  {
									  zerochar="000";
								  }else if(lenaccessno == 4)
								  {
									  zerochar="0000";
								  }else if(lenaccessno == 5)
								  {
									  zerochar="00000";
								  }else if(lenaccessno == 6)
								  {
									  zerochar="000000";
								  }else if(lenaccessno == 7)
								  {
									  zerochar="0000000";
								  }
								  
								  New_no = viewchar + zerochar + maxaccno;					        
							  }else {      
								  New_no = viewchar + maxaccno;  
							  }					  
							}				
						}			
					}
					
					log4jLogger.info("Value for Alpha Numeric :"+New_no);
					
					newbean.setStartingAccNo(New_no);
				}
				else
				{
					log4jLogger.info("No suggestion Available --> New Button in Book Master ");
				}			
				}
				else {
				log4jLogger.info("******************** No Use of this code: --> New Button in Book Master ********************************"+rs);
				}
				
				

				
				
				//int curMonth = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
				
				 String[] Month = { "January", "February", "March", "April", "May", "June", "July",
					        "August", "September", "October", "November", "December" };
				
				 	Calendar cal = Calendar.getInstance();
				  
				 	String curMonth = Month[cal.get(Calendar.MONTH)];
				    int curYear = Calendar.getInstance().get(Calendar.YEAR);
				
				    
				
				
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
				return newbean;
		 }
		 
		 public int findJnlSubscriptionUpdate(JournalSubscriptionbean newbean) {
			 
			 int count=0;
			 try {
				
				 
				 con = getSession().connection();
					Prest = con.prepareStatement(DataQuery.updateJournalSubscription);
					
					Prest.setInt(1,newbean.getJnlcode());
				
					count=Prest.executeUpdate();
					
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
		 
		 public int findJnlSubscriptionSave(JournalSubscriptionbean newbean) {
			
			 
			 log4jLogger.info("start===========findJnlSave=====");
				int count = 0;
				
				try {
				
					getSession().save(newbean);
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
		 public JournalSubscriptionbean findjnlSubsFlag(JournalSubscriptionbean newbean){
			 
			log4jLogger.info("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
			 //shek
			
			log4jLogger.info("**********************************"+newbean.getSubsNo());
			 JournalSubscriptionbean ob=new JournalSubscriptionbean();
			 
			 try {
				 con = getSession().connection();
				 Prest = con.prepareStatement(DataQuery.updateJournalSubsFlag);
				 Prest.setString(1, newbean.getSubsNo());
					Prest.executeUpdate();
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
		 
		 
		 public JournalSubscriptionbean findjnlSubscriptionDelete(JournalSubscriptionbean newbean){
			 
			 JournalSubscriptionbean ob=new JournalSubscriptionbean();
			 
			 
			 try {
				 con = getSession().connection();
				 Prest = con.prepareStatement(DataQuery.deleteJnlIssuesCheck);
				 Prest.setString(1, newbean.getSubsNo());
					rs = Prest.executeQuery();
					if (rs.next()) {
						ob.setFlag("0");
					}else{
						 con = getSession().connection();
						 Prest = con.prepareStatement(DataQuery.deleteJnlIssues);
						 Prest.setString(1, newbean.getSubsNo());
						 Prest.executeUpdate();
						 
						 Prest = con.prepareStatement(DataQuery.deleteJnlSubscription);
						 Prest.setString(1, newbean.getSubsNo());
						 Prest.executeUpdate();
						 
						 
						 ob.setFlag("1");
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
		 
		 
		 public int findjnlSubscriptionDateSearch(JournalSubscriptionbean newbean){
			 log4jLogger.info("start===========findJnlSubscriptionSearchName=====");
			 int result=0;
			 String SubsNo=newbean.getSubsNo();
			 String fromDt=newbean.getSubsFrom();
			 String toDt=newbean.getSubsTo();
			 log4jLogger.info("from date:::::::::"+fromDt);
			 log4jLogger.info("to date:::::::::"+toDt);
			 try {
				 
				 con = getSession().connection();
					Prest = con.prepareStatement(DataQuery.JournalSubscriptionSearch);
					Prest.setString(1, SubsNo);
					rs = Prest.executeQuery();
					if (rs.next()) {
						log4jLogger.info(":::::::::::::journal Subscriptions available");
						
						Prest = con.prepareStatement(DataQuery.JournalIssuesSearch);
						Prest.setString(1, SubsNo);
						Prest.setString(2, fromDt);
						Prest.setString(3, toDt);
						rs = Prest.executeQuery();
						if (rs.next()) {
							result=1;
						}else{
							result=0;
						}
					
					}else{
						result=2;
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
		 
		 
		 
		 public JournalSubscriptionbean findjnlSubscriptionSearch(JournalSubscriptionbean newbean){
			
			 log4jLogger.info("start===========findJnlSubscriptionSearchName=====");
			 
			 
			 String SubsNo=newbean.getSubsNo();
			
			 JournalSubscriptionbean ob=null;
			 
			 
			 try {
				
					con = getSession().connection();
					Prest = con.prepareStatement(DataQuery.JournalSubscriptionSearch);
					Prest.setString(1, SubsNo);
					rs = Prest.executeQuery();
					if (rs.next()) {
						ob=new JournalSubscriptionbean();
						
						
						ob.setJnlcode(rs.getInt("jnl_code"));
						ob.setJnlName(rs.getString("jnl_name"));
						ob.setSubsNo(rs.getString("subs_no"));
						ob.setSupCode(rs.getInt("sup_code"));
						ob.setSupplier(rs.getString("supplier"));
						ob.setSubsFrom(Security.getdate(rs.getString("subs_from")));
						ob.setSubsTo(Security.getdate(rs.getString("subs_to")));
						ob.setFrequency(rs.getString("frequency"));
						ob.setNoOfIssues(rs.getInt("noofissues"));
						ob.setExceptedDays(rs.getInt("Expected_days"));
						ob.setFlag(rs.getString("flag"));
						ob.setIssueYear(rs.getString("issue_year"));
						ob.setIssueMonth(rs.getString("issue_month"));
						ob.setIssueVolume(rs.getString("issue_volume"));
						ob.setIssueNo(rs.getString("issue_no"));
						ob.setjCost(rs.getString("cost"));
						ob.setjCurrency(rs.getString("currency"));
						ob.setjPrice(rs.getString("price"));
						ob.setjDiscount(rs.getString("discount"));
						ob.setNetPrice(rs.getString("netprice"));
						ob.setBudgCode(rs.getInt("budgcode"));
						ob.setBudName(rs.getString("bud_head"));
						
						log4jLogger.info("1   : "+ob.getJnlcode());
						log4jLogger.info("2   : "+ob.getJnlName());
						log4jLogger.info("3   : "+ob.getSubsNo());
						log4jLogger.info("4   : "+ob.getSupCode());
						log4jLogger.info("5   : "+ob.getSupplier());
						log4jLogger.info("6   : "+ob.getSubsFrom());
						log4jLogger.info("7   : "+ob.getSubsTo());
						log4jLogger.info("8   : "+ob.getFrequency());
						log4jLogger.info("9   : "+ob.getNoOfIssues());
						log4jLogger.info("10   : "+ob.getExceptedDays());
						log4jLogger.info("11   : "+ob.getFlag());
						log4jLogger.info("12   : "+ob.getIssueYear());
						log4jLogger.info("13   : "+ob.getIssueMonth());
						log4jLogger.info("14   : "+ob.getIssueVolume());
						log4jLogger.info("15   : "+ob.getIssueNo());
						log4jLogger.info("16   : "+ob.getjCost());
						log4jLogger.info("17   : "+ob.getjCurrency());
						log4jLogger.info("18   : "+ob.getjPrice());
						log4jLogger.info("19   : "+ob.getjDiscount());
						log4jLogger.info("20   : "+ob.getNetPrice());
						log4jLogger.info("21   : "+ob.getBudgCode());
						log4jLogger.info("22   : "+ob.getBudName());
					
						
						
						
						log4jLogger.info("3333333333333333333333333333333333333333");
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

		 public JournalSubscriptionbean findJnlSearchName(JournalSubscriptionbean newbean) {

				log4jLogger.info("start===========findJnlSubscriptionSearchName=====");
				
				String searchFlag=newbean.getFlag();
				String searchValue=newbean.getSearchValue();
				
				
				log4jLogger.info("start===========SearchValue=====  ::::::::"+searchValue);
				log4jLogger.info("start===========SearchFlag=====:::::::::::"+searchFlag);

				ArrayList finalResults = new ArrayList();
				JournalSubscriptionbean ob=new JournalSubscriptionbean();
				ArrayList ser=new ArrayList ();
				String flag,f1,f2,f3;
				
				try {
					
					con = getSession().connection();
					st = con.createStatement();
					
					
					if (searchFlag.equalsIgnoreCase("jnlName"))
					{
						log4jLogger.info("start===========searchJournalSearchName=====");
					
						if (searchValue.isEmpty() || searchValue.equals(""))
						{
						rs = st.executeQuery(DataQuery.JOURNAL_SEARCH_NAME );
						}
						else{
						rs = st.executeQuery(DataQuery.JOURNAL_SEARCH_NAME_LIKE +searchValue+"%'  order by jnl_name");
						
					}
				
					while (rs.next()) {
						
						f1=rs.getString("Jnl_Code");
				           f2=rs.getString("Jnl_Name");
				           f3=rs.getString("doc_Type");


							ser.add(f1);
							ser.add(f2);
							ser.add(f3);
						
					    }
					ob.setAl(ser);
					}
					
					
					
					if (searchFlag.equals("subsNo")){
						
						log4jLogger.info("start===========searchSubNo=====");
						if (searchValue.isEmpty() || searchValue.equals("")) {
							rs = st.executeQuery(DataQuery.JournalSubscriptionSearchfull );
							
						}else
						{
							rs = st.executeQuery(DataQuery.JournalSubscriptionSearchfull+" and jnl_name like '"+searchValue+"%'  order by jnl_name");
							
						}
						
						while (rs.next()) {
							
							f1=rs.getString("subs_no");
					           f2=rs.getString("Jnl_Name");
					           f3=rs.getString("supplier");


								ser.add(f1);
								ser.add(f2);
								ser.add(f3);
							
						    }
						
						
						ob.setAl(ser);
						
					}
					
					if (searchFlag.equals("budName")){
						
						log4jLogger.info("start===========searchJournalSearchBudgetName=====");
						
						if (searchValue.isEmpty() || searchValue.equals("")) {
							rs = st.executeQuery(DataQuery.BUDGET_SEARCH_LIST );
						}else{
							rs = st.executeQuery(DataQuery.BUDGET_SEARCH_LIKE
									
									+searchValue + "%'  order by bud_head");
						}	
						
						while (rs.next()) {
							
							f1=rs.getString("bud_code");
					           f2=rs.getString("bud_head");
					           f3=rs.getString("bud_amount");


								ser.add(f1);
								ser.add(f2);
								ser.add(f3);
							
						    }
						ob.setAl(ser);
					}
					
				
					if (searchFlag.equals("Sup")){
						log4jLogger.info("start===========searchJournalSearchPublisher=====");
						if (searchValue.isEmpty() || searchValue.equals("")) {
						rs = st.executeQuery(DataQuery.PUBSUBSEARCH_STRING_SUP );
					}else{
						rs = st.executeQuery(DataQuery.BOOKSEARCH_NAME_SUPLIKE
								
								+searchValue + "%'  order by sp_name");
					}
						while (rs.next()) {
							
							f1=rs.getString("sp_code");
					           f2=rs.getString("sp_name");
					           f3=rs.getString("short_desc");


								ser.add(f1);
								ser.add(f2);
								ser.add(f3);
							
						    }
						ob.setAl(ser);
						
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

				return ob;
				
						
			 
		 }
		 
		 public String findjnlSubsCalc(String frequency,String issueDate) {
			 
				
 			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Calendar c = Calendar.getInstance();
				log4jLogger.info("::::::::::::frequency::::::::::::"+frequency);
				log4jLogger.info("::::::::::::issueDate::::::::::::"+issueDate);
				 
			 try{
				
				 if(frequency.equals("MONTHLY")){
					 c.setTime(sdf.parse(issueDate));
					 c.add(Calendar.MONTH, 1);  
					 issueDate = sdf.format(c.getTime());
				 }
				 else if(frequency.equals("DAILY")){
					 c.setTime(sdf.parse(issueDate));
 					c.add(Calendar.DATE, 1);  
 					issueDate = sdf.format(c.getTime());
				 }
				/* else if(frequency.equals("WEEKLY TWO")){
					 c.setTime(sdf.parse(issueDate));
 					c.add(Calendar.DAY_OF_WEEK, (int) -3.5);  
 					issueDate = sdf.format(c.getTime());
				 }*/
				 else if(frequency.equals("WEEKLY")){
					 c.setTime(sdf.parse(issueDate));
 					c.add(Calendar.DATE, 7);  
 					issueDate = sdf.format(c.getTime());
				 }
				 else if(frequency.equals("FORTNIGHTLY")){
					 c.setTime(sdf.parse(issueDate));
 					c.add(Calendar.DATE, 14);  
 					issueDate = sdf.format(c.getTime());
				 }
				 else if(frequency.equals("BIMONTHLY")){
					 c.setTime(sdf.parse(issueDate));
 					c.add(Calendar.MONTH, 2);  
 					issueDate = sdf.format(c.getTime());
				 }
				 else if(frequency.equals("QUARTERLY")){
					 c.setTime(sdf.parse(issueDate));
 					c.add(Calendar.MONTH, 3);  
 					issueDate = sdf.format(c.getTime());
				 }
				 else if(frequency.equals("HALF YEARLY")){
					 c.setTime(sdf.parse(issueDate));
 					c.add(Calendar.MONTH, 6);  
 					issueDate = sdf.format(c.getTime());
				 }
				 else if(frequency.equals("ANNUAL")){
					 c.setTime(sdf.parse(issueDate));
 					c.add(Calendar.MONTH, 12);  
 					issueDate = sdf.format(c.getTime());
				 }
				 
				 
				 log4jLogger.info("finalIssueDate:::::::::::::::::::"+issueDate);
				 
				 
			 }catch(Exception e){
				 
			 }finally {
					try {
						if (rs != null) {
							rs.close();
						}
						if (Prest != null) {
							Prest.close();
						}
						if (con != null) {
							con.close();
						}

					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			 
			 return issueDate;
			 
			 
		 }
		 
		 
		 
	
			 
			 
		 
		 
	
			 
			 
		 
		 public String findjnlfrequencyFirstTime(String frequency,String issueDate) {
			 
				
	 			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					Calendar c = Calendar.getInstance();
					log4jLogger.info("::::::::::::frequency::::::::::::"+frequency);
					log4jLogger.info("::::::::::::issueDate::::::::::::"+issueDate);
					 
				 try{
					 if(frequency.equals("MONTHLY")){
						 c.setTime(sdf.parse(issueDate));
						 c.add(Calendar.MONTH, -1);  
						 issueDate = sdf.format(c.getTime());
					 }
					 else if(frequency.equals("DAILY")){
						 c.setTime(sdf.parse(issueDate));
	 					c.add(Calendar.DATE, -1);  
	 					issueDate = sdf.format(c.getTime());
					 }
					/* else if(frequency.equals("WEEKLY TWO")){
						 c.setTime(sdf.parse(issueDate));
	 					c.add(Calendar.DAY_OF_WEEK, (int) -3.5);  
	 					issueDate = sdf.format(c.getTime());
					 }*/
					 else if(frequency.equals("WEEKLY")){
						 c.setTime(sdf.parse(issueDate));
	 					c.add(Calendar.DATE, -7);  
	 					issueDate = sdf.format(c.getTime());
					 }
					 else if(frequency.equals("FORTNIGHTLY")){
						 c.setTime(sdf.parse(issueDate));
	 					c.add(Calendar.DATE, -14);  
	 					issueDate = sdf.format(c.getTime());
					 }
					 else if(frequency.equals("BIMONTHLY")){
						 c.setTime(sdf.parse(issueDate));
	 					c.add(Calendar.MONTH, -2);  
	 					issueDate = sdf.format(c.getTime());
					 }
					 else if(frequency.equals("QUARTERLY")){
						 c.setTime(sdf.parse(issueDate));
	 					c.add(Calendar.MONTH, -3);  
	 					issueDate = sdf.format(c.getTime());
					 }
					 else if(frequency.equals("HALF YEARLY")){
						 c.setTime(sdf.parse(issueDate));
	 					c.add(Calendar.MONTH, -6);  
	 					issueDate = sdf.format(c.getTime());
					 }
					 else if(frequency.equals("ANNUAL")){
						 c.setTime(sdf.parse(issueDate));
	 					c.add(Calendar.MONTH, -12);  
	 					issueDate = sdf.format(c.getTime());
					 }
					 
					 log4jLogger.info("finalIssueDate:::::::::::::::::::"+issueDate);
					 
					 
				 }catch(Exception e){
					 
				 }finally {
						try {
							if (rs != null) {
								rs.close();
							}
							if (Prest != null) {
								Prest.close();
							}
							if (con != null) {
								con.close();
							}

						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				 
				 return issueDate;
				 
				 
			 }
		
		 public int findjnlSubsFlagUpdate(String code) {
				int count = 0;
				try{
					log4jLogger.info("********************"+code);
					con = getSession().connection();
					Prest = con.prepareStatement(DataQuery.updateJournalSubsFlag);
					Prest.setString(1, code);
					Prest.executeUpdate();
					if (rs.next()) {

					}
				}catch (Exception e) {
				} finally {
					try {
						if (rs != null) {
							rs.close();
						}
						if (Prest != null) {
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
		 
		 
		 
	
	
}