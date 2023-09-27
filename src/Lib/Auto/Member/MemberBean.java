package Lib.Auto.Member;


public  class MemberBean   {


	private String code;
	private String name;
	private String bdate;
	private String edate;
	private String exdate;
	private String damount;
	private String decode;
	private String sex;
	private String madd1;
	private String madd2;
	private String mcity;
	private String mstate;
	private String mpincode;
	private String mphone;
	private String memail;
	private String deptcode;
	private String coursecode;
	private String groupcode;
	private String remarks;
	private String profile;
	private static String photo;
	private String cyear;
	private String security;
	
    static java.util.ArrayList al=null;
    
    private static byte[] photo1=null;

    public void setPhoto1(byte[] ad) {
		photo1 = ad;
	}

	public byte[] getPhoto1() {
		return photo1;
	}
    
	public static java.util.ArrayList getAl() {
		return al;
	}

	public static void setAl(java.util.ArrayList al) {
		MemberBean.al = al;
	}

	public String getBdate() {
		return bdate;
	}

	public void setBdate(String bdate) {
		this.bdate = bdate;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCoursecode() {
		return coursecode;
	}

	public void setCoursecode(String coursecode) {
		this.coursecode = coursecode;
	}

	public String getCyear() {
		return cyear;
	}

	public void setCyear(String cyear) {
		this.cyear = cyear;
	}

	public String getDamount() {
		return damount;
	}

	public void setDamount(String damount) {
		this.damount = damount;
	}

	public String getDecode() {
		return decode;
	}

	public void setDecode(String decode) {
		this.decode = decode;
	}

	public String getDeptcode() {
		return deptcode;
	}

	public void setDeptcode(String deptcode) {
		this.deptcode = deptcode;
	}

	public String getEdate() {
		return edate;
	}

	public void setEdate(String edate) {
		this.edate = edate;
	}

	public String getExdate() {
		return exdate;
	}

	public void setExdate(String exdate) {
		this.exdate = exdate;
	}

	public String getGroupcode() {
		return groupcode;
	}

	public void setGroupcode(String groupcode) {
		this.groupcode = groupcode;
	}

	public String getMadd1() {
		return madd1;
	}

	public void setMadd1(String madd1) {
		this.madd1 = madd1;
	}

	public String getMadd2() {
		return madd2;
	}

	public void setMadd2(String madd2) {
		this.madd2 = madd2;
	}

	public String getMcity() {
		return mcity;
	}

	public void setMcity(String mcity) {
		this.mcity = mcity;
	}

	public String getMemail() {
		return memail;
	}

	public void setMemail(String memail) {
		this.memail = memail;
	}

	public String getMphone() {
		return mphone;
	}

	public void setMphone(String mphone) {
		this.mphone = mphone;
	}

	public String getMpincode() {
		return mpincode;
	}

	public void setMpincode(String mpincode) {
		this.mpincode = mpincode;
	}

	public String getMstate() {
		return mstate;
	}

	public void setMstate(String mstate) {
		this.mstate = mstate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getSecurity() {
		return security;
	}
	public void setSecurity(String security) {
		this.security = security;
	}	
}
