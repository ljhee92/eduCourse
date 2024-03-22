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


public class ProfTestMgtEvent extends WindowAdapter implements ActionListener, ListSelectionListener{
	ProfTestMgtDesign ptmd;
	
	public ProfTestMgtEvent(ProfTestMgtDesign ptmd) {
		this.ptmd = ptmd;
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource() == ptmd.getJbtnTestMdfy()) {
			JOptionPane.showMessageDialog(ptmd, "수정버튼 클릭");
			new ProfTestMdfyDesign(ptmd, null);
		}
		
		if(ae.getSource() == ptmd.getJbtnTestReg()) {
			JOptionPane.showMessageDialog(ptmd, "등록버튼 클릭");
			new ProfTestRegDesign(ptmd, null);
		}
		//////////////////////////활성화 버튼 클릭시///////////////////////////
		if(ae.getSource() == ptmd.getJrbtnEnable()) {
			JOptionPane.showMessageDialog(ptmd, "활성화 버튼 클릭");
			int index = ptmd.getJtbTestMgt().getSelectedRow();
			if (index == -1) {
				JOptionPane.showMessageDialog(ptmd, "과목을 선택해주세요.");
				return;
			} // end if
			String course_name = ptmd.getDtmTestMgt().getValueAt(index, 0).toString();
			
			ProfDAO pDAO = ProfDAO.getInstance();
			try {
				pDAO.updateTestFlag(course_name, "Y");
				
				ptmd.getDtmTestMgt().setRowCount(0); 
				ptmd.slctTestMgt();
				ptmd.getJrbtnEnable().setSelected(false);
			} catch (SQLException e) {
				e.printStackTrace();
			}			
		}
		//////////////////////////비활성화 버튼 클릭시///////////////////////////
		if(ae.getSource() == ptmd.getJrbtnDisable()) {
			JOptionPane.showMessageDialog(ptmd, "비활성화 등록버튼 클릭");
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
	                JOptionPane.showMessageDialog(null, "테이블의 행이 클릭되었습니다." );
	                String check = (String) ptmd.getJtbTestMgt().getValueAt(selectedRow, 1);
	                
	                if (check.equals("Y")) {
						ptmd.getJrbtnEnable().setSelected(true);
					}else {
						ptmd.getJrbtnDisable().setSelected(true);
						
					}
	                
	                
	            }
	        

	        }
	
	}
	
	
}
