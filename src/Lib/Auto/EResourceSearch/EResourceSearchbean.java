package Lib.Auto.EResourceSearch;
//import java.io.*;
import java.util.*;
//import java.sql.*;

public  class EResourceSearchbean   {

    // ----------------------------------------------------- Instance Variables

	private String ecode="";
	private String esite="";
	private String edetails="";
	private String etitle="";
	private String esubtitle="";
	private String etype="";	
	static String sqlquery;
    private java.util.ArrayList al=null;
	public static String getSqlquery() {
		return sqlquery;
	}
	public static void setSqlquery(String sqlquery) {
		EResourceSearchbean.sqlquery = sqlquery;
	}
	public java.util.ArrayList getAl() {
		return al;
	}
	public void setAl(java.util.ArrayList al) {
		this.al = al;
	}
	public String getEcode() {
		return ecode;
	}
	public void setEcode(String ecode) {
		this.ecode = ecode;
	}
	public String getEdetails() {
		return edetails;
	}
	public void setEdetails(String edetails) {
		this.edetails = edetails;
	}
	public String getEsite() {
		return esite;
	}
	public void setEsite(String esite) {
		this.esite = esite;
	}
	public String getEsubtitle() {
		return esubtitle;
	}
	public void setEsubtitle(String esubtitle) {
		this.esubtitle = esubtitle;
	}
	public String getEtitle() {
		return etitle;
	}
	public void setEtitle(String etitle) {
		this.etitle = etitle;
	}
	public String getEtype() {
		return etype;
	}
	public void setEtype(String etype) {
		this.etype = etype;
	}
	
}
