package eduCourse_prj.student.event;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;

import javax.swing.JOptionPane;

import eduCourse_prj.student.design.StdntTestPageDesign;

public class StdntTestPageEvent extends WindowAdapter implements ActionListener {
	
	private StdntTestPageDesign stpd;
	
	public StdntTestPageEvent(StdntTestPageDesign stpd) {
		this.stpd = stpd;
	} // StdntTestPageEvent

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == stpd.getJbtnSubmit()) {
			JOptionPane.showMessageDialog(stpd, "제출 버튼 클릭");
		} // end if
	} // actionPerformed

} // class