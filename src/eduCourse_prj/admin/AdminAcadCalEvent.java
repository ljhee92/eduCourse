package eduCourse_prj.admin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class AdminAcadCalEvent extends WindowAdapter implements ActionListener{
	
	AdminAcadCalDesign aacd;
	
	public AdminAcadCalEvent(AdminAcadCalDesign aacd) {
		this.aacd = aacd;
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource() == aacd.getSearchBtn()) {
			aacd.calSet();
		}
		
		if(ae.getSource() == aacd.getSaveBtn()) {
			int selectedYear = (int)aacd.getYearCb().getSelectedItem();
			int selectedMonth = (int)aacd.getMonthCb().getSelectedItem();			
			int selectedDay = aacd.getDay();
			System.out.println(selectedDay);
			String memoText = aacd.getMemoJtf().getText();
			
//			CalenderVO cVO = new CalenderVO(selectedYear, selectedMonth, selectedDay, memoText);
			String memoMapKey = selectedYear+""+selectedMonth+""+selectedDay;
			
			aacd.getMemoMap().put(memoMapKey, memoText);
			aacd.getMemoJtf().setText("");	
			
			for(String str : aacd.getMemoMap().values()) {
				System.out.println(str);
			}
			for(String str : aacd.getMemoMap().keySet()) {
				System.out.println(str);
			}
		}
		if(ae.getSource() == aacd.getDeleteBtn()) {
			
		}
	}

	@Override
	public void windowClosing(WindowEvent we) {
		super.windowClosing(we);
	}
	
	
	
}
