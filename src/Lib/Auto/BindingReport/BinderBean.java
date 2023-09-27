package Lib.Auto.BindingReport;
import java.io.*;
import java.util.*;
import java.sql.*;

public class BinderBean {
	
	
	private int orderno;
	public int getOrderno() {
		return orderno;
	}
	public void setOrderno(int orderno) {
		this.orderno = orderno;
	}
	public String getAccessno() {
		return accessno;
	}
	public void setAccessno(String accessno) {
		this.accessno = accessno;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getBinder() {
		return binder;
	}
	public void setBinder(String binder) {
		this.binder = binder;
	}
	public String getDoctype() {
		return doctype;
	}
	public void setDoctype(String doctype) {
		this.doctype = doctype;
	}
	public String getSdate() {
		return sdate;
	}
	public void setSdate(String sdate) {
		this.sdate = sdate;
	}
	public String getRdate() {
		return rdate;
	}
	public void setRdate(String rdate) {
		this.rdate = rdate;
	}
	private String accessno;
	private String title;
	private String author;
	private String binder;
	private String doctype;
	private String sdate;
	private String rdate;

}
