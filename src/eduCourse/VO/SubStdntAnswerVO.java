package eduCourse.VO;

public class SubStdntAnswerVO {
	private int register_number;
	private String course_code;
	private String course_name;

	public int getRegister_number() {
		return register_number;
	}

	public String getCourse_code() {
		return course_code;
	}

	public String getCourse_name() {
		return course_name;
	}

	public SubStdntAnswerVO(int register_number, String course_code, String course_name) {
		super();
		this.register_number = register_number;
		this.course_code = course_code;
		this.course_name = course_name;
	}

	public SubStdntAnswerVO() {
	}
}
