package eduCourse_prj.admin;

import javax.swing.JDialog;

public class AdminDeptMgtDesign extends JDialog {
	AdminHomeDesign awd;
	
	public AdminDeptMgtDesign(AdminHomeDesign awd,String title ) {
		super(awd,title,true);
		this.awd = awd;
		
		
		setSize(1000,650);
		
		setLocationRelativeTo(null);
		setVisible(true);
		
		
	}

}
