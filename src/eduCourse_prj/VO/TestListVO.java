package eduCourse_prj.VO;

public class TestListVO {
	private String course_name;
	private String lect_delete_flag;
	private String isExaming;
	
	public String getCourse_name() {
		return course_name;
	}
	public String getLect_delete_flag() {
		return lect_delete_flag;
	}
	public String getIsExaming() {
		return isExaming;
	}
	
	public TestListVO(String isExaming) {
		super();
		this.isExaming = isExaming;
	}
	
	public TestListVO(String course_name, String lect_delete_flag) {
		super();
		this.course_name = course_name;
		this.lect_delete_flag = lect_delete_flag;
	}
	
	
	
	
	
	
}
