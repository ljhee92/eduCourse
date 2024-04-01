package eduCourse.professor.event;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import eduCourse.VO.CrsRegVO;
import eduCourse.VO.CrsVO;
import eduCourse.professor.dao.CrsMgtRegDAO;
import eduCourse.professor.dao.ProfDAO;
import eduCourse.professor.design.ProfCrsMgtDesign;
import eduCourse.professor.design.ProfCrsMgtMdfyDesign;
import eduCourse.professor.design.ProfCrsMgtRegDesign;

public class ProfCrsMgtEvent extends WindowAdapter implements ActionListener{
	private ProfCrsMgtDesign pcmd;

	public ProfCrsMgtEvent(ProfCrsMgtDesign pcmd) {
		this.pcmd = pcmd;
	} // ProfCrsMgtEvent

	@Override
	public void actionPerformed(ActionEvent ae) {

		// 등록 버튼 클릭 시
		if (ae.getSource() == pcmd.getJbtnLecReg()) {
			new ProfCrsMgtRegDesign(pcmd, "강의 과목 등록");
			updateTable();
		} // end if

		if (ae.getSource() == pcmd.getJbtnSlct()) {
			int index = pcmd.getJtbLecMgt().getSelectedRow();
			if (index == -1) {
				JOptionPane.showMessageDialog(pcmd, "조회할 과목을 선택해주세요.");
				return;
			} // end if
			try {
				String prof_name = pcmd.getPhd().getlVO().getName();
				String crs_name = pcmd.getDtmProfMgt().getValueAt(index, 2).toString();

				CrsMgtRegDAO cmrDAO = CrsMgtRegDAO.getInstance();
				CrsRegVO crVO = cmrDAO.slctProfOneLect(crs_name);

				StringBuilder output = new StringBuilder();
				output.append("과목코드: ").append(crVO.getCourse_code()).append("\n");
				output.append("과목: ").append(crs_name).append("\n");
				output.append("담당교수: ").append(prof_name).append("\n");
				output.append("강의실: ").append(crVO.getLect_room()).append("\n");
				output.append("학점: ").append(crVO.getCredit_hours()).append("\n");
				output.append("정원: ").append(crVO.getCapacity());

				JTextArea jta = new JTextArea(output.toString(), 8, 50);
				JScrollPane jsp = new JScrollPane(jta);
				jta.setEditable(false);
				JOptionPane.showMessageDialog(pcmd, jsp, "강의 과목 상세 조회", JOptionPane.INFORMATION_MESSAGE);
			} catch (SQLException e1) {
				JOptionPane.showMessageDialog(pcmd, "SQL 문제가 발생했습니다.");
				e1.printStackTrace();
			} // end catch
		} // end if

		// 수정 버튼 클릭 시
		if (ae.getSource() == pcmd.getJbtnMdfy()) {
			int index = pcmd.getJtbLecMgt().getSelectedRow();
			if (index == -1) {
				JOptionPane.showMessageDialog(pcmd, "수정할 과목을 선택해주세요.");
				return;
			} // end if

			new ProfCrsMgtMdfyDesign(pcmd, "강의 과목 수정");
		} // end if

		// 삭제 버튼 클릭 시
		if (ae.getSource() == pcmd.getJbtnDel()) {
			int index = pcmd.getJtbLecMgt().getSelectedRow();
			if (index == -1) {
				JOptionPane.showMessageDialog(pcmd, "삭제할 과목을 선택해주세요.");
				return;
			} // end if

			int deleteFlag = JOptionPane.showConfirmDialog(pcmd, "정말 삭제하시겠습니까?", "강의 과목 삭제", JOptionPane.YES_NO_OPTION);
			switch (deleteFlag) {
			case JOptionPane.NO_OPTION:
				return;
			case JOptionPane.YES_OPTION:
				try {

					CrsMgtRegDAO cmrDAO = CrsMgtRegDAO.getInstance();
					ProfDAO pDAO = ProfDAO.getInstance();
					
					boolean deleteable = pDAO.checkDeleteable(pcmd.getDtmProfMgt().getValueAt(index, 1).toString());
					
					if(!deleteable) {//삭제 불가능일 경우
						JOptionPane.showMessageDialog(pcmd, "해당 과목을 수강중인 학생이 존재하여\n과목을 삭제할 수 없습니다.");
						return;
						
					}
					
					
					
					int prof_number = Integer.parseInt(pcmd.getPhd().getlVO().getId());
					String course_name = pcmd.getDtmProfMgt().getValueAt(index, 2).toString();
					CrsVO cVO = cmrDAO.slctOneCrsCode(course_name);

					cmrDAO.deleteLect(prof_number, cVO.getCourCode());
					JOptionPane.showMessageDialog(pcmd, course_name + " 과목 정보 삭제 성공");
					updateTable();
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(pcmd, "SQL 문제가 발생했습니다.");
					e1.printStackTrace();
				} // end catch
				break;
			} // end case
		} // end if
	} // actionPerformed

	/**
	 * 등록, 삭제버튼 클릭 시 테이블 최신화
	 */
	public void updateTable() {
		pcmd.getDtmProfMgt().setRowCount(0);
		CrsMgtRegDAO cmrDAO = CrsMgtRegDAO.getInstance();
		try {
			String strProf_number = pcmd.getPhd().getlVO().getId();
			int prof_number = Integer.parseInt(strProf_number);
			List<CrsVO> listCrsVO = cmrDAO.slctProfLect(prof_number);
			for (CrsVO cVO1 : listCrsVO) {
				Object[] row = { cVO1.getDeptName(), cVO1.getCourCode(), cVO1.getCourName() };
				pcmd.getDtmProfMgt().addRow(row);
			} // end for
		} catch (SQLException e) {
			e.printStackTrace();
		} // end catch

	} // updateTable

	@Override
	public void windowClosing(WindowEvent e) {
		pcmd.dispose();
	} // windowClosing

} // class