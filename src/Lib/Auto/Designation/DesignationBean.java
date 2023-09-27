package Lib.Auto.Designation;


public  class DesignationBean   {

    // ----------------------------------------------------- Instance Variables
    
     //static int code;
	 //static String name;
	 //static String desc;
	 static java.util.ArrayList al=null;
	 
//	 int code;
//	 String name;
//	 String desc;
	 
	 int code;
	 String name;
	 String desc;
	
	/**
	 * @return
	 */
	public java.util.ArrayList getAl() {
		return al;
	}

	/**
	 * @return
	 */
//	public static int getCode() {
//		return code;
//	}
	
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
	public String getName() {
		return name;
	}

	/**
	 * @param list
	 */
	public void setAl(java.util.ArrayList list) {
		al = list;
	}

	/**
	 * @param string
	 */
//	public static void setCode(int i) {
//		code = i;
//	}
	
//	public void setCode(int i) {
//		code = i;
//	}
	
	public void setCode(int code) {
		this.code = code;
	}
	
	/**
	 * @param string
	 */
//	public void setDesc(String string) {
//		desc = string;
//	}
	
	public void setDesc(String desc) {
		this.desc = desc;
	}

	/**
	 * @param string
	 */
//	public void setName(String string) {
//		name = string;
//	}
	
	public void setName(String name) {
		this.name = name;
	}

   }
