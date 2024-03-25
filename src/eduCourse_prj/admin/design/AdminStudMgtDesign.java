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

import eduCourse_prj.VO.CrsVO;
import eduCourse_prj.VO.DeptVO;

import eduCourse_prj.admin.dao.AdminDAO;

import eduCourse_prj.admin.event.AdminStudMgtEvent;

@SuppressWarnings("serial")
public class AdminStudMgtDesign extends JDialog {
	private AdminHomeDesign awd;
	private AdminDAO aDAO = AdminDAO.getInstance();
	private JLabel jlBack;// 배경
	private JLabel topLogin; // 우상단 로그인상태 확인창
	private JLabel adminMgt;
	private JTable jtbStdMgt;
	private DefaultTableModel dtmAdminMgt;

	private JButton jbtnSlct, jbtnSlctTop;

	private JLabel jlDept;
	private JLabel jlCrs;
	private JLabel jlstdNum;

	private JComboBox<String> jcbDept;
	private JComboBox<String> jcbCrs;
	private JTextField jtfStdNum;

	List<DeptVO> lDept;
	List<CrsVO> lCrs;

	private DefaultTableModel dtmStdMgt;

	public AdminStudMgtDesign(AdminHomeDesign awd, String title) {
		super(awd, title, true);
		this.awd = awd;
		setLayout(null);
		setSize(1000, 650);

		String commonPath = "C:/dev/workspace/eduCourse_prj/src/eduCourse_prj/image/common/";
		String adminPath = "C:/dev/workspace/eduCourse_prj/src/eduCourse_prj/image/admin/";

		jlBack = new JLabel(new ImageIcon(commonPath + "back.png"));
		jlBack.setBounds(0, 0, 984, 620);
		add(jlBack);

		// 관리자관리, 등록 라벨 추가
		adminMgt = new JLabel(new ImageIcon(adminPath + "studentTitle.png"));
		adminMgt.setBounds(10, 76, 967, 44);
		add(adminMgt);

		// 우상단 로그인상태 확인창 추가
		topLogin = new JLabel(awd.getlVO().getName() + " 관리자님 로그인 중");
		Font font = new Font("나눔스퀘어라운드 ExtraBold", Font.BOLD, 15);
		topLogin.setFont(font);
		topLogin.setForeground(Color.WHITE);
		topLogin.setBounds(670, 30, 200, 20);
		add(topLogin);

		jlDept = new JLabel("학과");
		jlDept.setFont(font);
		jlDept.setBounds(240, 150, 50, 20);
		add(jlDept);

		jlCrs = new JLabel("과목");
		jlCrs.setFont(font);
		jlCrs.setBounds(500, 150, 50, 20);
		add(jlCrs);

		jlstdNum = new JLabel("학번");
		jlstdNum.setFont(font);
		jlstdNum.setBounds(240, 190, 50, 20);
		add(jlstdNum);

		jcbDept = new JComboBox<String>();
		jcbDept.setFont(font);
		jcbDept.setBounds(280, 145, 200, 30);
		add(jcbDept);

		jcbCrs = new JComboBox<String>();
		jcbCrs.setFont(font);
		jcbCrs.setBounds(540, 145, 200, 30);
		add(jcbCrs);

		jtfStdNum = new JTextField();
		jtfStdNum.setFont(font);
		jtfStdNum.setBounds(280, 185, 200, 30);
		add(jtfStdNum);

		///////////////////////////////////////////////////////////////////
		/////////////////////// 초기 설정/////////////////////////////////////
		////////////////////////////////////////////////////////////////////

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

		try {// 과목

			// 모든 과목 정보 가져오기

			jcbCrs.addItem("전체");

			lCrs = aDAO.slctAllCrs();
			// 과목명만 저장하는 리스트에 과목명 저장
			for (CrsVO crs : lCrs) {
				jcbCrs.addItem(crs.getCourName());

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		/////////////////////////////////////////////////////////////////////
		/////////////////////////////////////////////////////////////////////

		// 테이블 추가
		String[] tempColumn = { "학과", "과목", "학번", "이름" };
		dtmStdMgt = new DefaultTableModel(tempColumn, 0) {
			public boolean isCellEditable(int row, int column) {
				return false; // 테이블 셀 수정 불가하도록 설정
			} // isCellEditable
		};
		jtbStdMgt = new JTable(dtmStdMgt);
		JScrollPane jsp = new JScrollPane(jtbStdMgt);

		jtbStdMgt.setRowHeight(30); // 행 높이 조절
		jsp.setBounds(10, 230, 967, 250);
		add(jsp);

		/////////////////////////////////////////////////////////////
		// 조회 버튼 추가
		jbtnSlctTop = new JButton(new ImageIcon(commonPath + "search.png"));
		jbtnSlctTop.setBounds(780, 145, 70, 30);
		add(jbtnSlctTop);
		
		jbtnSlct = new JButton(new ImageIcon(commonPath + "Slct.png"));
		jbtnSlct.setBounds(440, 500, 111, 59);
		add(jbtnSlct);
		
		////////////////////////////////////////////////////////////////////

		// 테이블 컬럼 가운데 정렬
		setTbHorizontal();

		// 이벤트 클래스 연결

		add(jlBack);

		AdminStudMgtEvent asme = new AdminStudMgtEvent(this);
		addWindowListener(asme);
		jbtnSlct.addActionListener(asme);
		jbtnSlctTop.addActionListener(asme);
		jcbDept.addActionListener(asme);

		setLocationRelativeTo(null);

		setVisible(true);
	}

	/**
	 * 테이블의 컬럼을 가운데 정렬
	 */
	public void setTbHorizontal() {
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(SwingConstants.CENTER);
		TableColumnModel tcm = jtbStdMgt.getColumnModel();
		for (int i = 0; i < tcm.getColumnCount(); i++) {
			tcm.getColumn(i).setCellRenderer(dtcr);
		} // end for
	} // setTbHorizontal

	public AdminHomeDesign getAwd() {
		return awd;
	}

	public AdminDAO getaDAO() {
		return aDAO;
	}

	public JLabel getJlBack() {
		return jlBack;
	}

	public JLabel getTopLogin() {
		return topLogin;
	}

	public JLabel getAdminMgt() {
		return adminMgt;
	}

	public JTable getJtbStdMgt() {
		return jtbStdMgt;
	}

	public DefaultTableModel getDtmAdminMgt() {
		return dtmAdminMgt;
	}

	public JButton getJbtnSlct() {
		return jbtnSlct;
	}

	public JButton getJbtnSlctTop() {
		return jbtnSlctTop;
	}

	public JLabel getJlDept() {
		return jlDept;
	}

	public JLabel getJlCrs() {
		return jlCrs;
	}

	public JLabel getJlstdNum() {
		return jlstdNum;
	}

	public JComboBox<String> getJcbDept() {
		return jcbDept;
	}

	public JComboBox<String> getJcbCrs() {
		return jcbCrs;
	}

	public JTextField getJtfStdNum() {
		return jtfStdNum;
	}

	public List<DeptVO> getLDept() {
		return lDept;
	}

	public List<CrsVO> getLCrs() {
		return lCrs;
	}

	public DefaultTableModel getDtmStdMgt() {
		return dtmStdMgt;
	}

}
