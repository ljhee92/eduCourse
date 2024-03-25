package eduCourse_prj.admin.design;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.swing.*;

import eduCourse_prj.VO.LoginVO;
import eduCourse_prj.admin.event.AdminHomeEvent;

@SuppressWarnings({ "serial", "unused" })
public class AdminHomeDesign extends JFrame {
	private LoginVO lVO;

	private JButton jbtAdminMgt, jbtProfMgt, jbtDeptMgt, jbtStudMgt, jbtCourMgt, jbtSchedMgt, jbtLogout;

	JLabel img; // 사진 삽입 라벨
	JLabel role; // 권한 표시될 라벨
	JLabel showId; // id 표시될 라벨
	JLabel name;// 이름
	JLabel back; // 배경사진 라벨
	JLabel topLogin; // 우상단 로그인상태 확인창

	public AdminHomeDesign(LoginVO lVO) {
		super("관리자 작업창");

		this.lVO = lVO;

		setLayout(null); // 수동 배치를 위해 레이아웃 매니저를 null로 설정

		String commonPath = "src/eduCourse_prj/image/common/";
		String adminPath = "src/eduCourse_prj/image/admin/";
		
		
		back = new JLabel(new ImageIcon(commonPath+"Back.png"));
		role = new JLabel("권한 : 관리자");
		showId = new JLabel("ID : " + lVO.getId());
		name = new JLabel("이름 : " + lVO.getName());
		topLogin = new JLabel(lVO.getName() + " 관리자님 로그인 중");
		img = new JLabel(new ImageIcon(commonPath+"Img_new.png"));

		jbtAdminMgt = new JButton(new ImageIcon(adminPath+"AdminMgt_new.png"));//AdminMgt
		jbtProfMgt = new JButton(new ImageIcon(adminPath+"ProfMgt_new.png"));//ProfMgt
		jbtDeptMgt = new JButton(new ImageIcon(adminPath+"DeptMgt_new.png"));//DeptMgt
		jbtStudMgt = new JButton(new ImageIcon(adminPath+"StudMgt_new.png"));//StudMgt
		jbtCourMgt = new JButton(new ImageIcon(adminPath+"CourMgt_new.png"));//CourMgt
		jbtSchedMgt = new JButton(new ImageIcon(adminPath+"SchedMgt_new.png"));//SchedMgt
		jbtLogout = new JButton(new ImageIcon(commonPath+"Logout.png"));//

		/////////////////////////////////////////////////
		// 요소에 추가선언
		Font font = new Font("나눔스퀘어라운드 ExtraBold", Font.BOLD, 17);
		topLogin.setFont(font);
		topLogin.setForeground(Color.WHITE);

		role.setFont(font);
		showId.setFont(font);
		name.setFont(font);
		/////////////////////////////////////////////////

		// 요소 크기 및 위치 선언
		/////////////////////////////////////////////////

		back.setBounds(0, 0, 984, 620);// 성공

		role.setBounds(530, 220, 150, 20);
		showId.setBounds(530, 260, 150, 20);
		name.setBounds(530, 300, 200, 20);
		topLogin.setBounds(670, 30, 200, 20);

		img.setBounds(270, 150, 198, 233);

		jbtAdminMgt.setBounds(50, 450, 120, 50);
		jbtProfMgt.setBounds(200, 450, 120, 50);
		jbtDeptMgt.setBounds(350, 450, 120, 50);
		jbtStudMgt.setBounds(500, 450, 120, 50);
		jbtCourMgt.setBounds(650, 450, 120, 50);
		jbtSchedMgt.setBounds(800, 450, 120, 50);
		jbtLogout.setBounds(870, 20, 100, 36);

		// JFrame에 버튼 추가
		add(topLogin);
		add(role);
		add(img);
		add(showId);
		add(name);
		add(jbtAdminMgt);
		add(jbtProfMgt);
		add(jbtDeptMgt);
		add(jbtStudMgt);
		add(jbtCourMgt);
		add(jbtSchedMgt);
		add(jbtLogout);

		add(back);

		// AdminWorkEvent와 has a관계 설정
		AdminHomeEvent awe = new AdminHomeEvent(this);
		addWindowListener(awe);
		jbtAdminMgt.addActionListener(awe);
		jbtProfMgt.addActionListener(awe);
		jbtDeptMgt.addActionListener(awe);
		jbtStudMgt.addActionListener(awe);
		jbtCourMgt.addActionListener(awe);
		jbtSchedMgt.addActionListener(awe);
		jbtLogout.addActionListener(awe);

		setSize(1000, 650); // +16 +30

		setVisible(true);

		// 프레임크기 조절 불가
		setResizable(false);

		// 프레임을 화면 중앙에 배치
		setLocationRelativeTo(null);
	}

	public LoginVO getlVO() {
		return lVO;
	}

	public JButton getJbtAdminMgt() {
		return jbtAdminMgt;
	}

	public JButton getJbtProfMgt() {
		return jbtProfMgt;
	}

	public JButton getJbtDeptMgt() {
		return jbtDeptMgt;
	}

	public JButton getJbtStudMgt() {
		return jbtStudMgt;
	}

	public JButton getJbtCourMgt() {
		return jbtCourMgt;
	}

	public JButton getJbtSchedMgt() {
		return jbtSchedMgt;
	}

	public JButton getJbtLogout() {
		return jbtLogout;
	}

	public JLabel getImg() {
		return img;
	}

	public JLabel getRole() {
		return role;
	}

	public JLabel getShowId() {
		return showId;
	}

	public JLabel getBack() {
		return back;
	}

	public JLabel getTopLogin() {
		return topLogin;
	}
}