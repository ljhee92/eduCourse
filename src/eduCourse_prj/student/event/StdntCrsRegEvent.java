package eduCourse_prj.student.event;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import eduCourse_prj.VO.CrsRegVO;
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
		if(e.getSource() == scrd.getJbtnAdd()) {
			int index = scrd.getJtbCrsReg().getSelectedRow();
			if(index == -1) {
				JOptionPane.showMessageDialog(scrd, "수강바구니에 담을 과목을 선택해주세요.");
				return;
			} // end if
			
			addCart();
		} // end if
		
		// 취소 버튼 클릭
		if(e.getSource() == scrd.getJbtnCancel()) {
			int index = scrd.getJtbCrsCart().getSelectedRow();
			if(index == -1) {
				JOptionPane.showMessageDialog(scrd, "수강바구니에서 취소할 과목을 선택해주세요.");
				return;
			} // end if
			
			removeCart();
		} // end if
		
		// 최종신청 버튼 클릭
		if(e.getSource() == scrd.getJbtnReg()) {
			JOptionPane.showMessageDialog(scrd, "최종신청 버튼 클릭");
		} // end if
	} // actionPerformed
	
	/**
	 * Desc: 선택한 과목이 수강바구니에 추가되고, 수강신청 가능 목록에서는 사라지는 method
	 */
	public void addCart() {
		int index = scrd.getJtbCrsReg().getSelectedRow();
		
		try {
			String crs_code = scrd.getDtmCrsReg().getValueAt(index, 2).toString();
			
			CrsRegDAO crDAO = CrsRegDAO.getInstance();
			CrsRegVO crVO = crDAO.slctOneCrsReg(crs_code);
			
			Object[] selectedRow = {crVO.getDept_name(), crVO.getCourse_name(), crVO.getCourse_code(), crVO.getLect_room(), crVO.getCapacity(), crVO.getCredit_hours()};
			scrd.getDtmCrsCart().addRow(selectedRow);
			
			int credit_hours = Integer.parseInt(scrd.getDtmCrsReg().getValueAt(index, 5).toString());
			int sum_credit_hours = 0;
			
			for(int i = 0; i <= index; i++) {
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
		int index = scrd.getJtbCrsCart().getSelectedRow();
		
		try {
			String crs_code = scrd.getDtmCrsCart().getValueAt(index, 2).toString();
			
			CrsRegDAO crDAO = CrsRegDAO.getInstance();
			CrsRegVO crVO = crDAO.slctOneCrsReg(crs_code);
			
			Object[] selectedRow = {crVO.getDept_name(), crVO.getCourse_name(), crVO.getCourse_code(), crVO.getLect_room(), crVO.getCapacity(), crVO.getCredit_hours()};
			scrd.getDtmCrsReg().addRow(selectedRow);
			
			int credit_hours = Integer.parseInt(scrd.getDtmCrsCart().getValueAt(index, 5).toString());
			int sum_credit_hours = 0;
			
			for(int i = 0; i <= index; i++) {
				sum_credit_hours = Integer.parseInt(scrd.getJlAllCreditHour().getText()) - credit_hours;
			} // end for
			
			scrd.getJlAllCreditHour().setText(Integer.toString(sum_credit_hours));
			scrd.getDtmCrsCart().removeRow(index);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(scrd, "SQL 문제가 발생했습니다.");
			e.printStackTrace();
		} // end catch
	} // removeCart
	
} // class