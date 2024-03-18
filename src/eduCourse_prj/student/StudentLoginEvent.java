package eduCourse_prj.student;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import eduCourse_prj.VO.LoginVO;

public class StudentLoginEvent extends WindowAdapter implements ActionListener {
	private StudentLoginDesign sld;

	public StudentLoginEvent(StudentLoginDesign sld) {
		this.sld = sld;

	}

	@Override
	public void windowClosing(WindowEvent e) {
		sld.dispose();

	}

	@Override
	public void actionPerformed(ActionEvent ae) {

		if (ae.getSource() == sld.getJtfId()) {
		//	JOptionPane.showMessageDialog(null, "엔터클릭");

			// jtfId에 입력값이 존재한다면
			if (!sld.getJtfId().getText().isEmpty()) {
				try {
					Integer.parseInt(sld.getJtfId().getText());
				}catch (NumberFormatException mfe) {
					JOptionPane.showMessageDialog(sld, "학번은 숫자만 입력 가능합니다.");
					return;
				}


				// jpfPass에 입력값이 존재한다면
				if (!new String(sld.getJpfPass().getPassword()).isEmpty()) {
					

					// 로그인 실행
					sld.getLoginButton().doClick();

				} else {// jpfPass에 입력값이 존재하지 않으면 jpfPass로 포커스 이동
					sld.getJpfPass().requestFocus();

				} // end else

			} else {
				JOptionPane.showMessageDialog(sld, "ID를 입력해주세요.");
			}//end else

		} // end if

		if (ae.getSource() == sld.getJpfPass()) {
		//	JOptionPane.showMessageDialog(null, "엔터클릭");

			// jpfPass에 입력값이 존재한다면
			if (!new String(sld.getJpfPass().getPassword()).isEmpty()) {

				// jtfId에 입력값이 존재한다면
				if (!sld.getJtfId().getText().isEmpty()) {
					// 로그인 실행
					sld.getLoginButton().doClick();

				} else {// jtfId에 입력값이 존재하지 않으면 jtjId로 포커스 이동
					sld.getJtfId().requestFocus();

				} // end else

			}else {
				JOptionPane.showMessageDialog(sld, "PW를 입력해주세요.");
			} 

		} // end if

		if (ae.getSource() == sld.getLoginButton()) {
			//JOptionPane.showMessageDialog(null, "로그인 버튼이 클릭되었습니다.");
			StdntDAO slDAO = StdntDAO.getInstance();
			int id = 0;
			String pass = "";

			try {
				id = Integer.parseInt(sld.getJtfId().getText());
				pass = new String(sld.getJpfPass().getPassword());

			} catch (NumberFormatException afe) {
				// 1. 아이디와 비밀번호가 모두 입력되지 않았을 경우.
				if (sld.getJtfId().getText().isEmpty() && new String(sld.getJpfPass().getPassword()).isEmpty()) {
					JOptionPane.showMessageDialog(sld, "아이디와 비밀번호를 모두 입력해주세요");
					sld.getJtfId().requestFocus();
					return;
				}
				// 2. 비밀번호만만 입력되었을 경우 -> 아이디는 입력되지 않았음.
				if (sld.getJtfId().getText().isEmpty()) {
					JOptionPane.showMessageDialog(sld, "아이디를 입력해주세요.");
					sld.getJtfId().requestFocus();
					return;
				}
				// 3. 아이디만 입력되었을 경우 -> 비밀번호는 입력되지 않았음.
				if (new String(sld.getJpfPass().getPassword()).isEmpty()) {
					JOptionPane.showMessageDialog(sld, "비밀번호를 입력해주세요.");
					sld.getJpfPass().requestFocus();
					return;
				}
				// 4. 기존 NumberFormatException 예외에 의해서 아이디는 숫자만 가능함을 알림
				JOptionPane.showMessageDialog(sld, "아이디는 숫자만 입력 가능합니다.");
				sld.getJtfId().requestFocus();
				return;

			} // end catch

			LoginVO lVO = new LoginVO(id+"", pass);
			LoginVO LoginResult = null;
			try {

				LoginResult = slDAO.studentLogin(lVO);

			} catch (SQLException e) {
				e.printStackTrace();
			}

			if (LoginResult != null) {// 객체가 존재하면 로그인 성공
				JOptionPane.showMessageDialog(sld, LoginResult.getName() + " 학생님 로그인 성공");
				new StdntHomeDesign(LoginResult);
				sld.dispose();
			} else {
				JOptionPane.showMessageDialog(sld, "로그인 실패\n 로그인 정보를 다시 확인해주세요.");
			}

		}

	}

}
