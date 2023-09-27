package Lib.Auto.JNL_Payment;

import java.io.Serializable;
import java.util.ArrayList;

public class JnlPaymentBean implements Serializable {


	  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// ----------------------------------------------------- Instance Variables
    private int sno=0;    
    
    private String invno = null;    
    private int supcode;

    private String paymentsenddate = null;
    private String chequedate = null;    
    
    private String checkno;
    private String transdetails = null;
    private int paymentno;
    private double amount = 0.0;
    private double invoiceamount = 0.0;
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
        
    
    public String getInvNo() {

	return invno;
    }



    public void setInvNo(String invno) {

        this.invno = invno;

    }
   
   
    
    
    

    public String getPaymentsenddate() {

     	return paymentsenddate;
    }
    
    public void setPaymentsenddate(String paymentsenddate) {

        this.paymentsenddate = paymentsenddate;

    }    
    
    
    public String getChequedate() {

     	return chequedate;
    }
    
    public void setChequedate(String chequedate) {

        this.chequedate = chequedate;

    }      
       
    
    
    
        
    
    public String getCheckno() {

     	return checkno;
    }
    
    public void setCheckno(String checkno) {

        this.checkno = checkno;
    }    
    
  
       
    public String getTransdetails() {

    	return transdetails;
    }

    public void setTransdetails(String transdetails) {

        this.transdetails = transdetails;
    }        
    
    public int getPaymentno() {

    	return paymentno;
    }

    public void setPaymentno(int paymentno) {

        this.paymentno = paymentno;
    }  
       
    public double getNetamount() {

     	return amount;
    }
    
    public void setNetamount(double amount) {

        this.amount = amount;
    }

    
    public double getInvoiceamount() {

     	return invoiceamount;
    }
    
    public void setInvoiceamount(double invoiceamount) {

        this.invoiceamount = invoiceamount;
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