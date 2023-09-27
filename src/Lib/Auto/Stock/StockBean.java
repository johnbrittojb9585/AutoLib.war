package Lib.Auto.Stock;

public class StockBean {
	
	private  int cmas=0;
	private  int cyes=0;
	private  int cmissing=0;
	private  int clost=0;
	private  int cwithdrawn=0;
	private  int cissued=0;
	private  int cverifyIssued=0;	
	private  int cbinding=0;
	private  int cdamaged=0;
	private  int ctransfer=0;
	
	private  int branchCode;    // For Titan
	
	private  String flag="";
	private  String doctype="";
	private  String accno="";
	private  String title="";
	private  String author="";
	private  String publisher="";
	private  String year="";
	private  String document="";
	private  String bprice="";
	private  String availability="";
	
	
	
	
	
	public String getavailability() {
		return availability;
	}
	public void setavailability(String availability) {
		this.availability = availability;
	}
	
	public String getbprice() {
		return bprice;
	}
	public void setbprice(String bprice) {
		this.bprice = bprice;
	}
	
	public String getdocument() {
		return document;
	}
	public void setdocument(String document) {
		this.document = document;
	}
	
	public String getyear() {
		return year;
	}
	public void setyear(String year) {
		this.year = year;
	}

	public String getpublisher() {
		return publisher;
	}
	public void setpublisher(String publisher) {
		this.publisher = publisher;
	}
	
	public String getauthor() {
		return author;
	}
	public void setauthor(String author) {
		this.author = author;
	}
	
	
	public String gettitle() {
		return title;
	}
	public void settitle(String title) {
		this.title = title;
	}
		
	
	public String getaccno() {
		return accno;
	}
	public void setaccno(String accno) {
		this.accno = accno;
	}
	
	public String getflag() {
		return flag;
	}
	public void setflag(String flag) {
		this.flag = flag;
	}
	
	public String getdoctype() {
		return doctype;
	}
	public void setdoctype(String doctype) {
		this.doctype = doctype;
	}
	
	public int getCbinding() {
		return cbinding;
	}
	public void setCbinding(int cbinding) {
		this.cbinding = cbinding;
	}
	
	public int getCdamaged() {
		return cdamaged;
	}
	public void setCdamaged(int cdamaged) {
		this.cdamaged = cdamaged;
	}
	public int getCissued() {
		return cissued;
	}
	public void setCissued(int cissued) {
		this.cissued = cissued;
	}
	public int getClost() {
		return clost;
	}
	public void setClost(int clost) {
		this.clost = clost;
	}
	public int getCmas() {
		return cmas;
	}
	public void setCmas(int cmas) {
		this.cmas = cmas;
	}
	public int getCtransfer() {
		return ctransfer;
	}
	public void setCtransfer(int ctransfer) {
		this.ctransfer = ctransfer;
	}
	public int getCyes() {
		return cyes;
	}
	public void setCyes(int cyes) {
		this.cyes = cyes;
	}
	public int getCmissing() {
		return cmissing;
	}
	public void setCmissing(int cmissing) {
		this.cmissing = cmissing;
	}
	public int getCwithdrawn() {
		return cwithdrawn;
	}
	public void setCwithdrawn(int cwithdrawn) {
		this.cwithdrawn = cwithdrawn;
	}
	public int getCverifyIssued() {
		return cverifyIssued;
	}
	public void setCverifyIssued(int cverifyIssued) {
		this.cverifyIssued = cverifyIssued;
	}
	public int getBranchCode() {
		return branchCode;
	}
	public void setBranchCode(int branchCode) {
		this.branchCode = branchCode;
	}

	

	
	
	
	}
