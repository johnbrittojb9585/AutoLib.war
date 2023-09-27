package Lib.Auto.Statistics;
import java.io.*;
import java.util.*;
import java.sql.*;

public  class statbean

    // ----------------------------------------------------- Instance Variables

{

    private String code = null;
    private String name = null;
    private String desc = null;
    private java.util.ArrayList al=null;
    
    private String txtdepartment = null;
    public String getTxtdepartment() {
		return txtdepartment;
	}



	public void setTxtdepartment(String txtdepartment) {
		this.txtdepartment = txtdepartment;
	}



	public String getTxtsubject() {
		return txtsubject;
	}



	public void setTxtsubject(String txtsubject) {
		this.txtsubject = txtsubject;
	}



	public String getTxtpublisher() {
		return txtpublisher;
	}



	public void setTxtpublisher(String txtpublisher) {
		this.txtpublisher = txtpublisher;
	}



	public String getTxtsupplier() {
		return txtsupplier;
	}



	public void setTxtsupplier(String txtsupplier) {
		this.txtsupplier = txtsupplier;
	}



	private String txtsubject = null;
    private String txtpublisher = null;
    private String txtsupplier = null;
	


    // ----------------------------------------------------------- Properties

    public String getCode() {
	
	return code;
    }


    
    public void setCode(String code) {

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


  // ----------------------------------------------------------- Properties

   	 public ArrayList getAl() {
	
	return al;
    }


    
    public void setAl(ArrayList al) {

        this.al = al;

    }

}
