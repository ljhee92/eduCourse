package eduCourse_prj.admin.event;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import eduCourse_prj.admin.design.AdminDeptMgtDesign;
import eduCourse_prj.admin.design.AdminDeptMgtRegDesign;


@SuppressWarnings("serial")

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
