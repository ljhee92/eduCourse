package eduCourse.login;

import java.awt.Font;

import javax.swing.*;

@SuppressWarnings("serial")
public class SelectLoginDesign extends JFrame {
	
	private JButton jbtnAdmin, jbtnProfessor, jbtnStudent;
	private JLabel jlabelSistMark, jlabelTitle, jlabelCopyRight; // 쌍용마크 라벨, 살려조쌍용대학교, 하단텍스트

	public SelectLoginDesign() {
		super("로그인 모드 선택");
		
		// 레이아웃 설정 비활성화
		setLayout(null);

		// 이미지 경로 설정
		String loginPath = "src/eduCourse/image/login/";
		
		addButton(loginPath);
		addLabel(loginPath);
		addEvent();
		
		// 프레임 크기 설정
		setSize(1000, 650);
		
		// 프레임을 화면 중앙에 표시
		setLocationRelativeTo(null);

		// 프레임 크기 조절 불가설정
		setResizable(false);
		setVisible(true);
	} // SelectLoginDesign
	
	/**
	 * 라벨 생성, 추가 method
	 * @param loginPath
	 */
	private void addLabel(String loginPath) {
		// 마크 라벨, 살려조쌍용대학교, 하단텍스트 생성
		jlabelSistMark = new JLabel(new ImageIcon(loginPath + "sistMark.png"));
		jlabelTitle = new JLabel("살려조쌍용대학교");
		jlabelCopyRight = new JLabel("Copyright © 살려조쌍용대학교. All rights reserved.");
		
		// 마크 라벨, 살려조쌍용대학교, 하단텍스트 위치 설정
		jlabelSistMark.setBounds(340, 80, 66, 42);
		jlabelTitle.setBounds(420, 80, 300, 30);
		jlabelCopyRight.setBounds(340, 450, 400, 40);
		
		// 살려조쌍용대학교, 하단텍스트 폰트 설정
		jlabelTitle.setFont(new Font("나눔스퀘어라운드 ExtraBold", Font.BOLD, 30));
		jlabelCopyRight.setFont(new Font("나눔스퀘어라운드 Regular", Font.PLAIN, 14));
		
		// 프레임에 마크 라벨, 살려조쌍용대학교, 하단텍스트 추가
		add(jlabelSistMark);
		add(jlabelTitle);
		add(jlabelCopyRight);
	} // addLabel
	
	/**
	 * 버튼 생성, 추가 method
	 * @param loginPath
	 */
	private void addButton(String loginPath) {
		// 버튼 생성
		jbtnAdmin = new JButton(new ImageIcon(loginPath + "admin.png"));
		jbtnProfessor = new JButton(new ImageIcon(loginPath + "prof.png"));
		jbtnStudent = new JButton(new ImageIcon(loginPath + "stud.png"));
		
		// 버튼 크기 및 위치 설정
		jbtnAdmin.setBounds(200, 150, 194, 248);
		jbtnProfessor.setBounds(400, 150, 194, 248);
		jbtnStudent.setBounds(600, 150, 194, 248);
		
		// 프레임에 버튼 추가
		add(jbtnAdmin);
		add(jbtnProfessor);
		add(jbtnStudent);
	} // addButton
	
	/**
	 * Component에 이벤트 연결 method
	 */
	private void addEvent() {
		// 이벤트 연결
		SelectLoginEvent selectLoginEvent = new SelectLoginEvent(this);
		addWindowListener(selectLoginEvent);
		jbtnAdmin.addActionListener(selectLoginEvent);
		jbtnProfessor.addActionListener(selectLoginEvent);
		jbtnStudent.addActionListener(selectLoginEvent);
	} // addEvent

	public JButton getJbtnAdmin() {
		return jbtnAdmin;
	}

	public JButton getJbtnProfessor() {
		return jbtnProfessor;
	}
	
	public JButton getJbtnStudent() {
		return jbtnStudent;
	}

} // class