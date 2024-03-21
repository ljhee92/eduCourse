package eduCourse_prj.professor.event;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import eduCourse_prj.VO.CrsVO;
import eduCourse_prj.VO.ScoreVO;
import eduCourse_prj.VO.SlctStdVO;
import eduCourse_prj.VO.StdntVO;
import eduCourse_prj.admin.dao.AdminDAO;
import eduCourse_prj.admin.design.AdminStudMgtDesign;
import eduCourse_prj.professor.dao.ScoreDAO;
import eduCourse_prj.professor.design.ProfScoreDesign;
import eduCourse_prj.student.dao.StdntDAO;

public class ProfScoreEvent extends WindowAdapter implements ActionListener {
	ProfScoreDesign psd;
	ScoreDAO sDAO;
	public ProfScoreEvent(ProfScoreDesign psd, String title) {
		this.psd = psd;
	}

	
	


	@Override
	public void windowClosing(WindowEvent we) {
		psd.dispose();
	}

	public void actionPerformed(ActionEvent ae) {

	    if (ae.getSource() == psd.getJbtnSlctTop()) {
	        
	    	psd.getDtmScore().setRowCount(0);
	    	int prof_number = Integer.parseInt(psd.getPhd().getlVO().getId());
			int std_number = 0;
			String crs_code = "";
			
			
			
			//과목이 전체일 경우
			if(psd.getJcbCrs().getSelectedItem().equals("전체")) {
				
				
			}else {
			//과목이 전체가 아닐 경우
				 crs_code = (psd.getLCrs().get((psd.getJcbCrs().getSelectedIndex() - 1)).getCourCode());
			}
			

			
			// 학번 입력 유무 체크
			if (!(psd.getJtfStdNum().getText().isEmpty())) {
				try {

					std_number = Integer.parseInt(psd.getJtfStdNum().getText());
				} catch (Exception e) {
					//e.printStackTrace();
					JOptionPane.showMessageDialog(psd, "숫자만 입력가능 9자리");
					return;
				}
			}
	        

	        
	        

	        
	        
	        
	        
			List<ScoreVO> listScoreVO = null;

	        try {
	        	if (std_number!=0&&crs_code!="") {//둘다 작성
	        		listScoreVO = sDAO.slctOneScore(std_number, crs_code);
				}
	        	else if (std_number!=0&&crs_code=="") {//학번만 입력
	        		listScoreVO = sDAO.slctOneScore(std_number);
				}else if (std_number==0&&crs_code!="") {//과목만 입력
	        		listScoreVO = sDAO.slctOneScore(crs_code);
				}

	            for (ScoreVO sVO : listScoreVO) {

	                Object[] row = { sVO.getStdnt_num(), sVO.getStdnt_name(), sVO.getDept_name(), sVO.getScore()};
	                psd.getDtmScore().addRow(row);
	            } // end for

	        } catch (SQLException e) {
	            e.printStackTrace();
	        } // end catch


	    
	    
	    }//end if
	    
	    if (ae.getSource() == psd.getJbtnCnfrm()) {
	        JOptionPane.showMessageDialog(psd, "확인버튼 클릭");
	        
	        psd.dispose();

	    }   





	}


}
