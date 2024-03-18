package eduCourse_prj.student.event;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JOptionPane;

import eduCourse_prj.login.SelectLoginDesign;
import eduCourse_prj.student.design.StdntHomeDesign;

public class StdntHomeEvent extends WindowAdapter  implements ActionListener {
	private StdntHomeDesign swd;
	public StdntHomeEvent( StdntHomeDesign swd) {
		this.swd =swd;
		
	}
	
	
	
	
	
	
	@Override
	public void windowClosing(WindowEvent e) {

		swd.dispose();
	}






	@Override
	public void actionPerformed(ActionEvent ae) {
		
		if (ae.getSource()==swd.getJbtnEnrollCour()) {
			JOptionPane.showMessageDialog(swd, "수강 신청 클릭");
		}
		if (ae.getSource()==swd.getJbtnEnrollHist()) {
			JOptionPane.showMessageDialog(swd, "수강 신청 내역 클릭");
		}
		if (ae.getSource()==swd.getJbtnExamAttendResu()) {
			JOptionPane.showMessageDialog(swd, "시험 응시 및 결과 클릭");
		}
		if (ae.getSource()==swd.getJbtnJbtnExamCorr()) {
			JOptionPane.showMessageDialog(swd, "시험 정오표 클릭");
		}
		if (ae.getSource()==swd.getJbtnInfoUpda()) {
			JOptionPane.showMessageDialog(swd, "정보 수정 클릭");
		}

		if (ae.getSource()==swd.getJbtnLogout()) {
			JOptionPane.showMessageDialog(swd, "로그아웃 버튼클릭");
			new SelectLoginDesign();
			swd.dispose();
		}
		
	}

}
