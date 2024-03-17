package eduCourse_prj.VO;

public class ProfVO {
	
	private int prof_number;
	private String prof_password;
	private String prof_name;
	private String prof_email;
	private String prof_delete_flag;
	private String prof_input_date;
	private String dept_name;
	
	public ProfVO() {
	}

	public ProfVO(int prof_number, String prof_name) {
		this.prof_number = prof_number;
		this.prof_name = prof_name;
	}
	
	public ProfVO(int prof_number, String prof_name, String prof_email, String dept_name) {
		this.prof_number = prof_number;
		this.prof_name = prof_name;
		this.prof_email = prof_email;
		this.dept_name = dept_name;
	}
	
	public ProfVO(String prof_password, String prof_name, String prof_email, String dept_name) {
		this.prof_password = prof_password;
		this.prof_name = prof_name;
		this.prof_email = prof_email;
		this.dept_name = dept_name;
	}
	
	public ProfVO(int prof_number, String prof_password, String prof_name, String prof_email, String prof_delete_flag, String prof_input_date, String dept_name) {
		this.prof_number = prof_number;
		this.prof_password = prof_password;
		this.prof_name = prof_name;
		this.prof_email = prof_email;
		this.prof_delete_flag = prof_delete_flag;
		this.prof_input_date = prof_input_date;
		this.dept_name = dept_name;
	}

	public int getProf_number() {
		return prof_number;
	}

	public String getProf_password() {
		return prof_password;
	}

	public String getProf_name() {
		return prof_name;
	}

	public String getProf_email() {
		return prof_email;
	}

	public String getProf_delete_flag() {
		return prof_delete_flag;
	}

	public String getProf_input_date() {
		return prof_input_date;
	}

	public String getDept_name() {
		return dept_name;
	}

	@Override
	public String toString() {
		return "ProfVO [prof_number=" + prof_number + ", prof_name=" + prof_name + ", prof_email=" + prof_email
				+ ", prof_delete_flag=" + prof_delete_flag + ", prof_input_date=" + prof_input_date + ", dept_name=" + dept_name
				+ "]";
	}

} // class