package eduCourse_prj.admin;

import javax.swing.*;

import eduCourse_prj.VO.CourseVO;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminDeptMgtRegDialog extends JDialog {
    AdminCourDesign acd;
    JButton registerButton;//등록버튼
    JButton cancelButton;//취소버튼
    
    JTextField departmentNameTextField;//학과
    JTextField departmentCodeTextField;//학과코드
    JTextField departmentCapacityTextField;//정원
    
    public AdminDeptMgtRegDialog(AdminCourDesign acd, String title) {
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
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                 String departmentName = departmentNameTextField.getText();
                 int capacity = Integer.parseInt(departmentCapacityTextField.getText());
                 CourseVO cVO = new CourseVO(departmentName, capacity);
//                 acd.addDepartment(cVO);

                dispose();
            }
        });
        registerButton.setBounds(300, 50, 100, 30);
        
/////////////////////////////////////////////////////////////////////////////////        
//-------------------------------취소버튼 생성----------------------------------        
/////////////////////////////////////////////////////////////////////////////////   
        cancelButton = new JButton("취소");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
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
        setVisible(true);
    }
    
    public static void main(String[] args) {
    	
        AdminDeptMgtRegDialog dialog = new AdminDeptMgtRegDialog(null, "test");
        
    }//main
}
