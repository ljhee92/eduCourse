package eduCourse_prj.professor.event;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import eduCourse_prj.professor.design.ProfTestMdfyDesign;
import eduCourse_prj.professor.design.ProfTestMgtDesign;


public class ProfTestMgtEvent extends WindowAdapter implements ActionListener{
	ProfTestMgtDesign ptmd;
	
	public ProfTestMgtEvent(ProfTestMgtDesign ptmd) {
		this.ptmd = ptmd;
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource() == ptmd.getJbtnTestMdfy()) {
			new ProfTestMdfyDesign(ptmd, null);
		}
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		super.windowClosing(e);
	}
	
	
}
