package eduCourse_prj.admin.event;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import eduCourse_prj.VO.AdminProfVO;
import eduCourse_prj.VO.ProfVO;
import eduCourse_prj.VO.SlctStdVO;
import eduCourse_prj.admin.design.AdminProfMgtDesign;
import eduCourse_prj.admin.design.AdminProfMgtMdfyDesign;
import eduCourse_prj.admin.design.AdminProfMgtRegDesign;
import eduCourse_prj.professor.dao.ProfDAO;

public class AdminProfMgtEvent extends WindowAdapter implements ActionListener {

	private AdminProfMgtDesign apmd;
	ProfDAO pDAO = ProfDAO.getInstance();

	public AdminProfMgtEvent(AdminProfMgtDesign apmd) {
		this.apmd = apmd;
	} // AdminProfMgtEvent

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == apmd.getJbtnSlctTop()) {
			JOptionPane.showMessageDialog(apmd, "상단 조회버튼 클릭");
			updateTable();
			

		}

		// 교수 등록 버튼 클릭
		if (e.getSource() == apmd.getJbtnProfReg()) {
			new AdminProfMgtRegDesign(apmd, "교수 등록");
		} // end if

		// 교수 정보 상세 조회 클릭
		if (e.getSource() == apmd.getJbtnSlct()) {
			int index = apmd.getJtbProfMgt().getSelectedRow();
			if (index == -1) {
				JOptionPane.showMessageDialog(apmd, "조회할 교수님을 선택해주세요.");
				return;
			} // end if
			try {
				int prof_number = Integer.parseInt(apmd.getDtmProfMgt().getValueAt(index, 1).toString());
				ProfDAO pDAO = ProfDAO.getInstance();
				AdminProfVO apVO = pDAO.slctProfMgtSlct(prof_number);
				int profCnt = 1;

				StringBuilder output = new StringBuilder();
				output.append("교번: ").append(apVO.getProf_number()).append("\n");
				output.append("이름: ").append(apVO.getProf_name()).append("\n");
				output.append("이메일: ").append(apVO.getProf_email() != null ? apVO.getProf_email() : "").append("\n");
				output.append("소속학과: ").append(apVO.getDept_name()).append("\n");

				List<String> listCourse = apVO.getCourse_name(); // 과목 목록을 받기위한 리스트 생성
				output.append("담당 과목: ");
				for (String courses : listCourse) {
					output.append(profCnt).append(". ").append(courses).append(" ");
					profCnt++;
				}

				JTextArea jta = new JTextArea(output.toString(), 8, 50);
				JScrollPane jsp = new JScrollPane(jta);
				jta.setEditable(false);
				JOptionPane.showMessageDialog(apmd, jsp, "교수 상세 조회", JOptionPane.INFORMATION_MESSAGE);
			} catch (SQLException e1) {
				JOptionPane.showMessageDialog(apmd, "SQL 문제가 발생했습니다.");
				e1.printStackTrace();
			} // end catch
		} // end if

		// 교수 정보 수정 버튼 클릭
		if (e.getSource() == apmd.getJbtnMdfy()) {
			int index = apmd.getJtbProfMgt().getSelectedRow();
			if (index == -1) {
				JOptionPane.showMessageDialog(apmd, "수정할 교수님을 선택해주세요.");
				return;
			} // end if
			new AdminProfMgtMdfyDesign(apmd, "교수 정보 수정");
		} // end if

		// 교수 정보 삭제 버튼 클릭
		if (e.getSource() == apmd.getJbtnDel()) {
			int index = apmd.getJtbProfMgt().getSelectedRow();
			if (index == -1) {
				JOptionPane.showMessageDialog(apmd, "삭제할 교수님을 선택해주세요.");
				return;
			} // end if

			int deleteFlag = JOptionPane.showConfirmDialog(apmd, "정말 삭제하시겠습니까?");
			switch (deleteFlag) {
			case JOptionPane.CANCEL_OPTION:
			case JOptionPane.NO_OPTION:
				return;
			case JOptionPane.OK_OPTION:
				try {
					ProfDAO pDAO = ProfDAO.getInstance();
					int prof_number = Integer.parseInt(apmd.getDtmProfMgt().getValueAt(index, 1).toString());

					pDAO.deleteProf(prof_number);
					JOptionPane.showMessageDialog(apmd, apmd.getDtmProfMgt().getValueAt(index, 2).toString()
							+ " 교수님 정보 삭제 성공");
					
					updateTable();
					
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(apmd, "SQL 문제가 발생했습니다.");
					e1.printStackTrace();
				} // end catch
				break;
			} // end case
		} // end if
	} // actionPerformed

	
	
	/**
	 * 조회버튼, 삭제버튼 클릭 시 테이블 최신화
	 */
	public void updateTable() {

		apmd.getDtmProfMgt().setRowCount(0);

		int dept_code = 0; // 학과 코드

		int prof_num = 0; // 교번

		// 학과가 "전체"일 경우
		if (apmd.getJcbDept().getSelectedItem().equals("전체")) {

		} else {// 학과가 "전체"가 아닌 모든 경우
			dept_code = apmd.getLDept().get((apmd.getJcbDept().getSelectedIndex() - 1)).getDept_code();

		}

		// 교번 입력 유무 체크
		if (!(apmd.getJtfProfNum().getText().isEmpty())) {
			try {

				prof_num = Integer.parseInt(apmd.getJtfProfNum().getText());
			} catch (Exception ee) {
				ee.printStackTrace();
				JOptionPane.showMessageDialog(apmd, "숫자만 입력가능 9자리");
				return;
			}
		}

		
		
		@SuppressWarnings("unused")
		List<ProfVO> lProfVO;

	try {
		List<ProfVO> listProfVO = pDAO.slctProf(dept_code ,prof_num);

		for (ProfVO pVO : listProfVO) {

			Object[] row = { pVO.getDept_name(), pVO.getProf_number(), pVO.getProf_name()};
			apmd.getDtmProfMgt().addRow(row);
		} // end for

	} catch (SQLException se) {
		se.printStackTrace();
	} // end catch
		
		
		
	}
	
	
	
	
	
	
} // class