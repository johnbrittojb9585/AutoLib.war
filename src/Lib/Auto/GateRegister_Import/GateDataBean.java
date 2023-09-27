package Lib.Auto.GateRegister_Import;


public class GateDataBean    {
	
	private String memberCode;
	private String entryDate;
	private String outDate;
	private String inTime;	
	private String outTime;
	private int mins=0;

	public String getOutDate() {
		return outDate;
	}
	public void setOutDate(String outDate) {
		this.outDate = outDate;
	}
	public int getMins() {
		return mins;
	}
	public void setMins(int mins) {
		this.mins = mins;
	}	
	public String getEntryDate() {
		return entryDate;
	}
	public void setEntryDate(String entryDate) {
		this.entryDate = entryDate;
	}
	public String getMemberCode() {
		return memberCode;
	}
	public void setMemberCode(String memberCode) {
		this.memberCode = memberCode;
	}
	
	public String getInTime() {
		return inTime;
	}
	public void setInTime(String inTime) {
		this.inTime = inTime;
	}
	public String getOutTime() {
		return outTime;
	}
	public void setOutTime(String outTime) {
		this.outTime = outTime;
	}
	
	
	@Override
	public String toString() {
		return "GateDataBean [memberCode=" + memberCode + ", entryDate="
				+ entryDate + ", outDate=" + outDate + ", inTime=" + inTime
				+ ", outTime=" + outTime + ", mins=" + mins + "]";
	} 

	
}

