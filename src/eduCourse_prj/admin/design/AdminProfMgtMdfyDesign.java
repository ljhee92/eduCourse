package eduCourse_prj.admin.design;

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
import eduCourse_prj.admin.dao.AdminDAO;
import eduCourse_prj.admin.event.AdminProfMgtMdfyEvent;

@SuppressWarnings("serial")
public class AdminProfMgtMdfyDesign extends JDialog {
	private AdminProfMgtDesign apmd;
	private JLabel jlBack; //배경
	private JLabel topLogin; // 우상단 로그인상태 확인창
	private JLabel profMgt, profMgtMdfy, photo, jlProfNum, jlProfName, jlProfPass, jlProfEmail, jlDept, jlNecessary;
	private JTextField jtfProfNum, jtfProfName, jtfProfEmail;
	private JPasswordField jpfProfPass;
	private JComboBox<String> jcbDept;
	private JButton jbtnMdfy, jbtnCancel;
	
	public JLabel getJlBack() {
		return jlBack;
	}

	public JLabel getTopLogin() {
		return topLogin;
	}

	public JLabel getProfMgt() {
		return profMgt;
	}

	public JLabel getProfMgtMdfy() {
		return profMgtMdfy;
	}

	public JLabel getPhoto() {
		return photo;
	}

	public JLabel getJlProfNum() {
		return jlProfNum;
	}

	public JLabel getJlProfName() {
		return jlProfName;
	}

	public JLabel getJlProfPass() {
		return jlProfPass;
	}

	public JLabel getJlProfEmail() {
		return jlProfEmail;
	}

	public JLabel getJlDept() {
		return jlDept;
	}

	public JLabel getJlNecessary() {
		return jlNecessary;
	}

	public AdminProfMgtMdfyDesign(AdminProfMgtDesign apmd, String title) {
		super(apmd, title, true);
		this.apmd = apmd;
		setLayout(null);
		
		String commonPath = "src/eduCourse_prj/image/common/";
		String profPath = "src/eduCourse_prj/image/prof/";
		
		// 우상단 로그인상태 확인창 추가
		topLogin = new JLabel(apmd.getAwd().getlVO().getName() + " 관리자님 로그인 중");
		Font font = new Font("나눔스퀘어라운드 ExtraBold", Font.BOLD, 15);
		topLogin.setFont(font);
		topLogin.setForeground(Color.WHITE);
		topLogin.setBounds(670, 30, 200, 20);
		add(topLogin);
		
		// 교수관리, 수정 라벨 추가
		profMgt = new JLabel(new ImageIcon(profPath + "ProfMgtBanner_new.png"));
		profMgtMdfy = new JLabel(new ImageIcon(commonPath + "MdfyBanner_new.png"));
		profMgt.setBounds(10, 76, 967, 44);
		profMgtMdfy.setBounds(10, 120, 967, 44);
		add(profMgt);
		add(profMgtMdfy);
		
		// 프로필 사진 추가
		photo = new JLabel(new ImageIcon(commonPath + "photo.png"));
		photo.setBounds(160, 200, 198, 233);
		add(photo);
		
		// 교번, 이름, PW, 이메일, 소속학과, 필수입력 라벨 추가
		jlProfNum = new JLabel("교번");
		jlProfName = new JLabel("이름");
		jlProfPass = new JLabel("PW");
		jlProfEmail = new JLabel("이메일");
		jlDept = new JLabel("소속학과");
		jlNecessary = new JLabel("이름, PW는 필수 입력사항입니다.");
		
		jlProfNum.setBounds(410, 230, 100, 30);
		jlProfName.setBounds(410, 270, 100, 30);
		jlProfPass.setBounds(410, 310, 100, 30);
		jlProfEmail.setBounds(410, 350, 100, 30);
		jlDept.setBounds(410, 390, 100, 30);
		jlNecessary.setBounds(670, 420, 300, 30);
		
		jlProfNum.setFont(font);
		jlProfName.setFont(font);
		jlProfPass.setFont(font);
		jlProfEmail.setFont(font);
		jlDept.setFont(font);
		Font sfont = new Font("나눔스퀘어라운드 ExtraBold", Font.BOLD, 10);
		jlNecessary.setFont(sfont);
		jlNecessary.setForeground(Color.RED);
		
		add(jlProfNum);
		add(jlProfName);
		add(jlProfPass);
		add(jlProfEmail);
		add(jlDept);
		add(jlNecessary);
		
		// JTable에서 선택된 교번, 이름 가져오기
		int index = apmd.getJtbProfMgt().getSelectedRow();
		String prof_number = apmd.getDtmProfMgt().getValueAt(index, 1).toString();
		String prof_name = apmd.getDtmProfMgt().getValueAt(index, 2).toString();

		// 교번, 이름, PW, 이메일, 소속학과 JTF, JPF, ComboBox 추가
		jtfProfNum = new JTextField(prof_number);
		jtfProfName = new JTextField(prof_name);
		jpfProfPass = new JPasswordField();
		jtfProfEmail = new JTextField();
		
		jcbDept = new JComboBox<String>();
		AdminDAO aDAO = AdminDAO.getInstance();
		try {
			List<DeptVO> listDVO = aDAO.slctAllDept();
			String deptName;
			for(DeptVO dVO : listDVO) {
				deptName = dVO.getDept_name();
				jcbDept.addItem(deptName);
			} // end for
		} catch (SQLException e) {
			e.printStackTrace();
		} // end catch
		
		jtfProfNum.setEditable(false);

		jtfProfNum.setBounds(510, 230, 300, 30);
		jtfProfName.setBounds(510, 270, 300, 30);
		jpfProfPass.setBounds(510, 310, 300, 30);
		jtfProfEmail.setBounds(510, 350, 300, 30);
		jcbDept.setBounds(510, 390, 300, 30);
		
		add(jtfProfNum);
		add(jtfProfName);
		add(jpfProfPass);
		add(jtfProfEmail);
		add(jcbDept);
		
		// 수정, 취소 버튼 추가
		jbtnMdfy = new JButton(new ImageIcon(commonPath + "Mdfy.png"));
		jbtnCancel = new JButton(new ImageIcon(commonPath + "Cancel.png"));
		
		jbtnMdfy.setBounds(345, 490, 111, 59);
		jbtnCancel.setBounds(530, 490, 111, 59);
		
		add(jbtnMdfy);
		add(jbtnCancel);
				
		// 이벤트 클래스 연결
		AdminProfMgtMdfyEvent apmme = new AdminProfMgtMdfyEvent(this);
		addWindowListener(apmme);
		jbtnMdfy.addActionListener(apmme);
		jbtnCancel.addActionListener(apmme);
		
		// 배경 추가
        jlBack = new JLabel(new ImageIcon(commonPath + "Back.png"));
        jlBack.setBounds(0,0,984,620);
        add(jlBack);
		setSize(1000,650);
		setLocationRelativeTo(null);
		setVisible(true);
	} // AdminProfMgtRegDesign

	public JButton getJbtnMdfy() {
		return jbtnMdfy;
	}

	public JButton getJbtnCancel() {
		return jbtnCancel;
	}

	public JTextField getJtfProfNum() {
		return jtfProfNum;
	}

	public JTextField getJtfProfName() {
		return jtfProfName;
	}

	public JTextField getJtfProfEmail() {
		return jtfProfEmail;
	}

	public JPasswordField getJpfProfPass() {
		return jpfProfPass;
	}

	public JComboBox<String> getJcbDept() {
		return jcbDept;
	}

	public AdminProfMgtDesign getApmd() {
		return apmd;
	}
	
	

} // class