package Lib.Auto.Payment;

public class PaymentBean{
	
	private String mcode = "";
	private String mname = "";
	private String dept = "";
	private String course = "";
	private String paymentmode = "";

	public String getPaymentmode() {
		return paymentmode;
	}

	public void setPaymentmode(String paymentmode) {
		this.paymentmode = paymentmode;
	}
	private Double tot_amt;
	private Double paid_amt;
	private Double balance_amt;
	
	private int bill_no;
	private String pdate = "";
	private Double current_amt;
	private java.util.ArrayList PaymentList = null;
	private java.util.ArrayList PaidList = null;
		
	/**
	 * @return
	 */
	
	public java.util.ArrayList getPaymentList() {
		return PaymentList;
	}
	
	public java.util.ArrayList getPaidList() {
		return PaidList;
	}
	
	public String getMcode() {
		return mcode;
	}	


	public String getMname() {
		return mname;
	}



	public String getDept() {
		return dept;
	}


	public String getCourse() {
		return course;
	}
	
	/**
	 * @return
	 */
	public String getPdate() {
		return pdate;
	}
	
	public double getTot_Amt() {
		return tot_amt;
	}
	
	public double getPaid_Amt() {
		return paid_amt;
	}
	
	public double getBalance_Amt() {
		return balance_amt;
	}
	
	public int getBill_No() {
		return bill_no;
	}
	
	public double getCurrent_Amt() {
		return current_amt;
	}
	
	
	public void setPaymentList(java.util.ArrayList list) {
		PaymentList = list;
	}
	
	public void setPaidList(java.util.ArrayList list) {
		PaidList = list;
	}
	
	/**
	 * @param string
	 */
	public void setMcode(String string) {
		mcode = string;
	}


	public void setMname(String string) {
		mname = string;
	}


	public void setDept(String string) {
		dept = string;
	}


	public void setCourse(String string) {
		course = string;
	}
	
	public void setPdate(String string) {
		pdate = string;
	}

	
	public void setTot_Amt(double d) {
		tot_amt = d;
	}
	
	public void setPaid_Amt(double e) {
		paid_amt = e;
	}
	
	public void setBalance_Amt(double f) {
		balance_amt = f;
	}
	
	public void setBill_No(int g) {
		bill_no = g;
	}
	public void setCurrent_Amt(double h) {
		current_amt = h;
	}
	
	
	
	
}

