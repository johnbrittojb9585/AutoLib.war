package Lib.Auto.Binding_Books;

import java.util.ArrayList;

public class BookBindingBean {
	
	private String access_no="";
	private String title="";
	private String author="";
	private int binder=0;
	private String binderName="";
	private String document="";
	private String date="";
	private ArrayList al=null;
	private String avail="";
	
	
	public String getAccess_no() {
		return access_no;
	}
	public void setAccess_no(String access_no) {
		this.access_no = access_no;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getDocument() {
		return document;
	}
	public void setDocument(String document) {
		this.document = document;
	}
	public int getBinder() {
		return binder;
	}
	public void setBinder(int binder) {
		this.binder = binder;
	}
	public String getBinderName() {
		return binderName;
	}
	public void setBinderName(String binderName) {
		this.binderName = binderName;
	}
	public ArrayList getAl() {
		return al;
	}
	public void setAl(ArrayList al) {
		this.al = al;
	}
	public String getAvail() {
		return avail;
	}
	public void setAvail(String avail) {
		this.avail = avail;
	}

}
