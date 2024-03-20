package eduCourse_prj.professor.event;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;

import eduCourse_prj.VO.ProfLectStudVO;
import eduCourse_prj.VO.SlctStdVO;
import eduCourse_prj.professor.dao.ProfDAO;
import eduCourse_prj.professor.design.ProfStudMgtDesign;

public class ProfStudMgtEvent extends WindowAdapter implements ActionListener {

	private ProfStudMgtDesign psmd;
	ProfDAO pDAO = ProfDAO.getInstance();

	public ProfStudMgtEvent(ProfStudMgtDesign psmd) {

		this.psmd = psmd;

	}

	@Override
	public void windowClosing(WindowEvent e) {

		psmd.dispose();

	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == psmd.getJbtnSlctTop()) {
			
			JOptionPane.showMessageDialog(psmd, "상단 조회버튼 클릭.");
			psmd.getDtmStdMgt().setRowCount(0);

			int prof_number = Integer.parseInt(psmd.getPhd().getlVO().getId());
			int std_number = 0;
			String crs_code = "";
			
			
			
			//과목이 전체일 경우
			if(psmd.getJcbCrs().getSelectedItem().equals("전체")) {
				
				
			}else {
			//과목이 전체가 아닐 경우
				 crs_code = (psmd.getLCrs().get((psmd.getJcbCrs().getSelectedIndex() - 1)).getCourCode());
			}
			

			
			// 학번 입력 유무 체크
			if (!(psmd.getJtfStdNum().getText().isEmpty())) {
				try {

					std_number = Integer.parseInt(psmd.getJtfStdNum().getText());
				} catch (Exception e) {
					//e.printStackTrace();
					JOptionPane.showMessageDialog(psmd, "숫자만 입력가능 9자리");
					return;
				}
			}
			
			


//			System.out.println("prof_number : " + prof_number);
//			System.out.println("crs_number : " + crs_code);
//			System.out.println("std_number : " + std_number);

			
			
			try {
			List<ProfLectStudVO> listplsVO	=	pDAO.slctProfStud(prof_number,crs_code,std_number);
			
			for (ProfLectStudVO plsVO : listplsVO) {

				Object[] row = { plsVO.getDept_name(), plsVO.getCourse_name(), plsVO.getStd_number(), plsVO.getStd_name() };
				psmd.getDtmStdMgt().addRow(row);
			} // end for

			
			
			
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				
				e.printStackTrace();
			}
			
			
			
		}

		if (ae.getSource() == psmd.getJbtnSlct()) {
			JOptionPane.showMessageDialog(psmd, "하단 조회버튼 클릭.");

		}

	}

}
