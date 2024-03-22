package eduCourse_prj.professor.event;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import eduCourse_prj.VO.CrsVO;
import eduCourse_prj.VO.LectureVO;
import eduCourse_prj.professor.dao.CrsMgtRegDAO;
import eduCourse_prj.professor.design.ProfCrsMgtRegDesign;

public class ProfCrsMgtRegEvent extends WindowAdapter implements ActionListener {
	
	private ProfCrsMgtRegDesign pcmrd;
	
	public ProfCrsMgtRegEvent(ProfCrsMgtRegDesign pcmrd) {
		this.pcmrd = pcmrd;
	} // ProfCrsMgtRegEvent

	@Override
	public void actionPerformed(ActionEvent e) {
		// 과목 콤보박스 클릭 시
		if(e.getSource() == pcmrd.getJcbCrsName()) {
			CrsMgtRegDAO cmrDAO = CrsMgtRegDAO.getInstance();
			try {
				CrsVO cVO = cmrDAO.slctOneCrsCode(pcmrd.getJcbCrsName().getSelectedItem().toString());
				pcmrd.getJtfCrsCode().setText(cVO.getCourCode());
				pcmrd.getJtfCredit().setText(String.valueOf(cVO.getCreditHour()));
			} catch (SQLException e1) {
				JOptionPane.showMessageDialog(pcmrd, "SQL 문제가 발생했습니다.");
				e1.printStackTrace();
			} // end catch
		} // end if
		
		// 등록 버튼 클릭 시
		if(e.getSource() == pcmrd.getJbtnReg()) {
			CrsMgtRegDAO cmrDAO = CrsMgtRegDAO.getInstance();
			
			int prof_number = Integer.parseInt(pcmrd.getPcmd().getPhd().getlVO().getId());
			String course_code = pcmrd.getJtfCrsCode().getText();
			
			if(pcmrd.getJtfCapa().getText().isEmpty()) {
				JOptionPane.showMessageDialog(pcmrd, "정원은 필수 입력사항입니다.");
				return;
			} // end if
			
			int capacity = Integer.parseInt(pcmrd.getJtfCapa().getText().trim());
			String lect_room = pcmrd.getJcbLectRoom().getSelectedItem().toString();
			
			LectureVO ltVO = new LectureVO(prof_number, course_code, capacity, 0, null, lect_room);
			try {
				cmrDAO.insertLect(ltVO);
				JOptionPane.showMessageDialog(pcmrd, pcmrd.getJcbCrsName().getSelectedItem().toString()
						 + " 과목 등록이 완료되었습니다.");
			} catch (SQLException e1) {
				JOptionPane.showMessageDialog(pcmrd, "SQL 문제가 발생했습니다.");
				e1.printStackTrace();
			} // end catch
		} // end if
		
		// 취소 버튼 클릭 시
		if(e.getSource() == pcmrd.getJbtnCancel()) {
			pcmrd.dispose();
		} // end if
	} // actionPerformed

} // class