package Lib.Auto.Binding;
//import java.io.*;
import java.util.*;
//import java.sql.*;

public  class BindingBean   {

    // ----------------------------------------------------- Instance Variables

    private int code;
    private String name;
    private String desc;
    private String add1;
    private String add2 ;
    private String city;
    private String state;
    private String country;
    private String pin;
    private String phone;
    private String fax;
    private String email ;
    private String url;
    
	static java.util.ArrayList al=null;
	


    // ----------------------------------------------------------- Properties

    public int getCode() {

	return code;
    }


    
    public void setCode(int code) {

        this.code = code;

    }

//-------------------------------------
    
    public String getName() {

	return name;

    }


    
    public void setName(String name) {

        this.name = name;

    }
//------------------------------------------------

    
    public String getDesc() {

	return desc;

    }



    public void setDesc(String desc) {

        this.desc = desc;

    }

    //-------------------------------------------
 public String getAdd1() {

	return add1;

    }



    public void setAdd1(String add1) {

        this.add1 = add1;

    }
    //-----------------------------------
     public String getAdd2() {

	return add2;

    }



    public void setAdd2(String add2) {

        this.add2 = add2;

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

	return pin;

    }



    public void setPin(String pin) {

        this.pin = pin;

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

	return email;

    }



    public void setEmail(String email) {

        this.email = email;

    }

    //--------------------------------------

 public String getUrl() {

	return url;

    }



    public void setUrl(String url) {

        this.url = url;

    }

    



    

  public ArrayList getAl() {
	
	return al;
    }


    
    public void setAl(ArrayList al) {

        this.al = al;

    }



}

