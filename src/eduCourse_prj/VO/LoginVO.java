package eduCourse_prj.VO;

public class LoginVO {
	
	private String id ;
	private String password;
	private String name;
	private String email;

	
	
	
	public LoginVO(String id, String password) {
		super();
		this.id = id;
		this.password = password;
		this.name = "";

	}

	
	
	public LoginVO(String id, String password, String name) {
		super();
		this.id = id;
		this.password = password;
		this.name = name;

	}
	


	


	public LoginVO(String id, String password, String name, String email) {
		super();
		this.id = id;
		this.password = password;
		this.name = name;
		this.email = email;
	}



	public String getId() {
		return id;
	}


	public String getPassword() {
		return password;
	}




	public String getName() {
		return name;
	}

	
	
	
	public String getEmail() {
		return email;
	}



	@Override
	public String toString() {
		return "LoginVO [id=" + id + ", password=" + password + ", name=" + name + ", email=" + email + "]";
	}



	
	
	
	
	

}
