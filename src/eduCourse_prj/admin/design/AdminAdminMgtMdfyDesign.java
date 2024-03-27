package eduCourse_prj.admin.design;

import java.awt.Color;
import java.awt.Font;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import eduCourse_prj.VO.AdminVO;
import eduCourse_prj.admin.dao.AdminDAO;
import eduCourse_prj.admin.event.AdminAdminMgtMdfyEvent;

@SuppressWarnings("serial")
public class AdminAdminMgtMdfyDesign extends JDialog {
	private AdminAdminMgtDesign aamd;
	private AdminDAO aDAO = AdminDAO.getInstance();

	private JLabel jlBack; // 배경
	private JLabel topLogin; // 우상단 로그인상태 확인창
	private JLabel adminMgt, adminMgtMdfy, photo, jlAdminId, jlAdminName, jlAdminPass, jlNecessary;

	private JTextField jtfAdminId, jtfAdminName;
	private JPasswordField jpfAdminPass;
	private JButton jbtnMdfy, jbtnCancel;

	public AdminAdminMgtMdfyDesign(AdminAdminMgtDesign aamd, String title) {
		super(aamd, title, true);
		this.aamd = aamd;
		setLayout(null);

		String commonPath = "src/eduCourse_prj/image/common/";
		String adminPath = "src/eduCourse_prj/image/admin/";

		// 우상단 로그인상태 확인창 추가

		topLogin = new JLabel(aamd.getAhd().getlVO().getName() + " 관리자님 로그인 중");
		Font font = new Font("나눔스퀘어라운드 ExtraBold", Font.BOLD, 17);

		topLogin.setFont(font);
		topLogin.setForeground(Color.WHITE);
		topLogin.setBounds(670, 30, 200, 20);
		add(topLogin);

		// 관리자관리, 등록 라벨 추가
		adminMgt = new JLabel(new ImageIcon(adminPath + "adminMgtBanner_new.png"));
		adminMgt.setBounds(10, 76, 967, 44);
		add(adminMgt);

		// 관리자관리, 수정 라벨 추가
		adminMgt = new JLabel(new ImageIcon(adminPath + "AdminMgt.png"));
		adminMgtMdfy = new JLabel(new ImageIcon(commonPath + "Mdfy_label_new.png"));
		adminMgt.setBounds(10, 76, 967, 44);
		adminMgtMdfy.setBounds(10, 120, 967, 44);
		add(adminMgt);
		add(adminMgtMdfy);

		// 프로필 사진 추가
		photo = new JLabel(new ImageIcon(commonPath + "photo.png"));
		photo.setBounds(200, 210, 198, 233);
		add(photo);

		// 교번, 이름, PW, 이메일, 소속학과, 필수입력 라벨 추가
		jlAdminId = new JLabel("아이디");
		jlAdminName = new JLabel("이름");
		jlAdminPass = new JLabel("PW");

		jlNecessary = new JLabel("이름, PW는 필수 입력사항입니다.");

		jlAdminId.setBounds(450, 255, 100, 30);
		jlAdminName.setBounds(450, 310, 100, 30);
		jlAdminPass.setBounds(450, 365, 100, 30);

		jlNecessary.setBounds(680, 390, 300, 30);

		// 폰트 설정
		Font labelFont = new Font("나눔스퀘어라운드 ExtraBold", Font.PLAIN, 20);
		jlAdminId.setFont(labelFont);
		jlAdminName.setFont(labelFont);
		jlAdminPass.setFont(labelFont);
		Font smallFont = new Font("나눔스퀘어라운드 ExtraBold", Font.PLAIN, 10);
		jlNecessary.setFont(smallFont);
		jlNecessary.setForeground(Color.RED);

		add(jlAdminId);
		add(jlAdminName);
		add(jlAdminPass);
		add(jlNecessary);

		// JTable에서 선택된 id, 이름 가져오기
		int index = aamd.getJtbAdminMgt().getSelectedRow();
		String admin_id = aamd.getDtmAdminMgt().getValueAt(index, 0).toString();
		AdminVO aVO = null;
		try {
			aVO = aDAO.selectAdmin(admin_id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// 아이디, 이름, PW, JTF, JPF추가

		jtfAdminId = new JTextField(aVO.getAdmin_id());
		jtfAdminName = new JTextField(aVO.getAdmin_name());
		jpfAdminPass = new JPasswordField(aVO.getAdmin_password());

		jtfAdminId.setEditable(false);

		jtfAdminId.setBounds(520, 255, 300, 30);
		jtfAdminName.setBounds(520, 310, 300, 30);
		jpfAdminPass.setBounds(520, 365, 300, 30);

		add(jtfAdminId);
		add(jtfAdminName);
		add(jpfAdminPass);

		// 수정, 취소 버튼 추가
		jbtnMdfy = new JButton(new ImageIcon(commonPath + "Mdfy.png"));
		jbtnCancel = new JButton(new ImageIcon(commonPath + "Cancel.png"));

		jbtnMdfy.setBounds(345, 490, 111, 59);
		jbtnCancel.setBounds(530, 490, 111, 59);

		add(jbtnMdfy);
		add(jbtnCancel);

		// 이벤트 클래스 연결
		AdminAdminMgtMdfyEvent apmme = new AdminAdminMgtMdfyEvent(this);
		addWindowListener(apmme);
		jbtnMdfy.addActionListener(apmme);
		jbtnCancel.addActionListener(apmme);

		// 배경 추가
		jlBack = new JLabel(new ImageIcon(commonPath + "Back.png"));
		jlBack.setBounds(0, 0, 984, 620);
		add(jlBack);
		setSize(1000, 650);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
	} // AdminAdminMgtRegDesign

	public JLabel getJlBack() {
		return jlBack;
	}

	public JLabel getTopLogin() {
		return topLogin;
	}

	public JLabel getAdminMgt() {
		return adminMgt;
	}

	public JLabel getAdminMgtMdfy() {
		return adminMgtMdfy;
	}

	public JLabel getPhoto() {
		return photo;
	}

	public JLabel getJlAdminId() {
		return jlAdminId;
	}

	public JLabel getJlAdminName() {
		return jlAdminName;
	}

	public JLabel getJlAdminPass() {
		return jlAdminPass;
	}

	public JLabel getJlNecessary() {
		return jlNecessary;
	}

	public JTextField getJtfAdminId() {
		return jtfAdminId;
	}

	public JTextField getJtfAdminName() {
		return jtfAdminName;
	}

	public JPasswordField getJpfAdminPass() {
		return jpfAdminPass;
	}

	public JButton getJbtnMdfy() {
		return jbtnMdfy;
	}

	public JButton getJbtnCancel() {
		return jbtnCancel;
	}

	public AdminAdminMgtDesign getAamd() {
		return aamd;
	}

}
