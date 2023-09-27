package Lib.Auto.Indent_Invoice;

import java.io.Serializable;
import java.util.ArrayList;

public class IndentInvDetailsBean implements Serializable {


	  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// ----------------------------------------------------- Instance Variables
    private int sno=0;
    private int max = 0;
    
    private String invoiceno = null;
    private String invoicedate = null;
    
    private String ordno = null;
    private int supcode;
    private String supplier = null;
    private int titleno;     
    private String title = null;
    private String titlestatus = null;
    private String indentno = null;
       
    private int copies;
    private String author;
    private double bcost = 0;
    private String bcurrency;
    private double bprice = 0.0;
    private double discount = 0.0;
    private double amount = 0.0;
    
    private String remarks = null;
    private String add1 = null;
    private String add2 = null;
    
    private String flag = "PENDING";
    
    
    
    
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
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}	
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public double getBcost() {
		return bcost;
	}
	public void setBcost(double bcost) {
		this.bcost = bcost;
	}
	public String getBcurrency() {
		return bcurrency;
	}
	public void setBcurrency(String bcurrency) {
		this.bcurrency = bcurrency;
	}
	public double getBprice() {
		return bprice;
	}
	public void setBprice(double bprice) {
		this.bprice = bprice;
	}
	public int getCopies() {
		return copies;
	}
	public void setCopies(int copies) {
		this.copies = copies;
	}
	public double getDiscount() {
		return discount;
	}
	public void setDiscount(double discount) {
		this.discount = discount;
	}
	public String getInvoicedate() {
		return invoicedate;
	}
	public void setInvoicedate(String invoicedate) {
		this.invoicedate = invoicedate;
	}
	public String getInvoiceno() {
		return invoiceno;
	}
	public void setInvoiceno(String invoiceno) {
		this.invoiceno = invoiceno;
	}
	public int getMax() {
		return max;
	}
	public void setMax(int max) {
		this.max = max;
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
	public int getSupcode() {
		return supcode;
	}
	public void setSupcode(int supcode) {
		this.supcode = supcode;
	}
	public int getTitleno() {
		return titleno;
	}
	public void setTitleno(int titleno) {
		this.titleno = titleno;
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
	public String getOrdno() {
		return ordno;
	}
	public void setOrdno(String ordno) {
		this.ordno = ordno;
	}
	public String getSupplier() {
		return supplier;
	}
	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}
	public String getIndentno() {
		return indentno;
	}
	public void setIndentno(String indentno) {
		this.indentno = indentno;
	}
    
       
}