package eduCourse_prj.admin.design;

import javax.swing.*;

import eduCourse_prj.VO.DeptVO;
import eduCourse_prj.admin.event.AdminDeptMgtRegEvent;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class AdminDeptMgtRegDesign extends JDialog {
	private AdminDeptMgtDesign admd;
	private JButton registerButton;// 등록버튼
	private JButton cancelButton;// 취소버튼
	
	private JLabel departmentNameLabel;
	private JLabel departmentCapacityLabel;
	private JLabel departmentCodeLable;
	private JLabel topLogin;
	private JLabel deptRegLabel;
	private JLabel jlBack;
	private JLabel deptMgt;

	private JTextField departmentNameTextField;// 학과
//	private JTextField departmentCodeTextField; //학과코드
	private JTextField departmentCapacityTextField;// 정원

	public AdminDeptMgtRegDesign(AdminDeptMgtDesign admd, String title) {
		super(admd, title, true);
		this.admd = admd;
		String commonPath = "src/eduCourse_prj/image/common/";
		String deptPath = "src/eduCourse_prj/image/admin/";
		
		setSize(1000, 650);
		setLocationRelativeTo(admd);
		setModal(true);
		setLayout(null);
//-------------------------------배경 추가--------------------------------------
		jlBack = new JLabel(new ImageIcon(commonPath + "Back.png"));
        jlBack.setBounds(0,0,984,620);
        
        deptMgt = new JLabel(new ImageIcon(commonPath + "RegBanner_new.png"));
        deptMgt.setBounds(10, 76, 967, 44);
        
		
		
		
//===============================라벨 추가======================================
//------------------------------header Label------------------------------------		
		topLogin = new JLabel("관리자 로그인 중");
		Font font = new Font("나눔스퀘어라운드 ExtraBold", Font.BOLD, 15);
		topLogin.setFont(font);
		topLogin.setForeground(Color.WHITE);
		topLogin.setBounds(670, 30, 200, 20);
		add(topLogin);
		

/////////////////////////////////////////////////////////////////////////////////        
//-------------------------------등록버튼 생성----------------------------------        
/////////////////////////////////////////////////////////////////////////////////        
		registerButton = new JButton(new ImageIcon(commonPath + "RegButton_new.png"));
		registerButton.setBounds(370, 481, 90, 50);

/////////////////////////////////////////////////////////////////////////////////        
//-------------------------------취소버튼 생성----------------------------------        
/////////////////////////////////////////////////////////////////////////////////   
		cancelButton = new JButton(new ImageIcon(commonPath + "CancelButton_new.png"));
		cancelButton.setBounds(520, 481, 90, 50);
		


//--------------------------텍스트 필드 생성 및 추가----------------------------------
		departmentNameLabel = new JLabel("학과");
		Font basicfont = new Font("나눔스퀘어라운드 ExtraBold", Font.BOLD, 20);
		departmentNameLabel.setFont(basicfont);
		departmentNameLabel.setBounds(340, 225, 100, 50);
		departmentNameTextField = new JTextField();//학과 텍스트필드
		departmentNameTextField.setBounds(432, 227, 200, 40);

//		
//		departmentCodeLable = new JLabel("학과 코드");
//		departmentCodeLable.setBounds(372,293,100,50);
//		departmentCodeTextField = new JTextField();
//		departmentCodeTextField.setBounds(472, 293, 200, 40);
//		departmentCodeTextField.setEnabled(false);
		
		departmentCapacityLabel = new JLabel("정원");
		departmentCapacityLabel.setFont(basicfont);
		departmentCapacityLabel.setBounds(340, 316, 100, 50);
		departmentCapacityTextField = new JTextField();
		departmentCapacityTextField.setBounds(432, 320, 200, 40);

		add(departmentNameLabel);
		add(departmentNameTextField);
//		add(departmentCodeLable);
//		add(departmentCodeTextField);
		add(departmentCapacityLabel);
		add(departmentCapacityTextField);

		add(registerButton);
		add(cancelButton);
		
		add(deptMgt);
		add(jlBack);

/////////////////////////////////////////////////////////////////////////////////
//-------------------------------event관계 설정----------------------------------         
/////////////////////////////////////////////////////////////////////////////////

		AdminDeptMgtRegEvent admre = new AdminDeptMgtRegEvent(this);
		addWindowListener(admre);
		registerButton.addActionListener(admre);
		cancelButton.addActionListener(admre);

		
		
		
		setVisible(true);
	}

	public AdminDeptMgtDesign getAdmd() {
		return admd;
	}

	public JButton getRegisterButton() {
		return registerButton;
	}

	public JButton getCancelButton() {
		return cancelButton;
	}

	public JTextField getDepartmentNameTextField() {
		return departmentNameTextField;
	}

//	public JTextField getDepartmentCodeTextField() {
//		return departmentCodeTextField;
//	}

	public JTextField getDepartmentCapacityTextField() {
		return departmentCapacityTextField;
	}

	public static void main(String[] args) {

		AdminDeptMgtRegDesign dialog = new AdminDeptMgtRegDesign(null, "test");

	}// main
}
