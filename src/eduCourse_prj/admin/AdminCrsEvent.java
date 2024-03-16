package eduCourse_prj.admin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JOptionPane;

public class AdminCrsEvent extends WindowAdapter implements ActionListener{
	AdminCrsDesign acd;
	public AdminCrsEvent(AdminCrsDesign acd) {
		this.acd = acd;
		
		
		
	}
	
	
	
	
	
	
	
	
	@Override
	public void windowClosing(WindowEvent e) {
		acd.dispose();
		super.windowClosing(e);
	}





	@Override
	public void actionPerformed(ActionEvent ae) {
		
		if (ae.getSource() == acd.getJbtnRegister()) {
			JOptionPane.showMessageDialog(acd, "등록버튼클릭");
			new AdminCrsRegDisgn(acd, "과목 등록");
			
			
		}
		
		
		
	}

}
