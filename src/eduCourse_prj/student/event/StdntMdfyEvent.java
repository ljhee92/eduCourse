package eduCourse_prj.student.event;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import eduCourse_prj.VO.StdntVO;
import eduCourse_prj.student.dao.StdntDAO;
import eduCourse_prj.student.design.StdntMdfyDesign;

public class StdntMdfyEvent extends WindowAdapter implements ActionListener {
	
	private StdntMdfyDesign smd;
	
	public StdntMdfyEvent(StdntMdfyDesign smd) {
		this.smd = smd;
	} // StdntMdfyEvent

	@Override
	public void actionPerformed(ActionEvent e) {
		// 수정버튼 클릭
		if(e.getSource() == smd.getJbtnMdfy()) {
			StdntDAO sDAO = StdntDAO.getInstance();
			
			int stdntNum = Integer.parseInt(smd.getJtfStdntNum().getText().trim());
			
			String stdntPass = "";
			char[] secret_pass = smd.getJpfStdntPass().getPassword();
			for(char cha : secret_pass) {
				Character.toChars(cha);
				stdntPass += (stdntPass.equals("")) ? "" + cha + "" : cha + "";
			} // end for
			if(stdntPass.isEmpty()) {
				JOptionPane.showMessageDialog(smd, "비밀번호는 필수 입력 사항입니다.");
				return;
			} // end if
			
			String stdntEmail = smd.getJtfStdntEmail().getText().trim();
			if(!stdntEmail.isEmpty() && !stdntEmail.contains("@")) {
				JOptionPane.showMessageDialog(smd, "유효한 이메일 형식이 아닙니다.");
				return;
			} // end if
			
			String stdntAddr = smd.getJtfStdntAddr().getText().trim();

			try {
				StdntVO sVO = new StdntVO(stdntNum, stdntPass, smd.getShd().getlVO().getName(), stdntEmail, stdntAddr, null, null, null);
				sDAO.modifyStdnt(sVO);
				JOptionPane.showMessageDialog(smd, sVO.getStdnt_name() + " 학생님 정보가 성공적으로 수정되었습니다.");
				smd.dispose();
			} catch (SQLException e1) {
				JOptionPane.showMessageDialog(smd, "SQL 문제가 발생했습니다.");
				e1.printStackTrace();
			} // end catch
		} // end if

		// 취소버튼 클릭
		if(e.getSource() == smd.getJbtnCancel()) {
			smd.dispose();
		} // end if
	} // actionPerformed

} // class