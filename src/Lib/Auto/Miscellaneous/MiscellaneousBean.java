package Lib.Auto.Miscellaneous;

import java.io.Serializable;

public class MiscellaneousBean implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int mrno;
	private String tdate="";
	private String muserID="";
	private String mname="";
	private String Course="";
	private String thead="";
	private int quantity;
	
	private String tamount="";
	private String tremarks="";
	private String taddfield1="";
	private String slock="";
	private String staffCode="";
	public String getStaffCode() {
		return staffCode;
	}

	public void setStaffCode(String staffCode) {
		this.staffCode = staffCode;
	}

	public String getSlock() {
		return slock;
	}

	public void setSlock(String slock) {
		this.slock = slock;
	}

	public String getCyear() {
		return cyear;
	}

	public void setCyear(String cyear) {
		this.cyear = cyear;
	}
	private String cyear="";
	
	
	private int transNo;
	private int paymentNo;
	private String payFlag="";
	
    private static byte[] photo1=null;

    public void setPhoto1(byte[] ad) {
		photo1 = ad;
	}

	public byte[] getPhoto1() {
		return photo1;
	}
	
	
	
	
	
	public String getPayFlag() {
		return payFlag;
	}
	public void setPayFlag(String payFlag) {
		this.payFlag = payFlag;
	}
	public int getPaymentNo() {
		return paymentNo;
	}
	public void setPaymentNo(int paymentNo) {
		this.paymentNo = paymentNo;
	}
	public int getTransNo() {
		return transNo;
	}
	public void setTransNo(int transNo) {
		this.transNo = transNo;
	}
	public String getCourse() {
		return Course;
	}
	public void setCourse(String course) {
		Course = course;
	}
	public String getMname() {
		return mname;
	}
	public void setMname(String mname) {
		this.mname = mname;
	}
	public int getMrno() {
		return mrno;
	}
	public void setMrno(int mrno) {
		this.mrno = mrno;
	}
	public String getMuserID() {
		return muserID;
	}
	public void setMuserID(String muserID) {
		this.muserID = muserID;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getTaddfield1() {
		return taddfield1;
	}
	public void setTaddfield1(String taddfield1) {
		this.taddfield1 = taddfield1;
	}
	public String getTamount() {
		return tamount;
	}
	public void setTamount(String tamount) {
		this.tamount = tamount;
	}
	public String getTdate() {
		return tdate;
	}
	public void setTdate(String tdate) {
		this.tdate = tdate;
	}
	public String getThead() {
		return thead;
	}
	public void setThead(String thead) {
		this.thead = thead;
	}
	public String getTremarks() {
		return tremarks;
	}
	public void setTremarks(String tremarks) {
		this.tremarks = tremarks;
	}
	
	
	
	
	
	
	
}