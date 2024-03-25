package eduCourse_prj.professor.design;

import java.awt.Color;
import java.awt.Font;
import java.sql.SQLException;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import eduCourse_prj.VO.CrsVO;
import eduCourse_prj.admin.design.AdminDeptMgtDesign;
import eduCourse_prj.admin.event.AdminDeptMgtRegEvent;
import eduCourse_prj.professor.dao.TestDAO;
import eduCourse_prj.professor.event.ProfTestRegEvent;

public class ProfTestRegDesign extends JDialog {
	private ProfTestMgtDesign ptmd;
	TestDAO tDAO = TestDAO.getInstance();
	



	private JButton registerButton;// 출제버튼
	private JButton cancelButton;// 취소버튼
	
	private JComboBox<String> testNumberComboBox; //문제 번호 콤보박스
	
	private JLabel testNumberLabel; //문제 번호
	private JLabel multipleChoiceOneLabel;//선택 1
	private JLabel multipleChoiceTwoLabel;//선택 2
	private JLabel multipleChoiceThreeLabel;//선택 3
	private JLabel multipleChoiceFourLabel;//선택 4
	private JLabel testQuestionContentLabel;// 문제내용
	private JLabel answerLabel;//정답
	private JLabel topLogin;
	private JLabel deptRegLabel;
	private JLabel jlBack;
	private JLabel deptMgt;

	private JTextField multipleChoiceOneTextField;// 선택 1
	private JTextField multipleChoiceTwoTextField;// 선택 2
	private JTextField multipleChoiceThreeTextField;// 선택 3
	private JTextField multipleChoiceFourTextField;// 선택 4
	private JTextArea testQuestionContentTextArea;// 문제내용
	private JTextField answerTextField;// 정답
	

	public ProfTestRegDesign(ProfTestMgtDesign ptmd, String title) {
		super();
		this.ptmd = this.ptmd;

		
			this.ptmd = ptmd;
			String commonPath = "src/eduCourse_prj/image/common/";
			String testPath = "src/eduCourse_prj/image/prof/";

			setSize(1000, 650);
//			setLocationRelativeTo(ptmd);
			setModal(true);
			setLayout(null);
	//-------------------------------배경 추가--------------------------------------
			jlBack = new JLabel(new ImageIcon(commonPath + "Back.png"));
	        jlBack.setBounds(0,0,984,620);
	        
	        deptMgt = new JLabel(new ImageIcon(commonPath + "Reg_label.png"));
	        deptMgt.setBounds(10, 76, 967, 44);
	        
			
			
			
	//===============================라벨 추가======================================
	//------------------------------header Label------------------------------------		
			topLogin = new JLabel(ptmd.getPhd().getlVO().getName() + " 교수 로그인 중");
			Font font = new Font("나눔스퀘어라운드 ExtraBold", Font.BOLD, 15);
			topLogin.setFont(font);
			topLogin.setForeground(Color.WHITE);
			topLogin.setBounds(670, 30, 200, 20);
			add(topLogin);
			

	/////////////////////////////////////////////////////////////////////////////////        
	//-------------------------------등록버튼 생성----------------------------------        
	/////////////////////////////////////////////////////////////////////////////////        
			registerButton = new JButton(new ImageIcon(commonPath + "Reg.png"));
			registerButton.setBounds(720, 481, 60, 40);

	/////////////////////////////////////////////////////////////////////////////////        
	//-------------------------------취소버튼 생성----------------------------------        
	/////////////////////////////////////////////////////////////////////////////////   
			cancelButton = new JButton(new ImageIcon(commonPath + "Cancel.png"));
			cancelButton.setBounds(800, 481, 60, 40);
			
	//----------------------------좌측 인터페이스-----------------------------------------
			testNumberComboBox = new JComboBox<>();
			List<Integer> courNum;
			
			try {
				int seletedRow = ptmd.getJtbTestMgt().getSelectedRow();
				String seletedValue = (String) ptmd.getJtbTestMgt().getValueAt(seletedRow,0); //과목 이름
				
				
				courNum = tDAO.selectValidTestNumber(seletedValue);
				for (int i = 1; i <= 10; i++) {
				    boolean skipNumber = false;
				    for (Integer num : courNum) {
				        if (i == num) {
				            skipNumber = true;
				            break;
				        }
				    }
				    if (!skipNumber) {
				        testNumberComboBox.addItem(String.valueOf(i)); // 숫자를 문자열로 변환하여 콤보 박스에 추가
				    }
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
//			for (CrsVO crs : lCrs) {
//
//				jcbCrs.addItem(crs.getCourName());
//
//			}
			
			


			testNumberComboBox.setBounds(150, 180, 50, 30);
			testNumberLabel = new JLabel("문제 번호");
			testNumberLabel.setBounds(80, 180, 60, 30);
			
			testQuestionContentLabel = new JLabel("내용");
			testQuestionContentLabel.setBounds(80, 230, 50, 30);
			testQuestionContentTextArea = new JTextArea();
			testQuestionContentTextArea.setBounds(150, 230, 300, 300);
			testQuestionContentTextArea.setLineWrap(true);
			testQuestionContentTextArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			testQuestionContentTextArea.setBackground(Color.WHITE);
			
			


	//--------------------------텍스트 필드 생성 및 추가----------------------------------
			multipleChoiceOneLabel = new JLabel("선택 1");
			multipleChoiceOneLabel.setBounds(530, 250, 100, 30);
			multipleChoiceOneTextField = new JTextField();//학과 텍스트필드
			multipleChoiceOneTextField.setBounds(580, 250, 200, 30);

			multipleChoiceTwoLabel = new JLabel("선택 2");
			multipleChoiceTwoLabel.setBounds(530, 300, 100, 30);
			multipleChoiceTwoTextField = new JTextField();
			multipleChoiceTwoTextField.setBounds(580, 300, 200, 30);
			
			multipleChoiceThreeLabel = new JLabel("선택 3");
			multipleChoiceThreeLabel.setBounds(530, 350, 100, 30);
			multipleChoiceThreeTextField = new JTextField();
			multipleChoiceThreeTextField.setBounds(580, 350, 200, 30);
			
			multipleChoiceFourLabel = new JLabel("선택 4");
			multipleChoiceFourLabel.setBounds(530, 400, 100, 30);
			multipleChoiceFourTextField = new JTextField();
			multipleChoiceFourTextField.setBounds(580, 400, 200, 30);
			
			answerLabel = new JLabel("정답");
			answerLabel.setBounds(530, 460, 100, 30);
			answerTextField = new JTextField();
			answerTextField.setBounds(580, 460, 50, 30);
			
			
			add(testNumberComboBox);
			add(testNumberLabel);
			add(testQuestionContentLabel);
			add(testQuestionContentTextArea);
			add(multipleChoiceOneLabel);
			add(multipleChoiceOneTextField);
			add(multipleChoiceTwoLabel);
			add(multipleChoiceTwoTextField);
			add(multipleChoiceThreeLabel);
			add(multipleChoiceThreeTextField);
			add(multipleChoiceFourLabel);
			add(multipleChoiceFourTextField);
			
			add(answerLabel);
			add(answerTextField);

			add(registerButton);
			add(cancelButton);
			
			add(deptMgt);
			add(jlBack);

	/////////////////////////////////////////////////////////////////////////////////
	//-------------------------------event관계 설정----------------------------------         
	/////////////////////////////////////////////////////////////////////////////////

			ProfTestRegEvent admre = new ProfTestRegEvent(this,ptmd);
			addWindowListener(admre);
			registerButton.addActionListener(admre);
			cancelButton.addActionListener(admre);
			
			setVisible(true);
	}
	
	public JButton getRegisterButton() {
		return registerButton;
	}



	public JButton getCancelButton() {
		return cancelButton;
	}


	public JTextField getMultipleChoiceOneTextField() {
		return multipleChoiceOneTextField;
	}



	public JTextField getMultipleChoiceTwoTextField() {
		return multipleChoiceTwoTextField;
	}



	public JTextField getMultipleChoiceThreeTextField() {
		return multipleChoiceThreeTextField;
	}



	public JTextField getMultipleChoiceFourTextField() {
		return multipleChoiceFourTextField;
	}



	public JTextField getAnswerTextField() {
		return answerTextField;
	}

	
	

	public JComboBox<String> getTestNumberComboBox() {
		return testNumberComboBox;
	}



	public JTextArea getTestQuestionContentTextArea() {
		return testQuestionContentTextArea;
	}
	
	public ProfTestMgtDesign getPtmd() {
		return ptmd;
	}



//	public static void main(String[] args) {
//		ProfTestRegDesign ptrd = new ProfTestRegDesign(null, null);
//	}//main
}
