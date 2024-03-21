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
	ScoreDAO sDAO = ScoreDAO.getInstance();
	public ProfScoreEvent(ProfScoreDesign psd, String title) {
		this.psd = psd;
	}

	
	


	@Override
	public void windowClosing(WindowEvent we) {
		psd.dispose();
	}

	public void actionPerformed(ActionEvent ae) {
		String prof_id = psd.getPhd().getlVO().getId();
		
	    if (ae.getSource() == psd.getJbtnSlctTop()) {
	    	JOptionPane.showMessageDialog(psd, "상단 조회버튼 클릭");
	        psd.getDtmScore().setRowCount(0); //dtmScore 초기화
	        
	        int prof_number = Integer.parseInt(psd.getPhd().getlVO().getId());
	        int std_number = 0;
	        String crs_code = "";

	        // 과목이 전체가 아닐 경우
	        if (!psd.getJcbCrs().getSelectedItem().equals("전체")) {
	        	//System.out.println("일부선택");
	            crs_code = psd.getLCrs().get(psd.getJcbCrs().getSelectedIndex() - 1).getCourCode();
	           // System.out.println("crs_code : " +crs_code);
	        }

	        // 학번 입력 유무 체크
	        if (!psd.getJtfStdNum().getText().isEmpty()) {
	        	//System.out.println("학번입력되었음");
	            try {
	                std_number = Integer.parseInt(psd.getJtfStdNum().getText());
	            } catch (NumberFormatException e) {
	                JOptionPane.showMessageDialog(psd, "숫자만 입력 가능합니다.");
	                return;
	            }
	        }

	       // System.out.println("prof_number : "+prof_number );
	       // System.out.println("std_number  : "+std_number  );
	       // System.out.println("crs_code  : "+crs_code  );
	        
	        
	        
	        
	        List<ScoreVO> listScoreVO = new ArrayList<ScoreVO>();

	        try {
	            if (std_number != 0 && !crs_code.equals("")) { //학번, 과목으로 찾음
	                listScoreVO = sDAO.slctOneScore(std_number, crs_code, prof_id);
	            } else if (std_number != 0 && crs_code.equals("")) { // 학번으로 찾음
	                listScoreVO = sDAO.slctOneScore(std_number, prof_id);
	            } else if (std_number == 0 && !crs_code.equals("")) { // 과목으로 찾음
	                listScoreVO = sDAO.slctOneScore(crs_code, prof_id);
	            } else if (std_number == 0 && crs_code.equals("")) { // 전체 검색
	                listScoreVO = sDAO.slctAllScore(prof_id);
	            }
	            
//	        	listScoreVO = sDAO.slctAllScore();

	            for (ScoreVO sVO : listScoreVO) {
	                Object[] row = {sVO.getStd_num(), sVO.getStd_name(), sVO.getDept_name(), sVO.getScore()};
	                psd.getDtmScore().addRow(row);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	            JOptionPane.showMessageDialog(psd, "데이터베이스 조회 중 오류가 발생했습니다.");
	        }
	        
	        
	        
	        
	    }
	    
	    if (ae.getSource() == psd.getJbtnCnfrm()) {
	        JOptionPane.showMessageDialog(psd, "확인버튼 클릭");
	        
	        psd.dispose();

	    }   


	}


}
