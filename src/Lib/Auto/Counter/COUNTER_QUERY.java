/*
 * Created on Nov 21, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package Lib.Auto.Counter;


/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public interface COUNTER_QUERY {
	/**
	 * @author SELECT_MEMBER
	 *
	 * To change the template for this generated type comment go to
	 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
	 */
	 public static final String SELECT_MEMBER_MAS ="select member_code from member_mas  where member_code=?";
		
	public static final String SELECT_MEMBER =	"select * from counter_member  where member_code=?";
	/**
		 * @author SELECT_ISSUE_MEMBER
		 *
		 * To change the template for this generated type comment go to
		 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
		 */
	public static final String SELECT_ISSUE_MEMBER ="select * from issuedbooks where member_code=?";
	/**
		 * @author SELECT_BOOKCOUNT
		 *
		 * To change the template for this generated type comment go to
		 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
		 */
	public static final String SELECT_BOOKCOUNT ="select count(*) as cnt from issue_mas where member_code=? and doc_type=?";
	/**
		 * @author SELECT_BOOK
		 *
		 * To change the template for this generated type comment go to
		 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
		 */
	public static final String SELECT_BOOK ="select * from counter_book_select where access_no=?";
	/**
		 * @author SELECT_ISSUEMAS_ONLY
		 *
		 * To change the template for this generated type comment go to
		 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
		 */
	public static final String SELECT_ISSUEMAS_ONLY ="select * from issue_mas where  member_code=? and access_no=?";
	
	
	public static final String SELECT_RESERVE_PRI =	"select min(id) as mm from reserve_mas as a,member_mas as b where access_no=? and a.member_code=b.member_code";
	
	public static final String SELECT_RESERVE_PRI1 ="select a.member_code,a.id,b.member_name from reserve_mas as a,member_mas as b where access_no=? and a.member_code=b.member_code order by id";
	
	
	
	public static final String DELETE_RESERVE_MAS =	"delete from reserve_mas where member_code=? and access_no=?";
	public static final String valid_date ="select datediff(?,?) as no_of_days";
	
	
	/**
		 * @author SELECT_FINEMAS
		 *
		 * To change the template for this generated type comment go to
		 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
		 */
	public static final String SELECT_FINEMAS ="select * from fine_mas where group_code=? order by fine_id";
	/**
		 * @author UPDATE_ISSUEMAS
		 *
		 * To change the template for this generated type comment go to
		 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
		 */
	public static final String UPDATE_ISSUEMAS ="update issue_mas set due_date=?,issue_date=?,issue_type=?,staff_code=? where member_code=? and access_no=?";
	
	public static final String DELETE_RENEWAL =	"delete from renewal_time where access_no=? and member_code=?";
	/**
		 * @author INSERT_HISTORY
		 *
		 * To change the template for this generated type comment go to
		 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
		 */
	public static final String INSERT_HISTORY =	"insert into issue_history(member_code,access_no,issue_date,due_date,return_date,fine_amount,staff_code,doc_type) values (?,?,?,?,?,?,?,?)";
	/**
		 * @author DELETE_ISSUEMAS
		 *
		 * To change the template for this generated type comment go to
		 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
		 */
	public static final String DELETE_ISSUEMAS ="delete from issue_mas where member_code=?and access_no=?";
	/**
		 * @author SELECT_BOOKMAS
		 *
		 * To change the template for this generated type comment go to
		 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
		 */

	public static final String SELECT_BOOKMAS =	"select availability from book_mas where access_no=?";
	/**
		 * @author INSERT_STRING
		 *
		 * To change the template for this generated type comment go to
		 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
		 */

	public static final String INSERT_STRING =
		"insert into issue_mas (member_code,access_no,issue_date,due_date,issue_type,staff_code,doc_type) values (?,?,?,?,?,?,?)";
	/**
		 * @author SELECT_ISSUEMAS
		 *
		 * To change the template for this generated type comment go to
		 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
		 */

	public static final String SELECT_ISSUEMAS ="select issue_mas.member_code,issue_mas.issue_date,issue_mas.due_date,issue_mas.issue_type,issue_mas.Doc_Type,member_mas.group_code from issue_mas,member_mas where issue_mas.member_code=member_mas.member_code and  access_no=?";
	/**
		 * @author SELECT_RETURN_BOOK
		 *
		 * To change the template for this generated type comment go to
		 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
		 */
		 public static final String SELECT_Issuemas ="select availability,document from book_mas where access_no=?";
			

	public static final String SELECT_RETURN_BOOK =	"select a.* ,b.availability from issue_mas as a,book_mas as b where a.access_no=? and a.member_code=? and a.access_no=b.access_no";
	/**
		 * @author SELECT_RESERVEMAS
		 *
		 * To change the template for this generated type comment go to
		 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
		 */

	public static final String SELECT_RESERVEMAS =	"select * from reserve_mas where member_code=?and access_no=?";
	/**
		 * @author DELETE_RESERVEMAS
		 *
		 * To change the template for this generated type comment go to
		 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
		 */
	
	public static final String DELETE_RESERVEMAS =
		"delete from reserve_mas where member_code=?and access_no=?";
	/**
		 * @author INSERT_RESERVEMAS
		 *
		 * To change the template for this generated type comment go to
		 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
		 */

	public static final String INSERT_RESERVEMAS ="insert into reserve_mas (id,member_code,access_no,doc_type,res_date) values (?,?,?,?,?)";
	


	public static final String  CLEAR_PAGE1="/AutoLib/Journal_Issues/index.jsp";
	public static final String  CLEAR_PAGE="/AutoLib/Counter/index.jsp";
	public static final String  MEMBER_DISPLAY="/Counter/index.jsp?flag=member&detils=ISSUEDEATILS";
	public static final String  MEMBER_NOTFOUND= "/AutoLib/Counter/index.jsp?Message=MemberNotFound";
	public static final String  MEMBER_DISPLAY_Issue="/Counter/index.jsp?flags=Issuemember";



}                                              
