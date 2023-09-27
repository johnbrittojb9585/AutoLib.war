package Lib.Auto.bulkTransfer_Books;

import java.util.ArrayList;

public class BookTransferBean {
	
	private int ordno=0;
	private String access_no="";
	private String toaccess_no="";
	public String getToaccess_no() {
		return toaccess_no;
	}
	public void setToaccess_no(String toaccess_no) {
		this.toaccess_no = toaccess_no;
	}
	private String title="";
	private String author="";
	private int deptcode=0;
	private String deptName="";
	private String document="";
	private String date="";
	private ArrayList al=null;
	private String avail="";
	
	private int branchCode;
	
	public int getordno() {
		return ordno;
	}
	public void setordno(int ordno) {
		this.ordno = ordno;
	}
	public String getAccess_no() {
		return access_no;
	}
	public void setAccess_no(String access_no) {
		this.access_no = access_no;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getDocument() {
		return document;
	}
	public void setDocument(String document) {
		this.document = document;
	}
	public int getdeptcode() {
		return deptcode;
	}
	public void setdeptcode(int deptcode) {
		this.deptcode = deptcode;
	}
	public String getdeptName() {
		return deptName;
	}
	public void setdeptName(String deptName) {
		this.deptName = deptName;
	}
	public ArrayList getAl() {
		return al;
	}
	public void setAl(ArrayList al) {
		this.al = al;
	}
	public String getAvail() {
		return avail;
	}
	public void setAvail(String avail) {
		this.avail = avail;
	}
	public int getBranchCode() {
		return branchCode;
	}
	public void setBranchCode(int branchCode) {
		this.branchCode = branchCode;
	}

}
