package eduCourse.student.design;

import java.awt.Font;

import javax.swing.*;

import eduCourse.login.SelectLoginEvent;
import eduCourse.student.event.StdntLoginEvent;

@SuppressWarnings("serial")
public class StdntLoginDesign extends JFrame {

	private SelectLoginEvent sle;
	private JTextField jtfId;
	private JPasswordField jpfPass;
	//////////////////////////////////
	private JLabel back; // 배경사진 라벨
	private JLabel sistMark;// 쌍용마크 라벨
	private JLabel title;// 살려조쌍용대학교

	private JButton loginButton, jbtLogout;

	public StdntLoginDesign(SelectLoginEvent sle) {

		super("학생 로그인");
		this.sle = sle;

		// 레이아웃 매니저 설정 비활성화
		setLayout(null);

		setSize(1000, 650);
		setLocationRelativeTo(null); // 화면 중앙배치

		// 이미지 경로 변경
		String loginPath = "src/eduCourse/image/login/";

		sistMark = new JLabel(new ImageIcon(loginPath + "sistMark.png"));
		sistMark.setBounds(340, 140, 66, 42);

		title = new JLabel("살려조쌍용대학교");
		title.setFont(new Font("나눔스퀘어라운드 ExtraBold", Font.BOLD, 30));
		title.setBounds(420, 145, 300, 30);

		back = new JLabel(new ImageIcon(loginPath + "back.png"));
		back.setBounds(0, 0, 1000, 650);

		JSeparator horizontalLine = new JSeparator(SwingConstants.HORIZONTAL);
		horizontalLine.setBounds(350, 280, 300, 40);

		JLabel jlLoginTitle = new JLabel("학생 로그인:");
		jlLoginTitle.setBounds(350, 230, 300, 40);
		jlLoginTitle.setFont(new Font("나눔스퀘어라운드 ExtraBold", Font.BOLD, 25));

		// 사용자 ID 입력 필드
		jtfId = new JTextField("202403141", 20);
		jtfId.setBounds(350, 300, 300, 40);
		jtfId.setFont(new Font("나눔스퀘어라운드 ExtraBold", Font.BOLD, 30));

		// PW 입력 필드
		jpfPass = new JPasswordField("student", 20);
		jpfPass.setBounds(350, 350, 300, 40);
		jpfPass.setFont(new Font("맑은 고딕", Font.BOLD, 30));

		// 로그인 버튼
		loginButton = new JButton(new ImageIcon(loginPath + "login.png"));
		loginButton.setBounds(350, 400, 300, 40);

		jbtLogout = new JButton(new ImageIcon(loginPath + "HomeButton.png"));//
		jbtLogout.setBounds(910, 20, 50, 50);

		// has a관계 설정

		StdntLoginEvent ale = new StdntLoginEvent(this);

		addWindowListener(ale);
		loginButton.addActionListener(ale);
		jtfId.addActionListener(ale);
		jpfPass.addActionListener(ale);
		jbtLogout.addActionListener(ale);

		// 프레임에 컴포넌트 추가

		add(title);
		add(horizontalLine);

		add(jlLoginTitle);
		add(jtfId);
		add(jpfPass);
		add(loginButton);
		add(jbtLogout);
		add(sistMark);

		add(back);
		// 프레임크기 조절 불가
		setResizable(false);

		// 프레임 표시

		setVisible(true);
	}

	public SelectLoginEvent getSle() {
		return sle;
	}

	public JTextField getJtfId() {
		return jtfId;
	}

	public JPasswordField getJpfPass() {
		return jpfPass;
	}

	public JButton getLoginButton() {
		return loginButton;
	}

	public JButton getJbtLogout() {
		return jbtLogout;
	}

}