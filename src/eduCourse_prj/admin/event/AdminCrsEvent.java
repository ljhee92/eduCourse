package eduCourse_prj.admin.event;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import eduCourse_prj.VO.CrsVO;

import eduCourse_prj.admin.dao.AdminDAO;
import eduCourse_prj.admin.design.AdminCrsDesign;
import eduCourse_prj.admin.design.AdminCrsRegDisgn;

public class AdminCrsEvent extends WindowAdapter implements ActionListener {
	private AdminCrsDesign acd;
	private AdminDAO aDAO = AdminDAO.getInstance();

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

		if (ae.getSource() == acd.getJbtnSlctTop()) {
			updateTable();

		}

		if (ae.getSource() == acd.getJbtnCrsReg()) {
			new AdminCrsRegDisgn(acd, "과목 등록");

		}
		// 과목 정보 상세 조회 클릭
		if (ae.getSource() == acd.getJbtnSlct()) {
			int index = acd.getJtbCrsMgt().getSelectedRow();
			if (index == -1) {
				JOptionPane.showMessageDialog(acd, "조회할 과목을 선택해주세요.");
				return;
			} // end if
			try {

				String crs_name = acd.getDtmCrsMgt().getValueAt(index, 1).toString();

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
		if (ae.getSource() == acd.getJbtnDel()) {
			int index = acd.getJtbCrsMgt().getSelectedRow();
			if (index == -1) {
				JOptionPane.showMessageDialog(acd, "삭제할 과목을 선택해주세요.");
				return;
			} // end if

			int deleteFlag = JOptionPane.showConfirmDialog(acd, "정말 삭제하시겠습니까?", "과목정보 삭제", JOptionPane.YES_NO_OPTION);
			switch (deleteFlag) {
			case JOptionPane.NO_OPTION:
				return;
			case JOptionPane.YES_OPTION:
				try {
					AdminDAO aDAO = AdminDAO.getInstance();
					String crs_name = (acd.getDtmCrsMgt().getValueAt(index, 1).toString());

					aDAO.deleteCrs(crs_name);
					JOptionPane.showMessageDialog(acd,
							acd.getDtmCrsMgt().getValueAt(index, 1).toString() + " 과목 정보 삭제 성공");

					updateTable();

				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(acd, "SQL 문제가 발생했습니다.");
					e1.printStackTrace();
				} // end catch
				break;
			} // end case
		} // end if

	}

	/**
	 * 우상단 조회버튼 및 삭제버튼 클릭시 사용되는 메서드
	 */
	public void updateTable() {
		acd.getDtmCrsMgt().setRowCount(0);

		int dept_code = 0; // 학과 코드

		String crs_name = ""; // 과목명

		// 학과가 "전체"일 경우
		if (acd.getJcbDept().getSelectedItem().equals("전체")) {

			// 과목명이 존재하는 경우
			if (!acd.getJtfCrsName().getText().isEmpty()) {

				crs_name = acd.getJtfCrsName().getText();

			}

		} else {// 학과가 "전체"가 아닌 모든 경우
			dept_code = acd.getLDept().get((acd.getJcbDept().getSelectedIndex() - 1)).getDept_code();
			// 과목명이 존재하는 경우
			if (!acd.getJtfCrsName().getText().isEmpty()) {

				crs_name = acd.getJtfCrsName().getText();

			}

		}

		try {
			List<CrsVO> listCrsVO = aDAO.slctCrs(dept_code, crs_name);

			for (CrsVO cVO : listCrsVO) {

				Object[] row = { cVO.getDeptName(), cVO.getCourName() };
				acd.getDtmCrsMgt().addRow(row);
			} // end for
			
			if(acd.getDtmCrsMgt().getRowCount() == 0) {
				JOptionPane.showMessageDialog(acd, "검색된 정보가 없습니다.");
				return;
			} // end if
			
		} catch (SQLException se) {
			se.printStackTrace();
		} // end catch

	}

}
