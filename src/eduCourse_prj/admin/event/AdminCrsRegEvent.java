package eduCourse_prj.admin.event;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;

import eduCourse_prj.VO.CrsVO;

import eduCourse_prj.admin.dao.AdminDAO;
import eduCourse_prj.admin.design.AdminCrsRegDisgn;


public class AdminCrsRegEvent extends WindowAdapter implements ActionListener {
	private AdminCrsRegDisgn acrd;
	private AdminDAO aDAO = AdminDAO.getInstance();

	public AdminCrsRegEvent(AdminCrsRegDisgn acrd) {
		this.acrd = acrd;

	}

	@Override
	public void windowClosing(WindowEvent e) {

		acrd.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == acrd.getJbtnRegister()) {
			// 필수 입력 필드 값 가져오기
			String courCode = acrd.getJtfCrsCode().getText();
			String courName = acrd.getJtfCrsName().getText();
			String creditText = acrd.getJtfCredit().getText();

			if (courName.isEmpty()) {

				JOptionPane.showMessageDialog(acrd, "과목명은 필수입력사항입니다.", "유효성 오류", JOptionPane.ERROR_MESSAGE);
				return;
			}
			if (courCode.isEmpty()) {

				JOptionPane.showMessageDialog(acrd, "코드번호는 필수입력사항입니다.", "유효성 오류", JOptionPane.ERROR_MESSAGE);
				return;
			}

			// creditHour 유효성 검사
			int creditHour = -1; // 초기값 설정
			try {
				creditHour = Integer.parseInt(creditText);
				if (creditHour <= 0) {
					throw new NumberFormatException();
				}
			} catch (NumberFormatException ex) {
				// 숫자가 아닌 경우 또는 음수인 경우 예외 처리
				JOptionPane.showMessageDialog(acrd, "학점은 양의 정수만 입력하세요.", "유효성 오류", JOptionPane.ERROR_MESSAGE);
				return; // 메서드 종료
			}

			// courCode 유효성 검사
			if (!courCode.matches("[A-Za-z]\\d{4}")) {
				JOptionPane.showMessageDialog(acrd, "과목 코드는 영문자 1자리와 숫자 4자리로 이루어져야 합니다.", "유효성 오류",
						JOptionPane.ERROR_MESSAGE);
				return; // 메서드 종료
			}

			// 부서 코드 가져오기
			int dept_code = acrd.getlDept().get(acrd.getJcbDept().getSelectedIndex()).getDept_code();

			// CrsVO 객체 생성
			CrsVO cVO = new CrsVO(courCode, courName, creditHour, dept_code);

			try {
				// 과목 등록
				aDAO.addcourse(cVO);

				updateTable();
				acrd.dispose();
				

			} catch (SQLException se) {

				if (se.getErrorCode() == 12899) {

					JOptionPane.showMessageDialog(acrd, "과목 코드는 영,숫자 조합 최대 10자까지 가능합니다.", "오류",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				if (se.getErrorCode() == 1438) {

					JOptionPane.showMessageDialog(acrd, "학점은 2자리 정수까지 입력가능합니다.", "오류",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				se.printStackTrace();
			}
		}
		
		if (ae.getSource()==acrd.getJbtnCancel()) {
			acrd.dispose();
		}
		
	}

	
	
	public void updateTable() {
		
		acrd.getAcd().getDtmCrsMgt().setRowCount(0);

		int dept_code = 0; // 학과 코드

		String crs_name = ""; // 과목명

		// 학과가 "전체"일 경우
		if (acrd.getAcd().getJcbDept().getSelectedItem().equals("전체")) {

			// 과목명이 존재하는 경우
			if (!acrd.getAcd().getJtfCrsName().getText().isEmpty()) {

				crs_name = acrd.getAcd().getJtfCrsName().getText();

			}

		} else {// 학과가 "전체"가 아닌 모든 경우
			dept_code = acrd.getAcd().getLDept().get((acrd.getAcd().getJcbDept().getSelectedIndex() - 1)).getDept_code();
			// 과목명이 존재하는 경우
			if (!acrd.getAcd().getJtfCrsName().getText().isEmpty()) {

				crs_name = acrd.getAcd().getJtfCrsName().getText();

			}

		}

		@SuppressWarnings("unused")
		List<CrsVO> lCrsVO;

		try {
			List<CrsVO> listCrsVO = aDAO.slctCrs(dept_code,crs_name);

			for (CrsVO cVO : listCrsVO) {

				Object[] row = { cVO.getDeptName(), cVO.getCourName()};
				acrd.getAcd().getDtmCrsMgt().addRow(row);
			} // end for

		} catch (SQLException se) {
			se.printStackTrace();
		} // end catch
		
		
	}
	
	
	
	
	
	
}
