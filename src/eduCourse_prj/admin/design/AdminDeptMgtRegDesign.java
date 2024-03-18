package eduCourse_prj.admin.design;

import javax.swing.*;

import eduCourse_prj.VO.DeptVO;
import eduCourse_prj.admin.event.AdminDeptMgtRegEvent;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminDeptMgtRegDesign extends JDialog {
	private AdminDeptMgtDesign admd;
	private JButton registerButton;// 등록버튼
	private JButton cancelButton;// 취소버튼
	
	private JLabel departmentNameLabel;
	private JLabel departmentCapacityLabel;
	private JLabel topLogin;
	private JLabel deptRegLabel;
	private JLabel jlBack;
	private JLabel deptMgt;

	private JTextField departmentNameTextField;// 학과
	//JTextField departmentCodeTextField;// 학과코드
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
        
        deptMgt = new JLabel(new ImageIcon(commonPath + "Reg_label.png"));
        deptMgt.setBounds(10, 76, 967, 44);
        
		
		
		
//===============================라벨 추가======================================
//------------------------------header Label------------------------------------		
		topLogin = new JLabel("관리자 로그인 중");
		Font font = new Font("나눔스퀘어라운드 ExtraBold", Font.BOLD, 15);
		topLogin.setFont(font);
		topLogin.setForeground(Color.RED);
		topLogin.setBounds(670, 30, 200, 20);
		add(topLogin);
		

/////////////////////////////////////////////////////////////////////////////////        
//-------------------------------등록버튼 생성----------------------------------        
/////////////////////////////////////////////////////////////////////////////////        
		registerButton = new JButton(new ImageIcon(commonPath + "Reg.png"));
		registerButton.setBounds(350, 481, 111, 59);

/////////////////////////////////////////////////////////////////////////////////        
//-------------------------------취소버튼 생성----------------------------------        
/////////////////////////////////////////////////////////////////////////////////   
		cancelButton = new JButton(new ImageIcon(commonPath + "Cancel.png"));
		cancelButton.setBounds(500, 481, 111, 59);
		


//--------------------------텍스트 필드 생성 및 추가----------------------------------
		departmentNameLabel = new JLabel("학과");
		departmentNameLabel.setBounds(372, 267, 100, 30);
		departmentNameTextField = new JTextField();//학과 텍스트필드
		departmentNameTextField.setBounds(472, 267, 200, 30);


		//departmentCodeTextField = new JTextField();
		//departmentCodeTextField.setBounds(50, 100, 200, 30);
		departmentCapacityLabel = new JLabel("정원");
		departmentCapacityLabel.setBounds(372, 320, 100, 30);
		departmentCapacityTextField = new JTextField();
		departmentCapacityTextField.setBounds(472, 320, 200, 30);

		add(departmentNameLabel);
		add(departmentNameTextField);
		//add(departmentCodeTextField);
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
