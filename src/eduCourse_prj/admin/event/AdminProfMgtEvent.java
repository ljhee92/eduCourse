package eduCourse_prj.admin.event;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import eduCourse_prj.VO.ProfVO;
import eduCourse_prj.admin.design.AdminProfMgtDesign;
import eduCourse_prj.admin.design.AdminProfMgtMdfyDesign;
import eduCourse_prj.admin.design.AdminProfMgtRegDesign;
import eduCourse_prj.professor.dao.ProfDAO;

public class AdminProfMgtEvent extends WindowAdapter implements ActionListener {
	
	private AdminProfMgtDesign apmd;
	
	public AdminProfMgtEvent(AdminProfMgtDesign apmd) {
		this.apmd = apmd;
	} // AdminProfMgtEvent

	@Override
	public void actionPerformed(ActionEvent e) {
		// 교수 등록 버튼 클릭
		if(e.getSource() == apmd.getJbtnProfReg()) {
			new AdminProfMgtRegDesign(apmd, "교수 등록");
		} // end if
		
		// 교수 정보 상세 조회 클릭
		if(e.getSource() == apmd.getJbtnSlct()) {
			int index = apmd.getJtbProfMgt().getSelectedRow();
			if(index == -1) {
				JOptionPane.showMessageDialog(apmd, "조회할 교수님을 선택해주세요.");
				return;
			} // end if
			try {
				int prof_number = Integer.parseInt(apmd.getDtmProfMgt().getValueAt(index, 0).toString());
				
				ProfDAO pDAO = ProfDAO.getInstance();
				ProfVO pVO = pDAO.slctProfMgtSlct(prof_number);
				
				StringBuilder output = new StringBuilder();
				output.append("교번: ").append(pVO.getProf_number()).append("\n");
				output.append("이름: ").append(pVO.getProf_name()).append("\n");
				output.append("이메일: ").append(pVO.getProf_email()!=null?pVO.getProf_email():"").append("\n");
				output.append("소속학과: ").append(pVO.getDept_name()).append("\n");
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
		if(e.getSource() == apmd.getJbtnMdfy()) {
			int index = apmd.getJtbProfMgt().getSelectedRow();
			if(index == -1) {
				JOptionPane.showMessageDialog(apmd, "수정할 교수님을 선택해주세요.");
				return;
			} // end if
			new AdminProfMgtMdfyDesign(apmd, "교수 정보 수정");
		} // end if
		
		// 교수 정보 삭제 버튼 클릭
		if(e.getSource() == apmd.getJbtnDel()) {
			int index = apmd.getJtbProfMgt().getSelectedRow();
			if(index == -1) {
				JOptionPane.showMessageDialog(apmd, "삭제할 교수님을 선택해주세요.");
				return;
			} // end if
			
			int deleteFlag = JOptionPane.showConfirmDialog(apmd, "정말 삭제하시겠습니까?");
			switch(deleteFlag) {
			case JOptionPane.CANCEL_OPTION :
			case JOptionPane.NO_OPTION : return;
			case JOptionPane.OK_OPTION :
				try {
				ProfDAO pDAO = ProfDAO.getInstance();
				int prof_number = Integer.parseInt(apmd.getDtmProfMgt().getValueAt(index, 0).toString());

				pDAO.deleteProf(prof_number);
				JOptionPane.showMessageDialog(apmd, apmd.getDtmProfMgt().getValueAt(index, 1).toString() + " 교수님 정보 삭제 성공\n변경된 정보를 확인하시려면 교수관리 창을 종료 후 재실행해주세요.");
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(apmd, "SQL 문제가 발생했습니다.");
					e1.printStackTrace();
				} // end catch
				break;
			} // end case
		} // end if
	} // actionPerformed

} // class