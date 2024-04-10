package eduCourse.login;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import eduCourse.admin.design.AdminLoginDesign;
import eduCourse.professor.design.ProfLoginDesign;
import eduCourse.student.design.StdntLoginDesign;

public class SelectLoginEvent extends WindowAdapter implements ActionListener {
	
	private SelectLoginDesign selectLoginDesign;

	public SelectLoginEvent(SelectLoginDesign selectLoginDesign) {
		this.selectLoginDesign = selectLoginDesign;
	} // SelectLoginEvent

	@Override
	public void actionPerformed(ActionEvent ae) {
		// admin 버튼 클릭 시
		if (ae.getSource() == selectLoginDesign.getJbtnAdmin()) {
			new AdminLoginDesign(this);
		} // end if
		
		// professor 버튼 클릭 시
		if (ae.getSource() == selectLoginDesign.getJbtnProfessor()) {
			new ProfLoginDesign(this);
		} // end if
		
		// student 버튼 클릭 시
		if (ae.getSource() == selectLoginDesign.getJbtnStudent()) {
			new StdntLoginDesign(this);
		} // end if
		
		closeSelectLoginDesign();
	}// actionPerformed
	
	/**
	 * 로그인 창 닫기 method
	 */
	public void closeSelectLoginDesign() {
		selectLoginDesign.dispose();
	} // closeWindow

	@Override
	public void windowClosing(WindowEvent e) {
		closeSelectLoginDesign();
	} // windowClosing

} // class