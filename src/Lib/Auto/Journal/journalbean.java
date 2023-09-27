package Lib.Auto.Journal;
import java.io.*;
import java.util.*;
import java.sql.*;

public  class journalbean   {

    // ----------------------------------------------------- Instance Variables
    
    private String code = null;
    private String freq = null;
    private String country = null;
    private String lang = null;
    private String deli = null;
    private String type = null;
    private String remarks = null;
    private String name = null;
    private String issn = null;
    private String dname = null;
    private String pname = null;
    private String sname = null;
    private String Doc_Type = null;
    private java.util.ArrayList al=null;



    // ----------------------------------------------------------- Properties

    public String getJcode() {

	return code;
    }



    public void setJcode(String code) {

        this.code = code;

    }

//-------------------------------------
	public String getJfreq() {

	return freq;
    }



    public void setJfreq(String freq) {

        this.freq = freq;

    }

    public String getJcountry() {

	return country;
    }



    public void setJcountry(String country) {

        this.country = country;

    }

    public String getJlang() {

	return lang;
    }



    public void setJlang(String lang) {

        this.lang = lang;

    }

    public String getJdeli() {

	return deli;
    }



    public void setJdeli(String deli) {

        this.deli = deli;

    }

     public String getJtype() {

	return type;
    }



    public void setJtype(String type) {

        this.type = type;

    }

     public String getJremarks() {

	return remarks;
    }



    public void setJremarks(String remarks) {

        this.remarks = remarks;

    }


    public String getJname() {

	return name;

    }



    public void setJname(String name) {

        this.name = name;

    }
//------------------------------------------------


    public String getJissn() {

	return issn;

    }



    public void setJissn(String issn) {

        this.issn = issn;

    }

    public String getJdname() {

	return dname;

    }



    public void setJdname(String dname) {

        this.dname = dname;

    }

    public String getJsname() {

	return sname;

    }



    public void setJsname(String sname) {

        this.sname = sname;

    }

    public String getJpname() {

	return pname;

    }



    public void setJpname(String pname) {

        this.pname = pname;

    }



	 public ArrayList getAl() {

	return al;
    }



    public void setAl(ArrayList al) {

        this.al = al;

    }



	public  String getDoc_Type() {
		return Doc_Type;
	}



	public void setDoc_Type(String doc_Type) {
		Doc_Type = doc_Type;
	}

}
