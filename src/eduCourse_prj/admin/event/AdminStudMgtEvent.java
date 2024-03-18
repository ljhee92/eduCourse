package eduCourse_prj.admin.event;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;

import eduCourse_prj.admin.design.AdminStudMgtDesign;

public class AdminStudMgtEvent extends WindowAdapter implements ActionListener{
	AdminStudMgtDesign asmd;
	public AdminStudMgtEvent(AdminStudMgtDesign asmd){
		this.asmd = asmd;
		
		
	}
	
	
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == asmd.getJbtTest()) {
			System.out.println("test");
			
			
		}
		
		
	}
	
}
