package Lib.Auto.JNL_Enquiry;

import java.io.Serializable;
import java.util.ArrayList;

public class JnlenquiryBean implements Serializable {


	  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// ----------------------------------------------------- Instance Variables
    private int sno=0;
    private int max = 0;
    
    //private String ordNo = null;
    //private String ord_date = null;
    private String quote_no = null;
    private String quote_date = null;
    
    private int sp_code;
    private String remarks = null;
    private String add1 = null;
    private String add2 = null;
        
    
    private java.util.ArrayList al=null;


    private String jnl_accno = null;
    private String jnl_name = null;  

    // ----------------------------------------------------------- Properties

    
    public String getJNL_Accno() {

    	return jnl_accno;
        }



        public void setJNL_Accno(String jnl_accno) {

            this.jnl_accno = jnl_accno;

        }
       
    
    
    
    
    
    
    
    public int getImax() {

	return max;
    }



    public void setImax(int max) {

        this.max = max;

    }
    
 /*   public String getOrdNo() {

	return ordNo;
    }



    public void setOrdNo(String ord_no) {

        this.ordNo = ord_no;

    }
   
    public String getOrdate() {

	return ord_date;
    }



    public void setOrdate(String ord_date) {

        this.ord_date = ord_date;

    }*/
    
    
    public String getQuoteNo() {

    	return quote_no;
    }

    public void setQuoteNo(String quote_no) {

        this.quote_no = quote_no;

    }
       
    public String getQuotedate() {

     	return quote_date;
    }

    public void setQuotedate(String quote_date) {

        this.quote_date = quote_date;

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
	
	
	
	
	public String getJNLName() {

	    return jnl_name;
    }
    
    public void setJNLName(String jnl_name) {

        this.jnl_name = jnl_name;
    }
    
    public int getSupCode() {

	    return sp_code;
    }
    
    public void setSupCode(int sp_code) {

        this.sp_code = sp_code;
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