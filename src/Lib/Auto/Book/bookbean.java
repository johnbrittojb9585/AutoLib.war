package Lib.Auto.Book;

public  class bookbean   {

        
     private String accessNo;
     private String callNo;
     private String rcDate;
     private String author;
     private String title;
     private String otherTitle;
     private String role;
     private String stateRes;
     private String edition;
     private String language;
     private String corAut;
     private String corAdd;
     private String serAut;
     private String serName;
     private String serTitle;
     private String issn;
     private String location;
     private String avail;
     private String publisherName;
     private String place;
     private String yop;
     private String pages;
     private String size;
     private String illustration;
     private String isbn;
     private String bprice;
     private int copies;
     private int subCode;
     private int supCode;
     private int pubCode;
     private int branchCode;
     private int deptCode;
     private int budgetCode;
     private String script;
     private String subName;
     private String deptName;
     private String branchName;
     private String doc;
     private String type;
     private String physical;
     private String binding;
     private String budName;
     private String keywords;
     private String notes;
     private String summary;
     private String bibliography;
     private String contents;
     private String volumeNo;
     private String partNo;
     private String volumeTitle;
     private String volumeRes;
     private String meeting;
     private String sponsor;
     private String venue;
     private String otherDate;
     private String purchaseType;
     private String supName;
     private String pubName;
     private String invNo;
     private String invoiceDate;
     private String bcost;
     private String currency;
     private String acceptPrice;
     private double discount;
     private String addfield1;
     private String addfield2;
     private String addfield3;
     private String addfield4;
     private String authorvalue;
     
     private java.util.ArrayList Book_List=null;
	/**
	 * @return Returns the acceptPrice.
	 */
	public String getAcceptPrice() {
		return acceptPrice;
	}
	/**
	 * @param acceptPrice The acceptPrice to set.
	 */
	public void setAcceptPrice(String acceptPrice) {
		this.acceptPrice = acceptPrice;
	}
	/**
	 * @return Returns the author_value.
	 */
	public String getauthorvalue() {
		return authorvalue;
	}
	/**
	 * @param author_value The author_value to set.
	 */
	public void setauthorvalue(String authorvalue) {
		
		this.authorvalue = authorvalue;
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
	 * @return Returns the addfield1.
	 */
	public String getAddfield1() {
		return addfield1;
	}
	/**
	 * @param addfield1 The addfield1 to set.
	 */
	public void setAddfield1(String addfield1) {
		this.addfield1 = addfield1;
	}
	/**
	 * @return Returns the addfield2.
	 */
	public String getAddfield2() {
		return addfield2;
	}
	/**
	 * @param addfield2 The addfield2 to set.
	 */
	public void setAddfield2(String addfield2) {
		this.addfield2 = addfield2;
	}
	/**
	 * @return Returns the addfield3.
	 */
	public String getAddfield3() {
		return addfield3;
	}
	/**
	 * @param addfield3 The addfield3 to set.
	 */
	public void setAddfield3(String addfield3) {
		this.addfield3 = addfield3;
	}
	/**
	 * @return Returns the addfield4.
	 */
	public String getAddfield4() {
		return addfield4;
	}
	/**
	 * @param addfield4 The addfield4 to set.
	 */
	public void setAddfield4(String addfield4) {
		this.addfield4 = addfield4;
	}
	/**
	 * @return Returns the author.
	 */
	public String getAuthor() {
		return author;
	}
	/**
	 * @param author The author to set.
	 */
	public void setAuthor(String author) {
		author = author.trim();
		if(author.length()>0)
		{
			if(author.charAt(author.length()-1)==';')
			{
				author = author.substring(0, author.length()-1);
			}
		}

		this.author = author;
	}
	/**
	 * @return Returns the avail.
	 */
	public String getAvail() {
		return avail;
	}
	/**
	 * @param avail The avail to set.
	 */
	public void setAvail(String avail) {
		this.avail = avail;
	}
	/**
	 * @return Returns the bcost.
	 */
	public String getBcost() {
		return bcost;
	}
	/**
	 * @param bcost The bcost to set.
	 */
	public void setBcost(String bcost) {
		this.bcost = bcost;
	}
	/**
	 * @return Returns the bibliography.
	 */
	public String getBibliography() {
		return bibliography;
	}
	/**
	 * @param bibliography The bibliography to set.
	 */
	public void setBibliography(String bibliography) {
		this.bibliography = bibliography;
	}
	/**
	 * @return Returns the binding.
	 */
	public String getBinding() {
		return binding;
	}
	/**
	 * @param binding The binding to set.
	 */
	public void setBinding(String binding) {
		this.binding = binding;
	}
	/**
	 * @return Returns the book_List.
	 */
	public java.util.ArrayList getBook_List() {
		return Book_List;
	}
	/**
	 * @param book_List The book_List to set.
	 */
	public void setBook_List(java.util.ArrayList book_List) {
		Book_List = book_List;
	}
	/**
	 * @return Returns the bprice.
	 */
	public String getBprice() {
		return bprice;
	}
	/**
	 * @param bprice The bprice to set.
	 */
	public void setBprice(String bprice) {
		this.bprice = bprice;
	}
	/**
	 * @return Returns the branchCode.
	 */
	public int getBranchCode() {
		return branchCode;
	}
	/**
	 * @param branchCode The branchCode to set.
	 */
	public void setBranchCode(int branchCode) {
		this.branchCode = branchCode;
	}
	/**
	 * @return Returns the branchName.
	 */
	public String getBranchName() {
		return branchName;
	}
	/**
	 * @param branchName The branchName to set.
	 */
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	/**
	 * @return Returns the budgetCode.
	 */
	public int getBudgetCode() {
		return budgetCode;
	}
	/**
	 * @param budgetCode The budgetCode to set.
	 */
	public void setBudgetCode(int budgetCode) {
		this.budgetCode = budgetCode;
	}
	/**
	 * @return Returns the budName.
	 */
	public String getBudName() {
		return budName;
	}
	/**
	 * @param budName The budName to set.
	 */
	public void setBudName(String budName) {
		this.budName = budName;
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
	 * @return Returns the contents.
	 */
	public String getContents() {
		return contents;
	}
	/**
	 * @param contents The contents to set.
	 */
	public void setContents(String contents) {
		this.contents = contents;
	}
	/**
	 * @return Returns the copies.
	 */
	public int getCopies() {
		return copies;
	}
	/**
	 * @param copies The copies to set.
	 */
	public void setCopies(int copies) {
		this.copies = copies;
	}
	/**
	 * @return Returns the corAdd.
	 */
	public String getCorAdd() {
		return corAdd;
	}
	/**
	 * @param corAdd The corAdd to set.
	 */
	public void setCorAdd(String corAdd) {
		this.corAdd = corAdd;
	}
	/**
	 * @return Returns the corAut.
	 */
	public String getCorAut() {
		return corAut;
	}
	/**
	 * @param corAut The corAut to set.
	 */
	public void setCorAut(String corAut) {
		this.corAut = corAut;
	}
	/**
	 * @return Returns the currency.
	 */
	public String getCurrency() {
		return currency;
	}
	/**
	 * @param currency The currency to set.
	 */
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	/**
	 * @return Returns the deptCode.
	 */
	public int getDeptCode() {
		return deptCode;
	}
	/**
	 * @param deptCode The deptCode to set.
	 */
	public void setDeptCode(int deptCode) {
		this.deptCode = deptCode;
	}
	/**
	 * @return Returns the deptName.
	 */
	public String getDeptName() {
		return deptName;
	}
	/**
	 * @param deptName The deptName to set.
	 */
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	/**
	 * @return Returns the discount.
	 */
	public double getDiscount() {
		return discount;
	}
	/**
	 * @param discount The discount to set.
	 */
	public void setDiscount(double discount) {
		this.discount = discount;
	}
	/**
	 * @return Returns the doc.
	 */
	public String getDoc() {
		return doc;
	}
	/**
	 * @param doc The doc to set.
	 */
	public void setDoc(String doc) {
		this.doc = doc;
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
	 * @return Returns the illustration.
	 */
	public String getIllustration() {
		return illustration;
	}
	/**
	 * @param illustration The illustration to set.
	 */
	public void setIllustration(String illustration) {
		this.illustration = illustration;
	}
	/**
	 * @return Returns the invNo.
	 */
	public String getInvNo() {
		return invNo;
	}
	/**
	 * @param invNo The invNo to set.
	 */
	public void setInvNo(String invNo) {
		this.invNo = invNo;
	}
	/**
	 * @return Returns the invoiceDate.
	 */
	public String getInvoiceDate() {
		return invoiceDate;
	}
	/**
	 * @param invoiceDate The invoiceDate to set.
	 */
	public void setInvoiceDate(String invoiceDate) {
		this.invoiceDate = invoiceDate;
	}
	/**
	 * @return Returns the isbn.
	 */
	public String getIsbn() {
		return isbn;
	}
	/**
	 * @param isbn The isbn to set.
	 */
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	/**
	 * @return Returns the issn.
	 */
	public String getIssn() {
		return issn;
	}
	/**
	 * @param issn The issn to set.
	 */
	public void setIssn(String issn) {
		this.issn = issn;
	}
	/**
	 * @return Returns the keywords.
	 */
	public String getKeywords() {
		return keywords;
	}
	/**
	 * @param keywords The keywords to set.
	 */
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	/**
	 * @return Returns the language.
	 */
	public String getLanguage() {
		return language;
	}
	/**
	 * @param language The language to set.
	 */
	public void setLanguage(String language) {
		this.language = language;
	}
	/**
	 * @return Returns the location.
	 */
	public String getLocation() {
		return location;
	}
	/**
	 * @param location The location to set.
	 */
	public void setLocation(String location) {
		this.location = location;
	}
	/**
	 * @return Returns the meeting.
	 */
	public String getMeeting() {
		return meeting;
	}
	/**
	 * @param meeting The meeting to set.
	 */
	public void setMeeting(String meeting) {
		this.meeting = meeting;
	}
	/**
	 * @return Returns the notes.
	 */
	public String getNotes() {
		return notes;
	}
	/**
	 * @param notes The notes to set.
	 */
	public void setNotes(String notes) {
		this.notes = notes;
	}
	/**
	 * @return Returns the otherDate.
	 */
	public String getOtherDate() {
		return otherDate;
	}
	/**
	 * @param otherDate The otherDate to set.
	 */
	public void setOtherDate(String otherDate) {
		this.otherDate = otherDate;
	}
	/**
	 * @return Returns the otherTitle.
	 */
	public String getOtherTitle() {
		return otherTitle;
	}
	/**
	 * @param otherTitle The otherTitle to set.
	 */
	public void setOtherTitle(String otherTitle) {
		this.otherTitle = otherTitle;
	}
	/**
	 * @return Returns the pages.
	 */
	public String getPages() {
		return pages;
	}
	/**
	 * @param pages The pages to set.
	 */
	public void setPages(String pages) {
		this.pages = pages;
	}
	/**
	 * @return Returns the partNo.
	 */
	public String getPartNo() {
		return partNo;
	}
	/**
	 * @param partNo The partNo to set.
	 */
	public void setPartNo(String partNo) {
		this.partNo = partNo;
	}
	/**
	 * @return Returns the physical.
	 */
	public String getPhysical() {
		return physical;
	}
	/**
	 * @param physical The physical to set.
	 */
	public void setPhysical(String physical) {
		this.physical = physical;
	}
	/**
	 * @return Returns the place.
	 */
	public String getPlace() {
		return place;
	}
	/**
	 * @param place The place to set.
	 */
	public void setPlace(String place) {
		this.place = place;
	}
	/**
	 * @return Returns the pubCode.
	 */
	public int getPubCode() {
		return pubCode;
	}
	/**
	 * @param pubCode The pubCode to set.
	 */
	public void setPubCode(int pubCode) {
		this.pubCode = pubCode;
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
	 * @return Returns the pubName.
	 */
	public String getPubName() {
		return pubName;
	}
	/**
	 * @param pubName The pubName to set.
	 */
	public void setPubName(String pubName) {
		this.pubName = pubName;
	}
	/**
	 * @return Returns the purchaseType.
	 */
	public String getPurchaseType() {
		return purchaseType;
	}
	/**
	 * @param purchaseType The purchaseType to set.
	 */
	public void setPurchaseType(String purchaseType) {
		this.purchaseType = purchaseType;
	}
	/**
	 * @return Returns the rcDate.
	 */
	public String getRcDate() {
		return rcDate;
	}
	/**
	 * @param rcDate The rcDate to set.
	 */
	public void setRcDate(String rcDate) {
		this.rcDate = rcDate;
	}
	/**
	 * @return Returns the role.
	 */
	public String getRole() {
		return role;
	}
	/**
	 * @param role The role to set.
	 */
	public void setRole(String role) {
		this.role = role;
	}
	/**
	 * @return Returns the script.
	 */
	public String getScript() {
		return script;
	}
	/**
	 * @param script The script to set.
	 */
	public void setScript(String script) {
		this.script = script;
	}
	/**
	 * @return Returns the serAut.
	 */
	public String getSerAut() {
		return serAut;
	}
	/**
	 * @param serAut The serAut to set.
	 */
	public void setSerAut(String serAut) {
		this.serAut = serAut;
	}
	/**
	 * @return Returns the serName.
	 */
	public String getSerName() {
		return serName;
	}
	/**
	 * @param serName The serName to set.
	 */
	public void setSerName(String serName) {
		this.serName = serName;
	}
	/**
	 * @return Returns the serTitle.
	 */
	public String getSerTitle() {
		return serTitle;
	}
	/**
	 * @param serTitle The serTitle to set.
	 */
	public void setSerTitle(String serTitle) {
		this.serTitle = serTitle;
	}
	/**
	 * @return Returns the size.
	 */
	public String getSize() {
		return size;
	}
	/**
	 * @param size The size to set.
	 */
	public void setSize(String size) {
		this.size = size;
	}
	/**
	 * @return Returns the sponsor.
	 */
	public String getSponsor() {
		return sponsor;
	}
	/**
	 * @param sponsor The sponsor to set.
	 */
	public void setSponsor(String sponsor) {
		this.sponsor = sponsor;
	}
	/**
	 * @return Returns the stateRes.
	 */
	public String getStateRes() {
		return stateRes;
	}
	/**
	 * @param stateRes The stateRes to set.
	 */
	public void setStateRes(String stateRes) {
		this.stateRes = stateRes;
	}
	/**
	 * @return Returns the subCode.
	 */
	public int getSubCode() {
		return subCode;
	}
	/**
	 * @param subCode The subCode to set.
	 */
	public void setSubCode(int subCode) {
		this.subCode = subCode;
	}
	/**
	 * @return Returns the subName.
	 */
	public String getSubName() {
		return subName;
	}
	/**
	 * @param subName The subName to set.
	 */
	public void setSubName(String subName) {
		this.subName = subName;
	}
	/**
	 * @return Returns the summary.
	 */
	public String getSummary() {
		return summary;
	}
	/**
	 * @param summary The summary to set.
	 */
	public void setSummary(String summary) {
		this.summary = summary;
	}
	/**
	 * @return Returns the supCode.
	 */
	public int getSupCode() {
		return supCode;
	}
	/**
	 * @param supCode The supCode to set.
	 */
	public void setSupCode(int supCode) {
		this.supCode = supCode;
	}
	/**
	 * @return Returns the supName.
	 */
	public String getSupName() {
		return supName;
	}
	/**
	 * @param supName The supName to set.
	 */
	public void setSupName(String supName) {
		this.supName = supName;
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
	/**
	 * @return Returns the type.
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type The type to set.
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * @return Returns the venue.
	 */
	public String getVenue() {
		return venue;
	}
	/**
	 * @param venue The venue to set.
	 */
	public void setVenue(String venue) {
		this.venue = venue;
	}
	/**
	 * @return Returns the volumeNo.
	 */
	public String getVolumeNo() {
		return volumeNo;
	}
	/**
	 * @param volumeNo The volumeNo to set.
	 */
	public void setVolumeNo(String volumeNo) {
		this.volumeNo = volumeNo;
	}
	/**
	 * @return Returns the volumeRes.
	 */
	public String getVolumeRes() {
		return volumeRes;
	}
	/**
	 * @param volumeRes The volumeRes to set.
	 */
	public void setVolumeRes(String volumeRes) {
		this.volumeRes = volumeRes;
	}
	/**
	 * @return Returns the volumeTitle.
	 */
	public String getVolumeTitle() {
		return volumeTitle;
	}
	/**
	 * @param volumeTitle The volumeTitle to set.
	 */
	public void setVolumeTitle(String volumeTitle) {
		this.volumeTitle = volumeTitle;
	}
	/**
	 * @return Returns the yop.
	 */
	public String getYop() {
		return yop;
	}
	/**
	 * @param yop The yop to set.
	 */
	public void setYop(String yop) {
		this.yop = yop;
	}
		}





