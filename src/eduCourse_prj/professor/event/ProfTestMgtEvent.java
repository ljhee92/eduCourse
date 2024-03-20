package eduCourse_prj.professor.event;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JOptionPane;

import eduCourse_prj.professor.design.ProfTestMdfyDesign;
import eduCourse_prj.professor.design.ProfTestMgtDesign;
import eduCourse_prj.professor.design.ProfTestRegDesign;


public class ProfTestMgtEvent extends WindowAdapter implements ActionListener{
	ProfTestMgtDesign ptmd;
	
	public ProfTestMgtEvent(ProfTestMgtDesign ptmd) {
		this.ptmd = ptmd;
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource() == ptmd.getJbtnTestMdfy()) {
			JOptionPane.showMessageDialog(ptmd, "수정버튼 클릭");
			new ProfTestMdfyDesign(ptmd, null);
		}
		
		if(ae.getSource() == ptmd.getJbtnTestReg()) {
			JOptionPane.showMessageDialog(ptmd, "등록버튼 클릭");
			new ProfTestRegDesign(ptmd, null);
		}
	}

	@Override
	public void windowClosing(WindowEvent e) {
		super.windowClosing(e);
	}
	
	
	
}
