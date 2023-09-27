package Lib.Auto.Fine;
//import java.io.*;
import java.util.*;
//import java.sql.*;

public  class Finebean   {

    // ----------------------------------------------------- Instance Variables

    private int fcode = 0;
    private String gcode = null;
    private String fperiod = null;
    private String famount = null;
    private String type = null;
    private java.util.ArrayList al=null;
	
   

    // ----------------------------------------------------------- Properties

    public int getFcode() {
	
	return fcode;
    }


    
    public void setFcode(int fcode) {

        this.fcode = fcode;

    }

    // ----------------------------------------------------------- Properties

    public String getGcode() {
	
	return gcode;
    }


    
    public void setGcode(String gcode) {

        this.gcode = gcode;

    }

//-------------------------------------
    
    public String getFperiod() {

	return fperiod;

    }



    public void setFperiod(String fperiod) {

        this.fperiod = fperiod;

    }
//------------------------------------------------

    
    public String getFamount() {

	return famount;

    }


    
    public void setFamount(String famount) {

        this.famount = famount;

    }


 //--------------------------------------   
    public String getType() {

	return type;

    }


    public void setType(String type) {

        this.type = type;

    }
	
	
	
	 public ArrayList getAl() {
	
	return al;
    }


    
    public void setAl(ArrayList al) {

        this.al = al;

    }

}
