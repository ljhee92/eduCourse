package eduCourse.professor.event;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import eduCourse.VO.ProfVO;

import eduCourse.login.SelectLoginDesign;
import eduCourse.professor.dao.ProfDAO;
import eduCourse.professor.design.ProfMdfyDesign;

public class ProfMdfyEvent extends WindowAdapter implements ActionListener {
	private ProfMdfyDesign pmd;
	private ProfDAO pDAO = ProfDAO.getInstance();
	
	public ProfMdfyEvent(ProfMdfyDesign pmd) {
		this.pmd = pmd;
	}

	@Override
	public void actionPerformed(ActionEvent ae) {

		// 수정버튼 클릭
		if (ae.getSource() == pmd.getJbtnMdfy()) {
			int profNum = Integer.parseInt(pmd.getJtfProfNum().getText().trim());
			int isValidNewEmail;
			String profPass = "";
			char[] secret_pass = pmd.getJpfProfPass().getPassword();
			for (char cha : secret_pass) {
				Character.toChars(cha);
				profPass += (profPass.equals("")) ? "" + cha + "" : cha + "";
			} // end for
			if (profPass.isEmpty()) {
				JOptionPane.showMessageDialog(pmd, "비밀번호는 필수 입력 사항입니다.");
				return;
			} // end if

			String profEmail = pmd.getJtfProfEmail().getText().trim();
			if (!profEmail.isEmpty() && !profEmail.contains("@")) {
				JOptionPane.showMessageDialog(pmd, "유효한 이메일 형식이 아닙니다.");
				return;
			} // end if

			try {
				isValidNewEmail = pDAO.selectAllByEmail(profEmail, profNum);
				if (isValidNewEmail == -1) {
					JOptionPane.showMessageDialog(pmd, "중복된 이메일입니다");
					return;
				}
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(pmd, "SQL 문제가 발생했습니다.");
				e.printStackTrace();
			}

			try {
				ProfVO pVO = new ProfVO(profNum, profPass, pmd.getPhd().getlVO().getName(), profEmail, null, null,
						null);
				pDAO.modifyProf(pVO);
				JOptionPane.showMessageDialog(pmd,
						pVO.getProf_name() + " 교수님 정보가 성공적으로 수정되었습니다.\n 최신정보를 갱신하기위해 로그아웃됩니다.");
				new SelectLoginDesign();
				pmd.dispose();
				pmd.getPhd().dispose();
			} catch (SQLException e1) {
				JOptionPane.showMessageDialog(pmd, "SQL 문제가 발생했습니다.");
				e1.printStackTrace();
			} // end catch
		} // end if

		// 취소 버튼 클릭
		if (ae.getSource() == pmd.getJbtnCancel()) {

			pmd.dispose();
		}

	}

	@Override
	public void windowClosing(WindowEvent e) {

		super.windowClosing(e);
	}

}
