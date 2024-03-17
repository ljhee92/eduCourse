package eduCourse_prj.admin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import eduCourse_prj.VO.ProfVO;
import eduCourse_prj.professor.ProfessorDAO;

public class AdminProfMgtRegEvent extends WindowAdapter implements ActionListener {

	private AdminProfMgtRegDesign apmrd;
	
	public AdminProfMgtRegEvent(AdminProfMgtRegDesign apmrd) {
		this.apmrd = apmrd;
	} // AdminProfMgtRegEvent
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == apmrd.getJbtnReg()) {
			JOptionPane.showMessageDialog(apmrd, "등록버튼 클릭");
			ProfessorDAO pDAO = ProfessorDAO.getInstance();
			ProfVO pVO;
			
			String profName = apmrd.getJtfProfName().getText().trim();
			if(profName.isEmpty()) {
				JOptionPane.showMessageDialog(apmrd, "이름은 필수 입력 사항입니다.");
				return;
			} // end if
			
			String profPass = apmrd.getJpfProfPass().getPassword().toString().trim();
			if(profPass.isEmpty()) {
				JOptionPane.showMessageDialog(apmrd, "비밀번호는 필수 입력 사항입니다.");
				return;
			} // end if
			
			String profEmail = apmrd.getJtfProfEmail().getText().trim();
			if(!profEmail.isEmpty() && !profEmail.contains("@")) {
				JOptionPane.showMessageDialog(apmrd, "유효한 이메일 형식이 아닙니다.");
				return;
			} // end if
			
			String deptName = (String) apmrd.getJcbDept().getSelectedItem();
			
			pVO = new ProfVO(profPass, profName, profEmail, deptName);
			
			try {
				pDAO.addProf(pVO);
			} catch (SQLException e1) {
				JOptionPane.showMessageDialog(apmrd, "SQL 문제가 발생했습니다.");
				e1.printStackTrace();
			} // end catch
		} // end if

		if(e.getSource() == apmrd.getJbtnCancel()) {
			apmrd.dispose();
		} // end if
	} // actionPerformed
	
} // class