package eduCourse_prj.professor.event;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;

import eduCourse_prj.professor.design.ProfScoreDesign;

public class ProfScoreEvent extends WindowAdapter implements ActionListener {
	ProfScoreDesign psd;
	
	public ProfScoreEvent(ProfScoreDesign psd, String title) {
		this.psd = psd;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

}
