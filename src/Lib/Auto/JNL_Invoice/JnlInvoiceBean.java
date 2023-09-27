package Lib.Auto.JNL_Invoice;

import java.io.Serializable;
import java.util.ArrayList;

public class JnlInvoiceBean implements Serializable {


	  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// ----------------------------------------------------- Instance Variables
    private int sno=0;    
    
    private String ordno = null;    
    private int supcode;

    private String receiveddate = null;
    private String invoicedate = null;    
    
    private String invoiceno;
    private String paymentflag = null;
    private String paymentno =null;
    private double amount = 0.0;
    
    private String remarks = null;
    private String add1 = null;
    private String add2 = null;
    
    
    private String supplier = null;
    
    
    private java.util.ArrayList al=null;


    

    
    public String getSupplier() {

	    return supplier;
    }
    
    public void setSupplier(String sup_name) {

        this.supplier = sup_name;
    }
    
    public int getSupCode() {

      	return supcode;
    }

    public void setSupCode(int supcode) {

        this.supcode = supcode;

    }
        
    
    public String getOrdNo() {

	return ordno;
    }



    public void setOrdNo(String ord_no) {

        this.ordno = ord_no;

    }
   
   
    
    
    

    public String getReceiveddate() {

     	return receiveddate;
    }
    
    public void setReceiveddate(String receiveddate) {

        this.receiveddate = receiveddate;

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
    
  
       
    public String getPaymentflag() {

    	return paymentflag;
    }

    public void setPaymentflag(String paymentflag) {

        this.paymentflag = paymentflag;
    }        
    
    public String getPaymentno() {

    	return paymentno;
    }

    public void setPaymentno(String paymentno) {

        this.paymentno = paymentno;
    }  
       
    public double getNetamount() {

     	return amount;
    }
    
    public void setNetamount(double amount) {

        this.amount = amount;
    }

    
    
    
    
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

	public String getRemarks() {
		return remarks;
	}
	
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	
	
	
	
   
   
    public ArrayList getAl() {

         return al;
    }

    public void setAl(ArrayList al) {

        this.al = al;
    }
    
    
    

	public int getSno() {
		return sno;
	}

	public void setSno(int sno) {
		this.sno = sno;
	}

}