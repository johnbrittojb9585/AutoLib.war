package Lib.Auto.PubSup;

import java.util.*;

public  class PubSupBean   {

    // ----------------------------------------------------- Instance Variables

    private int sp_code = 0;
    private String sp_name = null;
    private String short_desc = null;
    private String address1 = null;
    private String address2 = null;
    private String city = null;
    private String state = null;
    private String country = null;
    private String pincode = null;
    private String phone = null;
    private String fax = null;
    private String emailid = null;
    private String urlid = null;
    private String sp_type = null;
	static java.util.ArrayList al=null;
	


    // ----------------------------------------------------------- Properties

    public int getCode() {

	return sp_code;
    }


    
    public void setCode(int sp_code) {

        this.sp_code = sp_code;

    }

//-------------------------------------
    
    public String getName() {

	return sp_name;

    }


    
    public void setName(String sp_name) {

        this.sp_name = sp_name;

    }
//------------------------------------------------

    
    public String getDesc() {

	return short_desc;

    }



    public void setDesc(String short_desc) {

        this.short_desc = short_desc;

    }

    //-------------------------------------------
 public String getAdd1() {

	return address1;

    }



    public void setAdd1(String address1) {

        this.address1 = address1;

    }
    //-----------------------------------
     public String getAdd2() {

	return address2;

    }



    public void setAdd2(String address2) {

        this.address2 = address2;

    }

    //------------------------------------------
 public String getCity() {

	return city;

    }



    public void setCity(String city) {

        this.city = city;

    }

 //--------------------------------------

 public String getState() {

	return state;

    }



    public void setState(String state) {

        this.state = state;

    }

    //--------------------------------------

 public String getCountry() {

	return country;

    }



    public void setCountry(String country) {

        this.country = country;

    }

    //--------------------------------------

 public String getPin() {

	return pincode;

    }



    public void setPin(String pincode) {

        this.pincode = pincode;

    }


    //--------------------------------------

 public String getPhone() {

	return phone;

    }



    public void setPhone(String phone) {

        this.phone = phone;

    }

    //--------------------------------------

 public String getFax() {

	return fax;

    }



    public void setFax(String fax) {

        this.fax = fax;

    }

    //--------------------------------------

 public String getEmail() {

	return emailid;

    }



    public void setEmail(String emailid) {

        this.emailid = emailid;

    }

    //--------------------------------------

 public String getUrl() {

	return urlid;

    }



    public void setUrl(String urlid) {

        this.urlid = urlid;

    }

     public String getType() {

	return sp_type;

    }



    public void setType(String sp_type) {

        this.sp_type = sp_type;

    }

  public ArrayList getAl() {
	
	return al;
    }


    
    public void setAl(ArrayList al) {

        this.al = al;

    }

}
