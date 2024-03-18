package eduCourse_prj.admin.design;

import javax.swing.JButton;
import javax.swing.JDialog;

import eduCourse_prj.admin.event.AdminStudMgtEvent;

@SuppressWarnings("serial")
public class AdminStudMgtDesign extends JDialog {
	AdminHomeDesign awd;
	JButton jbtTest;

	public AdminStudMgtDesign(AdminHomeDesign awd, String title) {
		super(awd, title, true);
		this.awd = awd;
		setLayout(null);

		jbtTest = new JButton("test");
		
		jbtTest.setBounds(0,0,100,100);
		
		add(jbtTest);
		AdminStudMgtEvent asme = new AdminStudMgtEvent(this);
		jbtTest.addActionListener(asme);
		
		
		// Set the size of the dialog
		setSize(1000, 650);
		
		setVisible(true);

		


		setLocationRelativeTo(null);
	}

	public JButton getJbtTest() {
		return jbtTest;
	}

}