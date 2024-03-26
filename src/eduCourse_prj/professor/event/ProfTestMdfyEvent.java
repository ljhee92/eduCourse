package eduCourse_prj.professor.event;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import eduCourse_prj.VO.DeptVO;
import eduCourse_prj.VO.TestQustVO;
import eduCourse_prj.admin.dao.AdminDAO;
import eduCourse_prj.admin.design.AdminDeptMgtRegDesign;
import eduCourse_prj.professor.dao.TestDAO;
import eduCourse_prj.professor.design.ProfTestMdfyDesign;
import eduCourse_prj.professor.design.ProfTestRegDesign;

public class ProfTestMdfyEvent extends WindowAdapter implements ActionListener {
	ProfTestMdfyDesign ptmd;

	public ProfTestMdfyEvent(ProfTestMdfyDesign ptmd) {
		this.ptmd = ptmd;

	}
	@Override
	public void actionPerformed(ActionEvent ae) {
		// 수정 버튼 클릭 시
		if (ae.getSource() == ptmd.getRegisterButton()) {
			TestDAO tDAO = TestDAO.getInstance();
			TestQustVO tqVO;
			
			int questionNumber = Integer.parseInt(ptmd.getTestNumberComboBox().getSelectedItem().toString());
			System.out.println(questionNumber);
			
			String multipleChoiceOne = ptmd.getMultipleChoiceOneTextField().getText();
			if(multipleChoiceOne.isEmpty()) {
			    JOptionPane.showMessageDialog(ptmd, "선택(1)은 필수 입력 사항입니다.");
			    return;
			}
			String multipleChoiceTwo = ptmd.getMultipleChoiceTwoTextField().getText();
			if(multipleChoiceTwo.isEmpty()) {
				JOptionPane.showMessageDialog(ptmd, "선택(2)은 필수 입력 사항입니다.");
				return;
			}
			String multipleChoiceThree = ptmd.getMultipleChoiceThreeTextField().getText();
			if(multipleChoiceThree.isEmpty()) {
				JOptionPane.showMessageDialog(ptmd, "선택(3)은 필수 입력 사항입니다.");
				return;
			}
			String multipleChoiceFour = ptmd.getMultipleChoiceFourTextField().getText();
			if(multipleChoiceFour.isEmpty()) {
				JOptionPane.showMessageDialog(ptmd, "선택(4)은 필수 입력 사항입니다.");
				return;
			}
			
			

			String answerText = ptmd.getAnswerTextField().getText();
			if(answerText.isEmpty()) {
			    JOptionPane.showMessageDialog(ptmd, "정답은 필수 입력 사항입니다.");
			    return;
			}

			try {
			    int answer = Integer.parseInt(answerText);
			    //capacity 유효성 검사
			    if (answer <= 0 || answer >4) {
			        JOptionPane.showMessageDialog(ptmd, "정답은 (1~4)만 입력 가능합니다.");
			        return;
			    }
			    
			    tqVO = new TestQustVO(questionNumber, "내용", answer,100403140, "M0001");
			    tDAO.insertTest(tqVO);
			}catch (SQLException se) {
				JOptionPane.showMessageDialog(ptmd, "SQL예외가 발생했습니다.");
				se.printStackTrace();
			}

//			System.out.println(tDAO.selectOneDept());

		}
		
		// 취소 버튼 클릭 시
		if (ae.getSource() == ptmd.getCancelButton()) {

			ptmd.dispose();
		}

	}
	

}

