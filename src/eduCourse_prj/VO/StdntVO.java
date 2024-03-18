package eduCourse_prj.VO;

public class StdntVO {
	
	private int stdnt_number;
	private String stdnt_password;
	private String stdnt_name;
	private String stdnt_email;
	private String stdnt_addr;
	private String stdnt_input_date;
	private String stdnt_delete_flag;
	private String dept_name;
	
	public StdntVO() {
	}

	public StdntVO(int stdnt_number, String stdnt_password, String stdnt_name, String stdnt_email, String stdnt_addr,
			String stdnt_input_date, String stdnt_delete_flag, String dept_name) {
		this.stdnt_number = stdnt_number;
		this.stdnt_password = stdnt_password;
		this.stdnt_name = stdnt_name;
		this.stdnt_email = stdnt_email;
		this.stdnt_addr = stdnt_addr;
		this.stdnt_input_date = stdnt_input_date;
		this.stdnt_delete_flag = stdnt_delete_flag;
		this.dept_name = dept_name;
	}

	public int getStdnt_number() {
		return stdnt_number;
	}

	public String getStdnt_password() {
		return stdnt_password;
	}

	public String getStdnt_name() {
		return stdnt_name;
	}

	public String getStdnt_email() {
		return stdnt_email;
	}

	public String getStdnt_addr() {
		return stdnt_addr;
	}

	public String getStdnt_input_date() {
		return stdnt_input_date;
	}

	public String getStdnt_delete_flag() {
		return stdnt_delete_flag;
	}

	public String getDept_name() {
		return dept_name;
	}

	@Override
	public String toString() {
		return "StdntVO [stdnt_number=" + stdnt_number + ", stdnt_password=" + stdnt_password + ", stdnt_name="
				+ stdnt_name + ", stdnt_email=" + stdnt_email + ", stdnt_addr=" + stdnt_addr + ", stdnt_input_date="
				+ stdnt_input_date + ", stdnt_delete_flag=" + stdnt_delete_flag + ", dept_name=" + dept_name + "]";
	}

} // class