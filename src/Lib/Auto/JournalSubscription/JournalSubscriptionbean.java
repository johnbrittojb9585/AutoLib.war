package Lib.Auto.JournalSubscription;

import java.util.*;

public class JournalSubscriptionbean {

	private int sno = 0;
	public int getSno() {
		return sno;
	}
	public void setSno(int sno) {
		this.sno = sno;
	}
	public int getJnlcode() {
		return jnlcode;
	}
	public void setJnlcode(int jnlcode) {
		this.jnlcode = jnlcode;
	}
	public String getSubsNo() {
		return subsNo;
	}
	public void setSubsNo(String subsNo) {
		this.subsNo = subsNo;
	}
	public String getSearchValue() {
		return searchValue;
	}
	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}
	public String getStartingAccNo() {
		return startingAccNo;
	}
	public void setStartingAccNo(String startingAccNo) {
		this.startingAccNo = startingAccNo;
	}
	public int getSupCode() {
		return supCode;
	}
	public void setSupCode(int supCode) {
		this.supCode = supCode;
	}
	public int getPubCode() {
		return pubCode;
	}
	public void setPubCode(int pubCode) {
		this.pubCode = pubCode;
	}
	public String getSubsFrom() {
		return subsFrom;
	}
	public void setSubsFrom(String subsFrom) {
		this.subsFrom = subsFrom;
	}
	public String getSubsTo() {
		return subsTo;
	}
	public void setSubsTo(String subsTo) {
		this.subsTo = subsTo;
	}
	public String getSubsAmt() {
		return subsAmt;
	}
	public void setSubsAmt(String subsAmt) {
		this.subsAmt = subsAmt;
	}
	public String getFrequency() {
		return frequency;
	}
	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}
	public String getAvailability() {
		return availability;
	}
	public void setAvailability(String availability) {
		this.availability = availability;
	}
	public int getNoOfIssues() {
		return noOfIssues;
	}
	public void setNoOfIssues(int noOfIssues) {
		this.noOfIssues = noOfIssues;
	}
	public int getExceptedDays() {
		return exceptedDays;
	}
	public void setExceptedDays(int exceptedDays) {
		this.exceptedDays = exceptedDays;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getIssueYear() {
		return issueYear;
	}
	public void setIssueYear(String issueYear) {
		this.issueYear = issueYear;
	}
	public String getIssueMonth() {
		return issueMonth;
	}
	public void setIssueMonth(String issueMonth) {
		this.issueMonth = issueMonth;
	}
	public String getIssueVolume() {
		return issueVolume;
	}
	public void setIssueVolume(String issueVolume) {
		this.issueVolume = issueVolume;
	}
	public String getIssueNo() {
		return issueNo;
	}
	public void setIssueNo(String issueNo) {
		this.issueNo = issueNo;
	}
	public String getjCost() {
		return jCost;
	}
	public void setjCost(String jCost) {
		this.jCost = jCost;
	}
	public String getjCurrency() {
		return jCurrency;
	}
	public void setjCurrency(String jCurrency) {
		this.jCurrency = jCurrency;
	}
	public String getjPrice() {
		return jPrice;
	}
	public void setjPrice(String jPrice) {
		this.jPrice = jPrice;
	}
	public String getjDiscount() {
		return jDiscount;
	}
	public void setjDiscount(String jDiscount) {
		this.jDiscount = jDiscount;
	}
	public String getNetPrice() {
		return netPrice;
	}
	public void setNetPrice(String netPrice) {
		this.netPrice = netPrice;
	}
	public int getBudgCode() {
		return budgCode;
	}
	public void setBudgCode(int budgCode) {
		this.budgCode = budgCode;
	}
	public String getBudName() {
		return budName;
	}
	public void setBudName(String budName) {
		this.budName = budName;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public String getSupplier() {
		return Supplier;
	}
	public void setSupplier(String supplier) {
		Supplier = supplier;
	}
	public String getJnlName() {
		return jnlName;
	}
	public void setJnlName(String jnlName) {
		this.jnlName = jnlName;
	}
	private int jnlcode = 0;
	private String subsNo = null;
	private String searchValue=null;
	private String startingAccNo = null;
	private int supCode = 0;
	private int pubCode = 0;
	private String subsFrom = null;
	private String subsTo = null;
	private String subsAmt = null;
	private String frequency = null;
	private String availability = null;
	private int noOfIssues = 0;
	private int exceptedDays = 0;
	private String flag = null;
	private String issueYear = null;
	private String issueMonth = null;
	private String issueVolume = null;
	private String issueNo = null;
	private String jCost = null;
	private String jCurrency = null;
	private String jPrice = null;
	private String jDiscount = null;
	private String netPrice = null;
	private int budgCode=0;
	
	private String budName="";
	private String publisher="";
	private String Supplier="";
	private String jnlName="";
	private java.util.ArrayList al=null;
	public java.util.ArrayList getAl() {
		return al;
	}
	public void setAl(java.util.ArrayList al) {
		this.al = al;
	}
	
	
}