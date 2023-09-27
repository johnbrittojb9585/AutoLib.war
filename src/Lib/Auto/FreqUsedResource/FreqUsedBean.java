package Lib.Auto.FreqUsedResource;
import java.sql.ResultSet;

public  class FreqUsedBean   {
	
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
