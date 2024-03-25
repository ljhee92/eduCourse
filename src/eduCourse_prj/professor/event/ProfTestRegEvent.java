package eduCourse_prj.professor.event;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import eduCourse_prj.VO.CrsVO;
import eduCourse_prj.VO.DeptVO;
import eduCourse_prj.VO.TestQustVO;
import eduCourse_prj.admin.dao.AdminDAO;
import eduCourse_prj.admin.design.AdminDeptMgtRegDesign;
import eduCourse_prj.professor.dao.CrsMgtRegDAO;
import eduCourse_prj.professor.dao.ProfDAO;
import eduCourse_prj.professor.dao.TestDAO;
import eduCourse_prj.professor.design.ProfTestMgtDesign;
import eduCourse_prj.professor.design.ProfTestRegDesign;
import oracle.security.o3logon.a;

public class ProfTestRegEvent extends WindowAdapter implements ActionListener {
	ProfTestRegDesign ptrd;
	ProfTestMgtDesign ptmd;
	CrsVO cVO = null;
	ProfDAO pDAO = ProfDAO.getInstance();

	public ProfTestRegEvent(ProfTestRegDesign ptrd,ProfTestMgtDesign ptmd) {
		this.ptrd = ptrd;
		this.ptmd = ptmd;

	}
	@Override
	public void actionPerformed(ActionEvent ae) {
		// TODO Auto-generated method stub
		if (ae.getSource() == ptrd.getRegisterButton()) {
			TestDAO tDAO = TestDAO.getInstance();
			CrsMgtRegDAO cmrDAO = CrsMgtRegDAO.getInstance();
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
				List<Integer> courNum;
			    int answer = Integer.parseInt(answerText);
			    //capacity 유효성 검사
			    if (answer <= 0 || answer >4) {
			        JOptionPane.showMessageDialog(ptrd, "정답은 (1~4)만 입력 가능합니다.");
			        return;
			    }
			    
			    int seletedRow = ptrd.getPtmd().getJtbTestMgt().getSelectedRow();
				String seletedValue = (String) ptrd.getPtmd().getJtbTestMgt().getValueAt(seletedRow,0);//선택된 과목코드 
				
				System.out.println(seletedValue);
				cVO = cmrDAO.slctOneCrsCode(seletedValue);
				
				int profId = Integer.parseInt(ptrd.getPtmd().getPhd().getlVO().getId());
				String courCode = cVO.getCourCode();
				
//				System.out.println("questionNumber : " + questionNumber + " content : " + content 
//						+ " answer : " + answer + " 교수 이름 : " + Integer.parseInt(ptrd.getPtmd().getPhd().getlVO().getId()) 
//						+ " 코스 코드  : " + cVO.getCourCode());
			    tqVO = new TestQustVO(questionNumber, content, answer, profId, courCode);
			    tDAO.insertTest(tqVO);
			    
			    
			  //등록이 완료되면 등록이 안된 문제의 첫 값으로 설정
		        courNum = tDAO.selectValidTestNumber(seletedValue);
		      
		        if (courNum.size()==10) {
					JOptionPane.showMessageDialog(ptrd, "출제가 완료되었습니다");
					ptrd.dispose();
					ptmd.getDtmTestMgt().setRowCount(0);
					ptmd.slctTestMgt();
					
				}else {
			        ptrd.getTestNumberComboBox().removeItem(questionNumber);
			        ptrd.getTestNumberComboBox().removeAllItems(); // 콤보 박스의 모든 아이템 삭제
			        for (int i = 1; i <= 10; i++) {
					    boolean skipNumber = false;
					    for (Integer num : courNum) {
					        if (i == num) {
					            skipNumber = true;
					            break;
					        }
					    }
					    if (!skipNumber) {
					    	ptrd.getTestNumberComboBox().addItem(String.valueOf(i)); // 새로운 값들 추가
					    }
					}
			        
			        ptrd.getTestNumberComboBox().setSelectedIndex(0); // 첫 번째 값 선택
			        //내용 초기화
	
			    	ptrd.getMultipleChoiceOneTextField().setText("");
			    	ptrd.getMultipleChoiceTwoTextField().setText("");
			    	ptrd.getMultipleChoiceThreeTextField().setText("");
			    	ptrd.getMultipleChoiceFourTextField().setText("");
			    	ptrd.getTestQuestionContentTextArea().setText("");
			    	ptrd.getAnswerTextField().setText("");
			    	ptrd.getTestQuestionContentTextArea().setCaretPosition(0);
				}
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

