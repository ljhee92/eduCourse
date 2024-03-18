package eduCourse_prj.admin.event;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import eduCourse_prj.VO.CrsVO;
import eduCourse_prj.VO.ProfVO;
import eduCourse_prj.admin.dao.AdminDAO;
import eduCourse_prj.admin.design.AdminCrsDesign;
import eduCourse_prj.admin.design.AdminCrsRegDisgn;
import eduCourse_prj.professor.dao.ProfDAO;

public class AdminCrsEvent extends WindowAdapter implements ActionListener{
	AdminCrsDesign acd;
	public AdminCrsEvent(AdminCrsDesign acd) {
		this.acd = acd;
		
		
		
	}
	
	
	
	
	
	
	
	
	@Override
	public void windowClosing(WindowEvent e) {
		acd.dispose();
		super.windowClosing(e);
	}





	@Override
	public void actionPerformed(ActionEvent ae) {
		
		if (ae.getSource() == acd.getJbtnCrsReg()) {
			JOptionPane.showMessageDialog(acd, "등록버튼클릭");
			new AdminCrsRegDisgn(acd, "과목 등록");
			
			
		}
		// 과목 정보 상세 조회 클릭
		if(ae.getSource() == acd.getJbtnSlct()) {
			int index = acd.getJtbCrsMgt().getSelectedRow();
			if(index == -1) {
				JOptionPane.showMessageDialog(acd, "조회할 과목을 선택해주세요.");
				return;
			} // end if
			try {
				

				
				String crs_name =acd.getDtmCrsMgt().getValueAt(index, 1).toString();

				AdminDAO aDAO = AdminDAO.getInstance();
				CrsVO cVO = aDAO.slctOneCrs(crs_name);
				
				StringBuilder output = new StringBuilder();
				output.append("학과: ").append(cVO.getDeptName()).append("\n");
				output.append("과목: ").append(cVO.getCourName()).append("\n");
				output.append("과목 코드: ").append(cVO.getCourCode()).append("\n");
				output.append("학점: ").append(cVO.getCreditHour()).append("\n");
				JTextArea jta = new JTextArea(output.toString(), 8, 50);
				JScrollPane jsp = new JScrollPane(jta);
				jta.setEditable(false);
				JOptionPane.showMessageDialog(acd, jsp, "과목 상세 조회", JOptionPane.INFORMATION_MESSAGE);
			} catch (SQLException e1) {
				JOptionPane.showMessageDialog(acd, "SQL 문제가 발생했습니다.");
				e1.printStackTrace();
			} // end catch
		} // end if
		
		// 과목 정보 삭제 버튼 클릭
		if(ae.getSource() == acd.getJbtnDel()) {
			int index = acd.getJtbCrsMgt().getSelectedRow();
			if(index == -1) {
				JOptionPane.showMessageDialog(acd, "삭제할 과목을 선택해주세요.");
				return;
			} // end if
			
			int deleteFlag = JOptionPane.showConfirmDialog(acd, "정말 삭제하시겠습니까?");
			switch(deleteFlag) {
			case JOptionPane.CANCEL_OPTION :
			case JOptionPane.NO_OPTION : return;
			case JOptionPane.OK_OPTION :
				try {
				AdminDAO aDAO = AdminDAO.getInstance();
				String crs_name = (acd.getDtmCrsMgt().getValueAt(index, 1).toString());

				aDAO.deleteCrs(crs_name);
				JOptionPane.showMessageDialog(acd, acd.getDtmCrsMgt().getValueAt(index, 1).toString() + " 과목 정보 삭제 성공\n변경된 정보를 확인하시려면 교수관리 창을 종료 후 재실행해주세요.");
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(acd, "SQL 문제가 발생했습니다.");
					e1.printStackTrace();
				} // end catch
				break;
			} // end case
		} // end if
		
		
		
		
		
		
	}

}
