package Lib.Auto.Currency;
import java.io.*;
import java.util.*;
import java.sql.*;

public  class CurrencyBean   {

    // ----------------------------------------------------- Instance Variables

    private int code;
    private String currency="";
    private String indian_value ="";
    private String remarks="";

    private String bcurrency="";
    private String bindian_value="";
    
    private java.util.ArrayList al=null;
	
//-------------------------------------
    
    public String getbcurrency() {

	return bcurrency;

    }


    
    public void setbcurrency(String string) {

    	bcurrency = string;

    }
	//-------------------------
	
//-------------------------------------
    
    public String getbindian_value() {

	return bindian_value;

    }


    
    public void setbindian_value(String string) {

    	bindian_value = string;

    }
	//-------------------------

    // ----------------------------------------------------------- Properties

    public int getCode() {

	return code;
    }


    
    public void setCode(int i) {

        code = i;

    }

//-------------------------------------
    
    public String getCurrency() {

	return currency;

    }


    
    public void setCurrency(String string) {

        currency = string;

    }
//------------------------------------------------

    
    

    //-------------------------------------------
 public String getRemarks() {

	return remarks;

    }



    public void setRemarks(String string) {

        this.remarks = string;

    }
   //---------------------------------

  public ArrayList getAl() {
	
	return al;
    }


    
    public void setAl(ArrayList al) {

        this.al = al;

    }



	/**
	 * @return Returns the indian_value.
	 */
	public String getIndian_value() {
		return indian_value;
	}



	/**
	 * @param indian_value The indian_value to set.
	 */
	public void setIndian_value(String indian_value) {
		this.indian_value = indian_value;
	}



	

}
