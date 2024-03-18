package eduCourse_prj.admin.event;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JOptionPane;

import eduCourse_prj.admin.design.AdminAcadCalDesign;
import eduCourse_prj.admin.design.AdminAdminMgtDesign;
import eduCourse_prj.admin.design.AdminCrsDesign;
import eduCourse_prj.admin.design.AdminDeptMgtDesign;
import eduCourse_prj.admin.design.AdminHomeDesign;
import eduCourse_prj.admin.design.AdminProfMgtDesign;
import eduCourse_prj.admin.design.AdminStudMgtDesign;
import eduCourse_prj.login.SelectLoginDesign;

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
			JOptionPane.showMessageDialog(awd, "관리자 관리 클릭");
			new AdminAdminMgtDesign(awd, "관리자 관리");

		}
		if (ae.getSource() == awd.getJbtProfMgt()) {
			JOptionPane.showMessageDialog(awd, "교수 관리 클릭");
			new AdminProfMgtDesign(awd, "교수 관리");

		}
		if (ae.getSource() == awd.getJbtDeptMgt()) {
			JOptionPane.showMessageDialog(awd, "학과 관리 클릭");
			new AdminDeptMgtDesign(awd, "학과 관리");
		}
		if (ae.getSource() == awd.getJbtStudMgt()) {
			JOptionPane.showMessageDialog(awd, "학생 관리 클릭");
			new AdminStudMgtDesign(awd, "학생 관리");
		}
		if (ae.getSource() == awd.getJbtCourMgt()) {
			JOptionPane.showMessageDialog(awd, "과목 관리 클릭");
			new AdminCrsDesign(awd, "과목 관리");
		}
		if (ae.getSource() == awd.getJbtSchedMgt()) {
			JOptionPane.showMessageDialog(awd, "일정 관리 클릭");
			new AdminAcadCalDesign(awd, "학사 일정 관리");
		}
		if (ae.getSource() == awd.getJbtLogout()) {
			JOptionPane.showMessageDialog(awd, "로그아웃 버튼클릭");
			new SelectLoginDesign();
			awd.dispose();
		}

	}

}
