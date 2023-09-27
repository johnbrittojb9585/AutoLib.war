package Lib.Auto.Holiday;
import java.io.*;
import java.util.*;
import java.sql.*;

public  class Holidaybean   {

    // ----------------------------------------------------- Instance Variables
    
    static String leavefrom = null;
    static String leaveto = null;
    static String remarks = null;

	static java.util.ArrayList al=null;
	
   

    // ----------------------------------------------------------- Properties

    public String getFrom() {
	
	return leavefrom;
    }


    
    public void setFrom(String leavefrom) {

        this.leavefrom = leavefrom;

    }

//-------------------------------------
    
    public String getTo() {

	return leaveto;

    }


    
    public void setTo(String leaveto) {

        this.leaveto = leaveto;

    }
//------------------------------------------------

    
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

}
