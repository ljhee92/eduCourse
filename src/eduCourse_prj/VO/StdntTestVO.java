package eduCourse_prj.VO;

public class StdntTestVO {
	
	private String dept_name;
	private String course_name;
	private String course_code;
	private String prof_name;
	private String test_flag;
	private int score;
	
	public StdntTestVO() {
	}

	public StdntTestVO(String dept_name, String course_name, String course_code, String prof_name, String test_flag,
			int score) {
		this.dept_name = dept_name;
		this.course_name = course_name;
		this.course_code = course_code;
		this.prof_name = prof_name;
		this.test_flag = test_flag;
		this.score = score;
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

	public String getProf_name() {
		return prof_name;
	}

	public String getTest_flag() {
		return test_flag;
	}

	public int getScore() {
		return score;
	}

	@Override
	public String toString() {
		return "StdntTestVO [dept_name=" + dept_name + ", course_name=" + course_name + ", course_code=" + course_code
				+ ", prof_name=" + prof_name + ", test_flag=" + test_flag + ", score=" + score + "]";
	}
	
} // class