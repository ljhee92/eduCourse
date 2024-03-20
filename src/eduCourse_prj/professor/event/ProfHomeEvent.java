package eduCourse_prj.professor.event;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JOptionPane;

import eduCourse_prj.login.SelectLoginDesign;
import eduCourse_prj.professor.design.ProfHomeDesign;
import eduCourse_prj.professor.design.ProfMdfyDesign;
import eduCourse_prj.professor.design.ProfTestMgtDesign;


public class ProfHomeEvent extends WindowAdapter  implements ActionListener {
	private ProfHomeDesign pwd;
	public ProfHomeEvent(ProfHomeDesign pwd) {
		this.pwd = pwd;
		
	}
	
	
	
	
	
	
	@Override
	public void windowClosing(WindowEvent e) {

		pwd.dispose();
	}






	@Override
	public void actionPerformed(ActionEvent ae) {
		
		if (ae.getSource()==pwd.getJbtnCourMgt()) {
			JOptionPane.showMessageDialog(pwd, "과목 관리 클릭");
		}
		if (ae.getSource()==pwd.getJbtnExamMgtGrad()) {
			JOptionPane.showMessageDialog(pwd, "시험 관리 및 채점 클릭");
		}
		if (ae.getSource()==pwd.getJbtnStudMgt()) {
			JOptionPane.showMessageDialog(pwd, "학생 관리 클릭");
		}
		if (ae.getSource()==pwd.getJbtnStudySucc()) {
			JOptionPane.showMessageDialog(pwd, "학습 성취도 클릭");
		}
		if (ae.getSource()==pwd.getJbtnInfoUpda()) {
			JOptionPane.showMessageDialog(pwd, "정보 수정 클릭");
			new ProfMdfyDesign(pwd, "정보수정");
		}

		if (ae.getSource()==pwd.getJbtnLogout()) {
			JOptionPane.showMessageDialog(pwd, "로그아웃 버튼클릭");
			new SelectLoginDesign();
			pwd.dispose();
		}
		
	}

}
