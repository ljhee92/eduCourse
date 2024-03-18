package eduCourse_prj.admin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import eduCourse_prj.VO.AdminVO;
import eduCourse_prj.admin.dao.AdminDAO;


public class AdminAdminMgtMdfyEvent extends WindowAdapter implements ActionListener {
	private AdminAdminMgtMdfyDesign aammd;

	public AdminAdminMgtMdfyEvent(AdminAdminMgtMdfyDesign aammd) {

		this.aammd = aammd;

	}

	@Override
	public void windowClosing(WindowEvent e) {

		aammd.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent ae) {

		// 수정버튼 클릭
		if (ae.getSource() == aammd.getJbtnMdfy()) {
			JOptionPane.showMessageDialog(aammd, "수정버튼 클릭");
			AdminDAO aDAO = AdminDAO.getInstance();

			String adminId = (aammd.getJtfAdminId().getText().trim());

			String adminName = aammd.getJtfAdminName().getText().trim();
			if (adminName.isEmpty()) {
				JOptionPane.showMessageDialog(aammd, "이름은 필수 입력 사항입니다.");
				return;
			} // end if

			String adminPass = "";
			char[] secret_pass = aammd.getJpfAdminPass().getPassword();
			for (char cha : secret_pass) {
				Character.toString(cha);
				adminPass += (adminPass.equals("")) ? "" + cha + "" : cha + "";
			} // end for
			if (adminPass.isEmpty()) {
				JOptionPane.showMessageDialog(aammd, "비밀번호는 필수 입력 사항입니다.");
				return;
			} // end if

			try {
				AdminVO aVO = new AdminVO(adminId, adminPass, adminName, 1);
				aDAO.modifyAdmin(aVO);
				JOptionPane.showMessageDialog(null,
						aVO.getAdmin_name() + " 관리자님 정보가 성공적으로 수정되었습니다.\n등록된 정보를 확인하시려면 관리자관리 창을 종료 후 재실행해주세요.");
				aammd.dispose();
			} catch (SQLException e1) {
				JOptionPane.showMessageDialog(aammd, "SQL 문제가 발생했습니다.");
				e1.printStackTrace();
			} // end catch
		}

		if (ae.getSource() == aammd.getJbtnCancel()) {
			aammd.dispose();
		}

	}

}
