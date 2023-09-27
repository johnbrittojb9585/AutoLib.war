package Lib.Auto.JournalBrowse;

import java.io.Serializable;
import java.util.ArrayList;


public  class JournalSearchbean implements Serializable  {

    // ----------------------------------------------------- Instance Variables

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String jnlCode = "";
    private String jnlName = "";
    private String frequency = "";
    private String country = "";
    private String document = "";
    private String subject = "";
    private String department = "";
    private String publisher = "";
    private String language = "";
    private String issn = "";
    
    private String jnlIssueAccNo = "";
    private String issueYear = "";
    private String issueMonth = "";
    private String issueVolume = "";
    private String issueNo = "";
    private String issueDate = "";
    private String receiveDate = "";
    private String availability = "";
    
    private String addField1 = "";
    private String addField2 = "";
    
    
    private String jnlType = "";
    public String getJnlType() {
		return jnlType;
	}
	public void setJnlType(String jnlType) {
		this.jnlType = jnlType;
	}
	public String getDeliMode() {
		return deliMode;
	}
	public void setDeliMode(String deliMode) {
		this.deliMode = deliMode;
	}



	private String deliMode = "";
    
    
    private String location = "";
    private String url = "";
    
    public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}



	static java.util.ArrayList al=null;
    
	public String getAddField1() {
		return addField1;
	}
	public void setAddField1(String addField1) {
		this.addField1 = addField1;
	}
	public String getAddField2() {
		return addField2;
	}
	public void setAddField2(String addField2) {
		this.addField2 = addField2;
	}
	public String getAvailability() {
		return availability;
	}
	public void setAvailability(String availability) {
		this.availability = availability;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getDocument() {
		return document;
	}
	public void setDocument(String document) {
		this.document = document;
	}
	public String getFrequency() {
		return frequency;
	}
	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}
	public String getIssn() {
		return issn;
	}
	public void setIssn(String issn) {
		this.issn = issn;
	}
	public String getIssueDate() {
		return issueDate;
	}
	public void setIssueDate(String issueDate) {
		this.issueDate = issueDate;
	}
	public String getIssueMonth() {
		return issueMonth;
	}
	public void setIssueMonth(String issueMonth) {
		this.issueMonth = issueMonth;
	}
	public String getIssueNo() {
		return issueNo;
	}
	public void setIssueNo(String issueNo) {
		this.issueNo = issueNo;
	}
	public String getIssueVolume() {
		return issueVolume;
	}
	public void setIssueVolume(String issueVolume) {
		this.issueVolume = issueVolume;
	}
	public String getIssueYear() {
		return issueYear;
	}
	public void setIssueYear(String issueYear) {
		this.issueYear = issueYear;
	}
	public String getJnlCode() {
		return jnlCode;
	}
	public void setJnlCode(String jnlCode) {
		this.jnlCode = jnlCode;
	}
	public String getJnlIssueAccNo() {
		return jnlIssueAccNo;
	}
	public void setJnlIssueAccNo(String jnlIssueNo) {
		this.jnlIssueAccNo = jnlIssueNo;
	}
	public String getJnlName() {
		return jnlName;
	}
	public void setJnlName(String jnlName) {
		this.jnlName = jnlName;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public String getReceiveDate() {
		return receiveDate;
	}
	public void setReceiveDate(String receiveDate) {
		this.receiveDate = receiveDate;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	public ArrayList getAl() {
		
		return al;
	    }


	    
	    public void setAl(ArrayList al) {

	        this.al = al;

	    }
    
    
    
}
