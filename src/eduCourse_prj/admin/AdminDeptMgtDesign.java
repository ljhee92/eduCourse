package eduCourse_prj.admin;

import javax.swing.JButton;
import javax.swing.JDialog;

public class AdminDeptMgtDesign extends JDialog {
	AdminHomeDesign awd;
	JButton jbtnRegister;
	public AdminDeptMgtDesign(AdminHomeDesign awd,String title ) {
		super(awd,title,true);
		this.awd = awd;
		
		
		
		setSize(1000,650);
		
		setLayout(null);
		
		jbtnRegister = new JButton("등록");
		
		jbtnRegister.setBounds(450,500,120,50);
		
		add(jbtnRegister);
		
		////////////////////////////////이벤트 관계 설정/////////////////////////
		AdminDeptMgtEvent adme = new AdminDeptMgtEvent(this);
		jbtnRegister.addActionListener(adme);
		
		
		
		
		
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
