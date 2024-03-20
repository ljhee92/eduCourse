package eduCourse_prj.professor.event;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import eduCourse_prj.professor.design.ProfCrsMgtDesign;

public class ProfCrsMgtEvent extends WindowAdapter implements ActionListener{
	ProfCrsMgtDesign pcmd;

	public ProfCrsMgtEvent(ProfCrsMgtDesign pcmd) {
		this.pcmd = pcmd;
	}


	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource() == pcmd.getJbtnLecReg()) {
			
		}
		if(ae.getSource() == pcmd.getJbtnSlct()) {
			
		}
		if(ae.getSource() == pcmd.getJbtnMdfy()) {
			
		}
		if(ae.getSource() == pcmd.getJbtnDel()) {
			
		}
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		super.windowClosing(e);
	}


}
