package eduCourse.admin.event;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import eduCourse.VO.LoginVO;
import eduCourse.admin.dao.AdminDAO;
import eduCourse.admin.design.AdminHomeDesign;
import eduCourse.admin.design.AdminLoginDesign;
import eduCourse.login.SelectLoginDesign;

public class AdminLoginEvent extends WindowAdapter implements ActionListener {
	private AdminLoginDesign ald;

	public AdminLoginEvent(AdminLoginDesign ald) {
		this.ald = ald;

	}

	@Override
	public void windowClosing(WindowEvent e) {
		ald.dispose();

	}

	@Override
	public void actionPerformed(ActionEvent ae) {

		if(ae.getSource()==ald.getJbtLogout()) {
			ald.dispose();
			new SelectLoginDesign();
		}
		
		
		if (ae.getSource() == ald.getJtfId()) {

			// jtfId에 입력값이 존재한다면
			if (!ald.getJtfId().getText().isEmpty()) {

				// jpfPass에 입력값이 존재한다면
				if (!new String(ald.getJpfPass().getPassword()).isEmpty()) {
					// 로그인 실행
					ald.getLoginButton().doClick();

				} else {// jpfPass에 입력값이 존재하지 않으면 jpfPass로 포커스 이동
					ald.getJpfPass().requestFocus();

				} // end else

			} else {
				JOptionPane.showMessageDialog(ald, "ID를 입력해주세요.");
			} // end else

		} // end if

		if (ae.getSource() == ald.getJpfPass()) {

			// jpfPass에 입력값이 존재한다면
			if (!new String(ald.getJpfPass().getPassword()).isEmpty()) {

				// jtfId에 입력값이 존재한다면
				if (!ald.getJtfId().getText().isEmpty()) {
					// 로그인 실행
					ald.getLoginButton().doClick();

				} else {// jtfId에 입력값이 존재하지 않으면 jtjId로 포커스 이동
					ald.getJtfId().requestFocus();

				} // end else

			} else {
				JOptionPane.showMessageDialog(ald, "PW를 입력해주세요.");
			}
		} // end if

		if (ae.getSource() == ald.getLoginButton()) {
			AdminDAO aDAO = AdminDAO.getInstance();
			String id = "";
			String pass = "";

			id = ald.getJtfId().getText();
			pass = new String(ald.getJpfPass().getPassword());

			// 1. 아이디와 비밀번호가 모두 입력되지 않았을 경우.
			if (ald.getJtfId().getText().isEmpty() && new String(ald.getJpfPass().getPassword()).isEmpty()) {
				JOptionPane.showMessageDialog(ald, "아이디와 비밀번호를 모두 입력해주세요");
				ald.getJtfId().requestFocus();
				return;
			}
			// 2. 비밀번호만만 입력되었을 경우 -> 아이디는 입력되지 않았음.
			if (ald.getJtfId().getText().isEmpty()) {
				JOptionPane.showMessageDialog(ald, "아이디를 입력해주세요.");
				ald.getJtfId().requestFocus();
				return;
			}
			// 3. 아이디만 입력되었을 경우 -> 비밀번호는 입력되지 않았음.
			if (new String(ald.getJpfPass().getPassword()).isEmpty()) {
				JOptionPane.showMessageDialog(ald, "비밀번호를 입력해주세요.");
				ald.getJpfPass().requestFocus();
				return;
			}

			LoginVO lVO = new LoginVO(id, pass);
			LoginVO LoginResult = null;
			try {

				LoginResult = aDAO.adminLogin(lVO);

			} catch (SQLException e) {
				e.printStackTrace();
			}

			if (LoginResult != null) {// 객체가 존재하면 로그인 성공
				JOptionPane.showMessageDialog(ald, LoginResult.getName() + " 관리자님 로그인 성공");
				new AdminHomeDesign(LoginResult);
				ald.dispose();
				return;
			} else {
				JOptionPane.showMessageDialog(ald, "로그인 실패\n 로그인 정보를 다시 확인해주세요.");
			}

		}

		
		
		
		
		
	}

}
