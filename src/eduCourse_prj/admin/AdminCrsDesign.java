package eduCourse_prj.admin;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JDialog;

import eduCourse_prj.VO.DeptVO;

public class AdminCrsDesign extends JDialog {
	AdminHomeDesign awd;
	JButton jbtnRegister ;
	
	public AdminCrsDesign(AdminHomeDesign awd,String title ) {
		super(awd,title,true);
		this.awd = awd;
		setLayout(null);
		setSize(1000,650);
		
		
		
		
		jbtnRegister= new JButton("등록");
		jbtnRegister.setBounds(500, 400, 100, 100);
		add(jbtnRegister);
		
		
		
		
		AdminCrsEvent ace = new AdminCrsEvent(this);
		addWindowListener(ace);
		jbtnRegister.addActionListener(ace);
		
		
		
		
		setLocationRelativeTo(null);
		setVisible(true);

		
		
		
		
		
		
	}
	

	
	

	public AdminHomeDesign getAwd() {
		return awd;
	}

	public JButton getJbtnRegister() {
		return jbtnRegister;
	}

	
	
	
	
	
}
