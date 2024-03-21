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
	AdminCrsDesign acd;
	AdminDAO aDAO = AdminDAO.getInstance();
	ProfDAO pDAO = ProfDAO.getInstance();

	List<DeptVO> lDept;
	List<ProfVO> lProf;

	JComboBox<String> jcbDept, jcbProf;

	JTextField JtfCrsName, JtfCrsCode, JtfCredit, JtfProfName;

	JButton jbtnRegister, jbtnCancel;

	JLabel jlBack, jlCrsMgtTitle , jlCrsReg;

	JLabel jlDept, jlCrs, jlCrsCode, jlProf, jlCredit, jlNecessary;

	public AdminCrsRegDisgn(AdminCrsDesign acd, String title) {
		super(acd, title, true);
		this.acd = acd;

		// 공통경로
		String commonPath = "src/eduCourse_prj/image/common/";
		String crsPath = "src/eduCourse_prj/image/crs/";


		Font font = new Font("나눔스퀘어라운드 ExtraBold", Font.BOLD, 15);



		setSize(1000, 650);
		setLayout(null);
		setLocationRelativeTo(null);

		

		jlDept = new JLabel("학과");
		jlDept.setFont(font);
		jlDept.setBounds(320, 200, 100, 30);
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
		
		jcbDept.setBounds(400, 200, 200, 30);
		jcbDept.setFont(font);
		add(jcbDept);


		
		jlCrs = new JLabel("과목");
		jlCrs.setFont(font);
		jlCrs.setBounds(320, 250, 100, 30);
		add(jlCrs);
		
		JtfCrsName = new JTextField();
		JtfCrsName.setFont(font);
		JtfCrsName.setBounds(400, 250, 200, 30);
		add(JtfCrsName);
		
	
		jlCrsCode = new JLabel("과목코드");
		jlCrsCode.setFont(font);
		jlCrsCode.setBounds(320, 300, 100, 30);
		add(jlCrsCode);
		
		JtfCrsCode = new JTextField();
		JtfCrsCode.setFont(font);
		JtfCrsCode.setBounds(400, 300, 200, 30);
		add(JtfCrsCode);


		
		
		
		jlCredit = new JLabel("학점");
		jlCredit.setFont(font);
		jlCredit.setBounds(320, 400, 100, 30);
		add(jlCredit);
	
		JtfCredit = new JTextField();
		JtfCredit.setFont(font);
		JtfCredit.setBounds(400, 400, 200, 30);
		add(JtfCredit);

		jbtnRegister = new JButton(new ImageIcon(commonPath + "Reg.png"));
		jbtnRegister.setFont(font);
		jbtnRegister.setBounds(345, 490, 111, 59);
		add(jbtnRegister);

		jbtnCancel = new JButton(new ImageIcon(commonPath + "Cancel.png"));
		jbtnCancel.setFont(font);
		jbtnCancel.setBounds(530, 490, 111, 59);
		add(jbtnCancel);

		Font sfont = new Font("나눔스퀘어라운드 ExtraBold", Font.BOLD, 10);
		jlNecessary = new JLabel("과목 과목코드 학점은 필수 입력사항입니다.");
		jlNecessary.setFont(sfont);
		jlNecessary.setForeground(Color.RED);
		jlNecessary.setBounds(470, 430, 300, 30);
		add(jlNecessary);

		// 배경 추가

		
		jlCrsMgtTitle = new JLabel(new ImageIcon(crsPath + "crsMgtTitle.png"));
		jlCrsMgtTitle.setBounds(10, 76, 967, 44);
		add(jlCrsMgtTitle);

		jlCrsReg = new JLabel(new ImageIcon(crsPath + "crsRegTitle.png"));
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
		return JtfCrsName;
	}

	public JTextField getJtfCrsCode() {
		return JtfCrsCode;
	}

	public JTextField getJtfCredit() {
		return JtfCredit;
	}

	public JTextField getJtfProfName() {
		return JtfProfName;
	}

	public JButton getJbtnRegister() {
		return jbtnRegister;
	}

	public JButton getJbtnCancel() {
		return jbtnCancel;
	}


}