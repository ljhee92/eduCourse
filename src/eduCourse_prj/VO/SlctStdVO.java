package eduCourse_prj.VO;

/**
 * 관리자모드 -> 학생관리 -> 상단 조회버튼 클릭시 사용될 VO
 */
public class SlctStdVO {

	private String dept_name; // 학과명

	private String crs_name; // 과목명

	private int std_num; // 학번

	private String std_name; // 학생명

	public SlctStdVO() {
		super();
	}

	public SlctStdVO(String dept_name, String crs_name, int std_num, String std_name) {
		super();
		this.dept_name = dept_name;
		this.crs_name = crs_name;
		this.std_num = std_num;
		this.std_name = std_name;
	}

	public String getDept_name() {
		return dept_name;
	}

	public String getCrs_name() {
		return crs_name;
	}

	public int getStd_num() {
		return std_num;
	}

	public String getStd_name() {
		return std_name;
	}

	@Override
	public String toString() {
		return "SlctStdVO [dept_name=" + dept_name + ", crs_name=" + crs_name + ", std_num=" + std_num + ", std_name="
				+ std_name + "]";
	}

}
