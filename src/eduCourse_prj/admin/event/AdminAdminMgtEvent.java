package eduCourse_prj.admin.event;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JOptionPane;

import eduCourse_prj.admin.AdminAdminMgtMdfyDesign;
import eduCourse_prj.admin.design.AdminAdminMgtDesign;

public class AdminAdminMgtEvent extends WindowAdapter implements ActionListener {

	AdminAdminMgtDesign aamd;

	public AdminAdminMgtEvent(AdminAdminMgtDesign aamd) {
		this.aamd = aamd;
	}

	@Override
	public void windowClosing(WindowEvent e) {
		aamd.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == aamd.getJbtnMdfy()) {

			//JOptionPane.showMessageDialog(aamd, "수정버튼 클릭");

			try {
				new AdminAdminMgtMdfyDesign(aamd, "관리자 수정");
			} catch (ArrayIndexOutOfBoundsException aiobe) {
				JOptionPane.showMessageDialog(aamd, "수정할 관리자를 선택해주세요.");
				return;
			}

		}

	}

}
