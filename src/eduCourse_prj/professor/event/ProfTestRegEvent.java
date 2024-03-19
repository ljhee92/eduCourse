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
import eduCourse_prj.professor.design.ProfTestRegDesign;

public class ProfTestRegEvent extends WindowAdapter implements ActionListener {
	ProfTestRegDesign ptrd;

	public ProfTestRegEvent(ProfTestRegDesign ptrd) {
		this.ptrd = ptrd;

	}
	@Override
	public void actionPerformed(ActionEvent ae) {
		// TODO Auto-generated method stub
		if (ae.getSource() == ptrd.getRegisterButton()) {
			TestDAO tDAO = TestDAO.getInstance();
			JOptionPane.showMessageDialog(ptrd, "출제버튼 클릭");
			TestQustVO tqVO;
			
			int questionNumber = Integer.parseInt(ptrd.getTestNumberComboBox().getSelectedItem().toString());
			System.out.println(questionNumber);
			
			String multipleChoiceOne = ptrd.getMultipleChoiceOneTextField().getText();
			if(multipleChoiceOne.isEmpty()) {
			    JOptionPane.showMessageDialog(ptrd, "선택(1)은 필수 입력 사항입니다.");
			    return;
			}
			String multipleChoiceTwo = ptrd.getMultipleChoiceTwoTextField().getText();
			if(multipleChoiceTwo.isEmpty()) {
				JOptionPane.showMessageDialog(ptrd, "선택(2)은 필수 입력 사항입니다.");
				return;
			}
			String multipleChoiceThree = ptrd.getMultipleChoiceThreeTextField().getText();
			if(multipleChoiceThree.isEmpty()) {
				JOptionPane.showMessageDialog(ptrd, "선택(3)은 필수 입력 사항입니다.");
				return;
			}
			String multipleChoiceFour = ptrd.getMultipleChoiceFourTextField().getText();
			if(multipleChoiceFour.isEmpty()) {
				JOptionPane.showMessageDialog(ptrd, "선택(4)은 필수 입력 사항입니다.");
				return;
			}
			String content = ptrd.getTestQuestionContentTextArea().getText().toString() + "\n\n" + ptrd.getMultipleChoiceOneTextField().getText() + "\t" + ptrd.getMultipleChoiceTwoTextField().getText()
			+ "\t" + ptrd.getMultipleChoiceThreeTextField().getText()+ "\t" + ptrd.getMultipleChoiceFourTextField().getText();
			System.out.println(content);
			

			String answerText = ptrd.getAnswerTextField().getText();
			if(answerText.isEmpty()) {
			    JOptionPane.showMessageDialog(ptrd, "정답은 필수 입력 사항입니다.");
			    return;
			}

			try {
			    int answer = Integer.parseInt(answerText);
			    //capacity 유효성 검사
			    if (answer <= 0 || answer >4) {
			        JOptionPane.showMessageDialog(ptrd, "정답은 (1~4)만 입력 가능합니다.");
			        return;
			    }
			    
			    
			    tqVO = new TestQustVO(questionNumber, content, answer,100403140, "M0001");
			    tDAO.insertTest(tqVO);
			}catch (SQLException se) {
				JOptionPane.showMessageDialog(ptrd, "SQL예외가 발생했습니다.");
				se.printStackTrace();
			}



		}
		if (ae.getSource() == ptrd.getCancelButton()) {

			JOptionPane.showMessageDialog(ptrd, "취소버튼 클릭");
			ptrd.dispose();
		}

	}
	

}

