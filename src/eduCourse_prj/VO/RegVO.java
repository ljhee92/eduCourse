package eduCourse_prj.VO;

public class RegVO {
	
	private String dept_name;
	private String course_name;
	private String lect_room;
	private int capacity;
	private int credit_hours;
	private String prof_name;
	private int register_number;
	private int stdnt_number;
	private int prof_number;
	private String course_code;
	
	public RegVO() {
	}

	public RegVO(int stdnt_number, String course_code) {
		this.stdnt_number = stdnt_number;
		this.course_code = course_code;
	}

	public RegVO(String dept_name, String course_name, String lect_room, int capacity, int credit_hours,
			String prof_name, String course_code) {
		this.dept_name = dept_name;
		this.course_name = course_name;
		this.lect_room = lect_room;
		this.capacity = capacity;
		this.credit_hours = credit_hours;
		this.prof_name = prof_name;
		this.course_code = course_code;
	}

	public String getDept_name() {
		return dept_name;
	}

	public String getCourse_name() {
		return course_name;
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

	public String getProf_name() {
		return prof_name;
	}

	public int getRegister_number() {
		return register_number;
	}

	public int getStdnt_number() {
		return stdnt_number;
	}

	public int getProf_number() {
		return prof_number;
	}

	public String getCourse_code() {
		return course_code;
	}

} // class