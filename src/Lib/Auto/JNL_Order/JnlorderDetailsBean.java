package Lib.Auto.JNL_Order;

import java.io.Serializable;
import java.util.ArrayList;

public class JnlorderDetailsBean implements Serializable {


	  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// ----------------------------------------------------- Instance Variables
    private int sno=0;
    private int max = 0;
    
    private String ordno = null;    
    private int supcode;
    private int jnlcode;     
    
    private String subs_fromdt = null;
    private String subs_todt = null;
    
    private int noofissue;
    private String volno;
    private double bcost = 0;
    private String bcurrency = null;
    private double bprice = 0.0;
    private double discount = 0.0;
    private double amount = 0.0;
    
    private String remarks = null;
    private String add1 = null;
    private String add2 = null;
    private String flag = "process";
    
        
    private String orddate = null;
    private String quoteno = null;
    private String quotedate = null;
    private String supplier = null;
    private String journal = null;  
    private String frequency = null;  
    
    private java.util.ArrayList al=null;


    

    // ----------------------------------------------------------- Properties

    public String getOrdDate() {

	    return orddate;
    }
    
    public void setOrdDate(String orddate) {

        this.orddate = orddate;
    }

    public String getQuoteNo() {

	    return quoteno;
    }
    
    public void setQuoteNo(String quoteno) {

        this.quoteno = quoteno;
    }
    
    public String getQuoteDate() {

	    return quotedate;
    }
    
    public void setQuoteDate(String quotedate) {

        this.quotedate = quotedate;
    }
    
    
    public String getSupplier() {

	    return supplier;
    }
    
    public void setSupplier(String sup_name) {

        this.supplier = sup_name;
    }
    
    
    
    public String getJournal() {

	    return journal;
    }
    
    public void setJournal(String jnl_name) {

        this.journal = jnl_name;
    }
    
    public String getFrequency() {

	    return frequency;
    }
    
    public void setFrequency(String frequency) {

        this.frequency = frequency;
    }
    
    
    public int getJnlCode() {

    	return jnlcode;
        }



        public void setJnlCode(int jnl_accno) {

            this.jnlcode = jnl_accno;

        }
       
    
        public int getSupCode() {

        	return supcode;
            }



            public void setSupCode(int supcode) {

                this.supcode = supcode;

            }
           
        
    
    
    
    
    
    public int getImax() {

	return max;
    }



    public void setImax(int max) {

        this.max = max;

    }
    
    public String getOrdNo() {

	return ordno;
    }



    public void setOrdNo(String ord_no) {

        this.ordno = ord_no;

    }
   
   
    
    
    

    public String getSubsfrmdate() {

     	return subs_fromdt;
    }
    
    public void setSubsfrmdate(String subs_fromdt) {

        this.subs_fromdt = subs_fromdt;

    }    
    
    
    public String getSubstodate() {

     	return subs_todt;
    }
    
    public void setSubstodate(String subs_todt) {

        this.subs_todt = subs_todt;

    }      
       
    
    
    
    public int getNoofissue() {

     	return noofissue;
    }
    
    public void setNoofissue(int noofissue) {

        this.noofissue = noofissue;
    }      
    
    public String getVolumeNo() {

     	return volno;
    }
    
    public void setVolumeNo(String volno) {

        this.volno = volno;
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
    
    public double getDiscount() {

     	return discount;
    }
    
    public void setDiscount(double discount) {

        this.discount = discount;
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

    public String getFlag() {
    	
	    return flag;
    }

    public void setFlag(String flag) {
    	
        this.flag = flag;
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