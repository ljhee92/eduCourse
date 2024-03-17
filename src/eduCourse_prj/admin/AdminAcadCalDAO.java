package eduCourse_prj.admin;

public class AdminAcadCalDAO {
	private static AdminAcadCalDAO aacDAO;
	
	private AdminAcadCalDAO() {
		
	}//AdminAcadCalDAO
	
	public static AdminAcadCalDAO getInstance() {
		if(aacDAO == null) {
			new AdminAcadCalDAO();
		}
		return aacDAO;
	}//getInstance
	
	
}
