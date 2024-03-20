package eduCourse_prj.VO;

public class ProfLectStudVO {

	private String dept_name;
	private String course_name;
	private int std_number;
	private String std_name;
	
	
	
	
	
	
	
	
	
	
	
	
	public ProfLectStudVO(String dept_name, String course_name, int std_number, String std_name) {
		super();
		this.dept_name = dept_name;
		this.course_name = course_name;
		this.std_number = std_number;
		this.std_name = std_name;
	}










	public String getDept_name() {
		return dept_name;
	}










	public String getCourse_name() {
		return course_name;
	}










	public int getStd_number() {
		return std_number;
	}










	public String getStd_name() {
		return std_name;
	}










	@Override
	public String toString() {
		return "ProfLectStudVO [dept_name=" + dept_name + ", course_name=" + course_name + ", std_number=" + std_number
				+ ", std_name=" + std_name + "]";
	}
	
	
	
	
}
