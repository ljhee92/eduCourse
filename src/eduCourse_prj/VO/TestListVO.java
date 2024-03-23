package eduCourse_prj.VO;

public class TestListVO {
	private String course_name;
	private String test_flag;
	private String isExaming;
	private String course_code;
	
	public String getCourse_name() {
		return course_name;
	}
	public String getTest_flag() {
		return test_flag;
	}
	public String getIsExaming() {
		return isExaming;
	}
		
	public String course_code() {
		return course_code;
	}
	public TestListVO(String isExaming) {
		super();
		this.isExaming = isExaming;
	}
	
	
	public TestListVO(String course_name, String test_flag, String course_code) {
		super();
		this.course_name = course_name;
		this.test_flag = test_flag;
		this.course_code = course_code;
	}
	public TestListVO(String course_name, String test_flag) {
		super();
		this.course_name = course_name;
		this.test_flag = test_flag;
	}
	
	
	
	
	
	
}
