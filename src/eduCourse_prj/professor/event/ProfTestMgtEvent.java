package eduCourse_prj.professor.event;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import eduCourse_prj.professor.dao.ProfDAO;
import eduCourse_prj.professor.design.ProfTestMdfyDesign;
import eduCourse_prj.professor.design.ProfTestMgtDesign;
import eduCourse_prj.professor.design.ProfTestRegDesign;

public class ProfTestMgtEvent extends WindowAdapter implements ActionListener, ListSelectionListener {
	ProfTestMgtDesign ptmd;

	public ProfTestMgtEvent(ProfTestMgtDesign ptmd) {
		this.ptmd = ptmd;
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		// 수정 버튼 클릭 시
		if (ae.getSource() == ptmd.getJbtnTestMdfy()) {
			int seletedRow = ptmd.getJtbTestMgt().getSelectedRow();

			if (seletedRow == -1) {
				JOptionPane.showMessageDialog(ptmd, "시험을 출제할 과목을 선택해주세요");
				return;
			} // end if

			String selectedValid = ptmd.getJtbTestMgt().getValueAt(seletedRow, 1).toString();
			String selectedReg = (String) ptmd.getJtbTestMgt().getValueAt(seletedRow, 2);

			if (selectedValid.equals("N") && selectedReg.equals("출제전")) {
				JOptionPane.showMessageDialog(ptmd, "출제 전 시험은 수정할 수 없습니다.");
				return;
			} // end if

			if (selectedValid.equals("Y") && selectedReg.equals("출제완료")) {
				JOptionPane.showMessageDialog(ptmd, "활성화된 시험은 수정불가합니다.");
				return;
			} // end if

			new ProfTestMdfyDesign(ptmd, "시험 문제 수정");
		} // end if

		// 등록 버튼 클릭 시
		if (ae.getSource() == ptmd.getJbtnTestReg()) {
			int seletedRow = ptmd.getJtbTestMgt().getSelectedRow();
			if (seletedRow == -1) {
				JOptionPane.showMessageDialog(ptmd, "시험을 출제할 과목을 선택해주세요");
				return;
			}
			String seletedValue = (String) ptmd.getJtbTestMgt().getValueAt(seletedRow, 2);
			if (seletedValue.equals("출제완료")) {
				JOptionPane.showMessageDialog(ptmd, "이미 출제 완료된 시험입니다.\n     <수정가능>");
				return;
			}
			new ProfTestRegDesign(ptmd, "시험 문제 출제");
		}
		////////////////////////// 활성화 버튼 클릭시///////////////////////////
		if (ae.getSource() == ptmd.getJrbtnEnable()) {
			int index = ptmd.getJtbTestMgt().getSelectedRow();

			if (index == -1) {
				JOptionPane.showMessageDialog(ptmd, "과목을 선택해주세요.");
				return;
			} // end if

			String course_name = ptmd.getDtmTestMgt().getValueAt(index, 0).toString();
			String testEnable = ptmd.getDtmTestMgt().getValueAt(index, 2).toString();
			if (!testEnable.equals("출제완료")) {
				JOptionPane.showMessageDialog(ptmd, "시험 활성화는 출제완료된 과목만 가능합니다");
				ptmd.getJrbtnDisable().setSelected(true);
				return;
			}

			ProfDAO pDAO = ProfDAO.getInstance();
			try {
				pDAO.updateTestFlag(course_name, "Y");

				ptmd.getDtmTestMgt().setRowCount(0);
				ptmd.slctTestMgt();
				ptmd.getJrbtnEnable().setSelected(false);
				JOptionPane.showMessageDialog(ptmd, course_name + " 과목의 시험이 활성화되었습니다.");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		////////////////////////// 비활성화 버튼 클릭시///////////////////////////
		if (ae.getSource() == ptmd.getJrbtnDisable()) {
			int index = ptmd.getJtbTestMgt().getSelectedRow();
			if (index == -1) {
				JOptionPane.showMessageDialog(ptmd, "과목을 선택해주세요.");
				return;
			} // end if
			String course_name = ptmd.getDtmTestMgt().getValueAt(index, 0).toString();

			ProfDAO pDAO = ProfDAO.getInstance();
			try {
				pDAO.updateTestFlag(course_name, "N");
				ptmd.getDtmTestMgt().setRowCount(0);
				ptmd.slctTestMgt();
				ptmd.getJrbtnEnable().setSelected(true);
				JOptionPane.showMessageDialog(ptmd, course_name + " 과목의 시험이 비활성화되었습니다.");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public void windowClosing(WindowEvent e) {
		super.windowClosing(e);
	}

	@Override
	public void valueChanged(ListSelectionEvent event) {

		if (!event.getValueIsAdjusting()) {
			int selectedRow = ptmd.getJtbTestMgt().getSelectedRow();
			if (selectedRow != -1) {
				String check = (String) ptmd.getJtbTestMgt().getValueAt(selectedRow, 1);

				if (check.equals("Y")) {
					ptmd.getJrbtnEnable().setSelected(true);
				} else {
					ptmd.getJrbtnDisable().setSelected(true);

				}

			}

		}

	}

}
