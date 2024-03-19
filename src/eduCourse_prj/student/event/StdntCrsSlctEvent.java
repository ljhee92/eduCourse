package eduCourse_prj.student.event;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;

import javax.swing.JOptionPane;

import eduCourse_prj.student.design.StdntCrsSlctDesign;

public class StdntCrsSlctEvent extends WindowAdapter implements ActionListener {
	
	private StdntCrsSlctDesign scsd;
	
	public StdntCrsSlctEvent(StdntCrsSlctDesign scsd) {
		this.scsd = scsd;
	} // StdntCrsSlctEvent

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == scsd.getJbtnOK()) {
			scsd.dispose();
		} // end if
	} // actionPerformed

} // class