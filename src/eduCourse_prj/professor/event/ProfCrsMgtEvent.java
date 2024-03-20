package eduCourse_prj.professor.event;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JOptionPane;

import eduCourse_prj.professor.design.ProfCrsMgtDesign;
import eduCourse_prj.professor.design.ProfCrsMgtMdfyDesign;
import eduCourse_prj.professor.design.ProfCrsMgtRegDesign;

public class ProfCrsMgtEvent extends WindowAdapter implements ActionListener{
	ProfCrsMgtDesign pcmd;

	public ProfCrsMgtEvent(ProfCrsMgtDesign pcmd) {
		this.pcmd = pcmd;
	}


	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource() == pcmd.getJbtnLecReg()) {
			JOptionPane.showMessageDialog(pcmd, "등록버튼 클릭");
			new ProfCrsMgtRegDesign();
		}
		if(ae.getSource() == pcmd.getJbtnSlct()) {
			JOptionPane.showMessageDialog(pcmd, "조회버튼 클릭");						
		}
		if(ae.getSource() == pcmd.getJbtnMdfy()) {
			JOptionPane.showMessageDialog(pcmd, "수정버튼 클릭");
			new ProfCrsMgtMdfyDesign();			
		}
		if(ae.getSource() == pcmd.getJbtnDel()) {
			JOptionPane.showMessageDialog(pcmd, "삭제버튼 클릭");
			
		}
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		super.windowClosing(e);
	}


}
