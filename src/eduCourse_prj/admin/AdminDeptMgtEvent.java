package eduCourse_prj.admin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

public class AdminDeptMgtEvent extends JDialog implements ActionListener{
	private AdminDeptMgtDesign admd;
	
	public AdminDeptMgtEvent(AdminDeptMgtDesign admd) {
		this.admd = admd;
		
		
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		
		if (ae.getSource()==admd.getJbtnRegister()) {
			JOptionPane.showMessageDialog(admd, "등록버튼 클릭");
			new AdminDeptMgtRegDesign(admd,"학과 등록");
			
		}
		
	}
	
	
	
	
	

}
