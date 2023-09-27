package Lib.Auto.FreqUsedMember;
import java.sql.ResultSet;



public  class FreqUsedMBean   {
	private String code = null;
    public String getCode() {
		return code;
	}


	public void setCode(String code) {
		this.code = code;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getDesc() {
		return desc;
	}


	public void setDesc(String desc) {
		this.desc = desc;
	}


	private String name = null;
    private String desc = null;

    
	
    static java.util.ArrayList al=null;
	static ResultSet   Dept_al=null;
	
	public java.util.ArrayList getAl() {
		return al;
	}

	
	public ResultSet getDept_al() {
		return Dept_al;
	}

	
	public void setAl(java.util.ArrayList list) {
		al = list;
	}

	
	public void setDept_al(ResultSet set) {
		Dept_al = set;
	}

	
   	}
