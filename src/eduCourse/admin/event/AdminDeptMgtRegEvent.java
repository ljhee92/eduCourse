package eduCourse.admin.event;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import eduCourse.VO.DeptVO;
import eduCourse.admin.dao.AdminDAO;
import eduCourse.admin.design.AdminDeptMgtRegDesign;

public class AdminDeptMgtRegEvent extends WindowAdapter implements ActionListener {
	private AdminDeptMgtRegDesign admrd;

	public AdminDeptMgtRegEvent(AdminDeptMgtRegDesign admrd) {
		this.admrd = admrd;

	}

	@Override
	public void actionPerformed(ActionEvent ae) {

		if (ae.getSource() == admrd.getRegisterButton()) {
			AdminDAO aDAO = AdminDAO.getInstance();
			DeptVO dVO;

			String departmentName = admrd.getDepartmentNameTextField().getText();
			if (departmentName.isEmpty()) {
				JOptionPane.showMessageDialog(admrd, "학과명은 필수 입력 사항입니다.");
				return;
			}

			String capacityText = admrd.getDepartmentCapacityTextField().getText();
			if (capacityText.isEmpty()) {
				JOptionPane.showMessageDialog(admrd, "정원은 필수 입력 사항입니다.");
				return;
			}

			try {
				int capacity = Integer.parseInt(capacityText);
				// capacity 유효성 검사
				if (capacity <= 0) {
					JOptionPane.showMessageDialog(admrd, "정원은 양수만 입력 가능합니다.");
					return;
				}

				dVO = new DeptVO(departmentName, capacity);
				aDAO.addDepartment(dVO);
				admrd.getAdmd().getDtmDeptMgt().setRowCount(0);
				admrd.getAdmd().slctDeptMgt();
				admrd.dispose();
			} catch (NumberFormatException nfe) {
				JOptionPane.showMessageDialog(admrd, "정원은 숫자만 입력 가능합니다.");
				// nfe.printStackTrace();
			} catch (SQLException se) {
				JOptionPane.showMessageDialog(admrd, "SQL예외가 발생했습니다.");

			}

		}
		if (ae.getSource() == admrd.getCancelButton()) {

			admrd.dispose();
		}

	}

}
