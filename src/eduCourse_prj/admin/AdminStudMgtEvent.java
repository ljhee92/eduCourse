package eduCourse_prj.admin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;

public class AdminStudMgtEvent extends WindowAdapter implements ActionListener{
	AdminStudMgtDesign asmd;
	AdminStudMgtEvent(AdminStudMgtDesign asmd){
		this.asmd = asmd;
		
		
	}
	
	
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == asmd.getJbtTest()) {
			System.out.println("test");
			
			
		}
		
		
	}
	
}
