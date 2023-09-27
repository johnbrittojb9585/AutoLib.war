package Lib.Auto.OrdInvProcessing;
import java.util.ArrayList;

public  class orderbean   {

    // ----------------------------------------------------- Instance Variables
    private int sno=0;
    private int max = 0;
    private String ord_no = null;
    private String ord_date = null;
    private int sup_code = 0;
    private int bud_code = 0;
    private String inv_no = null;
    private String Inv_Date = null;
    private double amount = 0.0;
    private int dept_code = 0;
    private String year = null;
    private String doc_type = null;
    private String cr_Deb = null;
    private String paid = null;
    private String pay_Date = null;
    private String mode = null;
    private String pay_details = null;
    private String add1 = null;
    private String add2 = null;
    private String sp_name = null;
    private String dept_name = null;
    private String bud_head = null;
    private String short_desc=null;
    
    private java.util.ArrayList al=null;



    // ----------------------------------------------------------- Properties

    public int getImax() {

	return max;
    }



    public void setImax(int max) {

        this.max = max;

    }
public String getIord() {

	return ord_no;
    }



    public void setIord(String ord_no) {

        this.ord_no = ord_no;

    }
public String getIordate() {

	return ord_date;
    }



    public void setIordate(String ord_date) {

        this.ord_date = ord_date;

    }
public int getIscode() {

	return sup_code;
    }



    public void setIscode(int sup_code) {

        this.sup_code = sup_code;

    }
public int getIbcode() {

	return bud_code;
    }



    public void setIbcode(int bud_code) {

        this.bud_code = bud_code;

    }
public String getIinvno() {

	return inv_no;
    }



    public void setIinvno(String inv_no) {

        this.inv_no = inv_no;

    }
public String getIinvdate() {

	return Inv_Date;
    }



    public void setIinvdate(String Inv_Date) {

        this.Inv_Date = Inv_Date;

    }
public double getIamount() {

	return amount;
    }



    public void setIamount(double amount) {

        this.amount = amount;

    }
public int getIdcode() {

	return dept_code;
    }



    public void setIdcode(int dept_code) {

        this.dept_code = dept_code;

    }
public String getIyear() {

	return year;
    }



    public void setIyear(String year) {

        this.year = year;

    }
public String getIdtype() {

	return doc_type;
    }



    public void setIdtype(String doc_type) {

        this.doc_type = doc_type;

    }
public String getIcrdeb() {

	return cr_Deb;
    }



    public void setIcrdeb(String cr_Deb) {

        this.cr_Deb = cr_Deb;

    }
public String getIpaid() {

	return paid;
    }



    public void setIpaid(String paid) {

        this.paid = paid;

    }
public String getIpaydate() {

	return pay_Date;
    }



    public void setIpaydate(String pay_Date) {

        this.pay_Date = pay_Date;

    }
public String getImode() {

	return mode;
    }



    public void setImode(String mode) {

        this.mode = mode;

    }
public String getIpaydet() {

	return pay_details;
    }



    public void setIpaydet(String pay_details) {

        this.pay_details = pay_details;

    }
public String getIadd1() {

	return add1;
    }



    public void setIadd1(String add1) {

        this.add1 = add1;

    }
public String getIadd2() {

	return add2;
    }



    public void setIadd2(String add2) {

        this.add2 = add2;

    }
public String getIsname() {

	return sp_name;
    }



    public void setIsname(String sp_name) {

        this.sp_name = sp_name;

    }
public String getIdname() {

	return dept_name;
    }



    public void setIdname(String dept_name) {

        this.dept_name = dept_name;

    }
public String getIbhead() {

	return bud_head;
    }



    public void setIbhead(String bud_head) {

        this.bud_head = bud_head;

    }

	 public ArrayList getAl() {

	return al;
    }



    public void setAl(ArrayList al) {

        this.al = al;

    }



	public String getShort_desc() {
		return short_desc;
	}



	public void setShort_desc(String short_desc) {
		this.short_desc = short_desc;
	}



	public int getSno() {
		return sno;
	}



	public void setSno(int sno) {
		this.sno = sno;
	}

}