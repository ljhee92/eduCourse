package eduCourse_prj.professor;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.swing.*;

import eduCourse_prj.VO.LoginVO;



@SuppressWarnings({ "serial", "unused" })
public class ProfessorHomeDesign extends JFrame {
	private LoginVO lVO;

	private JButton jbtnCourMgt, jbtnExamMgtGrad, jbtnStudMgt, jbtnStudySucc, jbtnInfoUpda,
			jbtnLogout;

	JLabel img; // 사진 삽입 라벨
	JLabel role; // 권한 표시될 라벨
	JLabel showId; // id 표시될 라벨
	JLabel name; // 이름
	JLabel back ; // 배경사진 라벨
	JLabel topLogin ;// 우상단 로그인상태 확인창
	
	public ProfessorHomeDesign(LoginVO lVO) {
		super("관리자 작업창");

		this.lVO = lVO;

		setLayout(null); // 수동 배치를 위해 레이아웃 매니저를 null로 설정
		
		String commonPath = "C:/dev/workspace/eduCourse_prj/src/eduCourse_prj/image/common/";
		String profPath = "C:/dev/workspace/eduCourse_prj/src/eduCourse_prj/image/prof/";

		
		back = new JLabel(new ImageIcon(commonPath+"back.png"));
		role = new JLabel("권한 : 교수");
		showId = new JLabel("ID : " + lVO.getId());
		name = new JLabel("이름 : " + lVO.getName());

		topLogin = new JLabel(lVO.getName()+" 교수님 로그인 중");
		img = new JLabel(new ImageIcon(commonPath+"img.png"));
		
		
		jbtnCourMgt = new JButton(new ImageIcon(profPath+"CourMgt.png"));
		jbtnExamMgtGrad = new JButton(new ImageIcon(profPath+"ExamMgtGrad.png"));
		jbtnStudMgt = new JButton(new ImageIcon(profPath+"StudMgt.png"));
		jbtnStudySucc = new JButton(new ImageIcon(profPath+"StudySucc.png"));
		jbtnInfoUpda = new JButton(new ImageIcon(commonPath+"InfoUpda.png"));
		jbtnLogout = new JButton(new ImageIcon(commonPath+"logout.png"));



		/////////////////////////////////////////////////
		//요소에 추가선언
		Font font = new Font("나눔스퀘어라운드 ExtraBold",Font.BOLD,15);
		topLogin.setFont(font);
		topLogin.setForeground(Color.RED);
		
		role.setFont(font);
		showId.setFont(font);
		name.setFont(font);

		/////////////////////////////////////////////////
		
		
		
		
		//요소 크기 및 위치 선언
		/////////////////////////////////////////////////
		
		back.setBounds(0, 0, 984, 620);//성공
		
		role.setBounds(520,200,150,20);
		showId.setBounds(520,230,150,20);
		name.setBounds(520, 260, 150, 20);
		topLogin.setBounds(670,30,200,20);
		img.setBounds(310, 150, 160, 188);
		
		
		jbtnCourMgt.setBounds(200, 440, 120, 50);
		jbtnExamMgtGrad.setBounds(350, 440, 120, 50);
		jbtnStudMgt.setBounds(500, 440, 120, 50);
		jbtnStudySucc.setBounds(650, 440, 120, 50);
		jbtnInfoUpda.setBounds(420, 360, 100, 40);

		jbtnLogout.setBounds(870, 20, 100, 36);

		// JFrame에 버튼 추가
		
		add(topLogin);
		add(role);
		add(img);
		add(showId);
		add(name);
		add(jbtnCourMgt);
		add(jbtnExamMgtGrad);
		add(jbtnStudMgt);
		add(jbtnStudySucc);
		add(jbtnInfoUpda);

		add(jbtnLogout);
		
		add(back);

		
		//AdminWorkEvent와 has a관계 설정
		
		ProfessorHomeEvent awe = new ProfessorHomeEvent(this);
		addWindowListener(awe);
		jbtnCourMgt.addActionListener(awe);
		jbtnExamMgtGrad.addActionListener(awe);
		jbtnStudMgt.addActionListener(awe);
		jbtnStudySucc.addActionListener(awe);
		jbtnInfoUpda.addActionListener(awe);
		jbtnLogout.addActionListener(awe);

		
		

		setSize(1000, 650); //+16 +30 

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




}
