package eduCourse_prj.professor.event;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import eduCourse_prj.login.SelectLoginDesign;
import eduCourse_prj.professor.design.ProfAcadCalDesign;
import eduCourse_prj.professor.design.ProfCrsMgtDesign;
import eduCourse_prj.professor.design.ProfHomeDesign;
import eduCourse_prj.professor.design.ProfScoreDesign;

import eduCourse_prj.professor.design.ProfMdfyDesign;
import eduCourse_prj.professor.design.ProfStdntMgtDesign;
import eduCourse_prj.professor.design.ProfTestMgtDesign;

public class ProfHomeEvent extends WindowAdapter implements ActionListener {
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

		if (ae.getSource() == pwd.getJbtnCourMgt()) {
			new ProfCrsMgtDesign(pwd, "강의 과목 관리");
		}
		if (ae.getSource() == pwd.getJbtnExamMgtGrad()) {
			new ProfTestMgtDesign(pwd, "시험 관리 및 채점");
		}

		if (ae.getSource() == pwd.getJbtnStudMgt()) {
			new ProfStdntMgtDesign(pwd, "학생 관리");
		}
		
		if (ae.getSource() == pwd.getJbtnStudySucc()) {
			new ProfScoreDesign(pwd, "학습 성취도");
		}
		if (ae.getSource() == pwd.getJbtnInfoUpda()) {
			new ProfMdfyDesign(pwd, "정보수정");
		}

		if (ae.getSource() == pwd.getJbtnLogout()) {
			new SelectLoginDesign();
			pwd.dispose();
		}
		if (ae.getSource() == pwd.getJbtnSched()) {
			new ProfAcadCalDesign(pwd, "학사일정");
		}

	}

}
