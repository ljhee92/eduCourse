package eduCourse.student.event;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;

import eduCourse.VO.StdntAnswerVO;
import eduCourse.student.dao.StdntAnswerDAO;
import eduCourse.student.design.StdntTestAnswerDesign;

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
			stad.getDtmTestAnswer().setRowCount(0);
			try {
				int selectedIndex = stad.getJcbCrs().getSelectedIndex();
				
				int register_number = stad.getCrsList().get(selectedIndex).getRegister_number();
				String course_code = stad.getCrsList().get(selectedIndex).getCourse_code();
				List<StdntAnswerVO> answerList = null;
				
				StdntAnswerDAO saDAO = StdntAnswerDAO.getInstance();
				answerList = saDAO.slctExamAnswer(course_code, register_number);
				for (StdntAnswerVO saVO : answerList) {
					Object[] row = { saVO.getQuestion_number(), saVO.getStd_answer(), saVO.getAnswer() };
					stad.getDtmTestAnswer().addRow(row);
				} // end if
			} catch(IndexOutOfBoundsException ie) {
				JOptionPane.showMessageDialog(stad, "선택할 수 있는 과목이 없습니다.");
				ie.printStackTrace();
			} catch (SQLException e1) {
				JOptionPane.showMessageDialog(stad, "SQL 문제가 발생했습니다.");
				e1.printStackTrace();
			} // end catch
		}
		if (e.getSource() == stad.getJbtnOk()) {
			stad.dispose();
		} // end if

	}// actionPerformed
} // class