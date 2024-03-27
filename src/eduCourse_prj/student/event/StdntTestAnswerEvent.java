package eduCourse_prj.student.event;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.List;

import eduCourse_prj.VO.StdntAnswerVO;
import eduCourse_prj.student.dao.StdntAnswerDAO;
import eduCourse_prj.student.design.StdntTestAnswerDesign;

public class StdntTestAnswerEvent extends WindowAdapter implements ActionListener {

	private StdntTestAnswerDesign stad;

	public StdntTestAnswerEvent(StdntTestAnswerDesign stad) {
		this.stad = stad;
	} // StdntTestAnswerEvent

	@Override
	public void windowClosing(WindowEvent e) {
		stad.dispose();

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == stad.getJbtnSlct()) {
			int selectedIndex = stad.getJcbCrs().getSelectedIndex();
			int register_number = stad.getCrsList().get(selectedIndex).getRegister_number();
			String course_code = stad.getCrsList().get(selectedIndex).getCourse_code();
			List<StdntAnswerVO> answerList = null;

			StdntAnswerDAO saDAO = StdntAnswerDAO.getInstance();
			try {
				answerList = saDAO.slctExamAnswer(course_code, register_number);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			for (StdntAnswerVO saVO : answerList) {
				Object[] row = { saVO.getQuestion_number(), saVO.getStd_answer(), saVO.getAnswer() };
				stad.getDtmTestAnswer().addRow(row);
			} // end if

		}
		if (e.getSource() == stad.getJbtnOk()) {
			stad.dispose();
		} // end if

	}// actionPerformed
} // class