package Lib.Auto.Login;



public  class LoginBean   {

    // ----------------------------------------------------- Instance Variables

    private String code;
	private String name = "";
	private String pass = "";
	private String rights="";
	private String flag = "";

	private java.util.ArrayList al=null;
	


    

	/**
	 * @return
	 */
	public java.util.ArrayList getAl() {
		return al;
	}

	/**
	 * @return
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @return
	 */
	public String getPass() {
		return pass;
	}

	/**
	 * @return
	 */
	public String getRights() {
		return rights;
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
	public String getFlag() {
		return flag;
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
	public void setCode(String string) {
		code = string;
	}

	/**
	 * @param string
	 */
	public void setPass(String string) {
		pass = string;
	}

	/**
	 * @param string
	 */
	public void setRights(String string) {
		rights = string;
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
	public void setFlag(String string) {
		flag = string;
	}

}

