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
import javax.swing.JTextField;

import eduCourse_prj.VO.DeptVO;
import eduCourse_prj.VO.ProfVO;
import eduCourse_prj.admin.dao.AdminDAO;
import eduCourse_prj.admin.event.AdminCrsRegEvent;
import eduCourse_prj.professor.dao.ProfDAO;

@SuppressWarnings("serial")
public class AdminCrsRegDisgn extends JDialog {
	private AdminCrsDesign acd;
	private AdminDAO aDAO = AdminDAO.getInstance();
	private ProfDAO pDAO = ProfDAO.getInstance();

	private List<DeptVO> lDept;
	private List<ProfVO> lProf;

	private JComboBox<String> jcbDept, jcbProf;

	private JTextField jtfCrsName, jtfCrsCode, jtfCredit, jtfProfName;

	private JButton jbtnRegister, jbtnCancel;

	private JLabel topLogin; // 우상단 로그인상태 확인창

	private JLabel jlBack, jlCrsMgtTitle, jlCrsReg;

	private JLabel jlDept, jlCrs, jlCrsCode, jlCredit, jlNecessary;

	public AdminCrsRegDisgn(AdminCrsDesign acd, String title) {
		super(acd, title, true);
		this.acd = acd;

		// 공통경로
		String commonPath = "src/eduCourse_prj/image/common/";

		String adminPath = "src/eduCourse_prj/image/admin/";

		Font font = new Font("나눔스퀘어라운드 ExtraBold", Font.BOLD, 17);

		setSize(1000, 650);
		setLayout(null);
		setLocationRelativeTo(null);

		jlDept = new JLabel("학과");
		jlDept.setFont(font);
		jlDept.setBounds(350, 220, 100, 30);
		add(jlDept);

		jcbDept = new JComboBox<>();
		try {

			// 모든 학과 정보 가져오기
			lDept = aDAO.slctAllDept();

			// 학과명만 저장하는 리스트에 학과명 저장
			for (DeptVO dept : lDept) {
				jcbDept.addItem(dept.getDept_name());

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		// 우상단 로그인상태 확인창 추가
		topLogin = new JLabel(acd.getAhd().getlVO().getName() + " 관리자님 로그인 중");
		topLogin.setFont(font);
		topLogin.setForeground(Color.WHITE);
		topLogin.setBounds(620, 30, 300, 20);
		add(topLogin);

		jcbDept.setBounds(430, 220, 200, 30);

		jcbDept.setFont(font);
		add(jcbDept);

		jlCrs = new JLabel("과목");
		jlCrs.setFont(font);
		jlCrs.setBounds(350, 270, 100, 30);
		add(jlCrs);

		jtfCrsName = new JTextField();
		jtfCrsName.setFont(font);
		jtfCrsName.setBounds(430, 270, 200, 30);
		add(jtfCrsName);

		jlCrsCode = new JLabel("과목코드");
		jlCrsCode.setFont(font);
		jlCrsCode.setBounds(350, 320, 100, 30);
		add(jlCrsCode);

		jtfCrsCode = new JTextField();
		jtfCrsCode.setFont(font);
		jtfCrsCode.setBounds(430, 320, 200, 30);
		add(jtfCrsCode);

		jlCredit = new JLabel("학점");
		jlCredit.setFont(font);
		jlCredit.setBounds(350, 370, 100, 30);
		add(jlCredit);

		jtfCredit = new JTextField();
		jtfCredit.setFont(font);
		jtfCredit.setBounds(430, 370, 200, 30);
		add(jtfCredit);

		jbtnRegister = new JButton(new ImageIcon(commonPath + "Reg.png"));
		jbtnRegister.setFont(font);
		jbtnRegister.setBounds(345, 490, 111, 59);
		add(jbtnRegister);

		jbtnCancel = new JButton(new ImageIcon(commonPath + "Cancel.png"));
		jbtnCancel.setFont(font);
		jbtnCancel.setBounds(530, 490, 111, 59);
		add(jbtnCancel);

		Font sfont = new Font("나눔스퀘어라운드 ExtraBold", Font.BOLD, 13);
		jlNecessary = new JLabel("과목, 과목코드, 학점은 필수 입력사항입니다.");
		jlNecessary.setFont(sfont);
		jlNecessary.setForeground(Color.RED);
		jlNecessary.setBounds(430, 400, 300, 30);
		add(jlNecessary);

		// 배경 추가

		jlCrsMgtTitle = new JLabel(new ImageIcon(adminPath + "CourMgtBanner_new.png"));

		jlCrsMgtTitle.setBounds(10, 76, 967, 44);
		add(jlCrsMgtTitle);

		jlCrsReg = new JLabel(new ImageIcon(commonPath + "RegBanner_new.png"));
		jlCrsReg.setBounds(10, 120, 967, 44);
		add(jlCrsReg);

		jlBack = new JLabel(new ImageIcon(commonPath + "Back.png"));
		jlBack.setBounds(0, 0, 984, 620);
		add(jlBack); // 배경

		AdminCrsRegEvent acre = new AdminCrsRegEvent(this);
		addWindowListener(acre);
		jcbDept.addActionListener(acre);
		jbtnRegister.addActionListener(acre);
		jbtnCancel.addActionListener(acre);

		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public AdminCrsDesign getAcd() {
		return acd;
	}

	public AdminDAO getaDAO() {
		return aDAO;
	}

	public ProfDAO getpDAO() {
		return pDAO;
	}

	public List<DeptVO> getlDept() {
		return lDept;
	}

	public List<ProfVO> getlProf() {
		return lProf;
	}

	public JComboBox<String> getJcbDept() {
		return jcbDept;
	}

	public JComboBox<String> getJcbProf() {
		return jcbProf;
	}

	public JTextField getJtfCrsName() {
		return jtfCrsName;
	}

	public JTextField getJtfCrsCode() {
		return jtfCrsCode;
	}

	public JTextField getJtfCredit() {
		return jtfCredit;
	}

	public JTextField getJtfProfName() {
		return jtfProfName;
	}

	public JButton getJbtnRegister() {
		return jbtnRegister;
	}

	public JButton getJbtnCancel() {
		return jbtnCancel;
	}

}