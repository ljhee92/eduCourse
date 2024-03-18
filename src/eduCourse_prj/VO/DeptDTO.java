package eduCourse_prj.VO;

import java.util.List;

public class DeptDTO {
	private int dept_code; // 학과 코드
	private String dept_name; // 학과명
    private List<String> prof_name; // 여러 명의 담당 교수를 저장하는 리스트 추가
	private int dept_capacity; // 정원
	public int getDept_code() {
		return dept_code;
	}
	public void setDept_code(int dept_code) {
		this.dept_code = dept_code;
	}
	public String getDept_name() {
		return dept_name;
	}
	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
	}
	
	public List<String> getProf_name() {
		return prof_name;
	}
	public void setProf_name(List<String> prof_name) {
		this.prof_name = prof_name;
	}
	public int getDept_capacity() {
		return dept_capacity;
	}
	public void setDept_capacity(int dept_capacity) {
		this.dept_capacity = dept_capacity;
	}
	
	public DeptDTO() {
		// TODO Auto-generated constructor stub
	}
	public DeptDTO(int dept_code, String dept_name, List<String> prof_name, int dept_capacity) {
		super();
		this.dept_code = dept_code;
		this.dept_name = dept_name;
		this.prof_name = prof_name;
		this.dept_capacity = dept_capacity;
	}
	
	
}
