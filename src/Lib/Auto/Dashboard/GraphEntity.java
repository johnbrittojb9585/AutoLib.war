package Lib.Auto.Dashboard;

import java.util.ArrayList;
import java.util.Map;

public class GraphEntity {
	
	private ArrayList bookList = null;
	
	private Map<String,Object> bookMap = null;
	

	public ArrayList getBookList() {
		return bookList;
	}

	public Map<String, Object> getBookMap() {
		return bookMap;
	}

	public void setBookMap(Map<String, Object> bookMap) {
		this.bookMap = bookMap;
	}

	public void setBookList(ArrayList bookList) {
		this.bookList = bookList;
	}

	@Override
	public String toString() {
		return "GraphEntity [bookList=" + bookList + ", bookMap=" + bookMap
				+ "]";
	}

}
