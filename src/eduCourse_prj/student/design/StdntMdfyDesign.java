package eduCourse_prj.student.design;

import java.awt.Color;
import java.awt.Font;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import eduCourse_prj.VO.StdntVO;

import eduCourse_prj.student.dao.StdntDAO;
import eduCourse_prj.student.event.StdntMdfyEvent;

@SuppressWarnings("serial")
public class StdntMdfyDesign extends JDialog {

	private StdntHomeDesign shd;
	private JLabel jlBack; // 배경
	private JLabel topLogin; // 우상단 로그인상태 확인창
	private JLabel jlStdntMdfy, photo, jlStdntNum, jlStdntPass, jlStdntEmail, jlStdntAddr, jlNecessary;
	private JTextField jtfStdntNum, jtfStdntEmail, jtfStdntAddr;
	private JPasswordField jpfStdntPass;
	private JButton jbtnMdfy, jbtnCancel;

	public StdntMdfyDesign(StdntHomeDesign shd, String title) {
		super(shd, title, true);
		this.shd = shd;

		setLayout(null);

		String commonPath = "src/eduCourse_prj/image/common/";

		// 우상단 로그인상태 확인창 추가
		topLogin = new JLabel(shd.getlVO().getName() + " 학생님 로그인 중");
		Font font = new Font("나눔스퀘어라운드 ExtraBold", Font.BOLD, 17);
		topLogin.setFont(font);
		topLogin.setForeground(Color.WHITE);
		topLogin.setBounds(670, 30, 200, 20);
		add(topLogin);

		// 정보수정 라벨 추가
		jlStdntMdfy = new JLabel(new ImageIcon(commonPath + "InfoMdfy_label.png"));
		jlStdntMdfy.setBounds(10, 76, 967, 44);
		add(jlStdntMdfy);

		// 프로필 사진 추가
		photo = new JLabel(new ImageIcon(commonPath + "photo.png"));
		photo.setBounds(160, 200, 198, 233);
		add(photo);

		// 학번, PW, 이메일, 주소 필수입력 라벨 추가
		jlStdntNum = new JLabel("학번");
		jlStdntPass = new JLabel("PW");
		jlStdntEmail = new JLabel("이메일");
		jlStdntAddr = new JLabel("주소");
		jlNecessary = new JLabel("PW는 필수 입력사항입니다.");

		jlStdntNum.setBounds(410, 230, 100, 30);
		jlStdntPass.setBounds(410, 270, 100, 30);
		jlStdntEmail.setBounds(410, 310, 100, 30);
		jlStdntAddr.setBounds(410, 350, 100, 30);
		jlNecessary.setBounds(600, 400, 300, 30);

		jlStdntNum.setFont(font);
		jlStdntPass.setFont(font);
		jlStdntEmail.setFont(font);
		jlStdntAddr.setFont(font);
		jlNecessary.setFont(font);
		jlNecessary.setForeground(Color.RED);

		add(jlStdntNum);
		add(jlStdntPass);
		add(jlStdntEmail);
		add(jlStdntAddr);
		add(jlNecessary);

		// 학번, PW, 이메일, 주소 JTF, JPF 추가
		jtfStdntNum = new JTextField(shd.getlVO().getId());

		StdntDAO sDAO = StdntDAO.getInstance();
		try {
			StdntVO sVO = sDAO.slctOneStdnt(Integer.parseInt(shd.getlVO().getId()));

			jpfStdntPass = new JPasswordField(sVO.getStdnt_password());
			jtfStdntEmail = new JTextField(sVO.getStdnt_email());
			jtfStdntAddr = new JTextField(sVO.getStdnt_addr());

		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} // end catch

		jtfStdntNum.setEditable(false);

		jtfStdntNum.setBounds(510, 230, 300, 30);
		jpfStdntPass.setBounds(510, 270, 300, 30);
		jtfStdntEmail.setBounds(510, 310, 300, 30);
		jtfStdntAddr.setBounds(510, 350, 300, 30);
		
		jtfStdntNum.setFont(font);
		jtfStdntEmail.setFont(font);
		jtfStdntAddr.setFont(font);

		add(jtfStdntNum);
		add(jpfStdntPass);
		add(jtfStdntEmail);
		add(jtfStdntAddr);

		// 수정, 취소 버튼 추가
		jbtnMdfy = new JButton(new ImageIcon(commonPath + "Mdfy.png"));
		jbtnCancel = new JButton(new ImageIcon(commonPath + "Cancel.png"));

		jbtnMdfy.setBounds(345, 490, 111, 59);
		jbtnCancel.setBounds(530, 490, 111, 59);

		add(jbtnMdfy);
		add(jbtnCancel);

		// 이벤트 클래스 연결
		StdntMdfyEvent sme = new StdntMdfyEvent(this);
		addWindowListener(sme);
		jbtnMdfy.addActionListener(sme);
		jbtnCancel.addActionListener(sme);

		// 배경 추가
		jlBack = new JLabel(new ImageIcon(commonPath + "Back.png"));
		jlBack.setBounds(0, 0, 984, 620);
		add(jlBack);
		setSize(1000, 650);
		setLocationRelativeTo(null);
		// 프레임크기 조절 불가
		setResizable(false);
		setVisible(true);
	} // AdminProfMgtRegDesign

	public JButton getJbtnMdfy() {
		return jbtnMdfy;
	}

	public JButton getJbtnCancel() {
		return jbtnCancel;
	}

	public JTextField getJtfStdntNum() {
		return jtfStdntNum;
	}

	public JTextField getJtfStdntEmail() {
		return jtfStdntEmail;
	}

	public JTextField getJtfStdntAddr() {
		return jtfStdntAddr;
	}

	public JPasswordField getJpfStdntPass() {
		return jpfStdntPass;
	}

	public StdntHomeDesign getShd() {
		return shd;
	}

} // class