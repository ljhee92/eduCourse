package eduCourse_prj.VO;

public class CrsRegVO {
	
	private String dept_name;
	private String course_name;
	private String course_code;
	private String lect_room;
	private int capacity;
	private int credit_hours;
	
	public CrsRegVO() {
	}

	public CrsRegVO(String dept_name, String course_name, String course_code, String lect_room, int capacity,
			int credit_hours) {
		this.dept_name = dept_name;
		this.course_name = course_name;
		this.course_code = course_code;
		this.lect_room = lect_room;
		this.capacity = capacity;
		this.credit_hours = credit_hours;
	}

	public String getDept_name() {
		return dept_name;
	}

	public String getCourse_name() {
		return course_name;
	}

	public String getCourse_code() {
		return course_code;
	}

	public String getLect_room() {
		return lect_room;
	}

	public int getCapacity() {
		return capacity;
	}

	public int getCredit_hours() {
		return credit_hours;
	}

	@Override
	public String toString() {
		return "CrsRegVO [dept_name=" + dept_name + ", course_name=" + course_name + ", course_code=" + course_code
				+ ", lect_room=" + lect_room + ", capacity=" + capacity + ", credit_hours=" + credit_hours + "]";
	}
	
} // class