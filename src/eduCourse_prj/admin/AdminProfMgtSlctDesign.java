package eduCourse_prj.admin;

import javax.swing.JDialog;

public class AdminProfMgtSlctDesign extends JDialog {
	private AdminProfMgtDesign apmd;
	
	public AdminProfMgtSlctDesign(AdminProfMgtDesign apmd, String title) {
		super(apmd,title,true);
		this.apmd = apmd;
		
		setLayout(null);
	} // AdminProfMgtSlctDesign

} // class