package Lib.Auto.Counter;

public class Receipt {
	
	private String name=null;
	private String code=null;
	private String accno=null;
	private String idate=null;
	private String ddate=null;
	private String title=null;
	private String document=null;
	private String status=null;
	private Double fineAmt;
	
	public String getAccno() {
		return accno;
	}
	public void setAccno(String accno) {
		this.accno = accno;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDdate() {
		return ddate;
	}
	public void setDdate(String ddate) {
		this.ddate = ddate;
	}	
	public Double getFineAmt() {
		return fineAmt;
	}
	public void setFineAmt(Double double1) {
		fineAmt = double1;
	}
	public String getIdate() {
		return idate;
	}
	public String getDocument() {
		return document;
	}
	public void setDocument(String doc) {
		this.document = doc;
	}	
	public void setIdate(String idate) {
		this.idate = idate;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
  
}
