package Lib.Auto.Suggestion;

public class SuggestionBean {
     
	 private int sugNo;     
     private String doc;
     private String remarks;
     private String sugName;
     private String rcDate;
     private String memberCode;
     private String actionTaken;
     private String status;
     private String actionTakenDate;
     
	public String getActionTakenDate() {
		return actionTakenDate;
	}
	public void setActionTakenDate(String actionTakenDate) {
		this.actionTakenDate = actionTakenDate;
	}

	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getActionTaken() {
		return actionTaken;
	}
	public void setActionTaken(String actionTaken) {
		this.actionTaken = actionTaken;
	}
	public String getMemberCode() {
		return memberCode;
	}
	public void setMemberCode(String memberCode) {
		this.memberCode = memberCode;
	}
	public String getRcDate() {
		return rcDate;
	}
	public void setRcDate(String rcDate) {
		this.rcDate = rcDate;
	}
	
	public int getSugNo() {
		return sugNo;
	}
	public void setSugNo(int sugNo) {
		this.sugNo = sugNo;
	}
	     
	public String getDoc() {
		return doc;
	}
	public void setDoc(String doc) {
		this.doc = doc;
	}
	
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	public String getSugName() {
		return sugName;
	}
	public void setSugName(String sugName) {
		this.sugName = sugName;
	}
     
		

}





