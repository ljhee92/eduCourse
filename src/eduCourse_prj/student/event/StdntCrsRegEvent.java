package eduCourse_prj.student.event;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;

import javax.swing.JOptionPane;

import eduCourse_prj.student.design.StdntCrsRegDesign;

public class StdntCrsRegEvent extends WindowAdapter implements ActionListener {
	
	private StdntCrsRegDesign scrd;
	
	public StdntCrsRegEvent(StdntCrsRegDesign scrd) {
		this.scrd = scrd;
	} // StdntCrsRegEvent

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == scrd.getJbtnAdd()) {
			JOptionPane.showMessageDialog(scrd, "담기 버튼 클릭");
		} // end if
		
		if(e.getSource() == scrd.getJbtnCancel()) {
			JOptionPane.showMessageDialog(scrd, "취소 버튼 클릭");
		} // end if
		
		if(e.getSource() == scrd.getJbtnReg()) {
			JOptionPane.showMessageDialog(scrd, "최종신청 버튼 클릭");
		} // end if
		
	} // actionPerformed

} // class