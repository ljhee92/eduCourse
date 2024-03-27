package eduCourse_prj.student.event;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.sql.SQLException;

import java.util.List;

import javax.swing.JOptionPane;

import eduCourse_prj.VO.RegVO;
import eduCourse_prj.VO.ScoreVO;
import eduCourse_prj.VO.StdntAnswerVO;
import eduCourse_prj.VO.TestPageVO;
import eduCourse_prj.student.dao.StdntTestDAO;
import eduCourse_prj.student.design.StdntTestPageDesign;

public class StdntTestPageEvent extends WindowAdapter implements ActionListener {

	private StdntTestPageDesign stpd;

	public StdntTestPageEvent(StdntTestPageDesign stpd) {
		this.stpd = stpd;
	} // StdntTestPageEvent

	@Override
	public void actionPerformed(ActionEvent e) {
		// 제출 버튼 클릭 시
		if (e.getSource() == stpd.getJbtnSubmit()) {
			int index = stpd.getJtbTestPage().getRowCount();
			int[] numbers = new int[index];
			String[] answers = new String[index];

			for (int i = 0; i < index; i++) {
				answers[i] = stpd.getDtmTestPage().getValueAt(i, 6).toString();
				numbers[i] = Integer.parseInt(stpd.getDtmTestPage().getValueAt(i, 0).toString());

				if (!(answers[i].equals("1") || answers[i].equals("2") || answers[i].equals("3")
						|| answers[i].equals("4"))) {
					JOptionPane.showMessageDialog(stpd, "답은 1,2,3,4만 입력 가능합니다.");
					return;
				} // end if
			} // end for

			addAnswer(numbers, answers);
		} // end if
	} // actionPerformed

	/**
	 * 학생이 입력한 답안을 DB의 std_answer 테이블에 추가하는 method
	 */
	public void addAnswer(int[] numbers, String[] answers) {
		StdntTestDAO stDAO = StdntTestDAO.getInstance();

		int stdnt_number = Integer.parseInt(stpd.getStsd().getShd().getlVO().getId());

		int slctIndex = stpd.getStsd().getJtbTestSlct().getSelectedRow();
		String course_code = stpd.getStsd().getDtmTestSlct().getValueAt(slctIndex, 2).toString();

		StdntAnswerVO saVO = null;
		try {
			RegVO rVO = stDAO.slctOneStdntReg(stdnt_number, course_code);

			int register_number = rVO.getRegister_number();

			int[] question_numbers = numbers;
			String[] stdnt_answers = answers;

			for (int i = 0; i < question_numbers.length; i++) {
				saVO = new StdntAnswerVO(register_number, question_numbers[i], stdnt_answers[i], course_code);
				stDAO.insertTestAnswer(saVO);
			} // end for

			// 제출 완료 시 테이블의 message dialog를 띄우고
			JOptionPane.showMessageDialog(stpd, "제출 완료");

			// TestPage 테이블의 응시가능여부를 N으로 변경
			stpd.getStsd().getDtmTestSlct().setValueAt("N", slctIndex, 4);

			// 해당 과목의 답안을 얻어오기
			int index = stpd.getJtbTestPage().getRowCount();
			String[] test_answers = new String[index];

			List<TestPageVO> listTPVO = stDAO.slctTestPage(course_code);
			TestPageVO tpVO = null;

			// 점수 체크를 위한 변수 선언
			int score = 0;

			for (int i = 0; i < index; i++) {
				tpVO = listTPVO.get(i);
				test_answers[i] = tpVO.getAnswer();

				if (test_answers[i].equals(stdnt_answers[i])) {
					score += 10;
				} // end if
			} // end for

			// 얻어진 점수를 DB score 테이블에 넣기
			stDAO.insertScore(new ScoreVO(register_number, score));

			// 얻어진 점수를 TestPage 테이블의 점수에 넣기
			stpd.getStsd().getDtmTestSlct().setValueAt(score, slctIndex, 5);

			// 점수에 따라 성취도 넣기
			String grade = showGrade(score);
			stpd.getStsd().getDtmTestSlct().setValueAt(grade, slctIndex, 6);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(stpd, "SQL 문제가 발생했습니다.");
			e.printStackTrace();
		} // end catch
	} // addAnswer

	/**
	 * 얻어진 점수를 계산하여 TestPage 테이블의 성취도에 표시하기
	 * 
	 * @param score
	 */
	private String showGrade(int score) {
		String grade = "";

		if (score >= 95) {
			grade = "A+";
		} else if (score >= 90) {
			grade = "A";
		} else if (score >= 85) {
			grade = "B+";
		} else if (score >= 80) {
			grade = "B";
		} else if (score >= 75) {
			grade = "C+";
		} else if (score >= 70) {
			grade = "C";
		} else if (score >= 65) {
			grade = "D+";
		} else if (score >= 60) {
			grade = "D";
		} else {
			grade = "F";
		} // end else

		return grade;
	} // showGrade

} // class