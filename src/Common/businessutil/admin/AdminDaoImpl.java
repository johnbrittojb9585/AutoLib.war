package Common.businessutil.admin;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Restrictions;

import Common.DataQuery;
import Common.Security;
import Common.businessutil.BusinessServiceFactory;
import Common.businessutil.calaloging.CalalogingService;
import Lib.Auto.BackVolume_Import.BvolumeDataBean;
import Lib.Auto.BackVolume_Import.BvolumeDataMessage;
import Lib.Auto.Book_Import.BookDataBean;
import Lib.Auto.Book_Import.BookDataMessage;
import Lib.Auto.Budget.BudgetBean;
import Lib.Auto.BulkChangesImport.ChangesDataBean;
import Lib.Auto.BulkChangesImport.ChangesDataMessage;
import Lib.Auto.BulkUpdate.BulkUpdateMsgBean;
import Lib.Auto.EBookImport.EBookDataBean;
import Lib.Auto.EBookImport.EBookDataMessage;
import Lib.Auto.EResource.EResourceBean;
//import Lib.Auto.EResourceImport.EResourceDataBean;
import Lib.Auto.EResourceImport.EResourceDataMessage;
import Lib.Auto.Fine.Finebean;
import Lib.Auto.GateRegister_Import.GateDataBean;
import Lib.Auto.Group.GroupBean;
import Lib.Auto.Holiday.Holidaybean;
import Lib.Auto.Issue_Mas_Update.IssueMasUpdatebean;
import Lib.Auto.Login.LoginBean;
import Lib.Auto.MemberTransfer.MemberTransRefBean;
import Lib.Auto.Member_Import.MemberDataBean;
import Lib.Auto.Member_Import.MemberDataMessage;
import Lib.Auto.Miscellaneous.MiscellaneousBean;
import Lib.Auto.QB_Import.QBDataBean;
import Lib.Auto.QB_Import.QBDataMessage;
import Lib.Auto.RefDueDays.RefDaysBean;
import Lib.Auto.Review.ReviewBean;
import Lib.Auto.Stock.BulkStockData;
import Lib.Auto.Stock.StockBean;
import Lib.Auto.SugEmail.SugmailBean;
import Lib.Auto.Suggestion.SuggestionBean;
import Lib.Auto.TransMas.TransMasBean;
import Lib.Auto.WeekEndHoliday.weekendholidaybean;

import com.library.autolib.portal.dbconnectionutil.BaseDBConnection;

public class AdminDaoImpl extends BaseDBConnection implements
		AdminDao {
	private static Logger log4jLogger = Logger.getLogger(AdminDaoImpl.class);
	
	java.sql.Connection con = null;

	java.sql.PreparedStatement Prest = null;
	java.sql.PreparedStatement Prest1 = null;
	java.sql.ResultSet rs = null;
	java.sql.ResultSet rs1 = null;
	java.sql.Statement st = null;
	
	//Fine
	
	public Finebean findFineMax() {
		log4jLogger.info("start===========findFineMax=====");

		Finebean newBean = null;
		try {
			
			
			 
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.NEW_FINE_MAX);
			rs = Prest.executeQuery();
			if (rs.next()) {
				newBean = new Finebean();
				newBean.setFcode(rs.getInt("maxno") + 1);
			
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
	
	public Finebean findFineSearch(int code) {
		log4jLogger.info("start===========findFineSearch====="+code);
		Finebean newBean = null;
		
		String searchgcode="",searchfperiod="",searchfamount="",searchftype="";
		int searchfcode=0;
		try {
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.NEW_FINE_SEARCH);
			Prest.setInt(1, code);
			rs = Prest.executeQuery();
						
			if (rs.next()) {
				
				
				searchfcode=rs.getInt("fine_id");
				 searchgcode=rs.getString("group_code");
				 searchfperiod=rs.getString("fine_period");
				 searchfamount=rs.getString("fine_amount");
				 searchftype=rs.getString("period_type");

			
			
			Prest = con.prepareStatement(DataQuery.NEW_FINE_SEARCH_GROUPNAME);
			Prest.setString(1, searchgcode);
			rs = Prest.executeQuery();
			if (rs.next()) {
				searchgcode=rs.getString("group_name");
			
				newBean = new Finebean();
				newBean.setFcode(searchfcode);
				newBean.setGcode(searchgcode);
				newBean.setFperiod(searchfperiod);
				newBean.setFamount(searchfamount);
				newBean.setType(searchftype);

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
		return newBean;
	}
	
	
	public int findFineDelete(int code) {
		log4jLogger.info("start===========findFineDelete=====");

		int count=0;
		try {
			
			
			 
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.FINE_DELETE);
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
	
	public int findFineSave(Finebean newbean) {
		log4jLogger.info("start===========findFineSave=====");

		int count=0;
		int Gname=0;
		try {
			
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.FINE_SELECT_GROUPNAME);
			Prest.setString(1, newbean.getGcode());
			rs=Prest.executeQuery();
			if (rs.next()) {
				
				Gname=rs.getInt("group_code");
			}
			
			 
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.FINE_SAVE);
			Prest.setInt(1, newbean.getFcode());
			Prest.setInt(2, Gname);
			Prest.setString(3, newbean.getFperiod());
			Prest.setString(4, newbean.getFamount());
			Prest.setString(5, newbean.getType());
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
	public int findFineUpdate(Finebean newbean) {
		log4jLogger.info("start===========findFineUpdate=====");

		int count=0;
		int Gname=0;
		try {
			
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.FINE_SELECT_GROUPNAME);
			Prest.setString(1, newbean.getGcode());
			rs=Prest.executeQuery();
			if (rs.next()) {
				
				Gname=rs.getInt("group_code");
			}
			
			 
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.FINE_UPDATE);
			Prest.setInt(5, newbean.getFcode());
			Prest.setInt(1, Gname);
			Prest.setString(2, newbean.getFperiod());
			Prest.setString(3, newbean.getFamount());
			Prest.setString(4, newbean.getType());
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
	
	
	public List findFineSearchName(String code) {
		log4jLogger.info("start===========findFineSearchName=====");
	
		Finebean newbean = null;
		List finalResults = null;
		newbean = new Finebean();
		
		try {
			con = getSession().connection();
			st = con.createStatement();

			if (code == "") {

				rs = st.executeQuery(DataQuery.FINE_LISTSEARCH);
			} else {

				rs = st.executeQuery(DataQuery.FINE_LISTSEARCH);

			}

			finalResults = new ArrayList();

			
			while (rs.next()) {
				newbean = new Finebean();
				newbean.setFcode(rs.getInt("fine_id"));
				newbean.setGcode(rs.getString("group_code"));
				newbean.setFamount(rs.getString("fine_amount"));
				finalResults.add(newbean);

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

	
	public List findGroupSearchName(String code) {
		log4jLogger.info("start===========findGroupSearchName=====");
	
		Finebean newbean = null;
		List finalResults = null;
		newbean = new Finebean();
		
		try {
			con = getSession().connection();
			st = con.createStatement();

			if (code == "") {

				rs = st.executeQuery(DataQuery.FINE_LISTSEARCH_GROUP);
			} else {

				rs = st.executeQuery(DataQuery.FINE_LISTSEARCH_GROUP_LIKE
						+ code + "%' order by group_Name,group_code");


			}

			finalResults = new ArrayList();

			
			while (rs.next()) {
				newbean = new Finebean();
				newbean.setFcode(rs.getInt("group_code"));
				newbean.setGcode(rs.getString("group_name"));
				newbean.setFamount(rs.getString("remarks"));
				finalResults.add(newbean);

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

	
	//Holiday
	
	public List findHolidaySearch() {
		log4jLogger.info("start===========findGroupSearchName=====");
		Holidaybean newbean=new Holidaybean();
		ArrayList finalResults = new ArrayList();
		//List finalResults = null;
		String holiday_date, holiday_day,holiday_remarks;
		
		try {
			con = getSession().connection();
			st = con.createStatement();

			

				rs = st.executeQuery(DataQuery.LISTSEARCH_HOLIDAY);
			
				

			
			while (rs.next()) {
				holiday_date=getholiday1(rs.getString("Leave_Date"));
				holiday_day=rs.getString("Add_Day");
				holiday_remarks=rs.getString("Remarks");
				if(holiday_remarks.equals(""))
				{
				holiday_remarks=" ";
				}
		
				finalResults.add(holiday_date);
				finalResults.add(holiday_day);
				finalResults.add(holiday_remarks);
				}
			newbean.setAl(finalResults);
			
				
				
				
				

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
	
	
	public String getholiday1(String holiday) {
		  
	     java.util.StringTokenizer stz_h=new java.util.StringTokenizer(holiday,"-");
			    int hy=Integer.parseInt(stz_h.nextToken());
			     int hm=Integer.parseInt(stz_h.nextToken());
			    int hd=Integer.parseInt(stz_h.nextToken());
		     	   String hissue_m=new Integer(hm).toString();
					    if(hissue_m.length()==1)
					    hissue_m="0"+hissue_m;
						String hissue_d=new Integer(hd).toString();
						if(hissue_d.length()==1)
					    hissue_d="0"+hissue_d;
						String HOLIDAYDATE=hissue_d+"-"+hissue_m+"-"+hy;
		return HOLIDAYDATE;
	  }
	
	
	public int findHolidayDeleteAll() {
		log4jLogger.info("start===========findHolidayDeleteAll=====");

		int count=0;
		try {
			
			
			 
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.HOLIDAY_DELETE);
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
	public int findHolidayDelete(String date) {
		log4jLogger.info("start===========findHolidayDelete====="+date);

		int count=0;
		try {
			
			
			 
			
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.HOLIDAY_SELECT_SINGLE);
			Prest.setString(1, date);
			rs=Prest.executeQuery();
			if (rs.next()) {
				
				Prest = con.prepareStatement(DataQuery.HOLIDAY_DELETE_SINGLE);
				Prest.setString(1, date);	
				Prest.executeUpdate();
				
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
	
	public int findHolidaySave(Holidaybean newbean) {
		log4jLogger.info("start===========findHolidaySave=====");

		int count=0;
		String no_of_days="";
		String Ddate="";
		java.util.Date Ddate1 = new Date();
		java.util.Date Ddate2 = new Date();
		int a;
		int Holiday_Count = 0,First_Holiday = 0,Second_Holiday = 0, Wday = 0;
		int no=1;
		try {
			
			
			 
			
			con = getSession().connection();
			Prest = con.prepareStatement("select datediff( '"+newbean.getTo()+"' , '"+newbean.getFrom()+"' ) as no_of_days");
			rs=Prest.executeQuery();
			if (rs.next()) {
				
				no_of_days=rs.getString("no_of_days");
				
			    int biy=Integer.parseInt(no_of_days);

	
			int n=biy;
			
			
			
			if(n<0)
			{
				count=1;
				}
				else
				{
			for(int i=0;i<=n;i++)
			{
				
				Prest = con.prepareStatement("select adddate( '"+newbean.getFrom()+"' , '"+i+"' ) as days");
				rs=Prest.executeQuery();
				
						if(rs.next())
						{
							 Ddate=rs.getString("days");
							
							 Prest = con.prepareStatement("select Leave_date  from holiday_mas where leave_date='"+Ddate+"'");
								rs=Prest.executeQuery();
						
	    				
	    						if(rs.next())
								{
									String datew=rs.getString("Leave_date");
	
									Prest = con.prepareStatement("update holiday_mas set leave_date='"+Ddate+"',add_day="+no+",remarks='"+newbean.getRemarks().trim()+"' where leave_date='"+Ddate+"'");
									Prest.executeUpdate();
									
									
									
								}
								else
								{
									Prest = con.prepareStatement("insert into holiday_mas values('"+Ddate+"',"+no+",'"+newbean.getRemarks().trim()+"')");
									Prest.executeUpdate();
									
									
								}
	
						}
	
	}
			
			for(int i=0;i<=n;i++)
			{
					
				Prest = con.prepareStatement("select adddate( '"+newbean.getFrom()+"' , '"+i+"' ) as days");
				rs=Prest.executeQuery();
				 
				 if(rs.next())
				 {
					Ddate1=rs.getDate("days");
					Ddate2=Ddate1;	
				    Prest = con.prepareStatement("select member_code,access_no from issue_mas where due_date='"+Ddate1+"'");
				    rs=Prest.executeQuery();
				
				    if(rs.next())
				    {
				    	log4jLogger.info("start===========Update Holiday as Due_Date in Issue Master=====");
				    	boolean Rflag = true;
				    	while (Rflag == true) {
				    		
				    		Prest=con.prepareStatement("select date_format('"+Ddate1+"','%w') as days");
				    		rs = Prest.executeQuery();

				    		if (rs.next()) {
				    			String day = rs.getString("days");
				    			int name_of_day = Integer.parseInt(day);
				    			
				    			if (name_of_day == 0) {			    			
				    				a=1;				
				    				Prest =
				    					con.prepareStatement(
				    						"select adddate('"+Ddate1+"', '"+a+"') as days");
				    				rs = Prest.executeQuery();
				    				if (rs.next()) {
				    					Ddate1 = rs.getDate("days");
				    					}				
				    			}

				    			if (name_of_day == 6) {			    				
				    				a=2;
				    				Prest =
				    					con.prepareStatement(
				    						"select adddate('"+Ddate1+"', '"+a+"') as days");
				    				
				    				rs = Prest.executeQuery();
				    				if (rs.next()) {
				    					Ddate1 = rs.getDate("days");
				    					}				
				    			}

				    			Prest =
				    				con.prepareStatement(
				    					"select Leave_date  from holiday_mas where leave_date='"
				    						+ Ddate1
				    						+ "'");
				    			rs = Prest.executeQuery();
				    			if (rs.next()) {			    				
				    				a=1;			    				
				    				Prest =
				    					con.prepareStatement(
				    						"select adddate('"+Ddate1+"', '"+a+"') as days");
				    				
				    				rs = Prest.executeQuery();

				    				if (rs.next()) {
				    					Ddate1 = rs.getDate("days");			    				
				    					Rflag = true;

				    				}
				    				
				    			} else {
				    				Rflag = false;
				    			}
				    			// For WeekEnd Holiday Master
				    			
				    			Prest =		
				    				con.prepareStatement(
				    					"select DAYOFWEEK('"+Ddate1+"') as weekday");
				    			rs = Prest.executeQuery();
				    			if (rs.next()) {			    				
				    					Wday = rs.getInt("weekday");
				    			}
				    			Prest =
				    				con.prepareStatement(
				    					"Select Day_ID from weekEnd_Holyday_Mas");
				    			rs = Prest.executeQuery();
				    			if (rs.next()) {			    				
				    				Prest=null;
				    				boolean b=rs.last();
				    				if(b)
				    				{
				    				 Holiday_Count=rs.getRow();
				    				}
				    				rs.first();
				    				
				    				if(Holiday_Count==1)
				    				{
				    					First_Holiday=rs.getInt("Day_ID");			    				
				    					if(Wday==First_Holiday)
				    					{
				    						Prest =
				    							con.prepareStatement(
				    								"select adddate('"+Ddate1+"', 1) as days");
				    					}
				    				}
				    				if(Holiday_Count==2)
				    				{
				    					First_Holiday=rs.getInt("Day_ID");
				    					rs.next();			
				    					Second_Holiday=rs.getInt("Day_ID");
				    					
				    					if(Second_Holiday!=7)
				    					{
				    						if(Wday==First_Holiday)
				    						{
				    							Prest =
				    								con.prepareStatement(
				    									"select adddate('"+Ddate1+"', 2) as days");
				    						}else if(Wday==Second_Holiday)
				    						{
				    							Prest =
				    								con.prepareStatement(
				    									"select adddate('"+Ddate1+"', 1) as days");
				    						}		
				    					}else{
				    						if(Wday==Second_Holiday)
				    						{
				    							Prest =
				    								con.prepareStatement(
				    									"select adddate('"+Ddate1+"', 2) as days");
				    						}else if(Wday==First_Holiday)
				    						{
				    							Prest =
				    								con.prepareStatement(
				    									"select adddate('"+Ddate1+"', 1) as days");
				    						}				
				    						
				    					}		
				    				}
				    				
				    				if(Prest!=null)
				    				{
				    				rs = Prest.executeQuery();
				    				if (rs.next()) {
				    					Ddate1 = rs.getDate("days");					
				    				}	
				    				}
				    				
				    				Prest =
				    					con.prepareStatement(
				    						"select Leave_date  from holiday_mas where leave_date='"
				    							+ Ddate1
				    							+ "'");
				    				rs = Prest.executeQuery();
				    				if (rs.next()) {
				    					Rflag = true;
				    				}else{
				    					Rflag = false;
				    				}				
				    		    }		
				    		}
				    	}	

				    	 Prest =
								con.prepareStatement(
									"update issue_mas set due_date='"+Ddate1+"' where due_date='"
										+ Ddate2
										+ "'");
							Prest.executeUpdate();
				    }     
		    	}	 
			}		
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

		return count;
	}
	
 // WeakEndHoliday 
	
	public int findWeakEndHolidaySave(int code,int code1) {
		log4jLogger.info("start===========findWeakEndHolidaySave=====");

		int Day_ID,fholiday,sholiday;
		fholiday=code;
		sholiday=code1;
		String Remarks="";
		int count=0;
		try
		{
			con = getSession().connection();				
			Prest = con.prepareStatement("Delete from WeekEnd_Holyday_Mas");
			Prest.executeUpdate();	
			log4jLogger.info("=== WeakEnd Cleared ===");
			
		if(fholiday==1 || sholiday==1)
		{
			Day_ID=1;
			Remarks="SUNDAY";
			count=SaveWeakEnd(Day_ID,Remarks);			
		}if(fholiday==2 || sholiday==2)
		{
			Day_ID=2;
			Remarks="MONDAY";
			count=SaveWeakEnd(Day_ID,Remarks);			
		}if(fholiday==3 || sholiday==3)
		{
			Day_ID=3;
			Remarks="TUESDAY";
			count=SaveWeakEnd(Day_ID,Remarks);			
		}if(fholiday==4 || sholiday==4)
		{
			Day_ID=4;
			Remarks="WEDNESDAY";
			count=SaveWeakEnd(Day_ID,Remarks);			
		}if(fholiday==5 || sholiday==5)
		{
			Day_ID=5;
			Remarks="THURSDAY";
			count=SaveWeakEnd(Day_ID,Remarks);			
		}if(fholiday==6 || sholiday==6)
		{
			Day_ID=6;
			Remarks="FRIDAY";
			count=SaveWeakEnd(Day_ID,Remarks);			
		}if(fholiday==7 || sholiday==7)
		{
			Day_ID=7;
			Remarks="SATURDAY";
			count=SaveWeakEnd(Day_ID,Remarks);			
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
	
	public int SaveWeakEnd(int code,String remarks)
	throws SQLException{
	int count=0;
	log4jLogger.info("=== WeakEnd ==="+code+remarks);
	try { 				
		    con = getSession().connection();				
			//Prest = con.prepareStatement("Delete from WeekEnd_Holyday_Mas");
			//Prest.executeUpdate();	
			//log4jLogger.info("=== WeakEnd Cleared ===");
			Prest1 = con.prepareStatement("insert into WeekEnd_Holyday_Mas(Day_ID,Add_Field1) values("+code+",'"+remarks+"')");
			Prest1.executeUpdate();
			count=1; 
			log4jLogger.info("=== WeakEnd Added ===");
		
	
	} catch (Exception e) {

	} finally {
		try {
			if (rs != null) {
				rs.close();
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
	return count;
}

	public int findWeakEndHolidaySearch() {		
		weekendholidaybean newbean=null;
		ArrayList finalResults=new ArrayList();
		int count = 0;
		log4jLogger.info("=== WeakEnd Search and Retrival ===");
		try{			
			    con = getSession().connection();
			    Prest = con.prepareStatement("select Day_ID from WeekEnd_Holyday_Mas");
				rs=Prest.executeQuery();				
				
				newbean=new weekendholidaybean();
				while(rs.next())
				{					
					finalResults.add(rs.getInt("Day_ID"));						
				}	
				newbean.setDay_ID(finalResults);
			count=1;
			
		}catch(Exception e){
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
	
	// ---------------- Suggestion ----------------------------
	
				public List findSuggestionList(SuggestionBean sugBean,String Query)
				{
					log4jLogger.info("start===========findSuggestionList=====");
					
					SuggestionBean suggestionBean = null;
					List<Object> result = new ArrayList<Object>();
					
					SQLQuery sql = getSession().createSQLQuery(Query);
								
					List list = sql.list();
							
					for(int i=0; i<list.size(); i++)
					{
						Object[] obj = (Object[]) list.get(i);
						suggestionBean = new SuggestionBean();
												
						suggestionBean.setSugNo(Integer.parseInt(obj[0].toString()));
						suggestionBean.setMemberCode(obj[1].toString());
						suggestionBean.setDoc(obj[2].toString());
						suggestionBean.setRcDate(obj[3].toString());
						suggestionBean.setSugName(obj[4].toString());
						suggestionBean.setRemarks(obj[5].toString());
						suggestionBean.setActionTaken(obj[6].toString());
						suggestionBean.setActionTakenDate(obj[7].toString());
						suggestionBean.setStatus(obj[8].toString());
						
										
						result.add(suggestionBean);
						
						
					}	
					
					return result;
					
				}  


	
	//==============================Budget===============================
	
	
	public BudgetBean findBudgetMax() {

		log4jLogger.info("start===========findBudgetMax=====");

		BudgetBean newBean = null;
		try {
			
			
			 
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.NEW_BUDGET_MAX);
			rs = Prest.executeQuery();
			if (rs.next()) {
				newBean = new BudgetBean();
				int Budgetcode=rs.getInt("maxno")+1;
			
				newBean.setBudCode(Budgetcode);
			
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
	
	public BudgetBean findBudgetSearch(String code) {

		log4jLogger.info("start===========findBudgetSearch====="+code);
		BudgetBean ob = null;
		String strsql = "";
		
		try {

			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.BUDGET_UpdateAll+"('"+code+"')");
			rs = Prest.executeQuery();
			
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.BUDGET_SEARCH+strsql);
			Prest.setInt(1, (Integer.parseInt(code)));
			rs = Prest.executeQuery();
		
			if (rs.next()) {

				ob = new BudgetBean();
			  
				 ob.setBudCode(rs.getInt("Bud_code"));
			     ob.setBudHead(rs.getString("Bud_Head"));
				 ob.setDeptCode(rs.getInt("dept_code"));
				 ob.setBudAmount(rs.getString("bud_amount"));
				 ob.setBudSAmount(rs.getString("bud_samount"));
				 ob.setFrom(getholiday(rs.getString("bud_from")));
				 ob.setTo(getholiday(rs.getString("bud_to")));
				 ob.setRemarks(rs.getString("remarks"));
				 
				 ob.setbAmount(rs.getString("B_Amount"));
				 ob.setbSAmount(rs.getString("B_SAmount"));
				 
				 ob.setBbAmount(rs.getString("BB_Amount"));
				 ob.setBbSAmount(rs.getString("BB_SAmount"));
				 
				 ob.setNbAmount(rs.getString("NB_Amount"));
				 ob.setNbSAmount(rs.getString("NB_SAmount"));
				 
				 ob.setrAmount(rs.getString("R_Amount"));
				 ob.setrSAmount(rs.getString("R_SAmount"));
				 
				 ob.settAmount(rs.getString("T_Amount"));
				 ob.settSAmount(rs.getString("T_SAmount"));
				 
				 ob.setsAmount(rs.getString("S_Amount"));
				 ob.setsSAmount(rs.getString("S_SAmount"));
				 
				 ob.setpAmount(rs.getString("P_Amount"));
				 ob.setpSAmount(rs.getString("P_SAmount"));
				 
				 ob.setjAmount(rs.getString("J_Amount"));
				 ob.setjSAmount(rs.getString("J_SAmount"));
				 
				 ob.setmAmount(rs.getString("M_Amount"));
				 ob.setmSAmount(rs.getString("M_SAmount"));
				 ob.setDeptname(rs.getString("Dept_Name"));
				
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
		

		return ob;
	
		
		
		
	}
	
	public String getholiday(String holiday) {
	     java.util.StringTokenizer stz_h=new java.util.StringTokenizer(holiday,"-");
		    int hy=Integer.parseInt(stz_h.nextToken());
		     int hm=Integer.parseInt(stz_h.nextToken());
		    int hd=Integer.parseInt(stz_h.nextToken());
	     	   String hissue_m=new Integer(hm).toString();
				    if(hissue_m.length()==1)
				    hissue_m="0"+hissue_m;
					String hissue_d=new Integer(hd).toString();
					if(hissue_d.length()==1)
				    hissue_d="0"+hissue_d;
					String HOLIDAYDATE=hissue_d+"-"+hissue_m+"-"+hy;
	return HOLIDAYDATE;
}
	
	public int findBudgetInterface(String code) {
		log4jLogger.info("start===========findBudgetInterface=====");

	int count=0;
		try {
			
			
			 
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.BUDGET_INTERFACE);
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
	
	public int findBudgetDelete(String code) {
		log4jLogger.info("start===========findBudgetDelete=====");

	int count=0;
		try {
			
			
			 
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.BUDGET_DELETE);
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
	
	
	public BudgetBean findBudgetDeptSearchName(String code) {


		log4jLogger.info("start===========findGroupSearchName=====");
		BudgetBean newbean=new BudgetBean();
		ArrayList finalResults = new ArrayList();
		//List finalResults = null;
		String strsql="";
		
		try {
			


			
			con = getSession().connection();
			st = con.createStatement();

			if (code == "") {

				rs = st.executeQuery(DataQuery.BUDGET_DEPT_SEARCH);
			}else{
				rs = st.executeQuery(DataQuery.BUDGET_DEPT_SEARCH_LIKE
						
						+code + "%'"+strsql+" order by dept_name");
			}
			
			while (rs.next()) {
				finalResults.add(rs.getString("dept_name"));
				finalResults.add(rs.getString("dept_code"));
				finalResults.add(rs.getString("Short_Desc"));
				}
			newbean.setAl(finalResults);
			

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

		return newbean;
	
	}
	
	
	public BudgetBean findBudgetUpdateSAmount()
	{
		
		

		log4jLogger.info("start===========findBudgetUpdateSAmount=====");

		BudgetBean newBean = null;
		try {			 
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.BUDGET_Select_SAmount);
			rs = Prest.executeQuery();
			
			
			
			while (rs.next()) {				
				Prest = con.prepareStatement(DataQuery.BUDGET_Update_SAmount);
				Prest.setString(1, rs.getString(2));
				Prest.setString(2, rs.getString(1));
				Prest.executeUpdate();			
			}
			
			// For New Budget Code
			newBean = findBudgetMax();
			
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
	
	
	public BudgetBean findBudgetSearchName(String code) {

		log4jLogger.info("start===========findBudgetSearchName=====");
		BudgetBean newbean=new BudgetBean();
		ArrayList finalResults = new ArrayList();
		//List finalResults = null;
		String strsql="";
		
		try {


			
			con = getSession().connection();
			st = con.createStatement();

			if (code == "") {

				rs = st.executeQuery(DataQuery.BUDGET_SEARCH_LIST);
			}else{
				rs = st.executeQuery(DataQuery.BUDGET_SEARCH_LIKE
						
						+code + "%'  order by bud_head");
			}
			
			while (rs.next()) {				
				
				finalResults.add(rs.getString("bud_code"));
				finalResults.add(rs.getString("bud_head"));
				finalResults.add(rs.getString("bud_amount"));
				
				}
			
			newbean.setAl(finalResults);		
				
				

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

		return newbean;
	
		
	}
	
	
	
	public int findBudgetSave(BudgetBean newbean) {
		log4jLogger.info("start===========findBudgetSave=====");

		int count=0;
		String Gname="";
		try {
			
			
			log4jLogger.info(":::::::::::::department_name::::::::::::::"+newbean.getDeptname());

			
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.BUDGET_SEARCH_DEPT_CODE);
			Prest.setString(1, newbean.getDeptname());
			rs=Prest.executeQuery();
			if (rs.next()) {
				
				Gname=rs.getString("dept_code");
								
			}else{
				Gname="1";
			}
			
			
			
			 
			
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.BUDGET_SAVE);
				
			Prest.setInt(1, newbean.getBudCode());
			Prest.setString(2, newbean.getBudHead());
			Prest.setString(3, Gname);
			Prest.setString(4, newbean.getBudAmount());
			Prest.setString(5, newbean.getBudSAmount());
			Prest.setString(6, newbean.getFrom());
			Prest.setString(7, newbean.getTo());
			Prest.setString(8, newbean.getRemarks());
			
			
			Prest.setString(9, newbean.getbAmount());
			Prest.setString(10, newbean.getbSAmount());
			
			Prest.setString(11, newbean.getBbAmount());
			Prest.setString(12, newbean.getBbSAmount());
			
			Prest.setString(13, newbean.getNbAmount());
			Prest.setString(14, newbean.getNbSAmount());
			
			
			Prest.setString(15, newbean.getrAmount());
			Prest.setString(16, newbean.getrSAmount());
			
			
			Prest.setString(17, newbean.gettAmount());
			Prest.setString(18, newbean.gettSAmount());
			
			Prest.setString(19, newbean.getsAmount());
			Prest.setString(20, newbean.getsSAmount());
			
			Prest.setString(21, newbean.getpAmount());
			Prest.setString(22, newbean.getpSAmount());
			
			Prest.setString(23, newbean.getjAmount());
			Prest.setString(24, newbean.getjSAmount());
			
			Prest.setString(25, newbean.getmAmount());
			Prest.setString(26, newbean.getmSAmount());
			
			
			
			
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
	public int findBudgetUpdate(BudgetBean newbean) {


		log4jLogger.info("start===========findBudgetUpdate=====");

		int count=0;
		String Gname="";
		try {
			
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.BUDGET_SEARCH_DEPT_CODE);
			Prest.setString(1, newbean.getDeptname());
			rs=Prest.executeQuery();
			if (rs.next()) {
				Gname=rs.getString("dept_code");
			}			
			 
			
			
			
			Prest = con.prepareStatement(DataQuery.BUDGET_UPDATE);
			//update budget_mas set bud_head=?,dept_code=?,bud_amount=?,bud_samount=?,bud_from=?,bud_to=?,remarks=?,Branch_Code=? where bud_code=?
			
			
			Prest.setString(1, newbean.getBudHead());
			Prest.setString(2, Gname);
			Prest.setString(3, newbean.getBudAmount());
			Prest.setString(4, newbean.getBudSAmount());
			Prest.setString(5, newbean.getFrom());
			Prest.setString(6, newbean.getTo());
			Prest.setString(7, newbean.getRemarks());
			
			Prest.setString(8, newbean.getbAmount());
			Prest.setString(9, newbean.getbSAmount());
			
			Prest.setString(10, newbean.getBbAmount());
			Prest.setString(11, newbean.getBbSAmount());
			
			Prest.setString(12, newbean.getNbAmount());
			Prest.setString(13, newbean.getNbSAmount());
			
			
			Prest.setString(14, newbean.getrAmount());
			Prest.setString(15, newbean.getrSAmount());
			
			
			Prest.setString(16, newbean.gettAmount());
			Prest.setString(17, newbean.gettSAmount());
			
			Prest.setString(18, newbean.getsAmount());
			Prest.setString(19, newbean.getsSAmount());
			
			Prest.setString(20, newbean.getpAmount());
			Prest.setString(21, newbean.getpSAmount());
			
			Prest.setString(22, newbean.getjAmount());
			Prest.setString(23, newbean.getjSAmount());
			
			Prest.setString(24, newbean.getmAmount());
			Prest.setString(25, newbean.getmSAmount());
			
		
			Prest.setInt(26, newbean.getBudCode());
			
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
	
	// ---------------- Book Review ----------------------------
	
		public List findReviewList(ReviewBean revBean)
		{
			log4jLogger.info("start===========findReviewList=====");
			
			ReviewBean reviewBean = null;
			List<Object> result = new ArrayList<Object>();
			
			SQLQuery sql = getSession().createSQLQuery(DataQuery.REVIEWSEARCH_NAME);
			sql.setParameter("AccessNo", revBean.getAccessNo());
			
			List list = sql.list();	
			for(int i=0; i<list.size(); i++)
			{
				Object[] obj = (Object[]) list.get(i);
				reviewBean = new ReviewBean();
										
				reviewBean.setReviewNo(Integer.parseInt(obj[0].toString()));
				reviewBean.setAccessNo(obj[1].toString());
				reviewBean.setMemberCode(obj[2].toString());
				reviewBean.setRcDate(obj[3].toString());
				reviewBean.setTitle(obj[4].toString());
				reviewBean.setDesc(obj[5].toString());
				reviewBean.setRating(Integer.parseInt(obj[6].toString()));
				
				result.add(reviewBean);
				log4jLogger.info("Rating===========Rating Value====="+reviewBean.getDesc());
				log4jLogger.info("Rating===========Rating Value====="+reviewBean.getRating());
			}	
			return result;
			
		}  

	
	//==========================================Group========================
	
	public GroupBean findGroupMax() {
		log4jLogger.info("start===========findGroupMax=====");

		GroupBean newBean = null;
		try {
			
			
			 
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.NEW_GROUP_MAX);
			rs = Prest.executeQuery();
			if (rs.next()) {
				newBean = new GroupBean();
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
	
	
	public GroupBean findGroupSearch(String code) {
		log4jLogger.info("start===========findGroupSearch====="+code);
		GroupBean ob = null;	
		
		try {
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.GROUP_SEARCH);
			Prest.setString(1, code);
			rs = Prest.executeQuery();

			if (rs.next()) {
				
				ob=new GroupBean();
				ob.setCode(rs.getInt("Group_code"));
				 ob.setName(rs.getString("Group_name"));
				 ob.setGeli(rs.getInt("geligible"));
				 ob.setBeli(rs.getInt("beligible"));
				 ob.setBbeli(rs.getInt("bbeligible"));
				 ob.setBveli(rs.getInt("bveligible"));
				 ob.setNbeli(rs.getInt("nbeligible"));
				 ob.setJeli(rs.getInt("jeligible"));
				 ob.setSeli(rs.getInt("seligible"));
				 ob.setReli(rs.getInt("religible"));
				 ob.setPeli(rs.getInt("peligible"));
				 ob.setTeli(rs.getInt("teligible"));
				 ob.setGlper(rs.getInt("glperiod"));
				 ob.setBlper(rs.getInt("blperiod"));
				 ob.setBblper(rs.getInt("bblperiod"));
	             ob.setBvlper(rs.getInt("bvlperiod"));
				 ob.setNblper(rs.getInt("nblperiod"));
				 ob.setJlper(rs.getInt("jlperiod"));
				 ob.setSlper(rs.getInt("slperiod"));
				 ob.setRlper(rs.getInt("rlperiod"));
				 ob.setPlper(rs.getInt("plperiod"));
				 ob.setTlper(rs.getInt("tlperiod"));
				 ob.setRemarks(rs.getString("remarks"));
				 ob.setRenewal(rs.getInt("renewal"));
				 ob.setStatus(rs.getString("status"));

				 ob.setGrper(rs.getInt("GRPeriod"));  // RK Start
				 ob.setBrper(rs.getInt("BRPeriod"));
				 ob.setBbrper(rs.getInt("BBRPeriod"));
	             ob.setBvrper(rs.getInt("BVRPeriod"));
				 ob.setNbrper(rs.getInt("NBRPeriod"));
				 ob.setJrper(rs.getInt("JRPeriod"));
				 ob.setSrper(rs.getInt("SRPeriod"));
				 ob.setRrper(rs.getInt("RRPeriod"));
				 ob.setPrper(rs.getInt("PRPeriod"));
				 ob.setTrper(rs.getInt("TRPeriod"));				 
				 ob.setReserve(rs.getInt("Reserve_Book_Max"));		

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

		return ob;
	}
	
	public int findGroupInterface(String code) {
		log4jLogger.info("start===========findGroupInterface=====");

	int count=0;
		try {
			
			
			 
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.GROUP_INTERFACE);
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
	
	public int findGroupNameInterface(String code) {
		log4jLogger.info("start===========findGroupNameInterface=====");

	int count=0;
		try {	
			 
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.GROUP_NAME_INTERFACE);
			Prest.setString(1, code);
			rs = Prest.executeQuery();
			if (rs.next()) {
				
			count=rs.getInt("Group_code");
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

	
	public int findGroupDelete(String code) {
		log4jLogger.info("start===========findGroupDelete=====");

	int count=0;
		try {
			
			
			 
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.GROUP_DELETE);
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
	
	public int findGroupSave(GroupBean newbean) {
		log4jLogger.info("start===========findGroupSave=====");

		int count=0;
		String Gname="";
		try {
			
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.GROUP_SAVE);
			Prest.setInt(1, newbean.getCode());
			Prest.setString(2, newbean.getName());
			Prest.setInt(3, newbean.getGeli());
			Prest.setInt(4, newbean.getBeli());
			Prest.setInt(5, newbean.getBbeli());
			Prest.setInt(6, newbean.getBveli());
			Prest.setInt(7, newbean.getNbeli());
			Prest.setInt(8, newbean.getJeli());			
			Prest.setInt(9, newbean.getSeli());
			Prest.setInt(10, newbean.getReli());
			Prest.setInt(11, newbean.getPeli());
			Prest.setInt(12, newbean.getTeli());			
			Prest.setInt(13, newbean.getGlper());
			Prest.setInt(14, newbean.getBlper());
			Prest.setInt(15, newbean.getBblper());			
			Prest.setInt(16, newbean.getBvlper());
			Prest.setInt(17, newbean.getNblper());
			Prest.setInt(18, newbean.getJlper());
			Prest.setInt(19, newbean.getSlper());			
			Prest.setInt(20, newbean.getRlper());
			Prest.setInt(21, newbean.getPlper());			
			Prest.setInt(22, newbean.getTlper());
			Prest.setString(23, newbean.getRemarks());
			Prest.setInt(24, newbean.getRenewal());
			Prest.setString(25, newbean.getStatus());
			
			Prest.setInt(26, newbean.getGrper()); // RK Start
			Prest.setInt(27, newbean.getBrper());
			Prest.setInt(28, newbean.getBbrper());
			Prest.setInt(29, newbean.getBvrper());
			Prest.setInt(30, newbean.getNbrper());			
			Prest.setInt(31, newbean.getJrper());
			Prest.setInt(32, newbean.getSrper());
			Prest.setInt(33, newbean.getRrper());
			Prest.setInt(34, newbean.getPrper());
			Prest.setInt(35, newbean.getTrper());
			Prest.setInt(36, newbean.getReserve());
			
						
			Prest.executeUpdate();
//			if (rs.next()) {
//				
//			
//			}
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
	public int findGroupUpdate(GroupBean newbean) {
		log4jLogger.info("start===========findGroupUpdate====="+newbean.getCode());

		int count=0;
		String Gname="";
		try {
				 
				 
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.GROUP_UPDATE);
			
			
			Prest.setString(1, newbean.getName());
			Prest.setInt(2, newbean.getGeli());
			Prest.setInt(3, newbean.getBeli());
			Prest.setInt(4, newbean.getBbeli());
			Prest.setInt(5, newbean.getBveli());
			Prest.setInt(6, newbean.getNbeli());
			Prest.setInt(7, newbean.getJeli());			
			Prest.setInt(8, newbean.getSeli());
			Prest.setInt(9, newbean.getReli());
			Prest.setInt(10, newbean.getPeli());
			Prest.setInt(11, newbean.getTeli());			
			Prest.setInt(12, newbean.getGlper());
			Prest.setInt(13, newbean.getBlper());
			Prest.setInt(14, newbean.getBblper());			
			Prest.setInt(15, newbean.getBvlper());
			Prest.setInt(16, newbean.getNblper());
			Prest.setInt(17, newbean.getJlper());
			Prest.setInt(18, newbean.getSlper());			
			Prest.setInt(19, newbean.getRlper());
			Prest.setInt(20, newbean.getPlper());			
			Prest.setInt(21, newbean.getTlper());
			Prest.setString(22, newbean.getRemarks());
			Prest.setInt(23, newbean.getRenewal());
			Prest.setString(24, newbean.getStatus());
			
			Prest.setInt(25, newbean.getGrper()); // RK Start
			Prest.setInt(26, newbean.getBrper());
			Prest.setInt(27, newbean.getBbrper());
			Prest.setInt(28, newbean.getBvrper());
			Prest.setInt(29, newbean.getNbrper());			
			Prest.setInt(30, newbean.getJrper());
			Prest.setInt(31, newbean.getSrper());
			Prest.setInt(32, newbean.getRrper());
			Prest.setInt(33, newbean.getPrper());
			Prest.setInt(34, newbean.getTrper());
			Prest.setInt(35, newbean.getReserve());
			
			Prest.setInt(36, newbean.getCode());			
			Prest.executeUpdate();
			
			if (rs.next()) {			
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
	
	public List findGroupGenSearch(GroupBean newbean) {
		log4jLogger.info("start===========findGroupGenSearch=====");
		GroupBean bean=new GroupBean();
		List<GroupBean> finalResults = new ArrayList<GroupBean>();		
		
		try {			
			
			if (newbean.getStatus().equals("Gen"))
			{
			con = getSession().connection();
			st = con.createStatement();
			if (newbean.getRemarks() == "") {
				rs = st.executeQuery(DataQuery.GROUP_GEN_SEARCH );
			}else{
				rs = st.executeQuery(DataQuery.GROUP_GEN_SEARCH_LIKE
						
						+newbean.getRemarks() + "%'  and status='V1' order by group_code");
			}
		
			while (rs.next()) {
				   String f1=rs.getString("group_Code");
		           String f2=rs.getString("group_Name");
		           String f3=rs.getString("remarks");

		           bean=new GroupBean();
		           bean.setCode(Integer.parseInt(f1));
		           bean.setName(f2);
		           bean.setRemarks(f3);
		           
		           finalResults.add(bean);
		           }			
			}	
			if (newbean.getStatus().equals("Spe")){
				con = getSession().connection();
				st = con.createStatement();
				if (newbean.getRemarks() == "") {
					rs = st.executeQuery(DataQuery.GROUP_SPE_SEARCH);
				}else{
					rs = st.executeQuery(DataQuery.GROUP_GEN_SEARCH_LIKE
							
							+newbean.getRemarks() + "%'  and status='V2' order by group_code");
				}
			
				while (rs.next()) {
					   String f1=rs.getString("group_Code");
			           String f2=rs.getString("group_Name");
			           String f3=rs.getString("remarks");

			           bean=new GroupBean();
			           bean.setCode(Integer.parseInt(f1));
			           bean.setName(f2);
			           bean.setRemarks(f3);
			           
			           finalResults.add(bean);				
					}			
			
			}
			if (newbean.getStatus().equals("Gs")){
				
				con = getSession().connection();
				st = con.createStatement();
				if (newbean.getRemarks() == "") {
					rs = st.executeQuery(DataQuery.GROUP_GS_SEARCH);
				}else{
					rs = st.executeQuery(DataQuery.GROUP_GEN_SEARCH_LIKE
							
							+newbean.getRemarks() + "%'  and status='V3' order by group_code");
				}
			
				while (rs.next()) {
					   String f1=rs.getString("group_Code");
			           String f2=rs.getString("group_Name");
			           String f3=rs.getString("remarks");

			           bean=new GroupBean();
			           bean.setCode(Integer.parseInt(f1));
			           bean.setName(f2);
			           bean.setRemarks(f3);
			           
			           finalResults.add(bean);				
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

		return finalResults;
	}
	
	
	//===================================Login=======================================
	
	public LoginBean findLoginSearch(String code) {
		log4jLogger.info("start===========findLoginSearch====="+code);
		LoginBean ob = null;
		
		
		try {
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.LOGIN_SELECT_STRING);
			Prest.setString(1, code);
			rs = Prest.executeQuery();
			

			if (rs.next()) {
				
				ob=new LoginBean();
				 ob.setCode(rs.getString("login_id"));
				 ob.setPass(rs.getString("login_password"));
				 ob.setName(rs.getString("staff_name"));
				 ob.setRights(rs.getString("user_rights"));
				 ob.setFlag(rs.getString("login_flag"));
			

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
	
	public int findLoginDelete(String code) {
		log4jLogger.info("start===========findLoginDelete=====");

	int count=0;
		try {
			
			
			 
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
	
	public int findLoginSave(LoginBean newbean) {
		log4jLogger.info("start===========findLoginSave=====");

		int count=0;
		int Gname=0;
		try {
			
			
			 
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.LOGIN_INSERT_STRING);
			Prest.setString(1, newbean.getCode());
			Prest.setString(2, newbean.getPass());
			Prest.setString(3, newbean.getName());
			Prest.setString(4, newbean.getRights());
			Prest.setString(5, newbean.getFlag());
			Prest.setString(6, "0");
			Prest.setString(7, "");
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
	public int findLoginUpdate(LoginBean newbean) {
		log4jLogger.info("start===========findLoginUpdate=====");

		int count=0;
		int Gname=0;
		try {
			
			
			 
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.LOGIN_UPDATE_STRING);
			Prest.setString(7, newbean.getCode());
			Prest.setString(1, newbean.getPass());
			Prest.setString(2, newbean.getName());
			Prest.setString(3, newbean.getRights());
			Prest.setString(4, newbean.getFlag());
			Prest.setString(5, "0");
			Prest.setString(6, "");
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
	
	
	public LoginBean findLoginSearchName(String name) {
		log4jLogger.info("start===========findGroupGenSearch=====");
		GroupBean bean=new GroupBean();
		ArrayList finalResults = new ArrayList();
		LoginBean ob=new LoginBean();
		ArrayList ser=new ArrayList ();
		String SQLString="",f3="";
		try {
			
			
		
			con = getSession().connection();
			st = con.createStatement();
			if (name == "") {
				rs = st.executeQuery(DataQuery.SEARCH_LOGIN_NAME + " order by staff_name,login_id");
			}else{
				rs = st.executeQuery(DataQuery.SEARCH_LOGIN_NAME_LIKE
						
						+name + "%'  order by staff_name,login_id");
			}
		
			while (rs.next()) {
				
				ser.add(rs.getString("login_id"));
				ser.add(rs.getString("staff_name"));
				int right=Integer.parseInt(rs.getString("user_rights"));
				
				switch(right)
				{
				case 1:
				f3="ADMIN-I";
				break;
				case 2:
				f3="ADMIN-II";
				break;
				case 3:
				f3="DATA ENTRY";
				break;
				case 4:
				f3="JOURNAL";
				break;
				case 5:
				f3="ACQUISITION";
				break;
				case 6:
				f3="COUNTER";
				break;
				case 7:
				f3="PUBLIC";
				break;
				}
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
	
	
	//-------------------------Stock----------------------------
	
	
	public StockBean findStock(String code) {
		log4jLogger.info("start===========findStock=====");
		StockBean SB=new StockBean();
		int count_mas=0,count_yes=0,count_miss=0,count_lost=0,count_withdraw=0,count_issued=0,count_vissued=0,count_binding=0,count_damaged=0,count_transfer=0;
	    int count=0;
	    
		try {
			
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.COUNT_STOCK_MAS);
		    Prest.setString(1,code);
			rs = Prest.executeQuery();
			
			if(rs.next()){
				count_mas=Integer.parseInt(rs.getString(1));
				SB.setCmas(count_mas);	
				}
			
			Prest = con.prepareStatement(DataQuery.COUNT_STOCK_YES);
			Prest.setString(1,code);
			rs = Prest.executeQuery();
			 if(rs.next()){
				count_yes=Integer.parseInt(rs.getString(1));
				SB.setCyes(count_yes);
				}

			Prest = con.prepareStatement(DataQuery.COUNT_STOCK_MISSING);   // RK Missing Book
			Prest.setString(1,code);
			rs = Prest.executeQuery();
						
			if(rs.next()){
			    count_miss=Integer.parseInt(rs.getString(1));
				SB.setCmissing(count_miss);
			} 			 
			
			Prest = con.prepareStatement(DataQuery.COUNT_STOCK_LOST);
			Prest.setString(1,code);
			rs = Prest.executeQuery();
			
			if(rs.next()){
				count_lost=Integer.parseInt(rs.getString(1));
				SB.setClost(count_lost);
			}

            Prest = con.prepareStatement(DataQuery.COUNT_STOCK_WITHDRAWN);   // RK WITHDRAWN Book
            Prest.setString(1,code);
            rs = Prest.executeQuery();
			
			if(rs.next()){
				count_withdraw=Integer.parseInt(rs.getString(1));
				SB.setCwithdrawn(count_withdraw);
			} 
			
			Prest = con.prepareStatement(DataQuery.COUNT_STOCK_ISSUED);
			Prest.setString(1,code);
			rs = Prest.executeQuery();
			
			if(rs.next()){
				count_issued=Integer.parseInt(rs.getString(1));
				SB.setCissued(count_issued);
				}
			
			Prest = con.prepareStatement(DataQuery.COUNT_STOCK_BINDING);
			Prest.setString(1,code);
			rs = Prest.executeQuery();
			
			
			if(rs.next()){
				count_binding=Integer.parseInt(rs.getString(1));
				SB.setCbinding(count_binding);
				}
			
			Prest = con.prepareStatement(DataQuery.COUNT_STOCK_DAMAGED);
			Prest.setString(1,code);
			rs = Prest.executeQuery();
			
			if(rs.next()){
				count_damaged=Integer.parseInt(rs.getString(1));
				SB.setCdamaged(count_damaged);
				}
			
			Prest = con.prepareStatement(DataQuery.COUNT_STOCK_TRANSFER);
			Prest.setString(1,code);
			rs = Prest.executeQuery();
			
		
			if(rs.next()){
				count_transfer=Integer.parseInt(rs.getString(1));
				SB.setCtransfer(count_transfer);
				}
			
			Prest = con.prepareStatement(DataQuery.COUNT_STOCK_VERIFY_ISSUED);  // RK After Stock the books were issued
			Prest.setString(1,code);
			rs = Prest.executeQuery();			
		
			if(rs.next()){
				count_vissued=Integer.parseInt(rs.getString(1));
				SB.setCverifyIssued(count_vissued);
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

		return SB;
	}
	
	public StockBean findStockAll() {
		log4jLogger.info("start===========findStock=====");
		StockBean SB=new StockBean();
		int count_mas=0,count_yes=0,count_miss=0,count_lost=0,count_withdraw=0,count_vissued=0,count_issued=0,count_binding=0,count_damaged=0,count_transfer=0;
	int count=0;
		try {
			
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.COUNT_STOCK_MAS_ALL);
		    rs = Prest.executeQuery();
			
			if(rs.next()){
				count_mas=Integer.parseInt(rs.getString(1));
				SB.setCmas(count_mas);	
				}
			
			Prest = con.prepareStatement(DataQuery.COUNT_STOCK_YES_ALL);
			rs = Prest.executeQuery();
			 if(rs.next()){
				count_yes=Integer.parseInt(rs.getString(1));
				SB.setCyes(count_yes);
				}

			Prest = con.prepareStatement(DataQuery.COUNT_STOCK_MISSING_ALL);   // RK Missing Book
			rs = Prest.executeQuery();
					
			if(rs.next()){
			    count_miss=Integer.parseInt(rs.getString(1));
				SB.setCmissing(count_miss);
			} 
			 
			Prest = con.prepareStatement(DataQuery.COUNT_STOCK_LOST_ALL);
			rs = Prest.executeQuery();
			
			if(rs.next()){
				count_lost=Integer.parseInt(rs.getString(1));
				SB.setClost(count_lost);
			}
			
			Prest = con.prepareStatement(DataQuery.COUNT_STOCK_WITHDRAWN_ALL);   // RK WITHDRAWN Book
			rs = Prest.executeQuery();
			
			if(rs.next()){
				count_withdraw=Integer.parseInt(rs.getString(1));
				SB.setCwithdrawn(count_withdraw);
			} 			
			
			Prest = con.prepareStatement(DataQuery.COUNT_STOCK_ISSUED_ALL);
			rs = Prest.executeQuery();
			
			if(rs.next()){
				count_issued=Integer.parseInt(rs.getString(1));
				SB.setCissued(count_issued);
				}
			
			Prest = con.prepareStatement(DataQuery.COUNT_STOCK_BINDING_ALL);
			rs = Prest.executeQuery();
			
			
			if(rs.next()){
				count_binding=Integer.parseInt(rs.getString(1));
				SB.setCbinding(count_binding);
				}
			
			Prest = con.prepareStatement(DataQuery.COUNT_STOCK_DAMAGED_ALL);
			rs = Prest.executeQuery();
			
			if(rs.next()){
				count_damaged=Integer.parseInt(rs.getString(1));
				SB.setCdamaged(count_damaged);
				}
			
			Prest = con.prepareStatement(DataQuery.COUNT_STOCK_TRANSFER_ALL);
			rs = Prest.executeQuery();
			if(rs.next()){
				count_transfer=Integer.parseInt(rs.getString(1));
				SB.setCtransfer(count_transfer);
				}

			Prest = con.prepareStatement(DataQuery.COUNT_STOCK_VERIFY_ISSUED_ALL);   // RK After Stock the books were issued
			rs = Prest.executeQuery();
			if(rs.next()){
				count_vissued=Integer.parseInt(rs.getString(1));
				SB.setCverifyIssued(count_vissued);
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

		return SB;
	}
	public StockBean findStockMasSave(String code) {
		log4jLogger.info("start===========findStockMasSave====="+code);
		String status="";
	    int count=0;
	    StockBean SB=null;
	    SB=new StockBean();
		try {
			
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.STOCK_SELECT_BOOK);	
			Prest.setString(1, code);
			rs=Prest.executeQuery();
			if (rs.next()) {
				status=rs.getString("availability");
			
			if((status.equals("YES")) || (status.equals("REFERENCE"))){
				
			
			
			Prest = con.prepareStatement(DataQuery.STOCK_SELECT);
			Prest.setString(1, code);
			rs=Prest.executeQuery();
			if (rs.next()) {				
				count=1;
				SB.setavailability("Already in Stock");
			
			}else{
				Prest = con.prepareStatement(DataQuery.STOCK_SAVE);
				Prest.setString(1, code);
				Prest.executeUpdate();
				
			}
			}else{
				
				SB.setavailability(status);
			}
			
			}else{
				SB.setavailability("Not found");
				
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

		return SB;
	}
	public int findStockMasDeleteAll() {
		log4jLogger.info("start===========findStockMasDeleteAll=====");

	int count=0;
		try {
			
			
			 
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.STOCK_DELETE);
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
	
	public List findStockMasDisplay(StockBean newbean) {
		log4jLogger.info("start===========findStockMasDisplay=====");
		StockBean SB=new StockBean();
	String strsql="";
	List finalResults = null;

		try {
			
			
			if(newbean.getflag().equals("rdStock")){
            	
	            if(newbean.getdoctype().equals("ALL")){
	            strsql="Select stock_view.* from stock_view,sort_book Where Exists (Select Access_No from stock_mas where Access_No= stock_view.Access_No) and stock_view.Access_No = sort_book.Access_No order by N1,N2,N3";	
	            }else{
	            strsql="Select stock_view.* from stock_view,sort_book Where Exists (Select Access_No from stock_mas where Access_No= stock_view.Access_No) and stock_view.Access_No = sort_book.Access_No and stock_view.doc_type='"+newbean.getdoctype()+"' order by N1,N2,N3";	
	            }
	            }else if(newbean.getflag().equals("rdNotVerify")){
	            	if(newbean.getdoctype().equals("ALL")){
	                    //strsql="Select stock_view_yes.* from stock_view_yes, sort_book Where stock_view_yes.Access_No = sort_book.Access_No  Order by N1,N2,N3 limit 1000";
	                    strsql="Select stock_view_yes.* from stock_view_yes, sort_book Where stock_view_yes.Access_No = sort_book.Access_No  Order by N1,N2,N3 ";
	                    }else{
	                    //strsql="Select stock_view_yes.* from stock_view_yes, sort_book Where stock_view_yes.Access_No = sort_book.Access_No and stock_view_yes.doc_type='"+newbean.getdoctype()+"' Order by N1,N2,N3 limit 1000";
	                    strsql="Select stock_view_yes.* from stock_view_yes, sort_book Where stock_view_yes.Access_No = sort_book.Access_No and stock_view_yes.doc_type='"+newbean.getdoctype()+"' Order by N1,N2,N3 ";
	                    }
	            }else if(newbean.getflag().equals("rdMissing")){
	            	if(newbean.getdoctype().equals("ALL")){
	                    strsql="Select stock_view_missing.* from stock_view_missing, sort_book Where stock_view_missing.Access_No = sort_book.Access_No  Order by N1,N2,N3";	
	                    }else{
	                    strsql="Select stock_view_missing.* from stock_view_missing, sort_book Where stock_view_missing.Access_No = sort_book.Access_No and stock_view_missing.doc_type='"+newbean.getdoctype()+"' Order by N1,N2,N3";	
	                    }
	            }else if(newbean.getflag().equals("rdLost")){
	            	if(newbean.getdoctype().equals("ALL")){
	                    strsql="Select stock_view_lost.* from stock_view_lost, sort_book Where stock_view_lost.Access_No = sort_book.Access_No  Order by N1,N2,N3";	
	                    }else{
	                    strsql="Select stock_view_lost.* from stock_view_lost, sort_book Where stock_view_lost.Access_No = sort_book.Access_No and stock_view_lost.doc_type='"+newbean.getdoctype()+"' Order by N1,N2,N3";	
	                    }
	            }else if(newbean.getflag().equals("rdWithdrawn")){
	            	if(newbean.getdoctype().equals("ALL")){
	                    strsql="Select stock_view_withdrawn.* from stock_view_withdrawn, sort_book Where stock_view_withdrawn.Access_No = sort_book.Access_No  Order by N1,N2,N3";	
	                    }else{
	                    strsql="Select stock_view_withdrawn.* from stock_view_withdrawn, sort_book Where stock_view_withdrawn.Access_No = sort_book.Access_No and stock_view_withdrawn.doc_type='"+newbean.getdoctype()+"' Order by N1,N2,N3";	
	                    }
	            }else if(newbean.getflag().equals("rdIssued")){
	            	if(newbean.getdoctype().equals("ALL")){
	                    strsql="Select stock_view_issued.* from stock_view_issued, sort_book Where stock_view_issued.Access_No = sort_book.Access_No  Order by N1,N2,N3";	
	                    }else{
	                    strsql="Select stock_view_issued.* from stock_view_issued, sort_book Where stock_view_issued.Access_No = sort_book.Access_No and stock_view_issued.doc_type='"+newbean.getdoctype()+"' Order by N1,N2,N3";	
	                    }
	            }else if(newbean.getflag().equals("rdBinding")){
	            	if(newbean.getdoctype().equals("ALL")){
	                    strsql="Select stock_view_binding.* from stock_view_binding, sort_book Where stock_view_binding.Access_No = sort_book.Access_No  Order by N1,N2,N3";	
	                    }else{
	                    strsql="Select stock_view_binding.* from stock_view_binding, sort_book Where stock_view_binding.Access_No = sort_book.Access_No and stock_view_binding.doc_type='"+newbean.getdoctype()+"' Order by N1,N2,N3";	
	                    }
	            }else if(newbean.getflag().equals("rdDamaged")){
	            	if(newbean.getdoctype().equals("ALL")){
	                    strsql="Select stock_view_damaged.* from stock_view_damaged, sort_book Where stock_view_damaged.Access_No = sort_book.Access_No  Order by N1,N2,N3";	
	                    }else{
	                    strsql="Select stock_view_damaged.* from stock_view_damaged, sort_book Where stock_view_damaged.Access_No = sort_book.Access_No and stock_view_damaged.doc_type='"+newbean.getdoctype()+"' Order by N1,N2,N3";	
	                    }
	            }else if(newbean.getflag().equals("rdTransferred")){
	            	if(newbean.getdoctype().equals("ALL")){
	                    strsql="Select stock_view_transfer.* from stock_view_transfer, sort_book Where stock_view_transfer.Access_No = sort_book.Access_No  Order by N1,N2,N3";	
	                    }else{
	                    strsql="Select stock_view_transfer.* from stock_view_transfer, sort_book Where stock_view_transfer.Access_No = sort_book.Access_No and stock_view_transfer.doc_type='"+newbean.getdoctype()+"' Order by N1,N2,N3";	
	                    }
	            }else if(newbean.getflag().equals("rdVerifyIssued")){
	            	if(newbean.getdoctype().equals("ALL")){
	                    strsql="Select stock_view_verify_issue.* from stock_view_verify_issue, sort_book Where stock_view_verify_issue.Access_No = sort_book.Access_No  Order by N1,N2,N3";	
	                    }else{
	                    strsql="Select stock_view_verify_issue.* from stock_view_verify_issue, sort_book Where stock_view_verify_issue.Access_No = sort_book.Access_No and stock_view_verify_issue.doc_type='"+newbean.getdoctype()+"' Order by N1,N2,N3";	
	                    }
	            }
			
			
			con = getSession().connection();
			st = con.createStatement();
		
				rs = st.executeQuery(strsql);			
			
				finalResults = new ArrayList();		
			
			
			while (rs.next()){
				SB=new StockBean();
				
				SB.setaccno(rs.getString("Access_No"));
				SB.settitle(rs.getString("Title"));
				SB.setauthor(rs.getString("Author"));				
				SB.setpublisher(rs.getString("publisher"));
				SB.setyear(rs.getString("Year"));
				SB.setdocument(rs.getString("Doc_Type"));
				SB.setbprice(rs.getString("BPrice"));
				SB.setavailability(rs.getString("Availability"));
				finalResults.add(SB);
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

		return finalResults;
	}
	
	public int findStockDeleteSingle(String code) {
		log4jLogger.info("start===========findStockDeleteSingle=====");

	int count=0;
		try {
			
			
			 
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.STOCK_SELECT);
			Prest.setString(1, code);
			rs=Prest.executeQuery();
		
			if(rs.next()){
				Prest = con.prepareStatement(DataQuery.STOCK_DELETE_SINGLE);
				Prest.setString(1, code);
				Prest.executeUpdate();
			}else{
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
	

	// Bulk Stock Import
	
	public int findBulkStockSave(List details)
	{		
		log4jLogger.info("start===========findBulkStockSave=====");				
	    int count=0;
	    String status="";
	    
	    try  {
	    
	    
	    	for (int i = 0; i < details.size(); i++)
			{
				BulkStockData detailsbean=(BulkStockData) details.get(i);
				
				con = getSession().connection();
				Prest = con.prepareStatement(DataQuery.STOCK_SELECT_BOOK);	
				Prest.setString(1, detailsbean.getAccessNo());
				rs=Prest.executeQuery();
				
				if (rs.next()) {
					status=rs.getString("availability");					
					
				if((status.equalsIgnoreCase("YES")) || (status.equalsIgnoreCase("REFERENCE"))){
					
					Prest = con.prepareStatement(DataQuery.STOCK_SELECT);
					Prest.setString(1, detailsbean.getAccessNo());
					rs=Prest.executeQuery();
					
					if (rs.next()) {				
						count=1;
						//SB.setavailability("Already in Stock");
						log4jLogger.info(">>>>>>> Already in Stock <<<<<<<<<");
					}else{
						Prest = con.prepareStatement(DataQuery.STOCK_SAVE);
						Prest.setString(1, detailsbean.getAccessNo());
						Prest.executeUpdate();						
					}
					}else{						
						//SB.setavailability(status);						
						log4jLogger.info(">>>>>>>>> Not Available or Issued or Missing <<<<<<<<<<<<<<<<");
					}
					
					}else{
						//SB.setavailability("Not found");
						log4jLogger.info(">>>>>>>>> Record Not found <<<<<<<<<<<<<");
					}				
			}
	    	
	    	
	    }catch(Exception e)
	    {
	    	log4jLogger.info("RK-Error:"+e);
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
	    
		return count;
	}
	
	

	// Book Data Import
	
	
	public BookDataMessage findCheckAccessNo(BookDataBean newbean)
	{
		log4jLogger.info("start===========findCheckAccessNo====="+newbean.getAccessNo());
		BookDataMessage message = new BookDataMessage();
		try  		
		{	
			int count = 0;
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.SELECT_STRING);
			Prest.setString(1, newbean.getAccessNo());
			rs=Prest.executeQuery();
		
			if(rs.next()){
				count = 1;
			}else{
				count = 0;
			}		
			
			message.setCount(count);
			
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
		
		return message;
		
	}
	
	
	public int findImportBookData(List details)
	{		
		log4jLogger.info("start ====== findImportBookData =====:"+details.size());
		int count = 0;
		CalalogingService cs = BusinessServiceFactory.INSTANCE.getCalalogingService();
		
		for (int k = 0; k < details.size(); k++)
		{
		  BookDataBean detailsbean=(BookDataBean) details.get(k);
			
		  // Start getting Code from other master
		  int pub = cs.getBookPubCode(detailsbean.getPublisher());
		  detailsbean.setPublisherCode(pub);
		  
		  int sup = cs.getBookSupplierCode(detailsbean.getSupplier());
		  detailsbean.setSuplierCode(sup);
		  
		  int sub = cs.getBookSubCode(detailsbean.getSubject());
		  detailsbean.setSubjectCode(sub);
		  
		  int dept = cs.getBookDeptCode(detailsbean.getDepartment());
		  detailsbean.setDepartmentCode(dept);
		  
		  int bcode = cs.getBookBranchCode(detailsbean.getBranch());
		  detailsbean.setBranchCode(bcode);
		  // End getting Code from other master 
		  
		  
		 // Start Save Data into Book Master Table
		  
					getSession().save(detailsbean);
					getSession().flush();
					getSession().clear();
					
					
		 // Start Save Data into Author Interface , Author Master , Sort Book Table
			try  {		
		            log4jLogger.info("start===========InterfaceSave====="+detailsbean.getAccessNo());
		            
			        con = getSession().connection();
					Prest = con.prepareStatement(DataQuery.SELECT_INTERFACE);
					Prest.setString(1, detailsbean.getAccessNo());
					rs = Prest.executeQuery();
					
					String accno="";
					
					if (rs.next()) {
						
						accno=rs.getString("access_no");
						
						con = getSession().connection();
						Prest = con.prepareStatement(DataQuery.DELETE_INTERFACE);
						Prest.setString(1, accno);
						Prest.executeUpdate();
					}	
					
					
					log4jLogger.info("start=========== AuthorMasterSave or Checking =====");
					
					con = getSession().connection();
					Prest = con.prepareStatement(DataQuery.AUTHORSQLStringCode);
					rs=Prest.executeQuery();
					int a_code=0;
					if(rs.next()){
						a_code=rs.getInt("maxno")+1;
					}
					
					StringBuffer sb = new StringBuffer();
					java.util.StringTokenizer auth=new java.util.StringTokenizer(detailsbean.getAuthor(),";");
										
					while(auth.hasMoreTokens() ){
						
					String nameAuthor=auth.nextToken();
					
					con = getSession().connection();
					Prest = con.prepareStatement(DataQuery.AUTHORSame_Name);
					Prest.setString(1,nameAuthor);
					rs=Prest.executeQuery();				
					if(rs.next()){
						sb.append(";"+rs.getInt("Author_code")+";");   // Added By Rk to get AuthorValue
					}else{
					
					con = getSession().connection();
					Prest = con.prepareStatement(DataQuery.AUTHORINSERT_STRING);
					Prest.setInt(1,a_code);
					Prest.setString(2,nameAuthor);
					Prest.setString(3,"");
					Prest.setString(4,"");
					
					Prest.executeUpdate();
					
					sb.append(";"+a_code+";");   // Added By Rk to get AuthorValue
					
					a_code++;
					}
					
					}					
					
					
					log4jLogger.info("start=========== AuthorInterfaceSave ====="+sb.toString());
					
					java.util.StringTokenizer stz=new java.util.StringTokenizer(sb.toString(),";");    // Added By Rk to get AuthorValue
					int c=1;
					
					while(stz.hasMoreTokens() ){
					int CodeAuthor=Integer.parseInt(stz.nextToken());
					
					con = getSession().connection();
					Prest = con.prepareStatement(DataQuery.INSERT_INTERFACE);
					Prest.setInt(1,CodeAuthor);
					Prest.setString(2,detailsbean.getAccessNo());
					Prest.setInt(3,c);
					Prest.setString(4,detailsbean.getRole());
					Prest.setString(5,detailsbean.getDocument());
					Prest.executeUpdate();
					c++;
					}
			
					
					log4jLogger.info("start=========== SortBookSave ====="+detailsbean.getAccessNo());

					String acc=detailsbean.getAccessNo().toUpperCase();
					int N1=0;//ascii value+length.
					int N2=0;//length.
					int N3=0;//Number only.

					char temp1[]=new char[10];
					char temp2[]=new char[10];

					char s[]=acc.toCharArray();
					for(int i=0;i<s.length;i++)
						{
							if ((s[i]>='A'||s[i]>='a') && (s[i]<='Z'||s[i]<='z'))
								{
									temp1[i]+=s[i];
								}else
								{
									temp2[i]+=s[i];
								}
						}

					String t2=String.valueOf(temp2).trim();
					int as=0;
					int i=0;
					if(i>='A'||i<='Z')
					{
					for(i='A';i<='Z';i++)
					{
						for(int j=0;j<temp1.length;j++)
							{
							if(temp1[j]==i)
								{
									as+=i;
									
								}
							}
					}
					}
					if(i>='a'||i<='z')
					{
					for(i='a';i<='z';i++)
					{
						for(int j=0;j<temp1.length;j++)
							{
							if(temp1[j]==i)
								{
									as+=i;
									
								}
							}
					}
					}
					
					N1=as+detailsbean.getAccessNo().length();
					N2=detailsbean.getAccessNo().length();
					N3=Integer.parseInt(t2);
					
					try {
						Prest = con.prepareStatement(DataQuery.INSERT_DELETE);
						Prest.setString(1, detailsbean.getAccessNo());
						Prest.executeUpdate();
						Prest = con.prepareStatement(DataQuery.INSERT_SORT);
						Prest.setString(1, detailsbean.getAccessNo());
						Prest.setInt(2, N1);
						Prest.setInt(3, N2);
						Prest.setInt(4, N3);
						Prest.executeUpdate();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						
					}
				
					
			}catch(Exception e)
			{
				log4jLogger.info("AutoLibError:SavingData:"+e);				
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

					
			count++;
	    }
		
		log4jLogger.info("End ====== findImportBookData ========"+count);
		
		return count;
		
	}
	
	// BackVolume Data Import
	
	
				public BvolumeDataMessage findCheckAccessNo(BvolumeDataBean newbean)
				{
					log4jLogger.info("start===========findCheckAccessNo====="+newbean.getAccessNo());
					BvolumeDataMessage message = new BvolumeDataMessage();
					try  		
					{	
						int count = 0;
						con = getSession().connection();
						Prest = con.prepareStatement(DataQuery.SELECT_STRING);
						Prest.setString(1, newbean.getAccessNo());
						rs=Prest.executeQuery();
					
						if(rs.next()){
							count = 1;
						}else{
							count = 0;
						}		
						
						message.setCount(count);
						
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
					
					return message;
					
				}
				
				
				public int findImportBvolumeData(List details)
				{		
					log4jLogger.info("start ====== findImportBvolumeData =====:"+details.size());
					int count = 0;
					CalalogingService cs = BusinessServiceFactory.INSTANCE.getCalalogingService();
					
					for (int k = 0; k < details.size(); k++)
					{
					  BvolumeDataBean detailsbean=(BvolumeDataBean) details.get(k);
						
					  // Start getting Code from other master
					  
					  
					  		  
					  int bcode =2;
					  
					  int dept = 1;
					  
					  int sub = 1;
					  
					  int budget = 1;
					  
					  int pub =1;
					  
					  int sup = 2;
					  
					  
					  // End getting Code from other master 
					  
					  
					 // Start Save Data into Book Master Table
					  
								getSession().save(detailsbean);
								getSession().flush();
								getSession().clear();
								
								
					 // Start Save Data into Author Interface , Author Master , Sort Book Table
						try  {		
					            log4jLogger.info("start===========InterfaceSave====="+detailsbean.getAccessNo());
					            
						        con = getSession().connection();
								Prest = con.prepareStatement(DataQuery.SELECT_INTERFACE);
								Prest.setString(1, detailsbean.getAccessNo());
								rs = Prest.executeQuery();
								
								String accno="";
								
								if (rs.next()) {
									
									accno=rs.getString("access_no");
									
									con = getSession().connection();
									Prest = con.prepareStatement(DataQuery.DELETE_INTERFACE);
									Prest.setString(1, accno);
									Prest.executeUpdate();
								}	
								
								
								log4jLogger.info("start=========== AuthorMasterSave or Checking =====");
								
								con = getSession().connection();
								Prest = con.prepareStatement(DataQuery.AUTHORSQLStringCode);
								rs=Prest.executeQuery();
								int a_code=0;
								if(rs.next()){
									a_code=rs.getInt("maxno")+1;
								}
								
								
								StringBuffer sb = new StringBuffer();
//								java.util.StringTokenizer auth=new java.util.StringTokenizer(detailsbean.getAuthor(),";");
//								//String AutCode="";
//								
//								while(auth.hasMoreTokens() ){
									
								String nameAuthor="NIL";
								
								con = getSession().connection();
								Prest = con.prepareStatement(DataQuery.AUTHORSame_Name);
								Prest.setString(1,nameAuthor);
								rs=Prest.executeQuery();				
								if(rs.next()){
									//AutCode = AutCode + ";" + rs.getInt("Author_code");   // Added By Rk to get AuthorValue
									sb.append(";"+rs.getInt("Author_code")+";");
								}else{
								
								con = getSession().connection();
								Prest = con.prepareStatement(DataQuery.AUTHORINSERT_STRING);
								Prest.setInt(1,1);
								Prest.setString(2,"NIL");
								Prest.setString(3,"");
								Prest.setString(4,"");
								
								Prest.executeUpdate();
								
								//AutCode = AutCode + ";" + a_code;   // Added By Rk to get AuthorValue
								sb.append(";"+1+";");
								
								//a_code++;
								}
								
													
								
								
								log4jLogger.info("start=========== AuthorInterfaceSave ====="+sb.toString());
								
								java.util.StringTokenizer stz=new java.util.StringTokenizer(sb.toString(),";");    // Added By Rk to get AuthorValue
								int c=1;
								
								while(stz.hasMoreTokens() ){
								int CodeAuthor=Integer.parseInt(stz.nextToken());
								
								con = getSession().connection();
								Prest = con.prepareStatement(DataQuery.INSERT_INTERFACE);
								Prest.setInt(1,1);
								Prest.setString(2,detailsbean.getAccessNo());
								Prest.setInt(3,c);
								Prest.setString(4,detailsbean.getRole());
								Prest.setString(5,"BACK VOLUME");
								Prest.executeUpdate();
								c++;
								}
						
								
								log4jLogger.info("start=========== SortBookSave ====="+detailsbean.getAccessNo());

								String acc=detailsbean.getAccessNo().toUpperCase();
								int N1=0;//ascii value+length.
								int N2=0;//length.
								int N3=0;//Number only.

								char temp1[]=new char[10];
								char temp2[]=new char[10];

								char s[]=acc.toCharArray();
								for(int i=0;i<s.length;i++)
									{
										if ((s[i]>='A'||s[i]>='a') && (s[i]<='Z'||s[i]<='z'))
											{
												temp1[i]+=s[i];
											}else
											{
												temp2[i]+=s[i];
											}
									}

								String t2=String.valueOf(temp2).trim();
								int as=0;
								int i=0;
								if(i>='A'||i<='Z')
								{
								for(i='A';i<='Z';i++)
								{
									for(int j=0;j<temp1.length;j++)
										{
										if(temp1[j]==i)
											{
												as+=i;
												
											}
										}
								}
								}
								if(i>='a'||i<='z')
								{
								for(i='a';i<='z';i++)
								{
									for(int j=0;j<temp1.length;j++)
										{
										if(temp1[j]==i)
											{
												as+=i;
												
											}
										}
								}
								}
								
								N1=as+detailsbean.getAccessNo().length();
								N2=detailsbean.getAccessNo().length();
								N3=Integer.parseInt(t2);
								
								try {
									Prest = con.prepareStatement(DataQuery.INSERT_DELETE);
									Prest.setString(1, detailsbean.getAccessNo());
									Prest.executeUpdate();
									Prest = con.prepareStatement(DataQuery.INSERT_SORT);
									Prest.setString(1, detailsbean.getAccessNo());
									Prest.setInt(2, N1);
									Prest.setInt(3, N2);
									Prest.setInt(4, N3);
									Prest.executeUpdate();
								} catch (SQLException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
									
								}
							
								
						}catch(Exception e)
						{
							
							log4jLogger.info("AutoLibError:SavingData:"+e);	
							e.printStackTrace();
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

								
						count++;
				    }
					
					log4jLogger.info("End ====== findImportBvolumeData ========"+count);
					
					return count;
					
				}
	
	
	//QB Data Import
	
		public QBDataMessage findCheckQBCode(QBDataBean newbean)
		{
			log4jLogger.info("start===========findCheckQBcode====="+newbean.getQbCode());
			QBDataMessage message = new QBDataMessage();
			try  		
			{	
				int count = 0;
				con = getSession().connection();
				Prest = con.prepareStatement(DataQuery.SELECT_QBSTRING);
				Prest.setString(1, newbean.getQbCode());
				rs=Prest.executeQuery();
			System.out.println("======Qbcode======"+rs);
				if(rs.next()){
					count = 1;
				}else{
					count = 0;
				}		
				
				message.setCount(count);
				
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
			
			return message;
			
		}
		
		
		public int findImportQBData(List details)
		{		
			log4jLogger.info("start ====== findImportQBData =====:"+details.size());
			int count = 0;
			CalalogingService cs = BusinessServiceFactory.INSTANCE.getCalalogingService();
			
			for (int k = 0; k < details.size(); k++)
			{
				QBDataBean detailsbean=(QBDataBean) details.get(k);
				
	// Start getting Code from other master
			 
			  int course =getCourseCode(detailsbean.getCourse());
			  detailsbean.setCourseCode(course);
			  
			  getQbSubjectCode(detailsbean.getSubjectCode(),detailsbean.getSubName());//for insert new subject_codeand sub_name if not exist
			  
			  int dept = cs.getBookDeptCode(detailsbean.getDepartment());
			  detailsbean.setDepartmentCode(dept);
			 
	// End getting Code from other master 
			  System.out.println("+++++++++++"+detailsbean.toString());
			  
			  // Start Save Data into EBook Master Table
			  
						getSession().save(detailsbean);
						getSession().flush();
						getSession().clear();
						
					
				count++;
		    }
			
			log4jLogger.info("End ====== findImportQBData ========"+count);
			
			return count;
			
		}
		
		public int getQbSubjectCode(String qbSubjectCode,String qbSubjectName)
		{
			int subjectCode=0;
			
			try{
				
				con = getSession().connection();
				Prest = con.prepareStatement(DataQuery.QBSubject_Name);
				Prest.setString(1,qbSubjectCode);
				Prest.setString(2,qbSubjectName);
				rs=Prest.executeQuery();
				
				if(rs.next())
				{
					System.out.println(""+rs.getString("cnt"));
					if(rs.getInt("cnt") > 0)
					{
						Prest = con.prepareStatement(DataQuery.Select_QbCode);
						Prest.setString(1,qbSubjectCode);
						Prest.setString(2,qbSubjectName);
						rs1=Prest.executeQuery();
						if(rs1.next())
						{
							return rs1.getInt("qb_code");
						}
					}
					else
					{
						
						Prest = con.prepareStatement(DataQuery.QBSUBJECTSQLStringCode);
						rs = Prest.executeQuery();
						if (rs.next()) {
							subjectCode=rs.getInt("maxno") + 1;	
							Prest = con.prepareStatement(DataQuery.Qbsubjectmas_Insert);
							Prest.setInt(1, subjectCode);
							Prest.setString(2, qbSubjectCode);
							Prest.setString(3, qbSubjectName);
							Prest.setString(4, "BULKINSERT");
							Prest.executeUpdate();

						}
					}
				}
			}catch(Exception e)
			{
				e.printStackTrace();
			}
			return subjectCode;
		}
	
	// EBook Data Import
	
	
	public EBookDataMessage findCheckEBookAccessNo(EBookDataBean newbean)
	{
		log4jLogger.info("start===========findCheckEBookAccessNo====="+newbean.getAccessNo());
		EBookDataMessage message = new EBookDataMessage();
		try  		
		{	
			int count = 0;
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.SELECT_EBOOKSTRING);
			Prest.setString(1, newbean.getAccessNo());
			rs=Prest.executeQuery();
		
			if(rs.next()){
				count = 1;
			}else{
				count = 0;
			}		
			
			message.setCount(count);
			
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
		
		return message;
		
	}
	
	
	public int findImportEBookData(List details)
	{		
		log4jLogger.info("start ====== findImportEBookData =====:"+details.size());
		int count = 0;
		CalalogingService cs = BusinessServiceFactory.INSTANCE.getCalalogingService();
		
		for (int k = 0; k < details.size(); k++)
		{
		  EBookDataBean detailsbean=(EBookDataBean) details.get(k);
			
// Start getting Code from other master
		  int pub = cs.getBookPubCode(detailsbean.getPubName());
		  detailsbean.setPubCode(pub);
		
		  int sub = cs.getBookSubCode(detailsbean.getSubName());
		  detailsbean.setSubCode(sub);
		  
		  int dept = cs.getBookDeptCode(detailsbean.getDeptName());
		  detailsbean.setDeptCode(dept);
		  
		  int sup = cs.getBookSupplierCode(detailsbean.getSupName());
		  detailsbean.setSupCode(sup);
		  
		  /*int bcode = cs.getBookBranchCode(detailsbean.getBranchName());
		  detailsbean.setBranchCode(bcode);*/
// End getting Code from other master 
		  
		  
		  // Start Save Data into EBook Master Table
		  
					getSession().save(detailsbean);
					getSession().flush();
					getSession().clear();
					
				
			count++;
	    }
		
		log4jLogger.info("End ====== findImportEBookData ========"+count);
		
		return count;
		
	}
	
	//::::::::::::::::::: Data Changes Import ::::::::: @ Vicknesh
	
	
		public ChangesDataMessage findCheckAccessNo(ChangesDataBean newbean)
		{
			log4jLogger.info("start===========findCheckAccessNo====="+newbean.getAccessNo());
			ChangesDataMessage message = new ChangesDataMessage();
			try  		
			{	
				int count = 0;
				con = getSession().connection();
				Prest = con.prepareStatement(DataQuery.SELECT_STRING);
				Prest.setString(1, newbean.getAccessNo());
				rs=Prest.executeQuery();
			
				if(rs.next()){
					count = 1;
				}else{
					count = 0;
				}		
				
				message.setCount(count);
				
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
			
			return message;
			
		}
		
		public int findImportChangesData(List details)
		{		
			log4jLogger.info("start ====== findImportChangesData =====:"+details.size());
			int count = 0;
			String filterQuery="";
			CalalogingService cs = BusinessServiceFactory.INSTANCE.getCalalogingService();
			
			for (int k = 0; k < details.size(); k++)
			{
			  ChangesDataBean detailsbean=(ChangesDataBean) details.get(k);
				
			  // Start getting Code from other master
			  
			  /*int bcode = cs.getBookBranchCode(detailsbean.getBranch());
			  detailsbean.setBranchCode(bcode);*/
			  int bcode=2;
			  
			  if(detailsbean.getCallNo()!="")
			  {
				  filterQuery= filterQuery + " ,call_no = '" + detailsbean.getCallNo() +"'";
			  }
			  
			  if(detailsbean.getTitle()!="")
			  {
				  filterQuery= filterQuery + " ,title = '" + detailsbean.getTitle() +"'";
			  }
			  
			  if(detailsbean.getAuthor()!="")
			  {
				  filterQuery= filterQuery + " ,author_name = '" + detailsbean.getAuthor() +"'";
			  }
			  
			  if(detailsbean.getLocation()!="")
			  {
				  filterQuery= filterQuery + " ,Location = '" + detailsbean.getLocation() +"'";
			  }
			  
			  if(detailsbean.getEdition()!="")
			  {
				  filterQuery= filterQuery + " ,edition = '" + detailsbean.getEdition() +"'";
			  }
			  
			  if(detailsbean.getVolumeNo()!="")
			  {
				  filterQuery= filterQuery + " ,volno = '" + detailsbean.getVolumeNo() +"'";
			  }
			  
			  if(detailsbean.getIsbn()!="")
			  {
				  filterQuery= filterQuery + " ,isbn = '" + detailsbean.getIsbn() +"'";
			  }
			  
			  if(detailsbean.getPubyear()!="")
			  {
				  filterQuery= filterQuery + " ,year_pub = '" + detailsbean.getPubyear() +"'";
			  }
			  
			  if(detailsbean.getPrice()!=0.0)
			  {
				  filterQuery= filterQuery + " ,bprice = '" + detailsbean.getPrice() +"'";
			  }
			  
			  if(detailsbean.getNetprice()!=0.0)
			  {
				  filterQuery= filterQuery + " ,accepted_price = '" + detailsbean.getNetprice() +"'";
			  }
			  
			  if(detailsbean.getDiscount()!=0.0)
			  {
				  filterQuery= filterQuery + " ,discount = '" + detailsbean.getDiscount() +"'";
			  }
			  
			  if(detailsbean.getInvoiceno()!="")
			  {
				  filterQuery= filterQuery + " ,invoice_no = '" + detailsbean.getInvoiceno() +"'";
			  }
			  
			  if(detailsbean.getInvoiceDate()!="")
			  {
				  filterQuery= filterQuery + " ,invoice_date = '" + detailsbean.getInvoiceDate() +"'";
			  }
			  
			  if(detailsbean.getReceiveDate()!="")
			  {
				  filterQuery= filterQuery + " ,received_date = '" + detailsbean.getReceiveDate() +"'";
			  }
			  
			  if(detailsbean.getPages()!="")
			  {
				  filterQuery= filterQuery + " ,pages = '" + detailsbean.getPages() +"'";
			  }
			  
			  if(detailsbean.getDocument()!="")
			  {
				  filterQuery= filterQuery + " ,document = '" + detailsbean.getDocument() +"'";
			  }
			  
			  if(detailsbean.getLanguage()!="")
			  {
				  filterQuery= filterQuery + " ,Language = '" + detailsbean.getLanguage() +"'";
			  }
			  
			  if(detailsbean.getAvailability()!="")
			  {
				  filterQuery= filterQuery + " ,availability = '" + detailsbean.getAvailability() +"'";
			  }
			  
			  if(detailsbean.getPurchaseType()!="")
			  {
				  filterQuery= filterQuery + " ,gift_purchase = '" + detailsbean.getPurchaseType() +"'";
			  }
			  
			  if(detailsbean.getKeywords()!="")
			  {
				  filterQuery= filterQuery + " ,keywords = '" + detailsbean.getKeywords() +"'";
			  }
			  
			  if(detailsbean.getBcost()!=0.0)
			  {
				  filterQuery= filterQuery + " ,bcost = '" + detailsbean.getBcost() +"'";
			  }
			  
			  if(detailsbean.getCurrency()!="")
			  {
				  filterQuery= filterQuery + " ,bCurrency = '" + detailsbean.getCurrency() +"'";
			  }
			  
			  if(detailsbean.getNotes()!="")
			  {
				  filterQuery= filterQuery + " ,biblio = '" + detailsbean.getNotes() +"'";
			  }
			  
			  if(detailsbean.getStateRes()!="")
			  {
				  filterQuery= filterQuery + " ,sres = '" + detailsbean.getStateRes() +"'";
			  }
			  
			  if(detailsbean.getAddfield1()!="")
			  {
				  filterQuery= filterQuery + " ,add_field1 = '" + detailsbean.getAddfield1() +"'";
			  }
			  
			  if(detailsbean.getAddfield2()!="")
			  {
				  filterQuery= filterQuery + " ,add_field2 = '" + detailsbean.getAddfield2() +"'";
			  }
			  
			  if(detailsbean.getAddfield3()!="")
			  {
				  filterQuery= filterQuery + " ,add_field3 = '" + detailsbean.getAddfield3() +"'";
			  }
			  
			  if(detailsbean.getScript()!="")
			  {
				  filterQuery= filterQuery + " ,script = '" + detailsbean.getScript() +"'";
			  }
			  
			  if(detailsbean.getBudget()!="")
			  {
				  int budget = getBudgetCode(detailsbean.getBudget());
				  detailsbean.setBudgetCode(budget);
				  filterQuery= filterQuery + " ,budg_code = '" + budget +"'";
			  }
			  
			  if(detailsbean.getPublisher()!="")
			  {
				  int pub = cs.getBookPubCode(detailsbean.getPublisher());
				  detailsbean.setPublisherCode(pub);
				  
				  filterQuery= filterQuery + " ,pub_code = '" + pub +"'";
			  }
			  
			  if(detailsbean.getSupplier()!="")
			  {
				  int sup = cs.getBookSupplierCode(detailsbean.getSupplier());
				  detailsbean.setSuplierCode(sup);
				  
				  filterQuery= filterQuery + " ,sup_code =  '" + sup +"'";
			  }
			  
			  if(detailsbean.getSubject()!="")
			  {
				  int sub = cs.getBookSubCode(detailsbean.getSubject());
				  detailsbean.setSubjectCode(sub);
				  
				  filterQuery= filterQuery + " ,sub_code =  '" + sub +"'";
						  }
			  
			  if(detailsbean.getDepartment()!="")
			  {
				  int dept = cs.getBookDeptCode(detailsbean.getDepartment());
				  detailsbean.setDepartmentCode(dept);
				  
				  filterQuery= filterQuery + " ,dept_code = '" + dept +"'";
			  }
			  filterQuery=filterQuery.substring(2);
			  System.out.println("::::FilterQuery:::"+filterQuery);	  		  
			  
				try  {		
					 con = getSession().connection();
					 Prest = con.prepareStatement(DataQuery.Update_Query +filterQuery + "  where access_no = ?");
					 Prest.setString(1, detailsbean.getAccessNo());
					 Prest.executeUpdate();
					 filterQuery="";
					 
					 log4jLogger.info("start=========== AuthorMasterSave or Checking =====");
						
						con = getSession().connection();
						Prest = con.prepareStatement(DataQuery.AUTHORSQLStringCode);
						rs=Prest.executeQuery();
						int a_code=0;
						if(rs.next()){
							a_code=rs.getInt("maxno")+1;
						}
						
						
						StringBuffer sb = new StringBuffer();
						java.util.StringTokenizer auth=new java.util.StringTokenizer(detailsbean.getAuthor(),";");
						//String AutCode="";
						
						while(auth.hasMoreTokens() ){
							
						String nameAuthor=auth.nextToken();
						
						con = getSession().connection();
						Prest = con.prepareStatement(DataQuery.AUTHORSame_Name);
						Prest.setString(1,nameAuthor);
						rs=Prest.executeQuery();				
						if(rs.next()){
							//AutCode = AutCode + ";" + rs.getInt("Author_code");   // Added By Rk to get AuthorValue
							sb.append(";"+rs.getInt("Author_code")+";");
						}else{
						
						con = getSession().connection();
						Prest = con.prepareStatement(DataQuery.AUTHORINSERT_STRING);
						Prest.setInt(1,a_code);
						Prest.setString(2,nameAuthor);
						Prest.setString(3,"");
						Prest.setString(4,"");
						
						Prest.executeUpdate();
						
						//AutCode = AutCode + ";" + a_code;   // Added By Rk to get AuthorValue
						sb.append(";"+a_code+";");
						
						a_code++;
						}
						
						}	
		
				}catch(Exception e)
				{
					log4jLogger.info("AutoLibError:SavingData:"+e);				
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
			
				count++;
		    }
			
			log4jLogger.info("End ====== findImportChangesData ========"+count);
			
			return count;
			
		}
	
	
	
	// ---------------EResource Import--------------------

	public EResourceDataMessage findCheckcode(EResourceBean newbean)
	{
		log4jLogger.info("start===========findCheckcode====="+newbean.getEcode());
		EResourceDataMessage message = new EResourceDataMessage();
		try  		
		{	
			int count = 0;
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.EResource_code);
			Prest.setLong(1, newbean.getEcode());
			rs=Prest.executeQuery();
		
			if(rs.next()){
				count = 1;
			}else{
				count = 0;
			}		
			
			message.setCount(count);
			
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
		
		return message;
		
	}
	
	
	
	public int findImportEResourceData(List details)
	{		
		log4jLogger.info("start ====== findImportEResourceData =====:"+details.size());
		int count = 0;
		CalalogingService cs = BusinessServiceFactory.INSTANCE.getCalalogingService();
		
		for (int k = 0; k < details.size(); k++)
		{
		  EResourceBean detailsbean=(EResourceBean) details.get(k);
			
		 		  
		 // Start Save Data into Web_mas Table
		  
					getSession().save(detailsbean);
					getSession().flush();
					getSession().clear();
					
					
			}	
			count++;
	    
		
		log4jLogger.info("End ====== findImportEResourceData ========"+count);
		
		return count;
		
	}
	

	// Member Data Import
	
	
	public MemberDataMessage findCheckMemberCode(MemberDataBean newbean)
	{
		log4jLogger.info("start===========findCheckMemberCode====="+newbean.getMemberCode());
		MemberDataMessage message = new MemberDataMessage();
		try  		
		{	
			int count = 0;
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.SELECT_MEMBER_MAS);
			Prest.setString(1, newbean.getMemberCode());
			rs=Prest.executeQuery();
		
			if(rs.next()){
				count = 1;
			}else{
				count = 0;
			}		
			
			message.setCount(count);
			
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
		
		return message;		
	}
	
	
	public int getDesignationCode(String desigName)
	{
		int desig=0;	

		try {			
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.MEMBERDESIG_CODE);
			Prest.setString(1, desigName);
			
			rs = Prest.executeQuery();
			
			if (rs.next()) {
				desig=rs.getInt("desig_code");
			}
			
			else{
				con = getSession().connection();
				Prest = con.prepareStatement(DataQuery.DesigSQLStringCode);
				rs = Prest.executeQuery();
				if (rs.next()) {
					desig = rs.getInt("maxno") + 1;
					
					con = getSession().connection();
					Prest = con.prepareStatement(DataQuery.DesigINSERT_STRING);
					Prest.setString(2, desigName);
					Prest.setString(3, "");
					Prest.setInt(1, desig);
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
		return desig;		
	}
	
	public int getGroupCode(String groupName)
	{		
		int groupCode = 0;		

		try {		
					
		con = getSession().connection();
		Prest = con.prepareStatement(DataQuery.MEMBERGROUP_CODE);
		Prest.setString(1, groupName);
		
		rs = Prest.executeQuery();			 

		  if (rs.next())
		  {
			  groupCode = rs.getInt("group_code"); 
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
	
	return groupCode;	
	}	
	
	public int getBudgetCode(String budget)
	{		
		int budgetCode = 0;		
		String strsql="";
		try {		
		
//			if(branchID > 0)
//			{
//				strsql = " and branch_code=? ";
//			}
				
		con = getSession().connection();
		Prest = con.prepareStatement(DataQuery.BUDGET_SEARCHNEW);
		Prest.setString(1, budget);
//		if(branchID > 0)
//		{
//		 Prest.setInt(2, branchID);
//		}
		rs = Prest.executeQuery(); 

		  if (rs.next())
		  {
			  budgetCode = rs.getInt("bud_code"); 
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

	return budgetCode;	
	}
		
	
	
	public int getCourseCode(String courseName)
	{		
		int courseCode = 0;		

		try {
		String part1 ="",part2 ="";
			
		String[] parts = courseName.split("-");
		if(parts.length > 1)
		{
		  part1 = parts[0].toString().trim(); // 004
		  part2 = parts[1].toString().trim(); // 034556
		}else {
		  part1 = "NIL";
		  part2 = "NIL";
		}
			
		con = getSession().connection();
		Prest = con.prepareStatement(DataQuery.MEMBERCOURSE_CODE);	
		Prest.setString(1, part1);
		Prest.setString(2, part2);

		rs = Prest.executeQuery();			 

		  if (rs.next())
		  {
			  courseCode = rs.getInt("course_code"); 
		  }else{
				con = getSession().connection();
				Prest = con.prepareStatement(DataQuery.COURSESQLStringCode);
				rs = Prest.executeQuery();
				if (rs.next()) {
					courseCode = rs.getInt("maxno") + 1;
					
					con = getSession().connection();
					Prest = con.prepareStatement(DataQuery.COURSEINSERT_STRING);
					Prest.setString(2, part1);
					Prest.setString(3, part2);
					Prest.setInt(4, 0);
					Prest.setString(5, "Day");
					Prest.setString(6, "");
					Prest.setInt(1, courseCode);
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
	
	return courseCode;	
	}
	
	
	public int findImportMemberData(List details)
	{		
		log4jLogger.info("start ====== findImportMemberData =====:"+details.size());
		int count = 0;
		CalalogingService cs = BusinessServiceFactory.INSTANCE.getCalalogingService();
		
		for (int k = 0; k < details.size(); k++)
		{			
	     try  {
	    	 
		  MemberDataBean detailsbean=(MemberDataBean) details.get(k);
			
		  // Start getting Code from other master	  
		  
		  int dept = cs.getBookDeptCode(detailsbean.getDepartment());
		  detailsbean.setDepartmentCode(dept);

		  int desig = getDesignationCode(detailsbean.getDesignation());
		  detailsbean.setDesigCode(desig);		   
			
		  int course = getCourseCode(detailsbean.getCourse());
		  detailsbean.setCourseCode(course);
		  
		  
	     	con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.MEMBERINSERT_STRING);
			Prest.setString(1, detailsbean.getMemberName());
			Prest.setString(2, (detailsbean.getBirthDate()));
			Prest.setString(3, (detailsbean.getEnrollDate()));		
			Prest.setString(4, (detailsbean.getExpiryDate()));
			Prest.setDouble(5, detailsbean.getDepositAmt());
			Prest.setInt(6, detailsbean.getDesigCode());
			Prest.setString(7, detailsbean.getSex());
			Prest.setString(8, detailsbean.getAddress());			
			Prest.setString(9, "");
			Prest.setString(10, detailsbean.getCity());
			Prest.setString(11, detailsbean.getState());
			Prest.setString(12, detailsbean.getPincode());
			Prest.setString(13, detailsbean.getPhoneNo());
			Prest.setString(14, detailsbean.getEmail());
			Prest.setInt(15, detailsbean.getDepartmentCode());			
			Prest.setInt(16, detailsbean.getCourseCode());		
			Prest.setInt(17, detailsbean.getGroupCode());
			Prest.setString(18, detailsbean.getRemarks());
			Prest.setString(19, detailsbean.getProfile());	
			//Prest.setString(20, newBean.getPhoto());
			//Prest.setBinaryStream(20, (InputStream)fis, (int)(image.length()));
			//Prest.setBinaryStream(20,fin, (int)(imgfile.length())); BY RK
			Prest.setString(20, detailsbean.getSLock());
			Prest.setString(21, detailsbean.getCYear());
			Prest.setString(22, detailsbean.getMemberCode());
			
			count = Prest.executeUpdate();
			
			
			con = getSession().connection();   
			log4jLogger.info("================loginsave=====================");
			Prest = con.prepareStatement(DataQuery.LOGIN_INSERT_STRING);
			Prest.setString(1, detailsbean.getMemberCode());
			Prest.setString(2, detailsbean.getMemberCode());
			Prest.setString(3, detailsbean.getMemberName());
			Prest.setString(4, "7");
			Prest.setString(5, "YES");
			Prest.setInt(6, 0);         // For Titan
			Prest.setString(7, "");
			Prest.executeUpdate();
		  // End getting Code from other master 
			
			
		  /**
		 // Start Save Data into Member Master Table
		  
					getSession().save(detailsbean);
					getSession().flush();
					getSession().clear();
					
					*/
							
					
			}catch(Exception e)
			{
				log4jLogger.info("AutoLibError:SavingData:"+e);	
				e.printStackTrace();
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

					
			count++;
	    }
		
		log4jLogger.info("End ====== findImportMemberData ========"+count);
		
		return count;
		
	}
	
	
	
	
	
//  ---------------------- Transaction Master ----------	
	
	public TransMasBean findTransCodeMax()
	{	
	 log4jLogger.info("start===========findTransCodeMax=====");

	 TransMasBean transBean = null;
	 try {
		 
		 con = getSession().connection();	
		 Prest = con.prepareStatement(DataQuery.TransMasMaxCode);
		 rs = Prest.executeQuery();
		
		if (rs.next()) {
			
			transBean = new TransMasBean();
			transBean.setTcode(rs.getInt("maxno") + 1);			
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

	return transBean;
	
	}
	
  
	
	public TransMasBean findTransMasSave(TransMasBean transBean)
	{
		TransMasBean transDetails = null;
		SQLQuery sql=null;
		String query="";		
		
	try {	
		log4jLogger.info("start===========findTransMasSave=====");
		
		if(TransMasSaveCheck(transBean))
    	{
		  getSession().save(transBean);
		  getSession().flush();
    	}
		else  {			
			
			query = getSession().getNamedQuery("TransMasHeadSelectQuery").getQueryString();			
			sql = getSession().createSQLQuery(query);
			
			sql.setParameter("transHead", transBean.getThead());
			List list = sql.list();		
			
			if(list!=null && list.size() > 0)
			{	
					Object[] obj = (Object[]) list.get(0);				
					transDetails = new TransMasBean();
					
					transDetails.setTcode(Integer.parseInt(obj[0].toString()));
					transDetails.setThead(obj[1].toString());
					transDetails.setTamount(transBean.getTamount());
					transDetails.setTremarks(transBean.getTremarks());
					transDetails.setTaddfield1(transBean.getTaddfield1());
					
				/**	transDetails.setTamount(obj[2].toString());
					transDetails.setTremarks(obj[3].toString());
					transDetails.setTaddfield1(obj[4].toString());  */
				
			}else  {
				log4jLogger.info("Else =========== Save With Automatic New Code ===== TransMaster");
				transDetails = new TransMasBean();
				transDetails=findTransCodeMax();
				
				transBean.setTcode(transDetails.getTcode());

				getSession().save(transBean);
				getSession().flush();
				
				transDetails=null;
			}
			
			//return transDetails;			
		}
		
	}catch(Exception e)
	{    		
		e.printStackTrace();
	}
	
	 return transDetails;
		
	}
	
	
	 public boolean TransMasSaveCheck(TransMasBean newbean)
	 {	    	
	   	boolean check=false;
	    	
	   	if (newbean.getThead() != null)
		{
	    	Criteria crit = getSession().createCriteria(TransMasBean.class);
			//crit.add(Restrictions.eq("thead", newbean.getThead()));
	    	Criterion Thead = Restrictions.eq("thead", newbean.getThead());
	    	Criterion Tcode = Restrictions.eq("tcode", newbean.getTcode());
	    	
	    	LogicalExpression orExp = Restrictions.or(Thead, Tcode);
	    	crit.add(orExp);
			
			List list = crit.list();
			if (list == null || list.size() == 0)
			{
				check = true;
			}    		
		}
	    	
	    return check;
	 }
	
	 public int findTransMasUpdate(TransMasBean transBean)
	 {
		 log4jLogger.info("start===========findTransMasUpdate=====");
		 
		 getSession().saveOrUpdate(transBean);
		 getSession().flush();		 
		 
		 return 0;		 
	 }
	 
	 
	 
	 
	 
	public TransMasBean findTransMasSearch(int code) 
	{
		log4jLogger.info("start===========findTransMasSearch====="+code);
		SQLQuery sql=null;
		String query="";
		
		query = getSession().getNamedQuery("TransMasSelectQuery").getQueryString();
		sql = getSession().createSQLQuery(query);
		sql.setParameter("transCode",code);
		
		List list = sql.list();		
		
		//List<Object> finalResults = null;
		TransMasBean transDetails = null;
		
		if(list!=null)
		{
			//finalResults = new ArrayList<Object>();			
			//for(int i=0; i < list.size(); i++)
			if(list.size() > 0)
			{
				Object[] obj = (Object[]) list.get(0);				
				transDetails = new TransMasBean();
				
				transDetails.setTcode(Integer.parseInt(obj[0].toString()));
				transDetails.setThead(obj[1].toString());
				transDetails.setTamount(obj[2].toString());
				transDetails.setTremarks(obj[3].toString());
				transDetails.setTaddfield1(obj[4].toString());
				
				//finalResults.add(transDetails);				
			}		
		}
		
		return transDetails;
		
	}
	
	
	public int findTransMasDelete(int code) 
	{
		log4jLogger.info("start===========findTransMasDelete====="+code);

		SQLQuery sql=null;
		String query="";
		
		query = getSession().getNamedQuery("TransMasDeleteQuery").getQueryString();
		sql = getSession().createSQLQuery(query);
		sql.setParameter("transCode",code);	
		
		int count= sql.executeUpdate();
		
		return count;		
	}
	
	
	
//  ---------------------- Miscellaneous & Trans_Mas Master ----------	
	
	public MiscellaneousBean findMiscellaneousMax()
	{		
		log4jLogger.info("start===========findMiscellaneousMax=====");
		
		MiscellaneousBean transBean = null;
		 try {
			 
			 con = getSession().connection();	
			 Prest = con.prepareStatement(DataQuery.MiscellaneousMaxCode);
			 rs = Prest.executeQuery();			
			 
			if (rs.next()) {				
				transBean = new MiscellaneousBean();
				transBean.setMrno(rs.getInt("maxno") + 1);			
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
		
		return transBean;
	}
	
	public List findMiscellaneousTHead()
	{
		log4jLogger.info("start===========findMiscellaneousTHead=====");
		List<Object> finalResults = null;
		MiscellaneousBean transBean = null;
		
		try {			
		
		con = getSession().connection();	
		Prest = con.prepareStatement(DataQuery.TransHeadSelect);
		rs = Prest.executeQuery();
		finalResults = new ArrayList<Object>();
		
         while (rs.next()) {
        	transBean = new MiscellaneousBean();
			transBean.setThead(rs.getString("Trans_Head"));		
			transBean.setTamount(rs.getString("Amount"));
			finalResults.add(transBean);
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
		return finalResults;
	}
	
	
	
	public MiscellaneousBean findMiscellaneousSave(MiscellaneousBean transBean)
	{
		MiscellaneousBean resultBean = null;
		
		log4jLogger.info("start===========findMiscellaneousSave=====");
		try
		{
		 SQLQuery sql=null;	
		 String query=null;		 
		 
		if(MiscellaneousSaveCheck(transBean))
	    {
		 
		 query = getSession().getNamedQuery("InsertTransMasQuery").getQueryString();
		 sql = getSession().createSQLQuery(query);
		 
		 sql.setParameter("transDate",transBean.getTdate());
		 sql.setParameter("transHead",transBean.getThead());
		 sql.setParameter("memberCode",transBean.getMuserID());
		 sql.setParameter("transAmount",transBean.getTamount());
		 sql.setParameter("remarks",transBean.getStaffCode());
		 
		 int count = sql.executeUpdate();
		 
		 con = getSession().connection();	
		 Prest = con.prepareStatement(DataQuery.Mis_TransMasMaxCode);
		 rs = Prest.executeQuery();
		
		 if (rs.next()) {			
			
			transBean.setTransNo(rs.getInt("maxno"));			// Select the current transacion number.
		 }
		 	 
				 
		 getSession().save(transBean);
		 getSession().flush();
		 
		 resultBean = new MiscellaneousBean();
		 resultBean.setMrno(0);
		 
	    }else
	    {
	    	resultBean = new MiscellaneousBean();
	    	resultBean.setMrno(1);
	    	//resultBean = findMiscellaneousSearch(transBean.getMrno());
	    	
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
		 
		 
		return resultBean;
	}
	
	public boolean MiscellaneousSaveCheck(MiscellaneousBean newbean)
	{	    	
	   	boolean check=false;
	    	
	   	if (newbean.getThead() != null)
		{
	    	Criteria crit = getSession().createCriteria(MiscellaneousBean.class);
	    	crit.add(Restrictions.eq("mrno", newbean.getMrno()));
	    	
			List list = crit.list();
			if (list == null || list.size() == 0)
			{
				check = true;
			}    		
		}
	    	
	    return check;
	}
	
	
	public MiscellaneousBean findTransMasPayment(MiscellaneousBean transBean)
	{
		log4jLogger.info("start===========findTransMasPayment=====");
		
		SQLQuery sql=null;	
		String query=null;
		int Bill_no = 0;
		int count = 0;
		MiscellaneousBean resultBean = new MiscellaneousBean();
		
		try {
			
		con = getSession().connection();
		Prest = con.prepareStatement(DataQuery.Max_BillNo_Payment);
		rs=Prest.executeQuery();	

		if (rs.next()) {			
			 Bill_no=rs.getInt("billno");
		}	

		if(Bill_no==0) {			
			Bill_no=1;
		}
		log4jLogger.info("Bill_No is: "+Bill_no);		
		 
		query = getSession().getNamedQuery("InsertMisPaymentMasQuery").getQueryString();
		sql = getSession().createSQLQuery(query);
		
		sql.setParameter("Bill_No", Bill_no);
		sql.setParameter("memberCode", transBean.getMuserID());
		sql.setParameter("payAmount", transBean.getTamount());
		sql.setParameter("payDate", transBean.getTdate());
		sql.setParameter("payMode", transBean.getThead());
		sql.setParameter("staffCode", transBean.getTaddfield1());
		
		count = sql.executeUpdate();
		
		
		query = getSession().getNamedQuery("UpdateMisTransMasQuery").getQueryString();
		sql = getSession().createSQLQuery(query);
		
		sql.setParameter("payNo", Bill_no);
		sql.setParameter("payFlag", "PAID");
		sql.setParameter("refNo", transBean.getMrno());
						
		count = sql.executeUpdate();	
		
		resultBean.setMrno(count);
		
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
		
		return resultBean;
		
	}
	
	public MiscellaneousBean findMiscellaneousMember(String code)
	{	  
	    MiscellaneousBean transMember = null;
	    try
	    {
		 log4jLogger.info("start===========findMiscellaneousMember=====");
	  
		 con = getSession().connection();	
		 Prest = con.prepareStatement(DataQuery.Trans_SelectMember);
		 Prest.setString(1, code);
		 rs = Prest.executeQuery();
		
		 
		 
		 
		 
		 if (rs.next()) {			
			 transMember = new MiscellaneousBean();
			 transMember.setMuserID(rs.getString("member_code"));
			 transMember.setMname(rs.getString("member_name"));
			 transMember.setCourse(rs.getString("cname")+" - "+rs.getString("cmajor"));
			 transMember.setPhoto1(rs.getBytes("photo"));
			 transMember.setSlock(rs.getString("slock"));
			 transMember.setCyear(rs.getString("cyear"));
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
		
		return transMember;
		
	}
	
	
	public MiscellaneousBean findMiscellaneousSearch(int code)
	{
		log4jLogger.info("start===========findMiscellaneousSearch====="+code);
		SQLQuery sql=null;
		String query="";
		
		query = getSession().getNamedQuery("SelectMiscellaneousQuery").getQueryString();
		sql = getSession().createSQLQuery(query);
		sql.setParameter("refNo",code);
		
		List list = sql.list();		
		
		MiscellaneousBean transDetails = null;
		
		if(list!=null)
		{
			if(list.size() > 0)
			{
				Object[] obj = (Object[]) list.get(0);				
				transDetails = new MiscellaneousBean();
				
				transDetails.setMrno(Integer.parseInt(obj[0].toString()));
				transDetails.setMuserID(obj[1].toString());
				transDetails.setMname(obj[2].toString());
				transDetails.setCourse(obj[3].toString()+"-"+obj[4].toString());	
				if( (obj[5].toString() != null) && (!obj[5].toString().isEmpty()) )
				{
				 try
				 {					
					con = getSession().connection();
					Prest = con.prepareStatement(DataQuery.MemberPhoto+" where member_code=?");
					Prest.setString(1, obj[1].toString());
					rs = Prest.executeQuery();
					if (rs.next()) {							 
						 transDetails.setPhoto1(rs.getBytes("photo"));
					 }									
					
					Prest = con.prepareStatement(DataQuery.TransHeadSelect+" where Trans_Head=?");
					Prest.setString(1, obj[5].toString());
					rs = Prest.executeQuery();
					 
					 if (rs.next()) {							 
						 transDetails.setThead(rs.getString("Amount"));			
						 
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
				
				}				
				
				
				transDetails.setQuantity(Integer.parseInt(obj[6].toString()));				
				transDetails.setTamount(obj[7].toString());
				transDetails.setTremarks(obj[8].toString());
				transDetails.setTaddfield1(obj[9].toString());
				
				transDetails.setTdate(Security.getdate(obj[10].toString()));
				transDetails.setTransNo(Integer.parseInt(obj[11].toString()));
				transDetails.setPaymentNo(Integer.parseInt(obj[12].toString()));
				transDetails.setPayFlag(obj[13].toString());
			}		
		}
		
		return transDetails;
		
	}
	
	
	public int findMiscellaneousDelete(int code)
	{
		
		log4jLogger.info("start===========findTransMasDelete====="+code);
		int count=0;
		
		try
		{
		 SQLQuery sql=null;	
		 String query=null;		
		 
		 int transNo = 0,payNo=0;
		 String payFlag = "";        
		 
		 con = getSession().connection();	
		 Prest = con.prepareStatement(DataQuery.MiscellaneousTransNo);
		 Prest.setInt(1, code);		 
		 rs = Prest.executeQuery();
		
		 if (rs.next()) {			 
			 
			 transNo = rs.getInt("Trans_No");
			 payNo = rs.getInt("Payment_No");			 
			 payFlag = rs.getString("Pay_Flag");
		 }
		 
		 // Delete Trans_Mas Table		 
		 if(transNo > 0)
		 {
		 
			 con = getSession().connection();	
			 Prest = con.prepareStatement("delete from trans_mas where Trans_No=?");
			 Prest.setInt(1, transNo);		 
			 count = Prest.executeUpdate();	 
		 }
			
		 // Delete Payment_Clearance Table			 
		 if(payNo > 0 && payFlag.equalsIgnoreCase("PAID"))
		 {
			 
			 con = getSession().connection();	
			 Prest = con.prepareStatement("delete from payment_clearance where Bill_No=?");
			 Prest.setInt(1, payNo);		 
			 count = Prest.executeUpdate();	
			 
		 }		 
		 
		 // Delete Miscellaneous_Mas Table
		    query = getSession().getNamedQuery("MiscellaneousDeleteQuery").getQueryString();
			sql = getSession().createSQLQuery(query);
			sql.setParameter("refNo",code);	
			
			count= sql.executeUpdate();	 
		 
	   
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
	
	public int findMiscellaneousUpdate(MiscellaneousBean transBean)
	{
		log4jLogger.info("start===========findMiscellaneousUpdate=====");
		int count=0;
		
		try
		{
		 SQLQuery sql=null;	
		 String query=null;		
		 
		 int transNo = 0,payNo=0;
		 String payFlag = "";        
		 
		 con = getSession().connection();	
		 Prest = con.prepareStatement(DataQuery.MiscellaneousTransNo);
		 Prest.setInt(1, transBean.getMrno());		 
		 rs = Prest.executeQuery();
		
		 if (rs.next()) {			 
			 
			 transNo = rs.getInt("Trans_No");
			 payNo = rs.getInt("Payment_No");			 
			 payFlag = rs.getString("Pay_Flag");
		 }
		 
		 
		 if(transNo > 0)
		 {
		 
		 query = getSession().getNamedQuery("UpdateTransMasQuery").getQueryString();
		 sql = getSession().createSQLQuery(query);
		 
		 sql.setParameter("transDate",transBean.getTdate());
		 sql.setParameter("transHead",transBean.getThead());
		 sql.setParameter("memberCode",transBean.getMuserID());
		 sql.setParameter("transAmount",transBean.getTamount());
		 sql.setParameter("remarks",transBean.getTremarks());
		 
		 sql.setParameter("transNo" , transNo);
		 
		 count = sql.executeUpdate();
		 
		 transBean.setTransNo(transNo);
		 
		 }
			
		 if(payNo > 0 && payFlag.equalsIgnoreCase("PAID"))
		 {
			 
			query = getSession().getNamedQuery("UpdateMisPaymentMasQuery").getQueryString();
			sql = getSession().createSQLQuery(query);
			
			sql.setParameter("memberCode", transBean.getMuserID());
			sql.setParameter("payAmount", transBean.getTamount());
			sql.setParameter("payDate", transBean.getTdate());
			sql.setParameter("payMode", transBean.getThead());
			sql.setParameter("staffCode", transBean.getPayFlag());
			
			sql.setParameter("Bill_No", payNo);
			
		    count = sql.executeUpdate();
		    
		    transBean.setPaymentNo(payNo);
		    transBean.setPayFlag(payFlag);
		 }		 
		 
		 
		 getSession().saveOrUpdate(transBean);
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
	
	
	
	
//  ---------------------- Member Transfer ----------		
	
	public List findDepartmentList()
	{
		log4jLogger.info("start===========getDepartmentList=====");
		
		MemberTransRefBean refBean = null;
		List<Object> result = new ArrayList<Object>();
		
		SQLQuery sql = getSession().createSQLQuery(DataQuery.DEPTSEARCH_NAME);		
		List list = sql.list();
		
		for(int i=0; i<list.size(); i++)
		{
			Object[] obj = (Object[]) list.get(i);
			refBean = new MemberTransRefBean();
			
			refBean.setCode(Integer.parseInt(obj[0].toString()));
			refBean.setName(obj[1].toString());
			refBean.setDesc(obj[2].toString());
			
			result.add(refBean);		
		}	
		
		return result;
	}
	
	
	public List findQBSubjectList()
	{
		log4jLogger.info("start===========getQB Subject List=====");
		String strsql = "";
		
		MemberTransRefBean refBean = null;
		List<Object> result = new ArrayList<Object>();
		
		SQLQuery sql = getSession().createSQLQuery(DataQuery.QBSUBJECTSEARCHLIST+strsql +" order by qbsub_name asc");		
		List list = sql.list();
		
		for(int i=0; i<list.size(); i++)
		{
			Object[] obj = (Object[]) list.get(i);
			refBean = new MemberTransRefBean();
			
			refBean.setCode(Integer.parseInt(obj[0].toString()));
			refBean.setName(obj[1].toString());
			refBean.setDesc(obj[2].toString());
			
			result.add(refBean);		
		}	
		
		return result;	
	}
	
	public List findCourseList()
	{
		log4jLogger.info("start===========getCourseList=====");
		
		MemberTransRefBean refBean = null;
		List<Object> result = new ArrayList<Object>();
		
		SQLQuery sql = getSession().createSQLQuery(DataQuery.COURSESEARCH_NAME);		
		List list = sql.list();
		
		for(int i=0; i<list.size(); i++)
		{
			Object[] obj = (Object[]) list.get(i);
			refBean = new MemberTransRefBean();
			
			refBean.setCode(Integer.parseInt(obj[0].toString()));
			refBean.setName(obj[1].toString());
			refBean.setDesc(obj[2].toString());
			
			result.add(refBean);		
		}	
		
		return result;	
	}
	
	public List findGroupList()
	{
		log4jLogger.info("start===========getGroupList=====");
		
		MemberTransRefBean refBean = null;
		List<Object> result = new ArrayList<Object>();
		
		SQLQuery sql = getSession().createSQLQuery(DataQuery.GROUPSEARCH_NAME);		
		List list = sql.list();
		
		for(int i=0; i<list.size(); i++)
		{
			Object[] obj = (Object[]) list.get(i);
			refBean = new MemberTransRefBean();
			
			refBean.setCode(Integer.parseInt(obj[0].toString()));
			refBean.setName(obj[1].toString());
			refBean.setDesc(obj[2].toString());
			
			result.add(refBean);		
		}	
		
		return result;	
	}
	
			
	public List findMemberList(MemberTransRefBean newBean)
	{
		
       log4jLogger.info("start===========findMemberList=====");
		
		MemberTransRefBean refBean = null;
		List<Object> result = new ArrayList<Object>();
		
		SQLQuery sql = getSession().createSQLQuery(DataQuery.Member_Transfer_Select);		
		sql.setParameter("deptCode",newBean.getDeptCode());
		sql.setParameter("courseCode",newBean.getCourseCode());
		sql.setParameter("groupCode",newBean.getGroupCode());	
		sql.setParameter("courseYear",newBean.getAdd1());
		
		List list = sql.list();
		
		for(int i=0; i<list.size(); i++)
		{
			Object[] obj = (Object[]) list.get(i);
			refBean = new MemberTransRefBean();
						
			refBean.setName(obj[0].toString());
			refBean.setDesc(obj[1].toString());
			
			result.add(refBean);		
		}	
		
		return result;	
		
	}
	
	
	public int findMemberTransfer(MemberTransRefBean newBean)
	{
        log4jLogger.info("start===========findMemberTransfer====="+newBean.getAdd1());

		SQLQuery sql = getSession().createSQLQuery(DataQuery.Member_Transfer_SaveQurey+" and member_code in(null"+newBean.getAdd1()+")");		
		sql.setParameter("courseYear",newBean.getAdd2());
				
		int count = sql.executeUpdate();		
		return count;		
	}
	


// Bulk Updation
 
	public List findBulkUpdateList(String param, String flag)
	{
		log4jLogger.info("start===========findBulkUpdateList====="+flag);		
		String query = "";		
		
		if(flag.equalsIgnoreCase("Aut"))  // will be update author_interface, sort_book table in future.
        {
			query = DataQuery.BUpdate_Author;
        }
		else if(flag.equalsIgnoreCase("Dept"))
        {
        	query = DataQuery.BUpdate_Department;        		 	        	   
        }
		else if(flag.equalsIgnoreCase("Invoice_No"))
        {
        	query = DataQuery.BUpdate_Invoice;        		 	        	   
        }
		else if(flag.equalsIgnoreCase("Discount"))
        {
        	query = DataQuery.BUpdate_Discount;        		 	        	   
        }
        else if(flag.equalsIgnoreCase("Pub"))
        {
        	query = DataQuery.BUpdate_Publisher;
        }
        else if(flag.equalsIgnoreCase("Sup"))
        {
        	query = DataQuery.BUpdate_Supplier;
        }
        else if(flag.equalsIgnoreCase("Sub"))
        {
        	query = DataQuery.BUpdate_Subject;
        }        	
        else if(flag.equalsIgnoreCase("Bud"))
        {
        	query = DataQuery.BUpdate_Budget;
        }
		
		
		else if(flag.equalsIgnoreCase("CALLNO"))
	    {
	    	query = DataQuery.BUpdate_CallNo;
	    }
        else if(flag.equalsIgnoreCase("Title"))
        {
        	query = DataQuery.BUpdate_Title;
        }
        else if(flag.equalsIgnoreCase("Edition"))
        {
        	query = DataQuery.BUpdate_Edition;
        }
        else if(flag.equalsIgnoreCase("YearPub"))
        {
        	query = DataQuery.BUpdate_YearPub;
        }
        else if(flag.equalsIgnoreCase("Keywords"))
        {
        	query = DataQuery.BUpdate_Keywords;
        }
        else if(flag.equalsIgnoreCase("Language"))
        {
        	query = DataQuery.BUpdate_Language;
        }
        else if(flag.equalsIgnoreCase("Location"))
        {
        	query = DataQuery.BUpdate_Location;
        }       
        else if(flag.equalsIgnoreCase("VolumeNo"))
        {
        	query = DataQuery.BUpdate_VolNo;
        }
        else if(flag.equalsIgnoreCase("ISBN"))
        {
        	query = DataQuery.BUpdate_ISBN;
        }
        else if(flag.equalsIgnoreCase("Availability"))
        {
        	query = DataQuery.BUpdate_Availability;
        }
        else if(flag.equalsIgnoreCase("PurchaseType"))
        {       	
        	query = DataQuery.BUpdate_Pur_type;
        }
        else if(flag.equalsIgnoreCase("biblio"))
        {
        	query = DataQuery.BUpdate_biblio;
        }
        else if(flag.equalsIgnoreCase("Invoice_date"))
        {
        	query = DataQuery.BUpdate_Invoice_date;
        }
        else if(flag.equalsIgnoreCase("Bprice"))
        {
        	query = DataQuery.BUpdate_Bprice;
        }
		
		
		List list = null;
		
		if(!query.isEmpty())
    	{
    		SQLQuery sql = getSession().createSQLQuery(query);
            list = sql.list();
    	} 
        
        return list;		
	}
	
	
	public int findBulkUpdateSave(BulkUpdateMsgBean newBean)
	{
		log4jLogger.info("start===========findBulkUpdateSave====="+newBean.getToValue()+ " And " +newBean.getFrmValue());	
		String query = ""; 	
		SQLQuery sql = null;
		int count = 0;
		
		try {
			
		if(!newBean.getToValue().isEmpty() && !newBean.getFrmValue().isEmpty())
		{			
			String frmAuthor = "NIL",toAuthor;
			int frmCode,toCode;
			List list;			
			
			if(newBean.getToUpdate().equalsIgnoreCase("Aut"))
			{				
				String fromAuthor[]= {""};
				
				if(newBean.getFrmUpdate().equalsIgnoreCase("Aut"))
				{				
					fromAuthor = newBean.getFrmValue().split(";");		
				}
								
				query = DataQuery.BUpdate_Author + newBean.getToValue();
				sql = getSession().createSQLQuery(query);
				list = sql.list();
				
				if(list!=null && list.size() > 0)
				{
					Object[] obj = (Object[]) list.get(0);		
					
					toCode = Integer.parseInt(obj[0].toString());
					toAuthor = obj[1].toString();	  // To Author Value					
					
					list = null;
					query = "";
					sql = null;
					
					if(newBean.getFrmUpdate().equalsIgnoreCase("Aut"))
					{						
						//query = DataQuery.BUpdate_Author + newBean.getFrmValue();
						
						query = DataQuery.BUpdate_Author + fromAuthor[0];							
						sql = getSession().createSQLQuery(query);
						list = sql.list();						
												
						if(list!=null && list.size() > 0)
						{
							Object[] obj1 = (Object[]) list.get(0);		
							
							frmCode = Integer.parseInt(obj1[0].toString());
							frmAuthor = obj1[1].toString();	  // From Author Value						
						}
						query = "";
						
						try
						{
							query = DataQuery.BUpdate_AutIntSelect + " WHERE access_no IN ("+DataQuery.BUpdate_Accno+" WHERE author_name='"+frmAuthor+"' "+fromAuthor[1]+") AND doc_type='BOOK' AND priority=1";
						}catch(ArrayIndexOutOfBoundsException arrayOutException)  
				    	{	
				    	    query = DataQuery.BUpdate_AutIntSelect + " WHERE access_no IN ("+DataQuery.BUpdate_Accno+" WHERE author_name='"+frmAuthor+"') AND doc_type='BOOK' AND priority=1";
				    	}					
						//log4jLogger.info("************ Author Wise ************"+frmAuthor);
					}
					else  {
						//log4jLogger.info("************ ELSE Author Wise ************"+newBean.getFrmValue());
						query = DataQuery.BUpdate_AutIntSelect + " WHERE access_no IN ("+DataQuery.BUpdate_Accno+" WHERE 2>1 "+newBean.getFrmValue()+") AND doc_type='BOOK' AND priority=1"; 
					}					
					
					list = null;					
					sql = null;
					
					sql = getSession().createSQLQuery(query);
					list = sql.list();
					
					if(list!=null && list.size() > 0)
					{							
						query = "";          // Update Author Interface Table
						sql = null;
						
						if(newBean.getFrmUpdate().equalsIgnoreCase("Aut"))
						{   
							try
							{
								query = DataQuery.BUpdate_AutInter + " WHERE access_no IN ("+DataQuery.BUpdate_Accno+" WHERE author_name='"+frmAuthor+"' "+fromAuthor[1]+") AND doc_type='BOOK' AND priority=1";
							}
							catch(ArrayIndexOutOfBoundsException arrayOutException)  
					    	{	
					    	    query = DataQuery.BUpdate_AutInter + " WHERE access_no IN ("+DataQuery.BUpdate_Accno+" WHERE author_name='"+frmAuthor+"') AND doc_type='BOOK' AND priority=1";
						    }							
						}
						else {
							//log4jLogger.info("************ ELSE Author Wise UPDATE INTERFACE TABLE ************"+newBean.getFrmValue());
							query = DataQuery.BUpdate_AutInter + " WHERE access_no IN ("+DataQuery.BUpdate_Accno+" WHERE 2>1 "+newBean.getFrmValue()+") AND doc_type='BOOK' AND priority=1";
						}
						
						sql = getSession().createSQLQuery(query);
						sql.setParameter("autcode", toCode);
						count = sql.executeUpdate();						
						
						if(count > 0)    // Update Book Master Table
						{
							query = "";
						    sql = null;
						    
						    if(newBean.getFrmUpdate().equalsIgnoreCase("Aut"))
						    {  	
						    	try	{
						    		query = DataQuery.BUpdate_Save_Query + " author_name='"+toAuthor+"' WHERE author_name='"+frmAuthor+"' "+fromAuthor[1];
						    	}
						    	catch(ArrayIndexOutOfBoundsException arrayOutException)  
						    	{	
						    		query = DataQuery.BUpdate_Save_Query + " author_name='"+toAuthor+"' WHERE author_name='"+frmAuthor+"' ";
								}
							    
							}
						    else {
							    //log4jLogger.info("************ ELSE Author Wise UPDATE BOOK TABLE ************"+toAuthor);
							    query = DataQuery.BUpdate_Save_Query + " author_name='"+toAuthor+"' WHERE 2>1 "+newBean.getFrmValue();
						    }
						   
						    sql = getSession().createSQLQuery(query);						
						    count = sql.executeUpdate();	
						}
					}				
				}			
			}
			else 
			{			
				if(newBean.getFrmUpdate().equalsIgnoreCase("Aut"))
				{
					String fromAuthor[] = newBean.getFrmValue().split(";");					
					//query = DataQuery.BUpdate_Author + newBean.getFrmValue();
					
					query = DataQuery.BUpdate_Author + fromAuthor[0];
					sql = getSession().createSQLQuery(query);
					list = sql.list();
					
					if(list!=null && list.size() > 0)
					{
						Object[] obj1 = (Object[]) list.get(0);		
						
						frmCode = Integer.parseInt(obj1[0].toString());
						frmAuthor = obj1[1].toString();	  // From Author Value	
						
						query = "";
						sql = null;						
						
						try	{							
							query = DataQuery.BUpdate_Save_Query+newBean.getToValue()+" where 2>1 and author_name='"+frmAuthor+"'"+fromAuthor[1];
						}
						catch(ArrayIndexOutOfBoundsException arrayOutException)  
						{							
							query = DataQuery.BUpdate_Save_Query+newBean.getToValue()+" where 2>1 and author_name='"+frmAuthor+"'";
						}
						
						sql = getSession().createSQLQuery(query);
						count = sql.executeUpdate();
					}		
				}
				else
				{
					query = DataQuery.BUpdate_Save_Query+newBean.getToValue()+" where 2>1 "+newBean.getFrmValue();
					sql = getSession().createSQLQuery(query);
					count = sql.executeUpdate();					
				}								
			}			
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
	
	
	public List findBulkMemberUpdateList(String param, String flag)
	{
		log4jLogger.info("start===========findBulkMemberUpdateList====="+flag);	
		String query = "";		
		String strsql = "";
		/*if(branchID > 0)
		{
			strsql = " AND branch_Code="+branchID+" ";
		}*/		
		
		if(flag.equalsIgnoreCase("Desig"))  
        {
			query = DataQuery.BulkMemberUpdate_Desig+" order by desig_name asc ";
        }else if(flag.equalsIgnoreCase("Dept")){
        	query = DataQuery.BulkMemberUpdate_Dept+" order by dept_name asc ";
        }else if(flag.equalsIgnoreCase("Group")){
        	query = DataQuery.BulkMemberUpdate_Group+" order by group_name asc ";
        }else if(flag.equalsIgnoreCase("Course")){
        	query = DataQuery.BulkMemberUpdate_Course+" order by course_name asc ";
        }else if(flag.equalsIgnoreCase("Slock")){
        	query = DataQuery.BulkMemberUpdate_Slock+" order by slock asc ";
        }else if(flag.equalsIgnoreCase("CourseYear")){
        	query = DataQuery.BulkMemberUpdate_CourseYear+" order by cyear asc ";
        }else if(flag.equalsIgnoreCase("Sex")){
        	query = DataQuery.BulkMemberUpdate_Sex+" order by sex asc ";
        }
		
		
		List list = null;
		
		if(!query.isEmpty())
    	{
    		SQLQuery sql = getSession().createSQLQuery(query);
            list = sql.list();
    	} 
        
        return list;		
	}
	
	
	public int findBulkMemberUpdateSave(BulkUpdateMsgBean newBean){
		log4jLogger.info("start===========findBulkMemberUpdateSave====="+newBean.getToValue()+ " And " +newBean.getFrmValue());	
		String query = ""; 	
		SQLQuery sql = null;
		int count = 0;
		try {
				query = DataQuery.BulkMemberUpdate_Save_Query+"'"+newBean.getToValue()+"' where 2>1 "+newBean.getFrmValue();
			
			log4jLogger.info("::::::::::final Query:::::::: "+query);
			
			sql = getSession().createSQLQuery(query);
			count = sql.executeUpdate();
			
			
			
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
		
		return count;
	}
	
	public RefDaysBean getRefDueDays()
	{
		RefDaysBean resultBean = new RefDaysBean();
		
		try {
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.SELECT_REF_DUEDAYS);
			rs = Prest.executeQuery();
			if (rs.next()) {
				resultBean = new RefDaysBean();
				resultBean.setDue_days(rs.getInt("due_days"));
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
	return resultBean;	
	}
	public SugmailBean getsugEmail()
	{
		SugmailBean resultBean = new SugmailBean();
	
		try {
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.SELECT_SUG_EMAILID);
			rs = Prest.executeQuery();
			if (rs.next()) {
				resultBean = new SugmailBean();
				resultBean.setEmail_id(rs.getString("email_id"));
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
	return resultBean;	
	
		
	}
	
	
	
	
	
	
	
	
	public int getduedaysSave(RefDaysBean newBean)
	{
		int count = 0;
		try {
			
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.DELETE_REF_DUEDAYS);			
			count = Prest.executeUpdate();		
			
			
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.SAVE_REF_DUEDAYS);
			Prest.setInt(1, newBean.getDue_days());
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
	
	public int getsugEmailsave(SugmailBean newBean)
	
	{

		int count = 0;
		try {
			
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.DELEET_SUG_EMAIL);			
			count = Prest.executeUpdate();		
			
			
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.SAVE_SUG_EMAIL);
			Prest.setInt(1, 1);
			Prest.setString(2, newBean.getEmail_id());

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
	
	
	
	
	
	
	
	public int getBranchCode(String branchName)
	{		
		int branchCode = 0;		
		try {		
			
		con = getSession().connection();
		Prest = con.prepareStatement(DataQuery.BOOKSEARCH_CODE_BRANCH);
		Prest.setString(1, branchName);
		rs = Prest.executeQuery(); 

		  if (rs.next())
		  {
			  branchCode = rs.getInt("branch_code"); 
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

	return branchCode;	
	}

	
	//-------------------Due Date Update-----------------
		public int findDueDateCount(IssueMasUpdatebean newBean)
		{
			int count1=0;
			
			 try {		
		    	   con = getSession().connection();
					Prest = con.prepareStatement("select count(*) from issue_mas where due_date between '"+newBean.getFromdt()+"' and '"+newBean.getTodt()+"'");			
				    rs = Prest.executeQuery();
				    if(rs.next()){
				    	count1= rs.getInt(1);
				    }
			 }
		       catch (Exception e) {
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
			
			return count1;
		}
		
		
		public int findDueDateUpdate(IssueMasUpdatebean newBean)
		{
			log4jLogger.info("==================findDueDateUpdate=========="+newBean.getFromdt());
			log4jLogger.info("==================findDueDateUpdate=========="+newBean.getTodt());
			int Count=0;
					
	       try {		
				con = getSession().connection();
				Prest = con.prepareStatement("update issue_mas set due_date='"+newBean.getUpdt()+"' where due_date between '"+newBean.getFromdt()+"' and '"+newBean.getTodt()+"'");			
			    Count = Prest.executeUpdate();		
			
	       }
	       catch (Exception e) {
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

			return Count;
			
		}

		@Override
		public int findImportGateData(List details) {		
			log4jLogger.info("start ====== findImportGateData =====:"+details.size());
			int count = 0;
			try {
				CalalogingService cs = BusinessServiceFactory.INSTANCE.getCalalogingService();

				for (int k = 0; k < details.size(); k++)
				{
					GateDataBean detailsbean=(GateDataBean) details.get(k);


					// Start Save Data into Web_mas Table

					getSession().save(detailsbean);
					getSession().flush();
					getSession().clear();

				}	} catch (Exception e) {

					e.printStackTrace();
				}		



			count++;


			log4jLogger.info("End ====== findImportGateData ========"+count);

			return count;

		}
		
}
