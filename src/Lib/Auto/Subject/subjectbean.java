package Lib.Auto.Subject;
public  class subjectbean   {

    // ----------------------------------------------------- Instance Variables
    
     private int code;
     private String name = "";
     private String desc = "";
     private String callno = "";
     private String email = "";


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
	public String getEmail() {
		return email;
	}

	/**
	 * @return
	 */
	public String getName() {
		return name;
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
	public void setEmail(String string) {
		email = string;
	}

	/**
	 * @param string
	 */
	public void setName(String string) {
		name = string;
	}
	
	public String getCallno() {
		return callno;
	}

	public void setCallno(String callno) {
		this.callno = callno;
	}


	
}