package eduCourse.admin.event;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import eduCourse.admin.design.AdminAcadCalDesign;
import eduCourse.admin.design.AdminAdminMgtDesign;
import eduCourse.admin.design.AdminCrsDesign;
import eduCourse.admin.design.AdminDeptMgtDesign;
import eduCourse.admin.design.AdminHomeDesign;
import eduCourse.admin.design.AdminProfMgtDesign;
import eduCourse.admin.design.AdminStudMgtDesign;
import eduCourse.login.SelectLoginDesign;

public class AdminHomeEvent extends WindowAdapter implements ActionListener {
	private AdminHomeDesign awd;

	public AdminHomeEvent(AdminHomeDesign awd) {
		this.awd = awd;

	}

	@Override
	public void windowClosing(WindowEvent e) {

		awd.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent ae) {

		if (ae.getSource() == awd.getJbtAdminMgt()) {
			new AdminAdminMgtDesign(awd, "관리자 관리");

		}
		if (ae.getSource() == awd.getJbtProfMgt()) {
			new AdminProfMgtDesign(awd, "교수 관리");

		}
		if (ae.getSource() == awd.getJbtDeptMgt()) {
			new AdminDeptMgtDesign(awd, "학과 관리");
		}
		if (ae.getSource() == awd.getJbtStudMgt()) {
			new AdminStudMgtDesign(awd, "학생 관리");
		}
		if (ae.getSource() == awd.getJbtCourMgt()) {
			new AdminCrsDesign(awd, "과목 관리");
		}
		if (ae.getSource() == awd.getJbtSchedMgt()) {
			new AdminAcadCalDesign(awd, "학사 일정 관리");
		}
		if (ae.getSource() == awd.getJbtLogout()) {
			new SelectLoginDesign();
			awd.dispose();
		}

	}

}
