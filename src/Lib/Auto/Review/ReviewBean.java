package Lib.Auto.Review;

public class ReviewBean {

	 private int reviewNo;
	 private String accessNo;
     private String memberCode;
     private String rcDate;
     private String title;
     private String desc;
     private int rating;
	 
     public int getReviewNo() {
		return reviewNo;
	}
	public void setReviewNo(int reviewNo) {
		this.reviewNo = reviewNo;
	}
	public String getAccessNo() {
		return accessNo;
	}
	public void setAccessNo(String accessNo) {
		this.accessNo = accessNo;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
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
	
	
}





