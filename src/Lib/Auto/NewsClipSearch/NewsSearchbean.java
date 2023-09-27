package Lib.Auto.NewsClipSearch;
//import java.io.*;
import java.util.*;
//import java.sql.*;

public  class NewsSearchbean   {

    // ----------------------------------------------------- Instance Variables

	private String ncode="";
	private String nname="";
	private String ntype="";
	private String ndate="";
	private String pno="";
	private String ntitle="";
	private String nsubject="";
	private String nkey="";
	private String nabstract="";
	private String ncontent="";
	static String sqlquery;
    private java.util.ArrayList al=null;
	//-------------------------------------------
	public java.util.ArrayList getAl() {
		return al;
	}
	public void setAl(java.util.ArrayList al) {
		this.al = al;
	}
	public String getNabstract() {
		return nabstract;
	}
	public void setNabstract(String nabstract) {
		this.nabstract = nabstract;
	}
	public String getNcode() {
		return ncode;
	}
	public void setNcode(String ncode) {
		this.ncode = ncode;
	}
	public String getNcontent() {
		return ncontent;
	}
	public void setNcontent(String ncontent) {
		this.ncontent = ncontent;
	}
	public String getNdate() {
		return ndate;
	}
	public void setNdate(String ndate) {
		this.ndate = ndate;
	}
	public String getNkey() {
		return nkey;
	}
	public void setNkey(String nkey) {
		this.nkey = nkey;
	}
	public String getNname() {
		return nname;
	}
	public void setNname(String nname) {
		this.nname = nname;
	}
	public String getNsubject() {
		return nsubject;
	}
	public void setNsubject(String nsubject) {
		this.nsubject = nsubject;
	}
	public String getNtitle() {
		return ntitle;
	}
	public void setNtitle(String ntitle) {
		this.ntitle = ntitle;
	}
	public String getNtype() {
		return ntype;
	}
	public void setNtype(String ntype) {
		this.ntype = ntype;
	}
	public String getPno() {
		return pno;
	}
	public void setPno(String pno) {
		this.pno = pno;
	}
	public static String getSqlquery() {
		return sqlquery;
	}
	public static void setSqlquery(String sqlquery) {
		NewsSearchbean.sqlquery = sqlquery;
	}
    

}
