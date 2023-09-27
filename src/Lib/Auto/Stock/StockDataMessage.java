package Lib.Auto.Stock;


public class StockDataMessage      {
	
	private String accno="";
	private String errorMsg="";
	private int count;
	
	
	
	public String getAccno() {
		return accno;
	}
	public void setAccno(String accno) {
		this.accno = accno;
	}
	
	
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
	
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	
	
	

	
	
	
}