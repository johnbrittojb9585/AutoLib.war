package Lib.Auto.City;



public  class CityBean   {

    // ----------------------------------------------------- Instance Variables
    
//	static int code;
//	static String name = "";
//	static String desc = "";
//	static String state = "";
//	static String country = "";
//	static int pincode;
	static java.util.ArrayList al=null;
    
	int code;
	String name = "";
	String desc = "";
	String state = "";
	String country = "";
	int pincode;

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
	public int getPincode() {
		return pincode;
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
	public String getName() {
		return name;
	}
	
	
	/**
	 * @return
	 */
	public String getState() {
		return state;
	}
	
	/**
	 * @return
	 */
	public String getCountry() {
		return country;
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
	public void setName(String string) {
		name = string;
	}

	
	/**
	 * @param string
	 */
	public void setState(String string) {
		state = string;
	}
	
	/**
	 * @param string
	 */
	public void setCountry(String string) {
		country = string;
	}
	
	/**
	 * @param string
	 */
	public void setPincode(int j) {
		pincode = j;
	}
}
