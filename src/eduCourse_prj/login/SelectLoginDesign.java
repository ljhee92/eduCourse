package eduCourse_prj.login;
import javax.swing.*;


@SuppressWarnings("serial")
public class SelectLoginDesign extends JFrame {
    private JButton adminButton;
    private JButton studentButton;
    private JButton professorButton;



    public SelectLoginDesign() {
        super("로그인 모드 선택");
        //이미지 경로 변경
        String loginPath = "C:/dev/workspace/eduCourse_prj/src/eduCourse_prj/image/login/";
        
        // 버튼 생성
        adminButton = new JButton(new ImageIcon(loginPath + "admin.png"));
        studentButton = new JButton(new ImageIcon(loginPath +"stud.png"));
        professorButton = new JButton(new ImageIcon(loginPath +"prof.png"));

        // 버튼 크기 및 위치 설정
        adminButton.setBounds(200, 150, 194, 248);

        professorButton.setBounds(400, 150, 194, 248);
        studentButton.setBounds(600, 150, 194, 248);

        // 프레임에 버튼 추가
        add(adminButton);
        add(studentButton);
        add(professorButton);
        

        // 프레임 크기 설정
        setSize(1000, 650);
        // 프레임을 화면 중앙에 표시
        setLocationRelativeTo(null);
        
        SelectLoginEvent sle = new SelectLoginEvent(this);
        addWindowListener(sle);
        adminButton.addActionListener(sle);
        studentButton.addActionListener(sle);
        professorButton.addActionListener(sle);
        
        
        

        // 레이아웃 매니저 설정 비활성화
        setLayout(null);
        
        // 프레임 크기 조절 불가설정
        setResizable(false);
        
        setVisible(true);
        
    }

    public JButton getProfessorButton() {
		return professorButton;
	}

	public JButton getAdminButton() {
        return adminButton;
    }

    public JButton getStudentButton() {
        return studentButton;
    }


}