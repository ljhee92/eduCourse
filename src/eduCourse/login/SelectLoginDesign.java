package eduCourse.login;

import java.awt.Font;

import javax.swing.*;

@SuppressWarnings("serial")
public class SelectLoginDesign extends JFrame {
	
	private JButton adminButton;
	private JButton studentButton;
	private JButton professorButton;
	private JLabel sistMark; // 쌍용마크 라벨
	private JLabel title; // 살려조쌍용대학교
	private JLabel copyRight; // 하단텍스트

	public SelectLoginDesign() {
		super("로그인 모드 선택");
		// 이미지 경로 변경
		String loginPath = "src/eduCourse/image/login/";

		// 마크 라벨, 살려조쌍용대학교, 하단텍스트 생성
		sistMark = new JLabel(new ImageIcon(loginPath + "sistMark.png"));
		title = new JLabel("살려조쌍용대학교");
		copyRight = new JLabel("Copyright © 살려조쌍용대학교. All rights reserved.");

		// 버튼 생성
		adminButton = new JButton(new ImageIcon(loginPath + "admin.png"));
		studentButton = new JButton(new ImageIcon(loginPath + "stud.png"));
		professorButton = new JButton(new ImageIcon(loginPath + "prof.png"));

		// 버튼 크기 및 위치 설정
		adminButton.setBounds(200, 150, 194, 248);

		professorButton.setBounds(400, 150, 194, 248);
		studentButton.setBounds(600, 150, 194, 248);

		// 마크 라벨, 살려조쌍용대학교, 하단텍스트 위치설정
		sistMark.setBounds(340, 80, 66, 42);
		title.setFont(new Font("나눔스퀘어라운드 ExtraBold", Font.BOLD, 30));
		title.setBounds(420, 80, 300, 30);
		copyRight.setBounds(340, 450, 400, 40);
		copyRight.setFont(new Font("나눔스퀘어라운드 Regular", Font.PLAIN, 14));

		// 프레임에 버튼 추가
		add(adminButton);
		add(studentButton);
		add(professorButton);

		// 프레임에 마크 라벨, 살려조쌍용대학교, 하단텍스트 추가
		add(sistMark);
		add(title);
		add(copyRight);

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
	} // SelectLoginDesign

	public JButton getProfessorButton() {
		return professorButton;
	}

	public JButton getAdminButton() {
		return adminButton;
	}

	public JButton getStudentButton() {
		return studentButton;
	}

} // class