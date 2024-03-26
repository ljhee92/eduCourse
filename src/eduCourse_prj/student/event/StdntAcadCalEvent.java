package eduCourse_prj.student.event;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import eduCourse_prj.admin.dao.AdminAcadCalDAO;
import eduCourse_prj.admin.design.AdminAcadCalDesign;
import eduCourse_prj.student.design.StdntAcadCalDesign;


public class StdntAcadCalEvent extends WindowAdapter implements ActionListener{
	
	StdntAcadCalDesign sacd;
	int i=0;
	
	public StdntAcadCalEvent(StdntAcadCalDesign sacd) {
		this.sacd = sacd;
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource() == sacd.getSearchBtn()) { //조회 버튼 클릭
			try {
				sacd.calSet();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		if(ae.getSource() == sacd.getJbtnCnfrm()) { //확인 버튼 클릭
	
			sacd.dispose();
		}
		
		

	}

	@Override
	public void windowClosing(WindowEvent we) {
		super.windowClosing(we);
	}
	
	
	
}
