package Lib.Auto.Course;



public  class CourseBean   {

    // ----------------------------------------------------- Instance Variables

    //static int code;
	//static String name = "";
	//static String major = "";
	//static int period;
	//static String type = "";
	//static String desc ="";
	static java.util.ArrayList al=null;
	
	int code;
	String name = "";
	String major = "";
	int period;
	String type = "";
	String desc ="";


    

	/**
	 * @return
	 */
	public java.util.ArrayList getAl() {
		return al;
	}

	/**
	 * @return
	 */
	public int getCode() {
		return code;
	}

	/**
	 * @return
	 */
	public String getDesc() {
		return desc;
	}

	/**
	 * @return
	 */
	public String getMajor() {
		return major;
	}

	/**
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return
	 */
	public int getPeriod() {
		return period;
	}

	/**
	 * @return
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param list
	 */
	public void setAl(java.util.ArrayList list) {
		al = list;
	}

	/**
	 * @param i
	 */
	public void setCode(int i) {
		code = i;
	}

	/**
	 * @param string
	 */
	public void setDesc(String string) {
		desc = string;
	}

	/**
	 * @param string
	 */
	public void setMajor(String string) {
		major = string;
	}

	/**
	 * @param string
	 */
	public void setName(String string) {
		name = string;
	}

	/**
	 * @param i
	 */
	public void setPeriod(int i) {
		period = i;
	}

	/**
	 * @param string
	 */
	public void setType(String string) {
		type = string;
	}

}
