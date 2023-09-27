package Lib.Auto.Report;
import java.sql.ResultSet;

public  class reportbean   {
	
    static java.util.ArrayList al=null;
	static ResultSet   Dept_al=null;
	static ResultSet   Group_al=null;
	static ResultSet   Course_al=null;
	
	
	private  String ucode=null;
	private  String uname=null;
	private  String accno=null;
	private  String title=null;
	private  String authorName=null;
	private  String issueDate=null;
	private  String dueDate=null;
	private  String returnDate=null;
	private  String docType=null;
	private  String staffCode=null;
	private String fineAmount=null;
	private int billNo;
	private String paymode="";
	
	/**
	 * @return
	 */
	public java.util.ArrayList getAl() {
		return al;
	}

	/**
	 * @return
	 */
	public ResultSet getCourse_al() {
		return Course_al;
	}

	/**
	 * @return
	 */
	public ResultSet getDept_al() {
		return Dept_al;
	}

	/**
	 * @return
	 */
	public ResultSet getGroup_al() {
		return Group_al;
	}

	/**
	 * @param list
	 */
	public void setAl(java.util.ArrayList list) {
		al = list;
	}

	/**
	 * @param set
	 */
	public void setCourse_al(ResultSet set) {
		Course_al = set;
	}

	/**
	 * @param set
	 */
	public void setDept_al(ResultSet set) {
		Dept_al = set;
	}

	/**
	 * @param set
	 */
	public void setGroup_al(ResultSet set) {
		Group_al = set;
	}
	
	public String getUcode() {
		return ucode;
	}

	public void setUcode(String ucode) {
		this.ucode = ucode;
	}
	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getAccno() {
		return accno;
	}

	public void setAccno(String accno) {
		this.accno = accno;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public String getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(String issueDate) {
		this.issueDate = issueDate;
	}

	public String getDueDate() {
		return dueDate;
	}

	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}

	public String getDocType() {
		return docType;
	}

	public void setDocType(String docType) {
		this.docType = docType;
	}

	public String getStaffCode() {
		return staffCode;
	}

	public void setStaffCode(String staffCode) {
		this.staffCode = staffCode;
	}
	public String getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(String returnDate) {
		this.returnDate = returnDate;
	}
	public String getFineAmount() {
		return fineAmount;
	}

	public void setFineAmount(String fineAmount) {
		this.fineAmount = fineAmount;
	}

	public int getBillNo() {
		return billNo;
	}

	public void setBillNo(int billNo) {
		this.billNo = billNo;
	}
	public String getPaymode() {
		return paymode;
	}

	public void setPaymode(String paymode) {
		this.paymode = paymode;
	}
   	}
