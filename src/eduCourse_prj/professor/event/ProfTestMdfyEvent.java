package eduCourse_prj.professor.event;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import eduCourse_prj.VO.TestPageVO;
import eduCourse_prj.VO.TestQustVO;

import eduCourse_prj.professor.dao.TestDAO;
import eduCourse_prj.professor.design.ProfTestMdfyDesign;

public class ProfTestMdfyEvent extends WindowAdapter implements ActionListener {
	ProfTestMdfyDesign ptmd;

	public ProfTestMdfyEvent(ProfTestMdfyDesign ptmd) {
		this.ptmd = ptmd;

	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		// 수정 버튼 클릭 시
		if (ae.getSource() == ptmd.getModifyButton()) {
			TestDAO tDAO = TestDAO.getInstance();
			TestQustVO tqVO;

			int questionNumber = Integer.parseInt(ptmd.getTestNumberComboBox().getSelectedItem().toString());
			System.out.println(questionNumber);

			String multipleChoiceOne = ptmd.getMultipleChoiceOneTextField().getText();
			if (multipleChoiceOne.isEmpty()) {
				JOptionPane.showMessageDialog(ptmd, "선택(1)은 필수 입력 사항입니다.");
				return;
			}
			String multipleChoiceTwo = ptmd.getMultipleChoiceTwoTextField().getText();
			if (multipleChoiceTwo.isEmpty()) {
				JOptionPane.showMessageDialog(ptmd, "선택(2)은 필수 입력 사항입니다.");
				return;
			}
			String multipleChoiceThree = ptmd.getMultipleChoiceThreeTextField().getText();
			if (multipleChoiceThree.isEmpty()) {
				JOptionPane.showMessageDialog(ptmd, "선택(3)은 필수 입력 사항입니다.");
				return;
			}
			String multipleChoiceFour = ptmd.getMultipleChoiceFourTextField().getText();
			if (multipleChoiceFour.isEmpty()) {
				JOptionPane.showMessageDialog(ptmd, "선택(4)은 필수 입력 사항입니다.");
				return;
			}

			String answerText = ptmd.getAnswerTextField().getText();
			if (answerText.isEmpty()) {
				JOptionPane.showMessageDialog(ptmd, "정답은 필수 입력 사항입니다.");
				return;
			}

			try {
				int answer = Integer.parseInt(answerText);
				// capacity 유효성 검사
				if (answer <= 0 || answer > 4) {
					JOptionPane.showMessageDialog(ptmd, "정답은 (1~4)만 입력 가능합니다.");
					return;
				}

				tqVO = new TestQustVO(questionNumber, "내용", answer, 100403140, "M0001");
				tDAO.insertTest(tqVO);
			} catch (SQLException se) {
				JOptionPane.showMessageDialog(ptmd, "SQL예외가 발생했습니다.");
				se.printStackTrace();
			}

//			System.out.println(tDAO.selectOneDept());

		} // end if

		// 취소 버튼 클릭 시
		if (ae.getSource() == ptmd.getCancelButton()) {
			ptmd.dispose();
		} // end if

		// 콤보박스 클릭 시
		if (ae.getSource() == ptmd.getTestNumberComboBox()) {
			slctTestQuestionContent();
		} // end if

	}

	/**
	 * 콤보박스를 클릭했을 때<br>
	 * DB test_question 테이블에 저장되어있는 question_content의 값을 가져와 JTA, JTF에 추가하는 method
	 */
	private void slctTestQuestionContent() {
		String courseName = ptmd.getJlCourseName2().getText();

		int selectedQuestionNumber = Integer.parseInt(ptmd.getTestNumberComboBox().getSelectedItem().toString());

		TestDAO tDAO = TestDAO.getInstance();
		try {
			TestPageVO tVO = tDAO.slctOneTestQuestion(courseName, selectedQuestionNumber);

			// DB에 등록된 문제가 없으면 빈칸으로 띄우자 !
			if (tVO == null) {
				ptmd.getTestQuestionContentTextArea().setText("");
				ptmd.getMultipleChoiceOneTextField().setText("");
				ptmd.getMultipleChoiceTwoTextField().setText("");
				ptmd.getMultipleChoiceThreeTextField().setText("");
				ptmd.getMultipleChoiceFourTextField().setText("");
				ptmd.getAnswerTextField().setText("");
				return;
			} // end if

			ptmd.getTestQuestionContentTextArea().setText(tVO.getQuestion_split_content());
			ptmd.getMultipleChoiceOneTextField().setText(tVO.getOption1());
			ptmd.getMultipleChoiceTwoTextField().setText(tVO.getOption2());
			ptmd.getMultipleChoiceThreeTextField().setText(tVO.getOption3());
			ptmd.getMultipleChoiceFourTextField().setText(tVO.getOption4());
			ptmd.getAnswerTextField().setText(tVO.getAnswer());
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(ptmd, "SQL 문제가 발생했습니다.");
			e.printStackTrace();
		} // end catch
	} // slctTestQuestionContent

}
