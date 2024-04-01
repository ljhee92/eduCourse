package eduCourse.student.event;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import eduCourse.login.SelectLoginDesign;
import eduCourse.student.design.StdntAcadCalDesign;
import eduCourse.student.design.StdntCrsRegDesign;
import eduCourse.student.design.StdntCrsSlctDesign;
import eduCourse.student.design.StdntHomeDesign;
import eduCourse.student.design.StdntMdfyDesign;
import eduCourse.student.design.StdntTestAnswerDesign;
import eduCourse.student.design.StdntTestSlctDesign;

public class StdntHomeEvent extends WindowAdapter implements ActionListener {
	private StdntHomeDesign shd;

	public StdntHomeEvent(StdntHomeDesign shd) {
		this.shd = shd;

	}

	@Override
	public void windowClosing(WindowEvent e) {

		shd.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent ae) {

		if (ae.getSource() == shd.getJbtnEnrollCour()) {
			new StdntCrsRegDesign(shd, "수강 신청");
		} // end if

		if (ae.getSource() == shd.getJbtnEnrollHist()) {
			new StdntCrsSlctDesign(shd, "수강 신청 내역");
		} // end if

		if (ae.getSource() == shd.getJbtnExamAttendResu()) {
			new StdntTestSlctDesign(shd, "시험 응시 및 결과");
		} // end if

		if (ae.getSource() == shd.getJbtnJbtnExamCorr()) {
			new StdntTestAnswerDesign(shd, "시험 정오표");
		} // end if

		if (ae.getSource() == shd.getJbtnInfoUpda()) {
			new StdntMdfyDesign(shd, "학생 정보 수정");
		} // end if

		if (ae.getSource() == shd.getJbtnLogout()) {
			new SelectLoginDesign();
			shd.dispose();
		} // end if
		if (ae.getSource() == shd.getJbtnSched()) {
			new StdntAcadCalDesign(shd, "학사일정");
		} // end if

	}// actionPerformed

}
