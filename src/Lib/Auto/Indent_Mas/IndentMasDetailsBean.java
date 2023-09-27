package Lib.Auto.Indent_Mas;

import java.io.Serializable;


public class IndentMasDetailsBean implements Serializable {


	  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// ----------------------------------------------------- Instance Variables
    private int sno=0;
    private int max = 0;
    
    private int titleNo=0 ;
    private String title;
    private String author;
    private String authorcode;
    private int noofcopy;
    private int pendingcopy;
    private String isbn;
    private int yearpub;
    private String edition;
    private String indtno;
    private String indtdate;
    private String indtstatus;    
    private String doctype;
    
    private double bcost = 0;
    private String bcurrency;
    private double bprice = 0.0;
    private double discount = 0.0;
    private double netamount = 0.0;    
    
    private String memcode;
    private String memname;
    private int deptcode;
    private String department;
    private int pubcode;
    private String publisher;
    private String titlestatus;
    private String flag;

    private String addstatus="Pending";
    
    private java.util.ArrayList al=null;

	public java.util.ArrayList getAl() {
		return al;
	}

	public void setAl(java.util.ArrayList al) {
		this.al = al;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getAuthorcode() {
		return authorcode;
	}

	public void setAuthorcode(String authorcode) {
		this.authorcode = authorcode;
	}

	public double getBcost() {
		return bcost;
	}

	public void setBcost(double bcost) {
		this.bcost = bcost;
	}

	public String getBcurrency() {
		return bcurrency;
	}

	public void setBcurrency(String bcurrency) {
		this.bcurrency = bcurrency;
	}

	public double getBprice() {
		return bprice;
	}

	public void setBprice(double bprice) {
		this.bprice = bprice;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public int getDeptcode() {
		return deptcode;
	}

	public void setDeptcode(int deptcode) {
		this.deptcode = deptcode;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public String getDoctype() {
		return doctype;
	}

	public void setDoctype(String doctype) {
		this.doctype = doctype;
	}

	public String getEdition() {
		return edition;
	}

	public void setEdition(String edition) {
		this.edition = edition;
	}

	public String getIndtdate() {
		return indtdate;
	}

	public void setIndtdate(String indtdate) {
		this.indtdate = indtdate;
	}

	public String getIndtno() {
		return indtno;
	}

	public void setIndtno(String indtno) {
		this.indtno = indtno;
	}

	public String getIndtstatus() {
		return indtstatus;
	}

	public void setIndtstatus(String indtstatus) {
		this.indtstatus = indtstatus;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public int getMax() {
		return max;
	}

	public void setMax(int max) {
		this.max = max;
	}

	public String getMemcode() {
		return memcode;
	}

	public void setMemcode(String memcode) {
		this.memcode = memcode;
	}

	public String getMemname() {
		return memname;
	}

	public void setMemname(String memname) {
		this.memname = memname;
	}

	public double getNetamount() {
		return netamount;
	}

	public void setNetamount(double netamount) {
		this.netamount = netamount;
	}

	public int getNoofcopy() {
		return noofcopy;
	}

	public void setNoofcopy(int noofcopy) {
		this.noofcopy = noofcopy;
	}

	public int getPubcode() {
		return pubcode;
	}

	public void setPubcode(int pubcode) {
		this.pubcode = pubcode;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public int getSno() {
		return sno;
	}

	public void setSno(int sno) {
		this.sno = sno;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getTitleNo() {
		return titleNo;
	}

	public void setTitleNo(int titleNo) {
		this.titleNo = titleNo;
	}

	public String getTitlestatus() {
		return titlestatus;
	}

	public void setTitlestatus(String titlestatus) {
		this.titlestatus = titlestatus;
	}

	public int getYearpub() {
		return yearpub;
	}

	public void setYearpub(int yearpub) {
		this.yearpub = yearpub;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getAddstatus() {
		return addstatus;
	}

	public void setAddstatus(String addstatus) {
		this.addstatus = addstatus;
	}

	public int getPendingcopy() {
		return pendingcopy;
	}

	public void setPendingcopy(int pendingcopy) {
		this.pendingcopy = pendingcopy;
	}
    
        
}