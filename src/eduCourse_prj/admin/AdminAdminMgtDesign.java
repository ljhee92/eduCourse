package eduCourse_prj.admin;

import javax.swing.JDialog;

public class AdminAdminMgtDesign extends JDialog {
	AdminHomeDesign awd;
	
	public AdminAdminMgtDesign(AdminHomeDesign ahd,String title ) {
		super(ahd,title,true);
		this.awd = awd;
		
		
		setSize(1000,650);
		
		setLocationRelativeTo(null);
		setVisible(true);
		
		
	}

}
