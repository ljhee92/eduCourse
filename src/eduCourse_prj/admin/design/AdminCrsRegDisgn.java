package eduCourse_prj.admin.design;

import java.sql.SQLException;

import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
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

	JComboBox<String> jcbDept;
	JComboBox<String> jcbProf;
	JTextField JtfCrsName;
	JTextField JtfCrsCode;
	JTextField JtfCredit;
	JTextField JtfProfName;

	JButton jbtnRegister; // 등록버튼
	JButton jbtnCancel; // 취소버튼

	public AdminCrsRegDisgn(AdminCrsDesign acd, String title) {
		super(acd, title, true);
		this.acd = acd;

		setLayout(null);
		setSize(1000, 650);

		// jcbDept 초기화
		jcbDept = new JComboBox<>();
		jcbDept.setBounds(400, 100, 200, 30);
		add(jcbDept);

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

		// JcbProf 초기화
		jcbProf = new JComboBox<>();
		jcbProf.setBounds(400, 250, 200, 30);

		try {

			// 최초 선택된 학과에 소속되어있는 교수를 리스트에 저장

			int dept_code = lDept.get(jcbDept.getSelectedIndex()).getDept_code();

			lProf = pDAO.slctDeptProf(dept_code);

			// 교수명만 저장하는 리스트에 교수명 저장
			for (ProfVO prof : lProf) {
				jcbProf.addItem(prof.getProf_name());

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		add(jcbProf);

		JtfCrsName = new JTextField("과목명");
		JtfCrsName.setBounds(400, 150, 200, 30);
		add(JtfCrsName);

		JtfCrsCode = new JTextField("과목코드");
		JtfCrsCode.setBounds(400, 200, 200, 30);
		add(JtfCrsCode);

		JtfCredit = new JTextField("학점");
		JtfCredit.setBounds(400, 300, 200, 30);
		add(JtfCredit);

		jbtnRegister = new JButton("등록");
		jbtnRegister.setBounds(300, 400, 200, 30);
		add(jbtnRegister);

		jbtnCancel = new JButton("취소");
		jbtnCancel.setBounds(500, 400, 200, 30);
		add(jbtnCancel);

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