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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import eduCourse_prj.VO.AdminVO;
import eduCourse_prj.VO.CrsVO;
import eduCourse_prj.VO.DeptVO;
import eduCourse_prj.admin.dao.AdminDAO;
import eduCourse_prj.admin.event.AdminAdminMgtEvent;
import eduCourse_prj.admin.event.AdminCrsEvent;

@SuppressWarnings("serial")
public class AdminCrsDesign extends JDialog {
	AdminHomeDesign awd;
	AdminDAO aDAO = AdminDAO.getInstance();

	JLabel jlBack;// 배경
	private JLabel topLogin; // 우상단 로그인상태 확인창

	private JLabel crsMgt;
	
	private JLabel jlDept;
	private JLabel jlCrsName;

	private JTable jtbCrsMgt;
	private DefaultTableModel dtmAdminMgt;

	private JButton jbtnCrsReg, jbtnSlct, jbtnDel ,jbtnSlctTop;

	private DefaultTableModel dtmCrsMgt;
	
	private JComboBox<String> jcbDept;
	private JTextField jtfCrsName;

	private List<DeptVO> lDept;
	

	public AdminCrsDesign(AdminHomeDesign awd, String title) {
		super(awd, title, true);
		this.awd = awd;
		setLayout(null);
		setSize(1000, 650);

		String commonPath = "src/eduCourse_prj/image/common/";
		String adminPath = "src/eduCourse_prj/image/admin/";
		String crsPath = "src/eduCourse_prj/image/crs/";

		jlBack = new JLabel(new ImageIcon(commonPath + "back.png"));
		jlBack.setBounds(0, 0, 984, 620);
		add(jlBack);

		// 관리자관리, 등록 라벨 추가

		crsMgt = new JLabel(new ImageIcon(adminPath + "CourMgtBanner_new.png"));
		crsMgt.setBounds(10, 76, 967, 45);
		add(crsMgt);

		// 우상단 로그인상태 확인창 추가
		topLogin = new JLabel(awd.getlVO().getName() + " 관리자님 로그인 중");
		Font font = new Font("나눔스퀘어라운드 ExtraBold", Font.BOLD, 15);
		topLogin.setFont(font);
		topLogin.setForeground(Color.WHITE);
		topLogin.setBounds(670, 30, 200, 20);
		add(topLogin);

		// 테이블 추가
		String[] tempColumn = { "학과", "과목" };
		dtmCrsMgt = new DefaultTableModel(tempColumn, 0) {
			public boolean isCellEditable(int row, int column) {
				return false; // 테이블 셀 수정 불가하도록 설정
			} // isCellEditable
		};
		jtbCrsMgt = new JTable(dtmCrsMgt);
		JScrollPane jsp = new JScrollPane(jtbCrsMgt);

		jtbCrsMgt.setRowHeight(30); // 행 높이 조절
		jsp.setBounds(10, 200, 967, 250);
		add(jsp);

		
		// 학과 라벨,콤보박스 // 교번 라벨,텍스트필드 추가
		jlDept = new JLabel("학과");
		jlDept.setFont(font);
		jlDept.setBounds(180, 150, 50, 20);
		add(jlDept);

		jcbDept = new JComboBox<String>();
		jcbDept.setFont(font);
		jcbDept.setBounds(230, 145, 200, 30);
		add(jcbDept);
		
		seltAllDept();

		jlCrsName = new JLabel("과목명");
		jlCrsName.setFont(font);
		jlCrsName.setBounds(480, 150, 50, 20);
		add(jlCrsName);

		jtfCrsName = new JTextField();
		jtfCrsName.setFont(font);
		jtfCrsName.setBounds(530, 145, 200, 30);
		add(jtfCrsName);
		
		// 상단 조회버튼 추가
		jbtnSlctTop = new JButton(new ImageIcon(commonPath + "searchButton_new.png"));
		jbtnSlctTop.setBounds(750, 145, 55, 30);
		add(jbtnSlctTop);
		
		
		
		
		// 과목등록, 조회, 삭제 버튼 추가

		jbtnCrsReg = new JButton(new ImageIcon(crsPath + "crsRegBtn_new.png"));
		jbtnSlct = new JButton(new ImageIcon(commonPath + "SlctButton_new.png"));
		jbtnDel = new JButton(new ImageIcon(commonPath + "DelButton_new.png"));

		jbtnCrsReg.setBounds(280, 490, 142, 50);
		jbtnSlct.setBounds(480, 490, 90, 50);
		jbtnDel.setBounds(600, 490, 90, 50);

		add(jbtnCrsReg);
		add(jbtnSlct);
		add(jbtnDel);



		// 테이블 컬럼 가운데 정렬
		setTbHorizontal();

		// 이벤트 클래스 연결

		add(jlBack);

		AdminCrsEvent ace = new AdminCrsEvent(this);
		addWindowListener(ace);
		jbtnCrsReg.addActionListener(ace);
		jbtnSlct.addActionListener(ace);
		jbtnDel.addActionListener(ace);
		jbtnSlctTop.addActionListener(ace);
		setLocationRelativeTo(null);

		setVisible(true);
	}

	
	
	
	/**
	 * DB에서 모든 학과 정보를 가져오는 method
	 */
	public void seltAllDept() {
	
	try {// 학과

		// 모든 학과 정보 가져오기
		lDept = aDAO.slctAllDept();

		// "전체 아이템 추가"
		jcbDept.addItem("전체");

		// 학과명만 저장하는 리스트에 학과명 저장
		for (DeptVO dept : lDept) {
			jcbDept.addItem(dept.getDept_name());

		}

	} catch (SQLException e) {
		e.printStackTrace();
	}
	
	}
	
	
	
	
	
	
	
	
	
	
	

	/**
	 * 테이블의 컬럼을 가운데 정렬
	 */
	public void setTbHorizontal() {
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(SwingConstants.CENTER);
		TableColumnModel tcm = jtbCrsMgt.getColumnModel();
		for (int i = 0; i < tcm.getColumnCount(); i++) {
			tcm.getColumn(i).setCellRenderer(dtcr);
		} // end for
	} // setTbHorizontal

	public AdminHomeDesign getAwd() {
		return awd;
	}

	public JLabel getJlBack() {
		return jlBack;
	}

	public JLabel getTopLogin() {
		return topLogin;
	}

	public JLabel getAdminMgt() {
		return crsMgt;
	}

	public JTable getJtbCrsMgt() {
		return jtbCrsMgt;
	}

	public DefaultTableModel getDtmAdminMgt() {
		return dtmAdminMgt;
	}

	public JButton getJbtnCrsReg() {
		return jbtnCrsReg;
	}

	public JButton getJbtnSlct() {
		return jbtnSlct;
	}

	public JButton getJbtnDel() {
		return jbtnDel;
	}

	public DefaultTableModel getDtmCrsMgt() {
		return dtmCrsMgt;
	}

	public JLabel getCrsMgt() {
		return crsMgt;
	}

	public JLabel getJlDept() {
		return jlDept;
	}

	public JLabel getJlCrsName() {
		return jlCrsName;
	}

	public JButton getJbtnSlctTop() {
		return jbtnSlctTop;
	}

	public JComboBox<String> getJcbDept() {
		return jcbDept;
	}

	public JTextField getJtfCrsName() {
		return jtfCrsName;
	}

	public List<DeptVO> getLDept() {
		return lDept;
	}

	
	
}
