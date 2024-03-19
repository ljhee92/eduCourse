package eduCourse_prj.student.event;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;

import javax.swing.JOptionPane;

import eduCourse_prj.student.design.StdntTestAnswerDesign;

public class StdntTestAnswerEvent extends WindowAdapter implements ActionListener {
	
	private StdntTestAnswerDesign stad;
	
	public StdntTestAnswerEvent(StdntTestAnswerDesign stad) {
		this.stad = stad;
	} // StdntTestAnswerEvent

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == stad.getJbtnSlct()) {
			JOptionPane.showMessageDialog(stad, "선택 버튼 클릭");
		} // end if

		if(e.getSource() == stad.getJbtnOk()) {
			stad.dispose();
		} // end if
	} // actionPerformed

} // class