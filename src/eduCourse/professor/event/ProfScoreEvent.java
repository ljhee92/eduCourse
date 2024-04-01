package eduCourse.professor.event;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import eduCourse.VO.ScoreVO;

import eduCourse.professor.dao.ScoreDAO;
import eduCourse.professor.design.ProfScoreDesign;

public class ProfScoreEvent extends WindowAdapter implements ActionListener {
	private ProfScoreDesign psd;
	private ScoreDAO sDAO = ScoreDAO.getInstance();

	public ProfScoreEvent(ProfScoreDesign psd, String title) {
		this.psd = psd;
	}

	@Override
	public void windowClosing(WindowEvent we) {
		psd.dispose();
	}

	public void actionPerformed(ActionEvent ae) {

		if (ae.getSource() == psd.getJbtnSlctTop()) {
			psd.getDtmScore().setRowCount(0); // dtmScore 초기화

			int prof_number = Integer.parseInt(psd.getPhd().getlVO().getId());
			int std_number = 0;
			String crs_code = "";

			// 과목이 전체가 아닐 경우
			if (!psd.getJcbCrs().getSelectedItem().equals("전체")) {
				crs_code = psd.getLCrs().get(psd.getJcbCrs().getSelectedIndex() - 1).getCourCode();
			}

			// 학번 입력 유무 체크
			if (!psd.getJtfStdNum().getText().isEmpty()) {
				try {
					std_number = Integer.parseInt(psd.getJtfStdNum().getText());
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(psd, "숫자만 입력 가능합니다.");
					return;
				}
			}


			List<ScoreVO> listScoreVO = new ArrayList<ScoreVO>();

			try {
				if (std_number != 0 && !crs_code.equals("")) { // 학번, 과목으로 찾음
					listScoreVO = sDAO.slctOneScore(std_number, crs_code, prof_number);
				} else if (std_number != 0 && crs_code.equals("")) { // 학번으로 찾음
					listScoreVO = sDAO.slctOneScore(std_number, prof_number);
				} else if (std_number == 0 && !crs_code.equals("")) { // 과목으로 찾음
					listScoreVO = sDAO.slctOneScore(crs_code, prof_number);
				} else if (std_number == 0 && crs_code.equals("")) { // 전체 검색
					listScoreVO = sDAO.slctAllScore(prof_number);
				}

				Object rowGrade;

				for (ScoreVO sVO : listScoreVO) {
					rowGrade = showGrade(sVO); // 점수에 따른 성취도 확인

					Object[] row = { sVO.getCourse_name(), sVO.getStd_num(), sVO.getStd_name(), sVO.getDept_name(),
							sVO.getScore(), rowGrade };
					psd.getDtmScore().addRow(row);
				}
				
				if(psd.getDtmScore().getRowCount() == 0) {
					JOptionPane.showMessageDialog(psd, "검색된 정보가 없습니다.");
					return;
				} // end if
			} catch (SQLException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(psd, "데이터베이스 조회 중 오류가 발생했습니다.");
			}

		}

		if (ae.getSource() == psd.getJbtnCnfrm()) {

			psd.dispose();

		}

	}

	/**
	 * VO의 점수에 따라 성취도를 결정하는 method
	 * 
	 * @param stVO
	 * @return
	 */
	private Object showGrade(ScoreVO sVO) {
		Object rowGrade;

		if (sVO.getScore() >= 95) {
			rowGrade = "A+";
		} else if (sVO.getScore() >= 90) {
			rowGrade = "A";
		} else if (sVO.getScore() >= 85) {
			rowGrade = "B+";
		} else if (sVO.getScore() >= 80) {
			rowGrade = "B";
		} else if (sVO.getScore() >= 75) {
			rowGrade = "C+";
		} else if (sVO.getScore() >= 70) {
			rowGrade = "C";
		} else if (sVO.getScore() >= 65) {
			rowGrade = "D+";
		} else if (sVO.getScore() >= 60) {
			rowGrade = "D";
		} else {
			rowGrade = "F";
		} // end else

		return rowGrade;
	} // showGrade

}