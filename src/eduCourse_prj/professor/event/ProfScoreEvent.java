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
import eduCourse_prj.VO.SlctStdVO;
import eduCourse_prj.VO.StdntVO;
import eduCourse_prj.admin.dao.AdminDAO;
import eduCourse_prj.admin.design.AdminStudMgtDesign;
import eduCourse_prj.professor.design.ProfScoreDesign;
import eduCourse_prj.student.dao.StdntDAO;

public class ProfScoreEvent extends WindowAdapter implements ActionListener {
	ProfScoreDesign psd;
	AdminDAO aDAO = AdminDAO.getInstance();
	
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
	        //JOptionPane.showMessageDialog(psd, "상단 조회버튼 클릭");

	        int dept_code = 0; // 학과 코드
	        String crs_code = ""; // 과목 코드
	        int std_num = 0; // 학번

	        // 학과가 "전체"일 경우
	        if (psd.getJcbDept().getSelectedItem().equals("전체")) {

	            // 과목이 "전체"일 경우
	            if (psd.getJcbCrs().getSelectedItem().equals("전체")) {

	            } else {// 과목이 "전체"가 아닌 모든 경우
	                crs_code = psd.getLCrs().get(psd.getJcbCrs().getSelectedIndex() - 1).getCourCode();

	            }

	        } else {// 학과가 "전체"가 아닌 모든 경우
	            dept_code = psd.getLDept().get((psd.getJcbDept().getSelectedIndex() - 1)).getDept_code();

	            // 과목이 "전체"일 경우
	            if (psd.getJcbCrs().getSelectedItem().equals("전체")) {

	            } else {// 과목이 "전체"가 아닌 모든 경우 //문제 발견 -> 

	                crs_code = (psd.getLCrs().get(psd.getJcbCrs().getSelectedIndex() - 1)).getCourCode();


	            }



	        }
	        
	        // 학번 입력 유무 체크
	        if (!(psd.getJtfStdNum().getText().isEmpty())) {
	            try {

	                std_num = Integer.parseInt(psd.getJtfStdNum().getText());
	            } catch (Exception e) {
	                e.printStackTrace();
	                JOptionPane.showMessageDialog(psd, "숫자만 입력가능 9자리");
	                return;
	            }
	        }
	        
	        

	        
	        
	        
	        
	        @SuppressWarnings("unused")
	        List<SlctStdVO> lSlctstdVO;

	        try {
	            List<SlctStdVO> listSlctStdVO = aDAO.slctStd(dept_code , crs_code, std_num);

	            for (SlctStdVO ssVO : listSlctStdVO) {

	                Object[] row = { ssVO.getDept_name(), ssVO.getCrs_name(), ssVO.getStd_num(), ssVO.getStd_name() };
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



	    if (ae.getSource() == psd.getJcbDept()) {
	        JOptionPane.showMessageDialog(psd, "과목 콤보박스 클릭");
	        int dept_code = 0;

	        // 학과 콤보박스(jcbDept)를 클릭시 선택된 아이템의 인덱스와 동일한 인덱스를 lDept(DeptVO가 저장되어있음)에서 선택하여
	        // DeptVo객체에서 dept_code를 얻어냄.

	        if (psd.getJcbDept().getSelectedItem().equals("전체")) {

	            // 모든 과목 정보 가져오기
	            
	            List<CrsVO> listCrsVO = new ArrayList<CrsVO>();

	            try {
	                listCrsVO = aDAO.slctAllCrs();

	                // 과목명만 저장하는 리스트에 과목명 저장
	                for (CrsVO cVO : listCrsVO) {
	                    psd.getJcbCrs().addItem(cVO.getCourName());
	                    psd.getLCrs().add(cVO);

	                }
	            } catch (SQLException e) {

	                e.printStackTrace();
	            }

	        } else
	            
	            dept_code = psd.getLDept().get(psd.getJcbDept().getSelectedIndex() - 1).getDept_code();


	        List<CrsVO> listCrsVO;
	        try {
	        
	            listCrsVO = aDAO.slctDeptCrs(dept_code);



	            for (CrsVO cVO : listCrsVO) {

	                psd.getJcbCrs().addItem(cVO.getCourName());
	                psd.getLCrs().add(cVO);
	                

	            }
	            

	        } catch (SQLException e) {
	            JOptionPane.showConfirmDialog(psd, "에러발생");
	            e.printStackTrace();
	        }

	    }

	}


}
