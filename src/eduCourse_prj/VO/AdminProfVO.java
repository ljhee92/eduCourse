package eduCourse_prj.VO;

import java.util.List;

public class AdminProfVO {
	private int prof_number;
	private String prof_name;
	private String prof_email;
	private String dept_name;
	private List<String> course_name;

	public int getProf_number() {
		return prof_number;
	}

	public String getProf_name() {
		return prof_name;
	}

	public String getProf_email() {
		return prof_email;
	}

	public String getDept_name() {
		return dept_name;
	}

	public List<String> getCourse_name() {
		return course_name;
	}

	public AdminProfVO() {
	}

	public AdminProfVO(int prof_number, String prof_name, String prof_email, String dept_name,
			List<String> course_name) {
		this.prof_number = prof_number;
		this.prof_name = prof_name;
		this.prof_email = prof_email;
		this.dept_name = dept_name;
		this.course_name = course_name;
	}

}
