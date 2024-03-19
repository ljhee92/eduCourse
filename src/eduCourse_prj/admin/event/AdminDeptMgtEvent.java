package eduCourse_prj.admin.event;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import eduCourse_prj.VO.DeptDTO;
import eduCourse_prj.VO.DeptVO;
import eduCourse_prj.admin.dao.AdminDAO;
import eduCourse_prj.admin.design.AdminDeptMgtDesign;
import eduCourse_prj.admin.design.AdminDeptMgtRegDesign;


@SuppressWarnings("serial")

public class AdminDeptMgtEvent extends JDialog implements ActionListener{
	private AdminDeptMgtDesign admd;
	
	public AdminDeptMgtEvent(AdminDeptMgtDesign admd) {
		this.admd = admd;
		
		
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource()==admd.getJbtnDeptReg()) {
			JOptionPane.showMessageDialog(admd, "등록버튼 클릭");
			new AdminDeptMgtRegDesign(admd,"학과 등록");
		}//getJbtnReg
		
		//////////조회버튼클릭////////////
		if(ae.getSource() == admd.getJbtnSlct()) {
			int index = admd.getJtbDeptMgt().getSelectedRow();
			
			if(index == -1) {
				JOptionPane.showMessageDialog(admd, "조회할 학과를 선택해 주세요");
				return;
			}
			try {
		        int deptCode = Integer.parseInt(admd.getDtmDeptMgt().getValueAt(index, 0).toString());
				DeptDTO dDTO = null;
				AdminDAO aDAO = AdminDAO.getInstance();
				dDTO = aDAO.selectOneDept(deptCode);
				int profCnt = 1; //교수 앞에 붙일 숫자
				
				StringBuilder output = new StringBuilder();
				output.append("학과 코드: ").append(dDTO.getDept_code()).append("\n");
				output.append("학과: ").append(dDTO.getDept_name()).append("\n");
				List<String> professors = dDTO.getProf_name();  	//교수 이름 목록을 받기위한 리스트 생성
				output.append("담당교수: ");					
				for(String prof : professors) { // stringbuilder에 교수 이름 추가
				    output.append(profCnt).append(". ").append(prof).append(" ");
				    profCnt++;
				}
				output.append("\n정원: ").append(dDTO.getDept_capacity()).append("\n");
				
				System.out.println(output);
				JTextArea jta = new JTextArea(output.toString(), 8, 50);
				JScrollPane jsp = new JScrollPane(jta);
				jta.setEditable(false);				
				JOptionPane.showMessageDialog(admd, jsp, "선택 학과 조회", JOptionPane.INFORMATION_MESSAGE);
			
			}catch(SQLException e){
				JOptionPane.showMessageDialog(admd, "SQL 문제가 발생했습니다.");
				e.printStackTrace();
			}
				
			}//getJbtnSlct
		
		if(ae.getSource() == admd.getJbtnDel()) {
		    int index = admd.getJtbDeptMgt().getSelectedRow();
		    if(index == -1) {
		        JOptionPane.showMessageDialog(admd, "삭제할 학과를 선택해 주세요");
		        return;
		    }
		    try {
		        int deptCode = Integer.parseInt(admd.getDtmDeptMgt().getValueAt(index, 0).toString());
		        AdminDAO aDAO = AdminDAO.getInstance();
		        // 학과 삭제 메서드 호출
		        boolean success = aDAO.deleteDept(deptCode);
		        if(success) {
		            JOptionPane.showMessageDialog(admd, "학과 삭제가 완료되었습니다.");
		            // 새로운 화면을 생성하여 학과 목록을 다시 표시
		            AdminDeptMgtDesign newAdmd = new AdminDeptMgtDesign();
		            newAdmd.setVisible(true);
		            admd.dispose(); // 현재 화면을 닫음
		        } else {
		            JOptionPane.showMessageDialog(admd, "학과 삭제에 실패했습니다.");
		        }
		    } catch(SQLException e) {
		        JOptionPane.showMessageDialog(admd, "SQL 문제가 발생했습니다.");
		        e.printStackTrace();
		    }
			
		}//getJbtnDel

	}
}
