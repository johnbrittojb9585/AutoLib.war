package Lib.Auto.Department;



public  class DepartmentBean   {

    // ----------------------------------------------------- Instance Variables
    
//	static int code;
//	static String name = "";
//	static String desc = "";	
	int code;
	String name = "";
	String desc = "";
	
	static java.util.ArrayList al=null;
	
   

    

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

}
