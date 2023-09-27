package Lib.Auto.Journal_Artical;
import java.io.*;
import java.util.*;
import java.sql.*;

public  class journalArtbean   {

    // ----------------------------------------------------- Instance Variables
    
    private String code = null;
    private String name = null;
    private String atlno = null;
    private String bvolno = null;
    private String title = null;
    private String author = null;
    private String volno = null;
    private String issueno = null;
    
    private String issueyear = null;
    private String issuemonth = null;
    private String pages = null;
    private String subject = null;
    private String keywords = null;
    private String abstracts = null;
    private String atl_abstracts = null;
    private String Issue_acc_no = null;
    private java.util.ArrayList al=null;



    // ----------------------------------------------------------- Properties

    public String getJcode() {

	return code;
    }



    public void setJcode(String code) {

        this.code = code;

    }

//-------------------------------------
	


    public String getJname() {

	return name;

    }



    public void setJname(String name) {

        this.name = name;

    }
//------------------------------------------------



	public String getAbstracts() {
		return abstracts;
	}



	public void setAbstracts(String abstracts) {
		this.abstracts = abstracts;
	}



	public java.util.ArrayList getAl() {
		return al;
	}



	public void setAl(java.util.ArrayList al) {
		this.al = al;
	}



	public String getAtlno() {
		return atlno;
	}



	public void setAtlno(String atlno) {
		this.atlno = atlno;
	}



	public String getAuthor() {
		return author;
	}



	public void setAuthor(String author) {
		this.author = author;
	}



	public String getBvolno() {
		return bvolno;
	}



	public void setBvolno(String bvolno) {
		this.bvolno = bvolno;
	}



	public String getIssuemonth() {
		return issuemonth;
	}



	public void setIssuemonth(String issuemonth) {
		this.issuemonth = issuemonth;
	}



	public String getIssueno() {
		return issueno;
	}



	public void setIssueno(String issueno) {
		this.issueno = issueno;
	}



	public String getIssueyear() {
		return issueyear;
	}



	public void setIssueyear(String issueyear) {
		this.issueyear = issueyear;
	}



	public String getKeywords() {
		return keywords;
	}



	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}



	public String getPages() {
		return pages;
	}



	public void setPages(String pages) {
		this.pages = pages;
	}



	public String getSubject() {
		return subject;
	}



	public void setSubject(String subject) {
		this.subject = subject;
	}



	public String getTitle() {
		return title;
	}



	public void setTitle(String title) {
		this.title = title;
	}



	public String getVolno() {
		return volno;
	}



	public void setVolno(String volno) {
		this.volno = volno;
	}

	public String getAtl_abstracts() {
		return atl_abstracts;
	}



	public void setAtl_abstracts(String atl_abstracts) {
		this.atl_abstracts = atl_abstracts;
	}



	public String getIssue_acc_no() {
		return Issue_acc_no;
	}



	public void setIssue_acc_no(String issue_acc_no) {
		Issue_acc_no = issue_acc_no;
	}
    

}
