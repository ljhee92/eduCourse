package eduCourse_prj.admin.event;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import eduCourse_prj.admin.dao.AdminAcadCalDAO;
import eduCourse_prj.admin.design.AdminAcadCalDesign;


public class AdminAcadCalEvent extends WindowAdapter implements ActionListener{
	
	AdminAcadCalDesign aacd;
	int i=0;
	
	public AdminAcadCalEvent(AdminAcadCalDesign aacd) {
		this.aacd = aacd;
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource() == aacd.getSearchBtn()) { //조회 버튼 클릭
			aacd.calSet();
		}
		
		if(ae.getSource() == aacd.getSaveBtn()) { //저장 버튼 클릭
			int selectedYear = (int)aacd.getYearCb().getSelectedItem();
			int selectedMonth = (int)aacd.getMonthCb().getSelectedItem();			
			int selectedDay = aacd.getDay();
			String dayMonthYear = selectedYear+""+selectedMonth+""+selectedDay;
			String memoText = aacd.getMemoJta().getText();
			AdminAcadCalDAO aacDAO = AdminAcadCalDAO.getInstance();
			try {
				if(aacd.getMemoJta().getText().isEmpty()) {
					JOptionPane.showMessageDialog(aacd, "내용을 입력해주세요");
					return;
				}
				if(aacDAO.selectOneCal(dayMonthYear).equals("")) {
					aacDAO.saveCal(memoText, dayMonthYear);	
					JOptionPane.showMessageDialog(aacd, "저장이 성공으로 끝났습니다");
				}else {
					aacDAO.updateCal(memoText, dayMonthYear);					
					JOptionPane.showMessageDialog(aacd, "저장이 성공으로 끝났습니다");
				}					
			} catch (SQLException e) {
				e.printStackTrace();
			}
					
		}
		if(ae.getSource() == aacd.getDeleteBtn()) {
			AdminAcadCalDAO aacDAO = AdminAcadCalDAO.getInstance();
			int selectedYear = (int)aacd.getYearCb().getSelectedItem();
			int selectedMonth = (int)aacd.getMonthCb().getSelectedItem();			
			int selectedDay = aacd.getDay();
			String dayMonthYear = selectedYear+""+selectedMonth+""+selectedDay;
			
			try {
				if(aacd.getMemoJta().getText().isEmpty()) {
					JOptionPane.showMessageDialog(aacd,"삭제할 내용이 없습니다");
					return;
				}
				 int result = JOptionPane.showConfirmDialog(aacd, "삭제하시겠습니까?", "확인", JOptionPane.YES_NO_OPTION);
			        
			        if (result == JOptionPane.YES_OPTION) {
			            aacDAO.deleteCal(dayMonthYear);
			            JOptionPane.showMessageDialog(aacd, "삭제완료 되었습니다");
			            aacd.getMemoJta().setText("");
			            aacd.calSet();
			        } else if (result == JOptionPane.NO_OPTION) {
			            System.out.println("삭제를 취소합니다.");
			            return;
			        }
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void windowClosing(WindowEvent we) {
		super.windowClosing(we);
	}
	
	
	
}
