package Common.businessutil.acquisition;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Restrictions;

import Common.DataQuery;
import Common.Security;
import Common.Security_Counter;
import Lib.Auto.Indent_Approval.IndentApproveBean;
import Lib.Auto.Indent_Invoice.IndentInvBean;
import Lib.Auto.Indent_Invoice.IndentInvDetailsBean;
import Lib.Auto.Indent_Mas.IndentMasDetailsBean;
import Lib.Auto.Indent_Order.IndentOrderDetailsBean;
import Lib.Auto.Indent_Payment.IndentPaymentBean;
import Lib.Auto.OrdInvProcessing.orderbean;

import com.library.autolib.portal.dbconnectionutil.BaseDBConnection;

public class AcquisitionDaoImpl extends BaseDBConnection implements
AcquisitionDao {
	private static Logger log4jLogger = Logger.getLogger(AcquisitionDaoImpl.class);
	
	java.sql.Connection con = null;

	java.sql.PreparedStatement Prest = null;
	java.sql.PreparedStatement Prest1 = null;
	java.sql.ResultSet rs = null;
	java.sql.ResultSet rs1 = null;
	java.sql.Statement st = null;
	
	public int findOrdInvMax() {
		log4jLogger.info("start===========findOrdInvMax=====");

		int code=0;
		try {
						 
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.ORDERSELECT_NEW);
			rs = Prest.executeQuery();
			if (rs.next()) {
				
				code=rs.getInt("maxno")+1;
			
			
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
	
	public List findOrdInvSearchName(orderbean newbean) {
		
		log4jLogger.info("start===========findOrdInvMax=====");
		ArrayList finalResults = new ArrayList();
		orderbean ob=null;
		try{
			if (newbean.getIadd1().equals("Slno"))
			{
			con = getSession().connection();
			st = con.createStatement();
			if (newbean.getShort_desc() == "") {
				rs = st.executeQuery(DataQuery.ORDER_SEARCH );
			}else{
				rs = st.executeQuery(DataQuery.ORDER_SEARCH_Like
						
						+newbean.getShort_desc() + "%' ");
			}
		
			while (rs.next()) {
				   ob=new orderbean();
			       ob.setSno(rs.getInt("sno"));
		           ob.setIord(rs.getString("ord_no"));
		           ob.setIordate(Security_Counter.Counter_DateFormat(rs.getDate("ord_date")));
		           finalResults.add(ob);
	   	
				}
			
				
			}	
			if ((newbean.getIadd1().equals("Dept"))||(newbean.getIadd1().equals("Dept_Report")))
			{
			con = getSession().connection();
			st = con.createStatement();
			if (newbean.getShort_desc() == "") {
				rs = st.executeQuery(DataQuery.BUDGET_DEPT_SEARCH );
			}else{
				rs = st.executeQuery(DataQuery.BUDGET_DEPT_SEARCH_LIKE
						
						+newbean.getShort_desc() + "%' order by dept_name");
			}
		
			while (rs.next()) {
				   ob=new orderbean();
			       ob.setIdcode(rs.getInt("Dept_Code"));
		           ob.setIdname(rs.getString("Dept_Name"));
		           ob.setShort_desc(rs.getString("Short_Desc"));
		           finalResults.add(ob);
	   	
				}
			
				
			}	
			if ((newbean.getIadd1().equals("Sup"))||(newbean.getIadd1().equals("Sup_Report")))
			{

			con = getSession().connection();
			st = con.createStatement();
			if (newbean.getShort_desc() == "") {
				rs = st.executeQuery(DataQuery.PUBSUBSEARCH_STRING_SUP );
			}else{
				rs = st.executeQuery(DataQuery.PUBSUBSEARCH_STRING_LIKE_SUP
						
						+newbean.getShort_desc() + "%' and sp_type='SUPPLIER' order by sp_name");
			}
		
			while (rs.next()) {				
				
				   ob=new orderbean();
				   ob.setIscode(rs.getInt("SP_Code"));
		           ob.setIsname(rs.getString("SP_Name"));
		           ob.setShort_desc(rs.getString("Short_Desc"));
		           finalResults.add(ob);
	   	
				}
			
				
			}	
			if ((newbean.getIadd1().equals("Bud"))||(newbean.getIadd1().equals("Budget_Report")))
			{
			con = getSession().connection();
			st = con.createStatement();
			if (newbean.getShort_desc() == "") {
				rs = st.executeQuery(DataQuery.BUDGET_SEARCH_LIST );
			}else{
				rs = st.executeQuery(DataQuery.BUDGET_SEARCH_LIKE
						
						+newbean.getShort_desc() + "%' order by bud_head");
			}
		
			while (rs.next()) {
				   ob=new orderbean();
				   ob.setIbcode(rs.getInt("bud_code"));
		           ob.setIbhead(rs.getString("bud_head"));
		           ob.setIamount(rs.getDouble("bud_Amount"));
		           finalResults.add(ob);
	   	
				}
			
				
			}	
			if (newbean.getIadd1().equals("Inv"))
			{
			con = getSession().connection();
			st = con.createStatement();
			if (newbean.getShort_desc() == "") {
				rs = st.executeQuery(DataQuery.ORDER_INV_SEARCH );
			}else{
				rs = st.executeQuery(DataQuery.ORDER_INV_SEARCH_Like
						
						+newbean.getShort_desc() + "%' ");
			}
		
			while (rs.next()) {
				   ob=new orderbean();
				   ob.setIinvno(rs.getString("Inv_no"));
		           ob.setIscode(rs.getInt("Sup_code"));
		           ob.setIinvdate(Security_Counter.Counter_DateFormat(rs.getDate("inv_date")));
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
	
	return finalResults;
	}
	
	public orderbean findOrdInvSearch(String name) {
		log4jLogger.info("start===========findOrdInvSearch====="+name);
		orderbean orderObject=null;
		 orderObject=new orderbean();
		try {
						 
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.ORDERSELECT_STRING);
			Prest.setString(1,name);
			rs = Prest.executeQuery();
			if (rs.next()) {
				
				
				orderObject.setImax(rs.getInt("sno"));
				orderObject.setIord(rs.getString("ord_no"));
				orderObject.setIordate(Security.getdate(rs.getString("ord_date")));
				orderObject.setIscode(rs.getInt("sup_code"));
				orderObject.setIbcode(rs.getInt("bud_code"));
				orderObject.setIinvno(rs.getString("inv_no"));
				orderObject.setIinvdate(Security.getdate(rs.getString("Inv_Date")));
				orderObject.setIamount(rs.getDouble("amount"));
				orderObject.setIdcode(rs.getInt("dept_code"));
				orderObject.setIyear(rs.getString("year"));
				orderObject.setIdtype(rs.getString("doc_type"));
				orderObject.setIcrdeb(rs.getString("cr_Deb"));
				orderObject.setIpaid(rs.getString("paid"));
				orderObject.setIpaydate(Security.getdate(rs.getString("pay_Date")));
				orderObject.setImode(rs.getString("mode"));
				orderObject.setIpaydet(rs.getString("pay_details"));
				orderObject.setIadd1(rs.getString("add1"));
				orderObject.setIadd2(rs.getString("add2"));
			
			

				con = getSession().connection();
				Prest = con.prepareStatement(DataQuery.ORDER_SUP_NAME);
				Prest.setInt(1,orderObject.getIscode());
				rs = Prest.executeQuery();
				if (rs.next()) {
					
					orderObject.setIsname(rs.getString("supplier"));
					
				}
				con = getSession().connection();
				Prest = con.prepareStatement(DataQuery.BUDGET_SEARCH);
				Prest.setInt(1,orderObject.getIbcode());
				rs = Prest.executeQuery();
				if (rs.next()) {
					
					orderObject.setIbhead(rs.getString("bud_head"));
				}
				con = getSession().connection();
				Prest = con.prepareStatement(DataQuery.DEPARTMENTSELECT_STRING);
				Prest.setInt(1,orderObject.getIdcode());
				rs = Prest.executeQuery();
				if (rs.next()) {
					
					orderObject.setIdname(rs.getString("dept_name"));
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

		return orderObject;
	}
	
	public int findOrdInvSave(orderbean newbean) {
		log4jLogger.info("start===========findOrdInvSave=====");

		int count=0;
		try {
						 
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.ORDERSELECT_INSERT);
			
			Prest.setInt(1,newbean.getImax());
			Prest.setString(2,newbean.getIord());
			Prest.setString(3,(Security.getdate(newbean.getIordate())));
			Prest.setInt(4,newbean.getIscode());
			Prest.setInt(5,newbean.getIbcode());
			Prest.setString(6,newbean.getIinvno());
			Prest.setString(7,(Security.getdate(newbean.getIinvdate())));
			Prest.setDouble(8,newbean.getIamount());
			Prest.setInt(9,newbean.getIdcode());
			Prest.setString(10,newbean.getIyear());
			Prest.setString(11,newbean.getIdtype());
			Prest.setString(12,newbean.getIcrdeb());
			Prest.setString(13,newbean.getIpaid());
			Prest.setString(14,(Security.getdate(newbean.getIpaydate())));
			Prest.setString(15,newbean.getImode());
			Prest.setString(16,newbean.getIpaydet());
			Prest.setString(17,newbean.getIadd1());
			Prest.setString(18,newbean.getIadd2());
			
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
	public int findOrdInvUpdate(orderbean newbean) {
		log4jLogger.info("start===========findOrdInvUpdate=====");

		int count=0;
		try {
						 
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.ORDERSELECT_UPDATE);
			
			Prest.setInt(18,newbean.getImax());
			Prest.setString(1,newbean.getIord());
			Prest.setString(2,(Security.getdate(newbean.getIordate())));
			Prest.setInt(3,newbean.getIscode());
			Prest.setInt(4,newbean.getIbcode());
			Prest.setString(5,newbean.getIinvno());
			Prest.setString(6,(Security.getdate(newbean.getIinvdate())));
			Prest.setDouble(7,newbean.getIamount());
			Prest.setInt(8,newbean.getIdcode());
			Prest.setString(9,newbean.getIyear());
			Prest.setString(10,newbean.getIdtype());
			Prest.setString(11,newbean.getIcrdeb());
			Prest.setString(12,newbean.getIpaid());
			Prest.setString(13,(Security.getdate(newbean.getIpaydate())));
			Prest.setString(14,newbean.getImode());
			Prest.setString(15,newbean.getIpaydet());
			Prest.setString(16,newbean.getIadd1());
			Prest.setString(17,newbean.getIadd2());
			
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
	public int findOrdInvDelete(String name) {
		log4jLogger.info("start===========findOrdInvDelete=====");

		int count=0;
		try {
						 
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.ORDERSELECT_DELETE);			
			Prest.setString(1,name);			
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
	
	
	
	
	
	/**
	 *
	 *start Indent Master Form From Here.
	 * 
	 */
	
	public List findSaveIndentMas(List details)
	{
		log4jLogger.info("Inside ====== findSaveIndentMas ========");   			
		
		List<Object> Indent=null;
		
		for(int i=0;i<details.size();i++)
		{
			Indent = new ArrayList<Object>();			
			IndentMasDetailsBean detailsbean=(IndentMasDetailsBean) details.get(i);
			
			           getSession().save(detailsbean);
			           getSession().flush();
			           getSession().clear();			
		}
		
		log4jLogger.info("End ====== findSaveIndentMas ========");		
		return Indent;
		
	}
	
	
   public List findIndentMasSearch(IndentMasDetailsBean newbean) {
		
		log4jLogger.info("start===========findIndentMasSearch====="+newbean.getFlag());
		SQLQuery sql = null;
					
		if ((newbean.getFlag().equals("Pub")))
		{
			if (newbean.getMemname() == "") {
				
			    sql = getSession().createSQLQuery(DataQuery.PUBSUBSEARCH_STRING_PUB);
			
			}else{
				
				sql = getSession().createSQLQuery(DataQuery.PUBSUBSEARCH_STRING_LIKE_PUB
						
						+newbean.getMemname() + "%' and sp_type='PUBLISHER' order by sp_name");				
			}
		}		
		
		
		if ((newbean.getFlag().equals("Member")))
		{			
           if (newbean.getMemname() == "") {
				
           	sql = getSession().createSQLQuery(DataQuery.MEMBERINDENTSELECT_STRING+" ORDER BY member_name ");
			
			}else{
				
				sql = getSession().createSQLQuery(DataQuery.MEMBERINDENTSELECT_STRING+" where member_name like '"
						
						+newbean.getMemname() + "%' ORDER BY member_name");				
			}			 
		}
		
		
		if ((newbean.getFlag().equals("Dept")))
		{			
           if (newbean.getMemname() == "") {
				
           	sql = getSession().createSQLQuery(DataQuery.DEPTSEARCH_NAME);
			
			}else{
				
				sql = getSession().createSQLQuery(DataQuery.DEPTSEARCH_NAME_LIKE
						
						+newbean.getMemname() + "%' ORDER BY Dept_Name");				
			}			 
		}
		
		
		if ((newbean.getFlag().equals("Author")))
		{			
           if (newbean.getMemname() == "") {
				
           	sql = getSession().createSQLQuery(DataQuery.AUTHORSEARCH_NAME);
			
			}else{
				
				sql = getSession().createSQLQuery(DataQuery.AUTHORSEARCH_NAME_LIKE
						
						+newbean.getMemname() + "%' ORDER BY Author_Name");				
			}			 
		}
		
		if ((newbean.getFlag().equals("Title")))
		{			
           if (newbean.getMemname() == "") {
				
           	sql = getSession().createSQLQuery(DataQuery.Indent_Distinct_Title+" ORDER BY title LIMIT 100");
			
			}else{
				
				sql = getSession().createSQLQuery(DataQuery.Indent_Distinct_Title+" where title like '"
						
						+newbean.getMemname() + "%' ORDER BY title");				
			}			 
		}
		
		if ((newbean.getFlag().equals("IndentNo")))
		{			
           if (newbean.getMemname() == "") {
				
           	sql = getSession().createSQLQuery(DataQuery.Indent_List+" GROUP BY Member_Code,Member_Name,Indent_No ORDER BY Indent_No DESC");
			
			}else{
				
				sql = getSession().createSQLQuery(DataQuery.Indent_List+" where Member_Name like '"
						
						+newbean.getMemname() + "%' GROUP BY Member_Code,Member_Name,Indent_No ORDER BY Indent_No DESC");				
			}			
		}		
		
		return  sql.list();

   }
	
   
   
   public IndentMasDetailsBean findTitleNo()   {
   	log4jLogger.info("start===========findTitleNo=====");
   	IndentMasDetailsBean beanobject=new IndentMasDetailsBean();
   	
   /*	Done this one by RK but when the table is empty gives error
   	Criteria criteria = getSession().createCriteria(IndentMasDetailsBean.class)
       .setProjection(Projections.max("titleNo"));
       //Integer maxTitleNo = (Integer)criteria.uniqueResult();  */
       
       int maxTitleNo=0;        
       try {
       	
          con=getSession().connection();
          Prest=con.prepareStatement(DataQuery.Indent_Title_No);
          rs=Prest.executeQuery();
          
          if(rs.next())
          {  
       	   maxTitleNo=rs.getInt("Title_No");        	   
          }
       }catch(Exception e)  {        	
       	e.printStackTrace();
       }
       
       beanobject.setTitleNo(maxTitleNo+1);
   
       log4jLogger.info("End===========findTitleNo====="+beanobject.getTitleNo());
   	return beanobject;
   }
   
   public List findFullViewIndentMas(String indentno)    {    	
   	log4jLogger.info("Start===========findFullViewIndentMas====="+indentno);
   	
   	String query = getSession().getNamedQuery("IndentMasFullView").getQueryString();
		SQLQuery sql = getSession().createSQLQuery(query);
		sql.setParameter("indtno", indentno);
				
		List list = sql.list();
		List<Object> finalResults = null;
		if (list != null)
		{			
			finalResults = new ArrayList<Object>();
			for (int i = 0; i < list.size(); i++)
			{				
				Object[] obj = (Object[]) list.get(i);
				IndentMasDetailsBean IndentDetails = new IndentMasDetailsBean();
				
				IndentDetails.setIndtno(obj[0].toString());
				IndentDetails.setTitlestatus(obj[1].toString());
				IndentDetails.setIndtdate(Security.getdate(obj[2].toString()));
				IndentDetails.setMemcode(obj[3].toString());
				IndentDetails.setMemname(obj[4].toString());
				IndentDetails.setIndtstatus(obj[5].toString());
				
				IndentDetails.setTitleNo(Integer.parseInt(obj[6].toString()));				
				IndentDetails.setTitle(obj[7].toString());
				IndentDetails.setNoofcopy(Integer.parseInt(obj[8].toString()));	
				IndentDetails.setAuthor(obj[9].toString());
				IndentDetails.setAuthorcode(obj[10].toString());
				IndentDetails.setPublisher(obj[11].toString());
				IndentDetails.setPubcode(Integer.parseInt(obj[12].toString()));	
				IndentDetails.setDepartment(obj[13].toString());
				IndentDetails.setDeptcode(Integer.parseInt(obj[14].toString()));	
				IndentDetails.setIsbn(obj[15].toString());
				IndentDetails.setYearpub(Integer.parseInt(obj[16].toString()));
				IndentDetails.setEdition(obj[17].toString());
				
				IndentDetails.setBcurrency(obj[18].toString());
				IndentDetails.setBcost(Double.parseDouble(obj[19].toString()));
				IndentDetails.setBprice(Double.parseDouble(obj[20].toString()));
				IndentDetails.setDiscount(Double.parseDouble(obj[21].toString()));
				IndentDetails.setNetamount(Double.parseDouble(obj[22].toString()));
				
				finalResults.add(IndentDetails);				
			}
		}
		log4jLogger.info("End===========findFullViewIndentMas=====");
		return finalResults;
   	
   }
   
   public void findDeleteIndentMas(String indentno)   {
   	
   	log4jLogger.info("Start===========findDeleteIndentMas=====");
   	
   	String query = getSession().getNamedQuery("DeleteIndentMas").getQueryString();
		SQLQuery sql = getSession().createSQLQuery(query);
		sql.setParameter("indtno", indentno);
		sql.executeUpdate();        	
   	
   	log4jLogger.info("End===========findDeleteIndentMas=====");
   }
   
   
   
   
   /**
    * 
    * Start Indent Approval Form
    * 
    */    
   
   
   public List findFullViewIndentApproval(String indentno)    {    	
   	log4jLogger.info("Start===========findFullViewIndentApproval====="+indentno);
   	
   	SQLQuery   sql = getSession().createSQLQuery(DataQuery.Indent_Approve_Select);
		sql.setParameter("indtno", indentno);
				
		List list = sql.list();
		List<Object> finalResults = null;
		if (list != null)
		{			
			finalResults = new ArrayList<Object>();
			for (int i = 0; i < list.size(); i++)
			{				
				Object[] obj = (Object[]) list.get(i);
				IndentApproveBean IndentDetails = new IndentApproveBean();
				
				IndentDetails.setIndtno(obj[0].toString());
				IndentDetails.setTitlestatus(obj[1].toString());
				IndentDetails.setIndtdate(Security.getdate(obj[2].toString()));
				
				if(!(obj[3]!=null) && !(obj[3]!=""))  {				
				 IndentDetails.setApprvedate(Security.getdate(obj[3].toString()));
				}
				
				IndentDetails.setMemcode(obj[4].toString());
				IndentDetails.setMemname(obj[5].toString());
				IndentDetails.setIndtstatus(obj[6].toString());
				
				IndentDetails.setTitleNo(Integer.parseInt(obj[7].toString()));				
				IndentDetails.setTitle(obj[8].toString());
				IndentDetails.setNoofcopy(Integer.parseInt(obj[9].toString()));	
				IndentDetails.setAuthor(obj[10].toString());
				IndentDetails.setAuthorcode(obj[11].toString());
				
				IndentDetails.setApprvecopy(Integer.parseInt(obj[12].toString()));
				IndentDetails.setPendingcopy(Integer.parseInt(obj[13].toString()));	
								
				finalResults.add(IndentDetails);				
			}
		}
		log4jLogger.info("End===========findFullViewIndentApproval=====");
		return finalResults;
   	
   }

   
   
   public void findIndentApproval(List Details)       {
   	log4jLogger.info("Start===========findIndentApproval=====");
		
   	
			for(int i=0;i<Details.size();i++)
			{				
				
				IndentApproveBean detailsbean=(IndentApproveBean) Details.get(i);				
		    	SQLQuery   sql = getSession().createSQLQuery(DataQuery.Indent_Approve_Update);
		    	 
				 sql.setParameter("appDate",detailsbean.getApprvedate());
				 sql.setParameter("appCopy",detailsbean.getApprvecopy());
				 sql.setParameter("pndCopy",detailsbean.getPendingcopy());
				 
				 sql.setParameter("titleno",detailsbean.getTitleNo());
				 sql.setParameter("indtno",detailsbean.getIndtno());
				 
				 sql.executeUpdate();
			   
			}			
  	
   	log4jLogger.info("End===========findIndentApproval=====");
   	
   }
   
   public void findIndentNotApproval(List Details)    {
       log4jLogger.info("Start===========findIndentNotApproval=====");
       
       for(int i=0;i<Details.size();i++)
		{				
			
			IndentApproveBean detailsbean=(IndentApproveBean) Details.get(i);				
	    	SQLQuery   sql = getSession().createSQLQuery(DataQuery.Indent_NotApprove_Update);
	    	 
			 sql.setParameter("appDate",detailsbean.getApprvedate());
			 sql.setParameter("appCopy",detailsbean.getApprvecopy());
			 sql.setParameter("pndCopy",detailsbean.getPendingcopy());
			 
			 sql.setParameter("titleno",detailsbean.getTitleNo());
			 sql.setParameter("indtno",detailsbean.getIndtno());
			 
			 sql.executeUpdate();
		   
		}
   	
   	log4jLogger.info("End===========findIndentNotApproval=====");    	
   	
   }
   
   
   
   /**
    * 
    * Start Indent Order Form
    * 
    */    
   
       
   public List findOrdIndentSearchName(IndentOrderDetailsBean newbean)  {
   	log4jLogger.info("start===========findOrdIndentSearchName====="+newbean.getAdd1());
		SQLQuery sql = null;
				
		if ((newbean.getAdd1().equals("Sup")))
		{
			if (newbean.getTitle() == "") {
				
			    sql = getSession().createSQLQuery(DataQuery.PUBSUBSEARCH_STRING_SUP);
			
			}else{
				
				sql = getSession().createSQLQuery(DataQuery.PUBSUBSEARCH_STRING_LIKE_SUP
						
						+newbean.getTitle() + "%' and sp_type='SUPPLIER' order by sp_name");
				
			}
		}
		
		if ((newbean.getAdd1().equals("IndentNo")))
		{			
           if (newbean.getTitle() == "") {
				
           	sql = getSession().createSQLQuery(DataQuery.Indent_List+" GROUP BY Member_Code,Member_Name,Indent_No ORDER BY Indent_No DESC");
			
			}else{
				
				sql = getSession().createSQLQuery(DataQuery.Indent_List+" where Member_Name like '"
						
						+newbean.getTitle() + "%' GROUP BY Member_Code,Member_Name,Indent_No ORDER BY Indent_No DESC");				
			}			
		}	
		
		if ((newbean.getAdd1().equals("OrderNo")))
		{			
           if (newbean.getTitle() == "") {
				
           	sql = getSession().createSQLQuery(DataQuery.Indent_OrderNo+" GROUP BY Order_No ORDER BY Order_No DESC");
			
			}else{
				
				sql = getSession().createSQLQuery(DataQuery.Indent_OrderNo+" and Order_No like '"
						
						+newbean.getTitle() + "%' GROUP BY Order_No ORDER BY Order_No DESC");				
			}			
		}
		
   	
   	return sql.list();
   }
   
   
   public List findOrdSelectedIndent(IndentOrderDetailsBean newbean)  {
   	log4jLogger.info("start===========findOrdSelectedIndent====="+newbean.getIndentno());

	     SQLQuery sql = null;		
		
		 sql = getSession().createSQLQuery(DataQuery.Indent_Order_CheckList+" and Indent_No in(null"+newbean.getIndentno()+")");
		 
		 List list = sql.list();

   	
		 List<Object> finalResults = null;
			if (list != null)
			{			
				finalResults = new ArrayList<Object>();
				for (int i = 0; i < list.size(); i++)
				{				
					Object[] obj = (Object[]) list.get(i);
					IndentOrderDetailsBean ordDetails = new IndentOrderDetailsBean();
					
					ordDetails.setTitleno(Integer.valueOf(obj[0].toString()));
					ordDetails.setTitle(obj[1].toString());
					ordDetails.setAuthor(obj[2].toString());
					ordDetails.setApprvoedcopy(Integer.valueOf(obj[3].toString()));
					ordDetails.setIndentno(obj[4].toString());
										
					ordDetails.setOrdno(newbean.getOrdno());
					ordDetails.setOrddate(newbean.getOrddate());
					ordDetails.setQuoteno(newbean.getQuoteno());
					ordDetails.setQuotedate(newbean.getQuotedate());
					ordDetails.setSupcode(newbean.getSupcode());
					ordDetails.setSupplier(newbean.getRemarks());
											
					finalResults.add(ordDetails);				
				}
			}
			log4jLogger.info("End===========findOrdSelectedIndent=====");
			return finalResults;	    	
   }
   
   
   public List findOrdIndentMasSave(List details,IndentOrderDetailsBean newbean) 
   {
   	log4jLogger.info("Inside ====== findOrdJNLDetailsSave ========");   	
   	
   	List<Object> user = null;
   	IndentOrderDetailsBean ob=new IndentOrderDetailsBean();
   	
   	try {
   		
       if(OrdIndentMasCheck(newbean))
       {                
		 for (int i = 0; i < details.size(); i++)
		 {
			user = new ArrayList<Object>();
			IndentOrderDetailsBean detailsbean=(IndentOrderDetailsBean) details.get(i);
			
					getSession().save(detailsbean);
					getSession().flush();
					getSession().clear();				
					
					String query = getSession().getNamedQuery("UpdateOrderIndentMas").getQueryString();
					SQLQuery sql = getSession().createSQLQuery(query);
					sql.setParameter("titleno", detailsbean.getTitleno());
					sql.setParameter("indtno", detailsbean.getIndentno());
					sql.executeUpdate();   					
	     }        		
       }
   	else {
   		ob.setAdd1("AlreadyExists");
   		user.add(ob);
   	}
   	
   	}catch(Exception e)
   	{    		
   		e.printStackTrace();
   	}
		
		log4jLogger.info("End ====== findOrdJNLDetailsSave ========");
		
	  return user;
	}
       
   public boolean OrdIndentMasCheck(IndentOrderDetailsBean newbean)
   {    	
   	boolean check=false;
   	
   	if (newbean.getOrdno() != null)
		{
   		log4jLogger.info("start ====== OrdIndentMasCheck ========"+newbean.getOrdno());
   		Criteria crit = getSession().createCriteria(IndentOrderDetailsBean.class);
			crit.add(Restrictions.eq("ordno", newbean.getOrdno()));
			List list = crit.list();
			if (list == null || list.size() == 0)
			{
				check = true;
			}  
			log4jLogger.info("End ====== OrdIndentMasCheck ========"+newbean.getOrdno());
		}
   	
   	return check;
   }

   public List findOrdIndentFullView(String OrderNo)   {
   	log4jLogger.info("start===========findOrdIndentFullView====="+OrderNo);
   	
   	String query = getSession().getNamedQuery("IndentOrderFullView").getQueryString();
		SQLQuery sql = getSession().createSQLQuery(query);
		sql.setParameter("ordno", OrderNo);
				
		List list = sql.list();
		List<Object> finalResults = null;
		if (list != null)
		{			
			finalResults = new ArrayList<Object>();
			for (int i = 0; i < list.size(); i++)
			{				
				Object[] obj = (Object[]) list.get(i);
				IndentOrderDetailsBean ordDetails = new IndentOrderDetailsBean();
				
				ordDetails.setOrdno(obj[0].toString());
				ordDetails.setOrddate(Security.getdate(obj[1].toString()));
				ordDetails.setQuoteno(obj[2].toString());
				ordDetails.setQuotedate(Security.getdate(obj[3].toString()));
				ordDetails.setSupcode(Integer.parseInt(obj[4].toString()));
				ordDetails.setSupplier(obj[5].toString());
				
				ordDetails.setIndentno(obj[6].toString());
				ordDetails.setTitleno(Integer.parseInt(obj[7].toString()));
				ordDetails.setTitle(obj[8].toString());
				ordDetails.setAuthor(obj[9].toString());				
				ordDetails.setApprvoedcopy(Integer.parseInt(obj[10].toString()));
				
				ordDetails.setRemarks(obj[11].toString());
				ordDetails.setAdd1(obj[12].toString());
				ordDetails.setAdd2(obj[13].toString());					
				ordDetails.setTitlestatus(obj[14].toString());
								
				finalResults.add(ordDetails);				
			}
		}
		log4jLogger.info("End===========findOrdIndentFullView=====");
		return finalResults;   	
   }
   
   public void findIndentOrderDelete(String OrderNo,String TitleNo)  {
   	log4jLogger.info("start===========findIndentOrderDelete====="+OrderNo+"And"+TitleNo);
   	
	     SQLQuery sql = null;			
		 sql = getSession().createSQLQuery(DataQuery.Indent_OrderDelete_Update + " and Title_No in("+TitleNo+")");		 
		 sql.executeUpdate();
		 
		 log4jLogger.info("Title Status===========Updated Successfully=====");
		 
		 sql=null;
		 String query = getSession().getNamedQuery("DeleteIndentOrderDetail").getQueryString();
		 sql=getSession().createSQLQuery(query);
		 sql.setParameter("ordno",OrderNo);
		 sql.executeUpdate();		 
		 
		 log4jLogger.info("Record===========Deleted Successfully=====");
   	
   }
	
	
   
   /**
    * 
    * Start Indent Invoice Form
    * 
    */    
   
   
   
   public List findInvSearchOrdNo(IndentInvBean newbean){
   	log4jLogger.info("start===========findInvSearchOrdNo====="+newbean.getOrdNo()+" And "+newbean.getAdd1() +"And Flag="+newbean.getAdd2());
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
		     
		    
		     if(newbean.getAdd2().equals("InvoiceNo"))  {
		    	 
		    	   query = getSession().getNamedQuery("IndentInvNo").getQueryString();
		    	   
		     }else {
		    	 
		    	   query = getSession().getNamedQuery("IndentOrderRefNo").getQueryString();
		     }		     
			
		sql=getSession().createSQLQuery(query);
		sql.setParameter("ordno", ordno+"%");
		sql.setParameter("supplier", sup_name+"%");
		
	    return sql.list();
   }
   
   
   public List findInvOrdCheckList(IndentInvBean newbean)    {
   	
   	log4jLogger.info("start===========findInvOrdCheckList====="+newbean.getAdd1());		
	    SQLQuery sql = getSession().createSQLQuery(DataQuery.Indent_Inv_CheckList+" and Order_No in(null"+newbean.getAdd1()+") and Title_Status='ORDERED' order by Order_No DESC");
				
		List list = sql.list();
		List<Object> finalResults = null;
		if (list != null)
		{			
			finalResults = new ArrayList<Object>();
			for (int i = 0; i < list.size(); i++)
			{				
				Object[] obj = (Object[]) list.get(i);
				
				IndentInvDetailsBean ordDetails = new IndentInvDetailsBean();
				
				ordDetails.setInvoiceno(newbean.getInvoiceNo());
				ordDetails.setInvoicedate(newbean.getInvoicedate());				
				ordDetails.setTitleno(Integer.parseInt(obj[0].toString()));
				ordDetails.setTitle(obj[1].toString());
				ordDetails.setAuthor(obj[2].toString());
				ordDetails.setCopies(Integer.parseInt(obj[3].toString()));
				ordDetails.setOrdno(obj[4].toString());
				ordDetails.setSupplier(obj[5].toString());
				ordDetails.setSupcode(Integer.parseInt(obj[6].toString()));
				ordDetails.setIndentno(obj[7].toString());
				
				finalResults.add(ordDetails);				
			}
		}
		return finalResults;    	
   }
   
   
   public List findIndentInvoiceSave(List details,IndentInvBean newbean) 
   {
   	log4jLogger.info("Inside ====== findIndentInvoiceSave ========");   	
   	
   	List<Object> user = null;
   	IndentInvDetailsBean ob=new IndentInvDetailsBean();
   	
   	try {
   		
       if(IndentInvoiceCheck(newbean))
       {                
		 for (int i = 0; i < details.size(); i++)
		 {
			user = new ArrayList<Object>();
			IndentInvDetailsBean detailsbean=(IndentInvDetailsBean) details.get(i);
			
					getSession().save(detailsbean);
					getSession().flush();
					getSession().clear();				
					
					String query = getSession().getNamedQuery("InvoiceUpdateIndentMas").getQueryString();
					SQLQuery sql = getSession().createSQLQuery(query);
					sql.setParameter("titleno", detailsbean.getTitleno());					
					sql.executeUpdate();   					
	     }        		
       }
   	else {
   		ob.setAdd1("AlreadyExists");
   		user.add(ob);
   	}
   	
   	}catch(Exception e)
   	{    		
   		e.printStackTrace();
   	}
		
		log4jLogger.info("End ====== findIndentInvoiceSave ========");
		
	  return user;
	}
       
   public boolean IndentInvoiceCheck(IndentInvBean newbean)
   {    	
   	boolean check=false;
   	
   	if (newbean.getInvoiceNo() != null)
		{
   		log4jLogger.info("start ====== IndentInvoiceCheck ========"+newbean.getInvoiceNo());
   		Criteria crit = getSession().createCriteria(IndentInvDetailsBean.class);
			crit.add(Restrictions.eq("invoiceno", newbean.getInvoiceNo()));
			List list = crit.list();
			if (list == null || list.size() == 0)
			{
				check = true;
			}  
			log4jLogger.info("End ====== IndentInvoiceCheck ========"+newbean.getInvoiceNo());
		}
   	
   	return check;
   }

   public List findIndentInvoiceFullView(String InvoiceNo)   {
       log4jLogger.info("start===========findIndentInvoiceFullView====="+InvoiceNo);
   	
   	String query = getSession().getNamedQuery("IndentInvoiceFullView").getQueryString();
		SQLQuery sql = getSession().createSQLQuery(query);
		sql.setParameter("invno", InvoiceNo);
				
		List list = sql.list();
		List<Object> finalResults = null;
		if (list != null)
		{			
			finalResults = new ArrayList<Object>();
			for (int i = 0; i < list.size(); i++)
			{				
				Object[] obj = (Object[]) list.get(i);
				IndentInvDetailsBean invDetails = new IndentInvDetailsBean();
				
				invDetails.setInvoiceno(obj[0].toString());
				invDetails.setInvoicedate(Security.getdate(obj[1].toString()));
				invDetails.setSupcode(Integer.parseInt(obj[2].toString()));
				invDetails.setSupplier(obj[3].toString());
				invDetails.setTitleno(Integer.parseInt(obj[4].toString()));
				invDetails.setTitle(obj[5].toString());
				invDetails.setAuthor(obj[6].toString());				
				invDetails.setCopies(Integer.parseInt(obj[7].toString()));	
				
				invDetails.setBcurrency(obj[8].toString());
				invDetails.setBcost(Double.parseDouble(obj[9].toString()));
				invDetails.setBprice(Double.parseDouble(obj[10].toString()));
				invDetails.setDiscount(Double.parseDouble(obj[11].toString()));
				invDetails.setAmount(Double.parseDouble(obj[12].toString()));
				
				invDetails.setIndentno(obj[13].toString());
				invDetails.setOrdno(obj[14].toString());
				
				invDetails.setRemarks(obj[15].toString());
				invDetails.setAdd1(obj[16].toString());
				invDetails.setAdd2(obj[17].toString());		

				invDetails.setFlag(obj[18].toString());	
				
				finalResults.add(invDetails);				
			}
		}
		log4jLogger.info("End===========findIndentInvoiceFullView=====");
		return finalResults;   	   	
   }
   
   public void findIndentInvoiceDelete(String InvoiceNo,String TitleNo)   {
   	log4jLogger.info("start===========findIndentInvoiceDelete====="+InvoiceNo+"And"+TitleNo);
   	
	     SQLQuery sql = null;			
		 sql = getSession().createSQLQuery(DataQuery.Indent_Inv_DeleteUpdate + " and Title_No in("+TitleNo+")");		 
		 sql.executeUpdate();
		 
		 log4jLogger.info("Title Status===========Updated Successfully=====");
		 
		 sql=null;
		 String query = getSession().getNamedQuery("DeleteIndentInvoiceDetail").getQueryString();
		 sql=getSession().createSQLQuery(query);
		 sql.setParameter("invno",InvoiceNo);
		 sql.executeUpdate();		 
		 
		 log4jLogger.info("Record===========Deleted Successfully=====");
   	
   }
   
   
   
   /**
    * 
    * Start Indent Payment Form
    * 
    */    
   
   
	public IndentPaymentBean findPaymentMax()  {
		log4jLogger.info("start===========IndentfindPaymentMax=====");
		IndentPaymentBean newbean = new IndentPaymentBean();
		
      try {
			
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.Indent_Payment_MAX);
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
	
	
	public List findPaymentIndentSearch(IndentPaymentBean newbean)  
	{   	
	    	log4jLogger.info("start===========findPaymentIndentSearch====="+newbean.getInvNo());
	    	String query ="";
	    	List list = null;
	    	
	    	if(newbean.getAdd2().equals("PaymentNo"))
	    	{
	    		query = getSession().getNamedQuery("IndentNoSearch").getQueryString();	
	    		SQLQuery sql = getSession().createSQLQuery(query);
	    		sql.setParameter("invno", newbean.getInvNo()+"%");	
	    		list = sql.list();
	    		
	    	}else {
	    		
	    		query = getSession().getNamedQuery("IndentInvRefNo").getQueryString();
	    		Query sql = getSession().createQuery(query);
	    		sql.setParameter("invno", newbean.getInvNo()+"%");	
	    		list = sql.list();
	    	}
			
			List<Object> finalResults = null;
			if (list != null)
			{			
				finalResults = new ArrayList<Object>();
				for (int i = 0; i < list.size(); i++)
				{				
					Object[] obj = (Object[]) list.get(i);
					IndentPaymentBean pmtDetails = new IndentPaymentBean();
					
					pmtDetails.setInvNo(obj[0].toString());
					pmtDetails.setSupplier(obj[1].toString());				
					pmtDetails.setNetamount(Double.parseDouble(obj[2].toString()));	
					
					finalResults.add(pmtDetails);				
				}
			}
			log4jLogger.info("End===========findPaymentIndentSearch=====");
			return finalResults;
	 }

	
	public List findPaymentIndentInvoice(IndentPaymentBean newbean)   
	{
		
		log4jLogger.info("start===========findPaymentIndentInvoice====="+newbean.getAdd1());

	     SQLQuery sql = null;					
		 sql = getSession().createSQLQuery(DataQuery.Indent_Invoice_CheckList+" and invoice_no in(null"+newbean.getAdd1()+")");			 
		 List list = sql.list();
		 
			List<Object> finalResults = null;
			if (list != null)
			{			
				finalResults = new ArrayList<Object>();
				for (int i = 0; i < list.size(); i++)
				{				
					Object[] obj = (Object[]) list.get(i);
					IndentPaymentBean PaymentDetails = new IndentPaymentBean();
					
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
			log4jLogger.info("End===========findPaymentIndentInvoice=====");
			return finalResults;
		
		
	}

	public void findPaymentIndentDetailsUpdate(List details)  
	{
		log4jLogger.info("Inside ====== findPaymentIndentDetailsUpdate ========");   
					
		try {

			String query = getSession().getNamedQuery("IndentInvoiceUpdate").getQueryString();
			SQLQuery sql = getSession().createSQLQuery(query);	
   	
		for (int i = 0; i < details.size(); i++)
		{
			IndentPaymentBean pmtbean=(IndentPaymentBean) details.get(i);
			
			sql.setParameter("pmtno", pmtbean.getPaymentno());
			sql.setParameter("invno",pmtbean.getInvNo());	
			sql.executeUpdate();
	    }
		
		}catch(Exception e)  {
			e.printStackTrace();
		}
		
		log4jLogger.info("End ====== findPaymentIndentDetailsUpdate ========");
					
				
	}
	
	public List findPaymentIndentMasSave(IndentPaymentBean newbean)
	{			
		log4jLogger.info("Inside ====== findPaymentIndentMasSave ========");
		
   	List<String> pmt=new ArrayList<String>();
   	try {
   		
   	if(IndentPaymentMasCheck(newbean))
   	{
   	 if(IndentInvoiceMasCheck(newbean))
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
   	    	
   	log4jLogger.info("End ====== findPaymentIndentMasSave ========");
   	
     return pmt;    	
		
		
	}
	
	 public boolean IndentPaymentMasCheck(IndentPaymentBean newbean)
	 {
	    	
	    	boolean check=false;
	    	
	    	if (newbean.getPaymentno() != 0)
			{
	    		Criteria crit = getSession().createCriteria(IndentPaymentBean.class);
				crit.add(Restrictions.eq("paymentno", newbean.getPaymentno()));
				List list = crit.list();
				if (list == null || list.size() == 0)
				{
					check = true;
				}    		
			}
	    	
	    	return check;
	 }
	 
	 public boolean IndentInvoiceMasCheck(IndentPaymentBean newbean)
	 {
	    	
	    	boolean check=false;
	    	
	    	if (newbean.getInvNo() != null)
			{
	    		SQLQuery sql = getSession().createSQLQuery(DataQuery.Indent_CheckPaidInvoice+" and invoice_no IN(null"+newbean.getInvNo()+")");					
	    		
	    		List list = sql.list();
				if (list == null || list.size() == 0)
				{
					check = true;
				}
			}
	    	
	    	return check;
	 }
	 
	 
	 
	 public List findPaymentIndentDetailsSearch(String pmtno)
	 {
		 log4jLogger.info("start===========findPaymentIndentDetailsSearch====="+pmtno);
		 
	    	String query = getSession().getNamedQuery("IndentPaymentFullView").getQueryString();
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
					IndentPaymentBean  pmtDetails = new IndentPaymentBean();
					
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
			log4jLogger.info("End===========findPaymentIndentDetailsSearch=====");
			return finalResults;			 
	 }
	 
	 public void findPaymentIndentDelete(String pmtno)  {
		    log4jLogger.info("Start===========findPaymentIndentDelete====="+pmtno);
	    	
		        try {
		    	SQLQuery sql1;
		    	String rk = getSession().getNamedQuery("IndentInvoiceDeleteUpdate").getQueryString();
		    	
	    		con=getSession().connection();
	    		Prest=con.prepareStatement(DataQuery.Indent_Invoice_DelUpdate);
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
		    
	    	String query = getSession().getNamedQuery("DeleteIndentPaymentMas").getQueryString();
			SQLQuery sql = getSession().createSQLQuery(query);
			sql.setParameter("pmtno", pmtno);
			sql.executeUpdate();    	
	    	
			log4jLogger.info("===========findPaymentIndentDelete SuccessFully=====");
		 
	 }
	
	
	
	
}