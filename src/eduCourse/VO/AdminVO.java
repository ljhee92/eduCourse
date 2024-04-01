package eduCourse.VO;

public class AdminVO {
	private String admin_id;
	private String admin_password;
	private String admin_name;
	private int admin_chmod;

	public AdminVO() {
		super();
	}

	public AdminVO(String admin_id, String admin_name) {
		super();
		this.admin_id = admin_id;
		this.admin_name = admin_name;
	}

	public AdminVO(String admin_id, String admin_password, String admin_name) {
		super();
		this.admin_id = admin_id;
		this.admin_password = admin_password;
		this.admin_name = admin_name;
	}

	public AdminVO(String admin_id, String admin_password, String admin_name, int admin_chmod) {
		super();
		this.admin_id = admin_id;
		this.admin_password = admin_password;
		this.admin_name = admin_name;
		this.admin_chmod = admin_chmod;
	}

	public String getAdmin_id() {
		return admin_id;
	}

	public String getAdmin_password() {
		return admin_password;
	}

	public String getAdmin_name() {
		return admin_name;
	}

	@Override
	public String toString() {
		return "AdminVO [admin_id=" + admin_id + ", admin_password=" + admin_password + ", admin_name=" + admin_name
				+ ", admin_chmod=" + admin_chmod + "]";
	}

	public int getAdmin_chmod() {
		return admin_chmod;
	}

}// class
