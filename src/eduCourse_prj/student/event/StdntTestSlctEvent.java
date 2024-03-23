package eduCourse_prj.student.event;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;

import javax.swing.JOptionPane;

import eduCourse_prj.student.design.StdntTestPageDesign;
import eduCourse_prj.student.design.StdntTestSlctDesign;

public class StdntTestSlctEvent extends WindowAdapter implements ActionListener {

	private StdntTestSlctDesign stsd;
	
	public StdntTestSlctEvent(StdntTestSlctDesign stsd) {
		this.stsd = stsd;
	} // StdntTestSlctEvent
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == stsd.getJbtnTest()) {
			int index = stsd.getJtbTestSlct().getSelectedRow();
			if(index == -1) {
				JOptionPane.showMessageDialog(stsd, "응시할 과목을 선택해주세요.");
				return;
			} // end if
			
			if(stsd.getDtmTestSlct().getValueAt(index, 4).equals("N")) {
				JOptionPane.showMessageDialog(stsd, "시험 활성화가 되지 않은 과목입니다.");
				return;
			} // end if
			new StdntTestPageDesign(stsd, "시험 응시");
		} // end if

		if(e.getSource() == stsd.getJbtnOK()) {
			stsd.dispose();
		} // end if
	} // actionPerformed

} // class