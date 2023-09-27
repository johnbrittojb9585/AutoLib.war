package Lib.Auto.Photo;

public class PhotoBean    {
	
	private String memberid;
	private String fileName;	
	private byte[] photo;
	
	
	
	public String getFileName() {
		return fileName;
	}

	public byte[] getPhoto() {
		return photo;
	}
	
	public String getMemberid() {
		return memberid;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	public void setMemberid(String memberid) {
		this.memberid = memberid;
	} 
	
	
}