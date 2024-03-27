package eduCourse_prj.student.event;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import eduCourse_prj.VO.CrsRegVO;
import eduCourse_prj.VO.LectureVO;
import eduCourse_prj.VO.RegVO;
import eduCourse_prj.student.dao.CrsRegDAO;
import eduCourse_prj.student.design.StdntCrsRegDesign;

public class StdntCrsRegEvent extends WindowAdapter implements ActionListener {

	private StdntCrsRegDesign scrd;

	public StdntCrsRegEvent(StdntCrsRegDesign scrd) {
		this.scrd = scrd;
	} // StdntCrsRegEvent

	@Override
	public void actionPerformed(ActionEvent e) {
		// 담기 버튼 클릭
		if (e.getSource() == scrd.getJbtnAdd()) {
			int index = scrd.getJtbCrsReg().getSelectedRow();
			if (index == -1) {
				JOptionPane.showMessageDialog(scrd, "수강바구니에 담을 과목을 선택해주세요.");
				return;
			} // end if

			addCart();
		} // end if

		// 취소 버튼 클릭
		if (e.getSource() == scrd.getJbtnCancel()) {
			int index = scrd.getJtbCrsCart().getSelectedRow();
			if (index == -1) {
				JOptionPane.showMessageDialog(scrd, "수강바구니에서 취소할 과목을 선택해주세요.");
				return;
			} // end if

			removeCart();
		} // end if

		// 최종신청 버튼 클릭
		if (e.getSource() == scrd.getJbtnReg()) {

			if (scrd.getDtmCrsCart().getRowCount() == 0) {
				JOptionPane.showMessageDialog(scrd, "수강할 과목을 수강바구니에 담은 후 최종 신청버튼 클릭해주세요");
				return;

			}

			StringBuilder sb = new StringBuilder();
			sb.append("수강신청 결과\t\n");

			try {
				CrsRegDAO crDAO = CrsRegDAO.getInstance();
				int index = scrd.getJtbCrsCart().getRowCount();

				String crs_code = "";

				LectureVO lVO = null;

				for (int i = 0; i < index; i++) {
					crs_code = scrd.getDtmCrsCart().getValueAt(0, 2).toString();

					lVO = new LectureVO(crs_code);

					// true일시 수강신청 가능
					boolean check = crDAO.checkCapacited(lVO);
					if (check == false) {
						// 정원보다 수강인원중인 숫자가 많을경우 -> 수강신청 불가
						// 수강신청한 과목이 인원초과로 등록 실패될 경우
						// 장바구니에 있던 선택과목 되돌리기.
						sb.append(scrd.getDtmCrsCart().getValueAt(0, 1) + "과목 수강신청 실패 : 정원초과\n");
						removeCartOne();
					} else {// 정원보다 수강인원중인 숫자가 적을경우 -> 수강신청 가능
							// 수강신청한 과목이 성공적으로 등록된 경우
							// 장바구니에 있던 선택과목 없애기.
						addRegisterOne();
						modifyCapacitedOne();

						sb.append(scrd.getDtmCrsCart().getValueAt(0, 1) + "과목 수강신청 성공\n");
						scrd.getDtmCrsCart().removeRow(0);
					}

				} // end for
				String result = sb.toString();
				JOptionPane.showMessageDialog(scrd, result);
			} catch (SQLException e1) {
				e1.printStackTrace();
			} // end catch
		} // end if
	} // actionPerformed

	/**
	 * Desc: 선택한 과목이 수강바구니에 추가되고, 수강신청 가능 목록에서는 사라지는 method
	 */
	public void addCart() {
		try {
			int index = scrd.getJtbCrsReg().getSelectedRow();
			String crs_code = scrd.getDtmCrsReg().getValueAt(index, 2).toString();

			CrsRegDAO crDAO = CrsRegDAO.getInstance();
			CrsRegVO crVO = crDAO.slctOneCrsReg(crs_code);

			Object[] selectedRow = { crVO.getDept_name(), crVO.getCourse_name(), crVO.getCourse_code(),
					crVO.getLect_room(), crVO.getCapacity(), crVO.getCredit_hours() };
			scrd.getDtmCrsCart().addRow(selectedRow);

			int credit_hours = Integer.parseInt(scrd.getDtmCrsReg().getValueAt(index, 5).toString());
			int sum_credit_hours = 0;

			for (int i = 0; i <= index; i++) {
				sum_credit_hours = Integer.parseInt(scrd.getJlAllCreditHour().getText()) + credit_hours;
			} // end for

			scrd.getJlAllCreditHour().setText(Integer.toString(sum_credit_hours));
			scrd.getDtmCrsReg().removeRow(index);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(scrd, "SQL 문제가 발생했습니다.");
			e.printStackTrace();
		} // end catch
	} // addCart

	/**
	 * Desc: 선택한 과목이 수강바구니에서 제거되고, 수강신청 가능 목록에 추가되는 method
	 */
	public void removeCart() {
		try {
			int index = scrd.getJtbCrsCart().getSelectedRow();
			String crs_code = scrd.getDtmCrsCart().getValueAt(index, 2).toString();

			CrsRegDAO crDAO = CrsRegDAO.getInstance();
			CrsRegVO crVO = crDAO.slctOneCrsReg(crs_code);

			Object[] selectedRow = { crVO.getDept_name(), crVO.getCourse_name(), crVO.getCourse_code(),
					crVO.getLect_room(), crVO.getCapacity(), crVO.getCredit_hours() };
			scrd.getDtmCrsReg().addRow(selectedRow);

			int credit_hours = Integer.parseInt(scrd.getDtmCrsCart().getValueAt(index, 5).toString());
			int sum_credit_hours = 0;

			for (int i = 0; i <= index; i++) {
				sum_credit_hours = Integer.parseInt(scrd.getJlAllCreditHour().getText()) - credit_hours;
			} // end for

			scrd.getJlAllCreditHour().setText(Integer.toString(sum_credit_hours));
			scrd.getDtmCrsCart().removeRow(index);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(scrd, "SQL 문제가 발생했습니다.");
			e.printStackTrace();
		} // end catch
	} // removeCart

	public void removeCartOne() {
		try {
			CrsRegDAO crDAO = CrsRegDAO.getInstance();
			int t = 0;
			String crs_code = scrd.getDtmCrsCart().getValueAt(t, 2).toString();
			t++;

			CrsRegVO crVO = crDAO.slctOneCrsReg(crs_code);
			Object[] selectedRow = { crVO.getDept_name(), crVO.getCourse_name(), crVO.getCourse_code(),
					crVO.getLect_room(), crVO.getCapacity(), crVO.getCredit_hours() };
			scrd.getDtmCrsReg().addRow(selectedRow);

			int credit_hours = Integer.parseInt(scrd.getDtmCrsCart().getValueAt(0, 5).toString());
			int sum_credit_hours = 0;

			for (int i = 0; i <= 0; i++) {
				sum_credit_hours = Integer.parseInt(scrd.getJlAllCreditHour().getText()) - credit_hours;
			} // end for

			scrd.getJlAllCreditHour().setText(Integer.toString(sum_credit_hours));
			scrd.getDtmCrsCart().removeRow(0);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(scrd, "SQL 문제가 발생했습니다.");
			e.printStackTrace();
		} // end catch
	} // removeCart

	/**
	 * 수강바구니에 담긴 한과목을 DB의 수강 테이블에 저장하는 method
	 */
	public void addRegisterOne() {
		try {
			CrsRegDAO crDAO = CrsRegDAO.getInstance();
			int t = 0;
			int stdnt_number = Integer.parseInt(scrd.getShd().getlVO().getId());
			String crs_code = scrd.getDtmCrsCart().getValueAt(t, 2).toString();
			t++;

			RegVO rVO = new RegVO(stdnt_number, crs_code);
			crDAO.insertReg(rVO);

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(scrd, "SQL 문제가 발생했습니다.");
			e.printStackTrace();
		} // end catch
	} // addRegister

	/**
	 * 수강신청한 한과목의 수강인원을 증가시키는 method
	 */
	public void modifyCapacitedOne() {
		try {
			CrsRegDAO crDAO = CrsRegDAO.getInstance();

			String crs_code = "";

			LectureVO ltVO = null;

			if (scrd.getDtmCrsCart().getRowCount() > 0) {
				crs_code = scrd.getDtmCrsCart().getValueAt(0, 2).toString();
			}
			ltVO = new LectureVO(crs_code);
			crDAO.updateCapacited(ltVO);
			// } // end for

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(scrd, "SQL 문제가 발생했습니다.");
			e.printStackTrace();
		} // end catch
	} // modifyCapacited

} // class