package eduCourse_prj.admin.event;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;

import eduCourse_prj.VO.ProfVO;
import eduCourse_prj.admin.design.AdminProfMgtMdfyDesign;
import eduCourse_prj.professor.dao.ProfDAO;

public class AdminProfMgtMdfyEvent extends WindowAdapter implements ActionListener {

	private AdminProfMgtMdfyDesign apmmd;
	private ProfDAO pDAO = ProfDAO.getInstance();

	public AdminProfMgtMdfyEvent(AdminProfMgtMdfyDesign apmmd) {
		this.apmmd = apmmd;
	} // AdminProfMgtRegEvent

	@Override
	public void actionPerformed(ActionEvent e) {
		// 수정버튼 클릭
		if (e.getSource() == apmmd.getJbtnMdfy()) {
			ProfDAO pDAO = ProfDAO.getInstance();

			int profNum = Integer.parseInt(apmmd.getJtfProfNum().getText().trim());

			String profName = apmmd.getJtfProfName().getText().trim();
			if (profName.isEmpty()) {
				JOptionPane.showMessageDialog(apmmd, "이름은 필수 입력 사항입니다.");
				return;
			} // end if

			String profPass = "";
			char[] secret_pass = apmmd.getJpfProfPass().getPassword();
			for (char cha : secret_pass) {
				Character.toString(cha);
				profPass += (profPass.equals("")) ? "" + cha + "" : cha + "";
			} // end for
			if (profPass.isEmpty()) {
				JOptionPane.showMessageDialog(apmmd, "비밀번호는 필수 입력 사항입니다.");
				return;
			} // end if

			String profEmail = apmmd.getJtfProfEmail().getText().trim();
			if (!profEmail.isEmpty() && !profEmail.contains("@")) {
				JOptionPane.showMessageDialog(apmmd, "유효한 이메일 형식이 아닙니다.");
				return;
			} // end if
			
			int isValidNewEmail;

			String deptName = (String) apmmd.getJcbDept().getSelectedItem();

			try {
				isValidNewEmail = pDAO.selectAllByEmail(profEmail, profNum);
				if (isValidNewEmail == -1) {
					JOptionPane.showMessageDialog(apmmd, "중복된 이메일입니다");
					return;
				} // end if
				
				ProfVO pVO = new ProfVO(profNum, profPass, profName, profEmail, null, null, deptName);
				pDAO.updateProf(pVO);
				JOptionPane.showMessageDialog(null, pVO.getProf_name() + " 교수님 정보가 성공적으로 수정되었습니다.");

				updateTable();

				apmmd.dispose();
			} catch (SQLException e1) {
				JOptionPane.showMessageDialog(apmmd, "SQL 문제가 발생했습니다.");
				e1.printStackTrace();
			} // end catch
		} // end if

		// 취소 버튼 클릭
		if (e.getSource() == apmmd.getJbtnCancel()) {
			apmmd.dispose();
		} // end if
	} // actionPerformed

	/**
	 * 교수정보 수정시 테이블 업데이트
	 */
	public void updateTable() {
		apmmd.getApmd().getDtmProfMgt().setRowCount(0);

		int dept_code = 0; // 학과 코드

		int prof_num = 0; // 교번

		// 학과가 "전체"일 경우
		if (apmmd.getApmd().getJcbDept().getSelectedItem().equals("전체")) {

		} else {// 학과가 "전체"가 아닌 모든 경우
			dept_code = apmmd.getApmd().getLDept().get((apmmd.getApmd().getJcbDept().getSelectedIndex() - 1))
					.getDept_code();

		}

		// 교번 입력 유무 체크
		if (!(apmmd.getApmd().getJtfProfNum().getText().isEmpty())) {
			try {

				prof_num = Integer.parseInt(apmmd.getApmd().getJtfProfNum().getText());
			} catch (Exception ee) {
				ee.printStackTrace();
				JOptionPane.showMessageDialog(apmmd.getApmd(), "숫자만 입력가능 9자리");
				return;
			}
		}

		@SuppressWarnings("unused")
		List<ProfVO> lProfVO;

		try {
			List<ProfVO> listProfVO = pDAO.slctProf(dept_code, prof_num);

			for (ProfVO pVO : listProfVO) {

				Object[] row = { pVO.getDept_name(), pVO.getProf_number(), pVO.getProf_name() };
				apmmd.getApmd().getDtmProfMgt().addRow(row);
			} // end for

		} catch (SQLException se) {
			se.printStackTrace();
		} // end catch

	}

} // class