package Lib.Auto.EBookImport;

public class EBookDataBean {
	

    
    private String accessNo;
    private String callNo;
    private String rcDate;
    private String title;
    private String edition;
    private String authorName;
    private String pubName;
    private String yop;
    private String subName;
    private int subCode;
    private int pubCode;
    private int branchCode;
    private String branchName;
    private int deptCode;
    private String deptName;
    private String addfield1="";
    private String addfield2="";
    private String keywords;
    private String branch;
    private String supName;
    private int supCode;
    private String invoiceno;
    private String idate;
    private String purchaseType;
    private String isbn;
    private double price;
    private String url;
    
    private String type = "UG";
    private String content = "";
    private String pages = "";
    private String doc = "EBOOK";
    private String role="AUTHOR";
    
    
    
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
		 * @param edition The edition to set.
		 */
		public void setEdition(String edition) {
			this.edition = edition;
		}
		
		/**
		 * @return Returns the edition.
		 */
		public String getEdition() {
			return edition;
		}
		


	/**
	 * @param author The author to set.
	 */
	public void setAuthorName(String authorName) {
		authorName = authorName.trim();
		if(authorName.length()>0)
		{
			if(authorName.charAt(authorName.length()-1)==';')
			{
				authorName = authorName.substring(0, authorName.length()-1);
			}
		}
		this.authorName = authorName;
	}
	
	/**
	 * @return Returns the author name.
	 */
	public String getAuthorName() {
		return authorName;
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
	 * @param pubName The pubName to set.
	 */
	public void setPubName(String pubName) {
		this.pubName = pubName;
	}
	/**
	 * @return Returns the pubName.
	 */
	public String getPubName() {
		return pubName;
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
	 * @return Returns the subName.
	 */
	
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
	
	/**
	 * @return Returns the contents.
	 */
	public String getContent() {
		return content;
	}
	/**
	 * @param contents The contents to set.
	 */
	public void setContent(String content) {
		this.content = content;
	}
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	public String getSupName() {
		return supName;
	}
	public void setSupName(String supName) {
		this.supName = supName;
	}
	public int getSupCode() {
		return supCode;
	}
	public void setSupCode(int supCode) {
		this.supCode = supCode;
	}
	public String getInvoiceno() {
		return invoiceno;
	}
	public void setInvoiceno(String invoiceno) {
		this.invoiceno = invoiceno;
	}
	
	public String getPurchaseType() {
		return purchaseType;
	}
	public void setPurchaseType(String purchaseType) {
		this.purchaseType = purchaseType;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getIdate() {
		return idate;
	}
	public void setIdate(String idate) {
		this.idate = idate;
	}

	
}
