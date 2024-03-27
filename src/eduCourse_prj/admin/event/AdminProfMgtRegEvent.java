package eduCourse_prj.admin.event;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;

import eduCourse_prj.VO.ProfVO;
import eduCourse_prj.admin.design.AdminProfMgtRegDesign;
import eduCourse_prj.professor.dao.ProfDAO;

public class AdminProfMgtRegEvent extends WindowAdapter implements ActionListener {

	private AdminProfMgtRegDesign apmrd;
	private ProfDAO pDAO = ProfDAO.getInstance();

	public AdminProfMgtRegEvent(AdminProfMgtRegDesign apmrd) {
		this.apmrd = apmrd;
	} // AdminProfMgtRegEvent

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == apmrd.getJbtnReg()) {
			ProfDAO pDAO = ProfDAO.getInstance();
			ProfVO pVO;
			int isValidNewEmail;

			String profName = apmrd.getJtfProfName().getText().trim();
			if (profName.isEmpty()) {
				JOptionPane.showMessageDialog(apmrd, "이름은 필수 입력 사항입니다.");
				return;
			} // end if

			String profPass = "";
			char[] secret_pass = apmrd.getJpfProfPass().getPassword();
			for (char cha : secret_pass) {
				Character.toString(cha);
				profPass += (profPass.equals("")) ? "" + cha + "" : cha + "";
			} // end for
			if (profPass.isEmpty()) {
				JOptionPane.showMessageDialog(apmrd, "비밀번호는 필수 입력 사항입니다.");
				return;
			} // end if

			String profEmail = apmrd.getJtfProfEmail().getText().trim();
			if (!profEmail.isEmpty() && !profEmail.contains("@")) {
				JOptionPane.showMessageDialog(apmrd, "유효한 이메일 형식이 아닙니다.");
				return;
			} // end if

			try {
				isValidNewEmail = pDAO.selectAllByEmail(profEmail);
				if (isValidNewEmail == -1) {
					JOptionPane.showMessageDialog(apmrd, "중복된 이메일입니다");
					return;
				}
			} catch (SQLException e2) {
				JOptionPane.showMessageDialog(apmrd, "SQL 문제가 발생했습니다.");
				e2.printStackTrace();
			}

			String deptName = (String) apmrd.getJcbDept().getSelectedItem();

			pVO = new ProfVO(profPass, profName, profEmail, deptName);

			try {
				pDAO.addProf(pVO);
				JOptionPane.showMessageDialog(null, pVO.getProf_name() + " 교수님 정보가 성공적으로 등록되었습니다.");

				updateTable();

				apmrd.dispose();
			} catch (SQLException e1) {
				JOptionPane.showMessageDialog(apmrd, "SQL 문제가 발생했습니다.");
				e1.printStackTrace();
			} // end catch
		} // end if

		if (e.getSource() == apmrd.getJbtnCancel()) {
			apmrd.dispose();
		} // end if
	} // actionPerformed

	/**
	 * 교수정보 등록 후 apmd테이블 정보 최신화
	 */
	public void updateTable() {
		apmrd.getApmd().getDtmProfMgt().setRowCount(0);

		int dept_code = 0; // 학과 코드

		int prof_num = 0; // 교번

		// 학과가 "전체"일 경우
		if (apmrd.getApmd().getJcbDept().getSelectedItem().equals("전체")) {

		} else {// 학과가 "전체"가 아닌 모든 경우
			dept_code = apmrd.getApmd().getLDept().get((apmrd.getApmd().getJcbDept().getSelectedIndex() - 1))
					.getDept_code();

		}

		// 교번 입력 유무 체크
		if (!(apmrd.getApmd().getJtfProfNum().getText().isEmpty())) {
			try {

				prof_num = Integer.parseInt(apmrd.getApmd().getJtfProfNum().getText());
			} catch (Exception ee) {
				ee.printStackTrace();
				JOptionPane.showMessageDialog(apmrd.getApmd(), "숫자만 입력가능 9자리");
				return;
			}
		}

		@SuppressWarnings("unused")
		List<ProfVO> lProfVO;

		try {
			List<ProfVO> listProfVO = pDAO.slctProf(dept_code, prof_num);

			for (ProfVO pVO1 : listProfVO) {

				Object[] row = { pVO1.getDept_name(), pVO1.getProf_number(), pVO1.getProf_name() };
				apmrd.getApmd().getDtmProfMgt().addRow(row);
			} // end for

		} catch (SQLException se) {
			se.printStackTrace();
		} // end catch

	}

} // class