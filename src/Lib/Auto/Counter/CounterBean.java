package Lib.Auto.Counter;
public class CounterBean {

	// ----------------------------------------------------- Instance Variables

	private String mcode = "";
	private String mname = "";
	private String group = "";
	private String desig = "";
	private String dept = "";
	private String validDate = "";
	private String course = "";
	private String major = "";
	private String year = "";
	private String img = "";
	private String slock = "";
	
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
	private int grperiod = 0;
	// Book 
	private int beligible = 0;
	private int bissued = 0;
	private int bperiod = 0;
	private int brperiod = 0;
	//BookBank
	private int bbeligible = 0;
	private int bbissued = 0;
	private int bbperiod = 0;
	private int bbrperiod = 0;
	//NonBook
	private int nbeligible = 0;
	private int nbissued = 0;
	private int nbperiod = 0;
	private int nbrperiod = 0;
	//Journal
	private int jeligible = 0;
	private int jissued = 0;
	private int jperiod = 0;
	private int jrperiod = 0;
	//BackVol
	private int bveligible = 0;
	private int bvissued = 0;
	private int bvperiod = 0;
	private int bvrperiod = 0;
	//Thesis
	private int teligible = 0;
	private int tissued = 0;
	private int tperiod = 0;
	private int trperiod = 0;
	//Standard
	private int seligible = 0;
	private int sissued = 0;
	private int speriod = 0;
	private int srperiod = 0;
	//Proceeding
	private int peligible = 0;
	private int pissued = 0;
	private int pperiod = 0;
	private int prperiod = 0;
	//Report
	private int religible = 0;
	private int rissued = 0;
	private int rperiod = 0;
	private int rrperiod = 0;
	
	private int renewal = 0;
	private int reserve = 0;

	private String calldate = "";
	private String callissdate = "";
	private String callduedate = "";
	private Double temp = 0.0;
	private String issue_check = "";
	private int doc_return=0;
	
	private String res_member = "";
	
	private String res_mail = "";
	
	private static byte[] photo1=null;
	
	
	
	/**
	 * @return Returns the photo1.
	 */
	public byte[] getPhoto1() {
		return photo1;
	}
	/**
	 * @param photo1 The photo1 to set.
	 */
	public void setPhoto1(byte[] photo1) {
		CounterBean.photo1 = photo1;
	}
	/**
	 * @return Returns the accno.
	 */
	public String getAccno() {
		return accno;
	}
	/**
	 * @param accno The accno to set.
	 */
	public void setAccno(String accno) {
		this.accno = accno;
	}
	/**
	 * @return Returns the author.
	 */
	public String getAuthor() {
		return author;
	}
	/**
	 * @param author The author to set.
	 */
	public void setAuthor(String author) {
		this.author = author;
	}
	/**
	 * @return Returns the avail.
	 */
	public String getAvail() {
		return avail;
	}
	/**
	 * @param avail The avail to set.
	 */
	public void setAvail(String avail) {
		this.avail = avail;
	}
	/**
	 * @return Returns the bbeligible.
	 */
	public int getBbeligible() {
		return bbeligible;
	}
	/**
	 * @param bbeligible The bbeligible to set.
	 */
	public void setBbeligible(int bbeligible) {
		this.bbeligible = bbeligible;
	}
	/**
	 * @return Returns the bbissued.
	 */
	public int getBbissued() {
		return bbissued;
	}
	/**
	 * @param bbissued The bbissued to set.
	 */
	public void setBbissued(int bbissued) {
		this.bbissued = bbissued;
	}
	/**
	 * @return Returns the bbperiod.
	 */
	public int getBbperiod() {
		return bbperiod;
	}
	/**
	 * @param bbperiod The bbperiod to set.
	 */
	public void setBbperiod(int bbperiod) {
		this.bbperiod = bbperiod;
	}
	/**
	 * @return Returns the bbrperiod.
	 */
	public int getBbrperiod() {
		return bbrperiod;
	}
	/**
	 * @param bbrperiod The bbrperiod to set.
	 */
	public void setBbrperiod(int bbrperiod) {
		this.bbrperiod = bbrperiod;
	}
	/**
	 * @return Returns the beligible.
	 */
	public int getBeligible() {
		return beligible;
	}
	/**
	 * @param beligible The beligible to set.
	 */
	public void setBeligible(int beligible) {
		this.beligible = beligible;
	}
	/**
	 * @return Returns the bissued.
	 */
	public int getBissued() {
		return bissued;
	}
	/**
	 * @param bissued The bissued to set.
	 */
	public void setBissued(int bissued) {
		this.bissued = bissued;
	}
	/**
	 * @return Returns the bperiod.
	 */
	public int getBperiod() {
		return bperiod;
	}
	/**
	 * @param bperiod The bperiod to set.
	 */
	public void setBperiod(int bperiod) {
		this.bperiod = bperiod;
	}
	/**
	 * @return Returns the brperiod.
	 */
	public int getBrperiod() {
		return brperiod;
	}
	/**
	 * @param brperiod The brperiod to set.
	 */
	public void setBrperiod(int brperiod) {
		this.brperiod = brperiod;
	}
	/**
	 * @return Returns the bveligible.
	 */
	public int getBveligible() {
		return bveligible;
	}
	/**
	 * @param bveligible The bveligible to set.
	 */
	public void setBveligible(int bveligible) {
		this.bveligible = bveligible;
	}
	/**
	 * @return Returns the bvissued.
	 */
	public int getBvissued() {
		return bvissued;
	}
	/**
	 * @param bvissued The bvissued to set.
	 */
	public void setBvissued(int bvissued) {
		this.bvissued = bvissued;
	}
	/**
	 * @return Returns the bvperiod.
	 */
	public int getBvperiod() {
		return bvperiod;
	}
	/**
	 * @param bvperiod The bvperiod to set.
	 */
	public void setBvperiod(int bvperiod) {
		this.bvperiod = bvperiod;
	}
	/**
	 * @return Returns the bvrperiod.
	 */
	public int getBvrperiod() {
		return bvrperiod;
	}
	/**
	 * @param bvrperiod The bvrperiod to set.
	 */
	public void setBvrperiod(int bvrperiod) {
		this.bvrperiod = bvrperiod;
	}
	/**
	 * @return Returns the calldate.
	 */
	public String getCalldate() {
		return calldate;
	}
	/**
	 * @param calldate The calldate to set.
	 */
	public void setCalldate(String calldate) {
		this.calldate = calldate;
	}
	/**
	 * @return Returns the callduedate.
	 */
	public String getCallduedate() {
		return callduedate;
	}
	/**
	 * @param callduedate The callduedate to set.
	 */
	public void setCallduedate(String callduedate) {
		this.callduedate = callduedate;
	}
	/**
	 * @return Returns the callissdate.
	 */
	public String getCallissdate() {
		return callissdate;
	}
	/**
	 * @param callissdate The callissdate to set.
	 */
	public void setCallissdate(String callissdate) {
		this.callissdate = callissdate;
	}
	/**
	 * @return Returns the callNo.
	 */
	public String getCallNo() {
		return callNo;
	}
	/**
	 * @param callNo The callNo to set.
	 */
	public void setCallNo(String callNo) {
		this.callNo = callNo;
	}
	/**
	 * @return Returns the counterList.
	 */
	public java.util.ArrayList getCounterList() {
		return counterList;
	}
	/**
	 * @param counterList The counterList to set.
	 */
	public void setCounterList(java.util.ArrayList counterList) {
		this.counterList = counterList;
	}
	/**
	 * @return Returns the countperiod.
	 */
	public int getCountperiod() {
		return countperiod;
	}
	/**
	 * @param countperiod The countperiod to set.
	 */
	public void setCountperiod(int countperiod) {
		this.countperiod = countperiod;
	}
	/**
	 * @return Returns the course.
	 */
	public String getCourse() {
		return course;
	}
	/**
	 * @param course The course to set.
	 */
	public void setCourse(String course) {
		this.course = course;
	}
	/**
	 * @return Returns the cunterList_RESERVE.
	 */
	public java.util.ArrayList getCunterList_RESERVE() {
		return cunterList_RESERVE;
	}
	/**
	 * @param cunterList_RESERVE The cunterList_RESERVE to set.
	 */
	public void setCunterList_RESERVE(java.util.ArrayList cunterList_RESERVE) {
		this.cunterList_RESERVE = cunterList_RESERVE;
	}
	/**
	 * @return Returns the ddate.
	 */
	public String getDdate() {
		return ddate;
	}
	/**
	 * @param ddate The ddate to set.
	 */
	public void setDdate(String ddate) {
		this.ddate = ddate;
	}
	/**
	 * @return Returns the dept.
	 */
	public String getDept() {
		return dept;
	}
	/**
	 * @param dept The dept to set.
	 */
	public void setDept(String dept) {
		this.dept = dept;
	}
	/**
	 * @return Returns the desig.
	 */
	public String getDesig() {
		return desig;
	}
	/**
	 * @param desig The desig to set.
	 */
	public void setDesig(String desig) {
		this.desig = desig;
	}
	/**
	 * @return Returns the doc.
	 */
	public String getDoc() {
		return doc;
	}
	/**
	 * @param doc The doc to set.
	 */
	public void setDoc(String doc) {
		this.doc = doc;
	}
	/**
	 * @return Returns the doc_return.
	 */
	public int getDoc_Return() {
		return doc_return;
	}
	/**
	 * @param doc_return The doc_return to set.
	 */
	public void setDoc_Return(int doc_return) {
		this.doc_return = doc_return;
	}
	/**
	 * @return Returns the fine.
	 */
	public String getFine() {
		return fine;
	}
	/**
	 * @param fine The fine to set.
	 */
	public void setFine(String fine) {
		this.fine = fine;
	}
	/**
	 * @return Returns the geligible.
	 */
	public int getGeligible() {
		return geligible;
	}
	/**
	 * @param geligible The geligible to set.
	 */
	public void setGeligible(int geligible) {
		this.geligible = geligible;
	}
	/**
	 * @return Returns the gissued.
	 */
	public int getGissued() {
		return gissued;
	}
	/**
	 * @param gissued The gissued to set.
	 */
	public void setGissued(int gissued) {
		this.gissued = gissued;
	}
	/**
	 * @return Returns the gperiod.
	 */
	public int getGperiod() {
		return gperiod;
	}
	/**
	 * @param gperiod The gperiod to set.
	 */
	public void setGperiod(int gperiod) {
		this.gperiod = gperiod;
	}
	/**
	 * @return Returns the group.
	 */
	public String getGroup() {
		return group;
	}
	/**
	 * @param group The group to set.
	 */
	public void setGroup(String group) {
		this.group = group;
	}
	/**
	 * @return Returns the grperiod.
	 */
	public int getGrperiod() {
		return grperiod;
	}
	/**
	 * @param grperiod The grperiod to set.
	 */
	public void setGrperiod(int grperiod) {
		this.grperiod = grperiod;
	}
	/**
	 * @return Returns the idate.
	 */
	public String getIdate() {
		return idate;
	}
	/**
	 * @param idate The idate to set.
	 */
	public void setIdate(String idate) {
		this.idate = idate;
	}
	/**
	 * @return Returns the img.
	 */
	public String getImg() {
		return img;
	}
	/**
	 * @param img The img to set.
	 */
	public void setImg(String img) {
		this.img = img;
	}
	/**
	 * @return Returns the issue_check.
	 */
	public String getIssue_Check() {
		return issue_check;
	}
	/**
	 * @param issue_check The issue_check to set.
	 */
	public void setIssue_Check(String issue_check) {
		this.issue_check = issue_check;
	}
	/**
	 * @return Returns the jeligible.
	 */
	public int getJeligible() {
		return jeligible;
	}
	/**
	 * @param jeligible The jeligible to set.
	 */
	public void setJeligible(int jeligible) {
		this.jeligible = jeligible;
	}
	/**
	 * @return Returns the jissued.
	 */
	public int getJissued() {
		return jissued;
	}
	/**
	 * @param jissued The jissued to set.
	 */
	public void setJissued(int jissued) {
		this.jissued = jissued;
	}
	/**
	 * @return Returns the jperiod.
	 */
	public int getJperiod() {
		return jperiod;
	}
	/**
	 * @param jperiod The jperiod to set.
	 */
	public void setJperiod(int jperiod) {
		this.jperiod = jperiod;
	}
	/**
	 * @return Returns the jrperiod.
	 */
	public int getJrperiod() {
		return jrperiod;
	}
	/**
	 * @param jrperiod The jrperiod to set.
	 */
	public void setJrperiod(int jrperiod) {
		this.jrperiod = jrperiod;
	}
	/**
	 * @return Returns the major.
	 */
	public String getMajor() {
		return major;
	}
	/**
	 * @param major The major to set.
	 */
	public void setMajor(String major) {
		this.major = major;
	}
	/**
	 * @return Returns the mcode.
	 */
	public String getMcode() {
		return mcode;
	}
	/**
	 * @param mcode The mcode to set.
	 */
	public void setMcode(String mcode) {
		this.mcode = mcode;
	}
	/**
	 * @return Returns the mname.
	 */
	public String getMname() {
		return mname;
	}
	/**
	 * @param mname The mname to set.
	 */
	public void setMname(String mname) {
		this.mname = mname;
	}
	/**
	 * @return Returns the nbeligible.
	 */
	public int getNbeligible() {
		return nbeligible;
	}
	/**
	 * @param nbeligible The nbeligible to set.
	 */
	public void setNbeligible(int nbeligible) {
		this.nbeligible = nbeligible;
	}
	/**
	 * @return Returns the nbissued.
	 */
	public int getNbissued() {
		return nbissued;
	}
	/**
	 * @param nbissued The nbissued to set.
	 */
	public void setNbissued(int nbissued) {
		this.nbissued = nbissued;
	}
	/**
	 * @return Returns the nbperiod.
	 */
	public int getNbperiod() {
		return nbperiod;
	}
	/**
	 * @param nbperiod The nbperiod to set.
	 */
	public void setNbperiod(int nbperiod) {
		this.nbperiod = nbperiod;
	}
	/**
	 * @return Returns the nbrperiod.
	 */
	public int getNbrperiod() {
		return nbrperiod;
	}
	/**
	 * @param nbrperiod The nbrperiod to set.
	 */
	public void setNbrperiod(int nbrperiod) {
		this.nbrperiod = nbrperiod;
	}
	/**
	 * @return Returns the peligible.
	 */
	public int getPeligible() {
		return peligible;
	}
	/**
	 * @param peligible The peligible to set.
	 */
	public void setPeligible(int peligible) {
		this.peligible = peligible;
	}
	/**
	 * @return Returns the pissued.
	 */
	public int getPissued() {
		return pissued;
	}
	/**
	 * @param pissued The pissued to set.
	 */
	public void setPissued(int pissued) {
		this.pissued = pissued;
	}
	/**
	 * @return Returns the pperiod.
	 */
	public int getPperiod() {
		return pperiod;
	}
	/**
	 * @param pperiod The pperiod to set.
	 */
	public void setPperiod(int pperiod) {
		this.pperiod = pperiod;
	}
	/**
	 * @return Returns the prperiod.
	 */
	public int getPrperiod() {
		return prperiod;
	}
	/**
	 * @param prperiod The prperiod to set.
	 */
	public void setPrperiod(int prperiod) {
		this.prperiod = prperiod;
	}
	/**
	 * @return Returns the publisher.
	 */
	public String getPublisher() {
		return publisher;
	}
	/**
	 * @param publisher The publisher to set.
	 */
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	/**
	 * @return Returns the rdate.
	 */
	public String getRdate() {
		return rdate;
	}
	/**
	 * @param rdate The rdate to set.
	 */
	public void setRdate(String rdate) {
		this.rdate = rdate;
	}
	/**
	 * @return Returns the religible.
	 */
	public int getReligible() {
		return religible;
	}
	/**
	 * @param religible The religible to set.
	 */
	public void setReligible(int religible) {
		this.religible = religible;
	}
	/**
	 * @return Returns the renewal.
	 */
	public int getRenewal() {
		return renewal;
	}
	/**
	 * @param renewal The renewal to set.
	 */
	public void setRenewal(int renewal) {
		this.renewal = renewal;
	}
	/**
	 * @return Returns the reserve.
	 */
	public int getReserve() {
		return reserve;
	}
	/**
	 * @param reserve The reserve to set.
	 */
	public void setReserve(int reserve) {
		this.reserve = reserve;
	}
	/**
	 * @return Returns the reservelist.
	 */
	public java.util.ArrayList getReservelist() {
		return reservelist;
	}
	/**
	 * @param reservelist The reservelist to set.
	 */
	public void setReservelist(java.util.ArrayList reservelist) {
		this.reservelist = reservelist;
	}
	/**
	 * @return Returns the rissued.
	 */
	public int getRissued() {
		return rissued;
	}
	/**
	 * @param rissued The rissued to set.
	 */
	public void setRissued(int rissued) {
		this.rissued = rissued;
	}
	/**
	 * @return Returns the rperiod.
	 */
	public int getRperiod() {
		return rperiod;
	}
	/**
	 * @param rperiod The rperiod to set.
	 */
	public void setRperiod(int rperiod) {
		this.rperiod = rperiod;
	}
	/**
	 * @return Returns the rrperiod.
	 */
	public int getRrperiod() {
		return rrperiod;
	}
	/**
	 * @param rrperiod The rrperiod to set.
	 */
	public void setRrperiod(int rrperiod) {
		this.rrperiod = rrperiod;
	}
	/**
	 * @return Returns the seligible.
	 */
	public int getSeligible() {
		return seligible;
	}
	/**
	 * @param seligible The seligible to set.
	 */
	public void setSeligible(int seligible) {
		this.seligible = seligible;
	}
	/**
	 * @return Returns the sissued.
	 */
	public int getSissued() {
		return sissued;
	}
	/**
	 * @param sissued The sissued to set.
	 */
	public void setSissued(int sissued) {
		this.sissued = sissued;
	}
	/**
	 * @return Returns the slock.
	 */
	public String getSLock() {
		return slock;
	}
	/**
	 * @param slock The slock to set.
	 */
	public void setSLock(String slock) {
		this.slock = slock;
	}
	/**
	 * @return Returns the speriod.
	 */
	public int getSperiod() {
		return speriod;
	}
	/**
	 * @param speriod The speriod to set.
	 */
	public void setSperiod(int speriod) {
		this.speriod = speriod;
	}
	/**
	 * @return Returns the srperiod.
	 */
	public int getSrperiod() {
		return srperiod;
	}
	/**
	 * @param srperiod The srperiod to set.
	 */
	public void setSrperiod(int srperiod) {
		this.srperiod = srperiod;
	}
	/**
	 * @return Returns the teligible.
	 */
	public int getTeligible() {
		return teligible;
	}
	/**
	 * @param teligible The teligible to set.
	 */
	public void setTeligible(int teligible) {
		this.teligible = teligible;
	}
	/**
	 * @return Returns the temp.
	 */
	public Double getTemp() {
		return temp;
	}
	/**
	 * @param temp The temp to set.
	 */
	public void setTemp(Double temp) {
		this.temp = temp;
	}
	/**
	 * @return Returns the tissued.
	 */
	public int getTissued() {
		return tissued;
	}
	/**
	 * @param tissued The tissued to set.
	 */
	public void setTissued(int tissued) {
		this.tissued = tissued;
	}
	/**
	 * @return Returns the title.
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title The title to set.
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return Returns the tperiod.
	 */
	public int getTperiod() {
		return tperiod;
	}
	/**
	 * @param tperiod The tperiod to set.
	 */
	public void setTperiod(int tperiod) {
		this.tperiod = tperiod;
	}
	/**
	 * @return Returns the trperiod.
	 */
	public int getTrperiod() {
		return trperiod;
	}
	/**
	 * @param trperiod The trperiod to set.
	 */
	public void setTrperiod(int trperiod) {
		this.trperiod = trperiod;
	}
	/**
	 * @return Returns the validDate.
	 */
	public String getValidDate() {
		return validDate;
	}
	/**
	 * @param validDate The validDate to set.
	 */
	public void setValidDate(String validDate) {
		this.validDate = validDate;
	}
	/**
	 * @return Returns the year.
	 */
	public String getYear() {
		return year;
	}
	/**
	 * @param year The year to set.
	 */
	public void setYear(String year) {
		this.year = year;
	}
	public String getRes_member() {
		return res_member;
	}
	public void setRes_member(String res_member) {
		this.res_member = res_member;
	}
	public String getRes_mail() {
		return res_mail;
	}
	public void setRes_mail(String res_mail) {
		this.res_mail = res_mail;
	}


}
