package eduCourse_prj.admin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import eduCourse_prj.VO.AdminVO;
import eduCourse_prj.admin.dao.AdminDAO;
import eduCourse_prj.login.SelectLoginDesign;


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
			
			boolean logoutFlag = false;
			String loginId = aammd.getAamd().getAwd().getlVO().getId();
			

			JOptionPane.showMessageDialog(aammd, "수정버튼 클릭");
			AdminDAO aDAO = AdminDAO.getInstance();

			String adminId = (aammd.getJtfAdminId().getText().trim());

			String adminName = aammd.getJtfAdminName().getText().trim();
			
			if(loginId.equals(adminId)) {
				logoutFlag = true;
			}
			
			
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
				
				//관리자 정보 수정시 테이블 최신화
				aammd.getAamd().getDtmAdminMgt().setRowCount(0);
				aammd.getAamd().slctAdminMgt();
	

				if(logoutFlag==true) {
					JOptionPane.showMessageDialog(null,
							aVO.getAdmin_name() + " 관리자님 정보가 성공적으로 수정되었습니다.\n 최신정보를 갱신하기위해 로그아웃됩니다.");
					aammd.dispose();
					aammd.getAamd().dispose();
					aammd.getAamd().getAwd().dispose();
					new SelectLoginDesign();
					
				}else {
					JOptionPane.showMessageDialog(null,
							aVO.getAdmin_name() + " 관리자님 정보가 성공적으로 수정되었습니다.");
					aammd.dispose();
				}

				
				


			} catch (SQLException e1) {
				
				switch (e1.getErrorCode()) {
				case 12899:
					JOptionPane.showMessageDialog(aammd, "이름은 한글 최대 3글자까지 입력가능합니다.");
					break;

				default:
					JOptionPane.showMessageDialog(aammd, "SQL작업중 문제발생");
					e1.printStackTrace();
					
					break;
				}

			} // end catch
		}

		if (ae.getSource() == aammd.getJbtnCancel()) {
			aammd.dispose();
		}

	}

}
