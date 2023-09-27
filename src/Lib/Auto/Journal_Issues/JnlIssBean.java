package Lib.Auto.Journal_Issues;

import java.util.ArrayList;

public class JnlIssBean {
	private String iss_acc_no;
	private int jnl_code=0;
	private int max=0;
	private String sub_code;
	private String jnl_name;
	private String iss_year;
	private String iss_month;
	private String iss_vol;
	private String iss_no;
	private String part_no;
	private String iss_date;
	private String exp_date;
	private String rec_date;
	private String avail;
	private String bvol_no;
	private String remarks;
	private float iss_amount;
	private String page;
	
	private String fromDt="";
	private String toDt="";
	private String flag="";
	
	
	public String getFromDt() {
		return fromDt;
	}
	public void setFromDt(String fromDt) {
		this.fromDt = fromDt;
	}
	public String getToDt() {
		return toDt;
	}
	public void setToDt(String toDt) {
		this.toDt = toDt;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}



	
	
	
	private java.util.ArrayList al=null;
	
	public String getAvail() {
		return avail;
	}
	public void setAvail(String avail) {
		this.avail = avail;
	}
	public String getBvol_no() {
		return bvol_no;
	}
	public void setBvol_no(String bvol_no) {
		this.bvol_no = bvol_no;
	}
	public String getExp_date() {
		return exp_date;
	}
	public void setExp_date(String exp_date) {
		this.exp_date = exp_date;
	}
	public String getIss_acc_no() {
		return iss_acc_no;
	}
	public void setIss_acc_no(String iss_acc_no) {
		this.iss_acc_no = iss_acc_no;
	}
	public float getIss_amount() {
		return iss_amount;
	}
	public void setIss_amount(float iss_amount) {
		this.iss_amount = iss_amount;
	}
	public String getIss_date() {
		return iss_date;
	}
	public void setIss_date(String iss_date) {
		this.iss_date = iss_date;
	}
	public String getIss_month() {
		return iss_month;
	}
	public void setIss_month(String iss_month) {
		this.iss_month = iss_month;
	}
	public String getIss_no() {
		return iss_no;
	}
	public void setIss_no(String iss_no) {
		this.iss_no = iss_no;
	}
	public String getIss_vol() {
		return iss_vol;
	}
	public void setIss_vol(String iss_vol) {
		this.iss_vol = iss_vol;
	}
	public String getIss_year() {
		return iss_year;
	}
	public void setIss_year(String iss_year) {
		this.iss_year = iss_year;
	}
	public int getJnl_code() {
		return jnl_code;
	}
	public void setJnl_code(int jnl_code) {
		this.jnl_code = jnl_code;
	}
	public String getPage() {
		return page;
	}
	public void setPage(String page) {
		this.page = page;
	}
	public String getPart_no() {
		return part_no;
	}
	public void setPart_no(String part_no) {
		this.part_no = part_no;
	}
	public String getRec_date() {
		return rec_date;
	}
	public void setRec_date(String rec_date) {
		this.rec_date = rec_date;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getSub_code() {
		return sub_code;
	}
	public void setSub_code(String sub_code) {
		this.sub_code = sub_code;
	}
	public String getJnl_name() {
		return jnl_name;
	}
	public void setJnl_name(String jnl_name) {
		this.jnl_name = jnl_name;
	}
	
	public int getMax() {
		return max;
	}
	public void setMax(int max) {
		this.max = max;
	}
	
	 public ArrayList getAl() {

			return al;
		    }



		    public void setAl(ArrayList al) {

		        this.al = al;

		    }
			
	

}
