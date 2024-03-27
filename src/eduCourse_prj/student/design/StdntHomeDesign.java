package eduCourse_prj.student.design;

import java.awt.Color;
import java.awt.Font;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import eduCourse_prj.VO.LoginVO;
import eduCourse_prj.VO.StdntVO;
import eduCourse_prj.student.dao.StdntDAO;
import eduCourse_prj.student.event.StdntHomeEvent;



@SuppressWarnings({ "serial", "unused" })
public class StdntHomeDesign extends JFrame {
	private LoginVO lVO;

	private JButton jbtnEnrollCour, jbtnEnrollHist, jbtnExamAttendResu, jbtnJbtnExamCorr, jbtnInfoUpda,
			jbtnLogout,jbtnSched;

	JLabel img; // 사진 삽입 라벨
	JLabel role; // 권한 표시될 라벨
	JLabel showId; // id 표시될 라벨
	JLabel name ;// 이름
	JLabel back ; // 배경사진 라벨
	JLabel topLogin ;// 우상단 로그인상태 확인창
	JLabel dept, email, addr;
	
	public StdntHomeDesign(LoginVO lVO) {
		super("학생");

		this.lVO = lVO;

		setLayout(null); // 수동 배치를 위해 레이아웃 매니저를 null로 설정

		String commonPath = "src/eduCourse_prj/image/common/";
		String studPath = "src/eduCourse_prj/image/stud/";
		
		back = new JLabel(new ImageIcon(commonPath+"back.png"));
		role = new JLabel("권한 : 학생");
		showId = new JLabel("학번 : " + lVO.getId());
		name = new JLabel("이름 : " +lVO.getName());
		
		StdntDAO sDAO = StdntDAO.getInstance();
		StdntVO sVO = null;
		
		try {
			sVO = sDAO.slctOneStdnt(Integer.parseInt(lVO.getId()));
			dept = new JLabel("학과 : " + sVO.getDept_name());
			email = new JLabel("이메일 : " + sVO.getStdnt_email());
			addr = new JLabel("주소 : " + sVO.getStdnt_addr());
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} // end catch
		
		topLogin = new JLabel(lVO.getName()+" 학생님 로그인 중");
		img = new JLabel(new ImageIcon(commonPath+"img.png"));
		
		
		jbtnEnrollCour = new JButton(new ImageIcon(studPath+"EnrollCour.png"));
		jbtnEnrollHist = new JButton(new ImageIcon(studPath+"EnrollHist.png"));
		jbtnExamAttendResu = new JButton(new ImageIcon(studPath+"ExamAttendResu.png"));
		jbtnJbtnExamCorr = new JButton(new ImageIcon(studPath+"JbtnExamCorr.png"));
		jbtnInfoUpda = new JButton(new ImageIcon(commonPath+"InfoUpda.png"));
		jbtnLogout = new JButton(new ImageIcon(commonPath+"Logout.png"));
		jbtnSched = new JButton(new ImageIcon(commonPath+"Sched.png"));



		/////////////////////////////////////////////////
		//요소에 추가선언
		Font font = new Font("나눔스퀘어라운드 ExtraBold",Font.BOLD,15);
		topLogin.setFont(font);
		topLogin.setForeground(Color.WHITE);
		
		role.setFont(font);
		showId.setFont(font);
		name.setFont(font);
		dept.setFont(font);
		email.setFont(font);
		addr.setFont(font);

		/////////////////////////////////////////////////
		
		
		
		
		//요소 크기 및 위치 선언
		/////////////////////////////////////////////////
		
		back.setBounds(0, 0, 984, 620);//성공
		
		role.setBounds(520,150,300,20);
		showId.setBounds(520,180,300,20);
		name.setBounds(520,210, 300,20);
		dept.setBounds(520, 240, 300, 20);
		email.setBounds(520, 270, 300, 20);
		addr.setBounds(520, 300, 300, 20);
		topLogin.setBounds(670,30,200,20);
		img.setBounds(310, 150, 160, 188);
		jbtnSched.setBounds(840,90,114,47);
		
		
		jbtnEnrollCour.setBounds(200, 440, 120, 50);
		jbtnEnrollHist.setBounds(350, 440, 120, 50);
		jbtnExamAttendResu.setBounds(500, 440, 120, 50);
		jbtnJbtnExamCorr.setBounds(650, 440, 120, 50);
		jbtnInfoUpda.setBounds(420, 360, 100, 40);
	
		jbtnLogout.setBounds(870, 20, 100, 36);


		// JFrame에 버튼 추가
		
		add(topLogin);
		add(role);
		add(img);
		add(showId);
		add(name);
		add(dept);
		add(email);
		add(addr);
		add(jbtnEnrollCour);
		add(jbtnEnrollHist);
		add(jbtnExamAttendResu);
		add(jbtnJbtnExamCorr);
		add(jbtnInfoUpda);

		add(jbtnLogout);
		add(jbtnSched);
		
		add(back);

		
		//AdminWorkEvent와 has a관계 설정
		
		StdntHomeEvent awe = new StdntHomeEvent(this);
		addWindowListener(awe);
		jbtnEnrollCour.addActionListener(awe);
		jbtnEnrollHist.addActionListener(awe);
		jbtnExamAttendResu.addActionListener(awe);
		jbtnJbtnExamCorr.addActionListener(awe);
		jbtnInfoUpda.addActionListener(awe);

		jbtnLogout.addActionListener(awe);
		jbtnSched.addActionListener(awe);

		
		

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



	public JButton getJbtnEnrollCour() {
		return jbtnEnrollCour;
	}



	public JButton getJbtnEnrollHist() {
		return jbtnEnrollHist;
	}



	public JButton getJbtnExamAttendResu() {
		return jbtnExamAttendResu;
	}



	public JButton getJbtnJbtnExamCorr() {
		return jbtnJbtnExamCorr;
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



	public void setJbtnSched(JButton jbtnSched) {
		this.jbtnSched = jbtnSched;
	}






}
