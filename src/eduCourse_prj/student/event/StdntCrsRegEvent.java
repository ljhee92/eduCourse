package eduCourse_prj.student.event;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
			
			addRegister();
			modifyCapacited();
			JOptionPane.showMessageDialog(scrd, "수강신청이 완료되었습니다.");
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
		try {
			int index = scrd.getJtbCrsCart().getSelectedRow();
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
	
	/**
	 * 수강바구니에 담긴 과목을 DB의 수강 테이블에 저장하는 method
	 */
	public void addRegister() {
		try {
			CrsRegDAO crDAO = CrsRegDAO.getInstance();
			
			int index = scrd.getJtbCrsCart().getRowCount();
			
			int stdnt_number = Integer.parseInt(scrd.getShd().getlVO().getId());
			String crs_code = "";
			
			List<RegVO> listRVO = new ArrayList<RegVO>();
			RegVO rVO = null;
			
			for(int i = 0; i < index; i++) {
				crs_code = scrd.getDtmCrsCart().getValueAt(i, 2).toString();
				rVO = new RegVO(stdnt_number, crs_code);
				listRVO.add(rVO);
			} // end for
			
			crDAO.insertReg(listRVO);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(scrd, "SQL 문제가 발생했습니다.");
			e.printStackTrace();
		} // end catch
	} // addRegister
	
	
	/**
	 * 수강신청한 과목의 수강인원을 증가시키는 method
	 */
	public void modifyCapacited() {
		try {
			CrsRegDAO crDAO = CrsRegDAO.getInstance();
			
			int index = scrd.getJtbCrsCart().getRowCount();
			
			String crs_code = "";
			
			List<LectureVO> listLtVO = new ArrayList<LectureVO>();
			LectureVO ltVO = null;
			
			for(int i = 0; i < index; i++) {
				crs_code = scrd.getDtmCrsCart().getValueAt(i, 2).toString();
				ltVO = new LectureVO(crs_code);
				listLtVO.add(ltVO);
			} // end for
			
			crDAO.updateCapacited(listLtVO);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(scrd, "SQL 문제가 발생했습니다.");
			e.printStackTrace();
		} // end catch
	} // modifyCapacited
	
} // class