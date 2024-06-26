package eduCourse.professor.event;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import eduCourse.VO.LectureVO;
import eduCourse.professor.dao.CrsMgtRegDAO;
import eduCourse.professor.design.ProfCrsMgtMdfyDesign;

public class ProfCrsMgtMdfyEvent extends WindowAdapter implements ActionListener {

	private ProfCrsMgtMdfyDesign pcmmd;

	public ProfCrsMgtMdfyEvent(ProfCrsMgtMdfyDesign pcmmd) {
		this.pcmmd = pcmmd;
	} // ProfCrsMgtMdfyEvent

	@Override
	public void actionPerformed(ActionEvent e) {
		// 수정 버튼 클릭 시
		if (e.getSource() == pcmmd.getJbtnMdfy()) {

			if (pcmmd.getJtfCapa().getText().isEmpty()) {
				JOptionPane.showMessageDialog(pcmmd, "정원은 필수 입력사항입니다.");
				return;
			} // end if

			try {
				CrsMgtRegDAO cmrDAO = CrsMgtRegDAO.getInstance();

				int prof_number = Integer.parseInt(pcmmd.getPcmd().getPhd().getlVO().getId());
				String course_code = pcmmd.getJtfCrsCode().getText();
				String lect_room = pcmmd.getJcbLectRoom().getSelectedItem().toString();
				int capacity = Integer.parseInt(pcmmd.getJtfCapa().getText());

				boolean editable = cmrDAO.checkEditable(course_code,capacity);
				if(!editable) {
					JOptionPane.showMessageDialog(pcmmd, "수정될 정원은 현재 수강중인 학생수보다 낮습니다.");
					return;
				}
				LectureVO ltVO = new LectureVO(prof_number, course_code, capacity, 0, null, lect_room, null);

				cmrDAO.updateLect(ltVO);
				JOptionPane.showMessageDialog(pcmmd, pcmmd.getJtfCrsName().getText() + " 과목을 수정했습니다.");
			} catch (SQLException e1) {
				e1.printStackTrace();
			} // end catch
		} // end if

		// 취소 버튼 클릭 시
		if (e.getSource() == pcmmd.getJbtnCancel()) {
			pcmmd.dispose();
		} // end if
	} // actionPerformed

} // class