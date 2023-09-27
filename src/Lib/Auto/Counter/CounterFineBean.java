package Lib.Auto.Counter;
public class CounterFineBean {
	
	
	private String mcode = "";
	private String mname = "";
	private String accno = "";
	private String title = "";
	private String author = "";
	private String doc = "";
	private String idate = "";
	private String ddate = "";
	private String rdate = "";
	private Double temp;
	
	
	
	
	/**
	 * @return
	 */
	public String getAccno() {
		return accno;
	}

	
	/**
	 * @return
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * @return
	 */
	public String getTitle() {
		return title;
	}
	
	/**
	 * @return
	 */
	public String getMcode() {
		return mcode;
	}

	/**
	 * @return
	 */
	public String getMname() {
		return mname;
	}

	/**
	 * @return
	 */
	public String getDoc() {
		return doc;
	}
	
	/**
	 * @return
	 */
	public String getIdate() {
		return idate;
	}

	/**
	 * @return
	 */
	public String getDdate() {
		return ddate;
	}

	/**
	 * @return
	 */
	public String getRdate() {
		return rdate;
	}
	
	public double getTemp() {
		return temp;
	}
	

	
	/**
	 * @param string
	 */
	public void setAccno(String string) {
		accno = string;
	}

	/**
	 * @param string
	 */
	public void setAuthor(String string) {
		author = string;
	}
	
	/**
	 * @param string
	 */
	public void setMcode(String string) {
		mcode = string;
	}

	/**
	 * @param string
	 */
	public void setMname(String string) {
		mname = string;
	}


	/**
	 * @param string
	 */
	public void setTitle(String string) {
		title = string;
	}
	/**
	 * @param string
	 */
	public void setIdate(String string) {
		idate = string;
	}

	/**
	 * @param string
	 */
	public void setDdate(String string) {
		ddate = string;
	}

	/**
	 * @param string
	 */
	public void setRdate(String string) {
		rdate = string;
	}
	
	/**
	 * @param string
	 */
	public void setDoc(String string) {
		doc = string;
	}

	/**
	 * @param string
	 */
	public void setTemp(double d) {
		temp = d;
	}

}