	package Common.businessutil;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.SQLQuery;

import Common.DataQuery;
import Common.Security;

import Login.User;

import com.library.autolib.portal.dbconnectionutil.BaseDBConnection;

public class LoginUserDaoImpl extends BaseDBConnection implements LoginUserDao {
	java.sql.Connection con = null;
	
	java.sql.PreparedStatement Prest = null;

	java.sql.ResultSet rs = null;

	public User findById(String userId, String pwd) {
		User user = null;
		try {
			con = getSession().connection();

			String sql = "select Login_ID, Login_Password, User_Rights, Login_Flag,Staff_Name from login_mas where Login_ID ='"
					+ userId + "' and Login_Password='" + pwd + "'";
           			
			Prest = con.prepareStatement(sql);
			rs = Prest.executeQuery();

			if (rs.next()) {
				user = new User();
				user.setUserId(rs.getString(1));
				user.setPassword(rs.getString(2));
				user.setUserRights(rs.getString(3));
				user.setLoginFlag(rs.getString(4));
				user.setFirstName(rs.getString(5));
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

		return user;
	}
	
	
	/**
	 * Find TimeDate
	 * 
	 * @param userId
	 *            user id.
	 * @return the List
	 */
	public List findTimeDate(String userId)
	{
		String query = getSession().getNamedQuery("UserSelectTimeDateQuery").getQueryString();
		SQLQuery sql = getSession().createSQLQuery(query);
		sql.setParameter("userId", userId);
		return sql.list();
	}

	/**
	 * Save TimeDate
	 * 
	 * @param userId
	 *            the user id
	 */
	public void saveTimeDate(String userId)
	{
		String query = getSession().getNamedQuery("UserSaveTimeDateQuery").getQueryString();
		SQLQuery sql = getSession().createSQLQuery(query);
		sql.setParameter("userId", userId);
		sql.executeUpdate();

	}

	/**
	 * Update TimeDate
	 * 
	 * @param userId
	 *            the user id
	 * 
	 */
	public void updateTimeDate(String userId)
	{
		String queryUpdate = getSession().getNamedQuery("UserUpdateTimeDateQuery").getQueryString();
		SQLQuery sqlUpdate = getSession().createSQLQuery(queryUpdate);
		sqlUpdate.setParameter("userId", userId);
		sqlUpdate.executeUpdate();
	}
	
	
	public Map loadHomeEvent(String mcode)
	{
		Map<Object, Object> resParam = new HashMap<Object, Object>();
		
		try {
			String strsql = "";	
			// For Library Collection
			con = getSession().connection();          			
			Prest = con.prepareStatement(DataQuery.TotCollection_Count);
			rs = Prest.executeQuery();

			if (rs.next()) {
				
				resParam.put("TotalCollection", rs.getInt(1));				
			}
			
			
			// For Member Count
			con = getSession().connection();          			
			Prest = con.prepareStatement(DataQuery.TotMember_Count);
			rs = Prest.executeQuery();

			if (rs.next()) {
				
				resParam.put("TotalMember", rs.getInt(1));				
			}
			
			
			
			
			// For Due List  			
			Prest = con.prepareStatement(DataQuery.DueList_Count);
			rs = Prest.executeQuery();

			if (rs.next()) {
				
				resParam.put("DueListCount", rs.getInt(1));				
			}	
			
			
			// For Issue List  				
			Prest = con.prepareStatement(DataQuery.TotIssue_Count);
			rs = Prest.executeQuery();

			if (rs.next()) {
				
				resParam.put("IssueListCount", rs.getInt(1));				
			}
			
			// For today Issue List  	
			Prest = con.prepareStatement(DataQuery.todayIssue_Count + strsql);
			rs = Prest.executeQuery();

			if (rs.next()) {
				
				resParam.put("todayIssueListCount", rs.getInt(1));				
			}
			
			
		//------------------------------------------------	
			Prest = con.prepareStatement(DataQuery.todayTransSumAmount + strsql);
			rs = Prest.executeQuery();

			if (rs.next()) {
				
				resParam.put("todayTransAmount", rs.getDouble(1));				
			}
			
			
			Prest = con.prepareStatement(DataQuery.todayPaymentSumAmount + strsql);
			rs = Prest.executeQuery();

			if (rs.next()) {
				
				resParam.put("todaypaidAmount", rs.getDouble(1));				
			}
			
			Prest = con.prepareStatement(DataQuery.todayBalSumAmount);
			rs = Prest.executeQuery();

			if (rs.next()) {
				
				resParam.put("todayBalAmount", rs.getDouble(1));				
			}
			// For today Return List  				
			Prest = con.prepareStatement(DataQuery.todayReturn_Count + strsql);
			rs = Prest.executeQuery();

			if (rs.next()) {
				
				resParam.put("todayReturnListCount", rs.getInt(1));				
			}
			// For today Renew List  					
			Prest = con.prepareStatement(DataQuery.todayRenew_Count + strsql);
			rs = Prest.executeQuery();

			if (rs.next()) {
				
				resParam.put("todayRenewListCount", rs.getInt(1));				
			}
			
			// For Return List  				
			Prest = con.prepareStatement(DataQuery.TotReturn_Count);
			rs = Prest.executeQuery();

			if (rs.next()) 
			{

				resParam.put("ReturnListCount", rs.getInt(1));				
			}
			
			
			// For Renew List  					
			Prest = con.prepareStatement(DataQuery.TotRenew_Count);
			rs = Prest.executeQuery();

			if (rs.next()) {
				
				resParam.put("RenewListCount", rs.getInt(1));				
			}
//----------------------Gate Counts Starts-----------
			
			Prest = con.prepareStatement(DataQuery.totalUserLoginCount);
			rs = Prest.executeQuery();
			if (rs.next()) {
				resParam.put("totalUserCount", rs.getInt(1));
			}
			
			Prest = con.prepareStatement(DataQuery.todayUserLoginCount);
			rs = Prest.executeQuery();
			if (rs.next()) {
				resParam.put("todayUserCount", rs.getInt(1));
			}

			Prest = con.prepareStatement(DataQuery.totalgatelogin);
			rs = Prest.executeQuery();
			if (rs.next()) {
				resParam.put("totalGateLogin", rs.getInt(1));
				
			}
			
			Prest = con.prepareStatement(DataQuery.totalvisted);
			rs = Prest.executeQuery();
			if (rs.next()) {
				resParam.put("totalvisted", rs.getInt(1));
				
			}
			
			Prest = con.prepareStatement(DataQuery.todayGateVisitedCount);
			rs = Prest.executeQuery();
			if (rs.next()) {
				resParam.put("todayGateVisitedCount", rs.getInt(1));
				
			}
			
//----------------------Gate Counts Ends-----------
				
			Prest = con.prepareStatement(DataQuery.MEMBERVIEWSELECT_STRING);
			
			Prest.setString(1, mcode);
			rs = Prest.executeQuery();
			if (rs.next()) {
				resParam.put("member_code", rs.getString("member_code"));
				
				resParam.put("member_name", rs.getString("member_name"));
				
				resParam.put("desig_name", rs.getString("desig_name"));//designation
				
				resParam.put("dname", rs.getString("dname"));//department
				
				resParam.put("expiry_date", Security.TextDate_member(rs.getString("expiry_date")));
			}
			
			Prest = con.prepareStatement(DataQuery.SUGGESTION_COUNT);
			//Prest.setInt(1, todays_count);
			rs = Prest.executeQuery();
			if (rs.next()) {			
				resParam.put("suggestionCount", rs.getInt(1));
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
		
		
		
		return resParam;		
	}
	
	
	public Map loadUserTransactionHome(String code)
	{
		Map<Object, Object> resParam = new HashMap<Object, Object>();
		
		try {
			// For User Transaction History
			
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.ACCOUNT_ISSUE_COUNT);
			Prest.setString(1, code);
			rs = Prest.executeQuery();
			if (rs.next()) {
				
				resParam.put("UserIssueCount", rs.getString("issuecount"));
			}
			
			Prest = con.prepareStatement(DataQuery.ACCOUNT_RETURN_COUNT);
			Prest.setString(1, code);
			rs = Prest.executeQuery();
			if (rs.next()) {
				
				resParam.put("UserReturnCount", rs.getString("returncount"));
			}
			
			Prest = con.prepareStatement(DataQuery.ACCOUNT_RESERVE_COUNT);
			Prest.setString(1, code);
			rs = Prest.executeQuery();
			if (rs.next()) {
				
				resParam.put("UserReserveCount", rs.getString("reservecount"));
			}	
			
			//System.out.println(" Inside End  "+resParam.get("UserIssueCount")+resParam.get("UserReturnCount")+resParam.get("UserReserveCount"));
			
			Prest = con.prepareStatement(DataQuery.Account_Fine_Details);
			Prest.setString(1, code);
			rs = Prest.executeQuery();
			if (rs.next()) {
				
				resParam.put("totalAmount", rs.getString("total_fine"));
				resParam.put("paidAmount", rs.getString("paid_amount"));
				resParam.put("balAmount", rs.getString("bal_amount"));
				
			}else{
				
				resParam.put("totalAmount","0");
				resParam.put("paidAmount","0");
				resParam.put("balAmount","0");
				
			}
			
			
			Prest = con.prepareStatement(DataQuery.libraryMessage);
			//Prest.setString(1, code);
			rs = Prest.executeQuery();
			if (rs.next()) {
				

				resParam.put("LibraryMessage", rs.getString(2));
				resParam.put("WhatsNew", rs.getString(3));
			}	
			
			
			
			
	Prest = con.prepareStatement(DataQuery.MEMBERVIEWSELECT_STRING);
			
			Prest.setString(1, code);
			rs = Prest.executeQuery();
			if (rs.next()) {
				resParam.put("member_code", rs.getString("member_code"));
				resParam.put("member_name", rs.getString("member_name"));
				resParam.put("desig_name", rs.getString("desig_name"));//designation
				resParam.put("dname", rs.getString("dname"));//department
				resParam.put("expiry_date", Security.TextDate_member(rs.getString("expiry_date")));
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
		
		
		return resParam;			
	}
	
public byte[] findUserImage(byte[] userImage,String mcode){
	
		
		
		try {
			Prest = con.prepareStatement(DataQuery.MEMBERVIEWSELECT_STRING);
			Prest.setString(1, mcode);
			rs = Prest.executeQuery();
	
			if (rs.next()) {
				userImage=rs.getBytes("photo");
			}
				
		}catch (Exception e) {
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
		
		
		return userImage;
	}
	

}
