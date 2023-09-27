package Lib.Auto.Branch;
import java.io.*;
import java.util.*;
import java.sql.*;

public  class BranchBean   {

    // ----------------------------------------------------- Instance Variables
    
	private int code;
	private String name="";
	private String desc="";
    //static String email = null;
	private java.util.ArrayList al=null;
	
   

    // ----------------------------------------------------------- Properties

    public int getCode() {
	
	return code;
    }


    
    public void setCode(int i) {

        code = i;

    }

//-------------------------------------
    
    public String getName() {

	return name;

    }


    
    public void setName(String string) {

        name = string;

    }
//------------------------------------------------

    
    public String getDesc() {

	return desc;

    }


    
    public void setDesc(String string) {

        desc = string;

    }


 //--------------------------------------   
  public ArrayList getAl() {
	
	return al;
    }


    
    public void setAl(ArrayList al) {

        this.al = al;

    }

}
