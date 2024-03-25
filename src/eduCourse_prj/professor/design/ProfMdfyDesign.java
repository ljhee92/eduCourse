package eduCourse_prj.professor.design;

import java.awt.Color;
import java.awt.Font;
import java.sql.SQLException;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import eduCourse_prj.VO.DeptVO;
import eduCourse_prj.VO.ProfVO;
import eduCourse_prj.admin.dao.AdminDAO;
import eduCourse_prj.admin.event.AdminProfMgtMdfyEvent;
import eduCourse_prj.professor.dao.ProfDAO;
import eduCourse_prj.professor.event.ProfMdfyEvent;

public class ProfMdfyDesign extends JDialog {

	private ProfHomeDesign phd;
	private JLabel jlBack; // 배경
	private JLabel topLogin; // 우상단 로그인상태 확인창
	private JLabel ProfMdfy, photo, jlProfNum, jlProfPass, jlProfEmail, jlNecessary;
	private JTextField jtfProfNum, jtfProfEmail, jtfProfAddr;
	private JPasswordField jpfProfPass;
	private JButton jbtnMdfy, jbtnCancel;

	public ProfMdfyDesign(ProfHomeDesign phd, String title) {
		super(phd, title, true);
		this.phd = phd;

		setLayout(null);

		String commonPath = "src/eduCourse_prj/image/common/";

		// 우상단 로그인상태 확인창 추가
		topLogin = new JLabel(phd.getlVO().getName() + " 학생님 로그인 중");
		Font font = new Font("나눔스퀘어라운드 ExtraBold", Font.BOLD, 15);
		topLogin.setFont(font);
		topLogin.setForeground(Color.WHITE);
		topLogin.setBounds(670, 30, 200, 20);
		add(topLogin);

		// 정보수정 라벨 추가
		ProfMdfy = new JLabel(new ImageIcon(commonPath + "InfoMdfy_label.png"));
		ProfMdfy.setBounds(10, 76, 967, 44);
		add(ProfMdfy);

		// 프로필 사진 추가
		photo = new JLabel(new ImageIcon(commonPath + "photo.png"));
		photo.setBounds(160, 200, 198, 233);
		add(photo);

		// 학번, PW, 이메일, 주소 필수입력 라벨 추가
		jlProfNum = new JLabel("학번");
		jlProfPass = new JLabel("PW");
		jlProfEmail = new JLabel("이메일");

		jlNecessary = new JLabel("PW는 필수 입력사항입니다.");

		jlProfNum.setBounds(410, 230, 100, 30);
		jlProfPass.setBounds(410, 270, 100, 30);
		jlProfEmail.setBounds(410, 310, 100, 30);

		jlNecessary.setBounds(600, 400, 300, 30);

		jlProfNum.setFont(font);
		jlProfPass.setFont(font);
		jlProfEmail.setFont(font);

		jlNecessary.setFont(font);
		jlNecessary.setForeground(Color.RED);

		add(jlProfNum);
		add(jlProfPass);
		add(jlProfEmail);
		add(jlNecessary);

		// 학번, PW, 이메일, 주소 JTF, JPF 추가
		jtfProfNum = new JTextField(phd.getlVO().getId());

		ProfDAO pDAO = ProfDAO.getInstance();
		try {
			// System.out.println(" test : " +phd.getlVO().getId());
			ProfVO pVO = pDAO.slctOneProf(Integer.parseInt(phd.getlVO().getId()));

			jpfProfPass = new JPasswordField(pVO.getProf_password());
			jtfProfEmail = new JTextField(pVO.getProf_email());

		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} // end catch

		jtfProfNum.setEditable(false);

		jtfProfNum.setBounds(510, 230, 300, 30);
		jpfProfPass.setBounds(510, 270, 300, 30);
		jtfProfEmail.setBounds(510, 310, 300, 30);

		add(jtfProfNum);
		add(jpfProfPass);
		add(jtfProfEmail);

		// 수정, 취소 버튼 추가
		jbtnMdfy = new JButton(new ImageIcon(commonPath + "Mdfy.png"));
		jbtnCancel = new JButton(new ImageIcon(commonPath + "Cancel.png"));

		jbtnMdfy.setBounds(345, 490, 111, 59);
		jbtnCancel.setBounds(530, 490, 111, 59);

		add(jbtnMdfy);
		add(jbtnCancel);

		// 이벤트 클래스 연결
		ProfMdfyEvent sme = new ProfMdfyEvent(this);
		addWindowListener(sme);
		jbtnMdfy.addActionListener(sme);
		jbtnCancel.addActionListener(sme);

		// 배경 추가
		jlBack = new JLabel(new ImageIcon(commonPath + "Back.png"));
		jlBack.setBounds(0, 0, 984, 620);
		add(jlBack);
		setSize(1000, 650);
		setLocationRelativeTo(null);
		setVisible(true);
	} // AdminProfMgtRegDesign

	public ProfHomeDesign getPhd() {
		return phd;
	}

	public JLabel getJlBack() {
		return jlBack;
	}

	public JLabel getTopLogin() {
		return topLogin;
	}

	public JLabel getProfMdfy() {
		return ProfMdfy;
	}

	public JLabel getPhoto() {
		return photo;
	}

	public JLabel getJlProfNum() {
		return jlProfNum;
	}

	public JLabel getJlProfPass() {
		return jlProfPass;
	}

	public JLabel getJlProfEmail() {
		return jlProfEmail;
	}

	public JLabel getJlNecessary() {
		return jlNecessary;
	}

	public JTextField getJtfProfNum() {
		return jtfProfNum;
	}

	public JTextField getJtfProfEmail() {
		return jtfProfEmail;
	}

	public JTextField getJtfProfAddr() {
		return jtfProfAddr;
	}

	public JPasswordField getJpfProfPass() {
		return jpfProfPass;
	}

	public JButton getJbtnMdfy() {
		return jbtnMdfy;
	}

	public JButton getJbtnCancel() {
		return jbtnCancel;
	}

} // class