package eduCourse_prj.admin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;

import javax.swing.JOptionPane;

import eduCourse_prj.VO.DeptVO;

public class AdminDeptMgtRegEvent extends WindowAdapter implements ActionListener {
	AdminDeptMgtRegDesign admrd;
	
	public AdminDeptMgtRegEvent(AdminDeptMgtRegDesign admrd) {
		this.admrd = admrd;
		
		
		
	}
	
	
	
	
	
	
	
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		
		if(ae.getSource() == admrd.getRegisterButton()) {
			JOptionPane.showMessageDialog(admrd, "등록버튼 클릭");
			
            String departmentName = admrd.getDepartmentNameTextField().getText();
            int capacity = Integer.parseInt(admrd.getDepartmentCapacityTextField().getText());
            DeptVO cVO = new DeptVO(departmentName, capacity);
            //acd.addDepartment(cVO);

			
			
		}
		if(ae.getSource()==admrd.getCancelButton()) {
			
			JOptionPane.showMessageDialog(admrd, "취소버튼 클릭");
		}
		
		

	}

}
