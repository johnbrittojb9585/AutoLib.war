package Lib.Auto.TransMas;

import java.io.Serializable;

public class TransMasBean implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int tcode;
	private String thead="";
	private String tamount="";
	private String tremarks="";
	private String taddfield1="";
	
	
	
	public String getTaddfield1() {
		return taddfield1;
	}
	public void setTaddfield1(String taddfield1) {
		this.taddfield1 = taddfield1;
	}
	public String getTamount() {
		return tamount;
	}
	public void setTamount(String tamount) {
		this.tamount = tamount;
	}
	public int getTcode() {
		return tcode;
	}
	public void setTcode(int tcode) {
		this.tcode = tcode;
	}
	public String getThead() {
		return thead;
	}
	public void setThead(String thead) {
		this.thead = thead;
	}
	public String getTremarks() {
		return tremarks;
	}
	public void setTremarks(String tremarks) {
		this.tremarks = tremarks;
	}
	
	
	
	
	
	
	
	
	
	
	
}