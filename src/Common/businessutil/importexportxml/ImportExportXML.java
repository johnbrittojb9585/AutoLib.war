/*
 *                 Autolib License Notice
 *
 * The contents of this file are subject to the Autolib  License
 * Version 1.0 (the "License"). You may not use this file except in
 * compliance with the License.The Initial Developer of the Original Code is
 * Autolib Software Systems.
 * Portions Copyright 1998-2008.Autolib Software Systems All Rights Reserved.
 *
 *
 */
package Common.businessutil.importexportxml;

/**
 * @author Raja
 * @Date 06 Jan 2008
 * @version 1.0
 */
import java.io.Serializable;


/** The Interface HostelMaster. */
public class ImportExportXML implements Serializable

{
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The accessNo.*/
	private String accessNo;
	
	private String binderName;
	
    private String totalamount;
	
	private String Paidamount;
	
	private String balamount;
	
	public String getBinderName() {
		return binderName;
	}

	public void setBinderName(String binderName) {
		this.binderName = binderName;
	}

	public String getAcceptedPrice() {
		return acceptedPrice;
	}

	public void setAcceptedPrice(String acceptedPrice) {
		this.acceptedPrice = acceptedPrice;
	}

	public String getSendingDate() {
		return sendingDate;
	}

	public void setSendingDate(String sendingDate) {
		this.sendingDate = sendingDate;
	}

	private String acceptedPrice;
	
	private String sendingDate;
	
	/** The title.*/
	private String title;
	
	/** The callNo.*/
	private String callNo;
	
	/** The authorName.*/
	private String authorName;
	
	/** The edition.*/
	private String edition;
	
	/** The publisherName.*/
	private String publisherName;
	
	/** The supplierName.*/
	private String supplierName;
	
	/** The yearPub.*/
	private String yearPub;
	
	/** The invoiceNo.*/
	private String invoiceNo;
	
	/** The isbnNo.*/
	private String isbnNo;
	
	/** The price.*/
	private String price;
	
	private String noOfPages;
	
	private String QBCode;
	
	public String getQBCode() {
		return QBCode;
	}

	public void setQBCode(String qBCode) {
		QBCode = qBCode;
	}

	public String getSemester() {
		return Semester;
	}

	public void setSemester(String semester) {
		Semester = semester;
	}

	public String getCourse() {
		return Course;
	}

	public void setCourse(String course) {
		Course = course;
	}

	public String getSubCode() {
		return SubCode;
	}

	public void setSubCode(String subCode) {
		SubCode = subCode;
	}

	public String getSubName() {
		return SubName;
	}

	public void setSubName(String subName) {
		SubName = subName;
	}

	private String Semester;
	
	private String Course;
	
	private String SubCode;
	
	private String SubName;
	
	private String InvoiceDate;
	
	private String purchaseType;
	
	private String keywords;
	
	private String courseName;
	
	private String cyear;
	
	private String totalMins;

	/** The memberCode.*/
	private String memberCode;
	
	/** The memberName.*/
	private String memberName;
	
	/** The staffCode.*/
	private String staffCode;
	
	/** The issueDate.*/
	private String issueDate;
	
	/** The dueDate.*/
	private String dueDate;
	
	
	/** The returnDate.*/
	private String returnDate;
	
	/** The document.*/
	private String document;
	
	private String departmentName;

	private String subjectName;

	private String availability;

	private String receivedDate;

	private String location;

	private String language;
	
	private String DeptName;
	
	private String Discount;
	
	private String NoOfBooks;
	
	private String NoOfTitles;
	
	private String NetPrice;
		
	private String Volume;
	
	private String bprice;
		
	private String designation;
	
	private String group;
	
	private String year;
	
	private String amount;
	
	private String paiddate;
	
	private String paymode;
	
	private String DesigName;
	
	private String breakupdate;
	
	private String status;
	
	private String frequency;
	
	private String country;
	
	private String trans_no;
	
	private String trans_date;
	
	private String trans_amount;
	
	
	
	public String getTrans_no() {
		return trans_no;
	}

	public void setTrans_no(String trans_no) {
		this.trans_no = trans_no;
	}

	public String getTrans_date() {
		return trans_date;
	}

	public void setTrans_date(String trans_date) {
		this.trans_date = trans_date;
	}

	public String getTrans_amount() {
		return trans_amount;
	}

	public void setTrans_amount(String trans_amount) {
		this.trans_amount = trans_amount;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getFrequency() {
		return frequency;
	}

	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public String getRecover() {
		return recover;
	}

	public void setRecover(String recover) {
		this.recover = recover;
	}

	public String getRecoverDate() {
		return recoverDate;
	}

	public void setRecoverDate(String recoverDate) {
		this.recoverDate = recoverDate;
	}

	private String orderDate;
	
	private String recover;
	
	private String recoverDate;
	
	public String getBreakupdate() {
		return breakupdate;
	}

	public void setBreakupdate(String breakupdate) {
		this.breakupdate = breakupdate;
	}

	public String getBreakupcount() {
		return breakupcount;
	}

	public void setBreakupcount(String breakupcount) {
		this.breakupcount = breakupcount;
	}

	private String breakupcount;
	
	public String getDesigName() {
		return DesigName;
	}

	public void setDesigName(String desigName) {
		DesigName = desigName;
	}

	public String getIntime() {
		return intime;
	}

	public void setIntime(String intime) {
		this.intime = intime;
	}

	public String getOuttime() {
		return outtime;
	}

	public void setOuttime(String outtime) {
		this.outtime = outtime;
	}

	public String getMinused() {
		return minused;
	}

	public void setMinused(String minused) {
		this.minused = minused;
	}

	private String intime;
	
	private String outtime;
	
	private String minused;
	
	//--------------------------for export TransAmount Cumulative-----
	
	private String totfine;
	
	public String getTotfine() {
		return totfine;
	}

	public void setTotfine(String totfine) {
		this.totfine = totfine;
	}
	
	
	public String getPaymode() {
		return paymode;
	}

	public void setPaymode(String paymode) {
		this.paymode = paymode;
	}

	public String getPaiddate() {
		return paiddate;
	}

	public void setPaiddate(String paiddate) {
		this.paiddate = paiddate;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getBprice() {
		return bprice;
	}

	public void setBprice(String bprice) {
		this.bprice = bprice;
	}

	public String getVolume() {
		return Volume;
	}

	public void setVolume(String volume) {
		Volume = volume;
	}

	private String overdue;
	private String PhotoCopy;
	private String Printout;
	private String ColorPrint;
	private String StripBinding;
	private String StickBinding;
	private String SprialBinding;
	private String Recovery;
	private String LossOfResources;
	private String Others;
	private String total;
	private String partno;

	public String getIssue_no() {
		return issue_no;
	}

	public void setIssue_no(String issue_no) {
		this.issue_no = issue_no;
	}

	public String getVolno() {
		return volno;
	}

	public void setVolno(String volno) {
		this.volno = volno;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	private String issue_no;
	private String volno;
	
	private String month;
	
	
	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	private String resdate;
	
	private String budget;
	
	public String getBudget() {
		return budget;
	}

	public void setBudget(String budget) {
		this.budget = budget;
	}

	public String getResdate() {
		return resdate;
	}

	public void setResdate(String resdate) {
		this.resdate = resdate;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public String getAvailability() {
		return availability;
	}

	public void setAvailability(String availability) {
		this.availability = availability;
	}

	public String getReceivedDate() {
		return receivedDate;
	}

	public void setReceivedDate(String receivedDate) {
		this.receivedDate = receivedDate;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	
	
	
	/**
	 * @return Returns the accessNo.
	 */
	public String getAccessNo() {
		return accessNo;
	}

	/**
	 * @param accessNo The accessNo to set.
	 */
	public void setAccessNo(String accessNo) {
		this.accessNo = accessNo;
	}

	/**
	 * @return Returns the authorName.
	 */
	public String getAuthorName() {
		return authorName;
	}

	/**
	 * @param authorName The authorName to set.
	 */
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	/**
	 * @return Returns the callNo.
	 */
	public String getCallNo() {
		return callNo;
	}

	/**
	 * @param callNo The callNo to set.
	 */
	public void setCallNo(String callNo) {
		this.callNo = callNo;
	}

	/**
	 * @return Returns the edition.
	 */
	public String getEdition() {
		return edition;
	}

	/**
	 * @param edition The edition to set.
	 */
	public void setEdition(String edition) {
		this.edition = edition;
	}

	/**
	 * @return Returns the invoiceNo.
	 */
	public String getInvoiceNo() {
		return invoiceNo;
	}

	/**
	 * @param invoiceNo The invoiceNo to set.
	 */
	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	/**
	 * @return Returns the publisherName.
	 */
	public String getPublisherName() {
		return publisherName;
	}

	/**
	 * @param publisherName The publisherName to set.
	 */
	public void setPublisherName(String publisherName) {
		this.publisherName = publisherName;
	}

	/**
	 * @return Returns the supplierName.
	 */
	public String getSupplierName() {
		return supplierName;
	}

	/**
	 * @param supplierName The supplierName to set.
	 */
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	/**
	 * @return Returns the title.
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title The title to set.
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	public String getNoOfPages() {
		return noOfPages;
	}

	public void setNoOfPages(String noOfPages) {
		this.noOfPages = noOfPages;
	}
	
	public String getInvoiceDate() {
		return InvoiceDate;
	}

	public void setInvoiceDate(String InvoiceDate) {
		this.InvoiceDate = InvoiceDate;
	}
	
	/**
	 * @return Returns the yearPub.
	 */
	public String getYearPub() {
		return yearPub;
	}

	/**
	 * @param yearPub The yearPub to set.
	 */
	public void setYearPub(String yearPub) {
		this.yearPub = yearPub;
	}

	/**
	 * @return Returns the isbnNo.
	 */
	public String getIsbnNo() {
		return isbnNo;
	}

	/**
	 * @param isbnNo The isbnNo to set.
	 */
	public void setIsbnNo(String isbnNo) {
		this.isbnNo = isbnNo;
	}

	/**
	 * @return Returns the price.
	 */
	public String getPrice() {
		return price;
	}

	/**
	 * @param price The price to set.
	 */
	public void setPrice(String price) {
		this.price = price;
	}

	/**
	 * @return Returns the document.
	 */
	public String getDocument() {
		return document;
	}

	/**
	 * @param document The document to set.
	 */
	public void setDocument(String document) {
		this.document = document;
	}

	/**
	 * @return Returns the dueDate.
	 */
	public String getDueDate() {
		return dueDate;
	}

	/**
	 * @param dueDate The dueDate to set.
	 */
	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}

	/**
	 * @return Returns the issueDate.
	 */
	public String getIssueDate() {
		return issueDate;
	}

	/**
	 * @param issueDate The issueDate to set.
	 */
	public void setIssueDate(String issueDate) {
		this.issueDate = issueDate;
	}

	/**
	 * @return Returns the memberCode.
	 */
	public String getMemberCode() {
		return memberCode;
	}

	/**
	 * @param memberCode The memberCode to set.
	 */
	public void setMemberCode(String memberCode) {
		this.memberCode = memberCode;
	}

	/**
	 * @return Returns the returnDate.
	 */
	public String getReturnDate() {
		return returnDate;
	}

	/**
	 * @param returnDate The returnDate to set.
	 */
	public void setReturnDate(String returnDate) {
		this.returnDate = returnDate;
	}

	/**
	 * @return Returns the staffCode.
	 */
	public String getStaffCode() {
		return staffCode;
	}

	/**
	 * @param staffCode The staffCode to set.
	 */
	public void setStaffCode(String staffCode) {
		this.staffCode = staffCode;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getColorPrint() {
		return ColorPrint;
	}

	public void setColorPrint(String colorPrint) {
		ColorPrint = colorPrint;
	}

	public String getLossOfResources() {
		return LossOfResources;
	}

	public void setLossOfResources(String lossOfResources) {
		LossOfResources = lossOfResources;
	}

	public String getOthers() {
		return Others;
	}

	public void setOthers(String others) {
		Others = others;
	}

	public String getOverdue() {
		return overdue;
	}

	public void setOverdue(String overdue) {
		this.overdue = overdue;
	}

	public String getPhotoCopy() {
		return PhotoCopy;
	}

	public void setPhotoCopy(String photoCopy) {
		PhotoCopy = photoCopy;
	}

	public String getPrintout() {
		return Printout;
	}

	public void setPrintout(String printout) {
		Printout = printout;
	}

	public String getRecovery() {
		return Recovery;
	}

	public void setRecovery(String recovery) {
		Recovery = recovery;
	}

	public String getSprialBinding() {
		return SprialBinding;
	}

	public void setSprialBinding(String sprialBinding) {
		SprialBinding = sprialBinding;
	}

	public String getStickBinding() {
		return StickBinding;
	}

	public void setStickBinding(String stickBinding) {
		StickBinding = stickBinding;
	}

	public String getStripBinding() {
		return StripBinding;
	}

	public void setStripBinding(String stripBinding) {
		StripBinding = stripBinding;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public String getDiscount() {
		return Discount;
	}

	public void setDiscount(String discount) {
		Discount = discount;
	}

	public String getDeptName() {
		return DeptName;
	}

	public void setDeptName(String deptName) {
		DeptName = deptName;
	}

	public String getNetPrice() {
		return NetPrice;
	}

	public void setNetPrice(String netPrice) {
		NetPrice = netPrice;
	}

	public String getNoOfTitles() {
		return NoOfTitles;
	}

	public void setNoOfTitles(String noOfTitles) {
		NoOfTitles = noOfTitles;
	}

	public String getNoOfBooks() {
		return NoOfBooks;
	}

	public void setNoOfBooks(String noOfBooks) {
		NoOfBooks = noOfBooks;
	}

	public String getTotalamount() {
		return totalamount;
	}

	public void setTotalamount(String totalamount) {
		this.totalamount = totalamount;
	}

	public String getPaidamount() {
		return Paidamount;
	}

	public void setPaidamount(String paidamount) {
		Paidamount = paidamount;
	}

	public String getBalamount() {
		return balamount;
	}

	public void setBalamount(String balamount) {
		this.balamount = balamount;
	}

	public String getPartno() {
		return partno;
	}

	public void setPartno(String partno) {
		this.partno = partno;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public String getPurchaseType() {
		return purchaseType;
	}

	public void setPurchaseType(String purchaseType) {
		this.purchaseType = purchaseType;
	}

	public String getTotalMins() {
		return totalMins;
	}

	public void setTotalMins(String totalMins) {
		this.totalMins = totalMins;
	}

	public String getCyear() {
		return cyear;
	}

	public void setCyear(String cyear) {
		this.cyear = cyear;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	



}
