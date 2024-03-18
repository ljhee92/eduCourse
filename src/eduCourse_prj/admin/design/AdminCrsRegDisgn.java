package eduCourse_prj.admin.design;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JTextField;

import eduCourse_prj.VO.DeptVO;
import eduCourse_prj.admin.dao.AdminDAO;
import eduCourse_prj.admin.event.AdminCrsRegEvent;

public class AdminCrsRegDisgn extends JDialog {
	AdminCrsDesign acd;
	AdminDAO aDAO = AdminDAO.getInstance();

	List<DeptVO> ldept;
	JComboBox<String> jcbdept;
	JTextField crsName;
	JTextField crsCode;
	JTextField credit;

	JButton jbtnRegister; // 등록버튼
	JButton jbtnCancel; // 취소버튼

	public AdminCrsRegDisgn(AdminCrsDesign acd, String title) {
		super(acd, title, true);
		this.acd = acd;

		setLayout(null);
		setSize(1000, 650);

		// JComboBox 초기화
		jcbdept = new JComboBox<>();
		jcbdept.setBounds(400, 100, 200, 30);
		add(jcbdept);

		try {

			// 모든 학과 정보 가져오기
			ldept = aDAO.slctAllDept();

			// 학과명만 저장하는 리스트에 학과명 저장
			for (DeptVO dept : ldept) {
				jcbdept.addItem(dept.getDept_name());

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		crsName = new JTextField("과목명");
		crsName.setBounds(400, 150, 200, 30);
		add(crsName);

		crsCode = new JTextField("과목코드");
		crsCode.setBounds(400, 200, 200, 30);
		add(crsCode);

		credit = new JTextField("학점");
		credit.setBounds(400, 300, 200, 30);
		add(credit);

		jbtnRegister = new JButton("등록");
		jbtnRegister.setBounds(300, 400, 200, 30);
		add(jbtnRegister);

		jbtnCancel = new JButton("취소");
		jbtnCancel.setBounds(500, 400, 200, 30);
		add(jbtnCancel);

		AdminCrsRegEvent acre = new AdminCrsRegEvent(this);
		addWindowListener(acre);
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

	public List<DeptVO> getLdept() {
		return ldept;
	}

	public JComboBox<String> getJcbdept() {
		return jcbdept;
	}

	public JTextField getCrsName() {
		return crsName;
	}

	public JTextField getCrsCode() {
		return crsCode;
	}

	public JTextField getCredit() {
		return credit;
	}

	public JButton getJbtnRegister() {
		return jbtnRegister;
	}

	public JButton getJbtnCancel() {
		return jbtnCancel;
	}

}
