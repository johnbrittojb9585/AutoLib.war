package Lib.Auto.QB_Import;


public class QBDataBean    {
	
	@Override
	public String toString() {
		return "QBDataBean [qbCode=" + qbCode + ", date=" + date
				+ ", university=" + university + ", subName=" + subName
				+ ", subjectCode=" + subjectCode + ", departmentCode="
				+ departmentCode + ", Department=" + Department + ", course="
				+ course + ", courseCode=" + courseCode + ", pubyear="
				+ pubyear + ", month=" + month + ", semester=" + semester
				+ ", content=" + content + ", addfield1=" + addfield1
				+ ", addfield2=" + addfield2 + "]";
	}
	private String qbCode;
	private String date;
	private String university;
	private String subName;
	private String subjectCode;
	private int departmentCode;
	private String Department;
	private String course;
	private int courseCode;
	private String pubyear;
	private String month;
	private String semester;
	private String content;
	private String addfield1;
	private String addfield2;
	
	public String getQbCode() {
		return qbCode;
	}
	public void setQbCode(String qbCode) {
		this.qbCode = qbCode;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getUniversity() {
		return university;
	}
	public void setUniversity(String university) {
		this.university = university;
	}
	public String getSubName() {
		return subName;
	}
	public void setSubName(String subName) {
		this.subName = subName;
	}
	public String getCourse() {
		return course;
	}
	public void setCourse(String course) {
		this.course = course;
	}
	public int getCourseCode() {
		return courseCode;
	}
	public void setCourseCode(int courseCode) {
		this.courseCode = courseCode;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getSemester() {
		return semester;
	}
	public void setSemester(String semester) {
		this.semester = semester;
	}
	public String getAddfield1() {
		return addfield1;
	}
	public void setAddfield1(String addfield1) {
		this.addfield1 = addfield1;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getAddfield2() {
		return addfield2;
	}
	public void setAddfield2(String addfield2) {
		this.addfield2 = addfield2;
	}
	public String getDepartment() {
		return Department;
	}
	public void setDepartment(String department) {
		Department = department;
	}
	public int getDepartmentCode() {
		return departmentCode;
	}
	public void setDepartmentCode(int departmentCode) {
		this.departmentCode = departmentCode;
	}
	public String getSubjectCode() {
		return subjectCode;
	}
	public void setSubjectCode(String subjectCode) {
		this.subjectCode = subjectCode;
	}
	public String getPubyear() {
		return pubyear;
	}
	public void setPubyear(String pubyear) {
		this.pubyear = pubyear;
	}
	
	
	
}

