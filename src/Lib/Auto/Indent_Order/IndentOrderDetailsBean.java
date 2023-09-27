package Lib.Auto.Indent_Order;

import java.io.Serializable;
import java.util.ArrayList;

public class IndentOrderDetailsBean implements Serializable {

	/**
	 * Declaration Begins
	 * 
	 */
	
	
	private static final long serialVersionUID = 1L;
	// ----------------------------------------------------- Instance Variables
    private int sno=0;
    private int max = 0;
    
    private String ordno = null; 
    private String orddate = null;
    private String quoteno = null;
    private String quotedate = null;
    private String supplier = null;
    private int supcode;
    
    private String indentno = null;  
    private int titleno;
    private String author = null;  
    private String title = null;
    private String titlestatus = null;
    private int apprvoedcopy; 
    
    private String remarks = null;
    private String add1 = null;
    private String add2 = null;
    private String status = null;
    
    /**
     * Definition Begin
     * @return
     */
    
    
    
    
	public String getAdd1() {
		return add1;
	}
	public void setAdd1(String add1) {
		this.add1 = add1;
	}
	public String getAdd2() {
		return add2;
	}
	public void setAdd2(String add2) {
		this.add2 = add2;
	}
	public int getApprvoedcopy() {
		return apprvoedcopy;
	}
	public void setApprvoedcopy(int apprvoedcopy) {
		this.apprvoedcopy = apprvoedcopy;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getIndentno() {
		return indentno;
	}
	public void setIndentno(String indentno) {
		this.indentno = indentno;
	}
	public int getMax() {
		return max;
	}
	public void setMax(int max) {
		this.max = max;
	}
	public String getOrddate() {
		return orddate;
	}
	public void setOrddate(String orddate) {
		this.orddate = orddate;
	}
	public String getOrdno() {
		return ordno;
	}
	public void setOrdno(String ordno) {
		this.ordno = ordno;
	}
	public String getQuotedate() {
		return quotedate;
	}
	public void setQuotedate(String quotedate) {
		this.quotedate = quotedate;
	}
	public String getQuoteno() {
		return quoteno;
	}
	public void setQuoteno(String quoteno) {
		this.quoteno = quoteno;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public int getSno() {
		return sno;
	}
	public void setSno(int sno) {
		this.sno = sno;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getSupcode() {
		return supcode;
	}
	public void setSupcode(int supcode) {
		this.supcode = supcode;
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
	public String getTitlestatus() {
		return titlestatus;
	}
	public void setTitlestatus(String titlestatus) {
		this.titlestatus = titlestatus;
	}
	public int getTitleno() {
		return titleno;
	}
	public void setTitleno(int titleno) {
		this.titleno = titleno;
	}
    

}