package eduCourse_prj.admin.design;

import javax.swing.*;

import eduCourse_prj.admin.event.AdminDeptMgtRegEvent;

import java.awt.Color;
import java.awt.Font;

@SuppressWarnings("serial")
public class AdminDeptMgtRegDesign extends JDialog {
	private AdminDeptMgtDesign admd;
	private JButton registerButton;// 등록버튼
	private JButton cancelButton;// 취소버튼

	private JLabel departmentNameLabel;
	private JLabel departmentCapacityLabel;
	private JLabel topLogin;
	private JLabel jlBack;
	private JLabel deptMgt;
	private JLabel deptMgtReg;
	private JLabel jlNecessary;

	private JTextField departmentNameTextField;// 학과
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
		jlBack.setBounds(0, 0, 984, 620);

		deptMgt = new JLabel(new ImageIcon(deptPath + "deptMgt1.png"));
		deptMgt.setBounds(10, 76, 967, 44);

		deptMgtReg = new JLabel(new ImageIcon(commonPath + "RegBanner_new.png"));
		deptMgtReg.setBounds(10, 119, 967, 44);

		jlNecessary = new JLabel("학과는 필수입력 사항입니다.");
		Font sfont = new Font("나눔스퀘어라운드 ExtraBold", Font.BOLD, 13);
		jlNecessary.setFont(sfont);
		jlNecessary.setForeground(Color.RED);
		jlNecessary.setBounds(480, 375, 300, 30);

//===============================라벨 추가======================================
//------------------------------header Label------------------------------------
		String adminName = admd.getAhd().getlVO().getName();
		topLogin = new JLabel(adminName + " 관리자님 로그인 중");
		Font font = new Font("나눔스퀘어라운드 ExtraBold", Font.BOLD, 17);
		topLogin.setFont(font);
		topLogin.setForeground(Color.WHITE);
		topLogin.setBounds(620, 30, 300, 20);
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
		departmentNameLabel.setBounds(340, 245, 100, 50);
		departmentNameTextField = new JTextField();// 학과 텍스트필드
		departmentNameTextField.setBounds(432, 247, 200, 40);

		departmentCapacityLabel = new JLabel("정원");
		departmentCapacityLabel.setFont(basicfont);
		departmentCapacityLabel.setBounds(340, 336, 100, 50);
		departmentCapacityTextField = new JTextField();
		departmentCapacityTextField.setBounds(432, 340, 200, 40);
		
		departmentNameTextField.setFont(font);
		departmentCapacityTextField.setFont(font);
		
		add(departmentNameLabel);
		add(departmentNameTextField);
		add(departmentCapacityLabel);
		add(departmentCapacityTextField);

		add(registerButton);
		add(cancelButton);

		add(jlNecessary);
		add(deptMgt);
		add(deptMgtReg);
		add(jlBack);

/////////////////////////////////////////////////////////////////////////////////
//-------------------------------event관계 설정----------------------------------         
/////////////////////////////////////////////////////////////////////////////////

		AdminDeptMgtRegEvent admre = new AdminDeptMgtRegEvent(this);
		addWindowListener(admre);
		registerButton.addActionListener(admre);
		cancelButton.addActionListener(admre);

		setResizable(false);

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

	public JTextField getDepartmentCapacityTextField() {
		return departmentCapacityTextField;
	}
}
