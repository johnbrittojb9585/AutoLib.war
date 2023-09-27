package Lib.Auto.Indent_Approval;

import java.io.Serializable;


public class IndentApproveBean implements Serializable {


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
    private int apprvecopy;
    
    
    private String indtno;
    private String indtdate;
    private String apprvedate;
    private String indtstatus;    
    private String doctype;
    
    
    private String memcode;
    private String memname;
    
    private String titlestatus;
    private String flag;

    
    private String approveBook;
    private String approveCancelBook;
    
    
    
    private java.util.ArrayList al=null;

	public java.util.ArrayList getAl() {
		return al;
	}

	public void setAl(java.util.ArrayList al) {
		this.al = al;
	}

	public int getApprvecopy() {
		return apprvecopy;
	}

	public void setApprvecopy(int apprvecopy) {
		this.apprvecopy = apprvecopy;
	}

	public String getApprvedate() {
		return apprvedate;
	}

	public void setApprvedate(String apprvedate) {
		this.apprvedate = apprvedate;
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

	public String getDoctype() {
		return doctype;
	}

	public void setDoctype(String doctype) {
		this.doctype = doctype;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
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

	public int getNoofcopy() {
		return noofcopy;
	}

	public void setNoofcopy(int noofcopy) {
		this.noofcopy = noofcopy;
	}

	public int getPendingcopy() {
		return pendingcopy;
	}

	public void setPendingcopy(int pendingcopy) {
		this.pendingcopy = pendingcopy;
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

	public String getApproveBook() {
		return approveBook;
	}

	public void setApproveBook(String approveBook) {
		this.approveBook = approveBook;
	}

	public String getApproveCancelBook() {
		return approveCancelBook;
	}

	public void setApproveCancelBook(String approveCancelBook) {
		this.approveCancelBook = approveCancelBook;
	}

    
            
}