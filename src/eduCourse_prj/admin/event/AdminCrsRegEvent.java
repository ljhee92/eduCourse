package eduCourse_prj.admin.event;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JOptionPane;

import eduCourse_prj.admin.design.AdminCrsRegDisgn;

public class AdminCrsRegEvent extends WindowAdapter implements ActionListener {
	AdminCrsRegDisgn acrd;
	
	public AdminCrsRegEvent (AdminCrsRegDisgn acrd) {
		this.acrd = acrd;
		
		
	}
	
	
	
	
	
	
	@Override
	public void windowClosing(WindowEvent e) {
		
		acrd.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		
		if (ae.getSource() == acrd.getJbtnRegister()) {
			JOptionPane.showMessageDialog(acrd, "등록버튼 클릭");
			
		}
		
		if (ae.getSource() == acrd.getJbtnCancel()) {
			JOptionPane.showMessageDialog(acrd, "취소버튼 클릭");
			
			acrd.dispose();
		}
		

	}

}
