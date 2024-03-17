package eduCourse_prj.professor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import eduCourse_prj.VO.LoginVO;

public class ProfessorLoginEvent extends WindowAdapter implements ActionListener {
	private ProfLoginDesign pld;

	public ProfessorLoginEvent(ProfLoginDesign pld) {
		this.pld = pld;

	}

	@Override
	public void windowClosing(WindowEvent e) {
		pld.dispose();

	}

	@Override
	public void actionPerformed(ActionEvent ae) {

		if (ae.getSource() == pld.getJtfId()) {

			//JOptionPane.showMessageDialog(null, "엔터클릭");


			// jtfId에 입력값이 존재한다면
			if (!pld.getJtfId().getText().isEmpty()) {
				try {
				Integer.parseInt(pld.getJtfId().getText());
			}catch (NumberFormatException mfe) {
				JOptionPane.showMessageDialog(pld, "교번은 숫자만 입력 가능합니다.");
				return;
			}

				// jpfPass에 입력값이 존재한다면
				if (!new String(pld.getJpfPass().getPassword()).isEmpty()) {
					// 로그인 실행
					pld.getLoginButton().doClick();

				} else {// jpfPass에 입력값이 존재하지 않으면 jpfPass로 포커스 이동
					pld.getJpfPass().requestFocus();

				} // end else

			} else {
				JOptionPane.showMessageDialog(pld, "ID를 입력해주세요.");
			}//end else

		} // end if

		if (ae.getSource() == pld.getJpfPass()) {
		//	JOptionPane.showMessageDialog(null, "엔터클릭");

			// jpfPass에 입력값이 존재한다면
			if (!new String(pld.getJpfPass().getPassword()).isEmpty()) {

				// jtfId에 입력값이 존재한다면
				if (!pld.getJtfId().getText().isEmpty()) {
					// 로그인 실행
					pld.getLoginButton().doClick();

				} else {// jtfId에 입력값이 존재하지 않으면 jtjId로 포커스 이동
					pld.getJtfId().requestFocus();

				} // end else

			} else {
				JOptionPane.showMessageDialog(pld, "PW를 입력해주세요.");
			}//end else

		} // end if

		if (ae.getSource() == pld.getLoginButton()) {
		//	JOptionPane.showMessageDialog(null, "로그인 버튼이 클릭되었습니다.");
			ProfDAO plDAO = ProfDAO.getInstance();
			int id = 0;
			String pass = "";

			try {
				id = Integer.parseInt(pld.getJtfId().getText());
				pass = new String(pld.getJpfPass().getPassword());

			} catch (NumberFormatException afe) {
				// 1. 아이디와 비밀번호가 모두 입력되지 않았을 경우.
				if (pld.getJtfId().getText().isEmpty() && new String(pld.getJpfPass().getPassword()).isEmpty()) {
					JOptionPane.showMessageDialog(pld, "아이디와 비밀번호를 모두 입력해주세요");
					pld.getJtfId().requestFocus();
					return;
				}
				// 2. 비밀번호만만 입력되었을 경우 -> 아이디는 입력되지 않았음.
				if (pld.getJtfId().getText().isEmpty()) {
					JOptionPane.showMessageDialog(pld, "아이디를 입력해주세요.");
					pld.getJtfId().requestFocus();
					return;
				}
				// 3. 아이디만 입력되었을 경우 -> 비밀번호는 입력되지 않았음.
				if (new String(pld.getJpfPass().getPassword()).isEmpty()) {
					JOptionPane.showMessageDialog(pld, "비밀번호를 입력해주세요.");
					pld.getJpfPass().requestFocus();
					return;
				}
				// 4. 기존 NumberFormatException 예외에 의해서 아이디는 숫자만 가능함을 알림
				JOptionPane.showMessageDialog(pld, "ID는 숫자만 입력가능합니다.");
				pld.getJtfId().requestFocus();
				return;

			} // end catch

			LoginVO lVO = new LoginVO(id+"" , pass);
			LoginVO LoginResult = null;
			try {

				LoginResult = plDAO.professorLogin(lVO);

			} catch (SQLException e) {
				e.printStackTrace();
			}

			if (LoginResult != null) {// 객체가 존재하면 로그인 성공
				JOptionPane.showMessageDialog(pld, LoginResult.getName()+ "교수님 로그인 성공");
				new ProfHomeDesign(LoginResult);
				pld.dispose();
			} else {
				JOptionPane.showMessageDialog(pld, "로그인 실패\n 로그인 정보를 다시 확인해주세요.");
			}

		}

	}

}
