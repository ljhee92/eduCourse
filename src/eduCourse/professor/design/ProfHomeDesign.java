package eduCourse.professor.design;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.sql.SQLException;

import javax.swing.*;

import eduCourse.VO.AdminProfVO;
import eduCourse.VO.LoginVO;
import eduCourse.VO.ProfVO;
import eduCourse.professor.dao.ProfDAO;
import eduCourse.professor.event.ProfHomeEvent;

@SuppressWarnings({ "serial", "unused" })
public class ProfHomeDesign extends JFrame {
	private LoginVO lVO;

	private JButton jbtnCourMgt, jbtnExamMgtGrad, jbtnStudMgt, jbtnStudySucc, jbtnInfoUpda, jbtnLogout, jbtnSched;

	JLabel img; // 사진 삽입 라벨
	JLabel role; // 권한 표시될 라벨
	JLabel showId; // id 표시될 라벨
	JLabel name; // 이름
	JLabel back; // 배경사진 라벨
	JLabel topLogin;// 우상단 로그인상태 확인창
	JLabel dept, email;

	public ProfHomeDesign(LoginVO lVO) {
		super("교수 모드");

		this.lVO = lVO;

		setLayout(null); // 수동 배치를 위해 레이아웃 매니저를 null로 설정

		String commonPath = "src/eduCourse/image/common/";
		String profPath = "src/eduCourse/image/prof/";

		back = new JLabel(new ImageIcon(commonPath + "back.png"));
		role = new JLabel("권한 : 교수");
		showId = new JLabel("교번 : " + lVO.getId());
		name = new JLabel("이름 : " + lVO.getName());

		ProfDAO pDAO = ProfDAO.getInstance();
		AdminProfVO apVO = null;

		try {
			apVO = pDAO.slctProfMgtSlct(Integer.parseInt(lVO.getId()));
			dept = new JLabel("소속학과 : " + apVO.getDept_name());
			email = new JLabel("이메일 : " + (apVO.getProf_email() == null ? "" : apVO.getProf_email()));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} // end catch

		topLogin = new JLabel(lVO.getName() + " 교수님 로그인 중");
		img = new JLabel(new ImageIcon(commonPath + "photo.png"));

		jbtnCourMgt = new JButton(new ImageIcon(profPath + "CourMgt.png"));
		jbtnExamMgtGrad = new JButton(new ImageIcon(profPath + "ExamMgtGrad.png"));
		jbtnStudMgt = new JButton(new ImageIcon(profPath + "StdntSlct.png"));
		jbtnStudySucc = new JButton(new ImageIcon(profPath + "StudySucc.png"));
		jbtnInfoUpda = new JButton(new ImageIcon(commonPath + "InfoUpda.png"));
		jbtnLogout = new JButton(new ImageIcon(commonPath + "logout.png"));
		jbtnSched = new JButton(new ImageIcon(commonPath + "Sched.png"));

		/////////////////////////////////////////////////
		// 요소에 추가선언
		Font font = new Font("나눔스퀘어라운드 ExtraBold", Font.BOLD, 17);
		topLogin.setFont(font);
		topLogin.setForeground(Color.WHITE);

		role.setFont(font);
		showId.setFont(font);
		name.setFont(font);
		dept.setFont(font);
		email.setFont(font);

		/////////////////////////////////////////////////

		// 요소 크기 및 위치 선언
		/////////////////////////////////////////////////

		back.setBounds(0, 0, 984, 620);// 성공

		role.setBounds(520, 171, 300, 20);
		showId.setBounds(520, 214, 300, 20);
		name.setBounds(520, 257, 300, 20);
		dept.setBounds(520, 300, 300, 20);
		email.setBounds(520, 343, 300, 20);
		topLogin.setBounds(600, 30, 300, 20);
		img.setBounds(270, 150, 198, 233);

		jbtnCourMgt.setBounds(200, 490, 120, 50);
		jbtnExamMgtGrad.setBounds(350, 490, 120, 50);
		jbtnStudMgt.setBounds(500, 490, 120, 50);
		jbtnStudySucc.setBounds(650, 490, 120, 50);
		jbtnInfoUpda.setBounds(435, 420, 100, 40);

		jbtnLogout.setBounds(870, 20, 100, 36);
		jbtnSched.setBounds(840, 90, 114, 47);

		// JFrame에 버튼 추가

		add(topLogin);
		add(role);
		add(img);
		add(showId);
		add(name);
		add(dept);
		add(email);
		add(jbtnCourMgt);
		add(jbtnExamMgtGrad);
		add(jbtnStudMgt);
		add(jbtnStudySucc);
		add(jbtnInfoUpda);

		add(jbtnLogout);
		add(jbtnSched);

		add(back);

		// AdminWorkEvent와 has a관계 설정

		ProfHomeEvent awe = new ProfHomeEvent(this);
		addWindowListener(awe);
		jbtnCourMgt.addActionListener(awe);
		jbtnExamMgtGrad.addActionListener(awe);
		jbtnStudMgt.addActionListener(awe);
		jbtnStudySucc.addActionListener(awe);
		jbtnInfoUpda.addActionListener(awe);
		jbtnLogout.addActionListener(awe);
		jbtnSched.addActionListener(awe);

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

	public JButton getJbtnCourMgt() {
		return jbtnCourMgt;
	}

	public JButton getJbtnExamMgtGrad() {
		return jbtnExamMgtGrad;
	}

	public JButton getJbtnStudMgt() {
		return jbtnStudMgt;
	}

	public JButton getJbtnStudySucc() {
		return jbtnStudySucc;
	}

	public JButton getJbtnInfoUpda() {
		return jbtnInfoUpda;
	}

	public JButton getJbtnLogout() {
		return jbtnLogout;
	}

	public JButton getJbtnSched() {
		return jbtnSched;
	}

}
