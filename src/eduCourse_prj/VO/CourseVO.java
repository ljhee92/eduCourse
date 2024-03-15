package eduCourse_prj.VO;

import java.sql.Date;

public class CourseVO {
	private int dept_code; //학과 코드
	private String dept_name; //학과명
	private int dept_capacity; //정원
	private Date dept_input_date; //입력일
	private String dept_delete_flag; //삭제 flag	
	
	public CourseVO(String dept_name, int dept_capacity) {
		this.dept_name = dept_name;
		this.dept_capacity = dept_capacity;
	}
	
	public int getDept_code() {
		return dept_code;
	}
	public String getDept_name() {
		return dept_name;
	}
	public int getDept_capacity() {
		return dept_capacity;
	}
	public Date getDept_input_date() {
		return dept_input_date;
	}
	public String getDept_delete_flag() {
		return dept_delete_flag;
	}
	
	@Override
	public String toString() {
		return "CourseVO [dept_code=" + dept_code + ", dept_name=" + dept_name + ", dept_capacity=" + dept_capacity
				+ ", dept_input_date=" + dept_input_date + ", dept_delete_flag=" + dept_delete_flag + "]";
	}
	
	
}
