package eduCourse.admin.event;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import eduCourse.admin.dao.AdminAcadCalDAO;
import eduCourse.admin.design.AdminAcadCalDesign;

public class AdminAcadCalEvent extends WindowAdapter implements ActionListener {

	private AdminAcadCalDesign aacd;

	public AdminAcadCalEvent(AdminAcadCalDesign aacd) {
		this.aacd = aacd;
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == aacd.getSearchBtn()) { // 조회 버튼 클릭
			try {
				aacd.calSet();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if (ae.getSource() == aacd.getSaveBtn()) { // 저장 버튼 클릭
			int selectedYear = (int) aacd.getYearCb().getSelectedItem();
			int selectedMonth = (int) aacd.getMonthCb().getSelectedItem();
			int selectedDay = aacd.getDay();
			String yearMonthDay = selectedYear + "" + selectedMonth + "" + selectedDay;
			String memoText = aacd.getMemoJta().getText();
			AdminAcadCalDAO aacDAO = AdminAcadCalDAO.getInstance();
			try {
				if (aacd.getDayJl().getText().isEmpty()) {
					JOptionPane.showMessageDialog(aacd, "날짜를 선택해주세요");
					return;
				}
				if (aacd.getMemoJta().getText().isEmpty()) {
					JOptionPane.showMessageDialog(aacd, "내용을 입력해주세요");
					return;
				}
				if (aacDAO.selectOneCal(yearMonthDay).equals("")) {
					aacDAO.saveCal(memoText, yearMonthDay);
					JOptionPane.showMessageDialog(aacd, "저장이 성공으로 끝났습니다");
					aacd.calSet();
				} else {
					aacDAO.updateCal(memoText, yearMonthDay);
					JOptionPane.showMessageDialog(aacd, "저장이 성공으로 끝났습니다");
					aacd.calSet();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		if (ae.getSource() == aacd.getDeleteBtn()) {
			AdminAcadCalDAO aacDAO = AdminAcadCalDAO.getInstance();
			int selectedYear = (int) aacd.getYearCb().getSelectedItem();
			int selectedMonth = (int) aacd.getMonthCb().getSelectedItem();
			int selectedDay = aacd.getDay();
			String yearMonthDay = selectedYear + "" + selectedMonth + "" + selectedDay;

			try {
				if (aacd.getDayJl().getText().isEmpty()) {
					JOptionPane.showMessageDialog(aacd, "날짜를 선택해주세요");
					return;
				}
				if (aacDAO.selectOneCal(yearMonthDay).isEmpty()) {
					JOptionPane.showMessageDialog(aacd, "해당 날짜에 저장된 메모가 없습니다");
					return;
				}
				int result = JOptionPane.showConfirmDialog(aacd, "삭제하시겠습니까?", "메모 삭제", JOptionPane.YES_NO_OPTION);

				if (result == JOptionPane.YES_OPTION) {
					aacDAO.deleteCal(yearMonthDay);
					JOptionPane.showMessageDialog(aacd, "삭제완료 되었습니다");
					aacd.getMemoJta().setText("");
					aacd.calSet();
				} else if (result == JOptionPane.NO_OPTION) {

					return;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void windowClosing(WindowEvent we) {
		super.windowClosing(we);
	}

}
