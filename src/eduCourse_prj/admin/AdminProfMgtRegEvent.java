package eduCourse_prj.admin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import eduCourse_prj.VO.ProfVO;
import eduCourse_prj.professor.ProfDAO;

public class AdminProfMgtRegEvent extends WindowAdapter implements ActionListener {

	private AdminProfMgtRegDesign apmrd;
	
	public AdminProfMgtRegEvent(AdminProfMgtRegDesign apmrd) {
		this.apmrd = apmrd;
	} // AdminProfMgtRegEvent
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == apmrd.getJbtnReg()) {
			ProfDAO pDAO = ProfDAO.getInstance();
			ProfVO pVO;
			
			String profName = apmrd.getJtfProfName().getText().trim();
			if(profName.isEmpty()) {
				JOptionPane.showMessageDialog(apmrd, "이름은 필수 입력 사항입니다.");
				return;
			} // end if
			
			String profPass = "";
			char[] secret_pass = apmrd.getJpfProfPass().getPassword();
			for(char cha : secret_pass) {
				Character.toString(cha);
				profPass += (profPass.equals("")) ? "" + cha + "" : cha + "";
			} // end for
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
				JOptionPane.showMessageDialog(null, pVO.getProf_name() + " 교수님 정보가 성공적으로 등록되었습니다.\n등록된 정보를 확인하시려면 교수관리 창을 종료 후 재실행해주세요.");
				apmrd.dispose();
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