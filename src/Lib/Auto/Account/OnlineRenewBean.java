package Lib.Auto.Account;
public class OnlineRenewBean {

	// ----------------------------------------------------- Instance Variables

	private String mcode = "";
	private String mname = "";
	private String group = "";
	private String desig = "";
	private String dept = "";
	private String validDate = "";
	private String course = "";
	private String year = "";
	private String img = "";

	private String accno = "";
	private String callNo = "";
	private String title = "";
	private String author = "";
	private String publisher = "";
	private String avail = "";
	private String doc = "";
	private String idate = "";
	private String ddate = "";
	private String rdate = "";
	private String fine = "";
	private java.util.ArrayList counterList = null;
	private java.util.ArrayList cunterList_RESERVE = null;
	private java.util.ArrayList reservelist = null;
	

	private int countperiod = 0;

	//general
	private int geligible = 0;
	private int gissued = 0;
	private int gperiod = 0;
	// Book 
	private int beligible = 0;
	private int bissued = 0;
	private int bperiod = 0;
	//BookBank
	private int bbeligible = 0;
	private int bbissued = 0;
	private int bbperiod = 0;
	//NonBook
	private int nbeligible = 0;
	private int nbissued = 0;
	private int nbperiod = 0;
	//Journal
	private int jeligible = 0;
	private int jissued = 0;
	private int jperiod = 0;
	//BackVol
	private int bveligible = 0;
	private int bvissued = 0;
	private int bvperiod = 0;
	//Thesis
	private int teligible = 0;
	private int tissued = 0;
	private int tperiod = 0;
	//Standard
	private int seligible = 0;
	private int sissued = 0;
	private int speriod = 0;
	//Proceeding
	private int peligible = 0;
	private int pissued = 0;
	private int pperiod = 0;
	//Report
	private int religible = 0;
	private int rissued = 0;
	private int rperiod = 0;

	private String calldate = "";
	private String callissdate = "";
	private String callduedate = "";
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
	public String getAvail() {
		return avail;
	}

	/**
	 * @return
	 */
	public int getBbeligible() {
		return bbeligible;
	}

	/**
	 * @return
	 */
	public int getBbissued() {
		return bbissued;
	}

	/**
	 * @return
	 */
	public int getBbperiod() {
		return bbperiod;
	}

	/**
	 * @return
	 */
	public int getBeligible() {
		return beligible;
	}

	/**
	 * @return
	 */
	public int getBissued() {
		return bissued;
	}

	/**
	 * @return
	 */
	public int getBperiod() {
		return bperiod;
	}

	/**
	 * @return
	 */
	public int getBveligible() {
		return bveligible;
	}

	/**
	 * @return
	 */
	public int getBvissued() {
		return bvissued;
	}

	/**
	 * @return
	 */
	public int getBvperiod() {
		return bvperiod;
	}

	/**
	 * @return
	 */
	public String getCallNo() {
		return callNo;
	}

	/**
	 * @return
	 */
	public java.util.ArrayList getCounterList() {
		return counterList;
	}

	/**
	 * @return
	 */
	public String getCourse() {
		return course;
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
	public String getDept() {
		return dept;
	}

	/**
	 * @return
	 */
	public String getDesig() {
		return desig;
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
	public String getFine() {
		return fine;
	}

	/**
	 * @return
	 */
	public int getGeligible() {
		return geligible;
	}

	/**
	 * @return
	 */
	public int getGissued() {
		return gissued;
	}

	/**
	 * @return
	 */
	public int getGperiod() {
		return gperiod;
	}

	/**
	 * @return
	 */
	public String getGroup() {
		return group;
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
	public int getJeligible() {
		return jeligible;
	}

	/**
	 * @return
	 */
	public int getJissued() {
		return jissued;
	}

	/**
	 * @return
	 */
	public int getJperiod() {
		return jperiod;
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
	public int getNbeligible() {
		return nbeligible;
	}

	/**
	 * @return
	 */
	public int getNbissued() {
		return nbissued;
	}

	/**
	 * @return
	 */
	public int getNbperiod() {
		return nbperiod;
	}

	/**
	 * @return
	 */
	public int getPeligible() {
		return peligible;
	}

	/**
	 * @return
	 */
	public int getPissued() {
		return pissued;
	}

	/**
	 * @return
	 */
	public int getPperiod() {
		return pperiod;
	}

	/**
	 * @return
	 */
	public String getPublisher() {
		return publisher;
	}

	/**
	 * @return
	 */
	public String getRdate() {
		return rdate;
	}

	/**
	 * @return
	 */
	public int getReligible() {
		return religible;
	}

	/**
	 * @return
	 */
	public int getRissued() {
		return rissued;
	}

	/**
	 * @return
	 */
	public int getRperiod() {
		return rperiod;
	}

	/**
	 * @return
	 */
	public int getSeligible() {
		return seligible;
	}

	/**
	 * @return
	 */
	public int getSissued() {
		return sissued;
	}

	/**
	 * @return
	 */
	public int getSperiod() {
		return speriod;
	}

	/**
	 * @return
	 */
	public int getTeligible() {
		return teligible;
	}

	/**
	 * @return
	 */
	public int getTissued() {
		return tissued;
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
	public int getTperiod() {
		return tperiod;
	}

	/**
	 * @return
	 */
	public String getYear() {
		return year;
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
	public void setAvail(String string) {
		avail = string;
	}

	/**
	 * @param i
	 */
	public void setBbeligible(int i) {
		bbeligible = i;
	}

	/**
	 * @param i
	 */
	public void setBbissued(int i) {
		bbissued = i;
	}

	/**
	 * @param i
	 */
	public void setBbperiod(int i) {
		bbperiod = i;
	}

	/**
	 * @param i
	 */
	public void setBeligible(int i) {
		beligible = i;
	}

	/**
	 * @param i
	 */
	public void setBissued(int i) {
		bissued = i;
	}

	/**
	 * @param i
	 */
	public void setBperiod(int i) {
		bperiod = i;
	}

	/**
	 * @param i
	 */
	public void setBveligible(int i) {
		bveligible = i;
	}

	/**
	 * @param i
	 */
	public void setBvissued(int i) {
		bvissued = i;
	}

	/**
	 * @param i
	 */
	public void setBvperiod(int i) {
		bvperiod = i;
	}

	/**
	 * @param string
	 */
	public void setCallNo(String string) {
		callNo = string;
	}

	/**
	 * @param list
	 */
	public void setCounterList(java.util.ArrayList list) {
		counterList = list;
	}

	/**
	 * @param string
	 */
	public void setCourse(String string) {
		course = string;
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
	public void setDept(String string) {
		dept = string;
	}

	/**
	 * @param string
	 */
	public void setDesig(String string) {
		desig = string;
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
	public void setFine(String string) {
		fine = string;
	}

	/**
	 * @param i
	 */
	public void setGeligible(int i) {
		geligible = i;
	}

	/**
	 * @param i
	 */
	public void setGissued(int i) {
		gissued = i;
	}

	/**
	 * @param i
	 */
	public void setGperiod(int i) {
		gperiod = i;
	}

	/**
	 * @param string
	 */
	public void setGroup(String string) {
		group = string;
	}

	/**
	 * @param string
	 */
	public void setIdate(String string) {
		idate = string;
	}

	/**
	 * @param i
	 */
	public void setJeligible(int i) {
		jeligible = i;
	}

	/**
	 * @param i
	 */
	public void setJissued(int i) {
		jissued = i;
	}

	/**
	 * @param i
	 */
	public void setJperiod(int i) {
		jperiod = i;
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
	 * @param i
	 */
	public void setNbeligible(int i) {
		nbeligible = i;
	}

	/**
	 * @param i
	 */
	public void setNbissued(int i) {
		nbissued = i;
	}

	/**
	 * @param i
	 */
	public void setNbperiod(int i) {
		nbperiod = i;
	}

	/**
	 * @param i
	 */
	public void setPeligible(int i) {
		peligible = i;
	}

	/**
	 * @param i
	 */
	public void setPissued(int i) {
		pissued = i;
	}

	/**
	 * @param i
	 */
	public void setPperiod(int i) {
		pperiod = i;
	}

	/**
	 * @param string
	 */
	public void setPublisher(String string) {
		publisher = string;
	}

	/**
	 * @param string
	 */
	public void setRdate(String string) {
		rdate = string;
	}

	/**
	 * @param i
	 */
	public void setReligible(int i) {
		religible = i;
	}

	/**
	 * @param i
	 */
	public void setRissued(int i) {
		rissued = i;
	}

	/**
	 * @param i
	 */
	public void setRperiod(int i) {
		rperiod = i;
	}

	/**
	 * @param i
	 */
	public void setSeligible(int i) {
		seligible = i;
	}

	/**
	 * @param i
	 */
	public void setSissued(int i) {
		sissued = i;
	}

	/**
	 * @param i
	 */
	public void setSperiod(int i) {
		speriod = i;
	}

	/**
	 * @param i
	 */
	public void setTeligible(int i) {
		teligible = i;
	}

	/**
	 * @param i
	 */
	public void setTissued(int i) {
		tissued = i;
	}

	/**
	 * @param string
	 */
	public void setTitle(String string) {
		title = string;
	}

	/**
	 * @param i
	 */
	public void setTperiod(int i) {
		tperiod = i;
	}

	/**
	 * @param string
	 */
	public void setYear(String string) {
		year = string;
	}

	/**
	 * @return
	 */
	public String getImg() {
		return img;
	}

	/**
	 * @param string
	 */
	public void setImg(String string) {
		img = string;
	}

	/**
	 * @return
	 */
	public String getValidDate() {
		return validDate;
	}

	/**
	 * @param string
	 */
	public void setValidDate(String string) {
		validDate = string;
	}

	/**
	 * @return
	 */
	public String getCalldate() {
		return calldate;
	}

	/**
	 * @param string
	 */
	public void setCalldate(String string) {
		calldate = string;
	}

	/**
	 * @return
	 */
	public String getCallissdate() {
		return callissdate;
	}

	/**
	 * @param string
	 */
	public void setCallissdate(String string) {
		callissdate = string;
	}

	/**
	 * @return
	 */
	public Double getTemp() {
		return temp;
	}

	/**
	 * @param double1
	 */
	public void setTemp(Double double1) {
		temp = double1;
	}

	/**
	 * @return
	 */
	public String getCallduedate() {
		return callduedate;
	}

	/**
	 * @param string
	 */
	public void setCallduedate(String string) {
		callduedate = string;
	}

	/**
	 * @return
	 */
	public java.util.ArrayList getCunterList_RESERVE() {
		return cunterList_RESERVE;
	}

	/**
	 * @param list
	 */
	public void setCunterList_RESERVE(java.util.ArrayList list) {
		cunterList_RESERVE = list;
	}

	/**
	 * @return
	 */
	public int getCountperiod() {
		return countperiod;
	}

	/**
	 * @param i
	 */
	public void setCountperiod(int i) {
		countperiod = i;
	}
	
	public void setAl(java.util.ArrayList list) {
		reservelist = list;
	}
	
	public java.util.ArrayList getAl() {
		 return reservelist;
	}

	

}
