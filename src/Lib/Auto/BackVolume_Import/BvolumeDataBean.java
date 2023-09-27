package Lib.Auto.BackVolume_Import;


public class BvolumeDataBean    {
	
	private String accessNo;
	private String callNo;
	private String title;
	private String author;
	private String receiveDate;
	private String publisher;
	private String pubyear;
	private String supplier;
	private String subject;
	private String department;
	private String document;
	private String branch;
	private String budget;
	private String language;
	private String location;
	
	private Double price;
	private Double discount;
	private Double netprice;
	
	private String invoiceno;
	private String invoiceDate;
	private String edition;
	private String volumeNo;
	private String pages;
	private String keywords;
	private String notes;
	
	private String addfield1;
	private String addfield2;
	
	
	private int subjectCode;
	private int suplierCode;
	private int publisherCode;
	private int branchCode;
	private int departmentCode;
	private int budgetCode;    // For Creating with Nil Budget
	private int noOfCopy=1;      // For Creating with only one Copy
	
	
	private String availability="YES";      // For Creating with YES Availability
	private String otherDate="1800-01-01";  // For Default data
	private String role="AUTHOR";
	private String AVtype="NO";
	
	private Double bcost=0.0;
	private String currency="Rupees";
	
	private String otherTitle="";
	private String stateRes="";
	private String place="";
	private String size="";
	private String illustration="";
	private String isbn="";
	private String script="";
	private String physical="";
	private String binding="";
	private String summary="";
	private String bibliography="";
	private String contents="";
	private String partNo="";
	private String volumeTitle="";
	private String volumeRes="";
	private String corAut="";
	private String corAdd="";
	private String serAut="";
	private String serName="";
	private String serTitle="";
	private String issn="";
	private String meeting="";
	private String sponsor="";
	private String venue="";
	private String addfield3="";
	private String addfield4="";
	private String purchaseType="";
	
	
	
	
	
	public String getAddfield3() {
		return addfield3;
	}
	public void setAddfield3(String addfield3) {
		this.addfield3 = addfield3;
	}
	public String getAddfield4() {
		return addfield4;
	}
	public void setAddfield4(String addfield4) {
		this.addfield4 = addfield4;
	}
	public String getBibliography() {
		return bibliography;
	}
	public void setBibliography(String bibliography) {
		this.bibliography = bibliography;
	}
	public String getBinding() {
		return binding;
	}
	public void setBinding(String binding) {
		this.binding = binding;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getCorAdd() {
		return corAdd;
	}
	public void setCorAdd(String corAdd) {
		this.corAdd = corAdd;
	}
	public String getCorAut() {
		return corAut;
	}
	public void setCorAut(String corAut) {
		this.corAut = corAut;
	}
	public String getIllustration() {
		return illustration;
	}
	public void setIllustration(String illustration) {
		this.illustration = illustration;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getIssn() {
		return issn;
	}
	public void setIssn(String issn) {
		this.issn = issn;
	}
	public String getMeeting() {
		return meeting;
	}
	public void setMeeting(String meeting) {
		this.meeting = meeting;
	}
	public String getOtherTitle() {
		return otherTitle;
	}
	public void setOtherTitle(String otherTitle) {
		this.otherTitle = otherTitle;
	}
	public String getPartNo() {
		return partNo;
	}
	public void setPartNo(String partNo) {
		this.partNo = partNo;
	}
	public String getPhysical() {
		return physical;
	}
	public void setPhysical(String physical) {
		this.physical = physical;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public String getPurchaseType() {
		return purchaseType;
	}
	public void setPurchaseType(String purchaseType) {
		this.purchaseType = purchaseType;
	}
	public String getScript() {
		return script;
	}
	public void setScript(String script) {
		this.script = script;
	}
	public String getSerAut() {
		return serAut;
	}
	public void setSerAut(String serAut) {
		this.serAut = serAut;
	}
	public String getSerName() {
		return serName;
	}
	public void setSerName(String serName) {
		this.serName = serName;
	}
	public String getSerTitle() {
		return serTitle;
	}
	public void setSerTitle(String serTitle) {
		this.serTitle = serTitle;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getSponsor() {
		return sponsor;
	}
	public void setSponsor(String sponsor) {
		this.sponsor = sponsor;
	}
	public String getStateRes() {
		return stateRes;
	}
	public void setStateRes(String stateRes) {
		this.stateRes = stateRes;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getVenue() {
		return venue;
	}
	public void setVenue(String venue) {
		this.venue = venue;
	}
	public String getVolumeRes() {
		return volumeRes;
	}
	public void setVolumeRes(String volumeRes) {
		this.volumeRes = volumeRes;
	}
	public String getVolumeTitle() {
		return volumeTitle;
	}
	public void setVolumeTitle(String volumeTitle) {
		this.volumeTitle = volumeTitle;
	}
	public String getAvailability() {
		return availability;
	}
	public void setAvailability(String availability) {
		this.availability = availability;
	}
	
	public String getAVtype() {
		return AVtype;
	}
	public void setAVtype(String vtype) {
		AVtype = vtype;
	}
	
	public Double getBcost() {
		return bcost;
	}
	public void setBcost(Double bcost) {
		this.bcost = bcost;
	}
	
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getOtherDate() {
		return otherDate;
	}
	
	public void setOtherDate(String otherDate) {
		this.otherDate = otherDate;
	}
	public String getRole() {
		return role;
	}
	
	public void setRole(String role) {
		this.role = role;
	}
	public String getAccessNo() {
		return accessNo;
	}
	public void setAccessNo(String accessNo) {
		this.accessNo = accessNo;
	}
	
	
	
	public String getAddfield1() {
		return addfield1;
	}
	public void setAddfield1(String addfield1) {
		this.addfield1 = addfield1;
	}
	
	
	public String getAddfield2() {
		return addfield2;
	}
	public void setAddfield2(String addfield2) {
		this.addfield2 = addfield2;
	}
	
	
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	
	
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	
	
	public int getBranchCode() {
		return branchCode;
	}
	public void setBranchCode(int branchCode) {
		this.branchCode = branchCode;
	}
	
	
	public int getBudgetCode() {
		return budgetCode;
	}
	public void setBudgetCode(int budgetCode) {
		this.budgetCode = budgetCode;
	}
	
	
	public String getCallNo() {
		return callNo;
	}
	public void setCallNo(String callNo) {
		this.callNo = callNo;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public int getDepartmentCode() {
		return departmentCode;
	}
	public void setDepartmentCode(int departmentCode) {
		this.departmentCode = departmentCode;
	}
	public Double getDiscount() {
		return discount;
	}
	public void setDiscount(Double discount) {
		this.discount = discount;
	}
	public String getDocument() {
		return document;
	}
	public void setDocument(String document) {
		this.document = document;
	}
	public String getEdition() {
		return edition;
	}
	public void setEdition(String edition) {
		this.edition = edition;
	}
	public String getInvoiceDate() {
		return invoiceDate;
	}
	public void setInvoiceDate(String invoiceDate) {
		this.invoiceDate = invoiceDate;
	}
	public String getInvoiceno() {
		return invoiceno;
	}
	public void setInvoiceno(String invoiceno) {
		this.invoiceno = invoiceno;
	}
	public String getKeywords() {
		return keywords;
	}
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public Double getNetprice() {
		return netprice;
	}
	public void setNetprice(Double netprice) {
		this.netprice = netprice;
	}
	public int getNoOfCopy() {
		return noOfCopy;
	}
	public void setNoOfCopy(int noOfCopy) {
		this.noOfCopy = noOfCopy;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public String getPages() {
		return pages;
	}
	public void setPages(String pages) {
		this.pages = pages;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public int getPublisherCode() {
		return publisherCode;
	}
	public void setPublisherCode(int publisherCode) {
		this.publisherCode = publisherCode;
	}
	public String getPubyear() {
		return pubyear;
	}
	public void setPubyear(String pubyear) {
		this.pubyear = pubyear;
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
	public int getSubjectCode() {
		return subjectCode;
	}
	public void setSubjectCode(int subjectCode) {
		this.subjectCode = subjectCode;
	}
	public int getSuplierCode() {
		return suplierCode;
	}
	public void setSuplierCode(int suplierCode) {
		this.suplierCode = suplierCode;
	}
	public String getSupplier() {
		return supplier;
	}
	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getVolumeNo() {
		return volumeNo;
	}
	public void setVolumeNo(String volumeNo) {
		this.volumeNo = volumeNo;
	}
	public String getBudget() {
		return budget;
	}
	public void setBudget(String budget) {
		this.budget = budget;
	}
	
	
	
	
	
	
	
	
	
		
	
	
	
	
	
	
}

