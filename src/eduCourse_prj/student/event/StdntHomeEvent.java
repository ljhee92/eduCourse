package eduCourse_prj.student.event;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JOptionPane;

import eduCourse_prj.login.SelectLoginDesign;
import eduCourse_prj.student.design.StdntHomeDesign;
import eduCourse_prj.student.design.StdntMdfyDesign;

public class StdntHomeEvent extends WindowAdapter  implements ActionListener {
	private StdntHomeDesign shd;
	public StdntHomeEvent( StdntHomeDesign shd) {
		this.shd =shd;
		
	}
	
	
	
	
	
	
	@Override
	public void windowClosing(WindowEvent e) {

		shd.dispose();
	}






	@Override
	public void actionPerformed(ActionEvent ae) {
		
		if (ae.getSource()==shd.getJbtnEnrollCour()) {
			JOptionPane.showMessageDialog(shd, "수강 신청 클릭");
		}
		if (ae.getSource()==shd.getJbtnEnrollHist()) {
			JOptionPane.showMessageDialog(shd, "수강 신청 내역 클릭");
		}
		if (ae.getSource()==shd.getJbtnExamAttendResu()) {
			JOptionPane.showMessageDialog(shd, "시험 응시 및 결과 클릭");
		}
		if (ae.getSource()==shd.getJbtnJbtnExamCorr()) {
			JOptionPane.showMessageDialog(shd, "시험 정오표 클릭");
		}
		if (ae.getSource()==shd.getJbtnInfoUpda()) {
			JOptionPane.showMessageDialog(shd, "정보 수정 클릭");
			new StdntMdfyDesign(shd, "학생 정보 수정");
		}

		if (ae.getSource()==shd.getJbtnLogout()) {
			JOptionPane.showMessageDialog(shd, "로그아웃 버튼클릭");
			new SelectLoginDesign();
			shd.dispose();
		}
		
	}

}
