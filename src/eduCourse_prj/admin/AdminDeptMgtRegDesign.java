package eduCourse_prj.admin;

import javax.swing.*;

import eduCourse_prj.VO.CourseVO;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminDeptMgtRegDesign extends JDialog {
	AdminCourDesign acd;
	JButton registerButton;// 등록버튼
	JButton cancelButton;// 취소버튼

	JTextField departmentNameTextField;// 학과
	JTextField departmentCodeTextField;// 학과코드
	JTextField departmentCapacityTextField;// 정원

	public AdminDeptMgtRegDesign(AdminCourDesign acd, String title) {
		super(acd, title, true);
		this.acd = acd;

		setSize(1000, 650);
		setLocationRelativeTo(acd);
		setModal(true);
		setLayout(null);

/////////////////////////////////////////////////////////////////////////////////        
//-------------------------------등록버튼 생성----------------------------------        
/////////////////////////////////////////////////////////////////////////////////        
		registerButton = new JButton("등록");
		registerButton.setBounds(300, 50, 100, 30);

/////////////////////////////////////////////////////////////////////////////////        
//-------------------------------취소버튼 생성----------------------------------        
/////////////////////////////////////////////////////////////////////////////////   
		cancelButton = new JButton("취소");
		cancelButton.setBounds(300, 100, 100, 30);

//--------------------------텍스트 필드 생성 및 추가----------------------------------
		departmentNameTextField = new JTextField();
		departmentNameTextField.setBounds(50, 50, 200, 30);

		departmentCodeTextField = new JTextField();
		departmentCodeTextField.setBounds(50, 100, 200, 30);

		departmentCapacityTextField = new JTextField();
		departmentCapacityTextField.setBounds(50, 150, 200, 30);

		add(departmentNameTextField);
		add(departmentCodeTextField);
		add(departmentCapacityTextField);

		add(registerButton);
		add(cancelButton);

/////////////////////////////////////////////////////////////////////////////////
//-------------------------------event관계 설정----------------------------------         
/////////////////////////////////////////////////////////////////////////////////

		AdminDeptMgtRegEvent admre = new AdminDeptMgtRegEvent(this);
		addWindowListener(admre);
		registerButton.addActionListener(admre);
		cancelButton.addActionListener(admre);

		setVisible(true);
	}

	public AdminCourDesign getAcd() {
		return acd;
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

	public JTextField getDepartmentCodeTextField() {
		return departmentCodeTextField;
	}

	public JTextField getDepartmentCapacityTextField() {
		return departmentCapacityTextField;
	}

	public static void main(String[] args) {

		AdminDeptMgtRegDesign dialog = new AdminDeptMgtRegDesign(null, "test");

	}// main
}
