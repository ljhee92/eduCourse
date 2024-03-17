package eduCourse_prj.admin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import eduCourse_prj.VO.ProfVO;
import eduCourse_prj.professor.ProfessorDAO;

public class AdminProfMgtEvent extends WindowAdapter implements ActionListener {
	
	private AdminProfMgtDesign apmd;
	
	public AdminProfMgtEvent(AdminProfMgtDesign apmd) {
		this.apmd = apmd;
	} // AdminProfMgtEvent

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == apmd.getJbtnProfReg()) {
			new AdminProfMgtRegDesign(apmd, "교수 등록");
		} // end if

		if(e.getSource() == apmd.getJbtnSlct()) {
			try {
				int index = apmd.getJtbProfMgt().getSelectedRow();
				int prof_number = Integer.parseInt(apmd.getDtmProfMgt().getValueAt(index, 0).toString());
				ProfessorDAO pDAO = ProfessorDAO.getInstance();
				List<ProfVO> listPVO = pDAO.slctProfMgsSlct(prof_number);
				StringBuilder output = new StringBuilder();
				for(ProfVO pVO : listPVO) {
					output.append("교번: ").append(pVO.getProf_number()).append("\n");
					output.append("이름: ").append(pVO.getProf_name()).append("\n");
					output.append("이메일: ").append(pVO.getProf_email()!=null?pVO.getProf_email():"").append("\n");
					output.append("소속학과: ").append(pVO.getDept_name()).append("\n");
					JTextArea jta = new JTextArea(output.toString(), 8, 50);
					JScrollPane jsp = new JScrollPane(jta);
					jta.setEditable(false);
					JOptionPane.showMessageDialog(apmd, jsp, "교수 상세 조회", JOptionPane.INFORMATION_MESSAGE);
				} // end for
			} catch (SQLException e1) {
				e1.printStackTrace();
			} // end catch
		} // end if
		
		if(e.getSource() == apmd.getJbtnMdfy()) {
			JOptionPane.showMessageDialog(apmd, "수정버튼");
		} // end if
		
		if(e.getSource() == apmd.getJbtnDel()) {
			JOptionPane.showConfirmDialog(apmd, "정말 삭제하시겠습니까?");
		} // end if
	} // actionPerformed

} // class