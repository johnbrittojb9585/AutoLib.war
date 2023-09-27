package Lib.Auto.Email_Reminder;



public  class ReminderBean   {

    // ----------------------------------------------------- Instance Variables

  
	private String mcode=null;
	private String mname=null;
	private String memail=null;
	private String mphone=null;
	private String dueDate=null;
		
	
	private java.util.ArrayList al=null;
	

    public String getMcode()  {
    	return mcode;
    }
    
    public String getMname()  {
    	return mname;
    }
    
    public String getMemail()  {
    	return memail;
    }
    
    public void setMcode(String mcode)  {
    	this.mcode=mcode;
    }
            
    public void setMname(String mname)  {
    	this.mname=mname;
    }
    
    public void setMemail(String memail)  {
    	this.memail=memail;
    }

	public java.util.ArrayList getAl() {
		return al;
	}

	public void setAl(java.util.ArrayList list) {
		al = list;
	}

	public String getDueDate() {
		return dueDate;
	}

	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}

	public String getMphone() {
		return mphone;
	}

	public void setMphone(String mphone) {
		this.mphone = mphone;
	}



}
