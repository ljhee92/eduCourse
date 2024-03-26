package eduCourse_prj.professor.design;

import java.awt.Color;
import java.awt.Font;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import eduCourse_prj.VO.TestPageVO;
import eduCourse_prj.admin.design.AdminDeptMgtDesign;
import eduCourse_prj.admin.event.AdminDeptMgtRegEvent;
import eduCourse_prj.professor.dao.TestDAO;
import eduCourse_prj.professor.event.ProfTestMdfyEvent;
import eduCourse_prj.professor.event.ProfTestRegEvent;

public class ProfTestMdfyDesign extends JDialog {
	private ProfTestMgtDesign ptmd;
	



	private JButton modifyButton;// 수정버튼
	private JButton cancelButton;// 취소버튼
	
	private JComboBox<String> testNumberComboBox; //문제 번호 콤보박스
	
	private JLabel jlCourseName1;
	private JLabel jlCourseName2;
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
	

	public ProfTestMdfyDesign(ProfTestMgtDesign ptmd, String title) {
		super(ptmd, title, true);
		this.ptmd = ptmd;

		
			String commonPath = "src/eduCourse_prj/image/common/";
//			String testPath = "src/eduCourse_prj/image/prof/";

			setSize(1000, 650);
			setLocationRelativeTo(ptmd);
			setModal(true);
			setLayout(null);
	//-------------------------------배경 추가--------------------------------------
			jlBack = new JLabel(new ImageIcon(commonPath + "Back.png"));
	        jlBack.setBounds(0,0,984,620);
	        
	        deptMgt = new JLabel(new ImageIcon(commonPath + "Mdfy_label.png"));
	        deptMgt.setBounds(10, 76, 967, 44);
	        
			
			
			
	//===============================라벨 추가======================================
	//------------------------------header Label------------------------------------		
			topLogin = new JLabel(ptmd.getPhd().getlVO().getName() +" 교수님 로그인 중");
			Font font = new Font("나눔스퀘어라운드 ExtraBold", Font.BOLD, 15);
			topLogin.setFont(font);
			topLogin.setForeground(Color.WHITE);
			topLogin.setBounds(670, 30, 200, 20);
			add(topLogin);
			

	/////////////////////////////////////////////////////////////////////////////////        
	//-------------------------------수정버튼 생성----------------------------------        
	/////////////////////////////////////////////////////////////////////////////////        
			modifyButton = new JButton(new ImageIcon(commonPath + "Mdfy.png"));
			modifyButton.setBounds(720, 500, 60, 40);

	/////////////////////////////////////////////////////////////////////////////////        
	//-------------------------------취소버튼 생성----------------------------------        
	/////////////////////////////////////////////////////////////////////////////////   
			cancelButton = new JButton(new ImageIcon(commonPath + "Cancel.png"));
			cancelButton.setBounds(800, 500, 60, 40);
			
	//----------------------------좌측 인터페이스-----------------------------------------
			testNumberComboBox = new JComboBox<>();
			for (int i = 1; i <= 10; i++) {
				testNumberComboBox.addItem(String.valueOf(i)); // 숫자를 문자열로 변환하여 콤보 박스에 추가
			}
			
			jlCourseName1 = new JLabel("과목명");
			jlCourseName1.setFont(font);
			jlCourseName1.setBounds(80, 140, 60, 30);
			jlCourseName2 = new JLabel();
			jlCourseName2.setFont(font);
			jlCourseName2.setBounds(150, 140, 150, 30);
			
			// 과목명 테이블에서 가져오기
			slctCourseName();
			
			testNumberComboBox.setBounds(150, 180, 50, 30);
			testNumberLabel = new JLabel("문제 번호");
			testNumberLabel.setFont(font);
			testNumberLabel.setBounds(80, 180, 60, 30);
			
			testQuestionContentLabel = new JLabel("내용");
			testQuestionContentLabel.setFont(font);
			testQuestionContentLabel.setBounds(80, 230, 50, 30);
			
			testQuestionContentTextArea = new JTextArea();
			testQuestionContentTextArea.setBounds(150, 230, 300, 300);
			
			testQuestionContentTextArea.setLineWrap(true);
			testQuestionContentTextArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			testQuestionContentTextArea.setBackground(Color.WHITE);
			
			


	//--------------------------텍스트 필드 생성 및 추가----------------------------------
			multipleChoiceOneLabel = new JLabel("선택 1");
			multipleChoiceOneLabel.setFont(font);
			multipleChoiceOneLabel.setBounds(530, 250, 100, 30);
			multipleChoiceOneTextField = new JTextField();
			multipleChoiceOneTextField.setBounds(580, 250, 200, 30);

			multipleChoiceTwoLabel = new JLabel("선택 2");
			multipleChoiceTwoLabel.setFont(font);
			multipleChoiceTwoLabel.setBounds(530, 300, 100, 30);
			multipleChoiceTwoTextField = new JTextField();
			multipleChoiceTwoTextField.setBounds(580, 300, 200, 30);
			
			multipleChoiceThreeLabel = new JLabel("선택 3");
			multipleChoiceThreeLabel.setFont(font);
			multipleChoiceThreeLabel.setBounds(530, 350, 100, 30);
			multipleChoiceThreeTextField = new JTextField();
			multipleChoiceThreeTextField.setBounds(580, 350, 200, 30);
			
			multipleChoiceFourLabel = new JLabel("선택 4");
			multipleChoiceFourLabel.setFont(font);
			multipleChoiceFourLabel.setBounds(530, 400, 100, 30);
			multipleChoiceFourTextField = new JTextField();
			multipleChoiceFourTextField.setBounds(580, 400, 200, 30);
			
			answerLabel = new JLabel("정답");
			answerLabel.setFont(font);
			answerLabel.setBounds(530, 460, 100, 30);
			answerTextField = new JTextField();
			answerTextField.setBounds(580, 460, 50, 30);
			
			// 내용 JTA와 보기 JTF, 정답 JTF에 DB 값을 추가하는 일
			slctTestQuestionContent();

			add(jlCourseName1);
			add(jlCourseName2);
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

			add(modifyButton);
			add(cancelButton);
			
			add(deptMgt);
			add(jlBack);

	/////////////////////////////////////////////////////////////////////////////////
	//-------------------------------event관계 설정----------------------------------         
	/////////////////////////////////////////////////////////////////////////////////

			ProfTestMdfyEvent ptme = new ProfTestMdfyEvent(this);
			addWindowListener(ptme);
			modifyButton.addActionListener(ptme);
			cancelButton.addActionListener(ptme);
			testNumberComboBox.addActionListener(ptme);
			
			setVisible(true);
			
	}
	
	/**
	 * ptmd의 테이블에서 선택한 과목명의 값을 가져와 과목명 라벨에 추가하는 method
	 */
	private void slctCourseName() {
		int selectedRow = ptmd.getJtbTestMgt().getSelectedRow();
		String courseName = ptmd.getDtmTestMgt().getValueAt(selectedRow, 0).toString();
		jlCourseName2.setText(courseName);
	} // slctCourseName
	
	/**
	 * DB test_question 테이블에 저장되어있는 question_content의 값을 가져와 JTA, JTF에 추가하는 method
	 */
	private void slctTestQuestionContent() {
		int selectedRow = ptmd.getJtbTestMgt().getSelectedRow();
		String courseName = ptmd.getDtmTestMgt().getValueAt(selectedRow, 0).toString();
		
		int selectedQuestionNumber = Integer.parseInt(testNumberComboBox.getSelectedItem().toString());

		TestDAO tDAO = TestDAO.getInstance();
		try {
			TestPageVO tVO = tDAO.slctOneTestQuestion(courseName, selectedQuestionNumber);
			
			testQuestionContentTextArea.setText(tVO.getQuestion_split_content());
			multipleChoiceOneTextField.setText(tVO.getOption1());
			multipleChoiceTwoTextField.setText(tVO.getOption2());
			multipleChoiceThreeTextField.setText(tVO.getOption3());
			multipleChoiceFourTextField.setText(tVO.getOption4());
			answerTextField.setText(tVO.getAnswer());
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(ptmd, "SQL 문제가 발생했습니다.");
			e.printStackTrace();
		} // end catch
	} // slctTestQuestionContent
	
	public JButton getModifyButton() {
		return modifyButton;
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
	
	
	public JLabel getJlCourseName2() {
		return jlCourseName2;
	}

	public ProfTestMgtDesign getPtmd() {
		return ptmd;
	}

}
